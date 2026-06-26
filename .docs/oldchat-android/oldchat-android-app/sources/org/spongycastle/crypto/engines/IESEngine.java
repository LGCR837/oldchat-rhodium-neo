package org.spongycastle.crypto.engines;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.spongycastle.crypto.BasicAgreement;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.EphemeralKeyPair;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.KeyParser;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.generators.EphemeralKeyPairGenerator;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.IESParameters;
import org.spongycastle.crypto.params.IESWithCipherParameters;
import org.spongycastle.crypto.params.KDFParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public class IESEngine {
    private byte[] IV;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    byte[] f7325V;
    BasicAgreement agree;
    BufferedBlockCipher cipher;
    boolean forEncryption;
    DerivationFunction kdf;
    private EphemeralKeyPairGenerator keyPairGenerator;
    private KeyParser keyParser;
    Mac mac;
    byte[] macBuf;
    IESParameters param;
    CipherParameters privParam;
    CipherParameters pubParam;

    public IESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac) {
        this.agree = basicAgreement;
        this.kdf = derivationFunction;
        this.mac = mac;
        this.macBuf = new byte[mac.getMacSize()];
        this.cipher = null;
    }

    private byte[] decryptBlock(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        byte[] bArr2;
        byte[] bArr3;
        int iProcessBytes;
        if (i3 < this.f7325V.length + this.mac.getMacSize()) {
            throw new InvalidCipherTextException("Length of input must be greater than the MAC and V combined");
        }
        if (this.cipher == null) {
            int length = (i3 - this.f7325V.length) - this.mac.getMacSize();
            byte[] bArr4 = new byte[length];
            int macKeySize = this.param.getMacKeySize() / 8;
            bArr2 = new byte[macKeySize];
            int i4 = length + macKeySize;
            byte[] bArr5 = new byte[i4];
            this.kdf.generateBytes(bArr5, 0, i4);
            if (this.f7325V.length != 0) {
                System.arraycopy(bArr5, 0, bArr2, 0, macKeySize);
                System.arraycopy(bArr5, macKeySize, bArr4, 0, length);
            } else {
                System.arraycopy(bArr5, 0, bArr4, 0, length);
                System.arraycopy(bArr5, length, bArr2, 0, macKeySize);
            }
            bArr3 = new byte[length];
            for (int i5 = 0; i5 != length; i5++) {
                bArr3[i5] = (byte) (bArr[(this.f7325V.length + i2) + i5] ^ bArr4[i5]);
            }
            iProcessBytes = 0;
        } else {
            int cipherKeySize = ((IESWithCipherParameters) this.param).getCipherKeySize() / 8;
            byte[] bArr6 = new byte[cipherKeySize];
            int macKeySize2 = this.param.getMacKeySize() / 8;
            bArr2 = new byte[macKeySize2];
            int i6 = cipherKeySize + macKeySize2;
            byte[] bArr7 = new byte[i6];
            this.kdf.generateBytes(bArr7, 0, i6);
            System.arraycopy(bArr7, 0, bArr6, 0, cipherKeySize);
            System.arraycopy(bArr7, cipherKeySize, bArr2, 0, macKeySize2);
            CipherParameters keyParameter = new KeyParameter(bArr6);
            byte[] bArr8 = this.IV;
            if (bArr8 != null) {
                keyParameter = new ParametersWithIV(keyParameter, bArr8);
            }
            this.cipher.init(false, keyParameter);
            bArr3 = new byte[this.cipher.getOutputSize((i3 - this.f7325V.length) - this.mac.getMacSize())];
            BufferedBlockCipher bufferedBlockCipher = this.cipher;
            byte[] bArr9 = this.f7325V;
            iProcessBytes = bufferedBlockCipher.processBytes(bArr, i2 + bArr9.length, (i3 - bArr9.length) - this.mac.getMacSize(), bArr3, 0);
        }
        byte[] encodingV = this.param.getEncodingV();
        byte[] lengthTag = this.f7325V.length != 0 ? getLengthTag(encodingV) : null;
        int i7 = i2 + i3;
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i7 - this.mac.getMacSize(), i7);
        int length2 = bArrCopyOfRange.length;
        byte[] bArr10 = new byte[length2];
        this.mac.init(new KeyParameter(bArr2));
        Mac mac = this.mac;
        byte[] bArr11 = this.f7325V;
        mac.update(bArr, i2 + bArr11.length, (i3 - bArr11.length) - length2);
        if (encodingV != null) {
            this.mac.update(encodingV, 0, encodingV.length);
        }
        if (this.f7325V.length != 0) {
            this.mac.update(lengthTag, 0, lengthTag.length);
        }
        this.mac.doFinal(bArr10, 0);
        if (!Arrays.constantTimeAreEqual(bArrCopyOfRange, bArr10)) {
            throw new InvalidCipherTextException("invalid MAC");
        }
        BufferedBlockCipher bufferedBlockCipher2 = this.cipher;
        return bufferedBlockCipher2 == null ? bArr3 : Arrays.copyOfRange(bArr3, 0, iProcessBytes + bufferedBlockCipher2.doFinal(bArr3, iProcessBytes));
    }

    private byte[] encryptBlock(byte[] bArr, int i2, int i3) {
        byte[] bArr2;
        byte[] bArr3;
        if (this.cipher == null) {
            byte[] bArr4 = new byte[i3];
            int macKeySize = this.param.getMacKeySize() / 8;
            bArr3 = new byte[macKeySize];
            int i4 = i3 + macKeySize;
            byte[] bArr5 = new byte[i4];
            this.kdf.generateBytes(bArr5, 0, i4);
            if (this.f7325V.length != 0) {
                System.arraycopy(bArr5, 0, bArr3, 0, macKeySize);
                System.arraycopy(bArr5, macKeySize, bArr4, 0, i3);
            } else {
                System.arraycopy(bArr5, 0, bArr4, 0, i3);
                System.arraycopy(bArr5, i3, bArr3, 0, macKeySize);
            }
            bArr2 = new byte[i3];
            for (int i5 = 0; i5 != i3; i5++) {
                bArr2[i5] = (byte) (bArr[i2 + i5] ^ bArr4[i5]);
            }
        } else {
            int cipherKeySize = ((IESWithCipherParameters) this.param).getCipherKeySize() / 8;
            byte[] bArr6 = new byte[cipherKeySize];
            int macKeySize2 = this.param.getMacKeySize() / 8;
            byte[] bArr7 = new byte[macKeySize2];
            int i6 = cipherKeySize + macKeySize2;
            byte[] bArr8 = new byte[i6];
            this.kdf.generateBytes(bArr8, 0, i6);
            System.arraycopy(bArr8, 0, bArr6, 0, cipherKeySize);
            System.arraycopy(bArr8, cipherKeySize, bArr7, 0, macKeySize2);
            if (this.IV != null) {
                this.cipher.init(true, new ParametersWithIV(new KeyParameter(bArr6), this.IV));
            } else {
                this.cipher.init(true, new KeyParameter(bArr6));
            }
            bArr2 = new byte[this.cipher.getOutputSize(i3)];
            int iProcessBytes = this.cipher.processBytes(bArr, i2, i3, bArr2, 0);
            i3 = iProcessBytes + this.cipher.doFinal(bArr2, iProcessBytes);
            bArr3 = bArr7;
        }
        byte[] encodingV = this.param.getEncodingV();
        byte[] lengthTag = this.f7325V.length != 0 ? getLengthTag(encodingV) : null;
        int macSize = this.mac.getMacSize();
        byte[] bArr9 = new byte[macSize];
        this.mac.init(new KeyParameter(bArr3));
        this.mac.update(bArr2, 0, bArr2.length);
        if (encodingV != null) {
            this.mac.update(encodingV, 0, encodingV.length);
        }
        if (this.f7325V.length != 0) {
            this.mac.update(lengthTag, 0, lengthTag.length);
        }
        this.mac.doFinal(bArr9, 0);
        byte[] bArr10 = this.f7325V;
        byte[] bArr11 = new byte[bArr10.length + i3 + macSize];
        System.arraycopy(bArr10, 0, bArr11, 0, bArr10.length);
        System.arraycopy(bArr2, 0, bArr11, this.f7325V.length, i3);
        System.arraycopy(bArr9, 0, bArr11, this.f7325V.length + i3, macSize);
        return bArr11;
    }

    private void extractParams(CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            this.IV = null;
            this.param = (IESParameters) cipherParameters;
        } else {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.IV = parametersWithIV.getIV();
            this.param = (IESParameters) parametersWithIV.getParameters();
        }
    }

    public BufferedBlockCipher getCipher() {
        return this.cipher;
    }

    public byte[] getLengthTag(byte[] bArr) {
        byte[] bArr2 = new byte[8];
        if (bArr != null) {
            Pack.longToBigEndian(((long) bArr.length) * 8, bArr2, 0);
        }
        return bArr2;
    }

    public Mac getMac() {
        return this.mac;
    }

    public void init(boolean z2, CipherParameters cipherParameters, CipherParameters cipherParameters2, CipherParameters cipherParameters3) {
        this.forEncryption = z2;
        this.privParam = cipherParameters;
        this.pubParam = cipherParameters2;
        this.f7325V = new byte[0];
        extractParams(cipherParameters3);
    }

    public byte[] processBlock(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        if (this.forEncryption) {
            EphemeralKeyPairGenerator ephemeralKeyPairGenerator = this.keyPairGenerator;
            if (ephemeralKeyPairGenerator != null) {
                EphemeralKeyPair ephemeralKeyPairGenerate = ephemeralKeyPairGenerator.generate();
                this.privParam = ephemeralKeyPairGenerate.getKeyPair().getPrivate();
                this.f7325V = ephemeralKeyPairGenerate.getEncodedPublicKey();
            }
        } else if (this.keyParser != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, i2, i3);
            try {
                this.pubParam = this.keyParser.readKey(byteArrayInputStream);
                this.f7325V = Arrays.copyOfRange(bArr, i2, (i3 - byteArrayInputStream.available()) + i2);
            } catch (IOException e2) {
                throw new InvalidCipherTextException("unable to recover ephemeral public key: " + e2.getMessage(), e2);
            } catch (IllegalArgumentException e3) {
                throw new InvalidCipherTextException("unable to recover ephemeral public key: " + e3.getMessage(), e3);
            }
        }
        this.agree.init(this.privParam);
        byte[] bArrAsUnsignedByteArray = BigIntegers.asUnsignedByteArray(this.agree.getFieldSize(), this.agree.calculateAgreement(this.pubParam));
        byte[] bArr2 = this.f7325V;
        if (bArr2.length != 0) {
            byte[] bArrConcatenate = Arrays.concatenate(bArr2, bArrAsUnsignedByteArray);
            Arrays.fill(bArrAsUnsignedByteArray, (byte) 0);
            bArrAsUnsignedByteArray = bArrConcatenate;
        }
        try {
            this.kdf.init(new KDFParameters(bArrAsUnsignedByteArray, this.param.getDerivationV()));
            byte[] bArrEncryptBlock = this.forEncryption ? encryptBlock(bArr, i2, i3) : decryptBlock(bArr, i2, i3);
            Arrays.fill(bArrAsUnsignedByteArray, (byte) 0);
            return bArrEncryptBlock;
        } catch (Throwable th) {
            Arrays.fill(bArrAsUnsignedByteArray, (byte) 0);
            throw th;
        }
    }

    public void init(AsymmetricKeyParameter asymmetricKeyParameter, CipherParameters cipherParameters, EphemeralKeyPairGenerator ephemeralKeyPairGenerator) {
        this.forEncryption = true;
        this.pubParam = asymmetricKeyParameter;
        this.keyPairGenerator = ephemeralKeyPairGenerator;
        extractParams(cipherParameters);
    }

    public IESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac, BufferedBlockCipher bufferedBlockCipher) {
        this.agree = basicAgreement;
        this.kdf = derivationFunction;
        this.mac = mac;
        this.macBuf = new byte[mac.getMacSize()];
        this.cipher = bufferedBlockCipher;
    }

    public void init(AsymmetricKeyParameter asymmetricKeyParameter, CipherParameters cipherParameters, KeyParser keyParser) {
        this.forEncryption = false;
        this.privParam = asymmetricKeyParameter;
        this.keyParser = keyParser;
        extractParams(cipherParameters);
    }
}

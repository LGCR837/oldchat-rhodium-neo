package org.spongycastle.crypto.encodings;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class PKCS1Encoding implements AsymmetricBlockCipher {
    private static final int HEADER_LENGTH = 10;
    public static final String NOT_STRICT_LENGTH_ENABLED_PROPERTY = "org.spongycastle.pkcs1.not_strict";
    public static final String STRICT_LENGTH_ENABLED_PROPERTY = "org.spongycastle.pkcs1.strict";
    private byte[] blockBuffer;
    private AsymmetricBlockCipher engine;
    private byte[] fallback;
    private boolean forEncryption;
    private boolean forPrivateKey;
    private int pLen;
    private SecureRandom random;
    private boolean useStrictLength;

    public PKCS1Encoding(AsymmetricBlockCipher asymmetricBlockCipher) {
        this.pLen = -1;
        this.fallback = null;
        this.engine = asymmetricBlockCipher;
        this.useStrictLength = useStrict();
    }

    private static int checkPkcs1Encoding(byte[] bArr, int i2) {
        int i3 = bArr[0] ^ 2;
        int i4 = i2 + 1;
        int length = bArr.length - i4;
        for (int i5 = 1; i5 < length; i5++) {
            byte b2 = bArr[i5];
            int i6 = b2 | (b2 >> 1);
            int i7 = i6 | (i6 >> 2);
            i3 |= ((i7 | (i7 >> 4)) & 1) - 1;
        }
        int i8 = bArr[bArr.length - i4] | i3;
        int i9 = i8 | (i8 >> 1);
        int i10 = i9 | (i9 >> 2);
        return (((i10 | (i10 >> 4)) & 1) - 1) ^ (-1);
    }

    private byte[] decodeBlock(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        if (this.pLen != -1) {
            return decodeBlockOrRandom(bArr, i2, i3);
        }
        byte[] bArrProcessBlock = this.engine.processBlock(bArr, i2, i3);
        boolean z2 = this.useStrictLength & (bArrProcessBlock.length != this.engine.getOutputBlockSize());
        if (bArrProcessBlock.length < getOutputBlockSize()) {
            bArrProcessBlock = this.blockBuffer;
        }
        byte b2 = bArrProcessBlock[0];
        boolean z3 = !this.forPrivateKey ? b2 == 1 : b2 == 2;
        int iFindStart = findStart(b2, bArrProcessBlock) + 1;
        if (z3 || (iFindStart < 10)) {
            Arrays.fill(bArrProcessBlock, (byte) 0);
            throw new InvalidCipherTextException("block incorrect");
        }
        if (z2) {
            Arrays.fill(bArrProcessBlock, (byte) 0);
            throw new InvalidCipherTextException("block incorrect size");
        }
        int length = bArrProcessBlock.length - iFindStart;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArrProcessBlock, iFindStart, bArr2, 0, length);
        return bArr2;
    }

    private byte[] decodeBlockOrRandom(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        if (!this.forPrivateKey) {
            throw new InvalidCipherTextException("sorry, this method is only for decryption, not for signing");
        }
        byte[] bArrProcessBlock = this.engine.processBlock(bArr, i2, i3);
        byte[] bArr2 = this.fallback;
        if (bArr2 == null) {
            bArr2 = new byte[this.pLen];
            this.random.nextBytes(bArr2);
        }
        if (this.useStrictLength & (bArrProcessBlock.length != this.engine.getOutputBlockSize())) {
            bArrProcessBlock = this.blockBuffer;
        }
        int iCheckPkcs1Encoding = checkPkcs1Encoding(bArrProcessBlock, this.pLen);
        byte[] bArr3 = new byte[this.pLen];
        int i4 = 0;
        while (true) {
            int i5 = this.pLen;
            if (i4 >= i5) {
                Arrays.fill(bArrProcessBlock, (byte) 0);
                return bArr3;
            }
            bArr3[i4] = (byte) ((bArrProcessBlock[(bArrProcessBlock.length - i5) + i4] & (iCheckPkcs1Encoding ^ (-1))) | (bArr2[i4] & iCheckPkcs1Encoding));
            i4++;
        }
    }

    private byte[] encodeBlock(byte[] bArr, int i2, int i3) {
        if (i3 > getInputBlockSize()) {
            throw new IllegalArgumentException("input data too large");
        }
        int inputBlockSize = this.engine.getInputBlockSize();
        byte[] bArr2 = new byte[inputBlockSize];
        if (this.forPrivateKey) {
            bArr2[0] = 1;
            for (int i4 = 1; i4 != (inputBlockSize - i3) - 1; i4++) {
                bArr2[i4] = -1;
            }
        } else {
            this.random.nextBytes(bArr2);
            bArr2[0] = 2;
            for (int i5 = 1; i5 != (inputBlockSize - i3) - 1; i5++) {
                while (bArr2[i5] == 0) {
                    bArr2[i5] = (byte) this.random.nextInt();
                }
            }
        }
        int i6 = inputBlockSize - i3;
        bArr2[i6 - 1] = 0;
        System.arraycopy(bArr, i2, bArr2, i6, i3);
        return this.engine.processBlock(bArr2, 0, inputBlockSize);
    }

    private int findStart(byte b2, byte[] bArr) {
        boolean z2 = false;
        int i2 = -1;
        for (int i3 = 1; i3 != bArr.length; i3++) {
            byte b3 = bArr[i3];
            if ((b3 == 0) & (i2 < 0)) {
                i2 = i3;
            }
            z2 |= (b3 != -1) & (b2 == 1) & (i2 < 0);
        }
        if (z2) {
            return -1;
        }
        return i2;
    }

    private boolean useStrict() {
        String str = (String) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.spongycastle.crypto.encodings.PKCS1Encoding.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                return System.getProperty(PKCS1Encoding.STRICT_LENGTH_ENABLED_PROPERTY);
            }
        });
        return ((String) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.spongycastle.crypto.encodings.PKCS1Encoding.2
            @Override // java.security.PrivilegedAction
            public Object run() {
                return System.getProperty(PKCS1Encoding.NOT_STRICT_LENGTH_ENABLED_PROPERTY);
            }
        })) != null ? !r1.equals("true") : str == null || str.equals("true");
    }

    @Override // org.spongycastle.crypto.AsymmetricBlockCipher
    public int getInputBlockSize() {
        int inputBlockSize = this.engine.getInputBlockSize();
        return this.forEncryption ? inputBlockSize - 10 : inputBlockSize;
    }

    @Override // org.spongycastle.crypto.AsymmetricBlockCipher
    public int getOutputBlockSize() {
        int outputBlockSize = this.engine.getOutputBlockSize();
        return this.forEncryption ? outputBlockSize : outputBlockSize - 10;
    }

    public AsymmetricBlockCipher getUnderlyingCipher() {
        return this.engine;
    }

    @Override // org.spongycastle.crypto.AsymmetricBlockCipher
    public void init(boolean z2, CipherParameters cipherParameters) {
        AsymmetricKeyParameter asymmetricKeyParameter;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.random = parametersWithRandom.getRandom();
            asymmetricKeyParameter = (AsymmetricKeyParameter) parametersWithRandom.getParameters();
        } else {
            asymmetricKeyParameter = (AsymmetricKeyParameter) cipherParameters;
            if (!asymmetricKeyParameter.isPrivate() && z2) {
                this.random = new SecureRandom();
            }
        }
        this.engine.init(z2, cipherParameters);
        this.forPrivateKey = asymmetricKeyParameter.isPrivate();
        this.forEncryption = z2;
        this.blockBuffer = new byte[this.engine.getOutputBlockSize()];
        if (this.pLen > 0 && this.fallback == null && this.random == null) {
            throw new IllegalArgumentException("encoder requires random");
        }
    }

    @Override // org.spongycastle.crypto.AsymmetricBlockCipher
    public byte[] processBlock(byte[] bArr, int i2, int i3) {
        return this.forEncryption ? encodeBlock(bArr, i2, i3) : decodeBlock(bArr, i2, i3);
    }

    public PKCS1Encoding(AsymmetricBlockCipher asymmetricBlockCipher, int i2) {
        this.pLen = -1;
        this.fallback = null;
        this.engine = asymmetricBlockCipher;
        this.useStrictLength = useStrict();
        this.pLen = i2;
    }

    public PKCS1Encoding(AsymmetricBlockCipher asymmetricBlockCipher, byte[] bArr) {
        this.pLen = -1;
        this.fallback = null;
        this.engine = asymmetricBlockCipher;
        this.useStrictLength = useStrict();
        this.fallback = bArr;
        this.pLen = bArr.length;
    }
}

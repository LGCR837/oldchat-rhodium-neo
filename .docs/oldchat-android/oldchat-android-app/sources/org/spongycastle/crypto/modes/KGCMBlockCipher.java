package org.spongycastle.crypto.modes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.AEADParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;

/* JADX INFO: loaded from: classes.dex */
public class KGCMBlockCipher implements AEADBlockCipher {
    private static final int BITS_IN_BYTE = 8;
    private static final int MIN_MAC_BITS = 64;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    private byte[] f7371H;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private byte[] f7372b;
    private BufferedBlockCipher ctrEngine;
    private BlockCipher engine;
    private boolean forEncryption;
    private byte[] initialAssociatedText;
    private byte[] iv;
    private byte[] temp;
    private static final BigInteger ZERO = BigInteger.valueOf(0);
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger MASK_1_128 = new BigInteger("340282366920938463463374607431768211456", 10);
    private static final BigInteger MASK_2_128 = new BigInteger("340282366920938463463374607431768211455", 10);
    private static final BigInteger POLYRED_128 = new BigInteger("135", 10);
    private static final BigInteger MASK_1_256 = new BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639936", 10);
    private static final BigInteger MASK_2_256 = new BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639935", 10);
    private static final BigInteger POLYRED_256 = new BigInteger("1061", 10);
    private static final BigInteger MASK_1_512 = new BigInteger("13407807929942597099574024998205846127479365820592393377723561443721764030073546976801874298166903427690031858186486050853753882811946569946433649006084096", 10);
    private static final BigInteger MASK_2_512 = new BigInteger("13407807929942597099574024998205846127479365820592393377723561443721764030073546976801874298166903427690031858186486050853753882811946569946433649006084095", 10);
    private static final BigInteger POLYRED_512 = new BigInteger("293", 10);
    private ExposedByteArrayOutputStream associatedText = new ExposedByteArrayOutputStream();
    private ExposedByteArrayOutputStream data = new ExposedByteArrayOutputStream();
    private int macSize = 0;
    private int lambda_c = 0;
    private int lambda_o = 0;
    private byte[] macBlock = null;

    public class ExposedByteArrayOutputStream extends ByteArrayOutputStream {
        public ExposedByteArrayOutputStream() {
        }

        public byte[] getBuffer() {
            return ((ByteArrayOutputStream) this).buf;
        }
    }

    public KGCMBlockCipher(BlockCipher blockCipher) {
        this.engine = blockCipher;
        this.ctrEngine = new BufferedBlockCipher(new KCTRBlockCipher(blockCipher));
        this.initialAssociatedText = new byte[this.engine.getBlockSize()];
        this.iv = new byte[this.engine.getBlockSize()];
        this.f7371H = new byte[this.engine.getBlockSize()];
        this.f7372b = new byte[this.engine.getBlockSize()];
        this.temp = new byte[this.engine.getBlockSize()];
    }

    private void calculateMac(byte[] bArr, int i2, int i3) {
        this.macBlock = new byte[this.engine.getBlockSize()];
        while (i3 > 0) {
            for (int i4 = 0; i4 < this.engine.getBlockSize(); i4++) {
                byte[] bArr2 = this.f7372b;
                bArr2[i4] = (byte) (bArr2[i4] ^ bArr[i4 + i2]);
            }
            multiplyOverField(this.engine.getBlockSize() * 8, this.f7372b, this.f7371H, this.temp);
            byte[] bArrReverse = Arrays.reverse(this.temp);
            this.temp = bArrReverse;
            System.arraycopy(bArrReverse, 0, this.f7372b, 0, this.engine.getBlockSize());
            i3 -= this.engine.getBlockSize();
            i2 += this.engine.getBlockSize();
        }
        Arrays.fill(this.temp, (byte) 0);
        intToBytes(this.lambda_o, this.temp, 0);
        intToBytes(this.lambda_c, this.temp, this.engine.getBlockSize() / 2);
        for (int i5 = 0; i5 < this.engine.getBlockSize(); i5++) {
            byte[] bArr3 = this.f7372b;
            bArr3[i5] = (byte) (bArr3[i5] ^ this.temp[i5]);
        }
        this.engine.processBlock(this.f7372b, 0, this.macBlock, 0);
    }

    private void intToBytes(int i2, byte[] bArr, int i3) {
        bArr[i3 + 3] = (byte) (i2 >> 24);
        bArr[i3 + 2] = (byte) (i2 >> 16);
        bArr[i3 + 1] = (byte) (i2 >> 8);
        bArr[i3] = (byte) i2;
    }

    private void multiplyOverField(int i2, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        byte[] bArr4 = new byte[this.engine.getBlockSize()];
        byte[] bArr5 = new byte[this.engine.getBlockSize()];
        System.arraycopy(bArr, 0, bArr4, 0, this.engine.getBlockSize());
        System.arraycopy(bArr2, 0, bArr5, 0, this.engine.getBlockSize());
        byte[] bArrReverse = Arrays.reverse(bArr4);
        byte[] bArrReverse2 = Arrays.reverse(bArr5);
        if (i2 == 128) {
            bigInteger = MASK_1_128;
            bigInteger2 = MASK_2_128;
            bigInteger3 = POLYRED_128;
        } else if (i2 == 256) {
            bigInteger = MASK_1_256;
            bigInteger2 = MASK_2_256;
            bigInteger3 = POLYRED_256;
        } else if (i2 != 512) {
            bigInteger = MASK_1_128;
            bigInteger2 = MASK_2_128;
            bigInteger3 = POLYRED_128;
        } else {
            bigInteger = MASK_1_512;
            bigInteger2 = MASK_2_512;
            bigInteger3 = POLYRED_512;
        }
        BigInteger bigIntegerXor = ZERO;
        BigInteger bigInteger4 = new BigInteger(1, bArrReverse);
        BigInteger bigInteger5 = new BigInteger(1, bArrReverse2);
        while (true) {
            BigInteger bigInteger6 = ZERO;
            if (bigInteger5.equals(bigInteger6)) {
                byte[] bArrAsUnsignedByteArray = BigIntegers.asUnsignedByteArray(bigIntegerXor.and(bigInteger2));
                Arrays.fill(bArr3, (byte) 0);
                System.arraycopy(bArrAsUnsignedByteArray, 0, bArr3, 0, bArrAsUnsignedByteArray.length);
                return;
            } else {
                BigInteger bigInteger7 = ONE;
                if (bigInteger5.and(bigInteger7).equals(bigInteger7)) {
                    bigIntegerXor = bigIntegerXor.xor(bigInteger4);
                }
                bigInteger4 = bigInteger4.shiftLeft(1);
                if (!bigInteger4.and(bigInteger).equals(bigInteger6)) {
                    bigInteger4 = bigInteger4.xor(bigInteger3);
                }
                bigInteger5 = bigInteger5.shiftRight(1);
            }
        }
    }

    private void processAAD(byte[] bArr, int i2, int i3) {
        this.lambda_o = i3 * 8;
        BlockCipher blockCipher = this.engine;
        byte[] bArr2 = this.f7371H;
        blockCipher.processBlock(bArr2, 0, bArr2, 0);
        while (i3 > 0) {
            for (int i4 = 0; i4 < this.engine.getBlockSize(); i4++) {
                byte[] bArr3 = this.f7372b;
                bArr3[i4] = (byte) (bArr3[i4] ^ bArr[i2 + i4]);
            }
            multiplyOverField(this.engine.getBlockSize() * 8, this.f7372b, this.f7371H, this.temp);
            byte[] bArrReverse = Arrays.reverse(this.temp);
            this.temp = bArrReverse;
            System.arraycopy(bArrReverse, 0, this.f7372b, 0, this.engine.getBlockSize());
            i3 -= this.engine.getBlockSize();
            i2 += this.engine.getBlockSize();
        }
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public int doFinal(byte[] bArr, int i2) throws InvalidCipherTextException, IOException {
        int iDoFinal;
        int size = this.data.size();
        if (this.associatedText.size() > 0) {
            processAAD(this.associatedText.getBuffer(), 0, this.associatedText.size());
        }
        if (!this.forEncryption) {
            this.lambda_c = (size - this.macSize) * 8;
            calculateMac(this.data.getBuffer(), 0, size - this.macSize);
            int iProcessBytes = this.ctrEngine.processBytes(this.data.getBuffer(), 0, size - this.macSize, bArr, i2);
            iDoFinal = iProcessBytes + this.ctrEngine.doFinal(bArr, i2 + iProcessBytes);
        } else {
            if (bArr.length - i2 < this.macSize + size) {
                throw new OutputLengthException("Output buffer too short");
            }
            this.lambda_c = size * 8;
            int iProcessBytes2 = this.ctrEngine.processBytes(this.data.getBuffer(), 0, size, bArr, i2);
            iDoFinal = iProcessBytes2 + this.ctrEngine.doFinal(bArr, i2 + iProcessBytes2);
            calculateMac(bArr, i2, size);
        }
        int i3 = i2 + iDoFinal;
        byte[] bArr2 = this.macBlock;
        if (bArr2 == null) {
            throw new IllegalStateException("mac is not calculated");
        }
        if (this.forEncryption) {
            System.arraycopy(bArr2, 0, bArr, i3, this.macSize);
            reset();
            return iDoFinal + this.macSize;
        }
        byte[] bArr3 = new byte[this.macSize];
        System.arraycopy(this.data.getBuffer(), iDoFinal, bArr3, 0, this.macSize);
        int i4 = this.macSize;
        byte[] bArr4 = new byte[i4];
        System.arraycopy(this.macBlock, 0, bArr4, 0, i4);
        if (!Arrays.constantTimeAreEqual(bArr3, bArr4)) {
            throw new InvalidCipherTextException("mac verification failed");
        }
        reset();
        return iDoFinal;
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public String getAlgorithmName() {
        return this.engine.getAlgorithmName() + "/KGCM";
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public byte[] getMac() {
        int i2 = this.macSize;
        byte[] bArr = new byte[i2];
        System.arraycopy(this.macBlock, 0, bArr, 0, i2);
        return bArr;
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public int getOutputSize(int i2) {
        return this.forEncryption ? i2 : i2 + this.macSize;
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public BlockCipher getUnderlyingCipher() {
        return this.engine;
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public int getUpdateOutputSize(int i2) {
        return i2;
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public void init(boolean z2, CipherParameters cipherParameters) throws IOException {
        KeyParameter key;
        this.forEncryption = z2;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            byte[] nonce = aEADParameters.getNonce();
            byte[] bArr = this.iv;
            int length = bArr.length - nonce.length;
            Arrays.fill(bArr, (byte) 0);
            System.arraycopy(nonce, 0, this.iv, length, nonce.length);
            this.initialAssociatedText = aEADParameters.getAssociatedText();
            int macSize = aEADParameters.getMacSize();
            if (macSize < 64 || macSize > this.engine.getBlockSize() * 8 || macSize % 8 != 0) {
                throw new IllegalArgumentException("Invalid value for MAC size: " + macSize);
            }
            this.macSize = macSize / 8;
            key = aEADParameters.getKey();
            byte[] bArr2 = this.initialAssociatedText;
            if (bArr2 != null) {
                processAADBytes(bArr2, 0, bArr2.length);
            }
        } else {
            if (!(cipherParameters instanceof ParametersWithIV)) {
                throw new IllegalArgumentException("Invalid parameter passed");
            }
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            byte[] iv = parametersWithIV.getIV();
            byte[] bArr3 = this.iv;
            int length2 = bArr3.length - iv.length;
            Arrays.fill(bArr3, (byte) 0);
            System.arraycopy(iv, 0, this.iv, length2, iv.length);
            this.initialAssociatedText = null;
            this.macSize = this.engine.getBlockSize();
            key = (KeyParameter) parametersWithIV.getParameters();
        }
        this.macBlock = new byte[this.engine.getBlockSize()];
        this.ctrEngine.init(true, new ParametersWithIV(key, this.iv));
        this.engine.init(true, key);
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public void processAADByte(byte b2) throws IOException {
        this.associatedText.write(b2);
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public void processAADBytes(byte[] bArr, int i2, int i3) throws IOException {
        this.associatedText.write(bArr, i2, i3);
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public int processByte(byte b2, byte[] bArr, int i2) throws IOException {
        this.data.write(b2);
        return 0;
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) throws IOException {
        if (bArr.length < i2 + i3) {
            throw new DataLengthException("input buffer too short");
        }
        this.data.write(bArr, i2, i3);
        return 0;
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public void reset() throws IOException {
        this.f7371H = new byte[this.engine.getBlockSize()];
        this.f7372b = new byte[this.engine.getBlockSize()];
        this.temp = new byte[this.engine.getBlockSize()];
        this.lambda_c = 0;
        this.lambda_o = 0;
        this.engine.reset();
        this.data.reset();
        this.associatedText.reset();
        byte[] bArr = this.initialAssociatedText;
        if (bArr != null) {
            processAADBytes(bArr, 0, bArr.length);
        }
    }
}

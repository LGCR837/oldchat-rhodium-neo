package org.spongycastle.crypto.modes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.AEADParameters;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class KCCMBlockCipher implements AEADBlockCipher {
    private static final int BITS_IN_BYTE = 8;
    private static final int BYTES_IN_INT = 4;
    private static final int MAX_MAC_BIT_LENGTH = 512;
    private static final int MIN_MAC_BIT_LENGTH = 64;
    private byte[] G1;
    private int Nb_;
    private ExposedByteArrayOutputStream associatedText;
    private byte[] buffer;
    private byte[] counter;
    private ExposedByteArrayOutputStream data;
    private BlockCipher engine;
    private boolean forEncryption;
    private byte[] initialAssociatedText;
    private byte[] mac;
    private byte[] macBlock;
    private int macSize;
    private byte[] nonce;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private byte[] f7370s;

    public class ExposedByteArrayOutputStream extends ByteArrayOutputStream {
        public ExposedByteArrayOutputStream() {
        }

        public byte[] getBuffer() {
            return ((ByteArrayOutputStream) this).buf;
        }
    }

    public KCCMBlockCipher(BlockCipher blockCipher) {
        this(blockCipher, 4);
    }

    private void CalculateMac(byte[] bArr, int i2, int i3) {
        while (i3 > 0) {
            for (int i4 = 0; i4 < this.engine.getBlockSize(); i4++) {
                byte[] bArr2 = this.macBlock;
                bArr2[i4] = (byte) (bArr2[i4] ^ bArr[i2 + i4]);
            }
            BlockCipher blockCipher = this.engine;
            byte[] bArr3 = this.macBlock;
            blockCipher.processBlock(bArr3, 0, bArr3, 0);
            i3 -= this.engine.getBlockSize();
            i2 += this.engine.getBlockSize();
        }
    }

    private void ProcessBlock(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        int i5 = 0;
        while (true) {
            byte[] bArr3 = this.counter;
            if (i5 >= bArr3.length) {
                break;
            }
            byte[] bArr4 = this.f7370s;
            bArr4[i5] = (byte) (bArr4[i5] + bArr3[i5]);
            i5++;
        }
        this.engine.processBlock(this.f7370s, 0, this.buffer, 0);
        for (int i6 = 0; i6 < this.engine.getBlockSize(); i6++) {
            bArr2[i4 + i6] = (byte) (this.buffer[i6] ^ bArr[i2 + i6]);
        }
    }

    private byte getFlag(boolean z2, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        if (z2) {
            stringBuffer.append("1");
        } else {
            stringBuffer.append("0");
        }
        if (i2 == 8) {
            stringBuffer.append("010");
        } else if (i2 == 16) {
            stringBuffer.append("011");
        } else if (i2 == 32) {
            stringBuffer.append("100");
        } else if (i2 == 48) {
            stringBuffer.append("101");
        } else if (i2 == 64) {
            stringBuffer.append("110");
        }
        String binaryString = Integer.toBinaryString(this.Nb_ - 1);
        while (binaryString.length() < 4) {
            binaryString = new StringBuffer(binaryString).insert(0, "0").toString();
        }
        stringBuffer.append(binaryString);
        return (byte) Integer.parseInt(stringBuffer.toString(), 2);
    }

    private void intToBytes(int i2, byte[] bArr, int i3) {
        bArr[i3 + 3] = (byte) (i2 >> 24);
        bArr[i3 + 2] = (byte) (i2 >> 16);
        bArr[i3 + 1] = (byte) (i2 >> 8);
        bArr[i3] = (byte) i2;
    }

    private void processAAD(byte[] bArr, int i2, int i3, int i4) {
        if (i3 - i2 < this.engine.getBlockSize()) {
            throw new IllegalArgumentException("authText buffer too short");
        }
        if (i3 % this.engine.getBlockSize() != 0) {
            throw new IllegalArgumentException("padding not supported");
        }
        byte[] bArr2 = this.nonce;
        System.arraycopy(bArr2, 0, this.G1, 0, (bArr2.length - this.Nb_) - 1);
        intToBytes(i4, this.buffer, 0);
        System.arraycopy(this.buffer, 0, this.G1, (this.nonce.length - this.Nb_) - 1, 4);
        byte[] bArr3 = this.G1;
        bArr3[bArr3.length - 1] = getFlag(true, this.macSize);
        this.engine.processBlock(this.G1, 0, this.macBlock, 0);
        intToBytes(i3, this.buffer, 0);
        if (i3 <= this.engine.getBlockSize() - this.Nb_) {
            for (int i5 = 0; i5 < i3; i5++) {
                byte[] bArr4 = this.buffer;
                int i6 = this.Nb_ + i5;
                bArr4[i6] = (byte) (bArr4[i6] ^ bArr[i2 + i5]);
            }
            for (int i7 = 0; i7 < this.engine.getBlockSize(); i7++) {
                byte[] bArr5 = this.macBlock;
                bArr5[i7] = (byte) (bArr5[i7] ^ this.buffer[i7]);
            }
            BlockCipher blockCipher = this.engine;
            byte[] bArr6 = this.macBlock;
            blockCipher.processBlock(bArr6, 0, bArr6, 0);
            return;
        }
        for (int i8 = 0; i8 < this.engine.getBlockSize(); i8++) {
            byte[] bArr7 = this.macBlock;
            bArr7[i8] = (byte) (bArr7[i8] ^ this.buffer[i8]);
        }
        BlockCipher blockCipher2 = this.engine;
        byte[] bArr8 = this.macBlock;
        blockCipher2.processBlock(bArr8, 0, bArr8, 0);
        while (i3 != 0) {
            for (int i9 = 0; i9 < this.engine.getBlockSize(); i9++) {
                byte[] bArr9 = this.macBlock;
                bArr9[i9] = (byte) (bArr9[i9] ^ bArr[i9 + i2]);
            }
            BlockCipher blockCipher3 = this.engine;
            byte[] bArr10 = this.macBlock;
            blockCipher3.processBlock(bArr10, 0, bArr10, 0);
            i2 += this.engine.getBlockSize();
            i3 -= this.engine.getBlockSize();
        }
    }

    private void setNb(int i2) {
        if (i2 != 4 && i2 != 6 && i2 != 8) {
            throw new IllegalArgumentException("Nb = 4 is recommended by DSTU7624 but can be changed to only 6 or 8 in this implementation");
        }
        this.Nb_ = i2;
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public int doFinal(byte[] bArr, int i2) throws InvalidCipherTextException, IOException {
        int iProcessPacket = processPacket(this.data.getBuffer(), 0, this.data.size(), bArr, i2);
        reset();
        return iProcessPacket;
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public String getAlgorithmName() {
        return this.engine.getAlgorithmName() + "/KCCM";
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public byte[] getMac() {
        return Arrays.clone(this.mac);
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public int getOutputSize(int i2) {
        return i2 + this.macSize;
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
        CipherParameters parameters;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            if (aEADParameters.getMacSize() > 512 || aEADParameters.getMacSize() < 64 || aEADParameters.getMacSize() % 8 != 0) {
                throw new IllegalArgumentException("Invalid mac size specified");
            }
            this.nonce = aEADParameters.getNonce();
            this.macSize = aEADParameters.getMacSize() / 8;
            this.initialAssociatedText = aEADParameters.getAssociatedText();
            parameters = aEADParameters.getKey();
        } else {
            if (!(cipherParameters instanceof ParametersWithIV)) {
                throw new IllegalArgumentException("Invalid parameters specified");
            }
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.nonce = parametersWithIV.getIV();
            this.macSize = this.engine.getBlockSize();
            this.initialAssociatedText = null;
            parameters = parametersWithIV.getParameters();
        }
        this.mac = new byte[this.macSize];
        this.forEncryption = z2;
        this.engine.init(true, parameters);
        this.counter[0] = 1;
        byte[] bArr = this.initialAssociatedText;
        if (bArr != null) {
            processAADBytes(bArr, 0, bArr.length);
        }
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

    public int processPacket(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) throws InvalidCipherTextException, IOException {
        int i5;
        if (bArr.length - i2 < i3) {
            throw new DataLengthException("input buffer too short");
        }
        if (bArr2.length - i4 < i3) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.associatedText.size() > 0) {
            if (this.forEncryption) {
                processAAD(this.associatedText.getBuffer(), 0, this.associatedText.size(), this.data.size());
            } else {
                processAAD(this.associatedText.getBuffer(), 0, this.associatedText.size(), this.data.size() - this.macSize);
            }
        }
        if (!this.forEncryption) {
            if ((i3 - this.macSize) % this.engine.getBlockSize() != 0) {
                throw new DataLengthException("partial blocks not supported");
            }
            this.engine.processBlock(this.nonce, 0, this.f7370s, 0);
            int blockSize = i3 / this.engine.getBlockSize();
            for (int i6 = 0; i6 < blockSize; i6++) {
                ProcessBlock(bArr, i2, i3, bArr2, i4);
                i2 += this.engine.getBlockSize();
                i4 += this.engine.getBlockSize();
            }
            if (i3 > i2) {
                int i7 = 0;
                while (true) {
                    byte[] bArr3 = this.counter;
                    if (i7 >= bArr3.length) {
                        break;
                    }
                    byte[] bArr4 = this.f7370s;
                    bArr4[i7] = (byte) (bArr4[i7] + bArr3[i7]);
                    i7++;
                }
                this.engine.processBlock(this.f7370s, 0, this.buffer, 0);
                int i8 = 0;
                while (true) {
                    i5 = this.macSize;
                    if (i8 >= i5) {
                        break;
                    }
                    bArr2[i4 + i8] = (byte) (this.buffer[i8] ^ bArr[i2 + i8]);
                    i8++;
                }
                i4 += i5;
            }
            int i9 = 0;
            while (true) {
                byte[] bArr5 = this.counter;
                if (i9 >= bArr5.length) {
                    break;
                }
                byte[] bArr6 = this.f7370s;
                bArr6[i9] = (byte) (bArr6[i9] + bArr5[i9]);
                i9++;
            }
            this.engine.processBlock(this.f7370s, 0, this.buffer, 0);
            int i10 = this.macSize;
            System.arraycopy(bArr2, i4 - i10, this.buffer, 0, i10);
            CalculateMac(bArr2, 0, i4 - this.macSize);
            System.arraycopy(this.macBlock, 0, this.mac, 0, this.macSize);
            int i11 = this.macSize;
            byte[] bArr7 = new byte[i11];
            System.arraycopy(this.buffer, 0, bArr7, 0, i11);
            if (!Arrays.constantTimeAreEqual(this.mac, bArr7)) {
                throw new InvalidCipherTextException("mac check failed");
            }
            reset();
            return i3 - this.macSize;
        }
        if (i3 % this.engine.getBlockSize() != 0) {
            throw new DataLengthException("partial blocks not supported");
        }
        CalculateMac(bArr, i2, i3);
        this.engine.processBlock(this.nonce, 0, this.f7370s, 0);
        int blockSize2 = i3;
        while (blockSize2 > 0) {
            ProcessBlock(bArr, i2, i3, bArr2, i4);
            blockSize2 -= this.engine.getBlockSize();
            i2 += this.engine.getBlockSize();
            i4 += this.engine.getBlockSize();
        }
        int i12 = 0;
        while (true) {
            byte[] bArr8 = this.counter;
            if (i12 >= bArr8.length) {
                break;
            }
            byte[] bArr9 = this.f7370s;
            bArr9[i12] = (byte) (bArr9[i12] + bArr8[i12]);
            i12++;
        }
        this.engine.processBlock(this.f7370s, 0, this.buffer, 0);
        int i13 = 0;
        while (true) {
            int i14 = this.macSize;
            if (i13 >= i14) {
                System.arraycopy(this.macBlock, 0, this.mac, 0, i14);
                reset();
                return i3 + this.macSize;
            }
            bArr2[i4 + i13] = (byte) (this.buffer[i13] ^ this.macBlock[i13]);
            i13++;
        }
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public void reset() throws IOException {
        Arrays.fill(this.G1, (byte) 0);
        Arrays.fill(this.buffer, (byte) 0);
        Arrays.fill(this.counter, (byte) 0);
        Arrays.fill(this.macBlock, (byte) 0);
        this.counter[0] = 1;
        this.data.reset();
        this.associatedText.reset();
        byte[] bArr = this.initialAssociatedText;
        if (bArr != null) {
            processAADBytes(bArr, 0, bArr.length);
        }
    }

    public KCCMBlockCipher(BlockCipher blockCipher, int i2) {
        this.associatedText = new ExposedByteArrayOutputStream();
        this.data = new ExposedByteArrayOutputStream();
        this.Nb_ = 4;
        this.engine = blockCipher;
        this.macSize = blockCipher.getBlockSize();
        this.nonce = new byte[blockCipher.getBlockSize()];
        this.initialAssociatedText = new byte[blockCipher.getBlockSize()];
        this.mac = new byte[blockCipher.getBlockSize()];
        this.macBlock = new byte[blockCipher.getBlockSize()];
        this.G1 = new byte[blockCipher.getBlockSize()];
        this.buffer = new byte[blockCipher.getBlockSize()];
        this.f7370s = new byte[blockCipher.getBlockSize()];
        this.counter = new byte[blockCipher.getBlockSize()];
        setNb(i2);
    }
}

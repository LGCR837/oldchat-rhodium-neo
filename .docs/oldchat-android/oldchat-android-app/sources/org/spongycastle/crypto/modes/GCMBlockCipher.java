package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.modes.gcm.GCMExponentiator;
import org.spongycastle.crypto.modes.gcm.GCMMultiplier;
import org.spongycastle.crypto.modes.gcm.GCMUtil;
import org.spongycastle.crypto.modes.gcm.Tables1kGCMExponentiator;
import org.spongycastle.crypto.modes.gcm.Tables8kGCMMultiplier;
import org.spongycastle.crypto.params.AEADParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public class GCMBlockCipher implements AEADBlockCipher {
    private static final int BLOCK_SIZE = 16;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    private byte[] f7368H;
    private byte[] J0;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    private byte[] f7369S;
    private byte[] S_at;
    private byte[] S_atPre;
    private byte[] atBlock;
    private int atBlockPos;
    private long atLength;
    private long atLengthPre;
    private int blocksRemaining;
    private byte[] bufBlock;
    private int bufOff;
    private BlockCipher cipher;
    private byte[] counter;
    private GCMExponentiator exp;
    private boolean forEncryption;
    private byte[] initialAssociatedText;
    private boolean initialised;
    private byte[] lastKey;
    private byte[] macBlock;
    private int macSize;
    private GCMMultiplier multiplier;
    private byte[] nonce;
    private long totalLength;

    public GCMBlockCipher(BlockCipher blockCipher) {
        this(blockCipher, null);
    }

    private void checkStatus() {
        if (this.initialised) {
            return;
        }
        if (!this.forEncryption) {
            throw new IllegalStateException("GCM cipher needs to be initialised");
        }
        throw new IllegalStateException("GCM cipher cannot be reused for encryption");
    }

    private void gCTRBlock(byte[] bArr, byte[] bArr2, int i2) {
        byte[] nextCounterBlock = getNextCounterBlock();
        GCMUtil.xor(nextCounterBlock, bArr);
        System.arraycopy(nextCounterBlock, 0, bArr2, i2, 16);
        byte[] bArr3 = this.f7369S;
        if (this.forEncryption) {
            bArr = nextCounterBlock;
        }
        gHASHBlock(bArr3, bArr);
        this.totalLength += 16;
    }

    private void gCTRPartial(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        byte[] nextCounterBlock = getNextCounterBlock();
        GCMUtil.xor(nextCounterBlock, bArr, i2, i3);
        System.arraycopy(nextCounterBlock, 0, bArr2, i4, i3);
        byte[] bArr3 = this.f7369S;
        if (this.forEncryption) {
            bArr = nextCounterBlock;
        }
        gHASHPartial(bArr3, bArr, 0, i3);
        this.totalLength += (long) i3;
    }

    private void gHASH(byte[] bArr, byte[] bArr2, int i2) {
        for (int i3 = 0; i3 < i2; i3 += 16) {
            gHASHPartial(bArr, bArr2, i3, Math.min(i2 - i3, 16));
        }
    }

    private void gHASHBlock(byte[] bArr, byte[] bArr2) {
        GCMUtil.xor(bArr, bArr2);
        this.multiplier.multiplyH(bArr);
    }

    private void gHASHPartial(byte[] bArr, byte[] bArr2, int i2, int i3) {
        GCMUtil.xor(bArr, bArr2, i2, i3);
        this.multiplier.multiplyH(bArr);
    }

    private byte[] getNextCounterBlock() {
        int i2 = this.blocksRemaining;
        if (i2 == 0) {
            throw new IllegalStateException("Attempt to process too many blocks");
        }
        this.blocksRemaining = i2 - 1;
        byte[] bArr = this.counter;
        int i3 = (bArr[15] & 255) + 1;
        bArr[15] = (byte) i3;
        int i4 = (i3 >>> 8) + (bArr[14] & 255);
        bArr[14] = (byte) i4;
        int i5 = (i4 >>> 8) + (bArr[13] & 255);
        bArr[13] = (byte) i5;
        bArr[12] = (byte) ((i5 >>> 8) + (bArr[12] & 255));
        byte[] bArr2 = new byte[16];
        this.cipher.processBlock(bArr, 0, bArr2, 0);
        return bArr2;
    }

    private void initCipher() {
        if (this.atLength > 0) {
            System.arraycopy(this.S_at, 0, this.S_atPre, 0, 16);
            this.atLengthPre = this.atLength;
        }
        int i2 = this.atBlockPos;
        if (i2 > 0) {
            gHASHPartial(this.S_atPre, this.atBlock, 0, i2);
            this.atLengthPre += (long) this.atBlockPos;
        }
        if (this.atLengthPre > 0) {
            System.arraycopy(this.S_atPre, 0, this.f7369S, 0, 16);
        }
    }

    private void outputBlock(byte[] bArr, int i2) {
        if (bArr.length < i2 + 16) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (this.totalLength == 0) {
            initCipher();
        }
        gCTRBlock(this.bufBlock, bArr, i2);
        if (this.forEncryption) {
            this.bufOff = 0;
            return;
        }
        byte[] bArr2 = this.bufBlock;
        System.arraycopy(bArr2, 16, bArr2, 0, this.macSize);
        this.bufOff = this.macSize;
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public int doFinal(byte[] bArr, int i2) throws InvalidCipherTextException {
        checkStatus();
        if (this.totalLength == 0) {
            initCipher();
        }
        int i3 = this.bufOff;
        if (!this.forEncryption) {
            int i4 = this.macSize;
            if (i3 < i4) {
                throw new InvalidCipherTextException("data too short");
            }
            i3 -= i4;
            if (bArr.length < i2 + i3) {
                throw new OutputLengthException("Output buffer too short");
            }
        } else if (bArr.length < i2 + i3 + this.macSize) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (i3 > 0) {
            gCTRPartial(this.bufBlock, 0, i3, bArr, i2);
        }
        long j2 = this.atLength;
        int i5 = this.atBlockPos;
        long j3 = j2 + ((long) i5);
        this.atLength = j3;
        if (j3 > this.atLengthPre) {
            if (i5 > 0) {
                gHASHPartial(this.S_at, this.atBlock, 0, i5);
            }
            if (this.atLengthPre > 0) {
                GCMUtil.xor(this.S_at, this.S_atPre);
            }
            long j4 = ((this.totalLength * 8) + 127) >>> 7;
            byte[] bArr2 = new byte[16];
            if (this.exp == null) {
                Tables1kGCMExponentiator tables1kGCMExponentiator = new Tables1kGCMExponentiator();
                this.exp = tables1kGCMExponentiator;
                tables1kGCMExponentiator.init(this.f7368H);
            }
            this.exp.exponentiateX(j4, bArr2);
            GCMUtil.multiply(this.S_at, bArr2);
            GCMUtil.xor(this.f7369S, this.S_at);
        }
        byte[] bArr3 = new byte[16];
        Pack.longToBigEndian(this.atLength * 8, bArr3, 0);
        Pack.longToBigEndian(this.totalLength * 8, bArr3, 8);
        gHASHBlock(this.f7369S, bArr3);
        byte[] bArr4 = new byte[16];
        this.cipher.processBlock(this.J0, 0, bArr4, 0);
        GCMUtil.xor(bArr4, this.f7369S);
        int i6 = this.macSize;
        byte[] bArr5 = new byte[i6];
        this.macBlock = bArr5;
        System.arraycopy(bArr4, 0, bArr5, 0, i6);
        if (this.forEncryption) {
            System.arraycopy(this.macBlock, 0, bArr, i2 + this.bufOff, this.macSize);
            i3 += this.macSize;
        } else {
            int i7 = this.macSize;
            byte[] bArr6 = new byte[i7];
            System.arraycopy(this.bufBlock, i3, bArr6, 0, i7);
            if (!Arrays.constantTimeAreEqual(this.macBlock, bArr6)) {
                throw new InvalidCipherTextException("mac check in GCM failed");
            }
        }
        reset(false);
        return i3;
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/GCM";
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public byte[] getMac() {
        byte[] bArr = this.macBlock;
        return bArr == null ? new byte[this.macSize] : Arrays.clone(bArr);
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public int getOutputSize(int i2) {
        int i3 = i2 + this.bufOff;
        if (this.forEncryption) {
            return i3 + this.macSize;
        }
        int i4 = this.macSize;
        if (i3 < i4) {
            return 0;
        }
        return i3 - i4;
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public int getUpdateOutputSize(int i2) {
        int i3 = i2 + this.bufOff;
        if (!this.forEncryption) {
            int i4 = this.macSize;
            if (i3 < i4) {
                return 0;
            }
            i3 -= i4;
        }
        return i3 - (i3 % 16);
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public void init(boolean z2, CipherParameters cipherParameters) {
        byte[] iv;
        KeyParameter key;
        byte[] bArr;
        this.forEncryption = z2;
        this.macBlock = null;
        this.initialised = true;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            iv = aEADParameters.getNonce();
            this.initialAssociatedText = aEADParameters.getAssociatedText();
            int macSize = aEADParameters.getMacSize();
            if (macSize < 32 || macSize > 128 || macSize % 8 != 0) {
                throw new IllegalArgumentException("Invalid value for MAC size: " + macSize);
            }
            this.macSize = macSize / 8;
            key = aEADParameters.getKey();
        } else {
            if (!(cipherParameters instanceof ParametersWithIV)) {
                throw new IllegalArgumentException("invalid parameters passed to GCM");
            }
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            iv = parametersWithIV.getIV();
            this.initialAssociatedText = null;
            this.macSize = 16;
            key = (KeyParameter) parametersWithIV.getParameters();
        }
        this.bufBlock = new byte[z2 ? 16 : this.macSize + 16];
        if (iv == null || iv.length < 1) {
            throw new IllegalArgumentException("IV must be at least 1 byte");
        }
        if (z2 && (bArr = this.nonce) != null && Arrays.areEqual(bArr, iv)) {
            if (key == null) {
                throw new IllegalArgumentException("cannot reuse nonce for GCM encryption");
            }
            byte[] bArr2 = this.lastKey;
            if (bArr2 != null && Arrays.areEqual(bArr2, key.getKey())) {
                throw new IllegalArgumentException("cannot reuse nonce for GCM encryption");
            }
        }
        this.nonce = iv;
        if (key != null) {
            this.lastKey = key.getKey();
        }
        if (key != null) {
            this.cipher.init(true, key);
            byte[] bArr3 = new byte[16];
            this.f7368H = bArr3;
            this.cipher.processBlock(bArr3, 0, bArr3, 0);
            this.multiplier.init(this.f7368H);
            this.exp = null;
        } else if (this.f7368H == null) {
            throw new IllegalArgumentException("Key must be specified in initial init");
        }
        byte[] bArr4 = new byte[16];
        this.J0 = bArr4;
        byte[] bArr5 = this.nonce;
        if (bArr5.length == 12) {
            System.arraycopy(bArr5, 0, bArr4, 0, bArr5.length);
            this.J0[15] = 1;
        } else {
            gHASH(bArr4, bArr5, bArr5.length);
            byte[] bArr6 = new byte[16];
            Pack.longToBigEndian(((long) this.nonce.length) * 8, bArr6, 8);
            gHASHBlock(this.J0, bArr6);
        }
        this.f7369S = new byte[16];
        this.S_at = new byte[16];
        this.S_atPre = new byte[16];
        this.atBlock = new byte[16];
        this.atBlockPos = 0;
        this.atLength = 0L;
        this.atLengthPre = 0L;
        this.counter = Arrays.clone(this.J0);
        this.blocksRemaining = -2;
        this.bufOff = 0;
        this.totalLength = 0L;
        byte[] bArr7 = this.initialAssociatedText;
        if (bArr7 != null) {
            processAADBytes(bArr7, 0, bArr7.length);
        }
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public void processAADByte(byte b2) {
        checkStatus();
        byte[] bArr = this.atBlock;
        int i2 = this.atBlockPos;
        bArr[i2] = b2;
        int i3 = i2 + 1;
        this.atBlockPos = i3;
        if (i3 == 16) {
            gHASHBlock(this.S_at, bArr);
            this.atBlockPos = 0;
            this.atLength += 16;
        }
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public void processAADBytes(byte[] bArr, int i2, int i3) {
        checkStatus();
        for (int i4 = 0; i4 < i3; i4++) {
            byte[] bArr2 = this.atBlock;
            int i5 = this.atBlockPos;
            bArr2[i5] = bArr[i2 + i4];
            int i6 = i5 + 1;
            this.atBlockPos = i6;
            if (i6 == 16) {
                gHASHBlock(this.S_at, bArr2);
                this.atBlockPos = 0;
                this.atLength += 16;
            }
        }
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public int processByte(byte b2, byte[] bArr, int i2) {
        checkStatus();
        byte[] bArr2 = this.bufBlock;
        int i3 = this.bufOff;
        bArr2[i3] = b2;
        int i4 = i3 + 1;
        this.bufOff = i4;
        if (i4 != bArr2.length) {
            return 0;
        }
        outputBlock(bArr, i2);
        return 16;
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        checkStatus();
        if (bArr.length < i2 + i3) {
            throw new DataLengthException("Input buffer too short");
        }
        int i5 = 0;
        for (int i6 = 0; i6 < i3; i6++) {
            byte[] bArr3 = this.bufBlock;
            int i7 = this.bufOff;
            bArr3[i7] = bArr[i2 + i6];
            int i8 = i7 + 1;
            this.bufOff = i8;
            if (i8 == bArr3.length) {
                outputBlock(bArr2, i4 + i5);
                i5 += 16;
            }
        }
        return i5;
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public void reset() {
        reset(true);
    }

    public GCMBlockCipher(BlockCipher blockCipher, GCMMultiplier gCMMultiplier) {
        if (blockCipher.getBlockSize() != 16) {
            throw new IllegalArgumentException("cipher required with a block size of 16.");
        }
        gCMMultiplier = gCMMultiplier == null ? new Tables8kGCMMultiplier() : gCMMultiplier;
        this.cipher = blockCipher;
        this.multiplier = gCMMultiplier;
    }

    private void reset(boolean z2) {
        this.cipher.reset();
        this.f7369S = new byte[16];
        this.S_at = new byte[16];
        this.S_atPre = new byte[16];
        this.atBlock = new byte[16];
        this.atBlockPos = 0;
        this.atLength = 0L;
        this.atLengthPre = 0L;
        this.counter = Arrays.clone(this.J0);
        this.blocksRemaining = -2;
        this.bufOff = 0;
        this.totalLength = 0L;
        byte[] bArr = this.bufBlock;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
        }
        if (z2) {
            this.macBlock = null;
        }
        if (this.forEncryption) {
            this.initialised = false;
            return;
        }
        byte[] bArr2 = this.initialAssociatedText;
        if (bArr2 != null) {
            processAADBytes(bArr2, 0, bArr2.length);
        }
    }
}

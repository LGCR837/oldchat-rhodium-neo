package org.spongycastle.crypto.engines;

import org.spongycastle.asn1.cmc.BodyPartID;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.MaxBytesExceededException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.SkippingStreamCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Pack;
import org.spongycastle.util.Strings;

/* JADX INFO: loaded from: classes.dex */
public class Salsa20Engine implements SkippingStreamCipher {
    public static final int DEFAULT_ROUNDS = 20;
    private static final int STATE_SIZE = 16;
    private static final int[] TAU_SIGMA = Pack.littleEndianToInt(Strings.toByteArray("expand 16-byte kexpand 32-byte k"), 0, 8);
    protected static final byte[] sigma = Strings.toByteArray("expand 32-byte k");
    protected static final byte[] tau = Strings.toByteArray("expand 16-byte k");
    private int cW0;
    private int cW1;
    private int cW2;
    protected int[] engineState;
    private int index;
    private boolean initialised;
    private byte[] keyStream;
    protected int rounds;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    protected int[] f7333x;

    public Salsa20Engine() {
        this(20);
    }

    private boolean limitExceeded() {
        int i2 = this.cW0 + 1;
        this.cW0 = i2;
        if (i2 == 0) {
            int i3 = this.cW1 + 1;
            this.cW1 = i3;
            if (i3 == 0) {
                int i4 = this.cW2 + 1;
                this.cW2 = i4;
                return (i4 & 32) != 0;
            }
        }
        return false;
    }

    private void resetLimitCounter() {
        this.cW0 = 0;
        this.cW1 = 0;
        this.cW2 = 0;
    }

    public static int rotl(int i2, int i3) {
        return (i2 >>> (-i3)) | (i2 << i3);
    }

    public static void salsaCore(int i2, int[] iArr, int[] iArr2) {
        if (iArr.length != 16) {
            throw new IllegalArgumentException();
        }
        if (iArr2.length != 16) {
            throw new IllegalArgumentException();
        }
        if (i2 % 2 != 0) {
            throw new IllegalArgumentException("Number of rounds must be even");
        }
        int i3 = iArr[0];
        int i4 = iArr[1];
        int i5 = iArr[2];
        int i6 = iArr[3];
        int i7 = iArr[4];
        int i8 = iArr[5];
        int i9 = iArr[6];
        int i10 = 7;
        int i11 = iArr[7];
        int i12 = iArr[8];
        int i13 = 9;
        int i14 = iArr[9];
        int i15 = iArr[10];
        int i16 = iArr[11];
        int i17 = iArr[12];
        int i18 = 13;
        int i19 = iArr[13];
        int i20 = iArr[14];
        int iRotl = iArr[15];
        int iRotl2 = i20;
        int iRotl3 = i19;
        int iRotl4 = i17;
        int iRotl5 = i16;
        int iRotl6 = i15;
        int iRotl7 = i14;
        int i21 = i12;
        int iRotl8 = i11;
        int iRotl9 = i9;
        int iRotl10 = i8;
        int i22 = i7;
        int i23 = i6;
        int iRotl11 = i5;
        int iRotl12 = i4;
        int i24 = i3;
        int i25 = i2;
        while (i25 > 0) {
            int iRotl13 = rotl(i24 + iRotl4, i10) ^ i22;
            int iRotl14 = i21 ^ rotl(iRotl13 + i24, i13);
            int iRotl15 = iRotl4 ^ rotl(iRotl14 + iRotl13, i18);
            int iRotl16 = rotl(iRotl15 + iRotl14, 18) ^ i24;
            int iRotl17 = iRotl7 ^ rotl(iRotl10 + iRotl12, i10);
            int iRotl18 = iRotl3 ^ rotl(iRotl17 + iRotl10, i13);
            int iRotl19 = iRotl12 ^ rotl(iRotl18 + iRotl17, i18);
            int iRotl20 = rotl(iRotl19 + iRotl18, 18) ^ iRotl10;
            int iRotl21 = iRotl2 ^ rotl(iRotl6 + iRotl9, 7);
            int iRotl22 = iRotl11 ^ rotl(iRotl21 + iRotl6, 9);
            int iRotl23 = iRotl9 ^ rotl(iRotl22 + iRotl21, 13);
            int iRotl24 = iRotl6 ^ rotl(iRotl23 + iRotl22, 18);
            int iRotl25 = i23 ^ rotl(iRotl + iRotl5, 7);
            int iRotl26 = iRotl8 ^ rotl(iRotl25 + iRotl, 9);
            int i26 = i25;
            int iRotl27 = iRotl5 ^ rotl(iRotl26 + iRotl25, 13);
            int iRotl28 = iRotl ^ rotl(iRotl27 + iRotl26, 18);
            iRotl12 = iRotl19 ^ rotl(iRotl16 + iRotl25, 7);
            iRotl11 = iRotl22 ^ rotl(iRotl12 + iRotl16, 9);
            int iRotl29 = iRotl25 ^ rotl(iRotl11 + iRotl12, 13);
            int iRotl30 = iRotl16 ^ rotl(iRotl29 + iRotl11, 18);
            iRotl9 = iRotl23 ^ rotl(iRotl20 + iRotl13, 7);
            iRotl8 = iRotl26 ^ rotl(iRotl9 + iRotl20, 9);
            int iRotl31 = rotl(iRotl8 + iRotl9, 13) ^ iRotl13;
            iRotl10 = iRotl20 ^ rotl(iRotl31 + iRotl8, 18);
            iRotl5 = iRotl27 ^ rotl(iRotl24 + iRotl17, 7);
            int iRotl32 = rotl(iRotl5 + iRotl24, 9) ^ iRotl14;
            iRotl7 = iRotl17 ^ rotl(iRotl32 + iRotl5, 13);
            iRotl6 = iRotl24 ^ rotl(iRotl7 + iRotl32, 18);
            iRotl4 = iRotl15 ^ rotl(iRotl28 + iRotl21, 7);
            iRotl3 = iRotl18 ^ rotl(iRotl4 + iRotl28, 9);
            iRotl2 = iRotl21 ^ rotl(iRotl3 + iRotl4, 13);
            iRotl = iRotl28 ^ rotl(iRotl2 + iRotl3, 18);
            i23 = iRotl29;
            i21 = iRotl32;
            i24 = iRotl30;
            i22 = iRotl31;
            i18 = 13;
            i13 = 9;
            i10 = 7;
            i25 = i26 - 2;
        }
        iArr2[0] = i24 + iArr[0];
        iArr2[1] = iRotl12 + iArr[1];
        iArr2[2] = iRotl11 + iArr[2];
        iArr2[3] = i23 + iArr[3];
        iArr2[4] = i22 + iArr[4];
        iArr2[5] = iRotl10 + iArr[5];
        iArr2[6] = iRotl9 + iArr[6];
        iArr2[7] = iRotl8 + iArr[7];
        iArr2[8] = i21 + iArr[8];
        iArr2[9] = iRotl7 + iArr[9];
        iArr2[10] = iRotl6 + iArr[10];
        iArr2[11] = iRotl5 + iArr[11];
        iArr2[12] = iRotl4 + iArr[12];
        iArr2[13] = iRotl3 + iArr[13];
        iArr2[14] = iRotl2 + iArr[14];
        iArr2[15] = iRotl + iArr[15];
    }

    public void advanceCounter(long j2) {
        int i2 = (int) (j2 >>> 32);
        int i3 = (int) j2;
        if (i2 > 0) {
            int[] iArr = this.engineState;
            iArr[9] = iArr[9] + i2;
        }
        int[] iArr2 = this.engineState;
        int i4 = iArr2[8];
        int i5 = i3 + i4;
        iArr2[8] = i5;
        if (i4 == 0 || i5 >= i4) {
            return;
        }
        iArr2[9] = iArr2[9] + 1;
    }

    public void generateKeyStream(byte[] bArr) {
        salsaCore(this.rounds, this.engineState, this.f7333x);
        Pack.intToLittleEndian(this.f7333x, bArr, 0);
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        if (this.rounds == 20) {
            return "Salsa20";
        }
        return "Salsa20/" + this.rounds;
    }

    public long getCounter() {
        int[] iArr = this.engineState;
        return (((long) iArr[9]) << 32) | (((long) iArr[8]) & BodyPartID.bodyIdMax);
    }

    public int getNonceSize() {
        return 8;
    }

    @Override // org.spongycastle.crypto.SkippingCipher
    public long getPosition() {
        return (getCounter() * 64) + ((long) this.index);
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public void init(boolean z2, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException(getAlgorithmName() + " Init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] iv = parametersWithIV.getIV();
        if (iv == null || iv.length != getNonceSize()) {
            throw new IllegalArgumentException(getAlgorithmName() + " requires exactly " + getNonceSize() + " bytes of IV");
        }
        CipherParameters parameters = parametersWithIV.getParameters();
        if (parameters == null) {
            if (!this.initialised) {
                throw new IllegalStateException(getAlgorithmName() + " KeyParameter can not be null for first initialisation");
            }
            setKey(null, iv);
        } else {
            if (!(parameters instanceof KeyParameter)) {
                throw new IllegalArgumentException(getAlgorithmName() + " Init parameters must contain a KeyParameter (or null for re-init)");
            }
            setKey(((KeyParameter) parameters).getKey(), iv);
        }
        reset();
        this.initialised = true;
    }

    public void packTauOrSigma(int i2, int[] iArr, int i3) {
        int i4 = (i2 - 16) / 4;
        int[] iArr2 = TAU_SIGMA;
        iArr[i3] = iArr2[i4];
        iArr[i3 + 1] = iArr2[i4 + 1];
        iArr[i3 + 2] = iArr2[i4 + 2];
        iArr[i3 + 3] = iArr2[i4 + 3];
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        if (!this.initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (i2 + i3 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i4 + i3 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (limitExceeded(i3)) {
            throw new MaxBytesExceededException("2^70 byte limit per IV would be exceeded; Change IV");
        }
        for (int i5 = 0; i5 < i3; i5++) {
            byte[] bArr3 = this.keyStream;
            int i6 = this.index;
            bArr2[i5 + i4] = (byte) (bArr3[i6] ^ bArr[i5 + i2]);
            int i7 = (i6 + 1) & 63;
            this.index = i7;
            if (i7 == 0) {
                advanceCounter();
                generateKeyStream(this.keyStream);
            }
        }
        return i3;
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public void reset() {
        this.index = 0;
        resetLimitCounter();
        resetCounter();
        generateKeyStream(this.keyStream);
    }

    public void resetCounter() {
        int[] iArr = this.engineState;
        iArr[9] = 0;
        iArr[8] = 0;
    }

    public void retreatCounter(long j2) {
        int i2 = (int) (j2 >>> 32);
        int i3 = (int) j2;
        if (i2 != 0) {
            int[] iArr = this.engineState;
            int i4 = iArr[9];
            if ((((long) i4) & BodyPartID.bodyIdMax) < (((long) i2) & BodyPartID.bodyIdMax)) {
                throw new IllegalStateException("attempt to reduce counter past zero.");
            }
            iArr[9] = i4 - i2;
        }
        int[] iArr2 = this.engineState;
        int i5 = iArr2[8];
        if ((((long) i5) & BodyPartID.bodyIdMax) >= (BodyPartID.bodyIdMax & ((long) i3))) {
            iArr2[8] = i5 - i3;
            return;
        }
        int i6 = iArr2[9];
        if (i6 == 0) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
        iArr2[9] = i6 - 1;
        iArr2[8] = i5 - i3;
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public byte returnByte(byte b2) {
        if (limitExceeded()) {
            throw new MaxBytesExceededException("2^70 byte limit per IV; Change IV");
        }
        byte[] bArr = this.keyStream;
        int i2 = this.index;
        byte b3 = (byte) (b2 ^ bArr[i2]);
        int i3 = (i2 + 1) & 63;
        this.index = i3;
        if (i3 == 0) {
            advanceCounter();
            generateKeyStream(this.keyStream);
        }
        return b3;
    }

    @Override // org.spongycastle.crypto.SkippingCipher
    public long seekTo(long j2) {
        reset();
        return skip(j2);
    }

    public void setKey(byte[] bArr, byte[] bArr2) {
        if (bArr != null) {
            if (bArr.length != 16 && bArr.length != 32) {
                throw new IllegalArgumentException(getAlgorithmName() + " requires 128 bit or 256 bit key");
            }
            int length = (bArr.length - 16) / 4;
            int[] iArr = this.engineState;
            int[] iArr2 = TAU_SIGMA;
            iArr[0] = iArr2[length];
            iArr[5] = iArr2[length + 1];
            iArr[10] = iArr2[length + 2];
            iArr[15] = iArr2[length + 3];
            Pack.littleEndianToInt(bArr, 0, iArr, 1, 4);
            Pack.littleEndianToInt(bArr, bArr.length - 16, this.engineState, 11, 4);
        }
        Pack.littleEndianToInt(bArr2, 0, this.engineState, 6, 2);
    }

    @Override // org.spongycastle.crypto.SkippingCipher
    public long skip(long j2) {
        long j3;
        if (j2 >= 0) {
            if (j2 >= 64) {
                long j4 = j2 / 64;
                advanceCounter(j4);
                j3 = j2 - (j4 * 64);
            } else {
                j3 = j2;
            }
            int i2 = this.index;
            int i3 = (((int) j3) + i2) & 63;
            this.index = i3;
            if (i3 < i2) {
                advanceCounter();
            }
        } else {
            long j5 = -j2;
            if (j5 >= 64) {
                long j6 = j5 / 64;
                retreatCounter(j6);
                j5 -= j6 * 64;
            }
            for (long j7 = 0; j7 < j5; j7++) {
                if (this.index == 0) {
                    retreatCounter();
                }
                this.index = (this.index - 1) & 63;
            }
        }
        generateKeyStream(this.keyStream);
        return j2;
    }

    public Salsa20Engine(int i2) {
        this.index = 0;
        this.engineState = new int[16];
        this.f7333x = new int[16];
        this.keyStream = new byte[64];
        this.initialised = false;
        if (i2 <= 0 || (i2 & 1) != 0) {
            throw new IllegalArgumentException("'rounds' must be a positive, even number");
        }
        this.rounds = i2;
    }

    private boolean limitExceeded(int i2) {
        int i3 = this.cW0 + i2;
        this.cW0 = i3;
        if (i3 >= i2 || i3 < 0) {
            return false;
        }
        int i4 = this.cW1 + 1;
        this.cW1 = i4;
        if (i4 != 0) {
            return false;
        }
        int i5 = this.cW2 + 1;
        this.cW2 = i5;
        return (i5 & 32) != 0;
    }

    public void advanceCounter() {
        int[] iArr = this.engineState;
        int i2 = iArr[8] + 1;
        iArr[8] = i2;
        if (i2 == 0) {
            iArr[9] = iArr[9] + 1;
        }
    }

    public void retreatCounter() {
        int[] iArr = this.engineState;
        int i2 = iArr[8];
        if (i2 == 0 && iArr[9] == 0) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
        int i3 = i2 - 1;
        iArr[8] = i3;
        if (i3 == -1) {
            iArr[9] = iArr[9] - 1;
        }
    }
}

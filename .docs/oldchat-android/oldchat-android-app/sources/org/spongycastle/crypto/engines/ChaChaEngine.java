package org.spongycastle.crypto.engines;

import org.spongycastle.asn1.cmc.BodyPartID;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public class ChaChaEngine extends Salsa20Engine {
    public ChaChaEngine() {
    }

    public static void chachaCore(int i2, int[] iArr, int[] iArr2) {
        int i3 = 16;
        if (iArr.length != 16) {
            throw new IllegalArgumentException();
        }
        if (iArr2.length != 16) {
            throw new IllegalArgumentException();
        }
        if (i2 % 2 != 0) {
            throw new IllegalArgumentException("Number of rounds must be even");
        }
        char c2 = 0;
        int i4 = iArr[0];
        int i5 = iArr[1];
        int i6 = iArr[2];
        int i7 = iArr[3];
        int i8 = iArr[4];
        int i9 = iArr[5];
        int i10 = iArr[6];
        int i11 = 7;
        int i12 = iArr[7];
        int i13 = 8;
        int i14 = iArr[8];
        int i15 = iArr[9];
        int i16 = iArr[10];
        int i17 = iArr[11];
        int i18 = 12;
        int i19 = iArr[12];
        int i20 = iArr[13];
        int i21 = iArr[14];
        int iRotl = iArr[15];
        int iRotl2 = i21;
        int iRotl3 = i20;
        int iRotl4 = i19;
        int i22 = i17;
        int i23 = i16;
        int i24 = i15;
        int i25 = i14;
        int iRotl5 = i12;
        int iRotl6 = i10;
        int iRotl7 = i9;
        int iRotl8 = i8;
        int i26 = i7;
        int i27 = i6;
        int i28 = i5;
        int i29 = i4;
        int i30 = i2;
        while (i30 > 0) {
            int i31 = i29 + iRotl8;
            int iRotl9 = Salsa20Engine.rotl(iRotl4 ^ i31, i3);
            int i32 = i25 + iRotl9;
            int iRotl10 = Salsa20Engine.rotl(iRotl8 ^ i32, i18);
            int i33 = i31 + iRotl10;
            int iRotl11 = Salsa20Engine.rotl(iRotl9 ^ i33, i13);
            int i34 = i32 + iRotl11;
            int iRotl12 = Salsa20Engine.rotl(iRotl10 ^ i34, i11);
            int i35 = i28 + iRotl7;
            int iRotl13 = Salsa20Engine.rotl(iRotl3 ^ i35, i3);
            int i36 = i24 + iRotl13;
            int iRotl14 = Salsa20Engine.rotl(iRotl7 ^ i36, i18);
            int i37 = i35 + iRotl14;
            int iRotl15 = Salsa20Engine.rotl(iRotl13 ^ i37, i13);
            int i38 = i36 + iRotl15;
            int iRotl16 = Salsa20Engine.rotl(iRotl14 ^ i38, i11);
            int i39 = i27 + iRotl6;
            int iRotl17 = Salsa20Engine.rotl(iRotl2 ^ i39, i3);
            int i40 = i23 + iRotl17;
            int iRotl18 = Salsa20Engine.rotl(iRotl6 ^ i40, i18);
            int i41 = i39 + iRotl18;
            int iRotl19 = Salsa20Engine.rotl(iRotl17 ^ i41, i13);
            int i42 = i40 + iRotl19;
            int iRotl20 = Salsa20Engine.rotl(iRotl18 ^ i42, i11);
            int i43 = i26 + iRotl5;
            int iRotl21 = Salsa20Engine.rotl(iRotl ^ i43, 16);
            int i44 = i22 + iRotl21;
            int iRotl22 = Salsa20Engine.rotl(iRotl5 ^ i44, i18);
            int i45 = i43 + iRotl22;
            int iRotl23 = Salsa20Engine.rotl(iRotl21 ^ i45, 8);
            int i46 = i44 + iRotl23;
            int iRotl24 = Salsa20Engine.rotl(iRotl22 ^ i46, 7);
            int i47 = i33 + iRotl16;
            int iRotl25 = Salsa20Engine.rotl(iRotl23 ^ i47, 16);
            int i48 = i42 + iRotl25;
            int iRotl26 = Salsa20Engine.rotl(iRotl16 ^ i48, 12);
            i29 = i47 + iRotl26;
            iRotl = Salsa20Engine.rotl(iRotl25 ^ i29, 8);
            i23 = i48 + iRotl;
            iRotl7 = Salsa20Engine.rotl(iRotl26 ^ i23, 7);
            int i49 = i37 + iRotl20;
            int iRotl27 = Salsa20Engine.rotl(iRotl11 ^ i49, 16);
            int i50 = i46 + iRotl27;
            int iRotl28 = Salsa20Engine.rotl(iRotl20 ^ i50, 12);
            i28 = i49 + iRotl28;
            iRotl4 = Salsa20Engine.rotl(iRotl27 ^ i28, 8);
            i22 = i50 + iRotl4;
            iRotl6 = Salsa20Engine.rotl(iRotl28 ^ i22, 7);
            int i51 = i41 + iRotl24;
            int iRotl29 = Salsa20Engine.rotl(iRotl15 ^ i51, 16);
            int i52 = i34 + iRotl29;
            int iRotl30 = Salsa20Engine.rotl(iRotl24 ^ i52, 12);
            i27 = i51 + iRotl30;
            iRotl3 = Salsa20Engine.rotl(iRotl29 ^ i27, 8);
            i25 = i52 + iRotl3;
            iRotl5 = Salsa20Engine.rotl(iRotl30 ^ i25, 7);
            int i53 = i45 + iRotl12;
            i3 = 16;
            int iRotl31 = Salsa20Engine.rotl(iRotl19 ^ i53, 16);
            int i54 = i38 + iRotl31;
            int iRotl32 = Salsa20Engine.rotl(iRotl12 ^ i54, 12);
            i26 = i53 + iRotl32;
            iRotl2 = Salsa20Engine.rotl(iRotl31 ^ i26, 8);
            i24 = i54 + iRotl2;
            iRotl8 = Salsa20Engine.rotl(iRotl32 ^ i24, 7);
            i30 -= 2;
            c2 = 0;
            i18 = 12;
            i13 = 8;
            i11 = 7;
        }
        iArr2[c2] = i29 + iArr[c2];
        iArr2[1] = i28 + iArr[1];
        iArr2[2] = i27 + iArr[2];
        iArr2[3] = i26 + iArr[3];
        iArr2[4] = iRotl8 + iArr[4];
        iArr2[5] = iRotl7 + iArr[5];
        iArr2[6] = iRotl6 + iArr[6];
        iArr2[7] = iRotl5 + iArr[7];
        iArr2[8] = i25 + iArr[8];
        iArr2[9] = i24 + iArr[9];
        iArr2[10] = i23 + iArr[10];
        iArr2[11] = i22 + iArr[11];
        iArr2[12] = iRotl4 + iArr[12];
        iArr2[13] = iRotl3 + iArr[13];
        iArr2[14] = iRotl2 + iArr[14];
        iArr2[15] = iRotl + iArr[15];
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public void advanceCounter(long j2) {
        int i2 = (int) (j2 >>> 32);
        int i3 = (int) j2;
        if (i2 > 0) {
            int[] iArr = this.engineState;
            iArr[13] = iArr[13] + i2;
        }
        int[] iArr2 = this.engineState;
        int i4 = iArr2[12];
        int i5 = i3 + i4;
        iArr2[12] = i5;
        if (i4 == 0 || i5 >= i4) {
            return;
        }
        iArr2[13] = iArr2[13] + 1;
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public void generateKeyStream(byte[] bArr) {
        chachaCore(this.rounds, this.engineState, this.f7333x);
        Pack.intToLittleEndian(this.f7333x, bArr, 0);
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine, org.spongycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "ChaCha" + this.rounds;
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public long getCounter() {
        int[] iArr = this.engineState;
        return (((long) iArr[13]) << 32) | (((long) iArr[12]) & BodyPartID.bodyIdMax);
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public void resetCounter() {
        int[] iArr = this.engineState;
        iArr[13] = 0;
        iArr[12] = 0;
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public void retreatCounter(long j2) {
        int i2 = (int) (j2 >>> 32);
        int i3 = (int) j2;
        if (i2 != 0) {
            int[] iArr = this.engineState;
            int i4 = iArr[13];
            if ((((long) i4) & BodyPartID.bodyIdMax) < (((long) i2) & BodyPartID.bodyIdMax)) {
                throw new IllegalStateException("attempt to reduce counter past zero.");
            }
            iArr[13] = i4 - i2;
        }
        int[] iArr2 = this.engineState;
        int i5 = iArr2[12];
        if ((((long) i5) & BodyPartID.bodyIdMax) >= (BodyPartID.bodyIdMax & ((long) i3))) {
            iArr2[12] = i5 - i3;
            return;
        }
        int i6 = iArr2[13];
        if (i6 == 0) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
        iArr2[13] = i6 - 1;
        iArr2[12] = i5 - i3;
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public void setKey(byte[] bArr, byte[] bArr2) {
        if (bArr != null) {
            if (bArr.length != 16 && bArr.length != 32) {
                throw new IllegalArgumentException(getAlgorithmName() + " requires 128 bit or 256 bit key");
            }
            packTauOrSigma(bArr.length, this.engineState, 0);
            Pack.littleEndianToInt(bArr, 0, this.engineState, 4, 4);
            Pack.littleEndianToInt(bArr, bArr.length - 16, this.engineState, 8, 4);
        }
        Pack.littleEndianToInt(bArr2, 0, this.engineState, 14, 2);
    }

    public ChaChaEngine(int i2) {
        super(i2);
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public void advanceCounter() {
        int[] iArr = this.engineState;
        int i2 = iArr[12] + 1;
        iArr[12] = i2;
        if (i2 == 0) {
            iArr[13] = iArr[13] + 1;
        }
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public void retreatCounter() {
        int[] iArr = this.engineState;
        int i2 = iArr[12];
        if (i2 == 0 && iArr[13] == 0) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
        int i3 = i2 - 1;
        iArr[12] = i3;
        if (i3 == -1) {
            iArr[13] = iArr[13] - 1;
        }
    }
}

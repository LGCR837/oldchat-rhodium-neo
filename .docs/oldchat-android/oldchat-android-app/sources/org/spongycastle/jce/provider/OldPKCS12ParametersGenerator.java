package org.spongycastle.jce.provider;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.PBEParametersGenerator;
import org.spongycastle.crypto.digests.MD5Digest;
import org.spongycastle.crypto.digests.RIPEMD160Digest;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

/* JADX INFO: loaded from: classes.dex */
class OldPKCS12ParametersGenerator extends PBEParametersGenerator {
    public static final int IV_MATERIAL = 2;
    public static final int KEY_MATERIAL = 1;
    public static final int MAC_MATERIAL = 3;
    private Digest digest;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private int f7471u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private int f7472v;

    public OldPKCS12ParametersGenerator(Digest digest) {
        this.digest = digest;
        if (digest instanceof MD5Digest) {
            this.f7471u = 16;
            this.f7472v = 64;
            return;
        }
        if (digest instanceof SHA1Digest) {
            this.f7471u = 20;
            this.f7472v = 64;
        } else if (digest instanceof RIPEMD160Digest) {
            this.f7471u = 20;
            this.f7472v = 64;
        } else {
            throw new IllegalArgumentException("Digest " + digest.getAlgorithmName() + " unsupported");
        }
    }

    private void adjust(byte[] bArr, int i2, byte[] bArr2) {
        int i3 = (bArr2[bArr2.length - 1] & 255) + (bArr[(bArr2.length + i2) - 1] & 255) + 1;
        bArr[(bArr2.length + i2) - 1] = (byte) i3;
        int i4 = i3 >>> 8;
        for (int length = bArr2.length - 2; length >= 0; length--) {
            int i5 = i2 + length;
            int i6 = i4 + (bArr2[length] & 255) + (bArr[i5] & 255);
            bArr[i5] = (byte) i6;
            i4 = i6 >>> 8;
        }
    }

    private byte[] generateDerivedKey(int i2, int i3) {
        byte[] bArr;
        byte[] bArr2;
        int i4 = this.f7472v;
        byte[] bArr3 = new byte[i4];
        byte[] bArr4 = new byte[i3];
        int i5 = 0;
        for (int i6 = 0; i6 != i4; i6++) {
            bArr3[i6] = (byte) i2;
        }
        byte[] bArr5 = this.salt;
        if (bArr5 == null || bArr5.length == 0) {
            bArr = new byte[0];
        } else {
            int i7 = this.f7472v;
            int length = i7 * (((bArr5.length + i7) - 1) / i7);
            bArr = new byte[length];
            for (int i8 = 0; i8 != length; i8++) {
                byte[] bArr6 = this.salt;
                bArr[i8] = bArr6[i8 % bArr6.length];
            }
        }
        byte[] bArr7 = this.password;
        if (bArr7 == null || bArr7.length == 0) {
            bArr2 = new byte[0];
        } else {
            int i9 = this.f7472v;
            int length2 = i9 * (((bArr7.length + i9) - 1) / i9);
            bArr2 = new byte[length2];
            for (int i10 = 0; i10 != length2; i10++) {
                byte[] bArr8 = this.password;
                bArr2[i10] = bArr8[i10 % bArr8.length];
            }
        }
        int length3 = bArr.length + bArr2.length;
        byte[] bArr9 = new byte[length3];
        System.arraycopy(bArr, 0, bArr9, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr9, bArr.length, bArr2.length);
        int i11 = this.f7472v;
        byte[] bArr10 = new byte[i11];
        int i12 = this.f7471u;
        int i13 = ((i3 + i12) - 1) / i12;
        int i14 = 1;
        while (i14 <= i13) {
            int i15 = this.f7471u;
            byte[] bArr11 = new byte[i15];
            this.digest.update(bArr3, i5, i4);
            this.digest.update(bArr9, i5, length3);
            this.digest.doFinal(bArr11, i5);
            for (int i16 = 1; i16 != this.iterationCount; i16++) {
                this.digest.update(bArr11, i5, i15);
                this.digest.doFinal(bArr11, i5);
            }
            for (int i17 = 0; i17 != i11; i17++) {
                bArr10[i14] = bArr11[i17 % i15];
            }
            int i18 = 0;
            while (true) {
                int i19 = this.f7472v;
                if (i18 == length3 / i19) {
                    break;
                }
                adjust(bArr9, i19 * i18, bArr10);
                i18++;
            }
            if (i14 == i13) {
                int i20 = i14 - 1;
                int i21 = this.f7471u;
                System.arraycopy(bArr11, 0, bArr4, i20 * i21, i3 - (i20 * i21));
            } else {
                System.arraycopy(bArr11, 0, bArr4, (i14 - 1) * this.f7471u, i15);
            }
            i14++;
            i5 = 0;
        }
        return bArr4;
    }

    @Override // org.spongycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedMacParameters(int i2) {
        int i3 = i2 / 8;
        return new KeyParameter(generateDerivedKey(3, i3), 0, i3);
    }

    @Override // org.spongycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedParameters(int i2) {
        int i3 = i2 / 8;
        return new KeyParameter(generateDerivedKey(1, i3), 0, i3);
    }

    @Override // org.spongycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedParameters(int i2, int i3) {
        int i4 = i2 / 8;
        int i5 = i3 / 8;
        byte[] bArrGenerateDerivedKey = generateDerivedKey(1, i4);
        return new ParametersWithIV(new KeyParameter(bArrGenerateDerivedKey, 0, i4), generateDerivedKey(2, i5), 0, i5);
    }
}

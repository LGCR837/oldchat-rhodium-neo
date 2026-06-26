package L;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Cloneable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f346a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f347b;

    public a() {
        this.f347b = 0;
        this.f346a = new int[1];
    }

    public static int[] n(int i2) {
        return new int[(i2 + 31) / 32];
    }

    public void a(boolean z2) {
        f(this.f347b + 1);
        if (z2) {
            int[] iArr = this.f346a;
            int i2 = this.f347b;
            int i3 = i2 / 32;
            iArr[i3] = (1 << (i2 & 31)) | iArr[i3];
        }
        this.f347b++;
    }

    public void b(a aVar) {
        int i2 = aVar.f347b;
        f(this.f347b + i2);
        for (int i3 = 0; i3 < i2; i3++) {
            a(aVar.g(i3));
        }
    }

    public void c(int i2, int i3) {
        if (i3 < 0 || i3 > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        f(this.f347b + i3);
        while (i3 > 0) {
            boolean z2 = true;
            if (((i2 >> (i3 - 1)) & 1) != 1) {
                z2 = false;
            }
            a(z2);
            i3--;
        }
    }

    public void d() {
        int length = this.f346a.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.f346a[i2] = 0;
        }
    }

    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public a clone() {
        return new a((int[]) this.f346a.clone(), this.f347b);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f347b == aVar.f347b && Arrays.equals(this.f346a, aVar.f346a);
    }

    public final void f(int i2) {
        if (i2 > (this.f346a.length << 5)) {
            int[] iArrN = n(i2);
            int[] iArr = this.f346a;
            System.arraycopy(iArr, 0, iArrN, 0, iArr.length);
            this.f346a = iArrN;
        }
    }

    public boolean g(int i2) {
        return ((1 << (i2 & 31)) & this.f346a[i2 / 32]) != 0;
    }

    public int[] h() {
        return this.f346a;
    }

    public int hashCode() {
        return (this.f347b * 31) + Arrays.hashCode(this.f346a);
    }

    public int i(int i2) {
        int i3 = this.f347b;
        if (i2 >= i3) {
            return i3;
        }
        int i4 = i2 / 32;
        int i5 = (((1 << (i2 & 31)) - 1) ^ (-1)) & this.f346a[i4];
        while (i5 == 0) {
            i4++;
            int[] iArr = this.f346a;
            if (i4 == iArr.length) {
                return this.f347b;
            }
            i5 = iArr[i4];
        }
        int iNumberOfTrailingZeros = (i4 << 5) + Integer.numberOfTrailingZeros(i5);
        int i6 = this.f347b;
        return iNumberOfTrailingZeros > i6 ? i6 : iNumberOfTrailingZeros;
    }

    public int j(int i2) {
        int i3 = this.f347b;
        if (i2 >= i3) {
            return i3;
        }
        int i4 = i2 / 32;
        int i5 = (((1 << (i2 & 31)) - 1) ^ (-1)) & (this.f346a[i4] ^ (-1));
        while (i5 == 0) {
            i4++;
            int[] iArr = this.f346a;
            if (i4 == iArr.length) {
                return this.f347b;
            }
            i5 = iArr[i4] ^ (-1);
        }
        int iNumberOfTrailingZeros = (i4 << 5) + Integer.numberOfTrailingZeros(i5);
        int i6 = this.f347b;
        return iNumberOfTrailingZeros > i6 ? i6 : iNumberOfTrailingZeros;
    }

    public int k() {
        return this.f347b;
    }

    public int l() {
        return (this.f347b + 7) / 8;
    }

    public boolean m(int i2, int i3, boolean z2) {
        if (i3 < i2 || i2 < 0 || i3 > this.f347b) {
            throw new IllegalArgumentException();
        }
        if (i3 == i2) {
            return true;
        }
        int i4 = i3 - 1;
        int i5 = i2 / 32;
        int i6 = i4 / 32;
        int i7 = i5;
        while (i7 <= i6) {
            int i8 = (2 << (i7 >= i6 ? 31 & i4 : 31)) - (1 << (i7 > i5 ? 0 : i2 & 31));
            int i9 = this.f346a[i7] & i8;
            if (!z2) {
                i8 = 0;
            }
            if (i9 != i8) {
                return false;
            }
            i7++;
        }
        return true;
    }

    public void o() {
        int[] iArr = new int[this.f346a.length];
        int i2 = (this.f347b - 1) / 32;
        int i3 = i2 + 1;
        for (int i4 = 0; i4 < i3; i4++) {
            long j2 = this.f346a[i4];
            long j3 = ((j2 & 1431655765) << 1) | ((j2 >> 1) & 1431655765);
            long j4 = ((j3 & 858993459) << 2) | ((j3 >> 2) & 858993459);
            long j5 = ((j4 & 252645135) << 4) | ((j4 >> 4) & 252645135);
            long j6 = ((j5 & 16711935) << 8) | ((j5 >> 8) & 16711935);
            iArr[i2 - i4] = (int) (((j6 & 65535) << 16) | ((j6 >> 16) & 65535));
        }
        int i5 = this.f347b;
        int i6 = i3 << 5;
        if (i5 != i6) {
            int i7 = i6 - i5;
            int i8 = iArr[0] >>> i7;
            for (int i9 = 1; i9 < i3; i9++) {
                int i10 = iArr[i9];
                iArr[i9 - 1] = i8 | (i10 << (32 - i7));
                i8 = i10 >>> i7;
            }
            iArr[i2] = i8;
        }
        this.f346a = iArr;
    }

    public void p(int i2) {
        int[] iArr = this.f346a;
        int i3 = i2 / 32;
        iArr[i3] = (1 << (i2 & 31)) | iArr[i3];
    }

    public void q(int i2, int i3) {
        this.f346a[i2 / 32] = i3;
    }

    public void r(int i2, byte[] bArr, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = 0;
            for (int i7 = 0; i7 < 8; i7++) {
                if (g(i2)) {
                    i6 |= 1 << (7 - i7);
                }
                i2++;
            }
            bArr[i3 + i5] = (byte) i6;
        }
    }

    public void s(a aVar) {
        if (this.f347b != aVar.f347b) {
            throw new IllegalArgumentException("Sizes don't match");
        }
        int i2 = 0;
        while (true) {
            int[] iArr = this.f346a;
            if (i2 >= iArr.length) {
                return;
            }
            iArr[i2] = iArr[i2] ^ aVar.f346a[i2];
            i2++;
        }
    }

    public String toString() {
        int i2 = this.f347b;
        StringBuilder sb = new StringBuilder(i2 + (i2 / 8) + 1);
        for (int i3 = 0; i3 < this.f347b; i3++) {
            if ((i3 & 7) == 0) {
                sb.append(' ');
            }
            sb.append(g(i3) ? 'X' : '.');
        }
        return sb.toString();
    }

    public a(int i2) {
        this.f347b = i2;
        this.f346a = n(i2);
    }

    public a(int[] iArr, int i2) {
        this.f346a = iArr;
        this.f347b = i2;
    }
}

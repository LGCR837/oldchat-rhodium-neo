package L;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f352a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f353b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f354c;

    public c(byte[] bArr) {
        this.f352a = bArr;
    }

    public int a() {
        return ((this.f352a.length - this.f353b) * 8) - this.f354c;
    }

    public int b() {
        return this.f354c;
    }

    public int c() {
        return this.f353b;
    }

    public int d(int i2) {
        if (i2 <= 0 || i2 > 32 || i2 > a()) {
            throw new IllegalArgumentException(String.valueOf(i2));
        }
        int i3 = this.f354c;
        int i4 = 0;
        if (i3 > 0) {
            int i5 = 8 - i3;
            int i6 = i2 < i5 ? i2 : i5;
            int i7 = i5 - i6;
            byte[] bArr = this.f352a;
            int i8 = this.f353b;
            int i9 = (((255 >> (8 - i6)) << i7) & bArr[i8]) >> i7;
            i2 -= i6;
            int i10 = i3 + i6;
            this.f354c = i10;
            if (i10 == 8) {
                this.f354c = 0;
                this.f353b = i8 + 1;
            }
            i4 = i9;
        }
        if (i2 <= 0) {
            return i4;
        }
        while (i2 >= 8) {
            int i11 = i4 << 8;
            byte[] bArr2 = this.f352a;
            int i12 = this.f353b;
            i4 = (bArr2[i12] & 255) | i11;
            this.f353b = i12 + 1;
            i2 -= 8;
        }
        if (i2 <= 0) {
            return i4;
        }
        int i13 = 8 - i2;
        int i14 = (i4 << i2) | ((((255 >> i13) << i13) & this.f352a[this.f353b]) >> i13);
        this.f354c += i2;
        return i14;
    }
}

package H;

/* JADX INFO: loaded from: classes.dex */
public final class k extends h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final byte[] f292c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f293d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f294e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f295f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f296g;

    public k(int i2, int i3, int[] iArr) {
        super(i2, i3);
        this.f293d = i2;
        this.f294e = i3;
        this.f295f = 0;
        this.f296g = 0;
        int i4 = i2 * i3;
        this.f292c = new byte[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = iArr[i5];
            this.f292c[i5] = (byte) (((((i6 >> 16) & 255) + ((i6 >> 7) & 510)) + (i6 & 255)) / 4);
        }
    }

    @Override // H.h
    public byte[] b() {
        int iD = d();
        int iA = a();
        int i2 = this.f293d;
        if (iD == i2 && iA == this.f294e) {
            return this.f292c;
        }
        int i3 = iD * iA;
        byte[] bArr = new byte[i3];
        int i4 = (this.f296g * i2) + this.f295f;
        if (iD == i2) {
            System.arraycopy(this.f292c, i4, bArr, 0, i3);
            return bArr;
        }
        for (int i5 = 0; i5 < iA; i5++) {
            System.arraycopy(this.f292c, i4, bArr, i5 * iD, iD);
            i4 += this.f293d;
        }
        return bArr;
    }

    @Override // H.h
    public byte[] c(int i2, byte[] bArr) {
        if (i2 < 0 || i2 >= a()) {
            throw new IllegalArgumentException("Requested row is outside the image: ".concat(String.valueOf(i2)));
        }
        int iD = d();
        if (bArr == null || bArr.length < iD) {
            bArr = new byte[iD];
        }
        System.arraycopy(this.f292c, ((i2 + this.f296g) * this.f293d) + this.f295f, bArr, 0, iD);
        return bArr;
    }
}

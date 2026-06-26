package N;

import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final a f422h = new a(4201, PKIFailureInfo.certConfirmed, 1);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final a f423i = new a(1033, 1024, 1);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final a f424j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final a f425k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final a f426l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final a f427m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final a f428n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final a f429o;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f430a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f431b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b f432c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b f433d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f434e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f435f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f436g;

    static {
        a aVar = new a(67, 64, 1);
        f424j = aVar;
        f425k = new a(19, 16, 1);
        f426l = new a(285, 256, 0);
        a aVar2 = new a(301, 256, 1);
        f427m = aVar2;
        f428n = aVar2;
        f429o = aVar;
    }

    public a(int i2, int i3, int i4) {
        this.f435f = i2;
        this.f434e = i3;
        this.f436g = i4;
        this.f430a = new int[i3];
        this.f431b = new int[i3];
        int i5 = 1;
        for (int i6 = 0; i6 < i3; i6++) {
            this.f430a[i6] = i5;
            i5 <<= 1;
            if (i5 >= i3) {
                i5 = (i5 ^ i2) & (i3 - 1);
            }
        }
        for (int i7 = 0; i7 < i3 - 1; i7++) {
            this.f431b[this.f430a[i7]] = i7;
        }
        this.f432c = new b(this, new int[]{0});
        this.f433d = new b(this, new int[]{1});
    }

    public static int a(int i2, int i3) {
        return i2 ^ i3;
    }

    public b b(int i2, int i3) {
        if (i2 < 0) {
            throw new IllegalArgumentException();
        }
        if (i3 == 0) {
            return this.f432c;
        }
        int[] iArr = new int[i2 + 1];
        iArr[0] = i3;
        return new b(this, iArr);
    }

    public int c(int i2) {
        return this.f430a[i2];
    }

    public int d() {
        return this.f436g;
    }

    public b e() {
        return this.f433d;
    }

    public int f() {
        return this.f434e;
    }

    public b g() {
        return this.f432c;
    }

    public int h(int i2) {
        if (i2 != 0) {
            return this.f430a[(this.f434e - this.f431b[i2]) - 1];
        }
        throw new ArithmeticException();
    }

    public int i(int i2) {
        if (i2 != 0) {
            return this.f431b[i2];
        }
        throw new IllegalArgumentException();
    }

    public int j(int i2, int i3) {
        if (i2 == 0 || i3 == 0) {
            return 0;
        }
        int[] iArr = this.f430a;
        int[] iArr2 = this.f431b;
        return iArr[(iArr2[i2] + iArr2[i3]) % (this.f434e - 1)];
    }

    public String toString() {
        return "GF(0x" + Integer.toHexString(this.f435f) + ',' + this.f434e + ')';
    }
}

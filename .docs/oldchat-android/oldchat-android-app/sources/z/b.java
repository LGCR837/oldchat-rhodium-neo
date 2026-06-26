package Z;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final b f657f = new b(929, 3);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f658a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f659b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c f660c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c f661d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f662e;

    public b(int i2, int i3) {
        this.f662e = i2;
        this.f658a = new int[i2];
        this.f659b = new int[i2];
        int i4 = 1;
        for (int i5 = 0; i5 < i2; i5++) {
            this.f658a[i5] = i4;
            i4 = (i4 * i3) % i2;
        }
        for (int i6 = 0; i6 < i2 - 1; i6++) {
            this.f659b[this.f658a[i6]] = i6;
        }
        this.f660c = new c(this, new int[]{0});
        this.f661d = new c(this, new int[]{1});
    }

    public int a(int i2, int i3) {
        return (i2 + i3) % this.f662e;
    }

    public c b(int i2, int i3) {
        if (i2 < 0) {
            throw new IllegalArgumentException();
        }
        if (i3 == 0) {
            return this.f660c;
        }
        int[] iArr = new int[i2 + 1];
        iArr[0] = i3;
        return new c(this, iArr);
    }

    public int c(int i2) {
        return this.f658a[i2];
    }

    public c d() {
        return this.f661d;
    }

    public int e() {
        return this.f662e;
    }

    public c f() {
        return this.f660c;
    }

    public int g(int i2) {
        if (i2 != 0) {
            return this.f658a[(this.f662e - this.f659b[i2]) - 1];
        }
        throw new ArithmeticException();
    }

    public int h(int i2) {
        if (i2 != 0) {
            return this.f659b[i2];
        }
        throw new IllegalArgumentException();
    }

    public int i(int i2, int i3) {
        if (i2 == 0 || i3 == 0) {
            return 0;
        }
        int[] iArr = this.f658a;
        int[] iArr2 = this.f659b;
        return iArr[(iArr2[i2] + iArr2[i3]) % (this.f662e - 1)];
    }

    public int j(int i2, int i3) {
        int i4 = this.f662e;
        return ((i2 + i4) - i3) % i4;
    }
}

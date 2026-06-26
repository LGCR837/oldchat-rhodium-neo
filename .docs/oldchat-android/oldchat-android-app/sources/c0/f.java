package c0;

/* JADX INFO: loaded from: classes.dex */
public enum f {
    L(1),
    M(0),
    Q(3),
    H(2);


    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final f[] f2098f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2100a;

    static {
        f fVar = L;
        f fVar2 = M;
        f fVar3 = Q;
        f2098f = new f[]{fVar2, fVar, H, fVar3};
    }

    f(int i2) {
        this.f2100a = i2;
    }

    public static f a(int i2) {
        if (i2 >= 0) {
            f[] fVarArr = f2098f;
            if (i2 < fVarArr.length) {
                return fVarArr[i2];
            }
        }
        throw new IllegalArgumentException();
    }

    public int b() {
        return this.f2100a;
    }
}

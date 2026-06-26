package T;

/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f522c = {1, 1, 2};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m f523a = new m();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final n f524b = new n();

    public H.n a(int i2, L.a aVar, int i3) {
        int[] iArrM = p.m(aVar, i3, false, f522c);
        try {
            return this.f524b.b(i2, aVar, iArrM);
        } catch (H.m unused) {
            return this.f523a.b(i2, aVar, iArrM);
        }
    }
}

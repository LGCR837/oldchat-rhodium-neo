package V;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f558a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final U.b f559b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final U.b f560c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final U.c f561d;

    public b(U.b bVar, U.b bVar2, U.c cVar, boolean z2) {
        this.f559b = bVar;
        this.f560c = bVar2;
        this.f561d = cVar;
        this.f558a = z2;
    }

    public static boolean a(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public static int e(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public U.c b() {
        return this.f561d;
    }

    public U.b c() {
        return this.f559b;
    }

    public U.b d() {
        return this.f560c;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return a(this.f559b, bVar.f559b) && a(this.f560c, bVar.f560c) && a(this.f561d, bVar.f561d);
    }

    public boolean f() {
        return this.f560c == null;
    }

    public int hashCode() {
        return (e(this.f559b) ^ e(this.f560c)) ^ e(this.f561d);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        sb.append(this.f559b);
        sb.append(" , ");
        sb.append(this.f560c);
        sb.append(" : ");
        U.c cVar = this.f561d;
        sb.append(cVar == null ? "null" : Integer.valueOf(cVar.c()));
        sb.append(" ]");
        return sb.toString();
    }
}

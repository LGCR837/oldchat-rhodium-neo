package U;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f542a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f543b;

    public b(int i2, int i3) {
        this.f542a = i2;
        this.f543b = i3;
    }

    public final int a() {
        return this.f543b;
    }

    public final int b() {
        return this.f542a;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f542a == bVar.f542a && this.f543b == bVar.f543b;
    }

    public final int hashCode() {
        return this.f542a ^ this.f543b;
    }

    public final String toString() {
        return this.f542a + "(" + this.f543b + ')';
    }
}

package F;

import B.b;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f197a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Type f198b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f199c;

    public a(Type type) {
        Type typeB = b.b((Type) B.a.b(type));
        this.f198b = typeB;
        this.f197a = b.k(typeB);
        this.f199c = typeB.hashCode();
    }

    public static a a(Class cls) {
        return new a(cls);
    }

    public static a b(Type type) {
        return new a(type);
    }

    public static a c(Type type, Type... typeArr) {
        return new a(b.o(null, type, typeArr));
    }

    public final Class d() {
        return this.f197a;
    }

    public final Type e() {
        return this.f198b;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof a) && b.f(this.f198b, ((a) obj).f198b);
    }

    public final int hashCode() {
        return this.f199c;
    }

    public final String toString() {
        return b.u(this.f198b);
    }
}

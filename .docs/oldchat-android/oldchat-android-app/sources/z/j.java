package z;

import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class j extends g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final B.h f8329a = new B.h();

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof j) && ((j) obj).f8329a.equals(this.f8329a));
    }

    public void h(String str, g gVar) {
        if (gVar == null) {
            gVar = i.f8328a;
        }
        this.f8329a.put(str, gVar);
    }

    public int hashCode() {
        return this.f8329a.hashCode();
    }

    public Set i() {
        return this.f8329a.entrySet();
    }
}

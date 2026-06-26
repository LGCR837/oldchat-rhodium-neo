package z;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class f extends g implements Iterable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f8327a = new ArrayList();

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof f) && ((f) obj).f8327a.equals(this.f8327a));
    }

    public void h(g gVar) {
        if (gVar == null) {
            gVar = i.f8328a;
        }
        this.f8327a.add(gVar);
    }

    public int hashCode() {
        return this.f8327a.hashCode();
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return this.f8327a.iterator();
    }
}

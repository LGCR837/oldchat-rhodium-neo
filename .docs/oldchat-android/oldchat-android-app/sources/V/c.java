package V;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f562a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f563b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f564c;

    public c(List list, int i2, boolean z2) {
        this.f562a = new ArrayList(list);
        this.f563b = i2;
        this.f564c = z2;
    }

    public List a() {
        return this.f562a;
    }

    public int b() {
        return this.f563b;
    }

    public boolean c(List list) {
        return this.f562a.equals(list);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return this.f562a.equals(cVar.a()) && this.f564c == cVar.f564c;
    }

    public int hashCode() {
        return this.f562a.hashCode() ^ Boolean.valueOf(this.f564c).hashCode();
    }

    public String toString() {
        return "{ " + this.f562a + " }";
    }
}

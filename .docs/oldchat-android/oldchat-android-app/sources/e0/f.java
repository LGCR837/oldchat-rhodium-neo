package e0;

import c0.h;
import c0.j;
import org.spongycastle.asn1.x509.DisplayText;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public h f4948a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c0.f f4949b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public j f4950c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4951d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b f4952e;

    public static boolean b(int i2) {
        return i2 >= 0 && i2 < 8;
    }

    public b a() {
        return this.f4952e;
    }

    public void c(c0.f fVar) {
        this.f4949b = fVar;
    }

    public void d(int i2) {
        this.f4951d = i2;
    }

    public void e(b bVar) {
        this.f4952e = bVar;
    }

    public void f(h hVar) {
        this.f4948a = hVar;
    }

    public void g(j jVar) {
        this.f4950c = jVar;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(DisplayText.DISPLAY_TEXT_MAXIMUM_SIZE);
        sb.append("<<\n");
        sb.append(" mode: ");
        sb.append(this.f4948a);
        sb.append("\n ecLevel: ");
        sb.append(this.f4949b);
        sb.append("\n version: ");
        sb.append(this.f4950c);
        sb.append("\n maskPattern: ");
        sb.append(this.f4951d);
        if (this.f4952e == null) {
            sb.append("\n matrix: null\n");
        } else {
            sb.append("\n matrix:\n");
            sb.append(this.f4952e);
        }
        sb.append(">>\n");
        return sb.toString();
    }
}

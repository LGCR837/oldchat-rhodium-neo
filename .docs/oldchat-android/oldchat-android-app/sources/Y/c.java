package Y;

import H.p;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final L.b f622a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final p f623b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final p f624c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final p f625d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final p f626e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f627f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f628g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f629h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f630i;

    public c(L.b bVar, p pVar, p pVar2, p pVar3, p pVar4) throws H.j {
        boolean z2 = pVar == null || pVar2 == null;
        boolean z3 = pVar3 == null || pVar4 == null;
        if (z2 && z3) {
            throw H.j.a();
        }
        if (z2) {
            pVar = new p(0.0f, pVar3.d());
            pVar2 = new p(0.0f, pVar4.d());
        } else if (z3) {
            pVar3 = new p(bVar.j() - 1, pVar.d());
            pVar4 = new p(bVar.j() - 1, pVar2.d());
        }
        this.f622a = bVar;
        this.f623b = pVar;
        this.f624c = pVar2;
        this.f625d = pVar3;
        this.f626e = pVar4;
        this.f627f = (int) Math.min(pVar.c(), pVar2.c());
        this.f628g = (int) Math.max(pVar3.c(), pVar4.c());
        this.f629h = (int) Math.min(pVar.d(), pVar3.d());
        this.f630i = (int) Math.max(pVar2.d(), pVar4.d());
    }

    public static c j(c cVar, c cVar2) {
        return cVar == null ? cVar2 : cVar2 == null ? cVar : new c(cVar.f622a, cVar.f623b, cVar.f624c, cVar2.f625d, cVar2.f626e);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public Y.c a(int r13, int r14, boolean r15) {
        /*
            r12 = this;
            H.p r0 = r12.f623b
            H.p r1 = r12.f624c
            H.p r2 = r12.f625d
            H.p r3 = r12.f626e
            if (r13 <= 0) goto L2a
            if (r15 == 0) goto Le
            r4 = r0
            goto Lf
        Le:
            r4 = r2
        Lf:
            float r5 = r4.d()
            int r5 = (int) r5
            int r5 = r5 - r13
            if (r5 >= 0) goto L18
            r5 = 0
        L18:
            H.p r13 = new H.p
            float r4 = r4.c()
            float r5 = (float) r5
            r13.<init>(r4, r5)
            if (r15 == 0) goto L27
            r8 = r13
        L25:
            r10 = r2
            goto L2c
        L27:
            r10 = r13
            r8 = r0
            goto L2c
        L2a:
            r8 = r0
            goto L25
        L2c:
            if (r14 <= 0) goto L5d
            if (r15 == 0) goto L33
            H.p r13 = r12.f624c
            goto L35
        L33:
            H.p r13 = r12.f626e
        L35:
            float r0 = r13.d()
            int r0 = (int) r0
            int r0 = r0 + r14
            L.b r14 = r12.f622a
            int r14 = r14.g()
            if (r0 < r14) goto L4b
            L.b r14 = r12.f622a
            int r14 = r14.g()
            int r0 = r14 + (-1)
        L4b:
            H.p r14 = new H.p
            float r13 = r13.c()
            float r0 = (float) r0
            r14.<init>(r13, r0)
            if (r15 == 0) goto L5a
            r9 = r14
        L58:
            r11 = r3
            goto L5f
        L5a:
            r11 = r14
            r9 = r1
            goto L5f
        L5d:
            r9 = r1
            goto L58
        L5f:
            Y.c r13 = new Y.c
            L.b r7 = r12.f622a
            r6 = r13
            r6.<init>(r7, r8, r9, r10, r11)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: Y.c.a(int, int, boolean):Y.c");
    }

    public p b() {
        return this.f624c;
    }

    public p c() {
        return this.f626e;
    }

    public int d() {
        return this.f628g;
    }

    public int e() {
        return this.f630i;
    }

    public int f() {
        return this.f627f;
    }

    public int g() {
        return this.f629h;
    }

    public p h() {
        return this.f623b;
    }

    public p i() {
        return this.f625d;
    }

    public c(c cVar) {
        this.f622a = cVar.f622a;
        this.f623b = cVar.h();
        this.f624c = cVar.b();
        this.f625d = cVar.i();
        this.f626e = cVar.c();
        this.f627f = cVar.f();
        this.f628g = cVar.d();
        this.f629h = cVar.g();
        this.f630i = cVar.e();
    }
}

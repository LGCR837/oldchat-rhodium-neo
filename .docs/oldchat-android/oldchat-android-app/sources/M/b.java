package M;

import H.j;
import H.p;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final L.b f415a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f416b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f417c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f418d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f419e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f420f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f421g;

    public b(L.b bVar) {
        this(bVar, 10, bVar.j() / 2, bVar.g() / 2);
    }

    public final p[] a(p pVar, p pVar2, p pVar3, p pVar4) {
        float fC = pVar.c();
        float fD = pVar.d();
        float fC2 = pVar2.c();
        float fD2 = pVar2.d();
        float fC3 = pVar3.c();
        float fD3 = pVar3.d();
        float fC4 = pVar4.c();
        float fD4 = pVar4.d();
        return fC < ((float) this.f417c) / 2.0f ? new p[]{new p(fC4 - 1.0f, fD4 + 1.0f), new p(fC2 + 1.0f, fD2 + 1.0f), new p(fC3 - 1.0f, fD3 - 1.0f), new p(fC + 1.0f, fD - 1.0f)} : new p[]{new p(fC4 + 1.0f, fD4 + 1.0f), new p(fC2 + 1.0f, fD2 - 1.0f), new p(fC3 - 1.0f, fD3 + 1.0f), new p(fC - 1.0f, fD - 1.0f)};
    }

    public final boolean b(int i2, int i3, int i4, boolean z2) {
        if (z2) {
            while (i2 <= i3) {
                if (this.f415a.d(i2, i4)) {
                    return true;
                }
                i2++;
            }
            return false;
        }
        while (i2 <= i3) {
            if (this.f415a.d(i4, i2)) {
                return true;
            }
            i2++;
        }
        return false;
    }

    public p[] c() throws j {
        int i2 = this.f418d;
        int i3 = this.f419e;
        int i4 = this.f421g;
        int i5 = this.f420f;
        boolean z2 = false;
        boolean z3 = true;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = false;
        while (z3) {
            boolean zB = true;
            boolean z9 = false;
            while (true) {
                if ((!zB && z4) || i3 >= this.f417c) {
                    break;
                }
                zB = b(i4, i5, i3, false);
                if (zB) {
                    i3++;
                    z4 = true;
                    z9 = true;
                } else if (!z4) {
                    i3++;
                }
            }
            if (i3 < this.f417c) {
                boolean zB2 = true;
                while (true) {
                    if ((!zB2 && z5) || i5 >= this.f416b) {
                        break;
                    }
                    zB2 = b(i2, i3, i5, true);
                    if (zB2) {
                        i5++;
                        z5 = true;
                        z9 = true;
                    } else if (!z5) {
                        i5++;
                    }
                }
                if (i5 < this.f416b) {
                    boolean zB3 = true;
                    while (true) {
                        if ((!zB3 && z6) || i2 < 0) {
                            break;
                        }
                        zB3 = b(i4, i5, i2, false);
                        if (zB3) {
                            i2--;
                            z6 = true;
                            z9 = true;
                        } else if (!z6) {
                            i2--;
                        }
                    }
                    if (i2 >= 0) {
                        z3 = z9;
                        boolean zB4 = true;
                        while (true) {
                            if ((!zB4 && z8) || i4 < 0) {
                                break;
                            }
                            zB4 = b(i2, i3, i4, true);
                            if (zB4) {
                                i4--;
                                z3 = true;
                                z8 = true;
                            } else if (!z8) {
                                i4--;
                            }
                        }
                        if (i4 >= 0) {
                            if (z3) {
                                z7 = true;
                            }
                        }
                    }
                }
            }
            z2 = true;
            break;
        }
        if (z2 || !z7) {
            throw j.a();
        }
        int i6 = i3 - i2;
        p pVarD = null;
        p pVarD2 = null;
        for (int i7 = 1; pVarD2 == null && i7 < i6; i7++) {
            pVarD2 = d(i2, i5 - i7, i2 + i7, i5);
        }
        if (pVarD2 == null) {
            throw j.a();
        }
        p pVarD3 = null;
        for (int i8 = 1; pVarD3 == null && i8 < i6; i8++) {
            pVarD3 = d(i2, i4 + i8, i2 + i8, i4);
        }
        if (pVarD3 == null) {
            throw j.a();
        }
        p pVarD4 = null;
        for (int i9 = 1; pVarD4 == null && i9 < i6; i9++) {
            pVarD4 = d(i3, i4 + i9, i3 - i9, i4);
        }
        if (pVarD4 == null) {
            throw j.a();
        }
        for (int i10 = 1; pVarD == null && i10 < i6; i10++) {
            pVarD = d(i3, i5 - i10, i3 - i10, i5);
        }
        if (pVarD != null) {
            return a(pVarD, pVarD2, pVarD4, pVarD3);
        }
        throw j.a();
    }

    public final p d(float f2, float f3, float f4, float f5) {
        int iC = a.c(a.a(f2, f3, f4, f5));
        float f6 = iC;
        float f7 = (f4 - f2) / f6;
        float f8 = (f5 - f3) / f6;
        for (int i2 = 0; i2 < iC; i2++) {
            float f9 = i2;
            int iC2 = a.c((f9 * f7) + f2);
            int iC3 = a.c((f9 * f8) + f3);
            if (this.f415a.d(iC2, iC3)) {
                return new p(iC2, iC3);
            }
        }
        return null;
    }

    public b(L.b bVar, int i2, int i3, int i4) throws j {
        this.f415a = bVar;
        int iG = bVar.g();
        this.f416b = iG;
        int iJ = bVar.j();
        this.f417c = iJ;
        int i5 = i2 / 2;
        int i6 = i3 - i5;
        this.f418d = i6;
        int i7 = i3 + i5;
        this.f419e = i7;
        int i8 = i4 - i5;
        this.f421g = i8;
        int i9 = i4 + i5;
        this.f420f = i9;
        if (i8 < 0 || i6 < 0 || i9 >= iG || i7 >= iJ) {
            throw j.a();
        }
    }
}

package Y;

import H.p;

/* JADX INFO: loaded from: classes.dex */
public final class j extends i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f653c;

    public j(c cVar, boolean z2) {
        super(cVar);
        this.f653c = z2;
    }

    public void g(a aVar) {
        d[] dVarArrD = d();
        m();
        l(dVarArrD, aVar);
        c cVarA = a();
        p pVarH = this.f653c ? cVarA.h() : cVarA.i();
        p pVarB = this.f653c ? cVarA.b() : cVarA.c();
        int iE = e((int) pVarH.d());
        int iE2 = e((int) pVarB.d());
        int iC = -1;
        int i2 = 0;
        int iMax = 1;
        while (iE < iE2) {
            d dVar = dVarArrD[iE];
            if (dVar != null) {
                int iC2 = dVar.c() - iC;
                if (iC2 == 0) {
                    i2++;
                } else {
                    if (iC2 == 1) {
                        iMax = Math.max(iMax, i2);
                        iC = dVar.c();
                    } else if (iC2 < 0 || dVar.c() >= aVar.c() || iC2 > iE) {
                        dVarArrD[iE] = null;
                    } else {
                        if (iMax > 2) {
                            iC2 *= iMax - 2;
                        }
                        boolean z2 = iC2 >= iE;
                        for (int i3 = 1; i3 <= iC2 && !z2; i3++) {
                            z2 = dVarArrD[iE - i3] != null;
                        }
                        if (z2) {
                            dVarArrD[iE] = null;
                        } else {
                            iC = dVar.c();
                        }
                    }
                    i2 = 1;
                }
            }
            iE++;
        }
    }

    public final void h(a aVar) {
        c cVarA = a();
        p pVarH = this.f653c ? cVarA.h() : cVarA.i();
        p pVarB = this.f653c ? cVarA.b() : cVarA.c();
        int iE = e((int) pVarB.d());
        d[] dVarArrD = d();
        int iC = -1;
        int i2 = 0;
        int iMax = 1;
        for (int iE2 = e((int) pVarH.d()); iE2 < iE; iE2++) {
            d dVar = dVarArrD[iE2];
            if (dVar != null) {
                dVar.j();
                int iC2 = dVar.c() - iC;
                if (iC2 == 0) {
                    i2++;
                } else {
                    if (iC2 == 1) {
                        iMax = Math.max(iMax, i2);
                        iC = dVar.c();
                    } else if (dVar.c() >= aVar.c()) {
                        dVarArrD[iE2] = null;
                    } else {
                        iC = dVar.c();
                    }
                    i2 = 1;
                }
            }
        }
    }

    public a i() {
        d[] dVarArrD = d();
        b bVar = new b();
        b bVar2 = new b();
        b bVar3 = new b();
        b bVar4 = new b();
        for (d dVar : dVarArrD) {
            if (dVar != null) {
                dVar.j();
                int iE = dVar.e() % 30;
                int iC = dVar.c();
                if (!this.f653c) {
                    iC += 2;
                }
                int i2 = iC % 3;
                if (i2 == 0) {
                    bVar2.b((iE * 3) + 1);
                } else if (i2 == 1) {
                    bVar4.b(iE / 3);
                    bVar3.b(iE % 3);
                } else if (i2 == 2) {
                    bVar.b(iE + 1);
                }
            }
        }
        if (bVar.a().length == 0 || bVar2.a().length == 0 || bVar3.a().length == 0 || bVar4.a().length == 0 || bVar.a()[0] <= 0 || bVar2.a()[0] + bVar3.a()[0] < 3 || bVar2.a()[0] + bVar3.a()[0] > 90) {
            return null;
        }
        a aVar = new a(bVar.a()[0], bVar2.a()[0], bVar3.a()[0], bVar4.a()[0]);
        l(dVarArrD, aVar);
        return aVar;
    }

    public int[] j() {
        int iC;
        a aVarI = i();
        if (aVarI == null) {
            return null;
        }
        h(aVarI);
        int iC2 = aVarI.c();
        int[] iArr = new int[iC2];
        for (d dVar : d()) {
            if (dVar != null && (iC = dVar.c()) < iC2) {
                iArr[iC] = iArr[iC] + 1;
            }
        }
        return iArr;
    }

    public boolean k() {
        return this.f653c;
    }

    public final void l(d[] dVarArr, a aVar) {
        for (int i2 = 0; i2 < dVarArr.length; i2++) {
            d dVar = dVarArr[i2];
            if (dVar != null) {
                int iE = dVar.e() % 30;
                int iC = dVar.c();
                if (iC > aVar.c()) {
                    dVarArr[i2] = null;
                } else {
                    if (!this.f653c) {
                        iC += 2;
                    }
                    int i3 = iC % 3;
                    if (i3 != 0) {
                        if (i3 != 1) {
                            if (i3 == 2 && iE + 1 != aVar.a()) {
                                dVarArr[i2] = null;
                            }
                        } else if (iE / 3 != aVar.b() || iE % 3 != aVar.d()) {
                            dVarArr[i2] = null;
                        }
                    } else if ((iE * 3) + 1 != aVar.e()) {
                        dVarArr[i2] = null;
                    }
                }
            }
        }
    }

    public final void m() {
        for (d dVar : d()) {
            if (dVar != null) {
                dVar.j();
            }
        }
    }

    @Override // Y.i
    public String toString() {
        return "IsLeft: " + this.f653c + '\n' + super.toString();
    }
}

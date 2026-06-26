package Y;

import java.lang.reflect.InvocationTargetException;
import java.util.Formatter;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f647a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i[] f648b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c f649c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f650d;

    public h(a aVar, c cVar) {
        this.f647a = aVar;
        int iA = aVar.a();
        this.f650d = iA;
        this.f649c = cVar;
        this.f648b = new i[iA + 2];
    }

    public static boolean b(d dVar, d dVar2) {
        if (dVar2 == null || !dVar2.g() || dVar2.a() != dVar.a()) {
            return false;
        }
        dVar.i(dVar2.c());
        return true;
    }

    public static int c(int i2, int i3, d dVar) {
        if (dVar == null || dVar.g()) {
            return i3;
        }
        if (!dVar.h(i2)) {
            return i3 + 1;
        }
        dVar.i(i2);
        return 0;
    }

    public final void a(i iVar) {
        if (iVar != null) {
            ((j) iVar).g(this.f647a);
        }
    }

    public final int d() {
        int iF = f();
        if (iF == 0) {
            return 0;
        }
        for (int i2 = 1; i2 < this.f650d + 1; i2++) {
            d[] dVarArrD = this.f648b[i2].d();
            for (int i3 = 0; i3 < dVarArrD.length; i3++) {
                d dVar = dVarArrD[i3];
                if (dVar != null && !dVar.g()) {
                    e(i2, i3, dVarArrD);
                }
            }
        }
        return iF;
    }

    public final void e(int i2, int i3, d[] dVarArr) {
        d dVar = dVarArr[i3];
        d[] dVarArrD = this.f648b[i2 - 1].d();
        i iVar = this.f648b[i2 + 1];
        d[] dVarArrD2 = iVar != null ? iVar.d() : dVarArrD;
        d[] dVarArr2 = new d[14];
        dVarArr2[2] = dVarArrD[i3];
        dVarArr2[3] = dVarArrD2[i3];
        if (i3 > 0) {
            int i4 = i3 - 1;
            dVarArr2[0] = dVarArr[i4];
            dVarArr2[4] = dVarArrD[i4];
            dVarArr2[5] = dVarArrD2[i4];
        }
        if (i3 > 1) {
            int i5 = i3 - 2;
            dVarArr2[8] = dVarArr[i5];
            dVarArr2[10] = dVarArrD[i5];
            dVarArr2[11] = dVarArrD2[i5];
        }
        if (i3 < dVarArr.length - 1) {
            int i6 = i3 + 1;
            dVarArr2[1] = dVarArr[i6];
            dVarArr2[6] = dVarArrD[i6];
            dVarArr2[7] = dVarArrD2[i6];
        }
        if (i3 < dVarArr.length - 2) {
            int i7 = i3 + 2;
            dVarArr2[9] = dVarArr[i7];
            dVarArr2[12] = dVarArrD[i7];
            dVarArr2[13] = dVarArrD2[i7];
        }
        for (int i8 = 0; i8 < 14 && !b(dVar, dVarArr2[i8]); i8++) {
        }
    }

    public final int f() {
        g();
        return h() + i();
    }

    public final void g() {
        i[] iVarArr = this.f648b;
        i iVar = iVarArr[0];
        if (iVar == null || iVarArr[this.f650d + 1] == null) {
            return;
        }
        d[] dVarArrD = iVar.d();
        d[] dVarArrD2 = this.f648b[this.f650d + 1].d();
        for (int i2 = 0; i2 < dVarArrD.length; i2++) {
            d dVar = dVarArrD[i2];
            if (dVar != null && dVarArrD2[i2] != null && dVar.c() == dVarArrD2[i2].c()) {
                for (int i3 = 1; i3 <= this.f650d; i3++) {
                    d dVar2 = this.f648b[i3].d()[i2];
                    if (dVar2 != null) {
                        dVar2.i(dVarArrD[i2].c());
                        if (!dVar2.g()) {
                            this.f648b[i3].d()[i2] = null;
                        }
                    }
                }
            }
        }
    }

    public final int h() {
        i iVar = this.f648b[0];
        if (iVar == null) {
            return 0;
        }
        d[] dVarArrD = iVar.d();
        int i2 = 0;
        for (int i3 = 0; i3 < dVarArrD.length; i3++) {
            d dVar = dVarArrD[i3];
            if (dVar != null) {
                int iC = dVar.c();
                int iC2 = 0;
                for (int i4 = 1; i4 < this.f650d + 1 && iC2 < 2; i4++) {
                    d dVar2 = this.f648b[i4].d()[i3];
                    if (dVar2 != null) {
                        iC2 = c(iC, iC2, dVar2);
                        if (!dVar2.g()) {
                            i2++;
                        }
                    }
                }
            }
        }
        return i2;
    }

    public final int i() {
        i[] iVarArr = this.f648b;
        int i2 = this.f650d;
        if (iVarArr[i2 + 1] == null) {
            return 0;
        }
        d[] dVarArrD = iVarArr[i2 + 1].d();
        int i3 = 0;
        for (int i4 = 0; i4 < dVarArrD.length; i4++) {
            d dVar = dVarArrD[i4];
            if (dVar != null) {
                int iC = dVar.c();
                int iC2 = 0;
                for (int i5 = this.f650d + 1; i5 > 0 && iC2 < 2; i5--) {
                    d dVar2 = this.f648b[i5].d()[i4];
                    if (dVar2 != null) {
                        iC2 = c(iC, iC2, dVar2);
                        if (!dVar2.g()) {
                            i3++;
                        }
                    }
                }
            }
        }
        return i3;
    }

    public int j() {
        return this.f650d;
    }

    public int k() {
        return this.f647a.b();
    }

    public int l() {
        return this.f647a.c();
    }

    public c m() {
        return this.f649c;
    }

    public i n(int i2) {
        return this.f648b[i2];
    }

    public i[] o() {
        a(this.f648b[0]);
        a(this.f648b[this.f650d + 1]);
        int i2 = 928;
        while (true) {
            int iD = d();
            if (iD <= 0 || iD >= i2) {
                break;
            }
            i2 = iD;
        }
        return this.f648b;
    }

    public void p(c cVar) {
        this.f649c = cVar;
    }

    public void q(int i2, i iVar) {
        this.f648b[i2] = iVar;
    }

    public String toString() throws IllegalAccessException, InvocationTargetException {
        i[] iVarArr = this.f648b;
        i iVar = iVarArr[0];
        if (iVar == null) {
            iVar = iVarArr[this.f650d + 1];
        }
        Formatter formatter = new Formatter();
        for (int i2 = 0; i2 < iVar.d().length; i2++) {
            try {
                formatter.format("CW %3d:", Integer.valueOf(i2));
                for (int i3 = 0; i3 < this.f650d + 2; i3++) {
                    i iVar2 = this.f648b[i3];
                    if (iVar2 == null) {
                        formatter.format("    |   ", new Object[0]);
                    } else {
                        d dVar = iVar2.d()[i2];
                        if (dVar == null) {
                            formatter.format("    |   ", new Object[0]);
                        } else {
                            formatter.format(" %3d|%3d", Integer.valueOf(dVar.c()), Integer.valueOf(dVar.e()));
                        }
                    }
                }
                formatter.format("%n", new Object[0]);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        formatter.close();
                    } catch (Throwable th3) {
                        Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th3);
                    }
                    throw th2;
                }
            }
        }
        String string = formatter.toString();
        formatter.close();
        return string;
    }
}

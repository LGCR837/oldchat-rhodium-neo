package Y;

import java.lang.reflect.InvocationTargetException;
import java.util.Formatter;

/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f651a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d[] f652b;

    public i(c cVar) {
        this.f651a = new c(cVar);
        this.f652b = new d[(cVar.e() - cVar.g()) + 1];
    }

    public final c a() {
        return this.f651a;
    }

    public final d b(int i2) {
        return this.f652b[e(i2)];
    }

    public final d c(int i2) {
        d dVar;
        d dVar2;
        d dVarB = b(i2);
        if (dVarB != null) {
            return dVarB;
        }
        for (int i3 = 1; i3 < 5; i3++) {
            int iE = e(i2) - i3;
            if (iE >= 0 && (dVar2 = this.f652b[iE]) != null) {
                return dVar2;
            }
            int iE2 = e(i2) + i3;
            d[] dVarArr = this.f652b;
            if (iE2 < dVarArr.length && (dVar = dVarArr[iE2]) != null) {
                return dVar;
            }
        }
        return null;
    }

    public final d[] d() {
        return this.f652b;
    }

    public final int e(int i2) {
        return i2 - this.f651a.g();
    }

    public final void f(int i2, d dVar) {
        this.f652b[e(i2)] = dVar;
    }

    public String toString() throws IllegalAccessException, InvocationTargetException {
        Formatter formatter = new Formatter();
        try {
            int i2 = 0;
            for (d dVar : this.f652b) {
                if (dVar == null) {
                    formatter.format("%3d:    |   %n", Integer.valueOf(i2));
                    i2++;
                } else {
                    formatter.format("%3d: %3d|%3d%n", Integer.valueOf(i2), Integer.valueOf(dVar.c()), Integer.valueOf(dVar.e()));
                    i2++;
                }
            }
            String string = formatter.toString();
            formatter.close();
            return string;
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
}

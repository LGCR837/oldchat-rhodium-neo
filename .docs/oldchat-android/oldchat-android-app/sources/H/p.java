package H;

/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f318a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f319b;

    public p(float f2, float f3) {
        this.f318a = f2;
        this.f319b = f3;
    }

    public static float a(p pVar, p pVar2, p pVar3) {
        float f2 = pVar2.f318a;
        float f3 = pVar2.f319b;
        return ((pVar3.f318a - f2) * (pVar.f319b - f3)) - ((pVar3.f319b - f3) * (pVar.f318a - f2));
    }

    public static float b(p pVar, p pVar2) {
        return M.a.a(pVar.f318a, pVar.f319b, pVar2.f318a, pVar2.f319b);
    }

    public static void e(p[] pVarArr) {
        p pVar;
        p pVar2;
        p pVar3;
        float fB = b(pVarArr[0], pVarArr[1]);
        float fB2 = b(pVarArr[1], pVarArr[2]);
        float fB3 = b(pVarArr[0], pVarArr[2]);
        if (fB2 >= fB && fB2 >= fB3) {
            pVar = pVarArr[0];
            pVar2 = pVarArr[1];
            pVar3 = pVarArr[2];
        } else if (fB3 < fB2 || fB3 < fB) {
            pVar = pVarArr[2];
            pVar2 = pVarArr[0];
            pVar3 = pVarArr[1];
        } else {
            pVar = pVarArr[1];
            pVar2 = pVarArr[0];
            pVar3 = pVarArr[2];
        }
        if (a(pVar2, pVar, pVar3) < 0.0f) {
            p pVar4 = pVar3;
            pVar3 = pVar2;
            pVar2 = pVar4;
        }
        pVarArr[0] = pVar2;
        pVarArr[1] = pVar;
        pVarArr[2] = pVar3;
    }

    public final float c() {
        return this.f318a;
    }

    public final float d() {
        return this.f319b;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof p) {
            p pVar = (p) obj;
            if (this.f318a == pVar.f318a && this.f319b == pVar.f319b) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (Float.floatToIntBits(this.f318a) * 31) + Float.floatToIntBits(this.f319b);
    }

    public final String toString() {
        return "(" + this.f318a + ',' + this.f319b + ')';
    }
}

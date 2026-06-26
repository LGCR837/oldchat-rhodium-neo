package c0;

import H.p;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f2117a;

    public i(boolean z2) {
        this.f2117a = z2;
    }

    public void a(p[] pVarArr) {
        if (!this.f2117a || pVarArr == null || pVarArr.length < 3) {
            return;
        }
        p pVar = pVarArr[0];
        pVarArr[0] = pVarArr[2];
        pVarArr[2] = pVar;
    }
}

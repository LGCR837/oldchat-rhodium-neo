package o0;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public abstract class Y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f7048a = {f0.k.f5245b, f0.k.f5250g, f0.k.f5246c, f0.k.f5252i, f0.k.f5254k, f0.k.f5257n, f0.k.f5248e};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f7049b = {f0.k.f5256m, f0.k.f5251h, f0.k.f5247d, f0.k.f5253j, f0.k.f5255l, f0.k.f5258o, f0.k.f5249f};

    public static int a(Context context) {
        if (context == null) {
            return f0.k.f5245b;
        }
        int iO = X.o(V.e(context)) + 1;
        if (iO < 0 || iO >= f7048a.length) {
            iO = 0;
        }
        return V.m(context) ? f7049b[iO] : f7048a[iO];
    }
}

package c;

import android.R;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import c.RunnableC0156o;
import i.AbstractC0250a;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import k.AbstractC0285k0;

/* JADX INFO: renamed from: c.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0109A extends AbstractC0175y implements k.H {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final Interpolator f1761A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public static final Interpolator f1762B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final Interpolator f1763C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public static final Interpolator f1764D;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static boolean f1765x = false;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final boolean f1766y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static Field f1767z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList f1768a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Runnable[] f1769b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1770c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArrayList f1771d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList f1772e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ArrayList f1773f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayList f1774g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ArrayList f1775h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList f1776i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ArrayList f1777j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ArrayList f1778k;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public AbstractC0173x f1780m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public AbstractC0169v f1781n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public AbstractComponentCallbacksC0165t f1782o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f1783p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f1784q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1785r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public String f1786s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f1787t;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1779l = 0;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Bundle f1788u = null;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public SparseArray f1789v = null;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public Runnable f1790w = new a();

    /* JADX INFO: renamed from: c.A$a */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C0109A.this.D();
        }
    }

    /* JADX INFO: renamed from: c.A$b */
    public class b extends c {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ AbstractComponentCallbacksC0165t f1792d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(View view, Animation animation, AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t) {
            super(view, animation);
            this.f1792d = abstractComponentCallbacksC0165t;
        }

        @Override // c.C0109A.c, android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            super.onAnimationEnd(animation);
            AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = this.f1792d;
            if (abstractComponentCallbacksC0165t.f2014b != null) {
                abstractComponentCallbacksC0165t.f2014b = null;
                C0109A.this.W(abstractComponentCallbacksC0165t, abstractComponentCallbacksC0165t.f2015c, 0, 0, false);
            }
        }
    }

    /* JADX INFO: renamed from: c.A$d */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final int[] f1798a = {R.attr.name, R.attr.id, R.attr.tag};
    }

    static {
        f1766y = Build.VERSION.SDK_INT >= 11;
        f1767z = null;
        f1761A = new DecelerateInterpolator(2.5f);
        f1762B = new DecelerateInterpolator(1.5f);
        f1763C = new AccelerateInterpolator(2.5f);
        f1764D = new AccelerateInterpolator(1.5f);
    }

    public static Animation P(Context context, float f2, float f3) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(f2, f3);
        alphaAnimation.setInterpolator(f1762B);
        alphaAnimation.setDuration(220L);
        return alphaAnimation;
    }

    public static Animation R(Context context, float f2, float f3, float f4, float f5) {
        AnimationSet animationSet = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(f2, f3, f2, f3, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(f1761A);
        scaleAnimation.setDuration(220L);
        animationSet.addAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(f4, f5);
        alphaAnimation.setInterpolator(f1762B);
        alphaAnimation.setDuration(220L);
        animationSet.addAnimation(alphaAnimation);
        return animationSet;
    }

    public static boolean S(Animation animation) {
        if (animation instanceof AlphaAnimation) {
            return true;
        }
        if (animation instanceof AnimationSet) {
            List<Animation> animations = ((AnimationSet) animation).getAnimations();
            for (int i2 = 0; i2 < animations.size(); i2++) {
                if (animations.get(i2) instanceof AlphaAnimation) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int f0(int i2) {
        if (i2 == 4097) {
            return 8194;
        }
        if (i2 != 4099) {
            return i2 != 8194 ? 0 : 4097;
        }
        return 4099;
    }

    public static boolean l0(View view, Animation animation) {
        return Build.VERSION.SDK_INT >= 19 && AbstractC0285k0.j(view) == 0 && AbstractC0285k0.w(view) && S(animation);
    }

    public static int p0(int i2, boolean z2) {
        if (i2 == 4097) {
            return z2 ? 1 : 2;
        }
        if (i2 == 4099) {
            return z2 ? 5 : 6;
        }
        if (i2 != 8194) {
            return -1;
        }
        return z2 ? 3 : 4;
    }

    public void A() {
        this.f1784q = false;
        U(4, false);
    }

    public void B() {
        this.f1784q = true;
        U(3, false);
    }

    public void C() {
        C0134e0 c0134e0;
        if (this.f1787t) {
            boolean zI = false;
            for (int i2 = 0; i2 < this.f1771d.size(); i2++) {
                AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = (AbstractComponentCallbacksC0165t) this.f1771d.get(i2);
                if (abstractComponentCallbacksC0165t != null && (c0134e0 = abstractComponentCallbacksC0165t.f2002M) != null) {
                    zI |= c0134e0.i();
                }
            }
            if (zI) {
                return;
            }
            this.f1787t = false;
            n0();
        }
    }

    public boolean D() {
        int size;
        if (this.f1770c) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (Looper.myLooper() != this.f1780m.j().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        boolean z2 = false;
        while (true) {
            synchronized (this) {
                try {
                    ArrayList arrayList = this.f1768a;
                    if (arrayList == null || arrayList.size() == 0) {
                        break;
                    }
                    size = this.f1768a.size();
                    Runnable[] runnableArr = this.f1769b;
                    if (runnableArr == null || runnableArr.length < size) {
                        this.f1769b = new Runnable[size];
                    }
                    this.f1768a.toArray(this.f1769b);
                    this.f1768a.clear();
                    this.f1780m.j().removeCallbacks(this.f1790w);
                } catch (Throwable th) {
                    throw th;
                }
            }
            this.f1770c = true;
            for (int i2 = 0; i2 < size; i2++) {
                this.f1769b[i2].run();
                this.f1769b[i2] = null;
            }
            this.f1770c = false;
            z2 = true;
        }
        C();
        return z2;
    }

    public void E(Runnable runnable, boolean z2) {
        if (this.f1770c) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (Looper.myLooper() != this.f1780m.j().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z2) {
            i();
        }
        this.f1770c = true;
        runnable.run();
        this.f1770c = false;
        C();
    }

    public boolean F() {
        return D();
    }

    public AbstractComponentCallbacksC0165t G(int i2) {
        ArrayList arrayList = this.f1772e;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = (AbstractComponentCallbacksC0165t) this.f1772e.get(size);
                if (abstractComponentCallbacksC0165t != null && abstractComponentCallbacksC0165t.f2035w == i2) {
                    return abstractComponentCallbacksC0165t;
                }
            }
        }
        ArrayList arrayList2 = this.f1771d;
        if (arrayList2 == null) {
            return null;
        }
        for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
            AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t2 = (AbstractComponentCallbacksC0165t) this.f1771d.get(size2);
            if (abstractComponentCallbacksC0165t2 != null && abstractComponentCallbacksC0165t2.f2035w == i2) {
                return abstractComponentCallbacksC0165t2;
            }
        }
        return null;
    }

    public AbstractComponentCallbacksC0165t H(String str) {
        AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165tD;
        ArrayList arrayList = this.f1771d;
        if (arrayList == null || str == null) {
            return null;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = (AbstractComponentCallbacksC0165t) this.f1771d.get(size);
            if (abstractComponentCallbacksC0165t != null && (abstractComponentCallbacksC0165tD = abstractComponentCallbacksC0165t.d(str)) != null) {
                return abstractComponentCallbacksC0165tD;
            }
        }
        return null;
    }

    public void I(int i2) {
        synchronized (this) {
            try {
                this.f1776i.set(i2, null);
                if (this.f1777j == null) {
                    this.f1777j = new ArrayList();
                }
                if (f1765x) {
                    Log.v("FragmentManager", "Freeing back stack index " + i2);
                }
                this.f1777j.add(Integer.valueOf(i2));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public AbstractComponentCallbacksC0165t J(Bundle bundle, String str) {
        int i2 = bundle.getInt(str, -1);
        if (i2 == -1) {
            return null;
        }
        if (i2 >= this.f1771d.size()) {
            o0(new IllegalStateException("Fragment no longer exists for key " + str + ": index " + i2));
        }
        AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = (AbstractComponentCallbacksC0165t) this.f1771d.get(i2);
        if (abstractComponentCallbacksC0165t == null) {
            o0(new IllegalStateException("Fragment no longer exists for key " + str + ": index " + i2));
        }
        return abstractComponentCallbacksC0165t;
    }

    public void L(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, int i2, int i3) {
        if (f1765x) {
            Log.v("FragmentManager", "hide: " + abstractComponentCallbacksC0165t);
        }
        if (abstractComponentCallbacksC0165t.f2038z) {
            return;
        }
        abstractComponentCallbacksC0165t.f2038z = true;
        if (abstractComponentCallbacksC0165t.f1998I != null) {
            Animation animationN = N(abstractComponentCallbacksC0165t, i2, false, i3);
            if (animationN != null) {
                k0(abstractComponentCallbacksC0165t.f1998I, animationN);
                abstractComponentCallbacksC0165t.f1998I.startAnimation(animationN);
            }
            abstractComponentCallbacksC0165t.f1998I.setVisibility(8);
        }
        if (abstractComponentCallbacksC0165t.f2024l && abstractComponentCallbacksC0165t.f1993D && abstractComponentCallbacksC0165t.f1994E) {
            this.f1783p = true;
        }
        abstractComponentCallbacksC0165t.P(true);
    }

    public boolean M(int i2) {
        return this.f1779l >= i2;
    }

    public Animation N(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, int i2, boolean z2, int i3) {
        int iP0;
        Animation animationLoadAnimation;
        Animation animationI = abstractComponentCallbacksC0165t.I(i2, z2, abstractComponentCallbacksC0165t.f1996G);
        if (animationI != null) {
            return animationI;
        }
        if (abstractComponentCallbacksC0165t.f1996G != 0 && (animationLoadAnimation = AnimationUtils.loadAnimation(this.f1780m.h(), abstractComponentCallbacksC0165t.f1996G)) != null) {
            return animationLoadAnimation;
        }
        if (i2 == 0 || (iP0 = p0(i2, z2)) < 0) {
            return null;
        }
        switch (iP0) {
            case 1:
                return R(this.f1780m.h(), 1.125f, 1.0f, 0.0f, 1.0f);
            case 2:
                return R(this.f1780m.h(), 1.0f, 0.975f, 1.0f, 0.0f);
            case 3:
                return R(this.f1780m.h(), 0.975f, 1.0f, 0.0f, 1.0f);
            case 4:
                return R(this.f1780m.h(), 1.0f, 1.075f, 1.0f, 0.0f);
            case 5:
                return P(this.f1780m.h(), 0.0f, 1.0f);
            case 6:
                return P(this.f1780m.h(), 1.0f, 0.0f);
            default:
                if (i3 == 0 && this.f1780m.r()) {
                    this.f1780m.q();
                }
                return null;
        }
    }

    public void O(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t) {
        if (abstractComponentCallbacksC0165t.f2018f >= 0) {
            return;
        }
        ArrayList arrayList = this.f1773f;
        if (arrayList == null || arrayList.size() <= 0) {
            if (this.f1771d == null) {
                this.f1771d = new ArrayList();
            }
            abstractComponentCallbacksC0165t.C0(this.f1771d.size(), this.f1782o);
            this.f1771d.add(abstractComponentCallbacksC0165t);
        } else {
            abstractComponentCallbacksC0165t.C0(((Integer) this.f1773f.remove(r0.size() - 1)).intValue(), this.f1782o);
            this.f1771d.set(abstractComponentCallbacksC0165t.f2018f, abstractComponentCallbacksC0165t);
        }
        if (f1765x) {
            Log.v("FragmentManager", "Allocated fragment index " + abstractComponentCallbacksC0165t);
        }
    }

    public void Q(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t) {
        if (abstractComponentCallbacksC0165t.f2018f < 0) {
            return;
        }
        if (f1765x) {
            Log.v("FragmentManager", "Freeing fragment index " + abstractComponentCallbacksC0165t);
        }
        this.f1771d.set(abstractComponentCallbacksC0165t.f2018f, null);
        if (this.f1773f == null) {
            this.f1773f = new ArrayList();
        }
        this.f1773f.add(Integer.valueOf(abstractComponentCallbacksC0165t.f2018f));
        this.f1780m.m(abstractComponentCallbacksC0165t.f2019g);
        abstractComponentCallbacksC0165t.t();
    }

    public void T(int i2, int i3, int i4, boolean z2) {
        AbstractC0173x abstractC0173x;
        if (this.f1780m == null && i2 != 0) {
            throw new IllegalStateException("No host");
        }
        if (z2 || this.f1779l != i2) {
            this.f1779l = i2;
            if (this.f1771d != null) {
                boolean zI = false;
                for (int i5 = 0; i5 < this.f1771d.size(); i5++) {
                    AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = (AbstractComponentCallbacksC0165t) this.f1771d.get(i5);
                    if (abstractComponentCallbacksC0165t != null) {
                        W(abstractComponentCallbacksC0165t, i2, i3, i4, false);
                        C0134e0 c0134e0 = abstractComponentCallbacksC0165t.f2002M;
                        if (c0134e0 != null) {
                            zI |= c0134e0.i();
                        }
                    }
                }
                if (!zI) {
                    n0();
                }
                if (this.f1783p && (abstractC0173x = this.f1780m) != null && this.f1779l == 5) {
                    abstractC0173x.u();
                    this.f1783p = false;
                }
            }
        }
    }

    public void U(int i2, boolean z2) {
        T(i2, 0, 0, z2);
    }

    public void V(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t) {
        W(abstractComponentCallbacksC0165t, this.f1779l, 0, 0, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x03cc  */
    /* JADX WARN: Removed duplicated region for block: B:223:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0159  */
    /* JADX WARN: Type inference failed for: r0v116 */
    /* JADX WARN: Type inference failed for: r0v117 */
    /* JADX WARN: Type inference failed for: r0v118 */
    /* JADX WARN: Type inference failed for: r0v88 */
    /* JADX WARN: Type inference failed for: r0v89, types: [android.view.ViewGroup] */
    /* JADX WARN: Type inference failed for: r19v0, types: [c.t, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v3, types: [android.os.Bundle, android.view.View] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7, types: [android.os.Bundle, android.util.SparseArray] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void W(c.AbstractComponentCallbacksC0165t r19, int r20, int r21, int r22, boolean r23) {
        /*
            Method dump skipped, instruction units count: 1018
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.C0109A.W(c.t, int, int, int, boolean):void");
    }

    public void X() {
        this.f1784q = false;
    }

    public void Y(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t) {
        if (abstractComponentCallbacksC0165t.f2000K) {
            if (this.f1770c) {
                this.f1787t = true;
            } else {
                abstractComponentCallbacksC0165t.f2000K = false;
                W(abstractComponentCallbacksC0165t, this.f1779l, 0, 0, false);
            }
        }
    }

    public boolean Z(Handler handler, String str, int i2, int i3) {
        int size;
        ArrayList arrayList = this.f1774g;
        if (arrayList == null) {
            return false;
        }
        RunnableC0156o.e eVarY = null;
        if (str == null && i2 < 0 && (i3 & 1) == 0) {
            int size2 = arrayList.size() - 1;
            if (size2 < 0) {
                return false;
            }
            RunnableC0156o runnableC0156o = (RunnableC0156o) this.f1774g.remove(size2);
            SparseArray sparseArray = new SparseArray();
            SparseArray sparseArray2 = new SparseArray();
            if (this.f1779l >= 1) {
                runnableC0156o.h(sparseArray, sparseArray2);
            }
            runnableC0156o.y(true, null, sparseArray, sparseArray2);
            c0();
        } else {
            if (str != null || i2 >= 0) {
                size = arrayList.size() - 1;
                while (size >= 0) {
                    RunnableC0156o runnableC0156o2 = (RunnableC0156o) this.f1774g.get(size);
                    if ((str != null && str.equals(runnableC0156o2.u())) || (i2 >= 0 && i2 == runnableC0156o2.f1887o)) {
                        break;
                    }
                    size--;
                }
                if (size < 0) {
                    return false;
                }
                if ((i3 & 1) != 0) {
                    while (true) {
                        size--;
                        if (size < 0) {
                            break;
                        }
                        RunnableC0156o runnableC0156o3 = (RunnableC0156o) this.f1774g.get(size);
                        if (str == null || !str.equals(runnableC0156o3.u())) {
                            if (i2 < 0 || i2 != runnableC0156o3.f1887o) {
                                break;
                            }
                        }
                    }
                }
            } else {
                size = -1;
            }
            if (size == this.f1774g.size() - 1) {
                return false;
            }
            ArrayList arrayList2 = new ArrayList();
            for (int size3 = this.f1774g.size() - 1; size3 > size; size3--) {
                arrayList2.add(this.f1774g.remove(size3));
            }
            int size4 = arrayList2.size() - 1;
            SparseArray sparseArray3 = new SparseArray();
            SparseArray sparseArray4 = new SparseArray();
            if (this.f1779l >= 1) {
                for (int i4 = 0; i4 <= size4; i4++) {
                    ((RunnableC0156o) arrayList2.get(i4)).h(sparseArray3, sparseArray4);
                }
            }
            int i5 = 0;
            while (i5 <= size4) {
                if (f1765x) {
                    Log.v("FragmentManager", "Popping back stack state: " + arrayList2.get(i5));
                }
                eVarY = ((RunnableC0156o) arrayList2.get(i5)).y(i5 == size4, eVarY, sparseArray3, sparseArray4);
                i5++;
            }
            c0();
        }
        return true;
    }

    @Override // c.AbstractC0175y
    public AbstractC0114F a() {
        return new RunnableC0156o(this);
    }

    public void a0(Bundle bundle, String str, AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t) {
        if (abstractComponentCallbacksC0165t.f2018f < 0) {
            o0(new IllegalStateException("Fragment " + abstractComponentCallbacksC0165t + " is not currently in the FragmentManager"));
        }
        bundle.putInt(str, abstractComponentCallbacksC0165t.f2018f);
    }

    @Override // c.AbstractC0175y
    public void b(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        int size3;
        int size4;
        int size5;
        int size6;
        String str2 = str + "    ";
        ArrayList arrayList = this.f1771d;
        if (arrayList != null && (size6 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.print("Active Fragments in ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(":");
            for (int i2 = 0; i2 < size6; i2++) {
                AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = (AbstractComponentCallbacksC0165t) this.f1771d.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(abstractComponentCallbacksC0165t);
                if (abstractComponentCallbacksC0165t != null) {
                    abstractComponentCallbacksC0165t.c(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }
        ArrayList arrayList2 = this.f1772e;
        if (arrayList2 != null && (size5 = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i3 = 0; i3 < size5; i3++) {
                AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t2 = (AbstractComponentCallbacksC0165t) this.f1772e.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(abstractComponentCallbacksC0165t2.toString());
            }
        }
        ArrayList arrayList3 = this.f1775h;
        if (arrayList3 != null && (size4 = arrayList3.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i4 = 0; i4 < size4; i4++) {
                AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t3 = (AbstractComponentCallbacksC0165t) this.f1775h.get(i4);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i4);
                printWriter.print(": ");
                printWriter.println(abstractComponentCallbacksC0165t3.toString());
            }
        }
        ArrayList arrayList4 = this.f1774g;
        if (arrayList4 != null && (size3 = arrayList4.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i5 = 0; i5 < size3; i5++) {
                RunnableC0156o runnableC0156o = (RunnableC0156o) this.f1774g.get(i5);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i5);
                printWriter.print(": ");
                printWriter.println(runnableC0156o.toString());
                runnableC0156o.o(str2, fileDescriptor, printWriter, strArr);
            }
        }
        synchronized (this) {
            try {
                ArrayList arrayList5 = this.f1776i;
                if (arrayList5 != null && (size2 = arrayList5.size()) > 0) {
                    printWriter.print(str);
                    printWriter.println("Back Stack Indices:");
                    for (int i6 = 0; i6 < size2; i6++) {
                        Object obj = (RunnableC0156o) this.f1776i.get(i6);
                        printWriter.print(str);
                        printWriter.print("  #");
                        printWriter.print(i6);
                        printWriter.print(": ");
                        printWriter.println(obj);
                    }
                }
                ArrayList arrayList6 = this.f1777j;
                if (arrayList6 != null && arrayList6.size() > 0) {
                    printWriter.print(str);
                    printWriter.print("mAvailBackStackIndices: ");
                    printWriter.println(Arrays.toString(this.f1777j.toArray()));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        ArrayList arrayList7 = this.f1768a;
        if (arrayList7 != null && (size = arrayList7.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Pending Actions:");
            for (int i7 = 0; i7 < size; i7++) {
                Object obj2 = (Runnable) this.f1768a.get(i7);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i7);
                printWriter.print(": ");
                printWriter.println(obj2);
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.f1780m);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.f1781n);
        if (this.f1782o != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.f1782o);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.f1779l);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.f1784q);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.f1785r);
        if (this.f1783p) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.f1783p);
        }
        if (this.f1786s != null) {
            printWriter.print(str);
            printWriter.print("  mNoTransactionsBecause=");
            printWriter.println(this.f1786s);
        }
        ArrayList arrayList8 = this.f1773f;
        if (arrayList8 == null || arrayList8.size() <= 0) {
            return;
        }
        printWriter.print(str);
        printWriter.print("  mAvailIndices: ");
        printWriter.println(Arrays.toString(this.f1773f.toArray()));
    }

    public void b0(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, int i2, int i3) {
        if (f1765x) {
            Log.v("FragmentManager", "remove: " + abstractComponentCallbacksC0165t + " nesting=" + abstractComponentCallbacksC0165t.f2029q);
        }
        boolean z2 = abstractComponentCallbacksC0165t.z();
        if (abstractComponentCallbacksC0165t.f1990A && z2) {
            return;
        }
        ArrayList arrayList = this.f1772e;
        if (arrayList != null) {
            arrayList.remove(abstractComponentCallbacksC0165t);
        }
        if (abstractComponentCallbacksC0165t.f1993D && abstractComponentCallbacksC0165t.f1994E) {
            this.f1783p = true;
        }
        abstractComponentCallbacksC0165t.f2024l = false;
        abstractComponentCallbacksC0165t.f2025m = true;
        W(abstractComponentCallbacksC0165t, z2 ? 1 : 0, i2, i3, false);
    }

    @Override // c.AbstractC0175y
    public AbstractComponentCallbacksC0165t c(String str) {
        ArrayList arrayList = this.f1772e;
        if (arrayList != null && str != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = (AbstractComponentCallbacksC0165t) this.f1772e.get(size);
                if (abstractComponentCallbacksC0165t != null && str.equals(abstractComponentCallbacksC0165t.f2037y)) {
                    return abstractComponentCallbacksC0165t;
                }
            }
        }
        ArrayList arrayList2 = this.f1771d;
        if (arrayList2 == null || str == null) {
            return null;
        }
        for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
            AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t2 = (AbstractComponentCallbacksC0165t) this.f1771d.get(size2);
            if (abstractComponentCallbacksC0165t2 != null && str.equals(abstractComponentCallbacksC0165t2.f2037y)) {
                return abstractComponentCallbacksC0165t2;
            }
        }
        return null;
    }

    public void c0() {
        ArrayList arrayList = this.f1778k;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        AbstractC0177z.a(this.f1778k.get(0));
        throw null;
    }

    @Override // c.AbstractC0175y
    public boolean d() {
        i();
        F();
        return Z(this.f1780m.j(), null, -1, 0);
    }

    public void d0(Parcelable parcelable, C0110B c0110b) {
        List listA;
        if (parcelable == null) {
            return;
        }
        C0111C c0111c = (C0111C) parcelable;
        if (c0111c.f1806a == null) {
            return;
        }
        if (c0110b != null) {
            List listB = c0110b.b();
            listA = c0110b.a();
            int size = listB != null ? listB.size() : 0;
            for (int i2 = 0; i2 < size; i2++) {
                AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = (AbstractComponentCallbacksC0165t) listB.get(i2);
                if (f1765x) {
                    Log.v("FragmentManager", "restoreAllState: re-attaching retained " + abstractComponentCallbacksC0165t);
                }
                C0113E c0113e = c0111c.f1806a[abstractComponentCallbacksC0165t.f2018f];
                c0113e.f1823l = abstractComponentCallbacksC0165t;
                abstractComponentCallbacksC0165t.f2017e = null;
                abstractComponentCallbacksC0165t.f2029q = 0;
                abstractComponentCallbacksC0165t.f2027o = false;
                abstractComponentCallbacksC0165t.f2024l = false;
                abstractComponentCallbacksC0165t.f2021i = null;
                Bundle bundle = c0113e.f1822k;
                if (bundle != null) {
                    bundle.setClassLoader(this.f1780m.h().getClassLoader());
                    abstractComponentCallbacksC0165t.f2017e = c0113e.f1822k.getSparseParcelableArray("android:view_state");
                    abstractComponentCallbacksC0165t.f2016d = c0113e.f1822k;
                }
            }
        } else {
            listA = null;
        }
        this.f1771d = new ArrayList(c0111c.f1806a.length);
        ArrayList arrayList = this.f1773f;
        if (arrayList != null) {
            arrayList.clear();
        }
        int i3 = 0;
        while (true) {
            C0113E[] c0113eArr = c0111c.f1806a;
            if (i3 >= c0113eArr.length) {
                break;
            }
            C0113E c0113e2 = c0113eArr[i3];
            if (c0113e2 != null) {
                AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165tA = c0113e2.a(this.f1780m, this.f1782o, (listA == null || i3 >= listA.size()) ? null : (C0110B) listA.get(i3));
                if (f1765x) {
                    Log.v("FragmentManager", "restoreAllState: active #" + i3 + ": " + abstractComponentCallbacksC0165tA);
                }
                this.f1771d.add(abstractComponentCallbacksC0165tA);
                c0113e2.f1823l = null;
            } else {
                this.f1771d.add(null);
                if (this.f1773f == null) {
                    this.f1773f = new ArrayList();
                }
                if (f1765x) {
                    Log.v("FragmentManager", "restoreAllState: avail #" + i3);
                }
                this.f1773f.add(Integer.valueOf(i3));
            }
            i3++;
        }
        if (c0110b != null) {
            List listB2 = c0110b.b();
            int size2 = listB2 != null ? listB2.size() : 0;
            for (int i4 = 0; i4 < size2; i4++) {
                AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t2 = (AbstractComponentCallbacksC0165t) listB2.get(i4);
                int i5 = abstractComponentCallbacksC0165t2.f2022j;
                if (i5 >= 0) {
                    if (i5 < this.f1771d.size()) {
                        abstractComponentCallbacksC0165t2.f2021i = (AbstractComponentCallbacksC0165t) this.f1771d.get(abstractComponentCallbacksC0165t2.f2022j);
                    } else {
                        Log.w("FragmentManager", "Re-attaching retained fragment " + abstractComponentCallbacksC0165t2 + " target no longer exists: " + abstractComponentCallbacksC0165t2.f2022j);
                        abstractComponentCallbacksC0165t2.f2021i = null;
                    }
                }
            }
        }
        if (c0111c.f1807b != null) {
            this.f1772e = new ArrayList(c0111c.f1807b.length);
            int i6 = 0;
            while (true) {
                int[] iArr = c0111c.f1807b;
                if (i6 >= iArr.length) {
                    break;
                }
                AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t3 = (AbstractComponentCallbacksC0165t) this.f1771d.get(iArr[i6]);
                if (abstractComponentCallbacksC0165t3 == null) {
                    o0(new IllegalStateException("No instantiated fragment for index #" + c0111c.f1807b[i6]));
                }
                abstractComponentCallbacksC0165t3.f2024l = true;
                if (f1765x) {
                    Log.v("FragmentManager", "restoreAllState: added #" + i6 + ": " + abstractComponentCallbacksC0165t3);
                }
                if (this.f1772e.contains(abstractComponentCallbacksC0165t3)) {
                    throw new IllegalStateException("Already added!");
                }
                this.f1772e.add(abstractComponentCallbacksC0165t3);
                i6++;
            }
        } else {
            this.f1772e = null;
        }
        if (c0111c.f1808c == null) {
            this.f1774g = null;
            return;
        }
        this.f1774g = new ArrayList(c0111c.f1808c.length);
        int i7 = 0;
        while (true) {
            C0158p[] c0158pArr = c0111c.f1808c;
            if (i7 >= c0158pArr.length) {
                return;
            }
            RunnableC0156o runnableC0156oA = c0158pArr[i7].a(this);
            if (f1765x) {
                Log.v("FragmentManager", "restoreAllState: back stack #" + i7 + " (index " + runnableC0156oA.f1887o + "): " + runnableC0156oA);
                runnableC0156oA.p("  ", new PrintWriter(new j.d("FragmentManager")), false);
            }
            this.f1774g.add(runnableC0156oA);
            int i8 = runnableC0156oA.f1887o;
            if (i8 >= 0) {
                j0(i8, runnableC0156oA);
            }
            i7++;
        }
    }

    public void e(RunnableC0156o runnableC0156o) {
        if (this.f1774g == null) {
            this.f1774g = new ArrayList();
        }
        this.f1774g.add(runnableC0156o);
        c0();
    }

    public C0110B e0() {
        ArrayList arrayList;
        ArrayList arrayList2;
        C0110B c0110bE0;
        if (this.f1771d != null) {
            arrayList = null;
            arrayList2 = null;
            for (int i2 = 0; i2 < this.f1771d.size(); i2++) {
                AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = (AbstractComponentCallbacksC0165t) this.f1771d.get(i2);
                if (abstractComponentCallbacksC0165t != null) {
                    boolean z2 = true;
                    if (abstractComponentCallbacksC0165t.f1991B) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(abstractComponentCallbacksC0165t);
                        abstractComponentCallbacksC0165t.f1992C = true;
                        AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t2 = abstractComponentCallbacksC0165t.f2021i;
                        abstractComponentCallbacksC0165t.f2022j = abstractComponentCallbacksC0165t2 != null ? abstractComponentCallbacksC0165t2.f2018f : -1;
                        if (f1765x) {
                            Log.v("FragmentManager", "retainNonConfig: keeping retained " + abstractComponentCallbacksC0165t);
                        }
                    }
                    C0109A c0109a = abstractComponentCallbacksC0165t.f2032t;
                    if (c0109a == null || (c0110bE0 = c0109a.e0()) == null) {
                        z2 = false;
                    } else {
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList();
                            for (int i3 = 0; i3 < i2; i3++) {
                                arrayList2.add(null);
                            }
                        }
                        arrayList2.add(c0110bE0);
                    }
                    if (arrayList2 != null && !z2) {
                        arrayList2.add(null);
                    }
                }
            }
        } else {
            arrayList = null;
            arrayList2 = null;
        }
        if (arrayList == null && arrayList2 == null) {
            return null;
        }
        return new C0110B(arrayList, arrayList2);
    }

    public void f(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, boolean z2) {
        if (this.f1772e == null) {
            this.f1772e = new ArrayList();
        }
        if (f1765x) {
            Log.v("FragmentManager", "add: " + abstractComponentCallbacksC0165t);
        }
        O(abstractComponentCallbacksC0165t);
        if (abstractComponentCallbacksC0165t.f1990A) {
            return;
        }
        if (this.f1772e.contains(abstractComponentCallbacksC0165t)) {
            throw new IllegalStateException("Fragment already added: " + abstractComponentCallbacksC0165t);
        }
        this.f1772e.add(abstractComponentCallbacksC0165t);
        abstractComponentCallbacksC0165t.f2024l = true;
        abstractComponentCallbacksC0165t.f2025m = false;
        if (abstractComponentCallbacksC0165t.f1993D && abstractComponentCallbacksC0165t.f1994E) {
            this.f1783p = true;
        }
        if (z2) {
            V(abstractComponentCallbacksC0165t);
        }
    }

    public void g(AbstractC0173x abstractC0173x, AbstractC0169v abstractC0169v, AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t) {
        if (this.f1780m != null) {
            throw new IllegalStateException("Already attached");
        }
        this.f1780m = abstractC0173x;
        this.f1781n = abstractC0169v;
        this.f1782o = abstractComponentCallbacksC0165t;
    }

    public Parcelable g0() {
        int[] iArr;
        int size;
        int size2;
        D();
        if (f1766y) {
            this.f1784q = true;
        }
        ArrayList arrayList = this.f1771d;
        C0158p[] c0158pArr = null;
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        int size3 = this.f1771d.size();
        C0113E[] c0113eArr = new C0113E[size3];
        boolean z2 = false;
        for (int i2 = 0; i2 < size3; i2++) {
            AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = (AbstractComponentCallbacksC0165t) this.f1771d.get(i2);
            if (abstractComponentCallbacksC0165t != null) {
                if (abstractComponentCallbacksC0165t.f2018f < 0) {
                    o0(new IllegalStateException("Failure saving state: active " + abstractComponentCallbacksC0165t + " has cleared index: " + abstractComponentCallbacksC0165t.f2018f));
                }
                C0113E c0113e = new C0113E(abstractComponentCallbacksC0165t);
                c0113eArr[i2] = c0113e;
                if (abstractComponentCallbacksC0165t.f2013a <= 0 || c0113e.f1822k != null) {
                    c0113e.f1822k = abstractComponentCallbacksC0165t.f2016d;
                } else {
                    c0113e.f1822k = h0(abstractComponentCallbacksC0165t);
                    AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t2 = abstractComponentCallbacksC0165t.f2021i;
                    if (abstractComponentCallbacksC0165t2 != null) {
                        if (abstractComponentCallbacksC0165t2.f2018f < 0) {
                            o0(new IllegalStateException("Failure saving state: " + abstractComponentCallbacksC0165t + " has target not in fragment manager: " + abstractComponentCallbacksC0165t.f2021i));
                        }
                        if (c0113e.f1822k == null) {
                            c0113e.f1822k = new Bundle();
                        }
                        a0(c0113e.f1822k, "android:target_state", abstractComponentCallbacksC0165t.f2021i);
                        int i3 = abstractComponentCallbacksC0165t.f2023k;
                        if (i3 != 0) {
                            c0113e.f1822k.putInt("android:target_req_state", i3);
                        }
                    }
                }
                if (f1765x) {
                    Log.v("FragmentManager", "Saved state of " + abstractComponentCallbacksC0165t + ": " + c0113e.f1822k);
                }
                z2 = true;
            }
        }
        if (!z2) {
            if (f1765x) {
                Log.v("FragmentManager", "saveAllState: no fragments!");
            }
            return null;
        }
        ArrayList arrayList2 = this.f1772e;
        if (arrayList2 == null || (size2 = arrayList2.size()) <= 0) {
            iArr = null;
        } else {
            iArr = new int[size2];
            for (int i4 = 0; i4 < size2; i4++) {
                int i5 = ((AbstractComponentCallbacksC0165t) this.f1772e.get(i4)).f2018f;
                iArr[i4] = i5;
                if (i5 < 0) {
                    o0(new IllegalStateException("Failure saving state: active " + this.f1772e.get(i4) + " has cleared index: " + iArr[i4]));
                }
                if (f1765x) {
                    Log.v("FragmentManager", "saveAllState: adding fragment #" + i4 + ": " + this.f1772e.get(i4));
                }
            }
        }
        ArrayList arrayList3 = this.f1774g;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            c0158pArr = new C0158p[size];
            for (int i6 = 0; i6 < size; i6++) {
                c0158pArr[i6] = new C0158p((RunnableC0156o) this.f1774g.get(i6));
                if (f1765x) {
                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i6 + ": " + this.f1774g.get(i6));
                }
            }
        }
        C0111C c0111c = new C0111C();
        c0111c.f1806a = c0113eArr;
        c0111c.f1807b = iArr;
        c0111c.f1808c = c0158pArr;
        return c0111c;
    }

    public void h(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, int i2, int i3) {
        if (f1765x) {
            Log.v("FragmentManager", "attach: " + abstractComponentCallbacksC0165t);
        }
        if (abstractComponentCallbacksC0165t.f1990A) {
            abstractComponentCallbacksC0165t.f1990A = false;
            if (abstractComponentCallbacksC0165t.f2024l) {
                return;
            }
            if (this.f1772e == null) {
                this.f1772e = new ArrayList();
            }
            if (this.f1772e.contains(abstractComponentCallbacksC0165t)) {
                throw new IllegalStateException("Fragment already added: " + abstractComponentCallbacksC0165t);
            }
            if (f1765x) {
                Log.v("FragmentManager", "add from attach: " + abstractComponentCallbacksC0165t);
            }
            this.f1772e.add(abstractComponentCallbacksC0165t);
            abstractComponentCallbacksC0165t.f2024l = true;
            if (abstractComponentCallbacksC0165t.f1993D && abstractComponentCallbacksC0165t.f1994E) {
                this.f1783p = true;
            }
            W(abstractComponentCallbacksC0165t, this.f1779l, i2, i3, false);
        }
    }

    public Bundle h0(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t) {
        if (this.f1788u == null) {
            this.f1788u = new Bundle();
        }
        abstractComponentCallbacksC0165t.x0(this.f1788u);
        Bundle bundle = null;
        if (!this.f1788u.isEmpty()) {
            Bundle bundle2 = this.f1788u;
            this.f1788u = null;
            bundle = bundle2;
        }
        if (abstractComponentCallbacksC0165t.f1998I != null) {
            i0(abstractComponentCallbacksC0165t);
        }
        if (abstractComponentCallbacksC0165t.f2017e != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", abstractComponentCallbacksC0165t.f2017e);
        }
        if (!abstractComponentCallbacksC0165t.f2001L) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", abstractComponentCallbacksC0165t.f2001L);
        }
        return bundle;
    }

    public final void i() {
        if (this.f1784q) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (this.f1786s == null) {
            return;
        }
        throw new IllegalStateException("Can not perform this action inside of " + this.f1786s);
    }

    public void i0(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t) {
        if (abstractComponentCallbacksC0165t.f1999J == null) {
            return;
        }
        SparseArray sparseArray = this.f1789v;
        if (sparseArray == null) {
            this.f1789v = new SparseArray();
        } else {
            sparseArray.clear();
        }
        abstractComponentCallbacksC0165t.f1999J.saveHierarchyState(this.f1789v);
        if (this.f1789v.size() > 0) {
            abstractComponentCallbacksC0165t.f2017e = this.f1789v;
            this.f1789v = null;
        }
    }

    public void j(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, int i2, int i3) {
        if (f1765x) {
            Log.v("FragmentManager", "detach: " + abstractComponentCallbacksC0165t);
        }
        if (abstractComponentCallbacksC0165t.f1990A) {
            return;
        }
        abstractComponentCallbacksC0165t.f1990A = true;
        if (abstractComponentCallbacksC0165t.f2024l) {
            if (this.f1772e != null) {
                if (f1765x) {
                    Log.v("FragmentManager", "remove from detach: " + abstractComponentCallbacksC0165t);
                }
                this.f1772e.remove(abstractComponentCallbacksC0165t);
            }
            if (abstractComponentCallbacksC0165t.f1993D && abstractComponentCallbacksC0165t.f1994E) {
                this.f1783p = true;
            }
            abstractComponentCallbacksC0165t.f2024l = false;
            W(abstractComponentCallbacksC0165t, 1, i2, i3, false);
        }
    }

    public void j0(int i2, RunnableC0156o runnableC0156o) {
        synchronized (this) {
            try {
                if (this.f1776i == null) {
                    this.f1776i = new ArrayList();
                }
                int size = this.f1776i.size();
                if (i2 < size) {
                    if (f1765x) {
                        Log.v("FragmentManager", "Setting back stack index " + i2 + " to " + runnableC0156o);
                    }
                    this.f1776i.set(i2, runnableC0156o);
                } else {
                    while (size < i2) {
                        this.f1776i.add(null);
                        if (this.f1777j == null) {
                            this.f1777j = new ArrayList();
                        }
                        if (f1765x) {
                            Log.v("FragmentManager", "Adding available back stack index " + size);
                        }
                        this.f1777j.add(Integer.valueOf(size));
                        size++;
                    }
                    if (f1765x) {
                        Log.v("FragmentManager", "Adding back stack index " + i2 + " with " + runnableC0156o);
                    }
                    this.f1776i.add(runnableC0156o);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void k() {
        this.f1784q = false;
        U(2, false);
    }

    public final void k0(View view, Animation animation) {
        Animation.AnimationListener animationListener;
        if (view == null || animation == null || !l0(view, animation)) {
            return;
        }
        try {
            if (f1767z == null) {
                Field declaredField = Animation.class.getDeclaredField("mListener");
                f1767z = declaredField;
                declaredField.setAccessible(true);
            }
            animationListener = (Animation.AnimationListener) f1767z.get(animation);
        } catch (IllegalAccessException e2) {
            Log.e("FragmentManager", "Cannot access Animation's mListener field", e2);
            animationListener = null;
        } catch (NoSuchFieldException e3) {
            Log.e("FragmentManager", "No field with the name mListener is found in Animation class", e3);
            animationListener = null;
        }
        AbstractC0285k0.Q(view, 2, null);
        animation.setAnimationListener(new c(view, animation, animationListener));
    }

    public void l(Configuration configuration) {
        if (this.f1772e != null) {
            for (int i2 = 0; i2 < this.f1772e.size(); i2++) {
                AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = (AbstractComponentCallbacksC0165t) this.f1772e.get(i2);
                if (abstractComponentCallbacksC0165t != null) {
                    abstractComponentCallbacksC0165t.g0(configuration);
                }
            }
        }
    }

    public boolean m(MenuItem menuItem) {
        if (this.f1772e != null) {
            for (int i2 = 0; i2 < this.f1772e.size(); i2++) {
                AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = (AbstractComponentCallbacksC0165t) this.f1772e.get(i2);
                if (abstractComponentCallbacksC0165t != null && abstractComponentCallbacksC0165t.h0(menuItem)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void m0(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, int i2, int i3) {
        if (f1765x) {
            Log.v("FragmentManager", "show: " + abstractComponentCallbacksC0165t);
        }
        if (abstractComponentCallbacksC0165t.f2038z) {
            abstractComponentCallbacksC0165t.f2038z = false;
            if (abstractComponentCallbacksC0165t.f1998I != null) {
                Animation animationN = N(abstractComponentCallbacksC0165t, i2, true, i3);
                if (animationN != null) {
                    k0(abstractComponentCallbacksC0165t.f1998I, animationN);
                    abstractComponentCallbacksC0165t.f1998I.startAnimation(animationN);
                }
                abstractComponentCallbacksC0165t.f1998I.setVisibility(0);
            }
            if (abstractComponentCallbacksC0165t.f2024l && abstractComponentCallbacksC0165t.f1993D && abstractComponentCallbacksC0165t.f1994E) {
                this.f1783p = true;
            }
            abstractComponentCallbacksC0165t.P(false);
        }
    }

    public void n() {
        this.f1784q = false;
        U(1, false);
    }

    public void n0() {
        if (this.f1771d == null) {
            return;
        }
        for (int i2 = 0; i2 < this.f1771d.size(); i2++) {
            AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = (AbstractComponentCallbacksC0165t) this.f1771d.get(i2);
            if (abstractComponentCallbacksC0165t != null) {
                Y(abstractComponentCallbacksC0165t);
            }
        }
    }

    public boolean o(Menu menu, MenuInflater menuInflater) {
        boolean z2;
        ArrayList arrayList = null;
        if (this.f1772e != null) {
            z2 = false;
            for (int i2 = 0; i2 < this.f1772e.size(); i2++) {
                AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = (AbstractComponentCallbacksC0165t) this.f1772e.get(i2);
                if (abstractComponentCallbacksC0165t != null && abstractComponentCallbacksC0165t.j0(menu, menuInflater)) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(abstractComponentCallbacksC0165t);
                    z2 = true;
                }
            }
        } else {
            z2 = false;
        }
        if (this.f1775h != null) {
            for (int i3 = 0; i3 < this.f1775h.size(); i3++) {
                AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t2 = (AbstractComponentCallbacksC0165t) this.f1775h.get(i3);
                if (arrayList == null || !arrayList.contains(abstractComponentCallbacksC0165t2)) {
                    abstractComponentCallbacksC0165t2.M();
                }
            }
        }
        this.f1775h = arrayList;
        return z2;
    }

    public final void o0(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new j.d("FragmentManager"));
        AbstractC0173x abstractC0173x = this.f1780m;
        if (abstractC0173x != null) {
            try {
                abstractC0173x.o("  ", null, printWriter, new String[0]);
                throw runtimeException;
            } catch (Exception e2) {
                Log.e("FragmentManager", "Failed dumping state", e2);
                throw runtimeException;
            }
        }
        try {
            b("  ", null, printWriter, new String[0]);
            throw runtimeException;
        } catch (Exception e3) {
            Log.e("FragmentManager", "Failed dumping state", e3);
            throw runtimeException;
        }
    }

    @Override // k.H
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, d.f1798a);
        if (attributeValue == null) {
            attributeValue = typedArrayObtainStyledAttributes.getString(0);
        }
        String str2 = attributeValue;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(1, -1);
        String string = typedArrayObtainStyledAttributes.getString(2);
        typedArrayObtainStyledAttributes.recycle();
        if (!AbstractComponentCallbacksC0165t.A(this.f1780m.h(), str2)) {
            return null;
        }
        int id = view != null ? view.getId() : 0;
        if (id == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + str2);
        }
        AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165tG = resourceId != -1 ? G(resourceId) : null;
        if (abstractComponentCallbacksC0165tG == null && string != null) {
            abstractComponentCallbacksC0165tG = c(string);
        }
        if (abstractComponentCallbacksC0165tG == null && id != -1) {
            abstractComponentCallbacksC0165tG = G(id);
        }
        if (f1765x) {
            Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + str2 + " existing=" + abstractComponentCallbacksC0165tG);
        }
        if (abstractComponentCallbacksC0165tG == null) {
            abstractComponentCallbacksC0165tG = AbstractComponentCallbacksC0165t.u(context, str2);
            abstractComponentCallbacksC0165tG.f2026n = true;
            abstractComponentCallbacksC0165tG.f2035w = resourceId != 0 ? resourceId : id;
            abstractComponentCallbacksC0165tG.f2036x = id;
            abstractComponentCallbacksC0165tG.f2037y = string;
            abstractComponentCallbacksC0165tG.f2027o = true;
            abstractComponentCallbacksC0165tG.f2030r = this;
            AbstractC0173x abstractC0173x = this.f1780m;
            abstractComponentCallbacksC0165tG.f2031s = abstractC0173x;
            abstractComponentCallbacksC0165tG.R(abstractC0173x.h(), attributeSet, abstractComponentCallbacksC0165tG.f2016d);
            f(abstractComponentCallbacksC0165tG, true);
        } else {
            if (abstractComponentCallbacksC0165tG.f2027o) {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + str2);
            }
            abstractComponentCallbacksC0165tG.f2027o = true;
            AbstractC0173x abstractC0173x2 = this.f1780m;
            abstractComponentCallbacksC0165tG.f2031s = abstractC0173x2;
            if (!abstractComponentCallbacksC0165tG.f1992C) {
                abstractComponentCallbacksC0165tG.R(abstractC0173x2.h(), attributeSet, abstractComponentCallbacksC0165tG.f2016d);
            }
        }
        AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = abstractComponentCallbacksC0165tG;
        if (this.f1779l >= 1 || !abstractComponentCallbacksC0165t.f2026n) {
            V(abstractComponentCallbacksC0165t);
        } else {
            W(abstractComponentCallbacksC0165t, 1, 0, 0, false);
        }
        View view2 = abstractComponentCallbacksC0165t.f1998I;
        if (view2 != null) {
            if (resourceId != 0) {
                view2.setId(resourceId);
            }
            if (abstractComponentCallbacksC0165t.f1998I.getTag() == null) {
                abstractComponentCallbacksC0165t.f1998I.setTag(string);
            }
            return abstractComponentCallbacksC0165t.f1998I;
        }
        throw new IllegalStateException("Fragment " + str2 + " did not create a view.");
    }

    public void p() {
        this.f1785r = true;
        D();
        U(0, false);
        this.f1780m = null;
        this.f1781n = null;
        this.f1782o = null;
    }

    public void q() {
        U(1, false);
    }

    public void r() {
        if (this.f1772e != null) {
            for (int i2 = 0; i2 < this.f1772e.size(); i2++) {
                AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = (AbstractComponentCallbacksC0165t) this.f1772e.get(i2);
                if (abstractComponentCallbacksC0165t != null) {
                    abstractComponentCallbacksC0165t.o0();
                }
            }
        }
    }

    public void s(boolean z2) {
        ArrayList arrayList = this.f1772e;
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = (AbstractComponentCallbacksC0165t) this.f1772e.get(size);
            if (abstractComponentCallbacksC0165t != null) {
                abstractComponentCallbacksC0165t.p0(z2);
            }
        }
    }

    public boolean t(MenuItem menuItem) {
        if (this.f1772e != null) {
            for (int i2 = 0; i2 < this.f1772e.size(); i2++) {
                AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = (AbstractComponentCallbacksC0165t) this.f1772e.get(i2);
                if (abstractComponentCallbacksC0165t != null && abstractComponentCallbacksC0165t.q0(menuItem)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = this.f1782o;
        if (abstractComponentCallbacksC0165t != null) {
            j.c.a(abstractComponentCallbacksC0165t, sb);
        } else {
            j.c.a(this.f1780m, sb);
        }
        sb.append("}}");
        return sb.toString();
    }

    public void u(Menu menu) {
        if (this.f1772e != null) {
            for (int i2 = 0; i2 < this.f1772e.size(); i2++) {
                AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = (AbstractComponentCallbacksC0165t) this.f1772e.get(i2);
                if (abstractComponentCallbacksC0165t != null) {
                    abstractComponentCallbacksC0165t.r0(menu);
                }
            }
        }
    }

    public void v() {
        U(4, false);
    }

    public void w(boolean z2) {
        ArrayList arrayList = this.f1772e;
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = (AbstractComponentCallbacksC0165t) this.f1772e.get(size);
            if (abstractComponentCallbacksC0165t != null) {
                abstractComponentCallbacksC0165t.t0(z2);
            }
        }
    }

    public boolean x(Menu menu) {
        if (this.f1772e == null) {
            return false;
        }
        boolean z2 = false;
        for (int i2 = 0; i2 < this.f1772e.size(); i2++) {
            AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = (AbstractComponentCallbacksC0165t) this.f1772e.get(i2);
            if (abstractComponentCallbacksC0165t != null && abstractComponentCallbacksC0165t.u0(menu)) {
                z2 = true;
            }
        }
        return z2;
    }

    public void y() {
        U(2, false);
    }

    public void z() {
        this.f1784q = false;
        U(5, false);
    }

    /* JADX INFO: renamed from: c.A$c */
    public static class c implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Animation.AnimationListener f1794a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1795b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public View f1796c;

        /* JADX INFO: renamed from: c.A$c$a */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                AbstractC0285k0.Q(c.this.f1796c, 0, null);
            }
        }

        public c(View view, Animation animation) {
            if (view == null || animation == null) {
                return;
            }
            this.f1796c = view;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            View view = this.f1796c;
            if (view != null && this.f1795b) {
                if (AbstractC0285k0.y(view) || AbstractC0250a.a()) {
                    this.f1796c.post(new a());
                } else {
                    AbstractC0285k0.Q(this.f1796c, 0, null);
                }
            }
            Animation.AnimationListener animationListener = this.f1794a;
            if (animationListener != null) {
                animationListener.onAnimationEnd(animation);
            }
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
            Animation.AnimationListener animationListener = this.f1794a;
            if (animationListener != null) {
                animationListener.onAnimationRepeat(animation);
            }
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            Animation.AnimationListener animationListener = this.f1794a;
            if (animationListener != null) {
                animationListener.onAnimationStart(animation);
            }
        }

        public c(View view, Animation animation, Animation.AnimationListener animationListener) {
            if (view == null || animation == null) {
                return;
            }
            this.f1794a = animationListener;
            this.f1796c = view;
            this.f1795b = true;
        }
    }

    public k.H K() {
        return this;
    }
}

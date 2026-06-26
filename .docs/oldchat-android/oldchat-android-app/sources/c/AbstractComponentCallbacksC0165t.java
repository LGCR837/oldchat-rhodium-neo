package c;

import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import k.AbstractC0253A;

/* JADX INFO: renamed from: c.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractComponentCallbacksC0165t implements ComponentCallbacks, View.OnCreateContextMenuListener {

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public static final j.j f1988X = new j.j();

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public static final Object f1989Y = new Object();

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f1990A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public boolean f1991B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public boolean f1992C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public boolean f1993D;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public boolean f1995F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public int f1996G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public ViewGroup f1997H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public View f1998I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public View f1999J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public boolean f2000K;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public C0134e0 f2002M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public boolean f2003N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public boolean f2004O;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public Object f2006Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public Object f2007R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public Object f2008S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public Object f2009T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public Object f2010U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public Boolean f2011V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public Boolean f2012W;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f2014b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2015c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Bundle f2016d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public SparseArray f2017e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f2019g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Bundle f2020h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public AbstractComponentCallbacksC0165t f2021i;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2023k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f2024l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f2025m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f2026n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f2027o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f2028p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f2029q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public C0109A f2030r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public AbstractC0173x f2031s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public C0109A f2032t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public C0110B f2033u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public AbstractComponentCallbacksC0165t f2034v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f2035w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f2036x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public String f2037y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f2038z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2013a = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2018f = -1;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2022j = -1;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public boolean f1994E = true;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public boolean f2001L = true;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public Object f2005P = null;

    /* JADX INFO: renamed from: c.t$a */
    public class a extends AbstractC0169v {
        public a() {
        }

        @Override // c.AbstractC0169v
        public View a(int i2) {
            View view = AbstractComponentCallbacksC0165t.this.f1998I;
            if (view != null) {
                return view.findViewById(i2);
            }
            throw new IllegalStateException("Fragment does not have a view");
        }

        @Override // c.AbstractC0169v
        public boolean b() {
            return AbstractComponentCallbacksC0165t.this.f1998I != null;
        }
    }

    /* JADX INFO: renamed from: c.t$b */
    public static class b extends RuntimeException {
        public b(String str, Exception exc) {
            super(str, exc);
        }
    }

    public AbstractComponentCallbacksC0165t() {
        Object obj = f1989Y;
        this.f2006Q = obj;
        this.f2007R = null;
        this.f2008S = obj;
        this.f2009T = null;
        this.f2010U = obj;
    }

    public static boolean A(Context context, String str) {
        try {
            j.j jVar = f1988X;
            Class<?> clsLoadClass = (Class) jVar.get(str);
            if (clsLoadClass == null) {
                clsLoadClass = context.getClassLoader().loadClass(str);
                jVar.put(str, clsLoadClass);
            }
            return AbstractComponentCallbacksC0165t.class.isAssignableFrom(clsLoadClass);
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static AbstractComponentCallbacksC0165t u(Context context, String str) {
        return v(context, str, null);
    }

    public static AbstractComponentCallbacksC0165t v(Context context, String str, Bundle bundle) {
        try {
            j.j jVar = f1988X;
            Class<?> clsLoadClass = (Class) jVar.get(str);
            if (clsLoadClass == null) {
                clsLoadClass = context.getClassLoader().loadClass(str);
                jVar.put(str, clsLoadClass);
            }
            AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = (AbstractComponentCallbacksC0165t) clsLoadClass.newInstance();
            if (bundle != null) {
                bundle.setClassLoader(abstractComponentCallbacksC0165t.getClass().getClassLoader());
                abstractComponentCallbacksC0165t.f2020h = bundle;
            }
            return abstractComponentCallbacksC0165t;
        } catch (ClassNotFoundException e2) {
            throw new b("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e2);
        } catch (IllegalAccessException e3) {
            throw new b("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e3);
        } catch (InstantiationException e4) {
            throw new b("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e4);
        }
    }

    public void A0(Bundle bundle) {
        Parcelable parcelable;
        if (bundle == null || (parcelable = bundle.getParcelable("android:support:fragments")) == null) {
            return;
        }
        if (this.f2032t == null) {
            w();
        }
        this.f2032t.d0(parcelable, this.f2033u);
        this.f2033u = null;
        this.f2032t.n();
    }

    public void B(Bundle bundle) {
        this.f1995F = true;
    }

    public final void B0(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = this.f2017e;
        if (sparseArray != null) {
            this.f1999J.restoreHierarchyState(sparseArray);
            this.f2017e = null;
        }
        this.f1995F = false;
        e0(bundle);
        if (this.f1995F) {
            return;
        }
        throw new F1("Fragment " + this + " did not call through to super.onViewStateRestored()");
    }

    public final void C0(int i2, AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t) {
        this.f2018f = i2;
        if (abstractComponentCallbacksC0165t == null) {
            this.f2019g = "android:fragment:" + this.f2018f;
            return;
        }
        this.f2019g = abstractComponentCallbacksC0165t.f2019g + ":" + this.f2018f;
    }

    public void D(Activity activity) {
        this.f1995F = true;
    }

    public void D0(boolean z2) {
        if (this.f1994E != z2) {
            this.f1994E = z2;
            if (this.f1993D && x() && !y()) {
                this.f2031s.u();
            }
        }
    }

    public void E(Context context) {
        this.f1995F = true;
        AbstractC0173x abstractC0173x = this.f2031s;
        Activity activityG = abstractC0173x == null ? null : abstractC0173x.g();
        if (activityG != null) {
            this.f1995F = false;
            D(activityG);
        }
    }

    public void E0(boolean z2) {
        if (!this.f2001L && z2 && this.f2013a < 4 && this.f2030r != null && x()) {
            this.f2030r.Y(this);
        }
        this.f2001L = z2;
        this.f2000K = this.f2013a < 4 && !z2;
    }

    public void F0(Intent intent) {
        G0(intent, null);
    }

    public boolean G(MenuItem menuItem) {
        return false;
    }

    public void G0(Intent intent, Bundle bundle) {
        AbstractC0173x abstractC0173x = this.f2031s;
        if (abstractC0173x != null) {
            abstractC0173x.t(this, intent, -1, bundle);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    public void H(Bundle bundle) {
        this.f1995F = true;
        A0(bundle);
        C0109A c0109a = this.f2032t;
        if (c0109a == null || c0109a.M(1)) {
            return;
        }
        this.f2032t.n();
    }

    public Animation I(int i2, boolean z2, int i3) {
        return null;
    }

    public abstract View K(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    public void L() {
        this.f1995F = true;
        if (!this.f2004O) {
            this.f2004O = true;
            this.f2002M = this.f2031s.k(this.f2019g, this.f2003N, false);
        }
        C0134e0 c0134e0 = this.f2002M;
        if (c0134e0 != null) {
            c0134e0.a();
        }
    }

    public void N() {
        this.f1995F = true;
    }

    public void O() {
        this.f1995F = true;
    }

    public void Q(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        this.f1995F = true;
    }

    public void R(Context context, AttributeSet attributeSet, Bundle bundle) {
        this.f1995F = true;
        AbstractC0173x abstractC0173x = this.f2031s;
        Activity activityG = abstractC0173x == null ? null : abstractC0173x.g();
        if (activityG != null) {
            this.f1995F = false;
            Q(activityG, attributeSet, bundle);
        }
    }

    public boolean T(MenuItem menuItem) {
        return false;
    }

    public void V() {
        this.f1995F = true;
    }

    public void Z() {
        this.f1995F = true;
    }

    public void b0() {
        this.f1995F = true;
        if (this.f2003N) {
            return;
        }
        this.f2003N = true;
        if (!this.f2004O) {
            this.f2004O = true;
            this.f2002M = this.f2031s.k(this.f2019g, true, false);
        }
        C0134e0 c0134e0 = this.f2002M;
        if (c0134e0 != null) {
            c0134e0.e();
        }
    }

    public void c(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.f2035w));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.f2036x));
        printWriter.print(" mTag=");
        printWriter.println(this.f2037y);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.f2013a);
        printWriter.print(" mIndex=");
        printWriter.print(this.f2018f);
        printWriter.print(" mWho=");
        printWriter.print(this.f2019g);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.f2029q);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.f2024l);
        printWriter.print(" mRemoving=");
        printWriter.print(this.f2025m);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.f2026n);
        printWriter.print(" mInLayout=");
        printWriter.println(this.f2027o);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.f2038z);
        printWriter.print(" mDetached=");
        printWriter.print(this.f1990A);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.f1994E);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.f1993D);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.f1991B);
        printWriter.print(" mRetaining=");
        printWriter.print(this.f1992C);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.f2001L);
        if (this.f2030r != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.f2030r);
        }
        if (this.f2031s != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.f2031s);
        }
        if (this.f2034v != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.f2034v);
        }
        if (this.f2020h != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.f2020h);
        }
        if (this.f2016d != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.f2016d);
        }
        if (this.f2017e != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.f2017e);
        }
        if (this.f2021i != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(this.f2021i);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.f2023k);
        }
        if (this.f1996G != 0) {
            printWriter.print(str);
            printWriter.print("mNextAnim=");
            printWriter.println(this.f1996G);
        }
        if (this.f1997H != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.f1997H);
        }
        if (this.f1998I != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.f1998I);
        }
        if (this.f1999J != null) {
            printWriter.print(str);
            printWriter.print("mInnerView=");
            printWriter.println(this.f1998I);
        }
        if (this.f2014b != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(this.f2014b);
            printWriter.print(str);
            printWriter.print("mStateAfterAnimating=");
            printWriter.println(this.f2015c);
        }
        if (this.f2002M != null) {
            printWriter.print(str);
            printWriter.println("Loader Manager:");
            this.f2002M.g(str + "  ", fileDescriptor, printWriter, strArr);
        }
        if (this.f2032t != null) {
            printWriter.print(str);
            printWriter.println("Child " + this.f2032t + ":");
            this.f2032t.b(str + "  ", fileDescriptor, printWriter, strArr);
        }
    }

    public void c0() {
        this.f1995F = true;
    }

    public AbstractComponentCallbacksC0165t d(String str) {
        if (str.equals(this.f2019g)) {
            return this;
        }
        C0109A c0109a = this.f2032t;
        if (c0109a != null) {
            return c0109a.H(str);
        }
        return null;
    }

    public final AbstractActivityC0167u e() {
        AbstractC0173x abstractC0173x = this.f2031s;
        if (abstractC0173x == null) {
            return null;
        }
        return (AbstractActivityC0167u) abstractC0173x.g();
    }

    public void e0(Bundle bundle) {
        this.f1995F = true;
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public boolean f() {
        Boolean bool = this.f2012W;
        if (bool == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public void f0(Bundle bundle) {
        C0109A c0109a = this.f2032t;
        if (c0109a != null) {
            c0109a.X();
        }
        this.f2013a = 2;
        this.f1995F = false;
        B(bundle);
        if (this.f1995F) {
            C0109A c0109a2 = this.f2032t;
            if (c0109a2 != null) {
                c0109a2.k();
                return;
            }
            return;
        }
        throw new F1("Fragment " + this + " did not call through to super.onActivityCreated()");
    }

    public boolean g() {
        Boolean bool = this.f2011V;
        if (bool == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public void g0(Configuration configuration) {
        onConfigurationChanged(configuration);
        C0109A c0109a = this.f2032t;
        if (c0109a != null) {
            c0109a.l(configuration);
        }
    }

    public final AbstractC0175y h() {
        if (this.f2032t == null) {
            w();
            int i2 = this.f2013a;
            if (i2 >= 5) {
                this.f2032t.z();
            } else if (i2 >= 4) {
                this.f2032t.A();
            } else if (i2 >= 2) {
                this.f2032t.k();
            } else if (i2 >= 1) {
                this.f2032t.n();
            }
        }
        return this.f2032t;
    }

    public boolean h0(MenuItem menuItem) {
        if (this.f2038z) {
            return false;
        }
        if (G(menuItem)) {
            return true;
        }
        C0109A c0109a = this.f2032t;
        return c0109a != null && c0109a.m(menuItem);
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public Object i() {
        return this.f2005P;
    }

    public void i0(Bundle bundle) {
        C0109A c0109a = this.f2032t;
        if (c0109a != null) {
            c0109a.X();
        }
        this.f2013a = 1;
        this.f1995F = false;
        H(bundle);
        if (this.f1995F) {
            return;
        }
        throw new F1("Fragment " + this + " did not call through to super.onCreate()");
    }

    public Object j() {
        return this.f2007R;
    }

    public boolean j0(Menu menu, MenuInflater menuInflater) {
        boolean z2 = false;
        if (this.f2038z) {
            return false;
        }
        if (this.f1993D && this.f1994E) {
            J(menu, menuInflater);
            z2 = true;
        }
        C0109A c0109a = this.f2032t;
        return c0109a != null ? z2 | c0109a.o(menu, menuInflater) : z2;
    }

    public LayoutInflater k(Bundle bundle) {
        LayoutInflater layoutInflaterP = this.f2031s.p();
        h();
        AbstractC0253A.b(layoutInflaterP, this.f2032t.K());
        return layoutInflaterP;
    }

    public View k0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        C0109A c0109a = this.f2032t;
        if (c0109a != null) {
            c0109a.X();
        }
        return K(layoutInflater, viewGroup, bundle);
    }

    public Object l() {
        Object obj = this.f2008S;
        return obj == f1989Y ? j() : obj;
    }

    public void l0() {
        C0109A c0109a = this.f2032t;
        if (c0109a != null) {
            c0109a.p();
        }
        this.f2013a = 0;
        this.f1995F = false;
        L();
        if (this.f1995F) {
            this.f2032t = null;
            return;
        }
        throw new F1("Fragment " + this + " did not call through to super.onDestroy()");
    }

    public final Resources m() {
        AbstractC0173x abstractC0173x = this.f2031s;
        if (abstractC0173x != null) {
            return abstractC0173x.h().getResources();
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    public void m0() {
        C0109A c0109a = this.f2032t;
        if (c0109a != null) {
            c0109a.q();
        }
        this.f2013a = 1;
        this.f1995F = false;
        N();
        if (this.f1995F) {
            C0134e0 c0134e0 = this.f2002M;
            if (c0134e0 != null) {
                c0134e0.b();
                return;
            }
            return;
        }
        throw new F1("Fragment " + this + " did not call through to super.onDestroyView()");
    }

    public Object n() {
        Object obj = this.f2006Q;
        return obj == f1989Y ? i() : obj;
    }

    public void n0() {
        this.f1995F = false;
        O();
        if (!this.f1995F) {
            throw new F1("Fragment " + this + " did not call through to super.onDetach()");
        }
        C0109A c0109a = this.f2032t;
        if (c0109a != null) {
            if (this.f1992C) {
                c0109a.p();
                this.f2032t = null;
                return;
            }
            throw new IllegalStateException("Child FragmentManager of " + this + " was not  destroyed and this fragment is not retaining instance");
        }
    }

    public Object o() {
        return this.f2009T;
    }

    public void o0() {
        onLowMemory();
        C0109A c0109a = this.f2032t;
        if (c0109a != null) {
            c0109a.r();
        }
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.f1995F = true;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        e().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.f1995F = true;
    }

    public Object p() {
        Object obj = this.f2010U;
        return obj == f1989Y ? o() : obj;
    }

    public void p0(boolean z2) {
        S(z2);
        C0109A c0109a = this.f2032t;
        if (c0109a != null) {
            c0109a.s(z2);
        }
    }

    public final String q(int i2) {
        return m().getString(i2);
    }

    public boolean q0(MenuItem menuItem) {
        if (this.f2038z) {
            return false;
        }
        if (this.f1993D && this.f1994E && T(menuItem)) {
            return true;
        }
        C0109A c0109a = this.f2032t;
        return c0109a != null && c0109a.t(menuItem);
    }

    public final String r(int i2, Object... objArr) {
        return m().getString(i2, objArr);
    }

    public void r0(Menu menu) {
        if (this.f2038z) {
            return;
        }
        if (this.f1993D && this.f1994E) {
            U(menu);
        }
        C0109A c0109a = this.f2032t;
        if (c0109a != null) {
            c0109a.u(menu);
        }
    }

    public View s() {
        return this.f1998I;
    }

    public void s0() {
        C0109A c0109a = this.f2032t;
        if (c0109a != null) {
            c0109a.v();
        }
        this.f2013a = 4;
        this.f1995F = false;
        V();
        if (this.f1995F) {
            return;
        }
        throw new F1("Fragment " + this + " did not call through to super.onPause()");
    }

    public void t() {
        this.f2018f = -1;
        this.f2019g = null;
        this.f2024l = false;
        this.f2025m = false;
        this.f2026n = false;
        this.f2027o = false;
        this.f2028p = false;
        this.f2029q = 0;
        this.f2030r = null;
        this.f2032t = null;
        this.f2031s = null;
        this.f2035w = 0;
        this.f2036x = 0;
        this.f2037y = null;
        this.f2038z = false;
        this.f1990A = false;
        this.f1992C = false;
        this.f2002M = null;
        this.f2003N = false;
        this.f2004O = false;
    }

    public void t0(boolean z2) {
        W(z2);
        C0109A c0109a = this.f2032t;
        if (c0109a != null) {
            c0109a.w(z2);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        j.c.a(this, sb);
        if (this.f2018f >= 0) {
            sb.append(" #");
            sb.append(this.f2018f);
        }
        if (this.f2035w != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.f2035w));
        }
        if (this.f2037y != null) {
            sb.append(" ");
            sb.append(this.f2037y);
        }
        sb.append('}');
        return sb.toString();
    }

    public boolean u0(Menu menu) {
        boolean z2 = false;
        if (this.f2038z) {
            return false;
        }
        if (this.f1993D && this.f1994E) {
            X(menu);
            z2 = true;
        }
        C0109A c0109a = this.f2032t;
        return c0109a != null ? z2 | c0109a.x(menu) : z2;
    }

    public void v0() {
        C0109A c0109a = this.f2032t;
        if (c0109a != null) {
            c0109a.y();
        }
        this.f2013a = 2;
        if (this.f2003N) {
            this.f2003N = false;
            if (!this.f2004O) {
                this.f2004O = true;
                this.f2002M = this.f2031s.k(this.f2019g, false, false);
            }
            if (this.f2002M != null) {
                if (this.f2031s.l()) {
                    this.f2002M.d();
                } else {
                    this.f2002M.f();
                }
            }
        }
    }

    public void w() {
        C0109A c0109a = new C0109A();
        this.f2032t = c0109a;
        c0109a.g(this.f2031s, new a(), this);
    }

    public void w0() {
        C0109A c0109a = this.f2032t;
        if (c0109a != null) {
            c0109a.X();
            this.f2032t.D();
        }
        this.f2013a = 5;
        this.f1995F = false;
        Z();
        if (!this.f1995F) {
            throw new F1("Fragment " + this + " did not call through to super.onResume()");
        }
        C0109A c0109a2 = this.f2032t;
        if (c0109a2 != null) {
            c0109a2.z();
            this.f2032t.D();
        }
    }

    public final boolean x() {
        return this.f2031s != null && this.f2024l;
    }

    public void x0(Bundle bundle) {
        Parcelable parcelableG0;
        a0(bundle);
        C0109A c0109a = this.f2032t;
        if (c0109a == null || (parcelableG0 = c0109a.g0()) == null) {
            return;
        }
        bundle.putParcelable("android:support:fragments", parcelableG0);
    }

    public final boolean y() {
        return this.f2038z;
    }

    public void y0() {
        C0109A c0109a = this.f2032t;
        if (c0109a != null) {
            c0109a.X();
            this.f2032t.D();
        }
        this.f2013a = 4;
        this.f1995F = false;
        b0();
        if (!this.f1995F) {
            throw new F1("Fragment " + this + " did not call through to super.onStart()");
        }
        C0109A c0109a2 = this.f2032t;
        if (c0109a2 != null) {
            c0109a2.A();
        }
        C0134e0 c0134e0 = this.f2002M;
        if (c0134e0 != null) {
            c0134e0.c();
        }
    }

    public final boolean z() {
        return this.f2029q > 0;
    }

    public void z0() {
        C0109A c0109a = this.f2032t;
        if (c0109a != null) {
            c0109a.B();
        }
        this.f2013a = 3;
        this.f1995F = false;
        c0();
        if (this.f1995F) {
            return;
        }
        throw new F1("Fragment " + this + " did not call through to super.onStop()");
    }

    public void M() {
    }

    public void F(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t) {
    }

    public void P(boolean z2) {
    }

    public void S(boolean z2) {
    }

    public void U(Menu menu) {
    }

    public void W(boolean z2) {
    }

    public void X(Menu menu) {
    }

    public void a0(Bundle bundle) {
    }

    public void J(Menu menu, MenuInflater menuInflater) {
    }

    public void d0(View view, Bundle bundle) {
    }

    public void C(int i2, int i3, Intent intent) {
    }

    public void Y(int i2, String[] strArr, int[] iArr) {
    }
}

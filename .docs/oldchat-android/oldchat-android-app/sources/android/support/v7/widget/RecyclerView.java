package android.support.v7.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v7.widget.b;
import android.support.v7.widget.c;
import android.support.v7.widget.o;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import c.AbstractC0177z;
import i.AbstractC0251b;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import k.AbstractC0285k0;
import k.C0255a0;
import k.InterfaceC0273g0;
import k.W;
import k.Z;
import l.AbstractC0343a;
import l.C0347e;
import l.N;
import n.C0427j;
import n.I;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import s.AbstractC0469c;

/* JADX INFO: loaded from: classes.dex */
public class RecyclerView extends ViewGroup implements InterfaceC0273g0, Z {
    public static final int[] q0 = {R.attr.nestedScrollingEnabled};
    public static final int[] r0 = {R.attr.clipToPadding};
    public static final boolean s0;
    public static final boolean t0;
    public static final Class[] u0;
    public static final Interpolator v0;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final AccessibilityManager f1282A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public List f1283B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public boolean f1284C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public int f1285D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public int f1286E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public C0427j f1287F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public C0427j f1288G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public C0427j f1289H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public C0427j f1290I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public k f1291J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public int f1292K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public int f1293L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public VelocityTracker f1294M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public int f1295N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public int f1296O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public int f1297P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public int f1298Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public int f1299R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public final int f1300S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public final int f1301T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public float f1302U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public boolean f1303V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public final A f1304W;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w f1305a;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public final y f1306a0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final u f1307b;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public s f1308b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public x f1309c;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public List f1310c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b f1311d;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public boolean f1312d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public android.support.v7.widget.c f1313e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public boolean f1314e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final android.support.v7.widget.o f1315f;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public k.b f1316f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1317g;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public boolean f1318g0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Runnable f1319h;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public android.support.v7.widget.j f1320h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Rect f1321i;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public j f1322i0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Rect f1323j;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public final int[] f1324j0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final RectF f1325k;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public C0255a0 f1326k0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public g f1327l;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public final int[] f1328l0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public n f1329m;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public final int[] f1330m0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ArrayList f1331n;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public final int[] f1332n0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final ArrayList f1333o;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public Runnable f1334o0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public r f1335p;
    public final o.b p0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f1336q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1337r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f1338s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f1339t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f1340u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f1341v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f1342w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f1343x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f1344y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final boolean f1345z;

    public class A implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1346a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1347b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public I f1348c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Interpolator f1349d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1350e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1351f;

        public A() {
            Interpolator interpolator = RecyclerView.v0;
            this.f1349d = interpolator;
            this.f1350e = false;
            this.f1351f = false;
            this.f1348c = I.d(RecyclerView.this.getContext(), interpolator);
        }

        public final int a(int i2, int i3, int i4, int i5) {
            int iRound;
            int iAbs = Math.abs(i2);
            int iAbs2 = Math.abs(i3);
            boolean z2 = iAbs > iAbs2;
            int iSqrt = (int) Math.sqrt((i4 * i4) + (i5 * i5));
            int iSqrt2 = (int) Math.sqrt((i2 * i2) + (i3 * i3));
            RecyclerView recyclerView = RecyclerView.this;
            int width = z2 ? recyclerView.getWidth() : recyclerView.getHeight();
            int i6 = width / 2;
            float f2 = width;
            float f3 = i6;
            float fC = f3 + (c(Math.min(1.0f, (iSqrt2 * 1.0f) / f2)) * f3);
            if (iSqrt > 0) {
                iRound = Math.round(Math.abs(fC / iSqrt) * 1000.0f) * 4;
            } else {
                if (!z2) {
                    iAbs = iAbs2;
                }
                iRound = (int) (((iAbs / f2) + 1.0f) * 300.0f);
            }
            return Math.min(iRound, 2000);
        }

        public final void b() {
            this.f1351f = false;
            this.f1350e = true;
        }

        public final float c(float f2) {
            Double.isNaN(f2 - 0.5f);
            return (float) Math.sin((float) (r0 * 0.4712389167638204d));
        }

        public final void d() {
            this.f1350e = false;
            if (this.f1351f) {
                f();
            }
        }

        public void e(int i2, int i3) {
            RecyclerView.this.setScrollState(2);
            this.f1347b = 0;
            this.f1346a = 0;
            this.f1348c.e(0, 0, i2, i3, PKIFailureInfo.systemUnavail, Integer.MAX_VALUE, PKIFailureInfo.systemUnavail, Integer.MAX_VALUE);
            f();
        }

        public void f() {
            if (this.f1350e) {
                this.f1351f = true;
            } else {
                RecyclerView.this.removeCallbacks(this);
                AbstractC0285k0.F(RecyclerView.this, this);
            }
        }

        public void g(int i2, int i3) {
            i(i2, i3, 0, 0);
        }

        public void h(int i2, int i3, int i4) {
            j(i2, i3, i4, RecyclerView.v0);
        }

        public void i(int i2, int i3, int i4, int i5) {
            h(i2, i3, a(i2, i3, i4, i5));
        }

        public void j(int i2, int i3, int i4, Interpolator interpolator) {
            if (this.f1349d != interpolator) {
                this.f1349d = interpolator;
                this.f1348c = I.d(RecyclerView.this.getContext(), interpolator);
            }
            RecyclerView.this.setScrollState(2);
            this.f1347b = 0;
            this.f1346a = 0;
            this.f1348c.o(0, 0, i2, i3, i4);
            f();
        }

        public void k() {
            RecyclerView.this.removeCallbacks(this);
            this.f1348c.a();
        }

        /* JADX WARN: Removed duplicated region for block: B:33:0x00b6  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00b9  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x00c0  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x00c9  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instruction units count: 318
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.A.run():void");
        }
    }

    public static abstract class B {

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public static final List f1353q = Collections.EMPTY_LIST;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final View f1354a;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f1362i;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public RecyclerView f1369p;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1355b = -1;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1356c = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f1357d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1358e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1359f = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public B f1360g = null;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public B f1361h = null;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public List f1363j = null;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public List f1364k = null;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public int f1365l = 0;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public u f1366m = null;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public boolean f1367n = false;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public int f1368o = 0;

        public B(View view) {
            if (view == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.f1354a = view;
        }

        public boolean A() {
            return (this.f1362i & 4) != 0;
        }

        public final boolean B() {
            return (this.f1362i & 16) == 0 && !AbstractC0285k0.x(this.f1354a);
        }

        public boolean C() {
            return (this.f1362i & 8) != 0;
        }

        public boolean D() {
            return this.f1366m != null;
        }

        public boolean E() {
            return (this.f1362i & 256) != 0;
        }

        public boolean F() {
            return (this.f1362i & 2) != 0;
        }

        public boolean G() {
            return (this.f1362i & 2) != 0;
        }

        public void H(int i2, boolean z2) {
            if (this.f1356c == -1) {
                this.f1356c = this.f1355b;
            }
            if (this.f1359f == -1) {
                this.f1359f = this.f1355b;
            }
            if (z2) {
                this.f1359f += i2;
            }
            this.f1355b += i2;
            if (this.f1354a.getLayoutParams() != null) {
                ((o) this.f1354a.getLayoutParams()).f1404c = true;
            }
        }

        public final void I() {
            this.f1368o = AbstractC0285k0.i(this.f1354a);
            AbstractC0285k0.P(this.f1354a, 4);
        }

        public final void J() {
            AbstractC0285k0.P(this.f1354a, this.f1368o);
            this.f1368o = 0;
        }

        public void K() {
            this.f1362i = 0;
            this.f1355b = -1;
            this.f1356c = -1;
            this.f1357d = -1L;
            this.f1359f = -1;
            this.f1365l = 0;
            this.f1360g = null;
            this.f1361h = null;
            l();
            this.f1368o = 0;
        }

        public void L() {
            if (this.f1356c == -1) {
                this.f1356c = this.f1355b;
            }
        }

        public void M(int i2, int i3) {
            this.f1362i = (i2 & i3) | (this.f1362i & (i3 ^ (-1)));
        }

        public final void N(boolean z2) {
            int i2 = this.f1365l;
            int i3 = z2 ? i2 - 1 : i2 + 1;
            this.f1365l = i3;
            if (i3 < 0) {
                this.f1365l = 0;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
                return;
            }
            if (!z2 && i3 == 1) {
                this.f1362i |= 16;
            } else if (z2 && i3 == 0) {
                this.f1362i &= -17;
            }
        }

        public void O(u uVar, boolean z2) {
            this.f1366m = uVar;
            this.f1367n = z2;
        }

        public final boolean P() {
            return (this.f1362i & 16) != 0;
        }

        public boolean Q() {
            return (this.f1362i & 128) != 0;
        }

        public void R() {
            this.f1366m.I(this);
        }

        public boolean S() {
            return (this.f1362i & 32) != 0;
        }

        public void i(Object obj) {
            if (obj == null) {
                j(1024);
            } else if ((1024 & this.f1362i) == 0) {
                o();
                this.f1363j.add(obj);
            }
        }

        public void j(int i2) {
            this.f1362i = i2 | this.f1362i;
        }

        public void k() {
            this.f1356c = -1;
            this.f1359f = -1;
        }

        public void l() {
            List list = this.f1363j;
            if (list != null) {
                list.clear();
            }
            this.f1362i &= -1025;
        }

        public void m() {
            this.f1362i &= -33;
        }

        public void n() {
            this.f1362i &= -257;
        }

        public final void o() {
            if (this.f1363j == null) {
                ArrayList arrayList = new ArrayList();
                this.f1363j = arrayList;
                this.f1364k = Collections.unmodifiableList(arrayList);
            }
        }

        public final boolean p() {
            return (this.f1362i & 16) == 0 && AbstractC0285k0.x(this.f1354a);
        }

        public void q(int i2, int i3, boolean z2) {
            j(8);
            H(i3, z2);
            this.f1355b = i2;
        }

        public final int r() {
            RecyclerView recyclerView = this.f1369p;
            if (recyclerView == null) {
                return -1;
            }
            return recyclerView.W(this);
        }

        public final long s() {
            return this.f1357d;
        }

        public final int t() {
            return this.f1358e;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("ViewHolder{" + Integer.toHexString(hashCode()) + " position=" + this.f1355b + " id=" + this.f1357d + ", oldPos=" + this.f1356c + ", pLpos:" + this.f1359f);
            if (D()) {
                sb.append(" scrap ");
                sb.append(this.f1367n ? "[changeScrap]" : "[attachedScrap]");
            }
            if (A()) {
                sb.append(" invalid");
            }
            if (!z()) {
                sb.append(" unbound");
            }
            if (G()) {
                sb.append(" update");
            }
            if (C()) {
                sb.append(" removed");
            }
            if (Q()) {
                sb.append(" ignored");
            }
            if (E()) {
                sb.append(" tmpDetached");
            }
            if (!B()) {
                sb.append(" not recyclable(" + this.f1365l + ")");
            }
            if (y()) {
                sb.append(" undefined adapter position");
            }
            if (this.f1354a.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }

        public final int u() {
            int i2 = this.f1359f;
            return i2 == -1 ? this.f1355b : i2;
        }

        public final int v() {
            return this.f1356c;
        }

        public List w() {
            if ((this.f1362i & 1024) != 0) {
                return f1353q;
            }
            List list = this.f1363j;
            return (list == null || list.size() == 0) ? f1353q : this.f1364k;
        }

        public boolean x(int i2) {
            return (i2 & this.f1362i) != 0;
        }

        public boolean y() {
            return (this.f1362i & 512) != 0 || A();
        }

        public boolean z() {
            return (this.f1362i & 1) != 0;
        }
    }

    /* JADX INFO: renamed from: android.support.v7.widget.RecyclerView$a, reason: case insensitive filesystem */
    public class RunnableC0094a implements Runnable {
        public RunnableC0094a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RecyclerView recyclerView = RecyclerView.this;
            if (!recyclerView.f1338s || recyclerView.isLayoutRequested()) {
                return;
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            if (!recyclerView2.f1336q) {
                recyclerView2.requestLayout();
            } else if (recyclerView2.f1341v) {
                recyclerView2.f1340u = true;
            } else {
                recyclerView2.u();
            }
        }
    }

    /* JADX INFO: renamed from: android.support.v7.widget.RecyclerView$b, reason: case insensitive filesystem */
    public class RunnableC0095b implements Runnable {
        public RunnableC0095b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            k kVar = RecyclerView.this.f1291J;
            if (kVar != null) {
                kVar.v();
            }
            RecyclerView.this.f1318g0 = false;
        }
    }

    public static class c implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3 = f2 - 1.0f;
            return (f3 * f3 * f3 * f3 * f3) + 1.0f;
        }
    }

    public class d implements o.b {
        public d() {
        }

        @Override // android.support.v7.widget.o.b
        public void a(B b2) {
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.f1329m.e1(b2.f1354a, recyclerView.f1307b);
        }

        @Override // android.support.v7.widget.o.b
        public void b(B b2, k.c cVar, k.c cVar2) {
            RecyclerView.this.m(b2, cVar, cVar2);
        }

        @Override // android.support.v7.widget.o.b
        public void c(B b2, k.c cVar, k.c cVar2) {
            b2.N(false);
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.f1284C) {
                if (recyclerView.f1291J.b(b2, b2, cVar, cVar2)) {
                    RecyclerView.this.B0();
                }
            } else if (recyclerView.f1291J.d(b2, cVar, cVar2)) {
                RecyclerView.this.B0();
            }
        }

        @Override // android.support.v7.widget.o.b
        public void d(B b2, k.c cVar, k.c cVar2) {
            RecyclerView.this.f1307b.I(b2);
            RecyclerView.this.o(b2, cVar, cVar2);
        }
    }

    public class e implements c.b {
        public e() {
        }

        @Override // android.support.v7.widget.c.b
        public void a(View view) {
            B bZ = RecyclerView.Z(view);
            if (bZ != null) {
                bZ.I();
            }
        }

        @Override // android.support.v7.widget.c.b
        public View b(int i2) {
            return RecyclerView.this.getChildAt(i2);
        }

        @Override // android.support.v7.widget.c.b
        public void c(int i2) {
            View childAt = RecyclerView.this.getChildAt(i2);
            if (childAt != null) {
                RecyclerView.this.z(childAt);
            }
            RecyclerView.this.removeViewAt(i2);
        }

        @Override // android.support.v7.widget.c.b
        public void d() {
            int iK = k();
            for (int i2 = 0; i2 < iK; i2++) {
                RecyclerView.this.z(b(i2));
            }
            RecyclerView.this.removeAllViews();
        }

        @Override // android.support.v7.widget.c.b
        public B e(View view) {
            return RecyclerView.Z(view);
        }

        @Override // android.support.v7.widget.c.b
        public void f(int i2) {
            B bZ;
            View viewB = b(i2);
            if (viewB != null && (bZ = RecyclerView.Z(viewB)) != null) {
                if (bZ.E() && !bZ.Q()) {
                    throw new IllegalArgumentException("called detach on an already detached child " + bZ);
                }
                bZ.j(256);
            }
            RecyclerView.this.detachViewFromParent(i2);
        }

        @Override // android.support.v7.widget.c.b
        public void g(View view) {
            B bZ = RecyclerView.Z(view);
            if (bZ != null) {
                bZ.J();
            }
        }

        @Override // android.support.v7.widget.c.b
        public void h(View view, int i2, ViewGroup.LayoutParams layoutParams) {
            B bZ = RecyclerView.Z(view);
            if (bZ != null) {
                if (!bZ.E() && !bZ.Q()) {
                    throw new IllegalArgumentException("Called attach on a child which is not detached: " + bZ);
                }
                bZ.n();
            }
            RecyclerView.this.attachViewToParent(view, i2, layoutParams);
        }

        @Override // android.support.v7.widget.c.b
        public void i(View view, int i2) {
            RecyclerView.this.addView(view, i2);
            RecyclerView.this.y(view);
        }

        @Override // android.support.v7.widget.c.b
        public int j(View view) {
            return RecyclerView.this.indexOfChild(view);
        }

        @Override // android.support.v7.widget.c.b
        public int k() {
            return RecyclerView.this.getChildCount();
        }
    }

    public class f implements b.a {
        public f() {
        }

        @Override // android.support.v7.widget.b.a
        public void a(b.C0015b c0015b) {
            i(c0015b);
        }

        @Override // android.support.v7.widget.b.a
        public void b(int i2, int i3) {
            RecyclerView.this.s0(i2, i3);
            RecyclerView.this.f1312d0 = true;
        }

        @Override // android.support.v7.widget.b.a
        public B c(int i2) {
            B bU = RecyclerView.this.U(i2, true);
            if (bU == null || RecyclerView.this.f1313e.n(bU.f1354a)) {
                return null;
            }
            return bU;
        }

        @Override // android.support.v7.widget.b.a
        public void d(int i2, int i3) {
            RecyclerView.this.t0(i2, i3, true);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.f1312d0 = true;
            recyclerView.f1306a0.f1423f += i3;
        }

        @Override // android.support.v7.widget.b.a
        public void e(int i2, int i3) {
            RecyclerView.this.t0(i2, i3, false);
            RecyclerView.this.f1312d0 = true;
        }

        @Override // android.support.v7.widget.b.a
        public void f(b.C0015b c0015b) {
            i(c0015b);
        }

        @Override // android.support.v7.widget.b.a
        public void g(int i2, int i3) {
            RecyclerView.this.r0(i2, i3);
            RecyclerView.this.f1312d0 = true;
        }

        @Override // android.support.v7.widget.b.a
        public void h(int i2, int i3, Object obj) {
            RecyclerView.this.a1(i2, i3, obj);
            RecyclerView.this.f1314e0 = true;
        }

        public void i(b.C0015b c0015b) {
            int i2 = c0015b.f1625a;
            if (i2 == 1) {
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.f1329m.K0(recyclerView, c0015b.f1626b, c0015b.f1628d);
                return;
            }
            if (i2 == 2) {
                RecyclerView recyclerView2 = RecyclerView.this;
                recyclerView2.f1329m.N0(recyclerView2, c0015b.f1626b, c0015b.f1628d);
            } else if (i2 == 4) {
                RecyclerView recyclerView3 = RecyclerView.this;
                recyclerView3.f1329m.P0(recyclerView3, c0015b.f1626b, c0015b.f1628d, c0015b.f1627c);
            } else {
                if (i2 != 8) {
                    return;
                }
                RecyclerView recyclerView4 = RecyclerView.this;
                recyclerView4.f1329m.M0(recyclerView4, c0015b.f1626b, c0015b.f1628d, 1);
            }
        }
    }

    public static abstract class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final h f1375a = new h();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1376b = false;

        public final void a(B b2, int i2) {
            b2.f1355b = i2;
            if (f()) {
                b2.f1357d = d(i2);
            }
            b2.M(1, 519);
            i.f.a("RV OnBindView");
            m(b2, i2, b2.w());
            b2.l();
            ViewGroup.LayoutParams layoutParams = b2.f1354a.getLayoutParams();
            if (layoutParams instanceof o) {
                ((o) layoutParams).f1404c = true;
            }
            i.f.b();
        }

        public final B b(ViewGroup viewGroup, int i2) {
            i.f.a("RV CreateView");
            B bN = n(viewGroup, i2);
            bN.f1358e = i2;
            i.f.b();
            return bN;
        }

        public abstract int c();

        public long d(int i2) {
            return -1L;
        }

        public int e(int i2) {
            return 0;
        }

        public final boolean f() {
            return this.f1376b;
        }

        public final void g() {
            this.f1375a.a();
        }

        public final void h(int i2) {
            this.f1375a.c(i2, 1);
        }

        public final void i(int i2, int i3) {
            this.f1375a.b(i2, i3);
        }

        public final void j(int i2, int i3) {
            this.f1375a.e(i2, i3);
        }

        public void k(RecyclerView recyclerView) {
        }

        public abstract void l(B b2, int i2);

        public void m(B b2, int i2, List list) {
            l(b2, i2);
        }

        public abstract B n(ViewGroup viewGroup, int i2);

        public void o(RecyclerView recyclerView) {
        }

        public boolean p(B b2) {
            return false;
        }

        public void q(B b2) {
        }

        public void r(B b2) {
        }

        public void s(B b2) {
        }

        public void t(i iVar) {
            this.f1375a.registerObserver(iVar);
        }

        public void u(i iVar) {
            this.f1375a.unregisterObserver(iVar);
        }
    }

    public static class h extends Observable {
        public void a() {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((i) ((Observable) this).mObservers.get(size)).a();
            }
        }

        public void b(int i2, int i3) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((i) ((Observable) this).mObservers.get(size)).d(i2, i3, 1);
            }
        }

        public void c(int i2, int i3) {
            d(i2, i3, null);
        }

        public void d(int i2, int i3, Object obj) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((i) ((Observable) this).mObservers.get(size)).b(i2, i3, obj);
            }
        }

        public void e(int i2, int i3) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((i) ((Observable) this).mObservers.get(size)).c(i2, i3);
            }
        }
    }

    public static abstract class i {
        public abstract void a();

        public abstract void b(int i2, int i3, Object obj);

        public abstract void c(int i2, int i3);

        public abstract void d(int i2, int i3, int i4);
    }

    public interface j {
        int a(int i2, int i3);
    }

    public static abstract class k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public b f1377a = null;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList f1378b = new ArrayList();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f1379c = 120;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f1380d = 120;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f1381e = 250;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f1382f = 250;

        public interface a {
            void a();
        }

        public interface b {
            void a(B b2);
        }

        public static class c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f1383a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f1384b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f1385c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public int f1386d;

            public c a(B b2) {
                return b(b2, 0);
            }

            public c b(B b2, int i2) {
                View view = b2.f1354a;
                this.f1383a = view.getLeft();
                this.f1384b = view.getTop();
                this.f1385c = view.getRight();
                this.f1386d = view.getBottom();
                return this;
            }
        }

        public static int e(B b2) {
            int i2 = b2.f1362i;
            int i3 = i2 & 14;
            if (b2.A()) {
                return 4;
            }
            if ((i2 & 4) != 0) {
                return i3;
            }
            int iV = b2.v();
            int iR = b2.r();
            return (iV == -1 || iR == -1 || iV == iR) ? i3 : i3 | 2048;
        }

        public abstract boolean a(B b2, c cVar, c cVar2);

        public abstract boolean b(B b2, B b3, c cVar, c cVar2);

        public abstract boolean c(B b2, c cVar, c cVar2);

        public abstract boolean d(B b2, c cVar, c cVar2);

        public abstract boolean f(B b2);

        public boolean g(B b2, List list) {
            return f(b2);
        }

        public final void h(B b2) {
            s(b2);
            b bVar = this.f1377a;
            if (bVar != null) {
                bVar.a(b2);
            }
        }

        public final void i() {
            if (this.f1378b.size() <= 0) {
                this.f1378b.clear();
            } else {
                AbstractC0177z.a(this.f1378b.get(0));
                throw null;
            }
        }

        public abstract void j(B b2);

        public abstract void k();

        public long l() {
            return this.f1379c;
        }

        public long m() {
            return this.f1382f;
        }

        public long n() {
            return this.f1381e;
        }

        public long o() {
            return this.f1380d;
        }

        public abstract boolean p();

        public final boolean q(a aVar) {
            boolean zP = p();
            if (aVar != null) {
                if (zP) {
                    this.f1378b.add(aVar);
                } else {
                    aVar.a();
                }
            }
            return zP;
        }

        public c r() {
            return new c();
        }

        public void s(B b2) {
        }

        public c t(y yVar, B b2) {
            return r().a(b2);
        }

        public c u(y yVar, B b2, int i2, List list) {
            return r().a(b2);
        }

        public abstract void v();

        public void w(b bVar) {
            this.f1377a = bVar;
        }
    }

    public class l implements k.b {
        public l() {
        }

        @Override // android.support.v7.widget.RecyclerView.k.b
        public void a(B b2) {
            b2.N(true);
            if (b2.f1360g != null && b2.f1361h == null) {
                b2.f1360g = null;
            }
            b2.f1361h = null;
            if (b2.P() || RecyclerView.this.I0(b2.f1354a) || !b2.E()) {
                return;
            }
            RecyclerView.this.removeDetachedView(b2.f1354a, false);
        }
    }

    public static abstract class m {
        public abstract void c(Rect rect, View view, RecyclerView recyclerView, y yVar);

        public abstract void d(Canvas canvas, RecyclerView recyclerView, y yVar);

        public abstract void e(Canvas canvas, RecyclerView recyclerView, y yVar);
    }

    public static abstract class n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public android.support.v7.widget.c f1388a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public RecyclerView f1389b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1390c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1391d = false;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1392e = false;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1393f = true;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1394g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f1395h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f1396i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f1397j;

        public static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f1398a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f1399b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public boolean f1400c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public boolean f1401d;
        }

        public static int I(int i2, int i3, int i4, int i5, boolean z2) {
            int iMax = Math.max(0, i2 - i4);
            if (i5 >= 0) {
                i3 = 1073741824;
            } else if (!z2) {
                if (i5 != -1) {
                    if (i5 == -2) {
                        i3 = (i3 == Integer.MIN_VALUE || i3 == 1073741824) ? PKIFailureInfo.systemUnavail : 0;
                    }
                    i3 = 0;
                    i5 = 0;
                }
                i5 = iMax;
            } else if (i5 != -1 || (i3 != Integer.MIN_VALUE && (i3 == 0 || i3 != 1073741824))) {
                i3 = 0;
                i5 = 0;
            } else {
                i5 = iMax;
            }
            return View.MeasureSpec.makeMeasureSpec(i5, i3);
        }

        public static a f0(Context context, AttributeSet attributeSet, int i2, int i3) {
            a aVar = new a();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0469c.f7913a, i2, i3);
            aVar.f1398a = typedArrayObtainStyledAttributes.getInt(AbstractC0469c.f7914b, 1);
            aVar.f1399b = typedArrayObtainStyledAttributes.getInt(AbstractC0469c.f7918f, 1);
            aVar.f1400c = typedArrayObtainStyledAttributes.getBoolean(AbstractC0469c.f7917e, false);
            aVar.f1401d = typedArrayObtainStyledAttributes.getBoolean(AbstractC0469c.f7919g, false);
            typedArrayObtainStyledAttributes.recycle();
            return aVar;
        }

        public static int n(int i2, int i3, int i4) {
            int mode = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i3, i4) : size : Math.min(size, Math.max(i3, i4));
        }

        public static boolean q0(int i2, int i3, int i4) {
            int mode = View.MeasureSpec.getMode(i3);
            int size = View.MeasureSpec.getSize(i3);
            if (i4 > 0 && i2 != i4) {
                return false;
            }
            if (mode == Integer.MIN_VALUE) {
                return size >= i2;
            }
            if (mode != 0) {
                return mode == 1073741824 && size == i2;
            }
            return true;
        }

        public View A(int i2) {
            int iH = H();
            for (int i3 = 0; i3 < iH; i3++) {
                View viewG = G(i3);
                B bZ = RecyclerView.Z(viewG);
                if (bZ != null && bZ.u() == i2 && !bZ.Q() && (this.f1389b.f1306a0.e() || !bZ.C())) {
                    return viewG;
                }
            }
            return null;
        }

        public void A0(RecyclerView recyclerView) {
        }

        public void A1() {
        }

        public abstract o B();

        public void B0(RecyclerView recyclerView, u uVar) {
            A0(recyclerView);
        }

        public abstract boolean B1();

        public o C(Context context, AttributeSet attributeSet) {
            return new o(context, attributeSet);
        }

        public abstract View C0(View view, int i2, u uVar, y yVar);

        public o D(ViewGroup.LayoutParams layoutParams) {
            return layoutParams instanceof o ? new o((o) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new o((ViewGroup.MarginLayoutParams) layoutParams) : new o(layoutParams);
        }

        public void D0(u uVar, y yVar, AccessibilityEvent accessibilityEvent) {
            N nA = AbstractC0343a.a(accessibilityEvent);
            RecyclerView recyclerView = this.f1389b;
            if (recyclerView == null || nA == null) {
                return;
            }
            boolean z2 = true;
            if (!AbstractC0285k0.c(recyclerView, 1) && !AbstractC0285k0.c(this.f1389b, -1) && !AbstractC0285k0.b(this.f1389b, -1) && !AbstractC0285k0.b(this.f1389b, 1)) {
                z2 = false;
            }
            nA.g(z2);
            g gVar = this.f1389b.f1327l;
            if (gVar != null) {
                nA.b(gVar.c());
            }
        }

        public int E() {
            return -1;
        }

        public void E0(AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f1389b;
            D0(recyclerView.f1307b, recyclerView.f1306a0, accessibilityEvent);
        }

        public int F(View view) {
            return ((o) view.getLayoutParams()).f1403b.bottom;
        }

        public void F0(u uVar, y yVar, C0347e c0347e) {
            if (AbstractC0285k0.c(this.f1389b, -1) || AbstractC0285k0.b(this.f1389b, -1)) {
                c0347e.a(PKIFailureInfo.certRevoked);
                c0347e.y(true);
            }
            if (AbstractC0285k0.c(this.f1389b, 1) || AbstractC0285k0.b(this.f1389b, 1)) {
                c0347e.a(PKIFailureInfo.certConfirmed);
                c0347e.y(true);
            }
            c0347e.w(C0347e.l.a(h0(uVar, yVar), K(uVar, yVar), p0(uVar, yVar), i0(uVar, yVar)));
        }

        public View G(int i2) {
            android.support.v7.widget.c cVar = this.f1388a;
            if (cVar != null) {
                return cVar.f(i2);
            }
            return null;
        }

        public void G0(C0347e c0347e) {
            RecyclerView recyclerView = this.f1389b;
            F0(recyclerView.f1307b, recyclerView.f1306a0, c0347e);
        }

        public int H() {
            android.support.v7.widget.c cVar = this.f1388a;
            if (cVar != null) {
                return cVar.g();
            }
            return 0;
        }

        public void H0(u uVar, y yVar, View view, C0347e c0347e) {
            c0347e.x(C0347e.m.a(l() ? e0(view) : 0, 1, k() ? e0(view) : 0, 1, false, false));
        }

        public void I0(View view, C0347e c0347e) {
            B bZ = RecyclerView.Z(view);
            if (bZ == null || bZ.C() || this.f1388a.n(bZ.f1354a)) {
                return;
            }
            RecyclerView recyclerView = this.f1389b;
            H0(recyclerView.f1307b, recyclerView.f1306a0, view, c0347e);
        }

        public boolean J() {
            RecyclerView recyclerView = this.f1389b;
            return recyclerView != null && recyclerView.f1317g;
        }

        public View J0(View view, int i2) {
            return null;
        }

        public int K(u uVar, y yVar) {
            RecyclerView recyclerView = this.f1389b;
            if (recyclerView == null || recyclerView.f1327l == null || !k()) {
                return 1;
            }
            return this.f1389b.f1327l.c();
        }

        public void K0(RecyclerView recyclerView, int i2, int i3) {
        }

        public int L(View view) {
            return view.getBottom() + F(view);
        }

        public void L0(RecyclerView recyclerView) {
        }

        public void M(View view, Rect rect) {
            o oVar = (o) view.getLayoutParams();
            Rect rect2 = oVar.f1403b;
            rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) oVar).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) oVar).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) oVar).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) oVar).bottomMargin);
        }

        public void M0(RecyclerView recyclerView, int i2, int i3, int i4) {
        }

        public int N(View view) {
            return view.getLeft() - X(view);
        }

        public void N0(RecyclerView recyclerView, int i2, int i3) {
        }

        public int O(View view) {
            Rect rect = ((o) view.getLayoutParams()).f1403b;
            return view.getMeasuredHeight() + rect.top + rect.bottom;
        }

        public void O0(RecyclerView recyclerView, int i2, int i3) {
        }

        public int P(View view) {
            Rect rect = ((o) view.getLayoutParams()).f1403b;
            return view.getMeasuredWidth() + rect.left + rect.right;
        }

        public void P0(RecyclerView recyclerView, int i2, int i3, Object obj) {
            O0(recyclerView, i2, i3);
        }

        public int Q(View view) {
            return view.getRight() + g0(view);
        }

        public abstract void Q0(u uVar, y yVar);

        public int R(View view) {
            return view.getTop() - j0(view);
        }

        public void R0(y yVar) {
        }

        public View S() {
            View focusedChild;
            RecyclerView recyclerView = this.f1389b;
            if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.f1388a.n(focusedChild)) {
                return null;
            }
            return focusedChild;
        }

        public void S0(u uVar, y yVar, int i2, int i3) {
            this.f1389b.w(i2, i3);
        }

        public int T() {
            return this.f1397j;
        }

        public boolean T0(RecyclerView recyclerView, y yVar, View view, View view2) {
            return U0(recyclerView, view, view2);
        }

        public int U() {
            return this.f1395h;
        }

        public boolean U0(RecyclerView recyclerView, View view, View view2) {
            return r0() || recyclerView.k0();
        }

        public int V() {
            RecyclerView recyclerView = this.f1389b;
            g adapter = recyclerView != null ? recyclerView.getAdapter() : null;
            if (adapter != null) {
                return adapter.c();
            }
            return 0;
        }

        public abstract void V0(Parcelable parcelable);

        public int W() {
            return AbstractC0285k0.k(this.f1389b);
        }

        public abstract Parcelable W0();

        public int X(View view) {
            return ((o) view.getLayoutParams()).f1403b.left;
        }

        public void X0(int i2) {
        }

        public int Y() {
            return AbstractC0285k0.o(this.f1389b);
        }

        public boolean Y0(int i2, Bundle bundle) {
            RecyclerView recyclerView = this.f1389b;
            return Z0(recyclerView.f1307b, recyclerView.f1306a0, i2, bundle);
        }

        public int Z() {
            return AbstractC0285k0.p(this.f1389b);
        }

        public boolean Z0(u uVar, y yVar, int i2, Bundle bundle) {
            int iT;
            int iL0;
            RecyclerView recyclerView = this.f1389b;
            if (recyclerView == null) {
                return false;
            }
            if (i2 != 4096) {
                if (i2 != 8192) {
                    iT = 0;
                } else {
                    iT = AbstractC0285k0.c(recyclerView, -1) ? -((T() - d0()) - a0()) : 0;
                    if (AbstractC0285k0.b(this.f1389b, -1)) {
                        iL0 = -((l0() - b0()) - c0());
                    }
                }
            } else {
                iT = AbstractC0285k0.c(recyclerView, 1) ? (T() - d0()) - a0() : 0;
                iL0 = AbstractC0285k0.b(this.f1389b, 1) ? (l0() - b0()) - c0() : 0;
            }
            if (iT == 0 && iL0 == 0) {
                return false;
            }
            this.f1389b.scrollBy(iL0, iT);
            return true;
        }

        public int a0() {
            RecyclerView recyclerView = this.f1389b;
            if (recyclerView != null) {
                return recyclerView.getPaddingBottom();
            }
            return 0;
        }

        public boolean a1(u uVar, y yVar, View view, int i2, Bundle bundle) {
            return false;
        }

        public void b(View view) {
            c(view, -1);
        }

        public int b0() {
            RecyclerView recyclerView = this.f1389b;
            if (recyclerView != null) {
                return recyclerView.getPaddingLeft();
            }
            return 0;
        }

        public boolean b1(View view, int i2, Bundle bundle) {
            RecyclerView recyclerView = this.f1389b;
            return a1(recyclerView.f1307b, recyclerView.f1306a0, view, i2, bundle);
        }

        public void c(View view, int i2) {
            f(view, i2, true);
        }

        public int c0() {
            RecyclerView recyclerView = this.f1389b;
            if (recyclerView != null) {
                return recyclerView.getPaddingRight();
            }
            return 0;
        }

        public void c1(u uVar) {
            for (int iH = H() - 1; iH >= 0; iH--) {
                if (!RecyclerView.Z(G(iH)).Q()) {
                    f1(iH, uVar);
                }
            }
        }

        public void d(View view) {
            e(view, -1);
        }

        public int d0() {
            RecyclerView recyclerView = this.f1389b;
            if (recyclerView != null) {
                return recyclerView.getPaddingTop();
            }
            return 0;
        }

        public void d1(u uVar) {
            int iJ = uVar.j();
            for (int i2 = iJ - 1; i2 >= 0; i2--) {
                View viewL = uVar.l(i2);
                B bZ = RecyclerView.Z(viewL);
                if (!bZ.Q()) {
                    bZ.N(false);
                    if (bZ.E()) {
                        this.f1389b.removeDetachedView(viewL, false);
                    }
                    k kVar = this.f1389b.f1291J;
                    if (kVar != null) {
                        kVar.j(bZ);
                    }
                    bZ.N(true);
                    uVar.y(viewL);
                }
            }
            uVar.e();
            if (iJ > 0) {
                this.f1389b.invalidate();
            }
        }

        public void e(View view, int i2) {
            f(view, i2, false);
        }

        public int e0(View view) {
            return ((o) view.getLayoutParams()).a();
        }

        public void e1(View view, u uVar) {
            h1(view);
            uVar.B(view);
        }

        public final void f(View view, int i2, boolean z2) {
            B bZ = RecyclerView.Z(view);
            if (z2 || bZ.C()) {
                this.f1389b.f1315f.b(bZ);
            } else {
                this.f1389b.f1315f.p(bZ);
            }
            o oVar = (o) view.getLayoutParams();
            if (bZ.S() || bZ.D()) {
                if (bZ.D()) {
                    bZ.R();
                } else {
                    bZ.m();
                }
                this.f1388a.c(view, i2, view.getLayoutParams(), false);
            } else if (view.getParent() == this.f1389b) {
                int iM = this.f1388a.m(view);
                if (i2 == -1) {
                    i2 = this.f1388a.g();
                }
                if (iM == -1) {
                    throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.f1389b.indexOfChild(view));
                }
                if (iM != i2) {
                    this.f1389b.f1329m.u0(iM, i2);
                }
            } else {
                this.f1388a.a(view, i2, false);
                oVar.f1404c = true;
            }
            if (oVar.f1405d) {
                bZ.f1354a.invalidate();
                oVar.f1405d = false;
            }
        }

        public void f1(int i2, u uVar) {
            View viewG = G(i2);
            i1(i2);
            uVar.B(viewG);
        }

        public void g(String str) {
            RecyclerView recyclerView = this.f1389b;
            if (recyclerView != null) {
                recyclerView.p(str);
            }
        }

        public int g0(View view) {
            return ((o) view.getLayoutParams()).f1403b.right;
        }

        public boolean g1(Runnable runnable) {
            RecyclerView recyclerView = this.f1389b;
            if (recyclerView != null) {
                return recyclerView.removeCallbacks(runnable);
            }
            return false;
        }

        public void h(View view, int i2) {
            i(view, i2, (o) view.getLayoutParams());
        }

        public int h0(u uVar, y yVar) {
            RecyclerView recyclerView = this.f1389b;
            if (recyclerView == null || recyclerView.f1327l == null || !l()) {
                return 1;
            }
            return this.f1389b.f1327l.c();
        }

        public void h1(View view) {
            this.f1388a.p(view);
        }

        public void i(View view, int i2, o oVar) {
            B bZ = RecyclerView.Z(view);
            if (bZ.C()) {
                this.f1389b.f1315f.b(bZ);
            } else {
                this.f1389b.f1315f.p(bZ);
            }
            this.f1388a.c(view, i2, oVar, bZ.C());
        }

        public int i0(u uVar, y yVar) {
            return 0;
        }

        public void i1(int i2) {
            if (G(i2) != null) {
                this.f1388a.q(i2);
            }
        }

        public void j(View view, Rect rect) {
            RecyclerView recyclerView = this.f1389b;
            if (recyclerView == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(recyclerView.c0(view));
            }
        }

        public int j0(View view) {
            return ((o) view.getLayoutParams()).f1403b.top;
        }

        public boolean j1(RecyclerView recyclerView, View view, Rect rect, boolean z2) {
            int iB0 = b0();
            int iD0 = d0();
            int iL0 = l0() - c0();
            int iT = T() - a0();
            int left = (view.getLeft() + rect.left) - view.getScrollX();
            int top = (view.getTop() + rect.top) - view.getScrollY();
            int iWidth = rect.width() + left;
            int iHeight = rect.height() + top;
            int i2 = left - iB0;
            int iMin = Math.min(0, i2);
            int i3 = top - iD0;
            int iMin2 = Math.min(0, i3);
            int i4 = iWidth - iL0;
            int iMax = Math.max(0, i4);
            int iMax2 = Math.max(0, iHeight - iT);
            if (W() != 1) {
                if (iMin == 0) {
                    iMin = Math.min(i2, iMax);
                }
                iMax = iMin;
            } else if (iMax == 0) {
                iMax = Math.max(iMin, i4);
            }
            if (iMin2 == 0) {
                iMin2 = Math.min(i3, iMax2);
            }
            if (iMax == 0 && iMin2 == 0) {
                return false;
            }
            if (z2) {
                recyclerView.scrollBy(iMax, iMin2);
            } else {
                recyclerView.X0(iMax, iMin2);
            }
            return true;
        }

        public abstract boolean k();

        public void k0(View view, boolean z2, Rect rect) {
            Matrix matrixL;
            if (z2) {
                Rect rect2 = ((o) view.getLayoutParams()).f1403b;
                rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
            } else {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if (this.f1389b != null && (matrixL = AbstractC0285k0.l(view)) != null && !matrixL.isIdentity()) {
                RectF rectF = this.f1389b.f1325k;
                rectF.set(rect);
                matrixL.mapRect(rectF);
                rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        public void k1() {
            RecyclerView recyclerView = this.f1389b;
            if (recyclerView != null) {
                recyclerView.requestLayout();
            }
        }

        public abstract boolean l();

        public int l0() {
            return this.f1396i;
        }

        public void l1() {
            this.f1390c = true;
        }

        public boolean m(o oVar) {
            return oVar != null;
        }

        public int m0() {
            return this.f1394g;
        }

        public final void m1(u uVar, int i2, View view) {
            B bZ = RecyclerView.Z(view);
            if (bZ.Q()) {
                return;
            }
            if (bZ.A() && !bZ.C() && !this.f1389b.f1327l.f()) {
                i1(i2);
                uVar.C(bZ);
            } else {
                v(i2);
                uVar.D(view);
                this.f1389b.f1315f.k(bZ);
            }
        }

        public boolean n0() {
            int iH = H();
            for (int i2 = 0; i2 < iH; i2++) {
                ViewGroup.LayoutParams layoutParams = G(i2).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
            return false;
        }

        public abstract int n1(int i2, u uVar, y yVar);

        public abstract int o(y yVar);

        public boolean o0() {
            return this.f1391d;
        }

        public abstract void o1(int i2);

        public abstract int p(y yVar);

        public boolean p0(u uVar, y yVar) {
            return false;
        }

        public abstract int p1(int i2, u uVar, y yVar);

        public abstract int q(y yVar);

        public void q1(boolean z2) {
            this.f1392e = z2;
        }

        public abstract int r(y yVar);

        public boolean r0() {
            return false;
        }

        public void r1(RecyclerView recyclerView) {
            s1(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
        }

        public abstract int s(y yVar);

        public void s0(View view, int i2, int i3, int i4, int i5) {
            o oVar = (o) view.getLayoutParams();
            Rect rect = oVar.f1403b;
            view.layout(i2 + rect.left + ((ViewGroup.MarginLayoutParams) oVar).leftMargin, i3 + rect.top + ((ViewGroup.MarginLayoutParams) oVar).topMargin, (i4 - rect.right) - ((ViewGroup.MarginLayoutParams) oVar).rightMargin, (i5 - rect.bottom) - ((ViewGroup.MarginLayoutParams) oVar).bottomMargin);
        }

        public void s1(int i2, int i3) {
            this.f1396i = View.MeasureSpec.getSize(i2);
            int mode = View.MeasureSpec.getMode(i2);
            this.f1394g = mode;
            if (mode == 0 && !RecyclerView.t0) {
                this.f1396i = 0;
            }
            this.f1397j = View.MeasureSpec.getSize(i3);
            int mode2 = View.MeasureSpec.getMode(i3);
            this.f1395h = mode2;
            if (mode2 != 0 || RecyclerView.t0) {
                return;
            }
            this.f1397j = 0;
        }

        public abstract int t(y yVar);

        public void t0(View view, int i2, int i3) {
            o oVar = (o) view.getLayoutParams();
            Rect rectC0 = this.f1389b.c0(view);
            int i4 = i2 + rectC0.left + rectC0.right;
            int i5 = i3 + rectC0.top + rectC0.bottom;
            int I2 = I(l0(), m0(), b0() + c0() + ((ViewGroup.MarginLayoutParams) oVar).leftMargin + ((ViewGroup.MarginLayoutParams) oVar).rightMargin + i4, ((ViewGroup.MarginLayoutParams) oVar).width, k());
            int I3 = I(T(), U(), d0() + a0() + ((ViewGroup.MarginLayoutParams) oVar).topMargin + ((ViewGroup.MarginLayoutParams) oVar).bottomMargin + i5, ((ViewGroup.MarginLayoutParams) oVar).height, l());
            if (x1(view, I2, I3, oVar)) {
                view.measure(I2, I3);
            }
        }

        public void t1(int i2, int i3) {
            this.f1389b.setMeasuredDimension(i2, i3);
        }

        public void u(u uVar) {
            for (int iH = H() - 1; iH >= 0; iH--) {
                m1(uVar, iH, G(iH));
            }
        }

        public void u0(int i2, int i3) {
            View viewG = G(i2);
            if (viewG != null) {
                v(i2);
                h(viewG, i3);
            } else {
                throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i2);
            }
        }

        public void u1(Rect rect, int i2, int i3) {
            t1(n(i2, rect.width() + b0() + c0(), Z()), n(i3, rect.height() + d0() + a0(), Y()));
        }

        public void v(int i2) {
            w(i2, G(i2));
        }

        public void v0(int i2) {
            RecyclerView recyclerView = this.f1389b;
            if (recyclerView != null) {
                recyclerView.p0(i2);
            }
        }

        public void v1(int i2, int i3) {
            int iH = H();
            if (iH == 0) {
                this.f1389b.w(i2, i3);
                return;
            }
            int i4 = PKIFailureInfo.systemUnavail;
            int i5 = PKIFailureInfo.systemUnavail;
            int i6 = Integer.MAX_VALUE;
            int i7 = Integer.MAX_VALUE;
            for (int i8 = 0; i8 < iH; i8++) {
                View viewG = G(i8);
                Rect rect = this.f1389b.f1321i;
                M(viewG, rect);
                int i9 = rect.left;
                if (i9 < i6) {
                    i6 = i9;
                }
                int i10 = rect.right;
                if (i10 > i4) {
                    i4 = i10;
                }
                int i11 = rect.top;
                if (i11 < i7) {
                    i7 = i11;
                }
                int i12 = rect.bottom;
                if (i12 > i5) {
                    i5 = i12;
                }
            }
            this.f1389b.f1321i.set(i6, i7, i4, i5);
            u1(this.f1389b.f1321i, i2, i3);
        }

        public final void w(int i2, View view) {
            this.f1388a.d(i2);
        }

        public void w0(int i2) {
            RecyclerView recyclerView = this.f1389b;
            if (recyclerView != null) {
                recyclerView.q0(i2);
            }
        }

        public void w1(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.f1389b = null;
                this.f1388a = null;
                this.f1396i = 0;
                this.f1397j = 0;
            } else {
                this.f1389b = recyclerView;
                this.f1388a = recyclerView.f1313e;
                this.f1396i = recyclerView.getWidth();
                this.f1397j = recyclerView.getHeight();
            }
            this.f1394g = 1073741824;
            this.f1395h = 1073741824;
        }

        public void x(RecyclerView recyclerView) {
            this.f1391d = true;
            z0(recyclerView);
        }

        public void x0(g gVar, g gVar2) {
        }

        public boolean x1(View view, int i2, int i3, o oVar) {
            return (!view.isLayoutRequested() && this.f1393f && q0(view.getWidth(), i2, ((ViewGroup.MarginLayoutParams) oVar).width) && q0(view.getHeight(), i3, ((ViewGroup.MarginLayoutParams) oVar).height)) ? false : true;
        }

        public void y(RecyclerView recyclerView, u uVar) {
            this.f1391d = false;
            B0(recyclerView, uVar);
        }

        public boolean y0(RecyclerView recyclerView, ArrayList arrayList, int i2, int i3) {
            return false;
        }

        public boolean y1() {
            return false;
        }

        public View z(View view) {
            View viewP;
            RecyclerView recyclerView = this.f1389b;
            if (recyclerView == null || (viewP = recyclerView.P(view)) == null || this.f1388a.n(viewP)) {
                return null;
            }
            return viewP;
        }

        public void z0(RecyclerView recyclerView) {
        }

        public boolean z1(View view, int i2, int i3, o oVar) {
            return (this.f1393f && q0(view.getMeasuredWidth(), i2, ((ViewGroup.MarginLayoutParams) oVar).width) && q0(view.getMeasuredHeight(), i3, ((ViewGroup.MarginLayoutParams) oVar).height)) ? false : true;
        }
    }

    public interface p {
        void a(View view);

        void b(View view);
    }

    public static abstract class q {
    }

    public interface r {
        void a(RecyclerView recyclerView, MotionEvent motionEvent);

        void b(boolean z2);

        boolean c(RecyclerView recyclerView, MotionEvent motionEvent);
    }

    public static abstract class s {
        public void a(RecyclerView recyclerView, int i2) {
        }

        public void b(RecyclerView recyclerView, int i2, int i3) {
        }
    }

    public static class t {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public SparseArray f1406a = new SparseArray();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public SparseIntArray f1407b = new SparseIntArray();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1408c = 0;

        public void a(g gVar) {
            this.f1408c++;
        }

        public void b() {
            this.f1406a.clear();
        }

        public void c() {
            this.f1408c--;
        }

        public B d(int i2) {
            ArrayList arrayList = (ArrayList) this.f1406a.get(i2);
            if (arrayList == null || arrayList.isEmpty()) {
                return null;
            }
            int size = arrayList.size() - 1;
            B b2 = (B) arrayList.get(size);
            arrayList.remove(size);
            return b2;
        }

        public final ArrayList e(int i2) {
            ArrayList arrayList = (ArrayList) this.f1406a.get(i2);
            if (arrayList == null) {
                arrayList = new ArrayList();
                this.f1406a.put(i2, arrayList);
                if (this.f1407b.indexOfKey(i2) < 0) {
                    this.f1407b.put(i2, 5);
                }
            }
            return arrayList;
        }

        public void f(g gVar, g gVar2, boolean z2) {
            if (gVar != null) {
                c();
            }
            if (!z2 && this.f1408c == 0) {
                b();
            }
            if (gVar2 != null) {
                a(gVar2);
            }
        }

        public void g(B b2) {
            int iT = b2.t();
            ArrayList arrayListE = e(iT);
            if (this.f1407b.get(iT) <= arrayListE.size()) {
                return;
            }
            b2.K();
            arrayListE.add(b2);
        }
    }

    public final class u {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ArrayList f1409a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList f1410b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ArrayList f1411c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final List f1412d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1413e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public t f1414f;

        public u() {
            ArrayList arrayList = new ArrayList();
            this.f1409a = arrayList;
            this.f1410b = null;
            this.f1411c = new ArrayList();
            this.f1412d = Collections.unmodifiableList(arrayList);
            this.f1413e = 2;
        }

        public void A(int i2) {
            a((B) this.f1411c.get(i2));
            this.f1411c.remove(i2);
        }

        public void B(View view) {
            B bZ = RecyclerView.Z(view);
            if (bZ.E()) {
                RecyclerView.this.removeDetachedView(view, false);
            }
            if (bZ.D()) {
                bZ.R();
            } else if (bZ.S()) {
                bZ.m();
            }
            C(bZ);
        }

        /* JADX WARN: Removed duplicated region for block: B:32:0x0062  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x0065  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x006a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void C(android.support.v7.widget.RecyclerView.B r6) {
            /*
                Method dump skipped, instruction units count: 203
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.u.C(android.support.v7.widget.RecyclerView$B):void");
        }

        public void D(View view) {
            B bZ = RecyclerView.Z(view);
            if (!bZ.x(12) && bZ.F() && !RecyclerView.this.q(bZ)) {
                if (this.f1410b == null) {
                    this.f1410b = new ArrayList();
                }
                bZ.O(this, true);
                this.f1410b.add(bZ);
                return;
            }
            if (bZ.A() && !bZ.C() && !RecyclerView.this.f1327l.f()) {
                throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.");
            }
            bZ.O(this, false);
            this.f1409a.add(bZ);
        }

        public void E() {
            int size = this.f1411c.size();
            for (int i2 = 0; i2 < size; i2++) {
                B b2 = (B) this.f1411c.get(i2);
                if (b2 != null) {
                    b2.j(512);
                }
            }
        }

        public void F(t tVar) {
            t tVar2 = this.f1414f;
            if (tVar2 != null) {
                tVar2.c();
            }
            this.f1414f = tVar;
            if (tVar != null) {
                tVar.a(RecyclerView.this.getAdapter());
            }
        }

        public void G(z zVar) {
        }

        public void H(int i2) {
            this.f1413e = i2;
            for (int size = this.f1411c.size() - 1; size >= 0 && this.f1411c.size() > i2; size--) {
                A(size);
            }
        }

        public void I(B b2) {
            if (b2.f1367n) {
                this.f1410b.remove(b2);
            } else {
                this.f1409a.remove(b2);
            }
            b2.f1366m = null;
            b2.f1367n = false;
            b2.m();
        }

        public boolean J(B b2) {
            if (b2.C()) {
                return RecyclerView.this.f1306a0.e();
            }
            int i2 = b2.f1355b;
            if (i2 < 0 || i2 >= RecyclerView.this.f1327l.c()) {
                throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + b2);
            }
            if (RecyclerView.this.f1306a0.e() || RecyclerView.this.f1327l.e(b2.f1355b) == b2.t()) {
                return !RecyclerView.this.f1327l.f() || b2.s() == RecyclerView.this.f1327l.d(b2.f1355b);
            }
            return false;
        }

        public void K(int i2, int i3) {
            int iU;
            int i4 = i3 + i2;
            for (int size = this.f1411c.size() - 1; size >= 0; size--) {
                B b2 = (B) this.f1411c.get(size);
                if (b2 != null && (iU = b2.u()) >= i2 && iU < i4) {
                    b2.j(2);
                    A(size);
                }
            }
        }

        public void a(B b2) {
            AbstractC0285k0.J(b2.f1354a, null);
            g(b2);
            b2.f1369p = null;
            i().g(b2);
        }

        public final void b(View view) {
            if (RecyclerView.this.j0()) {
                if (AbstractC0285k0.i(view) == 0) {
                    AbstractC0285k0.P(view, 1);
                }
                if (AbstractC0285k0.u(view)) {
                    return;
                }
                AbstractC0285k0.J(view, RecyclerView.this.f1320h0.k());
            }
        }

        public void c() {
            this.f1409a.clear();
            z();
        }

        public void d() {
            int size = this.f1411c.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((B) this.f1411c.get(i2)).k();
            }
            int size2 = this.f1409a.size();
            for (int i3 = 0; i3 < size2; i3++) {
                ((B) this.f1409a.get(i3)).k();
            }
            ArrayList arrayList = this.f1410b;
            if (arrayList != null) {
                int size3 = arrayList.size();
                for (int i4 = 0; i4 < size3; i4++) {
                    ((B) this.f1410b.get(i4)).k();
                }
            }
        }

        public void e() {
            this.f1409a.clear();
            ArrayList arrayList = this.f1410b;
            if (arrayList != null) {
                arrayList.clear();
            }
        }

        public int f(int i2) {
            if (i2 >= 0 && i2 < RecyclerView.this.f1306a0.b()) {
                return !RecyclerView.this.f1306a0.e() ? i2 : RecyclerView.this.f1311d.m(i2);
            }
            throw new IndexOutOfBoundsException("invalid position " + i2 + ". State item count is " + RecyclerView.this.f1306a0.b());
        }

        public void g(B b2) {
            RecyclerView.this.getClass();
            g gVar = RecyclerView.this.f1327l;
            if (gVar != null) {
                gVar.s(b2);
            }
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.f1306a0 != null) {
                recyclerView.f1315f.q(b2);
            }
        }

        public B h(int i2) {
            int size;
            int iM;
            ArrayList arrayList = this.f1410b;
            if (arrayList != null && (size = arrayList.size()) != 0) {
                for (int i3 = 0; i3 < size; i3++) {
                    B b2 = (B) this.f1410b.get(i3);
                    if (!b2.S() && b2.u() == i2) {
                        b2.j(32);
                        return b2;
                    }
                }
                if (RecyclerView.this.f1327l.f() && (iM = RecyclerView.this.f1311d.m(i2)) > 0 && iM < RecyclerView.this.f1327l.c()) {
                    long jD = RecyclerView.this.f1327l.d(iM);
                    for (int i4 = 0; i4 < size; i4++) {
                        B b3 = (B) this.f1410b.get(i4);
                        if (!b3.S() && b3.s() == jD) {
                            b3.j(32);
                            return b3;
                        }
                    }
                }
            }
            return null;
        }

        public t i() {
            if (this.f1414f == null) {
                this.f1414f = new t();
            }
            return this.f1414f;
        }

        public int j() {
            return this.f1409a.size();
        }

        public List k() {
            return this.f1412d;
        }

        public View l(int i2) {
            return ((B) this.f1409a.get(i2)).f1354a;
        }

        public B m(long j2, int i2, boolean z2) {
            for (int size = this.f1409a.size() - 1; size >= 0; size--) {
                B b2 = (B) this.f1409a.get(size);
                if (b2.s() == j2 && !b2.S()) {
                    if (i2 == b2.t()) {
                        b2.j(32);
                        if (b2.C() && !RecyclerView.this.f1306a0.e()) {
                            b2.M(2, 14);
                        }
                        return b2;
                    }
                    if (!z2) {
                        this.f1409a.remove(size);
                        RecyclerView.this.removeDetachedView(b2.f1354a, false);
                        y(b2.f1354a);
                    }
                }
            }
            for (int size2 = this.f1411c.size() - 1; size2 >= 0; size2--) {
                B b3 = (B) this.f1411c.get(size2);
                if (b3.s() == j2) {
                    if (i2 == b3.t()) {
                        if (!z2) {
                            this.f1411c.remove(size2);
                        }
                        return b3;
                    }
                    if (!z2) {
                        A(size2);
                    }
                }
            }
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:33:0x00c9  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public android.support.v7.widget.RecyclerView.B n(int r7, int r8, boolean r9) {
            /*
                Method dump skipped, instruction units count: 234
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.u.n(int, int, boolean):android.support.v7.widget.RecyclerView$B");
        }

        public View o(int i2) {
            return p(i2, false);
        }

        /* JADX WARN: Removed duplicated region for block: B:26:0x0059 A[PHI: r0
  0x0059: PHI (r0v10 android.support.v7.widget.RecyclerView$B) = (r0v9 android.support.v7.widget.RecyclerView$B), (r0v28 android.support.v7.widget.RecyclerView$B) binds: [B:12:0x0023, B:14:0x002a] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:28:0x005c  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x0108  */
        /* JADX WARN: Removed duplicated region for block: B:61:0x0134  */
        /* JADX WARN: Removed duplicated region for block: B:71:0x0169  */
        /* JADX WARN: Removed duplicated region for block: B:75:0x0174  */
        /* JADX WARN: Removed duplicated region for block: B:76:0x0182  */
        /* JADX WARN: Removed duplicated region for block: B:82:0x019e A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:84:0x01a1  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public android.view.View p(int r9, boolean r10) {
            /*
                Method dump skipped, instruction units count: 470
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.u.p(int, boolean):android.view.View");
        }

        public final void q(B b2) {
            View view = b2.f1354a;
            if (view instanceof ViewGroup) {
                r((ViewGroup) view, false);
            }
        }

        public final void r(ViewGroup viewGroup, boolean z2) {
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (childAt instanceof ViewGroup) {
                    r((ViewGroup) childAt, true);
                }
            }
            if (z2) {
                if (viewGroup.getVisibility() == 4) {
                    viewGroup.setVisibility(0);
                    viewGroup.setVisibility(4);
                } else {
                    int visibility = viewGroup.getVisibility();
                    viewGroup.setVisibility(4);
                    viewGroup.setVisibility(visibility);
                }
            }
        }

        public void s() {
            int size = this.f1411c.size();
            for (int i2 = 0; i2 < size; i2++) {
                o oVar = (o) ((B) this.f1411c.get(i2)).f1354a.getLayoutParams();
                if (oVar != null) {
                    oVar.f1404c = true;
                }
            }
        }

        public void t() {
            g gVar = RecyclerView.this.f1327l;
            if (gVar == null || !gVar.f()) {
                z();
                return;
            }
            int size = this.f1411c.size();
            for (int i2 = 0; i2 < size; i2++) {
                B b2 = (B) this.f1411c.get(i2);
                if (b2 != null) {
                    b2.j(6);
                    b2.i(null);
                }
            }
        }

        public void u(int i2, int i3) {
            int size = this.f1411c.size();
            for (int i4 = 0; i4 < size; i4++) {
                B b2 = (B) this.f1411c.get(i4);
                if (b2 != null && b2.f1355b >= i2) {
                    b2.H(i3, true);
                }
            }
        }

        public void v(int i2, int i3) {
            int i4;
            int i5;
            int i6;
            int i7;
            if (i2 < i3) {
                i4 = -1;
                i6 = i2;
                i5 = i3;
            } else {
                i4 = 1;
                i5 = i2;
                i6 = i3;
            }
            int size = this.f1411c.size();
            for (int i8 = 0; i8 < size; i8++) {
                B b2 = (B) this.f1411c.get(i8);
                if (b2 != null && (i7 = b2.f1355b) >= i6 && i7 <= i5) {
                    if (i7 == i2) {
                        b2.H(i3 - i2, false);
                    } else {
                        b2.H(i4, false);
                    }
                }
            }
        }

        public void w(int i2, int i3, boolean z2) {
            int i4 = i2 + i3;
            for (int size = this.f1411c.size() - 1; size >= 0; size--) {
                B b2 = (B) this.f1411c.get(size);
                if (b2 != null) {
                    int i5 = b2.f1355b;
                    if (i5 >= i4) {
                        b2.H(-i3, z2);
                    } else if (i5 >= i2) {
                        b2.j(8);
                        A(size);
                    }
                }
            }
        }

        public void x(g gVar, g gVar2, boolean z2) {
            c();
            i().f(gVar, gVar2, z2);
        }

        public void y(View view) {
            B bZ = RecyclerView.Z(view);
            bZ.f1366m = null;
            bZ.f1367n = false;
            bZ.m();
            C(bZ);
        }

        public void z() {
            for (int size = this.f1411c.size() - 1; size >= 0; size--) {
                A(size);
            }
            this.f1411c.clear();
        }
    }

    public interface v {
    }

    public class w extends i {
        public w() {
        }

        @Override // android.support.v7.widget.RecyclerView.i
        public void a() {
            RecyclerView.this.p(null);
            if (RecyclerView.this.f1327l.f()) {
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.f1306a0.f1424g = true;
                recyclerView.V0();
            } else {
                RecyclerView recyclerView2 = RecyclerView.this;
                recyclerView2.f1306a0.f1424g = true;
                recyclerView2.V0();
            }
            if (RecyclerView.this.f1311d.p()) {
                return;
            }
            RecyclerView.this.requestLayout();
        }

        @Override // android.support.v7.widget.RecyclerView.i
        public void b(int i2, int i3, Object obj) {
            RecyclerView.this.p(null);
            if (RecyclerView.this.f1311d.r(i2, i3, obj)) {
                e();
            }
        }

        @Override // android.support.v7.widget.RecyclerView.i
        public void c(int i2, int i3) {
            RecyclerView.this.p(null);
            if (RecyclerView.this.f1311d.s(i2, i3)) {
                e();
            }
        }

        @Override // android.support.v7.widget.RecyclerView.i
        public void d(int i2, int i3, int i4) {
            RecyclerView.this.p(null);
            if (RecyclerView.this.f1311d.t(i2, i3, i4)) {
                e();
            }
        }

        public void e() {
            if (RecyclerView.this.f1345z) {
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.f1337r && recyclerView.f1336q) {
                    AbstractC0285k0.F(recyclerView, recyclerView.f1319h);
                    return;
                }
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            recyclerView2.f1344y = true;
            recyclerView2.requestLayout();
        }
    }

    public static class y {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public SparseArray f1420c;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public int f1430m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public long f1431n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public int f1432o;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1418a = -1;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1419b = 1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1421d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1422e = 0;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1423f = 0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f1424g = false;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f1425h = false;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f1426i = false;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public boolean f1427j = false;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public boolean f1428k = false;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public boolean f1429l = false;

        public void a(int i2) {
            if ((this.f1419b & i2) != 0) {
                return;
            }
            throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i2) + " but it is " + Integer.toBinaryString(this.f1419b));
        }

        public int b() {
            return this.f1425h ? this.f1422e - this.f1423f : this.f1421d;
        }

        public int c() {
            return this.f1418a;
        }

        public boolean d() {
            return this.f1418a != -1;
        }

        public boolean e() {
            return this.f1425h;
        }

        public boolean f() {
            return this.f1427j;
        }

        public String toString() {
            return "State{mTargetPosition=" + this.f1418a + ", mData=" + this.f1420c + ", mItemCount=" + this.f1421d + ", mPreviousLayoutItemCount=" + this.f1422e + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.f1423f + ", mStructureChanged=" + this.f1424g + ", mInPreLayout=" + this.f1425h + ", mRunSimpleAnimations=" + this.f1426i + ", mRunPredictiveAnimations=" + this.f1427j + '}';
        }
    }

    public static abstract class z {
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        s0 = i2 == 18 || i2 == 19 || i2 == 20;
        t0 = i2 >= 23;
        Class cls = Integer.TYPE;
        u0 = new Class[]{Context.class, AttributeSet.class, cls, cls};
        v0 = new c();
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public static B Z(View view) {
        if (view == null) {
            return null;
        }
        return ((o) view.getLayoutParams()).f1402a;
    }

    private float getScrollFactor() {
        if (this.f1302U == Float.MIN_VALUE) {
            TypedValue typedValue = new TypedValue();
            if (!getContext().getTheme().resolveAttribute(R.attr.listPreferredItemHeight, typedValue, true)) {
                return 0.0f;
            }
            this.f1302U = typedValue.getDimension(getContext().getResources().getDisplayMetrics());
        }
        return this.f1302U;
    }

    private C0255a0 getScrollingChildHelper() {
        if (this.f1326k0 == null) {
            this.f1326k0 = new C0255a0(this);
        }
        return this.f1326k0;
    }

    public final void A() {
        int i2 = this.f1343x;
        this.f1343x = 0;
        if (i2 == 0 || !j0()) {
            return;
        }
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
        accessibilityEventObtain.setEventType(2048);
        AbstractC0343a.c(accessibilityEventObtain, i2);
        sendAccessibilityEventUnchecked(accessibilityEventObtain);
    }

    public void A0(int i2, int i3) {
    }

    public void B() {
        if (this.f1327l == null) {
            Log.e("RecyclerView", "No adapter attached; skipping layout");
            return;
        }
        if (this.f1329m == null) {
            Log.e("RecyclerView", "No layout manager attached; skipping layout");
            return;
        }
        y yVar = this.f1306a0;
        yVar.f1429l = false;
        if (yVar.f1419b == 1) {
            C();
            this.f1329m.r1(this);
            D();
        } else if (!this.f1311d.q() && this.f1329m.l0() == getWidth() && this.f1329m.T() == getHeight()) {
            this.f1329m.r1(this);
        } else {
            this.f1329m.r1(this);
            D();
        }
        E();
    }

    public void B0() {
        if (this.f1318g0 || !this.f1336q) {
            return;
        }
        AbstractC0285k0.F(this, this.f1334o0);
        this.f1318g0 = true;
    }

    public final void C() {
        this.f1306a0.a(1);
        this.f1306a0.f1429l = false;
        J();
        this.f1315f.f();
        w0();
        Q0();
        D0();
        y yVar = this.f1306a0;
        yVar.f1428k = yVar.f1426i && this.f1314e0;
        this.f1314e0 = false;
        this.f1312d0 = false;
        yVar.f1425h = yVar.f1427j;
        yVar.f1421d = this.f1327l.c();
        R(this.f1324j0);
        if (this.f1306a0.f1426i) {
            int iG = this.f1313e.g();
            for (int i2 = 0; i2 < iG; i2++) {
                B bZ = Z(this.f1313e.f(i2));
                if (!bZ.Q() && (!bZ.A() || this.f1327l.f())) {
                    this.f1315f.e(bZ, this.f1291J.u(this.f1306a0, bZ, k.e(bZ), bZ.w()));
                    if (this.f1306a0.f1428k && bZ.F() && !bZ.C() && !bZ.Q() && !bZ.A()) {
                        this.f1315f.c(X(bZ), bZ);
                    }
                }
            }
        }
        if (this.f1306a0.f1427j) {
            R0();
            y yVar2 = this.f1306a0;
            boolean z2 = yVar2.f1424g;
            yVar2.f1424g = false;
            this.f1329m.Q0(this.f1307b, yVar2);
            this.f1306a0.f1424g = z2;
            for (int i3 = 0; i3 < this.f1313e.g(); i3++) {
                B bZ2 = Z(this.f1313e.f(i3));
                if (!bZ2.Q() && !this.f1315f.i(bZ2)) {
                    int iE = k.e(bZ2);
                    boolean zX = bZ2.x(PKIFailureInfo.certRevoked);
                    if (!zX) {
                        iE |= PKIFailureInfo.certConfirmed;
                    }
                    k.c cVarU = this.f1291J.u(this.f1306a0, bZ2, iE, bZ2.w());
                    if (zX) {
                        F0(bZ2, cVarU);
                    } else {
                        this.f1315f.a(bZ2, cVarU);
                    }
                }
            }
            s();
        } else {
            s();
        }
        x0();
        P0(false);
        this.f1306a0.f1419b = 2;
    }

    public final boolean C0() {
        return this.f1291J != null && this.f1329m.B1();
    }

    public final void D() {
        J();
        w0();
        this.f1306a0.a(6);
        this.f1311d.j();
        this.f1306a0.f1421d = this.f1327l.c();
        y yVar = this.f1306a0;
        yVar.f1423f = 0;
        yVar.f1425h = false;
        this.f1329m.Q0(this.f1307b, yVar);
        y yVar2 = this.f1306a0;
        yVar2.f1424g = false;
        this.f1309c = null;
        yVar2.f1426i = yVar2.f1426i && this.f1291J != null;
        yVar2.f1419b = 4;
        x0();
        P0(false);
    }

    public final void D0() {
        boolean z2;
        if (this.f1284C) {
            this.f1311d.x();
            o0();
            this.f1329m.L0(this);
        }
        if (C0()) {
            this.f1311d.v();
        } else {
            this.f1311d.j();
        }
        boolean z3 = this.f1312d0 || this.f1314e0;
        this.f1306a0.f1426i = this.f1338s && this.f1291J != null && ((z2 = this.f1284C) || z3 || this.f1329m.f1390c) && (!z2 || this.f1327l.f());
        y yVar = this.f1306a0;
        yVar.f1427j = yVar.f1426i && z3 && !this.f1284C && C0();
    }

    public final void E() {
        this.f1306a0.a(4);
        J();
        w0();
        y yVar = this.f1306a0;
        yVar.f1419b = 1;
        if (yVar.f1426i) {
            for (int iG = this.f1313e.g() - 1; iG >= 0; iG--) {
                B bZ = Z(this.f1313e.f(iG));
                if (!bZ.Q()) {
                    long jX = X(bZ);
                    k.c cVarT = this.f1291J.t(this.f1306a0, bZ);
                    B bG = this.f1315f.g(jX);
                    if (bG == null || bG.Q()) {
                        this.f1315f.d(bZ, cVarT);
                    } else {
                        boolean zH = this.f1315f.h(bG);
                        boolean zH2 = this.f1315f.h(bZ);
                        if (zH && bG == bZ) {
                            this.f1315f.d(bZ, cVarT);
                        } else {
                            k.c cVarN = this.f1315f.n(bG);
                            this.f1315f.d(bZ, cVarT);
                            k.c cVarM = this.f1315f.m(bZ);
                            if (cVarN == null) {
                                d0(jX, bZ, bG);
                            } else {
                                n(bG, bZ, cVarN, cVarM, zH, zH2);
                            }
                        }
                    }
                }
            }
            this.f1315f.o(this.p0);
        }
        this.f1329m.d1(this.f1307b);
        y yVar2 = this.f1306a0;
        yVar2.f1422e = yVar2.f1421d;
        this.f1284C = false;
        yVar2.f1426i = false;
        yVar2.f1427j = false;
        this.f1329m.f1390c = false;
        ArrayList arrayList = this.f1307b.f1410b;
        if (arrayList != null) {
            arrayList.clear();
        }
        this.f1329m.R0(this.f1306a0);
        x0();
        P0(false);
        this.f1315f.f();
        int[] iArr = this.f1324j0;
        if (x(iArr[0], iArr[1])) {
            I(0, 0);
        }
        G0();
        N0();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void E0(float r7, float r8, float r9, float r10) {
        /*
            r6 = this;
            r0 = 1065353216(0x3f800000, float:1.0)
            r1 = 1
            r2 = 0
            int r3 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r3 >= 0) goto L24
            r6.L()
            n.j r3 = r6.f1287F
            float r4 = -r8
            int r5 = r6.getWidth()
            float r5 = (float) r5
            float r4 = r4 / r5
            int r5 = r6.getHeight()
            float r5 = (float) r5
            float r9 = r9 / r5
            float r9 = r0 - r9
            boolean r9 = r3.f(r4, r9)
            if (r9 == 0) goto L41
        L22:
            r9 = 1
            goto L42
        L24:
            int r3 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r3 <= 0) goto L41
            r6.M()
            n.j r3 = r6.f1289H
            int r4 = r6.getWidth()
            float r4 = (float) r4
            float r4 = r8 / r4
            int r5 = r6.getHeight()
            float r5 = (float) r5
            float r9 = r9 / r5
            boolean r9 = r3.f(r4, r9)
            if (r9 == 0) goto L41
            goto L22
        L41:
            r9 = 0
        L42:
            int r3 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r3 >= 0) goto L5f
            r6.N()
            n.j r0 = r6.f1288G
            float r3 = -r10
            int r4 = r6.getHeight()
            float r4 = (float) r4
            float r3 = r3 / r4
            int r4 = r6.getWidth()
            float r4 = (float) r4
            float r7 = r7 / r4
            boolean r7 = r0.f(r3, r7)
            if (r7 == 0) goto L7d
            goto L7e
        L5f:
            int r3 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r3 <= 0) goto L7d
            r6.K()
            n.j r3 = r6.f1290I
            int r4 = r6.getHeight()
            float r4 = (float) r4
            float r4 = r10 / r4
            int r5 = r6.getWidth()
            float r5 = (float) r5
            float r7 = r7 / r5
            float r0 = r0 - r7
            boolean r7 = r3.f(r4, r0)
            if (r7 == 0) goto L7d
            goto L7e
        L7d:
            r1 = r9
        L7e:
            if (r1 != 0) goto L88
            int r7 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r7 != 0) goto L88
            int r7 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r7 == 0) goto L8b
        L88:
            k.AbstractC0285k0.E(r6)
        L8b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.E0(float, float, float, float):void");
    }

    public final boolean F(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        r rVar = this.f1335p;
        if (rVar != null) {
            if (action != 0) {
                rVar.a(this, motionEvent);
                if (action == 3 || action == 1) {
                    this.f1335p = null;
                }
                return true;
            }
            this.f1335p = null;
        }
        if (action != 0) {
            int size = this.f1333o.size();
            for (int i2 = 0; i2 < size; i2++) {
                r rVar2 = (r) this.f1333o.get(i2);
                if (rVar2.c(this, motionEvent)) {
                    this.f1335p = rVar2;
                    return true;
                }
            }
        }
        return false;
    }

    public void F0(B b2, k.c cVar) {
        b2.M(0, PKIFailureInfo.certRevoked);
        if (this.f1306a0.f1428k && b2.F() && !b2.C() && !b2.Q()) {
            this.f1315f.c(X(b2), b2);
        }
        this.f1315f.e(b2, cVar);
    }

    public final boolean G(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 3 || action == 0) {
            this.f1335p = null;
        }
        int size = this.f1333o.size();
        for (int i2 = 0; i2 < size; i2++) {
            r rVar = (r) this.f1333o.get(i2);
            if (rVar.c(this, motionEvent) && action != 3) {
                this.f1335p = rVar;
                return true;
            }
        }
        return false;
    }

    public final void G0() {
        View viewFindViewById;
        View focusedChild;
        if (this.f1303V && this.f1327l != null && hasFocus()) {
            if (isFocused() || ((focusedChild = getFocusedChild()) != null && this.f1313e.n(focusedChild))) {
                int i2 = this.f1306a0.f1430m;
                B bS = i2 != -1 ? S(i2) : null;
                if (bS == null && this.f1306a0.f1431n != -1 && this.f1327l.f()) {
                    bS = T(this.f1306a0.f1431n);
                }
                if (bS == null || bS.f1354a.hasFocus() || !bS.f1354a.hasFocusable()) {
                    return;
                }
                View view = bS.f1354a;
                int i3 = this.f1306a0.f1432o;
                if (i3 != -1 && (viewFindViewById = view.findViewById(i3)) != null && viewFindViewById.isFocusable()) {
                    view = viewFindViewById;
                }
                view.requestFocus();
            }
        }
    }

    public void H(int i2) {
        n nVar = this.f1329m;
        if (nVar != null) {
            nVar.X0(i2);
        }
        z0(i2);
        s sVar = this.f1308b0;
        if (sVar != null) {
            sVar.a(this, i2);
        }
        List list = this.f1310c0;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                ((s) this.f1310c0.get(size)).a(this, i2);
            }
        }
    }

    public final void H0() {
        C0427j c0427j = this.f1287F;
        boolean zG = c0427j != null ? c0427j.g() : false;
        C0427j c0427j2 = this.f1288G;
        if (c0427j2 != null) {
            zG |= c0427j2.g();
        }
        C0427j c0427j3 = this.f1289H;
        if (c0427j3 != null) {
            zG |= c0427j3.g();
        }
        C0427j c0427j4 = this.f1290I;
        if (c0427j4 != null) {
            zG |= c0427j4.g();
        }
        if (zG) {
            AbstractC0285k0.E(this);
        }
    }

    public void I(int i2, int i3) {
        this.f1286E++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX, scrollY);
        A0(i2, i3);
        s sVar = this.f1308b0;
        if (sVar != null) {
            sVar.b(this, i2, i3);
        }
        List list = this.f1310c0;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                ((s) this.f1310c0.get(size)).b(this, i2, i3);
            }
        }
        this.f1286E--;
    }

    public boolean I0(View view) {
        J();
        boolean zR = this.f1313e.r(view);
        if (zR) {
            B bZ = Z(view);
            this.f1307b.I(bZ);
            this.f1307b.C(bZ);
        }
        P0(!zR);
        return zR;
    }

    public void J() {
        int i2 = this.f1339t + 1;
        this.f1339t = i2;
        if (i2 != 1 || this.f1341v) {
            return;
        }
        this.f1340u = false;
    }

    public void J0(m mVar) {
        n nVar = this.f1329m;
        if (nVar != null) {
            nVar.g("Cannot remove item decoration during a scroll  or layout");
        }
        this.f1331n.remove(mVar);
        if (this.f1331n.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        n0();
        requestLayout();
    }

    public void K() {
        if (this.f1290I != null) {
            return;
        }
        C0427j c0427j = new C0427j(getContext());
        this.f1290I = c0427j;
        if (this.f1317g) {
            c0427j.h((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            c0427j.h(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void K0(p pVar) {
        List list = this.f1283B;
        if (list == null) {
            return;
        }
        list.remove(pVar);
    }

    public void L() {
        if (this.f1287F != null) {
            return;
        }
        C0427j c0427j = new C0427j(getContext());
        this.f1287F = c0427j;
        if (this.f1317g) {
            c0427j.h((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            c0427j.h(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public void L0(r rVar) {
        this.f1333o.remove(rVar);
        if (this.f1335p == rVar) {
            this.f1335p = null;
        }
    }

    public void M() {
        if (this.f1289H != null) {
            return;
        }
        C0427j c0427j = new C0427j(getContext());
        this.f1289H = c0427j;
        if (this.f1317g) {
            c0427j.h((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            c0427j.h(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public void M0() {
        B b2;
        int iG = this.f1313e.g();
        for (int i2 = 0; i2 < iG; i2++) {
            View viewF = this.f1313e.f(i2);
            B bY = Y(viewF);
            if (bY != null && (b2 = bY.f1361h) != null) {
                View view = b2.f1354a;
                int left = viewF.getLeft();
                int top = viewF.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    public void N() {
        if (this.f1288G != null) {
            return;
        }
        C0427j c0427j = new C0427j(getContext());
        this.f1288G = c0427j;
        if (this.f1317g) {
            c0427j.h((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            c0427j.h(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public final void N0() {
        y yVar = this.f1306a0;
        yVar.f1431n = -1L;
        yVar.f1430m = -1;
        yVar.f1432o = -1;
    }

    public View O(float f2, float f3) {
        for (int iG = this.f1313e.g() - 1; iG >= 0; iG--) {
            View viewF = this.f1313e.f(iG);
            float fR = AbstractC0285k0.r(viewF);
            float fS = AbstractC0285k0.s(viewF);
            if (f2 >= viewF.getLeft() + fR && f2 <= viewF.getRight() + fR && f3 >= viewF.getTop() + fS && f3 <= viewF.getBottom() + fS) {
                return viewF;
            }
        }
        return null;
    }

    public final void O0() {
        VelocityTracker velocityTracker = this.f1294M;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        stopNestedScroll();
        H0();
    }

    public View P(View view) {
        ViewParent parent = view.getParent();
        while (parent != null && parent != this && (parent instanceof View)) {
            view = parent;
            parent = view.getParent();
        }
        if (parent == this) {
            return view;
        }
        return null;
    }

    public void P0(boolean z2) {
        if (this.f1339t < 1) {
            this.f1339t = 1;
        }
        if (!z2) {
            this.f1340u = false;
        }
        if (this.f1339t == 1) {
            if (z2 && this.f1340u && !this.f1341v && this.f1329m != null && this.f1327l != null) {
                B();
            }
            if (!this.f1341v) {
                this.f1340u = false;
            }
        }
        this.f1339t--;
    }

    public B Q(View view) {
        View viewP = P(view);
        if (viewP == null) {
            return null;
        }
        return Y(viewP);
    }

    public final void Q0() {
        View focusedChild = (this.f1303V && hasFocus() && this.f1327l != null) ? getFocusedChild() : null;
        B bQ = focusedChild != null ? Q(focusedChild) : null;
        if (bQ == null) {
            N0();
            return;
        }
        this.f1306a0.f1431n = this.f1327l.f() ? bQ.s() : -1L;
        this.f1306a0.f1430m = this.f1284C ? -1 : bQ.r();
        this.f1306a0.f1432o = a0(bQ.f1354a);
    }

    public final void R(int[] iArr) {
        int iG = this.f1313e.g();
        if (iG == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i2 = Integer.MAX_VALUE;
        int i3 = PKIFailureInfo.systemUnavail;
        for (int i4 = 0; i4 < iG; i4++) {
            B bZ = Z(this.f1313e.f(i4));
            if (!bZ.Q()) {
                int iU = bZ.u();
                if (iU < i2) {
                    i2 = iU;
                }
                if (iU > i3) {
                    i3 = iU;
                }
            }
        }
        iArr[0] = i2;
        iArr[1] = i3;
    }

    public void R0() {
        int iJ = this.f1313e.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            B bZ = Z(this.f1313e.i(i2));
            if (!bZ.Q()) {
                bZ.L();
            }
        }
    }

    public B S(int i2) {
        B b2 = null;
        if (this.f1284C) {
            return null;
        }
        int iJ = this.f1313e.j();
        for (int i3 = 0; i3 < iJ; i3++) {
            B bZ = Z(this.f1313e.i(i3));
            if (bZ != null && !bZ.C() && W(bZ) == i2) {
                if (!this.f1313e.n(bZ.f1354a)) {
                    return bZ;
                }
                b2 = bZ;
            }
        }
        return b2;
    }

    public boolean S0(int i2, int i3, MotionEvent motionEvent) {
        int iN1;
        int i4;
        int iP1;
        int i5;
        u();
        if (this.f1327l != null) {
            J();
            w0();
            i.f.a("RV Scroll");
            if (i2 != 0) {
                iN1 = this.f1329m.n1(i2, this.f1307b, this.f1306a0);
                i4 = i2 - iN1;
            } else {
                iN1 = 0;
                i4 = 0;
            }
            if (i3 != 0) {
                iP1 = this.f1329m.p1(i3, this.f1307b, this.f1306a0);
                i5 = i3 - iP1;
            } else {
                iP1 = 0;
                i5 = 0;
            }
            i.f.b();
            M0();
            x0();
            P0(false);
        } else {
            iN1 = 0;
            i4 = 0;
            iP1 = 0;
            i5 = 0;
        }
        if (!this.f1331n.isEmpty()) {
            invalidate();
        }
        if (dispatchNestedScroll(iN1, iP1, i4, i5, this.f1328l0)) {
            int i6 = this.f1297P;
            int[] iArr = this.f1328l0;
            int i7 = iArr[0];
            this.f1297P = i6 - i7;
            int i8 = this.f1298Q;
            int i9 = iArr[1];
            this.f1298Q = i8 - i9;
            if (motionEvent != null) {
                motionEvent.offsetLocation(i7, i9);
            }
            int[] iArr2 = this.f1332n0;
            int i10 = iArr2[0];
            int[] iArr3 = this.f1328l0;
            iArr2[0] = i10 + iArr3[0];
            iArr2[1] = iArr2[1] + iArr3[1];
        } else if (getOverScrollMode() != 2) {
            if (motionEvent != null) {
                E0(motionEvent.getX(), i4, motionEvent.getY(), i5);
            }
            t(i2, i3);
        }
        if (iN1 != 0 || iP1 != 0) {
            I(iN1, iP1);
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        return (iN1 == 0 && iP1 == 0) ? false : true;
    }

    public B T(long j2) {
        g gVar = this.f1327l;
        B b2 = null;
        if (gVar != null && gVar.f()) {
            int iJ = this.f1313e.j();
            for (int i2 = 0; i2 < iJ; i2++) {
                B bZ = Z(this.f1313e.i(i2));
                if (bZ != null && !bZ.C() && bZ.s() == j2) {
                    if (!this.f1313e.n(bZ.f1354a)) {
                        return bZ;
                    }
                    b2 = bZ;
                }
            }
        }
        return b2;
    }

    public void T0(int i2) {
        if (this.f1341v) {
            return;
        }
        Y0();
        n nVar = this.f1329m;
        if (nVar == null) {
            Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            nVar.o1(i2);
            awakenScrollBars();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.support.v7.widget.RecyclerView.B U(int r6, boolean r7) {
        /*
            r5 = this;
            android.support.v7.widget.c r0 = r5.f1313e
            int r0 = r0.j()
            r1 = 0
            r2 = 0
        L8:
            if (r2 >= r0) goto L3a
            android.support.v7.widget.c r3 = r5.f1313e
            android.view.View r3 = r3.i(r2)
            android.support.v7.widget.RecyclerView$B r3 = Z(r3)
            if (r3 == 0) goto L37
            boolean r4 = r3.C()
            if (r4 != 0) goto L37
            if (r7 == 0) goto L23
            int r4 = r3.f1355b
            if (r4 == r6) goto L2a
            goto L37
        L23:
            int r4 = r3.u()
            if (r4 == r6) goto L2a
            goto L37
        L2a:
            android.support.v7.widget.c r1 = r5.f1313e
            android.view.View r4 = r3.f1354a
            boolean r1 = r1.n(r4)
            if (r1 == 0) goto L36
            r1 = r3
            goto L37
        L36:
            return r3
        L37:
            int r2 = r2 + 1
            goto L8
        L3a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.U(int, boolean):android.support.v7.widget.RecyclerView$B");
    }

    public final void U0(g gVar, boolean z2, boolean z3) {
        g gVar2 = this.f1327l;
        if (gVar2 != null) {
            gVar2.u(this.f1305a);
            this.f1327l.o(this);
        }
        if (!z2 || z3) {
            k kVar = this.f1291J;
            if (kVar != null) {
                kVar.k();
            }
            n nVar = this.f1329m;
            if (nVar != null) {
                nVar.c1(this.f1307b);
                this.f1329m.d1(this.f1307b);
            }
            this.f1307b.c();
        }
        this.f1311d.x();
        g gVar3 = this.f1327l;
        this.f1327l = gVar;
        if (gVar != null) {
            gVar.t(this.f1305a);
            gVar.k(this);
        }
        n nVar2 = this.f1329m;
        if (nVar2 != null) {
            nVar2.x0(gVar3, this.f1327l);
        }
        this.f1307b.x(gVar3, this.f1327l, z2);
        this.f1306a0.f1424g = true;
        o0();
    }

    public boolean V(int i2, int i3) {
        n nVar = this.f1329m;
        if (nVar == null) {
            Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        }
        if (this.f1341v) {
            return false;
        }
        boolean zK = nVar.k();
        boolean zL = this.f1329m.l();
        if (!zK || Math.abs(i2) < this.f1300S) {
            i2 = 0;
        }
        if (!zL || Math.abs(i3) < this.f1300S) {
            i3 = 0;
        }
        if (i2 == 0 && i3 == 0) {
            return false;
        }
        float f2 = i2;
        float f3 = i3;
        if (!dispatchNestedPreFling(f2, f3)) {
            boolean z2 = zK || zL;
            dispatchNestedFling(f2, f3, z2);
            if (z2) {
                int i4 = this.f1301T;
                int iMax = Math.max(-i4, Math.min(i2, i4));
                int i5 = this.f1301T;
                this.f1304W.e(iMax, Math.max(-i5, Math.min(i3, i5)));
                return true;
            }
        }
        return false;
    }

    public void V0() {
        if (this.f1284C) {
            return;
        }
        this.f1284C = true;
        int iJ = this.f1313e.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            B bZ = Z(this.f1313e.i(i2));
            if (bZ != null && !bZ.Q()) {
                bZ.j(512);
            }
        }
        this.f1307b.E();
    }

    public int W(B b2) {
        if (b2.x(524) || !b2.z()) {
            return -1;
        }
        return this.f1311d.e(b2.f1355b);
    }

    public boolean W0(AccessibilityEvent accessibilityEvent) {
        if (!k0()) {
            return false;
        }
        int iB = accessibilityEvent != null ? AbstractC0343a.b(accessibilityEvent) : 0;
        this.f1343x |= iB != 0 ? iB : 0;
        return true;
    }

    public long X(B b2) {
        return this.f1327l.f() ? b2.s() : b2.f1355b;
    }

    public void X0(int i2, int i3) {
        n nVar = this.f1329m;
        if (nVar == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.f1341v) {
            return;
        }
        if (!nVar.k()) {
            i2 = 0;
        }
        if (!this.f1329m.l()) {
            i3 = 0;
        }
        if (i2 == 0 && i3 == 0) {
            return;
        }
        this.f1304W.g(i2, i3);
    }

    public B Y(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return Z(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    public void Y0() {
        setScrollState(0);
        Z0();
    }

    public final void Z0() {
        this.f1304W.k();
        n nVar = this.f1329m;
        if (nVar != null) {
            nVar.A1();
        }
    }

    public void a(int i2, int i3) {
        if (i2 < 0) {
            L();
            this.f1287F.d(-i2);
        } else if (i2 > 0) {
            M();
            this.f1289H.d(i2);
        }
        if (i3 < 0) {
            N();
            this.f1288G.d(-i3);
        } else if (i3 > 0) {
            K();
            this.f1290I.d(i3);
        }
        if (i2 == 0 && i3 == 0) {
            return;
        }
        AbstractC0285k0.E(this);
    }

    public final int a0(View view) {
        int id = view.getId();
        while (!view.isFocused() && (view instanceof ViewGroup) && view.hasFocus()) {
            view = ((ViewGroup) view).getFocusedChild();
            if (view.getId() != -1) {
                id = view.getId();
            }
        }
        return id;
    }

    public void a1(int i2, int i3, Object obj) {
        int i4;
        int iJ = this.f1313e.j();
        int i5 = i2 + i3;
        for (int i6 = 0; i6 < iJ; i6++) {
            View viewI = this.f1313e.i(i6);
            B bZ = Z(viewI);
            if (bZ != null && !bZ.Q() && (i4 = bZ.f1355b) >= i2 && i4 < i5) {
                bZ.j(2);
                bZ.i(obj);
                ((o) viewI.getLayoutParams()).f1404c = true;
            }
        }
        this.f1307b.K(i2, i3);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList arrayList, int i2, int i3) {
        n nVar = this.f1329m;
        if (nVar == null || !nVar.y0(this, arrayList, i2, i3)) {
            super.addFocusables(arrayList, i2, i3);
        }
    }

    public final String b0(Context context, String str) {
        if (str.charAt(0) == '.') {
            return context.getPackageName() + str;
        }
        if (str.contains(".")) {
            return str;
        }
        return RecyclerView.class.getPackage().getName() + '.' + str;
    }

    public Rect c0(View view) {
        o oVar = (o) view.getLayoutParams();
        if (!oVar.f1404c) {
            return oVar.f1403b;
        }
        if (this.f1306a0.e() && (oVar.b() || oVar.d())) {
            return oVar.f1403b;
        }
        Rect rect = oVar.f1403b;
        rect.set(0, 0, 0, 0);
        int size = this.f1331n.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.f1321i.set(0, 0, 0, 0);
            ((m) this.f1331n.get(i2)).c(this.f1321i, view, this, this.f1306a0);
            int i3 = rect.left;
            Rect rect2 = this.f1321i;
            rect.left = i3 + rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        oVar.f1404c = false;
        return rect;
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof o) && this.f1329m.m((o) layoutParams);
    }

    @Override // android.view.View, k.InterfaceC0273g0
    public int computeHorizontalScrollExtent() {
        n nVar = this.f1329m;
        if (nVar != null && nVar.k()) {
            return this.f1329m.o(this.f1306a0);
        }
        return 0;
    }

    @Override // android.view.View, k.InterfaceC0273g0
    public int computeHorizontalScrollOffset() {
        n nVar = this.f1329m;
        if (nVar != null && nVar.k()) {
            return this.f1329m.p(this.f1306a0);
        }
        return 0;
    }

    @Override // android.view.View, k.InterfaceC0273g0
    public int computeHorizontalScrollRange() {
        n nVar = this.f1329m;
        if (nVar != null && nVar.k()) {
            return this.f1329m.q(this.f1306a0);
        }
        return 0;
    }

    @Override // android.view.View, k.InterfaceC0273g0
    public int computeVerticalScrollExtent() {
        n nVar = this.f1329m;
        if (nVar != null && nVar.l()) {
            return this.f1329m.r(this.f1306a0);
        }
        return 0;
    }

    @Override // android.view.View, k.InterfaceC0273g0
    public int computeVerticalScrollOffset() {
        n nVar = this.f1329m;
        if (nVar != null && nVar.l()) {
            return this.f1329m.s(this.f1306a0);
        }
        return 0;
    }

    @Override // android.view.View, k.InterfaceC0273g0
    public int computeVerticalScrollRange() {
        n nVar = this.f1329m;
        if (nVar != null && nVar.l()) {
            return this.f1329m.t(this.f1306a0);
        }
        return 0;
    }

    public final void d0(long j2, B b2, B b3) {
        int iG = this.f1313e.g();
        for (int i2 = 0; i2 < iG; i2++) {
            B bZ = Z(this.f1313e.f(i2));
            if (bZ != b2 && X(bZ) == j2) {
                g gVar = this.f1327l;
                if (gVar == null || !gVar.f()) {
                    throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + bZ + " \n View Holder 2:" + b2);
                }
                throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + bZ + " \n View Holder 2:" + b2);
            }
        }
        Log.e("RecyclerView", "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + b3 + " cannot be found but it is necessary for " + b2);
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f2, float f3, boolean z2) {
        return getScrollingChildHelper().a(f2, f3, z2);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f2, float f3) {
        return getScrollingChildHelper().b(f2, f3);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().c(i2, i3, iArr, iArr2);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        return getScrollingChildHelper().d(i2, i3, i4, i5, iArr);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchSaveInstanceState(SparseArray sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z2;
        super.draw(canvas);
        int size = this.f1331n.size();
        boolean z3 = false;
        for (int i2 = 0; i2 < size; i2++) {
            ((m) this.f1331n.get(i2)).e(canvas, this, this.f1306a0);
        }
        C0427j c0427j = this.f1287F;
        if (c0427j == null || c0427j.c()) {
            z2 = false;
        } else {
            int iSave = canvas.save();
            int paddingBottom = this.f1317g ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, 0.0f);
            C0427j c0427j2 = this.f1287F;
            z2 = c0427j2 != null && c0427j2.a(canvas);
            canvas.restoreToCount(iSave);
        }
        C0427j c0427j3 = this.f1288G;
        if (c0427j3 != null && !c0427j3.c()) {
            int iSave2 = canvas.save();
            if (this.f1317g) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            C0427j c0427j4 = this.f1288G;
            z2 |= c0427j4 != null && c0427j4.a(canvas);
            canvas.restoreToCount(iSave2);
        }
        C0427j c0427j5 = this.f1289H;
        if (c0427j5 != null && !c0427j5.c()) {
            int iSave3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.f1317g ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(-paddingTop, -width);
            C0427j c0427j6 = this.f1289H;
            z2 |= c0427j6 != null && c0427j6.a(canvas);
            canvas.restoreToCount(iSave3);
        }
        C0427j c0427j7 = this.f1290I;
        if (c0427j7 != null && !c0427j7.c()) {
            int iSave4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.f1317g) {
                canvas.translate((-getWidth()) + getPaddingRight(), (-getHeight()) + getPaddingBottom());
            } else {
                canvas.translate(-getWidth(), -getHeight());
            }
            C0427j c0427j8 = this.f1290I;
            if (c0427j8 != null && c0427j8.a(canvas)) {
                z3 = true;
            }
            z2 |= z3;
            canvas.restoreToCount(iSave4);
        }
        if ((z2 || this.f1291J == null || this.f1331n.size() <= 0 || !this.f1291J.p()) ? z2 : true) {
            AbstractC0285k0.E(this);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j2) {
        return super.drawChild(canvas, view, j2);
    }

    public boolean e0() {
        return !this.f1338s || this.f1284C || this.f1311d.p();
    }

    public final boolean f0() {
        int iG = this.f1313e.g();
        for (int i2 = 0; i2 < iG; i2++) {
            B bZ = Z(this.f1313e.f(i2));
            if (bZ != null && !bZ.Q() && bZ.F()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0071  */
    @Override // android.view.ViewGroup, android.view.ViewParent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View focusSearch(android.view.View r8, int r9) {
        /*
            r7 = this;
            android.support.v7.widget.RecyclerView$n r0 = r7.f1329m
            android.view.View r0 = r0.J0(r8, r9)
            if (r0 == 0) goto L9
            return r0
        L9:
            android.support.v7.widget.RecyclerView$g r0 = r7.f1327l
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L1f
            android.support.v7.widget.RecyclerView$n r0 = r7.f1329m
            if (r0 == 0) goto L1f
            boolean r0 = r7.k0()
            if (r0 != 0) goto L1f
            boolean r0 = r7.f1341v
            if (r0 != 0) goto L1f
            r0 = 1
            goto L20
        L1f:
            r0 = 0
        L20:
            android.view.FocusFinder r3 = android.view.FocusFinder.getInstance()
            r4 = 0
            if (r0 == 0) goto L8f
            r5 = 2
            if (r9 == r5) goto L2c
            if (r9 != r1) goto L8f
        L2c:
            android.support.v7.widget.RecyclerView$n r0 = r7.f1329m
            boolean r0 = r0.l()
            if (r0 == 0) goto L43
            if (r9 != r5) goto L39
            r0 = 130(0x82, float:1.82E-43)
            goto L3b
        L39:
            r0 = 33
        L3b:
            android.view.View r0 = r3.findNextFocus(r7, r8, r0)
            if (r0 != 0) goto L43
            r0 = 1
            goto L44
        L43:
            r0 = 0
        L44:
            if (r0 != 0) goto L6f
            android.support.v7.widget.RecyclerView$n r6 = r7.f1329m
            boolean r6 = r6.k()
            if (r6 == 0) goto L6f
            android.support.v7.widget.RecyclerView$n r0 = r7.f1329m
            int r0 = r0.W()
            if (r0 != r1) goto L58
            r0 = 1
            goto L59
        L58:
            r0 = 0
        L59:
            if (r9 != r5) goto L5d
            r5 = 1
            goto L5e
        L5d:
            r5 = 0
        L5e:
            r0 = r0 ^ r5
            if (r0 == 0) goto L64
            r0 = 66
            goto L66
        L64:
            r0 = 17
        L66:
            android.view.View r0 = r3.findNextFocus(r7, r8, r0)
            if (r0 != 0) goto L6d
            goto L6e
        L6d:
            r1 = 0
        L6e:
            r0 = r1
        L6f:
            if (r0 == 0) goto L8a
            r7.u()
            android.view.View r0 = r7.P(r8)
            if (r0 != 0) goto L7b
            return r4
        L7b:
            r7.J()
            android.support.v7.widget.RecyclerView$n r0 = r7.f1329m
            android.support.v7.widget.RecyclerView$u r1 = r7.f1307b
            android.support.v7.widget.RecyclerView$y r4 = r7.f1306a0
            r0.C0(r8, r9, r1, r4)
            r7.P0(r2)
        L8a:
            android.view.View r0 = r3.findNextFocus(r7, r8, r9)
            goto Lb3
        L8f:
            android.view.View r1 = r3.findNextFocus(r7, r8, r9)
            if (r1 != 0) goto Lb2
            if (r0 == 0) goto Lb2
            r7.u()
            android.view.View r0 = r7.P(r8)
            if (r0 != 0) goto La1
            return r4
        La1:
            r7.J()
            android.support.v7.widget.RecyclerView$n r0 = r7.f1329m
            android.support.v7.widget.RecyclerView$u r1 = r7.f1307b
            android.support.v7.widget.RecyclerView$y r3 = r7.f1306a0
            android.view.View r0 = r0.C0(r8, r9, r1, r3)
            r7.P0(r2)
            goto Lb3
        Lb2:
            r0 = r1
        Lb3:
            boolean r1 = r7.l0(r8, r0, r9)
            if (r1 == 0) goto Lba
            goto Lbe
        Lba:
            android.view.View r0 = super.focusSearch(r8, r9)
        Lbe:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.focusSearch(android.view.View, int):android.view.View");
    }

    public final void g(B b2) {
        View view = b2.f1354a;
        boolean z2 = view.getParent() == this;
        this.f1307b.I(Y(view));
        if (b2.E()) {
            this.f1313e.c(view, -1, view.getLayoutParams(), true);
        } else if (z2) {
            this.f1313e.k(view);
        } else {
            this.f1313e.b(view, true);
        }
    }

    public void g0() {
        this.f1311d = new b(new f());
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        n nVar = this.f1329m;
        if (nVar != null) {
            return nVar.B();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager");
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        n nVar = this.f1329m;
        if (nVar != null) {
            return nVar.C(getContext(), attributeSet);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager");
    }

    public g getAdapter() {
        return this.f1327l;
    }

    @Override // android.view.View
    public int getBaseline() {
        n nVar = this.f1329m;
        return nVar != null ? nVar.E() : super.getBaseline();
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i2, int i3) {
        j jVar = this.f1322i0;
        return jVar == null ? super.getChildDrawingOrder(i2, i3) : jVar.a(i2, i3);
    }

    public android.support.v7.widget.j getCompatAccessibilityDelegate() {
        return this.f1320h0;
    }

    public k getItemAnimator() {
        return this.f1291J;
    }

    public n getLayoutManager() {
        return this.f1329m;
    }

    public int getMaxFlingVelocity() {
        return this.f1301T;
    }

    public int getMinFlingVelocity() {
        return this.f1300S;
    }

    public q getOnFlingListener() {
        return null;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.f1303V;
    }

    public t getRecycledViewPool() {
        return this.f1307b.i();
    }

    public int getScrollState() {
        return this.f1292K;
    }

    public void h(m mVar) {
        i(mVar, -1);
    }

    public final void h0() {
        this.f1313e = new android.support.v7.widget.c(new e());
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().e();
    }

    public void i(m mVar, int i2) {
        n nVar = this.f1329m;
        if (nVar != null) {
            nVar.g("Cannot add item decoration during a scroll  or layout");
        }
        if (this.f1331n.isEmpty()) {
            setWillNotDraw(false);
        }
        if (i2 < 0) {
            this.f1331n.add(mVar);
        } else {
            this.f1331n.add(i2, mVar);
        }
        n0();
        requestLayout();
    }

    public void i0() {
        this.f1290I = null;
        this.f1288G = null;
        this.f1289H = null;
        this.f1287F = null;
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return this.f1336q;
    }

    @Override // android.view.View, k.Z
    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().f();
    }

    public void j(p pVar) {
        if (this.f1283B == null) {
            this.f1283B = new ArrayList();
        }
        this.f1283B.add(pVar);
    }

    public boolean j0() {
        AccessibilityManager accessibilityManager = this.f1282A;
        return accessibilityManager != null && accessibilityManager.isEnabled();
    }

    public void k(r rVar) {
        this.f1333o.add(rVar);
    }

    public boolean k0() {
        return this.f1285D > 0;
    }

    public void l(s sVar) {
        if (this.f1310c0 == null) {
            this.f1310c0 = new ArrayList();
        }
        this.f1310c0.add(sVar);
    }

    public final boolean l0(View view, View view2, int i2) {
        if (view2 == null || view2 == this) {
            return false;
        }
        if (view == null) {
            return true;
        }
        if (i2 != 2 && i2 != 1) {
            return m0(view, view2, i2);
        }
        if (m0(view, view2, (i2 == 2) ^ (this.f1329m.W() == 1) ? 66 : 17)) {
            return true;
        }
        return i2 == 2 ? m0(view, view2, 130) : m0(view, view2, 33);
    }

    public void m(B b2, k.c cVar, k.c cVar2) {
        b2.N(false);
        if (this.f1291J.a(b2, cVar, cVar2)) {
            B0();
        }
    }

    public final boolean m0(View view, View view2, int i2) {
        this.f1321i.set(0, 0, view.getWidth(), view.getHeight());
        this.f1323j.set(0, 0, view2.getWidth(), view2.getHeight());
        offsetDescendantRectToMyCoords(view, this.f1321i);
        offsetDescendantRectToMyCoords(view2, this.f1323j);
        if (i2 == 17) {
            Rect rect = this.f1321i;
            int i3 = rect.right;
            Rect rect2 = this.f1323j;
            int i4 = rect2.right;
            return (i3 > i4 || rect.left >= i4) && rect.left > rect2.left;
        }
        if (i2 == 33) {
            Rect rect3 = this.f1321i;
            int i5 = rect3.bottom;
            Rect rect4 = this.f1323j;
            int i6 = rect4.bottom;
            return (i5 > i6 || rect3.top >= i6) && rect3.top > rect4.top;
        }
        if (i2 == 66) {
            Rect rect5 = this.f1321i;
            int i7 = rect5.left;
            Rect rect6 = this.f1323j;
            int i8 = rect6.left;
            return (i7 < i8 || rect5.right <= i8) && rect5.right < rect6.right;
        }
        if (i2 != 130) {
            throw new IllegalArgumentException("direction must be absolute. received:" + i2);
        }
        Rect rect7 = this.f1321i;
        int i9 = rect7.top;
        Rect rect8 = this.f1323j;
        int i10 = rect8.top;
        return (i9 < i10 || rect7.bottom <= i10) && rect7.bottom < rect8.bottom;
    }

    public final void n(B b2, B b3, k.c cVar, k.c cVar2, boolean z2, boolean z3) {
        b2.N(false);
        if (z2) {
            g(b2);
        }
        if (b2 != b3) {
            if (z3) {
                g(b3);
            }
            b2.f1360g = b3;
            g(b2);
            this.f1307b.I(b2);
            b3.N(false);
            b3.f1361h = b2;
        }
        if (this.f1291J.b(b2, b3, cVar, cVar2)) {
            B0();
        }
    }

    public void n0() {
        int iJ = this.f1313e.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            ((o) this.f1313e.i(i2).getLayoutParams()).f1404c = true;
        }
        this.f1307b.s();
    }

    public void o(B b2, k.c cVar, k.c cVar2) {
        g(b2);
        b2.N(false);
        if (this.f1291J.c(b2, cVar, cVar2)) {
            B0();
        }
    }

    public void o0() {
        int iJ = this.f1313e.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            B bZ = Z(this.f1313e.i(i2));
            if (bZ != null && !bZ.Q()) {
                bZ.j(6);
            }
        }
        n0();
        this.f1307b.t();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f1285D = 0;
        this.f1336q = true;
        this.f1338s = this.f1338s && !isLayoutRequested();
        n nVar = this.f1329m;
        if (nVar != null) {
            nVar.x(this);
        }
        this.f1318g0 = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        k kVar = this.f1291J;
        if (kVar != null) {
            kVar.k();
        }
        Y0();
        this.f1336q = false;
        n nVar = this.f1329m;
        if (nVar != null) {
            nVar.y(this, this.f1307b);
        }
        removeCallbacks(this.f1334o0);
        this.f1315f.j();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.f1331n.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((m) this.f1331n.get(i2)).d(canvas, this, this.f1306a0);
        }
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (this.f1329m != null && !this.f1341v && (motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8) {
            float f2 = this.f1329m.l() ? -W.c(motionEvent, 9) : 0.0f;
            float fC = this.f1329m.k() ? W.c(motionEvent, 10) : 0.0f;
            if (f2 != 0.0f || fC != 0.0f) {
                float scrollFactor = getScrollFactor();
                S0((int) (fC * scrollFactor), (int) (f2 * scrollFactor), motionEvent);
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00c6  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r9) {
        /*
            Method dump skipped, instruction units count: 301
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        i.f.a("RV OnLayout");
        B();
        i.f.b();
        this.f1338s = true;
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        n nVar = this.f1329m;
        if (nVar == null) {
            w(i2, i3);
            return;
        }
        boolean z2 = false;
        if (nVar.f1392e) {
            int mode = View.MeasureSpec.getMode(i2);
            int mode2 = View.MeasureSpec.getMode(i3);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z2 = true;
            }
            this.f1329m.S0(this.f1307b, this.f1306a0, i2, i3);
            if (z2 || this.f1327l == null) {
                return;
            }
            if (this.f1306a0.f1419b == 1) {
                C();
            }
            this.f1329m.s1(i2, i3);
            this.f1306a0.f1429l = true;
            D();
            this.f1329m.v1(i2, i3);
            if (this.f1329m.y1()) {
                this.f1329m.s1(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                this.f1306a0.f1429l = true;
                D();
                this.f1329m.v1(i2, i3);
                return;
            }
            return;
        }
        if (this.f1337r) {
            nVar.S0(this.f1307b, this.f1306a0, i2, i3);
            return;
        }
        if (this.f1344y) {
            J();
            D0();
            y yVar = this.f1306a0;
            if (yVar.f1427j) {
                yVar.f1425h = true;
            } else {
                this.f1311d.j();
                this.f1306a0.f1425h = false;
            }
            this.f1344y = false;
            P0(false);
        }
        g gVar = this.f1327l;
        if (gVar != null) {
            this.f1306a0.f1421d = gVar.c();
        } else {
            this.f1306a0.f1421d = 0;
        }
        J();
        this.f1329m.S0(this.f1307b, this.f1306a0, i2, i3);
        P0(false);
        this.f1306a0.f1425h = false;
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i2, Rect rect) {
        if (k0()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i2, rect);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof x)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        x xVar = (x) parcelable;
        this.f1309c = xVar;
        super.onRestoreInstanceState(xVar.a());
        n nVar = this.f1329m;
        if (nVar == null || (parcelable2 = this.f1309c.f1417c) == null) {
            return;
        }
        nVar.V0(parcelable2);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        x xVar = new x(super.onSaveInstanceState());
        x xVar2 = this.f1309c;
        if (xVar2 != null) {
            xVar.b(xVar2);
        } else {
            n nVar = this.f1329m;
            if (nVar != null) {
                xVar.f1417c = nVar.W0();
            } else {
                xVar.f1417c = null;
            }
        }
        return xVar;
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 == i4 && i3 == i5) {
            return;
        }
        i0();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0104  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r13) {
        /*
            Method dump skipped, instruction units count: 427
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void p(String str) {
        if (k0()) {
            if (str != null) {
                throw new IllegalStateException(str);
            }
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling");
        }
        if (this.f1286E > 0) {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks might be run during a measure & layout pass where you cannot change the RecyclerView data. Any method call that might change the structure of the RecyclerView or the adapter contents should be postponed to the next frame.", new IllegalStateException(""));
        }
    }

    public void p0(int i2) {
        int iG = this.f1313e.g();
        for (int i3 = 0; i3 < iG; i3++) {
            this.f1313e.f(i3).offsetLeftAndRight(i2);
        }
    }

    public boolean q(B b2) {
        k kVar = this.f1291J;
        return kVar == null || kVar.g(b2, b2.w());
    }

    public void q0(int i2) {
        int iG = this.f1313e.g();
        for (int i3 = 0; i3 < iG; i3++) {
            this.f1313e.f(i3).offsetTopAndBottom(i2);
        }
    }

    public final void r() {
        O0();
        setScrollState(0);
    }

    public void r0(int i2, int i3) {
        int iJ = this.f1313e.j();
        for (int i4 = 0; i4 < iJ; i4++) {
            B bZ = Z(this.f1313e.i(i4));
            if (bZ != null && !bZ.Q() && bZ.f1355b >= i2) {
                bZ.H(i3, false);
                this.f1306a0.f1424g = true;
            }
        }
        this.f1307b.u(i2, i3);
        requestLayout();
    }

    @Override // android.view.ViewGroup
    public void removeDetachedView(View view, boolean z2) {
        B bZ = Z(view);
        if (bZ != null) {
            if (bZ.E()) {
                bZ.n();
            } else if (!bZ.Q()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + bZ);
            }
        }
        z(view);
        super.removeDetachedView(view, z2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (!this.f1329m.T0(this, this.f1306a0, view, view2) && view2 != null) {
            this.f1321i.set(0, 0, view2.getWidth(), view2.getHeight());
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            if (layoutParams instanceof o) {
                o oVar = (o) layoutParams;
                if (!oVar.f1404c) {
                    Rect rect = oVar.f1403b;
                    Rect rect2 = this.f1321i;
                    rect2.left -= rect.left;
                    rect2.right += rect.right;
                    rect2.top -= rect.top;
                    rect2.bottom += rect.bottom;
                }
            }
            offsetDescendantRectToMyCoords(view2, this.f1321i);
            offsetRectIntoDescendantCoords(view, this.f1321i);
            requestChildRectangleOnScreen(view, this.f1321i, !this.f1338s);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z2) {
        return this.f1329m.j1(this, view, rect, z2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z2) {
        int size = this.f1333o.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((r) this.f1333o.get(i2)).b(z2);
        }
        super.requestDisallowInterceptTouchEvent(z2);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.f1339t != 0 || this.f1341v) {
            this.f1340u = true;
        } else {
            super.requestLayout();
        }
    }

    public void s() {
        int iJ = this.f1313e.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            B bZ = Z(this.f1313e.i(i2));
            if (!bZ.Q()) {
                bZ.k();
            }
        }
        this.f1307b.d();
    }

    public void s0(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int iJ = this.f1313e.j();
        if (i2 < i3) {
            i6 = -1;
            i5 = i2;
            i4 = i3;
        } else {
            i4 = i2;
            i5 = i3;
            i6 = 1;
        }
        for (int i8 = 0; i8 < iJ; i8++) {
            B bZ = Z(this.f1313e.i(i8));
            if (bZ != null && (i7 = bZ.f1355b) >= i5 && i7 <= i4) {
                if (i7 == i2) {
                    bZ.H(i3 - i2, false);
                } else {
                    bZ.H(i6, false);
                }
                this.f1306a0.f1424g = true;
            }
        }
        this.f1307b.v(i2, i3);
        requestLayout();
    }

    @Override // android.view.View
    public void scrollBy(int i2, int i3) {
        n nVar = this.f1329m;
        if (nVar == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.f1341v) {
            return;
        }
        boolean zK = nVar.k();
        boolean zL = this.f1329m.l();
        if (zK || zL) {
            if (!zK) {
                i2 = 0;
            }
            if (!zL) {
                i3 = 0;
            }
            S0(i2, i3, null);
        }
    }

    @Override // android.view.View
    public void scrollTo(int i2, int i3) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (W0(accessibilityEvent)) {
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent);
    }

    public void setAccessibilityDelegateCompat(android.support.v7.widget.j jVar) {
        this.f1320h0 = jVar;
        AbstractC0285k0.J(this, jVar);
    }

    public void setAdapter(g gVar) {
        setLayoutFrozen(false);
        U0(gVar, false, true);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(j jVar) {
        if (jVar == this.f1322i0) {
            return;
        }
        this.f1322i0 = jVar;
        setChildrenDrawingOrderEnabled(jVar != null);
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z2) {
        if (z2 != this.f1317g) {
            i0();
        }
        this.f1317g = z2;
        super.setClipToPadding(z2);
        if (this.f1338s) {
            requestLayout();
        }
    }

    public void setHasFixedSize(boolean z2) {
        this.f1337r = z2;
    }

    public void setItemAnimator(k kVar) {
        k kVar2 = this.f1291J;
        if (kVar2 != null) {
            kVar2.k();
            this.f1291J.w(null);
        }
        this.f1291J = kVar;
        if (kVar != null) {
            kVar.w(this.f1316f0);
        }
    }

    public void setItemViewCacheSize(int i2) {
        this.f1307b.H(i2);
    }

    public void setLayoutFrozen(boolean z2) {
        if (z2 != this.f1341v) {
            p("Do not setLayoutFrozen in layout or scroll");
            if (z2) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0));
                this.f1341v = true;
                this.f1342w = true;
                Y0();
                return;
            }
            this.f1341v = false;
            if (this.f1340u && this.f1329m != null && this.f1327l != null) {
                requestLayout();
            }
            this.f1340u = false;
        }
    }

    public void setLayoutManager(n nVar) {
        if (nVar == this.f1329m) {
            return;
        }
        Y0();
        if (this.f1329m != null) {
            k kVar = this.f1291J;
            if (kVar != null) {
                kVar.k();
            }
            this.f1329m.c1(this.f1307b);
            this.f1329m.d1(this.f1307b);
            this.f1307b.c();
            if (this.f1336q) {
                this.f1329m.y(this, this.f1307b);
            }
            this.f1329m.w1(null);
            this.f1329m = null;
        } else {
            this.f1307b.c();
        }
        this.f1313e.o();
        this.f1329m = nVar;
        if (nVar != null) {
            if (nVar.f1389b != null) {
                throw new IllegalArgumentException("LayoutManager " + nVar + " is already attached to a RecyclerView: " + nVar.f1389b);
            }
            nVar.w1(this);
            if (this.f1336q) {
                this.f1329m.x(this);
            }
        }
        requestLayout();
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z2) {
        getScrollingChildHelper().g(z2);
    }

    public void setOnFlingListener(q qVar) {
    }

    @Deprecated
    public void setOnScrollListener(s sVar) {
        this.f1308b0 = sVar;
    }

    public void setPreserveFocusAfterLayout(boolean z2) {
        this.f1303V = z2;
    }

    public void setRecycledViewPool(t tVar) {
        this.f1307b.F(tVar);
    }

    public void setRecyclerListener(v vVar) {
    }

    public void setScrollState(int i2) {
        if (i2 == this.f1292K) {
            return;
        }
        this.f1292K = i2;
        if (i2 != 2) {
            Z0();
        }
        H(i2);
    }

    public void setScrollingTouchSlop(int i2) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i2 != 0) {
            if (i2 == 1) {
                this.f1299R = viewConfiguration.getScaledPagingTouchSlop();
                return;
            }
            Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + i2 + "; using default value");
        }
        this.f1299R = viewConfiguration.getScaledTouchSlop();
    }

    public void setViewCacheExtension(z zVar) {
        this.f1307b.G(zVar);
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i2) {
        return getScrollingChildHelper().h(i2);
    }

    @Override // android.view.View, k.Z
    public void stopNestedScroll() {
        getScrollingChildHelper().i();
    }

    public void t(int i2, int i3) {
        C0427j c0427j = this.f1287F;
        boolean zG = (c0427j == null || c0427j.c() || i2 <= 0) ? false : this.f1287F.g();
        C0427j c0427j2 = this.f1289H;
        if (c0427j2 != null && !c0427j2.c() && i2 < 0) {
            zG |= this.f1289H.g();
        }
        C0427j c0427j3 = this.f1288G;
        if (c0427j3 != null && !c0427j3.c() && i3 > 0) {
            zG |= this.f1288G.g();
        }
        C0427j c0427j4 = this.f1290I;
        if (c0427j4 != null && !c0427j4.c() && i3 < 0) {
            zG |= this.f1290I.g();
        }
        if (zG) {
            AbstractC0285k0.E(this);
        }
    }

    public void t0(int i2, int i3, boolean z2) {
        int i4 = i2 + i3;
        int iJ = this.f1313e.j();
        for (int i5 = 0; i5 < iJ; i5++) {
            B bZ = Z(this.f1313e.i(i5));
            if (bZ != null && !bZ.Q()) {
                int i6 = bZ.f1355b;
                if (i6 >= i4) {
                    bZ.H(-i3, z2);
                    this.f1306a0.f1424g = true;
                } else if (i6 >= i2) {
                    bZ.q(i2 - 1, -i3, z2);
                    this.f1306a0.f1424g = true;
                }
            }
        }
        this.f1307b.w(i2, i3, z2);
        requestLayout();
    }

    public void u() {
        if (!this.f1338s || this.f1284C) {
            i.f.a("RV FullInvalidate");
            B();
            i.f.b();
            return;
        }
        if (this.f1311d.p()) {
            if (!this.f1311d.o(4) || this.f1311d.o(11)) {
                if (this.f1311d.p()) {
                    i.f.a("RV FullInvalidate");
                    B();
                    i.f.b();
                    return;
                }
                return;
            }
            i.f.a("RV PartialInvalidate");
            J();
            this.f1311d.v();
            if (!this.f1340u) {
                if (f0()) {
                    B();
                } else {
                    this.f1311d.i();
                }
            }
            P0(true);
            i.f.b();
        }
    }

    public void u0(View view) {
    }

    public final void v(Context context, String str, AttributeSet attributeSet, int i2, int i3) {
        Object[] objArr;
        Constructor constructor;
        if (str != null) {
            String strTrim = str.trim();
            if (strTrim.length() != 0) {
                String strB0 = b0(context, strTrim);
                try {
                    Class<? extends U> clsAsSubclass = (isInEditMode() ? getClass().getClassLoader() : context.getClassLoader()).loadClass(strB0).asSubclass(n.class);
                    try {
                        constructor = clsAsSubclass.getConstructor(u0);
                        objArr = new Object[]{context, attributeSet, Integer.valueOf(i2), Integer.valueOf(i3)};
                    } catch (NoSuchMethodException e2) {
                        objArr = null;
                        try {
                            constructor = clsAsSubclass.getConstructor(null);
                        } catch (NoSuchMethodException e3) {
                            e3.initCause(e2);
                            throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + strB0, e3);
                        }
                    }
                    constructor.setAccessible(true);
                    setLayoutManager((n) constructor.newInstance(objArr));
                } catch (ClassCastException e4) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + strB0, e4);
                } catch (ClassNotFoundException e5) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + strB0, e5);
                } catch (IllegalAccessException e6) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + strB0, e6);
                } catch (InstantiationException e7) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + strB0, e7);
                } catch (InvocationTargetException e8) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + strB0, e8);
                }
            }
        }
    }

    public void v0(View view) {
    }

    public void w(int i2, int i3) {
        setMeasuredDimension(n.n(i2, getPaddingLeft() + getPaddingRight(), AbstractC0285k0.p(this)), n.n(i3, getPaddingTop() + getPaddingBottom(), AbstractC0285k0.o(this)));
    }

    public void w0() {
        this.f1285D++;
    }

    public final boolean x(int i2, int i3) {
        R(this.f1324j0);
        int[] iArr = this.f1324j0;
        return (iArr[0] == i2 && iArr[1] == i3) ? false : true;
    }

    public void x0() {
        int i2 = this.f1285D - 1;
        this.f1285D = i2;
        if (i2 < 1) {
            this.f1285D = 0;
            A();
        }
    }

    public void y(View view) {
        B bZ = Z(view);
        u0(view);
        g gVar = this.f1327l;
        if (gVar != null && bZ != null) {
            gVar.q(bZ);
        }
        List list = this.f1283B;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                ((p) this.f1283B.get(size)).a(view);
            }
        }
    }

    public final void y0(MotionEvent motionEvent) {
        int iA = W.a(motionEvent);
        if (motionEvent.getPointerId(iA) == this.f1293L) {
            int i2 = iA == 0 ? 1 : 0;
            this.f1293L = motionEvent.getPointerId(i2);
            int x2 = (int) (motionEvent.getX(i2) + 0.5f);
            this.f1297P = x2;
            this.f1295N = x2;
            int y2 = (int) (motionEvent.getY(i2) + 0.5f);
            this.f1298Q = y2;
            this.f1296O = y2;
        }
    }

    public void z(View view) {
        B bZ = Z(view);
        v0(view);
        g gVar = this.f1327l;
        if (gVar != null && bZ != null) {
            gVar.r(bZ);
        }
        List list = this.f1283B;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                ((p) this.f1283B.get(size)).b(view);
            }
        }
    }

    public void z0(int i2) {
    }

    public RecyclerView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f1305a = new w();
        this.f1307b = new u();
        this.f1315f = new android.support.v7.widget.o();
        this.f1319h = new RunnableC0094a();
        this.f1321i = new Rect();
        this.f1323j = new Rect();
        this.f1325k = new RectF();
        this.f1331n = new ArrayList();
        this.f1333o = new ArrayList();
        this.f1339t = 0;
        this.f1284C = false;
        this.f1285D = 0;
        this.f1286E = 0;
        this.f1291J = new android.support.v7.widget.d();
        this.f1292K = 0;
        this.f1293L = -1;
        this.f1302U = Float.MIN_VALUE;
        boolean z2 = true;
        this.f1303V = true;
        this.f1304W = new A();
        this.f1306a0 = new y();
        this.f1312d0 = false;
        this.f1314e0 = false;
        this.f1316f0 = new l();
        this.f1318g0 = false;
        this.f1324j0 = new int[2];
        this.f1328l0 = new int[2];
        this.f1330m0 = new int[2];
        this.f1332n0 = new int[2];
        this.f1334o0 = new RunnableC0095b();
        this.p0 = new d();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, r0, i2, 0);
            this.f1317g = typedArrayObtainStyledAttributes.getBoolean(0, true);
            typedArrayObtainStyledAttributes.recycle();
        } else {
            this.f1317g = true;
        }
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        int i3 = Build.VERSION.SDK_INT;
        this.f1345z = i3 >= 16;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f1299R = viewConfiguration.getScaledTouchSlop();
        this.f1300S = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f1301T = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.f1291J.w(this.f1316f0);
        g0();
        h0();
        if (AbstractC0285k0.i(this) == 0) {
            AbstractC0285k0.P(this, 1);
        }
        this.f1282A = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new android.support.v7.widget.j(this));
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, AbstractC0469c.f7913a, i2, 0);
            String string = typedArrayObtainStyledAttributes2.getString(AbstractC0469c.f7916d);
            if (typedArrayObtainStyledAttributes2.getInt(AbstractC0469c.f7915c, -1) == -1) {
                setDescendantFocusability(PKIFailureInfo.transactionIdInUse);
            }
            typedArrayObtainStyledAttributes2.recycle();
            v(context, string, attributeSet, i2, 0);
            if (i3 >= 21) {
                TypedArray typedArrayObtainStyledAttributes3 = context.obtainStyledAttributes(attributeSet, q0, i2, 0);
                z2 = typedArrayObtainStyledAttributes3.getBoolean(0, true);
                typedArrayObtainStyledAttributes3.recycle();
            }
        } else {
            setDescendantFocusability(PKIFailureInfo.transactionIdInUse);
        }
        setNestedScrollingEnabled(z2);
    }

    public static class o extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public B f1402a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Rect f1403b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1404c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1405d;

        public o(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1403b = new Rect();
            this.f1404c = true;
            this.f1405d = false;
        }

        public int a() {
            return this.f1402a.u();
        }

        public boolean b() {
            return this.f1402a.F();
        }

        public boolean c() {
            return this.f1402a.C();
        }

        public boolean d() {
            return this.f1402a.A();
        }

        public o(int i2, int i3) {
            super(i2, i3);
            this.f1403b = new Rect();
            this.f1404c = true;
            this.f1405d = false;
        }

        public o(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1403b = new Rect();
            this.f1404c = true;
            this.f1405d = false;
        }

        public o(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1403b = new Rect();
            this.f1404c = true;
            this.f1405d = false;
        }

        public o(o oVar) {
            super((ViewGroup.LayoutParams) oVar);
            this.f1403b = new Rect();
            this.f1404c = true;
            this.f1405d = false;
        }
    }

    public static class x extends android.support.v4.view.a {
        public static final Parcelable.Creator<x> CREATOR = AbstractC0251b.a(new a());

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Parcelable f1417c;

        public static class a implements i.c {
            @Override // i.c
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public x createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new x(parcel, classLoader);
            }

            @Override // i.c
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public x[] newArray(int i2) {
                return new x[i2];
            }
        }

        public x(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1417c = parcel.readParcelable(classLoader == null ? n.class.getClassLoader() : classLoader);
        }

        public void b(x xVar) {
            this.f1417c = xVar.f1417c;
        }

        @Override // android.support.v4.view.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeParcelable(this.f1417c, 0);
        }

        public x(Parcelable parcelable) {
            super(parcelable);
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        n nVar = this.f1329m;
        if (nVar != null) {
            return nVar.D(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager");
    }
}

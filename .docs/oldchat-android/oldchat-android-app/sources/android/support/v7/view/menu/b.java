package android.support.v7.view.menu;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.view.menu.i;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import k.AbstractC0285k0;
import k.r;
import p.AbstractC0463d;
import w.u;
import x.O;
import x.T;

/* JADX INFO: loaded from: classes.dex */
public final class b extends u implements i, View.OnKeyListener, PopupWindow.OnDismissListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f1001b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1002c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1003d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1004e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f1005f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Handler f1006g;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public View f1013n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public View f1014o;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f1016q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1017r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f1018s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f1019t;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f1021v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public i.a f1022w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public ViewTreeObserver f1023x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public PopupWindow.OnDismissListener f1024y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f1025z;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final List f1007h = new LinkedList();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List f1008i = new ArrayList();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ViewTreeObserver.OnGlobalLayoutListener f1009j = new a();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final O f1010k = new C0011b();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1011l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1012m = 0;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f1020u = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f1015p = D();

    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!b.this.h() || b.this.f1008i.size() <= 0 || ((c) b.this.f1008i.get(0)).f1032a.p()) {
                return;
            }
            View view = b.this.f1014o;
            if (view == null || !view.isShown()) {
                b.this.dismiss();
                return;
            }
            Iterator it = b.this.f1008i.iterator();
            while (it.hasNext()) {
                ((c) it.next()).f1032a.c();
            }
        }
    }

    /* JADX INFO: renamed from: android.support.v7.view.menu.b$b, reason: collision with other inner class name */
    public class C0011b implements O {

        /* JADX INFO: renamed from: android.support.v7.view.menu.b$b$a */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ c f1028a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ MenuItem f1029b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ e f1030c;

            public a(c cVar, MenuItem menuItem, e eVar) {
                this.f1028a = cVar;
                this.f1029b = menuItem;
                this.f1030c = eVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                c cVar = this.f1028a;
                if (cVar != null) {
                    b.this.f1025z = true;
                    cVar.f1033b.e(false);
                    b.this.f1025z = false;
                }
                if (this.f1029b.isEnabled() && this.f1029b.hasSubMenu()) {
                    this.f1030c.K(this.f1029b, 0);
                }
            }
        }

        public C0011b() {
        }

        @Override // x.O
        public void a(e eVar, MenuItem menuItem) {
            b.this.f1006g.removeCallbacksAndMessages(null);
            int size = b.this.f1008i.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    i2 = -1;
                    break;
                } else if (eVar == ((c) b.this.f1008i.get(i2)).f1033b) {
                    break;
                } else {
                    i2++;
                }
            }
            if (i2 == -1) {
                return;
            }
            int i3 = i2 + 1;
            b.this.f1006g.postAtTime(new a(i3 < b.this.f1008i.size() ? (c) b.this.f1008i.get(i3) : null, menuItem, eVar), eVar, SystemClock.uptimeMillis() + 200);
        }

        @Override // x.O
        public void b(e eVar, MenuItem menuItem) {
            b.this.f1006g.removeCallbacksAndMessages(eVar);
        }
    }

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final T f1032a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final e f1033b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f1034c;

        public c(T t2, e eVar, int i2) {
            this.f1032a = t2;
            this.f1033b = eVar;
            this.f1034c = i2;
        }

        public ListView a() {
            return this.f1032a.i();
        }
    }

    public b(Context context, View view, int i2, int i3, boolean z2) {
        this.f1001b = context;
        this.f1013n = view;
        this.f1003d = i2;
        this.f1004e = i3;
        this.f1005f = z2;
        Resources resources = context.getResources();
        this.f1002c = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(AbstractC0463d.f7744b));
        this.f1006g = new Handler();
    }

    public final int A(e eVar) {
        int size = this.f1008i.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (eVar == ((c) this.f1008i.get(i2)).f1033b) {
                return i2;
            }
        }
        return -1;
    }

    public final MenuItem B(e eVar, e eVar2) {
        int size = eVar.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = eVar.getItem(i2);
            if (item.hasSubMenu() && eVar2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    public final View C(c cVar, e eVar) {
        d dVar;
        int headersCount;
        int firstVisiblePosition;
        MenuItem menuItemB = B(cVar.f1033b, eVar);
        if (menuItemB == null) {
            return null;
        }
        ListView listViewA = cVar.a();
        ListAdapter adapter = listViewA.getAdapter();
        int i2 = 0;
        if (adapter instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
            headersCount = headerViewListAdapter.getHeadersCount();
            dVar = (d) headerViewListAdapter.getWrappedAdapter();
        } else {
            dVar = (d) adapter;
            headersCount = 0;
        }
        int count = dVar.getCount();
        while (true) {
            if (i2 >= count) {
                i2 = -1;
                break;
            }
            if (menuItemB == dVar.getItem(i2)) {
                break;
            }
            i2++;
        }
        if (i2 != -1 && (firstVisiblePosition = (i2 + headersCount) - listViewA.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < listViewA.getChildCount()) {
            return listViewA.getChildAt(firstVisiblePosition);
        }
        return null;
    }

    public final int D() {
        return AbstractC0285k0.k(this.f1013n) == 1 ? 0 : 1;
    }

    public final int E(int i2) {
        List list = this.f1008i;
        ListView listViewA = ((c) list.get(list.size() - 1)).a();
        int[] iArr = new int[2];
        listViewA.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.f1014o.getWindowVisibleDisplayFrame(rect);
        return this.f1015p == 1 ? (iArr[0] + listViewA.getWidth()) + i2 > rect.right ? 0 : 1 : iArr[0] - i2 < 0 ? 1 : 0;
    }

    public final void F(e eVar) {
        c cVar;
        View viewC;
        int i2;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f1001b);
        d dVar = new d(eVar, layoutInflaterFrom, this.f1005f);
        if (!h() && this.f1020u) {
            dVar.d(true);
        } else if (h()) {
            dVar.d(u.x(eVar));
        }
        int iO = u.o(dVar, null, this.f1001b, this.f1002c);
        T tZ = z();
        tZ.r(dVar);
        tZ.v(iO);
        tZ.w(this.f1012m);
        if (this.f1008i.size() > 0) {
            List list = this.f1008i;
            cVar = (c) list.get(list.size() - 1);
            viewC = C(cVar, eVar);
        } else {
            cVar = null;
            viewC = null;
        }
        if (viewC != null) {
            tZ.L(false);
            tZ.I(null);
            int iE = E(iO);
            boolean z2 = iE == 1;
            this.f1015p = iE;
            int[] iArr = new int[2];
            viewC.getLocationInWindow(iArr);
            int iK = cVar.f1032a.k() + iArr[0];
            int iM = cVar.f1032a.m() + iArr[1];
            if ((this.f1012m & 5) == 5) {
                if (!z2) {
                    iO = viewC.getWidth();
                    i2 = iK - iO;
                }
                i2 = iK + iO;
            } else {
                if (z2) {
                    iO = viewC.getWidth();
                    i2 = iK + iO;
                }
                i2 = iK - iO;
            }
            tZ.y(i2);
            tZ.G(iM);
        } else {
            if (this.f1016q) {
                tZ.y(this.f1018s);
            }
            if (this.f1017r) {
                tZ.G(this.f1019t);
            }
            tZ.x(n());
        }
        this.f1008i.add(new c(tZ, eVar, this.f1015p));
        tZ.c();
        if (cVar == null && this.f1021v && eVar.x() != null) {
            ListView listViewI = tZ.i();
            FrameLayout frameLayout = (FrameLayout) layoutInflaterFrom.inflate(p.g.f7827k, (ViewGroup) listViewI, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            frameLayout.setEnabled(false);
            textView.setText(eVar.x());
            listViewI.addHeaderView(frameLayout, null, false);
            tZ.c();
        }
    }

    @Override // android.support.v7.view.menu.i
    public void a(e eVar, boolean z2) {
        int iA = A(eVar);
        if (iA < 0) {
            return;
        }
        int i2 = iA + 1;
        if (i2 < this.f1008i.size()) {
            ((c) this.f1008i.get(i2)).f1033b.e(false);
        }
        c cVar = (c) this.f1008i.remove(iA);
        cVar.f1033b.N(this);
        if (this.f1025z) {
            cVar.f1032a.J(null);
            cVar.f1032a.t(0);
        }
        cVar.f1032a.dismiss();
        int size = this.f1008i.size();
        if (size > 0) {
            this.f1015p = ((c) this.f1008i.get(size - 1)).f1034c;
        } else {
            this.f1015p = D();
        }
        if (size != 0) {
            if (z2) {
                ((c) this.f1008i.get(0)).f1033b.e(false);
                return;
            }
            return;
        }
        dismiss();
        i.a aVar = this.f1022w;
        if (aVar != null) {
            aVar.a(eVar, true);
        }
        ViewTreeObserver viewTreeObserver = this.f1023x;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.f1023x.removeGlobalOnLayoutListener(this.f1009j);
            }
            this.f1023x = null;
        }
        this.f1024y.onDismiss();
    }

    @Override // android.support.v7.view.menu.i
    public boolean b(l lVar) {
        for (c cVar : this.f1008i) {
            if (lVar == cVar.f1033b) {
                cVar.a().requestFocus();
                return true;
            }
        }
        if (!lVar.hasVisibleItems()) {
            return false;
        }
        l(lVar);
        i.a aVar = this.f1022w;
        if (aVar != null) {
            aVar.b(lVar);
        }
        return true;
    }

    @Override // w.z
    public void c() {
        if (h()) {
            return;
        }
        Iterator it = this.f1007h.iterator();
        while (it.hasNext()) {
            F((e) it.next());
        }
        this.f1007h.clear();
        View view = this.f1013n;
        this.f1014o = view;
        if (view != null) {
            boolean z2 = this.f1023x == null;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.f1023x = viewTreeObserver;
            if (z2) {
                viewTreeObserver.addOnGlobalLayoutListener(this.f1009j);
            }
        }
    }

    @Override // android.support.v7.view.menu.i
    public boolean d() {
        return false;
    }

    @Override // w.z
    public void dismiss() {
        int size = this.f1008i.size();
        if (size > 0) {
            c[] cVarArr = (c[]) this.f1008i.toArray(new c[size]);
            for (int i2 = size - 1; i2 >= 0; i2--) {
                c cVar = cVarArr[i2];
                if (cVar.f1032a.h()) {
                    cVar.f1032a.dismiss();
                }
            }
        }
    }

    @Override // android.support.v7.view.menu.i
    public void f(i.a aVar) {
        this.f1022w = aVar;
    }

    @Override // w.z
    public boolean h() {
        return this.f1008i.size() > 0 && ((c) this.f1008i.get(0)).f1032a.h();
    }

    @Override // w.z
    public ListView i() {
        if (this.f1008i.isEmpty()) {
            return null;
        }
        return ((c) this.f1008i.get(r0.size() - 1)).a();
    }

    @Override // android.support.v7.view.menu.i
    public void k(boolean z2) {
        Iterator it = this.f1008i.iterator();
        while (it.hasNext()) {
            u.y(((c) it.next()).a().getAdapter()).notifyDataSetChanged();
        }
    }

    @Override // w.u
    public void l(e eVar) {
        eVar.c(this, this.f1001b);
        if (h()) {
            F(eVar);
        } else {
            this.f1007h.add(eVar);
        }
    }

    @Override // w.u
    public boolean m() {
        return false;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        c cVar;
        int size = this.f1008i.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                cVar = null;
                break;
            }
            cVar = (c) this.f1008i.get(i2);
            if (!cVar.f1032a.h()) {
                break;
            } else {
                i2++;
            }
        }
        if (cVar != null) {
            cVar.f1033b.e(false);
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i2, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i2 != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // w.u
    public void p(View view) {
        if (this.f1013n != view) {
            this.f1013n = view;
            this.f1012m = r.a(this.f1011l, AbstractC0285k0.k(view));
        }
    }

    @Override // w.u
    public void r(boolean z2) {
        this.f1020u = z2;
    }

    @Override // w.u
    public void s(int i2) {
        if (this.f1011l != i2) {
            this.f1011l = i2;
            this.f1012m = r.a(i2, AbstractC0285k0.k(this.f1013n));
        }
    }

    @Override // w.u
    public void t(int i2) {
        this.f1016q = true;
        this.f1018s = i2;
    }

    @Override // w.u
    public void u(PopupWindow.OnDismissListener onDismissListener) {
        this.f1024y = onDismissListener;
    }

    @Override // w.u
    public void v(boolean z2) {
        this.f1021v = z2;
    }

    @Override // w.u
    public void w(int i2) {
        this.f1017r = true;
        this.f1019t = i2;
    }

    public final T z() {
        T t2 = new T(this.f1001b, null, this.f1003d, this.f1004e);
        t2.K(this.f1010k);
        t2.C(this);
        t2.B(this);
        t2.s(this.f1013n);
        t2.w(this.f1012m);
        t2.A(true);
        return t2;
    }
}

package android.support.v7.view.menu;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.view.menu.i;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import p.AbstractC0463d;
import w.u;
import x.T;

/* JADX INFO: loaded from: classes.dex */
public final class k extends u implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, i, View.OnKeyListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f1121b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final e f1122c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f1123d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f1124e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f1125f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f1126g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f1127h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final T f1128i;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public PopupWindow.OnDismissListener f1130k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public View f1131l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public View f1132m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public i.a f1133n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public ViewTreeObserver f1134o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f1135p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f1136q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f1137r;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f1139t;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ViewTreeObserver.OnGlobalLayoutListener f1129j = new a();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f1138s = 0;

    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!k.this.h() || k.this.f1128i.p()) {
                return;
            }
            View view = k.this.f1132m;
            if (view == null || !view.isShown()) {
                k.this.dismiss();
            } else {
                k.this.f1128i.c();
            }
        }
    }

    public k(Context context, e eVar, View view, int i2, int i3, boolean z2) {
        this.f1121b = context;
        this.f1122c = eVar;
        this.f1124e = z2;
        this.f1123d = new d(eVar, LayoutInflater.from(context), z2);
        this.f1126g = i2;
        this.f1127h = i3;
        Resources resources = context.getResources();
        this.f1125f = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(AbstractC0463d.f7744b));
        this.f1131l = view;
        this.f1128i = new T(context, null, i2, i3);
        eVar.c(this, context);
    }

    @Override // android.support.v7.view.menu.i
    public void a(e eVar, boolean z2) {
        if (eVar != this.f1122c) {
            return;
        }
        dismiss();
        i.a aVar = this.f1133n;
        if (aVar != null) {
            aVar.a(eVar, z2);
        }
    }

    @Override // android.support.v7.view.menu.i
    public boolean b(l lVar) {
        if (lVar.hasVisibleItems()) {
            h hVar = new h(this.f1121b, lVar, this.f1132m, this.f1124e, this.f1126g, this.f1127h);
            hVar.j(this.f1133n);
            hVar.g(u.x(lVar));
            hVar.i(this.f1130k);
            this.f1130k = null;
            this.f1122c.e(false);
            if (hVar.n(this.f1128i.k(), this.f1128i.m())) {
                i.a aVar = this.f1133n;
                if (aVar == null) {
                    return true;
                }
                aVar.b(lVar);
                return true;
            }
        }
        return false;
    }

    @Override // w.z
    public void c() {
        if (!z()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // android.support.v7.view.menu.i
    public boolean d() {
        return false;
    }

    @Override // w.z
    public void dismiss() {
        if (h()) {
            this.f1128i.dismiss();
        }
    }

    @Override // android.support.v7.view.menu.i
    public void f(i.a aVar) {
        this.f1133n = aVar;
    }

    @Override // w.z
    public boolean h() {
        return !this.f1135p && this.f1128i.h();
    }

    @Override // w.z
    public ListView i() {
        return this.f1128i.i();
    }

    @Override // android.support.v7.view.menu.i
    public void k(boolean z2) {
        this.f1136q = false;
        d dVar = this.f1123d;
        if (dVar != null) {
            dVar.notifyDataSetChanged();
        }
    }

    @Override // w.u
    public void l(e eVar) {
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.f1135p = true;
        this.f1122c.close();
        ViewTreeObserver viewTreeObserver = this.f1134o;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.f1134o = this.f1132m.getViewTreeObserver();
            }
            this.f1134o.removeGlobalOnLayoutListener(this.f1129j);
            this.f1134o = null;
        }
        PopupWindow.OnDismissListener onDismissListener = this.f1130k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
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
        this.f1131l = view;
    }

    @Override // w.u
    public void r(boolean z2) {
        this.f1123d.d(z2);
    }

    @Override // w.u
    public void s(int i2) {
        this.f1138s = i2;
    }

    @Override // w.u
    public void t(int i2) {
        this.f1128i.y(i2);
    }

    @Override // w.u
    public void u(PopupWindow.OnDismissListener onDismissListener) {
        this.f1130k = onDismissListener;
    }

    @Override // w.u
    public void v(boolean z2) {
        this.f1139t = z2;
    }

    @Override // w.u
    public void w(int i2) {
        this.f1128i.G(i2);
    }

    public final boolean z() {
        View view;
        if (h()) {
            return true;
        }
        if (this.f1135p || (view = this.f1131l) == null) {
            return false;
        }
        this.f1132m = view;
        this.f1128i.B(this);
        this.f1128i.C(this);
        this.f1128i.A(true);
        View view2 = this.f1132m;
        boolean z2 = this.f1134o == null;
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.f1134o = viewTreeObserver;
        if (z2) {
            viewTreeObserver.addOnGlobalLayoutListener(this.f1129j);
        }
        this.f1128i.s(view2);
        this.f1128i.w(this.f1138s);
        if (!this.f1136q) {
            this.f1137r = u.o(this.f1123d, null, this.f1121b, this.f1125f);
            this.f1136q = true;
        }
        this.f1128i.v(this.f1137r);
        this.f1128i.z(2);
        this.f1128i.x(n());
        this.f1128i.c();
        ListView listViewI = this.f1128i.i();
        listViewI.setOnKeyListener(this);
        if (this.f1139t && this.f1122c.x() != null) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.f1121b).inflate(p.g.f7827k, (ViewGroup) listViewI, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            if (textView != null) {
                textView.setText(this.f1122c.x());
            }
            frameLayout.setEnabled(false);
            listViewI.addHeaderView(frameLayout, null, false);
        }
        this.f1128i.r(this.f1123d);
        this.f1128i.c();
        return true;
    }
}

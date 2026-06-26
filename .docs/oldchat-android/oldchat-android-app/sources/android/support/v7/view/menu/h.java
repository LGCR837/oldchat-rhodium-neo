package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.view.menu.i;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import k.AbstractC0285k0;
import k.r;
import p.AbstractC0463d;
import w.u;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1108a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e f1109b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f1110c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1111d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1112e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f1113f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1114g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1115h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public i.a f1116i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public u f1117j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public PopupWindow.OnDismissListener f1118k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final PopupWindow.OnDismissListener f1119l;

    public class a implements PopupWindow.OnDismissListener {
        public a() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            h.this.e();
        }
    }

    public h(Context context, e eVar, View view, boolean z2, int i2) {
        this(context, eVar, view, z2, i2, 0);
    }

    public final u a() {
        Display defaultDisplay = ((WindowManager) this.f1108a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 17) {
            defaultDisplay.getRealSize(point);
        } else if (i2 >= 13) {
            defaultDisplay.getSize(point);
        } else {
            point.set(defaultDisplay.getWidth(), defaultDisplay.getHeight());
        }
        u bVar = Math.min(point.x, point.y) >= this.f1108a.getResources().getDimensionPixelSize(AbstractC0463d.f7743a) ? new b(this.f1108a, this.f1113f, this.f1111d, this.f1112e, this.f1110c) : new k(this.f1108a, this.f1109b, this.f1113f, this.f1111d, this.f1112e, this.f1110c);
        bVar.l(this.f1109b);
        bVar.u(this.f1119l);
        bVar.p(this.f1113f);
        bVar.f(this.f1116i);
        bVar.r(this.f1115h);
        bVar.s(this.f1114g);
        return bVar;
    }

    public void b() {
        if (d()) {
            this.f1117j.dismiss();
        }
    }

    public u c() {
        if (this.f1117j == null) {
            this.f1117j = a();
        }
        return this.f1117j;
    }

    public boolean d() {
        u uVar = this.f1117j;
        return uVar != null && uVar.h();
    }

    public void e() {
        this.f1117j = null;
        PopupWindow.OnDismissListener onDismissListener = this.f1118k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public void f(View view) {
        this.f1113f = view;
    }

    public void g(boolean z2) {
        this.f1115h = z2;
        u uVar = this.f1117j;
        if (uVar != null) {
            uVar.r(z2);
        }
    }

    public void h(int i2) {
        this.f1114g = i2;
    }

    public void i(PopupWindow.OnDismissListener onDismissListener) {
        this.f1118k = onDismissListener;
    }

    public void j(i.a aVar) {
        this.f1116i = aVar;
        u uVar = this.f1117j;
        if (uVar != null) {
            uVar.f(aVar);
        }
    }

    public void k() {
        if (!m()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public final void l(int i2, int i3, boolean z2, boolean z3) {
        u uVarC = c();
        uVarC.v(z3);
        if (z2) {
            if ((r.a(this.f1114g, AbstractC0285k0.k(this.f1113f)) & 7) == 5) {
                i2 -= this.f1113f.getWidth();
            }
            uVarC.t(i2);
            uVarC.w(i3);
            int i4 = (int) ((this.f1108a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            uVarC.q(new Rect(i2 - i4, i3 - i4, i2 + i4, i3 + i4));
        }
        uVarC.c();
    }

    public boolean m() {
        if (d()) {
            return true;
        }
        if (this.f1113f == null) {
            return false;
        }
        l(0, 0, false, false);
        return true;
    }

    public boolean n(int i2, int i3) {
        if (d()) {
            return true;
        }
        if (this.f1113f == null) {
            return false;
        }
        l(i2, i3, true, true);
        return true;
    }

    public h(Context context, e eVar, View view, boolean z2, int i2, int i3) {
        this.f1114g = 8388611;
        this.f1119l = new a();
        this.f1108a = context;
        this.f1109b = eVar;
        this.f1113f = view;
        this.f1110c = z2;
        this.f1111d = i2;
        this.f1112e = i3;
    }
}

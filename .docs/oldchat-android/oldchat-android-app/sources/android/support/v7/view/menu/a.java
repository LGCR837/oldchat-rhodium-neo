package android.support.v7.view.menu;

import android.content.Context;
import android.support.v7.view.menu.i;
import android.support.v7.view.menu.j;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import k.AbstractC0285k0;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f991a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f992b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public e f993c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public LayoutInflater f994d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public LayoutInflater f995e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public i.a f996f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f997g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f998h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public j f999i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f1000j;

    public a(Context context, int i2, int i3) {
        this.f991a = context;
        this.f994d = LayoutInflater.from(context);
        this.f997g = i2;
        this.f998h = i3;
    }

    @Override // android.support.v7.view.menu.i
    public void a(e eVar, boolean z2) {
        i.a aVar = this.f996f;
        if (aVar != null) {
            aVar.a(eVar, z2);
        }
    }

    @Override // android.support.v7.view.menu.i
    public boolean b(l lVar) {
        i.a aVar = this.f996f;
        if (aVar != null) {
            return aVar.b(lVar);
        }
        return false;
    }

    public void c(View view, int i2) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.f999i).addView(view, i2);
    }

    @Override // android.support.v7.view.menu.i
    public boolean e(e eVar, g gVar) {
        return false;
    }

    @Override // android.support.v7.view.menu.i
    public void f(i.a aVar) {
        this.f996f = aVar;
    }

    @Override // android.support.v7.view.menu.i
    public void g(Context context, e eVar) {
        this.f992b = context;
        this.f995e = LayoutInflater.from(context);
        this.f993c = eVar;
    }

    public abstract void h(g gVar, j.a aVar);

    public j.a i(ViewGroup viewGroup) {
        return (j.a) this.f994d.inflate(this.f998h, viewGroup, false);
    }

    @Override // android.support.v7.view.menu.i
    public boolean j(e eVar, g gVar) {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v7.view.menu.i
    public void k(boolean z2) {
        ViewGroup viewGroup = (ViewGroup) this.f999i;
        if (viewGroup == null) {
            return;
        }
        e eVar = this.f993c;
        int i2 = 0;
        if (eVar != null) {
            eVar.r();
            ArrayList arrayListE = this.f993c.E();
            int size = arrayListE.size();
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                g gVar = (g) arrayListE.get(i4);
                if (q(i3, gVar)) {
                    View childAt = viewGroup.getChildAt(i3);
                    g itemData = childAt instanceof j.a ? ((j.a) childAt).getItemData() : null;
                    View viewN = n(gVar, childAt, viewGroup);
                    if (gVar != itemData) {
                        viewN.setPressed(false);
                        AbstractC0285k0.B(viewN);
                    }
                    if (viewN != childAt) {
                        c(viewN, i3);
                    }
                    i3++;
                }
            }
            i2 = i3;
        }
        while (i2 < viewGroup.getChildCount()) {
            if (!l(viewGroup, i2)) {
                i2++;
            }
        }
    }

    public boolean l(ViewGroup viewGroup, int i2) {
        viewGroup.removeViewAt(i2);
        return true;
    }

    public i.a m() {
        return this.f996f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View n(g gVar, View view, ViewGroup viewGroup) {
        j.a aVarI = view instanceof j.a ? (j.a) view : i(viewGroup);
        h(gVar, aVarI);
        return (View) aVarI;
    }

    public j o(ViewGroup viewGroup) {
        if (this.f999i == null) {
            j jVar = (j) this.f994d.inflate(this.f997g, viewGroup, false);
            this.f999i = jVar;
            jVar.c(this.f993c);
            k(true);
        }
        return this.f999i;
    }

    public void p(int i2) {
        this.f1000j = i2;
    }

    public abstract boolean q(int i2, g gVar);
}

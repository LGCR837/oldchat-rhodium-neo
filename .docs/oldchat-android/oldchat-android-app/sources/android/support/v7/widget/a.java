package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.i;
import android.support.v7.view.menu.j;
import android.support.v7.widget.ActionMenuView;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import g.AbstractC0244a;
import java.util.ArrayList;
import k.AbstractC0299p;
import p.AbstractC0460a;
import u.AbstractC0471a;
import v.C0472a;
import w.z;
import x.L;

/* JADX INFO: loaded from: classes.dex */
public class a extends android.support.v7.view.menu.a implements AbstractC0299p.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public C0013a f1586A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public c f1587B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public b f1588C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final f f1589D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public int f1590E;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public d f1591k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Drawable f1592l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f1593m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f1594n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f1595o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f1596p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f1597q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f1598r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f1599s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f1600t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f1601u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f1602v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f1603w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final SparseBooleanArray f1604x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public View f1605y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public e f1606z;

    /* JADX INFO: renamed from: android.support.v7.widget.a$a, reason: collision with other inner class name */
    public class C0013a extends android.support.v7.view.menu.h {
        public C0013a(Context context, android.support.v7.view.menu.l lVar, View view) {
            super(context, lVar, view, false, AbstractC0460a.f7715i);
            if (!((android.support.v7.view.menu.g) lVar.getItem()).k()) {
                View view2 = a.this.f1591k;
                f(view2 == null ? (View) a.this.f999i : view2);
            }
            j(a.this.f1589D);
        }

        @Override // android.support.v7.view.menu.h
        public void e() {
            a aVar = a.this;
            aVar.f1586A = null;
            aVar.f1590E = 0;
            super.e();
        }
    }

    public class b extends ActionMenuItemView.b {
        public b() {
        }

        @Override // android.support.v7.view.menu.ActionMenuItemView.b
        public z a() {
            C0013a c0013a = a.this.f1586A;
            if (c0013a != null) {
                return c0013a.c();
            }
            return null;
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public e f1609a;

        public c(e eVar) {
            this.f1609a = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a.this.f993c != null) {
                a.this.f993c.d();
            }
            View view = (View) a.this.f999i;
            if (view != null && view.getWindowToken() != null && this.f1609a.m()) {
                a.this.f1606z = this.f1609a;
            }
            a.this.f1587B = null;
        }
    }

    public class d extends AppCompatImageView implements ActionMenuView.a {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final float[] f1611c;

        /* JADX INFO: renamed from: android.support.v7.widget.a$d$a, reason: collision with other inner class name */
        public class C0014a extends L {

            /* JADX INFO: renamed from: j, reason: collision with root package name */
            public final /* synthetic */ a f1613j;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0014a(View view, a aVar) {
                super(view);
                this.f1613j = aVar;
            }

            @Override // x.L
            public z b() {
                e eVar = a.this.f1606z;
                if (eVar == null) {
                    return null;
                }
                return eVar.c();
            }

            @Override // x.L
            public boolean c() {
                a.this.J();
                return true;
            }

            @Override // x.L
            public boolean d() {
                a aVar = a.this;
                if (aVar.f1587B != null) {
                    return false;
                }
                aVar.A();
                return true;
            }
        }

        public d(Context context) {
            super(context, null, AbstractC0460a.f7714h);
            this.f1611c = new float[2];
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            setOnTouchListener(new C0014a(this, a.this));
        }

        @Override // android.support.v7.widget.ActionMenuView.a
        public boolean a() {
            return false;
        }

        @Override // android.support.v7.widget.ActionMenuView.a
        public boolean c() {
            return false;
        }

        @Override // android.view.View
        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            a.this.J();
            return true;
        }

        @Override // android.widget.ImageView
        public boolean setFrame(int i2, int i3, int i4, int i5) {
            boolean frame = super.setFrame(i2, i3, i4, i5);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (drawable != null && background != null) {
                int width = getWidth();
                int height = getHeight();
                int iMax = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                AbstractC0244a.k(background, paddingLeft - iMax, paddingTop - iMax, paddingLeft + iMax, paddingTop + iMax);
            }
            return frame;
        }
    }

    public class e extends android.support.v7.view.menu.h {
        public e(Context context, android.support.v7.view.menu.e eVar, View view, boolean z2) {
            super(context, eVar, view, z2, AbstractC0460a.f7715i);
            h(8388613);
            j(a.this.f1589D);
        }

        @Override // android.support.v7.view.menu.h
        public void e() {
            if (a.this.f993c != null) {
                a.this.f993c.close();
            }
            a.this.f1606z = null;
            super.e();
        }
    }

    public class f implements i.a {
        public f() {
        }

        @Override // android.support.v7.view.menu.i.a
        public void a(android.support.v7.view.menu.e eVar, boolean z2) {
            if (eVar instanceof android.support.v7.view.menu.l) {
                eVar.D().e(false);
            }
            i.a aVarM = a.this.m();
            if (aVarM != null) {
                aVarM.a(eVar, z2);
            }
        }

        @Override // android.support.v7.view.menu.i.a
        public boolean b(android.support.v7.view.menu.e eVar) {
            if (eVar == null) {
                return false;
            }
            a.this.f1590E = ((android.support.v7.view.menu.l) eVar).getItem().getItemId();
            i.a aVarM = a.this.m();
            if (aVarM != null) {
                return aVarM.b(eVar);
            }
            return false;
        }
    }

    public a(Context context) {
        super(context, p.g.f7819c, p.g.f7818b);
        this.f1604x = new SparseBooleanArray();
        this.f1589D = new f();
    }

    public boolean A() {
        Object obj;
        c cVar = this.f1587B;
        if (cVar != null && (obj = this.f999i) != null) {
            ((View) obj).removeCallbacks(cVar);
            this.f1587B = null;
            return true;
        }
        e eVar = this.f1606z;
        if (eVar == null) {
            return false;
        }
        eVar.b();
        return true;
    }

    public boolean B() {
        C0013a c0013a = this.f1586A;
        if (c0013a == null) {
            return false;
        }
        c0013a.b();
        return true;
    }

    public boolean C() {
        return this.f1587B != null || D();
    }

    public boolean D() {
        e eVar = this.f1606z;
        return eVar != null && eVar.d();
    }

    public void E(Configuration configuration) {
        if (!this.f1599s) {
            this.f1598r = C0472a.b(this.f992b).d();
        }
        android.support.v7.view.menu.e eVar = this.f993c;
        if (eVar != null) {
            eVar.J(true);
        }
    }

    public void F(boolean z2) {
        this.f1602v = z2;
    }

    public void G(ActionMenuView actionMenuView) {
        this.f999i = actionMenuView;
        actionMenuView.c(this.f993c);
    }

    public void H(Drawable drawable) {
        d dVar = this.f1591k;
        if (dVar != null) {
            dVar.setImageDrawable(drawable);
        } else {
            this.f1593m = true;
            this.f1592l = drawable;
        }
    }

    public void I(boolean z2) {
        this.f1594n = z2;
        this.f1595o = true;
    }

    public boolean J() {
        android.support.v7.view.menu.e eVar;
        if (!this.f1594n || D() || (eVar = this.f993c) == null || this.f999i == null || this.f1587B != null || eVar.z().isEmpty()) {
            return false;
        }
        c cVar = new c(new e(this.f992b, this.f993c, this.f1591k, true));
        this.f1587B = cVar;
        ((View) this.f999i).post(cVar);
        super.b(null);
        return true;
    }

    @Override // android.support.v7.view.menu.a, android.support.v7.view.menu.i
    public void a(android.support.v7.view.menu.e eVar, boolean z2) {
        x();
        super.a(eVar, z2);
    }

    @Override // android.support.v7.view.menu.a, android.support.v7.view.menu.i
    public boolean b(android.support.v7.view.menu.l lVar) {
        boolean z2 = false;
        if (!lVar.hasVisibleItems()) {
            return false;
        }
        android.support.v7.view.menu.l lVar2 = lVar;
        while (lVar2.d0() != this.f993c) {
            lVar2 = (android.support.v7.view.menu.l) lVar2.d0();
        }
        View viewY = y(lVar2.getItem());
        if (viewY == null) {
            return false;
        }
        this.f1590E = lVar.getItem().getItemId();
        int size = lVar.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            MenuItem item = lVar.getItem(i2);
            if (item.isVisible() && item.getIcon() != null) {
                z2 = true;
                break;
            }
            i2++;
        }
        C0013a c0013a = new C0013a(this.f992b, lVar, viewY);
        this.f1586A = c0013a;
        c0013a.g(z2);
        this.f1586A.k();
        super.b(lVar);
        return true;
    }

    @Override // android.support.v7.view.menu.i
    public boolean d() {
        ArrayList arrayListE;
        int size;
        int i2;
        int iJ;
        int i3;
        a aVar = this;
        android.support.v7.view.menu.e eVar = aVar.f993c;
        int i4 = 0;
        if (eVar != null) {
            arrayListE = eVar.E();
            size = arrayListE.size();
        } else {
            arrayListE = null;
            size = 0;
        }
        int i5 = aVar.f1598r;
        int i6 = aVar.f1597q;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) aVar.f999i;
        boolean z2 = false;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < size; i9++) {
            android.support.v7.view.menu.g gVar = (android.support.v7.view.menu.g) arrayListE.get(i9);
            if (gVar.n()) {
                i7++;
            } else if (gVar.m()) {
                i8++;
            } else {
                z2 = true;
            }
            if (aVar.f1602v && gVar.isActionViewExpanded()) {
                i5 = 0;
            }
        }
        if (aVar.f1594n && (z2 || i8 + i7 > i5)) {
            i5--;
        }
        int i10 = i5 - i7;
        SparseBooleanArray sparseBooleanArray = aVar.f1604x;
        sparseBooleanArray.clear();
        if (aVar.f1600t) {
            int i11 = aVar.f1603w;
            iJ = i6 / i11;
            i2 = i11 + ((i6 % i11) / iJ);
        } else {
            i2 = 0;
            iJ = 0;
        }
        int i12 = 0;
        int i13 = 0;
        while (i12 < size) {
            android.support.v7.view.menu.g gVar2 = (android.support.v7.view.menu.g) arrayListE.get(i12);
            if (gVar2.n()) {
                View viewN = aVar.n(gVar2, aVar.f1605y, viewGroup);
                if (aVar.f1605y == null) {
                    aVar.f1605y = viewN;
                }
                if (aVar.f1600t) {
                    iJ -= ActionMenuView.J(viewN, i2, iJ, iMakeMeasureSpec, i4);
                } else {
                    viewN.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                }
                int measuredWidth = viewN.getMeasuredWidth();
                i6 -= measuredWidth;
                if (i13 == 0) {
                    i13 = measuredWidth;
                }
                int groupId = gVar2.getGroupId();
                if (groupId != 0) {
                    sparseBooleanArray.put(groupId, true);
                }
                gVar2.t(true);
                i3 = size;
            } else if (gVar2.m()) {
                int groupId2 = gVar2.getGroupId();
                boolean z3 = sparseBooleanArray.get(groupId2);
                boolean z4 = (i10 > 0 || z3) && i6 > 0 && (!aVar.f1600t || iJ > 0);
                boolean z5 = z4;
                if (z4) {
                    View viewN2 = aVar.n(gVar2, aVar.f1605y, viewGroup);
                    i3 = size;
                    if (aVar.f1605y == null) {
                        aVar.f1605y = viewN2;
                    }
                    if (aVar.f1600t) {
                        int iJ2 = ActionMenuView.J(viewN2, i2, iJ, iMakeMeasureSpec, 0);
                        iJ -= iJ2;
                        if (iJ2 == 0) {
                            z5 = false;
                        }
                    } else {
                        viewN2.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                    }
                    int measuredWidth2 = viewN2.getMeasuredWidth();
                    i6 -= measuredWidth2;
                    if (i13 == 0) {
                        i13 = measuredWidth2;
                    }
                    z4 = z5 & (!aVar.f1600t ? i6 + i13 <= 0 : i6 < 0);
                } else {
                    i3 = size;
                }
                if (z4 && groupId2 != 0) {
                    sparseBooleanArray.put(groupId2, true);
                } else if (z3) {
                    sparseBooleanArray.put(groupId2, false);
                    for (int i14 = 0; i14 < i12; i14++) {
                        android.support.v7.view.menu.g gVar3 = (android.support.v7.view.menu.g) arrayListE.get(i14);
                        if (gVar3.getGroupId() == groupId2) {
                            if (gVar3.k()) {
                                i10++;
                            }
                            gVar3.t(false);
                        }
                    }
                }
                if (z4) {
                    i10--;
                }
                gVar2.t(z4);
            } else {
                i3 = size;
                gVar2.t(false);
                i12++;
                aVar = this;
                size = i3;
                i4 = 0;
            }
            i12++;
            aVar = this;
            size = i3;
            i4 = 0;
        }
        return true;
    }

    @Override // android.support.v7.view.menu.a, android.support.v7.view.menu.i
    public void g(Context context, android.support.v7.view.menu.e eVar) {
        super.g(context, eVar);
        Resources resources = context.getResources();
        C0472a c0472aB = C0472a.b(context);
        if (!this.f1595o) {
            this.f1594n = c0472aB.f();
        }
        if (!this.f1601u) {
            this.f1596p = c0472aB.c();
        }
        if (!this.f1599s) {
            this.f1598r = c0472aB.d();
        }
        int measuredWidth = this.f1596p;
        if (this.f1594n) {
            if (this.f1591k == null) {
                d dVar = new d(this.f991a);
                this.f1591k = dVar;
                if (this.f1593m) {
                    dVar.setImageDrawable(this.f1592l);
                    this.f1592l = null;
                    this.f1593m = false;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.f1591k.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            }
            measuredWidth -= this.f1591k.getMeasuredWidth();
        } else {
            this.f1591k = null;
        }
        this.f1597q = measuredWidth;
        this.f1603w = (int) (resources.getDisplayMetrics().density * 56.0f);
        this.f1605y = null;
    }

    @Override // android.support.v7.view.menu.a
    public void h(android.support.v7.view.menu.g gVar, j.a aVar) {
        aVar.b(gVar, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.f999i);
        if (this.f1588C == null) {
            this.f1588C = new b();
        }
        actionMenuItemView.setPopupCallback(this.f1588C);
    }

    @Override // android.support.v7.view.menu.a, android.support.v7.view.menu.i
    public void k(boolean z2) {
        ViewGroup viewGroup = (ViewGroup) ((View) this.f999i).getParent();
        if (viewGroup != null) {
            AbstractC0471a.a(viewGroup);
        }
        super.k(z2);
        ((View) this.f999i).requestLayout();
        android.support.v7.view.menu.e eVar = this.f993c;
        boolean z3 = false;
        if (eVar != null) {
            ArrayList arrayListS = eVar.s();
            int size = arrayListS.size();
            for (int i2 = 0; i2 < size; i2++) {
                AbstractC0299p abstractC0299pE = ((android.support.v7.view.menu.g) arrayListS.get(i2)).e();
                if (abstractC0299pE != null) {
                    abstractC0299pE.i(this);
                }
            }
        }
        android.support.v7.view.menu.e eVar2 = this.f993c;
        ArrayList arrayListZ = eVar2 != null ? eVar2.z() : null;
        if (this.f1594n && arrayListZ != null) {
            int size2 = arrayListZ.size();
            if (size2 == 1) {
                z3 = !((android.support.v7.view.menu.g) arrayListZ.get(0)).isActionViewExpanded();
            } else if (size2 > 0) {
                z3 = true;
            }
        }
        if (z3) {
            if (this.f1591k == null) {
                this.f1591k = new d(this.f991a);
            }
            ViewGroup viewGroup2 = (ViewGroup) this.f1591k.getParent();
            if (viewGroup2 != this.f999i) {
                if (viewGroup2 != null) {
                    viewGroup2.removeView(this.f1591k);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.f999i;
                actionMenuView.addView(this.f1591k, actionMenuView.D());
            }
        } else {
            d dVar = this.f1591k;
            if (dVar != null) {
                Object parent = dVar.getParent();
                Object obj = this.f999i;
                if (parent == obj) {
                    ((ViewGroup) obj).removeView(this.f1591k);
                }
            }
        }
        ((ActionMenuView) this.f999i).setOverflowReserved(this.f1594n);
    }

    @Override // android.support.v7.view.menu.a
    public boolean l(ViewGroup viewGroup, int i2) {
        if (viewGroup.getChildAt(i2) == this.f1591k) {
            return false;
        }
        return super.l(viewGroup, i2);
    }

    @Override // android.support.v7.view.menu.a
    public View n(android.support.v7.view.menu.g gVar, View view, ViewGroup viewGroup) {
        View actionView = gVar.getActionView();
        if (actionView == null || gVar.i()) {
            actionView = super.n(gVar, view, viewGroup);
        }
        actionView.setVisibility(gVar.isActionViewExpanded() ? 8 : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    @Override // android.support.v7.view.menu.a
    public android.support.v7.view.menu.j o(ViewGroup viewGroup) {
        android.support.v7.view.menu.j jVar = this.f999i;
        android.support.v7.view.menu.j jVarO = super.o(viewGroup);
        if (jVar != jVarO) {
            ((ActionMenuView) jVarO).setPresenter(this);
        }
        return jVarO;
    }

    @Override // android.support.v7.view.menu.a
    public boolean q(int i2, android.support.v7.view.menu.g gVar) {
        return gVar.k();
    }

    public boolean x() {
        return A() | B();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final View y(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.f999i;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if ((childAt instanceof j.a) && ((j.a) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    public Drawable z() {
        d dVar = this.f1591k;
        if (dVar != null) {
            return dVar.getDrawable();
        }
        if (this.f1593m) {
            return this.f1592l;
        }
        return null;
    }
}

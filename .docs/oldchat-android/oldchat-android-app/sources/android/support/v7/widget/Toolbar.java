package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.view.menu.e;
import android.support.v7.view.menu.i;
import android.support.v7.widget.ActionMenuView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import i.AbstractC0251b;
import java.util.ArrayList;
import java.util.List;
import k.AbstractC0285k0;
import k.I;
import k.M;
import k.W;
import k.r;
import o.AbstractC0430a;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import p.AbstractC0460a;
import v.InterfaceC0474c;
import x.C0510m;
import x.E;
import x.G;
import x.Z;
import x.f0;
import x.h0;

/* JADX INFO: loaded from: classes.dex */
public class Toolbar extends ViewGroup {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f1533A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public boolean f1534B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public boolean f1535C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final ArrayList f1536D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final ArrayList f1537E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final int[] f1538F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final ActionMenuView.e f1539G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public n f1540H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public android.support.v7.widget.a f1541I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public d f1542J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public i.a f1543K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public e.a f1544L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public boolean f1545M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public final Runnable f1546N;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ActionMenuView f1547a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f1548b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextView f1549c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ImageButton f1550d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ImageView f1551e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f1552f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public CharSequence f1553g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ImageButton f1554h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public View f1555i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Context f1556j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1557k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1558l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1559m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f1560n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f1561o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f1562p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f1563q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f1564r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f1565s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Z f1566t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f1567u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f1568v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f1569w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public CharSequence f1570x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public CharSequence f1571y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f1572z;

    public class a implements ActionMenuView.e {
        public a() {
        }

        @Override // android.support.v7.widget.ActionMenuView.e
        public boolean onMenuItemClick(MenuItem menuItem) {
            Toolbar.this.getClass();
            return false;
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Toolbar.this.M();
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Toolbar.this.e();
        }
    }

    public class d implements android.support.v7.view.menu.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public android.support.v7.view.menu.e f1576a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public android.support.v7.view.menu.g f1577b;

        public d() {
        }

        @Override // android.support.v7.view.menu.i
        public void a(android.support.v7.view.menu.e eVar, boolean z2) {
        }

        @Override // android.support.v7.view.menu.i
        public boolean b(android.support.v7.view.menu.l lVar) {
            return false;
        }

        @Override // android.support.v7.view.menu.i
        public boolean d() {
            return false;
        }

        @Override // android.support.v7.view.menu.i
        public boolean e(android.support.v7.view.menu.e eVar, android.support.v7.view.menu.g gVar) {
            KeyEvent.Callback callback = Toolbar.this.f1555i;
            if (callback instanceof InterfaceC0474c) {
                ((InterfaceC0474c) callback).d();
            }
            Toolbar toolbar = Toolbar.this;
            toolbar.removeView(toolbar.f1555i);
            Toolbar toolbar2 = Toolbar.this;
            toolbar2.removeView(toolbar2.f1554h);
            Toolbar toolbar3 = Toolbar.this;
            toolbar3.f1555i = null;
            toolbar3.a();
            this.f1577b = null;
            Toolbar.this.requestLayout();
            gVar.q(false);
            return true;
        }

        @Override // android.support.v7.view.menu.i
        public void g(Context context, android.support.v7.view.menu.e eVar) {
            android.support.v7.view.menu.g gVar;
            android.support.v7.view.menu.e eVar2 = this.f1576a;
            if (eVar2 != null && (gVar = this.f1577b) != null) {
                eVar2.f(gVar);
            }
            this.f1576a = eVar;
        }

        @Override // android.support.v7.view.menu.i
        public boolean j(android.support.v7.view.menu.e eVar, android.support.v7.view.menu.g gVar) {
            Toolbar.this.g();
            ViewParent parent = Toolbar.this.f1554h.getParent();
            Toolbar toolbar = Toolbar.this;
            if (parent != toolbar) {
                toolbar.addView(toolbar.f1554h);
            }
            Toolbar.this.f1555i = gVar.getActionView();
            this.f1577b = gVar;
            ViewParent parent2 = Toolbar.this.f1555i.getParent();
            Toolbar toolbar2 = Toolbar.this;
            if (parent2 != toolbar2) {
                e eVarGenerateDefaultLayoutParams = toolbar2.generateDefaultLayoutParams();
                Toolbar toolbar3 = Toolbar.this;
                eVarGenerateDefaultLayoutParams.f6720a = (toolbar3.f1560n & 112) | 8388611;
                eVarGenerateDefaultLayoutParams.f1579b = 2;
                toolbar3.f1555i.setLayoutParams(eVarGenerateDefaultLayoutParams);
                Toolbar toolbar4 = Toolbar.this;
                toolbar4.addView(toolbar4.f1555i);
            }
            Toolbar.this.F();
            Toolbar.this.requestLayout();
            gVar.q(true);
            KeyEvent.Callback callback = Toolbar.this.f1555i;
            if (callback instanceof InterfaceC0474c) {
                ((InterfaceC0474c) callback).b();
            }
            return true;
        }

        @Override // android.support.v7.view.menu.i
        public void k(boolean z2) {
            if (this.f1577b != null) {
                android.support.v7.view.menu.e eVar = this.f1576a;
                if (eVar != null) {
                    int size = eVar.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        if (this.f1576a.getItem(i2) == this.f1577b) {
                            return;
                        }
                    }
                }
                e(this.f1576a, this.f1577b);
            }
        }
    }

    public interface f {
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, AbstractC0460a.f7706J);
    }

    private MenuInflater getMenuInflater() {
        return new v.k(getContext());
    }

    public final int A(View view, int i2, int[] iArr, int i3) {
        e eVar = (e) view.getLayoutParams();
        int i4 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin - iArr[0];
        int iMax = i2 + Math.max(0, i4);
        iArr[0] = Math.max(0, -i4);
        int iQ = q(view, i3);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax, iQ, iMax + measuredWidth, view.getMeasuredHeight() + iQ);
        return iMax + measuredWidth + ((ViewGroup.MarginLayoutParams) eVar).rightMargin;
    }

    public final int B(View view, int i2, int[] iArr, int i3) {
        e eVar = (e) view.getLayoutParams();
        int i4 = ((ViewGroup.MarginLayoutParams) eVar).rightMargin - iArr[1];
        int iMax = i2 - Math.max(0, i4);
        iArr[1] = Math.max(0, -i4);
        int iQ = q(view, i3);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax - measuredWidth, iQ, iMax, view.getMeasuredHeight() + iQ);
        return iMax - (measuredWidth + ((ViewGroup.MarginLayoutParams) eVar).leftMargin);
    }

    public final int C(View view, int i2, int i3, int i4, int i5, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i6 = marginLayoutParams.leftMargin - iArr[0];
        int i7 = marginLayoutParams.rightMargin - iArr[1];
        int iMax = Math.max(0, i6) + Math.max(0, i7);
        iArr[0] = Math.max(0, -i6);
        iArr[1] = Math.max(0, -i7);
        view.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + iMax + i3, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i4, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i5, marginLayoutParams.height));
        return view.getMeasuredWidth() + iMax;
    }

    public final void D(View view, int i2, int i3, int i4, int i5, int i6) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i3, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i4, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i5, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i6 >= 0) {
            if (mode != 0) {
                i6 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i6);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i6, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    public final void E() {
        removeCallbacks(this.f1546N);
        post(this.f1546N);
    }

    public void F() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (((e) childAt.getLayoutParams()).f1579b != 2 && childAt != this.f1547a) {
                removeViewAt(childCount);
                this.f1537E.add(childAt);
            }
        }
    }

    public void G(int i2, int i3) {
        h();
        this.f1566t.g(i2, i3);
    }

    public void H(android.support.v7.view.menu.e eVar, android.support.v7.widget.a aVar) {
        if (eVar == null && this.f1547a == null) {
            return;
        }
        k();
        android.support.v7.view.menu.e eVarL = this.f1547a.L();
        if (eVarL == eVar) {
            return;
        }
        if (eVarL != null) {
            eVarL.N(this.f1541I);
            eVarL.N(this.f1542J);
        }
        if (this.f1542J == null) {
            this.f1542J = new d();
        }
        aVar.F(true);
        if (eVar != null) {
            eVar.c(aVar, this.f1556j);
            eVar.c(this.f1542J, this.f1556j);
        } else {
            aVar.g(this.f1556j, null);
            this.f1542J.g(this.f1556j, null);
            aVar.k(true);
            this.f1542J.k(true);
        }
        this.f1547a.setPopupTheme(this.f1557k);
        this.f1547a.setPresenter(aVar);
        this.f1541I = aVar;
    }

    public void I(Context context, int i2) {
        this.f1559m = i2;
        TextView textView = this.f1549c;
        if (textView != null) {
            textView.setTextAppearance(context, i2);
        }
    }

    public void J(Context context, int i2) {
        this.f1558l = i2;
        TextView textView = this.f1548b;
        if (textView != null) {
            textView.setTextAppearance(context, i2);
        }
    }

    public final boolean K() {
        if (!this.f1545M) {
            return false;
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (L(childAt) && childAt.getMeasuredWidth() > 0 && childAt.getMeasuredHeight() > 0) {
                return false;
            }
        }
        return true;
    }

    public final boolean L(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    public boolean M() {
        ActionMenuView actionMenuView = this.f1547a;
        return actionMenuView != null && actionMenuView.N();
    }

    public void a() {
        for (int size = this.f1537E.size() - 1; size >= 0; size--) {
            addView((View) this.f1537E.get(size));
        }
        this.f1537E.clear();
    }

    public final void b(List list, int i2) {
        boolean z2 = AbstractC0285k0.k(this) == 1;
        int childCount = getChildCount();
        int iA = r.a(i2, AbstractC0285k0.k(this));
        list.clear();
        if (!z2) {
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.f1579b == 0 && L(childAt) && p(eVar.f6720a) == iA) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i4 = childCount - 1; i4 >= 0; i4--) {
            View childAt2 = getChildAt(i4);
            e eVar2 = (e) childAt2.getLayoutParams();
            if (eVar2.f1579b == 0 && L(childAt2) && p(eVar2.f6720a) == iA) {
                list.add(childAt2);
            }
        }
    }

    public final void c(View view, boolean z2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        e eVarGenerateDefaultLayoutParams = layoutParams == null ? generateDefaultLayoutParams() : !checkLayoutParams(layoutParams) ? generateLayoutParams(layoutParams) : (e) layoutParams;
        eVarGenerateDefaultLayoutParams.f1579b = 1;
        if (!z2 || this.f1555i == null) {
            addView(view, eVarGenerateDefaultLayoutParams);
        } else {
            view.setLayoutParams(eVarGenerateDefaultLayoutParams);
            this.f1537E.add(view);
        }
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof e);
    }

    public boolean d() {
        ActionMenuView actionMenuView;
        return getVisibility() == 0 && (actionMenuView = this.f1547a) != null && actionMenuView.I();
    }

    public void e() {
        d dVar = this.f1542J;
        android.support.v7.view.menu.g gVar = dVar == null ? null : dVar.f1577b;
        if (gVar != null) {
            gVar.collapseActionView();
        }
    }

    public void f() {
        ActionMenuView actionMenuView = this.f1547a;
        if (actionMenuView != null) {
            actionMenuView.z();
        }
    }

    public void g() {
        if (this.f1554h == null) {
            C0510m c0510m = new C0510m(getContext(), null, AbstractC0460a.f7705I);
            this.f1554h = c0510m;
            c0510m.setImageDrawable(this.f1552f);
            this.f1554h.setContentDescription(this.f1553g);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f6720a = (this.f1560n & 112) | 8388611;
            eVarGenerateDefaultLayoutParams.f1579b = 2;
            this.f1554h.setLayoutParams(eVarGenerateDefaultLayoutParams);
            this.f1554h.setOnClickListener(new c());
        }
    }

    public int getContentInsetEnd() {
        Z z2 = this.f1566t;
        if (z2 != null) {
            return z2.a();
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i2 = this.f1568v;
        return i2 != Integer.MIN_VALUE ? i2 : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        Z z2 = this.f1566t;
        if (z2 != null) {
            return z2.b();
        }
        return 0;
    }

    public int getContentInsetRight() {
        Z z2 = this.f1566t;
        if (z2 != null) {
            return z2.c();
        }
        return 0;
    }

    public int getContentInsetStart() {
        Z z2 = this.f1566t;
        if (z2 != null) {
            return z2.d();
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i2 = this.f1567u;
        return i2 != Integer.MIN_VALUE ? i2 : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        android.support.v7.view.menu.e eVarL;
        ActionMenuView actionMenuView = this.f1547a;
        return (actionMenuView == null || (eVarL = actionMenuView.L()) == null || !eVarL.hasVisibleItems()) ? getContentInsetEnd() : Math.max(getContentInsetEnd(), Math.max(this.f1568v, 0));
    }

    public int getCurrentContentInsetLeft() {
        return AbstractC0285k0.k(this) == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        return AbstractC0285k0.k(this) == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.f1567u, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        ImageView imageView = this.f1551e;
        if (imageView != null) {
            return imageView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        ImageView imageView = this.f1551e;
        if (imageView != null) {
            return imageView.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        j();
        return this.f1547a.getMenu();
    }

    public CharSequence getNavigationContentDescription() {
        ImageButton imageButton = this.f1550d;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        ImageButton imageButton = this.f1550d;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public Drawable getOverflowIcon() {
        j();
        return this.f1547a.getOverflowIcon();
    }

    public int getPopupTheme() {
        return this.f1557k;
    }

    public CharSequence getSubtitle() {
        return this.f1571y;
    }

    public CharSequence getTitle() {
        return this.f1570x;
    }

    public int getTitleMarginBottom() {
        return this.f1565s;
    }

    public int getTitleMarginEnd() {
        return this.f1563q;
    }

    public int getTitleMarginStart() {
        return this.f1562p;
    }

    public int getTitleMarginTop() {
        return this.f1564r;
    }

    public G getWrapper() {
        if (this.f1540H == null) {
            this.f1540H = new n(this, true);
        }
        return this.f1540H;
    }

    public final void h() {
        if (this.f1566t == null) {
            this.f1566t = new Z();
        }
    }

    public final void i() {
        if (this.f1551e == null) {
            this.f1551e = new AppCompatImageView(getContext());
        }
    }

    public final void j() {
        k();
        if (this.f1547a.L() == null) {
            android.support.v7.view.menu.e eVar = (android.support.v7.view.menu.e) this.f1547a.getMenu();
            if (this.f1542J == null) {
                this.f1542J = new d();
            }
            this.f1547a.setExpandedActionViewsExclusive(true);
            eVar.c(this.f1542J, this.f1556j);
        }
    }

    public final void k() {
        if (this.f1547a == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext());
            this.f1547a = actionMenuView;
            actionMenuView.setPopupTheme(this.f1557k);
            this.f1547a.setOnMenuItemClickListener(this.f1539G);
            this.f1547a.M(this.f1543K, this.f1544L);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f6720a = (this.f1560n & 112) | 8388613;
            this.f1547a.setLayoutParams(eVarGenerateDefaultLayoutParams);
            c(this.f1547a, false);
        }
    }

    public final void l() {
        if (this.f1550d == null) {
            this.f1550d = new C0510m(getContext(), null, AbstractC0460a.f7705I);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f6720a = (this.f1560n & 112) | 8388611;
            this.f1550d.setLayoutParams(eVarGenerateDefaultLayoutParams);
        }
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public e generateDefaultLayoutParams() {
        return new e(-2, -2);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public e generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public e generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e ? new e((e) layoutParams) : layoutParams instanceof AbstractC0430a.C0066a ? new e((AbstractC0430a.C0066a) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new e((ViewGroup.MarginLayoutParams) layoutParams) : new e(layoutParams);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.f1546N);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int iB = W.b(motionEvent);
        if (iB == 9) {
            this.f1535C = false;
        }
        if (!this.f1535C) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (iB == 9 && !zOnHoverEvent) {
                this.f1535C = true;
            }
        }
        if (iB == 10 || iB == 3) {
            this.f1535C = false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x02a1 A[LOOP:0: B:106:0x029f->B:107:0x02a1, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x02c3 A[LOOP:1: B:109:0x02c1->B:110:0x02c3, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:114:0x02ed  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x02fc A[LOOP:2: B:118:0x02fa->B:119:0x02fc, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0227  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onLayout(boolean r20, int r21, int r22, int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 785
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        int measuredWidth;
        int iMax;
        int iA;
        int measuredWidth2;
        int iA2;
        int iMax2;
        int measuredHeight;
        int[] iArr = this.f1538F;
        boolean zC = h0.c(this);
        int i4 = !zC ? 1 : 0;
        if (L(this.f1550d)) {
            D(this.f1550d, i2, 0, i3, 0, this.f1561o);
            measuredWidth = this.f1550d.getMeasuredWidth() + s(this.f1550d);
            iMax = Math.max(0, this.f1550d.getMeasuredHeight() + t(this.f1550d));
            iA = h0.a(0, AbstractC0285k0.m(this.f1550d));
        } else {
            measuredWidth = 0;
            iMax = 0;
            iA = 0;
        }
        if (L(this.f1554h)) {
            D(this.f1554h, i2, 0, i3, 0, this.f1561o);
            measuredWidth = this.f1554h.getMeasuredWidth() + s(this.f1554h);
            iMax = Math.max(iMax, this.f1554h.getMeasuredHeight() + t(this.f1554h));
            iA = h0.a(iA, AbstractC0285k0.m(this.f1554h));
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int iMax3 = Math.max(currentContentInsetStart, measuredWidth);
        iArr[zC ? 1 : 0] = Math.max(0, currentContentInsetStart - measuredWidth);
        if (L(this.f1547a)) {
            D(this.f1547a, i2, iMax3, i3, 0, this.f1561o);
            measuredWidth2 = this.f1547a.getMeasuredWidth() + s(this.f1547a);
            iMax = Math.max(iMax, this.f1547a.getMeasuredHeight() + t(this.f1547a));
            iA = h0.a(iA, AbstractC0285k0.m(this.f1547a));
        } else {
            measuredWidth2 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int iMax4 = iMax3 + Math.max(currentContentInsetEnd, measuredWidth2);
        iArr[i4] = Math.max(0, currentContentInsetEnd - measuredWidth2);
        if (L(this.f1555i)) {
            iMax4 += C(this.f1555i, i2, iMax4, i3, 0, iArr);
            iMax = Math.max(iMax, this.f1555i.getMeasuredHeight() + t(this.f1555i));
            iA = h0.a(iA, AbstractC0285k0.m(this.f1555i));
        }
        if (L(this.f1551e)) {
            iMax4 += C(this.f1551e, i2, iMax4, i3, 0, iArr);
            iMax = Math.max(iMax, this.f1551e.getMeasuredHeight() + t(this.f1551e));
            iA = h0.a(iA, AbstractC0285k0.m(this.f1551e));
        }
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (((e) childAt.getLayoutParams()).f1579b == 0 && L(childAt)) {
                iMax4 += C(childAt, i2, iMax4, i3, 0, iArr);
                iMax = Math.max(iMax, childAt.getMeasuredHeight() + t(childAt));
                iA = h0.a(iA, AbstractC0285k0.m(childAt));
            }
        }
        int i6 = this.f1564r + this.f1565s;
        int i7 = this.f1562p + this.f1563q;
        if (L(this.f1548b)) {
            C(this.f1548b, i2, iMax4 + i7, i3, i6, iArr);
            int measuredWidth3 = this.f1548b.getMeasuredWidth() + s(this.f1548b);
            measuredHeight = this.f1548b.getMeasuredHeight() + t(this.f1548b);
            iA2 = h0.a(iA, AbstractC0285k0.m(this.f1548b));
            iMax2 = measuredWidth3;
        } else {
            iA2 = iA;
            iMax2 = 0;
            measuredHeight = 0;
        }
        if (L(this.f1549c)) {
            iMax2 = Math.max(iMax2, C(this.f1549c, i2, iMax4 + i7, i3, measuredHeight + i6, iArr));
            measuredHeight += this.f1549c.getMeasuredHeight() + t(this.f1549c);
            iA2 = h0.a(iA2, AbstractC0285k0.m(this.f1549c));
        }
        int iMax5 = Math.max(iMax, measuredHeight);
        setMeasuredDimension(AbstractC0285k0.I(Math.max(iMax4 + iMax2 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i2, (-16777216) & iA2), K() ? 0 : AbstractC0285k0.I(Math.max(iMax5 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i3, iA2 << 16));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem menuItemFindItem;
        if (!(parcelable instanceof g)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        g gVar = (g) parcelable;
        super.onRestoreInstanceState(gVar.a());
        ActionMenuView actionMenuView = this.f1547a;
        android.support.v7.view.menu.e eVarL = actionMenuView != null ? actionMenuView.L() : null;
        int i2 = gVar.f1580c;
        if (i2 != 0 && this.f1542J != null && eVarL != null && (menuItemFindItem = eVarL.findItem(i2)) != null) {
            M.a(menuItemFindItem);
        }
        if (gVar.f1581d) {
            E();
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i2) {
        if (Build.VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(i2);
        }
        h();
        this.f1566t.f(i2 == 1);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        android.support.v7.view.menu.g gVar;
        g gVar2 = new g(super.onSaveInstanceState());
        d dVar = this.f1542J;
        if (dVar != null && (gVar = dVar.f1577b) != null) {
            gVar2.f1580c = gVar.getItemId();
        }
        gVar2.f1581d = z();
        return gVar2;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int iB = W.b(motionEvent);
        if (iB == 0) {
            this.f1534B = false;
        }
        if (!this.f1534B) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (iB == 0 && !zOnTouchEvent) {
                this.f1534B = true;
            }
        }
        if (iB == 1 || iB == 3) {
            this.f1534B = false;
        }
        return true;
    }

    public final int p(int i2) {
        int iK = AbstractC0285k0.k(this);
        int iA = r.a(i2, iK) & 7;
        return (iA == 1 || iA == 3 || iA == 5) ? iA : iK == 1 ? 5 : 3;
    }

    public final int q(View view, int i2) {
        e eVar = (e) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i3 = i2 > 0 ? (measuredHeight - i2) / 2 : 0;
        int iR = r(eVar.f6720a);
        if (iR == 48) {
            return getPaddingTop() - i3;
        }
        if (iR == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin) - i3;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int iMax = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i4 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
        if (iMax < i4) {
            iMax = i4;
        } else {
            int i5 = (((height - paddingBottom) - measuredHeight) - iMax) - paddingTop;
            int i6 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
            if (i5 < i6) {
                iMax = Math.max(0, iMax - (i6 - i5));
            }
        }
        return paddingTop + iMax;
    }

    public final int r(int i2) {
        int i3 = i2 & 112;
        return (i3 == 16 || i3 == 48 || i3 == 80) ? i3 : this.f1569w & 112;
    }

    public final int s(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return I.b(marginLayoutParams) + I.a(marginLayoutParams);
    }

    public void setCollapsible(boolean z2) {
        this.f1545M = z2;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i2) {
        if (i2 < 0) {
            i2 = PKIFailureInfo.systemUnavail;
        }
        if (i2 != this.f1568v) {
            this.f1568v = i2;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i2) {
        if (i2 < 0) {
            i2 = PKIFailureInfo.systemUnavail;
        }
        if (i2 != this.f1567u) {
            this.f1567u = i2;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setLogo(int i2) {
        setLogo(q.b.d(getContext(), i2));
    }

    public void setLogoDescription(int i2) {
        setLogoDescription(getContext().getText(i2));
    }

    public void setNavigationContentDescription(int i2) {
        setNavigationContentDescription(i2 != 0 ? getContext().getText(i2) : null);
    }

    public void setNavigationIcon(int i2) {
        setNavigationIcon(q.b.d(getContext(), i2));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        l();
        this.f1550d.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(f fVar) {
    }

    public void setOverflowIcon(Drawable drawable) {
        j();
        this.f1547a.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i2) {
        if (this.f1557k != i2) {
            this.f1557k = i2;
            if (i2 == 0) {
                this.f1556j = getContext();
            } else {
                this.f1556j = new ContextThemeWrapper(getContext(), i2);
            }
        }
    }

    public void setSubtitle(int i2) {
        setSubtitle(getContext().getText(i2));
    }

    public void setSubtitleTextColor(int i2) {
        this.f1533A = i2;
        TextView textView = this.f1549c;
        if (textView != null) {
            textView.setTextColor(i2);
        }
    }

    public void setTitle(int i2) {
        setTitle(getContext().getText(i2));
    }

    public void setTitleMarginBottom(int i2) {
        this.f1565s = i2;
        requestLayout();
    }

    public void setTitleMarginEnd(int i2) {
        this.f1563q = i2;
        requestLayout();
    }

    public void setTitleMarginStart(int i2) {
        this.f1562p = i2;
        requestLayout();
    }

    public void setTitleMarginTop(int i2) {
        this.f1564r = i2;
        requestLayout();
    }

    public void setTitleTextColor(int i2) {
        this.f1572z = i2;
        TextView textView = this.f1548b;
        if (textView != null) {
            textView.setTextColor(i2);
        }
    }

    public final int t(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    public final int u(List list, int[] iArr) {
        int i2 = iArr[0];
        int i3 = iArr[1];
        int size = list.size();
        int i4 = 0;
        int measuredWidth = 0;
        while (i4 < size) {
            View view = (View) list.get(i4);
            e eVar = (e) view.getLayoutParams();
            int i5 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin - i2;
            int i6 = ((ViewGroup.MarginLayoutParams) eVar).rightMargin - i3;
            int iMax = Math.max(0, i5);
            int iMax2 = Math.max(0, i6);
            int iMax3 = Math.max(0, -i5);
            int iMax4 = Math.max(0, -i6);
            measuredWidth += iMax + view.getMeasuredWidth() + iMax2;
            i4++;
            i3 = iMax4;
            i2 = iMax3;
        }
        return measuredWidth;
    }

    public boolean v() {
        d dVar = this.f1542J;
        return (dVar == null || dVar.f1577b == null) ? false : true;
    }

    public boolean w() {
        ActionMenuView actionMenuView = this.f1547a;
        return actionMenuView != null && actionMenuView.F();
    }

    public final boolean x(View view) {
        return view.getParent() == this || this.f1537E.contains(view);
    }

    public boolean y() {
        ActionMenuView actionMenuView = this.f1547a;
        return actionMenuView != null && actionMenuView.G();
    }

    public boolean z() {
        ActionMenuView actionMenuView = this.f1547a;
        return actionMenuView != null && actionMenuView.H();
    }

    public static class e extends AbstractC0430a.C0066a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1579b;

        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1579b = 0;
        }

        public void a(ViewGroup.MarginLayoutParams marginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) this).leftMargin = marginLayoutParams.leftMargin;
            ((ViewGroup.MarginLayoutParams) this).topMargin = marginLayoutParams.topMargin;
            ((ViewGroup.MarginLayoutParams) this).rightMargin = marginLayoutParams.rightMargin;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = marginLayoutParams.bottomMargin;
        }

        public e(int i2, int i3) {
            super(i2, i3);
            this.f1579b = 0;
            this.f6720a = 8388627;
        }

        public e(e eVar) {
            super((AbstractC0430a.C0066a) eVar);
            this.f1579b = 0;
            this.f1579b = eVar.f1579b;
        }

        public e(AbstractC0430a.C0066a c0066a) {
            super(c0066a);
            this.f1579b = 0;
        }

        public e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1579b = 0;
            a(marginLayoutParams);
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1579b = 0;
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f1569w = 8388627;
        this.f1536D = new ArrayList();
        this.f1537E = new ArrayList();
        this.f1538F = new int[2];
        this.f1539G = new a();
        this.f1546N = new b();
        f0 f0VarS = f0.s(getContext(), attributeSet, p.j.c2, i2, 0);
        this.f1558l = f0VarS.l(p.j.D2, 0);
        this.f1559m = f0VarS.l(p.j.u2, 0);
        this.f1569w = f0VarS.j(p.j.d2, this.f1569w);
        this.f1560n = f0VarS.j(p.j.e2, 48);
        int iD = f0VarS.d(p.j.x2, 0);
        iD = f0VarS.p(p.j.C2) ? f0VarS.d(p.j.C2, iD) : iD;
        this.f1565s = iD;
        this.f1564r = iD;
        this.f1563q = iD;
        this.f1562p = iD;
        int iD2 = f0VarS.d(p.j.A2, -1);
        if (iD2 >= 0) {
            this.f1562p = iD2;
        }
        int iD3 = f0VarS.d(p.j.z2, -1);
        if (iD3 >= 0) {
            this.f1563q = iD3;
        }
        int iD4 = f0VarS.d(p.j.B2, -1);
        if (iD4 >= 0) {
            this.f1564r = iD4;
        }
        int iD5 = f0VarS.d(p.j.y2, -1);
        if (iD5 >= 0) {
            this.f1565s = iD5;
        }
        this.f1561o = f0VarS.e(p.j.p2, -1);
        int iD6 = f0VarS.d(p.j.l2, PKIFailureInfo.systemUnavail);
        int iD7 = f0VarS.d(p.j.h2, PKIFailureInfo.systemUnavail);
        int iE = f0VarS.e(p.j.j2, 0);
        int iE2 = f0VarS.e(p.j.k2, 0);
        h();
        this.f1566t.e(iE, iE2);
        if (iD6 != Integer.MIN_VALUE || iD7 != Integer.MIN_VALUE) {
            this.f1566t.g(iD6, iD7);
        }
        this.f1567u = f0VarS.d(p.j.m2, PKIFailureInfo.systemUnavail);
        this.f1568v = f0VarS.d(p.j.i2, PKIFailureInfo.systemUnavail);
        this.f1552f = f0VarS.f(p.j.g2);
        this.f1553g = f0VarS.n(p.j.f2);
        CharSequence charSequenceN = f0VarS.n(p.j.w2);
        if (!TextUtils.isEmpty(charSequenceN)) {
            setTitle(charSequenceN);
        }
        CharSequence charSequenceN2 = f0VarS.n(p.j.t2);
        if (!TextUtils.isEmpty(charSequenceN2)) {
            setSubtitle(charSequenceN2);
        }
        this.f1556j = getContext();
        setPopupTheme(f0VarS.l(p.j.s2, 0));
        Drawable drawableF = f0VarS.f(p.j.r2);
        if (drawableF != null) {
            setNavigationIcon(drawableF);
        }
        CharSequence charSequenceN3 = f0VarS.n(p.j.q2);
        if (!TextUtils.isEmpty(charSequenceN3)) {
            setNavigationContentDescription(charSequenceN3);
        }
        Drawable drawableF2 = f0VarS.f(p.j.n2);
        if (drawableF2 != null) {
            setLogo(drawableF2);
        }
        CharSequence charSequenceN4 = f0VarS.n(p.j.o2);
        if (!TextUtils.isEmpty(charSequenceN4)) {
            setLogoDescription(charSequenceN4);
        }
        if (f0VarS.p(p.j.E2)) {
            setTitleTextColor(f0VarS.b(p.j.E2, -1));
        }
        if (f0VarS.p(p.j.v2)) {
            setSubtitleTextColor(f0VarS.b(p.j.v2, -1));
        }
        f0VarS.t();
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            i();
            if (!x(this.f1551e)) {
                c(this.f1551e, true);
            }
        } else {
            ImageView imageView = this.f1551e;
            if (imageView != null && x(imageView)) {
                removeView(this.f1551e);
                this.f1537E.remove(this.f1551e);
            }
        }
        ImageView imageView2 = this.f1551e;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            i();
        }
        ImageView imageView = this.f1551e;
        if (imageView != null) {
            imageView.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            l();
        }
        ImageButton imageButton = this.f1550d;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            l();
            if (!x(this.f1550d)) {
                c(this.f1550d, true);
            }
        } else {
            ImageButton imageButton = this.f1550d;
            if (imageButton != null && x(imageButton)) {
                removeView(this.f1550d);
                this.f1537E.remove(this.f1550d);
            }
        }
        ImageButton imageButton2 = this.f1550d;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.f1549c;
            if (textView != null && x(textView)) {
                removeView(this.f1549c);
                this.f1537E.remove(this.f1549c);
            }
        } else {
            if (this.f1549c == null) {
                Context context = getContext();
                E e2 = new E(context);
                this.f1549c = e2;
                e2.setSingleLine();
                this.f1549c.setEllipsize(TextUtils.TruncateAt.END);
                int i2 = this.f1559m;
                if (i2 != 0) {
                    this.f1549c.setTextAppearance(context, i2);
                }
                int i3 = this.f1533A;
                if (i3 != 0) {
                    this.f1549c.setTextColor(i3);
                }
            }
            if (!x(this.f1549c)) {
                c(this.f1549c, true);
            }
        }
        TextView textView2 = this.f1549c;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.f1571y = charSequence;
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.f1548b;
            if (textView != null && x(textView)) {
                removeView(this.f1548b);
                this.f1537E.remove(this.f1548b);
            }
        } else {
            if (this.f1548b == null) {
                Context context = getContext();
                E e2 = new E(context);
                this.f1548b = e2;
                e2.setSingleLine();
                this.f1548b.setEllipsize(TextUtils.TruncateAt.END);
                int i2 = this.f1558l;
                if (i2 != 0) {
                    this.f1548b.setTextAppearance(context, i2);
                }
                int i3 = this.f1572z;
                if (i3 != 0) {
                    this.f1548b.setTextColor(i3);
                }
            }
            if (!x(this.f1548b)) {
                c(this.f1548b, true);
            }
        }
        TextView textView2 = this.f1548b;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.f1570x = charSequence;
    }

    public static class g extends android.support.v4.view.a {
        public static final Parcelable.Creator<g> CREATOR = AbstractC0251b.a(new a());

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1580c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1581d;

        public static class a implements i.c {
            @Override // i.c
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public g createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new g(parcel, classLoader);
            }

            @Override // i.c
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public g[] newArray(int i2) {
                return new g[i2];
            }
        }

        public g(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1580c = parcel.readInt();
            this.f1581d = parcel.readInt() != 0;
        }

        @Override // android.support.v4.view.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f1580c);
            parcel.writeInt(this.f1581d ? 1 : 0);
        }

        public g(Parcelable parcelable) {
            super(parcelable);
        }
    }
}

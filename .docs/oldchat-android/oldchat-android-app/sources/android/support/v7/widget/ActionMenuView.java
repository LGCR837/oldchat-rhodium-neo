package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.e;
import android.support.v7.view.menu.i;
import android.support.v7.widget.g;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import x.h0;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuView extends g implements e.b, android.support.v7.view.menu.j {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public e f1197A;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public android.support.v7.view.menu.e f1198p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Context f1199q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f1200r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f1201s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public android.support.v7.widget.a f1202t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public i.a f1203u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public e.a f1204v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f1205w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f1206x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f1207y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f1208z;

    public interface a {
        boolean a();

        boolean c();
    }

    public class b implements i.a {
        public b() {
        }

        @Override // android.support.v7.view.menu.i.a
        public void a(android.support.v7.view.menu.e eVar, boolean z2) {
        }

        @Override // android.support.v7.view.menu.i.a
        public boolean b(android.support.v7.view.menu.e eVar) {
            return false;
        }
    }

    public static class c extends g.a {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1210c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1211d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1212e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1213f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f1214g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f1215h;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public c(c cVar) {
            super(cVar);
            this.f1210c = cVar.f1210c;
        }

        public c(int i2, int i3) {
            super(i2, i3);
            this.f1210c = false;
        }
    }

    public class d implements e.a {
        public d() {
        }

        @Override // android.support.v7.view.menu.e.a
        public void a(android.support.v7.view.menu.e eVar) {
            e.a aVar = ActionMenuView.this.f1204v;
            if (aVar != null) {
                aVar.a(eVar);
            }
        }

        @Override // android.support.v7.view.menu.e.a
        public boolean b(android.support.v7.view.menu.e eVar, MenuItem menuItem) {
            e eVar2 = ActionMenuView.this.f1197A;
            return eVar2 != null && eVar2.onMenuItemClick(menuItem);
        }
    }

    public interface e {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int J(android.view.View r5, int r6, int r7, int r8, int r9) {
        /*
            android.view.ViewGroup$LayoutParams r0 = r5.getLayoutParams()
            android.support.v7.widget.ActionMenuView$c r0 = (android.support.v7.widget.ActionMenuView.c) r0
            int r1 = android.view.View.MeasureSpec.getSize(r8)
            int r1 = r1 - r9
            int r8 = android.view.View.MeasureSpec.getMode(r8)
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r8)
            boolean r9 = r5 instanceof android.support.v7.view.menu.ActionMenuItemView
            if (r9 == 0) goto L1b
            r9 = r5
            android.support.v7.view.menu.ActionMenuItemView r9 = (android.support.v7.view.menu.ActionMenuItemView) r9
            goto L1c
        L1b:
            r9 = 0
        L1c:
            r1 = 0
            r2 = 1
            if (r9 == 0) goto L28
            boolean r9 = r9.e()
            if (r9 == 0) goto L28
            r9 = 1
            goto L29
        L28:
            r9 = 0
        L29:
            if (r7 <= 0) goto L4d
            r3 = 2
            if (r9 == 0) goto L30
            if (r7 < r3) goto L4d
        L30:
            int r7 = r7 * r6
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r4)
            r5.measure(r7, r8)
            int r7 = r5.getMeasuredWidth()
            int r4 = r7 / r6
            int r7 = r7 % r6
            if (r7 == 0) goto L46
            int r4 = r4 + 1
        L46:
            if (r9 == 0) goto L4b
            if (r4 >= r3) goto L4b
            goto L4e
        L4b:
            r3 = r4
            goto L4e
        L4d:
            r3 = 0
        L4e:
            boolean r7 = r0.f1210c
            if (r7 != 0) goto L55
            if (r9 == 0) goto L55
            r1 = 1
        L55:
            r0.f1213f = r1
            r0.f1211d = r3
            int r6 = r6 * r3
            r7 = 1073741824(0x40000000, float:2.0)
            int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r6, r7)
            r5.measure(r6, r8)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActionMenuView.J(android.view.View, int, int, int, int):int");
    }

    @Override // android.support.v7.widget.g
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public c generateDefaultLayoutParams() {
        c cVar = new c(-2, -2);
        cVar.f1705b = 16;
        return cVar;
    }

    @Override // android.support.v7.widget.g
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public c generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    @Override // android.support.v7.widget.g
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public c generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams == null) {
            return generateDefaultLayoutParams();
        }
        c cVar = layoutParams instanceof c ? new c((c) layoutParams) : new c(layoutParams);
        if (cVar.f1705b <= 0) {
            cVar.f1705b = 16;
        }
        return cVar;
    }

    public c D() {
        c cVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
        cVarGenerateDefaultLayoutParams.f1210c = true;
        return cVarGenerateDefaultLayoutParams;
    }

    public boolean E(int i2) {
        boolean zA = false;
        if (i2 == 0) {
            return false;
        }
        KeyEvent.Callback childAt = getChildAt(i2 - 1);
        KeyEvent.Callback childAt2 = getChildAt(i2);
        if (i2 < getChildCount() && (childAt instanceof a)) {
            zA = ((a) childAt).a();
        }
        return (i2 <= 0 || !(childAt2 instanceof a)) ? zA : zA | ((a) childAt2).c();
    }

    public boolean F() {
        android.support.v7.widget.a aVar = this.f1202t;
        return aVar != null && aVar.A();
    }

    public boolean G() {
        android.support.v7.widget.a aVar = this.f1202t;
        return aVar != null && aVar.C();
    }

    public boolean H() {
        android.support.v7.widget.a aVar = this.f1202t;
        return aVar != null && aVar.D();
    }

    public boolean I() {
        return this.f1201s;
    }

    /* JADX WARN: Type inference failed for: r14v13 */
    /* JADX WARN: Type inference failed for: r14v14, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r14v17 */
    public final void K(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        boolean z2;
        int i7;
        boolean z3;
        int i8;
        int i9;
        ?? r14;
        int mode = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i3, paddingTop, -2);
        int i10 = size - paddingLeft;
        int i11 = this.f1207y;
        int i12 = i10 / i11;
        int i13 = i10 % i11;
        if (i12 == 0) {
            setMeasuredDimension(i10, 0);
            return;
        }
        int i14 = i11 + (i13 / i12);
        int childCount = getChildCount();
        int iMax = 0;
        int i15 = 0;
        boolean z4 = false;
        int i16 = 0;
        int iMax2 = 0;
        int i17 = 0;
        long j2 = 0;
        while (i15 < childCount) {
            View childAt = getChildAt(i15);
            int i18 = size2;
            if (childAt.getVisibility() != 8) {
                boolean z5 = childAt instanceof ActionMenuItemView;
                int i19 = i16 + 1;
                if (z5) {
                    int i20 = this.f1208z;
                    i9 = i19;
                    r14 = 0;
                    childAt.setPadding(i20, 0, i20, 0);
                } else {
                    i9 = i19;
                    r14 = 0;
                }
                c cVar = (c) childAt.getLayoutParams();
                cVar.f1215h = r14;
                cVar.f1212e = r14;
                cVar.f1211d = r14;
                cVar.f1213f = r14;
                ((ViewGroup.MarginLayoutParams) cVar).leftMargin = r14;
                ((ViewGroup.MarginLayoutParams) cVar).rightMargin = r14;
                cVar.f1214g = z5 && ((ActionMenuItemView) childAt).e();
                int iJ = J(childAt, i14, cVar.f1210c ? 1 : i12, childMeasureSpec, paddingTop);
                iMax2 = Math.max(iMax2, iJ);
                if (cVar.f1213f) {
                    i17++;
                }
                if (cVar.f1210c) {
                    z4 = true;
                }
                i12 -= iJ;
                iMax = Math.max(iMax, childAt.getMeasuredHeight());
                if (iJ == 1) {
                    j2 |= (long) (1 << i15);
                    iMax = iMax;
                }
                i16 = i9;
            }
            i15++;
            size2 = i18;
        }
        int i21 = size2;
        boolean z6 = z4 && i16 == 2;
        boolean z7 = false;
        while (i17 > 0 && i12 > 0) {
            z2 = z7;
            i7 = iMax;
            int i22 = Integer.MAX_VALUE;
            int i23 = 0;
            int i24 = 0;
            long j3 = 0;
            while (i24 < childCount) {
                int i25 = i10;
                c cVar2 = (c) getChildAt(i24).getLayoutParams();
                int i26 = mode;
                if (cVar2.f1213f) {
                    int i27 = cVar2.f1211d;
                    if (i27 < i22) {
                        j3 = 1 << i24;
                        i23 = 1;
                        i22 = i27;
                    } else if (i27 == i22) {
                        i23++;
                        j3 = ((long) (1 << i24)) | j3;
                    }
                }
                i24++;
                mode = i26;
                i10 = i25;
            }
            i4 = mode;
            i5 = i10;
            j2 |= j3;
            if (i23 > i12) {
                i6 = childMeasureSpec;
                break;
            }
            int i28 = i22 + 1;
            int i29 = 0;
            while (i29 < childCount) {
                View childAt2 = getChildAt(i29);
                c cVar3 = (c) childAt2.getLayoutParams();
                int i30 = childMeasureSpec;
                int i31 = i16;
                int i32 = iMax2;
                long j4 = 1 << i29;
                if ((j3 & j4) != 0) {
                    if (z6 && cVar3.f1214g && i12 == 1) {
                        int i33 = this.f1208z;
                        childAt2.setPadding(i33 + i14, 0, i33, 0);
                    }
                    cVar3.f1211d++;
                    cVar3.f1215h = true;
                    i12--;
                } else if (cVar3.f1211d == i28) {
                    j2 |= j4;
                }
                i29++;
                i16 = i31;
                childMeasureSpec = i30;
                iMax2 = i32;
            }
            iMax = i7;
            mode = i4;
            i10 = i5;
            z7 = true;
        }
        i4 = mode;
        i5 = i10;
        i6 = childMeasureSpec;
        z2 = z7;
        i7 = iMax;
        int i34 = i16;
        int i35 = iMax2;
        boolean z8 = !z4 && i34 == 1;
        if (i12 <= 0 || j2 == 0 || (i12 >= i34 - 1 && !z8 && i35 <= 1)) {
            z3 = z2;
        } else {
            float fBitCount = Long.bitCount(j2);
            if (!z8) {
                if ((j2 & 1) != 0 && !((c) getChildAt(0).getLayoutParams()).f1214g) {
                    fBitCount -= 0.5f;
                }
                int i36 = childCount - 1;
                if ((j2 & ((long) (1 << i36))) != 0 && !((c) getChildAt(i36).getLayoutParams()).f1214g) {
                    fBitCount -= 0.5f;
                }
            }
            int i37 = fBitCount > 0.0f ? (int) ((i12 * i14) / fBitCount) : 0;
            z3 = z2;
            for (int i38 = 0; i38 < childCount; i38++) {
                if ((j2 & ((long) (1 << i38))) != 0) {
                    View childAt3 = getChildAt(i38);
                    c cVar4 = (c) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        cVar4.f1212e = i37;
                        cVar4.f1215h = true;
                        if (i38 == 0 && !cVar4.f1214g) {
                            ((ViewGroup.MarginLayoutParams) cVar4).leftMargin = (-i37) / 2;
                        }
                    } else if (cVar4.f1210c) {
                        cVar4.f1212e = i37;
                        cVar4.f1215h = true;
                        ((ViewGroup.MarginLayoutParams) cVar4).rightMargin = (-i37) / 2;
                    } else {
                        if (i38 != 0) {
                            ((ViewGroup.MarginLayoutParams) cVar4).leftMargin = i37 / 2;
                        }
                        if (i38 != childCount - 1) {
                            ((ViewGroup.MarginLayoutParams) cVar4).rightMargin = i37 / 2;
                        }
                    }
                    z3 = true;
                }
            }
        }
        if (z3) {
            int i39 = 0;
            while (i39 < childCount) {
                View childAt4 = getChildAt(i39);
                c cVar5 = (c) childAt4.getLayoutParams();
                if (cVar5.f1215h) {
                    i8 = i6;
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec((cVar5.f1211d * i14) + cVar5.f1212e, 1073741824), i8);
                } else {
                    i8 = i6;
                }
                i39++;
                i6 = i8;
            }
        }
        setMeasuredDimension(i5, i4 != 1073741824 ? i7 : i21);
    }

    public android.support.v7.view.menu.e L() {
        return this.f1198p;
    }

    public void M(i.a aVar, e.a aVar2) {
        this.f1203u = aVar;
        this.f1204v = aVar2;
    }

    public boolean N() {
        android.support.v7.widget.a aVar = this.f1202t;
        return aVar != null && aVar.J();
    }

    @Override // android.support.v7.view.menu.e.b
    public boolean a(android.support.v7.view.menu.g gVar) {
        return this.f1198p.K(gVar, 0);
    }

    @Override // android.support.v7.view.menu.j
    public void c(android.support.v7.view.menu.e eVar) {
        this.f1198p = eVar;
    }

    @Override // android.support.v7.widget.g, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams != null && (layoutParams instanceof c);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    public Menu getMenu() {
        if (this.f1198p == null) {
            Context context = getContext();
            android.support.v7.view.menu.e eVar = new android.support.v7.view.menu.e(context);
            this.f1198p = eVar;
            eVar.Q(new d());
            android.support.v7.widget.a aVar = new android.support.v7.widget.a(context);
            this.f1202t = aVar;
            aVar.I(true);
            android.support.v7.widget.a aVar2 = this.f1202t;
            i.a bVar = this.f1203u;
            if (bVar == null) {
                bVar = new b();
            }
            aVar2.f(bVar);
            this.f1198p.c(this.f1202t, this.f1199q);
            this.f1202t.G(this);
        }
        return this.f1198p;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        return this.f1202t.z();
    }

    public int getPopupTheme() {
        return this.f1200r;
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        android.support.v7.widget.a aVar = this.f1202t;
        if (aVar != null) {
            aVar.k(false);
            if (this.f1202t.D()) {
                this.f1202t.A();
                this.f1202t.J();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        z();
    }

    @Override // android.support.v7.widget.g, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int width;
        int paddingLeft;
        if (!this.f1205w) {
            super.onLayout(z2, i2, i3, i4, i5);
            return;
        }
        int childCount = getChildCount();
        int i6 = (i5 - i3) / 2;
        int dividerWidth = getDividerWidth();
        int i7 = i4 - i2;
        int paddingRight = (i7 - getPaddingRight()) - getPaddingLeft();
        boolean zC = h0.c(this);
        int i8 = 0;
        int i9 = 0;
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = getChildAt(i10);
            if (childAt.getVisibility() != 8) {
                c cVar = (c) childAt.getLayoutParams();
                if (cVar.f1210c) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (E(i10)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (zC) {
                        paddingLeft = getPaddingLeft() + ((ViewGroup.MarginLayoutParams) cVar).leftMargin;
                        width = paddingLeft + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) cVar).rightMargin;
                        paddingLeft = width - measuredWidth;
                    }
                    int i11 = i6 - (measuredHeight / 2);
                    childAt.layout(paddingLeft, i11, width, measuredHeight + i11);
                    paddingRight -= measuredWidth;
                    i8 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) cVar).leftMargin) + ((ViewGroup.MarginLayoutParams) cVar).rightMargin;
                    E(i10);
                    i9++;
                }
            }
        }
        if (childCount == 1 && i8 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i12 = (i7 / 2) - (measuredWidth2 / 2);
            int i13 = i6 - (measuredHeight2 / 2);
            childAt2.layout(i12, i13, measuredWidth2 + i12, measuredHeight2 + i13);
            return;
        }
        int i14 = i9 - (i8 ^ 1);
        int iMax = Math.max(0, i14 > 0 ? paddingRight / i14 : 0);
        if (zC) {
            int width2 = getWidth() - getPaddingRight();
            for (int i15 = 0; i15 < childCount; i15++) {
                View childAt3 = getChildAt(i15);
                c cVar2 = (c) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !cVar2.f1210c) {
                    int i16 = width2 - ((ViewGroup.MarginLayoutParams) cVar2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i17 = i6 - (measuredHeight3 / 2);
                    childAt3.layout(i16 - measuredWidth3, i17, i16, measuredHeight3 + i17);
                    width2 = i16 - ((measuredWidth3 + ((ViewGroup.MarginLayoutParams) cVar2).leftMargin) + iMax);
                }
            }
            return;
        }
        int paddingLeft2 = getPaddingLeft();
        for (int i18 = 0; i18 < childCount; i18++) {
            View childAt4 = getChildAt(i18);
            c cVar3 = (c) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !cVar3.f1210c) {
                int i19 = paddingLeft2 + ((ViewGroup.MarginLayoutParams) cVar3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i20 = i6 - (measuredHeight4 / 2);
                childAt4.layout(i19, i20, i19 + measuredWidth4, measuredHeight4 + i20);
                paddingLeft2 = i19 + measuredWidth4 + ((ViewGroup.MarginLayoutParams) cVar3).rightMargin + iMax;
            }
        }
    }

    @Override // android.support.v7.widget.g, android.view.View
    public void onMeasure(int i2, int i3) {
        android.support.v7.view.menu.e eVar;
        boolean z2 = this.f1205w;
        boolean z3 = View.MeasureSpec.getMode(i2) == 1073741824;
        this.f1205w = z3;
        if (z2 != z3) {
            this.f1206x = 0;
        }
        int size = View.MeasureSpec.getSize(i2);
        if (this.f1205w && (eVar = this.f1198p) != null && size != this.f1206x) {
            this.f1206x = size;
            eVar.J(true);
        }
        int childCount = getChildCount();
        if (this.f1205w && childCount > 0) {
            K(i2, i3);
            return;
        }
        for (int i4 = 0; i4 < childCount; i4++) {
            c cVar = (c) getChildAt(i4).getLayoutParams();
            ((ViewGroup.MarginLayoutParams) cVar).rightMargin = 0;
            ((ViewGroup.MarginLayoutParams) cVar).leftMargin = 0;
        }
        super.onMeasure(i2, i3);
    }

    public void setExpandedActionViewsExclusive(boolean z2) {
        this.f1202t.F(z2);
    }

    public void setOnMenuItemClickListener(e eVar) {
        this.f1197A = eVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        this.f1202t.H(drawable);
    }

    public void setOverflowReserved(boolean z2) {
        this.f1201s = z2;
    }

    public void setPopupTheme(int i2) {
        if (this.f1200r != i2) {
            this.f1200r = i2;
            if (i2 == 0) {
                this.f1199q = getContext();
            } else {
                this.f1199q = new ContextThemeWrapper(getContext(), i2);
            }
        }
    }

    public void setPresenter(android.support.v7.widget.a aVar) {
        this.f1202t = aVar;
        aVar.G(this);
    }

    public void z() {
        android.support.v7.widget.a aVar = this.f1202t;
        if (aVar != null) {
            aVar.x();
        }
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.f1207y = (int) (56.0f * f2);
        this.f1208z = (int) (f2 * 4.0f);
        this.f1199q = context;
        this.f1200r = 0;
    }
}

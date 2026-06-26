package x;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;
import k.AbstractC0285k0;
import k.InterfaceC0276h0;
import p.AbstractC0460a;

/* JADX INFO: renamed from: x.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0519w extends Spinner implements InterfaceC0276h0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final boolean f8208i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final boolean f8209j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final int[] f8210k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public C0503f f8211a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f8212b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public L f8213c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public SpinnerAdapter f8214d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8215e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c f8216f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f8217g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Rect f8218h;

    /* JADX INFO: renamed from: x.w$a */
    public class a extends L {

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final /* synthetic */ c f8219j;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(View view, c cVar) {
            super(view);
            this.f8219j = cVar;
        }

        @Override // x.L
        public w.z b() {
            return this.f8219j;
        }

        @Override // x.L
        public boolean c() {
            if (C0519w.this.f8216f.h()) {
                return true;
            }
            C0519w.this.f8216f.c();
            return true;
        }
    }

    /* JADX INFO: renamed from: x.w$b */
    public static class b implements ListAdapter, SpinnerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public SpinnerAdapter f8221a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ListAdapter f8222b;

        public b(SpinnerAdapter spinnerAdapter, Resources.Theme theme) {
            this.f8221a = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.f8222b = (ListAdapter) spinnerAdapter;
            }
            if (theme != null && C0519w.f8208i && AbstractC0520x.a(spinnerAdapter)) {
                ThemedSpinnerAdapter themedSpinnerAdapterA = AbstractC0521y.a(spinnerAdapter);
                if (themedSpinnerAdapterA.getDropDownViewTheme() != theme) {
                    themedSpinnerAdapterA.setDropDownViewTheme(theme);
                }
            }
        }

        @Override // android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.f8222b;
            if (listAdapter != null) {
                return listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            SpinnerAdapter spinnerAdapter = this.f8221a;
            if (spinnerAdapter == null) {
                return 0;
            }
            return spinnerAdapter.getCount();
        }

        @Override // android.widget.SpinnerAdapter
        public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
            SpinnerAdapter spinnerAdapter = this.f8221a;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getDropDownView(i2, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            SpinnerAdapter spinnerAdapter = this.f8221a;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getItem(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            SpinnerAdapter spinnerAdapter = this.f8221a;
            if (spinnerAdapter == null) {
                return -1L;
            }
            return spinnerAdapter.getItemId(i2);
        }

        @Override // android.widget.Adapter
        public int getItemViewType(int i2) {
            return 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            return getDropDownView(i2, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public int getViewTypeCount() {
            return 1;
        }

        @Override // android.widget.Adapter
        public boolean hasStableIds() {
            SpinnerAdapter spinnerAdapter = this.f8221a;
            return spinnerAdapter != null && spinnerAdapter.hasStableIds();
        }

        @Override // android.widget.Adapter
        public boolean isEmpty() {
            return getCount() == 0;
        }

        @Override // android.widget.ListAdapter
        public boolean isEnabled(int i2) {
            ListAdapter listAdapter = this.f8222b;
            if (listAdapter != null) {
                return listAdapter.isEnabled(i2);
            }
            return true;
        }

        @Override // android.widget.Adapter
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.f8221a;
            if (spinnerAdapter != null) {
                spinnerAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        @Override // android.widget.Adapter
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.f8221a;
            if (spinnerAdapter != null) {
                spinnerAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }

    /* JADX INFO: renamed from: x.w$c */
    public class c extends M {

        /* JADX INFO: renamed from: I, reason: collision with root package name */
        public CharSequence f8223I;

        /* JADX INFO: renamed from: J, reason: collision with root package name */
        public ListAdapter f8224J;

        /* JADX INFO: renamed from: K, reason: collision with root package name */
        public final Rect f8225K;

        /* JADX INFO: renamed from: x.w$c$a */
        public class a implements AdapterView.OnItemClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ C0519w f8227a;

            public a(C0519w c0519w) {
                this.f8227a = c0519w;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
                C0519w.this.setSelection(i2);
                if (C0519w.this.getOnItemClickListener() != null) {
                    c cVar = c.this;
                    C0519w.this.performItemClick(view, i2, cVar.f8224J.getItemId(i2));
                }
                c.this.dismiss();
            }
        }

        /* JADX INFO: renamed from: x.w$c$b */
        public class b implements ViewTreeObserver.OnGlobalLayoutListener {
            public b() {
            }

            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                c cVar = c.this;
                if (!cVar.L(C0519w.this)) {
                    c.this.dismiss();
                } else {
                    c.this.J();
                    c.super.c();
                }
            }
        }

        /* JADX INFO: renamed from: x.w$c$c, reason: collision with other inner class name */
        public class C0071c implements PopupWindow.OnDismissListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ ViewTreeObserver.OnGlobalLayoutListener f8230a;

            public C0071c(ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
                this.f8230a = onGlobalLayoutListener;
            }

            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                ViewTreeObserver viewTreeObserver = C0519w.this.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    viewTreeObserver.removeGlobalOnLayoutListener(this.f8230a);
                }
            }
        }

        public c(Context context, AttributeSet attributeSet, int i2) {
            super(context, attributeSet, i2);
            this.f8225K = new Rect();
            s(C0519w.this);
            A(true);
            E(0);
            C(new a(C0519w.this));
        }

        public void J() {
            int i2;
            Drawable drawableJ = j();
            if (drawableJ != null) {
                drawableJ.getPadding(C0519w.this.f8218h);
                i2 = h0.c(C0519w.this) ? C0519w.this.f8218h.right : -C0519w.this.f8218h.left;
            } else {
                Rect rect = C0519w.this.f8218h;
                rect.right = 0;
                rect.left = 0;
                i2 = 0;
            }
            int paddingLeft = C0519w.this.getPaddingLeft();
            int paddingRight = C0519w.this.getPaddingRight();
            int width = C0519w.this.getWidth();
            C0519w c0519w = C0519w.this;
            int i3 = c0519w.f8217g;
            if (i3 == -2) {
                int iA = c0519w.a((SpinnerAdapter) this.f8224J, j());
                int i4 = C0519w.this.getContext().getResources().getDisplayMetrics().widthPixels;
                Rect rect2 = C0519w.this.f8218h;
                int i5 = (i4 - rect2.left) - rect2.right;
                if (iA > i5) {
                    iA = i5;
                }
                v(Math.max(iA, (width - paddingLeft) - paddingRight));
            } else if (i3 == -1) {
                v((width - paddingLeft) - paddingRight);
            } else {
                v(i3);
            }
            y(h0.c(C0519w.this) ? i2 + ((width - paddingRight) - n()) : i2 + paddingLeft);
        }

        public CharSequence K() {
            return this.f8223I;
        }

        public boolean L(View view) {
            return AbstractC0285k0.y(view) && view.getGlobalVisibleRect(this.f8225K);
        }

        public void M(CharSequence charSequence) {
            this.f8223I = charSequence;
        }

        @Override // x.M, w.z
        public void c() {
            ViewTreeObserver viewTreeObserver;
            boolean zH = h();
            J();
            z(2);
            super.c();
            i().setChoiceMode(1);
            F(C0519w.this.getSelectedItemPosition());
            if (zH || (viewTreeObserver = C0519w.this.getViewTreeObserver()) == null) {
                return;
            }
            b bVar = new b();
            viewTreeObserver.addOnGlobalLayoutListener(bVar);
            B(new C0071c(bVar));
        }

        @Override // x.M
        public void r(ListAdapter listAdapter) {
            super.r(listAdapter);
            this.f8224J = listAdapter;
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        f8208i = i2 >= 23;
        f8209j = i2 >= 16;
        f8210k = new int[]{R.attr.spinnerMode};
    }

    public C0519w(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, AbstractC0460a.f7703G);
    }

    public int a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i2 = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int iMax = Math.max(0, getSelectedItemPosition());
        int iMin = Math.min(spinnerAdapter.getCount(), iMax + 15);
        View view = null;
        int iMax2 = 0;
        for (int iMax3 = Math.max(0, iMax - (15 - (iMin - iMax))); iMax3 < iMin; iMax3++) {
            int itemViewType = spinnerAdapter.getItemViewType(iMax3);
            if (itemViewType != i2) {
                view = null;
                i2 = itemViewType;
            }
            view = spinnerAdapter.getView(iMax3, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            iMax2 = Math.max(iMax2, view.getMeasuredWidth());
        }
        if (drawable == null) {
            return iMax2;
        }
        drawable.getPadding(this.f8218h);
        Rect rect = this.f8218h;
        return iMax2 + rect.left + rect.right;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0503f c0503f = this.f8211a;
        if (c0503f != null) {
            c0503f.b();
        }
    }

    @Override // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        c cVar = this.f8216f;
        if (cVar != null) {
            return cVar.k();
        }
        if (f8209j) {
            return super.getDropDownHorizontalOffset();
        }
        return 0;
    }

    @Override // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        c cVar = this.f8216f;
        if (cVar != null) {
            return cVar.m();
        }
        if (f8209j) {
            return super.getDropDownVerticalOffset();
        }
        return 0;
    }

    @Override // android.widget.Spinner
    public int getDropDownWidth() {
        if (this.f8216f != null) {
            return this.f8217g;
        }
        if (f8209j) {
            return super.getDropDownWidth();
        }
        return 0;
    }

    @Override // android.widget.Spinner
    public Drawable getPopupBackground() {
        c cVar = this.f8216f;
        if (cVar != null) {
            return cVar.j();
        }
        if (f8209j) {
            return super.getPopupBackground();
        }
        return null;
    }

    @Override // android.widget.Spinner
    public Context getPopupContext() {
        if (this.f8216f != null) {
            return this.f8212b;
        }
        if (f8208i) {
            return super.getPopupContext();
        }
        return null;
    }

    @Override // android.widget.Spinner
    public CharSequence getPrompt() {
        c cVar = this.f8216f;
        return cVar != null ? cVar.K() : super.getPrompt();
    }

    @Override // k.InterfaceC0276h0
    public ColorStateList getSupportBackgroundTintList() {
        C0503f c0503f = this.f8211a;
        if (c0503f != null) {
            return c0503f.c();
        }
        return null;
    }

    @Override // k.InterfaceC0276h0
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0503f c0503f = this.f8211a;
        if (c0503f != null) {
            return c0503f.d();
        }
        return null;
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c cVar = this.f8216f;
        if (cVar == null || !cVar.h()) {
            return;
        }
        this.f8216f.dismiss();
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.f8216f == null || View.MeasureSpec.getMode(i2) != Integer.MIN_VALUE) {
            return;
        }
        setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), a(getAdapter(), getBackground())), View.MeasureSpec.getSize(i2)), getMeasuredHeight());
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        L l2 = this.f8213c;
        if (l2 == null || !l2.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean performClick() {
        c cVar = this.f8216f;
        if (cVar == null) {
            return super.performClick();
        }
        if (cVar.h()) {
            return true;
        }
        this.f8216f.c();
        return true;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0503f c0503f = this.f8211a;
        if (c0503f != null) {
            c0503f.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        C0503f c0503f = this.f8211a;
        if (c0503f != null) {
            c0503f.g(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownHorizontalOffset(int i2) {
        c cVar = this.f8216f;
        if (cVar != null) {
            cVar.y(i2);
        } else if (f8209j) {
            super.setDropDownHorizontalOffset(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownVerticalOffset(int i2) {
        c cVar = this.f8216f;
        if (cVar != null) {
            cVar.G(i2);
        } else if (f8209j) {
            super.setDropDownVerticalOffset(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownWidth(int i2) {
        if (this.f8216f != null) {
            this.f8217g = i2;
        } else if (f8209j) {
            super.setDropDownWidth(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable drawable) {
        c cVar = this.f8216f;
        if (cVar != null) {
            cVar.u(drawable);
        } else if (f8209j) {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundResource(int i2) {
        setPopupBackgroundDrawable(q.b.d(getPopupContext(), i2));
    }

    @Override // android.widget.Spinner
    public void setPrompt(CharSequence charSequence) {
        c cVar = this.f8216f;
        if (cVar != null) {
            cVar.M(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    @Override // k.InterfaceC0276h0
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0503f c0503f = this.f8211a;
        if (c0503f != null) {
            c0503f.i(colorStateList);
        }
    }

    @Override // k.InterfaceC0276h0
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0503f c0503f = this.f8211a;
        if (c0503f != null) {
            c0503f.j(mode);
        }
    }

    public C0519w(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, -1);
    }

    @Override // android.widget.AdapterView
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.f8215e) {
            this.f8214d = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        if (this.f8216f != null) {
            Context context = this.f8212b;
            if (context == null) {
                context = getContext();
            }
            this.f8216f.r(new b(spinnerAdapter, context.getTheme()));
        }
    }

    public C0519w(Context context, AttributeSet attributeSet, int i2, int i3) {
        this(context, attributeSet, i2, i3, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public C0519w(android.content.Context r8, android.util.AttributeSet r9, int r10, int r11, android.content.res.Resources.Theme r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 221
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: x.C0519w.<init>(android.content.Context, android.util.AttributeSet, int, int, android.content.res.Resources$Theme):void");
    }
}

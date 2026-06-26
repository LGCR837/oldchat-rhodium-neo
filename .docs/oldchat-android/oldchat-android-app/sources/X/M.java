package x;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import java.lang.reflect.Method;
import k.AbstractC0285k0;
import n.AbstractC0414B;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: classes.dex */
public abstract class M implements w.z {

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static Method f8039F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public static Method f8040G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public static Method f8041H;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final Handler f8042A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public final Rect f8043B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public Rect f8044C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public boolean f8045D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public PopupWindow f8046E;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f8047a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ListAdapter f8048b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public K f8049c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f8050d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8051e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f8052f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f8053g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f8054h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f8055i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f8056j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f8057k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f8058l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f8059m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f8060n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public View f8061o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f8062p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public DataSetObserver f8063q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public View f8064r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Drawable f8065s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public AdapterView.OnItemClickListener f8066t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public AdapterView.OnItemSelectedListener f8067u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final g f8068v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final f f8069w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final e f8070x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final c f8071y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public Runnable f8072z;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            View viewG = M.this.g();
            if (viewG == null || viewG.getWindowToken() == null) {
                return;
            }
            M.this.c();
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            M.this.e();
        }
    }

    public class d extends DataSetObserver {
        public d() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (M.this.h()) {
                M.this.c();
            }
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            M.this.dismiss();
        }
    }

    public class f implements View.OnTouchListener {
        public f() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            PopupWindow popupWindow;
            int action = motionEvent.getAction();
            int x2 = (int) motionEvent.getX();
            int y2 = (int) motionEvent.getY();
            if (action == 0 && (popupWindow = M.this.f8046E) != null && popupWindow.isShowing() && x2 >= 0 && x2 < M.this.f8046E.getWidth() && y2 >= 0 && y2 < M.this.f8046E.getHeight()) {
                M m2 = M.this;
                m2.f8042A.postDelayed(m2.f8068v, 250L);
                return false;
            }
            if (action != 1) {
                return false;
            }
            M m3 = M.this;
            m3.f8042A.removeCallbacks(m3.f8068v);
            return false;
        }
    }

    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            K k2 = M.this.f8049c;
            if (k2 == null || !AbstractC0285k0.y(k2) || M.this.f8049c.getCount() <= M.this.f8049c.getChildCount()) {
                return;
            }
            int childCount = M.this.f8049c.getChildCount();
            M m2 = M.this;
            if (childCount <= m2.f8060n) {
                m2.f8046E.setInputMethodMode(2);
                M.this.c();
            }
        }
    }

    static {
        try {
            f8039F = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
        } catch (NoSuchMethodException unused) {
            Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
        }
        try {
            f8040G = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
        } catch (NoSuchMethodException unused2) {
            Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
        }
        try {
            f8041H = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
        } catch (NoSuchMethodException unused3) {
            Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
        }
    }

    public M(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public void A(boolean z2) {
        this.f8045D = z2;
        this.f8046E.setFocusable(z2);
    }

    public void B(PopupWindow.OnDismissListener onDismissListener) {
        this.f8046E.setOnDismissListener(onDismissListener);
    }

    public void C(AdapterView.OnItemClickListener onItemClickListener) {
        this.f8066t = onItemClickListener;
    }

    public final void D(boolean z2) {
        Method method = f8039F;
        if (method != null) {
            try {
                method.invoke(this.f8046E, Boolean.valueOf(z2));
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    public void E(int i2) {
        this.f8062p = i2;
    }

    public void F(int i2) {
        K k2 = this.f8049c;
        if (!h() || k2 == null) {
            return;
        }
        k2.setListSelectionHidden(false);
        k2.setSelection(i2);
        if (Build.VERSION.SDK_INT < 11 || k2.getChoiceMode() == 0) {
            return;
        }
        k2.setItemChecked(i2, true);
    }

    public void G(int i2) {
        this.f8053g = i2;
        this.f8055i = true;
    }

    public void H(int i2) {
        this.f8051e = i2;
    }

    @Override // w.z
    public void c() {
        int iD = d();
        boolean zO = o();
        AbstractC0414B.b(this.f8046E, this.f8054h);
        if (this.f8046E.isShowing()) {
            int width = this.f8051e;
            if (width == -1) {
                width = -1;
            } else if (width == -2) {
                width = g().getWidth();
            }
            int i2 = this.f8050d;
            if (i2 == -1) {
                if (!zO) {
                    iD = -1;
                }
                if (zO) {
                    this.f8046E.setWidth(this.f8051e == -1 ? -1 : 0);
                    this.f8046E.setHeight(0);
                } else {
                    this.f8046E.setWidth(this.f8051e == -1 ? -1 : 0);
                    this.f8046E.setHeight(-1);
                }
            } else if (i2 != -2) {
                iD = i2;
            }
            this.f8046E.setOutsideTouchable((this.f8059m || this.f8058l) ? false : true);
            this.f8046E.update(g(), this.f8052f, this.f8053g, width < 0 ? -1 : width, iD < 0 ? -1 : iD);
            return;
        }
        int width2 = this.f8051e;
        if (width2 == -1) {
            width2 = -1;
        } else if (width2 == -2) {
            width2 = g().getWidth();
        }
        int i3 = this.f8050d;
        if (i3 == -1) {
            iD = -1;
        } else if (i3 != -2) {
            iD = i3;
        }
        this.f8046E.setWidth(width2);
        this.f8046E.setHeight(iD);
        D(true);
        this.f8046E.setOutsideTouchable((this.f8059m || this.f8058l) ? false : true);
        this.f8046E.setTouchInterceptor(this.f8069w);
        Method method = f8041H;
        if (method != null) {
            try {
                method.invoke(this.f8046E, this.f8044C);
            } catch (Exception e2) {
                Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e2);
            }
        }
        AbstractC0414B.c(this.f8046E, g(), this.f8052f, this.f8053g, this.f8057k);
        this.f8049c.setSelection(-1);
        if (!this.f8045D || this.f8049c.isInTouchMode()) {
            e();
        }
        if (this.f8045D) {
            return;
        }
        this.f8042A.post(this.f8071y);
    }

    public final int d() {
        int measuredHeight;
        int i2;
        int iMakeMeasureSpec;
        View view;
        int i3;
        if (this.f8049c == null) {
            Context context = this.f8047a;
            this.f8072z = new a();
            K kF = f(context, !this.f8045D);
            this.f8049c = kF;
            Drawable drawable = this.f8065s;
            if (drawable != null) {
                kF.setSelector(drawable);
            }
            this.f8049c.setAdapter(this.f8048b);
            this.f8049c.setOnItemClickListener(this.f8066t);
            this.f8049c.setFocusable(true);
            this.f8049c.setFocusableInTouchMode(true);
            this.f8049c.setOnItemSelectedListener(new b());
            this.f8049c.setOnScrollListener(this.f8070x);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.f8067u;
            if (onItemSelectedListener != null) {
                this.f8049c.setOnItemSelectedListener(onItemSelectedListener);
            }
            K k2 = this.f8049c;
            View view2 = this.f8061o;
            if (view2 != null) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
                int i4 = this.f8062p;
                if (i4 == 0) {
                    linearLayout.addView(view2);
                    linearLayout.addView(k2, layoutParams);
                } else if (i4 != 1) {
                    Log.e("ListPopupWindow", "Invalid hint position " + this.f8062p);
                } else {
                    linearLayout.addView(k2, layoutParams);
                    linearLayout.addView(view2);
                }
                int i5 = this.f8051e;
                if (i5 >= 0) {
                    i3 = PKIFailureInfo.systemUnavail;
                } else {
                    i5 = 0;
                    i3 = 0;
                }
                view2.measure(View.MeasureSpec.makeMeasureSpec(i5, i3), 0);
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) view2.getLayoutParams();
                measuredHeight = view2.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin;
                view = linearLayout;
            } else {
                measuredHeight = 0;
                view = k2;
            }
            this.f8046E.setContentView(view);
        } else {
            View view3 = this.f8061o;
            if (view3 != null) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) view3.getLayoutParams();
                measuredHeight = view3.getMeasuredHeight() + layoutParams3.topMargin + layoutParams3.bottomMargin;
            } else {
                measuredHeight = 0;
            }
        }
        Drawable background = this.f8046E.getBackground();
        if (background != null) {
            background.getPadding(this.f8043B);
            Rect rect = this.f8043B;
            int i6 = rect.top;
            i2 = rect.bottom + i6;
            if (!this.f8055i) {
                this.f8053g = -i6;
            }
        } else {
            this.f8043B.setEmpty();
            i2 = 0;
        }
        int iL = l(g(), this.f8053g, this.f8046E.getInputMethodMode() == 2);
        if (this.f8058l || this.f8050d == -1) {
            return iL + i2;
        }
        int i7 = this.f8051e;
        if (i7 == -2) {
            int i8 = this.f8047a.getResources().getDisplayMetrics().widthPixels;
            Rect rect2 = this.f8043B;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i8 - (rect2.left + rect2.right), PKIFailureInfo.systemUnavail);
        } else if (i7 != -1) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i7, 1073741824);
        } else {
            int i9 = this.f8047a.getResources().getDisplayMetrics().widthPixels;
            Rect rect3 = this.f8043B;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i9 - (rect3.left + rect3.right), 1073741824);
        }
        int iB = this.f8049c.b(iMakeMeasureSpec, 0, -1, iL - measuredHeight, -1);
        if (iB > 0) {
            measuredHeight += i2 + this.f8049c.getPaddingTop() + this.f8049c.getPaddingBottom();
        }
        return iB + measuredHeight;
    }

    @Override // w.z
    public void dismiss() {
        this.f8046E.dismiss();
        q();
        this.f8046E.setContentView(null);
        this.f8049c = null;
        this.f8042A.removeCallbacks(this.f8068v);
    }

    public void e() {
        K k2 = this.f8049c;
        if (k2 != null) {
            k2.setListSelectionHidden(true);
            k2.requestLayout();
        }
    }

    public K f(Context context, boolean z2) {
        return new K(context, z2);
    }

    public View g() {
        return this.f8064r;
    }

    @Override // w.z
    public boolean h() {
        return this.f8046E.isShowing();
    }

    @Override // w.z
    public ListView i() {
        return this.f8049c;
    }

    public Drawable j() {
        return this.f8046E.getBackground();
    }

    public int k() {
        return this.f8052f;
    }

    public final int l(View view, int i2, boolean z2) {
        Method method = f8040G;
        if (method != null) {
            try {
                return ((Integer) method.invoke(this.f8046E, view, Integer.valueOf(i2), Boolean.valueOf(z2))).intValue();
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
            }
        }
        return this.f8046E.getMaxAvailableHeight(view, i2);
    }

    public int m() {
        if (this.f8055i) {
            return this.f8053g;
        }
        return 0;
    }

    public int n() {
        return this.f8051e;
    }

    public boolean o() {
        return this.f8046E.getInputMethodMode() == 2;
    }

    public boolean p() {
        return this.f8045D;
    }

    public final void q() {
        View view = this.f8061o;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.f8061o);
            }
        }
    }

    public void r(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.f8063q;
        if (dataSetObserver == null) {
            this.f8063q = new d();
        } else {
            ListAdapter listAdapter2 = this.f8048b;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f8048b = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.f8063q);
        }
        K k2 = this.f8049c;
        if (k2 != null) {
            k2.setAdapter(this.f8048b);
        }
    }

    public void s(View view) {
        this.f8064r = view;
    }

    public void t(int i2) {
        this.f8046E.setAnimationStyle(i2);
    }

    public void u(Drawable drawable) {
        this.f8046E.setBackgroundDrawable(drawable);
    }

    public void v(int i2) {
        Drawable background = this.f8046E.getBackground();
        if (background == null) {
            H(i2);
            return;
        }
        background.getPadding(this.f8043B);
        Rect rect = this.f8043B;
        this.f8051e = rect.left + rect.right + i2;
    }

    public void w(int i2) {
        this.f8057k = i2;
    }

    public void x(Rect rect) {
        this.f8044C = rect;
    }

    public void y(int i2) {
        this.f8052f = i2;
    }

    public void z(int i2) {
        this.f8046E.setInputMethodMode(i2);
    }

    public M(Context context, AttributeSet attributeSet, int i2, int i3) {
        this.f8050d = -2;
        this.f8051e = -2;
        this.f8054h = 1002;
        this.f8056j = true;
        this.f8057k = 0;
        this.f8058l = false;
        this.f8059m = false;
        this.f8060n = Integer.MAX_VALUE;
        this.f8062p = 0;
        this.f8068v = new g();
        this.f8069w = new f();
        this.f8070x = new e();
        this.f8071y = new c();
        this.f8043B = new Rect();
        this.f8047a = context;
        this.f8042A = new Handler(context.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, p.j.O0, i2, i3);
        this.f8052f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(p.j.P0, 0);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(p.j.Q0, 0);
        this.f8053g = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.f8055i = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        if (Build.VERSION.SDK_INT >= 11) {
            this.f8046E = new C0514q(context, attributeSet, i2, i3);
        } else {
            this.f8046E = new C0514q(context, attributeSet, i2);
        }
        this.f8046E.setInputMethodMode(1);
    }

    public class b implements AdapterView.OnItemSelectedListener {
        public b() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView adapterView, View view, int i2, long j2) {
            K k2;
            if (i2 == -1 || (k2 = M.this.f8049c) == null) {
                return;
            }
            k2.setListSelectionHidden(false);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView adapterView) {
        }
    }

    public class e implements AbsListView.OnScrollListener {
        public e() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            if (i2 != 1 || M.this.o() || M.this.f8046E.getContentView() == null) {
                return;
            }
            M m2 = M.this;
            m2.f8042A.removeCallbacks(m2.f8068v);
            M.this.f8068v.run();
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
        }
    }
}

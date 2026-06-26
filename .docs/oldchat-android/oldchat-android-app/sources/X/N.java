package x;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import g.AbstractC0244a;
import java.lang.reflect.Field;
import r.AbstractC0466a;

/* JADX INFO: loaded from: classes.dex */
public abstract class N extends ListView {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final int[] f8080i = {0};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Rect f8081a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f8082b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f8083c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f8084d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8085e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f8086f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Field f8087g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public a f8088h;

    public static class a extends AbstractC0466a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f8089b;

        public a(Drawable drawable) {
            super(drawable);
            this.f8089b = true;
        }

        public void c(boolean z2) {
            this.f8089b = z2;
        }

        @Override // r.AbstractC0466a, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            if (this.f8089b) {
                super.draw(canvas);
            }
        }

        @Override // r.AbstractC0466a, android.graphics.drawable.Drawable
        public void setHotspot(float f2, float f3) {
            if (this.f8089b) {
                super.setHotspot(f2, f3);
            }
        }

        @Override // r.AbstractC0466a, android.graphics.drawable.Drawable
        public void setHotspotBounds(int i2, int i3, int i4, int i5) {
            if (this.f8089b) {
                super.setHotspotBounds(i2, i3, i4, i5);
            }
        }

        @Override // r.AbstractC0466a, android.graphics.drawable.Drawable
        public boolean setState(int[] iArr) {
            if (this.f8089b) {
                return super.setState(iArr);
            }
            return false;
        }

        @Override // r.AbstractC0466a, android.graphics.drawable.Drawable
        public boolean setVisible(boolean z2, boolean z3) {
            if (this.f8089b) {
                return super.setVisible(z2, z3);
            }
            return false;
        }
    }

    public N(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f8081a = new Rect();
        this.f8082b = 0;
        this.f8083c = 0;
        this.f8084d = 0;
        this.f8085e = 0;
        try {
            Field declaredField = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.f8087g = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }

    public void a(Canvas canvas) {
        Drawable selector;
        if (this.f8081a.isEmpty() || (selector = getSelector()) == null) {
            return;
        }
        selector.setBounds(this.f8081a);
        selector.draw(canvas);
    }

    public int b(int i2, int i3, int i4, int i5, int i6) {
        int listPaddingTop = getListPaddingTop();
        int listPaddingBottom = getListPaddingBottom();
        getListPaddingLeft();
        getListPaddingRight();
        int dividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        if (adapter == null) {
            return listPaddingTop + listPaddingBottom;
        }
        int measuredHeight = listPaddingTop + listPaddingBottom;
        if (dividerHeight <= 0 || divider == null) {
            dividerHeight = 0;
        }
        int count = adapter.getCount();
        View view = null;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i7 < count) {
            int itemViewType = adapter.getItemViewType(i7);
            if (itemViewType != i8) {
                view = null;
                i8 = itemViewType;
            }
            view = adapter.getView(i7, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            int i10 = layoutParams.height;
            view.measure(i2, i10 > 0 ? View.MeasureSpec.makeMeasureSpec(i10, 1073741824) : View.MeasureSpec.makeMeasureSpec(0, 0));
            view.forceLayout();
            if (i7 > 0) {
                measuredHeight += dividerHeight;
            }
            measuredHeight += view.getMeasuredHeight();
            if (measuredHeight >= i5) {
                return (i6 < 0 || i7 <= i6 || i9 <= 0 || measuredHeight == i5) ? i5 : i9;
            }
            if (i6 >= 0 && i7 >= i6) {
                i9 = measuredHeight;
            }
            i7++;
        }
        return measuredHeight;
    }

    public void c(int i2, View view) {
        Rect rect = this.f8081a;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        rect.left -= this.f8082b;
        rect.top -= this.f8083c;
        rect.right += this.f8084d;
        rect.bottom += this.f8085e;
        try {
            boolean z2 = this.f8087g.getBoolean(this);
            if (view.isEnabled() != z2) {
                this.f8087g.set(this, Boolean.valueOf(!z2));
                if (i2 != -1) {
                    refreshDrawableState();
                }
            }
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }

    public void d(int i2, View view) {
        Drawable selector = getSelector();
        boolean z2 = (selector == null || i2 == -1) ? false : true;
        if (z2) {
            selector.setVisible(false, false);
        }
        c(i2, view);
        if (z2) {
            Rect rect = this.f8081a;
            float fExactCenterX = rect.exactCenterX();
            float fExactCenterY = rect.exactCenterY();
            selector.setVisible(getVisibility() == 0, false);
            AbstractC0244a.j(selector, fExactCenterX, fExactCenterY);
        }
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        a(canvas);
        super.dispatchDraw(canvas);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        setSelectorEnabled(true);
        h();
    }

    public void e(int i2, View view, float f2, float f3) {
        d(i2, view);
        Drawable selector = getSelector();
        if (selector == null || i2 == -1) {
            return;
        }
        AbstractC0244a.j(selector, f2, f3);
    }

    public boolean f() {
        return g() && isPressed();
    }

    public boolean g() {
        return false;
    }

    public void h() {
        Drawable selector = getSelector();
        if (selector == null || !f()) {
            return;
        }
        selector.setState(getDrawableState());
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f8086f = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.AbsListView
    public void setSelector(Drawable drawable) {
        a aVar = drawable != null ? new a(drawable) : null;
        this.f8088h = aVar;
        super.setSelector(aVar);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.f8082b = rect.left;
        this.f8083c = rect.top;
        this.f8084d = rect.right;
        this.f8085e = rect.bottom;
    }

    public void setSelectorEnabled(boolean z2) {
        a aVar = this.f8088h;
        if (aVar != null) {
            aVar.c(z2);
        }
    }
}

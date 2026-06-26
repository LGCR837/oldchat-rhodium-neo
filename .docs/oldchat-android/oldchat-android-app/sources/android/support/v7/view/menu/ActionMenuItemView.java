package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.view.menu.e;
import android.support.v7.view.menu.j;
import android.support.v7.widget.ActionMenuView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import e.AbstractC0236a;
import k.AbstractC0285k0;
import w.z;
import x.E;
import x.L;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuItemView extends E implements j.a, View.OnClickListener, View.OnLongClickListener, ActionMenuView.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g f961c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public CharSequence f962d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f963e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public e.b f964f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public L f965g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public b f966h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f967i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f968j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f969k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f970l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f971m;

    public class a extends L {
        public a() {
            super(ActionMenuItemView.this);
        }

        @Override // x.L
        public z b() {
            b bVar = ActionMenuItemView.this.f966h;
            if (bVar != null) {
                return bVar.a();
            }
            return null;
        }

        @Override // x.L
        public boolean c() {
            z zVarB;
            ActionMenuItemView actionMenuItemView = ActionMenuItemView.this;
            e.b bVar = actionMenuItemView.f964f;
            return bVar != null && bVar.a(actionMenuItemView.f961c) && (zVarB = b()) != null && zVarB.h();
        }
    }

    public static abstract class b {
        public abstract z a();
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.support.v7.widget.ActionMenuView.a
    public boolean a() {
        return e();
    }

    @Override // android.support.v7.view.menu.j.a
    public void b(g gVar, int i2) {
        this.f961c = gVar;
        setIcon(gVar.getIcon());
        setTitle(gVar.h(this));
        setId(gVar.getItemId());
        setVisibility(gVar.isVisible() ? 0 : 8);
        setEnabled(gVar.isEnabled());
        if (gVar.hasSubMenu() && this.f965g == null) {
            this.f965g = new a();
        }
    }

    @Override // android.support.v7.widget.ActionMenuView.a
    public boolean c() {
        return e() && this.f961c.getIcon() == null;
    }

    @Override // android.support.v7.view.menu.j.a
    public boolean d() {
        return true;
    }

    public boolean e() {
        return !TextUtils.isEmpty(getText());
    }

    public final boolean f() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int iB = AbstractC0236a.b(getResources());
        return iB >= 480 || (iB >= 640 && AbstractC0236a.a(getResources()) >= 480) || configuration.orientation == 2;
    }

    public final void g() {
        boolean z2 = true;
        boolean z3 = !TextUtils.isEmpty(this.f962d);
        if (this.f963e != null && (!this.f961c.A() || (!this.f967i && !this.f968j))) {
            z2 = false;
        }
        setText(z3 & z2 ? this.f962d : null);
    }

    @Override // android.support.v7.view.menu.j.a
    public g getItemData() {
        return this.f961c;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        e.b bVar = this.f964f;
        if (bVar != null) {
            bVar.a(this.f961c);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f967i = f();
        g();
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        if (e()) {
            return false;
        }
        int[] iArr = new int[2];
        Rect rect = new Rect();
        getLocationOnScreen(iArr);
        getWindowVisibleDisplayFrame(rect);
        Context context = getContext();
        int width = getWidth();
        int height = getHeight();
        int i2 = iArr[1] + (height / 2);
        int i3 = iArr[0] + (width / 2);
        if (AbstractC0285k0.k(view) == 0) {
            i3 = context.getResources().getDisplayMetrics().widthPixels - i3;
        }
        Toast toastMakeText = Toast.makeText(context, this.f961c.getTitle(), 0);
        if (i2 < rect.height()) {
            toastMakeText.setGravity(8388661, i3, (iArr[1] + height) - rect.top);
        } else {
            toastMakeText.setGravity(81, 0, height);
        }
        toastMakeText.show();
        return true;
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i2, int i3) {
        int i4;
        boolean zE = e();
        if (zE && (i4 = this.f970l) >= 0) {
            super.setPadding(i4, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i2, i3);
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int measuredWidth = getMeasuredWidth();
        int iMin = mode == Integer.MIN_VALUE ? Math.min(size, this.f969k) : this.f969k;
        if (mode != 1073741824 && this.f969k > 0 && measuredWidth < iMin) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(iMin, 1073741824), i3);
        }
        if (zE || this.f963e == null) {
            return;
        }
        super.setPadding((getMeasuredWidth() - this.f963e.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        L l2;
        if (this.f961c.hasSubMenu() && (l2 = this.f965g) != null && l2.onTouch(this, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setCheckable(boolean z2) {
    }

    public void setChecked(boolean z2) {
    }

    public void setExpandedFormat(boolean z2) {
        if (this.f968j != z2) {
            this.f968j = z2;
            g gVar = this.f961c;
            if (gVar != null) {
                gVar.c();
            }
        }
    }

    public void setIcon(Drawable drawable) {
        this.f963e = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i2 = this.f971m;
            if (intrinsicWidth > i2) {
                intrinsicHeight = (int) (intrinsicHeight * (i2 / intrinsicWidth));
                intrinsicWidth = i2;
            }
            if (intrinsicHeight > i2) {
                intrinsicWidth = (int) (intrinsicWidth * (i2 / intrinsicHeight));
            } else {
                i2 = intrinsicHeight;
            }
            drawable.setBounds(0, 0, intrinsicWidth, i2);
        }
        setCompoundDrawables(drawable, null, null, null);
        g();
    }

    public void setItemInvoker(e.b bVar) {
        this.f964f = bVar;
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i2, int i3, int i4, int i5) {
        this.f970l = i2;
        super.setPadding(i2, i3, i4, i5);
    }

    public void setPopupCallback(b bVar) {
        this.f966h = bVar;
    }

    public void setTitle(CharSequence charSequence) {
        this.f962d = charSequence;
        setContentDescription(charSequence);
        g();
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        Resources resources = context.getResources();
        this.f967i = f();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, p.j.f7898v, i2, 0);
        this.f969k = typedArrayObtainStyledAttributes.getDimensionPixelSize(p.j.f7899w, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.f971m = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        setOnLongClickListener(this);
        this.f970l = -1;
        setSaveEnabled(false);
    }
}

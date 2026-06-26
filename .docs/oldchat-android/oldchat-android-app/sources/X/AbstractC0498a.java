package x;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v7.widget.ActionMenuView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import k.AbstractC0285k0;
import k.F1;
import k.T1;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import p.AbstractC0460a;

/* JADX INFO: renamed from: x.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0498a extends ViewGroup {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0070a f8112a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f8113b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ActionMenuView f8114c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public android.support.v7.widget.a f8115d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8116e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public F1 f8117f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f8118g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f8119h;

    /* JADX INFO: renamed from: x.a$a, reason: collision with other inner class name */
    public class C0070a implements T1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f8120a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f8121b;

        public C0070a() {
        }

        @Override // k.T1
        public void a(View view) {
            if (this.f8120a) {
                return;
            }
            AbstractC0498a abstractC0498a = AbstractC0498a.this;
            abstractC0498a.f8117f = null;
            AbstractC0498a.super.setVisibility(this.f8121b);
        }

        @Override // k.T1
        public void b(View view) {
            AbstractC0498a.super.setVisibility(0);
            this.f8120a = false;
        }

        @Override // k.T1
        public void c(View view) {
            this.f8120a = true;
        }

        public C0070a d(F1 f1, int i2) {
            AbstractC0498a.this.f8117f = f1;
            this.f8121b = i2;
            return this;
        }
    }

    public AbstractC0498a(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f8112a = new C0070a();
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(AbstractC0460a.f7707a, typedValue, true) || typedValue.resourceId == 0) {
            this.f8113b = context;
        } else {
            this.f8113b = new ContextThemeWrapper(context, typedValue.resourceId);
        }
    }

    public static int d(int i2, int i3, boolean z2) {
        return z2 ? i2 - i3 : i2 + i3;
    }

    public int c(View view, int i2, int i3, int i4) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i2, PKIFailureInfo.systemUnavail), i3);
        return Math.max(0, (i2 - view.getMeasuredWidth()) - i4);
    }

    public int e(View view, int i2, int i3, int i4, boolean z2) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i5 = i3 + ((i4 - measuredHeight) / 2);
        if (z2) {
            view.layout(i2 - measuredWidth, i5, i2, measuredHeight + i5);
        } else {
            view.layout(i2, i5, i2 + measuredWidth, measuredHeight + i5);
        }
        return z2 ? -measuredWidth : measuredWidth;
    }

    public F1 f(int i2, long j2) {
        F1 f1 = this.f8117f;
        if (f1 != null) {
            f1.b();
        }
        if (i2 != 0) {
            F1 f1A = AbstractC0285k0.a(this).a(0.0f);
            f1A.d(j2);
            f1A.f(this.f8112a.d(f1A, i2));
            return f1A;
        }
        if (getVisibility() != 0) {
            AbstractC0285k0.K(this, 0.0f);
        }
        F1 f1A2 = AbstractC0285k0.a(this).a(1.0f);
        f1A2.d(j2);
        f1A2.f(this.f8112a.d(f1A2, i2));
        return f1A2;
    }

    public int getAnimatedVisibility() {
        return this.f8117f != null ? this.f8112a.f8121b : getVisibility();
    }

    public int getContentHeight() {
        return this.f8116e;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, p.j.f7862a, AbstractC0460a.f7709c, 0);
        setContentHeight(typedArrayObtainStyledAttributes.getLayoutDimension(p.j.f7880j, 0));
        typedArrayObtainStyledAttributes.recycle();
        android.support.v7.widget.a aVar = this.f8115d;
        if (aVar != null) {
            aVar.E(configuration);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int iB = k.W.b(motionEvent);
        if (iB == 9) {
            this.f8119h = false;
        }
        if (!this.f8119h) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (iB == 9 && !zOnHoverEvent) {
                this.f8119h = true;
            }
        }
        if (iB == 10 || iB == 3) {
            this.f8119h = false;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int iB = k.W.b(motionEvent);
        if (iB == 0) {
            this.f8118g = false;
        }
        if (!this.f8118g) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (iB == 0 && !zOnTouchEvent) {
                this.f8118g = true;
            }
        }
        if (iB == 1 || iB == 3) {
            this.f8118g = false;
        }
        return true;
    }

    public abstract void setContentHeight(int i2);

    @Override // android.view.View
    public void setVisibility(int i2) {
        if (i2 != getVisibility()) {
            F1 f1 = this.f8117f;
            if (f1 != null) {
                f1.b();
            }
            super.setVisibility(i2);
        }
    }
}

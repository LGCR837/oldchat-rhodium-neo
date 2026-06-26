package o;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.ViewGroup;
import v.AbstractC0473b;

/* JADX INFO: renamed from: o.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0430a {
    public abstract boolean g();

    public abstract void h(boolean z2);

    public abstract int i();

    public abstract Context j();

    public boolean k() {
        return false;
    }

    public abstract boolean l();

    public abstract void m(Configuration configuration);

    public boolean o(int i2, KeyEvent keyEvent) {
        return false;
    }

    public abstract boolean p();

    public abstract void q(boolean z2);

    public abstract void r(boolean z2);

    public abstract void s(CharSequence charSequence);

    public abstract AbstractC0473b t(AbstractC0473b.a aVar);

    /* JADX INFO: renamed from: o.a$a, reason: collision with other inner class name */
    public static class C0066a extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f6720a;

        public C0066a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f6720a = 0;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, p.j.f7896t);
            this.f6720a = typedArrayObtainStyledAttributes.getInt(p.j.f7897u, 0);
            typedArrayObtainStyledAttributes.recycle();
        }

        public C0066a(int i2, int i3) {
            super(i2, i3);
            this.f6720a = 8388627;
        }

        public C0066a(C0066a c0066a) {
            super((ViewGroup.MarginLayoutParams) c0066a);
            this.f6720a = 0;
            this.f6720a = c0066a.f6720a;
        }

        public C0066a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f6720a = 0;
        }
    }

    public void n() {
    }
}

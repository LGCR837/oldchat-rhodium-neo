package k;

import android.view.View;
import android.view.ViewParent;

/* JADX INFO: renamed from: k.a0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0255a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f5688a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ViewParent f5689b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f5690c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int[] f5691d;

    public C0255a0(View view) {
        this.f5688a = view;
    }

    public boolean a(float f2, float f3, boolean z2) {
        ViewParent viewParent;
        if (!f() || (viewParent = this.f5689b) == null) {
            return false;
        }
        return AbstractC0321w1.a(viewParent, this.f5688a, f2, f3, z2);
    }

    public boolean b(float f2, float f3) {
        ViewParent viewParent;
        if (!f() || (viewParent = this.f5689b) == null) {
            return false;
        }
        return AbstractC0321w1.b(viewParent, this.f5688a, f2, f3);
    }

    public boolean c(int i2, int i3, int[] iArr, int[] iArr2) {
        int i4;
        int i5;
        if (!f() || this.f5689b == null) {
            return false;
        }
        if (i2 == 0 && i3 == 0) {
            if (iArr2 == null) {
                return false;
            }
            iArr2[0] = 0;
            iArr2[1] = 0;
            return false;
        }
        if (iArr2 != null) {
            this.f5688a.getLocationInWindow(iArr2);
            i4 = iArr2[0];
            i5 = iArr2[1];
        } else {
            i4 = 0;
            i5 = 0;
        }
        if (iArr == null) {
            if (this.f5691d == null) {
                this.f5691d = new int[2];
            }
            iArr = this.f5691d;
        }
        iArr[0] = 0;
        iArr[1] = 0;
        AbstractC0321w1.c(this.f5689b, this.f5688a, i2, i3, iArr);
        if (iArr2 != null) {
            this.f5688a.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i4;
            iArr2[1] = iArr2[1] - i5;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    public boolean d(int i2, int i3, int i4, int i5, int[] iArr) {
        int i6;
        int i7;
        if (f() && this.f5689b != null) {
            if (i2 != 0 || i3 != 0 || i4 != 0 || i5 != 0) {
                if (iArr != null) {
                    this.f5688a.getLocationInWindow(iArr);
                    i6 = iArr[0];
                    i7 = iArr[1];
                } else {
                    i6 = 0;
                    i7 = 0;
                }
                AbstractC0321w1.d(this.f5689b, this.f5688a, i2, i3, i4, i5);
                if (iArr != null) {
                    this.f5688a.getLocationInWindow(iArr);
                    iArr[0] = iArr[0] - i6;
                    iArr[1] = iArr[1] - i7;
                }
                return true;
            }
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
        }
        return false;
    }

    public boolean e() {
        return this.f5689b != null;
    }

    public boolean f() {
        return this.f5690c;
    }

    public void g(boolean z2) {
        if (this.f5690c) {
            AbstractC0285k0.Y(this.f5688a);
        }
        this.f5690c = z2;
    }

    public boolean h(int i2) {
        if (e()) {
            return true;
        }
        if (!f()) {
            return false;
        }
        View view = this.f5688a;
        for (ViewParent parent = this.f5688a.getParent(); parent != null; parent = parent.getParent()) {
            if (AbstractC0321w1.f(parent, view, this.f5688a, i2)) {
                this.f5689b = parent;
                AbstractC0321w1.e(parent, view, this.f5688a, i2);
                return true;
            }
            if (parent instanceof View) {
                view = (View) parent;
            }
        }
        return false;
    }

    public void i() {
        ViewParent viewParent = this.f5689b;
        if (viewParent != null) {
            AbstractC0321w1.g(viewParent, this.f5688a);
            this.f5689b = null;
        }
    }
}

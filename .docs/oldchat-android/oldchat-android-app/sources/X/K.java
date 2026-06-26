package x;

import android.content.Context;
import android.os.Build;
import android.view.View;
import k.F1;
import p.AbstractC0460a;

/* JADX INFO: loaded from: classes.dex */
public class K extends N {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f8023j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f8024k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f8025l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public F1 f8026m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public n.v f8027n;

    public K(Context context, boolean z2) {
        super(context, null, AbstractC0460a.f7730x);
        this.f8024k = z2;
        setCacheColorHint(0);
    }

    @Override // x.N
    public boolean g() {
        return this.f8025l || super.g();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean hasFocus() {
        return this.f8024k || super.hasFocus();
    }

    @Override // android.view.View
    public boolean hasWindowFocus() {
        return this.f8024k || super.hasWindowFocus();
    }

    public final void i() {
        this.f8025l = false;
        setPressed(false);
        drawableStateChanged();
        View childAt = getChildAt(this.f8086f - getFirstVisiblePosition());
        if (childAt != null) {
            childAt.setPressed(false);
        }
        F1 f1 = this.f8026m;
        if (f1 != null) {
            f1.b();
            this.f8026m = null;
        }
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.f8024k || super.isFocused();
    }

    @Override // android.view.View
    public boolean isInTouchMode() {
        return (this.f8024k && this.f8023j) || super.isInTouchMode();
    }

    public final void j(View view, int i2) {
        performItemClick(view, i2, getItemIdAtPosition(i2));
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean k(android.view.MotionEvent r8, int r9) {
        /*
            r7 = this;
            int r0 = k.W.b(r8)
            r1 = 1
            r2 = 0
            if (r0 == r1) goto L16
            r3 = 2
            if (r0 == r3) goto L14
            r9 = 3
            if (r0 == r9) goto L11
        Le:
            r9 = 0
            r3 = 1
            goto L46
        L11:
            r9 = 0
            r3 = 0
            goto L46
        L14:
            r3 = 1
            goto L17
        L16:
            r3 = 0
        L17:
            int r9 = r8.findPointerIndex(r9)
            if (r9 >= 0) goto L1e
            goto L11
        L1e:
            float r4 = r8.getX(r9)
            int r4 = (int) r4
            float r9 = r8.getY(r9)
            int r9 = (int) r9
            int r5 = r7.pointToPosition(r4, r9)
            r6 = -1
            if (r5 != r6) goto L31
            r9 = 1
            goto L46
        L31:
            int r3 = r7.getFirstVisiblePosition()
            int r3 = r5 - r3
            android.view.View r3 = r7.getChildAt(r3)
            float r4 = (float) r4
            float r9 = (float) r9
            r7.l(r3, r5, r4, r9)
            if (r0 != r1) goto Le
            r7.j(r3, r5)
            goto Le
        L46:
            if (r3 == 0) goto L4a
            if (r9 == 0) goto L4d
        L4a:
            r7.i()
        L4d:
            if (r3 == 0) goto L65
            n.v r9 = r7.f8027n
            if (r9 != 0) goto L5a
            n.v r9 = new n.v
            r9.<init>(r7)
            r7.f8027n = r9
        L5a:
            n.v r9 = r7.f8027n
            r9.m(r1)
            n.v r9 = r7.f8027n
            r9.onTouch(r7, r8)
            goto L6c
        L65:
            n.v r8 = r7.f8027n
            if (r8 == 0) goto L6c
            r8.m(r2)
        L6c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: x.K.k(android.view.MotionEvent, int):boolean");
    }

    public final void l(View view, int i2, float f2, float f3) {
        View childAt;
        this.f8025l = true;
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 21) {
            drawableHotspotChanged(f2, f3);
        }
        if (!isPressed()) {
            setPressed(true);
        }
        layoutChildren();
        int i4 = this.f8086f;
        if (i4 != -1 && (childAt = getChildAt(i4 - getFirstVisiblePosition())) != null && childAt != view && childAt.isPressed()) {
            childAt.setPressed(false);
        }
        this.f8086f = i2;
        float left = f2 - view.getLeft();
        float top = f3 - view.getTop();
        if (i3 >= 21) {
            view.drawableHotspotChanged(left, top);
        }
        if (!view.isPressed()) {
            view.setPressed(true);
        }
        e(i2, view, f2, f3);
        setSelectorEnabled(false);
        refreshDrawableState();
    }

    public void setListSelectionHidden(boolean z2) {
        this.f8023j = z2;
    }
}

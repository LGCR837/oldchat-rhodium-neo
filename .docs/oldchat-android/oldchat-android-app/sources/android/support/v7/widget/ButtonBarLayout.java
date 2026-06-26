package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import e.AbstractC0236a;

/* JADX INFO: loaded from: classes.dex */
public class ButtonBarLayout extends LinearLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1220a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1221b;

    public ButtonBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1221b = -1;
        boolean z2 = AbstractC0236a.a(getResources()) >= 320;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, p.j.q0);
        this.f1220a = typedArrayObtainStyledAttributes.getBoolean(p.j.r0, z2);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void setStacked(boolean z2) {
        setOrientation(z2 ? 1 : 0);
        setGravity(z2 ? 5 : 80);
        View viewFindViewById = findViewById(p.f.f7811u);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(z2 ? 8 : 4);
        }
        for (int childCount = getChildCount() - 2; childCount >= 0; childCount--) {
            bringChildToFront(getChildAt(childCount));
        }
    }

    public final boolean a() {
        return getOrientation() == 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0073  */
    @Override // android.widget.LinearLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r8, int r9) {
        /*
            r7 = this;
            int r0 = android.view.View.MeasureSpec.getSize(r8)
            boolean r1 = r7.f1220a
            r2 = 0
            if (r1 == 0) goto L18
            int r1 = r7.f1221b
            if (r0 <= r1) goto L16
            boolean r1 = r7.a()
            if (r1 == 0) goto L16
            r7.setStacked(r2)
        L16:
            r7.f1221b = r0
        L18:
            boolean r1 = r7.a()
            r3 = 1
            if (r1 != 0) goto L2f
            int r1 = android.view.View.MeasureSpec.getMode(r8)
            r4 = 1073741824(0x40000000, float:2.0)
            if (r1 != r4) goto L2f
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r1)
            r4 = 1
            goto L31
        L2f:
            r1 = r8
            r4 = 0
        L31:
            super.onMeasure(r1, r9)
            boolean r1 = r7.f1220a
            if (r1 == 0) goto L73
            boolean r1 = r7.a()
            if (r1 != 0) goto L73
            int r1 = android.os.Build.VERSION.SDK_INT
            r5 = 11
            if (r1 < r5) goto L50
            int r0 = k.AbstractC0285k0.n(r7)
            r1 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r0 = r0 & r1
            r1 = 16777216(0x1000000, float:2.3509887E-38)
            if (r0 != r1) goto L73
            goto L6f
        L50:
            int r1 = r7.getChildCount()
            r5 = 0
        L55:
            if (r2 >= r1) goto L63
            android.view.View r6 = r7.getChildAt(r2)
            int r6 = r6.getMeasuredWidth()
            int r5 = r5 + r6
            int r2 = r2 + 1
            goto L55
        L63:
            int r1 = r7.getPaddingLeft()
            int r5 = r5 + r1
            int r1 = r7.getPaddingRight()
            int r5 = r5 + r1
            if (r5 <= r0) goto L73
        L6f:
            r7.setStacked(r3)
            goto L74
        L73:
            r3 = r4
        L74:
            if (r3 == 0) goto L79
            super.onMeasure(r8, r9)
        L79:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ButtonBarLayout.onMeasure(int, int):void");
    }

    public void setAllowStacking(boolean z2) {
        if (this.f1220a != z2) {
            this.f1220a = z2;
            if (!z2 && getOrientation() == 1) {
                setStacked(false);
            }
            requestLayout();
        }
    }
}

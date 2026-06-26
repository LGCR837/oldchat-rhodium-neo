package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import k.AbstractC0285k0;

/* JADX INFO: loaded from: classes.dex */
public class ContentFrameLayout extends FrameLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TypedValue f1222a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TypedValue f1223b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TypedValue f1224c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TypedValue f1225d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TypedValue f1226e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TypedValue f1227f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Rect f1228g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public a f1229h;

    public interface a {
        void a();

        void onDetachedFromWindow();
    }

    public ContentFrameLayout(Context context) {
        this(context, null);
    }

    public void a(Rect rect) {
        fitSystemWindows(rect);
    }

    public void b(int i2, int i3, int i4, int i5) {
        this.f1228g.set(i2, i3, i4, i5);
        if (AbstractC0285k0.z(this)) {
            requestLayout();
        }
    }

    public TypedValue getFixedHeightMajor() {
        if (this.f1226e == null) {
            this.f1226e = new TypedValue();
        }
        return this.f1226e;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.f1227f == null) {
            this.f1227f = new TypedValue();
        }
        return this.f1227f;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.f1224c == null) {
            this.f1224c = new TypedValue();
        }
        return this.f1224c;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.f1225d == null) {
            this.f1225d = new TypedValue();
        }
        return this.f1225d;
    }

    public TypedValue getMinWidthMajor() {
        if (this.f1222a == null) {
            this.f1222a = new TypedValue();
        }
        return this.f1222a;
    }

    public TypedValue getMinWidthMinor() {
        if (this.f1223b == null) {
            this.f1223b = new TypedValue();
        }
        return this.f1223b;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a aVar = this.f1229h;
        if (aVar != null) {
            aVar.a();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a aVar = this.f1229h;
        if (aVar != null) {
            aVar.onDetachedFromWindow();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00db  */
    @Override // android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r14, int r15) {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ContentFrameLayout.onMeasure(int, int):void");
    }

    public void setAttachListener(a aVar) {
        this.f1229h = aVar;
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f1228g = new Rect();
    }
}

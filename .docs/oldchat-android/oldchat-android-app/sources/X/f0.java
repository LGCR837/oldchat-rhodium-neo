package x;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/* JADX INFO: loaded from: classes.dex */
public class f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f8152a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TypedArray f8153b;

    public f0(Context context, TypedArray typedArray) {
        this.f8152a = context;
        this.f8153b = typedArray;
    }

    public static f0 q(Context context, int i2, int[] iArr) {
        return new f0(context, context.obtainStyledAttributes(i2, iArr));
    }

    public static f0 r(Context context, AttributeSet attributeSet, int[] iArr) {
        return new f0(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static f0 s(Context context, AttributeSet attributeSet, int[] iArr, int i2, int i3) {
        return new f0(context, context.obtainStyledAttributes(attributeSet, iArr, i2, i3));
    }

    public boolean a(int i2, boolean z2) {
        return this.f8153b.getBoolean(i2, z2);
    }

    public int b(int i2, int i3) {
        return this.f8153b.getColor(i2, i3);
    }

    public ColorStateList c(int i2) {
        int resourceId;
        ColorStateList colorStateListC;
        return (!this.f8153b.hasValue(i2) || (resourceId = this.f8153b.getResourceId(i2, 0)) == 0 || (colorStateListC = q.b.c(this.f8152a, resourceId)) == null) ? this.f8153b.getColorStateList(i2) : colorStateListC;
    }

    public int d(int i2, int i3) {
        return this.f8153b.getDimensionPixelOffset(i2, i3);
    }

    public int e(int i2, int i3) {
        return this.f8153b.getDimensionPixelSize(i2, i3);
    }

    public Drawable f(int i2) {
        int resourceId;
        return (!this.f8153b.hasValue(i2) || (resourceId = this.f8153b.getResourceId(i2, 0)) == 0) ? this.f8153b.getDrawable(i2) : q.b.d(this.f8152a, resourceId);
    }

    public Drawable g(int i2) {
        int resourceId;
        if (!this.f8153b.hasValue(i2) || (resourceId = this.f8153b.getResourceId(i2, 0)) == 0) {
            return null;
        }
        return C0508k.m().p(this.f8152a, resourceId, true);
    }

    public float h(int i2, float f2) {
        return this.f8153b.getFloat(i2, f2);
    }

    public int i(int i2, int i3) {
        return this.f8153b.getInt(i2, i3);
    }

    public int j(int i2, int i3) {
        return this.f8153b.getInteger(i2, i3);
    }

    public int k(int i2, int i3) {
        return this.f8153b.getLayoutDimension(i2, i3);
    }

    public int l(int i2, int i3) {
        return this.f8153b.getResourceId(i2, i3);
    }

    public String m(int i2) {
        return this.f8153b.getString(i2);
    }

    public CharSequence n(int i2) {
        return this.f8153b.getText(i2);
    }

    public CharSequence[] o(int i2) {
        return this.f8153b.getTextArray(i2);
    }

    public boolean p(int i2) {
        return this.f8153b.hasValue(i2);
    }

    public void t() {
        this.f8153b.recycle();
    }
}

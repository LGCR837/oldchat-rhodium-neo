package g;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import g.x;

/* JADX INFO: renamed from: g.C, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0242C extends z {

    /* JADX INFO: renamed from: g.C$a */
    public static class a extends x.a {
        public a(x.a aVar, Resources resources) {
            super(aVar, resources);
        }

        @Override // g.x.a, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new C0242C(this, resources);
        }
    }

    public C0242C(Drawable drawable) {
        super(drawable);
    }

    @Override // g.x
    public boolean c() {
        if (Build.VERSION.SDK_INT != 21) {
            return false;
        }
        Drawable drawable = this.f5274f;
        return (drawable instanceof GradientDrawable) || (drawable instanceof DrawableContainer) || (drawable instanceof InsetDrawable);
    }

    @Override // g.z, g.y, g.x
    public x.a d() {
        return new a(this.f5272d, null);
    }

    @Override // android.graphics.drawable.Drawable
    public Rect getDirtyBounds() {
        return this.f5274f.getDirtyBounds();
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        this.f5274f.getOutline(outline);
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float f2, float f3) {
        this.f5274f.setHotspot(f2, f3);
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspotBounds(int i2, int i3, int i4, int i5) {
        this.f5274f.setHotspotBounds(i2, i3, i4, i5);
    }

    @Override // g.x, android.graphics.drawable.Drawable
    public boolean setState(int[] iArr) {
        if (!super.setState(iArr)) {
            return false;
        }
        invalidateSelf();
        return true;
    }

    @Override // g.x, android.graphics.drawable.Drawable, g.InterfaceC0243D
    public void setTint(int i2) {
        if (c()) {
            super.setTint(i2);
        } else {
            this.f5274f.setTint(i2);
        }
    }

    @Override // g.x, android.graphics.drawable.Drawable, g.InterfaceC0243D
    public void setTintList(ColorStateList colorStateList) {
        if (c()) {
            super.setTintList(colorStateList);
        } else {
            this.f5274f.setTintList(colorStateList);
        }
    }

    @Override // g.x, android.graphics.drawable.Drawable, g.InterfaceC0243D
    public void setTintMode(PorterDuff.Mode mode) {
        if (c()) {
            super.setTintMode(mode);
        } else {
            this.f5274f.setTintMode(mode);
        }
    }

    public C0242C(x.a aVar, Resources resources) {
        super(aVar, resources);
    }
}

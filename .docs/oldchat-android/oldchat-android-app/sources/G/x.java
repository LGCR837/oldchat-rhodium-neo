package g;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public class x extends Drawable implements Drawable.Callback, w, InterfaceC0243D {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final PorterDuff.Mode f5268g = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f5269a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public PorterDuff.Mode f5270b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f5271c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a f5272d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f5273e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f5274f;

    public static abstract class a extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f5275a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Drawable.ConstantState f5276b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ColorStateList f5277c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public PorterDuff.Mode f5278d;

        public a(a aVar, Resources resources) {
            this.f5277c = null;
            this.f5278d = x.f5268g;
            if (aVar != null) {
                this.f5275a = aVar.f5275a;
                this.f5276b = aVar.f5276b;
                this.f5277c = aVar.f5277c;
                this.f5278d = aVar.f5278d;
            }
        }

        public boolean a() {
            return this.f5276b != null;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            int i2 = this.f5275a;
            Drawable.ConstantState constantState = this.f5276b;
            return i2 | (constantState != null ? constantState.getChangingConfigurations() : 0);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return newDrawable(null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public abstract Drawable newDrawable(Resources resources);
    }

    public static class b extends a {
        public b(a aVar, Resources resources) {
            super(aVar, resources);
        }

        @Override // g.x.a, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new x(this, resources);
        }
    }

    public x(a aVar, Resources resources) {
        this.f5272d = aVar;
        f(resources);
    }

    @Override // g.w
    public final Drawable a() {
        return this.f5274f;
    }

    @Override // g.w
    public final void b(Drawable drawable) {
        Drawable drawable2 = this.f5274f;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f5274f = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            setVisible(drawable.isVisible(), true);
            setState(drawable.getState());
            setLevel(drawable.getLevel());
            setBounds(drawable.getBounds());
            a aVar = this.f5272d;
            if (aVar != null) {
                aVar.f5276b = drawable.getConstantState();
            }
        }
        invalidateSelf();
    }

    public boolean c() {
        return true;
    }

    public a d() {
        return new b(this.f5272d, null);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.f5274f.draw(canvas);
    }

    public Drawable e(Drawable.ConstantState constantState, Resources resources) {
        return constantState.newDrawable(resources);
    }

    public final void f(Resources resources) {
        Drawable.ConstantState constantState;
        a aVar = this.f5272d;
        if (aVar == null || (constantState = aVar.f5276b) == null) {
            return;
        }
        b(e(constantState, resources));
    }

    public final boolean g(int[] iArr) {
        if (!c()) {
            return false;
        }
        a aVar = this.f5272d;
        ColorStateList colorStateList = aVar.f5277c;
        PorterDuff.Mode mode = aVar.f5278d;
        if (colorStateList == null || mode == null) {
            this.f5271c = false;
            clearColorFilter();
        } else {
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (!this.f5271c || colorForState != this.f5269a || mode != this.f5270b) {
                setColorFilter(colorForState, mode);
                this.f5269a = colorForState;
                this.f5270b = mode;
                this.f5271c = true;
                return true;
            }
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        int changingConfigurations = super.getChangingConfigurations();
        a aVar = this.f5272d;
        return changingConfigurations | (aVar != null ? aVar.getChangingConfigurations() : 0) | this.f5274f.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        a aVar = this.f5272d;
        if (aVar == null || !aVar.a()) {
            return null;
        }
        this.f5272d.f5275a = getChangingConfigurations();
        return this.f5272d;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        return this.f5274f.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f5274f.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f5274f.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return this.f5274f.getMinimumHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return this.f5274f.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f5274f.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        return this.f5274f.getPadding(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public int[] getState() {
        return this.f5274f.getState();
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        return this.f5274f.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        a aVar;
        ColorStateList colorStateList = (!c() || (aVar = this.f5272d) == null) ? null : aVar.f5277c;
        return (colorStateList != null && colorStateList.isStateful()) || this.f5274f.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.f5273e && super.mutate() == this) {
            this.f5272d = d();
            Drawable drawable = this.f5274f;
            if (drawable != null) {
                drawable.mutate();
            }
            a aVar = this.f5272d;
            if (aVar != null) {
                Drawable drawable2 = this.f5274f;
                aVar.f5276b = drawable2 != null ? drawable2.getConstantState() : null;
            }
            this.f5273e = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f5274f;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i2) {
        return this.f5274f.setLevel(i2);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
        scheduleSelf(runnable, j2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f5274f.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setChangingConfigurations(int i2) {
        this.f5274f.setChangingConfigurations(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f5274f.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z2) {
        this.f5274f.setDither(z2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z2) {
        this.f5274f.setFilterBitmap(z2);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setState(int[] iArr) {
        return g(iArr) || this.f5274f.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable, g.InterfaceC0243D
    public void setTint(int i2) {
        setTintList(ColorStateList.valueOf(i2));
    }

    @Override // android.graphics.drawable.Drawable, g.InterfaceC0243D
    public void setTintList(ColorStateList colorStateList) {
        this.f5272d.f5277c = colorStateList;
        g(getState());
    }

    @Override // android.graphics.drawable.Drawable, g.InterfaceC0243D
    public void setTintMode(PorterDuff.Mode mode) {
        this.f5272d.f5278d = mode;
        g(getState());
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z2, boolean z3) {
        return super.setVisible(z2, z3) || this.f5274f.setVisible(z2, z3);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }

    public x(Drawable drawable) {
        this.f5272d = d();
        b(drawable);
    }
}

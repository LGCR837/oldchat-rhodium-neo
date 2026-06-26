package a;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import g.AbstractC0244a;
import j.C0252a;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class s extends y implements Animatable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f739b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f740c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArgbEvaluator f741d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Drawable.Callback f742e;

    public class a implements Drawable.Callback {
        public a() {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
            s.this.invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
            s.this.scheduleSelf(runnable, j2);
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            s.this.unscheduleSelf(runnable);
        }
    }

    public static class b extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f744a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public C0087A f745b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ArrayList f746c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public C0252a f747d;

        public b(Context context, b bVar, Drawable.Callback callback, Resources resources) {
            if (bVar != null) {
                this.f744a = bVar.f744a;
                C0087A c0087a = bVar.f745b;
                if (c0087a != null) {
                    Drawable.ConstantState constantState = c0087a.getConstantState();
                    if (resources != null) {
                        this.f745b = (C0087A) constantState.newDrawable(resources);
                    } else {
                        this.f745b = (C0087A) constantState.newDrawable();
                    }
                    C0087A c0087a2 = (C0087A) this.f745b.mutate();
                    this.f745b = c0087a2;
                    c0087a2.setCallback(callback);
                    this.f745b.setBounds(bVar.f745b.getBounds());
                    this.f745b.i(false);
                }
                ArrayList arrayList = bVar.f746c;
                if (arrayList != null) {
                    int size = arrayList.size();
                    this.f746c = new ArrayList(size);
                    this.f747d = new C0252a(size);
                    for (int i2 = 0; i2 < size; i2++) {
                        Animator animatorA = k.a(bVar.f746c.get(i2));
                        Animator animatorClone = animatorA.clone();
                        String str = (String) bVar.f747d.get(animatorA);
                        animatorClone.setTarget(this.f745b.e(str));
                        this.f746c.add(animatorClone);
                        this.f747d.put(animatorClone, str);
                    }
                }
            }
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f744a;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            throw new IllegalStateException("No constant state support for SDK < 23.");
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            throw new IllegalStateException("No constant state support for SDK < 23.");
        }
    }

    public s() {
        this(null, null, null);
    }

    public static TypedArray a(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        return theme == null ? resources.obtainAttributes(attributeSet, iArr) : theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    public static s b(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        s sVar = new s(context);
        sVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return sVar;
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            AbstractC0244a.a(drawable, theme);
        }
    }

    public final boolean c() {
        ArrayList arrayList = this.f739b.f746c;
        if (arrayList == null) {
            return false;
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (k.a(arrayList.get(i2)).isRunning()) {
                return true;
            }
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            return AbstractC0244a.b(drawable);
        }
        return false;
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    public final void d(String str, Animator animator) {
        animator.setTarget(this.f739b.f745b.e(str));
        if (Build.VERSION.SDK_INT < 21) {
            e(animator);
        }
        b bVar = this.f739b;
        if (bVar.f746c == null) {
            bVar.f746c = new ArrayList();
            this.f739b.f747d = new C0252a();
        }
        this.f739b.f746c.add(animator);
        this.f739b.f747d.put(animator, str);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        this.f739b.f745b.draw(canvas);
        if (c()) {
            invalidateSelf();
        }
    }

    public final void e(Animator animator) {
        ArrayList childAnimations;
        if (AbstractC0091c.a(animator) && (childAnimations = AbstractC0092d.a(animator).getChildAnimations()) != null) {
            for (int i2 = 0; i2 < childAnimations.size(); i2++) {
                e(k.a(childAnimations.get(i2)));
            }
        }
        if (f.a(animator)) {
            ObjectAnimator objectAnimatorA = g.a(animator);
            String propertyName = objectAnimatorA.getPropertyName();
            if ("fillColor".equals(propertyName) || "strokeColor".equals(propertyName)) {
                if (this.f741d == null) {
                    this.f741d = new ArgbEvaluator();
                }
                objectAnimatorA.setEvaluator(this.f741d);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f753a;
        return drawable != null ? AbstractC0244a.c(drawable) : this.f739b.f745b.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f753a;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.f739b.f744a;
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        if (this.f753a != null) {
            return new c(this.f753a.getConstantState());
        }
        return null;
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f753a;
        return drawable != null ? drawable.getIntrinsicHeight() : this.f739b.f745b.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f753a;
        return drawable != null ? drawable.getIntrinsicWidth() : this.f739b.f745b.getIntrinsicWidth();
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getLayoutDirection() {
        return super.getLayoutDirection();
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f753a;
        return drawable != null ? drawable.getOpacity() : this.f739b.f745b.getOpacity();
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            AbstractC0244a.f(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        int eventType = xmlPullParser.getEventType();
        while (eventType != 1) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if ("animated-vector".equals(name)) {
                    TypedArray typedArrayA = a(resources, theme, attributeSet, AbstractC0089a.f737e);
                    int resourceId = typedArrayA.getResourceId(0, 0);
                    if (resourceId != 0) {
                        C0087A c0087aC = C0087A.c(resources, resourceId, theme);
                        c0087aC.i(false);
                        c0087aC.setCallback(this.f742e);
                        C0087A c0087a = this.f739b.f745b;
                        if (c0087a != null) {
                            c0087a.setCallback(null);
                        }
                        this.f739b.f745b = c0087aC;
                    }
                    typedArrayA.recycle();
                } else if ("target".equals(name)) {
                    TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, AbstractC0089a.f738f);
                    String string = typedArrayObtainAttributes.getString(0);
                    int resourceId2 = typedArrayObtainAttributes.getResourceId(1, 0);
                    if (resourceId2 != 0) {
                        Context context = this.f740c;
                        if (context == null) {
                            throw new IllegalStateException("Context can't be null when inflating animators");
                        }
                        d(string, AnimatorInflater.loadAnimator(context, resourceId2));
                    }
                    typedArrayObtainAttributes.recycle();
                } else {
                    continue;
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean isAutoMirrored() {
        return super.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            return AbstractC0090b.a(drawable).isRunning();
        }
        ArrayList arrayList = this.f739b.f746c;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (k.a(arrayList.get(i2)).isRunning()) {
                return true;
            }
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        Drawable drawable = this.f753a;
        return drawable != null ? drawable.isStateful() : this.f739b.f745b.isStateful();
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f753a;
        if (drawable == null) {
            throw new IllegalStateException("Mutate() is not supported for older platform");
        }
        drawable.mutate();
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            drawable.setBounds(rect);
        } else {
            this.f739b.f745b.setBounds(rect);
        }
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public boolean onLevelChange(int i2) {
        Drawable drawable = this.f753a;
        return drawable != null ? drawable.setLevel(i2) : this.f739b.f745b.setLevel(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f753a;
        return drawable != null ? drawable.setState(iArr) : this.f739b.f745b.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            drawable.setAlpha(i2);
        } else {
            this.f739b.f745b.setAlpha(i2);
        }
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setAutoMirrored(boolean z2) {
        super.setAutoMirrored(z2);
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i2) {
        super.setChangingConfigurations(i2);
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(int i2, PorterDuff.Mode mode) {
        super.setColorFilter(i2, mode);
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z2) {
        super.setFilterBitmap(z2);
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f2, float f3) {
        super.setHotspot(f2, f3);
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i2, int i3, int i4, int i5) {
        super.setHotspotBounds(i2, i3, i4, i5);
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable, g.InterfaceC0243D
    public void setTint(int i2) {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            AbstractC0244a.m(drawable, i2);
        } else {
            this.f739b.f745b.setTint(i2);
        }
    }

    @Override // android.graphics.drawable.Drawable, g.InterfaceC0243D
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            AbstractC0244a.n(drawable, colorStateList);
        } else {
            this.f739b.f745b.setTintList(colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable, g.InterfaceC0243D
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            AbstractC0244a.o(drawable, mode);
        } else {
            this.f739b.f745b.setTintMode(mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z2, boolean z3) {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            return drawable.setVisible(z2, z3);
        }
        this.f739b.f745b.setVisible(z2, z3);
        return super.setVisible(z2, z3);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            AbstractC0090b.a(drawable).start();
            return;
        }
        if (c()) {
            return;
        }
        ArrayList arrayList = this.f739b.f746c;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            k.a(arrayList.get(i2)).start();
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            AbstractC0090b.a(drawable).stop();
            return;
        }
        ArrayList arrayList = this.f739b.f746c;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            k.a(arrayList.get(i2)).end();
        }
    }

    public s(Context context) {
        this(context, null, null);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f739b.f745b.setColorFilter(colorFilter);
        }
    }

    public static class c extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Drawable.ConstantState f748a;

        public c(Drawable.ConstantState constantState) {
            this.f748a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f748a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f748a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            s sVar = new s();
            Drawable drawableNewDrawable = this.f748a.newDrawable();
            sVar.f753a = drawableNewDrawable;
            drawableNewDrawable.setCallback(sVar.f742e);
            return sVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            s sVar = new s();
            Drawable drawableNewDrawable = this.f748a.newDrawable(resources);
            sVar.f753a = drawableNewDrawable;
            drawableNewDrawable.setCallback(sVar.f742e);
            return sVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            s sVar = new s();
            Drawable drawableNewDrawable = this.f748a.newDrawable(resources, theme);
            sVar.f753a = drawableNewDrawable;
            drawableNewDrawable.setCallback(sVar.f742e);
            return sVar;
        }
    }

    public s(Context context, b bVar, Resources resources) {
        this.f741d = null;
        a aVar = new a();
        this.f742e = aVar;
        this.f740c = context;
        if (bVar != null) {
            this.f739b = bVar;
        } else {
            this.f739b = new b(context, bVar, aVar, resources);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        inflate(resources, xmlPullParser, attributeSet, null);
    }
}

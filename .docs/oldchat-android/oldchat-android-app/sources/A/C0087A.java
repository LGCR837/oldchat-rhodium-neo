package a;

import a.w;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import g.AbstractC0244a;
import j.C0252a;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: renamed from: a.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0087A extends y {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final PorterDuff.Mode f665k = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public f f666b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public PorterDuffColorFilter f667c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ColorFilter f668d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f669e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f670f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Drawable.ConstantState f671g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float[] f672h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Matrix f673i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Rect f674j;

    /* JADX INFO: renamed from: a.A$a */
    public static class a extends d {
        public a() {
        }

        @Override // a.C0087A.d
        public boolean b() {
            return true;
        }

        public void d(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            if (x.e(xmlPullParser, "pathData")) {
                TypedArray typedArrayA = y.a(resources, theme, attributeSet, AbstractC0089a.f736d);
                e(typedArrayA);
                typedArrayA.recycle();
            }
        }

        public final void e(TypedArray typedArray) {
            String string = typedArray.getString(0);
            if (string != null) {
                this.f702b = string;
            }
            String string2 = typedArray.getString(1);
            if (string2 != null) {
                this.f701a = w.c(string2);
            }
        }

        public a(a aVar) {
            super(aVar);
        }
    }

    /* JADX INFO: renamed from: a.A$f */
    public static class f extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f720a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public e f721b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ColorStateList f722c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public PorterDuff.Mode f723d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f724e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Bitmap f725f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ColorStateList f726g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public PorterDuff.Mode f727h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f728i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public boolean f729j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public boolean f730k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public Paint f731l;

        public f(f fVar) {
            this.f722c = null;
            this.f723d = C0087A.f665k;
            if (fVar != null) {
                this.f720a = fVar.f720a;
                this.f721b = new e(fVar.f721b);
                if (fVar.f721b.f709e != null) {
                    this.f721b.f709e = new Paint(fVar.f721b.f709e);
                }
                if (fVar.f721b.f708d != null) {
                    this.f721b.f708d = new Paint(fVar.f721b.f708d);
                }
                this.f722c = fVar.f722c;
                this.f723d = fVar.f723d;
                this.f724e = fVar.f724e;
            }
        }

        public boolean a(int i2, int i3) {
            return i2 == this.f725f.getWidth() && i3 == this.f725f.getHeight();
        }

        public boolean b() {
            return !this.f730k && this.f726g == this.f722c && this.f727h == this.f723d && this.f729j == this.f724e && this.f728i == this.f721b.k();
        }

        public void c(int i2, int i3) {
            if (this.f725f == null || !a(i2, i3)) {
                this.f725f = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
                this.f730k = true;
            }
        }

        public void d(Canvas canvas, ColorFilter colorFilter, Rect rect) {
            canvas.drawBitmap(this.f725f, (Rect) null, rect, e(colorFilter));
        }

        public Paint e(ColorFilter colorFilter) {
            if (!f() && colorFilter == null) {
                return null;
            }
            if (this.f731l == null) {
                Paint paint = new Paint();
                this.f731l = paint;
                paint.setFilterBitmap(true);
            }
            this.f731l.setAlpha(this.f721b.k());
            this.f731l.setColorFilter(colorFilter);
            return this.f731l;
        }

        public boolean f() {
            return this.f721b.k() < 255;
        }

        public void g() {
            this.f726g = this.f722c;
            this.f727h = this.f723d;
            this.f728i = this.f721b.k();
            this.f729j = this.f724e;
            this.f730k = false;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f720a;
        }

        public void h(int i2, int i3) {
            this.f725f.eraseColor(0);
            this.f721b.f(new Canvas(this.f725f), i2, i3, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new C0087A(this);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new C0087A(this);
        }

        public f() {
            this.f722c = null;
            this.f723d = C0087A.f665k;
            this.f721b = new e();
        }
    }

    public C0087A() {
        this.f670f = true;
        this.f672h = new float[9];
        this.f673i = new Matrix();
        this.f674j = new Rect();
        this.f666b = new f();
    }

    public static int b(int i2, float f2) {
        return (i2 & 16777215) | (((int) (Color.alpha(i2) * f2)) << 24);
    }

    public static C0087A c(Resources resources, int i2, Resources.Theme theme) {
        int next;
        if (Build.VERSION.SDK_INT >= 23) {
            C0087A c0087a = new C0087A();
            c0087a.f753a = e.g.a(resources, i2, theme);
            c0087a.f671g = new g(c0087a.f753a.getConstantState());
            return c0087a;
        }
        try {
            XmlResourceParser xml = resources.getXml(i2);
            AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
            do {
                next = xml.next();
                if (next == 2) {
                    break;
                }
            } while (next != 1);
            if (next == 2) {
                return d(resources, xml, attributeSetAsAttributeSet, theme);
            }
            throw new XmlPullParserException("No start tag found");
        } catch (IOException e2) {
            Log.e("VectorDrawableCompat", "parser error", e2);
            return null;
        } catch (XmlPullParserException e3) {
            Log.e("VectorDrawableCompat", "parser error", e3);
            return null;
        }
    }

    public static C0087A d(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        C0087A c0087a = new C0087A();
        c0087a.inflate(resources, xmlPullParser, attributeSet, theme);
        return c0087a;
    }

    public static PorterDuff.Mode h(int i2, PorterDuff.Mode mode) {
        if (i2 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i2 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i2 == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i2) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f753a;
        if (drawable == null) {
            return false;
        }
        AbstractC0244a.b(drawable);
        return false;
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        copyBounds(this.f674j);
        if (this.f674j.width() <= 0 || this.f674j.height() <= 0) {
            return;
        }
        ColorFilter colorFilter = this.f668d;
        if (colorFilter == null) {
            colorFilter = this.f667c;
        }
        canvas.getMatrix(this.f673i);
        this.f673i.getValues(this.f672h);
        float fAbs = Math.abs(this.f672h[0]);
        float fAbs2 = Math.abs(this.f672h[4]);
        float fAbs3 = Math.abs(this.f672h[1]);
        float fAbs4 = Math.abs(this.f672h[3]);
        if (fAbs3 != 0.0f || fAbs4 != 0.0f) {
            fAbs = 1.0f;
            fAbs2 = 1.0f;
        }
        int iMin = Math.min(2048, (int) (this.f674j.width() * fAbs));
        int iMin2 = Math.min(2048, (int) (this.f674j.height() * fAbs2));
        if (iMin <= 0 || iMin2 <= 0) {
            return;
        }
        int iSave = canvas.save();
        Rect rect = this.f674j;
        canvas.translate(rect.left, rect.top);
        if (g()) {
            canvas.translate(this.f674j.width(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        this.f674j.offsetTo(0, 0);
        this.f666b.c(iMin, iMin2);
        if (!this.f670f) {
            this.f666b.h(iMin, iMin2);
        } else if (!this.f666b.b()) {
            this.f666b.h(iMin, iMin2);
            this.f666b.g();
        }
        this.f666b.d(canvas, colorFilter, this.f674j);
        canvas.restoreToCount(iSave);
    }

    public Object e(String str) {
        return this.f666b.f721b.f719o.get(str);
    }

    public final void f(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        f fVar = this.f666b;
        e eVar = fVar.f721b;
        Stack stack = new Stack();
        stack.push(eVar.f712h);
        int eventType = xmlPullParser.getEventType();
        boolean z2 = true;
        while (eventType != 1) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                c cVar = (c) stack.peek();
                if ("path".equals(name)) {
                    b bVar = new b();
                    bVar.f(resources, attributeSet, theme, xmlPullParser);
                    cVar.f689b.add(bVar);
                    if (bVar.a() != null) {
                        eVar.f719o.put(bVar.a(), bVar);
                    }
                    fVar.f720a = bVar.f703c | fVar.f720a;
                    z2 = false;
                } else if ("clip-path".equals(name)) {
                    a aVar = new a();
                    aVar.d(resources, attributeSet, theme, xmlPullParser);
                    cVar.f689b.add(aVar);
                    if (aVar.a() != null) {
                        eVar.f719o.put(aVar.a(), aVar);
                    }
                    fVar.f720a = aVar.f703c | fVar.f720a;
                } else if ("group".equals(name)) {
                    c cVar2 = new c();
                    cVar2.d(resources, attributeSet, theme, xmlPullParser);
                    cVar.f689b.add(cVar2);
                    stack.push(cVar2);
                    if (cVar2.c() != null) {
                        eVar.f719o.put(cVar2.c(), cVar2);
                    }
                    fVar.f720a = cVar2.f698k | fVar.f720a;
                }
            } else if (eventType == 3 && "group".equals(xmlPullParser.getName())) {
                stack.pop();
            }
            eventType = xmlPullParser.next();
        }
        if (z2) {
            StringBuffer stringBuffer = new StringBuffer();
            if (stringBuffer.length() > 0) {
                stringBuffer.append(" or ");
            }
            stringBuffer.append("path");
            throw new XmlPullParserException("no " + ((Object) stringBuffer) + " defined");
        }
    }

    public final boolean g() {
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f753a;
        return drawable != null ? AbstractC0244a.c(drawable) : this.f666b.f721b.k();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f753a;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.f666b.getChangingConfigurations();
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        if (this.f753a != null) {
            return new g(this.f753a.getConstantState());
        }
        this.f666b.f720a = getChangingConfigurations();
        return this.f666b;
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f753a;
        return drawable != null ? drawable.getIntrinsicHeight() : (int) this.f666b.f721b.f714j;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f753a;
        return drawable != null ? drawable.getIntrinsicWidth() : (int) this.f666b.f721b.f713i;
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
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
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

    public void i(boolean z2) {
        this.f670f = z2;
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean isAutoMirrored() {
        return super.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        f fVar;
        ColorStateList colorStateList;
        Drawable drawable = this.f753a;
        return drawable != null ? drawable.isStateful() : super.isStateful() || !((fVar = this.f666b) == null || (colorStateList = fVar.f722c) == null || !colorStateList.isStateful());
    }

    public final void j(TypedArray typedArray, XmlPullParser xmlPullParser) throws XmlPullParserException {
        f fVar = this.f666b;
        e eVar = fVar.f721b;
        fVar.f723d = h(x.d(typedArray, xmlPullParser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
        ColorStateList colorStateList = typedArray.getColorStateList(1);
        if (colorStateList != null) {
            fVar.f722c = colorStateList;
        }
        fVar.f724e = x.a(typedArray, xmlPullParser, "autoMirrored", 5, fVar.f724e);
        eVar.f715k = x.c(typedArray, xmlPullParser, "viewportWidth", 7, eVar.f715k);
        float fC = x.c(typedArray, xmlPullParser, "viewportHeight", 8, eVar.f716l);
        eVar.f716l = fC;
        if (eVar.f715k <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        }
        if (fC <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
        }
        eVar.f713i = typedArray.getDimension(3, eVar.f713i);
        float dimension = typedArray.getDimension(2, eVar.f714j);
        eVar.f714j = dimension;
        if (eVar.f713i <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires width > 0");
        }
        if (dimension <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires height > 0");
        }
        eVar.l(x.c(typedArray, xmlPullParser, "alpha", 4, eVar.i()));
        String string = typedArray.getString(0);
        if (string != null) {
            eVar.f718n = string;
            eVar.f719o.put(string, eVar);
        }
    }

    @Override // a.y, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    public PorterDuffColorFilter k(PorterDuffColorFilter porterDuffColorFilter, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.f669e && super.mutate() == this) {
            this.f666b = new f(this.f666b);
            this.f669e = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        Drawable drawable = this.f753a;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        f fVar = this.f666b;
        ColorStateList colorStateList = fVar.f722c;
        if (colorStateList == null || (mode = fVar.f723d) == null) {
            return false;
        }
        this.f667c = k(this.f667c, colorStateList, mode);
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable runnable, long j2) {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j2);
        } else {
            super.scheduleSelf(runnable, j2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            drawable.setAlpha(i2);
        } else if (this.f666b.f721b.k() != i2) {
            this.f666b.f721b.m(i2);
            invalidateSelf();
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
            setTintList(ColorStateList.valueOf(i2));
        }
    }

    @Override // android.graphics.drawable.Drawable, g.InterfaceC0243D
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            AbstractC0244a.n(drawable, colorStateList);
            return;
        }
        f fVar = this.f666b;
        if (fVar.f722c != colorStateList) {
            fVar.f722c = colorStateList;
            this.f667c = k(this.f667c, colorStateList, fVar.f723d);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable, g.InterfaceC0243D
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            AbstractC0244a.o(drawable, mode);
            return;
        }
        f fVar = this.f666b;
        if (fVar.f723d != mode) {
            fVar.f723d = mode;
            this.f667c = k(this.f667c, fVar.f722c, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z2, boolean z3) {
        Drawable drawable = this.f753a;
        return drawable != null ? drawable.setVisible(z2, z3) : super.setVisible(z2, z3);
    }

    @Override // android.graphics.drawable.Drawable
    public void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    /* JADX INFO: renamed from: a.A$d */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public w.b[] f701a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f702b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f703c;

        public d() {
            this.f701a = null;
        }

        public String a() {
            return this.f702b;
        }

        public boolean b() {
            return false;
        }

        public void c(Path path) {
            path.reset();
            w.b[] bVarArr = this.f701a;
            if (bVarArr != null) {
                w.b.d(bVarArr, path);
            }
        }

        public d(d dVar) {
            this.f701a = null;
            this.f702b = dVar.f702b;
            this.f703c = dVar.f703c;
            this.f701a = w.d(dVar.f701a);
        }
    }

    /* JADX INFO: renamed from: a.A$g */
    public static class g extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Drawable.ConstantState f732a;

        public g(Drawable.ConstantState constantState) {
            this.f732a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f732a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f732a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            C0087A c0087a = new C0087A();
            c0087a.f753a = AbstractC0088B.a(this.f732a.newDrawable());
            return c0087a;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            C0087A c0087a = new C0087A();
            c0087a.f753a = AbstractC0088B.a(this.f732a.newDrawable(resources));
            return c0087a;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            C0087A c0087a = new C0087A();
            c0087a.f753a = AbstractC0088B.a(this.f732a.newDrawable(resources, theme));
            return c0087a;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f668d = colorFilter;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        Drawable drawable = this.f753a;
        if (drawable != null) {
            AbstractC0244a.f(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        f fVar = this.f666b;
        fVar.f721b = new e();
        TypedArray typedArrayA = y.a(resources, theme, attributeSet, AbstractC0089a.f733a);
        j(typedArrayA, xmlPullParser);
        typedArrayA.recycle();
        fVar.f720a = getChangingConfigurations();
        fVar.f730k = true;
        f(resources, xmlPullParser, attributeSet, theme);
        this.f667c = k(this.f667c, fVar.f722c, fVar.f723d);
    }

    public C0087A(f fVar) {
        this.f670f = true;
        this.f672h = new float[9];
        this.f673i = new Matrix();
        this.f674j = new Rect();
        this.f666b = fVar;
        this.f667c = k(this.f667c, fVar.f722c, fVar.f723d);
    }

    /* JADX INFO: renamed from: a.A$b */
    public static class b extends d {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int[] f675d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f676e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f677f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f678g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f679h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f680i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public float f681j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public float f682k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public float f683l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public float f684m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public Paint.Cap f685n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public Paint.Join f686o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public float f687p;

        public b() {
            this.f676e = 0;
            this.f677f = 0.0f;
            this.f678g = 0;
            this.f679h = 1.0f;
            this.f681j = 1.0f;
            this.f682k = 0.0f;
            this.f683l = 1.0f;
            this.f684m = 0.0f;
            this.f685n = Paint.Cap.BUTT;
            this.f686o = Paint.Join.MITER;
            this.f687p = 4.0f;
        }

        public final Paint.Cap d(int i2, Paint.Cap cap) {
            return i2 != 0 ? i2 != 1 ? i2 != 2 ? cap : Paint.Cap.SQUARE : Paint.Cap.ROUND : Paint.Cap.BUTT;
        }

        public final Paint.Join e(int i2, Paint.Join join) {
            return i2 != 0 ? i2 != 1 ? i2 != 2 ? join : Paint.Join.BEVEL : Paint.Join.ROUND : Paint.Join.MITER;
        }

        public void f(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray typedArrayA = y.a(resources, theme, attributeSet, AbstractC0089a.f735c);
            g(typedArrayA, xmlPullParser);
            typedArrayA.recycle();
        }

        public final void g(TypedArray typedArray, XmlPullParser xmlPullParser) {
            this.f675d = null;
            if (x.e(xmlPullParser, "pathData")) {
                String string = typedArray.getString(0);
                if (string != null) {
                    this.f702b = string;
                }
                String string2 = typedArray.getString(2);
                if (string2 != null) {
                    this.f701a = w.c(string2);
                }
                this.f678g = x.b(typedArray, xmlPullParser, "fillColor", 1, this.f678g);
                this.f681j = x.c(typedArray, xmlPullParser, "fillAlpha", 12, this.f681j);
                this.f685n = d(x.d(typedArray, xmlPullParser, "strokeLineCap", 8, -1), this.f685n);
                this.f686o = e(x.d(typedArray, xmlPullParser, "strokeLineJoin", 9, -1), this.f686o);
                this.f687p = x.c(typedArray, xmlPullParser, "strokeMiterLimit", 10, this.f687p);
                this.f676e = x.b(typedArray, xmlPullParser, "strokeColor", 3, this.f676e);
                this.f679h = x.c(typedArray, xmlPullParser, "strokeAlpha", 11, this.f679h);
                this.f677f = x.c(typedArray, xmlPullParser, "strokeWidth", 4, this.f677f);
                this.f683l = x.c(typedArray, xmlPullParser, "trimPathEnd", 6, this.f683l);
                this.f684m = x.c(typedArray, xmlPullParser, "trimPathOffset", 7, this.f684m);
                this.f682k = x.c(typedArray, xmlPullParser, "trimPathStart", 5, this.f682k);
            }
        }

        public b(b bVar) {
            super(bVar);
            this.f676e = 0;
            this.f677f = 0.0f;
            this.f678g = 0;
            this.f679h = 1.0f;
            this.f681j = 1.0f;
            this.f682k = 0.0f;
            this.f683l = 1.0f;
            this.f684m = 0.0f;
            this.f685n = Paint.Cap.BUTT;
            this.f686o = Paint.Join.MITER;
            this.f687p = 4.0f;
            this.f675d = bVar.f675d;
            this.f676e = bVar.f676e;
            this.f677f = bVar.f677f;
            this.f679h = bVar.f679h;
            this.f678g = bVar.f678g;
            this.f680i = bVar.f680i;
            this.f681j = bVar.f681j;
            this.f682k = bVar.f682k;
            this.f683l = bVar.f683l;
            this.f684m = bVar.f684m;
            this.f685n = bVar.f685n;
            this.f686o = bVar.f686o;
            this.f687p = bVar.f687p;
        }
    }

    /* JADX INFO: renamed from: a.A$e */
    public static class e {

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public static final Matrix f704p = new Matrix();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Path f705a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Path f706b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Matrix f707c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Paint f708d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Paint f709e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public PathMeasure f710f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f711g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final c f712h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public float f713i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public float f714j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public float f715k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public float f716l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public int f717m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public String f718n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public final C0252a f719o;

        public e() {
            this.f707c = new Matrix();
            this.f713i = 0.0f;
            this.f714j = 0.0f;
            this.f715k = 0.0f;
            this.f716l = 0.0f;
            this.f717m = 255;
            this.f718n = null;
            this.f719o = new C0252a();
            this.f712h = new c();
            this.f705a = new Path();
            this.f706b = new Path();
        }

        public static float e(float f2, float f3, float f4, float f5) {
            return (f2 * f5) - (f3 * f4);
        }

        public void f(Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            g(this.f712h, f704p, canvas, i2, i3, colorFilter);
        }

        public final void g(c cVar, Matrix matrix, Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            cVar.f688a.set(matrix);
            cVar.f688a.preConcat(cVar.f697j);
            canvas.save();
            for (int i4 = 0; i4 < cVar.f689b.size(); i4++) {
                Object obj = cVar.f689b.get(i4);
                if (obj instanceof c) {
                    g((c) obj, cVar.f688a, canvas, i2, i3, colorFilter);
                } else if (obj instanceof d) {
                    h(cVar, (d) obj, canvas, i2, i3, colorFilter);
                }
            }
            canvas.restore();
        }

        public final void h(c cVar, d dVar, Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            float f2 = i2 / this.f715k;
            float f3 = i3 / this.f716l;
            float fMin = Math.min(f2, f3);
            Matrix matrix = cVar.f688a;
            this.f707c.set(matrix);
            this.f707c.postScale(f2, f3);
            float fJ = j(matrix);
            if (fJ == 0.0f) {
                return;
            }
            dVar.c(this.f705a);
            Path path = this.f705a;
            this.f706b.reset();
            if (dVar.b()) {
                this.f706b.addPath(path, this.f707c);
                canvas.clipPath(this.f706b);
                return;
            }
            b bVar = (b) dVar;
            float f4 = bVar.f682k;
            if (f4 != 0.0f || bVar.f683l != 1.0f) {
                float f5 = bVar.f684m;
                float f6 = (f4 + f5) % 1.0f;
                float f7 = (bVar.f683l + f5) % 1.0f;
                if (this.f710f == null) {
                    this.f710f = new PathMeasure();
                }
                this.f710f.setPath(this.f705a, false);
                float length = this.f710f.getLength();
                float f8 = f6 * length;
                float f9 = f7 * length;
                path.reset();
                if (f8 > f9) {
                    this.f710f.getSegment(f8, length, path, true);
                    this.f710f.getSegment(0.0f, f9, path, true);
                } else {
                    this.f710f.getSegment(f8, f9, path, true);
                }
                path.rLineTo(0.0f, 0.0f);
            }
            this.f706b.addPath(path, this.f707c);
            if (bVar.f678g != 0) {
                if (this.f709e == null) {
                    Paint paint = new Paint();
                    this.f709e = paint;
                    paint.setStyle(Paint.Style.FILL);
                    this.f709e.setAntiAlias(true);
                }
                Paint paint2 = this.f709e;
                paint2.setColor(C0087A.b(bVar.f678g, bVar.f681j));
                paint2.setColorFilter(colorFilter);
                canvas.drawPath(this.f706b, paint2);
            }
            if (bVar.f676e != 0) {
                if (this.f708d == null) {
                    Paint paint3 = new Paint();
                    this.f708d = paint3;
                    paint3.setStyle(Paint.Style.STROKE);
                    this.f708d.setAntiAlias(true);
                }
                Paint paint4 = this.f708d;
                Paint.Join join = bVar.f686o;
                if (join != null) {
                    paint4.setStrokeJoin(join);
                }
                Paint.Cap cap = bVar.f685n;
                if (cap != null) {
                    paint4.setStrokeCap(cap);
                }
                paint4.setStrokeMiter(bVar.f687p);
                paint4.setColor(C0087A.b(bVar.f676e, bVar.f679h));
                paint4.setColorFilter(colorFilter);
                paint4.setStrokeWidth(bVar.f677f * fMin * fJ);
                canvas.drawPath(this.f706b, paint4);
            }
        }

        public float i() {
            return k() / 255.0f;
        }

        public final float j(Matrix matrix) {
            float[] fArr = {0.0f, 1.0f, 1.0f, 0.0f};
            matrix.mapVectors(fArr);
            float fHypot = (float) Math.hypot(fArr[0], fArr[1]);
            float fHypot2 = (float) Math.hypot(fArr[2], fArr[3]);
            float fE = e(fArr[0], fArr[1], fArr[2], fArr[3]);
            float fMax = Math.max(fHypot, fHypot2);
            if (fMax > 0.0f) {
                return Math.abs(fE) / fMax;
            }
            return 0.0f;
        }

        public int k() {
            return this.f717m;
        }

        public void l(float f2) {
            m((int) (f2 * 255.0f));
        }

        public void m(int i2) {
            this.f717m = i2;
        }

        public e(e eVar) {
            this.f707c = new Matrix();
            this.f713i = 0.0f;
            this.f714j = 0.0f;
            this.f715k = 0.0f;
            this.f716l = 0.0f;
            this.f717m = 255;
            this.f718n = null;
            C0252a c0252a = new C0252a();
            this.f719o = c0252a;
            this.f712h = new c(eVar.f712h, c0252a);
            this.f705a = new Path(eVar.f705a);
            this.f706b = new Path(eVar.f706b);
            this.f713i = eVar.f713i;
            this.f714j = eVar.f714j;
            this.f715k = eVar.f715k;
            this.f716l = eVar.f716l;
            this.f711g = eVar.f711g;
            this.f717m = eVar.f717m;
            this.f718n = eVar.f718n;
            String str = eVar.f718n;
            if (str != null) {
                c0252a.put(str, this);
            }
        }
    }

    /* JADX INFO: renamed from: a.A$c */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Matrix f688a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final ArrayList f689b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f690c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f691d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f692e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f693f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f694g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f695h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public float f696i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final Matrix f697j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f698k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public int[] f699l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public String f700m;

        public c(c cVar, C0252a c0252a) {
            d aVar;
            this.f688a = new Matrix();
            this.f689b = new ArrayList();
            this.f690c = 0.0f;
            this.f691d = 0.0f;
            this.f692e = 0.0f;
            this.f693f = 1.0f;
            this.f694g = 1.0f;
            this.f695h = 0.0f;
            this.f696i = 0.0f;
            Matrix matrix = new Matrix();
            this.f697j = matrix;
            this.f700m = null;
            this.f690c = cVar.f690c;
            this.f691d = cVar.f691d;
            this.f692e = cVar.f692e;
            this.f693f = cVar.f693f;
            this.f694g = cVar.f694g;
            this.f695h = cVar.f695h;
            this.f696i = cVar.f696i;
            this.f699l = cVar.f699l;
            String str = cVar.f700m;
            this.f700m = str;
            this.f698k = cVar.f698k;
            if (str != null) {
                c0252a.put(str, this);
            }
            matrix.set(cVar.f697j);
            ArrayList arrayList = cVar.f689b;
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                Object obj = arrayList.get(i2);
                if (obj instanceof c) {
                    this.f689b.add(new c((c) obj, c0252a));
                } else {
                    if (obj instanceof b) {
                        aVar = new b((b) obj);
                    } else {
                        if (!(obj instanceof a)) {
                            throw new IllegalStateException("Unknown object in the tree!");
                        }
                        aVar = new a((a) obj);
                    }
                    this.f689b.add(aVar);
                    Object obj2 = aVar.f702b;
                    if (obj2 != null) {
                        c0252a.put(obj2, aVar);
                    }
                }
            }
        }

        public String c() {
            return this.f700m;
        }

        public void d(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray typedArrayA = y.a(resources, theme, attributeSet, AbstractC0089a.f734b);
            f(typedArrayA, xmlPullParser);
            typedArrayA.recycle();
        }

        public final void e() {
            this.f697j.reset();
            this.f697j.postTranslate(-this.f691d, -this.f692e);
            this.f697j.postScale(this.f693f, this.f694g);
            this.f697j.postRotate(this.f690c, 0.0f, 0.0f);
            this.f697j.postTranslate(this.f695h + this.f691d, this.f696i + this.f692e);
        }

        public final void f(TypedArray typedArray, XmlPullParser xmlPullParser) {
            this.f699l = null;
            this.f690c = x.c(typedArray, xmlPullParser, "rotation", 5, this.f690c);
            this.f691d = typedArray.getFloat(1, this.f691d);
            this.f692e = typedArray.getFloat(2, this.f692e);
            this.f693f = x.c(typedArray, xmlPullParser, "scaleX", 3, this.f693f);
            this.f694g = x.c(typedArray, xmlPullParser, "scaleY", 4, this.f694g);
            this.f695h = x.c(typedArray, xmlPullParser, "translateX", 6, this.f695h);
            this.f696i = x.c(typedArray, xmlPullParser, "translateY", 7, this.f696i);
            String string = typedArray.getString(0);
            if (string != null) {
                this.f700m = string;
            }
            e();
        }

        public c() {
            this.f688a = new Matrix();
            this.f689b = new ArrayList();
            this.f690c = 0.0f;
            this.f691d = 0.0f;
            this.f692e = 0.0f;
            this.f693f = 1.0f;
            this.f694g = 1.0f;
            this.f695h = 0.0f;
            this.f696i = 0.0f;
            this.f697j = new Matrix();
            this.f700m = null;
        }
    }
}

package x;

import a.C0087A;
import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.util.Xml;
import d.AbstractC0235a;
import f.AbstractC0239a;
import g.AbstractC0244a;
import j.C0252a;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p.AbstractC0460a;
import p.AbstractC0462c;
import p.AbstractC0464e;

/* JADX INFO: renamed from: x.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0508k {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static C0508k f8168i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WeakHashMap f8176a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public C0252a f8177b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public SparseArray f8178c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f8179d = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final WeakHashMap f8180e = new WeakHashMap(0);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TypedValue f8181f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f8182g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final PorterDuff.Mode f8167h = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final b f8169j = new b(6);

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final int[] f8170k = {AbstractC0464e.f7761Q, AbstractC0464e.f7759O, AbstractC0464e.f7764a};

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final int[] f8171l = {AbstractC0464e.f7776m, AbstractC0464e.f7789z, AbstractC0464e.f7781r, AbstractC0464e.f7777n, AbstractC0464e.f7778o, AbstractC0464e.f7780q, AbstractC0464e.f7779p};

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final int[] f8172m = {AbstractC0464e.f7758N, AbstractC0464e.f7760P, AbstractC0464e.f7772i, AbstractC0464e.f7751G, AbstractC0464e.f7752H, AbstractC0464e.f7754J, AbstractC0464e.f7756L, AbstractC0464e.f7753I, AbstractC0464e.f7755K, AbstractC0464e.f7757M};

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final int[] f8173n = {AbstractC0464e.f7784u, AbstractC0464e.f7770g, AbstractC0464e.f7783t};

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final int[] f8174o = {AbstractC0464e.f7750F, AbstractC0464e.f7762R};

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final int[] f8175p = {AbstractC0464e.f7766c, AbstractC0464e.f7769f};

    /* JADX INFO: renamed from: x.k$a */
    public static class a implements c {
        @Override // x.C0508k.c
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return a.s.b(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e2) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e2);
                return null;
            }
        }
    }

    /* JADX INFO: renamed from: x.k$b */
    public static class b extends j.f {
        public b(int i2) {
            super(i2);
        }

        public static int i(int i2, PorterDuff.Mode mode) {
            return ((i2 + 31) * 31) + mode.hashCode();
        }

        public PorterDuffColorFilter j(int i2, PorterDuff.Mode mode) {
            return (PorterDuffColorFilter) d(Integer.valueOf(i(i2, mode)));
        }

        public PorterDuffColorFilter k(int i2, PorterDuff.Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return (PorterDuffColorFilter) e(Integer.valueOf(i(i2, mode)), porterDuffColorFilter);
        }
    }

    /* JADX INFO: renamed from: x.k$c */
    public interface c {
        Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme);
    }

    /* JADX INFO: renamed from: x.k$d */
    public static class d implements c {
        @Override // x.C0508k.c
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return C0087A.d(context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e2) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", e2);
                return null;
            }
        }
    }

    public static void A(Drawable drawable, int i2, PorterDuff.Mode mode) {
        if (H.a(drawable)) {
            drawable = drawable.mutate();
        }
        if (mode == null) {
            mode = f8167h;
        }
        drawable.setColorFilter(q(i2, mode));
    }

    public static void C(Drawable drawable, d0 d0Var, int[] iArr) {
        if (H.a(drawable) && drawable.mutate() != drawable) {
            Log.d("AppCompatDrawableManager", "Mutated drawable is not the same instance as the input.");
            return;
        }
        boolean z2 = d0Var.f8140d;
        if (z2 || d0Var.f8139c) {
            drawable.setColorFilter(l(z2 ? d0Var.f8137a : null, d0Var.f8139c ? d0Var.f8138b : f8167h, iArr));
        } else {
            drawable.clearColorFilter();
        }
        if (Build.VERSION.SDK_INT <= 23) {
            drawable.invalidateSelf();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006b A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean D(android.content.Context r6, int r7, android.graphics.drawable.Drawable r8) {
        /*
            android.graphics.PorterDuff$Mode r0 = x.C0508k.f8167h
            int[] r1 = x.C0508k.f8170k
            boolean r1 = d(r1, r7)
            r2 = 1
            r3 = 0
            r4 = -1
            if (r1 == 0) goto L13
            int r7 = p.AbstractC0460a.f7726t
        Lf:
            r1 = r0
        L10:
            r0 = -1
            r5 = 1
            goto L4e
        L13:
            int[] r1 = x.C0508k.f8172m
            boolean r1 = d(r1, r7)
            if (r1 == 0) goto L1e
            int r7 = p.AbstractC0460a.f7724r
            goto Lf
        L1e:
            int[] r1 = x.C0508k.f8173n
            boolean r1 = d(r1, r7)
            r5 = 16842801(0x1010031, float:2.3693695E-38)
            if (r1 == 0) goto L30
            android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.MULTIPLY
        L2b:
            r1 = r0
            r7 = 16842801(0x1010031, float:2.3693695E-38)
            goto L10
        L30:
            int r1 = p.AbstractC0464e.f7782s
            if (r7 != r1) goto L45
            r7 = 1109603123(0x42233333, float:40.8)
            int r7 = java.lang.Math.round(r7)
            r1 = 16842800(0x1010030, float:2.3693693E-38)
            r1 = r0
            r5 = 1
            r0 = r7
            r7 = 16842800(0x1010030, float:2.3693693E-38)
            goto L4e
        L45:
            int r1 = p.AbstractC0464e.f7773j
            if (r7 != r1) goto L4a
            goto L2b
        L4a:
            r1 = r0
            r7 = 0
            r0 = -1
            r5 = 0
        L4e:
            if (r5 == 0) goto L6b
            boolean r3 = x.H.a(r8)
            if (r3 == 0) goto L5a
            android.graphics.drawable.Drawable r8 = r8.mutate()
        L5a:
            int r6 = x.b0.b(r6, r7)
            android.graphics.PorterDuffColorFilter r6 = q(r6, r1)
            r8.setColorFilter(r6)
            if (r0 == r4) goto L6a
            r8.setAlpha(r0)
        L6a:
            return r2
        L6b:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: x.C0508k.D(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
    }

    public static boolean d(int[] iArr, int i2) {
        for (int i3 : iArr) {
            if (i3 == i2) {
                return true;
            }
        }
        return false;
    }

    public static long h(TypedValue typedValue) {
        return (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
    }

    public static PorterDuffColorFilter l(ColorStateList colorStateList, PorterDuff.Mode mode, int[] iArr) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return q(colorStateList.getColorForState(iArr, 0), mode);
    }

    public static C0508k m() {
        if (f8168i == null) {
            C0508k c0508k = new C0508k();
            f8168i = c0508k;
            v(c0508k);
        }
        return f8168i;
    }

    public static PorterDuffColorFilter q(int i2, PorterDuff.Mode mode) {
        b bVar = f8169j;
        PorterDuffColorFilter porterDuffColorFilterJ = bVar.j(i2, mode);
        if (porterDuffColorFilterJ != null) {
            return porterDuffColorFilterJ;
        }
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(i2, mode);
        bVar.k(i2, mode, porterDuffColorFilter);
        return porterDuffColorFilter;
    }

    public static PorterDuff.Mode u(int i2) {
        if (i2 == AbstractC0464e.f7748D) {
            return PorterDuff.Mode.MULTIPLY;
        }
        return null;
    }

    public static void v(C0508k c0508k) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 23) {
            c0508k.a("vector", new d());
            if (i2 >= 11) {
                c0508k.a("animated-vector", new a());
            }
        }
    }

    public static boolean w(Drawable drawable) {
        return (drawable instanceof C0087A) || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }

    public final Drawable B(Context context, int i2, boolean z2, Drawable drawable) {
        ColorStateList colorStateListR = r(context, i2);
        if (colorStateListR != null) {
            if (H.a(drawable)) {
                drawable = drawable.mutate();
            }
            Drawable drawableQ = AbstractC0244a.q(drawable);
            AbstractC0244a.n(drawableQ, colorStateListR);
            PorterDuff.Mode modeU = u(i2);
            if (modeU == null) {
                return drawableQ;
            }
            AbstractC0244a.o(drawableQ, modeU);
            return drawableQ;
        }
        if (i2 == AbstractC0464e.f7745A) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            Drawable drawableFindDrawableByLayerId = layerDrawable.findDrawableByLayerId(R.id.background);
            int iB = b0.b(context, AbstractC0460a.f7726t);
            PorterDuff.Mode mode = f8167h;
            A(drawableFindDrawableByLayerId, iB, mode);
            A(layerDrawable.findDrawableByLayerId(R.id.secondaryProgress), b0.b(context, AbstractC0460a.f7726t), mode);
            A(layerDrawable.findDrawableByLayerId(R.id.progress), b0.b(context, AbstractC0460a.f7724r), mode);
            return drawable;
        }
        if (i2 != AbstractC0464e.f7786w && i2 != AbstractC0464e.f7785v && i2 != AbstractC0464e.f7787x) {
            if (D(context, i2, drawable) || !z2) {
                return drawable;
            }
            return null;
        }
        LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
        Drawable drawableFindDrawableByLayerId2 = layerDrawable2.findDrawableByLayerId(R.id.background);
        int iA = b0.a(context, AbstractC0460a.f7726t);
        PorterDuff.Mode mode2 = f8167h;
        A(drawableFindDrawableByLayerId2, iA, mode2);
        A(layerDrawable2.findDrawableByLayerId(R.id.secondaryProgress), b0.b(context, AbstractC0460a.f7724r), mode2);
        A(layerDrawable2.findDrawableByLayerId(R.id.progress), b0.b(context, AbstractC0460a.f7724r), mode2);
        return drawable;
    }

    public final void a(String str, c cVar) {
        if (this.f8177b == null) {
            this.f8177b = new C0252a();
        }
        this.f8177b.put(str, cVar);
    }

    public final boolean b(Context context, long j2, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return false;
        }
        synchronized (this.f8179d) {
            try {
                j.e eVar = (j.e) this.f8180e.get(context);
                if (eVar == null) {
                    eVar = new j.e();
                    this.f8180e.put(context, eVar);
                }
                eVar.h(j2, new WeakReference(constantState));
            } catch (Throwable th) {
                throw th;
            }
        }
        return true;
    }

    public final void c(Context context, int i2, ColorStateList colorStateList) {
        if (this.f8176a == null) {
            this.f8176a = new WeakHashMap();
        }
        SparseArray sparseArray = (SparseArray) this.f8176a.get(context);
        if (sparseArray == null) {
            sparseArray = new SparseArray();
            this.f8176a.put(context, sparseArray);
        }
        sparseArray.append(i2, colorStateList);
    }

    public final void e(Context context) {
        if (this.f8182g) {
            return;
        }
        this.f8182g = true;
        Drawable drawableO = o(context, AbstractC0464e.f7763S);
        if (drawableO == null || !w(drawableO)) {
            this.f8182g = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        }
    }

    public final ColorStateList f(Context context, ColorStateList colorStateList) {
        return g(context, 0, null);
    }

    public final ColorStateList g(Context context, int i2, ColorStateList colorStateList) {
        int[][] iArr = new int[4][];
        int[] iArr2 = new int[4];
        int iB = b0.b(context, AbstractC0460a.f7725s);
        int iA = b0.a(context, AbstractC0460a.f7723q);
        int[] iArr3 = b0.f8125b;
        iArr[0] = iArr3;
        if (colorStateList != null) {
            iA = colorStateList.getColorForState(iArr3, 0);
        }
        iArr2[0] = iA;
        int[] iArr4 = b0.f8128e;
        iArr[1] = iArr4;
        iArr2[1] = AbstractC0239a.b(iB, colorStateList == null ? i2 : colorStateList.getColorForState(iArr4, 0));
        int[] iArr5 = b0.f8126c;
        iArr[2] = iArr5;
        iArr2[2] = AbstractC0239a.b(iB, colorStateList == null ? i2 : colorStateList.getColorForState(iArr5, 0));
        int[] iArr6 = b0.f8132i;
        iArr[3] = iArr6;
        if (colorStateList != null) {
            i2 = colorStateList.getColorForState(iArr6, 0);
        }
        iArr2[3] = i2;
        return new ColorStateList(iArr, iArr2);
    }

    public final ColorStateList i(Context context, ColorStateList colorStateList) {
        return g(context, b0.b(context, AbstractC0460a.f7722p), colorStateList);
    }

    public final ColorStateList j(Context context, ColorStateList colorStateList) {
        return g(context, b0.b(context, AbstractC0460a.f7723q), colorStateList);
    }

    public final Drawable k(Context context, int i2) {
        if (this.f8181f == null) {
            this.f8181f = new TypedValue();
        }
        TypedValue typedValue = this.f8181f;
        context.getResources().getValue(i2, typedValue, true);
        long jH = h(typedValue);
        Drawable drawableN = n(context, jH);
        if (drawableN != null) {
            return drawableN;
        }
        if (i2 == AbstractC0464e.f7771h) {
            drawableN = new LayerDrawable(new Drawable[]{o(context, AbstractC0464e.f7770g), o(context, AbstractC0464e.f7772i)});
        }
        if (drawableN != null) {
            drawableN.setChangingConfigurations(typedValue.changingConfigurations);
            b(context, jH, drawableN);
        }
        return drawableN;
    }

    public final Drawable n(Context context, long j2) {
        synchronized (this.f8179d) {
            try {
                j.e eVar = (j.e) this.f8180e.get(context);
                if (eVar == null) {
                    return null;
                }
                WeakReference weakReference = (WeakReference) eVar.e(j2);
                if (weakReference != null) {
                    Drawable.ConstantState constantState = (Drawable.ConstantState) weakReference.get();
                    if (constantState != null) {
                        return constantState.newDrawable(context.getResources());
                    }
                    eVar.c(j2);
                }
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public Drawable o(Context context, int i2) {
        return p(context, i2, false);
    }

    public Drawable p(Context context, int i2, boolean z2) {
        e(context);
        Drawable drawableX = x(context, i2);
        if (drawableX == null) {
            drawableX = k(context, i2);
        }
        if (drawableX == null) {
            drawableX = AbstractC0235a.d(context, i2);
        }
        if (drawableX != null) {
            drawableX = B(context, i2, z2, drawableX);
        }
        if (drawableX != null) {
            H.b(drawableX);
        }
        return drawableX;
    }

    public ColorStateList r(Context context, int i2) {
        return s(context, i2, null);
    }

    public ColorStateList s(Context context, int i2, ColorStateList colorStateList) {
        boolean z2 = colorStateList == null;
        ColorStateList colorStateListT = z2 ? t(context, i2) : null;
        if (colorStateListT == null) {
            if (i2 == AbstractC0464e.f7774k) {
                colorStateListT = q.b.c(context, AbstractC0462c.f7738d);
            } else if (i2 == AbstractC0464e.f7749E) {
                colorStateListT = q.b.c(context, AbstractC0462c.f7742h);
            } else if (i2 == AbstractC0464e.f7748D) {
                colorStateListT = q.b.c(context, AbstractC0462c.f7741g);
            } else if (i2 == AbstractC0464e.f7768e) {
                colorStateListT = j(context, colorStateList);
            } else if (i2 == AbstractC0464e.f7765b) {
                colorStateListT = f(context, colorStateList);
            } else if (i2 == AbstractC0464e.f7767d) {
                colorStateListT = i(context, colorStateList);
            } else if (i2 == AbstractC0464e.f7746B || i2 == AbstractC0464e.f7747C) {
                colorStateListT = q.b.c(context, AbstractC0462c.f7740f);
            } else if (d(f8171l, i2)) {
                colorStateListT = b0.d(context, AbstractC0460a.f7726t);
            } else if (d(f8174o, i2)) {
                colorStateListT = q.b.c(context, AbstractC0462c.f7737c);
            } else if (d(f8175p, i2)) {
                colorStateListT = q.b.c(context, AbstractC0462c.f7736b);
            } else if (i2 == AbstractC0464e.f7788y) {
                colorStateListT = q.b.c(context, AbstractC0462c.f7739e);
            }
            if (z2 && colorStateListT != null) {
                c(context, i2, colorStateListT);
            }
        }
        return colorStateListT;
    }

    public final ColorStateList t(Context context, int i2) {
        SparseArray sparseArray;
        WeakHashMap weakHashMap = this.f8176a;
        if (weakHashMap == null || (sparseArray = (SparseArray) weakHashMap.get(context)) == null) {
            return null;
        }
        return (ColorStateList) sparseArray.get(i2);
    }

    public final Drawable x(Context context, int i2) {
        int next;
        C0252a c0252a = this.f8177b;
        if (c0252a == null || c0252a.isEmpty()) {
            return null;
        }
        SparseArray sparseArray = this.f8178c;
        if (sparseArray != null) {
            String str = (String) sparseArray.get(i2);
            if ("appcompat_skip_skip".equals(str) || (str != null && this.f8177b.get(str) == null)) {
                return null;
            }
        } else {
            this.f8178c = new SparseArray();
        }
        if (this.f8181f == null) {
            this.f8181f = new TypedValue();
        }
        TypedValue typedValue = this.f8181f;
        Resources resources = context.getResources();
        resources.getValue(i2, typedValue, true);
        long jH = h(typedValue);
        Drawable drawableN = n(context, jH);
        if (drawableN != null) {
            return drawableN;
        }
        CharSequence charSequence = typedValue.string;
        if (charSequence != null && charSequence.toString().endsWith(".xml")) {
            try {
                XmlResourceParser xml = resources.getXml(i2);
                AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
                do {
                    next = xml.next();
                    if (next == 2) {
                        break;
                    }
                } while (next != 1);
                if (next != 2) {
                    throw new XmlPullParserException("No start tag found");
                }
                String name = xml.getName();
                this.f8178c.append(i2, name);
                c cVar = (c) this.f8177b.get(name);
                if (cVar != null) {
                    drawableN = cVar.a(context, xml, attributeSetAsAttributeSet, context.getTheme());
                }
                if (drawableN != null) {
                    drawableN.setChangingConfigurations(typedValue.changingConfigurations);
                    b(context, jH, drawableN);
                }
            } catch (Exception e2) {
                Log.e("AppCompatDrawableManager", "Exception while inflating drawable", e2);
            }
        }
        if (drawableN == null) {
            this.f8178c.append(i2, "appcompat_skip_skip");
        }
        return drawableN;
    }

    public void y(Context context) {
        synchronized (this.f8179d) {
            try {
                j.e eVar = (j.e) this.f8180e.get(context);
                if (eVar != null) {
                    eVar.a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public Drawable z(Context context, g0 g0Var, int i2) {
        Drawable drawableX = x(context, i2);
        if (drawableX == null) {
            drawableX = g0Var.b(i2);
        }
        if (drawableX != null) {
            return B(context, i2, false, drawableX);
        }
        return null;
    }
}

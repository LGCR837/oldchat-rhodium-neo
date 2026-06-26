package o0;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import p.AbstractC0460a;

/* JADX INFO: loaded from: classes.dex */
public abstract class X {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f7040a = {-15293622, -14326805, -1419252, -2349530, -15763858, -8627654};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f7041b = {-15368131, -14856488, -4046580, -4645860, -15638951, -10796502};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f7042c = {f0.j.i3, f0.j.g3, f0.j.j3, f0.j.k3, f0.j.l3, f0.j.h3};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f7043d = {f0.k.f5250g, f0.k.f5246c, f0.k.f5252i, f0.k.f5254k, f0.k.f5257n, f0.k.f5248e};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f7044e = {-15293622, -14326805, -1419252, -2349530, -15763858, -8627654, -14735049};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String[] f7045f = {"绿色", "蓝色", "橙色", "红色", "青绿", "棕色", "石墨灰"};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int[][] f7046g = {new int[]{-789258, -14012102}, new int[]{-1379841, -14405566}, new int[]{-1378322, -14468560}, new int[]{-2841, -12963290}, new int[]{-4882, -12899536}, new int[]{-856087, -13423062}};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String[] f7047h = {"灰白", "雾蓝", "薄荷", "暖米", "浅粉", "淡茶"};

    public static int a(int i2, float f2) {
        return f((int) ((i2 & 255) * f2)) | (((i2 >> 24) & 255) << 24) | (f((int) (((i2 >> 16) & 255) * f2)) << 16) | (f((int) (((i2 >> 8) & 255) * f2)) << 8);
    }

    public static boolean b(Context context, View view, int i2) {
        int iM;
        if (context == null || view == null) {
            return false;
        }
        if (i2 != f0.e.f5022i && i2 != f0.e.f5023j) {
            if ((i2 != f0.e.f5025l && i2 != f0.e.f5024k) || (iM = m(V.a(context))) == -1) {
                return false;
            }
            boolean zG = V.g(context);
            int[][] iArr = f7046g;
            int i3 = zG ? iArr[iM][1] : iArr[iM][0];
            view.setBackgroundDrawable(AbstractC0439e.b(context, i3, a(i3, zG ? 1.12f : 0.92f), false));
            return true;
        }
        int iN = n(V.b(context));
        if (iN != -1) {
            int i4 = f7044e[iN];
            view.setBackgroundDrawable(AbstractC0439e.b(context, i4, a(i4, 0.85f), true));
            return true;
        }
        int iO = o(V.e(context));
        if (iO == -1) {
            return false;
        }
        view.setBackgroundDrawable(AbstractC0439e.b(context, f7040a[iO], f7041b[iO], true));
        return true;
    }

    public static CharSequence[] c() {
        CharSequence[] charSequenceArr = new CharSequence[f7047h.length + 1];
        int i2 = 0;
        charSequenceArr[0] = "系统默认";
        while (true) {
            String[] strArr = f7047h;
            if (i2 >= strArr.length) {
                return charSequenceArr;
            }
            int i3 = i2 + 1;
            charSequenceArr[i3] = strArr[i2];
            i2 = i3;
        }
    }

    public static CharSequence[] d() {
        CharSequence[] charSequenceArr = new CharSequence[f7045f.length + 1];
        int i2 = 0;
        charSequenceArr[0] = "系统默认";
        while (true) {
            String[] strArr = f7045f;
            if (i2 >= strArr.length) {
                return charSequenceArr;
            }
            int i3 = i2 + 1;
            charSequenceArr[i3] = strArr[i2];
            i2 = i3;
        }
    }

    public static CharSequence[] e(Context context) {
        CharSequence[] charSequenceArr = new CharSequence[f7042c.length + 1];
        int i2 = 0;
        charSequenceArr[0] = "经典黑白";
        while (true) {
            int[] iArr = f7042c;
            if (i2 >= iArr.length) {
                return charSequenceArr;
            }
            int i3 = i2 + 1;
            charSequenceArr[i3] = context.getString(iArr[i2]);
            i2 = i3;
        }
    }

    public static int f(int i2) {
        if (i2 < 0) {
            return 0;
        }
        if (i2 > 255) {
            return 255;
        }
        return i2;
    }

    public static String g(Context context) {
        int iM = m(V.a(context));
        return iM == -1 ? "系统默认" : f7047h[iM];
    }

    public static String h(Context context) {
        int iN = n(V.b(context));
        return iN == -1 ? "系统默认" : f7045f[iN];
    }

    public static int i(Context context) {
        if (context == null) {
            return -15658735;
        }
        int iO = o(V.e(context));
        return iO == -1 ? context.getResources().getColor(f0.d.f4965b) : p(context, AbstractC0460a.f7727u, f7040a[iO]);
    }

    public static int j(Context context) {
        if (context == null) {
            return -16777216;
        }
        int iO = o(V.e(context));
        return iO == -1 ? context.getResources().getColor(f0.d.f4966c) : p(context, AbstractC0460a.f7728v, f7041b[iO]);
    }

    public static String k(Context context) {
        return l(context, V.e(context));
    }

    public static String l(Context context, int i2) {
        int iO = o(i2);
        return (iO == -1 || context == null) ? "经典黑白" : context.getString(f7042c[iO]);
    }

    public static int m(int i2) {
        if (i2 < 0 || i2 >= f7046g.length) {
            return -1;
        }
        return i2;
    }

    public static int n(int i2) {
        if (i2 < 0 || i2 >= f7044e.length) {
            return -1;
        }
        return i2;
    }

    public static int o(int i2) {
        if (i2 < 0 || i2 >= f7040a.length) {
            return -1;
        }
        return i2;
    }

    public static int p(Context context, int i2, int i3) {
        if (context == null) {
            return i3;
        }
        TypedValue typedValue = new TypedValue();
        if (context.getTheme() == null || !context.getTheme().resolveAttribute(i2, typedValue, true)) {
            return i3;
        }
        if (typedValue.resourceId != 0) {
            try {
                return context.getResources().getColor(typedValue.resourceId);
            } catch (Throwable unused) {
            }
        }
        return typedValue.data;
    }
}

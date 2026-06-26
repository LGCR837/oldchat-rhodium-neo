package o;

import android.R;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import j.C0252a;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import k.AbstractC0285k0;
import v.C0475d;
import x.C0502e;
import x.C0504g;
import x.C0505h;
import x.C0506i;
import x.C0509l;
import x.C0510m;
import x.C0513p;
import x.C0515s;
import x.C0516t;
import x.C0517u;
import x.C0519w;
import x.E;
import x.c0;

/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Class[] f6926b = {Context.class, AttributeSet.class};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f6927c = {R.attr.onClick};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String[] f6928d = {"android.widget.", "android.view.", "android.webkit."};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Map f6929e = new C0252a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f6930a = new Object[2];

    public static class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final View f6931a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f6932b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Method f6933c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Context f6934d;

        public a(View view, String str) {
            this.f6931a = view;
            this.f6932b = str;
        }

        public final void a(Context context, String str) {
            String str2;
            Method method;
            while (context != null) {
                try {
                    if (!context.isRestricted() && (method = context.getClass().getMethod(this.f6932b, View.class)) != null) {
                        this.f6933c = method;
                        this.f6934d = context;
                        return;
                    }
                } catch (NoSuchMethodException unused) {
                }
                context = context instanceof ContextWrapper ? ((ContextWrapper) context).getBaseContext() : null;
            }
            int id = this.f6931a.getId();
            if (id == -1) {
                str2 = "";
            } else {
                str2 = " with id '" + this.f6931a.getContext().getResources().getResourceEntryName(id) + "'";
            }
            throw new IllegalStateException("Could not find method " + this.f6932b + "(View) in a parent or ancestor Context for android:onClick attribute defined on view " + this.f6931a.getClass() + str2);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f6933c == null) {
                a(this.f6931a.getContext(), this.f6932b);
            }
            try {
                this.f6933c.invoke(this.f6934d, view);
            } catch (IllegalAccessException e2) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", e2);
            } catch (InvocationTargetException e3) {
                throw new IllegalStateException("Could not execute method for android:onClick", e3);
            }
        }
    }

    public static Context e(Context context, AttributeSet attributeSet, boolean z2, boolean z3) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, p.j.F2, 0, 0);
        int resourceId = z2 ? typedArrayObtainStyledAttributes.getResourceId(p.j.G2, 0) : 0;
        if (z3 && resourceId == 0 && (resourceId = typedArrayObtainStyledAttributes.getResourceId(p.j.H2, 0)) != 0) {
            Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
        }
        typedArrayObtainStyledAttributes.recycle();
        return resourceId != 0 ? ((context instanceof C0475d) && ((C0475d) context).a() == resourceId) ? context : new C0475d(context, resourceId) : context;
    }

    public final void a(View view, AttributeSet attributeSet) {
        Context context = view.getContext();
        if (context instanceof ContextWrapper) {
            if (Build.VERSION.SDK_INT < 15 || AbstractC0285k0.v(view)) {
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f6927c);
                String string = typedArrayObtainStyledAttributes.getString(0);
                if (string != null) {
                    view.setOnClickListener(new a(view, string));
                }
                typedArrayObtainStyledAttributes.recycle();
            }
        }
    }

    public final View b(Context context, String str, String str2) {
        String str3;
        Map map = f6929e;
        Constructor constructor = (Constructor) map.get(str);
        if (constructor == null) {
            try {
                ClassLoader classLoader = context.getClassLoader();
                if (str2 != null) {
                    str3 = str2 + str;
                } else {
                    str3 = str;
                }
                constructor = classLoader.loadClass(str3).asSubclass(View.class).getConstructor(f6926b);
                map.put(str, constructor);
            } catch (Exception unused) {
                return null;
            }
        }
        constructor.setAccessible(true);
        return (View) constructor.newInstance(this.f6930a);
    }

    public final View c(View view, String str, Context context, AttributeSet attributeSet, boolean z2, boolean z3, boolean z4, boolean z5) {
        Context context2;
        View c0516t;
        context2 = (!z2 || view == null) ? context : view.getContext();
        if (z3 || z4) {
            context2 = e(context2, attributeSet, z3, z4);
        }
        if (z5) {
            context2 = c0.b(context2);
        }
        str.hashCode();
        switch (str) {
            case "RatingBar":
                c0516t = new C0516t(context2, attributeSet);
                break;
            case "CheckedTextView":
                c0516t = new C0506i(context2, attributeSet);
                break;
            case "MultiAutoCompleteTextView":
                c0516t = new C0513p(context2, attributeSet);
                break;
            case "TextView":
                c0516t = new E(context2, attributeSet);
                break;
            case "ImageButton":
                c0516t = new C0510m(context2, attributeSet);
                break;
            case "SeekBar":
                c0516t = new C0517u(context2, attributeSet);
                break;
            case "Spinner":
                c0516t = new C0519w(context2, attributeSet);
                break;
            case "RadioButton":
                c0516t = new C0515s(context2, attributeSet);
                break;
            case "ImageView":
                c0516t = new AppCompatImageView(context2, attributeSet);
                break;
            case "AutoCompleteTextView":
                c0516t = new C0502e(context2, attributeSet);
                break;
            case "CheckBox":
                c0516t = new C0505h(context2, attributeSet);
                break;
            case "EditText":
                c0516t = new C0509l(context2, attributeSet);
                break;
            case "Button":
                c0516t = new C0504g(context2, attributeSet);
                break;
            default:
                c0516t = null;
                break;
        }
        if (c0516t == null && context != context2) {
            c0516t = d(context2, str, attributeSet);
        }
        if (c0516t != null) {
            a(c0516t, attributeSet);
        }
        return c0516t;
    }

    public final View d(Context context, String str, AttributeSet attributeSet) {
        if (str.equals("view")) {
            str = attributeSet.getAttributeValue(null, "class");
        }
        try {
            Object[] objArr = this.f6930a;
            objArr[0] = context;
            objArr[1] = attributeSet;
            if (-1 != str.indexOf(46)) {
                return b(context, str, null);
            }
            int i2 = 0;
            while (true) {
                String[] strArr = f6928d;
                if (i2 >= strArr.length) {
                    return null;
                }
                View viewB = b(context, str, strArr[i2]);
                if (viewB != null) {
                    return viewB;
                }
                i2++;
            }
        } catch (Exception unused) {
            return null;
        } finally {
            Object[] objArr2 = this.f6930a;
            objArr2[0] = null;
            objArr2[1] = null;
        }
    }
}

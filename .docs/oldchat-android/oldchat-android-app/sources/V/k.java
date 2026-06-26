package v;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import h.InterfaceMenuC0248a;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import k.AbstractC0299p;
import k.M;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import w.MenuItemC0484i;

/* JADX INFO: loaded from: classes.dex */
public class k extends MenuInflater {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Class[] f7940e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Class[] f7941f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f7942a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object[] f7943b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f7944c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f7945d;

    public static class a implements MenuItem.OnMenuItemClickListener {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final Class[] f7946c = {MenuItem.class};

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f7947a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Method f7948b;

        public a(Object obj, String str) {
            this.f7947a = obj;
            Class<?> cls = obj.getClass();
            try {
                this.f7948b = cls.getMethod(str, f7946c);
            } catch (Exception e2) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e2);
                throw inflateException;
            }
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.f7948b.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.f7948b.invoke(this.f7947a, menuItem)).booleanValue();
                }
                this.f7948b.invoke(this.f7947a, menuItem);
                return true;
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    public class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Menu f7949a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f7950b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f7951c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f7952d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f7953e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f7954f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f7955g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f7956h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f7957i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f7958j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public CharSequence f7959k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public CharSequence f7960l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public int f7961m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public char f7962n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public char f7963o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public int f7964p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public boolean f7965q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public boolean f7966r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public boolean f7967s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public int f7968t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public int f7969u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public String f7970v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public String f7971w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public String f7972x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public AbstractC0299p f7973y;

        public b(Menu menu) {
            this.f7949a = menu;
            h();
        }

        public void a() {
            this.f7956h = true;
            i(this.f7949a.add(this.f7950b, this.f7957i, this.f7958j, this.f7959k));
        }

        public SubMenu b() {
            this.f7956h = true;
            SubMenu subMenuAddSubMenu = this.f7949a.addSubMenu(this.f7950b, this.f7957i, this.f7958j, this.f7959k);
            i(subMenuAddSubMenu.getItem());
            return subMenuAddSubMenu;
        }

        public final char c(String str) {
            if (str == null) {
                return (char) 0;
            }
            return str.charAt(0);
        }

        public boolean d() {
            return this.f7956h;
        }

        public final Object e(String str, Class[] clsArr, Object[] objArr) {
            try {
                Constructor<?> constructor = k.this.f7944c.getClassLoader().loadClass(str).getConstructor(clsArr);
                constructor.setAccessible(true);
                return constructor.newInstance(objArr);
            } catch (Exception e2) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e2);
                return null;
            }
        }

        public void f(AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = k.this.f7944c.obtainStyledAttributes(attributeSet, p.j.R0);
            this.f7950b = typedArrayObtainStyledAttributes.getResourceId(p.j.T0, 0);
            this.f7951c = typedArrayObtainStyledAttributes.getInt(p.j.V0, 0);
            this.f7952d = typedArrayObtainStyledAttributes.getInt(p.j.W0, 0);
            this.f7953e = typedArrayObtainStyledAttributes.getInt(p.j.X0, 0);
            this.f7954f = typedArrayObtainStyledAttributes.getBoolean(p.j.U0, true);
            this.f7955g = typedArrayObtainStyledAttributes.getBoolean(p.j.S0, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        public void g(AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = k.this.f7944c.obtainStyledAttributes(attributeSet, p.j.Y0);
            this.f7957i = typedArrayObtainStyledAttributes.getResourceId(p.j.b1, 0);
            this.f7958j = (typedArrayObtainStyledAttributes.getInt(p.j.e1, this.f7951c) & (-65536)) | (typedArrayObtainStyledAttributes.getInt(p.j.f1, this.f7952d) & 65535);
            this.f7959k = typedArrayObtainStyledAttributes.getText(p.j.g1);
            this.f7960l = typedArrayObtainStyledAttributes.getText(p.j.h1);
            this.f7961m = typedArrayObtainStyledAttributes.getResourceId(p.j.Z0, 0);
            this.f7962n = c(typedArrayObtainStyledAttributes.getString(p.j.i1));
            this.f7963o = c(typedArrayObtainStyledAttributes.getString(p.j.j1));
            if (typedArrayObtainStyledAttributes.hasValue(p.j.k1)) {
                this.f7964p = typedArrayObtainStyledAttributes.getBoolean(p.j.k1, false) ? 1 : 0;
            } else {
                this.f7964p = this.f7953e;
            }
            this.f7965q = typedArrayObtainStyledAttributes.getBoolean(p.j.c1, false);
            this.f7966r = typedArrayObtainStyledAttributes.getBoolean(p.j.d1, this.f7954f);
            this.f7967s = typedArrayObtainStyledAttributes.getBoolean(p.j.a1, this.f7955g);
            this.f7968t = typedArrayObtainStyledAttributes.getInt(p.j.p1, -1);
            this.f7972x = typedArrayObtainStyledAttributes.getString(p.j.l1);
            this.f7969u = typedArrayObtainStyledAttributes.getResourceId(p.j.m1, 0);
            this.f7970v = typedArrayObtainStyledAttributes.getString(p.j.o1);
            String string = typedArrayObtainStyledAttributes.getString(p.j.n1);
            this.f7971w = string;
            boolean z2 = string != null;
            if (z2 && this.f7969u == 0 && this.f7970v == null) {
                this.f7973y = (AbstractC0299p) e(string, k.f7941f, k.this.f7943b);
            } else {
                if (z2) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.f7973y = null;
            }
            typedArrayObtainStyledAttributes.recycle();
            this.f7956h = false;
        }

        public void h() {
            this.f7950b = 0;
            this.f7951c = 0;
            this.f7952d = 0;
            this.f7953e = 0;
            this.f7954f = true;
            this.f7955g = true;
        }

        public final void i(MenuItem menuItem) {
            boolean z2 = false;
            menuItem.setChecked(this.f7965q).setVisible(this.f7966r).setEnabled(this.f7967s).setCheckable(this.f7964p >= 1).setTitleCondensed(this.f7960l).setIcon(this.f7961m).setAlphabeticShortcut(this.f7962n).setNumericShortcut(this.f7963o);
            int i2 = this.f7968t;
            if (i2 >= 0) {
                M.g(menuItem, i2);
            }
            if (this.f7972x != null) {
                if (k.this.f7944c.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                menuItem.setOnMenuItemClickListener(new a(k.this.b(), this.f7972x));
            }
            boolean z3 = menuItem instanceof android.support.v7.view.menu.g;
            if (z3) {
            }
            if (this.f7964p >= 2) {
                if (z3) {
                    ((android.support.v7.view.menu.g) menuItem).s(true);
                } else if (menuItem instanceof MenuItemC0484i) {
                    ((MenuItemC0484i) menuItem).l(true);
                }
            }
            String str = this.f7970v;
            if (str != null) {
                M.f(menuItem, (View) e(str, k.f7940e, k.this.f7942a));
                z2 = true;
            }
            int i3 = this.f7969u;
            if (i3 > 0) {
                if (z2) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                } else {
                    M.e(menuItem, i3);
                }
            }
            AbstractC0299p abstractC0299p = this.f7973y;
            if (abstractC0299p != null) {
                M.d(menuItem, abstractC0299p);
            }
        }
    }

    static {
        Class[] clsArr = {Context.class};
        f7940e = clsArr;
        f7941f = clsArr;
    }

    public k(Context context) {
        super(context);
        this.f7944c = context;
        Object[] objArr = {context};
        this.f7942a = objArr;
        this.f7943b = objArr;
    }

    public final Object a(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? a(((ContextWrapper) obj).getBaseContext()) : obj;
    }

    public Object b() {
        if (this.f7945d == null) {
            this.f7945d = a(this.f7944c);
        }
        return this.f7945d;
    }

    public final void c(XmlPullParser xmlPullParser, AttributeSet attributeSet, Menu menu) throws XmlPullParserException, IOException {
        b bVar = new b(menu);
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if (!name.equals("menu")) {
                    throw new RuntimeException("Expecting menu, got " + name);
                }
                eventType = xmlPullParser.next();
            } else {
                eventType = xmlPullParser.next();
                if (eventType == 1) {
                    break;
                }
            }
        }
        String str = null;
        boolean z2 = false;
        boolean z3 = false;
        while (!z2) {
            if (eventType == 1) {
                throw new RuntimeException("Unexpected end of document");
            }
            if (eventType != 2) {
                if (eventType == 3) {
                    String name2 = xmlPullParser.getName();
                    if (z3 && name2.equals(str)) {
                        str = null;
                        z3 = false;
                    } else if (name2.equals("group")) {
                        bVar.h();
                    } else if (name2.equals("item")) {
                        if (!bVar.d()) {
                            AbstractC0299p abstractC0299p = bVar.f7973y;
                            if (abstractC0299p == null || !abstractC0299p.a()) {
                                bVar.a();
                            } else {
                                bVar.b();
                            }
                        }
                    } else if (name2.equals("menu")) {
                        z2 = true;
                    }
                }
            } else if (!z3) {
                String name3 = xmlPullParser.getName();
                if (name3.equals("group")) {
                    bVar.f(attributeSet);
                } else if (name3.equals("item")) {
                    bVar.g(attributeSet);
                } else if (name3.equals("menu")) {
                    c(xmlPullParser, attributeSet, bVar.b());
                } else {
                    str = name3;
                    z3 = true;
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    @Override // android.view.MenuInflater
    public void inflate(int i2, Menu menu) {
        if (!(menu instanceof InterfaceMenuC0248a)) {
            super.inflate(i2, menu);
            return;
        }
        XmlResourceParser layout = null;
        try {
            try {
                try {
                    layout = this.f7944c.getResources().getLayout(i2);
                    c(layout, Xml.asAttributeSet(layout), menu);
                } catch (XmlPullParserException e2) {
                    throw new InflateException("Error inflating menu XML", e2);
                }
            } catch (IOException e3) {
                throw new InflateException("Error inflating menu XML", e3);
            }
        } finally {
            if (layout != null) {
                layout.close();
            }
        }
    }
}

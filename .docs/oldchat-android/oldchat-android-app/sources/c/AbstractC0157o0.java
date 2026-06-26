package c;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;
import c.A1;
import c.Y0;
import c.m1;
import c.y1;
import i.AbstractC0250a;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: renamed from: c.o0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0157o0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f f1925a;

    /* JADX INFO: renamed from: c.o0$a */
    public static class a extends m1.a {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final m1.a.InterfaceC0019a f1926g = new C0020a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Bundle f1927a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final B1[] f1928b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1929c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1930d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public CharSequence f1931e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public PendingIntent f1932f;

        /* JADX INFO: renamed from: c.o0$a$a, reason: collision with other inner class name */
        public static class C0020a implements m1.a.InterfaceC0019a {
        }

        public a(int i2, CharSequence charSequence, PendingIntent pendingIntent) {
            this(i2, charSequence, pendingIntent, new Bundle(), null, false);
        }

        @Override // c.m1.a
        public PendingIntent a() {
            return this.f1932f;
        }

        @Override // c.m1.a
        public boolean b() {
            return this.f1929c;
        }

        @Override // c.m1.a
        public Bundle c() {
            return this.f1927a;
        }

        @Override // c.m1.a
        public int d() {
            return this.f1930d;
        }

        @Override // c.m1.a
        public CharSequence f() {
            return this.f1931e;
        }

        @Override // c.m1.a
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public B1[] e() {
            return this.f1928b;
        }

        public a(int i2, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, B1[] b1Arr, boolean z2) {
            this.f1929c = false;
            this.f1930d = i2;
            this.f1931e = d.d(charSequence);
            this.f1932f = pendingIntent;
            this.f1927a = bundle == null ? new Bundle() : bundle;
            this.f1928b = b1Arr;
            this.f1929c = z2;
        }
    }

    /* JADX INFO: renamed from: c.o0$b */
    public static class b extends o {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Bitmap f1933e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Bitmap f1934f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f1935g;

        public b c(Bitmap bitmap) {
            this.f1933e = bitmap;
            return this;
        }

        public b d(CharSequence charSequence) {
            this.f1972c = d.d(charSequence);
            this.f1973d = true;
            return this;
        }
    }

    /* JADX INFO: renamed from: c.o0$c */
    public static class c extends o {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public CharSequence f1936e;

        public c c(CharSequence charSequence) {
            this.f1936e = d.d(charSequence);
            return this;
        }
    }

    /* JADX INFO: renamed from: c.o0$d */
    public static class d {

        /* JADX INFO: renamed from: B, reason: collision with root package name */
        public Notification f1938B;

        /* JADX INFO: renamed from: C, reason: collision with root package name */
        public RemoteViews f1939C;

        /* JADX INFO: renamed from: D, reason: collision with root package name */
        public RemoteViews f1940D;

        /* JADX INFO: renamed from: E, reason: collision with root package name */
        public RemoteViews f1941E;

        /* JADX INFO: renamed from: F, reason: collision with root package name */
        public Notification f1942F;

        /* JADX INFO: renamed from: G, reason: collision with root package name */
        public ArrayList f1943G;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Context f1944a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public CharSequence f1945b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public CharSequence f1946c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public PendingIntent f1947d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public PendingIntent f1948e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public RemoteViews f1949f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public Bitmap f1950g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public CharSequence f1951h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f1952i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f1953j;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public boolean f1955l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public o f1956m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public CharSequence f1957n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public CharSequence[] f1958o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public int f1959p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f1960q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public boolean f1961r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public String f1962s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public boolean f1963t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public String f1964u;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public String f1967x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public Bundle f1968y;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public boolean f1954k = true;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public ArrayList f1965v = new ArrayList();

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public boolean f1966w = false;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public int f1969z = 0;

        /* JADX INFO: renamed from: A, reason: collision with root package name */
        public int f1937A = 0;

        public d(Context context) {
            Notification notification = new Notification();
            this.f1942F = notification;
            this.f1944a = context;
            notification.when = System.currentTimeMillis();
            this.f1942F.audioStreamType = -1;
            this.f1953j = 0;
            this.f1943G = new ArrayList();
        }

        public static CharSequence d(CharSequence charSequence) {
            return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
        }

        public d a(int i2, CharSequence charSequence, PendingIntent pendingIntent) {
            this.f1965v.add(new a(i2, charSequence, pendingIntent));
            return this;
        }

        public Notification b() {
            return AbstractC0157o0.f1925a.a(this, c());
        }

        public e c() {
            return new e();
        }

        public d e(boolean z2) {
            i(16, z2);
            return this;
        }

        public d f(PendingIntent pendingIntent) {
            this.f1947d = pendingIntent;
            return this;
        }

        public d g(CharSequence charSequence) {
            this.f1946c = d(charSequence);
            return this;
        }

        public d h(CharSequence charSequence) {
            this.f1945b = d(charSequence);
            return this;
        }

        public final void i(int i2, boolean z2) {
            if (z2) {
                Notification notification = this.f1942F;
                notification.flags = i2 | notification.flags;
            } else {
                Notification notification2 = this.f1942F;
                notification2.flags = (i2 ^ (-1)) & notification2.flags;
            }
        }

        public d j(boolean z2) {
            i(2, z2);
            return this;
        }

        public d k(boolean z2) {
            i(8, z2);
            return this;
        }

        public d l(int i2) {
            this.f1953j = i2;
            return this;
        }

        public d m(int i2, int i3, boolean z2) {
            this.f1959p = i2;
            this.f1960q = i3;
            this.f1961r = z2;
            return this;
        }

        public d n(int i2) {
            this.f1942F.icon = i2;
            return this;
        }

        public d o(o oVar) {
            if (this.f1956m != oVar) {
                this.f1956m = oVar;
                if (oVar != null) {
                    oVar.b(this);
                }
            }
            return this;
        }
    }

    /* JADX INFO: renamed from: c.o0$e */
    public static class e {
        public Notification a(d dVar, InterfaceC0155n0 interfaceC0155n0) {
            return interfaceC0155n0.a();
        }
    }

    /* JADX INFO: renamed from: c.o0$f */
    public interface f {
        Notification a(d dVar, e eVar);
    }

    /* JADX INFO: renamed from: c.o0$g */
    public static class g extends n {
        @Override // c.AbstractC0157o0.n, c.AbstractC0157o0.m, c.AbstractC0157o0.j, c.AbstractC0157o0.f
        public Notification a(d dVar, e eVar) {
            Y0.a aVar = new Y0.a(dVar.f1944a, dVar.f1942F, dVar.f1945b, dVar.f1946c, dVar.f1951h, dVar.f1949f, dVar.f1952i, dVar.f1947d, dVar.f1948e, dVar.f1950g, dVar.f1959p, dVar.f1960q, dVar.f1961r, dVar.f1954k, dVar.f1955l, dVar.f1953j, dVar.f1957n, dVar.f1966w, dVar.f1943G, dVar.f1968y, dVar.f1962s, dVar.f1963t, dVar.f1964u, dVar.f1939C, dVar.f1940D);
            AbstractC0157o0.a(aVar, dVar.f1965v);
            AbstractC0157o0.c(aVar, dVar.f1956m);
            Notification notificationA = eVar.a(dVar, aVar);
            o oVar = dVar.f1956m;
            if (oVar != null) {
                oVar.a(b(notificationA));
            }
            return notificationA;
        }
    }

    /* JADX INFO: renamed from: c.o0$h */
    public static class h extends g {
        @Override // c.AbstractC0157o0.g, c.AbstractC0157o0.n, c.AbstractC0157o0.m, c.AbstractC0157o0.j, c.AbstractC0157o0.f
        public Notification a(d dVar, e eVar) {
            C0138f1 c0138f1 = new C0138f1(dVar.f1944a, dVar.f1942F, dVar.f1945b, dVar.f1946c, dVar.f1951h, dVar.f1949f, dVar.f1952i, dVar.f1947d, dVar.f1948e, dVar.f1950g, dVar.f1959p, dVar.f1960q, dVar.f1961r, dVar.f1954k, dVar.f1955l, dVar.f1953j, dVar.f1957n, dVar.f1966w, dVar.f1967x, dVar.f1943G, dVar.f1968y, dVar.f1969z, dVar.f1937A, dVar.f1938B, dVar.f1962s, dVar.f1963t, dVar.f1964u, dVar.f1939C, dVar.f1940D, dVar.f1941E);
            AbstractC0157o0.a(c0138f1, dVar.f1965v);
            AbstractC0157o0.c(c0138f1, dVar.f1956m);
            Notification notificationA = eVar.a(dVar, c0138f1);
            o oVar = dVar.f1956m;
            if (oVar != null) {
                oVar.a(b(notificationA));
            }
            return notificationA;
        }
    }

    /* JADX INFO: renamed from: c.o0$i */
    public static class i extends h {
        @Override // c.AbstractC0157o0.h, c.AbstractC0157o0.g, c.AbstractC0157o0.n, c.AbstractC0157o0.m, c.AbstractC0157o0.j, c.AbstractC0157o0.f
        public Notification a(d dVar, e eVar) {
            l1 l1Var = new l1(dVar.f1944a, dVar.f1942F, dVar.f1945b, dVar.f1946c, dVar.f1951h, dVar.f1949f, dVar.f1952i, dVar.f1947d, dVar.f1948e, dVar.f1950g, dVar.f1959p, dVar.f1960q, dVar.f1961r, dVar.f1954k, dVar.f1955l, dVar.f1953j, dVar.f1957n, dVar.f1966w, dVar.f1967x, dVar.f1943G, dVar.f1968y, dVar.f1969z, dVar.f1937A, dVar.f1938B, dVar.f1962s, dVar.f1963t, dVar.f1964u, dVar.f1958o, dVar.f1939C, dVar.f1940D, dVar.f1941E);
            AbstractC0157o0.a(l1Var, dVar.f1965v);
            AbstractC0157o0.b(l1Var, dVar.f1956m);
            Notification notificationA = eVar.a(dVar, l1Var);
            o oVar = dVar.f1956m;
            if (oVar != null) {
                oVar.a(b(notificationA));
            }
            return notificationA;
        }
    }

    /* JADX INFO: renamed from: c.o0$j */
    public static class j implements f {
        @Override // c.AbstractC0157o0.f
        public Notification a(d dVar, e eVar) {
            Notification notificationA = m1.a(dVar.f1942F, dVar.f1944a, dVar.f1945b, dVar.f1946c, dVar.f1947d, dVar.f1948e);
            if (dVar.f1953j > 0) {
                notificationA.flags |= 128;
            }
            RemoteViews remoteViews = dVar.f1939C;
            if (remoteViews != null) {
                notificationA.contentView = remoteViews;
            }
            return notificationA;
        }
    }

    /* JADX INFO: renamed from: c.o0$k */
    public static class k extends j {
        @Override // c.AbstractC0157o0.j, c.AbstractC0157o0.f
        public Notification a(d dVar, e eVar) {
            Notification notificationA = o1.a(dVar.f1944a, dVar.f1942F, dVar.f1945b, dVar.f1946c, dVar.f1951h, dVar.f1949f, dVar.f1952i, dVar.f1947d, dVar.f1948e, dVar.f1950g);
            RemoteViews remoteViews = dVar.f1939C;
            if (remoteViews != null) {
                notificationA.contentView = remoteViews;
            }
            return notificationA;
        }
    }

    /* JADX INFO: renamed from: c.o0$l */
    public static class l extends j {
        @Override // c.AbstractC0157o0.j, c.AbstractC0157o0.f
        public Notification a(d dVar, e eVar) {
            Notification notificationA = eVar.a(dVar, new p1(dVar.f1944a, dVar.f1942F, dVar.f1945b, dVar.f1946c, dVar.f1951h, dVar.f1949f, dVar.f1952i, dVar.f1947d, dVar.f1948e, dVar.f1950g, dVar.f1959p, dVar.f1960q, dVar.f1961r));
            RemoteViews remoteViews = dVar.f1939C;
            if (remoteViews != null) {
                notificationA.contentView = remoteViews;
            }
            return notificationA;
        }
    }

    /* JADX INFO: renamed from: c.o0$m */
    public static class m extends j {
        @Override // c.AbstractC0157o0.j, c.AbstractC0157o0.f
        public Notification a(d dVar, e eVar) {
            y1.a aVar = new y1.a(dVar.f1944a, dVar.f1942F, dVar.f1945b, dVar.f1946c, dVar.f1951h, dVar.f1949f, dVar.f1952i, dVar.f1947d, dVar.f1948e, dVar.f1950g, dVar.f1959p, dVar.f1960q, dVar.f1961r, dVar.f1955l, dVar.f1953j, dVar.f1957n, dVar.f1966w, dVar.f1968y, dVar.f1962s, dVar.f1963t, dVar.f1964u, dVar.f1939C, dVar.f1940D);
            AbstractC0157o0.a(aVar, dVar.f1965v);
            AbstractC0157o0.c(aVar, dVar.f1956m);
            Notification notificationA = eVar.a(dVar, aVar);
            o oVar = dVar.f1956m;
            if (oVar != null) {
                oVar.a(b(notificationA));
            }
            return notificationA;
        }

        public Bundle b(Notification notification) {
            return y1.d(notification);
        }
    }

    /* JADX INFO: renamed from: c.o0$n */
    public static class n extends m {
        @Override // c.AbstractC0157o0.m, c.AbstractC0157o0.j, c.AbstractC0157o0.f
        public Notification a(d dVar, e eVar) {
            A1.a aVar = new A1.a(dVar.f1944a, dVar.f1942F, dVar.f1945b, dVar.f1946c, dVar.f1951h, dVar.f1949f, dVar.f1952i, dVar.f1947d, dVar.f1948e, dVar.f1950g, dVar.f1959p, dVar.f1960q, dVar.f1961r, dVar.f1954k, dVar.f1955l, dVar.f1953j, dVar.f1957n, dVar.f1966w, dVar.f1943G, dVar.f1968y, dVar.f1962s, dVar.f1963t, dVar.f1964u, dVar.f1939C, dVar.f1940D);
            AbstractC0157o0.a(aVar, dVar.f1965v);
            AbstractC0157o0.c(aVar, dVar.f1956m);
            return eVar.a(dVar, aVar);
        }

        @Override // c.AbstractC0157o0.m
        public Bundle b(Notification notification) {
            return A1.a(notification);
        }
    }

    static {
        if (AbstractC0250a.a()) {
            f1925a = new i();
            return;
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            f1925a = new h();
            return;
        }
        if (i2 >= 20) {
            f1925a = new g();
            return;
        }
        if (i2 >= 19) {
            f1925a = new n();
            return;
        }
        if (i2 >= 16) {
            f1925a = new m();
            return;
        }
        if (i2 >= 14) {
            f1925a = new l();
        } else if (i2 >= 11) {
            f1925a = new k();
        } else {
            f1925a = new j();
        }
    }

    public static void a(InterfaceC0153m0 interfaceC0153m0, ArrayList arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            interfaceC0153m0.c((a) it.next());
        }
    }

    public static void b(InterfaceC0155n0 interfaceC0155n0, o oVar) {
        if (oVar != null) {
            c(interfaceC0155n0, oVar);
        }
    }

    public static void c(InterfaceC0155n0 interfaceC0155n0, o oVar) {
        if (oVar != null) {
            if (oVar instanceof c) {
                c cVar = (c) oVar;
                y1.b(interfaceC0155n0, cVar.f1971b, cVar.f1973d, cVar.f1972c, cVar.f1936e);
            } else if (oVar instanceof b) {
                b bVar = (b) oVar;
                y1.a(interfaceC0155n0, bVar.f1971b, bVar.f1973d, bVar.f1972c, bVar.f1933e, bVar.f1934f, bVar.f1935g);
            }
        }
    }

    /* JADX INFO: renamed from: c.o0$o */
    public static abstract class o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public d f1970a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public CharSequence f1971b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public CharSequence f1972c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1973d = false;

        public void b(d dVar) {
            if (this.f1970a != dVar) {
                this.f1970a = dVar;
                if (dVar != null) {
                    dVar.o(this);
                }
            }
        }

        public void a(Bundle bundle) {
        }
    }
}

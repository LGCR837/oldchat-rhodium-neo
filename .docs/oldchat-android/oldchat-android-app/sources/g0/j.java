package g0;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.im.oldchat.OldChatApplication;
import g0.d;
import g0.g;
import g0.i;
import g0.q;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import o0.AbstractC0443i;
import o0.G;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static j f5374o;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public g0.g f5382h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Set f5375a = new CopyOnWriteArraySet();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Handler f5376b = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final q f5377c = new q();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final g0.i f5378d = new g0.i();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final i.a f5379e = new a();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final q.a f5380f = new b();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final q.b f5381g = new c();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f5383i = false;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f5384j = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f5385k = false;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f5386l = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f5387m = false;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f5388n = 0;

    public class a implements i.a {
        public a() {
        }

        @Override // g0.i.a
        public void a(m mVar) {
            g0.h.c(j.this.f5376b, j.this.f5375a, mVar);
        }

        @Override // g0.i.a
        public void b(l lVar) {
            g0.h.a(j.this.f5376b, j.this.f5375a, lVar);
        }

        @Override // g0.i.a
        public void c(String str, String str2, long j2) {
            g0.h.b(j.this.f5376b, j.this.f5375a, str, str2, j2);
        }

        @Override // g0.i.a
        public void d(o oVar) {
            g0.h.e(j.this.f5376b, j.this.f5375a, oVar);
        }

        @Override // g0.i.a
        public void e(p pVar) {
            g0.h.f(j.this.f5376b, j.this.f5375a, pVar);
        }

        @Override // g0.i.a
        public void f(n nVar) {
            g0.h.d(j.this.f5376b, j.this.f5375a, nVar);
        }
    }

    public class b implements q.a {
        public b() {
        }

        @Override // g0.q.a
        public void a(l lVar) {
            g0.h.a(j.this.f5376b, j.this.f5375a, lVar);
        }
    }

    public class c implements q.b {
        public c() {
        }

        @Override // g0.q.b
        public void a(n nVar) {
            g0.h.d(j.this.f5376b, j.this.f5375a, nVar);
        }
    }

    public class d implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f5392a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f5393b;

        public d(Context context, String str) {
            this.f5392a = context;
            this.f5393b = str;
        }

        @Override // g0.d.i
        public void b(String str) {
            j.this.f5386l = false;
            String strV = j.this.v(this.f5392a);
            if (strV == null || strV.isEmpty()) {
                return;
            }
            j.this.C(this.f5392a, strV);
            j.this.B(this.f5392a, strV);
            j.this.t(strV);
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            j.this.f5386l = false;
            j.this.C(this.f5392a, this.f5393b);
            j.this.B(this.f5392a, this.f5393b);
            j.this.t(this.f5393b);
        }
    }

    public class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f5395a;

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ boolean f5397a;

            public a(boolean z2) {
                this.f5397a = z2;
            }

            @Override // java.lang.Runnable
            public void run() {
                j.this.f5383i = false;
                if (!this.f5397a) {
                    j.this.y();
                } else {
                    e eVar = e.this;
                    j.this.t(eVar.f5395a);
                }
            }
        }

        public e(String str) {
            this.f5395a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            j.this.f5376b.post(new a(g0.e.f()));
        }
    }

    public class f implements g.b {

        public class a implements Runnable {

            /* JADX INFO: renamed from: g0.j$f$a$a, reason: collision with other inner class name */
            public class RunnableC0041a implements Runnable {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public final /* synthetic */ String f5401a;

                public RunnableC0041a(String str) {
                    this.f5401a = str;
                }

                @Override // java.lang.Runnable
                public void run() {
                    j.this.f5387m = false;
                    String str = this.f5401a;
                    if (str == null || str.isEmpty()) {
                        j.this.y();
                    } else {
                        j.this.f5388n = 0;
                        j.this.t(this.f5401a);
                    }
                }
            }

            public a() {
            }

            @Override // java.lang.Runnable
            public void run() throws Throwable {
                j.this.f5376b.post(new RunnableC0041a(g0.a.e()));
            }
        }

        public f() {
        }

        @Override // g0.g.b
        public void a(Exception exc) {
            j.this.f5384j = false;
            j.this.f5383i = false;
            AbstractC0443i.g();
            G.d().h(-1);
            g0.h.g(j.this.f5376b, j.this.f5375a, false);
            String message = exc.getMessage();
            if (message == null || !(message.contains("403") || message.contains("401"))) {
                j.this.y();
            } else {
                j.this.f5387m = true;
                new Thread(new a(), "ws-auth-refresh").start();
            }
        }

        @Override // g0.g.b
        public void b() {
            String strV;
            j.this.f5383i = false;
            j.this.f5384j = true;
            j.this.f5388n = 0;
            G.d().i();
            g0.h.g(j.this.f5376b, j.this.f5375a, true);
            if (j.this.f5385k) {
                Context contextA = OldChatApplication.a();
                if (contextA != null && (strV = j.this.v(contextA)) != null) {
                    j.this.C(contextA, strV);
                    j.this.B(contextA, strV);
                }
                j.this.f5385k = false;
            }
        }

        @Override // g0.g.b
        public void c(int i2, String str) {
            j.this.f5384j = false;
            j.this.f5383i = false;
            AbstractC0443i.g();
            G.d().h(-1);
            g0.h.g(j.this.f5376b, j.this.f5375a, false);
            if (j.this.f5387m) {
                return;
            }
            j.this.y();
        }

        @Override // g0.g.b
        public void d(String str) {
            j.this.f5378d.b(OldChatApplication.a(), str, j.this.f5379e);
        }
    }

    public class i implements Runnable {
        public i() {
        }

        @Override // java.lang.Runnable
        public void run() {
            String strV;
            Context contextA = OldChatApplication.a();
            if (contextA == null || (strV = j.this.v(contextA)) == null || strV.isEmpty()) {
                return;
            }
            j.this.t(strV);
        }
    }

    /* JADX INFO: renamed from: g0.j$j, reason: collision with other inner class name */
    public interface InterfaceC0042j {
        void a(m mVar);

        void b(l lVar);

        void c(String str, String str2, long j2);

        void d(o oVar);

        void e(p pVar);

        void f(n nVar);

        void g(boolean z2);
    }

    public static synchronized j u() {
        try {
            if (f5374o == null) {
                f5374o = new j();
            }
        } catch (Throwable th) {
            throw th;
        }
        return f5374o;
    }

    public void A() {
        g0.g gVar = this.f5382h;
        if (gVar != null) {
            gVar.b();
            this.f5382h = null;
        }
        this.f5384j = false;
        this.f5383i = false;
    }

    public void B(Context context, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("limit", 50);
            g0.d.S("/groups/unread", jSONObject, str, new h(context == null ? null : context.getApplicationContext()));
        } catch (Exception unused) {
        }
    }

    public void C(Context context, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("limit", 50);
            g0.d.S("/direct/unread", jSONObject, str, new g(context == null ? null : context.getApplicationContext()));
        } catch (Exception unused) {
        }
    }

    public void r(InterfaceC0042j interfaceC0042j) {
        if (interfaceC0042j != null) {
            this.f5375a.add(interfaceC0042j);
        }
    }

    public final String s(String str) {
        String str2 = g0.e.f5335a;
        if (str2.startsWith("https://")) {
            str2 = "wss://" + str2.substring(8);
        } else if (str2.startsWith("http://")) {
            str2 = "ws://" + str2.substring(7);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("token=");
        sb.append(URLEncoder.encode(str, "UTF-8"));
        String strO = AbstractC0443i.o();
        if (strO != null && strO.length() > 0) {
            sb.append("&sid=");
            sb.append(URLEncoder.encode(strO, "UTF-8"));
        }
        return str2 + "/ws?" + ((Object) sb);
    }

    public void t(String str) {
        if (str == null || str.isEmpty() || this.f5384j || this.f5383i) {
            return;
        }
        if (AbstractC0443i.s() && !AbstractC0443i.q()) {
            this.f5383i = true;
            new Thread(new e(str), "ws-handshake").start();
            return;
        }
        this.f5383i = true;
        try {
            g0.g gVar = new g0.g(new URI(s(str)), new f());
            this.f5382h = gVar;
            gVar.e();
        } catch (Exception unused) {
            this.f5383i = false;
        }
    }

    public final String v(Context context) {
        return context.getSharedPreferences("auth", 0).getString("access_token", "");
    }

    public boolean w() {
        return this.f5384j;
    }

    public void x(InterfaceC0042j interfaceC0042j) {
        this.f5375a.remove(interfaceC0042j);
    }

    public final void y() {
        this.f5385k = true;
        long jMin = Math.min((1 << this.f5388n) * 1000, 60000L);
        this.f5388n++;
        this.f5376b.postDelayed(new i(), jMin);
    }

    public void z(Context context) {
        Context applicationContext;
        String strV;
        if (context == null || (strV = v((applicationContext = context.getApplicationContext()))) == null || strV.isEmpty()) {
            return;
        }
        if (this.f5384j || this.f5383i) {
            C(applicationContext, strV);
            B(applicationContext, strV);
        } else {
            if (this.f5386l) {
                return;
            }
            this.f5386l = true;
            g0.d.R("/me", strV, new d(applicationContext, strV));
        }
    }

    public class g implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f5403a;

        public g(Context context) {
            this.f5403a = context;
        }

        @Override // g0.d.i
        public void b(String str) {
            j.this.f5377c.b(this.f5403a, str, j.this.f5380f);
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
        }
    }

    public class h implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f5405a;

        public h(Context context) {
            this.f5405a = context;
        }

        @Override // g0.d.i
        public void b(String str) {
            j.this.f5377c.c(this.f5405a, str, j.this.f5381g);
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
        }
    }
}

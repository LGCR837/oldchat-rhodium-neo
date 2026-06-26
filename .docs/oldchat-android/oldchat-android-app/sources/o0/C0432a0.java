package o0;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import g0.d;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: renamed from: o0.a0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0432a0 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static C0432a0 f7054g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map f7055a = new ConcurrentHashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map f7056b = new ConcurrentHashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Handler f7057c = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Map f7058d = new ConcurrentHashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Map f7059e = new ConcurrentHashMap();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Map f7060f = new ConcurrentHashMap();

    /* JADX INFO: renamed from: o0.a0$a */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f7061a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f7062b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f7063c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ boolean f7064d;

        public a(Context context, String str, String str2, boolean z2) {
            this.f7061a = context;
            this.f7062b = str;
            this.f7063c = str2;
            this.f7064d = z2;
        }

        @Override // java.lang.Runnable
        public void run() {
            C0432a0.this.r(this.f7061a, this.f7062b, this.f7063c, true, this.f7064d);
            C0432a0.this.f7057c.postDelayed(this, 3000L);
        }
    }

    /* JADX INFO: renamed from: o0.a0$b */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f7066a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f7067b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f7068c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ boolean f7069d;

        public b(Context context, String str, String str2, boolean z2) {
            this.f7066a = context;
            this.f7067b = str;
            this.f7068c = str2;
            this.f7069d = z2;
        }

        @Override // java.lang.Runnable
        public void run() {
            C0432a0.this.j(this.f7066a, this.f7067b, this.f7068c, this.f7069d);
            C0432a0.this.f7057c.postDelayed(this, 1000L);
        }
    }

    /* JADX INFO: renamed from: o0.a0$e */
    public class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f7074a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f7075b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ boolean f7076c;

        public e(String str, String str2, boolean z2) {
            this.f7074a = str;
            this.f7075b = str2;
            this.f7076c = z2;
        }

        @Override // java.lang.Runnable
        public void run() {
            g gVar = (g) C0432a0.this.f7056b.get(this.f7074a);
            if (gVar != null) {
                gVar.a(this.f7075b, this.f7076c);
            }
        }
    }

    /* JADX INFO: renamed from: o0.a0$f */
    public class f implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f7078a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f7079b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f7080c;

        public f(String str, String str2, String str3) {
            this.f7078a = str;
            this.f7079b = str2;
            this.f7080c = str3;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (((Runnable) C0432a0.this.f7060f.get(this.f7078a)) != this) {
                return;
            }
            C0432a0.this.f7060f.remove(this.f7078a);
            C0432a0.this.f7055a.remove(this.f7078a);
            C0432a0.this.o(this.f7079b, this.f7080c, false);
        }
    }

    /* JADX INFO: renamed from: o0.a0$g */
    public interface g {
        void a(String str, boolean z2);
    }

    public static synchronized C0432a0 m() {
        try {
            if (f7054g == null) {
                f7054g = new C0432a0();
            }
        } catch (Throwable th) {
            throw th;
        }
        return f7054g;
    }

    public final void i(String str) {
        Runnable runnable = (Runnable) this.f7060f.remove(str);
        if (runnable != null) {
            this.f7057c.removeCallbacks(runnable);
        }
    }

    public final void j(Context context, String str, String str2, boolean z2) {
        String str3;
        if (context == null || str == null || str.isEmpty() || str2 == null || str2.isEmpty()) {
            return;
        }
        if (z2) {
            str3 = "/groups/" + str2 + "/typing";
        } else {
            str3 = "/chats/" + str2 + "/typing";
        }
        g0.d.R(str3, str, new d(str2));
    }

    public void k(String str, String str2) {
        if (str == null || str.isEmpty() || str2 == null || str2.isEmpty()) {
            return;
        }
        String str3 = str + "_" + str2;
        i(str3);
        this.f7055a.remove(str3);
        o(str, str2, false);
    }

    public final void l(String str) {
        Runnable runnable;
        for (String str2 : new ArrayList(this.f7055a.keySet())) {
            if (str2.startsWith(str + "_")) {
                this.f7055a.remove(str2);
            }
        }
        for (String str3 : new ArrayList(this.f7060f.keySet())) {
            if (str3.startsWith(str + "_") && (runnable = (Runnable) this.f7060f.remove(str3)) != null) {
                this.f7057c.removeCallbacks(runnable);
            }
        }
    }

    public void n(String str, String str2, boolean z2) {
        if (str == null || str.isEmpty() || str2 == null || str2.isEmpty()) {
            return;
        }
        x(str, str2, z2);
    }

    public final void o(String str, String str2, boolean z2) {
        this.f7057c.post(new e(str, str2, z2));
    }

    public void p(String str, g gVar) {
        this.f7056b.put(str, gVar);
    }

    public final void q(String str, String str2, String str3) {
        i(str3);
        j0.v vVar = (j0.v) this.f7055a.get(str3);
        if (vVar == null) {
            vVar = new j0.v();
            vVar.f5641a = str2;
            vVar.f5642b = str;
        }
        vVar.f5643c = true;
        vVar.f5644d = System.currentTimeMillis();
        this.f7055a.put(str3, vVar);
        f fVar = new f(str3, str, str2);
        this.f7060f.put(str3, fVar);
        this.f7057c.postDelayed(fVar, 3000L);
    }

    public final void r(Context context, String str, String str2, boolean z2, boolean z3) {
        if (context == null || str == null || str.isEmpty() || str2 == null || str2.isEmpty()) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("chat_id", str2);
            jSONObject.put("is_typing", z2);
            jSONObject.put("is_group", z3);
            g0.d.S(z3 ? "/groups/typing" : "/chats/typing", jSONObject, str, new c());
        } catch (Exception unused) {
        }
    }

    public void s(Context context, String str, String str2, boolean z2) {
        String str3 = str2 + "_check";
        if (this.f7059e.containsKey(str3)) {
            return;
        }
        b bVar = new b(context, str, str2, z2);
        this.f7059e.put(str3, bVar);
        this.f7057c.post(bVar);
    }

    public void t(Context context, String str, String str2, boolean z2) {
        String str3 = str2 + "_send";
        if (this.f7058d.containsKey(str3)) {
            return;
        }
        a aVar = new a(context, str, str2, z2);
        this.f7058d.put(str3, aVar);
        this.f7057c.post(aVar);
    }

    public void u(String str) {
        Runnable runnable = (Runnable) this.f7059e.remove(str + "_check");
        if (runnable != null) {
            this.f7057c.removeCallbacks(runnable);
        }
        l(str);
    }

    public void v(Context context, String str, String str2, boolean z2) {
        Runnable runnable = (Runnable) this.f7058d.remove(str2 + "_send");
        if (runnable != null) {
            this.f7057c.removeCallbacks(runnable);
        }
        r(context, str, str2, false, z2);
    }

    public void w(String str) {
        this.f7056b.remove(str);
        u(str);
    }

    public final void x(String str, String str2, boolean z2) {
        String str3 = str + "_" + str2;
        if (!z2) {
            if (((j0.v) this.f7055a.get(str3)) == null) {
                o(str, str2, false);
                return;
            } else {
                q(str, str2, str3);
                return;
            }
        }
        i(str3);
        j0.v vVar = (j0.v) this.f7055a.get(str3);
        if (vVar == null) {
            vVar = new j0.v();
            vVar.f5641a = str2;
            vVar.f5642b = str;
        }
        vVar.f5643c = true;
        vVar.f5644d = System.currentTimeMillis();
        this.f7055a.put(str3, vVar);
        o(str, str2, true);
    }

    /* JADX INFO: renamed from: o0.a0$c */
    public class c implements d.i {
        public c() {
        }

        @Override // g0.d.i
        public void b(String str) {
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
        }
    }

    /* JADX INFO: renamed from: o0.a0$d */
    public class d implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f7072a;

        public d(String str) {
            this.f7072a = str;
        }

        @Override // g0.d.i
        public void b(String str) {
            if (str != null) {
                try {
                    if (str.isEmpty()) {
                        return;
                    }
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.has("users")) {
                        JSONArray jSONArray = jSONObject.getJSONArray("users");
                        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                            JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                            String strOptString = jSONObject2.optString("uid");
                            boolean zOptBoolean = jSONObject2.optBoolean("is_typing", false);
                            if (strOptString != null && !strOptString.isEmpty()) {
                                C0432a0.this.x(this.f7072a, strOptString, zOptBoolean);
                            }
                        }
                    }
                } catch (Exception unused) {
                }
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
        }
    }
}

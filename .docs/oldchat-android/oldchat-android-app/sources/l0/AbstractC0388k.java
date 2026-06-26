package l0;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import g0.d;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;
import org.spongycastle.i18n.TextBundle;

/* JADX INFO: renamed from: l0.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0388k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f6107a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public RecyclerView f6108b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public com.im.oldchat.ui.S f6109c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public List f6110d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public HashSet f6111e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TextView f6112f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public TextView f6113g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ProgressBar f6114h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f6115i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f6116j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f6117k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f6118l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public TextView f6119m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f6120n = false;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f6121o = true;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f6122p = 0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f6123q = 0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f6124r = 0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f6125s = false;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public String f6126t = null;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public String f6127u = null;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public String f6128v = null;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f6129w = false;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f6130x = 0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f6131y = 0;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f6132z = 0;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f6106A = true;

    /* JADX INFO: renamed from: l0.k$b */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractC0388k.this.f();
        }
    }

    /* JADX INFO: renamed from: l0.k$c */
    public class c extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f6135a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f6136b;

        public c(Context context, int i2) {
            this.f6135a = context;
            this.f6136b = i2;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public List doInBackground(Void... voidArr) {
            Context context = this.f6135a;
            if (context == null) {
                return null;
            }
            return j0.l.e(context, AbstractC0388k.this.f6115i);
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List list) {
            if (this.f6136b != AbstractC0388k.this.f6130x || list == null || list.isEmpty() || !AbstractC0388k.this.f6110d.isEmpty()) {
                return;
            }
            AbstractC0388k abstractC0388k = AbstractC0388k.this;
            abstractC0388k.f6121o = true;
            abstractC0388k.f6110d.clear();
            AbstractC0388k.this.f6111e.clear();
            AbstractC0388k.this.f6110d.addAll(list);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String str = ((j0.k) it.next()).f5550a;
                if (str != null) {
                    AbstractC0388k.this.f6111e.add(str);
                }
            }
            AbstractC0388k abstractC0388k2 = AbstractC0388k.this;
            AbstractC0394n.f(abstractC0388k2.f6110d, abstractC0388k2.f6111e, 320);
            AbstractC0388k abstractC0388k3 = AbstractC0388k.this;
            abstractC0388k3.f6123q = abstractC0388k3.f6110d.size();
            AbstractC0388k abstractC0388k4 = AbstractC0388k.this;
            AbstractC0380g.a(abstractC0388k4.f6107a, abstractC0388k4.f6110d, abstractC0388k4.f6118l);
            AbstractC0388k.this.f6109c.g();
            AbstractC0388k abstractC0388k5 = AbstractC0388k.this;
            abstractC0388k5.f6108b.T0(abstractC0388k5.f6110d.size() - 1);
            TextView textView = AbstractC0388k.this.f6112f;
            if (textView != null) {
                textView.setEnabled(true);
                AbstractC0388k.this.f6112f.setText(f0.j.l1);
            }
            AbstractC0388k.this.s(list, false);
        }
    }

    public void a(j0.k kVar, boolean z2, String str) {
        if (kVar == null) {
            return;
        }
        boolean z3 = z2 || g();
        String str2 = kVar.f5550a;
        if (str2 == null || !this.f6111e.contains(str2)) {
            this.f6110d.add(kVar);
            String str3 = kVar.f5550a;
            if (str3 != null) {
                this.f6111e.add(str3);
            }
            AbstractC0394n.f(this.f6110d, this.f6111e, 320);
            AbstractC0380g.a(this.f6107a, this.f6110d, this.f6118l);
            if (this.f6109c != null) {
                if (o0.F.c(this.f6107a, kVar.f5552c, this.f6118l)) {
                    this.f6109c.p0(kVar);
                } else {
                    this.f6109c.o0(kVar);
                }
            }
            this.f6109c.g();
            j0.l.l(this.f6107a, this.f6115i, this.f6110d);
            r(kVar, z3);
            if (!z3) {
                String str4 = kVar.f5552c;
                if (str4 == null || o0.F.c(this.f6107a, str4, this.f6118l)) {
                    return;
                }
                j();
                return;
            }
            n();
            String str5 = kVar.f5552c;
            if (str5 == null || o0.F.c(this.f6107a, str5, this.f6118l)) {
                return;
            }
            i(str);
        }
    }

    public void b() {
        int iM1;
        LinearLayoutManager linearLayoutManagerC = c();
        if (linearLayoutManagerC != null && (iM1 = linearLayoutManagerC.M1()) >= 0) {
            View viewA = linearLayoutManagerC.A(iM1);
            this.f6131y = iM1;
            this.f6132z = viewA == null ? 0 : viewA.getTop();
        }
    }

    public LinearLayoutManager c() {
        RecyclerView.n layoutManager = this.f6108b.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return (LinearLayoutManager) layoutManager;
        }
        return null;
    }

    public abstract void d();

    public void e() {
        f();
    }

    public void f() {
        this.f6122p = 0;
        TextView textView = this.f6113g;
        if (textView != null) {
            textView.clearAnimation();
            this.f6113g.setClickable(false);
            this.f6113g.setVisibility(8);
        }
    }

    public boolean g() {
        if (this.f6110d.isEmpty()) {
            this.f6106A = true;
            return true;
        }
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.f6108b.getLayoutManager();
        if (linearLayoutManager == null) {
            return this.f6106A;
        }
        int iO1 = linearLayoutManager.O1();
        int iC = this.f6109c.c();
        if (iO1 < 0 || iC <= 0) {
            return this.f6106A;
        }
        boolean z2 = iO1 >= iC + (-2);
        this.f6106A = z2;
        return z2;
    }

    public void h() {
        int i2 = this.f6130x + 1;
        this.f6130x = i2;
        Context context = this.f6107a;
        new c(context != null ? context.getApplicationContext() : null, i2).execute(new Void[0]);
    }

    public void i(String str) {
        String str2 = this.f6115i;
        if (str2 == null || str2.isEmpty()) {
            return;
        }
        j0.r.c(this.f6107a, this.f6115i);
        f();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("with_uid", this.f6115i);
            g0.d.S("/direct/read", jSONObject, str, new a());
        } catch (Exception unused) {
        }
    }

    public void j() {
        if (g()) {
            f();
        } else {
            this.f6122p++;
            p();
        }
    }

    public void k(boolean z2) {
        b();
        if (z2) {
            f();
            if (this.f6124r == 0) {
                d();
            }
        }
    }

    public j0.k l(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            j0.k kVar = new j0.k();
            String strOptString = jSONObject.optString("id", null);
            if (strOptString == null || strOptString.isEmpty()) {
                strOptString = null;
            }
            kVar.f5550a = strOptString;
            kVar.f5551b = jSONObject.optString("thread_id", null);
            kVar.f5552c = jSONObject.optString("from_uid", this.f6118l);
            kVar.f5553d = jSONObject.optString("body", "");
            kVar.f5554e = jSONObject.optString("msg_type", TextBundle.TEXT_ENTRY);
            kVar.f5555f = jSONObject.optString("media_url", "");
            kVar.f5556g = jSONObject.optString("thumb_url", "");
            kVar.f5557h = jSONObject.optInt("duration_ms", 0);
            kVar.f5558i = AbstractC0380g.g(jSONObject.optLong("created_at", 0L));
            kVar.f5559j = AbstractC0380g.c(jSONObject);
            o0.B.d(kVar);
            return kVar;
        } catch (Exception unused) {
            return null;
        }
    }

    public void m() {
        this.f6106A = g();
        b();
    }

    public void n() {
        e();
        if (this.f6110d.isEmpty()) {
            return;
        }
        this.f6108b.T0(this.f6110d.size() - 1);
        this.f6108b.post(new b());
    }

    public void o(boolean z2) {
        ProgressBar progressBar = this.f6114h;
        if (progressBar != null) {
            progressBar.setVisibility(z2 ? 0 : 8);
        }
    }

    public void p() {
        if (this.f6113g == null) {
            return;
        }
        if (this.f6122p <= 0 || g()) {
            f();
            return;
        }
        this.f6113g.clearAnimation();
        this.f6113g.setClickable(true);
        this.f6113g.setText(this.f6122p + " 条新消息");
        this.f6113g.setVisibility(0);
    }

    public void q(String str) {
        String str2 = this.f6115i;
        if (str2 == null || str2.isEmpty()) {
            return;
        }
        String str3 = this.f6116j;
        if (str3 == null) {
            str3 = this.f6115i;
        }
        j0.r.k(this.f6107a, this.f6115i, str3, this.f6117k, str);
    }

    public void r(j0.k kVar, boolean z2) {
        String str;
        if (kVar == null || (str = this.f6115i) == null || str.isEmpty()) {
            return;
        }
        String strD = AbstractC0380g.d(kVar);
        String str2 = kVar.f5552c;
        if (str2 == null || !o0.F.c(this.f6107a, str2, this.f6118l)) {
            j0.r.l(this.f6107a, this.f6115i, this.f6116j, this.f6117k, strD, kVar.f5558i, !z2 ? 1 : 0);
        } else {
            j0.r.n(this.f6107a, this.f6115i, this.f6116j, this.f6117k, strD, kVar.f5558i);
        }
    }

    public void s(List list, boolean z2) {
        if (z2 || list.isEmpty()) {
            return;
        }
        q(AbstractC0380g.d((j0.k) list.get(list.size() - 1)));
    }

    /* JADX INFO: renamed from: l0.k$a */
    public class a implements d.i {
        public a() {
        }

        @Override // g0.d.i
        public void b(String str) {
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
        }
    }
}

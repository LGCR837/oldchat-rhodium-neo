package l0;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import g0.d;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;
import org.spongycastle.i18n.TextBundle;

/* JADX INFO: loaded from: classes.dex */
public abstract class F {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f5805a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ListView f5806b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public com.im.oldchat.ui.G f5807c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public List f5808d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public HashSet f5809e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TextView f5810f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public TextView f5811g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ProgressBar f5812h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f5813i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f5814j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f5815k;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public TextView f5821q;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f5816l = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f5817m = true;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f5818n = 0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f5819o = 0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f5820p = 0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f5822r = 0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f5823s = false;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public String f5824t = null;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public String f5825u = null;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public String f5826v = null;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f5827w = false;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f5828x = 0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f5829y = true;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            F.this.e();
        }
    }

    public class c extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f5832a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f5833b;

        public c(Context context, int i2) {
            this.f5832a = context;
            this.f5833b = i2;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public List doInBackground(Void... voidArr) {
            Context context = this.f5832a;
            if (context == null) {
                return null;
            }
            return j0.l.g(context, F.this.f5813i);
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List list) {
            if (this.f5833b != F.this.f5828x || list == null || list.isEmpty() || !F.this.f5808d.isEmpty()) {
                return;
            }
            F f2 = F.this;
            f2.f5817m = true;
            f2.f5808d.clear();
            F.this.f5809e.clear();
            F.this.f5808d.addAll(list);
            F f3 = F.this;
            f3.f5819o = f3.f5808d.size();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String str = ((j0.i) it.next()).f5529a;
                if (str != null) {
                    F.this.f5809e.add(str);
                }
            }
            F.this.f5807c.notifyDataSetChanged();
            int count = F.this.f5806b.getCount() - 1;
            if (count >= 0) {
                F.this.f5806b.setSelection(count);
            }
            F.this.f5806b.setTranscriptMode(2);
            TextView textView = F.this.f5810f;
            if (textView != null) {
                textView.setEnabled(true);
                F.this.f5810f.setText(f0.j.l1);
            }
            F.this.q(list, false);
        }
    }

    public void b(j0.i iVar, boolean z2) {
        if (iVar == null) {
            return;
        }
        boolean z3 = z2 || f();
        String str = iVar.f5529a;
        if (str == null || !this.f5809e.contains(str)) {
            this.f5808d.add(iVar);
            String str2 = iVar.f5529a;
            if (str2 != null) {
                this.f5809e.add(str2);
            }
            N.i(this.f5808d, this.f5809e, 320);
            if (this.f5807c != null) {
                if (o0.F.c(this.f5805a, iVar.f5531c, this.f5815k)) {
                    this.f5807c.l0(iVar);
                } else {
                    this.f5807c.k0(iVar);
                }
            }
            this.f5807c.notifyDataSetChanged();
            j0.l.m(this.f5805a, this.f5813i, this.f5808d);
            if (z3) {
                this.f5806b.setTranscriptMode(2);
                n();
                return;
            }
            String str3 = iVar.f5531c;
            if (str3 == null || o0.F.c(this.f5805a, str3, this.f5815k)) {
                return;
            }
            this.f5806b.setTranscriptMode(0);
            j();
        }
    }

    public abstract void c();

    public void d() {
        e();
    }

    public final void e() {
        this.f5818n = 0;
        TextView textView = this.f5811g;
        if (textView != null) {
            textView.clearAnimation();
            this.f5811g.setClickable(false);
            this.f5811g.setVisibility(8);
        }
    }

    public boolean f() {
        if (this.f5808d.isEmpty()) {
            this.f5829y = true;
            return true;
        }
        int count = this.f5806b.getCount();
        if (count <= 0) {
            return this.f5829y;
        }
        int lastVisiblePosition = this.f5806b.getLastVisiblePosition();
        if (lastVisiblePosition < 0) {
            return this.f5829y;
        }
        boolean z2 = lastVisiblePosition >= count + (-2);
        this.f5829y = z2;
        return z2;
    }

    public boolean g() {
        Context context = this.f5805a;
        if (context == null) {
            return false;
        }
        if (!(context instanceof Activity)) {
            return true;
        }
        Activity activity = (Activity) context;
        return Build.VERSION.SDK_INT >= 17 ? (activity.isFinishing() || activity.isDestroyed()) ? false : true : !activity.isFinishing();
    }

    public void h() {
        int i2 = this.f5828x + 1;
        this.f5828x = i2;
        Context context = this.f5805a;
        new c(context != null ? context.getApplicationContext() : null, i2).execute(new Void[0]);
    }

    public void i(String str) {
        String str2 = this.f5813i;
        if (str2 == null || str2.isEmpty()) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.f5820p < 2000) {
            return;
        }
        this.f5820p = jCurrentTimeMillis;
        j0.j.b(this.f5805a, this.f5813i);
        e();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("group_id", this.f5813i);
            g0.d.S("/groups/read", jSONObject, str, new b());
        } catch (Exception unused) {
        }
    }

    public void j() {
        if (f()) {
            e();
        } else {
            this.f5818n++;
            p();
        }
    }

    public void k(boolean z2) {
        if (!z2) {
            this.f5806b.setTranscriptMode(0);
            return;
        }
        this.f5806b.setTranscriptMode(2);
        e();
        if (this.f5822r == 0) {
            c();
        }
    }

    public j0.i l(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            j0.i iVar = new j0.i();
            String strOptString = jSONObject.optString("id", null);
            if (strOptString == null || strOptString.isEmpty()) {
                strOptString = null;
            }
            iVar.f5529a = strOptString;
            iVar.f5530b = jSONObject.optString("group_id", this.f5813i);
            String strB = o0.F.b(this.f5805a);
            if (strB == null || strB.length() == 0) {
                strB = this.f5815k;
            }
            iVar.f5531c = jSONObject.optString("from_uid", strB);
            iVar.f5532d = jSONObject.optString("body", "");
            iVar.f5533e = jSONObject.optString("msg_type", TextBundle.TEXT_ENTRY);
            iVar.f5534f = jSONObject.optString("media_url", "");
            iVar.f5535g = jSONObject.optString("thumb_url", "");
            iVar.f5536h = jSONObject.optInt("duration_ms", 0);
            iVar.f5537i = AbstractC0380g.g(jSONObject.optLong("created_at", 0L));
            o0.B.f(iVar);
            return iVar;
        } catch (Exception unused) {
            return null;
        }
    }

    public void m() {
        this.f5829y = f();
    }

    public void n() {
        ListView listView;
        d();
        if (this.f5808d.isEmpty() || (listView = this.f5806b) == null) {
            return;
        }
        int count = listView.getCount() - 1;
        if (count >= 0) {
            this.f5806b.setSelection(count);
        }
        this.f5806b.post(new a());
    }

    public void o(boolean z2) {
        ProgressBar progressBar = this.f5812h;
        if (progressBar != null) {
            progressBar.setVisibility(z2 ? 0 : 8);
        }
    }

    public void p() {
        if (this.f5811g == null) {
            return;
        }
        if (this.f5818n <= 0 || f()) {
            e();
            return;
        }
        this.f5811g.clearAnimation();
        this.f5811g.setClickable(true);
        this.f5811g.setText(this.f5818n + " 条新消息");
        this.f5811g.setVisibility(0);
    }

    public void q(List list, boolean z2) {
        if (z2 || list.isEmpty()) {
            return;
        }
        j0.i iVar = (j0.i) list.get(list.size() - 1);
        j0.j.k(this.f5805a, this.f5813i, this.f5814j, null, AbstractC0380g.e(iVar.f5533e, iVar.f5532d), iVar.f5537i);
    }

    public class b implements d.i {
        public b() {
        }

        @Override // g0.d.i
        public void b(String str) {
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
        }
    }
}

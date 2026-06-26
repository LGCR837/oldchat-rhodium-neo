package m0;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import c.AbstractComponentCallbacksC0165t;
import g0.d;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import l0.A0;
import l0.z0;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class a extends AbstractComponentCallbacksC0165t {

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public SwipeRefreshLayout f6424Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public RecyclerView f6425a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public TextView f6426b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public z0 f6427c0;

    /* JADX INFO: renamed from: m0.a$a, reason: collision with other inner class name */
    public class C0054a implements SwipeRefreshLayout.j {
        public C0054a() {
        }

        @Override // android.support.v4.widget.SwipeRefreshLayout.j
        public void a() {
            a.this.M0();
        }
    }

    public class b implements d.i {

        /* JADX INFO: renamed from: m0.a$b$a, reason: collision with other inner class name */
        public class RunnableC0055a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ List f6430a;

            public RunnableC0055a(List list) {
                this.f6430a = list;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.f6424Z.setRefreshing(false);
                a.this.f6427c0.z(this.f6430a);
                a.this.Q0(this.f6430a);
            }
        }

        /* JADX INFO: renamed from: m0.a$b$b, reason: collision with other inner class name */
        public class RunnableC0056b implements Runnable {
            public RunnableC0056b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.f6424Z.setRefreshing(false);
                Toast.makeText(a.this.e(), "加载失败", 0).show();
            }
        }

        public b() {
        }

        @Override // g0.d.i
        public void b(String str) {
            List listP0 = a.this.P0(str);
            if (a.this.e() == null) {
                return;
            }
            a.this.e().runOnUiThread(new RunnableC0055a(listP0));
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (a.this.e() == null) {
                return;
            }
            a.this.e().runOnUiThread(new RunnableC0056b());
        }
    }

    @Override // c.AbstractComponentCallbacksC0165t
    public View K(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(f0.g.f5162o0, viewGroup, false);
        this.f6424Z = (SwipeRefreshLayout) viewInflate.findViewById(f0.f.r8);
        this.f6425a0 = (RecyclerView) viewInflate.findViewById(f0.f.O7);
        this.f6426b0 = (TextView) viewInflate.findViewById(f0.f.K9);
        this.f6425a0.setLayoutManager(new LinearLayoutManager(e()));
        z0 z0Var = new z0();
        this.f6427c0 = z0Var;
        this.f6425a0.setAdapter(z0Var);
        this.f6424Z.setOnRefreshListener(new C0054a());
        return viewInflate;
    }

    public final void M0() {
        if (e() == null) {
            return;
        }
        this.f6424Z.setRefreshing(true);
        String string = e().getSharedPreferences("auth", 0).getString("access_token", "");
        if (string != null && string.length() != 0) {
            g0.d.R("/reports/bug?limit=50", string, new b());
        } else {
            this.f6424Z.setRefreshing(false);
            Toast.makeText(e(), "请先登录", 0).show();
        }
    }

    public final String N0(String str) {
        return "resolved".equals(str) ? "已修复" : "closed".equals(str) ? "已关闭" : "处理中";
    }

    public final int O0(String str) {
        return ("resolved".equals(str) || "closed".equals(str)) ? 1 : 0;
    }

    public final List P0(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("reports");
            if (jSONArrayOptJSONArray == null) {
                return arrayList;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
            for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArrayOptJSONArray.getJSONObject(i2);
                String strOptString = jSONObject.optString("content", "");
                String strOptString2 = jSONObject.optString("user_uid", "");
                String strOptString3 = jSONObject.optString("status", "open");
                String strOptString4 = jSONObject.optString("admin_note", "");
                long jOptLong = jSONObject.optLong("created_at", 0L);
                A0 a02 = new A0();
                a02.f5763a = "问题反馈";
                a02.f5764b = strOptString;
                a02.f5768f = 90;
                a02.f5766d = N0(strOptString3);
                a02.f5767e = O0(strOptString3);
                String str2 = "时间: " + (jOptLong > 0 ? simpleDateFormat.format(new Date(jOptLong * 1000)) : "");
                if (strOptString2 != null && strOptString2.length() > 0) {
                    str2 = str2 + "\n反馈人: " + strOptString2;
                }
                if (strOptString4 != null && strOptString4.length() > 0) {
                    str2 = str2 + "\n处理: " + strOptString4;
                }
                a02.f5765c = str2;
                arrayList.add(a02);
            }
        } catch (Exception unused) {
        }
        return arrayList;
    }

    public final void Q0(List list) {
        boolean z2 = list == null || list.isEmpty();
        this.f6426b0.setVisibility(z2 ? 0 : 8);
        this.f6425a0.setVisibility(z2 ? 8 : 0);
        this.f6426b0.setText("暂无问题反馈");
    }

    @Override // c.AbstractComponentCallbacksC0165t
    public void Z() {
        super.Z();
        M0();
    }
}

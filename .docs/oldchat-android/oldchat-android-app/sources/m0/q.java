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
public class q extends AbstractComponentCallbacksC0165t {

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public SwipeRefreshLayout f6594Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public RecyclerView f6595a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public TextView f6596b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public z0 f6597c0;

    public class a implements SwipeRefreshLayout.j {
        public a() {
        }

        @Override // android.support.v4.widget.SwipeRefreshLayout.j
        public void a() {
            q.this.M0();
        }
    }

    public class b implements d.i {

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ List f6600a;

            public a(List list) {
                this.f6600a = list;
            }

            @Override // java.lang.Runnable
            public void run() {
                q.this.f6594Z.setRefreshing(false);
                q.this.f6597c0.z(this.f6600a);
                q.this.Q0(this.f6600a);
            }
        }

        /* JADX INFO: renamed from: m0.q$b$b, reason: collision with other inner class name */
        public class RunnableC0062b implements Runnable {
            public RunnableC0062b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                q.this.f6594Z.setRefreshing(false);
                Toast.makeText(q.this.e(), "加载失败", 0).show();
            }
        }

        public b() {
        }

        @Override // g0.d.i
        public void b(String str) {
            List listP0 = q.this.P0(str);
            if (q.this.e() == null) {
                return;
            }
            q.this.e().runOnUiThread(new a(listP0));
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (q.this.e() == null) {
                return;
            }
            q.this.e().runOnUiThread(new RunnableC0062b());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M0() {
        if (e() == null) {
            return;
        }
        this.f6594Z.setRefreshing(true);
        String string = e().getSharedPreferences("auth", 0).getString("access_token", "");
        if (string != null && string.length() != 0) {
            g0.d.R("/reports/user?limit=50", string, new b());
        } else {
            this.f6594Z.setRefreshing(false);
            Toast.makeText(e(), "请先登录", 0).show();
        }
    }

    private String N0(String str) {
        return "handled".equals(str) ? "已处理" : "rejected".equals(str) ? "已驳回" : "待处理";
    }

    private int O0(String str) {
        if ("handled".equals(str)) {
            return 1;
        }
        return "rejected".equals(str) ? 2 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List P0(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("reports");
            if (jSONArrayOptJSONArray == null) {
                return arrayList;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
            for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArrayOptJSONArray.getJSONObject(i2);
                String strOptString = jSONObject.optString("target_uid", "");
                String strOptString2 = jSONObject.optString("reporter_uid", "");
                String strOptString3 = jSONObject.optString("reason", "");
                String strOptString4 = jSONObject.optString("status", "pending");
                String strOptString5 = jSONObject.optString("result", "");
                long jOptLong = jSONObject.optLong("created_at", 0L);
                A0 a02 = new A0();
                a02.f5763a = "举报用户: " + strOptString;
                a02.f5764b = strOptString3;
                a02.f5766d = N0(strOptString4);
                a02.f5767e = O0(strOptString4);
                String str2 = "时间: " + (jOptLong > 0 ? simpleDateFormat.format(new Date(1000 * jOptLong)) : "");
                if (strOptString2 != null && strOptString2.length() > 0) {
                    str2 = str2 + "\n举报人: " + strOptString2;
                }
                if (strOptString5 != null && strOptString5.length() > 0) {
                    str2 = str2 + "\n结果: " + strOptString5;
                }
                a02.f5765c = str2;
                arrayList.add(a02);
            }
        } catch (Exception unused) {
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q0(List list) {
        boolean z2 = list == null || list.isEmpty();
        this.f6596b0.setVisibility(z2 ? 0 : 8);
        this.f6595a0.setVisibility(z2 ? 8 : 0);
        this.f6596b0.setText("暂无用户举报");
    }

    @Override // c.AbstractComponentCallbacksC0165t
    public View K(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(f0.g.f5162o0, viewGroup, false);
        this.f6594Z = (SwipeRefreshLayout) viewInflate.findViewById(f0.f.r8);
        this.f6595a0 = (RecyclerView) viewInflate.findViewById(f0.f.O7);
        this.f6596b0 = (TextView) viewInflate.findViewById(f0.f.K9);
        this.f6595a0.setLayoutManager(new LinearLayoutManager(e()));
        z0 z0Var = new z0();
        this.f6597c0 = z0Var;
        this.f6595a0.setAdapter(z0Var);
        this.f6594Z.setOnRefreshListener(new a());
        return viewInflate;
    }

    @Override // c.AbstractComponentCallbacksC0165t
    public void Z() {
        super.Z();
        M0();
    }
}

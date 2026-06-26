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
public class p extends AbstractComponentCallbacksC0165t {

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public SwipeRefreshLayout f6585Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public RecyclerView f6586a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public TextView f6587b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public z0 f6588c0;

    public class a implements SwipeRefreshLayout.j {
        public a() {
        }

        @Override // android.support.v4.widget.SwipeRefreshLayout.j
        public void a() {
            p.this.M0();
        }
    }

    public class b implements d.i {

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ List f6591a;

            public a(List list) {
                this.f6591a = list;
            }

            @Override // java.lang.Runnable
            public void run() {
                p.this.f6585Z.setRefreshing(false);
                p.this.f6588c0.z(this.f6591a);
                p.this.Q0(this.f6591a);
            }
        }

        /* JADX INFO: renamed from: m0.p$b$b, reason: collision with other inner class name */
        public class RunnableC0061b implements Runnable {
            public RunnableC0061b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                p.this.f6585Z.setRefreshing(false);
                Toast.makeText(p.this.e(), "加载失败", 0).show();
            }
        }

        public b() {
        }

        @Override // g0.d.i
        public void b(String str) {
            List listP0 = p.this.P0(str);
            if (p.this.e() == null) {
                return;
            }
            p.this.e().runOnUiThread(new a(listP0));
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (p.this.e() == null) {
                return;
            }
            p.this.e().runOnUiThread(new RunnableC0061b());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M0() {
        if (e() == null) {
            return;
        }
        this.f6585Z.setRefreshing(true);
        String string = e().getSharedPreferences("auth", 0).getString("access_token", "");
        if (string != null && string.length() != 0) {
            g0.d.R("/reports/resource?limit=50", string, new b());
        } else {
            this.f6585Z.setRefreshing(false);
            Toast.makeText(e(), "请先登录", 0).show();
        }
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
                String strOptString = jSONObject.optString("item_name", "");
                String strOptString2 = jSONObject.optString("section_name", "");
                String strOptString3 = jSONObject.optString("reporter_uid", "");
                String strOptString4 = jSONObject.optString("reason", "");
                String strOptString5 = jSONObject.optString("status", "pending");
                String strOptString6 = jSONObject.optString("result", "");
                long jOptLong = jSONObject.optLong("created_at", 0L);
                A0 a02 = new A0();
                a02.f5763a = "资源举报: " + strOptString;
                a02.f5764b = strOptString4;
                a02.f5766d = N0(strOptString5);
                a02.f5767e = O0(strOptString5);
                String str2 = "分区: " + strOptString2 + "\n时间: " + (jOptLong > 0 ? simpleDateFormat.format(new Date(1000 * jOptLong)) : "");
                if (strOptString3 != null && strOptString3.length() > 0) {
                    str2 = str2 + "\n举报人: " + strOptString3;
                }
                if (strOptString6 != null && strOptString6.length() > 0) {
                    str2 = str2 + "\n结果: " + strOptString6;
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
        this.f6587b0.setVisibility(z2 ? 0 : 8);
        this.f6586a0.setVisibility(z2 ? 8 : 0);
        this.f6587b0.setText("暂无资源举报");
    }

    @Override // c.AbstractComponentCallbacksC0165t
    public View K(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(f0.g.f5162o0, viewGroup, false);
        this.f6585Z = (SwipeRefreshLayout) viewInflate.findViewById(f0.f.r8);
        this.f6586a0 = (RecyclerView) viewInflate.findViewById(f0.f.O7);
        this.f6587b0 = (TextView) viewInflate.findViewById(f0.f.K9);
        this.f6586a0.setLayoutManager(new LinearLayoutManager(e()));
        z0 z0Var = new z0();
        this.f6588c0 = z0Var;
        this.f6586a0.setAdapter(z0Var);
        this.f6585Z.setOnRefreshListener(new a());
        return viewInflate;
    }

    public final String N0(String str) {
        return "handled".equals(str) ? "已处理" : "rejected".equals(str) ? "已驳回" : "待处理";
    }

    public final int O0(String str) {
        if ("handled".equals(str)) {
            return 1;
        }
        return "rejected".equals(str) ? 2 : 0;
    }

    @Override // c.AbstractComponentCallbacksC0165t
    public void Z() {
        super.Z();
        M0();
    }
}

package com.im.oldchat.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import g0.d;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import o.c;
import o0.AbstractC0455v;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class PublicCourtActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public String f3723A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public boolean f3724B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public boolean f3725C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public boolean f3726D = true;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public long f3727E;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public SwipeRefreshLayout f3728w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public RecyclerView f3729x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f3730y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public g f3731z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PublicCourtActivity.this.finish();
        }
    }

    public class b extends RecyclerView.s {
        public b() {
        }

        @Override // android.support.v7.widget.RecyclerView.s
        public void b(RecyclerView recyclerView, int i2, int i3) {
            int iC;
            if (i3 <= 0 || PublicCourtActivity.this.f3724B || PublicCourtActivity.this.f3725C || !PublicCourtActivity.this.f3726D) {
                return;
            }
            RecyclerView.n layoutManager = recyclerView.getLayoutManager();
            if ((layoutManager instanceof LinearLayoutManager) && (iC = PublicCourtActivity.this.f3731z.c()) > 1 && ((LinearLayoutManager) layoutManager).O1() >= iC - 3) {
                PublicCourtActivity.this.w0();
            }
        }
    }

    public class c implements SwipeRefreshLayout.j {
        public c() {
        }

        @Override // android.support.v4.widget.SwipeRefreshLayout.j
        public void a() {
            PublicCourtActivity.this.y0();
        }
    }

    public class d implements g.f {

        public class a implements DialogInterface.OnClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ EditText f3736a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ EditText f3737b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ h f3738c;

            public a(EditText editText, EditText editText2, h hVar) {
                this.f3736a = editText;
                this.f3737b = editText2;
                this.f3738c = hVar;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                PublicCourtActivity.this.B0(this.f3738c.f3772a, this.f3736a.getText() == null ? "" : this.f3736a.getText().toString().trim(), this.f3737b.getText() != null ? this.f3737b.getText().toString().trim() : "");
            }
        }

        public d() {
        }

        @Override // com.im.oldchat.ui.PublicCourtActivity.g.f
        public void a(h hVar) {
            String str;
            if (hVar == null || (str = hVar.f3772a) == null || str.length() == 0) {
                return;
            }
            if (!hVar.f3793v) {
                Toast.makeText(PublicCourtActivity.this, "你不是当事方，不能提交陈述", 0).show();
                return;
            }
            if (!"open".equals(hVar.f3784m)) {
                Toast.makeText(PublicCourtActivity.this, "案件已锁定，无法再修改陈述", 0).show();
                return;
            }
            View viewInflate = LayoutInflater.from(PublicCourtActivity.this).inflate(f0.g.f5152j0, (ViewGroup) null);
            EditText editText = (EditText) viewInflate.findViewById(f0.f.U4);
            EditText editText2 = (EditText) viewInflate.findViewById(f0.f.T4);
            String str2 = hVar.f3794w;
            if (str2 == null) {
                str2 = "";
            }
            editText.setText(str2);
            String str3 = hVar.f3795x;
            editText2.setText(str3 != null ? str3 : "");
            new c.a(PublicCourtActivity.this).t("提交陈述与证据").u(viewInflate).q("保存", new a(editText, editText2, hVar)).k("取消", null).v();
        }

        @Override // com.im.oldchat.ui.PublicCourtActivity.g.f
        public void b(h hVar) {
            String str;
            if (hVar == null || (str = hVar.f3772a) == null || str.length() == 0) {
                return;
            }
            Intent intent = new Intent(PublicCourtActivity.this, (Class<?>) PublicCourtCaseDetailActivity.class);
            intent.putExtra("case_id", hVar.f3772a);
            PublicCourtActivity.this.startActivity(intent);
        }
    }

    public class e implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f3740a;

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ List f3742a;

            public a(List list) {
                this.f3742a = list;
            }

            @Override // java.lang.Runnable
            public void run() {
                PublicCourtActivity.this.f3724B = false;
                PublicCourtActivity.this.f3725C = false;
                e eVar = e.this;
                if (eVar.f3740a) {
                    PublicCourtActivity.this.f3728w.setRefreshing(false);
                    PublicCourtActivity.this.f3731z.E(this.f3742a);
                } else {
                    PublicCourtActivity.this.f3731z.w(this.f3742a);
                }
                List list = this.f3742a;
                if (list != null && !list.isEmpty()) {
                    List list2 = this.f3742a;
                    PublicCourtActivity.this.f3727E = ((h) list2.get(list2.size() - 1)).f3791t;
                }
                PublicCourtActivity publicCourtActivity = PublicCourtActivity.this;
                List list3 = this.f3742a;
                publicCourtActivity.f3726D = list3 != null && list3.size() >= 20 && PublicCourtActivity.this.f3727E > 0;
                PublicCourtActivity.this.f3731z.I(false, PublicCourtActivity.this.f3726D);
                boolean z2 = PublicCourtActivity.this.f3731z.A() <= 0;
                PublicCourtActivity.this.f3730y.setVisibility(z2 ? 0 : 8);
                PublicCourtActivity.this.f3729x.setVisibility(z2 ? 8 : 0);
            }
        }

        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                PublicCourtActivity.this.f3724B = false;
                PublicCourtActivity.this.f3725C = false;
                e eVar = e.this;
                if (eVar.f3740a) {
                    PublicCourtActivity.this.f3728w.setRefreshing(false);
                }
                PublicCourtActivity.this.f3731z.I(false, PublicCourtActivity.this.f3726D);
                Toast.makeText(PublicCourtActivity.this, "加载失败", 0).show();
            }
        }

        public e(boolean z2) {
            this.f3740a = z2;
        }

        @Override // g0.d.i
        public void b(String str) {
            PublicCourtActivity.this.runOnUiThread(new a(PublicCourtActivity.this.x0(str)));
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            PublicCourtActivity.this.runOnUiThread(new b());
        }
    }

    public class f implements d.i {

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(PublicCourtActivity.this, "陈述已保存", 0).show();
                PublicCourtActivity.this.y0();
            }
        }

        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(PublicCourtActivity.this, "保存失败", 0).show();
            }
        }

        public f() {
        }

        @Override // g0.d.i
        public void b(String str) {
            PublicCourtActivity.this.runOnUiThread(new a());
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            PublicCourtActivity.this.runOnUiThread(new b());
        }
    }

    public static class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f3772a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f3773b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f3774c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f3775d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f3776e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public String f3777f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public String f3778g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public String f3779h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public String f3780i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public String f3781j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public String f3782k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public String f3783l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public String f3784m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public String f3785n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public int f3786o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public String f3787p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f3788q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public int f3789r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public int f3790s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public long f3791t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public String f3792u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public boolean f3793v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public String f3794w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public String f3795x;

        public h() {
        }

        public /* synthetic */ h(a aVar) {
            this();
        }
    }

    public static String A0(String str) {
        return "pending_review".equals(str) ? "待二审（已锁定）" : "closed".equals(str) ? "已结案" : "withdrawn".equals(str) ? "已撤销" : "投票中";
    }

    public final void B0(String str, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (str2 == null) {
                str2 = "";
            }
            jSONObject.put("reason", str2);
            if (str3 == null) {
                str3 = "";
            }
            jSONObject.put("evidence", str3);
            g0.d.S("/public-court/cases/" + str + "/statement", jSONObject, this.f3723A, new f());
        } catch (Exception unused) {
            Toast.makeText(this, "保存失败", 0).show();
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5125S);
        View viewA0 = a0(f0.f.f5102v);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        this.f3728w = (SwipeRefreshLayout) a0(f0.f.r8);
        this.f3729x = (RecyclerView) a0(f0.f.O7);
        this.f3730y = (TextView) a0(f0.f.K9);
        this.f3729x.setLayoutManager(new LinearLayoutManager(this));
        g gVar = new g(null);
        this.f3731z = gVar;
        gVar.I(false, true);
        this.f3729x.setAdapter(this.f3731z);
        this.f3729x.l(new b());
        this.f3723A = getSharedPreferences("auth", 0).getString("access_token", "");
        this.f3728w.setOnRefreshListener(new c());
        this.f3731z.F(new d());
        y0();
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onResume() {
        super.onResume();
        y0();
    }

    public final void w0() {
        z0(false);
    }

    public final List x0(String str) {
        String str2;
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("cases");
            if (jSONArrayOptJSONArray == null) {
                return arrayList;
            }
            String string = getSharedPreferences("auth", 0).getString("my_uid", "");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
            for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArrayOptJSONArray.getJSONObject(i2);
                h hVar = new h(null);
                hVar.f3772a = jSONObject.optString("id", "");
                hVar.f3773b = jSONObject.optString("reporter_uid", "");
                hVar.f3774c = jSONObject.optString("reporter_name", "");
                hVar.f3775d = jSONObject.optString("reporter_avatar", "");
                hVar.f3776e = jSONObject.optString("defendant_uid", "");
                hVar.f3777f = jSONObject.optString("defendant_name", "");
                hVar.f3778g = jSONObject.optString("defendant_avatar", "");
                hVar.f3779h = jSONObject.optString("report_reason", "");
                hVar.f3780i = jSONObject.optString("report_evidence", "");
                hVar.f3781j = jSONObject.optString("defense_reason", "");
                hVar.f3782k = jSONObject.optString("defense_evidence", "");
                hVar.f3783l = jSONObject.optString("my_vote", "");
                hVar.f3784m = jSONObject.optString("status", "open");
                hVar.f3785n = jSONObject.optString("verdict", "");
                hVar.f3786o = jSONObject.optInt("ban_hours", 0);
                hVar.f3787p = jSONObject.optString("admin_note", "");
                hVar.f3788q = jSONObject.optInt("ban_vote_count", 0);
                hVar.f3789r = jSONObject.optInt("keep_vote_count", 0);
                int iOptInt = jSONObject.optInt("total_vote_count", 0);
                if (iOptInt <= 0) {
                    iOptInt = hVar.f3788q + hVar.f3789r;
                }
                hVar.f3790s = iOptInt;
                long jOptLong = jSONObject.optLong("created_at", 0L);
                hVar.f3791t = jOptLong;
                StringBuilder sb = new StringBuilder();
                if (jOptLong > 0) {
                    sb.append("开庭: ");
                    sb.append(simpleDateFormat.format(new Date(jOptLong * 1000)));
                }
                String str3 = hVar.f3783l;
                if (str3 != null && str3.length() > 0) {
                    if (sb.length() > 0) {
                        sb.append("  ·  ");
                    }
                    sb.append("我的票: ");
                    sb.append("ban".equals(hVar.f3783l) ? "封禁" : "不封");
                }
                hVar.f3792u = sb.toString();
                boolean z2 = true;
                boolean z3 = string != null && string.length() > 0 && string.equalsIgnoreCase(hVar.f3773b);
                boolean z4 = string != null && string.length() > 0 && string.equalsIgnoreCase(hVar.f3776e);
                if (!z3 && !z4 && ((str2 = hVar.f3783l) == null || str2.length() <= 0)) {
                    z2 = false;
                }
                hVar.f3793v = z2;
                if (z3) {
                    hVar.f3794w = hVar.f3779h;
                    hVar.f3795x = hVar.f3780i;
                } else if (z4) {
                    hVar.f3794w = hVar.f3781j;
                    hVar.f3795x = hVar.f3782k;
                }
                arrayList.add(hVar);
            }
        } catch (Exception unused) {
        }
        return arrayList;
    }

    public final void y0() {
        z0(true);
    }

    public final void z0(boolean z2) {
        String str = this.f3723A;
        if (str == null || str.length() == 0) {
            Toast.makeText(this, "请先登录", 0).show();
            if (z2) {
                this.f3728w.setRefreshing(false);
                return;
            }
            return;
        }
        if (this.f3724B) {
            return;
        }
        if (z2 || (!this.f3725C && this.f3726D && this.f3731z.A() > 0 && this.f3727E > 0)) {
            this.f3724B = true;
            this.f3725C = !z2;
            if (z2) {
                this.f3726D = true;
                this.f3727E = 0L;
                this.f3728w.setRefreshing(true);
                this.f3731z.I(false, true);
            } else {
                this.f3731z.I(true, this.f3726D);
            }
            StringBuilder sb = new StringBuilder("/public-court/cases?status=all&limit=");
            sb.append(20);
            if (!z2 && this.f3727E > 0) {
                sb.append("&before=");
                sb.append(this.f3727E);
            }
            g0.d.R(sb.toString(), this.f3723A, new e(z2));
        }
    }

    public static class g extends RecyclerView.g {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final List f3748c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public f f3749d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f3750e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f3751f;

        public class a implements View.OnClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ h f3752a;

            public a(h hVar) {
                this.f3752a = hVar;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (g.this.f3749d != null) {
                    g.this.f3749d.b(this.f3752a);
                }
            }
        }

        public class b implements View.OnClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ h f3754a;

            public b(h hVar) {
                this.f3754a = hVar;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (g.this.f3749d != null) {
                    g.this.f3749d.b(this.f3754a);
                }
            }
        }

        public class c implements View.OnClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ h f3756a;

            public c(h hVar) {
                this.f3756a = hVar;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (g.this.f3749d != null) {
                    g.this.f3749d.a(this.f3756a);
                }
            }
        }

        public static class d extends RecyclerView.B {

            /* JADX INFO: renamed from: A, reason: collision with root package name */
            public TextView f3758A;

            /* JADX INFO: renamed from: B, reason: collision with root package name */
            public TextView f3759B;

            /* JADX INFO: renamed from: r, reason: collision with root package name */
            public TextView f3760r;

            /* JADX INFO: renamed from: s, reason: collision with root package name */
            public ImageView f3761s;

            /* JADX INFO: renamed from: t, reason: collision with root package name */
            public ImageView f3762t;

            /* JADX INFO: renamed from: u, reason: collision with root package name */
            public TextView f3763u;

            /* JADX INFO: renamed from: v, reason: collision with root package name */
            public TextView f3764v;

            /* JADX INFO: renamed from: w, reason: collision with root package name */
            public TextView f3765w;

            /* JADX INFO: renamed from: x, reason: collision with root package name */
            public TextView f3766x;

            /* JADX INFO: renamed from: y, reason: collision with root package name */
            public TextView f3767y;

            /* JADX INFO: renamed from: z, reason: collision with root package name */
            public TextView f3768z;

            public d(View view) {
                super(view);
                this.f3760r = (TextView) view.findViewById(f0.f.Yd);
                this.f3761s = (ImageView) view.findViewById(f0.f.e6);
                this.f3762t = (ImageView) view.findViewById(f0.f.q5);
                this.f3763u = (TextView) view.findViewById(f0.f.fd);
                this.f3764v = (TextView) view.findViewById(f0.f.v9);
                this.f3765w = (TextView) view.findViewById(f0.f.Ud);
                this.f3766x = (TextView) view.findViewById(f0.f.Ac);
                this.f3767y = (TextView) view.findViewById(f0.f.a9);
                this.f3768z = (TextView) view.findViewById(f0.f.Ca);
                this.f3758A = (TextView) view.findViewById(f0.f.f5075e0);
                this.f3759B = (TextView) view.findViewById(f0.f.S3);
            }
        }

        public static class e extends RecyclerView.B {

            /* JADX INFO: renamed from: r, reason: collision with root package name */
            public TextView f3769r;

            public e(View view) {
                super(view);
                this.f3769r = (TextView) view.findViewById(f0.f.Y9);
            }
        }

        public interface f {
            void a(h hVar);

            void b(h hVar);
        }

        /* JADX INFO: renamed from: com.im.oldchat.ui.PublicCourtActivity$g$g, reason: collision with other inner class name */
        public static class C0027g extends RecyclerView.B {

            /* JADX INFO: renamed from: r, reason: collision with root package name */
            public TextView f3770r;

            /* JADX INFO: renamed from: s, reason: collision with root package name */
            public TextView f3771s;

            public C0027g(View view) {
                super(view);
                this.f3770r = (TextView) view.findViewById(f0.f.Ad);
                this.f3771s = (TextView) view.findViewById(f0.f.zd);
            }
        }

        public g() {
            this.f3748c = new ArrayList();
            this.f3751f = true;
        }

        public static boolean B(String str) {
            if (str == null) {
                return false;
            }
            String lowerCase = str.toLowerCase(Locale.US);
            return (lowerCase.startsWith("http://") || lowerCase.startsWith("https://") || lowerCase.startsWith("/")) && (lowerCase.contains(".jpg") || lowerCase.contains(".jpeg") || lowerCase.contains(".png") || lowerCase.contains(".gif") || lowerCase.contains(".webp") || lowerCase.contains("/media/"));
        }

        public static String C(String str) {
            return str == null ? "" : str;
        }

        public static String D(String str, String str2) {
            return (str == null || str.length() == 0) ? str2 : str;
        }

        public static String H(String str) {
            if (str != null && str.trim().length() != 0) {
                String[] strArrSplit = str.split("\\n");
                int length = strArrSplit.length;
                boolean z2 = false;
                for (int i2 = 0; i2 < length; i2++) {
                    String str2 = strArrSplit[i2];
                    String strTrim = str2 == null ? "" : str2.trim();
                    if (strTrim.length() != 0) {
                        if (!B(strTrim)) {
                            return strTrim;
                        }
                        z2 = true;
                    }
                }
                if (z2) {
                    return "已上传截图证据";
                }
            }
            return "(未提交)";
        }

        public static String x(h hVar) {
            if (hVar == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            String str = hVar.f3792u;
            if (str != null && str.length() > 0) {
                sb.append(hVar.f3792u);
            }
            if ("open".equals(hVar.f3784m)) {
                if (sb.length() > 0) {
                    sb.append("  ·  ");
                }
                if (hVar.f3793v) {
                    sb.append("你可补充观点");
                } else {
                    sb.append("投票后可补充观点");
                }
            }
            return sb.toString();
        }

        public static String y(h hVar) {
            String str;
            if (hVar == null) {
                return "";
            }
            if ("open".equals(hVar.f3784m)) {
                str = "投票进度 " + hVar.f3790s + "/10";
            } else {
                str = "最终票数 " + hVar.f3790s;
            }
            return str + "  ·  封禁 " + hVar.f3788q + "  ·  不封禁 " + hVar.f3789r;
        }

        public static String z(String str, String str2) {
            if (str == null || str.length() <= 0) {
                return (str2 == null || str2.length() == 0) ? "匿名用户" : str2;
            }
            if (str2 == null || str2.length() <= 0) {
                return str;
            }
            return str + " (" + str2 + ")";
        }

        public int A() {
            return this.f3748c.size();
        }

        public void E(List list) {
            this.f3748c.clear();
            if (list != null) {
                this.f3748c.addAll(list);
            }
            g();
        }

        public void F(f fVar) {
            this.f3749d = fVar;
        }

        public final boolean G() {
            return !this.f3748c.isEmpty();
        }

        public void I(boolean z2, boolean z3) {
            this.f3750e = z2;
            this.f3751f = z3;
            if (G()) {
                h(c() - 1);
            }
        }

        @Override // android.support.v7.widget.RecyclerView.g
        public int c() {
            return this.f3748c.size() + 1 + (G() ? 1 : 0);
        }

        @Override // android.support.v7.widget.RecyclerView.g
        public int e(int i2) {
            if (i2 == 0) {
                return 1;
            }
            return (G() && i2 == c() - 1) ? 3 : 2;
        }

        @Override // android.support.v7.widget.RecyclerView.g
        public void l(RecyclerView.B b2, int i2) {
            String str;
            if (e(i2) == 1) {
                C0027g c0027g = (C0027g) b2;
                c0027g.f3770r.setText("审理规则");
                c0027g.f3771s.setText("1. 投票需提供证据（可上传截图）\n2. 达到 10 票自动锁定，进入待二审\n3. 锁定后仅可查看，等待管理员裁决");
                return;
            }
            if (e(i2) == 3) {
                e eVar = (e) b2;
                if (this.f3750e) {
                    eVar.f3769r.setText("正在加载更多案件...");
                    return;
                } else if (this.f3751f) {
                    eVar.f3769r.setText("上滑加载更多");
                    return;
                } else {
                    eVar.f3769r.setText("没有更多案件了");
                    return;
                }
            }
            h hVar = (h) this.f3748c.get(i2 - 1);
            d dVar = (d) b2;
            dVar.f3760r.setText("案件 #" + C(hVar.f3772a));
            dVar.f3763u.setText(z(hVar.f3774c, hVar.f3773b));
            dVar.f3764v.setText(z(hVar.f3777f, hVar.f3776e));
            AbstractC0455v.h(dVar.f3761s, hVar.f3775d);
            AbstractC0455v.h(dVar.f3762t, hVar.f3778g);
            dVar.f3765w.setText(PublicCourtActivity.A0(hVar.f3784m));
            dVar.f3766x.setText(y(hVar));
            String string = "举报理由\n" + D(hVar.f3779h, "(未填写)") + "\n\n证据摘要\n" + H(hVar.f3780i);
            if ("pending_review".equals(hVar.f3784m) || "closed".equals(hVar.f3784m) || "withdrawn".equals(hVar.f3784m)) {
                String str2 = "ban".equals(hVar.f3785n) ? "封禁" : "keep".equals(hVar.f3785n) ? "不封禁" : "待定";
                StringBuilder sb = new StringBuilder();
                sb.append(string);
                sb.append("\n\n阶段结果\n");
                sb.append(str2);
                if ("ban".equals(hVar.f3785n)) {
                    str = " " + hVar.f3786o + "h";
                } else {
                    str = "";
                }
                sb.append(str);
                string = sb.toString();
            }
            dVar.f3767y.setText(string);
            dVar.f3768z.setText(x(hVar));
            boolean zEquals = "open".equals(hVar.f3784m);
            dVar.f3759B.setVisibility(0);
            if (!zEquals) {
                dVar.f3759B.setEnabled(false);
                dVar.f3759B.setText("withdrawn".equals(hVar.f3784m) ? "已撤销" : "已锁定");
            } else if (hVar.f3793v) {
                dVar.f3759B.setEnabled(true);
                dVar.f3759B.setText("补充观点");
            } else {
                dVar.f3759B.setEnabled(false);
                dVar.f3759B.setText("去详情投票");
            }
            dVar.f3758A.setOnClickListener(new a(hVar));
            dVar.f1354a.setOnClickListener(new b(hVar));
            dVar.f3759B.setOnClickListener(new c(hVar));
        }

        @Override // android.support.v7.widget.RecyclerView.g
        public RecyclerView.B n(ViewGroup viewGroup, int i2) {
            LayoutInflater layoutInflaterFrom = LayoutInflater.from(viewGroup.getContext());
            return i2 == 1 ? new C0027g(layoutInflaterFrom.inflate(f0.g.V0, viewGroup, false)) : i2 == 3 ? new e(layoutInflaterFrom.inflate(f0.g.T0, viewGroup, false)) : new d(layoutInflaterFrom.inflate(f0.g.R0, viewGroup, false));
        }

        public void w(List list) {
            if (list == null || list.isEmpty()) {
                return;
            }
            int size = this.f3748c.size();
            this.f3748c.addAll(list);
            j(size + 1, list.size());
            if (G()) {
                h(c() - 1);
            }
        }

        public /* synthetic */ g(a aVar) {
            this();
        }
    }
}

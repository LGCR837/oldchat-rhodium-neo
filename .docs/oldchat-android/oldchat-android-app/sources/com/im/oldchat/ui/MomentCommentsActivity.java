package com.im.oldchat.ui;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import g0.d;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import o.c;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class MomentCommentsActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final List f3055A = new ArrayList();

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public String f3056B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public String f3057C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public String f3058D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public String f3059E;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ListView f3060w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public EditText f3061x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f3062y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public l0.P f3063z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MomentCommentsActivity.this.finish();
        }
    }

    public class b implements AdapterView.OnItemLongClickListener {
        public b() {
        }

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView adapterView, View view, int i2, long j2) {
            j0.o oVar;
            if (i2 < 0 || i2 >= MomentCommentsActivity.this.f3055A.size() || (oVar = (j0.o) MomentCommentsActivity.this.f3055A.get(i2)) == null || !MomentCommentsActivity.this.o0(oVar)) {
                return false;
            }
            MomentCommentsActivity.this.p0(oVar, i2);
            return true;
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MomentCommentsActivity.this.t0();
        }
    }

    public class d implements d.i {

        public class a implements Comparator {
            public a() {
            }

            @Override // java.util.Comparator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(j0.o oVar, j0.o oVar2) {
                long j2 = oVar.f5586h;
                long j3 = oVar2.f5586h;
                if (j2 < j3) {
                    return -1;
                }
                return j2 > j3 ? 1 : 0;
            }
        }

        public d() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("comments");
                MomentCommentsActivity.this.f3055A.clear();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    j0.o oVar = new j0.o();
                    oVar.f5579a = jSONObject.optString("id");
                    oVar.f5580b = jSONObject.optString("moment_id");
                    oVar.f5581c = jSONObject.optString("from_uid");
                    oVar.f5582d = jSONObject.optString("from_name");
                    oVar.f5583e = jSONObject.optString("from_title");
                    oVar.f5584f = jSONObject.optString("from_avatar");
                    oVar.f5585g = jSONObject.optString("body");
                    oVar.f5586h = jSONObject.optLong("created_at");
                    MomentCommentsActivity.this.f3055A.add(oVar);
                }
                Collections.sort(MomentCommentsActivity.this.f3055A, new a());
                MomentCommentsActivity momentCommentsActivity = MomentCommentsActivity.this;
                j0.n.d(momentCommentsActivity, momentCommentsActivity.f3057C, MomentCommentsActivity.this.f3055A);
                MomentCommentsActivity.this.f3063z.notifyDataSetChanged();
            } catch (Exception unused) {
                Toast.makeText(MomentCommentsActivity.this, "加载评论失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(MomentCommentsActivity.this, "加载评论失败: " + i2, 0).show();
            MomentCommentsActivity.this.r0();
        }
    }

    public class e implements d.i {
        public e() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                j0.o oVar = new j0.o();
                oVar.f5579a = jSONObject.optString("id");
                oVar.f5580b = jSONObject.optString("moment_id");
                oVar.f5581c = jSONObject.optString("from_uid");
                oVar.f5582d = jSONObject.optString("from_name");
                oVar.f5583e = jSONObject.optString("from_title");
                oVar.f5584f = jSONObject.optString("from_avatar");
                oVar.f5585g = jSONObject.optString("body");
                oVar.f5586h = jSONObject.optLong("created_at");
                MomentCommentsActivity.this.f3055A.add(0, oVar);
                MomentCommentsActivity.this.f3063z.notifyDataSetChanged();
                MomentCommentsActivity.this.f3061x.setText("");
            } catch (Exception unused) {
                Toast.makeText(MomentCommentsActivity.this, "发送失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(MomentCommentsActivity.this, "发送失败: " + i2, 0).show();
        }
    }

    public class f implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.o f3070a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f3071b;

        public f(j0.o oVar, int i2) {
            this.f3070a = oVar;
            this.f3071b = i2;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            MomentCommentsActivity.this.q0(this.f3070a, this.f3071b);
        }
    }

    public class g implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3073a;

        public g(int i2) {
            this.f3073a = i2;
        }

        @Override // g0.d.i
        public void b(String str) {
            int i2 = this.f3073a;
            if (i2 >= 0 && i2 < MomentCommentsActivity.this.f3055A.size()) {
                MomentCommentsActivity.this.f3055A.remove(this.f3073a);
                MomentCommentsActivity.this.f3063z.notifyDataSetChanged();
                MomentCommentsActivity momentCommentsActivity = MomentCommentsActivity.this;
                j0.n.d(momentCommentsActivity, momentCommentsActivity.f3057C, MomentCommentsActivity.this.f3055A);
            }
            Toast.makeText(MomentCommentsActivity.this, f0.j.E1, 0).show();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(MomentCommentsActivity.this, f0.j.D1, 0).show();
        }
    }

    public final boolean o0(j0.o oVar) {
        String str;
        if (oVar == null || (str = this.f3058D) == null || str.isEmpty()) {
            return false;
        }
        if (this.f3058D.equals(oVar.f5581c)) {
            return true;
        }
        String str2 = this.f3059E;
        return str2 != null && this.f3058D.equals(str2);
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5173z);
        this.f3060w = (ListView) a0(f0.f.J6);
        this.f3061x = (EditText) a0(f0.f.q4);
        this.f3062y = (TextView) a0(f0.f.F3);
        View viewA0 = a0(f0.f.f5060U);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        SharedPreferences sharedPreferences = getSharedPreferences("auth", 0);
        this.f3056B = sharedPreferences.getString("access_token", "");
        this.f3058D = sharedPreferences.getString("my_uid", "");
        this.f3057C = getIntent().getStringExtra("moment_id");
        this.f3059E = getIntent().getStringExtra("moment_owner_uid");
        l0.P p2 = new l0.P(this, this.f3055A);
        this.f3063z = p2;
        this.f3060w.setAdapter((ListAdapter) p2);
        this.f3060w.setOnItemLongClickListener(new b());
        this.f3062y.setOnClickListener(new c());
        s0();
    }

    public final void p0(j0.o oVar, int i2) {
        new c.a(this).s(f0.j.F1).h(f0.j.C1).p(f0.j.G1, new f(oVar, i2)).j(f0.j.f5203a, null).v();
    }

    public final void q0(j0.o oVar, int i2) {
        String str;
        if (oVar == null || (str = oVar.f5579a) == null || str.isEmpty()) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("comment_id", oVar.f5579a);
            g0.d.S("/moments/comment/delete", jSONObject, this.f3056B, new g(i2));
        } catch (Exception unused) {
            Toast.makeText(this, f0.j.D1, 0).show();
        }
    }

    public final void r0() {
        List listB = j0.n.b(this, this.f3057C);
        if (listB == null || listB.isEmpty()) {
            return;
        }
        this.f3055A.clear();
        this.f3055A.addAll(listB);
        this.f3063z.notifyDataSetChanged();
    }

    public final void s0() {
        String str = this.f3057C;
        if (str == null || str.isEmpty()) {
            return;
        }
        String str2 = this.f3056B;
        if (str2 == null || str2.isEmpty() || !o0.G.d().f()) {
            r0();
            return;
        }
        g0.d.R("/moments/comments?moment_id=" + this.f3057C + "&limit=50", this.f3056B, new d());
    }

    public final void t0() {
        String strTrim = this.f3061x.getText().toString().trim();
        if (strTrim.isEmpty()) {
            Toast.makeText(this, "请输入评论内容", 0).show();
            return;
        }
        String str = this.f3057C;
        if (str == null || str.isEmpty()) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("moment_id", this.f3057C);
            jSONObject.put("body", strTrim);
            g0.d.S("/moments/comment", jSONObject, this.f3056B, new e());
        } catch (Exception unused) {
            Toast.makeText(this, "发送失败", 0).show();
        }
    }
}

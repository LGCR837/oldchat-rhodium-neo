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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import l0.B0;
import o.c;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ResourceCommentsActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final List f4048A = new ArrayList();

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public String f4049B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public String f4050C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public String f4051D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public String f4052E;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ListView f4053w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public EditText f4054x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f4055y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public B0 f4056z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ResourceCommentsActivity.this.finish();
        }
    }

    public class b implements AdapterView.OnItemLongClickListener {
        public b() {
        }

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView adapterView, View view, int i2, long j2) {
            j0.s sVar;
            if (i2 < 0 || i2 >= ResourceCommentsActivity.this.f4048A.size() || (sVar = (j0.s) ResourceCommentsActivity.this.f4048A.get(i2)) == null || !ResourceCommentsActivity.this.m0(sVar)) {
                return false;
            }
            ResourceCommentsActivity.this.n0(sVar, i2);
            return true;
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ResourceCommentsActivity.this.q0();
        }
    }

    public class d implements d.i {

        public class a implements Comparator {
            public a() {
            }

            @Override // java.util.Comparator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(j0.s sVar, j0.s sVar2) {
                long j2 = sVar.f5617h;
                long j3 = sVar2.f5617h;
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
                ResourceCommentsActivity.this.f4048A.clear();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    j0.s sVar = new j0.s();
                    sVar.f5610a = jSONObject.optString("id");
                    sVar.f5611b = jSONObject.optString("item_id");
                    sVar.f5612c = jSONObject.optString("from_uid");
                    sVar.f5613d = jSONObject.optString("from_name");
                    sVar.f5614e = jSONObject.optString("from_title");
                    sVar.f5615f = jSONObject.optString("from_avatar");
                    sVar.f5616g = jSONObject.optString("body");
                    sVar.f5617h = jSONObject.optLong("created_at");
                    ResourceCommentsActivity.this.f4048A.add(sVar);
                }
                Collections.sort(ResourceCommentsActivity.this.f4048A, new a());
                ResourceCommentsActivity.this.f4056z.notifyDataSetChanged();
            } catch (Exception unused) {
                Toast.makeText(ResourceCommentsActivity.this, "加载评论失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(ResourceCommentsActivity.this, "加载评论失败: " + i2, 0).show();
        }
    }

    public class e implements d.i {
        public e() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                j0.s sVar = new j0.s();
                sVar.f5610a = jSONObject.optString("id");
                sVar.f5611b = jSONObject.optString("item_id");
                sVar.f5612c = jSONObject.optString("from_uid");
                sVar.f5613d = jSONObject.optString("from_name");
                sVar.f5614e = jSONObject.optString("from_title");
                sVar.f5615f = jSONObject.optString("from_avatar");
                sVar.f5616g = jSONObject.optString("body");
                sVar.f5617h = jSONObject.optLong("created_at");
                ResourceCommentsActivity.this.f4048A.add(0, sVar);
                ResourceCommentsActivity.this.f4056z.notifyDataSetChanged();
                ResourceCommentsActivity.this.f4054x.setText("");
            } catch (Exception unused) {
                Toast.makeText(ResourceCommentsActivity.this, "发送失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(ResourceCommentsActivity.this, "发送失败: " + i2, 0).show();
        }
    }

    public class f implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.s f4063a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f4064b;

        public f(j0.s sVar, int i2) {
            this.f4063a = sVar;
            this.f4064b = i2;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            ResourceCommentsActivity.this.o0(this.f4063a, this.f4064b);
        }
    }

    public class g implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f4066a;

        public g(int i2) {
            this.f4066a = i2;
        }

        @Override // g0.d.i
        public void b(String str) {
            int i2 = this.f4066a;
            if (i2 >= 0 && i2 < ResourceCommentsActivity.this.f4048A.size()) {
                ResourceCommentsActivity.this.f4048A.remove(this.f4066a);
                ResourceCommentsActivity.this.f4056z.notifyDataSetChanged();
            }
            Toast.makeText(ResourceCommentsActivity.this, "已删除", 0).show();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(ResourceCommentsActivity.this, "删除失败", 0).show();
        }
    }

    private void p0() {
        String str;
        String strEncode;
        String str2 = this.f4051D;
        if (str2 == null || str2.isEmpty() || (str = this.f4049B) == null || str.isEmpty() || !o0.G.d().f()) {
            return;
        }
        try {
            strEncode = URLEncoder.encode(this.f4051D, "UTF-8");
        } catch (Exception unused) {
            strEncode = this.f4051D;
        }
        g0.d.R("/resources/comments?item_id=" + strEncode + "&limit=50", this.f4049B, new d());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q0() {
        String strTrim = this.f4054x.getText().toString().trim();
        if (strTrim.isEmpty()) {
            Toast.makeText(this, "请输入评论内容", 0).show();
            return;
        }
        String str = this.f4051D;
        if (str == null || str.isEmpty()) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("item_id", this.f4051D);
            jSONObject.put("body", strTrim);
            g0.d.S("/resources/comment", jSONObject, this.f4049B, new e());
        } catch (Exception unused) {
            Toast.makeText(this, "发送失败", 0).show();
        }
    }

    public final boolean m0(j0.s sVar) {
        String str;
        if (sVar == null || (str = this.f4050C) == null || str.isEmpty()) {
            return false;
        }
        if (this.f4050C.equals(sVar.f5612c)) {
            return true;
        }
        String str2 = this.f4052E;
        return str2 != null && this.f4050C.equals(str2);
    }

    public final void n0(j0.s sVar, int i2) {
        new c.a(this).t("删除评论").i("确定删除这条评论吗？").q("删除", new f(sVar, i2)).k("取消", null).v();
    }

    public final void o0(j0.s sVar, int i2) {
        String str;
        if (sVar == null || (str = sVar.f5610a) == null || str.isEmpty()) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("comment_id", sVar.f5610a);
            g0.d.S("/resources/comment/delete", jSONObject, this.f4049B, new g(i2));
        } catch (Exception unused) {
            Toast.makeText(this, "删除失败", 0).show();
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5173z);
        this.f4053w = (ListView) a0(f0.f.J6);
        this.f4054x = (EditText) a0(f0.f.q4);
        this.f4055y = (TextView) a0(f0.f.F3);
        TextView textView = (TextView) a0(f0.f.s9);
        if (textView != null) {
            textView.setText("资源评论");
        }
        View viewA0 = a0(f0.f.f5060U);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        SharedPreferences sharedPreferences = getSharedPreferences("auth", 0);
        this.f4049B = sharedPreferences.getString("access_token", "");
        this.f4050C = sharedPreferences.getString("my_uid", "");
        this.f4051D = getIntent().getStringExtra("item_id");
        this.f4052E = getIntent().getStringExtra("section_owner_uid");
        B0 b02 = new B0(this, this.f4048A);
        this.f4056z = b02;
        this.f4053w.setAdapter((ListAdapter) b02);
        this.f4053w.setOnItemLongClickListener(new b());
        this.f4055y.setOnClickListener(new c());
        p0();
    }
}

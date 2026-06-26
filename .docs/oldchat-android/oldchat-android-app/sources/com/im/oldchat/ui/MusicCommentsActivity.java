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
import o.c;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class MusicCommentsActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final List f3141A = new ArrayList();

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public String f3142B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public String f3143C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public String f3144D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public String f3145E;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ListView f3146w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public EditText f3147x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f3148y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public l0.X f3149z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicCommentsActivity.this.finish();
        }
    }

    public class b implements AdapterView.OnItemLongClickListener {
        public b() {
        }

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView adapterView, View view, int i2, long j2) {
            j0.q qVar;
            if (i2 < 0 || i2 >= MusicCommentsActivity.this.f3141A.size() || (qVar = (j0.q) MusicCommentsActivity.this.f3141A.get(i2)) == null || !MusicCommentsActivity.this.m0(qVar)) {
                return false;
            }
            MusicCommentsActivity.this.n0(qVar, i2);
            return true;
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicCommentsActivity.this.q0();
        }
    }

    public class d implements d.i {

        public class a implements Comparator {
            public a() {
            }

            @Override // java.util.Comparator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(j0.q qVar, j0.q qVar2) {
                long j2 = qVar.f5602h;
                long j3 = qVar2.f5602h;
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
                JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("comments");
                MusicCommentsActivity.this.f3141A.clear();
                if (jSONArrayOptJSONArray != null) {
                    for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                        JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i2);
                        if (jSONObjectOptJSONObject != null) {
                            j0.q qVar = new j0.q();
                            qVar.f5595a = jSONObjectOptJSONObject.optString("id");
                            qVar.f5596b = jSONObjectOptJSONObject.optString("item_id");
                            qVar.f5597c = jSONObjectOptJSONObject.optString("from_uid");
                            qVar.f5598d = jSONObjectOptJSONObject.optString("from_name");
                            qVar.f5599e = jSONObjectOptJSONObject.optString("from_title");
                            qVar.f5600f = jSONObjectOptJSONObject.optString("from_avatar");
                            qVar.f5601g = jSONObjectOptJSONObject.optString("body");
                            qVar.f5602h = jSONObjectOptJSONObject.optLong("created_at");
                            MusicCommentsActivity.this.f3141A.add(qVar);
                        }
                    }
                }
                Collections.sort(MusicCommentsActivity.this.f3141A, new a());
                MusicCommentsActivity.this.f3149z.notifyDataSetChanged();
            } catch (Exception unused) {
                Toast.makeText(MusicCommentsActivity.this, "加载评论失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(MusicCommentsActivity.this, "加载评论失败: " + i2, 0).show();
        }
    }

    public class e implements d.i {

        public class a implements Comparator {
            public a() {
            }

            @Override // java.util.Comparator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(j0.q qVar, j0.q qVar2) {
                long j2 = qVar.f5602h;
                long j3 = qVar2.f5602h;
                if (j2 < j3) {
                    return -1;
                }
                return j2 > j3 ? 1 : 0;
            }
        }

        public e() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                j0.q qVar = new j0.q();
                qVar.f5595a = jSONObject.optString("id");
                qVar.f5596b = jSONObject.optString("item_id");
                qVar.f5597c = jSONObject.optString("from_uid");
                qVar.f5598d = jSONObject.optString("from_name");
                qVar.f5599e = jSONObject.optString("from_title");
                qVar.f5600f = jSONObject.optString("from_avatar");
                qVar.f5601g = jSONObject.optString("body");
                qVar.f5602h = jSONObject.optLong("created_at");
                MusicCommentsActivity.this.f3141A.add(qVar);
                Collections.sort(MusicCommentsActivity.this.f3141A, new a());
                MusicCommentsActivity.this.f3149z.notifyDataSetChanged();
                if (MusicCommentsActivity.this.f3147x != null) {
                    MusicCommentsActivity.this.f3147x.setText("");
                }
            } catch (Exception unused) {
                Toast.makeText(MusicCommentsActivity.this, "发送失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(MusicCommentsActivity.this, "发送失败: " + i2, 0).show();
        }
    }

    public class f implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.q f3157a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f3158b;

        public f(j0.q qVar, int i2) {
            this.f3157a = qVar;
            this.f3158b = i2;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            MusicCommentsActivity.this.o0(this.f3157a, this.f3158b);
        }
    }

    public class g implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3160a;

        public g(int i2) {
            this.f3160a = i2;
        }

        @Override // g0.d.i
        public void b(String str) {
            int i2 = this.f3160a;
            if (i2 >= 0 && i2 < MusicCommentsActivity.this.f3141A.size()) {
                MusicCommentsActivity.this.f3141A.remove(this.f3160a);
                MusicCommentsActivity.this.f3149z.notifyDataSetChanged();
            }
            Toast.makeText(MusicCommentsActivity.this, "已删除", 0).show();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(MusicCommentsActivity.this, "删除失败", 0).show();
        }
    }

    private void p0() {
        String str;
        String strEncode;
        String str2 = this.f3144D;
        if (str2 == null || str2.length() == 0 || (str = this.f3142B) == null || str.length() == 0 || !o0.G.d().f()) {
            return;
        }
        try {
            strEncode = URLEncoder.encode(this.f3144D, "UTF-8");
        } catch (Exception unused) {
            strEncode = this.f3144D;
        }
        g0.d.R("/music/plaza/comments?item_id=" + strEncode + "&limit=50", this.f3142B, new d());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q0() {
        String str = this.f3144D;
        if (str == null || str.length() == 0) {
            return;
        }
        EditText editText = this.f3147x;
        String strTrim = (editText == null || editText.getText() == null) ? "" : this.f3147x.getText().toString().trim();
        if (strTrim.length() == 0) {
            Toast.makeText(this, "请输入评论内容", 0).show();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("item_id", this.f3144D);
            jSONObject.put("body", strTrim);
            g0.d.S("/music/plaza/comment", jSONObject, this.f3142B, new e());
        } catch (Exception unused) {
            Toast.makeText(this, "发送失败", 0).show();
        }
    }

    public final boolean m0(j0.q qVar) {
        String str;
        if (qVar == null || (str = this.f3143C) == null || str.length() == 0) {
            return false;
        }
        if (this.f3143C.equals(qVar.f5597c)) {
            return true;
        }
        String str2 = this.f3145E;
        return str2 != null && this.f3143C.equals(str2);
    }

    public final void n0(j0.q qVar, int i2) {
        new c.a(this).t("删除评论").i("确定删除这条评论吗？").q("删除", new f(qVar, i2)).k("取消", null).v();
    }

    public final void o0(j0.q qVar, int i2) {
        String str;
        if (qVar == null || (str = qVar.f5595a) == null || str.length() == 0) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("comment_id", qVar.f5595a);
            g0.d.S("/music/plaza/comment/delete", jSONObject, this.f3142B, new g(i2));
        } catch (Exception unused) {
            Toast.makeText(this, "删除失败", 0).show();
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5173z);
        this.f3146w = (ListView) a0(f0.f.J6);
        this.f3147x = (EditText) a0(f0.f.q4);
        this.f3148y = (TextView) a0(f0.f.F3);
        TextView textView = (TextView) a0(f0.f.s9);
        if (textView != null) {
            textView.setText("歌曲评论");
        }
        View viewA0 = a0(f0.f.f5060U);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        SharedPreferences sharedPreferences = getSharedPreferences("auth", 0);
        this.f3142B = sharedPreferences.getString("access_token", "");
        this.f3143C = sharedPreferences.getString("my_uid", "");
        this.f3144D = getIntent().getStringExtra("item_id");
        this.f3145E = getIntent().getStringExtra("owner_uid");
        l0.X x2 = new l0.X(this, this.f3141A);
        this.f3149z = x2;
        this.f3146w.setAdapter((ListAdapter) x2);
        this.f3146w.setOnItemLongClickListener(new b());
        TextView textView2 = this.f3148y;
        if (textView2 != null) {
            textView2.setOnClickListener(new c());
        }
        p0();
    }
}

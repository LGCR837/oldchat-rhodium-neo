package com.im.oldchat.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import g0.d;
import java.util.ArrayList;
import java.util.List;
import l0.R0;
import o0.AbstractC0455v;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GroupJoinRequestsActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public d f2936A;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ListView f2937w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public String f2938x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public String f2939y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final List f2940z = new ArrayList();

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            GroupJoinRequestsActivity.this.finish();
        }
    }

    public class b implements d.i {
        public b() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("requests");
                GroupJoinRequestsActivity.this.f2940z.clear();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    j0.g gVar = new j0.g();
                    gVar.f5515a = jSONObject.optString("request_id");
                    gVar.f5516b = jSONObject.optString("uid");
                    gVar.f5517c = jSONObject.optString("username");
                    gVar.f5518d = jSONObject.optString("display_name");
                    gVar.f5519e = jSONObject.optString("user_title");
                    gVar.f5520f = jSONObject.optString("avatar_url");
                    gVar.f5521g = jSONObject.optLong("created_at", 0L);
                    GroupJoinRequestsActivity.this.f2940z.add(gVar);
                }
                GroupJoinRequestsActivity.this.f2936A.notifyDataSetChanged();
            } catch (Exception unused) {
                Toast.makeText(GroupJoinRequestsActivity.this, "加载失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(GroupJoinRequestsActivity.this, "加载失败: " + i2, 0).show();
        }
    }

    public class c implements d.i {
        public c() {
        }

        @Override // g0.d.i
        public void b(String str) {
            GroupJoinRequestsActivity.this.k0();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(GroupJoinRequestsActivity.this, "操作失败: " + i2, 0).show();
        }
    }

    public class d extends BaseAdapter {

        public class a implements View.OnClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ j0.g f2945a;

            public a(j0.g gVar) {
                this.f2945a = gVar;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GroupJoinRequestsActivity.this.j0(this.f2945a.f5515a);
            }
        }

        public d() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return GroupJoinRequestsActivity.this.f2940z.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            return GroupJoinRequestsActivity.this.f2940z.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            e eVar;
            if (view == null) {
                view = LayoutInflater.from(GroupJoinRequestsActivity.this).inflate(f0.g.D0, viewGroup, false);
                eVar = new e(view);
                view.setTag(eVar);
            } else {
                eVar = (e) view.getTag();
            }
            j0.g gVar = (j0.g) GroupJoinRequestsActivity.this.f2940z.get(i2);
            String str = gVar.f5518d;
            eVar.f2948b.setText((str == null || str.isEmpty()) ? gVar.f5517c : gVar.f5518d);
            R0.a(eVar.f2950d, gVar.f5519e);
            eVar.f2949c.setText(gVar.f5516b);
            AbstractC0455v.h(eVar.f2947a, gVar.f5520f);
            eVar.f2951e.setOnClickListener(new a(gVar));
            return view;
        }

        public /* synthetic */ d(GroupJoinRequestsActivity groupJoinRequestsActivity, a aVar) {
            this();
        }
    }

    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ImageView f2947a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f2948b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final TextView f2949c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final TextView f2950d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final TextView f2951e;

        public e(View view) {
            this.f2947a = (ImageView) view.findViewById(f0.f.m5);
            this.f2948b = (TextView) view.findViewById(f0.f.Yd);
            this.f2949c = (TextView) view.findViewById(f0.f.Vd);
            this.f2950d = (TextView) view.findViewById(f0.f.Zd);
            this.f2951e = (TextView) view.findViewById(f0.f.f5101u);
        }
    }

    public final void j0(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("request_id", str);
            jSONObject.put("accept", true);
            g0.d.S("/groups/approve", jSONObject, this.f2938x, new c());
        } catch (Exception unused) {
            Toast.makeText(this, "操作失败", 0).show();
        }
    }

    public final void k0() {
        String str = this.f2939y;
        if (str == null || str.isEmpty()) {
            return;
        }
        g0.d.R("/groups/requests?group_id=" + this.f2939y, this.f2938x, new b());
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5169v);
        this.f2937w = (ListView) a0(f0.f.c7);
        this.f2939y = getIntent().getStringExtra("group_id");
        this.f2938x = getSharedPreferences("auth", 0).getString("access_token", "");
        View viewA0 = a0(f0.f.d1);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        d dVar = new d(this, null);
        this.f2936A = dVar;
        this.f2937w.setAdapter((ListAdapter) dVar);
        k0();
    }
}

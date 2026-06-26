package com.im.oldchat.ui;

import android.R;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import g0.d;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GroupCreateActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public ArrayAdapter f2894A;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public EditText f2895w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public ListView f2896x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public String f2897y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final List f2898z = new ArrayList();

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            GroupCreateActivity.this.k0();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            GroupCreateActivity.this.finish();
        }
    }

    public class c implements d.i {
        public c() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("friends");
                GroupCreateActivity.this.f2898z.clear();
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    j0.w wVar = new j0.w();
                    wVar.f5645a = jSONObject.optString("id");
                    wVar.f5646b = jSONObject.optString("uid");
                    wVar.f5647c = jSONObject.optString("username");
                    wVar.f5648d = jSONObject.optString("display_name");
                    wVar.f5649e = jSONObject.optString("remark_name", "");
                    wVar.f5650f = jSONObject.optString("user_title");
                    wVar.f5651g = jSONObject.optString("avatar_url");
                    GroupCreateActivity.this.f2898z.add(wVar);
                    arrayList.add(j0.d.b(wVar) + " (" + wVar.f5646b + ")");
                }
                GroupCreateActivity.this.f2894A = new ArrayAdapter(GroupCreateActivity.this, R.layout.simple_list_item_multiple_choice, arrayList);
                GroupCreateActivity.this.f2896x.setAdapter((ListAdapter) GroupCreateActivity.this.f2894A);
            } catch (Exception unused) {
                Toast.makeText(GroupCreateActivity.this, "加载好友失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(GroupCreateActivity.this, "加载好友失败: " + i2, 0).show();
        }
    }

    public class d implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2902a;

        public d(String str) {
            this.f2902a = str;
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                String strOptString = new JSONObject(str).optString("group_id");
                Intent intent = new Intent(GroupCreateActivity.this, (Class<?>) GroupChatActivity.class);
                intent.putExtra("group_id", strOptString);
                intent.putExtra("group_name", this.f2902a);
                GroupCreateActivity.this.startActivity(intent);
                GroupCreateActivity.this.finish();
            } catch (Exception unused) {
                Toast.makeText(GroupCreateActivity.this, "创建失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(GroupCreateActivity.this, "创建失败: " + i2, 0).show();
        }
    }

    public final void k0() {
        j0.w wVar;
        String str;
        String strTrim = this.f2895w.getText().toString().trim();
        if (strTrim.isEmpty()) {
            Toast.makeText(this, "请输入群名称", 0).show();
            return;
        }
        SparseBooleanArray checkedItemPositions = this.f2896x.getCheckedItemPositions();
        JSONArray jSONArray = new JSONArray();
        for (int i2 = 0; i2 < this.f2898z.size(); i2++) {
            if (checkedItemPositions != null && checkedItemPositions.get(i2) && (str = (wVar = (j0.w) this.f2898z.get(i2)).f5646b) != null && !str.isEmpty()) {
                jSONArray.put(wVar.f5646b);
            }
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("name", strTrim);
            jSONObject.put("member_uids", jSONArray);
            g0.d.S("/groups/create", jSONObject, this.f2897y, new d(strTrim));
        } catch (Exception unused) {
            Toast.makeText(this, "创建失败", 0).show();
        }
    }

    public final void l0() {
        g0.d.R("/friends", this.f2897y, new c());
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5165r);
        this.f2895w = (EditText) a0(f0.f.C4);
        this.f2896x = (ListView) a0(f0.f.N6);
        this.f2897y = getSharedPreferences("auth", 0).getString("access_token", "");
        a0(f0.f.f5063X).setOnClickListener(new a());
        View viewA0 = a0(f0.f.a1);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new b());
        }
        l0();
    }
}

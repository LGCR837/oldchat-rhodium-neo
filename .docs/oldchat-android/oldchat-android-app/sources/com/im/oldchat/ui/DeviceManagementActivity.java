package com.im.oldchat.ui;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.C0224o;
import g0.d;
import java.util.ArrayList;
import java.util.List;
import k.AbstractC0285k0;
import o.c;
import o0.AbstractC0445k;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class DeviceManagementActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final List f2597A = new ArrayList();

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public String f2598B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public String f2599C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public boolean f2600D;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ListView f2601w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public TextView f2602x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f2603y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public C0224o f2604z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DeviceManagementActivity.this.finish();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DeviceManagementActivity.this.l0();
        }
    }

    public class c implements d.i {
        public c() {
        }

        @Override // g0.d.i
        public void b(String str) {
            DeviceManagementActivity.this.p0(str);
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(DeviceManagementActivity.this, "加载失败: " + i2, 0).show();
        }
    }

    public class d implements DialogInterface.OnClickListener {
        public d() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            DeviceManagementActivity.this.k0();
        }
    }

    public class e implements d.i {
        public e() {
        }

        @Override // g0.d.i
        public void b(String str) {
            DeviceManagementActivity.this.r0(false);
            DeviceManagementActivity.this.n0(str);
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            DeviceManagementActivity.this.r0(false);
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(DeviceManagementActivity.this, "操作失败: " + i2, 0).show();
        }
    }

    public final void k0() {
        try {
            JSONObject jSONObject = new JSONObject();
            String str = this.f2599C;
            if (str == null) {
                str = "";
            }
            jSONObject.put("device_id", str);
            jSONObject.put("device_name", AbstractC0445k.c());
            jSONObject.put("imei", AbstractC0445k.d(this));
            jSONObject.put("platform", "android");
            jSONObject.put("app_version", AbstractC0445k.a(this));
            r0(true);
            g0.d.S("/me/devices/cleanup-others", jSONObject, this.f2598B, new e());
        } catch (Exception unused) {
            r0(false);
            Toast.makeText(this, "请求失败", 0).show();
        }
    }

    public final void l0() {
        if (this.f2600D) {
            return;
        }
        String str = this.f2598B;
        if (str == null || str.length() == 0) {
            Toast.makeText(this, "未登录", 0).show();
            return;
        }
        String str2 = this.f2599C;
        if (str2 == null || str2.trim().length() == 0) {
            Toast.makeText(this, "本机设备标识不可用", 0).show();
            return;
        }
        int iM0 = m0();
        if (iM0 <= 0) {
            Toast.makeText(this, "没有陌生登录设备", 0).show();
            return;
        }
        c.a aVar = new c.a(this, f0.k.f5244a);
        aVar.t("安全提醒");
        aVar.i("将删除并下线其他设备（" + iM0 + " 台），是否继续？");
        aVar.k("取消", null);
        aVar.q("继续", new d());
        aVar.v();
    }

    public final int m0() {
        List list = this.f2597A;
        if (list == null || list.isEmpty()) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.f2597A.size(); i3++) {
            C0224o.a aVar = (C0224o.a) this.f2597A.get(i3);
            String str = aVar == null ? "" : aVar.f4697a;
            if (!(str != null ? str : "").equals(this.f2599C)) {
                i2++;
            }
        }
        return i2;
    }

    public final void n0(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            q0(jSONObject.optString("access_token", ""), jSONObject.optString("refresh_token", ""));
            long jOptLong = jSONObject.optLong("removed_count", 0L);
            if (jOptLong > 0) {
                Toast.makeText(this, "已下线 " + jOptLong + " 台陌生设备", 0).show();
            } else {
                Toast.makeText(this, "未发现陌生设备", 0).show();
            }
            o0();
        } catch (Exception unused) {
            Toast.makeText(this, "解析失败", 0).show();
        }
    }

    public final void o0() {
        String str = this.f2598B;
        if (str == null || str.isEmpty()) {
            Toast.makeText(this, "未登录", 0).show();
            return;
        }
        TextView textView = this.f2602x;
        if (textView != null) {
            textView.setVisibility(8);
        }
        g0.d.R("/me/devices", this.f2598B, new c());
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5151j);
        View viewA0 = a0(f0.f.f5077f0);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        this.f2601w = (ListView) a0(f0.f.K6);
        this.f2602x = (TextView) a0(f0.f.K9);
        this.f2603y = (TextView) a0(f0.f.f5047H);
        this.f2598B = getSharedPreferences("auth", 0).getString("access_token", "");
        this.f2599C = AbstractC0445k.b(this);
        C0224o c0224o = new C0224o(this, this.f2597A, this.f2599C);
        this.f2604z = c0224o;
        ListView listView = this.f2601w;
        if (listView != null) {
            listView.setAdapter((ListAdapter) c0224o);
        }
        TextView textView = this.f2603y;
        if (textView != null) {
            textView.setOnClickListener(new b());
            r0(false);
        }
        o0();
    }

    public final void p0(String str) {
        try {
            JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("devices");
            this.f2597A.clear();
            if (jSONArrayOptJSONArray != null) {
                for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                    JSONObject jSONObject = jSONArrayOptJSONArray.getJSONObject(i2);
                    C0224o.a aVar = new C0224o.a();
                    aVar.f4697a = jSONObject.optString("device_id", "");
                    aVar.f4698b = jSONObject.optString("device_name", "");
                    aVar.f4699c = jSONObject.optString("platform", "");
                    aVar.f4700d = jSONObject.optString("app_version", "");
                    aVar.f4701e = jSONObject.optLong("last_seen", 0L);
                    this.f2597A.add(aVar);
                }
            }
            C0224o c0224o = this.f2604z;
            if (c0224o != null) {
                c0224o.notifyDataSetChanged();
            }
            TextView textView = this.f2602x;
            if (textView != null) {
                textView.setVisibility(this.f2597A.isEmpty() ? 0 : 8);
            }
        } catch (Exception unused) {
            Toast.makeText(this, "解析失败", 0).show();
        }
    }

    public final void q0(String str, String str2) {
        if (str == null || str.length() == 0) {
            return;
        }
        SharedPreferences.Editor editorEdit = getSharedPreferences("auth", 0).edit();
        editorEdit.putString("access_token", str);
        if (str2 != null && str2.length() > 0) {
            editorEdit.putString("refresh_token", str2);
        }
        editorEdit.apply();
        this.f2598B = str;
    }

    public final void r0(boolean z2) {
        this.f2600D = z2;
        TextView textView = this.f2603y;
        if (textView == null) {
            return;
        }
        textView.setEnabled(!z2);
        AbstractC0285k0.K(this.f2603y, z2 ? 0.6f : 1.0f);
    }
}

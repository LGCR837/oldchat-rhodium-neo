package com.im.oldchat.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import g0.d;
import l0.y0;
import o0.AbstractC0452s;
import o0.AbstractC0455v;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ProfileEditActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public EditText f3687A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public View f3688B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public View f3689C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public View f3690D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public String f3691E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public String f3692F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public String f3693G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public String f3694H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public String f3695I;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ImageView f3696w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public TextView f3697x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f3698y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public EditText f3699z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ProfileEditActivity.this.finish();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ProfileEditActivity profileEditActivity = ProfileEditActivity.this;
            y0.a(profileEditActivity, profileEditActivity.f3694H, ProfileEditActivity.this.f3693G, ProfileEditActivity.this.f3697x);
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ProfileEditActivity.this.z0();
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ProfileEditActivity.this.B0();
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ProfileEditActivity.this.D0();
        }
    }

    public class f implements d.i {
        public f() {
        }

        @Override // g0.d.i
        public void b(String str) {
            String str2 = "";
            try {
                JSONObject jSONObject = new JSONObject(str);
                String strOptString = jSONObject.optString("uid", "");
                String strOptString2 = jSONObject.optString("username", "");
                String strY0 = ProfileEditActivity.this.y0(jSONObject.optString("display_name", ""));
                ProfileEditActivity.this.f3692F = jSONObject.optString("avatar_url", "");
                ProfileEditActivity.this.f3693G = strY0;
                ProfileEditActivity.this.f3694H = strOptString;
                ProfileEditActivity.this.f3695I = strOptString2;
                String str3 = (strY0 == null || strY0.isEmpty()) ? strOptString : strY0;
                TextView textView = ProfileEditActivity.this.f3697x;
                if (str3 != null) {
                    str2 = str3;
                }
                textView.setText(str2);
                ProfileEditActivity.this.C0(strOptString, strOptString2);
                ProfileEditActivity.this.f3699z.setText(strY0);
                if (strOptString != null && !strOptString.isEmpty()) {
                    ProfileEditActivity.this.f3687A.setText(strOptString);
                }
                AbstractC0455v.h(ProfileEditActivity.this.f3696w, ProfileEditActivity.this.f3692F);
            } catch (Exception unused) {
                Toast.makeText(ProfileEditActivity.this, "加载资料失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(ProfileEditActivity.this, "加载资料失败: " + i2, 0).show();
        }
    }

    public class g implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f3706a;

        public g(String str) {
            this.f3706a = str;
        }

        @Override // g0.d.i
        public void b(String str) {
            ProfileEditActivity.this.f3693G = this.f3706a;
            ProfileEditActivity.this.A0();
            Toast.makeText(ProfileEditActivity.this, "保存成功", 0).show();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(ProfileEditActivity.this, "保存失败: " + i2, 0).show();
        }
    }

    public class h implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f3708a;

        public h(String str) {
            this.f3708a = str;
        }

        @Override // g0.d.i
        public void b(String str) {
            SharedPreferences sharedPreferences = ProfileEditActivity.this.getSharedPreferences("auth", 0);
            o0.F.e(ProfileEditActivity.this, sharedPreferences.getString("my_uid", ""), this.f3708a);
            sharedPreferences.edit().putString("my_uid", this.f3708a).apply();
            ProfileEditActivity.this.f3694H = this.f3708a;
            ProfileEditActivity.this.A0();
            ProfileEditActivity profileEditActivity = ProfileEditActivity.this;
            profileEditActivity.C0(profileEditActivity.f3694H, ProfileEditActivity.this.f3695I);
            Toast.makeText(ProfileEditActivity.this, "旧聊ID已更新", 0).show();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(ProfileEditActivity.this, "更新失败: " + i2, 0).show();
        }
    }

    public class i implements d.i {
        public i() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                ProfileEditActivity.this.f3692F = jSONObject.optString("avatar_url", "");
                AbstractC0455v.h(ProfileEditActivity.this.f3696w, ProfileEditActivity.this.f3692F);
                Toast.makeText(ProfileEditActivity.this, "头像已更新", 0).show();
            } catch (Exception unused) {
                Toast.makeText(ProfileEditActivity.this, "头像更新失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(ProfileEditActivity.this, "头像上传失败: " + i2, 0).show();
        }
    }

    private void x0() {
        g0.d.R("/me", this.f3691E, new f());
    }

    public final void A0() {
        String str = this.f3693G;
        if (str == null || str.isEmpty()) {
            str = this.f3694H;
        }
        TextView textView = this.f3697x;
        if (str == null) {
            str = "";
        }
        textView.setText(str);
    }

    public final void B0() {
        String strY0 = y0(this.f3699z.getText().toString());
        if (strY0.isEmpty()) {
            strY0 = y0(this.f3693G);
        }
        if (strY0 == null || strY0.isEmpty()) {
            Toast.makeText(this, "昵称不能为空", 0).show();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("display_name", strY0);
            String str = this.f3692F;
            if (str == null) {
                str = "";
            }
            jSONObject.put("avatar_url", str);
            g0.d.S("/me/profile", jSONObject, this.f3691E, new g(strY0));
        } catch (Exception unused) {
            Toast.makeText(this, "保存失败", 0).show();
        }
    }

    public final void C0(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        if (str != null && !str.isEmpty()) {
            sb.append("旧聊ID: ");
            sb.append(str);
        }
        if (str2 != null && !str2.isEmpty()) {
            if (sb.length() > 0) {
                sb.append("  ");
            }
            sb.append("用户名: ");
            sb.append(str2);
        }
        this.f3698y.setText(sb.toString());
    }

    public final void D0() {
        String upperCase = this.f3687A.getText().toString().trim().toUpperCase();
        if (upperCase.isEmpty()) {
            Toast.makeText(this, "请输入新的旧聊ID", 0).show();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("uid", upperCase);
            g0.d.S("/me/uid", jSONObject, this.f3691E, new h(upperCase));
        } catch (Exception unused) {
            Toast.makeText(this, "更新失败", 0).show();
        }
    }

    public final void E0(Uri uri) {
        try {
            g0.d.T("/me/avatar", AbstractC0452s.d(getContentResolver(), uri, 512, 409600), "avatar.jpg", "image/jpeg", this.f3691E, new i());
        } catch (Exception unused) {
            Toast.makeText(this, "头像上传失败", 0).show();
        }
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        Uri data;
        super.onActivityResult(i2, i3, intent);
        if (i2 != 1001 || i3 != -1 || intent == null || (data = intent.getData()) == null) {
            return;
        }
        E0(data);
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5123Q);
        this.f3696w = (ImageView) a0(f0.f.m5);
        this.f3697x = (TextView) a0(f0.f.tb);
        this.f3698y = (TextView) a0(f0.f.rb);
        this.f3699z = (EditText) a0(f0.f.u4);
        this.f3687A = (EditText) a0(f0.f.V4);
        this.f3699z.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
        this.f3699z.setSingleLine(true);
        this.f3688B = a0(f0.f.t3);
        this.f3689C = a0(f0.f.f5041B);
        this.f3690D = a0(f0.f.f5105y);
        View viewA0 = a0(f0.f.I7);
        View viewA02 = a0(f0.f.P2);
        if (viewA02 != null) {
            viewA02.setOnClickListener(new a());
        }
        if (viewA0 != null) {
            viewA0.setOnClickListener(new b());
        }
        this.f3691E = getSharedPreferences("auth", 0).getString("access_token", "");
        this.f3690D.setOnClickListener(new c());
        this.f3688B.setOnClickListener(new d());
        this.f3689C.setOnClickListener(new e());
        x0();
    }

    @Override // f0.a, c.AbstractActivityC0167u, android.app.Activity, c.AbstractC0121a.b
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        if (i2 != 1002) {
            super.onRequestPermissionsResult(i2, strArr, iArr);
        } else if (iArr.length <= 0 || iArr[0] != 0) {
            Toast.makeText(this, "未授权读取存储", 0).show();
        } else {
            z0();
        }
    }

    public final String y0(String str) {
        return j0.d.a(str, 15);
    }

    public final void z0() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "选择头像"), 1001);
    }
}

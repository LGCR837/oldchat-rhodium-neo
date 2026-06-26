package com.im.oldchat.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import g0.d;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ChangePasswordActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public String f2474A;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public EditText f2475w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public EditText f2476x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public EditText f2477y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public Button f2478z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ChangePasswordActivity.this.finish();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ChangePasswordActivity.this.h0();
        }
    }

    public class c implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2481a;

        public c(String str) {
            this.f2481a = str;
        }

        @Override // g0.d.i
        public void b(String str) {
            ChangePasswordActivity.this.getSharedPreferences("auth", 0).edit().putString("saved_password", this.f2481a).apply();
            Toast.makeText(ChangePasswordActivity.this, "密码修改成功", 0).show();
            ChangePasswordActivity.this.finish();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            ChangePasswordActivity.this.f2478z.setEnabled(true);
            if (g0.e.v(i2, str)) {
                return;
            }
            if (i2 == 400 || i2 == 401) {
                Toast.makeText(ChangePasswordActivity.this, "旧密码错误", 0).show();
                return;
            }
            Toast.makeText(ChangePasswordActivity.this, "修改失败: " + i2, 0).show();
        }
    }

    public final void h0() {
        String strTrim = this.f2475w.getText().toString().trim();
        String strTrim2 = this.f2476x.getText().toString().trim();
        String strTrim3 = this.f2477y.getText().toString().trim();
        if (strTrim.isEmpty()) {
            Toast.makeText(this, "请输入旧密码", 0).show();
            return;
        }
        if (strTrim2.isEmpty()) {
            Toast.makeText(this, "请输入新密码", 0).show();
            return;
        }
        if (strTrim2.length() < 6) {
            Toast.makeText(this, "新密码至少6位", 0).show();
            return;
        }
        if (!strTrim2.equals(strTrim3)) {
            Toast.makeText(this, "两次输入的密码不一致", 0).show();
            return;
        }
        this.f2478z.setEnabled(false);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("old_password", strTrim);
            jSONObject.put("new_password", strTrim2);
            g0.d.S("/me/password", jSONObject, this.f2474A, new c(strTrim2));
        } catch (Exception unused) {
            this.f2478z.setEnabled(true);
            Toast.makeText(this, "修改失败", 0).show();
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5139d);
        this.f2475w = (EditText) a0(f0.f.K4);
        this.f2476x = (EditText) a0(f0.f.J4);
        this.f2477y = (EditText) a0(f0.f.r4);
        this.f2478z = (Button) a0(f0.f.T3);
        View viewA0 = a0(f0.f.f5040A);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        this.f2474A = getSharedPreferences("auth", 0).getString("access_token", "");
        this.f2478z.setOnClickListener(new b());
    }
}

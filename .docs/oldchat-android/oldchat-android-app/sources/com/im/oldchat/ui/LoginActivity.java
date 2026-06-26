package com.im.oldchat.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.MainActivity;
import g0.d;
import o.c;
import o0.AbstractC0431a;
import o0.AbstractC0438d0;
import o0.AbstractC0445k;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class LoginActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public Button f3019A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public CheckBox f3020B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public TextView f3021C;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public EditText f3022w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public EditText f3023x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Button f3024y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public TextView f3025z;

    public class a implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f3026a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f3027b;

        public a(String str, String str2) {
            this.f3026a = str;
            this.f3027b = str2;
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString("access_token");
                String string2 = jSONObject.getString("refresh_token");
                JSONObject jSONObject2 = jSONObject.getJSONObject("user");
                String string3 = jSONObject2.getString("id");
                String string4 = jSONObject2.getString("uid");
                LoginActivity.this.p0(string4);
                LoginActivity.this.t0(string, string2, string3, string4);
                LoginActivity.this.s0(this.f3026a, this.f3027b);
                Toast.makeText(LoginActivity.this, "登录成功", 0).show();
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, (Class<?>) MainActivity.class));
                LoginActivity.this.finish();
            } catch (Exception e2) {
                c(-1, "解析失败: " + e2.getMessage());
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (i2 == 403 || (str != null && (str.contains("user_banned") || str.contains("device_banned")))) {
                Toast.makeText(LoginActivity.this, "已被封禁", 0).show();
                return;
            }
            Toast.makeText(LoginActivity.this, "登录失败 (" + i2 + ")", 0).show();
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (LoginActivity.this.f3020B != null) {
                LoginActivity.this.f3020B.setChecked(true);
            }
            o0.V.y(LoginActivity.this, true);
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (o0.V.l(LoginActivity.this)) {
                return;
            }
            LoginActivity.this.f3020B.setChecked(false);
            LoginActivity.this.v0();
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LoginActivity.this.v0();
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String string = LoginActivity.this.f3022w.getText().toString();
            String string2 = LoginActivity.this.f3023x.getText().toString();
            if (LoginActivity.this.q0()) {
                LoginActivity.this.r0(string, string2);
            }
        }
    }

    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LoginActivity.this.startActivity(new Intent(LoginActivity.this, (Class<?>) RegisterActivity.class));
        }
    }

    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LoginActivity.this.startActivity(new Intent(LoginActivity.this, (Class<?>) RecoverPasswordActivity.class));
        }
    }

    public class h implements View.OnClickListener {
        public h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Toast.makeText(LoginActivity.this, "长按图标可设置服务器地址", 0).show();
        }
    }

    public class i implements View.OnLongClickListener {
        public i() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            LoginActivity.this.w0();
            return true;
        }
    }

    public class j implements DialogInterface.OnClickListener {
        public j() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            o0.U.e(LoginActivity.this);
            Toast.makeText(LoginActivity.this, "已恢复默认服务器地址", 0).show();
        }
    }

    public class k implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f3038a;

        public k(EditText editText) {
            this.f3038a = editText;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            String strF = o0.U.f(LoginActivity.this, this.f3038a.getText() == null ? "" : this.f3038a.getText().toString());
            if (strF == null) {
                Toast.makeText(LoginActivity.this, "地址格式无效，请输入 http(s)://host[:port][/path]", 1).show();
                return;
            }
            Toast.makeText(LoginActivity.this, "服务器地址已保存: " + strF, 0).show();
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5171x);
        this.f3022w = (EditText) a0(f0.f.W4);
        this.f3023x = (EditText) a0(f0.f.M4);
        this.f3024y = (Button) a0(f0.f.p1);
        this.f3025z = (TextView) a0(f0.f.bd);
        this.f3019A = (Button) a0(f0.f.W2);
        this.f3020B = (CheckBox) a0(f0.f.e4);
        this.f3021C = (TextView) a0(f0.f.zc);
        u0();
        CheckBox checkBox = this.f3020B;
        if (checkBox != null) {
            checkBox.setChecked(o0.V.l(this));
            this.f3020B.setOnClickListener(new c());
        }
        TextView textView = this.f3021C;
        if (textView != null) {
            textView.setOnClickListener(new d());
        }
        AbstractC0438d0.g(this);
        this.f3024y.setOnClickListener(new e());
        this.f3025z.setOnClickListener(new f());
        Button button = this.f3019A;
        if (button != null) {
            button.setOnClickListener(new g());
        }
    }

    public final void p0(String str) {
        String string = getSharedPreferences("auth", 0).getString("my_uid", "");
        if (string.isEmpty() || str == null || str.isEmpty() || string.equals(str)) {
            return;
        }
        AbstractC0431a.a(this);
        o0.F.a(this);
    }

    public final boolean q0() {
        CheckBox checkBox = this.f3020B;
        if (checkBox != null && checkBox.isChecked()) {
            o0.V.y(this, true);
            return true;
        }
        Toast.makeText(this, f0.j.t2, 0).show();
        v0();
        return false;
    }

    public final void r0(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("identifier", str);
            jSONObject.put("password", str2);
            jSONObject.put("device_id", AbstractC0445k.b(this));
            jSONObject.put("imei", AbstractC0445k.d(this));
            jSONObject.put("device_name", AbstractC0445k.c());
            jSONObject.put("platform", "android");
            jSONObject.put("app_version", AbstractC0445k.a(this));
            g0.d.S("/auth/login", jSONObject, null, new a(str, str2));
        } catch (Exception unused) {
            Toast.makeText(this, "构造请求失败", 0).show();
        }
    }

    public final void s0(String str, String str2) {
        getSharedPreferences("auth", 0).edit().putString("saved_username", str).putString("saved_password", str2).apply();
    }

    public final void t0(String str, String str2, String str3, String str4) {
        getSharedPreferences("auth", 0).edit().putString("access_token", str).putString("refresh_token", str2).putString("user_id", str3).putString("my_uid", str4).apply();
    }

    public final void u0() {
        View viewA0 = a0(f0.f.C5);
        if (viewA0 == null) {
            return;
        }
        viewA0.setOnClickListener(new h());
        viewA0.setOnLongClickListener(new i());
    }

    public final void v0() {
        o0.M.b(this, new b());
    }

    public final void w0() {
        EditText editText = new EditText(this);
        editText.setSingleLine(true);
        editText.setHint("http://example.com:8080/v1");
        String strB = o0.U.b();
        if (strB != null) {
            editText.setText(strB);
            editText.setSelection(strB.length());
        }
        new c.a(this).t("设置服务器 Base URL").u(editText).q("保存", new k(editText)).m("恢复默认", new j()).k("取消", null).v();
    }
}

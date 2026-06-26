package com.im.oldchat.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.im.oldchat.service.MessageService;
import g0.d;
import o.c;
import o0.AbstractC0431a;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AccountManagementActivity extends f0.a {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public String f2364w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public View f2365x;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AccountManagementActivity.this.finish();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AccountManagementActivity.this.startActivity(new Intent(AccountManagementActivity.this, (Class<?>) ChangePasswordActivity.class));
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AccountManagementActivity.this.startActivity(new Intent(AccountManagementActivity.this, (Class<?>) DeviceManagementActivity.class));
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AccountManagementActivity.this.n0();
        }
    }

    public class e implements DialogInterface.OnClickListener {
        public e() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            AccountManagementActivity.this.o0();
        }
    }

    public class f implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f2371a;

        public f(EditText editText) {
            this.f2371a = editText;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            AccountManagementActivity.this.k0(this.f2371a.getText().toString().trim());
        }
    }

    public class g implements d.i {
        public g() {
        }

        @Override // g0.d.i
        public void b(String str) {
            Toast.makeText(AccountManagementActivity.this, "账号已注销", 0).show();
            AccountManagementActivity.this.m0();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (AccountManagementActivity.this.f2365x != null) {
                AccountManagementActivity.this.f2365x.setEnabled(true);
            }
            if (g0.e.v(i2, str)) {
                return;
            }
            if (i2 == 401) {
                Toast.makeText(AccountManagementActivity.this, "密码错误", 0).show();
                return;
            }
            Toast.makeText(AccountManagementActivity.this, "注销失败(" + i2 + ")", 0).show();
        }
    }

    public final void k0(String str) {
        if (str == null || str.length() == 0) {
            Toast.makeText(this, "请输入当前密码", 0).show();
            return;
        }
        String str2 = this.f2364w;
        if (str2 == null || str2.length() == 0) {
            Toast.makeText(this, "登录已失效，请重新登录", 0).show();
            return;
        }
        View view = this.f2365x;
        if (view != null) {
            view.setEnabled(false);
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("password", str);
            g0.d.S("/me/delete", jSONObject, this.f2364w, new g());
        } catch (Exception unused) {
            View view2 = this.f2365x;
            if (view2 != null) {
                view2.setEnabled(true);
            }
            Toast.makeText(this, "注销失败", 0).show();
        }
    }

    public final int l0(int i2) {
        return (int) ((i2 * getResources().getDisplayMetrics().density) + 0.5f);
    }

    public final void m0() {
        getSharedPreferences("auth", 0).edit().clear().apply();
        AbstractC0431a.a(this);
        g0.j.u().A();
        MessageService.h(this);
        Intent intent = new Intent(this, (Class<?>) LoginActivity.class);
        intent.addFlags(268468224);
        startActivity(intent);
        finish();
    }

    public final void n0() {
        new c.a(this, f0.k.f5244a).t("注销账号").i("注销后账号与聊天数据将被删除且不可恢复，是否继续？").q("继续", new e()).k("取消", null).v();
    }

    public final void o0() {
        EditText editText = new EditText(this);
        editText.setHint("请输入当前密码");
        editText.setInputType(129);
        int iL0 = l0(16);
        editText.setPadding(iL0, iL0, iL0, iL0);
        new c.a(this, f0.k.f5244a).t("确认密码").u(editText).q("确认注销", new f(editText)).k("取消", null).v();
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5133a);
        this.f2364w = getSharedPreferences("auth", 0).getString("access_token", "");
        View viewA0 = a0(f0.f.f5088l);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        View viewA02 = a0(f0.f.f5106z);
        if (viewA02 != null) {
            viewA02.setOnClickListener(new b());
        }
        View viewA03 = a0(f0.f.f5079g0);
        if (viewA03 != null) {
            viewA03.setOnClickListener(new c());
        }
        View viewA04 = a0(f0.f.f5069b0);
        this.f2365x = viewA04;
        if (viewA04 != null) {
            viewA04.setOnClickListener(new d());
        }
    }
}

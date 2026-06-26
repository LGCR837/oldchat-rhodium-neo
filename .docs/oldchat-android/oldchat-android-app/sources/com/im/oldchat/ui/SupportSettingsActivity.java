package com.im.oldchat.ui;

import android.R;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import o.c;
import o0.AbstractC0438d0;

/* JADX INFO: loaded from: classes.dex */
public class SupportSettingsActivity extends f0.a {

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SupportSettingsActivity.this.finish();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SupportSettingsActivity.this.startActivity(new Intent(SupportSettingsActivity.this, (Class<?>) FeedbackActivity.class));
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Toast.makeText(SupportSettingsActivity.this, "正在检查更新...", 0).show();
            AbstractC0438d0.h(SupportSettingsActivity.this, true);
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SupportSettingsActivity.this.startActivity(new Intent(SupportSettingsActivity.this, (Class<?>) PrivacyPolicyActivity.class));
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SupportSettingsActivity.this.g0();
        }
    }

    public final void g0() {
        String str = "未知";
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            if (packageInfo != null) {
                String str2 = packageInfo.versionName;
                if (str2 != null) {
                    str = str2;
                }
            }
        } catch (Exception unused) {
        }
        new c.a(this).t("关于旧聊").i("当前版本: " + str + "\n\n如果你遇到问题，可在本页进入问题反馈。").p(R.string.ok, null).v();
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5144f0);
        View viewA0 = a0(f0.f.W3);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        View viewA02 = a0(f0.f.M0);
        if (viewA02 != null) {
            viewA02.setOnClickListener(new b());
        }
        View viewA03 = a0(f0.f.f5046G);
        if (viewA03 != null) {
            viewA03.setOnClickListener(new c());
        }
        View viewA04 = a0(f0.f.O2);
        if (viewA04 != null) {
            viewA04.setOnClickListener(new d());
        }
        View viewA05 = a0(f0.f.f5084j);
        if (viewA05 != null) {
            viewA05.setOnClickListener(new e());
        }
    }
}

package com.im.oldchat.ui;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import g0.d;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class FeedbackActivity extends f0.a {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public EditText f2842w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public Button f2843x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f2844y;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FeedbackActivity.this.finish();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FeedbackActivity.this.i0();
        }
    }

    public class c implements d.i {

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(FeedbackActivity.this, "感谢您的反馈！", 0).show();
                FeedbackActivity.this.finish();
            }
        }

        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                FeedbackActivity.this.f2843x.setEnabled(true);
                FeedbackActivity.this.f2843x.setText("提交反馈");
                Toast.makeText(FeedbackActivity.this, "提交失败，请稍后重试", 0).show();
            }
        }

        public c() {
        }

        @Override // g0.d.i
        public void b(String str) {
            FeedbackActivity.this.runOnUiThread(new a());
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            FeedbackActivity.this.runOnUiThread(new b());
        }
    }

    public final String h0() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "unknown";
        }
    }

    public final void i0() {
        String strTrim = this.f2842w.getText().toString().trim();
        if (strTrim.isEmpty()) {
            Toast.makeText(this, "请输入反馈内容", 0).show();
            return;
        }
        this.f2843x.setEnabled(false);
        this.f2843x.setText("提交中...");
        String string = getSharedPreferences("auth", 0).getString("access_token", "");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("content", strTrim);
            jSONObject.put("device_model", Build.MANUFACTURER + " " + Build.MODEL);
            jSONObject.put("android_version", Build.VERSION.RELEASE);
            jSONObject.put("app_version", h0());
            g0.d.S("/feedback", jSONObject, string, new c());
        } catch (Exception unused) {
            this.f2843x.setEnabled(true);
            this.f2843x.setText("提交反馈");
            Toast.makeText(this, "提交失败", 0).show();
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5161o);
        this.f2842w = (EditText) a0(f0.f.s4);
        this.f2843x = (Button) a0(f0.f.T3);
        this.f2844y = (TextView) a0(f0.f.y9);
        View viewA0 = a0(f0.f.f5102v);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        this.f2844y.setText("设备: " + Build.MANUFACTURER + " " + Build.MODEL + " | 系统: Android " + Build.VERSION.RELEASE + " | 版本: " + h0());
        this.f2843x.setOnClickListener(new b());
    }
}

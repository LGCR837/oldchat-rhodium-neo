package com.im.oldchat.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import g0.d;
import java.io.BufferedReader;
import java.io.FileReader;
import o0.AbstractC0441g;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CrashActivity extends f0.a {

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2551a;

        public a(String str) {
            this.f2551a = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CrashActivity.this.i0(this.f2551a);
        }
    }

    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2553a;

        public b(String str) {
            this.f2553a = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CrashActivity.this.l0(this.f2553a);
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CrashActivity.this.finish();
        }
    }

    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2556a;

        public class a implements d.i {

            /* JADX INFO: renamed from: com.im.oldchat.ui.CrashActivity$d$a$a, reason: collision with other inner class name */
            public class RunnableC0025a implements Runnable {
                public RunnableC0025a() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    Toast.makeText(CrashActivity.this, "报告已提交", 0).show();
                    CrashActivity.this.finish();
                }
            }

            public class b implements Runnable {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public final /* synthetic */ int f2560a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                public final /* synthetic */ String f2561b;

                public b(int i2, String str) {
                    this.f2560a = i2;
                    this.f2561b = str;
                }

                @Override // java.lang.Runnable
                public void run() {
                    CrashActivity crashActivity = CrashActivity.this;
                    Toast.makeText(crashActivity, crashActivity.j0(this.f2560a, this.f2561b), 0).show();
                }
            }

            public a() {
            }

            @Override // g0.d.i
            public void b(String str) {
                CrashActivity.this.runOnUiThread(new RunnableC0025a());
            }

            @Override // g0.d.i
            public void c(int i2, String str) {
                CrashActivity.this.runOnUiThread(new b(i2, str));
            }
        }

        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(CrashActivity.this, "提交失败", 0).show();
            }
        }

        public d(String str) {
            this.f2556a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                String string = CrashActivity.this.getSharedPreferences("auth", 0).getString("access_token", "");
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("crash_log", this.f2556a);
                jSONObject.put("timestamp", System.currentTimeMillis());
                jSONObject.put("device_model", Build.MODEL);
                jSONObject.put("android_version", Build.VERSION.RELEASE);
                g0.d.S("/admins/crash-reports", jSONObject, string, new a());
            } catch (Exception unused) {
                CrashActivity.this.runOnUiThread(new b());
            }
        }
    }

    public final void i0(String str) {
        AbstractC0441g.a(this, str);
    }

    public final String j0(int i2, String str) {
        if (str == null || str.length() <= 0) {
            str = null;
        } else {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String strOptString = jSONObject.optString("message", "");
                str = (strOptString == null || strOptString.length() == 0) ? jSONObject.optString("error", "") : strOptString;
            } catch (Exception unused) {
            }
        }
        if (str == null || str.length() == 0) {
            str = String.valueOf(i2);
        } else if (str.length() > 80) {
            str = str.substring(0, 80);
        }
        return "提交失败: " + i2 + " " + str;
    }

    public final String k0(String str) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    bufferedReader.close();
                    return sb.toString();
                }
                sb.append(line);
                sb.append('\n');
            }
        } catch (Exception unused) {
            return "";
        }
    }

    public final void l0(String str) {
        new Thread(new d(str)).start();
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5147h);
        TextView textView = (TextView) a0(f0.f.t9);
        View viewA0 = a0(f0.f.f5062W);
        View viewA02 = a0(f0.f.U3);
        View viewA03 = a0(f0.f.f5059T);
        String stringExtra = getIntent().getStringExtra("log");
        String stringExtra2 = getIntent().getStringExtra("log_path");
        if ((stringExtra == null || stringExtra.isEmpty()) && stringExtra2 != null && !stringExtra2.isEmpty()) {
            stringExtra = k0(stringExtra2);
        }
        if (stringExtra == null) {
            stringExtra = "";
        }
        textView.setText(stringExtra);
        viewA0.setOnClickListener(new a(stringExtra));
        viewA02.setOnClickListener(new b(stringExtra));
        viewA03.setOnClickListener(new c());
    }
}

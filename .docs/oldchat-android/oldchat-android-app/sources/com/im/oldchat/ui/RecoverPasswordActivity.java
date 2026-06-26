package com.im.oldchat.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import g0.d;
import java.util.Locale;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class RecoverPasswordActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public EditText f3939A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public ImageView f3940B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public Button f3941C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public Button f3942D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public String f3943E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final Handler f3944F = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final Runnable f3945G = new a();

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public boolean f3946H = false;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public long f3947I = 0;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public long f3948J = 0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public EditText f3949w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public EditText f3950x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public EditText f3951y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public EditText f3952z;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RecoverPasswordActivity.this.v0();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RecoverPasswordActivity.this.finish();
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RecoverPasswordActivity.this.r0(true);
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RecoverPasswordActivity.this.t0();
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RecoverPasswordActivity.this.s0();
        }
    }

    public class f implements d.i {
        public f() {
        }

        @Override // g0.d.i
        public void b(String str) {
            String strOptString;
            try {
                JSONObject jSONObject = new JSONObject(str);
                RecoverPasswordActivity.this.f3943E = jSONObject.optString("captcha_id", "");
                strOptString = jSONObject.optString("image_base64", "");
            } catch (Exception unused) {
            }
            if (strOptString.isEmpty()) {
                RecoverPasswordActivity.this.f3946H = false;
                return;
            }
            byte[] bArrDecode = Base64.decode(strOptString, 2);
            Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
            if (RecoverPasswordActivity.this.f3940B != null && bitmapDecodeByteArray != null) {
                RecoverPasswordActivity.this.f3940B.setImageBitmap(bitmapDecodeByteArray);
            }
            if (RecoverPasswordActivity.this.f3951y != null) {
                RecoverPasswordActivity.this.f3951y.setText("");
            }
            RecoverPasswordActivity.this.f3946H = false;
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            RecoverPasswordActivity.this.f3946H = false;
        }
    }

    public class g implements d.i {
        public g() {
        }

        @Override // g0.d.i
        public void b(String str) {
            Toast.makeText(RecoverPasswordActivity.this, f0.j.A2, 0).show();
            RecoverPasswordActivity.this.u0(120);
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (str != null && str.contains("invalid_captcha")) {
                Toast.makeText(RecoverPasswordActivity.this, f0.j.f5185I, 0).show();
            } else if (str != null && str.contains("invalid_account")) {
                Toast.makeText(RecoverPasswordActivity.this, f0.j.z2, 0).show();
            } else if (str == null || !str.contains("email_cooldown")) {
                Toast.makeText(RecoverPasswordActivity.this, "发送失败 (" + i2 + ")", 0).show();
            } else {
                Toast.makeText(RecoverPasswordActivity.this, "发送太频繁，请稍后再试", 0).show();
                RecoverPasswordActivity.this.u0(120);
            }
            RecoverPasswordActivity.this.r0(false);
        }
    }

    public class h implements d.i {
        public h() {
        }

        @Override // g0.d.i
        public void b(String str) {
            Toast.makeText(RecoverPasswordActivity.this, f0.j.B2, 0).show();
            RecoverPasswordActivity.this.finish();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (str != null && str.contains("invalid_email_code")) {
                Toast.makeText(RecoverPasswordActivity.this, f0.j.f5187K, 0).show();
                return;
            }
            if (str != null && str.contains("invalid_account")) {
                Toast.makeText(RecoverPasswordActivity.this, f0.j.z2, 0).show();
                return;
            }
            if (str != null && str.contains("invalid_password")) {
                Toast.makeText(RecoverPasswordActivity.this, f0.j.f5191O, 0).show();
                return;
            }
            Toast.makeText(RecoverPasswordActivity.this, "重置失败 (" + i2 + ")", 0).show();
        }
    }

    public final boolean o0() {
        return this.f3947I > System.currentTimeMillis();
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5129W);
        this.f3949w = (EditText) a0(f0.f.W4);
        this.f3950x = (EditText) a0(f0.f.v4);
        this.f3951y = (EditText) a0(f0.f.p4);
        this.f3952z = (EditText) a0(f0.f.w4);
        this.f3939A = (EditText) a0(f0.f.J4);
        this.f3940B = (ImageView) a0(f0.f.o5);
        this.f3941C = (Button) a0(f0.f.H3);
        this.f3942D = (Button) a0(f0.f.k3);
        View viewA0 = a0(f0.f.V2);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new b());
        }
        ImageView imageView = this.f3940B;
        if (imageView != null) {
            imageView.setOnClickListener(new c());
        }
        Button button = this.f3941C;
        if (button != null) {
            button.setOnClickListener(new d());
        }
        Button button2 = this.f3942D;
        if (button2 != null) {
            button2.setOnClickListener(new e());
        }
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onPause() {
        super.onPause();
        this.f3944F.removeCallbacks(this.f3945G);
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onResume() {
        super.onResume();
        r0(false);
        v0();
    }

    public final boolean p0(String str) {
        return Patterns.EMAIL_ADDRESS.matcher(str).matches();
    }

    public final boolean q0(String str) {
        if (str.length() < 3 || str.length() > 24) {
            return false;
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            if ((cCharAt < 'a' || cCharAt > 'z') && ((cCharAt < '0' || cCharAt > '9') && cCharAt != '_')) {
                return false;
            }
        }
        return true;
    }

    public final void r0(boolean z2) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (z2) {
            long j2 = this.f3948J;
            if (j2 > 0 && jCurrentTimeMillis - j2 < 5000) {
                Toast.makeText(this, f0.j.f5209d, 0).show();
                return;
            }
        }
        if (this.f3946H) {
            return;
        }
        this.f3946H = true;
        this.f3948J = jCurrentTimeMillis;
        g0.d.R("/auth/captcha", null, new f());
    }

    public final void s0() {
        EditText editText = this.f3949w;
        String strTrim = editText != null ? editText.getText().toString().trim() : "";
        EditText editText2 = this.f3950x;
        String strTrim2 = editText2 != null ? editText2.getText().toString().trim() : "";
        EditText editText3 = this.f3952z;
        String strTrim3 = editText3 != null ? editText3.getText().toString().trim() : "";
        EditText editText4 = this.f3939A;
        String strTrim4 = editText4 != null ? editText4.getText().toString().trim() : "";
        if (strTrim.isEmpty() || strTrim2.isEmpty() || strTrim3.isEmpty() || strTrim4.isEmpty()) {
            Toast.makeText(this, f0.j.f5194R, 0).show();
            return;
        }
        Locale locale = Locale.US;
        String lowerCase = strTrim.toLowerCase(locale);
        String lowerCase2 = strTrim2.toLowerCase(locale);
        if (!q0(lowerCase)) {
            Toast.makeText(this, f0.j.f5188L, 0).show();
            return;
        }
        if (!p0(lowerCase2)) {
            Toast.makeText(this, f0.j.f5186J, 0).show();
            return;
        }
        if (strTrim4.length() < 8) {
            Toast.makeText(this, f0.j.f5191O, 0).show();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("username", lowerCase);
            jSONObject.put("email", lowerCase2);
            jSONObject.put("email_code", strTrim3);
            jSONObject.put("new_password", strTrim4);
            g0.d.S("/auth/password/reset", jSONObject, null, new h());
        } catch (Exception unused) {
            Toast.makeText(this, "构造请求失败", 0).show();
        }
    }

    public final void t0() {
        String str;
        if (o0()) {
            Toast.makeText(this, "请稍后再试", 0).show();
            return;
        }
        EditText editText = this.f3949w;
        String strTrim = editText != null ? editText.getText().toString().trim() : "";
        EditText editText2 = this.f3950x;
        String strTrim2 = editText2 != null ? editText2.getText().toString().trim() : "";
        EditText editText3 = this.f3951y;
        String strTrim3 = editText3 != null ? editText3.getText().toString().trim() : "";
        if (strTrim.isEmpty() || strTrim2.isEmpty()) {
            Toast.makeText(this, f0.j.f5194R, 0).show();
            return;
        }
        Locale locale = Locale.US;
        String lowerCase = strTrim.toLowerCase(locale);
        String lowerCase2 = strTrim2.toLowerCase(locale);
        if (!q0(lowerCase)) {
            Toast.makeText(this, f0.j.f5188L, 0).show();
            return;
        }
        if (!p0(lowerCase2)) {
            Toast.makeText(this, f0.j.f5186J, 0).show();
            return;
        }
        if (strTrim3.isEmpty() || (str = this.f3943E) == null || str.isEmpty()) {
            Toast.makeText(this, f0.j.f5185I, 0).show();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("email", lowerCase2);
            jSONObject.put("captcha_id", this.f3943E);
            jSONObject.put("captcha_code", strTrim3);
            jSONObject.put("username", lowerCase);
            g0.d.S("/auth/email/send", jSONObject, null, new g());
        } catch (Exception unused) {
            Toast.makeText(this, "构造请求失败", 0).show();
        }
    }

    public final void u0(int i2) {
        this.f3947I = System.currentTimeMillis() + (((long) i2) * 1000);
        v0();
    }

    public final void v0() {
        if (this.f3941C == null) {
            return;
        }
        long jCurrentTimeMillis = this.f3947I - System.currentTimeMillis();
        if (jCurrentTimeMillis <= 0) {
            this.f3947I = 0L;
            this.f3941C.setEnabled(true);
            this.f3941C.setText(getString(f0.j.C2));
            this.f3944F.removeCallbacks(this.f3945G);
            return;
        }
        int i2 = (int) (jCurrentTimeMillis / 1000);
        this.f3941C.setEnabled(false);
        this.f3941C.setText("重新发送(" + i2 + "s)");
        this.f3944F.removeCallbacks(this.f3945G);
        this.f3944F.postDelayed(this.f3945G, 1000L);
    }
}

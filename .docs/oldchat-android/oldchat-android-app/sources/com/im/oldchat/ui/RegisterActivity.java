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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import g0.d;
import java.util.Locale;
import o0.AbstractC0445k;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class RegisterActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public EditText f4018A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public Button f4019B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public Button f4020C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public ImageView f4021D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public CheckBox f4022E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public TextView f4023F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public String f4024G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final Handler f4025H = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final Runnable f4026I = new c();

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f4027J = false;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public long f4028K = 0;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public long f4029L = 0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public EditText f4030w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public EditText f4031x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public EditText f4032y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public EditText f4033z;

    public class a implements d.i {
        public a() {
        }

        @Override // g0.d.i
        public void b(String str) {
            Toast.makeText(RegisterActivity.this, "验证码已发送", 0).show();
            RegisterActivity.this.I0(120);
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (str != null && str.contains("invalid_captcha")) {
                Toast.makeText(RegisterActivity.this, f0.j.f5185I, 0).show();
            } else if (str != null && str.contains("email_cooldown")) {
                Toast.makeText(RegisterActivity.this, "发送太频繁，请稍后再试", 0).show();
                RegisterActivity.this.I0(120);
            } else if (str == null || !str.contains("invalid_email_domain")) {
                Toast.makeText(RegisterActivity.this, "发送失败 (" + i2 + ")", 0).show();
            } else {
                Toast.makeText(RegisterActivity.this, f0.j.f5193Q, 0).show();
            }
            RegisterActivity.this.E0(false);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (RegisterActivity.this.f4022E != null) {
                RegisterActivity.this.f4022E.setChecked(true);
            }
            o0.V.y(RegisterActivity.this, true);
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RegisterActivity.this.J0();
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (o0.V.l(RegisterActivity.this)) {
                return;
            }
            RegisterActivity.this.f4022E.setChecked(false);
            RegisterActivity.this.H0();
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RegisterActivity.this.H0();
        }
    }

    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RegisterActivity.this.finish();
        }
    }

    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RegisterActivity.this.E0(true);
        }
    }

    public class h implements View.OnClickListener {
        public h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RegisterActivity.this.G0();
        }
    }

    public class i implements View.OnClickListener {
        public i() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String strTrim = RegisterActivity.this.f4030w.getText().toString().trim();
            String strTrim2 = RegisterActivity.this.f4031x.getText().toString().trim();
            String strTrim3 = RegisterActivity.this.f4032y.getText().toString().trim();
            String strTrim4 = RegisterActivity.this.f4033z != null ? RegisterActivity.this.f4033z.getText().toString().trim() : "";
            String strTrim5 = RegisterActivity.this.f4018A != null ? RegisterActivity.this.f4018A.getText().toString().trim() : "";
            if (strTrim.isEmpty() || strTrim3.isEmpty() || strTrim2.isEmpty()) {
                Toast.makeText(RegisterActivity.this, f0.j.f5194R, 0).show();
                return;
            }
            Locale locale = Locale.US;
            String lowerCase = strTrim.toLowerCase(locale);
            String lowerCase2 = strTrim2.toLowerCase(locale);
            if (!RegisterActivity.this.C0(lowerCase2)) {
                Toast.makeText(RegisterActivity.this, f0.j.f5186J, 0).show();
                return;
            }
            if (!RegisterActivity.this.B0(lowerCase2)) {
                Toast.makeText(RegisterActivity.this, f0.j.f5193Q, 0).show();
                return;
            }
            if (!RegisterActivity.this.D0(lowerCase)) {
                Toast.makeText(RegisterActivity.this, f0.j.f5188L, 0).show();
                return;
            }
            if (strTrim3.length() < 8) {
                Toast.makeText(RegisterActivity.this, f0.j.f5191O, 0).show();
                return;
            }
            if (strTrim4.isEmpty() || RegisterActivity.this.f4024G == null || RegisterActivity.this.f4024G.isEmpty()) {
                Toast.makeText(RegisterActivity.this, f0.j.f5185I, 0).show();
            } else if (strTrim5.isEmpty()) {
                Toast.makeText(RegisterActivity.this, f0.j.f5187K, 0).show();
            } else if (RegisterActivity.this.z0()) {
                RegisterActivity.this.F0(lowerCase, strTrim3, lowerCase2, strTrim5);
            }
        }
    }

    public class j implements d.i {
        public j() {
        }

        @Override // g0.d.i
        public void b(String str) {
            Toast.makeText(RegisterActivity.this, "注册成功", 0).show();
            RegisterActivity.this.finish();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (i2 == 409) {
                Toast.makeText(RegisterActivity.this, "用户名或邮箱已占用", 0).show();
                return;
            }
            if (i2 == 403 || (str != null && str.contains("device_banned"))) {
                Toast.makeText(RegisterActivity.this, "设备已被封禁", 0).show();
                return;
            }
            if (str != null && str.contains("invalid_email_code")) {
                Toast.makeText(RegisterActivity.this, f0.j.f5187K, 0).show();
                return;
            }
            if (str != null && str.contains("invalid_email_domain")) {
                Toast.makeText(RegisterActivity.this, f0.j.f5193Q, 0).show();
                return;
            }
            Toast.makeText(RegisterActivity.this, "注册失败 (" + i2 + ")", 0).show();
        }
    }

    public class k implements d.i {
        public k() {
        }

        @Override // g0.d.i
        public void b(String str) {
            String strOptString;
            try {
                JSONObject jSONObject = new JSONObject(str);
                RegisterActivity.this.f4024G = jSONObject.optString("captcha_id", "");
                strOptString = jSONObject.optString("image_base64", "");
            } catch (Exception unused) {
            }
            if (strOptString.isEmpty()) {
                RegisterActivity.this.f4027J = false;
                return;
            }
            byte[] bArrDecode = Base64.decode(strOptString, 2);
            Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
            if (RegisterActivity.this.f4021D != null && bitmapDecodeByteArray != null) {
                RegisterActivity.this.f4021D.setImageBitmap(bitmapDecodeByteArray);
            }
            if (RegisterActivity.this.f4033z != null) {
                RegisterActivity.this.f4033z.setText("");
            }
            RegisterActivity.this.f4027J = false;
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            RegisterActivity.this.f4027J = false;
        }
    }

    private boolean A0() {
        return this.f4028K > System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean C0(String str) {
        return Patterns.EMAIL_ADDRESS.matcher(str).matches();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean D0(String str) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public void E0(boolean z2) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (z2) {
            long j2 = this.f4029L;
            if (j2 > 0 && jCurrentTimeMillis - j2 < 5000) {
                Toast.makeText(this, "请稍后再刷新", 0).show();
                return;
            }
        }
        if (this.f4027J) {
            return;
        }
        this.f4027J = true;
        this.f4029L = jCurrentTimeMillis;
        g0.d.R("/auth/captcha", null, new k());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H0() {
        o0.M.b(this, new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I0(int i2) {
        this.f4028K = System.currentTimeMillis() + (((long) i2) * 1000);
        J0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J0() {
        if (this.f4020C == null) {
            return;
        }
        long jCurrentTimeMillis = this.f4028K - System.currentTimeMillis();
        if (jCurrentTimeMillis <= 0) {
            this.f4028K = 0L;
            this.f4020C.setEnabled(true);
            this.f4020C.setText(getString(f0.j.d3));
            this.f4025H.removeCallbacks(this.f4026I);
            return;
        }
        int i2 = (int) (jCurrentTimeMillis / 1000);
        this.f4020C.setEnabled(false);
        this.f4020C.setText("重新发送(" + i2 + "s)");
        this.f4025H.removeCallbacks(this.f4026I);
        this.f4025H.postDelayed(this.f4026I, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean z0() {
        CheckBox checkBox = this.f4022E;
        if (checkBox != null && checkBox.isChecked()) {
            o0.V.y(this, true);
            return true;
        }
        Toast.makeText(this, f0.j.t2, 0).show();
        H0();
        return false;
    }

    public final boolean B0(String str) {
        if (str == null) {
            return false;
        }
        String strTrim = str.toLowerCase(Locale.US).trim();
        return strTrim.endsWith("@qq.com") || strTrim.endsWith("@vip.qq.com");
    }

    public final void F0(String str, String str2, String str3, String str4) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("username", str);
            jSONObject.put("password", str2);
            jSONObject.put("email", str3);
            jSONObject.put("email_code", str4);
            jSONObject.put("device_id", AbstractC0445k.b(this));
            jSONObject.put("imei", AbstractC0445k.d(this));
            jSONObject.put("device_name", AbstractC0445k.c());
            jSONObject.put("platform", "android");
            jSONObject.put("app_version", AbstractC0445k.a(this));
            g0.d.S("/auth/register", jSONObject, null, new j());
        } catch (Exception unused) {
            Toast.makeText(this, "构造请求失败", 0).show();
        }
    }

    public final void G0() {
        String str;
        if (A0()) {
            Toast.makeText(this, "请稍后再试", 0).show();
            return;
        }
        if (z0()) {
            EditText editText = this.f4031x;
            String strTrim = editText != null ? editText.getText().toString().trim() : "";
            EditText editText2 = this.f4033z;
            String strTrim2 = editText2 != null ? editText2.getText().toString().trim() : "";
            if (strTrim.isEmpty() || !C0(strTrim)) {
                Toast.makeText(this, f0.j.f5186J, 0).show();
                return;
            }
            if (!B0(strTrim)) {
                Toast.makeText(this, f0.j.f5193Q, 0).show();
                return;
            }
            if (strTrim2.isEmpty() || (str = this.f4024G) == null || str.isEmpty()) {
                Toast.makeText(this, f0.j.f5185I, 0).show();
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("email", strTrim);
                jSONObject.put("captcha_id", this.f4024G);
                jSONObject.put("captcha_code", strTrim2);
                g0.d.S("/auth/email/send", jSONObject, null, new a());
            } catch (Exception unused) {
                Toast.makeText(this, "构造请求失败", 0).show();
            }
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5134a0);
        this.f4030w = (EditText) a0(f0.f.W4);
        this.f4031x = (EditText) a0(f0.f.v4);
        this.f4032y = (EditText) a0(f0.f.M4);
        this.f4033z = (EditText) a0(f0.f.p4);
        this.f4018A = (EditText) a0(f0.f.w4);
        this.f4021D = (ImageView) a0(f0.f.o5);
        this.f4020C = (Button) a0(f0.f.G3);
        this.f4019B = (Button) a0(f0.f.e3);
        this.f4022E = (CheckBox) a0(f0.f.e4);
        this.f4023F = (TextView) a0(f0.f.zc);
        CheckBox checkBox = this.f4022E;
        if (checkBox != null) {
            checkBox.setChecked(o0.V.l(this));
            this.f4022E.setOnClickListener(new d());
        }
        TextView textView = this.f4023F;
        if (textView != null) {
            textView.setOnClickListener(new e());
        }
        View viewA0 = a0(f0.f.f3);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new f());
        }
        ImageView imageView = this.f4021D;
        if (imageView != null) {
            imageView.setOnClickListener(new g());
        }
        Button button = this.f4020C;
        if (button != null) {
            button.setOnClickListener(new h());
        }
        this.f4019B.setOnClickListener(new i());
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onPause() {
        super.onPause();
        this.f4025H.removeCallbacks(this.f4026I);
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onResume() {
        super.onResume();
        E0(false);
        J0();
    }
}

package com.im.oldchat.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import d.AbstractC0235a;
import g0.d;
import o0.AbstractC0452s;
import o0.AbstractC0455v;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ProfileSpaceEditActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public String f3711A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public String f3712B;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ImageView f3713w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public EditText f3714x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public View f3715y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public View f3716z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ProfileSpaceEditActivity.this.finish();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ProfileSpaceEditActivity.this.m0();
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ProfileSpaceEditActivity.this.n0();
        }
    }

    public class d implements d.i {
        public d() {
        }

        @Override // g0.d.i
        public void b(String str) {
            String str2 = "";
            try {
                JSONObject jSONObject = new JSONObject(str);
                String strOptString = jSONObject.optString("signature", "");
                ProfileSpaceEditActivity.this.f3712B = jSONObject.optString("cover_url", "");
                EditText editText = ProfileSpaceEditActivity.this.f3714x;
                if (strOptString != null) {
                    str2 = strOptString;
                }
                editText.setText(str2);
                if (ProfileSpaceEditActivity.this.f3712B == null || ProfileSpaceEditActivity.this.f3712B.isEmpty()) {
                    ProfileSpaceEditActivity.this.f3713w.setImageDrawable(null);
                    ProfileSpaceEditActivity.this.f3713w.setBackgroundColor(AbstractC0235a.b(ProfileSpaceEditActivity.this, f0.d.f4971h));
                } else {
                    ProfileSpaceEditActivity.this.f3713w.setBackgroundColor(0);
                    AbstractC0455v.e(ProfileSpaceEditActivity.this.f3713w, ProfileSpaceEditActivity.this.f3712B);
                }
            } catch (Exception unused) {
                Toast.makeText(ProfileSpaceEditActivity.this, "加载失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(ProfileSpaceEditActivity.this, "加载失败: " + i2, 0).show();
        }
    }

    public class e implements d.i {
        public e() {
        }

        @Override // g0.d.i
        public void b(String str) {
            Toast.makeText(ProfileSpaceEditActivity.this, "保存成功", 0).show();
            ProfileSpaceEditActivity.this.finish();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(ProfileSpaceEditActivity.this, "保存失败: " + i2, 0).show();
        }
    }

    public class f implements d.i {
        public f() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                ProfileSpaceEditActivity.this.f3712B = jSONObject.optString("cover_url", "");
                if (ProfileSpaceEditActivity.this.f3712B == null || ProfileSpaceEditActivity.this.f3712B.isEmpty()) {
                    ProfileSpaceEditActivity.this.f3713w.setImageDrawable(null);
                    ProfileSpaceEditActivity.this.f3713w.setBackgroundColor(AbstractC0235a.b(ProfileSpaceEditActivity.this, f0.d.f4971h));
                } else {
                    ProfileSpaceEditActivity.this.f3713w.setBackgroundColor(0);
                    AbstractC0455v.e(ProfileSpaceEditActivity.this.f3713w, ProfileSpaceEditActivity.this.f3712B);
                }
                Toast.makeText(ProfileSpaceEditActivity.this, "背景已更新", 0).show();
            } catch (Exception unused) {
                Toast.makeText(ProfileSpaceEditActivity.this, "背景更新失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(ProfileSpaceEditActivity.this, "背景上传失败: " + i2, 0).show();
        }
    }

    private void l0() {
        g0.d.R("/me", this.f3711A, new d());
    }

    public final void m0() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "选择背景"), 1401);
    }

    public final void n0() {
        String strTrim = this.f3714x.getText().toString().trim();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("signature", strTrim);
            String str = this.f3712B;
            if (str == null) {
                str = "";
            }
            jSONObject.put("cover_url", str);
            g0.d.S("/me/profile", jSONObject, this.f3711A, new e());
        } catch (Exception unused) {
            Toast.makeText(this, "保存失败", 0).show();
        }
    }

    public final void o0(Uri uri) {
        try {
            g0.d.T("/me/cover", AbstractC0452s.d(getContentResolver(), uri, 1280, 409600), "cover.jpg", "image/jpeg", this.f3711A, new f());
        } catch (Exception unused) {
            Toast.makeText(this, "背景上传失败", 0).show();
        }
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        Uri data;
        super.onActivityResult(i2, i3, intent);
        if (i2 != 1401 || i3 != -1 || intent == null || (data = intent.getData()) == null) {
            return;
        }
        o0(data);
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5124R);
        this.f3713w = (ImageView) a0(f0.f.u5);
        this.f3714x = (EditText) a0(f0.f.S4);
        this.f3715y = a0(f0.f.I2);
        this.f3716z = a0(f0.f.v3);
        View viewA0 = a0(f0.f.O3);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        this.f3711A = getSharedPreferences("auth", 0).getString("access_token", "");
        this.f3715y.setOnClickListener(new b());
        this.f3716z.setOnClickListener(new c());
        l0();
    }

    @Override // f0.a, c.AbstractActivityC0167u, android.app.Activity, c.AbstractC0121a.b
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        if (i2 != 1402) {
            super.onRequestPermissionsResult(i2, strArr, iArr);
        } else if (iArr.length <= 0 || iArr[0] != 0) {
            Toast.makeText(this, "未授权读取存储", 0).show();
        } else {
            m0();
        }
    }
}

package com.im.oldchat.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import c.AbstractC0121a;
import d.AbstractC0235a;

/* JADX INFO: loaded from: classes.dex */
public class QrCardActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public String f3927A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public String f3928B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public String f3929C;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ImageView f3930w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public TextView f3931x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f3932y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public Bitmap f3933z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            QrCardActivity.this.finish();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            QrCardActivity.this.l0();
        }
    }

    private int i0(int i2) {
        return (int) ((i2 * getResources().getDisplayMetrics().density) + 0.5f);
    }

    private void j0() {
        Intent intent = getIntent();
        this.f3927A = intent == null ? "" : k0(intent.getStringExtra("qr_mode"));
        this.f3928B = intent == null ? "" : k0(intent.getStringExtra("qr_id"));
        this.f3929C = intent == null ? "" : k0(intent.getStringExtra("qr_name"));
        String str = this.f3928B;
        if ((str == null || str.length() == 0) && "user".equals(this.f3927A)) {
            this.f3928B = k0(getSharedPreferences("auth", 0).getString("my_uid", ""));
        }
    }

    private String k0(String str) {
        return str == null ? "" : str.trim();
    }

    public final boolean g0() {
        String strB;
        String str;
        String str2;
        if (!"group".equals(this.f3927A)) {
            if ("user".equals(this.f3927A)) {
                strB = o0.Q.b(this.f3928B);
                str = (this.f3929C.length() == 0 ? this.f3928B : this.f3929C) + " (UID: " + this.f3928B + ")";
                str2 = "我的名片二维码";
            }
            return false;
        }
        strB = o0.Q.a(this.f3928B);
        str = (this.f3929C.length() == 0 ? "群聊" : this.f3929C) + " (" + this.f3928B + ")";
        str2 = "群二维码";
        if (strB.length() == 0) {
            return false;
        }
        TextView textView = this.f3931x;
        if (textView != null) {
            textView.setText(str2);
        }
        TextView textView2 = this.f3932y;
        if (textView2 != null) {
            textView2.setText(str);
        }
        try {
            Bitmap bitmapA = h0.b.a(strB, Math.max(480, getResources().getDisplayMetrics().widthPixels - i0(88)));
            this.f3933z = bitmapA;
            ImageView imageView = this.f3930w;
            if (imageView == null || bitmapA == null) {
                return true;
            }
            imageView.setImageBitmap(bitmapA);
            return true;
        } catch (Exception unused) {
        }
    }

    public final void h0() {
        try {
            Toast.makeText(this, "已保存到: " + o0.P.d(this, this.f3933z, "group".equals(this.f3927A) ? "group_qr" : "user_qr"), 1).show();
        } catch (Exception unused) {
            Toast.makeText(this, "保存二维码失败", 0).show();
        }
    }

    public final void l0() {
        if (this.f3933z == null) {
            Toast.makeText(this, "二维码尚未生成", 0).show();
        } else if (!o0.P.b() || Build.VERSION.SDK_INT < 23 || AbstractC0235a.a(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            h0();
        } else {
            AbstractC0121a.i(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 7301);
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5127U);
        this.f3930w = (ImageView) a0(f0.f.X5);
        this.f3931x = (TextView) a0(f0.f.Cc);
        this.f3932y = (TextView) a0(f0.f.Bc);
        View viewA0 = a0(f0.f.S2);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        View viewA02 = a0(f0.f.u3);
        if (viewA02 != null) {
            viewA02.setOnClickListener(new b());
        }
        j0();
        if (g0()) {
            return;
        }
        Toast.makeText(this, "无法生成二维码", 0).show();
        finish();
    }

    @Override // f0.a, c.AbstractActivityC0167u, android.app.Activity, c.AbstractC0121a.b
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        if (i2 != 7301) {
            super.onRequestPermissionsResult(i2, strArr, iArr);
        } else if (iArr == null || iArr.length <= 0 || iArr[0] != 0) {
            Toast.makeText(this, "未授权存储权限", 0).show();
        } else {
            h0();
        }
    }
}

package com.im.oldchat.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

/* JADX INFO: loaded from: classes.dex */
public class QrScanActivity extends f0.a {

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            QrScanActivity.this.finish();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            QrScanActivity.this.j0();
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            QrScanActivity.this.k0();
        }
    }

    public final Bitmap h0(Intent intent) {
        if (intent == null) {
            return null;
        }
        Parcelable parcelableExtra = intent.getParcelableExtra("data");
        if (parcelableExtra instanceof Bitmap) {
            return (Bitmap) parcelableExtra;
        }
        Uri data = intent.getData();
        if (data != null) {
            return o0.O.d(getContentResolver(), data, 1280);
        }
        return null;
    }

    public final Bitmap i0(Intent intent) {
        Uri data;
        if (intent == null || (data = intent.getData()) == null) {
            return null;
        }
        return o0.O.d(getContentResolver(), data, 1280);
    }

    public final void j0() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(this, "无可用相机", 0).show();
            return;
        }
        try {
            startActivityForResult(intent, 7402);
        } catch (Exception unused) {
            Toast.makeText(this, "无法打开相机", 0).show();
        }
    }

    public final void k0() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        intent.addCategory("android.intent.category.OPENABLE");
        try {
            startActivityForResult(Intent.createChooser(intent, "选择二维码图片"), 7401);
        } catch (Exception unused) {
            Toast.makeText(this, "无法打开相册", 0).show();
        }
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i3 != -1) {
            return;
        }
        Bitmap bitmapH0 = i2 == 7402 ? h0(intent) : i2 == 7401 ? i0(intent) : null;
        if (bitmapH0 == null) {
            Toast.makeText(this, "无法读取图片", 0).show();
            return;
        }
        String strC = o0.O.c(bitmapH0);
        if (strC.length() == 0) {
            Toast.makeText(this, "未识别到二维码", 0).show();
            return;
        }
        Intent intent2 = new Intent();
        intent2.putExtra("qr_result", strC);
        setResult(-1, intent2);
        finish();
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5128V);
        View viewA0 = a0(f0.f.T2);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        View viewA02 = a0(f0.f.w3);
        if (viewA02 != null) {
            viewA02.setOnClickListener(new b());
        }
        View viewA03 = a0(f0.f.x3);
        if (viewA03 != null) {
            viewA03.setOnClickListener(new c());
        }
    }
}

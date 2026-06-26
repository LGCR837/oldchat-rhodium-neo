package com.im.oldchat.ui;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import g0.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import o0.AbstractC0452s;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class MomentComposeActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public View f3075A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public View f3076B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public View f3077C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public String f3078D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final List f3079E = new ArrayList();

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final List f3080F = new ArrayList();

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final List f3081G = new ArrayList();

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public Uri f3082H;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public EditText f3083w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public ImageView f3084x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public View f3085y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public TextView f3086z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MomentComposeActivity.this.finish();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MomentComposeActivity.this.m0();
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MomentComposeActivity.this.n0();
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MomentComposeActivity.this.j0();
        }
    }

    public class e implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ArrayList f3091a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f3092b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f3093c;

        public e(ArrayList arrayList, int i2, String str) {
            this.f3091a = arrayList;
            this.f3092b = i2;
            this.f3093c = str;
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                String strOptString = new JSONObject(str).optString("url", "");
                if (strOptString != null && strOptString.length() != 0) {
                    this.f3091a.add(strOptString);
                    MomentComposeActivity.this.p0(this.f3092b + 1, this.f3093c, this.f3091a);
                    return;
                }
                Toast.makeText(MomentComposeActivity.this, "上传失败", 0).show();
            } catch (Exception unused) {
                Toast.makeText(MomentComposeActivity.this, "上传失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(MomentComposeActivity.this, "上传失败: " + i2, 0).show();
        }
    }

    public class f implements d.i {
        public f() {
        }

        @Override // g0.d.i
        public void b(String str) {
            Toast.makeText(MomentComposeActivity.this, "发布成功", 0).show();
            MomentComposeActivity.this.finish();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(MomentComposeActivity.this, "发布失败: " + i2, 0).show();
        }
    }

    public final void j0() {
        this.f3079E.clear();
        this.f3080F.clear();
        this.f3081G.clear();
        this.f3082H = null;
        View view = this.f3085y;
        if (view != null) {
            view.setVisibility(8);
        }
        ImageView imageView = this.f3084x;
        if (imageView != null) {
            imageView.setImageBitmap(null);
        }
        TextView textView = this.f3086z;
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    public final void k0(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (str == null) {
                str = "";
            }
            jSONObject.put("body", str);
            if (str2 == null) {
                str2 = "";
            }
            jSONObject.put("image_url", str2);
            g0.d.S("/moments", jSONObject, this.f3078D, new f());
        } catch (Exception unused) {
            Toast.makeText(this, "发布失败", 0).show();
        }
    }

    public final void l0(List list) {
        byte[] bArrD;
        j0();
        try {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Uri uri = (Uri) it.next();
                if (uri != null && (bArrD = AbstractC0452s.d(getContentResolver(), uri, 1280, 409600)) != null && bArrD.length != 0) {
                    this.f3079E.add(bArrD);
                    this.f3080F.add(o0(uri));
                    this.f3081G.add("image/jpeg");
                    if (this.f3082H == null) {
                        this.f3082H = uri;
                    }
                }
            }
            if (this.f3079E.isEmpty()) {
                return;
            }
            View view = this.f3085y;
            if (view != null) {
                view.setVisibility(0);
            }
            if (this.f3084x != null) {
                byte[] bArr = (byte[]) this.f3079E.get(0);
                Bitmap bitmapDecodeByteArray = (bArr == null || bArr.length <= 0) ? null : BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
                if (bitmapDecodeByteArray != null) {
                    this.f3084x.setImageBitmap(bitmapDecodeByteArray);
                } else {
                    Uri uri2 = this.f3082H;
                    if (uri2 != null) {
                        this.f3084x.setImageURI(uri2);
                    }
                }
            }
            TextView textView = this.f3086z;
            if (textView != null) {
                textView.setVisibility(this.f3079E.size() > 1 ? 0 : 8);
                this.f3086z.setText(this.f3079E.size() + "张");
            }
        } catch (Exception unused) {
            Toast.makeText(this, "读取图片失败", 0).show();
        } catch (OutOfMemoryError unused2) {
            j0();
            Toast.makeText(this, "图片太大，处理失败", 0).show();
        }
    }

    public final void m0() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        startActivityForResult(Intent.createChooser(intent, "选择图片"), 1201);
    }

    public final void n0() {
        String strTrim = this.f3083w.getText().toString().trim();
        if ((strTrim == null || strTrim.isEmpty()) && this.f3079E.isEmpty()) {
            Toast.makeText(this, "内容不能为空", 0).show();
        } else if (this.f3079E.isEmpty()) {
            k0(strTrim, "");
        } else {
            q0(strTrim);
        }
    }

    public final String o0(Uri uri) {
        int columnIndex;
        Cursor cursorQuery = getContentResolver().query(uri, null, null, null, null);
        if (cursorQuery == null) {
            return "moment.jpg";
        }
        try {
            return (!cursorQuery.moveToFirst() || (columnIndex = cursorQuery.getColumnIndex("_display_name")) < 0) ? "moment.jpg" : cursorQuery.getString(columnIndex);
        } finally {
            cursorQuery.close();
        }
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        Uri data;
        ClipData clipData;
        super.onActivityResult(i2, i3, intent);
        if (i2 == 1201 && i3 == -1 && intent != null) {
            List arrayList = new ArrayList();
            if (Build.VERSION.SDK_INT >= 16 && (clipData = intent.getClipData()) != null) {
                for (int i4 = 0; i4 < clipData.getItemCount(); i4++) {
                    Uri uri = clipData.getItemAt(i4).getUri();
                    if (uri != null) {
                        arrayList.add(uri);
                    }
                }
            }
            if (arrayList.isEmpty() && (data = intent.getData()) != null) {
                arrayList.add(data);
            }
            if (arrayList.isEmpty()) {
                return;
            }
            if (arrayList.size() > 9) {
                Toast.makeText(this, "最多选择9张图片", 0).show();
                arrayList = arrayList.subList(0, 9);
            }
            l0(arrayList);
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5107A);
        this.f3083w = (EditText) a0(f0.f.G4);
        this.f3084x = (ImageView) a0(f0.f.I5);
        this.f3085y = a0(f0.f.S7);
        this.f3086z = (TextView) a0(f0.f.Ga);
        this.f3075A = a0(f0.f.K2);
        this.f3076B = a0(f0.f.R2);
        this.f3077C = a0(f0.f.h3);
        View viewA0 = a0(f0.f.t1);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        this.f3078D = getSharedPreferences("auth", 0).getString("access_token", "");
        this.f3075A.setOnClickListener(new b());
        this.f3076B.setOnClickListener(new c());
        View view = this.f3077C;
        if (view != null) {
            view.setOnClickListener(new d());
        }
    }

    @Override // f0.a, c.AbstractActivityC0167u, android.app.Activity, c.AbstractC0121a.b
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        if (i2 != 1202) {
            super.onRequestPermissionsResult(i2, strArr, iArr);
        } else if (iArr.length <= 0 || iArr[0] != 0) {
            Toast.makeText(this, "未授权读取存储", 0).show();
        } else {
            m0();
        }
    }

    public final void p0(int i2, String str, ArrayList arrayList) {
        if (i2 >= this.f3079E.size()) {
            k0(str, o0.E.a(arrayList));
            return;
        }
        byte[] bArr = (byte[]) this.f3079E.get(i2);
        String str2 = (String) this.f3080F.get(i2);
        String str3 = (String) this.f3081G.get(i2);
        if (str2 == null) {
            str2 = "moment.jpg";
        }
        g0.d.T("/media", bArr, str2, str3, this.f3078D, new e(arrayList, i2, str));
    }

    public final void q0(String str) {
        p0(0, str, new ArrayList());
    }
}

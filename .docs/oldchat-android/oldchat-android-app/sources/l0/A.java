package l0;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;
import g0.d;
import o0.AbstractC0452s;
import o0.AbstractC0455v;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Activity f5756a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ImageView f5757b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f5758c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f5759d;

    public class a implements d.i {
        public a() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                A.this.h(new JSONObject(str).optString("url"));
            } catch (Exception unused) {
                Toast.makeText(A.this.f5756a, "头像更新失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(A.this.f5756a, "头像上传失败: " + i2, 0).show();
        }
    }

    public class b implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f5761a;

        public b(String str) {
            this.f5761a = str;
        }

        @Override // g0.d.i
        public void b(String str) {
            AbstractC0455v.h(A.this.f5757b, this.f5761a);
            Toast.makeText(A.this.f5756a, "头像已更新", 0).show();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(A.this.f5756a, "头像更新失败: " + i2, 0).show();
        }
    }

    public A(Activity activity, ImageView imageView, String str, String str2) {
        this.f5756a = activity;
        this.f5757b = imageView;
        this.f5758c = str;
        this.f5759d = str2;
    }

    public boolean d(int i2, int i3, Intent intent, int i4) {
        if (i2 != i4 || i3 != -1 || intent == null) {
            return false;
        }
        Uri data = intent.getData();
        if (data == null) {
            return true;
        }
        i(data);
        return true;
    }

    public boolean e(int i2, int[] iArr, int i3, int i4) {
        if (i2 != i3) {
            return false;
        }
        if (iArr.length <= 0 || iArr[0] != 0) {
            Toast.makeText(this.f5756a, "未授权读取存储", 0).show();
            return true;
        }
        g(i4);
        return true;
    }

    public void f(int i2, int i3) {
        if (this.f5756a == null) {
            return;
        }
        g(i2);
    }

    public final void g(int i2) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        try {
            this.f5756a.startActivityForResult(Intent.createChooser(intent, "选择群头像"), i2);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(this.f5756a, "无法选择图片", 0).show();
        }
    }

    public final void h(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("group_id", this.f5759d);
            jSONObject.put("avatar_url", str);
            g0.d.S("/groups/avatar", jSONObject, this.f5758c, new b(str));
        } catch (Exception unused) {
            Toast.makeText(this.f5756a, "头像更新失败", 0).show();
        }
    }

    public final void i(Uri uri) {
        try {
            g0.d.T("/media", AbstractC0452s.d(this.f5756a.getContentResolver(), uri, 512, 409600), "group_avatar.jpg", "image/jpeg", this.f5758c, new a());
        } catch (Exception unused) {
            Toast.makeText(this.f5756a, "头像上传失败", 0).show();
        }
    }
}

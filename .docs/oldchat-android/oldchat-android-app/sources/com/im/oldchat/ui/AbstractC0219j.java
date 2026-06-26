package com.im.oldchat.ui;

import android.app.Activity;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.C0218i;
import g0.d;
import java.io.File;
import o0.AbstractC0452s;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.im.oldchat.ui.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0219j extends AbstractC0220k {

    /* JADX INFO: renamed from: com.im.oldchat.ui.j$a */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractC0219j abstractC0219j = AbstractC0219j.this;
            if (abstractC0219j.f4642t != null) {
                abstractC0219j.f4647y = true;
                abstractC0219j.p(true);
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.j$b */
    public class b implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f4613a;

        public b(int i2) {
            this.f4613a = i2;
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                String strOptString = new JSONObject(str).optString("url");
                C0218i.j jVar = AbstractC0219j.this.f4633k;
                if (jVar != null) {
                    jVar.a("voice", strOptString, "", this.f4613a);
                }
            } catch (Exception unused) {
                Toast.makeText(AbstractC0219j.this.f4623a, "发送语音失败", 0).show();
            }
            AbstractC0219j.this.k(false);
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            AbstractC0219j.this.k(false);
            if (g0.e.v(i2, str)) {
                return;
            }
            String lowerCase = str == null ? "" : str.toLowerCase();
            if (i2 == 403) {
                if (lowerCase.contains("video_disabled")) {
                    Toast.makeText(AbstractC0219j.this.f4623a, "服务器禁用了视频/3GP上传，请联系管理员", 0).show();
                    return;
                } else if (lowerCase.contains("user_banned")) {
                    Toast.makeText(AbstractC0219j.this.f4623a, "账号已被封禁，无法发送语音", 0).show();
                    return;
                }
            }
            Toast.makeText(AbstractC0219j.this.f4623a, "发送语音失败: " + i2, 0).show();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.j$c */
    public class c implements d.j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int[] f4615a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ long[] f4616b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Toast f4617c;

        /* JADX INFO: renamed from: com.im.oldchat.ui.j$c$a */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f4619a;

            public a(int i2) {
                this.f4619a = i2;
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.f4617c.setText("上传中 " + this.f4619a + "%");
                c.this.f4617c.show();
            }
        }

        public c(int[] iArr, long[] jArr, Toast toast) {
            this.f4615a = iArr;
            this.f4616b = jArr;
            this.f4617c = toast;
        }

        @Override // g0.d.j
        public void a(long j2, long j3) {
            if (j3 <= 0) {
                return;
            }
            int i2 = (int) ((j2 * 100) / j3);
            long jCurrentTimeMillis = System.currentTimeMillis();
            int[] iArr = this.f4615a;
            boolean z2 = i2 != iArr[0];
            long[] jArr = this.f4616b;
            boolean z3 = jCurrentTimeMillis - jArr[0] >= 180;
            if (z2 || z3 || i2 >= 100) {
                jArr[0] = jCurrentTimeMillis;
                iArr[0] = i2;
                AbstractC0219j.this.f4623a.runOnUiThread(new a(i2));
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.j$d */
    public class d implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Toast f4621a;

        public d(Toast toast) {
            this.f4621a = toast;
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                this.f4621a.cancel();
                JSONObject jSONObject = new JSONObject(str);
                String strOptString = jSONObject.optString("url");
                String strOptString2 = jSONObject.optString("thumb_url");
                C0218i.j jVar = AbstractC0219j.this.f4633k;
                if (jVar != null) {
                    jVar.a("image", strOptString, strOptString2, 0);
                }
            } catch (Exception unused) {
                Toast.makeText(AbstractC0219j.this.f4623a, "发送图片失败", 0).show();
            }
            AbstractC0219j.this.k(false);
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            this.f4621a.cancel();
            AbstractC0219j.this.k(false);
            if (g0.e.v(i2, str)) {
                return;
            }
            if (i2 == 413 || (str != null && str.contains("image_too_large"))) {
                Toast.makeText(AbstractC0219j.this.f4623a, "图片不能超过3MB", 0).show();
                return;
            }
            Toast.makeText(AbstractC0219j.this.f4623a, "发送图片失败: " + i2, 0).show();
        }
    }

    public AbstractC0219j(Activity activity, String str, EditText editText, Button button, View view, View view2, View view3, View view4, View view5, View view6, View view7, ProgressBar progressBar, TextView textView, C0218i.j jVar, C0218i.k kVar) {
        super(activity, str, editText, button, view, view2, view3, view4, view5, view6, view7, progressBar, textView, jVar, kVar);
    }

    public void s() {
        Runnable runnable = this.f4646x;
        if (runnable != null) {
            this.f4645w.removeCallbacks(runnable);
            this.f4646x = null;
        }
    }

    public String t(String str) {
        String lowerCase = str == null ? "" : str.toLowerCase();
        return (lowerCase.endsWith(".m4a") || lowerCase.endsWith(".mp4")) ? "audio/mp4" : lowerCase.endsWith(".aac") ? "audio/aac" : lowerCase.endsWith(".amr") ? "audio/amr" : lowerCase.endsWith(".wav") ? "audio/wav" : "audio/3gpp";
    }

    public final String u(File file) {
        String name;
        if (file == null || (name = file.getName()) == null) {
            return "voice.3gp";
        }
        String strTrim = name.trim();
        return strTrim.length() > 0 ? strTrim : "voice.3gp";
    }

    public void v() {
        s();
        a aVar = new a();
        this.f4646x = aVar;
        this.f4645w.postDelayed(aVar, 60000L);
    }

    public void w(Uri uri) {
        try {
            k(true);
            Toast toastMakeText = Toast.makeText(this.f4623a, "上传中 0%", 0);
            toastMakeText.show();
            byte[] bArrD = AbstractC0452s.d(this.f4623a.getContentResolver(), uri, 1280, 409600);
            byte[] bArrD2 = AbstractC0452s.d(this.f4623a.getContentResolver(), uri, 320, 409600);
            if (bArrD != null && bArrD.length != 0 && bArrD.length <= 3145728) {
                if (bArrD2 == null || bArrD2.length <= 3145728) {
                    g0.d.X("/media", bArrD, "image.jpg", "image/jpeg", bArrD2, "thumb.jpg", "image/jpeg", this.f4624b, new c(new int[]{-1}, new long[]{0}, toastMakeText), new d(toastMakeText));
                    return;
                }
                toastMakeText.cancel();
                Toast.makeText(this.f4623a, "图片不能超过3MB", 0).show();
                k(false);
                return;
            }
            toastMakeText.cancel();
            Toast.makeText(this.f4623a, "图片不能超过3MB", 0).show();
            k(false);
        } catch (Exception unused) {
            Toast.makeText(this.f4623a, "发送图片失败", 0).show();
            k(false);
        }
    }

    public void x(File file, long j2) {
        int i2 = (int) j2;
        if (i2 > 60000) {
            i2 = 60000;
        }
        if (i2 < 1) {
            i2 = 1;
        }
        try {
            k(true);
            byte[] bArrM = m(file);
            String strU = u(file);
            g0.d.T("/media", bArrM, strU, t(strU), this.f4624b, new b(i2));
        } catch (Exception unused) {
            Toast.makeText(this.f4623a, "发送语音失败", 0).show();
            k(false);
        }
    }
}

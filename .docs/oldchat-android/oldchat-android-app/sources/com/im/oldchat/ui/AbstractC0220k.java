package com.im.oldchat.ui;

import android.app.Activity;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.C0218i;
import g0.d;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import o0.D0;
import org.json.JSONObject;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: renamed from: com.im.oldchat.ui.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0220k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Activity f4623a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f4624b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final EditText f4625c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Button f4626d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final View f4627e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final View f4628f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final View f4629g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final View f4630h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final View f4631i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final View f4632j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final C0218i.j f4633k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final C0218i.k f4634l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final View f4635m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ProgressBar f4636n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final TextView f4637o;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public MediaRecorder f4642t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public File f4643u;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public Runnable f4646x;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ExecutorService f4638p = Executors.newSingleThreadExecutor();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final AtomicBoolean f4639q = new AtomicBoolean(false);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f4640r = false;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f4641s = false;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public long f4644v = 0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final Handler f4645w = new Handler();

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f4647y = false;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f4648z = 0;

    /* JADX INFO: renamed from: com.im.oldchat.ui.k$a */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Uri f4649a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f4650b;

        public a(Uri uri, String str) {
            this.f4649a = uri;
            this.f4650b = str;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            File fileD;
            try {
                fileD = D0.d(AbstractC0220k.this.f4623a, this.f4649a, 52428800L, 720, this.f4650b);
                if (fileD != null) {
                    try {
                        if (fileD.exists()) {
                            if (fileD.length() > 52428800) {
                                AbstractC0220k.this.l("视频太大，请压缩后再发(需<50MB)");
                                AbstractC0220k.this.g(fileD, null);
                                AbstractC0220k.this.h();
                                return;
                            } else {
                                int iMin = (int) Math.min(2147483647L, Math.max(0L, D0.c(AbstractC0220k.this.f4623a, fileD)));
                                File fileA = D0.a(AbstractC0220k.this.f4623a, fileD, 360, 85, 204800L);
                                AbstractC0220k.this.o("上传中 0%", false, 0);
                                AbstractC0220k.this.r(fileD, fileA, iMin, this.f4650b);
                                return;
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        AbstractC0220k.this.g(fileD, null);
                        AbstractC0220k.this.h();
                        throw th;
                    }
                }
                AbstractC0220k.this.l("读取视频失败");
                AbstractC0220k.this.g(fileD, null);
                AbstractC0220k.this.h();
            } catch (Throwable th2) {
                th = th2;
                fileD = null;
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.k$b */
    public class b implements d.k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ File f4652a;

        public b(File file) {
            this.f4652a = file;
        }

        @Override // g0.d.k
        public InputStream a() {
            return new FileInputStream(this.f4652a);
        }

        @Override // g0.d.k
        public long length() {
            return this.f4652a.length();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.k$c */
    public class c implements d.j {
        public c() {
        }

        @Override // g0.d.j
        public void a(long j2, long j3) {
            int i2 = j3 > 0 ? (int) ((j2 * 100) / j3) : 0;
            AbstractC0220k.this.o("上传中 " + i2 + "%", false, i2);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.k$d */
    public class d implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f4655a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ File f4656b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ File f4657c;

        public d(int i2, File file, File file2) {
            this.f4655a = i2;
            this.f4656b = file;
            this.f4657c = file2;
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                AbstractC0220k.this.i();
                JSONObject jSONObject = new JSONObject(str);
                String strOptString = jSONObject.optString("url");
                String strOptString2 = jSONObject.optString("thumb_url");
                C0218i.j jVar = AbstractC0220k.this.f4633k;
                if (jVar != null) {
                    jVar.a("video", strOptString, strOptString2, this.f4655a);
                }
            } catch (Exception unused) {
                Toast.makeText(AbstractC0220k.this.f4623a, "发送视频失败", 0).show();
            }
            AbstractC0220k.this.g(this.f4656b, this.f4657c);
            AbstractC0220k.this.h();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            AbstractC0220k.this.i();
            AbstractC0220k.this.g(this.f4656b, this.f4657c);
            AbstractC0220k.this.h();
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(AbstractC0220k.this.f4623a, "发送视频失败: " + i2, 0).show();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.k$e */
    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractC0220k.this.i();
            AbstractC0220k.this.k(false);
            AbstractC0220k.this.f4639q.set(false);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.k$f */
    public class f implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f4660a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f4661b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f4662c;

        public f(boolean z2, int i2, String str) {
            this.f4660a = z2;
            this.f4661b = i2;
            this.f4662c = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            ProgressBar progressBar = AbstractC0220k.this.f4636n;
            if (progressBar != null) {
                progressBar.setVisibility(0);
                AbstractC0220k.this.f4636n.setIndeterminate(this.f4660a);
                if (!this.f4660a) {
                    AbstractC0220k.this.f4636n.setProgress(Math.max(0, Math.min(this.f4661b, 100)));
                }
            }
            View view = AbstractC0220k.this.f4635m;
            if (view != null) {
                view.setVisibility(0);
            }
            TextView textView = AbstractC0220k.this.f4637o;
            if (textView != null) {
                textView.setVisibility(0);
                TextView textView2 = AbstractC0220k.this.f4637o;
                String str = this.f4662c;
                if (str == null) {
                    str = "";
                }
                textView2.setText(str);
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.k$g */
    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ProgressBar progressBar = AbstractC0220k.this.f4636n;
            if (progressBar != null) {
                progressBar.setVisibility(8);
            }
            View view = AbstractC0220k.this.f4635m;
            if (view != null) {
                view.setVisibility(8);
            }
            TextView textView = AbstractC0220k.this.f4637o;
            if (textView != null) {
                textView.setVisibility(8);
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.k$h */
    public class h implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4665a;

        public h(String str) {
            this.f4665a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(AbstractC0220k.this.f4623a, this.f4665a, 0).show();
        }
    }

    public AbstractC0220k(Activity activity, String str, EditText editText, Button button, View view, View view2, View view3, View view4, View view5, View view6, View view7, ProgressBar progressBar, TextView textView, C0218i.j jVar, C0218i.k kVar) {
        this.f4623a = activity;
        this.f4624b = str;
        this.f4625c = editText;
        this.f4626d = button;
        this.f4627e = view;
        this.f4628f = view2;
        this.f4629g = view3;
        this.f4630h = view4;
        this.f4631i = view5;
        this.f4632j = view6;
        this.f4635m = view7;
        this.f4636n = progressBar;
        this.f4637o = textView;
        this.f4633k = jVar;
        this.f4634l = kVar;
    }

    public final void g(File file, File file2) {
        if (file != null && file.exists()) {
            file.delete();
        }
        if (file2 == null || !file2.exists()) {
            return;
        }
        file2.delete();
    }

    public final void h() {
        Activity activity = this.f4623a;
        if (activity != null) {
            activity.runOnUiThread(new e());
            return;
        }
        i();
        k(false);
        this.f4639q.set(false);
    }

    public final void i() {
        Activity activity = this.f4623a;
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new g());
    }

    public boolean j(String str) {
        if (str == null) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        return lowerCase.contains("mp4") || lowerCase.contains("3gpp") || lowerCase.contains("3gp");
    }

    public void k(boolean z2) {
        C0218i.k kVar = this.f4634l;
        if (kVar != null) {
            kVar.a(z2);
        }
    }

    public final void l(String str) {
        Activity activity = this.f4623a;
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new h(str));
    }

    public byte[] m(File file) {
        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[PKIFailureInfo.certConfirmed];
        while (true) {
            int i2 = fileInputStream.read(bArr);
            if (i2 == -1) {
                fileInputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i2);
        }
    }

    public String n(Uri uri) {
        Activity activity = this.f4623a;
        if (activity != null && uri != null) {
            String type = activity.getContentResolver().getType(uri);
            if (type != null && type.startsWith("video/")) {
                return type;
            }
            String path = uri.getPath();
            if (path != null) {
                String lowerCase = path.toLowerCase();
                if (lowerCase.endsWith(".3gp") || lowerCase.endsWith(".3gpp")) {
                    return "video/3gpp";
                }
                if (lowerCase.endsWith(".mp4") || lowerCase.endsWith(".m4v")) {
                    return "video/mp4";
                }
            }
        }
        return null;
    }

    public final void o(String str, boolean z2, int i2) {
        Activity activity = this.f4623a;
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new f(z2, i2, str));
    }

    public abstract void p(boolean z2);

    public void q(Uri uri) {
        if (uri == null) {
            return;
        }
        if (this.f4639q.getAndSet(true)) {
            Toast.makeText(this.f4623a, "视频处理中，请稍候", 0).show();
            return;
        }
        k(true);
        o("视频处理中...", true, 0);
        String strN = n(uri);
        if (strN != null && j(strN)) {
            this.f4638p.execute(new a(uri, strN));
            return;
        }
        i();
        this.f4639q.set(false);
        k(false);
        Toast.makeText(this.f4623a, "仅支持MP4/3GP视频", 0).show();
    }

    public final void r(File file, File file2, int i2, String str) {
        byte[] bArrM;
        String name;
        String str2;
        String str3 = str;
        if (file2 == null || !file2.exists()) {
            bArrM = null;
            name = null;
            str2 = null;
        } else {
            try {
                str2 = "image/jpeg";
                bArrM = m(file2);
                name = file2.getName();
            } catch (Exception unused) {
                bArrM = null;
                name = null;
                str2 = null;
            }
        }
        if (str3 == null || !str3.startsWith("video/")) {
            str3 = "video/mp4";
        }
        g0.d.V("/media", new b(file), file.getName(), str3, bArrM, name, str2, this.f4624b, new c(), new d(i2, file, file2));
    }
}

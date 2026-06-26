package com.im.oldchat.ui;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import g0.d;
import java.io.File;
import java.lang.ref.WeakReference;
import java.security.MessageDigest;
import o0.AbstractC0459z;
import org.json.JSONObject;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public abstract class r {

    public interface a {
        void a(String str);

        void b(String str);
    }

    public static class b extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference f4728a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final File f4729b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final boolean f4730c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final String f4731d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final a f4732e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public String f4733f;

        public class a implements d.i {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Activity f4734a;

            public a(Activity activity) {
                this.f4734a = activity;
            }

            @Override // g0.d.i
            public void b(String str) {
                try {
                    String strOptString = new JSONObject(str).optString("url");
                    if (strOptString != null && strOptString.length() > 0) {
                        b.this.g(this.f4734a, r.b(b.this.f4729b, b.this.f4730c), strOptString);
                    }
                    if (b.this.f4732e != null) {
                        b.this.f4732e.b(strOptString);
                    }
                } catch (Exception unused) {
                    if (b.this.f4732e != null) {
                        b.this.f4732e.a("发送表情失败");
                    }
                }
            }

            @Override // g0.d.i
            public void c(int i2, String str) {
                if (b.this.f4732e != null) {
                    if (i2 == 413 || (str != null && str.contains("image_too_large"))) {
                        b.this.f4732e.a("表情包不能超过3MB");
                        return;
                    }
                    b.this.f4732e.a("发送表情失败: " + i2);
                }
            }
        }

        public b(Activity activity, File file, boolean z2, String str, a aVar) {
            this.f4728a = new WeakReference(activity);
            this.f4729b = file;
            this.f4730c = z2;
            this.f4731d = str;
            this.f4732e = aVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public byte[] doInBackground(Void... voidArr) {
            try {
                File file = this.f4729b;
                if (file != null && file.exists()) {
                    if (this.f4729b.length() <= 3145728) {
                        return AbstractC0459z.a(this.f4729b);
                    }
                    this.f4733f = "表情包不能超过3MB";
                    return null;
                }
                this.f4733f = "表情文件不存在";
                return null;
            } catch (Exception unused) {
                this.f4733f = "发送表情失败";
                return null;
            }
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(byte[] bArr) {
            Activity activity = (Activity) this.f4728a.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            if (bArr != null) {
                g0.d.T("/media", bArr, this.f4729b.getName(), this.f4730c ? "image/gif" : AbstractC0459z.b(this.f4729b.getName()), this.f4731d, new a(activity));
                return;
            }
            a aVar = this.f4732e;
            if (aVar != null) {
                String str = this.f4733f;
                if (str == null) {
                    str = "发送表情失败";
                }
                aVar.a(str);
            }
        }

        public final void g(Activity activity, String str, String str2) {
            if (activity == null || str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
                return;
            }
            activity.getApplicationContext().getSharedPreferences("emoji_upload_cache", 0).edit().putString("f_" + str, str2).apply();
        }
    }

    public static String b(File file, boolean z2) {
        if (file == null || !file.exists()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(file.getAbsolutePath());
        sb.append('|');
        sb.append(file.length());
        sb.append('|');
        sb.append(file.lastModified());
        sb.append('|');
        sb.append(z2 ? '1' : '0');
        return e(sb.toString());
    }

    public static String c(Context context, String str) {
        if (context == null || str == null || str.length() == 0) {
            return null;
        }
        return context.getSharedPreferences("emoji_upload_cache", 0).getString("f_" + str, null);
    }

    public static void d(Activity activity, File file, boolean z2, String str, a aVar) {
        if (file == null || !file.exists()) {
            if (aVar != null) {
                aVar.a("表情文件不存在");
            }
        } else {
            if (file.length() > 3145728) {
                if (aVar != null) {
                    aVar.a("表情包不能超过3MB");
                    return;
                }
                return;
            }
            String strC = c(activity == null ? null : activity.getApplicationContext(), b(file, z2));
            if (strC == null || strC.length() <= 0) {
                new b(activity, file, z2, str, aVar).execute(new Void[0]);
            } else if (aVar != null) {
                aVar.b(strC);
            }
        }
    }

    public static String e(String str) {
        if (str == null) {
            return "";
        }
        try {
            byte[] bArrDigest = MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA1).digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(bArrDigest.length * 2);
            for (byte b2 : bArrDigest) {
                String hexString = Integer.toHexString(b2 & 255);
                if (hexString.length() == 1) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (Exception unused) {
            return String.valueOf(str.hashCode());
        }
    }
}

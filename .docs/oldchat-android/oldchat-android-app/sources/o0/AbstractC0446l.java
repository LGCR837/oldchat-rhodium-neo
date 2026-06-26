package o0;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import java.io.File;

/* JADX INFO: renamed from: o0.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0446l {

    /* JADX INFO: renamed from: o0.l$a */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f7114a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ c f7115b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f7116c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f7117d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Context f7118e;

        public a(String str, c cVar, String str2, String str3, Context context) {
            this.f7114a = str;
            this.f7115b = cVar;
            this.f7116c = str2;
            this.f7117d = str3;
            this.f7118e = context;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            File file;
            d dVarH;
            String strH;
            try {
                strH = AbstractC0446l.h(this.f7114a);
            } catch (Exception e2) {
                e = e2;
                file = null;
            }
            if (strH != null && strH.length() != 0) {
                String strF = AbstractC0446l.f(strH, this.f7116c);
                String str = this.f7117d;
                String str2 = ((str == null || str.isEmpty()) ? "oldchat_" : this.f7117d) + System.currentTimeMillis() + strF;
                String strJ = AbstractC0448n.j(this.f7118e);
                if (Build.VERSION.SDK_INT >= 29) {
                    d dVarG = AbstractC0448n.g(this.f7118e, strH, str2, strF, strJ);
                    if (!dVarG.f7123a) {
                        AbstractC0446l.g(this.f7115b, false, AbstractC0446l.i(dVarG), null);
                        return;
                    }
                    AbstractC0446l.g(this.f7115b, true, "已保存到下载: " + str2, null);
                    return;
                }
                File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                if (externalStoragePublicDirectory == null || (!externalStoragePublicDirectory.exists() && !externalStoragePublicDirectory.mkdirs())) {
                    externalStoragePublicDirectory = this.f7118e.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
                }
                if (externalStoragePublicDirectory == null) {
                    externalStoragePublicDirectory = this.f7118e.getExternalFilesDir(null);
                }
                if (externalStoragePublicDirectory == null) {
                    AbstractC0446l.g(this.f7115b, false, "无法访问存储目录", null);
                    return;
                }
                if (!externalStoragePublicDirectory.exists()) {
                    externalStoragePublicDirectory.mkdirs();
                }
                file = new File(externalStoragePublicDirectory, str2);
                try {
                    dVarH = AbstractC0448n.e(strH, file, strJ);
                } catch (Exception e3) {
                    e = e3;
                    dVarH = AbstractC0448n.h(AbstractC0448n.i(e));
                }
                if (!dVarH.f7123a || file == null) {
                    if (file != null && file.exists()) {
                        file.delete();
                    }
                    AbstractC0446l.g(this.f7115b, false, AbstractC0446l.i(dVarH), null);
                    return;
                }
                AbstractC0446l.k(this.f7118e, file);
                AbstractC0446l.g(this.f7115b, true, "已保存到下载: " + file.getAbsolutePath(), file);
                return;
            }
            AbstractC0446l.g(this.f7115b, false, "下载链接无效", null);
        }
    }

    /* JADX INFO: renamed from: o0.l$b */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ c f7119a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f7120b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f7121c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ File f7122d;

        public b(c cVar, boolean z2, String str, File file) {
            this.f7119a = cVar;
            this.f7120b = z2;
            this.f7121c = str;
            this.f7122d = file;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f7119a.a(this.f7120b, this.f7121c, this.f7122d);
        }
    }

    /* JADX INFO: renamed from: o0.l$c */
    public interface c {
        void a(boolean z2, String str, File file);
    }

    /* JADX INFO: renamed from: o0.l$d */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final boolean f7123a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f7124b;

        public d(boolean z2, String str) {
            this.f7123a = z2;
            this.f7124b = str;
        }
    }

    public static String f(String str, String str2) {
        if (str != null) {
            int iIndexOf = str.indexOf(63);
            if (iIndexOf >= 0) {
                str = str.substring(0, iIndexOf);
            }
            int iIndexOf2 = str.indexOf(35);
            if (iIndexOf2 >= 0) {
                str = str.substring(0, iIndexOf2);
            }
            int iLastIndexOf = str.lastIndexOf(46);
            if (iLastIndexOf >= 0 && iLastIndexOf < str.length() - 1) {
                String lowerCase = str.substring(iLastIndexOf).toLowerCase();
                if (lowerCase.length() <= 8) {
                    return lowerCase;
                }
            }
        }
        if (str2 == null || str2.isEmpty()) {
            return ".jpg";
        }
        if (str2.startsWith(".")) {
            return str2;
        }
        return "." + str2;
    }

    public static void g(c cVar, boolean z2, String str, File file) {
        if (cVar == null) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new b(cVar, z2, str, file));
    }

    public static String h(String str) {
        return A.g(str);
    }

    public static String i(d dVar) {
        String str = dVar == null ? "保存失败" : dVar.f7124b;
        return (str == null || str.length() == 0) ? "保存失败" : str;
    }

    public static void j(Context context, String str, String str2, String str3, c cVar) {
        if (context != null && str != null && !str.isEmpty()) {
            new Thread(new a(str, cVar, str3, str2, context.getApplicationContext())).start();
        } else if (cVar != null) {
            cVar.a(false, "无效的下载链接", null);
        }
    }

    public static void k(Context context, File file) {
        try {
            MediaScannerConnection.scanFile(context, new String[]{file.getAbsolutePath()}, null, null);
        } catch (Throwable unused) {
        }
    }
}

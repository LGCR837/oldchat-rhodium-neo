package o0;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: renamed from: o0.d0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0438d0 {

    /* JADX INFO: renamed from: o0.d0$a */
    public class a extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f7087a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f7088b;

        public a(Activity activity, boolean z2) {
            this.f7087a = activity;
            this.f7088b = z2;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e doInBackground(Void... voidArr) {
            return AbstractC0438d0.i();
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(e eVar) {
            if (this.f7087a.isFinishing()) {
                return;
            }
            if (eVar == null) {
                if (this.f7088b) {
                    Toast.makeText(this.f7087a, "检查更新失败，请稍后重试", 0).show();
                }
            } else if (AbstractC0438d0.m(this.f7087a, eVar)) {
                AbstractC0438d0.n(this.f7087a, eVar);
            } else if (this.f7088b) {
                Toast.makeText(this.f7087a, "当前已是最新版本", 0).show();
            }
        }
    }

    /* JADX INFO: renamed from: o0.d0$b */
    public class b implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f7089a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ e f7090b;

        public b(Activity activity, e eVar) {
            this.f7089a = activity;
            this.f7090b = eVar;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            AbstractC0438d0.o(this.f7089a, this.f7090b);
        }
    }

    /* JADX INFO: renamed from: o0.d0$c */
    public class c implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f7091a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ e f7092b;

        public c(Activity activity, e eVar) {
            this.f7091a = activity;
            this.f7092b = eVar;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            this.f7091a.getSharedPreferences("update_prefs", 0).edit().putInt("skip_version", this.f7092b.f7096a).apply();
        }
    }

    /* JADX INFO: renamed from: o0.d0$d */
    public static class d extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference f7093a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final e f7094b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ProgressDialog f7095c;

        public d(Activity activity, e eVar) {
            this.f7093a = new WeakReference(activity);
            this.f7094b = eVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public File doInBackground(Void... voidArr) {
            Activity activity = (Activity) this.f7093a.get();
            if (activity == null) {
                return null;
            }
            try {
                String strJ = AbstractC0438d0.j();
                if (!strJ.endsWith("/")) {
                    strJ = strJ + "/";
                }
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strJ + "update/" + this.f7094b.f7098c).openConnection();
                httpURLConnection.setConnectTimeout(8000);
                httpURLConnection.setReadTimeout(15000);
                if (httpURLConnection.getResponseCode() != 200) {
                    return null;
                }
                int contentLength = httpURLConnection.getContentLength();
                if (contentLength > 0) {
                    publishProgress(0);
                }
                File externalFilesDir = activity.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
                if (externalFilesDir == null) {
                    externalFilesDir = activity.getCacheDir();
                }
                if (externalFilesDir == null) {
                    return null;
                }
                if (!externalFilesDir.exists()) {
                    externalFilesDir.mkdirs();
                }
                File file = new File(externalFilesDir, this.f7094b.f7098c);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bArr = new byte[PKIFailureInfo.certRevoked];
                int i2 = 0;
                while (true) {
                    int i3 = bufferedInputStream.read(bArr);
                    if (i3 == -1) {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        bufferedInputStream.close();
                        return file;
                    }
                    fileOutputStream.write(bArr, 0, i3);
                    i2 += i3;
                    if (contentLength > 0) {
                        publishProgress(Integer.valueOf((int) ((i2 * 100.0f) / contentLength)));
                    }
                }
            } catch (Exception unused) {
                return null;
            }
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(File file) {
            ProgressDialog progressDialog = this.f7095c;
            if (progressDialog != null && progressDialog.isShowing()) {
                try {
                    this.f7095c.dismiss();
                } catch (Exception unused) {
                }
            }
            Activity activity = (Activity) this.f7093a.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            if (file != null && file.exists()) {
                AbstractC0438d0.l(activity, file);
                return;
            }
            Toast.makeText(activity, "下载失败", 0).show();
            e eVar = this.f7094b;
            if (eVar.f7100e) {
                AbstractC0438d0.n(activity, eVar);
            }
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onProgressUpdate(Integer... numArr) {
            int iIntValue;
            if (this.f7095c == null || numArr == null || numArr.length == 0 || (iIntValue = numArr[0].intValue()) <= 0) {
                return;
            }
            this.f7095c.setIndeterminate(false);
            this.f7095c.setProgress(iIntValue);
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            Activity activity = (Activity) this.f7093a.get();
            if (activity == null || activity.isFinishing()) {
                cancel(true);
                return;
            }
            ProgressDialog progressDialog = new ProgressDialog(activity);
            this.f7095c = progressDialog;
            progressDialog.setMessage("正在下载更新...");
            this.f7095c.setProgressStyle(1);
            this.f7095c.setIndeterminate(true);
            this.f7095c.setCancelable(true ^ this.f7094b.f7100e);
            this.f7095c.show();
        }
    }

    /* JADX INFO: renamed from: o0.d0$e */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f7096a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f7097b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f7098c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f7099d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f7100e;

        public e() {
        }

        public /* synthetic */ e(a aVar) {
            this();
        }
    }

    public static void g(Activity activity) {
        h(activity, false);
    }

    public static void h(Activity activity, boolean z2) {
        if (activity == null) {
            return;
        }
        new a(activity, z2).execute(new Void[0]);
    }

    public static e i() {
        a aVar = null;
        try {
            String strJ = j();
            if (!strJ.endsWith("/")) {
                strJ = strJ + "/";
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strJ + "update/update.json").openConnection();
            httpURLConnection.setConnectTimeout(8000);
            httpURLConnection.setReadTimeout(15000);
            if (httpURLConnection.getResponseCode() != 200) {
                return null;
            }
            byte[] bArr = new byte[PKIFailureInfo.certConfirmed];
            StringBuilder sb = new StringBuilder();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            while (true) {
                int i2 = bufferedInputStream.read(bArr);
                if (i2 == -1) {
                    break;
                }
                sb.append(new String(bArr, 0, i2, "UTF-8"));
            }
            bufferedInputStream.close();
            JSONObject jSONObject = new JSONObject(sb.toString());
            e eVar = new e(aVar);
            eVar.f7096a = jSONObject.optInt("version_code", 0);
            eVar.f7097b = jSONObject.optString("version_name", "");
            eVar.f7098c = jSONObject.optString("apk", "");
            eVar.f7099d = jSONObject.optString("notes", "");
            eVar.f7100e = jSONObject.optBoolean("force", false);
            if (eVar.f7096a > 0) {
                if (eVar.f7098c.length() != 0) {
                    return eVar;
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static String j() {
        String str = g0.e.f5335a;
        if (str.endsWith("/v1")) {
            return str.substring(0, str.length() - 3);
        }
        int iIndexOf = str.indexOf("/v1/");
        return iIndexOf > 0 ? str.substring(0, iIndexOf) : str;
    }

    public static int k(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static void l(Activity activity, File file) {
        Uri uriFromFile;
        try {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 26 && !activity.getPackageManager().canRequestPackageInstalls()) {
                activity.startActivity(new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES", Uri.parse("package:" + activity.getPackageName())));
                Toast.makeText(activity, "请授权安装权限后重试", 0).show();
                return;
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(268435456);
            intent.addFlags(1);
            if (i2 >= 24) {
                uriFromFile = FileProvider.e(activity, activity.getPackageName() + ".fileprovider", file);
            } else {
                uriFromFile = Uri.fromFile(file);
            }
            intent.setDataAndType(uriFromFile, "application/vnd.android.package-archive");
            activity.startActivity(intent);
        } catch (Exception unused) {
            Toast.makeText(activity, "无法安装更新", 0).show();
        }
    }

    public static boolean m(Activity activity, e eVar) {
        if (eVar.f7096a <= k(activity)) {
            return false;
        }
        if (eVar.f7100e) {
            return true;
        }
        return eVar.f7096a > activity.getSharedPreferences("update_prefs", 0).getInt("skip_version", 0);
    }

    public static void n(Activity activity, e eVar) {
        String str = eVar.f7097b;
        String str2 = "发现新版本";
        if (str != null && str.length() > 0) {
            str2 = "发现新版本 " + eVar.f7097b;
        }
        String str3 = eVar.f7099d;
        String str4 = (str3 == null || str3.length() <= 0) ? "有新版本可用" : eVar.f7099d;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(str2);
        builder.setMessage(str4);
        builder.setCancelable(!eVar.f7100e);
        builder.setPositiveButton("立即更新", new b(activity, eVar));
        if (!eVar.f7100e) {
            builder.setNegativeButton("跳过此版本", new c(activity, eVar));
            builder.setNeutralButton("稍后", (DialogInterface.OnClickListener) null);
        }
        AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.setCanceledOnTouchOutside(!eVar.f7100e);
        alertDialogCreate.show();
    }

    public static void o(Activity activity, e eVar) {
        new d(activity, eVar).execute(new Void[0]);
    }
}

package o0;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.text.TextUtils;
import com.im.oldchat.ui.CrashActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;

/* JADX INFO: renamed from: o0.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0442h implements Thread.UncaughtExceptionHandler {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static C0442h f7101b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f7102a;

    public C0442h(Context context) {
        this.f7102a = context.getApplicationContext();
    }

    public static synchronized void b(Context context) {
        if (f7101b == null) {
            C0442h c0442h = new C0442h(context);
            f7101b = c0442h;
            Thread.setDefaultUncaughtExceptionHandler(c0442h);
        }
    }

    public static boolean c() throws Throwable {
        String strA = N.a();
        if (TextUtils.isEmpty(strA)) {
            return false;
        }
        return strA.contains(":crash");
    }

    public final String a(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        printWriter.println("Thread: " + Thread.currentThread().getName());
        printWriter.println("Time: " + System.currentTimeMillis());
        printWriter.println();
        th.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }

    public final String d(String str) {
        try {
            File file = new File(this.f7102a.getFilesDir(), "crash");
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, "last_crash.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file2, false);
            fileOutputStream.write(str.getBytes("UTF-8"));
            fileOutputStream.flush();
            fileOutputStream.close();
            return file2.getAbsolutePath();
        } catch (Exception unused) {
            return "";
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        String strA = a(th);
        String strD = d(strA);
        Intent intent = new Intent(this.f7102a, (Class<?>) CrashActivity.class);
        intent.addFlags(268468224);
        intent.putExtra("log", strA);
        intent.putExtra("log_path", strD);
        this.f7102a.startActivity(intent);
        Process.killProcess(Process.myPid());
        System.exit(10);
    }
}

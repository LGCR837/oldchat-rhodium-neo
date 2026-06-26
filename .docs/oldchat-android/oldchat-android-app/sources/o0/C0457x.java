package o0;

import android.content.Context;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: renamed from: o0.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0457x {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static C0457x f7167g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Queue f7168a = new ConcurrentLinkedQueue();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f7169b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final SimpleDateFormat f7170c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final SimpleDateFormat f7171d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public File f7172e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public BufferedWriter f7173f;

    /* JADX INFO: renamed from: o0.x$a */
    public class a implements FilenameFilter {
        public a() {
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return str.startsWith("log_") && str.endsWith(".txt");
        }
    }

    public C0457x(Context context) {
        Locale locale = Locale.US;
        this.f7170c = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", locale);
        this.f7171d = new SimpleDateFormat("yyyy-MM-dd", locale);
        this.f7169b = context.getApplicationContext();
        c();
    }

    public static synchronized C0457x a(Context context) {
        try {
            if (f7167g == null) {
                f7167g = new C0457x(context);
            }
        } catch (Throwable th) {
            throw th;
        }
        return f7167g;
    }

    public File b() {
        return new File(this.f7169b.getFilesDir(), "logs");
    }

    public final void c() {
        try {
            File file = new File(this.f7169b.getFilesDir(), "logs");
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, "log_" + this.f7171d.format(new Date()) + ".txt");
            this.f7172e = file2;
            if (file2.exists() && this.f7172e.length() > 10485760) {
                d(file);
            }
            this.f7173f = new BufferedWriter(new FileWriter(this.f7172e, true));
        } catch (IOException e2) {
            Log.e("OldChat", "Failed to init log file", e2);
        }
    }

    public final void d(File file) {
        try {
            BufferedWriter bufferedWriter = this.f7173f;
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            File[] fileArrListFiles = file.listFiles(new a());
            if (fileArrListFiles != null && fileArrListFiles.length >= 5) {
                File file2 = fileArrListFiles[0];
                for (File file3 : fileArrListFiles) {
                    if (file3.lastModified() < file2.lastModified()) {
                        file2 = file3;
                    }
                }
                file2.delete();
            }
            this.f7172e = new File(file, "log_" + this.f7171d.format(new Date()) + "_" + System.currentTimeMillis() + ".txt");
            this.f7173f = new BufferedWriter(new FileWriter(this.f7172e, true));
        } catch (IOException e2) {
            Log.e("OldChat", "Failed to rotate log file", e2);
        }
    }
}

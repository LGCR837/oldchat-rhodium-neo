package com.im.oldchat.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import com.im.oldchat.MainActivity;
import d.q;
import g0.d;
import java.io.InputStream;
import o0.G;
import o0.H;
import o0.J;

/* JADX INFO: loaded from: classes.dex */
public class ResourceUploadService extends Service {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Object f2312g = new Object();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static e f2313h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Handler f2314a = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f2315b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2316c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f2317d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f2318e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f2319f = 0;

    public class a implements d.k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Uri f2320a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ long f2321b;

        public a(Uri uri, long j2) {
            this.f2320a = uri;
            this.f2321b = j2;
        }

        @Override // g0.d.k
        public InputStream a() throws Exception {
            InputStream inputStreamOpenInputStream = ResourceUploadService.this.getContentResolver().openInputStream(this.f2320a);
            if (inputStreamOpenInputStream != null) {
                return inputStreamOpenInputStream;
            }
            throw new Exception("open_failed");
        }

        @Override // g0.d.k
        public long length() {
            return this.f2321b;
        }
    }

    public class b implements d.j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2323a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f2324b;

        public b(String str, String str2) {
            this.f2323a = str;
            this.f2324b = str2;
        }

        @Override // g0.d.j
        public void a(long j2, long j3) {
            ResourceUploadService.this.o(this.f2323a, this.f2324b, j2, j3);
        }
    }

    public class c implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2326a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f2327b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ long f2328c;

        public c(String str, String str2, long j2) {
            this.f2326a = str;
            this.f2327b = str2;
            this.f2328c = j2;
        }

        @Override // g0.d.i
        public void b(String str) {
            ResourceUploadService resourceUploadService = ResourceUploadService.this;
            String str2 = this.f2326a;
            String str3 = this.f2327b;
            long j2 = this.f2328c;
            resourceUploadService.y(str2, str3, j2, j2, false, false, 0L);
            ResourceUploadService.this.w();
            ResourceUploadService.this.q(this.f2326a, str, this.f2327b);
            ResourceUploadService.this.stopSelf();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            ResourceUploadService resourceUploadService = ResourceUploadService.this;
            String str2 = this.f2326a;
            String str3 = this.f2327b;
            long j2 = this.f2328c;
            resourceUploadService.y(str2, str3, j2, 0L, j2 <= 0, false, 0L);
            ResourceUploadService.this.w();
            ResourceUploadService.this.r(this.f2326a, i2, str);
            ResourceUploadService.this.stopSelf();
        }
    }

    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ long f2330a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f2331b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f2332c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ long f2333d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ long f2334e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ int f2335f;

        public d(long j2, String str, String str2, long j3, long j4, int i2) {
            this.f2330a = j2;
            this.f2331b = str;
            this.f2332c = str2;
            this.f2333d = j3;
            this.f2334e = j4;
            this.f2335f = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            long j2 = this.f2330a;
            boolean z2 = j2 <= 0;
            ResourceUploadService.this.y(this.f2331b, this.f2332c, j2, this.f2333d, z2, true, this.f2334e);
            ResourceUploadService.this.x(this.f2332c, this.f2335f, z2);
            ResourceUploadService.this.s(this.f2331b, this.f2332c, this.f2333d, this.f2330a, this.f2335f, z2, this.f2334e);
        }
    }

    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2337a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f2338b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f2339c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f2340d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2341e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f2342f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f2343g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public long f2344h;
    }

    public static e k() {
        synchronized (f2312g) {
            try {
                if (f2313h == null) {
                    return null;
                }
                e eVar = new e();
                e eVar2 = f2313h;
                eVar.f2337a = eVar2.f2337a;
                eVar.f2338b = eVar2.f2338b;
                eVar.f2339c = eVar2.f2339c;
                eVar.f2340d = eVar2.f2340d;
                eVar.f2341e = eVar2.f2341e;
                eVar.f2342f = eVar2.f2342f;
                eVar.f2343g = eVar2.f2343g;
                eVar.f2344h = eVar2.f2344h;
                return eVar;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static boolean l() {
        boolean z2;
        synchronized (f2312g) {
            try {
                e eVar = f2313h;
                z2 = eVar != null && eVar.f2343g;
            } finally {
            }
        }
        return z2;
    }

    public static boolean m(String str) {
        boolean z2;
        synchronized (f2312g) {
            try {
                e eVar = f2313h;
                z2 = eVar != null && eVar.f2343g && str != null && str.equals(eVar.f2337a);
            } finally {
            }
        }
        return z2;
    }

    public static int n() {
        return Build.VERSION.SDK_INT >= 23 ? 201326592 : 134217728;
    }

    public static void u(Context context, String str, Uri uri, String str2, String str3, long j2) {
        if (context == null || uri == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) ResourceUploadService.class);
        intent.setAction("com.im.oldchat.action.RESOURCE_UPLOAD_START");
        intent.putExtra("section_id", str);
        intent.putExtra("uri", uri.toString());
        intent.putExtra("file_name", str2);
        intent.putExtra("content_type", str3);
        intent.putExtra("total_bytes", j2);
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                context.startForegroundService(intent);
            } else {
                context.startService(intent);
            }
        } catch (RuntimeException unused) {
        }
    }

    public final Notification h(String str, int i2, boolean z2) {
        return i(str, i2, z2, f0.e.f5004Y);
    }

    public final Notification i(String str, int i2, boolean z2, int i3) {
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (notificationManager != null && Build.VERSION.SDK_INT >= 26) {
            H.a(notificationManager, "oldchat_upload", "资源上传", 2);
        }
        if (str == null || str.length() == 0) {
            str = "资源";
        }
        return J.d(this, "oldchat_upload", i3, "正在上传资源", str, z2, 100, Math.max(0, Math.min(i2, 100)), true, -1, j());
    }

    public final PendingIntent j() {
        Intent intent = new Intent(this, (Class<?>) MainActivity.class);
        intent.addFlags(335544320);
        return PendingIntent.getActivity(this, 0, intent, n());
    }

    public final void o(String str, String str2, long j2, long j3) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        int i2 = j3 > 0 ? (int) ((100 * j2) / j3) : 0;
        if (i2 != this.f2316c || jCurrentTimeMillis - this.f2315b >= 300) {
            long j4 = this.f2317d;
            if (j4 == 0) {
                this.f2317d = jCurrentTimeMillis;
                this.f2318e = j2;
            } else if (jCurrentTimeMillis - j4 >= 500) {
                long j5 = j2 - this.f2318e;
                long j6 = jCurrentTimeMillis - j4;
                if (j6 > 0 && j5 >= 0) {
                    this.f2319f = (j5 * 1000) / j6;
                }
                this.f2317d = jCurrentTimeMillis;
                this.f2318e = j2;
            }
            this.f2316c = i2;
            this.f2315b = jCurrentTimeMillis;
            this.f2314a.post(new d(j3, str, str2, j2, this.f2319f, i2));
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        if (intent == null || !"com.im.oldchat.action.RESOURCE_UPLOAD_START".equals(intent.getAction())) {
            return 1;
        }
        v(intent);
        return 1;
    }

    public final String p() {
        return getSharedPreferences("auth", 0).getString("access_token", "");
    }

    public final void q(String str, String str2, String str3) {
        Intent intent = new Intent("com.im.oldchat.action.RESOURCE_UPLOAD_DONE");
        intent.putExtra("section_id", str);
        intent.putExtra("file_name", str3);
        intent.putExtra("response", str2);
        q.c(this).e(intent);
    }

    public final void r(String str, int i2, String str2) {
        Intent intent = new Intent("com.im.oldchat.action.RESOURCE_UPLOAD_ERROR");
        intent.putExtra("section_id", str);
        intent.putExtra("error_code", i2);
        intent.putExtra("error_message", str2);
        q.c(this).e(intent);
    }

    public final void s(String str, String str2, long j2, long j3, int i2, boolean z2, long j4) {
        Intent intent = new Intent("com.im.oldchat.action.RESOURCE_UPLOAD_PROGRESS");
        intent.putExtra("section_id", str);
        intent.putExtra("file_name", str2);
        intent.putExtra("uploaded_bytes", j2);
        intent.putExtra("total_bytes", j3);
        intent.putExtra("progress", i2);
        intent.putExtra("indeterminate", z2);
        intent.putExtra("speed_bps", j4);
        q.c(this).e(intent);
    }

    public final void t(String str, int i2, boolean z2) {
        try {
            try {
                startForeground(73, h(str, i2, z2));
            } catch (Throwable unused) {
                startForeground(73, i(str, i2, z2, f0.e.f4999T));
            }
        } catch (Throwable unused2) {
            stopSelf();
        }
    }

    public final void v(Intent intent) {
        if (l()) {
            r(intent.getStringExtra("section_id"), -2, "upload_busy");
            return;
        }
        if (!G.d().f()) {
            r(intent.getStringExtra("section_id"), -1, "network_unavailable");
            stopSelf();
            return;
        }
        String stringExtra = intent.getStringExtra("section_id");
        String stringExtra2 = intent.getStringExtra("uri");
        String stringExtra3 = intent.getStringExtra("file_name");
        String stringExtra4 = intent.getStringExtra("content_type");
        long longExtra = intent.getLongExtra("total_bytes", 0L);
        if (stringExtra == null || stringExtra.length() == 0 || stringExtra2 == null || stringExtra2.length() == 0) {
            stopSelf();
            return;
        }
        String strP = p();
        if (strP == null || strP.length() == 0) {
            r(stringExtra, 401, "no_token");
            stopSelf();
        } else {
            Uri uri = Uri.parse(stringExtra2);
            y(stringExtra, stringExtra3, longExtra, 0L, longExtra <= 0, true, 0L);
            t(stringExtra3, 0, longExtra <= 0);
            g0.d.U("/resources/upload", new a(uri, longExtra), stringExtra3, stringExtra4, strP, "section_id", stringExtra, new b(stringExtra, stringExtra3), new c(stringExtra, stringExtra3, longExtra));
        }
    }

    public final void w() {
        try {
            stopForeground(true);
        } catch (Exception unused) {
        }
    }

    public final void x(String str, int i2, boolean z2) {
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (notificationManager == null) {
            return;
        }
        try {
            try {
                notificationManager.notify(73, h(str, i2, z2));
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            notificationManager.notify(73, i(str, i2, z2, f0.e.f4999T));
        }
    }

    public final void y(String str, String str2, long j2, long j3, boolean z2, boolean z3, long j4) {
        synchronized (f2312g) {
            try {
                if (f2313h == null) {
                    f2313h = new e();
                }
                e eVar = f2313h;
                eVar.f2337a = str;
                eVar.f2338b = str2;
                eVar.f2339c = j2;
                eVar.f2340d = j3;
                eVar.f2341e = j2 > 0 ? (int) ((j3 * 100) / j2) : 0;
                eVar.f2342f = z2;
                eVar.f2343g = z3;
                eVar.f2344h = j4;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

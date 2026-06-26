package com.im.oldchat.service;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import com.im.oldchat.MainActivity;
import g0.j;
import o0.AbstractC0435c;
import o0.H;
import o0.J;

/* JADX INFO: loaded from: classes.dex */
public class MessageService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile boolean f2267a = false;

    public static int e() {
        return Build.VERSION.SDK_INT >= 23 ? 201326592 : 134217728;
    }

    public static void f(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) MessageService.class);
        try {
            try {
                if (Build.VERSION.SDK_INT < 26 || AbstractC0435c.f()) {
                    context.startService(intent);
                } else {
                    context.startForegroundService(intent);
                }
            } catch (RuntimeException unused) {
                j.u().z(context.getApplicationContext());
            }
        } catch (Exception unused2) {
        }
    }

    public static void g(Context context) {
        if (context == null) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        if (Build.VERSION.SDK_INT < 31 || AbstractC0435c.f()) {
            f(applicationContext);
        } else {
            try {
                j.u().z(applicationContext);
            } catch (Exception unused) {
            }
        }
    }

    public static void h(Context context) {
        if (context == null) {
            return;
        }
        context.stopService(new Intent(context, (Class<?>) MessageService.class));
    }

    public final Notification a() {
        try {
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            if (Build.VERSION.SDK_INT >= 26 && notificationManager != null) {
                H.a(notificationManager, "oldchat_service", "后台连接", 2);
            }
            Notification notificationC = J.c(this, "oldchat_service", R.drawable.stat_notify_chat, "旧聊已连接", "正在后台接收消息", true, c());
            if (notificationC != null) {
                return notificationC;
            }
        } catch (Throwable unused) {
        }
        return b();
    }

    public final Notification b() {
        PendingIntent pendingIntentC = c();
        if (Build.VERSION.SDK_INT < 26) {
            return new Notification.Builder(this).setSmallIcon(R.drawable.stat_notify_chat).setContentTitle("旧聊已连接").setContentText("正在后台接收消息").setOngoing(true).setContentIntent(pendingIntentC).build();
        }
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (notificationManager != null) {
            H.a(notificationManager, "oldchat_service", "后台连接", 2);
        }
        return new Notification.Builder(this, "oldchat_service").setSmallIcon(R.drawable.stat_notify_chat).setContentTitle("旧聊已连接").setContentText("正在后台接收消息").setOngoing(true).setContentIntent(pendingIntentC).build();
    }

    public final PendingIntent c() {
        Intent intent = new Intent(this, (Class<?>) MainActivity.class);
        intent.addFlags(335544320);
        return PendingIntent.getActivity(this, 0, intent, e());
    }

    public final void d() {
        if (this.f2267a) {
            return;
        }
        try {
            try {
                startForeground(42, a());
                this.f2267a = true;
            } catch (Throwable unused) {
                stopSelf();
            }
        } catch (Throwable unused2) {
            startForeground(42, b());
            this.f2267a = true;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        d();
        j.u().z(getApplicationContext());
    }

    @Override // android.app.Service
    public void onDestroy() {
        j.u().A();
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        d();
        j.u().z(getApplicationContext());
        return 1;
    }
}

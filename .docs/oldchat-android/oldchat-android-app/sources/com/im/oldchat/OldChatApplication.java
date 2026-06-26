package com.im.oldchat;

import android.app.Application;
import android.content.Context;
import com.im.oldchat.service.MessageService;
import o.f;
import o0.AbstractC0435c;
import o0.C0442h;
import o0.C0457x;
import o0.L;
import o0.U;
import o0.V;

/* JADX INFO: loaded from: classes.dex */
public class OldChatApplication extends Application {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Context f2156a;

    public static Context a() {
        return f2156a;
    }

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        b();
    }

    public final void b() {
        try {
            Class.forName("android.support.multidex.MultiDex").getMethod("install", Context.class).invoke(null, this);
        } catch (Throwable unused) {
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        f2156a = getApplicationContext();
        L.c(this);
        U.a(this);
        f.x(true);
        f.B(V.g(this) ? 2 : 1);
        C0457x.a(this);
        if (C0442h.c()) {
            return;
        }
        C0442h.b(this);
        AbstractC0435c.e(this);
        MessageService.g(this);
    }
}

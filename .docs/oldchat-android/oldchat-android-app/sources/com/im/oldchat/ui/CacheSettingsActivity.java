package com.im.oldchat.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import o0.AbstractC0437d;
import o0.AbstractC0440f;
import o0.AbstractC0455v;

/* JADX INFO: loaded from: classes.dex */
public class CacheSettingsActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public TextView f2452A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public TextView f2453B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public TextView f2454C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public TextView f2455D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public TextView f2456E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public TextView f2457F;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public TextView f2458w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public TextView f2459x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f2460y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public TextView f2461z;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractC0437d.d(CacheSettingsActivity.this);
            CacheSettingsActivity.this.p0("已清理头像映射缓存");
        }
    }

    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Runnable f2463a;

        public b(Runnable runnable) {
            this.f2463a = runnable;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f2463a.run();
            CacheSettingsActivity.this.q0();
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CacheSettingsActivity.this.finish();
        }
    }

    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractC0455v.c(CacheSettingsActivity.this);
            CacheSettingsActivity.this.p0("已清理图片缓存");
        }
    }

    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CacheSettingsActivity.this.j0();
            CacheSettingsActivity.this.p0("已清理语音缓存");
        }
    }

    public class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractC0440f.a(CacheSettingsActivity.this, "message_history_cache");
            CacheSettingsActivity.this.p0("已清理聊天记录缓存");
        }
    }

    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractC0440f.a(CacheSettingsActivity.this, "friend_cache");
            CacheSettingsActivity.this.p0("已清理好友缓存");
        }
    }

    public class h implements Runnable {
        public h() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractC0440f.a(CacheSettingsActivity.this, "group_cache");
            CacheSettingsActivity.this.p0("已清理群列表缓存");
        }
    }

    public class i implements Runnable {
        public i() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractC0440f.a(CacheSettingsActivity.this, "moment_cache");
            CacheSettingsActivity.this.p0("已清理动态缓存");
        }
    }

    public class j implements Runnable {
        public j() {
        }

        @Override // java.lang.Runnable
        public void run() {
            j0.r.b(CacheSettingsActivity.this);
            j0.j.a(CacheSettingsActivity.this);
            CacheSettingsActivity.this.p0("已清理最近会话缓存");
        }
    }

    public class k implements Runnable {
        public k() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractC0440f.a(CacheSettingsActivity.this, "user_name_cache");
            CacheSettingsActivity.this.p0("已清理用户名缓存");
        }
    }

    public final void i0(int i2, Runnable runnable) {
        View viewA0 = a0(i2);
        if (viewA0 == null || runnable == null) {
            return;
        }
        viewA0.setOnClickListener(new b(runnable));
    }

    public final void j0() {
        File fileM0 = m0();
        File fileL0 = l0();
        k0(fileM0);
        if (fileL0 != null) {
            if (fileM0 == null || !fileL0.getAbsolutePath().equals(fileM0.getAbsolutePath())) {
                k0(fileL0);
            }
        }
    }

    public final void k0(File file) {
        File[] fileArrListFiles;
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                k0(file2);
            }
        }
        try {
            file.delete();
        } catch (Exception unused) {
        }
    }

    public final File l0() {
        File cacheDir = getCacheDir();
        if (cacheDir == null) {
            return null;
        }
        return new File(cacheDir, "voice_cache");
    }

    public final File m0() {
        File filesDir = getFilesDir();
        if (filesDir == null) {
            return null;
        }
        return new File(filesDir, "voice_cache");
    }

    public final long n0() {
        File fileM0 = m0();
        File fileL0 = l0();
        long jC = AbstractC0440f.c(fileM0);
        return fileL0 != null ? (fileM0 == null || !fileL0.getAbsolutePath().equals(fileM0.getAbsolutePath())) ? jC + AbstractC0440f.c(fileL0) : jC : jC;
    }

    public final void o0(TextView textView, long j2) {
        if (textView != null) {
            textView.setText(AbstractC0440f.b(j2));
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5137c);
        this.f2458w = (TextView) a0(f0.f.d9);
        this.f2459x = (TextView) a0(f0.f.qa);
        this.f2460y = (TextView) a0(f0.f.fe);
        this.f2461z = (TextView) a0(f0.f.g9);
        this.f2452A = (TextView) a0(f0.f.aa);
        this.f2453B = (TextView) a0(f0.f.ka);
        this.f2454C = (TextView) a0(f0.f.Ea);
        this.f2455D = (TextView) a0(f0.f.Jc);
        this.f2456E = (TextView) a0(f0.f.de);
        this.f2457F = (TextView) a0(f0.f.Y8);
        View viewA0 = a0(f0.f.f5103w);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new c());
        }
        i0(f0.f.f5054O, new d());
        i0(f0.f.f5058S, new e());
        i0(f0.f.f5050K, new f());
        i0(f0.f.f5051L, new g());
        i0(f0.f.f5053N, new h());
        i0(f0.f.f5055P, new i());
        i0(f0.f.f5056Q, new j());
        i0(f0.f.f5057R, new k());
        i0(f0.f.f5049J, new a());
        q0();
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onResume() {
        super.onResume();
        q0();
    }

    public final void p0(String str) {
        Toast.makeText(this, str, 0).show();
    }

    public final void q0() {
        long jC = AbstractC0440f.c(new File(getCacheDir(), "img_cache"));
        long jN0 = n0();
        long jD = AbstractC0440f.d(this, "message_history_cache");
        long jD2 = AbstractC0440f.d(this, "friend_cache");
        long jD3 = AbstractC0440f.d(this, "group_cache");
        long jD4 = AbstractC0440f.d(this, "moment_cache");
        long jD5 = AbstractC0440f.d(this, "recent_chats") + AbstractC0440f.d(this, "recent_groups");
        long jD6 = AbstractC0440f.d(this, "user_name_cache");
        long jD7 = AbstractC0440f.d(this, "avatar_cache");
        o0(this.f2458w, jC + jN0 + jD + jD2 + jD3 + jD4 + jD5 + jD6 + jD7);
        o0(this.f2459x, jC);
        o0(this.f2460y, jN0);
        o0(this.f2461z, jD);
        o0(this.f2452A, jD2);
        o0(this.f2453B, jD3);
        o0(this.f2454C, jD4);
        o0(this.f2455D, jD5);
        o0(this.f2456E, jD6);
        o0(this.f2457F, jD7);
    }
}

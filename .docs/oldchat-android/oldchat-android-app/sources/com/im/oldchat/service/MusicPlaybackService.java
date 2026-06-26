package com.im.oldchat.service;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import c.AbstractC0157o0;
import com.im.oldchat.ui.MusicPlayerActivity;
import d.q;
import java.io.File;
import java.security.MessageDigest;
import java.util.Locale;
import o0.A;
import o0.AbstractC0450p;
import o0.H;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class MusicPlaybackService extends Service implements AudioManager.OnAudioFocusChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public MediaPlayer f2268a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f2269b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2270c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2271d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2272e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2273f;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f2283p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f2284q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f2285r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public AudioManager f2286s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f2287t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public volatile boolean f2288u;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public q f2291x;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f2274g = "";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f2275h = "";

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f2276i = "";

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f2277j = "";

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f2278k = "";

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f2279l = "";

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f2280m = "";

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f2281n = "";

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f2282o = "";

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final Object f2289v = new Object();

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public String f2290w = "";

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final Handler f2292y = new Handler();

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final Runnable f2293z = new a();

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MusicPlaybackService.this.p0();
            MusicPlaybackService.this.W("");
            if (MusicPlaybackService.this.Q()) {
                MusicPlaybackService.this.f2292y.postDelayed(this, 500L);
            }
        }
    }

    public class b implements MediaPlayer.OnPreparedListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2295a;

        public b(int i2) {
            this.f2295a = i2;
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            if (this.f2295a != MusicPlaybackService.this.f2270c) {
                return;
            }
            MusicPlaybackService.this.f2269b = false;
            try {
                MusicPlaybackService.this.f2271d = mediaPlayer.getDuration();
            } catch (Exception unused) {
                MusicPlaybackService.this.f2271d = 0;
            }
            MusicPlaybackService.this.f2272e = 0;
            MusicPlaybackService.this.f0();
            MusicPlaybackService.this.m0();
        }
    }

    public class c implements MediaPlayer.OnCompletionListener {
        public c() {
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            if (MusicPlaybackService.this.f2273f) {
                MusicPlaybackService.this.c0(0);
                MusicPlaybackService.this.f0();
            } else {
                MusicPlaybackService.this.j0();
                MusicPlaybackService.this.p0();
                MusicPlaybackService.this.W("");
                MusicPlaybackService.this.o0();
            }
        }
    }

    public class d implements MediaPlayer.OnErrorListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2298a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f2299b;

        public d(String str, int i2) {
            this.f2298a = str;
            this.f2299b = i2;
        }

        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
            if (MusicPlaybackService.this.n0(this.f2298a, this.f2299b)) {
                return true;
            }
            MusicPlaybackService.this.f2269b = false;
            MusicPlaybackService.this.j0();
            MusicPlaybackService.this.X();
            MusicPlaybackService.this.W("播放失败");
            MusicPlaybackService.this.o0();
            return true;
        }
    }

    public class e implements MediaPlayer.OnPreparedListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2301a;

        public e(int i2) {
            this.f2301a = i2;
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            if (this.f2301a != MusicPlaybackService.this.f2270c) {
                return;
            }
            MusicPlaybackService.this.f2269b = false;
            try {
                MusicPlaybackService.this.f2271d = mediaPlayer.getDuration();
            } catch (Exception unused) {
                MusicPlaybackService.this.f2271d = 0;
            }
            MusicPlaybackService.this.f2272e = 0;
            MusicPlaybackService.this.f0();
            MusicPlaybackService.this.m0();
        }
    }

    public class f implements MediaPlayer.OnCompletionListener {
        public f() {
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            if (MusicPlaybackService.this.f2273f) {
                MusicPlaybackService.this.c0(0);
                MusicPlaybackService.this.f0();
            } else {
                MusicPlaybackService.this.j0();
                MusicPlaybackService.this.p0();
                MusicPlaybackService.this.W("");
                MusicPlaybackService.this.o0();
            }
        }
    }

    public class g implements MediaPlayer.OnErrorListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ File f2304a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f2305b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f2306c;

        public g(File file, String str, int i2) {
            this.f2304a = file;
            this.f2305b = str;
            this.f2306c = i2;
        }

        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
            MusicPlaybackService.this.j0();
            MusicPlaybackService.this.X();
            File file = this.f2304a;
            if (file != null) {
                try {
                    file.delete();
                } catch (Exception unused) {
                }
            }
            String str = this.f2305b;
            if (str == null || str.length() <= 0 || this.f2306c != MusicPlaybackService.this.f2270c) {
                MusicPlaybackService.this.f2269b = false;
                MusicPlaybackService.this.W("播放失败");
                MusicPlaybackService.this.o0();
                return true;
            }
            MusicPlaybackService.this.V(this.f2305b, this.f2306c);
            if (MusicPlaybackService.this.d0(this.f2305b)) {
                MusicPlaybackService.this.q0(this.f2305b);
            }
            return true;
        }
    }

    public class h extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2308a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f2309b;

        public h(String str, boolean z2) {
            this.f2308a = str;
            this.f2309b = z2;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public File doInBackground(Void... voidArr) {
            return MusicPlaybackService.this.I(this.f2308a);
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(File file) {
            synchronized (MusicPlaybackService.this.f2289v) {
                try {
                    if (this.f2308a.equals(MusicPlaybackService.this.f2290w)) {
                        MusicPlaybackService.this.f2290w = "";
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (file == null || !file.exists() || file.length() <= 0) {
                if (this.f2309b) {
                    MusicPlaybackService.this.H(this.f2308a, false, 0L, "缓存失败");
                }
            } else {
                MusicPlaybackService.this.m0();
                if (this.f2309b) {
                    MusicPlaybackService.this.H(this.f2308a, true, file.length(), "");
                }
            }
        }
    }

    public class i implements Runnable {
        public i() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                MusicPlaybackService.this.l0();
            } finally {
                MusicPlaybackService.this.f2288u = false;
            }
        }
    }

    public final PendingIntent B() {
        Intent intent = new Intent(this, (Class<?>) MusicPlayerActivity.class);
        intent.putExtra("song_name", this.f2274g);
        intent.putExtra("song_url", this.f2275h);
        intent.putExtra("cover_url", this.f2276i);
        intent.putExtra("lyrics_url", this.f2277j);
        intent.putExtra("owner_uid", this.f2278k);
        intent.putExtra("owner_name", this.f2279l);
        intent.putExtra("owner_title", this.f2280m);
        intent.putExtra("owner_avatar", this.f2281n);
        intent.putExtra("item_id", this.f2282o);
        intent.putExtra("item_likes", Math.max(0, this.f2283p));
        intent.putExtra("item_comments", Math.max(0, this.f2284q));
        intent.putExtra("item_liked", this.f2285r);
        intent.addFlags(603979776);
        return PendingIntent.getActivity(this, 3001, intent, T());
    }

    public final String C(String str) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA1).digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(bArrDigest.length * 2);
            for (byte b2 : bArrDigest) {
                int i2 = b2 & 255;
                if (i2 < 16) {
                    sb.append('0');
                }
                sb.append(Integer.toHexString(i2));
            }
            return sb.toString();
        } catch (Exception unused) {
            return Integer.toHexString(str.hashCode());
        }
    }

    public final Notification D() {
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (notificationManager == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 26) {
            return F();
        }
        H.a(notificationManager, "oldchat_music_playback", "音乐播放", 2);
        return E();
    }

    public final Notification E() {
        String str = this.f2274g;
        if (str == null || str.length() == 0) {
            str = "旧聊音乐";
        }
        Notification.Builder contentIntent = new Notification.Builder(this, "oldchat_music_playback").setSmallIcon(f0.e.f5004Y).setContentTitle(str).setContentText(a0()).setOngoing(true).setOnlyAlertOnce(true).setContentIntent(B());
        PendingIntent pendingIntentG = G("com.im.oldchat.action.music.TOGGLE_PLAY", 1);
        PendingIntent pendingIntentG2 = G("com.im.oldchat.action.music.TOGGLE_REPEAT_ONE", 2);
        PendingIntent pendingIntentG3 = G("com.im.oldchat.action.music.STOP", 3);
        if (pendingIntentG != null) {
            contentIntent.addAction(new Notification.Action.Builder(Q() ? R.drawable.ic_media_pause : R.drawable.ic_media_play, Q() ? "暂停" : "播放", pendingIntentG).build());
        }
        if (pendingIntentG2 != null) {
            contentIntent.addAction(new Notification.Action.Builder(R.drawable.ic_menu_rotate, this.f2273f ? "循环开" : "循环关", pendingIntentG2).build());
        }
        if (pendingIntentG3 != null) {
            contentIntent.addAction(new Notification.Action.Builder(R.drawable.ic_menu_close_clear_cancel, "关闭", pendingIntentG3).build());
        }
        return contentIntent.build();
    }

    public final Notification F() {
        String str = this.f2274g;
        if (str == null || str.length() == 0) {
            str = "旧聊音乐";
        }
        AbstractC0157o0.d dVarF = new AbstractC0157o0.d(this).n(f0.e.f5004Y).h(str).g(a0()).j(true).k(true).f(B());
        PendingIntent pendingIntentG = G("com.im.oldchat.action.music.TOGGLE_PLAY", 1);
        PendingIntent pendingIntentG2 = G("com.im.oldchat.action.music.TOGGLE_REPEAT_ONE", 2);
        PendingIntent pendingIntentG3 = G("com.im.oldchat.action.music.STOP", 3);
        if (pendingIntentG != null) {
            dVarF.a(Q() ? R.drawable.ic_media_pause : R.drawable.ic_media_play, Q() ? "暂停" : "播放", pendingIntentG);
        }
        if (pendingIntentG2 != null) {
            dVarF.a(R.drawable.ic_menu_rotate, this.f2273f ? "循环开" : "循环关", pendingIntentG2);
        }
        if (pendingIntentG3 != null) {
            dVarF.a(R.drawable.ic_menu_close_clear_cancel, "关闭", pendingIntentG3);
        }
        return dVarF.b();
    }

    public final PendingIntent G(String str, int i2) {
        Intent intent = new Intent(this, (Class<?>) MusicPlaybackService.class);
        intent.setAction(str);
        return PendingIntent.getService(this, i2, intent, T());
    }

    public final void H(String str, boolean z2, long j2, String str2) {
        Intent intent = new Intent("com.im.oldchat.action.music.CACHE_RESULT");
        intent.putExtra("cache_url", k0(str));
        intent.putExtra("cache_ok", z2);
        intent.putExtra("cache_size", Math.max(0L, j2));
        intent.putExtra("cache_error", k0(str2));
        q qVar = this.f2291x;
        if (qVar != null) {
            qVar.e(intent);
        }
    }

    public final File I(String str) {
        if (str != null && str.length() != 0) {
            File fileZ = Z(str);
            if (fileZ != null && fileZ.exists() && fileZ.length() > 0) {
                try {
                    fileZ.setLastModified(System.currentTimeMillis());
                } catch (Exception unused) {
                }
                return fileZ;
            }
            String[] strArrI = A.i(str);
            if (strArrI == null || strArrI.length == 0) {
                strArrI = new String[]{str};
            }
            for (String str2 : strArrI) {
                if (str2 != null && str2.length() != 0 && J(str2, fileZ)) {
                    return fileZ;
                }
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x014e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:157:0x017b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0155 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0182 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:175:? A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean J(java.lang.String r14, java.io.File r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 422
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.im.oldchat.service.MusicPlaybackService.J(java.lang.String, java.io.File):boolean");
    }

    public final String K(String str) {
        if (str == null) {
            return ".cache";
        }
        String lowerCase = str.toLowerCase(Locale.US);
        int iIndexOf = lowerCase.indexOf(63);
        if (iIndexOf >= 0) {
            lowerCase = lowerCase.substring(0, iIndexOf);
        }
        int iIndexOf2 = lowerCase.indexOf(35);
        if (iIndexOf2 >= 0) {
            lowerCase = lowerCase.substring(0, iIndexOf2);
        }
        String[] strArr = {".mp3", ".m4a", ".aac", ".amr", ".3gp", ".wav", ".ogg", ".flac"};
        for (int i2 = 0; i2 < 8; i2++) {
            String str2 = strArr[i2];
            if (lowerCase.endsWith(str2)) {
                return str2;
            }
        }
        return ".cache";
    }

    public final void L(Intent intent) {
        String strK0 = intent == null ? "" : k0(intent.getStringExtra("song_url"));
        String strG = A.g(strK0);
        if (strG.length() == 0) {
            H(strK0, false, 0L, "歌曲链接不可用");
        } else {
            r0(strG, true);
        }
    }

    public final void M(Intent intent) {
        if (intent == null) {
            return;
        }
        String strG = A.g(k0(intent.getStringExtra("song_url")));
        if (strG.length() == 0) {
            W("歌曲链接不可用");
            return;
        }
        String strK0 = k0(intent.getStringExtra("song_name"));
        String strK02 = k0(intent.getStringExtra("cover_url"));
        String strK03 = k0(intent.getStringExtra("lyrics_url"));
        String strK04 = k0(intent.getStringExtra("owner_uid"));
        String strK05 = k0(intent.getStringExtra("owner_name"));
        String strK06 = k0(intent.getStringExtra("owner_title"));
        String strK07 = k0(intent.getStringExtra("owner_avatar"));
        String strK08 = k0(intent.getStringExtra("item_id"));
        int iMax = Math.max(0, intent.getIntExtra("item_likes", 0));
        int iMax2 = Math.max(0, intent.getIntExtra("item_comments", 0));
        boolean booleanExtra = intent.getBooleanExtra("item_liked", false);
        if (strG.equals(this.f2275h) && this.f2268a != null) {
            this.f2274g = strK0;
            this.f2276i = strK02;
            this.f2277j = strK03;
            this.f2278k = strK04;
            this.f2279l = strK05;
            this.f2280m = strK06;
            this.f2281n = strK07;
            this.f2282o = strK08;
            this.f2283p = iMax;
            this.f2284q = iMax2;
            this.f2285r = booleanExtra;
            if (!this.f2269b && !Q()) {
                if (P()) {
                    c0(0);
                }
                f0();
            }
            p0();
            W("");
            o0();
            return;
        }
        this.f2274g = strK0;
        this.f2275h = strG;
        this.f2276i = strK02;
        this.f2277j = strK03;
        this.f2278k = strK04;
        this.f2279l = strK05;
        this.f2280m = strK06;
        this.f2281n = strK07;
        this.f2282o = strK08;
        this.f2283p = iMax;
        this.f2284q = iMax2;
        this.f2285r = booleanExtra;
        this.f2269b = true;
        this.f2271d = 0;
        this.f2272e = 0;
        W("");
        o0();
        int i2 = this.f2270c + 1;
        this.f2270c = i2;
        File fileZ = Z(strG);
        if (fileZ != null && fileZ.exists() && fileZ.length() > 0) {
            try {
                fileZ.setLastModified(System.currentTimeMillis());
            } catch (Exception unused) {
            }
            U(fileZ, strG, i2);
        } else {
            V(strG, i2);
            if (d0(strG)) {
                q0(strG);
            }
        }
    }

    public final void N() {
        if (this.f2269b) {
            W("");
            return;
        }
        if (this.f2268a != null) {
            if (Q()) {
                S();
                return;
            } else {
                f0();
                return;
            }
        }
        String str = this.f2275h;
        if (str == null || str.length() <= 0) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) MusicPlaybackService.class);
        intent.setAction("com.im.oldchat.action.music.PLAY_SONG");
        intent.putExtra("song_name", this.f2274g);
        intent.putExtra("song_url", this.f2275h);
        intent.putExtra("cover_url", this.f2276i);
        intent.putExtra("lyrics_url", this.f2277j);
        intent.putExtra("owner_uid", this.f2278k);
        intent.putExtra("owner_name", this.f2279l);
        intent.putExtra("owner_title", this.f2280m);
        intent.putExtra("owner_avatar", this.f2281n);
        intent.putExtra("item_id", this.f2282o);
        intent.putExtra("item_likes", this.f2283p);
        intent.putExtra("item_comments", this.f2284q);
        intent.putExtra("item_liked", this.f2285r);
        onStartCommand(intent, 0, 0);
    }

    public final void O(Intent intent) {
        if (intent == null) {
            return;
        }
        String strK0 = k0(intent.getStringExtra("item_id"));
        if (strK0.length() == 0) {
            return;
        }
        this.f2282o = strK0;
        this.f2283p = Math.max(0, intent.getIntExtra("item_likes", this.f2283p));
        this.f2284q = Math.max(0, intent.getIntExtra("item_comments", this.f2284q));
        this.f2285r = intent.getBooleanExtra("item_liked", this.f2285r);
        W("");
    }

    public final boolean P() {
        MediaPlayer mediaPlayer = this.f2268a;
        if (mediaPlayer == null) {
            return false;
        }
        int iMax = this.f2271d;
        int iMax2 = this.f2272e;
        if (iMax <= 0) {
            try {
                iMax = Math.max(0, mediaPlayer.getDuration());
            } catch (Exception unused) {
                iMax = 0;
            }
        }
        if (iMax2 <= 0) {
            try {
                iMax2 = Math.max(0, this.f2268a.getCurrentPosition());
            } catch (Exception unused2) {
                iMax2 = 0;
            }
        }
        return iMax > 0 && iMax2 >= iMax + (-400);
    }

    public final boolean Q() {
        MediaPlayer mediaPlayer = this.f2268a;
        if (mediaPlayer == null) {
            return false;
        }
        try {
            return mediaPlayer.isPlaying();
        } catch (Exception unused) {
            return false;
        }
    }

    public final boolean R() {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService("connectivity");
            if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected()) {
                if (Build.VERSION.SDK_INT >= 16) {
                    try {
                        if (connectivityManager.isActiveNetworkMetered()) {
                            return false;
                        }
                    } catch (Throwable unused) {
                    }
                }
                int type = activeNetworkInfo.getType();
                if (type == 1 || type == 9 || type == 6) {
                    return true;
                }
            }
        } catch (Throwable unused2) {
        }
        return false;
    }

    public final void S() {
        MediaPlayer mediaPlayer = this.f2268a;
        if (mediaPlayer != null) {
            try {
                if (mediaPlayer.isPlaying()) {
                    this.f2268a.pause();
                }
            } catch (Exception unused) {
            }
        }
        j0();
        p0();
        W("");
        o0();
        a();
    }

    public final int T() {
        return Build.VERSION.SDK_INT >= 23 ? 201326592 : 134217728;
    }

    public final void U(File file, String str, int i2) {
        X();
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.f2268a = mediaPlayer;
            if (Build.VERSION.SDK_INT >= 21) {
                mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().setLegacyStreamType(3).setUsage(1).setContentType(2).build());
            } else {
                mediaPlayer.setAudioStreamType(3);
            }
            this.f2268a.setDataSource(file.getAbsolutePath());
            this.f2268a.setOnPreparedListener(new e(i2));
            this.f2268a.setOnCompletionListener(new f());
            this.f2268a.setOnErrorListener(new g(file, str, i2));
            this.f2268a.prepareAsync();
        } catch (Exception unused) {
            X();
            if (file != null) {
                try {
                    file.delete();
                } catch (Exception unused2) {
                }
            }
            if (str == null || str.length() <= 0 || i2 != this.f2270c) {
                this.f2269b = false;
                W("播放失败");
                o0();
            } else {
                V(str, i2);
                if (d0(str)) {
                    q0(str);
                }
            }
        }
    }

    public final void V(String str, int i2) {
        X();
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.f2268a = mediaPlayer;
            if (Build.VERSION.SDK_INT >= 21) {
                mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().setLegacyStreamType(3).setUsage(1).setContentType(2).build());
            } else {
                mediaPlayer.setAudioStreamType(3);
            }
            this.f2268a.setDataSource(str);
            this.f2268a.setOnPreparedListener(new b(i2));
            this.f2268a.setOnCompletionListener(new c());
            this.f2268a.setOnErrorListener(new d(str, i2));
            this.f2268a.prepareAsync();
        } catch (Exception unused) {
            if (n0(str, i2)) {
                return;
            }
            this.f2269b = false;
            X();
            W("播放失败");
            o0();
        }
    }

    public final void W(String str) {
        Intent intent = new Intent("com.im.oldchat.action.music.STATE_CHANGED");
        intent.putExtra("song_name", this.f2274g);
        intent.putExtra("song_url", this.f2275h);
        intent.putExtra("cover_url", this.f2276i);
        intent.putExtra("lyrics_url", this.f2277j);
        intent.putExtra("owner_uid", this.f2278k);
        intent.putExtra("owner_name", this.f2279l);
        intent.putExtra("owner_title", this.f2280m);
        intent.putExtra("owner_avatar", this.f2281n);
        intent.putExtra("item_id", this.f2282o);
        intent.putExtra("item_likes", Math.max(0, this.f2283p));
        intent.putExtra("item_comments", Math.max(0, this.f2284q));
        intent.putExtra("item_liked", this.f2285r);
        intent.putExtra("is_preparing", this.f2269b);
        intent.putExtra("is_playing", Q());
        intent.putExtra("duration_ms", Math.max(0, this.f2271d));
        intent.putExtra("position_ms", Math.max(0, this.f2272e));
        intent.putExtra("repeat_one", this.f2273f);
        if (str == null) {
            str = "";
        }
        intent.putExtra("error", str);
        q qVar = this.f2291x;
        if (qVar != null) {
            qVar.e(intent);
        }
    }

    public final void X() {
        MediaPlayer mediaPlayer = this.f2268a;
        if (mediaPlayer == null) {
            return;
        }
        try {
            mediaPlayer.stop();
        } catch (Exception unused) {
        }
        try {
            this.f2268a.release();
        } catch (Exception unused2) {
        }
        this.f2268a = null;
    }

    public final boolean Y() {
        AudioManager audioManager = this.f2286s;
        return audioManager == null || audioManager.requestAudioFocus(this, 3, 1) == 1;
    }

    public final File Z(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        File filesDir = getFilesDir();
        if (filesDir == null) {
            filesDir = getCacheDir();
        }
        if (filesDir == null) {
            return null;
        }
        File file = new File(filesDir, "music_player_cache");
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, C(str) + K(str));
    }

    public final void a() {
        AudioManager audioManager = this.f2286s;
        if (audioManager == null) {
            return;
        }
        try {
            audioManager.abandonAudioFocus(this);
        } catch (Exception unused) {
        }
    }

    public final String a0() {
        return this.f2269b ? "歌曲加载中" : Q() ? "正在后台播放" : this.f2268a != null ? "已暂停" : "等待播放";
    }

    public final void b0(int i2) {
        if (i2 == 0) {
            W("");
            return;
        }
        int currentPosition = this.f2272e;
        MediaPlayer mediaPlayer = this.f2268a;
        if (mediaPlayer != null) {
            try {
                currentPosition = mediaPlayer.getCurrentPosition();
            } catch (Exception unused) {
            }
        }
        int i3 = currentPosition + i2;
        if (i3 < 0) {
            i3 = 0;
        }
        int iMax = this.f2271d;
        MediaPlayer mediaPlayer2 = this.f2268a;
        if (mediaPlayer2 != null) {
            try {
                iMax = Math.max(iMax, mediaPlayer2.getDuration());
            } catch (Exception unused2) {
            }
        }
        if (iMax > 0 && i3 > iMax) {
            i3 = iMax;
        }
        c0(i3);
    }

    public final void c0(int i2) {
        MediaPlayer mediaPlayer = this.f2268a;
        if (mediaPlayer == null) {
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        try {
            mediaPlayer.seekTo(i2);
        } catch (Exception unused) {
        }
        p0();
        W("");
    }

    public final boolean d0(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        File fileZ = Z(str);
        if (fileZ == null || !fileZ.exists() || fileZ.length() <= 0) {
            return R();
        }
        return false;
    }

    public final void e0(AsyncTask asyncTask) {
        if (asyncTask == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 11) {
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
        } else {
            asyncTask.execute((Object[]) null);
        }
    }

    public final void f0() {
        if (this.f2268a == null) {
            return;
        }
        if (!Y()) {
            W("音频焦点被占用");
            o0();
            return;
        }
        try {
            this.f2268a.start();
            g0();
            W("");
            o0();
        } catch (Exception unused) {
            W("播放失败");
            o0();
        }
    }

    public final void g0() {
        j0();
        this.f2292y.post(this.f2293z);
    }

    public final void h0() {
        this.f2270c++;
        this.f2269b = false;
        j0();
        X();
        a();
        i0();
        W("");
        stopSelf();
    }

    public final void i0() {
        if (Build.VERSION.SDK_INT >= 24) {
            stopForeground(true);
        } else {
            stopForeground(true);
        }
    }

    public final void j0() {
        this.f2292y.removeCallbacks(this.f2293z);
    }

    public final String k0(String str) {
        return str == null ? "" : str;
    }

    public final void l0() {
        File[] fileArrListFiles;
        File filesDir = getFilesDir();
        if (filesDir == null) {
            filesDir = getCacheDir();
        }
        if (filesDir == null) {
            return;
        }
        File file = new File(filesDir, "music_player_cache");
        if (!file.exists() || !file.isDirectory() || (fileArrListFiles = file.listFiles()) == null || fileArrListFiles.length == 0) {
            return;
        }
        AbstractC0450p.a(fileArrListFiles);
        long length = 0;
        int i2 = 0;
        for (File file2 : fileArrListFiles) {
            if (file2 != null && file2.isFile()) {
                length += file2.length();
                i2++;
            }
        }
        for (File file3 : fileArrListFiles) {
            if (length <= 230686720 && i2 <= 120) {
                return;
            }
            if (file3 != null && file3.isFile()) {
                long length2 = file3.length();
                if (file3.delete()) {
                    length -= length2;
                    i2--;
                }
            }
        }
    }

    public final void m0() {
        if (this.f2288u) {
            return;
        }
        this.f2288u = true;
        new Thread(new i(), "music-cache-trim-svc").start();
    }

    public final boolean n0(String str, int i2) {
        String strJ;
        if (i2 != this.f2270c || (strJ = A.j(str)) == null || strJ.length() == 0 || strJ.equals(str)) {
            return false;
        }
        this.f2275h = strJ;
        V(strJ, i2);
        if (!d0(strJ)) {
            return true;
        }
        q0(strJ);
        return true;
    }

    public final void o0() {
        Notification notificationD = D();
        if (notificationD == null) {
            return;
        }
        try {
            startForeground(5201, notificationD);
        } catch (Throwable unused) {
        }
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int i2) {
        if (i2 == -2) {
            this.f2287t = Q();
            S();
        } else if (i2 == -1) {
            this.f2287t = false;
            S();
        } else if (i2 == 1 && this.f2287t) {
            this.f2287t = false;
            f0();
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f2291x = q.c(this);
        this.f2286s = (AudioManager) getSystemService("audio");
    }

    @Override // android.app.Service
    public void onDestroy() {
        j0();
        X();
        a();
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        String action = intent == null ? "com.im.oldchat.action.music.REQUEST_STATE" : intent.getAction();
        if ("com.im.oldchat.action.music.PLAY_SONG".equals(action)) {
            M(intent);
        } else if ("com.im.oldchat.action.music.CACHE_SONG".equals(action)) {
            L(intent);
        } else if ("com.im.oldchat.action.music.TOGGLE_PLAY".equals(action)) {
            N();
        } else if ("com.im.oldchat.action.music.PAUSE".equals(action)) {
            S();
        } else if ("com.im.oldchat.action.music.STOP".equals(action)) {
            h0();
        } else {
            if ("com.im.oldchat.action.music.SEEK".equals(action)) {
                c0(intent != null ? intent.getIntExtra("position_ms", 0) : 0);
            } else if ("com.im.oldchat.action.music.SEEK_RELATIVE".equals(action)) {
                b0(intent != null ? intent.getIntExtra("seek_delta_ms", 0) : 0);
            } else if ("com.im.oldchat.action.music.TOGGLE_REPEAT_ONE".equals(action)) {
                this.f2273f = !this.f2273f;
                W("");
                o0();
            } else if ("com.im.oldchat.action.music.UPDATE_ITEM_META".equals(action)) {
                O(intent);
            } else if ("com.im.oldchat.action.music.REQUEST_STATE".equals(action)) {
                p0();
                W("");
            }
        }
        return 1;
    }

    public final void p0() {
        MediaPlayer mediaPlayer = this.f2268a;
        if (mediaPlayer == null) {
            this.f2272e = 0;
            if (this.f2271d < 0) {
                this.f2271d = 0;
                return;
            }
            return;
        }
        try {
            this.f2272e = Math.max(0, mediaPlayer.getCurrentPosition());
        } catch (Exception unused) {
            this.f2272e = 0;
        }
        try {
            this.f2271d = Math.max(0, this.f2268a.getDuration());
        } catch (Exception unused2) {
            if (this.f2271d < 0) {
                this.f2271d = 0;
            }
        }
    }

    public final void q0(String str) {
        r0(str, false);
    }

    public final void r0(String str, boolean z2) {
        if (str == null || str.length() == 0) {
            if (z2) {
                H("", false, 0L, "歌曲链接不可用");
                return;
            }
            return;
        }
        synchronized (this.f2289v) {
            try {
                if (str.equals(this.f2290w)) {
                    if (z2) {
                        H(str, false, 0L, "正在缓存中");
                    }
                } else {
                    this.f2290w = str;
                    e0(new h(str, z2));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

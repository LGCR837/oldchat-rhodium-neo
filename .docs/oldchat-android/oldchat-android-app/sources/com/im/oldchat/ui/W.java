package com.im.oldchat.ui;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.security.MessageDigest;
import o0.AbstractC0450p;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class W {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f4254a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j f4255b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public MediaPlayer f4257d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f4258e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f4259f;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public File f4263j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f4264k;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Handler f4256c = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4260g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4261h = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4262i = 0;

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ File f4265a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f4266b;

        public a(File file, int i2) {
            this.f4265a = file;
            this.f4266b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            W.this.E(this.f4265a, this.f4266b);
        }
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4268a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f4269b;

        public b(String str, int i2) {
            this.f4268a = str;
            this.f4269b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            W.this.p(this.f4268a, this.f4269b);
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ File f4271a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f4272b;

        public c(File file, int i2) {
            this.f4271a = file;
            this.f4272b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            W.this.E(this.f4271a, this.f4272b);
        }
    }

    public class d implements MediaPlayer.OnPreparedListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f4274a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ MediaPlayer f4275b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ File f4276c;

        public d(int i2, MediaPlayer mediaPlayer, File file) {
            this.f4274a = i2;
            this.f4275b = mediaPlayer;
            this.f4276c = file;
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            synchronized (W.this) {
                if (W.this.A(this.f4274a) && W.this.f4257d == this.f4275b) {
                    W.this.f4260g = 3;
                    try {
                        mediaPlayer.start();
                        W.this.C();
                        return;
                    } catch (Exception e2) {
                        Log.e("MessageVoicePlayer", "start playback failed", e2);
                        W.this.v(this.f4276c);
                        W.this.H(this.f4274a, "语音播放失败");
                        W.this.s(this.f4274a, "语音播放失败");
                        return;
                    }
                }
                try {
                    mediaPlayer.release();
                } catch (Exception unused) {
                }
            }
        }
    }

    public class e implements MediaPlayer.OnCompletionListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f4278a;

        public e(int i2) {
            this.f4278a = i2;
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            W.this.o(this.f4278a);
        }
    }

    public class f implements MediaPlayer.OnErrorListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ File f4280a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f4281b;

        public f(File file, int i2) {
            this.f4280a = file;
            this.f4281b = i2;
        }

        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
            Log.e("MessageVoicePlayer", "MediaPlayer error what=" + i2 + " extra=" + i3);
            W.this.v(this.f4280a);
            W.this.H(this.f4281b, "语音播放失败");
            W.this.s(this.f4281b, "语音播放失败");
            return true;
        }
    }

    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            W.this.f4255b.a();
        }
    }

    public class h implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4284a;

        public h(String str) {
            this.f4284a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(W.this.f4254a, this.f4284a, 0).show();
        }
    }

    public class i implements Runnable {
        public i() {
        }

        @Override // java.lang.Runnable
        public void run() {
            W.this.N();
        }
    }

    public interface j {
        void a();
    }

    public W(Context context, j jVar) {
        this.f4254a = context == null ? null : context.getApplicationContext();
        this.f4255b = jVar;
    }

    public final boolean A(int i2) {
        boolean z2;
        synchronized (this) {
            z2 = i2 == this.f4262i;
        }
        return z2;
    }

    public final boolean B(File file) {
        File parentFile;
        return (file == null || (parentFile = file.getParentFile()) == null || !"voice_cache".equals(parentFile.getName())) ? false : true;
    }

    public final void C() {
        if (this.f4255b != null) {
            this.f4256c.post(new g());
        }
    }

    public void D(j0.k kVar) {
        int i2;
        File fileG;
        int i3;
        if (kVar == null) {
            return;
        }
        String strG = o0.A.g(kVar.f5555f);
        if (strG == null || strG.length() == 0) {
            J("语音地址无效");
            return;
        }
        synchronized (this) {
            try {
                if (z(kVar) && ((i3 = this.f4260g) == 1 || i3 == 2 || i3 == 3)) {
                    L();
                    C();
                    return;
                }
                K();
                synchronized (this) {
                    try {
                        I(kVar);
                        i2 = this.f4262i + 1;
                        this.f4262i = i2;
                        fileG = G(strG);
                        if (w(fileG)) {
                            this.f4260g = 2;
                            this.f4261h = 100;
                        } else {
                            this.f4260g = 1;
                            this.f4261h = 0;
                        }
                        this.f4264k = null;
                    } finally {
                    }
                }
                C();
                if (!w(fileG)) {
                    new Thread(new b(strG, i2), "voice-player").start();
                    return;
                }
                M(fileG);
                this.f4256c.post(new a(fileG, i2));
                O();
            } finally {
            }
        }
    }

    public final void E(File file, int i2) {
        if (file == null) {
            s(i2, "语音加载失败，请重试");
            return;
        }
        synchronized (this) {
            try {
                if (A(i2)) {
                    F();
                    this.f4263j = file;
                    this.f4260g = 2;
                    C();
                    try {
                        MediaPlayer mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource(file.getAbsolutePath());
                        mediaPlayer.setOnPreparedListener(new d(i2, mediaPlayer, file));
                        mediaPlayer.setOnCompletionListener(new e(i2));
                        mediaPlayer.setOnErrorListener(new f(file, i2));
                        synchronized (this) {
                            try {
                                if (!A(i2)) {
                                    mediaPlayer.release();
                                } else {
                                    this.f4257d = mediaPlayer;
                                    mediaPlayer.prepareAsync();
                                }
                            } finally {
                            }
                        }
                    } catch (Exception e2) {
                        Log.e("MessageVoicePlayer", "prepareLocalFile failed", e2);
                        v(file);
                        H(i2, "语音播放失败");
                        s(i2, "语音播放失败");
                    }
                }
            } finally {
            }
        }
    }

    public final void F() {
        MediaPlayer mediaPlayer = this.f4257d;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
            } catch (Exception unused) {
            }
            this.f4257d.release();
            this.f4257d = null;
        }
        this.f4263j = null;
    }

    public final File G(String str) {
        if (this.f4254a == null || str == null || str.length() == 0) {
            return null;
        }
        File filesDir = this.f4254a.getFilesDir();
        if (filesDir == null) {
            filesDir = this.f4254a.getCacheDir();
        }
        if (filesDir == null) {
            return null;
        }
        File file = new File(filesDir, "voice_cache");
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, n(str) + u(str));
    }

    public final void H(int i2, String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        synchronized (this) {
            try {
                if (A(i2)) {
                    this.f4264k = str;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void I(j0.k kVar) {
        this.f4258e = kVar.f5550a;
        this.f4259f = kVar.f5558i;
    }

    public final void J(String str) {
        if (this.f4254a == null || str == null || str.length() == 0) {
            return;
        }
        this.f4256c.post(new h(str));
    }

    public void K() {
        synchronized (this) {
            L();
        }
        C();
    }

    public final void L() {
        this.f4262i++;
        F();
        this.f4258e = null;
        this.f4259f = 0L;
        this.f4260g = 0;
        this.f4261h = 0;
        this.f4264k = null;
    }

    public final void M(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        try {
            file.setLastModified(System.currentTimeMillis());
        } catch (Exception unused) {
        }
    }

    public final void N() {
        File[] fileArrListFiles;
        Context context = this.f4254a;
        if (context == null) {
            return;
        }
        File filesDir = context.getFilesDir();
        if (filesDir == null) {
            filesDir = this.f4254a.getCacheDir();
        }
        if (filesDir == null) {
            return;
        }
        File file = new File(filesDir, "voice_cache");
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
            if (length <= 125829120 && i2 <= 600) {
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

    public final void O() {
        new Thread(new i(), "voice-cache-trim").start();
    }

    public final void P(int i2, int i3) {
        synchronized (this) {
            try {
                if (A(i2)) {
                    if (i3 < 0) {
                        i3 = 0;
                    }
                    if (i3 > 100) {
                        i3 = 100;
                    }
                    if (this.f4261h == i3 && this.f4260g == 1) {
                        return;
                    }
                    this.f4260g = 1;
                    this.f4261h = i3;
                    C();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final String n(String str) {
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

    public final void o(int i2) {
        synchronized (this) {
            try {
                if (A(i2)) {
                    L();
                    C();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void p(String str, int i2) {
        File fileR;
        try {
            fileR = r(str, i2);
        } catch (Exception e2) {
            Log.e("MessageVoicePlayer", "downloadAndPrepare failed", e2);
            H(i2, "语音加载失败，请重试");
            fileR = null;
        }
        if (fileR == null) {
            s(i2, "语音加载失败，请重试");
        } else {
            this.f4256c.post(new c(fileR, i2));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0304  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x02f1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:262:0x02f8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:291:? A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r3v30 */
    /* JADX WARN: Type inference failed for: r3v32 */
    /* JADX WARN: Type inference failed for: r3v33 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v19 */
    /* JADX WARN: Type inference failed for: r8v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r8v20 */
    /* JADX WARN: Type inference failed for: r8v21 */
    /* JADX WARN: Type inference failed for: r8v22 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.io.File q(java.lang.String r19, int r20, java.io.File r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 800
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.im.oldchat.ui.W.q(java.lang.String, int, java.io.File):java.io.File");
    }

    public final File r(String str, int i2) {
        File fileQ;
        File fileG = G(str);
        if (w(fileG)) {
            M(fileG);
            return fileG;
        }
        String[] strArrI = o0.A.i(str);
        if (strArrI == null || strArrI.length == 0) {
            strArrI = new String[]{str};
        }
        int i3 = 0;
        while (i3 < 3 && A(i2)) {
            for (String str2 : strArrI) {
                if (!A(i2)) {
                    return null;
                }
                if (str2 != null && str2.length() != 0 && (fileQ = q(str2, i2, fileG)) != null) {
                    O();
                    return fileQ;
                }
            }
            if (!A(i2)) {
                return null;
            }
            i3++;
            if (i3 < 3) {
                try {
                    Thread.sleep(((long) i3) * 350);
                } catch (InterruptedException unused) {
                }
            }
        }
        return null;
    }

    public final void s(int i2, String str) {
        synchronized (this) {
            try {
                if (A(i2)) {
                    F();
                    this.f4260g = 4;
                    this.f4261h = 0;
                    String str2 = this.f4264k;
                    if (str2 != null && str2.length() > 0) {
                        str = this.f4264k;
                    }
                    C();
                    J(str);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public String t(j0.k kVar, int i2) {
        synchronized (this) {
            try {
                if (!z(kVar)) {
                    return i2 + "\"";
                }
                int i3 = this.f4260g;
                if (i3 == 1) {
                    if (this.f4261h <= 0) {
                        return "加载中...";
                    }
                    return "加载" + this.f4261h + "%";
                }
                if (i3 == 2) {
                    return "准备中...";
                }
                if (i3 == 4) {
                    return "加载失败";
                }
                return i2 + "\"";
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final String u(String str) {
        if (str == null) {
            return ".cache";
        }
        String lowerCase = str.toLowerCase();
        int iIndexOf = lowerCase.indexOf(63);
        if (iIndexOf >= 0) {
            lowerCase = lowerCase.substring(0, iIndexOf);
        }
        int iIndexOf2 = lowerCase.indexOf(35);
        if (iIndexOf2 >= 0) {
            lowerCase = lowerCase.substring(0, iIndexOf2);
        }
        String[] strArr = {".3gp", ".amr", ".aac", ".mp3", ".m4a", ".wav", ".ogg"};
        for (int i2 = 0; i2 < 7; i2++) {
            if (lowerCase.endsWith(strArr[i2])) {
                return strArr[i2];
            }
        }
        return ".cache";
    }

    public final void v(File file) {
        if (file != null && B(file) && file.exists()) {
            try {
                file.delete();
            } catch (Exception unused) {
            }
        }
    }

    public final boolean w(File file) {
        return file != null && file.exists() && file.length() > 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0012  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean x(j0.k r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r3 = r2.z(r3)     // Catch: java.lang.Throwable -> L10
            if (r3 == 0) goto L12
            int r3 = r2.f4260g     // Catch: java.lang.Throwable -> L10
            r0 = 1
            if (r3 == r0) goto L13
            r1 = 2
            if (r3 != r1) goto L12
            goto L13
        L10:
            r3 = move-exception
            goto L15
        L12:
            r0 = 0
        L13:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L10
            return r0
        L15:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L10
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.im.oldchat.ui.W.x(j0.k):boolean");
    }

    public boolean y(j0.k kVar) {
        boolean z2;
        synchronized (this) {
            try {
                z2 = z(kVar) && this.f4260g == 3;
            } finally {
            }
        }
        return z2;
    }

    public final boolean z(j0.k kVar) {
        String str;
        if (kVar == null) {
            return false;
        }
        String str2 = this.f4258e;
        if (str2 != null && (str = kVar.f5550a) != null) {
            return str2.equals(str);
        }
        if (str2 != null) {
            return false;
        }
        long j2 = this.f4259f;
        return j2 > 0 && kVar.f5558i == j2;
    }
}

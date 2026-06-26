package com.im.oldchat.ui;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import org.spongycastle.i18n.MessageBundle;

/* JADX INFO: loaded from: classes.dex */
public class OldViewVideoFullActivity extends f0.a {

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public String f3636C;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public File f3639F;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public VideoView f3640w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public View f3641x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f3642y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f3643z = 0;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f3634A = 0;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public boolean f3635B = false;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public boolean f3637D = false;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public boolean f3638E = false;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OldViewVideoFullActivity.this.A0();
        }
    }

    public class b implements MediaPlayer.OnPreparedListener {
        public b() {
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            OldViewVideoFullActivity.this.F0(false, null);
            if (OldViewVideoFullActivity.this.f3643z > 0) {
                try {
                    OldViewVideoFullActivity.this.f3640w.seekTo(OldViewVideoFullActivity.this.f3643z);
                } catch (Exception unused) {
                }
            }
            OldViewVideoFullActivity.this.f3643z = 0;
            try {
                OldViewVideoFullActivity.this.f3640w.start();
            } catch (Exception unused2) {
                OldViewVideoFullActivity.this.F0(false, null);
                OldViewVideoFullActivity.this.C0();
            }
        }
    }

    public class c implements MediaPlayer.OnInfoListener {
        public c() {
        }

        @Override // android.media.MediaPlayer.OnInfoListener
        public boolean onInfo(MediaPlayer mediaPlayer, int i2, int i3) {
            if (i2 == 701) {
                OldViewVideoFullActivity.this.F0(true, "缓冲中...");
            } else if (i2 == 702 || i2 == 3) {
                OldViewVideoFullActivity.this.F0(false, null);
            }
            return false;
        }
    }

    public class d implements MediaPlayer.OnCompletionListener {
        public d() {
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            OldViewVideoFullActivity.this.f3635B = true;
            OldViewVideoFullActivity.this.A0();
        }
    }

    public class e implements MediaPlayer.OnErrorListener {
        public e() {
        }

        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
            OldViewVideoFullActivity oldViewVideoFullActivity = OldViewVideoFullActivity.this;
            oldViewVideoFullActivity.f3634A = oldViewVideoFullActivity.E0();
            OldViewVideoFullActivity.this.F0(false, null);
            if (OldViewVideoFullActivity.this.f3637D || OldViewVideoFullActivity.this.f3638E || OldViewVideoFullActivity.this.f3636C == null || !OldViewVideoFullActivity.this.f3636C.startsWith("http")) {
                OldViewVideoFullActivity.this.C0();
                return true;
            }
            OldViewVideoFullActivity.this.f3637D = true;
            OldViewVideoFullActivity.this.G0();
            return true;
        }
    }

    public class f implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f3649a;

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ File f3651a;

            public a(File file) {
                this.f3651a = file;
            }

            @Override // java.lang.Runnable
            public void run() {
                File file = this.f3651a;
                if (file == null || !file.exists()) {
                    OldViewVideoFullActivity.this.F0(false, null);
                    OldViewVideoFullActivity.this.C0();
                    return;
                }
                OldViewVideoFullActivity.this.f3639F = this.f3651a;
                OldViewVideoFullActivity.this.f3638E = true;
                if (OldViewVideoFullActivity.this.f3634A > 0) {
                    OldViewVideoFullActivity oldViewVideoFullActivity = OldViewVideoFullActivity.this;
                    oldViewVideoFullActivity.f3643z = oldViewVideoFullActivity.f3634A;
                }
                OldViewVideoFullActivity.this.D0(Uri.fromFile(this.f3651a), "正在加载本地缓存...");
            }
        }

        public f(String str) {
            this.f3649a = str;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            new Handler(Looper.getMainLooper()).post(new a(OldViewVideoFullActivity.this.z0(this.f3649a)));
        }
    }

    public final void A0() {
        if (this.f3634A <= 0) {
            this.f3634A = E0();
        }
        Intent intent = new Intent();
        intent.putExtra("result_position", this.f3634A);
        intent.putExtra("completed", this.f3635B);
        setResult(-1, intent);
        finish();
    }

    public final String B0(String str) {
        if (str == null) {
            return ".mp4";
        }
        int iIndexOf = str.indexOf(63);
        if (iIndexOf >= 0) {
            str = str.substring(0, iIndexOf);
        }
        int iIndexOf2 = str.indexOf(35);
        if (iIndexOf2 >= 0) {
            str = str.substring(0, iIndexOf2);
        }
        int iLastIndexOf = str.lastIndexOf(46);
        if (iLastIndexOf < 0 || iLastIndexOf >= str.length() - 1) {
            return ".mp4";
        }
        String lowerCase = str.substring(iLastIndexOf).toLowerCase();
        return lowerCase.length() <= 5 ? lowerCase : ".mp4";
    }

    public final void C0() {
        Uri uriFromFile;
        File file;
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            if (!this.f3638E || (file = this.f3639F) == null || !file.exists()) {
                String str = this.f3636C;
                if (str == null || str.length() <= 0) {
                    Toast.makeText(this, "播放失败", 0).show();
                    A0();
                    return;
                }
                uriFromFile = Uri.parse(this.f3636C);
            } else if (Build.VERSION.SDK_INT >= 24) {
                uriFromFile = FileProvider.e(this, getPackageName() + ".fileprovider", this.f3639F);
                intent.addFlags(1);
            } else {
                uriFromFile = Uri.fromFile(this.f3639F);
            }
            intent.setDataAndType(uriFromFile, "video/*");
            intent.addFlags(268435456);
            startActivity(intent);
        } catch (Exception unused) {
            Toast.makeText(this, "播放失败", 0).show();
        }
        A0();
    }

    public final void D0(Uri uri, String str) {
        if (this.f3640w == null || uri == null) {
            return;
        }
        F0(true, str);
        try {
            this.f3640w.stopPlayback();
        } catch (Exception unused) {
        }
        this.f3640w.setVideoURI(uri);
        this.f3640w.requestFocus();
    }

    public final int E0() {
        try {
            VideoView videoView = this.f3640w;
            if (videoView != null) {
                return Math.max(0, videoView.getCurrentPosition());
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    public final void F0(boolean z2, String str) {
        View view = this.f3641x;
        if (view != null) {
            view.setVisibility(z2 ? 0 : 8);
        }
        if (!z2 || this.f3642y == null || str == null || str.length() <= 0) {
            return;
        }
        this.f3642y.setText(str);
    }

    public final void G0() {
        F0(true, "网络波动，尝试缓存播放...");
        new Thread(new f(this.f3636C)).start();
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onBackPressed() {
        A0();
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        getWindow().addFlags(1024);
        super.onCreate(bundle);
        setContentView(f0.g.f5119M);
        this.f3640w = (VideoView) a0(f0.f.Be);
        this.f3641x = a0(f0.f.C6);
        this.f3642y = (TextView) a0(f0.f.Wb);
        TextView textView = (TextView) a0(f0.f.Xb);
        View viewA0 = a0(f0.f.q2);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        Intent intent = getIntent();
        String stringExtra = intent != null ? intent.getStringExtra("url") : null;
        String stringExtra2 = intent != null ? intent.getStringExtra(MessageBundle.TITLE_ENTRY) : null;
        this.f3643z = intent != null ? intent.getIntExtra("position", 0) : 0;
        if (textView != null) {
            if (stringExtra2 == null) {
                stringExtra2 = "";
            }
            textView.setText(stringExtra2);
        }
        if (stringExtra == null || stringExtra.length() == 0) {
            Toast.makeText(this, "无法获取播放地址", 0).show();
            A0();
            return;
        }
        String strG = o0.A.g(stringExtra);
        this.f3636C = strG;
        if (strG == null || strG.length() == 0) {
            Toast.makeText(this, "无法获取播放地址", 0).show();
            A0();
            return;
        }
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(this.f3640w);
        this.f3640w.setMediaController(mediaController);
        y0();
        D0(Uri.parse(this.f3636C), "视频加载中...");
    }

    @Override // o.d, c.AbstractActivityC0167u, android.app.Activity
    public void onDestroy() {
        VideoView videoView = this.f3640w;
        if (videoView != null) {
            try {
                videoView.stopPlayback();
            } catch (Exception unused) {
            }
        }
        File file = this.f3639F;
        if (file != null && file.exists()) {
            this.f3639F.delete();
        }
        super.onDestroy();
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onPause() {
        super.onPause();
        try {
            VideoView videoView = this.f3640w;
            if (videoView != null && videoView.isPlaying()) {
                this.f3640w.pause();
            }
            this.f3634A = E0();
        } catch (Exception unused) {
        }
    }

    public final void y0() {
        VideoView videoView = this.f3640w;
        if (videoView == null) {
            return;
        }
        videoView.setOnPreparedListener(new b());
        if (Build.VERSION.SDK_INT >= 17) {
            this.f3640w.setOnInfoListener(new c());
        }
        this.f3640w.setOnCompletionListener(new d());
        this.f3640w.setOnErrorListener(new e());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13, types: [java.io.FileOutputStream, java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v5, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r9v9 */
    public final File z0(String str) throws Throwable {
        ?? r1;
        ?? r2;
        Throwable th;
        ?? r9;
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        ?? fileOutputStream;
        if (str == null || str.length() == 0) {
            return null;
        }
        File file = new File(getCacheDir(), "video_play_cache");
        if (!file.exists()) {
            file.mkdirs();
        }
        String str2 = "video_" + System.currentTimeMillis() + B0(str);
        File file2 = new File(file, str2);
        try {
            try {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            } catch (Exception unused) {
                httpURLConnection = null;
                inputStream = null;
            } catch (Throwable th2) {
                r1 = 0;
                r2 = 0;
                th = th2;
                r9 = 0;
            }
            try {
                httpURLConnection.setConnectTimeout(8000);
                httpURLConnection.setReadTimeout(20000);
                httpURLConnection.setInstanceFollowRedirects(true);
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() != 200) {
                    httpURLConnection.disconnect();
                    return null;
                }
                inputStream = httpURLConnection.getInputStream();
                try {
                    fileOutputStream = new FileOutputStream(file2);
                    try {
                        byte[] bArr = new byte[PKIFailureInfo.certRevoked];
                        while (true) {
                            int i2 = inputStream.read(bArr);
                            if (i2 == -1) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, i2);
                        }
                        fileOutputStream.flush();
                        httpURLConnection.disconnect();
                        try {
                            inputStream.close();
                        } catch (Exception unused2) {
                        }
                        try {
                            fileOutputStream.close();
                        } catch (Exception unused3) {
                        }
                        return file2;
                    } catch (Exception unused4) {
                    }
                } catch (Exception unused5) {
                    fileOutputStream = 0;
                } catch (Throwable th3) {
                    r2 = 0;
                    th = th3;
                    r1 = inputStream;
                    r9 = httpURLConnection;
                    if (r9 != 0) {
                        r9.disconnect();
                    }
                    if (r1 != 0) {
                        try {
                            r1.close();
                        } catch (Exception unused6) {
                        }
                    }
                    if (r2 == 0) {
                        throw th;
                    }
                    try {
                        r2.close();
                        throw th;
                    } catch (Exception unused7) {
                        throw th;
                    }
                }
            } catch (Exception unused8) {
                inputStream = null;
                httpURLConnection = httpURLConnection;
                fileOutputStream = inputStream;
            } catch (Throwable th4) {
                r2 = 0;
                th = th4;
                r1 = 0;
                r9 = httpURLConnection;
            }
            if (file2.exists()) {
                file2.delete();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception unused9) {
                }
            }
            if (fileOutputStream != 0) {
                try {
                    fileOutputStream.close();
                } catch (Exception unused10) {
                }
            }
            return null;
        } catch (Throwable th5) {
            th = th5;
            r1 = file;
            r2 = str2;
            r9 = str;
        }
    }
}

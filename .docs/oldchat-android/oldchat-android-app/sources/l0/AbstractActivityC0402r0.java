package l0;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import com.im.oldchat.bili.BiliModels;
import com.im.oldchat.bili.a;
import com.im.oldchat.ui.OldViewVideoFullActivity;
import java.util.List;
import org.spongycastle.i18n.MessageBundle;

/* JADX INFO: renamed from: l0.r0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractActivityC0402r0 extends com.im.oldchat.ui.a0 {

    /* JADX INFO: renamed from: l0.r0$a */
    public class a implements a.h {
        public a() {
        }

        @Override // com.im.oldchat.bili.a.h
        public void a(String str) {
            AbstractActivityC0402r0.this.x0(false);
            AbstractActivityC0402r0.this.y0(false, null);
            AbstractActivityC0402r0.this.z0(true);
            AbstractActivityC0402r0.this.w0("网络异常，请重试");
            AbstractActivityC0402r0 abstractActivityC0402r0 = AbstractActivityC0402r0.this;
            StringBuilder sb = new StringBuilder();
            sb.append("PlayUrl error: ");
            sb.append(str != null ? str : "unknown");
            abstractActivityC0402r0.t0(sb.toString());
            AbstractActivityC0402r0 abstractActivityC0402r02 = AbstractActivityC0402r0.this;
            if (str == null) {
                str = "获取播放地址失败";
            }
            Toast.makeText(abstractActivityC0402r02, str, 0).show();
        }

        @Override // com.im.oldchat.bili.a.h
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(BiliModels.PlayUrlResult playUrlResult) {
            String str;
            BiliModels.PlayUrlData playUrlData;
            List<BiliModels.PlayUrlDurl> list;
            String str2;
            AbstractActivityC0402r0.this.x0(false);
            AbstractActivityC0402r0.this.y0(false, null);
            if (playUrlResult != null && playUrlResult.code == 0 && (playUrlData = playUrlResult.data) != null && (list = playUrlData.durl) != null && !list.isEmpty() && (str2 = playUrlResult.data.durl.get(0).url) != null && str2.length() > 0) {
                AbstractActivityC0402r0.this.u0("startPlayback: got url");
                AbstractActivityC0402r0.this.U0(str2);
                return;
            }
            if (playUrlResult == null || (str = playUrlResult.message) == null) {
                str = "无法获取播放地址";
            }
            AbstractActivityC0402r0 abstractActivityC0402r0 = AbstractActivityC0402r0.this;
            StringBuilder sb = new StringBuilder();
            sb.append("PlayUrl empty: code=");
            sb.append(playUrlResult != null ? playUrlResult.code : -1);
            sb.append(" msg=");
            sb.append(str);
            sb.append(" aid=");
            sb.append(AbstractActivityC0402r0.this.f6311b0);
            sb.append(" cid=");
            sb.append(AbstractActivityC0402r0.this.f6313d0);
            abstractActivityC0402r0.t0(sb.toString());
            AbstractActivityC0402r0.this.z0(true);
            AbstractActivityC0402r0.this.w0("播放地址获取失败");
            Toast.makeText(AbstractActivityC0402r0.this, str, 0).show();
        }
    }

    /* JADX INFO: renamed from: l0.r0$b */
    public class b implements a.h {
        public b() {
        }

        @Override // com.im.oldchat.bili.a.h
        public void a(String str) {
            AbstractActivityC0402r0.this.x0(false);
            AbstractActivityC0402r0.this.y0(false, null);
            AbstractActivityC0402r0 abstractActivityC0402r0 = AbstractActivityC0402r0.this;
            if (str == null) {
                str = "获取播放地址失败";
            }
            Toast.makeText(abstractActivityC0402r0, str, 0).show();
        }

        @Override // com.im.oldchat.bili.a.h
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(BiliModels.PlayUrlResult playUrlResult) {
            String str;
            BiliModels.PlayUrlData playUrlData;
            List<BiliModels.PlayUrlDurl> list;
            String str2;
            AbstractActivityC0402r0.this.x0(false);
            AbstractActivityC0402r0.this.y0(false, null);
            if (playUrlResult != null && playUrlResult.code == 0 && (playUrlData = playUrlResult.data) != null && (list = playUrlData.durl) != null && !list.isEmpty() && (str2 = playUrlResult.data.durl.get(0).url) != null && str2.length() > 0) {
                AbstractActivityC0402r0.this.Q0(str2);
                return;
            }
            if (playUrlResult == null || (str = playUrlResult.message) == null) {
                str = "无法获取播放地址";
            }
            Toast.makeText(AbstractActivityC0402r0.this, str, 0).show();
        }
    }

    /* JADX INFO: renamed from: l0.r0$c */
    public class c implements MediaPlayer.OnPreparedListener {

        /* JADX INFO: renamed from: l0.r0$c$a */
        public class a implements MediaPlayer.OnVideoSizeChangedListener {
            public a() {
            }

            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i2, int i3) {
                AbstractActivityC0402r0.this.O0(i2, i3);
            }
        }

        public c() {
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            AbstractActivityC0402r0.this.u0("playUrl: prepared");
            AbstractActivityC0402r0.this.y0(false, null);
            AbstractActivityC0402r0.this.w0("正在播放");
            AbstractActivityC0402r0.this.z0(false);
            mediaPlayer.setLooping(false);
            AbstractActivityC0402r0.this.O0(mediaPlayer.getVideoWidth(), mediaPlayer.getVideoHeight());
            mediaPlayer.setOnVideoSizeChangedListener(new a());
            AbstractActivityC0402r0 abstractActivityC0402r0 = AbstractActivityC0402r0.this;
            int i2 = abstractActivityC0402r0.f6320k0;
            if (i2 > 0) {
                abstractActivityC0402r0.f6289F.seekTo(i2);
            }
            if (!AbstractActivityC0402r0.this.P0()) {
                AbstractActivityC0402r0 abstractActivityC0402r02 = AbstractActivityC0402r0.this;
                abstractActivityC0402r02.f6317h0 = false;
                abstractActivityC0402r02.f6319j0 = true;
                return;
            }
            try {
                AbstractActivityC0402r0.this.f6289F.start();
                AbstractActivityC0402r0 abstractActivityC0402r03 = AbstractActivityC0402r0.this;
                abstractActivityC0402r03.f6317h0 = true;
                abstractActivityC0402r03.f6319j0 = false;
            } catch (Exception unused) {
                AbstractActivityC0402r0 abstractActivityC0402r04 = AbstractActivityC0402r0.this;
                abstractActivityC0402r04.f6317h0 = false;
                abstractActivityC0402r04.f6319j0 = true;
            }
        }
    }

    /* JADX INFO: renamed from: l0.r0$d */
    public class d implements MediaPlayer.OnInfoListener {
        public d() {
        }

        @Override // android.media.MediaPlayer.OnInfoListener
        public boolean onInfo(MediaPlayer mediaPlayer, int i2, int i3) {
            if (i2 == 701) {
                AbstractActivityC0402r0.this.y0(true, "缓冲中...");
            } else if (i2 == 702 || i2 == 3) {
                AbstractActivityC0402r0.this.y0(false, null);
            }
            return false;
        }
    }

    /* JADX INFO: renamed from: l0.r0$e */
    public class e implements MediaPlayer.OnCompletionListener {
        public e() {
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            AbstractActivityC0402r0.this.u0("playUrl: completed");
            AbstractActivityC0402r0.this.y0(false, null);
            AbstractActivityC0402r0 abstractActivityC0402r0 = AbstractActivityC0402r0.this;
            abstractActivityC0402r0.f6320k0 = 0;
            abstractActivityC0402r0.f6318i0 = false;
            abstractActivityC0402r0.f6319j0 = false;
            abstractActivityC0402r0.Z0(true);
        }
    }

    /* JADX INFO: renamed from: l0.r0$f */
    public class f implements MediaPlayer.OnErrorListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6274a;

        public f(String str) {
            this.f6274a = str;
        }

        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
            AbstractActivityC0402r0 abstractActivityC0402r0 = AbstractActivityC0402r0.this;
            StringBuilder sb = new StringBuilder();
            sb.append("playUrl error what=");
            sb.append(i2);
            sb.append(" extra=");
            sb.append(i3);
            sb.append(" retry=");
            sb.append(this.f6274a != null);
            abstractActivityC0402r0.t0(sb.toString());
            String str = this.f6274a;
            if (str != null && str.length() > 0) {
                AbstractActivityC0402r0.this.y0(true, "正在切换兼容线路...");
                AbstractActivityC0402r0.this.V0(this.f6274a, false);
                return true;
            }
            AbstractActivityC0402r0 abstractActivityC0402r02 = AbstractActivityC0402r0.this;
            abstractActivityC0402r02.f6320k0 = 0;
            abstractActivityC0402r02.f6318i0 = false;
            abstractActivityC0402r02.f6319j0 = false;
            abstractActivityC0402r02.y0(false, null);
            AbstractActivityC0402r0.this.Z0(true);
            AbstractActivityC0402r0.this.z0(true);
            AbstractActivityC0402r0.this.w0("播放失败，点击重试");
            Toast.makeText(AbstractActivityC0402r0.this, "播放失败", 0).show();
            return true;
        }
    }

    /* JADX INFO: renamed from: l0.r0$g */
    public class g implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f6276a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f6277b;

        public g(int i2, int i3) {
            this.f6276a = i2;
            this.f6277b = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractActivityC0402r0.this.O0(this.f6276a, this.f6277b);
        }
    }

    public void O0(int i2, int i3) {
        View view = this.f6289F;
        if (view == null || i2 <= 0 || i3 <= 0) {
            return;
        }
        this.t0 = i2;
        this.u0 = i3;
        View view2 = this.f6287D;
        if (view2 != null) {
            view = view2;
        }
        int width = view.getWidth();
        int height = view.getHeight();
        if (width <= 0 || height <= 0) {
            view.post(new g(i2, i3));
            return;
        }
        float f2 = i2 / i3;
        float f3 = width;
        float f4 = height;
        if (f2 > f3 / f4) {
            height = (int) (f3 / f2);
        } else {
            width = (int) (f4 * f2);
        }
        if (width <= 0 || height <= 0) {
            return;
        }
        this.f6289F.setLayoutParams(new FrameLayout.LayoutParams(width, height, 17));
        try {
            this.f6289F.getHolder().setFixedSize(i2, i3);
        } catch (Exception unused) {
        }
    }

    public boolean P0() {
        View view = this.f6287D;
        if (view == null || this.f6326x == null || view.getHeight() <= 0 || this.f6326x.getHeight() <= 0) {
            return true;
        }
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        this.f6326x.getLocationOnScreen(iArr);
        this.f6287D.getLocationOnScreen(iArr2);
        int i2 = iArr[1];
        int height = this.f6326x.getHeight() + i2;
        int i3 = iArr2[1];
        return this.f6287D.getHeight() + i3 > i2 && i3 < height;
    }

    public void Q0(String str) {
        if (str == null || str.length() == 0) {
            Toast.makeText(this, "无法获取播放地址", 0).show();
            return;
        }
        String strG = o0.A.g(str);
        this.p0 = strG;
        if (strG == null || strG.length() == 0) {
            Toast.makeText(this, "无法获取播放地址", 0).show();
            return;
        }
        int iX0 = X0();
        this.f6321l0 = true;
        S0();
        Intent intent = new Intent(this, (Class<?>) OldViewVideoFullActivity.class);
        intent.putExtra("url", this.p0);
        intent.putExtra("position", iX0);
        intent.putExtra(MessageBundle.TITLE_ENTRY, k0());
        startActivityForResult(intent, 9101);
    }

    public void R0() {
        String str = this.p0;
        if (str != null && str.length() > 0) {
            Q0(this.p0);
            return;
        }
        String str2 = this.f6322m0;
        if (str2 != null && str2.length() > 0) {
            Q0(this.f6322m0);
            return;
        }
        if (this.f6311b0 <= 0 || this.f6313d0 <= 0) {
            Toast.makeText(this, "视频信息不完整", 0).show();
            return;
        }
        x0(true);
        y0(true, "正在获取播放地址...");
        com.im.oldchat.bili.d.y(this.f6312c0, this.f6311b0, this.f6313d0, 16, h0.a.c(this), new b());
    }

    public void S0() {
        if (this.f6289F == null) {
            return;
        }
        this.f6320k0 = X0();
        this.f6318i0 = true;
        this.f6319j0 = true;
        this.f6317h0 = false;
        try {
            if (this.f6289F.isPlaying()) {
                this.f6289F.pause();
            }
        } catch (Exception unused) {
        }
    }

    public void T0() {
        VideoView videoView = this.f6289F;
        if (videoView == null || !this.f6318i0) {
            return;
        }
        try {
            if (videoView.isPlaying()) {
                this.f6320k0 = X0();
                this.f6319j0 = true;
                this.f6317h0 = false;
                this.f6289F.pause();
            }
        } catch (Exception unused) {
        }
    }

    public void U0(String str) {
        V0(str, true);
    }

    public final void V0(String str, boolean z2) {
        String str2;
        String str3;
        if (this.f6289F == null) {
            return;
        }
        String strG = o0.A.g(str);
        if (strG == null || strG.length() == 0) {
            z0(true);
            w0("播放地址无效");
            Toast.makeText(this, "无法获取播放地址", 0).show();
            return;
        }
        if (z2 && strG.startsWith("http://")) {
            str3 = "https://" + strG.substring(7);
            str2 = strG;
        } else {
            str2 = null;
            str3 = strG;
        }
        this.p0 = strG;
        this.f6318i0 = true;
        this.f6317h0 = true;
        this.f6319j0 = false;
        u0("playUrl: start target=" + str3);
        z0(false);
        y0(true, "视频加载中...");
        ImageView imageView = this.f6288E;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        View view = this.f6290G;
        if (view != null) {
            view.setVisibility(8);
        }
        this.f6289F.setVisibility(0);
        try {
            this.f6289F.stopPlayback();
        } catch (Exception unused) {
        }
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(this.f6289F);
        this.f6289F.setMediaController(mediaController);
        this.f6289F.setVideoPath(str3);
        this.f6289F.setOnPreparedListener(new c());
        if (Build.VERSION.SDK_INT >= 17) {
            this.f6289F.setOnInfoListener(new d());
        }
        this.f6289F.setOnCompletionListener(new e());
        this.f6289F.setOnErrorListener(new f(str2));
        this.f6289F.requestFocus();
    }

    public void W0() {
        if (this.f6289F != null && this.f6319j0 && this.f6318i0 && P0()) {
            try {
                int i2 = this.f6320k0;
                if (i2 > 0) {
                    this.f6289F.seekTo(i2);
                }
                this.f6289F.start();
                this.f6317h0 = true;
                this.f6319j0 = false;
                y0(false, null);
            } catch (Exception unused) {
                this.f6317h0 = false;
                this.f6319j0 = true;
            }
        }
    }

    public int X0() {
        try {
            VideoView videoView = this.f6289F;
            return Math.max(0, videoView != null ? videoView.getCurrentPosition() : 0);
        } catch (Exception unused) {
            return 0;
        }
    }

    public void Y0() {
        if (this.f6318i0 && this.f6319j0) {
            W0();
            return;
        }
        if (this.f6317h0) {
            return;
        }
        z0(false);
        w0("准备播放...");
        this.f6320k0 = 0;
        String str = this.f6322m0;
        if (str != null && str.length() > 0) {
            u0("startPlayback: use preload url");
            U0(this.f6322m0);
            return;
        }
        if (this.f6311b0 <= 0 || this.f6313d0 <= 0) {
            Toast.makeText(this, "视频信息不完整", 0).show();
            z0(true);
            return;
        }
        x0(true);
        y0(true, "正在获取播放地址...");
        StringBuilder sb = new StringBuilder();
        sb.append("startPlayback: aid=");
        sb.append(this.f6311b0);
        sb.append(" cid=");
        sb.append(this.f6313d0);
        sb.append(" bvid=");
        String str2 = this.f6312c0;
        if (str2 == null) {
            str2 = "";
        }
        sb.append(str2);
        u0(sb.toString());
        com.im.oldchat.bili.d.y(this.f6312c0, this.f6311b0, this.f6313d0, 16, h0.a.c(this), new a());
    }

    public void Z0(boolean z2) {
        VideoView videoView = this.f6289F;
        if (videoView == null) {
            return;
        }
        try {
            try {
                videoView.stopPlayback();
            } catch (Exception unused) {
            }
        } catch (Exception unused2) {
            if (this.f6289F.isPlaying()) {
                this.f6289F.pause();
            }
        }
        this.f6317h0 = false;
        this.f6318i0 = false;
        this.f6319j0 = false;
        this.f6320k0 = 0;
        y0(false, null);
        if (z2) {
            ImageView imageView = this.f6288E;
            if (imageView != null) {
                imageView.setVisibility(0);
            }
            View view = this.f6290G;
            if (view != null) {
                view.setVisibility(0);
            }
            z0(false);
            w0("点击播放");
            this.f6289F.setVisibility(8);
        }
    }

    public void a1() {
        if (this.f6289F == null || this.f6287D == null || this.f6326x == null || !this.f6318i0) {
            return;
        }
        if (!P0()) {
            T0();
        } else if (this.f6319j0) {
            W0();
        }
    }
}

package com.im.oldchat.ui;

import android.R;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.service.MusicPlaybackService;
import g0.d;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import k.AbstractC0285k0;
import o0.AbstractC0446l;
import o0.AbstractC0455v;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class MusicPlayerActivity extends f0.a {

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public static final Pattern f3261j0 = Pattern.compile("\\[(\\d{1,2}):(\\d{1,2})(?:\\.(\\d{1,3}))?\\]");

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public static final LinkedHashMap f3262k0 = new e(24, 0.75f, true);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public TextView f3263A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public ImageView f3264B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public ImageView f3265C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public ImageView f3266D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public ImageView f3267E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public TextView f3268F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public SeekBar f3269G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public TextView f3270H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public TextView f3271I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public TextView f3272J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public boolean f3273K;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public int f3284V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public int f3285W;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public boolean f3286X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public boolean f3287Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public boolean f3288Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public boolean f3289a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public boolean f3290b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public int f3291c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public int f3292d0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ImageView f3298w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public TextView f3299x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f3300y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public TextView f3301z;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public String f3274L = "";

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public String f3275M = "";

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public String f3276N = "";

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public String f3277O = "";

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public String f3278P = "";

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public String f3279Q = "";

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public String f3280R = "";

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public String f3281S = "";

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public String f3282T = "";

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public String f3283U = "";

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public final List f3293e0 = new ArrayList();

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public final List f3294f0 = new ArrayList();

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public int f3295g0 = -1;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public String f3296h0 = "";

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public final BroadcastReceiver f3297i0 = new f();

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicPlayerActivity.this.G0();
        }
    }

    public class b implements SeekBar.OnSeekBarChangeListener {
        public b() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i2, boolean z2) {
            if (z2) {
                MusicPlayerActivity.this.f3301z.setText(MusicPlayerActivity.this.D0(i2));
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            MusicPlayerActivity.this.f3273K = true;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            MusicPlayerActivity.this.f3273K = false;
            Intent intent = new Intent(MusicPlayerActivity.this, (Class<?>) MusicPlaybackService.class);
            intent.setAction("com.im.oldchat.action.music.SEEK");
            intent.putExtra("position_ms", seekBar.getProgress());
            MusicPlayerActivity.this.K0(intent);
        }
    }

    public class c implements AbstractC0446l.c {
        public c() {
        }

        @Override // o0.AbstractC0446l.c
        public void a(boolean z2, String str, File file) {
            Toast.makeText(MusicPlayerActivity.this, str, 0).show();
        }
    }

    public class d implements d.i {
        public d() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                MusicPlayerActivity musicPlayerActivity = MusicPlayerActivity.this;
                musicPlayerActivity.f3286X = jSONObject.optBoolean("liked", musicPlayerActivity.f3286X);
                MusicPlayerActivity musicPlayerActivity2 = MusicPlayerActivity.this;
                musicPlayerActivity2.f3284V = Math.max(0, jSONObject.optInt("likes", musicPlayerActivity2.f3284V));
            } catch (Exception unused) {
                MusicPlayerActivity.this.f3286X = !r5.f3286X;
                if (MusicPlayerActivity.this.f3286X) {
                    MusicPlayerActivity.this.f3284V++;
                } else if (MusicPlayerActivity.this.f3284V > 0) {
                    MusicPlayerActivity musicPlayerActivity3 = MusicPlayerActivity.this;
                    musicPlayerActivity3.f3284V--;
                }
            }
            MusicPlayerActivity.this.F0();
            MusicPlayerActivity.this.f3287Y = false;
            MusicPlayerActivity.this.O0();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            MusicPlayerActivity.this.f3287Y = false;
            MusicPlayerActivity.this.O0();
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(MusicPlayerActivity.this, "点赞失败", 0).show();
        }
    }

    public class e extends LinkedHashMap {
        public e(int i2, float f2, boolean z2) {
            super(i2, f2, z2);
        }

        @Override // java.util.LinkedHashMap
        public boolean removeEldestEntry(Map.Entry entry) {
            return size() > 18;
        }
    }

    public class f extends BroadcastReceiver {
        public f() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null && "com.im.oldchat.action.music.STATE_CHANGED".equals(intent.getAction())) {
                MusicPlayerActivity.this.x0(intent);
            }
        }
    }

    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicPlayerActivity.this.finish();
        }
    }

    public class h implements View.OnClickListener {
        public h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicPlayerActivity.this.J0("com.im.oldchat.action.music.TOGGLE_PLAY");
        }
    }

    public class i implements View.OnClickListener {
        public i() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent(MusicPlayerActivity.this, (Class<?>) MusicPlaybackService.class);
            intent.setAction("com.im.oldchat.action.music.SEEK_RELATIVE");
            intent.putExtra("seek_delta_ms", 10000);
            MusicPlayerActivity.this.K0(intent);
        }
    }

    public class j implements View.OnClickListener {
        public j() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicPlayerActivity.this.J0("com.im.oldchat.action.music.TOGGLE_REPEAT_ONE");
        }
    }

    public class k implements View.OnClickListener {
        public k() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicPlayerActivity.this.J0("com.im.oldchat.action.music.STOP");
            MusicPlayerActivity.this.finish();
        }
    }

    public class l implements View.OnClickListener {
        public l() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicPlayerActivity.this.C0();
        }
    }

    public class m implements View.OnClickListener {
        public m() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicPlayerActivity.this.M0();
        }
    }

    private void B0() {
        this.f3298w = (ImageView) a0(f0.f.M5);
        this.f3299x = (TextView) a0(f0.f.bb);
        this.f3300y = (TextView) a0(f0.f.ab);
        this.f3301z = (TextView) a0(f0.f.Ya);
        this.f3263A = (TextView) a0(f0.f.Za);
        this.f3264B = (ImageView) a0(f0.f.O1);
        this.f3265C = (ImageView) a0(f0.f.Q1);
        this.f3266D = (ImageView) a0(f0.f.M1);
        this.f3267E = (ImageView) a0(f0.f.P1);
        this.f3268F = (TextView) a0(f0.f.cb);
        this.f3269G = (SeekBar) a0(f0.f.h8);
        this.f3270H = (TextView) a0(f0.f.L1);
        this.f3271I = (TextView) a0(f0.f.N1);
        this.f3272J = (TextView) a0(f0.f.K1);
        v0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String D0(int i2) {
        if (i2 <= 0) {
            return "00:00";
        }
        int i3 = i2 / 1000;
        return String.format(Locale.getDefault(), "%02d:%02d", Integer.valueOf(i3 / 60), Integer.valueOf(i3 % 60));
    }

    private void z0() {
        ImageView imageView = this.f3264B;
        if (imageView != null) {
            imageView.setOnClickListener(new h());
        }
        ImageView imageView2 = this.f3266D;
        if (imageView2 != null) {
            imageView2.setOnClickListener(new i());
        }
        ImageView imageView3 = this.f3267E;
        if (imageView3 != null) {
            imageView3.setOnClickListener(new j());
        }
        ImageView imageView4 = this.f3265C;
        if (imageView4 != null) {
            imageView4.setOnClickListener(new k());
        }
        TextView textView = this.f3270H;
        if (textView != null) {
            textView.setOnClickListener(new l());
        }
        TextView textView2 = this.f3271I;
        if (textView2 != null) {
            textView2.setOnClickListener(new m());
        }
        TextView textView3 = this.f3272J;
        if (textView3 != null) {
            textView3.setOnClickListener(new a());
        }
        SeekBar seekBar = this.f3269G;
        if (seekBar == null) {
            return;
        }
        seekBar.setOnSeekBarChangeListener(new b());
    }

    public final void A0() {
        View viewA0 = a0(f0.f.J1);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new g());
            if (viewA0 instanceof ImageView) {
                w0((ImageView) viewA0);
            }
        }
        String str = this.f3274L;
        if (str.length() == 0) {
            str = "未命名歌曲";
        }
        this.f3299x.setText(str);
        String str2 = this.f3278P;
        if (str2.length() == 0) {
            str2 = this.f3279Q;
        }
        if (str2.length() == 0) {
            str2 = "匿名用户";
        }
        if (TextUtils.isEmpty(this.f3280R)) {
            this.f3300y.setText(str2);
        } else {
            this.f3300y.setText(str2 + " · " + this.f3280R);
        }
        if (this.f3276N.length() > 0) {
            AbstractC0455v.e(this.f3298w, this.f3276N);
        } else if (this.f3281S.length() > 0) {
            AbstractC0455v.e(this.f3298w, this.f3281S);
        } else {
            this.f3298w.setImageResource(f0.e.f4999T);
        }
        this.f3301z.setText(D0(0));
        this.f3263A.setText(D0(0));
        this.f3268F.setText("准备播放");
        ImageView imageView = this.f3264B;
        if (imageView != null) {
            imageView.setImageResource(R.drawable.ic_media_play);
        }
        N0();
        O0();
    }

    public final void C0() {
        String str = this.f3275M;
        if (str == null || str.length() == 0) {
            Toast.makeText(this, "歌曲链接不可用", 0).show();
            return;
        }
        String strG = o0.A.g(this.f3275M);
        if (strG == null || strG.length() == 0) {
            Toast.makeText(this, "歌曲链接不可用", 0).show();
        } else {
            Toast.makeText(this, "开始下载到本地", 0).show();
            AbstractC0446l.j(this, strG, "oldchat_music_", ".mp3", new c());
        }
    }

    public final void E0() {
        this.f3283U = getSharedPreferences("auth", 0).getString("access_token", "");
    }

    public final void F0() {
        String str = this.f3282T;
        if (str == null || str.length() == 0) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) MusicPlaybackService.class);
        intent.setAction("com.im.oldchat.action.music.UPDATE_ITEM_META");
        intent.putExtra("item_id", this.f3282T);
        intent.putExtra("item_likes", Math.max(0, this.f3284V));
        intent.putExtra("item_comments", Math.max(0, this.f3285W));
        intent.putExtra("item_liked", this.f3286X);
        K0(intent);
    }

    public final void G0() {
        String str = this.f3282T;
        if (str == null || str.length() == 0) {
            Toast.makeText(this, "当前歌曲暂无评论入口", 0).show();
            return;
        }
        Intent intent = new Intent(this, (Class<?>) MusicCommentsActivity.class);
        intent.putExtra("item_id", this.f3282T);
        String str2 = this.f3279Q;
        if (str2 == null) {
            str2 = "";
        }
        intent.putExtra("owner_uid", str2);
        startActivity(intent);
    }

    public final void H0(Intent intent) {
        if (intent == null) {
            return;
        }
        this.f3274L = P0(intent.getStringExtra("song_name"));
        this.f3275M = P0(intent.getStringExtra("song_url"));
        this.f3276N = P0(intent.getStringExtra("cover_url"));
        this.f3277O = "";
        this.f3279Q = P0(intent.getStringExtra("owner_uid"));
        this.f3278P = P0(intent.getStringExtra("owner_name"));
        this.f3280R = P0(intent.getStringExtra("owner_title"));
        this.f3281S = P0(intent.getStringExtra("owner_avatar"));
        if (intent.hasExtra("item_id")) {
            this.f3282T = P0(intent.getStringExtra("item_id"));
        }
        if (intent.hasExtra("item_likes")) {
            this.f3284V = Math.max(0, intent.getIntExtra("item_likes", this.f3284V));
        }
        if (intent.hasExtra("item_comments")) {
            this.f3285W = Math.max(0, intent.getIntExtra("item_comments", this.f3285W));
        }
        if (intent.hasExtra("item_liked")) {
            this.f3286X = intent.getBooleanExtra("item_liked", this.f3286X);
        }
    }

    public final void I0() {
        Intent intent = new Intent(this, (Class<?>) MusicPlaybackService.class);
        intent.setAction("com.im.oldchat.action.music.REQUEST_STATE");
        K0(intent);
    }

    public final void J0(String str) {
        Intent intent = new Intent(this, (Class<?>) MusicPlaybackService.class);
        intent.setAction(str);
        K0(intent);
    }

    public final void K0(Intent intent) {
        if (intent == null) {
            return;
        }
        boolean zEquals = "com.im.oldchat.action.music.PLAY_SONG".equals(P0(intent.getAction()));
        try {
            try {
                if (Build.VERSION.SDK_INT < 26 || !zEquals) {
                    startService(intent);
                } else {
                    startForegroundService(intent);
                }
            } catch (Exception unused) {
                startService(intent);
            }
        } catch (Exception unused2) {
        }
    }

    public final void L0() {
        String strG = o0.A.g(this.f3275M);
        if (strG.length() == 0) {
            Toast.makeText(this, "歌曲链接不可用", 0).show();
            return;
        }
        Intent intent = new Intent(this, (Class<?>) MusicPlaybackService.class);
        intent.setAction("com.im.oldchat.action.music.PLAY_SONG");
        intent.putExtra("song_name", this.f3274L);
        intent.putExtra("song_url", strG);
        intent.putExtra("cover_url", this.f3276N);
        intent.putExtra("owner_uid", this.f3279Q);
        intent.putExtra("owner_name", this.f3278P);
        intent.putExtra("owner_title", this.f3280R);
        intent.putExtra("owner_avatar", this.f3281S);
        intent.putExtra("item_id", this.f3282T);
        intent.putExtra("item_likes", this.f3284V);
        intent.putExtra("item_comments", this.f3285W);
        intent.putExtra("item_liked", this.f3286X);
        K0(intent);
    }

    public final void M0() {
        if (this.f3287Y) {
            return;
        }
        String str = this.f3282T;
        if (str == null || str.length() == 0) {
            Toast.makeText(this, "当前歌曲暂无点赞入口", 0).show();
            return;
        }
        String str2 = this.f3283U;
        if (str2 == null || str2.length() == 0) {
            E0();
        }
        String str3 = this.f3283U;
        if (str3 == null || str3.length() == 0) {
            Toast.makeText(this, "请先登录", 0).show();
            return;
        }
        this.f3287Y = true;
        O0();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("item_id", this.f3282T);
            g0.d.S(this.f3286X ? "/music/plaza/unlike" : "/music/plaza/like", jSONObject, this.f3283U, new d());
        } catch (Exception unused) {
            this.f3287Y = false;
            O0();
            Toast.makeText(this, "点赞失败", 0).show();
        }
    }

    public final void N0() {
        ImageView imageView = this.f3267E;
        if (imageView == null) {
            return;
        }
        AbstractC0285k0.K(imageView, this.f3290b0 ? 1.0f : 0.55f);
    }

    public final void O0() {
        String str = this.f3282T;
        boolean z2 = str != null && str.length() > 0;
        TextView textView = this.f3271I;
        if (textView != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f3286X ? "已赞 " : "点赞 ");
            sb.append(Math.max(0, this.f3284V));
            textView.setText(sb.toString());
            this.f3271I.setEnabled(z2 && !this.f3287Y);
            this.f3271I.setBackgroundResource(this.f3286X ? f0.e.f5027n : f0.e.f5026m);
            this.f3271I.setTextColor(getResources().getColor(this.f3286X ? f0.d.f4970g : f0.d.f4973j));
            AbstractC0285k0.K(this.f3271I, z2 ? 1.0f : 0.5f);
        }
        TextView textView2 = this.f3272J;
        if (textView2 != null) {
            textView2.setText("评论 " + Math.max(0, this.f3285W));
            this.f3272J.setEnabled(z2);
            AbstractC0285k0.K(this.f3272J, z2 ? 1.0f : 0.5f);
        }
    }

    public final String P0(String str) {
        return str == null ? "" : str;
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5113G);
        B0();
        E0();
        H0(getIntent());
        A0();
        z0();
        L0();
    }

    @Override // o.d, c.AbstractActivityC0167u, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent == null) {
            return;
        }
        setIntent(intent);
        H0(intent);
        A0();
        L0();
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onResume() {
        super.onResume();
        E0();
        O0();
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, android.app.Activity
    public void onStart() {
        super.onStart();
        d.q.c(this).d(this.f3297i0, new IntentFilter("com.im.oldchat.action.music.STATE_CHANGED"));
        I0();
    }

    @Override // o.d, c.AbstractActivityC0167u, android.app.Activity
    public void onStop() {
        d.q.c(this).f(this.f3297i0);
        super.onStop();
    }

    public final void v0() {
        y0(this.f3264B);
        w0(this.f3265C);
        w0(this.f3266D);
        w0(this.f3267E);
    }

    public final void w0(ImageView imageView) {
        if (imageView == null) {
            return;
        }
        imageView.setColorFilter(getResources().getColor(f0.d.f4973j));
    }

    public final void x0(Intent intent) {
        String strP0 = P0(intent.getStringExtra("song_url"));
        if (strP0.length() > 0 && !TextUtils.equals(strP0, this.f3275M)) {
            this.f3275M = strP0;
            this.f3274L = P0(intent.getStringExtra("song_name"));
            this.f3276N = P0(intent.getStringExtra("cover_url"));
            this.f3279Q = P0(intent.getStringExtra("owner_uid"));
            this.f3278P = P0(intent.getStringExtra("owner_name"));
            this.f3280R = P0(intent.getStringExtra("owner_title"));
            this.f3281S = P0(intent.getStringExtra("owner_avatar"));
            if (P0(intent.getStringExtra("item_id")).length() == 0) {
                this.f3282T = "";
                this.f3284V = 0;
                this.f3285W = 0;
                this.f3286X = false;
            }
            A0();
        }
        this.f3288Z = intent.getBooleanExtra("is_preparing", false);
        this.f3289a0 = intent.getBooleanExtra("is_playing", false);
        this.f3290b0 = intent.getBooleanExtra("repeat_one", false);
        this.f3291c0 = Math.max(0, intent.getIntExtra("duration_ms", 0));
        this.f3292d0 = Math.max(0, intent.getIntExtra("position_ms", 0));
        String strP02 = P0(intent.getStringExtra("item_id"));
        if (strP02.length() > 0 && !TextUtils.equals(strP02, this.f3282T)) {
            this.f3282T = strP02;
            this.f3284V = Math.max(0, intent.getIntExtra("item_likes", this.f3284V));
            this.f3285W = Math.max(0, intent.getIntExtra("item_comments", this.f3285W));
            this.f3286X = intent.getBooleanExtra("item_liked", this.f3286X);
        }
        String strP03 = P0(intent.getStringExtra("error"));
        N0();
        O0();
        if (!this.f3273K) {
            this.f3269G.setMax(Math.max(this.f3291c0, 1));
            this.f3269G.setProgress(Math.min(this.f3292d0, Math.max(this.f3291c0, 0)));
        }
        this.f3301z.setText(D0(this.f3292d0));
        this.f3263A.setText(D0(this.f3291c0));
        if (this.f3288Z) {
            this.f3268F.setText("正在加载（支持后台播放）");
            ImageView imageView = this.f3264B;
            if (imageView != null) {
                imageView.setImageResource(R.drawable.ic_popup_sync);
                this.f3264B.setEnabled(false);
                return;
            }
            return;
        }
        ImageView imageView2 = this.f3264B;
        if (imageView2 != null) {
            imageView2.setEnabled(true);
        }
        if (strP03.length() > 0) {
            this.f3268F.setText(strP03);
            ImageView imageView3 = this.f3264B;
            if (imageView3 != null) {
                imageView3.setImageResource(R.drawable.ic_media_play);
                return;
            }
            return;
        }
        if (this.f3289a0) {
            this.f3268F.setText("正在播放（后台持续）");
            ImageView imageView4 = this.f3264B;
            if (imageView4 != null) {
                imageView4.setImageResource(R.drawable.ic_media_pause);
                return;
            }
            return;
        }
        int i2 = this.f3291c0;
        if (i2 > 0 && this.f3292d0 >= i2 - 400) {
            this.f3268F.setText("播放完成");
            ImageView imageView5 = this.f3264B;
            if (imageView5 != null) {
                imageView5.setImageResource(R.drawable.ic_media_play);
                return;
            }
            return;
        }
        if (i2 > 0 || this.f3292d0 > 0) {
            this.f3268F.setText("已暂停（可在通知栏继续）");
        } else {
            this.f3268F.setText("准备播放");
        }
        ImageView imageView6 = this.f3264B;
        if (imageView6 != null) {
            imageView6.setImageResource(R.drawable.ic_media_play);
        }
    }

    public final void y0(ImageView imageView) {
        if (imageView == null) {
            return;
        }
        imageView.setColorFilter(getResources().getColor(f0.d.f4970g));
    }
}

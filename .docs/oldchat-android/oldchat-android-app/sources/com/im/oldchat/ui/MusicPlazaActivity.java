package com.im.oldchat.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import c.AbstractC0121a;
import com.im.oldchat.service.MusicPlaybackService;
import com.im.oldchat.ui.Y;
import d.AbstractC0235a;
import g0.d;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import k.AbstractC0285k0;
import o.c;
import o0.AbstractC0446l;
import o0.AbstractC0455v;
import org.json.JSONArray;
import org.json.JSONObject;
import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: loaded from: classes.dex */
public class MusicPlazaActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public TextView f3314A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public TextView f3315B;
    public int B0;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public TextView f3316C;
    public int C0;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public TextView f3317D;
    public boolean D0;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public View f3318E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public ImageView f3319F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public TextView f3320G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public TextView f3321H;
    public Runnable H0;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public TextView f3322I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public TextView f3323J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public TextView f3324K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public LinearLayout f3325L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public LinearLayout f3326M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public LinearLayout f3327N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public TextView f3328O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public TextView f3329P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public TextView f3330Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public TextView f3331R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public TextView f3332S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public TextView f3333T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public TextView f3334U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public ListView f3335V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public String f3336W;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public String f3337X;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public Q f3341b0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public Uri f3346g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public Uri f3347h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public String f3348i0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public o.c f3351l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public TextView f3352m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public TextView f3353n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public o.c f3354o0;
    public Uri p0;
    public TextView q0;
    public MediaPlayer r0;
    public String s0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public EditText f3355w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public TextView f3356x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f3357y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public TextView f3358z;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public final List f3338Y = new ArrayList();

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public final List f3339Z = new ArrayList();

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public final List f3340a0 = new ArrayList();

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public int f3342c0 = 0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public int f3343d0 = 0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public boolean f3344e0 = false;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public boolean f3345f0 = false;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public int f3349j0 = 0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public int f3350k0 = 4302;
    public String t0 = "";
    public String u0 = "";
    public String v0 = "";
    public String w0 = "";
    public String x0 = "";
    public String y0 = "";
    public String z0 = "";
    public String A0 = "";
    public boolean E0 = false;
    public boolean F0 = false;
    public final Handler G0 = new Handler(Looper.getMainLooper());
    public final Object I0 = new Object();
    public final HashMap J0 = new HashMap();
    public final HashSet K0 = new HashSet();
    public final HashSet L0 = new HashSet();
    public final BroadcastReceiver M0 = new C0200k();
    public final BroadcastReceiver N0 = new v();

    public class A implements d.i {
        public A() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                new JSONObject(str).optString("id", "");
            } catch (Exception unused) {
            }
            Toast.makeText(MusicPlazaActivity.this, "上传成功", 0).show();
            MusicPlazaActivity.this.f3342c0 = 0;
            MusicPlazaActivity.this.V1();
            MusicPlazaActivity.this.f3346g0 = null;
            MusicPlazaActivity.this.f3347h0 = null;
            MusicPlazaActivity.this.f3348i0 = null;
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            if (i2 == 409 || (str != null && str.contains("duplicate_song"))) {
                Toast.makeText(MusicPlazaActivity.this, "你已上传过同一首歌", 0).show();
                return;
            }
            if (i2 == 413 && str != null && str.contains("cover_too_large")) {
                Toast.makeText(MusicPlazaActivity.this, "封面不能超过1MB", 0).show();
                return;
            }
            if (i2 == 413 || (str != null && str.contains("song_too_large"))) {
                Toast.makeText(MusicPlazaActivity.this, "歌曲不能超过20MB", 0).show();
                return;
            }
            if (i2 == 400 && str != null && str.contains("invalid_song")) {
                Toast.makeText(MusicPlazaActivity.this, "歌曲格式不支持", 0).show();
                return;
            }
            if (i2 == 400 && str != null && str.contains("invalid_cover")) {
                Toast.makeText(MusicPlazaActivity.this, "封面格式不支持", 0).show();
                return;
            }
            if (i2 != -1) {
                Toast.makeText(MusicPlazaActivity.this, "上传失败: " + i2, 0).show();
                return;
            }
            String lowerCase = str == null ? "" : str.toLowerCase(Locale.US);
            if (lowerCase.contains("cannot_open_stream") || lowerCase.contains("open failed") || lowerCase.contains("enoent")) {
                Toast.makeText(MusicPlazaActivity.this, "上传失败：文件读取异常，请重新选择歌曲", 0).show();
            } else if (lowerCase.contains("timeout") || lowerCase.contains("timed out")) {
                Toast.makeText(MusicPlazaActivity.this, "上传超时，请检查网络后重试", 0).show();
            } else {
                Toast.makeText(MusicPlazaActivity.this, "上传失败：网络异常，请稍后重试", 0).show();
            }
        }
    }

    public class B implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ArrayList f3360a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ R f3361b;

        public B(ArrayList arrayList, R r2) {
            this.f3360a = arrayList;
            this.f3361b = r2;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            if (i2 < 0 || i2 >= this.f3360a.size()) {
                return;
            }
            String str = (String) this.f3360a.get(i2);
            if ("播放".equals(str)) {
                MusicPlazaActivity.this.i2(this.f3361b);
                return;
            }
            if ("下载到本地".equals(str)) {
                MusicPlazaActivity.this.H1(this.f3361b);
                return;
            }
            if ("缓存到本地".equals(str)) {
                MusicPlazaActivity.this.C1(this.f3361b);
                return;
            }
            if ("点赞".equals(str) || "取消点赞".equals(str)) {
                MusicPlazaActivity.this.P2(this.f3361b);
                return;
            }
            if ("评论".equals(str)) {
                MusicPlazaActivity.this.Y1(this.f3361b);
                return;
            }
            if ("分享到聊天".equals(str)) {
                MusicPlazaActivity.this.D2(this.f3361b);
                return;
            }
            if ("收藏".equals(str)) {
                String str2 = this.f3361b.f3397h;
                if (str2 == null || str2.length() == 0) {
                    str2 = this.f3361b.f3396g;
                }
                String str3 = str2;
                MusicPlazaActivity musicPlazaActivity = MusicPlazaActivity.this;
                R r2 = this.f3361b;
                AbstractC0228t.d(musicPlazaActivity, r2.f3390a, r2.f3391b, str3, r2.f3392c, r2.f3393d);
                return;
            }
            if ("举报".equals(str)) {
                MusicPlazaActivity.this.H2(this.f3361b);
            } else if ("编辑信息".equals(str)) {
                MusicPlazaActivity.this.E2(this.f3361b);
            } else if ("删除歌曲".equals(str)) {
                MusicPlazaActivity.this.G1(this.f3361b);
            }
        }
    }

    public class C implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f3363a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ R f3364b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f3365c;

        public C(EditText editText, R r2, String str) {
            this.f3363a = editText;
            this.f3364b = r2;
            this.f3365c = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            String strTrim = this.f3363a.getText() == null ? "" : this.f3363a.getText().toString().trim();
            StringBuilder sb = new StringBuilder();
            sb.append("[音乐广场举报]");
            String str = this.f3364b.f3390a;
            if (str != null && str.length() > 0) {
                sb.append(" item_id=");
                sb.append(this.f3364b.f3390a);
            }
            String str2 = this.f3364b.f3391b;
            if (str2 != null && str2.length() > 0) {
                sb.append(" name=");
                sb.append(this.f3364b.f3391b);
            }
            if (strTrim.length() > 0) {
                strTrim = strTrim + "\n";
            }
            MusicPlazaActivity.this.O2(this.f3365c, strTrim + sb.toString());
        }
    }

    public class D implements d.i {
        public D() {
        }

        @Override // g0.d.i
        public void b(String str) {
            Toast.makeText(MusicPlazaActivity.this, "举报已提交，已进入公开法庭", 0).show();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(MusicPlazaActivity.this, "举报失败: " + i2, 0).show();
        }
    }

    public class E implements AbstractC0446l.c {
        public E() {
        }

        @Override // o0.AbstractC0446l.c
        public void a(boolean z2, String str, File file) {
            Toast.makeText(MusicPlazaActivity.this, str, 0).show();
        }
    }

    public class F implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ R f3369a;

        public F(R r2) {
            this.f3369a = r2;
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                R r2 = this.f3369a;
                r2.f3403n = jSONObject.optBoolean("liked", r2.f3403n);
                R r3 = this.f3369a;
                r3.f3400k = jSONObject.optInt("likes", r3.f3400k);
                String str2 = this.f3369a.f3390a;
                if (str2 != null && str2.equals(MusicPlazaActivity.this.A0)) {
                    MusicPlazaActivity.this.D0 = this.f3369a.f3403n;
                    MusicPlazaActivity.this.B0 = Math.max(0, this.f3369a.f3400k);
                }
            } catch (Exception unused) {
            }
            MusicPlazaActivity.this.f3341b0.notifyDataSetChanged();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(MusicPlazaActivity.this, "操作失败", 0).show();
        }
    }

    public class G implements View.OnClickListener {
        public G() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicPlazaActivity.this.finish();
        }
    }

    public class H implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ R f3372a;

        public class a implements d.i {
            public a() {
            }

            @Override // g0.d.i
            public void b(String str) {
                MusicPlazaActivity.this.f3338Y.remove(H.this.f3372a);
                MusicPlazaActivity.this.f3341b0.notifyDataSetChanged();
                MusicPlazaActivity.this.W2();
                Toast.makeText(MusicPlazaActivity.this, "已删除", 0).show();
            }

            @Override // g0.d.i
            public void c(int i2, String str) {
                if (g0.e.v(i2, str)) {
                    return;
                }
                Toast.makeText(MusicPlazaActivity.this, "删除失败", 0).show();
            }
        }

        public H(R r2) {
            this.f3372a = r2;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("item_id", this.f3372a.f3390a);
                g0.d.S("/music/plaza/delete", jSONObject, MusicPlazaActivity.this.f3336W, new a());
            } catch (Exception unused) {
                Toast.makeText(MusicPlazaActivity.this, "删除失败", 0).show();
            }
        }
    }

    public class I implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ R f3375a;

        public I(R r2) {
            this.f3375a = r2;
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                MusicPlazaActivity.this.X2(this.f3375a.f3390a, new JSONObject(str).optInt("play_count", this.f3375a.f3402m + 1));
            } catch (Exception unused) {
                MusicPlazaActivity musicPlazaActivity = MusicPlazaActivity.this;
                R r2 = this.f3375a;
                musicPlazaActivity.X2(r2.f3390a, r2.f3402m + 1);
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
        }
    }

    public class J extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f3377a;

        public J(String str) {
            this.f3377a = str;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Integer doInBackground(Void... voidArr) {
            return Integer.valueOf(MusicPlazaActivity.this.j2(this.f3377a));
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Integer num) {
            String str;
            int iIntValue = num == null ? 0 : num.intValue();
            synchronized (MusicPlazaActivity.this.I0) {
                try {
                    MusicPlazaActivity.this.K0.remove(this.f3377a);
                    if (iIntValue > 0) {
                        MusicPlazaActivity.this.J0.put(this.f3377a, Integer.valueOf(iIntValue));
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (iIntValue <= 0) {
                return;
            }
            boolean z2 = false;
            for (int i2 = 0; i2 < MusicPlazaActivity.this.f3338Y.size(); i2++) {
                R r2 = (R) MusicPlazaActivity.this.f3338Y.get(i2);
                if (r2 != null && r2.f3395f <= 0 && (str = r2.f3392c) != null) {
                    if (this.f3377a.equals(o0.A.g(str))) {
                        r2.f3395f = iIntValue;
                        z2 = true;
                    }
                }
            }
            if (z2) {
                MusicPlazaActivity.this.f3341b0.notifyDataSetChanged();
                MusicPlazaActivity.this.B2();
            }
        }
    }

    public class K implements AdapterView.OnItemClickListener {
        public K() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            if (i2 < 0 || i2 >= MusicPlazaActivity.this.f3338Y.size()) {
                return;
            }
            MusicPlazaActivity musicPlazaActivity = MusicPlazaActivity.this;
            musicPlazaActivity.i2((R) musicPlazaActivity.f3338Y.get(i2));
        }
    }

    public class L implements AdapterView.OnItemLongClickListener {
        public L() {
        }

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView adapterView, View view, int i2, long j2) {
            R r2;
            if (i2 < 0 || i2 >= MusicPlazaActivity.this.f3338Y.size() || (r2 = (R) MusicPlazaActivity.this.f3338Y.get(i2)) == null) {
                return false;
            }
            MusicPlazaActivity.this.F2(r2);
            return true;
        }
    }

    public class M implements View.OnClickListener {
        public M() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicPlazaActivity.this.f3342c0 = 0;
            MusicPlazaActivity.this.V1();
        }
    }

    public class N implements View.OnClickListener {
        public N() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicPlazaActivity.this.G2();
        }
    }

    public class O implements View.OnClickListener {
        public O() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicPlazaActivity.this.a2();
        }
    }

    public class P implements View.OnClickListener {
        public P() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicPlazaActivity.this.startActivity(new Intent(MusicPlazaActivity.this, (Class<?>) MusicDownloadsActivity.class));
        }
    }

    public class Q extends BaseAdapter {

        public class a implements View.OnClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ R f3386a;

            public a(R r2) {
                this.f3386a = r2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MusicPlazaActivity.this.i2(this.f3386a);
            }
        }

        public class b implements View.OnClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ R f3388a;

            public b(R r2) {
                this.f3388a = r2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MusicPlazaActivity.this.F2(this.f3388a);
            }
        }

        public Q() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return MusicPlazaActivity.this.f3338Y.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            if (i2 < 0 || i2 >= MusicPlazaActivity.this.f3338Y.size()) {
                return null;
            }
            return MusicPlazaActivity.this.f3338Y.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            S s2;
            if (view == null) {
                view = LayoutInflater.from(MusicPlazaActivity.this).inflate(f0.g.K0, viewGroup, false);
                s2 = new S(view);
                view.setTag(s2);
            } else {
                s2 = (S) view.getTag();
            }
            R r2 = (R) getItem(i2);
            if (r2 == null) {
                return view;
            }
            TextView textView = s2.f3406b;
            String str = r2.f3391b;
            textView.setText((str == null || str.length() == 0) ? "未命名歌曲" : r2.f3391b);
            String str2 = r2.f3397h;
            if (str2 == null || str2.length() == 0) {
                str2 = r2.f3396g;
            }
            if (str2 == null || str2.length() == 0) {
                str2 = "匿名用户";
            }
            String str3 = r2.f3398i;
            String strTrim = str3 == null ? "" : str3.trim();
            String str4 = r2.f3404o ? "我上传 · " : "上传者 · ";
            if (strTrim.length() > 0) {
                s2.f3408d.setText(str4 + str2 + " · " + strTrim);
            } else {
                s2.f3408d.setText(str4 + str2);
            }
            String str5 = r2.f3403n ? "已赞" : "赞";
            MusicPlazaActivity.this.K1(r2);
            s2.f3407c.setText(MusicPlazaActivity.this.N1(r2.f3395f));
            s2.f3409e.setText("大小 " + MusicPlazaActivity.this.O1(r2.f3394e));
            s2.f3410f.setText(str5 + " " + r2.f3400k + " · 评论 " + r2.f3401l + " · 播放 " + r2.f3402m);
            s2.f3411g.setText(r2.f3404o ? "我的上传" : "广场");
            s2.f3411g.setBackgroundResource(r2.f3404o ? f0.e.f5031r : f0.e.f5030q);
            s2.f3411g.setTextColor(MusicPlazaActivity.this.getResources().getColor(r2.f3404o ? f0.d.f4973j : f0.d.f4974k));
            s2.f3412h.setOnClickListener(new a(r2));
            s2.f3413i.setText(r2.f3404o ? "管理" : "更多");
            s2.f3413i.setOnClickListener(new b(r2));
            String str6 = r2.f3393d;
            if (str6 == null || str6.length() <= 0) {
                String str7 = r2.f3399j;
                if (str7 == null || str7.length() <= 0) {
                    s2.f3405a.setImageResource(f0.e.f4999T);
                } else {
                    AbstractC0455v.e(s2.f3405a, r2.f3399j);
                }
            } else {
                AbstractC0455v.e(s2.f3405a, r2.f3393d);
            }
            return view;
        }

        public /* synthetic */ Q(MusicPlazaActivity musicPlazaActivity, C0200k c0200k) {
            this();
        }
    }

    public static class R {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f3390a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f3391b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f3392c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f3393d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f3394e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f3395f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public String f3396g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public String f3397h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public String f3398i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public String f3399j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f3400k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public int f3401l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public int f3402m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public boolean f3403n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public boolean f3404o;

        /* JADX INFO: Added by JADX */
        public static final class anim {

            /* JADX INFO: Added by JADX */
            public static final int abc_grow_fade_in_from_bottom = 0x7f010002;

            /* JADX INFO: Added by JADX */
            public static final int abc_popup_enter = 0x7f010003;

            /* JADX INFO: Added by JADX */
            public static final int abc_popup_exit = 0x7f010004;

            /* JADX INFO: Added by JADX */
            public static final int abc_shrink_fade_out_from_bottom = 0x7f010005;

            /* JADX INFO: Added by JADX */
            public static final int square_activity_close_enter = 0x7f01000a;

            /* JADX INFO: Added by JADX */
            public static final int square_activity_close_exit = 0x7f01000b;

            /* JADX INFO: Added by JADX */
            public static final int square_activity_open_enter = 0x7f01000c;

            /* JADX INFO: Added by JADX */
            public static final int square_activity_open_exit = 0x7f01000d;

            /* JADX INFO: Added by JADX */
            public static final int square_content_enter = 0x7f01000e;

            /* JADX INFO: Added by JADX */
            public static final int square_list_item_enter = 0x7f01000f;

            /* JADX INFO: Added by JADX */
            public static final int square_list_layout = 0x7f010010;
        }

        /* JADX INFO: Added by JADX */
        public static final class attr {

            /* JADX INFO: Added by JADX */
            public static final int actionBarDivider = 0x7f020000;

            /* JADX INFO: Added by JADX */
            public static final int actionBarItemBackground = 0x7f020001;

            /* JADX INFO: Added by JADX */
            public static final int actionBarPopupTheme = 0x7f020002;

            /* JADX INFO: Added by JADX */
            public static final int actionBarSize = 0x7f020003;

            /* JADX INFO: Added by JADX */
            public static final int actionBarSplitStyle = 0x7f020004;

            /* JADX INFO: Added by JADX */
            public static final int actionBarStyle = 0x7f020005;

            /* JADX INFO: Added by JADX */
            public static final int actionBarTabBarStyle = 0x7f020006;

            /* JADX INFO: Added by JADX */
            public static final int actionBarTabStyle = 0x7f020007;

            /* JADX INFO: Added by JADX */
            public static final int actionBarTabTextStyle = 0x7f020008;

            /* JADX INFO: Added by JADX */
            public static final int actionBarTheme = 0x7f020009;

            /* JADX INFO: Added by JADX */
            public static final int actionBarWidgetTheme = 0x7f02000a;

            /* JADX INFO: Added by JADX */
            public static final int actionButtonStyle = 0x7f02000b;

            /* JADX INFO: Added by JADX */
            public static final int actionDropDownStyle = 0x7f02000c;

            /* JADX INFO: Added by JADX */
            public static final int actionLayout = 0x7f02000d;

            /* JADX INFO: Added by JADX */
            public static final int actionMenuTextAppearance = 0x7f02000e;

            /* JADX INFO: Added by JADX */
            public static final int actionMenuTextColor = 0x7f02000f;

            /* JADX INFO: Added by JADX */
            public static final int actionModeBackground = 0x7f020010;

            /* JADX INFO: Added by JADX */
            public static final int actionModeCloseButtonStyle = 0x7f020011;

            /* JADX INFO: Added by JADX */
            public static final int actionModeCloseDrawable = 0x7f020012;

            /* JADX INFO: Added by JADX */
            public static final int actionModeCopyDrawable = 0x7f020013;

            /* JADX INFO: Added by JADX */
            public static final int actionModeCutDrawable = 0x7f020014;

            /* JADX INFO: Added by JADX */
            public static final int actionModeFindDrawable = 0x7f020015;

            /* JADX INFO: Added by JADX */
            public static final int actionModePasteDrawable = 0x7f020016;

            /* JADX INFO: Added by JADX */
            public static final int actionModePopupWindowStyle = 0x7f020017;

            /* JADX INFO: Added by JADX */
            public static final int actionModeSelectAllDrawable = 0x7f020018;

            /* JADX INFO: Added by JADX */
            public static final int actionModeShareDrawable = 0x7f020019;

            /* JADX INFO: Added by JADX */
            public static final int actionModeSplitBackground = 0x7f02001a;

            /* JADX INFO: Added by JADX */
            public static final int actionModeStyle = 0x7f02001b;

            /* JADX INFO: Added by JADX */
            public static final int actionModeWebSearchDrawable = 0x7f02001c;

            /* JADX INFO: Added by JADX */
            public static final int actionOverflowButtonStyle = 0x7f02001d;

            /* JADX INFO: Added by JADX */
            public static final int actionOverflowMenuStyle = 0x7f02001e;

            /* JADX INFO: Added by JADX */
            public static final int actionProviderClass = 0x7f02001f;

            /* JADX INFO: Added by JADX */
            public static final int actionViewClass = 0x7f020020;

            /* JADX INFO: Added by JADX */
            public static final int activityChooserViewStyle = 0x7f020021;

            /* JADX INFO: Added by JADX */
            public static final int alertDialogButtonGroupStyle = 0x7f020022;

            /* JADX INFO: Added by JADX */
            public static final int alertDialogCenterButtons = 0x7f020023;

            /* JADX INFO: Added by JADX */
            public static final int alertDialogStyle = 0x7f020024;

            /* JADX INFO: Added by JADX */
            public static final int alertDialogTheme = 0x7f020025;

            /* JADX INFO: Added by JADX */
            public static final int allowStacking = 0x7f020026;

            /* JADX INFO: Added by JADX */
            public static final int alpha = 0x7f020027;

            /* JADX INFO: Added by JADX */
            public static final int arrowHeadLength = 0x7f020028;

            /* JADX INFO: Added by JADX */
            public static final int arrowShaftLength = 0x7f020029;

            /* JADX INFO: Added by JADX */
            public static final int autoCompleteTextViewStyle = 0x7f02002a;

            /* JADX INFO: Added by JADX */
            public static final int background = 0x7f02002b;

            /* JADX INFO: Added by JADX */
            public static final int backgroundSplit = 0x7f02002c;

            /* JADX INFO: Added by JADX */
            public static final int backgroundStacked = 0x7f02002d;

            /* JADX INFO: Added by JADX */
            public static final int backgroundTint = 0x7f02002e;

            /* JADX INFO: Added by JADX */
            public static final int backgroundTintMode = 0x7f02002f;

            /* JADX INFO: Added by JADX */
            public static final int barLength = 0x7f020030;

            /* JADX INFO: Added by JADX */
            public static final int borderlessButtonStyle = 0x7f020031;

            /* JADX INFO: Added by JADX */
            public static final int buttonBarButtonStyle = 0x7f020032;

            /* JADX INFO: Added by JADX */
            public static final int buttonBarNegativeButtonStyle = 0x7f020033;

            /* JADX INFO: Added by JADX */
            public static final int buttonBarNeutralButtonStyle = 0x7f020034;

            /* JADX INFO: Added by JADX */
            public static final int buttonBarPositiveButtonStyle = 0x7f020035;

            /* JADX INFO: Added by JADX */
            public static final int buttonBarStyle = 0x7f020036;

            /* JADX INFO: Added by JADX */
            public static final int buttonCornerRadius = 0x7f020037;

            /* JADX INFO: Added by JADX */
            public static final int buttonGravity = 0x7f020038;

            /* JADX INFO: Added by JADX */
            public static final int buttonPanelSideLayout = 0x7f020039;

            /* JADX INFO: Added by JADX */
            public static final int buttonStyle = 0x7f02003a;

            /* JADX INFO: Added by JADX */
            public static final int buttonStyleSmall = 0x7f02003b;

            /* JADX INFO: Added by JADX */
            public static final int buttonTint = 0x7f02003c;

            /* JADX INFO: Added by JADX */
            public static final int buttonTintMode = 0x7f02003d;

            /* JADX INFO: Added by JADX */
            public static final int chatBubbleAvatarCornerRadius = 0x7f02003e;

            /* JADX INFO: Added by JADX */
            public static final int chatBubbleCornerRadius = 0x7f02003f;

            /* JADX INFO: Added by JADX */
            public static final int checkboxStyle = 0x7f020040;

            /* JADX INFO: Added by JADX */
            public static final int checkedTextViewStyle = 0x7f020041;

            /* JADX INFO: Added by JADX */
            public static final int closeIcon = 0x7f020042;

            /* JADX INFO: Added by JADX */
            public static final int closeItemLayout = 0x7f020043;

            /* JADX INFO: Added by JADX */
            public static final int collapseContentDescription = 0x7f020044;

            /* JADX INFO: Added by JADX */
            public static final int collapseIcon = 0x7f020045;

            /* JADX INFO: Added by JADX */
            public static final int color = 0x7f020046;

            /* JADX INFO: Added by JADX */
            public static final int colorAccent = 0x7f020047;

            /* JADX INFO: Added by JADX */
            public static final int colorBackgroundFloating = 0x7f020048;

            /* JADX INFO: Added by JADX */
            public static final int colorButtonNormal = 0x7f020049;

            /* JADX INFO: Added by JADX */
            public static final int colorControlActivated = 0x7f02004a;

            /* JADX INFO: Added by JADX */
            public static final int colorControlHighlight = 0x7f02004b;

            /* JADX INFO: Added by JADX */
            public static final int colorControlNormal = 0x7f02004c;

            /* JADX INFO: Added by JADX */
            public static final int colorPrimary = 0x7f02004d;

            /* JADX INFO: Added by JADX */
            public static final int colorPrimaryDark = 0x7f02004e;

            /* JADX INFO: Added by JADX */
            public static final int colorSwitchThumbNormal = 0x7f02004f;

            /* JADX INFO: Added by JADX */
            public static final int commitIcon = 0x7f020050;

            /* JADX INFO: Added by JADX */
            public static final int contentInsetEnd = 0x7f020051;

            /* JADX INFO: Added by JADX */
            public static final int contentInsetEndWithActions = 0x7f020052;

            /* JADX INFO: Added by JADX */
            public static final int contentInsetLeft = 0x7f020053;

            /* JADX INFO: Added by JADX */
            public static final int contentInsetRight = 0x7f020054;

            /* JADX INFO: Added by JADX */
            public static final int contentInsetStart = 0x7f020055;

            /* JADX INFO: Added by JADX */
            public static final int contentInsetStartWithNavigation = 0x7f020056;

            /* JADX INFO: Added by JADX */
            public static final int controlBackground = 0x7f020057;

            /* JADX INFO: Added by JADX */
            public static final int cornerRadius = 0x7f020058;

            /* JADX INFO: Added by JADX */
            public static final int customNavigationLayout = 0x7f020059;

            /* JADX INFO: Added by JADX */
            public static final int defaultQueryHint = 0x7f02005a;

            /* JADX INFO: Added by JADX */
            public static final int dialogPreferredPadding = 0x7f02005b;

            /* JADX INFO: Added by JADX */
            public static final int dialogTheme = 0x7f02005c;

            /* JADX INFO: Added by JADX */
            public static final int displayOptions = 0x7f02005d;

            /* JADX INFO: Added by JADX */
            public static final int divider = 0x7f02005e;

            /* JADX INFO: Added by JADX */
            public static final int dividerHorizontal = 0x7f02005f;

            /* JADX INFO: Added by JADX */
            public static final int dividerPadding = 0x7f020060;

            /* JADX INFO: Added by JADX */
            public static final int dividerVertical = 0x7f020061;

            /* JADX INFO: Added by JADX */
            public static final int drawableSize = 0x7f020062;

            /* JADX INFO: Added by JADX */
            public static final int drawerArrowStyle = 0x7f020063;

            /* JADX INFO: Added by JADX */
            public static final int dropDownListViewStyle = 0x7f020064;

            /* JADX INFO: Added by JADX */
            public static final int dropdownListPreferredItemHeight = 0x7f020065;

            /* JADX INFO: Added by JADX */
            public static final int editTextBackground = 0x7f020066;

            /* JADX INFO: Added by JADX */
            public static final int editTextColor = 0x7f020067;

            /* JADX INFO: Added by JADX */
            public static final int editTextStyle = 0x7f020068;

            /* JADX INFO: Added by JADX */
            public static final int elevation = 0x7f020069;

            /* JADX INFO: Added by JADX */
            public static final int expandActivityOverflowButtonDrawable = 0x7f02006a;

            /* JADX INFO: Added by JADX */
            public static final int gapBetweenBars = 0x7f02006b;

            /* JADX INFO: Added by JADX */
            public static final int goIcon = 0x7f02006c;

            /* JADX INFO: Added by JADX */
            public static final int height = 0x7f02006d;

            /* JADX INFO: Added by JADX */
            public static final int hideOnContentScroll = 0x7f02006e;

            /* JADX INFO: Added by JADX */
            public static final int homeAsUpIndicator = 0x7f02006f;

            /* JADX INFO: Added by JADX */
            public static final int homeLayout = 0x7f020070;

            /* JADX INFO: Added by JADX */
            public static final int icon = 0x7f020071;

            /* JADX INFO: Added by JADX */
            public static final int iconifiedByDefault = 0x7f020072;

            /* JADX INFO: Added by JADX */
            public static final int imageButtonStyle = 0x7f020073;

            /* JADX INFO: Added by JADX */
            public static final int indeterminateProgressStyle = 0x7f020074;

            /* JADX INFO: Added by JADX */
            public static final int initialActivityCount = 0x7f020075;

            /* JADX INFO: Added by JADX */
            public static final int isLightTheme = 0x7f020076;

            /* JADX INFO: Added by JADX */
            public static final int itemPadding = 0x7f020077;

            /* JADX INFO: Added by JADX */
            public static final int layout = 0x7f020078;

            /* JADX INFO: Added by JADX */
            public static final int layoutManager = 0x7f020079;

            /* JADX INFO: Added by JADX */
            public static final int listChoiceBackgroundIndicator = 0x7f02007a;

            /* JADX INFO: Added by JADX */
            public static final int listDividerAlertDialog = 0x7f02007b;

            /* JADX INFO: Added by JADX */
            public static final int listItemLayout = 0x7f02007c;

            /* JADX INFO: Added by JADX */
            public static final int listLayout = 0x7f02007d;

            /* JADX INFO: Added by JADX */
            public static final int listMenuViewStyle = 0x7f02007e;

            /* JADX INFO: Added by JADX */
            public static final int listPopupWindowStyle = 0x7f02007f;

            /* JADX INFO: Added by JADX */
            public static final int listPreferredItemHeight = 0x7f020080;

            /* JADX INFO: Added by JADX */
            public static final int listPreferredItemHeightLarge = 0x7f020081;

            /* JADX INFO: Added by JADX */
            public static final int listPreferredItemHeightSmall = 0x7f020082;

            /* JADX INFO: Added by JADX */
            public static final int listPreferredItemPaddingLeft = 0x7f020083;

            /* JADX INFO: Added by JADX */
            public static final int listPreferredItemPaddingRight = 0x7f020084;

            /* JADX INFO: Added by JADX */
            public static final int logo = 0x7f020085;

            /* JADX INFO: Added by JADX */
            public static final int logoDescription = 0x7f020086;

            /* JADX INFO: Added by JADX */
            public static final int maxButtonHeight = 0x7f020087;

            /* JADX INFO: Added by JADX */
            public static final int measureWithLargestChild = 0x7f020088;

            /* JADX INFO: Added by JADX */
            public static final int multiChoiceItemLayout = 0x7f020089;

            /* JADX INFO: Added by JADX */
            public static final int navigationContentDescription = 0x7f02008a;

            /* JADX INFO: Added by JADX */
            public static final int navigationIcon = 0x7f02008b;

            /* JADX INFO: Added by JADX */
            public static final int navigationMode = 0x7f02008c;

            /* JADX INFO: Added by JADX */
            public static final int overlapAnchor = 0x7f02008d;

            /* JADX INFO: Added by JADX */
            public static final int paddingEnd = 0x7f02008e;

            /* JADX INFO: Added by JADX */
            public static final int paddingStart = 0x7f02008f;

            /* JADX INFO: Added by JADX */
            public static final int panelBackground = 0x7f020090;

            /* JADX INFO: Added by JADX */
            public static final int panelMenuListTheme = 0x7f020091;

            /* JADX INFO: Added by JADX */
            public static final int panelMenuListWidth = 0x7f020092;

            /* JADX INFO: Added by JADX */
            public static final int popupMenuStyle = 0x7f020093;

            /* JADX INFO: Added by JADX */
            public static final int popupTheme = 0x7f020094;

            /* JADX INFO: Added by JADX */
            public static final int popupWindowStyle = 0x7f020095;

            /* JADX INFO: Added by JADX */
            public static final int preserveIconSpacing = 0x7f020096;

            /* JADX INFO: Added by JADX */
            public static final int progressBarPadding = 0x7f020097;

            /* JADX INFO: Added by JADX */
            public static final int progressBarStyle = 0x7f020098;

            /* JADX INFO: Added by JADX */
            public static final int queryBackground = 0x7f020099;

            /* JADX INFO: Added by JADX */
            public static final int queryHint = 0x7f02009a;

            /* JADX INFO: Added by JADX */
            public static final int radioButtonStyle = 0x7f02009b;

            /* JADX INFO: Added by JADX */
            public static final int ratingBarStyle = 0x7f02009c;

            /* JADX INFO: Added by JADX */
            public static final int ratingBarStyleIndicator = 0x7f02009d;

            /* JADX INFO: Added by JADX */
            public static final int ratingBarStyleSmall = 0x7f02009e;

            /* JADX INFO: Added by JADX */
            public static final int reverseLayout = 0x7f02009f;

            /* JADX INFO: Added by JADX */
            public static final int searchHintIcon = 0x7f0200a0;

            /* JADX INFO: Added by JADX */
            public static final int searchIcon = 0x7f0200a1;

            /* JADX INFO: Added by JADX */
            public static final int searchViewStyle = 0x7f0200a2;

            /* JADX INFO: Added by JADX */
            public static final int seekBarStyle = 0x7f0200a3;

            /* JADX INFO: Added by JADX */
            public static final int selectableItemBackground = 0x7f0200a4;

            /* JADX INFO: Added by JADX */
            public static final int selectableItemBackgroundBorderless = 0x7f0200a5;

            /* JADX INFO: Added by JADX */
            public static final int showAsAction = 0x7f0200a6;

            /* JADX INFO: Added by JADX */
            public static final int showDividers = 0x7f0200a7;

            /* JADX INFO: Added by JADX */
            public static final int showText = 0x7f0200a8;

            /* JADX INFO: Added by JADX */
            public static final int singleChoiceItemLayout = 0x7f0200a9;

            /* JADX INFO: Added by JADX */
            public static final int spanCount = 0x7f0200aa;

            /* JADX INFO: Added by JADX */
            public static final int spinBars = 0x7f0200ab;

            /* JADX INFO: Added by JADX */
            public static final int spinnerDropDownItemStyle = 0x7f0200ac;

            /* JADX INFO: Added by JADX */
            public static final int spinnerStyle = 0x7f0200ad;

            /* JADX INFO: Added by JADX */
            public static final int splitTrack = 0x7f0200ae;

            /* JADX INFO: Added by JADX */
            public static final int srcCompat = 0x7f0200af;

            /* JADX INFO: Added by JADX */
            public static final int stackFromEnd = 0x7f0200b0;

            /* JADX INFO: Added by JADX */
            public static final int state_above_anchor = 0x7f0200b1;

            /* JADX INFO: Added by JADX */
            public static final int subMenuArrow = 0x7f0200b2;

            /* JADX INFO: Added by JADX */
            public static final int submitBackground = 0x7f0200b3;

            /* JADX INFO: Added by JADX */
            public static final int subtitle = 0x7f0200b4;

            /* JADX INFO: Added by JADX */
            public static final int subtitleTextAppearance = 0x7f0200b5;

            /* JADX INFO: Added by JADX */
            public static final int subtitleTextColor = 0x7f0200b6;

            /* JADX INFO: Added by JADX */
            public static final int subtitleTextStyle = 0x7f0200b7;

            /* JADX INFO: Added by JADX */
            public static final int suggestionRowLayout = 0x7f0200b8;

            /* JADX INFO: Added by JADX */
            public static final int switchMinWidth = 0x7f0200b9;

            /* JADX INFO: Added by JADX */
            public static final int switchPadding = 0x7f0200ba;

            /* JADX INFO: Added by JADX */
            public static final int switchStyle = 0x7f0200bb;

            /* JADX INFO: Added by JADX */
            public static final int switchTextAppearance = 0x7f0200bc;

            /* JADX INFO: Added by JADX */
            public static final int textAllCaps = 0x7f0200bd;

            /* JADX INFO: Added by JADX */
            public static final int textAppearanceLargePopupMenu = 0x7f0200be;

            /* JADX INFO: Added by JADX */
            public static final int textAppearanceListItem = 0x7f0200bf;

            /* JADX INFO: Added by JADX */
            public static final int textAppearanceListItemSmall = 0x7f0200c0;

            /* JADX INFO: Added by JADX */
            public static final int textAppearancePopupMenuHeader = 0x7f0200c1;

            /* JADX INFO: Added by JADX */
            public static final int textAppearanceSearchResultSubtitle = 0x7f0200c2;

            /* JADX INFO: Added by JADX */
            public static final int textAppearanceSearchResultTitle = 0x7f0200c3;

            /* JADX INFO: Added by JADX */
            public static final int textAppearanceSmallPopupMenu = 0x7f0200c4;

            /* JADX INFO: Added by JADX */
            public static final int textColorAlertDialogListItem = 0x7f0200c5;

            /* JADX INFO: Added by JADX */
            public static final int textColorSearchUrl = 0x7f0200c6;

            /* JADX INFO: Added by JADX */
            public static final int theme = 0x7f0200c7;

            /* JADX INFO: Added by JADX */
            public static final int thickness = 0x7f0200c8;

            /* JADX INFO: Added by JADX */
            public static final int thumbTextPadding = 0x7f0200c9;

            /* JADX INFO: Added by JADX */
            public static final int thumbTint = 0x7f0200ca;

            /* JADX INFO: Added by JADX */
            public static final int thumbTintMode = 0x7f0200cb;

            /* JADX INFO: Added by JADX */
            public static final int tickMark = 0x7f0200cc;

            /* JADX INFO: Added by JADX */
            public static final int tickMarkTint = 0x7f0200cd;

            /* JADX INFO: Added by JADX */
            public static final int tickMarkTintMode = 0x7f0200ce;

            /* JADX INFO: Added by JADX */
            public static final int title = 0x7f0200cf;

            /* JADX INFO: Added by JADX */
            public static final int titleMargin = 0x7f0200d0;

            /* JADX INFO: Added by JADX */
            public static final int titleMarginBottom = 0x7f0200d1;

            /* JADX INFO: Added by JADX */
            public static final int titleMarginEnd = 0x7f0200d2;

            /* JADX INFO: Added by JADX */
            public static final int titleMarginStart = 0x7f0200d3;

            /* JADX INFO: Added by JADX */
            public static final int titleMarginTop = 0x7f0200d4;

            /* JADX INFO: Added by JADX */
            public static final int titleMargins = 0x7f0200d5;

            /* JADX INFO: Added by JADX */
            public static final int titleTextAppearance = 0x7f0200d6;

            /* JADX INFO: Added by JADX */
            public static final int titleTextColor = 0x7f0200d7;

            /* JADX INFO: Added by JADX */
            public static final int titleTextStyle = 0x7f0200d8;

            /* JADX INFO: Added by JADX */
            public static final int toolbarNavigationButtonStyle = 0x7f0200d9;

            /* JADX INFO: Added by JADX */
            public static final int toolbarStyle = 0x7f0200da;

            /* JADX INFO: Added by JADX */
            public static final int track = 0x7f0200db;

            /* JADX INFO: Added by JADX */
            public static final int trackTint = 0x7f0200dc;

            /* JADX INFO: Added by JADX */
            public static final int trackTintMode = 0x7f0200dd;

            /* JADX INFO: Added by JADX */
            public static final int voiceIcon = 0x7f0200de;

            /* JADX INFO: Added by JADX */
            public static final int windowActionBar = 0x7f0200df;

            /* JADX INFO: Added by JADX */
            public static final int windowActionBarOverlay = 0x7f0200e0;

            /* JADX INFO: Added by JADX */
            public static final int windowActionModeOverlay = 0x7f0200e1;

            /* JADX INFO: Added by JADX */
            public static final int windowFixedHeightMajor = 0x7f0200e2;

            /* JADX INFO: Added by JADX */
            public static final int windowFixedHeightMinor = 0x7f0200e3;

            /* JADX INFO: Added by JADX */
            public static final int windowFixedWidthMajor = 0x7f0200e4;

            /* JADX INFO: Added by JADX */
            public static final int windowFixedWidthMinor = 0x7f0200e5;

            /* JADX INFO: Added by JADX */
            public static final int windowMinWidthMajor = 0x7f0200e6;

            /* JADX INFO: Added by JADX */
            public static final int windowMinWidthMinor = 0x7f0200e7;

            /* JADX INFO: Added by JADX */
            public static final int windowNoTitle = 0x7f0200e8;
        }

        /* JADX INFO: Added by JADX */
        public static final class bool {

            /* JADX INFO: Added by JADX */
            public static final int abc_action_bar_embed_tabs = 0x7f030000;

            /* JADX INFO: Added by JADX */
            public static final int abc_config_actionMenuItemAllCaps = 0x7f030002;

            /* JADX INFO: Added by JADX */
            public static final int abc_config_closeDialogWhenTouchOutside = 0x7f030003;

            /* JADX INFO: Added by JADX */
            public static final int abc_config_showMenuShortcutsWhenKeyboardPresent = 0x7f030004;
        }

        /* JADX INFO: Added by JADX */
        public static final class color {

            /* JADX INFO: Added by JADX */
            public static final int abc_background_cache_hint_selector_material_dark = 0x7f040000;

            /* JADX INFO: Added by JADX */
            public static final int abc_background_cache_hint_selector_material_light = 0x7f040001;

            /* JADX INFO: Added by JADX */
            public static final int abc_btn_colored_borderless_text_material = 0x7f040002;

            /* JADX INFO: Added by JADX */
            public static final int abc_color_highlight_material = 0x7f040003;

            /* JADX INFO: Added by JADX */
            public static final int abc_input_method_navigation_guard = 0x7f040004;

            /* JADX INFO: Added by JADX */
            public static final int abc_primary_text_disable_only_material_dark = 0x7f040005;

            /* JADX INFO: Added by JADX */
            public static final int abc_primary_text_disable_only_material_light = 0x7f040006;

            /* JADX INFO: Added by JADX */
            public static final int abc_primary_text_material_dark = 0x7f040007;

            /* JADX INFO: Added by JADX */
            public static final int abc_primary_text_material_light = 0x7f040008;

            /* JADX INFO: Added by JADX */
            public static final int abc_search_url_text = 0x7f040009;

            /* JADX INFO: Added by JADX */
            public static final int abc_search_url_text_normal = 0x7f04000a;

            /* JADX INFO: Added by JADX */
            public static final int abc_search_url_text_pressed = 0x7f04000b;

            /* JADX INFO: Added by JADX */
            public static final int abc_search_url_text_selected = 0x7f04000c;

            /* JADX INFO: Added by JADX */
            public static final int abc_secondary_text_material_dark = 0x7f04000d;

            /* JADX INFO: Added by JADX */
            public static final int abc_secondary_text_material_light = 0x7f04000e;

            /* JADX INFO: Added by JADX */
            public static final int abc_tint_btn_checkable = 0x7f04000f;

            /* JADX INFO: Added by JADX */
            public static final int abc_tint_default = 0x7f040010;

            /* JADX INFO: Added by JADX */
            public static final int abc_tint_edittext = 0x7f040011;

            /* JADX INFO: Added by JADX */
            public static final int abc_tint_seek_thumb = 0x7f040012;

            /* JADX INFO: Added by JADX */
            public static final int abc_tint_spinner = 0x7f040013;

            /* JADX INFO: Added by JADX */
            public static final int abc_tint_switch_thumb = 0x7f040014;

            /* JADX INFO: Added by JADX */
            public static final int abc_tint_switch_track = 0x7f040015;

            /* JADX INFO: Added by JADX */
            public static final int accent_material_dark = 0x7f040016;

            /* JADX INFO: Added by JADX */
            public static final int accent_material_light = 0x7f040017;

            /* JADX INFO: Added by JADX */
            public static final int background_floating_material_dark = 0x7f040018;

            /* JADX INFO: Added by JADX */
            public static final int background_floating_material_light = 0x7f040019;

            /* JADX INFO: Added by JADX */
            public static final int background_material_dark = 0x7f04001a;

            /* JADX INFO: Added by JADX */
            public static final int background_material_light = 0x7f04001b;

            /* JADX INFO: Added by JADX */
            public static final int bright_foreground_disabled_material_dark = 0x7f04001c;

            /* JADX INFO: Added by JADX */
            public static final int bright_foreground_disabled_material_light = 0x7f04001d;

            /* JADX INFO: Added by JADX */
            public static final int bright_foreground_material_dark = 0x7f040020;

            /* JADX INFO: Added by JADX */
            public static final int bright_foreground_material_light = 0x7f040021;

            /* JADX INFO: Added by JADX */
            public static final int button_material_dark = 0x7f040022;

            /* JADX INFO: Added by JADX */
            public static final int button_material_light = 0x7f040023;

            /* JADX INFO: Added by JADX */
            public static final int colorAccent = 0x7f040024;

            /* JADX INFO: Added by JADX */
            public static final int colorAccentDark = 0x7f040025;

            /* JADX INFO: Added by JADX */
            public static final int colorBackground = 0x7f040026;

            /* JADX INFO: Added by JADX */
            public static final int colorBadgeAdmin = 0x7f040027;

            /* JADX INFO: Added by JADX */
            public static final int colorBadgeOwner = 0x7f040028;

            /* JADX INFO: Added by JADX */
            public static final int colorBadgeTitle = 0x7f040029;

            /* JADX INFO: Added by JADX */
            public static final int colorBubbleIn = 0x7f04002a;

            /* JADX INFO: Added by JADX */
            public static final int colorChatTipBackground = 0x7f04002c;

            /* JADX INFO: Added by JADX */
            public static final int colorChatTipText = 0x7f04002d;

            /* JADX INFO: Added by JADX */
            public static final int colorDanger = 0x7f04002e;

            /* JADX INFO: Added by JADX */
            public static final int colorDangerDark = 0x7f04002f;

            /* JADX INFO: Added by JADX */
            public static final int colorDivider = 0x7f040030;

            /* JADX INFO: Added by JADX */
            public static final int colorLoginCardStroke = 0x7f040033;

            /* JADX INFO: Added by JADX */
            public static final int colorOwnerTag = 0x7f040036;

            /* JADX INFO: Added by JADX */
            public static final int colorPrimary = 0x7f040037;

            /* JADX INFO: Added by JADX */
            public static final int colorPrimaryDark = 0x7f040038;

            /* JADX INFO: Added by JADX */
            public static final int colorReplyBubble = 0x7f040039;

            /* JADX INFO: Added by JADX */
            public static final int colorSuccess = 0x7f04003b;

            /* JADX INFO: Added by JADX */
            public static final int colorTextPrimary = 0x7f04003c;

            /* JADX INFO: Added by JADX */
            public static final int colorTextSecondary = 0x7f04003d;

            /* JADX INFO: Added by JADX */
            public static final int colorToolbar = 0x7f04003e;

            /* JADX INFO: Added by JADX */
            public static final int colorWarning = 0x7f04003f;

            /* JADX INFO: Added by JADX */
            public static final int color_accent = 0x7f040040;

            /* JADX INFO: Added by JADX */
            public static final int color_background = 0x7f040042;

            /* JADX INFO: Added by JADX */
            public static final int color_button_text = 0x7f040043;

            /* JADX INFO: Added by JADX */
            public static final int color_danger_pressed_overlay = 0x7f040045;

            /* JADX INFO: Added by JADX */
            public static final int color_divider = 0x7f040046;

            /* JADX INFO: Added by JADX */
            public static final int color_on_primary = 0x7f040047;

            /* JADX INFO: Added by JADX */
            public static final int color_primary = 0x7f040048;

            /* JADX INFO: Added by JADX */
            public static final int color_primary_dark = 0x7f040049;

            /* JADX INFO: Added by JADX */
            public static final int color_primary_pressed_overlay = 0x7f04004a;

            /* JADX INFO: Added by JADX */
            public static final int color_row_pressed = 0x7f04004b;

            /* JADX INFO: Added by JADX */
            public static final int color_surface = 0x7f04004c;

            /* JADX INFO: Added by JADX */
            public static final int color_surface_pressed = 0x7f04004d;

            /* JADX INFO: Added by JADX */
            public static final int color_surface_stroke = 0x7f04004e;

            /* JADX INFO: Added by JADX */
            public static final int color_tab_bar_divider = 0x7f04004f;

            /* JADX INFO: Added by JADX */
            public static final int color_tab_indicator = 0x7f040050;

            /* JADX INFO: Added by JADX */
            public static final int color_text_primary = 0x7f040051;

            /* JADX INFO: Added by JADX */
            public static final int color_text_secondary = 0x7f040052;

            /* JADX INFO: Added by JADX */
            public static final int foreground_material_dark = 0x7f04005a;

            /* JADX INFO: Added by JADX */
            public static final int foreground_material_light = 0x7f04005b;

            /* JADX INFO: Added by JADX */
            public static final int highlighted_text_material_dark = 0x7f04005d;

            /* JADX INFO: Added by JADX */
            public static final int highlighted_text_material_light = 0x7f04005e;

            /* JADX INFO: Added by JADX */
            public static final int hint_foreground_material_dark = 0x7f04005f;

            /* JADX INFO: Added by JADX */
            public static final int hint_foreground_material_light = 0x7f040060;

            /* JADX INFO: Added by JADX */
            public static final int home_chat_hero_text = 0x7f040061;

            /* JADX INFO: Added by JADX */
            public static final int home_chat_icon_tint = 0x7f040062;

            /* JADX INFO: Added by JADX */
            public static final int home_count_text_muted = 0x7f040063;

            /* JADX INFO: Added by JADX */
            public static final int home_empty_text = 0x7f040064;

            /* JADX INFO: Added by JADX */
            public static final int home_row_subtitle_mention = 0x7f040065;

            /* JADX INFO: Added by JADX */
            public static final int home_row_subtitle_text = 0x7f040066;

            /* JADX INFO: Added by JADX */
            public static final int home_row_tag_text = 0x7f040067;

            /* JADX INFO: Added by JADX */
            public static final int home_row_time_text = 0x7f040068;

            /* JADX INFO: Added by JADX */
            public static final int home_row_title_text = 0x7f040069;

            /* JADX INFO: Added by JADX */
            public static final int home_section_title_text = 0x7f04006a;

            /* JADX INFO: Added by JADX */
            public static final int material_deep_teal_200 = 0x7f04006e;

            /* JADX INFO: Added by JADX */
            public static final int material_deep_teal_500 = 0x7f04006f;

            /* JADX INFO: Added by JADX */
            public static final int material_grey_100 = 0x7f040070;

            /* JADX INFO: Added by JADX */
            public static final int material_grey_50 = 0x7f040072;

            /* JADX INFO: Added by JADX */
            public static final int material_grey_600 = 0x7f040073;

            /* JADX INFO: Added by JADX */
            public static final int material_grey_800 = 0x7f040074;

            /* JADX INFO: Added by JADX */
            public static final int material_grey_850 = 0x7f040075;

            /* JADX INFO: Added by JADX */
            public static final int material_grey_900 = 0x7f040076;

            /* JADX INFO: Added by JADX */
            public static final int primary_dark_material_dark = 0x7f040081;

            /* JADX INFO: Added by JADX */
            public static final int primary_dark_material_light = 0x7f040082;

            /* JADX INFO: Added by JADX */
            public static final int primary_material_dark = 0x7f040083;

            /* JADX INFO: Added by JADX */
            public static final int primary_material_light = 0x7f040084;

            /* JADX INFO: Added by JADX */
            public static final int primary_text_default_material_dark = 0x7f040085;

            /* JADX INFO: Added by JADX */
            public static final int primary_text_default_material_light = 0x7f040086;

            /* JADX INFO: Added by JADX */
            public static final int primary_text_disabled_material_dark = 0x7f040087;

            /* JADX INFO: Added by JADX */
            public static final int primary_text_disabled_material_light = 0x7f040088;

            /* JADX INFO: Added by JADX */
            public static final int ripple_material_dark = 0x7f040089;

            /* JADX INFO: Added by JADX */
            public static final int ripple_material_light = 0x7f04008a;

            /* JADX INFO: Added by JADX */
            public static final int secondary_text_default_material_dark = 0x7f04008b;

            /* JADX INFO: Added by JADX */
            public static final int secondary_text_default_material_light = 0x7f04008c;

            /* JADX INFO: Added by JADX */
            public static final int secondary_text_disabled_material_dark = 0x7f04008d;

            /* JADX INFO: Added by JADX */
            public static final int secondary_text_disabled_material_light = 0x7f04008e;

            /* JADX INFO: Added by JADX */
            public static final int switch_thumb_disabled_material_dark = 0x7f04008f;

            /* JADX INFO: Added by JADX */
            public static final int switch_thumb_disabled_material_light = 0x7f040090;

            /* JADX INFO: Added by JADX */
            public static final int switch_thumb_material_dark = 0x7f040091;

            /* JADX INFO: Added by JADX */
            public static final int switch_thumb_material_light = 0x7f040092;

            /* JADX INFO: Added by JADX */
            public static final int switch_thumb_normal_material_dark = 0x7f040093;

            /* JADX INFO: Added by JADX */
            public static final int switch_thumb_normal_material_light = 0x7f040094;

            /* JADX INFO: Added by JADX */
            public static final int tab_icon_tint = 0x7f040095;
        }

        /* JADX INFO: Added by JADX */
        public static final class dimen {

            /* JADX INFO: Added by JADX */
            public static final int abc_action_bar_content_inset_material = 0x7f050000;

            /* JADX INFO: Added by JADX */
            public static final int abc_action_bar_content_inset_with_nav = 0x7f050001;

            /* JADX INFO: Added by JADX */
            public static final int abc_action_bar_default_height_material = 0x7f050002;

            /* JADX INFO: Added by JADX */
            public static final int abc_action_bar_default_padding_end_material = 0x7f050003;

            /* JADX INFO: Added by JADX */
            public static final int abc_action_bar_default_padding_start_material = 0x7f050004;

            /* JADX INFO: Added by JADX */
            public static final int abc_action_bar_elevation_material = 0x7f050005;

            /* JADX INFO: Added by JADX */
            public static final int abc_action_bar_overflow_padding_end_material = 0x7f050007;

            /* JADX INFO: Added by JADX */
            public static final int abc_action_bar_overflow_padding_start_material = 0x7f050008;

            /* JADX INFO: Added by JADX */
            public static final int abc_action_bar_subtitle_top_margin_material = 0x7f05000d;

            /* JADX INFO: Added by JADX */
            public static final int abc_action_button_min_height_material = 0x7f05000e;

            /* JADX INFO: Added by JADX */
            public static final int abc_action_button_min_width_material = 0x7f05000f;

            /* JADX INFO: Added by JADX */
            public static final int abc_action_button_min_width_overflow_material = 0x7f050010;

            /* JADX INFO: Added by JADX */
            public static final int abc_alert_dialog_button_bar_height = 0x7f050011;

            /* JADX INFO: Added by JADX */
            public static final int abc_button_inset_horizontal_material = 0x7f050012;

            /* JADX INFO: Added by JADX */
            public static final int abc_button_inset_vertical_material = 0x7f050013;

            /* JADX INFO: Added by JADX */
            public static final int abc_button_padding_horizontal_material = 0x7f050014;

            /* JADX INFO: Added by JADX */
            public static final int abc_button_padding_vertical_material = 0x7f050015;

            /* JADX INFO: Added by JADX */
            public static final int abc_cascading_menus_min_smallest_width = 0x7f050016;

            /* JADX INFO: Added by JADX */
            public static final int abc_config_prefDialogWidth = 0x7f050017;

            /* JADX INFO: Added by JADX */
            public static final int abc_control_corner_material = 0x7f050018;

            /* JADX INFO: Added by JADX */
            public static final int abc_control_inset_material = 0x7f050019;

            /* JADX INFO: Added by JADX */
            public static final int abc_control_padding_material = 0x7f05001a;

            /* JADX INFO: Added by JADX */
            public static final int abc_dialog_list_padding_vertical_material = 0x7f05001f;

            /* JADX INFO: Added by JADX */
            public static final int abc_dialog_min_width_major = 0x7f050020;

            /* JADX INFO: Added by JADX */
            public static final int abc_dialog_min_width_minor = 0x7f050021;

            /* JADX INFO: Added by JADX */
            public static final int abc_dialog_padding_material = 0x7f050022;

            /* JADX INFO: Added by JADX */
            public static final int abc_dialog_padding_top_material = 0x7f050023;

            /* JADX INFO: Added by JADX */
            public static final int abc_disabled_alpha_material_dark = 0x7f050024;

            /* JADX INFO: Added by JADX */
            public static final int abc_disabled_alpha_material_light = 0x7f050025;

            /* JADX INFO: Added by JADX */
            public static final int abc_dropdownitem_icon_width = 0x7f050026;

            /* JADX INFO: Added by JADX */
            public static final int abc_dropdownitem_text_padding_left = 0x7f050027;

            /* JADX INFO: Added by JADX */
            public static final int abc_dropdownitem_text_padding_right = 0x7f050028;

            /* JADX INFO: Added by JADX */
            public static final int abc_edit_text_inset_bottom_material = 0x7f050029;

            /* JADX INFO: Added by JADX */
            public static final int abc_edit_text_inset_horizontal_material = 0x7f05002a;

            /* JADX INFO: Added by JADX */
            public static final int abc_edit_text_inset_top_material = 0x7f05002b;

            /* JADX INFO: Added by JADX */
            public static final int abc_floating_window_z = 0x7f05002c;

            /* JADX INFO: Added by JADX */
            public static final int abc_list_item_padding_horizontal_material = 0x7f05002d;

            /* JADX INFO: Added by JADX */
            public static final int abc_panel_menu_list_width = 0x7f05002e;

            /* JADX INFO: Added by JADX */
            public static final int abc_progress_bar_height_material = 0x7f05002f;

            /* JADX INFO: Added by JADX */
            public static final int abc_select_dialog_padding_start_material = 0x7f050034;

            /* JADX INFO: Added by JADX */
            public static final int abc_switch_padding = 0x7f050035;

            /* JADX INFO: Added by JADX */
            public static final int abc_text_size_body_1_material = 0x7f050036;

            /* JADX INFO: Added by JADX */
            public static final int abc_text_size_button_material = 0x7f050038;

            /* JADX INFO: Added by JADX */
            public static final int abc_text_size_large_material = 0x7f05003f;

            /* JADX INFO: Added by JADX */
            public static final int abc_text_size_medium_material = 0x7f050040;

            /* JADX INFO: Added by JADX */
            public static final int abc_text_size_menu_header_material = 0x7f050041;

            /* JADX INFO: Added by JADX */
            public static final int abc_text_size_menu_material = 0x7f050042;

            /* JADX INFO: Added by JADX */
            public static final int abc_text_size_small_material = 0x7f050043;

            /* JADX INFO: Added by JADX */
            public static final int abc_text_size_subhead_material = 0x7f050044;

            /* JADX INFO: Added by JADX */
            public static final int abc_text_size_subtitle_material_toolbar = 0x7f050045;

            /* JADX INFO: Added by JADX */
            public static final int abc_text_size_title_material = 0x7f050046;

            /* JADX INFO: Added by JADX */
            public static final int abc_text_size_title_material_toolbar = 0x7f050047;

            /* JADX INFO: Added by JADX */
            public static final int avatar_size = 0x7f050048;

            /* JADX INFO: Added by JADX */
            public static final int chat_plus_action_icon_padding = 0x7f050049;

            /* JADX INFO: Added by JADX */
            public static final int chat_plus_action_icon_padding_emoji = 0x7f05004a;

            /* JADX INFO: Added by JADX */
            public static final int chat_plus_action_tile_size = 0x7f05004b;

            /* JADX INFO: Added by JADX */
            public static final int highlight_alpha_material_colored = 0x7f05004e;

            /* JADX INFO: Added by JADX */
            public static final int item_touch_helper_max_drag_scroll_per_frame = 0x7f050051;

            /* JADX INFO: Added by JADX */
            public static final int item_touch_helper_swipe_escape_max_velocity = 0x7f050052;

            /* JADX INFO: Added by JADX */
            public static final int item_touch_helper_swipe_escape_velocity = 0x7f050053;

            /* JADX INFO: Added by JADX */
            public static final int message_text_max_width = 0x7f050054;

            /* JADX INFO: Added by JADX */
            public static final int notification_large_icon_height = 0x7f050056;

            /* JADX INFO: Added by JADX */
            public static final int notification_large_icon_width = 0x7f050057;

            /* JADX INFO: Added by JADX */
            public static final int notification_subtext_size = 0x7f050058;

            /* JADX INFO: Added by JADX */
            public static final int space_l = 0x7f050059;

            /* JADX INFO: Added by JADX */
            public static final int space_m = 0x7f05005a;

            /* JADX INFO: Added by JADX */
            public static final int space_s = 0x7f05005b;

            /* JADX INFO: Added by JADX */
            public static final int toolbar_height = 0x7f05005e;
        }

        /* JADX INFO: Added by JADX */
        public static final class drawable {

            /* JADX INFO: Added by JADX */
            public static final int abc_ab_share_pack_mtrl_alpha = 0x7f060000;

            /* JADX INFO: Added by JADX */
            public static final int abc_action_bar_item_background_material = 0x7f060001;

            /* JADX INFO: Added by JADX */
            public static final int abc_btn_borderless_material = 0x7f060002;

            /* JADX INFO: Added by JADX */
            public static final int abc_btn_check_material = 0x7f060003;

            /* JADX INFO: Added by JADX */
            public static final int abc_btn_check_to_on_mtrl_000 = 0x7f060004;

            /* JADX INFO: Added by JADX */
            public static final int abc_btn_check_to_on_mtrl_015 = 0x7f060005;

            /* JADX INFO: Added by JADX */
            public static final int abc_btn_colored_material = 0x7f060006;

            /* JADX INFO: Added by JADX */
            public static final int abc_btn_default_mtrl_shape = 0x7f060007;

            /* JADX INFO: Added by JADX */
            public static final int abc_btn_radio_material = 0x7f060008;

            /* JADX INFO: Added by JADX */
            public static final int abc_btn_radio_to_on_mtrl_000 = 0x7f060009;

            /* JADX INFO: Added by JADX */
            public static final int abc_btn_radio_to_on_mtrl_015 = 0x7f06000a;

            /* JADX INFO: Added by JADX */
            public static final int abc_btn_switch_to_on_mtrl_00001 = 0x7f06000b;

            /* JADX INFO: Added by JADX */
            public static final int abc_btn_switch_to_on_mtrl_00012 = 0x7f06000c;

            /* JADX INFO: Added by JADX */
            public static final int abc_cab_background_internal_bg = 0x7f06000d;

            /* JADX INFO: Added by JADX */
            public static final int abc_cab_background_top_material = 0x7f06000e;

            /* JADX INFO: Added by JADX */
            public static final int abc_cab_background_top_mtrl_alpha = 0x7f06000f;

            /* JADX INFO: Added by JADX */
            public static final int abc_control_background_material = 0x7f060010;

            /* JADX INFO: Added by JADX */
            public static final int abc_dialog_material_background = 0x7f060011;

            /* JADX INFO: Added by JADX */
            public static final int abc_edit_text_material = 0x7f060012;

            /* JADX INFO: Added by JADX */
            public static final int abc_ic_ab_back_material = 0x7f060013;

            /* JADX INFO: Added by JADX */
            public static final int abc_ic_arrow_drop_right_black_24dp = 0x7f060014;

            /* JADX INFO: Added by JADX */
            public static final int abc_ic_clear_material = 0x7f060015;

            /* JADX INFO: Added by JADX */
            public static final int abc_ic_commit_search_api_mtrl_alpha = 0x7f060016;

            /* JADX INFO: Added by JADX */
            public static final int abc_ic_go_search_api_material = 0x7f060017;

            /* JADX INFO: Added by JADX */
            public static final int abc_ic_menu_copy_mtrl_am_alpha = 0x7f060018;

            /* JADX INFO: Added by JADX */
            public static final int abc_ic_menu_cut_mtrl_alpha = 0x7f060019;

            /* JADX INFO: Added by JADX */
            public static final int abc_ic_menu_overflow_material = 0x7f06001a;

            /* JADX INFO: Added by JADX */
            public static final int abc_ic_menu_paste_mtrl_am_alpha = 0x7f06001b;

            /* JADX INFO: Added by JADX */
            public static final int abc_ic_menu_selectall_mtrl_alpha = 0x7f06001c;

            /* JADX INFO: Added by JADX */
            public static final int abc_ic_menu_share_mtrl_alpha = 0x7f06001d;

            /* JADX INFO: Added by JADX */
            public static final int abc_ic_search_api_material = 0x7f06001e;

            /* JADX INFO: Added by JADX */
            public static final int abc_ic_star_black_16dp = 0x7f06001f;

            /* JADX INFO: Added by JADX */
            public static final int abc_ic_star_black_36dp = 0x7f060020;

            /* JADX INFO: Added by JADX */
            public static final int abc_ic_star_black_48dp = 0x7f060021;

            /* JADX INFO: Added by JADX */
            public static final int abc_ic_star_half_black_16dp = 0x7f060022;

            /* JADX INFO: Added by JADX */
            public static final int abc_ic_star_half_black_36dp = 0x7f060023;

            /* JADX INFO: Added by JADX */
            public static final int abc_ic_star_half_black_48dp = 0x7f060024;

            /* JADX INFO: Added by JADX */
            public static final int abc_ic_voice_search_api_material = 0x7f060025;

            /* JADX INFO: Added by JADX */
            public static final int abc_item_background_holo_dark = 0x7f060026;

            /* JADX INFO: Added by JADX */
            public static final int abc_item_background_holo_light = 0x7f060027;

            /* JADX INFO: Added by JADX */
            public static final int abc_list_divider_mtrl_alpha = 0x7f060028;

            /* JADX INFO: Added by JADX */
            public static final int abc_list_focused_holo = 0x7f060029;

            /* JADX INFO: Added by JADX */
            public static final int abc_list_longpressed_holo = 0x7f06002a;

            /* JADX INFO: Added by JADX */
            public static final int abc_list_pressed_holo_dark = 0x7f06002b;

            /* JADX INFO: Added by JADX */
            public static final int abc_list_pressed_holo_light = 0x7f06002c;

            /* JADX INFO: Added by JADX */
            public static final int abc_list_selector_background_transition_holo_dark = 0x7f06002d;

            /* JADX INFO: Added by JADX */
            public static final int abc_list_selector_background_transition_holo_light = 0x7f06002e;

            /* JADX INFO: Added by JADX */
            public static final int abc_list_selector_disabled_holo_dark = 0x7f06002f;

            /* JADX INFO: Added by JADX */
            public static final int abc_list_selector_disabled_holo_light = 0x7f060030;

            /* JADX INFO: Added by JADX */
            public static final int abc_list_selector_holo_dark = 0x7f060031;

            /* JADX INFO: Added by JADX */
            public static final int abc_list_selector_holo_light = 0x7f060032;

            /* JADX INFO: Added by JADX */
            public static final int abc_menu_hardkey_panel_mtrl_mult = 0x7f060033;

            /* JADX INFO: Added by JADX */
            public static final int abc_popup_background_mtrl_mult = 0x7f060034;

            /* JADX INFO: Added by JADX */
            public static final int abc_ratingbar_indicator_material = 0x7f060035;

            /* JADX INFO: Added by JADX */
            public static final int abc_ratingbar_material = 0x7f060036;

            /* JADX INFO: Added by JADX */
            public static final int abc_ratingbar_small_material = 0x7f060037;

            /* JADX INFO: Added by JADX */
            public static final int abc_scrubber_control_off_mtrl_alpha = 0x7f060038;

            /* JADX INFO: Added by JADX */
            public static final int abc_scrubber_control_to_pressed_mtrl_000 = 0x7f060039;

            /* JADX INFO: Added by JADX */
            public static final int abc_scrubber_control_to_pressed_mtrl_005 = 0x7f06003a;

            /* JADX INFO: Added by JADX */
            public static final int abc_scrubber_primary_mtrl_alpha = 0x7f06003b;

            /* JADX INFO: Added by JADX */
            public static final int abc_scrubber_track_mtrl_alpha = 0x7f06003c;

            /* JADX INFO: Added by JADX */
            public static final int abc_seekbar_thumb_material = 0x7f06003d;

            /* JADX INFO: Added by JADX */
            public static final int abc_seekbar_tick_mark_material = 0x7f06003e;

            /* JADX INFO: Added by JADX */
            public static final int abc_seekbar_track_material = 0x7f06003f;

            /* JADX INFO: Added by JADX */
            public static final int abc_spinner_mtrl_am_alpha = 0x7f060040;

            /* JADX INFO: Added by JADX */
            public static final int abc_spinner_textfield_background_material = 0x7f060041;

            /* JADX INFO: Added by JADX */
            public static final int abc_switch_thumb_material = 0x7f060042;

            /* JADX INFO: Added by JADX */
            public static final int abc_switch_track_mtrl_alpha = 0x7f060043;

            /* JADX INFO: Added by JADX */
            public static final int abc_tab_indicator_material = 0x7f060044;

            /* JADX INFO: Added by JADX */
            public static final int abc_tab_indicator_mtrl_alpha = 0x7f060045;

            /* JADX INFO: Added by JADX */
            public static final int abc_text_cursor_material = 0x7f060046;

            /* JADX INFO: Added by JADX */
            public static final int abc_text_select_handle_left_mtrl_dark = 0x7f060047;

            /* JADX INFO: Added by JADX */
            public static final int abc_text_select_handle_left_mtrl_light = 0x7f060048;

            /* JADX INFO: Added by JADX */
            public static final int abc_text_select_handle_middle_mtrl_dark = 0x7f060049;

            /* JADX INFO: Added by JADX */
            public static final int abc_text_select_handle_middle_mtrl_light = 0x7f06004a;

            /* JADX INFO: Added by JADX */
            public static final int abc_text_select_handle_right_mtrl_dark = 0x7f06004b;

            /* JADX INFO: Added by JADX */
            public static final int abc_text_select_handle_right_mtrl_light = 0x7f06004c;

            /* JADX INFO: Added by JADX */
            public static final int abc_textfield_activated_mtrl_alpha = 0x7f06004d;

            /* JADX INFO: Added by JADX */
            public static final int abc_textfield_default_mtrl_alpha = 0x7f06004e;

            /* JADX INFO: Added by JADX */
            public static final int abc_textfield_search_activated_mtrl_alpha = 0x7f06004f;

            /* JADX INFO: Added by JADX */
            public static final int abc_textfield_search_default_mtrl_alpha = 0x7f060050;

            /* JADX INFO: Added by JADX */
            public static final int abc_textfield_search_material = 0x7f060051;

            /* JADX INFO: Added by JADX */
            public static final int abc_vector_test = 0x7f060052;

            /* JADX INFO: Added by JADX */
            public static final int badge_red_dot = 0x7f060053;

            /* JADX INFO: Added by JADX */
            public static final int bg_avatar_round = 0x7f060056;

            /* JADX INFO: Added by JADX */
            public static final int bg_badge_admin = 0x7f060057;

            /* JADX INFO: Added by JADX */
            public static final int bg_badge_dot = 0x7f060058;

            /* JADX INFO: Added by JADX */
            public static final int bg_badge_owner = 0x7f060059;

            /* JADX INFO: Added by JADX */
            public static final int bg_badge_title = 0x7f06005a;

            /* JADX INFO: Added by JADX */
            public static final int bg_bubble_in = 0x7f06005b;

            /* JADX INFO: Added by JADX */
            public static final int bg_chat_input = 0x7f06005c;

            /* JADX INFO: Added by JADX */
            public static final int bg_chat_panel_item = 0x7f06005d;

            /* JADX INFO: Added by JADX */
            public static final int bg_chat_tip = 0x7f06005e;

            /* JADX INFO: Added by JADX */
            public static final int bg_chip_pending = 0x7f06005f;

            /* JADX INFO: Added by JADX */
            public static final int bg_chip_success = 0x7f060060;

            /* JADX INFO: Added by JADX */
            public static final int bg_chip_warning = 0x7f060061;

            /* JADX INFO: Added by JADX */
            public static final int bg_flat_row_selector = 0x7f060062;

            /* JADX INFO: Added by JADX */
            public static final int bg_forward_bubble_inner = 0x7f060063;

            /* JADX INFO: Added by JADX */
            public static final int bg_group_manage_card = 0x7f060064;

            /* JADX INFO: Added by JADX */
            public static final int bg_group_manage_row = 0x7f060065;

            /* JADX INFO: Added by JADX */
            public static final int bg_hold_to_talk = 0x7f060066;

            /* JADX INFO: Added by JADX */
            public static final int bg_hold_to_talk_normal = 0x7f060067;

            /* JADX INFO: Added by JADX */
            public static final int bg_hold_to_talk_pressed = 0x7f060068;

            /* JADX INFO: Added by JADX */
            public static final int bg_icon_btn = 0x7f060069;

            /* JADX INFO: Added by JADX */
            public static final int bg_image_placeholder = 0x7f06006a;

            /* JADX INFO: Added by JADX */
            public static final int bg_list_item_surface = 0x7f06006b;

            /* JADX INFO: Added by JADX */
            public static final int bg_login_background = 0x7f06006c;

            /* JADX INFO: Added by JADX */
            public static final int bg_login_card = 0x7f06006d;

            /* JADX INFO: Added by JADX */
            public static final int bg_main_tabs = 0x7f06006e;

            /* JADX INFO: Added by JADX */
            public static final int bg_moments_cover_mask = 0x7f06006f;

            /* JADX INFO: Added by JADX */
            public static final int bg_moments_cover_placeholder = 0x7f060070;

            /* JADX INFO: Added by JADX */
            public static final int bg_moments_header_avatar_border = 0x7f060071;

            /* JADX INFO: Added by JADX */
            public static final int bg_msg_me = 0x7f060072;

            /* JADX INFO: Added by JADX */
            public static final int bg_msg_me_normal = 0x7f060073;

            /* JADX INFO: Added by JADX */
            public static final int bg_msg_me_pressed = 0x7f060074;

            /* JADX INFO: Added by JADX */
            public static final int bg_msg_music_me = 0x7f060075;

            /* JADX INFO: Added by JADX */
            public static final int bg_msg_music_me_normal = 0x7f060076;

            /* JADX INFO: Added by JADX */
            public static final int bg_msg_music_me_pressed = 0x7f060077;

            /* JADX INFO: Added by JADX */
            public static final int bg_msg_music_other = 0x7f060078;

            /* JADX INFO: Added by JADX */
            public static final int bg_msg_music_other_normal = 0x7f060079;

            /* JADX INFO: Added by JADX */
            public static final int bg_msg_music_other_pressed = 0x7f06007a;

            /* JADX INFO: Added by JADX */
            public static final int bg_msg_other = 0x7f06007b;

            /* JADX INFO: Added by JADX */
            public static final int bg_msg_other_normal = 0x7f06007c;

            /* JADX INFO: Added by JADX */
            public static final int bg_msg_other_pressed = 0x7f06007d;

            /* JADX INFO: Added by JADX */
            public static final int bg_music_action_delete = 0x7f06007e;

            /* JADX INFO: Added by JADX */
            public static final int bg_music_player_action_btn = 0x7f06007f;

            /* JADX INFO: Added by JADX */
            public static final int bg_music_player_action_btn_primary = 0x7f060080;

            /* JADX INFO: Added by JADX */
            public static final int bg_music_player_card = 0x7f060081;

            /* JADX INFO: Added by JADX */
            public static final int bg_music_player_chip = 0x7f060082;

            /* JADX INFO: Added by JADX */
            public static final int bg_music_player_control_circle = 0x7f060083;

            /* JADX INFO: Added by JADX */
            public static final int bg_music_player_control_circle_primary = 0x7f060084;

            /* JADX INFO: Added by JADX */
            public static final int bg_music_player_cover_inner = 0x7f060085;

            /* JADX INFO: Added by JADX */
            public static final int bg_music_player_cover_shell = 0x7f060086;

            /* JADX INFO: Added by JADX */
            public static final int bg_music_player_page = 0x7f060087;

            /* JADX INFO: Added by JADX */
            public static final int bg_music_player_seekbar = 0x7f060088;

            /* JADX INFO: Added by JADX */
            public static final int bg_music_player_seekbar_thumb = 0x7f060089;

            /* JADX INFO: Added by JADX */
            public static final int bg_music_player_topbar = 0x7f06008a;

            /* JADX INFO: Added by JADX */
            public static final int bg_music_plaza_card_selector = 0x7f06008b;

            /* JADX INFO: Added by JADX */
            public static final int bg_music_plaza_cover_frame = 0x7f06008c;

            /* JADX INFO: Added by JADX */
            public static final int bg_music_plaza_play_button = 0x7f06008d;

            /* JADX INFO: Added by JADX */
            public static final int bg_music_plaza_stat_chip = 0x7f06008e;

            /* JADX INFO: Added by JADX */
            public static final int bg_music_plaza_tag_neutral = 0x7f06008f;

            /* JADX INFO: Added by JADX */
            public static final int bg_music_plaza_tag_positive = 0x7f060090;

            /* JADX INFO: Added by JADX */
            public static final int bg_music_top_chip = 0x7f060091;

            /* JADX INFO: Added by JADX */
            public static final int bg_music_top_chip_primary = 0x7f060092;

            /* JADX INFO: Added by JADX */
            public static final int bg_new_message_bubble = 0x7f060093;

            /* JADX INFO: Added by JADX */
            public static final int bg_old_view_action_active = 0x7f060094;

            /* JADX INFO: Added by JADX */
            public static final int bg_old_view_action_inactive = 0x7f060095;

            /* JADX INFO: Added by JADX */
            public static final int bg_old_view_control_circle = 0x7f060096;

            /* JADX INFO: Added by JADX */
            public static final int bg_old_view_control_circle_primary = 0x7f060097;

            /* JADX INFO: Added by JADX */
            public static final int bg_old_view_detail_card = 0x7f060098;

            /* JADX INFO: Added by JADX */
            public static final int bg_old_view_detail_page = 0x7f060099;

            /* JADX INFO: Added by JADX */
            public static final int bg_old_view_detail_topbar = 0x7f06009a;

            /* JADX INFO: Added by JADX */
            public static final int bg_old_view_player_badge = 0x7f06009b;

            /* JADX INFO: Added by JADX */
            public static final int bg_old_view_player_shell = 0x7f06009c;

            /* JADX INFO: Added by JADX */
            public static final int bg_old_view_stat = 0x7f06009d;

            /* JADX INFO: Added by JADX */
            public static final int bg_old_view_tab_active = 0x7f06009e;

            /* JADX INFO: Added by JADX */
            public static final int bg_old_view_tab_inactive = 0x7f06009f;

            /* JADX INFO: Added by JADX */
            public static final int bg_pastel_chat_row = 0x7f0600a0;

            /* JADX INFO: Added by JADX */
            public static final int bg_pastel_circle_yellow = 0x7f0600a1;

            /* JADX INFO: Added by JADX */
            public static final int bg_pastel_dot_closed = 0x7f0600a2;

            /* JADX INFO: Added by JADX */
            public static final int bg_pastel_dot_open = 0x7f0600a3;

            /* JADX INFO: Added by JADX */
            public static final int bg_pastel_home_container = 0x7f0600a4;

            /* JADX INFO: Added by JADX */
            public static final int bg_pastel_pill_red = 0x7f0600a5;

            /* JADX INFO: Added by JADX */
            public static final int bg_pastel_pill_teal = 0x7f0600a6;

            /* JADX INFO: Added by JADX */
            public static final int bg_pastel_pill_yellow = 0x7f0600a7;

            /* JADX INFO: Added by JADX */
            public static final int bg_pastel_preview_bar = 0x7f0600a8;

            /* JADX INFO: Added by JADX */
            public static final int bg_pastel_section_card = 0x7f0600a9;

            /* JADX INFO: Added by JADX */
            public static final int bg_pastel_section_header = 0x7f0600aa;

            /* JADX INFO: Added by JADX */
            public static final int bg_pinned_badge = 0x7f0600ab;

            /* JADX INFO: Added by JADX */
            public static final int bg_primary_button = 0x7f0600ac;

            /* JADX INFO: Added by JADX */
            public static final int bg_profile_item = 0x7f0600ad;

            /* JADX INFO: Added by JADX */
            public static final int bg_quote_preview = 0x7f0600ae;

            /* JADX INFO: Added by JADX */
            public static final int bg_recent_chat_item = 0x7f0600af;

            /* JADX INFO: Added by JADX */
            public static final int bg_red_packet_icon_circle = 0x7f0600b0;

            /* JADX INFO: Added by JADX */
            public static final int bg_red_packet_in = 0x7f0600b1;

            /* JADX INFO: Added by JADX */
            public static final int bg_red_packet_in_dim = 0x7f0600b2;

            /* JADX INFO: Added by JADX */
            public static final int bg_red_packet_open_card = 0x7f0600b3;

            /* JADX INFO: Added by JADX */
            public static final int bg_red_packet_open_outline = 0x7f0600b4;

            /* JADX INFO: Added by JADX */
            public static final int bg_red_packet_open_primary = 0x7f0600b5;

            /* JADX INFO: Added by JADX */
            public static final int bg_red_packet_out = 0x7f0600b6;

            /* JADX INFO: Added by JADX */
            public static final int bg_red_packet_out_dim = 0x7f0600b7;

            /* JADX INFO: Added by JADX */
            public static final int bg_search_field = 0x7f0600b8;

            /* JADX INFO: Added by JADX */
            public static final int bg_search_filter_chip = 0x7f0600b9;

            /* JADX INFO: Added by JADX */
            public static final int bg_search_header = 0x7f0600ba;

            /* JADX INFO: Added by JADX */
            public static final int bg_search_type_image = 0x7f0600bb;

            /* JADX INFO: Added by JADX */
            public static final int bg_search_type_resource = 0x7f0600bc;

            /* JADX INFO: Added by JADX */
            public static final int bg_search_type_text = 0x7f0600bd;

            /* JADX INFO: Added by JADX */
            public static final int bg_search_type_video = 0x7f0600be;

            /* JADX INFO: Added by JADX */
            public static final int bg_search_type_voice = 0x7f0600bf;

            /* JADX INFO: Added by JADX */
            public static final int bg_settings_sub_item = 0x7f0600c0;

            /* JADX INFO: Added by JADX */
            public static final int bg_settings_sub_item_normal = 0x7f0600c1;

            /* JADX INFO: Added by JADX */
            public static final int bg_settings_sub_item_pressed = 0x7f0600c2;

            /* JADX INFO: Added by JADX */
            public static final int bg_space_back_button = 0x7f0600c3;

            /* JADX INFO: Added by JADX */
            public static final int bg_tab_item = 0x7f0600c4;

            /* JADX INFO: Added by JADX */
            public static final int bg_time_label = 0x7f0600c5;

            /* JADX INFO: Added by JADX */
            public static final int bg_typing_dot = 0x7f0600c6;

            /* JADX INFO: Added by JADX */
            public static final int bg_unread_badge = 0x7f0600c7;

            /* JADX INFO: Added by JADX */
            public static final int bg_unread_dot = 0x7f0600c8;

            /* JADX INFO: Added by JADX */
            public static final int bg_voice_chip_normal = 0x7f0600c9;

            /* JADX INFO: Added by JADX */
            public static final int bg_voice_chip_pressed = 0x7f0600ca;

            /* JADX INFO: Added by JADX */
            public static final int bg_wechat_icon_btn = 0x7f0600cb;

            /* JADX INFO: Added by JADX */
            public static final int button_outline_danger = 0x7f0600cc;

            /* JADX INFO: Added by JADX */
            public static final int button_outline_primary = 0x7f0600cd;

            /* JADX INFO: Added by JADX */
            public static final int chatting_setmode_keyboard_btn_normal = 0x7f0600d3;

            /* JADX INFO: Added by JADX */
            public static final int chatting_setmode_voice_btn_normal = 0x7f0600d5;

            /* JADX INFO: Added by JADX */
            public static final int default_user = 0x7f0600dc;

            /* JADX INFO: Added by JADX */
            public static final int edittext_login = 0x7f0600dd;

            /* JADX INFO: Added by JADX */
            public static final int fab_menu_bg = 0x7f0600df;

            /* JADX INFO: Added by JADX */
            public static final int flat_button_accent_bg = 0x7f0600e2;

            /* JADX INFO: Added by JADX */
            public static final int flat_button_accent_bg_normal = 0x7f0600e3;

            /* JADX INFO: Added by JADX */
            public static final int flat_button_accent_bg_pressed = 0x7f0600e4;

            /* JADX INFO: Added by JADX */
            public static final int flat_button_bg = 0x7f0600e5;

            /* JADX INFO: Added by JADX */
            public static final int flat_button_bg_normal = 0x7f0600e6;

            /* JADX INFO: Added by JADX */
            public static final int flat_button_bg_pressed = 0x7f0600e7;

            /* JADX INFO: Added by JADX */
            public static final int flat_button_danger_bg = 0x7f0600e8;

            /* JADX INFO: Added by JADX */
            public static final int flat_button_danger_bg_normal = 0x7f0600e9;

            /* JADX INFO: Added by JADX */
            public static final int flat_button_danger_bg_pressed = 0x7f0600ea;

            /* JADX INFO: Added by JADX */
            public static final int flat_input_bg = 0x7f0600eb;

            /* JADX INFO: Added by JADX */
            public static final int friend_request_accept_bg = 0x7f0600ec;

            /* JADX INFO: Added by JADX */
            public static final int friend_request_reject_bg = 0x7f0600ed;

            /* JADX INFO: Added by JADX */
            public static final int group = 0x7f0600ee;

            /* JADX INFO: Added by JADX */
            public static final int ic_action_announcement = 0x7f0600ef;

            /* JADX INFO: Added by JADX */
            public static final int ic_action_emoji = 0x7f0600f0;

            /* JADX INFO: Added by JADX */
            public static final int ic_action_image = 0x7f0600f1;

            /* JADX INFO: Added by JADX */
            public static final int ic_action_red_packet = 0x7f0600f2;

            /* JADX INFO: Added by JADX */
            public static final int ic_action_video = 0x7f0600f3;

            /* JADX INFO: Added by JADX */
            public static final int ic_avatar_placeholder = 0x7f0600f5;

            /* JADX INFO: Added by JADX */
            public static final int ic_chevron_right = 0x7f0600f7;

            /* JADX INFO: Added by JADX */
            public static final int ic_clean = 0x7f0600f8;

            /* JADX INFO: Added by JADX */
            public static final int ic_comment = 0x7f0600fa;

            /* JADX INFO: Added by JADX */
            public static final int ic_discover_emoji_plaza = 0x7f0600fb;

            /* JADX INFO: Added by JADX */
            public static final int ic_discover_fating = 0x7f0600fc;

            /* JADX INFO: Added by JADX */
            public static final int ic_discover_moments = 0x7f0600fd;

            /* JADX INFO: Added by JADX */
            public static final int ic_discover_music = 0x7f0600fe;

            /* JADX INFO: Added by JADX */
            public static final int ic_discover_old_view = 0x7f0600ff;

            /* JADX INFO: Added by JADX */
            public static final int ic_discover_qiandao = 0x7f060100;

            /* JADX INFO: Added by JADX */
            public static final int ic_discover_report = 0x7f060101;

            /* JADX INFO: Added by JADX */
            public static final int ic_document = 0x7f060102;

            /* JADX INFO: Added by JADX */
            public static final int ic_download = 0x7f060103;

            /* JADX INFO: Added by JADX */
            public static final int ic_feedback = 0x7f060104;

            /* JADX INFO: Added by JADX */
            public static final int ic_group_add = 0x7f060105;

            /* JADX INFO: Added by JADX */
            public static final int ic_image = 0x7f060106;

            /* JADX INFO: Added by JADX */
            public static final int ic_like = 0x7f060107;

            /* JADX INFO: Added by JADX */
            public static final int ic_like_filled = 0x7f060108;

            /* JADX INFO: Added by JADX */
            public static final int ic_lock = 0x7f060109;

            /* JADX INFO: Added by JADX */
            public static final int ic_logout = 0x7f06010a;

            /* JADX INFO: Added by JADX */
            public static final int ic_msg_read = 0x7f06010e;

            /* JADX INFO: Added by JADX */
            public static final int ic_msg_sent = 0x7f06010f;

            /* JADX INFO: Added by JADX */
            public static final int ic_nav_back = 0x7f060110;

            /* JADX INFO: Added by JADX */
            public static final int ic_notification = 0x7f060111;

            /* JADX INFO: Added by JADX */
            public static final int ic_person_add = 0x7f060112;

            /* JADX INFO: Added by JADX */
            public static final int ic_red_packet = 0x7f060113;

            /* JADX INFO: Added by JADX */
            public static final int ic_settings = 0x7f060114;

            /* JADX INFO: Added by JADX */
            public static final int ic_storage = 0x7f060116;

            /* JADX INFO: Added by JADX */
            public static final int ic_tab_chat_alt = 0x7f060117;

            /* JADX INFO: Added by JADX */
            public static final int ic_tab_chat_alt_normal = 0x7f060118;

            /* JADX INFO: Added by JADX */
            public static final int ic_tab_chat_alt_selected = 0x7f060119;

            /* JADX INFO: Added by JADX */
            public static final int ic_tab_friends = 0x7f06011a;

            /* JADX INFO: Added by JADX */
            public static final int ic_tab_friends_normal = 0x7f06011b;

            /* JADX INFO: Added by JADX */
            public static final int ic_tab_friends_selected = 0x7f06011c;

            /* JADX INFO: Added by JADX */
            public static final int ic_tab_profile = 0x7f06011d;

            /* JADX INFO: Added by JADX */
            public static final int ic_tab_profile_normal = 0x7f06011e;

            /* JADX INFO: Added by JADX */
            public static final int ic_tab_profile_selected = 0x7f06011f;

            /* JADX INFO: Added by JADX */
            public static final int ic_voice = 0x7f060120;

            /* JADX INFO: Added by JADX */
            public static final int icon_chat_plus = 0x7f060121;

            /* JADX INFO: Added by JADX */
            public static final int moment_action_bg = 0x7f060123;

            /* JADX INFO: Added by JADX */
            public static final int moment_action_transparent = 0x7f060124;

            /* JADX INFO: Added by JADX */
            public static final int msg_state_fail_resend = 0x7f060125;

            /* JADX INFO: Added by JADX */
            public static final int news = 0x7f060126;

            /* JADX INFO: Added by JADX */
            public static final int news_normal = 0x7f060127;

            /* JADX INFO: Added by JADX */
            public static final int news_selected = 0x7f060128;

            /* JADX INFO: Added by JADX */
            public static final int notification_template_icon_bg = 0x7f060129;

            /* JADX INFO: Added by JADX */
            public static final int profile_normal = 0x7f06012a;

            /* JADX INFO: Added by JADX */
            public static final int profile_pressed = 0x7f06012b;

            /* JADX INFO: Added by JADX */
            public static final int read = 0x7f06012c;

            /* JADX INFO: Added by JADX */
            public static final int right = 0x7f06012e;

            /* JADX INFO: Added by JADX */
            public static final int sent = 0x7f06012f;

            /* JADX INFO: Added by JADX */
            public static final int signal_station = 0x7f060130;

            /* JADX INFO: Added by JADX */
            public static final int star = 0x7f060133;

            /* JADX INFO: Added by JADX */
            public static final int unread_badge_bg = 0x7f060134;

            /* JADX INFO: Added by JADX */
            public static final int usershead = 0x7f060135;
        }

        /* JADX INFO: Added by JADX */
        public static final class id {

            /* JADX INFO: Added by JADX */
            public static final int action0 = 0x7f070000;

            /* JADX INFO: Added by JADX */
            public static final int action_bar = 0x7f070001;

            /* JADX INFO: Added by JADX */
            public static final int action_bar_activity_content = 0x7f070002;

            /* JADX INFO: Added by JADX */
            public static final int action_bar_container = 0x7f070003;

            /* JADX INFO: Added by JADX */
            public static final int action_bar_root = 0x7f070004;

            /* JADX INFO: Added by JADX */
            public static final int action_bar_spinner = 0x7f070005;

            /* JADX INFO: Added by JADX */
            public static final int action_bar_subtitle = 0x7f070006;

            /* JADX INFO: Added by JADX */
            public static final int action_bar_title = 0x7f070007;

            /* JADX INFO: Added by JADX */
            public static final int action_context_bar = 0x7f070008;

            /* JADX INFO: Added by JADX */
            public static final int action_divider = 0x7f070009;

            /* JADX INFO: Added by JADX */
            public static final int action_favorites = 0x7f07000a;

            /* JADX INFO: Added by JADX */
            public static final int action_history = 0x7f07000b;

            /* JADX INFO: Added by JADX */
            public static final int action_login = 0x7f07000c;

            /* JADX INFO: Added by JADX */
            public static final int action_logout = 0x7f07000d;

            /* JADX INFO: Added by JADX */
            public static final int action_menu_divider = 0x7f07000e;

            /* JADX INFO: Added by JADX */
            public static final int action_menu_presenter = 0x7f07000f;

            /* JADX INFO: Added by JADX */
            public static final int action_mode_bar = 0x7f070010;

            /* JADX INFO: Added by JADX */
            public static final int action_mode_bar_stub = 0x7f070011;

            /* JADX INFO: Added by JADX */
            public static final int action_mode_close_button = 0x7f070012;

            /* JADX INFO: Added by JADX */
            public static final int action_panel = 0x7f070013;

            /* JADX INFO: Added by JADX */
            public static final int action_resource_delete = 0x7f070014;

            /* JADX INFO: Added by JADX */
            public static final int action_resource_download = 0x7f070015;

            /* JADX INFO: Added by JADX */
            public static final int action_resource_favorite = 0x7f070016;

            /* JADX INFO: Added by JADX */
            public static final int action_resource_report = 0x7f070017;

            /* JADX INFO: Added by JADX */
            public static final int activity_chooser_view_content = 0x7f070018;

            /* JADX INFO: Added by JADX */
            public static final int add = 0x7f070019;

            /* JADX INFO: Added by JADX */
            public static final int alertTitle = 0x7f07001a;

            /* JADX INFO: Added by JADX */
            public static final int always = 0x7f07001b;

            /* JADX INFO: Added by JADX */
            public static final int beginning = 0x7f07001c;

            /* JADX INFO: Added by JADX */
            public static final int bottom = 0x7f07001d;

            /* JADX INFO: Added by JADX */
            public static final int btnAboutApp = 0x7f07001e;

            /* JADX INFO: Added by JADX */
            public static final int btnAccept = 0x7f07001f;

            /* JADX INFO: Added by JADX */
            public static final int btnAccountBack = 0x7f070020;

            /* JADX INFO: Added by JADX */
            public static final int btnAccountManagement = 0x7f070021;

            /* JADX INFO: Added by JADX */
            public static final int btnActionEmoji = 0x7f070022;

            /* JADX INFO: Added by JADX */
            public static final int btnActionFile = 0x7f070023;

            /* JADX INFO: Added by JADX */
            public static final int btnActionImage = 0x7f070024;

            /* JADX INFO: Added by JADX */
            public static final int btnActionRedPacket = 0x7f070025;

            /* JADX INFO: Added by JADX */
            public static final int btnActionVideo = 0x7f070026;

            /* JADX INFO: Added by JADX */
            public static final int btnAddFriendBack = 0x7f070027;

            /* JADX INFO: Added by JADX */
            public static final int btnAdmin = 0x7f070028;

            /* JADX INFO: Added by JADX */
            public static final int btnApprove = 0x7f070029;

            /* JADX INFO: Added by JADX */
            public static final int btnBack = 0x7f07002a;

            /* JADX INFO: Added by JADX */
            public static final int btnCacheBack = 0x7f07002b;

            /* JADX INFO: Added by JADX */
            public static final int btnCacheSettings = 0x7f07002c;

            /* JADX INFO: Added by JADX */
            public static final int btnChangeAvatar = 0x7f07002d;

            /* JADX INFO: Added by JADX */
            public static final int btnChangePassword = 0x7f07002e;

            /* JADX INFO: Added by JADX */
            public static final int btnChangePasswordBack = 0x7f07002f;

            /* JADX INFO: Added by JADX */
            public static final int btnChangeUID = 0x7f070030;

            /* JADX INFO: Added by JADX */
            public static final int btnChatBack = 0x7f070031;

            /* JADX INFO: Added by JADX */
            public static final int btnChatBackground = 0x7f070032;

            /* JADX INFO: Added by JADX */
            public static final int btnChatHeaderAdd = 0x7f070033;

            /* JADX INFO: Added by JADX */
            public static final int btnChatMenu = 0x7f070034;

            /* JADX INFO: Added by JADX */
            public static final int btnCheckUpdate = 0x7f070035;

            /* JADX INFO: Added by JADX */
            public static final int btnCleanupUnknownDevices = 0x7f070036;

            /* JADX INFO: Added by JADX */
            public static final int btnClearAvatarCache = 0x7f070037;

            /* JADX INFO: Added by JADX */
            public static final int btnClearAvatarMetaCache = 0x7f070038;

            /* JADX INFO: Added by JADX */
            public static final int btnClearChatCache = 0x7f070039;

            /* JADX INFO: Added by JADX */
            public static final int btnClearFriendCache = 0x7f07003a;

            /* JADX INFO: Added by JADX */
            public static final int btnClearFriendSearch = 0x7f07003b;

            /* JADX INFO: Added by JADX */
            public static final int btnClearGroupCache = 0x7f07003c;

            /* JADX INFO: Added by JADX */
            public static final int btnClearImageCache = 0x7f07003d;

            /* JADX INFO: Added by JADX */
            public static final int btnClearMomentCache = 0x7f07003e;

            /* JADX INFO: Added by JADX */
            public static final int btnClearRecentCache = 0x7f07003f;

            /* JADX INFO: Added by JADX */
            public static final int btnClearUserCache = 0x7f070040;

            /* JADX INFO: Added by JADX */
            public static final int btnClearVoiceCache = 0x7f070041;

            /* JADX INFO: Added by JADX */
            public static final int btnCloseApp = 0x7f070042;

            /* JADX INFO: Added by JADX */
            public static final int btnCommentsBack = 0x7f070043;

            /* JADX INFO: Added by JADX */
            public static final int btnCopyGroupId = 0x7f070044;

            /* JADX INFO: Added by JADX */
            public static final int btnCopyLog = 0x7f070045;

            /* JADX INFO: Added by JADX */
            public static final int btnCreateGroup = 0x7f070046;

            /* JADX INFO: Added by JADX */
            public static final int btnDataSettings = 0x7f070047;

            /* JADX INFO: Added by JADX */
            public static final int btnDataSettingsBack = 0x7f070048;

            /* JADX INFO: Added by JADX */
            public static final int btnDeepClearMediaCache = 0x7f070049;

            /* JADX INFO: Added by JADX */
            public static final int btnDeleteAccount = 0x7f07004a;

            /* JADX INFO: Added by JADX */
            public static final int btnDeleteEmoji = 0x7f07004b;

            /* JADX INFO: Added by JADX */
            public static final int btnDeleteFriend = 0x7f07004c;

            /* JADX INFO: Added by JADX */
            public static final int btnDetail = 0x7f07004d;

            /* JADX INFO: Added by JADX */
            public static final int btnDeviceBack = 0x7f07004e;

            /* JADX INFO: Added by JADX */
            public static final int btnDeviceManagement = 0x7f07004f;

            /* JADX INFO: Added by JADX */
            public static final int btnDiscoverCheckIn = 0x7f070050;

            /* JADX INFO: Added by JADX */
            public static final int btnDiscoverEmoji = 0x7f070051;

            /* JADX INFO: Added by JADX */
            public static final int btnDiscoverMoments = 0x7f070052;

            /* JADX INFO: Added by JADX */
            public static final int btnDiscoverMusic = 0x7f070053;

            /* JADX INFO: Added by JADX */
            public static final int btnDiscoverOldView = 0x7f070054;

            /* JADX INFO: Added by JADX */
            public static final int btnDiscoverPublicCourt = 0x7f070055;

            /* JADX INFO: Added by JADX */
            public static final int btnDiscoverReport = 0x7f070056;

            /* JADX INFO: Added by JADX */
            public static final int btnDiscoverSettings = 0x7f070057;

            /* JADX INFO: Added by JADX */
            public static final int btnDiscoverSettingsBack = 0x7f070058;

            /* JADX INFO: Added by JADX */
            public static final int btnDiscussionSend = 0x7f070059;

            /* JADX INFO: Added by JADX */
            public static final int btnEmojiAdd = 0x7f07005a;

            /* JADX INFO: Added by JADX */
            public static final int btnEmojiBack = 0x7f07005b;

            /* JADX INFO: Added by JADX */
            public static final int btnEmojiBatchCancel = 0x7f07005c;

            /* JADX INFO: Added by JADX */
            public static final int btnEmojiBatchDelete = 0x7f07005d;

            /* JADX INFO: Added by JADX */
            public static final int btnEmojiBatchMove = 0x7f07005e;

            /* JADX INFO: Added by JADX */
            public static final int btnEmojiManage = 0x7f07005f;

            /* JADX INFO: Added by JADX */
            public static final int btnEmojiPlazaBack = 0x7f070060;

            /* JADX INFO: Added by JADX */
            public static final int btnEmojiPlazaDelete = 0x7f070061;

            /* JADX INFO: Added by JADX */
            public static final int btnEmojiPlazaNext = 0x7f070062;

            /* JADX INFO: Added by JADX */
            public static final int btnEmojiPlazaPrev = 0x7f070063;

            /* JADX INFO: Added by JADX */
            public static final int btnEmojiPlazaSave = 0x7f070064;

            /* JADX INFO: Added by JADX */
            public static final int btnEmojiPlazaSearch = 0x7f070065;

            /* JADX INFO: Added by JADX */
            public static final int btnEmojiPlazaTabAll = 0x7f070066;

            /* JADX INFO: Added by JADX */
            public static final int btnEmojiPlazaTabMine = 0x7f070067;

            /* JADX INFO: Added by JADX */
            public static final int btnEmojiPlazaUpload = 0x7f070068;

            /* JADX INFO: Added by JADX */
            public static final int btnExportEmojis = 0x7f070069;

            /* JADX INFO: Added by JADX */
            public static final int btnExportLogs = 0x7f07006a;

            /* JADX INFO: Added by JADX */
            public static final int btnFavoriteDelete = 0x7f07006b;

            /* JADX INFO: Added by JADX */
            public static final int btnFavoriteOpen = 0x7f07006c;

            /* JADX INFO: Added by JADX */
            public static final int btnFavoritesBack = 0x7f07006d;

            /* JADX INFO: Added by JADX */
            public static final int btnFavoritesRefresh = 0x7f07006e;

            /* JADX INFO: Added by JADX */
            public static final int btnFeedback = 0x7f07006f;

            /* JADX INFO: Added by JADX */
            public static final int btnFilterAll = 0x7f070070;

            /* JADX INFO: Added by JADX */
            public static final int btnFilterMedia = 0x7f070071;

            /* JADX INFO: Added by JADX */
            public static final int btnFilterText = 0x7f070072;

            /* JADX INFO: Added by JADX */
            public static final int btnForwardMultiCancel = 0x7f070073;

            /* JADX INFO: Added by JADX */
            public static final int btnForwardMultiSend = 0x7f070074;

            /* JADX INFO: Added by JADX */
            public static final int btnGalleryBack = 0x7f070075;

            /* JADX INFO: Added by JADX */
            public static final int btnGroupAnnouncement = 0x7f070076;

            /* JADX INFO: Added by JADX */
            public static final int btnGroupAnnouncementArrowDown = 0x7f070077;

            /* JADX INFO: Added by JADX */
            public static final int btnGroupAnnouncementArrowUp = 0x7f070078;

            /* JADX INFO: Added by JADX */
            public static final int btnGroupAnnouncementBack = 0x7f070079;

            /* JADX INFO: Added by JADX */
            public static final int btnGroupAnnouncementDelete = 0x7f07007a;

            /* JADX INFO: Added by JADX */
            public static final int btnGroupAnnouncementPrimary = 0x7f07007b;

            /* JADX INFO: Added by JADX */
            public static final int btnGroupChatBack = 0x7f07007c;

            /* JADX INFO: Added by JADX */
            public static final int btnGroupCreateBack = 0x7f07007d;

            /* JADX INFO: Added by JADX */
            public static final int btnGroupManage = 0x7f07007e;

            /* JADX INFO: Added by JADX */
            public static final int btnGroupManageBack = 0x7f07007f;

            /* JADX INFO: Added by JADX */
            public static final int btnGroupRequestsBack = 0x7f070080;

            /* JADX INFO: Added by JADX */
            public static final int btnHoldToTalk = 0x7f070081;

            /* JADX INFO: Added by JADX */
            public static final int btnImportEmojis = 0x7f070082;

            /* JADX INFO: Added by JADX */
            public static final int btnInvite = 0x7f070083;

            /* JADX INFO: Added by JADX */
            public static final int btnInviteBack = 0x7f070084;

            /* JADX INFO: Added by JADX */
            public static final int btnInviteMember = 0x7f070085;

            /* JADX INFO: Added by JADX */
            public static final int btnJoinGroup = 0x7f070086;

            /* JADX INFO: Added by JADX */
            public static final int btnJoinRequests = 0x7f070087;

            /* JADX INFO: Added by JADX */
            public static final int btnJumpToUnread = 0x7f070088;

            /* JADX INFO: Added by JADX */
            public static final int btnKick = 0x7f070089;

            /* JADX INFO: Added by JADX */
            public static final int btnLeaveGroup = 0x7f07008a;

            /* JADX INFO: Added by JADX */
            public static final int btnLoadMore = 0x7f07008b;

            /* JADX INFO: Added by JADX */
            public static final int btnLogin = 0x7f07008c;

            /* JADX INFO: Added by JADX */
            public static final int btnLogout = 0x7f07008d;

            /* JADX INFO: Added by JADX */
            public static final int btnMergedReportNext = 0x7f07008e;

            /* JADX INFO: Added by JADX */
            public static final int btnMergedReportPrev = 0x7f07008f;

            /* JADX INFO: Added by JADX */
            public static final int btnMomentBack = 0x7f070090;

            /* JADX INFO: Added by JADX */
            public static final int btnMomentComment = 0x7f070091;

            /* JADX INFO: Added by JADX */
            public static final int btnMomentLike = 0x7f070092;

            /* JADX INFO: Added by JADX */
            public static final int btnMomentsAdd = 0x7f070093;

            /* JADX INFO: Added by JADX */
            public static final int btnMomentsBack = 0x7f070094;

            /* JADX INFO: Added by JADX */
            public static final int btnMomentsNotice = 0x7f070095;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicDownloadsBack = 0x7f070096;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicDownloadsRefresh = 0x7f070097;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicManageBack = 0x7f070098;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicManageBatchDelete = 0x7f070099;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicManageDelete = 0x7f07009a;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicManageMore = 0x7f07009b;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicManageNext = 0x7f07009c;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicManagePlay = 0x7f07009d;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicManagePrev = 0x7f07009e;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicManageSearch = 0x7f07009f;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicPlayerBack = 0x7f0700a0;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicPlayerComments = 0x7f0700a1;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicPlayerDownload = 0x7f0700a2;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicPlayerFastForward = 0x7f0700a3;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicPlayerLike = 0x7f0700a4;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicPlayerPlayPause = 0x7f0700a5;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicPlayerRepeatOne = 0x7f0700a6;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicPlayerStop = 0x7f0700a7;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicPlazaBack = 0x7f0700a8;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicPlazaDownloads = 0x7f0700a9;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicPlazaFeaturedPlay = 0x7f0700aa;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicPlazaFilterAll = 0x7f0700ab;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicPlazaFilterMine = 0x7f0700ac;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicPlazaMineManage = 0x7f0700ad;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicPlazaMore = 0x7f0700ae;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicPlazaNext = 0x7f0700af;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicPlazaNowPlaying = 0x7f0700b0;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicPlazaOpenAllList = 0x7f0700b1;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicPlazaPlay = 0x7f0700b2;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicPlazaPrev = 0x7f0700b3;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicPlazaSearch = 0x7f0700b4;

            /* JADX INFO: Added by JADX */
            public static final int btnMusicPlazaUpload = 0x7f0700b5;

            /* JADX INFO: Added by JADX */
            public static final int btnMute = 0x7f0700b6;

            /* JADX INFO: Added by JADX */
            public static final int btnMyFavorites = 0x7f0700b7;

            /* JADX INFO: Added by JADX */
            public static final int btnMySpace = 0x7f0700b8;

            /* JADX INFO: Added by JADX */
            public static final int btnNewMessage = 0x7f0700b9;

            /* JADX INFO: Added by JADX */
            public static final int btnNoticeBack = 0x7f0700ba;

            /* JADX INFO: Added by JADX */
            public static final int btnNotificationSettings = 0x7f0700bb;

            /* JADX INFO: Added by JADX */
            public static final int btnNotificationSettingsBack = 0x7f0700bc;

            /* JADX INFO: Added by JADX */
            public static final int btnOldViewBack = 0x7f0700bd;

            /* JADX INFO: Added by JADX */
            public static final int btnOldViewCommentSend = 0x7f0700be;

            /* JADX INFO: Added by JADX */
            public static final int btnOldViewDetailBack = 0x7f0700bf;

            /* JADX INFO: Added by JADX */
            public static final int btnOldViewDetailShare = 0x7f0700c0;

            /* JADX INFO: Added by JADX */
            public static final int btnOldViewFullBack = 0x7f0700c1;

            /* JADX INFO: Added by JADX */
            public static final int btnOldViewFullscreen = 0x7f0700c2;

            /* JADX INFO: Added by JADX */
            public static final int btnOldViewListBack = 0x7f0700c3;

            /* JADX INFO: Added by JADX */
            public static final int btnOldViewMenu = 0x7f0700c4;

            /* JADX INFO: Added by JADX */
            public static final int btnOldViewPlay = 0x7f0700c5;

            /* JADX INFO: Added by JADX */
            public static final int btnOldViewQuickFavorites = 0x7f0700c6;

            /* JADX INFO: Added by JADX */
            public static final int btnOldViewQuickHistory = 0x7f0700c7;

            /* JADX INFO: Added by JADX */
            public static final int btnOldViewQuickSearch = 0x7f0700c8;

            /* JADX INFO: Added by JADX */
            public static final int btnOldViewRefresh = 0x7f0700c9;

            /* JADX INFO: Added by JADX */
            public static final int btnOldViewRetry = 0x7f0700ca;

            /* JADX INFO: Added by JADX */
            public static final int btnOldViewSearch = 0x7f0700cb;

            /* JADX INFO: Added by JADX */
            public static final int btnOldViewUpProfile = 0x7f0700cc;

            /* JADX INFO: Added by JADX */
            public static final int btnOldViewUpProfileBack = 0x7f0700cd;

            /* JADX INFO: Added by JADX */
            public static final int btnOldViewUpProfileFollow = 0x7f0700ce;

            /* JADX INFO: Added by JADX */
            public static final int btnOldViewVideoFavorite = 0x7f0700cf;

            /* JADX INFO: Added by JADX */
            public static final int btnOldViewVideoLike = 0x7f0700d0;

            /* JADX INFO: Added by JADX */
            public static final int btnOpenMembersPage = 0x7f0700d1;

            /* JADX INFO: Added by JADX */
            public static final int btnOpenRedPacket = 0x7f0700d2;

            /* JADX INFO: Added by JADX */
            public static final int btnPickCover = 0x7f0700d3;

            /* JADX INFO: Added by JADX */
            public static final int btnPickEvidence = 0x7f0700d4;

            /* JADX INFO: Added by JADX */
            public static final int btnPickMomentImage = 0x7f0700d5;

            /* JADX INFO: Added by JADX */
            public static final int btnPlus = 0x7f0700d6;

            /* JADX INFO: Added by JADX */
            public static final int btnPreviewBack = 0x7f0700d7;

            /* JADX INFO: Added by JADX */
            public static final int btnPrivacyBack = 0x7f0700d8;

            /* JADX INFO: Added by JADX */
            public static final int btnPrivacyPolicy = 0x7f0700d9;

            /* JADX INFO: Added by JADX */
            public static final int btnProfileEditBack = 0x7f0700da;

            /* JADX INFO: Added by JADX */
            public static final int btnPublicCourtSetting = 0x7f0700db;

            /* JADX INFO: Added by JADX */
            public static final int btnPublishMoment = 0x7f0700dc;

            /* JADX INFO: Added by JADX */
            public static final int btnQrCardBack = 0x7f0700dd;

            /* JADX INFO: Added by JADX */
            public static final int btnQrScanBack = 0x7f0700de;

            /* JADX INFO: Added by JADX */
            public static final int btnQuickClearImageCache = 0x7f0700df;

            /* JADX INFO: Added by JADX */
            public static final int btnQuoteClose = 0x7f0700e0;

            /* JADX INFO: Added by JADX */
            public static final int btnRecoverBack = 0x7f0700e1;

            /* JADX INFO: Added by JADX */
            public static final int btnRecoverPassword = 0x7f0700e2;

            /* JADX INFO: Added by JADX */
            public static final int btnRedPacketClearCover = 0x7f0700e3;

            /* JADX INFO: Added by JADX */
            public static final int btnRedPacketDetail = 0x7f0700e4;

            /* JADX INFO: Added by JADX */
            public static final int btnRedPacketDetailBack = 0x7f0700e5;

            /* JADX INFO: Added by JADX */
            public static final int btnRedPacketOpenBack = 0x7f0700e6;

            /* JADX INFO: Added by JADX */
            public static final int btnRedPacketPickCover = 0x7f0700e7;

            /* JADX INFO: Added by JADX */
            public static final int btnRedPacketSendBack = 0x7f0700e8;

            /* JADX INFO: Added by JADX */
            public static final int btnRefresh = 0x7f0700e9;

            /* JADX INFO: Added by JADX */
            public static final int btnRegister = 0x7f0700ea;

            /* JADX INFO: Added by JADX */
            public static final int btnRegisterBack = 0x7f0700eb;

            /* JADX INFO: Added by JADX */
            public static final int btnReject = 0x7f0700ec;

            /* JADX INFO: Added by JADX */
            public static final int btnRemoveImage = 0x7f0700ed;

            /* JADX INFO: Added by JADX */
            public static final int btnReportGroup = 0x7f0700ee;

            /* JADX INFO: Added by JADX */
            public static final int btnRequestPermissions = 0x7f0700ef;

            /* JADX INFO: Added by JADX */
            public static final int btnResetPassword = 0x7f0700f0;

            /* JADX INFO: Added by JADX */
            public static final int btnResourceComment = 0x7f0700f1;

            /* JADX INFO: Added by JADX */
            public static final int btnResourceLike = 0x7f0700f2;

            /* JADX INFO: Added by JADX */
            public static final int btnResourceMore = 0x7f0700f3;

            /* JADX INFO: Added by JADX */
            public static final int btnResourceSearch = 0x7f0700f4;

            /* JADX INFO: Added by JADX */
            public static final int btnResourceSectionBack = 0x7f0700f5;

            /* JADX INFO: Added by JADX */
            public static final int btnResourceSectionsAdd = 0x7f0700f6;

            /* JADX INFO: Added by JADX */
            public static final int btnResourceSectionsBack = 0x7f0700f7;

            /* JADX INFO: Added by JADX */
            public static final int btnResourceUpload = 0x7f0700f8;

            /* JADX INFO: Added by JADX */
            public static final int btnSaveProfile = 0x7f0700f9;

            /* JADX INFO: Added by JADX */
            public static final int btnSaveQrCard = 0x7f0700fa;

            /* JADX INFO: Added by JADX */
            public static final int btnSaveSpace = 0x7f0700fb;

            /* JADX INFO: Added by JADX */
            public static final int btnScanFromCamera = 0x7f0700fc;

            /* JADX INFO: Added by JADX */
            public static final int btnScanFromGallery = 0x7f0700fd;

            /* JADX INFO: Added by JADX */
            public static final int btnScanQr = 0x7f0700fe;

            /* JADX INFO: Added by JADX */
            public static final int btnSearchAction = 0x7f0700ff;

            /* JADX INFO: Added by JADX */
            public static final int btnSearchBack = 0x7f070100;

            /* JADX INFO: Added by JADX */
            public static final int btnSearchClear = 0x7f070101;

            /* JADX INFO: Added by JADX */
            public static final int btnSearchLoadMore = 0x7f070102;

            /* JADX INFO: Added by JADX */
            public static final int btnSearchMessages = 0x7f070103;

            /* JADX INFO: Added by JADX */
            public static final int btnSend = 0x7f070104;

            /* JADX INFO: Added by JADX */
            public static final int btnSendComment = 0x7f070105;

            /* JADX INFO: Added by JADX */
            public static final int btnSendEmailCode = 0x7f070106;

            /* JADX INFO: Added by JADX */
            public static final int btnSendRecoverCode = 0x7f070107;

            /* JADX INFO: Added by JADX */
            public static final int btnSendRedPacket = 0x7f070108;

            /* JADX INFO: Added by JADX */
            public static final int btnSetRemark = 0x7f070109;

            /* JADX INFO: Added by JADX */
            public static final int btnSettings = 0x7f07010a;

            /* JADX INFO: Added by JADX */
            public static final int btnSettingsBack = 0x7f07010b;

            /* JADX INFO: Added by JADX */
            public static final int btnSpaceBack = 0x7f07010c;

            /* JADX INFO: Added by JADX */
            public static final int btnSpaceEdit = 0x7f07010d;

            /* JADX INFO: Added by JADX */
            public static final int btnSpaceEditBack = 0x7f07010e;

            /* JADX INFO: Added by JADX */
            public static final int btnSpacePost = 0x7f07010f;

            /* JADX INFO: Added by JADX */
            public static final int btnSpacePrimary = 0x7f070110;

            /* JADX INFO: Added by JADX */
            public static final int btnSpaceReport = 0x7f070111;

            /* JADX INFO: Added by JADX */
            public static final int btnStatement = 0x7f070112;

            /* JADX INFO: Added by JADX */
            public static final int btnSubmit = 0x7f070113;

            /* JADX INFO: Added by JADX */
            public static final int btnSubmitReport = 0x7f070114;

            /* JADX INFO: Added by JADX */
            public static final int btnSupportSettings = 0x7f070115;

            /* JADX INFO: Added by JADX */
            public static final int btnSupportSettingsBack = 0x7f070116;

            /* JADX INFO: Added by JADX */
            public static final int btnToggleVoice = 0x7f070117;

            /* JADX INFO: Added by JADX */
            public static final int btnUiSettings = 0x7f070118;

            /* JADX INFO: Added by JADX */
            public static final int btnUiSettingsBack = 0x7f070119;

            /* JADX INFO: Added by JADX */
            public static final int btnVoteBan = 0x7f07011a;

            /* JADX INFO: Added by JADX */
            public static final int btnVoteKeep = 0x7f07011b;

            /* JADX INFO: Added by JADX */
            public static final int btnWithdraw = 0x7f07011c;

            /* JADX INFO: Added by JADX */
            public static final int buttonPanel = 0x7f07011d;

            /* JADX INFO: Added by JADX */
            public static final int cancel_action = 0x7f07011e;

            /* JADX INFO: Added by JADX */
            public static final int cbInviteFriend = 0x7f07011f;

            /* JADX INFO: Added by JADX */
            public static final int cbPrivacyAgree = 0x7f070120;

            /* JADX INFO: Added by JADX */
            public static final int cbPublicCourtEnabled = 0x7f070121;

            /* JADX INFO: Added by JADX */
            public static final int chat_background = 0x7f070122;

            /* JADX INFO: Added by JADX */
            public static final int chat_header = 0x7f070123;

            /* JADX INFO: Added by JADX */
            public static final int chat_toolbar = 0x7f070124;

            /* JADX INFO: Added by JADX */
            public static final int checkbox = 0x7f070125;

            /* JADX INFO: Added by JADX */
            public static final int chronometer = 0x7f070126;

            /* JADX INFO: Added by JADX */
            public static final int collapseActionView = 0x7f070127;

            /* JADX INFO: Added by JADX */
            public static final int contentChats = 0x7f070128;

            /* JADX INFO: Added by JADX */
            public static final int contentFolded = 0x7f070129;

            /* JADX INFO: Added by JADX */
            public static final int contentNews = 0x7f07012a;

            /* JADX INFO: Added by JADX */
            public static final int contentPanel = 0x7f07012b;

            /* JADX INFO: Added by JADX */
            public static final int content_container = 0x7f07012c;

            /* JADX INFO: Added by JADX */
            public static final int custom = 0x7f07012d;

            /* JADX INFO: Added by JADX */
            public static final int customPanel = 0x7f07012e;

            /* JADX INFO: Added by JADX */
            public static final int decor_content_parent = 0x7f07012f;

            /* JADX INFO: Added by JADX */
            public static final int default_activity_button = 0x7f070130;

            /* JADX INFO: Added by JADX */
            public static final int disableHome = 0x7f070131;

            /* JADX INFO: Added by JADX */
            public static final int dotChats = 0x7f070132;

            /* JADX INFO: Added by JADX */
            public static final int dotFolded = 0x7f070133;

            /* JADX INFO: Added by JADX */
            public static final int dotNews = 0x7f070134;

            /* JADX INFO: Added by JADX */
            public static final int edit_query = 0x7f070135;

            /* JADX INFO: Added by JADX */
            public static final int end = 0x7f070136;

            /* JADX INFO: Added by JADX */
            public static final int end_padder = 0x7f070137;

            /* JADX INFO: Added by JADX */
            public static final int etCaptcha = 0x7f070138;

            /* JADX INFO: Added by JADX */
            public static final int etComment = 0x7f070139;

            /* JADX INFO: Added by JADX */
            public static final int etConfirmPassword = 0x7f07013a;

            /* JADX INFO: Added by JADX */
            public static final int etContent = 0x7f07013b;

            /* JADX INFO: Added by JADX */
            public static final int etDiscussionInput = 0x7f07013c;

            /* JADX INFO: Added by JADX */
            public static final int etDisplayName = 0x7f07013d;

            /* JADX INFO: Added by JADX */
            public static final int etEmail = 0x7f07013e;

            /* JADX INFO: Added by JADX */
            public static final int etEmailCode = 0x7f07013f;

            /* JADX INFO: Added by JADX */
            public static final int etEmojiPlazaKeyword = 0x7f070140;

            /* JADX INFO: Added by JADX */
            public static final int etFriendSearch = 0x7f070141;

            /* JADX INFO: Added by JADX */
            public static final int etFriendUsername = 0x7f070142;

            /* JADX INFO: Added by JADX */
            public static final int etGroupAnnouncementContent = 0x7f070143;

            /* JADX INFO: Added by JADX */
            public static final int etGroupId = 0x7f070144;

            /* JADX INFO: Added by JADX */
            public static final int etGroupName = 0x7f070145;

            /* JADX INFO: Added by JADX */
            public static final int etInput = 0x7f070146;

            /* JADX INFO: Added by JADX */
            public static final int etInviteSearch = 0x7f070147;

            /* JADX INFO: Added by JADX */
            public static final int etMentionSearch = 0x7f070148;

            /* JADX INFO: Added by JADX */
            public static final int etMomentBody = 0x7f070149;

            /* JADX INFO: Added by JADX */
            public static final int etMusicManageKeyword = 0x7f07014a;

            /* JADX INFO: Added by JADX */
            public static final int etMusicPlazaKeyword = 0x7f07014b;

            /* JADX INFO: Added by JADX */
            public static final int etNewPassword = 0x7f07014c;

            /* JADX INFO: Added by JADX */
            public static final int etOldPassword = 0x7f07014d;

            /* JADX INFO: Added by JADX */
            public static final int etOldViewComment = 0x7f07014e;

            /* JADX INFO: Added by JADX */
            public static final int etPassword = 0x7f07014f;

            /* JADX INFO: Added by JADX */
            public static final int etRedPacketAmount = 0x7f070150;

            /* JADX INFO: Added by JADX */
            public static final int etRedPacketCount = 0x7f070151;

            /* JADX INFO: Added by JADX */
            public static final int etRedPacketTitle = 0x7f070152;

            /* JADX INFO: Added by JADX */
            public static final int etResourceSearch = 0x7f070153;

            /* JADX INFO: Added by JADX */
            public static final int etSearchKeyword = 0x7f070154;

            /* JADX INFO: Added by JADX */
            public static final int etSignature = 0x7f070155;

            /* JADX INFO: Added by JADX */
            public static final int etStatementEvidence = 0x7f070156;

            /* JADX INFO: Added by JADX */
            public static final int etStatementReason = 0x7f070157;

            /* JADX INFO: Added by JADX */
            public static final int etUID = 0x7f070158;

            /* JADX INFO: Added by JADX */
            public static final int etUsername = 0x7f070159;

            /* JADX INFO: Added by JADX */
            public static final int expand_activities_button = 0x7f07015a;

            /* JADX INFO: Added by JADX */
            public static final int expanded_menu = 0x7f07015b;

            /* JADX INFO: Added by JADX */
            public static final int fabAdd = 0x7f07015c;

            /* JADX INFO: Added by JADX */
            public static final int fabMenu = 0x7f07015d;

            /* JADX INFO: Added by JADX */
            public static final int fabMenuAddFriend = 0x7f07015e;

            /* JADX INFO: Added by JADX */
            public static final int fabMenuCreateGroup = 0x7f07015f;

            /* JADX INFO: Added by JADX */
            public static final int flMomentImage = 0x7f070160;

            /* JADX INFO: Added by JADX */
            public static final int flResourceCover = 0x7f070161;

            /* JADX INFO: Added by JADX */
            public static final int foldedTileRoot = 0x7f070162;

            /* JADX INFO: Added by JADX */
            public static final int forward_multi_bar = 0x7f070163;

            /* JADX INFO: Added by JADX */
            public static final int friend_card = 0x7f070164;

            /* JADX INFO: Added by JADX */
            public static final int friendsSearchBar = 0x7f070165;

            /* JADX INFO: Added by JADX */
            public static final int friendsSearchDivider = 0x7f070166;

            /* JADX INFO: Added by JADX */
            public static final int group_announcement_banner = 0x7f070167;

            /* JADX INFO: Added by JADX */
            public static final int group_card = 0x7f070168;

            /* JADX INFO: Added by JADX */
            public static final int group_header = 0x7f070169;

            /* JADX INFO: Added by JADX */
            public static final int headerChats = 0x7f07016a;

            /* JADX INFO: Added by JADX */
            public static final int headerFolded = 0x7f07016b;

            /* JADX INFO: Added by JADX */
            public static final int headerNews = 0x7f07016c;

            /* JADX INFO: Added by JADX */
            public static final int home = 0x7f07016d;

            /* JADX INFO: Added by JADX */
            public static final int homeAsUp = 0x7f07016e;

            /* JADX INFO: Added by JADX */
            public static final int hsvMusicPlazaAllSongs = 0x7f07016f;

            /* JADX INFO: Added by JADX */
            public static final int icon = 0x7f070170;

            /* JADX INFO: Added by JADX */
            public static final int ifRoom = 0x7f070171;

            /* JADX INFO: Added by JADX */
            public static final int image = 0x7f070172;

            /* JADX INFO: Added by JADX */
            public static final int image_container = 0x7f070173;

            /* JADX INFO: Added by JADX */
            public static final int info = 0x7f070174;

            /* JADX INFO: Added by JADX */
            public static final int input_area = 0x7f070175;

            /* JADX INFO: Added by JADX */
            public static final int input_container = 0x7f070176;

            /* JADX INFO: Added by JADX */
            public static final int item_touch_helper_previous_elevation = 0x7f070177;

            /* JADX INFO: Added by JADX */
            public static final int ivAvatar = 0x7f070178;

            /* JADX INFO: Added by JADX */
            public static final int ivAvatarRight = 0x7f070179;

            /* JADX INFO: Added by JADX */
            public static final int ivCaptcha = 0x7f07017a;

            /* JADX INFO: Added by JADX */
            public static final int ivChevron = 0x7f07017b;

            /* JADX INFO: Added by JADX */
            public static final int ivCommentAvatar = 0x7f07017c;

            /* JADX INFO: Added by JADX */
            public static final int ivDefendantAvatar = 0x7f07017d;

            /* JADX INFO: Added by JADX */
            public static final int ivDiscoverIconCheckIn = 0x7f07017e;

            /* JADX INFO: Added by JADX */
            public static final int ivDiscoverIconEmoji = 0x7f07017f;

            /* JADX INFO: Added by JADX */
            public static final int ivDiscoverIconMusic = 0x7f070180;

            /* JADX INFO: Added by JADX */
            public static final int ivDiscoverIconOldView = 0x7f070181;

            /* JADX INFO: Added by JADX */
            public static final int ivDiscoverIconPublicCourt = 0x7f070182;

            /* JADX INFO: Added by JADX */
            public static final int ivDiscoverIconReport = 0x7f070183;

            /* JADX INFO: Added by JADX */
            public static final int ivDiscoverIconSettings = 0x7f070184;

            /* JADX INFO: Added by JADX */
            public static final int ivDiscoverMomentsIcon = 0x7f070185;

            /* JADX INFO: Added by JADX */
            public static final int ivEditCover = 0x7f070186;

            /* JADX INFO: Added by JADX */
            public static final int ivEmoji = 0x7f070187;

            /* JADX INFO: Added by JADX */
            public static final int ivEmojiBatchPreview = 0x7f070188;

            /* JADX INFO: Added by JADX */
            public static final int ivEmojiPlazaPreview = 0x7f070189;

            /* JADX INFO: Added by JADX */
            public static final int ivFoldedAvatar = 0x7f07018a;

            /* JADX INFO: Added by JADX */
            public static final int ivFoldedTileAvatar = 0x7f07018b;

            /* JADX INFO: Added by JADX */
            public static final int ivFriendAvatar = 0x7f07018c;

            /* JADX INFO: Added by JADX */
            public static final int ivGroupAvatar = 0x7f07018d;

            /* JADX INFO: Added by JADX */
            public static final int ivLikeIcon = 0x7f07018e;

            /* JADX INFO: Added by JADX */
            public static final int ivLoginLogo = 0x7f07018f;

            /* JADX INFO: Added by JADX */
            public static final int ivMessageImage = 0x7f070190;

            /* JADX INFO: Added by JADX */
            public static final int ivMessageStatus = 0x7f070191;

            /* JADX INFO: Added by JADX */
            public static final int ivMessageStatusMedia = 0x7f070192;

            /* JADX INFO: Added by JADX */
            public static final int ivMomentAvatar = 0x7f070193;

            /* JADX INFO: Added by JADX */
            public static final int ivMomentImage = 0x7f070194;

            /* JADX INFO: Added by JADX */
            public static final int ivMomentPreview = 0x7f070195;

            /* JADX INFO: Added by JADX */
            public static final int ivMomentsChevron = 0x7f070196;

            /* JADX INFO: Added by JADX */
            public static final int ivMomentsHeaderAvatar = 0x7f070197;

            /* JADX INFO: Added by JADX */
            public static final int ivMomentsHeaderCover = 0x7f070198;

            /* JADX INFO: Added by JADX */
            public static final int ivMusicManageCover = 0x7f070199;

            /* JADX INFO: Added by JADX */
            public static final int ivMusicPlayerCover = 0x7f07019a;

            /* JADX INFO: Added by JADX */
            public static final int ivMusicPlazaCover = 0x7f07019b;

            /* JADX INFO: Added by JADX */
            public static final int ivMusicPlazaFeaturedCover = 0x7f07019c;

            /* JADX INFO: Added by JADX */
            public static final int ivOldViewCommentAvatar = 0x7f07019d;

            /* JADX INFO: Added by JADX */
            public static final int ivOldViewCover = 0x7f07019e;

            /* JADX INFO: Added by JADX */
            public static final int ivOldViewDetailCover = 0x7f07019f;

            /* JADX INFO: Added by JADX */
            public static final int ivOldViewDetailOwnerAvatar = 0x7f0701a0;

            /* JADX INFO: Added by JADX */
            public static final int ivOldViewQr = 0x7f0701a1;

            /* JADX INFO: Added by JADX */
            public static final int ivOldViewUpProfileAvatar = 0x7f0701a2;

            /* JADX INFO: Added by JADX */
            public static final int ivPastelAvatar = 0x7f0701a3;

            /* JADX INFO: Added by JADX */
            public static final int ivPreview = 0x7f0701a4;

            /* JADX INFO: Added by JADX */
            public static final int ivQrCard = 0x7f0701a5;

            /* JADX INFO: Added by JADX */
            public static final int ivQuoteImage = 0x7f0701a6;

            /* JADX INFO: Added by JADX */
            public static final int ivRedPacketCoverPreview = 0x7f0701a7;

            /* JADX INFO: Added by JADX */
            public static final int ivRedPacketDetailCover = 0x7f0701a8;

            /* JADX INFO: Added by JADX */
            public static final int ivRedPacketIcon = 0x7f0701a9;

            /* JADX INFO: Added by JADX */
            public static final int ivRedPacketOpenIcon = 0x7f0701aa;

            /* JADX INFO: Added by JADX */
            public static final int ivRedPacketStatus = 0x7f0701ab;

            /* JADX INFO: Added by JADX */
            public static final int ivReporterAvatar = 0x7f0701ac;

            /* JADX INFO: Added by JADX */
            public static final int ivResourceCover = 0x7f0701ad;

            /* JADX INFO: Added by JADX */
            public static final int ivResourceIcon = 0x7f0701ae;

            /* JADX INFO: Added by JADX */
            public static final int ivResourceLike = 0x7f0701af;

            /* JADX INFO: Added by JADX */
            public static final int ivResourcePlay = 0x7f0701b0;

            /* JADX INFO: Added by JADX */
            public static final int ivSpaceAvatar = 0x7f0701b1;

            /* JADX INFO: Added by JADX */
            public static final int ivSpaceCover = 0x7f0701b2;

            /* JADX INFO: Added by JADX */
            public static final int ivTypingAvatar = 0x7f0701b3;

            /* JADX INFO: Added by JADX */
            public static final int ivTypingAvatar2 = 0x7f0701b4;

            /* JADX INFO: Added by JADX */
            public static final int ivTypingAvatar3 = 0x7f0701b5;

            /* JADX INFO: Added by JADX */
            public static final int ivVideoPlay = 0x7f0701b6;

            /* JADX INFO: Added by JADX */
            public static final int ivVoiceIcon = 0x7f0701b7;

            /* JADX INFO: Added by JADX */
            public static final int layoutAnnouncementEditor = 0x7f0701b8;

            /* JADX INFO: Added by JADX */
            public static final int layoutAnnouncementViewer = 0x7f0701b9;

            /* JADX INFO: Added by JADX */
            public static final int layoutDiscussions = 0x7f0701ba;

            /* JADX INFO: Added by JADX */
            public static final int layoutEmojiBatchActions = 0x7f0701bb;

            /* JADX INFO: Added by JADX */
            public static final int layoutEmojiCategories = 0x7f0701bc;

            /* JADX INFO: Added by JADX */
            public static final int layoutEmojiPlazaPager = 0x7f0701bd;

            /* JADX INFO: Added by JADX */
            public static final int layoutEmojiPlazaTabs = 0x7f0701be;

            /* JADX INFO: Added by JADX */
            public static final int layoutEvidenceImages = 0x7f0701bf;

            /* JADX INFO: Added by JADX */
            public static final int layoutGroupActions = 0x7f0701c0;

            /* JADX INFO: Added by JADX */
            public static final int layoutGroupAnnouncementQuickActions = 0x7f0701c1;

            /* JADX INFO: Added by JADX */
            public static final int layoutMergedReports = 0x7f0701c2;

            /* JADX INFO: Added by JADX */
            public static final int layoutMusicPlazaAllSongs = 0x7f0701c3;

            /* JADX INFO: Added by JADX */
            public static final int layoutMusicPlazaHero = 0x7f0701c4;

            /* JADX INFO: Added by JADX */
            public static final int layoutMusicPlazaLegacyHeader = 0x7f0701c5;

            /* JADX INFO: Added by JADX */
            public static final int layoutMusicPlazaRankingSongs = 0x7f0701c6;

            /* JADX INFO: Added by JADX */
            public static final int layoutMusicPlazaRecommendRows = 0x7f0701c7;

            /* JADX INFO: Added by JADX */
            public static final int layoutOldViewCommentInput = 0x7f0701c8;

            /* JADX INFO: Added by JADX */
            public static final int layoutOldViewDetailHeaderRoot = 0x7f0701c9;

            /* JADX INFO: Added by JADX */
            public static final int layoutOldViewFullLoading = 0x7f0701ca;

            /* JADX INFO: Added by JADX */
            public static final int layoutOldViewFullTop = 0x7f0701cb;

            /* JADX INFO: Added by JADX */
            public static final int layoutOldViewOwner = 0x7f0701cc;

            /* JADX INFO: Added by JADX */
            public static final int layoutOldViewPlayer = 0x7f0701cd;

            /* JADX INFO: Added by JADX */
            public static final int layoutOldViewPlayerLoading = 0x7f0701ce;

            /* JADX INFO: Added by JADX */
            public static final int layoutOldViewStats = 0x7f0701cf;

            /* JADX INFO: Added by JADX */
            public static final int layoutOldViewVideoActions = 0x7f0701d0;

            /* JADX INFO: Added by JADX */
            public static final int layoutResourceSearch = 0x7f0701d1;

            /* JADX INFO: Added by JADX */
            public static final int layoutResourceUploadProgress = 0x7f0701d2;

            /* JADX INFO: Added by JADX */
            public static final int layoutStatements = 0x7f0701d3;

            /* JADX INFO: Added by JADX */
            public static final int line1 = 0x7f0701d4;

            /* JADX INFO: Added by JADX */
            public static final int line3 = 0x7f0701d5;

            /* JADX INFO: Added by JADX */
            public static final int listMode = 0x7f0701d6;

            /* JADX INFO: Added by JADX */
            public static final int list_item = 0x7f0701d7;

            /* JADX INFO: Added by JADX */
            public static final int lvComments = 0x7f0701d8;

            /* JADX INFO: Added by JADX */
            public static final int lvDevices = 0x7f0701d9;

            /* JADX INFO: Added by JADX */
            public static final int lvEmojiPlaza = 0x7f0701da;

            /* JADX INFO: Added by JADX */
            public static final int lvFavorites = 0x7f0701db;

            /* JADX INFO: Added by JADX */
            public static final int lvFriends = 0x7f0701dc;

            /* JADX INFO: Added by JADX */
            public static final int lvMembers = 0x7f0701dd;

            /* JADX INFO: Added by JADX */
            public static final int lvMentionList = 0x7f0701de;

            /* JADX INFO: Added by JADX */
            public static final int lvMessages = 0x7f0701df;

            /* JADX INFO: Added by JADX */
            public static final int lvMomentNotices = 0x7f0701e0;

            /* JADX INFO: Added by JADX */
            public static final int lvMoments = 0x7f0701e1;

            /* JADX INFO: Added by JADX */
            public static final int lvMusicDownloads = 0x7f0701e2;

            /* JADX INFO: Added by JADX */
            public static final int lvMusicManage = 0x7f0701e3;

            /* JADX INFO: Added by JADX */
            public static final int lvMusicPlaza = 0x7f0701e4;

            /* JADX INFO: Added by JADX */
            public static final int lvNotifications = 0x7f0701e5;

            /* JADX INFO: Added by JADX */
            public static final int lvOldViewDetailComments = 0x7f0701e6;

            /* JADX INFO: Added by JADX */
            public static final int lvOldViewList = 0x7f0701e7;

            /* JADX INFO: Added by JADX */
            public static final int lvOldViewUpProfileVideos = 0x7f0701e8;

            /* JADX INFO: Added by JADX */
            public static final int lvOldViewVideos = 0x7f0701e9;

            /* JADX INFO: Added by JADX */
            public static final int lvRedPacketClaims = 0x7f0701ea;

            /* JADX INFO: Added by JADX */
            public static final int lvRequests = 0x7f0701eb;

            /* JADX INFO: Added by JADX */
            public static final int lvResourceSections = 0x7f0701ec;

            /* JADX INFO: Added by JADX */
            public static final int lvResources = 0x7f0701ed;

            /* JADX INFO: Added by JADX */
            public static final int lvSearchMessages = 0x7f0701ee;

            /* JADX INFO: Added by JADX */
            public static final int media_actions = 0x7f0701ef;

            /* JADX INFO: Added by JADX */
            public static final int message_bubble = 0x7f0701f0;

            /* JADX INFO: Added by JADX */
            public static final int message_container = 0x7f0701f1;

            /* JADX INFO: Added by JADX */
            public static final int message_status_media_row = 0x7f0701f2;

            /* JADX INFO: Added by JADX */
            public static final int message_status_row = 0x7f0701f3;

            /* JADX INFO: Added by JADX */
            public static final int message_text_row = 0x7f0701f4;

            /* JADX INFO: Added by JADX */
            public static final int middle = 0x7f0701f5;

            /* JADX INFO: Added by JADX */
            public static final int multiply = 0x7f0701f6;

            /* JADX INFO: Added by JADX */
            public static final int never = 0x7f0701f7;

            /* JADX INFO: Added by JADX */
            public static final int none = 0x7f0701f8;

            /* JADX INFO: Added by JADX */
            public static final int normal = 0x7f0701f9;

            /* JADX INFO: Added by JADX */
            public static final int pagerTabStrip = 0x7f0701fa;

            /* JADX INFO: Added by JADX */
            public static final int panelChats = 0x7f0701fb;

            /* JADX INFO: Added by JADX */
            public static final int panelFolded = 0x7f0701fc;

            /* JADX INFO: Added by JADX */
            public static final int panelNews = 0x7f0701fd;

            /* JADX INFO: Added by JADX */
            public static final int panelOldViewLogin = 0x7f0701fe;

            /* JADX INFO: Added by JADX */
            public static final int panelOldViewQuick = 0x7f0701ff;

            /* JADX INFO: Added by JADX */
            public static final int parentPanel = 0x7f070200;

            /* JADX INFO: Added by JADX */
            public static final int pastelMainContainer = 0x7f070201;

            /* JADX INFO: Added by JADX */
            public static final int pbFriendsLoading = 0x7f070202;

            /* JADX INFO: Added by JADX */
            public static final int pbHomeLoading = 0x7f070203;

            /* JADX INFO: Added by JADX */
            public static final int pbImageLoading = 0x7f070204;

            /* JADX INFO: Added by JADX */
            public static final int pbLoading = 0x7f070205;

            /* JADX INFO: Added by JADX */
            public static final int pbMessagesLoading = 0x7f070206;

            /* JADX INFO: Added by JADX */
            public static final int pbMomentsLoading = 0x7f070207;

            /* JADX INFO: Added by JADX */
            public static final int pbOldViewDetailLoading = 0x7f070208;

            /* JADX INFO: Added by JADX */
            public static final int pbOldViewFullLoading = 0x7f070209;

            /* JADX INFO: Added by JADX */
            public static final int pbOldViewListLoading = 0x7f07020a;

            /* JADX INFO: Added by JADX */
            public static final int pbOldViewLoading = 0x7f07020b;

            /* JADX INFO: Added by JADX */
            public static final int pbOldViewPlayerLoading = 0x7f07020c;

            /* JADX INFO: Added by JADX */
            public static final int pbOldViewUpProfileLoading = 0x7f07020d;

            /* JADX INFO: Added by JADX */
            public static final int pbPreviewLoading = 0x7f07020e;

            /* JADX INFO: Added by JADX */
            public static final int pbRedPacketOpen = 0x7f07020f;

            /* JADX INFO: Added by JADX */
            public static final int pbRedPacketSend = 0x7f070210;

            /* JADX INFO: Added by JADX */
            public static final int pbResourceSectionsLoading = 0x7f070211;

            /* JADX INFO: Added by JADX */
            public static final int pbResourceUpload = 0x7f070212;

            /* JADX INFO: Added by JADX */
            public static final int pbResourcesLoading = 0x7f070213;

            /* JADX INFO: Added by JADX */
            public static final int pbSend = 0x7f070214;

            /* JADX INFO: Added by JADX */
            public static final int pbVideoProgress = 0x7f070215;

            /* JADX INFO: Added by JADX */
            public static final int pbVoiceLoading = 0x7f070216;

            /* JADX INFO: Added by JADX */
            public static final int profile_card = 0x7f070217;

            /* JADX INFO: Added by JADX */
            public static final int progressBar = 0x7f070218;

            /* JADX INFO: Added by JADX */
            public static final int progress_circular = 0x7f070219;

            /* JADX INFO: Added by JADX */
            public static final int progress_horizontal = 0x7f07021a;

            /* JADX INFO: Added by JADX */
            public static final int quote_container = 0x7f07021b;

            /* JADX INFO: Added by JADX */
            public static final int quote_preview = 0x7f07021c;

            /* JADX INFO: Added by JADX */
            public static final int radio = 0x7f07021d;

            /* JADX INFO: Added by JADX */
            public static final int rbAnnouncementOptional = 0x7f07021e;

            /* JADX INFO: Added by JADX */
            public static final int rbAnnouncementRequired = 0x7f07021f;

            /* JADX INFO: Added by JADX */
            public static final int recentChatCard = 0x7f070220;

            /* JADX INFO: Added by JADX */
            public static final int recyclerView = 0x7f070221;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_container = 0x7f070222;

            /* JADX INFO: Added by JADX */
            public static final int requestActions = 0x7f070223;

            /* JADX INFO: Added by JADX */
            public static final int resource_card = 0x7f070224;

            /* JADX INFO: Added by JADX */
            public static final int rgAnnouncementMode = 0x7f070225;

            /* JADX INFO: Added by JADX */
            public static final int rightMeta = 0x7f070226;

            /* JADX INFO: Added by JADX */
            public static final int rlImagePreview = 0x7f070227;

            /* JADX INFO: Added by JADX */
            public static final int rowAvatarMetaCache = 0x7f070228;

            /* JADX INFO: Added by JADX */
            public static final int rowBubbleInColor = 0x7f070229;

            /* JADX INFO: Added by JADX */
            public static final int rowBubbleOutColor = 0x7f07022a;

            /* JADX INFO: Added by JADX */
            public static final int rowButton3dEffect = 0x7f07022b;

            /* JADX INFO: Added by JADX */
            public static final int rowChatBackgroundGlobal = 0x7f07022c;

            /* JADX INFO: Added by JADX */
            public static final int rowChatCache = 0x7f07022d;

            /* JADX INFO: Added by JADX */
            public static final int rowDarkMode = 0x7f07022e;

            /* JADX INFO: Added by JADX */
            public static final int rowDiscoverSettings = 0x7f07022f;

            /* JADX INFO: Added by JADX */
            public static final int rowEnterSend = 0x7f070230;

            /* JADX INFO: Added by JADX */
            public static final int rowEnterSendMain = 0x7f070231;

            /* JADX INFO: Added by JADX */
            public static final int rowFontSize = 0x7f070232;

            /* JADX INFO: Added by JADX */
            public static final int rowFriendCache = 0x7f070233;

            /* JADX INFO: Added by JADX */
            public static final int rowGroupCache = 0x7f070234;

            /* JADX INFO: Added by JADX */
            public static final int rowGroupChatBackgroundGlobal = 0x7f070235;

            /* JADX INFO: Added by JADX */
            public static final int rowHomeNews = 0x7f070236;

            /* JADX INFO: Added by JADX */
            public static final int rowImageCache = 0x7f070237;

            /* JADX INFO: Added by JADX */
            public static final int rowMomentCache = 0x7f070238;

            /* JADX INFO: Added by JADX */
            public static final int rowNotifications = 0x7f070239;

            /* JADX INFO: Added by JADX */
            public static final int rowOldViewEntry = 0x7f07023a;

            /* JADX INFO: Added by JADX */
            public static final int rowPublicCourtEntry = 0x7f07023b;

            /* JADX INFO: Added by JADX */
            public static final int rowRecentCache = 0x7f07023c;

            /* JADX INFO: Added by JADX */
            public static final int rowRoundedButtons = 0x7f07023d;

            /* JADX INFO: Added by JADX */
            public static final int rowThemeColor = 0x7f07023e;

            /* JADX INFO: Added by JADX */
            public static final int rowTypingIndicator = 0x7f07023f;

            /* JADX INFO: Added by JADX */
            public static final int rowUserCache = 0x7f070240;

            /* JADX INFO: Added by JADX */
            public static final int rowVoiceCache = 0x7f070241;

            /* JADX INFO: Added by JADX */
            public static final int rvEmojis = 0x7f070242;

            /* JADX INFO: Added by JADX */
            public static final int sbMusicPlayerProgress = 0x7f070243;

            /* JADX INFO: Added by JADX */
            public static final int screen = 0x7f070244;

            /* JADX INFO: Added by JADX */
            public static final int scrollIndicatorDown = 0x7f070245;

            /* JADX INFO: Added by JADX */
            public static final int scrollIndicatorUp = 0x7f070246;

            /* JADX INFO: Added by JADX */
            public static final int scrollView = 0x7f070247;

            /* JADX INFO: Added by JADX */
            public static final int search_badge = 0x7f070248;

            /* JADX INFO: Added by JADX */
            public static final int search_bar = 0x7f070249;

            /* JADX INFO: Added by JADX */
            public static final int search_button = 0x7f07024a;

            /* JADX INFO: Added by JADX */
            public static final int search_close_btn = 0x7f07024b;

            /* JADX INFO: Added by JADX */
            public static final int search_edit_frame = 0x7f07024c;

            /* JADX INFO: Added by JADX */
            public static final int search_go_btn = 0x7f07024d;

            /* JADX INFO: Added by JADX */
            public static final int search_mag_icon = 0x7f07024e;

            /* JADX INFO: Added by JADX */
            public static final int search_plate = 0x7f07024f;

            /* JADX INFO: Added by JADX */
            public static final int search_src_text = 0x7f070250;

            /* JADX INFO: Added by JADX */
            public static final int search_voice_btn = 0x7f070251;

            /* JADX INFO: Added by JADX */
            public static final int sectionChatsRoot = 0x7f070252;

            /* JADX INFO: Added by JADX */
            public static final int sectionFoldedRoot = 0x7f070253;

            /* JADX INFO: Added by JADX */
            public static final int sectionHeaderTouch = 0x7f070254;

            /* JADX INFO: Added by JADX */
            public static final int sectionNewsRoot = 0x7f070255;

            /* JADX INFO: Added by JADX */
            public static final int select_dialog_listview = 0x7f070256;

            /* JADX INFO: Added by JADX */
            public static final int sender_row = 0x7f070257;

            /* JADX INFO: Added by JADX */
            public static final int shortcut = 0x7f070258;

            /* JADX INFO: Added by JADX */
            public static final int showCustom = 0x7f070259;

            /* JADX INFO: Added by JADX */
            public static final int showHome = 0x7f07025a;

            /* JADX INFO: Added by JADX */
            public static final int showTitle = 0x7f07025b;

            /* JADX INFO: Added by JADX */
            public static final int space_action_row = 0x7f07025c;

            /* JADX INFO: Added by JADX */
            public static final int space_button_row = 0x7f07025d;

            /* JADX INFO: Added by JADX */
            public static final int spacer = 0x7f07025e;

            /* JADX INFO: Added by JADX */
            public static final int split_action_bar = 0x7f07025f;

            /* JADX INFO: Added by JADX */
            public static final int src_atop = 0x7f070260;

            /* JADX INFO: Added by JADX */
            public static final int src_in = 0x7f070261;

            /* JADX INFO: Added by JADX */
            public static final int src_over = 0x7f070262;

            /* JADX INFO: Added by JADX */
            public static final int statusContainer = 0x7f070263;

            /* JADX INFO: Added by JADX */
            public static final int statusText = 0x7f070264;

            /* JADX INFO: Added by JADX */
            public static final int status_bar_latest_event_content = 0x7f070265;

            /* JADX INFO: Added by JADX */
            public static final int submenuarrow = 0x7f070266;

            /* JADX INFO: Added by JADX */
            public static final int submit_area = 0x7f070267;

            /* JADX INFO: Added by JADX */
            public static final int svEmojiCategories = 0x7f070268;

            /* JADX INFO: Added by JADX */
            public static final int svGroupAnnouncementContent = 0x7f070269;

            /* JADX INFO: Added by JADX */
            public static final int svPastelHome = 0x7f07026a;

            /* JADX INFO: Added by JADX */
            public static final int swipeRefresh = 0x7f07026b;

            /* JADX INFO: Added by JADX */
            public static final int switchButton3dEffect = 0x7f07026c;

            /* JADX INFO: Added by JADX */
            public static final int switchDarkMode = 0x7f07026d;

            /* JADX INFO: Added by JADX */
            public static final int switchEnterSend = 0x7f07026e;

            /* JADX INFO: Added by JADX */
            public static final int switchEnterSendMain = 0x7f07026f;

            /* JADX INFO: Added by JADX */
            public static final int switchGlobalMute = 0x7f070270;

            /* JADX INFO: Added by JADX */
            public static final int switchHomeNews = 0x7f070271;

            /* JADX INFO: Added by JADX */
            public static final int switchJoinApproval = 0x7f070272;

            /* JADX INFO: Added by JADX */
            public static final int switchMuteNotify = 0x7f070273;

            /* JADX INFO: Added by JADX */
            public static final int switchNotifications = 0x7f070274;

            /* JADX INFO: Added by JADX */
            public static final int switchOldViewEntry = 0x7f070275;

            /* JADX INFO: Added by JADX */
            public static final int switchPublicCourtEntry = 0x7f070276;

            /* JADX INFO: Added by JADX */
            public static final int switchRoundedButtons = 0x7f070277;

            /* JADX INFO: Added by JADX */
            public static final int switchTypingIndicator = 0x7f070278;

            /* JADX INFO: Added by JADX */
            public static final int tabBadge = 0x7f070279;

            /* JADX INFO: Added by JADX */
            public static final int tabIcon = 0x7f07027a;

            /* JADX INFO: Added by JADX */
            public static final int tabIndicator = 0x7f07027b;

            /* JADX INFO: Added by JADX */
            public static final int tabLabel = 0x7f07027c;

            /* JADX INFO: Added by JADX */
            public static final int tabMode = 0x7f07027d;

            /* JADX INFO: Added by JADX */
            public static final int tabOldViewComments = 0x7f07027e;

            /* JADX INFO: Added by JADX */
            public static final int tabOldViewRelated = 0x7f07027f;

            /* JADX INFO: Added by JADX */
            public static final int tabOldViewUpProfileSpace = 0x7f070280;

            /* JADX INFO: Added by JADX */
            public static final int tabOldViewUpProfileVideos = 0x7f070281;

            /* JADX INFO: Added by JADX */
            public static final int tabs = 0x7f070282;

            /* JADX INFO: Added by JADX */
            public static final int tag_square_press_bound = 0x7f070283;

            /* JADX INFO: Added by JADX */
            public static final int tag_square_press_installed = 0x7f070284;

            /* JADX INFO: Added by JADX */
            public static final int tag_square_press_last_apply_ms = 0x7f070285;

            /* JADX INFO: Added by JADX */
            public static final int tag_square_press_pending = 0x7f070286;

            /* JADX INFO: Added by JADX */
            public static final int text = 0x7f070287;

            /* JADX INFO: Added by JADX */
            public static final int text2 = 0x7f070288;

            /* JADX INFO: Added by JADX */
            public static final int textSpacerNoButtons = 0x7f070289;

            /* JADX INFO: Added by JADX */
            public static final int time = 0x7f07028a;

            /* JADX INFO: Added by JADX */
            public static final int title = 0x7f07028b;

            /* JADX INFO: Added by JADX */
            public static final int title_template = 0x7f07028c;

            /* JADX INFO: Added by JADX */
            public static final int top = 0x7f07028d;

            /* JADX INFO: Added by JADX */
            public static final int topPanel = 0x7f07028e;

            /* JADX INFO: Added by JADX */
            public static final int topStatusBar = 0x7f07028f;

            /* JADX INFO: Added by JADX */
            public static final int tvActionHint = 0x7f070290;

            /* JADX INFO: Added by JADX */
            public static final int tvAdminNote = 0x7f070291;

            /* JADX INFO: Added by JADX */
            public static final int tvAdminResult = 0x7f070292;

            /* JADX INFO: Added by JADX */
            public static final int tvAnnouncementViewerContent = 0x7f070293;

            /* JADX INFO: Added by JADX */
            public static final int tvAnnouncementViewerMode = 0x7f070294;

            /* JADX INFO: Added by JADX */
            public static final int tvAvatarMetaCacheSize = 0x7f070295;

            /* JADX INFO: Added by JADX */
            public static final int tvBadge = 0x7f070296;

            /* JADX INFO: Added by JADX */
            public static final int tvBody = 0x7f070297;

            /* JADX INFO: Added by JADX */
            public static final int tvBubbleInColorValue = 0x7f070298;

            /* JADX INFO: Added by JADX */
            public static final int tvBubbleOutColorValue = 0x7f070299;

            /* JADX INFO: Added by JADX */
            public static final int tvCacheTitle = 0x7f07029a;

            /* JADX INFO: Added by JADX */
            public static final int tvCacheTotalSize = 0x7f07029b;

            /* JADX INFO: Added by JADX */
            public static final int tvCaseId = 0x7f07029c;

            /* JADX INFO: Added by JADX */
            public static final int tvChatBackgroundGlobalValue = 0x7f07029d;

            /* JADX INFO: Added by JADX */
            public static final int tvChatCacheSize = 0x7f07029e;

            /* JADX INFO: Added by JADX */
            public static final int tvChatTitle = 0x7f07029f;

            /* JADX INFO: Added by JADX */
            public static final int tvChatsCount = 0x7f0702a0;

            /* JADX INFO: Added by JADX */
            public static final int tvChatsTitle = 0x7f0702a1;

            /* JADX INFO: Added by JADX */
            public static final int tvClaimAmount = 0x7f0702a2;

            /* JADX INFO: Added by JADX */
            public static final int tvClaimName = 0x7f0702a3;

            /* JADX INFO: Added by JADX */
            public static final int tvClaimTime = 0x7f0702a4;

            /* JADX INFO: Added by JADX */
            public static final int tvCommentBody = 0x7f0702a5;

            /* JADX INFO: Added by JADX */
            public static final int tvCommentCount = 0x7f0702a6;

            /* JADX INFO: Added by JADX */
            public static final int tvCommentName = 0x7f0702a7;

            /* JADX INFO: Added by JADX */
            public static final int tvCommentTime = 0x7f0702a8;

            /* JADX INFO: Added by JADX */
            public static final int tvCommentTitleBadge = 0x7f0702a9;

            /* JADX INFO: Added by JADX */
            public static final int tvCommentsTitle = 0x7f0702aa;

            /* JADX INFO: Added by JADX */
            public static final int tvCrashLog = 0x7f0702ab;

            /* JADX INFO: Added by JADX */
            public static final int tvCreatedAt = 0x7f0702ac;

            /* JADX INFO: Added by JADX */
            public static final int tvDefendantName = 0x7f0702ad;

            /* JADX INFO: Added by JADX */
            public static final int tvDefenseEvidence = 0x7f0702ae;

            /* JADX INFO: Added by JADX */
            public static final int tvDefenseReason = 0x7f0702af;

            /* JADX INFO: Added by JADX */
            public static final int tvDeviceInfo = 0x7f0702b0;

            /* JADX INFO: Added by JADX */
            public static final int tvDeviceMeta = 0x7f0702b1;

            /* JADX INFO: Added by JADX */
            public static final int tvDeviceName = 0x7f0702b2;

            /* JADX INFO: Added by JADX */
            public static final int tvEmojiBatchCheck = 0x7f0702b3;

            /* JADX INFO: Added by JADX */
            public static final int tvEmojiBatchCount = 0x7f0702b4;

            /* JADX INFO: Added by JADX */
            public static final int tvEmojiEmpty = 0x7f0702b5;

            /* JADX INFO: Added by JADX */
            public static final int tvEmojiPlazaEmpty = 0x7f0702b6;

            /* JADX INFO: Added by JADX */
            public static final int tvEmojiPlazaHint = 0x7f0702b7;

            /* JADX INFO: Added by JADX */
            public static final int tvEmojiPlazaMeta = 0x7f0702b8;

            /* JADX INFO: Added by JADX */
            public static final int tvEmojiPlazaName = 0x7f0702b9;

            /* JADX INFO: Added by JADX */
            public static final int tvEmojiPlazaOwner = 0x7f0702ba;

            /* JADX INFO: Added by JADX */
            public static final int tvEmojiPlazaPageInfo = 0x7f0702bb;

            /* JADX INFO: Added by JADX */
            public static final int tvEmojiPlazaTitle = 0x7f0702bc;

            /* JADX INFO: Added by JADX */
            public static final int tvEmojiSelected = 0x7f0702bd;

            /* JADX INFO: Added by JADX */
            public static final int tvEmojiTitle = 0x7f0702be;

            /* JADX INFO: Added by JADX */
            public static final int tvEmpty = 0x7f0702bf;

            /* JADX INFO: Added by JADX */
            public static final int tvEvidence = 0x7f0702c0;

            /* JADX INFO: Added by JADX */
            public static final int tvEvidenceUploadStatus = 0x7f0702c1;

            /* JADX INFO: Added by JADX */
            public static final int tvFavoriteSubtitle = 0x7f0702c2;

            /* JADX INFO: Added by JADX */
            public static final int tvFavoriteTime = 0x7f0702c3;

            /* JADX INFO: Added by JADX */
            public static final int tvFavoriteTitle = 0x7f0702c4;

            /* JADX INFO: Added by JADX */
            public static final int tvFavoriteType = 0x7f0702c5;

            /* JADX INFO: Added by JADX */
            public static final int tvFavoritesEmpty = 0x7f0702c6;

            /* JADX INFO: Added by JADX */
            public static final int tvFoldedCount = 0x7f0702c7;

            /* JADX INFO: Added by JADX */
            public static final int tvFoldedName = 0x7f0702c8;

            /* JADX INFO: Added by JADX */
            public static final int tvFoldedTileSubtitle = 0x7f0702c9;

            /* JADX INFO: Added by JADX */
            public static final int tvFoldedTileTitle = 0x7f0702ca;

            /* JADX INFO: Added by JADX */
            public static final int tvFoldedTileUnread = 0x7f0702cb;

            /* JADX INFO: Added by JADX */
            public static final int tvFoldedTitle = 0x7f0702cc;

            /* JADX INFO: Added by JADX */
            public static final int tvFoldedUnread = 0x7f0702cd;

            /* JADX INFO: Added by JADX */
            public static final int tvFontSizeValue = 0x7f0702ce;

            /* JADX INFO: Added by JADX */
            public static final int tvFooter = 0x7f0702cf;

            /* JADX INFO: Added by JADX */
            public static final int tvForwardMultiCount = 0x7f0702d0;

            /* JADX INFO: Added by JADX */
            public static final int tvFriendCacheSize = 0x7f0702d1;

            /* JADX INFO: Added by JADX */
            public static final int tvFriendName = 0x7f0702d2;

            /* JADX INFO: Added by JADX */
            public static final int tvFriendUid = 0x7f0702d3;

            /* JADX INFO: Added by JADX */
            public static final int tvGalleryIndex = 0x7f0702d4;

            /* JADX INFO: Added by JADX */
            public static final int tvGroupAnnouncement = 0x7f0702d5;

            /* JADX INFO: Added by JADX */
            public static final int tvGroupAnnouncementBanner = 0x7f0702d6;

            /* JADX INFO: Added by JADX */
            public static final int tvGroupAnnouncementCount = 0x7f0702d7;

            /* JADX INFO: Added by JADX */
            public static final int tvGroupAnnouncementGroupName = 0x7f0702d8;

            /* JADX INFO: Added by JADX */
            public static final int tvGroupAnnouncementPageTitle = 0x7f0702d9;

            /* JADX INFO: Added by JADX */
            public static final int tvGroupAnnouncementPreview = 0x7f0702da;

            /* JADX INFO: Added by JADX */
            public static final int tvGroupCacheSize = 0x7f0702db;

            /* JADX INFO: Added by JADX */
            public static final int tvGroupChatBackgroundGlobalValue = 0x7f0702dc;

            /* JADX INFO: Added by JADX */
            public static final int tvGroupId = 0x7f0702dd;

            /* JADX INFO: Added by JADX */
            public static final int tvGroupMeta = 0x7f0702de;

            /* JADX INFO: Added by JADX */
            public static final int tvGroupName = 0x7f0702df;

            /* JADX INFO: Added by JADX */
            public static final int tvGroupTitle = 0x7f0702e0;

            /* JADX INFO: Added by JADX */
            public static final int tvImageCacheSize = 0x7f0702e1;

            /* JADX INFO: Added by JADX */
            public static final int tvInviteFriendMeta = 0x7f0702e2;

            /* JADX INFO: Added by JADX */
            public static final int tvInviteFriendName = 0x7f0702e3;

            /* JADX INFO: Added by JADX */
            public static final int tvInviteSelected = 0x7f0702e4;

            /* JADX INFO: Added by JADX */
            public static final int tvLeaveOrDissolve = 0x7f0702e5;

            /* JADX INFO: Added by JADX */
            public static final int tvLikeCount = 0x7f0702e6;

            /* JADX INFO: Added by JADX */
            public static final int tvMemberCount = 0x7f0702e7;

            /* JADX INFO: Added by JADX */
            public static final int tvMergedReportPage = 0x7f0702e8;

            /* JADX INFO: Added by JADX */
            public static final int tvMessage = 0x7f0702e9;

            /* JADX INFO: Added by JADX */
            public static final int tvMessageStatus = 0x7f0702ea;

            /* JADX INFO: Added by JADX */
            public static final int tvMessageStatusMedia = 0x7f0702eb;

            /* JADX INFO: Added by JADX */
            public static final int tvMessageTime = 0x7f0702ec;

            /* JADX INFO: Added by JADX */
            public static final int tvMeta = 0x7f0702ed;

            /* JADX INFO: Added by JADX */
            public static final int tvMomentBody = 0x7f0702ee;

            /* JADX INFO: Added by JADX */
            public static final int tvMomentCacheSize = 0x7f0702ef;

            /* JADX INFO: Added by JADX */
            public static final int tvMomentCounts = 0x7f0702f0;

            /* JADX INFO: Added by JADX */
            public static final int tvMomentImageCount = 0x7f0702f1;

            /* JADX INFO: Added by JADX */
            public static final int tvMomentName = 0x7f0702f2;

            /* JADX INFO: Added by JADX */
            public static final int tvMomentTime = 0x7f0702f3;

            /* JADX INFO: Added by JADX */
            public static final int tvMomentTitleBadge = 0x7f0702f4;

            /* JADX INFO: Added by JADX */
            public static final int tvMomentsEmpty = 0x7f0702f5;

            /* JADX INFO: Added by JADX */
            public static final int tvMomentsHeaderName = 0x7f0702f6;

            /* JADX INFO: Added by JADX */
            public static final int tvMomentsHeaderSignature = 0x7f0702f7;

            /* JADX INFO: Added by JADX */
            public static final int tvMomentsTitle = 0x7f0702f8;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicDownloadMeta = 0x7f0702f9;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicDownloadName = 0x7f0702fa;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicDownloadPlay = 0x7f0702fb;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicDownloadsEmpty = 0x7f0702fc;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicDownloadsSummary = 0x7f0702fd;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicManageEmpty = 0x7f0702fe;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicManageMeta = 0x7f0702ff;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicManageName = 0x7f070300;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicManageOwner = 0x7f070301;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicManagePageInfo = 0x7f070302;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicManageStats = 0x7f070303;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicManageSummary = 0x7f070304;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicManageTitle = 0x7f070305;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicPlayerCurrent = 0x7f070306;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicPlayerDuration = 0x7f070307;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicPlayerHint = 0x7f070308;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicPlayerOwner = 0x7f070309;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicPlayerSongName = 0x7f07030a;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicPlayerStatus = 0x7f07030b;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicPlazaAllEmpty = 0x7f07030c;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicPlazaDuration = 0x7f07030d;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicPlazaEmpty = 0x7f07030e;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicPlazaFeaturedSub = 0x7f07030f;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicPlazaFeaturedTitle = 0x7f070310;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicPlazaMeta = 0x7f070311;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicPlazaName = 0x7f070312;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicPlazaOwner = 0x7f070313;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicPlazaPageInfo = 0x7f070314;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicPlazaRankingEmpty = 0x7f070315;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicPlazaRecommendEmpty = 0x7f070316;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicPlazaStats = 0x7f070317;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicPlazaSummary = 0x7f070318;

            /* JADX INFO: Added by JADX */
            public static final int tvMusicPlazaTag = 0x7f070319;

            /* JADX INFO: Added by JADX */
            public static final int tvMyEmail = 0x7f07031a;

            /* JADX INFO: Added by JADX */
            public static final int tvMyTitleBadge = 0x7f07031b;

            /* JADX INFO: Added by JADX */
            public static final int tvMyUsername = 0x7f07031c;

            /* JADX INFO: Added by JADX */
            public static final int tvName = 0x7f07031d;

            /* JADX INFO: Added by JADX */
            public static final int tvNewsCount = 0x7f07031e;

            /* JADX INFO: Added by JADX */
            public static final int tvNewsTitle = 0x7f07031f;

            /* JADX INFO: Added by JADX */
            public static final int tvNoticeBody = 0x7f070320;

            /* JADX INFO: Added by JADX */
            public static final int tvNoticeEmpty = 0x7f070321;

            /* JADX INFO: Added by JADX */
            public static final int tvNoticeTime = 0x7f070322;

            /* JADX INFO: Added by JADX */
            public static final int tvNoticeTitle = 0x7f070323;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewCommentContent = 0x7f070324;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewCommentLike = 0x7f070325;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewCommentName = 0x7f070326;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewCommentReplies = 0x7f070327;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewCommentReply = 0x7f070328;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewCommentReplyCount = 0x7f070329;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewCommentReplyToggle = 0x7f07032a;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewCommentTag = 0x7f07032b;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewCommentTime = 0x7f07032c;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewDetailDesc = 0x7f07032d;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewDetailEmpty = 0x7f07032e;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewDetailMeta = 0x7f07032f;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewDetailOwnerName = 0x7f070330;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewDetailOwnerTag = 0x7f070331;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewDetailStatDanmaku = 0x7f070332;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewDetailStatLike = 0x7f070333;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewDetailStatPlay = 0x7f070334;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewDetailStatReply = 0x7f070335;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewDetailSummaryLabel = 0x7f070336;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewDetailTitle = 0x7f070337;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewDetailVideoTitle = 0x7f070338;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewDuration = 0x7f070339;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewEmpty = 0x7f07033a;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewFullBottomHint = 0x7f07033b;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewFullHint = 0x7f07033c;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewFullTitle = 0x7f07033d;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewListEmpty = 0x7f07033e;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewListTitle = 0x7f07033f;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewLoginTitle = 0x7f070340;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewMeta = 0x7f070341;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewPlayerHint = 0x7f070342;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewStatus = 0x7f070343;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewTitle = 0x7f070344;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewUpName = 0x7f070345;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewUpProfileArchive = 0x7f070346;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewUpProfileEmpty = 0x7f070347;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewUpProfileFollower = 0x7f070348;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewUpProfileFollowing = 0x7f070349;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewUpProfileName = 0x7f07034a;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewUpProfileSign = 0x7f07034b;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewUpProfileTitle = 0x7f07034c;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewUpProfileUid = 0x7f07034d;

            /* JADX INFO: Added by JADX */
            public static final int tvOldViewVideoActionHint = 0x7f07034e;

            /* JADX INFO: Added by JADX */
            public static final int tvOpenTime = 0x7f07034f;

            /* JADX INFO: Added by JADX */
            public static final int tvParticipants = 0x7f070350;

            /* JADX INFO: Added by JADX */
            public static final int tvPastelAt = 0x7f070351;

            /* JADX INFO: Added by JADX */
            public static final int tvPastelHeroTitle = 0x7f070352;

            /* JADX INFO: Added by JADX */
            public static final int tvPastelSubtitle = 0x7f070353;

            /* JADX INFO: Added by JADX */
            public static final int tvPastelTag = 0x7f070354;

            /* JADX INFO: Added by JADX */
            public static final int tvPastelTime = 0x7f070355;

            /* JADX INFO: Added by JADX */
            public static final int tvPastelTitle = 0x7f070356;

            /* JADX INFO: Added by JADX */
            public static final int tvPastelUnread = 0x7f070357;

            /* JADX INFO: Added by JADX */
            public static final int tvPinned = 0x7f070358;

            /* JADX INFO: Added by JADX */
            public static final int tvPrivacyContent = 0x7f070359;

            /* JADX INFO: Added by JADX */
            public static final int tvPrivacyPolicy = 0x7f07035a;

            /* JADX INFO: Added by JADX */
            public static final int tvProgress = 0x7f07035b;

            /* JADX INFO: Added by JADX */
            public static final int tvQrCardDesc = 0x7f07035c;

            /* JADX INFO: Added by JADX */
            public static final int tvQrCardHint = 0x7f07035d;

            /* JADX INFO: Added by JADX */
            public static final int tvQrCardTitle = 0x7f07035e;

            /* JADX INFO: Added by JADX */
            public static final int tvQrScanHint = 0x7f07035f;

            /* JADX INFO: Added by JADX */
            public static final int tvQuoteContent = 0x7f070360;

            /* JADX INFO: Added by JADX */
            public static final int tvQuotePreview = 0x7f070361;

            /* JADX INFO: Added by JADX */
            public static final int tvQuoteSender = 0x7f070362;

            /* JADX INFO: Added by JADX */
            public static final int tvReadCount = 0x7f070363;

            /* JADX INFO: Added by JADX */
            public static final int tvReadCountMedia = 0x7f070364;

            /* JADX INFO: Added by JADX */
            public static final int tvReason = 0x7f070365;

            /* JADX INFO: Added by JADX */
            public static final int tvRecentCacheSize = 0x7f070366;

            /* JADX INFO: Added by JADX */
            public static final int tvRedPacketCoverHint = 0x7f070367;

            /* JADX INFO: Added by JADX */
            public static final int tvRedPacketDesc = 0x7f070368;

            /* JADX INFO: Added by JADX */
            public static final int tvRedPacketDetailClaimedAmount = 0x7f070369;

            /* JADX INFO: Added by JADX */
            public static final int tvRedPacketDetailClaimedCount = 0x7f07036a;

            /* JADX INFO: Added by JADX */
            public static final int tvRedPacketDetailCreatedAt = 0x7f07036b;

            /* JADX INFO: Added by JADX */
            public static final int tvRedPacketDetailEmpty = 0x7f07036c;

            /* JADX INFO: Added by JADX */
            public static final int tvRedPacketDetailStatus = 0x7f07036d;

            /* JADX INFO: Added by JADX */
            public static final int tvRedPacketDetailTitle = 0x7f07036e;

            /* JADX INFO: Added by JADX */
            public static final int tvRedPacketDetailTotalAmount = 0x7f07036f;

            /* JADX INFO: Added by JADX */
            public static final int tvRedPacketDetailTotalCount = 0x7f070370;

            /* JADX INFO: Added by JADX */
            public static final int tvRedPacketOpenHint = 0x7f070371;

            /* JADX INFO: Added by JADX */
            public static final int tvRedPacketOpenStatus = 0x7f070372;

            /* JADX INFO: Added by JADX */
            public static final int tvRedPacketOpenTip = 0x7f070373;

            /* JADX INFO: Added by JADX */
            public static final int tvRedPacketOpenTitle = 0x7f070374;

            /* JADX INFO: Added by JADX */
            public static final int tvRedPacketResult = 0x7f070375;

            /* JADX INFO: Added by JADX */
            public static final int tvRedPacketSendTitle = 0x7f070376;

            /* JADX INFO: Added by JADX */
            public static final int tvRedPacketTarget = 0x7f070377;

            /* JADX INFO: Added by JADX */
            public static final int tvRedPacketTitle = 0x7f070378;

            /* JADX INFO: Added by JADX */
            public static final int tvRegister = 0x7f070379;

            /* JADX INFO: Added by JADX */
            public static final int tvReportEvidence = 0x7f07037a;

            /* JADX INFO: Added by JADX */
            public static final int tvReportId = 0x7f07037b;

            /* JADX INFO: Added by JADX */
            public static final int tvReportReason = 0x7f07037c;

            /* JADX INFO: Added by JADX */
            public static final int tvReporterName = 0x7f07037d;

            /* JADX INFO: Added by JADX */
            public static final int tvReputationScore = 0x7f07037e;

            /* JADX INFO: Added by JADX */
            public static final int tvResourceAction = 0x7f07037f;

            /* JADX INFO: Added by JADX */
            public static final int tvResourceComment = 0x7f070380;

            /* JADX INFO: Added by JADX */
            public static final int tvResourceLike = 0x7f070381;

            /* JADX INFO: Added by JADX */
            public static final int tvResourceMeta = 0x7f070382;

            /* JADX INFO: Added by JADX */
            public static final int tvResourceName = 0x7f070383;

            /* JADX INFO: Added by JADX */
            public static final int tvResourceQuota = 0x7f070384;

            /* JADX INFO: Added by JADX */
            public static final int tvResourceSectionMeta = 0x7f070385;

            /* JADX INFO: Added by JADX */
            public static final int tvResourceSectionName = 0x7f070386;

            /* JADX INFO: Added by JADX */
            public static final int tvResourceSectionOwner = 0x7f070387;

            /* JADX INFO: Added by JADX */
            public static final int tvResourceSectionOwnerBadge = 0x7f070388;

            /* JADX INFO: Added by JADX */
            public static final int tvResourceSectionTitle = 0x7f070389;

            /* JADX INFO: Added by JADX */
            public static final int tvResourceSectionsTitle = 0x7f07038a;

            /* JADX INFO: Added by JADX */
            public static final int tvResourceSub = 0x7f07038b;

            /* JADX INFO: Added by JADX */
            public static final int tvResourceTitle = 0x7f07038c;

            /* JADX INFO: Added by JADX */
            public static final int tvResourceUploadStatus = 0x7f07038d;

            /* JADX INFO: Added by JADX */
            public static final int tvResourceUploader = 0x7f07038e;

            /* JADX INFO: Added by JADX */
            public static final int tvResourceUploaderBadge = 0x7f07038f;

            /* JADX INFO: Added by JADX */
            public static final int tvRole = 0x7f070390;

            /* JADX INFO: Added by JADX */
            public static final int tvRoleBadge = 0x7f070391;

            /* JADX INFO: Added by JADX */
            public static final int tvRulesBody = 0x7f070392;

            /* JADX INFO: Added by JADX */
            public static final int tvRulesTitle = 0x7f070393;

            /* JADX INFO: Added by JADX */
            public static final int tvSearchContext = 0x7f070394;

            /* JADX INFO: Added by JADX */
            public static final int tvSearchEmpty = 0x7f070395;

            /* JADX INFO: Added by JADX */
            public static final int tvSearchItemJump = 0x7f070396;

            /* JADX INFO: Added by JADX */
            public static final int tvSearchItemMeta = 0x7f070397;

            /* JADX INFO: Added by JADX */
            public static final int tvSearchItemPreview = 0x7f070398;

            /* JADX INFO: Added by JADX */
            public static final int tvSearchItemType = 0x7f070399;

            /* JADX INFO: Added by JADX */
            public static final int tvSearchSummary = 0x7f07039a;

            /* JADX INFO: Added by JADX */
            public static final int tvSearchTitle = 0x7f07039b;

            /* JADX INFO: Added by JADX */
            public static final int tvSectionTitle = 0x7f07039c;

            /* JADX INFO: Added by JADX */
            public static final int tvSenderBadge = 0x7f07039d;

            /* JADX INFO: Added by JADX */
            public static final int tvSenderName = 0x7f07039e;

            /* JADX INFO: Added by JADX */
            public static final int tvSenderTitle = 0x7f07039f;

            /* JADX INFO: Added by JADX */
            public static final int tvSignature = 0x7f0703a0;

            /* JADX INFO: Added by JADX */
            public static final int tvSpaceName = 0x7f0703a1;

            /* JADX INFO: Added by JADX */
            public static final int tvSpaceSignature = 0x7f0703a2;

            /* JADX INFO: Added by JADX */
            public static final int tvSpaceTitleBadge = 0x7f0703a3;

            /* JADX INFO: Added by JADX */
            public static final int tvSpaceUid = 0x7f0703a4;

            /* JADX INFO: Added by JADX */
            public static final int tvStageHint = 0x7f0703a5;

            /* JADX INFO: Added by JADX */
            public static final int tvStatementUploadHint = 0x7f0703a6;

            /* JADX INFO: Added by JADX */
            public static final int tvStatus = 0x7f0703a7;

            /* JADX INFO: Added by JADX */
            public static final int tvSubtitle = 0x7f0703a8;

            /* JADX INFO: Added by JADX */
            public static final int tvThemeColorValue = 0x7f0703a9;

            /* JADX INFO: Added by JADX */
            public static final int tvTime = 0x7f0703aa;

            /* JADX INFO: Added by JADX */
            public static final int tvTitle = 0x7f0703ab;

            /* JADX INFO: Added by JADX */
            public static final int tvTitleBadge = 0x7f0703ac;

            /* JADX INFO: Added by JADX */
            public static final int tvTypingText = 0x7f0703ad;

            /* JADX INFO: Added by JADX */
            public static final int tvUid = 0x7f0703ae;

            /* JADX INFO: Added by JADX */
            public static final int tvUnreadCount = 0x7f0703af;

            /* JADX INFO: Added by JADX */
            public static final int tvUpdatedAt = 0x7f0703b0;

            /* JADX INFO: Added by JADX */
            public static final int tvUserCacheSize = 0x7f0703b1;

            /* JADX INFO: Added by JADX */
            public static final int tvVideoProgress = 0x7f0703b2;

            /* JADX INFO: Added by JADX */
            public static final int tvVoiceCacheSize = 0x7f0703b3;

            /* JADX INFO: Added by JADX */
            public static final int tvVoiceDuration = 0x7f0703b4;

            /* JADX INFO: Added by JADX */
            public static final int tvVoiceTranscript = 0x7f0703b5;

            /* JADX INFO: Added by JADX */
            public static final int tvVoteSummary = 0x7f0703b6;

            /* JADX INFO: Added by JADX */
            public static final int tvWalletBalance = 0x7f0703b7;

            /* JADX INFO: Added by JADX */
            public static final int typingAvatarStack = 0x7f0703b8;

            /* JADX INFO: Added by JADX */
            public static final int typingBubble = 0x7f0703b9;

            /* JADX INFO: Added by JADX */
            public static final int typingDot1 = 0x7f0703ba;

            /* JADX INFO: Added by JADX */
            public static final int typingDot2 = 0x7f0703bb;

            /* JADX INFO: Added by JADX */
            public static final int typingDot3 = 0x7f0703bc;

            /* JADX INFO: Added by JADX */
            public static final int typingDots = 0x7f0703bd;

            /* JADX INFO: Added by JADX */
            public static final int up = 0x7f0703be;

            /* JADX INFO: Added by JADX */
            public static final int useLogo = 0x7f0703bf;

            /* JADX INFO: Added by JADX */
            public static final int vBackDot = 0x7f0703c0;

            /* JADX INFO: Added by JADX */
            public static final int vPastelPreviewBar = 0x7f0703c1;

            /* JADX INFO: Added by JADX */
            public static final int vSectionDot = 0x7f0703c2;

            /* JADX INFO: Added by JADX */
            public static final int video_progress_container = 0x7f0703c3;

            /* JADX INFO: Added by JADX */
            public static final int viewDiscoverMomentsDot = 0x7f0703c4;

            /* JADX INFO: Added by JADX */
            public static final int viewEmojiBatchMask = 0x7f0703c5;

            /* JADX INFO: Added by JADX */
            public static final int viewGroupAnnouncementCursorPad = 0x7f0703c6;

            /* JADX INFO: Added by JADX */
            public static final int viewMomentsNoticeDot = 0x7f0703c7;

            /* JADX INFO: Added by JADX */
            public static final int viewPager = 0x7f0703c8;

            /* JADX INFO: Added by JADX */
            public static final int viewpager = 0x7f0703c9;

            /* JADX INFO: Added by JADX */
            public static final int voice_row = 0x7f0703ca;

            /* JADX INFO: Added by JADX */
            public static final int vpMomentGallery = 0x7f0703cb;

            /* JADX INFO: Added by JADX */
            public static final int vvOldViewFullPlayer = 0x7f0703cc;

            /* JADX INFO: Added by JADX */
            public static final int vvOldViewPlayer = 0x7f0703cd;

            /* JADX INFO: Added by JADX */
            public static final int withText = 0x7f0703ce;

            /* JADX INFO: Added by JADX */
            public static final int wrap_content = 0x7f0703cf;

            /* JADX INFO: Added by JADX */
            public static final int wvOldViewUpProfile = 0x7f0703d0;
        }

        /* JADX INFO: Added by JADX */
        public static final class integer {

            /* JADX INFO: Added by JADX */
            public static final int abc_config_activityDefaultDur = 0x7f080000;

            /* JADX INFO: Added by JADX */
            public static final int abc_config_activityShortDur = 0x7f080001;

            /* JADX INFO: Added by JADX */
            public static final int status_bar_notification_info_maxnum = 0x7f080003;
        }

        /* JADX INFO: Added by JADX */
        public static final class layout {

            /* JADX INFO: Added by JADX */
            public static final int abc_action_bar_title_item = 0x7f090000;

            /* JADX INFO: Added by JADX */
            public static final int abc_action_menu_item_layout = 0x7f090003;

            /* JADX INFO: Added by JADX */
            public static final int abc_action_menu_layout = 0x7f090004;

            /* JADX INFO: Added by JADX */
            public static final int abc_action_mode_bar = 0x7f090005;

            /* JADX INFO: Added by JADX */
            public static final int abc_action_mode_close_item_material = 0x7f090006;

            /* JADX INFO: Added by JADX */
            public static final int abc_alert_dialog_button_bar_material = 0x7f090009;

            /* JADX INFO: Added by JADX */
            public static final int abc_alert_dialog_material = 0x7f09000a;

            /* JADX INFO: Added by JADX */
            public static final int abc_dialog_title_material = 0x7f09000b;

            /* JADX INFO: Added by JADX */
            public static final int abc_expanded_menu_layout = 0x7f09000c;

            /* JADX INFO: Added by JADX */
            public static final int abc_list_menu_item_checkbox = 0x7f09000d;

            /* JADX INFO: Added by JADX */
            public static final int abc_list_menu_item_icon = 0x7f09000e;

            /* JADX INFO: Added by JADX */
            public static final int abc_list_menu_item_layout = 0x7f09000f;

            /* JADX INFO: Added by JADX */
            public static final int abc_list_menu_item_radio = 0x7f090010;

            /* JADX INFO: Added by JADX */
            public static final int abc_popup_menu_header_item_layout = 0x7f090011;

            /* JADX INFO: Added by JADX */
            public static final int abc_popup_menu_item_layout = 0x7f090012;

            /* JADX INFO: Added by JADX */
            public static final int abc_screen_content_include = 0x7f090013;

            /* JADX INFO: Added by JADX */
            public static final int abc_screen_simple = 0x7f090014;

            /* JADX INFO: Added by JADX */
            public static final int abc_screen_simple_overlay_action_mode = 0x7f090015;

            /* JADX INFO: Added by JADX */
            public static final int abc_screen_toolbar = 0x7f090016;

            /* JADX INFO: Added by JADX */
            public static final int abc_search_dropdown_item_icons_2line = 0x7f090017;

            /* JADX INFO: Added by JADX */
            public static final int abc_search_view = 0x7f090018;

            /* JADX INFO: Added by JADX */
            public static final int abc_select_dialog_material = 0x7f090019;

            /* JADX INFO: Added by JADX */
            public static final int activity_account_management = 0x7f09001a;

            /* JADX INFO: Added by JADX */
            public static final int activity_add_friend = 0x7f09001b;

            /* JADX INFO: Added by JADX */
            public static final int activity_cache_settings = 0x7f09001c;

            /* JADX INFO: Added by JADX */
            public static final int activity_change_password = 0x7f09001d;

            /* JADX INFO: Added by JADX */
            public static final int activity_chat = 0x7f09001e;

            /* JADX INFO: Added by JADX */
            public static final int activity_chat_search = 0x7f09001f;

            /* JADX INFO: Added by JADX */
            public static final int activity_chat_settings = 0x7f090020;

            /* JADX INFO: Added by JADX */
            public static final int activity_crash = 0x7f090021;

            /* JADX INFO: Added by JADX */
            public static final int activity_data_settings = 0x7f090022;

            /* JADX INFO: Added by JADX */
            public static final int activity_device_management = 0x7f090023;

            /* JADX INFO: Added by JADX */
            public static final int activity_discover_settings = 0x7f090024;

            /* JADX INFO: Added by JADX */
            public static final int activity_emoji_picker = 0x7f090025;

            /* JADX INFO: Added by JADX */
            public static final int activity_emoji_plaza = 0x7f090026;

            /* JADX INFO: Added by JADX */
            public static final int activity_favorites = 0x7f090027;

            /* JADX INFO: Added by JADX */
            public static final int activity_feedback = 0x7f090028;

            /* JADX INFO: Added by JADX */
            public static final int activity_group_announcement = 0x7f090029;

            /* JADX INFO: Added by JADX */
            public static final int activity_group_chat = 0x7f09002a;

            /* JADX INFO: Added by JADX */
            public static final int activity_group_create = 0x7f09002b;

            /* JADX INFO: Added by JADX */
            public static final int activity_group_invite = 0x7f09002c;

            /* JADX INFO: Added by JADX */
            public static final int activity_group_manage = 0x7f09002d;

            /* JADX INFO: Added by JADX */
            public static final int activity_group_members = 0x7f09002e;

            /* JADX INFO: Added by JADX */
            public static final int activity_group_requests = 0x7f09002f;

            /* JADX INFO: Added by JADX */
            public static final int activity_image_preview = 0x7f090030;

            /* JADX INFO: Added by JADX */
            public static final int activity_login = 0x7f090031;

            /* JADX INFO: Added by JADX */
            public static final int activity_main = 0x7f090032;

            /* JADX INFO: Added by JADX */
            public static final int activity_moment_comments = 0x7f090033;

            /* JADX INFO: Added by JADX */
            public static final int activity_moment_compose = 0x7f090034;

            /* JADX INFO: Added by JADX */
            public static final int activity_moment_gallery = 0x7f090035;

            /* JADX INFO: Added by JADX */
            public static final int activity_moment_notices = 0x7f090036;

            /* JADX INFO: Added by JADX */
            public static final int activity_moments = 0x7f090037;

            /* JADX INFO: Added by JADX */
            public static final int activity_music_downloads = 0x7f090038;

            /* JADX INFO: Added by JADX */
            public static final int activity_music_manage = 0x7f090039;

            /* JADX INFO: Added by JADX */
            public static final int activity_music_player = 0x7f09003a;

            /* JADX INFO: Added by JADX */
            public static final int activity_music_plaza = 0x7f09003b;

            /* JADX INFO: Added by JADX */
            public static final int activity_notification_chat = 0x7f09003c;

            /* JADX INFO: Added by JADX */
            public static final int activity_notification_settings = 0x7f09003d;

            /* JADX INFO: Added by JADX */
            public static final int activity_old_view = 0x7f09003e;

            /* JADX INFO: Added by JADX */
            public static final int activity_old_view_detail = 0x7f09003f;

            /* JADX INFO: Added by JADX */
            public static final int activity_old_view_fullscreen = 0x7f090040;

            /* JADX INFO: Added by JADX */
            public static final int activity_old_view_list = 0x7f090041;

            /* JADX INFO: Added by JADX */
            public static final int activity_old_view_up_profile = 0x7f090042;

            /* JADX INFO: Added by JADX */
            public static final int activity_privacy_policy = 0x7f090043;

            /* JADX INFO: Added by JADX */
            public static final int activity_profile_edit = 0x7f090044;

            /* JADX INFO: Added by JADX */
            public static final int activity_profile_space_edit = 0x7f090045;

            /* JADX INFO: Added by JADX */
            public static final int activity_public_court = 0x7f090046;

            /* JADX INFO: Added by JADX */
            public static final int activity_public_court_case_detail = 0x7f090047;

            /* JADX INFO: Added by JADX */
            public static final int activity_qr_card = 0x7f090048;

            /* JADX INFO: Added by JADX */
            public static final int activity_qr_scan = 0x7f090049;

            /* JADX INFO: Added by JADX */
            public static final int activity_recover_password = 0x7f09004a;

            /* JADX INFO: Added by JADX */
            public static final int activity_red_packet_detail = 0x7f09004b;

            /* JADX INFO: Added by JADX */
            public static final int activity_red_packet_open = 0x7f09004c;

            /* JADX INFO: Added by JADX */
            public static final int activity_red_packet_send = 0x7f09004d;

            /* JADX INFO: Added by JADX */
            public static final int activity_register = 0x7f09004e;

            /* JADX INFO: Added by JADX */
            public static final int activity_report_progress = 0x7f09004f;

            /* JADX INFO: Added by JADX */
            public static final int activity_resource_section = 0x7f090050;

            /* JADX INFO: Added by JADX */
            public static final int activity_resource_sections = 0x7f090051;

            /* JADX INFO: Added by JADX */
            public static final int activity_settings = 0x7f090052;

            /* JADX INFO: Added by JADX */
            public static final int activity_support_settings = 0x7f090053;

            /* JADX INFO: Added by JADX */
            public static final int activity_ui_settings = 0x7f090054;

            /* JADX INFO: Added by JADX */
            public static final int activity_user_space = 0x7f090055;

            /* JADX INFO: Added by JADX */
            public static final int dialog_group_mention = 0x7f090056;

            /* JADX INFO: Added by JADX */
            public static final int dialog_public_court_statement = 0x7f090057;

            /* JADX INFO: Added by JADX */
            public static final int fragment_chats = 0x7f090058;

            /* JADX INFO: Added by JADX */
            public static final int fragment_discover = 0x7f090059;

            /* JADX INFO: Added by JADX */
            public static final int fragment_friends = 0x7f09005a;

            /* JADX INFO: Added by JADX */
            public static final int fragment_profile = 0x7f09005b;

            /* JADX INFO: Added by JADX */
            public static final int fragment_progress_list = 0x7f09005c;

            /* JADX INFO: Added by JADX */
            public static final int header_user_space = 0x7f09005d;

            /* JADX INFO: Added by JADX */
            public static final int item_chat_search_message = 0x7f09005e;

            /* JADX INFO: Added by JADX */
            public static final int item_device = 0x7f09005f;

            /* JADX INFO: Added by JADX */
            public static final int item_emoji = 0x7f090060;

            /* JADX INFO: Added by JADX */
            public static final int item_emoji_batch_select = 0x7f090061;

            /* JADX INFO: Added by JADX */
            public static final int item_emoji_plaza = 0x7f090062;

            /* JADX INFO: Added by JADX */
            public static final int item_favorite = 0x7f090063;

            /* JADX INFO: Added by JADX */
            public static final int item_friend = 0x7f090064;

            /* JADX INFO: Added by JADX */
            public static final int item_friend_request = 0x7f090065;

            /* JADX INFO: Added by JADX */
            public static final int item_friend_section_header = 0x7f090066;

            /* JADX INFO: Added by JADX */
            public static final int item_group_invite_friend = 0x7f090067;

            /* JADX INFO: Added by JADX */
            public static final int item_group_member = 0x7f090068;

            /* JADX INFO: Added by JADX */
            public static final int item_group_mention = 0x7f090069;

            /* JADX INFO: Added by JADX */
            public static final int item_group_message = 0x7f09006a;

            /* JADX INFO: Added by JADX */
            public static final int item_group_request = 0x7f09006b;

            /* JADX INFO: Added by JADX */
            public static final int item_message = 0x7f09006c;

            /* JADX INFO: Added by JADX */
            public static final int item_moment = 0x7f09006d;

            /* JADX INFO: Added by JADX */
            public static final int item_moment_comment = 0x7f09006e;

            /* JADX INFO: Added by JADX */
            public static final int item_moment_notice = 0x7f09006f;

            /* JADX INFO: Added by JADX */
            public static final int item_music_download = 0x7f090070;

            /* JADX INFO: Added by JADX */
            public static final int item_music_manage = 0x7f090071;

            /* JADX INFO: Added by JADX */
            public static final int item_music_plaza = 0x7f090072;

            /* JADX INFO: Added by JADX */
            public static final int item_notification = 0x7f090073;

            /* JADX INFO: Added by JADX */
            public static final int item_old_view_comment = 0x7f090074;

            /* JADX INFO: Added by JADX */
            public static final int item_old_view_video = 0x7f090075;

            /* JADX INFO: Added by JADX */
            public static final int item_pastel_chat_row = 0x7f090076;

            /* JADX INFO: Added by JADX */
            public static final int item_pastel_folded_avatar = 0x7f090077;

            /* JADX INFO: Added by JADX */
            public static final int item_pastel_folded_tile = 0x7f090078;

            /* JADX INFO: Added by JADX */
            public static final int item_pastel_news_row = 0x7f090079;

            /* JADX INFO: Added by JADX */
            public static final int item_progress_row = 0x7f09007a;

            /* JADX INFO: Added by JADX */
            public static final int item_public_court_case = 0x7f09007b;

            /* JADX INFO: Added by JADX */
            public static final int item_public_court_discussion = 0x7f09007c;

            /* JADX INFO: Added by JADX */
            public static final int item_public_court_footer = 0x7f09007d;

            /* JADX INFO: Added by JADX */
            public static final int item_public_court_merged_report = 0x7f09007e;

            /* JADX INFO: Added by JADX */
            public static final int item_public_court_rules_header = 0x7f09007f;

            /* JADX INFO: Added by JADX */
            public static final int item_public_court_statement = 0x7f090080;

            /* JADX INFO: Added by JADX */
            public static final int item_recent_chat = 0x7f090081;

            /* JADX INFO: Added by JADX */
            public static final int item_red_packet_claim = 0x7f090082;

            /* JADX INFO: Added by JADX */
            public static final int item_resource = 0x7f090083;

            /* JADX INFO: Added by JADX */
            public static final int item_resource_section = 0x7f090084;

            /* JADX INFO: Added by JADX */
            public static final int item_section_header = 0x7f090085;

            /* JADX INFO: Added by JADX */
            public static final int item_typing_indicator = 0x7f090086;

            /* JADX INFO: Added by JADX */
            public static final int layout_moments_header_wechat = 0x7f090087;

            /* JADX INFO: Added by JADX */
            public static final int list_load_more = 0x7f090088;

            /* JADX INFO: Added by JADX */
            public static final int notification_media_action = 0x7f090089;

            /* JADX INFO: Added by JADX */
            public static final int notification_media_cancel_action = 0x7f09008a;

            /* JADX INFO: Added by JADX */
            public static final int notification_template_big_media = 0x7f09008b;

            /* JADX INFO: Added by JADX */
            public static final int notification_template_big_media_narrow = 0x7f09008c;

            /* JADX INFO: Added by JADX */
            public static final int notification_template_lines = 0x7f09008d;

            /* JADX INFO: Added by JADX */
            public static final int notification_template_media = 0x7f09008e;

            /* JADX INFO: Added by JADX */
            public static final int notification_template_part_chronometer = 0x7f09008f;

            /* JADX INFO: Added by JADX */
            public static final int notification_template_part_time = 0x7f090090;

            /* JADX INFO: Added by JADX */
            public static final int select_dialog_item_material = 0x7f090091;

            /* JADX INFO: Added by JADX */
            public static final int select_dialog_multichoice_material = 0x7f090092;

            /* JADX INFO: Added by JADX */
            public static final int select_dialog_singlechoice_material = 0x7f090093;

            /* JADX INFO: Added by JADX */
            public static final int support_simple_spinner_dropdown_item = 0x7f090094;

            /* JADX INFO: Added by JADX */
            public static final int view_old_view_detail_header = 0x7f090095;

            /* JADX INFO: Added by JADX */
            public static final int view_pastel_section_chats = 0x7f090096;

            /* JADX INFO: Added by JADX */
            public static final int view_pastel_section_folded = 0x7f090097;

            /* JADX INFO: Added by JADX */
            public static final int view_pastel_section_news = 0x7f090098;

            /* JADX INFO: Added by JADX */
            public static final int view_tab_item = 0x7f090099;

            /* JADX INFO: Added by JADX */
            public static final int view_top_status_bar = 0x7f09009a;

            /* JADX INFO: Added by JADX */
            public static final int view_ui_settings_bubble = 0x7f09009b;

            /* JADX INFO: Added by JADX */
            public static final int view_ui_settings_rounded = 0x7f09009c;
        }

        /* JADX INFO: Added by JADX */
        public static final class menu {

            /* JADX INFO: Added by JADX */
            public static final int menu_old_view = 0x7f0a0000;

            /* JADX INFO: Added by JADX */
            public static final int resource_item_actions = 0x7f0a0001;
        }

        /* JADX INFO: Added by JADX */
        public static final class mipmap {

            /* JADX INFO: Added by JADX */
            public static final int ic_launcher = 0x7f0b0000;
        }

        /* JADX INFO: Added by JADX */
        public static final class string {

            /* JADX INFO: Added by JADX */
            public static final int abc_action_bar_up_description = 0x7f0c0003;

            /* JADX INFO: Added by JADX */
            public static final int abc_action_menu_overflow_description = 0x7f0c0004;

            /* JADX INFO: Added by JADX */
            public static final int abc_action_mode_done = 0x7f0c0005;

            /* JADX INFO: Added by JADX */
            public static final int abc_capital_off = 0x7f0c0008;

            /* JADX INFO: Added by JADX */
            public static final int abc_capital_on = 0x7f0c0009;

            /* JADX INFO: Added by JADX */
            public static final int abc_font_family_title_material = 0x7f0c0015;

            /* JADX INFO: Added by JADX */
            public static final int abc_search_hint = 0x7f0c0016;

            /* JADX INFO: Added by JADX */
            public static final int abc_searchview_description_clear = 0x7f0c0017;

            /* JADX INFO: Added by JADX */
            public static final int abc_searchview_description_search = 0x7f0c0019;

            /* JADX INFO: Added by JADX */
            public static final int abc_searchview_description_submit = 0x7f0c001a;

            /* JADX INFO: Added by JADX */
            public static final int abc_searchview_description_voice = 0x7f0c001b;

            /* JADX INFO: Added by JADX */
            public static final int abc_toolbar_collapse_description = 0x7f0c001e;

            /* JADX INFO: Added by JADX */
            public static final int action_cancel = 0x7f0c0020;

            /* JADX INFO: Added by JADX */
            public static final int action_confirm = 0x7f0c0021;

            /* JADX INFO: Added by JADX */
            public static final int action_login = 0x7f0c0022;

            /* JADX INFO: Added by JADX */
            public static final int action_pick_avatar = 0x7f0c0023;

            /* JADX INFO: Added by JADX */
            public static final int action_refresh = 0x7f0c0024;

            /* JADX INFO: Added by JADX */
            public static final int action_register = 0x7f0c0025;

            /* JADX INFO: Added by JADX */
            public static final int action_to_login = 0x7f0c0026;

            /* JADX INFO: Added by JADX */
            public static final int action_to_register = 0x7f0c0027;

            /* JADX INFO: Added by JADX */
            public static final int action_uploading = 0x7f0c0028;

            /* JADX INFO: Added by JADX */
            public static final int add_friend = 0x7f0c0029;

            /* JADX INFO: Added by JADX */
            public static final int add_friend_action = 0x7f0c002a;

            /* JADX INFO: Added by JADX */
            public static final int add_friend_hint = 0x7f0c002b;

            /* JADX INFO: Added by JADX */
            public static final int add_friend_loading = 0x7f0c002c;

            /* JADX INFO: Added by JADX */
            public static final int add_friend_sent = 0x7f0c002d;

            /* JADX INFO: Added by JADX */
            public static final int add_friend_title = 0x7f0c002e;

            /* JADX INFO: Added by JADX */
            public static final int announcement_empty = 0x7f0c002f;

            /* JADX INFO: Added by JADX */
            public static final int announcement_title = 0x7f0c0030;

            /* JADX INFO: Added by JADX */
            public static final int announcement_updated_at = 0x7f0c0031;

            /* JADX INFO: Added by JADX */
            public static final int app_name = 0x7f0c0032;

            /* JADX INFO: Added by JADX */
            public static final int call_permission_needed = 0x7f0c003e;

            /* JADX INFO: Added by JADX */
            public static final int captcha_hint = 0x7f0c0047;

            /* JADX INFO: Added by JADX */
            public static final int captcha_too_fast = 0x7f0c004b;

            /* JADX INFO: Added by JADX */
            public static final int chat_background_clear = 0x7f0c004c;

            /* JADX INFO: Added by JADX */
            public static final int chat_background_clear_success = 0x7f0c004d;

            /* JADX INFO: Added by JADX */
            public static final int chat_background_global_group_title = 0x7f0c004e;

            /* JADX INFO: Added by JADX */
            public static final int chat_background_global_title = 0x7f0c004f;

            /* JADX INFO: Added by JADX */
            public static final int chat_background_set = 0x7f0c0050;

            /* JADX INFO: Added by JADX */
            public static final int chat_background_set_success = 0x7f0c0052;

            /* JADX INFO: Added by JADX */
            public static final int chat_background_status_empty = 0x7f0c0053;

            /* JADX INFO: Added by JADX */
            public static final int chat_background_status_set = 0x7f0c0054;

            /* JADX INFO: Added by JADX */
            public static final int chat_background_title = 0x7f0c0055;

            /* JADX INFO: Added by JADX */
            public static final int chat_delete_friend = 0x7f0c0059;

            /* JADX INFO: Added by JADX */
            public static final int chat_delete_friend_confirm = 0x7f0c005a;

            /* JADX INFO: Added by JADX */
            public static final int chat_delete_friend_done = 0x7f0c005b;

            /* JADX INFO: Added by JADX */
            public static final int chat_deleting = 0x7f0c005c;

            /* JADX INFO: Added by JADX */
            public static final int chat_emoji = 0x7f0c005d;

            /* JADX INFO: Added by JADX */
            public static final int chat_hold_release = 0x7f0c0069;

            /* JADX INFO: Added by JADX */
            public static final int chat_hold_release_for_voice_option = 0x7f0c006a;

            /* JADX INFO: Added by JADX */
            public static final int chat_hold_to_talk = 0x7f0c006c;

            /* JADX INFO: Added by JADX */
            public static final int chat_image = 0x7f0c006d;

            /* JADX INFO: Added by JADX */
            public static final int chat_mute_on = 0x7f0c0079;

            /* JADX INFO: Added by JADX */
            public static final int chat_muted = 0x7f0c007a;

            /* JADX INFO: Added by JADX */
            public static final int chat_red_packet = 0x7f0c007e;

            /* JADX INFO: Added by JADX */
            public static final int chat_unmuted = 0x7f0c0085;

            /* JADX INFO: Added by JADX */
            public static final int chat_video = 0x7f0c0088;

            /* JADX INFO: Added by JADX */
            public static final int chat_voice = 0x7f0c0089;

            /* JADX INFO: Added by JADX */
            public static final int chat_voice_call = 0x7f0c008a;

            /* JADX INFO: Added by JADX */
            public static final int chat_voice_failed = 0x7f0c008b;

            /* JADX INFO: Added by JADX */
            public static final int chat_voice_length_format = 0x7f0c008c;

            /* JADX INFO: Added by JADX */
            public static final int chat_voice_option_cancel = 0x7f0c008d;

            /* JADX INFO: Added by JADX */
            public static final int chat_voice_option_title = 0x7f0c008e;

            /* JADX INFO: Added by JADX */
            public static final int chat_voice_option_transcribe = 0x7f0c008f;

            /* JADX INFO: Added by JADX */
            public static final int chat_voice_play = 0x7f0c0090;

            /* JADX INFO: Added by JADX */
            public static final int chat_voice_recording = 0x7f0c0091;

            /* JADX INFO: Added by JADX */
            public static final int chat_voice_send_canceled = 0x7f0c0092;

            /* JADX INFO: Added by JADX */
            public static final int chat_voice_stop = 0x7f0c0093;

            /* JADX INFO: Added by JADX */
            public static final int chat_voice_transcribe_edit = 0x7f0c0094;

            /* JADX INFO: Added by JADX */
            public static final int chat_voice_transcribe_empty = 0x7f0c0095;

            /* JADX INFO: Added by JADX */
            public static final int chat_voice_transcribe_failed = 0x7f0c0096;

            /* JADX INFO: Added by JADX */
            public static final int chat_voice_transcribe_filled = 0x7f0c0097;

            /* JADX INFO: Added by JADX */
            public static final int chat_voice_transcribe_message_ready = 0x7f0c0098;

            /* JADX INFO: Added by JADX */
            public static final int chat_voice_transcribe_ready_message = 0x7f0c0099;

            /* JADX INFO: Added by JADX */
            public static final int chat_voice_transcribe_ready_title = 0x7f0c009a;

            /* JADX INFO: Added by JADX */
            public static final int chat_voice_transcribe_send_now = 0x7f0c009b;

            /* JADX INFO: Added by JADX */
            public static final int chat_voice_transcribe_sent = 0x7f0c009c;

            /* JADX INFO: Added by JADX */
            public static final int chat_voice_transcribing = 0x7f0c009d;

            /* JADX INFO: Added by JADX */
            public static final int crash_last_title = 0x7f0c00aa;

            /* JADX INFO: Added by JADX */
            public static final int email_code_hint = 0x7f0c00b8;

            /* JADX INFO: Added by JADX */
            public static final int email_hint = 0x7f0c00b9;

            /* JADX INFO: Added by JADX */
            public static final int emoji_action_delete = 0x7f0c00ba;

            /* JADX INFO: Added by JADX */
            public static final int emoji_action_move = 0x7f0c00bb;

            /* JADX INFO: Added by JADX */
            public static final int emoji_delete_confirm = 0x7f0c00bc;

            /* JADX INFO: Added by JADX */
            public static final int emoji_delete_default_blocked = 0x7f0c00bd;

            /* JADX INFO: Added by JADX */
            public static final int emoji_delete_done = 0x7f0c00be;

            /* JADX INFO: Added by JADX */
            public static final int emoji_delete_failed = 0x7f0c00bf;

            /* JADX INFO: Added by JADX */
            public static final int emoji_delete_title = 0x7f0c00c0;

            /* JADX INFO: Added by JADX */
            public static final int error_add_friend = 0x7f0c00c4;

            /* JADX INFO: Added by JADX */
            public static final int error_create_group = 0x7f0c00c5;

            /* JADX INFO: Added by JADX */
            public static final int error_input = 0x7f0c00c6;

            /* JADX INFO: Added by JADX */
            public static final int error_invalid_captcha = 0x7f0c00c7;

            /* JADX INFO: Added by JADX */
            public static final int error_invalid_email = 0x7f0c00c8;

            /* JADX INFO: Added by JADX */
            public static final int error_invalid_email_code = 0x7f0c00c9;

            /* JADX INFO: Added by JADX */
            public static final int error_invalid_id = 0x7f0c00ca;

            /* JADX INFO: Added by JADX */
            public static final int error_invalid_phone = 0x7f0c00cb;

            /* JADX INFO: Added by JADX */
            public static final int error_invalid_username = 0x7f0c00cc;

            /* JADX INFO: Added by JADX */
            public static final int error_login = 0x7f0c00cd;

            /* JADX INFO: Added by JADX */
            public static final int error_network = 0x7f0c00ce;

            /* JADX INFO: Added by JADX */
            public static final int error_not_logged_in = 0x7f0c00cf;

            /* JADX INFO: Added by JADX */
            public static final int error_password_mismatch = 0x7f0c00d0;

            /* JADX INFO: Added by JADX */
            public static final int error_password_too_short = 0x7f0c00d1;

            /* JADX INFO: Added by JADX */
            public static final int error_pick_image = 0x7f0c00d2;

            /* JADX INFO: Added by JADX */
            public static final int error_qq_email_only = 0x7f0c00d3;

            /* JADX INFO: Added by JADX */
            public static final int error_register = 0x7f0c00d4;

            /* JADX INFO: Added by JADX */
            public static final int error_required = 0x7f0c00d5;

            /* JADX INFO: Added by JADX */
            public static final int error_save_image = 0x7f0c00d6;

            /* JADX INFO: Added by JADX */
            public static final int forward_bubble_more_count = 0x7f0c00d7;

            /* JADX INFO: Added by JADX */
            public static final int forward_bubble_tap_to_open = 0x7f0c00d8;

            /* JADX INFO: Added by JADX */
            public static final int forward_bundle_title = 0x7f0c00d9;

            /* JADX INFO: Added by JADX */
            public static final int forward_detail_close = 0x7f0c00da;

            /* JADX INFO: Added by JADX */
            public static final int forward_failed = 0x7f0c00db;

            /* JADX INFO: Added by JADX */
            public static final int forward_fallback_count = 0x7f0c00dc;

            /* JADX INFO: Added by JADX */
            public static final int forward_multi_cancel = 0x7f0c00dd;

            /* JADX INFO: Added by JADX */
            public static final int forward_multi_count = 0x7f0c00de;

            /* JADX INFO: Added by JADX */
            public static final int forward_network_unavailable = 0x7f0c00df;

            /* JADX INFO: Added by JADX */
            public static final int forward_no_messages = 0x7f0c00e0;

            /* JADX INFO: Added by JADX */
            public static final int forward_not_login = 0x7f0c00e1;

            /* JADX INFO: Added by JADX */
            public static final int forward_pick_friend_title = 0x7f0c00e2;

            /* JADX INFO: Added by JADX */
            public static final int forward_pick_group_title = 0x7f0c00e3;

            /* JADX INFO: Added by JADX */
            public static final int forward_pick_target_title = 0x7f0c00e4;

            /* JADX INFO: Added by JADX */
            public static final int forward_preview_empty = 0x7f0c00e5;

            /* JADX INFO: Added by JADX */
            public static final int forward_preview_nested_bundle = 0x7f0c00e6;

            /* JADX INFO: Added by JADX */
            public static final int forward_sender_me = 0x7f0c00e7;

            /* JADX INFO: Added by JADX */
            public static final int forward_sender_unknown = 0x7f0c00e8;

            /* JADX INFO: Added by JADX */
            public static final int forward_success = 0x7f0c00e9;

            /* JADX INFO: Added by JADX */
            public static final int forward_target_friend = 0x7f0c00ea;

            /* JADX INFO: Added by JADX */
            public static final int forward_target_group = 0x7f0c00eb;

            /* JADX INFO: Added by JADX */
            public static final int friend_request_conflict = 0x7f0c00ed;

            /* JADX INFO: Added by JADX */
            public static final int friend_request_empty = 0x7f0c00ee;

            /* JADX INFO: Added by JADX */
            public static final int friend_request_failed = 0x7f0c00ef;

            /* JADX INFO: Added by JADX */
            public static final int friend_request_not_found = 0x7f0c00f0;

            /* JADX INFO: Added by JADX */
            public static final int friend_request_pending_processing = 0x7f0c00f1;

            /* JADX INFO: Added by JADX */
            public static final int friend_request_send = 0x7f0c00f3;

            /* JADX INFO: Added by JADX */
            public static final int friend_request_sent = 0x7f0c00f4;

            /* JADX INFO: Added by JADX */
            public static final int friend_requests_title = 0x7f0c00f5;

            /* JADX INFO: Added by JADX */
            public static final int friend_uid_hint = 0x7f0c00f6;

            /* JADX INFO: Added by JADX */
            public static final int friends_add_contact_or_group = 0x7f0c00f7;

            /* JADX INFO: Added by JADX */
            public static final int friends_search_hint_full = 0x7f0c00f8;

            /* JADX INFO: Added by JADX */
            public static final int group_announcement_banner_prefix = 0x7f0c00f9;

            /* JADX INFO: Added by JADX */
            public static final int group_announcement_close = 0x7f0c00fa;

            /* JADX INFO: Added by JADX */
            public static final int group_announcement_count_format = 0x7f0c00fb;

            /* JADX INFO: Added by JADX */
            public static final int group_announcement_cursor_pad = 0x7f0c00fc;

            /* JADX INFO: Added by JADX */
            public static final int group_announcement_delete = 0x7f0c00fd;

            /* JADX INFO: Added by JADX */
            public static final int group_announcement_delete_confirm = 0x7f0c00fe;

            /* JADX INFO: Added by JADX */
            public static final int group_announcement_delete_failed = 0x7f0c00ff;

            /* JADX INFO: Added by JADX */
            public static final int group_announcement_delete_success = 0x7f0c0100;

            /* JADX INFO: Added by JADX */
            public static final int group_announcement_edit = 0x7f0c0101;

            /* JADX INFO: Added by JADX */
            public static final int group_announcement_empty = 0x7f0c0102;

            /* JADX INFO: Added by JADX */
            public static final int group_announcement_group_format = 0x7f0c0103;

            /* JADX INFO: Added by JADX */
            public static final int group_announcement_hint = 0x7f0c0104;

            /* JADX INFO: Added by JADX */
            public static final int group_announcement_mode_optional = 0x7f0c0105;

            /* JADX INFO: Added by JADX */
            public static final int group_announcement_mode_required = 0x7f0c0106;

            /* JADX INFO: Added by JADX */
            public static final int group_announcement_no_change = 0x7f0c0107;

            /* JADX INFO: Added by JADX */
            public static final int group_announcement_preview_format = 0x7f0c0108;

            /* JADX INFO: Added by JADX */
            public static final int group_announcement_publish = 0x7f0c0109;

            /* JADX INFO: Added by JADX */
            public static final int group_announcement_publish_failed = 0x7f0c010a;

            /* JADX INFO: Added by JADX */
            public static final int group_announcement_publish_success = 0x7f0c010b;

            /* JADX INFO: Added by JADX */
            public static final int group_announcement_read = 0x7f0c010c;

            /* JADX INFO: Added by JADX */
            public static final int group_announcement_title = 0x7f0c010d;

            /* JADX INFO: Added by JADX */
            public static final int group_avatar_updated = 0x7f0c010e;

            /* JADX INFO: Added by JADX */
            public static final int group_id_empty = 0x7f0c010f;

            /* JADX INFO: Added by JADX */
            public static final int group_id_hint = 0x7f0c0110;

            /* JADX INFO: Added by JADX */
            public static final int group_invite_loading = 0x7f0c0111;

            /* JADX INFO: Added by JADX */
            public static final int group_invite_submit = 0x7f0c0112;

            /* JADX INFO: Added by JADX */
            public static final int group_invite_success = 0x7f0c0113;

            /* JADX INFO: Added by JADX */
            public static final int group_invite_title = 0x7f0c0114;

            /* JADX INFO: Added by JADX */
            public static final int group_join_failed = 0x7f0c0115;

            /* JADX INFO: Added by JADX */
            public static final int group_join_pending = 0x7f0c0116;

            /* JADX INFO: Added by JADX */
            public static final int group_joined = 0x7f0c0117;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_add = 0x7f0c0118;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_add_failed = 0x7f0c0119;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_admin_empty = 0x7f0c011a;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_admin_only = 0x7f0c011b;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_admins = 0x7f0c011c;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_avatar = 0x7f0c011d;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_clear_local = 0x7f0c011e;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_clear_local_confirm = 0x7f0c011f;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_clear_local_done = 0x7f0c0120;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_clear_local_missing = 0x7f0c0121;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_dissolve = 0x7f0c0122;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_dissolve_action = 0x7f0c0123;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_dissolve_confirm = 0x7f0c0124;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_dissolve_failed = 0x7f0c0125;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_dissolve_loading = 0x7f0c0126;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_dissolve_success = 0x7f0c0127;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_exit = 0x7f0c0128;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_exit_action = 0x7f0c0129;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_exit_confirm = 0x7f0c012a;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_exit_failed = 0x7f0c012b;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_exit_loading = 0x7f0c012c;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_exit_owner = 0x7f0c012d;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_exit_success = 0x7f0c012e;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_join_approval = 0x7f0c012f;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_members = 0x7f0c0130;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_mute = 0x7f0c0131;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_mute_all = 0x7f0c0132;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_mute_all_off = 0x7f0c0133;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_mute_all_on = 0x7f0c0134;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_name_failed = 0x7f0c0135;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_name_hint = 0x7f0c0136;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_name_success = 0x7f0c0137;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_name_title = 0x7f0c0138;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_owner_only = 0x7f0c0139;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_remove = 0x7f0c013a;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_remove_failed = 0x7f0c013b;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_rename_share = 0x7f0c013c;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_rename_title = 0x7f0c013d;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_title = 0x7f0c013e;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_update_failed = 0x7f0c013f;

            /* JADX INFO: Added by JADX */
            public static final int group_manage_update_loading = 0x7f0c0140;

            /* JADX INFO: Added by JADX */
            public static final int group_member_mute = 0x7f0c0141;

            /* JADX INFO: Added by JADX */
            public static final int group_member_mute_owner_blocked = 0x7f0c0142;

            /* JADX INFO: Added by JADX */
            public static final int group_member_mute_self_blocked = 0x7f0c0143;

            /* JADX INFO: Added by JADX */
            public static final int group_member_mute_success = 0x7f0c0144;

            /* JADX INFO: Added by JADX */
            public static final int group_member_muted = 0x7f0c0145;

            /* JADX INFO: Added by JADX */
            public static final int group_member_unmute = 0x7f0c0146;

            /* JADX INFO: Added by JADX */
            public static final int group_member_unmute_success = 0x7f0c0147;

            /* JADX INFO: Added by JADX */
            public static final int group_not_found = 0x7f0c0148;

            /* JADX INFO: Added by JADX */
            public static final int group_notice_count = 0x7f0c0149;

            /* JADX INFO: Added by JADX */
            public static final int group_notice_subtitle = 0x7f0c014a;

            /* JADX INFO: Added by JADX */
            public static final int group_notice_title = 0x7f0c014b;

            /* JADX INFO: Added by JADX */
            public static final int group_owner_tag = 0x7f0c014c;

            /* JADX INFO: Added by JADX */
            public static final int group_request_accept = 0x7f0c014d;

            /* JADX INFO: Added by JADX */
            public static final int group_request_reject = 0x7f0c014e;

            /* JADX INFO: Added by JADX */
            public static final int group_request_reject_reason_hint = 0x7f0c014f;

            /* JADX INFO: Added by JADX */
            public static final int group_request_reject_reason_title = 0x7f0c0150;

            /* JADX INFO: Added by JADX */
            public static final int group_requests_empty = 0x7f0c0151;

            /* JADX INFO: Added by JADX */
            public static final int group_requests_title = 0x7f0c0152;

            /* JADX INFO: Added by JADX */
            public static final int id_limit_tip = 0x7f0c0153;

            /* JADX INFO: Added by JADX */
            public static final int image_or_emoji_too_large = 0x7f0c0154;

            /* JADX INFO: Added by JADX */
            public static final int join_group_action = 0x7f0c0159;

            /* JADX INFO: Added by JADX */
            public static final int join_group_title = 0x7f0c015f;

            /* JADX INFO: Added by JADX */
            public static final int load_more_messages = 0x7f0c016c;

            /* JADX INFO: Added by JADX */
            public static final int login_button = 0x7f0c016d;

            /* JADX INFO: Added by JADX */
            public static final int login_helper = 0x7f0c016e;

            /* JADX INFO: Added by JADX */
            public static final int login_loading = 0x7f0c016f;

            /* JADX INFO: Added by JADX */
            public static final int login_subtitle = 0x7f0c0170;

            /* JADX INFO: Added by JADX */
            public static final int login_title = 0x7f0c0171;

            /* JADX INFO: Added by JADX */
            public static final int menu_gallery = 0x7f0c017c;

            /* JADX INFO: Added by JADX */
            public static final int menu_home = 0x7f0c017d;

            /* JADX INFO: Added by JADX */
            public static final int menu_slideshow = 0x7f0c017e;

            /* JADX INFO: Added by JADX */
            public static final int message_action_add_emoji = 0x7f0c017f;

            /* JADX INFO: Added by JADX */
            public static final int message_action_copy = 0x7f0c0180;

            /* JADX INFO: Added by JADX */
            public static final int message_action_favorite = 0x7f0c0181;

            /* JADX INFO: Added by JADX */
            public static final int message_action_forward = 0x7f0c0182;

            /* JADX INFO: Added by JADX */
            public static final int message_action_multi_forward = 0x7f0c0183;

            /* JADX INFO: Added by JADX */
            public static final int message_action_reply = 0x7f0c0184;

            /* JADX INFO: Added by JADX */
            public static final int message_action_revoke = 0x7f0c0185;

            /* JADX INFO: Added by JADX */
            public static final int message_action_save = 0x7f0c0186;

            /* JADX INFO: Added by JADX */
            public static final int message_call_tag = 0x7f0c0187;

            /* JADX INFO: Added by JADX */
            public static final int message_copy_done = 0x7f0c0188;

            /* JADX INFO: Added by JADX */
            public static final int message_emoji_tag = 0x7f0c0189;

            /* JADX INFO: Added by JADX */
            public static final int message_file_action_download = 0x7f0c018a;

            /* JADX INFO: Added by JADX */
            public static final int message_file_download_confirm = 0x7f0c018b;

            /* JADX INFO: Added by JADX */
            public static final int message_file_download_done = 0x7f0c018c;

            /* JADX INFO: Added by JADX */
            public static final int message_file_download_failed = 0x7f0c018d;

            /* JADX INFO: Added by JADX */
            public static final int message_file_download_ok = 0x7f0c018e;

            /* JADX INFO: Added by JADX */
            public static final int message_file_download_title = 0x7f0c018f;

            /* JADX INFO: Added by JADX */
            public static final int message_file_hint = 0x7f0c0190;

            /* JADX INFO: Added by JADX */
            public static final int message_file_tag = 0x7f0c0191;

            /* JADX INFO: Added by JADX */
            public static final int message_file_unknown = 0x7f0c0192;

            /* JADX INFO: Added by JADX */
            public static final int message_image_tag = 0x7f0c0193;

            /* JADX INFO: Added by JADX */
            public static final int message_need_friend = 0x7f0c0194;

            /* JADX INFO: Added by JADX */
            public static final int message_recall_failed = 0x7f0c0195;

            /* JADX INFO: Added by JADX */
            public static final int message_recall_timeout = 0x7f0c0196;

            /* JADX INFO: Added by JADX */
            public static final int message_recalled = 0x7f0c0197;

            /* JADX INFO: Added by JADX */
            public static final int message_recalled_member = 0x7f0c0198;

            /* JADX INFO: Added by JADX */
            public static final int message_recalled_other = 0x7f0c0199;

            /* JADX INFO: Added by JADX */
            public static final int message_recalled_self = 0x7f0c019a;

            /* JADX INFO: Added by JADX */
            public static final int message_red_packet_amount = 0x7f0c019b;

            /* JADX INFO: Added by JADX */
            public static final int message_red_packet_count = 0x7f0c019c;

            /* JADX INFO: Added by JADX */
            public static final int message_red_packet_tag = 0x7f0c019d;

            /* JADX INFO: Added by JADX */
            public static final int message_save_done = 0x7f0c019e;

            /* JADX INFO: Added by JADX */
            public static final int message_save_failed = 0x7f0c019f;

            /* JADX INFO: Added by JADX */
            public static final int message_save_need_permission = 0x7f0c01a0;

            /* JADX INFO: Added by JADX */
            public static final int message_send_failed = 0x7f0c01a1;

            /* JADX INFO: Added by JADX */
            public static final int message_status_sent = 0x7f0c01a2;

            /* JADX INFO: Added by JADX */
            public static final int message_video_play_failed = 0x7f0c01a3;

            /* JADX INFO: Added by JADX */
            public static final int message_video_tag = 0x7f0c01a4;

            /* JADX INFO: Added by JADX */
            public static final int message_voice_tag = 0x7f0c01a5;

            /* JADX INFO: Added by JADX */
            public static final int moments_add_image = 0x7f0c01a6;

            /* JADX INFO: Added by JADX */
            public static final int moments_change_cover = 0x7f0c01a7;

            /* JADX INFO: Added by JADX */
            public static final int moments_comment = 0x7f0c01a8;

            /* JADX INFO: Added by JADX */
            public static final int moments_comment_delete_confirm = 0x7f0c01a9;

            /* JADX INFO: Added by JADX */
            public static final int moments_comment_delete_failed = 0x7f0c01aa;

            /* JADX INFO: Added by JADX */
            public static final int moments_comment_delete_success = 0x7f0c01ab;

            /* JADX INFO: Added by JADX */
            public static final int moments_comment_delete_title = 0x7f0c01ac;

            /* JADX INFO: Added by JADX */
            public static final int moments_comment_empty = 0x7f0c01ad;

            /* JADX INFO: Added by JADX */
            public static final int moments_comment_hint = 0x7f0c01ae;

            /* JADX INFO: Added by JADX */
            public static final int moments_comment_send = 0x7f0c01af;

            /* JADX INFO: Added by JADX */
            public static final int moments_comment_user_placeholder = 0x7f0c01b0;

            /* JADX INFO: Added by JADX */
            public static final int moments_delete_action = 0x7f0c01b1;

            /* JADX INFO: Added by JADX */
            public static final int moments_delete_confirm = 0x7f0c01b2;

            /* JADX INFO: Added by JADX */
            public static final int moments_delete_loading = 0x7f0c01b3;

            /* JADX INFO: Added by JADX */
            public static final int moments_delete_title = 0x7f0c01b4;

            /* JADX INFO: Added by JADX */
            public static final int moments_empty = 0x7f0c01b5;

            /* JADX INFO: Added by JADX */
            public static final int moments_image_limit = 0x7f0c01b6;

            /* JADX INFO: Added by JADX */
            public static final int moments_image_too_large = 0x7f0c01b7;

            /* JADX INFO: Added by JADX */
            public static final int moments_like = 0x7f0c01b8;

            /* JADX INFO: Added by JADX */
            public static final int moments_like_count = 0x7f0c01b9;

            /* JADX INFO: Added by JADX */
            public static final int moments_load_more = 0x7f0c01ba;

            /* JADX INFO: Added by JADX */
            public static final int moments_loading = 0x7f0c01bb;

            /* JADX INFO: Added by JADX */
            public static final int moments_no_more = 0x7f0c01bc;

            /* JADX INFO: Added by JADX */
            public static final int moments_post = 0x7f0c01bd;

            /* JADX INFO: Added by JADX */
            public static final int moments_post_empty = 0x7f0c01be;

            /* JADX INFO: Added by JADX */
            public static final int moments_post_success = 0x7f0c01bf;

            /* JADX INFO: Added by JADX */
            public static final int moments_publish = 0x7f0c01c0;

            /* JADX INFO: Added by JADX */
            public static final int moments_publish_hint = 0x7f0c01c1;

            /* JADX INFO: Added by JADX */
            public static final int moments_sample_friend = 0x7f0c01c2;

            /* JADX INFO: Added by JADX */
            public static final int moments_sample_self = 0x7f0c01c3;

            /* JADX INFO: Added by JADX */
            public static final int moments_unlike = 0x7f0c01c4;

            /* JADX INFO: Added by JADX */
            public static final int no_account_hint = 0x7f0c01c5;

            /* JADX INFO: Added by JADX */
            public static final int no_more_messages = 0x7f0c01c6;

            /* JADX INFO: Added by JADX */
            public static final int notification_channel_messages = 0x7f0c01cc;

            /* JADX INFO: Added by JADX */
            public static final int notification_channel_service = 0x7f0c01cd;

            /* JADX INFO: Added by JADX */
            public static final int old_view_action_cancel = 0x7f0c01ce;

            /* JADX INFO: Added by JADX */
            public static final int old_view_action_clear_search = 0x7f0c01cf;

            /* JADX INFO: Added by JADX */
            public static final int old_view_action_favorites = 0x7f0c01d0;

            /* JADX INFO: Added by JADX */
            public static final int old_view_action_history = 0x7f0c01d1;

            /* JADX INFO: Added by JADX */
            public static final int old_view_action_login = 0x7f0c01d2;

            /* JADX INFO: Added by JADX */
            public static final int old_view_action_logout = 0x7f0c01d3;

            /* JADX INFO: Added by JADX */
            public static final int old_view_action_refresh_qr = 0x7f0c01d4;

            /* JADX INFO: Added by JADX */
            public static final int old_view_action_search = 0x7f0c01d6;

            /* JADX INFO: Added by JADX */
            public static final int old_view_action_up_profile = 0x7f0c01d7;

            /* JADX INFO: Added by JADX */
            public static final int old_view_comment_hint = 0x7f0c01d8;

            /* JADX INFO: Added by JADX */
            public static final int old_view_comment_send = 0x7f0c01d9;

            /* JADX INFO: Added by JADX */
            public static final int old_view_comments = 0x7f0c01da;

            /* JADX INFO: Added by JADX */
            public static final int old_view_comments_empty = 0x7f0c01db;

            /* JADX INFO: Added by JADX */
            public static final int old_view_detail_title = 0x7f0c01dc;

            /* JADX INFO: Added by JADX */
            public static final int old_view_empty = 0x7f0c01dd;

            /* JADX INFO: Added by JADX */
            public static final int old_view_empty_guest = 0x7f0c01de;

            /* JADX INFO: Added by JADX */
            public static final int old_view_favorite_detail_title = 0x7f0c01df;

            /* JADX INFO: Added by JADX */
            public static final int old_view_favorite_empty = 0x7f0c01e0;

            /* JADX INFO: Added by JADX */
            public static final int old_view_favorites_empty = 0x7f0c01e1;

            /* JADX INFO: Added by JADX */
            public static final int old_view_favorites_title = 0x7f0c01e2;

            /* JADX INFO: Added by JADX */
            public static final int old_view_history_empty = 0x7f0c01e3;

            /* JADX INFO: Added by JADX */
            public static final int old_view_history_title = 0x7f0c01e4;

            /* JADX INFO: Added by JADX */
            public static final int old_view_loading = 0x7f0c01e5;

            /* JADX INFO: Added by JADX */
            public static final int old_view_login_guest_title = 0x7f0c01e6;

            /* JADX INFO: Added by JADX */
            public static final int old_view_login_required = 0x7f0c01e7;

            /* JADX INFO: Added by JADX */
            public static final int old_view_login_title = 0x7f0c01e8;

            /* JADX INFO: Added by JADX */
            public static final int old_view_owner_tag = 0x7f0c01e9;

            /* JADX INFO: Added by JADX */
            public static final int old_view_quick_hint = 0x7f0c01ea;

            /* JADX INFO: Added by JADX */
            public static final int old_view_quick_title = 0x7f0c01eb;

            /* JADX INFO: Added by JADX */
            public static final int old_view_related_videos = 0x7f0c01ec;

            /* JADX INFO: Added by JADX */
            public static final int old_view_search_empty = 0x7f0c01ed;

            /* JADX INFO: Added by JADX */
            public static final int old_view_search_hint = 0x7f0c01ee;

            /* JADX INFO: Added by JADX */
            public static final int old_view_search_input_empty = 0x7f0c01ef;

            /* JADX INFO: Added by JADX */
            public static final int old_view_search_title = 0x7f0c01f0;

            /* JADX INFO: Added by JADX */
            public static final int old_view_status_expired = 0x7f0c01f1;

            /* JADX INFO: Added by JADX */
            public static final int old_view_status_fetch_qr = 0x7f0c01f2;

            /* JADX INFO: Added by JADX */
            public static final int old_view_status_guest = 0x7f0c01f3;

            /* JADX INFO: Added by JADX */
            public static final int old_view_status_login_ok = 0x7f0c01f4;

            /* JADX INFO: Added by JADX */
            public static final int old_view_status_scanned = 0x7f0c01f5;

            /* JADX INFO: Added by JADX */
            public static final int old_view_status_wait_scan = 0x7f0c01f6;

            /* JADX INFO: Added by JADX */
            public static final int old_view_summary = 0x7f0c01f7;

            /* JADX INFO: Added by JADX */
            public static final int old_view_title = 0x7f0c01f8;

            /* JADX INFO: Added by JADX */
            public static final int old_view_up_follow = 0x7f0c01f9;

            /* JADX INFO: Added by JADX */
            public static final int old_view_up_follow_toggle_failed = 0x7f0c01fa;

            /* JADX INFO: Added by JADX */
            public static final int old_view_up_followed = 0x7f0c01fb;

            /* JADX INFO: Added by JADX */
            public static final int old_view_up_following = 0x7f0c01fc;

            /* JADX INFO: Added by JADX */
            public static final int old_view_up_profile_load_failed = 0x7f0c01fd;

            /* JADX INFO: Added by JADX */
            public static final int old_view_up_profile_title = 0x7f0c01fe;

            /* JADX INFO: Added by JADX */
            public static final int old_view_up_profile_unavailable = 0x7f0c01ff;

            /* JADX INFO: Added by JADX */
            public static final int old_view_up_uid_prefix = 0x7f0c0200;

            /* JADX INFO: Added by JADX */
            public static final int password_hint = 0x7f0c0201;

            /* JADX INFO: Added by JADX */
            public static final int privacy_policy_agree = 0x7f0c0202;

            /* JADX INFO: Added by JADX */
            public static final int privacy_policy_agree_text = 0x7f0c0203;

            /* JADX INFO: Added by JADX */
            public static final int privacy_policy_required = 0x7f0c0204;

            /* JADX INFO: Added by JADX */
            public static final int privacy_policy_text = 0x7f0c0205;

            /* JADX INFO: Added by JADX */
            public static final int privacy_policy_title = 0x7f0c0206;

            /* JADX INFO: Added by JADX */
            public static final int profile_avatar = 0x7f0c0207;

            /* JADX INFO: Added by JADX */
            public static final int profile_edit_title = 0x7f0c0208;

            /* JADX INFO: Added by JADX */
            public static final int profile_loading = 0x7f0c0209;

            /* JADX INFO: Added by JADX */
            public static final int profile_logout = 0x7f0c020a;

            /* JADX INFO: Added by JADX */
            public static final int profile_logout_confirm = 0x7f0c020b;

            /* JADX INFO: Added by JADX */
            public static final int profile_oldchat_id = 0x7f0c020c;

            /* JADX INFO: Added by JADX */
            public static final int profile_oldchat_id_display = 0x7f0c020d;

            /* JADX INFO: Added by JADX */
            public static final int profile_oldchat_id_tip = 0x7f0c020e;

            /* JADX INFO: Added by JADX */
            public static final int profile_reputation_default = 0x7f0c020f;

            /* JADX INFO: Added by JADX */
            public static final int profile_reputation_format = 0x7f0c0210;

            /* JADX INFO: Added by JADX */
            public static final int profile_reputation_label = 0x7f0c0211;

            /* JADX INFO: Added by JADX */
            public static final int profile_settings = 0x7f0c0212;

            /* JADX INFO: Added by JADX */
            public static final int profile_signature = 0x7f0c0213;

            /* JADX INFO: Added by JADX */
            public static final int profile_signature_display = 0x7f0c0214;

            /* JADX INFO: Added by JADX */
            public static final int profile_title = 0x7f0c0215;

            /* JADX INFO: Added by JADX */
            public static final int profile_update = 0x7f0c0216;

            /* JADX INFO: Added by JADX */
            public static final int profile_username = 0x7f0c0217;

            /* JADX INFO: Added by JADX */
            public static final int profile_username_display = 0x7f0c0218;

            /* JADX INFO: Added by JADX */
            public static final int profile_wallet = 0x7f0c0219;

            /* JADX INFO: Added by JADX */
            public static final int profile_wallet_amount_default = 0x7f0c021a;

            /* JADX INFO: Added by JADX */
            public static final int profile_wallet_amount_format = 0x7f0c021b;

            /* JADX INFO: Added by JADX */
            public static final int recover_account_mismatch = 0x7f0c021c;

            /* JADX INFO: Added by JADX */
            public static final int recover_code_sent = 0x7f0c021d;

            /* JADX INFO: Added by JADX */
            public static final int recover_password = 0x7f0c021e;

            /* JADX INFO: Added by JADX */
            public static final int recover_reset_done = 0x7f0c021f;

            /* JADX INFO: Added by JADX */
            public static final int recover_reset_password = 0x7f0c0220;

            /* JADX INFO: Added by JADX */
            public static final int recover_send_code = 0x7f0c0221;

            /* JADX INFO: Added by JADX */
            public static final int recover_title = 0x7f0c0222;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_already_claimed = 0x7f0c0223;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_amount_hint = 0x7f0c0224;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_amount_invalid = 0x7f0c0225;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_amount_too_small = 0x7f0c0226;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_claim_amount_format = 0x7f0c0227;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_claimed_amount_format = 0x7f0c0228;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_claimed_count_format = 0x7f0c0229;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_count_hint = 0x7f0c022a;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_count_invalid = 0x7f0c022b;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_detail_empty = 0x7f0c022c;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_detail_title = 0x7f0c022d;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_empty = 0x7f0c022e;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_image_clear = 0x7f0c022f;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_image_hint = 0x7f0c0230;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_image_pick = 0x7f0c0231;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_insufficient = 0x7f0c0232;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_invalid = 0x7f0c0233;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_name_hint = 0x7f0c0234;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_no_permission = 0x7f0c0235;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_open = 0x7f0c0236;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_open_failed = 0x7f0c0237;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_open_tip = 0x7f0c0238;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_opening = 0x7f0c0239;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_received_format = 0x7f0c023a;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_resend_not_allowed = 0x7f0c023b;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_send = 0x7f0c023c;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_status_done = 0x7f0c023d;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_status_open = 0x7f0c023e;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_target_format = 0x7f0c023f;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_title = 0x7f0c0240;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_title_default = 0x7f0c0241;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_title_too_long = 0x7f0c0242;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_total_amount_format = 0x7f0c0243;

            /* JADX INFO: Added by JADX */
            public static final int red_packet_total_count_format = 0x7f0c0244;

            /* JADX INFO: Added by JADX */
            public static final int register_button = 0x7f0c0245;

            /* JADX INFO: Added by JADX */
            public static final int register_title = 0x7f0c0247;

            /* JADX INFO: Added by JADX */
            public static final int remark_hint = 0x7f0c0248;

            /* JADX INFO: Added by JADX */
            public static final int remark_set_title = 0x7f0c0249;

            /* JADX INFO: Added by JADX */
            public static final int report_user_menu = 0x7f0c024a;

            /* JADX INFO: Added by JADX */
            public static final int search_menu_title = 0x7f0c0253;

            /* JADX INFO: Added by JADX */
            public static final int self_memo_empty = 0x7f0c0254;

            /* JADX INFO: Added by JADX */
            public static final int self_memo_title = 0x7f0c0255;

            /* JADX INFO: Added by JADX */
            public static final int send_email_code = 0x7f0c0256;

            /* JADX INFO: Added by JADX */
            public static final int settings_account_change_password = 0x7f0c025b;

            /* JADX INFO: Added by JADX */
            public static final int settings_account_change_phone = 0x7f0c025c;

            /* JADX INFO: Added by JADX */
            public static final int settings_account_changing = 0x7f0c025d;

            /* JADX INFO: Added by JADX */
            public static final int settings_account_current_format = 0x7f0c025e;

            /* JADX INFO: Added by JADX */
            public static final int settings_account_new_password = 0x7f0c025f;

            /* JADX INFO: Added by JADX */
            public static final int settings_account_new_password_confirm = 0x7f0c0260;

            /* JADX INFO: Added by JADX */
            public static final int settings_account_new_phone = 0x7f0c0261;

            /* JADX INFO: Added by JADX */
            public static final int settings_account_old_password = 0x7f0c0262;

            /* JADX INFO: Added by JADX */
            public static final int settings_account_password_changed = 0x7f0c0263;

            /* JADX INFO: Added by JADX */
            public static final int settings_account_password_title = 0x7f0c0264;

            /* JADX INFO: Added by JADX */
            public static final int settings_account_phone_changed = 0x7f0c0265;

            /* JADX INFO: Added by JADX */
            public static final int settings_account_phone_password = 0x7f0c0266;

            /* JADX INFO: Added by JADX */
            public static final int settings_account_phone_same = 0x7f0c0267;

            /* JADX INFO: Added by JADX */
            public static final int settings_account_phone_title = 0x7f0c0268;

            /* JADX INFO: Added by JADX */
            public static final int settings_account_title = 0x7f0c0269;

            /* JADX INFO: Added by JADX */
            public static final int settings_bug_report = 0x7f0c026a;

            /* JADX INFO: Added by JADX */
            public static final int settings_cache_info = 0x7f0c026b;

            /* JADX INFO: Added by JADX */
            public static final int settings_cache_info_default = 0x7f0c026c;

            /* JADX INFO: Added by JADX */
            public static final int settings_check_update = 0x7f0c026d;

            /* JADX INFO: Added by JADX */
            public static final int settings_clear_cache = 0x7f0c026e;

            /* JADX INFO: Added by JADX */
            public static final int settings_clear_confirm = 0x7f0c026f;

            /* JADX INFO: Added by JADX */
            public static final int settings_clear_done = 0x7f0c0270;

            /* JADX INFO: Added by JADX */
            public static final int settings_dark_mode = 0x7f0c0271;

            /* JADX INFO: Added by JADX */
            public static final int settings_dark_mode_restart_message = 0x7f0c0272;

            /* JADX INFO: Added by JADX */
            public static final int settings_dark_mode_restart_title = 0x7f0c0273;

            /* JADX INFO: Added by JADX */
            public static final int settings_download_delete = 0x7f0c0274;

            /* JADX INFO: Added by JADX */
            public static final int settings_download_delete_confirm = 0x7f0c0275;

            /* JADX INFO: Added by JADX */
            public static final int settings_download_delete_done = 0x7f0c0276;

            /* JADX INFO: Added by JADX */
            public static final int settings_download_delete_failed = 0x7f0c0277;

            /* JADX INFO: Added by JADX */
            public static final int settings_download_empty = 0x7f0c0278;

            /* JADX INFO: Added by JADX */
            public static final int settings_download_entry = 0x7f0c0279;

            /* JADX INFO: Added by JADX */
            public static final int settings_download_open = 0x7f0c027a;

            /* JADX INFO: Added by JADX */
            public static final int settings_download_open_failed = 0x7f0c027b;

            /* JADX INFO: Added by JADX */
            public static final int settings_download_title = 0x7f0c027c;

            /* JADX INFO: Added by JADX */
            public static final int settings_export_chat = 0x7f0c027d;

            /* JADX INFO: Added by JADX */
            public static final int settings_export_chat_empty = 0x7f0c027e;

            /* JADX INFO: Added by JADX */
            public static final int settings_export_chat_failed = 0x7f0c027f;

            /* JADX INFO: Added by JADX */
            public static final int settings_export_logs = 0x7f0c0280;

            /* JADX INFO: Added by JADX */
            public static final int settings_export_logs_empty = 0x7f0c0281;

            /* JADX INFO: Added by JADX */
            public static final int settings_export_logs_failed = 0x7f0c0282;

            /* JADX INFO: Added by JADX */
            public static final int settings_feedback_title = 0x7f0c0283;

            /* JADX INFO: Added by JADX */
            public static final int settings_hide_phone = 0x7f0c0284;

            /* JADX INFO: Added by JADX */
            public static final int settings_import_chat = 0x7f0c0285;

            /* JADX INFO: Added by JADX */
            public static final int settings_import_chat_confirm = 0x7f0c0286;

            /* JADX INFO: Added by JADX */
            public static final int settings_import_chat_failed = 0x7f0c0287;

            /* JADX INFO: Added by JADX */
            public static final int settings_import_chat_success = 0x7f0c0288;

            /* JADX INFO: Added by JADX */
            public static final int settings_notify_denied = 0x7f0c0289;

            /* JADX INFO: Added by JADX */
            public static final int settings_notify_messages = 0x7f0c028a;

            /* JADX INFO: Added by JADX */
            public static final int settings_notify_sound = 0x7f0c028b;

            /* JADX INFO: Added by JADX */
            public static final int settings_notify_title = 0x7f0c028c;

            /* JADX INFO: Added by JADX */
            public static final int settings_privacy_title = 0x7f0c028d;

            /* JADX INFO: Added by JADX */
            public static final int settings_storage_title = 0x7f0c028e;

            /* JADX INFO: Added by JADX */
            public static final int settings_theme_color = 0x7f0c028f;

            /* JADX INFO: Added by JADX */
            public static final int settings_theme_color_tip_message = 0x7f0c0290;

            /* JADX INFO: Added by JADX */
            public static final int settings_theme_color_tip_title = 0x7f0c0291;

            /* JADX INFO: Added by JADX */
            public static final int settings_theme_color_title = 0x7f0c0292;

            /* JADX INFO: Added by JADX */
            public static final int settings_title = 0x7f0c0293;

            /* JADX INFO: Added by JADX */
            public static final int settings_tools_title = 0x7f0c0294;

            /* JADX INFO: Added by JADX */
            public static final int settings_version_format = 0x7f0c0295;

            /* JADX INFO: Added by JADX */
            public static final int signature_invalid_message = 0x7f0c0298;

            /* JADX INFO: Added by JADX */
            public static final int signature_invalid_title = 0x7f0c0299;

            /* JADX INFO: Added by JADX */
            public static final int signature_sha256_whitelist = 0x7f0c029a;

            /* JADX INFO: Added by JADX */
            public static final int status_bar_notification_info_overflow = 0x7f0c029b;

            /* JADX INFO: Added by JADX */
            public static final int theme_color_blue = 0x7f0c029c;

            /* JADX INFO: Added by JADX */
            public static final int theme_color_brown = 0x7f0c029d;

            /* JADX INFO: Added by JADX */
            public static final int theme_color_green = 0x7f0c029e;

            /* JADX INFO: Added by JADX */
            public static final int theme_color_orange = 0x7f0c029f;

            /* JADX INFO: Added by JADX */
            public static final int theme_color_red = 0x7f0c02a0;

            /* JADX INFO: Added by JADX */
            public static final int theme_color_teal = 0x7f0c02a1;

            /* JADX INFO: Added by JADX */
            public static final int unread_count_more = 0x7f0c02a2;

            /* JADX INFO: Added by JADX */
            public static final int update_download_failed = 0x7f0c02a3;

            /* JADX INFO: Added by JADX */
            public static final int update_downloading = 0x7f0c02a4;

            /* JADX INFO: Added by JADX */
            public static final int update_exit = 0x7f0c02a5;

            /* JADX INFO: Added by JADX */
            public static final int update_later = 0x7f0c02a6;

            /* JADX INFO: Added by JADX */
            public static final int update_message_default = 0x7f0c02a7;

            /* JADX INFO: Added by JADX */
            public static final int update_need_permission = 0x7f0c02a8;

            /* JADX INFO: Added by JADX */
            public static final int update_now = 0x7f0c02a9;

            /* JADX INFO: Added by JADX */
            public static final int update_skip = 0x7f0c02aa;

            /* JADX INFO: Added by JADX */
            public static final int update_title = 0x7f0c02ab;

            /* JADX INFO: Added by JADX */
            public static final int update_title_force = 0x7f0c02ac;

            /* JADX INFO: Added by JADX */
            public static final int update_version_format = 0x7f0c02ad;

            /* JADX INFO: Added by JADX */
            public static final int user_detail_action_add = 0x7f0c02ae;

            /* JADX INFO: Added by JADX */
            public static final int user_detail_action_message = 0x7f0c02af;

            /* JADX INFO: Added by JADX */
            public static final int user_detail_action_pending = 0x7f0c02b0;

            /* JADX INFO: Added by JADX */
            public static final int user_detail_title = 0x7f0c02b1;

            /* JADX INFO: Added by JADX */
            public static final int username_hint = 0x7f0c02b2;

            /* JADX INFO: Added by JADX */
            public static final int wallet_balance_label = 0x7f0c02b3;

            /* JADX INFO: Added by JADX */
            public static final int wallet_title = 0x7f0c02b4;
        }

        /* JADX INFO: Added by JADX */
        public static final class style {

            /* JADX INFO: Added by JADX */
            public static final int AlertDialog_AppCompat = 0x7f0d0000;

            /* JADX INFO: Added by JADX */
            public static final int AlertDialog_AppCompat_Light = 0x7f0d0001;

            /* JADX INFO: Added by JADX */
            public static final int Animation_AppCompat_Dialog = 0x7f0d0002;

            /* JADX INFO: Added by JADX */
            public static final int Animation_AppCompat_DropDownUp = 0x7f0d0003;

            /* JADX INFO: Added by JADX */
            public static final int AppAlertDialog = 0x7f0d0004;

            /* JADX INFO: Added by JADX */
            public static final int AppDialogTheme = 0x7f0d0005;

            /* JADX INFO: Added by JADX */
            public static final int AppPopupMenu = 0x7f0d0006;

            /* JADX INFO: Added by JADX */
            public static final int AppTheme = 0x7f0d0007;

            /* JADX INFO: Added by JADX */
            public static final int AppTheme_Blue = 0x7f0d0008;

            /* JADX INFO: Added by JADX */
            public static final int AppTheme_Blue_Round = 0x7f0d0009;

            /* JADX INFO: Added by JADX */
            public static final int AppTheme_Brown = 0x7f0d000a;

            /* JADX INFO: Added by JADX */
            public static final int AppTheme_Brown_Round = 0x7f0d000b;

            /* JADX INFO: Added by JADX */
            public static final int AppTheme_Fullscreen = 0x7f0d000c;

            /* JADX INFO: Added by JADX */
            public static final int AppTheme_Green = 0x7f0d000d;

            /* JADX INFO: Added by JADX */
            public static final int AppTheme_Green_Round = 0x7f0d000e;

            /* JADX INFO: Added by JADX */
            public static final int AppTheme_Orange = 0x7f0d000f;

            /* JADX INFO: Added by JADX */
            public static final int AppTheme_Orange_Round = 0x7f0d0010;

            /* JADX INFO: Added by JADX */
            public static final int AppTheme_Red = 0x7f0d0011;

            /* JADX INFO: Added by JADX */
            public static final int AppTheme_Red_Round = 0x7f0d0012;

            /* JADX INFO: Added by JADX */
            public static final int AppTheme_Round = 0x7f0d0013;

            /* JADX INFO: Added by JADX */
            public static final int AppTheme_Teal = 0x7f0d0014;

            /* JADX INFO: Added by JADX */
            public static final int AppTheme_Teal_Round = 0x7f0d0015;

            /* JADX INFO: Added by JADX */
            public static final int Base_AlertDialog_AppCompat = 0x7f0d0016;

            /* JADX INFO: Added by JADX */
            public static final int Base_AlertDialog_AppCompat_Light = 0x7f0d0017;

            /* JADX INFO: Added by JADX */
            public static final int Base_Animation_AppCompat_Dialog = 0x7f0d0018;

            /* JADX INFO: Added by JADX */
            public static final int Base_Animation_AppCompat_DropDownUp = 0x7f0d0019;

            /* JADX INFO: Added by JADX */
            public static final int Base_DialogWindowTitle_AppCompat = 0x7f0d001a;

            /* JADX INFO: Added by JADX */
            public static final int Base_DialogWindowTitleBackground_AppCompat = 0x7f0d001b;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat = 0x7f0d001c;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_Button = 0x7f0d001f;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_Inverse = 0x7f0d0026;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_Large = 0x7f0d0027;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_Large_Inverse = 0x7f0d0028;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_Medium = 0x7f0d002b;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_Medium_Inverse = 0x7f0d002c;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_Menu = 0x7f0d002d;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_SearchResult = 0x7f0d002e;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_SearchResult_Subtitle = 0x7f0d002f;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_SearchResult_Title = 0x7f0d0030;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_Small = 0x7f0d0031;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_Small_Inverse = 0x7f0d0032;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_Subhead = 0x7f0d0033;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_Title = 0x7f0d0035;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_Widget_ActionBar_Menu = 0x7f0d0037;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_Widget_ActionBar_Subtitle = 0x7f0d0038;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_Widget_ActionBar_Title = 0x7f0d003a;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_Widget_ActionMode_Subtitle = 0x7f0d003c;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_Widget_ActionMode_Title = 0x7f0d003d;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_Widget_Button = 0x7f0d003e;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_Widget_DropDownItem = 0x7f0d0040;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_Widget_PopupMenu_Header = 0x7f0d0041;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_Widget_PopupMenu_Large = 0x7f0d0042;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_Widget_PopupMenu_Small = 0x7f0d0043;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_Widget_Switch = 0x7f0d0044;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_AppCompat_Widget_TextView_SpinnerItem = 0x7f0d0045;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_Widget_AppCompat_Toolbar_Subtitle = 0x7f0d0047;

            /* JADX INFO: Added by JADX */
            public static final int Base_TextAppearance_Widget_AppCompat_Toolbar_Title = 0x7f0d0048;

            /* JADX INFO: Added by JADX */
            public static final int Base_Theme_AppCompat = 0x7f0d0049;

            /* JADX INFO: Added by JADX */
            public static final int Base_Theme_AppCompat_CompactMenu = 0x7f0d004a;

            /* JADX INFO: Added by JADX */
            public static final int Base_Theme_AppCompat_Dialog = 0x7f0d004b;

            /* JADX INFO: Added by JADX */
            public static final int Base_Theme_AppCompat_Dialog_Alert = 0x7f0d004c;

            /* JADX INFO: Added by JADX */
            public static final int Base_Theme_AppCompat_Light = 0x7f0d0050;

            /* JADX INFO: Added by JADX */
            public static final int Base_Theme_AppCompat_Light_Dialog = 0x7f0d0052;

            /* JADX INFO: Added by JADX */
            public static final int Base_Theme_AppCompat_Light_Dialog_Alert = 0x7f0d0053;

            /* JADX INFO: Added by JADX */
            public static final int Base_ThemeOverlay_AppCompat = 0x7f0d0057;

            /* JADX INFO: Added by JADX */
            public static final int Base_ThemeOverlay_AppCompat_ActionBar = 0x7f0d0058;

            /* JADX INFO: Added by JADX */
            public static final int Base_ThemeOverlay_AppCompat_Dialog = 0x7f0d005b;

            /* JADX INFO: Added by JADX */
            public static final int Base_ThemeOverlay_AppCompat_Dialog_Alert = 0x7f0d005c;

            /* JADX INFO: Added by JADX */
            public static final int Base_V11_Theme_AppCompat_Dialog = 0x7f0d005e;

            /* JADX INFO: Added by JADX */
            public static final int Base_V11_Theme_AppCompat_Light_Dialog = 0x7f0d005f;

            /* JADX INFO: Added by JADX */
            public static final int Base_V11_ThemeOverlay_AppCompat_Dialog = 0x7f0d0060;

            /* JADX INFO: Added by JADX */
            public static final int Base_V12_Widget_AppCompat_AutoCompleteTextView = 0x7f0d0061;

            /* JADX INFO: Added by JADX */
            public static final int Base_V12_Widget_AppCompat_EditText = 0x7f0d0062;

            /* JADX INFO: Added by JADX */
            public static final int Base_V21_Theme_AppCompat = 0x7f0d0063;

            /* JADX INFO: Added by JADX */
            public static final int Base_V21_Theme_AppCompat_Dialog = 0x7f0d0064;

            /* JADX INFO: Added by JADX */
            public static final int Base_V21_Theme_AppCompat_Light = 0x7f0d0065;

            /* JADX INFO: Added by JADX */
            public static final int Base_V21_Theme_AppCompat_Light_Dialog = 0x7f0d0066;

            /* JADX INFO: Added by JADX */
            public static final int Base_V21_ThemeOverlay_AppCompat_Dialog = 0x7f0d0067;

            /* JADX INFO: Added by JADX */
            public static final int Base_V22_Theme_AppCompat = 0x7f0d0068;

            /* JADX INFO: Added by JADX */
            public static final int Base_V22_Theme_AppCompat_Light = 0x7f0d0069;

            /* JADX INFO: Added by JADX */
            public static final int Base_V23_Theme_AppCompat = 0x7f0d006a;

            /* JADX INFO: Added by JADX */
            public static final int Base_V23_Theme_AppCompat_Light = 0x7f0d006b;

            /* JADX INFO: Added by JADX */
            public static final int Base_V7_Theme_AppCompat = 0x7f0d006c;

            /* JADX INFO: Added by JADX */
            public static final int Base_V7_Theme_AppCompat_Dialog = 0x7f0d006d;

            /* JADX INFO: Added by JADX */
            public static final int Base_V7_Theme_AppCompat_Light = 0x7f0d006e;

            /* JADX INFO: Added by JADX */
            public static final int Base_V7_Theme_AppCompat_Light_Dialog = 0x7f0d006f;

            /* JADX INFO: Added by JADX */
            public static final int Base_V7_ThemeOverlay_AppCompat_Dialog = 0x7f0d0070;

            /* JADX INFO: Added by JADX */
            public static final int Base_V7_Widget_AppCompat_AutoCompleteTextView = 0x7f0d0071;

            /* JADX INFO: Added by JADX */
            public static final int Base_V7_Widget_AppCompat_EditText = 0x7f0d0072;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_ActionBar = 0x7f0d0073;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_ActionBar_Solid = 0x7f0d0074;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_ActionBar_TabBar = 0x7f0d0075;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_ActionBar_TabText = 0x7f0d0076;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_ActionBar_TabView = 0x7f0d0077;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_ActionButton = 0x7f0d0078;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_ActionButton_CloseMode = 0x7f0d0079;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_ActionButton_Overflow = 0x7f0d007a;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_ActionMode = 0x7f0d007b;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_ActivityChooserView = 0x7f0d007c;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_AutoCompleteTextView = 0x7f0d007d;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_Button = 0x7f0d007e;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_Button_Borderless = 0x7f0d007f;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_Button_Borderless_Colored = 0x7f0d0080;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_Button_ButtonBar_AlertDialog = 0x7f0d0081;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_Button_Small = 0x7f0d0083;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_ButtonBar = 0x7f0d0084;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_ButtonBar_AlertDialog = 0x7f0d0085;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_CompoundButton_CheckBox = 0x7f0d0086;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_CompoundButton_RadioButton = 0x7f0d0087;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_CompoundButton_Switch = 0x7f0d0088;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_DrawerArrowToggle = 0x7f0d0089;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_DrawerArrowToggle_Common = 0x7f0d008a;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_DropDownItem_Spinner = 0x7f0d008b;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_EditText = 0x7f0d008c;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_ImageButton = 0x7f0d008d;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_Light_ActionBar = 0x7f0d008e;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_Light_ActionBar_Solid = 0x7f0d008f;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_Light_ActionBar_TabBar = 0x7f0d0090;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_Light_ActionBar_TabText = 0x7f0d0091;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_Light_ActionBar_TabView = 0x7f0d0093;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_Light_PopupMenu = 0x7f0d0094;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_Light_PopupMenu_Overflow = 0x7f0d0095;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_ListMenuView = 0x7f0d0096;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_ListPopupWindow = 0x7f0d0097;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_ListView = 0x7f0d0098;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_ListView_DropDown = 0x7f0d0099;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_ListView_Menu = 0x7f0d009a;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_PopupMenu = 0x7f0d009b;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_PopupMenu_Overflow = 0x7f0d009c;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_RatingBar = 0x7f0d00a0;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_RatingBar_Indicator = 0x7f0d00a1;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_RatingBar_Small = 0x7f0d00a2;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_SearchView = 0x7f0d00a3;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_SearchView_ActionBar = 0x7f0d00a4;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_SeekBar = 0x7f0d00a5;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_Spinner = 0x7f0d00a7;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_TextView_SpinnerItem = 0x7f0d00a9;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_Toolbar = 0x7f0d00aa;

            /* JADX INFO: Added by JADX */
            public static final int Base_Widget_AppCompat_Toolbar_Button_Navigation = 0x7f0d00ab;

            /* JADX INFO: Added by JADX */
            public static final int ChatSendButton = 0x7f0d00ac;

            /* JADX INFO: Added by JADX */
            public static final int CrashDialogTheme = 0x7f0d00ad;

            /* JADX INFO: Added by JADX */
            public static final int FlatButtonOutline = 0x7f0d00b0;

            /* JADX INFO: Added by JADX */
            public static final int FlatButtonPrimary = 0x7f0d00b1;

            /* JADX INFO: Added by JADX */
            public static final int FlatInput = 0x7f0d00b2;

            /* JADX INFO: Added by JADX */
            public static final int Platform_AppCompat = 0x7f0d00b3;

            /* JADX INFO: Added by JADX */
            public static final int Platform_AppCompat_Light = 0x7f0d00b4;

            /* JADX INFO: Added by JADX */
            public static final int Platform_ThemeOverlay_AppCompat = 0x7f0d00b5;

            /* JADX INFO: Added by JADX */
            public static final int Platform_V11_AppCompat = 0x7f0d00b8;

            /* JADX INFO: Added by JADX */
            public static final int Platform_V11_AppCompat_Light = 0x7f0d00b9;

            /* JADX INFO: Added by JADX */
            public static final int Platform_V14_AppCompat = 0x7f0d00ba;

            /* JADX INFO: Added by JADX */
            public static final int Platform_V14_AppCompat_Light = 0x7f0d00bb;

            /* JADX INFO: Added by JADX */
            public static final int Platform_Widget_AppCompat_Spinner = 0x7f0d00bc;

            /* JADX INFO: Added by JADX */
            public static final int PopupMenuOverlayDark = 0x7f0d00bd;

            /* JADX INFO: Added by JADX */
            public static final int PopupMenuOverlayLight = 0x7f0d00be;

            /* JADX INFO: Added by JADX */
            public static final int RoundedOutlineButton = 0x7f0d00bf;

            /* JADX INFO: Added by JADX */
            public static final int RtlOverlay_DialogWindowTitle_AppCompat = 0x7f0d00c0;

            /* JADX INFO: Added by JADX */
            public static final int RtlOverlay_Widget_AppCompat_ActionBar_TitleItem = 0x7f0d00c1;

            /* JADX INFO: Added by JADX */
            public static final int RtlOverlay_Widget_AppCompat_DialogTitle_Icon = 0x7f0d00c2;

            /* JADX INFO: Added by JADX */
            public static final int RtlOverlay_Widget_AppCompat_PopupMenuItem = 0x7f0d00c3;

            /* JADX INFO: Added by JADX */
            public static final int RtlOverlay_Widget_AppCompat_PopupMenuItem_InternalGroup = 0x7f0d00c4;

            /* JADX INFO: Added by JADX */
            public static final int RtlOverlay_Widget_AppCompat_PopupMenuItem_Text = 0x7f0d00c5;

            /* JADX INFO: Added by JADX */
            public static final int RtlOverlay_Widget_AppCompat_Search_DropDown = 0x7f0d00c6;

            /* JADX INFO: Added by JADX */
            public static final int RtlOverlay_Widget_AppCompat_Search_DropDown_Icon1 = 0x7f0d00c7;

            /* JADX INFO: Added by JADX */
            public static final int RtlOverlay_Widget_AppCompat_Search_DropDown_Icon2 = 0x7f0d00c8;

            /* JADX INFO: Added by JADX */
            public static final int RtlOverlay_Widget_AppCompat_Search_DropDown_Query = 0x7f0d00c9;

            /* JADX INFO: Added by JADX */
            public static final int RtlOverlay_Widget_AppCompat_Search_DropDown_Text = 0x7f0d00ca;

            /* JADX INFO: Added by JADX */
            public static final int RtlOverlay_Widget_AppCompat_SearchView_MagIcon = 0x7f0d00cb;

            /* JADX INFO: Added by JADX */
            public static final int RtlUnderlay_Widget_AppCompat_ActionButton = 0x7f0d00cc;

            /* JADX INFO: Added by JADX */
            public static final int RtlUnderlay_Widget_AppCompat_ActionButton_Overflow = 0x7f0d00cd;

            /* JADX INFO: Added by JADX */
            public static final int SquareWindowAnimation = 0x7f0d00ce;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat = 0x7f0d00cf;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Button = 0x7f0d00d2;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Inverse = 0x7f0d00d9;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Large = 0x7f0d00da;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Large_Inverse = 0x7f0d00db;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Light_Widget_PopupMenu_Large = 0x7f0d00de;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Light_Widget_PopupMenu_Small = 0x7f0d00df;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Medium = 0x7f0d00e0;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Medium_Inverse = 0x7f0d00e1;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Menu = 0x7f0d00e2;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_SearchResult_Subtitle = 0x7f0d00e3;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_SearchResult_Title = 0x7f0d00e4;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Small = 0x7f0d00e5;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Small_Inverse = 0x7f0d00e6;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Subhead = 0x7f0d00e7;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Title = 0x7f0d00e9;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Widget_ActionBar_Menu = 0x7f0d00eb;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Widget_ActionBar_Subtitle = 0x7f0d00ec;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Widget_ActionBar_Title = 0x7f0d00ee;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Widget_ActionMode_Subtitle = 0x7f0d00f0;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Widget_ActionMode_Title = 0x7f0d00f2;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Widget_Button = 0x7f0d00f4;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Widget_DropDownItem = 0x7f0d00f6;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Widget_PopupMenu_Header = 0x7f0d00f7;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Widget_PopupMenu_Large = 0x7f0d00f8;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Widget_PopupMenu_Small = 0x7f0d00f9;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Widget_Switch = 0x7f0d00fa;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_AppCompat_Widget_TextView_SpinnerItem = 0x7f0d00fb;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_StatusBar_EventContent = 0x7f0d00fc;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_StatusBar_EventContent_Info = 0x7f0d00fd;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_StatusBar_EventContent_Line2 = 0x7f0d00fe;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_StatusBar_EventContent_Time = 0x7f0d00ff;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_StatusBar_EventContent_Title = 0x7f0d0100;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_Widget_AppCompat_Toolbar_Subtitle = 0x7f0d0102;

            /* JADX INFO: Added by JADX */
            public static final int TextAppearance_Widget_AppCompat_Toolbar_Title = 0x7f0d0103;

            /* JADX INFO: Added by JADX */
            public static final int TextLinkButton = 0x7f0d0104;

            /* JADX INFO: Added by JADX */
            public static final int Theme_AppCompat = 0x7f0d0105;

            /* JADX INFO: Added by JADX */
            public static final int Theme_AppCompat_CompactMenu = 0x7f0d0106;

            /* JADX INFO: Added by JADX */
            public static final int Theme_AppCompat_Dialog = 0x7f0d010e;

            /* JADX INFO: Added by JADX */
            public static final int Theme_AppCompat_Dialog_Alert = 0x7f0d010f;

            /* JADX INFO: Added by JADX */
            public static final int Theme_AppCompat_Light = 0x7f0d0112;

            /* JADX INFO: Added by JADX */
            public static final int Theme_AppCompat_Light_Dialog = 0x7f0d0114;

            /* JADX INFO: Added by JADX */
            public static final int Theme_AppCompat_Light_Dialog_Alert = 0x7f0d0115;

            /* JADX INFO: Added by JADX */
            public static final int Theme_AppCompat_Light_NoActionBar = 0x7f0d0118;

            /* JADX INFO: Added by JADX */
            public static final int Theme_AppCompat_NoActionBar = 0x7f0d0119;

            /* JADX INFO: Added by JADX */
            public static final int ThemeOverlay_AppCompat_ActionBar = 0x7f0d011b;

            /* JADX INFO: Added by JADX */
            public static final int ThemeOverlay_AppCompat_Dialog = 0x7f0d011e;

            /* JADX INFO: Added by JADX */
            public static final int ThemeOverlay_AppCompat_Dialog_Alert = 0x7f0d011f;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_ActionBar_Solid = 0x7f0d0122;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_ActionBar_TabBar = 0x7f0d0123;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_ActionBar_TabText = 0x7f0d0124;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_ActionBar_TabView = 0x7f0d0125;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_ActionButton = 0x7f0d0126;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_ActionButton_CloseMode = 0x7f0d0127;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_ActionButton_Overflow = 0x7f0d0128;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_ActionMode = 0x7f0d0129;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_ActivityChooserView = 0x7f0d012a;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_AutoCompleteTextView = 0x7f0d012b;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_Button = 0x7f0d012c;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_Button_Borderless = 0x7f0d012d;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_Button_Borderless_Colored = 0x7f0d012e;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_Button_ButtonBar_AlertDialog = 0x7f0d012f;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_Button_Small = 0x7f0d0131;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_ButtonBar = 0x7f0d0132;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_ButtonBar_AlertDialog = 0x7f0d0133;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_CompoundButton_CheckBox = 0x7f0d0134;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_CompoundButton_RadioButton = 0x7f0d0135;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_CompoundButton_Switch = 0x7f0d0136;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_DrawerArrowToggle = 0x7f0d0137;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_DropDownItem_Spinner = 0x7f0d0138;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_EditText = 0x7f0d0139;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_ImageButton = 0x7f0d013a;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_Light_ActionBar_Solid = 0x7f0d013c;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_Light_ActionBar_TabBar = 0x7f0d013e;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_Light_ActionBar_TabText = 0x7f0d0140;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_Light_ActionBar_TabView = 0x7f0d0142;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_Light_ActionButton = 0x7f0d0144;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_Light_ActionButton_Overflow = 0x7f0d0146;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_Light_PopupMenu = 0x7f0d014d;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_Light_PopupMenu_Overflow = 0x7f0d014e;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_Light_SearchView = 0x7f0d014f;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_Light_Spinner_DropDown_ActionBar = 0x7f0d0150;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_ListMenuView = 0x7f0d0151;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_ListPopupWindow = 0x7f0d0152;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_ListView = 0x7f0d0153;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_ListView_DropDown = 0x7f0d0154;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_ListView_Menu = 0x7f0d0155;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_PopupMenu = 0x7f0d0156;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_PopupMenu_Overflow = 0x7f0d0157;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_RatingBar = 0x7f0d015b;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_RatingBar_Indicator = 0x7f0d015c;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_RatingBar_Small = 0x7f0d015d;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_SearchView = 0x7f0d015e;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_SearchView_ActionBar = 0x7f0d015f;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_SeekBar = 0x7f0d0160;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_Spinner = 0x7f0d0162;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_Spinner_DropDown = 0x7f0d0163;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_Spinner_DropDown_ActionBar = 0x7f0d0164;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_TextView_SpinnerItem = 0x7f0d0166;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_Toolbar = 0x7f0d0167;

            /* JADX INFO: Added by JADX */
            public static final int Widget_AppCompat_Toolbar_Button_Navigation = 0x7f0d0168;
        }

        /* JADX INFO: Added by JADX */
        public static final class xml {

            /* JADX INFO: Added by JADX */
            public static final int file_paths = 0x7f0f0000;
        }

        public R() {
        }

        public /* synthetic */ R(C0200k c0200k) {
            this();
        }
    }

    public static class S {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ImageView f3405a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f3406b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final TextView f3407c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final TextView f3408d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final TextView f3409e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final TextView f3410f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final TextView f3411g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final TextView f3412h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final TextView f3413i;

        public S(View view) {
            this.f3405a = (ImageView) view.findViewById(f0.f.N5);
            this.f3406b = (TextView) view.findViewById(f0.f.jb);
            this.f3407c = (TextView) view.findViewById(f0.f.eb);
            this.f3408d = (TextView) view.findViewById(f0.f.kb);
            this.f3409e = (TextView) view.findViewById(f0.f.ib);
            this.f3410f = (TextView) view.findViewById(f0.f.ob);
            this.f3411g = (TextView) view.findViewById(f0.f.qb);
            this.f3412h = (TextView) view.findViewById(f0.f.b2);
            this.f3413i = (TextView) view.findViewById(f0.f.X1);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.MusicPlazaActivity$a, reason: case insensitive filesystem */
    public class ViewOnClickListenerC0190a implements View.OnClickListener {
        public ViewOnClickListenerC0190a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent(MusicPlazaActivity.this, (Class<?>) MusicManageActivity.class);
            intent.putExtra("all_list_mode", true);
            MusicPlazaActivity.this.startActivity(intent);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.MusicPlazaActivity$b, reason: case insensitive filesystem */
    public class ViewOnClickListenerC0191b implements View.OnClickListener {
        public ViewOnClickListenerC0191b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicPlazaActivity.this.C2(false, true);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.MusicPlazaActivity$c, reason: case insensitive filesystem */
    public class ViewOnClickListenerC0192c implements View.OnClickListener {
        public ViewOnClickListenerC0192c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicPlazaActivity.this.C2(true, true);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.MusicPlazaActivity$d, reason: case insensitive filesystem */
    public class ViewOnClickListenerC0193d implements View.OnClickListener {
        public ViewOnClickListenerC0193d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicPlazaActivity.this.startActivity(new Intent(MusicPlazaActivity.this, (Class<?>) MusicManageActivity.class));
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.MusicPlazaActivity$e, reason: case insensitive filesystem */
    public class C0194e implements TextView.OnEditorActionListener {
        public C0194e() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
            if (i2 == 3) {
                MusicPlazaActivity.this.f3342c0 = 0;
                MusicPlazaActivity.this.V1();
                return true;
            }
            if (keyEvent == null || keyEvent.getAction() != 0 || keyEvent.getKeyCode() != 66) {
                return false;
            }
            MusicPlazaActivity.this.f3342c0 = 0;
            MusicPlazaActivity.this.V1();
            return true;
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.MusicPlazaActivity$f, reason: case insensitive filesystem */
    public class ViewOnClickListenerC0195f implements View.OnClickListener {
        public ViewOnClickListenerC0195f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MusicPlazaActivity.this.f3342c0 <= 0) {
                return;
            }
            MusicPlazaActivity.this.f3342c0 = Math.max(0, r3.f3342c0 - 50);
            MusicPlazaActivity.this.V1();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.MusicPlazaActivity$g, reason: case insensitive filesystem */
    public class ViewOnClickListenerC0196g implements View.OnClickListener {
        public ViewOnClickListenerC0196g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MusicPlazaActivity.this.f3344e0) {
                MusicPlazaActivity.q1(MusicPlazaActivity.this, 50);
                MusicPlazaActivity.this.V1();
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.MusicPlazaActivity$h, reason: case insensitive filesystem */
    public class ViewOnClickListenerC0197h implements View.OnClickListener {
        public ViewOnClickListenerC0197h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MusicPlazaActivity.this.f3338Y.isEmpty()) {
                return;
            }
            MusicPlazaActivity musicPlazaActivity = MusicPlazaActivity.this;
            musicPlazaActivity.i2((R) musicPlazaActivity.f3338Y.get(0));
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.MusicPlazaActivity$i, reason: case insensitive filesystem */
    public class DialogInterfaceOnClickListenerC0198i implements DialogInterface.OnClickListener {
        public DialogInterfaceOnClickListenerC0198i() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            if (i2 == 0) {
                MusicPlazaActivity.this.I2();
                return;
            }
            if (i2 == 1) {
                MusicPlazaActivity.this.startActivity(new Intent(MusicPlazaActivity.this, (Class<?>) MusicManageActivity.class));
            } else if (i2 == 2) {
                MusicPlazaActivity.this.J2();
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.MusicPlazaActivity$j, reason: case insensitive filesystem */
    public class DialogInterfaceOnClickListenerC0199j implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ List f3423a;

        public DialogInterfaceOnClickListenerC0199j(List list) {
            this.f3423a = list;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            String str;
            if (i2 < 0 || i2 >= this.f3423a.size()) {
                return;
            }
            R rE2 = MusicPlazaActivity.this.e2((String) this.f3423a.get(i2));
            if (rE2 == null || (str = rE2.f3392c) == null || str.length() == 0) {
                return;
            }
            MusicPlazaActivity.this.i2(rE2);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.MusicPlazaActivity$k, reason: case insensitive filesystem */
    public class C0200k extends BroadcastReceiver {
        public C0200k() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MusicPlazaActivity.this.Q1(intent);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.MusicPlazaActivity$l, reason: case insensitive filesystem */
    public class RunnableC0201l implements Runnable {
        public RunnableC0201l() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MusicPlazaActivity.this.isFinishing()) {
                return;
            }
            MusicPlazaActivity.this.F0 = false;
            MusicPlazaActivity.this.U2();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.MusicPlazaActivity$m, reason: case insensitive filesystem */
    public class ViewOnClickListenerC0202m implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ R f3427a;

        public ViewOnClickListenerC0202m(R r2) {
            this.f3427a = r2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            R r2 = this.f3427a;
            if (r2 == null) {
                return;
            }
            MusicPlazaActivity.this.i2(r2);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.MusicPlazaActivity$n, reason: case insensitive filesystem */
    public class ViewOnLongClickListenerC0203n implements View.OnLongClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ R f3429a;

        public ViewOnLongClickListenerC0203n(R r2) {
            this.f3429a = r2;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            R r2 = this.f3429a;
            if (r2 == null) {
                return false;
            }
            MusicPlazaActivity.this.F2(r2);
            return true;
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.MusicPlazaActivity$o, reason: case insensitive filesystem */
    public class C0204o implements d.i {
        public C0204o() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("items");
                MusicPlazaActivity.this.f3343d0 = jSONObject.optInt("total", 0);
                MusicPlazaActivity.this.f3344e0 = jSONObject.optBoolean("has_more", false);
                MusicPlazaActivity.this.f3338Y.clear();
                if (jSONArrayOptJSONArray != null) {
                    for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                        JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i2);
                        if (jSONObjectOptJSONObject != null) {
                            R r2 = new R(null);
                            r2.f3390a = jSONObjectOptJSONObject.optString("id", "");
                            r2.f3391b = jSONObjectOptJSONObject.optString("name", "");
                            r2.f3392c = jSONObjectOptJSONObject.optString("song_url", "");
                            r2.f3393d = jSONObjectOptJSONObject.optString("cover_url", "");
                            r2.f3394e = jSONObjectOptJSONObject.optLong("size_bytes", 0L);
                            r2.f3395f = MusicPlazaActivity.this.b2(jSONObjectOptJSONObject);
                            r2.f3396g = jSONObjectOptJSONObject.optString("owner_uid", "");
                            r2.f3397h = jSONObjectOptJSONObject.optString("owner_name", "");
                            r2.f3398i = jSONObjectOptJSONObject.optString("owner_title", "");
                            r2.f3399j = jSONObjectOptJSONObject.optString("owner_avatar", "");
                            r2.f3400k = jSONObjectOptJSONObject.optInt("likes", 0);
                            r2.f3401l = jSONObjectOptJSONObject.optInt("comments", 0);
                            r2.f3402m = jSONObjectOptJSONObject.optInt("play_count", 0);
                            r2.f3403n = jSONObjectOptJSONObject.optBoolean("liked", false);
                            r2.f3404o = jSONObjectOptJSONObject.optBoolean("can_delete", false);
                            if (r2.f3390a.length() != 0 && r2.f3392c.length() != 0) {
                                MusicPlazaActivity.this.f3338Y.add(r2);
                            }
                        }
                    }
                }
                MusicPlazaActivity.this.f3341b0.notifyDataSetChanged();
                MusicPlazaActivity.this.m2();
                MusicPlazaActivity.this.T2();
                MusicPlazaActivity.this.U2();
                MusicPlazaActivity.this.W2();
                MusicPlazaActivity.this.W1();
            } catch (Exception unused) {
                Toast.makeText(MusicPlazaActivity.this, "加载失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(MusicPlazaActivity.this, "加载失败: " + i2, 0).show();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.MusicPlazaActivity$p, reason: case insensitive filesystem */
    public class C0205p implements d.i {
        public C0205p() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("items");
                MusicPlazaActivity.this.f3340a0.clear();
                if (jSONArrayOptJSONArray != null) {
                    for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                        JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i2);
                        if (jSONObjectOptJSONObject != null) {
                            R r2 = new R(null);
                            r2.f3390a = jSONObjectOptJSONObject.optString("id", "");
                            r2.f3391b = jSONObjectOptJSONObject.optString("name", "");
                            r2.f3392c = jSONObjectOptJSONObject.optString("song_url", "");
                            r2.f3393d = jSONObjectOptJSONObject.optString("cover_url", "");
                            r2.f3394e = jSONObjectOptJSONObject.optLong("size_bytes", 0L);
                            r2.f3395f = MusicPlazaActivity.this.b2(jSONObjectOptJSONObject);
                            r2.f3396g = jSONObjectOptJSONObject.optString("owner_uid", "");
                            r2.f3397h = jSONObjectOptJSONObject.optString("owner_name", "");
                            r2.f3398i = jSONObjectOptJSONObject.optString("owner_title", "");
                            r2.f3399j = jSONObjectOptJSONObject.optString("owner_avatar", "");
                            r2.f3400k = jSONObjectOptJSONObject.optInt("likes", 0);
                            r2.f3401l = jSONObjectOptJSONObject.optInt("comments", 0);
                            r2.f3402m = jSONObjectOptJSONObject.optInt("play_count", 0);
                            r2.f3403n = jSONObjectOptJSONObject.optBoolean("liked", false);
                            r2.f3404o = jSONObjectOptJSONObject.optBoolean("can_delete", false);
                            if (r2.f3390a.length() != 0 && r2.f3392c.length() != 0) {
                                MusicPlazaActivity.this.f3340a0.add(r2);
                            }
                        }
                    }
                }
                MusicPlazaActivity.this.U2();
            } catch (Exception unused) {
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.MusicPlazaActivity$q, reason: case insensitive filesystem */
    public class ViewOnClickListenerC0206q implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f3433a;

        public ViewOnClickListenerC0206q(EditText editText) {
            this.f3433a = editText;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicPlazaActivity.this.f3348i0 = this.f3433a.getText() == null ? "" : this.f3433a.getText().toString().trim();
            MusicPlazaActivity.this.S2();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.MusicPlazaActivity$r, reason: case insensitive filesystem */
    public class ViewOnClickListenerC0207r implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f3435a;

        public ViewOnClickListenerC0207r(EditText editText) {
            this.f3435a = editText;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicPlazaActivity.this.f3348i0 = this.f3435a.getText() == null ? "" : this.f3435a.getText().toString().trim();
            MusicPlazaActivity.this.Q2();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.MusicPlazaActivity$s, reason: case insensitive filesystem */
    public class DialogInterfaceOnDismissListenerC0208s implements DialogInterface.OnDismissListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ o.c f3437a;

        public DialogInterfaceOnDismissListenerC0208s(o.c cVar) {
            this.f3437a = cVar;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (MusicPlazaActivity.this.f3351l0 == this.f3437a) {
                MusicPlazaActivity.this.f3351l0 = null;
                MusicPlazaActivity.this.f3352m0 = null;
                MusicPlazaActivity.this.f3353n0 = null;
            }
        }
    }

    public class t implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f3439a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ o.c f3440b;

        public t(EditText editText, o.c cVar) {
            this.f3439a = editText;
            this.f3440b = cVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws Throwable {
            String strTrim = this.f3439a.getText() == null ? "" : this.f3439a.getText().toString().trim();
            if (strTrim.length() == 0) {
                Toast.makeText(MusicPlazaActivity.this, "请填写歌曲名称", 0).show();
            } else {
                if (MusicPlazaActivity.this.f3346g0 == null) {
                    Toast.makeText(MusicPlazaActivity.this, "请选择歌曲文件", 0).show();
                    return;
                }
                MusicPlazaActivity.this.f3348i0 = strTrim;
                this.f3440b.dismiss();
                MusicPlazaActivity.this.Y2();
            }
        }
    }

    public class u implements View.OnClickListener {
        public u() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicPlazaActivity.this.R2();
        }
    }

    public class v extends BroadcastReceiver {
        public v() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MusicPlazaActivity.this.R1(intent);
        }
    }

    public class w implements DialogInterface.OnDismissListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ o.c f3444a;

        public w(o.c cVar) {
            this.f3444a = cVar;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (MusicPlazaActivity.this.f3354o0 == this.f3444a) {
                MusicPlazaActivity.this.f3354o0 = null;
                MusicPlazaActivity.this.q0 = null;
                MusicPlazaActivity.this.p0 = null;
            }
        }
    }

    public class x implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f3446a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ o.c f3447b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ R f3448c;

        public x(EditText editText, o.c cVar, R r2) {
            this.f3446a = editText;
            this.f3447b = cVar;
            this.f3448c = r2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws Throwable {
            String strTrim = this.f3446a.getText() == null ? "" : this.f3446a.getText().toString().trim();
            if (strTrim.length() == 0) {
                Toast.makeText(MusicPlazaActivity.this, "请填写歌曲名称", 0).show();
                return;
            }
            this.f3447b.dismiss();
            MusicPlazaActivity musicPlazaActivity = MusicPlazaActivity.this;
            musicPlazaActivity.N2(this.f3448c, strTrim, musicPlazaActivity.p0);
        }
    }

    public class y implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f3450a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ R f3451b;

        public y(String str, R r2) {
            this.f3450a = str;
            this.f3451b = r2;
        }

        @Override // g0.d.i
        public void b(String str) {
            String strTrim = this.f3450a;
            String strTrim2 = this.f3451b.f3393d;
            try {
                JSONObject jSONObject = new JSONObject(str);
                String strOptString = jSONObject.optString("name", "");
                if (strOptString != null && strOptString.trim().length() > 0) {
                    strTrim = strOptString.trim();
                }
                String strOptString2 = jSONObject.optString("cover_url", "");
                if (strOptString2 != null && strOptString2.trim().length() > 0) {
                    strTrim2 = strOptString2.trim();
                }
            } catch (Exception unused) {
            }
            MusicPlazaActivity.this.x1(this.f3451b.f3390a, strTrim, strTrim2);
            Toast.makeText(MusicPlazaActivity.this, "修改成功", 0).show();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            if (i2 == 400 && str != null && str.contains("invalid_name")) {
                Toast.makeText(MusicPlazaActivity.this, "歌曲名称不合法", 0).show();
                return;
            }
            if (i2 == 400 && str != null && str.contains("invalid_cover")) {
                Toast.makeText(MusicPlazaActivity.this, "封面格式不支持", 0).show();
                return;
            }
            if (i2 == 413 && str != null && str.contains("cover_too_large")) {
                Toast.makeText(MusicPlazaActivity.this, "封面不能超过1MB", 0).show();
                return;
            }
            if (i2 == 404 && str != null && str.contains("not_found")) {
                Toast.makeText(MusicPlazaActivity.this, "歌曲不存在，列表将刷新", 0).show();
                MusicPlazaActivity.this.V1();
            } else {
                if (i2 == 403) {
                    Toast.makeText(MusicPlazaActivity.this, "只能编辑自己上传的歌曲", 0).show();
                    return;
                }
                Toast.makeText(MusicPlazaActivity.this, "修改失败: " + i2, 0).show();
            }
        }
    }

    public class z implements d.k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Uri f3453a;

        public z(Uri uri) {
            this.f3453a = uri;
        }

        @Override // g0.d.k
        public InputStream a() {
            return MusicPlazaActivity.this.Z1(this.f3453a);
        }

        @Override // g0.d.k
        public long length() {
            return MusicPlazaActivity.this.u2(this.f3453a);
        }
    }

    private TextView A1(String str) {
        TextView textView = new TextView(this);
        textView.setText(str);
        textView.setTextColor(getResources().getColor(f0.d.f4973j));
        textView.setGravity(17);
        textView.setPadding(I1(10), I1(8), I1(10), I1(8));
        textView.setBackgroundResource(f0.e.f4995P);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.bottomMargin = I1(6);
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    private int I1(int i2) {
        return (int) ((i2 * getResources().getDisplayMetrics().density) + 0.5f);
    }

    private boolean L1(int i2) {
        String strW2;
        if (S1(i2) || (strW2 = w2(i2)) == null || strW2.length() == 0) {
            return true;
        }
        this.f3349j0 = i2;
        AbstractC0121a.i(this, new String[]{strW2}, 4303);
        return false;
    }

    private void L2(Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            startService(intent);
        } catch (Exception unused) {
            synchronized (this.L0) {
                this.L0.remove(o0.A.g(intent.getStringExtra("song_url")));
                Toast.makeText(this, "启动下载失败", 0).show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String N1(int i2) {
        if (i2 <= 0) {
            return "--:--";
        }
        int i3 = i2 / 1000;
        int i4 = i3 / 60;
        int i5 = i3 % 60;
        if (i4 > 99) {
            i4 = 99;
        }
        return String.format(Locale.getDefault(), "%02d:%02d", Integer.valueOf(i4), Integer.valueOf(i5));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String O1(long j2) {
        if (j2 <= 0) {
            return "0B";
        }
        float f2 = j2;
        if (f2 >= 1024.0f) {
            float f3 = f2 / 1024.0f;
            return f3 < 1024.0f ? String.format(Locale.getDefault(), "%.1fKB", Float.valueOf(f3)) : String.format(Locale.getDefault(), "%.1fMB", Float.valueOf(f3 / 1024.0f));
        }
        return ((int) f2) + "B";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O2(String str, String str2) {
        if (str == null || str.length() == 0) {
            Toast.makeText(this, "举报失败：缺少目标用户", 0).show();
            return;
        }
        String str3 = this.f3336W;
        if (str3 == null || str3.length() == 0) {
            Toast.makeText(this, getString(f0.j.f5190N), 0).show();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("target_uid", str);
            if (str2 == null) {
                str2 = "";
            }
            jSONObject.put("reason", str2);
            g0.d.S("/reports/user", jSONObject, this.f3336W, new D());
        } catch (Exception unused) {
            Toast.makeText(this, "举报失败", 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R2() {
        this.f3350k0 = 4304;
        if (L1(2)) {
            g2();
        }
    }

    private boolean S1(int i2) {
        String strW2 = w2(i2);
        return strW2 == null || strW2.length() == 0 || AbstractC0235a.a(this, strW2) == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void V1() {
        String str = this.f3336W;
        if (str == null || str.length() == 0) {
            Toast.makeText(this, getString(f0.j.f5190N), 0).show();
            return;
        }
        EditText editText = this.f3355w;
        String strTrim = (editText == null || editText.getText() == null) ? "" : this.f3355w.getText().toString().trim();
        StringBuilder sb = new StringBuilder("/music/plaza?limit=");
        sb.append(50);
        sb.append("&offset=");
        sb.append(this.f3342c0);
        sb.append("&sort=latest");
        if (this.f3345f0) {
            sb.append("&mine=1");
        }
        if (strTrim.length() > 0) {
            sb.append("&q=");
            sb.append(Z2(strTrim));
        }
        g0.d.R(sb.toString(), this.f3336W, new C0204o());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W2() {
        if (this.f3332S != null) {
            int i2 = this.f3343d0;
            int i3 = i2 <= 0 ? 0 : this.f3342c0 + 1;
            int iMin = Math.min(i2, this.f3342c0 + this.f3338Y.size());
            this.f3332S.setText("第 " + i3 + " - " + iMin + " 条，共 " + this.f3343d0 + " 条");
        }
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < this.f3338Y.size(); i6++) {
            R r2 = (R) this.f3338Y.get(i6);
            if (r2 != null) {
                if (r2.f3404o) {
                    i4++;
                }
                if (r2.f3403n) {
                    i5++;
                }
            }
        }
        TextView textView = this.f3324K;
        if (textView != null) {
            if (this.f3345f0) {
                textView.setText("我的上传：当前页 " + this.f3338Y.size() + " 首，累计 " + this.f3343d0 + " 首");
            } else {
                textView.setText("共 " + this.f3343d0 + " 首 · 当前加载 " + this.f3338Y.size() + " 首 · 我的 " + i4 + " 首 · 已赞 " + i5 + " 首");
            }
        }
        TextView textView2 = this.f3333T;
        if (textView2 != null) {
            textView2.setEnabled(this.f3342c0 > 0);
            AbstractC0285k0.K(this.f3333T, this.f3342c0 > 0 ? 1.0f : 0.55f);
        }
        TextView textView3 = this.f3334U;
        if (textView3 != null) {
            textView3.setEnabled(this.f3344e0);
            AbstractC0285k0.K(this.f3334U, this.f3344e0 ? 1.0f : 0.55f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InputStream Z1(Uri uri) throws IOException {
        InputStream inputStreamOpenInputStream;
        if (uri == null) {
            throw new IOException("uri_empty");
        }
        try {
            inputStreamOpenInputStream = getContentResolver().openInputStream(uri);
        } catch (Exception unused) {
            inputStreamOpenInputStream = null;
        }
        if (inputStreamOpenInputStream != null) {
            return inputStreamOpenInputStream;
        }
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
            if (parcelFileDescriptorOpenFileDescriptor != null) {
                return new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptorOpenFileDescriptor);
            }
        } catch (Exception unused2) {
        }
        throw new IOException("cannot_open_stream");
    }

    private String Z2(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception unused) {
            return str;
        }
    }

    private int d2(String str) {
        if (str == null) {
            return 0;
        }
        String strTrim = str.trim();
        if (strTrim.length() == 0) {
            return 0;
        }
        String[] strArrSplit = strTrim.split(":");
        if (strArrSplit.length >= 2 && strArrSplit.length <= 3) {
            int i2 = 0;
            int i3 = 0;
            while (i2 < strArrSplit.length) {
                try {
                    String str2 = strArrSplit[i2];
                    String strTrim2 = str2 == null ? "" : str2.trim();
                    if (strTrim2.length() == 0) {
                        return 0;
                    }
                    int i4 = (i2 == strArrSplit.length + (-1) && strTrim2.contains(".")) ? (int) Double.parseDouble(strTrim2) : Integer.parseInt(strTrim2);
                    if (i4 < 0) {
                        return 0;
                    }
                    i3 = (i3 * 60) + i4;
                    i2++;
                } catch (Exception unused) {
                }
            }
            if (i3 <= 0) {
                return 0;
            }
            long j2 = ((long) i3) * 1000;
            if (j2 > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            return (int) j2;
        }
        return 0;
    }

    private void g2() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.addFlags(1);
        startActivityForResult(Intent.createChooser(intent, "选择新的封面图片"), 4304);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String k2(android.net.Uri r10) throws java.lang.Throwable {
        /*
            r9 = this;
            java.lang.String r0 = "_display_name"
            java.lang.String r1 = ""
            if (r10 != 0) goto L7
            return r1
        L7:
            r2 = 0
            android.content.ContentResolver r3 = r9.getContentResolver()     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            r4 = 1
            java.lang.String[] r5 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            r4 = 0
            r5[r4] = r0     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            r7 = 0
            r8 = 0
            r6 = 0
            r4 = r10
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            if (r3 == 0) goto L32
            boolean r4 = r3.moveToFirst()     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L30
            if (r4 == 0) goto L32
            int r0 = r3.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L30
            if (r0 < 0) goto L32
            java.lang.String r2 = r3.getString(r0)     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L30
            goto L32
        L2d:
            r10 = move-exception
            r2 = r3
            goto L3d
        L30:
            goto L43
        L32:
            if (r3 == 0) goto L46
        L34:
            r3.close()
            goto L46
        L38:
            r10 = move-exception
            goto L3d
        L3a:
            r3 = r2
            goto L43
        L3d:
            if (r2 == 0) goto L42
            r2.close()
        L42:
            throw r10
        L43:
            if (r3 == 0) goto L46
            goto L34
        L46:
            if (r2 == 0) goto L4e
            int r0 = r2.length()
            if (r0 != 0) goto L5b
        L4e:
            java.lang.String r10 = r10.getLastPathSegment()
            if (r10 == 0) goto L5b
            int r0 = r10.length()
            if (r0 <= 0) goto L5b
            r2 = r10
        L5b:
            if (r2 != 0) goto L5e
            goto L5f
        L5e:
            r1 = r2
        L5f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.im.oldchat.ui.MusicPlazaActivity.k2(android.net.Uri):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x003e, code lost:
    
        r12 = r1.toByteArray();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0042, code lost:
    
        r11.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private byte[] l2(android.net.Uri r11, long r12) throws java.lang.Throwable {
        /*
            r10 = this;
            r0 = 0
            if (r11 != 0) goto L4
            return r0
        L4:
            java.io.InputStream r11 = r10.Z1(r11)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L5e
            if (r11 != 0) goto L10
            if (r11 == 0) goto Lf
            r11.close()     // Catch: java.lang.Exception -> Lf
        Lf:
            return r0
        L10:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4c
            r1.<init>()     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4c
            r2 = 8192(0x2000, float:1.148E-41)
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            r3 = 0
            r5 = r3
        L1c:
            int r7 = r11.read(r2)     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            r8 = -1
            if (r7 == r8) goto L3e
            long r8 = (long) r7
            long r5 = r5 + r8
            int r8 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r8 <= 0) goto L34
            int r8 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r8 <= 0) goto L34
            r11.close()     // Catch: java.lang.Exception -> L30
        L30:
            r1.close()     // Catch: java.lang.Exception -> L33
        L33:
            return r0
        L34:
            r8 = 0
            r1.write(r2, r8, r7)     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            goto L1c
        L39:
            r12 = move-exception
        L3a:
            r0 = r11
            goto L51
        L3c:
            goto L61
        L3e:
            byte[] r12 = r1.toByteArray()     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            r11.close()     // Catch: java.lang.Exception -> L45
        L45:
            r1.close()     // Catch: java.lang.Exception -> L48
        L48:
            return r12
        L49:
            r12 = move-exception
            r1 = r0
            goto L3a
        L4c:
            r1 = r0
            goto L61
        L4f:
            r12 = move-exception
            r1 = r0
        L51:
            if (r0 == 0) goto L58
            r0.close()     // Catch: java.lang.Exception -> L57
            goto L58
        L57:
        L58:
            if (r1 == 0) goto L5d
            r1.close()     // Catch: java.lang.Exception -> L5d
        L5d:
            throw r12
        L5e:
            r11 = r0
            r1 = r11
        L61:
            if (r11 == 0) goto L68
            r11.close()     // Catch: java.lang.Exception -> L67
            goto L68
        L67:
        L68:
            if (r1 == 0) goto L6d
            r1.close()     // Catch: java.lang.Exception -> L6d
        L6d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.im.oldchat.ui.MusicPlazaActivity.l2(android.net.Uri, long):byte[]");
    }

    private void n2() {
        TextView textView = this.q0;
        if (textView == null) {
            return;
        }
        if (this.p0 == null) {
            textView.setText("新封面: 不修改");
            return;
        }
        textView.setText("新封面: " + y2(this.p0, "cover"));
    }

    public static /* synthetic */ int q1(MusicPlazaActivity musicPlazaActivity, int i2) {
        int i3 = musicPlazaActivity.f3342c0 + i2;
        musicPlazaActivity.f3342c0 = i3;
        return i3;
    }

    private void t2() {
        Intent intent = new Intent(this, (Class<?>) MusicPlaybackService.class);
        intent.setAction("com.im.oldchat.action.music.REQUEST_STATE");
        try {
            startService(intent);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0061 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0094 A[Catch: all -> 0x00a0, Exception -> 0x00a2, TRY_LEAVE, TryCatch #19 {Exception -> 0x00a2, all -> 0x00a0, blocks: (B:54:0x008a, B:56:0x0094), top: B:100:0x008a }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0078 A[EXC_TOP_SPLITTER, PHI: r1
  0x0078: PHI (r1v4 android.content.res.AssetFileDescriptor) = (r1v2 android.content.res.AssetFileDescriptor), (r1v6 android.content.res.AssetFileDescriptor) binds: [B:52:0x0087, B:43:0x0076] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00a6 A[EXC_TOP_SPLITTER, PHI: r6
  0x00a6: PHI (r6v5 android.os.ParcelFileDescriptor) = (r6v4 android.os.ParcelFileDescriptor), (r6v6 android.os.ParcelFileDescriptor) binds: [B:70:0x00b0, B:64:0x00a4] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long u2(android.net.Uri r14) throws java.lang.Throwable {
        /*
            r13 = this;
            java.lang.String r0 = "r"
            java.lang.String r1 = "_size"
            r2 = -1
            if (r14 != 0) goto L9
            return r2
        L9:
            r4 = 0
            r6 = 0
            android.content.ContentResolver r7 = r13.getContentResolver()     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4b
            r8 = 1
            java.lang.String[] r9 = new java.lang.String[r8]     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4b
            r8 = 0
            r9[r8] = r1     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4b
            r11 = 0
            r12 = 0
            r10 = 0
            r8 = r14
            android.database.Cursor r7 = r7.query(r8, r9, r10, r11, r12)     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4b
            if (r7 == 0) goto L43
            boolean r8 = r7.moveToFirst()     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L41
            if (r8 == 0) goto L43
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L41
            if (r1 < 0) goto L43
            boolean r8 = r7.isNull(r1)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L41
            if (r8 != 0) goto L43
            long r8 = r7.getLong(r1)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L41
            int r1 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r1 <= 0) goto L43
            r7.close()     // Catch: java.lang.Exception -> L3d
        L3d:
            return r8
        L3e:
            r14 = move-exception
            r6 = r7
            goto L4e
        L41:
            goto L54
        L43:
            if (r7 == 0) goto L57
        L45:
            r7.close()     // Catch: java.lang.Exception -> L57
            goto L57
        L49:
            r14 = move-exception
            goto L4e
        L4b:
            r7 = r6
            goto L54
        L4e:
            if (r6 == 0) goto L53
            r6.close()     // Catch: java.lang.Exception -> L53
        L53:
            throw r14
        L54:
            if (r7 == 0) goto L57
            goto L45
        L57:
            android.content.ContentResolver r1 = r13.getContentResolver()     // Catch: java.lang.Throwable -> L7c java.lang.Exception -> L7e
            android.content.res.AssetFileDescriptor r1 = r1.openAssetFileDescriptor(r14, r0)     // Catch: java.lang.Throwable -> L7c java.lang.Exception -> L7e
            if (r1 == 0) goto L76
            long r7 = r1.getLength()     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L74
            int r9 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r9 <= 0) goto L76
            long r2 = r1.getLength()     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L74
            r1.close()     // Catch: java.lang.Exception -> L70
        L70:
            return r2
        L71:
            r14 = move-exception
            r6 = r1
            goto L81
        L74:
            goto L87
        L76:
            if (r1 == 0) goto L8a
        L78:
            r1.close()     // Catch: java.lang.Exception -> L8a
            goto L8a
        L7c:
            r14 = move-exception
            goto L81
        L7e:
            r1 = r6
            goto L87
        L81:
            if (r6 == 0) goto L86
            r6.close()     // Catch: java.lang.Exception -> L86
        L86:
            throw r14
        L87:
            if (r1 == 0) goto L8a
            goto L78
        L8a:
            android.content.ContentResolver r1 = r13.getContentResolver()     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            android.os.ParcelFileDescriptor r6 = r1.openFileDescriptor(r14, r0)     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            if (r6 == 0) goto La4
            long r0 = r6.getStatSize()     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            int r14 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r14 <= 0) goto La4
            r6.close()     // Catch: java.lang.Exception -> L9f
        L9f:
            return r0
        La0:
            r14 = move-exception
            goto Laa
        La2:
            goto Lb0
        La4:
            if (r6 == 0) goto Lb3
        La6:
            r6.close()     // Catch: java.lang.Exception -> Lb3
            goto Lb3
        Laa:
            if (r6 == 0) goto Laf
            r6.close()     // Catch: java.lang.Exception -> Laf
        Laf:
            throw r14
        Lb0:
            if (r6 == 0) goto Lb3
            goto La6
        Lb3:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.im.oldchat.ui.MusicPlazaActivity.u2(android.net.Uri):long");
    }

    private String v2(Uri uri, String str) {
        String type;
        try {
            type = getContentResolver().getType(uri);
        } catch (Exception unused) {
            type = null;
        }
        if (!TextUtils.isEmpty(type)) {
            return type;
        }
        String lowerCase = str == null ? "" : str.toLowerCase(Locale.US);
        return lowerCase.endsWith(".gif") ? "image/gif" : lowerCase.endsWith(".png") ? "image/png" : "image/jpeg";
    }

    private String w2(int i2) {
        int i3 = Build.VERSION.SDK_INT;
        return i3 < 23 ? "" : i3 >= 33 ? i2 == 2 ? "android.permission.READ_MEDIA_IMAGES" : "android.permission.READ_MEDIA_AUDIO" : "android.permission.READ_EXTERNAL_STORAGE";
    }

    public final void A2(R r2) {
        String str;
        if (r2 == null || (str = r2.f3392c) == null || str.length() == 0) {
            return;
        }
        String str2 = r2.f3391b;
        if (str2 == null) {
            str2 = "";
        }
        String str3 = r2.f3397h;
        if (str3 == null || str3.length() == 0) {
            String str4 = r2.f3396g;
            str3 = str4 != null ? str4 : "";
        }
        String str5 = J1(r2.f3390a) + "|" + J1(str2) + "|" + J1(str3) + "|" + J1(r2.f3392c) + "|" + J1(r2.f3393d);
        List listP1 = P1();
        ArrayList arrayList = new ArrayList();
        arrayList.add(str5);
        for (int i2 = 0; i2 < listP1.size(); i2++) {
            String str6 = (String) listP1.get(i2);
            if (str6 != null && str6.length() != 0 && !str6.equals(str5)) {
                arrayList.add(str6);
                if (arrayList.size() >= 30) {
                    break;
                }
            }
        }
        getSharedPreferences("music_plaza_local", 0).edit().putString("recent_play", U1(arrayList)).apply();
    }

    public final View B1(R r2, boolean z2) {
        String str;
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundResource(f0.e.f5028o);
        linearLayout.setPadding(I1(8), I1(8), I1(8), I1(8));
        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, I1(z2 ? 118 : CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setBackgroundResource(f0.e.f5029p);
        imageView.setPadding(1, 1, 1, 1);
        if (r2 != null) {
            String str2 = r2.f3393d;
            if (str2 == null || str2.length() <= 0) {
                String str3 = r2.f3399j;
                if (str3 == null || str3.length() <= 0) {
                    imageView.setImageResource(f0.e.f4999T);
                } else {
                    AbstractC0455v.e(imageView, r2.f3399j);
                }
            } else {
                AbstractC0455v.e(imageView, r2.f3393d);
            }
        } else {
            imageView.setImageResource(f0.e.f4999T);
        }
        linearLayout.addView(imageView, layoutParams);
        TextView textView = new TextView(this);
        textView.setTextColor(getResources().getColor(f0.d.f4973j));
        textView.setTextSize(13.0f);
        textView.setMaxLines(1);
        TextUtils.TruncateAt truncateAt = TextUtils.TruncateAt.END;
        textView.setEllipsize(truncateAt);
        textView.setPadding(0, I1(6), 0, 0);
        textView.setText((r2 == null || (str = r2.f3391b) == null || str.length() == 0) ? "未命名歌曲" : r2.f3391b);
        linearLayout.addView(textView);
        TextView textView2 = new TextView(this);
        textView2.setTextColor(getResources().getColor(f0.d.f4974k));
        textView2.setTextSize(11.0f);
        textView2.setMaxLines(2);
        textView2.setEllipsize(truncateAt);
        String str4 = "匿名用户";
        String str5 = r2 == null ? "匿名用户" : r2.f3397h;
        if (str5 == null || str5.length() == 0) {
            str5 = r2 == null ? "匿名用户" : r2.f3396g;
        }
        if (str5 != null && str5.length() != 0) {
            str4 = str5;
        }
        int i2 = r2 == null ? 0 : r2.f3395f;
        StringBuilder sb = new StringBuilder();
        sb.append(str4);
        sb.append(" · ");
        sb.append(N1(i2));
        sb.append(" · 播放 ");
        sb.append(r2 != null ? r2.f3402m : 0);
        textView2.setText(sb.toString());
        linearLayout.addView(textView2);
        linearLayout.setOnClickListener(new ViewOnClickListenerC0202m(r2));
        linearLayout.setOnLongClickListener(new ViewOnLongClickListenerC0203n(r2));
        return linearLayout;
    }

    public final void B2() {
        if (isFinishing()) {
            return;
        }
        this.F0 = true;
        if (this.H0 == null) {
            this.H0 = new RunnableC0201l();
        }
        this.G0.removeCallbacks(this.H0);
        this.G0.postDelayed(this.H0, 220L);
    }

    public final void C1(R r2) {
        String str;
        if (r2 == null || (str = r2.f3392c) == null || str.length() == 0) {
            Toast.makeText(this, "歌曲链接不可用", 0).show();
            return;
        }
        String strG = o0.A.g(r2.f3392c);
        if (strG == null || strG.length() == 0) {
            Toast.makeText(this, "歌曲链接不可用", 0).show();
            return;
        }
        synchronized (this.L0) {
            try {
                if (this.L0.contains(strG)) {
                    Toast.makeText(this, "该歌曲正在缓存中", 0).show();
                    return;
                }
                this.L0.add(strG);
                Toast.makeText(this, "已开始缓存，完成后会提示", 0).show();
                Intent intent = new Intent(this, (Class<?>) MusicPlaybackService.class);
                intent.setAction("com.im.oldchat.action.music.CACHE_SONG");
                intent.putExtra("song_url", strG);
                L2(intent);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void C2(boolean z2, boolean z3) {
        if (this.f3345f0 == z2) {
            if (z3) {
                this.f3342c0 = 0;
                V1();
                return;
            }
            return;
        }
        this.f3345f0 = z2;
        z1();
        if (z3) {
            this.f3342c0 = 0;
            V1();
        }
    }

    public final boolean D1(Uri uri) {
        if (uri == null) {
            return false;
        }
        try {
            InputStream inputStreamZ1 = Z1(uri);
            boolean z2 = inputStreamZ1 != null;
            if (inputStreamZ1 != null) {
                try {
                    inputStreamZ1.close();
                } catch (Exception unused) {
                }
            }
            return z2;
        } catch (Exception unused2) {
            return false;
        }
    }

    public final void D2(R r2) {
        String str;
        String str2;
        if (r2 == null || (str = r2.f3392c) == null || str.length() == 0) {
            Toast.makeText(this, "歌曲链接不可用", 0).show();
            return;
        }
        Y.f fVar = new Y.f();
        fVar.f4329a = r2.f3390a;
        fVar.f4330b = r2.f3391b;
        fVar.f4331c = r2.f3392c;
        String str3 = r2.f3393d;
        fVar.f4332d = str3;
        if ((str3 == null || str3.length() == 0) && (str2 = r2.f3399j) != null && str2.length() > 0) {
            fVar.f4332d = r2.f3399j;
        }
        fVar.f4333e = r2.f3396g;
        fVar.f4334f = r2.f3397h;
        fVar.f4335g = r2.f3395f;
        Y.m(this, this.f3336W, fVar);
    }

    public final String E1(String str) {
        List listK2 = K2(str);
        String str2 = listK2.size() > 1 ? (String) listK2.get(1) : "";
        String str3 = listK2.size() > 2 ? (String) listK2.get(2) : "";
        if (str2 == null || str2.length() == 0) {
            str2 = "未命名歌曲";
        }
        if (str3 == null || str3.length() == 0) {
            str3 = "匿名用户";
        }
        return str2 + " · " + str3;
    }

    public final void E2(R r2) {
        String str;
        if (r2 == null || !r2.f3404o || (str = r2.f3390a) == null || str.length() == 0) {
            return;
        }
        this.p0 = null;
        EditText editText = new EditText(this);
        editText.setHint("请输入歌曲名称");
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(64)});
        String str2 = r2.f3391b;
        if (str2 != null) {
            editText.setText(str2);
            editText.setSelection(r2.f3391b.length());
        }
        TextView textViewA1 = A1("选择新封面");
        TextView textView = new TextView(this);
        this.q0 = textView;
        textView.setPadding(0, I1(6), 0, I1(4));
        n2();
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(I1(14), I1(6), I1(14), 0);
        linearLayout.addView(editText);
        linearLayout.addView(textView);
        linearLayout.addView(textViewA1);
        textViewA1.setOnClickListener(new u());
        o.c cVarA = new c.a(this).t("编辑歌曲信息").u(linearLayout).k("取消", null).q("保存", null).a();
        this.f3354o0 = cVarA;
        cVarA.show();
        cVarA.setOnDismissListener(new w(cVarA));
        Button buttonD = cVarA.d(-1);
        if (buttonD != null) {
            buttonD.setOnClickListener(new x(editText, cVarA, r2));
        }
    }

    public final String F1(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z2 = false;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            if (z2) {
                if (cCharAt == 'p') {
                    sb.append('|');
                } else {
                    sb.append(cCharAt);
                }
                z2 = false;
            } else if (cCharAt == '\\') {
                z2 = true;
            } else {
                sb.append(cCharAt);
            }
        }
        if (z2) {
            sb.append('\\');
        }
        return sb.toString();
    }

    public final void F2(R r2) {
        if (r2 == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("播放");
        arrayList.add("下载到本地");
        arrayList.add("缓存到本地");
        arrayList.add(r2.f3403n ? "取消点赞" : "点赞");
        arrayList.add("评论");
        arrayList.add("分享到聊天");
        arrayList.add("收藏");
        arrayList.add("举报");
        if (r2.f3404o) {
            arrayList.add("编辑信息");
            arrayList.add("删除歌曲");
        }
        CharSequence[] charSequenceArr = (CharSequence[]) arrayList.toArray(new CharSequence[arrayList.size()]);
        c.a aVar = new c.a(this);
        String str = r2.f3391b;
        aVar.t((str == null || str.length() == 0) ? "歌曲操作" : r2.f3391b).g(charSequenceArr, new B(arrayList, r2)).k("取消", null).v();
    }

    public final void G1(R r2) {
        String str;
        if (r2 == null || (str = r2.f3390a) == null || str.length() == 0 || !r2.f3404o) {
            return;
        }
        new c.a(this).t("删除歌曲").i("确定删除这首歌曲吗？").q("删除", new H(r2)).k("取消", null).v();
    }

    public final void G2() {
        new c.a(this).t("我的").g(new String[]{"最近听过", "我的发布", "上传音乐"}, new DialogInterfaceOnClickListenerC0198i()).k("取消", null).v();
    }

    public final void H1(R r2) {
        String str;
        if (r2 == null || (str = r2.f3392c) == null || str.length() == 0) {
            Toast.makeText(this, "歌曲链接不可用", 0).show();
            return;
        }
        String strG = o0.A.g(r2.f3392c);
        if (strG == null || strG.length() == 0) {
            Toast.makeText(this, "歌曲链接不可用", 0).show();
        } else {
            Toast.makeText(this, "开始下载到本地", 0).show();
            AbstractC0446l.j(this, strG, "oldchat_music_", ".mp3", new E());
        }
    }

    public final void H2(R r2) {
        if (r2 == null) {
            return;
        }
        String str = r2.f3396g;
        String strTrim = str == null ? "" : str.trim();
        if (strTrim.length() == 0) {
            Toast.makeText(this, "无法举报：缺少发布者信息", 0).show();
            return;
        }
        String str2 = this.f3337X;
        if (str2 != null && str2.length() > 0 && this.f3337X.equalsIgnoreCase(strTrim)) {
            Toast.makeText(this, "不能举报自己发布的歌曲", 0).show();
            return;
        }
        EditText editText = new EditText(this);
        editText.setHint("如：侵权、违规内容、恶意音频等");
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256)});
        int iI1 = I1(10);
        editText.setPadding(iI1, iI1, iI1, iI1);
        new c.a(this).t("举报歌曲").u(editText).k("取消", null).q("提交", new C(editText, r2, strTrim)).v();
    }

    public final void I2() {
        List listP1 = P1();
        if (listP1.isEmpty()) {
            Toast.makeText(this, "暂无最近听过", 0).show();
            return;
        }
        CharSequence[] charSequenceArr = new CharSequence[listP1.size()];
        for (int i2 = 0; i2 < listP1.size(); i2++) {
            charSequenceArr[i2] = E1((String) listP1.get(i2));
        }
        new c.a(this).t("最近听过").g(charSequenceArr, new DialogInterfaceOnClickListenerC0199j(listP1)).k("关闭", null).v();
    }

    public final String J1(String str) {
        if (str == null) {
            str = "";
        }
        return str.replace("\\", "\\\\").replace("|", "\\p").replace("\n", " ");
    }

    public final void J2() {
        EditText editText = new EditText(this);
        editText.setHint("请输入歌曲名称");
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(64)});
        TextView textView = new TextView(this);
        TextView textView2 = new TextView(this);
        this.f3352m0 = textView;
        this.f3353n0 = textView2;
        o2();
        textView.setPadding(0, I1(6), 0, I1(4));
        textView2.setPadding(0, I1(2), 0, I1(4));
        TextView textViewA1 = A1("选择歌曲文件");
        TextView textViewA12 = A1("选择封面文件");
        this.f3346g0 = null;
        this.f3347h0 = null;
        this.f3348i0 = null;
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(I1(14), I1(6), I1(14), 0);
        linearLayout.addView(editText);
        linearLayout.addView(textView);
        linearLayout.addView(textViewA1);
        linearLayout.addView(textView2);
        linearLayout.addView(textViewA12);
        textViewA1.setOnClickListener(new ViewOnClickListenerC0206q(editText));
        textViewA12.setOnClickListener(new ViewOnClickListenerC0207r(editText));
        o.c cVarA = new c.a(this).t("上传音乐").u(linearLayout).k("取消", null).q("开始上传", null).a();
        this.f3351l0 = cVarA;
        cVarA.show();
        o2();
        cVarA.setOnDismissListener(new DialogInterfaceOnDismissListenerC0208s(cVarA));
        Button buttonD = cVarA.d(-1);
        if (buttonD != null) {
            buttonD.setOnClickListener(new t(editText, cVarA));
        }
    }

    public final void K1(R r2) {
        String strG;
        if (r2 == null || r2.f3395f > 0 || (strG = o0.A.g(r2.f3392c)) == null || strG.length() == 0) {
            return;
        }
        synchronized (this.I0) {
            try {
                Integer num = (Integer) this.J0.get(strG);
                if (num != null && num.intValue() > 0) {
                    r2.f3395f = num.intValue();
                    return;
                }
                if (this.K0.contains(strG)) {
                    return;
                }
                this.K0.add(strG);
                J j2 = new J(strG);
                if (Build.VERSION.SDK_INT >= 11) {
                    j2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
                } else {
                    j2.execute((Object[]) null);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final List K2(String str) {
        ArrayList arrayList = new ArrayList();
        if (str == null) {
            return arrayList;
        }
        StringBuilder sb = new StringBuilder();
        boolean z2 = false;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            if (z2) {
                sb.append('\\');
                sb.append(cCharAt);
                z2 = false;
            } else if (cCharAt == '\\') {
                z2 = true;
            } else if (cCharAt == '|') {
                arrayList.add(F1(sb.toString()));
                sb.setLength(0);
            } else {
                sb.append(cCharAt);
            }
        }
        if (z2) {
            sb.append('\\');
        }
        arrayList.add(F1(sb.toString()));
        return arrayList;
    }

    public final int M1(Object obj, int i2) {
        int iD2;
        if (obj != null && obj != JSONObject.NULL && i2 <= 4) {
            if (obj instanceof JSONObject) {
                JSONObject jSONObject = (JSONObject) obj;
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    Object objOpt = jSONObject.opt(next);
                    String lowerCase = next == null ? "" : next.toLowerCase(Locale.US);
                    if (T1(lowerCase)) {
                        int iX1 = X1(c2(objOpt), lowerCase);
                        if (iX1 > 0) {
                            return iX1;
                        }
                        if ((objOpt instanceof String) && (iD2 = d2((String) objOpt)) > 0) {
                            return iD2;
                        }
                    }
                    int iM1 = M1(objOpt, i2 + 1);
                    if (iM1 > 0) {
                        return iM1;
                    }
                }
                return 0;
            }
            if (obj instanceof JSONArray) {
                JSONArray jSONArray = (JSONArray) obj;
                for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                    int iM12 = M1(jSONArray.opt(i3), i2 + 1);
                    if (iM12 > 0) {
                        return iM12;
                    }
                }
                return 0;
            }
            if (obj instanceof String) {
                return d2((String) obj);
            }
        }
        return 0;
    }

    public final void M2() {
        MediaPlayer mediaPlayer = this.r0;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
            } catch (Exception unused) {
            }
            try {
                this.r0.release();
            } catch (Exception unused2) {
            }
            this.r0 = null;
        }
        this.s0 = null;
    }

    public final void N2(R r2, String str, Uri uri) throws Throwable {
        String str2;
        byte[] bArrL2;
        String strV2;
        if (r2 == null || (str2 = r2.f3390a) == null || str2.length() == 0) {
            return;
        }
        String str3 = "cover.jpg";
        if (uri == null) {
            bArrL2 = null;
            strV2 = "image/jpeg";
        } else {
            if (!S1(2)) {
                Toast.makeText(this, "未授予媒体读取权限，请重新选择", 0).show();
                return;
            }
            if (u2(uri) > 1048576) {
                Toast.makeText(this, "封面不能超过1MB", 0).show();
                return;
            }
            String strK2 = k2(uri);
            if (strK2 != null && strK2.length() != 0) {
                str3 = strK2;
            }
            strV2 = v2(uri, str3);
            bArrL2 = l2(uri, 1048576L);
            if (bArrL2 == null || bArrL2.length == 0) {
                Toast.makeText(this, "封面读取失败或超过1MB", 0).show();
                return;
            }
        }
        byte[] bArr = bArrL2;
        String str4 = str3;
        String str5 = strV2;
        Toast.makeText(this, "正在保存修改...", 0).show();
        g0.d.W("/music/plaza/update?item_id=" + Z2(r2.f3390a) + "&name=" + Z2(str), new byte[0], "meta.txt", "text/plain", bArr, str4, str5, this.f3336W, new y(str, r2));
    }

    public final List P1() {
        String string = getSharedPreferences("music_plaza_local", 0).getString("recent_play", "");
        ArrayList arrayList = new ArrayList();
        if (string != null && string.length() != 0) {
            String[] strArrSplit = string.split("\\n");
            for (int i2 = 0; i2 < strArrSplit.length; i2++) {
                String str = strArrSplit[i2];
                String strTrim = str == null ? "" : str.trim();
                if (strTrim.length() != 0) {
                    arrayList.add(strTrim);
                    if (arrayList.size() >= 30) {
                        break;
                    }
                }
            }
        }
        return arrayList;
    }

    public final void P2(R r2) {
        String str;
        if (r2 == null || (str = r2.f3390a) == null || str.length() == 0) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("item_id", r2.f3390a);
            g0.d.S(r2.f3403n ? "/music/plaza/unlike" : "/music/plaza/like", jSONObject, this.f3336W, new F(r2));
        } catch (Exception unused) {
            Toast.makeText(this, "操作失败", 0).show();
        }
    }

    public final void Q1(Intent intent) {
        if (intent != null && "com.im.oldchat.action.music.CACHE_RESULT".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("cache_url");
            if (stringExtra == null) {
                stringExtra = "";
            }
            synchronized (this.L0) {
                this.L0.remove(stringExtra);
            }
            boolean booleanExtra = intent.getBooleanExtra("cache_ok", false);
            long longExtra = intent.getLongExtra("cache_size", 0L);
            String stringExtra2 = intent.getStringExtra("cache_error");
            if (booleanExtra) {
                Toast.makeText(this, "已缓存到本地（" + O1(longExtra) + "）", 0).show();
                return;
            }
            if (stringExtra2 == null || stringExtra2.length() <= 0) {
                Toast.makeText(this, "缓存失败", 0).show();
                return;
            }
            Toast.makeText(this, "缓存失败：" + stringExtra2, 0).show();
        }
    }

    public final void Q2() {
        this.f3350k0 = 4302;
        if (L1(2)) {
            f2();
        }
    }

    public final void R1(Intent intent) {
        if (intent != null && "com.im.oldchat.action.music.STATE_CHANGED".equals(intent.getAction())) {
            this.t0 = z2(intent.getStringExtra("song_name"));
            this.u0 = z2(intent.getStringExtra("song_url"));
            this.v0 = z2(intent.getStringExtra("cover_url"));
            this.w0 = z2(intent.getStringExtra("owner_uid"));
            this.x0 = z2(intent.getStringExtra("owner_name"));
            this.y0 = z2(intent.getStringExtra("owner_title"));
            this.z0 = z2(intent.getStringExtra("owner_avatar"));
            this.A0 = z2(intent.getStringExtra("item_id"));
            this.B0 = Math.max(0, intent.getIntExtra("item_likes", this.B0));
            this.C0 = Math.max(0, intent.getIntExtra("item_comments", this.C0));
            this.D0 = intent.getBooleanExtra("item_liked", this.D0);
            this.E0 = intent.getBooleanExtra("is_playing", false) || intent.getBooleanExtra("is_preparing", false);
            V2();
        }
    }

    public final void S2() {
        if (L1(1)) {
            h2();
        }
    }

    public final boolean T1(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return str.contains("duration") || str.contains("length") || str.contains("seconds") || str.contains("second") || str.contains("time");
    }

    public final void T2() {
        String str;
        if (this.f3318E == null) {
            return;
        }
        if (this.f3338Y.isEmpty()) {
            this.f3318E.setVisibility(8);
            return;
        }
        R r2 = (R) this.f3338Y.get(0);
        if (r2 == null) {
            this.f3318E.setVisibility(8);
            return;
        }
        this.f3318E.setVisibility(0);
        if (this.f3320G != null) {
            String str2 = r2.f3391b;
            this.f3320G.setText((str2 == null || str2.length() == 0) ? "精选歌曲" : r2.f3391b);
        }
        if (this.f3321H != null) {
            String str3 = r2.f3397h;
            if (str3 == null || str3.length() == 0) {
                str3 = r2.f3396g;
            }
            if (str3 == null || str3.length() == 0) {
                str = "来自音乐广场";
            } else {
                str = "推荐上传者 · " + str3;
            }
            this.f3321H.setText(str);
        }
        if (this.f3319F != null) {
            String str4 = r2.f3393d;
            if (str4 != null && str4.length() > 0) {
                AbstractC0455v.e(this.f3319F, r2.f3393d);
                return;
            }
            String str5 = r2.f3399j;
            if (str5 == null || str5.length() <= 0) {
                this.f3319F.setImageResource(f0.e.f4999T);
            } else {
                AbstractC0455v.e(this.f3319F, r2.f3399j);
            }
        }
    }

    public final String U1(List list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < list.size(); i2++) {
            String str = (String) list.get(i2);
            if (str != null && str.length() != 0) {
                if (sb.length() > 0) {
                    sb.append('\n');
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public final void U2() {
        r2();
        q2();
        p2();
    }

    public final void V2() {
        if (this.f3357y == null) {
            return;
        }
        if (this.u0.length() == 0) {
            this.f3357y.setText("正在播放");
            AbstractC0285k0.K(this.f3357y, 0.85f);
        } else {
            AbstractC0285k0.K(this.f3357y, 1.0f);
            this.f3357y.setText(this.E0 ? "播放中" : "继续播放");
        }
    }

    public final void W1() {
        String str = this.f3336W;
        if (str == null || str.length() == 0) {
            return;
        }
        g0.d.R("/music/plaza/ranking?limit=10", this.f3336W, new C0205p());
    }

    public final int X1(long j2, String str) {
        if (j2 <= 0) {
            return 0;
        }
        String lowerCase = str == null ? "" : str.toLowerCase(Locale.US);
        if (lowerCase.contains("ms") || lowerCase.contains("milli")) {
            if (j2 > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            return (int) j2;
        }
        if (lowerCase.contains("sec") || lowerCase.contains("second")) {
            long j3 = j2 * 1000;
            if (j3 > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            return (int) j3;
        }
        if (j2 <= 10000) {
            j2 *= 1000;
        }
        if (j2 > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) j2;
    }

    public final void X2(String str, int i2) {
        if (str == null || str.length() == 0) {
            return;
        }
        for (int i3 = 0; i3 < this.f3338Y.size(); i3++) {
            R r2 = (R) this.f3338Y.get(i3);
            if (r2 != null && str.equals(r2.f3390a)) {
                r2.f3402m = i2;
            }
        }
        for (int i4 = 0; i4 < this.f3340a0.size(); i4++) {
            R r3 = (R) this.f3340a0.get(i4);
            if (r3 != null && str.equals(r3.f3390a)) {
                r3.f3402m = i2;
            }
        }
        Q q2 = this.f3341b0;
        if (q2 != null) {
            q2.notifyDataSetChanged();
        }
        U2();
    }

    public final void Y1(R r2) {
        String str;
        if (r2 == null || (str = r2.f3390a) == null || str.length() == 0) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) MusicCommentsActivity.class);
        intent.putExtra("item_id", r2.f3390a);
        String str2 = r2.f3396g;
        if (str2 == null) {
            str2 = "";
        }
        intent.putExtra("owner_uid", str2);
        startActivity(intent);
    }

    public final void Y2() throws Throwable {
        String str;
        byte[] bArrL2;
        String strV2;
        if (this.f3346g0 == null || (str = this.f3348i0) == null || str.length() == 0) {
            return;
        }
        Uri uri = this.f3346g0;
        Uri uri2 = this.f3347h0;
        String str2 = this.f3348i0;
        String strK2 = k2(uri);
        if (strK2 == null || strK2.length() == 0) {
            strK2 = "song.mp3";
        }
        String str3 = strK2;
        String strX2 = x2(uri, str3);
        if (u2(uri) > 20971520) {
            Toast.makeText(this, "歌曲不能超过20MB", 0).show();
            return;
        }
        if (!S1(1)) {
            Toast.makeText(this, "未授予媒体读取权限，请先在系统设置中开启后重试", 0).show();
            return;
        }
        if (!D1(uri)) {
            Toast.makeText(this, "歌曲文件不可读取，请重新选择本地文件", 0).show();
            return;
        }
        String str4 = "cover.jpg";
        if (uri2 == null) {
            bArrL2 = null;
            strV2 = "image/jpeg";
        } else {
            if (u2(uri2) > 1048576) {
                Toast.makeText(this, "封面不能超过1MB", 0).show();
                return;
            }
            String strK22 = k2(uri2);
            if (strK22 != null && strK22.length() != 0) {
                str4 = strK22;
            }
            strV2 = v2(uri2, str4);
            bArrL2 = l2(uri2, 1048576L);
            if (bArrL2 == null || bArrL2.length == 0) {
                Toast.makeText(this, "封面读取失败或超过1MB", 0).show();
                return;
            }
        }
        byte[] bArr = bArrL2;
        String str5 = str4;
        String str6 = strV2;
        Toast.makeText(this, "正在上传音乐...", 0).show();
        g0.d.V("/music/plaza/upload?name=" + Z2(str2), new z(uri), str3, strX2, bArr, str5, str6, this.f3336W, null, new A());
    }

    public final void a2() {
        if (this.u0.length() == 0) {
            t2();
            Toast.makeText(this, "当前没有正在播放的歌曲", 0).show();
            return;
        }
        Intent intent = new Intent(this, (Class<?>) MusicPlayerActivity.class);
        intent.putExtra("song_name", this.t0);
        intent.putExtra("song_url", this.u0);
        intent.putExtra("cover_url", this.v0);
        intent.putExtra("owner_uid", this.w0);
        intent.putExtra("owner_name", this.x0);
        intent.putExtra("owner_title", this.y0);
        intent.putExtra("owner_avatar", this.z0);
        intent.putExtra("item_id", this.A0);
        intent.putExtra("item_likes", this.B0);
        intent.putExtra("item_comments", this.C0);
        intent.putExtra("item_liked", this.D0);
        startActivity(intent);
    }

    public final int b2(JSONObject jSONObject) {
        if (jSONObject == null) {
            return 0;
        }
        String[] strArr = {"duration_ms", "durationMs", "duration", "duration_sec", "durationSec", "seconds", "length"};
        for (int i2 = 0; i2 < 7; i2++) {
            String str = strArr[i2];
            int iX1 = X1(c2(jSONObject.opt(str)), str);
            if (iX1 > 0) {
                return iX1;
            }
        }
        String[] strArr2 = {"duration_text", "duration", "length"};
        for (int i3 = 0; i3 < 3; i3++) {
            int iD2 = d2(jSONObject.optString(strArr2[i3], ""));
            if (iD2 > 0) {
                return iD2;
            }
        }
        return M1(jSONObject, 0);
    }

    public final long c2(Object obj) {
        if (obj == null || obj == JSONObject.NULL) {
            return -1L;
        }
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        if (!(obj instanceof String)) {
            return -1L;
        }
        String strTrim = ((String) obj).trim();
        if (strTrim.length() == 0 || strTrim.contains(":")) {
            return -1L;
        }
        try {
            return strTrim.contains(".") ? Math.round(Double.parseDouble(strTrim)) : Long.parseLong(strTrim);
        } catch (Exception unused) {
            return -1L;
        }
    }

    public final R e2(String str) {
        List listK2 = K2(str);
        C0200k c0200k = null;
        if (listK2.size() < 4) {
            return null;
        }
        R r2 = new R(c0200k);
        r2.f3390a = (String) listK2.get(0);
        r2.f3391b = (String) listK2.get(1);
        r2.f3397h = (String) listK2.get(2);
        r2.f3392c = (String) listK2.get(3);
        r2.f3393d = listK2.size() > 4 ? (String) listK2.get(4) : "";
        return r2;
    }

    public final void f2() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.addFlags(1);
        startActivityForResult(Intent.createChooser(intent, "选择封面图片"), 4302);
    }

    public final void h2() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("audio/*");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.addFlags(1);
        startActivityForResult(Intent.createChooser(intent, "选择歌曲文件"), 4301);
    }

    public final void i2(R r2) {
        String str;
        if (r2 == null || (str = r2.f3392c) == null || str.length() == 0) {
            Toast.makeText(this, "歌曲链接不可用", 0).show();
            return;
        }
        A2(r2);
        s2(r2);
        String str2 = r2.f3391b;
        if (str2 == null) {
            str2 = "";
        }
        this.t0 = str2;
        String str3 = r2.f3392c;
        if (str3 == null) {
            str3 = "";
        }
        this.u0 = str3;
        String str4 = r2.f3393d;
        if (str4 == null) {
            str4 = "";
        }
        this.v0 = str4;
        String str5 = r2.f3396g;
        if (str5 == null) {
            str5 = "";
        }
        this.w0 = str5;
        String str6 = r2.f3397h;
        if (str6 == null) {
            str6 = "";
        }
        this.x0 = str6;
        String str7 = r2.f3398i;
        if (str7 == null) {
            str7 = "";
        }
        this.y0 = str7;
        String str8 = r2.f3399j;
        if (str8 == null) {
            str8 = "";
        }
        this.z0 = str8;
        String str9 = r2.f3390a;
        this.A0 = str9 != null ? str9 : "";
        this.B0 = Math.max(0, r2.f3400k);
        this.C0 = Math.max(0, r2.f3401l);
        this.D0 = r2.f3403n;
        this.E0 = true;
        V2();
        Intent intent = new Intent(this, (Class<?>) MusicPlayerActivity.class);
        intent.putExtra("song_name", this.t0);
        intent.putExtra("song_url", this.u0);
        intent.putExtra("cover_url", this.v0);
        intent.putExtra("owner_uid", this.w0);
        intent.putExtra("owner_name", this.x0);
        intent.putExtra("owner_title", this.y0);
        intent.putExtra("owner_avatar", this.z0);
        intent.putExtra("item_id", this.A0);
        intent.putExtra("item_likes", this.B0);
        intent.putExtra("item_comments", this.C0);
        intent.putExtra("item_liked", this.D0);
        startActivity(intent);
    }

    public final int j2(String str) {
        if (str != null && str.length() != 0) {
            MediaMetadataRetriever mediaMetadataRetriever = null;
            try {
                MediaMetadataRetriever mediaMetadataRetriever2 = new MediaMetadataRetriever();
                try {
                    if (Build.VERSION.SDK_INT >= 14) {
                        mediaMetadataRetriever2.setDataSource(str, (Map<String, String>) new HashMap());
                    } else {
                        mediaMetadataRetriever2.setDataSource(str);
                    }
                    String strExtractMetadata = mediaMetadataRetriever2.extractMetadata(9);
                    int iX1 = X1(c2(strExtractMetadata), "duration_ms");
                    if (iX1 > 0) {
                        try {
                            mediaMetadataRetriever2.release();
                        } catch (Throwable unused) {
                        }
                        return iX1;
                    }
                    int iD2 = d2(strExtractMetadata);
                    try {
                        mediaMetadataRetriever2.release();
                    } catch (Throwable unused2) {
                    }
                    return iD2;
                } catch (Throwable unused3) {
                    mediaMetadataRetriever = mediaMetadataRetriever2;
                    if (mediaMetadataRetriever != null) {
                        try {
                            mediaMetadataRetriever.release();
                        } catch (Throwable unused4) {
                        }
                    }
                    return 0;
                }
            } catch (Throwable unused5) {
            }
        }
        return 0;
    }

    public final void m2() {
        this.f3339Z.clear();
        if (this.f3338Y.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(this.f3338Y);
        Collections.shuffle(arrayList);
        int iMin = Math.min(arrayList.size(), 12);
        for (int i2 = 0; i2 < iMin; i2++) {
            this.f3339Z.add((R) arrayList.get(i2));
        }
    }

    public final void o2() {
        TextView textView = this.f3352m0;
        if (textView != null) {
            textView.setText("歌曲文件：" + y2(this.f3346g0, "未选择（≤20MB）"));
        }
        TextView textView2 = this.f3353n0;
        if (textView2 != null) {
            textView2.setText("封面文件：" + y2(this.f3347h0, "未选择（可选，≤1MB）"));
        }
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        Uri data;
        super.onActivityResult(i2, i3, intent);
        if (i3 != -1 || intent == null || (data = intent.getData()) == null) {
            return;
        }
        if (i2 == 4301) {
            this.f3346g0 = data;
            o2();
            Toast.makeText(this, "已选择歌曲: " + y2(data, "song"), 0).show();
            return;
        }
        if (i2 == 4302) {
            this.f3347h0 = data;
            o2();
            Toast.makeText(this, "已选择封面: " + y2(data, "cover"), 0).show();
            return;
        }
        if (i2 == 4304) {
            this.p0 = data;
            n2();
            Toast.makeText(this, "已选择新封面: " + y2(data, "cover"), 0).show();
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5114H);
        SharedPreferences sharedPreferences = getSharedPreferences("auth", 0);
        this.f3336W = sharedPreferences.getString("access_token", "");
        this.f3337X = sharedPreferences.getString("my_uid", "");
        this.f3355w = (EditText) a0(f0.f.I4);
        this.f3356x = (TextView) a0(f0.f.d2);
        this.f3357y = (TextView) a0(f0.f.Z1);
        this.f3358z = (TextView) a0(f0.f.S1);
        this.f3314A = (TextView) a0(f0.f.e2);
        this.f3315B = (TextView) a0(f0.f.U1);
        this.f3316C = (TextView) a0(f0.f.V1);
        this.f3317D = (TextView) a0(f0.f.W1);
        this.f3318E = a0(f0.f.y6);
        this.f3319F = (ImageView) a0(f0.f.O5);
        this.f3320G = (TextView) a0(f0.f.hb);
        this.f3321H = (TextView) a0(f0.f.gb);
        this.f3322I = (TextView) a0(f0.f.T1);
        this.f3323J = (TextView) a0(f0.f.fb);
        this.f3324K = (TextView) a0(f0.f.pb);
        this.f3325L = (LinearLayout) a0(f0.f.A6);
        this.f3326M = (LinearLayout) a0(f0.f.z6);
        this.f3327N = (LinearLayout) a0(f0.f.x6);
        this.f3328O = (TextView) a0(f0.f.nb);
        this.f3329P = (TextView) a0(f0.f.mb);
        this.f3330Q = (TextView) a0(f0.f.db);
        this.f3331R = (TextView) a0(f0.f.a2);
        this.f3332S = (TextView) a0(f0.f.lb);
        this.f3333T = (TextView) a0(f0.f.c2);
        this.f3334U = (TextView) a0(f0.f.Y1);
        this.f3335V = (ListView) a0(f0.f.V6);
        View viewA0 = a0(f0.f.R1);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new G());
        }
        Q q2 = new Q(this, null);
        this.f3341b0 = q2;
        this.f3335V.setAdapter((ListAdapter) q2);
        this.f3335V.setEmptyView(this.f3323J);
        this.f3335V.setOnItemClickListener(new K());
        this.f3335V.setOnItemLongClickListener(new L());
        TextView textView = this.f3356x;
        if (textView != null) {
            textView.setOnClickListener(new M());
        }
        TextView textView2 = this.f3314A;
        if (textView2 != null) {
            textView2.setOnClickListener(new N());
        }
        TextView textView3 = this.f3357y;
        if (textView3 != null) {
            textView3.setOnClickListener(new O());
        }
        TextView textView4 = this.f3358z;
        if (textView4 != null) {
            textView4.setOnClickListener(new P());
        }
        TextView textView5 = this.f3331R;
        if (textView5 != null) {
            textView5.setOnClickListener(new ViewOnClickListenerC0190a());
        }
        TextView textView6 = this.f3315B;
        if (textView6 != null) {
            textView6.setOnClickListener(new ViewOnClickListenerC0191b());
        }
        TextView textView7 = this.f3316C;
        if (textView7 != null) {
            textView7.setOnClickListener(new ViewOnClickListenerC0192c());
        }
        TextView textView8 = this.f3317D;
        if (textView8 != null) {
            textView8.setOnClickListener(new ViewOnClickListenerC0193d());
        }
        EditText editText = this.f3355w;
        if (editText != null) {
            editText.setOnEditorActionListener(new C0194e());
        }
        TextView textView9 = this.f3333T;
        if (textView9 != null) {
            textView9.setOnClickListener(new ViewOnClickListenerC0195f());
        }
        TextView textView10 = this.f3334U;
        if (textView10 != null) {
            textView10.setOnClickListener(new ViewOnClickListenerC0196g());
        }
        TextView textView11 = this.f3322I;
        if (textView11 != null) {
            textView11.setOnClickListener(new ViewOnClickListenerC0197h());
        }
        z1();
        V2();
        V1();
    }

    @Override // o.d, c.AbstractActivityC0167u, android.app.Activity
    public void onDestroy() {
        Runnable runnable = this.H0;
        if (runnable != null) {
            this.G0.removeCallbacks(runnable);
        }
        M2();
        super.onDestroy();
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onPause() {
        super.onPause();
        M2();
    }

    @Override // f0.a, c.AbstractActivityC0167u, android.app.Activity, c.AbstractC0121a.b
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        if (i2 != 4303) {
            return;
        }
        int i3 = this.f3349j0;
        this.f3349j0 = 0;
        if (iArr == null || iArr.length <= 0 || iArr[0] != 0) {
            Toast.makeText(this, "未授予媒体读取权限，无法选择文件", 0).show();
            return;
        }
        if (i3 != 2) {
            if (i3 == 1) {
                h2();
            }
        } else if (this.f3350k0 == 4304) {
            g2();
        } else {
            f2();
        }
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.F0) {
            this.F0 = false;
            U2();
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, android.app.Activity
    public void onStart() {
        super.onStart();
        d.q qVarC = d.q.c(this);
        qVarC.d(this.M0, new IntentFilter("com.im.oldchat.action.music.CACHE_RESULT"));
        qVarC.d(this.N0, new IntentFilter("com.im.oldchat.action.music.STATE_CHANGED"));
        t2();
    }

    @Override // o.d, c.AbstractActivityC0167u, android.app.Activity
    public void onStop() {
        d.q qVarC = d.q.c(this);
        try {
            qVarC.f(this.M0);
        } catch (Exception unused) {
        }
        try {
            qVarC.f(this.N0);
        } catch (Exception unused2) {
        }
        super.onStop();
    }

    public final void p2() {
        LinearLayout linearLayout = this.f3327N;
        if (linearLayout == null) {
            return;
        }
        linearLayout.removeAllViews();
        if (this.f3338Y.isEmpty()) {
            TextView textView = this.f3330Q;
            if (textView != null) {
                textView.setVisibility(0);
                return;
            }
            return;
        }
        TextView textView2 = this.f3330Q;
        if (textView2 != null) {
            textView2.setVisibility(8);
        }
        int iMin = Math.min(this.f3338Y.size(), 12);
        for (int i2 = 0; i2 < iMin; i2++) {
            View viewB1 = B1((R) this.f3338Y.get(i2), false);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(I1(CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256), -2);
            layoutParams.rightMargin = I1(8);
            this.f3327N.addView(viewB1, layoutParams);
        }
    }

    public final void q2() {
        LinearLayout linearLayout = this.f3326M;
        if (linearLayout == null) {
            return;
        }
        linearLayout.removeAllViews();
        if (this.f3340a0.isEmpty()) {
            TextView textView = this.f3329P;
            if (textView != null) {
                textView.setVisibility(0);
                return;
            }
            return;
        }
        TextView textView2 = this.f3329P;
        if (textView2 != null) {
            textView2.setVisibility(8);
        }
        int iMin = Math.min(this.f3340a0.size(), 10);
        for (int i2 = 0; i2 < iMin; i2++) {
            View viewB1 = B1((R) this.f3340a0.get(i2), false);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(I1(CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256), -2);
            layoutParams.rightMargin = I1(8);
            this.f3326M.addView(viewB1, layoutParams);
        }
    }

    public final void r2() {
        LinearLayout linearLayout = this.f3325L;
        if (linearLayout == null) {
            return;
        }
        linearLayout.removeAllViews();
        List list = this.f3339Z.isEmpty() ? this.f3338Y : this.f3339Z;
        int iMin = Math.min(list.size(), 4);
        if (iMin <= 0) {
            TextView textView = this.f3328O;
            if (textView != null) {
                textView.setVisibility(0);
                return;
            }
            return;
        }
        TextView textView2 = this.f3328O;
        if (textView2 != null) {
            textView2.setVisibility(8);
        }
        int i2 = 0;
        while (i2 < iMin) {
            LinearLayout linearLayout2 = new LinearLayout(this);
            linearLayout2.setOrientation(0);
            linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            View viewB1 = B1((R) list.get(i2), true);
            K1((R) list.get(i2));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2, 1.0f);
            layoutParams.rightMargin = I1(5);
            linearLayout2.addView(viewB1, layoutParams);
            int i3 = i2 + 1;
            if (i3 < iMin) {
                View viewB12 = B1((R) list.get(i3), true);
                K1((R) list.get(i3));
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
                layoutParams2.leftMargin = I1(5);
                linearLayout2.addView(viewB12, layoutParams2);
                i2 += 2;
            } else {
                linearLayout2.addView(new View(this), new LinearLayout.LayoutParams(0, 1, 1.0f));
                i2 = i3;
            }
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) linearLayout2.getLayoutParams();
            layoutParams3.bottomMargin = I1(8);
            this.f3325L.addView(linearLayout2, layoutParams3);
        }
    }

    public final void s2(R r2) {
        String str;
        if (r2 == null || (str = r2.f3390a) == null || str.length() == 0) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("item_id", r2.f3390a);
            g0.d.S("/music/plaza/play", jSONObject, this.f3336W, new I(r2));
        } catch (Exception unused) {
        }
    }

    public final void x1(String str, String str2, String str3) {
        if (str == null || str.length() == 0) {
            return;
        }
        boolean z2 = y1(this.f3339Z, str, str2, str3) || y1(this.f3338Y, str, str2, str3);
        if (y1(this.f3340a0, str, str2, str3) || z2) {
            this.f3341b0.notifyDataSetChanged();
            T2();
            B2();
        }
    }

    public final String x2(Uri uri, String str) {
        String type;
        try {
            type = getContentResolver().getType(uri);
        } catch (Exception unused) {
            type = null;
        }
        if (!TextUtils.isEmpty(type)) {
            return type;
        }
        String lowerCase = str == null ? "" : str.toLowerCase(Locale.US);
        return lowerCase.endsWith(".mp3") ? "audio/mpeg" : (lowerCase.endsWith(".m4a") || lowerCase.endsWith(".mp4")) ? "audio/mp4" : lowerCase.endsWith(".aac") ? "audio/aac" : lowerCase.endsWith(".amr") ? "audio/amr" : (lowerCase.endsWith(".3gp") || lowerCase.endsWith(".3gpp")) ? "audio/3gpp" : lowerCase.endsWith(".flac") ? "audio/flac" : (lowerCase.endsWith(".ogg") || lowerCase.endsWith(".oga")) ? "audio/ogg" : (lowerCase.endsWith(".wav") || lowerCase.endsWith(".wave")) ? "audio/wav" : "audio/mpeg";
    }

    public final boolean y1(List list, String str, String str2, String str3) {
        String str4;
        if (list == null || list.isEmpty()) {
            return false;
        }
        boolean z2 = false;
        for (int i2 = 0; i2 < list.size(); i2++) {
            R r2 = (R) list.get(i2);
            if (r2 != null && (str4 = r2.f3390a) != null && str4.equals(str)) {
                if (str2 != null && str2.trim().length() > 0 && !str2.equals(r2.f3391b)) {
                    r2.f3391b = str2;
                    z2 = true;
                }
                if (str3 != null && str3.trim().length() > 0 && !str3.equals(r2.f3393d)) {
                    r2.f3393d = str3;
                    z2 = true;
                }
            }
        }
        return z2;
    }

    public final String y2(Uri uri, String str) throws Throwable {
        String strK2 = k2(uri);
        return (strK2 == null || strK2.length() == 0) ? str : strK2;
    }

    public final void z1() {
        TextView textView = this.f3315B;
        if (textView != null) {
            textView.setBackgroundResource(this.f3345f0 ? f0.e.f5030q : f0.e.f5031r);
            this.f3315B.setTextColor(getResources().getColor(this.f3345f0 ? f0.d.f4974k : f0.d.f4973j));
            this.f3315B.setTextSize(this.f3345f0 ? 12.0f : 12.5f);
        }
        TextView textView2 = this.f3316C;
        if (textView2 != null) {
            textView2.setBackgroundResource(this.f3345f0 ? f0.e.f5031r : f0.e.f5030q);
            this.f3316C.setTextColor(getResources().getColor(this.f3345f0 ? f0.d.f4973j : f0.d.f4974k));
            this.f3316C.setTextSize(this.f3345f0 ? 12.5f : 12.0f);
        }
        TextView textView3 = this.f3317D;
        if (textView3 != null) {
            textView3.setText("管理上传");
        }
        TextView textView4 = this.f3323J;
        if (textView4 != null) {
            textView4.setText(this.f3345f0 ? "你还没有上传歌曲，点右上角上传吧" : "暂无歌曲，去右上角上传你的第一首吧");
        }
        TextView textView5 = this.f3314A;
        if (textView5 != null) {
            textView5.setText("我的");
        }
    }

    public final String z2(String str) {
        return str == null ? "" : str;
    }
}

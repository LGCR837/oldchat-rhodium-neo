package com.im.oldchat.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import g0.d;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import k.AbstractC0285k0;
import o.c;
import o0.AbstractC0455v;
import org.json.JSONArray;
import org.json.JSONObject;
import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: loaded from: classes.dex */
public class PublicCourtCaseDetailActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public TextView f3796A;
    public EditText A0;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public TextView f3797B;
    public TextView B0;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public TextView f3798C;
    public o.c C0;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public TextView f3799D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public TextView f3800E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public TextView f3801F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public TextView f3802G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public TextView f3803H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public TextView f3804I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public TextView f3805J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public TextView f3806K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public TextView f3807L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public ImageView f3808M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public ImageView f3809N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public LinearLayout f3810O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public LinearLayout f3811P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public LinearLayout f3812Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public TextView f3813R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public TextView f3814S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public TextView f3815T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public TextView f3816U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public TextView f3817V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public TextView f3818W;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public TextView f3819X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public TextView f3820Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public TextView f3821Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public String f3822a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public String f3823b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public String f3824c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public String f3825d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public String f3826e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public String f3827f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public String f3828g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public String f3829h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public String f3830i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public String f3831j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public String f3832k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public String f3833l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public String f3834m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public String f3835n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public String f3836o0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public TextView f3837w;
    public EditText w0;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public TextView f3838x;
    public EditText x0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f3839y;
    public EditText y0;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public TextView f3840z;
    public TextView z0;
    public int p0 = 1;
    public int q0 = 5;
    public int r0 = 0;
    public final List s0 = new ArrayList();
    public final List t0 = new ArrayList();
    public final List u0 = new ArrayList();
    public final List v0 = new ArrayList();
    public boolean D0 = false;

    public static class A {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f3841a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f3842b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f3843c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f3844d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f3845e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public String f3846f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public long f3847g;

        public A() {
        }

        public /* synthetic */ A(k kVar) {
            this();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.PublicCourtCaseDetailActivity$a, reason: case insensitive filesystem */
    public class C0209a implements d.i {

        /* JADX INFO: renamed from: com.im.oldchat.ui.PublicCourtCaseDetailActivity$a$a, reason: collision with other inner class name */
        public class RunnableC0028a implements Runnable {
            public RunnableC0028a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(PublicCourtCaseDetailActivity.this, "加载案件详情失败", 0).show();
            }
        }

        public C0209a() {
        }

        @Override // g0.d.i
        public void b(String str) {
            PublicCourtCaseDetailActivity.this.H1(str);
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            PublicCourtCaseDetailActivity.this.runOnUiThread(new RunnableC0028a());
        }
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3850a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f3851b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f3852c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ long f3853d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ String f3854e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ int f3855f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ long f3856g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ String f3857h;

        public b(int i2, int i3, int i4, long j2, String str, int i5, long j3, String str2) {
            this.f3850a = i2;
            this.f3851b = i3;
            this.f3852c = i4;
            this.f3853d = j2;
            this.f3854e = str;
            this.f3855f = i5;
            this.f3856g = j3;
            this.f3857h = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            String str;
            String str2;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
            PublicCourtCaseDetailActivity.this.f3838x.setText(PublicCourtCaseDetailActivity.this.f3824c0);
            TextView textView = PublicCourtCaseDetailActivity.this.f3839y;
            PublicCourtCaseDetailActivity publicCourtCaseDetailActivity = PublicCourtCaseDetailActivity.this;
            textView.setText(publicCourtCaseDetailActivity.C1(publicCourtCaseDetailActivity.f3825d0));
            PublicCourtCaseDetailActivity.this.f3796A.setText("投票进度：" + this.f3850a + "/10（满 10 票自动锁定）");
            PublicCourtCaseDetailActivity.this.f3797B.setText("票数：封禁 " + this.f3851b + "  ·  不封禁 " + this.f3852c);
            TextView textView2 = PublicCourtCaseDetailActivity.this.f3840z;
            if (this.f3853d > 0) {
                str = "开庭时间：" + simpleDateFormat.format(new Date(this.f3853d * 1000));
            } else {
                str = "开庭时间：-";
            }
            textView2.setText(str);
            TextView textView3 = PublicCourtCaseDetailActivity.this.f3806K;
            PublicCourtCaseDetailActivity publicCourtCaseDetailActivity2 = PublicCourtCaseDetailActivity.this;
            textView3.setText(publicCourtCaseDetailActivity2.z1(publicCourtCaseDetailActivity2.f3827f0, PublicCourtCaseDetailActivity.this.f3826e0));
            TextView textView4 = PublicCourtCaseDetailActivity.this.f3807L;
            PublicCourtCaseDetailActivity publicCourtCaseDetailActivity3 = PublicCourtCaseDetailActivity.this;
            textView4.setText(publicCourtCaseDetailActivity3.z1(publicCourtCaseDetailActivity3.f3830i0, PublicCourtCaseDetailActivity.this.f3829h0));
            AbstractC0455v.h(PublicCourtCaseDetailActivity.this.f3808M, PublicCourtCaseDetailActivity.this.f3828g0);
            AbstractC0455v.h(PublicCourtCaseDetailActivity.this.f3809N, PublicCourtCaseDetailActivity.this.f3831j0);
            boolean z2 = true;
            if (PublicCourtCaseDetailActivity.this.r0 > 1) {
                PublicCourtCaseDetailActivity.this.f3800E.setText("该案件已叠加 " + PublicCourtCaseDetailActivity.this.r0 + " 条举报，详见页面底部“叠加举报记录”分页列表。");
                TextView textView5 = PublicCourtCaseDetailActivity.this.f3801F;
                StringBuilder sb = new StringBuilder();
                sb.append("当前摘要：");
                PublicCourtCaseDetailActivity publicCourtCaseDetailActivity4 = PublicCourtCaseDetailActivity.this;
                sb.append(publicCourtCaseDetailActivity4.X1(publicCourtCaseDetailActivity4.f3833l0));
                textView5.setText(sb.toString());
            } else {
                PublicCourtCaseDetailActivity.this.f3800E.setText(PublicCourtCaseDetailActivity.P1(PublicCourtCaseDetailActivity.this.f3832k0, "(举报方未填写)"));
                TextView textView6 = PublicCourtCaseDetailActivity.this.f3801F;
                PublicCourtCaseDetailActivity publicCourtCaseDetailActivity5 = PublicCourtCaseDetailActivity.this;
                textView6.setText(publicCourtCaseDetailActivity5.X1(publicCourtCaseDetailActivity5.f3833l0));
            }
            PublicCourtCaseDetailActivity.this.f3802G.setText(PublicCourtCaseDetailActivity.P1(PublicCourtCaseDetailActivity.this.f3834m0, "(被举报方未提交观点)"));
            TextView textView7 = PublicCourtCaseDetailActivity.this.f3803H;
            PublicCourtCaseDetailActivity publicCourtCaseDetailActivity6 = PublicCourtCaseDetailActivity.this;
            textView7.setText(publicCourtCaseDetailActivity6.X1(publicCourtCaseDetailActivity6.f3835n0));
            if ("ban".equals(this.f3854e)) {
                str2 = "封禁 " + this.f3855f + " 小时";
            } else {
                str2 = "keep".equals(this.f3854e) ? "不封禁" : "尚未二审裁决";
            }
            if (this.f3856g > 0) {
                str2 = str2 + "（结案于 " + simpleDateFormat.format(new Date(this.f3856g * 1000)) + "）";
            }
            PublicCourtCaseDetailActivity.this.f3804I.setText(str2);
            PublicCourtCaseDetailActivity.this.f3805J.setText(PublicCourtCaseDetailActivity.P1(this.f3857h, "(管理员尚未填写备注)"));
            PublicCourtCaseDetailActivity.this.v1();
            PublicCourtCaseDetailActivity.this.s1();
            PublicCourtCaseDetailActivity.this.u1();
            boolean zEquals = "open".equals(PublicCourtCaseDetailActivity.this.f3825d0);
            boolean z3 = PublicCourtCaseDetailActivity.this.f3823b0 != null && PublicCourtCaseDetailActivity.this.f3823b0.length() > 0 && PublicCourtCaseDetailActivity.this.f3823b0.equalsIgnoreCase(PublicCourtCaseDetailActivity.this.f3826e0);
            boolean z4 = PublicCourtCaseDetailActivity.this.f3823b0 != null && PublicCourtCaseDetailActivity.this.f3823b0.length() > 0 && PublicCourtCaseDetailActivity.this.f3823b0.equalsIgnoreCase(PublicCourtCaseDetailActivity.this.f3829h0);
            PublicCourtCaseDetailActivity publicCourtCaseDetailActivity7 = PublicCourtCaseDetailActivity.this;
            if (!z3 || (!"open".equals(publicCourtCaseDetailActivity7.f3825d0) && !"pending_review".equals(PublicCourtCaseDetailActivity.this.f3825d0))) {
                z2 = false;
            }
            publicCourtCaseDetailActivity7.D0 = z2;
            PublicCourtCaseDetailActivity.this.r1(zEquals, z3, z4, this.f3850a);
        }
    }

    public class c implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f3859a;

        public c(String str) {
            this.f3859a = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ImagePreviewActivity.f0(PublicCourtCaseDetailActivity.this, this.f3859a);
        }
    }

    public class d implements d.i {

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (PublicCourtCaseDetailActivity.this.w0 != null) {
                    PublicCourtCaseDetailActivity.this.w0.setText("");
                }
                Toast.makeText(PublicCourtCaseDetailActivity.this, "讨论已发送", 0).show();
                PublicCourtCaseDetailActivity.this.E1();
            }
        }

        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(PublicCourtCaseDetailActivity.this, "发送讨论失败", 0).show();
            }
        }

        public d() {
        }

        @Override // g0.d.i
        public void b(String str) {
            PublicCourtCaseDetailActivity.this.runOnUiThread(new a());
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            PublicCourtCaseDetailActivity.this.runOnUiThread(new b());
        }
    }

    public class e implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f3864a;

        public e(EditText editText) {
            this.f3864a = editText;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            PublicCourtCaseDetailActivity.this.W1(this.f3864a.getText() == null ? "" : this.f3864a.getText().toString().trim());
        }
    }

    public class f implements d.i {

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(PublicCourtCaseDetailActivity.this, "已撤销举报", 0).show();
                PublicCourtCaseDetailActivity.this.E1();
            }
        }

        public class b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f3868a;

            public b(int i2) {
                this.f3868a = i2;
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(PublicCourtCaseDetailActivity.this, "撤销失败", 0).show();
                int i2 = this.f3868a;
                if (i2 == 409 || i2 == 403) {
                    PublicCourtCaseDetailActivity.this.E1();
                }
            }
        }

        public f() {
        }

        @Override // g0.d.i
        public void b(String str) {
            PublicCourtCaseDetailActivity.this.runOnUiThread(new a());
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            PublicCourtCaseDetailActivity.this.runOnUiThread(new b(i2));
        }
    }

    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PublicCourtCaseDetailActivity.this.I1();
        }
    }

    public class h implements DialogInterface.OnDismissListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ o.c f3871a;

        public h(o.c cVar) {
            this.f3871a = cVar;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (PublicCourtCaseDetailActivity.this.C0 == this.f3871a) {
                PublicCourtCaseDetailActivity.this.C0 = null;
                PublicCourtCaseDetailActivity.this.A0 = null;
                PublicCourtCaseDetailActivity.this.B0 = null;
            }
            PublicCourtCaseDetailActivity.this.v0.clear();
        }
    }

    public class i implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f3873a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ EditText f3874b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f3875c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ o.c f3876d;

        public i(EditText editText, EditText editText2, String str, o.c cVar) {
            this.f3873a = editText;
            this.f3874b = editText2;
            this.f3875c = str;
            this.f3876d = cVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EditText editText = this.f3873a;
            String strTrim = "";
            String strTrim2 = (editText == null || editText.getText() == null) ? "" : this.f3873a.getText().toString().trim();
            EditText editText2 = this.f3874b;
            if (editText2 != null && editText2.getText() != null) {
                strTrim = this.f3874b.getText().toString().trim();
            }
            PublicCourtCaseDetailActivity publicCourtCaseDetailActivity = PublicCourtCaseDetailActivity.this;
            String strG1 = publicCourtCaseDetailActivity.G1(strTrim, publicCourtCaseDetailActivity.v0);
            if (TextUtils.isEmpty(strG1)) {
                Toast.makeText(PublicCourtCaseDetailActivity.this, "请先填写证据或上传截图", 0).show();
            } else {
                PublicCourtCaseDetailActivity.this.V1(this.f3875c, strTrim2, strG1);
                this.f3876d.dismiss();
            }
        }
    }

    public class j implements d.i {

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(PublicCourtCaseDetailActivity.this, "投票成功", 0).show();
                PublicCourtCaseDetailActivity.this.E1();
            }
        }

        public class b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f3880a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ String f3881b;

            public b(int i2, String str) {
                this.f3880a = i2;
                this.f3881b = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                PublicCourtCaseDetailActivity publicCourtCaseDetailActivity = PublicCourtCaseDetailActivity.this;
                Toast.makeText(publicCourtCaseDetailActivity, publicCourtCaseDetailActivity.w1(this.f3880a, this.f3881b), 0).show();
                if (this.f3880a == 409) {
                    PublicCourtCaseDetailActivity.this.E1();
                }
            }
        }

        public j() {
        }

        @Override // g0.d.i
        public void b(String str) {
            PublicCourtCaseDetailActivity.this.runOnUiThread(new a());
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            PublicCourtCaseDetailActivity.this.runOnUiThread(new b(i2, str));
        }
    }

    public class k implements View.OnClickListener {
        public k() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PublicCourtCaseDetailActivity.this.finish();
        }
    }

    public class l implements View.OnClickListener {
        public l() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PublicCourtCaseDetailActivity.this.I1();
        }
    }

    public class m implements DialogInterface.OnClickListener {
        public m() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            String strTrim = PublicCourtCaseDetailActivity.this.x0.getText() == null ? "" : PublicCourtCaseDetailActivity.this.x0.getText().toString().trim();
            String strTrim2 = PublicCourtCaseDetailActivity.this.y0.getText() != null ? PublicCourtCaseDetailActivity.this.y0.getText().toString().trim() : "";
            PublicCourtCaseDetailActivity publicCourtCaseDetailActivity = PublicCourtCaseDetailActivity.this;
            PublicCourtCaseDetailActivity.this.U1(strTrim, publicCourtCaseDetailActivity.G1(strTrim2, publicCourtCaseDetailActivity.v0));
        }
    }

    public class n implements DialogInterface.OnDismissListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ o.c f3886a;

        public n(o.c cVar) {
            this.f3886a = cVar;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (PublicCourtCaseDetailActivity.this.C0 == this.f3886a) {
                PublicCourtCaseDetailActivity.this.C0 = null;
                PublicCourtCaseDetailActivity.this.A0 = null;
                PublicCourtCaseDetailActivity.this.B0 = null;
            }
            PublicCourtCaseDetailActivity.this.v0.clear();
        }
    }

    public class o implements d.i {

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(PublicCourtCaseDetailActivity.this, "上传失败", 0).show();
            }
        }

        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                PublicCourtCaseDetailActivity.this.L1();
                String strTrim = (PublicCourtCaseDetailActivity.this.A0 == null || PublicCourtCaseDetailActivity.this.A0.getText() == null) ? "" : PublicCourtCaseDetailActivity.this.A0.getText().toString().trim();
                PublicCourtCaseDetailActivity publicCourtCaseDetailActivity = PublicCourtCaseDetailActivity.this;
                String strG1 = publicCourtCaseDetailActivity.G1(strTrim, publicCourtCaseDetailActivity.v0);
                if (PublicCourtCaseDetailActivity.this.A0 != null) {
                    PublicCourtCaseDetailActivity.this.A0.setText(strG1);
                    PublicCourtCaseDetailActivity.this.A0.setSelection(strG1.length());
                }
                Toast.makeText(PublicCourtCaseDetailActivity.this, "截图证据已上传", 0).show();
            }
        }

        public class c implements Runnable {
            public c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(PublicCourtCaseDetailActivity.this, "上传失败", 0).show();
                PublicCourtCaseDetailActivity.this.L1();
            }
        }

        public class d implements Runnable {
            public d() {
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(PublicCourtCaseDetailActivity.this, "上传失败", 0).show();
                PublicCourtCaseDetailActivity.this.L1();
            }
        }

        public o() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                String strOptString = new JSONObject(str).optString("url", "");
                if (TextUtils.isEmpty(strOptString)) {
                    PublicCourtCaseDetailActivity.this.runOnUiThread(new a());
                } else {
                    PublicCourtCaseDetailActivity.this.v0.add(strOptString);
                    PublicCourtCaseDetailActivity.this.runOnUiThread(new b());
                }
            } catch (Exception unused) {
                PublicCourtCaseDetailActivity.this.runOnUiThread(new c());
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            PublicCourtCaseDetailActivity.this.runOnUiThread(new d());
        }
    }

    public class p implements d.i {

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(PublicCourtCaseDetailActivity.this, "陈述已保存", 0).show();
                PublicCourtCaseDetailActivity.this.E1();
            }
        }

        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(PublicCourtCaseDetailActivity.this, "保存失败", 0).show();
            }
        }

        public p() {
        }

        @Override // g0.d.i
        public void b(String str) {
            PublicCourtCaseDetailActivity.this.runOnUiThread(new a());
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            PublicCourtCaseDetailActivity.this.runOnUiThread(new b());
        }
    }

    public class q implements View.OnClickListener {
        public q() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PublicCourtCaseDetailActivity.this.E1();
        }
    }

    public class r implements View.OnClickListener {
        public r() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PublicCourtCaseDetailActivity.this.R1("ban");
        }
    }

    public class s implements View.OnClickListener {
        public s() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PublicCourtCaseDetailActivity.this.R1("keep");
        }
    }

    public class t implements View.OnClickListener {
        public t() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PublicCourtCaseDetailActivity.this.Q1();
        }
    }

    public class u implements View.OnClickListener {
        public u() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PublicCourtCaseDetailActivity.this.S1();
        }
    }

    public class v implements View.OnClickListener {
        public v() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PublicCourtCaseDetailActivity.this.T1();
        }
    }

    public class w implements View.OnClickListener {
        public w() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PublicCourtCaseDetailActivity.this.p0 > 1) {
                PublicCourtCaseDetailActivity.this.p0--;
                PublicCourtCaseDetailActivity.this.E1();
            }
        }
    }

    public class x implements View.OnClickListener {
        public x() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int i2 = PublicCourtCaseDetailActivity.this.q0 > 0 ? ((PublicCourtCaseDetailActivity.this.r0 + PublicCourtCaseDetailActivity.this.q0) - 1) / PublicCourtCaseDetailActivity.this.q0 : 1;
            if (i2 < 1) {
                i2 = 1;
            }
            if (PublicCourtCaseDetailActivity.this.p0 < i2) {
                PublicCourtCaseDetailActivity.this.p0++;
                PublicCourtCaseDetailActivity.this.E1();
            }
        }
    }

    public static class y {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f3904a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f3905b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f3906c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f3907d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f3908e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f3909f;

        public y() {
        }

        public /* synthetic */ y(k kVar) {
            this();
        }
    }

    public static class z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f3910a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f3911b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f3912c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f3913d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f3914e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f3915f;

        public z() {
        }

        public /* synthetic */ z(k kVar) {
            this();
        }
    }

    private int A1(int i2) {
        return (int) ((getResources().getDisplayMetrics().density * i2) + 0.5f);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String J1(android.net.Uri r10) throws java.lang.Throwable {
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
        throw new UnsupportedOperationException("Method not decompiled: com.im.oldchat.ui.PublicCourtCaseDetailActivity.J1(android.net.Uri):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0042, code lost:
    
        r12 = r1.toByteArray();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0046, code lost:
    
        r11.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private byte[] K1(android.net.Uri r11, long r12) throws java.lang.Throwable {
        /*
            r10 = this;
            r0 = 0
            if (r11 != 0) goto L4
            return r0
        L4:
            android.content.ContentResolver r1 = r10.getContentResolver()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L56
            java.io.InputStream r11 = r1.openInputStream(r11)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L56
            if (r11 != 0) goto L14
            if (r11 == 0) goto L13
            r11.close()     // Catch: java.lang.Exception -> L13
        L13:
            return r0
        L14:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L50
            r1.<init>()     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L50
            r2 = 8192(0x2000, float:1.148E-41)
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L40
            r3 = 0
            r5 = r3
        L20:
            int r7 = r11.read(r2)     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L40
            r8 = -1
            if (r7 == r8) goto L42
            long r8 = (long) r7
            long r5 = r5 + r8
            int r8 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r8 <= 0) goto L38
            int r8 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r8 <= 0) goto L38
            r11.close()     // Catch: java.lang.Exception -> L34
        L34:
            r1.close()     // Catch: java.lang.Exception -> L37
        L37:
            return r0
        L38:
            r8 = 0
            r1.write(r2, r8, r7)     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L40
            goto L20
        L3d:
            r12 = move-exception
        L3e:
            r0 = r11
            goto L5a
        L40:
            goto L67
        L42:
            byte[] r12 = r1.toByteArray()     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L40
            r11.close()     // Catch: java.lang.Exception -> L49
        L49:
            r1.close()     // Catch: java.lang.Exception -> L4c
        L4c:
            return r12
        L4d:
            r12 = move-exception
            r1 = r0
            goto L3e
        L50:
            r1 = r0
            goto L67
        L53:
            r12 = move-exception
            r1 = r0
            goto L5a
        L56:
            r11 = r0
            r1 = r11
            goto L67
        L5a:
            if (r0 == 0) goto L61
            r0.close()     // Catch: java.lang.Exception -> L60
            goto L61
        L60:
        L61:
            if (r1 == 0) goto L66
            r1.close()     // Catch: java.lang.Exception -> L66
        L66:
            throw r12
        L67:
            if (r11 == 0) goto L6e
            r11.close()     // Catch: java.lang.Exception -> L6d
            goto L6e
        L6d:
        L6e:
            if (r1 == 0) goto L73
            r1.close()     // Catch: java.lang.Exception -> L73
        L73:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.im.oldchat.ui.PublicCourtCaseDetailActivity.K1(android.net.Uri, long):byte[]");
    }

    private String M1(Uri uri, String str) {
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
        return lowerCase.endsWith(".gif") ? "image/gif" : lowerCase.endsWith(".png") ? "image/png" : lowerCase.endsWith(".webp") ? "image/webp" : "image/jpeg";
    }

    private String O1(Uri uri, String str) throws Throwable {
        if (uri == null) {
            return str;
        }
        String strJ1 = J1(uri);
        return TextUtils.isEmpty(strJ1) ? str : strJ1;
    }

    public static String P1(String str, String str2) {
        return (str == null || str.length() == 0) ? str2 : str;
    }

    private boolean y1(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        Locale locale = Locale.US;
        String lowerCase = str.toLowerCase(locale);
        String lowerCase2 = str2.toLowerCase(locale);
        if (lowerCase.contains(lowerCase2)) {
            return true;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (lowerCase2.equalsIgnoreCase(jSONObject.optString("error", ""))) {
                return true;
            }
            String strOptString = jSONObject.optString("message", "");
            if (strOptString != null) {
                return strOptString.toLowerCase(locale).contains(lowerCase2);
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public final A B1() {
        String str = this.f3823b0;
        if (str != null && str.length() != 0) {
            for (A a2 : this.s0) {
                if (a2 != null && this.f3823b0.equalsIgnoreCase(a2.f3841a)) {
                    return a2;
                }
            }
        }
        return null;
    }

    public final String C1(String str) {
        return "pending_review".equals(str) ? "待二审" : "closed".equals(str) ? "已结案" : "withdrawn".equals(str) ? "已撤销" : "投票中";
    }

    public final boolean D1() {
        String str = this.f3836o0;
        return str != null && str.length() > 0;
    }

    public final void E1() {
        String str = this.f3822a0;
        if (str == null || str.length() == 0) {
            Toast.makeText(this, "请先登录", 0).show();
            return;
        }
        String str2 = this.f3824c0;
        if (str2 == null || str2.length() == 0) {
            Toast.makeText(this, "案件ID无效", 0).show();
            return;
        }
        g0.d.R("/public-court/cases/" + this.f3824c0 + "?report_page=" + this.p0 + "&report_page_size=" + this.q0, this.f3822a0, new C0209a());
    }

    public final boolean F1(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String lowerCase = str.toLowerCase(Locale.US);
        return (lowerCase.startsWith("http://") || lowerCase.startsWith("https://") || lowerCase.startsWith("/")) && (lowerCase.contains(".jpg") || lowerCase.contains(".jpeg") || lowerCase.contains(".png") || lowerCase.contains(".gif") || lowerCase.contains(".webp") || lowerCase.contains("/media/"));
    }

    public final String G1(String str, List list) {
        String strTrim = str == null ? "" : str.trim();
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(strTrim)) {
            String[] strArrSplit = strTrim.split("\\n");
            int length = strArrSplit.length;
            for (int i2 = 0; i2 < length; i2++) {
                String str2 = strArrSplit[i2];
                String strTrim2 = str2 == null ? "" : str2.trim();
                if (strTrim2.length() > 0) {
                    arrayList.add(strTrim2);
                }
            }
        }
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String str3 = (String) it.next();
                if (!TextUtils.isEmpty(str3) && !arrayList.contains(str3)) {
                    arrayList.add(str3);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            if (i3 > 0) {
                sb.append('\n');
            }
            sb.append((String) arrayList.get(i3));
        }
        return sb.toString();
    }

    public final void H1(String str) {
        String str2;
        String str3;
        String str4;
        String str5;
        int i2;
        String str6;
        String str7;
        String str8;
        int i3;
        JSONArray jSONArray;
        int i4;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13 = "reporter_avatar";
        String str14 = "reporter_name";
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("case");
            if (jSONObjectOptJSONObject == null) {
                return;
            }
            this.f3826e0 = jSONObjectOptJSONObject.optString("reporter_uid", "");
            this.f3827f0 = jSONObjectOptJSONObject.optString("reporter_name", "");
            this.f3828g0 = jSONObjectOptJSONObject.optString("reporter_avatar", "");
            this.f3829h0 = jSONObjectOptJSONObject.optString("defendant_uid", "");
            this.f3830i0 = jSONObjectOptJSONObject.optString("defendant_name", "");
            this.f3831j0 = jSONObjectOptJSONObject.optString("defendant_avatar", "");
            this.f3832k0 = jSONObjectOptJSONObject.optString("report_reason", "");
            this.f3833l0 = jSONObjectOptJSONObject.optString("report_evidence", "");
            this.f3834m0 = jSONObjectOptJSONObject.optString("defense_reason", "");
            this.f3835n0 = jSONObjectOptJSONObject.optString("defense_evidence", "");
            this.f3836o0 = jSONObjectOptJSONObject.optString("my_vote", "");
            this.f3825d0 = jSONObjectOptJSONObject.optString("status", "open");
            String strOptString = jSONObjectOptJSONObject.optString("verdict", "");
            int iOptInt = jSONObjectOptJSONObject.optInt("ban_hours", 0);
            String strOptString2 = jSONObjectOptJSONObject.optString("admin_note", "");
            int iOptInt2 = jSONObjectOptJSONObject.optInt("ban_vote_count", 0);
            int iOptInt3 = jSONObjectOptJSONObject.optInt("keep_vote_count", 0);
            int iOptInt4 = jSONObjectOptJSONObject.optInt("total_vote_count", 0);
            if (iOptInt4 <= 0) {
                iOptInt4 = iOptInt2 + iOptInt3;
            }
            int i5 = iOptInt2;
            long jOptLong = jSONObjectOptJSONObject.optLong("created_at", 0L);
            long jOptLong2 = jSONObjectOptJSONObject.optLong("closed_at", 0L);
            this.s0.clear();
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("statements");
            String str15 = "user_avatar";
            String str16 = "user_name";
            String str17 = "user_uid";
            if (jSONArrayOptJSONArray != null) {
                i2 = iOptInt3;
                int i6 = 0;
                while (i6 < jSONArrayOptJSONArray.length()) {
                    JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray.optJSONObject(i6);
                    if (jSONObjectOptJSONObject2 == null) {
                        str9 = str13;
                        str10 = str14;
                        jSONArray = jSONArrayOptJSONArray;
                        str11 = str15;
                        str12 = str16;
                        i4 = i5;
                    } else {
                        jSONArray = jSONArrayOptJSONArray;
                        i4 = i5;
                        A a2 = new A(null);
                        a2.f3841a = jSONObjectOptJSONObject2.optString("user_uid", "");
                        a2.f3842b = jSONObjectOptJSONObject2.optString(str16, "");
                        a2.f3843c = jSONObjectOptJSONObject2.optString(str15, "");
                        a2.f3844d = jSONObjectOptJSONObject2.optString("role", "");
                        a2.f3845e = jSONObjectOptJSONObject2.optString("reason", "");
                        a2.f3846f = jSONObjectOptJSONObject2.optString("evidence", "");
                        str9 = str13;
                        str10 = str14;
                        str11 = str15;
                        str12 = str16;
                        a2.f3847g = jSONObjectOptJSONObject2.optLong("updated_at", jSONObjectOptJSONObject2.optLong("created_at", 0L));
                        this.s0.add(a2);
                    }
                    i6++;
                    jSONArrayOptJSONArray = jSONArray;
                    i5 = i4;
                    str13 = str9;
                    str14 = str10;
                    str15 = str11;
                    str16 = str12;
                }
                str2 = str13;
                str3 = str14;
                str4 = str15;
                str5 = str16;
            } else {
                str2 = "reporter_avatar";
                str3 = "reporter_name";
                str4 = "user_avatar";
                str5 = "user_name";
                i2 = iOptInt3;
            }
            int i7 = i5;
            this.t0.clear();
            JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("discussions");
            if (jSONArrayOptJSONArray2 != null) {
                int i8 = 0;
                while (i8 < jSONArrayOptJSONArray2.length()) {
                    JSONObject jSONObjectOptJSONObject3 = jSONArrayOptJSONArray2.optJSONObject(i8);
                    if (jSONObjectOptJSONObject3 == null) {
                        str8 = str17;
                        i3 = iOptInt4;
                        str7 = str4;
                    } else {
                        y yVar = new y(null);
                        yVar.f3904a = jSONObjectOptJSONObject3.optString("id", "");
                        yVar.f3905b = jSONObjectOptJSONObject3.optString(str17, "");
                        String str18 = str5;
                        yVar.f3906c = jSONObjectOptJSONObject3.optString(str18, "");
                        str7 = str4;
                        yVar.f3907d = jSONObjectOptJSONObject3.optString(str7, "");
                        yVar.f3908e = jSONObjectOptJSONObject3.optString("body", "");
                        str8 = str17;
                        str5 = str18;
                        i3 = iOptInt4;
                        yVar.f3909f = jSONObjectOptJSONObject3.optLong("created_at", 0L);
                        this.t0.add(yVar);
                    }
                    i8++;
                    str4 = str7;
                    iOptInt4 = i3;
                    str17 = str8;
                }
            }
            int i9 = iOptInt4;
            this.u0.clear();
            this.r0 = jSONObject.optInt("merged_report_total", 0);
            int iOptInt5 = jSONObject.optInt("merged_report_page", this.p0);
            if (iOptInt5 < 1) {
                iOptInt5 = 1;
            }
            int iOptInt6 = jSONObject.optInt("merged_report_page_size", this.q0);
            if (iOptInt6 < 1) {
                iOptInt6 = 5;
            }
            this.p0 = iOptInt5;
            this.q0 = iOptInt6;
            JSONArray jSONArrayOptJSONArray3 = jSONObject.optJSONArray("merged_reports");
            if (jSONArrayOptJSONArray3 != null) {
                int i10 = 0;
                while (i10 < jSONArrayOptJSONArray3.length()) {
                    JSONObject jSONObjectOptJSONObject4 = jSONArrayOptJSONArray3.optJSONObject(i10);
                    if (jSONObjectOptJSONObject4 == null) {
                        str6 = str2;
                    } else {
                        z zVar = new z(null);
                        zVar.f3910a = jSONObjectOptJSONObject4.optString("report_id", "");
                        zVar.f3911b = jSONObjectOptJSONObject4.optString("reporter_uid", "");
                        String str19 = str3;
                        zVar.f3912c = jSONObjectOptJSONObject4.optString(str19, "");
                        str6 = str2;
                        zVar.f3913d = jSONObjectOptJSONObject4.optString(str6, "");
                        zVar.f3914e = jSONObjectOptJSONObject4.optString("reason", "");
                        str3 = str19;
                        zVar.f3915f = jSONObjectOptJSONObject4.optLong("created_at", 0L);
                        this.u0.add(zVar);
                    }
                    i10++;
                    str2 = str6;
                }
            }
            if (this.r0 <= 0) {
                this.r0 = this.u0.size();
            }
            runOnUiThread(new b(i9, i7, i2, jOptLong, strOptString, iOptInt, jOptLong2, strOptString2));
        } catch (Exception unused) {
        }
    }

    public final void I1() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        intent.addCategory("android.intent.category.OPENABLE");
        startActivityForResult(Intent.createChooser(intent, "选择截图证据"), 9101);
    }

    public final void L1() {
        if (this.B0 == null) {
            return;
        }
        if (this.v0.isEmpty()) {
            this.B0.setText("未上传截图");
            return;
        }
        this.B0.setText("已上传截图：" + this.v0.size() + " 张");
    }

    public final String N1(String str) {
        return "reporter".equals(str) ? "举报方" : "defendant".equals(str) ? "被举报方" : "jury".equals(str) ? "陪审团" : "参与者";
    }

    public final void Q1() {
        if (!"open".equals(this.f3825d0)) {
            Toast.makeText(this, "案件已锁定，不能修改陈述", 0).show();
            return;
        }
        String str = this.f3823b0;
        boolean z2 = str != null && str.length() > 0 && this.f3823b0.equalsIgnoreCase(this.f3826e0);
        String str2 = this.f3823b0;
        boolean z3 = str2 != null && str2.length() > 0 && this.f3823b0.equalsIgnoreCase(this.f3829h0);
        if (!z2 && !z3 && !D1()) {
            Toast.makeText(this, "请先参与投票后再提交观点", 0).show();
            return;
        }
        View viewInflate = LayoutInflater.from(this).inflate(f0.g.f5152j0, (ViewGroup) null);
        this.x0 = (EditText) viewInflate.findViewById(f0.f.U4);
        this.y0 = (EditText) viewInflate.findViewById(f0.f.T4);
        TextView textView = (TextView) viewInflate.findViewById(f0.f.J2);
        this.z0 = (TextView) viewInflate.findViewById(f0.f.M9);
        A aB1 = B1();
        if (aB1 != null) {
            EditText editText = this.x0;
            String str3 = aB1.f3845e;
            if (str3 == null) {
                str3 = "";
            }
            editText.setText(str3);
            EditText editText2 = this.y0;
            String str4 = aB1.f3846f;
            editText2.setText(str4 != null ? str4 : "");
        } else if (z2) {
            EditText editText3 = this.x0;
            String str5 = this.f3832k0;
            if (str5 == null) {
                str5 = "";
            }
            editText3.setText(str5);
            EditText editText4 = this.y0;
            String str6 = this.f3833l0;
            editText4.setText(str6 != null ? str6 : "");
        } else if (z3) {
            EditText editText5 = this.x0;
            String str7 = this.f3834m0;
            if (str7 == null) {
                str7 = "";
            }
            editText5.setText(str7);
            EditText editText6 = this.y0;
            String str8 = this.f3835n0;
            editText6.setText(str8 != null ? str8 : "");
        }
        this.v0.clear();
        this.A0 = this.y0;
        this.B0 = this.z0;
        L1();
        textView.setOnClickListener(new l());
        o.c cVarA = new c.a(this).t("提交观点与证据").u(viewInflate).q("保存", new m()).k("取消", null).a();
        this.C0 = cVarA;
        cVarA.setOnDismissListener(new n(cVarA));
        cVarA.show();
    }

    public final void R1(String str) {
        if (!"open".equals(this.f3825d0)) {
            Toast.makeText(this, "案件已锁定，不能继续投票", 0).show();
            return;
        }
        this.v0.clear();
        View viewInflate = LayoutInflater.from(this).inflate(f0.g.f5152j0, (ViewGroup) null);
        EditText editText = (EditText) viewInflate.findViewById(f0.f.U4);
        EditText editText2 = (EditText) viewInflate.findViewById(f0.f.T4);
        TextView textView = (TextView) viewInflate.findViewById(f0.f.Td);
        TextView textView2 = (TextView) viewInflate.findViewById(f0.f.J2);
        TextView textView3 = (TextView) viewInflate.findViewById(f0.f.M9);
        if (textView != null) {
            textView.setText("投票必须提交证据，可上传截图");
        }
        if (editText != null) {
            editText.setHint("投票理由（选填）");
            editText.setMinLines(2);
        }
        if (editText2 != null) {
            editText2.setHint("证据（必填，可写描述或上传截图链接）");
        }
        this.A0 = editText2;
        this.B0 = textView3;
        L1();
        if (textView2 != null) {
            textView2.setOnClickListener(new g());
        }
        c.a aVar = new c.a(this);
        StringBuilder sb = new StringBuilder();
        sb.append("投票");
        sb.append("ban".equals(str) ? "封禁" : "不封禁");
        o.c cVarA = aVar.t(sb.toString()).u(viewInflate).q("提交", null).k("取消", null).a();
        this.C0 = cVarA;
        cVarA.setOnDismissListener(new h(cVarA));
        cVarA.show();
        if (cVarA.d(-1) != null) {
            cVarA.d(-1).setOnClickListener(new i(editText, editText2, str, cVarA));
        }
    }

    public final void S1() {
        if (!this.D0) {
            Toast.makeText(this, "仅举报发起者可撤销", 0).show();
            return;
        }
        EditText editText = new EditText(this);
        editText.setHint("可选：填写撤销原因");
        editText.setMinLines(2);
        editText.setMaxLines(4);
        new c.a(this).t("撤销举报").i("确认撤销该公开法庭案件？撤销后案件将结束。").u(editText).k("取消", null).q("确认撤销", new e(editText)).v();
    }

    public final void T1() {
        if ("closed".equals(this.f3825d0) || "withdrawn".equals(this.f3825d0)) {
            Toast.makeText(this, "案件已结束，不能继续讨论", 0).show();
            return;
        }
        EditText editText = this.w0;
        if (editText == null) {
            return;
        }
        String strTrim = editText.getText() == null ? "" : this.w0.getText().toString().trim();
        if (TextUtils.isEmpty(strTrim)) {
            Toast.makeText(this, "请输入讨论内容", 0).show();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("body", strTrim);
            g0.d.S("/public-court/cases/" + this.f3824c0 + "/discussion", jSONObject, this.f3822a0, new d());
        } catch (Exception unused) {
            Toast.makeText(this, "发送讨论失败", 0).show();
        }
    }

    public final void U1(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (str == null) {
                str = "";
            }
            jSONObject.put("reason", str);
            if (str2 == null) {
                str2 = "";
            }
            jSONObject.put("evidence", str2);
            g0.d.S("/public-court/cases/" + this.f3824c0 + "/statement", jSONObject, this.f3822a0, new p());
        } catch (Exception unused) {
            Toast.makeText(this, "保存失败", 0).show();
        }
    }

    public final void V1(String str, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("vote", str);
            if (str2 == null) {
                str2 = "";
            }
            jSONObject.put("reason", str2);
            if (str3 == null) {
                str3 = "";
            }
            jSONObject.put("evidence", str3);
            g0.d.S("/public-court/cases/" + this.f3824c0 + "/vote", jSONObject, this.f3822a0, new j());
        } catch (Exception unused) {
            Toast.makeText(this, "投票失败", 0).show();
        }
    }

    public final void W1(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (str == null) {
                str = "";
            }
            jSONObject.put("reason", str);
            g0.d.S("/public-court/cases/" + this.f3824c0 + "/withdraw", jSONObject, this.f3822a0, new f());
        } catch (Exception unused) {
            Toast.makeText(this, "撤销失败", 0).show();
        }
    }

    public final String X1(String str) {
        if (TextUtils.isEmpty(str)) {
            return "(未填写)";
        }
        String[] strArrSplit = str.split("\\n");
        ArrayList arrayList = new ArrayList();
        int length = strArrSplit.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            String str2 = strArrSplit[i3];
            String strTrim = str2 == null ? "" : str2.trim();
            if (strTrim.length() != 0) {
                if (F1(strTrim)) {
                    i2++;
                } else {
                    arrayList.add(strTrim);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        if (!arrayList.isEmpty()) {
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                if (i4 > 0) {
                    sb.append("；");
                }
                sb.append((String) arrayList.get(i4));
            }
        }
        if (i2 > 0) {
            if (sb.length() > 0) {
                sb.append("；");
            }
            sb.append("已上传截图");
            sb.append(i2);
            sb.append("张");
        }
        return sb.length() == 0 ? "(未填写)" : sb.toString();
    }

    public final void Y1(Uri uri) throws Throwable {
        if (uri == null) {
            return;
        }
        byte[] bArrK1 = K1(uri, 1048576L);
        if (bArrK1 == null || bArrK1.length == 0) {
            Toast.makeText(this, "截图读取失败或超过1MB", 0).show();
            return;
        }
        String strO1 = O1(uri, "court_evidence.jpg");
        String strM1 = M1(uri, strO1);
        TextView textView = this.B0;
        if (textView != null) {
            textView.setText("上传中...");
        }
        g0.d.T("/media", bArrK1, strO1, strM1, this.f3822a0, new o());
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) throws Throwable {
        super.onActivityResult(i2, i3, intent);
        if (i3 == -1 && intent != null && i2 == 9101) {
            Uri data = intent.getData();
            if (data == null) {
                Toast.makeText(this, "未选择图片", 0).show();
            } else {
                Y1(data);
            }
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5126T);
        String stringExtra = getIntent().getStringExtra("case_id");
        this.f3824c0 = stringExtra;
        if (stringExtra == null) {
            this.f3824c0 = "";
        }
        SharedPreferences sharedPreferences = getSharedPreferences("auth", 0);
        this.f3822a0 = sharedPreferences.getString("access_token", "");
        this.f3823b0 = sharedPreferences.getString("my_uid", "");
        View viewA0 = a0(f0.f.f5102v);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new k());
        }
        this.f3837w = (TextView) a0(f0.f.Yd);
        this.f3838x = (TextView) a0(f0.f.e9);
        this.f3839y = (TextView) a0(f0.f.Ud);
        this.f3840z = (TextView) a0(f0.f.pc);
        this.f3796A = (TextView) a0(f0.f.qc);
        this.f3797B = (TextView) a0(f0.f.ie);
        this.f3798C = (TextView) a0(f0.f.Sd);
        this.f3799D = (TextView) a0(f0.f.T8);
        this.f3800E = (TextView) a0(f0.f.ed);
        this.f3801F = (TextView) a0(f0.f.cd);
        this.f3802G = (TextView) a0(f0.f.x9);
        this.f3803H = (TextView) a0(f0.f.w9);
        this.f3804I = (TextView) a0(f0.f.V8);
        this.f3805J = (TextView) a0(f0.f.U8);
        this.f3806K = (TextView) a0(f0.f.fd);
        this.f3807L = (TextView) a0(f0.f.v9);
        this.f3808M = (ImageView) a0(f0.f.e6);
        this.f3809N = (ImageView) a0(f0.f.q5);
        this.f3810O = (LinearLayout) a0(f0.f.I6);
        this.f3811P = (LinearLayout) a0(f0.f.s6);
        this.f3812Q = (LinearLayout) a0(f0.f.w6);
        this.f3813R = (TextView) a0(f0.f.xa);
        this.f3814S = (TextView) a0(f0.f.s1);
        this.f3815T = (TextView) a0(f0.f.r1);
        this.f3816U = (TextView) a0(f0.f.a4);
        this.f3817V = (TextView) a0(f0.f.b4);
        this.f3818W = (TextView) a0(f0.f.S3);
        this.f3819X = (TextView) a0(f0.f.d3);
        this.f3820Y = (TextView) a0(f0.f.c4);
        this.w0 = (EditText) a0(f0.f.t4);
        this.f3821Z = (TextView) a0(f0.f.q0);
        TextView textView = this.f3820Y;
        if (textView != null) {
            textView.setVisibility(8);
        }
        this.f3837w.setText("案件详情");
        this.f3838x.setText(this.f3824c0);
        this.f3819X.setOnClickListener(new q());
        this.f3816U.setOnClickListener(new r());
        this.f3817V.setOnClickListener(new s());
        this.f3818W.setOnClickListener(new t());
        TextView textView2 = this.f3820Y;
        if (textView2 != null) {
            textView2.setOnClickListener(new u());
        }
        TextView textView3 = this.f3821Z;
        if (textView3 != null) {
            textView3.setOnClickListener(new v());
        }
        TextView textView4 = this.f3814S;
        if (textView4 != null) {
            textView4.setOnClickListener(new w());
        }
        TextView textView5 = this.f3815T;
        if (textView5 != null) {
            textView5.setOnClickListener(new x());
        }
        E1();
    }

    public final void q1(TextView textView, String str) {
        if (textView == null) {
            return;
        }
        if ("reporter".equals(str)) {
            textView.setBackgroundResource(f0.e.f5016f);
            textView.setTextColor(getResources().getColor(f0.d.f4973j));
        } else if ("defendant".equals(str)) {
            textView.setBackgroundResource(f0.e.f5012d);
            textView.setTextColor(getResources().getColor(f0.d.f4970g));
        } else if ("jury".equals(str)) {
            textView.setBackgroundResource(f0.e.f5014e);
            textView.setTextColor(getResources().getColor(f0.d.f4970g));
        } else {
            textView.setBackgroundResource(f0.e.f4995P);
            textView.setTextColor(getResources().getColor(f0.d.f4974k));
        }
    }

    public final void r1(boolean z2, boolean z3, boolean z4, int i2) {
        boolean zD1 = D1();
        boolean z5 = z2 && (z3 || z4 || zD1);
        if (z2) {
            this.f3816U.setEnabled(true);
            this.f3817V.setEnabled(true);
            this.f3816U.setText("投封禁");
            this.f3817V.setText("投不封");
        } else {
            this.f3816U.setEnabled(false);
            this.f3817V.setEnabled(false);
            if ("closed".equals(this.f3825d0)) {
                this.f3816U.setText("已结案");
                this.f3817V.setText("已结案");
            } else if ("withdrawn".equals(this.f3825d0)) {
                this.f3816U.setText("已撤销");
                this.f3817V.setText("已撤销");
            } else {
                this.f3816U.setText("已锁定");
                this.f3817V.setText("待二审");
            }
        }
        this.f3818W.setVisibility(0);
        if (z5) {
            this.f3818W.setEnabled(true);
            this.f3818W.setText("补充观点");
        } else {
            this.f3818W.setEnabled(false);
            if (z2) {
                this.f3818W.setText("先投票后可补充");
            } else {
                this.f3818W.setText("已锁定");
            }
        }
        TextView textView = this.f3820Y;
        if (textView != null) {
            if (this.D0) {
                textView.setVisibility(0);
                this.f3820Y.setEnabled(true);
            } else {
                textView.setVisibility(8);
                this.f3820Y.setEnabled(false);
            }
        }
        boolean z6 = ("closed".equals(this.f3825d0) || "withdrawn".equals(this.f3825d0)) ? false : true;
        TextView textView2 = this.f3821Z;
        if (textView2 != null) {
            textView2.setEnabled(z6);
            this.f3821Z.setText(z6 ? "发送" : "已关闭");
        }
        EditText editText = this.w0;
        if (editText != null) {
            editText.setEnabled(z6);
        }
        TextView textView3 = this.f3798C;
        if (textView3 != null) {
            if (z2) {
                if (i2 >= 8) {
                    textView3.setText("当前接近锁定票数，请尽快补充有效证据。\n锁定后将进入管理员二审。");
                } else {
                    textView3.setText("当前为公开投票阶段。\n达到 10 票后自动锁定并转入二审。");
                }
            } else if ("withdrawn".equals(this.f3825d0)) {
                this.f3798C.setText("本案已由发起者撤销举报，案件结束。");
            } else if ("closed".equals(this.f3825d0)) {
                this.f3798C.setText("本案已结案，可查看最终裁决与历史观点。");
            } else {
                this.f3798C.setText("本案已锁定，当前只可查看，等待管理员二审。");
            }
        }
        if (this.f3799D != null) {
            if ("withdrawn".equals(this.f3825d0)) {
                this.f3799D.setText("本案已撤销，仅可查看历史观点和讨论。");
                return;
            }
            if (!z2) {
                this.f3799D.setText("当前阶段不可再投票或补充观点。可点击刷新查看最新进度。");
                return;
            }
            if (z3) {
                this.f3799D.setText("你是举报方：可投票、补充观点，也可撤销举报。\n撤销后案件将结束。");
                return;
            }
            if (z4) {
                this.f3799D.setText("你是被举报方：可投票，也可持续补充观点和证据。");
            } else if (zD1) {
                this.f3799D.setText("你已参与投票：可继续补充观点和证据。\n也可在下方参与讨论。");
            } else {
                this.f3799D.setText("你尚未投票：先投票后才可补充观点。\n投票时需提交证据。你也可以先参与讨论。");
            }
        }
    }

    public final void s1() {
        LinearLayout linearLayout = this.f3811P;
        if (linearLayout == null) {
            return;
        }
        linearLayout.removeAllViews();
        if (this.t0.isEmpty()) {
            TextView textView = new TextView(this);
            textView.setText("暂无讨论，欢迎补充观点");
            textView.setTextColor(getResources().getColor(f0.d.f4974k));
            textView.setTextSize(13.0f);
            this.f3811P.addView(textView);
            return;
        }
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
        for (y yVar : this.t0) {
            View viewInflate = layoutInflaterFrom.inflate(f0.g.S0, (ViewGroup) this.f3811P, false);
            ImageView imageView = (ImageView) viewInflate.findViewById(f0.f.m5);
            TextView textView2 = (TextView) viewInflate.findViewById(f0.f.ub);
            TextView textView3 = (TextView) viewInflate.findViewById(f0.f.a9);
            TextView textView4 = (TextView) viewInflate.findViewById(f0.f.u9);
            textView2.setText(z1(yVar.f3906c, yVar.f3905b));
            textView3.setText(P1(yVar.f3908e, "(未填写)"));
            if (yVar.f3909f > 0) {
                textView4.setText(simpleDateFormat.format(new Date(yVar.f3909f * 1000)));
            } else {
                textView4.setText("-");
            }
            AbstractC0455v.h(imageView, yVar.f3907d);
            this.f3811P.addView(viewInflate);
        }
    }

    public final void t1(LinearLayout linearLayout, String str) {
        if (linearLayout == null) {
            return;
        }
        linearLayout.removeAllViews();
        List<String> listX1 = x1(str);
        if (listX1.isEmpty()) {
            linearLayout.setVisibility(8);
            return;
        }
        linearLayout.setVisibility(0);
        for (String str2 : listX1) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, A1(CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256));
            layoutParams.bottomMargin = A1(6);
            imageView.setLayoutParams(layoutParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setBackgroundColor(285212672);
            AbstractC0455v.e(imageView, str2);
            imageView.setOnClickListener(new c(str2));
            linearLayout.addView(imageView);
        }
    }

    public final void u1() {
        LinearLayout linearLayout = this.f3812Q;
        if (linearLayout == null) {
            return;
        }
        linearLayout.removeAllViews();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this);
        if (this.u0.isEmpty()) {
            TextView textView = new TextView(this);
            if (this.r0 > 0) {
                textView.setText("本页暂无叠加举报记录");
            } else {
                textView.setText("暂无叠加举报记录");
            }
            textView.setTextColor(getResources().getColor(f0.d.f4974k));
            textView.setTextSize(13.0f);
            this.f3812Q.addView(textView);
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
            for (z zVar : this.u0) {
                View viewInflate = layoutInflaterFrom.inflate(f0.g.U0, (ViewGroup) this.f3812Q, false);
                ImageView imageView = (ImageView) viewInflate.findViewById(f0.f.e6);
                TextView textView2 = (TextView) viewInflate.findViewById(f0.f.fd);
                TextView textView3 = (TextView) viewInflate.findViewById(f0.f.u9);
                TextView textView4 = (TextView) viewInflate.findViewById(f0.f.Ic);
                TextView textView5 = (TextView) viewInflate.findViewById(f0.f.dd);
                textView2.setText(z1(zVar.f3912c, zVar.f3911b));
                if (zVar.f3915f > 0) {
                    textView3.setText(simpleDateFormat.format(new Date(zVar.f3915f * 1000)));
                } else {
                    textView3.setText("-");
                }
                textView4.setText(P1(zVar.f3914e, "(未填写)"));
                textView5.setText("举报ID：" + P1(zVar.f3910a, "-"));
                AbstractC0455v.h(imageView, zVar.f3913d);
                this.f3812Q.addView(viewInflate);
            }
        }
        int i2 = this.q0;
        int i3 = i2 > 0 ? ((this.r0 + i2) - 1) / i2 : 1;
        if (i3 < 1) {
            i3 = 1;
        }
        if (this.p0 > i3) {
            this.p0 = i3;
        }
        if (this.p0 < 1) {
            this.p0 = 1;
        }
        TextView textView6 = this.f3813R;
        if (textView6 != null) {
            if (this.r0 <= 0) {
                textView6.setText("暂无叠加举报");
            } else {
                textView6.setText("第 " + this.p0 + "/" + i3 + " 页 · 共 " + this.r0 + " 条");
            }
        }
        TextView textView7 = this.f3814S;
        if (textView7 != null) {
            boolean z2 = this.p0 > 1;
            textView7.setEnabled(z2);
            AbstractC0285k0.K(this.f3814S, z2 ? 1.0f : 0.45f);
        }
        TextView textView8 = this.f3815T;
        if (textView8 != null) {
            boolean z3 = this.p0 < i3;
            textView8.setEnabled(z3);
            AbstractC0285k0.K(this.f3815T, z3 ? 1.0f : 0.45f);
        }
    }

    public final void v1() {
        LinearLayout linearLayout = this.f3810O;
        if (linearLayout == null) {
            return;
        }
        linearLayout.removeAllViews();
        if (this.s0.isEmpty()) {
            TextView textView = new TextView(this);
            textView.setText("暂无观点陈述");
            textView.setTextColor(getResources().getColor(f0.d.f4974k));
            textView.setTextSize(13.0f);
            this.f3810O.addView(textView);
            return;
        }
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this);
        for (A a2 : this.s0) {
            View viewInflate = layoutInflaterFrom.inflate(f0.g.W0, (ViewGroup) this.f3810O, false);
            ImageView imageView = (ImageView) viewInflate.findViewById(f0.f.m5);
            TextView textView2 = (TextView) viewInflate.findViewById(f0.f.ub);
            TextView textView3 = (TextView) viewInflate.findViewById(f0.f.xd);
            TextView textView4 = (TextView) viewInflate.findViewById(f0.f.Ic);
            TextView textView5 = (TextView) viewInflate.findViewById(f0.f.L9);
            TextView textView6 = (TextView) viewInflate.findViewById(f0.f.ce);
            LinearLayout linearLayout2 = (LinearLayout) viewInflate.findViewById(f0.f.v6);
            textView2.setText(z1(a2.f3842b, a2.f3841a));
            textView3.setText(N1(a2.f3844d));
            q1(textView3, a2.f3844d);
            textView4.setText("观点：" + P1(a2.f3845e, "(未填写)"));
            textView5.setText("证据说明：" + X1(a2.f3846f));
            if (a2.f3847g > 0) {
                textView6.setText("更新于 " + new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date(a2.f3847g * 1000)));
            } else {
                textView6.setText("");
            }
            AbstractC0455v.h(imageView, a2.f3843c);
            t1(linearLayout2, a2.f3846f);
            this.f3810O.addView(viewInflate);
        }
    }

    public final String w1(int i2, String str) {
        if (str == null) {
            str = "";
        }
        return (i2 == 400 && y1(str, "invalid_evidence")) ? "投票必须提交证据" : (i2 == 400 && y1(str, "invalid_vote")) ? "投票选项无效，请重试" : (i2 == 403 && y1(str, "reputation_too_low")) ? "信誉分需高于50才能投票" : (i2 == 403 && y1(str, "forbidden")) ? "你没有投票权限" : (i2 == 409 && y1(str, "case_closed")) ? "案件已锁定，不能继续投票" : (i2 == 404 && y1(str, "not_found")) ? "案件不存在或已删除" : "投票失败，请稍后重试";
    }

    public final List x1(String str) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        String[] strArrSplit = str.split("\\n");
        int length = strArrSplit.length;
        for (int i2 = 0; i2 < length; i2++) {
            String str2 = strArrSplit[i2];
            String strTrim = str2 == null ? "" : str2.trim();
            if (strTrim.length() != 0) {
                String lowerCase = strTrim.toLowerCase(Locale.US);
                boolean z2 = true;
                boolean z3 = lowerCase.startsWith("http://") || lowerCase.startsWith("https://") || lowerCase.startsWith("/");
                if (!lowerCase.contains(".jpg") && !lowerCase.contains(".jpeg") && !lowerCase.contains(".png") && !lowerCase.contains(".gif") && !lowerCase.contains(".webp") && !lowerCase.contains("/media/")) {
                    z2 = false;
                }
                if (z3 && z2) {
                    arrayList.add(strTrim);
                }
            }
        }
        return arrayList;
    }

    public final String z1(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return P1(str2, "匿名用户");
        }
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        return str + " (" + str2 + ")";
    }
}

package com.im.oldchat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import g0.d;
import k.AbstractC0285k0;
import o0.AbstractC0455v;
import org.json.JSONObject;
import org.spongycastle.i18n.MessageBundle;

/* JADX INFO: loaded from: classes.dex */
public class RedPacketOpenActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public TextView f3977A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public ProgressBar f3978B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public TextView f3979C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public TextView f3980D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public String f3981E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public String f3982F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public int f3983G = 0;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public boolean f3984H = false;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ImageView f3985w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public TextView f3986x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f3987y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public TextView f3988z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RedPacketOpenActivity.this.finish();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RedPacketOpenActivity.this.n0();
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RedPacketOpenActivity.this.q0();
        }
    }

    public class d implements d.i {
        public d() {
        }

        @Override // g0.d.i
        public void b(String str) {
            RedPacketOpenActivity.this.m0(str);
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(RedPacketOpenActivity.this, f0.j.Q2, 0).show();
        }
    }

    public class e implements d.i {
        public e() {
        }

        @Override // g0.d.i
        public void b(String str) {
            RedPacketOpenActivity.this.r0(false);
            RedPacketOpenActivity.this.o0(str);
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            RedPacketOpenActivity.this.r0(false);
            if (g0.e.v(i2, str)) {
                return;
            }
            if (str != null && str.contains("red_packet_already_claimed")) {
                Toast.makeText(RedPacketOpenActivity.this, f0.j.D2, 0).show();
                RedPacketOpenActivity.this.p0();
                return;
            }
            if (str != null && str.contains("red_packet_empty")) {
                RedPacketOpenActivity redPacketOpenActivity = RedPacketOpenActivity.this;
                o0.T.c(redPacketOpenActivity, redPacketOpenActivity.f3982F);
                Toast.makeText(RedPacketOpenActivity.this, f0.j.K2, 0).show();
                RedPacketOpenActivity.this.p0();
                return;
            }
            if (str == null || !str.contains("red_packet_no_permission")) {
                Toast.makeText(RedPacketOpenActivity.this, f0.j.Q2, 0).show();
            } else {
                Toast.makeText(RedPacketOpenActivity.this, f0.j.P2, 0).show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m0(String str) {
        boolean z2 = true;
        int i2 = 0;
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString(MessageBundle.TITLE_ENTRY, "");
            if (strOptString == null || strOptString.isEmpty()) {
                strOptString = getString(f0.j.W2);
            }
            TextView textView = this.f3986x;
            if (textView != null) {
                textView.setText(strOptString);
            }
            String strOptString2 = jSONObject.optString("cover_url", "");
            if (this.f3985w != null) {
                if (strOptString2 == null || strOptString2.trim().isEmpty()) {
                    this.f3985w.setImageResource(f0.e.f5007a0);
                } else {
                    AbstractC0455v.e(this.f3985w, strOptString2.trim());
                }
            }
            boolean z3 = "done".equalsIgnoreCase(jSONObject.optString("status", "active")) || jSONObject.optInt("remaining_count", 0) <= 0;
            if (z3) {
                o0.T.c(this, this.f3982F);
            }
            TextView textView2 = this.f3987y;
            if (textView2 != null) {
                textView2.setText(getString(z3 ? f0.j.T2 : f0.j.U2));
            }
            this.f3983G = jSONObject.optInt("my_claim_amount", 0);
            boolean zOptBoolean = jSONObject.optBoolean("can_claim", false);
            this.f3984H = zOptBoolean;
            TextView textView3 = this.f3977A;
            if (textView3 != null) {
                textView3.setVisibility(zOptBoolean ? 0 : 8);
            }
            TextView textView4 = this.f3988z;
            if (textView4 != null) {
                if (this.f3984H) {
                    textView4.setText("点击开启领取红包");
                } else if (this.f3983G > 0) {
                    textView4.setText("你已领取该红包，可查看详情");
                } else {
                    textView4.setText("红包状态已更新，可查看领取记录");
                }
            }
            TextView textView5 = this.f3979C;
            if (textView5 != null) {
                int i3 = this.f3983G;
                if (i3 > 0) {
                    textView5.setText(getString(f0.j.S2, Integer.valueOf(i3)));
                    this.f3979C.setVisibility(0);
                } else {
                    textView5.setVisibility(8);
                }
            }
            TextView textView6 = this.f3980D;
            if (textView6 != null) {
                if (this.f3983G <= 0 && this.f3984H) {
                    z2 = false;
                }
                if (!z2) {
                    i2 = 8;
                }
                textView6.setVisibility(i2);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p0() {
        String str = this.f3982F;
        if (str == null || str.isEmpty()) {
            Toast.makeText(this, f0.j.O2, 0).show();
            finish();
        } else {
            g0.d.R("/redpackets/" + this.f3982F, this.f3981E, new d());
        }
    }

    public final void n0() {
        String str = this.f3981E;
        if (str == null || str.isEmpty()) {
            Toast.makeText(this, f0.j.f5190N, 0).show();
            return;
        }
        String str2 = this.f3982F;
        if (str2 == null || str2.isEmpty()) {
            Toast.makeText(this, f0.j.O2, 0).show();
            return;
        }
        r0(true);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("packet_id", this.f3982F);
            g0.d.S("/redpackets/claim", jSONObject, this.f3981E, new e());
        } catch (Exception unused) {
            r0(false);
        }
    }

    public final void o0(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int iOptInt = jSONObject.optInt("amount", 0);
            if (jSONObject.optInt("remaining_count", -1) == 0) {
                o0.T.c(this, this.f3982F);
            }
            TextView textView = this.f3979C;
            if (textView != null && iOptInt > 0) {
                textView.setText(getString(f0.j.S2, Integer.valueOf(iOptInt)));
                this.f3979C.setVisibility(0);
            }
            p0();
        } catch (Exception unused) {
            p0();
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5131Y);
        View viewA0 = a0(f0.f.a3);
        this.f3985w = (ImageView) a0(f0.f.c6);
        this.f3986x = (TextView) a0(f0.f.Xc);
        this.f3987y = (TextView) a0(f0.f.Vc);
        this.f3988z = (TextView) a0(f0.f.Uc);
        this.f3977A = (TextView) a0(f0.f.H2);
        this.f3978B = (ProgressBar) a0(f0.f.A7);
        this.f3979C = (TextView) a0(f0.f.Yc);
        this.f3980D = (TextView) a0(f0.f.Y2);
        this.f3981E = getSharedPreferences("auth", 0).getString("access_token", "");
        this.f3982F = getIntent().getStringExtra("packet_id");
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        TextView textView = this.f3977A;
        if (textView != null) {
            textView.setOnClickListener(new b());
        }
        TextView textView2 = this.f3980D;
        if (textView2 != null) {
            textView2.setOnClickListener(new c());
        }
        p0();
    }

    public final void q0() {
        String str = this.f3982F;
        if (str == null || str.isEmpty()) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) RedPacketDetailActivity.class);
        intent.putExtra("packet_id", this.f3982F);
        startActivity(intent);
    }

    public final void r0(boolean z2) {
        ProgressBar progressBar = this.f3978B;
        if (progressBar != null) {
            progressBar.setVisibility(z2 ? 0 : 8);
        }
        TextView textView = this.f3977A;
        if (textView != null) {
            textView.setEnabled(!z2);
            AbstractC0285k0.K(this.f3977A, z2 ? 0.65f : 1.0f);
        }
    }
}

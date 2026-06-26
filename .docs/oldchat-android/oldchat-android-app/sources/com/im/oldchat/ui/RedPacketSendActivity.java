package com.im.oldchat.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import g0.d;
import k.AbstractC0285k0;
import o0.AbstractC0452s;
import o0.AbstractC0455v;
import org.json.JSONObject;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import org.spongycastle.i18n.MessageBundle;

/* JADX INFO: loaded from: classes.dex */
public class RedPacketSendActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public TextView f3994A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public ProgressBar f3995B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public ImageView f3996C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public TextView f3997D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public TextView f3998E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public TextView f3999F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public String f4000G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public String f4001H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public String f4002I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public String f4003J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public boolean f4004K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public boolean f4005L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public boolean f4006M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public String f4007N = "";

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public EditText f4008w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public EditText f4009x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public EditText f4010y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public TextView f4011z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RedPacketSendActivity.this.finish();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RedPacketSendActivity.this.r0();
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RedPacketSendActivity.this.m0();
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RedPacketSendActivity.this.f4007N = "";
            RedPacketSendActivity.this.o0();
        }
    }

    public class e implements d.i {
        public e() {
        }

        @Override // g0.d.i
        public void b(String str) {
            RedPacketSendActivity.this.p0(false);
            try {
                String strOptString = new JSONObject(str).optString("url", "");
                if (strOptString != null && !strOptString.trim().isEmpty()) {
                    RedPacketSendActivity.this.f4007N = strOptString.trim();
                    RedPacketSendActivity.this.o0();
                    Toast.makeText(RedPacketSendActivity.this, "封面已上传", 0).show();
                    return;
                }
                Toast.makeText(RedPacketSendActivity.this, "封面上传失败", 0).show();
            } catch (Exception unused) {
                Toast.makeText(RedPacketSendActivity.this, "封面上传失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            RedPacketSendActivity.this.p0(false);
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(RedPacketSendActivity.this, "封面上传失败", 0).show();
        }
    }

    public class f implements d.i {
        public f() {
        }

        @Override // g0.d.i
        public void b(String str) {
            RedPacketSendActivity.this.q0(false);
            Intent intent = new Intent();
            intent.putExtra("message_json", str);
            RedPacketSendActivity.this.setResult(-1, intent);
            RedPacketSendActivity.this.finish();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            RedPacketSendActivity.this.q0(false);
            if (g0.e.v(i2, str)) {
                return;
            }
            if (str != null && str.contains("red_packet_insufficient")) {
                Toast.makeText(RedPacketSendActivity.this, f0.j.N2, 0).show();
                return;
            }
            if (str != null && str.contains("red_packet_amount_invalid")) {
                Toast.makeText(RedPacketSendActivity.this, f0.j.E2, 0).show();
                return;
            }
            if (str != null && str.contains("red_packet_count_invalid")) {
                Toast.makeText(RedPacketSendActivity.this, f0.j.J2, 0).show();
                return;
            }
            if (str != null && str.contains("red_packet_amount_too_small")) {
                Toast.makeText(RedPacketSendActivity.this, f0.j.F2, 0).show();
                return;
            }
            if (str != null && str.contains("red_packet_title_too_long")) {
                Toast.makeText(RedPacketSendActivity.this, f0.j.X2, 0).show();
                return;
            }
            if (str != null && str.contains("invalid_cover_url")) {
                Toast.makeText(RedPacketSendActivity.this, "封面地址无效，请重新上传", 0).show();
                return;
            }
            Toast.makeText(RedPacketSendActivity.this, RedPacketSendActivity.this.getString(f0.j.A1) + " (" + i2 + ")", 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m0() {
        if (this.f4006M || this.f4005L) {
            return;
        }
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        intent.addCategory("android.intent.category.OPENABLE");
        startActivityForResult(Intent.createChooser(intent, getString(f0.j.M2)), 4601);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q0(boolean z2) {
        this.f4005L = z2;
        ProgressBar progressBar = this.f3995B;
        if (progressBar != null) {
            progressBar.setVisibility(z2 ? 0 : 8);
        }
        n0();
    }

    private void s0(Uri uri) {
        if (uri == null) {
            return;
        }
        String str = this.f4000G;
        if (str == null || str.isEmpty()) {
            Toast.makeText(this, f0.j.f5190N, 0).show();
            return;
        }
        try {
            byte[] bArrD = AbstractC0452s.d(getContentResolver(), uri, 1024, PKIFailureInfo.badCertTemplate);
            if (bArrD == null || bArrD.length == 0) {
                Toast.makeText(this, "封面处理失败", 0).show();
            } else if (bArrD.length > 1048576) {
                Toast.makeText(this, "封面需小于1MB，请换图后重试", 0).show();
            } else {
                p0(true);
                g0.d.T("/media", bArrD, "red_packet_cover.jpg", "image/jpeg", this.f4000G, new e());
            }
        } catch (Exception unused) {
            Toast.makeText(this, "封面处理失败，请换一张图片", 0).show();
        }
    }

    public final int l0(String str) {
        if (str == null) {
            return 0;
        }
        String strTrim = str.trim();
        if (strTrim.isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(strTrim);
        } catch (Exception unused) {
            return 0;
        }
    }

    public final void n0() {
        boolean z2 = this.f4005L || this.f4006M;
        TextView textView = this.f3994A;
        if (textView != null) {
            textView.setEnabled(!z2);
            AbstractC0285k0.K(this.f3994A, z2 ? 0.65f : 1.0f);
        }
        TextView textView2 = this.f3998E;
        if (textView2 != null) {
            textView2.setEnabled(!z2);
            AbstractC0285k0.K(this.f3998E, z2 ? 0.65f : 1.0f);
        }
        TextView textView3 = this.f3999F;
        if (textView3 != null) {
            textView3.setEnabled(!z2);
            AbstractC0285k0.K(this.f3999F, z2 ? 0.65f : 1.0f);
        }
        EditText editText = this.f4008w;
        if (editText != null) {
            editText.setEnabled(!z2);
        }
        EditText editText2 = this.f4009x;
        if (editText2 != null) {
            editText2.setEnabled(!z2);
        }
        EditText editText3 = this.f4010y;
        if (editText3 != null) {
            editText3.setEnabled(!z2);
        }
    }

    public final void o0() {
        if (this.f3996C != null) {
            String str = this.f4007N;
            if (str == null || str.trim().isEmpty()) {
                this.f3996C.setImageResource(f0.e.f5007a0);
            } else {
                AbstractC0455v.e(this.f3996C, this.f4007N.trim());
            }
        }
        if (this.f3999F != null) {
            String str2 = this.f4007N;
            this.f3999F.setVisibility(str2 != null && !str2.trim().isEmpty() ? 0 : 8);
        }
        TextView textView = this.f3997D;
        if (textView != null) {
            if (this.f4006M) {
                textView.setText("封面上传中...");
                return;
            }
            String str3 = this.f4007N;
            if (str3 == null || str3.trim().isEmpty()) {
                this.f3997D.setText(f0.j.L2);
            } else {
                this.f3997D.setText("已选择红包封面");
            }
        }
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 != 4601 || i3 != -1 || intent == null || intent.getData() == null) {
            return;
        }
        s0(intent.getData());
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        EditText editText;
        String str;
        super.onCreate(bundle);
        setContentView(f0.g.f5132Z);
        View viewA0 = a0(f0.f.c3);
        this.f4008w = (EditText) a0(f0.f.P4);
        this.f4009x = (EditText) a0(f0.f.N4);
        this.f4010y = (EditText) a0(f0.f.O4);
        this.f4011z = (TextView) a0(f0.f.Zc);
        this.f3994A = (TextView) a0(f0.f.I3);
        this.f3995B = (ProgressBar) a0(f0.f.B7);
        this.f3996C = (ImageView) a0(f0.f.Z5);
        this.f3997D = (TextView) a0(f0.f.Kc);
        this.f3998E = (TextView) a0(f0.f.b3);
        this.f3999F = (TextView) a0(f0.f.X2);
        this.f4000G = getSharedPreferences("auth", 0).getString("access_token", "");
        Intent intent = getIntent();
        this.f4001H = intent.getStringExtra("to_uid");
        this.f4002I = intent.getStringExtra("group_id");
        this.f4003J = intent.getStringExtra("target_name");
        String str2 = this.f4002I;
        this.f4004K = (str2 == null || str2.isEmpty()) ? false : true;
        if (this.f4011z == null || (str = this.f4003J) == null || str.isEmpty()) {
            TextView textView = this.f4011z;
            if (textView != null) {
                textView.setVisibility(8);
            }
        } else {
            this.f4011z.setText(getString(f0.j.V2, this.f4003J));
            this.f4011z.setVisibility(0);
        }
        if (!this.f4004K && (editText = this.f4010y) != null) {
            editText.setVisibility(8);
        }
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        TextView textView2 = this.f3994A;
        if (textView2 != null) {
            textView2.setOnClickListener(new b());
        }
        TextView textView3 = this.f3998E;
        if (textView3 != null) {
            textView3.setOnClickListener(new c());
        }
        TextView textView4 = this.f3999F;
        if (textView4 != null) {
            textView4.setOnClickListener(new d());
        }
        o0();
        n0();
    }

    public final void p0(boolean z2) {
        this.f4006M = z2;
        o0();
        n0();
    }

    public final void r0() {
        int iL0;
        String str = this.f4000G;
        if (str == null || str.isEmpty()) {
            Toast.makeText(this, f0.j.f5190N, 0).show();
            return;
        }
        if (this.f4006M) {
            Toast.makeText(this, "封面上传中，请稍后", 0).show();
            return;
        }
        EditText editText = this.f4009x;
        int iL02 = l0(editText == null ? null : editText.getText().toString());
        if (iL02 <= 0) {
            Toast.makeText(this, f0.j.E2, 0).show();
            return;
        }
        if (this.f4004K) {
            String str2 = this.f4002I;
            if (str2 == null || str2.isEmpty()) {
                Toast.makeText(this, f0.j.O2, 0).show();
                return;
            }
            EditText editText2 = this.f4010y;
            iL0 = l0(editText2 != null ? editText2.getText().toString() : null);
            if (iL0 <= 0 || iL0 < 2) {
                Toast.makeText(this, f0.j.J2, 0).show();
                return;
            }
        } else {
            iL0 = 1;
        }
        if (this.f4004K && iL02 < iL0) {
            Toast.makeText(this, f0.j.F2, 0).show();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            EditText editText3 = this.f4008w;
            String strTrim = editText3 == null ? "" : editText3.getText().toString().trim();
            if (strTrim.length() > 20) {
                Toast.makeText(this, f0.j.X2, 0).show();
                return;
            }
            if (!strTrim.isEmpty()) {
                jSONObject.put(MessageBundle.TITLE_ENTRY, strTrim);
            }
            jSONObject.put("total_amount", iL02);
            jSONObject.put("total_count", iL0);
            String str3 = this.f4007N;
            if (str3 != null && !str3.trim().isEmpty()) {
                jSONObject.put("cover_url", this.f4007N.trim());
            }
            if (!this.f4004K) {
                String str4 = this.f4001H;
                if (str4 != null && !str4.isEmpty()) {
                    jSONObject.put("to_uid", this.f4001H);
                }
                Toast.makeText(this, f0.j.O2, 0).show();
                return;
            }
            jSONObject.put("group_id", this.f4002I);
            q0(true);
            g0.d.S("/redpackets/send", jSONObject, this.f4000G, new f());
        } catch (Exception unused) {
            q0(false);
            Toast.makeText(this, f0.j.A1, 0).show();
        }
    }
}

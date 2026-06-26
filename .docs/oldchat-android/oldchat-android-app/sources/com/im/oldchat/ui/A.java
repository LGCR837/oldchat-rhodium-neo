package com.im.oldchat.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.D;
import l0.C0370b;
import o.c;
import o0.AbstractC0441g;

/* JADX INFO: loaded from: classes.dex */
public abstract class A extends l0.J {

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent(A.this, (Class<?>) GroupMembersActivity.class);
            intent.putExtra("group_id", A.this.f2408T);
            intent.putExtra("group_name", A.this.f2409U);
            intent.putExtra("group_role", A.this.f2412X);
            A.this.startActivity(intent);
        }
    }

    public class b implements CompoundButton.OnCheckedChangeListener {
        public b() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
            A a2 = A.this;
            if (a2.f2413Y) {
                return;
            }
            a2.r0();
        }
    }

    public class c implements CompoundButton.OnCheckedChangeListener {
        public c() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
            A a2 = A.this;
            if (a2.f2413Y) {
                return;
            }
            a2.r0();
        }
    }

    public class d implements CompoundButton.OnCheckedChangeListener {
        public d() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
            A a2 = A.this;
            if (a2.f2413Y) {
                return;
            }
            i0.g.g(a2, a2.f2408T, true, z2);
            Toast.makeText(A.this, z2 ? f0.j.f5237t : f0.j.f5238u, 0).show();
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C0370b c0370b = A.this.f2406R;
            if (c0370b != null) {
                c0370b.l(3103);
            }
        }
    }

    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            A.this.t0();
        }
    }

    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            A.this.s0();
        }
    }

    public class h implements View.OnClickListener {
        public h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            A a2 = A.this;
            int i2 = a2.f2412X;
            if (i2 == 2) {
                a2.f2404P.c(a2, a2.f2408T, a2.f2407S, i2);
            } else {
                a2.f2404P.d(a2, a2.f2408T, a2.f2407S);
            }
        }
    }

    public class i implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2353a;

        public i(String str) {
            this.f2353a = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            if (i2 == 0) {
                AbstractC0441g.a(A.this, this.f2353a);
            } else if (i2 == 1) {
                A.this.x0(this.f2353a);
            }
        }
    }

    public class j implements View.OnClickListener {
        public j() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            A.this.finish();
        }
    }

    public class k implements View.OnClickListener {
        public k() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            A.this.z0();
        }
    }

    public class l implements View.OnClickListener {
        public l() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            A a2 = A.this;
            if (a2.f2412X >= 1) {
                a2.n0();
            } else {
                Toast.makeText(a2, f0.j.R0, 0).show();
            }
        }
    }

    public class m implements D.d {
        public m() {
        }

        @Override // com.im.oldchat.ui.D.d
        public void a(j0.h hVar, boolean z2) {
            A.this.m0(hVar, z2);
        }

        @Override // com.im.oldchat.ui.D.d
        public void b(j0.h hVar) {
            A.this.g0(hVar);
        }
    }

    public class n implements Runnable {
        public n() {
        }

        @Override // java.lang.Runnable
        public void run() {
            A.this.q0();
        }
    }

    public class o implements View.OnClickListener {
        public o() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            l0.A a2 = A.this.f2405Q;
            if (a2 != null) {
                a2.f(3101, 3102);
            }
        }
    }

    public class p implements View.OnClickListener {
        public p() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            A.this.k0();
        }
    }

    public class q implements View.OnClickListener {
        public q() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            A.this.l0();
        }
    }

    public class r implements View.OnClickListener {
        public r() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            A.this.j0();
        }
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        l0.A a2 = this.f2405Q;
        if (a2 == null || !a2.d(i2, i3, intent, 3101)) {
            if (i2 == 3104 && i3 == -1 && intent != null) {
                this.f2410V = intent.getStringExtra("announcement_text");
                this.f2411W = intent.getIntExtra("announcement_mode", this.f2411W);
                if (this.f2410V == null) {
                    this.f2410V = "";
                }
                o0();
                return;
            }
            if (i2 == 3103 && i3 == -1 && intent != null) {
                Uri data = intent.getData();
                C0370b c0370b = this.f2406R;
                if (c0370b != null) {
                    c0370b.h(data);
                }
            }
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5167t);
        this.f2414w = (ImageView) a0(f0.f.A5);
        this.f2415x = (TextView) a0(f0.f.oa);
        this.f2416y = (TextView) a0(f0.f.ma);
        this.f2417z = a0(f0.f.f5105y);
        this.f2389A = (SwitchCompat) a0(f0.f.y8);
        this.f2390B = (SwitchCompat) a0(f0.f.w8);
        this.f2391C = (SwitchCompat) a0(f0.f.z8);
        this.f2392D = a0(f0.f.i1);
        this.f2393E = a0(f0.f.k1);
        this.f2394F = a0(f0.f.f5043D);
        this.f2395G = a0(f0.f.T0);
        this.f2396H = a0(f0.f.n1);
        this.f2397I = a0(f0.f.i3);
        this.f2398J = (TextView) a0(f0.f.ua);
        this.f2399K = (ListView) a0(f0.f.O6);
        this.f2400L = (TextView) a0(f0.f.ea);
        View viewA0 = a0(f0.f.c1);
        View viewA02 = a0(f0.f.f5061V);
        View viewA03 = a0(f0.f.G2);
        if (viewA0 instanceof ImageView) {
            ((ImageView) viewA0).setColorFilter(getResources().getColor(f0.d.f4973j));
        }
        if (viewA0 != null) {
            viewA0.setOnClickListener(new j());
        }
        if (viewA02 != null) {
            viewA02.setOnClickListener(new k());
        }
        this.f2407S = getSharedPreferences("auth", 0).getString("access_token", "");
        this.f2408T = getIntent().getStringExtra("group_id");
        this.f2409U = getIntent().getStringExtra("group_name");
        this.f2412X = getIntent().getIntExtra("group_role", 0);
        this.f2405Q = new l0.A(this, this.f2414w, this.f2407S, this.f2408T);
        this.f2406R = new C0370b(this, null, this.f2408T, true);
        TextView textView = this.f2415x;
        String str = this.f2409U;
        if (str == null) {
            str = "";
        }
        textView.setText(str);
        TextView textView2 = this.f2416y;
        String str2 = this.f2408T;
        textView2.setText(str2 != null ? str2 : "");
        this.f2415x.setOnClickListener(new l());
        D d2 = new D(this, this.f2402N, this.f2412X, new m());
        this.f2401M = d2;
        this.f2399K.setAdapter((ListAdapter) d2);
        this.f2399K.post(new n());
        this.f2417z.setOnClickListener(new o());
        this.f2392D.setOnClickListener(new p());
        this.f2393E.setOnClickListener(new q());
        View view = this.f2395G;
        if (view != null) {
            view.setOnClickListener(new r());
        }
        if (viewA03 != null) {
            viewA03.setOnClickListener(new a());
        }
        this.f2389A.setOnCheckedChangeListener(new b());
        this.f2390B.setOnCheckedChangeListener(new c());
        this.f2391C.setOnCheckedChangeListener(new d());
        this.f2394F.setOnClickListener(new e());
        View view2 = this.f2397I;
        if (view2 != null) {
            view2.setOnClickListener(new f());
        }
        View viewA04 = a0(f0.f.D3);
        if (viewA04 != null) {
            viewA04.setOnClickListener(new g());
        }
        this.f2396H.setOnClickListener(new h());
        f0();
        h0();
        i0();
    }

    @Override // f0.a, c.AbstractActivityC0167u, android.app.Activity, c.AbstractC0121a.b
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        l0.A a2 = this.f2405Q;
        if (a2 == null || !a2.e(i2, iArr, 3102, 3101)) {
            super.onRequestPermissionsResult(i2, strArr, iArr);
        }
    }

    public final void x0(String str) {
        Intent intent = new Intent(this, (Class<?>) QrCardActivity.class);
        intent.putExtra("qr_mode", "group");
        intent.putExtra("qr_id", str);
        String str2 = this.f2409U;
        if (str2 == null) {
            str2 = "";
        }
        intent.putExtra("qr_name", str2);
        startActivity(intent);
    }

    public final String y0() {
        String strTrim = this.f2408T;
        if (strTrim == null || strTrim.length() == 0) {
            TextView textView = this.f2416y;
            CharSequence text = textView == null ? null : textView.getText();
            strTrim = text == null ? "" : text.toString().trim();
        }
        return strTrim == null ? "" : strTrim.trim();
    }

    public final void z0() {
        String strY0 = y0();
        if (strY0.length() == 0) {
            Toast.makeText(this, "群号为空", 0).show();
        } else {
            new c.a(this, f0.k.f5244a).t("群分享").g(new CharSequence[]{"复制群号", "查看群二维码"}, new i(strY0)).v();
        }
    }
}

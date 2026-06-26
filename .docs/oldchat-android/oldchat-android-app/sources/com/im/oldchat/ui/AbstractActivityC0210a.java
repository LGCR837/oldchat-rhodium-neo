package com.im.oldchat.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.im.oldchat.ui.AbstractC0217h;
import com.im.oldchat.ui.C0218i;
import com.im.oldchat.ui.C0225p;
import com.im.oldchat.ui.C0226q;
import com.im.oldchat.ui.S;
import l0.AbstractActivityC0368a;
import l0.AbstractC0372c;
import l0.C0370b;
import l0.C0384i;
import l0.C0386j;
import o0.AbstractC0441g;
import o0.C;
import o0.C0432a0;

/* JADX INFO: renamed from: com.im.oldchat.ui.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractActivityC0210a extends AbstractActivityC0368a {

    /* JADX INFO: renamed from: com.im.oldchat.ui.a$a, reason: collision with other inner class name */
    public class C0030a implements C0226q.d {
        public C0030a() {
        }

        @Override // com.im.oldchat.ui.C0226q.d
        public void a() {
            AbstractActivityC0210a.this.k0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.a$b */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C0386j c0386j = AbstractActivityC0210a.this.f4410a0;
            if (c0386j != null) {
                c0386j.n();
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.a$c */
    public class c extends RecyclerView.s {
        public c() {
        }

        @Override // android.support.v7.widget.RecyclerView.s
        public void b(RecyclerView recyclerView, int i2, int i3) {
            LinearLayoutManager linearLayoutManager;
            super.b(recyclerView, i2, i3);
            if (AbstractActivityC0210a.this.f4410a0 == null || (linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager()) == null) {
                return;
            }
            AbstractActivityC0210a.this.f4410a0.k(linearLayoutManager.O1() >= linearLayoutManager.V() - 1);
            int iM1 = linearLayoutManager.M1();
            if (i3 >= 0 || iM1 > 1 || !AbstractActivityC0210a.this.f4410a0.u()) {
                return;
            }
            long jW = AbstractActivityC0210a.this.f4410a0.w();
            AbstractActivityC0210a abstractActivityC0210a = AbstractActivityC0210a.this;
            abstractActivityC0210a.f4410a0.z(abstractActivityC0210a.f4404U, true, jW, true);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.a$d */
    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractActivityC0210a.this.u0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.a$e */
    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractActivityC0210a abstractActivityC0210a = AbstractActivityC0210a.this;
            C0386j c0386j = abstractActivityC0210a.f4410a0;
            if (c0386j != null) {
                c0386j.i(abstractActivityC0210a.f4404U);
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.a$f */
    public class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C0386j c0386j = AbstractActivityC0210a.this.f4410a0;
            if (c0386j != null) {
                c0386j.j();
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.a$g */
    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractActivityC0210a.this.A0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.a$h */
    public class h implements C0225p.c {
        public h() {
        }

        @Override // com.im.oldchat.ui.C0225p.c
        public C.d a() {
            return AbstractActivityC0210a.this.f4394K;
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.a$i */
    public class i implements C0218i.j {
        public i() {
        }

        @Override // com.im.oldchat.ui.C0218i.j
        public void a(String str, String str2, String str3, int i2) {
            AbstractActivityC0210a abstractActivityC0210a = AbstractActivityC0210a.this;
            C0226q c0226q = abstractActivityC0210a.f4411b0;
            if (c0226q != null) {
                c0226q.m(str, str2, str3, i2, abstractActivityC0210a.f4394K);
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.a$j */
    public class j implements C0218i.k {
        public j() {
        }

        @Override // com.im.oldchat.ui.C0218i.k
        public void a(boolean z2) {
            AbstractActivityC0210a.this.B0(z2);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.a$k */
    public class k implements View.OnClickListener {
        public k() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractActivityC0210a.this.t0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.a$l */
    public class l implements View.OnClickListener {
        public l() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractActivityC0210a.this.s0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.a$m */
    public class m implements View.OnClickListener {
        public m() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractActivityC0210a.this.r0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.a$n */
    public class n implements C0432a0.g {
        public n() {
        }

        @Override // o0.C0432a0.g
        public void a(String str, boolean z2) {
            if (str == null || str.isEmpty()) {
                return;
            }
            if (!o0.V.n(AbstractActivityC0210a.this)) {
                AbstractActivityC0210a.this.C0(false);
                return;
            }
            AbstractActivityC0210a abstractActivityC0210a = AbstractActivityC0210a.this;
            if (o0.F.c(abstractActivityC0210a, str, abstractActivityC0210a.f4403T)) {
                return;
            }
            String str2 = AbstractActivityC0210a.this.f4400Q;
            if (str2 == null || str2.equals(str)) {
                AbstractActivityC0210a.this.C0(z2);
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.a$o */
    public class o implements AbstractC0217h.d {
        public o() {
        }

        @Override // com.im.oldchat.ui.AbstractC0217h.d
        public void a() {
            AbstractActivityC0210a.this.A0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.a$p */
    public class p implements View.OnClickListener {
        public p() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractActivityC0210a.this.finish();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.a$q */
    public class q implements View.OnClickListener {
        public q() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractActivityC0210a.this.k0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.a$s */
    public class s implements S.o {
        public s() {
        }

        @Override // com.im.oldchat.ui.S.o
        public void a(boolean z2, int i2) {
            AbstractActivityC0210a.this.q0(z2, i2);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.a$t */
    public class t implements S.n {
        public t() {
        }

        @Override // com.im.oldchat.ui.S.n
        public void a(C.d dVar) {
            String str;
            AbstractActivityC0210a abstractActivityC0210a;
            C0386j c0386j;
            if (dVar == null || (str = dVar.f7007a) == null || str.isEmpty() || (c0386j = (abstractActivityC0210a = AbstractActivityC0210a.this).f4410a0) == null) {
                return;
            }
            c0386j.y(abstractActivityC0210a.f4404U, dVar.f7007a);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.a$u */
    public class u implements View.OnClickListener {
        public u() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractActivityC0210a abstractActivityC0210a = AbstractActivityC0210a.this;
            C0386j c0386j = abstractActivityC0210a.f4410a0;
            if (c0386j != null) {
                c0386j.x(abstractActivityC0210a.f4404U);
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.a$v */
    public class v implements C0226q.e {
        public v() {
        }

        @Override // com.im.oldchat.ui.C0226q.e
        public void a(boolean z2) {
            AbstractActivityC0210a.this.B0(z2);
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5141e);
        String stringExtra = getIntent().getStringExtra("friend_uid");
        this.f4400Q = stringExtra;
        if (stringExtra == null || stringExtra.isEmpty()) {
            this.f4400Q = getIntent().getStringExtra("friend_id");
        }
        this.f4401R = getIntent().getStringExtra("friend_name");
        this.f4402S = getIntent().getStringExtra("friend_avatar");
        String str = this.f4400Q;
        if (str != null && !str.isEmpty()) {
            String str2 = this.f4401R;
            j0.r.j(this, this.f4400Q, (str2 == null || str2.isEmpty()) ? this.f4400Q : this.f4401R, this.f4402S);
        }
        TextView textView = (TextView) a0(f0.f.h9);
        if (textView != null) {
            String str3 = this.f4401R;
            if (str3 == null || str3.isEmpty()) {
                str3 = this.f4400Q;
            }
            if (str3 == null) {
                str3 = "";
            }
            textView.setText(str3);
        }
        View viewA0 = a0(f0.f.h4);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new k());
        }
        this.f4420w = (RecyclerView) a0(f0.f.Q6);
        EditText editText = (EditText) a0(f0.f.D4);
        this.f4421x = editText;
        AbstractC0217h.f(editText, new o());
        registerForContextMenu(this.f4421x);
        this.f4423z = (Button) a0(f0.f.E3);
        this.f4386C = (ProgressBar) a0(f0.f.s7);
        this.f4416g0 = C0432a0.m();
        this.f4407X = a0(f0.f.pe);
        View viewA02 = a0(f0.f.f5042C);
        if (viewA02 instanceof ImageView) {
            ((ImageView) viewA02).setColorFilter(getResources().getColor(f0.d.f4968e));
        }
        if (viewA02 != null) {
            viewA02.setOnClickListener(new p());
        }
        this.f4388E = a0(f0.f.L7);
        this.f4389F = (TextView) a0(f0.f.Ec);
        View viewA03 = a0(f0.f.U2);
        if (viewA03 != null) {
            viewA03.setOnClickListener(new q());
        }
        View viewA04 = a0(f0.f.f5096p);
        View viewA05 = a0(f0.f.f5098r);
        View viewA06 = a0(f0.f.f5092n);
        View viewA07 = a0(f0.f.f5097q);
        View viewA08 = a0(f0.f.X3);
        View viewA09 = a0(f0.f.L2);
        View viewA010 = a0(f0.f.e1);
        View viewA011 = a0(f0.f.f5074e);
        SharedPreferences sharedPreferences = getSharedPreferences("auth", 0);
        this.f4404U = sharedPreferences.getString("access_token", "");
        this.f4403T = sharedPreferences.getString("my_uid", "");
        this.f4422y = AbstractC0372c.a(this, this.f4421x, o0());
        F0();
        E0();
        S s2 = new S(this, this.f4398O, this.f4403T);
        this.f4397N = s2;
        s2.s0(new r());
        this.f4397N.w0(new s());
        D0();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.n2(true);
        this.f4420w.setLayoutManager(linearLayoutManager);
        this.f4420w.setHasFixedSize(true);
        this.f4420w.setItemViewCacheSize(20);
        this.f4420w.setItemAnimator(null);
        this.f4420w.setAdapter(this.f4397N);
        TextView textView2 = (TextView) a0(f0.f.i2);
        this.f4408Y = textView2;
        this.f4410a0 = new C0386j(this, this.f4420w, this.f4397N, this.f4398O, this.f4399P, this.f4384A, textView2, this.f4386C, this.f4400Q, this.f4401R, this.f4402S, this.f4403T);
        this.f4397N.v0(new t());
        TextView textView3 = (TextView) a0(f0.f.l1);
        this.f4409Z = textView3;
        C0386j c0386j = this.f4410a0;
        if (c0386j != null && textView3 != null) {
            c0386j.E(textView3);
            int intExtra = getIntent().getIntExtra("unread_count", 0);
            if (intExtra > 0) {
                this.f4410a0.D(intExtra);
            }
            this.f4409Z.setOnClickListener(new u());
        }
        this.f4411b0 = new C0226q(this, this.f4410a0, this.f4384A, this.f4421x, this.f4404U, this.f4400Q, new v(), new C0030a());
        TextView textView4 = this.f4408Y;
        if (textView4 != null) {
            textView4.setOnClickListener(new b());
        }
        this.f4420w.l(new c());
        if (viewA07 != null) {
            viewA07.setOnClickListener(new d());
        }
        this.f4406W = new C0384i(h0(), this.f4400Q, this.f4403T, this.f4399P, this.f4398O, this.f4397N, this.f4420w, new e(), new f());
        this.f4423z.setOnClickListener(new g());
        this.f4385B = (ProgressBar) a0(f0.f.F7);
        C0218i c0218i = new C0218i(this, this.f4404U, this.f4421x, this.f4423z, viewA08, viewA09, viewA010, viewA011, viewA04, viewA05, a0(f0.f.se), (ProgressBar) a0(f0.f.G7), (TextView) a0(f0.f.ee), new i(), new j(), new C0225p(this.f4398O, this.f4399P, this.f4397N, this.f4410a0, this.f4411b0, this.f4403T, new h()));
        this.f4396M = c0218i;
        c0218i.b0();
        if (viewA06 != null) {
            viewA06.setOnClickListener(new l());
        }
        View viewA012 = a0(f0.f.f5045F);
        if (viewA012 instanceof ImageView) {
            ((ImageView) viewA012).setColorFilter(getResources().getColor(f0.d.f4968e));
        }
        if (viewA012 != null) {
            viewA012.setOnClickListener(new m());
        }
        C0370b c0370b = new C0370b(this, a0(f0.f.g4), this.f4400Q, false);
        this.f4412c0 = c0370b;
        c0370b.e();
        g0();
        String stringExtra2 = getIntent().getStringExtra("jump_message_id");
        if (stringExtra2 == null || stringExtra2.isEmpty()) {
            this.f4410a0.z(this.f4404U, false, 0L, false);
        } else {
            this.f4410a0.y(this.f4404U, stringExtra2);
        }
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onPause() {
        String str;
        super.onPause();
        this.f4422y = AbstractC0372c.c(this, this.f4421x, o0(), this.f4422y);
        J0();
        C0386j c0386j = this.f4410a0;
        if (c0386j != null) {
            c0386j.m();
        }
        C0218i c0218i = this.f4396M;
        if (c0218i != null) {
            c0218i.h0();
        }
        S s2 = this.f4397N;
        if (s2 != null) {
            s2.B0();
        }
        g0.j.u().x(this.f4406W);
        g0.j.u().x(this.f4419j0);
        if (this.f4416g0 != null && (str = this.f4400Q) != null && !str.isEmpty()) {
            if (this.f4415f0) {
                this.f4416g0.v(this, this.f4404U, this.f4400Q, false);
                this.f4415f0 = false;
            }
            this.f4416g0.u(this.f4400Q);
            this.f4416g0.w(this.f4400Q);
        }
        this.f4417h0.removeCallbacks(this.f4418i0);
        C0(false);
        this.f4405V = true;
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onResume() {
        String str;
        super.onResume();
        g0.j.u().z(this);
        g0.j.u().r(this.f4406W);
        g0.j.u().r(this.f4419j0);
        String str2 = this.f4400Q;
        if (str2 != null && !str2.isEmpty()) {
            j0.r.c(this, this.f4400Q);
        }
        H0();
        y0();
        g0();
        C0370b c0370b = this.f4412c0;
        if (c0370b != null) {
            c0370b.e();
        }
        if (this.f4405V) {
            C0386j c0386j = this.f4410a0;
            this.f4410a0.A(this.f4404U, false, 0L, true, (c0386j == null || c0386j.g()) ? false : true);
            this.f4405V = false;
        }
        boolean zN = o0.V.n(this);
        boolean zW = g0.j.u().w();
        if (this.f4416g0 == null || (str = this.f4400Q) == null || str.isEmpty()) {
            return;
        }
        this.f4416g0.w(this.f4400Q);
        this.f4416g0.p(this.f4400Q, new n());
        if (zN && !zW) {
            this.f4416g0.s(this, this.f4404U, this.f4400Q, false);
        } else {
            this.f4416g0.u(this.f4400Q);
            C0(false);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.a$r */
    public class r implements S.m {
        public r() {
        }

        @Override // com.im.oldchat.ui.S.m
        public void a(String str) {
            AbstractC0441g.a(AbstractActivityC0210a.this, str);
        }

        @Override // com.im.oldchat.ui.S.m
        public void b(j0.k kVar) {
            AbstractActivityC0210a.this.w0(kVar);
        }

        @Override // com.im.oldchat.ui.S.m
        public void c(j0.k kVar) {
            AbstractActivityC0210a.this.x0(kVar);
        }

        @Override // com.im.oldchat.ui.S.m
        public void d(j0.k kVar) {
            AbstractActivityC0210a.this.v0(kVar);
        }

        @Override // com.im.oldchat.ui.S.m
        public void e(j0.k kVar) {
            AbstractActivityC0210a.this.n0(kVar);
        }

        @Override // com.im.oldchat.ui.S.m
        public void g(j0.k kVar) {
            AbstractActivityC0210a.this.l0(kVar);
        }

        @Override // com.im.oldchat.ui.S.m
        public void f(j0.k kVar) {
        }
    }
}

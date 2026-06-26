package com.im.oldchat.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.im.oldchat.ui.AbstractC0217h;
import com.im.oldchat.ui.C0218i;
import com.im.oldchat.ui.C0234z;
import com.im.oldchat.ui.G;
import com.im.oldchat.ui.O;
import l0.AbstractC0372c;
import l0.C0370b;
import o0.AbstractC0441g;
import o0.C;
import o0.C0432a0;

/* JADX INFO: renamed from: com.im.oldchat.ui.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractActivityC0231w extends l0.B {

    /* JADX INFO: renamed from: com.im.oldchat.ui.w$a */
    public class a implements O.e {
        public a() {
        }

        @Override // com.im.oldchat.ui.O.e
        public void a(boolean z2) {
            AbstractActivityC0231w.this.C0(z2);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.w$b */
    public class b implements O.d {
        public b() {
        }

        @Override // com.im.oldchat.ui.O.d
        public void a() {
            AbstractActivityC0231w.this.l0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.w$c */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            l0.D d2 = AbstractActivityC0231w.this.f4866k0;
            if (d2 != null) {
                d2.n();
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.w$d */
    public class d implements AbsListView.OnScrollListener {
        public d() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            l0.D d2 = AbstractActivityC0231w.this.f4866k0;
            if (d2 != null) {
                d2.k(i2 + i3 >= i4 - 1);
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.w$e */
    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            l0.D d2 = AbstractActivityC0231w.this.f4866k0;
            if (d2 != null) {
                d2.j();
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.w$f */
    public class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractActivityC0231w abstractActivityC0231w = AbstractActivityC0231w.this;
            l0.D d2 = abstractActivityC0231w.f4866k0;
            if (d2 != null) {
                d2.i(abstractActivityC0231w.f4858c0);
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.w$g */
    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractActivityC0231w.this.B0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.w$h */
    public class h implements C0234z.c {
        public h() {
        }

        @Override // com.im.oldchat.ui.C0234z.c
        public C.d a() {
            return AbstractActivityC0231w.this.f4843N;
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.w$i */
    public class i implements C0218i.j {
        public i() {
        }

        @Override // com.im.oldchat.ui.C0218i.j
        public void a(String str, String str2, String str3, int i2) {
            AbstractActivityC0231w abstractActivityC0231w = AbstractActivityC0231w.this;
            O o2 = abstractActivityC0231w.f4867l0;
            if (o2 != null) {
                o2.q(str, str2, str3, i2, abstractActivityC0231w.f4843N);
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.w$j */
    public class j implements C0218i.k {
        public j() {
        }

        @Override // com.im.oldchat.ui.C0218i.k
        public void a(boolean z2) {
            AbstractActivityC0231w.this.C0(z2);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.w$k */
    public class k implements View.OnClickListener {
        public k() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractActivityC0231w.this.v0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.w$l */
    public class l implements View.OnClickListener {
        public l() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractActivityC0231w.this.u0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.w$m */
    public class m implements View.OnClickListener {
        public m() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractActivityC0231w.this.w0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.w$n */
    public class n implements Runnable {
        public n() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractActivityC0231w abstractActivityC0231w = AbstractActivityC0231w.this;
            l0.L l2 = abstractActivityC0231w.y0;
            if (l2 != null) {
                l2.e(abstractActivityC0231w.f4853X);
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.w$o */
    public class o implements C0432a0.g {
        public o() {
        }

        @Override // o0.C0432a0.g
        public void a(String str, boolean z2) {
            if (str == null || str.isEmpty()) {
                return;
            }
            if (!o0.V.n(AbstractActivityC0231w.this)) {
                AbstractActivityC0231w.this.X0();
                return;
            }
            AbstractActivityC0231w abstractActivityC0231w = AbstractActivityC0231w.this;
            if (o0.F.c(abstractActivityC0231w, str, abstractActivityC0231w.f4859d0)) {
                return;
            }
            if (z2) {
                AbstractActivityC0231w.this.B0.remove(str);
                AbstractActivityC0231w.this.B0.add(str);
            } else {
                AbstractActivityC0231w.this.B0.remove(str);
            }
            AbstractActivityC0231w.this.i1();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.w$p */
    public class p implements AbstractC0217h.d {
        public p() {
        }

        @Override // com.im.oldchat.ui.AbstractC0217h.d
        public void a() {
            AbstractActivityC0231w.this.B0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.w$q */
    public class q implements View.OnClickListener {
        public q() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractActivityC0231w.this.finish();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.w$r */
    public class r implements View.OnClickListener {
        public r() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractActivityC0231w abstractActivityC0231w = AbstractActivityC0231w.this;
            if (abstractActivityC0231w.r0 == 0) {
                abstractActivityC0231w.a1();
                AbstractActivityC0231w.this.Y0();
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.w$s */
    public class s implements View.OnClickListener {
        public s() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractActivityC0231w.this.l0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.w$u */
    public class u implements G.c {
        public u() {
        }

        @Override // com.im.oldchat.ui.G.c
        public void a(boolean z2, int i2) {
            AbstractActivityC0231w.this.t0(z2, i2);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.w$v */
    public class v implements G.b {
        public v() {
        }

        @Override // com.im.oldchat.ui.G.b
        public void a(C.d dVar) {
            String str;
            AbstractActivityC0231w abstractActivityC0231w;
            l0.D d2;
            if (dVar == null || (str = dVar.f7007a) == null || str.isEmpty() || (d2 = (abstractActivityC0231w = AbstractActivityC0231w.this).f4866k0) == null) {
                return;
            }
            d2.w(abstractActivityC0231w.f4858c0, dVar.f7007a);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.w$w, reason: collision with other inner class name */
    public class ViewOnClickListenerC0037w implements View.OnClickListener {
        public ViewOnClickListenerC0037w() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractActivityC0231w abstractActivityC0231w = AbstractActivityC0231w.this;
            l0.D d2 = abstractActivityC0231w.f4866k0;
            if (d2 != null) {
                d2.v(abstractActivityC0231w.f4858c0);
            }
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5164q);
        this.f4855Z = getIntent().getStringExtra("group_id");
        this.f4856a0 = getIntent().getStringExtra("group_name");
        this.f4857b0 = getIntent().getStringExtra("group_avatar");
        this.f4860e0 = getIntent().getIntExtra("group_role", 0);
        String str = this.f4855Z;
        if (str != null) {
            this.f4855Z = str.trim().toUpperCase();
        }
        j0.j.i(this, this.f4855Z, this.f4856a0, this.f4857b0, this.f4860e0);
        TextView textView = (TextView) a0(f0.f.pa);
        this.f4842M = textView;
        if (textView != null) {
            String str2 = this.f4856a0;
            if (str2 == null) {
                str2 = "";
            }
            textView.setText(str2);
        }
        View viewA0 = a0(f0.f.b1);
        if (viewA0 != null) {
            viewA0.setVisibility(0);
            viewA0.setOnClickListener(new k());
        }
        this.f4871w = (ListView) a0(f0.f.Q6);
        EditText editText = (EditText) a0(f0.f.D4);
        this.f4872x = editText;
        AbstractC0217h.f(editText, new p());
        registerForContextMenu(this.f4872x);
        U0();
        this.C0 = C0432a0.m();
        this.f4874z = (Button) a0(f0.f.E3);
        this.f4832C = (ProgressBar) a0(f0.f.s7);
        this.f4863h0 = a0(f0.f.pe);
        View viewA02 = a0(f0.f.Z0);
        if (viewA02 instanceof ImageView) {
            ((ImageView) viewA02).setColorFilter(getResources().getColor(f0.d.f4968e));
        }
        if (viewA02 != null) {
            viewA02.setOnClickListener(new q());
        }
        this.f4834E = a0(f0.f.L7);
        this.f4835F = (TextView) a0(f0.f.Ec);
        this.f4840K = a0(f0.f.U2);
        this.f4870o0 = a0(f0.f.g5);
        this.p0 = (TextView) a0(f0.f.fa);
        View view = this.f4870o0;
        if (view != null) {
            view.setOnClickListener(new r());
        }
        View view2 = this.f4840K;
        if (view2 != null) {
            view2.setOnClickListener(new s());
        }
        View viewA03 = a0(f0.f.X3);
        View viewA04 = a0(f0.f.L2);
        View viewA05 = a0(f0.f.e1);
        View viewA06 = a0(f0.f.f5096p);
        View viewA07 = a0(f0.f.f5098r);
        View viewA08 = a0(f0.f.f5092n);
        View viewA09 = a0(f0.f.f5097q);
        View viewA010 = a0(f0.f.f5074e);
        SharedPreferences sharedPreferences = getSharedPreferences("auth", 0);
        this.f4858c0 = sharedPreferences.getString("access_token", "");
        this.f4859d0 = sharedPreferences.getString("my_uid", "");
        this.f4873y = AbstractC0372c.a(this, this.f4872x, q0());
        f1();
        E0();
        G g2 = new G(this, this.f4847R, this.f4859d0);
        this.f4846Q = g2;
        g2.q0(this.f4860e0);
        this.f4846Q.o0(new t());
        this.f4846Q.t0(new u());
        D0();
        this.f4871w.setAdapter((ListAdapter) this.f4846Q);
        TextView textView2 = (TextView) a0(f0.f.i2);
        this.f4864i0 = textView2;
        this.f4866k0 = new l0.D(this, this.f4871w, this.f4846Q, this.f4847R, this.f4848S, this.f4830A, textView2, this.f4832C, this.f4855Z, this.f4856a0, this.f4859d0);
        this.f4846Q.s0(new v());
        TextView textView3 = (TextView) a0(f0.f.l1);
        this.f4865j0 = textView3;
        l0.D d2 = this.f4866k0;
        if (d2 != null && textView3 != null) {
            d2.C(textView3);
            int intExtra = getIntent().getIntExtra("unread_count", 0);
            if (intExtra > 0) {
                this.f4866k0.B(intExtra);
            }
            this.f4865j0.setOnClickListener(new ViewOnClickListenerC0037w());
        }
        this.f4867l0 = new O(this, this.f4866k0, this.f4830A, this.f4872x, this.f4858c0, this.f4855Z, this.f4856a0, new a(), new b());
        TextView textView4 = this.f4864i0;
        if (textView4 != null) {
            textView4.setOnClickListener(new c());
        }
        this.f4871w.setOnScrollListener(new d());
        this.f4862g0 = new l0.H(i0(), this.f4855Z, this.f4848S, this.f4847R, this.f4846Q, this.f4871w, new e(), new f());
        this.f4874z.setOnClickListener(new g());
        this.f4831B = (ProgressBar) a0(f0.f.F7);
        C0218i c0218i = new C0218i(this, this.f4858c0, this.f4872x, this.f4874z, viewA03, viewA04, viewA05, viewA010, viewA06, viewA07, a0(f0.f.se), (ProgressBar) a0(f0.f.G7), (TextView) a0(f0.f.ee), new i(), new j(), new C0234z(this.f4847R, this.f4848S, this.f4846Q, this.f4866k0, this.f4867l0, this.f4859d0, new h()));
        this.f4845P = c0218i;
        c0218i.b0();
        if (viewA08 != null) {
            viewA08.setOnClickListener(new l());
        }
        if (viewA09 != null) {
            viewA09.setOnClickListener(new m());
        }
        View viewA011 = a0(f0.f.g4);
        this.f4841L = viewA011;
        this.f4868m0 = new C0370b(this, viewA011, this.f4855Z, true);
        g0();
        this.f4869n0 = new C();
        l0.K.a(this, this.f4858c0, this.f4855Z, this.f4846Q, this.f4849T, this.f4850U, this.f4851V, this.f4852W, this.f4853X, new n());
        h0();
        String stringExtra = getIntent().getStringExtra("jump_message_id");
        if (stringExtra == null || stringExtra.isEmpty()) {
            this.f4866k0.x(this.f4858c0, false, 0L, false);
        } else {
            this.f4866k0.w(this.f4858c0, stringExtra);
        }
        b1();
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onResume() {
        String str;
        super.onResume();
        g0.j.u().z(this);
        g0.j.u().r(this.f4862g0);
        g0.j.u().r(this.F0);
        String str2 = this.f4855Z;
        if (str2 != null && !str2.isEmpty()) {
            j0.j.b(this, this.f4855Z);
        }
        G0();
        c1();
        h0();
        g0();
        b1();
        if (this.f4861f0) {
            l0.D d2 = this.f4866k0;
            this.f4866k0.y(this.f4858c0, false, 0L, true, (d2 == null || d2.f()) ? false : true);
            this.f4861f0 = false;
        }
        boolean zN = o0.V.n(this);
        boolean zW = g0.j.u().w();
        if (this.C0 == null || (str = this.f4855Z) == null || str.isEmpty()) {
            return;
        }
        this.C0.w(this.f4855Z);
        this.C0.p(this.f4855Z, new o());
        if (zN && !zW) {
            this.C0.s(this, this.f4858c0, this.f4855Z, true);
        } else {
            this.C0.u(this.f4855Z);
            X0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.w$t */
    public class t implements G.a {
        public t() {
        }

        @Override // com.im.oldchat.ui.G.a
        public void a(String str) {
            AbstractC0441g.a(AbstractActivityC0231w.this, str);
        }

        @Override // com.im.oldchat.ui.G.a
        public void b(j0.i iVar, String str) {
            AbstractActivityC0231w.this.Q0(iVar, str);
        }

        @Override // com.im.oldchat.ui.G.a
        public void c(j0.i iVar, String str) {
            AbstractActivityC0231w.this.x0(iVar, str);
        }

        @Override // com.im.oldchat.ui.G.a
        public void d(j0.i iVar) {
            AbstractActivityC0231w.this.n0(iVar);
        }

        @Override // com.im.oldchat.ui.G.a
        public void e(j0.i iVar) {
            AbstractActivityC0231w.this.p0(iVar);
        }

        @Override // com.im.oldchat.ui.G.a
        public void g(j0.i iVar) {
            AbstractActivityC0231w.this.y0(iVar);
        }

        @Override // com.im.oldchat.ui.G.a
        public void h(j0.i iVar) {
            AbstractActivityC0231w.this.z0(iVar);
        }

        @Override // com.im.oldchat.ui.G.a
        public void f(j0.i iVar) {
        }
    }
}

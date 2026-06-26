package com.im.oldchat.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.im.oldchat.ui.Q;
import com.im.oldchat.ui.U;
import java.util.List;
import l0.AbstractC0407w;
import o.c;
import o0.AbstractC0441g;
import o0.AbstractC0455v;
import o0.C;
import org.spongycastle.i18n.TextBundle;

/* JADX INFO: loaded from: classes.dex */
public class S extends T {

    public class a implements Q.d {
        public a() {
        }

        @Override // com.im.oldchat.ui.Q.d
        public boolean a(j0.k kVar) {
            return S.this.B(kVar);
        }
    }

    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.k f4084a;

        public b(j0.k kVar) {
            this.f4084a = kVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            S.this.F0(this.f4084a);
        }
    }

    public class c implements View.OnLongClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.k f4086a;

        public c(j0.k kVar) {
            this.f4086a = kVar;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            S.this.F0(this.f4086a);
            return true;
        }
    }

    public class d implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.k f4088a;

        public d(j0.k kVar) {
            this.f4088a = kVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            m mVar = S.this.f4139t;
            if (mVar != null) {
                mVar.b(this.f4088a);
            }
        }
    }

    public class e implements AbstractC0455v.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ U.d f4090a;

        public e(U.d dVar) {
            this.f4090a = dVar;
        }

        @Override // o0.AbstractC0455v.c
        public void a(String str) {
            View view;
            Object tag = this.f4090a.f4188S.getTag();
            if (!(tag == null ? str == null || str.length() == 0 : tag.equals(str)) || (view = this.f4090a.f4189T) == null) {
                return;
            }
            view.setVisibility(8);
        }
    }

    public class f implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f4092a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ j0.k f4093b;

        public f(boolean z2, j0.k kVar) {
            this.f4092a = z2;
            this.f4093b = kVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f4092a) {
                S.this.L(this.f4093b);
            } else {
                ImagePreviewActivity.f0(S.this.f4235e, this.f4093b.f5555f);
            }
        }
    }

    public class g implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.k f4095a;

        public g(j0.k kVar) {
            this.f4095a = kVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            S.this.f4236f.D(this.f4095a);
        }
    }

    public class h implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4097a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f4098b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f4099c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f4100d;

        public h(String str, String str2, String str3, String str4) {
            this.f4097a = str;
            this.f4098b = str2;
            this.f4099c = str3;
            this.f4100d = str4;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            S.this.J(this.f4097a, this.f4098b, this.f4099c, this.f4100d);
        }
    }

    public class i implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4102a;

        public i(String str) {
            this.f4102a = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            S.this.I(this.f4102a);
        }
    }

    public class j implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4104a;

        public class a implements DialogInterface.OnClickListener {
            public a() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                try {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(j.this.f4104a));
                    intent.addFlags(268435456);
                    S.this.f4235e.startActivity(intent);
                } catch (Exception unused) {
                }
            }
        }

        public class b implements DialogInterface.OnClickListener {
            public b() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                j jVar = j.this;
                AbstractC0441g.a(S.this.f4235e, jVar.f4104a);
            }
        }

        public j(String str) {
            this.f4104a = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            new c.a(S.this.f4235e).t("下载资源").i(this.f4104a).q("复制链接", new b()).k("浏览器下载", new a()).m("取消", null).v();
        }
    }

    public class k implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4108a;

        public k(String str) {
            this.f4108a = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            S.this.K(this.f4108a);
        }
    }

    public class l implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ C.a f4110a;

        public l(C.a aVar) {
            this.f4110a = aVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractC0407w.e(S.this.f4235e, this.f4110a);
        }
    }

    public interface m {
        void a(String str);

        void b(j0.k kVar);

        void c(j0.k kVar);

        void d(j0.k kVar);

        void e(j0.k kVar);

        void f(j0.k kVar);

        void g(j0.k kVar);
    }

    public interface n {
        void a(C.d dVar);
    }

    public interface o {
        void a(boolean z2, int i2);
    }

    public S(Context context, List list, String str) {
        super(context, list, str);
    }

    @Override // com.im.oldchat.ui.T
    public /* bridge */ /* synthetic */ boolean A0(j0.k kVar) {
        return super.A0(kVar);
    }

    @Override // com.im.oldchat.ui.T
    public /* bridge */ /* synthetic */ void B0() {
        super.B0();
    }

    @Override // com.im.oldchat.ui.T
    public /* bridge */ /* synthetic */ void F0(j0.k kVar) {
        super.F0(kVar);
    }

    public final void G0(U.d dVar, boolean z2) {
        a0(dVar, z2 ? f0.e.f4984E : f0.e.f4982C);
        dVar.f4183N.setTextColor(-352321537);
        dVar.f4184O.setTextColor(-1073741825);
        TextView textView = dVar.f4185P;
        if (textView != null) {
            textView.setTextColor(-1442840577);
        }
        ImageView imageView = dVar.f4186Q;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        dVar.f4186Q.setColorFilter(-1996488705);
    }

    public final void H0(View view, j0.k kVar) {
        if (view == null || kVar == null) {
            return;
        }
        if (n0() && m0(kVar)) {
            view.setBackgroundColor(574325494);
        } else {
            if (z0(kVar.f5550a)) {
                return;
            }
            view.setBackgroundColor(0);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:205:0x0569  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x056d  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x057a  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0591  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0596  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x05a4  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x05b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void I0(com.im.oldchat.ui.U.d r23, j0.k r24, int r25) {
        /*
            Method dump skipped, instruction units count: 1510
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.im.oldchat.ui.S.I0(com.im.oldchat.ui.U$d, j0.k, int):void");
    }

    public final void J0(U.d dVar, j0.k kVar) {
        if (dVar == null || kVar == null) {
            return;
        }
        b bVar = new b(kVar);
        c cVar = new c(kVar);
        K0(dVar.f1354a, bVar, cVar);
        K0(dVar.f4203s, bVar, cVar);
        K0(dVar.f4202r, bVar, cVar);
        K0(dVar.f4188S, bVar, cVar);
        K0(dVar.f4191V, bVar, cVar);
        K0(dVar.f4194Y, bVar, cVar);
        K0(dVar.f4204t, bVar, cVar);
        K0(dVar.f4208x, bVar, cVar);
        K0(dVar.f4181L, bVar, cVar);
        K0(dVar.f4171B, bVar, cVar);
    }

    public final void K0(View view, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
        if (view == null) {
            return;
        }
        view.setOnClickListener(onClickListener);
        view.setOnLongClickListener(onLongClickListener);
    }

    public final void L0(U.d dVar, o0.C c2) {
        if (dVar == null || dVar.f4194Y == null) {
            return;
        }
        String strTrim = c2 == null ? "" : c2.f6992c;
        if (strTrim != null) {
            strTrim = strTrim.trim();
        }
        if (strTrim == null || strTrim.length() == 0) {
            dVar.f4194Y.setVisibility(8);
            dVar.f4194Y.setText("");
        } else {
            dVar.f4194Y.setText(strTrim);
            dVar.f4194Y.setVisibility(0);
        }
    }

    public final boolean M0(j0.k kVar, boolean z2) {
        if (!z2 || kVar == null) {
            return false;
        }
        String str = kVar.f5561l;
        if ((str == null ? "" : str.trim()).length() == 0) {
            return false;
        }
        String str2 = kVar.f5560k;
        return TextBundle.TEXT_ENTRY.equals(str2 == null ? TextBundle.TEXT_ENTRY : str2.toLowerCase());
    }

    public final void N0(U.d dVar) {
        if (dVar == null) {
            return;
        }
        O0(dVar.f1354a);
    }

    public final void O0(View view) {
        if (view == null) {
            return;
        }
        view.setOnClickListener(null);
        view.setOnLongClickListener(null);
    }

    @Override // com.im.oldchat.ui.U, android.support.v7.widget.RecyclerView.g
    public /* bridge */ /* synthetic */ int c() {
        return super.c();
    }

    @Override // com.im.oldchat.ui.T, android.support.v7.widget.RecyclerView.g
    public /* bridge */ /* synthetic */ int e(int i2) {
        return super.e(i2);
    }

    @Override // com.im.oldchat.ui.T
    public /* bridge */ /* synthetic */ void i0(j0.k kVar) {
        super.i0(kVar);
    }

    @Override // com.im.oldchat.ui.T
    public /* bridge */ /* synthetic */ void j0() {
        super.j0();
    }

    @Override // com.im.oldchat.ui.T
    public /* bridge */ /* synthetic */ List k0() {
        return super.k0();
    }

    @Override // android.support.v7.widget.RecyclerView.g
    public void l(RecyclerView.B b2, int i2) {
        if (e(i2) != 1) {
            I0((U.d) b2, (j0.k) this.f4233c.get(i2), i2);
            return;
        }
        U.c cVar = (U.c) b2;
        T(cVar, this.f4156l ? this.f4155k : null);
        cVar.f1354a.setBackgroundColor(0);
    }

    @Override // com.im.oldchat.ui.T
    public /* bridge */ /* synthetic */ void l0(String str) {
        super.l0(str);
    }

    @Override // com.im.oldchat.ui.T
    public /* bridge */ /* synthetic */ boolean m0(j0.k kVar) {
        return super.m0(kVar);
    }

    @Override // android.support.v7.widget.RecyclerView.g
    public RecyclerView.B n(ViewGroup viewGroup, int i2) {
        return i2 == 1 ? new U.c(LayoutInflater.from(viewGroup.getContext()).inflate(f0.g.a1, viewGroup, false)) : new U.d(LayoutInflater.from(viewGroup.getContext()).inflate(f0.g.E0, viewGroup, false));
    }

    @Override // com.im.oldchat.ui.T
    public /* bridge */ /* synthetic */ boolean n0() {
        return super.n0();
    }

    @Override // com.im.oldchat.ui.T
    public /* bridge */ /* synthetic */ void o0(j0.k kVar) {
        super.o0(kVar);
    }

    @Override // com.im.oldchat.ui.T
    public /* bridge */ /* synthetic */ void p0(j0.k kVar) {
        super.p0(kVar);
    }

    @Override // android.support.v7.widget.RecyclerView.g
    public void s(RecyclerView.B b2) {
        if (b2 instanceof U.c) {
            ((U.c) b2).T();
        }
        super.s(b2);
    }

    @Override // com.im.oldchat.ui.T
    public /* bridge */ /* synthetic */ void s0(m mVar) {
        super.s0(mVar);
    }

    @Override // com.im.oldchat.ui.T
    public /* bridge */ /* synthetic */ void t0(float f2) {
        super.t0(f2);
    }

    @Override // com.im.oldchat.ui.T
    public /* bridge */ /* synthetic */ void u0(String str) {
        super.u0(str);
    }

    @Override // com.im.oldchat.ui.T
    public /* bridge */ /* synthetic */ void v0(n nVar) {
        super.v0(nVar);
    }

    @Override // com.im.oldchat.ui.T
    public /* bridge */ /* synthetic */ void w0(o oVar) {
        super.w0(oVar);
    }

    @Override // com.im.oldchat.ui.T
    public /* bridge */ /* synthetic */ void x0(boolean z2) {
        super.x0(z2);
    }
}

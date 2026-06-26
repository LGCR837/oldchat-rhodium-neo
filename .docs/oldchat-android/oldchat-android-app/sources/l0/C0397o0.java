package l0;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.OldViewVideoDetailActivity;
import k.AbstractC0285k0;
import l0.AbstractC0399p0;

/* JADX INFO: renamed from: l0.o0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0397o0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final OldViewVideoDetailActivity f6219a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f6220b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextView f6221c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextView f6222d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f6223e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f6225g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f6227i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f6228j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f6229k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f6230l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f6231m;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f6224f = "";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f6226h = -1;

    /* JADX INFO: renamed from: l0.o0$a */
    public class a implements AbstractC0399p0.f {
        public a() {
        }

        @Override // l0.AbstractC0399p0.f
        public void a(boolean z2, String str, boolean z3) {
            if (z2) {
                C0397o0.this.f6227i = z3;
                C0397o0.this.f6228j = true;
                C0397o0.this.u();
            }
        }
    }

    /* JADX INFO: renamed from: l0.o0$b */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C0397o0.this.x();
        }
    }

    /* JADX INFO: renamed from: l0.o0$c */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C0397o0.this.r();
        }
    }

    /* JADX INFO: renamed from: l0.o0$d */
    public class d implements AbstractC0399p0.h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f6235a;

        public d(boolean z2) {
            this.f6235a = z2;
        }

        @Override // l0.AbstractC0399p0.h
        public void a(boolean z2, String str) {
            C0397o0.this.f6229k = false;
            if (z2) {
                C0397o0.this.f6227i = this.f6235a;
                C0397o0.this.f6228j = true;
                if (C0397o0.this.f6226h < 0) {
                    C0397o0.this.f6226h = 0;
                }
                if (this.f6235a) {
                    C0397o0.j(C0397o0.this);
                } else if (C0397o0.this.f6226h > 0) {
                    C0397o0.k(C0397o0.this);
                }
            }
            if (!C0397o0.this.q(str)) {
                C0397o0.this.v(str);
            }
            C0397o0.this.u();
        }
    }

    /* JADX INFO: renamed from: l0.o0$e */
    public class e implements AbstractC0399p0.h {
        public e() {
        }

        @Override // l0.AbstractC0399p0.h
        public void a(boolean z2, String str) {
            C0397o0.this.f6230l = false;
            if (z2) {
                C0397o0.this.f6231m = true;
            }
            if (!C0397o0.this.q(str)) {
                C0397o0.this.v(str);
            }
            C0397o0.this.u();
        }
    }

    public C0397o0(OldViewVideoDetailActivity oldViewVideoDetailActivity) {
        this.f6219a = oldViewVideoDetailActivity;
    }

    public static /* synthetic */ int j(C0397o0 c0397o0) {
        int i2 = c0397o0.f6226h;
        c0397o0.f6226h = i2 + 1;
        return i2;
    }

    public static /* synthetic */ int k(C0397o0 c0397o0) {
        int i2 = c0397o0.f6226h;
        c0397o0.f6226h = i2 - 1;
        return i2;
    }

    public final void o() {
        TextView textView = this.f6220b;
        if (textView != null) {
            textView.setOnClickListener(new b());
        }
        TextView textView2 = this.f6221c;
        if (textView2 != null) {
            textView2.setOnClickListener(new c());
        }
    }

    public void p(View view) {
        if (view == null) {
            return;
        }
        this.f6220b = (TextView) view.findViewById(f0.f.F2);
        this.f6221c = (TextView) view.findViewById(f0.f.E2);
        this.f6222d = (TextView) view.findViewById(f0.f.oc);
        this.f6223e = (TextView) view.findViewById(f0.f.Qb);
        o();
        u();
    }

    public final boolean q(String str) {
        return str == null || str.trim().length() == 0;
    }

    public final void r() {
        if (this.f6230l) {
            return;
        }
        long jM0 = this.f6225g;
        if (jM0 <= 0 && !q(this.f6224f)) {
            jM0 = this.f6219a.m0(this.f6224f);
            if (jM0 > 0) {
                this.f6225g = jM0;
            }
        }
        if (jM0 <= 0) {
            w("视频信息未就绪");
            return;
        }
        String strC = h0.a.c(this.f6219a);
        if (q(strC)) {
            w("请先登录B站账号");
            return;
        }
        String strB = h0.a.b(this.f6219a);
        this.f6230l = true;
        v("正在收藏...");
        u();
        AbstractC0399p0.k(jM0, strB, strC, new e());
    }

    public void s(long j2, String str, int i2) {
        if (j2 > 0) {
            this.f6225g = j2;
        }
        if (str != null && str.trim().length() > 0) {
            this.f6224f = str.trim();
        }
        if (i2 >= 0) {
            this.f6226h = i2;
        }
        u();
        t(false);
    }

    public void t(boolean z2) {
        if (this.f6220b == null || this.f6229k || q(this.f6224f)) {
            return;
        }
        if (z2 || !this.f6228j) {
            String strC = h0.a.c(this.f6219a);
            if (q(strC)) {
                return;
            }
            AbstractC0399p0.o(this.f6224f, strC, new a());
        }
    }

    public final void u() {
        TextView textView = this.f6220b;
        if (textView != null) {
            textView.setEnabled(!this.f6229k);
            this.f6220b.setText(this.f6229k ? "处理中" : this.f6227i ? "已点赞" : "点赞");
            this.f6220b.setBackgroundResource(this.f6227i ? f0.e.f5032s : f0.e.f5033t);
            this.f6220b.setTextColor(this.f6219a.getResources().getColor(this.f6227i ? f0.d.f4970g : f0.d.f4973j));
            AbstractC0285k0.K(this.f6220b, this.f6229k ? 0.7f : 1.0f);
        }
        TextView textView2 = this.f6221c;
        if (textView2 != null) {
            textView2.setEnabled(!this.f6230l);
            this.f6221c.setText(this.f6230l ? "处理中" : this.f6231m ? "已收藏" : "收藏");
            this.f6221c.setBackgroundResource(this.f6231m ? f0.e.f5032s : f0.e.f5033t);
            this.f6221c.setTextColor(this.f6219a.getResources().getColor(this.f6231m ? f0.d.f4970g : f0.d.f4973j));
            AbstractC0285k0.K(this.f6221c, this.f6230l ? 0.7f : 1.0f);
        }
        TextView textView3 = this.f6223e;
        if (textView3 == null || this.f6226h < 0) {
            return;
        }
        textView3.setText("点赞 " + this.f6219a.o0(this.f6226h));
    }

    public final void v(String str) {
        if (this.f6222d == null || q(str)) {
            return;
        }
        this.f6222d.setText(str);
    }

    public final void w(String str) {
        Toast.makeText(this.f6219a, str, 0).show();
    }

    public final void x() {
        if (this.f6229k) {
            return;
        }
        if (q(this.f6224f)) {
            w("视频信息未就绪");
            return;
        }
        String strC = h0.a.c(this.f6219a);
        if (q(strC)) {
            w("请先登录B站账号");
            return;
        }
        String strB = h0.a.b(this.f6219a);
        boolean z2 = !this.f6227i;
        this.f6229k = true;
        v("正在提交点赞...");
        u();
        AbstractC0399p0.t(this.f6224f, z2, strB, strC, new d(z2));
    }
}

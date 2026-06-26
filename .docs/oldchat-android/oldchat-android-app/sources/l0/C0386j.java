package l0;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.ChatActivity;
import g0.d;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: renamed from: l0.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0386j extends AbstractC0388k {

    /* JADX INFO: renamed from: l0.j$a */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f6091a;

        public a(int i2) {
            this.f6091a = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i2 = this.f6091a;
            if (i2 < 0 || i2 >= C0386j.this.f6110d.size()) {
                return;
            }
            LinearLayoutManager linearLayoutManagerC = C0386j.this.c();
            if (linearLayoutManagerC != null) {
                linearLayoutManagerC.k2(this.f6091a, 0);
            } else {
                C0386j.this.f6108b.T0(this.f6091a);
            }
        }
    }

    /* JADX INFO: renamed from: l0.j$b */
    public class b implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f6093a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f6094b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f6095c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ LinearLayoutManager f6096d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ int f6097e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ String f6098f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ boolean f6099g;

        public b(boolean z2, boolean z3, int i2, LinearLayoutManager linearLayoutManager, int i3, String str, boolean z4) {
            this.f6093a = z2;
            this.f6094b = z3;
            this.f6095c = i2;
            this.f6096d = linearLayoutManager;
            this.f6097e = i3;
            this.f6098f = str;
            this.f6099g = z4;
        }

        @Override // g0.d.i
        public void b(String str) {
            int i2;
            int size;
            TextView textView;
            TextView textView2;
            try {
                List<j0.k> listA = AbstractC0396o.a(str);
                int iB = AbstractC0396o.b(str, C0386j.this.f6123q);
                Collections.reverse(listA);
                if (this.f6093a || !this.f6094b || listA.isEmpty()) {
                    i2 = 0;
                } else {
                    HashSet hashSet = new HashSet(C0386j.this.f6111e);
                    i2 = 0;
                    for (j0.k kVar : listA) {
                        String str2 = kVar.f5550a;
                        if (str2 != null && !hashSet.contains(str2)) {
                            String str3 = kVar.f5552c;
                            if (str3 != null) {
                                C0386j c0386j = C0386j.this;
                                if (!o0.F.c(c0386j.f6107a, str3, c0386j.f6118l)) {
                                }
                            }
                            i2++;
                        }
                    }
                }
                if (this.f6093a) {
                    int size2 = C0386j.this.f6110d.size();
                    C0386j c0386j2 = C0386j.this;
                    C0392m c0392mC = AbstractC0394n.c(c0386j2.f6110d, c0386j2.f6111e, listA, c0386j2.f6123q, 20);
                    C0386j c0386j3 = C0386j.this;
                    int i3 = c0392mC.f6208a;
                    c0386j3.f6123q = i3;
                    c0386j3.f6123q = Math.max(i3, iB + listA.size());
                    C0386j c0386j4 = C0386j.this;
                    c0386j4.f6121o = c0392mC.f6209b;
                    size = c0386j4.f6110d.size() - size2;
                    if (size < 0) {
                        size = 0;
                    }
                    C0386j c0386j5 = C0386j.this;
                    if (!c0386j5.f6121o && (textView2 = c0386j5.f6112f) != null) {
                        textView2.setEnabled(false);
                        C0386j.this.f6112f.setText(f0.j.H1);
                    }
                } else {
                    C0386j c0386j6 = C0386j.this;
                    C0392m c0392mD = AbstractC0394n.d(c0386j6.f6110d, c0386j6.f6111e, listA, 20, c0386j6.f6121o);
                    C0386j c0386j7 = C0386j.this;
                    c0386j7.f6123q = c0392mD.f6208a;
                    c0386j7.f6121o = c0392mD.f6209b;
                    if (c0392mD.f6210c && (textView = c0386j7.f6112f) != null) {
                        textView.setEnabled(true);
                        C0386j.this.f6112f.setText(f0.j.l1);
                    }
                    size = 0;
                }
                if (!this.f6093a) {
                    C0386j c0386j8 = C0386j.this;
                    AbstractC0394n.f(c0386j8.f6110d, c0386j8.f6111e, 320);
                }
                C0386j.this.s(listA, this.f6093a);
                C0386j c0386j9 = C0386j.this;
                AbstractC0380g.a(c0386j9.f6107a, c0386j9.f6110d, c0386j9.f6118l);
                C0386j.this.f6109c.g();
                C0386j c0386j10 = C0386j.this;
                j0.l.l(c0386j10.f6107a, c0386j10.f6115i, c0386j10.f6110d);
                if (this.f6093a) {
                    int size3 = this.f6095c + size;
                    if (size3 < 0) {
                        size3 = 0;
                    }
                    if (size3 >= C0386j.this.f6110d.size()) {
                        size3 = C0386j.this.f6110d.size() - 1;
                    }
                    LinearLayoutManager linearLayoutManager = this.f6096d;
                    if (linearLayoutManager != null) {
                        linearLayoutManager.k2(size3, this.f6097e);
                    } else {
                        C0386j.this.f6108b.T0(size3);
                    }
                } else if (!C0386j.this.f6110d.isEmpty()) {
                    if (this.f6094b) {
                        int iMin = Math.min(this.f6095c, C0386j.this.f6110d.size() - 1);
                        LinearLayoutManager linearLayoutManager2 = this.f6096d;
                        if (linearLayoutManager2 != null) {
                            linearLayoutManager2.k2(iMin, this.f6097e);
                        } else {
                            C0386j.this.f6108b.T0(iMin);
                        }
                    } else {
                        C0386j c0386j11 = C0386j.this;
                        c0386j11.f6108b.T0(c0386j11.f6110d.size() - 1);
                        C0386j.this.i(this.f6098f);
                    }
                }
                if (!this.f6093a && this.f6094b && i2 > 0) {
                    C0386j c0386j12 = C0386j.this;
                    c0386j12.f6122p += i2;
                    c0386j12.p();
                }
            } catch (Exception unused) {
                if (!this.f6099g) {
                    Toast.makeText(C0386j.this.f6107a, "加载消息失败", 0).show();
                }
            }
            C0386j.this.o(false);
            C0386j c0386j13 = C0386j.this;
            c0386j13.f6120n = false;
            if (this.f6093a) {
                c0386j13.B();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            C0386j.this.o(false);
            C0386j c0386j = C0386j.this;
            c0386j.f6120n = false;
            c0386j.f6125s = false;
            c0386j.f6127u = null;
            c0386j.f6128v = null;
            c0386j.f6129w = false;
            if (g0.e.v(i2, str)) {
                return;
            }
            if (i2 == 404) {
                Toast.makeText(C0386j.this.f6107a, "该用户不存在，可能已更改UID", 1).show();
                C0386j c0386j2 = C0386j.this;
                j0.r.h(c0386j2.f6107a, c0386j2.f6115i);
                Context context = C0386j.this.f6107a;
                if (context instanceof ChatActivity) {
                    ((ChatActivity) context).finish();
                    return;
                }
                return;
            }
            if (this.f6099g) {
                return;
            }
            Toast.makeText(C0386j.this.f6107a, "同步失败: " + i2, 0).show();
        }
    }

    public C0386j(Context context, RecyclerView recyclerView, com.im.oldchat.ui.S s2, List list, HashSet hashSet, TextView textView, TextView textView2, ProgressBar progressBar, String str, String str2, String str3, String str4) {
        this.f6107a = context;
        this.f6108b = recyclerView;
        this.f6109c = s2;
        this.f6110d = list;
        this.f6111e = hashSet;
        this.f6112f = textView;
        this.f6113g = textView2;
        this.f6114h = progressBar;
        this.f6115i = str;
        this.f6116j = str2;
        this.f6117k = str3;
        this.f6118l = str4;
    }

    public void A(String str, boolean z2, long j2, boolean z3, boolean z4) {
        int iM1;
        int top;
        String str2;
        if (!z2 && !z4) {
            h();
        }
        if ((z2 && !o0.G.d().f()) || str == null || str.isEmpty()) {
            o(false);
            return;
        }
        if (z2) {
            this.f6120n = true;
        } else {
            this.f6123q = 0;
        }
        o(true);
        StringBuilder sb = new StringBuilder("/direct/messages/v2?with_uid=");
        sb.append(this.f6115i);
        sb.append("&limit=");
        sb.append(20);
        sb.append("&offset=");
        sb.append(this.f6123q);
        if (z2 && (str2 = this.f6127u) != null && !str2.isEmpty() && !this.f6129w) {
            sb.append("&anchor_message_id=");
            sb.append(H(this.f6127u));
            this.f6129w = true;
        }
        int i2 = this.f6131y;
        int i3 = this.f6132z;
        LinearLayoutManager linearLayoutManagerC = c();
        if (linearLayoutManagerC == null || (iM1 = linearLayoutManagerC.M1()) < 0) {
            iM1 = i2;
            top = i3;
        } else {
            View viewA = linearLayoutManagerC.A(iM1);
            top = viewA != null ? viewA.getTop() : 0;
        }
        g0.d.R(sb.toString(), str, new b(z2, z4, iM1, linearLayoutManagerC, top, str, z3));
    }

    public final void B() {
        String str;
        if (!G() && this.f6125s) {
            int size = this.f6110d.size() - this.f6124r;
            if (size < 0) {
                size = 0;
            }
            if (size == 0 && this.f6121o && !this.f6120n && (str = this.f6126t) != null && !str.isEmpty()) {
                z(this.f6126t, true, w(), false);
            } else {
                C(size);
                d();
            }
        }
    }

    public final void C(int i2) {
        if (i2 < 0 || i2 >= this.f6110d.size()) {
            return;
        }
        LinearLayoutManager linearLayoutManagerC = c();
        if (linearLayoutManagerC != null) {
            linearLayoutManagerC.k2(i2, 0);
        } else {
            this.f6108b.T0(i2);
        }
        this.f6108b.post(new a(i2));
    }

    public void D(int i2) {
        if (i2 <= 8) {
            this.f6124r = 0;
            TextView textView = this.f6119m;
            if (textView != null) {
                textView.clearAnimation();
                this.f6119m.setClickable(false);
                this.f6119m.setVisibility(8);
                return;
            }
            return;
        }
        this.f6124r = i2;
        TextView textView2 = this.f6119m;
        if (textView2 != null) {
            textView2.clearAnimation();
            this.f6119m.setClickable(true);
            this.f6119m.setText(i2 + " 条未读");
            this.f6119m.setVisibility(0);
        }
    }

    public void E(TextView textView) {
        this.f6119m = textView;
    }

    public void F(String str) {
        this.f6118l = str;
    }

    public final boolean G() {
        String str;
        String str2 = this.f6127u;
        if (str2 == null || str2.isEmpty()) {
            return false;
        }
        int iV = v(this.f6127u);
        if (iV >= 0) {
            C(iV);
            com.im.oldchat.ui.S s2 = this.f6109c;
            if (s2 != null) {
                s2.l0(this.f6127u);
            }
            this.f6127u = null;
            this.f6128v = null;
            this.f6129w = false;
            return true;
        }
        if (this.f6121o && !this.f6120n && (str = this.f6128v) != null && !str.isEmpty()) {
            z(this.f6128v, true, w(), true);
            return true;
        }
        this.f6127u = null;
        this.f6128v = null;
        this.f6129w = false;
        Toast.makeText(this.f6107a, "未找到目标消息，可能已撤回或被清理", 0).show();
        return true;
    }

    public final String H(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception unused) {
            return str;
        }
    }

    @Override // l0.AbstractC0388k
    public /* bridge */ /* synthetic */ void a(j0.k kVar, boolean z2, String str) {
        super.a(kVar, z2, str);
    }

    @Override // l0.AbstractC0388k
    public void d() {
        this.f6124r = 0;
        this.f6125s = false;
        this.f6126t = null;
        TextView textView = this.f6119m;
        if (textView != null) {
            textView.clearAnimation();
            this.f6119m.setClickable(false);
            this.f6119m.setVisibility(8);
        }
    }

    @Override // l0.AbstractC0388k
    public /* bridge */ /* synthetic */ void e() {
        super.e();
    }

    @Override // l0.AbstractC0388k
    public /* bridge */ /* synthetic */ boolean g() {
        return super.g();
    }

    @Override // l0.AbstractC0388k
    public /* bridge */ /* synthetic */ void i(String str) {
        super.i(str);
    }

    @Override // l0.AbstractC0388k
    public /* bridge */ /* synthetic */ void j() {
        super.j();
    }

    @Override // l0.AbstractC0388k
    public /* bridge */ /* synthetic */ void k(boolean z2) {
        super.k(z2);
    }

    @Override // l0.AbstractC0388k
    public /* bridge */ /* synthetic */ j0.k l(String str) {
        return super.l(str);
    }

    @Override // l0.AbstractC0388k
    public /* bridge */ /* synthetic */ void m() {
        super.m();
    }

    @Override // l0.AbstractC0388k
    public /* bridge */ /* synthetic */ void n() {
        super.n();
    }

    public boolean u() {
        return !this.f6120n && this.f6121o;
    }

    public final int v(String str) {
        if (str != null && !str.isEmpty()) {
            for (int i2 = 0; i2 < this.f6110d.size(); i2++) {
                j0.k kVar = (j0.k) this.f6110d.get(i2);
                if (kVar != null && str.equals(kVar.f5550a)) {
                    return i2;
                }
            }
        }
        return -1;
    }

    public long w() {
        if (this.f6110d.isEmpty()) {
            return 0L;
        }
        return ((j0.k) this.f6110d.get(0)).f5558i;
    }

    public void x(String str) {
        e();
        if (this.f6124r <= 0) {
            return;
        }
        int size = this.f6110d.size() - this.f6124r;
        if (size < 0) {
            size = 0;
        }
        this.f6126t = str;
        if (size == 0 && this.f6121o && !this.f6120n) {
            this.f6125s = true;
            z(str, true, w(), false);
        } else {
            C(size);
            d();
        }
    }

    public void y(String str, String str2) {
        if (str2 == null || str2.isEmpty()) {
            return;
        }
        int iV = v(str2);
        if (iV >= 0) {
            C(iV);
            com.im.oldchat.ui.S s2 = this.f6109c;
            if (s2 != null) {
                s2.l0(str2);
                return;
            }
            return;
        }
        if (this.f6120n) {
            this.f6127u = str2;
            this.f6128v = str;
            this.f6129w = false;
        } else {
            if (!u() || str == null || str.isEmpty()) {
                Toast.makeText(this.f6107a, "未找到目标消息，可能已撤回或被清理", 0).show();
                return;
            }
            this.f6127u = str2;
            this.f6128v = str;
            this.f6129w = false;
            z(str, true, w(), true);
        }
    }

    public void z(String str, boolean z2, long j2, boolean z3) {
        A(str, z2, j2, z3, false);
    }
}

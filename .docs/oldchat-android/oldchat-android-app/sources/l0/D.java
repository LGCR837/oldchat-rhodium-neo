package l0;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import g0.d;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class D extends F {

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5785a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f5786b;

        public a(int i2, int i3) {
            this.f5785a = i2;
            this.f5786b = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i2 = this.f5785a;
            if (i2 < 0 || i2 >= D.this.f5808d.size()) {
                return;
            }
            D.this.f5806b.setSelectionFromTop(this.f5786b + this.f5785a, 0);
        }
    }

    public class b implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f5788a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f5789b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f5790c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f5791d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ int f5792e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ boolean f5793f;

        public b(boolean z2, boolean z3, String str, int i2, int i3, boolean z4) {
            this.f5788a = z2;
            this.f5789b = z3;
            this.f5790c = str;
            this.f5791d = i2;
            this.f5792e = i3;
            this.f5793f = z4;
        }

        @Override // g0.d.i
        public void b(String str) {
            int iC;
            int size;
            TextView textView;
            TextView textView2;
            try {
                List listG = N.g(str);
                int iF = N.f(str, D.this.f5819o);
                if (!this.f5788a && !this.f5789b && listG.isEmpty() && !D.this.f5808d.isEmpty()) {
                    D.this.y(this.f5790c, false, 0L, true, true);
                }
                if (this.f5788a || !this.f5789b || listG.isEmpty()) {
                    iC = 0;
                } else {
                    D d2 = D.this;
                    iC = N.c(d2.f5805a, d2.f5815k, listG, d2.f5809e);
                }
                if (this.f5788a) {
                    int size2 = D.this.f5808d.size();
                    D d3 = D.this;
                    M mD = N.d(d3.f5808d, d3.f5809e, listG, d3.f5819o, 20);
                    D d4 = D.this;
                    int i2 = mD.f5938a;
                    d4.f5819o = i2;
                    d4.f5819o = Math.max(i2, iF + listG.size());
                    D d5 = D.this;
                    d5.f5817m = mD.f5939b;
                    size = d5.f5808d.size() - size2;
                    if (size < 0) {
                        size = 0;
                    }
                    D d6 = D.this;
                    if (!d6.f5817m && (textView2 = d6.f5810f) != null) {
                        textView2.setEnabled(false);
                        D.this.f5810f.setText(f0.j.H1);
                    }
                } else {
                    D d7 = D.this;
                    M mE = N.e(d7.f5808d, d7.f5809e, listG, 20, d7.f5817m);
                    D d8 = D.this;
                    d8.f5819o = mE.f5938a;
                    d8.f5817m = mE.f5939b;
                    if (mE.f5940c && (textView = d8.f5810f) != null) {
                        textView.setEnabled(true);
                        D.this.f5810f.setText(f0.j.l1);
                    }
                    size = 0;
                }
                D.this.q(listG, this.f5788a);
                D.this.f5807c.notifyDataSetChanged();
                D d9 = D.this;
                j0.l.m(d9.f5805a, d9.f5813i, d9.f5808d);
                if (this.f5788a) {
                    D.this.f5806b.setTranscriptMode(0);
                    int i3 = this.f5791d + size;
                    if (i3 < 0) {
                        i3 = 0;
                    }
                    int count = D.this.f5806b.getCount() - 1;
                    if (count >= 0 && i3 > count) {
                        i3 = count;
                    }
                    D.this.f5806b.setSelectionFromTop(i3, this.f5792e);
                } else if (!D.this.f5808d.isEmpty()) {
                    if (this.f5789b) {
                        int count2 = D.this.f5806b.getCount() - 1;
                        int iMin = count2 < 0 ? 0 : Math.min(this.f5791d, count2);
                        D.this.f5806b.setTranscriptMode(0);
                        D.this.f5806b.setSelectionFromTop(iMin, this.f5792e);
                    } else {
                        int count3 = D.this.f5806b.getCount() - 1;
                        if (count3 >= 0) {
                            D.this.f5806b.setSelection(count3);
                        }
                        D.this.f5806b.setTranscriptMode(2);
                        D.this.i(this.f5790c);
                    }
                }
                if (!this.f5788a && this.f5789b && iC > 0) {
                    D d10 = D.this;
                    d10.f5818n += iC;
                    d10.p();
                }
            } catch (Exception unused) {
                if (!this.f5793f && D.this.g()) {
                    Toast.makeText(D.this.f5805a, "加载消息失败", 0).show();
                }
            }
            D.this.o(false);
            D d11 = D.this;
            d11.f5816l = false;
            if (this.f5788a) {
                d11.z();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            D.this.o(false);
            D d2 = D.this;
            d2.f5816l = false;
            d2.f5823s = false;
            d2.f5825u = null;
            d2.f5826v = null;
            d2.f5827w = false;
            if (g0.e.v(i2, str)) {
                return;
            }
            if (i2 != 404) {
                if (this.f5793f || !D.this.g()) {
                    return;
                }
                Toast.makeText(D.this.f5805a, "同步失败: " + i2, 0).show();
                return;
            }
            D d3 = D.this;
            j0.j.f(d3.f5805a, d3.f5813i);
            D d4 = D.this;
            j0.f.c(d4.f5805a, d4.f5813i);
            D d5 = D.this;
            j0.l.i(d5.f5805a, d5.f5813i);
            Context context = D.this.f5805a;
            if (context instanceof Activity) {
                ((Activity) context).finish();
            }
        }
    }

    public D(Context context, ListView listView, com.im.oldchat.ui.G g2, List list, HashSet hashSet, TextView textView, TextView textView2, ProgressBar progressBar, String str, String str2, String str3) {
        this.f5805a = context;
        this.f5806b = listView;
        this.f5807c = g2;
        this.f5808d = list;
        this.f5809e = hashSet;
        this.f5810f = textView;
        this.f5811g = textView2;
        this.f5812h = progressBar;
        this.f5813i = str;
        this.f5814j = str2;
        this.f5815k = str3;
    }

    public final void A(int i2) {
        if (i2 < 0 || i2 >= this.f5808d.size()) {
            return;
        }
        ListView listView = this.f5806b;
        int headerViewsCount = listView == null ? 0 : listView.getHeaderViewsCount();
        this.f5806b.setTranscriptMode(0);
        this.f5806b.setSelectionFromTop(headerViewsCount + i2, 0);
        this.f5806b.post(new a(i2, headerViewsCount));
    }

    public void B(int i2) {
        if (i2 <= 8) {
            this.f5822r = 0;
            TextView textView = this.f5821q;
            if (textView != null) {
                textView.clearAnimation();
                this.f5821q.setClickable(false);
                this.f5821q.setVisibility(8);
                return;
            }
            return;
        }
        this.f5822r = i2;
        TextView textView2 = this.f5821q;
        if (textView2 != null) {
            textView2.clearAnimation();
            this.f5821q.setClickable(true);
            this.f5821q.setText(i2 + " 条未读");
            this.f5821q.setVisibility(0);
        }
    }

    public void C(TextView textView) {
        this.f5821q = textView;
    }

    public void D(String str) {
        this.f5815k = str;
    }

    public final boolean E() {
        String str;
        String str2 = this.f5825u;
        if (str2 == null || str2.isEmpty()) {
            return false;
        }
        int iT = t(this.f5825u);
        if (iT >= 0) {
            A(iT);
            com.im.oldchat.ui.G g2 = this.f5807c;
            if (g2 != null) {
                g2.g0(this.f5825u);
            }
            this.f5825u = null;
            this.f5826v = null;
            this.f5827w = false;
            return true;
        }
        if (this.f5817m && !this.f5816l && (str = this.f5826v) != null && !str.isEmpty()) {
            x(this.f5826v, true, u(), true);
            return true;
        }
        this.f5825u = null;
        this.f5826v = null;
        this.f5827w = false;
        Toast.makeText(this.f5805a, "未找到目标消息，可能已撤回或被清理", 0).show();
        return true;
    }

    public final String F(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception unused) {
            return str;
        }
    }

    @Override // l0.F
    public /* bridge */ /* synthetic */ void b(j0.i iVar, boolean z2) {
        super.b(iVar, z2);
    }

    @Override // l0.F
    public void c() {
        this.f5822r = 0;
        this.f5823s = false;
        this.f5824t = null;
        TextView textView = this.f5821q;
        if (textView != null) {
            textView.clearAnimation();
            this.f5821q.setClickable(false);
            this.f5821q.setVisibility(8);
        }
    }

    @Override // l0.F
    public /* bridge */ /* synthetic */ void d() {
        super.d();
    }

    @Override // l0.F
    public /* bridge */ /* synthetic */ boolean f() {
        return super.f();
    }

    @Override // l0.F
    public /* bridge */ /* synthetic */ void i(String str) {
        super.i(str);
    }

    @Override // l0.F
    public /* bridge */ /* synthetic */ void j() {
        super.j();
    }

    @Override // l0.F
    public /* bridge */ /* synthetic */ void k(boolean z2) {
        super.k(z2);
    }

    @Override // l0.F
    public /* bridge */ /* synthetic */ j0.i l(String str) {
        return super.l(str);
    }

    @Override // l0.F
    public /* bridge */ /* synthetic */ void m() {
        super.m();
    }

    @Override // l0.F
    public /* bridge */ /* synthetic */ void n() {
        super.n();
    }

    public boolean s() {
        return !this.f5816l && this.f5817m;
    }

    public final int t(String str) {
        if (str != null && !str.isEmpty()) {
            for (int i2 = 0; i2 < this.f5808d.size(); i2++) {
                j0.i iVar = (j0.i) this.f5808d.get(i2);
                if (iVar != null && str.equals(iVar.f5529a)) {
                    return i2;
                }
            }
        }
        return -1;
    }

    public long u() {
        if (this.f5808d.isEmpty()) {
            return 0L;
        }
        return ((j0.i) this.f5808d.get(0)).f5537i;
    }

    public void v(String str) {
        d();
        if (this.f5822r <= 0) {
            return;
        }
        int size = this.f5808d.size() - this.f5822r;
        if (size < 0) {
            size = 0;
        }
        this.f5824t = str;
        if (size == 0 && this.f5817m && !this.f5816l) {
            this.f5823s = true;
            x(str, true, u(), false);
        } else {
            A(size);
            c();
        }
    }

    public void w(String str, String str2) {
        if (str2 == null || str2.isEmpty()) {
            return;
        }
        int iT = t(str2);
        if (iT >= 0) {
            A(iT);
            com.im.oldchat.ui.G g2 = this.f5807c;
            if (g2 != null) {
                g2.g0(str2);
                return;
            }
            return;
        }
        if (this.f5816l) {
            this.f5825u = str2;
            this.f5826v = str;
            this.f5827w = false;
        } else {
            if (!s() || str == null || str.isEmpty()) {
                Toast.makeText(this.f5805a, "未找到目标消息，可能已撤回或被清理", 0).show();
                return;
            }
            this.f5825u = str2;
            this.f5826v = str;
            this.f5827w = false;
            x(str, true, u(), true);
        }
    }

    public void x(String str, boolean z2, long j2, boolean z3) {
        y(str, z2, j2, z3, false);
    }

    public void y(String str, boolean z2, long j2, boolean z3, boolean z4) {
        String str2;
        String str3 = this.f5813i;
        if (str3 == null || str3.isEmpty()) {
            return;
        }
        if (!z2 && !z4) {
            h();
        }
        if ((z2 && !o0.G.d().f()) || str == null || str.isEmpty()) {
            o(false);
            return;
        }
        if (z2) {
            this.f5816l = true;
            this.f5806b.setTranscriptMode(0);
        } else {
            this.f5819o = 0;
        }
        o(true);
        StringBuilder sb = new StringBuilder("/groups/messages/v2?group_id=");
        sb.append(this.f5813i);
        sb.append("&limit=");
        sb.append(20);
        sb.append("&offset=");
        sb.append(this.f5819o);
        if (z2 && (str2 = this.f5825u) != null && !str2.isEmpty() && !this.f5827w) {
            sb.append("&anchor_message_id=");
            sb.append(F(this.f5825u));
            this.f5827w = true;
        }
        int firstVisiblePosition = this.f5806b.getFirstVisiblePosition();
        View childAt = this.f5806b.getChildAt(0);
        g0.d.R(sb.toString(), str, new b(z2, z4, str, firstVisiblePosition, childAt == null ? 0 : childAt.getTop(), z3));
    }

    public final void z() {
        String str;
        if (!E() && this.f5823s) {
            int size = this.f5808d.size() - this.f5822r;
            if (size < 0) {
                size = 0;
            }
            if (size == 0 && this.f5817m && !this.f5816l && (str = this.f5824t) != null && !str.isEmpty()) {
                x(this.f5824t, true, u(), false);
            } else {
                A(size);
                c();
            }
        }
    }
}

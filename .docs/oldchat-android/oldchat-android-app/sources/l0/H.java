package l0;

import android.widget.ListView;
import com.im.oldchat.ui.GroupChatActivity;
import g0.j;
import java.util.HashSet;
import java.util.List;
import o0.C0432a0;

/* JADX INFO: loaded from: classes.dex */
public class H implements j.InterfaceC0042j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final GroupChatActivity f5858a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f5859b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashSet f5860c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f5861d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final com.im.oldchat.ui.G f5862e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ListView f5863f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Runnable f5864g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Runnable f5865h;

    public H(GroupChatActivity groupChatActivity, String str, HashSet hashSet, List list, com.im.oldchat.ui.G g2, ListView listView, Runnable runnable, Runnable runnable2) {
        this.f5858a = groupChatActivity;
        this.f5859b = str;
        this.f5860c = hashSet;
        this.f5861d = list;
        this.f5862e = g2;
        this.f5863f = listView;
        this.f5864g = runnable;
        this.f5865h = runnable2;
    }

    @Override // g0.j.InterfaceC0042j
    public void d(g0.o oVar) {
        String str;
        String str2;
        if (oVar == null || (str = oVar.f5430a) == null || str.isEmpty() || (str2 = this.f5859b) == null || !str2.equals(oVar.f5431b) || !this.f5860c.contains(oVar.f5430a)) {
            return;
        }
        for (j0.i iVar : this.f5861d) {
            if (iVar != null && oVar.f5430a.equals(iVar.f5529a)) {
                String strH = h(oVar.f5432c);
                iVar.f5533e = "recall";
                iVar.f5532d = this.f5858a.getString(f0.j.u1, strH);
                iVar.f5534f = "";
                iVar.f5535g = "";
                iVar.f5536h = 0;
                j0.l.m(this.f5858a, this.f5859b, this.f5861d);
                this.f5862e.notifyDataSetChanged();
                return;
            }
        }
    }

    @Override // g0.j.InterfaceC0042j
    public void e(g0.p pVar) {
        String str;
        if (pVar == null || !pVar.f5435c || (str = this.f5859b) == null || !str.equals(pVar.f5433a)) {
            return;
        }
        C0432a0.m().n(this.f5859b, pVar.f5434b, pVar.f5436d);
    }

    @Override // g0.j.InterfaceC0042j
    public void f(g0.n nVar) {
        String str = this.f5859b;
        if (str == null || !str.equals(nVar.f5422b)) {
            return;
        }
        String str2 = nVar.f5421a;
        if (str2 == null || !this.f5860c.contains(str2)) {
            j0.i iVar = new j0.i();
            iVar.f5529a = nVar.f5421a;
            iVar.f5530b = nVar.f5422b;
            iVar.f5531c = nVar.f5423c;
            iVar.f5532d = nVar.f5424d;
            iVar.f5533e = nVar.f5425e;
            iVar.f5534f = nVar.f5426f;
            iVar.f5535g = nVar.f5427g;
            iVar.f5536h = nVar.f5428h;
            iVar.f5537i = AbstractC0380g.g(nVar.f5429i);
            o0.B.f(iVar);
            String str3 = iVar.f5529a;
            if (str3 != null && str3.length() > 0) {
                this.f5860c.add(iVar.f5529a);
            }
            this.f5861d.add(iVar);
            N.i(this.f5861d, this.f5860c, 320);
            j0.l.m(this.f5858a, this.f5859b, this.f5861d);
            com.im.oldchat.ui.G g2 = this.f5862e;
            if (g2 != null) {
                g2.k0(iVar);
            }
            com.im.oldchat.ui.G g3 = this.f5862e;
            boolean z2 = g3 != null && g3.w0(iVar);
            C0432a0.m().k(this.f5859b, nVar.f5423c);
            com.im.oldchat.ui.G g4 = this.f5862e;
            if (g4 != null && !z2) {
                g4.notifyDataSetChanged();
            }
            boolean z3 = this.f5863f.getLastVisiblePosition() >= this.f5863f.getCount() + (-2);
            Runnable runnable = this.f5864g;
            if (runnable != null) {
                runnable.run();
            }
            if (z3) {
                int count = this.f5863f.getCount() - 1;
                if (count >= 0) {
                    this.f5863f.setSelection(count);
                }
                j0.j.b(this.f5858a, this.f5859b);
                Runnable runnable2 = this.f5865h;
                if (runnable2 != null) {
                    runnable2.run();
                }
            }
        }
    }

    @Override // g0.j.InterfaceC0042j
    public void g(boolean z2) {
        String str;
        if (this.f5858a == null || (str = this.f5859b) == null || str.isEmpty() || !o0.V.n(this.f5858a)) {
            return;
        }
        C0432a0 c0432a0M = C0432a0.m();
        if (z2) {
            c0432a0M.u(this.f5859b);
        } else {
            c0432a0M.s(this.f5858a, this.f5858a.getSharedPreferences("auth", 0).getString("access_token", ""), this.f5859b, true);
        }
    }

    public final String h(String str) {
        if (str == null || str.isEmpty()) {
            return "成员";
        }
        String strB = j0.x.b(this.f5858a, str);
        return (strB == null || strB.isEmpty()) ? str : strB;
    }

    @Override // g0.j.InterfaceC0042j
    public void a(g0.m mVar) {
    }

    @Override // g0.j.InterfaceC0042j
    public void b(g0.l lVar) {
    }

    @Override // g0.j.InterfaceC0042j
    public void c(String str, String str2, long j2) {
    }
}

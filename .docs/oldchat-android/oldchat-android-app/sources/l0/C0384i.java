package l0;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.im.oldchat.ui.ChatActivity;
import g0.j;
import java.util.HashSet;
import java.util.List;
import o0.C0432a0;

/* JADX INFO: renamed from: l0.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0384i implements j.InterfaceC0042j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ChatActivity f6077a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f6078b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f6079c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashSet f6080d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f6081e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final com.im.oldchat.ui.S f6082f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final RecyclerView f6083g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Runnable f6084h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Runnable f6085i;

    public C0384i(ChatActivity chatActivity, String str, String str2, HashSet hashSet, List list, com.im.oldchat.ui.S s2, RecyclerView recyclerView, Runnable runnable, Runnable runnable2) {
        this.f6077a = chatActivity;
        this.f6078b = str;
        this.f6079c = str2;
        this.f6080d = hashSet;
        this.f6081e = list;
        this.f6082f = s2;
        this.f6083g = recyclerView;
        this.f6084h = runnable;
        this.f6085i = runnable2;
    }

    @Override // g0.j.InterfaceC0042j
    public void a(g0.m mVar) {
        String str;
        if (mVar == null || (str = mVar.f5418a) == null || str.isEmpty() || !this.f6080d.contains(mVar.f5418a)) {
            return;
        }
        for (j0.k kVar : this.f6081e) {
            if (kVar != null && mVar.f5418a.equals(kVar.f5550a)) {
                kVar.f5554e = "recall";
                if (o0.F.c(this.f6077a, mVar.f5420c, this.f6079c)) {
                    kVar.f5553d = this.f6077a.getString(f0.j.w1);
                } else {
                    kVar.f5553d = this.f6077a.getString(f0.j.v1);
                }
                kVar.f5555f = "";
                kVar.f5556g = "";
                kVar.f5557h = 0;
                j0.l.l(this.f6077a, this.f6078b, this.f6081e);
                this.f6082f.g();
                return;
            }
        }
    }

    @Override // g0.j.InterfaceC0042j
    public void b(g0.l lVar) {
        String str = this.f6078b;
        if (str == null || !str.equals(lVar.f5410c)) {
            return;
        }
        String str2 = lVar.f5408a;
        if (str2 == null || !this.f6080d.contains(str2)) {
            j0.k kVar = new j0.k();
            kVar.f5550a = lVar.f5408a;
            kVar.f5552c = lVar.f5410c;
            kVar.f5553d = lVar.f5412e;
            kVar.f5554e = lVar.f5413f;
            kVar.f5555f = lVar.f5414g;
            kVar.f5556g = lVar.f5415h;
            kVar.f5557h = lVar.f5416i;
            kVar.f5558i = AbstractC0380g.g(lVar.f5417j);
            o0.B.d(kVar);
            String str3 = kVar.f5550a;
            if (str3 != null && str3.length() > 0) {
                this.f6080d.add(kVar.f5550a);
            }
            this.f6081e.add(kVar);
            AbstractC0394n.f(this.f6081e, this.f6080d, 320);
            AbstractC0380g.a(this.f6077a, this.f6081e, this.f6079c);
            j0.l.l(this.f6077a, this.f6078b, this.f6081e);
            com.im.oldchat.ui.S s2 = this.f6082f;
            if (s2 != null) {
                s2.o0(kVar);
            }
            com.im.oldchat.ui.S s3 = this.f6082f;
            boolean z2 = false;
            boolean z3 = s3 != null && s3.A0(kVar);
            C0432a0.m().k(this.f6078b, lVar.f5410c);
            com.im.oldchat.ui.S s4 = this.f6082f;
            if (s4 != null && !z3) {
                s4.g();
            }
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.f6083g.getLayoutManager();
            if (linearLayoutManager != null && linearLayoutManager.O1() >= this.f6082f.c() - 2) {
                z2 = true;
            }
            Runnable runnable = this.f6085i;
            if (runnable != null) {
                runnable.run();
            }
            if (z2) {
                this.f6083g.T0(this.f6081e.size() - 1);
                j0.r.c(this.f6077a, this.f6078b);
                Runnable runnable2 = this.f6084h;
                if (runnable2 != null) {
                    runnable2.run();
                }
            }
        }
    }

    @Override // g0.j.InterfaceC0042j
    public void c(String str, String str2, long j2) {
        String str3 = this.f6078b;
        if (str3 == null || !str3.equals(str2)) {
            return;
        }
        for (j0.k kVar : this.f6081e) {
            if (o0.F.c(this.f6077a, kVar.f5552c, this.f6079c)) {
                kVar.f5559j = 3;
            }
        }
        this.f6082f.g();
    }

    @Override // g0.j.InterfaceC0042j
    public void e(g0.p pVar) {
        String str;
        if (pVar == null || pVar.f5435c || (str = this.f6078b) == null || !str.equals(pVar.f5433a)) {
            return;
        }
        C0432a0.m().n(this.f6078b, pVar.f5434b, pVar.f5436d);
    }

    @Override // g0.j.InterfaceC0042j
    public void g(boolean z2) {
        String str;
        if (this.f6077a == null || (str = this.f6078b) == null || str.isEmpty() || !o0.V.n(this.f6077a)) {
            return;
        }
        C0432a0 c0432a0M = C0432a0.m();
        if (z2) {
            c0432a0M.u(this.f6078b);
        } else {
            c0432a0M.s(this.f6077a, this.f6077a.getSharedPreferences("auth", 0).getString("access_token", ""), this.f6078b, false);
        }
    }

    public void h(String str) {
        this.f6079c = str;
    }

    @Override // g0.j.InterfaceC0042j
    public void d(g0.o oVar) {
    }

    @Override // g0.j.InterfaceC0042j
    public void f(g0.n nVar) {
    }
}

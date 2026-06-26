package com.im.oldchat.ui;

import com.im.oldchat.ui.C0212c;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import l0.AbstractC0403s;
import o0.C;

/* JADX INFO: renamed from: com.im.oldchat.ui.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0234z implements C0212c.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f4887a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashSet f4888b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final G f4889c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final l0.D f4890d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final O f4891e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f4892f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final c f4893g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Map f4894h = new HashMap();

    /* JADX INFO: renamed from: com.im.oldchat.ui.z$b */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f4895a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f4896b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f4897c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public j0.i f4898d;

        public b() {
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.z$c */
    public interface c {
        C.d a();
    }

    public C0234z(List list, HashSet hashSet, G g2, l0.D d2, O o2, String str, c cVar) {
        this.f4887a = list;
        this.f4888b = hashSet;
        this.f4889c = g2;
        this.f4890d = d2;
        this.f4891e = o2;
        this.f4892f = str;
        this.f4893g = cVar;
    }

    private void e() {
        G g2 = this.f4889c;
        if (g2 != null) {
            g2.notifyDataSetChanged();
        }
        l0.D d2 = this.f4890d;
        if (d2 != null) {
            d2.n();
        }
    }

    private void f(String str) {
        j0.i iVar;
        b bVar = (b) this.f4894h.remove(str);
        if (bVar == null || (iVar = bVar.f4898d) == null) {
            return;
        }
        this.f4887a.remove(iVar);
        String str2 = bVar.f4898d.f5529a;
        if (str2 != null && str2.length() > 0) {
            this.f4888b.remove(bVar.f4898d.f5529a);
        }
        e();
    }

    @Override // com.im.oldchat.ui.C0212c.b
    public void a(String str, String str2, long j2, String str3) {
        b bVar = (b) this.f4894h.get(str);
        if (bVar == null || bVar.f4898d == null) {
            return;
        }
        if (str3 == null || str3.length() == 0) {
            str3 = "上传失败";
        }
        bVar.f4898d.f5532d = AbstractC0403s.a(bVar.f4896b, bVar.f4897c, str3.startsWith("上传失败") ? str3 : "上传失败");
        e();
    }

    @Override // com.im.oldchat.ui.C0212c.b
    public void b(String str, String str2, long j2, String str3) {
        j0.i iVar;
        b bVar = (b) this.f4894h.get(str);
        if (bVar != null && (iVar = bVar.f4898d) != null) {
            iVar.f5532d = AbstractC0403s.a(bVar.f4896b, bVar.f4897c, "发送中...");
            e();
        }
        f(str);
        if (this.f4891e == null || str3 == null || str3.length() == 0) {
            return;
        }
        c cVar = this.f4893g;
        this.f4891e.p(str3, str2, j2, cVar == null ? null : cVar.a());
    }

    @Override // com.im.oldchat.ui.C0212c.b
    public void c(String str, String str2, long j2) {
        b bVar = new b();
        bVar.f4895a = str;
        bVar.f4896b = str2;
        bVar.f4897c = j2;
        j0.i iVar = new j0.i();
        bVar.f4898d = iVar;
        iVar.f5529a = str;
        iVar.f5531c = this.f4892f;
        iVar.f5533e = "resource";
        iVar.f5534f = "";
        iVar.f5535g = "";
        iVar.f5536h = 0;
        iVar.f5537i = System.currentTimeMillis() / 1000;
        j0.i iVar2 = bVar.f4898d;
        iVar2.f5538j = 0;
        iVar2.f5532d = AbstractC0403s.a(str2, j2, AbstractC0403s.c(0));
        this.f4894h.put(str, bVar);
        this.f4887a.add(bVar.f4898d);
        if (str != null && str.length() > 0) {
            this.f4888b.add(str);
        }
        e();
    }

    @Override // com.im.oldchat.ui.C0212c.b
    public void d(String str, String str2, long j2, int i2) {
        j0.i iVar;
        b bVar = (b) this.f4894h.get(str);
        if (bVar == null || (iVar = bVar.f4898d) == null) {
            return;
        }
        iVar.f5532d = AbstractC0403s.a(bVar.f4896b, bVar.f4897c, AbstractC0403s.c(i2));
        e();
    }
}

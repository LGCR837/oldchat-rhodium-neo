package com.im.oldchat.ui;

import com.im.oldchat.ui.C0212c;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import l0.AbstractC0403s;
import l0.C0386j;
import o0.C;

/* JADX INFO: renamed from: com.im.oldchat.ui.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0225p implements C0212c.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f4704a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashSet f4705b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final S f4706c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final C0386j f4707d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0226q f4708e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f4709f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final c f4710g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Map f4711h = new HashMap();

    /* JADX INFO: renamed from: com.im.oldchat.ui.p$b */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f4712a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f4713b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f4714c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public j0.k f4715d;

        public b() {
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.p$c */
    public interface c {
        C.d a();
    }

    public C0225p(List list, HashSet hashSet, S s2, C0386j c0386j, C0226q c0226q, String str, c cVar) {
        this.f4704a = list;
        this.f4705b = hashSet;
        this.f4706c = s2;
        this.f4707d = c0386j;
        this.f4708e = c0226q;
        this.f4709f = str;
        this.f4710g = cVar;
    }

    @Override // com.im.oldchat.ui.C0212c.b
    public void a(String str, String str2, long j2, String str3) {
        b bVar = (b) this.f4711h.get(str);
        if (bVar == null || bVar.f4715d == null) {
            return;
        }
        if (str3 == null || str3.length() == 0) {
            str3 = "上传失败";
        }
        bVar.f4715d.f5553d = AbstractC0403s.a(bVar.f4713b, bVar.f4714c, str3.startsWith("上传失败") ? str3 : "上传失败");
        e();
    }

    @Override // com.im.oldchat.ui.C0212c.b
    public void b(String str, String str2, long j2, String str3) {
        j0.k kVar;
        b bVar = (b) this.f4711h.get(str);
        if (bVar != null && (kVar = bVar.f4715d) != null) {
            kVar.f5553d = AbstractC0403s.a(bVar.f4713b, bVar.f4714c, "发送中...");
            e();
        }
        f(str);
        if (this.f4708e == null || str3 == null || str3.length() == 0) {
            return;
        }
        c cVar = this.f4710g;
        this.f4708e.l(str3, str2, j2, cVar == null ? null : cVar.a());
    }

    @Override // com.im.oldchat.ui.C0212c.b
    public void c(String str, String str2, long j2) {
        b bVar = new b();
        bVar.f4712a = str;
        bVar.f4713b = str2;
        bVar.f4714c = j2;
        j0.k kVar = new j0.k();
        bVar.f4715d = kVar;
        kVar.f5550a = str;
        kVar.f5552c = this.f4709f;
        kVar.f5554e = "resource";
        kVar.f5555f = "";
        kVar.f5556g = "";
        kVar.f5557h = 0;
        kVar.f5558i = System.currentTimeMillis() / 1000;
        bVar.f4715d.f5553d = AbstractC0403s.a(str2, j2, AbstractC0403s.c(0));
        this.f4711h.put(str, bVar);
        this.f4704a.add(bVar.f4715d);
        if (str != null && str.length() > 0) {
            this.f4705b.add(str);
        }
        e();
    }

    @Override // com.im.oldchat.ui.C0212c.b
    public void d(String str, String str2, long j2, int i2) {
        j0.k kVar;
        b bVar = (b) this.f4711h.get(str);
        if (bVar == null || (kVar = bVar.f4715d) == null) {
            return;
        }
        kVar.f5553d = AbstractC0403s.a(bVar.f4713b, bVar.f4714c, AbstractC0403s.c(i2));
        e();
    }

    public final void e() {
        S s2 = this.f4706c;
        if (s2 != null) {
            s2.g();
        }
        C0386j c0386j = this.f4707d;
        if (c0386j != null) {
            c0386j.n();
        }
    }

    public final void f(String str) {
        j0.k kVar;
        b bVar = (b) this.f4711h.remove(str);
        if (bVar == null || (kVar = bVar.f4715d) == null) {
            return;
        }
        this.f4704a.remove(kVar);
        String str2 = bVar.f4715d.f5550a;
        if (str2 != null && str2.length() > 0) {
            this.f4705b.remove(bVar.f4715d.f5550a);
        }
        e();
    }
}

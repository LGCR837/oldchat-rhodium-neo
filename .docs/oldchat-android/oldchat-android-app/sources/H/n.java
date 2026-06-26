package H;

import java.util.EnumMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f299a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final byte[] f300b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f301c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public p[] f302d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a f303e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Map f304f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f305g;

    public n(String str, byte[] bArr, p[] pVarArr, a aVar) {
        this(str, bArr, pVarArr, aVar, System.currentTimeMillis());
    }

    public void a(p[] pVarArr) {
        p[] pVarArr2 = this.f302d;
        if (pVarArr2 == null) {
            this.f302d = pVarArr;
            return;
        }
        if (pVarArr == null || pVarArr.length <= 0) {
            return;
        }
        p[] pVarArr3 = new p[pVarArr2.length + pVarArr.length];
        System.arraycopy(pVarArr2, 0, pVarArr3, 0, pVarArr2.length);
        System.arraycopy(pVarArr, 0, pVarArr3, pVarArr2.length, pVarArr.length);
        this.f302d = pVarArr3;
    }

    public a b() {
        return this.f303e;
    }

    public byte[] c() {
        return this.f300b;
    }

    public Map d() {
        return this.f304f;
    }

    public p[] e() {
        return this.f302d;
    }

    public String f() {
        return this.f299a;
    }

    public void g(Map map) {
        if (map != null) {
            Map map2 = this.f304f;
            if (map2 == null) {
                this.f304f = map;
            } else {
                map2.putAll(map);
            }
        }
    }

    public void h(o oVar, Object obj) {
        if (this.f304f == null) {
            this.f304f = new EnumMap(o.class);
        }
        this.f304f.put(oVar, obj);
    }

    public String toString() {
        return this.f299a;
    }

    public n(String str, byte[] bArr, p[] pVarArr, a aVar, long j2) {
        this(str, bArr, bArr == null ? 0 : bArr.length * 8, pVarArr, aVar, j2);
    }

    public n(String str, byte[] bArr, int i2, p[] pVarArr, a aVar, long j2) {
        this.f299a = str;
        this.f300b = bArr;
        this.f301c = i2;
        this.f302d = pVarArr;
        this.f303e = aVar;
        this.f304f = null;
        this.f305g = j2;
    }
}

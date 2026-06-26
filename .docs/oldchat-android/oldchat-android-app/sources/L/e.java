package L;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f387a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f388b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f389c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f390d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f391e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Integer f392f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Integer f393g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Object f394h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f395i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f396j;

    public e(byte[] bArr, String str, List list, String str2) {
        this(bArr, str, list, str2, -1, -1);
    }

    public List a() {
        return this.f390d;
    }

    public String b() {
        return this.f391e;
    }

    public int c() {
        return this.f388b;
    }

    public Object d() {
        return this.f394h;
    }

    public byte[] e() {
        return this.f387a;
    }

    public int f() {
        return this.f395i;
    }

    public int g() {
        return this.f396j;
    }

    public String h() {
        return this.f389c;
    }

    public boolean i() {
        return this.f395i >= 0 && this.f396j >= 0;
    }

    public void j(Integer num) {
        this.f393g = num;
    }

    public void k(Integer num) {
        this.f392f = num;
    }

    public void l(int i2) {
        this.f388b = i2;
    }

    public void m(Object obj) {
        this.f394h = obj;
    }

    public e(byte[] bArr, String str, List list, String str2, int i2, int i3) {
        this.f387a = bArr;
        this.f388b = bArr == null ? 0 : bArr.length * 8;
        this.f389c = str;
        this.f390d = list;
        this.f391e = str2;
        this.f395i = i3;
        this.f396j = i2;
    }
}

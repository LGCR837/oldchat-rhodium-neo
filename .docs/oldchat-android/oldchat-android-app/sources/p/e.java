package P;

import H.g;
import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final e[] f464h = a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f465a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f466b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f467c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f468d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f469e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final c f470f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f471g;

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f472a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f473b;

        public int a() {
            return this.f472a;
        }

        public int b() {
            return this.f473b;
        }

        public b(int i2, int i3) {
            this.f472a = i2;
            this.f473b = i3;
        }
    }

    public e(int i2, int i3, int i4, int i5, int i6, c cVar) {
        this.f465a = i2;
        this.f466b = i3;
        this.f467c = i4;
        this.f468d = i5;
        this.f469e = i6;
        this.f470f = cVar;
        int iB = cVar.b();
        int iA = 0;
        for (b bVar : cVar.a()) {
            iA += bVar.a() * (bVar.b() + iB);
        }
        this.f471g = iA;
    }

    public static e[] a() {
        int i2 = 1;
        int i3 = 5;
        e eVar = new e(1, 10, 10, 8, 8, new c(i3, new b(i2, 3)));
        int i4 = 12;
        e eVar2 = new e(2, 12, 12, 10, 10, new c(7, new b(i2, i3)));
        e eVar3 = new e(3, 14, 14, 12, 12, new c(10, new b(i2, 8)));
        e eVar4 = new e(4, 16, 16, 14, 14, new c(i4, new b(i2, i4)));
        int i5 = 18;
        e eVar5 = new e(5, 18, 18, 16, 16, new c(14, new b(i2, i5)));
        e eVar6 = new e(6, 20, 20, 18, 18, new c(i5, new b(i2, 22)));
        e eVar7 = new e(7, 22, 22, 20, 20, new c(20, new b(i2, 30)));
        int i6 = 36;
        e eVar8 = new e(8, 24, 24, 22, 22, new c(24, new b(i2, i6)));
        e eVar9 = new e(9, 26, 26, 24, 24, new c(28, new b(i2, 44)));
        e eVar10 = new e(10, 32, 32, 14, 14, new c(i6, new b(i2, 62)));
        e eVar11 = new e(11, 36, 36, 16, 16, new c(42, new b(i2, 86)));
        e eVar12 = new e(12, 40, 40, 18, 18, new c(48, new b(i2, 114)));
        e eVar13 = new e(13, 44, 44, 20, 20, new c(56, new b(i2, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA)));
        e eVar14 = new e(14, 48, 48, 22, 22, new c(68, new b(i2, CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256)));
        int i7 = 2;
        e eVar15 = new e(15, 52, 52, 24, 24, new c(42, new b(i7, 102)));
        e eVar16 = new e(16, 64, 64, 14, 14, new c(56, new b(i7, CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA)));
        int i8 = 4;
        e eVar17 = new e(17, 72, 72, 16, 16, new c(36, new b(i8, 92)));
        e eVar18 = new e(18, 80, 80, 18, 18, new c(48, new b(i8, 114)));
        e eVar19 = new e(19, 88, 88, 20, 20, new c(56, new b(i8, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA)));
        e eVar20 = new e(20, 96, 96, 22, 22, new c(68, new b(i8, CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256)));
        int i9 = 6;
        return new e[]{eVar, eVar2, eVar3, eVar4, eVar5, eVar6, eVar7, eVar8, eVar9, eVar10, eVar11, eVar12, eVar13, eVar14, eVar15, eVar16, eVar17, eVar18, eVar19, eVar20, new e(21, 104, 104, 24, 24, new c(56, new b(i9, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA))), new e(22, 120, 120, 18, 18, new c(68, new b(i9, CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384))), new e(23, CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA, 20, 20, new c(62, new b(8, CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384))), new e(24, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA, 22, 22, new c(62, new b(8, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256), new b(2, CipherSuite.TLS_DH_anon_WITH_SEED_CBC_SHA))), new e(25, 8, 18, 6, 16, new c(7, new b(1, 5))), new e(26, 8, 32, 6, 14, new c(11, new b(1, 10))), new e(27, 12, 26, 10, 24, new c(14, new b(1, 16))), new e(28, 12, 36, 10, 16, new c(18, new b(1, 22))), new e(29, 16, 36, 14, 16, new c(24, new b(1, 32))), new e(30, 16, 48, 14, 22, new c(28, new b(1, 49)))};
    }

    public static e h(int i2, int i3) throws g {
        if ((i2 & 1) != 0 || (i3 & 1) != 0) {
            throw g.a();
        }
        for (e eVar : f464h) {
            if (eVar.f466b == i2 && eVar.f467c == i3) {
                return eVar;
            }
        }
        throw g.a();
    }

    public int b() {
        return this.f469e;
    }

    public int c() {
        return this.f468d;
    }

    public c d() {
        return this.f470f;
    }

    public int e() {
        return this.f467c;
    }

    public int f() {
        return this.f466b;
    }

    public int g() {
        return this.f471g;
    }

    public int i() {
        return this.f465a;
    }

    public String toString() {
        return String.valueOf(this.f465a);
    }

    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f474a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final b[] f475b;

        public b[] a() {
            return this.f475b;
        }

        public int b() {
            return this.f474a;
        }

        public c(int i2, b bVar) {
            this.f474a = i2;
            this.f475b = new b[]{bVar};
        }

        public c(int i2, b bVar, b bVar2) {
            this.f474a = i2;
            this.f475b = new b[]{bVar, bVar2};
        }
    }
}

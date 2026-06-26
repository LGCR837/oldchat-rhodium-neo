package c0;

import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f2118e = {31892, 34236, 39577, 42195, 48118, 51042, 55367, 58893, 63784, 68472, 70749, 76311, 79154, 84390, 87683, 92361, 96236, 102084, 102881, 110507, 110734, 117786, 119615, 126325, 127568, 133589, 136944, 141498, 145311, 150283, 152622, 158308, 161089, 167017};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final j[] f2119f = b();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2120a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f2121b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b[] f2122c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2123d;

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f2124a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f2125b;

        public a(int i2, int i3) {
            this.f2124a = i2;
            this.f2125b = i3;
        }

        public int a() {
            return this.f2124a;
        }

        public int b() {
            return this.f2125b;
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f2126a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final a[] f2127b;

        public b(int i2, a... aVarArr) {
            this.f2126a = i2;
            this.f2127b = aVarArr;
        }

        public a[] a() {
            return this.f2127b;
        }

        public int b() {
            return this.f2126a;
        }

        public int c() {
            int iA = 0;
            for (a aVar : this.f2127b) {
                iA += aVar.a();
            }
            return iA;
        }

        public int d() {
            return this.f2126a * c();
        }
    }

    public j(int i2, int[] iArr, b... bVarArr) {
        this.f2120a = i2;
        this.f2121b = iArr;
        this.f2122c = bVarArr;
        int iB = bVarArr[0].b();
        int iA = 0;
        for (a aVar : bVarArr[0].a()) {
            iA += aVar.a() * (aVar.b() + iB);
        }
        this.f2123d = iA;
    }

    public static j[] b() {
        return new j[]{new j(1, new int[0], new b(7, new a(1, 19)), new b(10, new a(1, 16)), new b(13, new a(1, 13)), new b(17, new a(1, 9))), new j(2, new int[]{6, 18}, new b(10, new a(1, 34)), new b(16, new a(1, 28)), new b(22, new a(1, 22)), new b(28, new a(1, 16))), new j(3, new int[]{6, 22}, new b(15, new a(1, 55)), new b(26, new a(1, 44)), new b(18, new a(2, 17)), new b(22, new a(2, 13))), new j(4, new int[]{6, 26}, new b(20, new a(1, 80)), new b(18, new a(2, 32)), new b(26, new a(2, 24)), new b(16, new a(4, 9))), new j(5, new int[]{6, 30}, new b(26, new a(1, CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA256)), new b(24, new a(2, 43)), new b(18, new a(2, 15), new a(2, 16)), new b(22, new a(2, 11), new a(2, 12))), new j(6, new int[]{6, 34}, new b(18, new a(2, 68)), new b(16, new a(4, 27)), new b(24, new a(4, 19)), new b(28, new a(4, 15))), new j(7, new int[]{6, 22, 38}, new b(20, new a(2, 78)), new b(18, new a(4, 31)), new b(18, new a(2, 14), new a(4, 15)), new b(26, new a(4, 13), new a(1, 14))), new j(8, new int[]{6, 24, 42}, new b(24, new a(2, 97)), new b(22, new a(2, 38), new a(2, 39)), new b(22, new a(4, 18), new a(2, 19)), new b(26, new a(4, 14), new a(2, 15))), new j(9, new int[]{6, 26, 46}, new b(30, new a(2, 116)), new b(22, new a(3, 36), new a(2, 37)), new b(20, new a(4, 16), new a(4, 17)), new b(24, new a(4, 12), new a(4, 13))), new j(10, new int[]{6, 28, 50}, new b(18, new a(2, 68), new a(2, 69)), new b(26, new a(4, 43), new a(1, 44)), new b(24, new a(6, 19), new a(2, 20)), new b(28, new a(6, 15), new a(2, 16))), new j(11, new int[]{6, 30, 54}, new b(20, new a(4, 81)), new b(30, new a(1, 50), new a(4, 51)), new b(28, new a(4, 22), new a(4, 23)), new b(24, new a(3, 12), new a(8, 13))), new j(12, new int[]{6, 32, 58}, new b(24, new a(2, 92), new a(2, 93)), new b(22, new a(6, 36), new a(2, 37)), new b(26, new a(4, 20), new a(6, 21)), new b(28, new a(7, 14), new a(4, 15))), new j(13, new int[]{6, 34, 62}, new b(26, new a(4, CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA256)), new b(22, new a(8, 37), new a(1, 38)), new b(24, new a(8, 20), new a(4, 21)), new b(22, new a(12, 11), new a(4, 12))), new j(14, new int[]{6, 26, 46, 66}, new b(30, new a(3, 115), new a(1, 116)), new b(24, new a(4, 40), new a(5, 41)), new b(20, new a(11, 16), new a(5, 17)), new b(24, new a(11, 12), new a(5, 13))), new j(15, new int[]{6, 26, 48, 70}, new b(22, new a(5, 87), new a(1, 88)), new b(24, new a(5, 41), new a(5, 42)), new b(30, new a(5, 24), new a(7, 25)), new b(24, new a(11, 12), new a(7, 13))), new j(16, new int[]{6, 26, 50, 74}, new b(24, new a(5, 98), new a(1, 99)), new b(28, new a(7, 45), new a(3, 46)), new b(24, new a(15, 19), new a(2, 20)), new b(30, new a(3, 15), new a(13, 16))), new j(17, new int[]{6, 30, 54, 78}, new b(28, new a(1, CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA256), new a(5, CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA256)), new b(28, new a(10, 46), new a(1, 47)), new b(28, new a(1, 22), new a(15, 23)), new b(28, new a(2, 14), new a(17, 15))), new j(18, new int[]{6, 30, 56, 82}, new b(30, new a(5, 120), new a(1, 121)), new b(26, new a(9, 43), new a(4, 44)), new b(28, new a(17, 22), new a(1, 23)), new b(28, new a(2, 14), new a(19, 15))), new j(19, new int[]{6, 30, 58, 86}, new b(28, new a(3, 113), new a(4, 114)), new b(26, new a(3, 44), new a(11, 45)), new b(26, new a(17, 21), new a(4, 22)), new b(26, new a(9, 13), new a(16, 14))), new j(20, new int[]{6, 34, 62, 90}, new b(28, new a(3, CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA256), new a(5, CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA256)), new b(26, new a(3, 41), new a(13, 42)), new b(30, new a(15, 24), new a(5, 25)), new b(28, new a(15, 15), new a(10, 16))), new j(21, new int[]{6, 28, 50, 72, 94}, new b(28, new a(4, 116), new a(4, 117)), new b(26, new a(17, 42)), new b(28, new a(17, 22), new a(6, 23)), new b(30, new a(19, 16), new a(6, 17))), new j(22, new int[]{6, 26, 50, 74, 98}, new b(28, new a(2, 111), new a(7, 112)), new b(28, new a(17, 46)), new b(30, new a(7, 24), new a(16, 25)), new b(24, new a(34, 13))), new j(23, new int[]{6, 30, 54, 78, 102}, new b(30, new a(4, 121), new a(5, 122)), new b(28, new a(4, 47), new a(14, 48)), new b(30, new a(11, 24), new a(14, 25)), new b(30, new a(16, 15), new a(14, 16))), new j(24, new int[]{6, 28, 54, 80, CipherSuite.TLS_DHE_DSS_WITH_AES_256_CBC_SHA256}, new b(30, new a(6, 117), new a(4, 118)), new b(28, new a(6, 45), new a(14, 46)), new b(30, new a(11, 24), new a(16, 25)), new b(30, new a(30, 16), new a(2, 17))), new j(25, new int[]{6, 32, 58, 84, 110}, new b(26, new a(8, CipherSuite.TLS_DHE_DSS_WITH_AES_256_CBC_SHA256), new a(4, CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA256)), new b(28, new a(8, 47), new a(13, 48)), new b(30, new a(7, 24), new a(22, 25)), new b(30, new a(22, 15), new a(13, 16))), new j(26, new int[]{6, 30, 58, 86, 114}, new b(28, new a(10, 114), new a(2, 115)), new b(28, new a(19, 46), new a(4, 47)), new b(28, new a(28, 22), new a(6, 23)), new b(30, new a(33, 16), new a(4, 17))), new j(27, new int[]{6, 34, 62, 90, 118}, new b(30, new a(8, 122), new a(4, 123)), new b(28, new a(22, 45), new a(3, 46)), new b(30, new a(8, 23), new a(26, 24)), new b(30, new a(12, 15), new a(28, 16))), new j(28, new int[]{6, 26, 50, 74, 98, 122}, new b(30, new a(3, 117), new a(10, 118)), new b(28, new a(3, 45), new a(23, 46)), new b(30, new a(4, 24), new a(31, 25)), new b(30, new a(11, 15), new a(31, 16))), new j(29, new int[]{6, 30, 54, 78, 102, 126}, new b(30, new a(7, 116), new a(7, 117)), new b(28, new a(21, 45), new a(7, 46)), new b(30, new a(1, 23), new a(37, 24)), new b(30, new a(19, 15), new a(26, 16))), new j(30, new int[]{6, 26, 52, 78, 104, 130}, new b(30, new a(5, 115), new a(10, 116)), new b(28, new a(19, 47), new a(10, 48)), new b(30, new a(15, 24), new a(25, 25)), new b(30, new a(23, 15), new a(25, 16))), new j(31, new int[]{6, 30, 56, 82, CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA256, CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA}, new b(30, new a(13, 115), new a(3, 116)), new b(28, new a(2, 46), new a(29, 47)), new b(30, new a(42, 24), new a(1, 25)), new b(30, new a(23, 15), new a(28, 16))), new j(32, new int[]{6, 34, 60, 86, 112, CipherSuite.TLS_PSK_WITH_RC4_128_SHA}, new b(30, new a(17, 115)), new b(28, new a(10, 46), new a(23, 47)), new b(30, new a(10, 24), new a(35, 25)), new b(30, new a(19, 15), new a(35, 16))), new j(33, new int[]{6, 30, 58, 86, 114, CipherSuite.TLS_DHE_PSK_WITH_RC4_128_SHA}, new b(30, new a(17, 115), new a(1, 116)), new b(28, new a(14, 46), new a(21, 47)), new b(30, new a(29, 24), new a(19, 25)), new b(30, new a(11, 15), new a(46, 16))), new j(34, new int[]{6, 34, 62, 90, 118, CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA}, new b(30, new a(13, 115), new a(6, 116)), new b(28, new a(14, 46), new a(23, 47)), new b(30, new a(44, 24), new a(7, 25)), new b(30, new a(59, 16), new a(1, 17))), new j(35, new int[]{6, 30, 54, 78, 102, 126, CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA}, new b(30, new a(12, 121), new a(7, 122)), new b(28, new a(12, 47), new a(26, 48)), new b(30, new a(39, 24), new a(14, 25)), new b(30, new a(22, 15), new a(41, 16))), new j(36, new int[]{6, 24, 50, 76, 102, 128, CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA}, new b(30, new a(6, 121), new a(14, 122)), new b(28, new a(6, 47), new a(34, 48)), new b(30, new a(46, 24), new a(10, 25)), new b(30, new a(2, 15), new a(64, 16))), new j(37, new int[]{6, 28, 54, 80, CipherSuite.TLS_DHE_DSS_WITH_AES_256_CBC_SHA256, CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256}, new b(30, new a(17, 122), new a(4, 123)), new b(28, new a(29, 46), new a(14, 47)), new b(30, new a(49, 24), new a(10, 25)), new b(30, new a(24, 15), new a(46, 16))), new j(38, new int[]{6, 32, 58, 84, 110, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA, CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256}, new b(30, new a(4, 122), new a(18, 123)), new b(28, new a(13, 46), new a(32, 47)), new b(30, new a(48, 24), new a(14, 25)), new b(30, new a(42, 15), new a(32, 16))), new j(39, new int[]{6, 26, 54, 82, 110, CipherSuite.TLS_PSK_WITH_RC4_128_SHA, CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256}, new b(30, new a(20, 117), new a(4, 118)), new b(28, new a(40, 47), new a(7, 48)), new b(30, new a(43, 24), new a(22, 25)), new b(30, new a(10, 15), new a(67, 16))), new j(40, new int[]{6, 30, 58, 86, 114, CipherSuite.TLS_DHE_PSK_WITH_RC4_128_SHA, CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256}, new b(30, new a(19, 118), new a(6, 119)), new b(28, new a(18, 47), new a(31, 48)), new b(30, new a(34, 24), new a(34, 25)), new b(30, new a(20, 15), new a(61, 16)))};
    }

    public static j c(int i2) {
        int i3 = Integer.MAX_VALUE;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            int[] iArr = f2118e;
            if (i4 >= iArr.length) {
                if (i3 <= 3) {
                    return i(i5);
                }
                return null;
            }
            int i6 = iArr[i4];
            if (i6 == i2) {
                return i(i4 + 7);
            }
            int iE = g.e(i2, i6);
            if (iE < i3) {
                i5 = i4 + 7;
                i3 = iE;
            }
            i4++;
        }
    }

    public static j g(int i2) throws H.g {
        if (i2 % 4 != 1) {
            throw H.g.a();
        }
        try {
            return i((i2 - 17) / 4);
        } catch (IllegalArgumentException unused) {
            throw H.g.a();
        }
    }

    public static j i(int i2) {
        if (i2 <= 0 || i2 > 40) {
            throw new IllegalArgumentException();
        }
        return f2119f[i2 - 1];
    }

    public L.b a() {
        int iE = e();
        L.b bVar = new L.b(iE);
        bVar.m(0, 0, 9, 9);
        int i2 = iE - 8;
        bVar.m(i2, 0, 8, 9);
        bVar.m(0, i2, 9, 8);
        int length = this.f2121b.length;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = this.f2121b[i3] - 2;
            for (int i5 = 0; i5 < length; i5++) {
                if ((i3 != 0 || (i5 != 0 && i5 != length - 1)) && (i3 != length - 1 || i5 != 0)) {
                    bVar.m(this.f2121b[i5] - 2, i4, 5, 5);
                }
            }
        }
        int i6 = iE - 17;
        bVar.m(6, 9, 1, i6);
        bVar.m(9, 6, i6, 1);
        if (this.f2120a > 6) {
            int i7 = iE - 11;
            bVar.m(i7, 0, 3, 6);
            bVar.m(0, i7, 6, 3);
        }
        return bVar;
    }

    public int[] d() {
        return this.f2121b;
    }

    public int e() {
        return (this.f2120a * 4) + 17;
    }

    public b f(f fVar) {
        return this.f2122c[fVar.ordinal()];
    }

    public int h() {
        return this.f2123d;
    }

    public int j() {
        return this.f2120a;
    }

    public String toString() {
        return String.valueOf(this.f2120a);
    }
}

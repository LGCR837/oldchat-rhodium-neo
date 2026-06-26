package T;

import java.util.Arrays;
import java.util.Map;
import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: loaded from: classes.dex */
public final class c extends k {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f494e = {52, 289, 97, 352, 49, 304, 112, 37, 292, 100, 265, 73, 328, 25, 280, 88, 13, 268, 76, 28, 259, 67, 322, 19, 274, 82, 7, 262, 70, 22, 385, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, 448, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA, 400, 208, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA, 388, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256, CipherSuite.TLS_PSK_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_PSK_WITH_RC4_128_SHA, 42};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f495a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f496b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final StringBuilder f497c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int[] f498d;

    public c() {
        this(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String g(java.lang.CharSequence r12) throws H.g {
        /*
            int r0 = r12.length()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
            r2 = 0
            r3 = 0
        Lb:
            if (r3 >= r0) goto Lbf
            char r4 = r12.charAt(r3)
            r5 = 47
            r6 = 37
            r7 = 36
            r8 = 43
            if (r4 == r8) goto L27
            if (r4 == r7) goto L27
            if (r4 == r6) goto L27
            if (r4 != r5) goto L22
            goto L27
        L22:
            r1.append(r4)
            goto Lb6
        L27:
            int r3 = r3 + 1
            char r9 = r12.charAt(r3)
            r10 = 90
            r11 = 65
            if (r4 == r7) goto Lac
            r7 = 79
            if (r4 == r6) goto L5e
            if (r4 == r8) goto L52
            if (r4 == r5) goto L3e
        L3b:
            r4 = 0
            goto Lb3
        L3e:
            if (r9 < r11) goto L47
            if (r9 > r7) goto L47
            int r9 = r9 + (-32)
        L44:
            char r4 = (char) r9
            goto Lb3
        L47:
            if (r9 != r10) goto L4d
            r4 = 58
            goto Lb3
        L4d:
            H.g r12 = H.g.a()
            throw r12
        L52:
            if (r9 < r11) goto L59
            if (r9 > r10) goto L59
            int r9 = r9 + 32
            goto L44
        L59:
            H.g r12 = H.g.a()
            throw r12
        L5e:
            if (r9 < r11) goto L67
            r4 = 69
            if (r9 > r4) goto L67
            int r9 = r9 + (-38)
            goto L44
        L67:
            r4 = 70
            if (r9 < r4) goto L72
            r4 = 74
            if (r9 > r4) goto L72
            int r9 = r9 + (-11)
            goto L44
        L72:
            r4 = 75
            if (r9 < r4) goto L7b
            if (r9 > r7) goto L7b
            int r9 = r9 + 16
            goto L44
        L7b:
            r4 = 80
            if (r9 < r4) goto L86
            r4 = 84
            if (r9 > r4) goto L86
            int r9 = r9 + 43
            goto L44
        L86:
            r4 = 85
            if (r9 != r4) goto L8b
            goto L3b
        L8b:
            r4 = 86
            if (r9 != r4) goto L92
            r4 = 64
            goto Lb3
        L92:
            r4 = 87
            if (r9 != r4) goto L99
            r4 = 96
            goto Lb3
        L99:
            r4 = 88
            if (r9 == r4) goto La9
            r4 = 89
            if (r9 == r4) goto La9
            if (r9 != r10) goto La4
            goto La9
        La4:
            H.g r12 = H.g.a()
            throw r12
        La9:
            r4 = 127(0x7f, float:1.78E-43)
            goto Lb3
        Lac:
            if (r9 < r11) goto Lba
            if (r9 > r10) goto Lba
            int r9 = r9 + (-64)
            goto L44
        Lb3:
            r1.append(r4)
        Lb6:
            int r3 = r3 + 1
            goto Lb
        Lba:
            H.g r12 = H.g.a()
            throw r12
        Lbf:
            java.lang.String r12 = r1.toString()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: T.c.g(java.lang.CharSequence):java.lang.String");
    }

    public static int[] h(L.a aVar, int[] iArr) throws H.j {
        int iK = aVar.k();
        int i2 = aVar.i(0);
        int length = iArr.length;
        int i3 = i2;
        boolean z2 = false;
        int i4 = 0;
        while (i2 < iK) {
            if (aVar.g(i2) != z2) {
                iArr[i4] = iArr[i4] + 1;
            } else {
                if (i4 != length - 1) {
                    i4++;
                } else {
                    if (j(iArr) == 148 && aVar.m(Math.max(0, i3 - ((i2 - i3) / 2)), i3, false)) {
                        return new int[]{i3, i2};
                    }
                    i3 += iArr[0] + iArr[1];
                    int i5 = i4 - 1;
                    System.arraycopy(iArr, 2, iArr, 0, i5);
                    iArr[i5] = 0;
                    iArr[i4] = 0;
                    i4--;
                }
                iArr[i4] = 1;
                z2 = !z2;
            }
            i2++;
        }
        throw H.j.a();
    }

    public static char i(int i2) throws H.j {
        int i3 = 0;
        while (true) {
            int[] iArr = f494e;
            if (i3 >= iArr.length) {
                if (i2 == 148) {
                    return '*';
                }
                throw H.j.a();
            }
            if (iArr[i3] == i2) {
                return "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".charAt(i3);
            }
            i3++;
        }
    }

    public static int j(int[] iArr) {
        int length = iArr.length;
        int i2 = 0;
        while (true) {
            int i3 = Integer.MAX_VALUE;
            for (int i4 : iArr) {
                if (i4 < i3 && i4 > i2) {
                    i3 = i4;
                }
            }
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            for (int i8 = 0; i8 < length; i8++) {
                int i9 = iArr[i8];
                if (i9 > i3) {
                    i6 |= 1 << ((length - 1) - i8);
                    i5++;
                    i7 += i9;
                }
            }
            if (i5 == 3) {
                for (int i10 = 0; i10 < length && i5 > 0; i10++) {
                    int i11 = iArr[i10];
                    if (i11 > i3) {
                        i5--;
                        if ((i11 << 1) >= i7) {
                            return -1;
                        }
                    }
                }
                return i6;
            }
            if (i5 <= 3) {
                return -1;
            }
            i2 = i3;
        }
    }

    @Override // T.k
    public H.n b(int i2, L.a aVar, Map map) throws H.j, H.d {
        int[] iArr = this.f498d;
        Arrays.fill(iArr, 0);
        StringBuilder sb = this.f497c;
        sb.setLength(0);
        int i3 = aVar.i(h(aVar, iArr)[1]);
        int iK = aVar.k();
        while (true) {
            k.e(aVar, i3, iArr);
            int iJ = j(iArr);
            if (iJ < 0) {
                throw H.j.a();
            }
            char cI = i(iJ);
            sb.append(cI);
            int i4 = i3;
            for (int i5 : iArr) {
                i4 += i5;
            }
            int i6 = aVar.i(i4);
            if (cI == '*') {
                sb.setLength(sb.length() - 1);
                int i7 = 0;
                for (int i8 : iArr) {
                    i7 += i8;
                }
                int i9 = (i6 - i3) - i7;
                if (i6 != iK && (i9 << 1) < i7) {
                    throw H.j.a();
                }
                if (this.f495a) {
                    int length = sb.length() - 1;
                    int iIndexOf = 0;
                    for (int i10 = 0; i10 < length; i10++) {
                        iIndexOf += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".indexOf(this.f497c.charAt(i10));
                    }
                    if (sb.charAt(length) != "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".charAt(iIndexOf % 43)) {
                        throw H.d.a();
                    }
                    sb.setLength(length);
                }
                if (sb.length() == 0) {
                    throw H.j.a();
                }
                float f2 = i2;
                return new H.n(this.f496b ? g(sb) : sb.toString(), null, new H.p[]{new H.p((r2[1] + r2[0]) / 2.0f, f2), new H.p(i3 + (i7 / 2.0f), f2)}, H.a.CODE_39);
            }
            i3 = i6;
        }
    }

    public c(boolean z2) {
        this(z2, false);
    }

    public c(boolean z2, boolean z3) {
        this.f495a = z2;
        this.f496b = z3;
        this.f497c = new StringBuilder(20);
        this.f498d = new int[9];
    }
}

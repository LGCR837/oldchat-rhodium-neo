package T;

import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: loaded from: classes.dex */
public final class q extends p {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final int[] f533j = {1, 1, 1, 1, 1, 1};

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final int[][] f534k = {new int[]{56, 52, 50, 49, 44, 38, 35, 42, 41, 37}, new int[]{7, 11, 13, 14, 19, 25, 28, 21, 22, 26}};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int[] f535i = new int[4];

    public static String r(String str) {
        char[] cArr = new char[6];
        str.getChars(1, 7, cArr, 0);
        StringBuilder sb = new StringBuilder(12);
        sb.append(str.charAt(0));
        char c2 = cArr[5];
        switch (c2) {
            case '0':
            case CipherSuite.TLS_DH_RSA_WITH_AES_128_CBC_SHA /* 49 */:
            case '2':
                sb.append(cArr, 0, 2);
                sb.append(c2);
                sb.append("0000");
                sb.append(cArr, 2, 3);
                break;
            case '3':
                sb.append(cArr, 0, 3);
                sb.append("00000");
                sb.append(cArr, 3, 2);
                break;
            case '4':
                sb.append(cArr, 0, 4);
                sb.append("00000");
                sb.append(cArr[4]);
                break;
            default:
                sb.append(cArr, 0, 5);
                sb.append("0000");
                sb.append(c2);
                break;
        }
        if (str.length() >= 8) {
            sb.append(str.charAt(7));
        }
        return sb.toString();
    }

    public static void s(StringBuilder sb, int i2) throws H.j {
        for (int i3 = 0; i3 <= 1; i3++) {
            for (int i4 = 0; i4 < 10; i4++) {
                if (i2 == f534k[i3][i4]) {
                    sb.insert(0, (char) (i3 + 48));
                    sb.append((char) (i4 + 48));
                    return;
                }
            }
        }
        throw H.j.a();
    }

    @Override // T.p
    public boolean g(String str) {
        return super.g(r(str));
    }

    @Override // T.p
    public int[] j(L.a aVar, int i2) {
        return p.m(aVar, i2, true, f533j);
    }

    @Override // T.p
    public int k(L.a aVar, int[] iArr, StringBuilder sb) throws H.j {
        int[] iArr2 = this.f535i;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int iK = aVar.k();
        int i2 = iArr[1];
        int i3 = 0;
        for (int i4 = 0; i4 < 6 && i2 < iK; i4++) {
            int i5 = p.i(aVar, iArr2, i2, p.f529h);
            sb.append((char) ((i5 % 10) + 48));
            for (int i6 : iArr2) {
                i2 += i6;
            }
            if (i5 >= 10) {
                i3 |= 1 << (5 - i4);
            }
        }
        s(sb, i3);
        return i2;
    }

    @Override // T.p
    public H.a p() {
        return H.a.UPC_E;
    }
}

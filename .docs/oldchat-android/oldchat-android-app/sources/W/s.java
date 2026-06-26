package W;

import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final L.a f599a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m f600b = new m();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final StringBuilder f601c = new StringBuilder();

    public s(L.a aVar) {
        this.f599a = aVar;
    }

    public static int g(L.a aVar, int i2, int i3) {
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            if (aVar.g(i2 + i5)) {
                i4 |= 1 << ((i3 - i5) - 1);
            }
        }
        return i4;
    }

    public String a(StringBuilder sb, int i2) throws H.g, H.j {
        String str = null;
        while (true) {
            o oVarC = c(i2, str);
            String strA = r.a(oVarC.b());
            if (strA != null) {
                sb.append(strA);
            }
            String strValueOf = oVarC.d() ? String.valueOf(oVarC.c()) : null;
            if (i2 == oVarC.a()) {
                return sb.toString();
            }
            i2 = oVarC.a();
            str = strValueOf;
        }
    }

    public final n b(int i2) {
        char c2;
        int iF = f(i2, 5);
        if (iF == 15) {
            return new n(i2 + 5, '$');
        }
        if (iF >= 5 && iF < 15) {
            return new n(i2 + 5, (char) (iF + 43));
        }
        int iF2 = f(i2, 6);
        if (iF2 >= 32 && iF2 < 58) {
            return new n(i2 + 6, (char) (iF2 + 33));
        }
        switch (iF2) {
            case 58:
                c2 = '*';
                break;
            case 59:
                c2 = ',';
                break;
            case 60:
                c2 = '-';
                break;
            case 61:
                c2 = '.';
                break;
            case CipherSuite.TLS_DH_DSS_WITH_AES_128_CBC_SHA256 /* 62 */:
                c2 = '/';
                break;
            default:
                throw new IllegalStateException("Decoding invalid alphanumeric value: ".concat(String.valueOf(iF2)));
        }
        return new n(i2 + 6, c2);
    }

    public o c(int i2, String str) throws H.g {
        this.f601c.setLength(0);
        if (str != null) {
            this.f601c.append(str);
        }
        this.f600b.h(i2);
        o oVarO = o();
        return (oVarO == null || !oVarO.d()) ? new o(this.f600b.a(), this.f601c.toString()) : new o(this.f600b.a(), this.f601c.toString(), oVarO.c());
    }

    public final n d(int i2) throws H.g {
        int iF = f(i2, 5);
        if (iF == 15) {
            return new n(i2 + 5, '$');
        }
        char c2 = '+';
        if (iF >= 5 && iF < 15) {
            return new n(i2 + 5, (char) (iF + 43));
        }
        int iF2 = f(i2, 7);
        if (iF2 >= 64 && iF2 < 90) {
            return new n(i2 + 7, (char) (iF2 + 1));
        }
        if (iF2 >= 90 && iF2 < 116) {
            return new n(i2 + 7, (char) (iF2 + 7));
        }
        switch (f(i2, 8)) {
            case 232:
                c2 = '!';
                break;
            case 233:
                c2 = '\"';
                break;
            case 234:
                c2 = '%';
                break;
            case 235:
                c2 = '&';
                break;
            case 236:
                c2 = '\'';
                break;
            case 237:
                c2 = '(';
                break;
            case 238:
                c2 = ')';
                break;
            case 239:
                c2 = '*';
                break;
            case 240:
                break;
            case 241:
                c2 = ',';
                break;
            case 242:
                c2 = '-';
                break;
            case 243:
                c2 = '.';
                break;
            case 244:
                c2 = '/';
                break;
            case 245:
                c2 = ':';
                break;
            case 246:
                c2 = ';';
                break;
            case 247:
                c2 = '<';
                break;
            case 248:
                c2 = '=';
                break;
            case 249:
                c2 = '>';
                break;
            case 250:
                c2 = '?';
                break;
            case 251:
                c2 = '_';
                break;
            case 252:
                c2 = ' ';
                break;
            default:
                throw H.g.a();
        }
        return new n(i2 + 8, c2);
    }

    public final p e(int i2) {
        int i3 = i2 + 7;
        if (i3 > this.f599a.k()) {
            int iF = f(i2, 4);
            return iF == 0 ? new p(this.f599a.k(), 10, 10) : new p(this.f599a.k(), iF - 1, 10);
        }
        int iF2 = f(i2, 7) - 8;
        return new p(i3, iF2 / 11, iF2 % 11);
    }

    public int f(int i2, int i3) {
        return g(this.f599a, i2, i3);
    }

    public final boolean h(int i2) {
        int i3 = i2 + 3;
        if (i3 > this.f599a.k()) {
            return false;
        }
        while (i2 < i3) {
            if (this.f599a.g(i2)) {
                return false;
            }
            i2++;
        }
        return true;
    }

    public final boolean i(int i2) {
        int i3;
        if (i2 + 1 > this.f599a.k()) {
            return false;
        }
        for (int i4 = 0; i4 < 5 && (i3 = i4 + i2) < this.f599a.k(); i4++) {
            if (i4 == 2) {
                if (!this.f599a.g(i2 + 2)) {
                    return false;
                }
            } else if (this.f599a.g(i3)) {
                return false;
            }
        }
        return true;
    }

    public final boolean j(int i2) {
        int i3;
        if (i2 + 1 > this.f599a.k()) {
            return false;
        }
        for (int i4 = 0; i4 < 4 && (i3 = i4 + i2) < this.f599a.k(); i4++) {
            if (this.f599a.g(i3)) {
                return false;
            }
        }
        return true;
    }

    public final boolean k(int i2) {
        int iF;
        if (i2 + 5 > this.f599a.k()) {
            return false;
        }
        int iF2 = f(i2, 5);
        if (iF2 < 5 || iF2 >= 16) {
            return i2 + 6 <= this.f599a.k() && (iF = f(i2, 6)) >= 16 && iF < 63;
        }
        return true;
    }

    public final boolean l(int i2) {
        int iF;
        if (i2 + 5 > this.f599a.k()) {
            return false;
        }
        int iF2 = f(i2, 5);
        if (iF2 >= 5 && iF2 < 16) {
            return true;
        }
        if (i2 + 7 > this.f599a.k()) {
            return false;
        }
        int iF3 = f(i2, 7);
        if (iF3 < 64 || iF3 >= 116) {
            return i2 + 8 <= this.f599a.k() && (iF = f(i2, 8)) >= 232 && iF < 253;
        }
        return true;
    }

    public final boolean m(int i2) {
        if (i2 + 7 > this.f599a.k()) {
            return i2 + 4 <= this.f599a.k();
        }
        int i3 = i2;
        while (true) {
            int i4 = i2 + 3;
            if (i3 >= i4) {
                return this.f599a.g(i4);
            }
            if (this.f599a.g(i3)) {
                return true;
            }
            i3++;
        }
    }

    public final l n() {
        while (k(this.f600b.a())) {
            n nVarB = b(this.f600b.a());
            this.f600b.h(nVarB.a());
            if (nVarB.c()) {
                return new l(new o(this.f600b.a(), this.f601c.toString()), true);
            }
            this.f601c.append(nVarB.b());
        }
        if (h(this.f600b.a())) {
            this.f600b.b(3);
            this.f600b.g();
        } else if (i(this.f600b.a())) {
            if (this.f600b.a() + 5 < this.f599a.k()) {
                this.f600b.b(5);
            } else {
                this.f600b.h(this.f599a.k());
            }
            this.f600b.f();
        }
        return new l(false);
    }

    public final o o() throws H.g {
        l lVarQ;
        boolean zB;
        do {
            int iA = this.f600b.a();
            if (this.f600b.c()) {
                lVarQ = n();
                zB = lVarQ.b();
            } else if (this.f600b.d()) {
                lVarQ = p();
                zB = lVarQ.b();
            } else {
                lVarQ = q();
                zB = lVarQ.b();
            }
            if (iA == this.f600b.a() && !zB) {
                break;
            }
        } while (!zB);
        return lVarQ.a();
    }

    public final l p() throws H.g {
        while (l(this.f600b.a())) {
            n nVarD = d(this.f600b.a());
            this.f600b.h(nVarD.a());
            if (nVarD.c()) {
                return new l(new o(this.f600b.a(), this.f601c.toString()), true);
            }
            this.f601c.append(nVarD.b());
        }
        if (h(this.f600b.a())) {
            this.f600b.b(3);
            this.f600b.g();
        } else if (i(this.f600b.a())) {
            if (this.f600b.a() + 5 < this.f599a.k()) {
                this.f600b.b(5);
            } else {
                this.f600b.h(this.f599a.k());
            }
            this.f600b.e();
        }
        return new l(false);
    }

    public final l q() {
        while (m(this.f600b.a())) {
            p pVarE = e(this.f600b.a());
            this.f600b.h(pVarE.a());
            if (pVarE.d()) {
                return new l(pVarE.e() ? new o(this.f600b.a(), this.f601c.toString()) : new o(this.f600b.a(), this.f601c.toString(), pVarE.c()), true);
            }
            this.f601c.append(pVarE.b());
            if (pVarE.e()) {
                return new l(new o(this.f600b.a(), this.f601c.toString()), true);
            }
            this.f601c.append(pVarE.c());
        }
        if (j(this.f600b.a())) {
            this.f600b.e();
            this.f600b.b(4);
        }
        return new l(false);
    }
}

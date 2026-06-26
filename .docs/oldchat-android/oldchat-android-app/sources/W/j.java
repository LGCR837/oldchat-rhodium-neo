package W;

import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final L.a f577a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s f578b;

    public j(L.a aVar) {
        this.f577a = aVar;
        this.f578b = new s(aVar);
    }

    public static j a(L.a aVar) {
        if (aVar.g(1)) {
            return new g(aVar);
        }
        if (!aVar.g(2)) {
            return new k(aVar);
        }
        int iG = s.g(aVar, 1, 4);
        if (iG == 4) {
            return new a(aVar);
        }
        if (iG == 5) {
            return new b(aVar);
        }
        int iG2 = s.g(aVar, 1, 5);
        if (iG2 == 12) {
            return new c(aVar);
        }
        if (iG2 == 13) {
            return new d(aVar);
        }
        switch (s.g(aVar, 1, 7)) {
            case 56:
                return new e(aVar, "310", "11");
            case 57:
                return new e(aVar, "320", "11");
            case 58:
                return new e(aVar, "310", "13");
            case 59:
                return new e(aVar, "320", "13");
            case 60:
                return new e(aVar, "310", "15");
            case 61:
                return new e(aVar, "320", "15");
            case CipherSuite.TLS_DH_DSS_WITH_AES_128_CBC_SHA256 /* 62 */:
                return new e(aVar, "310", "17");
            case 63:
                return new e(aVar, "320", "17");
            default:
                throw new IllegalStateException("unknown decoder: ".concat(String.valueOf(aVar)));
        }
    }

    public final s b() {
        return this.f578b;
    }

    public final L.a c() {
        return this.f577a;
    }

    public abstract String d();
}

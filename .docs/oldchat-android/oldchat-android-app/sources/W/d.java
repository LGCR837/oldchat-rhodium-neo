package W;

/* JADX INFO: loaded from: classes.dex */
public final class d extends h {
    public d(L.a aVar) {
        super(aVar);
    }

    @Override // W.j
    public String d() throws H.j {
        if (c().k() < 48) {
            throw H.j.a();
        }
        StringBuilder sb = new StringBuilder();
        f(sb, 8);
        int iF = b().f(48, 2);
        sb.append("(393");
        sb.append(iF);
        sb.append(')');
        int iF2 = b().f(50, 10);
        if (iF2 / 100 == 0) {
            sb.append('0');
        }
        if (iF2 / 10 == 0) {
            sb.append('0');
        }
        sb.append(iF2);
        sb.append(b().c(60, null).b());
        return sb.toString();
    }
}

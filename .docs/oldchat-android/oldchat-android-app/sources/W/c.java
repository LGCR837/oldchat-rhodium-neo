package W;

/* JADX INFO: loaded from: classes.dex */
public final class c extends h {
    public c(L.a aVar) {
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
        sb.append("(392");
        sb.append(iF);
        sb.append(')');
        sb.append(b().c(50, null).b());
        return sb.toString();
    }
}

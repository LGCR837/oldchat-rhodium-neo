package W;

/* JADX INFO: loaded from: classes.dex */
public abstract class f extends i {
    public f(L.a aVar) {
        super(aVar);
    }

    @Override // W.j
    public String d() throws H.j {
        if (c().k() != 60) {
            throw H.j.a();
        }
        StringBuilder sb = new StringBuilder();
        f(sb, 5);
        j(sb, 45, 15);
        return sb.toString();
    }
}

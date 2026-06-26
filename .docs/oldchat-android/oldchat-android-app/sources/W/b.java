package W;

/* JADX INFO: loaded from: classes.dex */
public final class b extends f {
    public b(L.a aVar) {
        super(aVar);
    }

    @Override // W.i
    public void h(StringBuilder sb, int i2) {
        if (i2 < 10000) {
            sb.append("(3202)");
        } else {
            sb.append("(3203)");
        }
    }

    @Override // W.i
    public int i(int i2) {
        return i2 < 10000 ? i2 : i2 - 10000;
    }
}

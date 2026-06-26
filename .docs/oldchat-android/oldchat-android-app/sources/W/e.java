package W;

/* JADX INFO: loaded from: classes.dex */
public final class e extends i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f575c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f576d;

    public e(L.a aVar, String str, String str2) {
        super(aVar);
        this.f575c = str2;
        this.f576d = str;
    }

    @Override // W.j
    public String d() throws H.j {
        if (c().k() != 84) {
            throw H.j.a();
        }
        StringBuilder sb = new StringBuilder();
        f(sb, 8);
        j(sb, 48, 20);
        k(sb, 68);
        return sb.toString();
    }

    @Override // W.i
    public void h(StringBuilder sb, int i2) {
        sb.append('(');
        sb.append(this.f576d);
        sb.append(i2 / 100000);
        sb.append(')');
    }

    @Override // W.i
    public int i(int i2) {
        return i2 % 100000;
    }

    public final void k(StringBuilder sb, int i2) {
        int iF = b().f(i2, 16);
        if (iF == 38400) {
            return;
        }
        sb.append('(');
        sb.append(this.f575c);
        sb.append(')');
        int i3 = iF % 32;
        int i4 = iF / 32;
        int i5 = (i4 % 12) + 1;
        int i6 = i4 / 12;
        if (i6 / 10 == 0) {
            sb.append('0');
        }
        sb.append(i6);
        if (i5 / 10 == 0) {
            sb.append('0');
        }
        sb.append(i5);
        if (i3 / 10 == 0) {
            sb.append('0');
        }
        sb.append(i3);
    }
}

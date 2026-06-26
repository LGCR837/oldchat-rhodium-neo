package W;

/* JADX INFO: loaded from: classes.dex */
public abstract class h extends j {
    public h(L.a aVar) {
        super(aVar);
    }

    public static void e(StringBuilder sb, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < 13; i4++) {
            int iCharAt = sb.charAt(i4 + i2) - '0';
            if ((i4 & 1) == 0) {
                iCharAt *= 3;
            }
            i3 += iCharAt;
        }
        int i5 = 10 - (i3 % 10);
        sb.append(i5 != 10 ? i5 : 0);
    }

    public final void f(StringBuilder sb, int i2) {
        sb.append("(01)");
        int length = sb.length();
        sb.append('9');
        g(sb, i2, length);
    }

    public final void g(StringBuilder sb, int i2, int i3) {
        for (int i4 = 0; i4 < 4; i4++) {
            int iF = b().f((i4 * 10) + i2, 10);
            if (iF / 100 == 0) {
                sb.append('0');
            }
            if (iF / 10 == 0) {
                sb.append('0');
            }
            sb.append(iF);
        }
        e(sb, i3);
    }
}

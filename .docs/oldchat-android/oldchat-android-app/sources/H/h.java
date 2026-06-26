package H;

/* JADX INFO: loaded from: classes.dex */
public abstract class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f287a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f288b;

    public h(int i2, int i3) {
        this.f287a = i2;
        this.f288b = i3;
    }

    public final int a() {
        return this.f288b;
    }

    public abstract byte[] b();

    public abstract byte[] c(int i2, byte[] bArr);

    public final int d() {
        return this.f287a;
    }

    public boolean e() {
        return false;
    }

    public h f() {
        throw new UnsupportedOperationException("This luminance source does not support rotation by 90 degrees.");
    }

    public final String toString() {
        int i2 = this.f287a;
        byte[] bArrC = new byte[i2];
        StringBuilder sb = new StringBuilder(this.f288b * (i2 + 1));
        for (int i3 = 0; i3 < this.f288b; i3++) {
            bArrC = c(i3, bArrC);
            for (int i4 = 0; i4 < this.f287a; i4++) {
                int i5 = bArrC[i4] & 255;
                sb.append(i5 < 64 ? '#' : i5 < 128 ? '+' : i5 < 192 ? '.' : ' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}

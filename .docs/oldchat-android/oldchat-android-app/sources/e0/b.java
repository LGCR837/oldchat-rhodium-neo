package e0;

import java.lang.reflect.Array;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[][] f4939a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4940b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f4941c;

    public b(int i2, int i3) {
        this.f4939a = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, i3, i2);
        this.f4940b = i2;
        this.f4941c = i3;
    }

    public void a(byte b2) {
        for (byte[] bArr : this.f4939a) {
            Arrays.fill(bArr, b2);
        }
    }

    public byte b(int i2, int i3) {
        return this.f4939a[i3][i2];
    }

    public byte[][] c() {
        return this.f4939a;
    }

    public int d() {
        return this.f4941c;
    }

    public int e() {
        return this.f4940b;
    }

    public void f(int i2, int i3, int i4) {
        this.f4939a[i3][i2] = (byte) i4;
    }

    public void g(int i2, int i3, boolean z2) {
        this.f4939a[i3][i2] = z2 ? (byte) 1 : (byte) 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((this.f4940b * 2 * this.f4941c) + 2);
        for (int i2 = 0; i2 < this.f4941c; i2++) {
            byte[] bArr = this.f4939a[i2];
            for (int i3 = 0; i3 < this.f4940b; i3++) {
                byte b2 = bArr[i3];
                if (b2 == 0) {
                    sb.append(" 0");
                } else if (b2 != 1) {
                    sb.append("  ");
                } else {
                    sb.append(" 1");
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}

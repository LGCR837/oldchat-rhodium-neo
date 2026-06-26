package M;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static float a(float f2, float f3, float f4, float f5) {
        float f6 = f2 - f4;
        float f7 = f3 - f5;
        return (float) Math.sqrt((f6 * f6) + (f7 * f7));
    }

    public static float b(int i2, int i3, int i4, int i5) {
        int i6 = i2 - i4;
        int i7 = i3 - i5;
        return (float) Math.sqrt((i6 * i6) + (i7 * i7));
    }

    public static int c(float f2) {
        return (int) (f2 + (f2 < 0.0f ? -0.5f : 0.5f));
    }

    public static int d(int[] iArr) {
        int i2 = 0;
        for (int i3 : iArr) {
            i2 += i3;
        }
        return i2;
    }
}

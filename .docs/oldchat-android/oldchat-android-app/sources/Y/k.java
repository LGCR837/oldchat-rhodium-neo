package Y;

import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float[][] f654a = (float[][]) Array.newInstance((Class<?>) Float.TYPE, X.a.f603b.length, 8);

    static {
        int i2;
        int i3 = 0;
        while (true) {
            int[] iArr = X.a.f603b;
            if (i3 >= iArr.length) {
                return;
            }
            int i4 = iArr[i3];
            int i5 = i4 & 1;
            int i6 = 0;
            while (i6 < 8) {
                float f2 = 0.0f;
                while (true) {
                    i2 = i4 & 1;
                    if (i2 == i5) {
                        f2 += 1.0f;
                        i4 >>= 1;
                    }
                }
                f654a[i3][7 - i6] = f2 / 17.0f;
                i6++;
                i5 = i2;
            }
            i3++;
        }
    }

    public static int a(int[] iArr) {
        long j2 = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            for (int i3 = 0; i3 < iArr[i2]; i3++) {
                int i4 = 1;
                long j3 = j2 << 1;
                if (i2 % 2 != 0) {
                    i4 = 0;
                }
                j2 = j3 | ((long) i4);
            }
        }
        return (int) j2;
    }

    public static int b(int[] iArr) {
        int iD = M.a.d(iArr);
        float[] fArr = new float[8];
        if (iD > 1) {
            for (int i2 = 0; i2 < 8; i2++) {
                fArr[i2] = iArr[i2] / iD;
            }
        }
        float f2 = Float.MAX_VALUE;
        int i3 = -1;
        int i4 = 0;
        while (true) {
            float[][] fArr2 = f654a;
            if (i4 >= fArr2.length) {
                return i3;
            }
            float[] fArr3 = fArr2[i4];
            float f3 = 0.0f;
            for (int i5 = 0; i5 < 8; i5++) {
                float f4 = fArr3[i5] - fArr[i5];
                f3 += f4 * f4;
                if (f3 >= f2) {
                    break;
                }
            }
            if (f3 < f2) {
                i3 = X.a.f603b[i4];
                f2 = f3;
            }
            i4++;
        }
    }

    public static int c(int[] iArr) {
        int iA = a(iArr);
        if (X.a.a(iA) == -1) {
            return -1;
        }
        return iA;
    }

    public static int d(int[] iArr) {
        int iC = c(e(iArr));
        return iC != -1 ? iC : b(iArr);
    }

    public static int[] e(int[] iArr) {
        float fD = M.a.d(iArr);
        int[] iArr2 = new int[8];
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < 17; i4++) {
            float f2 = (fD / 34.0f) + ((i4 * fD) / 17.0f);
            int i5 = iArr[i3];
            if (i2 + i5 <= f2) {
                i2 += i5;
                i3++;
            }
            iArr2[i3] = iArr2[i3] + 1;
        }
        return iArr2;
    }
}

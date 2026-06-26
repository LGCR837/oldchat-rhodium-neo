package U;

import H.j;
import T.k;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends k {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f537b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int[] f540e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int[] f541f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f536a = new int[4];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float[] f538c = new float[4];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float[] f539d = new float[4];

    public a() {
        int[] iArr = new int[8];
        this.f537b = iArr;
        this.f540e = new int[iArr.length / 2];
        this.f541f = new int[iArr.length / 2];
    }

    public static void g(int[] iArr, float[] fArr) {
        int i2 = 0;
        float f2 = fArr[0];
        for (int i3 = 1; i3 < iArr.length; i3++) {
            float f3 = fArr[i3];
            if (f3 < f2) {
                i2 = i3;
                f2 = f3;
            }
        }
        iArr[i2] = iArr[i2] - 1;
    }

    public static void n(int[] iArr, float[] fArr) {
        int i2 = 0;
        float f2 = fArr[0];
        for (int i3 = 1; i3 < iArr.length; i3++) {
            float f3 = fArr[i3];
            if (f3 > f2) {
                i2 = i3;
                f2 = f3;
            }
        }
        iArr[i2] = iArr[i2] + 1;
    }

    public static boolean o(int[] iArr) {
        float f2 = (iArr[0] + iArr[1]) / ((iArr[2] + r1) + iArr[3]);
        if (f2 >= 0.7916667f && f2 <= 0.89285713f) {
            int i2 = Integer.MAX_VALUE;
            int i3 = PKIFailureInfo.systemUnavail;
            for (int i4 : iArr) {
                if (i4 > i3) {
                    i3 = i4;
                }
                if (i4 < i2) {
                    i2 = i4;
                }
            }
            if (i3 < i2 * 10) {
                return true;
            }
        }
        return false;
    }

    public static int p(int[] iArr, int[][] iArr2) throws j {
        for (int i2 = 0; i2 < iArr2.length; i2++) {
            if (k.d(iArr, iArr2[i2], 0.45f) < 0.2f) {
                return i2;
            }
        }
        throw j.a();
    }

    public final int[] h() {
        return this.f537b;
    }

    public final int[] i() {
        return this.f536a;
    }

    public final int[] j() {
        return this.f541f;
    }

    public final float[] k() {
        return this.f539d;
    }

    public final int[] l() {
        return this.f540e;
    }

    public final float[] m() {
        return this.f538c;
    }
}

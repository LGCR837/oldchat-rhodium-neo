package org.spongycastle.pqc.math.ntru.euclid;

/* JADX INFO: loaded from: classes.dex */
public class IntEuclidean {
    public int gcd;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f7693x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f7694y;

    private IntEuclidean() {
    }

    public static IntEuclidean calculate(int i2, int i3) {
        int i4 = 0;
        int i5 = 0;
        int i6 = 1;
        int i7 = 1;
        int i8 = i2;
        int i9 = i3;
        while (i9 != 0) {
            int i10 = i8 / i9;
            int i11 = i8 % i9;
            int i12 = i7 - (i10 * i5);
            i8 = i9;
            i9 = i11;
            int i13 = i6;
            i6 = i4 - (i10 * i6);
            i4 = i13;
            i7 = i5;
            i5 = i12;
        }
        IntEuclidean intEuclidean = new IntEuclidean();
        intEuclidean.f7693x = i7;
        intEuclidean.f7694y = i4;
        intEuclidean.gcd = i8;
        return intEuclidean;
    }
}

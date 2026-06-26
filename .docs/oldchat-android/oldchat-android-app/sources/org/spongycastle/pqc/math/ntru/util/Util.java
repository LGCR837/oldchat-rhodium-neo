package org.spongycastle.pqc.math.ntru.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import org.spongycastle.pqc.math.ntru.euclid.IntEuclidean;
import org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.SparseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.TernaryPolynomial;
import org.spongycastle.util.Integers;

/* JADX INFO: loaded from: classes.dex */
public class Util {
    private static volatile boolean IS_64_BITNESS_KNOWN;
    private static volatile boolean IS_64_BIT_JVM;

    public static TernaryPolynomial generateRandomTernary(int i2, int i3, int i4, boolean z2, SecureRandom secureRandom) {
        return z2 ? SparseTernaryPolynomial.generateRandom(i2, i3, i4, secureRandom) : DenseTernaryPolynomial.generateRandom(i2, i3, i4, secureRandom);
    }

    public static int invert(int i2, int i3) {
        int i4 = i2 % i3;
        if (i4 < 0) {
            i4 += i3;
        }
        return IntEuclidean.calculate(i4, i3).f7693x;
    }

    public static boolean is64BitJVM() {
        if (!IS_64_BITNESS_KNOWN) {
            String property = System.getProperty("os.arch");
            IS_64_BIT_JVM = "amd64".equals(property) || "x86_64".equals(property) || "ppc64".equals(property) || "64".equals(System.getProperty("sun.arch.data.model"));
            IS_64_BITNESS_KNOWN = true;
        }
        return IS_64_BIT_JVM;
    }

    public static int pow(int i2, int i3, int i4) {
        int i5 = 1;
        for (int i6 = 0; i6 < i3; i6++) {
            i5 = (i5 * i2) % i4;
        }
        return i5;
    }

    public static byte[] readFullLength(InputStream inputStream, int i2) throws IOException {
        byte[] bArr = new byte[i2];
        if (inputStream.read(bArr) == i2) {
            return bArr;
        }
        throw new IOException("Not enough bytes to read.");
    }

    public static long pow(long j2, int i2, long j3) {
        long j4 = 1;
        for (int i3 = 0; i3 < i2; i3++) {
            j4 = (j4 * j2) % j3;
        }
        return j4;
    }

    public static int[] generateRandomTernary(int i2, int i3, int i4, SecureRandom secureRandom) {
        Integer numValueOf = Integers.valueOf(1);
        Integer numValueOf2 = Integers.valueOf(-1);
        Integer numValueOf3 = Integers.valueOf(0);
        ArrayList arrayList = new ArrayList();
        for (int i5 = 0; i5 < i3; i5++) {
            arrayList.add(numValueOf);
        }
        for (int i6 = 0; i6 < i4; i6++) {
            arrayList.add(numValueOf2);
        }
        while (arrayList.size() < i2) {
            arrayList.add(numValueOf3);
        }
        Collections.shuffle(arrayList, secureRandom);
        int[] iArr = new int[i2];
        for (int i7 = 0; i7 < i2; i7++) {
            iArr[i7] = ((Integer) arrayList.get(i7)).intValue();
        }
        return iArr;
    }
}

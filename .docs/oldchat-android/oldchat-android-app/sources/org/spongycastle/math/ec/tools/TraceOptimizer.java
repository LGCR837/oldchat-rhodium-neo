package org.spongycastle.math.ec.tools;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TreeSet;
import org.spongycastle.asn1.x9.ECNamedCurveTable;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.crypto.ec.CustomNamedCurves;
import org.spongycastle.math.ec.ECAlgorithms;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.util.Integers;

/* JADX INFO: loaded from: classes.dex */
public class TraceOptimizer {
    private static final BigInteger ONE = BigInteger.valueOf(1);

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    private static final SecureRandom f7580R = new SecureRandom();

    private static int calculateTrace(ECFieldElement eCFieldElement) {
        int fieldSize = eCFieldElement.getFieldSize();
        ECFieldElement eCFieldElementSquare = eCFieldElement;
        for (int i2 = 1; i2 < fieldSize; i2++) {
            eCFieldElementSquare = eCFieldElementSquare.square();
            eCFieldElement = eCFieldElement.add(eCFieldElementSquare);
        }
        BigInteger bigInteger = eCFieldElement.toBigInteger();
        if (bigInteger.bitLength() <= 1) {
            return bigInteger.intValue();
        }
        throw new IllegalStateException();
    }

    private static ArrayList enumToList(Enumeration enumeration) {
        ArrayList arrayList = new ArrayList();
        while (enumeration.hasMoreElements()) {
            arrayList.add(enumeration.nextElement());
        }
        return arrayList;
    }

    public static void implPrintNonZeroTraceBits(X9ECParameters x9ECParameters) {
        ECCurve curve = x9ECParameters.getCurve();
        int fieldSize = curve.getFieldSize();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < fieldSize; i2++) {
            if (calculateTrace(curve.fromBigInteger(ONE.shiftLeft(i2))) != 0) {
                arrayList.add(Integers.valueOf(i2));
                System.out.print(" " + i2);
            }
        }
        System.out.println();
        for (int i3 = 0; i3 < 1000; i3++) {
            BigInteger bigInteger = new BigInteger(fieldSize, f7580R);
            int iCalculateTrace = calculateTrace(curve.fromBigInteger(bigInteger));
            int i4 = 0;
            for (int i5 = 0; i5 < arrayList.size(); i5++) {
                if (bigInteger.testBit(((Integer) arrayList.get(i5)).intValue())) {
                    i4 ^= 1;
                }
            }
            if (iCalculateTrace != i4) {
                throw new IllegalStateException("Optimized-trace sanity check failed");
            }
        }
    }

    public static void main(String[] strArr) {
        TreeSet<String> treeSet = new TreeSet(enumToList(ECNamedCurveTable.getNames()));
        treeSet.addAll(enumToList(CustomNamedCurves.getNames()));
        for (String str : treeSet) {
            X9ECParameters byName = CustomNamedCurves.getByName(str);
            if (byName == null) {
                byName = ECNamedCurveTable.getByName(str);
            }
            if (byName != null && ECAlgorithms.isF2mCurve(byName.getCurve())) {
                System.out.print(str + ":");
                implPrintNonZeroTraceBits(byName);
            }
        }
    }

    public static void printNonZeroTraceBits(X9ECParameters x9ECParameters) {
        if (!ECAlgorithms.isF2mCurve(x9ECParameters.getCurve())) {
            throw new IllegalArgumentException("Trace only defined over characteristic-2 fields");
        }
        implPrintNonZeroTraceBits(x9ECParameters);
    }
}

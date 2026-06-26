package org.spongycastle.math.raw;

import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public abstract class Mod {
    public static void add(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        int length = iArr.length;
        if (Nat.add(length, iArr2, iArr3, iArr4) != 0) {
            Nat.subFrom(length, iArr, iArr4);
        }
    }

    private static int getTrailingZeroes(int i2) {
        int i3 = 0;
        while ((i2 & 1) == 0) {
            i2 >>>= 1;
            i3++;
        }
        return i3;
    }

    public static int inverse32(int i2) {
        int i3 = (2 - (i2 * i2)) * i2;
        int i4 = i3 * (2 - (i2 * i3));
        int i5 = i4 * (2 - (i2 * i4));
        return i5 * (2 - (i2 * i5));
    }

    private static void inversionResult(int[] iArr, int i2, int[] iArr2, int[] iArr3) {
        if (i2 < 0) {
            Nat.add(iArr.length, iArr2, iArr, iArr3);
        } else {
            System.arraycopy(iArr2, 0, iArr3, 0, iArr.length);
        }
    }

    private static int inversionStep(int[] iArr, int[] iArr2, int i2, int[] iArr3, int i3) {
        int i4;
        int length = iArr.length;
        int i5 = 0;
        while (true) {
            i4 = iArr2[0];
            if (i4 != 0) {
                break;
            }
            Nat.shiftDownWord(i2, iArr2, 0);
            i5 += 32;
        }
        int trailingZeroes = getTrailingZeroes(i4);
        if (trailingZeroes > 0) {
            Nat.shiftDownBits(i2, iArr2, trailingZeroes, 0);
            i5 += trailingZeroes;
        }
        for (int i6 = 0; i6 < i5; i6++) {
            if ((iArr3[0] & 1) != 0) {
                i3 += i3 < 0 ? Nat.addTo(length, iArr, iArr3) : Nat.subFrom(length, iArr, iArr3);
            }
            Nat.shiftDownBit(length, iArr3, i3);
        }
        return i3;
    }

    public static void invert(int[] iArr, int[] iArr2, int[] iArr3) {
        int length = iArr.length;
        if (Nat.isZero(length, iArr2)) {
            throw new IllegalArgumentException("'x' cannot be 0");
        }
        int iInversionStep = 0;
        if (Nat.isOne(length, iArr2)) {
            System.arraycopy(iArr2, 0, iArr3, 0, length);
            return;
        }
        int[] iArrCopy = Nat.copy(length, iArr2);
        int[] iArrCreate = Nat.create(length);
        iArrCreate[0] = 1;
        int iInversionStep2 = (1 & iArrCopy[0]) == 0 ? inversionStep(iArr, iArrCopy, length, iArrCreate, 0) : 0;
        if (Nat.isOne(length, iArrCopy)) {
            inversionResult(iArr, iInversionStep2, iArrCreate, iArr3);
            return;
        }
        int[] iArrCopy2 = Nat.copy(length, iArr);
        int[] iArrCreate2 = Nat.create(length);
        int i2 = length;
        while (true) {
            int i3 = i2 - 1;
            if (iArrCopy[i3] == 0 && iArrCopy2[i3] == 0) {
                i2--;
            } else if (Nat.gte(i2, iArrCopy, iArrCopy2)) {
                Nat.subFrom(i2, iArrCopy2, iArrCopy);
                iInversionStep2 = inversionStep(iArr, iArrCopy, i2, iArrCreate, iInversionStep2 + (Nat.subFrom(length, iArrCreate2, iArrCreate) - iInversionStep));
                if (Nat.isOne(i2, iArrCopy)) {
                    inversionResult(iArr, iInversionStep2, iArrCreate, iArr3);
                    return;
                }
            } else {
                Nat.subFrom(i2, iArrCopy, iArrCopy2);
                iInversionStep = inversionStep(iArr, iArrCopy2, i2, iArrCreate2, iInversionStep + (Nat.subFrom(length, iArrCreate, iArrCreate2) - iInversionStep2));
                if (Nat.isOne(i2, iArrCopy2)) {
                    inversionResult(iArr, iInversionStep, iArrCreate2, iArr3);
                    return;
                }
            }
        }
    }

    public static int[] random(int[] iArr) {
        int length = iArr.length;
        Random random = new Random();
        int[] iArrCreate = Nat.create(length);
        int i2 = length - 1;
        int i3 = iArr[i2];
        int i4 = i3 | (i3 >>> 1);
        int i5 = i4 | (i4 >>> 2);
        int i6 = i5 | (i5 >>> 4);
        int i7 = i6 | (i6 >>> 8);
        int i8 = i7 | (i7 >>> 16);
        do {
            for (int i9 = 0; i9 != length; i9++) {
                iArrCreate[i9] = random.nextInt();
            }
            iArrCreate[i2] = iArrCreate[i2] & i8;
        } while (Nat.gte(length, iArrCreate, iArr));
        return iArrCreate;
    }

    public static void subtract(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        int length = iArr.length;
        if (Nat.sub(length, iArr2, iArr3, iArr4) != 0) {
            Nat.addTo(length, iArr, iArr4);
        }
    }
}

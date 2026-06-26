package org.spongycastle.util;

import java.math.BigInteger;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class Arrays {

    public static class Iterator<T> implements java.util.Iterator<T> {
        private final T[] dataArray;
        private int position = 0;

        public Iterator(T[] tArr) {
            this.dataArray = tArr;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.position < this.dataArray.length;
        }

        @Override // java.util.Iterator
        public T next() {
            int i2 = this.position;
            T[] tArr = this.dataArray;
            if (i2 != tArr.length) {
                this.position = i2 + 1;
                return tArr[i2];
            }
            throw new NoSuchElementException("Out of elements: " + this.position);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Cannot remove element from an Array.");
        }
    }

    private Arrays() {
    }

    public static byte[] append(byte[] bArr, byte b2) {
        if (bArr == null) {
            return new byte[]{b2};
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[1 + length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        bArr2[length] = b2;
        return bArr2;
    }

    public static boolean areEqual(boolean[] zArr, boolean[] zArr2) {
        if (zArr == zArr2) {
            return true;
        }
        if (zArr == null || zArr2 == null || zArr.length != zArr2.length) {
            return false;
        }
        for (int i2 = 0; i2 != zArr.length; i2++) {
            if (zArr[i2] != zArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static byte[] clone(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public static int compareUnsigned(byte[] bArr, byte[] bArr2) {
        if (bArr == bArr2) {
            return 0;
        }
        if (bArr == null) {
            return -1;
        }
        if (bArr2 == null) {
            return 1;
        }
        int iMin = Math.min(bArr.length, bArr2.length);
        for (int i2 = 0; i2 < iMin; i2++) {
            int i3 = bArr[i2] & 255;
            int i4 = bArr2[i2] & 255;
            if (i3 < i4) {
                return -1;
            }
            if (i3 > i4) {
                return 1;
            }
        }
        if (bArr.length < bArr2.length) {
            return -1;
        }
        return bArr.length > bArr2.length ? 1 : 0;
    }

    public static byte[] concatenate(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            return bArr2 != null ? clone(bArr2) : clone(bArr);
        }
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static boolean constantTimeAreEqual(byte[] bArr, byte[] bArr2) {
        if (bArr == bArr2) {
            return true;
        }
        if (bArr == null || bArr2 == null) {
            return false;
        }
        if (bArr.length != bArr2.length) {
            return !constantTimeAreEqual(bArr, bArr);
        }
        int i2 = 0;
        for (int i3 = 0; i3 != bArr.length; i3++) {
            i2 |= bArr[i3] ^ bArr2[i3];
        }
        return i2 == 0;
    }

    public static boolean contains(short[] sArr, short s2) {
        for (short s3 : sArr) {
            if (s3 == s2) {
                return true;
            }
        }
        return false;
    }

    public static byte[] copyOf(byte[] bArr, int i2) {
        byte[] bArr2 = new byte[i2];
        if (i2 < bArr.length) {
            System.arraycopy(bArr, 0, bArr2, 0, i2);
        } else {
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        }
        return bArr2;
    }

    public static byte[] copyOfRange(byte[] bArr, int i2, int i3) {
        int length = getLength(i2, i3);
        byte[] bArr2 = new byte[length];
        if (bArr.length - i2 < length) {
            System.arraycopy(bArr, i2, bArr2, 0, bArr.length - i2);
        } else {
            System.arraycopy(bArr, i2, bArr2, 0, length);
        }
        return bArr2;
    }

    public static void fill(byte[] bArr, byte b2) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = b2;
        }
    }

    private static int getLength(int i2, int i3) {
        int i4 = i3 - i2;
        if (i4 >= 0) {
            return i4;
        }
        StringBuffer stringBuffer = new StringBuffer(i2);
        stringBuffer.append(" > ");
        stringBuffer.append(i3);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public static int hashCode(byte[] bArr) {
        if (bArr == null) {
            return 0;
        }
        int length = bArr.length;
        int i2 = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i2;
            }
            i2 = (i2 * 257) ^ bArr[length];
        }
    }

    public static byte[] prepend(byte[] bArr, byte b2) {
        if (bArr == null) {
            return new byte[]{b2};
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[length + 1];
        System.arraycopy(bArr, 0, bArr2, 1, length);
        bArr2[0] = b2;
        return bArr2;
    }

    public static byte[] reverse(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        int i2 = 0;
        while (true) {
            length--;
            if (length < 0) {
                return bArr2;
            }
            bArr2[length] = bArr[i2];
            i2++;
        }
    }

    public static char[] clone(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        char[] cArr2 = new char[cArr.length];
        System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
        return cArr2;
    }

    public static boolean contains(int[] iArr, int i2) {
        for (int i3 : iArr) {
            if (i3 == i2) {
                return true;
            }
        }
        return false;
    }

    public static void fill(char[] cArr, char c2) {
        for (int i2 = 0; i2 < cArr.length; i2++) {
            cArr[i2] = c2;
        }
    }

    public static int hashCode(byte[] bArr, int i2, int i3) {
        if (bArr == null) {
            return 0;
        }
        int i4 = i3 + 1;
        while (true) {
            i3--;
            if (i3 < 0) {
                return i4;
            }
            i4 = (i4 * 257) ^ bArr[i2 + i3];
        }
    }

    public static boolean areEqual(char[] cArr, char[] cArr2) {
        if (cArr == cArr2) {
            return true;
        }
        if (cArr == null || cArr2 == null || cArr.length != cArr2.length) {
            return false;
        }
        for (int i2 = 0; i2 != cArr.length; i2++) {
            if (cArr[i2] != cArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static int hashCode(char[] cArr) {
        if (cArr == null) {
            return 0;
        }
        int length = cArr.length;
        int i2 = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i2;
            }
            i2 = (i2 * 257) ^ cArr[length];
        }
    }

    public static int[] reverse(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        int length = iArr.length;
        int[] iArr2 = new int[length];
        int i2 = 0;
        while (true) {
            length--;
            if (length < 0) {
                return iArr2;
            }
            iArr2[length] = iArr[i2];
            i2++;
        }
    }

    public static byte[] clone(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            return null;
        }
        if (bArr2 != null && bArr2.length == bArr.length) {
            System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
            return bArr2;
        }
        return clone(bArr);
    }

    public static char[] copyOf(char[] cArr, int i2) {
        char[] cArr2 = new char[i2];
        if (i2 < cArr.length) {
            System.arraycopy(cArr, 0, cArr2, 0, i2);
        } else {
            System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
        }
        return cArr2;
    }

    public static void fill(long[] jArr, long j2) {
        for (int i2 = 0; i2 < jArr.length; i2++) {
            jArr[i2] = j2;
        }
    }

    public static short[] append(short[] sArr, short s2) {
        if (sArr == null) {
            return new short[]{s2};
        }
        int length = sArr.length;
        short[] sArr2 = new short[1 + length];
        System.arraycopy(sArr, 0, sArr2, 0, length);
        sArr2[length] = s2;
        return sArr2;
    }

    public static byte[] concatenate(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr == null || bArr2 == null || bArr3 == null) {
            if (bArr == null) {
                return concatenate(bArr2, bArr3);
            }
            if (bArr2 == null) {
                return concatenate(bArr, bArr3);
            }
            return concatenate(bArr, bArr2);
        }
        byte[] bArr4 = new byte[bArr.length + bArr2.length + bArr3.length];
        System.arraycopy(bArr, 0, bArr4, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr4, bArr.length, bArr2.length);
        System.arraycopy(bArr3, 0, bArr4, bArr.length + bArr2.length, bArr3.length);
        return bArr4;
    }

    public static int[] copyOfRange(int[] iArr, int i2, int i3) {
        int length = getLength(i2, i3);
        int[] iArr2 = new int[length];
        if (iArr.length - i2 < length) {
            System.arraycopy(iArr, i2, iArr2, 0, iArr.length - i2);
        } else {
            System.arraycopy(iArr, i2, iArr2, 0, length);
        }
        return iArr2;
    }

    public static int hashCode(int[][] iArr) {
        int iHashCode = 0;
        for (int i2 = 0; i2 != iArr.length; i2++) {
            iHashCode = (iHashCode * 257) + hashCode(iArr[i2]);
        }
        return iHashCode;
    }

    public static short[] prepend(short[] sArr, short s2) {
        if (sArr == null) {
            return new short[]{s2};
        }
        int length = sArr.length;
        short[] sArr2 = new short[length + 1];
        System.arraycopy(sArr, 0, sArr2, 1, length);
        sArr2[0] = s2;
        return sArr2;
    }

    public static boolean areEqual(byte[] bArr, byte[] bArr2) {
        if (bArr == bArr2) {
            return true;
        }
        if (bArr == null || bArr2 == null || bArr.length != bArr2.length) {
            return false;
        }
        for (int i2 = 0; i2 != bArr.length; i2++) {
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static void fill(short[] sArr, short s2) {
        for (int i2 = 0; i2 < sArr.length; i2++) {
            sArr[i2] = s2;
        }
    }

    public static byte[][] clone(byte[][] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        byte[][] bArr2 = new byte[length][];
        for (int i2 = 0; i2 != length; i2++) {
            bArr2[i2] = clone(bArr[i2]);
        }
        return bArr2;
    }

    public static int hashCode(int[] iArr) {
        if (iArr == null) {
            return 0;
        }
        int length = iArr.length;
        int i2 = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i2;
            }
            i2 = (i2 * 257) ^ iArr[length];
        }
    }

    public static int[] copyOf(int[] iArr, int i2) {
        int[] iArr2 = new int[i2];
        if (i2 < iArr.length) {
            System.arraycopy(iArr, 0, iArr2, 0, i2);
        } else {
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        }
        return iArr2;
    }

    public static void fill(int[] iArr, int i2) {
        for (int i3 = 0; i3 < iArr.length; i3++) {
            iArr[i3] = i2;
        }
    }

    public static boolean areEqual(short[] sArr, short[] sArr2) {
        if (sArr == sArr2) {
            return true;
        }
        if (sArr == null || sArr2 == null || sArr.length != sArr2.length) {
            return false;
        }
        for (int i2 = 0; i2 != sArr.length; i2++) {
            if (sArr[i2] != sArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static byte[][][] clone(byte[][][] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        byte[][][] bArr2 = new byte[length][][];
        for (int i2 = 0; i2 != length; i2++) {
            bArr2[i2] = clone(bArr[i2]);
        }
        return bArr2;
    }

    public static int hashCode(int[] iArr, int i2, int i3) {
        if (iArr == null) {
            return 0;
        }
        int i4 = i3 + 1;
        while (true) {
            i3--;
            if (i3 < 0) {
                return i4;
            }
            i4 = (i4 * 257) ^ iArr[i2 + i3];
        }
    }

    public static int[] append(int[] iArr, int i2) {
        if (iArr == null) {
            return new int[]{i2};
        }
        int length = iArr.length;
        int[] iArr2 = new int[length + 1];
        System.arraycopy(iArr, 0, iArr2, 0, length);
        iArr2[length] = i2;
        return iArr2;
    }

    public static long[] copyOfRange(long[] jArr, int i2, int i3) {
        int length = getLength(i2, i3);
        long[] jArr2 = new long[length];
        if (jArr.length - i2 < length) {
            System.arraycopy(jArr, i2, jArr2, 0, jArr.length - i2);
        } else {
            System.arraycopy(jArr, i2, jArr2, 0, length);
        }
        return jArr2;
    }

    public static int hashCode(long[] jArr) {
        if (jArr == null) {
            return 0;
        }
        int length = jArr.length;
        int i2 = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i2;
            }
            long j2 = jArr[length];
            i2 = (((i2 * 257) ^ ((int) j2)) * 257) ^ ((int) (j2 >>> 32));
        }
    }

    public static int[] prepend(int[] iArr, int i2) {
        if (iArr == null) {
            return new int[]{i2};
        }
        int length = iArr.length;
        int[] iArr2 = new int[length + 1];
        System.arraycopy(iArr, 0, iArr2, 1, length);
        iArr2[0] = i2;
        return iArr2;
    }

    public static int[] clone(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        int[] iArr2 = new int[iArr.length];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        return iArr2;
    }

    public static boolean areEqual(int[] iArr, int[] iArr2) {
        if (iArr == iArr2) {
            return true;
        }
        if (iArr == null || iArr2 == null || iArr.length != iArr2.length) {
            return false;
        }
        for (int i2 = 0; i2 != iArr.length; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static byte[] concatenate(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        if (bArr != null && bArr2 != null && bArr3 != null && bArr4 != null) {
            byte[] bArr5 = new byte[bArr.length + bArr2.length + bArr3.length + bArr4.length];
            System.arraycopy(bArr, 0, bArr5, 0, bArr.length);
            System.arraycopy(bArr2, 0, bArr5, bArr.length, bArr2.length);
            System.arraycopy(bArr3, 0, bArr5, bArr.length + bArr2.length, bArr3.length);
            System.arraycopy(bArr4, 0, bArr5, bArr.length + bArr2.length + bArr3.length, bArr4.length);
            return bArr5;
        }
        if (bArr4 == null) {
            return concatenate(bArr, bArr2, bArr3);
        }
        if (bArr3 == null) {
            return concatenate(bArr, bArr2, bArr4);
        }
        if (bArr2 == null) {
            return concatenate(bArr, bArr3, bArr4);
        }
        return concatenate(bArr2, bArr3, bArr4);
    }

    public static long[] copyOf(long[] jArr, int i2) {
        long[] jArr2 = new long[i2];
        if (i2 < jArr.length) {
            System.arraycopy(jArr, 0, jArr2, 0, i2);
        } else {
            System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
        }
        return jArr2;
    }

    public static int hashCode(long[] jArr, int i2, int i3) {
        if (jArr == null) {
            return 0;
        }
        int i4 = i3 + 1;
        while (true) {
            i3--;
            if (i3 < 0) {
                return i4;
            }
            long j2 = jArr[i2 + i3];
            i4 = (((i4 * 257) ^ ((int) j2)) * 257) ^ ((int) (j2 >>> 32));
        }
    }

    public static long[] clone(long[] jArr) {
        if (jArr == null) {
            return null;
        }
        long[] jArr2 = new long[jArr.length];
        System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
        return jArr2;
    }

    public static int hashCode(short[][][] sArr) {
        int iHashCode = 0;
        for (int i2 = 0; i2 != sArr.length; i2++) {
            iHashCode = (iHashCode * 257) + hashCode(sArr[i2]);
        }
        return iHashCode;
    }

    public static String[] append(String[] strArr, String str) {
        if (strArr == null) {
            return new String[]{str};
        }
        int length = strArr.length;
        String[] strArr2 = new String[1 + length];
        System.arraycopy(strArr, 0, strArr2, 0, length);
        strArr2[length] = str;
        return strArr2;
    }

    public static boolean areEqual(long[] jArr, long[] jArr2) {
        if (jArr == jArr2) {
            return true;
        }
        if (jArr == null || jArr2 == null || jArr.length != jArr2.length) {
            return false;
        }
        for (int i2 = 0; i2 != jArr.length; i2++) {
            if (jArr[i2] != jArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static long[] clone(long[] jArr, long[] jArr2) {
        if (jArr == null) {
            return null;
        }
        if (jArr2 != null && jArr2.length == jArr.length) {
            System.arraycopy(jArr, 0, jArr2, 0, jArr2.length);
            return jArr2;
        }
        return clone(jArr);
    }

    public static BigInteger[] copyOfRange(BigInteger[] bigIntegerArr, int i2, int i3) {
        int length = getLength(i2, i3);
        BigInteger[] bigIntegerArr2 = new BigInteger[length];
        if (bigIntegerArr.length - i2 < length) {
            System.arraycopy(bigIntegerArr, i2, bigIntegerArr2, 0, bigIntegerArr.length - i2);
        } else {
            System.arraycopy(bigIntegerArr, i2, bigIntegerArr2, 0, length);
        }
        return bigIntegerArr2;
    }

    public static int hashCode(short[][] sArr) {
        int iHashCode = 0;
        for (int i2 = 0; i2 != sArr.length; i2++) {
            iHashCode = (iHashCode * 257) + hashCode(sArr[i2]);
        }
        return iHashCode;
    }

    public static BigInteger[] copyOf(BigInteger[] bigIntegerArr, int i2) {
        BigInteger[] bigIntegerArr2 = new BigInteger[i2];
        if (i2 < bigIntegerArr.length) {
            System.arraycopy(bigIntegerArr, 0, bigIntegerArr2, 0, i2);
        } else {
            System.arraycopy(bigIntegerArr, 0, bigIntegerArr2, 0, bigIntegerArr.length);
        }
        return bigIntegerArr2;
    }

    public static int hashCode(short[] sArr) {
        if (sArr == null) {
            return 0;
        }
        int length = sArr.length;
        int i2 = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i2;
            }
            i2 = (i2 * 257) ^ (sArr[length] & 255);
        }
    }

    public static boolean areEqual(Object[] objArr, Object[] objArr2) {
        if (objArr == objArr2) {
            return true;
        }
        if (objArr == null || objArr2 == null || objArr.length != objArr2.length) {
            return false;
        }
        for (int i2 = 0; i2 != objArr.length; i2++) {
            Object obj = objArr[i2];
            Object obj2 = objArr2[i2];
            if (obj == null) {
                if (obj2 != null) {
                    return false;
                }
            } else if (!obj.equals(obj2)) {
                return false;
            }
        }
        return true;
    }

    public static short[] clone(short[] sArr) {
        if (sArr == null) {
            return null;
        }
        short[] sArr2 = new short[sArr.length];
        System.arraycopy(sArr, 0, sArr2, 0, sArr.length);
        return sArr2;
    }

    public static int hashCode(Object[] objArr) {
        if (objArr == null) {
            return 0;
        }
        int length = objArr.length;
        int iHashCode = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return iHashCode;
            }
            iHashCode = (iHashCode * 257) ^ objArr[length].hashCode();
        }
    }

    public static BigInteger[] clone(BigInteger[] bigIntegerArr) {
        if (bigIntegerArr == null) {
            return null;
        }
        BigInteger[] bigIntegerArr2 = new BigInteger[bigIntegerArr.length];
        System.arraycopy(bigIntegerArr, 0, bigIntegerArr2, 0, bigIntegerArr.length);
        return bigIntegerArr2;
    }

    public static byte[] concatenate(byte[][] bArr) {
        int length = 0;
        for (int i2 = 0; i2 != bArr.length; i2++) {
            length += bArr[i2].length;
        }
        byte[] bArr2 = new byte[length];
        int length2 = 0;
        for (int i3 = 0; i3 != bArr.length; i3++) {
            byte[] bArr3 = bArr[i3];
            System.arraycopy(bArr3, 0, bArr2, length2, bArr3.length);
            length2 += bArr[i3].length;
        }
        return bArr2;
    }

    public static int[] concatenate(int[] iArr, int[] iArr2) {
        if (iArr == null) {
            return clone(iArr2);
        }
        if (iArr2 == null) {
            return clone(iArr);
        }
        int[] iArr3 = new int[iArr.length + iArr2.length];
        System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
        System.arraycopy(iArr2, 0, iArr3, iArr.length, iArr2.length);
        return iArr3;
    }
}

package org.spongycastle.pqc.math.linearalgebra;

/* JADX INFO: loaded from: classes.dex */
public final class ByteUtils {
    private static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private ByteUtils() {
    }

    public static byte[] clone(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public static byte[] concatenate(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static int deepHashCode(byte[] bArr) {
        int i2 = 1;
        for (byte b2 : bArr) {
            i2 = (i2 * 31) + b2;
        }
        return i2;
    }

    public static boolean equals(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            return bArr2 == null;
        }
        if (bArr2 == null || bArr.length != bArr2.length) {
            return false;
        }
        boolean z2 = true;
        for (int length = bArr.length - 1; length >= 0; length--) {
            z2 &= bArr[length] == bArr2[length];
        }
        return z2;
    }

    public static byte[] fromHexString(String str) {
        char[] charArray = str.toUpperCase().toCharArray();
        int i2 = 0;
        for (char c2 : charArray) {
            if ((c2 >= '0' && c2 <= '9') || (c2 >= 'A' && c2 <= 'F')) {
                i2++;
            }
        }
        byte[] bArr = new byte[(i2 + 1) >> 1];
        int i3 = i2 & 1;
        for (char c3 : charArray) {
            if (c3 >= '0' && c3 <= '9') {
                int i4 = i3 >> 1;
                byte b2 = (byte) (bArr[i4] << 4);
                bArr[i4] = b2;
                bArr[i4] = (byte) ((c3 - '0') | b2);
            } else if (c3 >= 'A' && c3 <= 'F') {
                int i5 = i3 >> 1;
                byte b3 = (byte) (bArr[i5] << 4);
                bArr[i5] = b3;
                bArr[i5] = (byte) ((c3 - '7') | b3);
            }
            i3++;
        }
        return bArr;
    }

    public static byte[][] split(byte[] bArr, int i2) {
        if (i2 > bArr.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        byte[][] bArr2 = {new byte[i2], new byte[bArr.length - i2]};
        System.arraycopy(bArr, 0, bArr2[0], 0, i2);
        System.arraycopy(bArr, i2, bArr2[1], 0, bArr.length - i2);
        return bArr2;
    }

    public static byte[] subArray(byte[] bArr, int i2, int i3) {
        int i4 = i3 - i2;
        byte[] bArr2 = new byte[i4];
        System.arraycopy(bArr, i2, bArr2, 0, i4);
        return bArr2;
    }

    public static String toBinaryString(byte[] bArr) {
        String str = "";
        for (int i2 = 0; i2 < bArr.length; i2++) {
            byte b2 = bArr[i2];
            for (int i3 = 0; i3 < 8; i3++) {
                str = str + ((b2 >>> i3) & 1);
            }
            if (i2 != bArr.length - 1) {
                str = str + " ";
            }
        }
        return str;
    }

    public static char[] toCharArray(byte[] bArr) {
        char[] cArr = new char[bArr.length];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            cArr[i2] = (char) bArr[i2];
        }
        return cArr;
    }

    public static String toHexString(byte[] bArr) {
        String str = "";
        for (int i2 = 0; i2 < bArr.length; i2++) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            char[] cArr = HEX_CHARS;
            sb.append(cArr[(bArr[i2] >>> 4) & 15]);
            str = sb.toString() + cArr[bArr[i2] & 15];
        }
        return str;
    }

    public static byte[] xor(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length];
        for (int length = bArr.length - 1; length >= 0; length--) {
            bArr3[length] = (byte) (bArr[length] ^ bArr2[length]);
        }
        return bArr3;
    }

    public static int deepHashCode(byte[][] bArr) {
        int iDeepHashCode = 1;
        for (byte[] bArr2 : bArr) {
            iDeepHashCode = (iDeepHashCode * 31) + deepHashCode(bArr2);
        }
        return iDeepHashCode;
    }

    public static byte[] subArray(byte[] bArr, int i2) {
        return subArray(bArr, i2, bArr.length);
    }

    public static byte[] concatenate(byte[][] bArr) {
        int length = bArr[0].length;
        byte[] bArr2 = new byte[bArr.length * length];
        int i2 = 0;
        for (byte[] bArr3 : bArr) {
            System.arraycopy(bArr3, 0, bArr2, i2, length);
            i2 += length;
        }
        return bArr2;
    }

    public static boolean equals(byte[][] bArr, byte[][] bArr2) {
        if (bArr.length != bArr2.length) {
            return false;
        }
        boolean zEquals = true;
        for (int length = bArr.length - 1; length >= 0; length--) {
            zEquals &= equals(bArr[length], bArr2[length]);
        }
        return zEquals;
    }

    public static String toHexString(byte[] bArr, String str, String str2) {
        String str3 = new String(str);
        for (int i2 = 0; i2 < bArr.length; i2++) {
            StringBuilder sb = new StringBuilder();
            sb.append(str3);
            char[] cArr = HEX_CHARS;
            sb.append(cArr[(bArr[i2] >>> 4) & 15]);
            str3 = sb.toString() + cArr[bArr[i2] & 15];
            if (i2 < bArr.length - 1) {
                str3 = str3 + str2;
            }
        }
        return str3;
    }

    public static int deepHashCode(byte[][][] bArr) {
        int iDeepHashCode = 1;
        for (byte[][] bArr2 : bArr) {
            iDeepHashCode = (iDeepHashCode * 31) + deepHashCode(bArr2);
        }
        return iDeepHashCode;
    }

    public static boolean equals(byte[][][] bArr, byte[][][] bArr2) {
        if (bArr.length != bArr2.length) {
            return false;
        }
        boolean zEquals = true;
        for (int length = bArr.length - 1; length >= 0; length--) {
            byte[][] bArr3 = bArr[length];
            if (bArr3.length != bArr2[length].length) {
                return false;
            }
            for (int length2 = bArr3.length - 1; length2 >= 0; length2--) {
                zEquals &= equals(bArr[length][length2], bArr2[length][length2]);
            }
        }
        return zEquals;
    }
}

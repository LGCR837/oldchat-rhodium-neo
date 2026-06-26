package org.spongycastle.pqc.crypto.xmss;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.spongycastle.crypto.Digest;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.encoders.Hex;

/* JADX INFO: loaded from: classes.dex */
public class XMSSUtil {
    public static boolean areEqual(byte[][] bArr, byte[][] bArr2) {
        if (hasNullPointer(bArr) || hasNullPointer(bArr2)) {
            throw new NullPointerException("a or b == null");
        }
        for (int i2 = 0; i2 < bArr.length; i2++) {
            if (!Arrays.areEqual(bArr[i2], bArr2[i2])) {
                return false;
            }
        }
        return true;
    }

    public static long bytesToXBigEndian(byte[] bArr, int i2, int i3) {
        if (bArr == null) {
            throw new NullPointerException("in == null");
        }
        long j2 = 0;
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            j2 = (j2 << 8) | ((long) (bArr[i4] & 255));
        }
        return j2;
    }

    public static int calculateTau(int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            if (((i2 >> i4) & 1) == 0) {
                return i4;
            }
        }
        return 0;
    }

    public static byte[] cloneArray(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("in == null");
        }
        byte[] bArr2 = new byte[bArr.length];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr2[i2] = bArr[i2];
        }
        return bArr2;
    }

    public static void copyBytesAtOffset(byte[] bArr, byte[] bArr2, int i2) {
        if (bArr == null) {
            throw new NullPointerException("dst == null");
        }
        if (bArr2 == null) {
            throw new NullPointerException("src == null");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("offset hast to be >= 0");
        }
        if (bArr2.length + i2 > bArr.length) {
            throw new IllegalArgumentException("src length + offset must not be greater than size of destination");
        }
        for (int i3 = 0; i3 < bArr2.length; i3++) {
            bArr[i2 + i3] = bArr2[i3];
        }
    }

    public static Object deserialize(byte[] bArr) {
        return new ObjectInputStream(new ByteArrayInputStream(bArr)).readObject();
    }

    public static void dumpByteArray(byte[][] bArr) {
        if (hasNullPointer(bArr)) {
            throw new NullPointerException("x has null pointers");
        }
        for (byte[] bArr2 : bArr) {
            System.out.println(Hex.toHexString(bArr2));
        }
    }

    public static byte[] extractBytesAtOffset(byte[] bArr, int i2, int i3) {
        if (bArr == null) {
            throw new NullPointerException("src == null");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("offset hast to be >= 0");
        }
        if (i3 < 0) {
            throw new IllegalArgumentException("length hast to be >= 0");
        }
        if (i2 + i3 > bArr.length) {
            throw new IllegalArgumentException("offset + length must not be greater then size of source array");
        }
        byte[] bArr2 = new byte[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            bArr2[i4] = bArr[i2 + i4];
        }
        return bArr2;
    }

    public static int getDigestSize(Digest digest) {
        if (digest == null) {
            throw new NullPointerException("digest == null");
        }
        String algorithmName = digest.getAlgorithmName();
        if (algorithmName.equals("SHAKE128")) {
            return 32;
        }
        if (algorithmName.equals("SHAKE256")) {
            return 64;
        }
        return digest.getDigestSize();
    }

    public static int getLeafIndex(long j2, int i2) {
        return (int) (j2 & ((1 << i2) - 1));
    }

    public static long getTreeIndex(long j2, int i2) {
        return j2 >> i2;
    }

    public static boolean hasNullPointer(byte[][] bArr) {
        if (bArr == null) {
            return true;
        }
        for (byte[] bArr2 : bArr) {
            if (bArr2 == null) {
                return true;
            }
        }
        return false;
    }

    public static boolean isIndexValid(int i2, long j2) {
        if (j2 >= 0) {
            return j2 < (1 << i2);
        }
        throw new IllegalStateException("index must not be negative");
    }

    public static boolean isNewAuthenticationPathNeeded(long j2, int i2, int i3) {
        return j2 != 0 && (j2 + 1) % ((long) Math.pow((double) (1 << i2), (double) i3)) == 0;
    }

    public static boolean isNewBDSInitNeeded(long j2, int i2, int i3) {
        return j2 != 0 && j2 % ((long) Math.pow((double) (1 << i2), (double) (i3 + 1))) == 0;
    }

    public static int log2(int i2) {
        int i3 = 0;
        while (true) {
            i2 >>= 1;
            if (i2 == 0) {
                return i3;
            }
            i3++;
        }
    }

    public static void longToBigEndian(long j2, byte[] bArr, int i2) {
        if (bArr == null) {
            throw new NullPointerException("in == null");
        }
        if (bArr.length - i2 < 8) {
            throw new IllegalArgumentException("not enough space in array");
        }
        bArr[i2] = (byte) ((j2 >> 56) & 255);
        bArr[i2 + 1] = (byte) ((j2 >> 48) & 255);
        bArr[i2 + 2] = (byte) ((j2 >> 40) & 255);
        bArr[i2 + 3] = (byte) ((j2 >> 32) & 255);
        bArr[i2 + 4] = (byte) ((j2 >> 24) & 255);
        bArr[i2 + 5] = (byte) ((j2 >> 16) & 255);
        bArr[i2 + 6] = (byte) ((j2 >> 8) & 255);
        bArr[i2 + 7] = (byte) (j2 & 255);
    }

    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        objectOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] toBytesBigEndian(long j2, int i2) {
        byte[] bArr = new byte[i2];
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            bArr[i3] = (byte) j2;
            j2 >>>= 8;
        }
        return bArr;
    }

    public static byte[][] cloneArray(byte[][] bArr) {
        if (!hasNullPointer(bArr)) {
            byte[][] bArr2 = new byte[bArr.length][];
            for (int i2 = 0; i2 < bArr.length; i2++) {
                bArr2[i2] = new byte[bArr[i2].length];
                int i3 = 0;
                while (true) {
                    byte[] bArr3 = bArr[i2];
                    if (i3 < bArr3.length) {
                        bArr2[i2][i3] = bArr3[i3];
                        i3++;
                    }
                }
            }
            return bArr2;
        }
        throw new NullPointerException("in has null pointers");
    }
}

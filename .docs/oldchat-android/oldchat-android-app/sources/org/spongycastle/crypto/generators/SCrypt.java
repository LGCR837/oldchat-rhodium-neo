package org.spongycastle.crypto.generators;

import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.engines.Salsa20Engine;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public class SCrypt {
    private static void BlockMix(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int i2) {
        System.arraycopy(iArr, iArr.length - 16, iArr2, 0, 16);
        int length = iArr.length >>> 1;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = i2 * 2; i5 > 0; i5--) {
            Xor(iArr2, iArr, i3, iArr3);
            Salsa20Engine.salsaCore(8, iArr3, iArr2);
            System.arraycopy(iArr2, 0, iArr4, i4, 16);
            i4 = (length + i3) - i4;
            i3 += 16;
        }
        System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
    }

    private static void Clear(byte[] bArr) {
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
        }
    }

    private static void ClearAll(int[][] iArr) {
        for (int[] iArr2 : iArr) {
            Clear(iArr2);
        }
    }

    private static byte[] MFcrypt(byte[] bArr, byte[] bArr2, int i2, int i3, int i4, int i5) {
        int i6 = i3 * 128;
        byte[] bArrSingleIterationPBKDF2 = SingleIterationPBKDF2(bArr, bArr2, i4 * i6);
        int[] iArr = null;
        try {
            int length = bArrSingleIterationPBKDF2.length >>> 2;
            iArr = new int[length];
            Pack.littleEndianToInt(bArrSingleIterationPBKDF2, 0, iArr);
            int i7 = i6 >>> 2;
            for (int i8 = 0; i8 < length; i8 += i7) {
                SMix(iArr, i8, i2, i3);
            }
            Pack.intToLittleEndian(iArr, bArrSingleIterationPBKDF2, 0);
            byte[] bArrSingleIterationPBKDF22 = SingleIterationPBKDF2(bArr, bArrSingleIterationPBKDF2, i5);
            Clear(bArrSingleIterationPBKDF2);
            Clear(iArr);
            return bArrSingleIterationPBKDF22;
        } catch (Throwable th) {
            Clear(bArrSingleIterationPBKDF2);
            Clear(iArr);
            throw th;
        }
    }

    private static void SMix(int[] iArr, int i2, int i3, int i4) {
        int i5 = i4 * 32;
        int[] iArr2 = new int[16];
        int[] iArr3 = new int[16];
        int[] iArr4 = new int[i5];
        int[] iArr5 = new int[i5];
        int[][] iArr6 = new int[i3][];
        try {
            System.arraycopy(iArr, i2, iArr5, 0, i5);
            for (int i6 = 0; i6 < i3; i6++) {
                iArr6[i6] = Arrays.clone(iArr5);
                BlockMix(iArr5, iArr2, iArr3, iArr4, i4);
            }
            int i7 = i3 - 1;
            for (int i8 = 0; i8 < i3; i8++) {
                Xor(iArr5, iArr6[iArr5[i5 - 16] & i7], 0, iArr5);
                BlockMix(iArr5, iArr2, iArr3, iArr4, i4);
            }
            System.arraycopy(iArr5, 0, iArr, i2, i5);
            ClearAll(iArr6);
            ClearAll(new int[][]{iArr5, iArr2, iArr3, iArr4});
        } catch (Throwable th) {
            ClearAll(iArr6);
            ClearAll(new int[][]{iArr5, iArr2, iArr3, iArr4});
            throw th;
        }
    }

    private static byte[] SingleIterationPBKDF2(byte[] bArr, byte[] bArr2, int i2) {
        PKCS5S2ParametersGenerator pKCS5S2ParametersGenerator = new PKCS5S2ParametersGenerator(new SHA256Digest());
        pKCS5S2ParametersGenerator.init(bArr, bArr2, 1);
        return ((KeyParameter) pKCS5S2ParametersGenerator.generateDerivedMacParameters(i2 * 8)).getKey();
    }

    private static void Xor(int[] iArr, int[] iArr2, int i2, int[] iArr3) {
        for (int length = iArr3.length - 1; length >= 0; length--) {
            iArr3[length] = iArr[length] ^ iArr2[i2 + length];
        }
    }

    public static byte[] generate(byte[] bArr, byte[] bArr2, int i2, int i3, int i4, int i5) {
        if (bArr == null) {
            throw new IllegalArgumentException("Passphrase P must be provided.");
        }
        if (bArr2 == null) {
            throw new IllegalArgumentException("Salt S must be provided.");
        }
        if (i2 <= 1) {
            throw new IllegalArgumentException("Cost parameter N must be > 1.");
        }
        if (i3 == 1 && i2 > 65536) {
            throw new IllegalArgumentException("Cost parameter N must be > 1 and < 65536.");
        }
        if (i3 < 1) {
            throw new IllegalArgumentException("Block size r must be >= 1.");
        }
        int i6 = Integer.MAX_VALUE / (i3 * 1024);
        if (i4 >= 1 && i4 <= i6) {
            if (i5 >= 1) {
                return MFcrypt(bArr, bArr2, i2, i3, i4, i5);
            }
            throw new IllegalArgumentException("Generated key length dkLen must be >= 1.");
        }
        throw new IllegalArgumentException("Parallelisation parameter p must be >= 1 and <= " + i6 + " (based on block size r of " + i3 + ")");
    }

    private static void Clear(int[] iArr) {
        if (iArr != null) {
            Arrays.fill(iArr, 0);
        }
    }
}

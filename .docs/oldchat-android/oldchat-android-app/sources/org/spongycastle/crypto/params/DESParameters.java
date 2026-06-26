package org.spongycastle.crypto.params;

/* JADX INFO: loaded from: classes.dex */
public class DESParameters extends KeyParameter {
    public static final int DES_KEY_LENGTH = 8;
    private static byte[] DES_weak_keys = {1, 1, 1, 1, 1, 1, 1, 1, 31, 31, 31, 31, 14, 14, 14, 14, -32, -32, -32, -32, -15, -15, -15, -15, -2, -2, -2, -2, -2, -2, -2, -2, 1, -2, 1, -2, 1, -2, 1, -2, 31, -32, 31, -32, 14, -15, 14, -15, 1, -32, 1, -32, 1, -15, 1, -15, 31, -2, 31, -2, 14, -2, 14, -2, 1, 31, 1, 31, 1, 14, 1, 14, -32, -2, -32, -2, -15, -2, -15, -2, -2, 1, -2, 1, -2, 1, -2, 1, -32, 31, -32, 31, -15, 14, -15, 14, -32, 1, -32, 1, -15, 1, -15, 1, -2, 31, -2, 31, -2, 14, -2, 14, 31, 1, 31, 1, 14, 1, 14, 1, -2, -32, -2, -32, -2, -15, -2, -15};
    private static final int N_DES_WEAK_KEYS = 16;

    public DESParameters(byte[] bArr) {
        super(bArr);
        if (isWeakKey(bArr, 0)) {
            throw new IllegalArgumentException("attempt to create weak DES key");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x001c, code lost:
    
        r2 = r2 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isWeakKey(byte[] r7, int r8) {
        /*
            int r0 = r7.length
            int r0 = r0 - r8
            r1 = 8
            if (r0 < r1) goto L25
            r0 = 0
            r2 = 0
        L8:
            r3 = 16
            if (r2 >= r3) goto L24
            r3 = 0
        Ld:
            if (r3 >= r1) goto L22
            int r4 = r3 + r8
            r4 = r7[r4]
            byte[] r5 = org.spongycastle.crypto.params.DESParameters.DES_weak_keys
            int r6 = r2 * 8
            int r6 = r6 + r3
            r5 = r5[r6]
            if (r4 == r5) goto L1f
            int r2 = r2 + 1
            goto L8
        L1f:
            int r3 = r3 + 1
            goto Ld
        L22:
            r7 = 1
            return r7
        L24:
            return r0
        L25:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "key material too short."
            r7.<init>(r8)
            goto L2e
        L2d:
            throw r7
        L2e:
            goto L2d
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.params.DESParameters.isWeakKey(byte[], int):boolean");
    }

    public static void setOddParity(byte[] bArr) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            byte b2 = bArr[i2];
            bArr[i2] = (byte) (((((b2 >> 7) ^ ((((((b2 >> 1) ^ (b2 >> 2)) ^ (b2 >> 3)) ^ (b2 >> 4)) ^ (b2 >> 5)) ^ (b2 >> 6))) ^ 1) & 1) | (b2 & 254));
        }
    }
}

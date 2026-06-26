package org.spongycastle.pqc.crypto.newhope;

/* JADX INFO: loaded from: classes.dex */
class Reduce {
    static final int QInv = 12287;
    static final int RLog = 18;
    static final int RMask = 262143;

    public static short barrett(short s2) {
        int i2 = s2 & 65535;
        return (short) (i2 - (((i2 * 5) >>> 16) * 12289));
    }

    public static short montgomery(int i2) {
        return (short) (((((i2 * QInv) & RMask) * 12289) + i2) >>> 18);
    }
}

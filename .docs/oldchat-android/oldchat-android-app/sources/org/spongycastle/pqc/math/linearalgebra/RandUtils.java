package org.spongycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class RandUtils {
    public static int nextInt(SecureRandom secureRandom, int i2) {
        int iNextInt;
        int i3;
        if (((-i2) & i2) == i2) {
            return (int) ((((long) i2) * ((long) (secureRandom.nextInt() >>> 1))) >> 31);
        }
        do {
            iNextInt = secureRandom.nextInt() >>> 1;
            i3 = iNextInt % i2;
        } while ((iNextInt - i3) + (i2 - 1) < 0);
        return i3;
    }
}

package org.spongycastle.jcajce.provider.asymmetric.util;

/* JADX INFO: loaded from: classes.dex */
public class PrimeCertaintyCalculator {
    private PrimeCertaintyCalculator() {
    }

    public static int getDefaultCertainty(int i2) {
        if (i2 <= 1024) {
            return 80;
        }
        return (((i2 - 1) / 1024) * 16) + 96;
    }
}

package c;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public abstract class E1 {
    public static Bundle a(D1 d1) {
        new Bundle();
        throw null;
    }

    public static Bundle[] b(D1[] d1Arr) {
        if (d1Arr == null) {
            return null;
        }
        Bundle[] bundleArr = new Bundle[d1Arr.length];
        for (int i2 = 0; i2 < d1Arr.length; i2++) {
            D1 d1 = d1Arr[i2];
            bundleArr[i2] = a(null);
        }
        return bundleArr;
    }
}

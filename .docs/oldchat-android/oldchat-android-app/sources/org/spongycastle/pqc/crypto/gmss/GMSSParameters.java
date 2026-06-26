package org.spongycastle.pqc.crypto.gmss;

import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class GMSSParameters {

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    private int[] f7606K;
    private int[] heightOfTrees;
    private int numOfLayers;
    private int[] winternitzParameter;

    public GMSSParameters(int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        init(i2, iArr, iArr2, iArr3);
    }

    private void init(int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        String str;
        boolean z2;
        this.numOfLayers = i2;
        if (i2 == iArr2.length && i2 == iArr.length && i2 == iArr3.length) {
            z2 = true;
            str = "";
        } else {
            str = "Unexpected parameterset format";
            z2 = false;
        }
        for (int i3 = 0; i3 < this.numOfLayers; i3++) {
            int i4 = iArr3[i3];
            if (i4 < 2 || (iArr[i3] - i4) % 2 != 0) {
                str = "Wrong parameter K (K >= 2 and H-K even required)!";
                z2 = false;
            }
            if (iArr[i3] < 4 || iArr2[i3] < 2) {
                str = "Wrong parameter H or w (H > 3 and w > 1 required)!";
                z2 = false;
            }
        }
        if (!z2) {
            throw new IllegalArgumentException(str);
        }
        this.heightOfTrees = Arrays.clone(iArr);
        this.winternitzParameter = Arrays.clone(iArr2);
        this.f7606K = Arrays.clone(iArr3);
    }

    public int[] getHeightOfTrees() {
        return Arrays.clone(this.heightOfTrees);
    }

    public int[] getK() {
        return Arrays.clone(this.f7606K);
    }

    public int getNumOfLayers() {
        return this.numOfLayers;
    }

    public int[] getWinternitzParameter() {
        return Arrays.clone(this.winternitzParameter);
    }

    public GMSSParameters(int i2) {
        if (i2 <= 10) {
            init(1, new int[]{10}, new int[]{3}, new int[]{2});
        } else if (i2 <= 20) {
            init(2, new int[]{10, 10}, new int[]{5, 4}, new int[]{2, 2});
        } else {
            init(4, new int[]{10, 10, 10, 10}, new int[]{9, 9, 9, 3}, new int[]{2, 2, 2, 2});
        }
    }
}

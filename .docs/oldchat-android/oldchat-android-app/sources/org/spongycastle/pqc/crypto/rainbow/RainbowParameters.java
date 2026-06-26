package org.spongycastle.pqc.crypto.rainbow;

import org.spongycastle.crypto.CipherParameters;

/* JADX INFO: loaded from: classes.dex */
public class RainbowParameters implements CipherParameters {
    private final int[] DEFAULT_VI;
    private int[] vi;

    public RainbowParameters() {
        int[] iArr = {6, 12, 17, 22, 33};
        this.DEFAULT_VI = iArr;
        this.vi = iArr;
    }

    private void checkParams() throws Exception {
        int[] iArr;
        int i2;
        int[] iArr2 = this.vi;
        if (iArr2 == null) {
            throw new Exception("no layers defined.");
        }
        if (iArr2.length <= 1) {
            throw new Exception("Rainbow needs at least 1 layer, such that v1 < v2.");
        }
        int i3 = 0;
        do {
            iArr = this.vi;
            if (i3 >= iArr.length - 1) {
                return;
            }
            i2 = iArr[i3];
            i3++;
        } while (i2 < iArr[i3]);
        throw new Exception("v[i] has to be smaller than v[i+1]");
    }

    public int getDocLength() {
        int[] iArr = this.vi;
        return iArr[iArr.length - 1] - iArr[0];
    }

    public int getNumOfLayers() {
        return this.vi.length - 1;
    }

    public int[] getVi() {
        return this.vi;
    }

    public RainbowParameters(int[] iArr) {
        this.DEFAULT_VI = new int[]{6, 12, 17, 22, 33};
        this.vi = iArr;
        try {
            checkParams();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}

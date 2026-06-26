package org.spongycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class RainbowParameterSpec implements AlgorithmParameterSpec {
    private static final int[] DEFAULT_VI = {6, 12, 17, 22, 33};
    private int[] vi;

    public RainbowParameterSpec() {
        this.vi = DEFAULT_VI;
    }

    private void checkParams() {
        int[] iArr;
        int i2;
        int[] iArr2 = this.vi;
        if (iArr2 == null) {
            throw new IllegalArgumentException("no layers defined.");
        }
        if (iArr2.length <= 1) {
            throw new IllegalArgumentException("Rainbow needs at least 1 layer, such that v1 < v2.");
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
        throw new IllegalArgumentException("v[i] has to be smaller than v[i+1]");
    }

    public int getDocumentLength() {
        int[] iArr = this.vi;
        return iArr[iArr.length - 1] - iArr[0];
    }

    public int getNumOfLayers() {
        return this.vi.length - 1;
    }

    public int[] getVi() {
        return Arrays.clone(this.vi);
    }

    public RainbowParameterSpec(int[] iArr) {
        this.vi = iArr;
        checkParams();
    }
}

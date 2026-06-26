package org.spongycastle.pqc.jcajce.spec;

import java.security.spec.KeySpec;

/* JADX INFO: loaded from: classes.dex */
public class RainbowPublicKeySpec implements KeySpec {
    private short[][] coeffquadratic;
    private short[] coeffscalar;
    private short[][] coeffsingular;
    private int docLength;

    public RainbowPublicKeySpec(int i2, short[][] sArr, short[][] sArr2, short[] sArr3) {
        this.docLength = i2;
        this.coeffquadratic = sArr;
        this.coeffsingular = sArr2;
        this.coeffscalar = sArr3;
    }

    public short[][] getCoeffQuadratic() {
        return this.coeffquadratic;
    }

    public short[] getCoeffScalar() {
        return this.coeffscalar;
    }

    public short[][] getCoeffSingular() {
        return this.coeffsingular;
    }

    public int getDocLength() {
        return this.docLength;
    }
}

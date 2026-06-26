package org.spongycastle.jce.spec;

import java.security.spec.AlgorithmParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class ElGamalGenParameterSpec implements AlgorithmParameterSpec {
    private int primeSize;

    public ElGamalGenParameterSpec(int i2) {
        this.primeSize = i2;
    }

    public int getPrimeSize() {
        return this.primeSize;
    }
}

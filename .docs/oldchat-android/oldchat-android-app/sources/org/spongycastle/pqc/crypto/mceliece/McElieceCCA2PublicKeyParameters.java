package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

/* JADX INFO: loaded from: classes.dex */
public class McElieceCCA2PublicKeyParameters extends McElieceCCA2KeyParameters {
    private GF2Matrix matrixG;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private int f7621n;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private int f7622t;

    public McElieceCCA2PublicKeyParameters(int i2, int i3, GF2Matrix gF2Matrix, String str) {
        super(false, str);
        this.f7621n = i2;
        this.f7622t = i3;
        this.matrixG = new GF2Matrix(gF2Matrix);
    }

    public GF2Matrix getG() {
        return this.matrixG;
    }

    public int getK() {
        return this.matrixG.getNumRows();
    }

    public int getN() {
        return this.f7621n;
    }

    public int getT() {
        return this.f7622t;
    }
}

package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

/* JADX INFO: loaded from: classes.dex */
public class McEliecePublicKeyParameters extends McElieceKeyParameters {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private GF2Matrix f7644g;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private int f7645n;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private int f7646t;

    public McEliecePublicKeyParameters(int i2, int i3, GF2Matrix gF2Matrix) {
        super(false, null);
        this.f7645n = i2;
        this.f7646t = i3;
        this.f7644g = new GF2Matrix(gF2Matrix);
    }

    public GF2Matrix getG() {
        return this.f7644g;
    }

    public int getK() {
        return this.f7644g.getNumRows();
    }

    public int getN() {
        return this.f7645n;
    }

    public int getT() {
        return this.f7646t;
    }
}

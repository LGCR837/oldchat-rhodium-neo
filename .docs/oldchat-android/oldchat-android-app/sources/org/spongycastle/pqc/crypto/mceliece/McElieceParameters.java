package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.pqc.math.linearalgebra.PolynomialRingGF2;

/* JADX INFO: loaded from: classes.dex */
public class McElieceParameters implements CipherParameters {
    public static final int DEFAULT_M = 11;
    public static final int DEFAULT_T = 50;
    private Digest digest;
    private int fieldPoly;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f7635m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private int f7636n;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private int f7637t;

    public McElieceParameters() {
        this(11, 50);
    }

    public int getFieldPoly() {
        return this.fieldPoly;
    }

    public int getM() {
        return this.f7635m;
    }

    public int getN() {
        return this.f7636n;
    }

    public int getT() {
        return this.f7637t;
    }

    public McElieceParameters(Digest digest) {
        this(11, 50, digest);
    }

    public McElieceParameters(int i2) {
        this(i2, (Digest) null);
    }

    public McElieceParameters(int i2, Digest digest) {
        if (i2 >= 1) {
            this.f7635m = 0;
            this.f7636n = 1;
            while (true) {
                int i3 = this.f7636n;
                if (i3 < i2) {
                    this.f7636n = i3 << 1;
                    this.f7635m++;
                } else {
                    int i4 = i3 >>> 1;
                    this.f7637t = i4;
                    int i5 = this.f7635m;
                    this.f7637t = i4 / i5;
                    this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(i5);
                    this.digest = digest;
                    return;
                }
            }
        } else {
            throw new IllegalArgumentException("key size must be positive");
        }
    }

    public McElieceParameters(int i2, int i3) {
        this(i2, i3, (Digest) null);
    }

    public McElieceParameters(int i2, int i3, Digest digest) {
        if (i2 < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (i2 <= 32) {
            this.f7635m = i2;
            int i4 = 1 << i2;
            this.f7636n = i4;
            if (i3 < 0) {
                throw new IllegalArgumentException("t must be positive");
            }
            if (i3 <= i4) {
                this.f7637t = i3;
                this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(i2);
                this.digest = digest;
                return;
            }
            throw new IllegalArgumentException("t must be less than n = 2^m");
        }
        throw new IllegalArgumentException("m is too large");
    }

    public McElieceParameters(int i2, int i3, int i4) {
        this(i2, i3, i4, null);
    }

    public McElieceParameters(int i2, int i3, int i4, Digest digest) {
        this.f7635m = i2;
        if (i2 < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (i2 <= 32) {
            int i5 = 1 << i2;
            this.f7636n = i5;
            this.f7637t = i3;
            if (i3 < 0) {
                throw new IllegalArgumentException("t must be positive");
            }
            if (i3 <= i5) {
                if (PolynomialRingGF2.degree(i4) == i2 && PolynomialRingGF2.isIrreducible(i4)) {
                    this.fieldPoly = i4;
                    this.digest = digest;
                    return;
                }
                throw new IllegalArgumentException("polynomial is not a field polynomial for GF(2^m)");
            }
            throw new IllegalArgumentException("t must be less than n = 2^m");
        }
        throw new IllegalArgumentException(" m is too large");
    }
}

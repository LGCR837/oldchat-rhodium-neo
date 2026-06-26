package org.spongycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.pqc.math.linearalgebra.PolynomialRingGF2;

/* JADX INFO: loaded from: classes.dex */
public class McElieceKeyGenParameterSpec implements AlgorithmParameterSpec {
    public static final int DEFAULT_M = 11;
    public static final int DEFAULT_T = 50;
    private int fieldPoly;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f7682m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private int f7683n;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private int f7684t;

    public McElieceKeyGenParameterSpec() {
        this(11, 50);
    }

    public int getFieldPoly() {
        return this.fieldPoly;
    }

    public int getM() {
        return this.f7682m;
    }

    public int getN() {
        return this.f7683n;
    }

    public int getT() {
        return this.f7684t;
    }

    public McElieceKeyGenParameterSpec(int i2) {
        if (i2 < 1) {
            throw new IllegalArgumentException("key size must be positive");
        }
        this.f7682m = 0;
        this.f7683n = 1;
        while (true) {
            int i3 = this.f7683n;
            if (i3 >= i2) {
                int i4 = i3 >>> 1;
                this.f7684t = i4;
                int i5 = this.f7682m;
                this.f7684t = i4 / i5;
                this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(i5);
                return;
            }
            this.f7683n = i3 << 1;
            this.f7682m++;
        }
    }

    public McElieceKeyGenParameterSpec(int i2, int i3) {
        if (i2 < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (i2 <= 32) {
            this.f7682m = i2;
            int i4 = 1 << i2;
            this.f7683n = i4;
            if (i3 < 0) {
                throw new IllegalArgumentException("t must be positive");
            }
            if (i3 <= i4) {
                this.f7684t = i3;
                this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(i2);
                return;
            }
            throw new IllegalArgumentException("t must be less than n = 2^m");
        }
        throw new IllegalArgumentException("m is too large");
    }

    public McElieceKeyGenParameterSpec(int i2, int i3, int i4) {
        this.f7682m = i2;
        if (i2 < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (i2 <= 32) {
            int i5 = 1 << i2;
            this.f7683n = i5;
            this.f7684t = i3;
            if (i3 < 0) {
                throw new IllegalArgumentException("t must be positive");
            }
            if (i3 <= i5) {
                if (PolynomialRingGF2.degree(i4) == i2 && PolynomialRingGF2.isIrreducible(i4)) {
                    this.fieldPoly = i4;
                    return;
                }
                throw new IllegalArgumentException("polynomial is not a field polynomial for GF(2^m)");
            }
            throw new IllegalArgumentException("t must be less than n = 2^m");
        }
        throw new IllegalArgumentException(" m is too large");
    }
}

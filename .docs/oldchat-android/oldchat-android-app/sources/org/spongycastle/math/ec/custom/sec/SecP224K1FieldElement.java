package org.spongycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat224;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class SecP224K1FieldElement extends ECFieldElement {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    protected int[] f7547x;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public static final BigInteger f7546Q = SecP224K1Curve.f7544q;
    private static final int[] PRECOMP_POW2 = {868209154, -587542221, 579297866, -1014948952, -1470801668, 514782679, -1897982644};

    public SecP224K1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f7546Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP224K1FieldElement");
        }
        this.f7547x = SecP224K1Field.fromBigInteger(bigInteger);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat224.create();
        SecP224K1Field.add(this.f7547x, ((SecP224K1FieldElement) eCFieldElement).f7547x, iArrCreate);
        return new SecP224K1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement addOne() {
        int[] iArrCreate = Nat224.create();
        SecP224K1Field.addOne(this.f7547x, iArrCreate);
        return new SecP224K1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat224.create();
        Mod.invert(SecP224K1Field.f7545P, ((SecP224K1FieldElement) eCFieldElement).f7547x, iArrCreate);
        SecP224K1Field.multiply(iArrCreate, this.f7547x, iArrCreate);
        return new SecP224K1FieldElement(iArrCreate);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecP224K1FieldElement) {
            return Nat224.eq(this.f7547x, ((SecP224K1FieldElement) obj).f7547x);
        }
        return false;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public String getFieldName() {
        return "SecP224K1Field";
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public int getFieldSize() {
        return f7546Q.bitLength();
    }

    public int hashCode() {
        return f7546Q.hashCode() ^ Arrays.hashCode(this.f7547x, 0, 7);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement invert() {
        int[] iArrCreate = Nat224.create();
        Mod.invert(SecP224K1Field.f7545P, this.f7547x, iArrCreate);
        return new SecP224K1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean isOne() {
        return Nat224.isOne(this.f7547x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean isZero() {
        return Nat224.isZero(this.f7547x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat224.create();
        SecP224K1Field.multiply(this.f7547x, ((SecP224K1FieldElement) eCFieldElement).f7547x, iArrCreate);
        return new SecP224K1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement negate() {
        int[] iArrCreate = Nat224.create();
        SecP224K1Field.negate(this.f7547x, iArrCreate);
        return new SecP224K1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement sqrt() {
        int[] iArr = this.f7547x;
        if (Nat224.isZero(iArr) || Nat224.isOne(iArr)) {
            return this;
        }
        int[] iArrCreate = Nat224.create();
        SecP224K1Field.square(iArr, iArrCreate);
        SecP224K1Field.multiply(iArrCreate, iArr, iArrCreate);
        SecP224K1Field.square(iArrCreate, iArrCreate);
        SecP224K1Field.multiply(iArrCreate, iArr, iArrCreate);
        int[] iArrCreate2 = Nat224.create();
        SecP224K1Field.square(iArrCreate, iArrCreate2);
        SecP224K1Field.multiply(iArrCreate2, iArr, iArrCreate2);
        int[] iArrCreate3 = Nat224.create();
        SecP224K1Field.squareN(iArrCreate2, 4, iArrCreate3);
        SecP224K1Field.multiply(iArrCreate3, iArrCreate2, iArrCreate3);
        int[] iArrCreate4 = Nat224.create();
        SecP224K1Field.squareN(iArrCreate3, 3, iArrCreate4);
        SecP224K1Field.multiply(iArrCreate4, iArrCreate, iArrCreate4);
        SecP224K1Field.squareN(iArrCreate4, 8, iArrCreate4);
        SecP224K1Field.multiply(iArrCreate4, iArrCreate3, iArrCreate4);
        SecP224K1Field.squareN(iArrCreate4, 4, iArrCreate3);
        SecP224K1Field.multiply(iArrCreate3, iArrCreate2, iArrCreate3);
        SecP224K1Field.squareN(iArrCreate3, 19, iArrCreate2);
        SecP224K1Field.multiply(iArrCreate2, iArrCreate4, iArrCreate2);
        int[] iArrCreate5 = Nat224.create();
        SecP224K1Field.squareN(iArrCreate2, 42, iArrCreate5);
        SecP224K1Field.multiply(iArrCreate5, iArrCreate2, iArrCreate5);
        SecP224K1Field.squareN(iArrCreate5, 23, iArrCreate2);
        SecP224K1Field.multiply(iArrCreate2, iArrCreate3, iArrCreate2);
        SecP224K1Field.squareN(iArrCreate2, 84, iArrCreate3);
        SecP224K1Field.multiply(iArrCreate3, iArrCreate5, iArrCreate3);
        SecP224K1Field.squareN(iArrCreate3, 20, iArrCreate3);
        SecP224K1Field.multiply(iArrCreate3, iArrCreate4, iArrCreate3);
        SecP224K1Field.squareN(iArrCreate3, 3, iArrCreate3);
        SecP224K1Field.multiply(iArrCreate3, iArr, iArrCreate3);
        SecP224K1Field.squareN(iArrCreate3, 2, iArrCreate3);
        SecP224K1Field.multiply(iArrCreate3, iArr, iArrCreate3);
        SecP224K1Field.squareN(iArrCreate3, 4, iArrCreate3);
        SecP224K1Field.multiply(iArrCreate3, iArrCreate, iArrCreate3);
        SecP224K1Field.square(iArrCreate3, iArrCreate3);
        SecP224K1Field.square(iArrCreate3, iArrCreate5);
        if (Nat224.eq(iArr, iArrCreate5)) {
            return new SecP224K1FieldElement(iArrCreate3);
        }
        SecP224K1Field.multiply(iArrCreate3, PRECOMP_POW2, iArrCreate3);
        SecP224K1Field.square(iArrCreate3, iArrCreate5);
        if (Nat224.eq(iArr, iArrCreate5)) {
            return new SecP224K1FieldElement(iArrCreate3);
        }
        return null;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement square() {
        int[] iArrCreate = Nat224.create();
        SecP224K1Field.square(this.f7547x, iArrCreate);
        return new SecP224K1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat224.create();
        SecP224K1Field.subtract(this.f7547x, ((SecP224K1FieldElement) eCFieldElement).f7547x, iArrCreate);
        return new SecP224K1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean testBitZero() {
        return Nat224.getBit(this.f7547x, 0) == 1;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat224.toBigInteger(this.f7547x);
    }

    public SecP224K1FieldElement() {
        this.f7547x = Nat224.create();
    }

    public SecP224K1FieldElement(int[] iArr) {
        this.f7547x = iArr;
    }
}

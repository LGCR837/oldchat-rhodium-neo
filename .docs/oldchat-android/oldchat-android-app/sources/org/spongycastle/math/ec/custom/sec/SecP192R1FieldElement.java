package org.spongycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat192;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class SecP192R1FieldElement extends ECFieldElement {

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public static final BigInteger f7542Q = SecP192R1Curve.f7539q;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    protected int[] f7543x;

    public SecP192R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f7542Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP192R1FieldElement");
        }
        this.f7543x = SecP192R1Field.fromBigInteger(bigInteger);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat192.create();
        SecP192R1Field.add(this.f7543x, ((SecP192R1FieldElement) eCFieldElement).f7543x, iArrCreate);
        return new SecP192R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement addOne() {
        int[] iArrCreate = Nat192.create();
        SecP192R1Field.addOne(this.f7543x, iArrCreate);
        return new SecP192R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat192.create();
        Mod.invert(SecP192R1Field.f7541P, ((SecP192R1FieldElement) eCFieldElement).f7543x, iArrCreate);
        SecP192R1Field.multiply(iArrCreate, this.f7543x, iArrCreate);
        return new SecP192R1FieldElement(iArrCreate);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecP192R1FieldElement) {
            return Nat192.eq(this.f7543x, ((SecP192R1FieldElement) obj).f7543x);
        }
        return false;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public String getFieldName() {
        return "SecP192R1Field";
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public int getFieldSize() {
        return f7542Q.bitLength();
    }

    public int hashCode() {
        return f7542Q.hashCode() ^ Arrays.hashCode(this.f7543x, 0, 6);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement invert() {
        int[] iArrCreate = Nat192.create();
        Mod.invert(SecP192R1Field.f7541P, this.f7543x, iArrCreate);
        return new SecP192R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean isOne() {
        return Nat192.isOne(this.f7543x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean isZero() {
        return Nat192.isZero(this.f7543x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat192.create();
        SecP192R1Field.multiply(this.f7543x, ((SecP192R1FieldElement) eCFieldElement).f7543x, iArrCreate);
        return new SecP192R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement negate() {
        int[] iArrCreate = Nat192.create();
        SecP192R1Field.negate(this.f7543x, iArrCreate);
        return new SecP192R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement sqrt() {
        int[] iArr = this.f7543x;
        if (Nat192.isZero(iArr) || Nat192.isOne(iArr)) {
            return this;
        }
        int[] iArrCreate = Nat192.create();
        int[] iArrCreate2 = Nat192.create();
        SecP192R1Field.square(iArr, iArrCreate);
        SecP192R1Field.multiply(iArrCreate, iArr, iArrCreate);
        SecP192R1Field.squareN(iArrCreate, 2, iArrCreate2);
        SecP192R1Field.multiply(iArrCreate2, iArrCreate, iArrCreate2);
        SecP192R1Field.squareN(iArrCreate2, 4, iArrCreate);
        SecP192R1Field.multiply(iArrCreate, iArrCreate2, iArrCreate);
        SecP192R1Field.squareN(iArrCreate, 8, iArrCreate2);
        SecP192R1Field.multiply(iArrCreate2, iArrCreate, iArrCreate2);
        SecP192R1Field.squareN(iArrCreate2, 16, iArrCreate);
        SecP192R1Field.multiply(iArrCreate, iArrCreate2, iArrCreate);
        SecP192R1Field.squareN(iArrCreate, 32, iArrCreate2);
        SecP192R1Field.multiply(iArrCreate2, iArrCreate, iArrCreate2);
        SecP192R1Field.squareN(iArrCreate2, 64, iArrCreate);
        SecP192R1Field.multiply(iArrCreate, iArrCreate2, iArrCreate);
        SecP192R1Field.squareN(iArrCreate, 62, iArrCreate);
        SecP192R1Field.square(iArrCreate, iArrCreate2);
        if (Nat192.eq(iArr, iArrCreate2)) {
            return new SecP192R1FieldElement(iArrCreate);
        }
        return null;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement square() {
        int[] iArrCreate = Nat192.create();
        SecP192R1Field.square(this.f7543x, iArrCreate);
        return new SecP192R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat192.create();
        SecP192R1Field.subtract(this.f7543x, ((SecP192R1FieldElement) eCFieldElement).f7543x, iArrCreate);
        return new SecP192R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean testBitZero() {
        return Nat192.getBit(this.f7543x, 0) == 1;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat192.toBigInteger(this.f7543x);
    }

    public SecP192R1FieldElement() {
        this.f7543x = Nat192.create();
    }

    public SecP192R1FieldElement(int[] iArr) {
        this.f7543x = iArr;
    }
}

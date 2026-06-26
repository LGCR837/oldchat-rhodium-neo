package org.spongycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class SecP384R1FieldElement extends ECFieldElement {

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public static final BigInteger f7565Q = SecP384R1Curve.f7562q;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    protected int[] f7566x;

    public SecP384R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f7565Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP384R1FieldElement");
        }
        this.f7566x = SecP384R1Field.fromBigInteger(bigInteger);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat.create(12);
        SecP384R1Field.add(this.f7566x, ((SecP384R1FieldElement) eCFieldElement).f7566x, iArrCreate);
        return new SecP384R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement addOne() {
        int[] iArrCreate = Nat.create(12);
        SecP384R1Field.addOne(this.f7566x, iArrCreate);
        return new SecP384R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat.create(12);
        Mod.invert(SecP384R1Field.f7564P, ((SecP384R1FieldElement) eCFieldElement).f7566x, iArrCreate);
        SecP384R1Field.multiply(iArrCreate, this.f7566x, iArrCreate);
        return new SecP384R1FieldElement(iArrCreate);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecP384R1FieldElement) {
            return Nat.eq(12, this.f7566x, ((SecP384R1FieldElement) obj).f7566x);
        }
        return false;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public String getFieldName() {
        return "SecP384R1Field";
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public int getFieldSize() {
        return f7565Q.bitLength();
    }

    public int hashCode() {
        return f7565Q.hashCode() ^ Arrays.hashCode(this.f7566x, 0, 12);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement invert() {
        int[] iArrCreate = Nat.create(12);
        Mod.invert(SecP384R1Field.f7564P, this.f7566x, iArrCreate);
        return new SecP384R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean isOne() {
        return Nat.isOne(12, this.f7566x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean isZero() {
        return Nat.isZero(12, this.f7566x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat.create(12);
        SecP384R1Field.multiply(this.f7566x, ((SecP384R1FieldElement) eCFieldElement).f7566x, iArrCreate);
        return new SecP384R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement negate() {
        int[] iArrCreate = Nat.create(12);
        SecP384R1Field.negate(this.f7566x, iArrCreate);
        return new SecP384R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement sqrt() {
        int[] iArr = this.f7566x;
        if (Nat.isZero(12, iArr) || Nat.isOne(12, iArr)) {
            return this;
        }
        int[] iArrCreate = Nat.create(12);
        int[] iArrCreate2 = Nat.create(12);
        int[] iArrCreate3 = Nat.create(12);
        int[] iArrCreate4 = Nat.create(12);
        SecP384R1Field.square(iArr, iArrCreate);
        SecP384R1Field.multiply(iArrCreate, iArr, iArrCreate);
        SecP384R1Field.squareN(iArrCreate, 2, iArrCreate2);
        SecP384R1Field.multiply(iArrCreate2, iArrCreate, iArrCreate2);
        SecP384R1Field.square(iArrCreate2, iArrCreate2);
        SecP384R1Field.multiply(iArrCreate2, iArr, iArrCreate2);
        SecP384R1Field.squareN(iArrCreate2, 5, iArrCreate3);
        SecP384R1Field.multiply(iArrCreate3, iArrCreate2, iArrCreate3);
        SecP384R1Field.squareN(iArrCreate3, 5, iArrCreate4);
        SecP384R1Field.multiply(iArrCreate4, iArrCreate2, iArrCreate4);
        SecP384R1Field.squareN(iArrCreate4, 15, iArrCreate2);
        SecP384R1Field.multiply(iArrCreate2, iArrCreate4, iArrCreate2);
        SecP384R1Field.squareN(iArrCreate2, 2, iArrCreate3);
        SecP384R1Field.multiply(iArrCreate, iArrCreate3, iArrCreate);
        SecP384R1Field.squareN(iArrCreate3, 28, iArrCreate3);
        SecP384R1Field.multiply(iArrCreate2, iArrCreate3, iArrCreate2);
        SecP384R1Field.squareN(iArrCreate2, 60, iArrCreate3);
        SecP384R1Field.multiply(iArrCreate3, iArrCreate2, iArrCreate3);
        SecP384R1Field.squareN(iArrCreate3, 120, iArrCreate2);
        SecP384R1Field.multiply(iArrCreate2, iArrCreate3, iArrCreate2);
        SecP384R1Field.squareN(iArrCreate2, 15, iArrCreate2);
        SecP384R1Field.multiply(iArrCreate2, iArrCreate4, iArrCreate2);
        SecP384R1Field.squareN(iArrCreate2, 33, iArrCreate2);
        SecP384R1Field.multiply(iArrCreate2, iArrCreate, iArrCreate2);
        SecP384R1Field.squareN(iArrCreate2, 64, iArrCreate2);
        SecP384R1Field.multiply(iArrCreate2, iArr, iArrCreate2);
        SecP384R1Field.squareN(iArrCreate2, 30, iArrCreate);
        SecP384R1Field.square(iArrCreate, iArrCreate2);
        if (Nat.eq(12, iArr, iArrCreate2)) {
            return new SecP384R1FieldElement(iArrCreate);
        }
        return null;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement square() {
        int[] iArrCreate = Nat.create(12);
        SecP384R1Field.square(this.f7566x, iArrCreate);
        return new SecP384R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat.create(12);
        SecP384R1Field.subtract(this.f7566x, ((SecP384R1FieldElement) eCFieldElement).f7566x, iArrCreate);
        return new SecP384R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean testBitZero() {
        return Nat.getBit(this.f7566x, 0) == 1;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat.toBigInteger(12, this.f7566x);
    }

    public SecP384R1FieldElement() {
        this.f7566x = Nat.create(12);
    }

    public SecP384R1FieldElement(int[] iArr) {
        this.f7566x = iArr;
    }
}

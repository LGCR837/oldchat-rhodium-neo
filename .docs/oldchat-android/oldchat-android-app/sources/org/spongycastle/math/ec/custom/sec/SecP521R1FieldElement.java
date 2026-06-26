package org.spongycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class SecP521R1FieldElement extends ECFieldElement {

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public static final BigInteger f7569Q = SecP521R1Curve.f7567q;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    protected int[] f7570x;

    public SecP521R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f7569Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP521R1FieldElement");
        }
        this.f7570x = SecP521R1Field.fromBigInteger(bigInteger);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat.create(17);
        SecP521R1Field.add(this.f7570x, ((SecP521R1FieldElement) eCFieldElement).f7570x, iArrCreate);
        return new SecP521R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement addOne() {
        int[] iArrCreate = Nat.create(17);
        SecP521R1Field.addOne(this.f7570x, iArrCreate);
        return new SecP521R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat.create(17);
        Mod.invert(SecP521R1Field.f7568P, ((SecP521R1FieldElement) eCFieldElement).f7570x, iArrCreate);
        SecP521R1Field.multiply(iArrCreate, this.f7570x, iArrCreate);
        return new SecP521R1FieldElement(iArrCreate);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecP521R1FieldElement) {
            return Nat.eq(17, this.f7570x, ((SecP521R1FieldElement) obj).f7570x);
        }
        return false;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public String getFieldName() {
        return "SecP521R1Field";
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public int getFieldSize() {
        return f7569Q.bitLength();
    }

    public int hashCode() {
        return f7569Q.hashCode() ^ Arrays.hashCode(this.f7570x, 0, 17);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement invert() {
        int[] iArrCreate = Nat.create(17);
        Mod.invert(SecP521R1Field.f7568P, this.f7570x, iArrCreate);
        return new SecP521R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean isOne() {
        return Nat.isOne(17, this.f7570x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean isZero() {
        return Nat.isZero(17, this.f7570x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat.create(17);
        SecP521R1Field.multiply(this.f7570x, ((SecP521R1FieldElement) eCFieldElement).f7570x, iArrCreate);
        return new SecP521R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement negate() {
        int[] iArrCreate = Nat.create(17);
        SecP521R1Field.negate(this.f7570x, iArrCreate);
        return new SecP521R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement sqrt() {
        int[] iArr = this.f7570x;
        if (Nat.isZero(17, iArr) || Nat.isOne(17, iArr)) {
            return this;
        }
        int[] iArrCreate = Nat.create(17);
        int[] iArrCreate2 = Nat.create(17);
        SecP521R1Field.squareN(iArr, 519, iArrCreate);
        SecP521R1Field.square(iArrCreate, iArrCreate2);
        if (Nat.eq(17, iArr, iArrCreate2)) {
            return new SecP521R1FieldElement(iArrCreate);
        }
        return null;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement square() {
        int[] iArrCreate = Nat.create(17);
        SecP521R1Field.square(this.f7570x, iArrCreate);
        return new SecP521R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat.create(17);
        SecP521R1Field.subtract(this.f7570x, ((SecP521R1FieldElement) eCFieldElement).f7570x, iArrCreate);
        return new SecP521R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean testBitZero() {
        return Nat.getBit(this.f7570x, 0) == 1;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat.toBigInteger(17, this.f7570x);
    }

    public SecP521R1FieldElement() {
        this.f7570x = Nat.create(17);
    }

    public SecP521R1FieldElement(int[] iArr) {
        this.f7570x = iArr;
    }
}

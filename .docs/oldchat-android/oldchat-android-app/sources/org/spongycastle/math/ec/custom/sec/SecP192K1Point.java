package org.spongycastle.math.ec.custom.sec;

import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat192;

/* JADX INFO: loaded from: classes.dex */
public class SecP192K1Point extends ECPoint.AbstractFp {
    public SecP192K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, false);
    }

    @Override // org.spongycastle.math.ec.ECPoint
    public ECPoint add(ECPoint eCPoint) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        if (isInfinity()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return this;
        }
        if (this == eCPoint) {
            return twice();
        }
        ECCurve curve = getCurve();
        SecP192K1FieldElement secP192K1FieldElement = (SecP192K1FieldElement) this.f7506x;
        SecP192K1FieldElement secP192K1FieldElement2 = (SecP192K1FieldElement) this.f7507y;
        SecP192K1FieldElement secP192K1FieldElement3 = (SecP192K1FieldElement) eCPoint.getXCoord();
        SecP192K1FieldElement secP192K1FieldElement4 = (SecP192K1FieldElement) eCPoint.getYCoord();
        SecP192K1FieldElement secP192K1FieldElement5 = (SecP192K1FieldElement) this.zs[0];
        SecP192K1FieldElement secP192K1FieldElement6 = (SecP192K1FieldElement) eCPoint.getZCoord(0);
        int[] iArrCreateExt = Nat192.createExt();
        int[] iArrCreate = Nat192.create();
        int[] iArrCreate2 = Nat192.create();
        int[] iArrCreate3 = Nat192.create();
        boolean zIsOne = secP192K1FieldElement5.isOne();
        if (zIsOne) {
            iArr = secP192K1FieldElement3.f7538x;
            iArr2 = secP192K1FieldElement4.f7538x;
        } else {
            SecP192K1Field.square(secP192K1FieldElement5.f7538x, iArrCreate2);
            SecP192K1Field.multiply(iArrCreate2, secP192K1FieldElement3.f7538x, iArrCreate);
            SecP192K1Field.multiply(iArrCreate2, secP192K1FieldElement5.f7538x, iArrCreate2);
            SecP192K1Field.multiply(iArrCreate2, secP192K1FieldElement4.f7538x, iArrCreate2);
            iArr = iArrCreate;
            iArr2 = iArrCreate2;
        }
        boolean zIsOne2 = secP192K1FieldElement6.isOne();
        if (zIsOne2) {
            iArr3 = secP192K1FieldElement.f7538x;
            iArr4 = secP192K1FieldElement2.f7538x;
        } else {
            SecP192K1Field.square(secP192K1FieldElement6.f7538x, iArrCreate3);
            SecP192K1Field.multiply(iArrCreate3, secP192K1FieldElement.f7538x, iArrCreateExt);
            SecP192K1Field.multiply(iArrCreate3, secP192K1FieldElement6.f7538x, iArrCreate3);
            SecP192K1Field.multiply(iArrCreate3, secP192K1FieldElement2.f7538x, iArrCreate3);
            iArr3 = iArrCreateExt;
            iArr4 = iArrCreate3;
        }
        int[] iArrCreate4 = Nat192.create();
        SecP192K1Field.subtract(iArr3, iArr, iArrCreate4);
        SecP192K1Field.subtract(iArr4, iArr2, iArrCreate);
        if (Nat192.isZero(iArrCreate4)) {
            return Nat192.isZero(iArrCreate) ? twice() : curve.getInfinity();
        }
        SecP192K1Field.square(iArrCreate4, iArrCreate2);
        int[] iArrCreate5 = Nat192.create();
        SecP192K1Field.multiply(iArrCreate2, iArrCreate4, iArrCreate5);
        SecP192K1Field.multiply(iArrCreate2, iArr3, iArrCreate2);
        SecP192K1Field.negate(iArrCreate5, iArrCreate5);
        Nat192.mul(iArr4, iArrCreate5, iArrCreateExt);
        SecP192K1Field.reduce32(Nat192.addBothTo(iArrCreate2, iArrCreate2, iArrCreate5), iArrCreate5);
        SecP192K1FieldElement secP192K1FieldElement7 = new SecP192K1FieldElement(iArrCreate3);
        SecP192K1Field.square(iArrCreate, secP192K1FieldElement7.f7538x);
        int[] iArr5 = secP192K1FieldElement7.f7538x;
        SecP192K1Field.subtract(iArr5, iArrCreate5, iArr5);
        SecP192K1FieldElement secP192K1FieldElement8 = new SecP192K1FieldElement(iArrCreate5);
        SecP192K1Field.subtract(iArrCreate2, secP192K1FieldElement7.f7538x, secP192K1FieldElement8.f7538x);
        SecP192K1Field.multiplyAddToExt(secP192K1FieldElement8.f7538x, iArrCreate, iArrCreateExt);
        SecP192K1Field.reduce(iArrCreateExt, secP192K1FieldElement8.f7538x);
        SecP192K1FieldElement secP192K1FieldElement9 = new SecP192K1FieldElement(iArrCreate4);
        if (!zIsOne) {
            int[] iArr6 = secP192K1FieldElement9.f7538x;
            SecP192K1Field.multiply(iArr6, secP192K1FieldElement5.f7538x, iArr6);
        }
        if (!zIsOne2) {
            int[] iArr7 = secP192K1FieldElement9.f7538x;
            SecP192K1Field.multiply(iArr7, secP192K1FieldElement6.f7538x, iArr7);
        }
        return new SecP192K1Point(curve, secP192K1FieldElement7, secP192K1FieldElement8, new ECFieldElement[]{secP192K1FieldElement9}, this.withCompression);
    }

    @Override // org.spongycastle.math.ec.ECPoint
    public ECPoint detach() {
        return new SecP192K1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    @Override // org.spongycastle.math.ec.ECPoint
    public ECPoint negate() {
        return isInfinity() ? this : new SecP192K1Point(this.curve, this.f7506x, this.f7507y.negate(), this.zs, this.withCompression);
    }

    @Override // org.spongycastle.math.ec.ECPoint
    public ECPoint threeTimes() {
        return (isInfinity() || this.f7507y.isZero()) ? this : twice().add(this);
    }

    @Override // org.spongycastle.math.ec.ECPoint
    public ECPoint twice() {
        if (isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        SecP192K1FieldElement secP192K1FieldElement = (SecP192K1FieldElement) this.f7507y;
        if (secP192K1FieldElement.isZero()) {
            return curve.getInfinity();
        }
        SecP192K1FieldElement secP192K1FieldElement2 = (SecP192K1FieldElement) this.f7506x;
        SecP192K1FieldElement secP192K1FieldElement3 = (SecP192K1FieldElement) this.zs[0];
        int[] iArrCreate = Nat192.create();
        SecP192K1Field.square(secP192K1FieldElement.f7538x, iArrCreate);
        int[] iArrCreate2 = Nat192.create();
        SecP192K1Field.square(iArrCreate, iArrCreate2);
        int[] iArrCreate3 = Nat192.create();
        SecP192K1Field.square(secP192K1FieldElement2.f7538x, iArrCreate3);
        SecP192K1Field.reduce32(Nat192.addBothTo(iArrCreate3, iArrCreate3, iArrCreate3), iArrCreate3);
        SecP192K1Field.multiply(iArrCreate, secP192K1FieldElement2.f7538x, iArrCreate);
        SecP192K1Field.reduce32(Nat.shiftUpBits(6, iArrCreate, 2, 0), iArrCreate);
        int[] iArrCreate4 = Nat192.create();
        SecP192K1Field.reduce32(Nat.shiftUpBits(6, iArrCreate2, 3, 0, iArrCreate4), iArrCreate4);
        SecP192K1FieldElement secP192K1FieldElement4 = new SecP192K1FieldElement(iArrCreate2);
        SecP192K1Field.square(iArrCreate3, secP192K1FieldElement4.f7538x);
        int[] iArr = secP192K1FieldElement4.f7538x;
        SecP192K1Field.subtract(iArr, iArrCreate, iArr);
        int[] iArr2 = secP192K1FieldElement4.f7538x;
        SecP192K1Field.subtract(iArr2, iArrCreate, iArr2);
        SecP192K1FieldElement secP192K1FieldElement5 = new SecP192K1FieldElement(iArrCreate);
        SecP192K1Field.subtract(iArrCreate, secP192K1FieldElement4.f7538x, secP192K1FieldElement5.f7538x);
        int[] iArr3 = secP192K1FieldElement5.f7538x;
        SecP192K1Field.multiply(iArr3, iArrCreate3, iArr3);
        int[] iArr4 = secP192K1FieldElement5.f7538x;
        SecP192K1Field.subtract(iArr4, iArrCreate4, iArr4);
        SecP192K1FieldElement secP192K1FieldElement6 = new SecP192K1FieldElement(iArrCreate3);
        SecP192K1Field.twice(secP192K1FieldElement.f7538x, secP192K1FieldElement6.f7538x);
        if (!secP192K1FieldElement3.isOne()) {
            int[] iArr5 = secP192K1FieldElement6.f7538x;
            SecP192K1Field.multiply(iArr5, secP192K1FieldElement3.f7538x, iArr5);
        }
        return new SecP192K1Point(curve, secP192K1FieldElement4, secP192K1FieldElement5, new ECFieldElement[]{secP192K1FieldElement6}, this.withCompression);
    }

    @Override // org.spongycastle.math.ec.ECPoint
    public ECPoint twicePlus(ECPoint eCPoint) {
        return this == eCPoint ? threeTimes() : isInfinity() ? eCPoint : eCPoint.isInfinity() ? twice() : this.f7507y.isZero() ? eCPoint : twice().add(eCPoint);
    }

    public SecP192K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z2) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
        if ((eCFieldElement == null) != (eCFieldElement2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.withCompression = z2;
    }

    public SecP192K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z2) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        this.withCompression = z2;
    }
}

package org.spongycastle.math.ec.custom.sec;

import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat256;

/* JADX INFO: loaded from: classes.dex */
public class SecP256R1Point extends ECPoint.AbstractFp {
    public SecP256R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
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
        SecP256R1FieldElement secP256R1FieldElement = (SecP256R1FieldElement) this.f7506x;
        SecP256R1FieldElement secP256R1FieldElement2 = (SecP256R1FieldElement) this.f7507y;
        SecP256R1FieldElement secP256R1FieldElement3 = (SecP256R1FieldElement) eCPoint.getXCoord();
        SecP256R1FieldElement secP256R1FieldElement4 = (SecP256R1FieldElement) eCPoint.getYCoord();
        SecP256R1FieldElement secP256R1FieldElement5 = (SecP256R1FieldElement) this.zs[0];
        SecP256R1FieldElement secP256R1FieldElement6 = (SecP256R1FieldElement) eCPoint.getZCoord(0);
        int[] iArrCreateExt = Nat256.createExt();
        int[] iArrCreate = Nat256.create();
        int[] iArrCreate2 = Nat256.create();
        int[] iArrCreate3 = Nat256.create();
        boolean zIsOne = secP256R1FieldElement5.isOne();
        if (zIsOne) {
            iArr = secP256R1FieldElement3.f7561x;
            iArr2 = secP256R1FieldElement4.f7561x;
        } else {
            SecP256R1Field.square(secP256R1FieldElement5.f7561x, iArrCreate2);
            SecP256R1Field.multiply(iArrCreate2, secP256R1FieldElement3.f7561x, iArrCreate);
            SecP256R1Field.multiply(iArrCreate2, secP256R1FieldElement5.f7561x, iArrCreate2);
            SecP256R1Field.multiply(iArrCreate2, secP256R1FieldElement4.f7561x, iArrCreate2);
            iArr = iArrCreate;
            iArr2 = iArrCreate2;
        }
        boolean zIsOne2 = secP256R1FieldElement6.isOne();
        if (zIsOne2) {
            iArr3 = secP256R1FieldElement.f7561x;
            iArr4 = secP256R1FieldElement2.f7561x;
        } else {
            SecP256R1Field.square(secP256R1FieldElement6.f7561x, iArrCreate3);
            SecP256R1Field.multiply(iArrCreate3, secP256R1FieldElement.f7561x, iArrCreateExt);
            SecP256R1Field.multiply(iArrCreate3, secP256R1FieldElement6.f7561x, iArrCreate3);
            SecP256R1Field.multiply(iArrCreate3, secP256R1FieldElement2.f7561x, iArrCreate3);
            iArr3 = iArrCreateExt;
            iArr4 = iArrCreate3;
        }
        int[] iArrCreate4 = Nat256.create();
        SecP256R1Field.subtract(iArr3, iArr, iArrCreate4);
        SecP256R1Field.subtract(iArr4, iArr2, iArrCreate);
        if (Nat256.isZero(iArrCreate4)) {
            return Nat256.isZero(iArrCreate) ? twice() : curve.getInfinity();
        }
        SecP256R1Field.square(iArrCreate4, iArrCreate2);
        int[] iArrCreate5 = Nat256.create();
        SecP256R1Field.multiply(iArrCreate2, iArrCreate4, iArrCreate5);
        SecP256R1Field.multiply(iArrCreate2, iArr3, iArrCreate2);
        SecP256R1Field.negate(iArrCreate5, iArrCreate5);
        Nat256.mul(iArr4, iArrCreate5, iArrCreateExt);
        SecP256R1Field.reduce32(Nat256.addBothTo(iArrCreate2, iArrCreate2, iArrCreate5), iArrCreate5);
        SecP256R1FieldElement secP256R1FieldElement7 = new SecP256R1FieldElement(iArrCreate3);
        SecP256R1Field.square(iArrCreate, secP256R1FieldElement7.f7561x);
        int[] iArr5 = secP256R1FieldElement7.f7561x;
        SecP256R1Field.subtract(iArr5, iArrCreate5, iArr5);
        SecP256R1FieldElement secP256R1FieldElement8 = new SecP256R1FieldElement(iArrCreate5);
        SecP256R1Field.subtract(iArrCreate2, secP256R1FieldElement7.f7561x, secP256R1FieldElement8.f7561x);
        SecP256R1Field.multiplyAddToExt(secP256R1FieldElement8.f7561x, iArrCreate, iArrCreateExt);
        SecP256R1Field.reduce(iArrCreateExt, secP256R1FieldElement8.f7561x);
        SecP256R1FieldElement secP256R1FieldElement9 = new SecP256R1FieldElement(iArrCreate4);
        if (!zIsOne) {
            int[] iArr6 = secP256R1FieldElement9.f7561x;
            SecP256R1Field.multiply(iArr6, secP256R1FieldElement5.f7561x, iArr6);
        }
        if (!zIsOne2) {
            int[] iArr7 = secP256R1FieldElement9.f7561x;
            SecP256R1Field.multiply(iArr7, secP256R1FieldElement6.f7561x, iArr7);
        }
        return new SecP256R1Point(curve, secP256R1FieldElement7, secP256R1FieldElement8, new ECFieldElement[]{secP256R1FieldElement9}, this.withCompression);
    }

    @Override // org.spongycastle.math.ec.ECPoint
    public ECPoint detach() {
        return new SecP256R1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    @Override // org.spongycastle.math.ec.ECPoint
    public ECPoint negate() {
        return isInfinity() ? this : new SecP256R1Point(this.curve, this.f7506x, this.f7507y.negate(), this.zs, this.withCompression);
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
        SecP256R1FieldElement secP256R1FieldElement = (SecP256R1FieldElement) this.f7507y;
        if (secP256R1FieldElement.isZero()) {
            return curve.getInfinity();
        }
        SecP256R1FieldElement secP256R1FieldElement2 = (SecP256R1FieldElement) this.f7506x;
        SecP256R1FieldElement secP256R1FieldElement3 = (SecP256R1FieldElement) this.zs[0];
        int[] iArrCreate = Nat256.create();
        int[] iArrCreate2 = Nat256.create();
        int[] iArrCreate3 = Nat256.create();
        SecP256R1Field.square(secP256R1FieldElement.f7561x, iArrCreate3);
        int[] iArrCreate4 = Nat256.create();
        SecP256R1Field.square(iArrCreate3, iArrCreate4);
        boolean zIsOne = secP256R1FieldElement3.isOne();
        int[] iArr = secP256R1FieldElement3.f7561x;
        if (!zIsOne) {
            SecP256R1Field.square(iArr, iArrCreate2);
            iArr = iArrCreate2;
        }
        SecP256R1Field.subtract(secP256R1FieldElement2.f7561x, iArr, iArrCreate);
        SecP256R1Field.add(secP256R1FieldElement2.f7561x, iArr, iArrCreate2);
        SecP256R1Field.multiply(iArrCreate2, iArrCreate, iArrCreate2);
        SecP256R1Field.reduce32(Nat256.addBothTo(iArrCreate2, iArrCreate2, iArrCreate2), iArrCreate2);
        SecP256R1Field.multiply(iArrCreate3, secP256R1FieldElement2.f7561x, iArrCreate3);
        SecP256R1Field.reduce32(Nat.shiftUpBits(8, iArrCreate3, 2, 0), iArrCreate3);
        SecP256R1Field.reduce32(Nat.shiftUpBits(8, iArrCreate4, 3, 0, iArrCreate), iArrCreate);
        SecP256R1FieldElement secP256R1FieldElement4 = new SecP256R1FieldElement(iArrCreate4);
        SecP256R1Field.square(iArrCreate2, secP256R1FieldElement4.f7561x);
        int[] iArr2 = secP256R1FieldElement4.f7561x;
        SecP256R1Field.subtract(iArr2, iArrCreate3, iArr2);
        int[] iArr3 = secP256R1FieldElement4.f7561x;
        SecP256R1Field.subtract(iArr3, iArrCreate3, iArr3);
        SecP256R1FieldElement secP256R1FieldElement5 = new SecP256R1FieldElement(iArrCreate3);
        SecP256R1Field.subtract(iArrCreate3, secP256R1FieldElement4.f7561x, secP256R1FieldElement5.f7561x);
        int[] iArr4 = secP256R1FieldElement5.f7561x;
        SecP256R1Field.multiply(iArr4, iArrCreate2, iArr4);
        int[] iArr5 = secP256R1FieldElement5.f7561x;
        SecP256R1Field.subtract(iArr5, iArrCreate, iArr5);
        SecP256R1FieldElement secP256R1FieldElement6 = new SecP256R1FieldElement(iArrCreate2);
        SecP256R1Field.twice(secP256R1FieldElement.f7561x, secP256R1FieldElement6.f7561x);
        if (!zIsOne) {
            int[] iArr6 = secP256R1FieldElement6.f7561x;
            SecP256R1Field.multiply(iArr6, secP256R1FieldElement3.f7561x, iArr6);
        }
        return new SecP256R1Point(curve, secP256R1FieldElement4, secP256R1FieldElement5, new ECFieldElement[]{secP256R1FieldElement6}, this.withCompression);
    }

    @Override // org.spongycastle.math.ec.ECPoint
    public ECPoint twicePlus(ECPoint eCPoint) {
        return this == eCPoint ? threeTimes() : isInfinity() ? eCPoint : eCPoint.isInfinity() ? twice() : this.f7507y.isZero() ? eCPoint : twice().add(eCPoint);
    }

    public SecP256R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z2) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
        if ((eCFieldElement == null) != (eCFieldElement2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.withCompression = z2;
    }

    public SecP256R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z2) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        this.withCompression = z2;
    }
}

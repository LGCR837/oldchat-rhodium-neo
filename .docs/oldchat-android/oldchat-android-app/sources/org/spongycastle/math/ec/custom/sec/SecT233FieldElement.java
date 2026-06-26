package org.spongycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.raw.Nat256;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class SecT233FieldElement extends ECFieldElement {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    protected long[] f7575x;

    public SecT233FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 233) {
            throw new IllegalArgumentException("x value invalid for SecT233FieldElement");
        }
        this.f7575x = SecT233Field.fromBigInteger(bigInteger);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        long[] jArrCreate64 = Nat256.create64();
        SecT233Field.add(this.f7575x, ((SecT233FieldElement) eCFieldElement).f7575x, jArrCreate64);
        return new SecT233FieldElement(jArrCreate64);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement addOne() {
        long[] jArrCreate64 = Nat256.create64();
        SecT233Field.addOne(this.f7575x, jArrCreate64);
        return new SecT233FieldElement(jArrCreate64);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        return multiply(eCFieldElement.invert());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecT233FieldElement) {
            return Nat256.eq64(this.f7575x, ((SecT233FieldElement) obj).f7575x);
        }
        return false;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public String getFieldName() {
        return "SecT233Field";
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public int getFieldSize() {
        return 233;
    }

    public int getK1() {
        return 74;
    }

    public int getK2() {
        return 0;
    }

    public int getK3() {
        return 0;
    }

    public int getM() {
        return 233;
    }

    public int getRepresentation() {
        return 2;
    }

    public int hashCode() {
        return Arrays.hashCode(this.f7575x, 0, 4) ^ 2330074;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement invert() {
        long[] jArrCreate64 = Nat256.create64();
        SecT233Field.invert(this.f7575x, jArrCreate64);
        return new SecT233FieldElement(jArrCreate64);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean isOne() {
        return Nat256.isOne64(this.f7575x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean isZero() {
        return Nat256.isZero64(this.f7575x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        long[] jArrCreate64 = Nat256.create64();
        SecT233Field.multiply(this.f7575x, ((SecT233FieldElement) eCFieldElement).f7575x, jArrCreate64);
        return new SecT233FieldElement(jArrCreate64);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        long[] jArr = this.f7575x;
        long[] jArr2 = ((SecT233FieldElement) eCFieldElement).f7575x;
        long[] jArr3 = ((SecT233FieldElement) eCFieldElement2).f7575x;
        long[] jArr4 = ((SecT233FieldElement) eCFieldElement3).f7575x;
        long[] jArrCreateExt64 = Nat256.createExt64();
        SecT233Field.multiplyAddToExt(jArr, jArr2, jArrCreateExt64);
        SecT233Field.multiplyAddToExt(jArr3, jArr4, jArrCreateExt64);
        long[] jArrCreate64 = Nat256.create64();
        SecT233Field.reduce(jArrCreateExt64, jArrCreate64);
        return new SecT233FieldElement(jArrCreate64);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement negate() {
        return this;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement sqrt() {
        long[] jArrCreate64 = Nat256.create64();
        SecT233Field.sqrt(this.f7575x, jArrCreate64);
        return new SecT233FieldElement(jArrCreate64);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement square() {
        long[] jArrCreate64 = Nat256.create64();
        SecT233Field.square(this.f7575x, jArrCreate64);
        return new SecT233FieldElement(jArrCreate64);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return squarePlusProduct(eCFieldElement, eCFieldElement2);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        long[] jArr = this.f7575x;
        long[] jArr2 = ((SecT233FieldElement) eCFieldElement).f7575x;
        long[] jArr3 = ((SecT233FieldElement) eCFieldElement2).f7575x;
        long[] jArrCreateExt64 = Nat256.createExt64();
        SecT233Field.squareAddToExt(jArr, jArrCreateExt64);
        SecT233Field.multiplyAddToExt(jArr2, jArr3, jArrCreateExt64);
        long[] jArrCreate64 = Nat256.create64();
        SecT233Field.reduce(jArrCreateExt64, jArrCreate64);
        return new SecT233FieldElement(jArrCreate64);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement squarePow(int i2) {
        if (i2 < 1) {
            return this;
        }
        long[] jArrCreate64 = Nat256.create64();
        SecT233Field.squareN(this.f7575x, i2, jArrCreate64);
        return new SecT233FieldElement(jArrCreate64);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        return add(eCFieldElement);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean testBitZero() {
        return (this.f7575x[0] & 1) != 0;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat256.toBigInteger64(this.f7575x);
    }

    public SecT233FieldElement() {
        this.f7575x = Nat256.create64();
    }

    public SecT233FieldElement(long[] jArr) {
        this.f7575x = jArr;
    }
}

package org.spongycastle.math.ec;

import java.math.BigInteger;
import java.util.Hashtable;
import org.spongycastle.math.ec.ECFieldElement;

/* JADX INFO: loaded from: classes.dex */
public abstract class ECPoint {
    protected static ECFieldElement[] EMPTY_ZS = new ECFieldElement[0];
    protected ECCurve curve;
    protected Hashtable preCompTable;
    protected boolean withCompression;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    protected ECFieldElement f7506x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    protected ECFieldElement f7507y;
    protected ECFieldElement[] zs;

    public static abstract class AbstractF2m extends ECPoint {
        public AbstractF2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public boolean satisfiesCurveEquation() {
            ECFieldElement eCFieldElementMultiplyPlusProduct;
            ECFieldElement eCFieldElementSquarePlusProduct;
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement = this.f7506x;
            ECFieldElement a2 = curve.getA();
            ECFieldElement b2 = curve.getB();
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem != 6) {
                ECFieldElement eCFieldElement2 = this.f7507y;
                ECFieldElement eCFieldElementMultiply = eCFieldElement2.add(eCFieldElement).multiply(eCFieldElement2);
                if (coordinateSystem != 0) {
                    if (coordinateSystem != 1) {
                        throw new IllegalStateException("unsupported coordinate system");
                    }
                    ECFieldElement eCFieldElement3 = this.zs[0];
                    if (!eCFieldElement3.isOne()) {
                        ECFieldElement eCFieldElementMultiply2 = eCFieldElement3.multiply(eCFieldElement3.square());
                        eCFieldElementMultiply = eCFieldElementMultiply.multiply(eCFieldElement3);
                        a2 = a2.multiply(eCFieldElement3);
                        b2 = b2.multiply(eCFieldElementMultiply2);
                    }
                }
                return eCFieldElementMultiply.equals(eCFieldElement.add(a2).multiply(eCFieldElement.square()).add(b2));
            }
            ECFieldElement eCFieldElement4 = this.zs[0];
            boolean zIsOne = eCFieldElement4.isOne();
            if (eCFieldElement.isZero()) {
                ECFieldElement eCFieldElementSquare = this.f7507y.square();
                if (!zIsOne) {
                    b2 = b2.multiply(eCFieldElement4.square());
                }
                return eCFieldElementSquare.equals(b2);
            }
            ECFieldElement eCFieldElement5 = this.f7507y;
            ECFieldElement eCFieldElementSquare2 = eCFieldElement.square();
            if (zIsOne) {
                eCFieldElementMultiplyPlusProduct = eCFieldElement5.square().add(eCFieldElement5).add(a2);
                eCFieldElementSquarePlusProduct = eCFieldElementSquare2.square().add(b2);
            } else {
                ECFieldElement eCFieldElementSquare3 = eCFieldElement4.square();
                ECFieldElement eCFieldElementSquare4 = eCFieldElementSquare3.square();
                eCFieldElementMultiplyPlusProduct = eCFieldElement5.add(eCFieldElement4).multiplyPlusProduct(eCFieldElement5, a2, eCFieldElementSquare3);
                eCFieldElementSquarePlusProduct = eCFieldElementSquare2.squarePlusProduct(b2, eCFieldElementSquare4);
            }
            return eCFieldElementMultiplyPlusProduct.multiply(eCFieldElementSquare2).equals(eCFieldElementSquarePlusProduct);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint scaleX(ECFieldElement eCFieldElement) {
            if (isInfinity()) {
                return this;
            }
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem == 5) {
                ECFieldElement rawXCoord = getRawXCoord();
                return getCurve().createRawPoint(rawXCoord, getRawYCoord().add(rawXCoord).divide(eCFieldElement).add(rawXCoord.multiply(eCFieldElement)), getRawZCoords(), this.withCompression);
            }
            if (curveCoordinateSystem != 6) {
                return super.scaleX(eCFieldElement);
            }
            ECFieldElement rawXCoord2 = getRawXCoord();
            ECFieldElement rawYCoord = getRawYCoord();
            ECFieldElement eCFieldElement2 = getRawZCoords()[0];
            ECFieldElement eCFieldElementMultiply = rawXCoord2.multiply(eCFieldElement.square());
            return getCurve().createRawPoint(eCFieldElementMultiply, rawYCoord.add(rawXCoord2).add(eCFieldElementMultiply), new ECFieldElement[]{eCFieldElement2.multiply(eCFieldElement)}, this.withCompression);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint scaleY(ECFieldElement eCFieldElement) {
            if (isInfinity()) {
                return this;
            }
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem != 5 && curveCoordinateSystem != 6) {
                return super.scaleY(eCFieldElement);
            }
            ECFieldElement rawXCoord = getRawXCoord();
            return getCurve().createRawPoint(rawXCoord, getRawYCoord().add(rawXCoord).multiply(eCFieldElement).add(rawXCoord), getRawZCoords(), this.withCompression);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint subtract(ECPoint eCPoint) {
            return eCPoint.isInfinity() ? this : add(eCPoint.negate());
        }

        public AbstractF2m tau() {
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement = this.f7506x;
            if (coordinateSystem != 0) {
                if (coordinateSystem != 1) {
                    if (coordinateSystem != 5) {
                        if (coordinateSystem != 6) {
                            throw new IllegalStateException("unsupported coordinate system");
                        }
                    }
                }
                return (AbstractF2m) curve.createRawPoint(eCFieldElement.square(), this.f7507y.square(), new ECFieldElement[]{this.zs[0].square()}, this.withCompression);
            }
            return (AbstractF2m) curve.createRawPoint(eCFieldElement.square(), this.f7507y.square(), this.withCompression);
        }

        public AbstractF2m tauPow(int i2) {
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement = this.f7506x;
            if (coordinateSystem != 0) {
                if (coordinateSystem != 1) {
                    if (coordinateSystem != 5) {
                        if (coordinateSystem != 6) {
                            throw new IllegalStateException("unsupported coordinate system");
                        }
                    }
                }
                return (AbstractF2m) curve.createRawPoint(eCFieldElement.squarePow(i2), this.f7507y.squarePow(i2), new ECFieldElement[]{this.zs[0].squarePow(i2)}, this.withCompression);
            }
            return (AbstractF2m) curve.createRawPoint(eCFieldElement.squarePow(i2), this.f7507y.squarePow(i2), this.withCompression);
        }

        public AbstractF2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }
    }

    public static abstract class AbstractFp extends ECPoint {
        public AbstractFp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public boolean getCompressionYTilde() {
            return getAffineYCoord().testBitZero();
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public boolean satisfiesCurveEquation() {
            ECFieldElement eCFieldElement = this.f7506x;
            ECFieldElement eCFieldElement2 = this.f7507y;
            ECFieldElement a2 = this.curve.getA();
            ECFieldElement b2 = this.curve.getB();
            ECFieldElement eCFieldElementSquare = eCFieldElement2.square();
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem != 0) {
                if (curveCoordinateSystem == 1) {
                    ECFieldElement eCFieldElement3 = this.zs[0];
                    if (!eCFieldElement3.isOne()) {
                        ECFieldElement eCFieldElementSquare2 = eCFieldElement3.square();
                        ECFieldElement eCFieldElementMultiply = eCFieldElement3.multiply(eCFieldElementSquare2);
                        eCFieldElementSquare = eCFieldElementSquare.multiply(eCFieldElement3);
                        a2 = a2.multiply(eCFieldElementSquare2);
                        b2 = b2.multiply(eCFieldElementMultiply);
                    }
                } else {
                    if (curveCoordinateSystem != 2 && curveCoordinateSystem != 3 && curveCoordinateSystem != 4) {
                        throw new IllegalStateException("unsupported coordinate system");
                    }
                    ECFieldElement eCFieldElement4 = this.zs[0];
                    if (!eCFieldElement4.isOne()) {
                        ECFieldElement eCFieldElementSquare3 = eCFieldElement4.square();
                        ECFieldElement eCFieldElementSquare4 = eCFieldElementSquare3.square();
                        ECFieldElement eCFieldElementMultiply2 = eCFieldElementSquare3.multiply(eCFieldElementSquare4);
                        a2 = a2.multiply(eCFieldElementSquare4);
                        b2 = b2.multiply(eCFieldElementMultiply2);
                    }
                }
            }
            return eCFieldElementSquare.equals(eCFieldElement.square().add(a2).multiply(eCFieldElement).add(b2));
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint subtract(ECPoint eCPoint) {
            return eCPoint.isInfinity() ? this : add(eCPoint.negate());
        }

        public AbstractFp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }
    }

    public static class F2m extends AbstractF2m {
        public F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            this(eCCurve, eCFieldElement, eCFieldElement2, false);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint add(ECPoint eCPoint) {
            ECFieldElement eCFieldElementMultiply;
            ECFieldElement eCFieldElementMultiply2;
            ECFieldElement eCFieldElementMultiply3;
            ECFieldElement eCFieldElement;
            ECFieldElement eCFieldElementAdd;
            ECFieldElement eCFieldElementFromBigInteger;
            if (isInfinity()) {
                return eCPoint;
            }
            if (eCPoint.isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElementMultiply4 = this.f7506x;
            ECFieldElement eCFieldElement2 = eCPoint.f7506x;
            if (coordinateSystem == 0) {
                ECFieldElement eCFieldElement3 = this.f7507y;
                ECFieldElement eCFieldElement4 = eCPoint.f7507y;
                ECFieldElement eCFieldElementAdd2 = eCFieldElementMultiply4.add(eCFieldElement2);
                ECFieldElement eCFieldElementAdd3 = eCFieldElement3.add(eCFieldElement4);
                if (eCFieldElementAdd2.isZero()) {
                    return eCFieldElementAdd3.isZero() ? twice() : curve.getInfinity();
                }
                ECFieldElement eCFieldElementDivide = eCFieldElementAdd3.divide(eCFieldElementAdd2);
                ECFieldElement eCFieldElementAdd4 = eCFieldElementDivide.square().add(eCFieldElementDivide).add(eCFieldElementAdd2).add(curve.getA());
                return new F2m(curve, eCFieldElementAdd4, eCFieldElementDivide.multiply(eCFieldElementMultiply4.add(eCFieldElementAdd4)).add(eCFieldElementAdd4).add(eCFieldElement3), this.withCompression);
            }
            if (coordinateSystem == 1) {
                ECFieldElement eCFieldElement5 = this.f7507y;
                ECFieldElement eCFieldElementMultiply5 = this.zs[0];
                ECFieldElement eCFieldElement6 = eCPoint.f7507y;
                ECFieldElement eCFieldElement7 = eCPoint.zs[0];
                boolean zIsOne = eCFieldElement7.isOne();
                ECFieldElement eCFieldElementAdd5 = eCFieldElementMultiply5.multiply(eCFieldElement6).add(zIsOne ? eCFieldElement5 : eCFieldElement5.multiply(eCFieldElement7));
                ECFieldElement eCFieldElementAdd6 = eCFieldElementMultiply5.multiply(eCFieldElement2).add(zIsOne ? eCFieldElementMultiply4 : eCFieldElementMultiply4.multiply(eCFieldElement7));
                if (eCFieldElementAdd6.isZero()) {
                    return eCFieldElementAdd5.isZero() ? twice() : curve.getInfinity();
                }
                ECFieldElement eCFieldElementSquare = eCFieldElementAdd6.square();
                ECFieldElement eCFieldElementMultiply6 = eCFieldElementSquare.multiply(eCFieldElementAdd6);
                if (!zIsOne) {
                    eCFieldElementMultiply5 = eCFieldElementMultiply5.multiply(eCFieldElement7);
                }
                ECFieldElement eCFieldElementAdd7 = eCFieldElementAdd5.add(eCFieldElementAdd6);
                ECFieldElement eCFieldElementAdd8 = eCFieldElementAdd7.multiplyPlusProduct(eCFieldElementAdd5, eCFieldElementSquare, curve.getA()).multiply(eCFieldElementMultiply5).add(eCFieldElementMultiply6);
                ECFieldElement eCFieldElementMultiply7 = eCFieldElementAdd6.multiply(eCFieldElementAdd8);
                if (!zIsOne) {
                    eCFieldElementSquare = eCFieldElementSquare.multiply(eCFieldElement7);
                }
                return new F2m(curve, eCFieldElementMultiply7, eCFieldElementAdd5.multiplyPlusProduct(eCFieldElementMultiply4, eCFieldElementAdd6, eCFieldElement5).multiplyPlusProduct(eCFieldElementSquare, eCFieldElementAdd7, eCFieldElementAdd8), new ECFieldElement[]{eCFieldElementMultiply6.multiply(eCFieldElementMultiply5)}, this.withCompression);
            }
            if (coordinateSystem != 6) {
                throw new IllegalStateException("unsupported coordinate system");
            }
            if (eCFieldElementMultiply4.isZero()) {
                return eCFieldElement2.isZero() ? curve.getInfinity() : eCPoint.add(this);
            }
            ECFieldElement eCFieldElement8 = this.f7507y;
            ECFieldElement eCFieldElement9 = this.zs[0];
            ECFieldElement eCFieldElement10 = eCPoint.f7507y;
            ECFieldElement eCFieldElement11 = eCPoint.zs[0];
            boolean zIsOne2 = eCFieldElement9.isOne();
            if (zIsOne2) {
                eCFieldElementMultiply = eCFieldElement2;
                eCFieldElementMultiply2 = eCFieldElement10;
            } else {
                eCFieldElementMultiply = eCFieldElement2.multiply(eCFieldElement9);
                eCFieldElementMultiply2 = eCFieldElement10.multiply(eCFieldElement9);
            }
            boolean zIsOne3 = eCFieldElement11.isOne();
            if (zIsOne3) {
                eCFieldElementMultiply3 = eCFieldElement8;
            } else {
                eCFieldElementMultiply4 = eCFieldElementMultiply4.multiply(eCFieldElement11);
                eCFieldElementMultiply3 = eCFieldElement8.multiply(eCFieldElement11);
            }
            ECFieldElement eCFieldElementAdd9 = eCFieldElementMultiply3.add(eCFieldElementMultiply2);
            ECFieldElement eCFieldElementAdd10 = eCFieldElementMultiply4.add(eCFieldElementMultiply);
            if (eCFieldElementAdd10.isZero()) {
                return eCFieldElementAdd9.isZero() ? twice() : curve.getInfinity();
            }
            if (eCFieldElement2.isZero()) {
                ECPoint eCPointNormalize = normalize();
                ECFieldElement xCoord = eCPointNormalize.getXCoord();
                ECFieldElement yCoord = eCPointNormalize.getYCoord();
                ECFieldElement eCFieldElementDivide2 = yCoord.add(eCFieldElement10).divide(xCoord);
                eCFieldElementAdd = eCFieldElementDivide2.square().add(eCFieldElementDivide2).add(xCoord).add(curve.getA());
                if (eCFieldElementAdd.isZero()) {
                    return new F2m(curve, eCFieldElementAdd, curve.getB().sqrt(), this.withCompression);
                }
                ECFieldElement eCFieldElementAdd11 = eCFieldElementDivide2.multiply(xCoord.add(eCFieldElementAdd)).add(eCFieldElementAdd).add(yCoord).divide(eCFieldElementAdd).add(eCFieldElementAdd);
                eCFieldElementFromBigInteger = curve.fromBigInteger(ECConstants.ONE);
                eCFieldElement = eCFieldElementAdd11;
            } else {
                ECFieldElement eCFieldElementSquare2 = eCFieldElementAdd10.square();
                ECFieldElement eCFieldElementMultiply8 = eCFieldElementAdd9.multiply(eCFieldElementMultiply4);
                ECFieldElement eCFieldElementMultiply9 = eCFieldElementAdd9.multiply(eCFieldElementMultiply);
                ECFieldElement eCFieldElementMultiply10 = eCFieldElementMultiply8.multiply(eCFieldElementMultiply9);
                if (eCFieldElementMultiply10.isZero()) {
                    return new F2m(curve, eCFieldElementMultiply10, curve.getB().sqrt(), this.withCompression);
                }
                ECFieldElement eCFieldElementMultiply11 = eCFieldElementAdd9.multiply(eCFieldElementSquare2);
                ECFieldElement eCFieldElementMultiply12 = !zIsOne3 ? eCFieldElementMultiply11.multiply(eCFieldElement11) : eCFieldElementMultiply11;
                ECFieldElement eCFieldElementSquarePlusProduct = eCFieldElementMultiply9.add(eCFieldElementSquare2).squarePlusProduct(eCFieldElementMultiply12, eCFieldElement8.add(eCFieldElement9));
                if (!zIsOne2) {
                    eCFieldElementMultiply12 = eCFieldElementMultiply12.multiply(eCFieldElement9);
                }
                eCFieldElement = eCFieldElementSquarePlusProduct;
                eCFieldElementAdd = eCFieldElementMultiply10;
                eCFieldElementFromBigInteger = eCFieldElementMultiply12;
            }
            return new F2m(curve, eCFieldElementAdd, eCFieldElement, new ECFieldElement[]{eCFieldElementFromBigInteger}, this.withCompression);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint detach() {
            return new F2m(null, getAffineXCoord(), getAffineYCoord());
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public boolean getCompressionYTilde() {
            ECFieldElement rawXCoord = getRawXCoord();
            if (rawXCoord.isZero()) {
                return false;
            }
            ECFieldElement rawYCoord = getRawYCoord();
            int curveCoordinateSystem = getCurveCoordinateSystem();
            return (curveCoordinateSystem == 5 || curveCoordinateSystem == 6) ? rawYCoord.testBitZero() != rawXCoord.testBitZero() : rawYCoord.divide(rawXCoord).testBitZero();
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECFieldElement getYCoord() {
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem != 5 && curveCoordinateSystem != 6) {
                return this.f7507y;
            }
            ECFieldElement eCFieldElement = this.f7506x;
            ECFieldElement eCFieldElement2 = this.f7507y;
            if (isInfinity() || eCFieldElement.isZero()) {
                return eCFieldElement2;
            }
            ECFieldElement eCFieldElementMultiply = eCFieldElement2.add(eCFieldElement).multiply(eCFieldElement);
            if (6 != curveCoordinateSystem) {
                return eCFieldElementMultiply;
            }
            ECFieldElement eCFieldElement3 = this.zs[0];
            return !eCFieldElement3.isOne() ? eCFieldElementMultiply.divide(eCFieldElement3) : eCFieldElementMultiply;
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint negate() {
            if (isInfinity()) {
                return this;
            }
            ECFieldElement eCFieldElement = this.f7506x;
            if (eCFieldElement.isZero()) {
                return this;
            }
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem == 0) {
                return new F2m(this.curve, eCFieldElement, this.f7507y.add(eCFieldElement), this.withCompression);
            }
            if (curveCoordinateSystem == 1) {
                return new F2m(this.curve, eCFieldElement, this.f7507y.add(eCFieldElement), new ECFieldElement[]{this.zs[0]}, this.withCompression);
            }
            if (curveCoordinateSystem == 5) {
                return new F2m(this.curve, eCFieldElement, this.f7507y.addOne(), this.withCompression);
            }
            if (curveCoordinateSystem != 6) {
                throw new IllegalStateException("unsupported coordinate system");
            }
            ECFieldElement eCFieldElement2 = this.f7507y;
            ECFieldElement eCFieldElement3 = this.zs[0];
            return new F2m(this.curve, eCFieldElement, eCFieldElement2.add(eCFieldElement3), new ECFieldElement[]{eCFieldElement3}, this.withCompression);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint twice() {
            ECFieldElement eCFieldElementAdd;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElementMultiply = this.f7506x;
            if (eCFieldElementMultiply.isZero()) {
                return curve.getInfinity();
            }
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem == 0) {
                ECFieldElement eCFieldElementAdd2 = this.f7507y.divide(eCFieldElementMultiply).add(eCFieldElementMultiply);
                ECFieldElement eCFieldElementAdd3 = eCFieldElementAdd2.square().add(eCFieldElementAdd2).add(curve.getA());
                return new F2m(curve, eCFieldElementAdd3, eCFieldElementMultiply.squarePlusProduct(eCFieldElementAdd3, eCFieldElementAdd2.addOne()), this.withCompression);
            }
            if (coordinateSystem == 1) {
                ECFieldElement eCFieldElementMultiply2 = this.f7507y;
                ECFieldElement eCFieldElement = this.zs[0];
                boolean zIsOne = eCFieldElement.isOne();
                ECFieldElement eCFieldElementMultiply3 = zIsOne ? eCFieldElementMultiply : eCFieldElementMultiply.multiply(eCFieldElement);
                if (!zIsOne) {
                    eCFieldElementMultiply2 = eCFieldElementMultiply2.multiply(eCFieldElement);
                }
                ECFieldElement eCFieldElementSquare = eCFieldElementMultiply.square();
                ECFieldElement eCFieldElementAdd4 = eCFieldElementSquare.add(eCFieldElementMultiply2);
                ECFieldElement eCFieldElementSquare2 = eCFieldElementMultiply3.square();
                ECFieldElement eCFieldElementAdd5 = eCFieldElementAdd4.add(eCFieldElementMultiply3);
                ECFieldElement eCFieldElementMultiplyPlusProduct = eCFieldElementAdd5.multiplyPlusProduct(eCFieldElementAdd4, eCFieldElementSquare2, curve.getA());
                return new F2m(curve, eCFieldElementMultiply3.multiply(eCFieldElementMultiplyPlusProduct), eCFieldElementSquare.square().multiplyPlusProduct(eCFieldElementMultiply3, eCFieldElementMultiplyPlusProduct, eCFieldElementAdd5), new ECFieldElement[]{eCFieldElementMultiply3.multiply(eCFieldElementSquare2)}, this.withCompression);
            }
            if (coordinateSystem != 6) {
                throw new IllegalStateException("unsupported coordinate system");
            }
            ECFieldElement eCFieldElement2 = this.f7507y;
            ECFieldElement eCFieldElement3 = this.zs[0];
            boolean zIsOne2 = eCFieldElement3.isOne();
            ECFieldElement eCFieldElementMultiply4 = zIsOne2 ? eCFieldElement2 : eCFieldElement2.multiply(eCFieldElement3);
            ECFieldElement eCFieldElementSquare3 = zIsOne2 ? eCFieldElement3 : eCFieldElement3.square();
            ECFieldElement a2 = curve.getA();
            ECFieldElement eCFieldElementMultiply5 = zIsOne2 ? a2 : a2.multiply(eCFieldElementSquare3);
            ECFieldElement eCFieldElementAdd6 = eCFieldElement2.square().add(eCFieldElementMultiply4).add(eCFieldElementMultiply5);
            if (eCFieldElementAdd6.isZero()) {
                return new F2m(curve, eCFieldElementAdd6, curve.getB().sqrt(), this.withCompression);
            }
            ECFieldElement eCFieldElementSquare4 = eCFieldElementAdd6.square();
            ECFieldElement eCFieldElementMultiply6 = zIsOne2 ? eCFieldElementAdd6 : eCFieldElementAdd6.multiply(eCFieldElementSquare3);
            ECFieldElement b2 = curve.getB();
            if (b2.bitLength() < (curve.getFieldSize() >> 1)) {
                ECFieldElement eCFieldElementSquare5 = eCFieldElement2.add(eCFieldElementMultiply).square();
                eCFieldElementAdd = eCFieldElementSquare5.add(eCFieldElementAdd6).add(eCFieldElementSquare3).multiply(eCFieldElementSquare5).add(b2.isOne() ? eCFieldElementMultiply5.add(eCFieldElementSquare3).square() : eCFieldElementMultiply5.squarePlusProduct(b2, eCFieldElementSquare3.square())).add(eCFieldElementSquare4);
                if (a2.isZero()) {
                    eCFieldElementAdd = eCFieldElementAdd.add(eCFieldElementMultiply6);
                } else if (!a2.isOne()) {
                    eCFieldElementAdd = eCFieldElementAdd.add(a2.addOne().multiply(eCFieldElementMultiply6));
                }
            } else {
                if (!zIsOne2) {
                    eCFieldElementMultiply = eCFieldElementMultiply.multiply(eCFieldElement3);
                }
                eCFieldElementAdd = eCFieldElementMultiply.squarePlusProduct(eCFieldElementAdd6, eCFieldElementMultiply4).add(eCFieldElementSquare4).add(eCFieldElementMultiply6);
            }
            return new F2m(curve, eCFieldElementSquare4, eCFieldElementAdd, new ECFieldElement[]{eCFieldElementMultiply6}, this.withCompression);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint twicePlus(ECPoint eCPoint) {
            if (isInfinity()) {
                return eCPoint;
            }
            if (eCPoint.isInfinity()) {
                return twice();
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement = this.f7506x;
            if (eCFieldElement.isZero()) {
                return eCPoint;
            }
            if (curve.getCoordinateSystem() != 6) {
                return twice().add(eCPoint);
            }
            ECFieldElement eCFieldElement2 = eCPoint.f7506x;
            ECFieldElement eCFieldElement3 = eCPoint.zs[0];
            if (eCFieldElement2.isZero() || !eCFieldElement3.isOne()) {
                return twice().add(eCPoint);
            }
            ECFieldElement eCFieldElement4 = this.f7507y;
            ECFieldElement eCFieldElement5 = this.zs[0];
            ECFieldElement eCFieldElement6 = eCPoint.f7507y;
            ECFieldElement eCFieldElementSquare = eCFieldElement.square();
            ECFieldElement eCFieldElementSquare2 = eCFieldElement4.square();
            ECFieldElement eCFieldElementSquare3 = eCFieldElement5.square();
            ECFieldElement eCFieldElementAdd = curve.getA().multiply(eCFieldElementSquare3).add(eCFieldElementSquare2).add(eCFieldElement4.multiply(eCFieldElement5));
            ECFieldElement eCFieldElementAddOne = eCFieldElement6.addOne();
            ECFieldElement eCFieldElementMultiplyPlusProduct = curve.getA().add(eCFieldElementAddOne).multiply(eCFieldElementSquare3).add(eCFieldElementSquare2).multiplyPlusProduct(eCFieldElementAdd, eCFieldElementSquare, eCFieldElementSquare3);
            ECFieldElement eCFieldElementMultiply = eCFieldElement2.multiply(eCFieldElementSquare3);
            ECFieldElement eCFieldElementSquare4 = eCFieldElementMultiply.add(eCFieldElementAdd).square();
            if (eCFieldElementSquare4.isZero()) {
                return eCFieldElementMultiplyPlusProduct.isZero() ? eCPoint.twice() : curve.getInfinity();
            }
            if (eCFieldElementMultiplyPlusProduct.isZero()) {
                return new F2m(curve, eCFieldElementMultiplyPlusProduct, curve.getB().sqrt(), this.withCompression);
            }
            ECFieldElement eCFieldElementMultiply2 = eCFieldElementMultiplyPlusProduct.square().multiply(eCFieldElementMultiply);
            ECFieldElement eCFieldElementMultiply3 = eCFieldElementMultiplyPlusProduct.multiply(eCFieldElementSquare4).multiply(eCFieldElementSquare3);
            return new F2m(curve, eCFieldElementMultiply2, eCFieldElementMultiplyPlusProduct.add(eCFieldElementSquare4).square().multiplyPlusProduct(eCFieldElementAdd, eCFieldElementAddOne, eCFieldElementMultiply3), new ECFieldElement[]{eCFieldElementMultiply3}, this.withCompression);
        }

        public F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
            if ((eCFieldElement == null) != (eCFieldElement2 == null)) {
                throw new IllegalArgumentException("Exactly one of the field elements is null");
            }
            if (eCFieldElement != null) {
                ECFieldElement.F2m.checkFieldElements(this.f7506x, this.f7507y);
                if (eCCurve != null) {
                    ECFieldElement.F2m.checkFieldElements(this.f7506x, this.curve.getA());
                }
            }
            this.withCompression = z2;
        }

        public F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z2) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
            this.withCompression = z2;
        }
    }

    public static class Fp extends AbstractFp {
        public Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            this(eCCurve, eCFieldElement, eCFieldElement2, false);
        }

        /* JADX WARN: Removed duplicated region for block: B:60:0x012a  */
        /* JADX WARN: Removed duplicated region for block: B:61:0x0139  */
        @Override // org.spongycastle.math.ec.ECPoint
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public org.spongycastle.math.ec.ECPoint add(org.spongycastle.math.ec.ECPoint r17) {
            /*
                Method dump skipped, instruction units count: 552
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.math.ec.ECPoint.Fp.add(org.spongycastle.math.ec.ECPoint):org.spongycastle.math.ec.ECPoint");
        }

        public ECFieldElement calculateJacobianModifiedW(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            ECFieldElement a2 = getCurve().getA();
            if (a2.isZero() || eCFieldElement.isOne()) {
                return a2;
            }
            if (eCFieldElement2 == null) {
                eCFieldElement2 = eCFieldElement.square();
            }
            ECFieldElement eCFieldElementSquare = eCFieldElement2.square();
            ECFieldElement eCFieldElementNegate = a2.negate();
            return eCFieldElementNegate.bitLength() < a2.bitLength() ? eCFieldElementSquare.multiply(eCFieldElementNegate).negate() : eCFieldElementSquare.multiply(a2);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint detach() {
            return new Fp(null, getAffineXCoord(), getAffineYCoord());
        }

        public ECFieldElement doubleProductFromSquares(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3, ECFieldElement eCFieldElement4) {
            return eCFieldElement.add(eCFieldElement2).square().subtract(eCFieldElement3).subtract(eCFieldElement4);
        }

        public ECFieldElement eight(ECFieldElement eCFieldElement) {
            return four(two(eCFieldElement));
        }

        public ECFieldElement four(ECFieldElement eCFieldElement) {
            return two(two(eCFieldElement));
        }

        public ECFieldElement getJacobianModifiedW() {
            ECFieldElement[] eCFieldElementArr = this.zs;
            ECFieldElement eCFieldElement = eCFieldElementArr[1];
            if (eCFieldElement != null) {
                return eCFieldElement;
            }
            ECFieldElement eCFieldElementCalculateJacobianModifiedW = calculateJacobianModifiedW(eCFieldElementArr[0], null);
            eCFieldElementArr[1] = eCFieldElementCalculateJacobianModifiedW;
            return eCFieldElementCalculateJacobianModifiedW;
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECFieldElement getZCoord(int i2) {
            return (i2 == 1 && 4 == getCurveCoordinateSystem()) ? getJacobianModifiedW() : super.getZCoord(i2);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint negate() {
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            return curve.getCoordinateSystem() != 0 ? new Fp(curve, this.f7506x, this.f7507y.negate(), this.zs, this.withCompression) : new Fp(curve, this.f7506x, this.f7507y.negate(), this.withCompression);
        }

        public ECFieldElement three(ECFieldElement eCFieldElement) {
            return two(eCFieldElement).add(eCFieldElement);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint threeTimes() {
            if (isInfinity()) {
                return this;
            }
            ECFieldElement eCFieldElement = this.f7507y;
            if (eCFieldElement.isZero()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem != 0) {
                return coordinateSystem != 4 ? twice().add(this) : twiceJacobianModified(false).add(this);
            }
            ECFieldElement eCFieldElement2 = this.f7506x;
            ECFieldElement eCFieldElementTwo = two(eCFieldElement);
            ECFieldElement eCFieldElementSquare = eCFieldElementTwo.square();
            ECFieldElement eCFieldElementAdd = three(eCFieldElement2.square()).add(getCurve().getA());
            ECFieldElement eCFieldElementSubtract = three(eCFieldElement2).multiply(eCFieldElementSquare).subtract(eCFieldElementAdd.square());
            if (eCFieldElementSubtract.isZero()) {
                return getCurve().getInfinity();
            }
            ECFieldElement eCFieldElementInvert = eCFieldElementSubtract.multiply(eCFieldElementTwo).invert();
            ECFieldElement eCFieldElementMultiply = eCFieldElementSubtract.multiply(eCFieldElementInvert).multiply(eCFieldElementAdd);
            ECFieldElement eCFieldElementSubtract2 = eCFieldElementSquare.square().multiply(eCFieldElementInvert).subtract(eCFieldElementMultiply);
            ECFieldElement eCFieldElementAdd2 = eCFieldElementSubtract2.subtract(eCFieldElementMultiply).multiply(eCFieldElementMultiply.add(eCFieldElementSubtract2)).add(eCFieldElement2);
            return new Fp(curve, eCFieldElementAdd2, eCFieldElement2.subtract(eCFieldElementAdd2).multiply(eCFieldElementSubtract2).subtract(eCFieldElement), this.withCompression);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint timesPow2(int i2) {
            if (i2 < 0) {
                throw new IllegalArgumentException("'e' cannot be negative");
            }
            if (i2 == 0 || isInfinity()) {
                return this;
            }
            if (i2 == 1) {
                return twice();
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElementSubtract = this.f7507y;
            if (eCFieldElementSubtract.isZero()) {
                return curve.getInfinity();
            }
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement a2 = curve.getA();
            ECFieldElement eCFieldElementMultiply = this.f7506x;
            ECFieldElement[] eCFieldElementArr = this.zs;
            ECFieldElement eCFieldElementFromBigInteger = eCFieldElementArr.length < 1 ? curve.fromBigInteger(ECConstants.ONE) : eCFieldElementArr[0];
            if (!eCFieldElementFromBigInteger.isOne() && coordinateSystem != 0) {
                if (coordinateSystem == 1) {
                    ECFieldElement eCFieldElementSquare = eCFieldElementFromBigInteger.square();
                    eCFieldElementMultiply = eCFieldElementMultiply.multiply(eCFieldElementFromBigInteger);
                    eCFieldElementSubtract = eCFieldElementSubtract.multiply(eCFieldElementSquare);
                    a2 = calculateJacobianModifiedW(eCFieldElementFromBigInteger, eCFieldElementSquare);
                } else if (coordinateSystem == 2) {
                    a2 = calculateJacobianModifiedW(eCFieldElementFromBigInteger, null);
                } else {
                    if (coordinateSystem != 4) {
                        throw new IllegalStateException("unsupported coordinate system");
                    }
                    a2 = getJacobianModifiedW();
                }
            }
            int i3 = 0;
            while (i3 < i2) {
                if (eCFieldElementSubtract.isZero()) {
                    return curve.getInfinity();
                }
                ECFieldElement eCFieldElementThree = three(eCFieldElementMultiply.square());
                ECFieldElement eCFieldElementTwo = two(eCFieldElementSubtract);
                ECFieldElement eCFieldElementMultiply2 = eCFieldElementTwo.multiply(eCFieldElementSubtract);
                ECFieldElement eCFieldElementTwo2 = two(eCFieldElementMultiply.multiply(eCFieldElementMultiply2));
                ECFieldElement eCFieldElementTwo3 = two(eCFieldElementMultiply2.square());
                if (!a2.isZero()) {
                    eCFieldElementThree = eCFieldElementThree.add(a2);
                    a2 = two(eCFieldElementTwo3.multiply(a2));
                }
                ECFieldElement eCFieldElementSubtract2 = eCFieldElementThree.square().subtract(two(eCFieldElementTwo2));
                eCFieldElementSubtract = eCFieldElementThree.multiply(eCFieldElementTwo2.subtract(eCFieldElementSubtract2)).subtract(eCFieldElementTwo3);
                eCFieldElementFromBigInteger = eCFieldElementFromBigInteger.isOne() ? eCFieldElementTwo : eCFieldElementTwo.multiply(eCFieldElementFromBigInteger);
                i3++;
                eCFieldElementMultiply = eCFieldElementSubtract2;
            }
            if (coordinateSystem == 0) {
                ECFieldElement eCFieldElementInvert = eCFieldElementFromBigInteger.invert();
                ECFieldElement eCFieldElementSquare2 = eCFieldElementInvert.square();
                return new Fp(curve, eCFieldElementMultiply.multiply(eCFieldElementSquare2), eCFieldElementSubtract.multiply(eCFieldElementSquare2.multiply(eCFieldElementInvert)), this.withCompression);
            }
            if (coordinateSystem == 1) {
                return new Fp(curve, eCFieldElementMultiply.multiply(eCFieldElementFromBigInteger), eCFieldElementSubtract, new ECFieldElement[]{eCFieldElementFromBigInteger.multiply(eCFieldElementFromBigInteger.square())}, this.withCompression);
            }
            if (coordinateSystem == 2) {
                return new Fp(curve, eCFieldElementMultiply, eCFieldElementSubtract, new ECFieldElement[]{eCFieldElementFromBigInteger}, this.withCompression);
            }
            if (coordinateSystem == 4) {
                return new Fp(curve, eCFieldElementMultiply, eCFieldElementSubtract, new ECFieldElement[]{eCFieldElementFromBigInteger, a2}, this.withCompression);
            }
            throw new IllegalStateException("unsupported coordinate system");
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint twice() {
            ECFieldElement eCFieldElementSubtract;
            ECFieldElement eCFieldElementFour;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement = this.f7507y;
            if (eCFieldElement.isZero()) {
                return curve.getInfinity();
            }
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement2 = this.f7506x;
            if (coordinateSystem == 0) {
                ECFieldElement eCFieldElementDivide = three(eCFieldElement2.square()).add(getCurve().getA()).divide(two(eCFieldElement));
                ECFieldElement eCFieldElementSubtract2 = eCFieldElementDivide.square().subtract(two(eCFieldElement2));
                return new Fp(curve, eCFieldElementSubtract2, eCFieldElementDivide.multiply(eCFieldElement2.subtract(eCFieldElementSubtract2)).subtract(eCFieldElement), this.withCompression);
            }
            if (coordinateSystem == 1) {
                ECFieldElement eCFieldElement3 = this.zs[0];
                boolean zIsOne = eCFieldElement3.isOne();
                ECFieldElement a2 = curve.getA();
                if (!a2.isZero() && !zIsOne) {
                    a2 = a2.multiply(eCFieldElement3.square());
                }
                ECFieldElement eCFieldElementAdd = a2.add(three(eCFieldElement2.square()));
                ECFieldElement eCFieldElementMultiply = zIsOne ? eCFieldElement : eCFieldElement.multiply(eCFieldElement3);
                ECFieldElement eCFieldElementSquare = zIsOne ? eCFieldElement.square() : eCFieldElementMultiply.multiply(eCFieldElement);
                ECFieldElement eCFieldElementFour2 = four(eCFieldElement2.multiply(eCFieldElementSquare));
                ECFieldElement eCFieldElementSubtract3 = eCFieldElementAdd.square().subtract(two(eCFieldElementFour2));
                ECFieldElement eCFieldElementTwo = two(eCFieldElementMultiply);
                ECFieldElement eCFieldElementMultiply2 = eCFieldElementSubtract3.multiply(eCFieldElementTwo);
                ECFieldElement eCFieldElementTwo2 = two(eCFieldElementSquare);
                return new Fp(curve, eCFieldElementMultiply2, eCFieldElementFour2.subtract(eCFieldElementSubtract3).multiply(eCFieldElementAdd).subtract(two(eCFieldElementTwo2.square())), new ECFieldElement[]{two(zIsOne ? two(eCFieldElementTwo2) : eCFieldElementTwo.square()).multiply(eCFieldElementMultiply)}, this.withCompression);
            }
            if (coordinateSystem != 2) {
                if (coordinateSystem == 4) {
                    return twiceJacobianModified(true);
                }
                throw new IllegalStateException("unsupported coordinate system");
            }
            ECFieldElement eCFieldElement4 = this.zs[0];
            boolean zIsOne2 = eCFieldElement4.isOne();
            ECFieldElement eCFieldElementSquare2 = eCFieldElement.square();
            ECFieldElement eCFieldElementSquare3 = eCFieldElementSquare2.square();
            ECFieldElement a3 = curve.getA();
            ECFieldElement eCFieldElementNegate = a3.negate();
            if (eCFieldElementNegate.toBigInteger().equals(BigInteger.valueOf(3L))) {
                ECFieldElement eCFieldElementSquare4 = zIsOne2 ? eCFieldElement4 : eCFieldElement4.square();
                eCFieldElementSubtract = three(eCFieldElement2.add(eCFieldElementSquare4).multiply(eCFieldElement2.subtract(eCFieldElementSquare4)));
                eCFieldElementFour = four(eCFieldElementSquare2.multiply(eCFieldElement2));
            } else {
                ECFieldElement eCFieldElementThree = three(eCFieldElement2.square());
                if (zIsOne2) {
                    eCFieldElementSubtract = eCFieldElementThree.add(a3);
                } else if (a3.isZero()) {
                    eCFieldElementSubtract = eCFieldElementThree;
                } else {
                    ECFieldElement eCFieldElementSquare5 = eCFieldElement4.square().square();
                    eCFieldElementSubtract = eCFieldElementNegate.bitLength() < a3.bitLength() ? eCFieldElementThree.subtract(eCFieldElementSquare5.multiply(eCFieldElementNegate)) : eCFieldElementThree.add(eCFieldElementSquare5.multiply(a3));
                }
                eCFieldElementFour = four(eCFieldElement2.multiply(eCFieldElementSquare2));
            }
            ECFieldElement eCFieldElementSubtract4 = eCFieldElementSubtract.square().subtract(two(eCFieldElementFour));
            ECFieldElement eCFieldElementSubtract5 = eCFieldElementFour.subtract(eCFieldElementSubtract4).multiply(eCFieldElementSubtract).subtract(eight(eCFieldElementSquare3));
            ECFieldElement eCFieldElementTwo3 = two(eCFieldElement);
            if (!zIsOne2) {
                eCFieldElementTwo3 = eCFieldElementTwo3.multiply(eCFieldElement4);
            }
            return new Fp(curve, eCFieldElementSubtract4, eCFieldElementSubtract5, new ECFieldElement[]{eCFieldElementTwo3}, this.withCompression);
        }

        public Fp twiceJacobianModified(boolean z2) {
            ECFieldElement eCFieldElement = this.f7506x;
            ECFieldElement eCFieldElement2 = this.f7507y;
            ECFieldElement eCFieldElement3 = this.zs[0];
            ECFieldElement jacobianModifiedW = getJacobianModifiedW();
            ECFieldElement eCFieldElementAdd = three(eCFieldElement.square()).add(jacobianModifiedW);
            ECFieldElement eCFieldElementTwo = two(eCFieldElement2);
            ECFieldElement eCFieldElementMultiply = eCFieldElementTwo.multiply(eCFieldElement2);
            ECFieldElement eCFieldElementTwo2 = two(eCFieldElement.multiply(eCFieldElementMultiply));
            ECFieldElement eCFieldElementSubtract = eCFieldElementAdd.square().subtract(two(eCFieldElementTwo2));
            ECFieldElement eCFieldElementTwo3 = two(eCFieldElementMultiply.square());
            ECFieldElement eCFieldElementSubtract2 = eCFieldElementAdd.multiply(eCFieldElementTwo2.subtract(eCFieldElementSubtract)).subtract(eCFieldElementTwo3);
            ECFieldElement eCFieldElementTwo4 = z2 ? two(eCFieldElementTwo3.multiply(jacobianModifiedW)) : null;
            if (!eCFieldElement3.isOne()) {
                eCFieldElementTwo = eCFieldElementTwo.multiply(eCFieldElement3);
            }
            return new Fp(getCurve(), eCFieldElementSubtract, eCFieldElementSubtract2, new ECFieldElement[]{eCFieldElementTwo, eCFieldElementTwo4}, this.withCompression);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint twicePlus(ECPoint eCPoint) {
            if (this == eCPoint) {
                return threeTimes();
            }
            if (isInfinity()) {
                return eCPoint;
            }
            if (eCPoint.isInfinity()) {
                return twice();
            }
            ECFieldElement eCFieldElement = this.f7507y;
            if (eCFieldElement.isZero()) {
                return eCPoint;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem != 0) {
                return coordinateSystem != 4 ? twice().add(eCPoint) : twiceJacobianModified(false).add(eCPoint);
            }
            ECFieldElement eCFieldElement2 = this.f7506x;
            ECFieldElement eCFieldElement3 = eCPoint.f7506x;
            ECFieldElement eCFieldElement4 = eCPoint.f7507y;
            ECFieldElement eCFieldElementSubtract = eCFieldElement3.subtract(eCFieldElement2);
            ECFieldElement eCFieldElementSubtract2 = eCFieldElement4.subtract(eCFieldElement);
            if (eCFieldElementSubtract.isZero()) {
                return eCFieldElementSubtract2.isZero() ? threeTimes() : this;
            }
            ECFieldElement eCFieldElementSquare = eCFieldElementSubtract.square();
            ECFieldElement eCFieldElementSubtract3 = eCFieldElementSquare.multiply(two(eCFieldElement2).add(eCFieldElement3)).subtract(eCFieldElementSubtract2.square());
            if (eCFieldElementSubtract3.isZero()) {
                return curve.getInfinity();
            }
            ECFieldElement eCFieldElementInvert = eCFieldElementSubtract3.multiply(eCFieldElementSubtract).invert();
            ECFieldElement eCFieldElementMultiply = eCFieldElementSubtract3.multiply(eCFieldElementInvert).multiply(eCFieldElementSubtract2);
            ECFieldElement eCFieldElementSubtract4 = two(eCFieldElement).multiply(eCFieldElementSquare).multiply(eCFieldElementSubtract).multiply(eCFieldElementInvert).subtract(eCFieldElementMultiply);
            ECFieldElement eCFieldElementAdd = eCFieldElementSubtract4.subtract(eCFieldElementMultiply).multiply(eCFieldElementMultiply.add(eCFieldElementSubtract4)).add(eCFieldElement3);
            return new Fp(curve, eCFieldElementAdd, eCFieldElement2.subtract(eCFieldElementAdd).multiply(eCFieldElementSubtract4).subtract(eCFieldElement), this.withCompression);
        }

        public ECFieldElement two(ECFieldElement eCFieldElement) {
            return eCFieldElement.add(eCFieldElement);
        }

        public Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
            if ((eCFieldElement == null) != (eCFieldElement2 == null)) {
                throw new IllegalArgumentException("Exactly one of the field elements is null");
            }
            this.withCompression = z2;
        }

        public Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z2) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
            this.withCompression = z2;
        }
    }

    public ECPoint(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, getInitialZCoords(eCCurve));
    }

    public static ECFieldElement[] getInitialZCoords(ECCurve eCCurve) {
        int coordinateSystem = eCCurve == null ? 0 : eCCurve.getCoordinateSystem();
        if (coordinateSystem == 0 || coordinateSystem == 5) {
            return EMPTY_ZS;
        }
        ECFieldElement eCFieldElementFromBigInteger = eCCurve.fromBigInteger(ECConstants.ONE);
        if (coordinateSystem != 1 && coordinateSystem != 2) {
            if (coordinateSystem == 3) {
                return new ECFieldElement[]{eCFieldElementFromBigInteger, eCFieldElementFromBigInteger, eCFieldElementFromBigInteger};
            }
            if (coordinateSystem == 4) {
                return new ECFieldElement[]{eCFieldElementFromBigInteger, eCCurve.getA()};
            }
            if (coordinateSystem != 6) {
                throw new IllegalArgumentException("unknown coordinate system");
            }
        }
        return new ECFieldElement[]{eCFieldElementFromBigInteger};
    }

    public abstract ECPoint add(ECPoint eCPoint);

    public void checkNormalized() {
        if (!isNormalized()) {
            throw new IllegalStateException("point not in normal form");
        }
    }

    public ECPoint createScaledPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return getCurve().createRawPoint(getRawXCoord().multiply(eCFieldElement), getRawYCoord().multiply(eCFieldElement2), this.withCompression);
    }

    public abstract ECPoint detach();

    public boolean equals(ECPoint eCPoint) {
        ECPoint eCPointNormalize;
        if (eCPoint == null) {
            return false;
        }
        ECCurve curve = getCurve();
        ECCurve curve2 = eCPoint.getCurve();
        boolean z2 = curve == null;
        boolean z3 = curve2 == null;
        boolean zIsInfinity = isInfinity();
        boolean zIsInfinity2 = eCPoint.isInfinity();
        if (zIsInfinity || zIsInfinity2) {
            return zIsInfinity && zIsInfinity2 && (z2 || z3 || curve.equals(curve2));
        }
        if (z2 && z3) {
            eCPointNormalize = this;
        } else if (z2) {
            eCPoint = eCPoint.normalize();
            eCPointNormalize = this;
        } else if (z3) {
            eCPointNormalize = normalize();
        } else {
            if (!curve.equals(curve2)) {
                return false;
            }
            ECPoint[] eCPointArr = {this, curve.importPoint(eCPoint)};
            curve.normalizeAll(eCPointArr);
            eCPointNormalize = eCPointArr[0];
            eCPoint = eCPointArr[1];
        }
        return eCPointNormalize.getXCoord().equals(eCPoint.getXCoord()) && eCPointNormalize.getYCoord().equals(eCPoint.getYCoord());
    }

    public ECFieldElement getAffineXCoord() {
        checkNormalized();
        return getXCoord();
    }

    public ECFieldElement getAffineYCoord() {
        checkNormalized();
        return getYCoord();
    }

    public abstract boolean getCompressionYTilde();

    public ECCurve getCurve() {
        return this.curve;
    }

    public int getCurveCoordinateSystem() {
        ECCurve eCCurve = this.curve;
        if (eCCurve == null) {
            return 0;
        }
        return eCCurve.getCoordinateSystem();
    }

    public final ECPoint getDetachedPoint() {
        return normalize().detach();
    }

    public byte[] getEncoded() {
        return getEncoded(this.withCompression);
    }

    public final ECFieldElement getRawXCoord() {
        return this.f7506x;
    }

    public final ECFieldElement getRawYCoord() {
        return this.f7507y;
    }

    public final ECFieldElement[] getRawZCoords() {
        return this.zs;
    }

    public ECFieldElement getX() {
        return normalize().getXCoord();
    }

    public ECFieldElement getXCoord() {
        return this.f7506x;
    }

    public ECFieldElement getY() {
        return normalize().getYCoord();
    }

    public ECFieldElement getYCoord() {
        return this.f7507y;
    }

    public ECFieldElement getZCoord(int i2) {
        if (i2 >= 0) {
            ECFieldElement[] eCFieldElementArr = this.zs;
            if (i2 < eCFieldElementArr.length) {
                return eCFieldElementArr[i2];
            }
        }
        return null;
    }

    public ECFieldElement[] getZCoords() {
        ECFieldElement[] eCFieldElementArr = this.zs;
        int length = eCFieldElementArr.length;
        if (length == 0) {
            return EMPTY_ZS;
        }
        ECFieldElement[] eCFieldElementArr2 = new ECFieldElement[length];
        System.arraycopy(eCFieldElementArr, 0, eCFieldElementArr2, 0, length);
        return eCFieldElementArr2;
    }

    public int hashCode() {
        ECCurve curve = getCurve();
        int iHashCode = curve == null ? 0 : curve.hashCode() ^ (-1);
        if (isInfinity()) {
            return iHashCode;
        }
        ECPoint eCPointNormalize = normalize();
        return (iHashCode ^ (eCPointNormalize.getXCoord().hashCode() * 17)) ^ (eCPointNormalize.getYCoord().hashCode() * 257);
    }

    public boolean isCompressed() {
        return this.withCompression;
    }

    public boolean isInfinity() {
        if (this.f7506x != null && this.f7507y != null) {
            ECFieldElement[] eCFieldElementArr = this.zs;
            if (eCFieldElementArr.length <= 0 || !eCFieldElementArr[0].isZero()) {
                return false;
            }
        }
        return true;
    }

    public boolean isNormalized() {
        int curveCoordinateSystem = getCurveCoordinateSystem();
        return curveCoordinateSystem == 0 || curveCoordinateSystem == 5 || isInfinity() || this.zs[0].isOne();
    }

    public boolean isValid() {
        return isInfinity() || getCurve() == null || (satisfiesCurveEquation() && satisfiesCofactor());
    }

    public ECPoint multiply(BigInteger bigInteger) {
        return getCurve().getMultiplier().multiply(this, bigInteger);
    }

    public abstract ECPoint negate();

    public ECPoint normalize() {
        int curveCoordinateSystem;
        if (isInfinity() || (curveCoordinateSystem = getCurveCoordinateSystem()) == 0 || curveCoordinateSystem == 5) {
            return this;
        }
        ECFieldElement zCoord = getZCoord(0);
        return zCoord.isOne() ? this : normalize(zCoord.invert());
    }

    public boolean satisfiesCofactor() {
        BigInteger cofactor = this.curve.getCofactor();
        return cofactor == null || cofactor.equals(ECConstants.ONE) || !ECAlgorithms.referenceMultiply(this, cofactor).isInfinity();
    }

    public abstract boolean satisfiesCurveEquation();

    public ECPoint scaleX(ECFieldElement eCFieldElement) {
        return isInfinity() ? this : getCurve().createRawPoint(getRawXCoord().multiply(eCFieldElement), getRawYCoord(), getRawZCoords(), this.withCompression);
    }

    public ECPoint scaleY(ECFieldElement eCFieldElement) {
        return isInfinity() ? this : getCurve().createRawPoint(getRawXCoord(), getRawYCoord().multiply(eCFieldElement), getRawZCoords(), this.withCompression);
    }

    public abstract ECPoint subtract(ECPoint eCPoint);

    public ECPoint threeTimes() {
        return twicePlus(this);
    }

    public ECPoint timesPow2(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("'e' cannot be negative");
        }
        ECPoint eCPointTwice = this;
        while (true) {
            i2--;
            if (i2 < 0) {
                return eCPointTwice;
            }
            eCPointTwice = eCPointTwice.twice();
        }
    }

    public String toString() {
        if (isInfinity()) {
            return "INF";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('(');
        stringBuffer.append(getRawXCoord());
        stringBuffer.append(',');
        stringBuffer.append(getRawYCoord());
        for (int i2 = 0; i2 < this.zs.length; i2++) {
            stringBuffer.append(',');
            stringBuffer.append(this.zs[i2]);
        }
        stringBuffer.append(')');
        return stringBuffer.toString();
    }

    public abstract ECPoint twice();

    public ECPoint twicePlus(ECPoint eCPoint) {
        return twice().add(eCPoint);
    }

    public ECPoint(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        this.preCompTable = null;
        this.curve = eCCurve;
        this.f7506x = eCFieldElement;
        this.f7507y = eCFieldElement2;
        this.zs = eCFieldElementArr;
    }

    public byte[] getEncoded(boolean z2) {
        if (isInfinity()) {
            return new byte[1];
        }
        ECPoint eCPointNormalize = normalize();
        byte[] encoded = eCPointNormalize.getXCoord().getEncoded();
        if (z2) {
            byte[] bArr = new byte[encoded.length + 1];
            bArr[0] = (byte) (eCPointNormalize.getCompressionYTilde() ? 3 : 2);
            System.arraycopy(encoded, 0, bArr, 1, encoded.length);
            return bArr;
        }
        byte[] encoded2 = eCPointNormalize.getYCoord().getEncoded();
        byte[] bArr2 = new byte[encoded.length + encoded2.length + 1];
        bArr2[0] = 4;
        System.arraycopy(encoded, 0, bArr2, 1, encoded.length);
        System.arraycopy(encoded2, 0, bArr2, encoded.length + 1, encoded2.length);
        return bArr2;
    }

    public ECPoint normalize(ECFieldElement eCFieldElement) {
        int curveCoordinateSystem = getCurveCoordinateSystem();
        if (curveCoordinateSystem != 1) {
            if (curveCoordinateSystem == 2 || curveCoordinateSystem == 3 || curveCoordinateSystem == 4) {
                ECFieldElement eCFieldElementSquare = eCFieldElement.square();
                return createScaledPoint(eCFieldElementSquare, eCFieldElementSquare.multiply(eCFieldElement));
            }
            if (curveCoordinateSystem != 6) {
                throw new IllegalStateException("not a projective coordinate system");
            }
        }
        return createScaledPoint(eCFieldElement, eCFieldElement);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ECPoint) {
            return equals((ECPoint) obj);
        }
        return false;
    }
}

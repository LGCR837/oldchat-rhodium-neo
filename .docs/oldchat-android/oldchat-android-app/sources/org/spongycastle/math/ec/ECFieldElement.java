package org.spongycastle.math.ec;

import java.math.BigInteger;
import java.util.Random;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;

/* JADX INFO: loaded from: classes.dex */
public abstract class ECFieldElement implements ECConstants {

    public static class Fp extends ECFieldElement {

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        BigInteger f7503q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        BigInteger f7504r;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        BigInteger f7505x;

        public Fp(BigInteger bigInteger, BigInteger bigInteger2) {
            this(bigInteger, calculateResidue(bigInteger), bigInteger2);
        }

        public static BigInteger calculateResidue(BigInteger bigInteger) {
            int iBitLength = bigInteger.bitLength();
            if (iBitLength < 96 || bigInteger.shiftRight(iBitLength - 64).longValue() != -1) {
                return null;
            }
            return ECConstants.ONE.shiftLeft(iBitLength).subtract(bigInteger);
        }

        private ECFieldElement checkSqrt(ECFieldElement eCFieldElement) {
            if (eCFieldElement.square().equals(this)) {
                return eCFieldElement;
            }
            return null;
        }

        private BigInteger[] lucasSequence(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            int iBitLength = bigInteger3.bitLength();
            int lowestSetBit = bigInteger3.getLowestSetBit();
            BigInteger bigIntegerModMult = ECConstants.ONE;
            BigInteger bigIntegerModReduce = bigInteger;
            BigInteger bigIntegerModMult2 = bigIntegerModMult;
            BigInteger bigIntegerModReduce2 = ECConstants.TWO;
            BigInteger bigIntegerModMult3 = bigIntegerModMult2;
            for (int i2 = iBitLength - 1; i2 >= lowestSetBit + 1; i2--) {
                bigIntegerModMult = modMult(bigIntegerModMult, bigIntegerModMult3);
                if (bigInteger3.testBit(i2)) {
                    bigIntegerModMult3 = modMult(bigIntegerModMult, bigInteger2);
                    bigIntegerModMult2 = modMult(bigIntegerModMult2, bigIntegerModReduce);
                    bigIntegerModReduce2 = modReduce(bigIntegerModReduce.multiply(bigIntegerModReduce2).subtract(bigInteger.multiply(bigIntegerModMult)));
                    bigIntegerModReduce = modReduce(bigIntegerModReduce.multiply(bigIntegerModReduce).subtract(bigIntegerModMult3.shiftLeft(1)));
                } else {
                    BigInteger bigIntegerModReduce3 = modReduce(bigIntegerModMult2.multiply(bigIntegerModReduce2).subtract(bigIntegerModMult));
                    BigInteger bigIntegerModReduce4 = modReduce(bigIntegerModReduce.multiply(bigIntegerModReduce2).subtract(bigInteger.multiply(bigIntegerModMult)));
                    bigIntegerModReduce2 = modReduce(bigIntegerModReduce2.multiply(bigIntegerModReduce2).subtract(bigIntegerModMult.shiftLeft(1)));
                    bigIntegerModReduce = bigIntegerModReduce4;
                    bigIntegerModMult2 = bigIntegerModReduce3;
                    bigIntegerModMult3 = bigIntegerModMult;
                }
            }
            BigInteger bigIntegerModMult4 = modMult(bigIntegerModMult, bigIntegerModMult3);
            BigInteger bigIntegerModMult5 = modMult(bigIntegerModMult4, bigInteger2);
            BigInteger bigIntegerModReduce5 = modReduce(bigIntegerModMult2.multiply(bigIntegerModReduce2).subtract(bigIntegerModMult4));
            BigInteger bigIntegerModReduce6 = modReduce(bigIntegerModReduce.multiply(bigIntegerModReduce2).subtract(bigInteger.multiply(bigIntegerModMult4)));
            BigInteger bigIntegerModMult6 = modMult(bigIntegerModMult4, bigIntegerModMult5);
            for (int i3 = 1; i3 <= lowestSetBit; i3++) {
                bigIntegerModReduce5 = modMult(bigIntegerModReduce5, bigIntegerModReduce6);
                bigIntegerModReduce6 = modReduce(bigIntegerModReduce6.multiply(bigIntegerModReduce6).subtract(bigIntegerModMult6.shiftLeft(1)));
                bigIntegerModMult6 = modMult(bigIntegerModMult6, bigIntegerModMult6);
            }
            return new BigInteger[]{bigIntegerModReduce5, bigIntegerModReduce6};
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement add(ECFieldElement eCFieldElement) {
            return new Fp(this.f7503q, this.f7504r, modAdd(this.f7505x, eCFieldElement.toBigInteger()));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement addOne() {
            BigInteger bigIntegerAdd = this.f7505x.add(ECConstants.ONE);
            if (bigIntegerAdd.compareTo(this.f7503q) == 0) {
                bigIntegerAdd = ECConstants.ZERO;
            }
            return new Fp(this.f7503q, this.f7504r, bigIntegerAdd);
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement divide(ECFieldElement eCFieldElement) {
            return new Fp(this.f7503q, this.f7504r, modMult(this.f7505x, modInverse(eCFieldElement.toBigInteger())));
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Fp)) {
                return false;
            }
            Fp fp = (Fp) obj;
            return this.f7503q.equals(fp.f7503q) && this.f7505x.equals(fp.f7505x);
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public String getFieldName() {
            return "Fp";
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public int getFieldSize() {
            return this.f7503q.bitLength();
        }

        public BigInteger getQ() {
            return this.f7503q;
        }

        public int hashCode() {
            return this.f7503q.hashCode() ^ this.f7505x.hashCode();
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement invert() {
            return new Fp(this.f7503q, this.f7504r, modInverse(this.f7505x));
        }

        public BigInteger modAdd(BigInteger bigInteger, BigInteger bigInteger2) {
            BigInteger bigIntegerAdd = bigInteger.add(bigInteger2);
            return bigIntegerAdd.compareTo(this.f7503q) >= 0 ? bigIntegerAdd.subtract(this.f7503q) : bigIntegerAdd;
        }

        public BigInteger modDouble(BigInteger bigInteger) {
            BigInteger bigIntegerShiftLeft = bigInteger.shiftLeft(1);
            return bigIntegerShiftLeft.compareTo(this.f7503q) >= 0 ? bigIntegerShiftLeft.subtract(this.f7503q) : bigIntegerShiftLeft;
        }

        public BigInteger modHalf(BigInteger bigInteger) {
            if (bigInteger.testBit(0)) {
                bigInteger = this.f7503q.add(bigInteger);
            }
            return bigInteger.shiftRight(1);
        }

        public BigInteger modHalfAbs(BigInteger bigInteger) {
            if (bigInteger.testBit(0)) {
                bigInteger = this.f7503q.subtract(bigInteger);
            }
            return bigInteger.shiftRight(1);
        }

        public BigInteger modInverse(BigInteger bigInteger) {
            int fieldSize = getFieldSize();
            int i2 = (fieldSize + 31) >> 5;
            int[] iArrFromBigInteger = Nat.fromBigInteger(fieldSize, this.f7503q);
            int[] iArrFromBigInteger2 = Nat.fromBigInteger(fieldSize, bigInteger);
            int[] iArrCreate = Nat.create(i2);
            Mod.invert(iArrFromBigInteger, iArrFromBigInteger2, iArrCreate);
            return Nat.toBigInteger(i2, iArrCreate);
        }

        public BigInteger modMult(BigInteger bigInteger, BigInteger bigInteger2) {
            return modReduce(bigInteger.multiply(bigInteger2));
        }

        public BigInteger modReduce(BigInteger bigInteger) {
            if (this.f7504r == null) {
                return bigInteger.mod(this.f7503q);
            }
            boolean z2 = bigInteger.signum() < 0;
            if (z2) {
                bigInteger = bigInteger.abs();
            }
            int iBitLength = this.f7503q.bitLength();
            boolean zEquals = this.f7504r.equals(ECConstants.ONE);
            while (bigInteger.bitLength() > iBitLength + 1) {
                BigInteger bigIntegerShiftRight = bigInteger.shiftRight(iBitLength);
                BigInteger bigIntegerSubtract = bigInteger.subtract(bigIntegerShiftRight.shiftLeft(iBitLength));
                if (!zEquals) {
                    bigIntegerShiftRight = bigIntegerShiftRight.multiply(this.f7504r);
                }
                bigInteger = bigIntegerShiftRight.add(bigIntegerSubtract);
            }
            while (bigInteger.compareTo(this.f7503q) >= 0) {
                bigInteger = bigInteger.subtract(this.f7503q);
            }
            return (!z2 || bigInteger.signum() == 0) ? bigInteger : this.f7503q.subtract(bigInteger);
        }

        public BigInteger modSubtract(BigInteger bigInteger, BigInteger bigInteger2) {
            BigInteger bigIntegerSubtract = bigInteger.subtract(bigInteger2);
            return bigIntegerSubtract.signum() < 0 ? bigIntegerSubtract.add(this.f7503q) : bigIntegerSubtract;
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement multiply(ECFieldElement eCFieldElement) {
            return new Fp(this.f7503q, this.f7504r, modMult(this.f7505x, eCFieldElement.toBigInteger()));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            BigInteger bigInteger = this.f7505x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            BigInteger bigInteger4 = eCFieldElement3.toBigInteger();
            return new Fp(this.f7503q, this.f7504r, modReduce(bigInteger.multiply(bigInteger2).subtract(bigInteger3.multiply(bigInteger4))));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            BigInteger bigInteger = this.f7505x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            BigInteger bigInteger4 = eCFieldElement3.toBigInteger();
            return new Fp(this.f7503q, this.f7504r, modReduce(bigInteger.multiply(bigInteger2).add(bigInteger3.multiply(bigInteger4))));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement negate() {
            if (this.f7505x.signum() == 0) {
                return this;
            }
            BigInteger bigInteger = this.f7503q;
            return new Fp(bigInteger, this.f7504r, bigInteger.subtract(this.f7505x));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement sqrt() {
            if (isZero() || isOne()) {
                return this;
            }
            if (!this.f7503q.testBit(0)) {
                throw new RuntimeException("not done yet");
            }
            if (this.f7503q.testBit(1)) {
                BigInteger bigIntegerAdd = this.f7503q.shiftRight(2).add(ECConstants.ONE);
                BigInteger bigInteger = this.f7503q;
                return checkSqrt(new Fp(bigInteger, this.f7504r, this.f7505x.modPow(bigIntegerAdd, bigInteger)));
            }
            if (this.f7503q.testBit(2)) {
                BigInteger bigIntegerModPow = this.f7505x.modPow(this.f7503q.shiftRight(3), this.f7503q);
                BigInteger bigIntegerModMult = modMult(bigIntegerModPow, this.f7505x);
                if (modMult(bigIntegerModMult, bigIntegerModPow).equals(ECConstants.ONE)) {
                    return checkSqrt(new Fp(this.f7503q, this.f7504r, bigIntegerModMult));
                }
                return checkSqrt(new Fp(this.f7503q, this.f7504r, modMult(bigIntegerModMult, ECConstants.TWO.modPow(this.f7503q.shiftRight(2), this.f7503q))));
            }
            BigInteger bigIntegerShiftRight = this.f7503q.shiftRight(1);
            BigInteger bigIntegerModPow2 = this.f7505x.modPow(bigIntegerShiftRight, this.f7503q);
            BigInteger bigInteger2 = ECConstants.ONE;
            if (!bigIntegerModPow2.equals(bigInteger2)) {
                return null;
            }
            BigInteger bigInteger3 = this.f7505x;
            BigInteger bigIntegerModDouble = modDouble(modDouble(bigInteger3));
            BigInteger bigIntegerAdd2 = bigIntegerShiftRight.add(bigInteger2);
            BigInteger bigIntegerSubtract = this.f7503q.subtract(bigInteger2);
            Random random = new Random();
            while (true) {
                BigInteger bigInteger4 = new BigInteger(this.f7503q.bitLength(), random);
                if (bigInteger4.compareTo(this.f7503q) < 0 && modReduce(bigInteger4.multiply(bigInteger4).subtract(bigIntegerModDouble)).modPow(bigIntegerShiftRight, this.f7503q).equals(bigIntegerSubtract)) {
                    BigInteger[] bigIntegerArrLucasSequence = lucasSequence(bigInteger4, bigInteger3, bigIntegerAdd2);
                    BigInteger bigInteger5 = bigIntegerArrLucasSequence[0];
                    BigInteger bigInteger6 = bigIntegerArrLucasSequence[1];
                    if (modMult(bigInteger6, bigInteger6).equals(bigIntegerModDouble)) {
                        return new Fp(this.f7503q, this.f7504r, modHalfAbs(bigInteger6));
                    }
                    if (!bigInteger5.equals(ECConstants.ONE) && !bigInteger5.equals(bigIntegerSubtract)) {
                        return null;
                    }
                }
            }
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement square() {
            BigInteger bigInteger = this.f7503q;
            BigInteger bigInteger2 = this.f7504r;
            BigInteger bigInteger3 = this.f7505x;
            return new Fp(bigInteger, bigInteger2, modMult(bigInteger3, bigInteger3));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            BigInteger bigInteger = this.f7505x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            return new Fp(this.f7503q, this.f7504r, modReduce(bigInteger.multiply(bigInteger).subtract(bigInteger2.multiply(bigInteger3))));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            BigInteger bigInteger = this.f7505x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            return new Fp(this.f7503q, this.f7504r, modReduce(bigInteger.multiply(bigInteger).add(bigInteger2.multiply(bigInteger3))));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement subtract(ECFieldElement eCFieldElement) {
            return new Fp(this.f7503q, this.f7504r, modSubtract(this.f7505x, eCFieldElement.toBigInteger()));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public BigInteger toBigInteger() {
            return this.f7505x;
        }

        public Fp(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            if (bigInteger3 == null || bigInteger3.signum() < 0 || bigInteger3.compareTo(bigInteger) >= 0) {
                throw new IllegalArgumentException("x value invalid in Fp field element");
            }
            this.f7503q = bigInteger;
            this.f7504r = bigInteger2;
            this.f7505x = bigInteger3;
        }
    }

    public abstract ECFieldElement add(ECFieldElement eCFieldElement);

    public abstract ECFieldElement addOne();

    public int bitLength() {
        return toBigInteger().bitLength();
    }

    public abstract ECFieldElement divide(ECFieldElement eCFieldElement);

    public byte[] getEncoded() {
        return BigIntegers.asUnsignedByteArray((getFieldSize() + 7) / 8, toBigInteger());
    }

    public abstract String getFieldName();

    public abstract int getFieldSize();

    public abstract ECFieldElement invert();

    public boolean isOne() {
        return bitLength() == 1;
    }

    public boolean isZero() {
        return toBigInteger().signum() == 0;
    }

    public abstract ECFieldElement multiply(ECFieldElement eCFieldElement);

    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiply(eCFieldElement).subtract(eCFieldElement2.multiply(eCFieldElement3));
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiply(eCFieldElement).add(eCFieldElement2.multiply(eCFieldElement3));
    }

    public abstract ECFieldElement negate();

    public abstract ECFieldElement sqrt();

    public abstract ECFieldElement square();

    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return square().subtract(eCFieldElement.multiply(eCFieldElement2));
    }

    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return square().add(eCFieldElement.multiply(eCFieldElement2));
    }

    public ECFieldElement squarePow(int i2) {
        ECFieldElement eCFieldElementSquare = this;
        for (int i3 = 0; i3 < i2; i3++) {
            eCFieldElementSquare = eCFieldElementSquare.square();
        }
        return eCFieldElementSquare;
    }

    public abstract ECFieldElement subtract(ECFieldElement eCFieldElement);

    public boolean testBitZero() {
        return toBigInteger().testBit(0);
    }

    public abstract BigInteger toBigInteger();

    public String toString() {
        return toBigInteger().toString(16);
    }

    public static class F2m extends ECFieldElement {
        public static final int GNB = 1;
        public static final int PPB = 3;
        public static final int TPB = 2;
        private int[] ks;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private int f7501m;
        private int representation;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        private LongArray f7502x;

        public F2m(int i2, int i3, int i4, int i5, BigInteger bigInteger) {
            if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > i2) {
                throw new IllegalArgumentException("x value invalid in F2m field element");
            }
            if (i4 == 0 && i5 == 0) {
                this.representation = 2;
                this.ks = new int[]{i3};
            } else {
                if (i4 >= i5) {
                    throw new IllegalArgumentException("k2 must be smaller than k3");
                }
                if (i4 <= 0) {
                    throw new IllegalArgumentException("k2 must be larger than 0");
                }
                this.representation = 3;
                this.ks = new int[]{i3, i4, i5};
            }
            this.f7501m = i2;
            this.f7502x = new LongArray(bigInteger);
        }

        public static void checkFieldElements(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            if (!(eCFieldElement instanceof F2m) || !(eCFieldElement2 instanceof F2m)) {
                throw new IllegalArgumentException("Field elements are not both instances of ECFieldElement.F2m");
            }
            F2m f2m = (F2m) eCFieldElement;
            F2m f2m2 = (F2m) eCFieldElement2;
            if (f2m.representation != f2m2.representation) {
                throw new IllegalArgumentException("One of the F2m field elements has incorrect representation");
            }
            if (f2m.f7501m != f2m2.f7501m || !Arrays.areEqual(f2m.ks, f2m2.ks)) {
                throw new IllegalArgumentException("Field elements are not elements of the same field F2m");
            }
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement add(ECFieldElement eCFieldElement) {
            LongArray longArray = (LongArray) this.f7502x.clone();
            longArray.addShiftedByWords(((F2m) eCFieldElement).f7502x, 0);
            return new F2m(this.f7501m, this.ks, longArray);
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement addOne() {
            return new F2m(this.f7501m, this.ks, this.f7502x.addOne());
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public int bitLength() {
            return this.f7502x.degree();
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement divide(ECFieldElement eCFieldElement) {
            return multiply(eCFieldElement.invert());
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof F2m)) {
                return false;
            }
            F2m f2m = (F2m) obj;
            return this.f7501m == f2m.f7501m && this.representation == f2m.representation && Arrays.areEqual(this.ks, f2m.ks) && this.f7502x.equals(f2m.f7502x);
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public String getFieldName() {
            return "F2m";
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public int getFieldSize() {
            return this.f7501m;
        }

        public int getK1() {
            return this.ks[0];
        }

        public int getK2() {
            int[] iArr = this.ks;
            if (iArr.length >= 2) {
                return iArr[1];
            }
            return 0;
        }

        public int getK3() {
            int[] iArr = this.ks;
            if (iArr.length >= 3) {
                return iArr[2];
            }
            return 0;
        }

        public int getM() {
            return this.f7501m;
        }

        public int getRepresentation() {
            return this.representation;
        }

        public int hashCode() {
            return (this.f7502x.hashCode() ^ this.f7501m) ^ Arrays.hashCode(this.ks);
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement invert() {
            int i2 = this.f7501m;
            int[] iArr = this.ks;
            return new F2m(i2, iArr, this.f7502x.modInverse(i2, iArr));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public boolean isOne() {
            return this.f7502x.isOne();
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public boolean isZero() {
            return this.f7502x.isZero();
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement multiply(ECFieldElement eCFieldElement) {
            int i2 = this.f7501m;
            int[] iArr = this.ks;
            return new F2m(i2, iArr, this.f7502x.modMultiply(((F2m) eCFieldElement).f7502x, i2, iArr));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            return multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            LongArray longArray = this.f7502x;
            LongArray longArray2 = ((F2m) eCFieldElement).f7502x;
            LongArray longArray3 = ((F2m) eCFieldElement2).f7502x;
            LongArray longArray4 = ((F2m) eCFieldElement3).f7502x;
            LongArray longArrayMultiply = longArray.multiply(longArray2, this.f7501m, this.ks);
            LongArray longArrayMultiply2 = longArray3.multiply(longArray4, this.f7501m, this.ks);
            if (longArrayMultiply == longArray || longArrayMultiply == longArray2) {
                longArrayMultiply = (LongArray) longArrayMultiply.clone();
            }
            longArrayMultiply.addShiftedByWords(longArrayMultiply2, 0);
            longArrayMultiply.reduce(this.f7501m, this.ks);
            return new F2m(this.f7501m, this.ks, longArrayMultiply);
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement negate() {
            return this;
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement sqrt() {
            return (this.f7502x.isZero() || this.f7502x.isOne()) ? this : squarePow(this.f7501m - 1);
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement square() {
            int i2 = this.f7501m;
            int[] iArr = this.ks;
            return new F2m(i2, iArr, this.f7502x.modSquare(i2, iArr));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            return squarePlusProduct(eCFieldElement, eCFieldElement2);
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            LongArray longArray = this.f7502x;
            LongArray longArray2 = ((F2m) eCFieldElement).f7502x;
            LongArray longArray3 = ((F2m) eCFieldElement2).f7502x;
            LongArray longArraySquare = longArray.square(this.f7501m, this.ks);
            LongArray longArrayMultiply = longArray2.multiply(longArray3, this.f7501m, this.ks);
            if (longArraySquare == longArray) {
                longArraySquare = (LongArray) longArraySquare.clone();
            }
            longArraySquare.addShiftedByWords(longArrayMultiply, 0);
            longArraySquare.reduce(this.f7501m, this.ks);
            return new F2m(this.f7501m, this.ks, longArraySquare);
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement squarePow(int i2) {
            if (i2 < 1) {
                return this;
            }
            int i3 = this.f7501m;
            int[] iArr = this.ks;
            return new F2m(i3, iArr, this.f7502x.modSquareN(i2, i3, iArr));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement subtract(ECFieldElement eCFieldElement) {
            return add(eCFieldElement);
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public boolean testBitZero() {
            return this.f7502x.testBitZero();
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public BigInteger toBigInteger() {
            return this.f7502x.toBigInteger();
        }

        public F2m(int i2, int i3, BigInteger bigInteger) {
            this(i2, i3, 0, 0, bigInteger);
        }

        private F2m(int i2, int[] iArr, LongArray longArray) {
            this.f7501m = i2;
            this.representation = iArr.length == 1 ? 2 : 3;
            this.ks = iArr;
            this.f7502x = longArray;
        }
    }
}

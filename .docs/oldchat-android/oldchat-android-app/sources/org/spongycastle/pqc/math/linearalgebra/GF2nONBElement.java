package org.spongycastle.pqc.math.linearalgebra;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.asn1.cmc.BodyPartID;

/* JADX INFO: loaded from: classes.dex */
public class GF2nONBElement extends GF2nElement {
    private static final int MAXLONG = 64;
    private int mBit;
    private int mLength;
    private long[] mPol;
    private static final long[] mBitmask = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576, 2097152, 4194304, 8388608, 16777216, 33554432, 67108864, 134217728, 268435456, 536870912, 1073741824, 2147483648L, 4294967296L, 8589934592L, 17179869184L, 34359738368L, 68719476736L, 137438953472L, 274877906944L, 549755813888L, 1099511627776L, 2199023255552L, 4398046511104L, 8796093022208L, 17592186044416L, 35184372088832L, 70368744177664L, 140737488355328L, 281474976710656L, 562949953421312L, 1125899906842624L, 2251799813685248L, 4503599627370496L, 9007199254740992L, 18014398509481984L, 36028797018963968L, 72057594037927936L, 144115188075855872L, 288230376151711744L, 576460752303423488L, 1152921504606846976L, 2305843009213693952L, 4611686018427387904L, Long.MIN_VALUE};
    private static final long[] mMaxmask = {1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, 1073741823, 2147483647L, BodyPartID.bodyIdMax, 8589934591L, 17179869183L, 34359738367L, 68719476735L, 137438953471L, 274877906943L, 549755813887L, 1099511627775L, 2199023255551L, 4398046511103L, 8796093022207L, 17592186044415L, 35184372088831L, 70368744177663L, 140737488355327L, 281474976710655L, 562949953421311L, 1125899906842623L, 2251799813685247L, 4503599627370495L, 9007199254740991L, 18014398509481983L, 36028797018963967L, 72057594037927935L, 144115188075855871L, 288230376151711743L, 576460752303423487L, 1152921504606846975L, 2305843009213693951L, 4611686018427387903L, Long.MAX_VALUE, -1};
    private static final int[] mIBY64 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};

    public GF2nONBElement(GF2nONBField gF2nONBField, SecureRandom secureRandom) {
        this.mField = gF2nONBField;
        this.mDegree = gF2nONBField.getDegree();
        this.mLength = gF2nONBField.getONBLength();
        this.mBit = gF2nONBField.getONBBit();
        int i2 = this.mLength;
        long[] jArr = new long[i2];
        this.mPol = jArr;
        if (i2 <= 1) {
            jArr[0] = secureRandom.nextLong();
            long[] jArr2 = this.mPol;
            jArr2[0] = jArr2[0] >>> (64 - this.mBit);
        } else {
            for (int i3 = 0; i3 < this.mLength - 1; i3++) {
                this.mPol[i3] = secureRandom.nextLong();
            }
            this.mPol[this.mLength - 1] = secureRandom.nextLong() >>> (64 - this.mBit);
        }
    }

    public static GF2nONBElement ONE(GF2nONBField gF2nONBField) {
        int oNBLength = gF2nONBField.getONBLength();
        long[] jArr = new long[oNBLength];
        int i2 = 0;
        while (true) {
            int i3 = oNBLength - 1;
            if (i2 >= i3) {
                jArr[i3] = mMaxmask[gF2nONBField.getONBBit() - 1];
                return new GF2nONBElement(gF2nONBField, jArr);
            }
            jArr[i2] = -1;
            i2++;
        }
    }

    public static GF2nONBElement ZERO(GF2nONBField gF2nONBField) {
        return new GF2nONBElement(gF2nONBField, new long[gF2nONBField.getONBLength()]);
    }

    private void assign(BigInteger bigInteger) {
        assign(bigInteger.toByteArray());
    }

    private long[] getElement() {
        long[] jArr = this.mPol;
        long[] jArr2 = new long[jArr.length];
        System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
        return jArr2;
    }

    private long[] getElementReverseOrder() {
        long[] jArr = new long[this.mPol.length];
        int i2 = 0;
        while (true) {
            if (i2 >= this.mDegree) {
                return jArr;
            }
            if (testBit((r2 - i2) - 1)) {
                int i3 = i2 >>> 6;
                jArr[i3] = jArr[i3] | mBitmask[i2 & 63];
            }
            i2++;
        }
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GFElement
    public GFElement add(GFElement gFElement) {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.addToThis(gFElement);
        return gF2nONBElement;
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GFElement
    public void addToThis(GFElement gFElement) {
        if (!(gFElement instanceof GF2nONBElement)) {
            throw new RuntimeException();
        }
        GF2nONBElement gF2nONBElement = (GF2nONBElement) gFElement;
        if (!this.mField.equals(gF2nONBElement.mField)) {
            throw new RuntimeException();
        }
        for (int i2 = 0; i2 < this.mLength; i2++) {
            long[] jArr = this.mPol;
            jArr[i2] = jArr[i2] ^ gF2nONBElement.mPol[i2];
        }
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GF2nElement
    public void assignOne() {
        int i2 = 0;
        while (true) {
            int i3 = this.mLength;
            if (i2 >= i3 - 1) {
                this.mPol[i3 - 1] = mMaxmask[this.mBit - 1];
                return;
            } else {
                this.mPol[i2] = -1;
                i2++;
            }
        }
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GF2nElement
    public void assignZero() {
        this.mPol = new long[this.mLength];
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GF2nElement, org.spongycastle.pqc.math.linearalgebra.GFElement
    public Object clone() {
        return new GF2nONBElement(this);
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GFElement
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GF2nONBElement)) {
            return false;
        }
        GF2nONBElement gF2nONBElement = (GF2nONBElement) obj;
        for (int i2 = 0; i2 < this.mLength; i2++) {
            if (this.mPol[i2] != gF2nONBElement.mPol[i2]) {
                return false;
            }
        }
        return true;
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GFElement
    public int hashCode() {
        return this.mPol.hashCode();
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GF2nElement
    public GF2nElement increase() {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.increaseThis();
        return gF2nONBElement;
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GF2nElement
    public void increaseThis() {
        addToThis(ONE((GF2nONBField) this.mField));
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GFElement
    public GFElement invert() {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.invertThis();
        return gF2nONBElement;
    }

    public void invertThis() {
        if (isZero()) {
            throw new ArithmeticException();
        }
        int i2 = 31;
        boolean z2 = false;
        while (!z2 && i2 >= 0) {
            if ((((long) (this.mDegree - 1)) & mBitmask[i2]) != 0) {
                z2 = true;
            }
            i2--;
        }
        ZERO((GF2nONBField) this.mField);
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        int i3 = 1;
        while (i2 >= 0) {
            GF2nElement gF2nElement = (GF2nElement) gF2nONBElement.clone();
            for (int i4 = 1; i4 <= i3; i4++) {
                gF2nElement.squareThis();
            }
            gF2nONBElement.multiplyThisBy(gF2nElement);
            i3 <<= 1;
            if ((((long) (this.mDegree - 1)) & mBitmask[i2]) != 0) {
                gF2nONBElement.squareThis();
                gF2nONBElement.multiplyThisBy(this);
                i3++;
            }
            i2--;
        }
        gF2nONBElement.squareThis();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0038  */
    @Override // org.spongycastle.pqc.math.linearalgebra.GFElement
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isOne() {
        /*
            r8 = this;
            r0 = 1
            r1 = 0
            r2 = 0
            r3 = 1
        L4:
            int r4 = r8.mLength
            int r5 = r4 + (-1)
            if (r2 >= r5) goto L1e
            if (r3 == 0) goto L1e
            if (r3 == 0) goto L1a
            long[] r3 = r8.mPol
            r4 = r3[r2]
            r6 = -1
            int r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r3 != 0) goto L1a
            r3 = 1
            goto L1b
        L1a:
            r3 = 0
        L1b:
            int r2 = r2 + 1
            goto L4
        L1e:
            if (r3 == 0) goto L3a
            if (r3 == 0) goto L38
            long[] r2 = r8.mPol
            int r4 = r4 - r0
            r3 = r2[r4]
            long[] r2 = org.spongycastle.pqc.math.linearalgebra.GF2nONBElement.mMaxmask
            int r5 = r8.mBit
            int r6 = r5 + (-1)
            r6 = r2[r6]
            long r3 = r3 & r6
            int r5 = r5 - r0
            r5 = r2[r5]
            int r2 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r2 != 0) goto L38
            goto L39
        L38:
            r0 = 0
        L39:
            r3 = r0
        L3a:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.pqc.math.linearalgebra.GF2nONBElement.isOne():boolean");
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GFElement
    public boolean isZero() {
        boolean z2 = true;
        for (int i2 = 0; i2 < this.mLength && z2; i2++) {
            z2 = z2 && this.mPol[i2] == 0;
        }
        return z2;
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GFElement
    public GFElement multiply(GFElement gFElement) {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.multiplyThisBy(gFElement);
        return gF2nONBElement;
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GFElement
    public void multiplyThisBy(GFElement gFElement) {
        char c2;
        boolean z2;
        char c3;
        boolean z3;
        if (!(gFElement instanceof GF2nONBElement)) {
            throw new RuntimeException("The elements have different representation: not yet implemented");
        }
        GF2nONBElement gF2nONBElement = (GF2nONBElement) gFElement;
        if (!this.mField.equals(gF2nONBElement.mField)) {
            throw new RuntimeException();
        }
        if (equals(gFElement)) {
            squareThis();
            return;
        }
        long[] jArr = this.mPol;
        long[] jArr2 = gF2nONBElement.mPol;
        int i2 = this.mLength;
        long[] jArr3 = new long[i2];
        int[][] iArr = ((GF2nONBField) this.mField).mMult;
        int i3 = i2 - 1;
        int i4 = this.mBit - 1;
        long[] jArr4 = mBitmask;
        long j2 = jArr4[63];
        long j3 = jArr4[i4];
        char c4 = 0;
        for (int i5 = 0; i5 < this.mDegree; i5++) {
            int i6 = 0;
            boolean z4 = false;
            while (i6 < this.mDegree) {
                int[] iArr2 = mIBY64;
                int i7 = iArr2[i6];
                int[] iArr3 = iArr[i6];
                int i8 = iArr3[c4];
                int i9 = iArr2[i8];
                int i10 = i8 & 63;
                long j4 = jArr[i7];
                long[] jArr5 = mBitmask;
                if ((j4 & jArr5[i6 & 63]) != 0) {
                    if ((jArr2[i9] & jArr5[i10]) != 0) {
                        z4 = !z4;
                    }
                    int i11 = iArr3[1];
                    if (i11 != -1) {
                        if ((jArr2[iArr2[i11]] & jArr5[i11 & 63]) != 0) {
                            z4 = !z4;
                        }
                    }
                }
                i6++;
                c4 = 0;
            }
            int i12 = mIBY64[i5];
            int i13 = i5 & 63;
            if (z4) {
                jArr3[i12] = jArr3[i12] ^ mBitmask[i13];
            }
            if (this.mLength > 1) {
                boolean z5 = (jArr[i3] & 1) == 1;
                int i14 = i2 - 2;
                int i15 = i14;
                while (i15 >= 0) {
                    long j5 = jArr[i15];
                    boolean z6 = (j5 & 1) != 0;
                    long j6 = j5 >>> 1;
                    jArr[i15] = j6;
                    if (z5) {
                        jArr[i15] = j6 ^ j2;
                    }
                    i15--;
                    z5 = z6;
                }
                long j7 = jArr[i3] >>> 1;
                jArr[i3] = j7;
                if (z5) {
                    jArr[i3] = j7 ^ j3;
                }
                boolean z7 = (jArr2[i3] & 1) == 1;
                while (i14 >= 0) {
                    long j8 = jArr2[i14];
                    boolean z8 = (j8 & 1) != 0;
                    long j9 = j8 >>> 1;
                    jArr2[i14] = j9;
                    if (z7) {
                        jArr2[i14] = j9 ^ j2;
                    }
                    i14--;
                    z7 = z8;
                }
                long j10 = jArr2[i3] >>> 1;
                jArr2[i3] = j10;
                if (z7) {
                    jArr2[i3] = j10 ^ j3;
                }
                c4 = 0;
            } else {
                c4 = 0;
                long j11 = jArr[0];
                if ((j11 & 1) == 1) {
                    c2 = 1;
                    z2 = true;
                } else {
                    c2 = 1;
                    z2 = false;
                }
                long j12 = j11 >>> c2;
                jArr[0] = j12;
                if (z2) {
                    jArr[0] = j12 ^ j3;
                }
                long j13 = jArr2[0];
                if ((j13 & 1) == 1) {
                    c3 = 1;
                    z3 = true;
                } else {
                    c3 = 1;
                    z3 = false;
                }
                long j14 = j13 >>> c3;
                jArr2[0] = j14;
                if (z3) {
                    jArr2[0] = j14 ^ j3;
                }
            }
        }
        assign(jArr3);
    }

    public void reverseOrder() {
        this.mPol = getElementReverseOrder();
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GF2nElement
    public GF2nElement solveQuadraticEquation() {
        int i2;
        if (trace() == 1) {
            throw new RuntimeException();
        }
        long j2 = mBitmask[63];
        long[] jArr = new long[this.mLength];
        int i3 = 0;
        long j3 = 0;
        while (true) {
            i2 = this.mLength;
            if (i3 >= i2 - 1) {
                break;
            }
            for (int i4 = 1; i4 < 64; i4++) {
                long[] jArr2 = mBitmask;
                long j4 = jArr2[i4];
                long j5 = this.mPol[i3];
                if (((j4 & j5) == 0 || (j3 & jArr2[i4 - 1]) == 0) && ((j5 & j4) != 0 || (jArr2[i4 - 1] & j3) != 0)) {
                    j3 ^= j4;
                }
            }
            jArr[i3] = j3;
            long j6 = j3 & j2;
            j3 = ((j6 == 0 || (this.mPol[i3 + 1] & 1) != 1) && !(j6 == 0 && (1 & this.mPol[i3 + 1]) == 0)) ? 1L : 0L;
            i3++;
        }
        int i5 = 63 & this.mDegree;
        long j7 = this.mPol[i2 - 1];
        for (int i6 = 1; i6 < i5; i6++) {
            long[] jArr3 = mBitmask;
            long j8 = jArr3[i6];
            if (((j8 & j7) == 0 || (jArr3[i6 - 1] & j3) == 0) && ((j8 & j7) != 0 || (jArr3[i6 - 1] & j3) != 0)) {
                j3 ^= j8;
            }
        }
        jArr[this.mLength - 1] = j3;
        return new GF2nONBElement((GF2nONBField) this.mField, jArr);
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GF2nElement
    public GF2nElement square() {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.squareThis();
        return gF2nONBElement;
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GF2nElement
    public GF2nElement squareRoot() {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.squareRootThis();
        return gF2nONBElement;
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GF2nElement
    public void squareRootThis() {
        long[] element = getElement();
        int i2 = this.mLength - 1;
        int i3 = this.mBit - 1;
        long j2 = mBitmask[63];
        boolean z2 = (element[0] & 1) != 0;
        int i4 = i2;
        while (i4 >= 0) {
            long j3 = element[i4];
            boolean z3 = (j3 & 1) != 0;
            long j4 = j3 >>> 1;
            element[i4] = j4;
            if (z2) {
                if (i4 == i2) {
                    element[i4] = j4 ^ mBitmask[i3];
                } else {
                    element[i4] = j4 ^ j2;
                }
            }
            i4--;
            z2 = z3;
        }
        assign(element);
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GF2nElement
    public void squareThis() {
        long[] element = getElement();
        int i2 = this.mLength - 1;
        int i3 = this.mBit;
        int i4 = i3 - 1;
        long[] jArr = mBitmask;
        long j2 = jArr[63];
        boolean z2 = (element[i2] & jArr[i4]) != 0;
        int i5 = 0;
        while (i5 < i2) {
            long j3 = element[i5];
            boolean z3 = (j3 & j2) != 0;
            long j4 = j3 << 1;
            element[i5] = j4;
            if (z2) {
                element[i5] = 1 ^ j4;
            }
            i5++;
            z2 = z3;
        }
        long j5 = element[i2];
        long[] jArr2 = mBitmask;
        boolean z4 = (jArr2[i4] & j5) != 0;
        long j6 = j5 << 1;
        element[i2] = j6;
        if (z2) {
            element[i2] = j6 ^ 1;
        }
        if (z4) {
            element[i2] = jArr2[i3] ^ element[i2];
        }
        assign(element);
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GF2nElement
    public boolean testBit(int i2) {
        return i2 >= 0 && i2 <= this.mDegree && (this.mPol[i2 >>> 6] & mBitmask[i2 & 63]) != 0;
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GF2nElement
    public boolean testRightmostBit() {
        return (this.mPol[this.mLength - 1] & mBitmask[this.mBit - 1]) != 0;
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GFElement
    public byte[] toByteArray() {
        int i2 = ((this.mDegree - 1) >> 3) + 1;
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = (i3 & 7) << 3;
            bArr[(i2 - i3) - 1] = (byte) ((this.mPol[i3 >>> 3] & (255 << i4)) >>> i4);
        }
        return bArr;
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GFElement
    public BigInteger toFlexiBigInt() {
        return new BigInteger(1, toByteArray());
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GFElement
    public String toString() {
        return toString(16);
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GF2nElement
    public int trace() {
        int i2 = this.mLength - 1;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            for (int i5 = 0; i5 < 64; i5++) {
                if ((this.mPol[i4] & mBitmask[i5]) != 0) {
                    i3 ^= 1;
                }
            }
        }
        int i6 = this.mBit;
        for (int i7 = 0; i7 < i6; i7++) {
            if ((this.mPol[i2] & mBitmask[i7]) != 0) {
                i3 ^= 1;
            }
        }
        return i3;
    }

    private void assign(long[] jArr) {
        System.arraycopy(jArr, 0, this.mPol, 0, this.mLength);
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GFElement
    public String toString(int i2) {
        long[] element = getElement();
        int i3 = this.mBit;
        String str = "";
        if (i2 == 2) {
            while (true) {
                i3--;
                if (i3 < 0) {
                    break;
                }
                str = (element[element.length - 1] & (1 << i3)) == 0 ? str + "0" : str + "1";
            }
            for (int length = element.length - 2; length >= 0; length--) {
                for (int i4 = 63; i4 >= 0; i4--) {
                    str = (element[length] & mBitmask[i4]) == 0 ? str + "0" : str + "1";
                }
            }
        } else if (i2 == 16) {
            char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            for (int length2 = element.length - 1; length2 >= 0; length2--) {
                str = ((((((((((((((((str + cArr[((int) (element[length2] >>> 60)) & 15]) + cArr[((int) (element[length2] >>> 56)) & 15]) + cArr[((int) (element[length2] >>> 52)) & 15]) + cArr[((int) (element[length2] >>> 48)) & 15]) + cArr[((int) (element[length2] >>> 44)) & 15]) + cArr[((int) (element[length2] >>> 40)) & 15]) + cArr[((int) (element[length2] >>> 36)) & 15]) + cArr[((int) (element[length2] >>> 32)) & 15]) + cArr[((int) (element[length2] >>> 28)) & 15]) + cArr[((int) (element[length2] >>> 24)) & 15]) + cArr[((int) (element[length2] >>> 20)) & 15]) + cArr[((int) (element[length2] >>> 16)) & 15]) + cArr[((int) (element[length2] >>> 12)) & 15]) + cArr[((int) (element[length2] >>> 8)) & 15]) + cArr[((int) (element[length2] >>> 4)) & 15]) + cArr[((int) element[length2]) & 15]) + " ";
            }
        }
        return str;
    }

    private void assign(byte[] bArr) {
        this.mPol = new long[this.mLength];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            long[] jArr = this.mPol;
            int i3 = i2 >>> 3;
            jArr[i3] = jArr[i3] | ((((long) bArr[(bArr.length - 1) - i2]) & 255) << ((i2 & 7) << 3));
        }
    }

    public GF2nONBElement(GF2nONBField gF2nONBField, byte[] bArr) {
        this.mField = gF2nONBField;
        this.mDegree = gF2nONBField.getDegree();
        this.mLength = gF2nONBField.getONBLength();
        this.mBit = gF2nONBField.getONBBit();
        this.mPol = new long[this.mLength];
        assign(bArr);
    }

    public GF2nONBElement(GF2nONBField gF2nONBField, BigInteger bigInteger) {
        this.mField = gF2nONBField;
        this.mDegree = gF2nONBField.getDegree();
        this.mLength = gF2nONBField.getONBLength();
        this.mBit = gF2nONBField.getONBBit();
        this.mPol = new long[this.mLength];
        assign(bigInteger);
    }

    private GF2nONBElement(GF2nONBField gF2nONBField, long[] jArr) {
        this.mField = gF2nONBField;
        this.mDegree = gF2nONBField.getDegree();
        this.mLength = gF2nONBField.getONBLength();
        this.mBit = gF2nONBField.getONBBit();
        this.mPol = jArr;
    }

    public GF2nONBElement(GF2nONBElement gF2nONBElement) {
        GF2nField gF2nField = gF2nONBElement.mField;
        this.mField = gF2nField;
        this.mDegree = gF2nField.getDegree();
        this.mLength = ((GF2nONBField) this.mField).getONBLength();
        this.mBit = ((GF2nONBField) this.mField).getONBBit();
        this.mPol = new long[this.mLength];
        assign(gF2nONBElement.getElement());
    }
}

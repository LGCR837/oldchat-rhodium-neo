package org.spongycastle.math.ec;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class WNafUtil {
    private static final int[] DEFAULT_WINDOW_SIZE_CUTOFFS = {13, 41, 121, 337, 897, 2305};
    private static final byte[] EMPTY_BYTES = new byte[0];
    private static final int[] EMPTY_INTS = new int[0];
    private static final ECPoint[] EMPTY_POINTS = new ECPoint[0];
    public static final String PRECOMP_NAME = "bc_wnaf";

    public static int[] generateCompactNaf(BigInteger bigInteger) {
        if ((bigInteger.bitLength() >>> 16) != 0) {
            throw new IllegalArgumentException("'k' must have bitlength < 2^16");
        }
        if (bigInteger.signum() == 0) {
            return EMPTY_INTS;
        }
        BigInteger bigIntegerAdd = bigInteger.shiftLeft(1).add(bigInteger);
        int iBitLength = bigIntegerAdd.bitLength();
        int i2 = iBitLength >> 1;
        int[] iArr = new int[i2];
        BigInteger bigIntegerXor = bigIntegerAdd.xor(bigInteger);
        int i3 = iBitLength - 1;
        int i4 = 0;
        int i5 = 0;
        int i6 = 1;
        while (i6 < i3) {
            if (bigIntegerXor.testBit(i6)) {
                iArr[i4] = i5 | ((bigInteger.testBit(i6) ? -1 : 1) << 16);
                i6++;
                i4++;
                i5 = 1;
            } else {
                i5++;
            }
            i6++;
        }
        int i7 = i4 + 1;
        iArr[i4] = 65536 | i5;
        return i2 > i7 ? trim(iArr, i7) : iArr;
    }

    public static int[] generateCompactWindowNaf(int i2, BigInteger bigInteger) {
        if (i2 == 2) {
            return generateCompactNaf(bigInteger);
        }
        if (i2 < 2 || i2 > 16) {
            throw new IllegalArgumentException("'width' must be in the range [2, 16]");
        }
        if ((bigInteger.bitLength() >>> 16) != 0) {
            throw new IllegalArgumentException("'k' must have bitlength < 2^16");
        }
        if (bigInteger.signum() == 0) {
            return EMPTY_INTS;
        }
        int iBitLength = (bigInteger.bitLength() / i2) + 1;
        int[] iArr = new int[iBitLength];
        int i3 = 1 << i2;
        int i4 = i3 - 1;
        int i5 = i3 >>> 1;
        int i6 = 0;
        int i7 = 0;
        boolean z2 = false;
        while (i6 <= bigInteger.bitLength()) {
            if (bigInteger.testBit(i6) == z2) {
                i6++;
            } else {
                bigInteger = bigInteger.shiftRight(i6);
                int iIntValue = bigInteger.intValue() & i4;
                if (z2) {
                    iIntValue++;
                }
                z2 = (iIntValue & i5) != 0;
                if (z2) {
                    iIntValue -= i3;
                }
                if (i7 > 0) {
                    i6--;
                }
                iArr[i7] = i6 | (iIntValue << 16);
                i6 = i2;
                i7++;
            }
        }
        return iBitLength > i7 ? trim(iArr, i7) : iArr;
    }

    public static byte[] generateJSF(BigInteger bigInteger, BigInteger bigInteger2) {
        int iMax = Math.max(bigInteger.bitLength(), bigInteger2.bitLength()) + 1;
        byte[] bArr = new byte[iMax];
        BigInteger bigIntegerShiftRight = bigInteger;
        BigInteger bigIntegerShiftRight2 = bigInteger2;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if ((i2 | i3) == 0 && bigIntegerShiftRight.bitLength() <= i4 && bigIntegerShiftRight2.bitLength() <= i4) {
                break;
            }
            int iIntValue = (bigIntegerShiftRight.intValue() >>> i4) + i2;
            int i6 = iIntValue & 7;
            int iIntValue2 = (bigIntegerShiftRight2.intValue() >>> i4) + i3;
            int i7 = iIntValue2 & 7;
            int i8 = iIntValue & 1;
            if (i8 != 0) {
                i8 -= iIntValue & 2;
                if (i6 + i8 == 4 && (iIntValue2 & 3) == 2) {
                    i8 = -i8;
                }
            }
            int i9 = iIntValue2 & 1;
            if (i9 != 0) {
                i9 -= iIntValue2 & 2;
                if (i7 + i9 == 4 && (iIntValue & 3) == 2) {
                    i9 = -i9;
                }
            }
            if ((i2 << 1) == i8 + 1) {
                i2 ^= 1;
            }
            if ((i3 << 1) == i9 + 1) {
                i3 ^= 1;
            }
            i4++;
            if (i4 == 30) {
                bigIntegerShiftRight = bigIntegerShiftRight.shiftRight(30);
                bigIntegerShiftRight2 = bigIntegerShiftRight2.shiftRight(30);
                i4 = 0;
            }
            bArr[i5] = (byte) ((i9 & 15) | (i8 << 4));
            i5++;
        }
        return iMax > i5 ? trim(bArr, i5) : bArr;
    }

    public static byte[] generateNaf(BigInteger bigInteger) {
        if (bigInteger.signum() == 0) {
            return EMPTY_BYTES;
        }
        BigInteger bigIntegerAdd = bigInteger.shiftLeft(1).add(bigInteger);
        int iBitLength = bigIntegerAdd.bitLength();
        int i2 = iBitLength - 1;
        byte[] bArr = new byte[i2];
        BigInteger bigIntegerXor = bigIntegerAdd.xor(bigInteger);
        int i3 = 1;
        while (i3 < i2) {
            if (bigIntegerXor.testBit(i3)) {
                bArr[i3 - 1] = (byte) (bigInteger.testBit(i3) ? -1 : 1);
                i3++;
            }
            i3++;
        }
        bArr[iBitLength - 2] = 1;
        return bArr;
    }

    public static byte[] generateWindowNaf(int i2, BigInteger bigInteger) {
        if (i2 == 2) {
            return generateNaf(bigInteger);
        }
        if (i2 < 2 || i2 > 8) {
            throw new IllegalArgumentException("'width' must be in the range [2, 8]");
        }
        if (bigInteger.signum() == 0) {
            return EMPTY_BYTES;
        }
        int iBitLength = bigInteger.bitLength() + 1;
        byte[] bArr = new byte[iBitLength];
        int i3 = 1 << i2;
        int i4 = i3 - 1;
        int i5 = i3 >>> 1;
        int i6 = 0;
        int i7 = 0;
        boolean z2 = false;
        while (i6 <= bigInteger.bitLength()) {
            if (bigInteger.testBit(i6) == z2) {
                i6++;
            } else {
                bigInteger = bigInteger.shiftRight(i6);
                int iIntValue = bigInteger.intValue() & i4;
                if (z2) {
                    iIntValue++;
                }
                z2 = (iIntValue & i5) != 0;
                if (z2) {
                    iIntValue -= i3;
                }
                if (i7 > 0) {
                    i6--;
                }
                int i8 = i7 + i6;
                bArr[i8] = (byte) iIntValue;
                i7 = i8 + 1;
                i6 = i2;
            }
        }
        return iBitLength > i7 ? trim(bArr, i7) : bArr;
    }

    public static int getNafWeight(BigInteger bigInteger) {
        if (bigInteger.signum() == 0) {
            return 0;
        }
        return bigInteger.shiftLeft(1).add(bigInteger).xor(bigInteger).bitCount();
    }

    public static WNafPreCompInfo getWNafPreCompInfo(ECPoint eCPoint) {
        return getWNafPreCompInfo(eCPoint.getCurve().getPreCompInfo(eCPoint, PRECOMP_NAME));
    }

    public static int getWindowSize(int i2) {
        return getWindowSize(i2, DEFAULT_WINDOW_SIZE_CUTOFFS);
    }

    public static ECPoint mapPointWithPrecomp(ECPoint eCPoint, int i2, boolean z2, ECPointMap eCPointMap) {
        ECCurve curve = eCPoint.getCurve();
        WNafPreCompInfo wNafPreCompInfoPrecompute = precompute(eCPoint, i2, z2);
        ECPoint map = eCPointMap.map(eCPoint);
        WNafPreCompInfo wNafPreCompInfo = getWNafPreCompInfo(curve.getPreCompInfo(map, PRECOMP_NAME));
        ECPoint twice = wNafPreCompInfoPrecompute.getTwice();
        if (twice != null) {
            wNafPreCompInfo.setTwice(eCPointMap.map(twice));
        }
        ECPoint[] preComp = wNafPreCompInfoPrecompute.getPreComp();
        int length = preComp.length;
        ECPoint[] eCPointArr = new ECPoint[length];
        for (int i3 = 0; i3 < preComp.length; i3++) {
            eCPointArr[i3] = eCPointMap.map(preComp[i3]);
        }
        wNafPreCompInfo.setPreComp(eCPointArr);
        if (z2) {
            ECPoint[] eCPointArr2 = new ECPoint[length];
            for (int i4 = 0; i4 < length; i4++) {
                eCPointArr2[i4] = eCPointArr[i4].negate();
            }
            wNafPreCompInfo.setPreCompNeg(eCPointArr2);
        }
        curve.setPreCompInfo(map, PRECOMP_NAME, wNafPreCompInfo);
        return map;
    }

    public static WNafPreCompInfo precompute(ECPoint eCPoint, int i2, boolean z2) {
        int length;
        int i3;
        int coordinateSystem;
        ECCurve curve = eCPoint.getCurve();
        WNafPreCompInfo wNafPreCompInfo = getWNafPreCompInfo(curve.getPreCompInfo(eCPoint, PRECOMP_NAME));
        int length2 = 0;
        int iMax = 1 << Math.max(0, i2 - 2);
        ECPoint[] preComp = wNafPreCompInfo.getPreComp();
        if (preComp == null) {
            preComp = EMPTY_POINTS;
            length = 0;
        } else {
            length = preComp.length;
        }
        if (length < iMax) {
            preComp = resizeTable(preComp, iMax);
            if (iMax == 1) {
                preComp[0] = eCPoint.normalize();
            } else {
                if (length == 0) {
                    preComp[0] = eCPoint;
                    i3 = 1;
                } else {
                    i3 = length;
                }
                ECFieldElement zCoord = null;
                if (iMax == 2) {
                    preComp[1] = eCPoint.threeTimes();
                } else {
                    ECPoint twice = wNafPreCompInfo.getTwice();
                    ECPoint eCPointAdd = preComp[i3 - 1];
                    if (twice == null) {
                        twice = preComp[0].twice();
                        wNafPreCompInfo.setTwice(twice);
                        if (!twice.isInfinity() && ECAlgorithms.isFpCurve(curve) && curve.getFieldSize() >= 64 && ((coordinateSystem = curve.getCoordinateSystem()) == 2 || coordinateSystem == 3 || coordinateSystem == 4)) {
                            zCoord = twice.getZCoord(0);
                            twice = curve.createPoint(twice.getXCoord().toBigInteger(), twice.getYCoord().toBigInteger());
                            ECFieldElement eCFieldElementSquare = zCoord.square();
                            eCPointAdd = eCPointAdd.scaleX(eCFieldElementSquare).scaleY(eCFieldElementSquare.multiply(zCoord));
                            if (length == 0) {
                                preComp[0] = eCPointAdd;
                            }
                        }
                    }
                    while (i3 < iMax) {
                        eCPointAdd = eCPointAdd.add(twice);
                        preComp[i3] = eCPointAdd;
                        i3++;
                    }
                }
                curve.normalizeAll(preComp, length, iMax - length, zCoord);
            }
        }
        wNafPreCompInfo.setPreComp(preComp);
        if (z2) {
            ECPoint[] preCompNeg = wNafPreCompInfo.getPreCompNeg();
            if (preCompNeg == null) {
                preCompNeg = new ECPoint[iMax];
            } else {
                length2 = preCompNeg.length;
                if (length2 < iMax) {
                    preCompNeg = resizeTable(preCompNeg, iMax);
                }
            }
            while (length2 < iMax) {
                preCompNeg[length2] = preComp[length2].negate();
                length2++;
            }
            wNafPreCompInfo.setPreCompNeg(preCompNeg);
        }
        curve.setPreCompInfo(eCPoint, PRECOMP_NAME, wNafPreCompInfo);
        return wNafPreCompInfo;
    }

    private static ECPoint[] resizeTable(ECPoint[] eCPointArr, int i2) {
        ECPoint[] eCPointArr2 = new ECPoint[i2];
        System.arraycopy(eCPointArr, 0, eCPointArr2, 0, eCPointArr.length);
        return eCPointArr2;
    }

    private static byte[] trim(byte[] bArr, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        return bArr2;
    }

    public static WNafPreCompInfo getWNafPreCompInfo(PreCompInfo preCompInfo) {
        return (preCompInfo == null || !(preCompInfo instanceof WNafPreCompInfo)) ? new WNafPreCompInfo() : (WNafPreCompInfo) preCompInfo;
    }

    public static int getWindowSize(int i2, int[] iArr) {
        int i3 = 0;
        while (i3 < iArr.length && i2 >= iArr[i3]) {
            i3++;
        }
        return i3 + 2;
    }

    private static int[] trim(int[] iArr, int i2) {
        int[] iArr2 = new int[i2];
        System.arraycopy(iArr, 0, iArr2, 0, i2);
        return iArr2;
    }
}

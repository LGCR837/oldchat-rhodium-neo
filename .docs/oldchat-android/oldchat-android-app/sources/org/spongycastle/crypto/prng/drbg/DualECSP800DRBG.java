package org.spongycastle.crypto.prng.drbg;

import java.math.BigInteger;
import org.spongycastle.asn1.nist.NISTNamedCurves;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.prng.EntropySource;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECMultiplier;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;

/* JADX INFO: loaded from: classes.dex */
public class DualECSP800DRBG implements SP80090DRBG {
    private static final int MAX_ADDITIONAL_INPUT = 4096;
    private static final int MAX_ENTROPY_LENGTH = 4096;
    private static final int MAX_PERSONALIZATION_STRING = 4096;
    private static final long RESEED_MAX = 2147483648L;
    private static final DualECPoints[] nistPoints;
    private static final BigInteger p256_Px;
    private static final BigInteger p256_Py;
    private static final BigInteger p256_Qx;
    private static final BigInteger p256_Qy;
    private static final BigInteger p384_Px;
    private static final BigInteger p384_Py;
    private static final BigInteger p384_Qx;
    private static final BigInteger p384_Qy;
    private static final BigInteger p521_Px;
    private static final BigInteger p521_Py;
    private static final BigInteger p521_Qx;
    private static final BigInteger p521_Qy;
    private ECPoint _P;
    private ECPoint _Q;
    private ECCurve.Fp _curve;
    private Digest _digest;
    private EntropySource _entropySource;
    private ECMultiplier _fixedPointMultiplier;
    private int _outlen;
    private long _reseedCounter;
    private byte[] _s;
    private int _sLength;
    private int _securityStrength;
    private int _seedlen;

    static {
        BigInteger bigInteger = new BigInteger("6b17d1f2e12c4247f8bce6e563a440f277037d812deb33a0f4a13945d898c296", 16);
        p256_Px = bigInteger;
        BigInteger bigInteger2 = new BigInteger("4fe342e2fe1a7f9b8ee7eb4a7c0f9e162bce33576b315ececbb6406837bf51f5", 16);
        p256_Py = bigInteger2;
        BigInteger bigInteger3 = new BigInteger("c97445f45cdef9f0d3e05e1e585fc297235b82b5be8ff3efca67c59852018192", 16);
        p256_Qx = bigInteger3;
        BigInteger bigInteger4 = new BigInteger("b28ef557ba31dfcbdd21ac46e2a91e3c304f44cb87058ada2cb815151e610046", 16);
        p256_Qy = bigInteger4;
        BigInteger bigInteger5 = new BigInteger("aa87ca22be8b05378eb1c71ef320ad746e1d3b628ba79b9859f741e082542a385502f25dbf55296c3a545e3872760ab7", 16);
        p384_Px = bigInteger5;
        BigInteger bigInteger6 = new BigInteger("3617de4a96262c6f5d9e98bf9292dc29f8f41dbd289a147ce9da3113b5f0b8c00a60b1ce1d7e819d7a431d7c90ea0e5f", 16);
        p384_Py = bigInteger6;
        BigInteger bigInteger7 = new BigInteger("8e722de3125bddb05580164bfe20b8b432216a62926c57502ceede31c47816edd1e89769124179d0b695106428815065", 16);
        p384_Qx = bigInteger7;
        BigInteger bigInteger8 = new BigInteger("023b1660dd701d0839fd45eec36f9ee7b32e13b315dc02610aa1b636e346df671f790f84c5e09b05674dbb7e45c803dd", 16);
        p384_Qy = bigInteger8;
        BigInteger bigInteger9 = new BigInteger("c6858e06b70404e9cd9e3ecb662395b4429c648139053fb521f828af606b4d3dbaa14b5e77efe75928fe1dc127a2ffa8de3348b3c1856a429bf97e7e31c2e5bd66", 16);
        p521_Px = bigInteger9;
        BigInteger bigInteger10 = new BigInteger("11839296a789a3bc0045c8a5fb42c7d1bd998f54449579b446817afbd17273e662c97ee72995ef42640c550b9013fad0761353c7086a272c24088be94769fd16650", 16);
        p521_Py = bigInteger10;
        BigInteger bigInteger11 = new BigInteger("1b9fa3e518d683c6b65763694ac8efbaec6fab44f2276171a42726507dd08add4c3b3f4c1ebc5b1222ddba077f722943b24c3edfa0f85fe24d0c8c01591f0be6f63", 16);
        p521_Qx = bigInteger11;
        BigInteger bigInteger12 = new BigInteger("1f3bdba585295d9a1110d1df1f9430ef8442c5018976ff3437ef91b81dc0b8132c8d5c39c32d0e004a3092b7d327c0e7a4d26d2c7b69b58f9066652911e457779de", 16);
        p521_Qy = bigInteger12;
        nistPoints = new DualECPoints[]{new DualECPoints(128, fp.createPoint(bigInteger, bigInteger2), fp.createPoint(bigInteger3, bigInteger4), 1), new DualECPoints(192, fp.createPoint(bigInteger5, bigInteger6), fp.createPoint(bigInteger7, bigInteger8), 1), new DualECPoints(256, fp.createPoint(bigInteger9, bigInteger10), fp.createPoint(bigInteger11, bigInteger12), 1)};
        ECCurve.Fp fp = (ECCurve.Fp) NISTNamedCurves.getByName("P-256").getCurve();
        ECCurve.Fp fp2 = (ECCurve.Fp) NISTNamedCurves.getByName("P-384").getCurve();
        ECCurve.Fp fp3 = (ECCurve.Fp) NISTNamedCurves.getByName("P-521").getCurve();
    }

    public DualECSP800DRBG(Digest digest, int i2, EntropySource entropySource, byte[] bArr, byte[] bArr2) {
        this(nistPoints, digest, i2, entropySource, bArr, bArr2);
    }

    private byte[] getEntropy() {
        byte[] entropy = this._entropySource.getEntropy();
        if (entropy.length >= (this._securityStrength + 7) / 8) {
            return entropy;
        }
        throw new IllegalStateException("Insufficient entropy provided by entropy source");
    }

    private BigInteger getScalarMultipleXCoord(ECPoint eCPoint, BigInteger bigInteger) {
        return this._fixedPointMultiplier.multiply(eCPoint, bigInteger).normalize().getAffineXCoord().toBigInteger();
    }

    private byte[] pad8(byte[] bArr, int i2) {
        int i3 = i2 % 8;
        if (i3 == 0) {
            return bArr;
        }
        int i4 = 8 - i3;
        int length = bArr.length - 1;
        int i5 = 0;
        while (length >= 0) {
            int i6 = bArr[length] & 255;
            bArr[length] = (byte) ((i5 >> (8 - i4)) | (i6 << i4));
            length--;
            i5 = i6;
        }
        return bArr;
    }

    private byte[] xor(byte[] bArr, byte[] bArr2) {
        if (bArr2 == null) {
            return bArr;
        }
        int length = bArr.length;
        byte[] bArr3 = new byte[length];
        for (int i2 = 0; i2 != length; i2++) {
            bArr3[i2] = (byte) (bArr[i2] ^ bArr2[i2]);
        }
        return bArr3;
    }

    @Override // org.spongycastle.crypto.prng.drbg.SP80090DRBG
    public int generate(byte[] bArr, byte[] bArr2, boolean z2) {
        int length = bArr.length * 8;
        int length2 = bArr.length / this._outlen;
        if (Utils.isTooLarge(bArr2, 512)) {
            throw new IllegalArgumentException("Additional input too large");
        }
        if (this._reseedCounter + ((long) length2) > RESEED_MAX) {
            return -1;
        }
        if (z2) {
            reseed(bArr2);
            bArr2 = null;
        }
        BigInteger bigInteger = bArr2 != null ? new BigInteger(1, xor(this._s, Utils.hash_df(this._digest, bArr2, this._seedlen))) : new BigInteger(1, this._s);
        Arrays.fill(bArr, (byte) 0);
        int i2 = 0;
        for (int i3 = 0; i3 < length2; i3++) {
            bigInteger = getScalarMultipleXCoord(this._P, bigInteger);
            byte[] byteArray = getScalarMultipleXCoord(this._Q, bigInteger).toByteArray();
            int length3 = byteArray.length;
            int i4 = this._outlen;
            if (length3 > i4) {
                System.arraycopy(byteArray, byteArray.length - i4, bArr, i2, i4);
            } else {
                System.arraycopy(byteArray, 0, bArr, (i4 - byteArray.length) + i2, byteArray.length);
            }
            i2 += this._outlen;
            this._reseedCounter++;
        }
        if (i2 < bArr.length) {
            bigInteger = getScalarMultipleXCoord(this._P, bigInteger);
            byte[] byteArray2 = getScalarMultipleXCoord(this._Q, bigInteger).toByteArray();
            int length4 = bArr.length - i2;
            int length5 = byteArray2.length;
            int i5 = this._outlen;
            if (length5 > i5) {
                System.arraycopy(byteArray2, byteArray2.length - i5, bArr, i2, length4);
            } else {
                System.arraycopy(byteArray2, 0, bArr, i2 + (i5 - byteArray2.length), length4);
            }
            this._reseedCounter++;
        }
        this._s = BigIntegers.asUnsignedByteArray(this._sLength, getScalarMultipleXCoord(this._P, bigInteger));
        return length;
    }

    @Override // org.spongycastle.crypto.prng.drbg.SP80090DRBG
    public int getBlockSize() {
        return this._outlen * 8;
    }

    @Override // org.spongycastle.crypto.prng.drbg.SP80090DRBG
    public void reseed(byte[] bArr) {
        if (Utils.isTooLarge(bArr, 512)) {
            throw new IllegalArgumentException("Additional input string too large");
        }
        this._s = Utils.hash_df(this._digest, Arrays.concatenate(pad8(this._s, this._seedlen), getEntropy(), bArr), this._seedlen);
        this._reseedCounter = 0L;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0076, code lost:
    
        if (r2._P == null) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0078, code lost:
    
        r3 = org.spongycastle.crypto.prng.drbg.Utils.hash_df(r2._digest, r6, r2._seedlen);
        r2._s = r3;
        r2._sLength = r3.length;
        r2._reseedCounter = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0089, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0091, code lost:
    
        throw new java.lang.IllegalArgumentException("security strength cannot be greater than 256 bits");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public DualECSP800DRBG(org.spongycastle.crypto.prng.drbg.DualECPoints[] r3, org.spongycastle.crypto.Digest r4, int r5, org.spongycastle.crypto.prng.EntropySource r6, byte[] r7, byte[] r8) {
        /*
            r2 = this;
            r2.<init>()
            org.spongycastle.math.ec.FixedPointCombMultiplier r0 = new org.spongycastle.math.ec.FixedPointCombMultiplier
            r0.<init>()
            r2._fixedPointMultiplier = r0
            r2._digest = r4
            r2._entropySource = r6
            r2._securityStrength = r5
            r0 = 512(0x200, float:7.17E-43)
            boolean r0 = org.spongycastle.crypto.prng.drbg.Utils.isTooLarge(r7, r0)
            if (r0 != 0) goto Lb6
            int r0 = r6.entropySize()
            r1 = 4096(0x1000, float:5.74E-42)
            if (r0 < r5) goto L92
            int r6 = r6.entropySize()
            if (r6 > r1) goto L92
            byte[] r6 = r2.getEntropy()
            byte[] r6 = org.spongycastle.util.Arrays.concatenate(r6, r8, r7)
            r7 = 0
        L2f:
            int r8 = r3.length
            if (r7 == r8) goto L74
            r8 = r3[r7]
            int r8 = r8.getSecurityStrength()
            if (r5 > r8) goto L71
            int r4 = org.spongycastle.crypto.prng.drbg.Utils.getMaxSecurityStrength(r4)
            r5 = r3[r7]
            int r5 = r5.getSecurityStrength()
            if (r4 < r5) goto L69
            r4 = r3[r7]
            int r4 = r4.getSeedLen()
            r2._seedlen = r4
            r4 = r3[r7]
            int r4 = r4.getMaxOutlen()
            int r4 = r4 / 8
            r2._outlen = r4
            r4 = r3[r7]
            org.spongycastle.math.ec.ECPoint r4 = r4.getP()
            r2._P = r4
            r3 = r3[r7]
            org.spongycastle.math.ec.ECPoint r3 = r3.getQ()
            r2._Q = r3
            goto L74
        L69:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "Requested security strength is not supported by digest"
            r3.<init>(r4)
            throw r3
        L71:
            int r7 = r7 + 1
            goto L2f
        L74:
            org.spongycastle.math.ec.ECPoint r3 = r2._P
            if (r3 == 0) goto L8a
            org.spongycastle.crypto.Digest r3 = r2._digest
            int r4 = r2._seedlen
            byte[] r3 = org.spongycastle.crypto.prng.drbg.Utils.hash_df(r3, r6, r4)
            r2._s = r3
            int r3 = r3.length
            r2._sLength = r3
            r3 = 0
            r2._reseedCounter = r3
            return
        L8a:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "security strength cannot be greater than 256 bits"
            r3.<init>(r4)
            throw r3
        L92:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "EntropySource must provide between "
            r4.append(r6)
            r4.append(r5)
            java.lang.String r5 = " and "
            r4.append(r5)
            r4.append(r1)
            java.lang.String r5 = " bits"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        Lb6:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "Personalization string too large"
            r3.<init>(r4)
            goto Lbf
        Lbe:
            throw r3
        Lbf:
            goto Lbe
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.prng.drbg.DualECSP800DRBG.<init>(org.spongycastle.crypto.prng.drbg.DualECPoints[], org.spongycastle.crypto.Digest, int, org.spongycastle.crypto.prng.EntropySource, byte[], byte[]):void");
    }
}

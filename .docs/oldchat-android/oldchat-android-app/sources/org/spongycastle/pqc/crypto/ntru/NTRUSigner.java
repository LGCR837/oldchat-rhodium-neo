package org.spongycastle.pqc.crypto.ntru;

import java.nio.ByteBuffer;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;

/* JADX INFO: loaded from: classes.dex */
public class NTRUSigner {
    private Digest hashAlg;
    private NTRUSigningParameters params;
    private NTRUSigningPrivateKeyParameters signingKeyPair;
    private NTRUSigningPublicKeyParameters verificationKey;

    public NTRUSigner(NTRUSigningParameters nTRUSigningParameters) {
        this.params = nTRUSigningParameters;
    }

    private IntegerPolynomial sign(IntegerPolynomial integerPolynomial, NTRUSigningPrivateKeyParameters nTRUSigningPrivateKeyParameters) {
        NTRUSigningParameters nTRUSigningParameters = this.params;
        int i2 = nTRUSigningParameters.f7668N;
        int i3 = nTRUSigningParameters.f7670q;
        NTRUSigningPublicKeyParameters publicKey = nTRUSigningPrivateKeyParameters.getPublicKey();
        IntegerPolynomial integerPolynomial2 = new IntegerPolynomial(i2);
        for (int i4 = nTRUSigningParameters.f7667B; i4 >= 1; i4--) {
            Polynomial polynomial = nTRUSigningPrivateKeyParameters.getBasis(i4).f7671f;
            Polynomial polynomial2 = nTRUSigningPrivateKeyParameters.getBasis(i4).fPrime;
            IntegerPolynomial integerPolynomialMult = polynomial.mult(integerPolynomial);
            integerPolynomialMult.div(i3);
            IntegerPolynomial integerPolynomialMult2 = polynomial2.mult(integerPolynomialMult);
            IntegerPolynomial integerPolynomialMult3 = polynomial2.mult(integerPolynomial);
            integerPolynomialMult3.div(i3);
            integerPolynomialMult2.sub(polynomial.mult(integerPolynomialMult3));
            integerPolynomial2.add(integerPolynomialMult2);
            IntegerPolynomial integerPolynomial3 = (IntegerPolynomial) nTRUSigningPrivateKeyParameters.getBasis(i4).f7672h.clone();
            if (i4 > 1) {
                integerPolynomial3.sub(nTRUSigningPrivateKeyParameters.getBasis(i4 - 1).f7672h);
            } else {
                integerPolynomial3.sub(publicKey.f7673h);
            }
            integerPolynomial = integerPolynomialMult2.mult(integerPolynomial3, i3);
        }
        Polynomial polynomial3 = nTRUSigningPrivateKeyParameters.getBasis(0).f7671f;
        Polynomial polynomial4 = nTRUSigningPrivateKeyParameters.getBasis(0).fPrime;
        IntegerPolynomial integerPolynomialMult4 = polynomial3.mult(integerPolynomial);
        integerPolynomialMult4.div(i3);
        IntegerPolynomial integerPolynomialMult5 = polynomial4.mult(integerPolynomialMult4);
        IntegerPolynomial integerPolynomialMult6 = polynomial4.mult(integerPolynomial);
        integerPolynomialMult6.div(i3);
        integerPolynomialMult5.sub(polynomial3.mult(integerPolynomialMult6));
        integerPolynomial2.add(integerPolynomialMult5);
        integerPolynomial2.modPositive(i3);
        return integerPolynomial2;
    }

    private byte[] signHash(byte[] bArr, NTRUSigningPrivateKeyParameters nTRUSigningPrivateKeyParameters) {
        IntegerPolynomial integerPolynomialCreateMsgRep;
        IntegerPolynomial integerPolynomialSign;
        NTRUSigningPublicKeyParameters publicKey = nTRUSigningPrivateKeyParameters.getPublicKey();
        int i2 = 0;
        do {
            i2++;
            if (i2 > this.params.signFailTolerance) {
                throw new IllegalStateException("Signing failed: too many retries (max=" + this.params.signFailTolerance + ")");
            }
            integerPolynomialCreateMsgRep = createMsgRep(bArr, i2);
            integerPolynomialSign = sign(integerPolynomialCreateMsgRep, nTRUSigningPrivateKeyParameters);
        } while (!verify(integerPolynomialCreateMsgRep, integerPolynomialSign, publicKey.f7673h));
        byte[] binary = integerPolynomialSign.toBinary(this.params.f7670q);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(binary.length + 4);
        byteBufferAllocate.put(binary);
        byteBufferAllocate.putInt(i2);
        return byteBufferAllocate.array();
    }

    private boolean verify(IntegerPolynomial integerPolynomial, IntegerPolynomial integerPolynomial2, IntegerPolynomial integerPolynomial3) {
        NTRUSigningParameters nTRUSigningParameters = this.params;
        int i2 = nTRUSigningParameters.f7670q;
        double d2 = nTRUSigningParameters.normBoundSq;
        double d3 = nTRUSigningParameters.betaSq;
        IntegerPolynomial integerPolynomialMult = integerPolynomial3.mult(integerPolynomial2, i2);
        integerPolynomialMult.sub(integerPolynomial);
        double dCenteredNormSq = integerPolynomial2.centeredNormSq(i2);
        double dCenteredNormSq2 = integerPolynomialMult.centeredNormSq(i2);
        Double.isNaN(dCenteredNormSq2);
        Double.isNaN(dCenteredNormSq);
        return ((double) ((long) (dCenteredNormSq + (d3 * dCenteredNormSq2)))) <= d2;
    }

    private boolean verifyHash(byte[] bArr, byte[] bArr2, NTRUSigningPublicKeyParameters nTRUSigningPublicKeyParameters) {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr2);
        byte[] bArr3 = new byte[bArr2.length - 4];
        byteBufferWrap.get(bArr3);
        NTRUSigningParameters nTRUSigningParameters = this.params;
        return verify(createMsgRep(bArr, byteBufferWrap.getInt()), IntegerPolynomial.fromBinary(bArr3, nTRUSigningParameters.f7668N, nTRUSigningParameters.f7670q), nTRUSigningPublicKeyParameters.f7673h);
    }

    public IntegerPolynomial createMsgRep(byte[] bArr, int i2) {
        NTRUSigningParameters nTRUSigningParameters = this.params;
        int i3 = nTRUSigningParameters.f7668N;
        int iNumberOfLeadingZeros = Integer.numberOfLeadingZeros(nTRUSigningParameters.f7670q);
        int i4 = 31 - iNumberOfLeadingZeros;
        int i5 = (38 - iNumberOfLeadingZeros) / 8;
        IntegerPolynomial integerPolynomial = new IntegerPolynomial(i3);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArr.length + 4);
        byteBufferAllocate.put(bArr);
        byteBufferAllocate.putInt(i2);
        NTRUSignerPrng nTRUSignerPrng = new NTRUSignerPrng(byteBufferAllocate.array(), this.params.hashAlg);
        for (int i6 = 0; i6 < i3; i6++) {
            byte[] bArrNextBytes = nTRUSignerPrng.nextBytes(i5);
            int i7 = (i5 * 8) - i4;
            bArrNextBytes[bArrNextBytes.length - 1] = (byte) ((bArrNextBytes[bArrNextBytes.length - 1] >> i7) << i7);
            ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(4);
            byteBufferAllocate2.put(bArrNextBytes);
            byteBufferAllocate2.rewind();
            integerPolynomial.coeffs[i6] = Integer.reverseBytes(byteBufferAllocate2.getInt());
        }
        return integerPolynomial;
    }

    public byte[] generateSignature() {
        Digest digest = this.hashAlg;
        if (digest == null || this.signingKeyPair == null) {
            throw new IllegalStateException("Call initSign first!");
        }
        byte[] bArr = new byte[digest.getDigestSize()];
        this.hashAlg.doFinal(bArr, 0);
        return signHash(bArr, this.signingKeyPair);
    }

    public void init(boolean z2, CipherParameters cipherParameters) {
        if (z2) {
            this.signingKeyPair = (NTRUSigningPrivateKeyParameters) cipherParameters;
        } else {
            this.verificationKey = (NTRUSigningPublicKeyParameters) cipherParameters;
        }
        Digest digest = this.params.hashAlg;
        this.hashAlg = digest;
        digest.reset();
    }

    public void update(byte b2) {
        Digest digest = this.hashAlg;
        if (digest == null) {
            throw new IllegalStateException("Call initSign or initVerify first!");
        }
        digest.update(b2);
    }

    public boolean verifySignature(byte[] bArr) {
        Digest digest = this.hashAlg;
        if (digest == null || this.verificationKey == null) {
            throw new IllegalStateException("Call initVerify first!");
        }
        byte[] bArr2 = new byte[digest.getDigestSize()];
        this.hashAlg.doFinal(bArr2, 0);
        return verifyHash(bArr2, bArr, this.verificationKey);
    }

    public void update(byte[] bArr, int i2, int i3) {
        Digest digest = this.hashAlg;
        if (digest != null) {
            digest.update(bArr, i2, i3);
            return;
        }
        throw new IllegalStateException("Call initSign or initVerify first!");
    }
}

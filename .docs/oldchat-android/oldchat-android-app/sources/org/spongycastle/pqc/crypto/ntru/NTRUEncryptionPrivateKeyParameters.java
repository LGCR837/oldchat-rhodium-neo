package org.spongycastle.pqc.crypto.ntru;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;
import org.spongycastle.pqc.math.ntru.polynomial.ProductFormPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.SparseTernaryPolynomial;

/* JADX INFO: loaded from: classes.dex */
public class NTRUEncryptionPrivateKeyParameters extends NTRUEncryptionKeyParameters {
    public IntegerPolynomial fp;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public IntegerPolynomial f7658h;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Polynomial f7659t;

    public NTRUEncryptionPrivateKeyParameters(IntegerPolynomial integerPolynomial, Polynomial polynomial, IntegerPolynomial integerPolynomial2, NTRUEncryptionParameters nTRUEncryptionParameters) {
        super(true, nTRUEncryptionParameters);
        this.f7658h = integerPolynomial;
        this.f7659t = polynomial;
        this.fp = integerPolynomial2;
    }

    private void init() {
        if (!this.params.fastFp) {
            this.fp = this.f7659t.toIntegerPolynomial().invertF3();
            return;
        }
        IntegerPolynomial integerPolynomial = new IntegerPolynomial(this.params.f7655N);
        this.fp = integerPolynomial;
        integerPolynomial.coeffs[0] = 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof NTRUEncryptionPrivateKeyParameters)) {
            return false;
        }
        NTRUEncryptionPrivateKeyParameters nTRUEncryptionPrivateKeyParameters = (NTRUEncryptionPrivateKeyParameters) obj;
        NTRUEncryptionParameters nTRUEncryptionParameters = this.params;
        if (nTRUEncryptionParameters == null) {
            if (nTRUEncryptionPrivateKeyParameters.params != null) {
                return false;
            }
        } else if (!nTRUEncryptionParameters.equals(nTRUEncryptionPrivateKeyParameters.params)) {
            return false;
        }
        Polynomial polynomial = this.f7659t;
        if (polynomial == null) {
            if (nTRUEncryptionPrivateKeyParameters.f7659t != null) {
                return false;
            }
        } else if (!polynomial.equals(nTRUEncryptionPrivateKeyParameters.f7659t)) {
            return false;
        }
        return this.f7658h.equals(nTRUEncryptionPrivateKeyParameters.f7658h);
    }

    public byte[] getEncoded() {
        byte[] binary = this.f7658h.toBinary(this.params.f7657q);
        Polynomial polynomial = this.f7659t;
        byte[] binary2 = polynomial instanceof ProductFormPolynomial ? ((ProductFormPolynomial) polynomial).toBinary() : polynomial.toIntegerPolynomial().toBinary3Tight();
        byte[] bArr = new byte[binary.length + binary2.length];
        System.arraycopy(binary, 0, bArr, 0, binary.length);
        System.arraycopy(binary2, 0, bArr, binary.length, binary2.length);
        return bArr;
    }

    public int hashCode() {
        NTRUEncryptionParameters nTRUEncryptionParameters = this.params;
        int iHashCode = ((nTRUEncryptionParameters == null ? 0 : nTRUEncryptionParameters.hashCode()) + 31) * 31;
        Polynomial polynomial = this.f7659t;
        int iHashCode2 = (iHashCode + (polynomial == null ? 0 : polynomial.hashCode())) * 31;
        IntegerPolynomial integerPolynomial = this.f7658h;
        return iHashCode2 + (integerPolynomial != null ? integerPolynomial.hashCode() : 0);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(getEncoded());
    }

    public NTRUEncryptionPrivateKeyParameters(byte[] bArr, NTRUEncryptionParameters nTRUEncryptionParameters) {
        this(new ByteArrayInputStream(bArr), nTRUEncryptionParameters);
    }

    public NTRUEncryptionPrivateKeyParameters(InputStream inputStream, NTRUEncryptionParameters nTRUEncryptionParameters) {
        super(true, nTRUEncryptionParameters);
        if (nTRUEncryptionParameters.polyType == 1) {
            int i2 = nTRUEncryptionParameters.f7655N;
            int i3 = nTRUEncryptionParameters.df1;
            int i4 = nTRUEncryptionParameters.df2;
            int i5 = nTRUEncryptionParameters.df3;
            int i6 = nTRUEncryptionParameters.fastFp ? i5 : i5 - 1;
            this.f7658h = IntegerPolynomial.fromBinary(inputStream, i2, nTRUEncryptionParameters.f7657q);
            this.f7659t = ProductFormPolynomial.fromBinary(inputStream, i2, i3, i4, i5, i6);
        } else {
            this.f7658h = IntegerPolynomial.fromBinary(inputStream, nTRUEncryptionParameters.f7655N, nTRUEncryptionParameters.f7657q);
            IntegerPolynomial integerPolynomialFromBinary3Tight = IntegerPolynomial.fromBinary3Tight(inputStream, nTRUEncryptionParameters.f7655N);
            this.f7659t = nTRUEncryptionParameters.sparse ? new SparseTernaryPolynomial(integerPolynomialFromBinary3Tight) : new DenseTernaryPolynomial(integerPolynomialFromBinary3Tight);
        }
        init();
    }
}

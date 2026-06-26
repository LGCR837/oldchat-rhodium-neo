package org.spongycastle.pqc.crypto.ntru;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;

/* JADX INFO: loaded from: classes.dex */
public class NTRUEncryptionPublicKeyParameters extends NTRUEncryptionKeyParameters {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public IntegerPolynomial f7660h;

    public NTRUEncryptionPublicKeyParameters(IntegerPolynomial integerPolynomial, NTRUEncryptionParameters nTRUEncryptionParameters) {
        super(false, nTRUEncryptionParameters);
        this.f7660h = integerPolynomial;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof NTRUEncryptionPublicKeyParameters)) {
            return false;
        }
        NTRUEncryptionPublicKeyParameters nTRUEncryptionPublicKeyParameters = (NTRUEncryptionPublicKeyParameters) obj;
        IntegerPolynomial integerPolynomial = this.f7660h;
        if (integerPolynomial == null) {
            if (nTRUEncryptionPublicKeyParameters.f7660h != null) {
                return false;
            }
        } else if (!integerPolynomial.equals(nTRUEncryptionPublicKeyParameters.f7660h)) {
            return false;
        }
        NTRUEncryptionParameters nTRUEncryptionParameters = this.params;
        if (nTRUEncryptionParameters == null) {
            if (nTRUEncryptionPublicKeyParameters.params != null) {
                return false;
            }
        } else if (!nTRUEncryptionParameters.equals(nTRUEncryptionPublicKeyParameters.params)) {
            return false;
        }
        return true;
    }

    public byte[] getEncoded() {
        return this.f7660h.toBinary(this.params.f7657q);
    }

    public int hashCode() {
        IntegerPolynomial integerPolynomial = this.f7660h;
        int iHashCode = ((integerPolynomial == null ? 0 : integerPolynomial.hashCode()) + 31) * 31;
        NTRUEncryptionParameters nTRUEncryptionParameters = this.params;
        return iHashCode + (nTRUEncryptionParameters != null ? nTRUEncryptionParameters.hashCode() : 0);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(getEncoded());
    }

    public NTRUEncryptionPublicKeyParameters(byte[] bArr, NTRUEncryptionParameters nTRUEncryptionParameters) {
        super(false, nTRUEncryptionParameters);
        this.f7660h = IntegerPolynomial.fromBinary(bArr, nTRUEncryptionParameters.f7655N, nTRUEncryptionParameters.f7657q);
    }

    public NTRUEncryptionPublicKeyParameters(InputStream inputStream, NTRUEncryptionParameters nTRUEncryptionParameters) {
        super(false, nTRUEncryptionParameters);
        this.f7660h = IntegerPolynomial.fromBinary(inputStream, nTRUEncryptionParameters.f7655N, nTRUEncryptionParameters.f7657q);
    }
}

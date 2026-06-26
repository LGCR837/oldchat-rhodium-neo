package org.spongycastle.pqc.crypto.ntru;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;

/* JADX INFO: loaded from: classes.dex */
public class NTRUSigningPublicKeyParameters extends AsymmetricKeyParameter {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public IntegerPolynomial f7673h;
    private NTRUSigningParameters params;

    public NTRUSigningPublicKeyParameters(IntegerPolynomial integerPolynomial, NTRUSigningParameters nTRUSigningParameters) {
        super(false);
        this.f7673h = integerPolynomial;
        this.params = nTRUSigningParameters;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NTRUSigningPublicKeyParameters nTRUSigningPublicKeyParameters = (NTRUSigningPublicKeyParameters) obj;
        IntegerPolynomial integerPolynomial = this.f7673h;
        if (integerPolynomial == null) {
            if (nTRUSigningPublicKeyParameters.f7673h != null) {
                return false;
            }
        } else if (!integerPolynomial.equals(nTRUSigningPublicKeyParameters.f7673h)) {
            return false;
        }
        NTRUSigningParameters nTRUSigningParameters = this.params;
        if (nTRUSigningParameters == null) {
            if (nTRUSigningPublicKeyParameters.params != null) {
                return false;
            }
        } else if (!nTRUSigningParameters.equals(nTRUSigningPublicKeyParameters.params)) {
            return false;
        }
        return true;
    }

    public byte[] getEncoded() {
        return this.f7673h.toBinary(this.params.f7670q);
    }

    public int hashCode() {
        IntegerPolynomial integerPolynomial = this.f7673h;
        int iHashCode = ((integerPolynomial == null ? 0 : integerPolynomial.hashCode()) + 31) * 31;
        NTRUSigningParameters nTRUSigningParameters = this.params;
        return iHashCode + (nTRUSigningParameters != null ? nTRUSigningParameters.hashCode() : 0);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(getEncoded());
    }

    public NTRUSigningPublicKeyParameters(byte[] bArr, NTRUSigningParameters nTRUSigningParameters) {
        super(false);
        this.f7673h = IntegerPolynomial.fromBinary(bArr, nTRUSigningParameters.f7668N, nTRUSigningParameters.f7670q);
        this.params = nTRUSigningParameters;
    }

    public NTRUSigningPublicKeyParameters(InputStream inputStream, NTRUSigningParameters nTRUSigningParameters) {
        super(false);
        this.f7673h = IntegerPolynomial.fromBinary(inputStream, nTRUSigningParameters.f7668N, nTRUSigningParameters.f7670q);
        this.params = nTRUSigningParameters;
    }
}

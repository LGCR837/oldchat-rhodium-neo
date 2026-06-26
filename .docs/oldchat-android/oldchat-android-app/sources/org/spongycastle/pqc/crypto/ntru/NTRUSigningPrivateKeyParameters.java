package org.spongycastle.pqc.crypto.ntru;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;
import org.spongycastle.pqc.math.ntru.polynomial.ProductFormPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.SparseTernaryPolynomial;

/* JADX INFO: loaded from: classes.dex */
public class NTRUSigningPrivateKeyParameters extends AsymmetricKeyParameter {
    private List<Basis> bases;
    private NTRUSigningPublicKeyParameters publicKey;

    public NTRUSigningPrivateKeyParameters(byte[] bArr, NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters) {
        this(new ByteArrayInputStream(bArr), nTRUSigningKeyGenerationParameters);
    }

    private void add(Basis basis) {
        this.bases.add(basis);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NTRUSigningPrivateKeyParameters nTRUSigningPrivateKeyParameters = (NTRUSigningPrivateKeyParameters) obj;
        List<Basis> list = this.bases;
        if ((list == null) != (nTRUSigningPrivateKeyParameters.bases == null)) {
            return false;
        }
        if (list == null) {
            return true;
        }
        if (list.size() != nTRUSigningPrivateKeyParameters.bases.size()) {
            return false;
        }
        for (int i2 = 0; i2 < this.bases.size(); i2++) {
            Basis basis = this.bases.get(i2);
            Basis basis2 = nTRUSigningPrivateKeyParameters.bases.get(i2);
            if (!basis.f7671f.equals(basis2.f7671f) || !basis.fPrime.equals(basis2.fPrime)) {
                return false;
            }
            if ((i2 != 0 && !basis.f7672h.equals(basis2.f7672h)) || !basis.params.equals(basis2.params)) {
                return false;
            }
        }
        return true;
    }

    public Basis getBasis(int i2) {
        return this.bases.get(i2);
    }

    public byte[] getEncoded() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        while (i2 < this.bases.size()) {
            this.bases.get(i2).encode(byteArrayOutputStream, i2 != 0);
            i2++;
        }
        byteArrayOutputStream.write(this.publicKey.getEncoded());
        return byteArrayOutputStream.toByteArray();
    }

    public NTRUSigningPublicKeyParameters getPublicKey() {
        return this.publicKey;
    }

    public int hashCode() {
        List<Basis> list = this.bases;
        if (list == null) {
            return 31;
        }
        int iHashCode = 31 + list.hashCode();
        Iterator<Basis> it = this.bases.iterator();
        while (it.hasNext()) {
            iHashCode += it.next().hashCode();
        }
        return iHashCode;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(getEncoded());
    }

    public NTRUSigningPrivateKeyParameters(InputStream inputStream, NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters) {
        super(true);
        this.bases = new ArrayList();
        int i2 = 0;
        while (i2 <= nTRUSigningKeyGenerationParameters.f7661B) {
            add(new Basis(inputStream, nTRUSigningKeyGenerationParameters, i2 != 0));
            i2++;
        }
        this.publicKey = new NTRUSigningPublicKeyParameters(inputStream, nTRUSigningKeyGenerationParameters.getSigningParameters());
    }

    public static class Basis {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Polynomial f7671f;
        public Polynomial fPrime;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public IntegerPolynomial f7672h;
        NTRUSigningKeyGenerationParameters params;

        public Basis(Polynomial polynomial, Polynomial polynomial2, IntegerPolynomial integerPolynomial, NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters) {
            this.f7671f = polynomial;
            this.fPrime = polynomial2;
            this.f7672h = integerPolynomial;
            this.params = nTRUSigningKeyGenerationParameters;
        }

        private byte[] getEncoded(Polynomial polynomial) {
            return polynomial instanceof ProductFormPolynomial ? ((ProductFormPolynomial) polynomial).toBinary() : polynomial.toIntegerPolynomial().toBinary3Tight();
        }

        public void encode(OutputStream outputStream, boolean z2) throws IOException {
            int i2 = this.params.f7664q;
            outputStream.write(getEncoded(this.f7671f));
            if (this.params.basisType == 0) {
                IntegerPolynomial integerPolynomial = this.fPrime.toIntegerPolynomial();
                int i3 = 0;
                while (true) {
                    int[] iArr = integerPolynomial.coeffs;
                    if (i3 >= iArr.length) {
                        break;
                    }
                    iArr[i3] = iArr[i3] + (i2 / 2);
                    i3++;
                }
                outputStream.write(integerPolynomial.toBinary(i2));
            } else {
                outputStream.write(getEncoded(this.fPrime));
            }
            if (z2) {
                outputStream.write(this.f7672h.toBinary(i2));
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof Basis)) {
                return false;
            }
            Basis basis = (Basis) obj;
            Polynomial polynomial = this.f7671f;
            if (polynomial == null) {
                if (basis.f7671f != null) {
                    return false;
                }
            } else if (!polynomial.equals(basis.f7671f)) {
                return false;
            }
            Polynomial polynomial2 = this.fPrime;
            if (polynomial2 == null) {
                if (basis.fPrime != null) {
                    return false;
                }
            } else if (!polynomial2.equals(basis.fPrime)) {
                return false;
            }
            IntegerPolynomial integerPolynomial = this.f7672h;
            if (integerPolynomial == null) {
                if (basis.f7672h != null) {
                    return false;
                }
            } else if (!integerPolynomial.equals(basis.f7672h)) {
                return false;
            }
            NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters = this.params;
            if (nTRUSigningKeyGenerationParameters == null) {
                if (basis.params != null) {
                    return false;
                }
            } else if (!nTRUSigningKeyGenerationParameters.equals(basis.params)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            Polynomial polynomial = this.f7671f;
            int iHashCode = ((polynomial == null ? 0 : polynomial.hashCode()) + 31) * 31;
            Polynomial polynomial2 = this.fPrime;
            int iHashCode2 = (iHashCode + (polynomial2 == null ? 0 : polynomial2.hashCode())) * 31;
            IntegerPolynomial integerPolynomial = this.f7672h;
            int iHashCode3 = (iHashCode2 + (integerPolynomial == null ? 0 : integerPolynomial.hashCode())) * 31;
            NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters = this.params;
            return iHashCode3 + (nTRUSigningKeyGenerationParameters != null ? nTRUSigningKeyGenerationParameters.hashCode() : 0);
        }

        public Basis(InputStream inputStream, NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters, boolean z2) {
            int i2 = nTRUSigningKeyGenerationParameters.f7662N;
            int i3 = nTRUSigningKeyGenerationParameters.f7664q;
            int i4 = nTRUSigningKeyGenerationParameters.d1;
            int i5 = nTRUSigningKeyGenerationParameters.d2;
            int i6 = nTRUSigningKeyGenerationParameters.d3;
            boolean z3 = nTRUSigningKeyGenerationParameters.sparse;
            this.params = nTRUSigningKeyGenerationParameters;
            if (nTRUSigningKeyGenerationParameters.polyType == 1) {
                this.f7671f = ProductFormPolynomial.fromBinary(inputStream, i2, i4, i5, i6 + 1, i6);
            } else {
                IntegerPolynomial integerPolynomialFromBinary3Tight = IntegerPolynomial.fromBinary3Tight(inputStream, i2);
                this.f7671f = z3 ? new SparseTernaryPolynomial(integerPolynomialFromBinary3Tight) : new DenseTernaryPolynomial(integerPolynomialFromBinary3Tight);
            }
            if (nTRUSigningKeyGenerationParameters.basisType == 0) {
                IntegerPolynomial integerPolynomialFromBinary = IntegerPolynomial.fromBinary(inputStream, i2, i3);
                int i7 = 0;
                while (true) {
                    int[] iArr = integerPolynomialFromBinary.coeffs;
                    if (i7 >= iArr.length) {
                        break;
                    }
                    iArr[i7] = iArr[i7] - (i3 / 2);
                    i7++;
                }
                this.fPrime = integerPolynomialFromBinary;
            } else if (nTRUSigningKeyGenerationParameters.polyType == 1) {
                this.fPrime = ProductFormPolynomial.fromBinary(inputStream, i2, i4, i5, i6 + 1, i6);
            } else {
                this.fPrime = IntegerPolynomial.fromBinary3Tight(inputStream, i2);
            }
            if (z2) {
                this.f7672h = IntegerPolynomial.fromBinary(inputStream, i2, i3);
            }
        }
    }

    public NTRUSigningPrivateKeyParameters(List<Basis> list, NTRUSigningPublicKeyParameters nTRUSigningPublicKeyParameters) {
        super(true);
        this.bases = new ArrayList(list);
        this.publicKey = nTRUSigningPublicKeyParameters;
    }
}

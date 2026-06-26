package org.spongycastle.pqc.crypto.ntru;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA512Digest;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class NTRUSigningKeyGenerationParameters extends KeyGenerationParameters implements Cloneable {
    public static final int BASIS_TYPE_STANDARD = 0;
    public static final int BASIS_TYPE_TRANSPOSE = 1;
    public static final int KEY_GEN_ALG_FLOAT = 1;
    public static final int KEY_GEN_ALG_RESULTANT = 0;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public int f7661B;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public int f7662N;
    public int basisType;
    double beta;
    public double betaSq;
    int bitsF;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f7663d;
    public int d1;
    public int d2;
    public int d3;
    public Digest hashAlg;
    public int keyGenAlg;
    double keyNormBound;
    public double keyNormBoundSq;
    double normBound;
    public double normBoundSq;
    public int polyType;
    public boolean primeCheck;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f7664q;
    public int signFailTolerance;
    public boolean sparse;
    public static final NTRUSigningKeyGenerationParameters APR2011_439 = new NTRUSigningKeyGenerationParameters(439, 2048, CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA, 1, 1, 0.165d, 490.0d, 280.0d, false, true, 0, new SHA256Digest());
    public static final NTRUSigningKeyGenerationParameters APR2011_439_PROD = new NTRUSigningKeyGenerationParameters(439, 2048, 9, 8, 5, 1, 1, 0.165d, 490.0d, 280.0d, false, true, 0, new SHA256Digest());
    public static final NTRUSigningKeyGenerationParameters APR2011_743 = new NTRUSigningKeyGenerationParameters(743, 2048, 248, 1, 1, 0.127d, 560.0d, 360.0d, true, false, 0, new SHA512Digest());
    public static final NTRUSigningKeyGenerationParameters APR2011_743_PROD = new NTRUSigningKeyGenerationParameters(743, 2048, 11, 11, 15, 1, 1, 0.127d, 560.0d, 360.0d, true, false, 0, new SHA512Digest());
    public static final NTRUSigningKeyGenerationParameters TEST157 = new NTRUSigningKeyGenerationParameters(CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, 256, 29, 1, 1, 0.38d, 200.0d, 80.0d, false, false, 0, new SHA256Digest());
    public static final NTRUSigningKeyGenerationParameters TEST157_PROD = new NTRUSigningKeyGenerationParameters(CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, 256, 5, 5, 8, 1, 1, 0.38d, 200.0d, 80.0d, false, false, 0, new SHA256Digest());

    public NTRUSigningKeyGenerationParameters(int i2, int i3, int i4, int i5, int i6, double d2, double d3, double d4, boolean z2, boolean z3, int i7, Digest digest) {
        super(new SecureRandom(), i2);
        this.signFailTolerance = 100;
        this.bitsF = 6;
        this.f7662N = i2;
        this.f7664q = i3;
        this.f7663d = i4;
        this.f7661B = i5;
        this.basisType = i6;
        this.beta = d2;
        this.normBound = d3;
        this.keyNormBound = d4;
        this.primeCheck = z2;
        this.sparse = z3;
        this.keyGenAlg = i7;
        this.hashAlg = digest;
        this.polyType = 0;
        init();
    }

    private void init() {
        double d2 = this.beta;
        this.betaSq = d2 * d2;
        double d3 = this.normBound;
        this.normBoundSq = d3 * d3;
        double d4 = this.keyNormBound;
        this.keyNormBoundSq = d4 * d4;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof NTRUSigningKeyGenerationParameters)) {
            return false;
        }
        NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters = (NTRUSigningKeyGenerationParameters) obj;
        if (this.f7661B != nTRUSigningKeyGenerationParameters.f7661B || this.f7662N != nTRUSigningKeyGenerationParameters.f7662N || this.basisType != nTRUSigningKeyGenerationParameters.basisType || Double.doubleToLongBits(this.beta) != Double.doubleToLongBits(nTRUSigningKeyGenerationParameters.beta) || Double.doubleToLongBits(this.betaSq) != Double.doubleToLongBits(nTRUSigningKeyGenerationParameters.betaSq) || this.bitsF != nTRUSigningKeyGenerationParameters.bitsF || this.f7663d != nTRUSigningKeyGenerationParameters.f7663d || this.d1 != nTRUSigningKeyGenerationParameters.d1 || this.d2 != nTRUSigningKeyGenerationParameters.d2 || this.d3 != nTRUSigningKeyGenerationParameters.d3) {
            return false;
        }
        Digest digest = this.hashAlg;
        if (digest == null) {
            if (nTRUSigningKeyGenerationParameters.hashAlg != null) {
                return false;
            }
        } else if (!digest.getAlgorithmName().equals(nTRUSigningKeyGenerationParameters.hashAlg.getAlgorithmName())) {
            return false;
        }
        return this.keyGenAlg == nTRUSigningKeyGenerationParameters.keyGenAlg && Double.doubleToLongBits(this.keyNormBound) == Double.doubleToLongBits(nTRUSigningKeyGenerationParameters.keyNormBound) && Double.doubleToLongBits(this.keyNormBoundSq) == Double.doubleToLongBits(nTRUSigningKeyGenerationParameters.keyNormBoundSq) && Double.doubleToLongBits(this.normBound) == Double.doubleToLongBits(nTRUSigningKeyGenerationParameters.normBound) && Double.doubleToLongBits(this.normBoundSq) == Double.doubleToLongBits(nTRUSigningKeyGenerationParameters.normBoundSq) && this.polyType == nTRUSigningKeyGenerationParameters.polyType && this.primeCheck == nTRUSigningKeyGenerationParameters.primeCheck && this.f7664q == nTRUSigningKeyGenerationParameters.f7664q && this.signFailTolerance == nTRUSigningKeyGenerationParameters.signFailTolerance && this.sparse == nTRUSigningKeyGenerationParameters.sparse;
    }

    public NTRUSigningParameters getSigningParameters() {
        return new NTRUSigningParameters(this.f7662N, this.f7664q, this.f7663d, this.f7661B, this.beta, this.normBound, this.hashAlg);
    }

    public int hashCode() {
        int i2 = ((((this.f7661B + 31) * 31) + this.f7662N) * 31) + this.basisType;
        long jDoubleToLongBits = Double.doubleToLongBits(this.beta);
        int i3 = (i2 * 31) + ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32)));
        long jDoubleToLongBits2 = Double.doubleToLongBits(this.betaSq);
        int i4 = ((((((((((((i3 * 31) + ((int) (jDoubleToLongBits2 ^ (jDoubleToLongBits2 >>> 32)))) * 31) + this.bitsF) * 31) + this.f7663d) * 31) + this.d1) * 31) + this.d2) * 31) + this.d3) * 31;
        Digest digest = this.hashAlg;
        int iHashCode = ((i4 + (digest == null ? 0 : digest.getAlgorithmName().hashCode())) * 31) + this.keyGenAlg;
        long jDoubleToLongBits3 = Double.doubleToLongBits(this.keyNormBound);
        int i5 = (iHashCode * 31) + ((int) (jDoubleToLongBits3 ^ (jDoubleToLongBits3 >>> 32)));
        long jDoubleToLongBits4 = Double.doubleToLongBits(this.keyNormBoundSq);
        int i6 = (i5 * 31) + ((int) (jDoubleToLongBits4 ^ (jDoubleToLongBits4 >>> 32)));
        long jDoubleToLongBits5 = Double.doubleToLongBits(this.normBound);
        int i7 = (i6 * 31) + ((int) (jDoubleToLongBits5 ^ (jDoubleToLongBits5 >>> 32)));
        long jDoubleToLongBits6 = Double.doubleToLongBits(this.normBoundSq);
        return (((((((((((i7 * 31) + ((int) (jDoubleToLongBits6 ^ (jDoubleToLongBits6 >>> 32)))) * 31) + this.polyType) * 31) + (this.primeCheck ? 1231 : 1237)) * 31) + this.f7664q) * 31) + this.signFailTolerance) * 31) + (this.sparse ? 1231 : 1237);
    }

    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        StringBuilder sb = new StringBuilder("SignatureParameters(N=" + this.f7662N + " q=" + this.f7664q);
        if (this.polyType == 0) {
            sb.append(" polyType=SIMPLE d=" + this.f7663d);
        } else {
            sb.append(" polyType=PRODUCT d1=" + this.d1 + " d2=" + this.d2 + " d3=" + this.d3);
        }
        sb.append(" B=" + this.f7661B + " basisType=" + this.basisType + " beta=" + decimalFormat.format(this.beta) + " normBound=" + decimalFormat.format(this.normBound) + " keyNormBound=" + decimalFormat.format(this.keyNormBound) + " prime=" + this.primeCheck + " sparse=" + this.sparse + " keyGenAlg=" + this.keyGenAlg + " hashAlg=" + this.hashAlg + ")");
        return sb.toString();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(this.f7662N);
        dataOutputStream.writeInt(this.f7664q);
        dataOutputStream.writeInt(this.f7663d);
        dataOutputStream.writeInt(this.d1);
        dataOutputStream.writeInt(this.d2);
        dataOutputStream.writeInt(this.d3);
        dataOutputStream.writeInt(this.f7661B);
        dataOutputStream.writeInt(this.basisType);
        dataOutputStream.writeDouble(this.beta);
        dataOutputStream.writeDouble(this.normBound);
        dataOutputStream.writeDouble(this.keyNormBound);
        dataOutputStream.writeInt(this.signFailTolerance);
        dataOutputStream.writeBoolean(this.primeCheck);
        dataOutputStream.writeBoolean(this.sparse);
        dataOutputStream.writeInt(this.bitsF);
        dataOutputStream.write(this.keyGenAlg);
        dataOutputStream.writeUTF(this.hashAlg.getAlgorithmName());
        dataOutputStream.write(this.polyType);
    }

    public NTRUSigningKeyGenerationParameters clone() {
        return this.polyType == 0 ? new NTRUSigningKeyGenerationParameters(this.f7662N, this.f7664q, this.f7663d, this.f7661B, this.basisType, this.beta, this.normBound, this.keyNormBound, this.primeCheck, this.sparse, this.keyGenAlg, this.hashAlg) : new NTRUSigningKeyGenerationParameters(this.f7662N, this.f7664q, this.d1, this.d2, this.d3, this.f7661B, this.basisType, this.beta, this.normBound, this.keyNormBound, this.primeCheck, this.sparse, this.keyGenAlg, this.hashAlg);
    }

    public NTRUSigningKeyGenerationParameters(int i2, int i3, int i4, int i5, int i6, int i7, int i8, double d2, double d3, double d4, boolean z2, boolean z3, int i9, Digest digest) {
        super(new SecureRandom(), i2);
        this.signFailTolerance = 100;
        this.bitsF = 6;
        this.f7662N = i2;
        this.f7664q = i3;
        this.d1 = i4;
        this.d2 = i5;
        this.d3 = i6;
        this.f7661B = i7;
        this.basisType = i8;
        this.beta = d2;
        this.normBound = d3;
        this.keyNormBound = d4;
        this.primeCheck = z2;
        this.sparse = z3;
        this.keyGenAlg = i9;
        this.hashAlg = digest;
        this.polyType = 1;
        init();
    }

    public NTRUSigningKeyGenerationParameters(InputStream inputStream) throws IOException {
        super(new SecureRandom(), 0);
        this.signFailTolerance = 100;
        this.bitsF = 6;
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.f7662N = dataInputStream.readInt();
        this.f7664q = dataInputStream.readInt();
        this.f7663d = dataInputStream.readInt();
        this.d1 = dataInputStream.readInt();
        this.d2 = dataInputStream.readInt();
        this.d3 = dataInputStream.readInt();
        this.f7661B = dataInputStream.readInt();
        this.basisType = dataInputStream.readInt();
        this.beta = dataInputStream.readDouble();
        this.normBound = dataInputStream.readDouble();
        this.keyNormBound = dataInputStream.readDouble();
        this.signFailTolerance = dataInputStream.readInt();
        this.primeCheck = dataInputStream.readBoolean();
        this.sparse = dataInputStream.readBoolean();
        this.bitsF = dataInputStream.readInt();
        this.keyGenAlg = dataInputStream.read();
        String utf = dataInputStream.readUTF();
        if (McElieceCCA2KeyGenParameterSpec.SHA512.equals(utf)) {
            this.hashAlg = new SHA512Digest();
        } else if (McElieceCCA2KeyGenParameterSpec.SHA256.equals(utf)) {
            this.hashAlg = new SHA256Digest();
        }
        this.polyType = dataInputStream.read();
        init();
    }
}

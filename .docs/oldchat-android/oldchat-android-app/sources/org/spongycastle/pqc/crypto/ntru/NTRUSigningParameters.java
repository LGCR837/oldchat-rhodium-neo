package org.spongycastle.pqc.crypto.ntru;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA512Digest;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class NTRUSigningParameters implements Cloneable {

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public int f7667B;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public int f7668N;
    double beta;
    public double betaSq;
    int bitsF;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f7669d;
    public int d1;
    public int d2;
    public int d3;
    public Digest hashAlg;
    double normBound;
    public double normBoundSq;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f7670q;
    public int signFailTolerance;

    public NTRUSigningParameters(int i2, int i3, int i4, int i5, double d2, double d3, Digest digest) {
        this.signFailTolerance = 100;
        this.bitsF = 6;
        this.f7668N = i2;
        this.f7670q = i3;
        this.f7669d = i4;
        this.f7667B = i5;
        this.beta = d2;
        this.normBound = d3;
        this.hashAlg = digest;
        init();
    }

    private void init() {
        double d2 = this.beta;
        this.betaSq = d2 * d2;
        double d3 = this.normBound;
        this.normBoundSq = d3 * d3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof NTRUSigningParameters)) {
            return false;
        }
        NTRUSigningParameters nTRUSigningParameters = (NTRUSigningParameters) obj;
        if (this.f7667B != nTRUSigningParameters.f7667B || this.f7668N != nTRUSigningParameters.f7668N || Double.doubleToLongBits(this.beta) != Double.doubleToLongBits(nTRUSigningParameters.beta) || Double.doubleToLongBits(this.betaSq) != Double.doubleToLongBits(nTRUSigningParameters.betaSq) || this.bitsF != nTRUSigningParameters.bitsF || this.f7669d != nTRUSigningParameters.f7669d || this.d1 != nTRUSigningParameters.d1 || this.d2 != nTRUSigningParameters.d2 || this.d3 != nTRUSigningParameters.d3) {
            return false;
        }
        Digest digest = this.hashAlg;
        if (digest == null) {
            if (nTRUSigningParameters.hashAlg != null) {
                return false;
            }
        } else if (!digest.getAlgorithmName().equals(nTRUSigningParameters.hashAlg.getAlgorithmName())) {
            return false;
        }
        return Double.doubleToLongBits(this.normBound) == Double.doubleToLongBits(nTRUSigningParameters.normBound) && Double.doubleToLongBits(this.normBoundSq) == Double.doubleToLongBits(nTRUSigningParameters.normBoundSq) && this.f7670q == nTRUSigningParameters.f7670q && this.signFailTolerance == nTRUSigningParameters.signFailTolerance;
    }

    public int hashCode() {
        int i2 = ((this.f7667B + 31) * 31) + this.f7668N;
        long jDoubleToLongBits = Double.doubleToLongBits(this.beta);
        int i3 = (i2 * 31) + ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32)));
        long jDoubleToLongBits2 = Double.doubleToLongBits(this.betaSq);
        int i4 = ((((((((((((i3 * 31) + ((int) (jDoubleToLongBits2 ^ (jDoubleToLongBits2 >>> 32)))) * 31) + this.bitsF) * 31) + this.f7669d) * 31) + this.d1) * 31) + this.d2) * 31) + this.d3) * 31;
        Digest digest = this.hashAlg;
        int iHashCode = i4 + (digest == null ? 0 : digest.getAlgorithmName().hashCode());
        long jDoubleToLongBits3 = Double.doubleToLongBits(this.normBound);
        int i5 = (iHashCode * 31) + ((int) (jDoubleToLongBits3 ^ (jDoubleToLongBits3 >>> 32)));
        long jDoubleToLongBits4 = Double.doubleToLongBits(this.normBoundSq);
        return (((((i5 * 31) + ((int) (jDoubleToLongBits4 ^ (jDoubleToLongBits4 >>> 32)))) * 31) + this.f7670q) * 31) + this.signFailTolerance;
    }

    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        StringBuilder sb = new StringBuilder("SignatureParameters(N=" + this.f7668N + " q=" + this.f7670q);
        sb.append(" B=" + this.f7667B + " beta=" + decimalFormat.format(this.beta) + " normBound=" + decimalFormat.format(this.normBound) + " hashAlg=" + this.hashAlg + ")");
        return sb.toString();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(this.f7668N);
        dataOutputStream.writeInt(this.f7670q);
        dataOutputStream.writeInt(this.f7669d);
        dataOutputStream.writeInt(this.d1);
        dataOutputStream.writeInt(this.d2);
        dataOutputStream.writeInt(this.d3);
        dataOutputStream.writeInt(this.f7667B);
        dataOutputStream.writeDouble(this.beta);
        dataOutputStream.writeDouble(this.normBound);
        dataOutputStream.writeInt(this.signFailTolerance);
        dataOutputStream.writeInt(this.bitsF);
        dataOutputStream.writeUTF(this.hashAlg.getAlgorithmName());
    }

    public NTRUSigningParameters clone() {
        return new NTRUSigningParameters(this.f7668N, this.f7670q, this.f7669d, this.f7667B, this.beta, this.normBound, this.hashAlg);
    }

    public NTRUSigningParameters(int i2, int i3, int i4, int i5, int i6, int i7, double d2, double d3, double d4, Digest digest) {
        this.signFailTolerance = 100;
        this.bitsF = 6;
        this.f7668N = i2;
        this.f7670q = i3;
        this.d1 = i4;
        this.d2 = i5;
        this.d3 = i6;
        this.f7667B = i7;
        this.beta = d2;
        this.normBound = d3;
        this.hashAlg = digest;
        init();
    }

    public NTRUSigningParameters(InputStream inputStream) throws IOException {
        this.signFailTolerance = 100;
        this.bitsF = 6;
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.f7668N = dataInputStream.readInt();
        this.f7670q = dataInputStream.readInt();
        this.f7669d = dataInputStream.readInt();
        this.d1 = dataInputStream.readInt();
        this.d2 = dataInputStream.readInt();
        this.d3 = dataInputStream.readInt();
        this.f7667B = dataInputStream.readInt();
        this.beta = dataInputStream.readDouble();
        this.normBound = dataInputStream.readDouble();
        this.signFailTolerance = dataInputStream.readInt();
        this.bitsF = dataInputStream.readInt();
        String utf = dataInputStream.readUTF();
        if (McElieceCCA2KeyGenParameterSpec.SHA512.equals(utf)) {
            this.hashAlg = new SHA512Digest();
        } else if (McElieceCCA2KeyGenParameterSpec.SHA256.equals(utf)) {
            this.hashAlg = new SHA256Digest();
        }
        init();
    }
}

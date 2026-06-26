package org.spongycastle.crypto.agreement.srp;

import java.math.BigInteger;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.SRP6GroupParameters;

/* JADX INFO: loaded from: classes.dex */
public class SRP6VerifierGenerator {

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    protected BigInteger f7265N;
    protected Digest digest;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected BigInteger f7266g;

    public BigInteger generateVerifier(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return this.f7266g.modPow(SRP6Util.calculateX(this.digest, this.f7265N, bArr, bArr2, bArr3), this.f7265N);
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, Digest digest) {
        this.f7265N = bigInteger;
        this.f7266g = bigInteger2;
        this.digest = digest;
    }

    public void init(SRP6GroupParameters sRP6GroupParameters, Digest digest) {
        this.f7265N = sRP6GroupParameters.getN();
        this.f7266g = sRP6GroupParameters.getG();
        this.digest = digest;
    }
}

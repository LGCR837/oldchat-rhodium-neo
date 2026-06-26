package org.spongycastle.jce.provider;

import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KDFParameters;

/* JADX INFO: loaded from: classes.dex */
public class BrokenKDF2BytesGenerator implements DerivationFunction {
    private Digest digest;
    private byte[] iv;
    private byte[] shared;

    public BrokenKDF2BytesGenerator(Digest digest) {
        this.digest = digest;
    }

    @Override // org.spongycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int i2, int i3) {
        if (bArr.length - i3 < i2) {
            throw new OutputLengthException("output buffer too small");
        }
        long j2 = ((long) i3) * 8;
        if (j2 > ((long) this.digest.getDigestSize()) * 17179869184L) {
            new IllegalArgumentException("Output length to large");
        }
        int digestSize = (int) (j2 / ((long) this.digest.getDigestSize()));
        int digestSize2 = this.digest.getDigestSize();
        byte[] bArr2 = new byte[digestSize2];
        for (int i4 = 1; i4 <= digestSize; i4++) {
            Digest digest = this.digest;
            byte[] bArr3 = this.shared;
            digest.update(bArr3, 0, bArr3.length);
            this.digest.update((byte) (i4 & 255));
            this.digest.update((byte) ((i4 >> 8) & 255));
            this.digest.update((byte) ((i4 >> 16) & 255));
            this.digest.update((byte) ((i4 >> 24) & 255));
            Digest digest2 = this.digest;
            byte[] bArr4 = this.iv;
            digest2.update(bArr4, 0, bArr4.length);
            this.digest.doFinal(bArr2, 0);
            int i5 = i3 - i2;
            if (i5 > digestSize2) {
                System.arraycopy(bArr2, 0, bArr, i2, digestSize2);
                i2 += digestSize2;
            } else {
                System.arraycopy(bArr2, 0, bArr, i2, i5);
            }
        }
        this.digest.reset();
        return i3;
    }

    public Digest getDigest() {
        return this.digest;
    }

    @Override // org.spongycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        if (!(derivationParameters instanceof KDFParameters)) {
            throw new IllegalArgumentException("KDF parameters required for generator");
        }
        KDFParameters kDFParameters = (KDFParameters) derivationParameters;
        this.shared = kDFParameters.getSharedSecret();
        this.iv = kDFParameters.getIV();
    }
}

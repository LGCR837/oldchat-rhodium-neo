package org.spongycastle.crypto.generators;

import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.MGFParameters;

/* JADX INFO: loaded from: classes.dex */
public class MGF1BytesGenerator implements DerivationFunction {
    private Digest digest;
    private int hLen;
    private byte[] seed;

    public MGF1BytesGenerator(Digest digest) {
        this.digest = digest;
        this.hLen = digest.getDigestSize();
    }

    private void ItoOSP(int i2, byte[] bArr) {
        bArr[0] = (byte) (i2 >>> 24);
        bArr[1] = (byte) (i2 >>> 16);
        bArr[2] = (byte) (i2 >>> 8);
        bArr[3] = (byte) i2;
    }

    @Override // org.spongycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int i2, int i3) {
        int i4;
        if (bArr.length - i3 < i2) {
            throw new OutputLengthException("output buffer too small");
        }
        byte[] bArr2 = new byte[this.hLen];
        byte[] bArr3 = new byte[4];
        this.digest.reset();
        if (i3 > this.hLen) {
            i4 = 0;
            do {
                ItoOSP(i4, bArr3);
                Digest digest = this.digest;
                byte[] bArr4 = this.seed;
                digest.update(bArr4, 0, bArr4.length);
                this.digest.update(bArr3, 0, 4);
                this.digest.doFinal(bArr2, 0);
                int i5 = this.hLen;
                System.arraycopy(bArr2, 0, bArr, (i4 * i5) + i2, i5);
                i4++;
            } while (i4 < i3 / this.hLen);
        } else {
            i4 = 0;
        }
        if (this.hLen * i4 < i3) {
            ItoOSP(i4, bArr3);
            Digest digest2 = this.digest;
            byte[] bArr5 = this.seed;
            digest2.update(bArr5, 0, bArr5.length);
            this.digest.update(bArr3, 0, 4);
            this.digest.doFinal(bArr2, 0);
            int i6 = this.hLen;
            System.arraycopy(bArr2, 0, bArr, i2 + (i4 * i6), i3 - (i4 * i6));
        }
        return i3;
    }

    public Digest getDigest() {
        return this.digest;
    }

    @Override // org.spongycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        if (!(derivationParameters instanceof MGFParameters)) {
            throw new IllegalArgumentException("MGF parameters required for MGF1Generator");
        }
        this.seed = ((MGFParameters) derivationParameters).getSeed();
    }
}

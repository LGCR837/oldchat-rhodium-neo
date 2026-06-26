package org.spongycastle.crypto.agreement.kdf;

import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KDFParameters;

/* JADX INFO: loaded from: classes.dex */
public class ConcatenationKDFGenerator implements DerivationFunction {
    private Digest digest;
    private int hLen;
    private byte[] otherInfo;
    private byte[] shared;

    public ConcatenationKDFGenerator(Digest digest) {
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
        int i5;
        if (bArr.length - i3 < i2) {
            throw new OutputLengthException("output buffer too small");
        }
        byte[] bArr2 = new byte[this.hLen];
        byte[] bArr3 = new byte[4];
        this.digest.reset();
        int i6 = 1;
        if (i3 > this.hLen) {
            i4 = 0;
            while (true) {
                ItoOSP(i6, bArr3);
                this.digest.update(bArr3, 0, 4);
                Digest digest = this.digest;
                byte[] bArr4 = this.shared;
                digest.update(bArr4, 0, bArr4.length);
                Digest digest2 = this.digest;
                byte[] bArr5 = this.otherInfo;
                digest2.update(bArr5, 0, bArr5.length);
                this.digest.doFinal(bArr2, 0);
                System.arraycopy(bArr2, 0, bArr, i2 + i4, this.hLen);
                int i7 = this.hLen;
                i4 += i7;
                i5 = i6 + 1;
                if (i6 >= i3 / i7) {
                    break;
                }
                i6 = i5;
            }
            i6 = i5;
        } else {
            i4 = 0;
        }
        if (i4 < i3) {
            ItoOSP(i6, bArr3);
            this.digest.update(bArr3, 0, 4);
            Digest digest3 = this.digest;
            byte[] bArr6 = this.shared;
            digest3.update(bArr6, 0, bArr6.length);
            Digest digest4 = this.digest;
            byte[] bArr7 = this.otherInfo;
            digest4.update(bArr7, 0, bArr7.length);
            this.digest.doFinal(bArr2, 0);
            System.arraycopy(bArr2, 0, bArr, i2 + i4, i3 - i4);
        }
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
        this.otherInfo = kDFParameters.getIV();
    }
}

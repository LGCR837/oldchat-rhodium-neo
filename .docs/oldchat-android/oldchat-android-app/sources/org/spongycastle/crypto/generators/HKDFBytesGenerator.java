package org.spongycastle.crypto.generators;

import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.crypto.params.HKDFParameters;
import org.spongycastle.crypto.params.KeyParameter;

/* JADX INFO: loaded from: classes.dex */
public class HKDFBytesGenerator implements DerivationFunction {
    private byte[] currentT;
    private int generatedBytes;
    private HMac hMacHash;
    private int hashLen;
    private byte[] info;

    public HKDFBytesGenerator(Digest digest) {
        this.hMacHash = new HMac(digest);
        this.hashLen = digest.getDigestSize();
    }

    private void expandNext() {
        int i2 = this.generatedBytes;
        int i3 = this.hashLen;
        int i4 = (i2 / i3) + 1;
        if (i4 >= 256) {
            throw new DataLengthException("HKDF cannot generate more than 255 blocks of HashLen size");
        }
        if (i2 != 0) {
            this.hMacHash.update(this.currentT, 0, i3);
        }
        HMac hMac = this.hMacHash;
        byte[] bArr = this.info;
        hMac.update(bArr, 0, bArr.length);
        this.hMacHash.update((byte) i4);
        this.hMacHash.doFinal(this.currentT, 0);
    }

    private KeyParameter extract(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            this.hMacHash.init(new KeyParameter(new byte[this.hashLen]));
        } else {
            this.hMacHash.init(new KeyParameter(bArr));
        }
        this.hMacHash.update(bArr2, 0, bArr2.length);
        byte[] bArr3 = new byte[this.hashLen];
        this.hMacHash.doFinal(bArr3, 0);
        return new KeyParameter(bArr3);
    }

    @Override // org.spongycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int i2, int i3) {
        int i4 = this.generatedBytes;
        int i5 = i4 + i3;
        int i6 = this.hashLen;
        if (i5 > i6 * 255) {
            throw new DataLengthException("HKDF may only be used for 255 * HashLen bytes of output");
        }
        if (i4 % i6 == 0) {
            expandNext();
        }
        int i7 = this.generatedBytes;
        int i8 = this.hashLen;
        int i9 = i7 % i8;
        int iMin = Math.min(i8 - (i7 % i8), i3);
        System.arraycopy(this.currentT, i9, bArr, i2, iMin);
        this.generatedBytes += iMin;
        int i10 = i3 - iMin;
        while (true) {
            i2 += iMin;
            if (i10 <= 0) {
                return i3;
            }
            expandNext();
            iMin = Math.min(this.hashLen, i10);
            System.arraycopy(this.currentT, 0, bArr, i2, iMin);
            this.generatedBytes += iMin;
            i10 -= iMin;
        }
    }

    public Digest getDigest() {
        return this.hMacHash.getUnderlyingDigest();
    }

    @Override // org.spongycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        if (!(derivationParameters instanceof HKDFParameters)) {
            throw new IllegalArgumentException("HKDF parameters required for HKDFBytesGenerator");
        }
        HKDFParameters hKDFParameters = (HKDFParameters) derivationParameters;
        if (hKDFParameters.skipExtract()) {
            this.hMacHash.init(new KeyParameter(hKDFParameters.getIKM()));
        } else {
            this.hMacHash.init(extract(hKDFParameters.getSalt(), hKDFParameters.getIKM()));
        }
        this.info = hKDFParameters.getInfo();
        this.generatedBytes = 0;
        this.currentT = new byte[this.hashLen];
    }
}

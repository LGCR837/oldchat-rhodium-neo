package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.MacDerivationFunction;
import org.spongycastle.crypto.params.KDFFeedbackParameters;
import org.spongycastle.crypto.params.KeyParameter;

/* JADX INFO: loaded from: classes.dex */
public class KDFFeedbackBytesGenerator implements MacDerivationFunction {
    private static final BigInteger INTEGER_MAX = BigInteger.valueOf(2147483647L);
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private byte[] fixedInputData;
    private int generatedBytes;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final int f7350h;
    private byte[] ios;
    private byte[] iv;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private byte[] f7351k;
    private int maxSizeExcl;
    private final Mac prf;
    private boolean useCounter;

    public KDFFeedbackBytesGenerator(Mac mac) {
        this.prf = mac;
        int macSize = mac.getMacSize();
        this.f7350h = macSize;
        this.f7351k = new byte[macSize];
    }

    private void generateNext() {
        if (this.generatedBytes == 0) {
            Mac mac = this.prf;
            byte[] bArr = this.iv;
            mac.update(bArr, 0, bArr.length);
        } else {
            Mac mac2 = this.prf;
            byte[] bArr2 = this.f7351k;
            mac2.update(bArr2, 0, bArr2.length);
        }
        if (this.useCounter) {
            int i2 = (this.generatedBytes / this.f7350h) + 1;
            byte[] bArr3 = this.ios;
            int length = bArr3.length;
            if (length != 1) {
                if (length != 2) {
                    if (length != 3) {
                        if (length != 4) {
                            throw new IllegalStateException("Unsupported size of counter i");
                        }
                        bArr3[0] = (byte) (i2 >>> 24);
                    }
                    bArr3[bArr3.length - 3] = (byte) (i2 >>> 16);
                }
                bArr3[bArr3.length - 2] = (byte) (i2 >>> 8);
            }
            bArr3[bArr3.length - 1] = (byte) i2;
            this.prf.update(bArr3, 0, bArr3.length);
        }
        Mac mac3 = this.prf;
        byte[] bArr4 = this.fixedInputData;
        mac3.update(bArr4, 0, bArr4.length);
        this.prf.doFinal(this.f7351k, 0);
    }

    @Override // org.spongycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int i2, int i3) {
        int i4 = this.generatedBytes;
        int i5 = i4 + i3;
        if (i5 < 0 || i5 >= this.maxSizeExcl) {
            throw new DataLengthException("Current KDFCTR may only be used for " + this.maxSizeExcl + " bytes");
        }
        if (i4 % this.f7350h == 0) {
            generateNext();
        }
        int i6 = this.generatedBytes;
        int i7 = this.f7350h;
        int i8 = i6 % i7;
        int iMin = Math.min(i7 - (i6 % i7), i3);
        System.arraycopy(this.f7351k, i8, bArr, i2, iMin);
        this.generatedBytes += iMin;
        int i9 = i3 - iMin;
        while (true) {
            i2 += iMin;
            if (i9 <= 0) {
                return i3;
            }
            generateNext();
            iMin = Math.min(this.f7350h, i9);
            System.arraycopy(this.f7351k, 0, bArr, i2, iMin);
            this.generatedBytes += iMin;
            i9 -= iMin;
        }
    }

    @Override // org.spongycastle.crypto.MacDerivationFunction
    public Mac getMac() {
        return this.prf;
    }

    @Override // org.spongycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        if (!(derivationParameters instanceof KDFFeedbackParameters)) {
            throw new IllegalArgumentException("Wrong type of arguments given");
        }
        KDFFeedbackParameters kDFFeedbackParameters = (KDFFeedbackParameters) derivationParameters;
        this.prf.init(new KeyParameter(kDFFeedbackParameters.getKI()));
        this.fixedInputData = kDFFeedbackParameters.getFixedInputData();
        int r2 = kDFFeedbackParameters.getR();
        this.ios = new byte[r2 / 8];
        if (kDFFeedbackParameters.useCounter()) {
            BigInteger bigIntegerMultiply = TWO.pow(r2).multiply(BigInteger.valueOf(this.f7350h));
            this.maxSizeExcl = bigIntegerMultiply.compareTo(INTEGER_MAX) != 1 ? bigIntegerMultiply.intValue() : Integer.MAX_VALUE;
        } else {
            this.maxSizeExcl = Integer.MAX_VALUE;
        }
        this.iv = kDFFeedbackParameters.getIV();
        this.useCounter = kDFFeedbackParameters.useCounter();
        this.generatedBytes = 0;
    }
}

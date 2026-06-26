package org.spongycastle.crypto.macs;

import java.io.IOException;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.modes.KGCMBlockCipher;
import org.spongycastle.crypto.params.AEADParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

/* JADX INFO: loaded from: classes.dex */
public class KGMac implements Mac {
    private final KGCMBlockCipher cipher;
    private final int macSizeBits;

    public KGMac(KGCMBlockCipher kGCMBlockCipher) {
        this.cipher = kGCMBlockCipher;
        this.macSizeBits = kGCMBlockCipher.getUnderlyingCipher().getBlockSize() * 8;
    }

    @Override // org.spongycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i2) {
        try {
            return this.cipher.doFinal(bArr, i2);
        } catch (InvalidCipherTextException e2) {
            throw new IllegalStateException(e2.toString());
        }
    }

    @Override // org.spongycastle.crypto.Mac
    public String getAlgorithmName() {
        return this.cipher.getUnderlyingCipher().getAlgorithmName() + "-KGMAC";
    }

    @Override // org.spongycastle.crypto.Mac
    public int getMacSize() {
        return this.macSizeBits / 8;
    }

    @Override // org.spongycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) throws IOException {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("KGMAC requires ParametersWithIV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] iv = parametersWithIV.getIV();
        this.cipher.init(true, new AEADParameters((KeyParameter) parametersWithIV.getParameters(), this.macSizeBits, iv));
    }

    @Override // org.spongycastle.crypto.Mac
    public void reset() throws IOException {
        this.cipher.reset();
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte b2) throws IOException {
        this.cipher.processAADByte(b2);
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte[] bArr, int i2, int i3) throws IOException {
        this.cipher.processAADBytes(bArr, i2, i3);
    }

    public KGMac(KGCMBlockCipher kGCMBlockCipher, int i2) {
        this.cipher = kGCMBlockCipher;
        this.macSizeBits = i2;
    }
}

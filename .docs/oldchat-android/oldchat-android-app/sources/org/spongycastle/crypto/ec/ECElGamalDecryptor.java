package org.spongycastle.crypto.ec;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.math.ec.ECPoint;

/* JADX INFO: loaded from: classes.dex */
public class ECElGamalDecryptor implements ECDecryptor {
    private ECPrivateKeyParameters key;

    @Override // org.spongycastle.crypto.ec.ECDecryptor
    public ECPoint decrypt(ECPair eCPair) {
        if (this.key == null) {
            throw new IllegalStateException("ECElGamalDecryptor not initialised");
        }
        return eCPair.getY().subtract(eCPair.getX().multiply(this.key.getD())).normalize();
    }

    @Override // org.spongycastle.crypto.ec.ECDecryptor
    public void init(CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ECPrivateKeyParameters)) {
            throw new IllegalArgumentException("ECPrivateKeyParameters are required for decryption.");
        }
        this.key = (ECPrivateKeyParameters) cipherParameters;
    }
}

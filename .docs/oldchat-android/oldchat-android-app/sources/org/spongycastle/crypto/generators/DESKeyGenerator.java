package org.spongycastle.crypto.generators;

import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.DESParameters;

/* JADX INFO: loaded from: classes.dex */
public class DESKeyGenerator extends CipherKeyGenerator {
    @Override // org.spongycastle.crypto.CipherKeyGenerator
    public byte[] generateKey() {
        byte[] bArr = new byte[8];
        do {
            this.random.nextBytes(bArr);
            DESParameters.setOddParity(bArr);
        } while (DESParameters.isWeakKey(bArr, 0));
        return bArr;
    }

    @Override // org.spongycastle.crypto.CipherKeyGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        super.init(keyGenerationParameters);
        int i2 = this.strength;
        if (i2 == 0 || i2 == 7) {
            this.strength = 8;
        } else if (i2 != 8) {
            throw new IllegalArgumentException("DES key must be 64 bits long.");
        }
    }
}

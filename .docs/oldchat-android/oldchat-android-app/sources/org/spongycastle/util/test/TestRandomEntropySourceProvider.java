package org.spongycastle.util.test;

import java.security.SecureRandom;
import org.spongycastle.crypto.prng.EntropySource;
import org.spongycastle.crypto.prng.EntropySourceProvider;

/* JADX INFO: loaded from: classes.dex */
public class TestRandomEntropySourceProvider implements EntropySourceProvider {
    private final boolean _predictionResistant;
    private final SecureRandom _sr = new SecureRandom();

    public TestRandomEntropySourceProvider(boolean z2) {
        this._predictionResistant = z2;
    }

    @Override // org.spongycastle.crypto.prng.EntropySourceProvider
    public EntropySource get(final int i2) {
        return new EntropySource() { // from class: org.spongycastle.util.test.TestRandomEntropySourceProvider.1
            @Override // org.spongycastle.crypto.prng.EntropySource
            public int entropySize() {
                return i2;
            }

            @Override // org.spongycastle.crypto.prng.EntropySource
            public byte[] getEntropy() {
                byte[] bArr = new byte[(i2 + 7) / 8];
                TestRandomEntropySourceProvider.this._sr.nextBytes(bArr);
                return bArr;
            }

            @Override // org.spongycastle.crypto.prng.EntropySource
            public boolean isPredictionResistant() {
                return TestRandomEntropySourceProvider.this._predictionResistant;
            }
        };
    }
}

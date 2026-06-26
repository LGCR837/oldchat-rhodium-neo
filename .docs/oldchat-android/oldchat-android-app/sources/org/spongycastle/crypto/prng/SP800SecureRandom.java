package org.spongycastle.crypto.prng;

import java.security.SecureRandom;
import org.spongycastle.crypto.prng.drbg.SP80090DRBG;

/* JADX INFO: loaded from: classes.dex */
public class SP800SecureRandom extends SecureRandom {
    private SP80090DRBG drbg;
    private final DRBGProvider drbgProvider;
    private final EntropySource entropySource;
    private final boolean predictionResistant;
    private final SecureRandom randomSource;

    public SP800SecureRandom(SecureRandom secureRandom, EntropySource entropySource, DRBGProvider dRBGProvider, boolean z2) {
        this.randomSource = secureRandom;
        this.entropySource = entropySource;
        this.drbgProvider = dRBGProvider;
        this.predictionResistant = z2;
    }

    @Override // java.security.SecureRandom
    public byte[] generateSeed(int i2) {
        return EntropyUtil.generateSeed(this.entropySource, i2);
    }

    @Override // java.security.SecureRandom, java.util.Random
    public void nextBytes(byte[] bArr) {
        synchronized (this) {
            try {
                if (this.drbg == null) {
                    this.drbg = this.drbgProvider.get(this.entropySource);
                }
                if (this.drbg.generate(bArr, null, this.predictionResistant) < 0) {
                    this.drbg.reseed(null);
                    this.drbg.generate(bArr, null, this.predictionResistant);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void reseed(byte[] bArr) {
        synchronized (this) {
            try {
                if (this.drbg == null) {
                    this.drbg = this.drbgProvider.get(this.entropySource);
                }
                this.drbg.reseed(bArr);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // java.security.SecureRandom
    public void setSeed(byte[] bArr) {
        synchronized (this) {
            try {
                SecureRandom secureRandom = this.randomSource;
                if (secureRandom != null) {
                    secureRandom.setSeed(bArr);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // java.security.SecureRandom, java.util.Random
    public void setSeed(long j2) {
        synchronized (this) {
            try {
                SecureRandom secureRandom = this.randomSource;
                if (secureRandom != null) {
                    secureRandom.setSeed(j2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

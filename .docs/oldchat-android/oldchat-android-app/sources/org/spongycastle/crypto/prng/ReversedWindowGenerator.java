package org.spongycastle.crypto.prng;

/* JADX INFO: loaded from: classes.dex */
public class ReversedWindowGenerator implements RandomGenerator {
    private final RandomGenerator generator;
    private byte[] window;
    private int windowCount;

    public ReversedWindowGenerator(RandomGenerator randomGenerator, int i2) {
        if (randomGenerator == null) {
            throw new IllegalArgumentException("generator cannot be null");
        }
        if (i2 < 2) {
            throw new IllegalArgumentException("windowSize must be at least 2");
        }
        this.generator = randomGenerator;
        this.window = new byte[i2];
    }

    private void doNextBytes(byte[] bArr, int i2, int i3) {
        synchronized (this) {
            for (int i4 = 0; i4 < i3; i4++) {
                try {
                    if (this.windowCount < 1) {
                        RandomGenerator randomGenerator = this.generator;
                        byte[] bArr2 = this.window;
                        randomGenerator.nextBytes(bArr2, 0, bArr2.length);
                        this.windowCount = this.window.length;
                    }
                    byte[] bArr3 = this.window;
                    int i5 = this.windowCount - 1;
                    this.windowCount = i5;
                    bArr[i4 + i2] = bArr3[i5];
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    @Override // org.spongycastle.crypto.prng.RandomGenerator
    public void addSeedMaterial(byte[] bArr) {
        synchronized (this) {
            this.windowCount = 0;
            this.generator.addSeedMaterial(bArr);
        }
    }

    @Override // org.spongycastle.crypto.prng.RandomGenerator
    public void nextBytes(byte[] bArr) {
        doNextBytes(bArr, 0, bArr.length);
    }

    @Override // org.spongycastle.crypto.prng.RandomGenerator
    public void nextBytes(byte[] bArr, int i2, int i3) {
        doNextBytes(bArr, i2, i3);
    }

    @Override // org.spongycastle.crypto.prng.RandomGenerator
    public void addSeedMaterial(long j2) {
        synchronized (this) {
            this.windowCount = 0;
            this.generator.addSeedMaterial(j2);
        }
    }
}

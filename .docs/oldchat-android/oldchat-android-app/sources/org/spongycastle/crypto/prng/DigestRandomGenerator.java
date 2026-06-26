package org.spongycastle.crypto.prng;

import org.spongycastle.crypto.Digest;

/* JADX INFO: loaded from: classes.dex */
public class DigestRandomGenerator implements RandomGenerator {
    private static long CYCLE_COUNT = 10;
    private Digest digest;
    private byte[] seed;
    private byte[] state;
    private long seedCounter = 1;
    private long stateCounter = 1;

    public DigestRandomGenerator(Digest digest) {
        this.digest = digest;
        this.seed = new byte[digest.getDigestSize()];
        this.state = new byte[digest.getDigestSize()];
    }

    private void cycleSeed() {
        digestUpdate(this.seed);
        long j2 = this.seedCounter;
        this.seedCounter = 1 + j2;
        digestAddCounter(j2);
        digestDoFinal(this.seed);
    }

    private void digestAddCounter(long j2) {
        for (int i2 = 0; i2 != 8; i2++) {
            this.digest.update((byte) j2);
            j2 >>>= 8;
        }
    }

    private void digestDoFinal(byte[] bArr) {
        this.digest.doFinal(bArr, 0);
    }

    private void digestUpdate(byte[] bArr) {
        this.digest.update(bArr, 0, bArr.length);
    }

    private void generateState() {
        long j2 = this.stateCounter;
        this.stateCounter = 1 + j2;
        digestAddCounter(j2);
        digestUpdate(this.state);
        digestUpdate(this.seed);
        digestDoFinal(this.state);
        if (this.stateCounter % CYCLE_COUNT == 0) {
            cycleSeed();
        }
    }

    @Override // org.spongycastle.crypto.prng.RandomGenerator
    public void addSeedMaterial(byte[] bArr) {
        synchronized (this) {
            digestUpdate(bArr);
            digestUpdate(this.seed);
            digestDoFinal(this.seed);
        }
    }

    @Override // org.spongycastle.crypto.prng.RandomGenerator
    public void nextBytes(byte[] bArr) {
        nextBytes(bArr, 0, bArr.length);
    }

    @Override // org.spongycastle.crypto.prng.RandomGenerator
    public void nextBytes(byte[] bArr, int i2, int i3) {
        synchronized (this) {
            try {
                generateState();
                int i4 = i3 + i2;
                int i5 = 0;
                while (i2 != i4) {
                    if (i5 == this.state.length) {
                        generateState();
                        i5 = 0;
                    }
                    bArr[i2] = this.state[i5];
                    i2++;
                    i5++;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // org.spongycastle.crypto.prng.RandomGenerator
    public void addSeedMaterial(long j2) {
        synchronized (this) {
            digestAddCounter(j2);
            digestUpdate(this.seed);
            digestDoFinal(this.seed);
        }
    }
}

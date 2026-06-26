package org.spongycastle.crypto.prng;

/* JADX INFO: loaded from: classes.dex */
public class ThreadedSeedGenerator {

    public class SeedGenerator implements Runnable {
        private volatile int counter;
        private volatile boolean stop;

        private SeedGenerator() {
            this.counter = 0;
            this.stop = false;
        }

        public byte[] generateSeed(int i2, boolean z2) {
            Thread thread = new Thread(this);
            byte[] bArr = new byte[i2];
            this.counter = 0;
            this.stop = false;
            thread.start();
            if (!z2) {
                i2 *= 8;
            }
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                while (this.counter == i3) {
                    try {
                        Thread.sleep(1L);
                    } catch (InterruptedException unused) {
                    }
                }
                i3 = this.counter;
                if (z2) {
                    bArr[i4] = (byte) (i3 & 255);
                } else {
                    int i5 = i4 / 8;
                    bArr[i5] = (byte) ((bArr[i5] << 1) | (i3 & 1));
                }
            }
            this.stop = true;
            return bArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            while (!this.stop) {
                this.counter++;
            }
        }
    }

    public byte[] generateSeed(int i2, boolean z2) {
        return new SeedGenerator().generateSeed(i2, z2);
    }
}

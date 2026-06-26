package org.spongycastle.crypto.prng;

/* JADX INFO: loaded from: classes.dex */
public class EntropyUtil {
    public static byte[] generateSeed(EntropySource entropySource, int i2) {
        byte[] bArr = new byte[i2];
        if (i2 * 8 <= entropySource.entropySize()) {
            System.arraycopy(entropySource.getEntropy(), 0, bArr, 0, i2);
        } else {
            int iEntropySize = entropySource.entropySize() / 8;
            for (int i3 = 0; i3 < i2; i3 += iEntropySize) {
                byte[] entropy = entropySource.getEntropy();
                int i4 = i2 - i3;
                if (entropy.length <= i4) {
                    System.arraycopy(entropy, 0, bArr, i3, entropy.length);
                } else {
                    System.arraycopy(entropy, 0, bArr, i3, i4);
                }
            }
        }
        return bArr;
    }
}

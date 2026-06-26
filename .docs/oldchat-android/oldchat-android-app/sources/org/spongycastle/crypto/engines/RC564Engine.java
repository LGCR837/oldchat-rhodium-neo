package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.RC5Parameters;

/* JADX INFO: loaded from: classes.dex */
public class RC564Engine implements BlockCipher {
    private static final long P64 = -5196783011329398165L;
    private static final long Q64 = -7046029254386353131L;
    private static final int bytesPerWord = 8;
    private static final int wordSize = 64;
    private boolean forEncryption;
    private int _noRounds = 12;
    private long[] _S = null;

    private long bytesToWord(byte[] bArr, int i2) {
        long j2 = 0;
        for (int i3 = 7; i3 >= 0; i3--) {
            j2 = (j2 << 8) + ((long) (bArr[i3 + i2] & 255));
        }
        return j2;
    }

    private int decryptBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        long jBytesToWord = bytesToWord(bArr, i2);
        long jBytesToWord2 = bytesToWord(bArr, i2 + 8);
        for (int i4 = this._noRounds; i4 >= 1; i4--) {
            int i5 = i4 * 2;
            jBytesToWord2 = rotateRight(jBytesToWord2 - this._S[i5 + 1], jBytesToWord) ^ jBytesToWord;
            jBytesToWord = rotateRight(jBytesToWord - this._S[i5], jBytesToWord2) ^ jBytesToWord2;
        }
        wordToBytes(jBytesToWord - this._S[0], bArr2, i3);
        wordToBytes(jBytesToWord2 - this._S[1], bArr2, i3 + 8);
        return 16;
    }

    private int encryptBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        long jBytesToWord = bytesToWord(bArr, i2) + this._S[0];
        long jBytesToWord2 = bytesToWord(bArr, i2 + 8) + this._S[1];
        for (int i4 = 1; i4 <= this._noRounds; i4++) {
            int i5 = i4 * 2;
            jBytesToWord = rotateLeft(jBytesToWord ^ jBytesToWord2, jBytesToWord2) + this._S[i5];
            jBytesToWord2 = rotateLeft(jBytesToWord2 ^ jBytesToWord, jBytesToWord) + this._S[i5 + 1];
        }
        wordToBytes(jBytesToWord, bArr2, i3);
        wordToBytes(jBytesToWord2, bArr2, i3 + 8);
        return 16;
    }

    private long rotateLeft(long j2, long j3) {
        long j4 = j3 & 63;
        return (j2 >>> ((int) (64 - j4))) | (j2 << ((int) j4));
    }

    private long rotateRight(long j2, long j3) {
        long j4 = j3 & 63;
        return (j2 << ((int) (64 - j4))) | (j2 >>> ((int) j4));
    }

    private void setKey(byte[] bArr) {
        long[] jArr;
        int length = (bArr.length + 7) / 8;
        long[] jArr2 = new long[length];
        for (int i2 = 0; i2 != bArr.length; i2++) {
            int i3 = i2 / 8;
            jArr2[i3] = jArr2[i3] + (((long) (bArr[i2] & 255)) << ((i2 % 8) * 8));
        }
        long[] jArr3 = new long[(this._noRounds + 1) * 2];
        this._S = jArr3;
        jArr3[0] = -5196783011329398165L;
        int i4 = 1;
        while (true) {
            jArr = this._S;
            if (i4 >= jArr.length) {
                break;
            }
            jArr[i4] = jArr[i4 - 1] + Q64;
            i4++;
        }
        int length2 = length > jArr.length ? length * 3 : jArr.length * 3;
        long jRotateLeft = 0;
        long jRotateLeft2 = 0;
        int length3 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < length2; i6++) {
            long[] jArr4 = this._S;
            jRotateLeft = rotateLeft(jArr4[length3] + jRotateLeft + jRotateLeft2, 3L);
            jArr4[length3] = jRotateLeft;
            jRotateLeft2 = rotateLeft(jArr2[i5] + jRotateLeft + jRotateLeft2, jRotateLeft2 + jRotateLeft);
            jArr2[i5] = jRotateLeft2;
            length3 = (length3 + 1) % this._S.length;
            i5 = (i5 + 1) % length;
        }
    }

    private void wordToBytes(long j2, byte[] bArr, int i2) {
        for (int i3 = 0; i3 < 8; i3++) {
            bArr[i3 + i2] = (byte) j2;
            j2 >>>= 8;
        }
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "RC5-64";
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void init(boolean z2, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof RC5Parameters)) {
            throw new IllegalArgumentException("invalid parameter passed to RC564 init - " + cipherParameters.getClass().getName());
        }
        RC5Parameters rC5Parameters = (RC5Parameters) cipherParameters;
        this.forEncryption = z2;
        this._noRounds = rC5Parameters.getRounds();
        setKey(rC5Parameters.getKey());
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        return this.forEncryption ? encryptBlock(bArr, i2, bArr2, i3) : decryptBlock(bArr, i2, bArr2, i3);
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void reset() {
    }
}

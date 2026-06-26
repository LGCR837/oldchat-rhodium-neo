package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.RC5Parameters;

/* JADX INFO: loaded from: classes.dex */
public class RC532Engine implements BlockCipher {
    private static final int P32 = -1209970333;
    private static final int Q32 = -1640531527;
    private boolean forEncryption;
    private int _noRounds = 12;
    private int[] _S = null;

    private int bytesToWord(byte[] bArr, int i2) {
        return ((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
    }

    private int decryptBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int iBytesToWord = bytesToWord(bArr, i2);
        int iBytesToWord2 = bytesToWord(bArr, i2 + 4);
        for (int i4 = this._noRounds; i4 >= 1; i4--) {
            int i5 = i4 * 2;
            iBytesToWord2 = rotateRight(iBytesToWord2 - this._S[i5 + 1], iBytesToWord) ^ iBytesToWord;
            iBytesToWord = rotateRight(iBytesToWord - this._S[i5], iBytesToWord2) ^ iBytesToWord2;
        }
        wordToBytes(iBytesToWord - this._S[0], bArr2, i3);
        wordToBytes(iBytesToWord2 - this._S[1], bArr2, i3 + 4);
        return 8;
    }

    private int encryptBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int iBytesToWord = bytesToWord(bArr, i2) + this._S[0];
        int iBytesToWord2 = bytesToWord(bArr, i2 + 4) + this._S[1];
        for (int i4 = 1; i4 <= this._noRounds; i4++) {
            int i5 = i4 * 2;
            iBytesToWord = rotateLeft(iBytesToWord ^ iBytesToWord2, iBytesToWord2) + this._S[i5];
            iBytesToWord2 = rotateLeft(iBytesToWord2 ^ iBytesToWord, iBytesToWord) + this._S[i5 + 1];
        }
        wordToBytes(iBytesToWord, bArr2, i3);
        wordToBytes(iBytesToWord2, bArr2, i3 + 4);
        return 8;
    }

    private int rotateLeft(int i2, int i3) {
        int i4 = i3 & 31;
        return (i2 >>> (32 - i4)) | (i2 << i4);
    }

    private int rotateRight(int i2, int i3) {
        int i4 = i3 & 31;
        return (i2 << (32 - i4)) | (i2 >>> i4);
    }

    private void setKey(byte[] bArr) {
        int[] iArr;
        int length = (bArr.length + 3) / 4;
        int[] iArr2 = new int[length];
        for (int i2 = 0; i2 != bArr.length; i2++) {
            int i3 = i2 / 4;
            iArr2[i3] = iArr2[i3] + ((bArr[i2] & 255) << ((i2 % 4) * 8));
        }
        int[] iArr3 = new int[(this._noRounds + 1) * 2];
        this._S = iArr3;
        iArr3[0] = P32;
        int i4 = 1;
        while (true) {
            iArr = this._S;
            if (i4 >= iArr.length) {
                break;
            }
            iArr[i4] = iArr[i4 - 1] + Q32;
            i4++;
        }
        int length2 = length > iArr.length ? length * 3 : iArr.length * 3;
        int length3 = 0;
        int iRotateLeft = 0;
        int iRotateLeft2 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < length2; i6++) {
            int[] iArr4 = this._S;
            iRotateLeft = rotateLeft(iArr4[length3] + iRotateLeft + iRotateLeft2, 3);
            iArr4[length3] = iRotateLeft;
            iRotateLeft2 = rotateLeft(iArr2[i5] + iRotateLeft + iRotateLeft2, iRotateLeft2 + iRotateLeft);
            iArr2[i5] = iRotateLeft2;
            length3 = (length3 + 1) % this._S.length;
            i5 = (i5 + 1) % length;
        }
    }

    private void wordToBytes(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) i2;
        bArr[i3 + 1] = (byte) (i2 >> 8);
        bArr[i3 + 2] = (byte) (i2 >> 16);
        bArr[i3 + 3] = (byte) (i2 >> 24);
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "RC5-32";
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 8;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void init(boolean z2, CipherParameters cipherParameters) {
        if (cipherParameters instanceof RC5Parameters) {
            RC5Parameters rC5Parameters = (RC5Parameters) cipherParameters;
            this._noRounds = rC5Parameters.getRounds();
            setKey(rC5Parameters.getKey());
        } else {
            if (!(cipherParameters instanceof KeyParameter)) {
                throw new IllegalArgumentException("invalid parameter passed to RC532 init - " + cipherParameters.getClass().getName());
            }
            setKey(((KeyParameter) cipherParameters).getKey());
        }
        this.forEncryption = z2;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        return this.forEncryption ? encryptBlock(bArr, i2, bArr2, i3) : decryptBlock(bArr, i2, bArr2, i3);
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void reset() {
    }
}

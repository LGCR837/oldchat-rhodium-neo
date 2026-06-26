package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;

/* JADX INFO: loaded from: classes.dex */
public class RC6Engine implements BlockCipher {
    private static final int LGW = 5;
    private static final int P32 = -1209970333;
    private static final int Q32 = -1640531527;
    private static final int _noRounds = 20;
    private static final int bytesPerWord = 4;
    private static final int wordSize = 32;
    private int[] _S = null;
    private boolean forEncryption;

    private int bytesToWord(byte[] bArr, int i2) {
        int i3 = 0;
        for (int i4 = 3; i4 >= 0; i4--) {
            i3 = (i3 << 8) + (bArr[i4 + i2] & 255);
        }
        return i3;
    }

    private int decryptBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int iBytesToWord = bytesToWord(bArr, i2);
        int iBytesToWord2 = bytesToWord(bArr, i2 + 4);
        int iBytesToWord3 = bytesToWord(bArr, i2 + 8);
        int iBytesToWord4 = bytesToWord(bArr, i2 + 12);
        int[] iArr = this._S;
        int i4 = iBytesToWord3 - iArr[43];
        int iRotateRight = iBytesToWord - iArr[42];
        int i5 = 20;
        while (i5 >= 1) {
            int iRotateLeft = rotateLeft(((iRotateRight * 2) + 1) * iRotateRight, 5);
            int iRotateLeft2 = rotateLeft(((i4 * 2) + 1) * i4, 5);
            int i6 = i5 * 2;
            int iRotateRight2 = rotateRight(iBytesToWord2 - this._S[i6 + 1], iRotateLeft) ^ iRotateLeft2;
            i5--;
            int i7 = iRotateRight;
            iRotateRight = rotateRight(iBytesToWord4 - this._S[i6], iRotateLeft2) ^ iRotateLeft;
            iBytesToWord4 = i4;
            i4 = iRotateRight2;
            iBytesToWord2 = i7;
        }
        int[] iArr2 = this._S;
        int i8 = iBytesToWord4 - iArr2[1];
        int i9 = iBytesToWord2 - iArr2[0];
        wordToBytes(iRotateRight, bArr2, i3);
        wordToBytes(i9, bArr2, i3 + 4);
        wordToBytes(i4, bArr2, i3 + 8);
        wordToBytes(i8, bArr2, i3 + 12);
        return 16;
    }

    private int encryptBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int iBytesToWord = bytesToWord(bArr, i2);
        int iBytesToWord2 = bytesToWord(bArr, i2 + 4);
        int iBytesToWord3 = bytesToWord(bArr, i2 + 8);
        int iBytesToWord4 = bytesToWord(bArr, i2 + 12);
        int[] iArr = this._S;
        int i4 = iBytesToWord2 + iArr[0];
        int i5 = iBytesToWord4 + iArr[1];
        int i6 = 1;
        while (i6 <= 20) {
            int iRotateLeft = rotateLeft(((i4 * 2) + 1) * i4, 5);
            int iRotateLeft2 = rotateLeft(((i5 * 2) + 1) * i5, 5);
            int i7 = i6 * 2;
            int iRotateLeft3 = rotateLeft(iBytesToWord ^ iRotateLeft, iRotateLeft2) + this._S[i7];
            int iRotateLeft4 = rotateLeft(iBytesToWord3 ^ iRotateLeft2, iRotateLeft) + this._S[i7 + 1];
            i6++;
            iBytesToWord3 = i5;
            i5 = iRotateLeft3;
            iBytesToWord = i4;
            i4 = iRotateLeft4;
        }
        int[] iArr2 = this._S;
        int i8 = iBytesToWord + iArr2[42];
        int i9 = iBytesToWord3 + iArr2[43];
        wordToBytes(i8, bArr2, i3);
        wordToBytes(i4, bArr2, i3 + 4);
        wordToBytes(i9, bArr2, i3 + 8);
        wordToBytes(i5, bArr2, i3 + 12);
        return 16;
    }

    private int rotateLeft(int i2, int i3) {
        return (i2 >>> (-i3)) | (i2 << i3);
    }

    private int rotateRight(int i2, int i3) {
        return (i2 << (-i3)) | (i2 >>> i3);
    }

    private void setKey(byte[] bArr) {
        int[] iArr;
        int length = bArr.length;
        int length2 = (bArr.length + 3) / 4;
        int[] iArr2 = new int[length2];
        for (int length3 = bArr.length - 1; length3 >= 0; length3--) {
            int i2 = length3 / 4;
            iArr2[i2] = (iArr2[i2] << 8) + (bArr[length3] & 255);
        }
        int[] iArr3 = new int[44];
        this._S = iArr3;
        iArr3[0] = P32;
        int i3 = 1;
        while (true) {
            iArr = this._S;
            if (i3 >= iArr.length) {
                break;
            }
            iArr[i3] = iArr[i3 - 1] + Q32;
            i3++;
        }
        int length4 = length2 > iArr.length ? length2 * 3 : iArr.length * 3;
        int length5 = 0;
        int iRotateLeft = 0;
        int iRotateLeft2 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < length4; i5++) {
            int[] iArr4 = this._S;
            iRotateLeft = rotateLeft(iArr4[length5] + iRotateLeft + iRotateLeft2, 3);
            iArr4[length5] = iRotateLeft;
            iRotateLeft2 = rotateLeft(iArr2[i4] + iRotateLeft + iRotateLeft2, iRotateLeft2 + iRotateLeft);
            iArr2[i4] = iRotateLeft2;
            length5 = (length5 + 1) % this._S.length;
            i4 = (i4 + 1) % length2;
        }
    }

    private void wordToBytes(int i2, byte[] bArr, int i3) {
        for (int i4 = 0; i4 < 4; i4++) {
            bArr[i4 + i3] = (byte) i2;
            i2 >>>= 8;
        }
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "RC6";
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void init(boolean z2, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.forEncryption = z2;
            setKey(((KeyParameter) cipherParameters).getKey());
        } else {
            throw new IllegalArgumentException("invalid parameter passed to RC6 init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int blockSize = getBlockSize();
        if (this._S == null) {
            throw new IllegalStateException("RC6 engine not initialised");
        }
        if (i2 + blockSize > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (blockSize + i3 <= bArr2.length) {
            return this.forEncryption ? encryptBlock(bArr, i2, bArr2, i3) : decryptBlock(bArr, i2, bArr2, i3);
        }
        throw new OutputLengthException("output buffer too short");
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void reset() {
    }
}

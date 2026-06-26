package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;

/* JADX INFO: loaded from: classes.dex */
public class Shacal2Engine implements BlockCipher {
    private static final int BLOCK_SIZE = 32;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    private static final int[] f7334K = {1116352408, 1899447441, -1245643825, -373957723, 961987163, 1508970993, -1841331548, -1424204075, -670586216, 310598401, 607225278, 1426881987, 1925078388, -2132889090, -1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 770255983, 1249150122, 1555081692, 1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259, -1564481375, -1474664885, -1035236496, -949202525, -778901479, -694614492, -200395387, 275423344, 430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, -2067236844, -1933114872, -1866530822, -1538233109, -1090935817, -965641998};
    private static final int ROUNDS = 64;
    private boolean forEncryption = false;
    private int[] workingKey = null;

    private void byteBlockToInts(byte[] bArr, int[] iArr, int i2, int i3) {
        while (i3 < 8) {
            int i4 = ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2] & 255) << 24);
            int i5 = i2 + 3;
            int i6 = ((bArr[i2 + 2] & 255) << 8) | i4;
            i2 += 4;
            iArr[i3] = i6 | (bArr[i5] & 255);
            i3++;
        }
    }

    private void bytes2ints(byte[] bArr, int[] iArr, int i2, int i3) {
        while (i3 < bArr.length / 4) {
            int i4 = ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2] & 255) << 24);
            int i5 = i2 + 3;
            int i6 = i4 | ((bArr[i2 + 2] & 255) << 8);
            i2 += 4;
            iArr[i3] = i6 | (bArr[i5] & 255);
            i3++;
        }
    }

    private void decryptBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int[] iArr = new int[8];
        byteBlockToInts(bArr, iArr, i2, 0);
        for (int i4 = 63; i4 > -1; i4--) {
            int i5 = iArr[0];
            int i6 = iArr[1];
            int i7 = iArr[2];
            int i8 = iArr[3];
            int i9 = (i5 - ((((i6 >>> 2) | (i6 << (-2))) ^ ((i6 >>> 13) | (i6 << (-13)))) ^ ((i6 >>> 22) | (i6 << (-22))))) - (((i6 & i7) ^ (i6 & i8)) ^ (i7 & i8));
            iArr[0] = i6;
            iArr[1] = i7;
            iArr[2] = i8;
            iArr[3] = iArr[4] - i9;
            int i10 = iArr[5];
            iArr[4] = i10;
            int i11 = iArr[6];
            iArr[5] = i11;
            int i12 = iArr[7];
            iArr[6] = i12;
            iArr[7] = (((i9 - f7334K[i4]) - this.workingKey[i4]) - ((((i10 >>> 6) | (i10 << (-6))) ^ ((i10 >>> 11) | (i10 << (-11)))) ^ ((i10 >>> 25) | (i10 << (-25))))) - ((((-1) ^ i10) & i12) ^ (i10 & i11));
        }
        ints2bytes(iArr, bArr2, i3);
    }

    private void encryptBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int[] iArr = new int[8];
        byteBlockToInts(bArr, iArr, i2, 0);
        for (int i4 = 0; i4 < 64; i4++) {
            int i5 = iArr[4];
            int i6 = iArr[5];
            int i7 = iArr[6];
            int i8 = ((((i5 >>> 6) | (i5 << (-6))) ^ ((i5 >>> 11) | (i5 << (-11)))) ^ ((i5 >>> 25) | (i5 << (-25)))) + ((i5 & i6) ^ ((i5 ^ (-1)) & i7)) + iArr[7] + f7334K[i4] + this.workingKey[i4];
            iArr[7] = i7;
            iArr[6] = i6;
            iArr[5] = i5;
            iArr[4] = iArr[3] + i8;
            int i9 = iArr[2];
            iArr[3] = i9;
            int i10 = iArr[1];
            iArr[2] = i10;
            int i11 = iArr[0];
            iArr[1] = i11;
            iArr[0] = i8 + ((((i11 >>> 2) | (i11 << (-2))) ^ ((i11 >>> 13) | (i11 << (-13)))) ^ ((i11 >>> 22) | (i11 << (-22)))) + (((i11 & i9) ^ (i11 & i10)) ^ (i10 & i9));
        }
        ints2bytes(iArr, bArr2, i3);
    }

    private void ints2bytes(int[] iArr, byte[] bArr, int i2) {
        for (int i3 : iArr) {
            bArr[i2] = (byte) (i3 >>> 24);
            bArr[i2 + 1] = (byte) (i3 >>> 16);
            int i4 = i2 + 3;
            bArr[i2 + 2] = (byte) (i3 >>> 8);
            i2 += 4;
            bArr[i4] = (byte) i3;
        }
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Shacal2";
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 32;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void init(boolean z2, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("only simple KeyParameter expected.");
        }
        this.forEncryption = z2;
        this.workingKey = new int[64];
        setKey(((KeyParameter) cipherParameters).getKey());
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        if (this.workingKey == null) {
            throw new IllegalStateException("Shacal2 not initialised");
        }
        if (i2 + 32 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + 32 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.forEncryption) {
            encryptBlock(bArr, i2, bArr2, i3);
            return 32;
        }
        decryptBlock(bArr, i2, bArr2, i3);
        return 32;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void reset() {
    }

    public void setKey(byte[] bArr) {
        if (bArr.length != 0 && bArr.length <= 64) {
            if (bArr.length >= 16 && bArr.length % 8 == 0) {
                bytes2ints(bArr, this.workingKey, 0, 0);
                for (int i2 = 16; i2 < 64; i2++) {
                    int[] iArr = this.workingKey;
                    int i3 = iArr[i2 - 2];
                    int i4 = ((i3 >>> 10) ^ (((i3 >>> 17) | (i3 << (-17))) ^ ((i3 >>> 19) | (i3 << (-19))))) + iArr[i2 - 7];
                    int i5 = iArr[i2 - 15];
                    iArr[i2] = i4 + ((i5 >>> 3) ^ (((i5 >>> 7) | (i5 << (-7))) ^ ((i5 >>> 18) | (i5 << (-18))))) + iArr[i2 - 16];
                }
                return;
            }
        }
        throw new IllegalArgumentException("Shacal2-key must be 16 - 64 bytes and multiple of 8");
    }
}

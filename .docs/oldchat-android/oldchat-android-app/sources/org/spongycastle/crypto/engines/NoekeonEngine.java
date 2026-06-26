package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: loaded from: classes.dex */
public class NoekeonEngine implements BlockCipher {
    private static final int genericSize = 16;
    private static final int[] nullVector = {0, 0, 0, 0};
    private static final int[] roundConstants = {128, 27, 54, CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA256, 216, CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384, 77, CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA, 47, 94, 188, 99, 198, CipherSuite.TLS_DH_DSS_WITH_SEED_CBC_SHA, 53, CipherSuite.TLS_DHE_DSS_WITH_AES_256_CBC_SHA256, 212};
    private boolean _forEncryption;
    private int[] state = new int[4];
    private int[] subKeys = new int[4];
    private int[] decryptKeys = new int[4];
    private boolean _initialised = false;

    private int bytesToIntBig(byte[] bArr, int i2) {
        int i3 = ((bArr[i2 + 1] & 255) << 16) | (bArr[i2] << 24);
        return (bArr[i2 + 3] & 255) | i3 | ((bArr[i2 + 2] & 255) << 8);
    }

    private int decryptBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        this.state[0] = bytesToIntBig(bArr, i2);
        this.state[1] = bytesToIntBig(bArr, i2 + 4);
        this.state[2] = bytesToIntBig(bArr, i2 + 8);
        this.state[3] = bytesToIntBig(bArr, i2 + 12);
        int[] iArr = this.subKeys;
        System.arraycopy(iArr, 0, this.decryptKeys, 0, iArr.length);
        theta(this.decryptKeys, nullVector);
        int i4 = 16;
        while (i4 > 0) {
            theta(this.state, this.decryptKeys);
            int[] iArr2 = this.state;
            iArr2[0] = iArr2[0] ^ roundConstants[i4];
            pi1(iArr2);
            gamma(this.state);
            pi2(this.state);
            i4--;
        }
        theta(this.state, this.decryptKeys);
        int[] iArr3 = this.state;
        int i5 = roundConstants[i4] ^ iArr3[0];
        iArr3[0] = i5;
        intToBytesBig(i5, bArr2, i3);
        intToBytesBig(this.state[1], bArr2, i3 + 4);
        intToBytesBig(this.state[2], bArr2, i3 + 8);
        intToBytesBig(this.state[3], bArr2, i3 + 12);
        return 16;
    }

    private int encryptBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        this.state[0] = bytesToIntBig(bArr, i2);
        this.state[1] = bytesToIntBig(bArr, i2 + 4);
        this.state[2] = bytesToIntBig(bArr, i2 + 8);
        this.state[3] = bytesToIntBig(bArr, i2 + 12);
        int i4 = 0;
        while (i4 < 16) {
            int[] iArr = this.state;
            iArr[0] = iArr[0] ^ roundConstants[i4];
            theta(iArr, this.subKeys);
            pi1(this.state);
            gamma(this.state);
            pi2(this.state);
            i4++;
        }
        int[] iArr2 = this.state;
        iArr2[0] = roundConstants[i4] ^ iArr2[0];
        theta(iArr2, this.subKeys);
        intToBytesBig(this.state[0], bArr2, i3);
        intToBytesBig(this.state[1], bArr2, i3 + 4);
        intToBytesBig(this.state[2], bArr2, i3 + 8);
        intToBytesBig(this.state[3], bArr2, i3 + 12);
        return 16;
    }

    private void gamma(int[] iArr) {
        int i2 = iArr[1];
        int i3 = iArr[3];
        int i4 = iArr[2];
        int i5 = i2 ^ ((i3 ^ (-1)) & (i4 ^ (-1)));
        iArr[1] = i5;
        int i6 = iArr[0] ^ (i4 & i5);
        iArr[0] = i6;
        iArr[3] = i6;
        iArr[0] = i3;
        int i7 = ((i3 ^ i5) ^ i6) ^ i4;
        iArr[2] = i7;
        int i8 = i5 ^ ((i6 ^ (-1)) & (i7 ^ (-1)));
        iArr[1] = i8;
        iArr[0] = (i7 & i8) ^ i3;
    }

    private void intToBytesBig(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) (i2 >>> 24);
        bArr[i3 + 1] = (byte) (i2 >>> 16);
        bArr[i3 + 2] = (byte) (i2 >>> 8);
        bArr[i3 + 3] = (byte) i2;
    }

    private void pi1(int[] iArr) {
        iArr[1] = rotl(iArr[1], 1);
        iArr[2] = rotl(iArr[2], 5);
        iArr[3] = rotl(iArr[3], 2);
    }

    private void pi2(int[] iArr) {
        iArr[1] = rotl(iArr[1], 31);
        iArr[2] = rotl(iArr[2], 27);
        iArr[3] = rotl(iArr[3], 30);
    }

    private int rotl(int i2, int i3) {
        return (i2 >>> (32 - i3)) | (i2 << i3);
    }

    private void setKey(byte[] bArr) {
        this.subKeys[0] = bytesToIntBig(bArr, 0);
        this.subKeys[1] = bytesToIntBig(bArr, 4);
        this.subKeys[2] = bytesToIntBig(bArr, 8);
        this.subKeys[3] = bytesToIntBig(bArr, 12);
    }

    private void theta(int[] iArr, int[] iArr2) {
        int i2 = iArr[0] ^ iArr[2];
        int iRotl = i2 ^ (rotl(i2, 8) ^ rotl(i2, 24));
        iArr[1] = iArr[1] ^ iRotl;
        iArr[3] = iRotl ^ iArr[3];
        for (int i3 = 0; i3 < 4; i3++) {
            iArr[i3] = iArr[i3] ^ iArr2[i3];
        }
        int i4 = iArr[1] ^ iArr[3];
        int iRotl2 = i4 ^ (rotl(i4, 8) ^ rotl(i4, 24));
        iArr[0] = iArr[0] ^ iRotl2;
        iArr[2] = iRotl2 ^ iArr[2];
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Noekeon";
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void init(boolean z2, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this._forEncryption = z2;
            this._initialised = true;
            setKey(((KeyParameter) cipherParameters).getKey());
        } else {
            throw new IllegalArgumentException("invalid parameter passed to Noekeon init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        if (!this._initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (i2 + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + 16 <= bArr2.length) {
            return this._forEncryption ? encryptBlock(bArr, i2, bArr2, i3) : decryptBlock(bArr, i2, bArr2, i3);
        }
        throw new OutputLengthException("output buffer too short");
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void reset() {
    }
}

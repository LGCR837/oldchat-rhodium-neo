package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.params.KeyParameter;

/* JADX INFO: loaded from: classes.dex */
public class RC4Engine implements StreamCipher {
    private static final int STATE_LENGTH = 256;
    private byte[] engineState = null;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private int f7329x = 0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private int f7330y = 0;
    private byte[] workingKey = null;

    private void setKey(byte[] bArr) {
        this.workingKey = bArr;
        this.f7329x = 0;
        this.f7330y = 0;
        if (this.engineState == null) {
            this.engineState = new byte[256];
        }
        for (int i2 = 0; i2 < 256; i2++) {
            this.engineState[i2] = (byte) i2;
        }
        int length = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < 256; i4++) {
            int i5 = bArr[length] & 255;
            byte[] bArr2 = this.engineState;
            byte b2 = bArr2[i4];
            i3 = (i5 + b2 + i3) & 255;
            bArr2[i4] = bArr2[i3];
            bArr2[i3] = b2;
            length = (length + 1) % bArr.length;
        }
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "RC4";
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public void init(boolean z2, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            byte[] key = ((KeyParameter) cipherParameters).getKey();
            this.workingKey = key;
            setKey(key);
        } else {
            throw new IllegalArgumentException("invalid parameter passed to RC4 init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        if (i2 + i3 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i4 + i3 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = (this.f7329x + 1) & 255;
            this.f7329x = i6;
            byte[] bArr3 = this.engineState;
            byte b2 = bArr3[i6];
            int i7 = (this.f7330y + b2) & 255;
            this.f7330y = i7;
            bArr3[i6] = bArr3[i7];
            bArr3[i7] = b2;
            bArr2[i5 + i4] = (byte) (bArr3[(bArr3[i6] + b2) & 255] ^ bArr[i5 + i2]);
        }
        return i3;
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public void reset() {
        setKey(this.workingKey);
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public byte returnByte(byte b2) {
        int i2 = (this.f7329x + 1) & 255;
        this.f7329x = i2;
        byte[] bArr = this.engineState;
        byte b3 = bArr[i2];
        int i3 = (this.f7330y + b3) & 255;
        this.f7330y = i3;
        bArr[i2] = bArr[i3];
        bArr[i3] = b3;
        return (byte) (b2 ^ bArr[(bArr[i2] + b3) & 255]);
    }
}

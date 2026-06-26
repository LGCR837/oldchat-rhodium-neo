package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

/* JADX INFO: loaded from: classes.dex */
public class VMPCEngine implements StreamCipher {
    protected byte[] workingIV;
    protected byte[] workingKey;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    protected byte f7339n = 0;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    protected byte[] f7338P = null;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    protected byte f7340s = 0;

    @Override // org.spongycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "VMPC";
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public void init(boolean z2, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("VMPC init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        if (!(parametersWithIV.getParameters() instanceof KeyParameter)) {
            throw new IllegalArgumentException("VMPC init parameters must include a key");
        }
        KeyParameter keyParameter = (KeyParameter) parametersWithIV.getParameters();
        byte[] iv = parametersWithIV.getIV();
        this.workingIV = iv;
        if (iv == null || iv.length < 1 || iv.length > 768) {
            throw new IllegalArgumentException("VMPC requires 1 to 768 bytes of IV");
        }
        byte[] key = keyParameter.getKey();
        this.workingKey = key;
        initKey(key, this.workingIV);
    }

    public void initKey(byte[] bArr, byte[] bArr2) {
        this.f7340s = (byte) 0;
        this.f7338P = new byte[256];
        for (int i2 = 0; i2 < 256; i2++) {
            this.f7338P[i2] = (byte) i2;
        }
        for (int i3 = 0; i3 < 768; i3++) {
            byte[] bArr3 = this.f7338P;
            byte b2 = this.f7340s;
            int i4 = i3 & 255;
            byte b3 = bArr3[i4];
            byte b4 = bArr3[(b2 + b3 + bArr[i3 % bArr.length]) & 255];
            this.f7340s = b4;
            bArr3[i4] = bArr3[b4 & 255];
            bArr3[b4 & 255] = b3;
        }
        for (int i5 = 0; i5 < 768; i5++) {
            byte[] bArr4 = this.f7338P;
            byte b5 = this.f7340s;
            int i6 = i5 & 255;
            byte b6 = bArr4[i6];
            byte b7 = bArr4[(b5 + b6 + bArr2[i5 % bArr2.length]) & 255];
            this.f7340s = b7;
            bArr4[i6] = bArr4[b7 & 255];
            bArr4[b7 & 255] = b6;
        }
        this.f7339n = (byte) 0;
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
            byte[] bArr3 = this.f7338P;
            byte b2 = this.f7340s;
            byte b3 = this.f7339n;
            byte b4 = bArr3[(b2 + bArr3[b3 & 255]) & 255];
            this.f7340s = b4;
            byte b5 = bArr3[(bArr3[bArr3[b4 & 255] & 255] + 1) & 255];
            byte b6 = bArr3[b3 & 255];
            bArr3[b3 & 255] = bArr3[b4 & 255];
            bArr3[b4 & 255] = b6;
            this.f7339n = (byte) ((b3 + 1) & 255);
            bArr2[i5 + i4] = (byte) (bArr[i5 + i2] ^ b5);
        }
        return i3;
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public void reset() {
        initKey(this.workingKey, this.workingIV);
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public byte returnByte(byte b2) {
        byte[] bArr = this.f7338P;
        byte b3 = this.f7340s;
        byte b4 = this.f7339n;
        byte b5 = bArr[(b3 + bArr[b4 & 255]) & 255];
        this.f7340s = b5;
        byte b6 = bArr[(bArr[bArr[b5 & 255] & 255] + 1) & 255];
        byte b7 = bArr[b4 & 255];
        bArr[b4 & 255] = bArr[b5 & 255];
        bArr[b5 & 255] = b7;
        this.f7339n = (byte) ((b4 + 1) & 255);
        return (byte) (b2 ^ b6);
    }
}

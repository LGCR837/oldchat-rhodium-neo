package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

/* JADX INFO: loaded from: classes.dex */
public class VMPCMac implements Mac {

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    private byte[] f7363T;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private byte f7364g;
    private byte[] workingIV;
    private byte[] workingKey;
    private byte x1;
    private byte x2;
    private byte x3;
    private byte x4;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private byte f7365n = 0;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    private byte[] f7362P = null;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private byte f7366s = 0;

    private void initKey(byte[] bArr, byte[] bArr2) {
        this.f7366s = (byte) 0;
        this.f7362P = new byte[256];
        for (int i2 = 0; i2 < 256; i2++) {
            this.f7362P[i2] = (byte) i2;
        }
        for (int i3 = 0; i3 < 768; i3++) {
            byte[] bArr3 = this.f7362P;
            byte b2 = this.f7366s;
            int i4 = i3 & 255;
            byte b3 = bArr3[i4];
            byte b4 = bArr3[(b2 + b3 + bArr[i3 % bArr.length]) & 255];
            this.f7366s = b4;
            bArr3[i4] = bArr3[b4 & 255];
            bArr3[b4 & 255] = b3;
        }
        for (int i5 = 0; i5 < 768; i5++) {
            byte[] bArr4 = this.f7362P;
            byte b5 = this.f7366s;
            int i6 = i5 & 255;
            byte b6 = bArr4[i6];
            byte b7 = bArr4[(b5 + b6 + bArr2[i5 % bArr2.length]) & 255];
            this.f7366s = b7;
            bArr4[i6] = bArr4[b7 & 255];
            bArr4[b7 & 255] = b6;
        }
        this.f7365n = (byte) 0;
    }

    @Override // org.spongycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i2) {
        for (int i3 = 1; i3 < 25; i3++) {
            byte[] bArr2 = this.f7362P;
            byte b2 = this.f7366s;
            byte b3 = this.f7365n;
            byte b4 = bArr2[(b2 + bArr2[b3 & 255]) & 255];
            this.f7366s = b4;
            byte b5 = this.x4;
            byte b6 = this.x3;
            byte b7 = bArr2[(b5 + b6 + i3) & 255];
            this.x4 = b7;
            byte b8 = this.x2;
            byte b9 = bArr2[(b6 + b8 + i3) & 255];
            this.x3 = b9;
            byte b10 = this.x1;
            byte b11 = bArr2[(b8 + b10 + i3) & 255];
            this.x2 = b11;
            byte b12 = bArr2[(b10 + b4 + i3) & 255];
            this.x1 = b12;
            byte[] bArr3 = this.f7363T;
            byte b13 = this.f7364g;
            bArr3[b13 & 31] = (byte) (b12 ^ bArr3[b13 & 31]);
            bArr3[(b13 + 1) & 31] = (byte) (b11 ^ bArr3[(b13 + 1) & 31]);
            bArr3[(b13 + 2) & 31] = (byte) (b9 ^ bArr3[(b13 + 2) & 31]);
            bArr3[(b13 + 3) & 31] = (byte) (b7 ^ bArr3[(b13 + 3) & 31]);
            this.f7364g = (byte) ((b13 + 4) & 31);
            byte b14 = bArr2[b3 & 255];
            bArr2[b3 & 255] = bArr2[b4 & 255];
            bArr2[b4 & 255] = b14;
            this.f7365n = (byte) ((b3 + 1) & 255);
        }
        for (int i4 = 0; i4 < 768; i4++) {
            byte[] bArr4 = this.f7362P;
            byte b15 = this.f7366s;
            int i5 = i4 & 255;
            byte b16 = bArr4[i5];
            byte b17 = bArr4[(b15 + b16 + this.f7363T[i4 & 31]) & 255];
            this.f7366s = b17;
            bArr4[i5] = bArr4[b17 & 255];
            bArr4[b17 & 255] = b16;
        }
        byte[] bArr5 = new byte[20];
        for (int i6 = 0; i6 < 20; i6++) {
            byte[] bArr6 = this.f7362P;
            int i7 = i6 & 255;
            byte b18 = bArr6[(this.f7366s + bArr6[i7]) & 255];
            this.f7366s = b18;
            bArr5[i6] = bArr6[(bArr6[bArr6[b18 & 255] & 255] + 1) & 255];
            byte b19 = bArr6[i7];
            bArr6[i7] = bArr6[b18 & 255];
            bArr6[b18 & 255] = b19;
        }
        System.arraycopy(bArr5, 0, bArr, i2, 20);
        reset();
        return 20;
    }

    @Override // org.spongycastle.crypto.Mac
    public String getAlgorithmName() {
        return "VMPC-MAC";
    }

    @Override // org.spongycastle.crypto.Mac
    public int getMacSize() {
        return 20;
    }

    @Override // org.spongycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("VMPC-MAC Init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        KeyParameter keyParameter = (KeyParameter) parametersWithIV.getParameters();
        if (!(parametersWithIV.getParameters() instanceof KeyParameter)) {
            throw new IllegalArgumentException("VMPC-MAC Init parameters must include a key");
        }
        byte[] iv = parametersWithIV.getIV();
        this.workingIV = iv;
        if (iv == null || iv.length < 1 || iv.length > 768) {
            throw new IllegalArgumentException("VMPC-MAC requires 1 to 768 bytes of IV");
        }
        this.workingKey = keyParameter.getKey();
        reset();
    }

    @Override // org.spongycastle.crypto.Mac
    public void reset() {
        initKey(this.workingKey, this.workingIV);
        this.f7365n = (byte) 0;
        this.x4 = (byte) 0;
        this.x3 = (byte) 0;
        this.x2 = (byte) 0;
        this.x1 = (byte) 0;
        this.f7364g = (byte) 0;
        this.f7363T = new byte[32];
        for (int i2 = 0; i2 < 32; i2++) {
            this.f7363T[i2] = 0;
        }
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte b2) {
        byte[] bArr = this.f7362P;
        byte b3 = this.f7366s;
        byte b4 = this.f7365n;
        byte b5 = bArr[(b3 + bArr[b4 & 255]) & 255];
        this.f7366s = b5;
        byte b6 = (byte) (b2 ^ bArr[(bArr[bArr[b5 & 255] & 255] + 1) & 255]);
        byte b7 = this.x4;
        byte b8 = this.x3;
        byte b9 = bArr[(b7 + b8) & 255];
        this.x4 = b9;
        byte b10 = this.x2;
        byte b11 = bArr[(b8 + b10) & 255];
        this.x3 = b11;
        byte b12 = this.x1;
        byte b13 = bArr[(b10 + b12) & 255];
        this.x2 = b13;
        byte b14 = bArr[(b12 + b5 + b6) & 255];
        this.x1 = b14;
        byte[] bArr2 = this.f7363T;
        byte b15 = this.f7364g;
        bArr2[b15 & 31] = (byte) (b14 ^ bArr2[b15 & 31]);
        bArr2[(b15 + 1) & 31] = (byte) (b13 ^ bArr2[(b15 + 1) & 31]);
        bArr2[(b15 + 2) & 31] = (byte) (b11 ^ bArr2[(b15 + 2) & 31]);
        bArr2[(b15 + 3) & 31] = (byte) (b9 ^ bArr2[(b15 + 3) & 31]);
        this.f7364g = (byte) ((b15 + 4) & 31);
        byte b16 = bArr[b4 & 255];
        bArr[b4 & 255] = bArr[b5 & 255];
        bArr[b5 & 255] = b16;
        this.f7365n = (byte) ((b4 + 1) & 255);
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte[] bArr, int i2, int i3) {
        if (i2 + i3 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        for (int i4 = 0; i4 < i3; i4++) {
            update(bArr[i2 + i4]);
        }
    }
}

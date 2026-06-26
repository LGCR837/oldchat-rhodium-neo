package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

/* JADX INFO: loaded from: classes.dex */
public class Grainv1Engine implements StreamCipher {
    private static final int STATE_SIZE = 5;
    private int index = 2;
    private boolean initialised = false;
    private int[] lfsr;
    private int[] nfsr;
    private byte[] out;
    private int output;
    private byte[] workingIV;
    private byte[] workingKey;

    private byte getKeyStream() {
        if (this.index > 1) {
            oneRound();
            this.index = 0;
        }
        byte[] bArr = this.out;
        int i2 = this.index;
        this.index = i2 + 1;
        return bArr[i2];
    }

    private int getOutput() {
        int[] iArr = this.nfsr;
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = (i2 >>> 1) | (i3 << 15);
        int i5 = (i2 >>> 2) | (i3 << 14);
        int i6 = (i2 >>> 4) | (i3 << 12);
        int i7 = (i2 >>> 10) | (i3 << 6);
        int i8 = iArr[2];
        int i9 = (i3 >>> 15) | (i8 << 1);
        int i10 = iArr[3];
        int i11 = (i8 >>> 11) | (i10 << 5);
        int i12 = iArr[4];
        int i13 = (i10 >>> 8) | (i12 << 8);
        int i14 = (i12 << 1) | (i10 >>> 15);
        int[] iArr2 = this.lfsr;
        int i15 = iArr2[0] >>> 3;
        int i16 = iArr2[1];
        int i17 = i15 | (i16 << 13);
        int i18 = iArr2[2];
        int i19 = (i16 >>> 9) | (i18 << 7);
        int i20 = (iArr2[3] << 2) | (i18 >>> 14);
        int i21 = iArr2[4];
        int i22 = i20 & i21;
        int i23 = ((((i19 ^ i14) ^ (i17 & i21)) ^ i22) ^ (i21 & i14)) ^ ((i17 & i19) & i20);
        int i24 = i17 & i20;
        return (((((((((i14 & i22) ^ (((i24 & i14) ^ ((i21 & i24) ^ i23)) ^ ((i19 & i20) & i14))) ^ i4) ^ i5) ^ i6) ^ i7) ^ i9) ^ i11) ^ i13) & 65535;
    }

    private int getOutputLFSR() {
        int[] iArr = this.lfsr;
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = (i2 >>> 13) | (i3 << 3);
        int i5 = iArr[2];
        int i6 = (i3 >>> 7) | (i5 << 9);
        int i7 = iArr[3];
        int i8 = (i5 >>> 6) | (i7 << 10);
        int i9 = iArr[4];
        int i10 = (i7 >>> 3) | (i9 << 13);
        return (((i9 << 2) | (i7 >>> 14)) ^ ((((i2 ^ i4) ^ i6) ^ i8) ^ i10)) & 65535;
    }

    private int getOutputNFSR() {
        int[] iArr = this.nfsr;
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = (i2 >>> 9) | (i3 << 7);
        int i5 = (i2 >>> 14) | (i3 << 2);
        int i6 = (i2 >>> 15) | (i3 << 1);
        int i7 = iArr[2];
        int i8 = (i3 >>> 5) | (i7 << 11);
        int i9 = (i3 >>> 12) | (i7 << 4);
        int i10 = iArr[3];
        int i11 = (i7 >>> 1) | (i10 << 15);
        int i12 = (i7 >>> 5) | (i10 << 11);
        int i13 = (i7 >>> 13) | (i10 << 3);
        int i14 = iArr[4];
        int i15 = (i10 >>> 4) | (i14 << 12);
        int i16 = (i10 >>> 12) | (i14 << 4);
        int i17 = (i10 >>> 14) | (i14 << 2);
        int i18 = (i14 << 1) | (i10 >>> 15);
        int i19 = i18 & i16;
        int i20 = (((i2 ^ (((((((((i17 ^ i16) ^ i15) ^ i13) ^ i12) ^ i11) ^ i9) ^ i8) ^ i5) ^ i4)) ^ i19) ^ (i12 & i11)) ^ (i6 & i4);
        int i21 = i16 & i15;
        int i22 = i11 & i9 & i8;
        return (((((((((i18 & i13) & i9) & i4) ^ ((i20 ^ (i21 & i13)) ^ i22)) ^ ((i21 & i12) & i11)) ^ ((i19 & i8) & i6)) ^ (((i19 & i15) & i13) & i12)) ^ ((i22 & i6) & i4)) ^ (((((i15 & i13) & i12) & i11) & i9) & i8)) & 65535;
    }

    private void initGrain() {
        for (int i2 = 0; i2 < 10; i2++) {
            this.output = getOutput();
            this.nfsr = shift(this.nfsr, (getOutputNFSR() ^ this.lfsr[0]) ^ this.output);
            this.lfsr = shift(this.lfsr, getOutputLFSR() ^ this.output);
        }
        this.initialised = true;
    }

    private void oneRound() {
        int output = getOutput();
        this.output = output;
        byte[] bArr = this.out;
        bArr[0] = (byte) output;
        bArr[1] = (byte) (output >> 8);
        this.nfsr = shift(this.nfsr, getOutputNFSR() ^ this.lfsr[0]);
        this.lfsr = shift(this.lfsr, getOutputLFSR());
    }

    private void setKey(byte[] bArr, byte[] bArr2) {
        bArr2[8] = -1;
        bArr2[9] = -1;
        this.workingKey = bArr;
        this.workingIV = bArr2;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr = this.nfsr;
            if (i2 >= iArr.length) {
                return;
            }
            byte[] bArr3 = this.workingKey;
            int i4 = i3 + 1;
            iArr[i2] = ((bArr3[i3] & 255) | (bArr3[i4] << 8)) & 65535;
            int[] iArr2 = this.lfsr;
            byte[] bArr4 = this.workingIV;
            iArr2[i2] = ((bArr4[i3] & 255) | (bArr4[i4] << 8)) & 65535;
            i3 += 2;
            i2++;
        }
    }

    private int[] shift(int[] iArr, int i2) {
        iArr[0] = iArr[1];
        iArr[1] = iArr[2];
        iArr[2] = iArr[3];
        iArr[3] = iArr[4];
        iArr[4] = i2;
        return iArr;
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "Grain v1";
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public void init(boolean z2, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("Grain v1 Init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] iv = parametersWithIV.getIV();
        if (iv == null || iv.length != 8) {
            throw new IllegalArgumentException("Grain v1 requires exactly 8 bytes of IV");
        }
        if (!(parametersWithIV.getParameters() instanceof KeyParameter)) {
            throw new IllegalArgumentException("Grain v1 Init parameters must include a key");
        }
        KeyParameter keyParameter = (KeyParameter) parametersWithIV.getParameters();
        this.workingIV = new byte[keyParameter.getKey().length];
        this.workingKey = new byte[keyParameter.getKey().length];
        this.lfsr = new int[5];
        this.nfsr = new int[5];
        this.out = new byte[2];
        System.arraycopy(iv, 0, this.workingIV, 0, iv.length);
        System.arraycopy(keyParameter.getKey(), 0, this.workingKey, 0, keyParameter.getKey().length);
        reset();
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        if (!this.initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (i2 + i3 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i4 + i3 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        for (int i5 = 0; i5 < i3; i5++) {
            bArr2[i4 + i5] = (byte) (bArr[i2 + i5] ^ getKeyStream());
        }
        return i3;
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public void reset() {
        this.index = 2;
        setKey(this.workingKey, this.workingIV);
        initGrain();
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public byte returnByte(byte b2) {
        if (this.initialised) {
            return (byte) (b2 ^ getKeyStream());
        }
        throw new IllegalStateException(getAlgorithmName() + " not initialised");
    }
}

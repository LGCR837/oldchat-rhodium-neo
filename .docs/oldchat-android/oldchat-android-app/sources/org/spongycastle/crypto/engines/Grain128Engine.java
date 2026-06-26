package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.math.ec.Tnaf;

/* JADX INFO: loaded from: classes.dex */
public class Grain128Engine implements StreamCipher {
    private static final int STATE_SIZE = 4;
    private int index = 4;
    private boolean initialised = false;
    private int[] lfsr;
    private int[] nfsr;
    private byte[] out;
    private int output;
    private byte[] workingIV;
    private byte[] workingKey;

    private byte getKeyStream() {
        if (this.index > 3) {
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
        int i4 = (i2 >>> 2) | (i3 << 30);
        int i5 = (i2 >>> 12) | (i3 << 20);
        int i6 = (i2 >>> 15) | (i3 << 17);
        int i7 = iArr[2];
        int i8 = (i3 >>> 4) | (i7 << 28);
        int i9 = (i3 >>> 13) | (i7 << 19);
        int i10 = iArr[3];
        int i11 = (i7 >>> 9) | (i10 << 23);
        int i12 = (i7 >>> 25) | (i10 << 7);
        int i13 = (i10 << 1) | (i7 >>> 31);
        int[] iArr2 = this.lfsr;
        int i14 = iArr2[0];
        int i15 = iArr2[1];
        int i16 = (i14 >>> 8) | (i15 << 24);
        int i17 = (i14 >>> 13) | (i15 << 19);
        int i18 = (i14 >>> 20) | (i15 << 12);
        int i19 = iArr2[2];
        int i20 = iArr2[3];
        int i21 = i17 & i18;
        return ((((((((((i13 & i5) & ((i20 << 1) | (i19 >>> 31))) ^ (((i21 ^ (i5 & i16)) ^ (i13 & ((i15 >>> 10) | (i19 << 22)))) ^ (((i15 >>> 28) | (i19 << 4)) & ((i19 >>> 15) | (i20 << 17))))) ^ ((i19 >>> 29) | (i20 << 3))) ^ i4) ^ i6) ^ i8) ^ i9) ^ i7) ^ i11) ^ i12;
    }

    private int getOutputLFSR() {
        int[] iArr = this.lfsr;
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = (i2 >>> 7) | (i3 << 25);
        int i5 = iArr[2];
        int i6 = iArr[3];
        int i7 = (i5 >>> 6) | (i6 << 26);
        return i6 ^ ((((i2 ^ i4) ^ ((i3 >>> 6) | (i5 << 26))) ^ i7) ^ ((i5 >>> 17) | (i6 << 15)));
    }

    private int getOutputNFSR() {
        int[] iArr = this.nfsr;
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = (i2 >>> 3) | (i3 << 29);
        int i5 = (i2 >>> 11) | (i3 << 21);
        int i6 = (i2 >>> 13) | (i3 << 19);
        int i7 = (i2 >>> 17) | (i3 << 15);
        int i8 = (i2 >>> 18) | (i3 << 14);
        int i9 = (i2 >>> 26) | (i3 << 6);
        int i10 = (i2 >>> 27) | (i3 << 5);
        int i11 = iArr[2];
        int i12 = (i3 >>> 8) | (i11 << 24);
        int i13 = (i3 >>> 16) | (i11 << 16);
        int i14 = (i3 >>> 24) | (i11 << 8);
        int i15 = (i3 >>> 27) | (i11 << 5);
        int i16 = (i3 >>> 29) | (i11 << 3);
        int i17 = iArr[3];
        return (((((((i17 ^ (((i2 ^ i9) ^ i14) ^ ((i11 >>> 27) | (i17 << 5)))) ^ (i4 & ((i11 >>> 3) | (i17 << 29)))) ^ (i5 & i6)) ^ (i7 & i8)) ^ (i10 & i15)) ^ (i12 & i13)) ^ (i16 & ((i11 >>> 1) | (i17 << 31)))) ^ (((i11 >>> 4) | (i17 << 28)) & ((i11 >>> 20) | (i17 << 12)));
    }

    private void initGrain() {
        for (int i2 = 0; i2 < 8; i2++) {
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
        bArr[2] = (byte) (output >> 16);
        bArr[3] = (byte) (output >> 24);
        this.nfsr = shift(this.nfsr, getOutputNFSR() ^ this.lfsr[0]);
        this.lfsr = shift(this.lfsr, getOutputLFSR());
    }

    private void setKey(byte[] bArr, byte[] bArr2) {
        bArr2[12] = -1;
        bArr2[13] = -1;
        bArr2[14] = -1;
        bArr2[15] = -1;
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
            int i4 = i3 + 3;
            int i5 = i3 + 2;
            int i6 = i3 + 1;
            iArr[i2] = (bArr3[i3] & 255) | (bArr3[i4] << 24) | ((bArr3[i5] << Tnaf.POW_2_WIDTH) & 16711680) | ((bArr3[i6] << 8) & CipherSuite.DRAFT_TLS_DHE_RSA_WITH_AES_128_OCB);
            int[] iArr2 = this.lfsr;
            byte[] bArr4 = this.workingIV;
            iArr2[i2] = (bArr4[i3] & 255) | (bArr4[i4] << 24) | ((bArr4[i5] << Tnaf.POW_2_WIDTH) & 16711680) | ((bArr4[i6] << 8) & CipherSuite.DRAFT_TLS_DHE_RSA_WITH_AES_128_OCB);
            i3 += 4;
            i2++;
        }
    }

    private int[] shift(int[] iArr, int i2) {
        iArr[0] = iArr[1];
        iArr[1] = iArr[2];
        iArr[2] = iArr[3];
        iArr[3] = i2;
        return iArr;
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "Grain-128";
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public void init(boolean z2, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("Grain-128 Init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] iv = parametersWithIV.getIV();
        if (iv == null || iv.length != 12) {
            throw new IllegalArgumentException("Grain-128  requires exactly 12 bytes of IV");
        }
        if (!(parametersWithIV.getParameters() instanceof KeyParameter)) {
            throw new IllegalArgumentException("Grain-128 Init parameters must include a key");
        }
        KeyParameter keyParameter = (KeyParameter) parametersWithIV.getParameters();
        this.workingIV = new byte[keyParameter.getKey().length];
        this.workingKey = new byte[keyParameter.getKey().length];
        this.lfsr = new int[4];
        this.nfsr = new int[4];
        this.out = new byte[4];
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
        this.index = 4;
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

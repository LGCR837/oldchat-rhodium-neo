package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.engines.DSTU7624Engine;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class DSTU7624Mac implements Mac {
    private static final int BITS_IN_BYTE = 8;
    private int blockSize;
    private byte[] buf;
    private int bufOff;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private byte[] f7354c;
    private byte[] cTemp;
    private DSTU7624Engine engine;
    private byte[] kDelta;
    private int macSize;

    public DSTU7624Mac(int i2, int i3) {
        this.engine = new DSTU7624Engine(i2);
        int i4 = i2 / 8;
        this.blockSize = i4;
        this.macSize = i3 / 8;
        this.f7354c = new byte[i4];
        this.kDelta = new byte[i4];
        this.cTemp = new byte[i4];
        this.buf = new byte[i4];
    }

    private void processBlock(byte[] bArr, int i2) {
        xor(this.f7354c, 0, bArr, i2, this.cTemp);
        this.engine.processBlock(this.cTemp, 0, this.f7354c, 0);
    }

    private void xor(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3) {
        int length = bArr.length - i2;
        int i4 = this.blockSize;
        if (length < i4 || bArr2.length - i3 < i4 || bArr3.length < i4) {
            throw new IllegalArgumentException("some of input buffers too short");
        }
        for (int i5 = 0; i5 < this.blockSize; i5++) {
            bArr3[i5] = (byte) (bArr[i5 + i2] ^ bArr2[i5 + i3]);
        }
    }

    @Override // org.spongycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i2) {
        int i3 = this.bufOff;
        byte[] bArr2 = this.buf;
        if (i3 % bArr2.length != 0) {
            throw new DataLengthException("input must be a multiple of blocksize");
        }
        xor(this.f7354c, 0, bArr2, 0, this.cTemp);
        xor(this.cTemp, 0, this.kDelta, 0, this.f7354c);
        DSTU7624Engine dSTU7624Engine = this.engine;
        byte[] bArr3 = this.f7354c;
        dSTU7624Engine.processBlock(bArr3, 0, bArr3, 0);
        int i4 = this.macSize;
        if (i4 + i2 > bArr.length) {
            throw new OutputLengthException("output buffer too short");
        }
        System.arraycopy(this.f7354c, 0, bArr, i2, i4);
        return this.macSize;
    }

    @Override // org.spongycastle.crypto.Mac
    public String getAlgorithmName() {
        return "DSTU7624Mac";
    }

    @Override // org.spongycastle.crypto.Mac
    public int getMacSize() {
        return this.macSize;
    }

    @Override // org.spongycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("Invalid parameter passed to DSTU7624Mac");
        }
        this.engine.init(true, cipherParameters);
        DSTU7624Engine dSTU7624Engine = this.engine;
        byte[] bArr = this.kDelta;
        dSTU7624Engine.processBlock(bArr, 0, bArr, 0);
    }

    @Override // org.spongycastle.crypto.Mac
    public void reset() {
        Arrays.fill(this.f7354c, (byte) 0);
        Arrays.fill(this.cTemp, (byte) 0);
        Arrays.fill(this.kDelta, (byte) 0);
        Arrays.fill(this.buf, (byte) 0);
        this.engine.reset();
        DSTU7624Engine dSTU7624Engine = this.engine;
        byte[] bArr = this.kDelta;
        dSTU7624Engine.processBlock(bArr, 0, bArr, 0);
        this.bufOff = 0;
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte b2) {
        int i2 = this.bufOff;
        byte[] bArr = this.buf;
        if (i2 == bArr.length) {
            processBlock(bArr, 0);
            this.bufOff = 0;
        }
        byte[] bArr2 = this.buf;
        int i3 = this.bufOff;
        this.bufOff = i3 + 1;
        bArr2[i3] = b2;
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte[] bArr, int i2, int i3) {
        if (i3 >= 0) {
            int blockSize = this.engine.getBlockSize();
            int i4 = this.bufOff;
            int i5 = blockSize - i4;
            if (i3 > i5) {
                System.arraycopy(bArr, i2, this.buf, i4, i5);
                processBlock(this.buf, 0);
                this.bufOff = 0;
                i3 -= i5;
                i2 += i5;
                while (i3 > blockSize) {
                    processBlock(bArr, i2);
                    i3 -= blockSize;
                    i2 += blockSize;
                }
            }
            System.arraycopy(bArr, i2, this.buf, this.bufOff, i3);
            this.bufOff += i3;
            return;
        }
        throw new IllegalArgumentException("can't have a negative input length!");
    }
}

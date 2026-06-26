package org.spongycastle.crypto.digests;

import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.util.Memoable;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public abstract class GeneralDigest implements ExtendedDigest, Memoable {
    private static final int BYTE_LENGTH = 64;
    private long byteCount;
    private final byte[] xBuf;
    private int xBufOff;

    public GeneralDigest() {
        this.xBuf = new byte[4];
        this.xBufOff = 0;
    }

    public void copyIn(GeneralDigest generalDigest) {
        byte[] bArr = generalDigest.xBuf;
        System.arraycopy(bArr, 0, this.xBuf, 0, bArr.length);
        this.xBufOff = generalDigest.xBufOff;
        this.byteCount = generalDigest.byteCount;
    }

    public void finish() {
        long j2 = this.byteCount << 3;
        update((byte) -128);
        while (this.xBufOff != 0) {
            update((byte) 0);
        }
        processLength(j2);
        processBlock();
    }

    @Override // org.spongycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 64;
    }

    public void populateState(byte[] bArr) {
        System.arraycopy(this.xBuf, 0, bArr, 0, this.xBufOff);
        Pack.intToBigEndian(this.xBufOff, bArr, 4);
        Pack.longToBigEndian(this.byteCount, bArr, 8);
    }

    public abstract void processBlock();

    public abstract void processLength(long j2);

    public abstract void processWord(byte[] bArr, int i2);

    @Override // org.spongycastle.crypto.Digest
    public void reset() {
        this.byteCount = 0L;
        this.xBufOff = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.xBuf;
            if (i2 >= bArr.length) {
                return;
            }
            bArr[i2] = 0;
            i2++;
        }
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte b2) {
        byte[] bArr = this.xBuf;
        int i2 = this.xBufOff;
        int i3 = i2 + 1;
        this.xBufOff = i3;
        bArr[i2] = b2;
        if (i3 == bArr.length) {
            processWord(bArr, 0);
            this.xBufOff = 0;
        }
        this.byteCount++;
    }

    public GeneralDigest(GeneralDigest generalDigest) {
        this.xBuf = new byte[4];
        copyIn(generalDigest);
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte[] bArr, int i2, int i3) {
        int i4 = 0;
        int iMax = Math.max(0, i3);
        if (this.xBufOff != 0) {
            int i5 = 0;
            while (true) {
                if (i5 >= iMax) {
                    i4 = i5;
                    break;
                }
                byte[] bArr2 = this.xBuf;
                int i6 = this.xBufOff;
                int i7 = i6 + 1;
                this.xBufOff = i7;
                int i8 = i5 + 1;
                bArr2[i6] = bArr[i5 + i2];
                if (i7 == 4) {
                    processWord(bArr2, 0);
                    this.xBufOff = 0;
                    i4 = i8;
                    break;
                }
                i5 = i8;
            }
        }
        int i9 = ((iMax - i4) & (-4)) + i4;
        while (i4 < i9) {
            processWord(bArr, i2 + i4);
            i4 += 4;
        }
        while (i4 < iMax) {
            byte[] bArr3 = this.xBuf;
            int i10 = this.xBufOff;
            this.xBufOff = i10 + 1;
            bArr3[i10] = bArr[i4 + i2];
            i4++;
        }
        this.byteCount += (long) iMax;
    }

    public GeneralDigest(byte[] bArr) {
        byte[] bArr2 = new byte[4];
        this.xBuf = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.xBufOff = Pack.bigEndianToInt(bArr, 4);
        this.byteCount = Pack.bigEndianToLong(bArr, 8);
    }
}

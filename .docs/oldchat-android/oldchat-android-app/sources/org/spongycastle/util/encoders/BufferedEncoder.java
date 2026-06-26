package org.spongycastle.util.encoders;

/* JADX INFO: loaded from: classes.dex */
public class BufferedEncoder {
    protected byte[] buf;
    protected int bufOff;
    protected Translator translator;

    public BufferedEncoder(Translator translator, int i2) {
        this.translator = translator;
        if (i2 % translator.getEncodedBlockSize() != 0) {
            throw new IllegalArgumentException("buffer size not multiple of input block size");
        }
        this.buf = new byte[i2];
        this.bufOff = 0;
    }

    public int processByte(byte b2, byte[] bArr, int i2) {
        byte[] bArr2 = this.buf;
        int i3 = this.bufOff;
        int i4 = i3 + 1;
        this.bufOff = i4;
        bArr2[i3] = b2;
        if (i4 != bArr2.length) {
            return 0;
        }
        int iEncode = this.translator.encode(bArr2, 0, bArr2.length, bArr, i2);
        this.bufOff = 0;
        return iEncode;
    }

    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        if (i3 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        byte[] bArr3 = this.buf;
        int length = bArr3.length;
        int i5 = this.bufOff;
        int i6 = length - i5;
        int iEncode = 0;
        if (i3 > i6) {
            System.arraycopy(bArr, i2, bArr3, i5, i6);
            Translator translator = this.translator;
            byte[] bArr4 = this.buf;
            int iEncode2 = translator.encode(bArr4, 0, bArr4.length, bArr2, i4);
            this.bufOff = 0;
            int i7 = i3 - i6;
            int i8 = i2 + i6;
            int i9 = i4 + iEncode2;
            int length2 = i7 - (i7 % this.buf.length);
            iEncode = iEncode2 + this.translator.encode(bArr, i8, length2, bArr2, i9);
            i3 = i7 - length2;
            i2 = i8 + length2;
        }
        if (i3 != 0) {
            System.arraycopy(bArr, i2, this.buf, this.bufOff, i3);
            this.bufOff += i3;
        }
        return iEncode;
    }
}

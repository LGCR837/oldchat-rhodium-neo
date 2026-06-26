package org.spongycastle.pqc.crypto.ntru;

import org.spongycastle.crypto.Digest;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class IndexGenerator {

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    private int f7650N;
    private BitString buf;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f7651c;
    private int hLen;
    private Digest hashAlg;
    private boolean initialized;
    private int minCallsR;
    private byte[] seed;
    private int totLen = 0;
    private int remLen = 0;
    private int counter = 0;

    public IndexGenerator(byte[] bArr, NTRUEncryptionParameters nTRUEncryptionParameters) {
        this.seed = bArr;
        this.f7650N = nTRUEncryptionParameters.f7655N;
        this.f7651c = nTRUEncryptionParameters.f7656c;
        this.minCallsR = nTRUEncryptionParameters.minCallsR;
        Digest digest = nTRUEncryptionParameters.hashAlg;
        this.hashAlg = digest;
        this.hLen = digest.getDigestSize();
        this.initialized = false;
    }

    private void appendHash(BitString bitString, byte[] bArr) {
        Digest digest = this.hashAlg;
        byte[] bArr2 = this.seed;
        digest.update(bArr2, 0, bArr2.length);
        putInt(this.hashAlg, this.counter);
        this.hashAlg.doFinal(bArr, 0);
        bitString.appendBits(bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] copyOf(byte[] bArr, int i2) {
        byte[] bArr2 = new byte[i2];
        if (i2 >= bArr.length) {
            i2 = bArr.length;
        }
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        return bArr2;
    }

    private void putInt(Digest digest, int i2) {
        digest.update((byte) (i2 >> 24));
        digest.update((byte) (i2 >> 16));
        digest.update((byte) (i2 >> 8));
        digest.update((byte) i2);
    }

    public int nextIndex() {
        int leadingAsInt;
        int i2;
        int i3;
        int i4;
        if (!this.initialized) {
            this.buf = new BitString();
            byte[] bArr = new byte[this.hashAlg.getDigestSize()];
            while (true) {
                int i5 = this.counter;
                i4 = this.minCallsR;
                if (i5 >= i4) {
                    break;
                }
                appendHash(this.buf, bArr);
                this.counter++;
            }
            int i6 = i4 * 8 * this.hLen;
            this.totLen = i6;
            this.remLen = i6;
            this.initialized = true;
        }
        do {
            this.totLen += this.f7651c;
            BitString trailing = this.buf.getTrailing(this.remLen);
            int i7 = this.remLen;
            int i8 = this.f7651c;
            if (i7 < i8) {
                int i9 = i8 - i7;
                int i10 = this.counter;
                int i11 = this.hLen;
                int i12 = i10 + (((i9 + i11) - 1) / i11);
                byte[] bArr2 = new byte[this.hashAlg.getDigestSize()];
                while (this.counter < i12) {
                    appendHash(trailing, bArr2);
                    this.counter++;
                    int i13 = this.hLen;
                    if (i9 > i13 * 8) {
                        i9 -= i13 * 8;
                    }
                }
                this.remLen = (this.hLen * 8) - i9;
                BitString bitString = new BitString();
                this.buf = bitString;
                bitString.appendBits(bArr2);
            } else {
                this.remLen = i7 - i8;
            }
            leadingAsInt = trailing.getLeadingAsInt(this.f7651c);
            i2 = this.f7651c;
            i3 = this.f7650N;
        } while (leadingAsInt >= (1 << i2) - ((1 << i2) % i3));
        return leadingAsInt % i3;
    }

    public static class BitString {
        byte[] bytes = new byte[4];
        int lastByteBits;
        int numBytes;

        public void appendBits(byte[] bArr) {
            for (int i2 = 0; i2 != bArr.length; i2++) {
                appendBits(bArr[i2]);
            }
        }

        public byte[] getBytes() {
            return Arrays.clone(this.bytes);
        }

        public int getLeadingAsInt(int i2) {
            int i3 = (((this.numBytes - 1) * 8) + this.lastByteBits) - i2;
            int i4 = i3 / 8;
            int i5 = i3 % 8;
            int i6 = (this.bytes[i4] & 255) >>> i5;
            int i7 = 8 - i5;
            while (true) {
                i4++;
                if (i4 >= this.numBytes) {
                    return i6;
                }
                i6 |= (this.bytes[i4] & 255) << i7;
                i7 += 8;
            }
        }

        public BitString getTrailing(int i2) {
            int i3;
            BitString bitString = new BitString();
            int i4 = (i2 + 7) / 8;
            bitString.numBytes = i4;
            bitString.bytes = new byte[i4];
            int i5 = 0;
            while (true) {
                i3 = bitString.numBytes;
                if (i5 >= i3) {
                    break;
                }
                bitString.bytes[i5] = this.bytes[i5];
                i5++;
            }
            int i6 = i2 % 8;
            bitString.lastByteBits = i6;
            if (i6 == 0) {
                bitString.lastByteBits = 8;
            } else {
                int i7 = 32 - i6;
                byte[] bArr = bitString.bytes;
                bArr[i3 - 1] = (byte) ((bArr[i3 - 1] << i7) >>> i7);
            }
            return bitString;
        }

        public void appendBits(byte b2) {
            int i2 = this.numBytes;
            byte[] bArr = this.bytes;
            if (i2 == bArr.length) {
                this.bytes = IndexGenerator.copyOf(bArr, bArr.length * 2);
            }
            int i3 = this.numBytes;
            if (i3 == 0) {
                this.numBytes = 1;
                this.bytes[0] = b2;
                this.lastByteBits = 8;
                return;
            }
            int i4 = this.lastByteBits;
            if (i4 == 8) {
                byte[] bArr2 = this.bytes;
                this.numBytes = i3 + 1;
                bArr2[i3] = b2;
                return;
            }
            byte[] bArr3 = this.bytes;
            int i5 = i3 - 1;
            int i6 = b2 & 255;
            bArr3[i5] = (byte) ((i6 << i4) | bArr3[i5]);
            this.numBytes = i3 + 1;
            bArr3[i3] = (byte) (i6 >> (8 - i4));
        }
    }
}

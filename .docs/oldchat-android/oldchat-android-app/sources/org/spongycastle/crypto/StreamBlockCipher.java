package org.spongycastle.crypto;

/* JADX INFO: loaded from: classes.dex */
public abstract class StreamBlockCipher implements BlockCipher, StreamCipher {
    private final BlockCipher cipher;

    public StreamBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
    }

    public abstract byte calculateByte(byte b2);

    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        int i5 = i2 + i3;
        if (i5 > bArr.length) {
            throw new DataLengthException("input buffer too small");
        }
        if (i4 + i3 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        while (i2 < i5) {
            bArr2[i4] = calculateByte(bArr[i2]);
            i4++;
            i2++;
        }
        return i3;
    }

    @Override // org.spongycastle.crypto.StreamCipher
    public final byte returnByte(byte b2) {
        return calculateByte(b2);
    }
}

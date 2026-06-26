package org.spongycastle.util.encoders;

/* JADX INFO: loaded from: classes.dex */
public interface Translator {
    int decode(byte[] bArr, int i2, int i3, byte[] bArr2, int i4);

    int encode(byte[] bArr, int i2, int i3, byte[] bArr2, int i4);

    int getDecodedBlockSize();

    int getEncodedBlockSize();
}

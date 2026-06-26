package org.spongycastle.util.encoders;

/* JADX INFO: loaded from: classes.dex */
public class HexTranslator implements Translator {
    private static final byte[] hexTable = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    @Override // org.spongycastle.util.encoders.Translator
    public int decode(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        int i5 = i3 / 2;
        for (int i6 = 0; i6 < i5; i6++) {
            int i7 = (i6 * 2) + i2;
            byte b2 = bArr[i7];
            byte b3 = bArr[i7 + 1];
            if (b2 < 97) {
                bArr2[i4] = (byte) ((b2 - 48) << 4);
            } else {
                bArr2[i4] = (byte) ((b2 - 87) << 4);
            }
            if (b3 < 97) {
                bArr2[i4] = (byte) (bArr2[i4] + ((byte) (b3 - 48)));
            } else {
                bArr2[i4] = (byte) (bArr2[i4] + ((byte) (b3 - 87)));
            }
            i4++;
        }
        return i5;
    }

    @Override // org.spongycastle.util.encoders.Translator
    public int encode(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        int i5 = 0;
        int i6 = 0;
        while (i5 < i3) {
            int i7 = i4 + i6;
            byte[] bArr3 = hexTable;
            bArr2[i7] = bArr3[(bArr[i2] >> 4) & 15];
            bArr2[i7 + 1] = bArr3[bArr[i2] & 15];
            i2++;
            i5++;
            i6 += 2;
        }
        return i3 * 2;
    }

    @Override // org.spongycastle.util.encoders.Translator
    public int getDecodedBlockSize() {
        return 1;
    }

    @Override // org.spongycastle.util.encoders.Translator
    public int getEncodedBlockSize() {
        return 2;
    }
}

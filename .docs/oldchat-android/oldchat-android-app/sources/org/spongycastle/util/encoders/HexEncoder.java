package org.spongycastle.util.encoders;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class HexEncoder implements Encoder {
    protected final byte[] encodingTable = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    protected final byte[] decodingTable = new byte[128];

    public HexEncoder() {
        initialiseDecodingTable();
    }

    private static boolean ignore(char c2) {
        return c2 == '\n' || c2 == '\r' || c2 == '\t' || c2 == ' ';
    }

    @Override // org.spongycastle.util.encoders.Encoder
    public int decode(byte[] bArr, int i2, int i3, OutputStream outputStream) throws IOException {
        int i4 = i3 + i2;
        while (i4 > i2 && ignore((char) bArr[i4 - 1])) {
            i4--;
        }
        int i5 = 0;
        while (i2 < i4) {
            while (i2 < i4 && ignore((char) bArr[i2])) {
                i2++;
            }
            int i6 = i2 + 1;
            byte b2 = this.decodingTable[bArr[i2]];
            while (i6 < i4 && ignore((char) bArr[i6])) {
                i6++;
            }
            int i7 = i6 + 1;
            byte b3 = this.decodingTable[bArr[i6]];
            if ((b2 | b3) < 0) {
                throw new IOException("invalid characters encountered in Hex data");
            }
            outputStream.write((b2 << 4) | b3);
            i5++;
            i2 = i7;
        }
        return i5;
    }

    @Override // org.spongycastle.util.encoders.Encoder
    public int encode(byte[] bArr, int i2, int i3, OutputStream outputStream) throws IOException {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            byte b2 = bArr[i4];
            outputStream.write(this.encodingTable[(b2 & 255) >>> 4]);
            outputStream.write(this.encodingTable[b2 & 15]);
        }
        return i3 * 2;
    }

    public void initialiseDecodingTable() {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr = this.decodingTable;
            if (i3 >= bArr.length) {
                break;
            }
            bArr[i3] = -1;
            i3++;
        }
        while (true) {
            byte[] bArr2 = this.encodingTable;
            if (i2 >= bArr2.length) {
                byte[] bArr3 = this.decodingTable;
                bArr3[65] = bArr3[97];
                bArr3[66] = bArr3[98];
                bArr3[67] = bArr3[99];
                bArr3[68] = bArr3[100];
                bArr3[69] = bArr3[101];
                bArr3[70] = bArr3[102];
                return;
            }
            this.decodingTable[bArr2[i2]] = (byte) i2;
            i2++;
        }
    }

    @Override // org.spongycastle.util.encoders.Encoder
    public int decode(String str, OutputStream outputStream) throws IOException {
        int length = str.length();
        while (length > 0 && ignore(str.charAt(length - 1))) {
            length--;
        }
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            while (i2 < length && ignore(str.charAt(i2))) {
                i2++;
            }
            int i4 = i2 + 1;
            byte b2 = this.decodingTable[str.charAt(i2)];
            while (i4 < length && ignore(str.charAt(i4))) {
                i4++;
            }
            int i5 = i4 + 1;
            byte b3 = this.decodingTable[str.charAt(i4)];
            if ((b2 | b3) >= 0) {
                outputStream.write((b2 << 4) | b3);
                i3++;
                i2 = i5;
            } else {
                throw new IOException("invalid characters encountered in Hex string");
            }
        }
        return i3;
    }
}

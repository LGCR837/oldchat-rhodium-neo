package org.spongycastle.util.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public final class Streams {
    private static int BUFFER_SIZE = 4096;

    public static void drain(InputStream inputStream) {
        int i2 = BUFFER_SIZE;
        while (inputStream.read(new byte[i2], 0, i2) >= 0) {
        }
    }

    public static void pipeAll(InputStream inputStream, OutputStream outputStream) throws IOException {
        int i2 = BUFFER_SIZE;
        byte[] bArr = new byte[i2];
        while (true) {
            int i3 = inputStream.read(bArr, 0, i2);
            if (i3 < 0) {
                return;
            } else {
                outputStream.write(bArr, 0, i3);
            }
        }
    }

    public static long pipeAllLimited(InputStream inputStream, long j2, OutputStream outputStream) throws IOException {
        int i2 = BUFFER_SIZE;
        byte[] bArr = new byte[i2];
        long j3 = 0;
        while (true) {
            int i3 = inputStream.read(bArr, 0, i2);
            if (i3 < 0) {
                return j3;
            }
            long j4 = i3;
            if (j2 - j3 < j4) {
                throw new StreamOverflowException("Data Overflow");
            }
            j3 += j4;
            outputStream.write(bArr, 0, i3);
        }
    }

    public static byte[] readAll(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        pipeAll(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] readAllLimited(InputStream inputStream, int i2) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        pipeAllLimited(inputStream, i2, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static int readFully(InputStream inputStream, byte[] bArr) {
        return readFully(inputStream, bArr, 0, bArr.length);
    }

    public static void writeBufTo(ByteArrayOutputStream byteArrayOutputStream, OutputStream outputStream) throws IOException {
        byteArrayOutputStream.writeTo(outputStream);
    }

    public static int readFully(InputStream inputStream, byte[] bArr, int i2, int i3) throws IOException {
        int i4 = 0;
        while (i4 < i3) {
            int i5 = inputStream.read(bArr, i2 + i4, i3 - i4);
            if (i5 < 0) {
                break;
            }
            i4 += i5;
        }
        return i4;
    }
}

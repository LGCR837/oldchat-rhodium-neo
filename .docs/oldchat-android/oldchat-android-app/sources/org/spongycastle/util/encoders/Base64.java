package org.spongycastle.util.encoders;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import org.spongycastle.util.Strings;

/* JADX INFO: loaded from: classes.dex */
public class Base64 {
    private static final Encoder encoder = new Base64Encoder();

    public static byte[] decode(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((bArr.length / 4) * 3);
        try {
            encoder.decode(bArr, 0, bArr.length, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e2) {
            throw new DecoderException("unable to decode base64 data: " + e2.getMessage(), e2);
        }
    }

    public static byte[] encode(byte[] bArr) {
        return encode(bArr, 0, bArr.length);
    }

    public static String toBase64String(byte[] bArr) {
        return toBase64String(bArr, 0, bArr.length);
    }

    public static byte[] encode(byte[] bArr, int i2, int i3) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(((i3 + 2) / 3) * 4);
        try {
            encoder.encode(bArr, i2, i3, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e2) {
            throw new EncoderException("exception encoding base64 string: " + e2.getMessage(), e2);
        }
    }

    public static String toBase64String(byte[] bArr, int i2, int i3) {
        return Strings.fromByteArray(encode(bArr, i2, i3));
    }

    public static byte[] decode(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((str.length() / 4) * 3);
        try {
            encoder.decode(str, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e2) {
            throw new DecoderException("unable to decode base64 string: " + e2.getMessage(), e2);
        }
    }

    public static int encode(byte[] bArr, OutputStream outputStream) {
        return encoder.encode(bArr, 0, bArr.length, outputStream);
    }

    public static int encode(byte[] bArr, int i2, int i3, OutputStream outputStream) {
        return encoder.encode(bArr, i2, i3, outputStream);
    }

    public static int decode(String str, OutputStream outputStream) {
        return encoder.decode(str, outputStream);
    }

    public static int decode(byte[] bArr, int i2, int i3, OutputStream outputStream) {
        try {
            return encoder.decode(bArr, i2, i3, outputStream);
        } catch (Exception e2) {
            throw new DecoderException("unable to decode base64 data: " + e2.getMessage(), e2);
        }
    }
}

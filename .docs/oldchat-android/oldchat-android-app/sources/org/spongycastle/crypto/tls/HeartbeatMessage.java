package org.spongycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.io.Streams;

/* JADX INFO: loaded from: classes.dex */
public class HeartbeatMessage {
    protected int paddingLength;
    protected byte[] payload;
    protected short type;

    public static class PayloadBuffer extends ByteArrayOutputStream {
        public byte[] toTruncatedByteArray(int i2) {
            if (((ByteArrayOutputStream) this).count < i2 + 16) {
                return null;
            }
            return Arrays.copyOf(((ByteArrayOutputStream) this).buf, i2);
        }
    }

    public HeartbeatMessage(short s2, byte[] bArr, int i2) {
        if (!HeartbeatMessageType.isValid(s2)) {
            throw new IllegalArgumentException("'type' is not a valid HeartbeatMessageType value");
        }
        if (bArr == null || bArr.length >= 65536) {
            throw new IllegalArgumentException("'payload' must have length < 2^16");
        }
        if (i2 < 16) {
            throw new IllegalArgumentException("'paddingLength' must be at least 16");
        }
        this.type = s2;
        this.payload = bArr;
        this.paddingLength = i2;
    }

    public static HeartbeatMessage parse(InputStream inputStream) throws IOException {
        short uint8 = TlsUtils.readUint8(inputStream);
        if (!HeartbeatMessageType.isValid(uint8)) {
            throw new TlsFatalAlert((short) 47);
        }
        int uint16 = TlsUtils.readUint16(inputStream);
        PayloadBuffer payloadBuffer = new PayloadBuffer();
        Streams.pipeAll(inputStream, payloadBuffer);
        byte[] truncatedByteArray = payloadBuffer.toTruncatedByteArray(uint16);
        if (truncatedByteArray == null) {
            return null;
        }
        return new HeartbeatMessage(uint8, truncatedByteArray, payloadBuffer.size() - truncatedByteArray.length);
    }

    public void encode(TlsContext tlsContext, OutputStream outputStream) throws IOException {
        TlsUtils.writeUint8(this.type, outputStream);
        TlsUtils.checkUint16(this.payload.length);
        TlsUtils.writeUint16(this.payload.length, outputStream);
        outputStream.write(this.payload);
        byte[] bArr = new byte[this.paddingLength];
        tlsContext.getNonceRandomGenerator().nextBytes(bArr);
        outputStream.write(bArr);
    }
}

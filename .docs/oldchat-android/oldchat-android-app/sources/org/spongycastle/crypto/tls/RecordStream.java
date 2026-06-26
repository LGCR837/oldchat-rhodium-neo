package org.spongycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.spongycastle.util.io.SimpleOutputStream;

/* JADX INFO: loaded from: classes.dex */
class RecordStream {
    private static int DEFAULT_PLAINTEXT_LIMIT = 16384;
    static final int TLS_HEADER_LENGTH_OFFSET = 3;
    static final int TLS_HEADER_SIZE = 5;
    static final int TLS_HEADER_TYPE_OFFSET = 0;
    static final int TLS_HEADER_VERSION_OFFSET = 1;
    private int ciphertextLimit;
    private int compressedLimit;
    private TlsProtocol handler;
    private InputStream input;
    private OutputStream output;
    private int plaintextLimit;
    private TlsCompression readCompression;
    private SequenceNumber readSeqNo;
    private TlsCompression writeCompression;
    private SequenceNumber writeSeqNo;
    private TlsCompression pendingCompression = null;
    private TlsCipher pendingCipher = null;
    private TlsCipher readCipher = null;
    private TlsCipher writeCipher = null;
    private ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private TlsHandshakeHash handshakeHash = null;
    private SimpleOutputStream handshakeHashUpdater = new SimpleOutputStream() { // from class: org.spongycastle.crypto.tls.RecordStream.1
        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i2, int i3) {
            RecordStream.this.handshakeHash.update(bArr, i2, i3);
        }
    };
    private ProtocolVersion readVersion = null;
    private ProtocolVersion writeVersion = null;
    private boolean restrictReadVersion = true;

    public static class SequenceNumber {
        private boolean exhausted;
        private long value;

        private SequenceNumber() {
            this.value = 0L;
            this.exhausted = false;
        }

        public synchronized long nextValue(short s2) {
            long j2;
            if (this.exhausted) {
                throw new TlsFatalAlert(s2);
            }
            j2 = this.value;
            long j3 = 1 + j2;
            this.value = j3;
            if (j3 == 0) {
                this.exhausted = true;
            }
            return j2;
        }
    }

    public RecordStream(TlsProtocol tlsProtocol, InputStream inputStream, OutputStream outputStream) {
        this.readCompression = null;
        this.writeCompression = null;
        this.readSeqNo = new SequenceNumber();
        this.writeSeqNo = new SequenceNumber();
        this.handler = tlsProtocol;
        this.input = inputStream;
        this.output = outputStream;
        TlsNullCompression tlsNullCompression = new TlsNullCompression();
        this.readCompression = tlsNullCompression;
        this.writeCompression = tlsNullCompression;
    }

    private static void checkLength(int i2, int i3, short s2) throws TlsFatalAlert {
        if (i2 > i3) {
            throw new TlsFatalAlert(s2);
        }
    }

    private static void checkType(short s2, short s3) throws TlsFatalAlert {
        switch (s2) {
            case 20:
            case 21:
            case 22:
            case 23:
                return;
            default:
                throw new TlsFatalAlert(s3);
        }
    }

    private byte[] getBufferContents() {
        byte[] byteArray = this.buffer.toByteArray();
        this.buffer.reset();
        return byteArray;
    }

    public void checkRecordHeader(byte[] bArr) {
        checkType(TlsUtils.readUint8(bArr, 0), (short) 10);
        if (this.restrictReadVersion) {
            ProtocolVersion version = TlsUtils.readVersion(bArr, 1);
            ProtocolVersion protocolVersion = this.readVersion;
            if (protocolVersion != null && !version.equals(protocolVersion)) {
                throw new TlsFatalAlert((short) 47);
            }
        } else if ((TlsUtils.readVersionRaw(bArr, 1) & (-256)) != 768) {
            throw new TlsFatalAlert((short) 47);
        }
        checkLength(TlsUtils.readUint16(bArr, 3), this.ciphertextLimit, (short) 22);
    }

    public byte[] decodeAndVerify(short s2, InputStream inputStream, int i2) throws IOException {
        byte[] fully = TlsUtils.readFully(i2, inputStream);
        byte[] bArrDecodeCiphertext = this.readCipher.decodeCiphertext(this.readSeqNo.nextValue((short) 10), s2, fully, 0, fully.length);
        checkLength(bArrDecodeCiphertext.length, this.compressedLimit, (short) 22);
        OutputStream outputStreamDecompress = this.readCompression.decompress(this.buffer);
        if (outputStreamDecompress != this.buffer) {
            outputStreamDecompress.write(bArrDecodeCiphertext, 0, bArrDecodeCiphertext.length);
            outputStreamDecompress.flush();
            bArrDecodeCiphertext = getBufferContents();
        }
        checkLength(bArrDecodeCiphertext.length, this.plaintextLimit, (short) 30);
        if (bArrDecodeCiphertext.length >= 1 || s2 == 23) {
            return bArrDecodeCiphertext;
        }
        throw new TlsFatalAlert((short) 47);
    }

    public void finaliseHandshake() throws TlsFatalAlert {
        TlsCompression tlsCompression = this.readCompression;
        TlsCompression tlsCompression2 = this.pendingCompression;
        if (tlsCompression == tlsCompression2 && this.writeCompression == tlsCompression2) {
            TlsCipher tlsCipher = this.readCipher;
            TlsCipher tlsCipher2 = this.pendingCipher;
            if (tlsCipher == tlsCipher2 && this.writeCipher == tlsCipher2) {
                this.pendingCompression = null;
                this.pendingCipher = null;
                return;
            }
        }
        throw new TlsFatalAlert((short) 40);
    }

    public void flush() {
        this.output.flush();
    }

    public TlsHandshakeHash getHandshakeHash() {
        return this.handshakeHash;
    }

    public OutputStream getHandshakeHashUpdater() {
        return this.handshakeHashUpdater;
    }

    public int getPlaintextLimit() {
        return this.plaintextLimit;
    }

    public ProtocolVersion getReadVersion() {
        return this.readVersion;
    }

    public void init(TlsContext tlsContext) {
        TlsNullCipher tlsNullCipher = new TlsNullCipher(tlsContext);
        this.readCipher = tlsNullCipher;
        this.writeCipher = tlsNullCipher;
        DeferredHash deferredHash = new DeferredHash();
        this.handshakeHash = deferredHash;
        deferredHash.init(tlsContext);
        setPlaintextLimit(DEFAULT_PLAINTEXT_LIMIT);
    }

    public void notifyHelloComplete() {
        this.handshakeHash = this.handshakeHash.notifyPRFDetermined();
    }

    public TlsHandshakeHash prepareToFinish() {
        TlsHandshakeHash tlsHandshakeHash = this.handshakeHash;
        this.handshakeHash = tlsHandshakeHash.stopTracking();
        return tlsHandshakeHash;
    }

    public boolean readRecord() throws IOException {
        byte[] allOrNothing = TlsUtils.readAllOrNothing(5, this.input);
        if (allOrNothing == null) {
            return false;
        }
        short uint8 = TlsUtils.readUint8(allOrNothing, 0);
        checkType(uint8, (short) 10);
        if (this.restrictReadVersion) {
            ProtocolVersion version = TlsUtils.readVersion(allOrNothing, 1);
            ProtocolVersion protocolVersion = this.readVersion;
            if (protocolVersion == null) {
                this.readVersion = version;
            } else if (!version.equals(protocolVersion)) {
                throw new TlsFatalAlert((short) 47);
            }
        } else if ((TlsUtils.readVersionRaw(allOrNothing, 1) & (-256)) != 768) {
            throw new TlsFatalAlert((short) 47);
        }
        int uint16 = TlsUtils.readUint16(allOrNothing, 3);
        checkLength(uint16, this.ciphertextLimit, (short) 22);
        byte[] bArrDecodeAndVerify = decodeAndVerify(uint8, this.input, uint16);
        this.handler.processRecord(uint8, bArrDecodeAndVerify, 0, bArrDecodeAndVerify.length);
        return true;
    }

    public void receivedReadCipherSpec() {
        TlsCipher tlsCipher;
        TlsCompression tlsCompression = this.pendingCompression;
        if (tlsCompression == null || (tlsCipher = this.pendingCipher) == null) {
            throw new TlsFatalAlert((short) 40);
        }
        this.readCompression = tlsCompression;
        this.readCipher = tlsCipher;
        this.readSeqNo = new SequenceNumber();
    }

    public void safeClose() {
        try {
            this.input.close();
        } catch (IOException unused) {
        }
        try {
            this.output.close();
        } catch (IOException unused2) {
        }
    }

    public void sentWriteCipherSpec() {
        TlsCipher tlsCipher;
        TlsCompression tlsCompression = this.pendingCompression;
        if (tlsCompression == null || (tlsCipher = this.pendingCipher) == null) {
            throw new TlsFatalAlert((short) 40);
        }
        this.writeCompression = tlsCompression;
        this.writeCipher = tlsCipher;
        this.writeSeqNo = new SequenceNumber();
    }

    public void setPendingConnectionState(TlsCompression tlsCompression, TlsCipher tlsCipher) {
        this.pendingCompression = tlsCompression;
        this.pendingCipher = tlsCipher;
    }

    public void setPlaintextLimit(int i2) {
        this.plaintextLimit = i2;
        this.compressedLimit = i2 + 1024;
        this.ciphertextLimit = i2 + 2048;
    }

    public void setReadVersion(ProtocolVersion protocolVersion) {
        this.readVersion = protocolVersion;
    }

    public void setRestrictReadVersion(boolean z2) {
        this.restrictReadVersion = z2;
    }

    public void setWriteVersion(ProtocolVersion protocolVersion) {
        this.writeVersion = protocolVersion;
    }

    public void writeRecord(short s2, byte[] bArr, int i2, int i3) {
        byte[] bArrEncodePlaintext;
        if (this.writeVersion == null) {
            return;
        }
        checkType(s2, (short) 80);
        checkLength(i3, this.plaintextLimit, (short) 80);
        if (i3 < 1 && s2 != 23) {
            throw new TlsFatalAlert((short) 80);
        }
        OutputStream outputStreamCompress = this.writeCompression.compress(this.buffer);
        long jNextValue = this.writeSeqNo.nextValue((short) 80);
        if (outputStreamCompress == this.buffer) {
            bArrEncodePlaintext = this.writeCipher.encodePlaintext(jNextValue, s2, bArr, i2, i3);
        } else {
            outputStreamCompress.write(bArr, i2, i3);
            outputStreamCompress.flush();
            byte[] bufferContents = getBufferContents();
            checkLength(bufferContents.length, i3 + 1024, (short) 80);
            bArrEncodePlaintext = this.writeCipher.encodePlaintext(jNextValue, s2, bufferContents, 0, bufferContents.length);
        }
        checkLength(bArrEncodePlaintext.length, this.ciphertextLimit, (short) 80);
        byte[] bArr2 = new byte[bArrEncodePlaintext.length + 5];
        TlsUtils.writeUint8(s2, bArr2, 0);
        TlsUtils.writeVersion(this.writeVersion, bArr2, 1);
        TlsUtils.writeUint16(bArrEncodePlaintext.length, bArr2, 3);
        System.arraycopy(bArrEncodePlaintext, 0, bArr2, 5, bArrEncodePlaintext.length);
        this.output.write(bArr2);
        this.output.flush();
    }
}

package org.spongycastle.crypto.tls;

import org.spongycastle.asn1.cmc.BodyPartID;

/* JADX INFO: loaded from: classes.dex */
class DTLSRecordLayer implements DatagramTransport {
    private static final int MAX_FRAGMENT_LENGTH = 16384;
    private static final int RECORD_HEADER_LENGTH = 13;
    private static final long RETRANSMIT_TIMEOUT = 240000;
    private static final long TCP_MSL = 120000;
    private final TlsContext context;
    private DTLSEpoch currentEpoch;
    private final TlsPeer peer;
    private DTLSEpoch pendingEpoch;
    private volatile int plaintextLimit;
    private DTLSEpoch readEpoch;
    private final DatagramTransport transport;
    private DTLSEpoch writeEpoch;
    private final ByteQueue recordQueue = new ByteQueue();
    private volatile boolean closed = false;
    private volatile boolean failed = false;
    private volatile ProtocolVersion readVersion = null;
    private volatile ProtocolVersion writeVersion = null;
    private DTLSHandshakeRetransmit retransmit = null;
    private DTLSEpoch retransmitEpoch = null;
    private long retransmitExpiry = 0;
    private volatile boolean inHandshake = true;

    public DTLSRecordLayer(DatagramTransport datagramTransport, TlsContext tlsContext, TlsPeer tlsPeer, short s2) {
        this.transport = datagramTransport;
        this.context = tlsContext;
        this.peer = tlsPeer;
        DTLSEpoch dTLSEpoch = new DTLSEpoch(0, new TlsNullCipher(tlsContext));
        this.currentEpoch = dTLSEpoch;
        this.pendingEpoch = null;
        this.readEpoch = dTLSEpoch;
        this.writeEpoch = dTLSEpoch;
        setPlaintextLimit(16384);
    }

    private void closeTransport() {
        if (this.closed) {
            return;
        }
        try {
            if (!this.failed) {
                warn((short) 0, null);
            }
            this.transport.close();
        } catch (Exception unused) {
        }
        this.closed = true;
    }

    private static long getMacSequenceNumber(int i2, long j2) {
        return j2 | ((((long) i2) & BodyPartID.bodyIdMax) << 48);
    }

    private void raiseAlert(short s2, short s3, String str, Throwable th) throws TlsFatalAlert {
        this.peer.notifyAlertRaised(s2, s3, str, th);
        sendRecord((short) 21, new byte[]{(byte) s2, (byte) s3}, 0, 2);
    }

    private int receiveRecord(byte[] bArr, int i2, int i3, int i4) {
        int uint16;
        int uint162;
        if (this.recordQueue.available() <= 0) {
            int iReceive = this.transport.receive(bArr, i2, i3, i4);
            if (iReceive < 13 || iReceive <= (uint16 = TlsUtils.readUint16(bArr, i2 + 11) + 13)) {
                return iReceive;
            }
            this.recordQueue.addData(bArr, i2 + uint16, iReceive - uint16);
            return uint16;
        }
        if (this.recordQueue.available() >= 13) {
            byte[] bArr2 = new byte[2];
            this.recordQueue.read(bArr2, 0, 2, 11);
            uint162 = TlsUtils.readUint16(bArr2, 0);
        } else {
            uint162 = 0;
        }
        int iMin = Math.min(this.recordQueue.available(), uint162 + 13);
        this.recordQueue.removeData(bArr, i2, iMin, 0);
        return iMin;
    }

    private void sendRecord(short s2, byte[] bArr, int i2, int i3) throws TlsFatalAlert {
        if (this.writeVersion == null) {
            return;
        }
        if (i3 > this.plaintextLimit) {
            throw new TlsFatalAlert((short) 80);
        }
        if (i3 < 1 && s2 != 23) {
            throw new TlsFatalAlert((short) 80);
        }
        int epoch = this.writeEpoch.getEpoch();
        long jAllocateSequenceNumber = this.writeEpoch.allocateSequenceNumber();
        byte[] bArrEncodePlaintext = this.writeEpoch.getCipher().encodePlaintext(getMacSequenceNumber(epoch, jAllocateSequenceNumber), s2, bArr, i2, i3);
        int length = bArrEncodePlaintext.length + 13;
        byte[] bArr2 = new byte[length];
        TlsUtils.writeUint8(s2, bArr2, 0);
        TlsUtils.writeVersion(this.writeVersion, bArr2, 1);
        TlsUtils.writeUint16(epoch, bArr2, 3);
        TlsUtils.writeUint48(jAllocateSequenceNumber, bArr2, 5);
        TlsUtils.writeUint16(bArrEncodePlaintext.length, bArr2, 11);
        System.arraycopy(bArrEncodePlaintext, 0, bArr2, 13, bArrEncodePlaintext.length);
        this.transport.send(bArr2, 0, length);
    }

    @Override // org.spongycastle.crypto.tls.DatagramTransport
    public void close() throws TlsFatalAlert {
        if (this.closed) {
            return;
        }
        if (this.inHandshake) {
            warn((short) 90, "User canceled handshake");
        }
        closeTransport();
    }

    public void fail(short s2) {
        if (this.closed) {
            return;
        }
        try {
            raiseAlert((short) 2, s2, null, null);
        } catch (Exception unused) {
        }
        this.failed = true;
        closeTransport();
    }

    public void failed() {
        if (this.closed) {
            return;
        }
        this.failed = true;
        closeTransport();
    }

    public int getReadEpoch() {
        return this.readEpoch.getEpoch();
    }

    public ProtocolVersion getReadVersion() {
        return this.readVersion;
    }

    @Override // org.spongycastle.crypto.tls.DatagramTransport
    public int getReceiveLimit() {
        return Math.min(this.plaintextLimit, this.readEpoch.getCipher().getPlaintextLimit(this.transport.getReceiveLimit() - 13));
    }

    @Override // org.spongycastle.crypto.tls.DatagramTransport
    public int getSendLimit() {
        return Math.min(this.plaintextLimit, this.writeEpoch.getCipher().getPlaintextLimit(this.transport.getSendLimit() - 13));
    }

    public void handshakeSuccessful(DTLSHandshakeRetransmit dTLSHandshakeRetransmit) {
        DTLSEpoch dTLSEpoch = this.readEpoch;
        DTLSEpoch dTLSEpoch2 = this.currentEpoch;
        if (dTLSEpoch == dTLSEpoch2 || this.writeEpoch == dTLSEpoch2) {
            throw new IllegalStateException();
        }
        if (dTLSHandshakeRetransmit != null) {
            this.retransmit = dTLSHandshakeRetransmit;
            this.retransmitEpoch = dTLSEpoch2;
            this.retransmitExpiry = System.currentTimeMillis() + RETRANSMIT_TIMEOUT;
        }
        this.inHandshake = false;
        this.currentEpoch = this.pendingEpoch;
        this.pendingEpoch = null;
    }

    public void initPendingEpoch(TlsCipher tlsCipher) {
        if (this.pendingEpoch != null) {
            throw new IllegalStateException();
        }
        this.pendingEpoch = new DTLSEpoch(this.writeEpoch.getEpoch() + 1, tlsCipher);
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x00eb, code lost:
    
        if (r18.inHandshake != false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00ef, code lost:
    
        if (r18.retransmit == null) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00f1, code lost:
    
        r18.retransmit = null;
        r18.retransmitEpoch = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00f6, code lost:
    
        java.lang.System.arraycopy(r3, 0, r19, r20, r3.length);
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0100, code lost:
    
        return r3.length;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0036 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [org.spongycastle.crypto.tls.DTLSEpoch, org.spongycastle.crypto.tls.DTLSHandshakeRetransmit] */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v4 */
    @Override // org.spongycastle.crypto.tls.DatagramTransport
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int receive(byte[] r19, int r20, int r21, int r22) throws org.spongycastle.crypto.tls.TlsFatalAlert {
        /*
            Method dump skipped, instruction units count: 352
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.tls.DTLSRecordLayer.receive(byte[], int, int, int):int");
    }

    public void resetWriteEpoch() {
        DTLSEpoch dTLSEpoch = this.retransmitEpoch;
        if (dTLSEpoch != null) {
            this.writeEpoch = dTLSEpoch;
        } else {
            this.writeEpoch = this.currentEpoch;
        }
    }

    @Override // org.spongycastle.crypto.tls.DatagramTransport
    public void send(byte[] bArr, int i2, int i3) throws TlsFatalAlert {
        short s2;
        if (this.inHandshake || this.writeEpoch == this.retransmitEpoch) {
            if (TlsUtils.readUint8(bArr, i2) == 20) {
                DTLSEpoch dTLSEpoch = this.inHandshake ? this.pendingEpoch : this.writeEpoch == this.retransmitEpoch ? this.currentEpoch : null;
                if (dTLSEpoch == null) {
                    throw new IllegalStateException();
                }
                sendRecord((short) 20, new byte[]{1}, 0, 1);
                this.writeEpoch = dTLSEpoch;
            }
            s2 = 22;
        } else {
            s2 = 23;
        }
        sendRecord(s2, bArr, i2, i3);
    }

    public void setPlaintextLimit(int i2) {
        this.plaintextLimit = i2;
    }

    public void setReadVersion(ProtocolVersion protocolVersion) {
        this.readVersion = protocolVersion;
    }

    public void setWriteVersion(ProtocolVersion protocolVersion) {
        this.writeVersion = protocolVersion;
    }

    public void warn(short s2, String str) throws TlsFatalAlert {
        raiseAlert((short) 1, s2, str, null);
    }
}

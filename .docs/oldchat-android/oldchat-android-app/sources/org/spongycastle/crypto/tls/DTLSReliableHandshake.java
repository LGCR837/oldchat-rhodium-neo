package org.spongycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.util.Integers;

/* JADX INFO: loaded from: classes.dex */
class DTLSReliableHandshake {
    private static final int MAX_RECEIVE_AHEAD = 16;
    private static final int MESSAGE_HEADER_LENGTH = 12;
    private TlsHandshakeHash handshakeHash;
    private DTLSRecordLayer recordLayer;
    private Hashtable currentInboundFlight = new Hashtable();
    private Hashtable previousInboundFlight = null;
    private Vector outboundFlight = new Vector();
    private boolean sending = true;
    private int message_seq = 0;
    private int next_receive_seq = 0;

    public static class Message {
        private final byte[] body;
        private final int message_seq;
        private final short msg_type;

        public byte[] getBody() {
            return this.body;
        }

        public int getSeq() {
            return this.message_seq;
        }

        public short getType() {
            return this.msg_type;
        }

        private Message(int i2, short s2, byte[] bArr) {
            this.message_seq = i2;
            this.msg_type = s2;
            this.body = bArr;
        }
    }

    public static class RecordLayerBuffer extends ByteArrayOutputStream {
        public RecordLayerBuffer(int i2) {
            super(i2);
        }

        public void sendToRecordLayer(DTLSRecordLayer dTLSRecordLayer) throws TlsFatalAlert {
            dTLSRecordLayer.send(((ByteArrayOutputStream) this).buf, 0, ((ByteArrayOutputStream) this).count);
            ((ByteArrayOutputStream) this).buf = null;
        }
    }

    public DTLSReliableHandshake(TlsContext tlsContext, DTLSRecordLayer dTLSRecordLayer) {
        this.recordLayer = dTLSRecordLayer;
        DeferredHash deferredHash = new DeferredHash();
        this.handshakeHash = deferredHash;
        deferredHash.init(tlsContext);
    }

    private int backOff(int i2) {
        return Math.min(i2 * 2, 60000);
    }

    private static boolean checkAll(Hashtable hashtable) {
        Enumeration enumerationElements = hashtable.elements();
        while (enumerationElements.hasMoreElements()) {
            if (((DTLSReassembler) enumerationElements.nextElement()).getBodyIfComplete() == null) {
                return false;
            }
        }
        return true;
    }

    private void checkInboundFlight() {
        Enumeration enumerationKeys = this.currentInboundFlight.keys();
        while (enumerationKeys.hasMoreElements()) {
            ((Integer) enumerationKeys.nextElement()).intValue();
        }
    }

    private Message getPendingMessage() {
        byte[] bodyIfComplete;
        DTLSReassembler dTLSReassembler = (DTLSReassembler) this.currentInboundFlight.get(Integers.valueOf(this.next_receive_seq));
        if (dTLSReassembler == null || (bodyIfComplete = dTLSReassembler.getBodyIfComplete()) == null) {
            return null;
        }
        this.previousInboundFlight = null;
        int i2 = this.next_receive_seq;
        this.next_receive_seq = i2 + 1;
        return updateHandshakeMessagesDigest(new Message(i2, dTLSReassembler.getMsgType(), bodyIfComplete));
    }

    private void prepareInboundFlight(Hashtable hashtable) {
        resetAll(this.currentInboundFlight);
        this.previousInboundFlight = this.currentInboundFlight;
        this.currentInboundFlight = hashtable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean processRecord(int i2, int i3, byte[] bArr, int i4, int i5) throws IOException {
        int uint24;
        int uint242;
        DTLSReassembler dTLSReassembler;
        boolean z2 = false;
        int i6 = i4;
        int i7 = i5;
        boolean z3 = false;
        while (i7 >= 12 && i7 >= (uint242 = (uint24 = TlsUtils.readUint24(bArr, i6 + 9)) + 12)) {
            int uint243 = TlsUtils.readUint24(bArr, i6 + 1);
            int uint244 = TlsUtils.readUint24(bArr, i6 + 6);
            if (uint244 + uint24 > uint243) {
                break;
            }
            short uint8 = TlsUtils.readUint8(bArr, i6);
            if (i3 != (uint8 == 20 ? 1 : 0)) {
                break;
            }
            int uint16 = TlsUtils.readUint16(bArr, i6 + 4);
            int i8 = this.next_receive_seq;
            if (uint16 < i8 + i2) {
                if (uint16 >= i8) {
                    DTLSReassembler dTLSReassembler2 = (DTLSReassembler) this.currentInboundFlight.get(Integers.valueOf(uint16));
                    if (dTLSReassembler2 == null) {
                        dTLSReassembler2 = new DTLSReassembler(uint8, uint243);
                        this.currentInboundFlight.put(Integers.valueOf(uint16), dTLSReassembler2);
                    }
                    dTLSReassembler2.contributeFragment(uint8, uint243, bArr, i6 + 12, uint244, uint24);
                } else {
                    Hashtable hashtable = this.previousInboundFlight;
                    if (hashtable != null && (dTLSReassembler = (DTLSReassembler) hashtable.get(Integers.valueOf(uint16))) != null) {
                        dTLSReassembler.contributeFragment(uint8, uint243, bArr, i6 + 12, uint244, uint24);
                        z3 = true;
                    }
                }
            }
            i6 += uint242;
            i7 -= uint242;
        }
        if (z3 && checkAll(this.previousInboundFlight)) {
            z2 = true;
        }
        if (z2) {
            resendOutboundFlight();
            resetAll(this.previousInboundFlight);
        }
        return z2;
    }

    private void resendOutboundFlight() throws IOException {
        this.recordLayer.resetWriteEpoch();
        for (int i2 = 0; i2 < this.outboundFlight.size(); i2++) {
            writeMessage((Message) this.outboundFlight.elementAt(i2));
        }
    }

    private static void resetAll(Hashtable hashtable) {
        Enumeration enumerationElements = hashtable.elements();
        while (enumerationElements.hasMoreElements()) {
            ((DTLSReassembler) enumerationElements.nextElement()).reset();
        }
    }

    private Message updateHandshakeMessagesDigest(Message message) {
        if (message.getType() != 0) {
            byte[] body = message.getBody();
            byte[] bArr = new byte[12];
            TlsUtils.writeUint8(message.getType(), bArr, 0);
            TlsUtils.writeUint24(body.length, bArr, 1);
            TlsUtils.writeUint16(message.getSeq(), bArr, 4);
            TlsUtils.writeUint24(0, bArr, 6);
            TlsUtils.writeUint24(body.length, bArr, 9);
            this.handshakeHash.update(bArr, 0, 12);
            this.handshakeHash.update(body, 0, body.length);
        }
        return message;
    }

    private void writeHandshakeFragment(Message message, int i2, int i3) throws IOException {
        RecordLayerBuffer recordLayerBuffer = new RecordLayerBuffer(i3 + 12);
        TlsUtils.writeUint8(message.getType(), (OutputStream) recordLayerBuffer);
        TlsUtils.writeUint24(message.getBody().length, recordLayerBuffer);
        TlsUtils.writeUint16(message.getSeq(), recordLayerBuffer);
        TlsUtils.writeUint24(i2, recordLayerBuffer);
        TlsUtils.writeUint24(i3, recordLayerBuffer);
        recordLayerBuffer.write(message.getBody(), i2, i3);
        recordLayerBuffer.sendToRecordLayer(this.recordLayer);
    }

    private void writeMessage(Message message) throws IOException {
        int sendLimit = this.recordLayer.getSendLimit() - 12;
        if (sendLimit < 1) {
            throw new TlsFatalAlert((short) 80);
        }
        int length = message.getBody().length;
        int i2 = 0;
        do {
            int iMin = Math.min(length - i2, sendLimit);
            writeHandshakeFragment(message, i2, iMin);
            i2 += iMin;
        } while (i2 < length);
    }

    public void finish() {
        DTLSHandshakeRetransmit dTLSHandshakeRetransmit = null;
        if (this.sending) {
            prepareInboundFlight(null);
            if (this.previousInboundFlight != null) {
                dTLSHandshakeRetransmit = new DTLSHandshakeRetransmit() { // from class: org.spongycastle.crypto.tls.DTLSReliableHandshake.1
                    @Override // org.spongycastle.crypto.tls.DTLSHandshakeRetransmit
                    public void receivedHandshakeRecord(int i2, byte[] bArr, int i3, int i4) throws IOException {
                        DTLSReliableHandshake.this.processRecord(0, i2, bArr, i3, i4);
                    }
                };
            }
        } else {
            checkInboundFlight();
        }
        this.recordLayer.handshakeSuccessful(dTLSHandshakeRetransmit);
    }

    public TlsHandshakeHash getHandshakeHash() {
        return this.handshakeHash;
    }

    public void notifyHelloComplete() {
        this.handshakeHash = this.handshakeHash.notifyPRFDetermined();
    }

    public TlsHandshakeHash prepareToFinish() {
        TlsHandshakeHash tlsHandshakeHash = this.handshakeHash;
        this.handshakeHash = tlsHandshakeHash.stopTracking();
        return tlsHandshakeHash;
    }

    public Message receiveMessage() throws IOException {
        Message pendingMessage;
        if (this.sending) {
            this.sending = false;
            prepareInboundFlight(new Hashtable());
        }
        byte[] bArr = null;
        int iBackOff = 1000;
        while (true) {
            try {
                pendingMessage = getPendingMessage();
            } catch (IOException unused) {
            }
            if (pendingMessage != null) {
                return pendingMessage;
            }
            int receiveLimit = this.recordLayer.getReceiveLimit();
            if (bArr == null || bArr.length < receiveLimit) {
                bArr = new byte[receiveLimit];
            }
            int iReceive = this.recordLayer.receive(bArr, 0, receiveLimit, iBackOff);
            if (iReceive < 0) {
                resendOutboundFlight();
                iBackOff = backOff(iBackOff);
            } else if (processRecord(16, this.recordLayer.getReadEpoch(), bArr, 0, iReceive)) {
                iBackOff = backOff(iBackOff);
            }
        }
    }

    public byte[] receiveMessageBody(short s2) throws IOException {
        Message messageReceiveMessage = receiveMessage();
        if (messageReceiveMessage.getType() == s2) {
            return messageReceiveMessage.getBody();
        }
        throw new TlsFatalAlert((short) 10);
    }

    public void resetHandshakeMessagesDigest() {
        this.handshakeHash.reset();
    }

    public void sendMessage(short s2, byte[] bArr) throws IOException {
        TlsUtils.checkUint24(bArr.length);
        if (!this.sending) {
            checkInboundFlight();
            this.sending = true;
            this.outboundFlight.removeAllElements();
        }
        int i2 = this.message_seq;
        this.message_seq = i2 + 1;
        Message message = new Message(i2, s2, bArr);
        this.outboundFlight.addElement(message);
        writeMessage(message);
        updateHandshakeMessagesDigest(message);
    }
}

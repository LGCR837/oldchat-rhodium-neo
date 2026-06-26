package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.prng.RandomGenerator;
import org.spongycastle.crypto.tls.SessionParameters;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Integers;

/* JADX INFO: loaded from: classes.dex */
public abstract class TlsProtocol {
    protected static final short ADS_MODE_0_N = 1;
    protected static final short ADS_MODE_0_N_FIRSTONLY = 2;
    protected static final short ADS_MODE_1_Nsub1 = 0;
    protected static final short CS_CERTIFICATE_REQUEST = 7;
    protected static final short CS_CERTIFICATE_STATUS = 5;
    protected static final short CS_CERTIFICATE_VERIFY = 12;
    protected static final short CS_CLIENT_CERTIFICATE = 10;
    protected static final short CS_CLIENT_FINISHED = 13;
    protected static final short CS_CLIENT_HELLO = 1;
    protected static final short CS_CLIENT_KEY_EXCHANGE = 11;
    protected static final short CS_CLIENT_SUPPLEMENTAL_DATA = 9;
    protected static final short CS_END = 16;
    protected static final short CS_SERVER_CERTIFICATE = 4;
    protected static final short CS_SERVER_FINISHED = 15;
    protected static final short CS_SERVER_HELLO = 2;
    protected static final short CS_SERVER_HELLO_DONE = 8;
    protected static final short CS_SERVER_KEY_EXCHANGE = 6;
    protected static final short CS_SERVER_SESSION_TICKET = 14;
    protected static final short CS_SERVER_SUPPLEMENTAL_DATA = 3;
    protected static final short CS_START = 0;
    protected static final Integer EXT_RenegotiationInfo = Integers.valueOf(65281);
    protected static final Integer EXT_SessionTicket = Integers.valueOf(35);
    private ByteQueue alertQueue;
    protected boolean allowCertificateStatus;
    private volatile boolean appDataReady;
    private volatile boolean appDataSplitEnabled;
    private volatile int appDataSplitMode;
    private ByteQueue applicationDataQueue;
    protected boolean blocking;
    protected Hashtable clientExtensions;
    private volatile boolean closed;
    protected short connection_state;
    protected boolean expectSessionTicket;
    private byte[] expected_verify_data;
    private volatile boolean failedWithError;
    private ByteQueue handshakeQueue;
    protected ByteQueueInputStream inputBuffers;
    protected int[] offeredCipherSuites;
    protected short[] offeredCompressionMethods;
    protected ByteQueueOutputStream outputBuffer;
    protected Certificate peerCertificate;
    protected boolean receivedChangeCipherSpec;
    RecordStream recordStream;
    protected boolean resumedSession;
    protected SecureRandom secureRandom;
    protected boolean secure_renegotiation;
    protected SecurityParameters securityParameters;
    protected Hashtable serverExtensions;
    protected SessionParameters sessionParameters;
    private TlsInputStream tlsInputStream;
    private TlsOutputStream tlsOutputStream;
    protected TlsSession tlsSession;

    public class HandshakeMessage extends ByteArrayOutputStream {
        public HandshakeMessage(TlsProtocol tlsProtocol, short s2) {
            this(s2, 60);
        }

        public void writeToRecordStream() throws IOException {
            int i2 = ((ByteArrayOutputStream) this).count - 4;
            TlsUtils.checkUint24(i2);
            TlsUtils.writeUint24(i2, ((ByteArrayOutputStream) this).buf, 1);
            TlsProtocol.this.writeHandshakeMessage(((ByteArrayOutputStream) this).buf, 0, ((ByteArrayOutputStream) this).count);
            ((ByteArrayOutputStream) this).buf = null;
        }

        public HandshakeMessage(short s2, int i2) throws IOException {
            super(i2 + 4);
            TlsUtils.writeUint8(s2, (OutputStream) this);
            ((ByteArrayOutputStream) this).count += 3;
        }
    }

    public TlsProtocol(InputStream inputStream, OutputStream outputStream, SecureRandom secureRandom) {
        this.applicationDataQueue = new ByteQueue(0);
        this.alertQueue = new ByteQueue(2);
        this.handshakeQueue = new ByteQueue(0);
        this.tlsInputStream = null;
        this.tlsOutputStream = null;
        this.closed = false;
        this.failedWithError = false;
        this.appDataReady = false;
        this.appDataSplitEnabled = true;
        this.appDataSplitMode = 0;
        this.expected_verify_data = null;
        this.tlsSession = null;
        this.sessionParameters = null;
        this.securityParameters = null;
        this.peerCertificate = null;
        this.offeredCipherSuites = null;
        this.offeredCompressionMethods = null;
        this.clientExtensions = null;
        this.serverExtensions = null;
        this.connection_state = (short) 0;
        this.resumedSession = false;
        this.receivedChangeCipherSpec = false;
        this.secure_renegotiation = false;
        this.allowCertificateStatus = false;
        this.expectSessionTicket = false;
        this.blocking = true;
        this.recordStream = new RecordStream(this, inputStream, outputStream);
        this.secureRandom = secureRandom;
    }

    public static void assertEmpty(ByteArrayInputStream byteArrayInputStream) {
        if (byteArrayInputStream.available() > 0) {
            throw new TlsFatalAlert((short) 50);
        }
    }

    public static byte[] createRandomBlock(boolean z2, RandomGenerator randomGenerator) {
        byte[] bArr = new byte[32];
        randomGenerator.nextBytes(bArr);
        if (z2) {
            TlsUtils.writeGMTUnixTime(bArr, 0);
        }
        return bArr;
    }

    public static byte[] createRenegotiationInfo(byte[] bArr) {
        return TlsUtils.encodeOpaque8(bArr);
    }

    public static void establishMasterSecret(TlsContext tlsContext, TlsKeyExchange tlsKeyExchange) {
        byte[] bArrGeneratePremasterSecret = tlsKeyExchange.generatePremasterSecret();
        try {
            tlsContext.getSecurityParameters().masterSecret = TlsUtils.calculateMasterSecret(tlsContext, bArrGeneratePremasterSecret);
        } finally {
            if (bArrGeneratePremasterSecret != null) {
                Arrays.fill(bArrGeneratePremasterSecret, (byte) 0);
            }
        }
    }

    public static byte[] getCurrentPRFHash(TlsContext tlsContext, TlsHandshakeHash tlsHandshakeHash, byte[] bArr) {
        Digest digestForkPRFHash = tlsHandshakeHash.forkPRFHash();
        if (bArr != null && TlsUtils.isSSL(tlsContext)) {
            digestForkPRFHash.update(bArr, 0, bArr.length);
        }
        byte[] bArr2 = new byte[digestForkPRFHash.getDigestSize()];
        digestForkPRFHash.doFinal(bArr2, 0);
        return bArr2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:18:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getPRFAlgorithm(org.spongycastle.crypto.tls.TlsContext r4, int r5) throws org.spongycastle.crypto.tls.TlsFatalAlert {
        /*
            Method dump skipped, instruction units count: 480
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.tls.TlsProtocol.getPRFAlgorithm(org.spongycastle.crypto.tls.TlsContext, int):int");
    }

    private void processAlertQueue() throws TlsFatalAlert, TlsFatalAlertReceived {
        while (this.alertQueue.available() >= 2) {
            byte[] bArrRemoveData = this.alertQueue.removeData(2, 0);
            handleAlertMessage(bArrRemoveData[0], bArrRemoveData[1]);
        }
    }

    private void processApplicationDataQueue() {
    }

    private void processChangeCipherSpec(byte[] bArr, int i2, int i3) throws TlsFatalAlert {
        for (int i4 = 0; i4 < i3; i4++) {
            if (TlsUtils.readUint8(bArr, i2 + i4) != 1) {
                throw new TlsFatalAlert((short) 50);
            }
            if (this.receivedChangeCipherSpec || this.alertQueue.available() > 0 || this.handshakeQueue.available() > 0) {
                throw new TlsFatalAlert((short) 10);
            }
            this.recordStream.receivedReadCipherSpec();
            this.receivedChangeCipherSpec = true;
            handleChangeCipherSpecMessage();
        }
    }

    private void processHandshakeQueue(ByteQueue byteQueue) throws IOException {
        while (byteQueue.available() >= 4) {
            byte[] bArr = new byte[4];
            byteQueue.read(bArr, 0, 4, 0);
            short uint8 = TlsUtils.readUint8(bArr, 0);
            int uint24 = TlsUtils.readUint24(bArr, 1);
            int i2 = uint24 + 4;
            if (byteQueue.available() < i2) {
                return;
            }
            checkReceivedChangeCipherSpec(this.connection_state == 16 || uint8 == 20);
            if (uint8 != 0) {
                if (uint8 == 20) {
                    TlsContext context = getContext();
                    if (this.expected_verify_data == null && context.getSecurityParameters().getMasterSecret() != null) {
                        this.expected_verify_data = createVerifyData(!context.isServer());
                    }
                }
                byteQueue.copyTo(this.recordStream.getHandshakeHashUpdater(), i2);
            }
            byteQueue.removeData(4);
            handleHandshakeMessage(uint8, byteQueue.readFrom(uint24));
        }
    }

    public static Hashtable readExtensions(ByteArrayInputStream byteArrayInputStream) throws TlsFatalAlert {
        if (byteArrayInputStream.available() < 1) {
            return null;
        }
        byte[] opaque16 = TlsUtils.readOpaque16(byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(opaque16);
        Hashtable hashtable = new Hashtable();
        while (byteArrayInputStream2.available() > 0) {
            if (hashtable.put(Integers.valueOf(TlsUtils.readUint16(byteArrayInputStream2)), TlsUtils.readOpaque16(byteArrayInputStream2)) != null) {
                throw new TlsFatalAlert((short) 47);
            }
        }
        return hashtable;
    }

    public static Vector readSupplementalDataMessage(ByteArrayInputStream byteArrayInputStream) {
        byte[] opaque24 = TlsUtils.readOpaque24(byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(opaque24);
        Vector vector = new Vector();
        while (byteArrayInputStream2.available() > 0) {
            vector.addElement(new SupplementalDataEntry(TlsUtils.readUint16(byteArrayInputStream2), TlsUtils.readOpaque16(byteArrayInputStream2)));
        }
        return vector;
    }

    public static void writeExtensions(OutputStream outputStream, Hashtable hashtable) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        writeSelectedExtensions(byteArrayOutputStream, hashtable, true);
        writeSelectedExtensions(byteArrayOutputStream, hashtable, false);
        TlsUtils.writeOpaque16(byteArrayOutputStream.toByteArray(), outputStream);
    }

    public static void writeSelectedExtensions(OutputStream outputStream, Hashtable hashtable, boolean z2) throws IOException {
        Enumeration enumerationKeys = hashtable.keys();
        while (enumerationKeys.hasMoreElements()) {
            Integer num = (Integer) enumerationKeys.nextElement();
            int iIntValue = num.intValue();
            byte[] bArr = (byte[]) hashtable.get(num);
            if (z2 == (bArr.length == 0)) {
                TlsUtils.checkUint16(iIntValue);
                TlsUtils.writeUint16(iIntValue, outputStream);
                TlsUtils.writeOpaque16(bArr, outputStream);
            }
        }
    }

    public static void writeSupplementalData(OutputStream outputStream, Vector vector) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i2 = 0; i2 < vector.size(); i2++) {
            SupplementalDataEntry supplementalDataEntry = (SupplementalDataEntry) vector.elementAt(i2);
            int dataType = supplementalDataEntry.getDataType();
            TlsUtils.checkUint16(dataType);
            TlsUtils.writeUint16(dataType, byteArrayOutputStream);
            TlsUtils.writeOpaque16(supplementalDataEntry.getData(), byteArrayOutputStream);
        }
        TlsUtils.writeOpaque24(byteArrayOutputStream.toByteArray(), outputStream);
    }

    public int applicationDataAvailable() {
        return this.applicationDataQueue.available();
    }

    public void applyMaxFragmentLengthExtension() throws TlsFatalAlert {
        short s2 = this.securityParameters.maxFragmentLength;
        if (s2 >= 0) {
            if (!MaxFragmentLength.isValid(s2)) {
                throw new TlsFatalAlert((short) 80);
            }
            this.recordStream.setPlaintextLimit(1 << (this.securityParameters.maxFragmentLength + CS_SERVER_HELLO_DONE));
        }
    }

    public void blockForHandshake() throws TlsNoCloseNotifyException, TlsFatalAlert, TlsFatalAlertReceived {
        if (this.blocking) {
            while (this.connection_state != 16) {
                if (this.closed) {
                    throw new TlsFatalAlert((short) 80);
                }
                safeReadRecord();
            }
        }
    }

    public void checkReceivedChangeCipherSpec(boolean z2) throws TlsFatalAlert {
        if (z2 != this.receivedChangeCipherSpec) {
            throw new TlsFatalAlert((short) 10);
        }
    }

    public void cleanupHandshake() {
        byte[] bArr = this.expected_verify_data;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
            this.expected_verify_data = null;
        }
        this.securityParameters.clear();
        this.peerCertificate = null;
        this.offeredCipherSuites = null;
        this.offeredCompressionMethods = null;
        this.clientExtensions = null;
        this.serverExtensions = null;
        this.resumedSession = false;
        this.receivedChangeCipherSpec = false;
        this.secure_renegotiation = false;
        this.allowCertificateStatus = false;
        this.expectSessionTicket = false;
    }

    public void close() throws TlsFatalAlert {
        handleClose(true);
    }

    public void closeInput() throws TlsFatalAlert, EOFException {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use closeInput() in blocking mode!");
        }
        if (this.closed) {
            return;
        }
        if (this.inputBuffers.available() > 0) {
            throw new EOFException();
        }
        if (!this.appDataReady) {
            throw new TlsFatalAlert((short) 40);
        }
        throw new TlsNoCloseNotifyException();
    }

    public void completeHandshake() {
        try {
            this.connection_state = (short) 16;
            this.alertQueue.shrink();
            this.handshakeQueue.shrink();
            this.recordStream.finaliseHandshake();
            this.appDataSplitEnabled = !TlsUtils.isTLSv11(getContext());
            if (!this.appDataReady) {
                this.appDataReady = true;
                if (this.blocking) {
                    this.tlsInputStream = new TlsInputStream(this);
                    this.tlsOutputStream = new TlsOutputStream(this);
                }
            }
            if (this.tlsSession != null) {
                if (this.sessionParameters == null) {
                    this.sessionParameters = new SessionParameters.Builder().setCipherSuite(this.securityParameters.getCipherSuite()).setCompressionAlgorithm(this.securityParameters.getCompressionAlgorithm()).setMasterSecret(this.securityParameters.getMasterSecret()).setPeerCertificate(this.peerCertificate).setPSKIdentity(this.securityParameters.getPSKIdentity()).setSRPIdentity(this.securityParameters.getSRPIdentity()).setServerExtensions(this.serverExtensions).build();
                    this.tlsSession = new TlsSessionImpl(this.tlsSession.getSessionID(), this.sessionParameters);
                }
                getContextAdmin().setResumableSession(this.tlsSession);
            }
            getPeer().notifyHandshakeComplete();
            cleanupHandshake();
        } catch (Throwable th) {
            cleanupHandshake();
            throw th;
        }
    }

    public byte[] createVerifyData(boolean z2) {
        TlsContext context = getContext();
        return TlsUtils.calculateVerifyData(context, z2 ? ExporterLabel.server_finished : ExporterLabel.client_finished, getCurrentPRFHash(context, this.recordStream.getHandshakeHash(), z2 ? TlsUtils.SSL_SERVER : TlsUtils.SSL_CLIENT));
    }

    public void flush() {
        this.recordStream.flush();
    }

    public int getAvailableInputBytes() {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use getAvailableInputBytes() in blocking mode! Use getInputStream().available() instead.");
        }
        return applicationDataAvailable();
    }

    public int getAvailableOutputBytes() {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use getAvailableOutputBytes() in blocking mode! Use getOutputStream() instead.");
        }
        return this.outputBuffer.getBuffer().available();
    }

    public abstract TlsContext getContext();

    public abstract AbstractTlsContext getContextAdmin();

    public InputStream getInputStream() {
        if (this.blocking) {
            return this.tlsInputStream;
        }
        throw new IllegalStateException("Cannot use InputStream in non-blocking mode! Use offerInput() instead.");
    }

    public OutputStream getOutputStream() {
        if (this.blocking) {
            return this.tlsOutputStream;
        }
        throw new IllegalStateException("Cannot use OutputStream in non-blocking mode! Use offerOutput() instead.");
    }

    public abstract TlsPeer getPeer();

    public void handleAlertMessage(short s2, short s3) throws TlsFatalAlert, TlsFatalAlertReceived {
        getPeer().notifyAlertReceived(s2, s3);
        if (s2 == 1) {
            handleAlertWarningMessage(s3);
        } else {
            handleFailure();
            throw new TlsFatalAlertReceived(s3);
        }
    }

    public void handleAlertWarningMessage(short s2) throws TlsFatalAlert {
        if (s2 == 0) {
            if (!this.appDataReady) {
                throw new TlsFatalAlert((short) 40);
            }
            handleClose(false);
        }
    }

    public void handleChangeCipherSpecMessage() {
    }

    public void handleClose(boolean z2) throws TlsFatalAlert {
        if (this.closed) {
            return;
        }
        this.closed = true;
        if (z2 && !this.appDataReady) {
            raiseAlertWarning((short) 90, "User canceled handshake");
        }
        raiseAlertWarning((short) 0, "Connection closed");
        this.recordStream.safeClose();
        if (this.appDataReady) {
            return;
        }
        cleanupHandshake();
    }

    public void handleException(short s2, String str, Throwable th) {
        if (this.closed) {
            return;
        }
        raiseAlertFatal(s2, str, th);
        handleFailure();
    }

    public void handleFailure() {
        this.closed = true;
        this.failedWithError = true;
        invalidateSession();
        this.recordStream.safeClose();
        if (this.appDataReady) {
            return;
        }
        cleanupHandshake();
    }

    public abstract void handleHandshakeMessage(short s2, ByteArrayInputStream byteArrayInputStream);

    public void invalidateSession() {
        SessionParameters sessionParameters = this.sessionParameters;
        if (sessionParameters != null) {
            sessionParameters.clear();
            this.sessionParameters = null;
        }
        TlsSession tlsSession = this.tlsSession;
        if (tlsSession != null) {
            tlsSession.invalidate();
            this.tlsSession = null;
        }
    }

    public boolean isClosed() {
        return this.closed;
    }

    public void offerInput(byte[] bArr) throws IOException {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use offerInput() in blocking mode! Use getInputStream() instead.");
        }
        if (this.closed) {
            throw new IOException("Connection is closed, cannot accept any more input");
        }
        this.inputBuffers.addBytes(bArr);
        while (this.inputBuffers.available() >= 5) {
            byte[] bArr2 = new byte[5];
            this.inputBuffers.peek(bArr2);
            if (this.inputBuffers.available() < TlsUtils.readUint16(bArr2, 3) + 5) {
                safeCheckRecordHeader(bArr2);
                return;
            }
            safeReadRecord();
            if (this.closed) {
                if (this.connection_state != 16) {
                    throw new TlsFatalAlert((short) 80);
                }
                return;
            }
        }
    }

    public void offerOutput(byte[] bArr, int i2, int i3) throws IOException {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use offerOutput() in blocking mode! Use getOutputStream() instead.");
        }
        if (!this.appDataReady) {
            throw new IOException("Application data cannot be sent until the handshake is complete!");
        }
        writeData(bArr, i2, i3);
    }

    public void processFinishedMessage(ByteArrayInputStream byteArrayInputStream) throws TlsFatalAlert, EOFException {
        byte[] bArr = this.expected_verify_data;
        if (bArr == null) {
            throw new TlsFatalAlert((short) 80);
        }
        byte[] fully = TlsUtils.readFully(bArr.length, byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        if (!Arrays.constantTimeAreEqual(this.expected_verify_data, fully)) {
            throw new TlsFatalAlert((short) 51);
        }
    }

    public short processMaxFragmentLengthExtension(Hashtable hashtable, Hashtable hashtable2, short s2) throws TlsFatalAlert {
        short maxFragmentLengthExtension = TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable2);
        if (maxFragmentLengthExtension < 0 || (MaxFragmentLength.isValid(maxFragmentLengthExtension) && (this.resumedSession || maxFragmentLengthExtension == TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable)))) {
            return maxFragmentLengthExtension;
        }
        throw new TlsFatalAlert(s2);
    }

    public void processRecord(short s2, byte[] bArr, int i2, int i3) throws IOException {
        switch (s2) {
            case 20:
                processChangeCipherSpec(bArr, i2, i3);
                return;
            case 21:
                this.alertQueue.addData(bArr, i2, i3);
                processAlertQueue();
                return;
            case 22:
                if (this.handshakeQueue.available() > 0) {
                    this.handshakeQueue.addData(bArr, i2, i3);
                    processHandshakeQueue(this.handshakeQueue);
                    return;
                }
                ByteQueue byteQueue = new ByteQueue(bArr, i2, i3);
                processHandshakeQueue(byteQueue);
                int iAvailable = byteQueue.available();
                if (iAvailable > 0) {
                    this.handshakeQueue.addData(bArr, (i2 + i3) - iAvailable, iAvailable);
                    return;
                }
                return;
            case 23:
                if (!this.appDataReady) {
                    throw new TlsFatalAlert((short) 10);
                }
                this.applicationDataQueue.addData(bArr, i2, i3);
                processApplicationDataQueue();
                return;
            default:
                throw new TlsFatalAlert((short) 80);
        }
    }

    public void raiseAlertFatal(short s2, String str, Throwable th) {
        getPeer().notifyAlertRaised((short) 2, s2, str, th);
        try {
            this.recordStream.writeRecord((short) 21, new byte[]{2, (byte) s2}, 0, 2);
        } catch (Exception unused) {
        }
    }

    public void raiseAlertWarning(short s2, String str) throws TlsFatalAlert {
        getPeer().notifyAlertRaised((short) 1, s2, str, null);
        safeWriteRecord((short) 21, new byte[]{1, (byte) s2}, 0, 2);
    }

    public int readApplicationData(byte[] bArr, int i2, int i3) throws IOException {
        if (i3 < 1) {
            return 0;
        }
        while (this.applicationDataQueue.available() == 0) {
            if (this.closed) {
                if (this.failedWithError) {
                    throw new IOException("Cannot read application data on failed TLS connection");
                }
                if (this.appDataReady) {
                    return -1;
                }
                throw new IllegalStateException("Cannot read application data until initial handshake completed.");
            }
            safeReadRecord();
        }
        int iMin = Math.min(i3, this.applicationDataQueue.available());
        this.applicationDataQueue.removeData(bArr, i2, iMin, 0);
        return iMin;
    }

    public int readInput(byte[] bArr, int i2, int i3) {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use readInput() in blocking mode! Use getInputStream() instead.");
        }
        try {
            return readApplicationData(bArr, i2, Math.min(i3, applicationDataAvailable()));
        } catch (IOException e2) {
            throw new RuntimeException(e2.toString());
        }
    }

    public int readOutput(byte[] bArr, int i2, int i3) {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use readOutput() in blocking mode! Use getOutputStream() instead.");
        }
        int iMin = Math.min(getAvailableOutputBytes(), i3);
        this.outputBuffer.getBuffer().removeData(bArr, i2, iMin, 0);
        return iMin;
    }

    public void refuseRenegotiation() throws TlsFatalAlert {
        if (TlsUtils.isSSL(getContext())) {
            throw new TlsFatalAlert((short) 40);
        }
        raiseAlertWarning((short) 100, "Renegotiation not supported");
    }

    public void safeCheckRecordHeader(byte[] bArr) throws TlsFatalAlert {
        try {
            this.recordStream.checkRecordHeader(bArr);
        } catch (TlsFatalAlert e2) {
            handleException(e2.getAlertDescription(), "Failed to read record", e2);
            throw e2;
        } catch (IOException e3) {
            handleException((short) 80, "Failed to read record", e3);
            throw e3;
        } catch (RuntimeException e4) {
            handleException((short) 80, "Failed to read record", e4);
            throw new TlsFatalAlert((short) 80, e4);
        }
    }

    public void safeReadRecord() throws TlsNoCloseNotifyException, TlsFatalAlert, TlsFatalAlertReceived {
        try {
            if (this.recordStream.readRecord()) {
                return;
            }
            if (!this.appDataReady) {
                throw new TlsFatalAlert((short) 40);
            }
            handleFailure();
            throw new TlsNoCloseNotifyException();
        } catch (RuntimeException e2) {
            handleException((short) 80, "Failed to read record", e2);
            throw new TlsFatalAlert((short) 80, e2);
        } catch (TlsFatalAlert e3) {
            handleException(e3.getAlertDescription(), "Failed to read record", e3);
            throw e3;
        } catch (TlsFatalAlertReceived e4) {
            throw e4;
        } catch (IOException e5) {
            handleException((short) 80, "Failed to read record", e5);
            throw e5;
        }
    }

    public void safeWriteRecord(short s2, byte[] bArr, int i2, int i3) throws TlsFatalAlert {
        try {
            this.recordStream.writeRecord(s2, bArr, i2, i3);
        } catch (TlsFatalAlert e2) {
            handleException(e2.getAlertDescription(), "Failed to write record", e2);
            throw e2;
        } catch (IOException e3) {
            handleException((short) 80, "Failed to write record", e3);
            throw e3;
        } catch (RuntimeException e4) {
            handleException((short) 80, "Failed to write record", e4);
            throw new TlsFatalAlert((short) 80, e4);
        }
    }

    public void sendCertificateMessage(Certificate certificate) throws IOException {
        if (certificate == null) {
            certificate = Certificate.EMPTY_CHAIN;
        }
        if (certificate.isEmpty() && !getContext().isServer()) {
            ProtocolVersion serverVersion = getContext().getServerVersion();
            if (serverVersion.isSSL()) {
                raiseAlertWarning((short) 41, serverVersion.toString() + " client didn't provide credentials");
                return;
            }
        }
        HandshakeMessage handshakeMessage = new HandshakeMessage(this, (short) 11);
        certificate.encode(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }

    public void sendChangeCipherSpecMessage() throws TlsFatalAlert {
        safeWriteRecord((short) 20, new byte[]{1}, 0, 1);
        this.recordStream.sentWriteCipherSpec();
    }

    public void sendFinishedMessage() throws IOException {
        byte[] bArrCreateVerifyData = createVerifyData(getContext().isServer());
        HandshakeMessage handshakeMessage = new HandshakeMessage((short) 20, bArrCreateVerifyData.length);
        handshakeMessage.write(bArrCreateVerifyData);
        handshakeMessage.writeToRecordStream();
    }

    public void sendSupplementalDataMessage(Vector vector) throws IOException {
        HandshakeMessage handshakeMessage = new HandshakeMessage(this, (short) 23);
        writeSupplementalData(handshakeMessage, vector);
        handshakeMessage.writeToRecordStream();
    }

    public void setAppDataSplitMode(int i2) {
        if (i2 >= 0 && i2 <= 2) {
            this.appDataSplitMode = i2;
            return;
        }
        throw new IllegalArgumentException("Illegal appDataSplitMode mode: " + i2);
    }

    public void writeData(byte[] bArr, int i2, int i3) throws IOException {
        if (this.closed) {
            throw new IOException("Cannot write application data on closed/failed TLS connection");
        }
        while (i3 > 0) {
            if (this.appDataSplitEnabled) {
                int i4 = this.appDataSplitMode;
                if (i4 == 1) {
                    safeWriteRecord((short) 23, TlsUtils.EMPTY_BYTES, 0, 0);
                } else if (i4 != 2) {
                    safeWriteRecord((short) 23, bArr, i2, 1);
                    i2++;
                    i3--;
                } else {
                    this.appDataSplitEnabled = false;
                    safeWriteRecord((short) 23, TlsUtils.EMPTY_BYTES, 0, 0);
                }
            }
            if (i3 > 0) {
                int iMin = Math.min(i3, this.recordStream.getPlaintextLimit());
                safeWriteRecord((short) 23, bArr, i2, iMin);
                i2 += iMin;
                i3 -= iMin;
            }
        }
    }

    public void writeHandshakeMessage(byte[] bArr, int i2, int i3) throws IOException {
        if (i3 < 4) {
            throw new TlsFatalAlert((short) 80);
        }
        if (TlsUtils.readUint8(bArr, i2) != 0) {
            this.recordStream.getHandshakeHashUpdater().write(bArr, i2, i3);
        }
        int i4 = 0;
        do {
            int iMin = Math.min(i3 - i4, this.recordStream.getPlaintextLimit());
            safeWriteRecord((short) 22, bArr, i2 + i4, iMin);
            i4 += iMin;
        } while (i4 < i3);
    }

    public TlsProtocol(SecureRandom secureRandom) {
        this.applicationDataQueue = new ByteQueue(0);
        this.alertQueue = new ByteQueue(2);
        this.handshakeQueue = new ByteQueue(0);
        this.tlsInputStream = null;
        this.tlsOutputStream = null;
        this.closed = false;
        this.failedWithError = false;
        this.appDataReady = false;
        this.appDataSplitEnabled = true;
        this.appDataSplitMode = 0;
        this.expected_verify_data = null;
        this.tlsSession = null;
        this.sessionParameters = null;
        this.securityParameters = null;
        this.peerCertificate = null;
        this.offeredCipherSuites = null;
        this.offeredCompressionMethods = null;
        this.clientExtensions = null;
        this.serverExtensions = null;
        this.connection_state = (short) 0;
        this.resumedSession = false;
        this.receivedChangeCipherSpec = false;
        this.secure_renegotiation = false;
        this.allowCertificateStatus = false;
        this.expectSessionTicket = false;
        this.blocking = false;
        this.inputBuffers = new ByteQueueInputStream();
        this.outputBuffer = new ByteQueueOutputStream();
        this.recordStream = new RecordStream(this, this.inputBuffers, this.outputBuffer);
        this.secureRandom = secureRandom;
    }
}

package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.crypto.tls.TlsProtocol;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class TlsClientProtocol extends TlsProtocol {
    protected TlsAuthentication authentication;
    protected CertificateRequest certificateRequest;
    protected CertificateStatus certificateStatus;
    protected TlsKeyExchange keyExchange;
    protected byte[] selectedSessionID;
    protected TlsClient tlsClient;
    TlsClientContextImpl tlsClientContext;

    public TlsClientProtocol(InputStream inputStream, OutputStream outputStream, SecureRandom secureRandom) {
        super(inputStream, outputStream, secureRandom);
        this.tlsClient = null;
        this.tlsClientContext = null;
        this.selectedSessionID = null;
        this.keyExchange = null;
        this.authentication = null;
        this.certificateStatus = null;
        this.certificateRequest = null;
    }

    @Override // org.spongycastle.crypto.tls.TlsProtocol
    public void cleanupHandshake() {
        super.cleanupHandshake();
        this.selectedSessionID = null;
        this.keyExchange = null;
        this.authentication = null;
        this.certificateStatus = null;
        this.certificateRequest = null;
    }

    public void connect(TlsClient tlsClient) throws IOException {
        SessionParameters sessionParametersExportSessionParameters;
        if (tlsClient == null) {
            throw new IllegalArgumentException("'tlsClient' cannot be null");
        }
        if (this.tlsClient != null) {
            throw new IllegalStateException("'connect' can only be called once");
        }
        this.tlsClient = tlsClient;
        SecurityParameters securityParameters = new SecurityParameters();
        this.securityParameters = securityParameters;
        securityParameters.entity = 1;
        this.tlsClientContext = new TlsClientContextImpl(this.secureRandom, this.securityParameters);
        this.securityParameters.clientRandom = TlsProtocol.createRandomBlock(tlsClient.shouldUseGMTUnixTime(), this.tlsClientContext.getNonceRandomGenerator());
        this.tlsClient.init(this.tlsClientContext);
        this.recordStream.init(this.tlsClientContext);
        TlsSession sessionToResume = tlsClient.getSessionToResume();
        if (sessionToResume != null && sessionToResume.isResumable() && (sessionParametersExportSessionParameters = sessionToResume.exportSessionParameters()) != null) {
            this.tlsSession = sessionToResume;
            this.sessionParameters = sessionParametersExportSessionParameters;
        }
        sendClientHelloMessage();
        this.connection_state = (short) 1;
        blockForHandshake();
    }

    @Override // org.spongycastle.crypto.tls.TlsProtocol
    public TlsContext getContext() {
        return this.tlsClientContext;
    }

    @Override // org.spongycastle.crypto.tls.TlsProtocol
    public AbstractTlsContext getContextAdmin() {
        return this.tlsClientContext;
    }

    @Override // org.spongycastle.crypto.tls.TlsProtocol
    public TlsPeer getPeer() {
        return this.tlsClient;
    }

    @Override // org.spongycastle.crypto.tls.TlsProtocol
    public void handleHandshakeMessage(short s2, ByteArrayInputStream byteArrayInputStream) throws IOException {
        TlsCredentials clientCredentials;
        if (this.resumedSession) {
            if (s2 != 20 || this.connection_state != 2) {
                throw new TlsFatalAlert((short) 10);
            }
            processFinishedMessage(byteArrayInputStream);
            this.connection_state = (short) 15;
            sendFinishedMessage();
            this.connection_state = (short) 13;
            completeHandshake();
            return;
        }
        if (s2 == 0) {
            TlsProtocol.assertEmpty(byteArrayInputStream);
            if (this.connection_state == 16) {
                refuseRenegotiation();
                return;
            }
            return;
        }
        if (s2 == 2) {
            if (this.connection_state != 1) {
                throw new TlsFatalAlert((short) 10);
            }
            receiveServerHelloMessage(byteArrayInputStream);
            this.connection_state = (short) 2;
            this.recordStream.notifyHelloComplete();
            applyMaxFragmentLengthExtension();
            if (this.resumedSession) {
                this.securityParameters.masterSecret = Arrays.clone(this.sessionParameters.getMasterSecret());
                this.recordStream.setPendingConnectionState(getPeer().getCompression(), getPeer().getCipher());
                sendChangeCipherSpecMessage();
                return;
            } else {
                invalidateSession();
                byte[] bArr = this.selectedSessionID;
                if (bArr.length > 0) {
                    this.tlsSession = new TlsSessionImpl(bArr, null);
                    return;
                }
                return;
            }
        }
        if (s2 == 4) {
            if (this.connection_state != 13) {
                throw new TlsFatalAlert((short) 10);
            }
            if (!this.expectSessionTicket) {
                throw new TlsFatalAlert((short) 10);
            }
            invalidateSession();
            receiveNewSessionTicketMessage(byteArrayInputStream);
            this.connection_state = (short) 14;
            return;
        }
        if (s2 == 20) {
            short s3 = this.connection_state;
            if (s3 != 13) {
                if (s3 != 14) {
                    throw new TlsFatalAlert((short) 10);
                }
            } else if (this.expectSessionTicket) {
                throw new TlsFatalAlert((short) 10);
            }
            processFinishedMessage(byteArrayInputStream);
            this.connection_state = (short) 15;
            completeHandshake();
            return;
        }
        if (s2 == 22) {
            if (this.connection_state != 4) {
                throw new TlsFatalAlert((short) 10);
            }
            if (!this.allowCertificateStatus) {
                throw new TlsFatalAlert((short) 10);
            }
            this.certificateStatus = CertificateStatus.parse(byteArrayInputStream);
            TlsProtocol.assertEmpty(byteArrayInputStream);
            this.connection_state = (short) 5;
            return;
        }
        if (s2 == 23) {
            if (this.connection_state != 2) {
                throw new TlsFatalAlert((short) 10);
            }
            handleSupplementalData(TlsProtocol.readSupplementalDataMessage(byteArrayInputStream));
            return;
        }
        switch (s2) {
            case 11:
                short s4 = this.connection_state;
                if (s4 == 2) {
                    handleSupplementalData(null);
                } else if (s4 != 3) {
                    throw new TlsFatalAlert((short) 10);
                }
                this.peerCertificate = Certificate.parse(byteArrayInputStream);
                TlsProtocol.assertEmpty(byteArrayInputStream);
                Certificate certificate = this.peerCertificate;
                if (certificate == null || certificate.isEmpty()) {
                    this.allowCertificateStatus = false;
                }
                this.keyExchange.processServerCertificate(this.peerCertificate);
                TlsAuthentication authentication = this.tlsClient.getAuthentication();
                this.authentication = authentication;
                authentication.notifyServerCertificate(this.peerCertificate);
                this.connection_state = (short) 4;
                return;
            case 12:
                short s5 = this.connection_state;
                if (s5 != 2) {
                    if (s5 != 3) {
                        if (s5 != 4 && s5 != 5) {
                            throw new TlsFatalAlert((short) 10);
                        }
                    }
                    this.keyExchange.processServerKeyExchange(byteArrayInputStream);
                    TlsProtocol.assertEmpty(byteArrayInputStream);
                    this.connection_state = (short) 6;
                    return;
                }
                handleSupplementalData(null);
                this.keyExchange.skipServerCredentials();
                this.authentication = null;
                this.keyExchange.processServerKeyExchange(byteArrayInputStream);
                TlsProtocol.assertEmpty(byteArrayInputStream);
                this.connection_state = (short) 6;
                return;
            case 13:
                short s6 = this.connection_state;
                if (s6 == 4 || s6 == 5) {
                    this.keyExchange.skipServerKeyExchange();
                } else if (s6 != 6) {
                    throw new TlsFatalAlert((short) 10);
                }
                if (this.authentication == null) {
                    throw new TlsFatalAlert((short) 40);
                }
                this.certificateRequest = CertificateRequest.parse(getContext(), byteArrayInputStream);
                TlsProtocol.assertEmpty(byteArrayInputStream);
                this.keyExchange.validateCertificateRequest(this.certificateRequest);
                TlsUtils.trackHashAlgorithms(this.recordStream.getHandshakeHash(), this.certificateRequest.getSupportedSignatureAlgorithms());
                this.connection_state = (short) 7;
                return;
            case 14:
                switch (this.connection_state) {
                    case 2:
                        handleSupplementalData(null);
                    case 3:
                        this.keyExchange.skipServerCredentials();
                        this.authentication = null;
                    case 4:
                    case 5:
                        this.keyExchange.skipServerKeyExchange();
                    case 6:
                    case 7:
                        TlsProtocol.assertEmpty(byteArrayInputStream);
                        this.connection_state = (short) 8;
                        this.recordStream.getHandshakeHash().sealHashAlgorithms();
                        Vector clientSupplementalData = this.tlsClient.getClientSupplementalData();
                        if (clientSupplementalData != null) {
                            sendSupplementalDataMessage(clientSupplementalData);
                        }
                        this.connection_state = (short) 9;
                        CertificateRequest certificateRequest = this.certificateRequest;
                        if (certificateRequest == null) {
                            this.keyExchange.skipClientCredentials();
                            clientCredentials = null;
                        } else {
                            clientCredentials = this.authentication.getClientCredentials(certificateRequest);
                            if (clientCredentials == null) {
                                this.keyExchange.skipClientCredentials();
                                sendCertificateMessage(Certificate.EMPTY_CHAIN);
                            } else {
                                this.keyExchange.processClientCredentials(clientCredentials);
                                sendCertificateMessage(clientCredentials.getCertificate());
                            }
                        }
                        this.connection_state = (short) 10;
                        sendClientKeyExchangeMessage();
                        this.connection_state = (short) 11;
                        if (TlsUtils.isSSL(getContext())) {
                            TlsProtocol.establishMasterSecret(getContext(), this.keyExchange);
                        }
                        TlsHandshakeHash tlsHandshakeHashPrepareToFinish = this.recordStream.prepareToFinish();
                        this.securityParameters.sessionHash = TlsProtocol.getCurrentPRFHash(getContext(), tlsHandshakeHashPrepareToFinish, null);
                        if (!TlsUtils.isSSL(getContext())) {
                            TlsProtocol.establishMasterSecret(getContext(), this.keyExchange);
                        }
                        this.recordStream.setPendingConnectionState(getPeer().getCompression(), getPeer().getCipher());
                        if (clientCredentials != null && (clientCredentials instanceof TlsSignerCredentials)) {
                            TlsSignerCredentials tlsSignerCredentials = (TlsSignerCredentials) clientCredentials;
                            SignatureAndHashAlgorithm signatureAndHashAlgorithm = TlsUtils.getSignatureAndHashAlgorithm(getContext(), tlsSignerCredentials);
                            sendCertificateVerifyMessage(new DigitallySigned(signatureAndHashAlgorithm, tlsSignerCredentials.generateCertificateSignature(signatureAndHashAlgorithm == null ? this.securityParameters.getSessionHash() : tlsHandshakeHashPrepareToFinish.getFinalHash(signatureAndHashAlgorithm.getHash()))));
                            this.connection_state = (short) 12;
                        }
                        sendChangeCipherSpecMessage();
                        sendFinishedMessage();
                        this.connection_state = (short) 13;
                        return;
                    default:
                        throw new TlsFatalAlert((short) 10);
                }
                break;
            default:
                throw new TlsFatalAlert((short) 10);
        }
    }

    public void handleSupplementalData(Vector vector) {
        this.tlsClient.processServerSupplementalData(vector);
        this.connection_state = (short) 3;
        TlsKeyExchange keyExchange = this.tlsClient.getKeyExchange();
        this.keyExchange = keyExchange;
        keyExchange.init(getContext());
    }

    public void receiveNewSessionTicketMessage(ByteArrayInputStream byteArrayInputStream) {
        NewSessionTicket newSessionTicket = NewSessionTicket.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        this.tlsClient.notifyNewSessionTicket(newSessionTicket);
    }

    public void receiveServerHelloMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        TlsSession tlsSession;
        ProtocolVersion version = TlsUtils.readVersion(byteArrayInputStream);
        if (version.isDTLS()) {
            throw new TlsFatalAlert((short) 47);
        }
        if (!version.equals(this.recordStream.getReadVersion())) {
            throw new TlsFatalAlert((short) 47);
        }
        if (!version.isEqualOrEarlierVersionOf(getContext().getClientVersion())) {
            throw new TlsFatalAlert((short) 47);
        }
        this.recordStream.setWriteVersion(version);
        getContextAdmin().setServerVersion(version);
        this.tlsClient.notifyServerVersion(version);
        this.securityParameters.serverRandom = TlsUtils.readFully(32, byteArrayInputStream);
        byte[] opaque8 = TlsUtils.readOpaque8(byteArrayInputStream);
        this.selectedSessionID = opaque8;
        if (opaque8.length > 32) {
            throw new TlsFatalAlert((short) 47);
        }
        this.tlsClient.notifySessionID(opaque8);
        byte[] bArr = this.selectedSessionID;
        boolean z2 = false;
        this.resumedSession = bArr.length > 0 && (tlsSession = this.tlsSession) != null && Arrays.areEqual(bArr, tlsSession.getSessionID());
        int uint16 = TlsUtils.readUint16(byteArrayInputStream);
        if (!Arrays.contains(this.offeredCipherSuites, uint16) || uint16 == 0 || CipherSuite.isSCSV(uint16) || !TlsUtils.isValidCipherSuiteForVersion(uint16, getContext().getServerVersion())) {
            throw new TlsFatalAlert((short) 47);
        }
        this.tlsClient.notifySelectedCipherSuite(uint16);
        short uint8 = TlsUtils.readUint8(byteArrayInputStream);
        if (!Arrays.contains(this.offeredCompressionMethods, uint8)) {
            throw new TlsFatalAlert((short) 47);
        }
        this.tlsClient.notifySelectedCompressionMethod(uint8);
        Hashtable extensions = TlsProtocol.readExtensions(byteArrayInputStream);
        this.serverExtensions = extensions;
        if (extensions != null) {
            Enumeration enumerationKeys = extensions.keys();
            while (enumerationKeys.hasMoreElements()) {
                Integer num = (Integer) enumerationKeys.nextElement();
                if (!num.equals(TlsProtocol.EXT_RenegotiationInfo) && TlsUtils.getExtensionData(this.clientExtensions, num) == null) {
                    throw new TlsFatalAlert(AlertDescription.unsupported_extension);
                }
            }
        }
        byte[] extensionData = TlsUtils.getExtensionData(this.serverExtensions, TlsProtocol.EXT_RenegotiationInfo);
        if (extensionData != null) {
            this.secure_renegotiation = true;
            if (!Arrays.constantTimeAreEqual(extensionData, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                throw new TlsFatalAlert((short) 40);
            }
        }
        this.tlsClient.notifySecureRenegotiation(this.secure_renegotiation);
        Hashtable hashtable = this.clientExtensions;
        Hashtable serverExtensions = this.serverExtensions;
        if (this.resumedSession) {
            if (uint16 != this.sessionParameters.getCipherSuite() || uint8 != this.sessionParameters.getCompressionAlgorithm()) {
                throw new TlsFatalAlert((short) 47);
            }
            serverExtensions = this.sessionParameters.readServerExtensions();
            hashtable = null;
        }
        SecurityParameters securityParameters = this.securityParameters;
        securityParameters.cipherSuite = uint16;
        securityParameters.compressionAlgorithm = uint8;
        if (serverExtensions != null) {
            boolean zHasEncryptThenMACExtension = TlsExtensionsUtils.hasEncryptThenMACExtension(serverExtensions);
            if (zHasEncryptThenMACExtension && !TlsUtils.isBlockCipherSuite(uint16)) {
                throw new TlsFatalAlert((short) 47);
            }
            SecurityParameters securityParameters2 = this.securityParameters;
            securityParameters2.encryptThenMAC = zHasEncryptThenMACExtension;
            securityParameters2.extendedMasterSecret = TlsExtensionsUtils.hasExtendedMasterSecretExtension(serverExtensions);
            this.securityParameters.maxFragmentLength = processMaxFragmentLengthExtension(hashtable, serverExtensions, (short) 47);
            this.securityParameters.truncatedHMac = TlsExtensionsUtils.hasTruncatedHMacExtension(serverExtensions);
            this.allowCertificateStatus = !this.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(serverExtensions, TlsExtensionsUtils.EXT_status_request, (short) 47);
            if (!this.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(serverExtensions, TlsProtocol.EXT_SessionTicket, (short) 47)) {
                z2 = true;
            }
            this.expectSessionTicket = z2;
        }
        if (hashtable != null) {
            this.tlsClient.processServerExtensions(serverExtensions);
        }
        this.securityParameters.prfAlgorithm = TlsProtocol.getPRFAlgorithm(getContext(), this.securityParameters.getCipherSuite());
        this.securityParameters.verifyDataLength = 12;
    }

    public void sendCertificateVerifyMessage(DigitallySigned digitallySigned) throws IOException {
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(this, (short) 15);
        digitallySigned.encode(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }

    public void sendClientHelloMessage() throws IOException {
        byte[] sessionID;
        SessionParameters sessionParameters;
        this.recordStream.setWriteVersion(this.tlsClient.getClientHelloRecordLayerVersion());
        ProtocolVersion clientVersion = this.tlsClient.getClientVersion();
        if (clientVersion.isDTLS()) {
            throw new TlsFatalAlert((short) 80);
        }
        getContextAdmin().setClientVersion(clientVersion);
        byte[] bArr = TlsUtils.EMPTY_BYTES;
        TlsSession tlsSession = this.tlsSession;
        if (tlsSession == null || (sessionID = tlsSession.getSessionID()) == null || sessionID.length > 32) {
            sessionID = bArr;
        }
        boolean zIsFallback = this.tlsClient.isFallback();
        this.offeredCipherSuites = this.tlsClient.getCipherSuites();
        this.offeredCompressionMethods = this.tlsClient.getCompressionMethods();
        if (sessionID.length <= 0 || (sessionParameters = this.sessionParameters) == null || (Arrays.contains(this.offeredCipherSuites, sessionParameters.getCipherSuite()) && Arrays.contains(this.offeredCompressionMethods, this.sessionParameters.getCompressionAlgorithm()))) {
            bArr = sessionID;
        }
        this.clientExtensions = this.tlsClient.getClientExtensions();
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(this, (short) 1);
        TlsUtils.writeVersion(clientVersion, handshakeMessage);
        handshakeMessage.write(this.securityParameters.getClientRandom());
        TlsUtils.writeOpaque8(bArr, handshakeMessage);
        boolean z2 = TlsUtils.getExtensionData(this.clientExtensions, TlsProtocol.EXT_RenegotiationInfo) == null;
        boolean zContains = Arrays.contains(this.offeredCipherSuites, 255);
        if (z2 && !zContains) {
            this.offeredCipherSuites = Arrays.append(this.offeredCipherSuites, 255);
        }
        if (zIsFallback && !Arrays.contains(this.offeredCipherSuites, CipherSuite.TLS_FALLBACK_SCSV)) {
            this.offeredCipherSuites = Arrays.append(this.offeredCipherSuites, CipherSuite.TLS_FALLBACK_SCSV);
        }
        TlsUtils.writeUint16ArrayWithUint16Length(this.offeredCipherSuites, handshakeMessage);
        TlsUtils.writeUint8ArrayWithUint8Length(this.offeredCompressionMethods, handshakeMessage);
        Hashtable hashtable = this.clientExtensions;
        if (hashtable != null) {
            TlsProtocol.writeExtensions(handshakeMessage, hashtable);
        }
        handshakeMessage.writeToRecordStream();
    }

    public void sendClientKeyExchangeMessage() throws IOException {
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(this, (short) 16);
        this.keyExchange.generateClientKeyExchange(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }

    public TlsClientProtocol(SecureRandom secureRandom) {
        super(secureRandom);
        this.tlsClient = null;
        this.tlsClientContext = null;
        this.selectedSessionID = null;
        this.keyExchange = null;
        this.authentication = null;
        this.certificateStatus = null;
        this.certificateRequest = null;
    }
}

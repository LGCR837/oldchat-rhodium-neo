package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.tls.TlsProtocol;
import org.spongycastle.crypto.util.PublicKeyFactory;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class TlsServerProtocol extends TlsProtocol {
    protected CertificateRequest certificateRequest;
    protected short clientCertificateType;
    protected TlsKeyExchange keyExchange;
    protected TlsHandshakeHash prepareFinishHash;
    protected TlsCredentials serverCredentials;
    protected TlsServer tlsServer;
    TlsServerContextImpl tlsServerContext;

    public TlsServerProtocol(InputStream inputStream, OutputStream outputStream, SecureRandom secureRandom) {
        super(inputStream, outputStream, secureRandom);
        this.tlsServer = null;
        this.tlsServerContext = null;
        this.keyExchange = null;
        this.serverCredentials = null;
        this.certificateRequest = null;
        this.clientCertificateType = (short) -1;
        this.prepareFinishHash = null;
    }

    public void accept(TlsServer tlsServer) throws TlsNoCloseNotifyException, TlsFatalAlert, TlsFatalAlertReceived {
        if (tlsServer == null) {
            throw new IllegalArgumentException("'tlsServer' cannot be null");
        }
        if (this.tlsServer != null) {
            throw new IllegalStateException("'accept' can only be called once");
        }
        this.tlsServer = tlsServer;
        SecurityParameters securityParameters = new SecurityParameters();
        this.securityParameters = securityParameters;
        securityParameters.entity = 0;
        this.tlsServerContext = new TlsServerContextImpl(this.secureRandom, this.securityParameters);
        this.securityParameters.serverRandom = TlsProtocol.createRandomBlock(tlsServer.shouldUseGMTUnixTime(), this.tlsServerContext.getNonceRandomGenerator());
        this.tlsServer.init(this.tlsServerContext);
        this.recordStream.init(this.tlsServerContext);
        this.recordStream.setRestrictReadVersion(false);
        blockForHandshake();
    }

    @Override // org.spongycastle.crypto.tls.TlsProtocol
    public void cleanupHandshake() {
        super.cleanupHandshake();
        this.keyExchange = null;
        this.serverCredentials = null;
        this.certificateRequest = null;
        this.prepareFinishHash = null;
    }

    public boolean expectCertificateVerifyMessage() {
        short s2 = this.clientCertificateType;
        return s2 >= 0 && TlsUtils.hasSigningCapability(s2);
    }

    @Override // org.spongycastle.crypto.tls.TlsProtocol
    public TlsContext getContext() {
        return this.tlsServerContext;
    }

    @Override // org.spongycastle.crypto.tls.TlsProtocol
    public AbstractTlsContext getContextAdmin() {
        return this.tlsServerContext;
    }

    @Override // org.spongycastle.crypto.tls.TlsProtocol
    public TlsPeer getPeer() {
        return this.tlsServer;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0020, code lost:
    
        if (r3 == 9) goto L15;
     */
    @Override // org.spongycastle.crypto.tls.TlsProtocol
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void handleAlertWarningMessage(short r3) throws org.spongycastle.crypto.tls.TlsFatalAlert {
        /*
            r2 = this;
            super.handleAlertWarningMessage(r3)
            r0 = 41
            if (r3 == r0) goto L8
            return
        L8:
            org.spongycastle.crypto.tls.TlsContext r3 = r2.getContext()
            boolean r3 = org.spongycastle.crypto.tls.TlsUtils.isSSL(r3)
            r0 = 10
            if (r3 == 0) goto L31
            org.spongycastle.crypto.tls.CertificateRequest r3 = r2.certificateRequest
            if (r3 == 0) goto L31
            short r3 = r2.connection_state
            r1 = 8
            if (r3 == r1) goto L23
            r1 = 9
            if (r3 != r1) goto L31
            goto L29
        L23:
            org.spongycastle.crypto.tls.TlsServer r3 = r2.tlsServer
            r1 = 0
            r3.processClientSupplementalData(r1)
        L29:
            org.spongycastle.crypto.tls.Certificate r3 = org.spongycastle.crypto.tls.Certificate.EMPTY_CHAIN
            r2.notifyClientCertificate(r3)
            r2.connection_state = r0
            return
        L31:
            org.spongycastle.crypto.tls.TlsFatalAlert r3 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.tls.TlsServerProtocol.handleAlertWarningMessage(short):void");
    }

    @Override // org.spongycastle.crypto.tls.TlsProtocol
    public void handleHandshakeMessage(short s2, ByteArrayInputStream byteArrayInputStream) throws IOException {
        CertificateStatus certificateStatus;
        Certificate certificate = null;
        if (s2 == 1) {
            short s3 = this.connection_state;
            if (s3 != 0) {
                if (s3 != 16) {
                    throw new TlsFatalAlert((short) 10);
                }
                refuseRenegotiation();
                return;
            }
            receiveClientHelloMessage(byteArrayInputStream);
            this.connection_state = (short) 1;
            sendServerHelloMessage();
            this.connection_state = (short) 2;
            this.recordStream.notifyHelloComplete();
            Vector serverSupplementalData = this.tlsServer.getServerSupplementalData();
            if (serverSupplementalData != null) {
                sendSupplementalDataMessage(serverSupplementalData);
            }
            this.connection_state = (short) 3;
            TlsKeyExchange keyExchange = this.tlsServer.getKeyExchange();
            this.keyExchange = keyExchange;
            keyExchange.init(getContext());
            TlsCredentials credentials = this.tlsServer.getCredentials();
            this.serverCredentials = credentials;
            if (credentials == null) {
                this.keyExchange.skipServerCredentials();
            } else {
                this.keyExchange.processServerCredentials(credentials);
                certificate = this.serverCredentials.getCertificate();
                sendCertificateMessage(certificate);
            }
            this.connection_state = (short) 4;
            if (certificate == null || certificate.isEmpty()) {
                this.allowCertificateStatus = false;
            }
            if (this.allowCertificateStatus && (certificateStatus = this.tlsServer.getCertificateStatus()) != null) {
                sendCertificateStatusMessage(certificateStatus);
            }
            this.connection_state = (short) 5;
            byte[] bArrGenerateServerKeyExchange = this.keyExchange.generateServerKeyExchange();
            if (bArrGenerateServerKeyExchange != null) {
                sendServerKeyExchangeMessage(bArrGenerateServerKeyExchange);
            }
            this.connection_state = (short) 6;
            if (this.serverCredentials != null) {
                CertificateRequest certificateRequest = this.tlsServer.getCertificateRequest();
                this.certificateRequest = certificateRequest;
                if (certificateRequest != null) {
                    if (TlsUtils.isTLSv12(getContext()) != (this.certificateRequest.getSupportedSignatureAlgorithms() != null)) {
                        throw new TlsFatalAlert((short) 80);
                    }
                    this.keyExchange.validateCertificateRequest(this.certificateRequest);
                    sendCertificateRequestMessage(this.certificateRequest);
                    TlsUtils.trackHashAlgorithms(this.recordStream.getHandshakeHash(), this.certificateRequest.getSupportedSignatureAlgorithms());
                }
            }
            this.connection_state = (short) 7;
            sendServerHelloDoneMessage();
            this.connection_state = (short) 8;
            this.recordStream.getHandshakeHash().sealHashAlgorithms();
            return;
        }
        if (s2 == 11) {
            short s4 = this.connection_state;
            if (s4 == 8) {
                this.tlsServer.processClientSupplementalData(null);
            } else if (s4 != 9) {
                throw new TlsFatalAlert((short) 10);
            }
            if (this.certificateRequest == null) {
                throw new TlsFatalAlert((short) 10);
            }
            receiveCertificateMessage(byteArrayInputStream);
            this.connection_state = (short) 10;
            return;
        }
        if (s2 == 20) {
            short s5 = this.connection_state;
            if (s5 != 11) {
                if (s5 != 12) {
                    throw new TlsFatalAlert((short) 10);
                }
            } else if (expectCertificateVerifyMessage()) {
                throw new TlsFatalAlert((short) 10);
            }
            processFinishedMessage(byteArrayInputStream);
            this.connection_state = (short) 13;
            if (this.expectSessionTicket) {
                sendNewSessionTicketMessage(this.tlsServer.getNewSessionTicket());
                sendChangeCipherSpecMessage();
            }
            this.connection_state = (short) 14;
            sendFinishedMessage();
            this.connection_state = (short) 15;
            completeHandshake();
            return;
        }
        if (s2 == 23) {
            if (this.connection_state != 8) {
                throw new TlsFatalAlert((short) 10);
            }
            this.tlsServer.processClientSupplementalData(TlsProtocol.readSupplementalDataMessage(byteArrayInputStream));
            this.connection_state = (short) 9;
            return;
        }
        if (s2 == 15) {
            if (this.connection_state != 11) {
                throw new TlsFatalAlert((short) 10);
            }
            if (!expectCertificateVerifyMessage()) {
                throw new TlsFatalAlert((short) 10);
            }
            receiveCertificateVerifyMessage(byteArrayInputStream);
            this.connection_state = (short) 12;
            return;
        }
        if (s2 != 16) {
            throw new TlsFatalAlert((short) 10);
        }
        switch (this.connection_state) {
            case 8:
                this.tlsServer.processClientSupplementalData(null);
            case 9:
                if (this.certificateRequest != null) {
                    if (TlsUtils.isTLSv12(getContext())) {
                        throw new TlsFatalAlert((short) 10);
                    }
                    if (!TlsUtils.isSSL(getContext())) {
                        notifyClientCertificate(Certificate.EMPTY_CHAIN);
                    } else if (this.peerCertificate == null) {
                        throw new TlsFatalAlert((short) 10);
                    }
                    break;
                } else {
                    this.keyExchange.skipClientCredentials();
                    break;
                }
            case 10:
                receiveClientKeyExchangeMessage(byteArrayInputStream);
                this.connection_state = (short) 11;
                return;
            default:
                throw new TlsFatalAlert((short) 10);
        }
    }

    public void notifyClientCertificate(Certificate certificate) throws TlsFatalAlert {
        if (this.certificateRequest == null) {
            throw new IllegalStateException();
        }
        if (this.peerCertificate != null) {
            throw new TlsFatalAlert((short) 10);
        }
        this.peerCertificate = certificate;
        if (certificate.isEmpty()) {
            this.keyExchange.skipClientCredentials();
        } else {
            this.clientCertificateType = TlsUtils.getClientCertificateType(certificate, this.serverCredentials.getCertificate());
            this.keyExchange.processClientCertificate(certificate);
        }
        this.tlsServer.notifyClientCertificate(certificate);
    }

    public void receiveCertificateMessage(ByteArrayInputStream byteArrayInputStream) throws TlsFatalAlert {
        Certificate certificate = Certificate.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        notifyClientCertificate(certificate);
    }

    public void receiveCertificateVerifyMessage(ByteArrayInputStream byteArrayInputStream) throws TlsFatalAlert {
        byte[] sessionHash;
        if (this.certificateRequest == null) {
            throw new IllegalStateException();
        }
        DigitallySigned digitallySigned = DigitallySigned.parse(getContext(), byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        try {
            SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
            if (TlsUtils.isTLSv12(getContext())) {
                TlsUtils.verifySupportedSignatureAlgorithm(this.certificateRequest.getSupportedSignatureAlgorithms(), algorithm);
                sessionHash = this.prepareFinishHash.getFinalHash(algorithm.getHash());
            } else {
                sessionHash = this.securityParameters.getSessionHash();
            }
            AsymmetricKeyParameter asymmetricKeyParameterCreateKey = PublicKeyFactory.createKey(this.peerCertificate.getCertificateAt(0).getSubjectPublicKeyInfo());
            TlsSigner tlsSignerCreateTlsSigner = TlsUtils.createTlsSigner(this.clientCertificateType);
            tlsSignerCreateTlsSigner.init(getContext());
            if (tlsSignerCreateTlsSigner.verifyRawSignature(algorithm, digitallySigned.getSignature(), asymmetricKeyParameterCreateKey, sessionHash)) {
            } else {
                throw new TlsFatalAlert((short) 51);
            }
        } catch (TlsFatalAlert e2) {
            throw e2;
        } catch (Exception e3) {
            throw new TlsFatalAlert((short) 51, e3);
        }
    }

    public void receiveClientHelloMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        ProtocolVersion version = TlsUtils.readVersion(byteArrayInputStream);
        this.recordStream.setWriteVersion(version);
        if (version.isDTLS()) {
            throw new TlsFatalAlert((short) 47);
        }
        byte[] fully = TlsUtils.readFully(32, byteArrayInputStream);
        if (TlsUtils.readOpaque8(byteArrayInputStream).length > 32) {
            throw new TlsFatalAlert((short) 47);
        }
        int uint16 = TlsUtils.readUint16(byteArrayInputStream);
        if (uint16 < 2 || (uint16 & 1) != 0) {
            throw new TlsFatalAlert((short) 50);
        }
        this.offeredCipherSuites = TlsUtils.readUint16Array(uint16 / 2, byteArrayInputStream);
        short uint8 = TlsUtils.readUint8(byteArrayInputStream);
        if (uint8 < 1) {
            throw new TlsFatalAlert((short) 47);
        }
        this.offeredCompressionMethods = TlsUtils.readUint8Array(uint8, byteArrayInputStream);
        Hashtable extensions = TlsProtocol.readExtensions(byteArrayInputStream);
        this.clientExtensions = extensions;
        this.securityParameters.extendedMasterSecret = TlsExtensionsUtils.hasExtendedMasterSecretExtension(extensions);
        getContextAdmin().setClientVersion(version);
        this.tlsServer.notifyClientVersion(version);
        this.tlsServer.notifyFallback(Arrays.contains(this.offeredCipherSuites, CipherSuite.TLS_FALLBACK_SCSV));
        this.securityParameters.clientRandom = fully;
        this.tlsServer.notifyOfferedCipherSuites(this.offeredCipherSuites);
        this.tlsServer.notifyOfferedCompressionMethods(this.offeredCompressionMethods);
        if (Arrays.contains(this.offeredCipherSuites, 255)) {
            this.secure_renegotiation = true;
        }
        byte[] extensionData = TlsUtils.getExtensionData(this.clientExtensions, TlsProtocol.EXT_RenegotiationInfo);
        if (extensionData != null) {
            this.secure_renegotiation = true;
            if (!Arrays.constantTimeAreEqual(extensionData, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                throw new TlsFatalAlert((short) 40);
            }
        }
        this.tlsServer.notifySecureRenegotiation(this.secure_renegotiation);
        Hashtable hashtable = this.clientExtensions;
        if (hashtable != null) {
            TlsExtensionsUtils.getPaddingExtension(hashtable);
            this.tlsServer.processClientExtensions(this.clientExtensions);
        }
    }

    public void receiveClientKeyExchangeMessage(ByteArrayInputStream byteArrayInputStream) throws TlsFatalAlert {
        this.keyExchange.processClientKeyExchange(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        if (TlsUtils.isSSL(getContext())) {
            TlsProtocol.establishMasterSecret(getContext(), this.keyExchange);
        }
        this.prepareFinishHash = this.recordStream.prepareToFinish();
        this.securityParameters.sessionHash = TlsProtocol.getCurrentPRFHash(getContext(), this.prepareFinishHash, null);
        if (!TlsUtils.isSSL(getContext())) {
            TlsProtocol.establishMasterSecret(getContext(), this.keyExchange);
        }
        this.recordStream.setPendingConnectionState(getPeer().getCompression(), getPeer().getCipher());
        if (this.expectSessionTicket) {
            return;
        }
        sendChangeCipherSpecMessage();
    }

    public void sendCertificateRequestMessage(CertificateRequest certificateRequest) throws IOException {
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(this, (short) 13);
        certificateRequest.encode(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }

    public void sendCertificateStatusMessage(CertificateStatus certificateStatus) throws IOException {
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(this, (short) 22);
        certificateStatus.encode(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }

    public void sendNewSessionTicketMessage(NewSessionTicket newSessionTicket) throws IOException {
        if (newSessionTicket == null) {
            throw new TlsFatalAlert((short) 80);
        }
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(this, (short) 4);
        newSessionTicket.encode(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }

    public void sendServerHelloDoneMessage() throws IOException {
        byte[] bArr = new byte[4];
        TlsUtils.writeUint8((short) 14, bArr, 0);
        TlsUtils.writeUint24(0, bArr, 1);
        writeHandshakeMessage(bArr, 0, 4);
    }

    public void sendServerHelloMessage() throws IOException {
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(this, (short) 2);
        ProtocolVersion serverVersion = this.tlsServer.getServerVersion();
        if (!serverVersion.isEqualOrEarlierVersionOf(getContext().getClientVersion())) {
            throw new TlsFatalAlert((short) 80);
        }
        this.recordStream.setReadVersion(serverVersion);
        this.recordStream.setWriteVersion(serverVersion);
        this.recordStream.setRestrictReadVersion(true);
        getContextAdmin().setServerVersion(serverVersion);
        TlsUtils.writeVersion(serverVersion, handshakeMessage);
        handshakeMessage.write(this.securityParameters.serverRandom);
        byte[] bArr = TlsUtils.EMPTY_BYTES;
        TlsUtils.writeOpaque8(bArr, handshakeMessage);
        int selectedCipherSuite = this.tlsServer.getSelectedCipherSuite();
        if (!Arrays.contains(this.offeredCipherSuites, selectedCipherSuite) || selectedCipherSuite == 0 || CipherSuite.isSCSV(selectedCipherSuite) || !TlsUtils.isValidCipherSuiteForVersion(selectedCipherSuite, getContext().getServerVersion())) {
            throw new TlsFatalAlert((short) 80);
        }
        this.securityParameters.cipherSuite = selectedCipherSuite;
        short selectedCompressionMethod = this.tlsServer.getSelectedCompressionMethod();
        if (!Arrays.contains(this.offeredCompressionMethods, selectedCompressionMethod)) {
            throw new TlsFatalAlert((short) 80);
        }
        this.securityParameters.compressionAlgorithm = selectedCompressionMethod;
        TlsUtils.writeUint16(selectedCipherSuite, handshakeMessage);
        TlsUtils.writeUint8(selectedCompressionMethod, (OutputStream) handshakeMessage);
        Hashtable serverExtensions = this.tlsServer.getServerExtensions();
        this.serverExtensions = serverExtensions;
        if (this.secure_renegotiation) {
            Integer num = TlsProtocol.EXT_RenegotiationInfo;
            if (TlsUtils.getExtensionData(serverExtensions, num) == null) {
                Hashtable hashtableEnsureExtensionsInitialised = TlsExtensionsUtils.ensureExtensionsInitialised(this.serverExtensions);
                this.serverExtensions = hashtableEnsureExtensionsInitialised;
                hashtableEnsureExtensionsInitialised.put(num, TlsProtocol.createRenegotiationInfo(bArr));
            }
        }
        if (this.securityParameters.extendedMasterSecret) {
            Hashtable hashtableEnsureExtensionsInitialised2 = TlsExtensionsUtils.ensureExtensionsInitialised(this.serverExtensions);
            this.serverExtensions = hashtableEnsureExtensionsInitialised2;
            TlsExtensionsUtils.addExtendedMasterSecretExtension(hashtableEnsureExtensionsInitialised2);
        }
        Hashtable hashtable = this.serverExtensions;
        if (hashtable != null) {
            this.securityParameters.encryptThenMAC = TlsExtensionsUtils.hasEncryptThenMACExtension(hashtable);
            this.securityParameters.maxFragmentLength = processMaxFragmentLengthExtension(this.clientExtensions, this.serverExtensions, (short) 80);
            this.securityParameters.truncatedHMac = TlsExtensionsUtils.hasTruncatedHMacExtension(this.serverExtensions);
            this.allowCertificateStatus = !this.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(this.serverExtensions, TlsExtensionsUtils.EXT_status_request, (short) 80);
            this.expectSessionTicket = !this.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(this.serverExtensions, TlsProtocol.EXT_SessionTicket, (short) 80);
            TlsProtocol.writeExtensions(handshakeMessage, this.serverExtensions);
        }
        this.securityParameters.prfAlgorithm = TlsProtocol.getPRFAlgorithm(getContext(), this.securityParameters.getCipherSuite());
        this.securityParameters.verifyDataLength = 12;
        applyMaxFragmentLengthExtension();
        handshakeMessage.writeToRecordStream();
    }

    public void sendServerKeyExchangeMessage(byte[] bArr) throws IOException {
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage((short) 12, bArr.length);
        handshakeMessage.write(bArr);
        handshakeMessage.writeToRecordStream();
    }

    public TlsServerProtocol(SecureRandom secureRandom) {
        super(secureRandom);
        this.tlsServer = null;
        this.tlsServerContext = null;
        this.keyExchange = null;
        this.serverCredentials = null;
        this.certificateRequest = null;
        this.clientCertificateType = (short) -1;
        this.prepareFinishHash = null;
    }
}

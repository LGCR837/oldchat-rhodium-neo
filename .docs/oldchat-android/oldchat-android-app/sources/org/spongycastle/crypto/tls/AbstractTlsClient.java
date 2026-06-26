package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractTlsClient extends AbstractTlsPeer implements TlsClient {
    protected TlsCipherFactory cipherFactory;
    protected short[] clientECPointFormats;
    protected TlsClientContext context;
    protected int[] namedCurves;
    protected int selectedCipherSuite;
    protected short selectedCompressionMethod;
    protected short[] serverECPointFormats;
    protected Vector supportedSignatureAlgorithms;

    public AbstractTlsClient() {
        this(new DefaultTlsCipherFactory());
    }

    public boolean allowUnexpectedServerExtension(Integer num, byte[] bArr) throws IOException {
        if (num.intValue() != 10) {
            return false;
        }
        TlsECCUtils.readSupportedEllipticCurvesExtension(bArr);
        return true;
    }

    public void checkForUnexpectedServerExtension(Hashtable hashtable, Integer num) throws TlsFatalAlert {
        byte[] extensionData = TlsUtils.getExtensionData(hashtable, num);
        if (extensionData != null && !allowUnexpectedServerExtension(num, extensionData)) {
            throw new TlsFatalAlert((short) 47);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsPeer
    public TlsCipher getCipher() throws TlsFatalAlert {
        return this.cipherFactory.createCipher(this.context, TlsUtils.getEncryptionAlgorithm(this.selectedCipherSuite), TlsUtils.getMACAlgorithm(this.selectedCipherSuite));
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public Hashtable getClientExtensions() {
        Hashtable hashtableEnsureExtensionsInitialised = null;
        if (TlsUtils.isSignatureAlgorithmsExtensionAllowed(this.context.getClientVersion())) {
            this.supportedSignatureAlgorithms = TlsUtils.getDefaultSupportedSignatureAlgorithms();
            hashtableEnsureExtensionsInitialised = TlsExtensionsUtils.ensureExtensionsInitialised(null);
            TlsUtils.addSignatureAlgorithmsExtension(hashtableEnsureExtensionsInitialised, this.supportedSignatureAlgorithms);
        }
        if (!TlsECCUtils.containsECCCipherSuites(getCipherSuites())) {
            return hashtableEnsureExtensionsInitialised;
        }
        this.namedCurves = new int[]{23, 24};
        this.clientECPointFormats = new short[]{0, 1, 2};
        Hashtable hashtableEnsureExtensionsInitialised2 = TlsExtensionsUtils.ensureExtensionsInitialised(hashtableEnsureExtensionsInitialised);
        TlsECCUtils.addSupportedEllipticCurvesExtension(hashtableEnsureExtensionsInitialised2, this.namedCurves);
        TlsECCUtils.addSupportedPointFormatsExtension(hashtableEnsureExtensionsInitialised2, this.clientECPointFormats);
        return hashtableEnsureExtensionsInitialised2;
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public ProtocolVersion getClientHelloRecordLayerVersion() {
        return getClientVersion();
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public Vector getClientSupplementalData() {
        return null;
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public ProtocolVersion getClientVersion() {
        return ProtocolVersion.TLSv12;
    }

    @Override // org.spongycastle.crypto.tls.TlsPeer
    public TlsCompression getCompression() throws TlsFatalAlert {
        if (this.selectedCompressionMethod == 0) {
            return new TlsNullCompression();
        }
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public short[] getCompressionMethods() {
        return new short[]{0};
    }

    public ProtocolVersion getMinimumVersion() {
        return ProtocolVersion.TLSv10;
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public TlsSession getSessionToResume() {
        return null;
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public void init(TlsClientContext tlsClientContext) {
        this.context = tlsClientContext;
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public boolean isFallback() {
        return false;
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public void notifyNewSessionTicket(NewSessionTicket newSessionTicket) {
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public void notifySelectedCipherSuite(int i2) {
        this.selectedCipherSuite = i2;
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public void notifySelectedCompressionMethod(short s2) {
        this.selectedCompressionMethod = s2;
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public void notifyServerVersion(ProtocolVersion protocolVersion) throws TlsFatalAlert {
        if (!getMinimumVersion().isEqualOrEarlierVersionOf(protocolVersion)) {
            throw new TlsFatalAlert((short) 70);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public void notifySessionID(byte[] bArr) {
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public void processServerExtensions(Hashtable hashtable) throws TlsFatalAlert {
        if (hashtable != null) {
            checkForUnexpectedServerExtension(hashtable, TlsUtils.EXT_signature_algorithms);
            checkForUnexpectedServerExtension(hashtable, TlsECCUtils.EXT_elliptic_curves);
            if (TlsECCUtils.isECCCipherSuite(this.selectedCipherSuite)) {
                this.serverECPointFormats = TlsECCUtils.getSupportedPointFormatsExtension(hashtable);
            } else {
                checkForUnexpectedServerExtension(hashtable, TlsECCUtils.EXT_ec_point_formats);
            }
            checkForUnexpectedServerExtension(hashtable, TlsExtensionsUtils.EXT_padding);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public void processServerSupplementalData(Vector vector) throws TlsFatalAlert {
        if (vector != null) {
            throw new TlsFatalAlert((short) 10);
        }
    }

    public AbstractTlsClient(TlsCipherFactory tlsCipherFactory) {
        this.cipherFactory = tlsCipherFactory;
    }
}

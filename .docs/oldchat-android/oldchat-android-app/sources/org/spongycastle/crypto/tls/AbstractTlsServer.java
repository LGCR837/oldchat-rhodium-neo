package org.spongycastle.crypto.tls;

import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractTlsServer extends AbstractTlsPeer implements TlsServer {
    protected TlsCipherFactory cipherFactory;
    protected short[] clientECPointFormats;
    protected Hashtable clientExtensions;
    protected ProtocolVersion clientVersion;
    protected TlsServerContext context;
    protected boolean eccCipherSuitesOffered;
    protected boolean encryptThenMACOffered;
    protected short maxFragmentLengthOffered;
    protected int[] namedCurves;
    protected int[] offeredCipherSuites;
    protected short[] offeredCompressionMethods;
    protected int selectedCipherSuite;
    protected short selectedCompressionMethod;
    protected short[] serverECPointFormats;
    protected Hashtable serverExtensions;
    protected ProtocolVersion serverVersion;
    protected Vector supportedSignatureAlgorithms;
    protected boolean truncatedHMacOffered;

    public AbstractTlsServer() {
        this(new DefaultTlsCipherFactory());
    }

    public boolean allowEncryptThenMAC() {
        return true;
    }

    public boolean allowTruncatedHMac() {
        return false;
    }

    public Hashtable checkServerExtensions() {
        Hashtable hashtableEnsureExtensionsInitialised = TlsExtensionsUtils.ensureExtensionsInitialised(this.serverExtensions);
        this.serverExtensions = hashtableEnsureExtensionsInitialised;
        return hashtableEnsureExtensionsInitialised;
    }

    @Override // org.spongycastle.crypto.tls.TlsServer
    public CertificateRequest getCertificateRequest() {
        return null;
    }

    @Override // org.spongycastle.crypto.tls.TlsServer
    public CertificateStatus getCertificateStatus() {
        return null;
    }

    @Override // org.spongycastle.crypto.tls.TlsPeer
    public TlsCipher getCipher() throws TlsFatalAlert {
        return this.cipherFactory.createCipher(this.context, TlsUtils.getEncryptionAlgorithm(this.selectedCipherSuite), TlsUtils.getMACAlgorithm(this.selectedCipherSuite));
    }

    public abstract int[] getCipherSuites();

    @Override // org.spongycastle.crypto.tls.TlsPeer
    public TlsCompression getCompression() throws TlsFatalAlert {
        if (this.selectedCompressionMethod == 0) {
            return new TlsNullCompression();
        }
        throw new TlsFatalAlert((short) 80);
    }

    public short[] getCompressionMethods() {
        return new short[]{0};
    }

    public ProtocolVersion getMaximumVersion() {
        return ProtocolVersion.TLSv11;
    }

    public ProtocolVersion getMinimumVersion() {
        return ProtocolVersion.TLSv10;
    }

    @Override // org.spongycastle.crypto.tls.TlsServer
    public NewSessionTicket getNewSessionTicket() {
        return new NewSessionTicket(0L, TlsUtils.EMPTY_BYTES);
    }

    @Override // org.spongycastle.crypto.tls.TlsServer
    public int getSelectedCipherSuite() throws TlsFatalAlert {
        Vector usableSignatureAlgorithms = TlsUtils.getUsableSignatureAlgorithms(this.supportedSignatureAlgorithms);
        boolean zSupportsClientECCCapabilities = supportsClientECCCapabilities(this.namedCurves, this.clientECPointFormats);
        for (int i2 : getCipherSuites()) {
            if (Arrays.contains(this.offeredCipherSuites, i2) && ((zSupportsClientECCCapabilities || !TlsECCUtils.isECCCipherSuite(i2)) && TlsUtils.isValidCipherSuiteForVersion(i2, this.serverVersion) && TlsUtils.isValidCipherSuiteForSignatureAlgorithms(i2, usableSignatureAlgorithms))) {
                this.selectedCipherSuite = i2;
                return i2;
            }
        }
        throw new TlsFatalAlert((short) 40);
    }

    @Override // org.spongycastle.crypto.tls.TlsServer
    public short getSelectedCompressionMethod() throws TlsFatalAlert {
        short[] compressionMethods = getCompressionMethods();
        for (int i2 = 0; i2 < compressionMethods.length; i2++) {
            if (Arrays.contains(this.offeredCompressionMethods, compressionMethods[i2])) {
                short s2 = compressionMethods[i2];
                this.selectedCompressionMethod = s2;
                return s2;
            }
        }
        throw new TlsFatalAlert((short) 40);
    }

    @Override // org.spongycastle.crypto.tls.TlsServer
    public Hashtable getServerExtensions() {
        if (this.encryptThenMACOffered && allowEncryptThenMAC() && TlsUtils.isBlockCipherSuite(this.selectedCipherSuite)) {
            TlsExtensionsUtils.addEncryptThenMACExtension(checkServerExtensions());
        }
        short s2 = this.maxFragmentLengthOffered;
        if (s2 >= 0 && MaxFragmentLength.isValid(s2)) {
            TlsExtensionsUtils.addMaxFragmentLengthExtension(checkServerExtensions(), this.maxFragmentLengthOffered);
        }
        if (this.truncatedHMacOffered && allowTruncatedHMac()) {
            TlsExtensionsUtils.addTruncatedHMacExtension(checkServerExtensions());
        }
        if (this.clientECPointFormats != null && TlsECCUtils.isECCCipherSuite(this.selectedCipherSuite)) {
            this.serverECPointFormats = new short[]{0, 1, 2};
            TlsECCUtils.addSupportedPointFormatsExtension(checkServerExtensions(), this.serverECPointFormats);
        }
        return this.serverExtensions;
    }

    @Override // org.spongycastle.crypto.tls.TlsServer
    public Vector getServerSupplementalData() {
        return null;
    }

    @Override // org.spongycastle.crypto.tls.TlsServer
    public ProtocolVersion getServerVersion() throws TlsFatalAlert {
        if (getMinimumVersion().isEqualOrEarlierVersionOf(this.clientVersion)) {
            ProtocolVersion maximumVersion = getMaximumVersion();
            if (this.clientVersion.isEqualOrEarlierVersionOf(maximumVersion)) {
                ProtocolVersion protocolVersion = this.clientVersion;
                this.serverVersion = protocolVersion;
                return protocolVersion;
            }
            if (this.clientVersion.isLaterVersionOf(maximumVersion)) {
                this.serverVersion = maximumVersion;
                return maximumVersion;
            }
        }
        throw new TlsFatalAlert((short) 70);
    }

    @Override // org.spongycastle.crypto.tls.TlsServer
    public void init(TlsServerContext tlsServerContext) {
        this.context = tlsServerContext;
    }

    @Override // org.spongycastle.crypto.tls.TlsServer
    public void notifyClientCertificate(Certificate certificate) throws TlsFatalAlert {
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.spongycastle.crypto.tls.TlsServer
    public void notifyClientVersion(ProtocolVersion protocolVersion) {
        this.clientVersion = protocolVersion;
    }

    @Override // org.spongycastle.crypto.tls.TlsServer
    public void notifyFallback(boolean z2) throws TlsFatalAlert {
        if (z2 && getMaximumVersion().isLaterVersionOf(this.clientVersion)) {
            throw new TlsFatalAlert((short) 86);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsServer
    public void notifyOfferedCipherSuites(int[] iArr) {
        this.offeredCipherSuites = iArr;
        this.eccCipherSuitesOffered = TlsECCUtils.containsECCCipherSuites(iArr);
    }

    @Override // org.spongycastle.crypto.tls.TlsServer
    public void notifyOfferedCompressionMethods(short[] sArr) {
        this.offeredCompressionMethods = sArr;
    }

    @Override // org.spongycastle.crypto.tls.TlsServer
    public void processClientExtensions(Hashtable hashtable) throws TlsFatalAlert {
        this.clientExtensions = hashtable;
        if (hashtable != null) {
            this.encryptThenMACOffered = TlsExtensionsUtils.hasEncryptThenMACExtension(hashtable);
            short maxFragmentLengthExtension = TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable);
            this.maxFragmentLengthOffered = maxFragmentLengthExtension;
            if (maxFragmentLengthExtension >= 0 && !MaxFragmentLength.isValid(maxFragmentLengthExtension)) {
                throw new TlsFatalAlert((short) 47);
            }
            this.truncatedHMacOffered = TlsExtensionsUtils.hasTruncatedHMacExtension(hashtable);
            Vector signatureAlgorithmsExtension = TlsUtils.getSignatureAlgorithmsExtension(hashtable);
            this.supportedSignatureAlgorithms = signatureAlgorithmsExtension;
            if (signatureAlgorithmsExtension != null && !TlsUtils.isSignatureAlgorithmsExtensionAllowed(this.clientVersion)) {
                throw new TlsFatalAlert((short) 47);
            }
            this.namedCurves = TlsECCUtils.getSupportedEllipticCurvesExtension(hashtable);
            this.clientECPointFormats = TlsECCUtils.getSupportedPointFormatsExtension(hashtable);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsServer
    public void processClientSupplementalData(Vector vector) throws TlsFatalAlert {
        if (vector != null) {
            throw new TlsFatalAlert((short) 10);
        }
    }

    public boolean supportsClientECCCapabilities(int[] iArr, short[] sArr) {
        if (iArr == null) {
            return TlsECCUtils.hasAnySupportedNamedCurves();
        }
        for (int i2 : iArr) {
            if (NamedCurve.isValid(i2) && (!NamedCurve.refersToASpecificNamedCurve(i2) || TlsECCUtils.isSupportedNamedCurve(i2))) {
                return true;
            }
        }
        return false;
    }

    public AbstractTlsServer(TlsCipherFactory tlsCipherFactory) {
        this.cipherFactory = tlsCipherFactory;
    }
}

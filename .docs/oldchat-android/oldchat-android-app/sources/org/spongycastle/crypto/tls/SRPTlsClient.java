package org.spongycastle.crypto.tls;

import java.util.Hashtable;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class SRPTlsClient extends AbstractTlsClient {
    protected TlsSRPGroupVerifier groupVerifier;
    protected byte[] identity;
    protected byte[] password;

    public SRPTlsClient(byte[] bArr, byte[] bArr2) {
        this(new DefaultTlsCipherFactory(), new DefaultTlsSRPGroupVerifier(), bArr, bArr2);
    }

    public TlsKeyExchange createSRPKeyExchange(int i2) {
        return new TlsSRPKeyExchange(i2, this.supportedSignatureAlgorithms, this.groupVerifier, this.identity, this.password);
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public TlsAuthentication getAuthentication() throws TlsFatalAlert {
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public int[] getCipherSuites() {
        return new int[]{CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_128_CBC_SHA};
    }

    @Override // org.spongycastle.crypto.tls.AbstractTlsClient, org.spongycastle.crypto.tls.TlsClient
    public Hashtable getClientExtensions() {
        Hashtable hashtableEnsureExtensionsInitialised = TlsExtensionsUtils.ensureExtensionsInitialised(super.getClientExtensions());
        TlsSRPUtils.addSRPExtension(hashtableEnsureExtensionsInitialised, this.identity);
        return hashtableEnsureExtensionsInitialised;
    }

    @Override // org.spongycastle.crypto.tls.TlsClient
    public TlsKeyExchange getKeyExchange() throws TlsFatalAlert {
        int keyExchangeAlgorithm = TlsUtils.getKeyExchangeAlgorithm(this.selectedCipherSuite);
        switch (keyExchangeAlgorithm) {
            case 21:
            case 22:
            case 23:
                return createSRPKeyExchange(keyExchangeAlgorithm);
            default:
                throw new TlsFatalAlert((short) 80);
        }
    }

    @Override // org.spongycastle.crypto.tls.AbstractTlsClient, org.spongycastle.crypto.tls.TlsClient
    public void processServerExtensions(Hashtable hashtable) throws TlsFatalAlert {
        if (!TlsUtils.hasExpectedEmptyExtensionData(hashtable, TlsSRPUtils.EXT_SRP, (short) 47) && requireSRPServerExtension()) {
            throw new TlsFatalAlert((short) 47);
        }
        super.processServerExtensions(hashtable);
    }

    public boolean requireSRPServerExtension() {
        return false;
    }

    public SRPTlsClient(TlsCipherFactory tlsCipherFactory, byte[] bArr, byte[] bArr2) {
        this(tlsCipherFactory, new DefaultTlsSRPGroupVerifier(), bArr, bArr2);
    }

    public SRPTlsClient(TlsCipherFactory tlsCipherFactory, TlsSRPGroupVerifier tlsSRPGroupVerifier, byte[] bArr, byte[] bArr2) {
        super(tlsCipherFactory);
        this.groupVerifier = tlsSRPGroupVerifier;
        this.identity = Arrays.clone(bArr);
        this.password = Arrays.clone(bArr2);
    }
}

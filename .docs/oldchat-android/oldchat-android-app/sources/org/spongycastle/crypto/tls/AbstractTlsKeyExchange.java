package org.spongycastle.crypto.tls;

import java.io.InputStream;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractTlsKeyExchange implements TlsKeyExchange {
    protected TlsContext context;
    protected int keyExchange;
    protected Vector supportedSignatureAlgorithms;

    public AbstractTlsKeyExchange(int i2, Vector vector) {
        this.keyExchange = i2;
        this.supportedSignatureAlgorithms = vector;
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public byte[] generateServerKeyExchange() throws TlsFatalAlert {
        if (requiresServerKeyExchange()) {
            throw new TlsFatalAlert((short) 80);
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void init(TlsContext tlsContext) {
        this.context = tlsContext;
        ProtocolVersion clientVersion = tlsContext.getClientVersion();
        if (!TlsUtils.isSignatureAlgorithmsExtensionAllowed(clientVersion)) {
            if (this.supportedSignatureAlgorithms == null) {
                return;
            }
            throw new IllegalStateException("supported_signature_algorithms not allowed for " + clientVersion);
        }
        if (this.supportedSignatureAlgorithms == null) {
            int i2 = this.keyExchange;
            if (i2 != 1) {
                if (i2 != 3) {
                    if (i2 != 5) {
                        if (i2 != 7) {
                            if (i2 != 9) {
                                switch (i2) {
                                    case 13:
                                    case 14:
                                        return;
                                    case 15:
                                    case 18:
                                    case 19:
                                        break;
                                    case 16:
                                    case 17:
                                        this.supportedSignatureAlgorithms = TlsUtils.getDefaultECDSASignatureAlgorithms();
                                        return;
                                    default:
                                        switch (i2) {
                                            case 21:
                                            case 24:
                                                return;
                                            case 22:
                                                break;
                                            case 23:
                                                break;
                                            default:
                                                throw new IllegalStateException("unsupported key exchange algorithm");
                                        }
                                        break;
                                }
                            }
                        }
                    }
                }
                this.supportedSignatureAlgorithms = TlsUtils.getDefaultDSSSignatureAlgorithms();
                return;
            }
            this.supportedSignatureAlgorithms = TlsUtils.getDefaultRSASignatureAlgorithms();
        }
    }

    public DigitallySigned parseSignature(InputStream inputStream) throws TlsFatalAlert {
        DigitallySigned digitallySigned = DigitallySigned.parse(this.context, inputStream);
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        if (algorithm != null) {
            TlsUtils.verifySupportedSignatureAlgorithm(this.supportedSignatureAlgorithms, algorithm);
        }
        return digitallySigned;
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void processClientCertificate(Certificate certificate) {
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void processClientKeyExchange(InputStream inputStream) throws TlsFatalAlert {
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void processServerCertificate(Certificate certificate) {
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void processServerCredentials(TlsCredentials tlsCredentials) {
        processServerCertificate(tlsCredentials.getCertificate());
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void processServerKeyExchange(InputStream inputStream) throws TlsFatalAlert {
        if (!requiresServerKeyExchange()) {
            throw new TlsFatalAlert((short) 10);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public boolean requiresServerKeyExchange() {
        return false;
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void skipClientCredentials() {
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void skipServerKeyExchange() throws TlsFatalAlert {
        if (requiresServerKeyExchange()) {
            throw new TlsFatalAlert((short) 10);
        }
    }
}

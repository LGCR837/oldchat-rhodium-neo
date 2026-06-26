package org.spongycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPrivateKeyParameters;
import org.spongycastle.crypto.params.DHPublicKeyParameters;
import org.spongycastle.crypto.util.PublicKeyFactory;

/* JADX INFO: loaded from: classes.dex */
public class TlsDHKeyExchange extends AbstractTlsKeyExchange {
    protected TlsAgreementCredentials agreementCredentials;
    protected DHPrivateKeyParameters dhAgreePrivateKey;
    protected DHPublicKeyParameters dhAgreePublicKey;
    protected DHParameters dhParameters;
    protected AsymmetricKeyParameter serverPublicKey;
    protected TlsSigner tlsSigner;

    public TlsDHKeyExchange(int i2, Vector vector, DHParameters dHParameters) {
        super(i2, vector);
        if (i2 == 3) {
            this.tlsSigner = new TlsDSSSigner();
        } else if (i2 == 5) {
            this.tlsSigner = new TlsRSASigner();
        } else {
            if (i2 != 7 && i2 != 9 && i2 != 11) {
                throw new IllegalArgumentException("unsupported key exchange algorithm");
            }
            this.tlsSigner = null;
        }
        this.dhParameters = dHParameters;
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void generateClientKeyExchange(OutputStream outputStream) {
        if (this.agreementCredentials == null) {
            this.dhAgreePrivateKey = TlsDHUtils.generateEphemeralClientKeyExchange(this.context.getSecureRandom(), this.dhParameters, outputStream);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public byte[] generatePremasterSecret() throws TlsFatalAlert {
        TlsAgreementCredentials tlsAgreementCredentials = this.agreementCredentials;
        if (tlsAgreementCredentials != null) {
            return tlsAgreementCredentials.generateAgreement(this.dhAgreePublicKey);
        }
        DHPrivateKeyParameters dHPrivateKeyParameters = this.dhAgreePrivateKey;
        if (dHPrivateKeyParameters != null) {
            return TlsDHUtils.calculateDHBasicAgreement(this.dhAgreePublicKey, dHPrivateKeyParameters);
        }
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.spongycastle.crypto.tls.AbstractTlsKeyExchange, org.spongycastle.crypto.tls.TlsKeyExchange
    public byte[] generateServerKeyExchange() {
        if (!requiresServerKeyExchange()) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.dhAgreePrivateKey = TlsDHUtils.generateEphemeralServerKeyExchange(this.context.getSecureRandom(), this.dhParameters, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public int getMinimumPrimeBits() {
        return 1024;
    }

    @Override // org.spongycastle.crypto.tls.AbstractTlsKeyExchange, org.spongycastle.crypto.tls.TlsKeyExchange
    public void init(TlsContext tlsContext) {
        super.init(tlsContext);
        TlsSigner tlsSigner = this.tlsSigner;
        if (tlsSigner != null) {
            tlsSigner.init(tlsContext);
        }
    }

    @Override // org.spongycastle.crypto.tls.AbstractTlsKeyExchange, org.spongycastle.crypto.tls.TlsKeyExchange
    public void processClientCertificate(Certificate certificate) throws TlsFatalAlert {
        if (this.keyExchange == 11) {
            throw new TlsFatalAlert((short) 10);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void processClientCredentials(TlsCredentials tlsCredentials) throws TlsFatalAlert {
        if (this.keyExchange == 11) {
            throw new TlsFatalAlert((short) 80);
        }
        if (tlsCredentials instanceof TlsAgreementCredentials) {
            this.agreementCredentials = (TlsAgreementCredentials) tlsCredentials;
        } else if (!(tlsCredentials instanceof TlsSignerCredentials)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    @Override // org.spongycastle.crypto.tls.AbstractTlsKeyExchange, org.spongycastle.crypto.tls.TlsKeyExchange
    public void processClientKeyExchange(InputStream inputStream) {
        if (this.dhAgreePublicKey != null) {
            return;
        }
        this.dhAgreePublicKey = TlsDHUtils.validateDHPublicKey(new DHPublicKeyParameters(TlsDHUtils.readDHParameter(inputStream), this.dhParameters));
    }

    @Override // org.spongycastle.crypto.tls.AbstractTlsKeyExchange, org.spongycastle.crypto.tls.TlsKeyExchange
    public void processServerCertificate(Certificate certificate) throws TlsFatalAlert {
        if (this.keyExchange == 11) {
            throw new TlsFatalAlert((short) 10);
        }
        if (certificate.isEmpty()) {
            throw new TlsFatalAlert((short) 42);
        }
        org.spongycastle.asn1.x509.Certificate certificateAt = certificate.getCertificateAt(0);
        try {
            AsymmetricKeyParameter asymmetricKeyParameterCreateKey = PublicKeyFactory.createKey(certificateAt.getSubjectPublicKeyInfo());
            this.serverPublicKey = asymmetricKeyParameterCreateKey;
            TlsSigner tlsSigner = this.tlsSigner;
            if (tlsSigner == null) {
                try {
                    DHPublicKeyParameters dHPublicKeyParametersValidateDHPublicKey = TlsDHUtils.validateDHPublicKey((DHPublicKeyParameters) asymmetricKeyParameterCreateKey);
                    this.dhAgreePublicKey = dHPublicKeyParametersValidateDHPublicKey;
                    this.dhParameters = validateDHParameters(dHPublicKeyParametersValidateDHPublicKey.getParameters());
                    TlsUtils.validateKeyUsage(certificateAt, 8);
                } catch (ClassCastException e2) {
                    throw new TlsFatalAlert((short) 46, e2);
                }
            } else {
                if (!tlsSigner.isValidPublicKey(asymmetricKeyParameterCreateKey)) {
                    throw new TlsFatalAlert((short) 46);
                }
                TlsUtils.validateKeyUsage(certificateAt, 128);
            }
            super.processServerCertificate(certificate);
        } catch (RuntimeException e3) {
            throw new TlsFatalAlert((short) 43, e3);
        }
    }

    @Override // org.spongycastle.crypto.tls.AbstractTlsKeyExchange, org.spongycastle.crypto.tls.TlsKeyExchange
    public void processServerKeyExchange(InputStream inputStream) throws TlsFatalAlert {
        if (!requiresServerKeyExchange()) {
            throw new TlsFatalAlert((short) 10);
        }
        DHPublicKeyParameters dHPublicKeyParametersValidateDHPublicKey = TlsDHUtils.validateDHPublicKey(ServerDHParams.parse(inputStream).getPublicKey());
        this.dhAgreePublicKey = dHPublicKeyParametersValidateDHPublicKey;
        this.dhParameters = validateDHParameters(dHPublicKeyParametersValidateDHPublicKey.getParameters());
    }

    @Override // org.spongycastle.crypto.tls.AbstractTlsKeyExchange, org.spongycastle.crypto.tls.TlsKeyExchange
    public boolean requiresServerKeyExchange() {
        int i2 = this.keyExchange;
        return i2 == 3 || i2 == 5 || i2 == 11;
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void skipServerCredentials() throws TlsFatalAlert {
        if (this.keyExchange != 11) {
            throw new TlsFatalAlert((short) 10);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void validateCertificateRequest(CertificateRequest certificateRequest) throws TlsFatalAlert {
        if (this.keyExchange == 11) {
            throw new TlsFatalAlert((short) 40);
        }
        for (short s2 : certificateRequest.getCertificateTypes()) {
            if (s2 != 1 && s2 != 2 && s2 != 3 && s2 != 4 && s2 != 64) {
                throw new TlsFatalAlert((short) 47);
            }
        }
    }

    public DHParameters validateDHParameters(DHParameters dHParameters) throws TlsFatalAlert {
        if (dHParameters.getP().bitLength() >= getMinimumPrimeBits()) {
            return TlsDHUtils.validateDHParameters(dHParameters);
        }
        throw new TlsFatalAlert((short) 71);
    }
}

package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Vector;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.agreement.srp.SRP6Client;
import org.spongycastle.crypto.agreement.srp.SRP6Server;
import org.spongycastle.crypto.agreement.srp.SRP6Util;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.SRP6GroupParameters;
import org.spongycastle.crypto.util.PublicKeyFactory;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;
import org.spongycastle.util.io.TeeInputStream;

/* JADX INFO: loaded from: classes.dex */
public class TlsSRPKeyExchange extends AbstractTlsKeyExchange {
    protected TlsSRPGroupVerifier groupVerifier;
    protected byte[] identity;
    protected byte[] password;
    protected TlsSignerCredentials serverCredentials;
    protected AsymmetricKeyParameter serverPublicKey;
    protected SRP6Client srpClient;
    protected SRP6GroupParameters srpGroup;
    protected BigInteger srpPeerCredentials;
    protected byte[] srpSalt;
    protected SRP6Server srpServer;
    protected BigInteger srpVerifier;
    protected TlsSigner tlsSigner;

    public TlsSRPKeyExchange(int i2, Vector vector, byte[] bArr, byte[] bArr2) {
        this(i2, vector, new DefaultTlsSRPGroupVerifier(), bArr, bArr2);
    }

    public static TlsSigner createSigner(int i2) {
        switch (i2) {
            case 21:
                return null;
            case 22:
                return new TlsDSSSigner();
            case 23:
                return new TlsRSASigner();
            default:
                throw new IllegalArgumentException("unsupported key exchange algorithm");
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void generateClientKeyExchange(OutputStream outputStream) throws IOException {
        TlsSRPUtils.writeSRPParameter(this.srpClient.generateClientCredentials(this.srpSalt, this.identity, this.password), outputStream);
        this.context.getSecurityParameters().srpIdentity = Arrays.clone(this.identity);
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public byte[] generatePremasterSecret() throws TlsFatalAlert {
        try {
            SRP6Server sRP6Server = this.srpServer;
            return BigIntegers.asUnsignedByteArray(sRP6Server != null ? sRP6Server.calculateSecret(this.srpPeerCredentials) : this.srpClient.calculateSecret(this.srpPeerCredentials));
        } catch (CryptoException e2) {
            throw new TlsFatalAlert((short) 47, e2);
        }
    }

    @Override // org.spongycastle.crypto.tls.AbstractTlsKeyExchange, org.spongycastle.crypto.tls.TlsKeyExchange
    public byte[] generateServerKeyExchange() throws IOException {
        this.srpServer.init(this.srpGroup, this.srpVerifier, TlsUtils.createHash((short) 2), this.context.getSecureRandom());
        ServerSRPParams serverSRPParams = new ServerSRPParams(this.srpGroup.getN(), this.srpGroup.getG(), this.srpSalt, this.srpServer.generateServerCredentials());
        DigestInputBuffer digestInputBuffer = new DigestInputBuffer();
        serverSRPParams.encode(digestInputBuffer);
        TlsSignerCredentials tlsSignerCredentials = this.serverCredentials;
        if (tlsSignerCredentials != null) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = TlsUtils.getSignatureAndHashAlgorithm(this.context, tlsSignerCredentials);
            Digest digestCreateHash = TlsUtils.createHash(signatureAndHashAlgorithm);
            SecurityParameters securityParameters = this.context.getSecurityParameters();
            byte[] bArr = securityParameters.clientRandom;
            digestCreateHash.update(bArr, 0, bArr.length);
            byte[] bArr2 = securityParameters.serverRandom;
            digestCreateHash.update(bArr2, 0, bArr2.length);
            digestInputBuffer.updateDigest(digestCreateHash);
            byte[] bArr3 = new byte[digestCreateHash.getDigestSize()];
            digestCreateHash.doFinal(bArr3, 0);
            new DigitallySigned(signatureAndHashAlgorithm, this.serverCredentials.generateCertificateSignature(bArr3)).encode(digestInputBuffer);
        }
        return digestInputBuffer.toByteArray();
    }

    @Override // org.spongycastle.crypto.tls.AbstractTlsKeyExchange, org.spongycastle.crypto.tls.TlsKeyExchange
    public void init(TlsContext tlsContext) {
        super.init(tlsContext);
        TlsSigner tlsSigner = this.tlsSigner;
        if (tlsSigner != null) {
            tlsSigner.init(tlsContext);
        }
    }

    public Signer initVerifyer(TlsSigner tlsSigner, SignatureAndHashAlgorithm signatureAndHashAlgorithm, SecurityParameters securityParameters) {
        Signer signerCreateVerifyer = tlsSigner.createVerifyer(signatureAndHashAlgorithm, this.serverPublicKey);
        byte[] bArr = securityParameters.clientRandom;
        signerCreateVerifyer.update(bArr, 0, bArr.length);
        byte[] bArr2 = securityParameters.serverRandom;
        signerCreateVerifyer.update(bArr2, 0, bArr2.length);
        return signerCreateVerifyer;
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void processClientCredentials(TlsCredentials tlsCredentials) throws TlsFatalAlert {
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.spongycastle.crypto.tls.AbstractTlsKeyExchange, org.spongycastle.crypto.tls.TlsKeyExchange
    public void processClientKeyExchange(InputStream inputStream) throws TlsFatalAlert {
        try {
            this.srpPeerCredentials = SRP6Util.validatePublicValue(this.srpGroup.getN(), TlsSRPUtils.readSRPParameter(inputStream));
            this.context.getSecurityParameters().srpIdentity = Arrays.clone(this.identity);
        } catch (CryptoException e2) {
            throw new TlsFatalAlert((short) 47, e2);
        }
    }

    @Override // org.spongycastle.crypto.tls.AbstractTlsKeyExchange, org.spongycastle.crypto.tls.TlsKeyExchange
    public void processServerCertificate(Certificate certificate) throws TlsFatalAlert {
        if (this.tlsSigner == null) {
            throw new TlsFatalAlert((short) 10);
        }
        if (certificate.isEmpty()) {
            throw new TlsFatalAlert((short) 42);
        }
        org.spongycastle.asn1.x509.Certificate certificateAt = certificate.getCertificateAt(0);
        try {
            AsymmetricKeyParameter asymmetricKeyParameterCreateKey = PublicKeyFactory.createKey(certificateAt.getSubjectPublicKeyInfo());
            this.serverPublicKey = asymmetricKeyParameterCreateKey;
            if (!this.tlsSigner.isValidPublicKey(asymmetricKeyParameterCreateKey)) {
                throw new TlsFatalAlert((short) 46);
            }
            TlsUtils.validateKeyUsage(certificateAt, 128);
            super.processServerCertificate(certificate);
        } catch (RuntimeException e2) {
            throw new TlsFatalAlert((short) 43, e2);
        }
    }

    @Override // org.spongycastle.crypto.tls.AbstractTlsKeyExchange, org.spongycastle.crypto.tls.TlsKeyExchange
    public void processServerCredentials(TlsCredentials tlsCredentials) throws TlsFatalAlert {
        if (this.keyExchange == 21 || !(tlsCredentials instanceof TlsSignerCredentials)) {
            throw new TlsFatalAlert((short) 80);
        }
        processServerCertificate(tlsCredentials.getCertificate());
        this.serverCredentials = (TlsSignerCredentials) tlsCredentials;
    }

    @Override // org.spongycastle.crypto.tls.AbstractTlsKeyExchange, org.spongycastle.crypto.tls.TlsKeyExchange
    public void processServerKeyExchange(InputStream inputStream) throws TlsFatalAlert {
        SignerInputBuffer signerInputBuffer;
        InputStream teeInputStream;
        SecurityParameters securityParameters = this.context.getSecurityParameters();
        if (this.tlsSigner != null) {
            signerInputBuffer = new SignerInputBuffer();
            teeInputStream = new TeeInputStream(inputStream, signerInputBuffer);
        } else {
            signerInputBuffer = null;
            teeInputStream = inputStream;
        }
        ServerSRPParams serverSRPParams = ServerSRPParams.parse(teeInputStream);
        if (signerInputBuffer != null) {
            DigitallySigned signature = parseSignature(inputStream);
            Signer signerInitVerifyer = initVerifyer(this.tlsSigner, signature.getAlgorithm(), securityParameters);
            signerInputBuffer.updateSigner(signerInitVerifyer);
            if (!signerInitVerifyer.verifySignature(signature.getSignature())) {
                throw new TlsFatalAlert((short) 51);
            }
        }
        SRP6GroupParameters sRP6GroupParameters = new SRP6GroupParameters(serverSRPParams.getN(), serverSRPParams.getG());
        this.srpGroup = sRP6GroupParameters;
        if (!this.groupVerifier.accept(sRP6GroupParameters)) {
            throw new TlsFatalAlert((short) 71);
        }
        this.srpSalt = serverSRPParams.getS();
        try {
            this.srpPeerCredentials = SRP6Util.validatePublicValue(this.srpGroup.getN(), serverSRPParams.getB());
            this.srpClient.init(this.srpGroup, TlsUtils.createHash((short) 2), this.context.getSecureRandom());
        } catch (CryptoException e2) {
            throw new TlsFatalAlert((short) 47, e2);
        }
    }

    @Override // org.spongycastle.crypto.tls.AbstractTlsKeyExchange, org.spongycastle.crypto.tls.TlsKeyExchange
    public boolean requiresServerKeyExchange() {
        return true;
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void skipServerCredentials() throws TlsFatalAlert {
        if (this.tlsSigner != null) {
            throw new TlsFatalAlert((short) 10);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void validateCertificateRequest(CertificateRequest certificateRequest) throws TlsFatalAlert {
        throw new TlsFatalAlert((short) 10);
    }

    public TlsSRPKeyExchange(int i2, Vector vector, TlsSRPGroupVerifier tlsSRPGroupVerifier, byte[] bArr, byte[] bArr2) {
        super(i2, vector);
        this.serverPublicKey = null;
        this.srpGroup = null;
        this.srpClient = null;
        this.srpServer = null;
        this.srpPeerCredentials = null;
        this.srpVerifier = null;
        this.srpSalt = null;
        this.serverCredentials = null;
        this.tlsSigner = createSigner(i2);
        this.groupVerifier = tlsSRPGroupVerifier;
        this.identity = bArr;
        this.password = bArr2;
        this.srpClient = new SRP6Client();
    }

    public TlsSRPKeyExchange(int i2, Vector vector, byte[] bArr, TlsSRPLoginParameters tlsSRPLoginParameters) {
        super(i2, vector);
        this.serverPublicKey = null;
        this.srpGroup = null;
        this.srpClient = null;
        this.srpServer = null;
        this.srpPeerCredentials = null;
        this.srpVerifier = null;
        this.srpSalt = null;
        this.serverCredentials = null;
        this.tlsSigner = createSigner(i2);
        this.identity = bArr;
        this.srpServer = new SRP6Server();
        this.srpGroup = tlsSRPLoginParameters.getGroup();
        this.srpVerifier = tlsSRPLoginParameters.getVerifier();
        this.srpSalt = tlsSRPLoginParameters.getSalt();
    }
}

package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPublicKeyParameters;
import org.spongycastle.util.io.TeeInputStream;

/* JADX INFO: loaded from: classes.dex */
public class TlsDHEKeyExchange extends TlsDHKeyExchange {
    protected TlsSignerCredentials serverCredentials;

    public TlsDHEKeyExchange(int i2, Vector vector, DHParameters dHParameters) {
        super(i2, vector, dHParameters);
        this.serverCredentials = null;
    }

    @Override // org.spongycastle.crypto.tls.TlsDHKeyExchange, org.spongycastle.crypto.tls.AbstractTlsKeyExchange, org.spongycastle.crypto.tls.TlsKeyExchange
    public byte[] generateServerKeyExchange() throws IOException {
        if (this.dhParameters == null) {
            throw new TlsFatalAlert((short) 80);
        }
        DigestInputBuffer digestInputBuffer = new DigestInputBuffer();
        this.dhAgreePrivateKey = TlsDHUtils.generateEphemeralServerKeyExchange(this.context.getSecureRandom(), this.dhParameters, digestInputBuffer);
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = TlsUtils.getSignatureAndHashAlgorithm(this.context, this.serverCredentials);
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
        return digestInputBuffer.toByteArray();
    }

    public Signer initVerifyer(TlsSigner tlsSigner, SignatureAndHashAlgorithm signatureAndHashAlgorithm, SecurityParameters securityParameters) {
        Signer signerCreateVerifyer = tlsSigner.createVerifyer(signatureAndHashAlgorithm, this.serverPublicKey);
        byte[] bArr = securityParameters.clientRandom;
        signerCreateVerifyer.update(bArr, 0, bArr.length);
        byte[] bArr2 = securityParameters.serverRandom;
        signerCreateVerifyer.update(bArr2, 0, bArr2.length);
        return signerCreateVerifyer;
    }

    @Override // org.spongycastle.crypto.tls.AbstractTlsKeyExchange, org.spongycastle.crypto.tls.TlsKeyExchange
    public void processServerCredentials(TlsCredentials tlsCredentials) throws TlsFatalAlert {
        if (!(tlsCredentials instanceof TlsSignerCredentials)) {
            throw new TlsFatalAlert((short) 80);
        }
        processServerCertificate(tlsCredentials.getCertificate());
        this.serverCredentials = (TlsSignerCredentials) tlsCredentials;
    }

    @Override // org.spongycastle.crypto.tls.TlsDHKeyExchange, org.spongycastle.crypto.tls.AbstractTlsKeyExchange, org.spongycastle.crypto.tls.TlsKeyExchange
    public void processServerKeyExchange(InputStream inputStream) throws TlsFatalAlert {
        SecurityParameters securityParameters = this.context.getSecurityParameters();
        SignerInputBuffer signerInputBuffer = new SignerInputBuffer();
        ServerDHParams serverDHParams = ServerDHParams.parse(new TeeInputStream(inputStream, signerInputBuffer));
        DigitallySigned signature = parseSignature(inputStream);
        Signer signerInitVerifyer = initVerifyer(this.tlsSigner, signature.getAlgorithm(), securityParameters);
        signerInputBuffer.updateSigner(signerInitVerifyer);
        if (!signerInitVerifyer.verifySignature(signature.getSignature())) {
            throw new TlsFatalAlert((short) 51);
        }
        DHPublicKeyParameters dHPublicKeyParametersValidateDHPublicKey = TlsDHUtils.validateDHPublicKey(serverDHParams.getPublicKey());
        this.dhAgreePublicKey = dHPublicKeyParametersValidateDHPublicKey;
        this.dhParameters = validateDHParameters(dHPublicKeyParametersValidateDHPublicKey.getParameters());
    }
}

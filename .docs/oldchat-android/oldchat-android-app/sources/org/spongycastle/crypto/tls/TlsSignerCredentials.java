package org.spongycastle.crypto.tls;

/* JADX INFO: loaded from: classes.dex */
public interface TlsSignerCredentials extends TlsCredentials {
    byte[] generateCertificateSignature(byte[] bArr);

    SignatureAndHashAlgorithm getSignatureAndHashAlgorithm();
}

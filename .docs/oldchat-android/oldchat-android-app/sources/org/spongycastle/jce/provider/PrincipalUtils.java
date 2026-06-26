package org.spongycastle.jce.provider;

import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.x509.X509AttributeCertificate;

/* JADX INFO: loaded from: classes.dex */
class PrincipalUtils {
    public static X500Name getCA(TrustAnchor trustAnchor) {
        return X500Name.getInstance(trustAnchor.getCA().getEncoded());
    }

    public static X500Name getEncodedIssuerPrincipal(Object obj) {
        return obj instanceof X509Certificate ? getIssuerPrincipal((X509Certificate) obj) : X500Name.getInstance(((X500Principal) ((X509AttributeCertificate) obj).getIssuer().getPrincipals()[0]).getEncoded());
    }

    public static X500Name getIssuerPrincipal(X509CRL x509crl) {
        return X500Name.getInstance(x509crl.getIssuerX500Principal().getEncoded());
    }

    public static X500Name getSubjectPrincipal(X509Certificate x509Certificate) {
        return X500Name.getInstance(x509Certificate.getSubjectX500Principal().getEncoded());
    }

    public static X500Name getIssuerPrincipal(X509Certificate x509Certificate) {
        return X500Name.getInstance(x509Certificate.getIssuerX500Principal().getEncoded());
    }
}

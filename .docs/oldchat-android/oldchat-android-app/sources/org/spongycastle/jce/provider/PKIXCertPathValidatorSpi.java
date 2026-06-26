package org.spongycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertPathValidatorSpi;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.asn1.x509.TBSCertificate;
import org.spongycastle.jcajce.PKIXExtendedBuilderParameters;
import org.spongycastle.jcajce.PKIXExtendedParameters;
import org.spongycastle.jcajce.util.BCJcaJceHelper;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.jce.exception.ExtCertPathValidatorException;
import org.spongycastle.x509.ExtendedPKIXParameters;

/* JADX INFO: loaded from: classes.dex */
public class PKIXCertPathValidatorSpi extends CertPathValidatorSpi {
    private final JcaJceHelper helper = new BCJcaJceHelper();

    public static void checkCertificate(X509Certificate x509Certificate) throws AnnotatedException {
        try {
            TBSCertificate.getInstance(x509Certificate.getTBSCertificate());
        } catch (IllegalArgumentException e2) {
            throw new AnnotatedException(e2.getMessage());
        } catch (CertificateEncodingException unused) {
            throw new AnnotatedException("unable to process TBSCertificate");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v8, types: [org.spongycastle.asn1.x509.AlgorithmIdentifier] */
    @Override // java.security.cert.CertPathValidatorSpi
    public CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters certPathParameters) throws CertPathValidatorException, InvalidAlgorithmParameterException {
        PKIXExtendedParameters baseParameters;
        List<? extends Certificate> list;
        X500Name ca;
        PublicKey cAPublicKey;
        HashSet hashSet;
        PKIXNameConstraintValidator pKIXNameConstraintValidator;
        ArrayList[] arrayListArr;
        HashSet hashSet2;
        PKIXCertPathValidatorSpi pKIXCertPathValidatorSpi = this;
        if (certPathParameters instanceof PKIXParameters) {
            PKIXExtendedParameters.Builder builder = new PKIXExtendedParameters.Builder((PKIXParameters) certPathParameters);
            if (certPathParameters instanceof ExtendedPKIXParameters) {
                ExtendedPKIXParameters extendedPKIXParameters = (ExtendedPKIXParameters) certPathParameters;
                builder.setUseDeltasEnabled(extendedPKIXParameters.isUseDeltasEnabled());
                builder.setValidityModel(extendedPKIXParameters.getValidityModel());
            }
            baseParameters = builder.build();
        } else if (certPathParameters instanceof PKIXExtendedBuilderParameters) {
            baseParameters = ((PKIXExtendedBuilderParameters) certPathParameters).getBaseParameters();
        } else {
            if (!(certPathParameters instanceof PKIXExtendedParameters)) {
                throw new InvalidAlgorithmParameterException("Parameters must be a " + PKIXParameters.class.getName() + " instance.");
            }
            baseParameters = (PKIXExtendedParameters) certPathParameters;
        }
        if (baseParameters.getTrustAnchors() == null) {
            throw new InvalidAlgorithmParameterException("trustAnchors is null, this is not allowed for certification path validation.");
        }
        List<? extends Certificate> certificates = certPath.getCertificates();
        int size = certificates.size();
        int algorithmIdentifier = -1;
        if (certificates.isEmpty()) {
            throw new CertPathValidatorException("Certification path is empty.", null, certPath, -1);
        }
        Set initialPolicies = baseParameters.getInitialPolicies();
        try {
            TrustAnchor trustAnchorFindTrustAnchor = CertPathValidatorUtilities.findTrustAnchor((X509Certificate) certificates.get(certificates.size() - 1), baseParameters.getTrustAnchors(), baseParameters.getSigProvider());
            if (trustAnchorFindTrustAnchor != null) {
                checkCertificate(trustAnchorFindTrustAnchor.getTrustedCert());
                PKIXExtendedParameters pKIXExtendedParametersBuild = new PKIXExtendedParameters.Builder(baseParameters).setTrustAnchor(trustAnchorFindTrustAnchor).build();
                int i2 = size + 1;
                ArrayList[] arrayListArr2 = new ArrayList[i2];
                for (int i3 = 0; i3 < i2; i3++) {
                    arrayListArr2[i3] = new ArrayList();
                }
                HashSet hashSet3 = new HashSet();
                hashSet3.add(RFC3280CertPathUtilities.ANY_POLICY);
                PKIXPolicyNode pKIXPolicyNode = new PKIXPolicyNode(new ArrayList(), 0, hashSet3, null, new HashSet(), RFC3280CertPathUtilities.ANY_POLICY, false);
                arrayListArr2[0].add(pKIXPolicyNode);
                PKIXNameConstraintValidator pKIXNameConstraintValidator2 = new PKIXNameConstraintValidator();
                HashSet hashSet4 = new HashSet();
                int i4 = pKIXExtendedParametersBuild.isExplicitPolicyRequired() ? 0 : i2;
                int i5 = pKIXExtendedParametersBuild.isAnyPolicyInhibited() ? 0 : i2;
                if (pKIXExtendedParametersBuild.isPolicyMappingInhibited()) {
                    i2 = 0;
                }
                X509Certificate trustedCert = trustAnchorFindTrustAnchor.getTrustedCert();
                try {
                    if (trustedCert != null) {
                        ca = PrincipalUtils.getSubjectPrincipal(trustedCert);
                        cAPublicKey = trustedCert.getPublicKey();
                    } else {
                        ca = PrincipalUtils.getCA(trustAnchorFindTrustAnchor);
                        cAPublicKey = trustAnchorFindTrustAnchor.getCAPublicKey();
                    }
                    try {
                        algorithmIdentifier = CertPathValidatorUtilities.getAlgorithmIdentifier(cAPublicKey);
                        algorithmIdentifier.getAlgorithm();
                        algorithmIdentifier.getParameters();
                        if (pKIXExtendedParametersBuild.getTargetConstraints() != null && !pKIXExtendedParametersBuild.getTargetConstraints().match((Certificate) certificates.get(0))) {
                            throw new ExtCertPathValidatorException("Target certificate in certification path does not match targetConstraints.", null, certPath, 0);
                        }
                        List certPathCheckers = pKIXExtendedParametersBuild.getCertPathCheckers();
                        Iterator it = certPathCheckers.iterator();
                        while (it.hasNext()) {
                            ((PKIXCertPathChecker) it.next()).init(false);
                        }
                        int iPrepareNextCertM = size;
                        X509Certificate x509Certificate = null;
                        int i6 = i2;
                        int i7 = i5;
                        PKIXPolicyNode pKIXPolicyNode2 = pKIXPolicyNode;
                        int i8 = i4;
                        int size2 = certificates.size() - 1;
                        int iPrepareNextCertI1 = i8;
                        while (size2 >= 0) {
                            int i9 = size - size2;
                            Set set = initialPolicies;
                            X509Certificate x509Certificate2 = (X509Certificate) certificates.get(size2);
                            boolean z2 = size2 == certificates.size() + (-1);
                            try {
                                checkCertificate(x509Certificate2);
                                TrustAnchor trustAnchor = trustAnchorFindTrustAnchor;
                                JcaJceHelper jcaJceHelper = pKIXCertPathValidatorSpi.helper;
                                int i10 = i7;
                                List<? extends Certificate> list2 = certificates;
                                int i11 = iPrepareNextCertI1;
                                PKIXExtendedParameters pKIXExtendedParameters = pKIXExtendedParametersBuild;
                                int i12 = size2;
                                PKIXExtendedParameters pKIXExtendedParameters2 = pKIXExtendedParametersBuild;
                                int iPrepareNextCertI2 = i6;
                                PKIXNameConstraintValidator pKIXNameConstraintValidator3 = pKIXNameConstraintValidator2;
                                ArrayList[] arrayListArr3 = arrayListArr2;
                                RFC3280CertPathUtilities.processCertA(certPath, pKIXExtendedParameters, size2, cAPublicKey, z2, ca, trustedCert, jcaJceHelper);
                                RFC3280CertPathUtilities.processCertBC(certPath, i12, pKIXNameConstraintValidator3);
                                PKIXPolicyNode pKIXPolicyNodeProcessCertE = RFC3280CertPathUtilities.processCertE(certPath, i12, RFC3280CertPathUtilities.processCertD(certPath, i12, hashSet4, pKIXPolicyNode2, arrayListArr3, i10));
                                RFC3280CertPathUtilities.processCertF(certPath, i12, pKIXPolicyNodeProcessCertE, i11);
                                if (i9 == size) {
                                    pKIXNameConstraintValidator = pKIXNameConstraintValidator3;
                                    arrayListArr = arrayListArr3;
                                    pKIXCertPathValidatorSpi = this;
                                    pKIXPolicyNode2 = pKIXPolicyNodeProcessCertE;
                                    i7 = i10;
                                    iPrepareNextCertM = iPrepareNextCertM;
                                    iPrepareNextCertI1 = i11;
                                } else if (x509Certificate2 == null || x509Certificate2.getVersion() != 1) {
                                    RFC3280CertPathUtilities.prepareNextCertA(certPath, i12);
                                    arrayListArr = arrayListArr3;
                                    PKIXPolicyNode pKIXPolicyNodePrepareCertB = RFC3280CertPathUtilities.prepareCertB(certPath, i12, arrayListArr, pKIXPolicyNodeProcessCertE, iPrepareNextCertI2);
                                    RFC3280CertPathUtilities.prepareNextCertG(certPath, i12, pKIXNameConstraintValidator3);
                                    int iPrepareNextCertH1 = RFC3280CertPathUtilities.prepareNextCertH1(certPath, i12, i11);
                                    int iPrepareNextCertH2 = RFC3280CertPathUtilities.prepareNextCertH2(certPath, i12, iPrepareNextCertI2);
                                    int iPrepareNextCertH3 = RFC3280CertPathUtilities.prepareNextCertH3(certPath, i12, i10);
                                    iPrepareNextCertI1 = RFC3280CertPathUtilities.prepareNextCertI1(certPath, i12, iPrepareNextCertH1);
                                    iPrepareNextCertI2 = RFC3280CertPathUtilities.prepareNextCertI2(certPath, i12, iPrepareNextCertH2);
                                    int iPrepareNextCertJ = RFC3280CertPathUtilities.prepareNextCertJ(certPath, i12, iPrepareNextCertH3);
                                    RFC3280CertPathUtilities.prepareNextCertK(certPath, i12);
                                    iPrepareNextCertM = RFC3280CertPathUtilities.prepareNextCertM(certPath, i12, RFC3280CertPathUtilities.prepareNextCertL(certPath, i12, iPrepareNextCertM));
                                    RFC3280CertPathUtilities.prepareNextCertN(certPath, i12);
                                    Set<String> criticalExtensionOIDs = x509Certificate2.getCriticalExtensionOIDs();
                                    if (criticalExtensionOIDs != null) {
                                        hashSet2 = new HashSet(criticalExtensionOIDs);
                                        hashSet2.remove(RFC3280CertPathUtilities.KEY_USAGE);
                                        hashSet2.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                                        hashSet2.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                                        hashSet2.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                                        hashSet2.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                                        hashSet2.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                                        hashSet2.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                                        hashSet2.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                                        hashSet2.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                                        hashSet2.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                                    } else {
                                        hashSet2 = new HashSet();
                                    }
                                    RFC3280CertPathUtilities.prepareNextCertO(certPath, i12, hashSet2, certPathCheckers);
                                    X500Name subjectPrincipal = PrincipalUtils.getSubjectPrincipal(x509Certificate2);
                                    try {
                                        pKIXNameConstraintValidator = pKIXNameConstraintValidator3;
                                        pKIXCertPathValidatorSpi = this;
                                    } catch (CertPathValidatorException e2) {
                                        e = e2;
                                    }
                                    try {
                                        PublicKey nextWorkingKey = CertPathValidatorUtilities.getNextWorkingKey(certPath.getCertificates(), i12, pKIXCertPathValidatorSpi.helper);
                                        AlgorithmIdentifier algorithmIdentifier2 = CertPathValidatorUtilities.getAlgorithmIdentifier(nextWorkingKey);
                                        algorithmIdentifier2.getAlgorithm();
                                        algorithmIdentifier2.getParameters();
                                        pKIXPolicyNode2 = pKIXPolicyNodePrepareCertB;
                                        i7 = iPrepareNextCertJ;
                                        ca = subjectPrincipal;
                                        cAPublicKey = nextWorkingKey;
                                        trustedCert = x509Certificate2;
                                    } catch (CertPathValidatorException e3) {
                                        e = e3;
                                        throw new CertPathValidatorException("Next working key could not be retrieved.", e, certPath, i12);
                                    }
                                } else {
                                    if (i9 != 1 || !x509Certificate2.equals(trustAnchor.getTrustedCert())) {
                                        throw new CertPathValidatorException("Version 1 certificates can't be used as CA ones.", null, certPath, i12);
                                    }
                                    pKIXNameConstraintValidator = pKIXNameConstraintValidator3;
                                    arrayListArr = arrayListArr3;
                                    pKIXCertPathValidatorSpi = this;
                                    pKIXPolicyNode2 = pKIXPolicyNodeProcessCertE;
                                    i7 = i10;
                                    iPrepareNextCertM = iPrepareNextCertM;
                                    iPrepareNextCertI1 = i11;
                                }
                                i6 = iPrepareNextCertI2;
                                size2 = i12 - 1;
                                x509Certificate = x509Certificate2;
                                initialPolicies = set;
                                certificates = list2;
                                pKIXExtendedParametersBuild = pKIXExtendedParameters2;
                                trustAnchorFindTrustAnchor = trustAnchor;
                                PKIXNameConstraintValidator pKIXNameConstraintValidator4 = pKIXNameConstraintValidator;
                                arrayListArr2 = arrayListArr;
                                pKIXNameConstraintValidator2 = pKIXNameConstraintValidator4;
                            } catch (AnnotatedException e4) {
                                throw new CertPathValidatorException(e4.getMessage(), e4.getUnderlyingException(), certPath, size2);
                            }
                        }
                        PKIXExtendedParameters pKIXExtendedParameters3 = pKIXExtendedParametersBuild;
                        ArrayList[] arrayListArr4 = arrayListArr2;
                        TrustAnchor trustAnchor2 = trustAnchorFindTrustAnchor;
                        Set set2 = initialPolicies;
                        X509Certificate x509Certificate3 = x509Certificate;
                        int i13 = size2;
                        int i14 = i13 + 1;
                        int iWrapupCertB = RFC3280CertPathUtilities.wrapupCertB(certPath, i14, RFC3280CertPathUtilities.wrapupCertA(iPrepareNextCertI1, x509Certificate3));
                        Set<String> criticalExtensionOIDs2 = x509Certificate3.getCriticalExtensionOIDs();
                        if (criticalExtensionOIDs2 != null) {
                            hashSet = new HashSet(criticalExtensionOIDs2);
                            hashSet.remove(RFC3280CertPathUtilities.KEY_USAGE);
                            hashSet.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                            hashSet.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                            hashSet.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                            hashSet.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                            hashSet.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                            hashSet.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                            hashSet.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                            hashSet.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                            hashSet.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                            hashSet.remove(RFC3280CertPathUtilities.CRL_DISTRIBUTION_POINTS);
                            hashSet.remove(Extension.extendedKeyUsage.getId());
                        } else {
                            hashSet = new HashSet();
                        }
                        RFC3280CertPathUtilities.wrapupCertF(certPath, i14, certPathCheckers, hashSet);
                        PKIXPolicyNode pKIXPolicyNodeWrapupCertG = RFC3280CertPathUtilities.wrapupCertG(certPath, pKIXExtendedParameters3, set2, i14, arrayListArr4, pKIXPolicyNode2, hashSet4);
                        if (iWrapupCertB > 0 || pKIXPolicyNodeWrapupCertG != null) {
                            return new PKIXCertPathValidatorResult(trustAnchor2, pKIXPolicyNodeWrapupCertG, x509Certificate3.getPublicKey());
                        }
                        throw new CertPathValidatorException("Path processing failed on policy.", null, certPath, i13);
                    } catch (CertPathValidatorException e5) {
                        throw new ExtCertPathValidatorException("Algorithm identifier of public key of trust anchor could not be read.", e5, certPath, -1);
                    }
                } catch (IllegalArgumentException e6) {
                    throw new ExtCertPathValidatorException("Subject of trust anchor could not be (re)encoded.", e6, certPath, algorithmIdentifier);
                }
            }
            list = certificates;
            try {
                throw new CertPathValidatorException("Trust anchor for certification path not found.", null, certPath, -1);
            } catch (AnnotatedException e7) {
                e = e7;
            }
        } catch (AnnotatedException e8) {
            e = e8;
            list = certificates;
        }
        throw new CertPathValidatorException(e.getMessage(), e.getUnderlyingException(), certPath, list.size() - 1);
    }
}

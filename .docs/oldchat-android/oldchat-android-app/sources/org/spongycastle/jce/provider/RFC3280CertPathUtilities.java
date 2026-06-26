package org.spongycastle.jce.provider;

import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.cert.CRL;
import java.security.cert.CertPath;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLSelector;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.security.cert.X509Extension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1String;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x500.RDN;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x500.style.BCStyle;
import org.spongycastle.asn1.x509.BasicConstraints;
import org.spongycastle.asn1.x509.CRLDistPoint;
import org.spongycastle.asn1.x509.DistributionPoint;
import org.spongycastle.asn1.x509.DistributionPointName;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.GeneralNames;
import org.spongycastle.asn1.x509.GeneralSubtree;
import org.spongycastle.asn1.x509.IssuingDistributionPoint;
import org.spongycastle.asn1.x509.NameConstraints;
import org.spongycastle.asn1.x509.PolicyInformation;
import org.spongycastle.jcajce.PKIXCRLStoreSelector;
import org.spongycastle.jcajce.PKIXCertStoreSelector;
import org.spongycastle.jcajce.PKIXExtendedBuilderParameters;
import org.spongycastle.jcajce.PKIXExtendedParameters;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.jce.exception.ExtCertPathValidatorException;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
class RFC3280CertPathUtilities {
    public static final String ANY_POLICY = "2.5.29.32.0";
    protected static final int CRL_SIGN = 6;
    protected static final int KEY_CERT_SIGN = 5;
    private static final PKIXCRLUtil CRL_UTIL = new PKIXCRLUtil();
    public static final String CERTIFICATE_POLICIES = Extension.certificatePolicies.getId();
    public static final String POLICY_MAPPINGS = Extension.policyMappings.getId();
    public static final String INHIBIT_ANY_POLICY = Extension.inhibitAnyPolicy.getId();
    public static final String ISSUING_DISTRIBUTION_POINT = Extension.issuingDistributionPoint.getId();
    public static final String FRESHEST_CRL = Extension.freshestCRL.getId();
    public static final String DELTA_CRL_INDICATOR = Extension.deltaCRLIndicator.getId();
    public static final String POLICY_CONSTRAINTS = Extension.policyConstraints.getId();
    public static final String BASIC_CONSTRAINTS = Extension.basicConstraints.getId();
    public static final String CRL_DISTRIBUTION_POINTS = Extension.cRLDistributionPoints.getId();
    public static final String SUBJECT_ALTERNATIVE_NAME = Extension.subjectAlternativeName.getId();
    public static final String NAME_CONSTRAINTS = Extension.nameConstraints.getId();
    public static final String AUTHORITY_KEY_IDENTIFIER = Extension.authorityKeyIdentifier.getId();
    public static final String KEY_USAGE = Extension.keyUsage.getId();
    public static final String CRL_NUMBER = Extension.cRLNumber.getId();
    protected static final String[] crlReasons = {"unspecified", "keyCompromise", "cACompromise", "affiliationChanged", "superseded", "cessationOfOperation", "certificateHold", "unknown", "removeFromCRL", "privilegeWithdrawn", "aACompromise"};

    private static void checkCRL(DistributionPoint distributionPoint, PKIXExtendedParameters pKIXExtendedParameters, X509Certificate x509Certificate, Date date, X509Certificate x509Certificate2, PublicKey publicKey, CertStatus certStatus, ReasonsMask reasonsMask, List list, JcaJceHelper jcaJceHelper) throws AnnotatedException {
        Date date2;
        Iterator it;
        Set<String> criticalExtensionOIDs;
        Date date3 = new Date(System.currentTimeMillis());
        if (date.getTime() > date3.getTime()) {
            throw new AnnotatedException("Validation time is in future.");
        }
        Iterator it2 = CertPathValidatorUtilities.getCompleteCRLs(distributionPoint, x509Certificate, date3, pKIXExtendedParameters).iterator();
        AnnotatedException e2 = null;
        boolean z2 = false;
        while (it2.hasNext() && certStatus.getCertStatus() == 11 && !reasonsMask.isAllReasons()) {
            try {
                X509CRL x509crl = (X509CRL) it2.next();
                ReasonsMask reasonsMaskProcessCRLD = processCRLD(x509crl, distributionPoint);
                if (reasonsMaskProcessCRLD.hasNewReasons(reasonsMask)) {
                    date2 = date3;
                    it = it2;
                    AnnotatedException annotatedException = e2;
                    try {
                        X509CRL x509crlProcessCRLH = pKIXExtendedParameters.isUseDeltasEnabled() ? processCRLH(CertPathValidatorUtilities.getDeltaCRLs(pKIXExtendedParameters.getDate() != null ? pKIXExtendedParameters.getDate() : date2, x509crl, pKIXExtendedParameters.getCertStores(), pKIXExtendedParameters.getCRLStores()), processCRLG(x509crl, processCRLF(x509crl, x509Certificate, x509Certificate2, publicKey, pKIXExtendedParameters, list, jcaJceHelper))) : null;
                        if (pKIXExtendedParameters.getValidityModel() != 1 && x509Certificate.getNotAfter().getTime() < x509crl.getThisUpdate().getTime()) {
                            throw new AnnotatedException("No valid CRL for current time found.");
                        }
                        processCRLB1(distributionPoint, x509Certificate, x509crl);
                        processCRLB2(distributionPoint, x509Certificate, x509crl);
                        processCRLC(x509crlProcessCRLH, x509crl, pKIXExtendedParameters);
                        processCRLI(date, x509crlProcessCRLH, x509Certificate, certStatus, pKIXExtendedParameters);
                        processCRLJ(date, x509crl, x509Certificate, certStatus);
                        if (certStatus.getCertStatus() == 8) {
                            certStatus.setCertStatus(11);
                        }
                        reasonsMask.addReasons(reasonsMaskProcessCRLD);
                        Set<String> criticalExtensionOIDs2 = x509crl.getCriticalExtensionOIDs();
                        if (criticalExtensionOIDs2 != null) {
                            HashSet hashSet = new HashSet(criticalExtensionOIDs2);
                            hashSet.remove(Extension.issuingDistributionPoint.getId());
                            hashSet.remove(Extension.deltaCRLIndicator.getId());
                            if (!hashSet.isEmpty()) {
                                throw new AnnotatedException("CRL contains unsupported critical extensions.");
                            }
                        }
                        if (x509crlProcessCRLH != null && (criticalExtensionOIDs = x509crlProcessCRLH.getCriticalExtensionOIDs()) != null) {
                            HashSet hashSet2 = new HashSet(criticalExtensionOIDs);
                            hashSet2.remove(Extension.issuingDistributionPoint.getId());
                            hashSet2.remove(Extension.deltaCRLIndicator.getId());
                            if (!hashSet2.isEmpty()) {
                                throw new AnnotatedException("Delta CRL contains unsupported critical extension.");
                            }
                        }
                        date3 = date2;
                        it2 = it;
                        e2 = annotatedException;
                        z2 = true;
                    } catch (AnnotatedException e3) {
                        e2 = e3;
                        date3 = date2;
                        it2 = it;
                    }
                } else {
                    continue;
                }
            } catch (AnnotatedException e4) {
                e2 = e4;
                date2 = date3;
                it = it2;
            }
        }
        AnnotatedException annotatedException2 = e2;
        if (!z2) {
            throw annotatedException2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0096  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void checkCRLs(org.spongycastle.jcajce.PKIXExtendedParameters r20, java.security.cert.X509Certificate r21, java.util.Date r22, java.security.cert.X509Certificate r23, java.security.PublicKey r24, java.util.List r25, org.spongycastle.jcajce.util.JcaJceHelper r26) throws org.spongycastle.jce.provider.AnnotatedException {
        /*
            Method dump skipped, instruction units count: 405
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.jce.provider.RFC3280CertPathUtilities.checkCRLs(org.spongycastle.jcajce.PKIXExtendedParameters, java.security.cert.X509Certificate, java.util.Date, java.security.cert.X509Certificate, java.security.PublicKey, java.util.List, org.spongycastle.jcajce.util.JcaJceHelper):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x00aa, code lost:
    
        r5 = r20[r3].iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00b4, code lost:
    
        if (r5.hasNext() == false) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00b6, code lost:
    
        r6 = (org.spongycastle.jce.provider.PKIXPolicyNode) r5.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00c6, code lost:
    
        if (org.spongycastle.jce.provider.RFC3280CertPathUtilities.ANY_POLICY.equals(r6.getValidPolicy()) == false) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00d0, code lost:
    
        r5 = ((org.spongycastle.asn1.ASN1Sequence) org.spongycastle.jce.provider.CertPathValidatorUtilities.getExtensionValue(r4, org.spongycastle.jce.provider.RFC3280CertPathUtilities.CERTIFICATE_POLICIES)).getObjects();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d8, code lost:
    
        if (r5.hasMoreElements() == false) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00da, code lost:
    
        r7 = org.spongycastle.asn1.x509.PolicyInformation.getInstance(r5.nextElement());
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00ee, code lost:
    
        if (org.spongycastle.jce.provider.RFC3280CertPathUtilities.ANY_POLICY.equals(r7.getPolicyIdentifier().getId()) == false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00f0, code lost:
    
        r5 = org.spongycastle.jce.provider.CertPathValidatorUtilities.getQualifierSet(r7.getPolicyQualifiers());
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00f8, code lost:
    
        r10 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00fa, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0102, code lost:
    
        throw new org.spongycastle.jce.exception.ExtCertPathValidatorException("Policy qualifier info set could not be decoded.", r0, r18, r19);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0103, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x010b, code lost:
    
        throw new java.security.cert.CertPathValidatorException("Policy information could not be decoded.", r0, r18, r19);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x010c, code lost:
    
        r5 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0112, code lost:
    
        if (r4.getCriticalExtensionOIDs() == null) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0114, code lost:
    
        r12 = r4.getCriticalExtensionOIDs().contains(org.spongycastle.jce.provider.RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0120, code lost:
    
        r12 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0121, code lost:
    
        r9 = (org.spongycastle.jce.provider.PKIXPolicyNode) r6.getParent();
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0130, code lost:
    
        if (org.spongycastle.jce.provider.RFC3280CertPathUtilities.ANY_POLICY.equals(r9.getValidPolicy()) == false) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0132, code lost:
    
        r8 = new org.spongycastle.jce.provider.PKIXPolicyNode(new java.util.ArrayList(), r3, (java.util.Set) r13.get(r11), r9, r10, r11, r12);
        r9.addChild(r8);
        r20[r3].add(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0156, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x015e, code lost:
    
        throw new org.spongycastle.jce.exception.ExtCertPathValidatorException("Certificate policies extension could not be decoded.", r0, r18, r19);
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01b3, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.spongycastle.jce.provider.PKIXPolicyNode prepareCertB(java.security.cert.CertPath r18, int r19, java.util.List[] r20, org.spongycastle.jce.provider.PKIXPolicyNode r21, int r22) throws java.security.cert.CertPathValidatorException {
        /*
            Method dump skipped, instruction units count: 452
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.jce.provider.RFC3280CertPathUtilities.prepareCertB(java.security.cert.CertPath, int, java.util.List[], org.spongycastle.jce.provider.PKIXPolicyNode, int):org.spongycastle.jce.provider.PKIXPolicyNode");
    }

    public static void prepareNextCertA(CertPath certPath, int i2) throws CertPathValidatorException {
        try {
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(i2), POLICY_MAPPINGS));
            if (aSN1Sequence != null) {
                for (int i3 = 0; i3 < aSN1Sequence.size(); i3++) {
                    try {
                        ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(i3));
                        ASN1ObjectIdentifier aSN1ObjectIdentifier = ASN1ObjectIdentifier.getInstance(aSN1Sequence2.getObjectAt(0));
                        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = ASN1ObjectIdentifier.getInstance(aSN1Sequence2.getObjectAt(1));
                        if (ANY_POLICY.equals(aSN1ObjectIdentifier.getId())) {
                            throw new CertPathValidatorException("IssuerDomainPolicy is anyPolicy", null, certPath, i2);
                        }
                        if (ANY_POLICY.equals(aSN1ObjectIdentifier2.getId())) {
                            throw new CertPathValidatorException("SubjectDomainPolicy is anyPolicy,", null, certPath, i2);
                        }
                    } catch (Exception e2) {
                        throw new ExtCertPathValidatorException("Policy mappings extension contents could not be decoded.", e2, certPath, i2);
                    }
                }
            }
        } catch (AnnotatedException e3) {
            throw new ExtCertPathValidatorException("Policy mappings extension could not be decoded.", e3, certPath, i2);
        }
    }

    public static void prepareNextCertG(CertPath certPath, int i2, PKIXNameConstraintValidator pKIXNameConstraintValidator) throws ExtCertPathValidatorException {
        try {
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(i2), NAME_CONSTRAINTS));
            NameConstraints nameConstraints = aSN1Sequence != null ? NameConstraints.getInstance(aSN1Sequence) : null;
            if (nameConstraints != null) {
                GeneralSubtree[] permittedSubtrees = nameConstraints.getPermittedSubtrees();
                if (permittedSubtrees != null) {
                    try {
                        pKIXNameConstraintValidator.intersectPermittedSubtree(permittedSubtrees);
                    } catch (Exception e2) {
                        throw new ExtCertPathValidatorException("Permitted subtrees cannot be build from name constraints extension.", e2, certPath, i2);
                    }
                }
                GeneralSubtree[] excludedSubtrees = nameConstraints.getExcludedSubtrees();
                if (excludedSubtrees != null) {
                    for (int i3 = 0; i3 != excludedSubtrees.length; i3++) {
                        try {
                            pKIXNameConstraintValidator.addExcludedSubtree(excludedSubtrees[i3]);
                        } catch (Exception e3) {
                            throw new ExtCertPathValidatorException("Excluded subtrees cannot be build from name constraints extension.", e3, certPath, i2);
                        }
                    }
                }
            }
        } catch (Exception e4) {
            throw new ExtCertPathValidatorException("Name constraints extension could not be decoded.", e4, certPath, i2);
        }
    }

    public static int prepareNextCertH1(CertPath certPath, int i2, int i3) {
        return (CertPathValidatorUtilities.isSelfIssued((X509Certificate) certPath.getCertificates().get(i2)) || i3 == 0) ? i3 : i3 - 1;
    }

    public static int prepareNextCertH2(CertPath certPath, int i2, int i3) {
        return (CertPathValidatorUtilities.isSelfIssued((X509Certificate) certPath.getCertificates().get(i2)) || i3 == 0) ? i3 : i3 - 1;
    }

    public static int prepareNextCertH3(CertPath certPath, int i2, int i3) {
        return (CertPathValidatorUtilities.isSelfIssued((X509Certificate) certPath.getCertificates().get(i2)) || i3 == 0) ? i3 : i3 - 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002e, code lost:
    
        r3 = org.spongycastle.asn1.ASN1Integer.getInstance(r1, false).getValue().intValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x003b, code lost:
    
        if (r3 >= r5) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x003d, code lost:
    
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int prepareNextCertI1(java.security.cert.CertPath r3, int r4, int r5) throws org.spongycastle.jce.exception.ExtCertPathValidatorException {
        /*
            java.util.List r0 = r3.getCertificates()
            java.lang.Object r0 = r0.get(r4)
            java.security.cert.X509Certificate r0 = (java.security.cert.X509Certificate) r0
            java.lang.String r1 = org.spongycastle.jce.provider.RFC3280CertPathUtilities.POLICY_CONSTRAINTS     // Catch: java.lang.Exception -> L48
            org.spongycastle.asn1.ASN1Primitive r0 = org.spongycastle.jce.provider.CertPathValidatorUtilities.getExtensionValue(r0, r1)     // Catch: java.lang.Exception -> L48
            org.spongycastle.asn1.ASN1Sequence r0 = org.spongycastle.asn1.ASN1Sequence.getInstance(r0)     // Catch: java.lang.Exception -> L48
            if (r0 == 0) goto L47
            java.util.Enumeration r0 = r0.getObjects()
        L1a:
            boolean r1 = r0.hasMoreElements()
            if (r1 == 0) goto L47
            java.lang.Object r1 = r0.nextElement()     // Catch: java.lang.IllegalArgumentException -> L3e
            org.spongycastle.asn1.ASN1TaggedObject r1 = org.spongycastle.asn1.ASN1TaggedObject.getInstance(r1)     // Catch: java.lang.IllegalArgumentException -> L3e
            int r2 = r1.getTagNo()     // Catch: java.lang.IllegalArgumentException -> L3e
            if (r2 != 0) goto L1a
            r0 = 0
            org.spongycastle.asn1.ASN1Integer r0 = org.spongycastle.asn1.ASN1Integer.getInstance(r1, r0)     // Catch: java.lang.IllegalArgumentException -> L3e
            java.math.BigInteger r0 = r0.getValue()     // Catch: java.lang.IllegalArgumentException -> L3e
            int r3 = r0.intValue()     // Catch: java.lang.IllegalArgumentException -> L3e
            if (r3 >= r5) goto L47
            return r3
        L3e:
            r5 = move-exception
            org.spongycastle.jce.exception.ExtCertPathValidatorException r0 = new org.spongycastle.jce.exception.ExtCertPathValidatorException
            java.lang.String r1 = "Policy constraints extension contents cannot be decoded."
            r0.<init>(r1, r5, r3, r4)
            throw r0
        L47:
            return r5
        L48:
            r5 = move-exception
            org.spongycastle.jce.exception.ExtCertPathValidatorException r0 = new org.spongycastle.jce.exception.ExtCertPathValidatorException
            java.lang.String r1 = "Policy constraints extension cannot be decoded."
            r0.<init>(r1, r5, r3, r4)
            goto L52
        L51:
            throw r0
        L52:
            goto L51
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.jce.provider.RFC3280CertPathUtilities.prepareNextCertI1(java.security.cert.CertPath, int, int):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002f, code lost:
    
        r4 = org.spongycastle.asn1.ASN1Integer.getInstance(r1, false).getValue().intValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x003c, code lost:
    
        if (r4 >= r6) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x003e, code lost:
    
        return r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int prepareNextCertI2(java.security.cert.CertPath r4, int r5, int r6) throws org.spongycastle.jce.exception.ExtCertPathValidatorException {
        /*
            java.util.List r0 = r4.getCertificates()
            java.lang.Object r0 = r0.get(r5)
            java.security.cert.X509Certificate r0 = (java.security.cert.X509Certificate) r0
            java.lang.String r1 = org.spongycastle.jce.provider.RFC3280CertPathUtilities.POLICY_CONSTRAINTS     // Catch: java.lang.Exception -> L49
            org.spongycastle.asn1.ASN1Primitive r0 = org.spongycastle.jce.provider.CertPathValidatorUtilities.getExtensionValue(r0, r1)     // Catch: java.lang.Exception -> L49
            org.spongycastle.asn1.ASN1Sequence r0 = org.spongycastle.asn1.ASN1Sequence.getInstance(r0)     // Catch: java.lang.Exception -> L49
            if (r0 == 0) goto L48
            java.util.Enumeration r0 = r0.getObjects()
        L1a:
            boolean r1 = r0.hasMoreElements()
            if (r1 == 0) goto L48
            java.lang.Object r1 = r0.nextElement()     // Catch: java.lang.IllegalArgumentException -> L3f
            org.spongycastle.asn1.ASN1TaggedObject r1 = org.spongycastle.asn1.ASN1TaggedObject.getInstance(r1)     // Catch: java.lang.IllegalArgumentException -> L3f
            int r2 = r1.getTagNo()     // Catch: java.lang.IllegalArgumentException -> L3f
            r3 = 1
            if (r2 != r3) goto L1a
            r0 = 0
            org.spongycastle.asn1.ASN1Integer r0 = org.spongycastle.asn1.ASN1Integer.getInstance(r1, r0)     // Catch: java.lang.IllegalArgumentException -> L3f
            java.math.BigInteger r0 = r0.getValue()     // Catch: java.lang.IllegalArgumentException -> L3f
            int r4 = r0.intValue()     // Catch: java.lang.IllegalArgumentException -> L3f
            if (r4 >= r6) goto L48
            return r4
        L3f:
            r6 = move-exception
            org.spongycastle.jce.exception.ExtCertPathValidatorException r0 = new org.spongycastle.jce.exception.ExtCertPathValidatorException
            java.lang.String r1 = "Policy constraints extension contents cannot be decoded."
            r0.<init>(r1, r6, r4, r5)
            throw r0
        L48:
            return r6
        L49:
            r6 = move-exception
            org.spongycastle.jce.exception.ExtCertPathValidatorException r0 = new org.spongycastle.jce.exception.ExtCertPathValidatorException
            java.lang.String r1 = "Policy constraints extension cannot be decoded."
            r0.<init>(r1, r6, r4, r5)
            goto L53
        L52:
            throw r0
        L53:
            goto L52
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.jce.provider.RFC3280CertPathUtilities.prepareNextCertI2(java.security.cert.CertPath, int, int):int");
    }

    public static int prepareNextCertJ(CertPath certPath, int i2, int i3) throws ExtCertPathValidatorException {
        int iIntValue;
        try {
            ASN1Integer aSN1Integer = ASN1Integer.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(i2), INHIBIT_ANY_POLICY));
            return (aSN1Integer == null || (iIntValue = aSN1Integer.getValue().intValue()) >= i3) ? i3 : iIntValue;
        } catch (Exception e2) {
            throw new ExtCertPathValidatorException("Inhibit any-policy extension cannot be decoded.", e2, certPath, i2);
        }
    }

    public static void prepareNextCertK(CertPath certPath, int i2) throws CertPathValidatorException {
        try {
            BasicConstraints basicConstraints = BasicConstraints.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(i2), BASIC_CONSTRAINTS));
            if (basicConstraints == null) {
                throw new CertPathValidatorException("Intermediate certificate lacks BasicConstraints");
            }
            if (!basicConstraints.isCA()) {
                throw new CertPathValidatorException("Not a CA certificate");
            }
        } catch (Exception e2) {
            throw new ExtCertPathValidatorException("Basic constraints extension cannot be decoded.", e2, certPath, i2);
        }
    }

    public static int prepareNextCertL(CertPath certPath, int i2, int i3) throws ExtCertPathValidatorException {
        if (CertPathValidatorUtilities.isSelfIssued((X509Certificate) certPath.getCertificates().get(i2))) {
            return i3;
        }
        if (i3 > 0) {
            return i3 - 1;
        }
        throw new ExtCertPathValidatorException("Max path length not greater than zero", null, certPath, i2);
    }

    public static int prepareNextCertM(CertPath certPath, int i2, int i3) throws ExtCertPathValidatorException {
        BigInteger pathLenConstraint;
        int iIntValue;
        try {
            BasicConstraints basicConstraints = BasicConstraints.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(i2), BASIC_CONSTRAINTS));
            return (basicConstraints == null || (pathLenConstraint = basicConstraints.getPathLenConstraint()) == null || (iIntValue = pathLenConstraint.intValue()) >= i3) ? i3 : iIntValue;
        } catch (Exception e2) {
            throw new ExtCertPathValidatorException("Basic constraints extension cannot be decoded.", e2, certPath, i2);
        }
    }

    public static void prepareNextCertN(CertPath certPath, int i2) throws ExtCertPathValidatorException {
        boolean[] keyUsage = ((X509Certificate) certPath.getCertificates().get(i2)).getKeyUsage();
        if (keyUsage != null && !keyUsage[5]) {
            throw new ExtCertPathValidatorException("Issuer certificate keyusage extension is critical and does not permit key signing.", null, certPath, i2);
        }
    }

    public static void prepareNextCertO(CertPath certPath, int i2, Set set, List list) throws CertPathValidatorException {
        X509Certificate x509Certificate = (X509Certificate) certPath.getCertificates().get(i2);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            try {
                ((PKIXCertPathChecker) it.next()).check(x509Certificate, set);
            } catch (CertPathValidatorException e2) {
                throw new CertPathValidatorException(e2.getMessage(), e2.getCause(), certPath, i2);
            }
        }
        if (set.isEmpty()) {
            return;
        }
        throw new ExtCertPathValidatorException("Certificate has unsupported critical extension: " + set, null, certPath, i2);
    }

    public static Set processCRLA1i(Date date, PKIXExtendedParameters pKIXExtendedParameters, X509Certificate x509Certificate, X509CRL x509crl) throws AnnotatedException {
        HashSet hashSet = new HashSet();
        if (pKIXExtendedParameters.isUseDeltasEnabled()) {
            try {
                String str = FRESHEST_CRL;
                CRLDistPoint cRLDistPoint = CRLDistPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(x509Certificate, str));
                if (cRLDistPoint == null) {
                    try {
                        cRLDistPoint = CRLDistPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(x509crl, str));
                    } catch (AnnotatedException e2) {
                        throw new AnnotatedException("Freshest CRL extension could not be decoded from CRL.", e2);
                    }
                }
                if (cRLDistPoint != null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.addAll(pKIXExtendedParameters.getCRLStores());
                    try {
                        arrayList.addAll(CertPathValidatorUtilities.getAdditionalStoresFromCRLDistributionPoint(cRLDistPoint, pKIXExtendedParameters.getNamedCRLStoreMap()));
                        try {
                            hashSet.addAll(CertPathValidatorUtilities.getDeltaCRLs(date, x509crl, pKIXExtendedParameters.getCertStores(), arrayList));
                        } catch (AnnotatedException e3) {
                            throw new AnnotatedException("Exception obtaining delta CRLs.", e3);
                        }
                    } catch (AnnotatedException e4) {
                        throw new AnnotatedException("No new delta CRL locations could be added from Freshest CRL extension.", e4);
                    }
                }
            } catch (AnnotatedException e5) {
                throw new AnnotatedException("Freshest CRL extension could not be decoded from certificate.", e5);
            }
        }
        return hashSet;
    }

    public static Set[] processCRLA1ii(Date date, PKIXExtendedParameters pKIXExtendedParameters, X509Certificate x509Certificate, X509CRL x509crl) throws AnnotatedException {
        HashSet hashSet = new HashSet();
        X509CRLSelector x509CRLSelector = new X509CRLSelector();
        x509CRLSelector.setCertificateChecking(x509Certificate);
        try {
            x509CRLSelector.addIssuerName(PrincipalUtils.getIssuerPrincipal(x509crl).getEncoded());
            PKIXCRLStoreSelector<? extends CRL> pKIXCRLStoreSelectorBuild = new PKIXCRLStoreSelector.Builder(x509CRLSelector).setCompleteCRLEnabled(true).build();
            if (pKIXExtendedParameters.getDate() != null) {
                date = pKIXExtendedParameters.getDate();
            }
            Set setFindCRLs = CRL_UTIL.findCRLs(pKIXCRLStoreSelectorBuild, date, pKIXExtendedParameters.getCertStores(), pKIXExtendedParameters.getCRLStores());
            if (pKIXExtendedParameters.isUseDeltasEnabled()) {
                try {
                    hashSet.addAll(CertPathValidatorUtilities.getDeltaCRLs(date, x509crl, pKIXExtendedParameters.getCertStores(), pKIXExtendedParameters.getCRLStores()));
                } catch (AnnotatedException e2) {
                    throw new AnnotatedException("Exception obtaining delta CRLs.", e2);
                }
            }
            return new Set[]{setFindCRLs, hashSet};
        } catch (IOException e3) {
            throw new AnnotatedException("Cannot extract issuer from CRL." + e3, e3);
        }
    }

    public static void processCRLB1(DistributionPoint distributionPoint, Object obj, X509CRL x509crl) throws AnnotatedException {
        ASN1Primitive extensionValue = CertPathValidatorUtilities.getExtensionValue(x509crl, ISSUING_DISTRIBUTION_POINT);
        boolean z2 = true;
        boolean z3 = extensionValue != null && IssuingDistributionPoint.getInstance(extensionValue).isIndirectCRL();
        try {
            byte[] encoded = PrincipalUtils.getIssuerPrincipal(x509crl).getEncoded();
            if (distributionPoint.getCRLIssuer() != null) {
                GeneralName[] names = distributionPoint.getCRLIssuer().getNames();
                boolean z4 = false;
                for (int i2 = 0; i2 < names.length; i2++) {
                    if (names[i2].getTagNo() == 4) {
                        try {
                            if (Arrays.areEqual(names[i2].getName().toASN1Primitive().getEncoded(), encoded)) {
                                z4 = true;
                            }
                        } catch (IOException e2) {
                            throw new AnnotatedException("CRL issuer information from distribution point cannot be decoded.", e2);
                        }
                    }
                }
                if (z4 && !z3) {
                    throw new AnnotatedException("Distribution point contains cRLIssuer field but CRL is not indirect.");
                }
                if (!z4) {
                    throw new AnnotatedException("CRL issuer of CRL does not match CRL issuer of distribution point.");
                }
                z2 = z4;
            } else if (!PrincipalUtils.getIssuerPrincipal(x509crl).equals(PrincipalUtils.getEncodedIssuerPrincipal(obj))) {
                z2 = false;
            }
            if (!z2) {
                throw new AnnotatedException("Cannot find matching CRL issuer for certificate.");
            }
        } catch (IOException e3) {
            throw new AnnotatedException("Exception encoding CRL issuer: " + e3.getMessage(), e3);
        }
    }

    public static void processCRLB2(DistributionPoint distributionPoint, Object obj, X509CRL x509crl) throws AnnotatedException {
        int i2;
        GeneralName[] names;
        try {
            IssuingDistributionPoint issuingDistributionPoint = IssuingDistributionPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(x509crl, ISSUING_DISTRIBUTION_POINT));
            if (issuingDistributionPoint != null) {
                if (issuingDistributionPoint.getDistributionPoint() != null) {
                    DistributionPointName distributionPoint2 = IssuingDistributionPoint.getInstance(issuingDistributionPoint).getDistributionPoint();
                    ArrayList arrayList = new ArrayList();
                    if (distributionPoint2.getType() == 0) {
                        for (GeneralName generalName : GeneralNames.getInstance(distributionPoint2.getName()).getNames()) {
                            arrayList.add(generalName);
                        }
                    }
                    if (distributionPoint2.getType() == 1) {
                        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                        try {
                            Enumeration objects = ASN1Sequence.getInstance(PrincipalUtils.getIssuerPrincipal(x509crl)).getObjects();
                            while (objects.hasMoreElements()) {
                                aSN1EncodableVector.add((ASN1Encodable) objects.nextElement());
                            }
                            aSN1EncodableVector.add(distributionPoint2.getName());
                            arrayList.add(new GeneralName(X500Name.getInstance(new DERSequence(aSN1EncodableVector))));
                        } catch (Exception e2) {
                            throw new AnnotatedException("Could not read CRL issuer.", e2);
                        }
                    }
                    if (distributionPoint.getDistributionPoint() == null) {
                        if (distributionPoint.getCRLIssuer() == null) {
                            throw new AnnotatedException("Either the cRLIssuer or the distributionPoint field must be contained in DistributionPoint.");
                        }
                        GeneralName[] names2 = distributionPoint.getCRLIssuer().getNames();
                        while (i2 < names2.length) {
                            i2 = arrayList.contains(names2[i2]) ? 0 : i2 + 1;
                        }
                        throw new AnnotatedException("No match for certificate CRL issuing distribution point name to cRLIssuer CRL distribution point.");
                    }
                    DistributionPointName distributionPoint3 = distributionPoint.getDistributionPoint();
                    GeneralName[] names3 = distributionPoint3.getType() == 0 ? GeneralNames.getInstance(distributionPoint3.getName()).getNames() : null;
                    if (distributionPoint3.getType() == 1) {
                        if (distributionPoint.getCRLIssuer() != null) {
                            names = distributionPoint.getCRLIssuer().getNames();
                        } else {
                            names = new GeneralName[1];
                            try {
                                names[0] = new GeneralName(X500Name.getInstance(PrincipalUtils.getEncodedIssuerPrincipal(obj).getEncoded()));
                            } catch (Exception e3) {
                                throw new AnnotatedException("Could not read certificate issuer.", e3);
                            }
                        }
                        names3 = names;
                        for (int i3 = 0; i3 < names3.length; i3++) {
                            Enumeration objects2 = ASN1Sequence.getInstance(names3[i3].getName().toASN1Primitive()).getObjects();
                            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
                            while (objects2.hasMoreElements()) {
                                aSN1EncodableVector2.add((ASN1Encodable) objects2.nextElement());
                            }
                            aSN1EncodableVector2.add(distributionPoint3.getName());
                            names3[i3] = new GeneralName(X500Name.getInstance(new DERSequence(aSN1EncodableVector2)));
                        }
                    }
                    if (names3 != null) {
                        while (i2 < names3.length) {
                            i2 = arrayList.contains(names3[i2]) ? 0 : i2 + 1;
                        }
                    }
                    throw new AnnotatedException("No match for certificate CRL issuing distribution point name to cRLIssuer CRL distribution point.");
                }
                try {
                    BasicConstraints basicConstraints = BasicConstraints.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Extension) obj, BASIC_CONSTRAINTS));
                    if (obj instanceof X509Certificate) {
                        if (issuingDistributionPoint.onlyContainsUserCerts() && basicConstraints != null && basicConstraints.isCA()) {
                            throw new AnnotatedException("CA Cert CRL only contains user certificates.");
                        }
                        if (issuingDistributionPoint.onlyContainsCACerts() && (basicConstraints == null || !basicConstraints.isCA())) {
                            throw new AnnotatedException("End CRL only contains CA certificates.");
                        }
                    }
                    if (issuingDistributionPoint.onlyContainsAttributeCerts()) {
                        throw new AnnotatedException("onlyContainsAttributeCerts boolean is asserted.");
                    }
                } catch (Exception e4) {
                    throw new AnnotatedException("Basic constraints extension could not be decoded.", e4);
                }
            }
        } catch (Exception e5) {
            throw new AnnotatedException("Issuing distribution point extension could not be decoded.", e5);
        }
    }

    public static void processCRLC(X509CRL x509crl, X509CRL x509crl2, PKIXExtendedParameters pKIXExtendedParameters) throws AnnotatedException {
        if (x509crl == null) {
            return;
        }
        try {
            String str = ISSUING_DISTRIBUTION_POINT;
            IssuingDistributionPoint issuingDistributionPoint = IssuingDistributionPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(x509crl2, str));
            if (pKIXExtendedParameters.isUseDeltasEnabled()) {
                if (!PrincipalUtils.getIssuerPrincipal(x509crl).equals(PrincipalUtils.getIssuerPrincipal(x509crl2))) {
                    throw new AnnotatedException("Complete CRL issuer does not match delta CRL issuer.");
                }
                try {
                    IssuingDistributionPoint issuingDistributionPoint2 = IssuingDistributionPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(x509crl, str));
                    if (issuingDistributionPoint != null ? !issuingDistributionPoint.equals(issuingDistributionPoint2) : issuingDistributionPoint2 != null) {
                        throw new AnnotatedException("Issuing distribution point extension from delta CRL and complete CRL does not match.");
                    }
                    try {
                        String str2 = AUTHORITY_KEY_IDENTIFIER;
                        ASN1Primitive extensionValue = CertPathValidatorUtilities.getExtensionValue(x509crl2, str2);
                        try {
                            ASN1Primitive extensionValue2 = CertPathValidatorUtilities.getExtensionValue(x509crl, str2);
                            if (extensionValue == null) {
                                throw new AnnotatedException("CRL authority key identifier is null.");
                            }
                            if (extensionValue2 == null) {
                                throw new AnnotatedException("Delta CRL authority key identifier is null.");
                            }
                            if (!extensionValue.equals(extensionValue2)) {
                                throw new AnnotatedException("Delta CRL authority key identifier does not match complete CRL authority key identifier.");
                            }
                        } catch (AnnotatedException e2) {
                            throw new AnnotatedException("Authority key identifier extension could not be extracted from delta CRL.", e2);
                        }
                    } catch (AnnotatedException e3) {
                        throw new AnnotatedException("Authority key identifier extension could not be extracted from complete CRL.", e3);
                    }
                } catch (Exception e4) {
                    throw new AnnotatedException("Issuing distribution point extension from delta CRL could not be decoded.", e4);
                }
            }
        } catch (Exception e5) {
            throw new AnnotatedException("Issuing distribution point extension could not be decoded.", e5);
        }
    }

    public static ReasonsMask processCRLD(X509CRL x509crl, DistributionPoint distributionPoint) throws AnnotatedException {
        try {
            IssuingDistributionPoint issuingDistributionPoint = IssuingDistributionPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(x509crl, ISSUING_DISTRIBUTION_POINT));
            if (issuingDistributionPoint != null && issuingDistributionPoint.getOnlySomeReasons() != null && distributionPoint.getReasons() != null) {
                return new ReasonsMask(distributionPoint.getReasons()).intersect(new ReasonsMask(issuingDistributionPoint.getOnlySomeReasons()));
            }
            if ((issuingDistributionPoint == null || issuingDistributionPoint.getOnlySomeReasons() == null) && distributionPoint.getReasons() == null) {
                return ReasonsMask.allReasons;
            }
            return (distributionPoint.getReasons() == null ? ReasonsMask.allReasons : new ReasonsMask(distributionPoint.getReasons())).intersect(issuingDistributionPoint == null ? ReasonsMask.allReasons : new ReasonsMask(issuingDistributionPoint.getOnlySomeReasons()));
        } catch (Exception e2) {
            throw new AnnotatedException("Issuing distribution point extension could not be decoded.", e2);
        }
    }

    public static Set processCRLF(X509CRL x509crl, Object obj, X509Certificate x509Certificate, PublicKey publicKey, PKIXExtendedParameters pKIXExtendedParameters, List list, JcaJceHelper jcaJceHelper) throws AnnotatedException {
        int i2;
        X509CertSelector x509CertSelector = new X509CertSelector();
        try {
            x509CertSelector.setSubject(PrincipalUtils.getIssuerPrincipal(x509crl).getEncoded());
            PKIXCertStoreSelector<? extends Certificate> pKIXCertStoreSelectorBuild = new PKIXCertStoreSelector.Builder(x509CertSelector).build();
            try {
                Collection collectionFindCertificates = CertPathValidatorUtilities.findCertificates(pKIXCertStoreSelectorBuild, pKIXExtendedParameters.getCertificateStores());
                collectionFindCertificates.addAll(CertPathValidatorUtilities.findCertificates(pKIXCertStoreSelectorBuild, pKIXExtendedParameters.getCertStores()));
                collectionFindCertificates.add(x509Certificate);
                Iterator it = collectionFindCertificates.iterator();
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    X509Certificate x509Certificate2 = (X509Certificate) it.next();
                    if (x509Certificate2.equals(x509Certificate)) {
                        arrayList.add(x509Certificate2);
                        arrayList2.add(publicKey);
                    } else {
                        try {
                            PKIXCertPathBuilderSpi pKIXCertPathBuilderSpi = new PKIXCertPathBuilderSpi();
                            X509CertSelector x509CertSelector2 = new X509CertSelector();
                            x509CertSelector2.setCertificate(x509Certificate2);
                            PKIXExtendedParameters.Builder targetConstraints = new PKIXExtendedParameters.Builder(pKIXExtendedParameters).setTargetConstraints(new PKIXCertStoreSelector.Builder(x509CertSelector2).build());
                            if (list.contains(x509Certificate2)) {
                                targetConstraints.setRevocationEnabled(false);
                            } else {
                                targetConstraints.setRevocationEnabled(true);
                            }
                            List<? extends Certificate> certificates = pKIXCertPathBuilderSpi.engineBuild(new PKIXExtendedBuilderParameters.Builder(targetConstraints.build()).build()).getCertPath().getCertificates();
                            arrayList.add(x509Certificate2);
                            arrayList2.add(CertPathValidatorUtilities.getNextWorkingKey(certificates, 0, jcaJceHelper));
                        } catch (CertPathBuilderException e2) {
                            throw new AnnotatedException("CertPath for CRL signer failed to validate.", e2);
                        } catch (CertPathValidatorException e3) {
                            throw new AnnotatedException("Public key of issuer certificate of CRL could not be retrieved.", e3);
                        } catch (Exception e4) {
                            throw new AnnotatedException(e4.getMessage());
                        }
                    }
                }
                HashSet hashSet = new HashSet();
                AnnotatedException annotatedException = null;
                for (i2 = 0; i2 < arrayList.size(); i2++) {
                    boolean[] keyUsage = ((X509Certificate) arrayList.get(i2)).getKeyUsage();
                    if (keyUsage == null || (keyUsage.length >= 7 && keyUsage[6])) {
                        hashSet.add(arrayList2.get(i2));
                    } else {
                        annotatedException = new AnnotatedException("Issuer certificate key usage extension does not permit CRL signing.");
                    }
                }
                if (hashSet.isEmpty() && annotatedException == null) {
                    throw new AnnotatedException("Cannot find a valid issuer certificate.");
                }
                if (!hashSet.isEmpty() || annotatedException == null) {
                    return hashSet;
                }
                throw annotatedException;
            } catch (AnnotatedException e5) {
                throw new AnnotatedException("Issuer certificate for CRL cannot be searched.", e5);
            }
        } catch (IOException e6) {
            throw new AnnotatedException("Subject criteria for certificate selector to find issuer certificate for CRL could not be set.", e6);
        }
    }

    public static PublicKey processCRLG(X509CRL x509crl, Set set) throws AnnotatedException {
        Iterator it = set.iterator();
        Exception e2 = null;
        while (it.hasNext()) {
            PublicKey publicKey = (PublicKey) it.next();
            try {
                x509crl.verify(publicKey);
                return publicKey;
            } catch (Exception e3) {
                e2 = e3;
            }
        }
        throw new AnnotatedException("Cannot verify CRL.", e2);
    }

    public static X509CRL processCRLH(Set set, PublicKey publicKey) throws AnnotatedException {
        Iterator it = set.iterator();
        Exception e2 = null;
        while (it.hasNext()) {
            X509CRL x509crl = (X509CRL) it.next();
            try {
                x509crl.verify(publicKey);
                return x509crl;
            } catch (Exception e3) {
                e2 = e3;
            }
        }
        if (e2 == null) {
            return null;
        }
        throw new AnnotatedException("Cannot verify delta CRL.", e2);
    }

    public static void processCRLI(Date date, X509CRL x509crl, Object obj, CertStatus certStatus, PKIXExtendedParameters pKIXExtendedParameters) {
        if (!pKIXExtendedParameters.isUseDeltasEnabled() || x509crl == null) {
            return;
        }
        CertPathValidatorUtilities.getCertStatus(date, x509crl, obj, certStatus);
    }

    public static void processCRLJ(Date date, X509CRL x509crl, Object obj, CertStatus certStatus) {
        if (certStatus.getCertStatus() == 11) {
            CertPathValidatorUtilities.getCertStatus(date, x509crl, obj, certStatus);
        }
    }

    public static void processCertA(CertPath certPath, PKIXExtendedParameters pKIXExtendedParameters, int i2, PublicKey publicKey, boolean z2, X500Name x500Name, X509Certificate x509Certificate, JcaJceHelper jcaJceHelper) throws ExtCertPathValidatorException {
        List<? extends Certificate> certificates = certPath.getCertificates();
        X509Certificate x509Certificate2 = (X509Certificate) certificates.get(i2);
        if (!z2) {
            try {
                CertPathValidatorUtilities.verifyX509Certificate(x509Certificate2, publicKey, pKIXExtendedParameters.getSigProvider());
            } catch (GeneralSecurityException e2) {
                throw new ExtCertPathValidatorException("Could not validate certificate signature.", e2, certPath, i2);
            }
        }
        try {
            x509Certificate2.checkValidity(CertPathValidatorUtilities.getValidCertDateFromValidityModel(pKIXExtendedParameters, certPath, i2));
            if (pKIXExtendedParameters.isRevocationEnabled()) {
                try {
                    checkCRLs(pKIXExtendedParameters, x509Certificate2, CertPathValidatorUtilities.getValidCertDateFromValidityModel(pKIXExtendedParameters, certPath, i2), x509Certificate, publicKey, certificates, jcaJceHelper);
                } catch (AnnotatedException e3) {
                    throw new ExtCertPathValidatorException(e3.getMessage(), e3.getCause() != null ? e3.getCause() : e3, certPath, i2);
                }
            }
            if (PrincipalUtils.getEncodedIssuerPrincipal(x509Certificate2).equals(x500Name)) {
                return;
            }
            throw new ExtCertPathValidatorException("IssuerName(" + PrincipalUtils.getEncodedIssuerPrincipal(x509Certificate2) + ") does not match SubjectName(" + x500Name + ") of signing certificate.", null, certPath, i2);
        } catch (CertificateExpiredException e4) {
            throw new ExtCertPathValidatorException("Could not validate certificate: " + e4.getMessage(), e4, certPath, i2);
        } catch (CertificateNotYetValidException e5) {
            throw new ExtCertPathValidatorException("Could not validate certificate: " + e5.getMessage(), e5, certPath, i2);
        } catch (AnnotatedException e6) {
            throw new ExtCertPathValidatorException("Could not validate time of certificate.", e6, certPath, i2);
        }
    }

    public static void processCertBC(CertPath certPath, int i2, PKIXNameConstraintValidator pKIXNameConstraintValidator) throws CertPathValidatorException {
        List<? extends Certificate> certificates = certPath.getCertificates();
        X509Certificate x509Certificate = (X509Certificate) certificates.get(i2);
        int size = certificates.size();
        int i3 = size - i2;
        if (!CertPathValidatorUtilities.isSelfIssued(x509Certificate) || i3 >= size) {
            try {
                ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(PrincipalUtils.getSubjectPrincipal(x509Certificate).getEncoded());
                try {
                    pKIXNameConstraintValidator.checkPermittedDN(aSN1Sequence);
                    pKIXNameConstraintValidator.checkExcludedDN(aSN1Sequence);
                    try {
                        GeneralNames generalNames = GeneralNames.getInstance(CertPathValidatorUtilities.getExtensionValue(x509Certificate, SUBJECT_ALTERNATIVE_NAME));
                        RDN[] rDNs = X500Name.getInstance(aSN1Sequence).getRDNs(BCStyle.EmailAddress);
                        for (int i4 = 0; i4 != rDNs.length; i4++) {
                            GeneralName generalName = new GeneralName(1, ((ASN1String) rDNs[i4].getFirst().getValue()).getString());
                            try {
                                pKIXNameConstraintValidator.checkPermitted(generalName);
                                pKIXNameConstraintValidator.checkExcluded(generalName);
                            } catch (PKIXNameConstraintValidatorException e2) {
                                throw new CertPathValidatorException("Subtree check for certificate subject alternative email failed.", e2, certPath, i2);
                            }
                        }
                        if (generalNames != null) {
                            try {
                                GeneralName[] names = generalNames.getNames();
                                for (int i5 = 0; i5 < names.length; i5++) {
                                    try {
                                        pKIXNameConstraintValidator.checkPermitted(names[i5]);
                                        pKIXNameConstraintValidator.checkExcluded(names[i5]);
                                    } catch (PKIXNameConstraintValidatorException e3) {
                                        throw new CertPathValidatorException("Subtree check for certificate subject alternative name failed.", e3, certPath, i2);
                                    }
                                }
                            } catch (Exception e4) {
                                throw new CertPathValidatorException("Subject alternative name contents could not be decoded.", e4, certPath, i2);
                            }
                        }
                    } catch (Exception e5) {
                        throw new CertPathValidatorException("Subject alternative name extension could not be decoded.", e5, certPath, i2);
                    }
                } catch (PKIXNameConstraintValidatorException e6) {
                    throw new CertPathValidatorException("Subtree check for certificate subject failed.", e6, certPath, i2);
                }
            } catch (Exception e7) {
                throw new CertPathValidatorException("Exception extracting subject name when checking subtrees.", e7, certPath, i2);
            }
        }
    }

    public static PKIXPolicyNode processCertD(CertPath certPath, int i2, Set set, PKIXPolicyNode pKIXPolicyNode, List[] listArr, int i3) throws ExtCertPathValidatorException {
        String id;
        int i4;
        List<? extends Certificate> certificates = certPath.getCertificates();
        X509Certificate x509Certificate = (X509Certificate) certificates.get(i2);
        int size = certificates.size();
        int i5 = size - i2;
        try {
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(CertPathValidatorUtilities.getExtensionValue(x509Certificate, CERTIFICATE_POLICIES));
            if (aSN1Sequence == null || pKIXPolicyNode == null) {
                return null;
            }
            Enumeration objects = aSN1Sequence.getObjects();
            HashSet hashSet = new HashSet();
            while (objects.hasMoreElements()) {
                PolicyInformation policyInformation = PolicyInformation.getInstance(objects.nextElement());
                ASN1ObjectIdentifier policyIdentifier = policyInformation.getPolicyIdentifier();
                hashSet.add(policyIdentifier.getId());
                if (!ANY_POLICY.equals(policyIdentifier.getId())) {
                    try {
                        Set qualifierSet = CertPathValidatorUtilities.getQualifierSet(policyInformation.getPolicyQualifiers());
                        if (!CertPathValidatorUtilities.processCertD1i(i5, listArr, policyIdentifier, qualifierSet)) {
                            CertPathValidatorUtilities.processCertD1ii(i5, listArr, policyIdentifier, qualifierSet);
                        }
                    } catch (CertPathValidatorException e2) {
                        throw new ExtCertPathValidatorException("Policy qualifier info set could not be build.", e2, certPath, i2);
                    }
                }
            }
            if (set.isEmpty() || set.contains(ANY_POLICY)) {
                set.clear();
                set.addAll(hashSet);
            } else {
                HashSet hashSet2 = new HashSet();
                for (Object obj : set) {
                    if (hashSet.contains(obj)) {
                        hashSet2.add(obj);
                    }
                }
                set.clear();
                set.addAll(hashSet2);
            }
            if (i3 > 0 || (i5 < size && CertPathValidatorUtilities.isSelfIssued(x509Certificate))) {
                Enumeration objects2 = aSN1Sequence.getObjects();
                while (true) {
                    if (!objects2.hasMoreElements()) {
                        break;
                    }
                    PolicyInformation policyInformation2 = PolicyInformation.getInstance(objects2.nextElement());
                    if (ANY_POLICY.equals(policyInformation2.getPolicyIdentifier().getId())) {
                        Set qualifierSet2 = CertPathValidatorUtilities.getQualifierSet(policyInformation2.getPolicyQualifiers());
                        List list = listArr[i5 - 1];
                        for (int i6 = 0; i6 < list.size(); i6++) {
                            PKIXPolicyNode pKIXPolicyNode2 = (PKIXPolicyNode) list.get(i6);
                            for (Object obj2 : pKIXPolicyNode2.getExpectedPolicies()) {
                                if (obj2 instanceof String) {
                                    id = (String) obj2;
                                } else if (obj2 instanceof ASN1ObjectIdentifier) {
                                    id = ((ASN1ObjectIdentifier) obj2).getId();
                                }
                                String str = id;
                                Iterator children = pKIXPolicyNode2.getChildren();
                                boolean z2 = false;
                                while (children.hasNext()) {
                                    if (str.equals(((PKIXPolicyNode) children.next()).getValidPolicy())) {
                                        z2 = true;
                                    }
                                }
                                if (!z2) {
                                    HashSet hashSet3 = new HashSet();
                                    hashSet3.add(str);
                                    PKIXPolicyNode pKIXPolicyNode3 = new PKIXPolicyNode(new ArrayList(), i5, hashSet3, pKIXPolicyNode2, qualifierSet2, str, false);
                                    pKIXPolicyNode2.addChild(pKIXPolicyNode3);
                                    listArr[i5].add(pKIXPolicyNode3);
                                }
                            }
                        }
                    }
                }
            }
            PKIXPolicyNode pKIXPolicyNodeRemovePolicyNode = pKIXPolicyNode;
            for (int i7 = i5 - 1; i7 >= 0; i7--) {
                List list2 = listArr[i7];
                while (i4 < list2.size()) {
                    PKIXPolicyNode pKIXPolicyNode4 = (PKIXPolicyNode) list2.get(i4);
                    i4 = (pKIXPolicyNode4.hasChildren() || (pKIXPolicyNodeRemovePolicyNode = CertPathValidatorUtilities.removePolicyNode(pKIXPolicyNodeRemovePolicyNode, listArr, pKIXPolicyNode4)) != null) ? i4 + 1 : 0;
                }
            }
            Set<String> criticalExtensionOIDs = x509Certificate.getCriticalExtensionOIDs();
            if (criticalExtensionOIDs != null) {
                boolean zContains = criticalExtensionOIDs.contains(CERTIFICATE_POLICIES);
                List list3 = listArr[i5];
                for (int i8 = 0; i8 < list3.size(); i8++) {
                    ((PKIXPolicyNode) list3.get(i8)).setCritical(zContains);
                }
            }
            return pKIXPolicyNodeRemovePolicyNode;
        } catch (AnnotatedException e3) {
            throw new ExtCertPathValidatorException("Could not read certificate policies extension from certificate.", e3, certPath, i2);
        }
    }

    public static PKIXPolicyNode processCertE(CertPath certPath, int i2, PKIXPolicyNode pKIXPolicyNode) throws ExtCertPathValidatorException {
        try {
            if (ASN1Sequence.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(i2), CERTIFICATE_POLICIES)) == null) {
                return null;
            }
            return pKIXPolicyNode;
        } catch (AnnotatedException e2) {
            throw new ExtCertPathValidatorException("Could not read certificate policies extension from certificate.", e2, certPath, i2);
        }
    }

    public static void processCertF(CertPath certPath, int i2, PKIXPolicyNode pKIXPolicyNode, int i3) throws ExtCertPathValidatorException {
        if (i3 <= 0 && pKIXPolicyNode == null) {
            throw new ExtCertPathValidatorException("No valid policy tree found when one expected.", null, certPath, i2);
        }
    }

    public static int wrapupCertA(int i2, X509Certificate x509Certificate) {
        return (CertPathValidatorUtilities.isSelfIssued(x509Certificate) || i2 == 0) ? i2 : i2 - 1;
    }

    public static int wrapupCertB(CertPath certPath, int i2, int i3) throws ExtCertPathValidatorException {
        try {
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(i2), POLICY_CONSTRAINTS));
            if (aSN1Sequence != null) {
                Enumeration objects = aSN1Sequence.getObjects();
                while (objects.hasMoreElements()) {
                    ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objects.nextElement();
                    if (aSN1TaggedObject.getTagNo() == 0) {
                        try {
                            if (ASN1Integer.getInstance(aSN1TaggedObject, false).getValue().intValue() == 0) {
                                return 0;
                            }
                        } catch (Exception e2) {
                            throw new ExtCertPathValidatorException("Policy constraints requireExplicitPolicy field could not be decoded.", e2, certPath, i2);
                        }
                    }
                }
            }
            return i3;
        } catch (AnnotatedException e3) {
            throw new ExtCertPathValidatorException("Policy constraints could not be decoded.", e3, certPath, i2);
        }
    }

    public static void wrapupCertF(CertPath certPath, int i2, List list, Set set) throws ExtCertPathValidatorException {
        X509Certificate x509Certificate = (X509Certificate) certPath.getCertificates().get(i2);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            try {
                ((PKIXCertPathChecker) it.next()).check(x509Certificate, set);
            } catch (CertPathValidatorException e2) {
                throw new ExtCertPathValidatorException("Additional certificate path checker failed.", e2, certPath, i2);
            }
        }
        if (set.isEmpty()) {
            return;
        }
        throw new ExtCertPathValidatorException("Certificate has unsupported critical extension: " + set, null, certPath, i2);
    }

    public static PKIXPolicyNode wrapupCertG(CertPath certPath, PKIXExtendedParameters pKIXExtendedParameters, Set set, int i2, List[] listArr, PKIXPolicyNode pKIXPolicyNode, Set set2) throws ExtCertPathValidatorException {
        int size = certPath.getCertificates().size();
        if (pKIXPolicyNode == null) {
            if (pKIXExtendedParameters.isExplicitPolicyRequired()) {
                throw new ExtCertPathValidatorException("Explicit policy requested but none available.", null, certPath, i2);
            }
            return null;
        }
        if (!CertPathValidatorUtilities.isAnyPolicy(set)) {
            HashSet<PKIXPolicyNode> hashSet = new HashSet();
            for (List list : listArr) {
                for (int i3 = 0; i3 < list.size(); i3++) {
                    PKIXPolicyNode pKIXPolicyNode2 = (PKIXPolicyNode) list.get(i3);
                    if (ANY_POLICY.equals(pKIXPolicyNode2.getValidPolicy())) {
                        Iterator children = pKIXPolicyNode2.getChildren();
                        while (children.hasNext()) {
                            PKIXPolicyNode pKIXPolicyNode3 = (PKIXPolicyNode) children.next();
                            if (!ANY_POLICY.equals(pKIXPolicyNode3.getValidPolicy())) {
                                hashSet.add(pKIXPolicyNode3);
                            }
                        }
                    }
                }
            }
            for (PKIXPolicyNode pKIXPolicyNode4 : hashSet) {
                if (!set.contains(pKIXPolicyNode4.getValidPolicy())) {
                    pKIXPolicyNode = CertPathValidatorUtilities.removePolicyNode(pKIXPolicyNode, listArr, pKIXPolicyNode4);
                }
            }
            if (pKIXPolicyNode != null) {
                for (int i4 = size - 1; i4 >= 0; i4--) {
                    List list2 = listArr[i4];
                    for (int i5 = 0; i5 < list2.size(); i5++) {
                        PKIXPolicyNode pKIXPolicyNode5 = (PKIXPolicyNode) list2.get(i5);
                        if (!pKIXPolicyNode5.hasChildren()) {
                            pKIXPolicyNode = CertPathValidatorUtilities.removePolicyNode(pKIXPolicyNode, listArr, pKIXPolicyNode5);
                        }
                    }
                }
            }
        } else if (pKIXExtendedParameters.isExplicitPolicyRequired()) {
            if (set2.isEmpty()) {
                throw new ExtCertPathValidatorException("Explicit policy requested but none available.", null, certPath, i2);
            }
            HashSet hashSet2 = new HashSet();
            for (List list3 : listArr) {
                for (int i6 = 0; i6 < list3.size(); i6++) {
                    PKIXPolicyNode pKIXPolicyNode6 = (PKIXPolicyNode) list3.get(i6);
                    if (ANY_POLICY.equals(pKIXPolicyNode6.getValidPolicy())) {
                        Iterator children2 = pKIXPolicyNode6.getChildren();
                        while (children2.hasNext()) {
                            hashSet2.add(children2.next());
                        }
                    }
                }
            }
            Iterator it = hashSet2.iterator();
            while (it.hasNext()) {
                set2.contains(((PKIXPolicyNode) it.next()).getValidPolicy());
            }
            for (int i7 = size - 1; i7 >= 0; i7--) {
                List list4 = listArr[i7];
                for (int i8 = 0; i8 < list4.size(); i8++) {
                    PKIXPolicyNode pKIXPolicyNode7 = (PKIXPolicyNode) list4.get(i8);
                    if (!pKIXPolicyNode7.hasChildren()) {
                        pKIXPolicyNode = CertPathValidatorUtilities.removePolicyNode(pKIXPolicyNode, listArr, pKIXPolicyNode7);
                    }
                }
            }
        }
        return pKIXPolicyNode;
    }
}

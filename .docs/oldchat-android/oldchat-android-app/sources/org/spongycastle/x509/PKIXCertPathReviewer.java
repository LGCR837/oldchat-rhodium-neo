package org.spongycastle.x509;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXParameters;
import java.security.cert.PolicyNode;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.x509.AccessDescription;
import org.spongycastle.asn1.x509.AuthorityInformationAccess;
import org.spongycastle.asn1.x509.AuthorityKeyIdentifier;
import org.spongycastle.asn1.x509.BasicConstraints;
import org.spongycastle.asn1.x509.CRLDistPoint;
import org.spongycastle.asn1.x509.DistributionPoint;
import org.spongycastle.asn1.x509.DistributionPointName;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.GeneralNames;
import org.spongycastle.asn1.x509.GeneralSubtree;
import org.spongycastle.asn1.x509.NameConstraints;
import org.spongycastle.asn1.x509.qualified.ETSIQCObjectIdentifiers;
import org.spongycastle.asn1.x509.qualified.MonetaryValue;
import org.spongycastle.asn1.x509.qualified.QCStatement;
import org.spongycastle.asn1.x509.qualified.RFC3739QCObjectIdentifiers;
import org.spongycastle.i18n.ErrorBundle;
import org.spongycastle.i18n.filter.TrustedInput;
import org.spongycastle.i18n.filter.UntrustedInput;
import org.spongycastle.jce.provider.AnnotatedException;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.provider.PKIXNameConstraintValidator;
import org.spongycastle.jce.provider.PKIXNameConstraintValidatorException;
import org.spongycastle.util.Integers;

/* JADX INFO: loaded from: classes.dex */
public class PKIXCertPathReviewer extends CertPathValidatorUtilities {
    private static final String RESOURCE_NAME = "org.spongycastle.x509.CertPathReviewerMessages";
    protected CertPath certPath;
    protected List certs;
    protected List[] errors;
    private boolean initialized;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    protected int f7696n;
    protected List[] notifications;
    protected PKIXParameters pkixParams;
    protected PolicyNode policyTree;
    protected PublicKey subjectPublicKey;
    protected TrustAnchor trustAnchor;
    protected Date validDate;
    private static final String QC_STATEMENT = Extension.qCStatements.getId();
    private static final String CRL_DIST_POINTS = Extension.cRLDistributionPoints.getId();
    private static final String AUTH_INFO_ACCESS = Extension.authorityInfoAccess.getId();

    public PKIXCertPathReviewer(CertPath certPath, PKIXParameters pKIXParameters) throws CertPathReviewerException {
        init(certPath, pKIXParameters);
    }

    private String IPtoString(byte[] bArr) {
        try {
            return InetAddress.getByAddress(bArr).getHostAddress();
        } catch (Exception unused) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i2 = 0; i2 != bArr.length; i2++) {
                stringBuffer.append(Integer.toHexString(bArr[i2] & 255));
                stringBuffer.append(' ');
            }
            return stringBuffer.toString();
        }
    }

    private void checkCriticalExtensions() {
        List<PKIXCertPathChecker> certPathCheckers = this.pkixParams.getCertPathCheckers();
        Iterator<PKIXCertPathChecker> it = certPathCheckers.iterator();
        while (it.hasNext()) {
            try {
                try {
                    it.next().init(false);
                } catch (CertPathValidatorException e2) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certPathCheckerError", new Object[]{e2.getMessage(), e2, e2.getClass().getName()}), e2);
                }
            } catch (CertPathReviewerException e3) {
                addError(e3.getErrorMessage(), e3.getIndex());
                return;
            }
        }
        for (int size = this.certs.size() - 1; size >= 0; size--) {
            X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
            Set<String> criticalExtensionOIDs = x509Certificate.getCriticalExtensionOIDs();
            if (criticalExtensionOIDs != null && !criticalExtensionOIDs.isEmpty()) {
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.KEY_USAGE);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.CERTIFICATE_POLICIES);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.POLICY_MAPPINGS);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.INHIBIT_ANY_POLICY);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.ISSUING_DISTRIBUTION_POINT);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.DELTA_CRL_INDICATOR);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.POLICY_CONSTRAINTS);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.BASIC_CONSTRAINTS);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.SUBJECT_ALTERNATIVE_NAME);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.NAME_CONSTRAINTS);
                String str = QC_STATEMENT;
                if (criticalExtensionOIDs.contains(str) && processQcStatements(x509Certificate, size)) {
                    criticalExtensionOIDs.remove(str);
                }
                Iterator<PKIXCertPathChecker> it2 = certPathCheckers.iterator();
                while (it2.hasNext()) {
                    try {
                        it2.next().check(x509Certificate, criticalExtensionOIDs);
                    } catch (CertPathValidatorException e4) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.criticalExtensionError", new Object[]{e4.getMessage(), e4, e4.getClass().getName()}), e4.getCause(), this.certPath, size);
                    }
                }
                if (!criticalExtensionOIDs.isEmpty()) {
                    Iterator<String> it3 = criticalExtensionOIDs.iterator();
                    while (it3.hasNext()) {
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.unknownCriticalExt", new Object[]{new ASN1ObjectIdentifier(it3.next())}), size);
                    }
                }
            }
        }
    }

    private void checkNameConstraints() {
        PKIXNameConstraintValidator pKIXNameConstraintValidator = new PKIXNameConstraintValidator();
        try {
            for (int size = this.certs.size() - 1; size > 0; size--) {
                X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
                if (!CertPathValidatorUtilities.isSelfIssued(x509Certificate)) {
                    X500Principal subjectPrincipal = CertPathValidatorUtilities.getSubjectPrincipal(x509Certificate);
                    try {
                        ASN1Sequence aSN1Sequence = (ASN1Sequence) new ASN1InputStream(new ByteArrayInputStream(subjectPrincipal.getEncoded())).readObject();
                        try {
                            pKIXNameConstraintValidator.checkPermittedDN(aSN1Sequence);
                            try {
                                pKIXNameConstraintValidator.checkExcludedDN(aSN1Sequence);
                                try {
                                    ASN1Sequence aSN1Sequence2 = (ASN1Sequence) CertPathValidatorUtilities.getExtensionValue(x509Certificate, CertPathValidatorUtilities.SUBJECT_ALTERNATIVE_NAME);
                                    if (aSN1Sequence2 != null) {
                                        for (int i2 = 0; i2 < aSN1Sequence2.size(); i2++) {
                                            GeneralName generalName = GeneralName.getInstance(aSN1Sequence2.getObjectAt(i2));
                                            try {
                                                pKIXNameConstraintValidator.checkPermitted(generalName);
                                                pKIXNameConstraintValidator.checkExcluded(generalName);
                                            } catch (PKIXNameConstraintValidatorException e2) {
                                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notPermittedEmail", new Object[]{new UntrustedInput(generalName)}), e2, this.certPath, size);
                                            }
                                        }
                                    }
                                } catch (AnnotatedException e3) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.subjAltNameExtError"), e3, this.certPath, size);
                                }
                            } catch (PKIXNameConstraintValidatorException e4) {
                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.excludedDN", new Object[]{new UntrustedInput(subjectPrincipal.getName())}), e4, this.certPath, size);
                            }
                        } catch (PKIXNameConstraintValidatorException e5) {
                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notPermittedDN", new Object[]{new UntrustedInput(subjectPrincipal.getName())}), e5, this.certPath, size);
                        }
                    } catch (IOException e6) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ncSubjectNameError", new Object[]{new UntrustedInput(subjectPrincipal)}), e6, this.certPath, size);
                    }
                }
                try {
                    ASN1Sequence aSN1Sequence3 = (ASN1Sequence) CertPathValidatorUtilities.getExtensionValue(x509Certificate, CertPathValidatorUtilities.NAME_CONSTRAINTS);
                    if (aSN1Sequence3 != null) {
                        NameConstraints nameConstraints = NameConstraints.getInstance(aSN1Sequence3);
                        GeneralSubtree[] permittedSubtrees = nameConstraints.getPermittedSubtrees();
                        if (permittedSubtrees != null) {
                            pKIXNameConstraintValidator.intersectPermittedSubtree(permittedSubtrees);
                        }
                        GeneralSubtree[] excludedSubtrees = nameConstraints.getExcludedSubtrees();
                        if (excludedSubtrees != null) {
                            for (int i3 = 0; i3 != excludedSubtrees.length; i3++) {
                                pKIXNameConstraintValidator.addExcludedSubtree(excludedSubtrees[i3]);
                            }
                        }
                    }
                } catch (AnnotatedException e7) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ncExtError"), e7, this.certPath, size);
                }
            }
        } catch (CertPathReviewerException e8) {
            addError(e8.getErrorMessage(), e8.getIndex());
        }
    }

    private void checkPathLength() {
        BasicConstraints basicConstraints;
        BigInteger pathLenConstraint;
        int iIntValue;
        int i2 = this.f7696n;
        int i3 = 0;
        for (int size = this.certs.size() - 1; size > 0; size--) {
            X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
            if (!CertPathValidatorUtilities.isSelfIssued(x509Certificate)) {
                if (i2 <= 0) {
                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.pathLengthExtended"));
                }
                i2--;
                i3++;
            }
            try {
                basicConstraints = BasicConstraints.getInstance(CertPathValidatorUtilities.getExtensionValue(x509Certificate, CertPathValidatorUtilities.BASIC_CONSTRAINTS));
            } catch (AnnotatedException unused) {
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.processLengthConstError"), size);
                basicConstraints = null;
            }
            if (basicConstraints != null && (pathLenConstraint = basicConstraints.getPathLenConstraint()) != null && (iIntValue = pathLenConstraint.intValue()) < i2) {
                i2 = iIntValue;
            }
        }
        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.totalPathLength", new Object[]{Integers.valueOf(i3)}));
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x0242 A[Catch: CertPathReviewerException -> 0x00e0, TryCatch #9 {CertPathReviewerException -> 0x00e0, blocks: (B:17:0x006f, B:21:0x007f, B:23:0x008c, B:27:0x009c, B:28:0x00a7, B:30:0x00ad, B:32:0x00ce, B:33:0x00d6, B:35:0x00dc, B:39:0x00e4, B:40:0x00f0, B:44:0x00fc, B:47:0x0103, B:48:0x010c, B:50:0x0112, B:52:0x011c, B:55:0x0123, B:57:0x0127, B:97:0x0213, B:99:0x0219, B:100:0x021c, B:102:0x0222, B:104:0x022e, B:107:0x0236, B:108:0x0239, B:109:0x023c, B:111:0x0242, B:112:0x024b, B:114:0x0251, B:122:0x0274, B:123:0x0280, B:124:0x0281, B:126:0x0285, B:128:0x028d, B:129:0x0291, B:131:0x0297, B:134:0x02b9, B:136:0x02c3, B:137:0x02c8, B:138:0x02d4, B:139:0x02d5, B:140:0x02e1, B:142:0x02e4, B:143:0x02f1, B:145:0x02f7, B:147:0x031d, B:149:0x0335, B:148:0x032c, B:150:0x033c, B:151:0x0342, B:153:0x0348, B:155:0x0350, B:166:0x037a, B:159:0x0358, B:160:0x0364, B:162:0x0366, B:163:0x0375, B:170:0x0388, B:181:0x03a5, B:183:0x03af, B:184:0x03b3, B:186:0x03b9, B:191:0x03c9, B:194:0x03da, B:197:0x03eb, B:199:0x03f5, B:210:0x0437, B:202:0x0401, B:203:0x040f, B:204:0x0410, B:205:0x041e, B:207:0x0420, B:208:0x042e, B:61:0x0136, B:62:0x013a, B:64:0x0140, B:66:0x0156, B:68:0x0160, B:69:0x0165, B:71:0x016b, B:72:0x0179, B:74:0x017f, B:76:0x018b, B:80:0x0198, B:81:0x019e, B:83:0x01a4, B:88:0x01bd, B:77:0x018e, B:79:0x0192, B:92:0x01f6, B:95:0x0206, B:96:0x0212, B:212:0x0446, B:213:0x0453, B:214:0x0454, B:218:0x0465, B:220:0x046f, B:221:0x0474, B:223:0x047a, B:226:0x0488, B:233:0x04a1, B:310:0x05f1, B:311:0x05fd, B:236:0x04ac, B:237:0x04b8, B:238:0x04b9, B:240:0x04bf, B:242:0x04c7, B:244:0x04cd, B:246:0x04d7, B:247:0x04da, B:249:0x04e0, B:251:0x04f0, B:252:0x04f4, B:254:0x04fa, B:255:0x0502, B:256:0x0505, B:257:0x0508, B:258:0x050c, B:260:0x0512, B:261:0x0520, B:263:0x0528, B:264:0x052b, B:266:0x0531, B:268:0x053d, B:269:0x0541, B:270:0x0544, B:271:0x0547, B:272:0x0553, B:274:0x0558, B:276:0x0562, B:277:0x0565, B:279:0x056b, B:281:0x057b, B:282:0x057f, B:284:0x0585, B:286:0x0595, B:287:0x0599, B:288:0x059c, B:289:0x059f, B:290:0x05a5, B:292:0x05ab, B:294:0x05bd, B:297:0x05c7, B:299:0x05cd, B:300:0x05d0, B:302:0x05d6, B:304:0x05e2, B:305:0x05e6, B:306:0x05e9, B:312:0x05fe, B:313:0x060a), top: B:330:0x006f, inners: #0, #1, #2, #3, #4, #5, #8, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0219 A[Catch: CertPathReviewerException -> 0x00e0, TryCatch #9 {CertPathReviewerException -> 0x00e0, blocks: (B:17:0x006f, B:21:0x007f, B:23:0x008c, B:27:0x009c, B:28:0x00a7, B:30:0x00ad, B:32:0x00ce, B:33:0x00d6, B:35:0x00dc, B:39:0x00e4, B:40:0x00f0, B:44:0x00fc, B:47:0x0103, B:48:0x010c, B:50:0x0112, B:52:0x011c, B:55:0x0123, B:57:0x0127, B:97:0x0213, B:99:0x0219, B:100:0x021c, B:102:0x0222, B:104:0x022e, B:107:0x0236, B:108:0x0239, B:109:0x023c, B:111:0x0242, B:112:0x024b, B:114:0x0251, B:122:0x0274, B:123:0x0280, B:124:0x0281, B:126:0x0285, B:128:0x028d, B:129:0x0291, B:131:0x0297, B:134:0x02b9, B:136:0x02c3, B:137:0x02c8, B:138:0x02d4, B:139:0x02d5, B:140:0x02e1, B:142:0x02e4, B:143:0x02f1, B:145:0x02f7, B:147:0x031d, B:149:0x0335, B:148:0x032c, B:150:0x033c, B:151:0x0342, B:153:0x0348, B:155:0x0350, B:166:0x037a, B:159:0x0358, B:160:0x0364, B:162:0x0366, B:163:0x0375, B:170:0x0388, B:181:0x03a5, B:183:0x03af, B:184:0x03b3, B:186:0x03b9, B:191:0x03c9, B:194:0x03da, B:197:0x03eb, B:199:0x03f5, B:210:0x0437, B:202:0x0401, B:203:0x040f, B:204:0x0410, B:205:0x041e, B:207:0x0420, B:208:0x042e, B:61:0x0136, B:62:0x013a, B:64:0x0140, B:66:0x0156, B:68:0x0160, B:69:0x0165, B:71:0x016b, B:72:0x0179, B:74:0x017f, B:76:0x018b, B:80:0x0198, B:81:0x019e, B:83:0x01a4, B:88:0x01bd, B:77:0x018e, B:79:0x0192, B:92:0x01f6, B:95:0x0206, B:96:0x0212, B:212:0x0446, B:213:0x0453, B:214:0x0454, B:218:0x0465, B:220:0x046f, B:221:0x0474, B:223:0x047a, B:226:0x0488, B:233:0x04a1, B:310:0x05f1, B:311:0x05fd, B:236:0x04ac, B:237:0x04b8, B:238:0x04b9, B:240:0x04bf, B:242:0x04c7, B:244:0x04cd, B:246:0x04d7, B:247:0x04da, B:249:0x04e0, B:251:0x04f0, B:252:0x04f4, B:254:0x04fa, B:255:0x0502, B:256:0x0505, B:257:0x0508, B:258:0x050c, B:260:0x0512, B:261:0x0520, B:263:0x0528, B:264:0x052b, B:266:0x0531, B:268:0x053d, B:269:0x0541, B:270:0x0544, B:271:0x0547, B:272:0x0553, B:274:0x0558, B:276:0x0562, B:277:0x0565, B:279:0x056b, B:281:0x057b, B:282:0x057f, B:284:0x0585, B:286:0x0595, B:287:0x0599, B:288:0x059c, B:289:0x059f, B:290:0x05a5, B:292:0x05ab, B:294:0x05bd, B:297:0x05c7, B:299:0x05cd, B:300:0x05d0, B:302:0x05d6, B:304:0x05e2, B:305:0x05e6, B:306:0x05e9, B:312:0x05fe, B:313:0x060a), top: B:330:0x006f, inners: #0, #1, #2, #3, #4, #5, #8, #10 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void checkPolicy() {
        /*
            Method dump skipped, instruction units count: 1559
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.x509.PKIXCertPathReviewer.checkPolicy():void");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(17:55|(2:153|57)(2:61|(2:159|63)(16:67|(2:74|75)(3:71|(1:73)(0)|75)|176|76|80|(16:155|82|(1:84)(1:87)|166|88|(1:90)(1:93)|94|(2:97|95)|182|98|(2:101|99)|149|102|103|157|104)(1:111)|(1:116)(1:115)|117|(1:138)(7:119|(1:123)|164|125|(2:127|(1:129))(1:130)|133|(1:137))|139|172|140|151|141|181|145))|58|176|76|80|(0)(0)|(2:113|116)(0)|117|(0)(0)|139|172|140|151|141|181|145) */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x03fa, code lost:
    
        r6 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x03fc, code lost:
    
        addError(new org.spongycastle.i18n.ErrorBundle(r12, "CertPathReviewer.pubKeyError"), r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0267, code lost:
    
        r2 = new java.lang.Object[r15];
        r2[0] = new org.spongycastle.i18n.filter.TrustedInput(r3.getNotAfter());
        addError(new org.spongycastle.i18n.ErrorBundle(r9, "CertPathReviewer.certificateExpired", r2), r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x027f, code lost:
    
        r2 = new java.lang.Object[r15];
        r2[0] = new org.spongycastle.i18n.filter.TrustedInput(r3.getNotBefore());
        addError(new org.spongycastle.i18n.ErrorBundle(r9, "CertPathReviewer.certificateNotYetValid", r2), r5);
     */
    /* JADX WARN: Removed duplicated region for block: B:111:0x034c  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0381  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0388  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x029e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x025d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void checkSignatures() {
        /*
            Method dump skipped, instruction units count: 1053
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.x509.PKIXCertPathReviewer.checkSignatures():void");
    }

    private X509CRL getCRL(String str) throws CertPathReviewerException {
        try {
            URL url = new URL(str);
            if (!url.getProtocol().equals("http") && !url.getProtocol().equals("https")) {
                return null;
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                return (X509CRL) CertificateFactory.getInstance("X.509", BouncyCastleProvider.PROVIDER_NAME).generateCRL(httpURLConnection.getInputStream());
            }
            throw new Exception(httpURLConnection.getResponseMessage());
        } catch (Exception e2) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.loadCrlDistPointError", new Object[]{new UntrustedInput(str), e2.getMessage(), e2, e2.getClass().getName()}));
        }
    }

    private boolean processQcStatements(X509Certificate x509Certificate, int i2) {
        String str;
        int i3;
        ErrorBundle errorBundle;
        char c2 = 2;
        String str2 = RESOURCE_NAME;
        try {
            ASN1Sequence aSN1Sequence = (ASN1Sequence) CertPathValidatorUtilities.getExtensionValue(x509Certificate, QC_STATEMENT);
            int i4 = 0;
            boolean z2 = false;
            while (i4 < aSN1Sequence.size()) {
                QCStatement qCStatement = QCStatement.getInstance(aSN1Sequence.getObjectAt(i4));
                if (ETSIQCObjectIdentifiers.id_etsi_qcs_QcCompliance.equals(qCStatement.getStatementId())) {
                    addNotification(new ErrorBundle(str2, "CertPathReviewer.QcEuCompliance"), i2);
                } else if (!RFC3739QCObjectIdentifiers.id_qcs_pkixQCSyntax_v1.equals(qCStatement.getStatementId())) {
                    if (ETSIQCObjectIdentifiers.id_etsi_qcs_QcSSCD.equals(qCStatement.getStatementId())) {
                        addNotification(new ErrorBundle(str2, "CertPathReviewer.QcSSCD"), i2);
                    } else if (ETSIQCObjectIdentifiers.id_etsi_qcs_LimiteValue.equals(qCStatement.getStatementId())) {
                        MonetaryValue monetaryValue = MonetaryValue.getInstance(qCStatement.getStatementInfo());
                        monetaryValue.getCurrency();
                        String str3 = str2;
                        try {
                            double dDoubleValue = monetaryValue.getAmount().doubleValue() * Math.pow(10.0d, monetaryValue.getExponent().doubleValue());
                            if (monetaryValue.getCurrency().isAlphabetic()) {
                                String alphabetic = monetaryValue.getCurrency().getAlphabetic();
                                TrustedInput trustedInput = new TrustedInput(new Double(dDoubleValue));
                                Object[] objArr = new Object[3];
                                objArr[0] = alphabetic;
                                objArr[1] = trustedInput;
                                objArr[c2] = monetaryValue;
                                str = str3;
                                try {
                                    errorBundle = new ErrorBundle(str, "CertPathReviewer.QcLimitValueAlpha", objArr);
                                } catch (AnnotatedException unused) {
                                    addError(new ErrorBundle(str, "CertPathReviewer.QcStatementExtError"), i2);
                                    return false;
                                }
                            } else {
                                str = str3;
                                errorBundle = new ErrorBundle(str, "CertPathReviewer.QcLimitValueNum", new Object[]{Integers.valueOf(monetaryValue.getCurrency().getNumeric()), new TrustedInput(new Double(dDoubleValue)), monetaryValue});
                            }
                            addNotification(errorBundle, i2);
                            i3 = 1;
                            i4 += i3;
                            str2 = str;
                            c2 = 2;
                        } catch (AnnotatedException unused2) {
                            str = str3;
                        }
                    } else {
                        str = str2;
                        i3 = 1;
                        addNotification(new ErrorBundle(str, "CertPathReviewer.QcUnknownStatement", new Object[]{qCStatement.getStatementId(), new UntrustedInput(qCStatement)}), i2);
                        z2 = true;
                        i4 += i3;
                        str2 = str;
                        c2 = 2;
                    }
                }
                str = str2;
                i3 = 1;
                i4 += i3;
                str2 = str;
                c2 = 2;
            }
            return !z2;
        } catch (AnnotatedException unused3) {
            str = str2;
        }
    }

    public void addError(ErrorBundle errorBundle) {
        this.errors[0].add(errorBundle);
    }

    public void addNotification(ErrorBundle errorBundle) {
        this.notifications[0].add(errorBundle);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x02bc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void checkCRLs(java.security.cert.PKIXParameters r23, java.security.cert.X509Certificate r24, java.util.Date r25, java.security.cert.X509Certificate r26, java.security.PublicKey r27, java.util.Vector r28, int r29) throws org.spongycastle.x509.CertPathReviewerException {
        /*
            Method dump skipped, instruction units count: 1198
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.x509.PKIXCertPathReviewer.checkCRLs(java.security.cert.PKIXParameters, java.security.cert.X509Certificate, java.util.Date, java.security.cert.X509Certificate, java.security.PublicKey, java.util.Vector, int):void");
    }

    public void checkRevocation(PKIXParameters pKIXParameters, X509Certificate x509Certificate, Date date, X509Certificate x509Certificate2, PublicKey publicKey, Vector vector, Vector vector2, int i2) throws CertPathReviewerException {
        checkCRLs(pKIXParameters, x509Certificate, date, x509Certificate2, publicKey, vector, i2);
    }

    public void doChecks() {
        if (!this.initialized) {
            throw new IllegalStateException("Object not initialized. Call init() first.");
        }
        if (this.notifications != null) {
            return;
        }
        int i2 = this.f7696n;
        this.notifications = new List[i2 + 1];
        this.errors = new List[i2 + 1];
        int i3 = 0;
        while (true) {
            List[] listArr = this.notifications;
            if (i3 >= listArr.length) {
                checkSignatures();
                checkNameConstraints();
                checkPathLength();
                checkPolicy();
                checkCriticalExtensions();
                return;
            }
            listArr[i3] = new ArrayList();
            this.errors[i3] = new ArrayList();
            i3++;
        }
    }

    public Vector getCRLDistUrls(CRLDistPoint cRLDistPoint) {
        Vector vector = new Vector();
        if (cRLDistPoint != null) {
            for (DistributionPoint distributionPoint : cRLDistPoint.getDistributionPoints()) {
                DistributionPointName distributionPoint2 = distributionPoint.getDistributionPoint();
                if (distributionPoint2.getType() == 0) {
                    GeneralName[] names = GeneralNames.getInstance(distributionPoint2.getName()).getNames();
                    for (int i2 = 0; i2 < names.length; i2++) {
                        if (names[i2].getTagNo() == 6) {
                            vector.add(((DERIA5String) names[i2].getName()).getString());
                        }
                    }
                }
            }
        }
        return vector;
    }

    public CertPath getCertPath() {
        return this.certPath;
    }

    public int getCertPathSize() {
        return this.f7696n;
    }

    public List[] getErrors() {
        doChecks();
        return this.errors;
    }

    public List[] getNotifications() {
        doChecks();
        return this.notifications;
    }

    public Vector getOCSPUrls(AuthorityInformationAccess authorityInformationAccess) {
        Vector vector = new Vector();
        if (authorityInformationAccess != null) {
            AccessDescription[] accessDescriptions = authorityInformationAccess.getAccessDescriptions();
            for (int i2 = 0; i2 < accessDescriptions.length; i2++) {
                if (accessDescriptions[i2].getAccessMethod().equals(AccessDescription.id_ad_ocsp)) {
                    GeneralName accessLocation = accessDescriptions[i2].getAccessLocation();
                    if (accessLocation.getTagNo() == 6) {
                        vector.add(((DERIA5String) accessLocation.getName()).getString());
                    }
                }
            }
        }
        return vector;
    }

    public PolicyNode getPolicyTree() {
        doChecks();
        return this.policyTree;
    }

    public PublicKey getSubjectPublicKey() {
        doChecks();
        return this.subjectPublicKey;
    }

    public TrustAnchor getTrustAnchor() {
        doChecks();
        return this.trustAnchor;
    }

    public Collection getTrustAnchors(X509Certificate x509Certificate, Set set) throws CertPathReviewerException {
        ArrayList arrayList = new ArrayList();
        Iterator it = set.iterator();
        X509CertSelector x509CertSelector = new X509CertSelector();
        try {
            x509CertSelector.setSubject(CertPathValidatorUtilities.getEncodedIssuerPrincipal(x509Certificate).getEncoded());
            byte[] extensionValue = x509Certificate.getExtensionValue(Extension.authorityKeyIdentifier.getId());
            if (extensionValue != null) {
                AuthorityKeyIdentifier authorityKeyIdentifier = AuthorityKeyIdentifier.getInstance(ASN1Primitive.fromByteArray(((ASN1OctetString) ASN1Primitive.fromByteArray(extensionValue)).getOctets()));
                x509CertSelector.setSerialNumber(authorityKeyIdentifier.getAuthorityCertSerialNumber());
                byte[] keyIdentifier = authorityKeyIdentifier.getKeyIdentifier();
                if (keyIdentifier != null) {
                    x509CertSelector.setSubjectKeyIdentifier(new DEROctetString(keyIdentifier).getEncoded());
                }
            }
            while (it.hasNext()) {
                TrustAnchor trustAnchor = (TrustAnchor) it.next();
                if (trustAnchor.getTrustedCert() != null) {
                    if (x509CertSelector.match(trustAnchor.getTrustedCert())) {
                        arrayList.add(trustAnchor);
                    }
                } else if (trustAnchor.getCAName() != null && trustAnchor.getCAPublicKey() != null && CertPathValidatorUtilities.getEncodedIssuerPrincipal(x509Certificate).equals(new X500Principal(trustAnchor.getCAName()))) {
                    arrayList.add(trustAnchor);
                }
            }
            return arrayList;
        } catch (IOException unused) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustAnchorIssuerError"));
        }
    }

    public void init(CertPath certPath, PKIXParameters pKIXParameters) throws CertPathReviewerException {
        if (this.initialized) {
            throw new IllegalStateException("object is already initialized!");
        }
        this.initialized = true;
        if (certPath == null) {
            throw new NullPointerException("certPath was null");
        }
        this.certPath = certPath;
        List<? extends Certificate> certificates = certPath.getCertificates();
        this.certs = certificates;
        this.f7696n = certificates.size();
        if (this.certs.isEmpty()) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.emptyCertPath"));
        }
        PKIXParameters pKIXParameters2 = (PKIXParameters) pKIXParameters.clone();
        this.pkixParams = pKIXParameters2;
        this.validDate = CertPathValidatorUtilities.getValidDate(pKIXParameters2);
        this.notifications = null;
        this.errors = null;
        this.trustAnchor = null;
        this.subjectPublicKey = null;
        this.policyTree = null;
    }

    public boolean isValidCertPath() {
        doChecks();
        int i2 = 0;
        while (true) {
            List[] listArr = this.errors;
            if (i2 >= listArr.length) {
                return true;
            }
            if (!listArr[i2].isEmpty()) {
                return false;
            }
            i2++;
        }
    }

    public void addError(ErrorBundle errorBundle, int i2) {
        if (i2 < -1 || i2 >= this.f7696n) {
            throw new IndexOutOfBoundsException();
        }
        this.errors[i2 + 1].add(errorBundle);
    }

    public void addNotification(ErrorBundle errorBundle, int i2) {
        if (i2 < -1 || i2 >= this.f7696n) {
            throw new IndexOutOfBoundsException();
        }
        this.notifications[i2 + 1].add(errorBundle);
    }

    public PKIXCertPathReviewer() {
    }

    public List getErrors(int i2) {
        doChecks();
        return this.errors[i2 + 1];
    }

    public List getNotifications(int i2) {
        doChecks();
        return this.notifications[i2 + 1];
    }
}

package org.spongycastle.jce.provider;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.security.cert.CRL;
import java.util.ArrayList;
import java.util.Collection;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.SignedData;
import org.spongycastle.asn1.x509.CertificateList;
import org.spongycastle.x509.X509StreamParserSpi;
import org.spongycastle.x509.util.StreamParsingException;

/* JADX INFO: loaded from: classes.dex */
public class X509CRLParser extends X509StreamParserSpi {
    private static final PEMUtil PEM_PARSER = new PEMUtil("CRL");
    private ASN1Set sData = null;
    private int sDataObjectCount = 0;
    private InputStream currentStream = null;

    private CRL getCRL() {
        ASN1Set aSN1Set = this.sData;
        if (aSN1Set == null || this.sDataObjectCount >= aSN1Set.size()) {
            return null;
        }
        ASN1Set aSN1Set2 = this.sData;
        int i2 = this.sDataObjectCount;
        this.sDataObjectCount = i2 + 1;
        return new X509CRLObject(CertificateList.getInstance(aSN1Set2.getObjectAt(i2)));
    }

    private CRL readDERCRL(InputStream inputStream) {
        ASN1Sequence aSN1Sequence = (ASN1Sequence) new ASN1InputStream(inputStream).readObject();
        if (aSN1Sequence.size() <= 1 || !(aSN1Sequence.getObjectAt(0) instanceof ASN1ObjectIdentifier) || !aSN1Sequence.getObjectAt(0).equals(PKCSObjectIdentifiers.signedData)) {
            return new X509CRLObject(CertificateList.getInstance(aSN1Sequence));
        }
        this.sData = new SignedData(ASN1Sequence.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(1), true)).getCRLs();
        return getCRL();
    }

    private CRL readPEMCRL(InputStream inputStream) {
        ASN1Sequence pEMObject = PEM_PARSER.readPEMObject(inputStream);
        if (pEMObject != null) {
            return new X509CRLObject(CertificateList.getInstance(pEMObject));
        }
        return null;
    }

    @Override // org.spongycastle.x509.X509StreamParserSpi
    public void engineInit(InputStream inputStream) {
        this.currentStream = inputStream;
        this.sData = null;
        this.sDataObjectCount = 0;
        if (inputStream.markSupported()) {
            return;
        }
        this.currentStream = new BufferedInputStream(this.currentStream);
    }

    @Override // org.spongycastle.x509.X509StreamParserSpi
    public Object engineRead() throws StreamParsingException {
        try {
            ASN1Set aSN1Set = this.sData;
            if (aSN1Set != null) {
                if (this.sDataObjectCount != aSN1Set.size()) {
                    return getCRL();
                }
                this.sData = null;
                this.sDataObjectCount = 0;
                return null;
            }
            this.currentStream.mark(10);
            int i2 = this.currentStream.read();
            if (i2 == -1) {
                return null;
            }
            if (i2 != 48) {
                this.currentStream.reset();
                return readPEMCRL(this.currentStream);
            }
            this.currentStream.reset();
            return readDERCRL(this.currentStream);
        } catch (Exception e2) {
            throw new StreamParsingException(e2.toString(), e2);
        }
    }

    @Override // org.spongycastle.x509.X509StreamParserSpi
    public Collection engineReadAll() {
        ArrayList arrayList = new ArrayList();
        while (true) {
            CRL crl = (CRL) engineRead();
            if (crl == null) {
                return arrayList;
            }
            arrayList.add(crl);
        }
    }
}

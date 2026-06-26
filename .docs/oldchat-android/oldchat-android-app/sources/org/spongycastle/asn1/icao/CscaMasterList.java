package org.spongycastle.asn1.icao;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERSet;
import org.spongycastle.asn1.x509.Certificate;

/* JADX INFO: loaded from: classes.dex */
public class CscaMasterList extends ASN1Object {
    private Certificate[] certList;
    private ASN1Integer version;

    private CscaMasterList(ASN1Sequence aSN1Sequence) {
        this.version = new ASN1Integer(0L);
        if (aSN1Sequence == null || aSN1Sequence.size() == 0) {
            throw new IllegalArgumentException("null or empty sequence passed.");
        }
        if (aSN1Sequence.size() != 2) {
            throw new IllegalArgumentException("Incorrect sequence size: " + aSN1Sequence.size());
        }
        int i2 = 0;
        this.version = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        ASN1Set aSN1Set = ASN1Set.getInstance(aSN1Sequence.getObjectAt(1));
        this.certList = new Certificate[aSN1Set.size()];
        while (true) {
            Certificate[] certificateArr = this.certList;
            if (i2 >= certificateArr.length) {
                return;
            }
            certificateArr[i2] = Certificate.getInstance(aSN1Set.getObjectAt(i2));
            i2++;
        }
    }

    private Certificate[] copyCertList(Certificate[] certificateArr) {
        int length = certificateArr.length;
        Certificate[] certificateArr2 = new Certificate[length];
        for (int i2 = 0; i2 != length; i2++) {
            certificateArr2[i2] = certificateArr[i2];
        }
        return certificateArr2;
    }

    public static CscaMasterList getInstance(Object obj) {
        if (obj instanceof CscaMasterList) {
            return (CscaMasterList) obj;
        }
        if (obj != null) {
            return new CscaMasterList(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public Certificate[] getCertStructs() {
        return copyCertList(this.certList);
    }

    public int getVersion() {
        return this.version.getValue().intValue();
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.version);
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        int i2 = 0;
        while (true) {
            Certificate[] certificateArr = this.certList;
            if (i2 >= certificateArr.length) {
                aSN1EncodableVector.add(new DERSet(aSN1EncodableVector2));
                return new DERSequence(aSN1EncodableVector);
            }
            aSN1EncodableVector2.add(certificateArr[i2]);
            i2++;
        }
    }

    public CscaMasterList(Certificate[] certificateArr) {
        this.version = new ASN1Integer(0L);
        this.certList = copyCertList(certificateArr);
    }
}

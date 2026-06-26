package org.spongycastle.asn1.cmc;

import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.x509.Extension;

/* JADX INFO: loaded from: classes.dex */
class Utils {
    public static BodyPartID[] clone(BodyPartID[] bodyPartIDArr) {
        BodyPartID[] bodyPartIDArr2 = new BodyPartID[bodyPartIDArr.length];
        System.arraycopy(bodyPartIDArr, 0, bodyPartIDArr2, 0, bodyPartIDArr.length);
        return bodyPartIDArr2;
    }

    public static BodyPartID[] toBodyPartIDArray(ASN1Sequence aSN1Sequence) {
        BodyPartID[] bodyPartIDArr = new BodyPartID[aSN1Sequence.size()];
        for (int i2 = 0; i2 != aSN1Sequence.size(); i2++) {
            bodyPartIDArr[i2] = BodyPartID.getInstance(aSN1Sequence.getObjectAt(i2));
        }
        return bodyPartIDArr;
    }

    public static Extension[] clone(Extension[] extensionArr) {
        Extension[] extensionArr2 = new Extension[extensionArr.length];
        System.arraycopy(extensionArr, 0, extensionArr2, 0, extensionArr.length);
        return extensionArr2;
    }
}

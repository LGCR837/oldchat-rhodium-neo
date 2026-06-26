package org.spongycastle.asn1.x9;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECFieldElement;

/* JADX INFO: loaded from: classes.dex */
public class X9IntegerConverter {
    public int getByteLength(ECCurve eCCurve) {
        return (eCCurve.getFieldSize() + 7) / 8;
    }

    public byte[] integerToBytes(BigInteger bigInteger, int i2) {
        byte[] byteArray = bigInteger.toByteArray();
        if (i2 < byteArray.length) {
            byte[] bArr = new byte[i2];
            System.arraycopy(byteArray, byteArray.length - i2, bArr, 0, i2);
            return bArr;
        }
        if (i2 <= byteArray.length) {
            return byteArray;
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(byteArray, 0, bArr2, i2 - byteArray.length, byteArray.length);
        return bArr2;
    }

    public int getByteLength(ECFieldElement eCFieldElement) {
        return (eCFieldElement.getFieldSize() + 7) / 8;
    }
}

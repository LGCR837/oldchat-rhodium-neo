package org.spongycastle.jce;

import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class ECNamedCurveTable {
    public static Enumeration getNames() {
        return org.spongycastle.asn1.x9.ECNamedCurveTable.getNames();
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0013, code lost:
    
        r0 = org.spongycastle.asn1.x9.ECNamedCurveTable.getByName(r8);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.spongycastle.jce.spec.ECNamedCurveParameterSpec getParameterSpec(java.lang.String r8) {
        /*
            org.spongycastle.asn1.x9.X9ECParameters r0 = org.spongycastle.crypto.ec.CustomNamedCurves.getByName(r8)
            if (r0 != 0) goto L24
            org.spongycastle.asn1.ASN1ObjectIdentifier r1 = new org.spongycastle.asn1.ASN1ObjectIdentifier     // Catch: java.lang.IllegalArgumentException -> L10
            r1.<init>(r8)     // Catch: java.lang.IllegalArgumentException -> L10
            org.spongycastle.asn1.x9.X9ECParameters r0 = org.spongycastle.crypto.ec.CustomNamedCurves.getByOID(r1)     // Catch: java.lang.IllegalArgumentException -> L10
            goto L11
        L10:
        L11:
            if (r0 != 0) goto L24
            org.spongycastle.asn1.x9.X9ECParameters r0 = org.spongycastle.asn1.x9.ECNamedCurveTable.getByName(r8)
            if (r0 != 0) goto L24
            org.spongycastle.asn1.ASN1ObjectIdentifier r1 = new org.spongycastle.asn1.ASN1ObjectIdentifier     // Catch: java.lang.IllegalArgumentException -> L23
            r1.<init>(r8)     // Catch: java.lang.IllegalArgumentException -> L23
            org.spongycastle.asn1.x9.X9ECParameters r0 = org.spongycastle.asn1.x9.ECNamedCurveTable.getByOID(r1)     // Catch: java.lang.IllegalArgumentException -> L23
            goto L24
        L23:
        L24:
            if (r0 != 0) goto L28
            r8 = 0
            return r8
        L28:
            org.spongycastle.jce.spec.ECNamedCurveParameterSpec r7 = new org.spongycastle.jce.spec.ECNamedCurveParameterSpec
            org.spongycastle.math.ec.ECCurve r2 = r0.getCurve()
            org.spongycastle.math.ec.ECPoint r3 = r0.getG()
            java.math.BigInteger r4 = r0.getN()
            java.math.BigInteger r5 = r0.getH()
            byte[] r6 = r0.getSeed()
            r0 = r7
            r1 = r8
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.jce.ECNamedCurveTable.getParameterSpec(java.lang.String):org.spongycastle.jce.spec.ECNamedCurveParameterSpec");
    }
}

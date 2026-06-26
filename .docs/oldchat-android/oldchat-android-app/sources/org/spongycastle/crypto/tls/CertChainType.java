package org.spongycastle.crypto.tls;

/* JADX INFO: loaded from: classes.dex */
public class CertChainType {
    public static final short individual_certs = 0;
    public static final short pkipath = 1;

    public static boolean isValid(short s2) {
        return s2 >= 0 && s2 <= 1;
    }
}

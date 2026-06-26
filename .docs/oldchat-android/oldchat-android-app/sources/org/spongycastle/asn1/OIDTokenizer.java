package org.spongycastle.asn1;

/* JADX INFO: loaded from: classes.dex */
public class OIDTokenizer {
    private int index = 0;
    private String oid;

    public OIDTokenizer(String str) {
        this.oid = str;
    }

    public boolean hasMoreTokens() {
        return this.index != -1;
    }

    public String nextToken() {
        int i2 = this.index;
        if (i2 == -1) {
            return null;
        }
        int iIndexOf = this.oid.indexOf(46, i2);
        if (iIndexOf == -1) {
            String strSubstring = this.oid.substring(this.index);
            this.index = -1;
            return strSubstring;
        }
        String strSubstring2 = this.oid.substring(this.index, iIndexOf);
        this.index = iIndexOf + 1;
        return strSubstring2;
    }
}

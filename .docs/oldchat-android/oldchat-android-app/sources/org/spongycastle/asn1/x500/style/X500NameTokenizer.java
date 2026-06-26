package org.spongycastle.asn1.x500.style;

/* JADX INFO: loaded from: classes.dex */
public class X500NameTokenizer {
    private StringBuffer buf;
    private int index;
    private char separator;
    private String value;

    public X500NameTokenizer(String str) {
        this(str, ',');
    }

    public boolean hasMoreTokens() {
        return this.index != this.value.length();
    }

    public String nextToken() {
        if (this.index == this.value.length()) {
            return null;
        }
        int i2 = this.index + 1;
        this.buf.setLength(0);
        boolean z2 = false;
        boolean z3 = false;
        while (i2 != this.value.length()) {
            char cCharAt = this.value.charAt(i2);
            if (cCharAt == '\"') {
                if (!z2) {
                    z3 = !z3;
                }
                this.buf.append(cCharAt);
            } else if (z2 || z3) {
                this.buf.append(cCharAt);
            } else {
                if (cCharAt == '\\') {
                    this.buf.append(cCharAt);
                    z2 = true;
                } else {
                    if (cCharAt == this.separator) {
                        break;
                    }
                    this.buf.append(cCharAt);
                }
                i2++;
            }
            z2 = false;
            i2++;
        }
        this.index = i2;
        return this.buf.toString();
    }

    public X500NameTokenizer(String str, char c2) {
        this.buf = new StringBuffer();
        this.value = str;
        this.index = -1;
        this.separator = c2;
    }
}

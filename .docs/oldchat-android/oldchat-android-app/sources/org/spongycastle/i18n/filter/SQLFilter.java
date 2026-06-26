package org.spongycastle.i18n.filter;

/* JADX INFO: loaded from: classes.dex */
public class SQLFilter implements Filter {
    @Override // org.spongycastle.i18n.filter.Filter
    public String doFilter(String str) {
        int i2;
        StringBuffer stringBuffer = new StringBuffer(str);
        int i3 = 0;
        while (i3 < stringBuffer.length()) {
            char cCharAt = stringBuffer.charAt(i3);
            if (cCharAt == '\n') {
                i2 = i3 + 1;
                stringBuffer.replace(i3, i2, "\\n");
            } else if (cCharAt == '\r') {
                i2 = i3 + 1;
                stringBuffer.replace(i3, i2, "\\r");
            } else if (cCharAt == '\"') {
                i2 = i3 + 1;
                stringBuffer.replace(i3, i2, "\\\"");
            } else if (cCharAt == '\'') {
                i2 = i3 + 1;
                stringBuffer.replace(i3, i2, "\\'");
            } else if (cCharAt == '-') {
                i2 = i3 + 1;
                stringBuffer.replace(i3, i2, "\\-");
            } else if (cCharAt == '/') {
                i2 = i3 + 1;
                stringBuffer.replace(i3, i2, "\\/");
            } else if (cCharAt == ';') {
                i2 = i3 + 1;
                stringBuffer.replace(i3, i2, "\\;");
            } else if (cCharAt == '=') {
                i2 = i3 + 1;
                stringBuffer.replace(i3, i2, "\\=");
            } else if (cCharAt != '\\') {
                i3++;
            } else {
                i2 = i3 + 1;
                stringBuffer.replace(i3, i2, "\\\\");
            }
            i3 = i2;
            i3++;
        }
        return stringBuffer.toString();
    }

    @Override // org.spongycastle.i18n.filter.Filter
    public String doFilterUrl(String str) {
        return doFilter(str);
    }
}

package L;

import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public abstract class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f413a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final boolean f414b;

    static {
        String strName = Charset.defaultCharset().name();
        f413a = strName;
        f414b = "SJIS".equalsIgnoreCase(strName) || "EUC_JP".equalsIgnoreCase(strName);
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x00bc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(byte[] r21, java.util.Map r22) {
        /*
            Method dump skipped, instruction units count: 321
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: L.l.a(byte[], java.util.Map):java.lang.String");
    }
}

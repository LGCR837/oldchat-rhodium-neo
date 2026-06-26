package org.spongycastle.crypto.tls;

/* JADX INFO: loaded from: classes.dex */
public class AlertLevel {
    public static final short fatal = 2;
    public static final short warning = 1;

    public static String getName(short s2) {
        return s2 != 1 ? s2 != 2 ? "UNKNOWN" : "fatal" : "warning";
    }

    public static String getText(short s2) {
        return getName(s2) + "(" + ((int) s2) + ")";
    }
}

package org.spongycastle.crypto.tls;

/* JADX INFO: loaded from: classes.dex */
public class HeartbeatMessageType {
    public static final short heartbeat_request = 1;
    public static final short heartbeat_response = 2;

    public static boolean isValid(short s2) {
        return s2 >= 1 && s2 <= 2;
    }
}

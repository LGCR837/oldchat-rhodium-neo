package org.spongycastle.crypto.tls;

/* JADX INFO: loaded from: classes.dex */
public interface DatagramTransport {
    void close();

    int getReceiveLimit();

    int getSendLimit();

    int receive(byte[] bArr, int i2, int i3, int i4);

    void send(byte[] bArr, int i2, int i3);
}

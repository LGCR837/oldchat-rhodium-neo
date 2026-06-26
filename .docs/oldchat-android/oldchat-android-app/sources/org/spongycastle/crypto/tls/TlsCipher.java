package org.spongycastle.crypto.tls;

/* JADX INFO: loaded from: classes.dex */
public interface TlsCipher {
    byte[] decodeCiphertext(long j2, short s2, byte[] bArr, int i2, int i3);

    byte[] encodePlaintext(long j2, short s2, byte[] bArr, int i2, int i3);

    int getPlaintextLimit(int i2);
}

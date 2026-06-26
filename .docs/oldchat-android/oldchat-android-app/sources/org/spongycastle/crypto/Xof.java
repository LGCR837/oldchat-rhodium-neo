package org.spongycastle.crypto;

/* JADX INFO: loaded from: classes.dex */
public interface Xof extends ExtendedDigest {
    int doFinal(byte[] bArr, int i2, int i3);

    int doOutput(byte[] bArr, int i2, int i3);
}

package org.spongycastle.crypto;

/* JADX INFO: loaded from: classes.dex */
public interface SignerWithRecovery extends Signer {
    byte[] getRecoveredMessage();

    boolean hasFullMessage();

    void updateWithRecoveredMessage(byte[] bArr);
}

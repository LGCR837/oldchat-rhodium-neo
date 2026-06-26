package org.spongycastle.pqc.crypto;

import org.spongycastle.crypto.CipherParameters;

/* JADX INFO: loaded from: classes.dex */
public interface MessageSigner {
    byte[] generateSignature(byte[] bArr);

    void init(boolean z2, CipherParameters cipherParameters);

    boolean verifySignature(byte[] bArr, byte[] bArr2);
}

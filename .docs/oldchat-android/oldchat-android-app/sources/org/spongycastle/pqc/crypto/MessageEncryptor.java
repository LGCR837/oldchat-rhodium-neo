package org.spongycastle.pqc.crypto;

import org.spongycastle.crypto.CipherParameters;

/* JADX INFO: loaded from: classes.dex */
public interface MessageEncryptor {
    void init(boolean z2, CipherParameters cipherParameters);

    byte[] messageDecrypt(byte[] bArr);

    byte[] messageEncrypt(byte[] bArr);
}

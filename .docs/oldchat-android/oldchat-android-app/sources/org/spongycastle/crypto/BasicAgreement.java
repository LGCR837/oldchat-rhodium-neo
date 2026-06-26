package org.spongycastle.crypto;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public interface BasicAgreement {
    BigInteger calculateAgreement(CipherParameters cipherParameters);

    int getFieldSize();

    void init(CipherParameters cipherParameters);
}

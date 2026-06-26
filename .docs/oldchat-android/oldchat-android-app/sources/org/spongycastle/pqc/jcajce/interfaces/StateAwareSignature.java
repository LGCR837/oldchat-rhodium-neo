package org.spongycastle.pqc.jcajce.interfaces;

import java.nio.ByteBuffer;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.Certificate;

/* JADX INFO: loaded from: classes.dex */
public interface StateAwareSignature {
    String getAlgorithm();

    PrivateKey getUpdatedPrivateKey();

    void initSign(PrivateKey privateKey);

    void initSign(PrivateKey privateKey, SecureRandom secureRandom);

    void initVerify(PublicKey publicKey);

    void initVerify(Certificate certificate);

    int sign(byte[] bArr, int i2, int i3);

    byte[] sign();

    void update(byte b2);

    void update(ByteBuffer byteBuffer);

    void update(byte[] bArr);

    void update(byte[] bArr, int i2, int i3);

    boolean verify(byte[] bArr);

    boolean verify(byte[] bArr, int i2, int i3);
}

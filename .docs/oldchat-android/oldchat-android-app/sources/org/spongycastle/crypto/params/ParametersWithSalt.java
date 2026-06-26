package org.spongycastle.crypto.params;

import org.spongycastle.crypto.CipherParameters;

/* JADX INFO: loaded from: classes.dex */
public class ParametersWithSalt implements CipherParameters {
    private CipherParameters parameters;
    private byte[] salt;

    public ParametersWithSalt(CipherParameters cipherParameters, byte[] bArr) {
        this(cipherParameters, bArr, 0, bArr.length);
    }

    public CipherParameters getParameters() {
        return this.parameters;
    }

    public byte[] getSalt() {
        return this.salt;
    }

    public ParametersWithSalt(CipherParameters cipherParameters, byte[] bArr, int i2, int i3) {
        byte[] bArr2 = new byte[i3];
        this.salt = bArr2;
        this.parameters = cipherParameters;
        System.arraycopy(bArr, i2, bArr2, 0, i3);
    }
}

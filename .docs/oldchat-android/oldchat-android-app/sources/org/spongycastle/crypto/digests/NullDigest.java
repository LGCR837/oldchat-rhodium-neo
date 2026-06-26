package org.spongycastle.crypto.digests;

import java.io.ByteArrayOutputStream;
import org.spongycastle.crypto.Digest;

/* JADX INFO: loaded from: classes.dex */
public class NullDigest implements Digest {
    private ByteArrayOutputStream bOut = new ByteArrayOutputStream();

    @Override // org.spongycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i2) {
        byte[] byteArray = this.bOut.toByteArray();
        System.arraycopy(byteArray, 0, bArr, i2, byteArray.length);
        reset();
        return byteArray.length;
    }

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return "NULL";
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return this.bOut.size();
    }

    @Override // org.spongycastle.crypto.Digest
    public void reset() {
        this.bOut.reset();
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte b2) {
        this.bOut.write(b2);
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte[] bArr, int i2, int i3) {
        this.bOut.write(bArr, i2, i3);
    }
}

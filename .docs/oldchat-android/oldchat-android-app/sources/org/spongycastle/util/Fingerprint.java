package org.spongycastle.util;

import org.spongycastle.crypto.digests.SHA512tDigest;
import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: loaded from: classes.dex */
public class Fingerprint {
    private static char[] encodingTable = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private final byte[] fingerprint;

    public Fingerprint(byte[] bArr) {
        this.fingerprint = calculateFingerprint(bArr);
    }

    public static byte[] calculateFingerprint(byte[] bArr) {
        SHA512tDigest sHA512tDigest = new SHA512tDigest(CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256);
        sHA512tDigest.update(bArr, 0, bArr.length);
        byte[] bArr2 = new byte[sHA512tDigest.getDigestSize()];
        sHA512tDigest.doFinal(bArr2, 0);
        return bArr2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Fingerprint) {
            return Arrays.areEqual(((Fingerprint) obj).fingerprint, this.fingerprint);
        }
        return false;
    }

    public byte[] getFingerprint() {
        return Arrays.clone(this.fingerprint);
    }

    public int hashCode() {
        return Arrays.hashCode(this.fingerprint);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 != this.fingerprint.length; i2++) {
            if (i2 > 0) {
                stringBuffer.append(":");
            }
            stringBuffer.append(encodingTable[(this.fingerprint[i2] >>> 4) & 15]);
            stringBuffer.append(encodingTable[this.fingerprint[i2] & 15]);
        }
        return stringBuffer.toString();
    }
}

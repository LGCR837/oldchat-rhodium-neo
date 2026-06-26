package org.spongycastle.crypto.generators;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.PBEParametersGenerator;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

/* JADX INFO: loaded from: classes.dex */
public class PKCS5S1ParametersGenerator extends PBEParametersGenerator {
    private Digest digest;

    public PKCS5S1ParametersGenerator(Digest digest) {
        this.digest = digest;
    }

    private byte[] generateDerivedKey() {
        int digestSize = this.digest.getDigestSize();
        byte[] bArr = new byte[digestSize];
        Digest digest = this.digest;
        byte[] bArr2 = this.password;
        digest.update(bArr2, 0, bArr2.length);
        Digest digest2 = this.digest;
        byte[] bArr3 = this.salt;
        digest2.update(bArr3, 0, bArr3.length);
        this.digest.doFinal(bArr, 0);
        for (int i2 = 1; i2 < this.iterationCount; i2++) {
            this.digest.update(bArr, 0, digestSize);
            this.digest.doFinal(bArr, 0);
        }
        return bArr;
    }

    @Override // org.spongycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedMacParameters(int i2) {
        return generateDerivedParameters(i2);
    }

    @Override // org.spongycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedParameters(int i2) {
        int i3 = i2 / 8;
        if (i3 <= this.digest.getDigestSize()) {
            return new KeyParameter(generateDerivedKey(), 0, i3);
        }
        throw new IllegalArgumentException("Can't generate a derived key " + i3 + " bytes long.");
    }

    @Override // org.spongycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedParameters(int i2, int i3) {
        int i4 = i2 / 8;
        int i5 = i3 / 8;
        int i6 = i4 + i5;
        if (i6 <= this.digest.getDigestSize()) {
            byte[] bArrGenerateDerivedKey = generateDerivedKey();
            return new ParametersWithIV(new KeyParameter(bArrGenerateDerivedKey, 0, i4), bArrGenerateDerivedKey, i4, i5);
        }
        throw new IllegalArgumentException("Can't generate a derived key " + i6 + " bytes long.");
    }
}

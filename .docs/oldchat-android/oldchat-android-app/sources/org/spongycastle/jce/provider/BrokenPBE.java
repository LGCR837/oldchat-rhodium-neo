package org.spongycastle.jce.provider;

import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.PBEParametersGenerator;
import org.spongycastle.crypto.digests.MD5Digest;
import org.spongycastle.crypto.digests.RIPEMD160Digest;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.generators.PKCS12ParametersGenerator;
import org.spongycastle.crypto.generators.PKCS5S1ParametersGenerator;
import org.spongycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.jcajce.provider.symmetric.util.BCPBEKey;

/* JADX INFO: loaded from: classes.dex */
public interface BrokenPBE {
    public static final int MD5 = 0;
    public static final int OLD_PKCS12 = 3;
    public static final int PKCS12 = 2;
    public static final int PKCS5S1 = 0;
    public static final int PKCS5S2 = 1;
    public static final int RIPEMD160 = 2;
    public static final int SHA1 = 1;

    public static class Util {
        private static PBEParametersGenerator makePBEGenerator(int i2, int i3) {
            if (i2 == 0) {
                if (i3 == 0) {
                    return new PKCS5S1ParametersGenerator(new MD5Digest());
                }
                if (i3 == 1) {
                    return new PKCS5S1ParametersGenerator(new SHA1Digest());
                }
                throw new IllegalStateException("PKCS5 scheme 1 only supports only MD5 and SHA1.");
            }
            if (i2 == 1) {
                return new PKCS5S2ParametersGenerator();
            }
            if (i2 == 3) {
                if (i3 == 0) {
                    return new OldPKCS12ParametersGenerator(new MD5Digest());
                }
                if (i3 == 1) {
                    return new OldPKCS12ParametersGenerator(new SHA1Digest());
                }
                if (i3 == 2) {
                    return new OldPKCS12ParametersGenerator(new RIPEMD160Digest());
                }
                throw new IllegalStateException("unknown digest scheme for PBE encryption.");
            }
            if (i3 == 0) {
                return new PKCS12ParametersGenerator(new MD5Digest());
            }
            if (i3 == 1) {
                return new PKCS12ParametersGenerator(new SHA1Digest());
            }
            if (i3 == 2) {
                return new PKCS12ParametersGenerator(new RIPEMD160Digest());
            }
            throw new IllegalStateException("unknown digest scheme for PBE encryption.");
        }

        public static CipherParameters makePBEMacParameters(BCPBEKey bCPBEKey, AlgorithmParameterSpec algorithmParameterSpec, int i2, int i3, int i4) {
            if (algorithmParameterSpec == null || !(algorithmParameterSpec instanceof PBEParameterSpec)) {
                throw new IllegalArgumentException("Need a PBEParameter spec with a PBE key.");
            }
            PBEParameterSpec pBEParameterSpec = (PBEParameterSpec) algorithmParameterSpec;
            PBEParametersGenerator pBEParametersGeneratorMakePBEGenerator = makePBEGenerator(i2, i3);
            byte[] encoded = bCPBEKey.getEncoded();
            pBEParametersGeneratorMakePBEGenerator.init(encoded, pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
            CipherParameters cipherParametersGenerateDerivedMacParameters = pBEParametersGeneratorMakePBEGenerator.generateDerivedMacParameters(i4);
            for (int i5 = 0; i5 != encoded.length; i5++) {
                encoded[i5] = 0;
            }
            return cipherParametersGenerateDerivedMacParameters;
        }

        public static CipherParameters makePBEParameters(BCPBEKey bCPBEKey, AlgorithmParameterSpec algorithmParameterSpec, int i2, int i3, String str, int i4, int i5) {
            if (algorithmParameterSpec == null || !(algorithmParameterSpec instanceof PBEParameterSpec)) {
                throw new IllegalArgumentException("Need a PBEParameter spec with a PBE key.");
            }
            PBEParameterSpec pBEParameterSpec = (PBEParameterSpec) algorithmParameterSpec;
            PBEParametersGenerator pBEParametersGeneratorMakePBEGenerator = makePBEGenerator(i2, i3);
            byte[] encoded = bCPBEKey.getEncoded();
            pBEParametersGeneratorMakePBEGenerator.init(encoded, pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
            CipherParameters cipherParametersGenerateDerivedParameters = i5 != 0 ? pBEParametersGeneratorMakePBEGenerator.generateDerivedParameters(i4, i5) : pBEParametersGeneratorMakePBEGenerator.generateDerivedParameters(i4);
            if (str.startsWith("DES")) {
                if (cipherParametersGenerateDerivedParameters instanceof ParametersWithIV) {
                    setOddParity(((KeyParameter) ((ParametersWithIV) cipherParametersGenerateDerivedParameters).getParameters()).getKey());
                } else {
                    setOddParity(((KeyParameter) cipherParametersGenerateDerivedParameters).getKey());
                }
            }
            for (int i6 = 0; i6 != encoded.length; i6++) {
                encoded[i6] = 0;
            }
            return cipherParametersGenerateDerivedParameters;
        }

        private static void setOddParity(byte[] bArr) {
            for (int i2 = 0; i2 < bArr.length; i2++) {
                byte b2 = bArr[i2];
                bArr[i2] = (byte) ((((b2 >> 7) ^ ((((((b2 >> 1) ^ (b2 >> 2)) ^ (b2 >> 3)) ^ (b2 >> 4)) ^ (b2 >> 5)) ^ (b2 >> 6))) ^ 1) | (b2 & 254));
            }
        }
    }
}

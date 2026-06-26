package org.spongycastle.pqc.jcajce.provider.mceliece;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.pqc.crypto.mceliece.McElieceCipher;
import org.spongycastle.pqc.crypto.mceliece.McElieceKeyParameters;
import org.spongycastle.pqc.jcajce.provider.util.AsymmetricBlockCipher;

/* JADX INFO: loaded from: classes.dex */
public class McEliecePKCSCipherSpi extends AsymmetricBlockCipher implements PKCSObjectIdentifiers, X509ObjectIdentifiers {
    private McElieceCipher cipher;

    public static class McEliecePKCS extends McEliecePKCSCipherSpi {
        public McEliecePKCS() {
            super(new McElieceCipher());
        }
    }

    public McEliecePKCSCipherSpi(McElieceCipher mcElieceCipher) {
        this.cipher = mcElieceCipher;
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public int getKeySize(Key key) {
        return this.cipher.getKeySize(key instanceof PublicKey ? (McElieceKeyParameters) McElieceKeysToParams.generatePublicKeyParameter((PublicKey) key) : (McElieceKeyParameters) McElieceKeysToParams.generatePrivateKeyParameter((PrivateKey) key));
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public String getName() {
        return "McEliecePKCS";
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.AsymmetricBlockCipher
    public void initCipherDecrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec) throws InvalidKeyException {
        this.cipher.init(false, McElieceKeysToParams.generatePrivateKeyParameter((PrivateKey) key));
        McElieceCipher mcElieceCipher = this.cipher;
        this.maxPlainTextSize = mcElieceCipher.maxPlainTextSize;
        this.cipherTextSize = mcElieceCipher.cipherTextSize;
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.AsymmetricBlockCipher
    public void initCipherEncrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        this.cipher.init(true, new ParametersWithRandom(McElieceKeysToParams.generatePublicKeyParameter((PublicKey) key), secureRandom));
        McElieceCipher mcElieceCipher = this.cipher;
        this.maxPlainTextSize = mcElieceCipher.maxPlainTextSize;
        this.cipherTextSize = mcElieceCipher.cipherTextSize;
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.AsymmetricBlockCipher
    public byte[] messageDecrypt(byte[] bArr) {
        try {
            return this.cipher.messageDecrypt(bArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.AsymmetricBlockCipher
    public byte[] messageEncrypt(byte[] bArr) {
        try {
            return this.cipher.messageEncrypt(bArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}

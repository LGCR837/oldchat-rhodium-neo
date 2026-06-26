package org.spongycastle.crypto.signers;

import java.io.IOException;
import java.util.Hashtable;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.DigestInfo;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.encodings.PKCS1Encoding;
import org.spongycastle.crypto.engines.RSABlindedEngine;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import org.spongycastle.pqc.jcajce.spec.SPHINCS256KeyGenParameterSpec;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class RSADigestSigner implements Signer {
    private static final Hashtable oidMap;
    private final AlgorithmIdentifier algId;
    private final Digest digest;
    private boolean forSigning;
    private final AsymmetricBlockCipher rsaEngine;

    static {
        Hashtable hashtable = new Hashtable();
        oidMap = hashtable;
        hashtable.put("RIPEMD128", TeleTrusTObjectIdentifiers.ripemd128);
        hashtable.put("RIPEMD160", TeleTrusTObjectIdentifiers.ripemd160);
        hashtable.put("RIPEMD256", TeleTrusTObjectIdentifiers.ripemd256);
        hashtable.put(McElieceCCA2KeyGenParameterSpec.SHA1, X509ObjectIdentifiers.id_SHA1);
        hashtable.put(McElieceCCA2KeyGenParameterSpec.SHA224, NISTObjectIdentifiers.id_sha224);
        hashtable.put(McElieceCCA2KeyGenParameterSpec.SHA256, NISTObjectIdentifiers.id_sha256);
        hashtable.put(McElieceCCA2KeyGenParameterSpec.SHA384, NISTObjectIdentifiers.id_sha384);
        hashtable.put(McElieceCCA2KeyGenParameterSpec.SHA512, NISTObjectIdentifiers.id_sha512);
        hashtable.put("SHA-512/224", NISTObjectIdentifiers.id_sha512_224);
        hashtable.put("SHA-512/256", NISTObjectIdentifiers.id_sha512_256);
        hashtable.put("SHA3-224", NISTObjectIdentifiers.id_sha3_224);
        hashtable.put(SPHINCS256KeyGenParameterSpec.SHA3_256, NISTObjectIdentifiers.id_sha3_256);
        hashtable.put("SHA3-384", NISTObjectIdentifiers.id_sha3_384);
        hashtable.put("SHA3-512", NISTObjectIdentifiers.id_sha3_512);
        hashtable.put("MD2", PKCSObjectIdentifiers.md2);
        hashtable.put("MD4", PKCSObjectIdentifiers.md4);
        hashtable.put("MD5", PKCSObjectIdentifiers.md5);
    }

    public RSADigestSigner(Digest digest) {
        this(digest, (ASN1ObjectIdentifier) oidMap.get(digest.getAlgorithmName()));
    }

    private byte[] derEncode(byte[] bArr) {
        return new DigestInfo(this.algId, bArr).getEncoded(ASN1Encoding.DER);
    }

    @Override // org.spongycastle.crypto.Signer
    public byte[] generateSignature() throws CryptoException {
        if (!this.forSigning) {
            throw new IllegalStateException("RSADigestSigner not initialised for signature generation.");
        }
        byte[] bArr = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(bArr, 0);
        try {
            byte[] bArrDerEncode = derEncode(bArr);
            return this.rsaEngine.processBlock(bArrDerEncode, 0, bArrDerEncode.length);
        } catch (IOException e2) {
            throw new CryptoException("unable to encode signature: " + e2.getMessage(), e2);
        }
    }

    public String getAlgorithmName() {
        return this.digest.getAlgorithmName() + "withRSA";
    }

    @Override // org.spongycastle.crypto.Signer
    public void init(boolean z2, CipherParameters cipherParameters) {
        this.forSigning = z2;
        AsymmetricKeyParameter asymmetricKeyParameter = cipherParameters instanceof ParametersWithRandom ? (AsymmetricKeyParameter) ((ParametersWithRandom) cipherParameters).getParameters() : (AsymmetricKeyParameter) cipherParameters;
        if (z2 && !asymmetricKeyParameter.isPrivate()) {
            throw new IllegalArgumentException("signing requires private key");
        }
        if (!z2 && asymmetricKeyParameter.isPrivate()) {
            throw new IllegalArgumentException("verification requires public key");
        }
        reset();
        this.rsaEngine.init(z2, cipherParameters);
    }

    @Override // org.spongycastle.crypto.Signer
    public void reset() {
        this.digest.reset();
    }

    @Override // org.spongycastle.crypto.Signer
    public void update(byte b2) {
        this.digest.update(b2);
    }

    @Override // org.spongycastle.crypto.Signer
    public boolean verifySignature(byte[] bArr) {
        byte[] bArrProcessBlock;
        byte[] bArrDerEncode;
        if (this.forSigning) {
            throw new IllegalStateException("RSADigestSigner not initialised for verification");
        }
        int digestSize = this.digest.getDigestSize();
        byte[] bArr2 = new byte[digestSize];
        this.digest.doFinal(bArr2, 0);
        try {
            bArrProcessBlock = this.rsaEngine.processBlock(bArr, 0, bArr.length);
            bArrDerEncode = derEncode(bArr2);
        } catch (Exception unused) {
        }
        if (bArrProcessBlock.length == bArrDerEncode.length) {
            return Arrays.constantTimeAreEqual(bArrProcessBlock, bArrDerEncode);
        }
        if (bArrProcessBlock.length != bArrDerEncode.length - 2) {
            Arrays.constantTimeAreEqual(bArrDerEncode, bArrDerEncode);
            return false;
        }
        int length = (bArrProcessBlock.length - digestSize) - 2;
        int length2 = (bArrDerEncode.length - digestSize) - 2;
        bArrDerEncode[1] = (byte) (bArrDerEncode[1] - 2);
        bArrDerEncode[3] = (byte) (bArrDerEncode[3] - 2);
        int i2 = 0;
        for (int i3 = 0; i3 < digestSize; i3++) {
            i2 |= bArrProcessBlock[length + i3] ^ bArrDerEncode[length2 + i3];
        }
        for (int i4 = 0; i4 < length; i4++) {
            i2 |= bArrProcessBlock[i4] ^ bArrDerEncode[i4];
        }
        return i2 == 0;
    }

    public RSADigestSigner(Digest digest, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.rsaEngine = new PKCS1Encoding(new RSABlindedEngine());
        this.digest = digest;
        this.algId = new AlgorithmIdentifier(aSN1ObjectIdentifier, DERNull.INSTANCE);
    }

    @Override // org.spongycastle.crypto.Signer
    public void update(byte[] bArr, int i2, int i3) {
        this.digest.update(bArr, i2, i3);
    }
}

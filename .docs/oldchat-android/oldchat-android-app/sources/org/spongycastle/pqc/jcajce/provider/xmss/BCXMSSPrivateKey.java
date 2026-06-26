package org.spongycastle.pqc.jcajce.provider.xmss;

import java.io.IOException;
import java.security.PrivateKey;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.pqc.asn1.PQCObjectIdentifiers;
import org.spongycastle.pqc.asn1.XMSSKeyParams;
import org.spongycastle.pqc.asn1.XMSSPrivateKey;
import org.spongycastle.pqc.crypto.xmss.BDS;
import org.spongycastle.pqc.crypto.xmss.XMSSParameters;
import org.spongycastle.pqc.crypto.xmss.XMSSPrivateKeyParameters;
import org.spongycastle.pqc.crypto.xmss.XMSSUtil;
import org.spongycastle.pqc.jcajce.interfaces.XMSSKey;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class BCXMSSPrivateKey implements PrivateKey, XMSSKey {
    private final XMSSPrivateKeyParameters keyParams;
    private final ASN1ObjectIdentifier treeDigest;

    public BCXMSSPrivateKey(ASN1ObjectIdentifier aSN1ObjectIdentifier, XMSSPrivateKeyParameters xMSSPrivateKeyParameters) {
        this.treeDigest = aSN1ObjectIdentifier;
        this.keyParams = xMSSPrivateKeyParameters;
    }

    private XMSSPrivateKey createKeyStructure() {
        byte[] byteArray = this.keyParams.toByteArray();
        int digestSize = this.keyParams.getParameters().getDigestSize();
        int height = this.keyParams.getParameters().getHeight();
        int iBytesToXBigEndian = (int) XMSSUtil.bytesToXBigEndian(byteArray, 0, 4);
        if (!XMSSUtil.isIndexValid(height, iBytesToXBigEndian)) {
            throw new IllegalArgumentException("index out of bounds");
        }
        byte[] bArrExtractBytesAtOffset = XMSSUtil.extractBytesAtOffset(byteArray, 4, digestSize);
        int i2 = 4 + digestSize;
        byte[] bArrExtractBytesAtOffset2 = XMSSUtil.extractBytesAtOffset(byteArray, i2, digestSize);
        int i3 = i2 + digestSize;
        byte[] bArrExtractBytesAtOffset3 = XMSSUtil.extractBytesAtOffset(byteArray, i3, digestSize);
        int i4 = i3 + digestSize;
        byte[] bArrExtractBytesAtOffset4 = XMSSUtil.extractBytesAtOffset(byteArray, i4, digestSize);
        int i5 = i4 + digestSize;
        return new XMSSPrivateKey(iBytesToXBigEndian, bArrExtractBytesAtOffset, bArrExtractBytesAtOffset2, bArrExtractBytesAtOffset3, bArrExtractBytesAtOffset4, XMSSUtil.extractBytesAtOffset(byteArray, i5, byteArray.length - i5));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BCXMSSPrivateKey)) {
            return false;
        }
        BCXMSSPrivateKey bCXMSSPrivateKey = (BCXMSSPrivateKey) obj;
        return this.treeDigest.equals(bCXMSSPrivateKey.treeDigest) && Arrays.areEqual(this.keyParams.toByteArray(), bCXMSSPrivateKey.keyParams.toByteArray());
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "XMSS";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return new PrivateKeyInfo(new AlgorithmIdentifier(PQCObjectIdentifiers.xmss, new XMSSKeyParams(this.keyParams.getParameters().getHeight(), new AlgorithmIdentifier(this.treeDigest))), createKeyStructure()).getEncoded();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    @Override // org.spongycastle.pqc.jcajce.interfaces.XMSSKey
    public int getHeight() {
        return this.keyParams.getParameters().getHeight();
    }

    public CipherParameters getKeyParams() {
        return this.keyParams;
    }

    @Override // org.spongycastle.pqc.jcajce.interfaces.XMSSKey
    public String getTreeDigest() {
        return DigestUtil.getXMSSDigestName(this.treeDigest);
    }

    public ASN1ObjectIdentifier getTreeDigestOID() {
        return this.treeDigest;
    }

    public int hashCode() {
        return this.treeDigest.hashCode() + (Arrays.hashCode(this.keyParams.toByteArray()) * 37);
    }

    public BCXMSSPrivateKey(PrivateKeyInfo privateKeyInfo) throws IOException {
        XMSSKeyParams xMSSKeyParams = XMSSKeyParams.getInstance(privateKeyInfo.getPrivateKeyAlgorithm().getParameters());
        ASN1ObjectIdentifier algorithm = xMSSKeyParams.getTreeDigest().getAlgorithm();
        this.treeDigest = algorithm;
        XMSSPrivateKey xMSSPrivateKey = XMSSPrivateKey.getInstance(privateKeyInfo.parsePrivateKey());
        try {
            XMSSPrivateKeyParameters.Builder builderWithRoot = new XMSSPrivateKeyParameters.Builder(new XMSSParameters(xMSSKeyParams.getHeight(), DigestUtil.getDigest(algorithm))).withIndex(xMSSPrivateKey.getIndex()).withSecretKeySeed(xMSSPrivateKey.getSecretKeySeed()).withSecretKeyPRF(xMSSPrivateKey.getSecretKeyPRF()).withPublicSeed(xMSSPrivateKey.getPublicSeed()).withRoot(xMSSPrivateKey.getRoot());
            if (xMSSPrivateKey.getBdsState() != null) {
                builderWithRoot.withBDSState((BDS) XMSSUtil.deserialize(xMSSPrivateKey.getBdsState()));
            }
            this.keyParams = builderWithRoot.build();
        } catch (ClassNotFoundException e2) {
            throw new IOException("ClassNotFoundException processing BDS state: " + e2.getMessage());
        }
    }
}

package org.spongycastle.crypto.agreement.kdf;

import java.io.IOException;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public class DHKEKGenerator implements DerivationFunction {
    private ASN1ObjectIdentifier algorithm;
    private final Digest digest;
    private int keySize;
    private byte[] partyAInfo;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private byte[] f7247z;

    public DHKEKGenerator(Digest digest) {
        this.digest = digest;
    }

    @Override // org.spongycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int i2, int i3) {
        boolean z2;
        int i4 = i3;
        int i5 = i2;
        if (bArr.length - i4 < i5) {
            throw new OutputLengthException("output buffer too small");
        }
        long j2 = i4;
        int digestSize = this.digest.getDigestSize();
        if (j2 > 8589934591L) {
            throw new IllegalArgumentException("Output length too large");
        }
        long j3 = digestSize;
        int i6 = (int) (((j2 + j3) - 1) / j3);
        byte[] bArr2 = new byte[this.digest.getDigestSize()];
        int i7 = 0;
        int i8 = 0;
        int i9 = 1;
        while (i8 < i6) {
            Digest digest = this.digest;
            byte[] bArr3 = this.f7247z;
            digest.update(bArr3, i7, bArr3.length);
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
            aSN1EncodableVector2.add(this.algorithm);
            aSN1EncodableVector2.add(new DEROctetString(Pack.intToBigEndian(i9)));
            aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
            if (this.partyAInfo != null) {
                z2 = true;
                aSN1EncodableVector.add(new DERTaggedObject(true, i7, new DEROctetString(this.partyAInfo)));
            } else {
                z2 = true;
            }
            aSN1EncodableVector.add(new DERTaggedObject(z2, 2, new DEROctetString(Pack.intToBigEndian(this.keySize))));
            try {
                byte[] encoded = new DERSequence(aSN1EncodableVector).getEncoded(ASN1Encoding.DER);
                this.digest.update(encoded, 0, encoded.length);
                this.digest.doFinal(bArr2, 0);
                if (i4 > digestSize) {
                    System.arraycopy(bArr2, 0, bArr, i5, digestSize);
                    i5 += digestSize;
                    i4 -= digestSize;
                } else {
                    System.arraycopy(bArr2, 0, bArr, i5, i4);
                }
                i9++;
                i8++;
                i7 = 0;
            } catch (IOException e2) {
                throw new IllegalArgumentException("unable to encode parameter info: " + e2.getMessage());
            }
        }
        this.digest.reset();
        return (int) j2;
    }

    public Digest getDigest() {
        return this.digest;
    }

    @Override // org.spongycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        DHKDFParameters dHKDFParameters = (DHKDFParameters) derivationParameters;
        this.algorithm = dHKDFParameters.getAlgorithm();
        this.keySize = dHKDFParameters.getKeySize();
        this.f7247z = dHKDFParameters.getZ();
        this.partyAInfo = dHKDFParameters.getExtraInfo();
    }
}

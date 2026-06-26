package org.spongycastle.pqc.asn1;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class XMSSPrivateKey extends ASN1Object {
    private final byte[] bdsState;
    private final int index;
    private final byte[] publicSeed;
    private final byte[] root;
    private final byte[] secretKeyPRF;
    private final byte[] secretKeySeed;

    public XMSSPrivateKey(int i2, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        this.index = i2;
        this.secretKeySeed = Arrays.clone(bArr);
        this.secretKeyPRF = Arrays.clone(bArr2);
        this.publicSeed = Arrays.clone(bArr3);
        this.root = Arrays.clone(bArr4);
        this.bdsState = Arrays.clone(bArr5);
    }

    public static XMSSPrivateKey getInstance(Object obj) {
        if (obj instanceof XMSSPrivateKey) {
            return (XMSSPrivateKey) obj;
        }
        if (obj != null) {
            return new XMSSPrivateKey(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public byte[] getBdsState() {
        return Arrays.clone(this.bdsState);
    }

    public int getIndex() {
        return this.index;
    }

    public byte[] getPublicSeed() {
        return Arrays.clone(this.publicSeed);
    }

    public byte[] getRoot() {
        return Arrays.clone(this.root);
    }

    public byte[] getSecretKeyPRF() {
        return Arrays.clone(this.secretKeyPRF);
    }

    public byte[] getSecretKeySeed() {
        return Arrays.clone(this.secretKeySeed);
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(0L));
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        aSN1EncodableVector2.add(new ASN1Integer(this.index));
        aSN1EncodableVector2.add(new DEROctetString(this.secretKeySeed));
        aSN1EncodableVector2.add(new DEROctetString(this.secretKeyPRF));
        aSN1EncodableVector2.add(new DEROctetString(this.publicSeed));
        aSN1EncodableVector2.add(new DEROctetString(this.root));
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        aSN1EncodableVector.add(new DERTaggedObject(true, 0, new DEROctetString(this.bdsState)));
        return new DERSequence(aSN1EncodableVector);
    }

    private XMSSPrivateKey(ASN1Sequence aSN1Sequence) {
        if (ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0)).getValue().equals(BigInteger.valueOf(0L))) {
            if (aSN1Sequence.size() != 2 && aSN1Sequence.size() != 3) {
                throw new IllegalArgumentException("key sequence wrong size");
            }
            ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
            this.index = ASN1Integer.getInstance(aSN1Sequence2.getObjectAt(0)).getValue().intValue();
            this.secretKeySeed = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence2.getObjectAt(1)).getOctets());
            this.secretKeyPRF = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence2.getObjectAt(2)).getOctets());
            this.publicSeed = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence2.getObjectAt(3)).getOctets());
            this.root = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence2.getObjectAt(4)).getOctets());
            if (aSN1Sequence.size() == 3) {
                this.bdsState = Arrays.clone(ASN1OctetString.getInstance(ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(2)), true).getOctets());
                return;
            } else {
                this.bdsState = null;
                return;
            }
        }
        throw new IllegalArgumentException("unknown version of sequence");
    }
}

package org.spongycastle.pqc.asn1;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.pqc.crypto.rainbow.Layer;
import org.spongycastle.pqc.crypto.rainbow.util.RainbowUtil;

/* JADX INFO: loaded from: classes.dex */
public class RainbowPrivateKey extends ASN1Object {
    private byte[] b1;
    private byte[] b2;
    private byte[][] invA1;
    private byte[][] invA2;
    private Layer[] layers;
    private ASN1ObjectIdentifier oid;
    private ASN1Integer version;
    private byte[] vi;

    private RainbowPrivateKey(ASN1Sequence aSN1Sequence) {
        int i2 = 0;
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1Integer) {
            this.version = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        } else {
            this.oid = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        }
        ASN1Sequence aSN1Sequence2 = (ASN1Sequence) aSN1Sequence.getObjectAt(1);
        this.invA1 = new byte[aSN1Sequence2.size()][];
        for (int i3 = 0; i3 < aSN1Sequence2.size(); i3++) {
            this.invA1[i3] = ((ASN1OctetString) aSN1Sequence2.getObjectAt(i3)).getOctets();
        }
        this.b1 = ((ASN1OctetString) ((ASN1Sequence) aSN1Sequence.getObjectAt(2)).getObjectAt(0)).getOctets();
        ASN1Sequence aSN1Sequence3 = (ASN1Sequence) aSN1Sequence.getObjectAt(3);
        this.invA2 = new byte[aSN1Sequence3.size()][];
        for (int i4 = 0; i4 < aSN1Sequence3.size(); i4++) {
            this.invA2[i4] = ((ASN1OctetString) aSN1Sequence3.getObjectAt(i4)).getOctets();
        }
        this.b2 = ((ASN1OctetString) ((ASN1Sequence) aSN1Sequence.getObjectAt(4)).getObjectAt(0)).getOctets();
        this.vi = ((ASN1OctetString) ((ASN1Sequence) aSN1Sequence.getObjectAt(5)).getObjectAt(0)).getOctets();
        ASN1Sequence aSN1Sequence4 = (ASN1Sequence) aSN1Sequence.getObjectAt(6);
        byte[][][][] bArr = new byte[aSN1Sequence4.size()][][][];
        byte[][][][] bArr2 = new byte[aSN1Sequence4.size()][][][];
        byte[][][] bArr3 = new byte[aSN1Sequence4.size()][][];
        byte[][] bArr4 = new byte[aSN1Sequence4.size()][];
        int i5 = 0;
        while (i5 < aSN1Sequence4.size()) {
            ASN1Sequence aSN1Sequence5 = (ASN1Sequence) aSN1Sequence4.getObjectAt(i5);
            ASN1Sequence aSN1Sequence6 = (ASN1Sequence) aSN1Sequence5.getObjectAt(i2);
            bArr[i5] = new byte[aSN1Sequence6.size()][][];
            for (int i6 = 0; i6 < aSN1Sequence6.size(); i6++) {
                ASN1Sequence aSN1Sequence7 = (ASN1Sequence) aSN1Sequence6.getObjectAt(i6);
                bArr[i5][i6] = new byte[aSN1Sequence7.size()][];
                for (int i7 = 0; i7 < aSN1Sequence7.size(); i7++) {
                    bArr[i5][i6][i7] = ((ASN1OctetString) aSN1Sequence7.getObjectAt(i7)).getOctets();
                }
            }
            ASN1Sequence aSN1Sequence8 = (ASN1Sequence) aSN1Sequence5.getObjectAt(1);
            bArr2[i5] = new byte[aSN1Sequence8.size()][][];
            for (int i8 = 0; i8 < aSN1Sequence8.size(); i8++) {
                ASN1Sequence aSN1Sequence9 = (ASN1Sequence) aSN1Sequence8.getObjectAt(i8);
                bArr2[i5][i8] = new byte[aSN1Sequence9.size()][];
                for (int i9 = 0; i9 < aSN1Sequence9.size(); i9++) {
                    bArr2[i5][i8][i9] = ((ASN1OctetString) aSN1Sequence9.getObjectAt(i9)).getOctets();
                }
            }
            ASN1Sequence aSN1Sequence10 = (ASN1Sequence) aSN1Sequence5.getObjectAt(2);
            bArr3[i5] = new byte[aSN1Sequence10.size()][];
            for (int i10 = 0; i10 < aSN1Sequence10.size(); i10++) {
                bArr3[i5][i10] = ((ASN1OctetString) aSN1Sequence10.getObjectAt(i10)).getOctets();
            }
            bArr4[i5] = ((ASN1OctetString) aSN1Sequence5.getObjectAt(3)).getOctets();
            i5++;
            i2 = 0;
        }
        int length = this.vi.length - 1;
        this.layers = new Layer[length];
        int i11 = 0;
        while (i11 < length) {
            byte[] bArr5 = this.vi;
            int i12 = i11 + 1;
            this.layers[i11] = new Layer(bArr5[i11], bArr5[i12], RainbowUtil.convertArray(bArr[i11]), RainbowUtil.convertArray(bArr2[i11]), RainbowUtil.convertArray(bArr3[i11]), RainbowUtil.convertArray(bArr4[i11]));
            i11 = i12;
        }
    }

    public static RainbowPrivateKey getInstance(Object obj) {
        if (obj instanceof RainbowPrivateKey) {
            return (RainbowPrivateKey) obj;
        }
        if (obj != null) {
            return new RainbowPrivateKey(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public short[] getB1() {
        return RainbowUtil.convertArray(this.b1);
    }

    public short[] getB2() {
        return RainbowUtil.convertArray(this.b2);
    }

    public short[][] getInvA1() {
        return RainbowUtil.convertArray(this.invA1);
    }

    public short[][] getInvA2() {
        return RainbowUtil.convertArray(this.invA2);
    }

    public Layer[] getLayers() {
        return this.layers;
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public int[] getVi() {
        return RainbowUtil.convertArraytoInt(this.vi);
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1Integer aSN1Integer = this.version;
        if (aSN1Integer != null) {
            aSN1EncodableVector.add(aSN1Integer);
        } else {
            aSN1EncodableVector.add(this.oid);
        }
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        int i2 = 0;
        while (true) {
            byte[][] bArr = this.invA1;
            if (i2 >= bArr.length) {
                break;
            }
            aSN1EncodableVector2.add(new DEROctetString(bArr[i2]));
            i2++;
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
        aSN1EncodableVector3.add(new DEROctetString(this.b1));
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector3));
        ASN1EncodableVector aSN1EncodableVector4 = new ASN1EncodableVector();
        int i3 = 0;
        while (true) {
            byte[][] bArr2 = this.invA2;
            if (i3 >= bArr2.length) {
                break;
            }
            aSN1EncodableVector4.add(new DEROctetString(bArr2[i3]));
            i3++;
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector4));
        ASN1EncodableVector aSN1EncodableVector5 = new ASN1EncodableVector();
        aSN1EncodableVector5.add(new DEROctetString(this.b2));
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector5));
        ASN1EncodableVector aSN1EncodableVector6 = new ASN1EncodableVector();
        aSN1EncodableVector6.add(new DEROctetString(this.vi));
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector6));
        ASN1EncodableVector aSN1EncodableVector7 = new ASN1EncodableVector();
        for (int i4 = 0; i4 < this.layers.length; i4++) {
            ASN1EncodableVector aSN1EncodableVector8 = new ASN1EncodableVector();
            byte[][][] bArrConvertArray = RainbowUtil.convertArray(this.layers[i4].getCoeffAlpha());
            ASN1EncodableVector aSN1EncodableVector9 = new ASN1EncodableVector();
            for (byte[][] bArr3 : bArrConvertArray) {
                ASN1EncodableVector aSN1EncodableVector10 = new ASN1EncodableVector();
                int i5 = 0;
                while (true) {
                    if (i5 < bArr3.length) {
                        aSN1EncodableVector10.add(new DEROctetString(bArr3[i5]));
                        i5++;
                    }
                }
                aSN1EncodableVector9.add(new DERSequence(aSN1EncodableVector10));
            }
            aSN1EncodableVector8.add(new DERSequence(aSN1EncodableVector9));
            byte[][][] bArrConvertArray2 = RainbowUtil.convertArray(this.layers[i4].getCoeffBeta());
            ASN1EncodableVector aSN1EncodableVector11 = new ASN1EncodableVector();
            for (byte[][] bArr4 : bArrConvertArray2) {
                ASN1EncodableVector aSN1EncodableVector12 = new ASN1EncodableVector();
                int i6 = 0;
                while (true) {
                    if (i6 < bArr4.length) {
                        aSN1EncodableVector12.add(new DEROctetString(bArr4[i6]));
                        i6++;
                    }
                }
                aSN1EncodableVector11.add(new DERSequence(aSN1EncodableVector12));
            }
            aSN1EncodableVector8.add(new DERSequence(aSN1EncodableVector11));
            byte[][] bArrConvertArray3 = RainbowUtil.convertArray(this.layers[i4].getCoeffGamma());
            ASN1EncodableVector aSN1EncodableVector13 = new ASN1EncodableVector();
            for (byte[] bArr5 : bArrConvertArray3) {
                aSN1EncodableVector13.add(new DEROctetString(bArr5));
            }
            aSN1EncodableVector8.add(new DERSequence(aSN1EncodableVector13));
            aSN1EncodableVector8.add(new DEROctetString(RainbowUtil.convertArray(this.layers[i4].getCoeffEta())));
            aSN1EncodableVector7.add(new DERSequence(aSN1EncodableVector8));
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector7));
        return new DERSequence(aSN1EncodableVector);
    }

    public RainbowPrivateKey(short[][] sArr, short[] sArr2, short[][] sArr3, short[] sArr4, int[] iArr, Layer[] layerArr) {
        this.version = new ASN1Integer(1L);
        this.invA1 = RainbowUtil.convertArray(sArr);
        this.b1 = RainbowUtil.convertArray(sArr2);
        this.invA2 = RainbowUtil.convertArray(sArr3);
        this.b2 = RainbowUtil.convertArray(sArr4);
        this.vi = RainbowUtil.convertIntArray(iArr);
        this.layers = layerArr;
    }
}

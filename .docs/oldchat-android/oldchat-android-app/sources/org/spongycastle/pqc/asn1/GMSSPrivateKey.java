package org.spongycastle.pqc.asn1;

import java.math.BigInteger;
import java.util.Vector;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.pqc.crypto.gmss.GMSSLeaf;
import org.spongycastle.pqc.crypto.gmss.GMSSParameters;
import org.spongycastle.pqc.crypto.gmss.GMSSRootCalc;
import org.spongycastle.pqc.crypto.gmss.GMSSRootSig;
import org.spongycastle.pqc.crypto.gmss.Treehash;

/* JADX INFO: loaded from: classes.dex */
public class GMSSPrivateKey extends ASN1Object {
    private ASN1Primitive primitive;

    private GMSSPrivateKey(ASN1Sequence aSN1Sequence) {
        ASN1Sequence aSN1Sequence2 = (ASN1Sequence) aSN1Sequence.getObjectAt(0);
        int[] iArr = new int[aSN1Sequence2.size()];
        for (int i2 = 0; i2 < aSN1Sequence2.size(); i2++) {
            iArr[i2] = checkBigIntegerInIntRange(aSN1Sequence2.getObjectAt(i2));
        }
        ASN1Sequence aSN1Sequence3 = (ASN1Sequence) aSN1Sequence.getObjectAt(1);
        int size = aSN1Sequence3.size();
        byte[][] bArr = new byte[size][];
        for (int i3 = 0; i3 < size; i3++) {
            bArr[i3] = ((DEROctetString) aSN1Sequence3.getObjectAt(i3)).getOctets();
        }
        ASN1Sequence aSN1Sequence4 = (ASN1Sequence) aSN1Sequence.getObjectAt(2);
        int size2 = aSN1Sequence4.size();
        byte[][] bArr2 = new byte[size2][];
        for (int i4 = 0; i4 < size2; i4++) {
            bArr2[i4] = ((DEROctetString) aSN1Sequence4.getObjectAt(i4)).getOctets();
        }
        ASN1Sequence aSN1Sequence5 = (ASN1Sequence) aSN1Sequence.getObjectAt(3);
        int size3 = aSN1Sequence5.size();
        byte[][][] bArr3 = new byte[size3][][];
        for (int i5 = 0; i5 < size3; i5++) {
            ASN1Sequence aSN1Sequence6 = (ASN1Sequence) aSN1Sequence5.getObjectAt(i5);
            bArr3[i5] = new byte[aSN1Sequence6.size()][];
            int i6 = 0;
            while (true) {
                byte[][] bArr4 = bArr3[i5];
                if (i6 < bArr4.length) {
                    bArr4[i6] = ((DEROctetString) aSN1Sequence6.getObjectAt(i6)).getOctets();
                    i6++;
                }
            }
        }
        ASN1Sequence aSN1Sequence7 = (ASN1Sequence) aSN1Sequence.getObjectAt(4);
        int size4 = aSN1Sequence7.size();
        byte[][][] bArr5 = new byte[size4][][];
        for (int i7 = 0; i7 < size4; i7++) {
            ASN1Sequence aSN1Sequence8 = (ASN1Sequence) aSN1Sequence7.getObjectAt(i7);
            bArr5[i7] = new byte[aSN1Sequence8.size()][];
            int i8 = 0;
            while (true) {
                byte[][] bArr6 = bArr5[i7];
                if (i8 < bArr6.length) {
                    bArr6[i8] = ((DEROctetString) aSN1Sequence8.getObjectAt(i8)).getOctets();
                    i8++;
                }
            }
        }
        Treehash[][] treehashArr = new Treehash[((ASN1Sequence) aSN1Sequence.getObjectAt(5)).size()][];
    }

    private static int checkBigIntegerInIntRange(ASN1Encodable aSN1Encodable) {
        BigInteger value = ((ASN1Integer) aSN1Encodable).getValue();
        if (value.compareTo(BigInteger.valueOf(2147483647L)) <= 0 && value.compareTo(BigInteger.valueOf(-2147483648L)) >= 0) {
            return value.intValue();
        }
        throw new IllegalArgumentException("BigInteger not in Range: " + value.toString());
    }

    private ASN1Primitive encode(int[] iArr, byte[][] bArr, byte[][] bArr2, byte[][][] bArr3, byte[][][] bArr4, byte[][][] bArr5, Treehash[][] treehashArr, Treehash[][] treehashArr2, Vector[] vectorArr, Vector[] vectorArr2, Vector[][] vectorArr3, Vector[][] vectorArr4, GMSSLeaf[] gMSSLeafArr, GMSSLeaf[] gMSSLeafArr2, GMSSLeaf[] gMSSLeafArr3, int[] iArr2, byte[][] bArr6, GMSSRootCalc[] gMSSRootCalcArr, byte[][] bArr7, GMSSRootSig[] gMSSRootSigArr, GMSSParameters gMSSParameters, AlgorithmIdentifier[] algorithmIdentifierArr) {
        Treehash[][] treehashArr3 = treehashArr;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        for (int i2 : iArr) {
            aSN1EncodableVector2.add(new ASN1Integer(i2));
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
        for (byte[] bArr8 : bArr) {
            aSN1EncodableVector3.add(new DEROctetString(bArr8));
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector3));
        ASN1EncodableVector aSN1EncodableVector4 = new ASN1EncodableVector();
        for (byte[] bArr9 : bArr2) {
            aSN1EncodableVector4.add(new DEROctetString(bArr9));
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector4));
        ASN1EncodableVector aSN1EncodableVector5 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector6 = new ASN1EncodableVector();
        for (byte[][] bArr10 : bArr3) {
            int i3 = 0;
            while (true) {
                if (i3 < bArr10.length) {
                    aSN1EncodableVector5.add(new DEROctetString(bArr10[i3]));
                    i3++;
                }
            }
            aSN1EncodableVector6.add(new DERSequence(aSN1EncodableVector5));
            aSN1EncodableVector5 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector6));
        ASN1EncodableVector aSN1EncodableVector7 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector8 = new ASN1EncodableVector();
        for (byte[][] bArr11 : bArr4) {
            int i4 = 0;
            while (true) {
                if (i4 < bArr11.length) {
                    aSN1EncodableVector7.add(new DEROctetString(bArr11[i4]));
                    i4++;
                }
            }
            aSN1EncodableVector8.add(new DERSequence(aSN1EncodableVector7));
            aSN1EncodableVector7 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector8));
        ASN1EncodableVector aSN1EncodableVector9 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector10 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector11 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector12 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector13 = new ASN1EncodableVector();
        int i5 = 0;
        while (i5 < treehashArr3.length) {
            int i6 = 0;
            while (i6 < treehashArr3[i5].length) {
                aSN1EncodableVector11.add(new DERSequence(algorithmIdentifierArr[0]));
                int i7 = treehashArr3[i5][i6].getStatInt()[1];
                aSN1EncodableVector12.add(new DEROctetString(treehashArr3[i5][i6].getStatByte()[0]));
                aSN1EncodableVector12.add(new DEROctetString(treehashArr3[i5][i6].getStatByte()[1]));
                aSN1EncodableVector12.add(new DEROctetString(treehashArr3[i5][i6].getStatByte()[2]));
                for (int i8 = 0; i8 < i7; i8++) {
                    aSN1EncodableVector12.add(new DEROctetString(treehashArr3[i5][i6].getStatByte()[i8 + 3]));
                }
                aSN1EncodableVector11.add(new DERSequence(aSN1EncodableVector12));
                aSN1EncodableVector12 = new ASN1EncodableVector();
                aSN1EncodableVector13.add(new ASN1Integer(treehashArr3[i5][i6].getStatInt()[0]));
                aSN1EncodableVector13.add(new ASN1Integer(i7));
                aSN1EncodableVector13.add(new ASN1Integer(treehashArr3[i5][i6].getStatInt()[2]));
                aSN1EncodableVector13.add(new ASN1Integer(treehashArr3[i5][i6].getStatInt()[3]));
                aSN1EncodableVector13.add(new ASN1Integer(treehashArr3[i5][i6].getStatInt()[4]));
                aSN1EncodableVector13.add(new ASN1Integer(treehashArr3[i5][i6].getStatInt()[5]));
                int i9 = 0;
                while (i9 < i7) {
                    aSN1EncodableVector13.add(new ASN1Integer(treehashArr3[i5][i6].getStatInt()[i9 + 6]));
                    i9++;
                    treehashArr3 = treehashArr;
                }
                aSN1EncodableVector11.add(new DERSequence(aSN1EncodableVector13));
                aSN1EncodableVector13 = new ASN1EncodableVector();
                aSN1EncodableVector10.add(new DERSequence(aSN1EncodableVector11));
                aSN1EncodableVector11 = new ASN1EncodableVector();
                i6++;
                treehashArr3 = treehashArr;
            }
            aSN1EncodableVector9.add(new DERSequence(aSN1EncodableVector10));
            aSN1EncodableVector10 = new ASN1EncodableVector();
            i5++;
            treehashArr3 = treehashArr;
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector9));
        ASN1EncodableVector aSN1EncodableVector14 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector15 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector16 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector17 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector18 = new ASN1EncodableVector();
        for (int i10 = 0; i10 < treehashArr2.length; i10++) {
            for (int i11 = 0; i11 < treehashArr2[i10].length; i11++) {
                aSN1EncodableVector16.add(new DERSequence(algorithmIdentifierArr[0]));
                int i12 = treehashArr2[i10][i11].getStatInt()[1];
                aSN1EncodableVector17.add(new DEROctetString(treehashArr2[i10][i11].getStatByte()[0]));
                aSN1EncodableVector17.add(new DEROctetString(treehashArr2[i10][i11].getStatByte()[1]));
                aSN1EncodableVector17.add(new DEROctetString(treehashArr2[i10][i11].getStatByte()[2]));
                for (int i13 = 0; i13 < i12; i13++) {
                    aSN1EncodableVector17.add(new DEROctetString(treehashArr2[i10][i11].getStatByte()[i13 + 3]));
                }
                aSN1EncodableVector16.add(new DERSequence(aSN1EncodableVector17));
                aSN1EncodableVector17 = new ASN1EncodableVector();
                aSN1EncodableVector18.add(new ASN1Integer(treehashArr2[i10][i11].getStatInt()[0]));
                aSN1EncodableVector18.add(new ASN1Integer(i12));
                aSN1EncodableVector18.add(new ASN1Integer(treehashArr2[i10][i11].getStatInt()[2]));
                aSN1EncodableVector18.add(new ASN1Integer(treehashArr2[i10][i11].getStatInt()[3]));
                aSN1EncodableVector18.add(new ASN1Integer(treehashArr2[i10][i11].getStatInt()[4]));
                aSN1EncodableVector18.add(new ASN1Integer(treehashArr2[i10][i11].getStatInt()[5]));
                for (int i14 = 0; i14 < i12; i14++) {
                    aSN1EncodableVector18.add(new ASN1Integer(treehashArr2[i10][i11].getStatInt()[i14 + 6]));
                }
                aSN1EncodableVector16.add(new DERSequence(aSN1EncodableVector18));
                aSN1EncodableVector18 = new ASN1EncodableVector();
                aSN1EncodableVector15.add(new DERSequence(aSN1EncodableVector16));
                aSN1EncodableVector16 = new ASN1EncodableVector();
            }
            aSN1EncodableVector14.add(new DERSequence(new DERSequence(aSN1EncodableVector15)));
            aSN1EncodableVector15 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector14));
        ASN1EncodableVector aSN1EncodableVector19 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector20 = new ASN1EncodableVector();
        for (byte[][] bArr12 : bArr5) {
            int i15 = 0;
            while (true) {
                if (i15 < bArr12.length) {
                    aSN1EncodableVector19.add(new DEROctetString(bArr12[i15]));
                    i15++;
                }
            }
            aSN1EncodableVector20.add(new DERSequence(aSN1EncodableVector19));
            aSN1EncodableVector19 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector20));
        ASN1EncodableVector aSN1EncodableVector21 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector22 = new ASN1EncodableVector();
        for (int i16 = 0; i16 < vectorArr.length; i16++) {
            for (int i17 = 0; i17 < vectorArr[i16].size(); i17++) {
                aSN1EncodableVector21.add(new DEROctetString((byte[]) vectorArr[i16].elementAt(i17)));
            }
            aSN1EncodableVector22.add(new DERSequence(aSN1EncodableVector21));
            aSN1EncodableVector21 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector22));
        ASN1EncodableVector aSN1EncodableVector23 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector24 = new ASN1EncodableVector();
        for (int i18 = 0; i18 < vectorArr2.length; i18++) {
            for (int i19 = 0; i19 < vectorArr2[i18].size(); i19++) {
                aSN1EncodableVector23.add(new DEROctetString((byte[]) vectorArr2[i18].elementAt(i19)));
            }
            aSN1EncodableVector24.add(new DERSequence(aSN1EncodableVector23));
            aSN1EncodableVector23 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector24));
        ASN1EncodableVector aSN1EncodableVector25 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector26 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector27 = new ASN1EncodableVector();
        for (int i20 = 0; i20 < vectorArr3.length; i20++) {
            for (int i21 = 0; i21 < vectorArr3[i20].length; i21++) {
                for (int i22 = 0; i22 < vectorArr3[i20][i21].size(); i22++) {
                    aSN1EncodableVector25.add(new DEROctetString((byte[]) vectorArr3[i20][i21].elementAt(i22)));
                }
                aSN1EncodableVector26.add(new DERSequence(aSN1EncodableVector25));
                aSN1EncodableVector25 = new ASN1EncodableVector();
            }
            aSN1EncodableVector27.add(new DERSequence(aSN1EncodableVector26));
            aSN1EncodableVector26 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector27));
        ASN1EncodableVector aSN1EncodableVector28 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector29 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector30 = new ASN1EncodableVector();
        for (int i23 = 0; i23 < vectorArr4.length; i23++) {
            for (int i24 = 0; i24 < vectorArr4[i23].length; i24++) {
                for (int i25 = 0; i25 < vectorArr4[i23][i24].size(); i25++) {
                    aSN1EncodableVector28.add(new DEROctetString((byte[]) vectorArr4[i23][i24].elementAt(i25)));
                }
                aSN1EncodableVector29.add(new DERSequence(aSN1EncodableVector28));
                aSN1EncodableVector28 = new ASN1EncodableVector();
            }
            aSN1EncodableVector30.add(new DERSequence(aSN1EncodableVector29));
            aSN1EncodableVector29 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector30));
        ASN1EncodableVector aSN1EncodableVector31 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector32 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector33 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector34 = new ASN1EncodableVector();
        for (int i26 = 0; i26 < gMSSLeafArr.length; i26++) {
            aSN1EncodableVector32.add(new DERSequence(algorithmIdentifierArr[0]));
            byte[][] statByte = gMSSLeafArr[i26].getStatByte();
            aSN1EncodableVector33.add(new DEROctetString(statByte[0]));
            aSN1EncodableVector33.add(new DEROctetString(statByte[1]));
            aSN1EncodableVector33.add(new DEROctetString(statByte[2]));
            aSN1EncodableVector33.add(new DEROctetString(statByte[3]));
            aSN1EncodableVector32.add(new DERSequence(aSN1EncodableVector33));
            aSN1EncodableVector33 = new ASN1EncodableVector();
            int[] statInt = gMSSLeafArr[i26].getStatInt();
            aSN1EncodableVector34.add(new ASN1Integer(statInt[0]));
            aSN1EncodableVector34.add(new ASN1Integer(statInt[1]));
            aSN1EncodableVector34.add(new ASN1Integer(statInt[2]));
            aSN1EncodableVector34.add(new ASN1Integer(statInt[3]));
            aSN1EncodableVector32.add(new DERSequence(aSN1EncodableVector34));
            aSN1EncodableVector34 = new ASN1EncodableVector();
            aSN1EncodableVector31.add(new DERSequence(aSN1EncodableVector32));
            aSN1EncodableVector32 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector31));
        ASN1EncodableVector aSN1EncodableVector35 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector36 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector37 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector38 = new ASN1EncodableVector();
        for (int i27 = 0; i27 < gMSSLeafArr2.length; i27++) {
            aSN1EncodableVector36.add(new DERSequence(algorithmIdentifierArr[0]));
            byte[][] statByte2 = gMSSLeafArr2[i27].getStatByte();
            aSN1EncodableVector37.add(new DEROctetString(statByte2[0]));
            aSN1EncodableVector37.add(new DEROctetString(statByte2[1]));
            aSN1EncodableVector37.add(new DEROctetString(statByte2[2]));
            aSN1EncodableVector37.add(new DEROctetString(statByte2[3]));
            aSN1EncodableVector36.add(new DERSequence(aSN1EncodableVector37));
            aSN1EncodableVector37 = new ASN1EncodableVector();
            int[] statInt2 = gMSSLeafArr2[i27].getStatInt();
            aSN1EncodableVector38.add(new ASN1Integer(statInt2[0]));
            aSN1EncodableVector38.add(new ASN1Integer(statInt2[1]));
            aSN1EncodableVector38.add(new ASN1Integer(statInt2[2]));
            aSN1EncodableVector38.add(new ASN1Integer(statInt2[3]));
            aSN1EncodableVector36.add(new DERSequence(aSN1EncodableVector38));
            aSN1EncodableVector38 = new ASN1EncodableVector();
            aSN1EncodableVector35.add(new DERSequence(aSN1EncodableVector36));
            aSN1EncodableVector36 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector35));
        ASN1EncodableVector aSN1EncodableVector39 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector40 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector41 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector42 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector43 = aSN1EncodableVector;
        for (int i28 = 0; i28 < gMSSLeafArr3.length; i28++) {
            aSN1EncodableVector40.add(new DERSequence(algorithmIdentifierArr[0]));
            byte[][] statByte3 = gMSSLeafArr3[i28].getStatByte();
            aSN1EncodableVector41.add(new DEROctetString(statByte3[0]));
            aSN1EncodableVector41.add(new DEROctetString(statByte3[1]));
            aSN1EncodableVector41.add(new DEROctetString(statByte3[2]));
            aSN1EncodableVector41.add(new DEROctetString(statByte3[3]));
            aSN1EncodableVector40.add(new DERSequence(aSN1EncodableVector41));
            aSN1EncodableVector41 = new ASN1EncodableVector();
            int[] statInt3 = gMSSLeafArr3[i28].getStatInt();
            aSN1EncodableVector42.add(new ASN1Integer(statInt3[0]));
            aSN1EncodableVector42.add(new ASN1Integer(statInt3[1]));
            aSN1EncodableVector42.add(new ASN1Integer(statInt3[2]));
            aSN1EncodableVector42.add(new ASN1Integer(statInt3[3]));
            aSN1EncodableVector40.add(new DERSequence(aSN1EncodableVector42));
            aSN1EncodableVector42 = new ASN1EncodableVector();
            aSN1EncodableVector39.add(new DERSequence(aSN1EncodableVector40));
            aSN1EncodableVector40 = new ASN1EncodableVector();
        }
        aSN1EncodableVector43.add(new DERSequence(aSN1EncodableVector39));
        ASN1EncodableVector aSN1EncodableVector44 = new ASN1EncodableVector();
        for (int i29 : iArr2) {
            aSN1EncodableVector44.add(new ASN1Integer(i29));
        }
        aSN1EncodableVector43.add(new DERSequence(aSN1EncodableVector44));
        ASN1EncodableVector aSN1EncodableVector45 = new ASN1EncodableVector();
        for (byte[] bArr13 : bArr6) {
            aSN1EncodableVector45.add(new DEROctetString(bArr13));
        }
        aSN1EncodableVector43.add(new DERSequence(aSN1EncodableVector45));
        ASN1EncodableVector aSN1EncodableVector46 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector47 = new ASN1EncodableVector();
        new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector48 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector49 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector50 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector51 = new ASN1EncodableVector();
        int i30 = 0;
        while (i30 < gMSSRootCalcArr.length) {
            aSN1EncodableVector47.add(new DERSequence(algorithmIdentifierArr[0]));
            new ASN1EncodableVector();
            int i31 = gMSSRootCalcArr[i30].getStatInt()[0];
            int i32 = gMSSRootCalcArr[i30].getStatInt()[7];
            aSN1EncodableVector48.add(new DEROctetString(gMSSRootCalcArr[i30].getStatByte()[0]));
            int i33 = 0;
            while (i33 < i31) {
                i33++;
                aSN1EncodableVector48.add(new DEROctetString(gMSSRootCalcArr[i30].getStatByte()[i33]));
            }
            for (int i34 = 0; i34 < i32; i34++) {
                aSN1EncodableVector48.add(new DEROctetString(gMSSRootCalcArr[i30].getStatByte()[i31 + 1 + i34]));
            }
            aSN1EncodableVector47.add(new DERSequence(aSN1EncodableVector48));
            ASN1EncodableVector aSN1EncodableVector52 = new ASN1EncodableVector();
            aSN1EncodableVector49.add(new ASN1Integer(i31));
            aSN1EncodableVector49.add(new ASN1Integer(gMSSRootCalcArr[i30].getStatInt()[1]));
            aSN1EncodableVector49.add(new ASN1Integer(gMSSRootCalcArr[i30].getStatInt()[2]));
            aSN1EncodableVector49.add(new ASN1Integer(gMSSRootCalcArr[i30].getStatInt()[3]));
            aSN1EncodableVector49.add(new ASN1Integer(gMSSRootCalcArr[i30].getStatInt()[4]));
            aSN1EncodableVector49.add(new ASN1Integer(gMSSRootCalcArr[i30].getStatInt()[5]));
            aSN1EncodableVector49.add(new ASN1Integer(gMSSRootCalcArr[i30].getStatInt()[6]));
            aSN1EncodableVector49.add(new ASN1Integer(i32));
            for (int i35 = 0; i35 < i31; i35++) {
                aSN1EncodableVector49.add(new ASN1Integer(gMSSRootCalcArr[i30].getStatInt()[i35 + 8]));
            }
            for (int i36 = 0; i36 < i32; i36++) {
                aSN1EncodableVector49.add(new ASN1Integer(gMSSRootCalcArr[i30].getStatInt()[i31 + 8 + i36]));
            }
            aSN1EncodableVector47.add(new DERSequence(aSN1EncodableVector49));
            ASN1EncodableVector aSN1EncodableVector53 = new ASN1EncodableVector();
            ASN1EncodableVector aSN1EncodableVector54 = new ASN1EncodableVector();
            ASN1EncodableVector aSN1EncodableVector55 = new ASN1EncodableVector();
            ASN1EncodableVector aSN1EncodableVector56 = new ASN1EncodableVector();
            if (gMSSRootCalcArr[i30].getTreehash() != null) {
                int i37 = 0;
                while (i37 < gMSSRootCalcArr[i30].getTreehash().length) {
                    aSN1EncodableVector54.add(new DERSequence(algorithmIdentifierArr[0]));
                    int i38 = gMSSRootCalcArr[i30].getTreehash()[i37].getStatInt()[1];
                    ASN1EncodableVector aSN1EncodableVector57 = aSN1EncodableVector52;
                    aSN1EncodableVector55.add(new DEROctetString(gMSSRootCalcArr[i30].getTreehash()[i37].getStatByte()[0]));
                    aSN1EncodableVector55.add(new DEROctetString(gMSSRootCalcArr[i30].getTreehash()[i37].getStatByte()[1]));
                    aSN1EncodableVector55.add(new DEROctetString(gMSSRootCalcArr[i30].getTreehash()[i37].getStatByte()[2]));
                    int i39 = 0;
                    while (i39 < i38) {
                        aSN1EncodableVector55.add(new DEROctetString(gMSSRootCalcArr[i30].getTreehash()[i37].getStatByte()[i39 + 3]));
                        i39++;
                        aSN1EncodableVector53 = aSN1EncodableVector53;
                    }
                    ASN1EncodableVector aSN1EncodableVector58 = aSN1EncodableVector53;
                    aSN1EncodableVector54.add(new DERSequence(aSN1EncodableVector55));
                    aSN1EncodableVector55 = new ASN1EncodableVector();
                    ASN1EncodableVector aSN1EncodableVector59 = aSN1EncodableVector43;
                    aSN1EncodableVector56.add(new ASN1Integer(gMSSRootCalcArr[i30].getTreehash()[i37].getStatInt()[0]));
                    aSN1EncodableVector56.add(new ASN1Integer(i38));
                    aSN1EncodableVector56.add(new ASN1Integer(gMSSRootCalcArr[i30].getTreehash()[i37].getStatInt()[2]));
                    aSN1EncodableVector56.add(new ASN1Integer(gMSSRootCalcArr[i30].getTreehash()[i37].getStatInt()[3]));
                    aSN1EncodableVector56.add(new ASN1Integer(gMSSRootCalcArr[i30].getTreehash()[i37].getStatInt()[4]));
                    aSN1EncodableVector56.add(new ASN1Integer(gMSSRootCalcArr[i30].getTreehash()[i37].getStatInt()[5]));
                    int i40 = 0;
                    while (i40 < i38) {
                        aSN1EncodableVector56.add(new ASN1Integer(gMSSRootCalcArr[i30].getTreehash()[i37].getStatInt()[i40 + 6]));
                        i40++;
                        i38 = i38;
                        aSN1EncodableVector59 = aSN1EncodableVector59;
                    }
                    aSN1EncodableVector54.add(new DERSequence(aSN1EncodableVector56));
                    aSN1EncodableVector56 = new ASN1EncodableVector();
                    aSN1EncodableVector50.add(new DERSequence(aSN1EncodableVector54));
                    aSN1EncodableVector54 = new ASN1EncodableVector();
                    i37++;
                    aSN1EncodableVector52 = aSN1EncodableVector57;
                    aSN1EncodableVector53 = aSN1EncodableVector58;
                    aSN1EncodableVector43 = aSN1EncodableVector59;
                }
            }
            ASN1EncodableVector aSN1EncodableVector60 = aSN1EncodableVector52;
            ASN1EncodableVector aSN1EncodableVector61 = aSN1EncodableVector53;
            ASN1EncodableVector aSN1EncodableVector62 = aSN1EncodableVector43;
            aSN1EncodableVector47.add(new DERSequence(aSN1EncodableVector50));
            aSN1EncodableVector50 = new ASN1EncodableVector();
            ASN1EncodableVector aSN1EncodableVector63 = new ASN1EncodableVector();
            if (gMSSRootCalcArr[i30].getRetain() != null) {
                for (int i41 = 0; i41 < gMSSRootCalcArr[i30].getRetain().length; i41++) {
                    for (int i42 = 0; i42 < gMSSRootCalcArr[i30].getRetain()[i41].size(); i42++) {
                        aSN1EncodableVector63.add(new DEROctetString((byte[]) gMSSRootCalcArr[i30].getRetain()[i41].elementAt(i42)));
                    }
                    aSN1EncodableVector51.add(new DERSequence(aSN1EncodableVector63));
                    aSN1EncodableVector63 = new ASN1EncodableVector();
                }
            }
            aSN1EncodableVector47.add(new DERSequence(aSN1EncodableVector51));
            aSN1EncodableVector51 = new ASN1EncodableVector();
            aSN1EncodableVector46.add(new DERSequence(aSN1EncodableVector47));
            aSN1EncodableVector47 = new ASN1EncodableVector();
            i30++;
            aSN1EncodableVector48 = aSN1EncodableVector60;
            aSN1EncodableVector49 = aSN1EncodableVector61;
            aSN1EncodableVector43 = aSN1EncodableVector62;
        }
        ASN1EncodableVector aSN1EncodableVector64 = aSN1EncodableVector43;
        aSN1EncodableVector64.add(new DERSequence(aSN1EncodableVector46));
        ASN1EncodableVector aSN1EncodableVector65 = new ASN1EncodableVector();
        for (byte[] bArr14 : bArr7) {
            aSN1EncodableVector65.add(new DEROctetString(bArr14));
        }
        aSN1EncodableVector64.add(new DERSequence(aSN1EncodableVector65));
        ASN1EncodableVector aSN1EncodableVector66 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector67 = new ASN1EncodableVector();
        new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector68 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector69 = new ASN1EncodableVector();
        for (int i43 = 0; i43 < gMSSRootSigArr.length; i43++) {
            aSN1EncodableVector67.add(new DERSequence(algorithmIdentifierArr[0]));
            new ASN1EncodableVector();
            aSN1EncodableVector68.add(new DEROctetString(gMSSRootSigArr[i43].getStatByte()[0]));
            aSN1EncodableVector68.add(new DEROctetString(gMSSRootSigArr[i43].getStatByte()[1]));
            aSN1EncodableVector68.add(new DEROctetString(gMSSRootSigArr[i43].getStatByte()[2]));
            aSN1EncodableVector68.add(new DEROctetString(gMSSRootSigArr[i43].getStatByte()[3]));
            aSN1EncodableVector68.add(new DEROctetString(gMSSRootSigArr[i43].getStatByte()[4]));
            aSN1EncodableVector67.add(new DERSequence(aSN1EncodableVector68));
            aSN1EncodableVector68 = new ASN1EncodableVector();
            aSN1EncodableVector69.add(new ASN1Integer(gMSSRootSigArr[i43].getStatInt()[0]));
            aSN1EncodableVector69.add(new ASN1Integer(gMSSRootSigArr[i43].getStatInt()[1]));
            aSN1EncodableVector69.add(new ASN1Integer(gMSSRootSigArr[i43].getStatInt()[2]));
            aSN1EncodableVector69.add(new ASN1Integer(gMSSRootSigArr[i43].getStatInt()[3]));
            aSN1EncodableVector69.add(new ASN1Integer(gMSSRootSigArr[i43].getStatInt()[4]));
            aSN1EncodableVector69.add(new ASN1Integer(gMSSRootSigArr[i43].getStatInt()[5]));
            aSN1EncodableVector69.add(new ASN1Integer(gMSSRootSigArr[i43].getStatInt()[6]));
            aSN1EncodableVector69.add(new ASN1Integer(gMSSRootSigArr[i43].getStatInt()[7]));
            aSN1EncodableVector69.add(new ASN1Integer(gMSSRootSigArr[i43].getStatInt()[8]));
            aSN1EncodableVector67.add(new DERSequence(aSN1EncodableVector69));
            aSN1EncodableVector69 = new ASN1EncodableVector();
            aSN1EncodableVector66.add(new DERSequence(aSN1EncodableVector67));
            aSN1EncodableVector67 = new ASN1EncodableVector();
        }
        aSN1EncodableVector64.add(new DERSequence(aSN1EncodableVector66));
        ASN1EncodableVector aSN1EncodableVector70 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector71 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector72 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector73 = new ASN1EncodableVector();
        for (int i44 = 0; i44 < gMSSParameters.getHeightOfTrees().length; i44++) {
            aSN1EncodableVector71.add(new ASN1Integer(gMSSParameters.getHeightOfTrees()[i44]));
            aSN1EncodableVector72.add(new ASN1Integer(gMSSParameters.getWinternitzParameter()[i44]));
            aSN1EncodableVector73.add(new ASN1Integer(gMSSParameters.getK()[i44]));
        }
        aSN1EncodableVector70.add(new ASN1Integer(gMSSParameters.getNumOfLayers()));
        aSN1EncodableVector70.add(new DERSequence(aSN1EncodableVector71));
        aSN1EncodableVector70.add(new DERSequence(aSN1EncodableVector72));
        aSN1EncodableVector70.add(new DERSequence(aSN1EncodableVector73));
        aSN1EncodableVector64.add(new DERSequence(aSN1EncodableVector70));
        ASN1EncodableVector aSN1EncodableVector74 = new ASN1EncodableVector();
        for (AlgorithmIdentifier algorithmIdentifier : algorithmIdentifierArr) {
            aSN1EncodableVector74.add(algorithmIdentifier);
        }
        aSN1EncodableVector64.add(new DERSequence(aSN1EncodableVector74));
        return new DERSequence(aSN1EncodableVector64);
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.primitive;
    }

    public GMSSPrivateKey(int[] iArr, byte[][] bArr, byte[][] bArr2, byte[][][] bArr3, byte[][][] bArr4, Treehash[][] treehashArr, Treehash[][] treehashArr2, Vector[] vectorArr, Vector[] vectorArr2, Vector[][] vectorArr3, Vector[][] vectorArr4, byte[][][] bArr5, GMSSLeaf[] gMSSLeafArr, GMSSLeaf[] gMSSLeafArr2, GMSSLeaf[] gMSSLeafArr3, int[] iArr2, byte[][] bArr6, GMSSRootCalc[] gMSSRootCalcArr, byte[][] bArr7, GMSSRootSig[] gMSSRootSigArr, GMSSParameters gMSSParameters, AlgorithmIdentifier algorithmIdentifier) {
        this.primitive = encode(iArr, bArr, bArr2, bArr3, bArr4, bArr5, treehashArr, treehashArr2, vectorArr, vectorArr2, vectorArr3, vectorArr4, gMSSLeafArr, gMSSLeafArr2, gMSSLeafArr3, iArr2, bArr6, gMSSRootCalcArr, bArr7, gMSSRootSigArr, gMSSParameters, new AlgorithmIdentifier[]{algorithmIdentifier});
    }
}

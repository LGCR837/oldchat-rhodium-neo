package org.spongycastle.asn1;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.asn1.eac.CertificateBody;
import org.spongycastle.util.io.Streams;

/* JADX INFO: loaded from: classes.dex */
public class ASN1InputStream extends FilterInputStream implements BERTags {
    private final boolean lazyEvaluate;
    private final int limit;
    private final byte[][] tmpBuffers;

    public ASN1InputStream(InputStream inputStream) {
        this(inputStream, StreamUtil.findLimit(inputStream));
    }

    public static ASN1Primitive createPrimitiveDERObject(int i2, DefiniteLengthInputStream definiteLengthInputStream, byte[][] bArr) throws IOException {
        if (i2 == 10) {
            return ASN1Enumerated.fromOctetString(getBuffer(definiteLengthInputStream, bArr));
        }
        if (i2 == 12) {
            return new DERUTF8String(definiteLengthInputStream.toByteArray());
        }
        if (i2 == 30) {
            return new DERBMPString(getBMPCharBuffer(definiteLengthInputStream));
        }
        switch (i2) {
            case 1:
                return ASN1Boolean.fromOctetString(getBuffer(definiteLengthInputStream, bArr));
            case 2:
                return new ASN1Integer(definiteLengthInputStream.toByteArray(), false);
            case 3:
                return ASN1BitString.fromInputStream(definiteLengthInputStream.getRemaining(), definiteLengthInputStream);
            case 4:
                return new DEROctetString(definiteLengthInputStream.toByteArray());
            case 5:
                return DERNull.INSTANCE;
            case 6:
                return ASN1ObjectIdentifier.fromOctetString(getBuffer(definiteLengthInputStream, bArr));
            default:
                switch (i2) {
                    case 18:
                        return new DERNumericString(definiteLengthInputStream.toByteArray());
                    case 19:
                        return new DERPrintableString(definiteLengthInputStream.toByteArray());
                    case 20:
                        return new DERT61String(definiteLengthInputStream.toByteArray());
                    case 21:
                        return new DERVideotexString(definiteLengthInputStream.toByteArray());
                    case 22:
                        return new DERIA5String(definiteLengthInputStream.toByteArray());
                    case 23:
                        return new ASN1UTCTime(definiteLengthInputStream.toByteArray());
                    case 24:
                        return new ASN1GeneralizedTime(definiteLengthInputStream.toByteArray());
                    case 25:
                        return new DERGraphicString(definiteLengthInputStream.toByteArray());
                    case 26:
                        return new DERVisibleString(definiteLengthInputStream.toByteArray());
                    case 27:
                        return new DERGeneralString(definiteLengthInputStream.toByteArray());
                    case 28:
                        return new DERUniversalString(definiteLengthInputStream.toByteArray());
                    default:
                        throw new IOException("unknown tag " + i2 + " encountered");
                }
        }
    }

    private static char[] getBMPCharBuffer(DefiniteLengthInputStream definiteLengthInputStream) throws IOException {
        int i2;
        int remaining = definiteLengthInputStream.getRemaining() / 2;
        char[] cArr = new char[remaining];
        for (int i3 = 0; i3 < remaining; i3++) {
            int i4 = definiteLengthInputStream.read();
            if (i4 < 0 || (i2 = definiteLengthInputStream.read()) < 0) {
                break;
            }
            cArr[i3] = (char) ((i4 << 8) | (i2 & 255));
        }
        return cArr;
    }

    private static byte[] getBuffer(DefiniteLengthInputStream definiteLengthInputStream, byte[][] bArr) {
        int remaining = definiteLengthInputStream.getRemaining();
        if (definiteLengthInputStream.getRemaining() >= bArr.length) {
            return definiteLengthInputStream.toByteArray();
        }
        byte[] bArr2 = bArr[remaining];
        if (bArr2 == null) {
            bArr2 = new byte[remaining];
            bArr[remaining] = bArr2;
        }
        Streams.readFully(definiteLengthInputStream, bArr2);
        return bArr2;
    }

    public static int readTagNumber(InputStream inputStream, int i2) throws IOException {
        int i3 = i2 & 31;
        if (i3 != 31) {
            return i3;
        }
        int i4 = inputStream.read();
        if ((i4 & CertificateBody.profileType) == 0) {
            throw new IOException("corrupted stream - invalid high tag number found");
        }
        int i5 = 0;
        while (i4 >= 0 && (i4 & 128) != 0) {
            i5 = ((i4 & CertificateBody.profileType) | i5) << 7;
            i4 = inputStream.read();
        }
        if (i4 >= 0) {
            return i5 | (i4 & CertificateBody.profileType);
        }
        throw new EOFException("EOF found inside tag value.");
    }

    public ASN1EncodableVector buildDEREncodableVector(DefiniteLengthInputStream definiteLengthInputStream) {
        return new ASN1InputStream(definiteLengthInputStream).buildEncodableVector();
    }

    public ASN1EncodableVector buildEncodableVector() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        while (true) {
            ASN1Primitive object = readObject();
            if (object == null) {
                return aSN1EncodableVector;
            }
            aSN1EncodableVector.add(object);
        }
    }

    public ASN1Primitive buildObject(int i2, int i3, int i4) throws IOException {
        boolean z2 = (i2 & 32) != 0;
        DefiniteLengthInputStream definiteLengthInputStream = new DefiniteLengthInputStream(this, i4);
        if ((i2 & 64) != 0) {
            return new DERApplicationSpecific(z2, i3, definiteLengthInputStream.toByteArray());
        }
        if ((i2 & 128) != 0) {
            return new ASN1StreamParser(definiteLengthInputStream).readTaggedObject(z2, i3);
        }
        if (!z2) {
            return createPrimitiveDERObject(i3, definiteLengthInputStream, this.tmpBuffers);
        }
        if (i3 == 4) {
            ASN1EncodableVector aSN1EncodableVectorBuildDEREncodableVector = buildDEREncodableVector(definiteLengthInputStream);
            int size = aSN1EncodableVectorBuildDEREncodableVector.size();
            ASN1OctetString[] aSN1OctetStringArr = new ASN1OctetString[size];
            for (int i5 = 0; i5 != size; i5++) {
                aSN1OctetStringArr[i5] = (ASN1OctetString) aSN1EncodableVectorBuildDEREncodableVector.get(i5);
            }
            return new BEROctetString(aSN1OctetStringArr);
        }
        if (i3 == 8) {
            return new DERExternal(buildDEREncodableVector(definiteLengthInputStream));
        }
        if (i3 == 16) {
            return this.lazyEvaluate ? new LazyEncodedSequence(definiteLengthInputStream.toByteArray()) : DERFactory.createSequence(buildDEREncodableVector(definiteLengthInputStream));
        }
        if (i3 == 17) {
            return DERFactory.createSet(buildDEREncodableVector(definiteLengthInputStream));
        }
        throw new IOException("unknown tag " + i3 + " encountered");
    }

    public int getLimit() {
        return this.limit;
    }

    public void readFully(byte[] bArr) throws EOFException {
        if (Streams.readFully(this, bArr) != bArr.length) {
            throw new EOFException("EOF encountered in middle of object");
        }
    }

    public int readLength() {
        return readLength(this, this.limit);
    }

    public ASN1Primitive readObject() {
        int i2 = read();
        if (i2 <= 0) {
            if (i2 != 0) {
                return null;
            }
            throw new IOException("unexpected end-of-contents marker");
        }
        int tagNumber = readTagNumber(this, i2);
        boolean z2 = (i2 & 32) != 0;
        int length = readLength();
        if (length >= 0) {
            try {
                return buildObject(i2, tagNumber, length);
            } catch (IllegalArgumentException e2) {
                throw new ASN1Exception("corrupted stream detected", e2);
            }
        }
        if (!z2) {
            throw new IOException("indefinite-length primitive encoding encountered");
        }
        ASN1StreamParser aSN1StreamParser = new ASN1StreamParser(new IndefiniteLengthInputStream(this, this.limit), this.limit);
        if ((i2 & 64) != 0) {
            return new BERApplicationSpecificParser(tagNumber, aSN1StreamParser).getLoadedObject();
        }
        if ((i2 & 128) != 0) {
            return new BERTaggedObjectParser(true, tagNumber, aSN1StreamParser).getLoadedObject();
        }
        if (tagNumber == 4) {
            return new BEROctetStringParser(aSN1StreamParser).getLoadedObject();
        }
        if (tagNumber == 8) {
            return new DERExternalParser(aSN1StreamParser).getLoadedObject();
        }
        if (tagNumber == 16) {
            return new BERSequenceParser(aSN1StreamParser).getLoadedObject();
        }
        if (tagNumber == 17) {
            return new BERSetParser(aSN1StreamParser).getLoadedObject();
        }
        throw new IOException("unknown BER object encountered");
    }

    public ASN1InputStream(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), bArr.length);
    }

    public static int readLength(InputStream inputStream, int i2) throws IOException {
        int i3 = inputStream.read();
        if (i3 < 0) {
            throw new EOFException("EOF found when length expected");
        }
        if (i3 == 128) {
            return -1;
        }
        if (i3 <= 127) {
            return i3;
        }
        int i4 = i3 & CertificateBody.profileType;
        if (i4 > 4) {
            throw new IOException("DER length more than 4 bytes: " + i4);
        }
        int i5 = 0;
        for (int i6 = 0; i6 < i4; i6++) {
            int i7 = inputStream.read();
            if (i7 < 0) {
                throw new EOFException("EOF found reading length");
            }
            i5 = (i5 << 8) + i7;
        }
        if (i5 < 0) {
            throw new IOException("corrupted stream - negative length found");
        }
        if (i5 < i2) {
            return i5;
        }
        throw new IOException("corrupted stream - out of bounds length found");
    }

    public ASN1InputStream(byte[] bArr, boolean z2) {
        this(new ByteArrayInputStream(bArr), bArr.length, z2);
    }

    public ASN1InputStream(InputStream inputStream, int i2) {
        this(inputStream, i2, false);
    }

    public ASN1InputStream(InputStream inputStream, boolean z2) {
        this(inputStream, StreamUtil.findLimit(inputStream), z2);
    }

    public ASN1InputStream(InputStream inputStream, int i2, boolean z2) {
        super(inputStream);
        this.limit = i2;
        this.lazyEvaluate = z2;
        this.tmpBuffers = new byte[11][];
    }
}

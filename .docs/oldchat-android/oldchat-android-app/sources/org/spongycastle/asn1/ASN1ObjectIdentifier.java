package org.spongycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.spongycastle.asn1.eac.CertificateBody;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ASN1ObjectIdentifier extends ASN1Primitive {
    private static final long LONG_LIMIT = 72057594037927808L;
    private static final ConcurrentMap<OidHandle, ASN1ObjectIdentifier> pool = new ConcurrentHashMap();
    private byte[] body;
    private final String identifier;

    public static class OidHandle {
        private final byte[] enc;
        private final int key;

        public OidHandle(byte[] bArr) {
            this.key = Arrays.hashCode(bArr);
            this.enc = bArr;
        }

        public boolean equals(Object obj) {
            if (obj instanceof OidHandle) {
                return Arrays.areEqual(this.enc, ((OidHandle) obj).enc);
            }
            return false;
        }

        public int hashCode() {
            return this.key;
        }
    }

    public ASN1ObjectIdentifier(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        boolean z2 = true;
        BigInteger bigIntegerShiftLeft = null;
        long j2 = 0;
        for (int i2 = 0; i2 != bArr.length; i2++) {
            byte b2 = bArr[i2];
            if (j2 <= LONG_LIMIT) {
                long j3 = j2 + ((long) (b2 & 127));
                if ((b2 & 128) == 0) {
                    if (z2) {
                        if (j3 < 40) {
                            stringBuffer.append('0');
                        } else if (j3 < 80) {
                            stringBuffer.append('1');
                            j3 -= 40;
                        } else {
                            stringBuffer.append('2');
                            j3 -= 80;
                        }
                        z2 = false;
                    }
                    stringBuffer.append('.');
                    stringBuffer.append(j3);
                    j2 = 0;
                } else {
                    j2 = j3 << 7;
                }
            } else {
                BigInteger bigIntegerOr = (bigIntegerShiftLeft == null ? BigInteger.valueOf(j2) : bigIntegerShiftLeft).or(BigInteger.valueOf(b2 & 127));
                if ((b2 & 128) == 0) {
                    if (z2) {
                        stringBuffer.append('2');
                        bigIntegerOr = bigIntegerOr.subtract(BigInteger.valueOf(80L));
                        z2 = false;
                    }
                    stringBuffer.append('.');
                    stringBuffer.append(bigIntegerOr);
                    bigIntegerShiftLeft = null;
                    j2 = 0;
                } else {
                    bigIntegerShiftLeft = bigIntegerOr.shiftLeft(7);
                }
            }
        }
        this.identifier = stringBuffer.toString();
        this.body = Arrays.clone(bArr);
    }

    private void doOutput(ByteArrayOutputStream byteArrayOutputStream) {
        OIDTokenizer oIDTokenizer = new OIDTokenizer(this.identifier);
        int i2 = Integer.parseInt(oIDTokenizer.nextToken()) * 40;
        String strNextToken = oIDTokenizer.nextToken();
        if (strNextToken.length() <= 18) {
            writeField(byteArrayOutputStream, ((long) i2) + Long.parseLong(strNextToken));
        } else {
            writeField(byteArrayOutputStream, new BigInteger(strNextToken).add(BigInteger.valueOf(i2)));
        }
        while (oIDTokenizer.hasMoreTokens()) {
            String strNextToken2 = oIDTokenizer.nextToken();
            if (strNextToken2.length() <= 18) {
                writeField(byteArrayOutputStream, Long.parseLong(strNextToken2));
            } else {
                writeField(byteArrayOutputStream, new BigInteger(strNextToken2));
            }
        }
    }

    public static ASN1ObjectIdentifier fromOctetString(byte[] bArr) {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = pool.get(new OidHandle(bArr));
        return aSN1ObjectIdentifier == null ? new ASN1ObjectIdentifier(bArr) : aSN1ObjectIdentifier;
    }

    private synchronized byte[] getBody() {
        try {
            if (this.body == null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                doOutput(byteArrayOutputStream);
                this.body = byteArrayOutputStream.toByteArray();
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.body;
    }

    public static ASN1ObjectIdentifier getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1ObjectIdentifier)) {
            return (ASN1ObjectIdentifier) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Encodable aSN1Encodable = (ASN1Encodable) obj;
            if (aSN1Encodable.toASN1Primitive() instanceof ASN1ObjectIdentifier) {
                return (ASN1ObjectIdentifier) aSN1Encodable.toASN1Primitive();
            }
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (ASN1ObjectIdentifier) ASN1Primitive.fromByteArray((byte[]) obj);
        } catch (IOException e2) {
            throw new IllegalArgumentException("failed to construct object identifier from byte[]: " + e2.getMessage());
        }
    }

    private static boolean isValidBranchID(String str, int i2) {
        boolean z2;
        char cCharAt;
        int length = str.length();
        do {
            z2 = false;
            while (true) {
                length--;
                if (length < i2) {
                    return z2;
                }
                cCharAt = str.charAt(length);
                if ('0' > cCharAt || cCharAt > '9') {
                    break;
                }
                z2 = true;
            }
            if (cCharAt != '.') {
                break;
            }
        } while (z2);
        return false;
    }

    private static boolean isValidIdentifier(String str) {
        char cCharAt;
        if (str.length() < 3 || str.charAt(1) != '.' || (cCharAt = str.charAt(0)) < '0' || cCharAt > '2') {
            return false;
        }
        return isValidBranchID(str, 2);
    }

    private void writeField(ByteArrayOutputStream byteArrayOutputStream, long j2) {
        byte[] bArr = new byte[9];
        int i2 = 8;
        bArr[8] = (byte) (((int) j2) & CertificateBody.profileType);
        while (j2 >= 128) {
            j2 >>= 7;
            i2--;
            bArr[i2] = (byte) ((((int) j2) & CertificateBody.profileType) | 128);
        }
        byteArrayOutputStream.write(bArr, i2, 9 - i2);
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive == this) {
            return true;
        }
        if (aSN1Primitive instanceof ASN1ObjectIdentifier) {
            return this.identifier.equals(((ASN1ObjectIdentifier) aSN1Primitive).identifier);
        }
        return false;
    }

    public ASN1ObjectIdentifier branch(String str) {
        return new ASN1ObjectIdentifier(this, str);
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream) {
        byte[] body = getBody();
        aSN1OutputStream.write(6);
        aSN1OutputStream.writeLength(body.length);
        aSN1OutputStream.write(body);
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    public int encodedLength() {
        int length = getBody().length;
        return StreamUtil.calculateBodyLength(length) + 1 + length;
    }

    public String getId() {
        return this.identifier;
    }

    @Override // org.spongycastle.asn1.ASN1Primitive, org.spongycastle.asn1.ASN1Object
    public int hashCode() {
        return this.identifier.hashCode();
    }

    public ASN1ObjectIdentifier intern() {
        OidHandle oidHandle = new OidHandle(getBody());
        ConcurrentMap<OidHandle, ASN1ObjectIdentifier> concurrentMap = pool;
        ASN1ObjectIdentifier aSN1ObjectIdentifier = concurrentMap.get(oidHandle);
        if (aSN1ObjectIdentifier != null) {
            return aSN1ObjectIdentifier;
        }
        ASN1ObjectIdentifier aSN1ObjectIdentifierPutIfAbsent = concurrentMap.putIfAbsent(oidHandle, this);
        return aSN1ObjectIdentifierPutIfAbsent == null ? this : aSN1ObjectIdentifierPutIfAbsent;
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return false;
    }

    public boolean on(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String id = getId();
        String id2 = aSN1ObjectIdentifier.getId();
        return id.length() > id2.length() && id.charAt(id2.length()) == '.' && id.startsWith(id2);
    }

    public String toString() {
        return getId();
    }

    private void writeField(ByteArrayOutputStream byteArrayOutputStream, BigInteger bigInteger) {
        int iBitLength = (bigInteger.bitLength() + 6) / 7;
        if (iBitLength == 0) {
            byteArrayOutputStream.write(0);
            return;
        }
        byte[] bArr = new byte[iBitLength];
        int i2 = iBitLength - 1;
        for (int i3 = i2; i3 >= 0; i3--) {
            bArr[i3] = (byte) ((bigInteger.intValue() & CertificateBody.profileType) | 128);
            bigInteger = bigInteger.shiftRight(7);
        }
        bArr[i2] = (byte) (bArr[i2] & 127);
        byteArrayOutputStream.write(bArr, 0, iBitLength);
    }

    public static ASN1ObjectIdentifier getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z2) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        if (!z2 && !(object instanceof ASN1ObjectIdentifier)) {
            return fromOctetString(ASN1OctetString.getInstance(aSN1TaggedObject.getObject()).getOctets());
        }
        return getInstance(object);
    }

    public ASN1ObjectIdentifier(String str) {
        if (str != null) {
            if (isValidIdentifier(str)) {
                this.identifier = str;
                return;
            }
            throw new IllegalArgumentException("string " + str + " not an OID");
        }
        throw new IllegalArgumentException("'identifier' cannot be null");
    }

    public ASN1ObjectIdentifier(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        if (isValidBranchID(str, 0)) {
            this.identifier = aSN1ObjectIdentifier.getId() + "." + str;
            return;
        }
        throw new IllegalArgumentException("string " + str + " not a valid OID branch");
    }
}

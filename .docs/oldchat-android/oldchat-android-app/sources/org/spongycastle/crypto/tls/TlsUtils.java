package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.cmc.BodyPartID;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.KeyUsage;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.MD5Digest;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.digests.SHA224Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA384Digest;
import org.spongycastle.crypto.digests.SHA512Digest;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DSAPublicKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.util.PublicKeyFactory;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Integers;
import org.spongycastle.util.Shorts;
import org.spongycastle.util.Strings;
import org.spongycastle.util.io.Streams;

/* JADX INFO: loaded from: classes.dex */
public class TlsUtils {
    public static final byte[] EMPTY_BYTES = new byte[0];
    public static final short[] EMPTY_SHORTS = new short[0];
    public static final int[] EMPTY_INTS = new int[0];
    public static final long[] EMPTY_LONGS = new long[0];
    public static final Integer EXT_signature_algorithms = Integers.valueOf(13);
    static final byte[] SSL_CLIENT = {67, 76, 78, 84};
    static final byte[] SSL_SERVER = {83, 82, 86, 82};
    static final byte[][] SSL3_CONST = genSSL3Const();

    public static byte[] PRF(TlsContext tlsContext, byte[] bArr, String str, byte[] bArr2, int i2) {
        if (tlsContext.getServerVersion().isSSL()) {
            throw new IllegalStateException("No PRF available for SSLv3 session");
        }
        byte[] byteArray = Strings.toByteArray(str);
        byte[] bArrConcat = concat(byteArray, bArr2);
        int prfAlgorithm = tlsContext.getSecurityParameters().getPrfAlgorithm();
        if (prfAlgorithm == 0) {
            return PRF_legacy(bArr, byteArray, bArrConcat, i2);
        }
        byte[] bArr3 = new byte[i2];
        hmac_hash(createPRFHash(prfAlgorithm), bArr, bArrConcat, bArr3);
        return bArr3;
    }

    public static byte[] PRF_legacy(byte[] bArr, String str, byte[] bArr2, int i2) {
        byte[] byteArray = Strings.toByteArray(str);
        return PRF_legacy(bArr, byteArray, concat(byteArray, bArr2), i2);
    }

    public static void addSignatureAlgorithmsExtension(Hashtable hashtable, Vector vector) {
        hashtable.put(EXT_signature_algorithms, createSignatureAlgorithmsExtension(vector));
    }

    public static byte[] calculateKeyBlock(TlsContext tlsContext, int i2) {
        SecurityParameters securityParameters = tlsContext.getSecurityParameters();
        byte[] masterSecret = securityParameters.getMasterSecret();
        byte[] bArrConcat = concat(securityParameters.getServerRandom(), securityParameters.getClientRandom());
        return isSSL(tlsContext) ? calculateKeyBlock_SSL(masterSecret, bArrConcat, i2) : PRF(tlsContext, masterSecret, "key expansion", bArrConcat, i2);
    }

    public static byte[] calculateKeyBlock_SSL(byte[] bArr, byte[] bArr2, int i2) {
        Digest digestCreateHash = createHash((short) 1);
        Digest digestCreateHash2 = createHash((short) 2);
        int digestSize = digestCreateHash.getDigestSize();
        int digestSize2 = digestCreateHash2.getDigestSize();
        byte[] bArr3 = new byte[digestSize2];
        byte[] bArr4 = new byte[i2 + digestSize];
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            byte[] bArr5 = SSL3_CONST[i4];
            digestCreateHash2.update(bArr5, 0, bArr5.length);
            digestCreateHash2.update(bArr, 0, bArr.length);
            digestCreateHash2.update(bArr2, 0, bArr2.length);
            digestCreateHash2.doFinal(bArr3, 0);
            digestCreateHash.update(bArr, 0, bArr.length);
            digestCreateHash.update(bArr3, 0, digestSize2);
            digestCreateHash.doFinal(bArr4, i3);
            i3 += digestSize;
            i4++;
        }
        return Arrays.copyOfRange(bArr4, 0, i2);
    }

    public static byte[] calculateMasterSecret(TlsContext tlsContext, byte[] bArr) {
        SecurityParameters securityParameters = tlsContext.getSecurityParameters();
        byte[] sessionHash = securityParameters.extendedMasterSecret ? securityParameters.getSessionHash() : concat(securityParameters.getClientRandom(), securityParameters.getServerRandom());
        if (isSSL(tlsContext)) {
            return calculateMasterSecret_SSL(bArr, sessionHash);
        }
        return PRF(tlsContext, bArr, securityParameters.extendedMasterSecret ? ExporterLabel.extended_master_secret : "master secret", sessionHash, 48);
    }

    public static byte[] calculateMasterSecret_SSL(byte[] bArr, byte[] bArr2) {
        Digest digestCreateHash = createHash((short) 1);
        Digest digestCreateHash2 = createHash((short) 2);
        int digestSize = digestCreateHash.getDigestSize();
        int digestSize2 = digestCreateHash2.getDigestSize();
        byte[] bArr3 = new byte[digestSize2];
        byte[] bArr4 = new byte[digestSize * 3];
        int i2 = 0;
        for (int i3 = 0; i3 < 3; i3++) {
            byte[] bArr5 = SSL3_CONST[i3];
            digestCreateHash2.update(bArr5, 0, bArr5.length);
            digestCreateHash2.update(bArr, 0, bArr.length);
            digestCreateHash2.update(bArr2, 0, bArr2.length);
            digestCreateHash2.doFinal(bArr3, 0);
            digestCreateHash.update(bArr, 0, bArr.length);
            digestCreateHash.update(bArr3, 0, digestSize2);
            digestCreateHash.doFinal(bArr4, i2);
            i2 += digestSize;
        }
        return bArr4;
    }

    public static byte[] calculateVerifyData(TlsContext tlsContext, String str, byte[] bArr) {
        if (isSSL(tlsContext)) {
            return bArr;
        }
        SecurityParameters securityParameters = tlsContext.getSecurityParameters();
        return PRF(tlsContext, securityParameters.getMasterSecret(), str, bArr, securityParameters.getVerifyDataLength());
    }

    public static void checkUint16(int i2) throws TlsFatalAlert {
        if (!isValidUint16(i2)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint24(int i2) {
        if (!isValidUint24(i2)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint32(long j2) throws TlsFatalAlert {
        if (!isValidUint32(j2)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint48(long j2) throws TlsFatalAlert {
        if (!isValidUint48(j2)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint64(long j2) throws TlsFatalAlert {
        if (!isValidUint64(j2)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint8(short s2) throws TlsFatalAlert {
        if (!isValidUint8(s2)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static Digest cloneHash(short s2, Digest digest) {
        switch (s2) {
            case 1:
                return new MD5Digest((MD5Digest) digest);
            case 2:
                return new SHA1Digest((SHA1Digest) digest);
            case 3:
                return new SHA224Digest((SHA224Digest) digest);
            case 4:
                return new SHA256Digest((SHA256Digest) digest);
            case 5:
                return new SHA384Digest((SHA384Digest) digest);
            case 6:
                return new SHA512Digest((SHA512Digest) digest);
            default:
                throw new IllegalArgumentException("unknown HashAlgorithm");
        }
    }

    public static Digest clonePRFHash(int i2, Digest digest) {
        return i2 != 0 ? cloneHash(getHashAlgorithmForPRFAlgorithm(i2), digest) : new CombinedHash((CombinedHash) digest);
    }

    public static byte[] concat(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static Digest createHash(short s2) {
        switch (s2) {
            case 1:
                return new MD5Digest();
            case 2:
                return new SHA1Digest();
            case 3:
                return new SHA224Digest();
            case 4:
                return new SHA256Digest();
            case 5:
                return new SHA384Digest();
            case 6:
                return new SHA512Digest();
            default:
                throw new IllegalArgumentException("unknown HashAlgorithm");
        }
    }

    public static Digest createPRFHash(int i2) {
        return i2 != 0 ? createHash(getHashAlgorithmForPRFAlgorithm(i2)) : new CombinedHash();
    }

    public static byte[] createSignatureAlgorithmsExtension(Vector vector) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        encodeSupportedSignatureAlgorithms(vector, false, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static TlsSigner createTlsSigner(short s2) {
        if (s2 == 1) {
            return new TlsRSASigner();
        }
        if (s2 == 2) {
            return new TlsDSSSigner();
        }
        if (s2 == 64) {
            return new TlsECDSASigner();
        }
        throw new IllegalArgumentException("'clientCertificateType' is not a type with signing capability");
    }

    public static byte[] encodeOpaque8(byte[] bArr) throws TlsFatalAlert {
        checkUint8(bArr.length);
        return Arrays.prepend(bArr, (byte) bArr.length);
    }

    public static void encodeSupportedSignatureAlgorithms(Vector vector, boolean z2, OutputStream outputStream) throws IOException {
        if (vector == null || vector.size() < 1 || vector.size() >= 32768) {
            throw new IllegalArgumentException("'supportedSignatureAlgorithms' must have length from 1 to (2^15 - 1)");
        }
        int size = vector.size() * 2;
        checkUint16(size);
        writeUint16(size, outputStream);
        for (int i2 = 0; i2 < vector.size(); i2++) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = (SignatureAndHashAlgorithm) vector.elementAt(i2);
            if (!z2 && signatureAndHashAlgorithm.getSignature() == 0) {
                throw new IllegalArgumentException("SignatureAlgorithm.anonymous MUST NOT appear in the signature_algorithms extension");
            }
            signatureAndHashAlgorithm.encode(outputStream);
        }
    }

    public static byte[] encodeUint16ArrayWithUint16Length(int[] iArr) throws TlsFatalAlert {
        byte[] bArr = new byte[(iArr.length * 2) + 2];
        writeUint16ArrayWithUint16Length(iArr, bArr, 0);
        return bArr;
    }

    public static byte[] encodeUint8ArrayWithUint8Length(short[] sArr) throws TlsFatalAlert {
        byte[] bArr = new byte[sArr.length + 1];
        writeUint8ArrayWithUint8Length(sArr, bArr, 0);
        return bArr;
    }

    private static byte[][] genSSL3Const() {
        byte[][] bArr = new byte[10][];
        int i2 = 0;
        while (i2 < 10) {
            int i3 = i2 + 1;
            byte[] bArr2 = new byte[i3];
            Arrays.fill(bArr2, (byte) (i2 + 65));
            bArr[i2] = bArr2;
            i2 = i3;
        }
        return bArr;
    }

    public static Vector getAllSignatureAlgorithms() {
        Vector vector = new Vector(4);
        vector.addElement(Shorts.valueOf((short) 0));
        vector.addElement(Shorts.valueOf((short) 1));
        vector.addElement(Shorts.valueOf((short) 2));
        vector.addElement(Shorts.valueOf((short) 3));
        return vector;
    }

    public static int getCipherType(int i2) throws TlsFatalAlert {
        int encryptionAlgorithm = getEncryptionAlgorithm(i2);
        if (encryptionAlgorithm == 103 || encryptionAlgorithm == 104) {
            return 2;
        }
        switch (encryptionAlgorithm) {
            case 0:
            case 1:
            case 2:
                return 0;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 12:
            case 13:
            case 14:
                return 1;
            case 10:
            case 11:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return 2;
            default:
                throw new TlsFatalAlert((short) 80);
        }
    }

    public static short getClientCertificateType(Certificate certificate, Certificate certificate2) throws TlsFatalAlert {
        if (certificate.isEmpty()) {
            return (short) -1;
        }
        org.spongycastle.asn1.x509.Certificate certificateAt = certificate.getCertificateAt(0);
        try {
            AsymmetricKeyParameter asymmetricKeyParameterCreateKey = PublicKeyFactory.createKey(certificateAt.getSubjectPublicKeyInfo());
            if (asymmetricKeyParameterCreateKey.isPrivate()) {
                throw new TlsFatalAlert((short) 80);
            }
            if (asymmetricKeyParameterCreateKey instanceof RSAKeyParameters) {
                validateKeyUsage(certificateAt, 128);
                return (short) 1;
            }
            if (asymmetricKeyParameterCreateKey instanceof DSAPublicKeyParameters) {
                validateKeyUsage(certificateAt, 128);
                return (short) 2;
            }
            if (!(asymmetricKeyParameterCreateKey instanceof ECPublicKeyParameters)) {
                throw new TlsFatalAlert((short) 43);
            }
            validateKeyUsage(certificateAt, 128);
            return (short) 64;
        } catch (Exception e2) {
            throw new TlsFatalAlert((short) 43, e2);
        }
    }

    public static Vector getDefaultDSSSignatureAlgorithms() {
        return vectorOfOne(new SignatureAndHashAlgorithm((short) 2, (short) 2));
    }

    public static Vector getDefaultECDSASignatureAlgorithms() {
        return vectorOfOne(new SignatureAndHashAlgorithm((short) 2, (short) 3));
    }

    public static Vector getDefaultRSASignatureAlgorithms() {
        return vectorOfOne(new SignatureAndHashAlgorithm((short) 2, (short) 1));
    }

    public static Vector getDefaultSupportedSignatureAlgorithms() {
        short[] sArr = {2, 3, 4, 5, 6};
        short[] sArr2 = {1, 2, 3};
        Vector vector = new Vector();
        for (int i2 = 0; i2 < 3; i2++) {
            for (int i3 = 0; i3 < 5; i3++) {
                vector.addElement(new SignatureAndHashAlgorithm(sArr[i3], sArr2[i2]));
            }
        }
        return vector;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x004b A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getEncryptionAlgorithm(int r3) throws org.spongycastle.crypto.tls.TlsFatalAlert {
        /*
            Method dump skipped, instruction units count: 768
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.tls.TlsUtils.getEncryptionAlgorithm(int):int");
    }

    public static byte[] getExtensionData(Hashtable hashtable, Integer num) {
        if (hashtable == null) {
            return null;
        }
        return (byte[]) hashtable.get(num);
    }

    public static short getHashAlgorithmForPRFAlgorithm(int i2) {
        if (i2 == 0) {
            throw new IllegalArgumentException("legacy PRF not a valid algorithm");
        }
        if (i2 == 1) {
            return (short) 4;
        }
        if (i2 == 2) {
            return (short) 5;
        }
        throw new IllegalArgumentException("unknown PRFAlgorithm");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0053 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getKeyExchangeAlgorithm(int r2) throws org.spongycastle.crypto.tls.TlsFatalAlert {
        /*
            Method dump skipped, instruction units count: 762
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.tls.TlsUtils.getKeyExchangeAlgorithm(int):int");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:20:0x002c A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getMACAlgorithm(int r4) throws org.spongycastle.crypto.tls.TlsFatalAlert {
        /*
            Method dump skipped, instruction units count: 722
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.tls.TlsUtils.getMACAlgorithm(int):int");
    }

    public static ProtocolVersion getMinimumVersion(int i2) {
        switch (i2) {
            case 59:
            case 60:
            case 61:
            case CipherSuite.TLS_DH_DSS_WITH_AES_128_CBC_SHA256 /* 62 */:
            case 63:
            case 64:
                break;
            default:
                switch (i2) {
                    case 103:
                    case 104:
                    case CipherSuite.TLS_DH_RSA_WITH_AES_256_CBC_SHA256 /* 105 */:
                    case CipherSuite.TLS_DHE_DSS_WITH_AES_256_CBC_SHA256 /* 106 */:
                    case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA256 /* 107 */:
                    case CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA256 /* 108 */:
                    case CipherSuite.TLS_DH_anon_WITH_AES_256_CBC_SHA256 /* 109 */:
                        break;
                    default:
                        switch (i2) {
                            case CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256 /* 156 */:
                            case CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384 /* 157 */:
                            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256 /* 158 */:
                            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384 /* 159 */:
                            case CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256 /* 160 */:
                            case CipherSuite.TLS_DH_RSA_WITH_AES_256_GCM_SHA384 /* 161 */:
                            case CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256 /* 162 */:
                            case CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384 /* 163 */:
                            case CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256 /* 164 */:
                            case CipherSuite.TLS_DH_DSS_WITH_AES_256_GCM_SHA384 /* 165 */:
                            case CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256 /* 166 */:
                            case CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384 /* 167 */:
                            case CipherSuite.TLS_PSK_WITH_AES_128_GCM_SHA256 /* 168 */:
                            case CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384 /* 169 */:
                            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256 /* 170 */:
                            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384 /* 171 */:
                            case CipherSuite.TLS_RSA_PSK_WITH_AES_128_GCM_SHA256 /* 172 */:
                            case CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384 /* 173 */:
                                break;
                            default:
                                switch (i2) {
                                    case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 186 */:
                                    case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256 /* 187 */:
                                    case 188:
                                    case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256 /* 189 */:
                                    case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 190 */:
                                    case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256 /* 191 */:
                                    case 192:
                                    case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256 /* 193 */:
                                    case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256 /* 194 */:
                                    case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256 /* 195 */:
                                    case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256 /* 196 */:
                                    case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256 /* 197 */:
                                        break;
                                    default:
                                        switch (i2) {
                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 /* 49187 */:
                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384 /* 49188 */:
                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256 /* 49189 */:
                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384 /* 49190 */:
                                            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 /* 49191 */:
                                            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384 /* 49192 */:
                                            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256 /* 49193 */:
                                            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384 /* 49194 */:
                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 /* 49195 */:
                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 /* 49196 */:
                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256 /* 49197 */:
                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384 /* 49198 */:
                                            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 /* 49199 */:
                                            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 /* 49200 */:
                                            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256 /* 49201 */:
                                            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384 /* 49202 */:
                                                break;
                                            default:
                                                switch (i2) {
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49266 */:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49267 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49268 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49269 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49270 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49271 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49272 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49273 */:
                                                    case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49274 */:
                                                    case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49275 */:
                                                    case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49276 */:
                                                    case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49277 */:
                                                    case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49278 */:
                                                    case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49279 */:
                                                    case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49280 */:
                                                    case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49281 */:
                                                    case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49282 */:
                                                    case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49283 */:
                                                    case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_GCM_SHA256 /* 49284 */:
                                                    case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_GCM_SHA384 /* 49285 */:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49286 */:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49287 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49288 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49289 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49290 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49291 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49292 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49293 */:
                                                    case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49294 */:
                                                    case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49295 */:
                                                    case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49296 */:
                                                    case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49297 */:
                                                    case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49298 */:
                                                    case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49299 */:
                                                        break;
                                                    default:
                                                        switch (i2) {
                                                            case CipherSuite.TLS_RSA_WITH_AES_128_CCM /* 49308 */:
                                                            case CipherSuite.TLS_RSA_WITH_AES_256_CCM /* 49309 */:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM /* 49310 */:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM /* 49311 */:
                                                            case CipherSuite.TLS_RSA_WITH_AES_128_CCM_8 /* 49312 */:
                                                            case CipherSuite.TLS_RSA_WITH_AES_256_CCM_8 /* 49313 */:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM_8 /* 49314 */:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM_8 /* 49315 */:
                                                            case CipherSuite.TLS_PSK_WITH_AES_128_CCM /* 49316 */:
                                                            case CipherSuite.TLS_PSK_WITH_AES_256_CCM /* 49317 */:
                                                            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CCM /* 49318 */:
                                                            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CCM /* 49319 */:
                                                            case CipherSuite.TLS_PSK_WITH_AES_128_CCM_8 /* 49320 */:
                                                            case CipherSuite.TLS_PSK_WITH_AES_256_CCM_8 /* 49321 */:
                                                            case CipherSuite.TLS_PSK_DHE_WITH_AES_128_CCM_8 /* 49322 */:
                                                            case CipherSuite.TLS_PSK_DHE_WITH_AES_256_CCM_8 /* 49323 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM /* 49324 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM /* 49325 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8 /* 49326 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8 /* 49327 */:
                                                                break;
                                                            default:
                                                                switch (i2) {
                                                                    case CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52392 */:
                                                                    case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256 /* 52393 */:
                                                                    case CipherSuite.DRAFT_TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52394 */:
                                                                    case CipherSuite.DRAFT_TLS_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52395 */:
                                                                    case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52396 */:
                                                                    case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52397 */:
                                                                    case CipherSuite.DRAFT_TLS_RSA_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52398 */:
                                                                        break;
                                                                    default:
                                                                        switch (i2) {
                                                                            case CipherSuite.DRAFT_TLS_DHE_RSA_WITH_AES_128_OCB /* 65280 */:
                                                                            case 65281:
                                                                            case 65282:
                                                                            case CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_AES_256_OCB /* 65283 */:
                                                                            case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_AES_128_OCB /* 65284 */:
                                                                            case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_AES_256_OCB /* 65285 */:
                                                                                break;
                                                                            default:
                                                                                switch (i2) {
                                                                                    case CipherSuite.DRAFT_TLS_PSK_WITH_AES_128_OCB /* 65296 */:
                                                                                    case CipherSuite.DRAFT_TLS_PSK_WITH_AES_256_OCB /* 65297 */:
                                                                                    case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_AES_128_OCB /* 65298 */:
                                                                                    case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_AES_256_OCB /* 65299 */:
                                                                                    case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_AES_128_OCB /* 65300 */:
                                                                                    case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_AES_256_OCB /* 65301 */:
                                                                                        break;
                                                                                    default:
                                                                                        return ProtocolVersion.SSLv3;
                                                                                }
                                                                                break;
                                                                        }
                                                                        break;
                                                                }
                                                                break;
                                                        }
                                                        break;
                                                }
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                        break;
                }
                break;
        }
        return ProtocolVersion.TLSv12;
    }

    public static ASN1ObjectIdentifier getOIDForHashAlgorithm(short s2) {
        switch (s2) {
            case 1:
                return PKCSObjectIdentifiers.md5;
            case 2:
                return X509ObjectIdentifiers.id_SHA1;
            case 3:
                return NISTObjectIdentifiers.id_sha224;
            case 4:
                return NISTObjectIdentifiers.id_sha256;
            case 5:
                return NISTObjectIdentifiers.id_sha384;
            case 6:
                return NISTObjectIdentifiers.id_sha512;
            default:
                throw new IllegalArgumentException("unknown HashAlgorithm");
        }
    }

    public static Vector getSignatureAlgorithmsExtension(Hashtable hashtable) {
        byte[] extensionData = getExtensionData(hashtable, EXT_signature_algorithms);
        if (extensionData == null) {
            return null;
        }
        return readSignatureAlgorithmsExtension(extensionData);
    }

    public static SignatureAndHashAlgorithm getSignatureAndHashAlgorithm(TlsContext tlsContext, TlsSignerCredentials tlsSignerCredentials) throws TlsFatalAlert {
        if (!isTLSv12(tlsContext)) {
            return null;
        }
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = tlsSignerCredentials.getSignatureAndHashAlgorithm();
        if (signatureAndHashAlgorithm != null) {
            return signatureAndHashAlgorithm;
        }
        throw new TlsFatalAlert((short) 80);
    }

    public static Vector getUsableSignatureAlgorithms(Vector vector) {
        if (vector == null) {
            return getAllSignatureAlgorithms();
        }
        Vector vector2 = new Vector(4);
        vector2.addElement(Shorts.valueOf((short) 0));
        for (int i2 = 0; i2 < vector.size(); i2++) {
            Short shValueOf = Shorts.valueOf(((SignatureAndHashAlgorithm) vector.elementAt(i2)).getSignature());
            if (!vector2.contains(shValueOf)) {
                vector2.addElement(shValueOf);
            }
        }
        return vector2;
    }

    public static boolean hasExpectedEmptyExtensionData(Hashtable hashtable, Integer num, short s2) throws TlsFatalAlert {
        byte[] extensionData = getExtensionData(hashtable, num);
        if (extensionData == null) {
            return false;
        }
        if (extensionData.length == 0) {
            return true;
        }
        throw new TlsFatalAlert(s2);
    }

    public static boolean hasSigningCapability(short s2) {
        return s2 == 1 || s2 == 2 || s2 == 64;
    }

    public static void hmac_hash(Digest digest, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        HMac hMac = new HMac(digest);
        hMac.init(new KeyParameter(bArr));
        int digestSize = digest.getDigestSize();
        int length = ((bArr3.length + digestSize) - 1) / digestSize;
        int macSize = hMac.getMacSize();
        byte[] bArr4 = new byte[macSize];
        byte[] bArr5 = new byte[hMac.getMacSize()];
        byte[] bArr6 = bArr2;
        int i2 = 0;
        while (i2 < length) {
            hMac.update(bArr6, 0, bArr6.length);
            hMac.doFinal(bArr4, 0);
            hMac.update(bArr4, 0, macSize);
            hMac.update(bArr2, 0, bArr2.length);
            hMac.doFinal(bArr5, 0);
            int i3 = digestSize * i2;
            System.arraycopy(bArr5, 0, bArr3, i3, Math.min(digestSize, bArr3.length - i3));
            i2++;
            bArr6 = bArr4;
        }
    }

    public static TlsSession importSession(byte[] bArr, SessionParameters sessionParameters) {
        return new TlsSessionImpl(bArr, sessionParameters);
    }

    public static boolean isAEADCipherSuite(int i2) {
        return 2 == getCipherType(i2);
    }

    public static boolean isBlockCipherSuite(int i2) {
        return 1 == getCipherType(i2);
    }

    public static boolean isSSL(TlsContext tlsContext) {
        return tlsContext.getServerVersion().isSSL();
    }

    public static boolean isSignatureAlgorithmsExtensionAllowed(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv12.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isStreamCipherSuite(int i2) {
        return getCipherType(i2) == 0;
    }

    public static boolean isTLSv11(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv11.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isTLSv12(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv12.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isValidCipherSuiteForSignatureAlgorithms(int i2, Vector vector) {
        try {
            int keyExchangeAlgorithm = getKeyExchangeAlgorithm(i2);
            if (keyExchangeAlgorithm != 3 && keyExchangeAlgorithm != 4) {
                if (keyExchangeAlgorithm != 5 && keyExchangeAlgorithm != 6) {
                    if (keyExchangeAlgorithm != 11 && keyExchangeAlgorithm != 12) {
                        if (keyExchangeAlgorithm == 17) {
                            return vector.contains(Shorts.valueOf((short) 3));
                        }
                        if (keyExchangeAlgorithm != 19) {
                            if (keyExchangeAlgorithm != 20) {
                                if (keyExchangeAlgorithm != 22) {
                                    if (keyExchangeAlgorithm != 23) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                    return vector.contains(Shorts.valueOf((short) 0));
                }
                return vector.contains(Shorts.valueOf((short) 1));
            }
            return vector.contains(Shorts.valueOf((short) 2));
        } catch (IOException unused) {
            return true;
        }
    }

    public static boolean isValidCipherSuiteForVersion(int i2, ProtocolVersion protocolVersion) {
        return getMinimumVersion(i2).isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isValidUint16(int i2) {
        return (65535 & i2) == i2;
    }

    public static boolean isValidUint24(int i2) {
        return (16777215 & i2) == i2;
    }

    public static boolean isValidUint32(long j2) {
        return (BodyPartID.bodyIdMax & j2) == j2;
    }

    public static boolean isValidUint48(long j2) {
        return (281474976710655L & j2) == j2;
    }

    public static boolean isValidUint64(long j2) {
        return true;
    }

    public static boolean isValidUint8(int i2) {
        return (i2 & 255) == i2;
    }

    public static Vector parseSupportedSignatureAlgorithms(boolean z2, InputStream inputStream) throws IOException {
        int uint16 = readUint16(inputStream);
        if (uint16 < 2 || (uint16 & 1) != 0) {
            throw new TlsFatalAlert((short) 50);
        }
        int i2 = uint16 / 2;
        Vector vector = new Vector(i2);
        for (int i3 = 0; i3 < i2; i3++) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = SignatureAndHashAlgorithm.parse(inputStream);
            if (!z2 && signatureAndHashAlgorithm.getSignature() == 0) {
                throw new TlsFatalAlert((short) 47);
            }
            vector.addElement(signatureAndHashAlgorithm);
        }
        return vector;
    }

    public static ASN1Primitive readASN1Object(byte[] bArr) throws TlsFatalAlert {
        ASN1InputStream aSN1InputStream = new ASN1InputStream(bArr);
        ASN1Primitive object = aSN1InputStream.readObject();
        if (object == null) {
            throw new TlsFatalAlert((short) 50);
        }
        if (aSN1InputStream.readObject() == null) {
            return object;
        }
        throw new TlsFatalAlert((short) 50);
    }

    public static byte[] readAllOrNothing(int i2, InputStream inputStream) throws EOFException {
        if (i2 < 1) {
            return EMPTY_BYTES;
        }
        byte[] bArr = new byte[i2];
        int fully = Streams.readFully(inputStream, bArr);
        if (fully == 0) {
            return null;
        }
        if (fully == i2) {
            return bArr;
        }
        throw new EOFException();
    }

    public static ASN1Primitive readDERObject(byte[] bArr) throws TlsFatalAlert {
        ASN1Primitive aSN1Object = readASN1Object(bArr);
        if (Arrays.areEqual(aSN1Object.getEncoded(ASN1Encoding.DER), bArr)) {
            return aSN1Object;
        }
        throw new TlsFatalAlert((short) 50);
    }

    public static byte[] readFully(int i2, InputStream inputStream) throws EOFException {
        if (i2 < 1) {
            return EMPTY_BYTES;
        }
        byte[] bArr = new byte[i2];
        if (i2 == Streams.readFully(inputStream, bArr)) {
            return bArr;
        }
        throw new EOFException();
    }

    public static byte[] readOpaque16(InputStream inputStream) {
        return readFully(readUint16(inputStream), inputStream);
    }

    public static byte[] readOpaque24(InputStream inputStream) {
        return readFully(readUint24(inputStream), inputStream);
    }

    public static byte[] readOpaque8(InputStream inputStream) {
        return readFully(readUint8(inputStream), inputStream);
    }

    public static Vector readSignatureAlgorithmsExtension(byte[] bArr) throws IOException {
        if (bArr == null) {
            throw new IllegalArgumentException("'extensionData' cannot be null");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        Vector supportedSignatureAlgorithms = parseSupportedSignatureAlgorithms(false, byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        return supportedSignatureAlgorithms;
    }

    public static int readUint16(InputStream inputStream) throws IOException {
        int i2 = inputStream.read();
        int i3 = inputStream.read();
        if (i3 >= 0) {
            return i3 | (i2 << 8);
        }
        throw new EOFException();
    }

    public static int[] readUint16Array(int i2, InputStream inputStream) {
        int[] iArr = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i3] = readUint16(inputStream);
        }
        return iArr;
    }

    public static int readUint24(InputStream inputStream) {
        int i2 = inputStream.read();
        int i3 = inputStream.read();
        int i4 = inputStream.read();
        if (i4 >= 0) {
            return i4 | (i2 << 16) | (i3 << 8);
        }
        throw new EOFException();
    }

    public static long readUint32(InputStream inputStream) throws IOException {
        int i2 = inputStream.read();
        int i3 = inputStream.read();
        int i4 = inputStream.read();
        int i5 = inputStream.read();
        if (i5 >= 0) {
            return ((long) (i5 | (i2 << 24) | (i3 << 16) | (i4 << 8))) & BodyPartID.bodyIdMax;
        }
        throw new EOFException();
    }

    public static long readUint48(InputStream inputStream) {
        return ((((long) readUint24(inputStream)) & BodyPartID.bodyIdMax) << 24) | (BodyPartID.bodyIdMax & ((long) readUint24(inputStream)));
    }

    public static short readUint8(InputStream inputStream) throws IOException {
        int i2 = inputStream.read();
        if (i2 >= 0) {
            return (short) i2;
        }
        throw new EOFException();
    }

    public static short[] readUint8Array(int i2, InputStream inputStream) {
        short[] sArr = new short[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            sArr[i3] = readUint8(inputStream);
        }
        return sArr;
    }

    public static ProtocolVersion readVersion(byte[] bArr, int i2) {
        return ProtocolVersion.get(bArr[i2] & 255, bArr[i2 + 1] & 255);
    }

    public static int readVersionRaw(byte[] bArr, int i2) {
        return bArr[i2 + 1] | (bArr[i2] << 8);
    }

    public static void trackHashAlgorithms(TlsHandshakeHash tlsHandshakeHash, Vector vector) {
        if (vector != null) {
            for (int i2 = 0; i2 < vector.size(); i2++) {
                short hash = ((SignatureAndHashAlgorithm) vector.elementAt(i2)).getHash();
                if (!HashAlgorithm.isPrivate(hash)) {
                    tlsHandshakeHash.trackHashAlgorithm(hash);
                }
            }
        }
    }

    public static void validateKeyUsage(org.spongycastle.asn1.x509.Certificate certificate, int i2) throws TlsFatalAlert {
        KeyUsage keyUsageFromExtensions;
        Extensions extensions = certificate.getTBSCertificate().getExtensions();
        if (extensions != null && (keyUsageFromExtensions = KeyUsage.fromExtensions(extensions)) != null && (keyUsageFromExtensions.getBytes()[0] & 255 & i2) != i2) {
            throw new TlsFatalAlert((short) 46);
        }
    }

    private static Vector vectorOfOne(Object obj) {
        Vector vector = new Vector(1);
        vector.addElement(obj);
        return vector;
    }

    public static void verifySupportedSignatureAlgorithm(Vector vector, SignatureAndHashAlgorithm signatureAndHashAlgorithm) throws TlsFatalAlert {
        if (vector == null || vector.size() < 1 || vector.size() >= 32768) {
            throw new IllegalArgumentException("'supportedSignatureAlgorithms' must have length from 1 to (2^15 - 1)");
        }
        if (signatureAndHashAlgorithm == null) {
            throw new IllegalArgumentException("'signatureAlgorithm' cannot be null");
        }
        if (signatureAndHashAlgorithm.getSignature() != 0) {
            for (int i2 = 0; i2 < vector.size(); i2++) {
                SignatureAndHashAlgorithm signatureAndHashAlgorithm2 = (SignatureAndHashAlgorithm) vector.elementAt(i2);
                if (signatureAndHashAlgorithm2.getHash() == signatureAndHashAlgorithm.getHash() && signatureAndHashAlgorithm2.getSignature() == signatureAndHashAlgorithm.getSignature()) {
                    return;
                }
            }
        }
        throw new TlsFatalAlert((short) 47);
    }

    public static void writeGMTUnixTime(byte[] bArr, int i2) {
        int iCurrentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        bArr[i2] = (byte) (iCurrentTimeMillis >>> 24);
        bArr[i2 + 1] = (byte) (iCurrentTimeMillis >>> 16);
        bArr[i2 + 2] = (byte) (iCurrentTimeMillis >>> 8);
        bArr[i2 + 3] = (byte) iCurrentTimeMillis;
    }

    public static void writeOpaque16(byte[] bArr, OutputStream outputStream) throws IOException {
        checkUint16(bArr.length);
        writeUint16(bArr.length, outputStream);
        outputStream.write(bArr);
    }

    public static void writeOpaque24(byte[] bArr, OutputStream outputStream) {
        checkUint24(bArr.length);
        writeUint24(bArr.length, outputStream);
        outputStream.write(bArr);
    }

    public static void writeOpaque8(byte[] bArr, OutputStream outputStream) throws IOException {
        checkUint8(bArr.length);
        writeUint8(bArr.length, outputStream);
        outputStream.write(bArr);
    }

    public static void writeUint16(int i2, OutputStream outputStream) throws IOException {
        outputStream.write(i2 >>> 8);
        outputStream.write(i2);
    }

    public static void writeUint16Array(int[] iArr, OutputStream outputStream) throws IOException {
        for (int i2 : iArr) {
            writeUint16(i2, outputStream);
        }
    }

    public static void writeUint16ArrayWithUint16Length(int[] iArr, OutputStream outputStream) throws IOException {
        int length = iArr.length * 2;
        checkUint16(length);
        writeUint16(length, outputStream);
        writeUint16Array(iArr, outputStream);
    }

    public static void writeUint24(int i2, OutputStream outputStream) {
        outputStream.write((byte) (i2 >>> 16));
        outputStream.write((byte) (i2 >>> 8));
        outputStream.write((byte) i2);
    }

    public static void writeUint32(long j2, OutputStream outputStream) throws IOException {
        outputStream.write((byte) (j2 >>> 24));
        outputStream.write((byte) (j2 >>> 16));
        outputStream.write((byte) (j2 >>> 8));
        outputStream.write((byte) j2);
    }

    public static void writeUint48(long j2, OutputStream outputStream) throws IOException {
        outputStream.write((byte) (j2 >>> 40));
        outputStream.write((byte) (j2 >>> 32));
        outputStream.write((byte) (j2 >>> 24));
        outputStream.write((byte) (j2 >>> 16));
        outputStream.write((byte) (j2 >>> 8));
        outputStream.write((byte) j2);
    }

    public static void writeUint64(long j2, OutputStream outputStream) throws IOException {
        outputStream.write((byte) (j2 >>> 56));
        outputStream.write((byte) (j2 >>> 48));
        outputStream.write((byte) (j2 >>> 40));
        outputStream.write((byte) (j2 >>> 32));
        outputStream.write((byte) (j2 >>> 24));
        outputStream.write((byte) (j2 >>> 16));
        outputStream.write((byte) (j2 >>> 8));
        outputStream.write((byte) j2);
    }

    public static void writeUint8(short s2, OutputStream outputStream) throws IOException {
        outputStream.write(s2);
    }

    public static void writeUint8Array(short[] sArr, OutputStream outputStream) throws IOException {
        for (short s2 : sArr) {
            writeUint8(s2, outputStream);
        }
    }

    public static void writeUint8ArrayWithUint8Length(short[] sArr, OutputStream outputStream) throws IOException {
        checkUint8(sArr.length);
        writeUint8(sArr.length, outputStream);
        writeUint8Array(sArr, outputStream);
    }

    public static void writeVersion(ProtocolVersion protocolVersion, OutputStream outputStream) throws IOException {
        outputStream.write(protocolVersion.getMajorVersion());
        outputStream.write(protocolVersion.getMinorVersion());
    }

    public static boolean isTLSv11(TlsContext tlsContext) {
        return isTLSv11(tlsContext.getServerVersion());
    }

    public static boolean isTLSv12(TlsContext tlsContext) {
        return isTLSv12(tlsContext.getServerVersion());
    }

    public static boolean isValidUint16(long j2) {
        return (65535 & j2) == j2;
    }

    public static boolean isValidUint24(long j2) {
        return (16777215 & j2) == j2;
    }

    public static boolean isValidUint8(long j2) {
        return (255 & j2) == j2;
    }

    public static ProtocolVersion readVersion(InputStream inputStream) throws IOException {
        int i2 = inputStream.read();
        int i3 = inputStream.read();
        if (i3 >= 0) {
            return ProtocolVersion.get(i2, i3);
        }
        throw new EOFException();
    }

    public static int readVersionRaw(InputStream inputStream) throws IOException {
        int i2 = inputStream.read();
        int i3 = inputStream.read();
        if (i3 >= 0) {
            return i3 | (i2 << 8);
        }
        throw new EOFException();
    }

    public static void writeUint8(int i2, OutputStream outputStream) throws IOException {
        outputStream.write(i2);
    }

    public static void checkUint16(long j2) throws TlsFatalAlert {
        if (!isValidUint16(j2)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint24(long j2) throws TlsFatalAlert {
        if (!isValidUint24(j2)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint8(int i2) throws TlsFatalAlert {
        if (!isValidUint8(i2)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static boolean isValidUint8(short s2) {
        return (s2 & 255) == s2;
    }

    public static long readUint48(byte[] bArr, int i2) {
        int uint24 = readUint24(bArr, i2);
        int uint242 = readUint24(bArr, i2 + 3);
        return (((long) uint242) & BodyPartID.bodyIdMax) | ((((long) uint24) & BodyPartID.bodyIdMax) << 24);
    }

    public static short readUint8(byte[] bArr, int i2) {
        return (short) (bArr[i2] & 255);
    }

    public static void writeUint16(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) (i2 >>> 8);
        bArr[i3 + 1] = (byte) i2;
    }

    public static void writeUint16Array(int[] iArr, byte[] bArr, int i2) {
        for (int i3 : iArr) {
            writeUint16(i3, bArr, i2);
            i2 += 2;
        }
    }

    public static void writeUint8(short s2, byte[] bArr, int i2) {
        bArr[i2] = (byte) s2;
    }

    public static void writeUint8Array(short[] sArr, byte[] bArr, int i2) {
        for (short s2 : sArr) {
            writeUint8(s2, bArr, i2);
            i2++;
        }
    }

    public static void writeVersion(ProtocolVersion protocolVersion, byte[] bArr, int i2) {
        bArr[i2] = (byte) protocolVersion.getMajorVersion();
        bArr[i2 + 1] = (byte) protocolVersion.getMinorVersion();
    }

    public static byte[] PRF_legacy(byte[] bArr, byte[] bArr2, byte[] bArr3, int i2) {
        int length = (bArr.length + 1) / 2;
        byte[] bArr4 = new byte[length];
        byte[] bArr5 = new byte[length];
        System.arraycopy(bArr, 0, bArr4, 0, length);
        System.arraycopy(bArr, bArr.length - length, bArr5, 0, length);
        byte[] bArr6 = new byte[i2];
        byte[] bArr7 = new byte[i2];
        hmac_hash(createHash((short) 1), bArr4, bArr3, bArr6);
        hmac_hash(createHash((short) 2), bArr5, bArr3, bArr7);
        for (int i3 = 0; i3 < i2; i3++) {
            bArr6[i3] = (byte) (bArr6[i3] ^ bArr7[i3]);
        }
        return bArr6;
    }

    public static int readUint16(byte[] bArr, int i2) {
        return (bArr[i2 + 1] & 255) | ((bArr[i2] & 255) << 8);
    }

    public static void writeUint24(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) (i2 >>> 16);
        bArr[i3 + 1] = (byte) (i2 >>> 8);
        bArr[i3 + 2] = (byte) i2;
    }

    public static void writeUint8(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) i2;
    }

    public static void writeUint8ArrayWithUint8Length(short[] sArr, byte[] bArr, int i2) throws TlsFatalAlert {
        checkUint8(sArr.length);
        writeUint8(sArr.length, bArr, i2);
        writeUint8Array(sArr, bArr, i2 + 1);
    }

    public static void checkUint8(long j2) throws TlsFatalAlert {
        if (!isValidUint8(j2)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void readFully(byte[] bArr, InputStream inputStream) throws EOFException {
        int length = bArr.length;
        if (length > 0 && length != Streams.readFully(inputStream, bArr)) {
            throw new EOFException();
        }
    }

    public static int readUint24(byte[] bArr, int i2) {
        return (bArr[i2 + 2] & 255) | ((bArr[i2] & 255) << 16) | ((bArr[i2 + 1] & 255) << 8);
    }

    public static void writeUint16ArrayWithUint16Length(int[] iArr, byte[] bArr, int i2) throws TlsFatalAlert {
        int length = iArr.length * 2;
        checkUint16(length);
        writeUint16(length, bArr, i2);
        writeUint16Array(iArr, bArr, i2 + 2);
    }

    public static void writeUint32(long j2, byte[] bArr, int i2) {
        bArr[i2] = (byte) (j2 >>> 24);
        bArr[i2 + 1] = (byte) (j2 >>> 16);
        bArr[i2 + 2] = (byte) (j2 >>> 8);
        bArr[i2 + 3] = (byte) j2;
    }

    public static long readUint32(byte[] bArr, int i2) {
        return ((long) ((bArr[i2 + 3] & 255) | ((bArr[i2] & 255) << 24) | ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2 + 2] & 255) << 8))) & BodyPartID.bodyIdMax;
    }

    public static void writeUint48(long j2, byte[] bArr, int i2) {
        bArr[i2] = (byte) (j2 >>> 40);
        bArr[i2 + 1] = (byte) (j2 >>> 32);
        bArr[i2 + 2] = (byte) (j2 >>> 24);
        bArr[i2 + 3] = (byte) (j2 >>> 16);
        bArr[i2 + 4] = (byte) (j2 >>> 8);
        bArr[i2 + 5] = (byte) j2;
    }

    public static Digest createHash(SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        if (signatureAndHashAlgorithm == null) {
            return new CombinedHash();
        }
        return createHash(signatureAndHashAlgorithm.getHash());
    }

    public static void writeUint64(long j2, byte[] bArr, int i2) {
        bArr[i2] = (byte) (j2 >>> 56);
        bArr[i2 + 1] = (byte) (j2 >>> 48);
        bArr[i2 + 2] = (byte) (j2 >>> 40);
        bArr[i2 + 3] = (byte) (j2 >>> 32);
        bArr[i2 + 4] = (byte) (j2 >>> 24);
        bArr[i2 + 5] = (byte) (j2 >>> 16);
        bArr[i2 + 6] = (byte) (j2 >>> 8);
        bArr[i2 + 7] = (byte) j2;
    }
}

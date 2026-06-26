package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class DTLSProtocol {
    protected final SecureRandom secureRandom;

    public DTLSProtocol(SecureRandom secureRandom) {
        if (secureRandom == null) {
            throw new IllegalArgumentException("'secureRandom' cannot be null");
        }
        this.secureRandom = secureRandom;
    }

    public static void applyMaxFragmentLengthExtension(DTLSRecordLayer dTLSRecordLayer, short s2) throws TlsFatalAlert {
        if (s2 >= 0) {
            if (!MaxFragmentLength.isValid(s2)) {
                throw new TlsFatalAlert((short) 80);
            }
            dTLSRecordLayer.setPlaintextLimit(1 << (s2 + 8));
        }
    }

    public static short evaluateMaxFragmentLengthExtension(boolean z2, Hashtable hashtable, Hashtable hashtable2, short s2) throws TlsFatalAlert {
        short maxFragmentLengthExtension = TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable2);
        if (maxFragmentLengthExtension < 0 || (MaxFragmentLength.isValid(maxFragmentLengthExtension) && (z2 || maxFragmentLengthExtension == TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable)))) {
            return maxFragmentLengthExtension;
        }
        throw new TlsFatalAlert(s2);
    }

    public static byte[] generateCertificate(Certificate certificate) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        certificate.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] generateSupplementalData(Vector vector) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        TlsProtocol.writeSupplementalData(byteArrayOutputStream, vector);
        return byteArrayOutputStream.toByteArray();
    }

    public static void validateSelectedCipherSuite(int i2, short s2) throws TlsFatalAlert {
        int encryptionAlgorithm = TlsUtils.getEncryptionAlgorithm(i2);
        if (encryptionAlgorithm == 1 || encryptionAlgorithm == 2) {
            throw new TlsFatalAlert(s2);
        }
    }

    public void processFinished(byte[] bArr, byte[] bArr2) throws TlsFatalAlert, EOFException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        byte[] fully = TlsUtils.readFully(bArr2.length, byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        if (!Arrays.constantTimeAreEqual(bArr2, fully)) {
            throw new TlsFatalAlert((short) 40);
        }
    }
}

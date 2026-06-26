package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class TlsStreamCipher implements TlsCipher {
    protected TlsContext context;
    protected StreamCipher decryptCipher;
    protected StreamCipher encryptCipher;
    protected TlsMac readMac;
    protected boolean usesNonce;
    protected TlsMac writeMac;

    public TlsStreamCipher(TlsContext tlsContext, StreamCipher streamCipher, StreamCipher streamCipher2, Digest digest, Digest digest2, int i2, boolean z2) throws TlsFatalAlert {
        boolean zIsServer = tlsContext.isServer();
        this.context = tlsContext;
        this.usesNonce = z2;
        this.encryptCipher = streamCipher;
        this.decryptCipher = streamCipher2;
        int digestSize = (i2 * 2) + digest.getDigestSize() + digest2.getDigestSize();
        byte[] bArrCalculateKeyBlock = TlsUtils.calculateKeyBlock(tlsContext, digestSize);
        TlsMac tlsMac = new TlsMac(tlsContext, digest, bArrCalculateKeyBlock, 0, digest.getDigestSize());
        int digestSize2 = digest.getDigestSize();
        TlsMac tlsMac2 = new TlsMac(tlsContext, digest2, bArrCalculateKeyBlock, digestSize2, digest2.getDigestSize());
        int digestSize3 = digestSize2 + digest2.getDigestSize();
        CipherParameters keyParameter = new KeyParameter(bArrCalculateKeyBlock, digestSize3, i2);
        int i3 = digestSize3 + i2;
        CipherParameters keyParameter2 = new KeyParameter(bArrCalculateKeyBlock, i3, i2);
        if (i3 + i2 != digestSize) {
            throw new TlsFatalAlert((short) 80);
        }
        if (zIsServer) {
            this.writeMac = tlsMac2;
            this.readMac = tlsMac;
            this.encryptCipher = streamCipher2;
            this.decryptCipher = streamCipher;
            keyParameter2 = keyParameter;
            keyParameter = keyParameter2;
        } else {
            this.writeMac = tlsMac;
            this.readMac = tlsMac2;
            this.encryptCipher = streamCipher;
            this.decryptCipher = streamCipher2;
        }
        if (z2) {
            byte[] bArr = new byte[8];
            ParametersWithIV parametersWithIV = new ParametersWithIV(keyParameter, bArr);
            keyParameter2 = new ParametersWithIV(keyParameter2, bArr);
            keyParameter = parametersWithIV;
        }
        this.encryptCipher.init(true, keyParameter);
        this.decryptCipher.init(false, keyParameter2);
    }

    public void checkMAC(long j2, short s2, byte[] bArr, int i2, int i3, byte[] bArr2, int i4, int i5) throws TlsFatalAlert {
        if (!Arrays.constantTimeAreEqual(Arrays.copyOfRange(bArr, i2, i3), this.readMac.calculateMac(j2, s2, bArr2, i4, i5))) {
            throw new TlsFatalAlert((short) 20);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsCipher
    public byte[] decodeCiphertext(long j2, short s2, byte[] bArr, int i2, int i3) throws TlsFatalAlert {
        if (this.usesNonce) {
            updateIV(this.decryptCipher, false, j2);
        }
        int size = this.readMac.getSize();
        if (i3 < size) {
            throw new TlsFatalAlert((short) 50);
        }
        int i4 = i3 - size;
        byte[] bArr2 = new byte[i3];
        this.decryptCipher.processBytes(bArr, i2, i3, bArr2, 0);
        checkMAC(j2, s2, bArr2, i4, i3, bArr2, 0, i4);
        return Arrays.copyOfRange(bArr2, 0, i4);
    }

    @Override // org.spongycastle.crypto.tls.TlsCipher
    public byte[] encodePlaintext(long j2, short s2, byte[] bArr, int i2, int i3) {
        if (this.usesNonce) {
            updateIV(this.encryptCipher, true, j2);
        }
        byte[] bArr2 = new byte[i3 + this.writeMac.getSize()];
        this.encryptCipher.processBytes(bArr, i2, i3, bArr2, 0);
        byte[] bArrCalculateMac = this.writeMac.calculateMac(j2, s2, bArr, i2, i3);
        this.encryptCipher.processBytes(bArrCalculateMac, 0, bArrCalculateMac.length, bArr2, i3);
        return bArr2;
    }

    @Override // org.spongycastle.crypto.tls.TlsCipher
    public int getPlaintextLimit(int i2) {
        return i2 - this.writeMac.getSize();
    }

    public void updateIV(StreamCipher streamCipher, boolean z2, long j2) {
        byte[] bArr = new byte[8];
        TlsUtils.writeUint64(j2, bArr, 0);
        streamCipher.init(z2, new ParametersWithIV(null, bArr));
    }
}

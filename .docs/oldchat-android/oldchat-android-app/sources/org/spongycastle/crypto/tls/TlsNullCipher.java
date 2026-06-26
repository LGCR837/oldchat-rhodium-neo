package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.Digest;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class TlsNullCipher implements TlsCipher {
    protected TlsContext context;
    protected TlsMac readMac;
    protected TlsMac writeMac;

    public TlsNullCipher(TlsContext tlsContext) {
        this.context = tlsContext;
        this.writeMac = null;
        this.readMac = null;
    }

    @Override // org.spongycastle.crypto.tls.TlsCipher
    public byte[] decodeCiphertext(long j2, short s2, byte[] bArr, int i2, int i3) throws TlsFatalAlert {
        TlsMac tlsMac = this.readMac;
        if (tlsMac == null) {
            return Arrays.copyOfRange(bArr, i2, i3 + i2);
        }
        int size = tlsMac.getSize();
        if (i3 < size) {
            throw new TlsFatalAlert((short) 50);
        }
        int i4 = i3 - size;
        int i5 = i2 + i4;
        if (Arrays.constantTimeAreEqual(Arrays.copyOfRange(bArr, i5, i3 + i2), this.readMac.calculateMac(j2, s2, bArr, i2, i4))) {
            return Arrays.copyOfRange(bArr, i2, i5);
        }
        throw new TlsFatalAlert((short) 20);
    }

    @Override // org.spongycastle.crypto.tls.TlsCipher
    public byte[] encodePlaintext(long j2, short s2, byte[] bArr, int i2, int i3) {
        TlsMac tlsMac = this.writeMac;
        if (tlsMac == null) {
            return Arrays.copyOfRange(bArr, i2, i3 + i2);
        }
        byte[] bArrCalculateMac = tlsMac.calculateMac(j2, s2, bArr, i2, i3);
        byte[] bArr2 = new byte[bArrCalculateMac.length + i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        System.arraycopy(bArrCalculateMac, 0, bArr2, i3, bArrCalculateMac.length);
        return bArr2;
    }

    @Override // org.spongycastle.crypto.tls.TlsCipher
    public int getPlaintextLimit(int i2) {
        TlsMac tlsMac = this.writeMac;
        return tlsMac != null ? i2 - tlsMac.getSize() : i2;
    }

    public TlsNullCipher(TlsContext tlsContext, Digest digest, Digest digest2) throws TlsFatalAlert {
        TlsMac tlsMac;
        TlsMac tlsMac2;
        if ((digest == null) == (digest2 == null)) {
            this.context = tlsContext;
            if (digest != null) {
                int digestSize = digest.getDigestSize() + digest2.getDigestSize();
                byte[] bArrCalculateKeyBlock = TlsUtils.calculateKeyBlock(tlsContext, digestSize);
                tlsMac = new TlsMac(tlsContext, digest, bArrCalculateKeyBlock, 0, digest.getDigestSize());
                int digestSize2 = digest.getDigestSize();
                tlsMac2 = new TlsMac(tlsContext, digest2, bArrCalculateKeyBlock, digestSize2, digest2.getDigestSize());
                if (digestSize2 + digest2.getDigestSize() != digestSize) {
                    throw new TlsFatalAlert((short) 80);
                }
            } else {
                tlsMac = null;
                tlsMac2 = null;
            }
            if (tlsContext.isServer()) {
                this.writeMac = tlsMac2;
                this.readMac = tlsMac;
                return;
            } else {
                this.writeMac = tlsMac;
                this.readMac = tlsMac2;
                return;
            }
        }
        throw new TlsFatalAlert((short) 80);
    }
}

package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.modes.AEADBlockCipher;
import org.spongycastle.crypto.params.AEADParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class TlsAEADCipher implements TlsCipher {
    static final int NONCE_DRAFT_CHACHA20_POLY1305 = 2;
    public static final int NONCE_RFC5288 = 1;
    protected TlsContext context;
    protected AEADBlockCipher decryptCipher;
    protected byte[] decryptImplicitNonce;
    protected AEADBlockCipher encryptCipher;
    protected byte[] encryptImplicitNonce;
    protected int macSize;
    protected int nonceMode;
    protected int record_iv_length;

    public TlsAEADCipher(TlsContext tlsContext, AEADBlockCipher aEADBlockCipher, AEADBlockCipher aEADBlockCipher2, int i2, int i3) {
        this(tlsContext, aEADBlockCipher, aEADBlockCipher2, i2, i3, 1);
    }

    @Override // org.spongycastle.crypto.tls.TlsCipher
    public byte[] decodeCiphertext(long j2, short s2, byte[] bArr, int i2, int i3) throws TlsFatalAlert {
        if (getPlaintextLimit(i3) < 0) {
            throw new TlsFatalAlert((short) 50);
        }
        byte[] bArr2 = this.decryptImplicitNonce;
        int length = bArr2.length + this.record_iv_length;
        byte[] bArr3 = new byte[length];
        int i4 = this.nonceMode;
        if (i4 == 1) {
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
            int i5 = this.record_iv_length;
            System.arraycopy(bArr, i2, bArr3, length - i5, i5);
        } else {
            if (i4 != 2) {
                throw new TlsFatalAlert((short) 80);
            }
            TlsUtils.writeUint64(j2, bArr3, length - 8);
            int i6 = 0;
            while (true) {
                byte[] bArr4 = this.decryptImplicitNonce;
                if (i6 >= bArr4.length) {
                    break;
                }
                bArr3[i6] = (byte) (bArr4[i6] ^ bArr3[i6]);
                i6++;
            }
        }
        int i7 = this.record_iv_length;
        int i8 = i2 + i7;
        int i9 = i3 - i7;
        int outputSize = this.decryptCipher.getOutputSize(i9);
        byte[] bArr5 = new byte[outputSize];
        try {
            this.decryptCipher.init(false, new AEADParameters(null, this.macSize * 8, bArr3, getAdditionalData(j2, s2, outputSize)));
            int iProcessBytes = this.decryptCipher.processBytes(bArr, i8, i9, bArr5, 0);
            if (iProcessBytes + this.decryptCipher.doFinal(bArr5, iProcessBytes) == outputSize) {
                return bArr5;
            }
            throw new TlsFatalAlert((short) 80);
        } catch (Exception e2) {
            throw new TlsFatalAlert((short) 20, e2);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsCipher
    public byte[] encodePlaintext(long j2, short s2, byte[] bArr, int i2, int i3) throws TlsFatalAlert {
        byte[] bArr2 = this.encryptImplicitNonce;
        int length = bArr2.length + this.record_iv_length;
        byte[] bArr3 = new byte[length];
        int i4 = this.nonceMode;
        if (i4 == 1) {
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
            TlsUtils.writeUint64(j2, bArr3, this.encryptImplicitNonce.length);
        } else {
            if (i4 != 2) {
                throw new TlsFatalAlert((short) 80);
            }
            TlsUtils.writeUint64(j2, bArr3, length - 8);
            int i5 = 0;
            while (true) {
                byte[] bArr4 = this.encryptImplicitNonce;
                if (i5 >= bArr4.length) {
                    break;
                }
                bArr3[i5] = (byte) (bArr4[i5] ^ bArr3[i5]);
                i5++;
            }
        }
        int outputSize = this.encryptCipher.getOutputSize(i3);
        int i6 = this.record_iv_length;
        int i7 = i6 + outputSize;
        byte[] bArr5 = new byte[i7];
        if (i6 != 0) {
            System.arraycopy(bArr3, length - i6, bArr5, 0, i6);
        }
        int i8 = this.record_iv_length;
        try {
            this.encryptCipher.init(true, new AEADParameters(null, this.macSize * 8, bArr3, getAdditionalData(j2, s2, i3)));
            int iProcessBytes = i8 + this.encryptCipher.processBytes(bArr, i2, i3, bArr5, i8);
            if (iProcessBytes + this.encryptCipher.doFinal(bArr5, iProcessBytes) == i7) {
                return bArr5;
            }
            throw new TlsFatalAlert((short) 80);
        } catch (Exception e2) {
            throw new TlsFatalAlert((short) 80, e2);
        }
    }

    public byte[] getAdditionalData(long j2, short s2, int i2) {
        byte[] bArr = new byte[13];
        TlsUtils.writeUint64(j2, bArr, 0);
        TlsUtils.writeUint8(s2, bArr, 8);
        TlsUtils.writeVersion(this.context.getServerVersion(), bArr, 9);
        TlsUtils.writeUint16(i2, bArr, 11);
        return bArr;
    }

    @Override // org.spongycastle.crypto.tls.TlsCipher
    public int getPlaintextLimit(int i2) {
        return (i2 - this.macSize) - this.record_iv_length;
    }

    public TlsAEADCipher(TlsContext tlsContext, AEADBlockCipher aEADBlockCipher, AEADBlockCipher aEADBlockCipher2, int i2, int i3, int i4) throws TlsFatalAlert {
        int i5;
        if (!TlsUtils.isTLSv12(tlsContext)) {
            throw new TlsFatalAlert((short) 80);
        }
        this.nonceMode = i4;
        if (i4 == 1) {
            this.record_iv_length = 8;
            i5 = 4;
        } else {
            if (i4 != 2) {
                throw new TlsFatalAlert((short) 80);
            }
            this.record_iv_length = 0;
            i5 = 12;
        }
        this.context = tlsContext;
        this.macSize = i3;
        int i6 = (i2 * 2) + (i5 * 2);
        byte[] bArrCalculateKeyBlock = TlsUtils.calculateKeyBlock(tlsContext, i6);
        KeyParameter keyParameter = new KeyParameter(bArrCalculateKeyBlock, 0, i2);
        KeyParameter keyParameter2 = new KeyParameter(bArrCalculateKeyBlock, i2, i2);
        int i7 = i2 + i2;
        int i8 = i7 + i5;
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArrCalculateKeyBlock, i7, i8);
        int i9 = i8 + i5;
        byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArrCalculateKeyBlock, i8, i9);
        if (i9 != i6) {
            throw new TlsFatalAlert((short) 80);
        }
        if (tlsContext.isServer()) {
            this.encryptCipher = aEADBlockCipher2;
            this.decryptCipher = aEADBlockCipher;
            this.encryptImplicitNonce = bArrCopyOfRange2;
            this.decryptImplicitNonce = bArrCopyOfRange;
            keyParameter2 = keyParameter;
            keyParameter = keyParameter2;
        } else {
            this.encryptCipher = aEADBlockCipher;
            this.decryptCipher = aEADBlockCipher2;
            this.encryptImplicitNonce = bArrCopyOfRange;
            this.decryptImplicitNonce = bArrCopyOfRange2;
        }
        byte[] bArr = new byte[i5 + this.record_iv_length];
        int i10 = i3 * 8;
        this.encryptCipher.init(true, new AEADParameters(keyParameter, i10, bArr));
        this.decryptCipher.init(false, new AEADParameters(keyParameter2, i10, bArr));
    }
}

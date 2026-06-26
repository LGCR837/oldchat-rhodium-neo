package org.spongycastle.crypto.tls;

import org.spongycastle.asn1.cmc.BodyPartID;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.engines.ChaCha7539Engine;
import org.spongycastle.crypto.macs.Poly1305;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public class Chacha20Poly1305 implements TlsCipher {
    private static final byte[] ZEROES = new byte[15];
    protected TlsContext context;
    protected ChaCha7539Engine decryptCipher;
    protected byte[] decryptIV;
    protected ChaCha7539Engine encryptCipher;
    protected byte[] encryptIV;

    public Chacha20Poly1305(TlsContext tlsContext) throws TlsFatalAlert {
        if (!TlsUtils.isTLSv12(tlsContext)) {
            throw new TlsFatalAlert((short) 80);
        }
        this.context = tlsContext;
        byte[] bArrCalculateKeyBlock = TlsUtils.calculateKeyBlock(tlsContext, 88);
        KeyParameter keyParameter = new KeyParameter(bArrCalculateKeyBlock, 0, 32);
        KeyParameter keyParameter2 = new KeyParameter(bArrCalculateKeyBlock, 32, 32);
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArrCalculateKeyBlock, 64, 76);
        byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArrCalculateKeyBlock, 76, 88);
        this.encryptCipher = new ChaCha7539Engine();
        this.decryptCipher = new ChaCha7539Engine();
        if (tlsContext.isServer()) {
            this.encryptIV = bArrCopyOfRange2;
            this.decryptIV = bArrCopyOfRange;
            keyParameter2 = keyParameter;
            keyParameter = keyParameter2;
        } else {
            this.encryptIV = bArrCopyOfRange;
            this.decryptIV = bArrCopyOfRange2;
        }
        this.encryptCipher.init(true, new ParametersWithIV(keyParameter, this.encryptIV));
        this.decryptCipher.init(false, new ParametersWithIV(keyParameter2, this.decryptIV));
    }

    public byte[] calculateNonce(long j2, byte[] bArr) {
        byte[] bArr2 = new byte[12];
        TlsUtils.writeUint64(j2, bArr2, 4);
        for (int i2 = 0; i2 < 12; i2++) {
            bArr2[i2] = (byte) (bArr2[i2] ^ bArr[i2]);
        }
        return bArr2;
    }

    public byte[] calculateRecordMAC(KeyParameter keyParameter, byte[] bArr, byte[] bArr2, int i2, int i3) {
        Poly1305 poly1305 = new Poly1305();
        poly1305.init(keyParameter);
        updateRecordMACText(poly1305, bArr, 0, bArr.length);
        updateRecordMACText(poly1305, bArr2, i2, i3);
        updateRecordMACLength(poly1305, bArr.length);
        updateRecordMACLength(poly1305, i3);
        byte[] bArr3 = new byte[poly1305.getMacSize()];
        poly1305.doFinal(bArr3, 0);
        return bArr3;
    }

    @Override // org.spongycastle.crypto.tls.TlsCipher
    public byte[] decodeCiphertext(long j2, short s2, byte[] bArr, int i2, int i3) throws TlsFatalAlert {
        if (getPlaintextLimit(i3) < 0) {
            throw new TlsFatalAlert((short) 50);
        }
        int i4 = i3 - 16;
        if (!Arrays.constantTimeAreEqual(calculateRecordMAC(initRecord(this.decryptCipher, false, j2, this.decryptIV), getAdditionalData(j2, s2, i4), bArr, i2, i4), Arrays.copyOfRange(bArr, i2 + i4, i2 + i3))) {
            throw new TlsFatalAlert((short) 20);
        }
        byte[] bArr2 = new byte[i4];
        this.decryptCipher.processBytes(bArr, i2, i4, bArr2, 0);
        return bArr2;
    }

    @Override // org.spongycastle.crypto.tls.TlsCipher
    public byte[] encodePlaintext(long j2, short s2, byte[] bArr, int i2, int i3) {
        KeyParameter keyParameterInitRecord = initRecord(this.encryptCipher, true, j2, this.encryptIV);
        byte[] bArr2 = new byte[i3 + 16];
        this.encryptCipher.processBytes(bArr, i2, i3, bArr2, 0);
        byte[] bArrCalculateRecordMAC = calculateRecordMAC(keyParameterInitRecord, getAdditionalData(j2, s2, i3), bArr2, 0, i3);
        System.arraycopy(bArrCalculateRecordMAC, 0, bArr2, i3, bArrCalculateRecordMAC.length);
        return bArr2;
    }

    public KeyParameter generateRecordMACKey(StreamCipher streamCipher) {
        byte[] bArr = new byte[64];
        streamCipher.processBytes(bArr, 0, 64, bArr, 0);
        KeyParameter keyParameter = new KeyParameter(bArr, 0, 32);
        Arrays.fill(bArr, (byte) 0);
        return keyParameter;
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
        return i2 - 16;
    }

    public KeyParameter initRecord(StreamCipher streamCipher, boolean z2, long j2, byte[] bArr) {
        streamCipher.init(z2, new ParametersWithIV(null, calculateNonce(j2, bArr)));
        return generateRecordMACKey(streamCipher);
    }

    public void updateRecordMACLength(Mac mac, int i2) {
        byte[] bArrLongToLittleEndian = Pack.longToLittleEndian(((long) i2) & BodyPartID.bodyIdMax);
        mac.update(bArrLongToLittleEndian, 0, bArrLongToLittleEndian.length);
    }

    public void updateRecordMACText(Mac mac, byte[] bArr, int i2, int i3) {
        mac.update(bArr, i2, i3);
        int i4 = i3 % 16;
        if (i4 != 0) {
            mac.update(ZEROES, 0, 16 - i4);
        }
    }
}

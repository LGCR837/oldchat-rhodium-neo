package org.spongycastle.crypto.tls;

import java.security.SecureRandom;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class TlsBlockCipher implements TlsCipher {
    protected TlsContext context;
    protected BlockCipher decryptCipher;
    protected BlockCipher encryptCipher;
    protected boolean encryptThenMAC;
    protected byte[] randomData = new byte[256];
    protected TlsMac readMac;
    protected boolean useExplicitIV;
    protected TlsMac writeMac;

    public TlsBlockCipher(TlsContext tlsContext, BlockCipher blockCipher, BlockCipher blockCipher2, Digest digest, Digest digest2, int i2) throws TlsFatalAlert {
        byte[] bArrCopyOfRange;
        byte[] bArrCopyOfRange2;
        ParametersWithIV parametersWithIV;
        ParametersWithIV parametersWithIV2;
        this.context = tlsContext;
        tlsContext.getNonceRandomGenerator().nextBytes(this.randomData);
        this.useExplicitIV = TlsUtils.isTLSv11(tlsContext);
        this.encryptThenMAC = tlsContext.getSecurityParameters().encryptThenMAC;
        int digestSize = (i2 * 2) + digest.getDigestSize() + digest2.getDigestSize();
        int blockSize = this.useExplicitIV ? digestSize : digestSize + blockCipher.getBlockSize() + blockCipher2.getBlockSize();
        byte[] bArrCalculateKeyBlock = TlsUtils.calculateKeyBlock(tlsContext, blockSize);
        TlsMac tlsMac = new TlsMac(tlsContext, digest, bArrCalculateKeyBlock, 0, digest.getDigestSize());
        int digestSize2 = digest.getDigestSize();
        TlsMac tlsMac2 = new TlsMac(tlsContext, digest2, bArrCalculateKeyBlock, digestSize2, digest2.getDigestSize());
        int digestSize3 = digestSize2 + digest2.getDigestSize();
        KeyParameter keyParameter = new KeyParameter(bArrCalculateKeyBlock, digestSize3, i2);
        int i3 = digestSize3 + i2;
        KeyParameter keyParameter2 = new KeyParameter(bArrCalculateKeyBlock, i3, i2);
        int blockSize2 = i3 + i2;
        if (this.useExplicitIV) {
            bArrCopyOfRange = new byte[blockCipher.getBlockSize()];
            bArrCopyOfRange2 = new byte[blockCipher2.getBlockSize()];
        } else {
            bArrCopyOfRange = Arrays.copyOfRange(bArrCalculateKeyBlock, blockSize2, blockCipher.getBlockSize() + blockSize2);
            int blockSize3 = blockSize2 + blockCipher.getBlockSize();
            bArrCopyOfRange2 = Arrays.copyOfRange(bArrCalculateKeyBlock, blockSize3, blockCipher2.getBlockSize() + blockSize3);
            blockSize2 = blockSize3 + blockCipher2.getBlockSize();
        }
        if (blockSize2 != blockSize) {
            throw new TlsFatalAlert((short) 80);
        }
        if (tlsContext.isServer()) {
            this.writeMac = tlsMac2;
            this.readMac = tlsMac;
            this.encryptCipher = blockCipher2;
            this.decryptCipher = blockCipher;
            parametersWithIV = new ParametersWithIV(keyParameter2, bArrCopyOfRange2);
            parametersWithIV2 = new ParametersWithIV(keyParameter, bArrCopyOfRange);
        } else {
            this.writeMac = tlsMac;
            this.readMac = tlsMac2;
            this.encryptCipher = blockCipher;
            this.decryptCipher = blockCipher2;
            parametersWithIV = new ParametersWithIV(keyParameter, bArrCopyOfRange);
            parametersWithIV2 = new ParametersWithIV(keyParameter2, bArrCopyOfRange2);
        }
        this.encryptCipher.init(true, parametersWithIV);
        this.decryptCipher.init(false, parametersWithIV2);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0031 A[LOOP:0: B:16:0x002d->B:18:0x0031, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int checkPaddingConstantTime(byte[] r5, int r6, int r7, int r8, int r9) {
        /*
            r4 = this;
            int r6 = r6 + r7
            int r0 = r6 + (-1)
            r0 = r5[r0]
            r1 = r0 & 255(0xff, float:3.57E-43)
            int r1 = r1 + 1
            org.spongycastle.crypto.tls.TlsContext r2 = r4.context
            boolean r2 = org.spongycastle.crypto.tls.TlsUtils.isSSL(r2)
            r3 = 0
            if (r2 == 0) goto L14
            if (r1 > r8) goto L17
        L14:
            int r9 = r9 + r1
            if (r9 <= r7) goto L1b
        L17:
            r5 = 0
            r8 = 0
        L19:
            r1 = 0
            goto L2b
        L1b:
            int r7 = r6 - r1
            r8 = 0
        L1e:
            int r9 = r7 + 1
            r7 = r5[r7]
            r7 = r7 ^ r0
            r7 = r7 | r8
            byte r8 = (byte) r7
            if (r9 < r6) goto L41
            r5 = r1
            if (r8 == 0) goto L2b
            goto L19
        L2b:
            byte[] r6 = r4.randomData
        L2d:
            r7 = 256(0x100, float:3.59E-43)
            if (r5 >= r7) goto L3a
            int r7 = r5 + 1
            r5 = r6[r5]
            r5 = r5 ^ r0
            r5 = r5 | r8
            byte r8 = (byte) r5
            r5 = r7
            goto L2d
        L3a:
            r5 = r6[r3]
            r5 = r5 ^ r8
            byte r5 = (byte) r5
            r6[r3] = r5
            return r1
        L41:
            r7 = r9
            goto L1e
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.tls.TlsBlockCipher.checkPaddingConstantTime(byte[], int, int, int, int):int");
    }

    public int chooseExtraPadBlocks(SecureRandom secureRandom, int i2) {
        return Math.min(lowestBitSet(secureRandom.nextInt()), i2);
    }

    @Override // org.spongycastle.crypto.tls.TlsCipher
    public byte[] decodeCiphertext(long j2, short s2, byte[] bArr, int i2, int i3) throws TlsFatalAlert {
        int i4;
        int i5 = i2;
        int blockSize = this.decryptCipher.getBlockSize();
        int size = this.readMac.getSize();
        int iMax = this.encryptThenMAC ? blockSize + size : Math.max(blockSize, size + 1);
        if (this.useExplicitIV) {
            iMax += blockSize;
        }
        if (i3 < iMax) {
            throw new TlsFatalAlert((short) 50);
        }
        boolean z2 = this.encryptThenMAC;
        int i6 = z2 ? i3 - size : i3;
        if (i6 % blockSize != 0) {
            throw new TlsFatalAlert((short) 21);
        }
        if (z2) {
            int i7 = i5 + i3;
            if (!Arrays.constantTimeAreEqual(this.readMac.calculateMac(j2, s2, bArr, i2, i3 - size), Arrays.copyOfRange(bArr, i7 - size, i7))) {
                throw new TlsFatalAlert((short) 20);
            }
        }
        if (this.useExplicitIV) {
            this.decryptCipher.init(false, new ParametersWithIV(null, bArr, i5, blockSize));
            i5 += blockSize;
            i6 -= blockSize;
        }
        int i8 = i5;
        int i9 = i6;
        for (int i10 = 0; i10 < i9; i10 += blockSize) {
            int i11 = i8 + i10;
            this.decryptCipher.processBlock(bArr, i11, bArr, i11);
        }
        int iCheckPaddingConstantTime = checkPaddingConstantTime(bArr, i8, i9, blockSize, this.encryptThenMAC ? 0 : size);
        boolean zConstantTimeAreEqual = iCheckPaddingConstantTime == 0;
        int i12 = i9 - iCheckPaddingConstantTime;
        if (this.encryptThenMAC) {
            i4 = i8;
        } else {
            i12 -= size;
            int i13 = i8 + i12;
            i4 = i8;
            zConstantTimeAreEqual |= true ^ Arrays.constantTimeAreEqual(this.readMac.calculateMacConstantTime(j2, s2, bArr, i8, i12, i9 - size, this.randomData), Arrays.copyOfRange(bArr, i13, i13 + size));
        }
        if (zConstantTimeAreEqual) {
            throw new TlsFatalAlert((short) 20);
        }
        return Arrays.copyOfRange(bArr, i4, i4 + i12);
    }

    @Override // org.spongycastle.crypto.tls.TlsCipher
    public byte[] encodePlaintext(long j2, short s2, byte[] bArr, int i2, int i3) {
        byte[] bArr2;
        int i4;
        int i5;
        int blockSize = this.encryptCipher.getBlockSize();
        int size = this.writeMac.getSize();
        ProtocolVersion serverVersion = this.context.getServerVersion();
        boolean z2 = this.encryptThenMAC;
        int iChooseExtraPadBlocks = (blockSize - 1) - ((!z2 ? i3 + size : i3) % blockSize);
        if ((z2 || !this.context.getSecurityParameters().truncatedHMac) && !serverVersion.isDTLS() && !serverVersion.isSSL()) {
            iChooseExtraPadBlocks += chooseExtraPadBlocks(this.context.getSecureRandom(), (255 - iChooseExtraPadBlocks) / blockSize) * blockSize;
        }
        int i6 = iChooseExtraPadBlocks;
        int i7 = size + i3 + i6 + 1;
        boolean z3 = this.useExplicitIV;
        if (z3) {
            i7 += blockSize;
        }
        byte[] bArr3 = new byte[i7];
        if (z3) {
            byte[] bArr4 = new byte[blockSize];
            this.context.getNonceRandomGenerator().nextBytes(bArr4);
            this.encryptCipher.init(true, new ParametersWithIV(null, bArr4));
            System.arraycopy(bArr4, 0, bArr3, 0, blockSize);
            bArr2 = bArr;
            i4 = i2;
            i5 = blockSize;
        } else {
            bArr2 = bArr;
            i4 = i2;
            i5 = 0;
        }
        System.arraycopy(bArr2, i4, bArr3, i5, i3);
        int length = i5 + i3;
        if (!this.encryptThenMAC) {
            byte[] bArrCalculateMac = this.writeMac.calculateMac(j2, s2, bArr, i2, i3);
            System.arraycopy(bArrCalculateMac, 0, bArr3, length, bArrCalculateMac.length);
            length += bArrCalculateMac.length;
        }
        int i8 = length;
        int i9 = 0;
        while (i9 <= i6) {
            bArr3[i8] = (byte) i6;
            i9++;
            i8++;
        }
        while (i5 < i8) {
            this.encryptCipher.processBlock(bArr3, i5, bArr3, i5);
            i5 += blockSize;
        }
        if (!this.encryptThenMAC) {
            return bArr3;
        }
        byte[] bArrCalculateMac2 = this.writeMac.calculateMac(j2, s2, bArr3, 0, i8);
        System.arraycopy(bArrCalculateMac2, 0, bArr3, i8, bArrCalculateMac2.length);
        return bArr3;
    }

    @Override // org.spongycastle.crypto.tls.TlsCipher
    public int getPlaintextLimit(int i2) {
        int i3;
        int blockSize = this.encryptCipher.getBlockSize();
        int size = this.writeMac.getSize();
        if (this.useExplicitIV) {
            i2 -= blockSize;
        }
        if (this.encryptThenMAC) {
            int i4 = i2 - size;
            i3 = i4 - (i4 % blockSize);
        } else {
            i3 = (i2 - (i2 % blockSize)) - size;
        }
        return i3 - 1;
    }

    public TlsMac getReadMac() {
        return this.readMac;
    }

    public TlsMac getWriteMac() {
        return this.writeMac;
    }

    public int lowestBitSet(int i2) {
        if (i2 == 0) {
            return 32;
        }
        int i3 = 0;
        while ((i2 & 1) == 0) {
            i3++;
            i2 >>= 1;
        }
        return i3;
    }
}

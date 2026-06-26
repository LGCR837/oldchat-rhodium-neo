package org.spongycastle.crypto.macs;

import org.spongycastle.asn1.cmc.BodyPartID;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public class Poly1305 implements Mac {
    private static final int BLOCK_SIZE = 16;
    private final BlockCipher cipher;
    private final byte[] currentBlock;
    private int currentBlockOffset;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    private int f7356h0;
    private int h1;
    private int h2;
    private int h3;
    private int h4;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    private int f7357k0;
    private int k1;
    private int k2;
    private int k3;
    private int r0;
    private int r1;
    private int r2;
    private int r3;
    private int r4;
    private int s1;
    private int s2;
    private int s3;
    private int s4;
    private final byte[] singleByte;

    public Poly1305() {
        this.singleByte = new byte[1];
        this.currentBlock = new byte[16];
        this.currentBlockOffset = 0;
        this.cipher = null;
    }

    private static final long mul32x32_64(int i2, int i3) {
        return (((long) i2) & BodyPartID.bodyIdMax) * ((long) i3);
    }

    private void processBlock() {
        int i2 = this.currentBlockOffset;
        if (i2 < 16) {
            this.currentBlock[i2] = 1;
            for (int i3 = i2 + 1; i3 < 16; i3++) {
                this.currentBlock[i3] = 0;
            }
        }
        long jLittleEndianToInt = Pack.littleEndianToInt(this.currentBlock, 0);
        long j2 = jLittleEndianToInt & BodyPartID.bodyIdMax;
        long jLittleEndianToInt2 = ((long) Pack.littleEndianToInt(this.currentBlock, 4)) & BodyPartID.bodyIdMax;
        long jLittleEndianToInt3 = ((long) Pack.littleEndianToInt(this.currentBlock, 8)) & BodyPartID.bodyIdMax;
        long jLittleEndianToInt4 = BodyPartID.bodyIdMax & ((long) Pack.littleEndianToInt(this.currentBlock, 12));
        int i4 = (int) (((long) this.f7356h0) + (jLittleEndianToInt & 67108863));
        this.f7356h0 = i4;
        this.h1 = (int) (((long) this.h1) + ((((jLittleEndianToInt2 << 32) | j2) >>> 26) & 67108863));
        this.h2 = (int) (((long) this.h2) + (((jLittleEndianToInt2 | (jLittleEndianToInt3 << 32)) >>> 20) & 67108863));
        this.h3 = (int) (((long) this.h3) + ((((jLittleEndianToInt4 << 32) | jLittleEndianToInt3) >>> 14) & 67108863));
        int i5 = (int) (((long) this.h4) + (jLittleEndianToInt4 >>> 8));
        this.h4 = i5;
        if (this.currentBlockOffset == 16) {
            this.h4 = i5 + 16777216;
        }
        long jMul32x32_64 = mul32x32_64(i4, this.r0) + mul32x32_64(this.h1, this.s4) + mul32x32_64(this.h2, this.s3) + mul32x32_64(this.h3, this.s2) + mul32x32_64(this.h4, this.s1);
        long jMul32x32_642 = mul32x32_64(this.f7356h0, this.r1) + mul32x32_64(this.h1, this.r0) + mul32x32_64(this.h2, this.s4) + mul32x32_64(this.h3, this.s3) + mul32x32_64(this.h4, this.s2);
        long jMul32x32_643 = mul32x32_64(this.f7356h0, this.r2) + mul32x32_64(this.h1, this.r1) + mul32x32_64(this.h2, this.r0) + mul32x32_64(this.h3, this.s4) + mul32x32_64(this.h4, this.s3);
        long jMul32x32_644 = mul32x32_64(this.f7356h0, this.r3) + mul32x32_64(this.h1, this.r2) + mul32x32_64(this.h2, this.r1) + mul32x32_64(this.h3, this.r0) + mul32x32_64(this.h4, this.s4);
        long jMul32x32_645 = mul32x32_64(this.f7356h0, this.r4) + mul32x32_64(this.h1, this.r3) + mul32x32_64(this.h2, this.r2) + mul32x32_64(this.h3, this.r1) + mul32x32_64(this.h4, this.r0);
        long j3 = jMul32x32_642 + (jMul32x32_64 >>> 26);
        long j4 = jMul32x32_643 + (j3 >>> 26);
        this.h2 = ((int) j4) & 67108863;
        long j5 = jMul32x32_644 + (j4 >>> 26);
        this.h3 = ((int) j5) & 67108863;
        long j6 = jMul32x32_645 + (j5 >>> 26);
        this.h4 = ((int) j6) & 67108863;
        int i6 = (((int) jMul32x32_64) & 67108863) + (((int) (j6 >>> 26)) * 5);
        this.h1 = (((int) j3) & 67108863) + (i6 >>> 26);
        this.f7356h0 = i6 & 67108863;
    }

    private void setKey(byte[] bArr, byte[] bArr2) {
        if (bArr.length != 32) {
            throw new IllegalArgumentException("Poly1305 key must be 256 bits.");
        }
        int i2 = 16;
        if (this.cipher != null && (bArr2 == null || bArr2.length != 16)) {
            throw new IllegalArgumentException("Poly1305 requires a 128 bit IV.");
        }
        int iLittleEndianToInt = Pack.littleEndianToInt(bArr, 0);
        int iLittleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
        int iLittleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
        int iLittleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
        this.r0 = 67108863 & iLittleEndianToInt;
        int i3 = ((iLittleEndianToInt >>> 26) | (iLittleEndianToInt2 << 6)) & 67108611;
        this.r1 = i3;
        int i4 = ((iLittleEndianToInt2 >>> 20) | (iLittleEndianToInt3 << 12)) & 67092735;
        this.r2 = i4;
        int i5 = ((iLittleEndianToInt3 >>> 14) | (iLittleEndianToInt4 << 18)) & 66076671;
        this.r3 = i5;
        int i6 = (iLittleEndianToInt4 >>> 8) & 1048575;
        this.r4 = i6;
        this.s1 = i3 * 5;
        this.s2 = i4 * 5;
        this.s3 = i5 * 5;
        this.s4 = i6 * 5;
        BlockCipher blockCipher = this.cipher;
        if (blockCipher != null) {
            byte[] bArr3 = new byte[16];
            blockCipher.init(true, new KeyParameter(bArr, 16, 16));
            this.cipher.processBlock(bArr2, 0, bArr3, 0);
            bArr = bArr3;
            i2 = 0;
        }
        this.f7357k0 = Pack.littleEndianToInt(bArr, i2);
        this.k1 = Pack.littleEndianToInt(bArr, i2 + 4);
        this.k2 = Pack.littleEndianToInt(bArr, i2 + 8);
        this.k3 = Pack.littleEndianToInt(bArr, i2 + 12);
    }

    @Override // org.spongycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i2) {
        if (i2 + 16 > bArr.length) {
            throw new OutputLengthException("Output buffer is too short.");
        }
        if (this.currentBlockOffset > 0) {
            processBlock();
        }
        int i3 = this.h1;
        int i4 = this.f7356h0;
        int i5 = i3 + (i4 >>> 26);
        int i6 = this.h2 + (i5 >>> 26);
        int i7 = this.h3 + (i6 >>> 26);
        int i8 = i6 & 67108863;
        int i9 = this.h4 + (i7 >>> 26);
        int i10 = i7 & 67108863;
        int i11 = (i4 & 67108863) + ((i9 >>> 26) * 5);
        int i12 = i9 & 67108863;
        int i13 = (i5 & 67108863) + (i11 >>> 26);
        int i14 = i11 & 67108863;
        int i15 = i14 + 5;
        int i16 = (i15 >>> 26) + i13;
        int i17 = (i16 >>> 26) + i8;
        int i18 = (i17 >>> 26) + i10;
        int i19 = 67108863 & i18;
        int i20 = ((i18 >>> 26) + i12) - 67108864;
        int i21 = (i20 >>> 31) - 1;
        int i22 = i21 ^ (-1);
        int i23 = (i14 & i22) | (i15 & 67108863 & i21);
        this.f7356h0 = i23;
        int i24 = (i13 & i22) | (i16 & 67108863 & i21);
        this.h1 = i24;
        int i25 = (i8 & i22) | (i17 & 67108863 & i21);
        this.h2 = i25;
        int i26 = (i19 & i21) | (i10 & i22);
        this.h3 = i26;
        int i27 = (i12 & i22) | (i20 & i21);
        this.h4 = i27;
        long j2 = (((long) (i23 | (i24 << 26))) & BodyPartID.bodyIdMax) + (((long) this.f7357k0) & BodyPartID.bodyIdMax);
        long j3 = (((long) ((i24 >>> 6) | (i25 << 20))) & BodyPartID.bodyIdMax) + (((long) this.k1) & BodyPartID.bodyIdMax);
        long j4 = (((long) ((i25 >>> 12) | (i26 << 14))) & BodyPartID.bodyIdMax) + (((long) this.k2) & BodyPartID.bodyIdMax);
        long j5 = (((long) ((i26 >>> 18) | (i27 << 8))) & BodyPartID.bodyIdMax) + (BodyPartID.bodyIdMax & ((long) this.k3));
        Pack.intToLittleEndian((int) j2, bArr, i2);
        long j6 = j3 + (j2 >>> 32);
        Pack.intToLittleEndian((int) j6, bArr, i2 + 4);
        long j7 = j4 + (j6 >>> 32);
        Pack.intToLittleEndian((int) j7, bArr, i2 + 8);
        Pack.intToLittleEndian((int) (j5 + (j7 >>> 32)), bArr, i2 + 12);
        reset();
        return 16;
    }

    @Override // org.spongycastle.crypto.Mac
    public String getAlgorithmName() {
        if (this.cipher == null) {
            return "Poly1305";
        }
        return "Poly1305-" + this.cipher.getAlgorithmName();
    }

    @Override // org.spongycastle.crypto.Mac
    public int getMacSize() {
        return 16;
    }

    @Override // org.spongycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) {
        byte[] iv;
        if (this.cipher == null) {
            iv = null;
        } else {
            if (!(cipherParameters instanceof ParametersWithIV)) {
                throw new IllegalArgumentException("Poly1305 requires an IV when used with a block cipher.");
            }
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            iv = parametersWithIV.getIV();
            cipherParameters = parametersWithIV.getParameters();
        }
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("Poly1305 requires a key.");
        }
        setKey(((KeyParameter) cipherParameters).getKey(), iv);
        reset();
    }

    @Override // org.spongycastle.crypto.Mac
    public void reset() {
        this.currentBlockOffset = 0;
        this.h4 = 0;
        this.h3 = 0;
        this.h2 = 0;
        this.h1 = 0;
        this.f7356h0 = 0;
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte b2) {
        byte[] bArr = this.singleByte;
        bArr[0] = b2;
        update(bArr, 0, 1);
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte[] bArr, int i2, int i3) {
        int i4 = 0;
        while (i3 > i4) {
            if (this.currentBlockOffset == 16) {
                processBlock();
                this.currentBlockOffset = 0;
            }
            int iMin = Math.min(i3 - i4, 16 - this.currentBlockOffset);
            System.arraycopy(bArr, i4 + i2, this.currentBlock, this.currentBlockOffset, iMin);
            i4 += iMin;
            this.currentBlockOffset += iMin;
        }
    }

    public Poly1305(BlockCipher blockCipher) {
        this.singleByte = new byte[1];
        this.currentBlock = new byte[16];
        this.currentBlockOffset = 0;
        if (blockCipher.getBlockSize() == 16) {
            this.cipher = blockCipher;
            return;
        }
        throw new IllegalArgumentException("Poly1305 requires a 128 bit block cipher.");
    }
}

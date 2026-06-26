package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public class SipHash implements Mac {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final int f7358c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected final int f7359d;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    protected long f7360k0;
    protected long k1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    protected long f7361m;
    protected long v0;
    protected long v1;
    protected long v2;
    protected long v3;
    protected int wordCount;
    protected int wordPos;

    public SipHash() {
        this.f7361m = 0L;
        this.wordPos = 0;
        this.wordCount = 0;
        this.f7358c = 2;
        this.f7359d = 4;
    }

    public static long rotateLeft(long j2, int i2) {
        return (j2 >>> (-i2)) | (j2 << i2);
    }

    public void applySipRounds(int i2) {
        long jRotateLeft = this.v0;
        long jRotateLeft2 = this.v1;
        long jRotateLeft3 = this.v2;
        long jRotateLeft4 = this.v3;
        for (int i3 = 0; i3 < i2; i3++) {
            long j2 = jRotateLeft + jRotateLeft2;
            long j3 = jRotateLeft3 + jRotateLeft4;
            long jRotateLeft5 = rotateLeft(jRotateLeft2, 13) ^ j2;
            long jRotateLeft6 = rotateLeft(jRotateLeft4, 16) ^ j3;
            long j4 = j3 + jRotateLeft5;
            jRotateLeft = rotateLeft(j2, 32) + jRotateLeft6;
            jRotateLeft2 = rotateLeft(jRotateLeft5, 17) ^ j4;
            jRotateLeft4 = rotateLeft(jRotateLeft6, 21) ^ jRotateLeft;
            jRotateLeft3 = rotateLeft(j4, 32);
        }
        this.v0 = jRotateLeft;
        this.v1 = jRotateLeft2;
        this.v2 = jRotateLeft3;
        this.v3 = jRotateLeft4;
    }

    public long doFinal() {
        long j2 = this.f7361m;
        int i2 = this.wordPos;
        this.f7361m = ((j2 >>> ((7 - i2) << 3)) >>> 8) | ((((long) ((this.wordCount << 3) + i2)) & 255) << 56);
        processMessageWord();
        this.v2 ^= 255;
        applySipRounds(this.f7359d);
        long j3 = ((this.v0 ^ this.v1) ^ this.v2) ^ this.v3;
        reset();
        return j3;
    }

    @Override // org.spongycastle.crypto.Mac
    public String getAlgorithmName() {
        return "SipHash-" + this.f7358c + "-" + this.f7359d;
    }

    @Override // org.spongycastle.crypto.Mac
    public int getMacSize() {
        return 8;
    }

    @Override // org.spongycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("'params' must be an instance of KeyParameter");
        }
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        if (key.length != 16) {
            throw new IllegalArgumentException("'params' must be a 128-bit key");
        }
        this.f7360k0 = Pack.littleEndianToLong(key, 0);
        this.k1 = Pack.littleEndianToLong(key, 8);
        reset();
    }

    public void processMessageWord() {
        this.wordCount++;
        this.v3 ^= this.f7361m;
        applySipRounds(this.f7358c);
        this.v0 ^= this.f7361m;
    }

    @Override // org.spongycastle.crypto.Mac
    public void reset() {
        long j2 = this.f7360k0;
        this.v0 = 8317987319222330741L ^ j2;
        long j3 = this.k1;
        this.v1 = 7237128888997146477L ^ j3;
        this.v2 = j2 ^ 7816392313619706465L;
        this.v3 = 8387220255154660723L ^ j3;
        this.f7361m = 0L;
        this.wordPos = 0;
        this.wordCount = 0;
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte b2) {
        this.f7361m = (this.f7361m >>> 8) | ((((long) b2) & 255) << 56);
        int i2 = this.wordPos + 1;
        this.wordPos = i2;
        if (i2 == 8) {
            processMessageWord();
            this.wordPos = 0;
        }
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte[] bArr, int i2, int i3) {
        int i4 = i3 & (-8);
        int i5 = this.wordPos;
        int i6 = 0;
        if (i5 == 0) {
            while (i6 < i4) {
                this.f7361m = Pack.littleEndianToLong(bArr, i2 + i6);
                processMessageWord();
                i6 += 8;
            }
            while (i6 < i3) {
                long j2 = this.f7361m >>> 8;
                this.f7361m = j2;
                this.f7361m = j2 | ((((long) bArr[i2 + i6]) & 255) << 56);
                i6++;
            }
            this.wordPos = i3 - i4;
            return;
        }
        int i7 = i5 << 3;
        int i8 = 0;
        while (i8 < i4) {
            long jLittleEndianToLong = Pack.littleEndianToLong(bArr, i2 + i8);
            this.f7361m = (this.f7361m >>> (-i7)) | (jLittleEndianToLong << i7);
            processMessageWord();
            this.f7361m = jLittleEndianToLong;
            i8 += 8;
        }
        while (i8 < i3) {
            long j3 = this.f7361m >>> 8;
            this.f7361m = j3;
            this.f7361m = j3 | ((((long) bArr[i2 + i8]) & 255) << 56);
            int i9 = this.wordPos + 1;
            this.wordPos = i9;
            if (i9 == 8) {
                processMessageWord();
                this.wordPos = 0;
            }
            i8++;
        }
    }

    public SipHash(int i2, int i3) {
        this.f7361m = 0L;
        this.wordPos = 0;
        this.wordCount = 0;
        this.f7358c = i2;
        this.f7359d = i3;
    }

    @Override // org.spongycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i2) {
        Pack.longToLittleEndian(doFinal(), bArr, i2);
        return 8;
    }
}

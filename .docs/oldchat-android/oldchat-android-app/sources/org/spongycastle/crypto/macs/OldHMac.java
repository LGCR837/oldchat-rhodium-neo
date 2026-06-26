package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.params.KeyParameter;

/* JADX INFO: loaded from: classes.dex */
public class OldHMac implements Mac {
    private static final int BLOCK_LENGTH = 64;
    private static final byte IPAD = 54;
    private static final byte OPAD = 92;
    private Digest digest;
    private int digestSize;
    private byte[] inputPad = new byte[64];
    private byte[] outputPad = new byte[64];

    public OldHMac(Digest digest) {
        this.digest = digest;
        this.digestSize = digest.getDigestSize();
    }

    @Override // org.spongycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i2) {
        int i3 = this.digestSize;
        byte[] bArr2 = new byte[i3];
        this.digest.doFinal(bArr2, 0);
        Digest digest = this.digest;
        byte[] bArr3 = this.outputPad;
        digest.update(bArr3, 0, bArr3.length);
        this.digest.update(bArr2, 0, i3);
        int iDoFinal = this.digest.doFinal(bArr, i2);
        reset();
        return iDoFinal;
    }

    @Override // org.spongycastle.crypto.Mac
    public String getAlgorithmName() {
        return this.digest.getAlgorithmName() + "/HMAC";
    }

    @Override // org.spongycastle.crypto.Mac
    public int getMacSize() {
        return this.digestSize;
    }

    public Digest getUnderlyingDigest() {
        return this.digest;
    }

    @Override // org.spongycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) {
        this.digest.reset();
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        if (key.length <= 64) {
            System.arraycopy(key, 0, this.inputPad, 0, key.length);
            int length = key.length;
            while (true) {
                byte[] bArr = this.inputPad;
                if (length >= bArr.length) {
                    break;
                }
                bArr[length] = 0;
                length++;
            }
        } else {
            this.digest.update(key, 0, key.length);
            this.digest.doFinal(this.inputPad, 0);
            int i2 = this.digestSize;
            while (true) {
                byte[] bArr2 = this.inputPad;
                if (i2 >= bArr2.length) {
                    break;
                }
                bArr2[i2] = 0;
                i2++;
            }
        }
        byte[] bArr3 = this.inputPad;
        byte[] bArr4 = new byte[bArr3.length];
        this.outputPad = bArr4;
        System.arraycopy(bArr3, 0, bArr4, 0, bArr3.length);
        int i3 = 0;
        while (true) {
            byte[] bArr5 = this.inputPad;
            if (i3 >= bArr5.length) {
                break;
            }
            bArr5[i3] = (byte) (bArr5[i3] ^ IPAD);
            i3++;
        }
        int i4 = 0;
        while (true) {
            byte[] bArr6 = this.outputPad;
            if (i4 >= bArr6.length) {
                Digest digest = this.digest;
                byte[] bArr7 = this.inputPad;
                digest.update(bArr7, 0, bArr7.length);
                return;
            }
            bArr6[i4] = (byte) (bArr6[i4] ^ OPAD);
            i4++;
        }
    }

    @Override // org.spongycastle.crypto.Mac
    public void reset() {
        this.digest.reset();
        Digest digest = this.digest;
        byte[] bArr = this.inputPad;
        digest.update(bArr, 0, bArr.length);
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte b2) {
        this.digest.update(b2);
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte[] bArr, int i2, int i3) {
        this.digest.update(bArr, i2, i3);
    }
}

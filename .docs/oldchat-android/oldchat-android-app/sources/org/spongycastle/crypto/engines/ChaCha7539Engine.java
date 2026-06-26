package org.spongycastle.crypto.engines;

import org.spongycastle.asn1.cmc.BodyPartID;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public class ChaCha7539Engine extends Salsa20Engine {
    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public void advanceCounter(long j2) {
        int i2 = (int) j2;
        if (((int) (j2 >>> 32)) > 0) {
            throw new IllegalStateException("attempt to increase counter past 2^32.");
        }
        int[] iArr = this.engineState;
        int i3 = iArr[12];
        int i4 = i2 + i3;
        iArr[12] = i4;
        if (i3 != 0 && i4 < i3) {
            throw new IllegalStateException("attempt to increase counter past 2^32.");
        }
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public void generateKeyStream(byte[] bArr) {
        ChaChaEngine.chachaCore(this.rounds, this.engineState, this.f7333x);
        Pack.intToLittleEndian(this.f7333x, bArr, 0);
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine, org.spongycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "ChaCha7539-" + this.rounds;
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public long getCounter() {
        return ((long) this.engineState[12]) & BodyPartID.bodyIdMax;
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public int getNonceSize() {
        return 12;
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public void resetCounter() {
        this.engineState[12] = 0;
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public void retreatCounter(long j2) {
        int i2 = (int) j2;
        if (((int) (j2 >>> 32)) != 0) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
        int[] iArr = this.engineState;
        int i3 = iArr[12];
        if ((((long) i3) & BodyPartID.bodyIdMax) < (BodyPartID.bodyIdMax & ((long) i2))) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
        iArr[12] = i3 - i2;
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public void setKey(byte[] bArr, byte[] bArr2) {
        if (bArr != null) {
            if (bArr.length != 32) {
                throw new IllegalArgumentException(getAlgorithmName() + " requires 256 bit key");
            }
            packTauOrSigma(bArr.length, this.engineState, 0);
            Pack.littleEndianToInt(bArr, 0, this.engineState, 4, 8);
        }
        Pack.littleEndianToInt(bArr2, 0, this.engineState, 13, 3);
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public void advanceCounter() {
        int[] iArr = this.engineState;
        int i2 = iArr[12] + 1;
        iArr[12] = i2;
        if (i2 == 0) {
            throw new IllegalStateException("attempt to increase counter past 2^32.");
        }
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    public void retreatCounter() {
        int[] iArr = this.engineState;
        int i2 = iArr[12];
        if (i2 != 0) {
            iArr[12] = i2 - 1;
            return;
        }
        throw new IllegalStateException("attempt to reduce counter past zero.");
    }
}

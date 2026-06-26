package org.spongycastle.crypto.engines;

import java.util.ArrayList;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.Wrapper;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class DSTU7624WrapEngine implements Wrapper {
    private static final int BYTES_IN_INTEGER = 4;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    private byte[] f7319B;
    private ArrayList<byte[]> Btemp;
    private byte[] checkSumArray;
    private DSTU7624Engine engine;
    private boolean forWrapping;
    private byte[] intArray;
    private byte[] zeroArray;

    public DSTU7624WrapEngine(int i2) {
        DSTU7624Engine dSTU7624Engine = new DSTU7624Engine(i2);
        this.engine = dSTU7624Engine;
        this.f7319B = new byte[dSTU7624Engine.getBlockSize() / 2];
        this.checkSumArray = new byte[this.engine.getBlockSize()];
        this.zeroArray = new byte[this.engine.getBlockSize()];
        this.Btemp = new ArrayList<>();
        this.intArray = new byte[4];
    }

    private void intToBytes(int i2, byte[] bArr, int i3) {
        bArr[i3 + 3] = (byte) (i2 >> 24);
        bArr[i3 + 2] = (byte) (i2 >> 16);
        bArr[i3 + 1] = (byte) (i2 >> 8);
        bArr[i3] = (byte) i2;
    }

    @Override // org.spongycastle.crypto.Wrapper
    public String getAlgorithmName() {
        return "DSTU7624WrapEngine";
    }

    @Override // org.spongycastle.crypto.Wrapper
    public void init(boolean z2, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters = ((ParametersWithRandom) cipherParameters).getParameters();
        }
        this.forWrapping = z2;
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameters passed to DSTU7624WrapEngine");
        }
        this.engine.init(z2, cipherParameters);
    }

    @Override // org.spongycastle.crypto.Wrapper
    public byte[] unwrap(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        if (this.forWrapping) {
            throw new IllegalStateException("not set for unwrapping");
        }
        if (i3 % this.engine.getBlockSize() != 0) {
            throw new DataLengthException("unwrap data must be a multiple of " + this.engine.getBlockSize() + " bytes");
        }
        int blockSize = (i3 * 2) / this.engine.getBlockSize();
        int i4 = blockSize - 1;
        int i5 = i4 * 6;
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        byte[] bArr3 = new byte[this.engine.getBlockSize() / 2];
        System.arraycopy(bArr2, 0, bArr3, 0, this.engine.getBlockSize() / 2);
        this.Btemp.clear();
        int blockSize2 = i3 - (this.engine.getBlockSize() / 2);
        int blockSize3 = this.engine.getBlockSize() / 2;
        while (blockSize2 != 0) {
            byte[] bArr4 = new byte[this.engine.getBlockSize() / 2];
            System.arraycopy(bArr2, blockSize3, bArr4, 0, this.engine.getBlockSize() / 2);
            this.Btemp.add(bArr4);
            blockSize2 -= this.engine.getBlockSize() / 2;
            blockSize3 += this.engine.getBlockSize() / 2;
        }
        for (int i6 = 0; i6 < i5; i6++) {
            System.arraycopy(this.Btemp.get(blockSize - 2), 0, bArr2, 0, this.engine.getBlockSize() / 2);
            System.arraycopy(bArr3, 0, bArr2, this.engine.getBlockSize() / 2, this.engine.getBlockSize() / 2);
            intToBytes(i5 - i6, this.intArray, 0);
            for (int i7 = 0; i7 < 4; i7++) {
                int blockSize4 = (this.engine.getBlockSize() / 2) + i7;
                bArr2[blockSize4] = (byte) (bArr2[blockSize4] ^ this.intArray[i7]);
            }
            this.engine.processBlock(bArr2, 0, bArr2, 0);
            System.arraycopy(bArr2, 0, bArr3, 0, this.engine.getBlockSize() / 2);
            for (int i8 = 2; i8 < blockSize; i8++) {
                int i9 = blockSize - i8;
                System.arraycopy(this.Btemp.get(i9 - 1), 0, this.Btemp.get(i9), 0, this.engine.getBlockSize() / 2);
            }
            System.arraycopy(bArr2, this.engine.getBlockSize() / 2, this.Btemp.get(0), 0, this.engine.getBlockSize() / 2);
        }
        System.arraycopy(bArr3, 0, bArr2, 0, this.engine.getBlockSize() / 2);
        int blockSize5 = this.engine.getBlockSize() / 2;
        for (int i10 = 0; i10 < i4; i10++) {
            System.arraycopy(this.Btemp.get(i10), 0, bArr2, blockSize5, this.engine.getBlockSize() / 2);
            blockSize5 += this.engine.getBlockSize() / 2;
        }
        System.arraycopy(bArr2, i3 - this.engine.getBlockSize(), this.checkSumArray, 0, this.engine.getBlockSize());
        byte[] bArr5 = new byte[i3 - this.engine.getBlockSize()];
        if (!Arrays.areEqual(this.checkSumArray, this.zeroArray)) {
            throw new InvalidCipherTextException("checksum failed");
        }
        System.arraycopy(bArr2, 0, bArr5, 0, i3 - this.engine.getBlockSize());
        return bArr5;
    }

    @Override // org.spongycastle.crypto.Wrapper
    public byte[] wrap(byte[] bArr, int i2, int i3) {
        if (!this.forWrapping) {
            throw new IllegalStateException("not set for wrapping");
        }
        if (i3 % this.engine.getBlockSize() != 0) {
            throw new DataLengthException("wrap data must be a multiple of " + this.engine.getBlockSize() + " bytes");
        }
        if (i2 + i3 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        int blockSize = ((i3 / this.engine.getBlockSize()) + 1) * 2;
        int i4 = blockSize - 1;
        int i5 = i4 * 6;
        int blockSize2 = this.engine.getBlockSize() + i3;
        byte[] bArr2 = new byte[blockSize2];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        System.arraycopy(bArr2, 0, this.f7319B, 0, this.engine.getBlockSize() / 2);
        this.Btemp.clear();
        int blockSize3 = blockSize2 - (this.engine.getBlockSize() / 2);
        int blockSize4 = this.engine.getBlockSize() / 2;
        while (blockSize3 != 0) {
            byte[] bArr3 = new byte[this.engine.getBlockSize() / 2];
            System.arraycopy(bArr2, blockSize4, bArr3, 0, this.engine.getBlockSize() / 2);
            this.Btemp.add(bArr3);
            blockSize3 -= this.engine.getBlockSize() / 2;
            blockSize4 += this.engine.getBlockSize() / 2;
        }
        int i6 = 0;
        while (i6 < i5) {
            System.arraycopy(this.f7319B, 0, bArr2, 0, this.engine.getBlockSize() / 2);
            System.arraycopy(this.Btemp.get(0), 0, bArr2, this.engine.getBlockSize() / 2, this.engine.getBlockSize() / 2);
            this.engine.processBlock(bArr2, 0, bArr2, 0);
            i6++;
            intToBytes(i6, this.intArray, 0);
            for (int i7 = 0; i7 < 4; i7++) {
                int blockSize5 = (this.engine.getBlockSize() / 2) + i7;
                bArr2[blockSize5] = (byte) (bArr2[blockSize5] ^ this.intArray[i7]);
            }
            System.arraycopy(bArr2, this.engine.getBlockSize() / 2, this.f7319B, 0, this.engine.getBlockSize() / 2);
            for (int i8 = 2; i8 < blockSize; i8++) {
                System.arraycopy(this.Btemp.get(i8 - 1), 0, this.Btemp.get(i8 - 2), 0, this.engine.getBlockSize() / 2);
            }
            System.arraycopy(bArr2, 0, this.Btemp.get(blockSize - 2), 0, this.engine.getBlockSize() / 2);
        }
        System.arraycopy(this.f7319B, 0, bArr2, 0, this.engine.getBlockSize() / 2);
        int blockSize6 = this.engine.getBlockSize() / 2;
        for (int i9 = 0; i9 < i4; i9++) {
            System.arraycopy(this.Btemp.get(i9), 0, bArr2, blockSize6, this.engine.getBlockSize() / 2);
            blockSize6 += this.engine.getBlockSize() / 2;
        }
        return bArr2;
    }
}

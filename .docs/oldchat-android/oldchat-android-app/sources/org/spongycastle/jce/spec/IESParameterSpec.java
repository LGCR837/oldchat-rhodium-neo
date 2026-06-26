package org.spongycastle.jce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class IESParameterSpec implements AlgorithmParameterSpec {
    private int cipherKeySize;
    private byte[] derivation;
    private byte[] encoding;
    private int macKeySize;
    private byte[] nonce;
    private boolean usePointCompression;

    public IESParameterSpec(byte[] bArr, byte[] bArr2, int i2) {
        this(bArr, bArr2, i2, -1, null, false);
    }

    public int getCipherKeySize() {
        return this.cipherKeySize;
    }

    public byte[] getDerivationV() {
        return Arrays.clone(this.derivation);
    }

    public byte[] getEncodingV() {
        return Arrays.clone(this.encoding);
    }

    public int getMacKeySize() {
        return this.macKeySize;
    }

    public byte[] getNonce() {
        return Arrays.clone(this.nonce);
    }

    public boolean getPointCompression() {
        return this.usePointCompression;
    }

    public void setPointCompression(boolean z2) {
        this.usePointCompression = z2;
    }

    public IESParameterSpec(byte[] bArr, byte[] bArr2, int i2, int i3, byte[] bArr3) {
        this(bArr, bArr2, i2, i3, bArr3, false);
    }

    public IESParameterSpec(byte[] bArr, byte[] bArr2, int i2, int i3, byte[] bArr3, boolean z2) {
        if (bArr != null) {
            byte[] bArr4 = new byte[bArr.length];
            this.derivation = bArr4;
            System.arraycopy(bArr, 0, bArr4, 0, bArr.length);
        } else {
            this.derivation = null;
        }
        if (bArr2 != null) {
            byte[] bArr5 = new byte[bArr2.length];
            this.encoding = bArr5;
            System.arraycopy(bArr2, 0, bArr5, 0, bArr2.length);
        } else {
            this.encoding = null;
        }
        this.macKeySize = i2;
        this.cipherKeySize = i3;
        this.nonce = Arrays.clone(bArr3);
        this.usePointCompression = z2;
    }
}

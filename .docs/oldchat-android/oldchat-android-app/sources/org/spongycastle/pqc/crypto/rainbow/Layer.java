package org.spongycastle.pqc.crypto.rainbow;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.spongycastle.pqc.crypto.rainbow.util.GF2Field;
import org.spongycastle.pqc.crypto.rainbow.util.RainbowUtil;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class Layer {
    private short[][][] coeff_alpha;
    private short[][][] coeff_beta;
    private short[] coeff_eta;
    private short[][] coeff_gamma;
    private int oi;
    private int vi;
    private int viNext;

    public Layer(byte b2, byte b3, short[][][] sArr, short[][][] sArr2, short[][] sArr3, short[] sArr4) {
        int i2 = b2 & 255;
        this.vi = i2;
        int i3 = b3 & 255;
        this.viNext = i3;
        this.oi = i3 - i2;
        this.coeff_alpha = sArr;
        this.coeff_beta = sArr2;
        this.coeff_gamma = sArr3;
        this.coeff_eta = sArr4;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Layer)) {
            return false;
        }
        Layer layer = (Layer) obj;
        return this.vi == layer.getVi() && this.viNext == layer.getViNext() && this.oi == layer.getOi() && RainbowUtil.equals(this.coeff_alpha, layer.getCoeffAlpha()) && RainbowUtil.equals(this.coeff_beta, layer.getCoeffBeta()) && RainbowUtil.equals(this.coeff_gamma, layer.getCoeffGamma()) && RainbowUtil.equals(this.coeff_eta, layer.getCoeffEta());
    }

    public short[][][] getCoeffAlpha() {
        return this.coeff_alpha;
    }

    public short[][][] getCoeffBeta() {
        return this.coeff_beta;
    }

    public short[] getCoeffEta() {
        return this.coeff_eta;
    }

    public short[][] getCoeffGamma() {
        return this.coeff_gamma;
    }

    public int getOi() {
        return this.oi;
    }

    public int getVi() {
        return this.vi;
    }

    public int getViNext() {
        return this.viNext;
    }

    public int hashCode() {
        return (((((((((((this.vi * 37) + this.viNext) * 37) + this.oi) * 37) + Arrays.hashCode(this.coeff_alpha)) * 37) + Arrays.hashCode(this.coeff_beta)) * 37) + Arrays.hashCode(this.coeff_gamma)) * 37) + Arrays.hashCode(this.coeff_eta);
    }

    public short[][] plugInVinegars(short[] sArr) {
        int i2 = this.oi;
        int i3 = 0;
        short[][] sArr2 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, i2, i2 + 1);
        short[] sArr3 = new short[this.oi];
        for (int i4 = 0; i4 < this.oi; i4++) {
            for (int i5 = 0; i5 < this.vi; i5++) {
                for (int i6 = 0; i6 < this.vi; i6++) {
                    sArr3[i4] = GF2Field.addElem(sArr3[i4], GF2Field.multElem(GF2Field.multElem(this.coeff_beta[i4][i5][i6], sArr[i5]), sArr[i6]));
                }
            }
        }
        for (int i7 = 0; i7 < this.oi; i7++) {
            for (int i8 = 0; i8 < this.oi; i8++) {
                for (int i9 = 0; i9 < this.vi; i9++) {
                    short sMultElem = GF2Field.multElem(this.coeff_alpha[i7][i8][i9], sArr[i9]);
                    short[] sArr4 = sArr2[i7];
                    sArr4[i8] = GF2Field.addElem(sArr4[i8], sMultElem);
                }
            }
        }
        for (int i10 = 0; i10 < this.oi; i10++) {
            for (int i11 = 0; i11 < this.vi; i11++) {
                sArr3[i10] = GF2Field.addElem(sArr3[i10], GF2Field.multElem(this.coeff_gamma[i10][i11], sArr[i11]));
            }
        }
        for (int i12 = 0; i12 < this.oi; i12++) {
            for (int i13 = this.vi; i13 < this.viNext; i13++) {
                short[] sArr5 = sArr2[i12];
                int i14 = this.vi;
                sArr5[i13 - i14] = GF2Field.addElem(this.coeff_gamma[i12][i13], sArr5[i13 - i14]);
            }
        }
        for (int i15 = 0; i15 < this.oi; i15++) {
            sArr3[i15] = GF2Field.addElem(sArr3[i15], this.coeff_eta[i15]);
        }
        while (true) {
            int i16 = this.oi;
            if (i3 >= i16) {
                return sArr2;
            }
            sArr2[i3][i16] = sArr3[i3];
            i3++;
        }
    }

    public Layer(int i2, int i3, SecureRandom secureRandom) {
        this.vi = i2;
        this.viNext = i3;
        int i4 = i3 - i2;
        this.oi = i4;
        int[] iArr = {i4, i4, i2};
        Class cls = Short.TYPE;
        this.coeff_alpha = (short[][][]) Array.newInstance((Class<?>) cls, iArr);
        int i5 = this.oi;
        int i6 = this.vi;
        this.coeff_beta = (short[][][]) Array.newInstance((Class<?>) cls, i5, i6, i6);
        this.coeff_gamma = (short[][]) Array.newInstance((Class<?>) cls, this.oi, this.viNext);
        int i7 = this.oi;
        this.coeff_eta = new short[i7];
        for (int i8 = 0; i8 < i7; i8++) {
            for (int i9 = 0; i9 < this.oi; i9++) {
                for (int i10 = 0; i10 < this.vi; i10++) {
                    this.coeff_alpha[i8][i9][i10] = (short) (secureRandom.nextInt() & 255);
                }
            }
        }
        for (int i11 = 0; i11 < i7; i11++) {
            for (int i12 = 0; i12 < this.vi; i12++) {
                for (int i13 = 0; i13 < this.vi; i13++) {
                    this.coeff_beta[i11][i12][i13] = (short) (secureRandom.nextInt() & 255);
                }
            }
        }
        for (int i14 = 0; i14 < i7; i14++) {
            for (int i15 = 0; i15 < this.viNext; i15++) {
                this.coeff_gamma[i14][i15] = (short) (secureRandom.nextInt() & 255);
            }
        }
        for (int i16 = 0; i16 < i7; i16++) {
            this.coeff_eta[i16] = (short) (secureRandom.nextInt() & 255);
        }
    }
}

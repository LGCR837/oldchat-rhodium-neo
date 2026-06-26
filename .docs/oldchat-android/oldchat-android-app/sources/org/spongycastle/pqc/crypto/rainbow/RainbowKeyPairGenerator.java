package org.spongycastle.pqc.crypto.rainbow;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.crypto.rainbow.util.ComputeInField;
import org.spongycastle.pqc.crypto.rainbow.util.GF2Field;

/* JADX INFO: loaded from: classes.dex */
public class RainbowKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private short[][] A1;
    private short[][] A1inv;
    private short[][] A2;
    private short[][] A2inv;
    private short[] b1;
    private short[] b2;
    private boolean initialized = false;
    private Layer[] layers;
    private int numOfLayers;
    private short[][] pub_quadratic;
    private short[] pub_scalar;
    private short[][] pub_singular;
    private RainbowKeyGenerationParameters rainbowParams;
    private SecureRandom sr;
    private int[] vi;

    private void compactPublicKey(short[][][] sArr) {
        int length = sArr.length;
        int length2 = sArr[0].length;
        this.pub_quadratic = (short[][]) Array.newInstance((Class<?>) Short.TYPE, length, ((length2 + 1) * length2) / 2);
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = 0;
            for (int i4 = 0; i4 < length2; i4++) {
                for (int i5 = i4; i5 < length2; i5++) {
                    if (i5 == i4) {
                        this.pub_quadratic[i2][i3] = sArr[i2][i4][i5];
                    } else {
                        short[] sArr2 = this.pub_quadratic[i2];
                        short[][] sArr3 = sArr[i2];
                        sArr2[i3] = GF2Field.addElem(sArr3[i4][i5], sArr3[i5][i4]);
                    }
                    i3++;
                }
            }
        }
    }

    private void computePublicKey() {
        ComputeInField computeInField = new ComputeInField();
        int[] iArr = this.vi;
        int i2 = 0;
        int i3 = iArr[iArr.length - 1] - iArr[0];
        int i4 = iArr[iArr.length - 1];
        Class cls = Short.TYPE;
        short[][][] sArr = (short[][][]) Array.newInstance((Class<?>) cls, i3, i4, i4);
        this.pub_singular = (short[][]) Array.newInstance((Class<?>) cls, i3, i4);
        this.pub_scalar = new short[i3];
        short[] sArr2 = new short[i4];
        int i5 = 0;
        int i6 = 0;
        while (true) {
            Layer[] layerArr = this.layers;
            if (i5 >= layerArr.length) {
                break;
            }
            short[][][] coeffAlpha = layerArr[i5].getCoeffAlpha();
            short[][][] coeffBeta = this.layers[i5].getCoeffBeta();
            short[][] coeffGamma = this.layers[i5].getCoeffGamma();
            short[] coeffEta = this.layers[i5].getCoeffEta();
            int length = coeffAlpha[i2].length;
            int length2 = coeffBeta[i2].length;
            while (i2 < length) {
                for (int i7 = 0; i7 < length; i7++) {
                    int i8 = 0;
                    while (i8 < length2) {
                        int i9 = i3;
                        int i10 = i4;
                        int i11 = i7 + length2;
                        short[] sArrMultVect = computeInField.multVect(coeffAlpha[i2][i7][i8], this.A2[i11]);
                        int i12 = i6 + i2;
                        int i13 = i5;
                        sArr[i12] = computeInField.addSquareMatrix(sArr[i12], computeInField.multVects(sArrMultVect, this.A2[i8]));
                        short[] sArrMultVect2 = computeInField.multVect(this.b2[i8], sArrMultVect);
                        short[][] sArr3 = this.pub_singular;
                        sArr3[i12] = computeInField.addVect(sArrMultVect2, sArr3[i12]);
                        short[] sArrMultVect3 = computeInField.multVect(this.b2[i11], computeInField.multVect(coeffAlpha[i2][i7][i8], this.A2[i8]));
                        short[][] sArr4 = this.pub_singular;
                        sArr4[i12] = computeInField.addVect(sArrMultVect3, sArr4[i12]);
                        short sMultElem = GF2Field.multElem(coeffAlpha[i2][i7][i8], this.b2[i11]);
                        short[] sArr5 = this.pub_scalar;
                        sArr5[i12] = GF2Field.addElem(sArr5[i12], GF2Field.multElem(sMultElem, this.b2[i8]));
                        i8++;
                        i4 = i10;
                        i3 = i9;
                        coeffAlpha = coeffAlpha;
                        i5 = i13;
                        coeffEta = coeffEta;
                    }
                }
                int i14 = i4;
                int i15 = i3;
                int i16 = i5;
                short[][][] sArr6 = coeffAlpha;
                short[] sArr7 = coeffEta;
                for (int i17 = 0; i17 < length2; i17++) {
                    for (int i18 = 0; i18 < length2; i18++) {
                        short[] sArrMultVect4 = computeInField.multVect(coeffBeta[i2][i17][i18], this.A2[i17]);
                        int i19 = i6 + i2;
                        sArr[i19] = computeInField.addSquareMatrix(sArr[i19], computeInField.multVects(sArrMultVect4, this.A2[i18]));
                        short[] sArrMultVect5 = computeInField.multVect(this.b2[i18], sArrMultVect4);
                        short[][] sArr8 = this.pub_singular;
                        sArr8[i19] = computeInField.addVect(sArrMultVect5, sArr8[i19]);
                        short[] sArrMultVect6 = computeInField.multVect(this.b2[i17], computeInField.multVect(coeffBeta[i2][i17][i18], this.A2[i18]));
                        short[][] sArr9 = this.pub_singular;
                        sArr9[i19] = computeInField.addVect(sArrMultVect6, sArr9[i19]);
                        short sMultElem2 = GF2Field.multElem(coeffBeta[i2][i17][i18], this.b2[i17]);
                        short[] sArr10 = this.pub_scalar;
                        sArr10[i19] = GF2Field.addElem(sArr10[i19], GF2Field.multElem(sMultElem2, this.b2[i18]));
                    }
                }
                for (int i20 = 0; i20 < length2 + length; i20++) {
                    short[] sArrMultVect7 = computeInField.multVect(coeffGamma[i2][i20], this.A2[i20]);
                    short[][] sArr11 = this.pub_singular;
                    int i21 = i6 + i2;
                    sArr11[i21] = computeInField.addVect(sArrMultVect7, sArr11[i21]);
                    short[] sArr12 = this.pub_scalar;
                    sArr12[i21] = GF2Field.addElem(sArr12[i21], GF2Field.multElem(coeffGamma[i2][i20], this.b2[i20]));
                }
                short[] sArr13 = this.pub_scalar;
                int i22 = i6 + i2;
                sArr13[i22] = GF2Field.addElem(sArr13[i22], sArr7[i2]);
                i2++;
                i4 = i14;
                i3 = i15;
                coeffAlpha = sArr6;
                i5 = i16;
                coeffEta = sArr7;
            }
            i6 += length;
            i5++;
            i2 = 0;
        }
        int i23 = i4;
        int i24 = i3;
        Class cls2 = Short.TYPE;
        short[][][] sArr14 = (short[][][]) Array.newInstance((Class<?>) cls2, i24, i23, i23);
        short[][] sArr15 = (short[][]) Array.newInstance((Class<?>) cls2, i24, i23);
        short[] sArr16 = new short[i24];
        for (int i25 = 0; i25 < i24; i25++) {
            int i26 = 0;
            while (true) {
                short[][] sArr17 = this.A1;
                if (i26 < sArr17.length) {
                    sArr14[i25] = computeInField.addSquareMatrix(sArr14[i25], computeInField.multMatrix(sArr17[i25][i26], sArr[i26]));
                    sArr15[i25] = computeInField.addVect(sArr15[i25], computeInField.multVect(this.A1[i25][i26], this.pub_singular[i26]));
                    sArr16[i25] = GF2Field.addElem(sArr16[i25], GF2Field.multElem(this.A1[i25][i26], this.pub_scalar[i26]));
                    i26++;
                }
            }
            sArr16[i25] = GF2Field.addElem(sArr16[i25], this.b1[i25]);
        }
        this.pub_singular = sArr15;
        this.pub_scalar = sArr16;
        compactPublicKey(sArr14);
    }

    private void generateF() {
        this.layers = new Layer[this.numOfLayers];
        int i2 = 0;
        while (i2 < this.numOfLayers) {
            Layer[] layerArr = this.layers;
            int[] iArr = this.vi;
            int i3 = i2 + 1;
            layerArr[i2] = new Layer(iArr[i2], iArr[i3], this.sr);
            i2 = i3;
        }
    }

    private void generateL1() {
        int[] iArr = this.vi;
        int i2 = iArr[iArr.length - 1] - iArr[0];
        this.A1 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, i2, i2);
        this.A1inv = null;
        ComputeInField computeInField = new ComputeInField();
        while (this.A1inv == null) {
            for (int i3 = 0; i3 < i2; i3++) {
                for (int i4 = 0; i4 < i2; i4++) {
                    this.A1[i3][i4] = (short) (this.sr.nextInt() & 255);
                }
            }
            this.A1inv = computeInField.inverse(this.A1);
        }
        this.b1 = new short[i2];
        for (int i5 = 0; i5 < i2; i5++) {
            this.b1[i5] = (short) (this.sr.nextInt() & 255);
        }
    }

    private void generateL2() {
        int[] iArr = this.vi;
        int i2 = iArr[iArr.length - 1];
        this.A2 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, i2, i2);
        this.A2inv = null;
        ComputeInField computeInField = new ComputeInField();
        while (this.A2inv == null) {
            for (int i3 = 0; i3 < i2; i3++) {
                for (int i4 = 0; i4 < i2; i4++) {
                    this.A2[i3][i4] = (short) (this.sr.nextInt() & 255);
                }
            }
            this.A2inv = computeInField.inverse(this.A2);
        }
        this.b2 = new short[i2];
        for (int i5 = 0; i5 < i2; i5++) {
            this.b2[i5] = (short) (this.sr.nextInt() & 255);
        }
    }

    private void initializeDefault() {
        initialize(new RainbowKeyGenerationParameters(new SecureRandom(), new RainbowParameters()));
    }

    private void keygen() {
        generateL1();
        generateL2();
        generateF();
        computePublicKey();
    }

    public AsymmetricCipherKeyPair genKeyPair() {
        if (!this.initialized) {
            initializeDefault();
        }
        keygen();
        RainbowPrivateKeyParameters rainbowPrivateKeyParameters = new RainbowPrivateKeyParameters(this.A1inv, this.b1, this.A2inv, this.b2, this.vi, this.layers);
        int[] iArr = this.vi;
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new RainbowPublicKeyParameters(iArr[iArr.length - 1] - iArr[0], this.pub_quadratic, this.pub_singular, this.pub_scalar), (AsymmetricKeyParameter) rainbowPrivateKeyParameters);
    }

    @Override // org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        return genKeyPair();
    }

    @Override // org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        initialize(keyGenerationParameters);
    }

    public void initialize(KeyGenerationParameters keyGenerationParameters) {
        RainbowKeyGenerationParameters rainbowKeyGenerationParameters = (RainbowKeyGenerationParameters) keyGenerationParameters;
        this.rainbowParams = rainbowKeyGenerationParameters;
        this.sr = rainbowKeyGenerationParameters.getRandom();
        this.vi = this.rainbowParams.getParameters().getVi();
        this.numOfLayers = this.rainbowParams.getParameters().getNumOfLayers();
        this.initialized = true;
    }
}

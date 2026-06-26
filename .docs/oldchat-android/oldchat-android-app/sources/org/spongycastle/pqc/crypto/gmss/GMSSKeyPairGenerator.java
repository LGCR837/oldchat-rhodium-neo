package org.spongycastle.pqc.crypto.gmss;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.Vector;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.spongycastle.pqc.crypto.gmss.util.WinternitzOTSVerify;
import org.spongycastle.pqc.crypto.gmss.util.WinternitzOTSignature;

/* JADX INFO: loaded from: classes.dex */
public class GMSSKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.3";

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    private int[] f7602K;
    private byte[][] currentRootSigs;
    private byte[][] currentSeeds;
    private GMSSDigestProvider digestProvider;
    private GMSSParameters gmssPS;
    private GMSSKeyGenerationParameters gmssParams;
    private GMSSRandom gmssRandom;
    private int[] heightOfTrees;
    private boolean initialized = false;
    private int mdLength;
    private Digest messDigestTree;
    private byte[][] nextNextSeeds;
    private int numLayer;
    private int[] otsIndex;

    public GMSSKeyPairGenerator(GMSSDigestProvider gMSSDigestProvider) {
        this.digestProvider = gMSSDigestProvider;
        Digest digest = gMSSDigestProvider.get();
        this.messDigestTree = digest;
        this.mdLength = digest.getDigestSize();
        this.gmssRandom = new GMSSRandom(this.messDigestTree);
    }

    private AsymmetricCipherKeyPair genKeyPair() {
        int i2;
        int i3;
        if (!this.initialized) {
            initializeDefault();
        }
        int i4 = this.numLayer;
        byte[][][] bArr = new byte[i4][][];
        byte[][][] bArr2 = new byte[i4 - 1][][];
        Treehash[][] treehashArr = new Treehash[i4][];
        Treehash[][] treehashArr2 = new Treehash[i4 - 1][];
        Vector[] vectorArr = new Vector[i4];
        Vector[] vectorArr2 = new Vector[i4 - 1];
        Vector[][] vectorArr3 = new Vector[i4][];
        int i5 = 1;
        Vector[][] vectorArr4 = new Vector[i4 - 1][];
        char c2 = 0;
        int i6 = 0;
        while (true) {
            i2 = this.numLayer;
            if (i6 >= i2) {
                break;
            }
            int i7 = this.heightOfTrees[i6];
            int[] iArr = new int[2];
            iArr[1] = this.mdLength;
            iArr[c2] = i7;
            Class cls = Byte.TYPE;
            bArr[i6] = (byte[][]) Array.newInstance((Class<?>) cls, iArr);
            int i8 = this.heightOfTrees[i6];
            treehashArr[i6] = new Treehash[i8 - this.f7602K[i6]];
            if (i6 > 0) {
                int i9 = i6 - 1;
                bArr2[i9] = (byte[][]) Array.newInstance((Class<?>) cls, i8, this.mdLength);
                treehashArr2[i9] = new Treehash[this.heightOfTrees[i6] - this.f7602K[i6]];
            }
            vectorArr[i6] = new Vector();
            if (i6 > 0) {
                vectorArr2[i6 - 1] = new Vector();
            }
            i6++;
            c2 = 0;
        }
        int[] iArr2 = {i2, this.mdLength};
        Class cls2 = Byte.TYPE;
        byte[][] bArr3 = (byte[][]) Array.newInstance((Class<?>) cls2, iArr2);
        byte[][] bArr4 = (byte[][]) Array.newInstance((Class<?>) cls2, this.numLayer - 1, this.mdLength);
        byte[][] bArr5 = (byte[][]) Array.newInstance((Class<?>) cls2, this.numLayer, this.mdLength);
        int i10 = 0;
        while (true) {
            i3 = this.numLayer;
            if (i10 >= i3) {
                break;
            }
            System.arraycopy(this.currentSeeds[i10], 0, bArr5[i10], 0, this.mdLength);
            i10++;
        }
        this.currentRootSigs = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, i3 - 1, this.mdLength);
        int i11 = this.numLayer - 1;
        while (i11 >= 0) {
            GMSSRootCalc gMSSRootCalc = new GMSSRootCalc(this.heightOfTrees[i11], this.f7602K[i11], this.digestProvider);
            try {
                gMSSRootCalc = i11 == this.numLayer - i5 ? generateCurrentAuthpathAndRoot(null, vectorArr[i11], bArr5[i11], i11) : generateCurrentAuthpathAndRoot(bArr3[i11 + 1], vectorArr[i11], bArr5[i11], i11);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            int i12 = 0;
            while (i12 < this.heightOfTrees[i11]) {
                System.arraycopy(gMSSRootCalc.getAuthPath()[i12], 0, bArr[i11][i12], 0, this.mdLength);
                i12++;
                vectorArr = vectorArr;
            }
            vectorArr3[i11] = gMSSRootCalc.getRetain();
            treehashArr[i11] = gMSSRootCalc.getTreehash();
            System.arraycopy(gMSSRootCalc.getRoot(), 0, bArr3[i11], 0, this.mdLength);
            i11--;
            vectorArr = vectorArr;
            i5 = 1;
        }
        Vector[] vectorArr5 = vectorArr;
        int i13 = this.numLayer - 2;
        while (i13 >= 0) {
            int i14 = i13 + 1;
            GMSSRootCalc gMSSRootCalcGenerateNextAuthpathAndRoot = generateNextAuthpathAndRoot(vectorArr2[i13], bArr5[i14], i14);
            int i15 = 0;
            while (i15 < this.heightOfTrees[i14]) {
                System.arraycopy(gMSSRootCalcGenerateNextAuthpathAndRoot.getAuthPath()[i15], 0, bArr2[i13][i15], 0, this.mdLength);
                i15++;
                vectorArr3 = vectorArr3;
            }
            vectorArr4[i13] = gMSSRootCalcGenerateNextAuthpathAndRoot.getRetain();
            treehashArr2[i13] = gMSSRootCalcGenerateNextAuthpathAndRoot.getTreehash();
            System.arraycopy(gMSSRootCalcGenerateNextAuthpathAndRoot.getRoot(), 0, bArr4[i13], 0, this.mdLength);
            System.arraycopy(bArr5[i14], 0, this.nextNextSeeds[i13], 0, this.mdLength);
            i13--;
            vectorArr3 = vectorArr3;
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new GMSSPublicKeyParameters(bArr3[0], this.gmssPS), (AsymmetricKeyParameter) new GMSSPrivateKeyParameters(this.currentSeeds, this.nextNextSeeds, bArr, bArr2, treehashArr, treehashArr2, vectorArr5, vectorArr2, vectorArr3, vectorArr4, bArr4, this.currentRootSigs, this.gmssPS, this.digestProvider));
    }

    private GMSSRootCalc generateCurrentAuthpathAndRoot(byte[] bArr, Vector vector, byte[] bArr2, int i2) {
        byte[] bArrVerify;
        int i3 = this.mdLength;
        byte[] bArr3 = new byte[i3];
        byte[] bArr4 = new byte[i3];
        byte[] bArrNextSeed = this.gmssRandom.nextSeed(bArr2);
        GMSSRootCalc gMSSRootCalc = new GMSSRootCalc(this.heightOfTrees[i2], this.f7602K[i2], this.digestProvider);
        gMSSRootCalc.initialize(vector);
        if (i2 == this.numLayer - 1) {
            bArrVerify = new WinternitzOTSignature(bArrNextSeed, this.digestProvider.get(), this.otsIndex[i2]).getPublicKey();
        } else {
            this.currentRootSigs[i2] = new WinternitzOTSignature(bArrNextSeed, this.digestProvider.get(), this.otsIndex[i2]).getSignature(bArr);
            bArrVerify = new WinternitzOTSVerify(this.digestProvider.get(), this.otsIndex[i2]).Verify(bArr, this.currentRootSigs[i2]);
        }
        gMSSRootCalc.update(bArrVerify);
        int i4 = 3;
        int i5 = 0;
        int i6 = 1;
        while (true) {
            int i7 = this.heightOfTrees[i2];
            if (i6 >= (1 << i7)) {
                break;
            }
            if (i6 == i4 && i5 < i7 - this.f7602K[i2]) {
                gMSSRootCalc.initializeTreehashSeed(bArr2, i5);
                i4 *= 2;
                i5++;
            }
            gMSSRootCalc.update(new WinternitzOTSignature(this.gmssRandom.nextSeed(bArr2), this.digestProvider.get(), this.otsIndex[i2]).getPublicKey());
            i6++;
        }
        if (gMSSRootCalc.wasFinished()) {
            return gMSSRootCalc;
        }
        System.err.println("Baum noch nicht fertig konstruiert!!!");
        return null;
    }

    private GMSSRootCalc generateNextAuthpathAndRoot(Vector vector, byte[] bArr, int i2) {
        byte[] bArr2 = new byte[this.numLayer];
        GMSSRootCalc gMSSRootCalc = new GMSSRootCalc(this.heightOfTrees[i2], this.f7602K[i2], this.digestProvider);
        gMSSRootCalc.initialize(vector);
        int i3 = 3;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            int i6 = this.heightOfTrees[i2];
            if (i4 >= (1 << i6)) {
                break;
            }
            if (i4 == i3 && i5 < i6 - this.f7602K[i2]) {
                gMSSRootCalc.initializeTreehashSeed(bArr, i5);
                i3 *= 2;
                i5++;
            }
            gMSSRootCalc.update(new WinternitzOTSignature(this.gmssRandom.nextSeed(bArr), this.digestProvider.get(), this.otsIndex[i2]).getPublicKey());
            i4++;
        }
        if (gMSSRootCalc.wasFinished()) {
            return gMSSRootCalc;
        }
        System.err.println("N�chster Baum noch nicht fertig konstruiert!!!");
        return null;
    }

    private void initializeDefault() {
        initialize(new GMSSKeyGenerationParameters(new SecureRandom(), new GMSSParameters(4, new int[]{10, 10, 10, 10}, new int[]{3, 3, 3, 3}, new int[]{2, 2, 2, 2})));
    }

    @Override // org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        return genKeyPair();
    }

    @Override // org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        initialize(keyGenerationParameters);
    }

    public void initialize(int i2, SecureRandom secureRandom) {
        initialize(i2 <= 10 ? new GMSSKeyGenerationParameters(secureRandom, new GMSSParameters(1, new int[]{10}, new int[]{3}, new int[]{2})) : i2 <= 20 ? new GMSSKeyGenerationParameters(secureRandom, new GMSSParameters(2, new int[]{10, 10}, new int[]{5, 4}, new int[]{2, 2})) : new GMSSKeyGenerationParameters(secureRandom, new GMSSParameters(4, new int[]{10, 10, 10, 10}, new int[]{9, 9, 9, 3}, new int[]{2, 2, 2, 2})));
    }

    public void initialize(KeyGenerationParameters keyGenerationParameters) {
        GMSSKeyGenerationParameters gMSSKeyGenerationParameters = (GMSSKeyGenerationParameters) keyGenerationParameters;
        this.gmssParams = gMSSKeyGenerationParameters;
        GMSSParameters gMSSParameters = new GMSSParameters(gMSSKeyGenerationParameters.getParameters().getNumOfLayers(), this.gmssParams.getParameters().getHeightOfTrees(), this.gmssParams.getParameters().getWinternitzParameter(), this.gmssParams.getParameters().getK());
        this.gmssPS = gMSSParameters;
        this.numLayer = gMSSParameters.getNumOfLayers();
        this.heightOfTrees = this.gmssPS.getHeightOfTrees();
        this.otsIndex = this.gmssPS.getWinternitzParameter();
        this.f7602K = this.gmssPS.getK();
        int[] iArr = {this.numLayer, this.mdLength};
        Class cls = Byte.TYPE;
        this.currentSeeds = (byte[][]) Array.newInstance((Class<?>) cls, iArr);
        this.nextNextSeeds = (byte[][]) Array.newInstance((Class<?>) cls, this.numLayer - 1, this.mdLength);
        SecureRandom secureRandom = new SecureRandom();
        for (int i2 = 0; i2 < this.numLayer; i2++) {
            secureRandom.nextBytes(this.currentSeeds[i2]);
            this.gmssRandom.nextSeed(this.currentSeeds[i2]);
        }
        this.initialized = true;
    }
}

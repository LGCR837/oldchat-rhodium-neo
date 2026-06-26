package org.spongycastle.pqc.crypto.gmss;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.pqc.crypto.MessageSigner;
import org.spongycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.spongycastle.pqc.crypto.gmss.util.GMSSUtil;
import org.spongycastle.pqc.crypto.gmss.util.WinternitzOTSVerify;
import org.spongycastle.pqc.crypto.gmss.util.WinternitzOTSignature;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class GMSSSigner implements MessageSigner {
    private byte[][][] currentAuthPaths;
    private GMSSDigestProvider digestProvider;
    private GMSSParameters gmssPS;
    private GMSSRandom gmssRandom;
    private GMSSUtil gmssUtil = new GMSSUtil();
    private int[] index;
    GMSSKeyParameters key;
    private int mdLength;
    private Digest messDigestOTS;
    private Digest messDigestTrees;
    private int numLayer;
    private WinternitzOTSignature ots;
    private byte[] pubKeyBytes;
    private SecureRandom random;
    private byte[][] subtreeRootSig;

    public GMSSSigner(GMSSDigestProvider gMSSDigestProvider) {
        this.digestProvider = gMSSDigestProvider;
        Digest digest = gMSSDigestProvider.get();
        this.messDigestTrees = digest;
        this.messDigestOTS = digest;
        this.mdLength = digest.getDigestSize();
        this.gmssRandom = new GMSSRandom(this.messDigestTrees);
    }

    private void initSign() {
        int i2;
        this.messDigestTrees.reset();
        GMSSPrivateKeyParameters gMSSPrivateKeyParameters = (GMSSPrivateKeyParameters) this.key;
        if (gMSSPrivateKeyParameters.isUsed()) {
            throw new IllegalStateException("Private key already used");
        }
        if (gMSSPrivateKeyParameters.getIndex(0) >= gMSSPrivateKeyParameters.getNumLeafs(0)) {
            throw new IllegalStateException("No more signatures can be generated");
        }
        GMSSParameters parameters = gMSSPrivateKeyParameters.getParameters();
        this.gmssPS = parameters;
        this.numLayer = parameters.getNumOfLayers();
        byte[] bArr = gMSSPrivateKeyParameters.getCurrentSeeds()[this.numLayer - 1];
        int i3 = this.mdLength;
        byte[] bArr2 = new byte[i3];
        byte[] bArr3 = new byte[i3];
        System.arraycopy(bArr, 0, bArr3, 0, i3);
        this.ots = new WinternitzOTSignature(this.gmssRandom.nextSeed(bArr3), this.digestProvider.get(), this.gmssPS.getWinternitzParameter()[this.numLayer - 1]);
        byte[][][] currentAuthPaths = gMSSPrivateKeyParameters.getCurrentAuthPaths();
        this.currentAuthPaths = new byte[this.numLayer][][];
        int i4 = 0;
        while (true) {
            i2 = this.numLayer;
            if (i4 >= i2) {
                break;
            }
            this.currentAuthPaths[i4] = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, currentAuthPaths[i4].length, this.mdLength);
            int i5 = 0;
            while (true) {
                byte[][] bArr4 = currentAuthPaths[i4];
                if (i5 < bArr4.length) {
                    System.arraycopy(bArr4[i5], 0, this.currentAuthPaths[i4][i5], 0, this.mdLength);
                    i5++;
                }
            }
            i4++;
        }
        this.index = new int[i2];
        System.arraycopy(gMSSPrivateKeyParameters.getIndex(), 0, this.index, 0, this.numLayer);
        this.subtreeRootSig = new byte[this.numLayer - 1][];
        for (int i6 = 0; i6 < this.numLayer - 1; i6++) {
            byte[] subtreeRootSig = gMSSPrivateKeyParameters.getSubtreeRootSig(i6);
            byte[][] bArr5 = this.subtreeRootSig;
            byte[] bArr6 = new byte[subtreeRootSig.length];
            bArr5[i6] = bArr6;
            System.arraycopy(subtreeRootSig, 0, bArr6, 0, subtreeRootSig.length);
        }
        gMSSPrivateKeyParameters.markUsed();
    }

    private void initVerify() {
        this.messDigestTrees.reset();
        GMSSPublicKeyParameters gMSSPublicKeyParameters = (GMSSPublicKeyParameters) this.key;
        this.pubKeyBytes = gMSSPublicKeyParameters.getPublicKey();
        GMSSParameters parameters = gMSSPublicKeyParameters.getParameters();
        this.gmssPS = parameters;
        this.numLayer = parameters.getNumOfLayers();
    }

    @Override // org.spongycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        byte[] bArr2 = new byte[this.mdLength];
        byte[] signature = this.ots.getSignature(bArr);
        byte[] bArrConcatenateArray = this.gmssUtil.concatenateArray(this.currentAuthPaths[this.numLayer - 1]);
        byte[] bArrIntToBytesLittleEndian = this.gmssUtil.intToBytesLittleEndian(this.index[this.numLayer - 1]);
        int length = bArrIntToBytesLittleEndian.length + signature.length + bArrConcatenateArray.length;
        byte[] bArr3 = new byte[length];
        System.arraycopy(bArrIntToBytesLittleEndian, 0, bArr3, 0, bArrIntToBytesLittleEndian.length);
        System.arraycopy(signature, 0, bArr3, bArrIntToBytesLittleEndian.length, signature.length);
        System.arraycopy(bArrConcatenateArray, 0, bArr3, bArrIntToBytesLittleEndian.length + signature.length, bArrConcatenateArray.length);
        byte[] bArr4 = new byte[0];
        for (int i2 = this.numLayer - 2; i2 >= 0; i2--) {
            byte[] bArrConcatenateArray2 = this.gmssUtil.concatenateArray(this.currentAuthPaths[i2]);
            byte[] bArrIntToBytesLittleEndian2 = this.gmssUtil.intToBytesLittleEndian(this.index[i2]);
            int length2 = bArr4.length;
            byte[] bArr5 = new byte[length2];
            System.arraycopy(bArr4, 0, bArr5, 0, bArr4.length);
            bArr4 = new byte[bArrIntToBytesLittleEndian2.length + length2 + this.subtreeRootSig[i2].length + bArrConcatenateArray2.length];
            System.arraycopy(bArr5, 0, bArr4, 0, length2);
            System.arraycopy(bArrIntToBytesLittleEndian2, 0, bArr4, length2, bArrIntToBytesLittleEndian2.length);
            byte[] bArr6 = this.subtreeRootSig[i2];
            System.arraycopy(bArr6, 0, bArr4, bArrIntToBytesLittleEndian2.length + length2, bArr6.length);
            System.arraycopy(bArrConcatenateArray2, 0, bArr4, length2 + bArrIntToBytesLittleEndian2.length + this.subtreeRootSig[i2].length, bArrConcatenateArray2.length);
        }
        byte[] bArr7 = new byte[bArr4.length + length];
        System.arraycopy(bArr3, 0, bArr7, 0, length);
        System.arraycopy(bArr4, 0, bArr7, length, bArr4.length);
        return bArr7;
    }

    @Override // org.spongycastle.pqc.crypto.MessageSigner
    public void init(boolean z2, CipherParameters cipherParameters) {
        if (!z2) {
            this.key = (GMSSPublicKeyParameters) cipherParameters;
            initVerify();
        } else if (!(cipherParameters instanceof ParametersWithRandom)) {
            this.random = new SecureRandom();
            this.key = (GMSSPrivateKeyParameters) cipherParameters;
            initSign();
        } else {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.random = parametersWithRandom.getRandom();
            this.key = (GMSSPrivateKeyParameters) parametersWithRandom.getParameters();
            initSign();
        }
    }

    @Override // org.spongycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        this.messDigestOTS.reset();
        int i2 = 0;
        for (int i3 = this.numLayer - 1; i3 >= 0; i3--) {
            WinternitzOTSVerify winternitzOTSVerify = new WinternitzOTSVerify(this.digestProvider.get(), this.gmssPS.getWinternitzParameter()[i3]);
            int signatureLength = winternitzOTSVerify.getSignatureLength();
            int iBytesToIntLittleEndian = this.gmssUtil.bytesToIntLittleEndian(bArr2, i2);
            int i4 = i2 + 4;
            byte[] bArr3 = new byte[signatureLength];
            System.arraycopy(bArr2, i4, bArr3, 0, signatureLength);
            i2 = i4 + signatureLength;
            bArr = winternitzOTSVerify.Verify(bArr, bArr3);
            if (bArr == null) {
                System.err.println("OTS Public Key is null in GMSSSignature.verify");
                return false;
            }
            byte[][] bArr4 = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, this.gmssPS.getHeightOfTrees()[i3], this.mdLength);
            for (byte[] bArr5 : bArr4) {
                System.arraycopy(bArr2, i2, bArr5, 0, this.mdLength);
                i2 += this.mdLength;
            }
            byte[] bArr6 = new byte[this.mdLength];
            int length = (1 << bArr4.length) + iBytesToIntLittleEndian;
            for (int i5 = 0; i5 < bArr4.length; i5++) {
                int i6 = this.mdLength;
                int i7 = i6 << 1;
                byte[] bArr7 = new byte[i7];
                if (length % 2 == 0) {
                    System.arraycopy(bArr, 0, bArr7, 0, i6);
                    byte[] bArr8 = bArr4[i5];
                    int i8 = this.mdLength;
                    System.arraycopy(bArr8, 0, bArr7, i8, i8);
                    length /= 2;
                } else {
                    System.arraycopy(bArr4[i5], 0, bArr7, 0, i6);
                    System.arraycopy(bArr, 0, bArr7, this.mdLength, bArr.length);
                    length = (length - 1) / 2;
                }
                this.messDigestTrees.update(bArr7, 0, i7);
                bArr = new byte[this.messDigestTrees.getDigestSize()];
                this.messDigestTrees.doFinal(bArr, 0);
            }
        }
        return Arrays.areEqual(this.pubKeyBytes, bArr);
    }
}

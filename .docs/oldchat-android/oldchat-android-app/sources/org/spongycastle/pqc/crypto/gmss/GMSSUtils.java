package org.spongycastle.pqc.crypto.gmss;

import java.util.Enumeration;
import java.util.Vector;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
class GMSSUtils {
    public static GMSSLeaf[] clone(GMSSLeaf[] gMSSLeafArr) {
        if (gMSSLeafArr == null) {
            return null;
        }
        GMSSLeaf[] gMSSLeafArr2 = new GMSSLeaf[gMSSLeafArr.length];
        System.arraycopy(gMSSLeafArr, 0, gMSSLeafArr2, 0, gMSSLeafArr.length);
        return gMSSLeafArr2;
    }

    public static GMSSRootCalc[] clone(GMSSRootCalc[] gMSSRootCalcArr) {
        if (gMSSRootCalcArr == null) {
            return null;
        }
        GMSSRootCalc[] gMSSRootCalcArr2 = new GMSSRootCalc[gMSSRootCalcArr.length];
        System.arraycopy(gMSSRootCalcArr, 0, gMSSRootCalcArr2, 0, gMSSRootCalcArr.length);
        return gMSSRootCalcArr2;
    }

    public static GMSSRootSig[] clone(GMSSRootSig[] gMSSRootSigArr) {
        if (gMSSRootSigArr == null) {
            return null;
        }
        GMSSRootSig[] gMSSRootSigArr2 = new GMSSRootSig[gMSSRootSigArr.length];
        System.arraycopy(gMSSRootSigArr, 0, gMSSRootSigArr2, 0, gMSSRootSigArr.length);
        return gMSSRootSigArr2;
    }

    public static byte[][] clone(byte[][] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[][] bArr2 = new byte[bArr.length][];
        for (int i2 = 0; i2 != bArr.length; i2++) {
            bArr2[i2] = Arrays.clone(bArr[i2]);
        }
        return bArr2;
    }

    public static byte[][][] clone(byte[][][] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[][][] bArr2 = new byte[bArr.length][][];
        for (int i2 = 0; i2 != bArr.length; i2++) {
            bArr2[i2] = clone(bArr[i2]);
        }
        return bArr2;
    }

    public static Treehash[] clone(Treehash[] treehashArr) {
        if (treehashArr == null) {
            return null;
        }
        Treehash[] treehashArr2 = new Treehash[treehashArr.length];
        System.arraycopy(treehashArr, 0, treehashArr2, 0, treehashArr.length);
        return treehashArr2;
    }

    public static Treehash[][] clone(Treehash[][] treehashArr) {
        if (treehashArr == null) {
            return null;
        }
        Treehash[][] treehashArr2 = new Treehash[treehashArr.length][];
        for (int i2 = 0; i2 != treehashArr.length; i2++) {
            treehashArr2[i2] = clone(treehashArr[i2]);
        }
        return treehashArr2;
    }

    public static Vector[] clone(Vector[] vectorArr) {
        if (vectorArr == null) {
            return null;
        }
        Vector[] vectorArr2 = new Vector[vectorArr.length];
        for (int i2 = 0; i2 != vectorArr.length; i2++) {
            vectorArr2[i2] = new Vector();
            Enumeration enumerationElements = vectorArr[i2].elements();
            while (enumerationElements.hasMoreElements()) {
                vectorArr2[i2].addElement(enumerationElements.nextElement());
            }
        }
        return vectorArr2;
    }

    public static Vector[][] clone(Vector[][] vectorArr) {
        if (vectorArr == null) {
            return null;
        }
        Vector[][] vectorArr2 = new Vector[vectorArr.length][];
        for (int i2 = 0; i2 != vectorArr.length; i2++) {
            vectorArr2[i2] = clone(vectorArr[i2]);
        }
        return vectorArr2;
    }
}

package org.spongycastle.pqc.crypto.gmss;

import java.lang.reflect.Array;
import java.util.Enumeration;
import java.util.Vector;
import org.spongycastle.crypto.Digest;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Integers;
import org.spongycastle.util.encoders.Hex;

/* JADX INFO: loaded from: classes.dex */
public class GMSSRootCalc {
    private byte[][] AuthPath;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    private int f7608K;
    private GMSSDigestProvider digestProvider;
    private int heightOfNextSeed;
    private Vector heightOfNodes;
    private int heightOfTree;
    private int[] index;
    private int indexForNextSeed;
    private boolean isFinished;
    private boolean isInitialized;
    private int mdLength;
    private Digest messDigestTree;
    private Vector[] retain;
    private byte[] root;
    private Vector tailStack;
    private Treehash[] treehash;

    public GMSSRootCalc(Digest digest, byte[][] bArr, int[] iArr, Treehash[] treehashArr, Vector[] vectorArr) {
        this.messDigestTree = this.digestProvider.get();
        this.digestProvider = this.digestProvider;
        int i2 = iArr[0];
        this.heightOfTree = i2;
        this.mdLength = iArr[1];
        this.f7608K = iArr[2];
        this.indexForNextSeed = iArr[3];
        this.heightOfNextSeed = iArr[4];
        if (iArr[5] == 1) {
            this.isFinished = true;
        } else {
            this.isFinished = false;
        }
        if (iArr[6] == 1) {
            this.isInitialized = true;
        } else {
            this.isInitialized = false;
        }
        int i3 = iArr[7];
        this.index = new int[i2];
        for (int i4 = 0; i4 < this.heightOfTree; i4++) {
            this.index[i4] = iArr[i4 + 8];
        }
        this.heightOfNodes = new Vector();
        for (int i5 = 0; i5 < i3; i5++) {
            this.heightOfNodes.addElement(Integers.valueOf(iArr[this.heightOfTree + 8 + i5]));
        }
        this.root = bArr[0];
        this.AuthPath = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, this.heightOfTree, this.mdLength);
        int i6 = 0;
        while (i6 < this.heightOfTree) {
            int i7 = i6 + 1;
            this.AuthPath[i6] = bArr[i7];
            i6 = i7;
        }
        this.tailStack = new Vector();
        for (int i8 = 0; i8 < i3; i8++) {
            this.tailStack.addElement(bArr[this.heightOfTree + 1 + i8]);
        }
        this.treehash = GMSSUtils.clone(treehashArr);
        this.retain = GMSSUtils.clone(vectorArr);
    }

    public byte[][] getAuthPath() {
        return GMSSUtils.clone(this.AuthPath);
    }

    public Vector[] getRetain() {
        return GMSSUtils.clone(this.retain);
    }

    public byte[] getRoot() {
        return Arrays.clone(this.root);
    }

    public Vector getStack() {
        Vector vector = new Vector();
        Enumeration enumerationElements = this.tailStack.elements();
        while (enumerationElements.hasMoreElements()) {
            vector.addElement(enumerationElements.nextElement());
        }
        return vector;
    }

    public byte[][] getStatByte() {
        Vector vector = this.tailStack;
        int size = vector == null ? 0 : vector.size();
        byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, this.heightOfTree + 1 + size, 64);
        bArr[0] = this.root;
        int i2 = 0;
        while (i2 < this.heightOfTree) {
            int i3 = i2 + 1;
            bArr[i3] = this.AuthPath[i2];
            i2 = i3;
        }
        for (int i4 = 0; i4 < size; i4++) {
            bArr[this.heightOfTree + 1 + i4] = (byte[]) this.tailStack.elementAt(i4);
        }
        return bArr;
    }

    public int[] getStatInt() {
        Vector vector = this.tailStack;
        int size = vector == null ? 0 : vector.size();
        int i2 = this.heightOfTree;
        int[] iArr = new int[i2 + 8 + size];
        iArr[0] = i2;
        iArr[1] = this.mdLength;
        iArr[2] = this.f7608K;
        iArr[3] = this.indexForNextSeed;
        iArr[4] = this.heightOfNextSeed;
        if (this.isFinished) {
            iArr[5] = 1;
        } else {
            iArr[5] = 0;
        }
        if (this.isInitialized) {
            iArr[6] = 1;
        } else {
            iArr[6] = 0;
        }
        iArr[7] = size;
        for (int i3 = 0; i3 < this.heightOfTree; i3++) {
            iArr[i3 + 8] = this.index[i3];
        }
        for (int i4 = 0; i4 < size; i4++) {
            iArr[this.heightOfTree + 8 + i4] = ((Integer) this.heightOfNodes.elementAt(i4)).intValue();
        }
        return iArr;
    }

    public Treehash[] getTreehash() {
        return GMSSUtils.clone(this.treehash);
    }

    public void initialize(Vector vector) {
        int i2;
        this.treehash = new Treehash[this.heightOfTree - this.f7608K];
        int i3 = 0;
        while (true) {
            i2 = this.heightOfTree;
            if (i3 >= i2 - this.f7608K) {
                break;
            }
            this.treehash[i3] = new Treehash(vector, i3, this.digestProvider.get());
            i3++;
        }
        this.index = new int[i2];
        this.AuthPath = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, i2, this.mdLength);
        this.root = new byte[this.mdLength];
        this.tailStack = new Vector();
        this.heightOfNodes = new Vector();
        this.isInitialized = true;
        this.isFinished = false;
        for (int i4 = 0; i4 < this.heightOfTree; i4++) {
            this.index[i4] = -1;
        }
        this.retain = new Vector[this.f7608K - 1];
        for (int i5 = 0; i5 < this.f7608K - 1; i5++) {
            this.retain[i5] = new Vector();
        }
        this.indexForNextSeed = 3;
        this.heightOfNextSeed = 0;
    }

    public void initializeTreehashSeed(byte[] bArr, int i2) {
        this.treehash[i2].initializeSeed(bArr);
    }

    public String toString() {
        Vector vector = this.tailStack;
        int size = vector == null ? 0 : vector.size();
        String str = "";
        for (int i2 = 0; i2 < this.heightOfTree + 8 + size; i2++) {
            str = str + getStatInt()[i2] + " ";
        }
        for (int i3 = 0; i3 < this.heightOfTree + 1 + size; i3++) {
            str = str + new String(Hex.encode(getStatByte()[i3])) + " ";
        }
        return str + "  " + this.digestProvider.get().getDigestSize();
    }

    public void update(byte[] bArr, byte[] bArr2) {
        int i2 = this.heightOfNextSeed;
        if (i2 < this.heightOfTree - this.f7608K && this.indexForNextSeed - 2 == this.index[0]) {
            initializeTreehashSeed(bArr, i2);
            this.heightOfNextSeed++;
            this.indexForNextSeed *= 2;
        }
        update(bArr2);
    }

    public boolean wasFinished() {
        return this.isFinished;
    }

    public boolean wasInitialized() {
        return this.isInitialized;
    }

    public void update(byte[] bArr) {
        if (this.isFinished) {
            System.out.print("Too much updates for Tree!!");
            return;
        }
        if (!this.isInitialized) {
            System.err.println("GMSSRootCalc not initialized!");
            return;
        }
        int[] iArr = this.index;
        int i2 = iArr[0] + 1;
        iArr[0] = i2;
        if (i2 == 1) {
            System.arraycopy(bArr, 0, this.AuthPath[0], 0, this.mdLength);
        } else if (i2 == 3 && this.heightOfTree > this.f7608K) {
            this.treehash[0].setFirstNode(bArr);
        }
        int i3 = this.index[0];
        if ((i3 - 3) % 2 == 0 && i3 >= 3 && this.heightOfTree == this.f7608K) {
            this.retain[0].insertElementAt(bArr, 0);
        }
        if (this.index[0] == 0) {
            this.tailStack.addElement(bArr);
            this.heightOfNodes.addElement(Integers.valueOf(0));
            return;
        }
        int i4 = this.mdLength;
        byte[] bArr2 = new byte[i4];
        int i5 = i4 << 1;
        byte[] bArr3 = new byte[i5];
        System.arraycopy(bArr, 0, bArr2, 0, i4);
        int i6 = 0;
        while (this.tailStack.size() > 0 && i6 == ((Integer) this.heightOfNodes.lastElement()).intValue()) {
            System.arraycopy(this.tailStack.lastElement(), 0, bArr3, 0, this.mdLength);
            Vector vector = this.tailStack;
            vector.removeElementAt(vector.size() - 1);
            Vector vector2 = this.heightOfNodes;
            vector2.removeElementAt(vector2.size() - 1);
            int i7 = this.mdLength;
            System.arraycopy(bArr2, 0, bArr3, i7, i7);
            this.messDigestTree.update(bArr3, 0, i5);
            bArr2 = new byte[this.messDigestTree.getDigestSize()];
            this.messDigestTree.doFinal(bArr2, 0);
            i6++;
            if (i6 < this.heightOfTree) {
                int[] iArr2 = this.index;
                int i8 = iArr2[i6] + 1;
                iArr2[i6] = i8;
                if (i8 == 1) {
                    System.arraycopy(bArr2, 0, this.AuthPath[i6], 0, this.mdLength);
                }
                if (i6 >= this.heightOfTree - this.f7608K) {
                    if (i6 == 0) {
                        System.out.println("M���P");
                    }
                    int i9 = this.index[i6];
                    if ((i9 - 3) % 2 == 0 && i9 >= 3) {
                        this.retain[i6 - (this.heightOfTree - this.f7608K)].insertElementAt(bArr2, 0);
                    }
                } else if (this.index[i6] == 3) {
                    this.treehash[i6].setFirstNode(bArr2);
                }
            }
        }
        this.tailStack.addElement(bArr2);
        this.heightOfNodes.addElement(Integers.valueOf(i6));
        if (i6 == this.heightOfTree) {
            this.isFinished = true;
            this.isInitialized = false;
            this.root = (byte[]) this.tailStack.lastElement();
        }
    }

    public GMSSRootCalc(int i2, int i3, GMSSDigestProvider gMSSDigestProvider) {
        this.heightOfTree = i2;
        this.digestProvider = gMSSDigestProvider;
        Digest digest = gMSSDigestProvider.get();
        this.messDigestTree = digest;
        int digestSize = digest.getDigestSize();
        this.mdLength = digestSize;
        this.f7608K = i3;
        this.index = new int[i2];
        int[] iArr = {i2, digestSize};
        this.AuthPath = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, iArr);
        this.root = new byte[this.mdLength];
        this.retain = new Vector[this.f7608K - 1];
        for (int i4 = 0; i4 < i3 - 1; i4++) {
            this.retain[i4] = new Vector();
        }
    }
}

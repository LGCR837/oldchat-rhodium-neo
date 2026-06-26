package org.spongycastle.pqc.crypto.xmss;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import org.spongycastle.pqc.crypto.xmss.HashTreeAddress;
import org.spongycastle.pqc.crypto.xmss.LTreeAddress;
import org.spongycastle.pqc.crypto.xmss.OTSHashAddress;

/* JADX INFO: loaded from: classes.dex */
public final class BDS implements Serializable {
    private static final long serialVersionUID = 1;
    private List<XMSSNode> authenticationPath;
    private int index;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f7677k;
    private Map<Integer, XMSSNode> keep;
    private Map<Integer, LinkedList<XMSSNode>> retain;
    private XMSSNode root;
    private Stack<XMSSNode> stack;
    private final List<BDSTreeHash> treeHashInstances;
    private final int treeHeight;
    private boolean used;
    private transient WOTSPlus wotsPlus;

    public BDS(XMSSParameters xMSSParameters, int i2) {
        this(xMSSParameters.getWOTSPlus(), xMSSParameters.getHeight(), xMSSParameters.getK());
        this.index = i2;
        this.used = true;
    }

    private BDSTreeHash getBDSTreeHashInstanceForUpdate() {
        BDSTreeHash bDSTreeHash = null;
        for (BDSTreeHash bDSTreeHash2 : this.treeHashInstances) {
            if (!bDSTreeHash2.isFinished() && bDSTreeHash2.isInitialized() && (bDSTreeHash == null || bDSTreeHash2.getHeight() < bDSTreeHash.getHeight() || (bDSTreeHash2.getHeight() == bDSTreeHash.getHeight() && bDSTreeHash2.getIndexLeaf() < bDSTreeHash.getIndexLeaf()))) {
                bDSTreeHash = bDSTreeHash2;
            }
        }
        return bDSTreeHash;
    }

    private void initialize(byte[] bArr, byte[] bArr2, OTSHashAddress oTSHashAddress) {
        if (oTSHashAddress == null) {
            throw new NullPointerException("otsHashAddress == null");
        }
        LTreeAddress lTreeAddress = (LTreeAddress) new LTreeAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).build();
        HashTreeAddress hashTreeAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).build();
        for (int i2 = 0; i2 < (1 << this.treeHeight); i2++) {
            oTSHashAddress = (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withOTSAddress(i2).withChainAddress(oTSHashAddress.getChainAddress()).withHashAddress(oTSHashAddress.getHashAddress()).withKeyAndMask(oTSHashAddress.getKeyAndMask()).build();
            WOTSPlus wOTSPlus = this.wotsPlus;
            wOTSPlus.importKeys(wOTSPlus.getWOTSPlusSecretKey(bArr2, oTSHashAddress), bArr);
            WOTSPlusPublicKeyParameters publicKey = this.wotsPlus.getPublicKey(oTSHashAddress);
            lTreeAddress = (LTreeAddress) new LTreeAddress.Builder().withLayerAddress(lTreeAddress.getLayerAddress()).withTreeAddress(lTreeAddress.getTreeAddress()).withLTreeAddress(i2).withTreeHeight(lTreeAddress.getTreeHeight()).withTreeIndex(lTreeAddress.getTreeIndex()).withKeyAndMask(lTreeAddress.getKeyAndMask()).build();
            XMSSNode xMSSNodeLTree = XMSSNodeUtil.lTree(this.wotsPlus, publicKey, lTreeAddress);
            hashTreeAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress()).withTreeAddress(hashTreeAddress.getTreeAddress()).withTreeIndex(i2).withKeyAndMask(hashTreeAddress.getKeyAndMask()).build();
            while (!this.stack.isEmpty() && this.stack.peek().getHeight() == xMSSNodeLTree.getHeight()) {
                int iFloor = (int) Math.floor(i2 / (1 << xMSSNodeLTree.getHeight()));
                if (iFloor == 1) {
                    this.authenticationPath.add(xMSSNodeLTree.clone());
                }
                if (iFloor == 3 && xMSSNodeLTree.getHeight() < this.treeHeight - this.f7677k) {
                    this.treeHashInstances.get(xMSSNodeLTree.getHeight()).setNode(xMSSNodeLTree.clone());
                }
                if (iFloor >= 3 && (iFloor & 1) == 1 && xMSSNodeLTree.getHeight() >= this.treeHeight - this.f7677k && xMSSNodeLTree.getHeight() <= this.treeHeight - 2) {
                    if (this.retain.get(Integer.valueOf(xMSSNodeLTree.getHeight())) == null) {
                        LinkedList<XMSSNode> linkedList = new LinkedList<>();
                        linkedList.add(xMSSNodeLTree.clone());
                        this.retain.put(Integer.valueOf(xMSSNodeLTree.getHeight()), linkedList);
                    } else {
                        this.retain.get(Integer.valueOf(xMSSNodeLTree.getHeight())).add(xMSSNodeLTree.clone());
                    }
                }
                HashTreeAddress hashTreeAddress2 = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress()).withTreeAddress(hashTreeAddress.getTreeAddress()).withTreeHeight(hashTreeAddress.getTreeHeight()).withTreeIndex((hashTreeAddress.getTreeIndex() - 1) / 2).withKeyAndMask(hashTreeAddress.getKeyAndMask()).build();
                XMSSNode xMSSNodeRandomizeHash = XMSSNodeUtil.randomizeHash(this.wotsPlus, this.stack.pop(), xMSSNodeLTree, hashTreeAddress2);
                XMSSNode xMSSNode = new XMSSNode(xMSSNodeRandomizeHash.getHeight() + 1, xMSSNodeRandomizeHash.getValue());
                hashTreeAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress2.getLayerAddress()).withTreeAddress(hashTreeAddress2.getTreeAddress()).withTreeHeight(hashTreeAddress2.getTreeHeight() + 1).withTreeIndex(hashTreeAddress2.getTreeIndex()).withKeyAndMask(hashTreeAddress2.getKeyAndMask()).build();
                xMSSNodeLTree = xMSSNode;
            }
            this.stack.push(xMSSNodeLTree);
        }
        this.root = this.stack.pop();
    }

    private void nextAuthenticationPath(byte[] bArr, byte[] bArr2, OTSHashAddress oTSHashAddress) {
        if (oTSHashAddress == null) {
            throw new NullPointerException("otsHashAddress == null");
        }
        if (this.used) {
            throw new IllegalStateException("index already used");
        }
        if (this.index > (1 << this.treeHeight) - 2) {
            throw new IllegalStateException("index out of bounds");
        }
        LTreeAddress lTreeAddress = (LTreeAddress) new LTreeAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).build();
        HashTreeAddress hashTreeAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).build();
        int iCalculateTau = XMSSUtil.calculateTau(this.index, this.treeHeight);
        if (((this.index >> (iCalculateTau + 1)) & 1) == 0 && iCalculateTau < this.treeHeight - 1) {
            this.keep.put(Integer.valueOf(iCalculateTau), this.authenticationPath.get(iCalculateTau).clone());
        }
        if (iCalculateTau == 0) {
            oTSHashAddress = (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withOTSAddress(this.index).withChainAddress(oTSHashAddress.getChainAddress()).withHashAddress(oTSHashAddress.getHashAddress()).withKeyAndMask(oTSHashAddress.getKeyAndMask()).build();
            WOTSPlus wOTSPlus = this.wotsPlus;
            wOTSPlus.importKeys(wOTSPlus.getWOTSPlusSecretKey(bArr2, oTSHashAddress), bArr);
            this.authenticationPath.set(0, XMSSNodeUtil.lTree(this.wotsPlus, this.wotsPlus.getPublicKey(oTSHashAddress), (LTreeAddress) new LTreeAddress.Builder().withLayerAddress(lTreeAddress.getLayerAddress()).withTreeAddress(lTreeAddress.getTreeAddress()).withLTreeAddress(this.index).withTreeHeight(lTreeAddress.getTreeHeight()).withTreeIndex(lTreeAddress.getTreeIndex()).withKeyAndMask(lTreeAddress.getKeyAndMask()).build()));
        } else {
            int i2 = iCalculateTau - 1;
            XMSSNode xMSSNodeRandomizeHash = XMSSNodeUtil.randomizeHash(this.wotsPlus, this.authenticationPath.get(i2), this.keep.get(Integer.valueOf(i2)), (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress()).withTreeAddress(hashTreeAddress.getTreeAddress()).withTreeHeight(i2).withTreeIndex(this.index >> iCalculateTau).withKeyAndMask(hashTreeAddress.getKeyAndMask()).build());
            this.authenticationPath.set(iCalculateTau, new XMSSNode(xMSSNodeRandomizeHash.getHeight() + 1, xMSSNodeRandomizeHash.getValue()));
            this.keep.remove(Integer.valueOf(i2));
            for (int i3 = 0; i3 < iCalculateTau; i3++) {
                if (i3 < this.treeHeight - this.f7677k) {
                    this.authenticationPath.set(i3, this.treeHashInstances.get(i3).getTailNode());
                } else {
                    this.authenticationPath.set(i3, this.retain.get(Integer.valueOf(i3)).removeFirst());
                }
            }
            int iMin = Math.min(iCalculateTau, this.treeHeight - this.f7677k);
            for (int i4 = 0; i4 < iMin; i4++) {
                int i5 = this.index + 1 + ((1 << i4) * 3);
                if (i5 < (1 << this.treeHeight)) {
                    this.treeHashInstances.get(i4).initialize(i5);
                }
            }
        }
        for (int i6 = 0; i6 < ((this.treeHeight - this.f7677k) >> 1); i6++) {
            BDSTreeHash bDSTreeHashInstanceForUpdate = getBDSTreeHashInstanceForUpdate();
            if (bDSTreeHashInstanceForUpdate != null) {
                bDSTreeHashInstanceForUpdate.update(this.stack, this.wotsPlus, bArr, bArr2, oTSHashAddress);
            }
        }
        this.index++;
    }

    public List<XMSSNode> getAuthenticationPath() {
        ArrayList arrayList = new ArrayList();
        Iterator<XMSSNode> it = this.authenticationPath.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().clone());
        }
        return arrayList;
    }

    public int getIndex() {
        return this.index;
    }

    public BDS getNextState(byte[] bArr, byte[] bArr2, OTSHashAddress oTSHashAddress) {
        return new BDS(this, bArr, bArr2, oTSHashAddress);
    }

    public XMSSNode getRoot() {
        return this.root.clone();
    }

    public int getTreeHeight() {
        return this.treeHeight;
    }

    public boolean isUsed() {
        return this.used;
    }

    public void setXMSS(XMSSParameters xMSSParameters) {
        if (this.treeHeight != xMSSParameters.getHeight()) {
            throw new IllegalStateException("wrong height");
        }
        this.wotsPlus = xMSSParameters.getWOTSPlus();
    }

    public void validate() {
        if (this.authenticationPath == null) {
            throw new IllegalStateException("authenticationPath == null");
        }
        if (this.retain == null) {
            throw new IllegalStateException("retain == null");
        }
        if (this.stack == null) {
            throw new IllegalStateException("stack == null");
        }
        if (this.treeHashInstances == null) {
            throw new IllegalStateException("treeHashInstances == null");
        }
        if (this.keep == null) {
            throw new IllegalStateException("keep == null");
        }
        if (!XMSSUtil.isIndexValid(this.treeHeight, this.index)) {
            throw new IllegalStateException("index in BDS state out of bounds");
        }
    }

    public BDS(XMSSParameters xMSSParameters, byte[] bArr, byte[] bArr2, OTSHashAddress oTSHashAddress) {
        this(xMSSParameters.getWOTSPlus(), xMSSParameters.getHeight(), xMSSParameters.getK());
        initialize(bArr, bArr2, oTSHashAddress);
    }

    public BDS(XMSSParameters xMSSParameters, byte[] bArr, byte[] bArr2, OTSHashAddress oTSHashAddress, int i2) {
        this(xMSSParameters.getWOTSPlus(), xMSSParameters.getHeight(), xMSSParameters.getK());
        initialize(bArr, bArr2, oTSHashAddress);
        while (this.index < i2) {
            nextAuthenticationPath(bArr, bArr2, oTSHashAddress);
            this.used = false;
        }
    }

    private BDS(WOTSPlus wOTSPlus, int i2, int i3) {
        this.wotsPlus = wOTSPlus;
        this.treeHeight = i2;
        this.f7677k = i3;
        if (i3 <= i2 && i3 >= 2) {
            int i4 = i2 - i3;
            if (i4 % 2 == 0) {
                this.authenticationPath = new ArrayList();
                this.retain = new TreeMap();
                this.stack = new Stack<>();
                this.treeHashInstances = new ArrayList();
                for (int i5 = 0; i5 < i4; i5++) {
                    this.treeHashInstances.add(new BDSTreeHash(i5));
                }
                this.keep = new TreeMap();
                this.index = 0;
                this.used = false;
                return;
            }
        }
        throw new IllegalArgumentException("illegal value for BDS parameter k");
    }

    private BDS(BDS bds, byte[] bArr, byte[] bArr2, OTSHashAddress oTSHashAddress) {
        this.wotsPlus = bds.wotsPlus;
        this.treeHeight = bds.treeHeight;
        this.f7677k = bds.f7677k;
        this.root = bds.root;
        this.authenticationPath = new ArrayList(bds.authenticationPath);
        this.retain = bds.retain;
        this.stack = (Stack) bds.stack.clone();
        this.treeHashInstances = bds.treeHashInstances;
        this.keep = new TreeMap(bds.keep);
        this.index = bds.index;
        nextAuthenticationPath(bArr, bArr2, oTSHashAddress);
        bds.used = true;
    }
}

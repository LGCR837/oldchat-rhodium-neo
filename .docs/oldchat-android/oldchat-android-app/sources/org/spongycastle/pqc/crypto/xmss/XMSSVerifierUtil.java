package org.spongycastle.pqc.crypto.xmss;

import org.spongycastle.pqc.crypto.xmss.HashTreeAddress;
import org.spongycastle.pqc.crypto.xmss.LTreeAddress;

/* JADX INFO: loaded from: classes.dex */
class XMSSVerifierUtil {
    public static XMSSNode getRootNodeFromSignature(WOTSPlus wOTSPlus, int i2, byte[] bArr, XMSSReducedSignature xMSSReducedSignature, OTSHashAddress oTSHashAddress, int i3) {
        if (bArr.length != wOTSPlus.getParams().getDigestSize()) {
            throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
        }
        if (xMSSReducedSignature == null) {
            throw new NullPointerException("signature == null");
        }
        if (oTSHashAddress == null) {
            throw new NullPointerException("otsHashAddress == null");
        }
        LTreeAddress lTreeAddress = (LTreeAddress) new LTreeAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withLTreeAddress(oTSHashAddress.getOTSAddress()).build();
        HashTreeAddress hashTreeAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withTreeIndex(oTSHashAddress.getOTSAddress()).build();
        XMSSNode[] xMSSNodeArr = new XMSSNode[2];
        xMSSNodeArr[0] = XMSSNodeUtil.lTree(wOTSPlus, wOTSPlus.getPublicKeyFromSignature(bArr, xMSSReducedSignature.getWOTSPlusSignature(), oTSHashAddress), lTreeAddress);
        for (int i4 = 0; i4 < i2; i4++) {
            HashTreeAddress hashTreeAddress2 = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress()).withTreeAddress(hashTreeAddress.getTreeAddress()).withTreeHeight(i4).withTreeIndex(hashTreeAddress.getTreeIndex()).withKeyAndMask(hashTreeAddress.getKeyAndMask()).build();
            if (Math.floor(i3 / (1 << i4)) % 2.0d == 0.0d) {
                hashTreeAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress2.getLayerAddress()).withTreeAddress(hashTreeAddress2.getTreeAddress()).withTreeHeight(hashTreeAddress2.getTreeHeight()).withTreeIndex(hashTreeAddress2.getTreeIndex() / 2).withKeyAndMask(hashTreeAddress2.getKeyAndMask()).build();
                XMSSNode xMSSNodeRandomizeHash = XMSSNodeUtil.randomizeHash(wOTSPlus, xMSSNodeArr[0], xMSSReducedSignature.getAuthPath().get(i4), hashTreeAddress);
                xMSSNodeArr[1] = xMSSNodeRandomizeHash;
                xMSSNodeArr[1] = new XMSSNode(xMSSNodeRandomizeHash.getHeight() + 1, xMSSNodeArr[1].getValue());
            } else {
                hashTreeAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress2.getLayerAddress()).withTreeAddress(hashTreeAddress2.getTreeAddress()).withTreeHeight(hashTreeAddress2.getTreeHeight()).withTreeIndex((hashTreeAddress2.getTreeIndex() - 1) / 2).withKeyAndMask(hashTreeAddress2.getKeyAndMask()).build();
                XMSSNode xMSSNodeRandomizeHash2 = XMSSNodeUtil.randomizeHash(wOTSPlus, xMSSReducedSignature.getAuthPath().get(i4), xMSSNodeArr[0], hashTreeAddress);
                xMSSNodeArr[1] = xMSSNodeRandomizeHash2;
                xMSSNodeArr[1] = new XMSSNode(xMSSNodeRandomizeHash2.getHeight() + 1, xMSSNodeArr[1].getValue());
            }
            xMSSNodeArr[0] = xMSSNodeArr[1];
        }
        return xMSSNodeArr[0];
    }
}

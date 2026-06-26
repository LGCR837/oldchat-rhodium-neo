package org.spongycastle.pqc.crypto.xmss;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import org.spongycastle.pqc.crypto.xmss.OTSHashAddress;
import org.spongycastle.util.Integers;

/* JADX INFO: loaded from: classes.dex */
public class BDSStateMap implements Serializable {
    private final Map<Integer, BDS> bdsState = new TreeMap();

    public BDSStateMap() {
    }

    private void updateState(XMSSMTParameters xMSSMTParameters, long j2, byte[] bArr, byte[] bArr2) {
        XMSSParameters xMSSParameters = xMSSMTParameters.getXMSSParameters();
        int height = xMSSParameters.getHeight();
        long treeIndex = XMSSUtil.getTreeIndex(j2, height);
        int leafIndex = XMSSUtil.getLeafIndex(j2, height);
        OTSHashAddress oTSHashAddress = (OTSHashAddress) new OTSHashAddress.Builder().withTreeAddress(treeIndex).withOTSAddress(leafIndex).build();
        int i2 = (1 << height) - 1;
        if (leafIndex < i2) {
            if (get(0) == null || leafIndex == 0) {
                put(0, new BDS(xMSSParameters, bArr, bArr2, oTSHashAddress));
            }
            update(0, bArr, bArr2, oTSHashAddress);
        }
        for (int i3 = 1; i3 < xMSSMTParameters.getLayers(); i3++) {
            int leafIndex2 = XMSSUtil.getLeafIndex(treeIndex, height);
            treeIndex = XMSSUtil.getTreeIndex(treeIndex, height);
            OTSHashAddress oTSHashAddress2 = (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(i3).withTreeAddress(treeIndex).withOTSAddress(leafIndex2).build();
            if (leafIndex2 < i2 && XMSSUtil.isNewAuthenticationPathNeeded(j2, height, i3)) {
                if (get(i3) == null) {
                    put(i3, new BDS(xMSSMTParameters.getXMSSParameters(), bArr, bArr2, oTSHashAddress2));
                }
                update(i3, bArr, bArr2, oTSHashAddress2);
            }
        }
    }

    public BDS get(int i2) {
        return this.bdsState.get(Integers.valueOf(i2));
    }

    public boolean isEmpty() {
        return this.bdsState.isEmpty();
    }

    public void put(int i2, BDS bds) {
        this.bdsState.put(Integers.valueOf(i2), bds);
    }

    public void setXMSS(XMSSParameters xMSSParameters) {
        Iterator<Integer> it = this.bdsState.keySet().iterator();
        while (it.hasNext()) {
            BDS bds = this.bdsState.get(it.next());
            bds.setXMSS(xMSSParameters);
            bds.validate();
        }
    }

    public BDS update(int i2, byte[] bArr, byte[] bArr2, OTSHashAddress oTSHashAddress) {
        return this.bdsState.put(Integers.valueOf(i2), this.bdsState.get(Integers.valueOf(i2)).getNextState(bArr, bArr2, oTSHashAddress));
    }

    public BDSStateMap(XMSSMTParameters xMSSMTParameters, long j2, byte[] bArr, byte[] bArr2) {
        for (long j3 = 0; j3 < j2; j3++) {
            updateState(xMSSMTParameters, j3, bArr, bArr2);
        }
    }

    public BDSStateMap(BDSStateMap bDSStateMap, XMSSMTParameters xMSSMTParameters, long j2, byte[] bArr, byte[] bArr2) {
        for (Integer num : bDSStateMap.bdsState.keySet()) {
            this.bdsState.put(num, bDSStateMap.bdsState.get(num));
        }
        updateState(xMSSMTParameters, j2, bArr, bArr2);
    }
}

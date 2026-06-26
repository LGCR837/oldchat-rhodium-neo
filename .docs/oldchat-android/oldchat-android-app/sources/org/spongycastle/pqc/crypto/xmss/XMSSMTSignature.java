package org.spongycastle.pqc.crypto.xmss;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.spongycastle.pqc.crypto.xmss.XMSSReducedSignature;

/* JADX INFO: loaded from: classes.dex */
public final class XMSSMTSignature implements XMSSStoreableObjectInterface {
    private final long index;
    private final XMSSMTParameters params;
    private final byte[] random;
    private final List<XMSSReducedSignature> reducedSignatures;

    public static class Builder {
        private final XMSSMTParameters params;
        private long index = 0;
        private byte[] random = null;
        private List<XMSSReducedSignature> reducedSignatures = null;
        private byte[] signature = null;

        public Builder(XMSSMTParameters xMSSMTParameters) {
            this.params = xMSSMTParameters;
        }

        public XMSSMTSignature build() {
            return new XMSSMTSignature(this);
        }

        public Builder withIndex(long j2) {
            this.index = j2;
            return this;
        }

        public Builder withRandom(byte[] bArr) {
            this.random = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public Builder withReducedSignatures(List<XMSSReducedSignature> list) {
            this.reducedSignatures = list;
            return this;
        }

        public Builder withSignature(byte[] bArr) {
            this.signature = bArr;
            return this;
        }
    }

    public long getIndex() {
        return this.index;
    }

    public byte[] getRandom() {
        return XMSSUtil.cloneArray(this.random);
    }

    public List<XMSSReducedSignature> getReducedSignatures() {
        return this.reducedSignatures;
    }

    @Override // org.spongycastle.pqc.crypto.xmss.XMSSStoreableObjectInterface
    public byte[] toByteArray() {
        int digestSize = this.params.getDigestSize();
        int len = this.params.getWOTSPlus().getParams().getLen();
        double height = this.params.getHeight();
        Double.isNaN(height);
        int iCeil = (int) Math.ceil(height / 8.0d);
        int height2 = ((this.params.getHeight() / this.params.getLayers()) + len) * digestSize;
        byte[] bArr = new byte[iCeil + digestSize + (this.params.getLayers() * height2)];
        XMSSUtil.copyBytesAtOffset(bArr, XMSSUtil.toBytesBigEndian(this.index, iCeil), 0);
        XMSSUtil.copyBytesAtOffset(bArr, this.random, iCeil);
        int i2 = iCeil + digestSize;
        Iterator<XMSSReducedSignature> it = this.reducedSignatures.iterator();
        while (it.hasNext()) {
            XMSSUtil.copyBytesAtOffset(bArr, it.next().toByteArray(), i2);
            i2 += height2;
        }
        return bArr;
    }

    private XMSSMTSignature(Builder builder) {
        XMSSMTParameters xMSSMTParameters = builder.params;
        this.params = xMSSMTParameters;
        if (xMSSMTParameters == null) {
            throw new NullPointerException("params == null");
        }
        int digestSize = xMSSMTParameters.getDigestSize();
        byte[] bArr = builder.signature;
        if (bArr == null) {
            this.index = builder.index;
            byte[] bArr2 = builder.random;
            if (bArr2 == null) {
                this.random = new byte[digestSize];
            } else {
                if (bArr2.length != digestSize) {
                    throw new IllegalArgumentException("size of random needs to be equal to size of digest");
                }
                this.random = bArr2;
            }
            List<XMSSReducedSignature> list = builder.reducedSignatures;
            if (list != null) {
                this.reducedSignatures = list;
                return;
            } else {
                this.reducedSignatures = new ArrayList();
                return;
            }
        }
        int len = xMSSMTParameters.getWOTSPlus().getParams().getLen();
        double height = xMSSMTParameters.getHeight();
        Double.isNaN(height);
        int iCeil = (int) Math.ceil(height / 8.0d);
        int height2 = ((xMSSMTParameters.getHeight() / xMSSMTParameters.getLayers()) + len) * digestSize;
        if (bArr.length != iCeil + digestSize + (xMSSMTParameters.getLayers() * height2)) {
            throw new IllegalArgumentException("signature has wrong size");
        }
        long jBytesToXBigEndian = XMSSUtil.bytesToXBigEndian(bArr, 0, iCeil);
        this.index = jBytesToXBigEndian;
        if (!XMSSUtil.isIndexValid(xMSSMTParameters.getHeight(), jBytesToXBigEndian)) {
            throw new IllegalArgumentException("index out of bounds");
        }
        this.random = XMSSUtil.extractBytesAtOffset(bArr, iCeil, digestSize);
        this.reducedSignatures = new ArrayList();
        for (int i2 = iCeil + digestSize; i2 < bArr.length; i2 += height2) {
            this.reducedSignatures.add(new XMSSReducedSignature.Builder(this.params.getXMSSParameters()).withReducedSignature(XMSSUtil.extractBytesAtOffset(bArr, i2, height2)).build());
        }
    }
}

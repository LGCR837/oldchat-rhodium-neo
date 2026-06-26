package org.spongycastle.pqc.crypto.xmss;

import org.spongycastle.pqc.crypto.xmss.XMSSReducedSignature;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public final class XMSSSignature extends XMSSReducedSignature implements XMSSStoreableObjectInterface {
    private final int index;
    private final byte[] random;

    public static class Builder extends XMSSReducedSignature.Builder {
        private int index;
        private final XMSSParameters params;
        private byte[] random;

        public Builder(XMSSParameters xMSSParameters) {
            super(xMSSParameters);
            this.index = 0;
            this.random = null;
            this.params = xMSSParameters;
        }

        public Builder withIndex(int i2) {
            this.index = i2;
            return this;
        }

        public Builder withRandom(byte[] bArr) {
            this.random = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public Builder withSignature(byte[] bArr) {
            if (bArr == null) {
                throw new NullPointerException("signature == null");
            }
            int digestSize = this.params.getDigestSize();
            int len = this.params.getWOTSPlus().getParams().getLen();
            int height = this.params.getHeight() * digestSize;
            this.index = Pack.bigEndianToInt(bArr, 0);
            this.random = XMSSUtil.extractBytesAtOffset(bArr, 4, digestSize);
            withReducedSignature(XMSSUtil.extractBytesAtOffset(bArr, 4 + digestSize, (len * digestSize) + height));
            return this;
        }

        @Override // org.spongycastle.pqc.crypto.xmss.XMSSReducedSignature.Builder
        public XMSSSignature build() {
            return new XMSSSignature(this);
        }
    }

    public int getIndex() {
        return this.index;
    }

    public byte[] getRandom() {
        return XMSSUtil.cloneArray(this.random);
    }

    @Override // org.spongycastle.pqc.crypto.xmss.XMSSReducedSignature, org.spongycastle.pqc.crypto.xmss.XMSSStoreableObjectInterface
    public byte[] toByteArray() {
        int digestSize = getParams().getDigestSize();
        byte[] bArr = new byte[digestSize + 4 + (getParams().getWOTSPlus().getParams().getLen() * digestSize) + (getParams().getHeight() * digestSize)];
        Pack.intToBigEndian(this.index, bArr, 0);
        XMSSUtil.copyBytesAtOffset(bArr, this.random, 4);
        int i2 = 4 + digestSize;
        for (byte[] bArr2 : getWOTSPlusSignature().toByteArray()) {
            XMSSUtil.copyBytesAtOffset(bArr, bArr2, i2);
            i2 += digestSize;
        }
        for (int i3 = 0; i3 < getAuthPath().size(); i3++) {
            XMSSUtil.copyBytesAtOffset(bArr, getAuthPath().get(i3).getValue(), i2);
            i2 += digestSize;
        }
        return bArr;
    }

    private XMSSSignature(Builder builder) {
        super(builder);
        this.index = builder.index;
        int digestSize = getParams().getDigestSize();
        byte[] bArr = builder.random;
        if (bArr == null) {
            this.random = new byte[digestSize];
        } else {
            if (bArr.length != digestSize) {
                throw new IllegalArgumentException("size of random needs to be equal to size of digest");
            }
            this.random = bArr;
        }
    }
}

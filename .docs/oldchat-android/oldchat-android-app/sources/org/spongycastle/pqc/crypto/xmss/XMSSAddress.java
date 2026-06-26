package org.spongycastle.pqc.crypto.xmss;

import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public abstract class XMSSAddress {
    private final int keyAndMask;
    private final int layerAddress;
    private final long treeAddress;
    private final int type;

    public static abstract class Builder<T extends Builder> {
        private final int type;
        private int layerAddress = 0;
        private long treeAddress = 0;
        private int keyAndMask = 0;

        public Builder(int i2) {
            this.type = i2;
        }

        public abstract XMSSAddress build();

        public abstract T getThis();

        public T withKeyAndMask(int i2) {
            this.keyAndMask = i2;
            return (T) getThis();
        }

        public T withLayerAddress(int i2) {
            this.layerAddress = i2;
            return (T) getThis();
        }

        public T withTreeAddress(long j2) {
            this.treeAddress = j2;
            return (T) getThis();
        }
    }

    public XMSSAddress(Builder builder) {
        this.layerAddress = builder.layerAddress;
        this.treeAddress = builder.treeAddress;
        this.type = builder.type;
        this.keyAndMask = builder.keyAndMask;
    }

    public final int getKeyAndMask() {
        return this.keyAndMask;
    }

    public final int getLayerAddress() {
        return this.layerAddress;
    }

    public final long getTreeAddress() {
        return this.treeAddress;
    }

    public final int getType() {
        return this.type;
    }

    public byte[] toByteArray() {
        byte[] bArr = new byte[32];
        Pack.intToBigEndian(this.layerAddress, bArr, 0);
        Pack.longToBigEndian(this.treeAddress, bArr, 4);
        Pack.intToBigEndian(this.type, bArr, 12);
        Pack.intToBigEndian(this.keyAndMask, bArr, 28);
        return bArr;
    }
}

package org.spongycastle.pqc.crypto.xmss;

import org.spongycastle.pqc.crypto.xmss.XMSSAddress;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
final class OTSHashAddress extends XMSSAddress {
    private static final int TYPE = 0;
    private final int chainAddress;
    private final int hashAddress;
    private final int otsAddress;

    public static class Builder extends XMSSAddress.Builder<Builder> {
        private int chainAddress;
        private int hashAddress;
        private int otsAddress;

        public Builder() {
            super(0);
            this.otsAddress = 0;
            this.chainAddress = 0;
            this.hashAddress = 0;
        }

        @Override // org.spongycastle.pqc.crypto.xmss.XMSSAddress.Builder
        public XMSSAddress build() {
            return new OTSHashAddress(this);
        }

        @Override // org.spongycastle.pqc.crypto.xmss.XMSSAddress.Builder
        public Builder getThis() {
            return this;
        }

        public Builder withChainAddress(int i2) {
            this.chainAddress = i2;
            return this;
        }

        public Builder withHashAddress(int i2) {
            this.hashAddress = i2;
            return this;
        }

        public Builder withOTSAddress(int i2) {
            this.otsAddress = i2;
            return this;
        }
    }

    public int getChainAddress() {
        return this.chainAddress;
    }

    public int getHashAddress() {
        return this.hashAddress;
    }

    public int getOTSAddress() {
        return this.otsAddress;
    }

    @Override // org.spongycastle.pqc.crypto.xmss.XMSSAddress
    public byte[] toByteArray() {
        byte[] byteArray = super.toByteArray();
        Pack.intToBigEndian(this.otsAddress, byteArray, 16);
        Pack.intToBigEndian(this.chainAddress, byteArray, 20);
        Pack.intToBigEndian(this.hashAddress, byteArray, 24);
        return byteArray;
    }

    private OTSHashAddress(Builder builder) {
        super(builder);
        this.otsAddress = builder.otsAddress;
        this.chainAddress = builder.chainAddress;
        this.hashAddress = builder.hashAddress;
    }
}

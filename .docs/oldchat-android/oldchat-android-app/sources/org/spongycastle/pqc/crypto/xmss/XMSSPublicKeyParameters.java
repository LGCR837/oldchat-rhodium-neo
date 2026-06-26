package org.spongycastle.pqc.crypto.xmss;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;

/* JADX INFO: loaded from: classes.dex */
public final class XMSSPublicKeyParameters extends AsymmetricKeyParameter implements XMSSStoreableObjectInterface {
    private final XMSSParameters params;
    private final byte[] publicSeed;
    private final byte[] root;

    public static class Builder {
        private final XMSSParameters params;
        private byte[] root = null;
        private byte[] publicSeed = null;
        private byte[] publicKey = null;

        public Builder(XMSSParameters xMSSParameters) {
            this.params = xMSSParameters;
        }

        public XMSSPublicKeyParameters build() {
            return new XMSSPublicKeyParameters(this);
        }

        public Builder withPublicKey(byte[] bArr) {
            this.publicKey = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public Builder withPublicSeed(byte[] bArr) {
            this.publicSeed = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public Builder withRoot(byte[] bArr) {
            this.root = XMSSUtil.cloneArray(bArr);
            return this;
        }
    }

    public XMSSParameters getParameters() {
        return this.params;
    }

    public byte[] getPublicSeed() {
        return XMSSUtil.cloneArray(this.publicSeed);
    }

    public byte[] getRoot() {
        return XMSSUtil.cloneArray(this.root);
    }

    @Override // org.spongycastle.pqc.crypto.xmss.XMSSStoreableObjectInterface
    public byte[] toByteArray() {
        int digestSize = this.params.getDigestSize();
        byte[] bArr = new byte[digestSize + digestSize];
        XMSSUtil.copyBytesAtOffset(bArr, this.root, 0);
        XMSSUtil.copyBytesAtOffset(bArr, this.publicSeed, digestSize);
        return bArr;
    }

    private XMSSPublicKeyParameters(Builder builder) {
        super(false);
        XMSSParameters xMSSParameters = builder.params;
        this.params = xMSSParameters;
        if (xMSSParameters == null) {
            throw new NullPointerException("params == null");
        }
        int digestSize = xMSSParameters.getDigestSize();
        byte[] bArr = builder.publicKey;
        if (bArr != null) {
            if (bArr.length != digestSize + digestSize) {
                throw new IllegalArgumentException("public key has wrong size");
            }
            this.root = XMSSUtil.extractBytesAtOffset(bArr, 0, digestSize);
            this.publicSeed = XMSSUtil.extractBytesAtOffset(bArr, digestSize, digestSize);
            return;
        }
        byte[] bArr2 = builder.root;
        if (bArr2 == null) {
            this.root = new byte[digestSize];
        } else {
            if (bArr2.length != digestSize) {
                throw new IllegalArgumentException("length of root must be equal to length of digest");
            }
            this.root = bArr2;
        }
        byte[] bArr3 = builder.publicSeed;
        if (bArr3 == null) {
            this.publicSeed = new byte[digestSize];
        } else {
            if (bArr3.length != digestSize) {
                throw new IllegalArgumentException("length of publicSeed must be equal to length of digest");
            }
            this.publicSeed = bArr3;
        }
    }
}

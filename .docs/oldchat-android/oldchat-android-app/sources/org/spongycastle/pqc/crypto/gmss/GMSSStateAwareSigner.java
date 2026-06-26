package org.spongycastle.pqc.crypto.gmss;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.pqc.crypto.StateAwareMessageSigner;
import org.spongycastle.util.Memoable;

/* JADX INFO: loaded from: classes.dex */
public class GMSSStateAwareSigner implements StateAwareMessageSigner {
    private final GMSSSigner gmssSigner;
    private GMSSPrivateKeyParameters key;

    public GMSSStateAwareSigner(Digest digest) {
        if (!(digest instanceof Memoable)) {
            throw new IllegalArgumentException("digest must implement Memoable");
        }
        final Memoable memoableCopy = ((Memoable) digest).copy();
        this.gmssSigner = new GMSSSigner(new GMSSDigestProvider() { // from class: org.spongycastle.pqc.crypto.gmss.GMSSStateAwareSigner.1
            @Override // org.spongycastle.pqc.crypto.gmss.GMSSDigestProvider
            public Digest get() {
                return (Digest) memoableCopy.copy();
            }
        });
    }

    @Override // org.spongycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        if (this.key == null) {
            throw new IllegalStateException("signing key no longer usable");
        }
        byte[] bArrGenerateSignature = this.gmssSigner.generateSignature(bArr);
        this.key = this.key.nextKey();
        return bArrGenerateSignature;
    }

    @Override // org.spongycastle.pqc.crypto.StateAwareMessageSigner
    public AsymmetricKeyParameter getUpdatedPrivateKey() {
        GMSSPrivateKeyParameters gMSSPrivateKeyParameters = this.key;
        this.key = null;
        return gMSSPrivateKeyParameters;
    }

    @Override // org.spongycastle.pqc.crypto.MessageSigner
    public void init(boolean z2, CipherParameters cipherParameters) {
        if (z2) {
            if (cipherParameters instanceof ParametersWithRandom) {
                this.key = (GMSSPrivateKeyParameters) ((ParametersWithRandom) cipherParameters).getParameters();
            } else {
                this.key = (GMSSPrivateKeyParameters) cipherParameters;
            }
        }
        this.gmssSigner.init(z2, cipherParameters);
    }

    @Override // org.spongycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        return this.gmssSigner.verifySignature(bArr, bArr2);
    }
}

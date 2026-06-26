package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import org.spongycastle.crypto.Digest;
import org.spongycastle.util.Shorts;

/* JADX INFO: loaded from: classes.dex */
class DeferredHash implements TlsHandshakeHash {
    protected static final int BUFFERING_HASH_LIMIT = 4;
    private DigestInputBuffer buf;
    protected TlsContext context;
    private Hashtable hashes;
    private Short prfHashAlgorithm;

    public DeferredHash() {
        this.buf = new DigestInputBuffer();
        this.hashes = new Hashtable();
        this.prfHashAlgorithm = null;
    }

    public void checkStopBuffering() {
        if (this.buf == null || this.hashes.size() > 4) {
            return;
        }
        Enumeration enumerationElements = this.hashes.elements();
        while (enumerationElements.hasMoreElements()) {
            this.buf.updateDigest((Digest) enumerationElements.nextElement());
        }
        this.buf = null;
    }

    public void checkTrackingHash(Short sh) {
        if (this.hashes.containsKey(sh)) {
            return;
        }
        this.hashes.put(sh, TlsUtils.createHash(sh.shortValue()));
    }

    @Override // org.spongycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i2) {
        throw new IllegalStateException("Use fork() to get a definite Digest");
    }

    @Override // org.spongycastle.crypto.tls.TlsHandshakeHash
    public Digest forkPRFHash() {
        checkStopBuffering();
        if (this.buf == null) {
            return TlsUtils.cloneHash(this.prfHashAlgorithm.shortValue(), (Digest) this.hashes.get(this.prfHashAlgorithm));
        }
        Digest digestCreateHash = TlsUtils.createHash(this.prfHashAlgorithm.shortValue());
        this.buf.updateDigest(digestCreateHash);
        return digestCreateHash;
    }

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        throw new IllegalStateException("Use fork() to get a definite Digest");
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        throw new IllegalStateException("Use fork() to get a definite Digest");
    }

    @Override // org.spongycastle.crypto.tls.TlsHandshakeHash
    public byte[] getFinalHash(short s2) {
        Digest digest = (Digest) this.hashes.get(Shorts.valueOf(s2));
        if (digest == null) {
            throw new IllegalStateException("HashAlgorithm." + HashAlgorithm.getText(s2) + " is not being tracked");
        }
        Digest digestCloneHash = TlsUtils.cloneHash(s2, digest);
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.updateDigest(digestCloneHash);
        }
        byte[] bArr = new byte[digestCloneHash.getDigestSize()];
        digestCloneHash.doFinal(bArr, 0);
        return bArr;
    }

    @Override // org.spongycastle.crypto.tls.TlsHandshakeHash
    public void init(TlsContext tlsContext) {
        this.context = tlsContext;
    }

    @Override // org.spongycastle.crypto.tls.TlsHandshakeHash
    public TlsHandshakeHash notifyPRFDetermined() {
        int prfAlgorithm = this.context.getSecurityParameters().getPrfAlgorithm();
        if (prfAlgorithm == 0) {
            CombinedHash combinedHash = new CombinedHash();
            combinedHash.init(this.context);
            this.buf.updateDigest(combinedHash);
            return combinedHash.notifyPRFDetermined();
        }
        Short shValueOf = Shorts.valueOf(TlsUtils.getHashAlgorithmForPRFAlgorithm(prfAlgorithm));
        this.prfHashAlgorithm = shValueOf;
        checkTrackingHash(shValueOf);
        return this;
    }

    @Override // org.spongycastle.crypto.Digest
    public void reset() {
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.reset();
            return;
        }
        Enumeration enumerationElements = this.hashes.elements();
        while (enumerationElements.hasMoreElements()) {
            ((Digest) enumerationElements.nextElement()).reset();
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsHandshakeHash
    public void sealHashAlgorithms() {
        checkStopBuffering();
    }

    @Override // org.spongycastle.crypto.tls.TlsHandshakeHash
    public TlsHandshakeHash stopTracking() {
        Digest digestCloneHash = TlsUtils.cloneHash(this.prfHashAlgorithm.shortValue(), (Digest) this.hashes.get(this.prfHashAlgorithm));
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.updateDigest(digestCloneHash);
        }
        DeferredHash deferredHash = new DeferredHash(this.prfHashAlgorithm, digestCloneHash);
        deferredHash.init(this.context);
        return deferredHash;
    }

    @Override // org.spongycastle.crypto.tls.TlsHandshakeHash
    public void trackHashAlgorithm(short s2) {
        if (this.buf == null) {
            throw new IllegalStateException("Too late to track more hash algorithms");
        }
        checkTrackingHash(Shorts.valueOf(s2));
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte b2) throws IOException {
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.write(b2);
            return;
        }
        Enumeration enumerationElements = this.hashes.elements();
        while (enumerationElements.hasMoreElements()) {
            ((Digest) enumerationElements.nextElement()).update(b2);
        }
    }

    private DeferredHash(Short sh, Digest digest) {
        this.buf = null;
        Hashtable hashtable = new Hashtable();
        this.hashes = hashtable;
        this.prfHashAlgorithm = sh;
        hashtable.put(sh, digest);
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte[] bArr, int i2, int i3) throws IOException {
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.write(bArr, i2, i3);
            return;
        }
        Enumeration enumerationElements = this.hashes.elements();
        while (enumerationElements.hasMoreElements()) {
            ((Digest) enumerationElements.nextElement()).update(bArr, i2, i3);
        }
    }
}

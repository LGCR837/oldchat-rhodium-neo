package org.spongycastle.crypto.tls;

/* JADX INFO: loaded from: classes.dex */
class DTLSEpoch {
    private final TlsCipher cipher;
    private final int epoch;
    private final DTLSReplayWindow replayWindow = new DTLSReplayWindow();
    private long sequenceNumber = 0;

    public DTLSEpoch(int i2, TlsCipher tlsCipher) {
        if (i2 < 0) {
            throw new IllegalArgumentException("'epoch' must be >= 0");
        }
        if (tlsCipher == null) {
            throw new IllegalArgumentException("'cipher' cannot be null");
        }
        this.epoch = i2;
        this.cipher = tlsCipher;
    }

    public long allocateSequenceNumber() {
        long j2 = this.sequenceNumber;
        this.sequenceNumber = 1 + j2;
        return j2;
    }

    public TlsCipher getCipher() {
        return this.cipher;
    }

    public int getEpoch() {
        return this.epoch;
    }

    public DTLSReplayWindow getReplayWindow() {
        return this.replayWindow;
    }

    public long getSequenceNumber() {
        return this.sequenceNumber;
    }
}

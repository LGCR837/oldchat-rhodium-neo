package org.spongycastle.asn1;

import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
abstract class LimitedInputStream extends InputStream {
    protected final InputStream _in;
    private int _limit;

    public LimitedInputStream(InputStream inputStream, int i2) {
        this._in = inputStream;
        this._limit = i2;
    }

    public int getRemaining() {
        return this._limit;
    }

    public void setParentEofDetect(boolean z2) {
        InputStream inputStream = this._in;
        if (inputStream instanceof IndefiniteLengthInputStream) {
            ((IndefiniteLengthInputStream) inputStream).setEofOn00(z2);
        }
    }
}

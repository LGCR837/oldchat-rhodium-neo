package org.spongycastle.asn1;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.util.io.Streams;

/* JADX INFO: loaded from: classes.dex */
class DefiniteLengthInputStream extends LimitedInputStream {
    private static final byte[] EMPTY_BYTES = new byte[0];
    private final int _originalLength;
    private int _remaining;

    public DefiniteLengthInputStream(InputStream inputStream, int i2) {
        super(inputStream, i2);
        if (i2 < 0) {
            throw new IllegalArgumentException("negative lengths not allowed");
        }
        this._originalLength = i2;
        this._remaining = i2;
        if (i2 == 0) {
            setParentEofDetect(true);
        }
    }

    @Override // org.spongycastle.asn1.LimitedInputStream
    public int getRemaining() {
        return this._remaining;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this._remaining == 0) {
            return -1;
        }
        int i2 = this._in.read();
        if (i2 >= 0) {
            int i3 = this._remaining - 1;
            this._remaining = i3;
            if (i3 == 0) {
                setParentEofDetect(true);
            }
            return i2;
        }
        throw new EOFException("DEF length " + this._originalLength + " object truncated by " + this._remaining);
    }

    public byte[] toByteArray() throws EOFException {
        int i2 = this._remaining;
        if (i2 == 0) {
            return EMPTY_BYTES;
        }
        byte[] bArr = new byte[i2];
        int fully = i2 - Streams.readFully(this._in, bArr);
        this._remaining = fully;
        if (fully == 0) {
            setParentEofDetect(true);
            return bArr;
        }
        throw new EOFException("DEF length " + this._originalLength + " object truncated by " + this._remaining);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) throws IOException {
        int i4 = this._remaining;
        if (i4 == 0) {
            return -1;
        }
        int i5 = this._in.read(bArr, i2, Math.min(i3, i4));
        if (i5 >= 0) {
            int i6 = this._remaining - i5;
            this._remaining = i6;
            if (i6 == 0) {
                setParentEofDetect(true);
            }
            return i5;
        }
        throw new EOFException("DEF length " + this._originalLength + " object truncated by " + this._remaining);
    }
}

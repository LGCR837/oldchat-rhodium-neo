package org.spongycastle.crypto.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.SkippingCipher;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.modes.AEADBlockCipher;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class CipherInputStream extends FilterInputStream {
    private static final int INPUT_BUF_SIZE = 2048;
    private AEADBlockCipher aeadBlockCipher;
    private byte[] buf;
    private int bufOff;
    private BufferedBlockCipher bufferedBlockCipher;
    private boolean finalized;
    private byte[] inBuf;
    private byte[] markBuf;
    private int markBufOff;
    private long markPosition;
    private int maxBuf;
    private SkippingCipher skippingCipher;
    private StreamCipher streamCipher;

    public CipherInputStream(InputStream inputStream, BufferedBlockCipher bufferedBlockCipher) {
        this(inputStream, bufferedBlockCipher, 2048);
    }

    private void ensureCapacity(int i2, boolean z2) {
        if (z2) {
            BufferedBlockCipher bufferedBlockCipher = this.bufferedBlockCipher;
            if (bufferedBlockCipher != null) {
                i2 = bufferedBlockCipher.getOutputSize(i2);
            } else {
                AEADBlockCipher aEADBlockCipher = this.aeadBlockCipher;
                if (aEADBlockCipher != null) {
                    i2 = aEADBlockCipher.getOutputSize(i2);
                }
            }
        } else {
            BufferedBlockCipher bufferedBlockCipher2 = this.bufferedBlockCipher;
            if (bufferedBlockCipher2 != null) {
                i2 = bufferedBlockCipher2.getUpdateOutputSize(i2);
            } else {
                AEADBlockCipher aEADBlockCipher2 = this.aeadBlockCipher;
                if (aEADBlockCipher2 != null) {
                    i2 = aEADBlockCipher2.getUpdateOutputSize(i2);
                }
            }
        }
        byte[] bArr = this.buf;
        if (bArr == null || bArr.length < i2) {
            this.buf = new byte[i2];
        }
    }

    private void finaliseCipher() throws IOException {
        try {
            this.finalized = true;
            ensureCapacity(0, true);
            BufferedBlockCipher bufferedBlockCipher = this.bufferedBlockCipher;
            if (bufferedBlockCipher != null) {
                this.maxBuf = bufferedBlockCipher.doFinal(this.buf, 0);
                return;
            }
            AEADBlockCipher aEADBlockCipher = this.aeadBlockCipher;
            if (aEADBlockCipher != null) {
                this.maxBuf = aEADBlockCipher.doFinal(this.buf, 0);
            } else {
                this.maxBuf = 0;
            }
        } catch (InvalidCipherTextException e2) {
            throw new InvalidCipherTextIOException("Error finalising cipher", e2);
        } catch (Exception e3) {
            throw new IOException("Error finalising cipher " + e3);
        }
    }

    private int nextChunk() throws IOException {
        if (this.finalized) {
            return -1;
        }
        this.bufOff = 0;
        this.maxBuf = 0;
        while (true) {
            int i2 = this.maxBuf;
            if (i2 != 0) {
                return i2;
            }
            int i3 = ((FilterInputStream) this).in.read(this.inBuf);
            if (i3 == -1) {
                finaliseCipher();
                int i4 = this.maxBuf;
                if (i4 == 0) {
                    return -1;
                }
                return i4;
            }
            try {
                ensureCapacity(i3, false);
                BufferedBlockCipher bufferedBlockCipher = this.bufferedBlockCipher;
                if (bufferedBlockCipher != null) {
                    this.maxBuf = bufferedBlockCipher.processBytes(this.inBuf, 0, i3, this.buf, 0);
                } else {
                    AEADBlockCipher aEADBlockCipher = this.aeadBlockCipher;
                    if (aEADBlockCipher != null) {
                        this.maxBuf = aEADBlockCipher.processBytes(this.inBuf, 0, i3, this.buf, 0);
                    } else {
                        this.streamCipher.processBytes(this.inBuf, 0, i3, this.buf, 0);
                        this.maxBuf = i3;
                    }
                }
            } catch (Exception e2) {
                throw new CipherIOException("Error processing stream ", e2);
            }
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() {
        return this.maxBuf - this.bufOff;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            ((FilterInputStream) this).in.close();
            this.bufOff = 0;
            this.maxBuf = 0;
            this.markBufOff = 0;
            this.markPosition = 0L;
            byte[] bArr = this.markBuf;
            if (bArr != null) {
                Arrays.fill(bArr, (byte) 0);
                this.markBuf = null;
            }
            byte[] bArr2 = this.buf;
            if (bArr2 != null) {
                Arrays.fill(bArr2, (byte) 0);
                this.buf = null;
            }
            Arrays.fill(this.inBuf, (byte) 0);
        } finally {
            if (!this.finalized) {
                finaliseCipher();
            }
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int i2) {
        ((FilterInputStream) this).in.mark(i2);
        SkippingCipher skippingCipher = this.skippingCipher;
        if (skippingCipher != null) {
            this.markPosition = skippingCipher.getPosition();
        }
        byte[] bArr = this.buf;
        if (bArr != null) {
            byte[] bArr2 = new byte[bArr.length];
            this.markBuf = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        }
        this.markBufOff = this.bufOff;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        if (this.skippingCipher != null) {
            return ((FilterInputStream) this).in.markSupported();
        }
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        if (this.bufOff >= this.maxBuf && nextChunk() < 0) {
            return -1;
        }
        byte[] bArr = this.buf;
        int i2 = this.bufOff;
        this.bufOff = i2 + 1;
        return bArr[i2] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        if (this.skippingCipher == null) {
            throw new IOException("cipher must implement SkippingCipher to be used with reset()");
        }
        ((FilterInputStream) this).in.reset();
        this.skippingCipher.seekTo(this.markPosition);
        byte[] bArr = this.markBuf;
        if (bArr != null) {
            this.buf = bArr;
        }
        this.bufOff = this.markBufOff;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j2) throws IOException {
        if (j2 <= 0) {
            return 0L;
        }
        if (this.skippingCipher == null) {
            int iMin = (int) Math.min(j2, available());
            this.bufOff += iMin;
            return iMin;
        }
        long jAvailable = available();
        if (j2 <= jAvailable) {
            this.bufOff = (int) (((long) this.bufOff) + j2);
            return j2;
        }
        this.bufOff = this.maxBuf;
        long jSkip = ((FilterInputStream) this).in.skip(j2 - jAvailable);
        if (jSkip == this.skippingCipher.skip(jSkip)) {
            return jSkip + jAvailable;
        }
        throw new IOException("Unable to skip cipher " + jSkip + " bytes.");
    }

    public CipherInputStream(InputStream inputStream, StreamCipher streamCipher) {
        this(inputStream, streamCipher, 2048);
    }

    public CipherInputStream(InputStream inputStream, AEADBlockCipher aEADBlockCipher) {
        this(inputStream, aEADBlockCipher, 2048);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CipherInputStream(InputStream inputStream, BufferedBlockCipher bufferedBlockCipher, int i2) {
        super(inputStream);
        this.bufferedBlockCipher = bufferedBlockCipher;
        this.inBuf = new byte[i2];
        this.skippingCipher = bufferedBlockCipher instanceof SkippingCipher ? (SkippingCipher) bufferedBlockCipher : null;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        if (this.bufOff >= this.maxBuf && nextChunk() < 0) {
            return -1;
        }
        int iMin = Math.min(i3, available());
        System.arraycopy(this.buf, this.bufOff, bArr, i2, iMin);
        this.bufOff += iMin;
        return iMin;
    }

    public CipherInputStream(InputStream inputStream, StreamCipher streamCipher, int i2) {
        super(inputStream);
        this.streamCipher = streamCipher;
        this.inBuf = new byte[i2];
        this.skippingCipher = streamCipher instanceof SkippingCipher ? (SkippingCipher) streamCipher : null;
    }

    public CipherInputStream(InputStream inputStream, AEADBlockCipher aEADBlockCipher, int i2) {
        super(inputStream);
        this.aeadBlockCipher = aEADBlockCipher;
        this.inBuf = new byte[i2];
        this.skippingCipher = aEADBlockCipher instanceof SkippingCipher ? (SkippingCipher) aEADBlockCipher : null;
    }
}

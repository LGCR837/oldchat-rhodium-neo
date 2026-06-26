package org.spongycastle.crypto.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.modes.AEADBlockCipher;

/* JADX INFO: loaded from: classes.dex */
public class CipherOutputStream extends FilterOutputStream {
    private AEADBlockCipher aeadBlockCipher;
    private byte[] buf;
    private BufferedBlockCipher bufferedBlockCipher;
    private final byte[] oneByte;
    private StreamCipher streamCipher;

    public CipherOutputStream(OutputStream outputStream, BufferedBlockCipher bufferedBlockCipher) {
        super(outputStream);
        this.oneByte = new byte[1];
        this.bufferedBlockCipher = bufferedBlockCipher;
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

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        IOException cipherIOException;
        IOException iOException;
        ensureCapacity(0, true);
        try {
            BufferedBlockCipher bufferedBlockCipher = this.bufferedBlockCipher;
            if (bufferedBlockCipher != null) {
                int iDoFinal = bufferedBlockCipher.doFinal(this.buf, 0);
                if (iDoFinal != 0) {
                    ((FilterOutputStream) this).out.write(this.buf, 0, iDoFinal);
                }
            } else {
                AEADBlockCipher aEADBlockCipher = this.aeadBlockCipher;
                if (aEADBlockCipher != null) {
                    int iDoFinal2 = aEADBlockCipher.doFinal(this.buf, 0);
                    if (iDoFinal2 != 0) {
                        ((FilterOutputStream) this).out.write(this.buf, 0, iDoFinal2);
                    }
                } else {
                    StreamCipher streamCipher = this.streamCipher;
                    if (streamCipher != null) {
                        streamCipher.reset();
                    }
                }
            }
            iOException = null;
        } catch (InvalidCipherTextException e2) {
            cipherIOException = new InvalidCipherTextIOException("Error finalising cipher data", e2);
            iOException = cipherIOException;
        } catch (Exception e3) {
            cipherIOException = new CipherIOException("Error closing stream: ", e3);
            iOException = cipherIOException;
        }
        try {
            flush();
            ((FilterOutputStream) this).out.close();
        } catch (IOException e4) {
            if (iOException == null) {
                iOException = e4;
            }
        }
        if (iOException != null) {
            throw iOException;
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        ((FilterOutputStream) this).out.flush();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i2) throws IOException {
        byte[] bArr = this.oneByte;
        byte b2 = (byte) i2;
        bArr[0] = b2;
        StreamCipher streamCipher = this.streamCipher;
        if (streamCipher != null) {
            ((FilterOutputStream) this).out.write(streamCipher.returnByte(b2));
        } else {
            write(bArr, 0, 1);
        }
    }

    public CipherOutputStream(OutputStream outputStream, StreamCipher streamCipher) {
        super(outputStream);
        this.oneByte = new byte[1];
        this.streamCipher = streamCipher;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) throws IOException {
        ensureCapacity(i3, false);
        BufferedBlockCipher bufferedBlockCipher = this.bufferedBlockCipher;
        if (bufferedBlockCipher != null) {
            int iProcessBytes = bufferedBlockCipher.processBytes(bArr, i2, i3, this.buf, 0);
            if (iProcessBytes != 0) {
                ((FilterOutputStream) this).out.write(this.buf, 0, iProcessBytes);
                return;
            }
            return;
        }
        AEADBlockCipher aEADBlockCipher = this.aeadBlockCipher;
        if (aEADBlockCipher != null) {
            int iProcessBytes2 = aEADBlockCipher.processBytes(bArr, i2, i3, this.buf, 0);
            if (iProcessBytes2 != 0) {
                ((FilterOutputStream) this).out.write(this.buf, 0, iProcessBytes2);
                return;
            }
            return;
        }
        this.streamCipher.processBytes(bArr, i2, i3, this.buf, 0);
        ((FilterOutputStream) this).out.write(this.buf, 0, i3);
    }

    public CipherOutputStream(OutputStream outputStream, AEADBlockCipher aEADBlockCipher) {
        super(outputStream);
        this.oneByte = new byte[1];
        this.aeadBlockCipher = aEADBlockCipher;
    }
}

package org.spongycastle.jcajce.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import org.spongycastle.crypto.io.InvalidCipherTextIOException;

/* JADX INFO: loaded from: classes.dex */
public class CipherOutputStream extends FilterOutputStream {
    private final Cipher cipher;
    private final byte[] oneByte;

    public CipherOutputStream(OutputStream outputStream, Cipher cipher) {
        super(outputStream);
        this.oneByte = new byte[1];
        this.cipher = cipher;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        IOException iOException;
        IOException iOException2;
        try {
            byte[] bArrDoFinal = this.cipher.doFinal();
            if (bArrDoFinal != null) {
                ((FilterOutputStream) this).out.write(bArrDoFinal);
            }
            iOException2 = null;
        } catch (GeneralSecurityException e2) {
            iOException = new InvalidCipherTextIOException("Error during cipher finalisation", e2);
            iOException2 = iOException;
        } catch (Exception e3) {
            iOException = new IOException("Error closing stream: " + e3);
            iOException2 = iOException;
        }
        try {
            flush();
            ((FilterOutputStream) this).out.close();
        } catch (IOException e4) {
            if (iOException2 == null) {
                iOException2 = e4;
            }
        }
        if (iOException2 != null) {
            throw iOException2;
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        ((FilterOutputStream) this).out.flush();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i2) throws IOException {
        byte[] bArr = this.oneByte;
        bArr[0] = (byte) i2;
        write(bArr, 0, 1);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) throws IOException {
        byte[] bArrUpdate = this.cipher.update(bArr, i2, i3);
        if (bArrUpdate != null) {
            ((FilterOutputStream) this).out.write(bArrUpdate);
        }
    }
}

package org.spongycastle.crypto.tls;

import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public interface TlsCompression {
    OutputStream compress(OutputStream outputStream);

    OutputStream decompress(OutputStream outputStream);
}

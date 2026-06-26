package org.spongycastle.util.encoders;

import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public interface Encoder {
    int decode(String str, OutputStream outputStream);

    int decode(byte[] bArr, int i2, int i3, OutputStream outputStream);

    int encode(byte[] bArr, int i2, int i3, OutputStream outputStream);
}

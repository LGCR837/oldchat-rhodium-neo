package org.spongycastle.crypto;

/* JADX INFO: loaded from: classes.dex */
public interface CharToByteConverter {
    byte[] convert(char[] cArr);

    String getType();
}

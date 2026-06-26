package org.spongycastle.crypto;

/* JADX INFO: loaded from: classes.dex */
public interface SkippingCipher {
    long getPosition();

    long seekTo(long j2);

    long skip(long j2);
}

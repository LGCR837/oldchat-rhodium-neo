package org.spongycastle.util;

/* JADX INFO: loaded from: classes.dex */
public interface Memoable {
    Memoable copy();

    void reset(Memoable memoable);
}

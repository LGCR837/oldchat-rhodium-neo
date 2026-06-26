package org.spongycastle.util;

/* JADX INFO: loaded from: classes.dex */
public interface Selector<T> extends Cloneable {
    Object clone();

    boolean match(T t2);
}

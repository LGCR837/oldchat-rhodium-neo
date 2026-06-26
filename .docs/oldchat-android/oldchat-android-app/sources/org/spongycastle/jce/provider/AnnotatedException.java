package org.spongycastle.jce.provider;

import org.spongycastle.jce.exception.ExtException;

/* JADX INFO: loaded from: classes.dex */
public class AnnotatedException extends Exception implements ExtException {
    private Throwable _underlyingException;

    public AnnotatedException(String str, Throwable th) {
        super(str);
        this._underlyingException = th;
    }

    @Override // java.lang.Throwable, org.spongycastle.jce.exception.ExtException
    public Throwable getCause() {
        return this._underlyingException;
    }

    public Throwable getUnderlyingException() {
        return this._underlyingException;
    }

    public AnnotatedException(String str) {
        this(str, null);
    }
}

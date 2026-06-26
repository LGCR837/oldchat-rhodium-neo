package org.spongycastle.x509;

import java.io.InputStream;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public abstract class X509StreamParserSpi {
    public abstract void engineInit(InputStream inputStream);

    public abstract Object engineRead();

    public abstract Collection engineReadAll();
}

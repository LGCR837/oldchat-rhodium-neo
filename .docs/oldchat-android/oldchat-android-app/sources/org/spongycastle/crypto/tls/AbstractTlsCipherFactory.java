package org.spongycastle.crypto.tls;

/* JADX INFO: loaded from: classes.dex */
public class AbstractTlsCipherFactory implements TlsCipherFactory {
    @Override // org.spongycastle.crypto.tls.TlsCipherFactory
    public TlsCipher createCipher(TlsContext tlsContext, int i2, int i3) throws TlsFatalAlert {
        throw new TlsFatalAlert((short) 80);
    }
}

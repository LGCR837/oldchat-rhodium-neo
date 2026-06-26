package org.spongycastle.jcajce.provider.config;

import java.io.OutputStream;
import java.security.KeyStore;

/* JADX INFO: loaded from: classes.dex */
public class PKCS12StoreParameter extends org.spongycastle.jcajce.PKCS12StoreParameter {
    public PKCS12StoreParameter(OutputStream outputStream, char[] cArr) {
        super(outputStream, cArr, false);
    }

    public PKCS12StoreParameter(OutputStream outputStream, KeyStore.ProtectionParameter protectionParameter) {
        super(outputStream, protectionParameter, false);
    }

    public PKCS12StoreParameter(OutputStream outputStream, char[] cArr, boolean z2) {
        super(outputStream, new KeyStore.PasswordProtection(cArr), z2);
    }

    public PKCS12StoreParameter(OutputStream outputStream, KeyStore.ProtectionParameter protectionParameter, boolean z2) {
        super(outputStream, protectionParameter, z2);
    }
}

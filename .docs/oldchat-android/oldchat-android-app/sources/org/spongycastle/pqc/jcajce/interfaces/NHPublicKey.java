package org.spongycastle.pqc.jcajce.interfaces;

import java.security.PublicKey;

/* JADX INFO: loaded from: classes.dex */
public interface NHPublicKey extends NHKey, PublicKey {
    byte[] getPublicData();
}

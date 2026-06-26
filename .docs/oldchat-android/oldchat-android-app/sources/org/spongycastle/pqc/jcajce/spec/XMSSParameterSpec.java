package org.spongycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class XMSSParameterSpec implements AlgorithmParameterSpec {
    public static final String SHA256 = "SHA256";
    public static final String SHA512 = "SHA512";
    public static final String SHAKE128 = "SHAKE128";
    public static final String SHAKE256 = "SHAKE256";
    private final int height;
    private final String treeDigest;

    public XMSSParameterSpec(int i2, String str) {
        this.height = i2;
        this.treeDigest = str;
    }

    public int getHeight() {
        return this.height;
    }

    public String getTreeDigest() {
        return this.treeDigest;
    }
}

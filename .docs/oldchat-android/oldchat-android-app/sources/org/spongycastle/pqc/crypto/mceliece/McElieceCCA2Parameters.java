package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class McElieceCCA2Parameters extends McElieceParameters {
    private final String digest;

    public McElieceCCA2Parameters() {
        this(11, 50, McElieceCCA2KeyGenParameterSpec.SHA256);
    }

    public String getDigest() {
        return this.digest;
    }

    public McElieceCCA2Parameters(String str) {
        this(11, 50, str);
    }

    public McElieceCCA2Parameters(int i2) {
        this(i2, McElieceCCA2KeyGenParameterSpec.SHA256);
    }

    public McElieceCCA2Parameters(int i2, String str) {
        super(i2);
        this.digest = str;
    }

    public McElieceCCA2Parameters(int i2, int i3) {
        this(i2, i3, McElieceCCA2KeyGenParameterSpec.SHA256);
    }

    public McElieceCCA2Parameters(int i2, int i3, String str) {
        super(i2, i3);
        this.digest = str;
    }

    public McElieceCCA2Parameters(int i2, int i3, int i4) {
        this(i2, i3, i4, McElieceCCA2KeyGenParameterSpec.SHA256);
    }

    public McElieceCCA2Parameters(int i2, int i3, int i4, String str) {
        super(i2, i3, i4);
        this.digest = str;
    }
}

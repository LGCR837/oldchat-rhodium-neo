package org.spongycastle.crypto.tls;

/* JADX INFO: loaded from: classes.dex */
public class SupplementalDataEntry {
    protected byte[] data;
    protected int dataType;

    public SupplementalDataEntry(int i2, byte[] bArr) {
        this.dataType = i2;
        this.data = bArr;
    }

    public byte[] getData() {
        return this.data;
    }

    public int getDataType() {
        return this.dataType;
    }
}

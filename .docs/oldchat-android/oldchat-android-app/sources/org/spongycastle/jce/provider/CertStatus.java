package org.spongycastle.jce.provider;

import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
class CertStatus {
    public static final int UNDETERMINED = 12;
    public static final int UNREVOKED = 11;
    int certStatus = 11;
    Date revocationDate = null;

    public int getCertStatus() {
        return this.certStatus;
    }

    public Date getRevocationDate() {
        return this.revocationDate;
    }

    public void setCertStatus(int i2) {
        this.certStatus = i2;
    }

    public void setRevocationDate(Date date) {
        this.revocationDate = date;
    }
}

package org.spongycastle.asn1.x509;

import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class GeneralNamesBuilder {
    private Vector names = new Vector();

    public GeneralNamesBuilder addName(GeneralName generalName) {
        this.names.addElement(generalName);
        return this;
    }

    public GeneralNamesBuilder addNames(GeneralNames generalNames) {
        GeneralName[] names = generalNames.getNames();
        for (int i2 = 0; i2 != names.length; i2++) {
            this.names.addElement(names[i2]);
        }
        return this;
    }

    public GeneralNames build() {
        int size = this.names.size();
        GeneralName[] generalNameArr = new GeneralName[size];
        for (int i2 = 0; i2 != size; i2++) {
            generalNameArr[i2] = (GeneralName) this.names.elementAt(i2);
        }
        return new GeneralNames(generalNameArr);
    }
}

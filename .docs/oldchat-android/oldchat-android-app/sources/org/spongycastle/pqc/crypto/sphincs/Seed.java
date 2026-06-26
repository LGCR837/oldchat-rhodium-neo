package org.spongycastle.pqc.crypto.sphincs;

import org.spongycastle.crypto.engines.ChaChaEngine;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.pqc.crypto.sphincs.Tree;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
class Seed {
    public static void get_seed(HashFunctions hashFunctions, byte[] bArr, int i2, byte[] bArr2, Tree.leafaddr leafaddrVar) {
        byte[] bArr3 = new byte[40];
        for (int i3 = 0; i3 < 32; i3++) {
            bArr3[i3] = bArr2[i3];
        }
        Pack.longToLittleEndian((leafaddrVar.subleaf << 59) | ((long) leafaddrVar.level) | (leafaddrVar.subtree << 4), bArr3, 32);
        hashFunctions.varlen_hash(bArr, i2, bArr3, 40);
    }

    public static void prg(byte[] bArr, int i2, long j2, byte[] bArr2, int i3) {
        ChaChaEngine chaChaEngine = new ChaChaEngine(12);
        chaChaEngine.init(true, new ParametersWithIV(new KeyParameter(bArr2, i3, 32), new byte[8]));
        chaChaEngine.processBytes(bArr, i2, (int) j2, bArr, i2);
    }
}

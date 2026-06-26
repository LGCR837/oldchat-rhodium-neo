package org.spongycastle.pqc.crypto.sphincs;

/* JADX INFO: loaded from: classes.dex */
class Tree {

    public static class leafaddr {
        int level;
        long subleaf;
        long subtree;

        public leafaddr() {
        }

        public leafaddr(leafaddr leafaddrVar) {
            this.level = leafaddrVar.level;
            this.subtree = leafaddrVar.subtree;
            this.subleaf = leafaddrVar.subleaf;
        }
    }

    public static void gen_leaf_wots(HashFunctions hashFunctions, byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, leafaddr leafaddrVar) {
        byte[] bArr4 = new byte[32];
        byte[] bArr5 = new byte[2144];
        Wots wots = new Wots();
        Seed.get_seed(hashFunctions, bArr4, 0, bArr3, leafaddrVar);
        wots.wots_pkgen(hashFunctions, bArr5, 0, bArr4, 0, bArr2, i3);
        l_tree(hashFunctions, bArr, i2, bArr5, 0, bArr2, i3);
    }

    public static void l_tree(HashFunctions hashFunctions, byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, int i4) {
        int i5;
        int i6 = 67;
        for (int i7 = 0; i7 < 7; i7++) {
            int i8 = 0;
            while (true) {
                i5 = i6 >>> 1;
                if (i8 >= i5) {
                    break;
                }
                hashFunctions.hash_2n_n_mask(bArr2, i3 + (i8 * 32), bArr2, i3 + (i8 * 64), bArr3, i4 + (i7 * 64));
                i8++;
            }
            if ((i6 & 1) != 0) {
                System.arraycopy(bArr2, i3 + ((i6 - 1) * 32), bArr2, (i5 * 32) + i3, 32);
                i5++;
            }
            i6 = i5;
        }
        System.arraycopy(bArr2, i3, bArr, i2, 32);
    }

    public static void treehash(HashFunctions hashFunctions, byte[] bArr, int i2, int i3, byte[] bArr2, leafaddr leafaddrVar, byte[] bArr3, int i4) {
        leafaddr leafaddrVar2 = new leafaddr(leafaddrVar);
        int i5 = i3 + 1;
        byte[] bArr4 = new byte[i5 * 32];
        int[] iArr = new int[i5];
        int i6 = 1;
        int i7 = (int) (leafaddrVar2.subleaf + ((long) (1 << i3)));
        int i8 = 0;
        while (leafaddrVar2.subleaf < i7) {
            gen_leaf_wots(hashFunctions, bArr4, i8 * 32, bArr3, i4, bArr2, leafaddrVar2);
            iArr[i8] = 0;
            int i9 = i8 + i6;
            while (i9 > i6) {
                int i10 = iArr[i9 - 1];
                int i11 = i9 - 2;
                if (i10 == iArr[i11]) {
                    int i12 = i11 * 32;
                    int[] iArr2 = iArr;
                    hashFunctions.hash_2n_n_mask(bArr4, i12, bArr4, i12, bArr3, i4 + ((i10 + 7) * 64));
                    iArr2[i11] = iArr2[i11] + 1;
                    i9--;
                    i7 = i7;
                    iArr = iArr2;
                    i6 = 1;
                }
            }
            leafaddrVar2.subleaf++;
            i8 = i9;
            i7 = i7;
            iArr = iArr;
            i6 = 1;
        }
        for (int i13 = 0; i13 < 32; i13++) {
            bArr[i2 + i13] = bArr4[i13];
        }
    }
}

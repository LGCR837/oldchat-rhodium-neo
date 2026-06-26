package e0;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {
    public static int a(b bVar) {
        return b(bVar, true) + b(bVar, false);
    }

    public static int b(b bVar, boolean z2) {
        int iD = z2 ? bVar.d() : bVar.e();
        int iE = z2 ? bVar.e() : bVar.d();
        byte[][] bArrC = bVar.c();
        int i2 = 0;
        for (int i3 = 0; i3 < iD; i3++) {
            byte b2 = -1;
            int i4 = 0;
            for (int i5 = 0; i5 < iE; i5++) {
                byte b3 = z2 ? bArrC[i3][i5] : bArrC[i5][i3];
                if (b3 == b2) {
                    i4++;
                } else {
                    if (i4 >= 5) {
                        i2 += i4 - 2;
                    }
                    b2 = b3;
                    i4 = 1;
                }
            }
            if (i4 >= 5) {
                i2 += i4 - 2;
            }
        }
        return i2;
    }

    public static int c(b bVar) {
        byte[][] bArrC = bVar.c();
        int iE = bVar.e();
        int iD = bVar.d();
        int i2 = 0;
        for (int i3 = 0; i3 < iD - 1; i3++) {
            byte[] bArr = bArrC[i3];
            int i4 = 0;
            while (i4 < iE - 1) {
                byte b2 = bArr[i4];
                int i5 = i4 + 1;
                if (b2 == bArr[i5]) {
                    byte[] bArr2 = bArrC[i3 + 1];
                    if (b2 == bArr2[i4] && b2 == bArr2[i5]) {
                        i2++;
                    }
                }
                i4 = i5;
            }
        }
        return i2 * 3;
    }

    public static int d(b bVar) {
        byte[][] bArrC = bVar.c();
        int iE = bVar.e();
        int iD = bVar.d();
        int i2 = 0;
        for (int i3 = 0; i3 < iD; i3++) {
            for (int i4 = 0; i4 < iE; i4++) {
                byte[] bArr = bArrC[i3];
                int i5 = i4 + 6;
                if (i5 < iE && bArr[i4] == 1 && bArr[i4 + 1] == 0 && bArr[i4 + 2] == 1 && bArr[i4 + 3] == 1 && bArr[i4 + 4] == 1 && bArr[i4 + 5] == 0 && bArr[i5] == 1 && (g(bArr, i4 - 4, i4) || g(bArr, i4 + 7, i4 + 11))) {
                    i2++;
                }
                int i6 = i3 + 6;
                if (i6 < iD && bArrC[i3][i4] == 1 && bArrC[i3 + 1][i4] == 0 && bArrC[i3 + 2][i4] == 1 && bArrC[i3 + 3][i4] == 1 && bArrC[i3 + 4][i4] == 1 && bArrC[i3 + 5][i4] == 0 && bArrC[i6][i4] == 1 && (h(bArrC, i4, i3 - 4, i3) || h(bArrC, i4, i3 + 7, i3 + 11))) {
                    i2++;
                }
            }
        }
        return i2 * 40;
    }

    public static int e(b bVar) {
        byte[][] bArrC = bVar.c();
        int iE = bVar.e();
        int iD = bVar.d();
        int i2 = 0;
        for (int i3 = 0; i3 < iD; i3++) {
            byte[] bArr = bArrC[i3];
            for (int i4 = 0; i4 < iE; i4++) {
                if (bArr[i4] == 1) {
                    i2++;
                }
            }
        }
        int iD2 = bVar.d() * bVar.e();
        return ((Math.abs((i2 << 1) - iD2) * 10) / iD2) * 10;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003f A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean f(int r1, int r2, int r3) {
        /*
            r0 = 1
            switch(r1) {
                case 0: goto L32;
                case 1: goto L33;
                case 2: goto L3a;
                case 3: goto L36;
                case 4: goto L2e;
                case 5: goto L26;
                case 6: goto L1e;
                case 7: goto L14;
                default: goto L4;
            }
        L4:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Invalid mask pattern: "
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r1 = r3.concat(r1)
            r2.<init>(r1)
            throw r2
        L14:
            int r1 = r3 * r2
            int r1 = r1 % 3
            int r3 = r3 + r2
            r2 = r3 & 1
            int r1 = r1 + r2
        L1c:
            r1 = r1 & r0
            goto L3c
        L1e:
            int r3 = r3 * r2
            r1 = r3 & 1
            int r3 = r3 % 3
            int r1 = r1 + r3
            goto L1c
        L26:
            int r3 = r3 * r2
            r1 = r3 & 1
            int r3 = r3 % 3
            int r1 = r1 + r3
            goto L3c
        L2e:
            int r3 = r3 / 2
            int r2 = r2 / 3
        L32:
            int r3 = r3 + r2
        L33:
            r1 = r3 & 1
            goto L3c
        L36:
            int r3 = r3 + r2
            int r1 = r3 % 3
            goto L3c
        L3a:
            int r1 = r2 % 3
        L3c:
            if (r1 != 0) goto L3f
            return r0
        L3f:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: e0.d.f(int, int, int):boolean");
    }

    public static boolean g(byte[] bArr, int i2, int i3) {
        int iMin = Math.min(i3, bArr.length);
        for (int iMax = Math.max(i2, 0); iMax < iMin; iMax++) {
            if (bArr[iMax] == 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean h(byte[][] bArr, int i2, int i3, int i4) {
        int iMin = Math.min(i4, bArr.length);
        for (int iMax = Math.max(i3, 0); iMax < iMin; iMax++) {
            if (bArr[iMax][i2] == 1) {
                return false;
            }
        }
        return true;
    }
}

package L;

/* JADX INFO: loaded from: classes.dex */
public final class j extends h {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b f403e;

    public j(H.h hVar) {
        super(hVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x009c A[PHI: r5
  0x009c: PHI (r5v3 int) = (r5v2 int), (r5v6 int), (r5v6 int) binds: [B:31:0x007b, B:33:0x007f, B:34:0x0081] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int[][] i(byte[] r17, int r18, int r19, int r20, int r21) {
        /*
            r0 = r18
            r1 = r19
            r2 = 8
            int r3 = r21 + (-8)
            int r4 = r20 + (-8)
            r5 = 2
            int[] r6 = new int[r5]
            r7 = 1
            r6[r7] = r0
            r8 = 0
            r6[r8] = r1
            java.lang.Class r9 = java.lang.Integer.TYPE
            java.lang.Object r6 = java.lang.reflect.Array.newInstance(r9, r6)
            int[][] r6 = (int[][]) r6
            r9 = 0
        L1c:
            if (r9 >= r1) goto Lb5
            int r10 = r9 << 3
            if (r10 <= r3) goto L23
            r10 = r3
        L23:
            r11 = 0
        L24:
            if (r11 >= r0) goto Laa
            int r12 = r11 << 3
            if (r12 <= r4) goto L2b
            r12 = r4
        L2b:
            int r13 = r10 * r20
            int r13 = r13 + r12
            r12 = 255(0xff, float:3.57E-43)
            r8 = 255(0xff, float:3.57E-43)
            r14 = 0
            r15 = 0
            r16 = 0
        L36:
            if (r14 >= r2) goto L74
            r7 = r16
            r5 = 0
        L3b:
            if (r5 >= r2) goto L4e
            int r16 = r13 + r5
            r2 = r17[r16]
            r2 = r2 & r12
            int r15 = r15 + r2
            if (r2 >= r8) goto L46
            r8 = r2
        L46:
            if (r2 <= r7) goto L49
            r7 = r2
        L49:
            int r5 = r5 + 1
            r2 = 8
            goto L3b
        L4e:
            int r2 = r7 - r8
            r5 = 24
            if (r2 <= r5) goto L6a
        L54:
            int r14 = r14 + 1
            int r13 = r13 + r20
            r2 = 8
            if (r14 >= r2) goto L6a
            r5 = 0
        L5d:
            if (r5 >= r2) goto L54
            int r16 = r13 + r5
            r2 = r17[r16]
            r2 = r2 & r12
            int r15 = r15 + r2
            int r5 = r5 + 1
            r2 = 8
            goto L5d
        L6a:
            r2 = 1
            int r14 = r14 + r2
            int r13 = r13 + r20
            r16 = r7
            r2 = 8
            r7 = 1
            goto L36
        L74:
            r2 = 1
            int r5 = r15 >> 6
            int r7 = r16 - r8
            r12 = 24
            if (r7 > r12) goto L9c
            int r5 = r8 / 2
            if (r9 <= 0) goto L9c
            if (r11 <= 0) goto L9c
            int r7 = r9 + (-1)
            r7 = r6[r7]
            r12 = r7[r11]
            r13 = r6[r9]
            int r14 = r11 + (-1)
            r13 = r13[r14]
            r15 = 2
            int r13 = r13 * 2
            int r12 = r12 + r13
            r7 = r7[r14]
            int r12 = r12 + r7
            int r7 = r12 / 4
            if (r8 >= r7) goto L9d
            r5 = r7
            goto L9d
        L9c:
            r15 = 2
        L9d:
            r7 = r6[r9]
            r7[r11] = r5
            int r11 = r11 + 1
            r2 = 8
            r5 = 2
            r7 = 1
            r8 = 0
            goto L24
        Laa:
            r2 = 1
            r15 = 2
            int r9 = r9 + 1
            r2 = 8
            r5 = 2
            r7 = 1
            r8 = 0
            goto L1c
        Lb5:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: L.j.i(byte[], int, int, int, int):int[][]");
    }

    public static void j(byte[] bArr, int i2, int i3, int i4, int i5, int[][] iArr, b bVar) {
        int i6 = i5 - 8;
        int i7 = i4 - 8;
        for (int i8 = 0; i8 < i3; i8++) {
            int i9 = i8 << 3;
            int i10 = i9 > i6 ? i6 : i9;
            int iK = k(i8, 2, i3 - 3);
            for (int i11 = 0; i11 < i2; i11++) {
                int i12 = i11 << 3;
                int i13 = i12 > i7 ? i7 : i12;
                int iK2 = k(i11, 2, i2 - 3);
                int i14 = 0;
                for (int i15 = -2; i15 <= 2; i15++) {
                    int[] iArr2 = iArr[iK + i15];
                    i14 += iArr2[iK2 - 2] + iArr2[iK2 - 1] + iArr2[iK2] + iArr2[iK2 + 1] + iArr2[iK2 + 2];
                }
                l(bArr, i13, i10, i14 / 25, i4, bVar);
            }
        }
    }

    public static int k(int i2, int i3, int i4) {
        return i2 < i3 ? i3 : i2 > i4 ? i4 : i2;
    }

    public static void l(byte[] bArr, int i2, int i3, int i4, int i5, b bVar) {
        int i6 = (i3 * i5) + i2;
        int i7 = 0;
        while (i7 < 8) {
            for (int i8 = 0; i8 < 8; i8++) {
                if ((bArr[i6 + i8] & 255) <= i4) {
                    bVar.l(i2 + i8, i3 + i7);
                }
            }
            i7++;
            i6 += i5;
        }
    }

    @Override // H.b
    public H.b a(H.h hVar) {
        return new j(hVar);
    }

    @Override // L.h, H.b
    public b b() {
        b bVar = this.f403e;
        if (bVar != null) {
            return bVar;
        }
        H.h hVarE = e();
        int iD = hVarE.d();
        int iA = hVarE.a();
        if (iD < 40 || iA < 40) {
            this.f403e = super.b();
        } else {
            byte[] bArrB = hVarE.b();
            int i2 = iD >> 3;
            if ((iD & 7) != 0) {
                i2++;
            }
            int i3 = i2;
            int i4 = iA >> 3;
            if ((iA & 7) != 0) {
                i4++;
            }
            int i5 = i4;
            int[][] iArrI = i(bArrB, i3, i5, iD, iA);
            b bVar2 = new b(iD, iA);
            j(bArrB, i3, i5, iD, iA, iArrI, bVar2);
            this.f403e = bVar2;
        }
        return this.f403e;
    }
}

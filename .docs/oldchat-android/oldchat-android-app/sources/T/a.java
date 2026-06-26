package T;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class a extends k {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final char[] f487d = "0123456789-$:/.+ABCD".toCharArray();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f488e = {3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 12, 24, 69, 81, 84, 21, 26, 41, 11, 14};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final char[] f489f = {'A', 'B', 'C', 'D'};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final StringBuilder f490a = new StringBuilder(20);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f491b = new int[80];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f492c = 0;

    public static boolean g(char[] cArr, char c2) {
        if (cArr != null) {
            for (char c3 : cArr) {
                if (c3 == c2) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // T.k
    public H.n b(int i2, L.a aVar, Map map) throws H.j {
        int i3;
        Arrays.fill(this.f491b, 0);
        j(aVar);
        int i4 = i();
        this.f490a.setLength(0);
        int i5 = i4;
        while (true) {
            int iK = k(i5);
            if (iK == -1) {
                throw H.j.a();
            }
            this.f490a.append((char) iK);
            i3 = i5 + 8;
            if ((this.f490a.length() > 1 && g(f489f, f487d[iK])) || i3 >= this.f492c) {
                break;
            }
            i5 = i3;
        }
        int i6 = i5 + 7;
        int i7 = this.f491b[i6];
        int i8 = 0;
        for (int i9 = -8; i9 < -1; i9++) {
            i8 += this.f491b[i3 + i9];
        }
        if (i3 < this.f492c && i7 < i8 / 2) {
            throw H.j.a();
        }
        l(i4);
        for (int i10 = 0; i10 < this.f490a.length(); i10++) {
            StringBuilder sb = this.f490a;
            sb.setCharAt(i10, f487d[sb.charAt(i10)]);
        }
        char cCharAt = this.f490a.charAt(0);
        char[] cArr = f489f;
        if (!g(cArr, cCharAt)) {
            throw H.j.a();
        }
        StringBuilder sb2 = this.f490a;
        if (!g(cArr, sb2.charAt(sb2.length() - 1))) {
            throw H.j.a();
        }
        if (this.f490a.length() <= 3) {
            throw H.j.a();
        }
        if (map == null || !map.containsKey(H.e.RETURN_CODABAR_START_END)) {
            StringBuilder sb3 = this.f490a;
            sb3.deleteCharAt(sb3.length() - 1);
            this.f490a.deleteCharAt(0);
        }
        int i11 = 0;
        for (int i12 = 0; i12 < i4; i12++) {
            i11 += this.f491b[i12];
        }
        float f2 = i11;
        while (i4 < i6) {
            i11 += this.f491b[i4];
            i4++;
        }
        float f3 = i2;
        return new H.n(this.f490a.toString(), null, new H.p[]{new H.p(f2, f3), new H.p(i11, f3)}, H.a.CODABAR);
    }

    public final void h(int i2) {
        int[] iArr = this.f491b;
        int i3 = this.f492c;
        iArr[i3] = i2;
        int i4 = i3 + 1;
        this.f492c = i4;
        if (i4 >= iArr.length) {
            int[] iArr2 = new int[i4 << 1];
            System.arraycopy(iArr, 0, iArr2, 0, i4);
            this.f491b = iArr2;
        }
    }

    public final int i() throws H.j {
        for (int i2 = 1; i2 < this.f492c; i2 += 2) {
            int iK = k(i2);
            if (iK != -1 && g(f489f, f487d[iK])) {
                int i3 = 0;
                for (int i4 = i2; i4 < i2 + 7; i4++) {
                    i3 += this.f491b[i4];
                }
                if (i2 == 1 || this.f491b[i2 - 1] >= i3 / 2) {
                    return i2;
                }
            }
        }
        throw H.j.a();
    }

    public final void j(L.a aVar) throws H.j {
        int i2 = 0;
        this.f492c = 0;
        int iJ = aVar.j(0);
        int iK = aVar.k();
        if (iJ >= iK) {
            throw H.j.a();
        }
        boolean z2 = true;
        while (iJ < iK) {
            if (aVar.g(iJ) != z2) {
                i2++;
            } else {
                h(i2);
                z2 = !z2;
                i2 = 1;
            }
            iJ++;
        }
        h(i2);
    }

    public final int k(int i2) {
        int i3 = i2 + 7;
        if (i3 >= this.f492c) {
            return -1;
        }
        int[] iArr = this.f491b;
        int i4 = Integer.MAX_VALUE;
        int i5 = 0;
        int i6 = Integer.MAX_VALUE;
        int i7 = 0;
        for (int i8 = i2; i8 < i3; i8 += 2) {
            int i9 = iArr[i8];
            if (i9 < i6) {
                i6 = i9;
            }
            if (i9 > i7) {
                i7 = i9;
            }
        }
        int i10 = (i6 + i7) / 2;
        int i11 = 0;
        for (int i12 = i2 + 1; i12 < i3; i12 += 2) {
            int i13 = iArr[i12];
            if (i13 < i4) {
                i4 = i13;
            }
            if (i13 > i11) {
                i11 = i13;
            }
        }
        int i14 = (i4 + i11) / 2;
        int i15 = 128;
        int i16 = 0;
        for (int i17 = 0; i17 < 7; i17++) {
            i15 >>= 1;
            if (iArr[i2 + i17] > ((i17 & 1) == 0 ? i10 : i14)) {
                i16 |= i15;
            }
        }
        while (true) {
            int[] iArr2 = f488e;
            if (i5 >= iArr2.length) {
                return -1;
            }
            if (iArr2[i5] == i16) {
                return i5;
            }
            i5++;
        }
    }

    public final void l(int i2) throws H.j {
        int[] iArr = new int[4];
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        int[] iArr2 = new int[4];
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int length = this.f490a.length() - 1;
        int i3 = i2;
        int i4 = 0;
        while (true) {
            int i5 = f488e[this.f490a.charAt(i4)];
            for (int i6 = 6; i6 >= 0; i6--) {
                int i7 = (i6 & 1) + ((i5 & 1) << 1);
                iArr[i7] = iArr[i7] + this.f491b[i3 + i6];
                iArr2[i7] = iArr2[i7] + 1;
                i5 >>= 1;
            }
            if (i4 >= length) {
                break;
            }
            i3 += 8;
            i4++;
        }
        float[] fArr = new float[4];
        float[] fArr2 = new float[4];
        for (int i8 = 0; i8 < 2; i8++) {
            fArr2[i8] = 0.0f;
            int i9 = i8 + 2;
            int i10 = iArr[i9];
            int i11 = iArr2[i9];
            float f2 = ((iArr[i8] / iArr2[i8]) + (i10 / i11)) / 2.0f;
            fArr2[i9] = f2;
            fArr[i8] = f2;
            fArr[i9] = ((i10 * 2.0f) + 1.5f) / i11;
        }
        int i12 = i2;
        int i13 = 0;
        loop3: while (true) {
            int i14 = f488e[this.f490a.charAt(i13)];
            for (int i15 = 6; i15 >= 0; i15--) {
                int i16 = (i15 & 1) + ((i14 & 1) << 1);
                float f3 = this.f491b[i12 + i15];
                if (f3 < fArr2[i16] || f3 > fArr[i16]) {
                    break loop3;
                }
                i14 >>= 1;
            }
            if (i13 >= length) {
                return;
            }
            i12 += 8;
            i13++;
        }
        throw H.j.a();
    }
}

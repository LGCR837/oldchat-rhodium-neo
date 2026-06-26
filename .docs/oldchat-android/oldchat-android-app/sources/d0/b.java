package d0;

import H.j;
import H.q;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final L.b f4916a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f4918c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f4919d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f4920e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f4921f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f4922g;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f4917b = new ArrayList(5);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int[] f4923h = new int[3];

    public b(L.b bVar, int i2, int i3, int i4, int i5, float f2, q qVar) {
        this.f4916a = bVar;
        this.f4918c = i2;
        this.f4919d = i3;
        this.f4920e = i4;
        this.f4921f = i5;
        this.f4922g = f2;
    }

    public static float a(int[] iArr, int i2) {
        return (i2 - iArr[2]) - (iArr[1] / 2.0f);
    }

    public final float b(int i2, int i3, int i4, int i5) {
        L.b bVar = this.f4916a;
        int iG = bVar.g();
        int[] iArr = this.f4923h;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        int i6 = i2;
        while (i6 >= 0 && bVar.d(i3, i6)) {
            int i7 = iArr[1];
            if (i7 > i4) {
                break;
            }
            iArr[1] = i7 + 1;
            i6--;
        }
        if (i6 >= 0 && iArr[1] <= i4) {
            while (i6 >= 0 && !bVar.d(i3, i6)) {
                int i8 = iArr[0];
                if (i8 > i4) {
                    break;
                }
                iArr[0] = i8 + 1;
                i6--;
            }
            if (iArr[0] > i4) {
                return Float.NaN;
            }
            int i9 = i2 + 1;
            while (i9 < iG && bVar.d(i3, i9)) {
                int i10 = iArr[1];
                if (i10 > i4) {
                    break;
                }
                iArr[1] = i10 + 1;
                i9++;
            }
            if (i9 != iG && iArr[1] <= i4) {
                while (i9 < iG && !bVar.d(i3, i9)) {
                    int i11 = iArr[2];
                    if (i11 > i4) {
                        break;
                    }
                    iArr[2] = i11 + 1;
                    i9++;
                }
                int i12 = iArr[2];
                if (i12 <= i4 && Math.abs(((iArr[0] + iArr[1]) + i12) - i5) * 5 < i5 * 2 && d(iArr)) {
                    return a(iArr, i9);
                }
            }
        }
        return Float.NaN;
    }

    public a c() throws j {
        a aVarE;
        a aVarE2;
        int i2 = this.f4918c;
        int i3 = this.f4921f;
        int i4 = this.f4920e + i2;
        int i5 = this.f4919d + (i3 / 2);
        int[] iArr = new int[3];
        for (int i6 = 0; i6 < i3; i6++) {
            int i7 = ((i6 & 1) == 0 ? (i6 + 1) / 2 : -((i6 + 1) / 2)) + i5;
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            int i8 = i2;
            while (i8 < i4 && !this.f4916a.d(i8, i7)) {
                i8++;
            }
            int i9 = 0;
            while (i8 < i4) {
                if (!this.f4916a.d(i8, i7)) {
                    if (i9 == 1) {
                        i9++;
                    }
                    iArr[i9] = iArr[i9] + 1;
                } else if (i9 == 1) {
                    iArr[1] = iArr[1] + 1;
                } else if (i9 != 2) {
                    i9++;
                    iArr[i9] = iArr[i9] + 1;
                } else {
                    if (d(iArr) && (aVarE2 = e(iArr, i7, i8)) != null) {
                        return aVarE2;
                    }
                    iArr[0] = iArr[2];
                    iArr[1] = 1;
                    iArr[2] = 0;
                    i9 = 1;
                }
                i8++;
            }
            if (d(iArr) && (aVarE = e(iArr, i7, i4)) != null) {
                return aVarE;
            }
        }
        if (this.f4917b.isEmpty()) {
            throw j.a();
        }
        return (a) this.f4917b.get(0);
    }

    public final boolean d(int[] iArr) {
        float f2 = this.f4922g;
        float f3 = f2 / 2.0f;
        for (int i2 = 0; i2 < 3; i2++) {
            if (Math.abs(f2 - iArr[i2]) >= f3) {
                return false;
            }
        }
        return true;
    }

    public final a e(int[] iArr, int i2, int i3) {
        int i4 = iArr[0] + iArr[1] + iArr[2];
        float fA = a(iArr, i3);
        float fB = b(i2, (int) fA, iArr[1] * 2, i4);
        if (Float.isNaN(fB)) {
            return null;
        }
        float f2 = ((iArr[0] + iArr[1]) + iArr[2]) / 3.0f;
        for (a aVar : this.f4917b) {
            if (aVar.f(f2, fB, fA)) {
                return aVar.g(fB, fA, f2);
            }
        }
        this.f4917b.add(new a(fA, fB, f2));
        return null;
    }
}

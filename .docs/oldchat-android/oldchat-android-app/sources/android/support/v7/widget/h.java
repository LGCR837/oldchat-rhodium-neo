package android.support.v7.widget;

import android.support.v7.widget.b;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f1706a;

    public interface a {
        void a(b.C0015b c0015b);

        b.C0015b b(int i2, int i3, int i4, Object obj);
    }

    public h(a aVar) {
        this.f1706a = aVar;
    }

    public final int a(List list) {
        boolean z2 = false;
        for (int size = list.size() - 1; size >= 0; size--) {
            if (((b.C0015b) list.get(size)).f1625a != 8) {
                z2 = true;
            } else if (z2) {
                return size;
            }
        }
        return -1;
    }

    public void b(List list) {
        while (true) {
            int iA = a(list);
            if (iA == -1) {
                return;
            } else {
                d(list, iA, iA + 1);
            }
        }
    }

    public final void c(List list, int i2, b.C0015b c0015b, int i3, b.C0015b c0015b2) {
        int i4 = c0015b.f1628d;
        int i5 = c0015b2.f1626b;
        int i6 = i4 < i5 ? -1 : 0;
        int i7 = c0015b.f1626b;
        if (i7 < i5) {
            i6++;
        }
        if (i5 <= i7) {
            c0015b.f1626b = i7 + c0015b2.f1628d;
        }
        int i8 = c0015b2.f1626b;
        if (i8 <= i4) {
            c0015b.f1628d = i4 + c0015b2.f1628d;
        }
        c0015b2.f1626b = i8 + i6;
        list.set(i2, c0015b2);
        list.set(i3, c0015b);
    }

    public final void d(List list, int i2, int i3) {
        b.C0015b c0015b = (b.C0015b) list.get(i2);
        b.C0015b c0015b2 = (b.C0015b) list.get(i3);
        int i4 = c0015b2.f1625a;
        if (i4 == 1) {
            c(list, i2, c0015b, i3, c0015b2);
        } else if (i4 == 2) {
            e(list, i2, c0015b, i3, c0015b2);
        } else {
            if (i4 != 4) {
                return;
            }
            f(list, i2, c0015b, i3, c0015b2);
        }
    }

    public void e(List list, int i2, b.C0015b c0015b, int i3, b.C0015b c0015b2) {
        boolean z2;
        int i4 = c0015b.f1626b;
        int i5 = c0015b.f1628d;
        boolean z3 = false;
        if (i4 < i5) {
            if (c0015b2.f1626b == i4 && c0015b2.f1628d == i5 - i4) {
                z2 = false;
                z3 = true;
            } else {
                z2 = false;
            }
        } else if (c0015b2.f1626b == i5 + 1 && c0015b2.f1628d == i4 - i5) {
            z2 = true;
            z3 = true;
        } else {
            z2 = true;
        }
        int i6 = c0015b2.f1626b;
        if (i5 < i6) {
            c0015b2.f1626b = i6 - 1;
        } else {
            int i7 = c0015b2.f1628d;
            if (i5 < i6 + i7) {
                c0015b2.f1628d = i7 - 1;
                c0015b.f1625a = 2;
                c0015b.f1628d = 1;
                if (c0015b2.f1628d == 0) {
                    list.remove(i3);
                    this.f1706a.a(c0015b2);
                    return;
                }
                return;
            }
        }
        int i8 = c0015b.f1626b;
        int i9 = c0015b2.f1626b;
        b.C0015b c0015bB = null;
        if (i8 <= i9) {
            c0015b2.f1626b = i9 + 1;
        } else {
            int i10 = c0015b2.f1628d;
            if (i8 < i9 + i10) {
                c0015bB = this.f1706a.b(2, i8 + 1, (i9 + i10) - i8, null);
                c0015b2.f1628d = c0015b.f1626b - c0015b2.f1626b;
            }
        }
        if (z3) {
            list.set(i2, c0015b2);
            list.remove(i3);
            this.f1706a.a(c0015b);
            return;
        }
        if (z2) {
            if (c0015bB != null) {
                int i11 = c0015b.f1626b;
                if (i11 > c0015bB.f1626b) {
                    c0015b.f1626b = i11 - c0015bB.f1628d;
                }
                int i12 = c0015b.f1628d;
                if (i12 > c0015bB.f1626b) {
                    c0015b.f1628d = i12 - c0015bB.f1628d;
                }
            }
            int i13 = c0015b.f1626b;
            if (i13 > c0015b2.f1626b) {
                c0015b.f1626b = i13 - c0015b2.f1628d;
            }
            int i14 = c0015b.f1628d;
            if (i14 > c0015b2.f1626b) {
                c0015b.f1628d = i14 - c0015b2.f1628d;
            }
        } else {
            if (c0015bB != null) {
                int i15 = c0015b.f1626b;
                if (i15 >= c0015bB.f1626b) {
                    c0015b.f1626b = i15 - c0015bB.f1628d;
                }
                int i16 = c0015b.f1628d;
                if (i16 >= c0015bB.f1626b) {
                    c0015b.f1628d = i16 - c0015bB.f1628d;
                }
            }
            int i17 = c0015b.f1626b;
            if (i17 >= c0015b2.f1626b) {
                c0015b.f1626b = i17 - c0015b2.f1628d;
            }
            int i18 = c0015b.f1628d;
            if (i18 >= c0015b2.f1626b) {
                c0015b.f1628d = i18 - c0015b2.f1628d;
            }
        }
        list.set(i2, c0015b2);
        if (c0015b.f1626b != c0015b.f1628d) {
            list.set(i3, c0015b);
        } else {
            list.remove(i3);
        }
        if (c0015bB != null) {
            list.add(i2, c0015bB);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void f(java.util.List r9, int r10, android.support.v7.widget.b.C0015b r11, int r12, android.support.v7.widget.b.C0015b r13) {
        /*
            r8 = this;
            int r0 = r11.f1628d
            int r1 = r13.f1626b
            r2 = 4
            r3 = 1
            r4 = 0
            if (r0 >= r1) goto Ld
            int r1 = r1 - r3
            r13.f1626b = r1
            goto L20
        Ld:
            int r5 = r13.f1628d
            int r1 = r1 + r5
            if (r0 >= r1) goto L20
            int r5 = r5 - r3
            r13.f1628d = r5
            android.support.v7.widget.h$a r0 = r8.f1706a
            int r1 = r11.f1626b
            java.lang.Object r5 = r13.f1627c
            android.support.v7.widget.b$b r0 = r0.b(r2, r1, r3, r5)
            goto L21
        L20:
            r0 = r4
        L21:
            int r1 = r11.f1626b
            int r5 = r13.f1626b
            if (r1 > r5) goto L2b
            int r5 = r5 + r3
            r13.f1626b = r5
            goto L41
        L2b:
            int r6 = r13.f1628d
            int r7 = r5 + r6
            if (r1 >= r7) goto L41
            int r5 = r5 + r6
            int r5 = r5 - r1
            android.support.v7.widget.h$a r4 = r8.f1706a
            int r1 = r1 + r3
            java.lang.Object r3 = r13.f1627c
            android.support.v7.widget.b$b r4 = r4.b(r2, r1, r5, r3)
            int r1 = r13.f1628d
            int r1 = r1 - r5
            r13.f1628d = r1
        L41:
            r9.set(r12, r11)
            int r11 = r13.f1628d
            if (r11 <= 0) goto L4c
            r9.set(r10, r13)
            goto L54
        L4c:
            r9.remove(r10)
            android.support.v7.widget.h$a r11 = r8.f1706a
            r11.a(r13)
        L54:
            if (r0 == 0) goto L59
            r9.add(r10, r0)
        L59:
            if (r4 == 0) goto L5e
            r9.add(r10, r4)
        L5e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.h.f(java.util.List, int, android.support.v7.widget.b$b, int, android.support.v7.widget.b$b):void");
    }
}

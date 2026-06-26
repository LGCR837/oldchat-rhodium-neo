package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.Arrays;
import l.C0347e;

/* JADX INFO: loaded from: classes.dex */
public class GridLayoutManager extends LinearLayoutManager {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int[] f1232A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public View[] f1233B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final SparseIntArray f1234C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final SparseIntArray f1235D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public c f1236E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final Rect f1237F;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f1238y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f1239z;

    public static final class a extends c {
        @Override // android.support.v7.widget.GridLayoutManager.c
        public int c(int i2, int i3) {
            return i2 % i3;
        }

        @Override // android.support.v7.widget.GridLayoutManager.c
        public int d(int i2) {
            return 1;
        }
    }

    public static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final SparseIntArray f1242a = new SparseIntArray();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1243b = false;

        public int a(int i2, int i3) {
            if (!this.f1243b) {
                return c(i2, i3);
            }
            int i4 = this.f1242a.get(i2, -1);
            if (i4 != -1) {
                return i4;
            }
            int iC = c(i2, i3);
            this.f1242a.put(i2, iC);
            return iC;
        }

        public int b(int i2, int i3) {
            int iD = d(i2);
            int i4 = 0;
            int i5 = 0;
            for (int i6 = 0; i6 < i2; i6++) {
                int iD2 = d(i6);
                i4 += iD2;
                if (i4 == i3) {
                    i5++;
                    i4 = 0;
                } else if (i4 > i3) {
                    i5++;
                    i4 = iD2;
                }
            }
            return i4 + iD > i3 ? i5 + 1 : i5;
        }

        public abstract int c(int i2, int i3);

        public abstract int d(int i2);

        public void e() {
            this.f1242a.clear();
        }
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.f1238y = false;
        this.f1239z = -1;
        this.f1234C = new SparseIntArray();
        this.f1235D = new SparseIntArray();
        this.f1236E = new a();
        this.f1237F = new Rect();
        K2(RecyclerView.n.f0(context, attributeSet, i2, i3).f1399b);
    }

    public static int[] z2(int[] iArr, int i2, int i3) {
        int i4;
        if (iArr == null || iArr.length != i2 + 1 || iArr[iArr.length - 1] != i3) {
            iArr = new int[i2 + 1];
        }
        int i5 = 0;
        iArr[0] = 0;
        int i6 = i3 / i2;
        int i7 = i3 % i2;
        int i8 = 0;
        for (int i9 = 1; i9 <= i2; i9++) {
            i5 += i7;
            if (i5 <= 0 || i2 - i5 >= i7) {
                i4 = i6;
            } else {
                i4 = i6 + 1;
                i5 -= i2;
            }
            i8 += i4;
            iArr[i9] = i8;
        }
        return iArr;
    }

    public final void A2() {
        this.f1234C.clear();
        this.f1235D.clear();
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.n
    public RecyclerView.o B() {
        return this.f1244k == 0 ? new b(-2, -1) : new b(-1, -2);
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.n
    public boolean B1() {
        return this.f1255v == null && !this.f1238y;
    }

    public final void B2(RecyclerView.u uVar, RecyclerView.y yVar, LinearLayoutManager.a aVar, int i2) {
        boolean z2 = i2 == 1;
        int iF2 = F2(uVar, yVar, aVar.f1258a);
        if (z2) {
            while (iF2 > 0) {
                int i3 = aVar.f1258a;
                if (i3 <= 0) {
                    return;
                }
                int i4 = i3 - 1;
                aVar.f1258a = i4;
                iF2 = F2(uVar, yVar, i4);
            }
            return;
        }
        int iB = yVar.b() - 1;
        int i5 = aVar.f1258a;
        while (i5 < iB) {
            int i6 = i5 + 1;
            int iF22 = F2(uVar, yVar, i6);
            if (iF22 <= iF2) {
                break;
            }
            i5 = i6;
            iF2 = iF22;
        }
        aVar.f1258a = i5;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public RecyclerView.o C(Context context, AttributeSet attributeSet) {
        return new b(context, attributeSet);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0083  */
    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.n
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View C0(android.view.View r19, int r20, android.support.v7.widget.RecyclerView.u r21, android.support.v7.widget.RecyclerView.y r22) {
        /*
            r18 = this;
            r0 = r18
            android.view.View r1 = r18.z(r19)
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            android.view.ViewGroup$LayoutParams r3 = r1.getLayoutParams()
            android.support.v7.widget.GridLayoutManager$b r3 = (android.support.v7.widget.GridLayoutManager.b) r3
            int r4 = r3.f1240e
            int r3 = r3.f1241f
            int r3 = r3 + r4
            android.view.View r5 = super.C0(r19, r20, r21, r22)
            if (r5 != 0) goto L1c
            return r2
        L1c:
            r5 = r20
            int r5 = r0.F1(r5)
            r7 = 1
            if (r5 != r7) goto L27
            r5 = 1
            goto L28
        L27:
            r5 = 0
        L28:
            boolean r8 = r0.f1249p
            r9 = -1
            if (r5 == r8) goto L35
            int r5 = r18.H()
            int r5 = r5 - r7
            r8 = -1
            r10 = -1
            goto L3c
        L35:
            int r5 = r18.H()
            r8 = r5
            r5 = 0
            r10 = 1
        L3c:
            int r11 = r0.f1244k
            if (r11 != r7) goto L48
            boolean r11 = r18.Z1()
            if (r11 == 0) goto L48
            r11 = 1
            goto L49
        L48:
            r11 = 0
        L49:
            r12 = 0
        L4a:
            if (r5 == r8) goto L94
            android.view.View r13 = r0.G(r5)
            if (r13 != r1) goto L53
            goto L94
        L53:
            boolean r14 = r13.isFocusable()
            if (r14 != 0) goto L5a
            goto L91
        L5a:
            android.view.ViewGroup$LayoutParams r14 = r13.getLayoutParams()
            android.support.v7.widget.GridLayoutManager$b r14 = (android.support.v7.widget.GridLayoutManager.b) r14
            int r15 = r14.f1240e
            int r6 = r14.f1241f
            int r6 = r6 + r15
            if (r15 != r4) goto L6a
            if (r6 != r3) goto L6a
            return r13
        L6a:
            if (r2 != 0) goto L6d
            goto L83
        L6d:
            int r16 = java.lang.Math.max(r15, r4)
            int r17 = java.lang.Math.min(r6, r3)
            int r7 = r17 - r16
            if (r7 <= r12) goto L7a
            goto L83
        L7a:
            if (r7 != r12) goto L91
            if (r15 <= r9) goto L80
            r7 = 1
            goto L81
        L80:
            r7 = 0
        L81:
            if (r11 != r7) goto L91
        L83:
            int r2 = r14.f1240e
            int r6 = java.lang.Math.min(r6, r3)
            int r7 = java.lang.Math.max(r15, r4)
            int r12 = r6 - r7
            r9 = r2
            r2 = r13
        L91:
            int r5 = r5 + r10
            r7 = 1
            goto L4a
        L94:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.GridLayoutManager.C0(android.view.View, int, android.support.v7.widget.RecyclerView$u, android.support.v7.widget.RecyclerView$y):android.view.View");
    }

    public final void C2() {
        View[] viewArr = this.f1233B;
        if (viewArr == null || viewArr.length != this.f1239z) {
            this.f1233B = new View[this.f1239z];
        }
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public RecyclerView.o D(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new b((ViewGroup.MarginLayoutParams) layoutParams) : new b(layoutParams);
    }

    public int D2(int i2, int i3) {
        if (this.f1244k != 1 || !Z1()) {
            int[] iArr = this.f1232A;
            return iArr[i3 + i2] - iArr[i2];
        }
        int[] iArr2 = this.f1232A;
        int i4 = this.f1239z;
        return iArr2[i4 - i2] - iArr2[(i4 - i2) - i3];
    }

    public final int E2(RecyclerView.u uVar, RecyclerView.y yVar, int i2) {
        if (!yVar.e()) {
            return this.f1236E.b(i2, this.f1239z);
        }
        int iF = uVar.f(i2);
        if (iF != -1) {
            return this.f1236E.b(iF, this.f1239z);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i2);
        return 0;
    }

    public final int F2(RecyclerView.u uVar, RecyclerView.y yVar, int i2) {
        if (!yVar.e()) {
            return this.f1236E.a(i2, this.f1239z);
        }
        int i3 = this.f1235D.get(i2, -1);
        if (i3 != -1) {
            return i3;
        }
        int iF = uVar.f(i2);
        if (iF != -1) {
            return this.f1236E.a(iF, this.f1239z);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i2);
        return 0;
    }

    public final int G2(RecyclerView.u uVar, RecyclerView.y yVar, int i2) {
        if (!yVar.e()) {
            return this.f1236E.d(i2);
        }
        int i3 = this.f1234C.get(i2, -1);
        if (i3 != -1) {
            return i3;
        }
        int iF = uVar.f(i2);
        if (iF != -1) {
            return this.f1236E.d(iF);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i2);
        return 1;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void H0(RecyclerView.u uVar, RecyclerView.y yVar, View view, C0347e c0347e) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof b)) {
            super.I0(view, c0347e);
            return;
        }
        b bVar = (b) layoutParams;
        int iE2 = E2(uVar, yVar, bVar.a());
        if (this.f1244k == 0) {
            c0347e.x(C0347e.m.a(bVar.e(), bVar.f(), iE2, 1, this.f1239z > 1 && bVar.f() == this.f1239z, false));
        } else {
            c0347e.x(C0347e.m.a(iE2, 1, bVar.e(), bVar.f(), this.f1239z > 1 && bVar.f() == this.f1239z, false));
        }
    }

    public final void H2(float f2, int i2) {
        y2(Math.max(Math.round(f2 * this.f1239z), i2));
    }

    public final void I2(View view, int i2, boolean z2) {
        int I2;
        int I3;
        b bVar = (b) view.getLayoutParams();
        Rect rect = bVar.f1403b;
        int i3 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) bVar).topMargin + ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
        int i4 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) bVar).leftMargin + ((ViewGroup.MarginLayoutParams) bVar).rightMargin;
        int iD2 = D2(bVar.f1240e, bVar.f1241f);
        if (this.f1244k == 1) {
            I3 = RecyclerView.n.I(iD2, i2, i4, ((ViewGroup.MarginLayoutParams) bVar).width, false);
            I2 = RecyclerView.n.I(this.f1246m.n(), U(), i3, ((ViewGroup.MarginLayoutParams) bVar).height, true);
        } else {
            int I4 = RecyclerView.n.I(iD2, i2, i3, ((ViewGroup.MarginLayoutParams) bVar).height, false);
            int I5 = RecyclerView.n.I(this.f1246m.n(), m0(), i4, ((ViewGroup.MarginLayoutParams) bVar).width, true);
            I2 = I4;
            I3 = I5;
        }
        J2(view, I3, I2, z2);
    }

    public final void J2(View view, int i2, int i3, boolean z2) {
        RecyclerView.o oVar = (RecyclerView.o) view.getLayoutParams();
        if (z2 ? z1(view, i2, i3, oVar) : x1(view, i2, i3, oVar)) {
            view.measure(i2, i3);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public int K(RecyclerView.u uVar, RecyclerView.y yVar) {
        if (this.f1244k == 1) {
            return this.f1239z;
        }
        if (yVar.b() < 1) {
            return 0;
        }
        return E2(uVar, yVar, yVar.b() - 1) + 1;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void K0(RecyclerView recyclerView, int i2, int i3) {
        this.f1236E.e();
    }

    public void K2(int i2) {
        if (i2 == this.f1239z) {
            return;
        }
        this.f1238y = true;
        if (i2 >= 1) {
            this.f1239z = i2;
            this.f1236E.e();
            k1();
        } else {
            throw new IllegalArgumentException("Span count should be at least 1. Provided " + i2);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void L0(RecyclerView recyclerView) {
        this.f1236E.e();
    }

    public final void L2() {
        int iT;
        int iD0;
        if (Y1() == 1) {
            iT = l0() - c0();
            iD0 = b0();
        } else {
            iT = T() - a0();
            iD0 = d0();
        }
        y2(iT - iD0);
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void M0(RecyclerView recyclerView, int i2, int i3, int i4) {
        this.f1236E.e();
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void N0(RecyclerView recyclerView, int i2, int i3) {
        this.f1236E.e();
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void P0(RecyclerView recyclerView, int i2, int i3, Object obj) {
        this.f1236E.e();
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.n
    public void Q0(RecyclerView.u uVar, RecyclerView.y yVar) {
        if (yVar.e()) {
            x2();
        }
        super.Q0(uVar, yVar);
        A2();
    }

    @Override // android.support.v7.widget.LinearLayoutManager
    public View Q1(RecyclerView.u uVar, RecyclerView.y yVar, int i2, int i3, int i4) {
        H1();
        int iM = this.f1246m.m();
        int i5 = this.f1246m.i();
        int i6 = i3 > i2 ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i2 != i3) {
            View viewG = G(i2);
            int iE0 = e0(viewG);
            if (iE0 >= 0 && iE0 < i4 && F2(uVar, yVar, iE0) == 0) {
                if (((RecyclerView.o) viewG.getLayoutParams()).c()) {
                    if (view2 == null) {
                        view2 = viewG;
                    }
                } else {
                    if (this.f1246m.g(viewG) < i5 && this.f1246m.d(viewG) >= iM) {
                        return viewG;
                    }
                    if (view == null) {
                        view = viewG;
                    }
                }
            }
            i2 += i6;
        }
        return view != null ? view : view2;
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.n
    public void R0(RecyclerView.y yVar) {
        super.R0(yVar);
        this.f1238y = false;
    }

    @Override // android.support.v7.widget.LinearLayoutManager
    public void a2(RecyclerView.u uVar, RecyclerView.y yVar, LinearLayoutManager.c cVar, LinearLayoutManager.b bVar) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int iF;
        int iF2;
        int iF3;
        int I2;
        int I3;
        boolean z2;
        View viewD;
        int iL = this.f1246m.l();
        boolean z3 = iL != 1073741824;
        int i7 = H() > 0 ? this.f1232A[this.f1239z] : 0;
        if (z3) {
            L2();
        }
        boolean z4 = cVar.f1271e == 1;
        int iF22 = this.f1239z;
        if (!z4) {
            iF22 = F2(uVar, yVar, cVar.f1270d) + G2(uVar, yVar, cVar.f1270d);
        }
        int i8 = 0;
        int i9 = 0;
        while (i9 < this.f1239z && cVar.c(yVar) && iF22 > 0) {
            int i10 = cVar.f1270d;
            int iG2 = G2(uVar, yVar, i10);
            if (iG2 > this.f1239z) {
                throw new IllegalArgumentException("Item at position " + i10 + " requires " + iG2 + " spans but GridLayoutManager has only " + this.f1239z + " spans.");
            }
            iF22 -= iG2;
            if (iF22 < 0 || (viewD = cVar.d(uVar)) == null) {
                break;
            }
            i8 += iG2;
            this.f1233B[i9] = viewD;
            i9++;
        }
        if (i9 == 0) {
            bVar.f1264b = true;
            return;
        }
        int i11 = i9;
        w2(uVar, yVar, i9, i8, z4);
        float f2 = 0.0f;
        int i12 = 0;
        for (int i13 = 0; i13 < i11; i13++) {
            View view = this.f1233B[i13];
            if (cVar.f1277k != null) {
                z2 = false;
                if (z4) {
                    b(view);
                } else {
                    c(view, 0);
                }
            } else if (z4) {
                d(view);
                z2 = false;
            } else {
                z2 = false;
                e(view, 0);
            }
            j(view, this.f1237F);
            I2(view, iL, z2);
            int iE = this.f1246m.e(view);
            if (iE > i12) {
                i12 = iE;
            }
            float f3 = (this.f1246m.f(view) * 1.0f) / ((b) view.getLayoutParams()).f1241f;
            if (f3 > f2) {
                f2 = f3;
            }
        }
        if (z3) {
            H2(f2, i7);
            i12 = 0;
            for (int i14 = 0; i14 < i11; i14++) {
                View view2 = this.f1233B[i14];
                I2(view2, 1073741824, true);
                int iE2 = this.f1246m.e(view2);
                if (iE2 > i12) {
                    i12 = iE2;
                }
            }
        }
        for (int i15 = 0; i15 < i11; i15++) {
            View view3 = this.f1233B[i15];
            if (this.f1246m.e(view3) != i12) {
                b bVar2 = (b) view3.getLayoutParams();
                Rect rect = bVar2.f1403b;
                int i16 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) bVar2).topMargin + ((ViewGroup.MarginLayoutParams) bVar2).bottomMargin;
                int i17 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) bVar2).leftMargin + ((ViewGroup.MarginLayoutParams) bVar2).rightMargin;
                int iD2 = D2(bVar2.f1240e, bVar2.f1241f);
                if (this.f1244k == 1) {
                    I3 = RecyclerView.n.I(iD2, 1073741824, i17, ((ViewGroup.MarginLayoutParams) bVar2).width, false);
                    I2 = View.MeasureSpec.makeMeasureSpec(i12 - i16, 1073741824);
                } else {
                    int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i12 - i17, 1073741824);
                    I2 = RecyclerView.n.I(iD2, 1073741824, i16, ((ViewGroup.MarginLayoutParams) bVar2).height, false);
                    I3 = iMakeMeasureSpec;
                }
                J2(view3, I3, I2, true);
            }
        }
        int i18 = 0;
        bVar.f1263a = i12;
        if (this.f1244k != 1) {
            if (cVar.f1272f == -1) {
                int i19 = cVar.f1268b;
                i3 = i19 - i12;
                i2 = i19;
            } else {
                int i20 = cVar.f1268b;
                i2 = i20 + i12;
                i3 = i20;
            }
            i4 = 0;
            i5 = 0;
        } else if (cVar.f1272f == -1) {
            int i21 = cVar.f1268b;
            int i22 = i21 - i12;
            i3 = 0;
            i2 = 0;
            i5 = i22;
            i4 = i21;
        } else {
            i5 = cVar.f1268b;
            i4 = i5 + i12;
            i3 = 0;
            i2 = 0;
        }
        while (i18 < i11) {
            View view4 = this.f1233B[i18];
            b bVar3 = (b) view4.getLayoutParams();
            if (this.f1244k == 1) {
                if (Z1()) {
                    int iB0 = b0() + this.f1232A[this.f1239z - bVar3.f1240e];
                    iF3 = i4;
                    iF2 = iB0;
                    iF = iB0 - this.f1246m.f(view4);
                } else {
                    int iB02 = b0() + this.f1232A[bVar3.f1240e];
                    iF3 = i4;
                    iF = iB02;
                    iF2 = this.f1246m.f(view4) + iB02;
                }
                i6 = i5;
            } else {
                int iD0 = d0() + this.f1232A[bVar3.f1240e];
                i6 = iD0;
                iF = i3;
                iF2 = i2;
                iF3 = this.f1246m.f(view4) + iD0;
            }
            s0(view4, iF, i6, iF2, iF3);
            if (bVar3.c() || bVar3.b()) {
                bVar.f1265c = true;
            }
            bVar.f1266d |= view4.isFocusable();
            i18++;
            i4 = iF3;
            i3 = iF;
            i2 = iF2;
            i5 = i6;
        }
        Arrays.fill(this.f1233B, (Object) null);
    }

    @Override // android.support.v7.widget.LinearLayoutManager
    public void c2(RecyclerView.u uVar, RecyclerView.y yVar, LinearLayoutManager.a aVar, int i2) {
        super.c2(uVar, yVar, aVar, i2);
        L2();
        if (yVar.b() > 0 && !yVar.e()) {
            B2(uVar, yVar, aVar, i2);
        }
        C2();
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public int h0(RecyclerView.u uVar, RecyclerView.y yVar) {
        if (this.f1244k == 0) {
            return this.f1239z;
        }
        if (yVar.b() < 1) {
            return 0;
        }
        return E2(uVar, yVar, yVar.b() - 1) + 1;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public boolean m(RecyclerView.o oVar) {
        return oVar instanceof b;
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.n
    public int n1(int i2, RecyclerView.u uVar, RecyclerView.y yVar) {
        L2();
        C2();
        return super.n1(i2, uVar, yVar);
    }

    @Override // android.support.v7.widget.LinearLayoutManager
    public void n2(boolean z2) {
        if (z2) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.n2(false);
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.n
    public int p1(int i2, RecyclerView.u uVar, RecyclerView.y yVar) {
        L2();
        C2();
        return super.p1(i2, uVar, yVar);
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void u1(Rect rect, int i2, int i3) {
        int iN;
        int iN2;
        if (this.f1232A == null) {
            super.u1(rect, i2, i3);
        }
        int iB0 = b0() + c0();
        int iD0 = d0() + a0();
        if (this.f1244k == 1) {
            iN2 = RecyclerView.n.n(i3, rect.height() + iD0, Y());
            int[] iArr = this.f1232A;
            iN = RecyclerView.n.n(i2, iArr[iArr.length - 1] + iB0, Z());
        } else {
            iN = RecyclerView.n.n(i2, rect.width() + iB0, Z());
            int[] iArr2 = this.f1232A;
            iN2 = RecyclerView.n.n(i3, iArr2[iArr2.length - 1] + iD0, Y());
        }
        t1(iN, iN2);
    }

    public final void w2(RecyclerView.u uVar, RecyclerView.y yVar, int i2, int i3, boolean z2) {
        int i4;
        int i5;
        int i6;
        int i7 = 0;
        if (z2) {
            i5 = i2;
            i4 = 0;
            i6 = 1;
        } else {
            i4 = i2 - 1;
            i5 = -1;
            i6 = -1;
        }
        while (i4 != i5) {
            View view = this.f1233B[i4];
            b bVar = (b) view.getLayoutParams();
            int iG2 = G2(uVar, yVar, e0(view));
            bVar.f1241f = iG2;
            bVar.f1240e = i7;
            i7 += iG2;
            i4 += i6;
        }
    }

    public final void x2() {
        int iH = H();
        for (int i2 = 0; i2 < iH; i2++) {
            b bVar = (b) G(i2).getLayoutParams();
            int iA = bVar.a();
            this.f1234C.put(iA, bVar.f());
            this.f1235D.put(iA, bVar.e());
        }
    }

    public final void y2(int i2) {
        this.f1232A = z2(this.f1232A, this.f1239z, i2);
    }

    public static class b extends RecyclerView.o {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1240e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1241f;

        public b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1240e = -1;
            this.f1241f = 0;
        }

        public int e() {
            return this.f1240e;
        }

        public int f() {
            return this.f1241f;
        }

        public b(int i2, int i3) {
            super(i2, i3);
            this.f1240e = -1;
            this.f1241f = 0;
        }

        public b(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1240e = -1;
            this.f1241f = 0;
        }

        public b(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1240e = -1;
            this.f1241f = 0;
        }
    }

    public GridLayoutManager(Context context, int i2) {
        super(context);
        this.f1238y = false;
        this.f1239z = -1;
        this.f1234C = new SparseIntArray();
        this.f1235D = new SparseIntArray();
        this.f1236E = new a();
        this.f1237F = new Rect();
        K2(i2);
    }

    public GridLayoutManager(Context context, int i2, int i3, boolean z2) {
        super(context, i3, z2);
        this.f1238y = false;
        this.f1239z = -1;
        this.f1234C = new SparseIntArray();
        this.f1235D = new SparseIntArray();
        this.f1236E = new a();
        this.f1237F = new Rect();
        K2(i2);
    }
}

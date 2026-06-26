package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import l.AbstractC0343a;
import l.C0347e;
import l.N;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: classes.dex */
public class StaggeredGridLayoutManager extends RecyclerView.n {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public e f1434A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public int f1435B;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public f[] f1442l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public i f1443m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public i f1444n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f1445o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f1446p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final android.support.v7.widget.f f1447q;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public BitSet f1450t;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f1455y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f1456z;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1441k = -1;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1448r = false;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f1449s = false;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f1451u = -1;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f1452v = PKIFailureInfo.systemUnavail;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public d f1453w = new d();

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f1454x = 2;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final Rect f1436C = new Rect();

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final b f1437D = new b();

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public boolean f1438E = false;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public boolean f1439F = true;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final Runnable f1440G = new a();

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            StaggeredGridLayoutManager.this.I1();
        }
    }

    public class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1458a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1459b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1460c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1461d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1462e;

        public b() {
            c();
        }

        public void a() {
            this.f1459b = this.f1460c ? StaggeredGridLayoutManager.this.f1443m.i() : StaggeredGridLayoutManager.this.f1443m.m();
        }

        public void b(int i2) {
            if (this.f1460c) {
                this.f1459b = StaggeredGridLayoutManager.this.f1443m.i() - i2;
            } else {
                this.f1459b = StaggeredGridLayoutManager.this.f1443m.m() + i2;
            }
        }

        public void c() {
            this.f1458a = -1;
            this.f1459b = PKIFailureInfo.systemUnavail;
            this.f1460c = false;
            this.f1461d = false;
            this.f1462e = false;
        }
    }

    public static class c extends RecyclerView.o {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public f f1464e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1465f;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public final int e() {
            f fVar = this.f1464e;
            if (fVar == null) {
                return -1;
            }
            return fVar.f1486e;
        }

        public boolean f() {
            return this.f1465f;
        }

        public c(int i2, int i3) {
            super(i2, i3);
        }

        public c(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public static class e implements Parcelable {
        public static final Parcelable.Creator<e> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1472a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1473b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1474c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int[] f1475d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1476e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int[] f1477f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public List f1478g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f1479h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f1480i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public boolean f1481j;

        public static class a implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public e createFromParcel(Parcel parcel) {
                return new e(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public e[] newArray(int i2) {
                return new e[i2];
            }
        }

        public e() {
        }

        public void a() {
            this.f1475d = null;
            this.f1474c = 0;
            this.f1472a = -1;
            this.f1473b = -1;
        }

        public void b() {
            this.f1475d = null;
            this.f1474c = 0;
            this.f1476e = 0;
            this.f1477f = null;
            this.f1478g = null;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f1472a);
            parcel.writeInt(this.f1473b);
            parcel.writeInt(this.f1474c);
            if (this.f1474c > 0) {
                parcel.writeIntArray(this.f1475d);
            }
            parcel.writeInt(this.f1476e);
            if (this.f1476e > 0) {
                parcel.writeIntArray(this.f1477f);
            }
            parcel.writeInt(this.f1479h ? 1 : 0);
            parcel.writeInt(this.f1480i ? 1 : 0);
            parcel.writeInt(this.f1481j ? 1 : 0);
            parcel.writeList(this.f1478g);
        }

        public e(Parcel parcel) {
            this.f1472a = parcel.readInt();
            this.f1473b = parcel.readInt();
            int i2 = parcel.readInt();
            this.f1474c = i2;
            if (i2 > 0) {
                int[] iArr = new int[i2];
                this.f1475d = iArr;
                parcel.readIntArray(iArr);
            }
            int i3 = parcel.readInt();
            this.f1476e = i3;
            if (i3 > 0) {
                int[] iArr2 = new int[i3];
                this.f1477f = iArr2;
                parcel.readIntArray(iArr2);
            }
            this.f1479h = parcel.readInt() == 1;
            this.f1480i = parcel.readInt() == 1;
            this.f1481j = parcel.readInt() == 1;
            this.f1478g = parcel.readArrayList(d.a.class.getClassLoader());
        }

        public e(e eVar) {
            this.f1474c = eVar.f1474c;
            this.f1472a = eVar.f1472a;
            this.f1473b = eVar.f1473b;
            this.f1475d = eVar.f1475d;
            this.f1476e = eVar.f1476e;
            this.f1477f = eVar.f1477f;
            this.f1479h = eVar.f1479h;
            this.f1480i = eVar.f1480i;
            this.f1481j = eVar.f1481j;
            this.f1478g = eVar.f1478g;
        }
    }

    public class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ArrayList f1482a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1483b = PKIFailureInfo.systemUnavail;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1484c = PKIFailureInfo.systemUnavail;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1485d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f1486e;

        public f(int i2) {
            this.f1486e = i2;
        }

        public void a(View view) {
            c cVarJ = j(view);
            cVarJ.f1464e = this;
            this.f1482a.add(view);
            this.f1484c = PKIFailureInfo.systemUnavail;
            if (this.f1482a.size() == 1) {
                this.f1483b = PKIFailureInfo.systemUnavail;
            }
            if (cVarJ.c() || cVarJ.b()) {
                this.f1485d += StaggeredGridLayoutManager.this.f1443m.e(view);
            }
        }

        public void b(boolean z2, int i2) {
            int iH = z2 ? h(PKIFailureInfo.systemUnavail) : l(PKIFailureInfo.systemUnavail);
            e();
            if (iH == Integer.MIN_VALUE) {
                return;
            }
            if (!z2 || iH >= StaggeredGridLayoutManager.this.f1443m.i()) {
                if (z2 || iH <= StaggeredGridLayoutManager.this.f1443m.m()) {
                    if (i2 != Integer.MIN_VALUE) {
                        iH += i2;
                    }
                    this.f1484c = iH;
                    this.f1483b = iH;
                }
            }
        }

        public void c() {
            d.a aVarF;
            ArrayList arrayList = this.f1482a;
            View view = (View) arrayList.get(arrayList.size() - 1);
            c cVarJ = j(view);
            this.f1484c = StaggeredGridLayoutManager.this.f1443m.d(view);
            if (cVarJ.f1465f && (aVarF = StaggeredGridLayoutManager.this.f1453w.f(cVarJ.a())) != null && aVarF.f1469b == 1) {
                this.f1484c += aVarF.a(this.f1486e);
            }
        }

        public void d() {
            d.a aVarF;
            View view = (View) this.f1482a.get(0);
            c cVarJ = j(view);
            this.f1483b = StaggeredGridLayoutManager.this.f1443m.g(view);
            if (cVarJ.f1465f && (aVarF = StaggeredGridLayoutManager.this.f1453w.f(cVarJ.a())) != null && aVarF.f1469b == -1) {
                this.f1483b -= aVarF.a(this.f1486e);
            }
        }

        public void e() {
            this.f1482a.clear();
            m();
            this.f1485d = 0;
        }

        public int f() {
            return this.f1485d;
        }

        public int g() {
            int i2 = this.f1484c;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            c();
            return this.f1484c;
        }

        public int h(int i2) {
            int i3 = this.f1484c;
            if (i3 != Integer.MIN_VALUE) {
                return i3;
            }
            if (this.f1482a.size() == 0) {
                return i2;
            }
            c();
            return this.f1484c;
        }

        public View i(int i2, int i3) {
            View view = null;
            if (i3 != -1) {
                int size = this.f1482a.size() - 1;
                while (size >= 0) {
                    View view2 = (View) this.f1482a.get(size);
                    if (!view2.isFocusable()) {
                        break;
                    }
                    if ((StaggeredGridLayoutManager.this.e0(view2) > i2) != (!StaggeredGridLayoutManager.this.f1448r)) {
                        break;
                    }
                    size--;
                    view = view2;
                }
            } else {
                int size2 = this.f1482a.size();
                int i4 = 0;
                while (i4 < size2) {
                    View view3 = (View) this.f1482a.get(i4);
                    if (!view3.isFocusable()) {
                        break;
                    }
                    if ((StaggeredGridLayoutManager.this.e0(view3) > i2) != StaggeredGridLayoutManager.this.f1448r) {
                        break;
                    }
                    i4++;
                    view = view3;
                }
            }
            return view;
        }

        public c j(View view) {
            return (c) view.getLayoutParams();
        }

        public int k() {
            int i2 = this.f1483b;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            d();
            return this.f1483b;
        }

        public int l(int i2) {
            int i3 = this.f1483b;
            if (i3 != Integer.MIN_VALUE) {
                return i3;
            }
            if (this.f1482a.size() == 0) {
                return i2;
            }
            d();
            return this.f1483b;
        }

        public void m() {
            this.f1483b = PKIFailureInfo.systemUnavail;
            this.f1484c = PKIFailureInfo.systemUnavail;
        }

        public void n(int i2) {
            int i3 = this.f1483b;
            if (i3 != Integer.MIN_VALUE) {
                this.f1483b = i3 + i2;
            }
            int i4 = this.f1484c;
            if (i4 != Integer.MIN_VALUE) {
                this.f1484c = i4 + i2;
            }
        }

        public void o() {
            int size = this.f1482a.size();
            View view = (View) this.f1482a.remove(size - 1);
            c cVarJ = j(view);
            cVarJ.f1464e = null;
            if (cVarJ.c() || cVarJ.b()) {
                this.f1485d -= StaggeredGridLayoutManager.this.f1443m.e(view);
            }
            if (size == 1) {
                this.f1483b = PKIFailureInfo.systemUnavail;
            }
            this.f1484c = PKIFailureInfo.systemUnavail;
        }

        public void p() {
            View view = (View) this.f1482a.remove(0);
            c cVarJ = j(view);
            cVarJ.f1464e = null;
            if (this.f1482a.size() == 0) {
                this.f1484c = PKIFailureInfo.systemUnavail;
            }
            if (cVarJ.c() || cVarJ.b()) {
                this.f1485d -= StaggeredGridLayoutManager.this.f1443m.e(view);
            }
            this.f1483b = PKIFailureInfo.systemUnavail;
        }

        public void q(View view) {
            c cVarJ = j(view);
            cVarJ.f1464e = this;
            this.f1482a.add(0, view);
            this.f1483b = PKIFailureInfo.systemUnavail;
            if (this.f1482a.size() == 1) {
                this.f1484c = PKIFailureInfo.systemUnavail;
            }
            if (cVarJ.c() || cVarJ.b()) {
                this.f1485d += StaggeredGridLayoutManager.this.f1443m.e(view);
            }
        }

        public void r(int i2) {
            this.f1483b = i2;
            this.f1484c = i2;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        RecyclerView.n.a aVarF0 = RecyclerView.n.f0(context, attributeSet, i2, i3);
        w2(aVarF0.f1398a);
        y2(aVarF0.f1399b);
        x2(aVarF0.f1400c);
        q1(this.f1454x != 0);
        this.f1447q = new android.support.v7.widget.f();
        Q1();
    }

    private int K1(RecyclerView.y yVar) {
        if (H() == 0) {
            return 0;
        }
        return k.a(yVar, this.f1443m, U1(!this.f1439F, true), T1(!this.f1439F, true), this, this.f1439F);
    }

    private int L1(RecyclerView.y yVar) {
        if (H() == 0) {
            return 0;
        }
        return k.b(yVar, this.f1443m, U1(!this.f1439F, true), T1(!this.f1439F, true), this, this.f1439F, this.f1449s);
    }

    private int M1(RecyclerView.y yVar) {
        if (H() == 0) {
            return 0;
        }
        return k.c(yVar, this.f1443m, U1(!this.f1439F, true), T1(!this.f1439F, true), this, this.f1439F);
    }

    private int N1(int i2) {
        if (i2 == 1) {
            return (this.f1445o != 1 && j2()) ? 1 : -1;
        }
        if (i2 == 2) {
            return (this.f1445o != 1 && j2()) ? -1 : 1;
        }
        if (i2 == 17) {
            if (this.f1445o == 0) {
                return -1;
            }
            return PKIFailureInfo.systemUnavail;
        }
        if (i2 == 33) {
            if (this.f1445o == 1) {
                return -1;
            }
            return PKIFailureInfo.systemUnavail;
        }
        if (i2 == 66) {
            if (this.f1445o == 0) {
                return 1;
            }
            return PKIFailureInfo.systemUnavail;
        }
        if (i2 == 130 && this.f1445o == 1) {
            return 1;
        }
        return PKIFailureInfo.systemUnavail;
    }

    private void k2(View view, int i2, int i3, boolean z2) {
        j(view, this.f1436C);
        c cVar = (c) view.getLayoutParams();
        int i4 = ((ViewGroup.MarginLayoutParams) cVar).leftMargin;
        Rect rect = this.f1436C;
        int iG2 = G2(i2, i4 + rect.left, ((ViewGroup.MarginLayoutParams) cVar).rightMargin + rect.right);
        int i5 = ((ViewGroup.MarginLayoutParams) cVar).topMargin;
        Rect rect2 = this.f1436C;
        int iG22 = G2(i3, i5 + rect2.top, ((ViewGroup.MarginLayoutParams) cVar).bottomMargin + rect2.bottom);
        if (z2 ? z1(view, iG2, iG22, cVar) : x1(view, iG2, iG22, cVar)) {
            view.measure(iG2, iG22);
        }
    }

    private void t2() {
        if (this.f1445o == 1 || !j2()) {
            this.f1449s = this.f1448r;
        } else {
            this.f1449s = !this.f1448r;
        }
    }

    public final boolean A2(RecyclerView.y yVar, b bVar) {
        bVar.f1458a = this.f1455y ? W1(yVar.b()) : S1(yVar.b());
        bVar.f1459b = PKIFailureInfo.systemUnavail;
        return true;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public RecyclerView.o B() {
        return this.f1445o == 0 ? new c(-2, -1) : new c(-1, -2);
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void B0(RecyclerView recyclerView, RecyclerView.u uVar) {
        g1(this.f1440G);
        for (int i2 = 0; i2 < this.f1441k; i2++) {
            this.f1442l[i2].e();
        }
        recyclerView.requestLayout();
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public boolean B1() {
        return this.f1434A == null;
    }

    public boolean B2(RecyclerView.y yVar, b bVar) {
        int i2;
        if (!yVar.e() && (i2 = this.f1451u) != -1) {
            if (i2 >= 0 && i2 < yVar.b()) {
                e eVar = this.f1434A;
                if (eVar == null || eVar.f1472a == -1 || eVar.f1474c < 1) {
                    View viewA = A(this.f1451u);
                    if (viewA != null) {
                        bVar.f1458a = this.f1449s ? a2() : Z1();
                        if (this.f1452v != Integer.MIN_VALUE) {
                            if (bVar.f1460c) {
                                bVar.f1459b = (this.f1443m.i() - this.f1452v) - this.f1443m.d(viewA);
                            } else {
                                bVar.f1459b = (this.f1443m.m() + this.f1452v) - this.f1443m.g(viewA);
                            }
                            return true;
                        }
                        if (this.f1443m.e(viewA) > this.f1443m.n()) {
                            bVar.f1459b = bVar.f1460c ? this.f1443m.i() : this.f1443m.m();
                            return true;
                        }
                        int iG = this.f1443m.g(viewA) - this.f1443m.m();
                        if (iG < 0) {
                            bVar.f1459b = -iG;
                            return true;
                        }
                        int i3 = this.f1443m.i() - this.f1443m.d(viewA);
                        if (i3 < 0) {
                            bVar.f1459b = i3;
                            return true;
                        }
                        bVar.f1459b = PKIFailureInfo.systemUnavail;
                    } else {
                        int i4 = this.f1451u;
                        bVar.f1458a = i4;
                        int i5 = this.f1452v;
                        if (i5 == Integer.MIN_VALUE) {
                            bVar.f1460c = H1(i4) == 1;
                            bVar.a();
                        } else {
                            bVar.b(i5);
                        }
                        bVar.f1461d = true;
                    }
                } else {
                    bVar.f1459b = PKIFailureInfo.systemUnavail;
                    bVar.f1458a = this.f1451u;
                }
                return true;
            }
            this.f1451u = -1;
            this.f1452v = PKIFailureInfo.systemUnavail;
        }
        return false;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public RecyclerView.o C(Context context, AttributeSet attributeSet) {
        return new c(context, attributeSet);
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public View C0(View view, int i2, RecyclerView.u uVar, RecyclerView.y yVar) {
        View viewZ;
        View viewI;
        if (H() == 0 || (viewZ = z(view)) == null) {
            return null;
        }
        t2();
        int iN1 = N1(i2);
        if (iN1 == Integer.MIN_VALUE) {
            return null;
        }
        c cVar = (c) viewZ.getLayoutParams();
        boolean z2 = cVar.f1465f;
        f fVar = cVar.f1464e;
        int iA2 = iN1 == 1 ? a2() : Z1();
        D2(iA2, yVar);
        v2(iN1);
        android.support.v7.widget.f fVar2 = this.f1447q;
        fVar2.f1682c = fVar2.f1683d + iA2;
        fVar2.f1681b = (int) (this.f1443m.n() * 0.33333334f);
        android.support.v7.widget.f fVar3 = this.f1447q;
        fVar3.f1687h = true;
        fVar3.f1680a = false;
        R1(uVar, fVar3, yVar);
        this.f1455y = this.f1449s;
        if (!z2 && (viewI = fVar.i(iA2, iN1)) != null && viewI != viewZ) {
            return viewI;
        }
        if (n2(iN1)) {
            for (int i3 = this.f1441k - 1; i3 >= 0; i3--) {
                View viewI2 = this.f1442l[i3].i(iA2, iN1);
                if (viewI2 != null && viewI2 != viewZ) {
                    return viewI2;
                }
            }
        } else {
            for (int i4 = 0; i4 < this.f1441k; i4++) {
                View viewI3 = this.f1442l[i4].i(iA2, iN1);
                if (viewI3 != null && viewI3 != viewZ) {
                    return viewI3;
                }
            }
        }
        return null;
    }

    public final void C1(View view) {
        for (int i2 = this.f1441k - 1; i2 >= 0; i2--) {
            this.f1442l[i2].a(view);
        }
    }

    public void C2(RecyclerView.y yVar, b bVar) {
        if (B2(yVar, bVar) || A2(yVar, bVar)) {
            return;
        }
        bVar.a();
        bVar.f1458a = 0;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public RecyclerView.o D(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new c((ViewGroup.MarginLayoutParams) layoutParams) : new c(layoutParams);
    }

    public final void D1(b bVar) {
        e eVar = this.f1434A;
        int i2 = eVar.f1474c;
        if (i2 > 0) {
            if (i2 == this.f1441k) {
                for (int i3 = 0; i3 < this.f1441k; i3++) {
                    this.f1442l[i3].e();
                    e eVar2 = this.f1434A;
                    int i4 = eVar2.f1475d[i3];
                    if (i4 != Integer.MIN_VALUE) {
                        i4 += eVar2.f1480i ? this.f1443m.i() : this.f1443m.m();
                    }
                    this.f1442l[i3].r(i4);
                }
            } else {
                eVar.b();
                e eVar3 = this.f1434A;
                eVar3.f1472a = eVar3.f1473b;
            }
        }
        e eVar4 = this.f1434A;
        this.f1456z = eVar4.f1481j;
        x2(eVar4.f1479h);
        t2();
        e eVar5 = this.f1434A;
        int i5 = eVar5.f1472a;
        if (i5 != -1) {
            this.f1451u = i5;
            bVar.f1460c = eVar5.f1480i;
        } else {
            bVar.f1460c = this.f1449s;
        }
        if (eVar5.f1476e > 1) {
            d dVar = this.f1453w;
            dVar.f1466a = eVar5.f1477f;
            dVar.f1467b = eVar5.f1478g;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void D2(int r5, android.support.v7.widget.RecyclerView.y r6) {
        /*
            r4 = this;
            android.support.v7.widget.f r0 = r4.f1447q
            r1 = 0
            r0.f1681b = r1
            r0.f1682c = r5
            boolean r0 = r4.r0()
            r2 = 1
            if (r0 == 0) goto L2f
            int r6 = r6.c()
            r0 = -1
            if (r6 == r0) goto L2f
            boolean r0 = r4.f1449s
            if (r6 >= r5) goto L1b
            r5 = 1
            goto L1c
        L1b:
            r5 = 0
        L1c:
            if (r0 != r5) goto L26
            android.support.v7.widget.i r5 = r4.f1443m
            int r5 = r5.n()
        L24:
            r6 = 0
            goto L31
        L26:
            android.support.v7.widget.i r5 = r4.f1443m
            int r5 = r5.n()
            r6 = r5
            r5 = 0
            goto L31
        L2f:
            r5 = 0
            goto L24
        L31:
            boolean r0 = r4.J()
            if (r0 == 0) goto L4e
            android.support.v7.widget.f r0 = r4.f1447q
            android.support.v7.widget.i r3 = r4.f1443m
            int r3 = r3.m()
            int r3 = r3 - r6
            r0.f1685f = r3
            android.support.v7.widget.f r6 = r4.f1447q
            android.support.v7.widget.i r0 = r4.f1443m
            int r0 = r0.i()
            int r0 = r0 + r5
            r6.f1686g = r0
            goto L5e
        L4e:
            android.support.v7.widget.f r0 = r4.f1447q
            android.support.v7.widget.i r3 = r4.f1443m
            int r3 = r3.h()
            int r3 = r3 + r5
            r0.f1686g = r3
            android.support.v7.widget.f r5 = r4.f1447q
            int r6 = -r6
            r5.f1685f = r6
        L5e:
            android.support.v7.widget.f r5 = r4.f1447q
            r5.f1687h = r1
            r5.f1680a = r2
            android.support.v7.widget.i r6 = r4.f1443m
            int r6 = r6.k()
            if (r6 != 0) goto L75
            android.support.v7.widget.i r6 = r4.f1443m
            int r6 = r6.h()
            if (r6 != 0) goto L75
            r1 = 1
        L75:
            r5.f1688i = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.StaggeredGridLayoutManager.D2(int, android.support.v7.widget.RecyclerView$y):void");
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void E0(AccessibilityEvent accessibilityEvent) {
        super.E0(accessibilityEvent);
        if (H() > 0) {
            N nA = AbstractC0343a.a(accessibilityEvent);
            View viewU1 = U1(false, true);
            View viewT1 = T1(false, true);
            if (viewU1 == null || viewT1 == null) {
                return;
            }
            int iE0 = e0(viewU1);
            int iE02 = e0(viewT1);
            if (iE0 < iE02) {
                nA.a(iE0);
                nA.h(iE02);
            } else {
                nA.a(iE02);
                nA.h(iE0);
            }
        }
    }

    public boolean E1() {
        int iH = this.f1442l[0].h(PKIFailureInfo.systemUnavail);
        for (int i2 = 1; i2 < this.f1441k; i2++) {
            if (this.f1442l[i2].h(PKIFailureInfo.systemUnavail) != iH) {
                return false;
            }
        }
        return true;
    }

    public void E2(int i2) {
        this.f1446p = i2 / this.f1441k;
        this.f1435B = View.MeasureSpec.makeMeasureSpec(i2, this.f1444n.k());
    }

    public boolean F1() {
        int iL = this.f1442l[0].l(PKIFailureInfo.systemUnavail);
        for (int i2 = 1; i2 < this.f1441k; i2++) {
            if (this.f1442l[i2].l(PKIFailureInfo.systemUnavail) != iL) {
                return false;
            }
        }
        return true;
    }

    public final void F2(f fVar, int i2, int i3) {
        int iF = fVar.f();
        if (i2 == -1) {
            if (fVar.k() + iF <= i3) {
                this.f1450t.set(fVar.f1486e, false);
            }
        } else if (fVar.g() - iF >= i3) {
            this.f1450t.set(fVar.f1486e, false);
        }
    }

    public final void G1(View view, c cVar, android.support.v7.widget.f fVar) {
        if (fVar.f1684e == 1) {
            if (cVar.f1465f) {
                C1(view);
                return;
            } else {
                cVar.f1464e.a(view);
                return;
            }
        }
        if (cVar.f1465f) {
            o2(view);
        } else {
            cVar.f1464e.q(view);
        }
    }

    public final int G2(int i2, int i3, int i4) {
        if (i3 == 0 && i4 == 0) {
            return i2;
        }
        int mode = View.MeasureSpec.getMode(i2);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i2) - i3) - i4), mode) : i2;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void H0(RecyclerView.u uVar, RecyclerView.y yVar, View view, C0347e c0347e) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof c)) {
            super.I0(view, c0347e);
            return;
        }
        c cVar = (c) layoutParams;
        if (this.f1445o == 0) {
            int iE = cVar.e();
            boolean z2 = cVar.f1465f;
            c0347e.x(C0347e.m.a(iE, z2 ? this.f1441k : 1, -1, -1, z2, false));
        } else {
            int iE2 = cVar.e();
            boolean z3 = cVar.f1465f;
            c0347e.x(C0347e.m.a(-1, -1, iE2, z3 ? this.f1441k : 1, z3, false));
        }
    }

    public final int H1(int i2) {
        if (H() == 0) {
            return this.f1449s ? 1 : -1;
        }
        return (i2 < Z1()) != this.f1449s ? -1 : 1;
    }

    public boolean I1() {
        int iZ1;
        int iA2;
        if (H() == 0 || this.f1454x == 0 || !o0()) {
            return false;
        }
        if (this.f1449s) {
            iZ1 = a2();
            iA2 = Z1();
        } else {
            iZ1 = Z1();
            iA2 = a2();
        }
        if (iZ1 == 0 && h2() != null) {
            this.f1453w.b();
            l1();
            k1();
            return true;
        }
        if (!this.f1438E) {
            return false;
        }
        int i2 = this.f1449s ? -1 : 1;
        int i3 = iA2 + 1;
        d.a aVarE = this.f1453w.e(iZ1, i3, i2, true);
        if (aVarE == null) {
            this.f1438E = false;
            this.f1453w.d(i3);
            return false;
        }
        d.a aVarE2 = this.f1453w.e(iZ1, aVarE.f1468a, i2 * (-1), true);
        if (aVarE2 == null) {
            this.f1453w.d(aVarE.f1468a);
        } else {
            this.f1453w.d(aVarE2.f1468a + 1);
        }
        l1();
        k1();
        return true;
    }

    public final boolean J1(f fVar) {
        if (this.f1449s) {
            if (fVar.g() < this.f1443m.i()) {
                ArrayList arrayList = fVar.f1482a;
                return !fVar.j((View) arrayList.get(arrayList.size() - 1)).f1465f;
            }
        } else if (fVar.k() > this.f1443m.m()) {
            return !fVar.j((View) fVar.f1482a.get(0)).f1465f;
        }
        return false;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public int K(RecyclerView.u uVar, RecyclerView.y yVar) {
        return this.f1445o == 1 ? this.f1441k : super.K(uVar, yVar);
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void K0(RecyclerView recyclerView, int i2, int i3) {
        g2(i2, i3, 1);
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void L0(RecyclerView recyclerView) {
        this.f1453w.b();
        k1();
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void M0(RecyclerView recyclerView, int i2, int i3, int i4) {
        g2(i2, i3, 8);
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void N0(RecyclerView recyclerView, int i2, int i3) {
        g2(i2, i3, 2);
    }

    public final d.a O1(int i2) {
        d.a aVar = new d.a();
        aVar.f1470c = new int[this.f1441k];
        for (int i3 = 0; i3 < this.f1441k; i3++) {
            aVar.f1470c[i3] = i2 - this.f1442l[i3].h(i2);
        }
        return aVar;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void P0(RecyclerView recyclerView, int i2, int i3, Object obj) {
        g2(i2, i3, 4);
    }

    public final d.a P1(int i2) {
        d.a aVar = new d.a();
        aVar.f1470c = new int[this.f1441k];
        for (int i3 = 0; i3 < this.f1441k; i3++) {
            aVar.f1470c[i3] = this.f1442l[i3].l(i2) - i2;
        }
        return aVar;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void Q0(RecyclerView.u uVar, RecyclerView.y yVar) {
        m2(uVar, yVar, true);
    }

    public final void Q1() {
        this.f1443m = i.b(this, this.f1445o);
        this.f1444n = i.b(this, 1 - this.f1445o);
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void R0(RecyclerView.y yVar) {
        super.R0(yVar);
        this.f1451u = -1;
        this.f1452v = PKIFailureInfo.systemUnavail;
        this.f1434A = null;
        this.f1437D.c();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v7 */
    public final int R1(RecyclerView.u uVar, android.support.v7.widget.f fVar, RecyclerView.y yVar) {
        int i2;
        f fVarF2;
        int iE;
        int i3;
        int iE2;
        int iE3;
        ?? r9 = 0;
        this.f1450t.set(0, this.f1441k, true);
        if (this.f1447q.f1688i) {
            i2 = fVar.f1684e == 1 ? Integer.MAX_VALUE : PKIFailureInfo.systemUnavail;
        } else {
            i2 = fVar.f1684e == 1 ? fVar.f1686g + fVar.f1681b : fVar.f1685f - fVar.f1681b;
        }
        z2(fVar.f1684e, i2);
        int i4 = this.f1449s ? this.f1443m.i() : this.f1443m.m();
        boolean z2 = false;
        while (fVar.a(yVar) && (this.f1447q.f1688i || !this.f1450t.isEmpty())) {
            View viewB = fVar.b(uVar);
            c cVar = (c) viewB.getLayoutParams();
            int iA = cVar.a();
            int iG = this.f1453w.g(iA);
            boolean z3 = iG == -1;
            if (z3) {
                fVarF2 = cVar.f1465f ? this.f1442l[r9] : f2(fVar);
                this.f1453w.n(iA, fVarF2);
            } else {
                fVarF2 = this.f1442l[iG];
            }
            f fVar2 = fVarF2;
            cVar.f1464e = fVar2;
            if (fVar.f1684e == 1) {
                d(viewB);
            } else {
                e(viewB, r9);
            }
            l2(viewB, cVar, r9);
            if (fVar.f1684e == 1) {
                int iB2 = cVar.f1465f ? b2(i4) : fVar2.h(i4);
                int iE4 = this.f1443m.e(viewB) + iB2;
                if (z3 && cVar.f1465f) {
                    d.a aVarO1 = O1(iB2);
                    aVarO1.f1469b = -1;
                    aVarO1.f1468a = iA;
                    this.f1453w.a(aVarO1);
                }
                i3 = iE4;
                iE = iB2;
            } else {
                int iE22 = cVar.f1465f ? e2(i4) : fVar2.l(i4);
                iE = iE22 - this.f1443m.e(viewB);
                if (z3 && cVar.f1465f) {
                    d.a aVarP1 = P1(iE22);
                    aVarP1.f1469b = 1;
                    aVarP1.f1468a = iA;
                    this.f1453w.a(aVarP1);
                }
                i3 = iE22;
            }
            if (cVar.f1465f && fVar.f1683d == -1) {
                if (z3) {
                    this.f1438E = true;
                } else {
                    if (!(fVar.f1684e == 1 ? E1() : F1())) {
                        d.a aVarF = this.f1453w.f(iA);
                        if (aVarF != null) {
                            aVarF.f1471d = true;
                        }
                        this.f1438E = true;
                    }
                }
            }
            G1(viewB, cVar, fVar);
            if (j2() && this.f1445o == 1) {
                int i5 = cVar.f1465f ? this.f1444n.i() : this.f1444n.i() - (((this.f1441k - 1) - fVar2.f1486e) * this.f1446p);
                iE3 = i5;
                iE2 = i5 - this.f1444n.e(viewB);
            } else {
                int iM = cVar.f1465f ? this.f1444n.m() : (fVar2.f1486e * this.f1446p) + this.f1444n.m();
                iE2 = iM;
                iE3 = this.f1444n.e(viewB) + iM;
            }
            if (this.f1445o == 1) {
                s0(viewB, iE2, iE, iE3, i3);
            } else {
                s0(viewB, iE, iE2, i3, iE3);
            }
            if (cVar.f1465f) {
                z2(this.f1447q.f1684e, i2);
            } else {
                F2(fVar2, this.f1447q.f1684e, i2);
            }
            p2(uVar, this.f1447q);
            if (this.f1447q.f1687h && viewB.isFocusable()) {
                if (cVar.f1465f) {
                    this.f1450t.clear();
                } else {
                    this.f1450t.set(fVar2.f1486e, false);
                }
            }
            z2 = true;
            r9 = 0;
        }
        if (!z2) {
            p2(uVar, this.f1447q);
        }
        int iM2 = this.f1447q.f1684e == -1 ? this.f1443m.m() - e2(this.f1443m.m()) : b2(this.f1443m.i()) - this.f1443m.i();
        if (iM2 > 0) {
            return Math.min(fVar.f1681b, iM2);
        }
        return 0;
    }

    public final int S1(int i2) {
        int iH = H();
        for (int i3 = 0; i3 < iH; i3++) {
            int iE0 = e0(G(i3));
            if (iE0 >= 0 && iE0 < i2) {
                return iE0;
            }
        }
        return 0;
    }

    public View T1(boolean z2, boolean z3) {
        int iM = this.f1443m.m();
        int i2 = this.f1443m.i();
        View view = null;
        for (int iH = H() - 1; iH >= 0; iH--) {
            View viewG = G(iH);
            int iG = this.f1443m.g(viewG);
            int iD = this.f1443m.d(viewG);
            if (iD > iM && iG < i2) {
                if (iD <= i2 || !z2) {
                    return viewG;
                }
                if (z3 && view == null) {
                    view = viewG;
                }
            }
        }
        return view;
    }

    public View U1(boolean z2, boolean z3) {
        int iM = this.f1443m.m();
        int i2 = this.f1443m.i();
        int iH = H();
        View view = null;
        for (int i3 = 0; i3 < iH; i3++) {
            View viewG = G(i3);
            int iG = this.f1443m.g(viewG);
            if (this.f1443m.d(viewG) > iM && iG < i2) {
                if (iG >= iM || !z2) {
                    return viewG;
                }
                if (z3 && view == null) {
                    view = viewG;
                }
            }
        }
        return view;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void V0(Parcelable parcelable) {
        if (parcelable instanceof e) {
            this.f1434A = (e) parcelable;
            k1();
        }
    }

    public int V1() {
        View viewT1 = this.f1449s ? T1(true, true) : U1(true, true);
        if (viewT1 == null) {
            return -1;
        }
        return e0(viewT1);
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public Parcelable W0() {
        int iL;
        int iM;
        int[] iArr;
        if (this.f1434A != null) {
            return new e(this.f1434A);
        }
        e eVar = new e();
        eVar.f1479h = this.f1448r;
        eVar.f1480i = this.f1455y;
        eVar.f1481j = this.f1456z;
        d dVar = this.f1453w;
        if (dVar == null || (iArr = dVar.f1466a) == null) {
            eVar.f1476e = 0;
        } else {
            eVar.f1477f = iArr;
            eVar.f1476e = iArr.length;
            eVar.f1478g = dVar.f1467b;
        }
        if (H() > 0) {
            eVar.f1472a = this.f1455y ? a2() : Z1();
            eVar.f1473b = V1();
            int i2 = this.f1441k;
            eVar.f1474c = i2;
            eVar.f1475d = new int[i2];
            for (int i3 = 0; i3 < this.f1441k; i3++) {
                if (this.f1455y) {
                    iL = this.f1442l[i3].h(PKIFailureInfo.systemUnavail);
                    if (iL != Integer.MIN_VALUE) {
                        iM = this.f1443m.i();
                        iL -= iM;
                    }
                } else {
                    iL = this.f1442l[i3].l(PKIFailureInfo.systemUnavail);
                    if (iL != Integer.MIN_VALUE) {
                        iM = this.f1443m.m();
                        iL -= iM;
                    }
                }
                eVar.f1475d[i3] = iL;
            }
        } else {
            eVar.f1472a = -1;
            eVar.f1473b = -1;
            eVar.f1474c = 0;
        }
        return eVar;
    }

    public final int W1(int i2) {
        for (int iH = H() - 1; iH >= 0; iH--) {
            int iE0 = e0(G(iH));
            if (iE0 >= 0 && iE0 < i2) {
                return iE0;
            }
        }
        return 0;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void X0(int i2) {
        if (i2 == 0) {
            I1();
        }
    }

    public final void X1(RecyclerView.u uVar, RecyclerView.y yVar, boolean z2) {
        int i2;
        int iB2 = b2(PKIFailureInfo.systemUnavail);
        if (iB2 != Integer.MIN_VALUE && (i2 = this.f1443m.i() - iB2) > 0) {
            int i3 = i2 - (-u2(-i2, uVar, yVar));
            if (!z2 || i3 <= 0) {
                return;
            }
            this.f1443m.r(i3);
        }
    }

    public final void Y1(RecyclerView.u uVar, RecyclerView.y yVar, boolean z2) {
        int iM;
        int iE2 = e2(Integer.MAX_VALUE);
        if (iE2 != Integer.MAX_VALUE && (iM = iE2 - this.f1443m.m()) > 0) {
            int iU2 = iM - u2(iM, uVar, yVar);
            if (!z2 || iU2 <= 0) {
                return;
            }
            this.f1443m.r(-iU2);
        }
    }

    public final int Z1() {
        if (H() == 0) {
            return 0;
        }
        return e0(G(0));
    }

    public final int a2() {
        int iH = H();
        if (iH == 0) {
            return 0;
        }
        return e0(G(iH - 1));
    }

    public final int b2(int i2) {
        int iH = this.f1442l[0].h(i2);
        for (int i3 = 1; i3 < this.f1441k; i3++) {
            int iH2 = this.f1442l[i3].h(i2);
            if (iH2 > iH) {
                iH = iH2;
            }
        }
        return iH;
    }

    public final int c2(int i2) {
        int iL = this.f1442l[0].l(i2);
        for (int i3 = 1; i3 < this.f1441k; i3++) {
            int iL2 = this.f1442l[i3].l(i2);
            if (iL2 > iL) {
                iL = iL2;
            }
        }
        return iL;
    }

    public final int d2(int i2) {
        int iH = this.f1442l[0].h(i2);
        for (int i3 = 1; i3 < this.f1441k; i3++) {
            int iH2 = this.f1442l[i3].h(i2);
            if (iH2 < iH) {
                iH = iH2;
            }
        }
        return iH;
    }

    public final int e2(int i2) {
        int iL = this.f1442l[0].l(i2);
        for (int i3 = 1; i3 < this.f1441k; i3++) {
            int iL2 = this.f1442l[i3].l(i2);
            if (iL2 < iL) {
                iL = iL2;
            }
        }
        return iL;
    }

    public final f f2(android.support.v7.widget.f fVar) {
        int i2;
        int i3;
        int i4;
        if (n2(fVar.f1684e)) {
            i3 = this.f1441k - 1;
            i2 = -1;
            i4 = -1;
        } else {
            i2 = this.f1441k;
            i3 = 0;
            i4 = 1;
        }
        f fVar2 = null;
        if (fVar.f1684e == 1) {
            int iM = this.f1443m.m();
            int i5 = Integer.MAX_VALUE;
            while (i3 != i2) {
                f fVar3 = this.f1442l[i3];
                int iH = fVar3.h(iM);
                if (iH < i5) {
                    fVar2 = fVar3;
                    i5 = iH;
                }
                i3 += i4;
            }
            return fVar2;
        }
        int i6 = this.f1443m.i();
        int i7 = PKIFailureInfo.systemUnavail;
        while (i3 != i2) {
            f fVar4 = this.f1442l[i3];
            int iL = fVar4.l(i6);
            if (iL > i7) {
                fVar2 = fVar4;
                i7 = iL;
            }
            i3 += i4;
        }
        return fVar2;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void g(String str) {
        if (this.f1434A == null) {
            super.g(str);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0044 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g2(int r7, int r8, int r9) {
        /*
            r6 = this;
            boolean r0 = r6.f1449s
            if (r0 == 0) goto L9
            int r0 = r6.a2()
            goto Ld
        L9:
            int r0 = r6.Z1()
        Ld:
            r1 = 8
            if (r9 != r1) goto L1b
            if (r7 >= r8) goto L17
            int r2 = r8 + 1
        L15:
            r3 = r7
            goto L1e
        L17:
            int r2 = r7 + 1
            r3 = r8
            goto L1e
        L1b:
            int r2 = r7 + r8
            goto L15
        L1e:
            android.support.v7.widget.StaggeredGridLayoutManager$d r4 = r6.f1453w
            r4.h(r3)
            r4 = 1
            if (r9 == r4) goto L3d
            r5 = 2
            if (r9 == r5) goto L37
            if (r9 == r1) goto L2c
            goto L42
        L2c:
            android.support.v7.widget.StaggeredGridLayoutManager$d r9 = r6.f1453w
            r9.k(r7, r4)
            android.support.v7.widget.StaggeredGridLayoutManager$d r7 = r6.f1453w
            r7.j(r8, r4)
            goto L42
        L37:
            android.support.v7.widget.StaggeredGridLayoutManager$d r9 = r6.f1453w
            r9.k(r7, r8)
            goto L42
        L3d:
            android.support.v7.widget.StaggeredGridLayoutManager$d r9 = r6.f1453w
            r9.j(r7, r8)
        L42:
            if (r2 > r0) goto L45
            return
        L45:
            boolean r7 = r6.f1449s
            if (r7 == 0) goto L4e
            int r7 = r6.Z1()
            goto L52
        L4e:
            int r7 = r6.a2()
        L52:
            if (r3 > r7) goto L57
            r6.k1()
        L57:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.StaggeredGridLayoutManager.g2(int, int, int):void");
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public int h0(RecyclerView.u uVar, RecyclerView.y yVar) {
        return this.f1445o == 0 ? this.f1441k : super.h0(uVar, yVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View h2() {
        /*
            r12 = this;
            int r0 = r12.H()
            int r1 = r0 + (-1)
            java.util.BitSet r2 = new java.util.BitSet
            int r3 = r12.f1441k
            r2.<init>(r3)
            int r3 = r12.f1441k
            r4 = 0
            r5 = 1
            r2.set(r4, r3, r5)
            int r3 = r12.f1445o
            r6 = -1
            if (r3 != r5) goto L21
            boolean r3 = r12.j2()
            if (r3 == 0) goto L21
            r3 = 1
            goto L22
        L21:
            r3 = -1
        L22:
            boolean r7 = r12.f1449s
            if (r7 == 0) goto L28
            r0 = -1
            goto L29
        L28:
            r1 = 0
        L29:
            if (r1 >= r0) goto L2c
            r6 = 1
        L2c:
            if (r1 == r0) goto La4
            android.view.View r7 = r12.G(r1)
            android.view.ViewGroup$LayoutParams r8 = r7.getLayoutParams()
            android.support.v7.widget.StaggeredGridLayoutManager$c r8 = (android.support.v7.widget.StaggeredGridLayoutManager.c) r8
            android.support.v7.widget.StaggeredGridLayoutManager$f r9 = r8.f1464e
            int r9 = r9.f1486e
            boolean r9 = r2.get(r9)
            if (r9 == 0) goto L52
            android.support.v7.widget.StaggeredGridLayoutManager$f r9 = r8.f1464e
            boolean r9 = r12.J1(r9)
            if (r9 == 0) goto L4b
            return r7
        L4b:
            android.support.v7.widget.StaggeredGridLayoutManager$f r9 = r8.f1464e
            int r9 = r9.f1486e
            r2.clear(r9)
        L52:
            boolean r9 = r8.f1465f
            if (r9 == 0) goto L57
            goto La2
        L57:
            int r9 = r1 + r6
            if (r9 == r0) goto La2
            android.view.View r9 = r12.G(r9)
            boolean r10 = r12.f1449s
            if (r10 == 0) goto L75
            android.support.v7.widget.i r10 = r12.f1443m
            int r10 = r10.d(r7)
            android.support.v7.widget.i r11 = r12.f1443m
            int r11 = r11.d(r9)
            if (r10 >= r11) goto L72
            return r7
        L72:
            if (r10 != r11) goto La2
            goto L86
        L75:
            android.support.v7.widget.i r10 = r12.f1443m
            int r10 = r10.g(r7)
            android.support.v7.widget.i r11 = r12.f1443m
            int r11 = r11.g(r9)
            if (r10 <= r11) goto L84
            return r7
        L84:
            if (r10 != r11) goto La2
        L86:
            android.view.ViewGroup$LayoutParams r9 = r9.getLayoutParams()
            android.support.v7.widget.StaggeredGridLayoutManager$c r9 = (android.support.v7.widget.StaggeredGridLayoutManager.c) r9
            android.support.v7.widget.StaggeredGridLayoutManager$f r8 = r8.f1464e
            int r8 = r8.f1486e
            android.support.v7.widget.StaggeredGridLayoutManager$f r9 = r9.f1464e
            int r9 = r9.f1486e
            int r8 = r8 - r9
            if (r8 >= 0) goto L99
            r8 = 1
            goto L9a
        L99:
            r8 = 0
        L9a:
            if (r3 >= 0) goto L9e
            r9 = 1
            goto L9f
        L9e:
            r9 = 0
        L9f:
            if (r8 == r9) goto La2
            return r7
        La2:
            int r1 = r1 + r6
            goto L2c
        La4:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.StaggeredGridLayoutManager.h2():android.view.View");
    }

    public void i2() {
        this.f1453w.b();
        k1();
    }

    public boolean j2() {
        return W() == 1;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public boolean k() {
        return this.f1445o == 0;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public boolean l() {
        return this.f1445o == 1;
    }

    public final void l2(View view, c cVar, boolean z2) {
        if (cVar.f1465f) {
            if (this.f1445o == 1) {
                k2(view, this.f1435B, RecyclerView.n.I(T(), U(), 0, ((ViewGroup.MarginLayoutParams) cVar).height, true), z2);
                return;
            } else {
                k2(view, RecyclerView.n.I(l0(), m0(), 0, ((ViewGroup.MarginLayoutParams) cVar).width, true), this.f1435B, z2);
                return;
            }
        }
        if (this.f1445o == 1) {
            k2(view, RecyclerView.n.I(this.f1446p, m0(), 0, ((ViewGroup.MarginLayoutParams) cVar).width, false), RecyclerView.n.I(T(), U(), 0, ((ViewGroup.MarginLayoutParams) cVar).height, true), z2);
        } else {
            k2(view, RecyclerView.n.I(l0(), m0(), 0, ((ViewGroup.MarginLayoutParams) cVar).width, true), RecyclerView.n.I(this.f1446p, U(), 0, ((ViewGroup.MarginLayoutParams) cVar).height, false), z2);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public boolean m(RecyclerView.o oVar) {
        return oVar instanceof c;
    }

    /* JADX WARN: Removed duplicated region for block: B:73:0x0127  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m2(android.support.v7.widget.RecyclerView.u r9, android.support.v7.widget.RecyclerView.y r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 328
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.StaggeredGridLayoutManager.m2(android.support.v7.widget.RecyclerView$u, android.support.v7.widget.RecyclerView$y, boolean):void");
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public int n1(int i2, RecyclerView.u uVar, RecyclerView.y yVar) {
        return u2(i2, uVar, yVar);
    }

    public final boolean n2(int i2) {
        if (this.f1445o == 0) {
            return (i2 == -1) != this.f1449s;
        }
        return ((i2 == -1) == this.f1449s) == j2();
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public int o(RecyclerView.y yVar) {
        return K1(yVar);
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void o1(int i2) {
        e eVar = this.f1434A;
        if (eVar != null && eVar.f1472a != i2) {
            eVar.a();
        }
        this.f1451u = i2;
        this.f1452v = PKIFailureInfo.systemUnavail;
        k1();
    }

    public final void o2(View view) {
        for (int i2 = this.f1441k - 1; i2 >= 0; i2--) {
            this.f1442l[i2].q(view);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public int p(RecyclerView.y yVar) {
        return L1(yVar);
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public int p1(int i2, RecyclerView.u uVar, RecyclerView.y yVar) {
        return u2(i2, uVar, yVar);
    }

    public final void p2(RecyclerView.u uVar, android.support.v7.widget.f fVar) {
        if (!fVar.f1680a || fVar.f1688i) {
            return;
        }
        if (fVar.f1681b == 0) {
            if (fVar.f1684e == -1) {
                q2(uVar, fVar.f1686g);
                return;
            } else {
                r2(uVar, fVar.f1685f);
                return;
            }
        }
        if (fVar.f1684e != -1) {
            int iD2 = d2(fVar.f1686g) - fVar.f1686g;
            r2(uVar, iD2 < 0 ? fVar.f1685f : Math.min(iD2, fVar.f1681b) + fVar.f1685f);
        } else {
            int i2 = fVar.f1685f;
            int iC2 = i2 - c2(i2);
            q2(uVar, iC2 < 0 ? fVar.f1686g : fVar.f1686g - Math.min(iC2, fVar.f1681b));
        }
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public int q(RecyclerView.y yVar) {
        return M1(yVar);
    }

    public final void q2(RecyclerView.u uVar, int i2) {
        for (int iH = H() - 1; iH >= 0; iH--) {
            View viewG = G(iH);
            if (this.f1443m.g(viewG) < i2 || this.f1443m.q(viewG) < i2) {
                return;
            }
            c cVar = (c) viewG.getLayoutParams();
            if (cVar.f1465f) {
                for (int i3 = 0; i3 < this.f1441k; i3++) {
                    if (this.f1442l[i3].f1482a.size() == 1) {
                        return;
                    }
                }
                for (int i4 = 0; i4 < this.f1441k; i4++) {
                    this.f1442l[i4].o();
                }
            } else if (cVar.f1464e.f1482a.size() == 1) {
                return;
            } else {
                cVar.f1464e.o();
            }
            e1(viewG, uVar);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public int r(RecyclerView.y yVar) {
        return K1(yVar);
    }

    public final void r2(RecyclerView.u uVar, int i2) {
        while (H() > 0) {
            View viewG = G(0);
            if (this.f1443m.d(viewG) > i2 || this.f1443m.p(viewG) > i2) {
                return;
            }
            c cVar = (c) viewG.getLayoutParams();
            if (cVar.f1465f) {
                for (int i3 = 0; i3 < this.f1441k; i3++) {
                    if (this.f1442l[i3].f1482a.size() == 1) {
                        return;
                    }
                }
                for (int i4 = 0; i4 < this.f1441k; i4++) {
                    this.f1442l[i4].p();
                }
            } else if (cVar.f1464e.f1482a.size() == 1) {
                return;
            } else {
                cVar.f1464e.p();
            }
            e1(viewG, uVar);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public int s(RecyclerView.y yVar) {
        return L1(yVar);
    }

    public final void s2() {
        if (this.f1444n.k() == 1073741824) {
            return;
        }
        int iH = H();
        float fMax = 0.0f;
        for (int i2 = 0; i2 < iH; i2++) {
            View viewG = G(i2);
            float fE = this.f1444n.e(viewG);
            if (fE >= fMax) {
                if (((c) viewG.getLayoutParams()).f()) {
                    fE = (fE * 1.0f) / this.f1441k;
                }
                fMax = Math.max(fMax, fE);
            }
        }
        int i3 = this.f1446p;
        int iRound = Math.round(fMax * this.f1441k);
        if (this.f1444n.k() == Integer.MIN_VALUE) {
            iRound = Math.min(iRound, this.f1444n.n());
        }
        E2(iRound);
        if (this.f1446p == i3) {
            return;
        }
        for (int i4 = 0; i4 < iH; i4++) {
            View viewG2 = G(i4);
            c cVar = (c) viewG2.getLayoutParams();
            if (!cVar.f1465f) {
                if (j2() && this.f1445o == 1) {
                    int i5 = this.f1441k;
                    int i6 = cVar.f1464e.f1486e;
                    viewG2.offsetLeftAndRight(((-((i5 - 1) - i6)) * this.f1446p) - ((-((i5 - 1) - i6)) * i3));
                } else {
                    int i7 = cVar.f1464e.f1486e;
                    int i8 = this.f1446p * i7;
                    int i9 = i7 * i3;
                    if (this.f1445o == 1) {
                        viewG2.offsetLeftAndRight(i8 - i9);
                    } else {
                        viewG2.offsetTopAndBottom(i8 - i9);
                    }
                }
            }
        }
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public int t(RecyclerView.y yVar) {
        return M1(yVar);
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void u1(Rect rect, int i2, int i3) {
        int iN;
        int iN2;
        int iB0 = b0() + c0();
        int iD0 = d0() + a0();
        if (this.f1445o == 1) {
            iN2 = RecyclerView.n.n(i3, rect.height() + iD0, Y());
            iN = RecyclerView.n.n(i2, (this.f1446p * this.f1441k) + iB0, Z());
        } else {
            iN = RecyclerView.n.n(i2, rect.width() + iB0, Z());
            iN2 = RecyclerView.n.n(i3, (this.f1446p * this.f1441k) + iD0, Y());
        }
        t1(iN, iN2);
    }

    public int u2(int i2, RecyclerView.u uVar, RecyclerView.y yVar) {
        int iZ1;
        int i3;
        if (i2 > 0) {
            iZ1 = a2();
            i3 = 1;
        } else {
            iZ1 = Z1();
            i3 = -1;
        }
        this.f1447q.f1680a = true;
        D2(iZ1, yVar);
        v2(i3);
        android.support.v7.widget.f fVar = this.f1447q;
        fVar.f1682c = iZ1 + fVar.f1683d;
        int iAbs = Math.abs(i2);
        android.support.v7.widget.f fVar2 = this.f1447q;
        fVar2.f1681b = iAbs;
        int iR1 = R1(uVar, fVar2, yVar);
        if (iAbs >= iR1) {
            i2 = i2 < 0 ? -iR1 : iR1;
        }
        this.f1443m.r(-i2);
        this.f1455y = this.f1449s;
        return i2;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void v0(int i2) {
        super.v0(i2);
        for (int i3 = 0; i3 < this.f1441k; i3++) {
            this.f1442l[i3].n(i2);
        }
    }

    public final void v2(int i2) {
        android.support.v7.widget.f fVar = this.f1447q;
        fVar.f1684e = i2;
        fVar.f1683d = this.f1449s != (i2 == -1) ? -1 : 1;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void w0(int i2) {
        super.w0(i2);
        for (int i3 = 0; i3 < this.f1441k; i3++) {
            this.f1442l[i3].n(i2);
        }
    }

    public void w2(int i2) {
        if (i2 != 0 && i2 != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        g(null);
        if (i2 == this.f1445o) {
            return;
        }
        this.f1445o = i2;
        i iVar = this.f1443m;
        this.f1443m = this.f1444n;
        this.f1444n = iVar;
        k1();
    }

    public void x2(boolean z2) {
        g(null);
        e eVar = this.f1434A;
        if (eVar != null && eVar.f1479h != z2) {
            eVar.f1479h = z2;
        }
        this.f1448r = z2;
        k1();
    }

    public void y2(int i2) {
        g(null);
        if (i2 != this.f1441k) {
            i2();
            this.f1441k = i2;
            this.f1450t = new BitSet(this.f1441k);
            this.f1442l = new f[this.f1441k];
            for (int i3 = 0; i3 < this.f1441k; i3++) {
                this.f1442l[i3] = new f(i3);
            }
            k1();
        }
    }

    public final void z2(int i2, int i3) {
        for (int i4 = 0; i4 < this.f1441k; i4++) {
            if (!this.f1442l[i4].f1482a.isEmpty()) {
                F2(this.f1442l[i4], i2, i3);
            }
        }
    }

    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int[] f1466a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public List f1467b;

        public void a(a aVar) {
            if (this.f1467b == null) {
                this.f1467b = new ArrayList();
            }
            int size = this.f1467b.size();
            for (int i2 = 0; i2 < size; i2++) {
                a aVar2 = (a) this.f1467b.get(i2);
                if (aVar2.f1468a == aVar.f1468a) {
                    this.f1467b.remove(i2);
                }
                if (aVar2.f1468a >= aVar.f1468a) {
                    this.f1467b.add(i2, aVar);
                    return;
                }
            }
            this.f1467b.add(aVar);
        }

        public void b() {
            int[] iArr = this.f1466a;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f1467b = null;
        }

        public void c(int i2) {
            int[] iArr = this.f1466a;
            if (iArr == null) {
                int[] iArr2 = new int[Math.max(i2, 10) + 1];
                this.f1466a = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i2 >= iArr.length) {
                int[] iArr3 = new int[o(i2)];
                this.f1466a = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                int[] iArr4 = this.f1466a;
                Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
            }
        }

        public int d(int i2) {
            List list = this.f1467b;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (((a) this.f1467b.get(size)).f1468a >= i2) {
                        this.f1467b.remove(size);
                    }
                }
            }
            return h(i2);
        }

        public a e(int i2, int i3, int i4, boolean z2) {
            List list = this.f1467b;
            if (list == null) {
                return null;
            }
            int size = list.size();
            for (int i5 = 0; i5 < size; i5++) {
                a aVar = (a) this.f1467b.get(i5);
                int i6 = aVar.f1468a;
                if (i6 >= i3) {
                    return null;
                }
                if (i6 >= i2 && (i4 == 0 || aVar.f1469b == i4 || (z2 && aVar.f1471d))) {
                    return aVar;
                }
            }
            return null;
        }

        public a f(int i2) {
            List list = this.f1467b;
            if (list == null) {
                return null;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                a aVar = (a) this.f1467b.get(size);
                if (aVar.f1468a == i2) {
                    return aVar;
                }
            }
            return null;
        }

        public int g(int i2) {
            int[] iArr = this.f1466a;
            if (iArr == null || i2 >= iArr.length) {
                return -1;
            }
            return iArr[i2];
        }

        public int h(int i2) {
            int[] iArr = this.f1466a;
            if (iArr == null || i2 >= iArr.length) {
                return -1;
            }
            int i3 = i(i2);
            if (i3 == -1) {
                int[] iArr2 = this.f1466a;
                Arrays.fill(iArr2, i2, iArr2.length, -1);
                return this.f1466a.length;
            }
            int i4 = i3 + 1;
            Arrays.fill(this.f1466a, i2, i4, -1);
            return i4;
        }

        public final int i(int i2) {
            if (this.f1467b == null) {
                return -1;
            }
            a aVarF = f(i2);
            if (aVarF != null) {
                this.f1467b.remove(aVarF);
            }
            int size = this.f1467b.size();
            int i3 = 0;
            while (true) {
                if (i3 >= size) {
                    i3 = -1;
                    break;
                }
                if (((a) this.f1467b.get(i3)).f1468a >= i2) {
                    break;
                }
                i3++;
            }
            if (i3 == -1) {
                return -1;
            }
            a aVar = (a) this.f1467b.get(i3);
            this.f1467b.remove(i3);
            return aVar.f1468a;
        }

        public void j(int i2, int i3) {
            int[] iArr = this.f1466a;
            if (iArr == null || i2 >= iArr.length) {
                return;
            }
            int i4 = i2 + i3;
            c(i4);
            int[] iArr2 = this.f1466a;
            System.arraycopy(iArr2, i2, iArr2, i4, (iArr2.length - i2) - i3);
            Arrays.fill(this.f1466a, i2, i4, -1);
            l(i2, i3);
        }

        public void k(int i2, int i3) {
            int[] iArr = this.f1466a;
            if (iArr == null || i2 >= iArr.length) {
                return;
            }
            int i4 = i2 + i3;
            c(i4);
            int[] iArr2 = this.f1466a;
            System.arraycopy(iArr2, i4, iArr2, i2, (iArr2.length - i2) - i3);
            int[] iArr3 = this.f1466a;
            Arrays.fill(iArr3, iArr3.length - i3, iArr3.length, -1);
            m(i2, i3);
        }

        public final void l(int i2, int i3) {
            List list = this.f1467b;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                a aVar = (a) this.f1467b.get(size);
                int i4 = aVar.f1468a;
                if (i4 >= i2) {
                    aVar.f1468a = i4 + i3;
                }
            }
        }

        public final void m(int i2, int i3) {
            List list = this.f1467b;
            if (list == null) {
                return;
            }
            int i4 = i2 + i3;
            for (int size = list.size() - 1; size >= 0; size--) {
                a aVar = (a) this.f1467b.get(size);
                int i5 = aVar.f1468a;
                if (i5 >= i2) {
                    if (i5 < i4) {
                        this.f1467b.remove(size);
                    } else {
                        aVar.f1468a = i5 - i3;
                    }
                }
            }
        }

        public void n(int i2, f fVar) {
            c(i2);
            this.f1466a[i2] = fVar.f1486e;
        }

        public int o(int i2) {
            int length = this.f1466a.length;
            while (length <= i2) {
                length *= 2;
            }
            return length;
        }

        public static class a implements Parcelable {
            public static final Parcelable.Creator<a> CREATOR = new C0012a();

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f1468a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f1469b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int[] f1470c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public boolean f1471d;

            /* JADX INFO: renamed from: android.support.v7.widget.StaggeredGridLayoutManager$d$a$a, reason: collision with other inner class name */
            public static class C0012a implements Parcelable.Creator {
                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public a createFromParcel(Parcel parcel) {
                    return new a(parcel);
                }

                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
                public a[] newArray(int i2) {
                    return new a[i2];
                }
            }

            public a(Parcel parcel) {
                this.f1468a = parcel.readInt();
                this.f1469b = parcel.readInt();
                this.f1471d = parcel.readInt() == 1;
                int i2 = parcel.readInt();
                if (i2 > 0) {
                    int[] iArr = new int[i2];
                    this.f1470c = iArr;
                    parcel.readIntArray(iArr);
                }
            }

            public int a(int i2) {
                int[] iArr = this.f1470c;
                if (iArr == null) {
                    return 0;
                }
                return iArr[i2];
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.f1468a + ", mGapDir=" + this.f1469b + ", mHasUnwantedGapAfter=" + this.f1471d + ", mGapPerSpan=" + Arrays.toString(this.f1470c) + '}';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i2) {
                parcel.writeInt(this.f1468a);
                parcel.writeInt(this.f1469b);
                parcel.writeInt(this.f1471d ? 1 : 0);
                int[] iArr = this.f1470c;
                if (iArr == null || iArr.length <= 0) {
                    parcel.writeInt(0);
                } else {
                    parcel.writeInt(iArr.length);
                    parcel.writeIntArray(this.f1470c);
                }
            }

            public a() {
            }
        }
    }

    public StaggeredGridLayoutManager(int i2, int i3) {
        this.f1445o = i3;
        y2(i2);
        q1(this.f1454x != 0);
        this.f1447q = new android.support.v7.widget.f();
        Q1();
    }
}

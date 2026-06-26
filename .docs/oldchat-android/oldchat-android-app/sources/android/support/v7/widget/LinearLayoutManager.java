package android.support.v7.widget;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;
import l.AbstractC0343a;
import l.N;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import y.C0523a;

/* JADX INFO: loaded from: classes.dex */
public class LinearLayoutManager extends RecyclerView.n implements C0523a.i {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1244k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public c f1245l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public i f1246m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f1247n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f1248o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f1249p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f1250q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1251r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f1252s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f1253t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f1254u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public d f1255v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final a f1256w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final b f1257x;

    public class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1258a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1259b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1260c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1261d;

        public a() {
            e();
        }

        public void a() {
            this.f1259b = this.f1260c ? LinearLayoutManager.this.f1246m.i() : LinearLayoutManager.this.f1246m.m();
        }

        public void b(View view) {
            if (this.f1260c) {
                this.f1259b = LinearLayoutManager.this.f1246m.d(view) + LinearLayoutManager.this.f1246m.o();
            } else {
                this.f1259b = LinearLayoutManager.this.f1246m.g(view);
            }
            this.f1258a = LinearLayoutManager.this.e0(view);
        }

        public void c(View view) {
            int iO = LinearLayoutManager.this.f1246m.o();
            if (iO >= 0) {
                b(view);
                return;
            }
            this.f1258a = LinearLayoutManager.this.e0(view);
            if (this.f1260c) {
                int i2 = (LinearLayoutManager.this.f1246m.i() - iO) - LinearLayoutManager.this.f1246m.d(view);
                this.f1259b = LinearLayoutManager.this.f1246m.i() - i2;
                if (i2 > 0) {
                    int iE = this.f1259b - LinearLayoutManager.this.f1246m.e(view);
                    int iM = LinearLayoutManager.this.f1246m.m();
                    int iMin = iE - (iM + Math.min(LinearLayoutManager.this.f1246m.g(view) - iM, 0));
                    if (iMin < 0) {
                        this.f1259b += Math.min(i2, -iMin);
                        return;
                    }
                    return;
                }
                return;
            }
            int iG = LinearLayoutManager.this.f1246m.g(view);
            int iM2 = iG - LinearLayoutManager.this.f1246m.m();
            this.f1259b = iG;
            if (iM2 > 0) {
                int i3 = (LinearLayoutManager.this.f1246m.i() - Math.min(0, (LinearLayoutManager.this.f1246m.i() - iO) - LinearLayoutManager.this.f1246m.d(view))) - (iG + LinearLayoutManager.this.f1246m.e(view));
                if (i3 < 0) {
                    this.f1259b -= Math.min(iM2, -i3);
                }
            }
        }

        public boolean d(View view, RecyclerView.y yVar) {
            RecyclerView.o oVar = (RecyclerView.o) view.getLayoutParams();
            return !oVar.c() && oVar.a() >= 0 && oVar.a() < yVar.b();
        }

        public void e() {
            this.f1258a = -1;
            this.f1259b = PKIFailureInfo.systemUnavail;
            this.f1260c = false;
            this.f1261d = false;
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.f1258a + ", mCoordinate=" + this.f1259b + ", mLayoutFromEnd=" + this.f1260c + ", mValid=" + this.f1261d + '}';
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1263a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1264b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1265c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1266d;

        public void a() {
            this.f1263a = 0;
            this.f1264b = false;
            this.f1265c = false;
            this.f1266d = false;
        }
    }

    public static class c {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1268b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1269c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1270d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1271e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1272f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1273g;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f1276j;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public boolean f1278l;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1267a = true;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f1274h = 0;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f1275i = false;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public List f1277k = null;

        public void a() {
            b(null);
        }

        public void b(View view) {
            View viewF = f(view);
            if (viewF == null) {
                this.f1270d = -1;
            } else {
                this.f1270d = ((RecyclerView.o) viewF.getLayoutParams()).a();
            }
        }

        public boolean c(RecyclerView.y yVar) {
            int i2 = this.f1270d;
            return i2 >= 0 && i2 < yVar.b();
        }

        public View d(RecyclerView.u uVar) {
            if (this.f1277k != null) {
                return e();
            }
            View viewO = uVar.o(this.f1270d);
            this.f1270d += this.f1271e;
            return viewO;
        }

        public final View e() {
            int size = this.f1277k.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = ((RecyclerView.B) this.f1277k.get(i2)).f1354a;
                RecyclerView.o oVar = (RecyclerView.o) view.getLayoutParams();
                if (!oVar.c() && this.f1270d == oVar.a()) {
                    b(view);
                    return view;
                }
            }
            return null;
        }

        public View f(View view) {
            int iA;
            int size = this.f1277k.size();
            View view2 = null;
            int i2 = Integer.MAX_VALUE;
            for (int i3 = 0; i3 < size; i3++) {
                View view3 = ((RecyclerView.B) this.f1277k.get(i3)).f1354a;
                RecyclerView.o oVar = (RecyclerView.o) view3.getLayoutParams();
                if (view3 != view && !oVar.c() && (iA = (oVar.a() - this.f1270d) * this.f1271e) >= 0 && iA < i2) {
                    view2 = view3;
                    if (iA == 0) {
                        break;
                    }
                    i2 = iA;
                }
            }
            return view2;
        }
    }

    public static class d implements Parcelable {
        public static final Parcelable.Creator<d> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1279a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1280b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1281c;

        public static class a implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public d createFromParcel(Parcel parcel) {
                return new d(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public d[] newArray(int i2) {
                return new d[i2];
            }
        }

        public d() {
        }

        public boolean a() {
            return this.f1279a >= 0;
        }

        public void b() {
            this.f1279a = -1;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f1279a);
            parcel.writeInt(this.f1280b);
            parcel.writeInt(this.f1281c ? 1 : 0);
        }

        public d(Parcel parcel) {
            this.f1279a = parcel.readInt();
            this.f1280b = parcel.readInt();
            this.f1281c = parcel.readInt() == 1;
        }

        public d(d dVar) {
            this.f1279a = dVar.f1279a;
            this.f1280b = dVar.f1280b;
            this.f1281c = dVar.f1281c;
        }
    }

    public LinearLayoutManager(Context context) {
        this(context, 1, false);
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public View A(int i2) {
        int iH = H();
        if (iH == 0) {
            return null;
        }
        int iE0 = i2 - e0(G(0));
        if (iE0 >= 0 && iE0 < iH) {
            View viewG = G(iE0);
            if (e0(viewG) == i2) {
                return viewG;
            }
        }
        return super.A(i2);
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public RecyclerView.o B() {
        return new RecyclerView.o(-2, -2);
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void B0(RecyclerView recyclerView, RecyclerView.u uVar) {
        super.B0(recyclerView, uVar);
        if (this.f1254u) {
            c1(uVar);
            uVar.c();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public boolean B1() {
        return this.f1255v == null && this.f1247n == this.f1250q;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public View C0(View view, int i2, RecyclerView.u uVar, RecyclerView.y yVar) {
        int iF1;
        i2();
        if (H() == 0 || (iF1 = F1(i2)) == Integer.MIN_VALUE) {
            return null;
        }
        H1();
        View viewS1 = iF1 == -1 ? S1(uVar, yVar) : R1(uVar, yVar);
        if (viewS1 == null) {
            return null;
        }
        H1();
        r2(iF1, (int) (this.f1246m.n() * 0.33333334f), false, yVar);
        c cVar = this.f1245l;
        cVar.f1273g = PKIFailureInfo.systemUnavail;
        cVar.f1267a = false;
        I1(uVar, cVar, yVar, true);
        View viewW1 = iF1 == -1 ? W1() : V1();
        if (viewW1 == viewS1 || !viewW1.isFocusable()) {
            return null;
        }
        return viewW1;
    }

    public final int C1(RecyclerView.y yVar) {
        if (H() == 0) {
            return 0;
        }
        H1();
        return k.a(yVar, this.f1246m, L1(!this.f1251r, true), K1(!this.f1251r, true), this, this.f1251r);
    }

    public final int D1(RecyclerView.y yVar) {
        if (H() == 0) {
            return 0;
        }
        H1();
        return k.b(yVar, this.f1246m, L1(!this.f1251r, true), K1(!this.f1251r, true), this, this.f1251r, this.f1249p);
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void E0(AccessibilityEvent accessibilityEvent) {
        super.E0(accessibilityEvent);
        if (H() > 0) {
            N nA = AbstractC0343a.a(accessibilityEvent);
            nA.a(M1());
            nA.h(O1());
        }
    }

    public final int E1(RecyclerView.y yVar) {
        if (H() == 0) {
            return 0;
        }
        H1();
        return k.c(yVar, this.f1246m, L1(!this.f1251r, true), K1(!this.f1251r, true), this, this.f1251r);
    }

    public int F1(int i2) {
        if (i2 == 1) {
            return (this.f1244k != 1 && Z1()) ? 1 : -1;
        }
        if (i2 == 2) {
            return (this.f1244k != 1 && Z1()) ? -1 : 1;
        }
        if (i2 == 17) {
            if (this.f1244k == 0) {
                return -1;
            }
            return PKIFailureInfo.systemUnavail;
        }
        if (i2 == 33) {
            if (this.f1244k == 1) {
                return -1;
            }
            return PKIFailureInfo.systemUnavail;
        }
        if (i2 == 66) {
            if (this.f1244k == 0) {
                return 1;
            }
            return PKIFailureInfo.systemUnavail;
        }
        if (i2 == 130 && this.f1244k == 1) {
            return 1;
        }
        return PKIFailureInfo.systemUnavail;
    }

    public c G1() {
        return new c();
    }

    public void H1() {
        if (this.f1245l == null) {
            this.f1245l = G1();
        }
        if (this.f1246m == null) {
            this.f1246m = i.b(this, this.f1244k);
        }
    }

    public int I1(RecyclerView.u uVar, c cVar, RecyclerView.y yVar, boolean z2) {
        int i2 = cVar.f1269c;
        int i3 = cVar.f1273g;
        if (i3 != Integer.MIN_VALUE) {
            if (i2 < 0) {
                cVar.f1273g = i3 + i2;
            }
            d2(uVar, cVar);
        }
        int i4 = cVar.f1269c + cVar.f1274h;
        b bVar = this.f1257x;
        while (true) {
            if ((!cVar.f1278l && i4 <= 0) || !cVar.c(yVar)) {
                break;
            }
            bVar.a();
            a2(uVar, yVar, cVar, bVar);
            if (!bVar.f1264b) {
                cVar.f1268b += bVar.f1263a * cVar.f1272f;
                if (!bVar.f1265c || this.f1245l.f1277k != null || !yVar.e()) {
                    int i5 = cVar.f1269c;
                    int i6 = bVar.f1263a;
                    cVar.f1269c = i5 - i6;
                    i4 -= i6;
                }
                int i7 = cVar.f1273g;
                if (i7 != Integer.MIN_VALUE) {
                    int i8 = i7 + bVar.f1263a;
                    cVar.f1273g = i8;
                    int i9 = cVar.f1269c;
                    if (i9 < 0) {
                        cVar.f1273g = i8 + i9;
                    }
                    d2(uVar, cVar);
                }
                if (z2 && bVar.f1266d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i2 - cVar.f1269c;
    }

    public final View J1(RecyclerView.u uVar, RecyclerView.y yVar) {
        return Q1(uVar, yVar, 0, H(), yVar.b());
    }

    public final View K1(boolean z2, boolean z3) {
        return this.f1249p ? P1(0, H(), z2, z3) : P1(H() - 1, -1, z2, z3);
    }

    public final View L1(boolean z2, boolean z3) {
        return this.f1249p ? P1(H() - 1, -1, z2, z3) : P1(0, H(), z2, z3);
    }

    public int M1() {
        View viewP1 = P1(0, H(), false, true);
        if (viewP1 == null) {
            return -1;
        }
        return e0(viewP1);
    }

    public final View N1(RecyclerView.u uVar, RecyclerView.y yVar) {
        return Q1(uVar, yVar, H() - 1, -1, yVar.b());
    }

    public int O1() {
        View viewP1 = P1(H() - 1, -1, false, true);
        if (viewP1 == null) {
            return -1;
        }
        return e0(viewP1);
    }

    public View P1(int i2, int i3, boolean z2, boolean z3) {
        H1();
        int iM = this.f1246m.m();
        int i4 = this.f1246m.i();
        int i5 = i3 > i2 ? 1 : -1;
        View view = null;
        while (i2 != i3) {
            View viewG = G(i2);
            int iG = this.f1246m.g(viewG);
            int iD = this.f1246m.d(viewG);
            if (iG < i4 && iD > iM) {
                if (!z2) {
                    return viewG;
                }
                if (iG >= iM && iD <= i4) {
                    return viewG;
                }
                if (z3 && view == null) {
                    view = viewG;
                }
            }
            i2 += i5;
        }
        return view;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void Q0(RecyclerView.u uVar, RecyclerView.y yVar) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int iT1;
        int i7;
        View viewA;
        int iG;
        int i8;
        int i9 = -1;
        if (!(this.f1255v == null && this.f1252s == -1) && yVar.b() == 0) {
            c1(uVar);
            return;
        }
        d dVar = this.f1255v;
        if (dVar != null && dVar.a()) {
            this.f1252s = this.f1255v.f1279a;
        }
        H1();
        this.f1245l.f1267a = false;
        i2();
        a aVar = this.f1256w;
        if (!aVar.f1261d || this.f1252s != -1 || this.f1255v != null) {
            aVar.e();
            a aVar2 = this.f1256w;
            aVar2.f1260c = this.f1249p ^ this.f1250q;
            q2(uVar, yVar, aVar2);
            this.f1256w.f1261d = true;
        }
        int iX1 = X1(yVar);
        if (this.f1245l.f1276j >= 0) {
            i2 = iX1;
            iX1 = 0;
        } else {
            i2 = 0;
        }
        int iM = iX1 + this.f1246m.m();
        int iJ = i2 + this.f1246m.j();
        if (yVar.e() && (i7 = this.f1252s) != -1 && this.f1253t != Integer.MIN_VALUE && (viewA = A(i7)) != null) {
            if (this.f1249p) {
                i8 = this.f1246m.i() - this.f1246m.d(viewA);
                iG = this.f1253t;
            } else {
                iG = this.f1246m.g(viewA) - this.f1246m.m();
                i8 = this.f1253t;
            }
            int i10 = i8 - iG;
            if (i10 > 0) {
                iM += i10;
            } else {
                iJ -= i10;
            }
        }
        a aVar3 = this.f1256w;
        if (!aVar3.f1260c ? !this.f1249p : this.f1249p) {
            i9 = 1;
        }
        c2(uVar, yVar, aVar3, i9);
        u(uVar);
        this.f1245l.f1278l = h2();
        this.f1245l.f1275i = yVar.e();
        a aVar4 = this.f1256w;
        if (aVar4.f1260c) {
            v2(aVar4);
            c cVar = this.f1245l;
            cVar.f1274h = iM;
            I1(uVar, cVar, yVar, false);
            c cVar2 = this.f1245l;
            i4 = cVar2.f1268b;
            int i11 = cVar2.f1270d;
            int i12 = cVar2.f1269c;
            if (i12 > 0) {
                iJ += i12;
            }
            t2(this.f1256w);
            c cVar3 = this.f1245l;
            cVar3.f1274h = iJ;
            cVar3.f1270d += cVar3.f1271e;
            I1(uVar, cVar3, yVar, false);
            c cVar4 = this.f1245l;
            i3 = cVar4.f1268b;
            int i13 = cVar4.f1269c;
            if (i13 > 0) {
                u2(i11, i4);
                c cVar5 = this.f1245l;
                cVar5.f1274h = i13;
                I1(uVar, cVar5, yVar, false);
                i4 = this.f1245l.f1268b;
            }
        } else {
            t2(aVar4);
            c cVar6 = this.f1245l;
            cVar6.f1274h = iJ;
            I1(uVar, cVar6, yVar, false);
            c cVar7 = this.f1245l;
            i3 = cVar7.f1268b;
            int i14 = cVar7.f1270d;
            int i15 = cVar7.f1269c;
            if (i15 > 0) {
                iM += i15;
            }
            v2(this.f1256w);
            c cVar8 = this.f1245l;
            cVar8.f1274h = iM;
            cVar8.f1270d += cVar8.f1271e;
            I1(uVar, cVar8, yVar, false);
            c cVar9 = this.f1245l;
            i4 = cVar9.f1268b;
            int i16 = cVar9.f1269c;
            if (i16 > 0) {
                s2(i14, i3);
                c cVar10 = this.f1245l;
                cVar10.f1274h = i16;
                I1(uVar, cVar10, yVar, false);
                i3 = this.f1245l.f1268b;
            }
        }
        if (H() > 0) {
            if (this.f1249p ^ this.f1250q) {
                int iT12 = T1(i3, uVar, yVar, true);
                i5 = i4 + iT12;
                i6 = i3 + iT12;
                iT1 = U1(i5, uVar, yVar, false);
            } else {
                int iU1 = U1(i4, uVar, yVar, true);
                i5 = i4 + iU1;
                i6 = i3 + iU1;
                iT1 = T1(i6, uVar, yVar, false);
            }
            i4 = i5 + iT1;
            i3 = i6 + iT1;
        }
        b2(uVar, yVar, i4, i3);
        if (yVar.e()) {
            this.f1256w.e();
        } else {
            this.f1246m.s();
        }
        this.f1247n = this.f1250q;
    }

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
            if (iE0 >= 0 && iE0 < i4) {
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

    @Override // android.support.v7.widget.RecyclerView.n
    public void R0(RecyclerView.y yVar) {
        super.R0(yVar);
        this.f1255v = null;
        this.f1252s = -1;
        this.f1253t = PKIFailureInfo.systemUnavail;
        this.f1256w.e();
    }

    public final View R1(RecyclerView.u uVar, RecyclerView.y yVar) {
        return this.f1249p ? J1(uVar, yVar) : N1(uVar, yVar);
    }

    public final View S1(RecyclerView.u uVar, RecyclerView.y yVar) {
        return this.f1249p ? N1(uVar, yVar) : J1(uVar, yVar);
    }

    public final int T1(int i2, RecyclerView.u uVar, RecyclerView.y yVar, boolean z2) {
        int i3;
        int i4 = this.f1246m.i() - i2;
        if (i4 <= 0) {
            return 0;
        }
        int i5 = -j2(-i4, uVar, yVar);
        int i6 = i2 + i5;
        if (!z2 || (i3 = this.f1246m.i() - i6) <= 0) {
            return i5;
        }
        this.f1246m.r(i3);
        return i3 + i5;
    }

    public final int U1(int i2, RecyclerView.u uVar, RecyclerView.y yVar, boolean z2) {
        int iM;
        int iM2 = i2 - this.f1246m.m();
        if (iM2 <= 0) {
            return 0;
        }
        int i3 = -j2(iM2, uVar, yVar);
        int i4 = i2 + i3;
        if (!z2 || (iM = i4 - this.f1246m.m()) <= 0) {
            return i3;
        }
        this.f1246m.r(-iM);
        return i3 - iM;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void V0(Parcelable parcelable) {
        if (parcelable instanceof d) {
            this.f1255v = (d) parcelable;
            k1();
        }
    }

    public final View V1() {
        return G(this.f1249p ? 0 : H() - 1);
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public Parcelable W0() {
        if (this.f1255v != null) {
            return new d(this.f1255v);
        }
        d dVar = new d();
        if (H() > 0) {
            H1();
            boolean z2 = this.f1247n ^ this.f1249p;
            dVar.f1281c = z2;
            if (z2) {
                View viewV1 = V1();
                dVar.f1280b = this.f1246m.i() - this.f1246m.d(viewV1);
                dVar.f1279a = e0(viewV1);
            } else {
                View viewW1 = W1();
                dVar.f1279a = e0(viewW1);
                dVar.f1280b = this.f1246m.g(viewW1) - this.f1246m.m();
            }
        } else {
            dVar.b();
        }
        return dVar;
    }

    public final View W1() {
        return G(this.f1249p ? H() - 1 : 0);
    }

    public int X1(RecyclerView.y yVar) {
        if (yVar.d()) {
            return this.f1246m.n();
        }
        return 0;
    }

    public int Y1() {
        return this.f1244k;
    }

    public boolean Z1() {
        return W() == 1;
    }

    @Override // y.C0523a.i
    public void a(View view, View view2, int i2, int i3) {
        g("Cannot drop a view during a scroll or layout calculation");
        H1();
        i2();
        int iE0 = e0(view);
        int iE02 = e0(view2);
        byte b2 = iE0 < iE02 ? (byte) 1 : (byte) -1;
        if (this.f1249p) {
            if (b2 == 1) {
                k2(iE02, this.f1246m.i() - (this.f1246m.g(view2) + this.f1246m.e(view)));
                return;
            } else {
                k2(iE02, this.f1246m.i() - this.f1246m.d(view2));
                return;
            }
        }
        if (b2 == -1) {
            k2(iE02, this.f1246m.g(view2));
        } else {
            k2(iE02, this.f1246m.d(view2) - this.f1246m.e(view));
        }
    }

    public void a2(RecyclerView.u uVar, RecyclerView.y yVar, c cVar, b bVar) {
        int i2;
        int i3;
        int i4;
        int iB0;
        int iF;
        View viewD = cVar.d(uVar);
        if (viewD == null) {
            bVar.f1264b = true;
            return;
        }
        RecyclerView.o oVar = (RecyclerView.o) viewD.getLayoutParams();
        if (cVar.f1277k == null) {
            if (this.f1249p == (cVar.f1272f == -1)) {
                d(viewD);
            } else {
                e(viewD, 0);
            }
        } else {
            if (this.f1249p == (cVar.f1272f == -1)) {
                b(viewD);
            } else {
                c(viewD, 0);
            }
        }
        t0(viewD, 0, 0);
        bVar.f1263a = this.f1246m.e(viewD);
        if (this.f1244k == 1) {
            if (Z1()) {
                iF = l0() - c0();
                iB0 = iF - this.f1246m.f(viewD);
            } else {
                iB0 = b0();
                iF = this.f1246m.f(viewD) + iB0;
            }
            if (cVar.f1272f == -1) {
                int i5 = cVar.f1268b;
                i4 = i5;
                i3 = iF;
                i2 = i5 - bVar.f1263a;
            } else {
                int i6 = cVar.f1268b;
                i2 = i6;
                i3 = iF;
                i4 = bVar.f1263a + i6;
            }
        } else {
            int iD0 = d0();
            int iF2 = this.f1246m.f(viewD) + iD0;
            if (cVar.f1272f == -1) {
                int i7 = cVar.f1268b;
                i3 = i7;
                i2 = iD0;
                i4 = iF2;
                iB0 = i7 - bVar.f1263a;
            } else {
                int i8 = cVar.f1268b;
                i2 = iD0;
                i3 = bVar.f1263a + i8;
                i4 = iF2;
                iB0 = i8;
            }
        }
        s0(viewD, iB0, i2, i3, i4);
        if (oVar.c() || oVar.b()) {
            bVar.f1265c = true;
        }
        bVar.f1266d = viewD.isFocusable();
    }

    public final void b2(RecyclerView.u uVar, RecyclerView.y yVar, int i2, int i3) {
        if (!yVar.f() || H() == 0 || yVar.e() || !B1()) {
            return;
        }
        List listK = uVar.k();
        int size = listK.size();
        int iE0 = e0(G(0));
        int iE = 0;
        int iE2 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            RecyclerView.B b2 = (RecyclerView.B) listK.get(i4);
            if (!b2.C()) {
                if ((b2.u() < iE0) != this.f1249p) {
                    iE += this.f1246m.e(b2.f1354a);
                } else {
                    iE2 += this.f1246m.e(b2.f1354a);
                }
            }
        }
        this.f1245l.f1277k = listK;
        if (iE > 0) {
            u2(e0(W1()), i2);
            c cVar = this.f1245l;
            cVar.f1274h = iE;
            cVar.f1269c = 0;
            cVar.a();
            I1(uVar, this.f1245l, yVar, false);
        }
        if (iE2 > 0) {
            s2(e0(V1()), i3);
            c cVar2 = this.f1245l;
            cVar2.f1274h = iE2;
            cVar2.f1269c = 0;
            cVar2.a();
            I1(uVar, this.f1245l, yVar, false);
        }
        this.f1245l.f1277k = null;
    }

    public void c2(RecyclerView.u uVar, RecyclerView.y yVar, a aVar, int i2) {
    }

    public final void d2(RecyclerView.u uVar, c cVar) {
        if (!cVar.f1267a || cVar.f1278l) {
            return;
        }
        if (cVar.f1272f == -1) {
            f2(uVar, cVar.f1273g);
        } else {
            g2(uVar, cVar.f1273g);
        }
    }

    public final void e2(RecyclerView.u uVar, int i2, int i3) {
        if (i2 == i3) {
            return;
        }
        if (i3 <= i2) {
            while (i2 > i3) {
                f1(i2, uVar);
                i2--;
            }
        } else {
            for (int i4 = i3 - 1; i4 >= i2; i4--) {
                f1(i4, uVar);
            }
        }
    }

    public final void f2(RecyclerView.u uVar, int i2) {
        int iH = H();
        if (i2 < 0) {
            return;
        }
        int iH2 = this.f1246m.h() - i2;
        if (this.f1249p) {
            for (int i3 = 0; i3 < iH; i3++) {
                View viewG = G(i3);
                if (this.f1246m.g(viewG) < iH2 || this.f1246m.q(viewG) < iH2) {
                    e2(uVar, 0, i3);
                    return;
                }
            }
            return;
        }
        int i4 = iH - 1;
        for (int i5 = i4; i5 >= 0; i5--) {
            View viewG2 = G(i5);
            if (this.f1246m.g(viewG2) < iH2 || this.f1246m.q(viewG2) < iH2) {
                e2(uVar, i4, i5);
                return;
            }
        }
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void g(String str) {
        if (this.f1255v == null) {
            super.g(str);
        }
    }

    public final void g2(RecyclerView.u uVar, int i2) {
        if (i2 < 0) {
            return;
        }
        int iH = H();
        if (!this.f1249p) {
            for (int i3 = 0; i3 < iH; i3++) {
                View viewG = G(i3);
                if (this.f1246m.d(viewG) > i2 || this.f1246m.p(viewG) > i2) {
                    e2(uVar, 0, i3);
                    return;
                }
            }
            return;
        }
        int i4 = iH - 1;
        for (int i5 = i4; i5 >= 0; i5--) {
            View viewG2 = G(i5);
            if (this.f1246m.d(viewG2) > i2 || this.f1246m.p(viewG2) > i2) {
                e2(uVar, i4, i5);
                return;
            }
        }
    }

    public boolean h2() {
        return this.f1246m.k() == 0 && this.f1246m.h() == 0;
    }

    public final void i2() {
        if (this.f1244k == 1 || !Z1()) {
            this.f1249p = this.f1248o;
        } else {
            this.f1249p = !this.f1248o;
        }
    }

    public int j2(int i2, RecyclerView.u uVar, RecyclerView.y yVar) {
        if (H() == 0 || i2 == 0) {
            return 0;
        }
        this.f1245l.f1267a = true;
        H1();
        int i3 = i2 > 0 ? 1 : -1;
        int iAbs = Math.abs(i2);
        r2(i3, iAbs, true, yVar);
        c cVar = this.f1245l;
        int iI1 = cVar.f1273g + I1(uVar, cVar, yVar, false);
        if (iI1 < 0) {
            return 0;
        }
        if (iAbs > iI1) {
            i2 = i3 * iI1;
        }
        this.f1246m.r(-i2);
        this.f1245l.f1276j = i2;
        return i2;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public boolean k() {
        return this.f1244k == 0;
    }

    public void k2(int i2, int i3) {
        this.f1252s = i2;
        this.f1253t = i3;
        d dVar = this.f1255v;
        if (dVar != null) {
            dVar.b();
        }
        k1();
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public boolean l() {
        return this.f1244k == 1;
    }

    public void l2(int i2) {
        if (i2 != 0 && i2 != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i2);
        }
        g(null);
        if (i2 == this.f1244k) {
            return;
        }
        this.f1244k = i2;
        this.f1246m = null;
        k1();
    }

    public void m2(boolean z2) {
        g(null);
        if (z2 == this.f1248o) {
            return;
        }
        this.f1248o = z2;
        k1();
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public int n1(int i2, RecyclerView.u uVar, RecyclerView.y yVar) {
        if (this.f1244k == 1) {
            return 0;
        }
        return j2(i2, uVar, yVar);
    }

    public void n2(boolean z2) {
        g(null);
        if (this.f1250q == z2) {
            return;
        }
        this.f1250q = z2;
        k1();
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public int o(RecyclerView.y yVar) {
        return C1(yVar);
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public void o1(int i2) {
        this.f1252s = i2;
        this.f1253t = PKIFailureInfo.systemUnavail;
        d dVar = this.f1255v;
        if (dVar != null) {
            dVar.b();
        }
        k1();
    }

    public final boolean o2(RecyclerView.u uVar, RecyclerView.y yVar, a aVar) {
        if (H() == 0) {
            return false;
        }
        View viewS = S();
        if (viewS != null && aVar.d(viewS, yVar)) {
            aVar.c(viewS);
            return true;
        }
        if (this.f1247n != this.f1250q) {
            return false;
        }
        View viewR1 = aVar.f1260c ? R1(uVar, yVar) : S1(uVar, yVar);
        if (viewR1 == null) {
            return false;
        }
        aVar.b(viewR1);
        if (!yVar.e() && B1() && (this.f1246m.g(viewR1) >= this.f1246m.i() || this.f1246m.d(viewR1) < this.f1246m.m())) {
            aVar.f1259b = aVar.f1260c ? this.f1246m.i() : this.f1246m.m();
        }
        return true;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public int p(RecyclerView.y yVar) {
        return D1(yVar);
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public int p1(int i2, RecyclerView.u uVar, RecyclerView.y yVar) {
        if (this.f1244k == 0) {
            return 0;
        }
        return j2(i2, uVar, yVar);
    }

    public final boolean p2(RecyclerView.y yVar, a aVar) {
        int i2;
        if (!yVar.e() && (i2 = this.f1252s) != -1) {
            if (i2 >= 0 && i2 < yVar.b()) {
                aVar.f1258a = this.f1252s;
                d dVar = this.f1255v;
                if (dVar != null && dVar.a()) {
                    boolean z2 = this.f1255v.f1281c;
                    aVar.f1260c = z2;
                    if (z2) {
                        aVar.f1259b = this.f1246m.i() - this.f1255v.f1280b;
                    } else {
                        aVar.f1259b = this.f1246m.m() + this.f1255v.f1280b;
                    }
                    return true;
                }
                if (this.f1253t != Integer.MIN_VALUE) {
                    boolean z3 = this.f1249p;
                    aVar.f1260c = z3;
                    if (z3) {
                        aVar.f1259b = this.f1246m.i() - this.f1253t;
                    } else {
                        aVar.f1259b = this.f1246m.m() + this.f1253t;
                    }
                    return true;
                }
                View viewA = A(this.f1252s);
                if (viewA == null) {
                    if (H() > 0) {
                        aVar.f1260c = (this.f1252s < e0(G(0))) == this.f1249p;
                    }
                    aVar.a();
                } else {
                    if (this.f1246m.e(viewA) > this.f1246m.n()) {
                        aVar.a();
                        return true;
                    }
                    if (this.f1246m.g(viewA) - this.f1246m.m() < 0) {
                        aVar.f1259b = this.f1246m.m();
                        aVar.f1260c = false;
                        return true;
                    }
                    if (this.f1246m.i() - this.f1246m.d(viewA) < 0) {
                        aVar.f1259b = this.f1246m.i();
                        aVar.f1260c = true;
                        return true;
                    }
                    aVar.f1259b = aVar.f1260c ? this.f1246m.d(viewA) + this.f1246m.o() : this.f1246m.g(viewA);
                }
                return true;
            }
            this.f1252s = -1;
            this.f1253t = PKIFailureInfo.systemUnavail;
        }
        return false;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public int q(RecyclerView.y yVar) {
        return E1(yVar);
    }

    public final void q2(RecyclerView.u uVar, RecyclerView.y yVar, a aVar) {
        if (p2(yVar, aVar) || o2(uVar, yVar, aVar)) {
            return;
        }
        aVar.a();
        aVar.f1258a = this.f1250q ? yVar.b() - 1 : 0;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public int r(RecyclerView.y yVar) {
        return C1(yVar);
    }

    public final void r2(int i2, int i3, boolean z2, RecyclerView.y yVar) {
        int iM;
        this.f1245l.f1278l = h2();
        this.f1245l.f1274h = X1(yVar);
        c cVar = this.f1245l;
        cVar.f1272f = i2;
        if (i2 == 1) {
            cVar.f1274h += this.f1246m.j();
            View viewV1 = V1();
            c cVar2 = this.f1245l;
            cVar2.f1271e = this.f1249p ? -1 : 1;
            int iE0 = e0(viewV1);
            c cVar3 = this.f1245l;
            cVar2.f1270d = iE0 + cVar3.f1271e;
            cVar3.f1268b = this.f1246m.d(viewV1);
            iM = this.f1246m.d(viewV1) - this.f1246m.i();
        } else {
            View viewW1 = W1();
            this.f1245l.f1274h += this.f1246m.m();
            c cVar4 = this.f1245l;
            cVar4.f1271e = this.f1249p ? 1 : -1;
            int iE02 = e0(viewW1);
            c cVar5 = this.f1245l;
            cVar4.f1270d = iE02 + cVar5.f1271e;
            cVar5.f1268b = this.f1246m.g(viewW1);
            iM = (-this.f1246m.g(viewW1)) + this.f1246m.m();
        }
        c cVar6 = this.f1245l;
        cVar6.f1269c = i3;
        if (z2) {
            cVar6.f1269c = i3 - iM;
        }
        cVar6.f1273g = iM;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public int s(RecyclerView.y yVar) {
        return D1(yVar);
    }

    public final void s2(int i2, int i3) {
        this.f1245l.f1269c = this.f1246m.i() - i3;
        c cVar = this.f1245l;
        cVar.f1271e = this.f1249p ? -1 : 1;
        cVar.f1270d = i2;
        cVar.f1272f = 1;
        cVar.f1268b = i3;
        cVar.f1273g = PKIFailureInfo.systemUnavail;
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public int t(RecyclerView.y yVar) {
        return E1(yVar);
    }

    public final void t2(a aVar) {
        s2(aVar.f1258a, aVar.f1259b);
    }

    public final void u2(int i2, int i3) {
        this.f1245l.f1269c = i3 - this.f1246m.m();
        c cVar = this.f1245l;
        cVar.f1270d = i2;
        cVar.f1271e = this.f1249p ? 1 : -1;
        cVar.f1272f = -1;
        cVar.f1268b = i3;
        cVar.f1273g = PKIFailureInfo.systemUnavail;
    }

    public final void v2(a aVar) {
        u2(aVar.f1258a, aVar.f1259b);
    }

    @Override // android.support.v7.widget.RecyclerView.n
    public boolean y1() {
        return (U() == 1073741824 || m0() == 1073741824 || !n0()) ? false : true;
    }

    public LinearLayoutManager(Context context, int i2, boolean z2) {
        this.f1248o = false;
        this.f1249p = false;
        this.f1250q = false;
        this.f1251r = true;
        this.f1252s = -1;
        this.f1253t = PKIFailureInfo.systemUnavail;
        this.f1255v = null;
        this.f1256w = new a();
        this.f1257x = new b();
        l2(i2);
        m2(z2);
        q1(true);
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        this.f1248o = false;
        this.f1249p = false;
        this.f1250q = false;
        this.f1251r = true;
        this.f1252s = -1;
        this.f1253t = PKIFailureInfo.systemUnavail;
        this.f1255v = null;
        this.f1256w = new a();
        this.f1257x = new b();
        RecyclerView.n.a aVarF0 = RecyclerView.n.f0(context, attributeSet, i2, i3);
        l2(aVarF0.f1398a);
        m2(aVarF0.f1400c);
        n2(aVarF0.f1401d);
        q1(true);
    }
}

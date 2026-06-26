package l;

import android.graphics.Rect;
import android.os.Build;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: renamed from: l.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0347e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final f f5752b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f5753a;

    /* JADX INFO: renamed from: l.e$a */
    public static class a extends j {
        @Override // l.C0347e.j, l.C0347e.k, l.C0347e.f
        public Object b(int i2, int i3, boolean z2, int i4) {
            return AbstractC0350h.a(i2, i3, z2, i4);
        }

        @Override // l.C0347e.j, l.C0347e.k, l.C0347e.f
        public Object r(int i2, int i3, int i4, int i5, boolean z2, boolean z3) {
            return AbstractC0350h.b(i2, i3, i4, i5, z2, z3);
        }
    }

    /* JADX INFO: renamed from: l.e$b */
    public static class b extends a {
    }

    /* JADX INFO: renamed from: l.e$c */
    public static class c extends b {
    }

    /* JADX INFO: renamed from: l.e$d */
    public static class d extends c {
    }

    /* JADX INFO: renamed from: l.e$e, reason: collision with other inner class name */
    public static class C0049e extends k {
        @Override // l.C0347e.k, l.C0347e.f
        public void a(Object obj, boolean z2) {
            AbstractC0333C.t(obj, z2);
        }

        @Override // l.C0347e.k, l.C0347e.f
        public CharSequence c(Object obj) {
            return AbstractC0333C.h(obj);
        }

        @Override // l.C0347e.k, l.C0347e.f
        public boolean d(Object obj) {
            return AbstractC0333C.j(obj);
        }

        @Override // l.C0347e.k, l.C0347e.f
        public CharSequence e(Object obj) {
            return AbstractC0333C.g(obj);
        }

        @Override // l.C0347e.k, l.C0347e.f
        public void g(Object obj, Rect rect) {
            AbstractC0333C.c(obj, rect);
        }

        @Override // l.C0347e.k, l.C0347e.f
        public void h(Object obj, int i2) {
            AbstractC0333C.a(obj, i2);
        }

        @Override // l.C0347e.k, l.C0347e.f
        public boolean i(Object obj) {
            return AbstractC0333C.n(obj);
        }

        @Override // l.C0347e.k, l.C0347e.f
        public int j(Object obj) {
            return AbstractC0333C.b(obj);
        }

        @Override // l.C0347e.k, l.C0347e.f
        public CharSequence k(Object obj) {
            return AbstractC0333C.e(obj);
        }

        @Override // l.C0347e.k, l.C0347e.f
        public boolean l(Object obj) {
            return AbstractC0333C.i(obj);
        }

        @Override // l.C0347e.k, l.C0347e.f
        public boolean m(Object obj) {
            return AbstractC0333C.r(obj);
        }

        @Override // l.C0347e.k, l.C0347e.f
        public boolean n(Object obj) {
            return AbstractC0333C.p(obj);
        }

        @Override // l.C0347e.k, l.C0347e.f
        public void o(Object obj, CharSequence charSequence) {
            AbstractC0333C.s(obj, charSequence);
        }

        @Override // l.C0347e.k, l.C0347e.f
        public CharSequence q(Object obj) {
            return AbstractC0333C.f(obj);
        }

        @Override // l.C0347e.k, l.C0347e.f
        public boolean s(Object obj) {
            return AbstractC0333C.l(obj);
        }

        @Override // l.C0347e.k, l.C0347e.f
        public boolean t(Object obj) {
            return AbstractC0333C.q(obj);
        }

        @Override // l.C0347e.k, l.C0347e.f
        public boolean u(Object obj) {
            return AbstractC0333C.o(obj);
        }

        @Override // l.C0347e.k, l.C0347e.f
        public void v(Object obj, Rect rect) {
            AbstractC0333C.d(obj, rect);
        }

        @Override // l.C0347e.k, l.C0347e.f
        public boolean w(Object obj) {
            return AbstractC0333C.k(obj);
        }

        @Override // l.C0347e.k, l.C0347e.f
        public boolean x(Object obj) {
            return AbstractC0333C.m(obj);
        }
    }

    /* JADX INFO: renamed from: l.e$f */
    public interface f {
        void a(Object obj, boolean z2);

        Object b(int i2, int i3, boolean z2, int i4);

        CharSequence c(Object obj);

        boolean d(Object obj);

        CharSequence e(Object obj);

        void f(Object obj, Object obj2);

        void g(Object obj, Rect rect);

        void h(Object obj, int i2);

        boolean i(Object obj);

        int j(Object obj);

        CharSequence k(Object obj);

        boolean l(Object obj);

        boolean m(Object obj);

        boolean n(Object obj);

        void o(Object obj, CharSequence charSequence);

        void p(Object obj, Object obj2);

        CharSequence q(Object obj);

        Object r(int i2, int i3, int i4, int i5, boolean z2, boolean z3);

        boolean s(Object obj);

        boolean t(Object obj);

        boolean u(Object obj);

        void v(Object obj, Rect rect);

        boolean w(Object obj);

        boolean x(Object obj);

        String y(Object obj);
    }

    /* JADX INFO: renamed from: l.e$g */
    public static class g extends C0049e {
    }

    /* JADX INFO: renamed from: l.e$h */
    public static class h extends g {
    }

    /* JADX INFO: renamed from: l.e$i */
    public static class i extends h {
        @Override // l.C0347e.k, l.C0347e.f
        public String y(Object obj) {
            return AbstractC0335E.a(obj);
        }
    }

    /* JADX INFO: renamed from: l.e$j */
    public static class j extends i {
        @Override // l.C0347e.k, l.C0347e.f
        public Object b(int i2, int i3, boolean z2, int i4) {
            return AbstractC0342L.a(i2, i3, z2, i4);
        }

        @Override // l.C0347e.k, l.C0347e.f
        public void f(Object obj, Object obj2) {
            AbstractC0342L.d(obj, obj2);
        }

        @Override // l.C0347e.k, l.C0347e.f
        public void p(Object obj, Object obj2) {
            AbstractC0342L.c(obj, obj2);
        }

        @Override // l.C0347e.k, l.C0347e.f
        public Object r(int i2, int i3, int i4, int i5, boolean z2, boolean z3) {
            return AbstractC0342L.b(i2, i3, i4, i5, z2);
        }
    }

    /* JADX INFO: renamed from: l.e$l */
    public static class l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f5754a;

        public l(Object obj) {
            this.f5754a = obj;
        }

        public static l a(int i2, int i3, boolean z2, int i4) {
            return new l(C0347e.f5752b.b(i2, i3, z2, i4));
        }
    }

    /* JADX INFO: renamed from: l.e$m */
    public static class m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f5755a;

        public m(Object obj) {
            this.f5755a = obj;
        }

        public static m a(int i2, int i3, int i4, int i5, boolean z2, boolean z3) {
            return new m(C0347e.f5752b.r(i2, i3, i4, i5, z2, z3));
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 24) {
            f5752b = new d();
            return;
        }
        if (i2 >= 23) {
            f5752b = new c();
            return;
        }
        if (i2 >= 22) {
            f5752b = new b();
            return;
        }
        if (i2 >= 21) {
            f5752b = new a();
            return;
        }
        if (i2 >= 19) {
            f5752b = new j();
            return;
        }
        if (i2 >= 18) {
            f5752b = new i();
            return;
        }
        if (i2 >= 17) {
            f5752b = new h();
            return;
        }
        if (i2 >= 16) {
            f5752b = new g();
        } else if (i2 >= 14) {
            f5752b = new C0049e();
        } else {
            f5752b = new k();
        }
    }

    public C0347e(Object obj) {
        this.f5753a = obj;
    }

    public static String b(int i2) {
        if (i2 == 1) {
            return "ACTION_FOCUS";
        }
        if (i2 == 2) {
            return "ACTION_CLEAR_FOCUS";
        }
        switch (i2) {
            case 4:
                return "ACTION_SELECT";
            case 8:
                return "ACTION_CLEAR_SELECTION";
            case 16:
                return "ACTION_CLICK";
            case 32:
                return "ACTION_LONG_CLICK";
            case 64:
                return "ACTION_ACCESSIBILITY_FOCUS";
            case 128:
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            case 256:
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            case 512:
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            case 1024:
                return "ACTION_NEXT_HTML_ELEMENT";
            case 2048:
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            case PKIFailureInfo.certConfirmed /* 4096 */:
                return "ACTION_SCROLL_FORWARD";
            case PKIFailureInfo.certRevoked /* 8192 */:
                return "ACTION_SCROLL_BACKWARD";
            case 16384:
                return "ACTION_COPY";
            case 32768:
                return "ACTION_PASTE";
            case PKIFailureInfo.notAuthorized /* 65536 */:
                return "ACTION_CUT";
            case PKIFailureInfo.unsupportedVersion /* 131072 */:
                return "ACTION_SET_SELECTION";
            default:
                return "ACTION_UNKNOWN";
        }
    }

    public void a(int i2) {
        f5752b.h(this.f5753a, i2);
    }

    public int c() {
        return f5752b.j(this.f5753a);
    }

    public void d(Rect rect) {
        f5752b.g(this.f5753a, rect);
    }

    public void e(Rect rect) {
        f5752b.v(this.f5753a, rect);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C0347e c0347e = (C0347e) obj;
        Object obj2 = this.f5753a;
        if (obj2 == null) {
            if (c0347e.f5753a != null) {
                return false;
            }
        } else if (!obj2.equals(c0347e.f5753a)) {
            return false;
        }
        return true;
    }

    public CharSequence f() {
        return f5752b.k(this.f5753a);
    }

    public CharSequence g() {
        return f5752b.q(this.f5753a);
    }

    public Object h() {
        return this.f5753a;
    }

    public int hashCode() {
        Object obj = this.f5753a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public CharSequence i() {
        return f5752b.e(this.f5753a);
    }

    public CharSequence j() {
        return f5752b.c(this.f5753a);
    }

    public String k() {
        return f5752b.y(this.f5753a);
    }

    public boolean l() {
        return f5752b.l(this.f5753a);
    }

    public boolean m() {
        return f5752b.d(this.f5753a);
    }

    public boolean n() {
        return f5752b.w(this.f5753a);
    }

    public boolean o() {
        return f5752b.s(this.f5753a);
    }

    public boolean p() {
        return f5752b.x(this.f5753a);
    }

    public boolean q() {
        return f5752b.i(this.f5753a);
    }

    public boolean r() {
        return f5752b.u(this.f5753a);
    }

    public boolean s() {
        return f5752b.n(this.f5753a);
    }

    public boolean t() {
        return f5752b.t(this.f5753a);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        d(rect);
        sb.append("; boundsInParent: " + rect);
        e(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ");
        sb.append(i());
        sb.append("; className: ");
        sb.append(f());
        sb.append("; text: ");
        sb.append(j());
        sb.append("; contentDescription: ");
        sb.append(g());
        sb.append("; viewId: ");
        sb.append(k());
        sb.append("; checkable: ");
        sb.append(l());
        sb.append("; checked: ");
        sb.append(m());
        sb.append("; focusable: ");
        sb.append(p());
        sb.append("; focused: ");
        sb.append(q());
        sb.append("; selected: ");
        sb.append(u());
        sb.append("; clickable: ");
        sb.append(n());
        sb.append("; longClickable: ");
        sb.append(r());
        sb.append("; enabled: ");
        sb.append(o());
        sb.append("; password: ");
        sb.append(s());
        sb.append("; scrollable: " + t());
        sb.append("; [");
        int iC = c();
        while (iC != 0) {
            int iNumberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(iC);
            iC &= iNumberOfTrailingZeros ^ (-1);
            sb.append(b(iNumberOfTrailingZeros));
            if (iC != 0) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean u() {
        return f5752b.m(this.f5753a);
    }

    public void v(CharSequence charSequence) {
        f5752b.o(this.f5753a, charSequence);
    }

    public void w(Object obj) {
        f5752b.p(this.f5753a, ((l) obj).f5754a);
    }

    public void x(Object obj) {
        f5752b.f(this.f5753a, ((m) obj).f5755a);
    }

    public void y(boolean z2) {
        f5752b.a(this.f5753a, z2);
    }

    /* JADX INFO: renamed from: l.e$k */
    public static class k implements f {
        @Override // l.C0347e.f
        public Object b(int i2, int i3, boolean z2, int i4) {
            return null;
        }

        @Override // l.C0347e.f
        public CharSequence c(Object obj) {
            return null;
        }

        @Override // l.C0347e.f
        public boolean d(Object obj) {
            return false;
        }

        @Override // l.C0347e.f
        public CharSequence e(Object obj) {
            return null;
        }

        @Override // l.C0347e.f
        public boolean i(Object obj) {
            return false;
        }

        @Override // l.C0347e.f
        public int j(Object obj) {
            return 0;
        }

        @Override // l.C0347e.f
        public CharSequence k(Object obj) {
            return null;
        }

        @Override // l.C0347e.f
        public boolean l(Object obj) {
            return false;
        }

        @Override // l.C0347e.f
        public boolean m(Object obj) {
            return false;
        }

        @Override // l.C0347e.f
        public boolean n(Object obj) {
            return false;
        }

        @Override // l.C0347e.f
        public CharSequence q(Object obj) {
            return null;
        }

        @Override // l.C0347e.f
        public Object r(int i2, int i3, int i4, int i5, boolean z2, boolean z3) {
            return null;
        }

        @Override // l.C0347e.f
        public boolean s(Object obj) {
            return false;
        }

        @Override // l.C0347e.f
        public boolean t(Object obj) {
            return false;
        }

        @Override // l.C0347e.f
        public boolean u(Object obj) {
            return false;
        }

        @Override // l.C0347e.f
        public boolean w(Object obj) {
            return false;
        }

        @Override // l.C0347e.f
        public boolean x(Object obj) {
            return false;
        }

        @Override // l.C0347e.f
        public String y(Object obj) {
            return null;
        }

        @Override // l.C0347e.f
        public void a(Object obj, boolean z2) {
        }

        @Override // l.C0347e.f
        public void f(Object obj, Object obj2) {
        }

        @Override // l.C0347e.f
        public void g(Object obj, Rect rect) {
        }

        @Override // l.C0347e.f
        public void h(Object obj, int i2) {
        }

        @Override // l.C0347e.f
        public void o(Object obj, CharSequence charSequence) {
        }

        @Override // l.C0347e.f
        public void p(Object obj, Object obj2) {
        }

        @Override // l.C0347e.f
        public void v(Object obj, Rect rect) {
        }
    }
}

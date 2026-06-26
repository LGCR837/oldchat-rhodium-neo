package y;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import b.AbstractC0096a;
import b.InterfaceC0097b;
import b.InterfaceC0099d;
import b.r;
import java.util.ArrayList;
import java.util.List;
import k.AbstractC0279i0;
import k.AbstractC0285k0;
import k.C0302q;
import k.W;
import org.spongycastle.crypto.tls.CipherSuite;
import s.AbstractC0467a;

/* JADX INFO: renamed from: y.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0523a extends RecyclerView.m implements RecyclerView.p {

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public Rect f8233B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public long f8234C;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f8238d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f8239e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f8240f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f8241g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f8242h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f8243i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f8244j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f8245k;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public f f8247m;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f8249o;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f8251q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public RecyclerView f8252r;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public VelocityTracker f8254t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public List f8255u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public List f8256v;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public C0302q f8260z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f8235a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float[] f8236b = new float[2];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public RecyclerView.B f8237c = null;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f8246l = -1;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f8248n = 0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public List f8250p = new ArrayList();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Runnable f8253s = new RunnableC0072a();

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public RecyclerView.j f8257w = null;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public View f8258x = null;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f8259y = -1;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final RecyclerView.r f8232A = new b();

    /* JADX INFO: renamed from: y.a$a, reason: collision with other inner class name */
    public class RunnableC0072a implements Runnable {
        public RunnableC0072a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C0523a c0523a = C0523a.this;
            if (c0523a.f8237c == null || !c0523a.z()) {
                return;
            }
            C0523a c0523a2 = C0523a.this;
            RecyclerView.B b2 = c0523a2.f8237c;
            if (b2 != null) {
                c0523a2.u(b2);
            }
            C0523a c0523a3 = C0523a.this;
            c0523a3.f8252r.removeCallbacks(c0523a3.f8253s);
            AbstractC0285k0.F(C0523a.this.f8252r, this);
        }
    }

    /* JADX INFO: renamed from: y.a$b */
    public class b implements RecyclerView.r {
        public b() {
        }

        @Override // android.support.v7.widget.RecyclerView.r
        public void a(RecyclerView recyclerView, MotionEvent motionEvent) {
            C0523a.this.f8260z.a(motionEvent);
            VelocityTracker velocityTracker = C0523a.this.f8254t;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
            if (C0523a.this.f8246l == -1) {
                return;
            }
            int iB = W.b(motionEvent);
            int iFindPointerIndex = motionEvent.findPointerIndex(C0523a.this.f8246l);
            if (iFindPointerIndex >= 0) {
                C0523a.this.i(iB, motionEvent, iFindPointerIndex);
            }
            C0523a c0523a = C0523a.this;
            RecyclerView.B b2 = c0523a.f8237c;
            if (b2 == null) {
                return;
            }
            if (iB != 1) {
                if (iB == 2) {
                    if (iFindPointerIndex >= 0) {
                        c0523a.E(motionEvent, c0523a.f8249o, iFindPointerIndex);
                        C0523a.this.u(b2);
                        C0523a c0523a2 = C0523a.this;
                        c0523a2.f8252r.removeCallbacks(c0523a2.f8253s);
                        C0523a.this.f8253s.run();
                        C0523a.this.f8252r.invalidate();
                        return;
                    }
                    return;
                }
                if (iB != 3) {
                    if (iB != 6) {
                        return;
                    }
                    int iA = W.a(motionEvent);
                    int pointerId = motionEvent.getPointerId(iA);
                    C0523a c0523a3 = C0523a.this;
                    if (pointerId == c0523a3.f8246l) {
                        c0523a3.f8246l = motionEvent.getPointerId(iA == 0 ? 1 : 0);
                        C0523a c0523a4 = C0523a.this;
                        c0523a4.E(motionEvent, c0523a4.f8249o, iA);
                        return;
                    }
                    return;
                }
                VelocityTracker velocityTracker2 = c0523a.f8254t;
                if (velocityTracker2 != null) {
                    velocityTracker2.clear();
                }
            }
            C0523a.this.A(null, 0);
            C0523a.this.f8246l = -1;
        }

        @Override // android.support.v7.widget.RecyclerView.r
        public void b(boolean z2) {
            if (z2) {
                C0523a.this.A(null, 0);
            }
        }

        @Override // android.support.v7.widget.RecyclerView.r
        public boolean c(RecyclerView recyclerView, MotionEvent motionEvent) {
            int iFindPointerIndex;
            h hVarM;
            C0523a.this.f8260z.a(motionEvent);
            int iB = W.b(motionEvent);
            if (iB == 0) {
                C0523a.this.f8246l = motionEvent.getPointerId(0);
                C0523a.this.f8238d = motionEvent.getX();
                C0523a.this.f8239e = motionEvent.getY();
                C0523a.this.v();
                C0523a c0523a = C0523a.this;
                if (c0523a.f8237c == null && (hVarM = c0523a.m(motionEvent)) != null) {
                    C0523a c0523a2 = C0523a.this;
                    c0523a2.f8238d -= hVarM.f8284j;
                    c0523a2.f8239e -= hVarM.f8285k;
                    c0523a2.l(hVarM.f8279e, true);
                    if (C0523a.this.f8235a.remove(hVarM.f8279e.f1354a)) {
                        C0523a c0523a3 = C0523a.this;
                        c0523a3.f8247m.c(c0523a3.f8252r, hVarM.f8279e);
                    }
                    C0523a.this.A(hVarM.f8279e, hVarM.f8280f);
                    C0523a c0523a4 = C0523a.this;
                    c0523a4.E(motionEvent, c0523a4.f8249o, 0);
                }
            } else if (iB == 3 || iB == 1) {
                C0523a c0523a5 = C0523a.this;
                c0523a5.f8246l = -1;
                c0523a5.A(null, 0);
            } else {
                int i2 = C0523a.this.f8246l;
                if (i2 != -1 && (iFindPointerIndex = motionEvent.findPointerIndex(i2)) >= 0) {
                    C0523a.this.i(iB, motionEvent, iFindPointerIndex);
                }
            }
            VelocityTracker velocityTracker = C0523a.this.f8254t;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
            return C0523a.this.f8237c != null;
        }
    }

    /* JADX INFO: renamed from: y.a$c */
    public class c extends h {

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public final /* synthetic */ int f8263p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.B f8264q;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(RecyclerView.B b2, int i2, int i3, float f2, float f3, float f4, float f5, int i4, RecyclerView.B b3) {
            super(b2, i2, i3, f2, f3, f4, f5);
            this.f8263p = i4;
            this.f8264q = b3;
        }

        @Override // y.C0523a.h, b.InterfaceC0097b
        public void a(r rVar) {
            super.a(rVar);
            if (this.f8286l) {
                return;
            }
            if (this.f8263p <= 0) {
                C0523a c0523a = C0523a.this;
                c0523a.f8247m.c(c0523a.f8252r, this.f8264q);
            } else {
                C0523a.this.f8235a.add(this.f8264q.f1354a);
                this.f8283i = true;
                int i2 = this.f8263p;
                if (i2 > 0) {
                    C0523a.this.w(this, i2);
                }
            }
            C0523a c0523a2 = C0523a.this;
            View view = c0523a2.f8258x;
            View view2 = this.f8264q.f1354a;
            if (view == view2) {
                c0523a2.y(view2);
            }
        }
    }

    /* JADX INFO: renamed from: y.a$d */
    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ h f8266a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f8267b;

        public d(h hVar, int i2) {
            this.f8266a = hVar;
            this.f8267b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            RecyclerView recyclerView = C0523a.this.f8252r;
            if (recyclerView == null || !recyclerView.isAttachedToWindow()) {
                return;
            }
            h hVar = this.f8266a;
            if (hVar.f8286l || hVar.f8279e.r() == -1) {
                return;
            }
            RecyclerView.k itemAnimator = C0523a.this.f8252r.getItemAnimator();
            if ((itemAnimator == null || !itemAnimator.q(null)) && !C0523a.this.r()) {
                C0523a.this.f8247m.B(this.f8266a.f8279e, this.f8267b);
            } else {
                C0523a.this.f8252r.post(this);
            }
        }
    }

    /* JADX INFO: renamed from: y.a$e */
    public class e implements RecyclerView.j {
        public e() {
        }

        @Override // android.support.v7.widget.RecyclerView.j
        public int a(int i2, int i3) {
            C0523a c0523a = C0523a.this;
            View view = c0523a.f8258x;
            if (view == null) {
                return i3;
            }
            int iIndexOfChild = c0523a.f8259y;
            if (iIndexOfChild == -1) {
                iIndexOfChild = c0523a.f8252r.indexOfChild(view);
                C0523a.this.f8259y = iIndexOfChild;
            }
            return i3 == i2 + (-1) ? iIndexOfChild : i3 < iIndexOfChild ? i3 : i3 + 1;
        }
    }

    /* JADX INFO: renamed from: y.a$g */
    public class g extends GestureDetector.SimpleOnGestureListener {
        public g() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            RecyclerView.B bY;
            View viewN = C0523a.this.n(motionEvent);
            if (viewN == null || (bY = C0523a.this.f8252r.Y(viewN)) == null) {
                return;
            }
            C0523a c0523a = C0523a.this;
            if (c0523a.f8247m.o(c0523a.f8252r, bY)) {
                int pointerId = motionEvent.getPointerId(0);
                int i2 = C0523a.this.f8246l;
                if (pointerId == i2) {
                    int iFindPointerIndex = motionEvent.findPointerIndex(i2);
                    float x2 = motionEvent.getX(iFindPointerIndex);
                    float y2 = motionEvent.getY(iFindPointerIndex);
                    C0523a c0523a2 = C0523a.this;
                    c0523a2.f8238d = x2;
                    c0523a2.f8239e = y2;
                    c0523a2.f8243i = 0.0f;
                    c0523a2.f8242h = 0.0f;
                    if (c0523a2.f8247m.r()) {
                        C0523a.this.A(bY, 2);
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: y.a$i */
    public interface i {
        void a(View view, View view2, int i2, int i3);
    }

    public C0523a(f fVar) {
        this.f8247m = fVar;
    }

    public static boolean s(View view, float f2, float f3, float f4, float f5) {
        return f2 >= f4 && f2 <= f4 + ((float) view.getWidth()) && f3 >= f5 && f3 <= f5 + ((float) view.getHeight());
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0117  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void A(android.support.v7.widget.RecyclerView.B r24, int r25) {
        /*
            Method dump skipped, instruction units count: 324
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: y.C0523a.A(android.support.v7.widget.RecyclerView$B, int):void");
    }

    public final void B() {
        this.f8251q = ViewConfiguration.get(this.f8252r.getContext()).getScaledTouchSlop();
        this.f8252r.h(this);
        this.f8252r.k(this.f8232A);
        this.f8252r.j(this);
        t();
    }

    public void C(RecyclerView.B b2) {
        if (!this.f8247m.o(this.f8252r, b2)) {
            Log.e("ItemTouchHelper", "Start drag has been called but swiping is not enabled");
            return;
        }
        if (b2.f1354a.getParent() != this.f8252r) {
            Log.e("ItemTouchHelper", "Start drag has been called with a view holder which is not a child of the RecyclerView which is controlled by this ItemTouchHelper.");
            return;
        }
        v();
        this.f8243i = 0.0f;
        this.f8242h = 0.0f;
        A(b2, 2);
    }

    public final int D(RecyclerView.B b2) {
        if (this.f8248n == 2) {
            return 0;
        }
        int iK = this.f8247m.k(this.f8252r, b2);
        int iD = (this.f8247m.d(iK, AbstractC0285k0.k(this.f8252r)) & CipherSuite.DRAFT_TLS_DHE_RSA_WITH_AES_128_OCB) >> 8;
        if (iD == 0) {
            return 0;
        }
        int i2 = (iK & CipherSuite.DRAFT_TLS_DHE_RSA_WITH_AES_128_OCB) >> 8;
        if (Math.abs(this.f8242h) > Math.abs(this.f8243i)) {
            int iH = h(b2, iD);
            if (iH > 0) {
                return (i2 & iH) == 0 ? f.e(iH, AbstractC0285k0.k(this.f8252r)) : iH;
            }
            int iJ = j(b2, iD);
            if (iJ > 0) {
                return iJ;
            }
        } else {
            int iJ2 = j(b2, iD);
            if (iJ2 > 0) {
                return iJ2;
            }
            int iH2 = h(b2, iD);
            if (iH2 > 0) {
                return (i2 & iH2) == 0 ? f.e(iH2, AbstractC0285k0.k(this.f8252r)) : iH2;
            }
        }
        return 0;
    }

    public void E(MotionEvent motionEvent, int i2, int i3) {
        float x2 = motionEvent.getX(i3);
        float y2 = motionEvent.getY(i3);
        float f2 = x2 - this.f8238d;
        this.f8242h = f2;
        this.f8243i = y2 - this.f8239e;
        if ((i2 & 4) == 0) {
            this.f8242h = Math.max(0.0f, f2);
        }
        if ((i2 & 8) == 0) {
            this.f8242h = Math.min(0.0f, this.f8242h);
        }
        if ((i2 & 1) == 0) {
            this.f8243i = Math.max(0.0f, this.f8243i);
        }
        if ((i2 & 2) == 0) {
            this.f8243i = Math.min(0.0f, this.f8243i);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.p
    public void b(View view) {
        y(view);
        RecyclerView.B bY = this.f8252r.Y(view);
        if (bY == null) {
            return;
        }
        RecyclerView.B b2 = this.f8237c;
        if (b2 != null && bY == b2) {
            A(null, 0);
            return;
        }
        l(bY, false);
        if (this.f8235a.remove(bY.f1354a)) {
            this.f8247m.c(this.f8252r, bY);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.m
    public void c(Rect rect, View view, RecyclerView recyclerView, RecyclerView.y yVar) {
        rect.setEmpty();
    }

    @Override // android.support.v7.widget.RecyclerView.m
    public void d(Canvas canvas, RecyclerView recyclerView, RecyclerView.y yVar) {
        float f2;
        float f3;
        this.f8259y = -1;
        if (this.f8237c != null) {
            q(this.f8236b);
            float[] fArr = this.f8236b;
            float f4 = fArr[0];
            f3 = fArr[1];
            f2 = f4;
        } else {
            f2 = 0.0f;
            f3 = 0.0f;
        }
        this.f8247m.w(canvas, recyclerView, this.f8237c, this.f8250p, this.f8248n, f2, f3);
    }

    @Override // android.support.v7.widget.RecyclerView.m
    public void e(Canvas canvas, RecyclerView recyclerView, RecyclerView.y yVar) {
        float f2;
        float f3;
        if (this.f8237c != null) {
            q(this.f8236b);
            float[] fArr = this.f8236b;
            float f4 = fArr[0];
            f3 = fArr[1];
            f2 = f4;
        } else {
            f2 = 0.0f;
            f3 = 0.0f;
        }
        this.f8247m.x(canvas, recyclerView, this.f8237c, this.f8250p, this.f8248n, f2, f3);
    }

    public final void f() {
        if (Build.VERSION.SDK_INT >= 21) {
            return;
        }
        if (this.f8257w == null) {
            this.f8257w = new e();
        }
        this.f8252r.setChildDrawingOrderCallback(this.f8257w);
    }

    public void g(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.f8252r;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            k();
        }
        this.f8252r = recyclerView;
        if (recyclerView != null) {
            Resources resources = recyclerView.getResources();
            this.f8240f = resources.getDimension(AbstractC0467a.f7911c);
            this.f8241g = resources.getDimension(AbstractC0467a.f7910b);
            B();
        }
    }

    public final int h(RecyclerView.B b2, int i2) {
        if ((i2 & 12) == 0) {
            return 0;
        }
        int i3 = this.f8242h > 0.0f ? 8 : 4;
        VelocityTracker velocityTracker = this.f8254t;
        if (velocityTracker != null && this.f8246l > -1) {
            velocityTracker.computeCurrentVelocity(1000, this.f8247m.n(this.f8241g));
            float fA = AbstractC0279i0.a(this.f8254t, this.f8246l);
            float fB = AbstractC0279i0.b(this.f8254t, this.f8246l);
            int i4 = fA > 0.0f ? 8 : 4;
            float fAbs = Math.abs(fA);
            if ((i4 & i2) != 0 && i3 == i4 && fAbs >= this.f8247m.l(this.f8240f) && fAbs > Math.abs(fB)) {
                return i4;
            }
        }
        float width = this.f8252r.getWidth() * this.f8247m.m(b2);
        if ((i2 & i3) == 0 || Math.abs(this.f8242h) <= width) {
            return 0;
        }
        return i3;
    }

    public boolean i(int i2, MotionEvent motionEvent, int i3) {
        RecyclerView.B bP;
        int iF;
        if (this.f8237c != null || i2 != 2 || this.f8248n == 2 || !this.f8247m.q() || this.f8252r.getScrollState() == 1 || (bP = p(motionEvent)) == null || (iF = (this.f8247m.f(this.f8252r, bP) & CipherSuite.DRAFT_TLS_DHE_RSA_WITH_AES_128_OCB) >> 8) == 0) {
            return false;
        }
        float x2 = motionEvent.getX(i3);
        float y2 = motionEvent.getY(i3);
        float f2 = x2 - this.f8238d;
        float f3 = y2 - this.f8239e;
        float fAbs = Math.abs(f2);
        float fAbs2 = Math.abs(f3);
        int i4 = this.f8251q;
        if (fAbs < i4 && fAbs2 < i4) {
            return false;
        }
        if (fAbs > fAbs2) {
            if (f2 < 0.0f && (iF & 4) == 0) {
                return false;
            }
            if (f2 > 0.0f && (iF & 8) == 0) {
                return false;
            }
        } else {
            if (f3 < 0.0f && (iF & 1) == 0) {
                return false;
            }
            if (f3 > 0.0f && (iF & 2) == 0) {
                return false;
            }
        }
        this.f8243i = 0.0f;
        this.f8242h = 0.0f;
        this.f8246l = motionEvent.getPointerId(0);
        A(bP, 1);
        return true;
    }

    public final int j(RecyclerView.B b2, int i2) {
        if ((i2 & 3) == 0) {
            return 0;
        }
        int i3 = this.f8243i > 0.0f ? 2 : 1;
        VelocityTracker velocityTracker = this.f8254t;
        if (velocityTracker != null && this.f8246l > -1) {
            velocityTracker.computeCurrentVelocity(1000, this.f8247m.n(this.f8241g));
            float fA = AbstractC0279i0.a(this.f8254t, this.f8246l);
            float fB = AbstractC0279i0.b(this.f8254t, this.f8246l);
            int i4 = fB > 0.0f ? 2 : 1;
            float fAbs = Math.abs(fB);
            if ((i4 & i2) != 0 && i4 == i3 && fAbs >= this.f8247m.l(this.f8240f) && fAbs > Math.abs(fA)) {
                return i4;
            }
        }
        float height = this.f8252r.getHeight() * this.f8247m.m(b2);
        if ((i2 & i3) == 0 || Math.abs(this.f8243i) <= height) {
            return 0;
        }
        return i3;
    }

    public final void k() {
        this.f8252r.J0(this);
        this.f8252r.L0(this.f8232A);
        this.f8252r.K0(this);
        for (int size = this.f8250p.size() - 1; size >= 0; size--) {
            this.f8247m.c(this.f8252r, ((h) this.f8250p.get(0)).f8279e);
        }
        this.f8250p.clear();
        this.f8258x = null;
        this.f8259y = -1;
        x();
    }

    public int l(RecyclerView.B b2, boolean z2) {
        for (int size = this.f8250p.size() - 1; size >= 0; size--) {
            h hVar = (h) this.f8250p.get(size);
            if (hVar.f8279e == b2) {
                hVar.f8286l |= z2;
                if (!hVar.f8287m) {
                    hVar.e();
                }
                this.f8250p.remove(size);
                return hVar.f8282h;
            }
        }
        return 0;
    }

    public h m(MotionEvent motionEvent) {
        if (this.f8250p.isEmpty()) {
            return null;
        }
        View viewN = n(motionEvent);
        for (int size = this.f8250p.size() - 1; size >= 0; size--) {
            h hVar = (h) this.f8250p.get(size);
            if (hVar.f8279e.f1354a == viewN) {
                return hVar;
            }
        }
        return null;
    }

    public View n(MotionEvent motionEvent) {
        float x2 = motionEvent.getX();
        float y2 = motionEvent.getY();
        RecyclerView.B b2 = this.f8237c;
        if (b2 != null) {
            View view = b2.f1354a;
            if (s(view, x2, y2, this.f8244j + this.f8242h, this.f8245k + this.f8243i)) {
                return view;
            }
        }
        for (int size = this.f8250p.size() - 1; size >= 0; size--) {
            h hVar = (h) this.f8250p.get(size);
            View view2 = hVar.f8279e.f1354a;
            if (s(view2, x2, y2, hVar.f8284j, hVar.f8285k)) {
                return view2;
            }
        }
        return this.f8252r.O(x2, y2);
    }

    public final List o(RecyclerView.B b2) {
        RecyclerView.B b3 = b2;
        List list = this.f8255u;
        if (list == null) {
            this.f8255u = new ArrayList();
            this.f8256v = new ArrayList();
        } else {
            list.clear();
            this.f8256v.clear();
        }
        int iH = this.f8247m.h();
        int iRound = Math.round(this.f8244j + this.f8242h) - iH;
        int iRound2 = Math.round(this.f8245k + this.f8243i) - iH;
        int i2 = iH * 2;
        int width = b3.f1354a.getWidth() + iRound + i2;
        int height = b3.f1354a.getHeight() + iRound2 + i2;
        int i3 = (iRound + width) / 2;
        int i4 = (iRound2 + height) / 2;
        RecyclerView.n layoutManager = this.f8252r.getLayoutManager();
        int iH2 = layoutManager.H();
        int i5 = 0;
        while (i5 < iH2) {
            View viewG = layoutManager.G(i5);
            if (viewG != b3.f1354a && viewG.getBottom() >= iRound2 && viewG.getTop() <= height && viewG.getRight() >= iRound && viewG.getLeft() <= width) {
                RecyclerView.B bY = this.f8252r.Y(viewG);
                if (this.f8247m.a(this.f8252r, this.f8237c, bY)) {
                    int iAbs = Math.abs(i3 - ((viewG.getLeft() + viewG.getRight()) / 2));
                    int iAbs2 = Math.abs(i4 - ((viewG.getTop() + viewG.getBottom()) / 2));
                    int i6 = (iAbs * iAbs) + (iAbs2 * iAbs2);
                    int size = this.f8255u.size();
                    int i7 = 0;
                    for (int i8 = 0; i8 < size && i6 > ((Integer) this.f8256v.get(i8)).intValue(); i8++) {
                        i7++;
                    }
                    this.f8255u.add(i7, bY);
                    this.f8256v.add(i7, Integer.valueOf(i6));
                }
            }
            i5++;
            b3 = b2;
        }
        return this.f8255u;
    }

    public final RecyclerView.B p(MotionEvent motionEvent) {
        View viewN;
        RecyclerView.n layoutManager = this.f8252r.getLayoutManager();
        int i2 = this.f8246l;
        if (i2 == -1) {
            return null;
        }
        int iFindPointerIndex = motionEvent.findPointerIndex(i2);
        float x2 = motionEvent.getX(iFindPointerIndex) - this.f8238d;
        float y2 = motionEvent.getY(iFindPointerIndex) - this.f8239e;
        float fAbs = Math.abs(x2);
        float fAbs2 = Math.abs(y2);
        int i3 = this.f8251q;
        if (fAbs < i3 && fAbs2 < i3) {
            return null;
        }
        if (fAbs > fAbs2 && layoutManager.k()) {
            return null;
        }
        if ((fAbs2 <= fAbs || !layoutManager.l()) && (viewN = n(motionEvent)) != null) {
            return this.f8252r.Y(viewN);
        }
        return null;
    }

    public final void q(float[] fArr) {
        if ((this.f8249o & 12) != 0) {
            fArr[0] = (this.f8244j + this.f8242h) - this.f8237c.f1354a.getLeft();
        } else {
            fArr[0] = AbstractC0285k0.r(this.f8237c.f1354a);
        }
        if ((this.f8249o & 3) != 0) {
            fArr[1] = (this.f8245k + this.f8243i) - this.f8237c.f1354a.getTop();
        } else {
            fArr[1] = AbstractC0285k0.s(this.f8237c.f1354a);
        }
    }

    public boolean r() {
        int size = this.f8250p.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!((h) this.f8250p.get(i2)).f8287m) {
                return true;
            }
        }
        return false;
    }

    public final void t() {
        if (this.f8260z != null) {
            return;
        }
        this.f8260z = new C0302q(this.f8252r.getContext(), new g());
    }

    public void u(RecyclerView.B b2) {
        if (!this.f8252r.isLayoutRequested() && this.f8248n == 2) {
            float fJ = this.f8247m.j(b2);
            int i2 = (int) (this.f8244j + this.f8242h);
            int i3 = (int) (this.f8245k + this.f8243i);
            if (Math.abs(i3 - b2.f1354a.getTop()) >= b2.f1354a.getHeight() * fJ || Math.abs(i2 - b2.f1354a.getLeft()) >= b2.f1354a.getWidth() * fJ) {
                List listO = o(b2);
                if (listO.size() == 0) {
                    return;
                }
                RecyclerView.B b3 = this.f8247m.b(b2, listO, i2, i3);
                if (b3 == null) {
                    this.f8255u.clear();
                    this.f8256v.clear();
                    return;
                }
                int iR = b3.r();
                int iR2 = b2.r();
                if (this.f8247m.y(this.f8252r, b2, b3)) {
                    this.f8247m.z(this.f8252r, b2, iR2, b3, iR, i2, i3);
                }
            }
        }
    }

    public void v() {
        VelocityTracker velocityTracker = this.f8254t;
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
        this.f8254t = VelocityTracker.obtain();
    }

    public void w(h hVar, int i2) {
        this.f8252r.post(new d(hVar, i2));
    }

    public final void x() {
        VelocityTracker velocityTracker = this.f8254t;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f8254t = null;
        }
    }

    public void y(View view) {
        if (view == this.f8258x) {
            this.f8258x = null;
            if (this.f8257w != null) {
                this.f8252r.setChildDrawingOrderCallback(null);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean z() {
        /*
            Method dump skipped, instruction units count: 277
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: y.C0523a.z():boolean");
    }

    /* JADX INFO: renamed from: y.a$f */
    public static abstract class f {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final InterfaceC0524b f8270b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final Interpolator f8271c = new InterpolatorC0073a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final Interpolator f8272d = new b();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f8273a = -1;

        /* JADX INFO: renamed from: y.a$f$a, reason: collision with other inner class name */
        public static class InterpolatorC0073a implements Interpolator {
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f2) {
                return f2 * f2 * f2 * f2 * f2;
            }
        }

        /* JADX INFO: renamed from: y.a$f$b */
        public static class b implements Interpolator {
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f2) {
                float f3 = f2 - 1.0f;
                return (f3 * f3 * f3 * f3 * f3) + 1.0f;
            }
        }

        static {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 21) {
                f8270b = new C0527e();
            } else if (i2 >= 11) {
                f8270b = new C0526d();
            } else {
                f8270b = new C0525c();
            }
        }

        public static int e(int i2, int i3) {
            int i4;
            int i5 = i2 & 789516;
            if (i5 == 0) {
                return i2;
            }
            int i6 = i2 & (i5 ^ (-1));
            if (i3 == 0) {
                i4 = i5 << 2;
            } else {
                int i7 = i5 << 1;
                i6 |= (-789517) & i7;
                i4 = (i7 & 789516) << 2;
            }
            return i6 | i4;
        }

        public static int s(int i2, int i3) {
            return i3 << (i2 * 8);
        }

        public static int t(int i2, int i3) {
            return s(2, i2) | s(1, i3) | s(0, i3 | i2);
        }

        public void A(RecyclerView.B b2, int i2) {
            if (b2 != null) {
                f8270b.d(b2.f1354a);
            }
        }

        public abstract void B(RecyclerView.B b2, int i2);

        public boolean a(RecyclerView recyclerView, RecyclerView.B b2, RecyclerView.B b3) {
            return true;
        }

        public RecyclerView.B b(RecyclerView.B b2, List list, int i2, int i3) {
            int bottom;
            int iAbs;
            int top;
            int iAbs2;
            int left;
            int iAbs3;
            int right;
            int iAbs4;
            int width = i2 + b2.f1354a.getWidth();
            int height = i3 + b2.f1354a.getHeight();
            int left2 = i2 - b2.f1354a.getLeft();
            int top2 = i3 - b2.f1354a.getTop();
            int size = list.size();
            RecyclerView.B b3 = null;
            int i4 = -1;
            for (int i5 = 0; i5 < size; i5++) {
                RecyclerView.B b4 = (RecyclerView.B) list.get(i5);
                if (left2 > 0 && (right = b4.f1354a.getRight() - width) < 0 && b4.f1354a.getRight() > b2.f1354a.getRight() && (iAbs4 = Math.abs(right)) > i4) {
                    b3 = b4;
                    i4 = iAbs4;
                }
                if (left2 < 0 && (left = b4.f1354a.getLeft() - i2) > 0 && b4.f1354a.getLeft() < b2.f1354a.getLeft() && (iAbs3 = Math.abs(left)) > i4) {
                    b3 = b4;
                    i4 = iAbs3;
                }
                if (top2 < 0 && (top = b4.f1354a.getTop() - i3) > 0 && b4.f1354a.getTop() < b2.f1354a.getTop() && (iAbs2 = Math.abs(top)) > i4) {
                    b3 = b4;
                    i4 = iAbs2;
                }
                if (top2 > 0 && (bottom = b4.f1354a.getBottom() - height) < 0 && b4.f1354a.getBottom() > b2.f1354a.getBottom() && (iAbs = Math.abs(bottom)) > i4) {
                    b3 = b4;
                    i4 = iAbs;
                }
            }
            return b3;
        }

        public void c(RecyclerView recyclerView, RecyclerView.B b2) {
            f8270b.a(b2.f1354a);
        }

        public int d(int i2, int i3) {
            int i4;
            int i5 = i2 & 3158064;
            if (i5 == 0) {
                return i2;
            }
            int i6 = i2 & (i5 ^ (-1));
            if (i3 == 0) {
                i4 = i5 >> 2;
            } else {
                int i7 = i5 >> 1;
                i6 |= (-3158065) & i7;
                i4 = (i7 & 3158064) >> 2;
            }
            return i6 | i4;
        }

        public final int f(RecyclerView recyclerView, RecyclerView.B b2) {
            return d(k(recyclerView, b2), AbstractC0285k0.k(recyclerView));
        }

        public long g(RecyclerView recyclerView, int i2, float f2, float f3) {
            RecyclerView.k itemAnimator = recyclerView.getItemAnimator();
            return itemAnimator == null ? i2 == 8 ? 200L : 250L : i2 == 8 ? itemAnimator.n() : itemAnimator.o();
        }

        public int h() {
            return 0;
        }

        public final int i(RecyclerView recyclerView) {
            if (this.f8273a == -1) {
                this.f8273a = recyclerView.getResources().getDimensionPixelSize(AbstractC0467a.f7909a);
            }
            return this.f8273a;
        }

        public float j(RecyclerView.B b2) {
            return 0.5f;
        }

        public abstract int k(RecyclerView recyclerView, RecyclerView.B b2);

        public float m(RecyclerView.B b2) {
            return 0.5f;
        }

        public boolean o(RecyclerView recyclerView, RecyclerView.B b2) {
            return (f(recyclerView, b2) & 16711680) != 0;
        }

        public int p(RecyclerView recyclerView, int i2, int i3, int i4, long j2) {
            int iSignum = (int) (((int) (((int) Math.signum(i3)) * i(recyclerView) * f8272d.getInterpolation(Math.min(1.0f, (Math.abs(i3) * 1.0f) / i2)))) * f8271c.getInterpolation(j2 <= 2000 ? j2 / 2000.0f : 1.0f));
            return iSignum == 0 ? i3 > 0 ? 1 : -1 : iSignum;
        }

        public boolean q() {
            return true;
        }

        public abstract boolean r();

        public void u(Canvas canvas, RecyclerView recyclerView, RecyclerView.B b2, float f2, float f3, int i2, boolean z2) {
            f8270b.b(canvas, recyclerView, b2.f1354a, f2, f3, i2, z2);
        }

        public void v(Canvas canvas, RecyclerView recyclerView, RecyclerView.B b2, float f2, float f3, int i2, boolean z2) {
            f8270b.c(canvas, recyclerView, b2.f1354a, f2, f3, i2, z2);
        }

        public void w(Canvas canvas, RecyclerView recyclerView, RecyclerView.B b2, List list, int i2, float f2, float f3) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                h hVar = (h) list.get(i3);
                hVar.i();
                int iSave = canvas.save();
                u(canvas, recyclerView, hVar.f8279e, hVar.f8284j, hVar.f8285k, hVar.f8280f, false);
                canvas.restoreToCount(iSave);
            }
            if (b2 != null) {
                int iSave2 = canvas.save();
                u(canvas, recyclerView, b2, f2, f3, i2, true);
                canvas.restoreToCount(iSave2);
            }
        }

        public void x(Canvas canvas, RecyclerView recyclerView, RecyclerView.B b2, List list, int i2, float f2, float f3) {
            int size = list.size();
            boolean z2 = false;
            for (int i3 = 0; i3 < size; i3++) {
                h hVar = (h) list.get(i3);
                int iSave = canvas.save();
                v(canvas, recyclerView, hVar.f8279e, hVar.f8284j, hVar.f8285k, hVar.f8280f, false);
                canvas.restoreToCount(iSave);
            }
            if (b2 != null) {
                int iSave2 = canvas.save();
                v(canvas, recyclerView, b2, f2, f3, i2, true);
                canvas.restoreToCount(iSave2);
            }
            for (int i4 = size - 1; i4 >= 0; i4--) {
                h hVar2 = (h) list.get(i4);
                boolean z3 = hVar2.f8287m;
                if (z3 && !hVar2.f8283i) {
                    list.remove(i4);
                } else if (!z3) {
                    z2 = true;
                }
            }
            if (z2) {
                recyclerView.invalidate();
            }
        }

        public abstract boolean y(RecyclerView recyclerView, RecyclerView.B b2, RecyclerView.B b3);

        /* JADX WARN: Multi-variable type inference failed */
        public void z(RecyclerView recyclerView, RecyclerView.B b2, int i2, RecyclerView.B b3, int i3, int i4, int i5) {
            RecyclerView.n layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof i) {
                ((i) layoutManager).a(b2.f1354a, b3.f1354a, i4, i5);
                return;
            }
            if (layoutManager.k()) {
                if (layoutManager.N(b3.f1354a) <= recyclerView.getPaddingLeft()) {
                    recyclerView.T0(i3);
                }
                if (layoutManager.Q(b3.f1354a) >= recyclerView.getWidth() - recyclerView.getPaddingRight()) {
                    recyclerView.T0(i3);
                }
            }
            if (layoutManager.l()) {
                if (layoutManager.R(b3.f1354a) <= recyclerView.getPaddingTop()) {
                    recyclerView.T0(i3);
                }
                if (layoutManager.L(b3.f1354a) >= recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                    recyclerView.T0(i3);
                }
            }
        }

        public float l(float f2) {
            return f2;
        }

        public float n(float f2) {
            return f2;
        }
    }

    /* JADX INFO: renamed from: y.a$h */
    public class h implements InterfaceC0097b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final float f8275a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final float f8276b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final float f8277c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final float f8278d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final RecyclerView.B f8279e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final int f8280f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final r f8281g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final int f8282h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f8283i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public float f8284j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public float f8285k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public boolean f8286l = false;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public boolean f8287m = false;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public float f8288n;

        /* JADX INFO: renamed from: y.a$h$a, reason: collision with other inner class name */
        public class C0074a implements InterfaceC0099d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ C0523a f8290a;

            public C0074a(C0523a c0523a) {
                this.f8290a = c0523a;
            }

            @Override // b.InterfaceC0099d
            public void a(r rVar) {
                h.this.g(rVar.c());
            }
        }

        public h(RecyclerView.B b2, int i2, int i3, float f2, float f3, float f4, float f5) {
            this.f8280f = i3;
            this.f8282h = i2;
            this.f8279e = b2;
            this.f8275a = f2;
            this.f8276b = f3;
            this.f8277c = f4;
            this.f8278d = f5;
            r rVarB = AbstractC0096a.b();
            this.f8281g = rVarB;
            rVarB.b(new C0074a(C0523a.this));
            rVarB.e(b2.f1354a);
            rVarB.d(this);
            g(0.0f);
        }

        @Override // b.InterfaceC0097b
        public void a(r rVar) {
            if (!this.f8287m) {
                this.f8279e.N(true);
            }
            this.f8287m = true;
        }

        @Override // b.InterfaceC0097b
        public void d(r rVar) {
            g(1.0f);
        }

        public void e() {
            this.f8281g.cancel();
        }

        public void f(long j2) {
            this.f8281g.a(j2);
        }

        public void g(float f2) {
            this.f8288n = f2;
        }

        public void h() {
            this.f8279e.N(false);
            this.f8281g.start();
        }

        public void i() {
            float f2 = this.f8275a;
            float f3 = this.f8277c;
            if (f2 == f3) {
                this.f8284j = AbstractC0285k0.r(this.f8279e.f1354a);
            } else {
                this.f8284j = f2 + (this.f8288n * (f3 - f2));
            }
            float f4 = this.f8276b;
            float f5 = this.f8278d;
            if (f4 == f5) {
                this.f8285k = AbstractC0285k0.s(this.f8279e.f1354a);
            } else {
                this.f8285k = f4 + (this.f8288n * (f5 - f4));
            }
        }

        @Override // b.InterfaceC0097b
        public void b(r rVar) {
        }

        @Override // b.InterfaceC0097b
        public void c(r rVar) {
        }
    }

    @Override // android.support.v7.widget.RecyclerView.p
    public void a(View view) {
    }
}

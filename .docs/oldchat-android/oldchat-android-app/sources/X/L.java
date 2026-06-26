package x;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;

/* JADX INFO: loaded from: classes.dex */
public abstract class L implements View.OnTouchListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f8028a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f8029b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f8030c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final View f8031d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Runnable f8032e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Runnable f8033f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f8034g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f8035h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int[] f8036i = new int[2];

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewParent parent = L.this.f8031d.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            L.this.e();
        }
    }

    public L(View view) {
        this.f8031d = view;
        this.f8028a = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        int tapTimeout = ViewConfiguration.getTapTimeout();
        this.f8029b = tapTimeout;
        this.f8030c = (tapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
    }

    public static boolean h(View view, float f2, float f3, float f4) {
        float f5 = -f4;
        return f2 >= f5 && f3 >= f5 && f2 < ((float) (view.getRight() - view.getLeft())) + f4 && f3 < ((float) (view.getBottom() - view.getTop())) + f4;
    }

    public final void a() {
        Runnable runnable = this.f8033f;
        if (runnable != null) {
            this.f8031d.removeCallbacks(runnable);
        }
        Runnable runnable2 = this.f8032e;
        if (runnable2 != null) {
            this.f8031d.removeCallbacks(runnable2);
        }
    }

    public abstract w.z b();

    public abstract boolean c();

    public boolean d() {
        w.z zVarB = b();
        if (zVarB == null || !zVarB.h()) {
            return true;
        }
        zVarB.dismiss();
        return true;
    }

    public void e() {
        a();
        View view = this.f8031d;
        if (view.isEnabled() && !view.isLongClickable() && c()) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            long jUptimeMillis = SystemClock.uptimeMillis();
            MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
            view.onTouchEvent(motionEventObtain);
            motionEventObtain.recycle();
            this.f8034g = true;
        }
    }

    public final boolean f(MotionEvent motionEvent) {
        K k2;
        View view = this.f8031d;
        w.z zVarB = b();
        if (zVarB == null || !zVarB.h() || (k2 = (K) zVarB.i()) == null || !k2.isShown()) {
            return false;
        }
        MotionEvent motionEventObtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
        i(view, motionEventObtainNoHistory);
        j(k2, motionEventObtainNoHistory);
        boolean zK = k2.k(motionEventObtainNoHistory, this.f8035h);
        motionEventObtainNoHistory.recycle();
        int iB = k.W.b(motionEvent);
        return zK && (iB != 1 && iB != 3);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean g(android.view.MotionEvent r6) {
        /*
            r5 = this;
            android.view.View r0 = r5.f8031d
            boolean r1 = r0.isEnabled()
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            int r1 = k.W.b(r6)
            if (r1 == 0) goto L41
            r3 = 1
            if (r1 == r3) goto L3d
            r4 = 2
            if (r1 == r4) goto L1a
            r6 = 3
            if (r1 == r6) goto L3d
            goto L6d
        L1a:
            int r1 = r5.f8035h
            int r1 = r6.findPointerIndex(r1)
            if (r1 < 0) goto L6d
            float r4 = r6.getX(r1)
            float r6 = r6.getY(r1)
            float r1 = r5.f8028a
            boolean r6 = h(r0, r4, r6, r1)
            if (r6 != 0) goto L6d
            r5.a()
            android.view.ViewParent r6 = r0.getParent()
            r6.requestDisallowInterceptTouchEvent(r3)
            return r3
        L3d:
            r5.a()
            goto L6d
        L41:
            int r6 = r6.getPointerId(r2)
            r5.f8035h = r6
            java.lang.Runnable r6 = r5.f8032e
            if (r6 != 0) goto L52
            x.L$a r6 = new x.L$a
            r6.<init>()
            r5.f8032e = r6
        L52:
            java.lang.Runnable r6 = r5.f8032e
            int r1 = r5.f8029b
            long r3 = (long) r1
            r0.postDelayed(r6, r3)
            java.lang.Runnable r6 = r5.f8033f
            if (r6 != 0) goto L65
            x.L$b r6 = new x.L$b
            r6.<init>()
            r5.f8033f = r6
        L65:
            java.lang.Runnable r6 = r5.f8033f
            int r1 = r5.f8030c
            long r3 = (long) r1
            r0.postDelayed(r6, r3)
        L6d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: x.L.g(android.view.MotionEvent):boolean");
    }

    public final boolean i(View view, MotionEvent motionEvent) {
        view.getLocationOnScreen(this.f8036i);
        motionEvent.offsetLocation(r0[0], r0[1]);
        return true;
    }

    public final boolean j(View view, MotionEvent motionEvent) {
        view.getLocationOnScreen(this.f8036i);
        motionEvent.offsetLocation(-r0[0], -r0[1]);
        return true;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z2;
        boolean z3 = this.f8034g;
        if (z3) {
            z2 = f(motionEvent) || !d();
        } else {
            z2 = g(motionEvent) && c();
            if (z2) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                this.f8031d.onTouchEvent(motionEventObtain);
                motionEventObtain.recycle();
            }
        }
        this.f8034g = z2;
        return z2 || z3;
    }
}

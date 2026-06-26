package k;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

/* JADX INFO: renamed from: k.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0302q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f5714a;

    /* JADX INFO: renamed from: k.q$a */
    public interface a {
        boolean a(MotionEvent motionEvent);
    }

    /* JADX INFO: renamed from: k.q$b */
    public static class b implements a {

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public static final int f5715v = ViewConfiguration.getLongPressTimeout();

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public static final int f5716w = ViewConfiguration.getTapTimeout();

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public static final int f5717x = ViewConfiguration.getDoubleTapTimeout();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f5718a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f5719b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f5720c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f5721d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Handler f5722e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final GestureDetector.OnGestureListener f5723f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public GestureDetector.OnDoubleTapListener f5724g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f5725h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f5726i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public boolean f5727j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public boolean f5728k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public boolean f5729l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public MotionEvent f5730m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public MotionEvent f5731n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public boolean f5732o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public float f5733p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public float f5734q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public float f5735r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public float f5736s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public boolean f5737t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public VelocityTracker f5738u;

        public b(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
            if (handler != null) {
                this.f5722e = new a(handler);
            } else {
                this.f5722e = new a();
            }
            this.f5723f = onGestureListener;
            if (onGestureListener instanceof GestureDetector.OnDoubleTapListener) {
                g((GestureDetector.OnDoubleTapListener) onGestureListener);
            }
            e(context);
        }

        @Override // k.C0302q.a
        public boolean a(MotionEvent motionEvent) {
            boolean zOnDoubleTap;
            MotionEvent motionEvent2;
            boolean zOnFling;
            GestureDetector.OnDoubleTapListener onDoubleTapListener;
            int action = motionEvent.getAction();
            if (this.f5738u == null) {
                this.f5738u = VelocityTracker.obtain();
            }
            this.f5738u.addMovement(motionEvent);
            int i2 = action & 255;
            boolean z2 = i2 == 6;
            int iA = z2 ? W.a(motionEvent) : -1;
            int pointerCount = motionEvent.getPointerCount();
            float x2 = 0.0f;
            float y2 = 0.0f;
            for (int i3 = 0; i3 < pointerCount; i3++) {
                if (iA != i3) {
                    x2 += motionEvent.getX(i3);
                    y2 += motionEvent.getY(i3);
                }
            }
            float f2 = z2 ? pointerCount - 1 : pointerCount;
            float f3 = x2 / f2;
            float f4 = y2 / f2;
            if (i2 == 0) {
                if (this.f5724g == null) {
                    zOnDoubleTap = false;
                } else {
                    boolean zHasMessages = this.f5722e.hasMessages(3);
                    if (zHasMessages) {
                        this.f5722e.removeMessages(3);
                    }
                    MotionEvent motionEvent3 = this.f5730m;
                    if (motionEvent3 == null || (motionEvent2 = this.f5731n) == null || !zHasMessages || !f(motionEvent3, motionEvent2, motionEvent)) {
                        this.f5722e.sendEmptyMessageDelayed(3, f5717x);
                        zOnDoubleTap = false;
                    } else {
                        this.f5732o = true;
                        zOnDoubleTap = this.f5724g.onDoubleTap(this.f5730m) | this.f5724g.onDoubleTapEvent(motionEvent);
                    }
                }
                this.f5733p = f3;
                this.f5735r = f3;
                this.f5734q = f4;
                this.f5736s = f4;
                MotionEvent motionEvent4 = this.f5730m;
                if (motionEvent4 != null) {
                    motionEvent4.recycle();
                }
                this.f5730m = MotionEvent.obtain(motionEvent);
                this.f5728k = true;
                this.f5729l = true;
                this.f5725h = true;
                this.f5727j = false;
                this.f5726i = false;
                if (this.f5737t) {
                    this.f5722e.removeMessages(2);
                    this.f5722e.sendEmptyMessageAtTime(2, this.f5730m.getDownTime() + ((long) f5716w) + ((long) f5715v));
                }
                this.f5722e.sendEmptyMessageAtTime(1, this.f5730m.getDownTime() + ((long) f5716w));
                return zOnDoubleTap | this.f5723f.onDown(motionEvent);
            }
            if (i2 == 1) {
                this.f5725h = false;
                MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
                if (this.f5732o) {
                    zOnFling = this.f5724g.onDoubleTapEvent(motionEvent);
                } else {
                    if (this.f5727j) {
                        this.f5722e.removeMessages(3);
                        this.f5727j = false;
                    } else if (this.f5728k) {
                        boolean zOnSingleTapUp = this.f5723f.onSingleTapUp(motionEvent);
                        if (this.f5726i && (onDoubleTapListener = this.f5724g) != null) {
                            onDoubleTapListener.onSingleTapConfirmed(motionEvent);
                        }
                        zOnFling = zOnSingleTapUp;
                    } else {
                        VelocityTracker velocityTracker = this.f5738u;
                        int pointerId = motionEvent.getPointerId(0);
                        velocityTracker.computeCurrentVelocity(1000, this.f5721d);
                        float fB = AbstractC0279i0.b(velocityTracker, pointerId);
                        float fA = AbstractC0279i0.a(velocityTracker, pointerId);
                        if (Math.abs(fB) > this.f5720c || Math.abs(fA) > this.f5720c) {
                            zOnFling = this.f5723f.onFling(this.f5730m, motionEvent, fA, fB);
                        }
                    }
                    zOnFling = false;
                }
                MotionEvent motionEvent5 = this.f5731n;
                if (motionEvent5 != null) {
                    motionEvent5.recycle();
                }
                this.f5731n = motionEventObtain;
                VelocityTracker velocityTracker2 = this.f5738u;
                if (velocityTracker2 != null) {
                    velocityTracker2.recycle();
                    this.f5738u = null;
                }
                this.f5732o = false;
                this.f5726i = false;
                this.f5722e.removeMessages(1);
                this.f5722e.removeMessages(2);
            } else {
                if (i2 != 2) {
                    if (i2 == 3) {
                        b();
                        return false;
                    }
                    if (i2 == 5) {
                        this.f5733p = f3;
                        this.f5735r = f3;
                        this.f5734q = f4;
                        this.f5736s = f4;
                        c();
                        return false;
                    }
                    if (i2 != 6) {
                        return false;
                    }
                    this.f5733p = f3;
                    this.f5735r = f3;
                    this.f5734q = f4;
                    this.f5736s = f4;
                    this.f5738u.computeCurrentVelocity(1000, this.f5721d);
                    int iA2 = W.a(motionEvent);
                    int pointerId2 = motionEvent.getPointerId(iA2);
                    float fA2 = AbstractC0279i0.a(this.f5738u, pointerId2);
                    float fB2 = AbstractC0279i0.b(this.f5738u, pointerId2);
                    for (int i4 = 0; i4 < pointerCount; i4++) {
                        if (i4 != iA2) {
                            int pointerId3 = motionEvent.getPointerId(i4);
                            if ((AbstractC0279i0.a(this.f5738u, pointerId3) * fA2) + (AbstractC0279i0.b(this.f5738u, pointerId3) * fB2) < 0.0f) {
                                this.f5738u.clear();
                                return false;
                            }
                        }
                    }
                    return false;
                }
                if (this.f5727j) {
                    return false;
                }
                float f5 = this.f5733p - f3;
                float f6 = this.f5734q - f4;
                if (this.f5732o) {
                    return this.f5724g.onDoubleTapEvent(motionEvent);
                }
                if (!this.f5728k) {
                    if (Math.abs(f5) < 1.0f && Math.abs(f6) < 1.0f) {
                        return false;
                    }
                    boolean zOnScroll = this.f5723f.onScroll(this.f5730m, motionEvent, f5, f6);
                    this.f5733p = f3;
                    this.f5734q = f4;
                    return zOnScroll;
                }
                int i5 = (int) (f3 - this.f5735r);
                int i6 = (int) (f4 - this.f5736s);
                int i7 = (i5 * i5) + (i6 * i6);
                if (i7 > this.f5718a) {
                    zOnFling = this.f5723f.onScroll(this.f5730m, motionEvent, f5, f6);
                    this.f5733p = f3;
                    this.f5734q = f4;
                    this.f5728k = false;
                    this.f5722e.removeMessages(3);
                    this.f5722e.removeMessages(1);
                    this.f5722e.removeMessages(2);
                } else {
                    zOnFling = false;
                }
                if (i7 > this.f5718a) {
                    this.f5729l = false;
                }
            }
            return zOnFling;
        }

        public final void b() {
            this.f5722e.removeMessages(1);
            this.f5722e.removeMessages(2);
            this.f5722e.removeMessages(3);
            this.f5738u.recycle();
            this.f5738u = null;
            this.f5732o = false;
            this.f5725h = false;
            this.f5728k = false;
            this.f5729l = false;
            this.f5726i = false;
            if (this.f5727j) {
                this.f5727j = false;
            }
        }

        public final void c() {
            this.f5722e.removeMessages(1);
            this.f5722e.removeMessages(2);
            this.f5722e.removeMessages(3);
            this.f5732o = false;
            this.f5728k = false;
            this.f5729l = false;
            this.f5726i = false;
            if (this.f5727j) {
                this.f5727j = false;
            }
        }

        public void d() {
            this.f5722e.removeMessages(3);
            this.f5726i = false;
            this.f5727j = true;
            this.f5723f.onLongPress(this.f5730m);
        }

        public final void e(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null");
            }
            if (this.f5723f == null) {
                throw new IllegalArgumentException("OnGestureListener must not be null");
            }
            this.f5737t = true;
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            int scaledTouchSlop = viewConfiguration.getScaledTouchSlop();
            int scaledDoubleTapSlop = viewConfiguration.getScaledDoubleTapSlop();
            this.f5720c = viewConfiguration.getScaledMinimumFlingVelocity();
            this.f5721d = viewConfiguration.getScaledMaximumFlingVelocity();
            this.f5718a = scaledTouchSlop * scaledTouchSlop;
            this.f5719b = scaledDoubleTapSlop * scaledDoubleTapSlop;
        }

        public final boolean f(MotionEvent motionEvent, MotionEvent motionEvent2, MotionEvent motionEvent3) {
            if (!this.f5729l || motionEvent3.getEventTime() - motionEvent2.getEventTime() > f5717x) {
                return false;
            }
            int x2 = ((int) motionEvent.getX()) - ((int) motionEvent3.getX());
            int y2 = ((int) motionEvent.getY()) - ((int) motionEvent3.getY());
            return (x2 * x2) + (y2 * y2) < this.f5719b;
        }

        public void g(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
            this.f5724g = onDoubleTapListener;
        }

        /* JADX INFO: renamed from: k.q$b$a */
        public class a extends Handler {
            public a() {
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i2 = message.what;
                if (i2 == 1) {
                    b bVar = b.this;
                    bVar.f5723f.onShowPress(bVar.f5730m);
                    return;
                }
                if (i2 == 2) {
                    b.this.d();
                    return;
                }
                if (i2 != 3) {
                    throw new RuntimeException("Unknown message " + message);
                }
                b bVar2 = b.this;
                GestureDetector.OnDoubleTapListener onDoubleTapListener = bVar2.f5724g;
                if (onDoubleTapListener != null) {
                    if (bVar2.f5725h) {
                        bVar2.f5726i = true;
                    } else {
                        onDoubleTapListener.onSingleTapConfirmed(bVar2.f5730m);
                    }
                }
            }

            public a(Handler handler) {
                super(handler.getLooper());
            }
        }
    }

    /* JADX INFO: renamed from: k.q$c */
    public static class c implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final GestureDetector f5740a;

        public c(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
            this.f5740a = new GestureDetector(context, onGestureListener, handler);
        }

        @Override // k.C0302q.a
        public boolean a(MotionEvent motionEvent) {
            return this.f5740a.onTouchEvent(motionEvent);
        }
    }

    public C0302q(Context context, GestureDetector.OnGestureListener onGestureListener) {
        this(context, onGestureListener, null);
    }

    public boolean a(MotionEvent motionEvent) {
        return this.f5714a.a(motionEvent);
    }

    public C0302q(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
        if (Build.VERSION.SDK_INT > 17) {
            this.f5714a = new c(context, onGestureListener, handler);
        } else {
            this.f5714a = new b(context, onGestureListener, handler);
        }
    }
}

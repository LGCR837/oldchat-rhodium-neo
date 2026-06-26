package com.im.oldchat.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import l0.AbstractC0380g;

/* JADX INFO: loaded from: classes.dex */
public abstract class U extends V {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public j0.k f4155k;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final j.f f4153i = new j.f(400);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f4154j = false;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f4156l = false;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            U.this.V();
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f4158a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public o0.C f4159b;

        public b() {
        }

        public /* synthetic */ b(a aVar) {
            this();
        }
    }

    public static class c extends RecyclerView.B {

        /* JADX INFO: renamed from: A, reason: collision with root package name */
        public Runnable f4160A;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public final View f4161r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public final View f4162s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public final View f4163t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public final TextView f4164u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public final View f4165v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public final View f4166w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public final View f4167x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public final o0.Z f4168y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public String f4169z;

        public c(View view) {
            super(view);
            this.f4161r = view.findViewById(f0.f.ke);
            this.f4162s = view.findViewById(f0.f.l6);
            this.f4163t = view.findViewById(f0.f.oe);
            this.f4164u = (TextView) view.findViewById(f0.f.ae);
            this.f4165v = view.findViewById(f0.f.le);
            this.f4166w = view.findViewById(f0.f.me);
            this.f4167x = view.findViewById(f0.f.ne);
            this.f4168y = new o0.Z();
        }

        public void T() {
            X();
            View view = this.f4163t;
            if (view != null) {
                view.clearAnimation();
                this.f4163t.setVisibility(0);
            }
            TextView textView = this.f4164u;
            if (textView != null) {
                textView.clearAnimation();
                this.f4164u.setVisibility(8);
            }
            this.f4169z = null;
            Runnable runnable = this.f4160A;
            if (runnable != null) {
                this.f1354a.removeCallbacks(runnable);
                this.f4160A = null;
            }
        }

        public void U() {
            T();
        }

        public void V(String str, String str2) {
            X();
            if (this.f4163t == null || this.f4164u == null) {
                return;
            }
            if (str2 == null) {
                str2 = "";
            }
            if (str2.equals(this.f4169z)) {
                this.f4163t.setVisibility(4);
                this.f4164u.setVisibility(0);
                return;
            }
            this.f4169z = str2;
            this.f4163t.setVisibility(0);
            this.f4164u.setVisibility(0);
            TextView textView = this.f4164u;
            if (str == null) {
                str = "";
            }
            textView.setText(str);
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(200L);
            alphaAnimation.setFillAfter(true);
            AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation2.setDuration(200L);
            alphaAnimation2.setFillAfter(true);
            this.f4163t.startAnimation(alphaAnimation);
            int width = this.f4163t.getWidth();
            if (width <= 0) {
                this.f4163t.measure(0, 0);
                width = this.f4163t.getMeasuredWidth();
            }
            AnimationSet animationSet = new AnimationSet(false);
            animationSet.setFillAfter(true);
            animationSet.addAnimation(alphaAnimation2);
            if (width > 0) {
                TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, -width, 0.0f, 0.0f);
                translateAnimation.setDuration(200L);
                translateAnimation.setFillAfter(true);
                animationSet.addAnimation(translateAnimation);
            }
            this.f4164u.startAnimation(animationSet);
        }

        public void W() {
            o0.Z z2 = this.f4168y;
            if (z2 != null) {
                z2.b(this.f4165v, this.f4166w, this.f4167x);
            }
        }

        public void X() {
            o0.Z z2 = this.f4168y;
            if (z2 != null) {
                z2.c();
            }
        }
    }

    public static class d extends RecyclerView.B {

        /* JADX INFO: renamed from: A, reason: collision with root package name */
        public final ImageView f4170A;

        /* JADX INFO: renamed from: B, reason: collision with root package name */
        public final LinearLayout f4171B;

        /* JADX INFO: renamed from: C, reason: collision with root package name */
        public final TextView f4172C;

        /* JADX INFO: renamed from: D, reason: collision with root package name */
        public final TextView f4173D;

        /* JADX INFO: renamed from: E, reason: collision with root package name */
        public final ImageView f4174E;

        /* JADX INFO: renamed from: F, reason: collision with root package name */
        public final LinearLayout f4175F;

        /* JADX INFO: renamed from: G, reason: collision with root package name */
        public final TextView f4176G;

        /* JADX INFO: renamed from: H, reason: collision with root package name */
        public final ImageView f4177H;

        /* JADX INFO: renamed from: I, reason: collision with root package name */
        public final LinearLayout f4178I;

        /* JADX INFO: renamed from: J, reason: collision with root package name */
        public final TextView f4179J;

        /* JADX INFO: renamed from: K, reason: collision with root package name */
        public final ImageView f4180K;

        /* JADX INFO: renamed from: L, reason: collision with root package name */
        public final LinearLayout f4181L;

        /* JADX INFO: renamed from: M, reason: collision with root package name */
        public final ImageView f4182M;

        /* JADX INFO: renamed from: N, reason: collision with root package name */
        public final TextView f4183N;

        /* JADX INFO: renamed from: O, reason: collision with root package name */
        public final TextView f4184O;

        /* JADX INFO: renamed from: P, reason: collision with root package name */
        public final TextView f4185P;

        /* JADX INFO: renamed from: Q, reason: collision with root package name */
        public final ImageView f4186Q;

        /* JADX INFO: renamed from: R, reason: collision with root package name */
        public final View f4187R;

        /* JADX INFO: renamed from: S, reason: collision with root package name */
        public final ImageView f4188S;

        /* JADX INFO: renamed from: T, reason: collision with root package name */
        public final View f4189T;

        /* JADX INFO: renamed from: U, reason: collision with root package name */
        public final ImageView f4190U;

        /* JADX INFO: renamed from: V, reason: collision with root package name */
        public final LinearLayout f4191V;

        /* JADX INFO: renamed from: W, reason: collision with root package name */
        public final ImageView f4192W;

        /* JADX INFO: renamed from: X, reason: collision with root package name */
        public final TextView f4193X;

        /* JADX INFO: renamed from: Y, reason: collision with root package name */
        public final TextView f4194Y;

        /* JADX INFO: renamed from: Z, reason: collision with root package name */
        public final ProgressBar f4195Z;

        /* JADX INFO: renamed from: a0, reason: collision with root package name */
        public final TextView f4196a0;

        /* JADX INFO: renamed from: b0, reason: collision with root package name */
        public final LinearLayout f4197b0;

        /* JADX INFO: renamed from: c0, reason: collision with root package name */
        public final int f4198c0;

        /* JADX INFO: renamed from: d0, reason: collision with root package name */
        public final int f4199d0;

        /* JADX INFO: renamed from: e0, reason: collision with root package name */
        public final int f4200e0;

        /* JADX INFO: renamed from: f0, reason: collision with root package name */
        public final int f4201f0;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public final TextView f4202r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public final LinearLayout f4203s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public final LinearLayout f4204t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public final ImageView f4205u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public final TextView f4206v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public final TextView f4207w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public final TextView f4208x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public final View f4209y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public final ImageView f4210z;

        public d(View view) {
            super(view);
            this.f4197b0 = (LinearLayout) view.findViewById(f0.f.h7);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(f0.f.g7);
            this.f4203s = linearLayout;
            this.f4202r = (TextView) view.findViewById(f0.f.ya);
            this.f4204t = (LinearLayout) view.findViewById(f0.f.Q7);
            this.f4205u = (ImageView) view.findViewById(f0.f.g6);
            this.f4206v = (TextView) view.findViewById(f0.f.td);
            this.f4207w = (TextView) view.findViewById(f0.f.sd);
            this.f4208x = (TextView) view.findViewById(f0.f.hd);
            this.f4209y = view.findViewById(f0.f.c5);
            this.f4210z = (ImageView) view.findViewById(f0.f.f6);
            this.f4170A = (ImageView) view.findViewById(f0.f.i6);
            this.f4171B = (LinearLayout) view.findViewById(f0.f.K7);
            this.f4172C = (TextView) view.findViewById(f0.f.Fc);
            this.f4173D = (TextView) view.findViewById(f0.f.Dc);
            this.f4174E = (ImageView) view.findViewById(f0.f.Y5);
            this.f4175F = (LinearLayout) view.findViewById(f0.f.j7);
            this.f4176G = (TextView) view.findViewById(f0.f.za);
            this.f4177H = (ImageView) view.findViewById(f0.f.E5);
            this.f4178I = (LinearLayout) view.findViewById(f0.f.i7);
            this.f4179J = (TextView) view.findViewById(f0.f.Aa);
            this.f4180K = (ImageView) view.findViewById(f0.f.F5);
            this.f4181L = (LinearLayout) view.findViewById(f0.f.P7);
            this.f4182M = (ImageView) view.findViewById(f0.f.b6);
            this.f4183N = (TextView) view.findViewById(f0.f.ad);
            this.f4184O = (TextView) view.findViewById(f0.f.Lc);
            this.f4185P = (TextView) view.findViewById(f0.f.Wc);
            this.f4186Q = (ImageView) view.findViewById(f0.f.d6);
            this.f4187R = view.findViewById(f0.f.k5);
            this.f4188S = (ImageView) view.findViewById(f0.f.D5);
            this.f4189T = view.findViewById(f0.f.q7);
            this.f4190U = (ImageView) view.findViewById(f0.f.o6);
            this.f4191V = (LinearLayout) view.findViewById(f0.f.ze);
            this.f4192W = (ImageView) view.findViewById(f0.f.p6);
            this.f4193X = (TextView) view.findViewById(f0.f.ge);
            this.f4194Y = (TextView) view.findViewById(f0.f.he);
            this.f4195Z = (ProgressBar) view.findViewById(f0.f.H7);
            this.f4196a0 = (TextView) view.findViewById(f0.f.Ba);
            this.f4198c0 = linearLayout != null ? linearLayout.getPaddingLeft() : 0;
            this.f4199d0 = linearLayout != null ? linearLayout.getPaddingTop() : 0;
            this.f4200e0 = linearLayout != null ? linearLayout.getPaddingRight() : 0;
            this.f4201f0 = linearLayout != null ? linearLayout.getPaddingBottom() : 0;
        }
    }

    public void S(d dVar) {
        float f2 = this.f4237g;
        TextView textView = dVar.f4202r;
        if (textView != null) {
            textView.setTextSize(2, 16.0f * f2);
        }
        TextView textView2 = dVar.f4172C;
        if (textView2 != null) {
            textView2.setTextSize(2, f2 * 12.0f);
        }
        TextView textView3 = dVar.f4173D;
        if (textView3 != null) {
            textView3.setTextSize(2, f2 * 13.0f);
        }
        TextView textView4 = dVar.f4193X;
        if (textView4 != null) {
            textView4.setTextSize(2, 14.0f * f2);
        }
        TextView textView5 = dVar.f4194Y;
        if (textView5 != null) {
            textView5.setTextSize(2, 13.0f * f2);
        }
        TextView textView6 = dVar.f4206v;
        if (textView6 != null) {
            textView6.setTextSize(2, 15.0f * f2);
        }
        TextView textView7 = dVar.f4207w;
        if (textView7 != null) {
            textView7.setTextSize(2, f2 * 12.0f);
        }
        TextView textView8 = dVar.f4208x;
        if (textView8 != null) {
            textView8.setTextSize(2, f2 * 12.0f);
        }
    }

    public void T(c cVar, j0.k kVar) {
        if (cVar == null) {
            return;
        }
        View view = cVar.f4161r;
        if (view != null) {
            view.setVisibility(8);
        } else {
            View view2 = cVar.f4162s;
            if (view2 != null) {
                view2.setVisibility(8);
            }
        }
        if (kVar == null) {
            cVar.U();
            cVar.W();
        } else {
            String strD = AbstractC0380g.d(kVar);
            String str = kVar.f5550a;
            cVar.V(strD, (str == null || str.length() <= 0) ? String.valueOf(kVar.f5558i) : kVar.f5550a);
            Y(cVar);
        }
    }

    public final void U(String str, String str2, o0.C c2) {
        if (str == null) {
            return;
        }
        b bVar = new b(null);
        bVar.f4158a = str2;
        bVar.f4159b = c2;
        this.f4153i.e(str, bVar);
    }

    public final void V() {
        if (this.f4156l) {
            this.f4156l = false;
            this.f4155k = null;
            g();
        }
    }

    public o0.C W(j0.k kVar) {
        b bVar;
        o0.C c2;
        if (kVar == null) {
            return new o0.C();
        }
        String str = kVar.f5550a;
        String str2 = (str == null || str.length() <= 0) ? kVar.f5553d : kVar.f5550a;
        if (str2 != null && (bVar = (b) this.f4153i.d(str2)) != null && X(bVar.f4158a, kVar.f5553d) && (c2 = bVar.f4159b) != null) {
            return c2;
        }
        o0.C cB = o0.C.b(kVar.f5553d);
        U(str2, kVar.f5553d, cB);
        return cB;
    }

    public final boolean X(String str, String str2) {
        return str == null ? str2 == null : str.equals(str2);
    }

    public final void Y(c cVar) {
        if (cVar == null) {
            return;
        }
        cVar.f1354a.removeCallbacks(cVar.f4160A);
        a aVar = new a();
        cVar.f4160A = aVar;
        cVar.f1354a.postDelayed(aVar, 200L);
    }

    @Override // android.support.v7.widget.RecyclerView.g
    public int c() {
        return this.f4156l ? this.f4233c.size() : this.f4233c.size() + (this.f4154j ? 1 : 0);
    }
}

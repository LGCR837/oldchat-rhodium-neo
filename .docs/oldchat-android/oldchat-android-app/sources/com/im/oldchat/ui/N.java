package com.im.oldchat.ui;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.im.oldchat.ui.G;
import com.im.oldchat.ui.P;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: loaded from: classes.dex */
public abstract class N extends BaseAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f3455a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f3456b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f3457c;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final P f3464j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public G.a f3465k;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public j0.i f3468n;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f3470p;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public G.b f3473s;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3458d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Map f3459e = new HashMap();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Map f3460f = new HashMap();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Map f3461g = new HashMap();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Map f3462h = new HashMap();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Map f3463i = new HashMap();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f3466l = 1.0f;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final LinkedHashSet f3467m = new LinkedHashSet();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f3469o = false;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final HashSet f3471q = new HashSet();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final HashSet f3472r = new HashSet();

    public class a implements P.j {
        public a() {
        }

        @Override // com.im.oldchat.ui.P.j
        public void a() {
            N.this.notifyDataSetChanged();
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f3475a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public o0.C f3476b;
    }

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final View f3477a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final View f3478b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ImageView f3479c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final ImageView f3480d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final ImageView f3481e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final View f3482f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final TextView f3483g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final View f3484h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final View f3485i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final View f3486j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public final o0.Z f3487k = new o0.Z();

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public String f3488l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public Runnable f3489m;

        public c(View view) {
            this.f3477a = view;
            this.f3478b = view.findViewById(f0.f.ke);
            this.f3479c = (ImageView) view.findViewById(f0.f.l6);
            this.f3480d = (ImageView) view.findViewById(f0.f.m6);
            this.f3481e = (ImageView) view.findViewById(f0.f.n6);
            this.f3482f = view.findViewById(f0.f.oe);
            this.f3483g = (TextView) view.findViewById(f0.f.ae);
            this.f3484h = view.findViewById(f0.f.le);
            this.f3485i = view.findViewById(f0.f.me);
            this.f3486j = view.findViewById(f0.f.ne);
        }

        public void a() {
            View view = this.f3478b;
            if (view != null) {
                view.setVisibility(8);
            }
            ImageView imageView = this.f3479c;
            if (imageView != null) {
                imageView.setVisibility(8);
                this.f3479c.setTag(null);
            }
            ImageView imageView2 = this.f3480d;
            if (imageView2 != null) {
                imageView2.setVisibility(8);
                this.f3480d.setTag(null);
            }
            ImageView imageView3 = this.f3481e;
            if (imageView3 != null) {
                imageView3.setVisibility(8);
                this.f3481e.setTag(null);
            }
        }

        public void b() {
            f();
            View view = this.f3482f;
            if (view != null) {
                view.clearAnimation();
                this.f3482f.setVisibility(0);
            }
            TextView textView = this.f3483g;
            if (textView != null) {
                textView.clearAnimation();
                this.f3483g.setVisibility(8);
            }
            this.f3488l = null;
            Runnable runnable = this.f3489m;
            if (runnable != null) {
                View view2 = this.f3477a;
                if (view2 != null) {
                    view2.removeCallbacks(runnable);
                }
                this.f3489m = null;
            }
        }

        public void c() {
            b();
        }

        public void d(String str, String str2) {
            f();
            if (this.f3482f == null || this.f3483g == null) {
                return;
            }
            if (str2 == null) {
                str2 = "";
            }
            if (str2.equals(this.f3488l)) {
                this.f3482f.setVisibility(4);
                this.f3483g.setVisibility(0);
                return;
            }
            this.f3488l = str2;
            this.f3482f.setVisibility(0);
            this.f3483g.setVisibility(0);
            TextView textView = this.f3483g;
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
            this.f3482f.startAnimation(alphaAnimation);
            int width = this.f3482f.getWidth();
            if (width <= 0) {
                this.f3482f.measure(0, 0);
                width = this.f3482f.getMeasuredWidth();
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
            this.f3483g.startAnimation(animationSet);
        }

        public void e() {
            o0.Z z2 = this.f3487k;
            if (z2 != null) {
                z2.b(this.f3484h, this.f3485i, this.f3486j);
            }
        }

        public void f() {
            o0.Z z2 = this.f3487k;
            if (z2 != null) {
                z2.c();
            }
        }
    }

    public static class d {

        /* JADX INFO: renamed from: A, reason: collision with root package name */
        public final ImageView f3490A;

        /* JADX INFO: renamed from: B, reason: collision with root package name */
        public final View f3491B;

        /* JADX INFO: renamed from: C, reason: collision with root package name */
        public final ImageView f3492C;

        /* JADX INFO: renamed from: D, reason: collision with root package name */
        public final ImageView f3493D;

        /* JADX INFO: renamed from: E, reason: collision with root package name */
        public final LinearLayout f3494E;

        /* JADX INFO: renamed from: F, reason: collision with root package name */
        public final ImageView f3495F;

        /* JADX INFO: renamed from: G, reason: collision with root package name */
        public final TextView f3496G;

        /* JADX INFO: renamed from: H, reason: collision with root package name */
        public final TextView f3497H;

        /* JADX INFO: renamed from: I, reason: collision with root package name */
        public final ProgressBar f3498I;

        /* JADX INFO: renamed from: J, reason: collision with root package name */
        public final LinearLayout f3499J;

        /* JADX INFO: renamed from: K, reason: collision with root package name */
        public final ImageView f3500K;

        /* JADX INFO: renamed from: L, reason: collision with root package name */
        public final TextView f3501L;

        /* JADX INFO: renamed from: M, reason: collision with root package name */
        public final LinearLayout f3502M;

        /* JADX INFO: renamed from: N, reason: collision with root package name */
        public final ImageView f3503N;

        /* JADX INFO: renamed from: O, reason: collision with root package name */
        public final TextView f3504O;

        /* JADX INFO: renamed from: P, reason: collision with root package name */
        public final TextView f3505P;

        /* JADX INFO: renamed from: Q, reason: collision with root package name */
        public final LinearLayout f3506Q;

        /* JADX INFO: renamed from: R, reason: collision with root package name */
        public final int f3507R;

        /* JADX INFO: renamed from: S, reason: collision with root package name */
        public final int f3508S;

        /* JADX INFO: renamed from: T, reason: collision with root package name */
        public final int f3509T;

        /* JADX INFO: renamed from: U, reason: collision with root package name */
        public final int f3510U;

        /* JADX INFO: renamed from: V, reason: collision with root package name */
        public final int f3511V;

        /* JADX INFO: renamed from: W, reason: collision with root package name */
        public final int f3512W;

        /* JADX INFO: renamed from: X, reason: collision with root package name */
        public final int f3513X;

        /* JADX INFO: renamed from: Y, reason: collision with root package name */
        public final int f3514Y;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final LinearLayout f3515a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f3516b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final TextView f3517c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final TextView f3518d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final ImageView f3519e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final ImageView f3520f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final LinearLayout f3521g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final TextView f3522h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final LinearLayout f3523i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final LinearLayout f3524j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public final ImageView f3525k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public final TextView f3526l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public final TextView f3527m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public final TextView f3528n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public final View f3529o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public final ImageView f3530p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public final ImageView f3531q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public final LinearLayout f3532r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public final ImageView f3533s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public final TextView f3534t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public final TextView f3535u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public final TextView f3536v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public final ImageView f3537w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public final LinearLayout f3538x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public final TextView f3539y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public final TextView f3540z;

        public d(View view) {
            LinearLayout linearLayout = (LinearLayout) view.findViewById(f0.f.h7);
            this.f3506Q = linearLayout;
            this.f3515a = (LinearLayout) view.findViewById(f0.f.k8);
            this.f3516b = (TextView) view.findViewById(f0.f.Ld);
            this.f3517c = (TextView) view.findViewById(f0.f.Kd);
            this.f3518d = (TextView) view.findViewById(f0.f.Md);
            this.f3519e = (ImageView) view.findViewById(f0.f.m5);
            this.f3520f = (ImageView) view.findViewById(f0.f.n5);
            this.f3521g = (LinearLayout) view.findViewById(f0.f.l4);
            this.f3522h = (TextView) view.findViewById(f0.f.ya);
            LinearLayout linearLayout2 = (LinearLayout) view.findViewById(f0.f.g7);
            this.f3523i = linearLayout2;
            this.f3524j = (LinearLayout) view.findViewById(f0.f.Q7);
            this.f3525k = (ImageView) view.findViewById(f0.f.g6);
            this.f3526l = (TextView) view.findViewById(f0.f.td);
            this.f3527m = (TextView) view.findViewById(f0.f.sd);
            this.f3528n = (TextView) view.findViewById(f0.f.hd);
            this.f3529o = view.findViewById(f0.f.c5);
            this.f3530p = (ImageView) view.findViewById(f0.f.f6);
            this.f3531q = (ImageView) view.findViewById(f0.f.i6);
            this.f3532r = (LinearLayout) view.findViewById(f0.f.P7);
            this.f3533s = (ImageView) view.findViewById(f0.f.b6);
            this.f3534t = (TextView) view.findViewById(f0.f.ad);
            this.f3535u = (TextView) view.findViewById(f0.f.Lc);
            this.f3536v = (TextView) view.findViewById(f0.f.Wc);
            this.f3537w = (ImageView) view.findViewById(f0.f.d6);
            this.f3538x = (LinearLayout) view.findViewById(f0.f.K7);
            this.f3539y = (TextView) view.findViewById(f0.f.Fc);
            this.f3540z = (TextView) view.findViewById(f0.f.Dc);
            this.f3490A = (ImageView) view.findViewById(f0.f.Y5);
            this.f3491B = view.findViewById(f0.f.k5);
            this.f3492C = (ImageView) view.findViewById(f0.f.D5);
            this.f3493D = (ImageView) view.findViewById(f0.f.o6);
            this.f3494E = (LinearLayout) view.findViewById(f0.f.ze);
            this.f3495F = (ImageView) view.findViewById(f0.f.p6);
            this.f3496G = (TextView) view.findViewById(f0.f.ge);
            this.f3497H = (TextView) view.findViewById(f0.f.he);
            this.f3498I = (ProgressBar) view.findViewById(f0.f.H7);
            this.f3499J = (LinearLayout) view.findViewById(f0.f.j7);
            this.f3500K = (ImageView) view.findViewById(f0.f.E5);
            this.f3501L = (TextView) view.findViewById(f0.f.Gc);
            this.f3502M = (LinearLayout) view.findViewById(f0.f.i7);
            this.f3503N = (ImageView) view.findViewById(f0.f.F5);
            this.f3504O = (TextView) view.findViewById(f0.f.Hc);
            this.f3505P = (TextView) view.findViewById(f0.f.Ba);
            this.f3507R = linearLayout != null ? linearLayout.getPaddingLeft() : 0;
            this.f3508S = linearLayout != null ? linearLayout.getPaddingTop() : 0;
            this.f3509T = linearLayout != null ? linearLayout.getPaddingRight() : 0;
            this.f3510U = linearLayout != null ? linearLayout.getPaddingBottom() : 0;
            this.f3511V = linearLayout2 != null ? linearLayout2.getPaddingLeft() : 0;
            this.f3512W = linearLayout2 != null ? linearLayout2.getPaddingTop() : 0;
            this.f3513X = linearLayout2 != null ? linearLayout2.getPaddingRight() : 0;
            this.f3514Y = linearLayout2 != null ? linearLayout2.getPaddingBottom() : 0;
        }
    }

    public N(Context context, List list, String str) {
        this.f3455a = context;
        this.f3456b = list;
        this.f3457c = str;
        this.f3464j = new P(context, new a());
    }

    public void a(d dVar) {
        float f2 = this.f3466l;
        TextView textView = dVar.f3522h;
        if (textView != null) {
            textView.setTextSize(2, 16.0f * f2);
        }
        TextView textView2 = dVar.f3516b;
        if (textView2 != null) {
            textView2.setTextSize(2, f2 * 12.0f);
        }
        TextView textView3 = dVar.f3539y;
        if (textView3 != null) {
            textView3.setTextSize(2, f2 * 12.0f);
        }
        TextView textView4 = dVar.f3540z;
        if (textView4 != null) {
            textView4.setTextSize(2, f2 * 13.0f);
        }
        TextView textView5 = dVar.f3496G;
        if (textView5 != null) {
            textView5.setTextSize(2, 14.0f * f2);
        }
        TextView textView6 = dVar.f3497H;
        if (textView6 != null) {
            textView6.setTextSize(2, 13.0f * f2);
        }
        TextView textView7 = dVar.f3526l;
        if (textView7 != null) {
            textView7.setTextSize(2, 15.0f * f2);
        }
        TextView textView8 = dVar.f3527m;
        if (textView8 != null) {
            textView8.setTextSize(2, f2 * 12.0f);
        }
        TextView textView9 = dVar.f3528n;
        if (textView9 != null) {
            textView9.setTextSize(2, f2 * 12.0f);
        }
    }

    public void b(d dVar) {
        if (dVar == null) {
            return;
        }
        int iD = d();
        TextView textView = dVar.f3522h;
        if (textView != null) {
            textView.setMaxWidth(iD);
        }
        if (dVar.f3540z != null) {
            dVar.f3540z.setMaxWidth(Math.max(c(CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA), Math.min(c(240), iD + c(16))));
        }
    }

    public int c(int i2) {
        return (int) ((i2 * this.f3455a.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public final int d() {
        int iC = this.f3455a.getResources().getDisplayMetrics().widthPixels - ((((c(8) + c(42)) + c(20)) + c(36)) + c(6));
        int iC2 = c(260);
        int iC3 = c(CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA);
        return iC < iC3 ? iC3 : iC > iC2 ? iC2 : iC;
    }
}

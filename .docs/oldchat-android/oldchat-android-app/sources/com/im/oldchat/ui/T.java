package com.im.oldchat.ui;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.im.oldchat.ui.S;
import com.im.oldchat.ui.U;
import com.im.oldchat.ui.W;
import d.AbstractC0235a;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class T extends U {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f4136q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public long f4137r;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public S.m f4139t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public S.o f4140u;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final HashSet f4132m = new HashSet();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final HashSet f4133n = new HashSet();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final LinkedHashSet f4134o = new LinkedHashSet();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Handler f4135p = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Runnable f4138s = new a();

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f4141v = false;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            T t2 = T.this;
            if (t2.f4136q == null) {
                return;
            }
            t2.f4136q = null;
            t2.f4137r = 0L;
            t2.g();
        }
    }

    public class b implements W.j {
        public b() {
        }

        @Override // com.im.oldchat.ui.W.j
        public void a() {
            T.this.g();
        }
    }

    public T(Context context, List list, String str) {
        this.f4235e = context;
        this.f4233c = list;
        this.f4234d = str;
        this.f4236f = new W(context, new b());
    }

    public boolean A0(j0.k kVar) {
        if (kVar == null) {
            return false;
        }
        if (!this.f4154j && !this.f4156l) {
            return false;
        }
        this.f4155k = kVar;
        this.f4156l = true;
        this.f4154j = false;
        g();
        return true;
    }

    public void B0() {
        W w2 = this.f4236f;
        if (w2 != null) {
            w2.K();
        }
        this.f4135p.removeCallbacks(this.f4138s);
    }

    public int C0() {
        return o0.X.i(this.f4235e);
    }

    public int D0() {
        return AbstractC0235a.b(this.f4235e, f0.d.f4973j);
    }

    public int E0() {
        return AbstractC0235a.b(this.f4235e, f0.d.f4974k);
    }

    public void F0(j0.k kVar) {
        if (kVar == null) {
            return;
        }
        if (!this.f4141v) {
            i0(kVar);
            return;
        }
        String strR0 = r0(kVar);
        if (strR0.length() == 0) {
            return;
        }
        if (this.f4134o.contains(strR0)) {
            this.f4134o.remove(strR0);
        } else {
            this.f4134o.add(strR0);
        }
        if (this.f4134o.isEmpty()) {
            this.f4141v = false;
        }
        h0();
        g();
    }

    public final String Z(j0.k kVar) {
        if (kVar == null) {
            return null;
        }
        String str = kVar.f5550a;
        if (str != null && !str.isEmpty()) {
            return "id:" + kVar.f5550a;
        }
        if (kVar.f5558i <= 0) {
            return "obj:" + System.identityHashCode(kVar);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("ts:");
        sb.append(kVar.f5558i);
        sb.append("_");
        String str2 = kVar.f5552c;
        if (str2 == null) {
            str2 = "";
        }
        sb.append(str2);
        return sb.toString();
    }

    public void a0(U.d dVar, int i2) {
        LinearLayout linearLayout;
        if (dVar == null || (linearLayout = dVar.f4203s) == null) {
            return;
        }
        if (!o0.X.b(this.f4235e, linearLayout, i2)) {
            dVar.f4203s.setBackgroundResource(i2);
        }
        dVar.f4203s.setPadding(dVar.f4198c0, dVar.f4199d0, dVar.f4200e0, dVar.f4201f0);
    }

    public void b0(U.d dVar, String str, j0.k kVar, boolean z2) {
        dVar.f4197b0.setGravity(8388613);
        if ("red_packet".equals(str)) {
            a0(dVar, f0.e.f4983D);
        } else {
            a0(dVar, f0.e.f5022i);
        }
        dVar.f4202r.setTextColor(-1);
        dVar.f4193X.setTextColor(-1);
        TextView textView = dVar.f4194Y;
        if (textView != null) {
            textView.setTextColor(D0());
        }
        dVar.f4192W.setColorFilter(-1);
        dVar.f4206v.setTextColor(-1);
        dVar.f4207w.setTextColor(-855638017);
        dVar.f4208x.setTextColor(-1);
        dVar.f4205u.setColorFilter(-1);
        dVar.f4183N.setTextColor(-1);
        dVar.f4184O.setTextColor(-855638017);
        TextView textView2 = dVar.f4185P;
        if (textView2 != null) {
            textView2.setTextColor(-1711276033);
        }
        ImageView imageView = dVar.f4186Q;
        if (imageView != null && imageView.getVisibility() == 0) {
            dVar.f4186Q.setColorFilter(-1711276033);
        }
        if (z2) {
            LinearLayout linearLayout = dVar.f4175F;
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
            }
            LinearLayout linearLayout2 = dVar.f4178I;
            if (linearLayout2 != null) {
                linearLayout2.setVisibility(0);
                f0(dVar.f4179J, dVar.f4180K, kVar.f5559j);
                return;
            }
            return;
        }
        LinearLayout linearLayout3 = dVar.f4178I;
        if (linearLayout3 != null) {
            linearLayout3.setVisibility(8);
        }
        LinearLayout linearLayout4 = dVar.f4175F;
        if (linearLayout4 != null) {
            linearLayout4.setVisibility(0);
            e0(dVar, kVar.f5559j);
        }
    }

    public void c0(U.d dVar, String str) {
        dVar.f4197b0.setGravity(8388611);
        if ("red_packet".equals(str)) {
            a0(dVar, f0.e.f4981B);
        } else {
            a0(dVar, f0.e.f5025l);
        }
        int iD0 = D0();
        int iE0 = E0();
        int iC0 = C0();
        dVar.f4202r.setTextColor(iD0);
        dVar.f4193X.setTextColor(iD0);
        TextView textView = dVar.f4194Y;
        if (textView != null) {
            textView.setTextColor(iD0);
        }
        dVar.f4192W.setColorFilter(iD0);
        dVar.f4206v.setTextColor(iD0);
        dVar.f4207w.setTextColor(iE0);
        dVar.f4208x.setTextColor(iC0);
        dVar.f4205u.setColorFilter(iC0);
        dVar.f4183N.setTextColor(-1);
        dVar.f4184O.setTextColor(-855638017);
        TextView textView2 = dVar.f4185P;
        if (textView2 != null) {
            textView2.setTextColor(-1711276033);
        }
        ImageView imageView = dVar.f4186Q;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        LinearLayout linearLayout = dVar.f4175F;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        LinearLayout linearLayout2 = dVar.f4178I;
        if (linearLayout2 != null) {
            linearLayout2.setVisibility(8);
        }
    }

    public void d0(View view, String str) {
        if (view == null) {
            return;
        }
        if (z0(str)) {
            view.setBackgroundColor(721409359);
        } else {
            view.setBackgroundColor(0);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.g
    public int e(int i2) {
        if (this.f4156l && i2 == this.f4233c.size() - 1) {
            return 1;
        }
        return (this.f4154j && i2 == c() - 1) ? 1 : 0;
    }

    public void e0(U.d dVar, int i2) {
        if (dVar == null) {
            return;
        }
        f0(dVar.f4176G, dVar.f4177H, i2);
    }

    public void f0(TextView textView, ImageView imageView, int i2) {
        if (textView == null || imageView == null) {
            return;
        }
        if (i2 == 1) {
            textView.setText(this.f4235e.getString(f0.j.B1));
            textView.setVisibility(0);
            imageView.setVisibility(8);
        } else {
            textView.setVisibility(8);
            imageView.setVisibility(0);
            if (i2 == 3) {
                imageView.setImageResource(f0.e.f5003X);
            } else {
                imageView.setImageResource(f0.e.f5004Y);
            }
        }
    }

    public void g0(U.d dVar) {
        LinearLayout linearLayout;
        if (dVar == null || (linearLayout = dVar.f4203s) == null) {
            return;
        }
        linearLayout.setBackgroundResource(0);
        dVar.f4203s.setPadding(0, 0, 0, 0);
    }

    public void h0() {
        S.o oVar = this.f4140u;
        if (oVar != null) {
            oVar.a(this.f4141v, this.f4134o.size());
        }
    }

    public void i0(j0.k kVar) {
        this.f4141v = true;
        this.f4134o.clear();
        if (kVar != null) {
            String strR0 = r0(kVar);
            if (strR0.length() > 0) {
                this.f4134o.add(strR0);
            }
        }
        h0();
        g();
    }

    public void j0() {
        if (this.f4141v || !this.f4134o.isEmpty()) {
            this.f4141v = false;
            this.f4134o.clear();
            h0();
            g();
        }
    }

    public List k0() {
        ArrayList arrayList = new ArrayList();
        if (this.f4134o.isEmpty()) {
            return arrayList;
        }
        for (int i2 = 0; i2 < this.f4233c.size(); i2++) {
            j0.k kVar = (j0.k) this.f4233c.get(i2);
            if (m0(kVar)) {
                arrayList.add(kVar);
            }
        }
        return arrayList;
    }

    public void l0(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        this.f4136q = str;
        this.f4137r = System.currentTimeMillis() + 1200;
        this.f4135p.removeCallbacks(this.f4138s);
        this.f4135p.postDelayed(this.f4138s, 1280L);
        g();
    }

    public boolean m0(j0.k kVar) {
        if (kVar == null) {
            return false;
        }
        String strR0 = r0(kVar);
        return strR0.length() > 0 && this.f4134o.contains(strR0);
    }

    public boolean n0() {
        return this.f4141v;
    }

    public void o0(j0.k kVar) {
        String strZ = Z(kVar);
        if (strZ == null || strZ.length() == 0 || !y0(kVar)) {
            return;
        }
        this.f4133n.add(strZ);
    }

    public void p0(j0.k kVar) {
        String strZ = Z(kVar);
        if (strZ == null || strZ.isEmpty()) {
            return;
        }
        this.f4132m.add(strZ);
    }

    public void q0(U.d dVar, j0.k kVar, boolean z2) {
        LinearLayout linearLayout;
        if (dVar == null || (linearLayout = dVar.f4203s) == null) {
            return;
        }
        linearLayout.clearAnimation();
        String strZ = Z(kVar);
        if (strZ == null || strZ.length() == 0) {
            return;
        }
        if (z2) {
            if (this.f4132m.remove(strZ)) {
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.setInterpolator(new DecelerateInterpolator());
                ScaleAnimation scaleAnimation = new ScaleAnimation(0.92f, 1.0f, 0.92f, 1.0f, 1, 1.0f, 1, 1.0f);
                scaleAnimation.setDuration(120L);
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.6f, 1.0f);
                alphaAnimation.setDuration(120L);
                animationSet.addAnimation(scaleAnimation);
                animationSet.addAnimation(alphaAnimation);
                dVar.f4203s.startAnimation(animationSet);
                return;
            }
            return;
        }
        if (y0(kVar) && this.f4133n.remove(strZ)) {
            AnimationSet animationSet2 = new AnimationSet(true);
            animationSet2.setInterpolator(new DecelerateInterpolator());
            TranslateAnimation translateAnimation = new TranslateAnimation(1, -0.35f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
            translateAnimation.setDuration(170L);
            AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.25f, 1.0f);
            alphaAnimation2.setDuration(170L);
            animationSet2.addAnimation(translateAnimation);
            animationSet2.addAnimation(alphaAnimation2);
            dVar.f4203s.startAnimation(animationSet2);
        }
    }

    public final String r0(j0.k kVar) {
        if (kVar == null) {
            return "";
        }
        String str = kVar.f5550a;
        if (str != null && str.length() > 0) {
            return "id:" + kVar.f5550a;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("ts:");
        sb.append(kVar.f5558i);
        sb.append("|uid:");
        String str2 = kVar.f5552c;
        if (str2 == null) {
            str2 = "";
        }
        sb.append(str2);
        sb.append("|type:");
        String str3 = kVar.f5554e;
        if (str3 == null) {
            str3 = "";
        }
        sb.append(str3);
        sb.append("|body:");
        String str4 = kVar.f5553d;
        sb.append(str4 != null ? str4 : "");
        return sb.toString();
    }

    public void s0(S.m mVar) {
        this.f4139t = mVar;
    }

    public void t0(float f2) {
        if (f2 <= 0.0f) {
            f2 = 1.0f;
        }
        if (Math.abs(this.f4237g - f2) < 0.01f) {
            return;
        }
        this.f4237g = f2;
        g();
    }

    public void u0(String str) {
        String str2 = this.f4234d;
        if (str2 == null || !str2.equals(str)) {
            this.f4234d = str;
            g();
        }
    }

    public void v0(S.n nVar) {
        this.f4238h = nVar;
    }

    public void w0(S.o oVar) {
        this.f4140u = oVar;
    }

    public void x0(boolean z2) {
        if (this.f4154j == z2) {
            return;
        }
        this.f4154j = z2;
        g();
    }

    public boolean y0(j0.k kVar) {
        if (kVar == null) {
            return false;
        }
        String str = kVar.f5554e;
        String lowerCase = str == null ? "" : str.trim().toLowerCase();
        return "image".equals(lowerCase) || "voice".equals(lowerCase);
    }

    public boolean z0(String str) {
        String str2;
        if (str == null || str.length() == 0 || (str2 = this.f4136q) == null || !str.equals(str2)) {
            return false;
        }
        if (System.currentTimeMillis() <= this.f4137r) {
            return true;
        }
        this.f4136q = null;
        this.f4137r = 0L;
        return false;
    }
}

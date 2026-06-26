package com.im.oldchat.ui;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.im.oldchat.ui.N;
import java.util.ArrayList;
import java.util.List;
import l0.AbstractC0380g;
import o0.AbstractC0434b0;
import o0.AbstractC0451q;
import o0.AbstractC0455v;

/* JADX INFO: loaded from: classes.dex */
public abstract class L extends M {

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            L.this.N();
        }
    }

    public L(Context context, List list, String str) {
        super(context, list, str);
    }

    public String I(j0.i iVar) {
        if (iVar == null) {
            return null;
        }
        String str = iVar.f5529a;
        if (str != null && !str.isEmpty()) {
            return "id:" + iVar.f5529a;
        }
        if (iVar.f5537i <= 0) {
            return "obj:" + System.identityHashCode(iVar);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("ts:");
        sb.append(iVar.f5537i);
        sb.append("_");
        String str2 = iVar.f5531c;
        if (str2 == null) {
            str2 = "";
        }
        sb.append(str2);
        return sb.toString();
    }

    public void J(N.d dVar, int i2) {
        LinearLayout linearLayout;
        if (dVar == null || (linearLayout = dVar.f3523i) == null) {
            return;
        }
        if (!o0.X.b(this.f3455a, linearLayout, i2)) {
            dVar.f3523i.setBackgroundResource(i2);
        }
        dVar.f3523i.setPadding(dVar.f3511V, dVar.f3512W, dVar.f3513X, dVar.f3514Y);
    }

    public void K(N.c cVar, j0.i iVar) {
        String str;
        if (cVar == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (iVar == null || (str = iVar.f5531c) == null || str.isEmpty()) {
            arrayList.addAll(O());
        } else {
            arrayList.add(iVar.f5531c);
        }
        if (arrayList.isEmpty()) {
            cVar.b();
            cVar.a();
            return;
        }
        L(cVar, arrayList);
        if (iVar == null) {
            cVar.c();
            cVar.e();
        } else {
            String strE = AbstractC0380g.e(iVar.f5533e, iVar.f5532d);
            String str2 = iVar.f5529a;
            cVar.d(strE, (str2 == null || str2.length() <= 0) ? String.valueOf(iVar.f5537i) : iVar.f5529a);
            S(cVar);
        }
    }

    public void L(N.c cVar, ArrayList arrayList) {
        if (cVar == null) {
            return;
        }
        View view = cVar.f3478b;
        if (view != null) {
            view.setVisibility(arrayList.isEmpty() ? 8 : 0);
        }
        ImageView[] imageViewArr = {cVar.f3479c, cVar.f3480d, cVar.f3481e};
        for (int i2 = 0; i2 < 3; i2++) {
            ImageView imageView = imageViewArr[i2];
            if (imageView != null) {
                if (i2 < arrayList.size()) {
                    imageView.setVisibility(0);
                    Q(imageView, (String) arrayList.get(i2));
                } else {
                    imageView.setVisibility(8);
                    imageView.setTag(null);
                }
            }
        }
    }

    public void M(N.d dVar) {
        LinearLayout linearLayout;
        if (dVar == null || (linearLayout = dVar.f3523i) == null) {
            return;
        }
        linearLayout.setBackgroundResource(0);
        dVar.f3523i.setPadding(0, 0, 0, 0);
    }

    public void N() {
        if (this.f3469o) {
            this.f3469o = false;
            this.f3468n = null;
            this.f3470p = null;
            notifyDataSetChanged();
        }
    }

    public ArrayList O() {
        ArrayList arrayList = new ArrayList();
        for (String str : this.f3467m) {
            if (str != null && !str.isEmpty() && (!this.f3469o || !str.equals(this.f3470p))) {
                arrayList.add(str);
                if (arrayList.size() > 3) {
                    arrayList.remove(0);
                }
            }
        }
        return arrayList;
    }

    public boolean P(int i2) {
        if (O().isEmpty()) {
            return false;
        }
        return !(this.f3469o && i2 == this.f3456b.size() - 1) && i2 == getCount() - 1;
    }

    public void Q(ImageView imageView, String str) {
        if (imageView == null || str == null || str.isEmpty()) {
            return;
        }
        String strD = AbstractC0434b0.d(this.f3455a, str);
        if (strD != null && !strD.isEmpty()) {
            str = strD;
        }
        String strB = (String) this.f3460f.get(str);
        if ((strB == null || strB.isEmpty()) && (strB = AbstractC0451q.b(this.f3455a, str)) != null && !strB.isEmpty()) {
            this.f3460f.put(str, strB);
        }
        if (strB == null || strB.isEmpty()) {
            imageView.setImageResource(f0.e.f4999T);
            imageView.setTag(null);
            return;
        }
        Object tag = imageView.getTag();
        if (tag == null || !strB.equals(tag)) {
            imageView.setTag(strB);
            AbstractC0455v.h(imageView, strB);
        }
    }

    public void R(N.d dVar, j0.i iVar, boolean z2) {
        LinearLayout linearLayout;
        if (dVar == null || (linearLayout = dVar.f3523i) == null) {
            return;
        }
        linearLayout.clearAnimation();
        String strI = I(iVar);
        if (strI == null || strI.length() == 0) {
            return;
        }
        if (z2) {
            if (this.f3471q.remove(strI)) {
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.setInterpolator(new DecelerateInterpolator());
                ScaleAnimation scaleAnimation = new ScaleAnimation(0.92f, 1.0f, 0.92f, 1.0f, 1, 1.0f, 1, 1.0f);
                scaleAnimation.setDuration(120L);
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.6f, 1.0f);
                alphaAnimation.setDuration(120L);
                animationSet.addAnimation(scaleAnimation);
                animationSet.addAnimation(alphaAnimation);
                dVar.f3523i.startAnimation(animationSet);
                return;
            }
            return;
        }
        if (T(iVar) && this.f3472r.remove(strI)) {
            AnimationSet animationSet2 = new AnimationSet(true);
            animationSet2.setInterpolator(new DecelerateInterpolator());
            TranslateAnimation translateAnimation = new TranslateAnimation(1, -0.35f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
            translateAnimation.setDuration(170L);
            AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.25f, 1.0f);
            alphaAnimation2.setDuration(170L);
            animationSet2.addAnimation(translateAnimation);
            animationSet2.addAnimation(alphaAnimation2);
            dVar.f3523i.startAnimation(animationSet2);
        }
    }

    public void S(N.c cVar) {
        if (cVar == null) {
            return;
        }
        View view = cVar.f3477a;
        if (view != null) {
            view.removeCallbacks(cVar.f3489m);
        }
        a aVar = new a();
        cVar.f3489m = aVar;
        View view2 = cVar.f3477a;
        if (view2 != null) {
            view2.postDelayed(aVar, 200L);
        }
    }

    public boolean T(j0.i iVar) {
        if (iVar == null) {
            return false;
        }
        String str = iVar.f5533e;
        String lowerCase = str == null ? "" : str.trim().toLowerCase();
        return "image".equals(lowerCase) || "voice".equals(lowerCase);
    }
}

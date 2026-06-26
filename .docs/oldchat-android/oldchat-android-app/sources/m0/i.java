package m0;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.im.oldchat.ui.widget.TiltFrameLayout;
import java.util.ArrayList;
import java.util.List;
import k.AbstractC0285k0;
import l0.AbstractC0382h;
import o0.AbstractC0455v;
import o0.V;

/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f6509a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Typeface f6510b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final e f6511c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f6512d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final g[] f6513e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ProgressBar f6514f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final View f6515g;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f6516a;

        public a(int i2) {
            this.f6516a = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            i.this.s(this.f6516a);
        }
    }

    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ n f6518a;

        public b(n nVar) {
            this.f6518a = nVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (i.this.f6511c != null) {
                i.this.f6511c.b(this.f6518a);
            }
        }
    }

    public class c implements View.OnLongClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ n f6520a;

        public c(n nVar) {
            this.f6520a = nVar;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if (i.this.f6511c == null) {
                return true;
            }
            i.this.f6511c.a(view, this.f6520a);
            return true;
        }
    }

    public interface e {
        void a(View view, n nVar);

        void b(n nVar);
    }

    public static class f extends Animation {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final View f6524a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f6525b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f6526c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final float f6527d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final float f6528e;

        public class a implements Interpolator {
            public a() {
            }

            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f2) {
                float f3 = f2 - 1.0f;
                return (f3 * f3 * f3) + 1.0f;
            }
        }

        public f(View view, int i2, int i3, float f2, float f3) {
            this.f6524a = view;
            this.f6525b = i2;
            this.f6526c = i3;
            this.f6527d = f2;
            this.f6528e = f3;
            setDuration(260L);
            setInterpolator(new a());
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f2, Transformation transformation) {
            int i2 = this.f6525b + ((int) ((this.f6526c - r4) * f2));
            ViewGroup.LayoutParams layoutParams = this.f6524a.getLayoutParams();
            if (i2 <= 0) {
                i2 = 0;
            }
            layoutParams.height = i2;
            this.f6524a.setLayoutParams(layoutParams);
            View view = this.f6524a;
            float f3 = this.f6527d;
            AbstractC0285k0.K(view, f3 + ((this.f6528e - f3) * f2));
        }

        @Override // android.view.animation.Animation
        public boolean willChangeBounds() {
            return true;
        }
    }

    public static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public View f6530a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public View f6531b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f6532c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public FrameLayout f6533d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public LinearLayout f6534e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f6535f;

        public g() {
        }

        public /* synthetic */ g(a aVar) {
            this();
        }
    }

    public i(View view, Typeface typeface, e eVar) {
        ArrayList arrayList = new ArrayList();
        this.f6512d = arrayList;
        g[] gVarArr = new g[3];
        this.f6513e = gVarArr;
        this.f6509a = view;
        this.f6510b = typeface;
        this.f6511c = eVar;
        this.f6514f = (ProgressBar) view.findViewById(f0.f.p7);
        View viewFindViewById = view.findViewById(f0.f.j8);
        this.f6515g = viewFindViewById;
        if (viewFindViewById != null && !V.i(view.getContext())) {
            viewFindViewById.setVisibility(8);
        }
        g(view);
        int i2 = 0;
        gVarArr[0] = h(view, f0.f.j5, f0.f.o4, f0.f.vb, f0.f.m7, f0.f.k4, true);
        gVarArr[1] = h(view, f0.f.h5, f0.f.m4, f0.f.i9, f0.f.k7, f0.f.i4, true);
        gVarArr[2] = h(view, f0.f.i5, f0.f.n4, f0.f.S9, f0.f.l7, f0.f.j4, false);
        arrayList.add(0);
        arrayList.add(1);
        while (true) {
            g[] gVarArr2 = this.f6513e;
            if (i2 >= gVarArr2.length) {
                return;
            }
            e(gVarArr2[i2]);
            a aVar = new a(i2);
            this.f6513e[i2].f6530a.setOnClickListener(aVar);
            View view2 = (View) this.f6513e[i2].f6530a.getParent();
            if (view2 != null) {
                view2.setOnClickListener(aVar);
            }
            i2++;
        }
    }

    public final void c(LinearLayout linearLayout, String str) {
        TextView textView = new TextView(this.f6509a.getContext());
        textView.setText(str);
        textView.setTextSize(12.0f);
        textView.setTextColor(i(f0.d.f4976m));
        textView.setPadding(j(6), j(6), j(6), j(8));
        linearLayout.addView(textView);
    }

    public final void d(FrameLayout frameLayout, boolean z2, boolean z3) {
        frameLayout.clearAnimation();
        frameLayout.setTag(z2 ? Boolean.TRUE : Boolean.FALSE);
        if (!z3) {
            frameLayout.setVisibility(z2 ? 0 : 8);
            frameLayout.getLayoutParams().height = z2 ? -2 : 0;
            AbstractC0285k0.K(frameLayout, z2 ? 1.0f : 0.0f);
            return;
        }
        int height = frameLayout.getHeight();
        if (z2) {
            frameLayout.setVisibility(0);
            frameLayout.startAnimation(new f(frameLayout, Math.max(0, height), k(frameLayout), 0.0f, 1.0f));
            return;
        }
        if (height <= 0) {
            height = k(frameLayout);
        }
        f fVar = new f(frameLayout, height, 0, 1.0f, 0.0f);
        fVar.setAnimationListener(new d(frameLayout));
        frameLayout.startAnimation(fVar);
    }

    public final void e(g gVar) {
        t(gVar.f6531b, gVar.f6535f);
        d(gVar.f6533d, gVar.f6535f, false);
    }

    public final void f(View view, n nVar) {
        TextView textView = (TextView) view.findViewById(f0.f.wc);
        TextView textView2 = (TextView) view.findViewById(f0.f.rc);
        TextView textView3 = (TextView) view.findViewById(f0.f.uc);
        TextView textView4 = (TextView) view.findViewById(f0.f.tc);
        TextView textView5 = (TextView) view.findViewById(f0.f.vc);
        TextView textView6 = (TextView) view.findViewById(f0.f.xc);
        View viewFindViewById = view.findViewById(f0.f.qe);
        View viewFindViewById2 = view.findViewById(f0.f.V5);
        Typeface typeface = this.f6510b;
        if (typeface != null) {
            textView.setTypeface(typeface);
        }
        String str = nVar.f6574g;
        if (str == null) {
            str = "";
        }
        textView.setText(str);
        boolean z2 = nVar.f6568a && nVar.f6582o;
        if (textView2 != null) {
            textView2.setVisibility(z2 ? 0 : 8);
        }
        String str2 = nVar.f6576i;
        if (str2 == null) {
            str2 = "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(z2 ? "@我 " : "");
        sb.append(str2);
        textView4.setText(sb.toString());
        textView4.setTextColor(i(z2 ? f0.d.f4977n : f0.d.f4978o));
        String strA = AbstractC0382h.a(nVar.f6579l);
        if (strA == null || strA.length() == 0) {
            strA = "--:--";
        }
        textView5.setText(strA);
        if (nVar.f6580m > 0) {
            textView6.setVisibility(0);
            textView6.setText(String.valueOf(nVar.f6580m));
        } else {
            textView6.setVisibility(8);
        }
        if (nVar.f6569b) {
            textView3.setVisibility(0);
            textView3.setText("系统");
        } else if (nVar.f6568a) {
            textView3.setVisibility(0);
            textView3.setText("群聊");
        } else {
            textView3.setVisibility(8);
        }
        ViewGroup.LayoutParams layoutParams = viewFindViewById.getLayoutParams();
        layoutParams.width = j(Math.min(90, (Math.max(0, nVar.f6580m) * 4) + 42));
        viewFindViewById.setLayoutParams(layoutParams);
        ImageView imageView = (ImageView) viewFindViewById2;
        if (nVar.f6569b) {
            imageView.setImageResource(f0.i.f5176a);
        } else {
            AbstractC0455v.h(imageView, nVar.f6578k);
        }
        view.setOnClickListener(new b(nVar));
        view.setOnLongClickListener(new c(nVar));
    }

    public final void g(View view) {
        TextView textView = (TextView) view.findViewById(f0.f.sc);
        Typeface typeface = this.f6510b;
        if (typeface == null || textView == null) {
            return;
        }
        textView.setTypeface(typeface);
    }

    public final g h(View view, int i2, int i3, int i4, int i5, int i6, boolean z2) {
        TextView textView;
        g gVar = new g(null);
        gVar.f6530a = view.findViewById(i2);
        gVar.f6531b = view.findViewById(i3);
        gVar.f6532c = (TextView) view.findViewById(i4);
        gVar.f6533d = (FrameLayout) view.findViewById(i5);
        gVar.f6534e = (LinearLayout) view.findViewById(i6);
        gVar.f6535f = z2;
        if (this.f6510b != null && (textView = (TextView) view.findViewById(o(i2))) != null) {
            textView.setTypeface(this.f6510b);
        }
        return gVar;
    }

    public final int i(int i2) {
        return this.f6509a.getResources().getColor(i2);
    }

    public final int j(int i2) {
        return (int) ((this.f6509a.getResources().getDisplayMetrics().density * i2) + 0.5f);
    }

    public final int k(View view) {
        int width = ((View) view.getParent()).getWidth();
        if (width <= 0) {
            width = this.f6509a.getResources().getDisplayMetrics().widthPixels - j(48);
        }
        view.measure(View.MeasureSpec.makeMeasureSpec(width, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
        return Math.max(1, view.getMeasuredHeight());
    }

    public final void l(LinearLayout linearLayout, List list, int i2, boolean z2) {
        linearLayout.removeAllViews();
        if (list.isEmpty()) {
            c(linearLayout, "暂无会话");
            return;
        }
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f6509a.getContext());
        for (int i3 = 0; i3 < list.size(); i3++) {
            n nVar = (n) list.get(i3);
            View viewInflate = layoutInflaterFrom.inflate(i2, (ViewGroup) linearLayout, false);
            if (viewInflate instanceof TiltFrameLayout) {
                ((TiltFrameLayout) viewInflate).setAutoMorphEnabled(z2);
            }
            f(viewInflate, nVar);
            linearLayout.addView(viewInflate);
        }
    }

    public final void m(LinearLayout linearLayout, List list) {
        h.f(this.f6509a, linearLayout, list, this.f6510b, this.f6511c);
    }

    public void n(List list, List list2, List list3) {
        boolean zI = V.i(this.f6509a.getContext());
        if (!zI && list != null && !list.isEmpty()) {
            list2.addAll(0, list);
            list.clear();
        }
        View view = this.f6515g;
        if (view != null) {
            view.setVisibility(zI ? 0 : 8);
        }
        int size = list == null ? 0 : list.size();
        this.f6513e[0].f6532c.setText(String.valueOf(size));
        this.f6513e[0].f6532c.setBackgroundResource(size > 0 ? f0.e.f5038y : f0.e.f5039z);
        this.f6513e[0].f6532c.setTextColor(size > 0 ? -1 : i(f0.d.f4975l));
        this.f6513e[1].f6532c.setText(String.valueOf(list2.size()));
        this.f6513e[2].f6532c.setText(String.valueOf(list3.size()));
        l(this.f6513e[0].f6534e, list, f0.g.P0, false);
        l(this.f6513e[1].f6534e, list2, f0.g.P0, false);
        m(this.f6513e[2].f6534e, list3);
        r();
    }

    public final int o(int i2) {
        return i2 == f0.f.j5 ? f0.f.wb : i2 == f0.f.h5 ? f0.f.j9 : f0.f.W9;
    }

    public final void p(int i2, boolean z2, boolean z3) {
        g gVar = this.f6513e[i2];
        gVar.f6535f = z2;
        this.f6512d.remove(Integer.valueOf(i2));
        if (z2) {
            this.f6512d.add(Integer.valueOf(i2));
        }
        t(gVar.f6531b, z2);
        d(gVar.f6533d, z2, z3);
    }

    public void q(boolean z2) {
        ProgressBar progressBar = this.f6514f;
        if (progressBar != null) {
            progressBar.setVisibility(z2 ? 8 : 0);
        }
    }

    public final void r() {
        FrameLayout frameLayout;
        int i2 = 0;
        while (true) {
            g[] gVarArr = this.f6513e;
            if (i2 >= gVarArr.length) {
                return;
            }
            g gVar = gVarArr[i2];
            if (gVar != null && (frameLayout = gVar.f6533d) != null && gVar.f6535f) {
                ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
                gVar.f6533d.setVisibility(0);
                if (layoutParams != null && layoutParams.height != -2) {
                    layoutParams.height = -2;
                    gVar.f6533d.setLayoutParams(layoutParams);
                }
                AbstractC0285k0.K(gVar.f6533d, 1.0f);
                gVar.f6533d.requestLayout();
            }
            i2++;
        }
    }

    public final void s(int i2) {
        if (this.f6513e[i2].f6535f) {
            p(i2, false, true);
            return;
        }
        if (this.f6512d.size() >= 2) {
            p(((Integer) this.f6512d.get(0)).intValue(), false, true);
        }
        p(i2, true, true);
    }

    public final void t(View view, boolean z2) {
        view.setBackgroundResource(z2 ? f0.e.f5037x : f0.e.f5036w);
        float f2 = z2 ? 0.72f : 1.0f;
        float f3 = z2 ? 1.0f : 0.72f;
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animationSet.setDuration(240L);
        animationSet.addAnimation(new ScaleAnimation(f2, f3, f2, f3, 1, 0.5f, 1, 0.5f));
        animationSet.addAnimation(new AlphaAnimation(z2 ? 0.7f : 1.0f, z2 ? 1.0f : 0.7f));
        view.startAnimation(animationSet);
    }

    public class d implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ FrameLayout f6522a;

        public d(FrameLayout frameLayout) {
            this.f6522a = frameLayout;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            Object tag = this.f6522a.getTag();
            if ((tag instanceof Boolean) && ((Boolean) tag).booleanValue()) {
                return;
            }
            this.f6522a.setVisibility(8);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }
}

package m0;

import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.im.oldchat.ui.widget.RoundedImageView;
import java.util.List;
import m0.i;
import o0.AbstractC0455v;
import o0.X;

/* JADX INFO: loaded from: classes.dex */
public abstract class h {

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ i.e f6505a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ n f6506b;

        public a(i.e eVar, n nVar) {
            this.f6505a = eVar;
            this.f6506b = nVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            i.e eVar = this.f6505a;
            if (eVar != null) {
                eVar.b(this.f6506b);
            }
        }
    }

    public class b implements View.OnLongClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ i.e f6507a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ n f6508b;

        public b(i.e eVar, n nVar) {
            this.f6507a = eVar;
            this.f6508b = nVar;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            i.e eVar = this.f6507a;
            if (eVar == null) {
                return true;
            }
            eVar.a(view, this.f6508b);
            return true;
        }
    }

    public static void a(View view, LinearLayout linearLayout) {
        TextView textView = new TextView(view.getContext());
        textView.setText("暂无折叠会话");
        textView.setTextSize(12.0f);
        textView.setTextColor(view.getResources().getColor(f0.d.f4976m));
        int iE = e(view, 6);
        textView.setPadding(iE, iE, iE, e(view, 8));
        linearLayout.addView(textView);
    }

    public static void b(View view, View view2, boolean z2) {
        int i2 = z2 ? X.i(view.getContext()) : c(view, f0.d.f4971h);
        int iJ = z2 ? X.j(view.getContext()) : c(view, f0.d.f4972i);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(i2);
        gradientDrawable.setCornerRadius(0.0f);
        gradientDrawable.setStroke(e(view, 1), iJ);
        view2.setBackgroundDrawable(gradientDrawable);
    }

    public static int c(View view, int i2) {
        return view.getResources().getColor(i2);
    }

    public static View d(View view, ViewGroup viewGroup, n nVar, Typeface typeface, i.e eVar) {
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(f0.g.O0, viewGroup, false);
        View viewFindViewById = viewInflate.findViewById(f0.f.d5);
        RoundedImageView roundedImageView = (RoundedImageView) viewInflate.findViewById(f0.f.y5);
        TextView textView = (TextView) viewInflate.findViewById(f0.f.U9);
        TextView textView2 = (TextView) viewInflate.findViewById(f0.f.T9);
        TextView textView3 = (TextView) viewInflate.findViewById(f0.f.V9);
        boolean z2 = true;
        boolean z3 = nVar.f6568a && nVar.f6582o;
        if (!z3 && nVar.f6580m <= 0) {
            z2 = false;
        }
        if (typeface != null && textView != null) {
            textView.setTypeface(typeface);
        }
        String str = nVar.f6574g;
        if (str == null) {
            str = "";
        }
        textView.setText(str);
        if (z3) {
            textView2.setVisibility(0);
            textView2.setText("@我");
            textView2.setTextColor(c(view, f0.d.f4964a));
        } else if (nVar.f6580m > 0) {
            textView2.setVisibility(0);
            textView2.setText("新消息");
            textView2.setTextColor(c(view, f0.d.f4970g));
        } else {
            textView2.setVisibility(8);
        }
        textView.setTextColor(z2 ? c(view, f0.d.f4970g) : c(view, f0.d.f4973j));
        if (nVar.f6580m > 0) {
            textView3.setVisibility(0);
            int i2 = nVar.f6580m;
            textView3.setText(i2 > 99 ? "99+" : String.valueOf(i2));
        } else {
            textView3.setVisibility(8);
        }
        if (nVar.f6569b) {
            roundedImageView.setImageResource(f0.i.f5176a);
        } else {
            AbstractC0455v.h(roundedImageView, nVar.f6578k);
        }
        b(view, viewFindViewById, z2);
        viewInflate.setOnClickListener(new a(eVar, nVar));
        viewInflate.setOnLongClickListener(new b(eVar, nVar));
        return viewInflate;
    }

    public static int e(View view, int i2) {
        return (int) ((view.getResources().getDisplayMetrics().density * i2) + 0.5f);
    }

    public static void f(View view, LinearLayout linearLayout, List list, Typeface typeface, i.e eVar) {
        View view2;
        linearLayout.removeAllViews();
        if (list == null || list.isEmpty()) {
            a(view, linearLayout);
            return;
        }
        int iE = e(view, 4);
        int iG = (g(view, linearLayout) - (iE * 5)) / 6;
        if (iG < e(view, 34)) {
            iG = e(view, 34);
        }
        int i2 = 0;
        while (i2 < list.size()) {
            LinearLayout linearLayout2 = new LinearLayout(linearLayout.getContext());
            linearLayout2.setOrientation(0);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.bottomMargin = iE;
            linearLayout.addView(linearLayout2, layoutParams);
            for (int i3 = 0; i3 < 6; i3++) {
                if (i2 < list.size()) {
                    view2 = d(view, linearLayout, (n) list.get(i2), typeface, eVar);
                    i2++;
                } else {
                    view2 = new View(linearLayout.getContext());
                }
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, iG, 1.0f);
                if (i3 > 0) {
                    layoutParams2.leftMargin = iE;
                }
                linearLayout2.addView(view2, layoutParams2);
            }
        }
    }

    public static int g(View view, View view2) {
        int width = view2.getWidth();
        if (width > 0 && (view2 instanceof LinearLayout)) {
            LinearLayout linearLayout = (LinearLayout) view2;
            width = (width - linearLayout.getPaddingLeft()) - linearLayout.getPaddingRight();
        }
        if (width <= 0) {
            width = view.getResources().getDisplayMetrics().widthPixels - e(view, 52);
        }
        return width <= 0 ? e(view, 280) : width;
    }
}

package l0;

import android.content.Context;
import android.graphics.ColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import l0.C0408x;
import o0.AbstractC0455v;

/* JADX INFO: renamed from: l0.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0409y {

    /* JADX INFO: renamed from: l0.y$a */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextView f6392a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f6393b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final TextView f6394c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final ImageView f6395d;

        public a(View view) {
            this.f6392a = AbstractC0409y.i(view);
            this.f6393b = AbstractC0409y.h(view);
            this.f6394c = (TextView) view.findViewById(f0.f.Zd);
            this.f6395d = (ImageView) view.findViewById(f0.f.m5);
        }
    }

    /* JADX INFO: renamed from: l0.y$b */
    public static class b extends a implements View.OnClickListener {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public C0408x.c f6396e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public j0.w f6397f;

        /* JADX INFO: renamed from: l0.y$b$a */
        public class a implements View.OnClickListener {
            public a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                j0.w wVar;
                b bVar = b.this;
                C0408x.c cVar = bVar.f6396e;
                if (cVar == null || (wVar = bVar.f6397f) == null) {
                    return;
                }
                cVar.d(wVar);
            }
        }

        public b(View view) {
            super(view);
            view.setOnClickListener(this);
            this.f6395d.setOnClickListener(new a());
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            j0.w wVar;
            C0408x.c cVar = this.f6396e;
            if (cVar == null || (wVar = this.f6397f) == null) {
                return;
            }
            cVar.b(wVar);
        }
    }

    /* JADX INFO: renamed from: l0.y$c */
    public static class c extends a implements View.OnClickListener {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public C0408x.c f6399e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public j0.e f6400f;

        public c(View view) {
            super(view);
            view.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            j0.e eVar;
            C0408x.c cVar = this.f6399e;
            if (cVar == null || (eVar = this.f6400f) == null) {
                return;
            }
            cVar.f(eVar);
        }
    }

    /* JADX INFO: renamed from: l0.y$d */
    public static class d extends a {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final View f6401e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final View f6402f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public C0408x.c f6403g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public String f6404h;

        /* JADX INFO: renamed from: l0.y$d$a */
        public class a implements View.OnClickListener {
            public a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String str;
                d dVar = d.this;
                if (dVar.f6403g == null || (str = dVar.f6404h) == null || str.length() <= 0) {
                    return;
                }
                d dVar2 = d.this;
                dVar2.f6403g.a(dVar2.f6404h);
            }
        }

        /* JADX INFO: renamed from: l0.y$d$b */
        public class b implements View.OnClickListener {
            public b() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String str;
                d dVar = d.this;
                if (dVar.f6403g == null || (str = dVar.f6404h) == null || str.length() <= 0) {
                    return;
                }
                d dVar2 = d.this;
                dVar2.f6403g.c(dVar2.f6404h);
            }
        }

        public d(View view) {
            super(view);
            this.f6404h = "";
            View viewFindViewById = view.findViewById(f0.f.f5086k);
            this.f6401e = viewFindViewById;
            View viewFindViewById2 = view.findViewById(f0.f.g3);
            this.f6402f = viewFindViewById2;
            viewFindViewById.setOnClickListener(new a());
            viewFindViewById2.setOnClickListener(new b());
        }
    }

    /* JADX INFO: renamed from: l0.y$e */
    public static class e extends a implements View.OnClickListener {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public C0408x.c f6407e;

        public e(View view) {
            super(view);
            view.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C0408x.c cVar = this.f6407e;
            if (cVar != null) {
                cVar.e();
            }
        }
    }

    public static View c(View view, ViewGroup viewGroup, LayoutInflater layoutInflater, j0.w wVar, C0408x.c cVar) {
        b bVar;
        if (view == null || !(view.getTag() instanceof b)) {
            view = layoutInflater.inflate(f0.g.w0, viewGroup, false);
            bVar = new b(view);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        bVar.f6396e = cVar;
        bVar.f6397f = wVar;
        TextView textView = bVar.f6392a;
        if (textView != null) {
            textView.setText(j0.d.b(wVar));
        }
        TextView textView2 = bVar.f6393b;
        if (textView2 != null) {
            textView2.setText(g(wVar));
        }
        R0.b(bVar.f6394c, wVar == null ? "" : wVar.f5650f);
        bVar.f6395d.setColorFilter((ColorFilter) null);
        if (wVar == null || j(wVar.f5651g).length() == 0) {
            bVar.f6395d.setImageResource(f0.e.f4999T);
            bVar.f6395d.setTag(null);
        } else {
            AbstractC0455v.h(bVar.f6395d, wVar.f5651g);
        }
        return view;
    }

    public static View d(View view, ViewGroup viewGroup, LayoutInflater layoutInflater, j0.e eVar, C0408x.c cVar) {
        c cVar2;
        if (view == null || !(view.getTag() instanceof c)) {
            view = layoutInflater.inflate(f0.g.w0, viewGroup, false);
            cVar2 = new c(view);
            view.setTag(cVar2);
        } else {
            cVar2 = (c) view.getTag();
        }
        cVar2.f6399e = cVar;
        cVar2.f6400f = eVar;
        TextView textView = cVar2.f6392a;
        if (textView != null) {
            textView.setText(eVar == null ? "" : j(eVar.f5505b));
        }
        TextView textView2 = cVar2.f6393b;
        if (textView2 != null) {
            textView2.setText(eVar == null ? "" : j(eVar.f5504a));
        }
        R0.a(cVar2.f6394c, "");
        cVar2.f6395d.setColorFilter((ColorFilter) null);
        if (eVar == null || j(eVar.f5506c).length() == 0) {
            cVar2.f6395d.setImageResource(f0.e.f4997R);
            cVar2.f6395d.setTag(null);
        } else {
            AbstractC0455v.h(cVar2.f6395d, eVar.f5506c);
        }
        cVar2.f6395d.setOnClickListener(null);
        return view;
    }

    public static View e(View view, ViewGroup viewGroup, LayoutInflater layoutInflater, C0408x.b bVar, C0408x.c cVar) {
        d dVar;
        String str;
        if (view == null || !(view.getTag() instanceof d)) {
            view = layoutInflater.inflate(f0.g.x0, viewGroup, false);
            dVar = new d(view);
            view.setTag(dVar);
        } else {
            dVar = (d) view.getTag();
        }
        dVar.f6403g = cVar;
        dVar.f6404h = bVar == null ? "" : j(bVar.f6371a);
        String str2 = (bVar == null || j(bVar.f6373c).length() <= 0) ? bVar == null ? "" : bVar.f6372b : bVar.f6373c;
        TextView textView = dVar.f6392a;
        if (str2 == null) {
            str2 = "";
        }
        textView.setText(str2);
        R0.b(dVar.f6394c, bVar != null ? bVar.f6374d : "");
        TextView textView2 = dVar.f6393b;
        if (bVar == null || j(bVar.f6372b).length() <= 0) {
            str = "好友请求";
        } else {
            str = "UID: " + bVar.f6372b;
        }
        textView2.setText(str);
        dVar.f6395d.setColorFilter((ColorFilter) null);
        if (bVar == null || j(bVar.f6375e).length() == 0) {
            dVar.f6395d.setImageResource(f0.e.f4999T);
            dVar.f6395d.setTag(null);
        } else {
            AbstractC0455v.h(dVar.f6395d, bVar.f6375e);
        }
        dVar.f6395d.setOnClickListener(null);
        view.setOnClickListener(null);
        return view;
    }

    public static View f(Context context, View view, ViewGroup viewGroup, LayoutInflater layoutInflater, C0408x.c cVar) {
        e eVar;
        if (view == null || !(view.getTag() instanceof e)) {
            view = layoutInflater.inflate(f0.g.w0, viewGroup, false);
            eVar = new e(view);
            view.setTag(eVar);
        } else {
            eVar = (e) view.getTag();
        }
        eVar.f6407e = cVar;
        TextView textView = eVar.f6392a;
        if (textView != null) {
            textView.setText("系统通知");
        }
        TextView textView2 = eVar.f6393b;
        if (textView2 != null) {
            textView2.setText("官方公告和通知");
        }
        R0.a(eVar.f6394c, "");
        eVar.f6395d.setImageResource(f0.i.f5176a);
        try {
            eVar.f6395d.setColorFilter(context.getResources().getColor(f0.d.f4974k));
        } catch (Exception unused) {
        }
        eVar.f6395d.setOnClickListener(null);
        return view;
    }

    public static String g(j0.w wVar) {
        StringBuilder sb;
        if (wVar == null) {
            return "";
        }
        String strJ = j(wVar.f5646b);
        String strJ2 = j(wVar.f5649e);
        String strJ3 = j(wVar.f5648d);
        if (strJ3.length() == 0) {
            strJ3 = j(wVar.f5647c);
        }
        if (strJ3.length() == 0) {
            strJ3 = strJ;
        }
        if (strJ2.length() <= 0 || strJ3.length() <= 0 || strJ2.equals(strJ3)) {
            if (strJ.length() <= 0) {
                return "";
            }
            return "UID: " + strJ;
        }
        if (strJ.length() > 0) {
            sb = new StringBuilder();
            sb.append("原名：");
            sb.append(strJ3);
            sb.append(" · UID: ");
            sb.append(strJ);
        } else {
            sb = new StringBuilder();
            sb.append("原名：");
            sb.append(strJ3);
        }
        return sb.toString();
    }

    public static TextView h(View view) {
        TextView textView = (TextView) view.findViewById(f0.f.Nd);
        return textView == null ? (TextView) view.findViewById(f0.f.Vd) : textView;
    }

    public static TextView i(View view) {
        TextView textView = (TextView) view.findViewById(f0.f.ub);
        return textView == null ? (TextView) view.findViewById(f0.f.Yd) : textView;
    }

    public static String j(String str) {
        return str == null ? "" : str.trim();
    }
}

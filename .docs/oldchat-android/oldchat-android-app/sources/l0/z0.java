package l0;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import o.c;

/* JADX INFO: loaded from: classes.dex */
public class z0 extends RecyclerView.g {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List f6408c = new ArrayList();

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ c f6409a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ A0 f6410b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f6411c;

        public a(c cVar, A0 a02, String str) {
            this.f6409a = cVar;
            this.f6410b = a02;
            this.f6411c = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            z0.this.A(this.f6409a, this.f6410b, this.f6411c);
        }
    }

    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ c f6413a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ A0 f6414b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f6415c;

        public b(c cVar, A0 a02, String str) {
            this.f6413a = cVar;
            this.f6414b = a02;
            this.f6415c = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            z0.this.A(this.f6413a, this.f6414b, this.f6415c);
        }
    }

    public static class c extends RecyclerView.B {

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public TextView f6417r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public TextView f6418s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public TextView f6419t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public TextView f6420u;

        public c(View view) {
            super(view);
            this.f6417r = (TextView) view.findViewById(f0.f.Yd);
            this.f6418s = (TextView) view.findViewById(f0.f.a9);
            this.f6419t = (TextView) view.findViewById(f0.f.Ca);
            this.f6420u = (TextView) view.findViewById(f0.f.Ud);
        }
    }

    public final void A(c cVar, A0 a02, String str) {
        String str2;
        if (cVar == null || cVar.f1354a == null) {
            return;
        }
        String str3 = (a02 == null || (str2 = a02.f5763a) == null || str2.length() == 0) ? "反馈详情" : a02.f5763a;
        if (str == null || str.length() == 0) {
            str = "(无内容)";
        }
        new c.a(cVar.f1354a.getContext()).t(str3).i(str).q("关闭", null).v();
    }

    @Override // android.support.v7.widget.RecyclerView.g
    public int c() {
        return this.f6408c.size();
    }

    public final String w(String str, int i2) {
        if (str == null || str.length() <= i2) {
            return str == null ? "" : str;
        }
        String strTrim = str.substring(0, i2).trim();
        if (strTrim.length() == 0) {
            strTrim = str.substring(0, Math.min(i2, str.length()));
        }
        return strTrim + "… 点击查看详情";
    }

    @Override // android.support.v7.widget.RecyclerView.g
    /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
    public void l(c cVar, int i2) {
        A0 a02 = (A0) this.f6408c.get(i2);
        TextView textView = cVar.f6417r;
        String str = a02.f5763a;
        if (str == null) {
            str = "";
        }
        textView.setText(str);
        TextView textView2 = cVar.f6419t;
        String str2 = a02.f5765c;
        if (str2 == null) {
            str2 = "";
        }
        textView2.setText(str2);
        TextView textView3 = cVar.f6420u;
        String str3 = a02.f5766d;
        if (str3 == null) {
            str3 = "";
        }
        textView3.setText(str3);
        String str4 = a02.f5764b;
        String str5 = str4 != null ? str4 : "";
        int i3 = a02.f5768f;
        if (i3 <= 0 || str5.length() <= i3) {
            cVar.f6418s.setText(str5);
            cVar.f6418s.setOnClickListener(null);
            cVar.f1354a.setOnClickListener(null);
        } else {
            cVar.f6418s.setText(w(str5, i3));
            cVar.f6418s.setOnClickListener(new a(cVar, a02, str5));
            cVar.f1354a.setOnClickListener(new b(cVar, a02, str5));
        }
        int i4 = a02.f5767e;
        cVar.f6420u.setBackgroundResource(i4 == 1 ? f0.e.f5014e : i4 == 2 ? f0.e.f5016f : f0.e.f5012d);
    }

    @Override // android.support.v7.widget.RecyclerView.g
    /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
    public c n(ViewGroup viewGroup, int i2) {
        return new c(LayoutInflater.from(viewGroup.getContext()).inflate(f0.g.Q0, viewGroup, false));
    }

    public void z(List list) {
        this.f6408c.clear();
        if (list != null) {
            this.f6408c.addAll(list);
        }
        g();
    }
}

package l0;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: l0.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0408x extends BaseAdapter {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f6364k;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Context f6366m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final LayoutInflater f6367n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final c f6368o;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f6354a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f6355b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List f6356c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f6357d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f6358e = new ArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f6359f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f6360g = true;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f6361h = true;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f6362i = true;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f6363j = "";

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final SparseBooleanArray f6365l = new SparseBooleanArray();

    /* JADX INFO: renamed from: l0.x$a */
    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ d f6369a;

        public a(d dVar) {
            this.f6369a = dVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C0408x.this.p(this.f6369a.f6376a);
        }
    }

    /* JADX INFO: renamed from: l0.x$b */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f6371a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f6372b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f6373c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f6374d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f6375e;

        public b(String str, String str2, String str3, String str4, String str5) {
            this.f6371a = str;
            this.f6372b = str2;
            this.f6373c = str3;
            this.f6374d = str4;
            this.f6375e = str5;
        }
    }

    /* JADX INFO: renamed from: l0.x$c */
    public interface c {
        void a(String str);

        void b(j0.w wVar);

        void c(String str);

        void d(j0.w wVar);

        void e();

        void f(j0.e eVar);
    }

    /* JADX INFO: renamed from: l0.x$d */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f6376a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f6377b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f6378c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final boolean f6379d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final boolean f6380e;

        public d(String str, String str2, int i2, boolean z2, boolean z3) {
            this.f6376a = str;
            this.f6377b = str2;
            this.f6378c = i2;
            this.f6379d = z2;
            this.f6380e = z3;
        }
    }

    /* JADX INFO: renamed from: l0.x$e */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e f6381a = new e();
    }

    public C0408x(Context context, c cVar) {
        this.f6366m = context;
        this.f6367n = LayoutInflater.from(context);
        this.f6368o = cVar;
    }

    public final void b(String str, String str2, int i2, boolean z2) {
        this.f6354a.add(new d(str, str2, i2, z2, true));
    }

    public final View c(Object obj, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f6367n.inflate(f0.g.y0, viewGroup, false);
        }
        d dVar = (d) obj;
        TextView textView = (TextView) view.findViewById(f0.f.Jd);
        TextView textView2 = (TextView) view.findViewById(f0.f.Z8);
        View viewFindViewById = view.findViewById(f0.f.re);
        View viewFindViewById2 = view.findViewById(f0.f.i8);
        if (viewFindViewById2 == null) {
            viewFindViewById2 = view;
        }
        textView.setText(dVar.f6377b);
        int i2 = dVar.f6378c;
        textView2.setText(i2 > 99 ? "99+" : String.valueOf(i2));
        textView2.setBackgroundResource(f0.e.f5039z);
        textView2.setTextColor(this.f6366m.getResources().getColor(f0.d.f4975l));
        viewFindViewById.setBackgroundResource(dVar.f6379d ? f0.e.f5037x : f0.e.f5036w);
        if (!dVar.f6380e || dVar.f6376a == null || this.f6363j.length() > 0) {
            view.setOnClickListener(null);
            viewFindViewById2.setOnClickListener(null);
            return view;
        }
        a aVar = new a(dVar);
        view.setOnClickListener(aVar);
        viewFindViewById2.setOnClickListener(aVar);
        return view;
    }

    public final void d() {
        this.f6364k = null;
        this.f6365l.clear();
    }

    public final boolean e(String str, String str2) {
        if (str2 == null || str2.length() == 0) {
            return true;
        }
        if (str == null) {
            return false;
        }
        try {
            return str.toLowerCase().contains(str2);
        } catch (Exception unused) {
            return false;
        }
    }

    public final float f(int i2) {
        return this.f6366m.getResources().getDisplayMetrics().density * i2;
    }

    public void g(String str) {
        this.f6363j = str == null ? "" : str.trim();
        d();
        m();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f6354a.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        return this.f6354a.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i2) {
        Object obj = this.f6354a.get(i2);
        if (obj instanceof d) {
            return 0;
        }
        if (obj instanceof b) {
            return 1;
        }
        if (obj instanceof j0.e) {
            return 2;
        }
        return obj instanceof e ? 4 : 3;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        int itemViewType = getItemViewType(i2);
        Object obj = this.f6354a.get(i2);
        if (itemViewType == 0) {
            return c(obj, view, viewGroup);
        }
        View viewF = itemViewType == 4 ? AbstractC0409y.f(this.f6366m, view, viewGroup, this.f6367n, this.f6368o) : itemViewType == 1 ? AbstractC0409y.e(view, viewGroup, this.f6367n, (b) obj, this.f6368o) : itemViewType == 2 ? AbstractC0409y.d(view, viewGroup, this.f6367n, (j0.e) obj, this.f6368o) : AbstractC0409y.c(view, viewGroup, this.f6367n, (j0.w) obj, this.f6368o);
        l(viewF, i2);
        return viewF;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 5;
    }

    public final int h(int i2) {
        int i3 = 0;
        for (int i4 = i2 - 1; i4 >= 0; i4--) {
            if (this.f6354a.get(i4) instanceof d) {
                return i3;
            }
            i3++;
        }
        return -1;
    }

    public final boolean i(int i2) {
        while (i2 >= 0) {
            Object obj = this.f6354a.get(i2);
            if (obj instanceof d) {
                String str = ((d) obj).f6376a;
                return str != null && str.equals(this.f6364k);
            }
            i2--;
        }
        return false;
    }

    public final boolean j(String str) {
        if ("groups".equals(str)) {
            return this.f6359f;
        }
        if ("recent".equals(str)) {
            return this.f6360g;
        }
        if ("requests".equals(str)) {
            return this.f6361h;
        }
        if ("friends".equals(str)) {
            return this.f6362i;
        }
        return false;
    }

    public final boolean k(j0.w wVar, String str) {
        if (wVar == null) {
            return false;
        }
        return e(wVar.f5649e, str) || e(wVar.f5648d, str) || e(wVar.f5647c, str) || e(wVar.f5646b, str);
    }

    public final void l(View view, int i2) {
        if (view == null) {
            return;
        }
        if (this.f6364k == null || this.f6363j.length() > 0) {
            view.clearAnimation();
            return;
        }
        if (!i(i2)) {
            view.clearAnimation();
            return;
        }
        if (this.f6365l.get(i2)) {
            return;
        }
        this.f6365l.put(i2, true);
        int iH = h(i2);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setInterpolator(new DecelerateInterpolator());
        animationSet.setDuration(220L);
        if (iH >= 0) {
            long j2 = ((long) iH) * 22;
            if (j2 > 120) {
                j2 = 120;
            }
            animationSet.setStartOffset(j2);
        }
        animationSet.addAnimation(new AlphaAnimation(0.3f, 1.0f));
        animationSet.addAnimation(new TranslateAnimation(0.0f, 0.0f, f(12), 0.0f));
        view.startAnimation(animationSet);
    }

    public final void m() {
        this.f6354a.clear();
        String str = this.f6363j;
        String strTrim = str == null ? "" : str.trim();
        if (strTrim.length() > 0) {
            n(strTrim.toLowerCase());
            notifyDataSetChanged();
            return;
        }
        b("groups", "GROUPS", this.f6355b.size(), this.f6359f);
        if (this.f6359f) {
            this.f6354a.addAll(this.f6355b);
        }
        b("recent", "RECENT", this.f6356c.size(), this.f6360g);
        if (this.f6360g) {
            this.f6354a.addAll(this.f6356c);
        }
        b("requests", "REQUESTS", this.f6358e.size(), this.f6361h);
        if (this.f6361h) {
            this.f6354a.add(e.f6381a);
            this.f6354a.addAll(this.f6358e);
        }
        b("friends", "FRIENDS", this.f6357d.size(), this.f6362i);
        if (this.f6362i) {
            this.f6354a.addAll(this.f6357d);
        }
        notifyDataSetChanged();
    }

    public final void n(String str) {
        ArrayList arrayList = new ArrayList();
        if (e("系统通知 官方公告和通知", str) || e("系统通知", str)) {
            arrayList.add(e.f6381a);
        }
        for (int i2 = 0; i2 < this.f6355b.size(); i2++) {
            j0.e eVar = (j0.e) this.f6355b.get(i2);
            if (eVar != null && (e(eVar.f5505b, str) || e(eVar.f5504a, str))) {
                arrayList.add(eVar);
            }
        }
        for (int i3 = 0; i3 < this.f6358e.size(); i3++) {
            b bVar = (b) this.f6358e.get(i3);
            if (bVar != null && (e(bVar.f6373c, str) || e(bVar.f6372b, str))) {
                arrayList.add(bVar);
            }
        }
        for (int i4 = 0; i4 < this.f6356c.size(); i4++) {
            if (k((j0.w) this.f6356c.get(i4), str)) {
                arrayList.add(this.f6356c.get(i4));
            }
        }
        for (int i5 = 0; i5 < this.f6357d.size(); i5++) {
            if (k((j0.w) this.f6357d.get(i5), str)) {
                arrayList.add(this.f6357d.get(i5));
            }
        }
        if (arrayList.isEmpty()) {
            this.f6354a.add(new d(null, "无匹配结果", 0, true, false));
        } else {
            this.f6354a.add(new d(null, "搜索结果", arrayList.size(), true, false));
            this.f6354a.addAll(arrayList);
        }
    }

    public void o(List list, List list2, List list3, List list4) {
        this.f6355b.clear();
        this.f6356c.clear();
        this.f6357d.clear();
        this.f6358e.clear();
        if (list != null) {
            this.f6355b.addAll(list);
        }
        if (list2 != null) {
            this.f6356c.addAll(list2);
        }
        if (list3 != null) {
            this.f6357d.addAll(list3);
        }
        if (list4 != null) {
            this.f6358e.addAll(list4);
        }
        d();
        m();
    }

    public final void p(String str) {
        boolean zJ = j(str);
        if ("groups".equals(str)) {
            this.f6359f = !this.f6359f;
        } else if ("recent".equals(str)) {
            this.f6360g = !this.f6360g;
        } else if ("requests".equals(str)) {
            this.f6361h = !this.f6361h;
        } else if ("friends".equals(str)) {
            this.f6362i = !this.f6362i;
        }
        boolean zJ2 = j(str);
        if (zJ || !zJ2) {
            d();
        } else {
            this.f6364k = str;
            this.f6365l.clear();
        }
        m();
    }
}

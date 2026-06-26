package l0;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import o0.AbstractC0451q;
import o0.AbstractC0455v;

/* JADX INFO: loaded from: classes.dex */
public class L extends BaseAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f5925a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f5928d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f5926b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List f5927c = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f5929e = "";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5930f = 0;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ImageView f5931a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f5932b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final TextView f5933c;

        public a(View view) {
            this.f5931a = (ImageView) view.findViewById(f0.f.m5);
            this.f5932b = (TextView) view.findViewById(f0.f.ub);
            this.f5933c = (TextView) view.findViewById(f0.f.be);
        }
    }

    public L(Context context, List list, String str) {
        this.f5925a = context;
        this.f5928d = str;
        e(list);
    }

    public final String a(j0.h hVar) {
        if (hVar == null) {
            return "";
        }
        String str = hVar.f5524c;
        if (str != null && !str.isEmpty()) {
            return hVar.f5524c;
        }
        String str2 = hVar.f5523b;
        if (str2 != null && !str2.isEmpty()) {
            return hVar.f5523b;
        }
        String str3 = hVar.f5522a;
        return str3 == null ? "" : str3;
    }

    public void b(String str) {
        String str2;
        String str3;
        this.f5929e = str == null ? "" : str.trim();
        this.f5927c.clear();
        String lowerCase = this.f5929e.toLowerCase();
        if (this.f5930f >= 1 && (lowerCase.length() == 0 || "所有人".contains(lowerCase) || "all".contains(lowerCase))) {
            j0.h hVar = new j0.h();
            hVar.f5522a = "ALL";
            hVar.f5524c = "所有人";
            hVar.f5523b = "all";
            hVar.f5527f = -1;
            this.f5927c.add(hVar);
        }
        for (int i2 = 0; i2 < this.f5926b.size(); i2++) {
            j0.h hVar2 = (j0.h) this.f5926b.get(i2);
            if (hVar2 != null && (str2 = hVar2.f5522a) != null && !str2.isEmpty() && ((str3 = this.f5928d) == null || !str3.equals(hVar2.f5522a))) {
                String strA = a(hVar2);
                if (lowerCase.length() == 0) {
                    this.f5927c.add(hVar2);
                } else {
                    String lowerCase2 = strA == null ? "" : strA.toLowerCase();
                    String lowerCase3 = hVar2.f5522a.toLowerCase();
                    String str4 = hVar2.f5523b;
                    String lowerCase4 = str4 == null ? "" : str4.toLowerCase();
                    if (lowerCase2.contains(lowerCase) || lowerCase3.contains(lowerCase) || lowerCase4.contains(lowerCase)) {
                        this.f5927c.add(hVar2);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public j0.h getItem(int i2) {
        return (j0.h) this.f5927c.get(i2);
    }

    public void d(String str) {
        this.f5928d = str;
        b(this.f5929e);
    }

    public void e(List list) {
        this.f5926b.clear();
        if (list != null && !list.isEmpty()) {
            this.f5926b.addAll(list);
        }
        b(this.f5929e);
    }

    public void f(int i2) {
        this.f5930f = i2;
        b(this.f5929e);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f5927c.size();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(this.f5925a).inflate(f0.g.B0, viewGroup, false);
            aVar = new a(view);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        j0.h item = getItem(i2);
        String strA = a(item);
        TextView textView = aVar.f5932b;
        if (strA == null) {
            strA = "";
        }
        textView.setText(strA);
        if ("ALL".equals(item.f5522a)) {
            aVar.f5933c.setText("提及所有成员");
            aVar.f5931a.setImageResource(f0.e.f5005Z);
        } else {
            TextView textView2 = aVar.f5933c;
            String str = item.f5522a;
            textView2.setText(str != null ? str : "");
            String strB = item.f5526e;
            if (strB == null || strB.isEmpty()) {
                strB = AbstractC0451q.b(this.f5925a, item.f5522a);
            }
            AbstractC0455v.h(aVar.f5931a, strB);
        }
        return view;
    }
}

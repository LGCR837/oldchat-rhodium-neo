package com.im.oldchat.ui;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import l0.R0;
import o0.AbstractC0451q;
import o0.AbstractC0455v;

/* JADX INFO: loaded from: classes.dex */
public class D extends BaseAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f2564a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List f2565b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2566c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f2567d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Map f2568e = new HashMap();

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.h f2569a;

        public a(j0.h hVar) {
            this.f2569a = hVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (D.this.f2567d != null) {
                D.this.f2567d.b(this.f2569a);
            }
        }
    }

    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.h f2571a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f2572b;

        public b(j0.h hVar, boolean z2) {
            this.f2571a = hVar;
            this.f2572b = z2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (D.this.f2567d != null) {
                D.this.f2567d.a(this.f2571a, !this.f2572b);
            }
        }
    }

    public class c implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.h f2574a;

        public c(j0.h hVar) {
            this.f2574a = hVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String str = this.f2574a.f5522a;
            if (str == null || str.isEmpty()) {
                return;
            }
            Intent intent = new Intent(D.this.f2564a, (Class<?>) UserSpaceActivity.class);
            intent.putExtra("uid", this.f2574a.f5522a);
            D.this.f2564a.startActivity(intent);
        }
    }

    public interface d {
        void a(j0.h hVar, boolean z2);

        void b(j0.h hVar);
    }

    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ImageView f2576a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f2577b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final TextView f2578c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final TextView f2579d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final TextView f2580e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final TextView f2581f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final TextView f2582g;

        public e(View view) {
            this.f2576a = (ImageView) view.findViewById(f0.f.m5);
            this.f2577b = (TextView) view.findViewById(f0.f.Yd);
            this.f2578c = (TextView) view.findViewById(f0.f.Vd);
            this.f2579d = (TextView) view.findViewById(f0.f.yd);
            this.f2580e = (TextView) view.findViewById(f0.f.Zd);
            this.f2581f = (TextView) view.findViewById(f0.f.m1);
            this.f2582g = (TextView) view.findViewById(f0.f.f5100t);
        }
    }

    public D(Context context, List list, int i2, d dVar) {
        this.f2564a = context;
        this.f2565b = list;
        this.f2566c = i2;
        this.f2567d = dVar;
        f();
    }

    public final void c(TextView textView, int i2) {
        if (textView == null) {
            return;
        }
        if (i2 == 2) {
            textView.setText("群主");
            textView.setBackgroundResource(f0.e.f5008b);
            textView.setVisibility(0);
        } else {
            if (i2 != 1) {
                textView.setVisibility(8);
                return;
            }
            textView.setText("管理员");
            textView.setBackgroundResource(f0.e.f5006a);
            textView.setVisibility(0);
        }
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public j0.h getItem(int i2) {
        List list = this.f2565b;
        if (list == null || i2 < 0 || i2 >= list.size()) {
            return null;
        }
        return (j0.h) this.f2565b.get(i2);
    }

    public final String e(String str) {
        if (str == null) {
            return "";
        }
        String strTrim = str.trim();
        while (strTrim.startsWith("@")) {
            strTrim = strTrim.substring(1).trim();
        }
        return strTrim.length() == 0 ? "" : strTrim.toUpperCase(Locale.US);
    }

    public final void f() {
        this.f2568e.clear();
        List list = this.f2565b;
        if (list == null || list.isEmpty()) {
            return;
        }
        HashMap map = new HashMap();
        for (int i2 = 0; i2 < this.f2565b.size(); i2++) {
            j0.h hVar = (j0.h) this.f2565b.get(i2);
            if (hVar != null && !TextUtils.isEmpty(hVar.f5522a)) {
                if (TextUtils.isEmpty(hVar.f5526e)) {
                    map.put(hVar.f5522a, "");
                    map.put(e(hVar.f5522a), "");
                } else {
                    this.f2568e.put(hVar.f5522a, hVar.f5526e);
                    this.f2568e.put(e(hVar.f5522a), hVar.f5526e);
                }
            }
        }
        if (map.isEmpty()) {
            return;
        }
        AbstractC0451q.a(this.f2564a, map);
        for (int i3 = 0; i3 < this.f2565b.size(); i3++) {
            j0.h hVar2 = (j0.h) this.f2565b.get(i3);
            if (hVar2 != null && !TextUtils.isEmpty(hVar2.f5522a) && TextUtils.isEmpty(hVar2.f5526e)) {
                String str = (String) map.get(hVar2.f5522a);
                if (TextUtils.isEmpty(str)) {
                    str = (String) map.get(e(hVar2.f5522a));
                }
                if (!TextUtils.isEmpty(str)) {
                    hVar2.f5526e = str;
                    this.f2568e.put(hVar2.f5522a, str);
                    this.f2568e.put(e(hVar2.f5522a), str);
                }
            }
        }
    }

    public void g(List list) {
        this.f2565b = list;
        f();
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List list = this.f2565b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        e eVar;
        j0.h item = getItem(i2);
        if (item == null) {
            if (view != null) {
                return view;
            }
            View viewInflate = LayoutInflater.from(this.f2564a).inflate(f0.g.A0, viewGroup, false);
            viewInflate.setTag(new e(viewInflate));
            return viewInflate;
        }
        if (view == null) {
            view = LayoutInflater.from(this.f2564a).inflate(f0.g.A0, viewGroup, false);
            eVar = new e(view);
            view.setTag(eVar);
        } else {
            eVar = (e) view.getTag();
        }
        String str = item.f5524c;
        String str2 = (str == null || str.isEmpty()) ? item.f5523b : item.f5524c;
        TextView textView = eVar.f2577b;
        if (str2 == null) {
            str2 = "";
        }
        textView.setText(str2);
        c(eVar.f2579d, item.f5527f);
        R0.a(eVar.f2580e, item.f5525d);
        eVar.f2578c.setText(item.f5522a);
        String str3 = item.f5526e;
        if (TextUtils.isEmpty(str3)) {
            str3 = (String) this.f2568e.get(item.f5522a);
            if (TextUtils.isEmpty(str3)) {
                str3 = (String) this.f2568e.get(e(item.f5522a));
            }
        }
        AbstractC0455v.h(eVar.f2576a, str3);
        int i3 = item.f5527f;
        boolean z2 = i3 == 2;
        boolean z3 = i3 == 1;
        int i4 = this.f2566c;
        boolean z4 = i4 >= 1 && !z2;
        boolean z5 = i4 == 2 && !z2;
        eVar.f2581f.setVisibility(z4 ? 0 : 8);
        eVar.f2582g.setVisibility(z5 ? 0 : 8);
        eVar.f2582g.setText(z3 ? "取消管理员" : "设为管理员");
        eVar.f2576a.setOnClickListener(null);
        eVar.f2581f.setOnClickListener(new a(item));
        eVar.f2582g.setOnClickListener(new b(item, z3));
        eVar.f2576a.setOnClickListener(new c(item));
        return view;
    }

    public void h(int i2) {
        this.f2566c = i2;
        notifyDataSetChanged();
    }
}

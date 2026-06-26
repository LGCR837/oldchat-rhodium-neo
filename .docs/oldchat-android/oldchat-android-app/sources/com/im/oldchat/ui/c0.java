package com.im.oldchat.ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import l0.R0;
import v.C0475d;
import x.U;

/* JADX INFO: loaded from: classes.dex */
public class c0 extends BaseAdapter {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final SimpleDateFormat f4457e = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f4458a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f4459b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LayoutInflater f4460c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f4461d;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.t f4462a;

        public a(j0.t tVar) {
            this.f4462a = tVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (c0.this.f4461d != null) {
                c0.this.f4461d.j(this.f4462a);
            }
        }
    }

    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.t f4464a;

        public b(j0.t tVar) {
            this.f4464a = tVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (c0.this.f4461d != null) {
                c0.this.f4461d.m(this.f4464a);
            }
        }
    }

    public class c implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.t f4466a;

        public class a implements U.c {
            public a() {
            }

            @Override // x.U.c
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (c0.this.f4461d == null) {
                    return true;
                }
                int itemId = menuItem.getItemId();
                if (itemId == f0.f.f5078g) {
                    c0.this.f4461d.f(c.this.f4466a);
                    return true;
                }
                if (itemId == f0.f.f5082i) {
                    c0.this.f4461d.c(c.this.f4466a);
                    return true;
                }
                if (itemId == f0.f.f5080h) {
                    c0.this.f4461d.g(c.this.f4466a);
                    return true;
                }
                if (itemId != f0.f.f5076f) {
                    return false;
                }
                c0.this.f4461d.s(c.this.f4466a);
                return true;
            }
        }

        public c(j0.t tVar) {
            this.f4466a = tVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            x.U u2 = new x.U(new C0475d(c0.this.f4458a, o0.V.g(c0.this.f4458a) ? f0.k.f5259p : f0.k.f5260q), view);
            u2.b().inflate(f0.h.f5175b, u2.a());
            u2.a().findItem(f0.f.f5076f).setVisible(this.f4466a.f5631n);
            u2.c(new a());
            u2.d();
        }
    }

    public interface d {
        void c(j0.t tVar);

        void f(j0.t tVar);

        void g(j0.t tVar);

        void j(j0.t tVar);

        void m(j0.t tVar);

        void s(j0.t tVar);
    }

    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextView f4469a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f4470b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final TextView f4471c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final TextView f4472d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final View f4473e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final View f4474f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final View f4475g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final ImageView f4476h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final TextView f4477i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final TextView f4478j;

        public e(View view) {
            this.f4469a = (TextView) view.findViewById(f0.f.ld);
            this.f4470b = (TextView) view.findViewById(f0.f.vd);
            this.f4471c = (TextView) view.findViewById(f0.f.wd);
            this.f4472d = (TextView) view.findViewById(f0.f.kd);
            this.f4473e = view.findViewById(f0.f.m3);
            this.f4474f = view.findViewById(f0.f.l3);
            this.f4475g = view.findViewById(f0.f.n3);
            this.f4476h = (ImageView) view.findViewById(f0.f.h6);
            this.f4477i = (TextView) view.findViewById(f0.f.jd);
            this.f4478j = (TextView) view.findViewById(f0.f.id);
        }
    }

    public c0(Context context, List list, d dVar) {
        this.f4458a = context;
        this.f4459b = list;
        this.f4461d = dVar;
        this.f4460c = LayoutInflater.from(context);
    }

    public final String c(long j2) {
        if (j2 <= 0) {
            return "0B";
        }
        float f2 = j2;
        if (f2 < 1024.0f) {
            return ((int) f2) + "B";
        }
        float f3 = f2 / 1024.0f;
        if (f3 < 1024.0f) {
            return String.format(Locale.getDefault(), "%.1fKB", Float.valueOf(f3));
        }
        float f4 = f3 / 1024.0f;
        return f4 < 1024.0f ? String.format(Locale.getDefault(), "%.1fMB", Float.valueOf(f4)) : String.format(Locale.getDefault(), "%.1fGB", Float.valueOf(f4 / 1024.0f));
    }

    public final String d(long j2) {
        if (j2 <= 0) {
            return "";
        }
        return f4457e.format(new Date(j2 * 1000));
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List list = this.f4459b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        return this.f4459b.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        e eVar;
        if (view == null) {
            view = this.f4460c.inflate(f0.g.Y0, viewGroup, false);
            eVar = new e(view);
            view.setTag(eVar);
        } else {
            eVar = (e) view.getTag();
        }
        j0.t tVar = (j0.t) this.f4459b.get(i2);
        if (tVar != null) {
            TextView textView = eVar.f4469a;
            String str = tVar.f5620c;
            if (str == null) {
                str = "";
            }
            textView.setText(str);
            String str2 = !TextUtils.isEmpty(tVar.f5624g) ? tVar.f5624g : tVar.f5623f;
            if (TextUtils.isEmpty(str2)) {
                str2 = "未知";
            }
            eVar.f4470b.setText("上传者: " + str2);
            R0.a(eVar.f4471c, tVar.f5625h);
            eVar.f4472d.setText(c(tVar.f5622e) + " · " + d(tVar.f5627j));
            if (tVar.f5630m) {
                eVar.f4476h.setImageResource(f0.e.f5002W);
                eVar.f4476h.setColorFilter(-50384);
                eVar.f4477i.setTextColor(-50384);
                TextView textView2 = eVar.f4477i;
                int i3 = tVar.f5628k;
                textView2.setText(i3 > 0 ? String.valueOf(i3) : "已赞");
            } else {
                eVar.f4476h.setImageResource(f0.e.f5001V);
                eVar.f4476h.setColorFilter(-6710887);
                eVar.f4477i.setTextColor(-10066330);
                TextView textView3 = eVar.f4477i;
                int i4 = tVar.f5628k;
                textView3.setText(i4 > 0 ? String.valueOf(i4) : "赞");
            }
            TextView textView4 = eVar.f4478j;
            int i5 = tVar.f5629l;
            textView4.setText(i5 > 0 ? String.valueOf(i5) : "评论");
            eVar.f4473e.setOnClickListener(new a(tVar));
            eVar.f4474f.setOnClickListener(new b(tVar));
            eVar.f4475g.setOnClickListener(new c(tVar));
        }
        return view;
    }
}

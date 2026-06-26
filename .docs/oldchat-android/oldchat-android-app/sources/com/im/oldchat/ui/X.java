package com.im.oldchat.ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import d.AbstractC0235a;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import l0.R0;
import o0.AbstractC0455v;

/* JADX INFO: loaded from: classes.dex */
public class X extends BaseAdapter {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final SimpleDateFormat f4287f = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f4288a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f4289b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final e f4290c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LayoutInflater f4291d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f4292e = new ArrayList();

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ArrayList f4293a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f4294b;

        public a(ArrayList arrayList, String str) {
            this.f4293a = arrayList;
            this.f4294b = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f4293a.size() > 1) {
                MomentGalleryActivity.g0(X.this.f4288a, this.f4293a, 0);
            } else {
                ImagePreviewActivity.f0(X.this.f4288a, this.f4294b);
            }
        }
    }

    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.m f4296a;

        public b(j0.m mVar) {
            this.f4296a = mVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (X.this.f4290c != null) {
                X.this.f4290c.q(this.f4296a);
            }
        }
    }

    public class c implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.m f4298a;

        public c(j0.m mVar) {
            this.f4298a = mVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (X.this.f4290c != null) {
                X.this.f4290c.l(this.f4298a);
            }
        }
    }

    public class d implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.m f4300a;

        public d(j0.m mVar) {
            this.f4300a = mVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (X.this.f4290c != null) {
                X.this.f4290c.o(this.f4300a);
            }
        }
    }

    public interface e {
        void l(j0.m mVar);

        void o(j0.m mVar);

        void q(j0.m mVar);
    }

    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ImageView f4302a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f4303b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final TextView f4304c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final TextView f4305d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final TextView f4306e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final View f4307f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final ImageView f4308g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final TextView f4309h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final View f4310i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final View f4311j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public final ImageView f4312k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public final TextView f4313l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public final TextView f4314m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public final TextView f4315n;

        public f(View view) {
            this.f4302a = (ImageView) view.findViewById(f0.f.G5);
            this.f4303b = (TextView) view.findViewById(f0.f.Ha);
            this.f4304c = (TextView) view.findViewById(f0.f.Ja);
            this.f4305d = (TextView) view.findViewById(f0.f.Ia);
            this.f4306e = (TextView) view.findViewById(f0.f.Da);
            this.f4307f = view.findViewById(f0.f.b5);
            this.f4308g = (ImageView) view.findViewById(f0.f.H5);
            this.f4309h = (TextView) view.findViewById(f0.f.Ga);
            this.f4310i = view.findViewById(f0.f.v1);
            this.f4311j = view.findViewById(f0.f.u1);
            this.f4312k = (ImageView) view.findViewById(f0.f.B5);
            this.f4313l = (TextView) view.findViewById(f0.f.va);
            this.f4314m = (TextView) view.findViewById(f0.f.o9);
            this.f4315n = (TextView) view.findViewById(f0.f.Fa);
        }
    }

    public X(Context context, List list, e eVar) {
        this.f4288a = context;
        this.f4289b = list;
        this.f4290c = eVar;
        this.f4291d = LayoutInflater.from(context);
    }

    public final String c(long j2) {
        if (j2 <= 0) {
            return "";
        }
        long j3 = j2 * 1000;
        long jCurrentTimeMillis = System.currentTimeMillis() - j3;
        long j4 = jCurrentTimeMillis >= 0 ? jCurrentTimeMillis : 0L;
        if (j4 < 60000) {
            return "刚刚";
        }
        if (j4 < 3600000) {
            return (j4 / 60000) + "分钟前";
        }
        if (j4 >= 86400000) {
            return f4287f.format(new Date(j3));
        }
        return (j4 / 3600000) + "小时前";
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List list = this.f4289b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        return this.f4289b.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        f fVar;
        if (view == null) {
            view = this.f4291d.inflate(f0.g.F0, viewGroup, false);
            fVar = new f(view);
            view.setTag(fVar);
        } else {
            fVar = (f) view.getTag();
        }
        j0.m mVar = (j0.m) this.f4289b.get(i2);
        String str = !TextUtils.isEmpty(mVar.f5569c) ? mVar.f5569c : mVar.f5568b;
        TextView textView = fVar.f4303b;
        if (str == null) {
            str = "";
        }
        textView.setText(str);
        R0.b(fVar.f4304c, mVar.f5570d);
        fVar.f4305d.setText(c(mVar.f5574h));
        if (TextUtils.isEmpty(mVar.f5572f)) {
            fVar.f4306e.setVisibility(8);
        } else {
            fVar.f4306e.setVisibility(0);
            fVar.f4306e.setText(mVar.f5572f);
        }
        List listB = mVar.f5578l;
        if (listB == null) {
            listB = o0.E.b(mVar.f5573g);
            mVar.f5578l = listB;
        }
        if (listB == null || listB.isEmpty()) {
            fVar.f4307f.setVisibility(8);
            fVar.f4308g.setOnClickListener(null);
            fVar.f4308g.setTag(null);
            fVar.f4309h.setVisibility(8);
        } else {
            fVar.f4307f.setVisibility(0);
            String str2 = (String) listB.get(0);
            fVar.f4308g.setTag(str2);
            AbstractC0455v.e(fVar.f4308g, str2);
            if (listB.size() > 1) {
                fVar.f4309h.setVisibility(0);
                fVar.f4309h.setText(listB.size() + "张");
            } else {
                fVar.f4309h.setVisibility(8);
            }
            this.f4292e.clear();
            this.f4292e.addAll(listB);
            fVar.f4308g.setOnClickListener(new a(new ArrayList(this.f4292e), str2));
        }
        AbstractC0455v.h(fVar.f4302a, mVar.f5571e);
        fVar.f4302a.setOnClickListener(new b(mVar));
        int iB = AbstractC0235a.b(this.f4288a, f0.d.f4964a);
        int iB2 = AbstractC0235a.b(this.f4288a, f0.d.f4974k);
        if (mVar.f5577k) {
            fVar.f4312k.setImageResource(f0.e.f5002W);
            fVar.f4312k.setColorFilter(iB);
            fVar.f4313l.setTextColor(iB);
            TextView textView2 = fVar.f4313l;
            int i3 = mVar.f5575i;
            textView2.setText(i3 > 0 ? String.valueOf(i3) : "已赞");
        } else {
            fVar.f4312k.setImageResource(f0.e.f5001V);
            fVar.f4312k.setColorFilter(iB2);
            fVar.f4313l.setTextColor(iB2);
            TextView textView3 = fVar.f4313l;
            int i4 = mVar.f5575i;
            textView3.setText(i4 > 0 ? String.valueOf(i4) : "赞");
        }
        int i5 = mVar.f5576j;
        if (i5 > 0) {
            fVar.f4314m.setText(String.valueOf(i5));
        } else {
            fVar.f4314m.setText("评论");
        }
        fVar.f4310i.setOnClickListener(new c(mVar));
        fVar.f4311j.setOnClickListener(new d(mVar));
        return view;
    }
}

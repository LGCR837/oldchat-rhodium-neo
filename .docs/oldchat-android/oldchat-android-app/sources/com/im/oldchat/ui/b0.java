package com.im.oldchat.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class b0 extends BaseAdapter {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final SimpleDateFormat f4431c = new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f4432a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f4433b = new ArrayList();

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f4434a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f4435b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f4436c;
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextView f4437a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f4438b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final TextView f4439c;

        public b(View view) {
            this.f4437a = (TextView) view.findViewById(f0.f.l9);
            this.f4438b = (TextView) view.findViewById(f0.f.m9);
            this.f4439c = (TextView) view.findViewById(f0.f.k9);
        }
    }

    public b0(Context context) {
        this.f4432a = context;
    }

    public void a(List list) {
        this.f4433b.clear();
        if (list != null) {
            this.f4433b.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f4433b.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        return this.f4433b.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            view = LayoutInflater.from(this.f4432a).inflate(f0.g.X0, viewGroup, false);
            bVar = new b(view);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        a aVar = (a) this.f4433b.get(i2);
        TextView textView = bVar.f4437a;
        if (textView != null) {
            String str = aVar.f4434a;
            if (str == null) {
                str = "";
            }
            textView.setText(str);
        }
        TextView textView2 = bVar.f4439c;
        if (textView2 != null) {
            textView2.setText(this.f4432a.getString(f0.j.G2, Integer.valueOf(aVar.f4435b)));
        }
        TextView textView3 = bVar.f4438b;
        if (textView3 != null) {
            if (aVar.f4436c > 0) {
                textView3.setText(f4431c.format(new Date(aVar.f4436c * 1000)));
                bVar.f4438b.setVisibility(0);
            } else {
                textView3.setText("");
                bVar.f4438b.setVisibility(8);
            }
        }
        return view;
    }
}

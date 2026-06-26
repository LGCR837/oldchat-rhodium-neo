package l0;

import android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import o0.AbstractC0455v;

/* JADX INFO: renamed from: l0.i0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0385i0 extends BaseAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LayoutInflater f6086a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f6087b = new ArrayList();

    /* JADX INFO: renamed from: l0.i0$b */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ImageView f6088a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f6089b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f6090c;

        public b() {
        }
    }

    public C0385i0(Context context) {
        this.f6086a = LayoutInflater.from(context);
    }

    public void a(List list) {
        if (list != null) {
            this.f6087b.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void b(List list) {
        this.f6087b.clear();
        if (list != null) {
            this.f6087b.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f6087b.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        if (i2 < 0 || i2 >= this.f6087b.size()) {
            return null;
        }
        return this.f6087b.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            view = this.f6086a.inflate(f0.g.N0, viewGroup, false);
            bVar = new b();
            bVar.f6088a = (ImageView) view.findViewById(f0.f.Q5);
            bVar.f6089b = (TextView) view.findViewById(f0.f.ec);
            bVar.f6090c = (TextView) view.findViewById(f0.f.bc);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        C0387j0 c0387j0 = (C0387j0) this.f6087b.get(i2);
        if (c0387j0 != null) {
            TextView textView = bVar.f6089b;
            String str = c0387j0.f6101a;
            if (str == null) {
                str = "";
            }
            textView.setText(str);
            TextView textView2 = bVar.f6090c;
            String str2 = c0387j0.f6103c;
            textView2.setText(str2 != null ? str2 : "");
            String strI = com.im.oldchat.bili.c.i(c0387j0.f6102b);
            if (strI == null || strI.length() <= 0) {
                bVar.f6088a.setImageResource(R.drawable.ic_media_play);
            } else {
                AbstractC0455v.e(bVar.f6088a, strI);
            }
        }
        return view;
    }
}

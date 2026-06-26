package l0;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import d.AbstractC0235a;
import java.util.List;
import o.c;
import o0.AbstractC0455v;
import o0.C;

/* JADX INFO: renamed from: l0.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0407w {

    /* JADX INFO: renamed from: l0.w$b */
    public static class b extends BaseAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Context f6338a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final List f6339b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f6340c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f6341d;

        public b(Context context, List list) {
            this.f6338a = context;
            this.f6339b = list;
            this.f6340c = AbstractC0235a.b(context, f0.d.f4974k);
            this.f6341d = AbstractC0235a.b(context, f0.d.f4973j);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            List list = this.f6339b;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            List list = this.f6339b;
            if (list == null || i2 < 0 || i2 >= list.size()) {
                return null;
            }
            return this.f6339b.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            c cVar;
            View view2;
            if (view == null) {
                LinearLayout linearLayout = new LinearLayout(this.f6338a);
                linearLayout.setOrientation(0);
                linearLayout.setGravity(16);
                linearLayout.setPadding(AbstractC0407w.c(this.f6338a, 12), AbstractC0407w.c(this.f6338a, 10), AbstractC0407w.c(this.f6338a, 12), AbstractC0407w.c(this.f6338a, 10));
                ImageView imageView = new ImageView(this.f6338a);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(AbstractC0407w.c(this.f6338a, 36), AbstractC0407w.c(this.f6338a, 36));
                layoutParams.rightMargin = AbstractC0407w.c(this.f6338a, 10);
                linearLayout.addView(imageView, layoutParams);
                LinearLayout linearLayout2 = new LinearLayout(this.f6338a);
                linearLayout2.setOrientation(1);
                linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                TextView textView = new TextView(this.f6338a);
                textView.setTextColor(this.f6340c);
                textView.setTextSize(13.0f);
                textView.setSingleLine(true);
                textView.setIncludeFontPadding(false);
                TextView textView2 = new TextView(this.f6338a);
                textView2.setTextColor(this.f6341d);
                textView2.setTextSize(14.0f);
                textView2.setMaxLines(3);
                textView2.setIncludeFontPadding(false);
                linearLayout2.addView(textView);
                linearLayout2.addView(textView2);
                linearLayout.addView(linearLayout2);
                cVar = new c();
                cVar.f6342a = imageView;
                cVar.f6343b = textView;
                cVar.f6344c = textView2;
                linearLayout.setTag(cVar);
                view2 = linearLayout;
            } else {
                cVar = (c) view.getTag();
                view2 = view;
            }
            C.b bVar = (C.b) this.f6339b.get(i2);
            String strD = bVar == null ? "" : AbstractC0407w.d(bVar.f7000c);
            if (strD.length() == 0) {
                strD = bVar == null ? "" : AbstractC0407w.d(bVar.f6999b);
            }
            if (strD.length() == 0) {
                strD = this.f6338a.getString(f0.j.f5222j0);
            }
            String strD2 = bVar == null ? "" : AbstractC0407w.d(bVar.f7004g);
            if (strD2.length() == 0) {
                strD2 = this.f6338a.getString(f0.j.f5216g0);
            }
            cVar.f6343b.setText(strD);
            cVar.f6344c.setText(strD2);
            String strD3 = bVar != null ? AbstractC0407w.d(bVar.f7001d) : "";
            if (strD3.length() == 0) {
                cVar.f6342a.setImageResource(f0.e.f4999T);
                cVar.f6342a.setTag(null);
            } else {
                Object tag = cVar.f6342a.getTag();
                if (tag == null || !strD3.equals(tag)) {
                    cVar.f6342a.setTag(strD3);
                    AbstractC0455v.h(cVar.f6342a, strD3);
                }
            }
            return view2;
        }
    }

    /* JADX INFO: renamed from: l0.w$c */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ImageView f6342a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f6343b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f6344c;

        public c() {
        }
    }

    public static int c(Context context, int i2) {
        return (int) ((context.getResources().getDisplayMetrics().density * i2) + 0.5f);
    }

    public static String d(String str) {
        return str == null ? "" : str.trim();
    }

    public static void e(Context context, C.a aVar) {
        List list;
        if (context == null || aVar == null || (list = aVar.f6997b) == null || list.isEmpty()) {
            return;
        }
        List listH = AbstractC0406v.h(aVar.f6997b);
        if (listH.isEmpty()) {
            return;
        }
        ListView listView = new ListView(context);
        listView.setBackgroundColor(AbstractC0235a.b(context, f0.d.f4971h));
        listView.setDivider(new ColorDrawable(AbstractC0235a.b(context, f0.d.f4969f)));
        listView.setDividerHeight(c(context, 1));
        listView.setAdapter((ListAdapter) new b(context, listH));
        listView.setLayoutParams(new AbsListView.LayoutParams(-1, c(context, 360)));
        String str = aVar.f6996a;
        String strTrim = str == null ? "" : str.trim();
        if (strTrim.length() == 0) {
            strTrim = context.getString(f0.j.f5198V);
        }
        new c.a(context, f0.k.f5244a).t(strTrim).u(listView).p(f0.j.f5199W, null).v();
    }
}

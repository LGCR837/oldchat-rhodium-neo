package l0;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class V extends BaseAdapter {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final SimpleDateFormat f5983d = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f5984a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f5985b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LayoutInflater f5986c;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextView f5987a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f5988b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final TextView f5989c;

        public a(View view) {
            this.f5987a = (TextView) view.findViewById(f0.f.Ab);
            this.f5988b = (TextView) view.findViewById(f0.f.xb);
            this.f5989c = (TextView) view.findViewById(f0.f.zb);
        }
    }

    public V(Context context, List list) {
        this.f5984a = context;
        this.f5985b = list;
        this.f5986c = LayoutInflater.from(context);
    }

    public final String a(long j2) {
        if (j2 <= 0) {
            return "";
        }
        return f5983d.format(new Date(j2 * 1000));
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List list = this.f5985b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        return this.f5985b.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        a aVar;
        String str;
        if (view == null) {
            view = this.f5986c.inflate(f0.g.H0, viewGroup, false);
            aVar = new a(view);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        j0.p pVar = (j0.p) this.f5985b.get(i2);
        if ("comment".equals(pVar.f5592f)) {
            if (pVar.f5593g > 0) {
                str = "有" + pVar.f5593g + "条新评论";
            } else {
                str = "有新评论";
            }
        } else if (!"like".equals(pVar.f5592f)) {
            str = "动态互动";
        } else if (pVar.f5593g > 0) {
            str = "有" + pVar.f5593g + "个新赞";
        } else {
            str = "有新赞";
        }
        aVar.f5987a.setText(str);
        String str2 = pVar.f5589c;
        if (TextUtils.isEmpty(str2)) {
            str2 = "动态";
        }
        aVar.f5988b.setText(str2);
        aVar.f5989c.setText(a(pVar.f5594h));
        return view;
    }
}

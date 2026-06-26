package l0;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class F0 extends BaseAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f5835a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f5836b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LayoutInflater f5837c;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextView f5838a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f5839b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final TextView f5840c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final TextView f5841d;

        public a(View view) {
            this.f5838a = (TextView) view.findViewById(f0.f.od);
            this.f5839b = (TextView) view.findViewById(f0.f.pd);
            this.f5840c = (TextView) view.findViewById(f0.f.qd);
            this.f5841d = (TextView) view.findViewById(f0.f.nd);
        }
    }

    public F0(Context context, List list) {
        this.f5835a = context;
        this.f5836b = list;
        this.f5837c = LayoutInflater.from(context);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List list = this.f5836b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        return this.f5836b.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = this.f5837c.inflate(f0.g.Z0, viewGroup, false);
            aVar = new a(view);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        j0.u uVar = (j0.u) this.f5836b.get(i2);
        if (uVar != null) {
            TextView textView = aVar.f5838a;
            String str = uVar.f5633b;
            if (str == null) {
                str = "";
            }
            textView.setText(str);
            String str2 = !TextUtils.isEmpty(uVar.f5635d) ? uVar.f5635d : uVar.f5634c;
            if (TextUtils.isEmpty(str2)) {
                str2 = "未知";
            }
            if (uVar.f5640i) {
                str2 = "我创建";
            }
            String str3 = "资源 " + uVar.f5639h;
            aVar.f5839b.setText(str2);
            R0.a(aVar.f5840c, uVar.f5636e);
            aVar.f5841d.setText(str3);
        }
        return view;
    }
}

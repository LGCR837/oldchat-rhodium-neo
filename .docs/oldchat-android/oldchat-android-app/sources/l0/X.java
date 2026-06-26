package l0;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.im.oldchat.ui.UserSpaceActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import o0.AbstractC0455v;

/* JADX INFO: loaded from: classes.dex */
public class X extends BaseAdapter {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final SimpleDateFormat f6000d = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f6001a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f6002b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LayoutInflater f6003c;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.q f6004a;

        public a(j0.q qVar) {
            this.f6004a = qVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String str = this.f6004a.f5597c;
            if (str == null || str.isEmpty()) {
                return;
            }
            Intent intent = new Intent(X.this.f6001a, (Class<?>) UserSpaceActivity.class);
            intent.putExtra("uid", this.f6004a.f5597c);
            X.this.f6001a.startActivity(intent);
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ImageView f6006a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f6007b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final TextView f6008c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final TextView f6009d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final TextView f6010e;

        public b(View view) {
            this.f6006a = (ImageView) view.findViewById(f0.f.p5);
            this.f6007b = (TextView) view.findViewById(f0.f.p9);
            this.f6008c = (TextView) view.findViewById(f0.f.r9);
            this.f6009d = (TextView) view.findViewById(f0.f.n9);
            this.f6010e = (TextView) view.findViewById(f0.f.q9);
        }
    }

    public X(Context context, List list) {
        this.f6001a = context;
        this.f6002b = list;
        this.f6003c = LayoutInflater.from(context);
    }

    public final String b(long j2) {
        if (j2 <= 0) {
            return "";
        }
        return f6000d.format(new Date(j2 * 1000));
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List list = this.f6002b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        return this.f6002b.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            view = this.f6003c.inflate(f0.g.G0, viewGroup, false);
            bVar = new b(view);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        j0.q qVar = (j0.q) this.f6002b.get(i2);
        String str = !TextUtils.isEmpty(qVar.f5598d) ? qVar.f5598d : qVar.f5597c;
        TextView textView = bVar.f6007b;
        if (str == null) {
            str = "";
        }
        textView.setText(str);
        R0.a(bVar.f6008c, qVar.f5599e);
        TextView textView2 = bVar.f6009d;
        String str2 = qVar.f5601g;
        textView2.setText(str2 != null ? str2 : "");
        bVar.f6010e.setText(b(qVar.f5602h));
        AbstractC0455v.h(bVar.f6006a, qVar.f5600f);
        bVar.f6006a.setOnClickListener(new a(qVar));
        return view;
    }
}

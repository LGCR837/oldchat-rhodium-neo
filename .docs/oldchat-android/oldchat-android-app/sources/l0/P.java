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
public class P extends BaseAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f5964a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f5965b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LayoutInflater f5966c;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.o f5967a;

        public a(j0.o oVar) {
            this.f5967a = oVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String str = this.f5967a.f5581c;
            if (str == null || str.isEmpty()) {
                return;
            }
            Intent intent = new Intent(P.this.f5964a, (Class<?>) UserSpaceActivity.class);
            intent.putExtra("uid", this.f5967a.f5581c);
            P.this.f5964a.startActivity(intent);
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ImageView f5969a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f5970b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final TextView f5971c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final TextView f5972d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final TextView f5973e;

        public b(View view) {
            this.f5969a = (ImageView) view.findViewById(f0.f.p5);
            this.f5970b = (TextView) view.findViewById(f0.f.p9);
            this.f5971c = (TextView) view.findViewById(f0.f.r9);
            this.f5972d = (TextView) view.findViewById(f0.f.n9);
            this.f5973e = (TextView) view.findViewById(f0.f.q9);
        }
    }

    public P(Context context, List list) {
        this.f5964a = context;
        this.f5965b = list;
        this.f5966c = LayoutInflater.from(context);
    }

    public final String b(long j2) {
        if (j2 <= 0) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date(j2 * 1000));
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List list = this.f5965b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        return this.f5965b.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            view = this.f5966c.inflate(f0.g.G0, viewGroup, false);
            bVar = new b(view);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        j0.o oVar = (j0.o) this.f5965b.get(i2);
        String str = !TextUtils.isEmpty(oVar.f5582d) ? oVar.f5582d : oVar.f5581c;
        TextView textView = bVar.f5970b;
        if (str == null) {
            str = "";
        }
        textView.setText(str);
        R0.a(bVar.f5971c, oVar.f5583e);
        TextView textView2 = bVar.f5972d;
        String str2 = oVar.f5585g;
        textView2.setText(str2 != null ? str2 : "");
        bVar.f5973e.setText(b(oVar.f5586h));
        AbstractC0455v.h(bVar.f5969a, oVar.f5584f);
        bVar.f5969a.setOnClickListener(new a(oVar));
        return view;
    }
}

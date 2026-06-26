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
public class B0 extends BaseAdapter {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final SimpleDateFormat f5769d = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f5770a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f5771b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LayoutInflater f5772c;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.s f5773a;

        public a(j0.s sVar) {
            this.f5773a = sVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String str = this.f5773a.f5612c;
            if (str == null || str.isEmpty()) {
                return;
            }
            Intent intent = new Intent(B0.this.f5770a, (Class<?>) UserSpaceActivity.class);
            intent.putExtra("uid", this.f5773a.f5612c);
            B0.this.f5770a.startActivity(intent);
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ImageView f5775a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f5776b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final TextView f5777c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final TextView f5778d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final TextView f5779e;

        public b(View view) {
            this.f5775a = (ImageView) view.findViewById(f0.f.p5);
            this.f5776b = (TextView) view.findViewById(f0.f.p9);
            this.f5777c = (TextView) view.findViewById(f0.f.r9);
            this.f5778d = (TextView) view.findViewById(f0.f.n9);
            this.f5779e = (TextView) view.findViewById(f0.f.q9);
        }
    }

    public B0(Context context, List list) {
        this.f5770a = context;
        this.f5771b = list;
        this.f5772c = LayoutInflater.from(context);
    }

    public final String b(long j2) {
        if (j2 <= 0) {
            return "";
        }
        return f5769d.format(new Date(j2 * 1000));
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List list = this.f5771b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        return this.f5771b.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            view = this.f5772c.inflate(f0.g.G0, viewGroup, false);
            bVar = new b(view);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        j0.s sVar = (j0.s) this.f5771b.get(i2);
        String str = !TextUtils.isEmpty(sVar.f5613d) ? sVar.f5613d : sVar.f5612c;
        TextView textView = bVar.f5776b;
        if (str == null) {
            str = "";
        }
        textView.setText(str);
        R0.a(bVar.f5777c, sVar.f5614e);
        TextView textView2 = bVar.f5778d;
        String str2 = sVar.f5616g;
        textView2.setText(str2 != null ? str2 : "");
        bVar.f5779e.setText(b(sVar.f5617h));
        AbstractC0455v.h(bVar.f5775a, sVar.f5615f);
        bVar.f5775a.setOnClickListener(new a(sVar));
        return view;
    }
}

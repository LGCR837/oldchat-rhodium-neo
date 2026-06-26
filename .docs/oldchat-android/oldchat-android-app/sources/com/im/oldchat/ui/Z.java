package com.im.oldchat.ui;

import android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.im.oldchat.bili.BiliModels;
import java.util.ArrayList;
import java.util.List;
import o0.AbstractC0455v;

/* JADX INFO: loaded from: classes.dex */
public class Z extends BaseAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LayoutInflater f4336a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f4337b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b f4338c;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ BiliModels.RecommendItem f4339a;

        public a(BiliModels.RecommendItem recommendItem) {
            this.f4339a = recommendItem;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (Z.this.f4338c != null) {
                Z.this.f4338c.a(this.f4339a);
            }
        }
    }

    public interface b {
        void a(BiliModels.RecommendItem recommendItem);
    }

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ImageView f4341a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f4342b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f4343c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public TextView f4344d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public TextView f4345e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public TextView f4346f;

        public c() {
        }

        public /* synthetic */ c(a aVar) {
            this();
        }
    }

    public Z(Context context) {
        this.f4336a = LayoutInflater.from(context);
    }

    public final void b(StringBuilder sb, String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        if (sb.length() > 0) {
            sb.append(" · ");
        }
        sb.append(str);
    }

    public final String c(BiliModels.RecommendItem recommendItem) {
        StringBuilder sb = new StringBuilder();
        String str = recommendItem.playCount;
        if (str != null && str.length() > 0) {
            b(sb, "播放 " + recommendItem.playCount);
        }
        String str2 = recommendItem.danmakuCount;
        if (str2 != null && str2.length() > 0) {
            String str3 = recommendItem.danmakuCount;
            if (str3.indexOf("弹幕") < 0) {
                str3 = "弹幕 " + str3;
            }
            b(sb, str3);
        }
        return sb.length() == 0 ? "推荐视频" : sb.toString();
    }

    public final String d(BiliModels.RecommendItem recommendItem) {
        BiliModels.RecommendArgs recommendArgs;
        String str;
        return (recommendItem == null || (recommendArgs = recommendItem.args) == null || (str = recommendArgs.upName) == null || str.length() <= 0) ? "UP 主" : recommendItem.args.upName;
    }

    public final boolean e(BiliModels.RecommendItem recommendItem) {
        BiliModels.RecommendArgs recommendArgs;
        if (recommendItem == null || (recommendArgs = recommendItem.args) == null) {
            return false;
        }
        return recommendArgs.upId > 0 || recommendArgs.mid > 0;
    }

    public void f(b bVar) {
        this.f4338c = bVar;
    }

    public void g(List list) {
        this.f4337b.clear();
        if (list != null) {
            this.f4337b.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f4337b.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        if (i2 < 0 || i2 >= this.f4337b.size()) {
            return null;
        }
        return this.f4337b.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        c cVar;
        a aVar = null;
        if (view == null) {
            view = this.f4336a.inflate(f0.g.N0, viewGroup, false);
            cVar = new c(aVar);
            cVar.f4341a = (ImageView) view.findViewById(f0.f.Q5);
            cVar.f4342b = (TextView) view.findViewById(f0.f.ec);
            cVar.f4343c = (TextView) view.findViewById(f0.f.fc);
            cVar.f4344d = (TextView) view.findViewById(f0.f.B2);
            cVar.f4345e = (TextView) view.findViewById(f0.f.bc);
            cVar.f4346f = (TextView) view.findViewById(f0.f.Ub);
            view.setTag(cVar);
        } else {
            cVar = (c) view.getTag();
        }
        BiliModels.RecommendItem recommendItem = (BiliModels.RecommendItem) this.f4337b.get(i2);
        if (recommendItem != null) {
            TextView textView = cVar.f4342b;
            String str = recommendItem.title;
            if (str == null) {
                str = "";
            }
            textView.setText(str);
            cVar.f4343c.setText(d(recommendItem));
            cVar.f4345e.setText(c(recommendItem));
            String str2 = recommendItem.duration;
            String strTrim = str2 != null ? str2.trim() : "";
            if (strTrim.length() > 0) {
                cVar.f4346f.setText(strTrim);
                cVar.f4346f.setVisibility(0);
            } else {
                cVar.f4346f.setVisibility(8);
            }
            String strI = com.im.oldchat.bili.c.i(recommendItem.cover);
            if (strI == null || strI.length() <= 0) {
                cVar.f4341a.setImageResource(R.drawable.ic_media_play);
            } else {
                AbstractC0455v.e(cVar.f4341a, strI);
            }
            if (this.f4338c == null || !e(recommendItem)) {
                cVar.f4344d.setVisibility(8);
                cVar.f4344d.setOnClickListener(null);
                cVar.f4343c.setOnClickListener(null);
            } else {
                cVar.f4344d.setVisibility(0);
                a aVar2 = new a(recommendItem);
                cVar.f4344d.setOnClickListener(aVar2);
                cVar.f4343c.setOnClickListener(aVar2);
            }
        }
        return view;
    }
}

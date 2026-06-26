package com.im.oldchat.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* JADX INFO: renamed from: com.im.oldchat.ui.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0229u extends BaseAdapter {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final SimpleDateFormat f4751d = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LayoutInflater f4752a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f4753b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c f4754c;

    /* JADX INFO: renamed from: com.im.oldchat.ui.u$a */
    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.b f4755a;

        public a(j0.b bVar) {
            this.f4755a = bVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (C0229u.this.f4754c != null) {
                C0229u.this.f4754c.k(this.f4755a);
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.u$b */
    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.b f4757a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f4758b;

        public b(j0.b bVar, int i2) {
            this.f4757a = bVar;
            this.f4758b = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (C0229u.this.f4754c != null) {
                C0229u.this.f4754c.r(this.f4757a, this.f4758b);
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.u$c */
    public interface c {
        void k(j0.b bVar);

        void r(j0.b bVar, int i2);
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.u$d */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextView f4760a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f4761b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final TextView f4762c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final TextView f4763d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final TextView f4764e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final TextView f4765f;

        public d(View view) {
            this.f4760a = (TextView) view.findViewById(f0.f.Q9);
            this.f4761b = (TextView) view.findViewById(f0.f.P9);
            this.f4762c = (TextView) view.findViewById(f0.f.N9);
            this.f4763d = (TextView) view.findViewById(f0.f.O9);
            this.f4764e = (TextView) view.findViewById(f0.f.J0);
            this.f4765f = (TextView) view.findViewById(f0.f.I0);
        }
    }

    public C0229u(Context context, List list, c cVar) {
        this.f4752a = LayoutInflater.from(context);
        this.f4753b = list;
        this.f4754c = cVar;
    }

    public final String b(long j2) {
        if (j2 <= 0) {
            return "";
        }
        return f4751d.format(new Date(j2 * 1000));
    }

    public final String c(String str) {
        return str == null ? "收藏" : "chat_image".equals(str) ? "聊天图片" : "chat_voice".equals(str) ? "聊天语音" : "chat_video".equals(str) ? "聊天视频" : "resource_file".equals(str) ? "资源文件" : "emoji_pack".equals(str) ? "表情包" : "music_song".equals(str) ? "歌曲" : "收藏";
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List list = this.f4753b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        List list = this.f4753b;
        if (list == null || i2 < 0 || i2 >= list.size()) {
            return null;
        }
        return this.f4753b.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        d dVar;
        if (view == null) {
            view = this.f4752a.inflate(f0.g.v0, viewGroup, false);
            dVar = new d(view);
            view.setTag(dVar);
        } else {
            dVar = (d) view.getTag();
        }
        j0.b bVar = (j0.b) getItem(i2);
        if (bVar == null) {
            return view;
        }
        dVar.f4760a.setText(c(bVar.f5497b));
        TextView textView = dVar.f4761b;
        String str = bVar.f5499d;
        textView.setText((str == null || str.length() == 0) ? "未命名收藏" : bVar.f5499d);
        String str2 = bVar.f5500e;
        if (str2 == null) {
            str2 = "";
        }
        if (str2.length() == 0) {
            String str3 = bVar.f5501f;
            str2 = str3 != null ? str3 : "";
        }
        dVar.f4762c.setText(str2);
        dVar.f4763d.setText(b(bVar.f5503h));
        dVar.f4764e.setOnClickListener(new a(bVar));
        dVar.f4765f.setOnClickListener(new b(bVar, i2));
        return view;
    }
}

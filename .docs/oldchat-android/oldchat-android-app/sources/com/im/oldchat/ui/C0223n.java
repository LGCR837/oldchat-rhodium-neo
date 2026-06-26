package com.im.oldchat.ui;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import l0.AbstractC0380g;
import l0.AbstractC0382h;

/* JADX INFO: renamed from: com.im.oldchat.ui.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0223n extends BaseAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f4681a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f4682b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f4683c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f4684d = "";

    /* JADX INFO: renamed from: com.im.oldchat.ui.n$a */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f4685a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f4686b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f4687c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f4688d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f4689e;
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.n$b */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextView f4690a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f4691b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final TextView f4692c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final TextView f4693d;

        public b(View view) {
            this.f4690a = (TextView) view.findViewById(f0.f.Ed);
            this.f4691b = (TextView) view.findViewById(f0.f.Gd);
            this.f4692c = (TextView) view.findViewById(f0.f.Fd);
            this.f4693d = (TextView) view.findViewById(f0.f.Dd);
        }
    }

    public C0223n(Context context, List list, boolean z2) {
        this.f4681a = context;
        this.f4682b = list;
        this.f4683c = z2;
    }

    public final int a(String str) {
        String lowerCase = str == null ? "" : str.toLowerCase();
        return "image".equals(lowerCase) ? f0.e.f4986G : "video".equals(lowerCase) ? f0.e.f4989J : "voice".equals(lowerCase) ? f0.e.f4990K : "resource".equals(lowerCase) ? f0.e.f4987H : f0.e.f4988I;
    }

    public final CharSequence b(String str) {
        int iIndexOf;
        if (str == null) {
            return "";
        }
        String str2 = this.f4684d;
        if (str2 == null || str2.length() == 0) {
            return str;
        }
        String lowerCase = str.toLowerCase();
        String lowerCase2 = this.f4684d.toLowerCase();
        int i2 = o0.X.i(this.f4681a);
        SpannableString spannableString = new SpannableString(str);
        int i3 = 0;
        while (i3 < lowerCase.length() && (iIndexOf = lowerCase.indexOf(lowerCase2, i3)) >= 0) {
            int length = lowerCase2.length() + iIndexOf;
            if (length > iIndexOf) {
                spannableString.setSpan(new ForegroundColorSpan(i2), iIndexOf, length, 33);
            }
            i3 = length;
        }
        return spannableString;
    }

    public final String c(String str) {
        String lowerCase = str == null ? "" : str.toLowerCase();
        return "image".equals(lowerCase) ? "图片" : "video".equals(lowerCase) ? "视频" : "voice".equals(lowerCase) ? "语音" : "resource".equals(lowerCase) ? "资源" : "文本";
    }

    public void d(String str) {
        this.f4684d = str == null ? "" : str.trim();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List list = this.f4682b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        List list = this.f4682b;
        if (list == null || i2 < 0 || i2 >= list.size()) {
            return null;
        }
        return this.f4682b.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            view = LayoutInflater.from(this.f4681a).inflate(f0.g.q0, viewGroup, false);
            bVar = new b(view);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        a aVar = (a) getItem(i2);
        if (aVar == null) {
            bVar.f4690a.setText("");
            bVar.f4691b.setText("");
            bVar.f4692c.setText("");
            bVar.f4693d.setText("");
            bVar.f4691b.setBackgroundResource(f0.e.f4988I);
            return view;
        }
        String str = aVar.f4686b;
        String str2 = (str == null || str.length() == 0) ? "成员" : aVar.f4686b;
        String strA = AbstractC0382h.a(aVar.f4689e);
        if (strA == null || strA.length() == 0) {
            strA = "未知时间";
        }
        String strC = c(aVar.f4687c);
        if (this.f4683c) {
            bVar.f4690a.setText(str2 + " · " + strA + " · " + strC);
        } else {
            bVar.f4690a.setText(strA + " · " + strC);
        }
        bVar.f4691b.setText(strC);
        bVar.f4691b.setBackgroundResource(a(aVar.f4687c));
        bVar.f4693d.setText("定位");
        String strE = AbstractC0380g.e(aVar.f4687c, aVar.f4688d);
        if (strE == null || strE.length() == 0) {
            strE = "(空消息)";
        }
        bVar.f4692c.setText(b(strE));
        return view;
    }
}

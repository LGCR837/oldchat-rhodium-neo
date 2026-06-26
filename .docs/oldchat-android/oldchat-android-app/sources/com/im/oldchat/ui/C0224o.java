package com.im.oldchat.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import l0.AbstractC0382h;

/* JADX INFO: renamed from: com.im.oldchat.ui.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0224o extends BaseAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f4694a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f4695b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f4696c;

    /* JADX INFO: renamed from: com.im.oldchat.ui.o$a */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f4697a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f4698b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f4699c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f4700d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f4701e;
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.o$b */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f4702a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f4703b;

        public b(View view) {
            this.f4702a = (TextView) view.findViewById(f0.f.A9);
            this.f4703b = (TextView) view.findViewById(f0.f.z9);
        }
    }

    public C0224o(Context context, List list, String str) {
        this.f4694a = context;
        this.f4695b = list;
        this.f4696c = str == null ? "" : str;
    }

    public final String a(a aVar) {
        String str = aVar.f4699c;
        String strTrim = str == null ? "" : str.trim();
        if ("android".equalsIgnoreCase(strTrim)) {
            strTrim = "Android";
        } else if ("ios".equalsIgnoreCase(strTrim)) {
            strTrim = "iOS";
        }
        String str2 = aVar.f4700d;
        String strTrim2 = str2 == null ? "" : str2.trim();
        if (strTrim2.length() > 0) {
            strTrim2 = "v" + strTrim2;
        }
        long j2 = aVar.f4701e;
        String strA = j2 > 0 ? AbstractC0382h.a(j2) : "";
        StringBuilder sb = new StringBuilder();
        if (strTrim.length() > 0) {
            sb.append(strTrim);
        }
        if (strTrim2.length() > 0) {
            if (sb.length() > 0) {
                sb.append(" · ");
            }
            sb.append(strTrim2);
        }
        if (strA.length() > 0) {
            if (sb.length() > 0) {
                sb.append(" · ");
            }
            sb.append("最近使用 ");
            sb.append(strA);
        }
        if (sb.length() == 0 && aVar.f4697a != null) {
            sb.append("ID: ");
            sb.append(aVar.f4697a);
        }
        return sb.toString();
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public a getItem(int i2) {
        List list = this.f4695b;
        if (list == null || i2 < 0 || i2 >= list.size()) {
            return null;
        }
        return (a) this.f4695b.get(i2);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List list = this.f4695b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            view = LayoutInflater.from(this.f4694a).inflate(f0.g.r0, viewGroup, false);
            bVar = new b(view);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        a item = getItem(i2);
        String str = "";
        if (item == null) {
            bVar.f4702a.setText("");
            bVar.f4703b.setText("");
            return view;
        }
        String str2 = item.f4698b;
        if (str2 == null || str2.isEmpty()) {
            String str3 = item.f4697a;
            if (str3 != null) {
                str = str3;
            }
        } else {
            str = item.f4698b;
        }
        String str4 = item.f4697a;
        if (str4 != null && !str4.isEmpty() && item.f4697a.equals(this.f4696c)) {
            str = str + " (本机)";
        }
        bVar.f4702a.setText(str);
        bVar.f4703b.setText(a(item));
        return view;
    }
}

package com.im.oldchat.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import g0.d;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import k.AbstractC0285k0;
import org.json.JSONArray;
import org.json.JSONObject;
import org.spongycastle.i18n.MessageBundle;

/* JADX INFO: loaded from: classes.dex */
public class NotificationChatActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public String f3542A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public d f3543B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public List f3544C = new ArrayList();

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ListView f3545w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public ProgressBar f3546x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f3547y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public ImageView f3548z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NotificationChatActivity.this.finish();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NotificationChatActivity.this.q0();
        }
    }

    public class c implements d.i {
        public c() {
        }

        @Override // g0.d.i
        public void b(String str) {
            NotificationChatActivity.this.f3546x.setVisibility(8);
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("notifications");
                NotificationChatActivity.this.f3544C.clear();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    e eVar = new e();
                    eVar.f3555a = jSONObject.optString("id", "");
                    eVar.f3556b = jSONObject.optString(MessageBundle.TITLE_ENTRY, "");
                    eVar.f3557c = jSONObject.optString("body", "");
                    eVar.f3558d = jSONObject.optLong("created_at", 0L);
                    NotificationChatActivity.this.f3544C.add(eVar);
                }
                NotificationChatActivity notificationChatActivity = NotificationChatActivity.this;
                notificationChatActivity.p0(notificationChatActivity.f3544C);
                NotificationChatActivity.this.n0();
                NotificationChatActivity.this.f3543B.notifyDataSetChanged();
                if (NotificationChatActivity.this.f3544C.isEmpty()) {
                    NotificationChatActivity.this.f3547y.setVisibility(0);
                    NotificationChatActivity.this.f3545w.setVisibility(8);
                } else {
                    NotificationChatActivity.this.f3547y.setVisibility(8);
                    NotificationChatActivity.this.f3545w.setVisibility(0);
                }
            } catch (Exception unused) {
                NotificationChatActivity.this.f3547y.setVisibility(0);
                NotificationChatActivity.this.f3545w.setVisibility(8);
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            NotificationChatActivity.this.f3546x.setVisibility(8);
            NotificationChatActivity.this.f3547y.setVisibility(0);
            NotificationChatActivity.this.f3545w.setVisibility(8);
            Toast.makeText(NotificationChatActivity.this, "加载失败", 0).show();
        }
    }

    public static class d extends BaseAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Context f3552a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public List f3553b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public SimpleDateFormat f3554c = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

        public d(Context context, List list) {
            this.f3552a = context;
            this.f3553b = list;
        }

        @Override // android.widget.Adapter
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e getItem(int i2) {
            return (e) this.f3553b.get(i2);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.f3553b.size();
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(this.f3552a).inflate(f0.g.L0, viewGroup, false);
            }
            e item = getItem(i2);
            TextView textView = (TextView) view.findViewById(f0.f.Yd);
            TextView textView2 = (TextView) view.findViewById(f0.f.a9);
            TextView textView3 = (TextView) view.findViewById(f0.f.Xd);
            String str = item.f3556b;
            if (str == null || str.isEmpty()) {
                textView.setVisibility(8);
            } else {
                textView.setText(item.f3556b);
                textView.setVisibility(0);
            }
            textView2.setText(item.f3557c);
            textView3.setText(this.f3554c.format(new Date(item.f3558d)));
            return view;
        }
    }

    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f3555a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f3556b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f3557c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f3558d;
    }

    public final void n0() {
        getSharedPreferences("notification", 0).edit().putInt("unread_count", 0).apply();
    }

    public final void o0() {
        this.f3546x.setVisibility(0);
        this.f3545w.setVisibility(8);
        this.f3547y.setVisibility(8);
        g0.d.R("/notifications?limit=100", this.f3542A, new c());
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5115I);
        this.f3542A = getSharedPreferences("auth", 0).getString("access_token", "");
        this.f3545w = (ListView) a0(f0.f.W6);
        this.f3546x = (ProgressBar) a0(f0.f.r7);
        this.f3547y = (TextView) a0(f0.f.K9);
        this.f3548z = (ImageView) a0(f0.f.f2);
        a0(f0.f.f5102v).setOnClickListener(new a());
        d dVar = new d(this, this.f3544C);
        this.f3543B = dVar;
        this.f3545w.setAdapter((ListAdapter) dVar);
        r0();
        this.f3548z.setOnClickListener(new b());
        o0();
    }

    public final void p0(List list) {
        String str;
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            e eVar = (e) list.get(i2);
            if (eVar != null && (str = eVar.f3555a) != null && str.length() != 0) {
                i0.f.d(this, eVar.f3555a);
            }
        }
    }

    public final void q0() {
        boolean zB = i0.g.b(this, "SYSTEM", false);
        i0.g.g(this, "SYSTEM", false, !zB);
        r0();
        Toast.makeText(this, zB ? "已取消屏蔽" : "已屏蔽通知", 0).show();
    }

    public final void r0() {
        AbstractC0285k0.K(this.f3548z, i0.g.b(this, "SYSTEM", false) ? 0.5f : 1.0f);
    }
}

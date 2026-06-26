package com.im.oldchat.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.b0;
import g0.d;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import o0.AbstractC0455v;
import org.json.JSONArray;
import org.json.JSONObject;
import org.spongycastle.i18n.MessageBundle;

/* JADX INFO: loaded from: classes.dex */
public class RedPacketDetailActivity extends f0.a {

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public static final SimpleDateFormat f3961J = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public TextView f3962A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public TextView f3963B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public TextView f3964C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public TextView f3965D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public TextView f3966E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public TextView f3967F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public TextView f3968G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public ListView f3969H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public b0 f3970I;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public String f3971w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public String f3972x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public ImageView f3973y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public TextView f3974z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RedPacketDetailActivity.this.finish();
        }
    }

    public class b implements d.i {
        public b() {
        }

        @Override // g0.d.i
        public void b(String str) {
            RedPacketDetailActivity.this.g0(str);
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(RedPacketDetailActivity.this, f0.j.Q2, 0).show();
        }
    }

    private void h0() {
        String str = this.f3972x;
        if (str == null || str.isEmpty()) {
            Toast.makeText(this, f0.j.O2, 0).show();
            finish();
        } else {
            g0.d.R("/redpackets/" + this.f3972x, this.f3971w, new b());
        }
    }

    public final void g0(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString(MessageBundle.TITLE_ENTRY, "");
            if (strOptString == null || strOptString.isEmpty()) {
                strOptString = getString(f0.j.W2);
            }
            TextView textView = this.f3974z;
            if (textView != null) {
                textView.setText(strOptString);
            }
            String strOptString2 = jSONObject.optString("cover_url", "");
            if (this.f3973y != null) {
                if (strOptString2 == null || strOptString2.trim().isEmpty()) {
                    this.f3973y.setImageResource(f0.e.f5007a0);
                } else {
                    AbstractC0455v.e(this.f3973y, strOptString2.trim());
                }
            }
            boolean z2 = "done".equalsIgnoreCase(jSONObject.optString("status", "active")) || jSONObject.optInt("remaining_count", 0) <= 0;
            TextView textView2 = this.f3962A;
            if (textView2 != null) {
                textView2.setText(getString(z2 ? f0.j.T2 : f0.j.U2));
            }
            long jOptLong = jSONObject.optLong("created_at", 0L);
            TextView textView3 = this.f3963B;
            if (textView3 != null) {
                if (jOptLong > 0) {
                    textView3.setText("创建时间：" + f3961J.format(new Date(jOptLong * 1000)));
                } else {
                    textView3.setText("创建时间：未知");
                }
            }
            int iOptInt = jSONObject.optInt("total_amount", 0);
            int iOptInt2 = jSONObject.optInt("total_count", 0);
            int iOptInt3 = jSONObject.optInt("claimed_amount", iOptInt - jSONObject.optInt("remaining_amount", 0));
            int iOptInt4 = jSONObject.optInt("claimed_count", iOptInt2 - jSONObject.optInt("remaining_count", 0));
            TextView textView4 = this.f3964C;
            if (textView4 != null) {
                textView4.setText(getString(f0.j.Y2, Integer.valueOf(iOptInt)));
            }
            TextView textView5 = this.f3965D;
            if (textView5 != null) {
                textView5.setText(getString(f0.j.Z2, Integer.valueOf(iOptInt2)));
            }
            TextView textView6 = this.f3966E;
            if (textView6 != null) {
                textView6.setText(getString(f0.j.H2, Integer.valueOf(iOptInt3)));
            }
            TextView textView7 = this.f3967F;
            if (textView7 != null) {
                textView7.setText(getString(f0.j.I2, Integer.valueOf(iOptInt4)));
            }
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("claims");
            if (jSONArrayOptJSONArray != null) {
                for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                    JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i2);
                    if (jSONObjectOptJSONObject != null) {
                        b0.a aVar = new b0.a();
                        String strOptString3 = jSONObjectOptJSONObject.optString("display_name", "");
                        if (strOptString3 == null || strOptString3.isEmpty()) {
                            strOptString3 = jSONObjectOptJSONObject.optString("uid", "");
                        }
                        aVar.f4434a = strOptString3;
                        aVar.f4435b = jSONObjectOptJSONObject.optInt("amount", 0);
                        aVar.f4436c = jSONObjectOptJSONObject.optLong("created_at", 0L);
                        arrayList.add(aVar);
                    }
                }
            }
            b0 b0Var = this.f3970I;
            if (b0Var != null) {
                b0Var.a(arrayList);
            }
        } catch (Exception unused) {
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5130X);
        View viewA0 = a0(f0.f.Z2);
        this.f3973y = (ImageView) a0(f0.f.a6);
        this.f3974z = (TextView) a0(f0.f.Rc);
        this.f3962A = (TextView) a0(f0.f.Qc);
        this.f3963B = (TextView) a0(f0.f.Oc);
        this.f3964C = (TextView) a0(f0.f.Sc);
        this.f3965D = (TextView) a0(f0.f.Tc);
        this.f3966E = (TextView) a0(f0.f.Mc);
        this.f3967F = (TextView) a0(f0.f.Nc);
        this.f3968G = (TextView) a0(f0.f.Pc);
        this.f3969H = (ListView) a0(f0.f.b7);
        this.f3971w = getSharedPreferences("auth", 0).getString("access_token", "");
        this.f3972x = getIntent().getStringExtra("packet_id");
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        b0 b0Var = new b0(this);
        this.f3970I = b0Var;
        ListView listView = this.f3969H;
        if (listView != null) {
            listView.setAdapter((ListAdapter) b0Var);
            TextView textView = this.f3968G;
            if (textView != null) {
                this.f3969H.setEmptyView(textView);
            }
        }
        h0();
    }
}

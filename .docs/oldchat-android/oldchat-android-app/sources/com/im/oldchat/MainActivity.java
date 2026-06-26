package com.im.oldchat;

import android.R;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import c.AbstractC0112D;
import c.AbstractC0175y;
import c.AbstractComponentCallbacksC0165t;
import com.im.oldchat.ui.LoginActivity;
import com.im.oldchat.ui.NoAnimViewPager;
import com.im.oldchat.ui.TopStatusBar;
import g.AbstractC0244a;
import g0.d;
import g0.j;
import java.util.ArrayList;
import java.util.List;
import m0.k;
import o.c;
import o0.AbstractC0437d;
import o0.AbstractC0438d0;
import o0.G;
import org.json.JSONArray;
import org.json.JSONObject;
import org.spongycastle.i18n.MessageBundle;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public TopStatusBar f2128A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public View f2129B;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public LinearLayout f2131w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public ViewPager f2132x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final List f2133y = new ArrayList();

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f2134z = false;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public boolean f2130C = false;

    public class a implements TopStatusBar.c {
        public a() {
        }

        @Override // com.im.oldchat.ui.TopStatusBar.c
        public void a() {
            SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("auth", 0);
            String string = sharedPreferences.getString("access_token", null);
            if (string == null) {
                string = sharedPreferences.getString("token", null);
            }
            if (string == null || string.length() == 0) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, (Class<?>) LoginActivity.class));
                MainActivity.this.finish();
            } else {
                j.u().z(MainActivity.this);
                MainActivity.this.p0();
                MainActivity.this.q0();
                MainActivity.this.o0();
            }
        }
    }

    public class b extends ViewPager.m {
        public b() {
        }

        @Override // android.support.v4.view.ViewPager.j
        public void c(int i2) {
            MainActivity.this.x0(i2);
        }
    }

    public class c implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2137a;

        public c(int i2) {
            this.f2137a = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MainActivity.this.f2132x != null) {
                MainActivity.this.f2132x.L(this.f2137a, true);
            }
        }
    }

    public class d implements d.i {
        public d() {
        }

        @Override // g0.d.i
        public void b(String str) {
            int iB;
            MainActivity.this.f2130C = false;
            if (MainActivity.this.f2128A != null) {
                MainActivity.this.f2128A.setLoading(false);
            }
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("requests");
                iB = 0;
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    if (jSONArray.getJSONObject(i2).optInt("status", 1) == 0) {
                        iB++;
                    }
                }
            } catch (Exception unused) {
                iB = i0.b.b(MainActivity.this);
            }
            i0.b.c(MainActivity.this, iB);
            MainActivity.this.w0(iB);
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            MainActivity.this.f2130C = false;
            if (MainActivity.this.f2128A != null) {
                MainActivity.this.f2128A.setLoading(false);
            }
            MainActivity mainActivity = MainActivity.this;
            mainActivity.w0(i0.b.b(mainActivity));
        }
    }

    public class e implements d.i {

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ String f2141a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ String f2142b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ String f2143c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ boolean f2144d;

            public a(String str, String str2, String str3, boolean z2) {
                this.f2141a = str;
                this.f2142b = str2;
                this.f2143c = str3;
                this.f2144d = z2;
            }

            @Override // java.lang.Runnable
            public void run() {
                MainActivity.this.v0(this.f2141a, this.f2142b, this.f2143c, this.f2144d);
            }
        }

        public e() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("notifications");
                if (jSONArray.length() > 0) {
                    JSONObject jSONObject = jSONArray.getJSONObject(0);
                    String strOptString = jSONObject.optString("id", "");
                    String strOptString2 = jSONObject.optString(MessageBundle.TITLE_ENTRY, "");
                    String strOptString3 = jSONObject.optString("body", "");
                    boolean zOptBoolean = jSONObject.optBoolean("important", false);
                    if (strOptString.isEmpty() || i0.f.c(MainActivity.this, strOptString)) {
                        return;
                    }
                    MainActivity.this.runOnUiThread(new a(strOptString, strOptString2, strOptString3, zOptBoolean));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
        }
    }

    public class f implements CompoundButton.OnCheckedChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean[] f2146a;

        public f(boolean[] zArr) {
            this.f2146a = zArr;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
            this.f2146a[0] = z2;
        }
    }

    public class g implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean[] f2148a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f2149b;

        public g(boolean[] zArr, String str) {
            this.f2148a = zArr;
            this.f2149b = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            if (this.f2148a[0]) {
                i0.f.d(MainActivity.this, this.f2149b);
            }
            dialogInterface.dismiss();
        }
    }

    public class h implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2151a;

        public h(String str) {
            this.f2151a = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            i0.f.d(MainActivity.this, this.f2151a);
            dialogInterface.dismiss();
        }
    }

    public class i extends AbstractC0112D {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final List f2153f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final List f2154g;

        public i(AbstractC0175y abstractC0175y) {
            super(abstractC0175y);
            this.f2153f = new ArrayList();
            this.f2154g = new ArrayList();
        }

        @Override // k.AbstractC0267e0
        public int d() {
            return this.f2153f.size();
        }

        @Override // k.AbstractC0267e0
        public CharSequence f(int i2) {
            return (CharSequence) this.f2154g.get(i2);
        }

        @Override // c.AbstractC0112D
        public AbstractComponentCallbacksC0165t s(int i2) {
            return (AbstractComponentCallbacksC0165t) this.f2153f.get(i2);
        }

        public void v(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, String str) {
            this.f2153f.add(abstractComponentCallbacksC0165t);
            this.f2154g.add(str);
        }
    }

    public final void n0(ImageView imageView, ColorStateList colorStateList) {
        Drawable drawable;
        if (imageView == null || colorStateList == null || (drawable = imageView.getDrawable()) == null) {
            return;
        }
        Drawable drawableQ = AbstractC0244a.q(drawable.mutate());
        AbstractC0244a.n(drawableQ, colorStateList);
        imageView.setImageDrawable(drawableQ);
    }

    public final void o0() {
        g0.d.R("/notifications?limit=1", getSharedPreferences("auth", 0).getString("access_token", ""), new e());
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SharedPreferences sharedPreferences = getSharedPreferences("auth", 0);
        String string = sharedPreferences.getString("access_token", null);
        if (string == null) {
            string = sharedPreferences.getString("token", null);
        }
        if (string == null) {
            startActivity(new Intent(this, (Class<?>) LoginActivity.class));
            finish();
            return;
        }
        setContentView(f0.g.f5172y);
        j.u().z(this);
        TopStatusBar topStatusBar = (TopStatusBar) a0(f0.f.S8);
        this.f2128A = topStatusBar;
        if (topStatusBar != null) {
            topStatusBar.setOnRetryClickListener(new a());
        }
        ViewPager viewPager = (ViewPager) a0(f0.f.ye);
        this.f2132x = viewPager;
        u0(viewPager);
        this.f2131w = (LinearLayout) a0(f0.f.N8);
        t0();
        AbstractC0437d.g(this, string);
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onResume() {
        super.onResume();
        j.u().z(this);
        p0();
        q0();
        AbstractC0438d0.g(this);
        if (this.f2134z) {
            return;
        }
        this.f2134z = true;
        o0();
    }

    public final void p0() {
        if (this.f2132x == null) {
            return;
        }
        AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165tC = y().c("android:switcher:" + this.f2132x.getId() + ":0");
        if (abstractComponentCallbacksC0165tC instanceof m0.b) {
            ((m0.b) abstractComponentCallbacksC0165tC).X0();
        }
    }

    public final void q0() {
        if (this.f2130C) {
            return;
        }
        String string = getSharedPreferences("auth", 0).getString("access_token", "");
        if (string == null || string.isEmpty() || !G.d().f()) {
            w0(i0.b.b(this));
            return;
        }
        this.f2130C = true;
        TopStatusBar topStatusBar = this.f2128A;
        if (topStatusBar != null) {
            topStatusBar.setLoading(true);
        }
        g0.d.R("/friends/requests", string, new d());
    }

    public final void r0(View view, boolean z2) {
        if (view == null) {
            return;
        }
        ImageView imageView = (ImageView) view.findViewById(f0.f.G8);
        TextView textView = (TextView) view.findViewById(f0.f.I8);
        View viewFindViewById = view.findViewById(f0.f.H8);
        view.setSelected(z2);
        if (imageView != null) {
            imageView.setSelected(z2);
        }
        if (textView != null) {
            textView.setSelected(z2);
            textView.setTypeface(z2 ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
        }
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(z2 ? 0 : 4);
        }
    }

    public final void s0(int i2, int i3, String str) {
        View viewInflate = LayoutInflater.from(this).inflate(f0.g.e1, (ViewGroup) this.f2131w, false);
        ImageView imageView = (ImageView) viewInflate.findViewById(f0.f.G8);
        TextView textView = (TextView) viewInflate.findViewById(f0.f.I8);
        View viewFindViewById = viewInflate.findViewById(f0.f.F8);
        imageView.setImageDrawable(getResources().getDrawable(i3));
        textView.setText(str);
        ColorStateList colorStateList = getResources().getColorStateList(f0.d.f4979p);
        if (colorStateList != null) {
            textView.setTextColor(colorStateList);
            n0(imageView, colorStateList);
        }
        viewInflate.setClickable(true);
        viewInflate.setFocusable(true);
        viewInflate.setOnClickListener(new c(i2));
        this.f2131w.addView(viewInflate, new LinearLayout.LayoutParams(0, -1, 1.0f));
        this.f2133y.add(viewInflate);
        if (i2 == 1) {
            this.f2129B = viewFindViewById;
        }
    }

    public final void t0() {
        this.f2131w.removeAllViews();
        this.f2133y.clear();
        s0(0, f0.e.f5009b0, "聊天");
        s0(1, f0.e.f5011c0, "好友");
        s0(2, f0.e.f5017f0, "发现");
        s0(3, f0.e.f5013d0, "我的");
        x0(this.f2132x.getCurrentItem());
        w0(i0.b.b(this));
        this.f2132x.c(new b());
    }

    public final void u0(ViewPager viewPager) {
        i iVar = new i(y());
        iVar.v(new m0.b(), "聊天");
        iVar.v(new m0.e(), "好友");
        iVar.v(new m0.c(), "发现");
        iVar.v(new k(), "我的");
        viewPager.setAdapter(iVar);
        if (viewPager instanceof NoAnimViewPager) {
            ((NoAnimViewPager) viewPager).setDefaultSmoothScroll(true);
        }
    }

    public final void v0(String str, String str2, String str3, boolean z2) {
        c.a aVar = new c.a(this, f0.k.f5244a);
        if (str2 == null || str2.isEmpty()) {
            aVar.t("系统通知");
        } else {
            aVar.t(str2);
        }
        aVar.i(str3);
        aVar.d(false);
        if (z2) {
            boolean[] zArr = {false};
            getLayoutInflater().inflate(R.layout.select_dialog_singlechoice, (ViewGroup) null);
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText("不再提醒");
            checkBox.setOnCheckedChangeListener(new f(zArr));
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(1);
            linearLayout.setPadding(50, 20, 50, 20);
            linearLayout.addView(checkBox);
            aVar.u(linearLayout);
            aVar.q("知道了", new g(zArr, str));
        } else {
            aVar.q("知道了", new h(str));
        }
        aVar.v();
    }

    public void w0(int i2) {
        View view = this.f2129B;
        if (view == null) {
            return;
        }
        view.setVisibility(i2 > 0 ? 0 : 8);
    }

    public final void x0(int i2) {
        int i3 = 0;
        while (i3 < this.f2133y.size()) {
            r0((View) this.f2133y.get(i3), i3 == i2);
            i3++;
        }
    }
}

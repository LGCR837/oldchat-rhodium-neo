package m0;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import c.AbstractComponentCallbacksC0165t;
import com.im.oldchat.ui.FavoritesActivity;
import com.im.oldchat.ui.ProfileEditActivity;
import com.im.oldchat.ui.SettingsActivity;
import com.im.oldchat.ui.UserSpaceActivity;
import g0.d;
import l0.R0;
import o0.AbstractC0455v;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class k extends AbstractComponentCallbacksC0165t {

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public ImageView f6551Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public TextView f6552a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public TextView f6553b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public TextView f6554c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public TextView f6555d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public TextView f6556e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public View f6557f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public View f6558g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public View f6559h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public View f6560i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public String f6561j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public String f6562k0;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            k.this.F0(new Intent(k.this.e(), (Class<?>) ProfileEditActivity.class));
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            k.this.F0(new Intent(k.this.e(), (Class<?>) UserSpaceActivity.class));
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            k.this.F0(new Intent(k.this.e(), (Class<?>) FavoritesActivity.class));
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            k.this.F0(new Intent(k.this.e(), (Class<?>) SettingsActivity.class));
        }
    }

    public class e implements d.i {
        public e() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                k.this.M0(str);
                k.this.J0(str);
            } catch (Exception unused) {
                Toast.makeText(k.this.e(), "加载资料失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(k.this.e(), "加载资料失败: " + i2, 0).show();
        }
    }

    public final void J0(String str) {
        String str2 = "";
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("uid", "");
            String strOptString2 = jSONObject.optString("username", "");
            String strOptString3 = jSONObject.optString("display_name", "");
            String strOptString4 = jSONObject.optString("user_title", "");
            this.f6562k0 = jSONObject.optString("avatar_url", "");
            if (strOptString3 == null || strOptString3.isEmpty()) {
                strOptString3 = strOptString;
            }
            TextView textView = this.f6552a0;
            if (textView != null) {
                if (strOptString3 != null) {
                    str2 = strOptString3;
                }
                textView.setText(str2);
            }
            R0.a(this.f6553b0, strOptString4);
            StringBuilder sb = new StringBuilder();
            if (strOptString != null && !strOptString.isEmpty()) {
                sb.append("UID: ");
                sb.append(strOptString);
            }
            if (strOptString2 != null && !strOptString2.isEmpty()) {
                if (sb.length() > 0) {
                    sb.append("  ");
                }
                sb.append("用户名: ");
                sb.append(strOptString2);
            }
            TextView textView2 = this.f6554c0;
            if (textView2 != null) {
                textView2.setText(sb.toString());
            }
            int iOptInt = jSONObject.optInt("coin_balance", 0);
            int iOptInt2 = jSONObject.optInt("reputation_score", 0);
            if (this.f6555d0 != null) {
                String strR = r(f0.j.y2, Integer.valueOf(iOptInt));
                this.f6555d0.setText(q(f0.j.m3) + ": " + strR);
            }
            if (this.f6556e0 != null) {
                String strR2 = r(f0.j.w2, Integer.valueOf(iOptInt2));
                this.f6556e0.setText(q(f0.j.x2) + ": " + strR2);
            }
            AbstractC0455v.h(this.f6551Z, this.f6562k0);
        } catch (Exception unused) {
        }
    }

    @Override // c.AbstractComponentCallbacksC0165t
    public View K(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(f0.g.f5160n0, viewGroup, false);
        this.f6551Z = (ImageView) viewInflate.findViewById(f0.f.m5);
        this.f6552a0 = (TextView) viewInflate.findViewById(f0.f.tb);
        this.f6553b0 = (TextView) viewInflate.findViewById(f0.f.sb);
        this.f6554c0 = (TextView) viewInflate.findViewById(f0.f.rb);
        this.f6555d0 = (TextView) viewInflate.findViewById(f0.f.je);
        this.f6556e0 = (TextView) viewInflate.findViewById(f0.f.gd);
        this.f6557f0 = viewInflate.findViewById(f0.f.I7);
        this.f6558g0 = viewInflate.findViewById(f0.f.h2);
        this.f6559h0 = viewInflate.findViewById(f0.f.g2);
        this.f6560i0 = viewInflate.findViewById(f0.f.K3);
        this.f6561j0 = e().getSharedPreferences("auth", 0).getString("access_token", "");
        View view = this.f6557f0;
        if (view != null) {
            view.setOnClickListener(new a());
        }
        View view2 = this.f6558g0;
        if (view2 != null) {
            view2.setOnClickListener(new b());
        }
        View view3 = this.f6559h0;
        if (view3 != null) {
            view3.setOnClickListener(new c());
        }
        View view4 = this.f6560i0;
        if (view4 != null) {
            view4.setOnClickListener(new d());
        }
        L0();
        return viewInflate;
    }

    public final void K0() {
        String string;
        if (e() == null || (string = e().getSharedPreferences("profile_cache", 0).getString("me_profile_json", "")) == null || string.isEmpty()) {
            return;
        }
        J0(string);
    }

    public final void L0() {
        K0();
        g0.d.R("/me", this.f6561j0, new e());
    }

    public final void M0(String str) {
        if (e() == null || str == null) {
            return;
        }
        e().getSharedPreferences("profile_cache", 0).edit().putString("me_profile_json", str).apply();
    }

    @Override // c.AbstractComponentCallbacksC0165t
    public void Z() {
        super.Z();
        L0();
    }
}

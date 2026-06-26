package m0;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import c.AbstractComponentCallbacksC0165t;
import com.im.oldchat.ui.DiscoverSettingsActivity;
import com.im.oldchat.ui.EmojiPlazaActivity;
import com.im.oldchat.ui.MomentsActivity;
import com.im.oldchat.ui.MusicPlazaActivity;
import com.im.oldchat.ui.OldViewActivity;
import com.im.oldchat.ui.PublicCourtActivity;
import com.im.oldchat.ui.ReportProgressActivity;
import g0.d;
import k.AbstractC0285k0;
import o0.V;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class c extends AbstractComponentCallbacksC0165t {

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public View f6456Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public View f6457a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public View f6458b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public View f6459c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public ImageView f6460d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public ImageView f6461e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public ImageView f6462f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public boolean f6463g0;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            c.this.O0();
        }
    }

    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Class f6465a;

        public b(Class cls) {
            this.f6465a = cls;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            c.this.M0(this.f6465a);
        }
    }

    /* JADX INFO: renamed from: m0.c$c, reason: collision with other inner class name */
    public class C0058c implements d.i {
        public C0058c() {
        }

        @Override // g0.d.i
        public void b(String str) {
            if (c.this.e() == null) {
                return;
            }
            c.this.f6463g0 = false;
            c.this.P0();
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.optBoolean("already_checked", false)) {
                    Toast.makeText(c.this.e(), "今天已签到", 0).show();
                    return;
                }
                int iOptInt = jSONObject.optInt("coin_reward", 10);
                int iOptInt2 = jSONObject.optInt("reputation_reward", 50);
                Toast.makeText(c.this.e(), "签到成功 +" + iOptInt + "旧币 +" + iOptInt2 + "信誉", 0).show();
            } catch (Exception unused) {
                Toast.makeText(c.this.e(), "签到成功", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (c.this.e() == null) {
                return;
            }
            c.this.f6463g0 = false;
            c.this.P0();
            if (i2 == 409) {
                Toast.makeText(c.this.e(), "今天已签到", 0).show();
            } else {
                Toast.makeText(c.this.e(), "签到失败", 0).show();
            }
        }
    }

    @Override // c.AbstractComponentCallbacksC0165t
    public View K(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(f0.g.f5156l0, viewGroup, false);
    }

    public final void L0(View view, Class cls) {
        if (view == null) {
            return;
        }
        view.setOnClickListener(new b(cls));
    }

    public final void M0(Class cls) {
        if (e() == null) {
            return;
        }
        F0(new Intent(e(), (Class<?>) cls));
    }

    public final void N0() {
        if (this.f6459c0 == null || e() == null) {
            return;
        }
        this.f6459c0.setVisibility(i0.c.f(e()) ? 0 : 8);
    }

    public final void O0() {
        if (this.f6463g0 || e() == null) {
            return;
        }
        SharedPreferences sharedPreferences = e().getSharedPreferences("auth", 0);
        String string = sharedPreferences.getString("access_token", null);
        if (string == null || string.length() == 0) {
            string = sharedPreferences.getString("token", null);
        }
        if (string == null || string.length() == 0) {
            Toast.makeText(e(), "请先登录", 0).show();
            return;
        }
        this.f6463g0 = true;
        P0();
        g0.d.S("/me/checkin", new JSONObject(), string, new C0058c());
    }

    public final void P0() {
        View view = this.f6458b0;
        if (view == null) {
            return;
        }
        view.setEnabled(!this.f6463g0);
        this.f6458b0.setClickable(!this.f6463g0);
        AbstractC0285k0.K(this.f6458b0, this.f6463g0 ? 0.6f : 1.0f);
    }

    public final void Q0() {
        if (e() == null) {
            return;
        }
        boolean zK = V.k(e());
        boolean zC = i0.g.c(e());
        View view = this.f6456Z;
        if (view != null) {
            view.setVisibility(zK ? 0 : 8);
        }
        View view2 = this.f6457a0;
        if (view2 != null) {
            view2.setVisibility(zC ? 0 : 8);
        }
    }

    public final void R0() {
        if (e() == null) {
            return;
        }
        int color = m().getColor(f0.d.f4973j);
        S0(this.f6460d0, color);
        S0(this.f6461e0, color);
        S0(this.f6462f0, color);
    }

    public final void S0(ImageView imageView, int i2) {
        if (imageView == null) {
            return;
        }
        imageView.setColorFilter(i2, PorterDuff.Mode.SRC_IN);
    }

    @Override // c.AbstractComponentCallbacksC0165t
    public void Z() {
        super.Z();
        Q0();
        N0();
        P0();
    }

    @Override // c.AbstractComponentCallbacksC0165t
    public void d0(View view, Bundle bundle) {
        super.d0(view, bundle);
        View viewFindViewById = view.findViewById(f0.f.f5085j0);
        View viewFindViewById2 = view.findViewById(f0.f.f5083i0);
        View viewFindViewById3 = view.findViewById(f0.f.f5087k0);
        this.f6456Z = view.findViewById(f0.f.f5089l0);
        this.f6458b0 = view.findViewById(f0.f.f5081h0);
        View viewFindViewById4 = view.findViewById(f0.f.f5093n0);
        this.f6457a0 = view.findViewById(f0.f.f5091m0);
        View viewFindViewById5 = view.findViewById(f0.f.f5095o0);
        this.f6459c0 = view.findViewById(f0.f.te);
        this.f6460d0 = (ImageView) view.findViewById(f0.f.r5);
        this.f6461e0 = (ImageView) view.findViewById(f0.f.s5);
        this.f6462f0 = (ImageView) view.findViewById(f0.f.t5);
        R0();
        L0(viewFindViewById, MomentsActivity.class);
        L0(viewFindViewById2, EmojiPlazaActivity.class);
        L0(viewFindViewById3, MusicPlazaActivity.class);
        L0(this.f6456Z, OldViewActivity.class);
        L0(viewFindViewById4, ReportProgressActivity.class);
        L0(this.f6457a0, PublicCourtActivity.class);
        L0(viewFindViewById5, DiscoverSettingsActivity.class);
        View view2 = this.f6458b0;
        if (view2 != null) {
            view2.setOnClickListener(new a());
        }
    }
}

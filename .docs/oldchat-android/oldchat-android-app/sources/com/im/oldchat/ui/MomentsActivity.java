package com.im.oldchat.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.X;
import g0.d;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import o0.AbstractC0455v;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class MomentsActivity extends f0.a implements X.e {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public String f3109A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public String f3110B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public View f3111C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public ImageView f3112D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public ImageView f3113E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public TextView f3114F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public TextView f3115G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public View f3116H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public View f3117I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public View f3118J;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public String f3122N;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ListView f3124w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public ProgressBar f3125x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public X f3126y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final List f3127z = new ArrayList();

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public int f3119K = 0;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public boolean f3120L = true;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public boolean f3121M = false;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public int f3123O = 0;

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3128a;

        public a(int i2) {
            this.f3128a = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            MomentsActivity.this.f3124w.setSelection(this.f3128a);
        }
    }

    public class b implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.m f3130a;

        public b(j0.m mVar) {
            this.f3130a = mVar;
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                j0.m mVar = this.f3130a;
                mVar.f5577k = jSONObject.optBoolean("liked", mVar.f5577k);
                j0.m mVar2 = this.f3130a;
                mVar2.f5575i = jSONObject.optInt("likes", mVar2.f5575i);
                MomentsActivity.this.f3126y.notifyDataSetChanged();
            } catch (Exception unused) {
                MomentsActivity.this.f3126y.notifyDataSetChanged();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(MomentsActivity.this, "操作失败", 0).show();
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MomentsActivity.this.finish();
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MomentsActivity.this.K0();
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MomentsActivity.this.startActivity(new Intent(MomentsActivity.this, (Class<?>) MomentNoticeActivity.class));
        }
    }

    public class f implements AdapterView.OnItemLongClickListener {
        public f() {
        }

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView adapterView, View view, int i2, long j2) {
            int headerViewsCount = i2 - MomentsActivity.this.f3124w.getHeaderViewsCount();
            if (headerViewsCount < 0 || headerViewsCount >= MomentsActivity.this.f3127z.size()) {
                return false;
            }
            j0.m mVar = (j0.m) MomentsActivity.this.f3127z.get(headerViewsCount);
            if (!l0.W.d(MomentsActivity.this.f3110B, mVar)) {
                return false;
            }
            MomentsActivity momentsActivity = MomentsActivity.this;
            l0.W.b(momentsActivity, momentsActivity.f3109A, mVar, headerViewsCount, MomentsActivity.this.f3127z, MomentsActivity.this.f3126y);
            return true;
        }
    }

    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MomentsActivity.this.f3110B == null || MomentsActivity.this.f3110B.isEmpty()) {
                return;
            }
            Intent intent = new Intent(MomentsActivity.this, (Class<?>) UserSpaceActivity.class);
            intent.putExtra("uid", MomentsActivity.this.f3110B);
            MomentsActivity.this.startActivity(intent);
        }
    }

    public class h implements d.i {
        public h() {
        }

        @Override // g0.d.i
        public void b(String str) {
            MomentsActivity.this.C0(str);
            MomentsActivity.this.A0();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
        }
    }

    public class i implements View.OnClickListener {
        public i() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MomentsActivity.this.f3121M || !MomentsActivity.this.f3120L) {
                return;
            }
            MomentsActivity.this.I0();
        }
    }

    public class j implements d.i {
        public j() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("moments");
                List listA = m0.g.a(jSONArray);
                if (MomentsActivity.this.M0(listA)) {
                    MomentsActivity.this.f3126y.notifyDataSetChanged();
                    MomentsActivity.this.L0();
                }
                MomentsActivity.this.f3120L = jSONArray.length() >= 10;
                MomentsActivity momentsActivity = MomentsActivity.this;
                j0.n.e(momentsActivity, momentsActivity.f3127z);
                MomentsActivity momentsActivity2 = MomentsActivity.this;
                i0.c.c(momentsActivity2, listA, momentsActivity2.f3110B);
                MomentsActivity.this.T0();
                MomentsActivity.this.S0();
                MomentsActivity.this.J0();
            } catch (Exception unused) {
                Toast.makeText(MomentsActivity.this, "加载动态失败", 0).show();
            }
            MomentsActivity.this.Q0(false);
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(MomentsActivity.this, "加载动态失败: " + i2, 0).show();
            MomentsActivity.this.G0();
            MomentsActivity.this.Q0(false);
            MomentsActivity.this.S0();
        }
    }

    public class k implements d.i {
        public k() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("moments");
                List listA = m0.g.a(jSONArray);
                MomentsActivity.this.f3127z.addAll(listA);
                MomentsActivity.this.f3120L = jSONArray.length() >= 10;
                MomentsActivity momentsActivity = MomentsActivity.this;
                i0.c.c(momentsActivity, listA, momentsActivity.f3110B);
                MomentsActivity.this.f3126y.notifyDataSetChanged();
                MomentsActivity.this.L0();
                MomentsActivity.this.T0();
                MomentsActivity.this.J0();
            } catch (Exception unused) {
                Toast.makeText(MomentsActivity.this, "加载更多失败", 0).show();
            }
            MomentsActivity.this.f3121M = false;
            MomentsActivity.this.S0();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(MomentsActivity.this, "加载更多失败: " + i2, 0).show();
            MomentsActivity.this.f3121M = false;
            MomentsActivity.this.S0();
        }
    }

    private void P0() {
        View viewInflate = getLayoutInflater().inflate(f0.g.c1, (ViewGroup) this.f3124w, false);
        this.f3118J = viewInflate;
        View viewFindViewById = viewInflate.findViewById(f0.f.o1);
        this.f3117I = viewFindViewById;
        viewFindViewById.setOnClickListener(new i());
        if (this.f3124w.getFooterViewsCount() == 0) {
            this.f3124w.addFooterView(this.f3118J);
        }
        S0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q0(boolean z2) {
        ProgressBar progressBar = this.f3125x;
        if (progressBar != null) {
            progressBar.setVisibility(z2 ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S0() {
        if (this.f3124w == null || this.f3117I == null) {
            return;
        }
        List list = this.f3127z;
        boolean z2 = (list == null || list.isEmpty()) ? false : true;
        if (this.f3118J != null && this.f3124w.getFooterViewsCount() == 0) {
            this.f3124w.addFooterView(this.f3118J);
        }
        if (!z2) {
            View view = this.f3118J;
            if (view != null) {
                view.setVisibility(8);
                return;
            }
            return;
        }
        View view2 = this.f3118J;
        if (view2 != null) {
            view2.setVisibility(0);
        }
        if (this.f3121M) {
            this.f3117I.setEnabled(false);
            ((TextView) this.f3117I).setText("加载中...");
        } else if (this.f3120L) {
            this.f3117I.setEnabled(true);
            ((TextView) this.f3117I).setText("加载更多");
        } else {
            this.f3117I.setEnabled(false);
            ((TextView) this.f3117I).setText("没有更多了");
        }
    }

    public final void A0() {
        String strOptString;
        String str;
        String strOptString2;
        String strOptString3;
        if (this.f3114F == null) {
            return;
        }
        String str2 = this.f3110B;
        try {
            String string = getSharedPreferences("profile_cache", 0).getString("me_profile_json", "");
            if (string == null || string.length() <= 0) {
                strOptString = "";
                strOptString3 = strOptString;
                str = strOptString3;
                strOptString2 = str;
            } else {
                JSONObject jSONObject = new JSONObject(string);
                String strOptString4 = jSONObject.optString("uid", "");
                str = !TextUtils.isEmpty(strOptString4) ? strOptString4 : "";
                try {
                    if (TextUtils.isEmpty(this.f3110B) && !TextUtils.isEmpty(strOptString4)) {
                        this.f3110B = strOptString4;
                        str2 = strOptString4;
                    }
                    String strOptString5 = jSONObject.optString("display_name", "");
                    String strOptString6 = jSONObject.optString("username", "");
                    if (!TextUtils.isEmpty(strOptString5)) {
                        str2 = strOptString5;
                    } else if (!TextUtils.isEmpty(strOptString6)) {
                        str2 = strOptString6;
                    } else if (!TextUtils.isEmpty(strOptString4)) {
                        str2 = strOptString4;
                    }
                    strOptString = jSONObject.optString("avatar_url", "");
                    try {
                        strOptString2 = jSONObject.optString("cover_url", "");
                        try {
                            strOptString3 = jSONObject.optString("signature", "");
                        } catch (Exception unused) {
                            strOptString3 = "";
                        }
                    } catch (Exception unused2) {
                        strOptString2 = "";
                    }
                } catch (Exception unused3) {
                    strOptString = "";
                    strOptString2 = strOptString;
                }
            }
        } catch (Exception unused4) {
            strOptString = "";
            str = strOptString;
            strOptString2 = str;
        }
        if (TextUtils.isEmpty(strOptString2) || TextUtils.isEmpty(strOptString3)) {
            try {
                if (TextUtils.isEmpty(str)) {
                    str = this.f3110B;
                }
                String upperCase = str == null ? "" : str.trim().toUpperCase(Locale.US);
                if (upperCase.length() > 0) {
                    String string2 = getSharedPreferences("user_space_profile_cache", 0).getString("profile_" + upperCase, "");
                    if (string2 != null && string2.length() > 0) {
                        JSONObject jSONObject2 = new JSONObject(string2);
                        if (TextUtils.isEmpty(strOptString2)) {
                            strOptString2 = jSONObject2.optString("cover_url", "");
                        }
                        if (TextUtils.isEmpty(strOptString3)) {
                            strOptString3 = jSONObject2.optString("signature", "");
                        }
                    }
                }
            } catch (Exception unused5) {
            }
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "我";
        }
        this.f3114F.setText(str2);
        ImageView imageView = this.f3113E;
        if (imageView != null) {
            AbstractC0455v.h(imageView, strOptString);
        }
        B0(strOptString3);
        z0(strOptString2);
    }

    public final void B0(String str) {
        if (this.f3115G == null) {
            return;
        }
        String strTrim = str == null ? "" : str.trim();
        if (strTrim.length() == 0) {
            strTrim = "暂无签名";
        }
        this.f3115G.setText(strTrim);
    }

    public final void C0(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        try {
            getSharedPreferences("profile_cache", 0).edit().putString("me_profile_json", str).apply();
            String strOptString = new JSONObject(str).optString("uid", "");
            if (strOptString != null) {
                strOptString = strOptString.trim();
            }
            if (strOptString == null || strOptString.length() <= 0) {
                return;
            }
            getSharedPreferences("user_space_profile_cache", 0).edit().putString("profile_" + strOptString.toUpperCase(Locale.US), str).apply();
        } catch (Exception unused) {
        }
    }

    public final int D0(String str) {
        if (str != null && !str.isEmpty()) {
            for (int i2 = 0; i2 < this.f3127z.size(); i2++) {
                j0.m mVar = (j0.m) this.f3127z.get(i2);
                if (mVar != null && str.equals(mVar.f5567a)) {
                    return i2;
                }
            }
        }
        return -1;
    }

    public final boolean E0(j0.m mVar, j0.m mVar2) {
        return (mVar == null || mVar2 == null) ? mVar == mVar2 : N0(mVar.f5567a, mVar2.f5567a) && N0(mVar.f5568b, mVar2.f5568b) && N0(mVar.f5569c, mVar2.f5569c) && N0(mVar.f5570d, mVar2.f5570d) && N0(mVar.f5571e, mVar2.f5571e) && N0(mVar.f5572f, mVar2.f5572f) && N0(mVar.f5573g, mVar2.f5573g) && mVar.f5574h == mVar2.f5574h && mVar.f5575i == mVar2.f5575i && mVar.f5576j == mVar2.f5576j && mVar.f5577k == mVar2.f5577k;
    }

    public final boolean F0(List list, List list2) {
        if (list == null || list2 == null) {
            return list == list2;
        }
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (!E0((j0.m) list.get(i2), (j0.m) list2.get(i2))) {
                return false;
            }
        }
        return true;
    }

    public final void G0() {
        List listC = j0.n.c(this);
        if (listC == null || listC.isEmpty()) {
            return;
        }
        if (M0(listC)) {
            this.f3126y.notifyDataSetChanged();
            L0();
        }
        T0();
        S0();
    }

    public final void H0() {
        String str = this.f3109A;
        if (str == null || str.isEmpty() || !o0.G.d().f()) {
            Q0(false);
            return;
        }
        g0.d.R("/moments/v2?limit=10&offset=" + this.f3119K, this.f3109A, new j());
    }

    public final void I0() {
        if (this.f3121M || !this.f3120L) {
            return;
        }
        this.f3121M = true;
        this.f3119K = this.f3127z.size();
        S0();
        g0.d.R("/moments/v2?limit=10&offset=" + this.f3119K, this.f3109A, new k());
    }

    public final void J0() {
        int i2;
        String str = this.f3122N;
        if (str == null || str.isEmpty()) {
            return;
        }
        int iD0 = D0(this.f3122N);
        if (iD0 >= 0) {
            this.f3124w.post(new a(iD0 + this.f3124w.getHeaderViewsCount()));
            this.f3122N = null;
        } else {
            if (!this.f3120L || this.f3121M || (i2 = this.f3123O) >= 3) {
                return;
            }
            this.f3123O = i2 + 1;
            I0();
        }
    }

    public final void K0() {
        startActivity(new Intent(this, (Class<?>) MomentComposeActivity.class));
    }

    public final void L0() {
        String str;
        List list = this.f3127z;
        if (list == null || list.isEmpty() || this.f3114F == null || TextUtils.isEmpty(this.f3110B)) {
            return;
        }
        for (int i2 = 0; i2 < this.f3127z.size(); i2++) {
            j0.m mVar = (j0.m) this.f3127z.get(i2);
            if (mVar != null && (str = mVar.f5568b) != null && this.f3110B.equals(str)) {
                if (!TextUtils.isEmpty(mVar.f5569c)) {
                    this.f3114F.setText(mVar.f5569c);
                }
                if (this.f3113E == null || TextUtils.isEmpty(mVar.f5571e)) {
                    return;
                }
                AbstractC0455v.h(this.f3113E, mVar.f5571e);
                return;
            }
        }
    }

    public final boolean M0(List list) {
        if (list == null) {
            list = new ArrayList();
        }
        if (F0(this.f3127z, list)) {
            return false;
        }
        this.f3127z.clear();
        this.f3127z.addAll(list);
        return true;
    }

    public final boolean N0(String str, String str2) {
        return str == null ? str2 == null : str.equals(str2);
    }

    public final void O0() {
        if (this.f3124w == null || this.f3116H != null) {
            return;
        }
        View viewInflate = getLayoutInflater().inflate(f0.g.b1, (ViewGroup) this.f3124w, false);
        this.f3116H = viewInflate;
        this.f3112D = (ImageView) viewInflate.findViewById(f0.f.K5);
        this.f3113E = (ImageView) this.f3116H.findViewById(f0.f.J5);
        this.f3114F = (TextView) this.f3116H.findViewById(f0.f.La);
        this.f3115G = (TextView) this.f3116H.findViewWithTag("moments_header_signature");
        if (this.f3124w.getHeaderViewsCount() == 0) {
            this.f3124w.addHeaderView(this.f3116H, null, false);
        }
        ImageView imageView = this.f3113E;
        if (imageView != null) {
            imageView.setOnClickListener(new g());
        }
    }

    public final void R0() {
        String str = this.f3109A;
        if (str == null || str.length() == 0 || !o0.G.d().f()) {
            return;
        }
        g0.d.R("/me", this.f3109A, new h());
    }

    public final void T0() {
        View view = this.f3111C;
        if (view == null) {
            return;
        }
        view.setVisibility(i0.c.f(this) ? 0 : 8);
    }

    @Override // com.im.oldchat.ui.X.e
    public void l(j0.m mVar) {
        if (mVar == null || mVar.f5567a == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("moment_id", mVar.f5567a);
            g0.d.S(mVar.f5577k ? "/moments/unlike" : "/moments/like", jSONObject, this.f3109A, new b(mVar));
        } catch (Exception unused) {
            Toast.makeText(this, "操作失败", 0).show();
        }
    }

    @Override // com.im.oldchat.ui.X.e
    public void o(j0.m mVar) {
        if (mVar == null || mVar.f5567a == null) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) MomentCommentsActivity.class);
        intent.putExtra("moment_id", mVar.f5567a);
        intent.putExtra("moment_owner_uid", mVar.f5568b);
        startActivity(intent);
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5110D);
        this.f3124w = (ListView) a0(f0.f.S6);
        this.f3125x = (ProgressBar) a0(f0.f.t7);
        this.f3111C = a0(f0.f.we);
        View viewA0 = a0(f0.f.Ka);
        ListView listView = this.f3124w;
        if (listView != null && viewA0 != null) {
            listView.setEmptyView(viewA0);
        }
        View viewA02 = a0(f0.f.x1);
        if (viewA02 != null) {
            viewA02.setOnClickListener(new c());
        }
        View viewA03 = a0(f0.f.w1);
        if (viewA03 != null) {
            viewA03.setOnClickListener(new d());
        }
        View viewA04 = a0(f0.f.y1);
        if (viewA04 != null) {
            viewA04.setOnClickListener(new e());
        }
        this.f3122N = getIntent().getStringExtra("scroll_moment_id");
        P0();
        O0();
        X x2 = new X(this, this.f3127z, this);
        this.f3126y = x2;
        this.f3124w.setAdapter((ListAdapter) x2);
        this.f3124w.setOnItemLongClickListener(new f());
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onResume() {
        super.onResume();
        T0();
        SharedPreferences sharedPreferences = getSharedPreferences("auth", 0);
        this.f3109A = sharedPreferences.getString("access_token", "");
        this.f3110B = sharedPreferences.getString("my_uid", "");
        A0();
        R0();
        this.f3119K = 0;
        this.f3120L = true;
        this.f3123O = 0;
        List list = this.f3127z;
        if (list == null || list.isEmpty()) {
            Q0(true);
        }
        G0();
        H0();
    }

    @Override // com.im.oldchat.ui.X.e
    public void q(j0.m mVar) {
        String str;
        if (mVar == null || (str = mVar.f5568b) == null || str.isEmpty()) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) UserSpaceActivity.class);
        intent.putExtra("uid", mVar.f5568b);
        startActivity(intent);
    }

    public final void z0(String str) {
        if (this.f3112D == null) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            AbstractC0455v.e(this.f3112D, str);
        } else {
            this.f3112D.setImageResource(f0.e.f5020h);
            this.f3112D.setTag(null);
        }
    }
}

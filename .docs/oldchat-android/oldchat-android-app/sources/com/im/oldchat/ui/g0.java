package com.im.oldchat.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.X;
import g0.d;
import java.util.ArrayList;
import java.util.List;
import l0.O0;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class g0 extends f0.a implements X.e {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public TextView f4562A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public TextView f4563B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public TextView f4564C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public View f4565D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public View f4566E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public View f4567F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public View f4568G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public View f4569H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public View f4570I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public X f4571J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final List f4572K = new ArrayList();

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public String f4573L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public String f4574M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public String f4575N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public boolean f4576O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public boolean f4577P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public String f4578Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public String f4579R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public boolean f4580S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public boolean f4581T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public boolean f4582U;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ListView f4583w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public ImageView f4584x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public ImageView f4585y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public TextView f4586z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            g0.this.startActivity(new Intent(g0.this, (Class<?>) ProfileSpaceEditActivity.class));
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            g0.this.startActivity(new Intent(g0.this, (Class<?>) MomentComposeActivity.class));
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            g0.this.finish();
        }
    }

    public class d implements AdapterView.OnItemLongClickListener {
        public d() {
        }

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView adapterView, View view, int i2, long j2) {
            int headerViewsCount = i2 - g0.this.f4583w.getHeaderViewsCount();
            if (headerViewsCount < 0 || headerViewsCount >= g0.this.f4572K.size()) {
                return false;
            }
            j0.m mVar = (j0.m) g0.this.f4572K.get(headerViewsCount);
            if (!g0.this.g0(mVar)) {
                return false;
            }
            g0.this.h0(mVar, headerViewsCount);
            return true;
        }
    }

    public class e implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f4591a;

        public e(boolean z2) {
            this.f4591a = z2;
        }

        @Override // g0.d.i
        public void b(String str) {
            g0.this.n0(str);
            if (g0.this.f0(str)) {
                return;
            }
            Toast.makeText(g0.this, "加载资料失败", 0).show();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            if (g0.this.s0(i2, str, this.f4591a)) {
                g0 g0Var = g0.this;
                g0Var.f4582U = true;
                g0Var.r0();
                return;
            }
            if (g0.this.t0(i2, str, this.f4591a)) {
                g0.this.r0();
                return;
            }
            if (g0.this.i0(i2, str)) {
                g0 g0Var2 = g0.this;
                if (g0Var2.f4581T) {
                    Toast.makeText(g0Var2, "网络波动，已显示缓存资料", 0).show();
                    return;
                } else {
                    Toast.makeText(g0Var2, "用户不存在或已注销", 0).show();
                    return;
                }
            }
            g0 g0Var3 = g0.this;
            if (g0Var3.f4581T) {
                Toast.makeText(g0Var3, "网络波动，已显示缓存资料", 0).show();
            } else {
                Toast.makeText(g0Var3, "加载资料失败，请检查网络", 0).show();
            }
        }
    }

    public class f implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f4593a;

        public f(boolean z2) {
            this.f4593a = z2;
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                List listA = m0.g.a(new JSONObject(str).getJSONArray("moments"));
                g0.this.f4572K.clear();
                g0.this.f4572K.addAll(listA);
                g0.this.f4571J.notifyDataSetChanged();
            } catch (Exception unused) {
                Toast.makeText(g0.this, "加载动态失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            if (g0.this.t0(i2, str, this.f4593a)) {
                g0.this.q0();
                return;
            }
            if (g0.this.i0(i2, str)) {
                if (g0.this.f4572K.isEmpty()) {
                    Toast.makeText(g0.this, "该用户暂无动态", 0).show();
                }
            } else if (g0.this.f4572K.isEmpty()) {
                Toast.makeText(g0.this, "加载动态失败，请稍后重试", 0).show();
            } else {
                Toast.makeText(g0.this, "网络波动，已保留已加载动态", 0).show();
            }
        }
    }

    public abstract boolean f0(String str);

    public abstract boolean g0(j0.m mVar);

    public abstract void h0(j0.m mVar, int i2);

    public abstract boolean i0(int i2, String str);

    public abstract void j0();

    public void k0() {
        o0(false);
    }

    public void l0() {
        this.f4582U = false;
        j0();
        p0(false);
    }

    public String m0(String str) {
        if (str == null) {
            return "";
        }
        String strTrim = str.trim();
        while (strTrim.startsWith("@")) {
            strTrim = strTrim.substring(1).trim();
        }
        return strTrim;
    }

    public abstract void n0(String str);

    public void o0(boolean z2) {
        String str = this.f4574M;
        if (str == null) {
            str = "";
        }
        g0.d.R("/moments/user?uid=" + Uri.encode(str) + "&limit=20", this.f4573L, new f(z2));
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5148h0);
        this.f4583w = (ListView) a0(f0.f.S6);
        View viewInflate = LayoutInflater.from(this).inflate(f0.g.p0, (ViewGroup) this.f4583w, false);
        this.f4584x = (ImageView) viewInflate.findViewById(f0.f.k6);
        this.f4585y = (ImageView) viewInflate.findViewById(f0.f.j6);
        this.f4586z = (TextView) viewInflate.findViewById(f0.f.Od);
        this.f4562A = (TextView) viewInflate.findViewById(f0.f.Qd);
        this.f4563B = (TextView) viewInflate.findViewById(f0.f.Rd);
        this.f4564C = (TextView) viewInflate.findViewById(f0.f.Pd);
        this.f4565D = viewInflate.findViewById(f0.f.m8);
        this.f4566E = viewInflate.findViewById(f0.f.N3);
        this.f4567F = viewInflate.findViewById(f0.f.P3);
        this.f4568G = viewInflate.findViewById(f0.f.l8);
        this.f4569H = viewInflate.findViewById(f0.f.Q3);
        this.f4570I = viewInflate.findViewById(f0.f.R3);
        View viewFindViewById = viewInflate.findViewById(f0.f.M3);
        this.f4583w.addHeaderView(viewInflate);
        SharedPreferences sharedPreferences = getSharedPreferences("auth", 0);
        this.f4573L = sharedPreferences.getString("access_token", "");
        this.f4575N = sharedPreferences.getString("my_uid", "");
        String strM0 = m0(getIntent().getStringExtra("uid"));
        this.f4574M = strM0;
        if (strM0 == null || strM0.isEmpty()) {
            this.f4574M = this.f4575N;
        }
        if (this.f4574M == null) {
            this.f4574M = "";
        }
        this.f4576O = this.f4574M.equalsIgnoreCase(this.f4575N);
        this.f4577P = O0.f(this, this.f4574M);
        if (!this.f4576O) {
            View view = this.f4565D;
            if (view != null) {
                view.setVisibility(8);
            }
            View view2 = this.f4566E;
            if (view2 != null) {
                view2.setVisibility(8);
            }
            View view3 = this.f4567F;
            if (view3 != null) {
                view3.setVisibility(8);
            }
            View view4 = this.f4568G;
            if (view4 != null) {
                view4.setVisibility(0);
            }
        }
        View view5 = this.f4566E;
        if (view5 != null) {
            view5.setOnClickListener(new a());
        }
        View view6 = this.f4567F;
        if (view6 != null) {
            view6.setOnClickListener(new b());
        }
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(new c());
        }
        if (!this.f4576O) {
            O0.e((UserSpaceActivity) this, this.f4569H, this.f4570I, this.f4577P, this.f4574M, this.f4578Q, this.f4579R, this.f4573L);
        }
        X x2 = new X(this, this.f4572K, this);
        this.f4571J = x2;
        this.f4583w.setAdapter((ListAdapter) x2);
        this.f4583w.setOnItemLongClickListener(new d());
        l0();
        k0();
        this.f4580S = true;
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onResume() {
        super.onResume();
        if (!this.f4576O) {
            boolean zF = O0.f(this, this.f4574M);
            this.f4577P = zF;
            O0.e((UserSpaceActivity) this, this.f4569H, this.f4570I, zF, this.f4574M, this.f4578Q, this.f4579R, this.f4573L);
        }
        if (this.f4580S) {
            this.f4580S = false;
        } else {
            l0();
            k0();
        }
    }

    public void p0(boolean z2) {
        String str;
        if (this.f4576O) {
            str = "/me";
        } else {
            String str2 = this.f4574M;
            if (str2 == null) {
                str2 = "";
            }
            str = "/users/profile?uid=" + Uri.encode(str2);
        }
        g0.d.R(str, this.f4573L, new e(z2));
    }

    public abstract void q0();

    public abstract void r0();

    public abstract boolean s0(int i2, String str, boolean z2);

    public abstract boolean t0(int i2, String str, boolean z2);
}

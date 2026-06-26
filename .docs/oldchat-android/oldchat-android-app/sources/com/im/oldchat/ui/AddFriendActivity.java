package com.im.oldchat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import g0.d;
import l0.AbstractC0410z;
import o0.Q;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AddFriendActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public View f2374A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public String f2375B;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public EditText f2376w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public View f2377x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public EditText f2378y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public View f2379z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AddFriendActivity.this.finish();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String strTrim = AddFriendActivity.this.f2376w.getText().toString().trim();
            if (strTrim.isEmpty()) {
                Toast.makeText(AddFriendActivity.this, f0.j.f5232o0, 0).show();
            } else {
                AddFriendActivity.this.m0(strTrim, true);
            }
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String upperCase = AddFriendActivity.this.f2378y.getText().toString().trim().toUpperCase();
            if (upperCase.isEmpty()) {
                Toast.makeText(AddFriendActivity.this, f0.j.N0, 0).show();
            } else {
                AddFriendActivity.this.l0(upperCase, true);
            }
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AddFriendActivity.this.startActivityForResult(new Intent(AddFriendActivity.this, (Class<?>) QrScanActivity.class), 5210);
        }
    }

    public class e implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f2384a;

        public e(boolean z2) {
            this.f2384a = z2;
        }

        @Override // g0.d.i
        public void b(String str) {
            AddFriendActivity.this.f2377x.setEnabled(true);
            Toast.makeText(AddFriendActivity.this, f0.j.s0, 0).show();
            if (this.f2384a) {
                AddFriendActivity.this.finish();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            AddFriendActivity.this.f2377x.setEnabled(true);
            if (g0.e.v(i2, str)) {
                return;
            }
            if (i2 == 404) {
                Toast.makeText(AddFriendActivity.this, f0.j.q0, 0).show();
                return;
            }
            if (i2 != 409) {
                Toast.makeText(AddFriendActivity.this, f0.j.p0, 0).show();
            } else if (AbstractC0410z.b(str)) {
                Toast.makeText(AddFriendActivity.this, f0.j.r0, 0).show();
            } else {
                Toast.makeText(AddFriendActivity.this, f0.j.f5230n0, 0).show();
            }
        }
    }

    public class f implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f2386a;

        public f(boolean z2) {
            this.f2386a = z2;
        }

        @Override // g0.d.i
        public void b(String str) {
            AddFriendActivity.this.f2379z.setEnabled(true);
            try {
                String strOptString = new JSONObject(str).optString("status", "");
                if ("joined".equalsIgnoreCase(strOptString)) {
                    Toast.makeText(AddFriendActivity.this, f0.j.Q0, 0).show();
                } else if ("pending".equalsIgnoreCase(strOptString)) {
                    Toast.makeText(AddFriendActivity.this, f0.j.P0, 0).show();
                } else {
                    Toast.makeText(AddFriendActivity.this, f0.j.O0, 0).show();
                }
                if (this.f2386a) {
                    AddFriendActivity.this.finish();
                }
            } catch (Exception unused) {
                Toast.makeText(AddFriendActivity.this, f0.j.O0, 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            AddFriendActivity.this.f2379z.setEnabled(true);
            if (g0.e.v(i2, str)) {
                return;
            }
            if (i2 == 404) {
                Toast.makeText(AddFriendActivity.this, f0.j.k1, 0).show();
            } else {
                Toast.makeText(AddFriendActivity.this, f0.j.O0, 0).show();
            }
        }
    }

    public final void l0(String str, boolean z2) {
        this.f2379z.setEnabled(false);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("group_id", str);
            g0.d.S("/groups/join", jSONObject, this.f2375B, new f(z2));
        } catch (Exception unused) {
            this.f2379z.setEnabled(true);
            Toast.makeText(this, f0.j.O0, 0).show();
        }
    }

    public final void m0(String str, boolean z2) {
        this.f2377x.setEnabled(false);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("to_uid", str);
            g0.d.S("/friends/request", jSONObject, this.f2375B, new e(z2));
        } catch (Exception unused) {
            this.f2377x.setEnabled(true);
            Toast.makeText(this, f0.j.p0, 0).show();
        }
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 5210 && i3 == -1 && intent != null) {
            Q.a aVarF = o0.Q.f(intent.getStringExtra("qr_result"));
            if (aVarF == null || aVarF.f7028b.length() == 0) {
                Toast.makeText(this, "二维码内容无效", 0).show();
                return;
            }
            if ("group".equals(aVarF.f7027a)) {
                this.f2378y.setText(aVarF.f7028b);
                l0(aVarF.f7028b, true);
            } else {
                if (!"user".equals(aVarF.f7027a)) {
                    Toast.makeText(this, "暂不支持的二维码", 0).show();
                    return;
                }
                String string = getSharedPreferences("auth", 0).getString("my_uid", "");
                if (string != null && string.equalsIgnoreCase(aVarF.f7028b)) {
                    Toast.makeText(this, "这是你自己的名片", 0).show();
                } else {
                    this.f2376w.setText(aVarF.f7028b);
                    m0(aVarF.f7028b, true);
                }
            }
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5135b);
        this.f2376w = (EditText) a0(f0.f.z4);
        this.f2377x = a0(f0.f.T3);
        this.f2378y = (EditText) a0(f0.f.B4);
        this.f2379z = a0(f0.f.j1);
        this.f2374A = a0(f0.f.y3);
        View viewA0 = a0(f0.f.f5099s);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        this.f2375B = getSharedPreferences("auth", 0).getString("access_token", "");
        this.f2377x.setOnClickListener(new b());
        this.f2379z.setOnClickListener(new c());
        View view = this.f2374A;
        if (view != null) {
            view.setOnClickListener(new d());
        }
    }
}

package com.im.oldchat.ui;

import android.R;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import g0.d;
import java.util.Iterator;
import java.util.List;
import o0.AbstractC0455v;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ChatSettingsActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public View f2521A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public View f2522B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public View f2523C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public View f2524D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public View f2525E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public String f2526F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public String f2527G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public String f2528H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public String f2529I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f2530J = false;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ImageView f2531w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public TextView f2532x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f2533y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public SwitchCompat f2534z;

    public class a implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ProgressDialog f2535a;

        public a(ProgressDialog progressDialog) {
            this.f2535a = progressDialog;
        }

        @Override // g0.d.i
        public void b(String str) {
            this.f2535a.dismiss();
            ChatSettingsActivity chatSettingsActivity = ChatSettingsActivity.this;
            j0.r.g(chatSettingsActivity, chatSettingsActivity.f2527G);
            Toast.makeText(ChatSettingsActivity.this, f0.j.f5231o, 0).show();
            Intent intent = new Intent();
            intent.putExtra("friend_deleted", true);
            ChatSettingsActivity.this.setResult(-1, intent);
            ChatSettingsActivity.this.finish();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            this.f2535a.dismiss();
            Toast.makeText(ChatSettingsActivity.this, f0.j.f5189M, 0).show();
        }
    }

    public class b implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f2537a;

        public b(EditText editText) {
            this.f2537a = editText;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            ChatSettingsActivity.this.D0(this.f2537a.getText() == null ? "" : this.f2537a.getText().toString().trim());
        }
    }

    public class c implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ProgressDialog f2539a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f2540b;

        public c(ProgressDialog progressDialog, String str) {
            this.f2539a = progressDialog;
            this.f2540b = str;
        }

        @Override // g0.d.i
        public void b(String str) {
            this.f2539a.dismiss();
            ChatSettingsActivity chatSettingsActivity = ChatSettingsActivity.this;
            String strE0 = chatSettingsActivity.E0(chatSettingsActivity.f2527G, this.f2540b);
            if (strE0 == null || strE0.length() == 0) {
                strE0 = ChatSettingsActivity.this.f2527G;
            }
            ChatSettingsActivity.this.f2528H = strE0;
            TextView textView = ChatSettingsActivity.this.f2532x;
            if (strE0 == null) {
                strE0 = "";
            }
            textView.setText(strE0);
            ChatSettingsActivity chatSettingsActivity2 = ChatSettingsActivity.this;
            chatSettingsActivity2.setTitle(chatSettingsActivity2.f2528H != null ? ChatSettingsActivity.this.f2528H : "");
            Intent intent = new Intent();
            intent.putExtra("friend_name", ChatSettingsActivity.this.f2528H);
            ChatSettingsActivity.this.setResult(-1, intent);
            ChatSettingsActivity chatSettingsActivity3 = ChatSettingsActivity.this;
            String str2 = this.f2540b;
            Toast.makeText(chatSettingsActivity3, (str2 == null || str2.length() == 0) ? "已清除备注" : "备注已更新", 0).show();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            this.f2539a.dismiss();
            if (g0.e.v(i2, str)) {
                return;
            }
            if (str == null || !str.contains("remark_too_long")) {
                Toast.makeText(ChatSettingsActivity.this, "设置备注失败", 0).show();
            } else {
                Toast.makeText(ChatSettingsActivity.this, "备注最多32个字", 0).show();
            }
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ChatSettingsActivity.this.finish();
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ChatSettingsActivity.this.y0();
        }
    }

    public class f implements CompoundButton.OnCheckedChangeListener {
        public f() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
            if (ChatSettingsActivity.this.f2530J) {
                return;
            }
            ChatSettingsActivity chatSettingsActivity = ChatSettingsActivity.this;
            i0.g.g(chatSettingsActivity, chatSettingsActivity.f2527G, false, z2);
            Toast.makeText(ChatSettingsActivity.this, z2 ? f0.j.f5237t : f0.j.f5238u, 0).show();
        }
    }

    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ChatSettingsActivity.this.B0();
        }
    }

    public class h implements View.OnClickListener {
        public h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ChatSettingsActivity.this.C0();
        }
    }

    public class i implements View.OnClickListener {
        public i() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ChatSettingsActivity.this.v0();
        }
    }

    public class j implements View.OnClickListener {
        public j() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ChatSettingsActivity.this.z0();
        }
    }

    public class k implements DialogInterface.OnClickListener {
        public k() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            if (i2 == 0) {
                ChatSettingsActivity.this.A0();
            } else {
                ChatSettingsActivity.this.u0();
            }
        }
    }

    public class l implements DialogInterface.OnClickListener {
        public l() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            ChatSettingsActivity.this.w0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y0() {
        String str = this.f2527G;
        if (str == null || str.isEmpty()) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) UserSpaceActivity.class);
        intent.putExtra("uid", this.f2527G);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z0() {
        String str = this.f2527G;
        if (str == null || str.isEmpty()) {
            Toast.makeText(this, "好友ID无效", 0).show();
            return;
        }
        Intent intent = new Intent(this, (Class<?>) ChatSearchActivity.class);
        intent.putExtra("mode", "direct");
        intent.putExtra("friend_uid", this.f2527G);
        intent.putExtra("friend_name", this.f2528H);
        intent.putExtra("friend_avatar", this.f2529I);
        startActivity(intent);
    }

    public final void A0() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        intent.addCategory("android.intent.category.OPENABLE");
        try {
            startActivityForResult(Intent.createChooser(intent, getString(f0.j.f5219i)), 3001);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(this, f0.j.f5192P, 0).show();
        }
    }

    public final void B0() {
        String str = this.f2527G;
        if (str == null) {
            return;
        }
        new AlertDialog.Builder(this).setTitle(f0.j.f5227m).setItems(i0.a.k(this, str, false) ? new String[]{getString(f0.j.f5219i), getString(f0.j.f5211e)} : new String[]{getString(f0.j.f5219i)}, new k()).show();
    }

    public final void C0() {
        String str = this.f2527G;
        if (str == null || str.isEmpty()) {
            return;
        }
        EditText editText = new EditText(this);
        editText.setHint(getString(f0.j.a3));
        String strX0 = x0(this.f2527G);
        if (strX0 != null && strX0.length() > 0) {
            editText.setText(strX0);
            editText.setSelection(strX0.length());
        }
        new AlertDialog.Builder(this).setTitle(f0.j.b3).setView(editText).setPositiveButton(R.string.ok, new b(editText)).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).show();
    }

    public final void D0(String str) {
        if (str != null && str.length() > 32) {
            Toast.makeText(this, "备注最多32个字", 0).show();
            return;
        }
        ProgressDialog progressDialogShow = ProgressDialog.show(this, null, "保存中...", true, false);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("friend_uid", this.f2527G);
            jSONObject.put("remark_name", str == null ? "" : str);
            g0.d.S("/friends/remark", jSONObject, this.f2526F, new c(progressDialogShow, str));
        } catch (Exception unused) {
            progressDialogShow.dismiss();
            Toast.makeText(this, "设置备注失败", 0).show();
        }
    }

    public final String E0(String str, String str2) {
        boolean z2;
        String strB;
        String str3;
        List listB = j0.c.b(this);
        if (listB == null || listB.isEmpty()) {
            String strC = j0.d.c(str2, this.f2528H, "", str);
            j0.r.j(this, str, strC, this.f2529I);
            j0.x.d(this, str, strC);
            return strC;
        }
        String str4 = str2 == null ? "" : str2;
        Iterator it = listB.iterator();
        while (true) {
            if (!it.hasNext()) {
                z2 = false;
                strB = "";
                break;
            }
            j0.w wVar = (j0.w) it.next();
            if (wVar != null && (str3 = wVar.f5646b) != null && str.equals(str3)) {
                wVar.f5649e = str4;
                strB = j0.d.b(wVar);
                if (strB == null || strB.length() == 0) {
                    strB = str;
                }
                z2 = true;
            }
        }
        if (z2) {
            j0.c.c(this, listB);
            j0.r.j(this, str, strB, this.f2529I);
            j0.x.d(this, str, strB);
            return strB;
        }
        String strC2 = j0.d.c(str2, this.f2528H, "", str);
        j0.r.j(this, str, strC2, this.f2529I);
        j0.x.d(this, str, strC2);
        return strC2;
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        Uri data;
        String str;
        super.onActivityResult(i2, i3, intent);
        if (i2 != 3001 || i3 != -1 || intent == null || (data = intent.getData()) == null || (str = this.f2527G) == null) {
            return;
        }
        if (i0.a.p(this, str, false, data)) {
            Toast.makeText(this, f0.j.f5221j, 0).show();
        } else {
            Toast.makeText(this, f0.j.f5195S, 0).show();
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5145g);
        this.f2531w = (ImageView) a0(f0.f.z5);
        this.f2532x = (TextView) a0(f0.f.ba);
        this.f2533y = (TextView) a0(f0.f.ca);
        this.f2534z = (SwitchCompat) a0(f0.f.z8);
        this.f2521A = a0(f0.f.f5043D);
        this.f2522B = a0(f0.f.J3);
        this.f2523C = a0(f0.f.f5073d0);
        this.f2524D = a0(f0.f.D3);
        this.f2525E = a0(f0.f.f5);
        View viewA0 = a0(f0.f.f5102v);
        if (viewA0 instanceof ImageView) {
            ((ImageView) viewA0).setColorFilter(getResources().getColor(f0.d.f4973j));
        }
        if (viewA0 != null) {
            viewA0.setOnClickListener(new d());
        }
        this.f2526F = getSharedPreferences("auth", 0).getString("access_token", "");
        this.f2527G = getIntent().getStringExtra("friend_uid");
        this.f2528H = getIntent().getStringExtra("friend_name");
        this.f2529I = getIntent().getStringExtra("friend_avatar");
        TextView textView = this.f2532x;
        String str = this.f2528H;
        if (str == null) {
            str = "";
        }
        textView.setText(str);
        TextView textView2 = this.f2533y;
        String str2 = this.f2527G;
        if (str2 == null) {
            str2 = "";
        }
        textView2.setText(str2);
        String str3 = this.f2528H;
        setTitle(str3 != null ? str3 : "");
        String str4 = this.f2529I;
        if (str4 != null && !str4.isEmpty()) {
            AbstractC0455v.h(this.f2531w, this.f2529I);
        }
        this.f2525E.setOnClickListener(new e());
        this.f2530J = true;
        this.f2534z.setChecked(i0.g.b(this, this.f2527G, false));
        this.f2530J = false;
        this.f2534z.setOnCheckedChangeListener(new f());
        this.f2521A.setOnClickListener(new g());
        View view = this.f2522B;
        if (view != null) {
            view.setOnClickListener(new h());
        }
        this.f2523C.setOnClickListener(new i());
        View view2 = this.f2524D;
        if (view2 != null) {
            view2.setOnClickListener(new j());
        }
    }

    public final void u0() {
        i0.a.e(this, this.f2527G, false);
        Toast.makeText(this, f0.j.f5213f, 0).show();
    }

    public final void v0() {
        new AlertDialog.Builder(this).setMessage(f0.j.f5229n).setPositiveButton(R.string.ok, new l()).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).show();
    }

    public final void w0() {
        String str = this.f2527G;
        if (str == null || str.isEmpty()) {
            return;
        }
        ProgressDialog progressDialogShow = ProgressDialog.show(this, null, getString(f0.j.f5233p), true, false);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("friend_uid", this.f2527G);
            g0.d.S("/friends/delete", jSONObject, this.f2526F, new a(progressDialogShow));
        } catch (Exception unused) {
            progressDialogShow.dismiss();
            Toast.makeText(this, f0.j.f5189M, 0).show();
        }
    }

    public final String x0(String str) {
        String str2;
        List<j0.w> listB = j0.c.b(this);
        if (listB == null || listB.isEmpty()) {
            return "";
        }
        for (j0.w wVar : listB) {
            if (wVar != null && (str2 = wVar.f5646b) != null && str.equals(str2)) {
                String str3 = wVar.f5649e;
                return str3 == null ? "" : str3;
            }
        }
        return "";
    }
}

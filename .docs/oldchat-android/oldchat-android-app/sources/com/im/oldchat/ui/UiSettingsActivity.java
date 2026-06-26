package com.im.oldchat.ui;

import android.R;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import l0.K0;
import l0.L0;
import l0.M0;
import o.c;

/* JADX INFO: loaded from: classes.dex */
public class UiSettingsActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public TextView f4211A;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public TextView f4212w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public TextView f4213x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f4214y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public TextView f4215z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            UiSettingsActivity.this.u0(true);
        }
    }

    public class b implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ TextView f4217a;

        public b(TextView textView) {
            this.f4217a = textView;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            o0.V.u(UiSettingsActivity.this, i2);
            UiSettingsActivity.this.x0(this.f4217a);
            dialogInterface.dismiss();
        }
    }

    public class c implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ TextView f4219a;

        public c(TextView textView) {
            this.f4219a = textView;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            o0.V.A(UiSettingsActivity.this, o0.X.o(i2 - 1));
            UiSettingsActivity.this.y0(this.f4219a);
            Toast.makeText(UiSettingsActivity.this, f0.j.e3, 0).show();
            dialogInterface.dismiss();
            UiSettingsActivity.this.r0();
        }
    }

    public class d implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f4221a;

        public d(boolean z2) {
            this.f4221a = z2;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            if (i2 == 0) {
                UiSettingsActivity.this.q0(this.f4221a);
                return;
            }
            i0.a.f(UiSettingsActivity.this, this.f4221a);
            UiSettingsActivity.this.w0();
            Toast.makeText(UiSettingsActivity.this, f0.j.f5213f, 0).show();
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            UiSettingsActivity.this.finish();
        }
    }

    public class f implements CompoundButton.OnCheckedChangeListener {
        public f() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
            o0.V.s(UiSettingsActivity.this, z2);
            o.f.B(z2 ? 2 : 1);
            UiSettingsActivity.this.r0();
        }
    }

    public class g implements CompoundButton.OnCheckedChangeListener {
        public g() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
            o0.V.v(UiSettingsActivity.this, z2);
        }
    }

    public class h implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ TextView f4226a;

        public h(TextView textView) {
            this.f4226a = textView;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            UiSettingsActivity.this.t0(this.f4226a);
        }
    }

    public class i implements View.OnClickListener {
        public i() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            UiSettingsActivity uiSettingsActivity = UiSettingsActivity.this;
            uiSettingsActivity.v0(uiSettingsActivity.f4214y);
        }
    }

    public class j implements View.OnClickListener {
        public j() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            UiSettingsActivity uiSettingsActivity = UiSettingsActivity.this;
            L0.b(uiSettingsActivity, uiSettingsActivity.f4215z);
        }
    }

    public class k implements View.OnClickListener {
        public k() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            UiSettingsActivity uiSettingsActivity = UiSettingsActivity.this;
            L0.a(uiSettingsActivity, uiSettingsActivity.f4211A);
        }
    }

    public class l implements View.OnClickListener {
        public l() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            UiSettingsActivity.this.startActivity(new Intent(UiSettingsActivity.this, (Class<?>) DiscoverSettingsActivity.class));
        }
    }

    public class m implements View.OnClickListener {
        public m() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            UiSettingsActivity.this.u0(false);
        }
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        Uri data;
        super.onActivityResult(i2, i3, intent);
        if (i3 != -1 || intent == null || (data = intent.getData()) == null) {
            return;
        }
        if (i2 == 4101 ? i0.a.q(this, false, data) : i2 == 4102 ? i0.a.q(this, true, data) : false) {
            Toast.makeText(this, f0.j.f5221j, 0).show();
        } else if (i2 == 4101 || i2 == 4102) {
            Toast.makeText(this, f0.j.f5195S, 0).show();
        }
        w0();
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5146g0);
        View viewA0 = a0(f0.f.Z3);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new e());
        }
        SwitchCompat switchCompat = (SwitchCompat) a0(f0.f.t8);
        if (switchCompat != null) {
            switchCompat.setChecked(o0.V.g(this));
            switchCompat.setOnCheckedChangeListener(new f());
        }
        M0.a(this, (SwitchCompat) a0(f0.f.D8));
        K0.a(this, (SwitchCompat) a0(f0.f.s8));
        SwitchCompat switchCompat2 = (SwitchCompat) a0(f0.f.x8);
        if (switchCompat2 != null) {
            switchCompat2.setChecked(o0.V.i(this));
            switchCompat2.setOnCheckedChangeListener(new g());
        }
        View viewA02 = a0(f0.f.Z7);
        TextView textView = (TextView) a0(f0.f.X9);
        x0(textView);
        if (viewA02 != null) {
            viewA02.setOnClickListener(new h(textView));
        }
        View viewA03 = a0(f0.f.e8);
        TextView textView2 = (TextView) a0(f0.f.Wd);
        this.f4214y = textView2;
        y0(textView2);
        if (viewA03 != null) {
            viewA03.setOnClickListener(new i());
        }
        View viewA04 = a0(f0.f.U7);
        View viewA05 = a0(f0.f.T7);
        this.f4215z = (TextView) a0(f0.f.c9);
        TextView textView3 = (TextView) a0(f0.f.b9);
        this.f4211A = textView3;
        L0.c(this, this.f4215z, textView3);
        if (viewA04 != null) {
            viewA04.setOnClickListener(new j());
        }
        if (viewA05 != null) {
            viewA05.setOnClickListener(new k());
        }
        View viewA06 = a0(f0.f.W7);
        if (viewA06 != null) {
            viewA06.setOnClickListener(new l());
        }
        View viewA07 = a0(f0.f.V7);
        View viewA08 = a0(f0.f.a8);
        this.f4212w = (TextView) a0(f0.f.f9);
        this.f4213x = (TextView) a0(f0.f.la);
        w0();
        if (viewA07 != null) {
            viewA07.setOnClickListener(new m());
        }
        if (viewA08 != null) {
            viewA08.setOnClickListener(new a());
        }
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onResume() {
        super.onResume();
        w0();
        y0(this.f4214y);
        L0.c(this, this.f4215z, this.f4211A);
    }

    public final void q0(boolean z2) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        intent.addCategory("android.intent.category.OPENABLE");
        try {
            startActivityForResult(Intent.createChooser(intent, getString(f0.j.f5219i)), z2 ? 4102 : 4101);
        } catch (Exception unused) {
            Toast.makeText(this, f0.j.f5192P, 0).show();
        }
    }

    public final void r0() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void s0() {
        r0();
    }

    public final void t0(TextView textView) {
        int i2 = 1;
        String[] strArr = {"小", "标准", "大", "特大"};
        int iD = o0.V.d(this);
        if (iD >= 0 && iD < 4) {
            i2 = iD;
        }
        new c.a(this).t("字体大小").r(strArr, i2, new b(textView)).j(R.string.cancel, null).v();
    }

    public final void u0(boolean z2) {
        new c.a(this).s(z2 ? f0.j.f5215g : f0.j.f5217h).g(i0.a.l(this, z2) ? new String[]{getString(f0.j.f5219i), getString(f0.j.f5211e)} : new String[]{getString(f0.j.f5219i)}, new d(z2)).v();
    }

    public final void v0(TextView textView) {
        new c.a(this).s(f0.j.f3).r(o0.X.e(this), o0.X.o(o0.V.e(this)) + 1, new c(textView)).j(R.string.cancel, null).v();
    }

    public final void w0() {
        TextView textView = this.f4212w;
        if (textView != null) {
            textView.setText(i0.a.l(this, false) ? f0.j.f5225l : f0.j.f5223k);
        }
        TextView textView2 = this.f4213x;
        if (textView2 != null) {
            textView2.setText(i0.a.l(this, true) ? f0.j.f5225l : f0.j.f5223k);
        }
    }

    public final void x0(TextView textView) {
        if (textView == null) {
            return;
        }
        int iD = o0.V.d(this);
        textView.setText(iD != 0 ? iD != 2 ? iD != 3 ? "标准" : "特大" : "大" : "小");
    }

    public final void y0(TextView textView) {
        if (textView == null) {
            return;
        }
        textView.setText(o0.X.k(this));
    }
}

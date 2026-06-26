package com.im.oldchat.ui;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;

/* JADX INFO: loaded from: classes.dex */
public class NotificationSettingsActivity extends f0.a {

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NotificationSettingsActivity.this.finish();
        }
    }

    public class b implements CompoundButton.OnCheckedChangeListener {
        public b() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
            o0.V.w(NotificationSettingsActivity.this, z2);
        }
    }

    public class c implements CompoundButton.OnCheckedChangeListener {
        public c() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
            o0.V.B(NotificationSettingsActivity.this, z2);
        }
    }

    public class d implements CompoundButton.OnCheckedChangeListener {
        public d() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
            o0.V.t(NotificationSettingsActivity.this, z2);
        }
    }

    public class e implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ SwitchCompat f3563a;

        public e(SwitchCompat switchCompat) {
            this.f3563a = switchCompat;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f3563a.setChecked(!r2.isChecked());
        }
    }

    public class f implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ SwitchCompat f3565a;

        public f(SwitchCompat switchCompat) {
            this.f3565a = switchCompat;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f3565a.setChecked(!r2.isChecked());
        }
    }

    public class g implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ SwitchCompat f3567a;

        public g(SwitchCompat switchCompat) {
            this.f3567a = switchCompat;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f3567a.setChecked(!r2.isChecked());
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5116J);
        View viewA0 = a0(f0.f.l2);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        SwitchCompat switchCompat = (SwitchCompat) a0(f0.f.A8);
        SwitchCompat switchCompat2 = (SwitchCompat) a0(f0.f.E8);
        SwitchCompat switchCompat3 = (SwitchCompat) a0(f0.f.u8);
        if (switchCompat != null) {
            switchCompat.setChecked(o0.V.j(this));
            switchCompat.setOnCheckedChangeListener(new b());
        }
        if (switchCompat2 != null) {
            switchCompat2.setChecked(o0.V.n(this));
            switchCompat2.setOnCheckedChangeListener(new c());
        }
        if (switchCompat3 != null) {
            switchCompat3.setChecked(o0.V.h(this));
            switchCompat3.setOnCheckedChangeListener(new d());
        }
        View viewA02 = a0(f0.f.b8);
        if (viewA02 != null && switchCompat != null) {
            viewA02.setOnClickListener(new e(switchCompat));
        }
        View viewA03 = a0(f0.f.f8);
        if (viewA03 != null && switchCompat2 != null) {
            viewA03.setOnClickListener(new f(switchCompat2));
        }
        View viewA04 = a0(f0.f.X7);
        if (viewA04 == null || switchCompat3 == null) {
            return;
        }
        viewA04.setOnClickListener(new g(switchCompat3));
    }
}

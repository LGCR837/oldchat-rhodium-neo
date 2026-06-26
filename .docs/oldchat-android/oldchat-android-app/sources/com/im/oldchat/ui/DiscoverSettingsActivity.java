package com.im.oldchat.ui;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;

/* JADX INFO: loaded from: classes.dex */
public class DiscoverSettingsActivity extends f0.a {

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DiscoverSettingsActivity.this.finish();
        }
    }

    public class b implements CompoundButton.OnCheckedChangeListener {
        public b() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
            o0.V.x(DiscoverSettingsActivity.this, z2);
        }
    }

    public class c implements CompoundButton.OnCheckedChangeListener {
        public c() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
            i0.g.h(DiscoverSettingsActivity.this, z2);
        }
    }

    public class d implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ SwitchCompat f2613a;

        public d(SwitchCompat switchCompat) {
            this.f2613a = switchCompat;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f2613a.setChecked(!r2.isChecked());
        }
    }

    public class e implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ SwitchCompat f2615a;

        public e(SwitchCompat switchCompat) {
            this.f2615a = switchCompat;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f2615a.setChecked(!r2.isChecked());
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5153k);
        View viewA0 = a0(f0.f.p0);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        SwitchCompat switchCompat = (SwitchCompat) a0(f0.f.B8);
        SwitchCompat switchCompat2 = (SwitchCompat) a0(f0.f.C8);
        if (switchCompat != null) {
            switchCompat.setChecked(o0.V.k(this));
            switchCompat.setOnCheckedChangeListener(new b());
        }
        if (switchCompat2 != null) {
            switchCompat2.setChecked(i0.g.c(this));
            switchCompat2.setOnCheckedChangeListener(new c());
        }
        View viewA02 = a0(f0.f.c8);
        if (viewA02 != null && switchCompat != null) {
            viewA02.setOnClickListener(new d(switchCompat));
        }
        View viewA03 = a0(f0.f.d8);
        if (viewA03 == null || switchCompat2 == null) {
            return;
        }
        viewA03.setOnClickListener(new e(switchCompat2));
    }
}

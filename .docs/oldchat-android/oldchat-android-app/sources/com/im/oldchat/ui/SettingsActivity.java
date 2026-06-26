package com.im.oldchat.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import c.AbstractC0121a;
import com.im.oldchat.service.MessageService;
import d.AbstractC0235a;
import java.util.ArrayList;
import java.util.List;
import o0.AbstractC0431a;

/* JADX INFO: loaded from: classes.dex */
public class SettingsActivity extends f0.a {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final ArrayList f4112w = new ArrayList();

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f4113x;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SettingsActivity.this.startActivity(new Intent(SettingsActivity.this, (Class<?>) SupportSettingsActivity.class));
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SettingsActivity.this.getSharedPreferences("auth", 0).edit().clear().apply();
            AbstractC0431a.a(SettingsActivity.this);
            g0.j.u().A();
            MessageService.h(SettingsActivity.this);
            Intent intent = new Intent(SettingsActivity.this, (Class<?>) LoginActivity.class);
            intent.addFlags(268468224);
            SettingsActivity.this.startActivity(intent);
            SettingsActivity.this.finish();
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SettingsActivity.this.finish();
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SettingsActivity.this.startActivity(new Intent(SettingsActivity.this, (Class<?>) UiSettingsActivity.class));
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SettingsActivity.this.startActivity(new Intent(SettingsActivity.this, (Class<?>) NotificationSettingsActivity.class));
        }
    }

    public class f implements CompoundButton.OnCheckedChangeListener {
        public f() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
            o0.V.t(SettingsActivity.this, z2);
        }
    }

    public class g implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ SwitchCompat f4120a;

        public g(SwitchCompat switchCompat) {
            this.f4120a = switchCompat;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f4120a.setChecked(!r2.isChecked());
        }
    }

    public class h implements View.OnClickListener {
        public h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SettingsActivity.this.j0();
        }
    }

    public class i implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ CheckBox f4123a;

        public i(CheckBox checkBox) {
            this.f4123a = checkBox;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            boolean z2 = !i0.g.c(SettingsActivity.this);
            i0.g.h(SettingsActivity.this, z2);
            CheckBox checkBox = this.f4123a;
            if (checkBox != null) {
                checkBox.setChecked(z2);
            }
        }
    }

    public class j implements View.OnClickListener {
        public j() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SettingsActivity.this.startActivity(new Intent(SettingsActivity.this, (Class<?>) AccountManagementActivity.class));
        }
    }

    public class k implements View.OnClickListener {
        public k() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SettingsActivity.this.startActivity(new Intent(SettingsActivity.this, (Class<?>) DataSettingsActivity.class));
        }
    }

    public final void g0(List list, String str) {
        if (list == null || str == null || str.length() == 0 || list.contains(str) || AbstractC0235a.a(this, str) == 0) {
            return;
        }
        list.add(str);
    }

    public final void h0(List list) {
        if (list == null) {
            return;
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 33) {
            g0(list, "android.permission.READ_MEDIA_IMAGES");
            g0(list, "android.permission.READ_MEDIA_VIDEO");
            g0(list, "android.permission.READ_MEDIA_AUDIO");
            g0(list, "android.permission.POST_NOTIFICATIONS");
        } else {
            g0(list, "android.permission.READ_EXTERNAL_STORAGE");
            if (i2 <= 28) {
                g0(list, "android.permission.WRITE_EXTERNAL_STORAGE");
            }
        }
        g0(list, "android.permission.RECORD_AUDIO");
        g0(list, "android.permission.READ_PHONE_STATE");
    }

    public final String i0(String str) {
        return (str == null || str.length() == 0) ? "权限" : "android.permission.READ_MEDIA_IMAGES".equals(str) ? "图片读取权限" : "android.permission.READ_MEDIA_VIDEO".equals(str) ? "视频读取权限" : "android.permission.READ_MEDIA_AUDIO".equals(str) ? "音频读取权限" : "android.permission.READ_EXTERNAL_STORAGE".equals(str) ? "媒体读取权限" : "android.permission.WRITE_EXTERNAL_STORAGE".equals(str) ? "存储写入权限" : "android.permission.POST_NOTIFICATIONS".equals(str) ? "通知权限" : "android.permission.RECORD_AUDIO".equals(str) ? "录音权限" : "android.permission.READ_PHONE_STATE".equals(str) ? "设备识别权限" : str;
    }

    public final void j0() {
        if (Build.VERSION.SDK_INT < 23) {
            Toast.makeText(this, "当前系统无需手动申请运行时权限", 0).show();
            return;
        }
        if (this.f4113x) {
            Toast.makeText(this, "正在申请权限，请稍候", 0).show();
            return;
        }
        this.f4112w.clear();
        h0(this.f4112w);
        if (this.f4112w.isEmpty()) {
            Toast.makeText(this, "已授予所需权限", 0).show();
            return;
        }
        this.f4113x = true;
        Toast.makeText(this, "开始申请权限（共 " + this.f4112w.size() + " 项）", 0).show();
        k0();
    }

    public final void k0() {
        if (!this.f4112w.isEmpty()) {
            AbstractC0121a.i(this, new String[]{(String) this.f4112w.get(0)}, 6201);
        } else {
            this.f4113x = false;
            Toast.makeText(this, "权限申请流程已结束", 0).show();
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5142e0);
        View viewA0 = a0(f0.f.L3);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new c());
        }
        View viewA02 = a0(f0.f.Y3);
        if (viewA02 != null) {
            viewA02.setOnClickListener(new d());
        }
        View viewA03 = a0(f0.f.k2);
        if (viewA03 != null) {
            viewA03.setOnClickListener(new e());
        }
        SwitchCompat switchCompat = (SwitchCompat) a0(f0.f.v8);
        View viewA04 = a0(f0.f.Y7);
        if (switchCompat != null) {
            switchCompat.setChecked(o0.V.h(this));
            switchCompat.setOnCheckedChangeListener(new f());
        }
        if (viewA04 != null && switchCompat != null) {
            viewA04.setOnClickListener(new g(switchCompat));
        }
        View viewA05 = a0(f0.f.j3);
        if (viewA05 != null) {
            viewA05.setOnClickListener(new h());
        }
        CheckBox checkBox = (CheckBox) a0(f0.f.f4);
        View viewA06 = a0(f0.f.Q2);
        if (checkBox != null) {
            checkBox.setChecked(i0.g.c(this));
        }
        if (viewA06 != null) {
            viewA06.setOnClickListener(new i(checkBox));
        }
        View viewA07 = a0(f0.f.f5090m);
        if (viewA07 != null) {
            viewA07.setOnClickListener(new j());
        }
        View viewA08 = a0(f0.f.f5064Y);
        if (viewA08 != null) {
            viewA08.setOnClickListener(new k());
        }
        View viewA09 = a0(f0.f.V3);
        if (viewA09 != null) {
            viewA09.setOnClickListener(new a());
        }
        View viewA010 = a0(f0.f.q1);
        if (viewA010 != null) {
            viewA010.setOnClickListener(new b());
        }
    }

    @Override // f0.a, c.AbstractActivityC0167u, android.app.Activity, c.AbstractC0121a.b
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        if (i2 != 6201) {
            return;
        }
        String str = (strArr == null || strArr.length <= 0) ? "" : strArr[0];
        boolean z2 = iArr != null && iArr.length > 0 && iArr[0] == 0;
        if (!this.f4112w.isEmpty()) {
            this.f4112w.remove(0);
        }
        if (z2) {
            Toast.makeText(this, i0(str) + "已授权", 0).show();
        } else if (Build.VERSION.SDK_INT < 23 || str == null || str.length() <= 0 || AbstractC0121a.j(this, str)) {
            Toast.makeText(this, i0(str) + "未授权", 0).show();
        } else {
            Toast.makeText(this, i0(str) + "被永久拒绝，请到系统设置手动开启", 0).show();
        }
        k0();
    }
}

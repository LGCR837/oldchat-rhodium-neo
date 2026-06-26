package com.im.oldchat.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import l0.C0;
import o.c;
import o0.AbstractC0441g;

/* JADX INFO: loaded from: classes.dex */
public abstract class d0 extends C0 {

    public class a implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4481a;

        public a(String str) {
            this.f4481a = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            try {
                d0.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f4481a)));
            } catch (Exception unused) {
                Toast.makeText(d0.this, "无法打开浏览器", 0).show();
            }
        }
    }

    public class b implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4483a;

        public b(String str) {
            this.f4483a = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            AbstractC0441g.a(d0.this, this.f4483a);
        }
    }

    public void B0(String str) {
        String strK0 = k0(str);
        new c.a(this).t("下载资源").i(strK0).q("复制链接", new b(strK0)).k("浏览器下载", new a(strK0)).m("取消", null).v();
    }

    public void f(j0.t tVar) {
        String str;
        if (tVar == null || (str = tVar.f5621d) == null || str.isEmpty()) {
            return;
        }
        B0(tVar.f5621d);
    }
}

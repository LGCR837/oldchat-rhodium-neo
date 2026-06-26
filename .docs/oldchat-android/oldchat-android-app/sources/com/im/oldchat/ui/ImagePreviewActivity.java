package com.im.oldchat.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import o0.AbstractC0455v;

/* JADX INFO: loaded from: classes.dex */
public class ImagePreviewActivity extends f0.a {

    public class a implements AbstractC0455v.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f2997a;

        public a(View view) {
            this.f2997a = view;
        }

        @Override // o0.AbstractC0455v.c
        public void a(String str) {
            View view = this.f2997a;
            if (view != null) {
                view.setVisibility(8);
            }
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ImagePreviewActivity.this.finish();
        }
    }

    public static void f0(Context context, String str) {
        if (context == null || str == null || str.isEmpty()) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) ImagePreviewActivity.class);
        intent.putExtra("url", str);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        context.startActivity(intent);
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5170w);
        ImageView imageView = (ImageView) a0(f0.f.W5);
        ImageView imageView2 = (ImageView) a0(f0.f.M2);
        View viewA0 = a0(f0.f.z7);
        String stringExtra = getIntent().getStringExtra("url");
        if (viewA0 != null) {
            viewA0.setVisibility(0);
        }
        AbstractC0455v.j(imageView, stringExtra, new a(viewA0));
        if (imageView2 != null) {
            imageView2.setOnClickListener(new b());
        }
    }
}

package com.im.oldchat.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import k.AbstractC0267e0;
import o0.AbstractC0455v;

/* JADX INFO: loaded from: classes.dex */
public class MomentGalleryActivity extends f0.a {

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MomentGalleryActivity.this.finish();
        }
    }

    public class b extends ViewPager.m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ TextView f3097a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ArrayList f3098b;

        public b(TextView textView, ArrayList arrayList) {
            this.f3097a = textView;
            this.f3098b = arrayList;
        }

        @Override // android.support.v4.view.ViewPager.j
        public void c(int i2) {
            MomentGalleryActivity.this.h0(this.f3097a, i2, this.f3098b.size());
        }
    }

    public static class c extends AbstractC0267e0 {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final List f3100c;

        public class a implements AbstractC0455v.c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ ProgressBar f3101a;

            public a(ProgressBar progressBar) {
                this.f3101a = progressBar;
            }

            @Override // o0.AbstractC0455v.c
            public void a(String str) {
                this.f3101a.setVisibility(8);
            }
        }

        public c(List list) {
            this.f3100c = list;
        }

        @Override // k.AbstractC0267e0
        public void a(ViewGroup viewGroup, int i2, Object obj) {
            if (obj instanceof View) {
                viewGroup.removeView((View) obj);
            }
        }

        @Override // k.AbstractC0267e0
        public int d() {
            List list = this.f3100c;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        @Override // k.AbstractC0267e0
        public Object h(ViewGroup viewGroup, int i2) {
            FrameLayout frameLayout = new FrameLayout(viewGroup.getContext());
            frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            ZoomImageView zoomImageView = new ZoomImageView(viewGroup.getContext());
            zoomImageView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            ProgressBar progressBar = new ProgressBar(viewGroup.getContext());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            progressBar.setLayoutParams(layoutParams);
            progressBar.setIndeterminate(true);
            frameLayout.addView(zoomImageView);
            frameLayout.addView(progressBar);
            progressBar.setVisibility(0);
            AbstractC0455v.j(zoomImageView, (String) this.f3100c.get(i2), new a(progressBar));
            viewGroup.addView(frameLayout);
            return frameLayout;
        }

        @Override // k.AbstractC0267e0
        public boolean i(View view, Object obj) {
            return view == obj;
        }
    }

    public static void g0(Context context, ArrayList arrayList, int i2) {
        if (context == null || arrayList == null || arrayList.isEmpty()) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) MomentGalleryActivity.class);
        intent.putStringArrayListExtra("urls", arrayList);
        intent.putExtra("index", i2);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        context.startActivity(intent);
    }

    public final void h0(TextView textView, int i2, int i3) {
        if (textView == null) {
            return;
        }
        textView.setText((i2 + 1) + "/" + i3);
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5108B);
        ArrayList<String> stringArrayListExtra = getIntent().getStringArrayListExtra("urls");
        if (stringArrayListExtra == null || stringArrayListExtra.isEmpty()) {
            finish();
            return;
        }
        int intExtra = getIntent().getIntExtra("index", 0);
        if (intExtra < 0 || intExtra >= stringArrayListExtra.size()) {
            intExtra = 0;
        }
        TextView textView = (TextView) a0(f0.f.da);
        ImageView imageView = (ImageView) a0(f0.f.S0);
        if (imageView != null) {
            imageView.setOnClickListener(new a());
        }
        ViewPager viewPager = (ViewPager) a0(f0.f.Ae);
        viewPager.setAdapter(new c(stringArrayListExtra));
        viewPager.L(intExtra, false);
        h0(textView, intExtra, stringArrayListExtra.size());
        viewPager.c(new b(textView, stringArrayListExtra));
    }
}

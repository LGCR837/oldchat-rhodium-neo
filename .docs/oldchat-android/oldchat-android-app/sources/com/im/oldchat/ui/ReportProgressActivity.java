package com.im.oldchat.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import c.AbstractC0112D;
import c.AbstractC0175y;
import c.AbstractComponentCallbacksC0165t;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ReportProgressActivity extends f0.a {

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ReportProgressActivity.this.finish();
        }
    }

    public static class b extends AbstractC0112D {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final List f4046f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final List f4047g;

        public b(AbstractC0175y abstractC0175y) {
            super(abstractC0175y);
            this.f4046f = new ArrayList();
            this.f4047g = new ArrayList();
        }

        @Override // k.AbstractC0267e0
        public int d() {
            return this.f4046f.size();
        }

        @Override // k.AbstractC0267e0
        public CharSequence f(int i2) {
            return (CharSequence) this.f4047g.get(i2);
        }

        @Override // c.AbstractC0112D
        public AbstractComponentCallbacksC0165t s(int i2) {
            return (AbstractComponentCallbacksC0165t) this.f4046f.get(i2);
        }

        public void v(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, String str) {
            this.f4046f.add(abstractComponentCallbacksC0165t);
            this.f4047g.add(str);
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5136b0);
        View viewA0 = a0(f0.f.f5102v);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        ViewPager viewPager = (ViewPager) a0(f0.f.xe);
        b bVar = new b(y());
        bVar.v(new m0.a(), "Bug反馈");
        bVar.v(new m0.q(), "用户举报");
        bVar.v(new m0.p(), "资源举报");
        viewPager.setAdapter(bVar);
        viewPager.setOffscreenPageLimit(2);
    }
}

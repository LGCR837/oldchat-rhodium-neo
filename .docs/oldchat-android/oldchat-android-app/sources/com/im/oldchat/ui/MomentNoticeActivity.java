package com.im.oldchat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MomentNoticeActivity extends f0.a {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ListView f3103w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public TextView f3104x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public l0.V f3105y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final List f3106z = new ArrayList();

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MomentNoticeActivity.this.finish();
        }
    }

    public class b implements AdapterView.OnItemClickListener {
        public b() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            j0.p pVar;
            String str;
            if (i2 < 0 || i2 >= MomentNoticeActivity.this.f3106z.size() || (pVar = (j0.p) MomentNoticeActivity.this.f3106z.get(i2)) == null || (str = pVar.f5588b) == null || str.isEmpty()) {
                return;
            }
            if ("comment".equals(pVar.f5592f)) {
                Intent intent = new Intent(MomentNoticeActivity.this, (Class<?>) MomentCommentsActivity.class);
                intent.putExtra("moment_id", pVar.f5588b);
                intent.putExtra("moment_owner_uid", pVar.f5591e);
                MomentNoticeActivity.this.startActivity(intent);
                return;
            }
            if ("like".equals(pVar.f5592f)) {
                Intent intent2 = new Intent(MomentNoticeActivity.this, (Class<?>) MomentsActivity.class);
                intent2.putExtra("scroll_moment_id", pVar.f5588b);
                MomentNoticeActivity.this.startActivity(intent2);
            }
        }
    }

    public final void g0() {
        boolean zIsEmpty = this.f3106z.isEmpty();
        TextView textView = this.f3104x;
        if (textView != null) {
            textView.setVisibility(zIsEmpty ? 0 : 8);
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5109C);
        this.f3103w = (ListView) a0(f0.f.R6);
        this.f3104x = (TextView) a0(f0.f.yb);
        View viewA0 = a0(f0.f.j2);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        l0.V v2 = new l0.V(this, this.f3106z);
        this.f3105y = v2;
        this.f3103w.setAdapter((ListAdapter) v2);
        TextView textView = this.f3104x;
        if (textView != null) {
            this.f3103w.setEmptyView(textView);
        }
        this.f3103w.setOnItemClickListener(new b());
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onResume() {
        super.onResume();
        this.f3106z.clear();
        this.f3106z.addAll(i0.c.d(this));
        i0.c.b(this);
        this.f3105y.notifyDataSetChanged();
        g0();
    }
}

package com.im.oldchat.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.C;
import com.im.oldchat.ui.D;
import com.im.oldchat.ui.E;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GroupMembersActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public TextView f2952A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public String f2953B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public String f2954C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public String f2955D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public int f2956E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final C f2957F = new C();

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final List f2958G = new ArrayList();

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final E f2959H = new E();

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public D f2960I;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public SwipeRefreshLayout f2961w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public ListView f2962x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f2963y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public TextView f2964z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            GroupMembersActivity.this.finish();
        }
    }

    public class b implements D.d {
        public b() {
        }

        @Override // com.im.oldchat.ui.D.d
        public void a(j0.h hVar, boolean z2) {
            GroupMembersActivity.this.v0(hVar, z2);
        }

        @Override // com.im.oldchat.ui.D.d
        public void b(j0.h hVar) {
            GroupMembersActivity.this.s0(hVar);
        }
    }

    public class c implements AbsListView.OnScrollListener {
        public c() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            GroupMembersActivity.this.y0();
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
        }
    }

    public class d implements SwipeRefreshLayout.j {
        public d() {
        }

        @Override // android.support.v4.widget.SwipeRefreshLayout.j
        public void a() {
            GroupMembersActivity.this.u0();
        }
    }

    public class e implements C.i {

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ j0.e f2970a;

            public a(j0.e eVar) {
                this.f2970a = eVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                j0.e eVar = this.f2970a;
                if (eVar == null) {
                    return;
                }
                if (!TextUtils.isEmpty(eVar.f5505b)) {
                    GroupMembersActivity.this.f2955D = this.f2970a.f5505b;
                }
                GroupMembersActivity.this.f2956E = this.f2970a.f5509f;
                if (GroupMembersActivity.this.f2960I != null) {
                    GroupMembersActivity.this.f2960I.h(GroupMembersActivity.this.f2956E);
                }
                GroupMembersActivity.this.x0();
            }
        }

        public e() {
        }

        @Override // com.im.oldchat.ui.C.i
        public void a(j0.e eVar) {
            GroupMembersActivity.this.runOnUiThread(new a(eVar));
        }
    }

    public class f implements E.c {
        public f() {
        }

        @Override // com.im.oldchat.ui.E.c
        public void a(List list) {
            if (GroupMembersActivity.this.f2961w != null) {
                GroupMembersActivity.this.f2961w.setRefreshing(false);
            }
            GroupMembersActivity.this.f2958G.clear();
            if (list != null && !list.isEmpty()) {
                GroupMembersActivity.this.f2958G.addAll(list);
            }
            if (GroupMembersActivity.this.f2960I != null) {
                GroupMembersActivity.this.f2960I.g(GroupMembersActivity.this.f2958G);
            }
            GroupMembersActivity.this.x0();
            GroupMembersActivity.this.w0();
            GroupMembersActivity.this.y0();
        }

        @Override // com.im.oldchat.ui.E.c
        public void b(int i2, String str) {
            if (GroupMembersActivity.this.f2961w != null) {
                GroupMembersActivity.this.f2961w.setRefreshing(false);
            }
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(GroupMembersActivity.this, "加载成员失败: " + i2, 0).show();
        }

        @Override // com.im.oldchat.ui.E.c
        public void c() {
            if (GroupMembersActivity.this.f2961w != null) {
                GroupMembersActivity.this.f2961w.setRefreshing(false);
            }
            Toast.makeText(GroupMembersActivity.this, "加载成员失败", 0).show();
        }
    }

    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GroupMembersActivity.this.u0();
        }
    }

    public class h implements Runnable {
        public h() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GroupMembersActivity.this.u0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s0(j0.h hVar) {
        if (hVar == null || TextUtils.isEmpty(this.f2954C) || TextUtils.isEmpty(this.f2953B)) {
            return;
        }
        this.f2957F.a(this, this.f2953B, this.f2954C, hVar, new g());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u0() {
        if (TextUtils.isEmpty(this.f2953B)) {
            Toast.makeText(this, getString(f0.j.f5190N), 0).show();
            return;
        }
        if (TextUtils.isEmpty(this.f2954C)) {
            Toast.makeText(this, "群ID无效", 0).show();
            return;
        }
        SwipeRefreshLayout swipeRefreshLayout = this.f2961w;
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
            this.f2961w.setEnabled(true);
        }
        this.f2959H.f(this.f2953B, this.f2954C, new f());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v0(j0.h hVar, boolean z2) {
        if (hVar == null || TextUtils.isEmpty(this.f2954C) || TextUtils.isEmpty(this.f2953B)) {
            return;
        }
        this.f2957F.e(this, this.f2953B, this.f2954C, hVar, z2, new h());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w0() {
        boolean zIsEmpty = this.f2958G.isEmpty();
        TextView textView = this.f2952A;
        if (textView != null) {
            textView.setVisibility(zIsEmpty ? 0 : 8);
        }
        ListView listView = this.f2962x;
        if (listView != null) {
            listView.setVisibility(zIsEmpty ? 8 : 0);
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5168u);
        View viewA0 = a0(f0.f.f5102v);
        this.f2961w = (SwipeRefreshLayout) a0(f0.f.r8);
        this.f2962x = (ListView) a0(f0.f.O6);
        this.f2963y = (TextView) a0(f0.f.na);
        this.f2964z = (TextView) a0(f0.f.wa);
        this.f2952A = (TextView) a0(f0.f.K9);
        this.f2953B = getSharedPreferences("auth", 0).getString("access_token", "");
        this.f2954C = getIntent().getStringExtra("group_id");
        this.f2955D = getIntent().getStringExtra("group_name");
        this.f2956E = getIntent().getIntExtra("group_role", 0);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        D d2 = new D(this, this.f2958G, this.f2956E, new b());
        this.f2960I = d2;
        ListView listView = this.f2962x;
        if (listView != null) {
            listView.setAdapter((ListAdapter) d2);
            this.f2962x.setFastScrollEnabled(true);
            this.f2962x.setScrollingCacheEnabled(false);
            this.f2962x.setAnimationCacheEnabled(false);
            this.f2962x.setOnScrollListener(new c());
        }
        SwipeRefreshLayout swipeRefreshLayout = this.f2961w;
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setOnRefreshListener(new d());
        }
        x0();
        y0();
        t0();
        u0();
    }

    @Override // o.d, c.AbstractActivityC0167u, android.app.Activity
    public void onDestroy() {
        this.f2959H.d();
        super.onDestroy();
    }

    public final boolean r0() {
        ListView listView = this.f2962x;
        if (listView == null || listView.getChildCount() == 0) {
            return true;
        }
        if (this.f2962x.getFirstVisiblePosition() > 0) {
            return false;
        }
        View childAt = this.f2962x.getChildAt(0);
        return childAt == null || childAt.getTop() >= this.f2962x.getListPaddingTop();
    }

    public final void t0() {
        if (TextUtils.isEmpty(this.f2953B) || TextUtils.isEmpty(this.f2954C)) {
            return;
        }
        this.f2957F.b(this, this.f2953B, this.f2954C, new e());
    }

    public final void x0() {
        String str;
        if (this.f2963y != null) {
            if (TextUtils.isEmpty(this.f2955D)) {
                if (TextUtils.isEmpty(this.f2954C)) {
                    str = "群信息";
                } else {
                    str = "群号：" + this.f2954C;
                }
            } else if (TextUtils.isEmpty(this.f2954C)) {
                str = this.f2955D;
            } else {
                str = this.f2955D + "（" + this.f2954C + "）";
            }
            this.f2963y.setText(str);
        }
        TextView textView = this.f2964z;
        if (textView != null) {
            textView.setText("成员 " + this.f2958G.size() + " 人");
        }
    }

    public final void y0() {
        SwipeRefreshLayout swipeRefreshLayout = this.f2961w;
        if (swipeRefreshLayout == null) {
            return;
        }
        this.f2961w.setEnabled(swipeRefreshLayout.i() || r0());
    }
}

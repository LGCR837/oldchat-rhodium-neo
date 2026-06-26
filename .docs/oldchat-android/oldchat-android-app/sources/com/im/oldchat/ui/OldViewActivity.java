package com.im.oldchat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.bili.BiliModels;
import com.im.oldchat.ui.Z;
import l0.AbstractActivityC0375d0;

/* JADX INFO: loaded from: classes.dex */
public class OldViewActivity extends AbstractActivityC0375d0 {

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OldViewActivity.this.startActivity(new Intent(OldViewActivity.this, (Class<?>) OldViewFavoritesActivity.class));
        }
    }

    public class b implements Z.b {
        public b() {
        }

        @Override // com.im.oldchat.ui.Z.b
        public void a(BiliModels.RecommendItem recommendItem) {
            OldViewActivity.this.Z0(recommendItem);
        }
    }

    public class c implements AdapterView.OnItemClickListener {
        public c() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            Z z2 = OldViewActivity.this.f6037G;
            Object item = z2 != null ? z2.getItem(i2) : null;
            if (item instanceof BiliModels.RecommendItem) {
                OldViewActivity.this.w0((BiliModels.RecommendItem) item);
            }
        }
    }

    public class d implements AbsListView.OnScrollListener {
        public d() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            OldViewActivity oldViewActivity = OldViewActivity.this;
            if (!oldViewActivity.f6044N || !oldViewActivity.f6048R || oldViewActivity.f6047Q || i4 <= 0 || i2 + i3 < i4 - 2) {
                return;
            }
            oldViewActivity.Q0(oldViewActivity.f6046P + 1, false);
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OldViewActivity.this.finish();
        }
    }

    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OldViewActivity.this.K0(view);
        }
    }

    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OldViewActivity.this.S0();
        }
    }

    public class h implements View.OnClickListener {
        public h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OldViewActivity.this.L0(true);
        }
    }

    public class i implements View.OnClickListener {
        public i() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OldViewActivity.this.S0();
        }
    }

    public class j implements View.OnClickListener {
        public j() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OldViewActivity.this.startActivity(new Intent(OldViewActivity.this, (Class<?>) OldViewHistoryActivity.class));
        }
    }

    public void V0() {
        this.f6051w = (ImageView) a0(f0.f.T5);
        this.f6052x = (TextView) a0(f0.f.dc);
        this.f6053y = (TextView) a0(f0.f.ac);
        this.f6054z = a0(f0.f.n7);
        this.f6031A = a0(f0.f.t2);
        this.f6032B = a0(f0.f.A2);
        this.f6033C = (Button) a0(f0.f.y2);
        this.f6034D = (ProgressBar) a0(f0.f.w7);
        this.f6035E = (ListView) a0(f0.f.a7);
        TextView textView = (TextView) a0(f0.f.Vb);
        this.f6036F = textView;
        ListView listView = this.f6035E;
        if (listView == null || textView == null) {
            return;
        }
        listView.setEmptyView(textView);
    }

    public final long W0(BiliModels.RecommendItem recommendItem) {
        BiliModels.RecommendArgs recommendArgs;
        if (recommendItem != null && (recommendArgs = recommendItem.args) != null) {
            long j2 = recommendArgs.upId;
            if (j2 > 0) {
                return j2;
            }
            long j3 = recommendArgs.mid;
            if (j3 > 0) {
                return j3;
            }
        }
        return 0L;
    }

    public void X0() {
        View viewA0 = a0(f0.f.m2);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new e());
        }
        View view = this.f6031A;
        if (view != null) {
            view.setOnClickListener(new f());
        }
        View view2 = this.f6032B;
        if (view2 instanceof ImageView) {
            ((ImageView) view2).setColorFilter(getResources().getColor(f0.d.f4973j));
        }
        View view3 = this.f6032B;
        if (view3 != null) {
            view3.setOnClickListener(new g());
        }
        Button button = this.f6033C;
        if (button != null) {
            button.setOnClickListener(new h());
        }
        View viewA02 = a0(f0.f.x2);
        if (viewA02 != null) {
            viewA02.setOnClickListener(new i());
        }
        View viewA03 = a0(f0.f.w2);
        if (viewA03 != null) {
            viewA03.setOnClickListener(new j());
        }
        View viewA04 = a0(f0.f.v2);
        if (viewA04 != null) {
            viewA04.setOnClickListener(new a());
        }
    }

    public void Y0() {
        Z z2 = new Z(this);
        this.f6037G = z2;
        z2.f(new b());
        ListView listView = this.f6035E;
        if (listView == null) {
            return;
        }
        listView.setAdapter((ListAdapter) this.f6037G);
        this.f6035E.setOnItemClickListener(new c());
        this.f6035E.setOnScrollListener(new d());
    }

    public final void Z0(BiliModels.RecommendItem recommendItem) {
        BiliModels.RecommendArgs recommendArgs;
        long jW0 = W0(recommendItem);
        if (jW0 <= 0) {
            Toast.makeText(this, getString(f0.j.q2), 0).show();
            return;
        }
        Intent intent = new Intent(this, (Class<?>) OldViewUpProfileActivity.class);
        intent.putExtra("up_mid", jW0);
        if (recommendItem != null && (recommendArgs = recommendItem.args) != null) {
            intent.putExtra("up_name", recommendArgs.upName);
        }
        startActivity(intent);
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5117K);
        V0();
        Y0();
        X0();
    }

    @Override // o.d, c.AbstractActivityC0167u, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        N0();
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onResume() {
        super.onResume();
        h0("onResume: ensureLoginState");
        D0();
    }
}

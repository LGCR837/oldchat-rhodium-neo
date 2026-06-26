package com.im.oldchat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.bili.BiliModels;
import com.im.oldchat.bili.a;
import java.util.ArrayList;
import java.util.List;
import l0.C0385i0;
import l0.C0387j0;
import org.spongycastle.i18n.MessageBundle;

/* JADX INFO: loaded from: classes.dex */
public class OldViewFavoritesActivity extends f0.a {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ProgressBar f3608w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public ListView f3609x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f3610y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public C0385i0 f3611z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OldViewFavoritesActivity.this.finish();
        }
    }

    public class b implements AdapterView.OnItemClickListener {
        public b() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            Object item = OldViewFavoritesActivity.this.f3611z != null ? OldViewFavoritesActivity.this.f3611z.getItem(i2) : null;
            if (item instanceof C0387j0) {
                OldViewFavoritesActivity.this.n0((C0387j0) item);
            }
        }
    }

    public class c implements a.h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f3614a;

        public c(String str) {
            this.f3614a = str;
        }

        @Override // com.im.oldchat.bili.a.h
        public void a(String str) {
            OldViewFavoritesActivity.this.p0(false);
            if (str == null || str.length() <= 0) {
                return;
            }
            Toast.makeText(OldViewFavoritesActivity.this, str, 0).show();
        }

        @Override // com.im.oldchat.bili.a.h
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(BiliModels.NavResult navResult) {
            String str;
            BiliModels.NavData navData;
            if (navResult != null && navResult.code == 0 && (navData = navResult.data) != null) {
                long j2 = navData.mid;
                h0.a.h(OldViewFavoritesActivity.this, j2);
                OldViewFavoritesActivity.this.o0(j2, this.f3614a);
            } else {
                OldViewFavoritesActivity.this.p0(false);
                OldViewFavoritesActivity oldViewFavoritesActivity = OldViewFavoritesActivity.this;
                if (navResult == null || (str = navResult.message) == null) {
                    str = "获取用户信息失败";
                }
                Toast.makeText(oldViewFavoritesActivity, str, 0).show();
            }
        }
    }

    public class d implements a.h {
        public d() {
        }

        @Override // com.im.oldchat.bili.a.h
        public void a(String str) {
            OldViewFavoritesActivity.this.p0(false);
            if (str == null || str.length() <= 0) {
                return;
            }
            Toast.makeText(OldViewFavoritesActivity.this, str, 0).show();
        }

        @Override // com.im.oldchat.bili.a.h
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(BiliModels.FavFolderResult favFolderResult) {
            BiliModels.FavFolderData favFolderData;
            OldViewFavoritesActivity.this.p0(false);
            if (favFolderResult != null && favFolderResult.code == 0 && (favFolderData = favFolderResult.data) != null) {
                List listM0 = OldViewFavoritesActivity.this.m0(favFolderData.list);
                if (OldViewFavoritesActivity.this.f3611z != null) {
                    OldViewFavoritesActivity.this.f3611z.b(listM0);
                    return;
                }
                return;
            }
            if (favFolderResult != null) {
                OldViewFavoritesActivity oldViewFavoritesActivity = OldViewFavoritesActivity.this;
                String str = favFolderResult.message;
                if (str == null) {
                    str = "获取收藏夹失败";
                }
                Toast.makeText(oldViewFavoritesActivity, str, 0).show();
            }
        }
    }

    private void l0() {
        String strC = h0.a.c(this);
        if (strC == null || strC.length() == 0) {
            TextView textView = this.f3610y;
            if (textView != null) {
                textView.setText(f0.j.X1);
                return;
            }
            return;
        }
        TextView textView2 = this.f3610y;
        if (textView2 != null) {
            textView2.setText(f0.j.R1);
        }
        long jD = h0.a.d(this);
        if (jD <= 0) {
            k0(strC);
        } else {
            o0(jD, strC);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p0(boolean z2) {
        ProgressBar progressBar = this.f3608w;
        if (progressBar != null) {
            progressBar.setVisibility(z2 ? 0 : 8);
        }
    }

    public final void k0(String str) {
        p0(true);
        com.im.oldchat.bili.d.x(str, new c(str));
    }

    public final List m0(List list) {
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            return arrayList;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            BiliModels.FavFolder favFolder = (BiliModels.FavFolder) list.get(i2);
            if (favFolder != null) {
                C0387j0 c0387j0 = new C0387j0();
                c0387j0.f6101a = favFolder.title;
                c0387j0.f6103c = "共 " + favFolder.mediaCount + " 个视频";
                c0387j0.f6105e = favFolder.id;
                arrayList.add(c0387j0);
            }
        }
        return arrayList;
    }

    public final void n0(C0387j0 c0387j0) {
        if (c0387j0 == null || c0387j0.f6105e <= 0) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) OldViewFavoriteDetailActivity.class);
        intent.putExtra("media_id", c0387j0.f6105e);
        String str = c0387j0.f6101a;
        if (str == null) {
            str = "";
        }
        intent.putExtra(MessageBundle.TITLE_ENTRY, str);
        startActivity(intent);
    }

    public final void o0(long j2, String str) {
        p0(true);
        com.im.oldchat.bili.d.u(j2, str, new d());
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5120N);
        TextView textView = (TextView) a0(f0.f.Zb);
        if (textView != null) {
            textView.setText(f0.j.S1);
        }
        View viewA0 = a0(f0.f.s2);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        this.f3608w = (ProgressBar) a0(f0.f.v7);
        this.f3609x = (ListView) a0(f0.f.Y6);
        TextView textView2 = (TextView) a0(f0.f.Yb);
        this.f3610y = textView2;
        if (textView2 != null) {
            textView2.setText(f0.j.X1);
        }
        ListView listView = this.f3609x;
        if (listView != null) {
            listView.setEmptyView(this.f3610y);
        }
        C0385i0 c0385i0 = new C0385i0(this);
        this.f3611z = c0385i0;
        ListView listView2 = this.f3609x;
        if (listView2 != null) {
            listView2.setAdapter((ListAdapter) c0385i0);
            this.f3609x.setOnItemClickListener(new b());
        }
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onResume() {
        super.onResume();
        C0385i0 c0385i0 = this.f3611z;
        if (c0385i0 == null || c0385i0.getCount() == 0) {
            l0();
        }
    }
}

package com.im.oldchat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
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
import java.util.Locale;
import l0.C0385i0;
import l0.C0387j0;
import org.spongycastle.i18n.MessageBundle;

/* JADX INFO: loaded from: classes.dex */
public class OldViewFavoriteDetailActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public long f3595A = 0;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public int f3596B = 1;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public boolean f3597C = false;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public boolean f3598D = true;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ProgressBar f3599w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public ListView f3600x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f3601y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public C0385i0 f3602z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OldViewFavoriteDetailActivity.this.finish();
        }
    }

    public class b implements AdapterView.OnItemClickListener {
        public b() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            Object item = OldViewFavoriteDetailActivity.this.f3602z != null ? OldViewFavoriteDetailActivity.this.f3602z.getItem(i2) : null;
            if (item instanceof C0387j0) {
                OldViewFavoriteDetailActivity.this.s0((C0387j0) item);
            }
        }
    }

    public class c implements AbsListView.OnScrollListener {
        public c() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            if (!OldViewFavoriteDetailActivity.this.f3598D || OldViewFavoriteDetailActivity.this.f3597C || i4 <= 0 || i2 + i3 < i4 - 2) {
                return;
            }
            OldViewFavoriteDetailActivity.this.q0(false);
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
        }
    }

    public class d implements a.h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f3606a;

        public d(boolean z2) {
            this.f3606a = z2;
        }

        @Override // com.im.oldchat.bili.a.h
        public void a(String str) {
            OldViewFavoriteDetailActivity.this.f3597C = false;
            OldViewFavoriteDetailActivity.this.t0(false);
            if (str == null || str.length() <= 0) {
                return;
            }
            Toast.makeText(OldViewFavoriteDetailActivity.this, str, 0).show();
        }

        @Override // com.im.oldchat.bili.a.h
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(BiliModels.FavResourceResult favResourceResult) {
            BiliModels.FavResourceData favResourceData;
            OldViewFavoriteDetailActivity.this.f3597C = false;
            OldViewFavoriteDetailActivity.this.t0(false);
            if (favResourceResult == null || favResourceResult.code != 0 || (favResourceData = favResourceResult.data) == null) {
                if (favResourceResult != null) {
                    OldViewFavoriteDetailActivity oldViewFavoriteDetailActivity = OldViewFavoriteDetailActivity.this;
                    String str = favResourceResult.message;
                    if (str == null) {
                        str = "加载失败";
                    }
                    Toast.makeText(oldViewFavoriteDetailActivity, str, 0).show();
                    OldViewFavoriteDetailActivity.this.f3598D = false;
                    return;
                }
                return;
            }
            List listR0 = OldViewFavoriteDetailActivity.this.r0(favResourceData.medias);
            if (OldViewFavoriteDetailActivity.this.f3602z != null) {
                if (this.f3606a) {
                    OldViewFavoriteDetailActivity.this.f3602z.b(listR0);
                } else {
                    OldViewFavoriteDetailActivity.this.f3602z.a(listR0);
                }
            }
            OldViewFavoriteDetailActivity.this.f3598D = favResourceResult.data.hasMore;
            if (OldViewFavoriteDetailActivity.this.f3598D) {
                OldViewFavoriteDetailActivity.o0(OldViewFavoriteDetailActivity.this);
            }
        }
    }

    public static /* synthetic */ int o0(OldViewFavoriteDetailActivity oldViewFavoriteDetailActivity) {
        int i2 = oldViewFavoriteDetailActivity.f3596B;
        oldViewFavoriteDetailActivity.f3596B = i2 + 1;
        return i2;
    }

    private String p0(int i2) {
        return i2 <= 0 ? "" : String.format(Locale.US, "%02d:%02d", Integer.valueOf(i2 / 60), Integer.valueOf(i2 % 60));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t0(boolean z2) {
        ProgressBar progressBar = this.f3599w;
        if (progressBar != null) {
            progressBar.setVisibility(z2 ? 0 : 8);
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5120N);
        Intent intent = getIntent();
        if (intent != null) {
            this.f3595A = intent.getLongExtra("media_id", 0L);
        }
        String stringExtra = intent != null ? intent.getStringExtra(MessageBundle.TITLE_ENTRY) : null;
        TextView textView = (TextView) a0(f0.f.Zb);
        if (textView != null) {
            if (stringExtra == null || stringExtra.length() <= 0) {
                stringExtra = getString(f0.j.P1);
            }
            textView.setText(stringExtra);
        }
        View viewA0 = a0(f0.f.s2);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        this.f3599w = (ProgressBar) a0(f0.f.v7);
        this.f3600x = (ListView) a0(f0.f.Y6);
        TextView textView2 = (TextView) a0(f0.f.Yb);
        this.f3601y = textView2;
        ListView listView = this.f3600x;
        if (listView != null) {
            listView.setEmptyView(textView2);
        }
        C0385i0 c0385i0 = new C0385i0(this);
        this.f3602z = c0385i0;
        ListView listView2 = this.f3600x;
        if (listView2 != null) {
            listView2.setAdapter((ListAdapter) c0385i0);
            this.f3600x.setOnItemClickListener(new b());
            this.f3600x.setOnScrollListener(new c());
        }
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onResume() {
        super.onResume();
        C0385i0 c0385i0 = this.f3602z;
        if (c0385i0 == null || c0385i0.getCount() == 0) {
            this.f3596B = 1;
            this.f3598D = true;
            q0(true);
        }
    }

    public final void q0(boolean z2) {
        if (this.f3595A <= 0 || this.f3597C || !this.f3598D) {
            return;
        }
        String strC = h0.a.c(this);
        if (strC == null || strC.length() == 0) {
            TextView textView = this.f3601y;
            if (textView != null) {
                textView.setText(f0.j.X1);
                return;
            }
            return;
        }
        TextView textView2 = this.f3601y;
        if (textView2 != null) {
            textView2.setText(f0.j.Q1);
        }
        this.f3597C = true;
        t0(true);
        com.im.oldchat.bili.d.v(this.f3595A, this.f3596B, 20, strC, new d(z2));
    }

    public final List r0(List list) {
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            return arrayList;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            BiliModels.FavMedia favMedia = (BiliModels.FavMedia) list.get(i2);
            if (favMedia != null) {
                C0387j0 c0387j0 = new C0387j0();
                c0387j0.f6101a = favMedia.title;
                c0387j0.f6102b = favMedia.cover;
                BiliModels.FavUpper favUpper = favMedia.upper;
                String str = favUpper != null ? favUpper.name : "";
                String strP0 = p0(favMedia.duration);
                StringBuilder sb = new StringBuilder();
                if (str != null && str.length() > 0) {
                    sb.append(str);
                }
                if (strP0.length() > 0) {
                    if (sb.length() > 0) {
                        sb.append(" · ");
                    }
                    sb.append("时长 ");
                    sb.append(strP0);
                }
                c0387j0.f6103c = sb.toString();
                String str2 = favMedia.bvid;
                if (str2 == null) {
                    str2 = favMedia.bvId;
                }
                c0387j0.f6104d = str2;
                arrayList.add(c0387j0);
            }
        }
        return arrayList;
    }

    public final void s0(C0387j0 c0387j0) {
        if (c0387j0 == null) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) OldViewVideoDetailActivity.class);
        String str = c0387j0.f6104d;
        if (str != null && str.length() > 0) {
            intent.putExtra("bvid", c0387j0.f6104d);
        }
        String str2 = c0387j0.f6102b;
        if (str2 != null && str2.length() > 0) {
            intent.putExtra("cover_url", c0387j0.f6102b);
        }
        String str3 = c0387j0.f6101a;
        if (str3 != null) {
            intent.putExtra(MessageBundle.TITLE_ENTRY, str3);
        }
        startActivity(intent);
    }
}

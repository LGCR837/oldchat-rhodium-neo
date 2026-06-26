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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import l0.C0385i0;
import l0.C0387j0;
import org.spongycastle.i18n.MessageBundle;

/* JADX INFO: loaded from: classes.dex */
public class OldViewHistoryActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f3617A = false;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public boolean f3618B = true;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public long f3619C = 0;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public long f3620D = 0;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final SimpleDateFormat f3621E = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ProgressBar f3622w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public ListView f3623x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f3624y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public C0385i0 f3625z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OldViewHistoryActivity.this.finish();
        }
    }

    public class b implements AdapterView.OnItemClickListener {
        public b() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            Object item = OldViewHistoryActivity.this.f3625z != null ? OldViewHistoryActivity.this.f3625z.getItem(i2) : null;
            if (item instanceof C0387j0) {
                OldViewHistoryActivity.this.u0((C0387j0) item);
            }
        }
    }

    public class c implements AbsListView.OnScrollListener {
        public c() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            if (!OldViewHistoryActivity.this.f3618B || OldViewHistoryActivity.this.f3617A || i4 <= 0 || i2 + i3 < i4 - 2) {
                return;
            }
            OldViewHistoryActivity.this.s0(false);
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
        }
    }

    public class d implements a.h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f3629a;

        public d(boolean z2) {
            this.f3629a = z2;
        }

        @Override // com.im.oldchat.bili.a.h
        public void a(String str) {
            OldViewHistoryActivity.this.f3617A = false;
            OldViewHistoryActivity.this.w0(false);
            if (str == null || str.length() <= 0) {
                return;
            }
            Toast.makeText(OldViewHistoryActivity.this, str, 0).show();
        }

        @Override // com.im.oldchat.bili.a.h
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(BiliModels.HistoryResult historyResult) {
            BiliModels.HistoryData historyData;
            boolean z2 = false;
            OldViewHistoryActivity.this.f3617A = false;
            OldViewHistoryActivity.this.w0(false);
            if (historyResult == null || historyResult.code != 0 || (historyData = historyResult.data) == null) {
                if (historyResult != null) {
                    OldViewHistoryActivity oldViewHistoryActivity = OldViewHistoryActivity.this;
                    String str = historyResult.message;
                    if (str == null) {
                        str = "获取历史失败";
                    }
                    Toast.makeText(oldViewHistoryActivity, str, 0).show();
                    OldViewHistoryActivity.this.f3618B = false;
                    return;
                }
                return;
            }
            List listT0 = OldViewHistoryActivity.this.t0(historyData.list);
            if (OldViewHistoryActivity.this.f3625z != null) {
                if (this.f3629a) {
                    OldViewHistoryActivity.this.f3625z.b(listT0);
                } else {
                    OldViewHistoryActivity.this.f3625z.a(listT0);
                }
            }
            BiliModels.HistoryCursor historyCursor = historyResult.data.cursor;
            if (historyCursor != null) {
                OldViewHistoryActivity.this.f3619C = historyCursor.max;
                OldViewHistoryActivity.this.f3620D = historyResult.data.cursor.viewAt;
            }
            OldViewHistoryActivity oldViewHistoryActivity2 = OldViewHistoryActivity.this;
            if (listT0 != null && !listT0.isEmpty()) {
                z2 = true;
            }
            oldViewHistoryActivity2.f3618B = z2;
        }
    }

    private String r0(long j2) {
        if (j2 <= 0) {
            return "";
        }
        try {
            return this.f3621E.format(new Date(j2 * 1000));
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u0(C0387j0 c0387j0) {
        if (c0387j0 == null) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) OldViewVideoDetailActivity.class);
        String str = c0387j0.f6104d;
        if (str != null && str.length() > 0) {
            intent.putExtra("bvid", c0387j0.f6104d);
        }
        long j2 = c0387j0.f6105e;
        if (j2 > 0) {
            intent.putExtra("aid", j2);
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

    /* JADX INFO: Access modifiers changed from: private */
    public void w0(boolean z2) {
        ProgressBar progressBar = this.f3622w;
        if (progressBar != null) {
            progressBar.setVisibility(z2 ? 0 : 8);
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5120N);
        TextView textView = (TextView) a0(f0.f.Zb);
        if (textView != null) {
            textView.setText(f0.j.U1);
        }
        View viewA0 = a0(f0.f.s2);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        this.f3622w = (ProgressBar) a0(f0.f.v7);
        this.f3623x = (ListView) a0(f0.f.Y6);
        TextView textView2 = (TextView) a0(f0.f.Yb);
        this.f3624y = textView2;
        if (textView2 != null) {
            textView2.setText(f0.j.X1);
        }
        ListView listView = this.f3623x;
        if (listView != null) {
            listView.setEmptyView(this.f3624y);
        }
        C0385i0 c0385i0 = new C0385i0(this);
        this.f3625z = c0385i0;
        ListView listView2 = this.f3623x;
        if (listView2 != null) {
            listView2.setAdapter((ListAdapter) c0385i0);
            this.f3623x.setOnItemClickListener(new b());
            this.f3623x.setOnScrollListener(new c());
        }
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onResume() {
        super.onResume();
        C0385i0 c0385i0 = this.f3625z;
        if (c0385i0 == null || c0385i0.getCount() == 0) {
            v0();
        }
    }

    public final String q0(BiliModels.HistoryItem historyItem) {
        int i2;
        StringBuilder sb = new StringBuilder();
        String str = historyItem.authorName;
        if (str != null && str.length() > 0) {
            sb.append(historyItem.authorName);
        }
        String strR0 = r0(historyItem.viewAt);
        if (strR0.length() > 0) {
            if (sb.length() > 0) {
                sb.append(" · ");
            }
            sb.append(strR0);
        }
        int i3 = historyItem.progress;
        if (i3 > 0 && (i2 = historyItem.duration) > 0) {
            int iMin = Math.min(100, Math.round((i3 * 100.0f) / i2));
            if (sb.length() > 0) {
                sb.append(" · ");
            }
            sb.append("进度 ");
            sb.append(iMin);
            sb.append("%");
        }
        return sb.toString();
    }

    public final void s0(boolean z2) {
        if (this.f3617A || !this.f3618B) {
            return;
        }
        String strC = h0.a.c(this);
        if (strC == null || strC.length() == 0) {
            TextView textView = this.f3624y;
            if (textView != null) {
                textView.setText(f0.j.X1);
                return;
            }
            return;
        }
        TextView textView2 = this.f3624y;
        if (textView2 != null) {
            textView2.setText(f0.j.T1);
        }
        this.f3617A = true;
        w0(true);
        com.im.oldchat.bili.d.w(this.f3619C, this.f3620D, 20, strC, new d(z2));
    }

    public final List t0(List list) {
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            return arrayList;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            BiliModels.HistoryItem historyItem = (BiliModels.HistoryItem) list.get(i2);
            if (historyItem != null) {
                C0387j0 c0387j0 = new C0387j0();
                c0387j0.f6101a = historyItem.title;
                c0387j0.f6102b = historyItem.cover;
                c0387j0.f6103c = q0(historyItem);
                BiliModels.HistoryDetail historyDetail = historyItem.history;
                if (historyDetail != null) {
                    c0387j0.f6105e = historyDetail.oid;
                    c0387j0.f6104d = historyDetail.bvid;
                }
                arrayList.add(c0387j0);
            }
        }
        return arrayList;
    }

    public final void v0() {
        this.f3619C = 0L;
        this.f3620D = 0L;
        this.f3618B = true;
        C0385i0 c0385i0 = this.f3625z;
        if (c0385i0 != null) {
            c0385i0.b(null);
        }
        s0(true);
    }
}

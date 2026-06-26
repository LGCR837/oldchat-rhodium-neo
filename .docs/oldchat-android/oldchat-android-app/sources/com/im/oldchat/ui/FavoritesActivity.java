package com.im.oldchat.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.AbstractC0228t;
import com.im.oldchat.ui.C0229u;
import g0.d;
import java.util.ArrayList;
import java.util.List;
import o.c;
import o0.AbstractC0441g;
import org.json.JSONArray;
import org.json.JSONObject;
import org.spongycastle.i18n.MessageBundle;

/* JADX INFO: loaded from: classes.dex */
public class FavoritesActivity extends f0.a implements C0229u.c {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public String f2828A;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ListView f2829w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public TextView f2830x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public C0229u f2831y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final List f2832z = new ArrayList();

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FavoritesActivity.this.finish();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FavoritesActivity.this.i0();
        }
    }

    public class c implements d.i {
        public c() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("items");
                FavoritesActivity.this.f2832z.clear();
                if (jSONArrayOptJSONArray != null) {
                    for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                        JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i2);
                        if (jSONObjectOptJSONObject != null) {
                            j0.b bVar = new j0.b();
                            bVar.f5496a = jSONObjectOptJSONObject.optString("id", "");
                            bVar.f5497b = jSONObjectOptJSONObject.optString("type", "");
                            bVar.f5498c = jSONObjectOptJSONObject.optString("target_id", "");
                            bVar.f5499d = jSONObjectOptJSONObject.optString(MessageBundle.TITLE_ENTRY, "");
                            bVar.f5500e = jSONObjectOptJSONObject.optString("subtitle", "");
                            bVar.f5501f = jSONObjectOptJSONObject.optString("media_url", "");
                            bVar.f5502g = jSONObjectOptJSONObject.optString("extra", "");
                            bVar.f5503h = jSONObjectOptJSONObject.optLong("created_at", 0L);
                            String str2 = bVar.f5497b;
                            if (str2 != null && str2.length() != 0) {
                                FavoritesActivity.this.f2832z.add(bVar);
                            }
                        }
                    }
                }
                FavoritesActivity.this.f2831y.notifyDataSetChanged();
            } catch (Exception unused) {
                Toast.makeText(FavoritesActivity.this, "加载收藏失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(FavoritesActivity.this, "加载收藏失败: " + i2, 0).show();
        }
    }

    public class d implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2836a;

        public d(String str) {
            this.f2836a = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            AbstractC0441g.a(FavoritesActivity.this, this.f2836a);
        }
    }

    public class e implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.b f2838a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f2839b;

        public class a implements AbstractC0228t.c {
            public a() {
            }

            @Override // com.im.oldchat.ui.AbstractC0228t.c
            public void a(boolean z2) {
                if (!z2) {
                    Toast.makeText(FavoritesActivity.this, "移除失败", 0).show();
                    return;
                }
                e eVar = e.this;
                int i2 = eVar.f2839b;
                if (i2 < 0 || i2 >= FavoritesActivity.this.f2832z.size()) {
                    FavoritesActivity.this.i0();
                } else {
                    FavoritesActivity.this.f2832z.remove(e.this.f2839b);
                    FavoritesActivity.this.f2831y.notifyDataSetChanged();
                }
                Toast.makeText(FavoritesActivity.this, "已移除", 0).show();
            }
        }

        public e(j0.b bVar, int i2) {
            this.f2838a = bVar;
            this.f2839b = i2;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            FavoritesActivity favoritesActivity = FavoritesActivity.this;
            j0.b bVar = this.f2838a;
            AbstractC0228t.h(favoritesActivity, bVar.f5497b, bVar.f5498c, bVar.f5501f, new a());
        }
    }

    public final void i0() {
        String str = this.f2828A;
        if (str == null || str.length() == 0) {
            Toast.makeText(this, "请先登录", 0).show();
        } else {
            g0.d.R("/favorites?limit=100", this.f2828A, new c());
        }
    }

    public final void j0(String str) {
        String strG = o0.A.g(str);
        if (strG.length() == 0) {
            Toast.makeText(this, "链接无效", 0).show();
            return;
        }
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(strG)));
        } catch (Exception unused) {
            new c.a(this).t("打开失败").i(strG).q("复制链接", new d(strG)).k("取消", null).v();
        }
    }

    @Override // com.im.oldchat.ui.C0229u.c
    public void k(j0.b bVar) {
        if (bVar == null) {
            return;
        }
        String str = bVar.f5497b;
        if (str == null) {
            str = "";
        }
        if (!"chat_image".equals(str) && !"emoji_pack".equals(str)) {
            if ("music_song".equals(str)) {
                k0(bVar);
                return;
            } else {
                j0(bVar.f5501f);
                return;
            }
        }
        String strG = o0.A.g(bVar.f5501f);
        if (strG.length() == 0) {
            Toast.makeText(this, "图片地址无效", 0).show();
        } else {
            ImagePreviewActivity.f0(this, strG);
        }
    }

    public final void k0(j0.b bVar) {
        String strOptString;
        String strOptString2;
        if (bVar == null) {
            return;
        }
        String str = bVar.f5501f;
        if (str == null) {
            str = "";
        }
        try {
            String str2 = bVar.f5502g;
            if (str2 == null) {
                str2 = "";
            }
            JSONObject jSONObject = new JSONObject(str2);
            String strOptString3 = jSONObject.optString("song_url", "");
            if (strOptString3 != null && strOptString3.length() > 0) {
                str = strOptString3;
            }
            strOptString = jSONObject.optString("cover_url", "");
            try {
                strOptString2 = jSONObject.optString("lyrics_url", "");
            } catch (Exception unused) {
                strOptString2 = "";
            }
        } catch (Exception unused2) {
            strOptString = "";
        }
        if (str == null || str.length() == 0) {
            Toast.makeText(this, "歌曲地址无效", 0).show();
            return;
        }
        Intent intent = new Intent(this, (Class<?>) MusicPlayerActivity.class);
        String str3 = bVar.f5499d;
        if (str3 == null) {
            str3 = "";
        }
        intent.putExtra("song_name", str3);
        intent.putExtra("song_url", str);
        if (strOptString == null) {
            strOptString = "";
        }
        intent.putExtra("cover_url", strOptString);
        if (strOptString2 == null) {
            strOptString2 = "";
        }
        intent.putExtra("lyrics_url", strOptString2);
        String str4 = bVar.f5500e;
        intent.putExtra("owner_name", str4 != null ? str4 : "");
        startActivity(intent);
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5159n);
        this.f2829w = (ListView) a0(f0.f.M6);
        this.f2830x = (TextView) a0(f0.f.R9);
        TextView textView = (TextView) a0(f0.f.L0);
        View viewA0 = a0(f0.f.K0);
        this.f2828A = getSharedPreferences("auth", 0).getString("access_token", "");
        C0229u c0229u = new C0229u(this, this.f2832z, this);
        this.f2831y = c0229u;
        this.f2829w.setAdapter((ListAdapter) c0229u);
        this.f2829w.setEmptyView(this.f2830x);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        if (textView != null) {
            textView.setOnClickListener(new b());
        }
        i0();
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onResume() {
        super.onResume();
        i0();
    }

    @Override // com.im.oldchat.ui.C0229u.c
    public void r(j0.b bVar, int i2) {
        if (bVar == null) {
            return;
        }
        new c.a(this).t("取消收藏").i("确定从收藏中移除吗？").q("移除", new e(bVar, i2)).k("取消", null).v();
    }
}

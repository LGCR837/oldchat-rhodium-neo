package com.im.oldchat.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import c.AbstractC0121a;
import com.im.oldchat.ui.Y;
import d.AbstractC0235a;
import g0.d;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import k.AbstractC0285k0;
import o.c;
import o0.AbstractC0455v;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class MusicManageActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public TextView f3180A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public TextView f3181B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public TextView f3182C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public TextView f3183D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public ListView f3184E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public String f3185F;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public s f3188I;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public Uri f3193N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public o.c f3194O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public TextView f3195P;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public EditText f3196w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public TextView f3197x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f3198y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public TextView f3199z;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public boolean f3186G = false;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final List f3187H = new ArrayList();

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f3189J = 0;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public int f3190K = 0;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public boolean f3191L = false;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public int f3192M = 0;

    public class a implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ArrayList f3200a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ t f3201b;

        public a(ArrayList arrayList, t tVar) {
            this.f3200a = arrayList;
            this.f3201b = tVar;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            if (i2 < 0 || i2 >= this.f3200a.size()) {
                return;
            }
            String str = (String) this.f3200a.get(i2);
            if ("播放".equals(str)) {
                MusicManageActivity.this.X0(this.f3201b);
                return;
            }
            if ("评论".equals(str)) {
                MusicManageActivity.this.S0(this.f3201b);
                return;
            }
            if ("分享到聊天".equals(str)) {
                MusicManageActivity.this.f1(this.f3201b);
                return;
            }
            if ("点赞".equals(str) || "取消点赞".equals(str)) {
                MusicManageActivity.this.j1(this.f3201b);
                return;
            }
            if (!"收藏".equals(str)) {
                if ("编辑信息".equals(str)) {
                    MusicManageActivity.this.g1(this.f3201b);
                    return;
                } else {
                    if ("删除".equals(str)) {
                        MusicManageActivity.this.L0(this.f3201b);
                        return;
                    }
                    return;
                }
            }
            String str2 = this.f3201b.f3243f;
            if (str2 == null || str2.length() == 0) {
                str2 = this.f3201b.f3242e;
            }
            String str3 = str2;
            MusicManageActivity musicManageActivity = MusicManageActivity.this;
            t tVar = this.f3201b;
            AbstractC0228t.d(musicManageActivity, tVar.f3238a, tVar.f3239b, str3, tVar.f3240c, tVar.f3241d);
        }
    }

    public class b implements DialogInterface.OnClickListener {

        public class a implements d.i {
            public a() {
            }

            @Override // g0.d.i
            public void b(String str) {
                int iOptInt;
                try {
                    iOptInt = new JSONObject(str).optInt("deleted", 0);
                } catch (Exception unused) {
                    iOptInt = 0;
                }
                Toast.makeText(MusicManageActivity.this, "已删除 " + iOptInt + " 首", 0).show();
                MusicManageActivity.this.R0();
            }

            @Override // g0.d.i
            public void c(int i2, String str) {
                if (g0.e.v(i2, str)) {
                    return;
                }
                Toast.makeText(MusicManageActivity.this, "批量删除失败", 0).show();
            }
        }

        public b() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            String str;
            try {
                JSONArray jSONArray = new JSONArray();
                for (int i3 = 0; i3 < MusicManageActivity.this.f3187H.size(); i3++) {
                    t tVar = (t) MusicManageActivity.this.f3187H.get(i3);
                    if (tVar != null && (str = tVar.f3238a) != null && str.length() > 0) {
                        jSONArray.put(tVar.f3238a);
                    }
                }
                if (jSONArray.length() == 0) {
                    Toast.makeText(MusicManageActivity.this, "没有可删除项", 0).show();
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("item_ids", jSONArray);
                g0.d.S("/music/plaza/mine/delete-batch", jSONObject, MusicManageActivity.this.f3185F, new a());
            } catch (Exception unused) {
                Toast.makeText(MusicManageActivity.this, "批量删除失败", 0).show();
            }
        }
    }

    public class c implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ t f3205a;

        public class a implements d.i {
            public a() {
            }

            @Override // g0.d.i
            public void b(String str) {
                Toast.makeText(MusicManageActivity.this, "已删除", 0).show();
                MusicManageActivity.this.R0();
            }

            @Override // g0.d.i
            public void c(int i2, String str) {
                if (g0.e.v(i2, str)) {
                    return;
                }
                Toast.makeText(MusicManageActivity.this, "删除失败", 0).show();
            }
        }

        public c(t tVar) {
            this.f3205a = tVar;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("item_id", this.f3205a.f3238a);
                g0.d.S("/music/plaza/delete", jSONObject, MusicManageActivity.this.f3185F, new a());
            } catch (Exception unused) {
                Toast.makeText(MusicManageActivity.this, "删除失败", 0).show();
            }
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicManageActivity.this.k1();
        }
    }

    public class e implements DialogInterface.OnDismissListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ o.c f3209a;

        public e(o.c cVar) {
            this.f3209a = cVar;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (MusicManageActivity.this.f3194O == this.f3209a) {
                MusicManageActivity.this.f3194O = null;
                MusicManageActivity.this.f3195P = null;
                MusicManageActivity.this.f3193N = null;
            }
        }
    }

    public class f implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f3211a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ o.c f3212b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ t f3213c;

        public f(EditText editText, o.c cVar, t tVar) {
            this.f3211a = editText;
            this.f3212b = cVar;
            this.f3213c = tVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws Throwable {
            String strTrim = this.f3211a.getText() == null ? "" : this.f3211a.getText().toString().trim();
            if (strTrim.length() == 0) {
                Toast.makeText(MusicManageActivity.this, "请填写歌曲名称", 0).show();
                return;
            }
            this.f3212b.dismiss();
            MusicManageActivity musicManageActivity = MusicManageActivity.this;
            musicManageActivity.i1(this.f3213c, strTrim, musicManageActivity.f3193N);
        }
    }

    public class g implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f3215a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ t f3216b;

        public g(String str, t tVar) {
            this.f3215a = str;
            this.f3216b = tVar;
        }

        @Override // g0.d.i
        public void b(String str) {
            String strTrim = this.f3215a;
            String strTrim2 = this.f3216b.f3241d;
            try {
                JSONObject jSONObject = new JSONObject(str);
                String strOptString = jSONObject.optString("name", "");
                if (!TextUtils.isEmpty(strOptString)) {
                    strTrim = strOptString.trim();
                }
                String strOptString2 = jSONObject.optString("cover_url", "");
                if (!TextUtils.isEmpty(strOptString2)) {
                    strTrim2 = strOptString2.trim();
                }
            } catch (Exception unused) {
            }
            this.f3216b.f3239b = strTrim;
            if (!TextUtils.isEmpty(strTrim2)) {
                this.f3216b.f3241d = strTrim2;
            }
            MusicManageActivity.this.f3188I.notifyDataSetChanged();
            Toast.makeText(MusicManageActivity.this, "修改成功", 0).show();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            if (i2 == 400 && str != null && str.contains("invalid_name")) {
                Toast.makeText(MusicManageActivity.this, "歌曲名称不合法", 0).show();
                return;
            }
            if (i2 == 400 && str != null && str.contains("invalid_cover")) {
                Toast.makeText(MusicManageActivity.this, "封面格式不支持", 0).show();
                return;
            }
            if (i2 == 413 && str != null && str.contains("cover_too_large")) {
                Toast.makeText(MusicManageActivity.this, "封面不能超过1MB", 0).show();
                return;
            }
            if (i2 == 404) {
                Toast.makeText(MusicManageActivity.this, "歌曲不存在，列表将刷新", 0).show();
                MusicManageActivity.this.R0();
            } else {
                if (i2 == 403) {
                    Toast.makeText(MusicManageActivity.this, "只能编辑自己发布的歌曲", 0).show();
                    return;
                }
                Toast.makeText(MusicManageActivity.this, "修改失败: " + i2, 0).show();
            }
        }
    }

    public class h implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ t f3218a;

        public h(t tVar) {
            this.f3218a = tVar;
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                t tVar = this.f3218a;
                tVar.f3250m = jSONObject.optInt("play_count", tVar.f3250m + 1);
            } catch (Exception unused) {
                this.f3218a.f3250m++;
            }
            MusicManageActivity.this.f3188I.notifyDataSetChanged();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
        }
    }

    public class i implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ t f3220a;

        public i(t tVar) {
            this.f3220a = tVar;
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                t tVar = this.f3220a;
                tVar.f3251n = jSONObject.optBoolean("liked", tVar.f3251n);
                t tVar2 = this.f3220a;
                tVar2.f3248k = jSONObject.optInt("likes", tVar2.f3248k);
            } catch (Exception unused) {
            }
            MusicManageActivity.this.f3188I.notifyDataSetChanged();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(MusicManageActivity.this, "操作失败", 0).show();
        }
    }

    public class j implements View.OnClickListener {
        public j() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicManageActivity.this.finish();
        }
    }

    public class k implements AdapterView.OnItemClickListener {
        public k() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            if (i2 < 0 || i2 >= MusicManageActivity.this.f3187H.size()) {
                return;
            }
            MusicManageActivity musicManageActivity = MusicManageActivity.this;
            musicManageActivity.X0((t) musicManageActivity.f3187H.get(i2));
        }
    }

    public class l implements AdapterView.OnItemLongClickListener {
        public l() {
        }

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView adapterView, View view, int i2, long j2) {
            if (i2 < 0 || i2 >= MusicManageActivity.this.f3187H.size()) {
                return false;
            }
            MusicManageActivity musicManageActivity = MusicManageActivity.this;
            musicManageActivity.h1((t) musicManageActivity.f3187H.get(i2));
            return true;
        }
    }

    public class m implements View.OnClickListener {
        public m() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicManageActivity.this.f3189J = 0;
            MusicManageActivity.this.R0();
        }
    }

    public class n implements View.OnClickListener {
        public n() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MusicManageActivity.this.f3186G) {
                return;
            }
            MusicManageActivity.this.K0();
        }
    }

    public class o implements TextView.OnEditorActionListener {
        public o() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
            if (i2 == 3) {
                MusicManageActivity.this.f3189J = 0;
                MusicManageActivity.this.R0();
                return true;
            }
            if (keyEvent == null || keyEvent.getAction() != 0 || keyEvent.getKeyCode() != 66) {
                return false;
            }
            MusicManageActivity.this.f3189J = 0;
            MusicManageActivity.this.R0();
            return true;
        }
    }

    public class p implements View.OnClickListener {
        public p() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MusicManageActivity.this.f3189J <= 0) {
                return;
            }
            MusicManageActivity.this.f3189J = Math.max(0, r3.f3189J - 50);
            MusicManageActivity.this.R0();
        }
    }

    public class q implements View.OnClickListener {
        public q() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MusicManageActivity.this.f3191L) {
                MusicManageActivity.C0(MusicManageActivity.this, 50);
                MusicManageActivity.this.R0();
            }
        }
    }

    public class r implements d.i {
        public r() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("items");
                MusicManageActivity.this.f3190K = jSONObject.optInt("total", 0);
                MusicManageActivity.this.f3191L = jSONObject.optBoolean("has_more", false);
                MusicManageActivity.this.f3187H.clear();
                if (jSONArrayOptJSONArray != null) {
                    for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                        JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i2);
                        if (jSONObjectOptJSONObject != null) {
                            t tVar = new t(null);
                            tVar.f3238a = jSONObjectOptJSONObject.optString("id", "");
                            tVar.f3239b = jSONObjectOptJSONObject.optString("name", "");
                            tVar.f3240c = jSONObjectOptJSONObject.optString("song_url", "");
                            tVar.f3241d = jSONObjectOptJSONObject.optString("cover_url", "");
                            tVar.f3242e = jSONObjectOptJSONObject.optString("owner_uid", "");
                            tVar.f3243f = jSONObjectOptJSONObject.optString("owner_name", "");
                            tVar.f3244g = jSONObjectOptJSONObject.optString("owner_title", "");
                            tVar.f3245h = jSONObjectOptJSONObject.optString("owner_avatar", "");
                            tVar.f3246i = jSONObjectOptJSONObject.optLong("size_bytes", 0L);
                            tVar.f3247j = MusicManageActivity.this.U0(jSONObjectOptJSONObject);
                            tVar.f3248k = jSONObjectOptJSONObject.optInt("likes", 0);
                            tVar.f3249l = jSONObjectOptJSONObject.optInt("comments", 0);
                            tVar.f3250m = jSONObjectOptJSONObject.optInt("play_count", 0);
                            tVar.f3251n = jSONObjectOptJSONObject.optBoolean("liked", false);
                            tVar.f3252o = jSONObjectOptJSONObject.optBoolean("can_delete", false);
                            if (tVar.f3238a.length() != 0 && tVar.f3240c.length() != 0) {
                                MusicManageActivity.this.f3187H.add(tVar);
                            }
                        }
                    }
                }
                MusicManageActivity.this.f3188I.notifyDataSetChanged();
                MusicManageActivity.this.l1();
            } catch (Exception unused) {
                Toast.makeText(MusicManageActivity.this, "加载失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(MusicManageActivity.this, "加载失败: " + i2, 0).show();
        }
    }

    public class s extends BaseAdapter {

        public class a implements View.OnClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ t f3232a;

            public a(t tVar) {
                this.f3232a = tVar;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MusicManageActivity.this.X0(this.f3232a);
            }
        }

        public class b implements View.OnClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ t f3234a;

            public b(t tVar) {
                this.f3234a = tVar;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MusicManageActivity.this.L0(this.f3234a);
            }
        }

        public class c implements View.OnClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ t f3236a;

            public c(t tVar) {
                this.f3236a = tVar;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MusicManageActivity.this.h1(this.f3236a);
            }
        }

        public s() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return MusicManageActivity.this.f3187H.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            if (i2 < 0 || i2 >= MusicManageActivity.this.f3187H.size()) {
                return null;
            }
            return MusicManageActivity.this.f3187H.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            u uVar;
            if (view == null) {
                view = LayoutInflater.from(MusicManageActivity.this).inflate(f0.g.J0, viewGroup, false);
                uVar = new u(view);
                view.setTag(uVar);
            } else {
                uVar = (u) view.getTag();
            }
            t tVar = (t) getItem(i2);
            if (tVar == null) {
                return view;
            }
            String str = tVar.f3239b;
            uVar.f3254b.setText((str == null || str.length() == 0) ? "未命名歌曲" : tVar.f3239b);
            String str2 = tVar.f3243f;
            if (str2 == null || str2.length() == 0) {
                str2 = tVar.f3242e;
            }
            if (str2 == null || str2.length() == 0) {
                str2 = "我";
            }
            String str3 = tVar.f3244g;
            String strTrim = str3 == null ? "" : str3.trim();
            if (strTrim.length() > 0) {
                uVar.f3255c.setText("上传者 · " + str2 + " · " + strTrim);
            } else {
                uVar.f3255c.setText("上传者 · " + str2);
            }
            uVar.f3256d.setText("时长 " + MusicManageActivity.this.O0(tVar.f3247j) + " · 大小 " + MusicManageActivity.this.P0(tVar.f3246i));
            TextView textView = uVar.f3257e;
            StringBuilder sb = new StringBuilder();
            sb.append(tVar.f3251n ? "已赞 " : "赞 ");
            sb.append(tVar.f3248k);
            sb.append(" · 评论 ");
            sb.append(tVar.f3249l);
            sb.append(" · 播放 ");
            sb.append(tVar.f3250m);
            textView.setText(sb.toString());
            uVar.f3258f.setOnClickListener(new a(tVar));
            uVar.f3259g.setOnClickListener(new b(tVar));
            uVar.f3260h.setOnClickListener(new c(tVar));
            boolean z2 = tVar.f3252o;
            if (!MusicManageActivity.this.f3186G || z2) {
                uVar.f3259g.setVisibility(0);
                uVar.f3259g.setEnabled(z2);
                AbstractC0285k0.K(uVar.f3259g, z2 ? 1.0f : 0.55f);
            } else {
                uVar.f3259g.setVisibility(8);
            }
            String str4 = tVar.f3241d;
            if (str4 == null || str4.length() <= 0) {
                String str5 = tVar.f3245h;
                if (str5 == null || str5.length() <= 0) {
                    uVar.f3253a.setImageResource(f0.e.f4999T);
                } else {
                    AbstractC0455v.e(uVar.f3253a, tVar.f3245h);
                }
            } else {
                AbstractC0455v.e(uVar.f3253a, tVar.f3241d);
            }
            return view;
        }

        public /* synthetic */ s(MusicManageActivity musicManageActivity, j jVar) {
            this();
        }
    }

    public static class t {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f3238a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f3239b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f3240c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f3241d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f3242e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public String f3243f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public String f3244g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public String f3245h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public long f3246i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f3247j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f3248k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public int f3249l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public int f3250m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public boolean f3251n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public boolean f3252o;

        public t() {
        }

        public /* synthetic */ t(j jVar) {
            this();
        }
    }

    public static class u {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ImageView f3253a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f3254b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final TextView f3255c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final TextView f3256d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final TextView f3257e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final TextView f3258f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final TextView f3259g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final TextView f3260h;

        public u(View view) {
            this.f3253a = (ImageView) view.findViewById(f0.f.L5);
            this.f3254b = (TextView) view.findViewById(f0.f.Sa);
            this.f3255c = (TextView) view.findViewById(f0.f.Ta);
            this.f3256d = (TextView) view.findViewById(f0.f.Ra);
            this.f3257e = (TextView) view.findViewById(f0.f.Va);
            this.f3258f = (TextView) view.findViewById(f0.f.G1);
            this.f3259g = (TextView) view.findViewById(f0.f.D1);
            this.f3260h = (TextView) view.findViewById(f0.f.E1);
        }
    }

    public static /* synthetic */ int C0(MusicManageActivity musicManageActivity, int i2) {
        int i3 = musicManageActivity.f3189J + i2;
        musicManageActivity.f3189J = i3;
        return i3;
    }

    private int M0(int i2) {
        return (int) ((i2 * getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String P0(long j2) {
        if (j2 <= 0) {
            return "0B";
        }
        float f2 = j2;
        if (f2 >= 1024.0f) {
            float f3 = f2 / 1024.0f;
            return f3 < 1024.0f ? String.format(Locale.getDefault(), "%.1fKB", Float.valueOf(f3)) : String.format(Locale.getDefault(), "%.1fMB", Float.valueOf(f3 / 1024.0f));
        }
        return ((int) f2) + "B";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R0() {
        StringBuilder sb;
        String str = this.f3185F;
        if (str == null || str.length() == 0) {
            Toast.makeText(this, getString(f0.j.f5190N), 0).show();
            return;
        }
        EditText editText = this.f3196w;
        String strTrim = (editText == null || editText.getText() == null) ? "" : this.f3196w.getText().toString().trim();
        if (this.f3186G) {
            sb = new StringBuilder("/music/plaza?limit=");
            sb.append(50);
            sb.append("&offset=");
            sb.append(this.f3189J);
            sb.append("&sort=latest");
        } else {
            sb = new StringBuilder("/music/plaza/mine?limit=");
            sb.append(50);
            sb.append("&offset=");
            sb.append(this.f3189J);
        }
        if (strTrim.length() > 0) {
            sb.append("&q=");
            sb.append(m1(strTrim));
        }
        g0.d.R(sb.toString(), this.f3185F, new r());
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String Y0(android.net.Uri r10) throws java.lang.Throwable {
        /*
            r9 = this;
            java.lang.String r0 = "_display_name"
            java.lang.String r1 = ""
            if (r10 != 0) goto L7
            return r1
        L7:
            r2 = 0
            android.content.ContentResolver r3 = r9.getContentResolver()     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            r4 = 1
            java.lang.String[] r5 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            r4 = 0
            r5[r4] = r0     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            r7 = 0
            r8 = 0
            r6 = 0
            r4 = r10
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            if (r3 == 0) goto L32
            boolean r4 = r3.moveToFirst()     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L30
            if (r4 == 0) goto L32
            int r0 = r3.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L30
            if (r0 < 0) goto L32
            java.lang.String r2 = r3.getString(r0)     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L30
            goto L32
        L2d:
            r10 = move-exception
            r2 = r3
            goto L3d
        L30:
            goto L43
        L32:
            if (r3 == 0) goto L46
        L34:
            r3.close()
            goto L46
        L38:
            r10 = move-exception
            goto L3d
        L3a:
            r3 = r2
            goto L43
        L3d:
            if (r2 == 0) goto L42
            r2.close()
        L42:
            throw r10
        L43:
            if (r3 == 0) goto L46
            goto L34
        L46:
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 == 0) goto L57
            java.lang.String r10 = r10.getLastPathSegment()
            boolean r0 = android.text.TextUtils.isEmpty(r10)
            if (r0 != 0) goto L57
            r2 = r10
        L57:
            if (r2 != 0) goto L5a
            goto L5b
        L5a:
            r1 = r2
        L5b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.im.oldchat.ui.MusicManageActivity.Y0(android.net.Uri):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x0077 A[EXC_TOP_SPLITTER, PHI: r5
  0x0077: PHI (r5v4 android.content.res.AssetFileDescriptor) = (r5v3 android.content.res.AssetFileDescriptor), (r5v5 android.content.res.AssetFileDescriptor) binds: [B:49:0x0081, B:43:0x0075] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private long c1(android.net.Uri r13) throws java.lang.Throwable {
        /*
            r12 = this;
            java.lang.String r0 = "_size"
            r1 = -1
            if (r13 != 0) goto L7
            return r1
        L7:
            r3 = 0
            r5 = 0
            android.content.ContentResolver r6 = r12.getContentResolver()     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            r7 = 1
            java.lang.String[] r8 = new java.lang.String[r7]     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            r7 = 0
            r8[r7] = r0     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            r10 = 0
            r11 = 0
            r9 = 0
            r7 = r13
            android.database.Cursor r6 = r6.query(r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            if (r6 == 0) goto L41
            boolean r7 = r6.moveToFirst()     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3f
            if (r7 == 0) goto L41
            int r0 = r6.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3f
            if (r0 < 0) goto L41
            boolean r7 = r6.isNull(r0)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3f
            if (r7 != 0) goto L41
            long r7 = r6.getLong(r0)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3f
            int r0 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r0 <= 0) goto L41
            r6.close()     // Catch: java.lang.Exception -> L3b
        L3b:
            return r7
        L3c:
            r13 = move-exception
            r5 = r6
            goto L4c
        L3f:
            goto L52
        L41:
            if (r6 == 0) goto L55
        L43:
            r6.close()     // Catch: java.lang.Exception -> L55
            goto L55
        L47:
            r13 = move-exception
            goto L4c
        L49:
            r6 = r5
            goto L52
        L4c:
            if (r5 == 0) goto L51
            r5.close()     // Catch: java.lang.Exception -> L51
        L51:
            throw r13
        L52:
            if (r6 == 0) goto L55
            goto L43
        L55:
            android.content.ContentResolver r0 = r12.getContentResolver()     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            java.lang.String r6 = "r"
            android.content.res.AssetFileDescriptor r5 = r0.openAssetFileDescriptor(r13, r6)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            if (r5 == 0) goto L75
            long r6 = r5.getLength()     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            int r13 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r13 <= 0) goto L75
            long r0 = r5.getLength()     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            r5.close()     // Catch: java.lang.Exception -> L70
        L70:
            return r0
        L71:
            r13 = move-exception
            goto L7b
        L73:
            goto L81
        L75:
            if (r5 == 0) goto L84
        L77:
            r5.close()     // Catch: java.lang.Exception -> L84
            goto L84
        L7b:
            if (r5 == 0) goto L80
            r5.close()     // Catch: java.lang.Exception -> L80
        L80:
            throw r13
        L81:
            if (r5 == 0) goto L84
            goto L77
        L84:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.im.oldchat.ui.MusicManageActivity.c1(android.net.Uri):long");
    }

    private String m1(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception unused) {
            return str;
        }
    }

    public final TextView J0(String str) {
        TextView textView = new TextView(this);
        textView.setText(str);
        textView.setTextColor(getResources().getColor(f0.d.f4973j));
        textView.setGravity(17);
        textView.setPadding(M0(10), M0(8), M0(10), M0(8));
        textView.setBackgroundResource(f0.e.f4995P);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.bottomMargin = M0(6);
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    public final void K0() {
        if (this.f3187H.isEmpty()) {
            Toast.makeText(this, "当前页没有可删除歌曲", 0).show();
            return;
        }
        new c.a(this).t("批量删除").i("确定删除当前页全部 " + this.f3187H.size() + " 首歌曲吗？").q("删除", new b()).k("取消", null).v();
    }

    public final void L0(t tVar) {
        if (tVar != null && tVar.f3252o && !TextUtils.isEmpty(tVar.f3238a)) {
            new c.a(this).t("删除歌曲").i("确定删除这首歌曲吗？").q("删除", new c(tVar)).k("取消", null).v();
        } else if (this.f3186G) {
            Toast.makeText(this, "只能删除自己发布的歌曲", 0).show();
        }
    }

    public final boolean N0(int i2) {
        if (Q0(i2)) {
            return true;
        }
        String strE1 = e1(i2);
        if (TextUtils.isEmpty(strE1)) {
            return true;
        }
        this.f3192M = i2;
        AbstractC0121a.i(this, new String[]{strE1}, 5402);
        return false;
    }

    public final String O0(int i2) {
        if (i2 <= 0) {
            return "--:--";
        }
        int i3 = i2 / 1000;
        int i4 = i3 / 60;
        int i5 = i3 % 60;
        if (i4 > 99) {
            i4 = 99;
        }
        return String.format(Locale.getDefault(), "%02d:%02d", Integer.valueOf(i4), Integer.valueOf(i5));
    }

    public final boolean Q0(int i2) {
        String strE1 = e1(i2);
        return TextUtils.isEmpty(strE1) || AbstractC0235a.a(this, strE1) == 0;
    }

    public final void S0(t tVar) {
        String str;
        if (tVar == null || (str = tVar.f3238a) == null || str.length() == 0) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) MusicCommentsActivity.class);
        intent.putExtra("item_id", tVar.f3238a);
        String str2 = tVar.f3242e;
        if (str2 == null) {
            str2 = "";
        }
        intent.putExtra("owner_uid", str2);
        startActivity(intent);
    }

    public final InputStream T0(Uri uri) throws IOException {
        InputStream inputStreamOpenInputStream;
        if (uri == null) {
            throw new IOException("uri_empty");
        }
        try {
            inputStreamOpenInputStream = getContentResolver().openInputStream(uri);
        } catch (Exception unused) {
            inputStreamOpenInputStream = null;
        }
        if (inputStreamOpenInputStream != null) {
            return inputStreamOpenInputStream;
        }
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
            if (parcelFileDescriptorOpenFileDescriptor != null) {
                return new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptorOpenFileDescriptor);
            }
        } catch (Exception unused2) {
        }
        throw new IOException("cannot_open_stream");
    }

    public final int U0(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject;
        int iV0;
        int iV02;
        if (jSONObject == null) {
            return 0;
        }
        long jOptLong = jSONObject.optLong("duration_ms", 0L);
        if (jOptLong <= 0) {
            jOptLong = jSONObject.optLong("duration", 0L);
        }
        if (jOptLong <= 0) {
            jOptLong = jSONObject.optLong("duration_sec", 0L);
            if (jOptLong > 0) {
                jOptLong *= 1000;
            }
        }
        if (jOptLong <= 0 && (iV02 = V0(jSONObject.optString("duration_text", ""))) > 0) {
            return iV02;
        }
        if (jOptLong <= 0 && (jSONObjectOptJSONObject = jSONObject.optJSONObject("stats")) != null) {
            jOptLong = jSONObjectOptJSONObject.optLong("duration_ms", 0L);
            if (jOptLong <= 0) {
                jOptLong = jSONObjectOptJSONObject.optLong("duration", 0L);
            }
            if (jOptLong <= 0) {
                jOptLong = jSONObjectOptJSONObject.optLong("duration_sec", 0L);
                if (jOptLong > 0) {
                    jOptLong *= 1000;
                }
            }
            if (jOptLong <= 0 && (iV0 = V0(jSONObjectOptJSONObject.optString("duration_text", ""))) > 0) {
                return iV0;
            }
        }
        if (jOptLong <= 0) {
            return 0;
        }
        if (jOptLong < 1000) {
            jOptLong *= 1000;
        }
        if (jOptLong > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) jOptLong;
    }

    public final int V0(String str) {
        if (str == null) {
            return 0;
        }
        String strTrim = str.trim();
        if (strTrim.length() == 0) {
            return 0;
        }
        String[] strArrSplit = strTrim.split(":");
        if (strArrSplit.length >= 2 && strArrSplit.length <= 3) {
            int i2 = 0;
            int i3 = 0;
            while (i2 < strArrSplit.length) {
                try {
                    String str2 = strArrSplit[i2];
                    String strTrim2 = str2 == null ? "" : str2.trim();
                    if (strTrim2.length() == 0) {
                        return 0;
                    }
                    int i4 = (i2 == strArrSplit.length + (-1) && strTrim2.contains(".")) ? (int) Double.parseDouble(strTrim2) : Integer.parseInt(strTrim2);
                    if (i4 < 0) {
                        return 0;
                    }
                    i3 = (i3 * 60) + i4;
                    i2++;
                } catch (Exception unused) {
                }
            }
            if (i3 <= 0) {
                return 0;
            }
            long j2 = ((long) i3) * 1000;
            if (j2 > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            return (int) j2;
        }
        return 0;
    }

    public final void W0() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.addFlags(1);
        startActivityForResult(Intent.createChooser(intent, "选择新的封面图片"), 5401);
    }

    public final void X0(t tVar) {
        String str;
        if (tVar == null || (str = tVar.f3240c) == null || str.length() == 0) {
            Toast.makeText(this, "歌曲链接不可用", 0).show();
            return;
        }
        b1(tVar);
        Intent intent = new Intent(this, (Class<?>) MusicPlayerActivity.class);
        String str2 = tVar.f3239b;
        if (str2 == null) {
            str2 = "";
        }
        intent.putExtra("song_name", str2);
        String str3 = tVar.f3240c;
        if (str3 == null) {
            str3 = "";
        }
        intent.putExtra("song_url", str3);
        String str4 = tVar.f3241d;
        if (str4 == null) {
            str4 = "";
        }
        intent.putExtra("cover_url", str4);
        String str5 = tVar.f3242e;
        if (str5 == null) {
            str5 = "";
        }
        intent.putExtra("owner_uid", str5);
        String str6 = tVar.f3243f;
        if (str6 == null) {
            str6 = "";
        }
        intent.putExtra("owner_name", str6);
        String str7 = tVar.f3244g;
        if (str7 == null) {
            str7 = "";
        }
        intent.putExtra("owner_title", str7);
        String str8 = tVar.f3245h;
        if (str8 == null) {
            str8 = "";
        }
        intent.putExtra("owner_avatar", str8);
        String str9 = tVar.f3238a;
        intent.putExtra("item_id", str9 != null ? str9 : "");
        intent.putExtra("item_likes", Math.max(0, tVar.f3248k));
        intent.putExtra("item_comments", Math.max(0, tVar.f3249l));
        intent.putExtra("item_liked", tVar.f3251n);
        startActivity(intent);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x003e, code lost:
    
        r12 = r1.toByteArray();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0042, code lost:
    
        r11.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final byte[] Z0(android.net.Uri r11, long r12) throws java.lang.Throwable {
        /*
            r10 = this;
            r0 = 0
            if (r11 != 0) goto L4
            return r0
        L4:
            java.io.InputStream r11 = r10.T0(r11)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L5e
            if (r11 != 0) goto L10
            if (r11 == 0) goto Lf
            r11.close()     // Catch: java.lang.Exception -> Lf
        Lf:
            return r0
        L10:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4c
            r1.<init>()     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4c
            r2 = 8192(0x2000, float:1.148E-41)
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            r3 = 0
            r5 = r3
        L1c:
            int r7 = r11.read(r2)     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            r8 = -1
            if (r7 == r8) goto L3e
            long r8 = (long) r7
            long r5 = r5 + r8
            int r8 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r8 <= 0) goto L34
            int r8 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r8 <= 0) goto L34
            r11.close()     // Catch: java.lang.Exception -> L30
        L30:
            r1.close()     // Catch: java.lang.Exception -> L33
        L33:
            return r0
        L34:
            r8 = 0
            r1.write(r2, r8, r7)     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            goto L1c
        L39:
            r12 = move-exception
        L3a:
            r0 = r11
            goto L51
        L3c:
            goto L61
        L3e:
            byte[] r12 = r1.toByteArray()     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            r11.close()     // Catch: java.lang.Exception -> L45
        L45:
            r1.close()     // Catch: java.lang.Exception -> L48
        L48:
            return r12
        L49:
            r12 = move-exception
            r1 = r0
            goto L3a
        L4c:
            r1 = r0
            goto L61
        L4f:
            r12 = move-exception
            r1 = r0
        L51:
            if (r0 == 0) goto L58
            r0.close()     // Catch: java.lang.Exception -> L57
            goto L58
        L57:
        L58:
            if (r1 == 0) goto L5d
            r1.close()     // Catch: java.lang.Exception -> L5d
        L5d:
            throw r12
        L5e:
            r11 = r0
            r1 = r11
        L61:
            if (r11 == 0) goto L68
            r11.close()     // Catch: java.lang.Exception -> L67
            goto L68
        L67:
        L68:
            if (r1 == 0) goto L6d
            r1.close()     // Catch: java.lang.Exception -> L6d
        L6d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.im.oldchat.ui.MusicManageActivity.Z0(android.net.Uri, long):byte[]");
    }

    public final void a1() {
        TextView textView = this.f3195P;
        if (textView == null) {
            return;
        }
        if (this.f3193N == null) {
            textView.setText("新封面: 不修改");
            return;
        }
        textView.setText("新封面: " + Y0(this.f3193N));
    }

    public final void b1(t tVar) {
        String str;
        if (tVar == null || (str = tVar.f3238a) == null || str.length() == 0) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("item_id", tVar.f3238a);
            g0.d.S("/music/plaza/play", jSONObject, this.f3185F, new h(tVar));
        } catch (Exception unused) {
        }
    }

    public final String d1(Uri uri, String str) {
        String type;
        try {
            type = getContentResolver().getType(uri);
        } catch (Exception unused) {
            type = null;
        }
        if (!TextUtils.isEmpty(type)) {
            return type;
        }
        String lowerCase = str == null ? "" : str.toLowerCase(Locale.US);
        return lowerCase.endsWith(".gif") ? "image/gif" : lowerCase.endsWith(".png") ? "image/png" : "image/jpeg";
    }

    public final String e1(int i2) {
        int i3 = Build.VERSION.SDK_INT;
        return i3 < 23 ? "" : i3 >= 33 ? i2 == 2 ? "android.permission.READ_MEDIA_IMAGES" : "" : "android.permission.READ_EXTERNAL_STORAGE";
    }

    public final void f1(t tVar) {
        String str;
        String str2;
        if (tVar == null || (str = tVar.f3240c) == null || str.length() == 0) {
            Toast.makeText(this, "歌曲链接不可用", 0).show();
            return;
        }
        Y.f fVar = new Y.f();
        fVar.f4329a = tVar.f3238a;
        fVar.f4330b = tVar.f3239b;
        fVar.f4331c = tVar.f3240c;
        String str3 = tVar.f3241d;
        fVar.f4332d = str3;
        if ((str3 == null || str3.length() == 0) && (str2 = tVar.f3245h) != null && str2.length() > 0) {
            fVar.f4332d = tVar.f3245h;
        }
        fVar.f4333e = tVar.f3242e;
        fVar.f4334f = tVar.f3243f;
        fVar.f4335g = tVar.f3247j;
        Y.m(this, this.f3185F, fVar);
    }

    public final void g1(t tVar) {
        if (tVar == null || !tVar.f3252o || TextUtils.isEmpty(tVar.f3238a)) {
            Toast.makeText(this, "只能编辑自己发布的歌曲", 0).show();
            return;
        }
        this.f3193N = null;
        EditText editText = new EditText(this);
        editText.setHint("请输入歌曲名称");
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(64)});
        if (!TextUtils.isEmpty(tVar.f3239b)) {
            editText.setText(tVar.f3239b);
            editText.setSelection(tVar.f3239b.length());
        }
        TextView textViewJ0 = J0("选择新封面");
        TextView textView = new TextView(this);
        this.f3195P = textView;
        textView.setPadding(0, M0(6), 0, M0(4));
        a1();
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(M0(14), M0(6), M0(14), 0);
        linearLayout.addView(editText);
        linearLayout.addView(textView);
        linearLayout.addView(textViewJ0);
        textViewJ0.setOnClickListener(new d());
        o.c cVarA = new c.a(this).t("编辑歌曲信息").u(linearLayout).k("取消", null).q("保存", null).a();
        this.f3194O = cVarA;
        cVarA.show();
        cVarA.setOnDismissListener(new e(cVarA));
        Button buttonD = cVarA.d(-1);
        if (buttonD != null) {
            buttonD.setOnClickListener(new f(editText, cVarA, tVar));
        }
    }

    public final void h1(t tVar) {
        if (tVar == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("播放");
        arrayList.add("评论");
        arrayList.add("分享到聊天");
        if (this.f3186G) {
            arrayList.add(tVar.f3251n ? "取消点赞" : "点赞");
            arrayList.add("收藏");
            if (tVar.f3252o) {
                arrayList.add("编辑信息");
                arrayList.add("删除");
            }
        } else if (tVar.f3252o) {
            arrayList.add("编辑信息");
            arrayList.add("删除");
        }
        CharSequence[] charSequenceArr = (CharSequence[]) arrayList.toArray(new CharSequence[arrayList.size()]);
        c.a aVar = new c.a(this);
        String str = tVar.f3239b;
        aVar.t((str == null || str.length() == 0) ? "歌曲管理" : tVar.f3239b).g(charSequenceArr, new a(arrayList, tVar)).k("取消", null).v();
    }

    public final void i1(t tVar, String str, Uri uri) throws Throwable {
        String str2;
        byte[] bArrZ0;
        String strD1;
        if (tVar == null || TextUtils.isEmpty(tVar.f3238a)) {
            return;
        }
        if (TextUtils.isEmpty(this.f3185F)) {
            Toast.makeText(this, getString(f0.j.f5190N), 0).show();
            return;
        }
        str2 = "cover.jpg";
        if (uri == null) {
            bArrZ0 = null;
            strD1 = "image/jpeg";
        } else {
            if (!Q0(2)) {
                Toast.makeText(this, "未授予媒体读取权限，请重新选择", 0).show();
                return;
            }
            if (c1(uri) > 1048576) {
                Toast.makeText(this, "封面不能超过1MB", 0).show();
                return;
            }
            String strY0 = Y0(uri);
            str2 = TextUtils.isEmpty(strY0) ? "cover.jpg" : strY0;
            strD1 = d1(uri, str2);
            bArrZ0 = Z0(uri, 1048576L);
            if (bArrZ0 == null || bArrZ0.length == 0) {
                Toast.makeText(this, "封面读取失败或超过1MB", 0).show();
                return;
            }
        }
        byte[] bArr = bArrZ0;
        String str3 = str2;
        String str4 = strD1;
        Toast.makeText(this, "正在保存修改...", 0).show();
        g0.d.W("/music/plaza/update?item_id=" + m1(tVar.f3238a) + "&name=" + m1(str), new byte[0], "meta.txt", "text/plain", bArr, str3, str4, this.f3185F, new g(str, tVar));
    }

    public final void j1(t tVar) {
        String str;
        if (tVar == null || (str = tVar.f3238a) == null || str.length() == 0) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("item_id", tVar.f3238a);
            g0.d.S(tVar.f3251n ? "/music/plaza/unlike" : "/music/plaza/like", jSONObject, this.f3185F, new i(tVar));
        } catch (Exception unused) {
            Toast.makeText(this, "操作失败", 0).show();
        }
    }

    public final void k1() {
        if (N0(2)) {
            W0();
        }
    }

    public final void l1() {
        if (this.f3199z != null) {
            int i2 = 0;
            for (int i3 = 0; i3 < this.f3187H.size(); i3++) {
                if (this.f3187H.get(i3) != null && ((t) this.f3187H.get(i3)).f3251n) {
                    i2++;
                }
            }
            if (this.f3186G) {
                this.f3199z.setText("全部歌曲 " + this.f3190K + " 首 · 当前页 " + this.f3187H.size() + " 首 · 已点赞 " + i2 + " 首");
            } else {
                this.f3199z.setText("我的上传 " + this.f3190K + " 首 · 当前页 " + this.f3187H.size() + " 首 · 已点赞 " + i2 + " 首");
            }
        }
        if (this.f3180A != null) {
            int i4 = this.f3190K;
            int i5 = i4 <= 0 ? 0 : this.f3189J + 1;
            int iMin = Math.min(i4, this.f3189J + this.f3187H.size());
            this.f3180A.setText("第 " + i5 + " - " + iMin + " 条，共 " + this.f3190K + " 条");
        }
        TextView textView = this.f3181B;
        if (textView != null) {
            textView.setEnabled(this.f3189J > 0);
            AbstractC0285k0.K(this.f3181B, this.f3189J > 0 ? 1.0f : 0.55f);
        }
        TextView textView2 = this.f3182C;
        if (textView2 != null) {
            textView2.setEnabled(this.f3191L);
            AbstractC0285k0.K(this.f3182C, this.f3191L ? 1.0f : 0.55f);
        }
        if (this.f3198y == null || this.f3186G) {
            return;
        }
        boolean zIsEmpty = this.f3187H.isEmpty();
        this.f3198y.setEnabled(!zIsEmpty);
        AbstractC0285k0.K(this.f3198y, zIsEmpty ? 0.55f : 1.0f);
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        Uri data;
        super.onActivityResult(i2, i3, intent);
        if (i2 != 5401 || i3 != -1 || intent == null || (data = intent.getData()) == null) {
            return;
        }
        this.f3193N = data;
        a1();
        Toast.makeText(this, "已选择新封面: " + Y0(data), 0).show();
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5112F);
        this.f3186G = getIntent() != null && getIntent().getBooleanExtra("all_list_mode", false);
        this.f3185F = getSharedPreferences("auth", 0).getString("access_token", "");
        View viewA0 = a0(f0.f.B1);
        TextView textView = (TextView) a0(f0.f.Xa);
        this.f3196w = (EditText) a0(f0.f.H4);
        this.f3197x = (TextView) a0(f0.f.I1);
        this.f3198y = (TextView) a0(f0.f.C1);
        this.f3199z = (TextView) a0(f0.f.Wa);
        this.f3180A = (TextView) a0(f0.f.Ua);
        this.f3181B = (TextView) a0(f0.f.H1);
        this.f3182C = (TextView) a0(f0.f.F1);
        this.f3183D = (TextView) a0(f0.f.Qa);
        this.f3184E = (ListView) a0(f0.f.U6);
        if (this.f3186G) {
            if (textView != null) {
                textView.setText("全部歌曲");
            }
            TextView textView2 = this.f3198y;
            if (textView2 != null) {
                textView2.setVisibility(8);
            }
            TextView textView3 = this.f3183D;
            if (textView3 != null) {
                textView3.setText("暂无歌曲");
            }
        }
        if (viewA0 != null) {
            viewA0.setOnClickListener(new j());
        }
        s sVar = new s(this, null);
        this.f3188I = sVar;
        this.f3184E.setAdapter((ListAdapter) sVar);
        this.f3184E.setEmptyView(this.f3183D);
        this.f3184E.setOnItemClickListener(new k());
        this.f3184E.setOnItemLongClickListener(new l());
        TextView textView4 = this.f3197x;
        if (textView4 != null) {
            textView4.setOnClickListener(new m());
        }
        TextView textView5 = this.f3198y;
        if (textView5 != null) {
            textView5.setOnClickListener(new n());
        }
        EditText editText = this.f3196w;
        if (editText != null) {
            editText.setOnEditorActionListener(new o());
        }
        TextView textView6 = this.f3181B;
        if (textView6 != null) {
            textView6.setOnClickListener(new p());
        }
        TextView textView7 = this.f3182C;
        if (textView7 != null) {
            textView7.setOnClickListener(new q());
        }
        R0();
    }

    @Override // f0.a, c.AbstractActivityC0167u, android.app.Activity, c.AbstractC0121a.b
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        if (i2 != 5402) {
            return;
        }
        int i3 = this.f3192M;
        this.f3192M = 0;
        if (iArr == null || iArr.length <= 0 || iArr[0] != 0) {
            Toast.makeText(this, "未授予媒体读取权限，无法选择封面", 0).show();
        } else if (i3 == 2) {
            W0();
        }
    }
}

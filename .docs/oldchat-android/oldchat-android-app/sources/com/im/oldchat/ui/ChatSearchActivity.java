package com.im.oldchat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.C0223n;
import g0.d;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import l0.AbstractC0380g;
import org.json.JSONArray;
import org.json.JSONObject;
import org.spongycastle.i18n.TextBundle;

/* JADX INFO: loaded from: classes.dex */
public class ChatSearchActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public TextView f2483A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public TextView f2484B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public TextView f2485C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public TextView f2486D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public TextView f2487E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public TextView f2488F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public ListView f2489G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public C0223n f2490H;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public String f2493K;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public String f2495M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public String f2496N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public String f2497O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public String f2498P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public String f2499Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public String f2500R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public int f2501S;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public EditText f2507w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public TextView f2508x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f2509y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public TextView f2510z;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final List f2491I = new ArrayList();

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final HashSet f2492J = new HashSet();

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public String f2494L = "direct";

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public String f2502T = "";

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public int f2503U = 0;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public boolean f2504V = false;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public boolean f2505W = false;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public String f2506X = "all";

    public class a implements d.i {
        public a() {
        }

        @Override // g0.d.i
        public void b(String str) {
            String str2;
            try {
                List listK0 = ChatSearchActivity.this.K0(str);
                for (int i2 = 0; i2 < listK0.size(); i2++) {
                    C0223n.a aVar = (C0223n.a) listK0.get(i2);
                    if (aVar != null && (str2 = aVar.f4685a) != null && str2.length() != 0 && !ChatSearchActivity.this.f2492J.contains(aVar.f4685a)) {
                        ChatSearchActivity.this.f2492J.add(aVar.f4685a);
                        ChatSearchActivity.this.f2491I.add(aVar);
                    }
                }
                ChatSearchActivity.n0(ChatSearchActivity.this, listK0.size());
                ChatSearchActivity.this.f2504V = listK0.size() >= 50;
                ChatSearchActivity.this.f2490H.notifyDataSetChanged();
                if (ChatSearchActivity.this.f2491I.isEmpty()) {
                    ChatSearchActivity.this.f2484B.setText("未找到相关消息");
                }
            } catch (Exception unused) {
                Toast.makeText(ChatSearchActivity.this, "解析搜索结果失败", 0).show();
            }
            ChatSearchActivity.this.f2505W = false;
            ChatSearchActivity.this.N0();
            ChatSearchActivity.this.R0();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            ChatSearchActivity.this.f2505W = false;
            ChatSearchActivity.this.N0();
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(ChatSearchActivity.this, "搜索失败: " + i2, 0).show();
            ChatSearchActivity.this.R0();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ChatSearchActivity.this.finish();
        }
    }

    public class c implements AdapterView.OnItemClickListener {
        public c() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            if (i2 < 0 || i2 >= ChatSearchActivity.this.f2491I.size()) {
                return;
            }
            ChatSearchActivity chatSearchActivity = ChatSearchActivity.this;
            chatSearchActivity.J0((C0223n.a) chatSearchActivity.f2491I.get(i2));
        }
    }

    public class d implements AbsListView.OnScrollListener {
        public d() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            if (ChatSearchActivity.this.f2505W || !ChatSearchActivity.this.f2504V || ChatSearchActivity.this.f2502T.length() <= 0 || i4 <= 0 || i2 + i3 < i4 - 2) {
                return;
            }
            ChatSearchActivity.this.I0(false);
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
            if (ChatSearchActivity.this.f2505W || !ChatSearchActivity.this.f2504V || ChatSearchActivity.this.f2502T.length() <= 0) {
                return;
            }
            ChatSearchActivity.this.I0(false);
        }
    }

    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view == ChatSearchActivity.this.f2487E) {
                ChatSearchActivity.this.f2506X = TextBundle.TEXT_ENTRY;
            } else if (view == ChatSearchActivity.this.f2488F) {
                ChatSearchActivity.this.f2506X = "media";
            } else {
                ChatSearchActivity.this.f2506X = "all";
            }
            ChatSearchActivity.this.Q0();
            if (ChatSearchActivity.this.f2502T.length() > 0) {
                ChatSearchActivity.this.I0(true);
            }
        }
    }

    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ChatSearchActivity.this.L0();
        }
    }

    public class h implements View.OnClickListener {
        public h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (ChatSearchActivity.this.f2507w != null) {
                ChatSearchActivity.this.f2507w.setText("");
                ChatSearchActivity.this.f2507w.requestFocus();
            }
            ChatSearchActivity.this.H0();
            ChatSearchActivity.this.P0();
        }
    }

    public class i implements TextView.OnEditorActionListener {
        public i() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
            if (i2 == 3) {
                ChatSearchActivity.this.L0();
                return true;
            }
            if (keyEvent == null || keyEvent.getAction() != 0 || keyEvent.getKeyCode() != 66) {
                return false;
            }
            ChatSearchActivity.this.L0();
            return true;
        }
    }

    public class j implements TextWatcher {
        public j() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            ChatSearchActivity.this.P0();
            if (editable == null || editable.toString().trim().length() == 0) {
                ChatSearchActivity.this.H0();
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L0() {
        String strTrim = this.f2507w.getText() == null ? "" : this.f2507w.getText().toString().trim();
        if (strTrim.length() == 0) {
            Toast.makeText(this, "请输入关键词", 0).show();
            return;
        }
        this.f2502T = strTrim;
        C0223n c0223n = this.f2490H;
        if (c0223n != null) {
            c0223n.d(strTrim);
        }
        I0(true);
    }

    public static /* synthetic */ int n0(ChatSearchActivity chatSearchActivity, int i2) {
        int i3 = chatSearchActivity.f2503U + i2;
        chatSearchActivity.f2503U = i3;
        return i3;
    }

    public final void C0() {
        f fVar = new f();
        this.f2486D.setOnClickListener(fVar);
        this.f2487E.setOnClickListener(fVar);
        this.f2488F.setOnClickListener(fVar);
        Q0();
    }

    public final void D0() {
        View viewA0 = a0(f0.f.A3);
        if (viewA0 instanceof ImageView) {
            ((ImageView) viewA0).setColorFilter(getResources().getColor(f0.d.f4973j));
        }
        if (viewA0 != null) {
            viewA0.setOnClickListener(new b());
        }
        TextView textView = (TextView) a0(f0.f.Id);
        if (textView != null) {
            textView.setText("查找聊天记录");
        }
        if (this.f2508x != null) {
            if ("group".equals(this.f2494L)) {
                String str = this.f2499Q;
                String str2 = (str == null || str.length() == 0) ? this.f2498P : this.f2499Q;
                this.f2508x.setText(str2 != null ? str2 : "");
            } else {
                String str3 = this.f2496N;
                String str4 = (str3 == null || str3.length() == 0) ? this.f2495M : this.f2496N;
                this.f2508x.setText(str4 != null ? str4 : "");
            }
        }
    }

    public final void E0() {
        C0223n c0223n = new C0223n(this, this.f2491I, "group".equals(this.f2494L));
        this.f2490H = c0223n;
        c0223n.d(this.f2502T);
        this.f2489G.setAdapter((ListAdapter) this.f2490H);
        this.f2489G.setEmptyView(this.f2484B);
        this.f2489G.setOnItemClickListener(new c());
        this.f2489G.setOnScrollListener(new d());
        this.f2483A.setOnClickListener(new e());
        N0();
        R0();
    }

    public final void F0() {
        this.f2509y.setOnClickListener(new g());
        TextView textView = this.f2510z;
        if (textView != null) {
            textView.setOnClickListener(new h());
        }
        this.f2507w.setOnEditorActionListener(new i());
        this.f2507w.addTextChangedListener(new j());
        P0();
    }

    public final String G0(int i2, String str) {
        StringBuilder sb = new StringBuilder();
        if ("group".equals(this.f2494L)) {
            sb.append("/groups/messages/search?group_id=");
            sb.append(S0(this.f2498P));
        } else {
            sb.append("/direct/messages/search?with_uid=");
            sb.append(S0(this.f2495M));
        }
        sb.append("&q=");
        sb.append(S0(str));
        sb.append("&kind=");
        sb.append(this.f2506X);
        sb.append("&limit=");
        sb.append(50);
        sb.append("&offset=");
        sb.append(i2);
        return sb.toString();
    }

    public final void H0() {
        this.f2502T = "";
        this.f2503U = 0;
        this.f2504V = false;
        this.f2505W = false;
        this.f2491I.clear();
        this.f2492J.clear();
        C0223n c0223n = this.f2490H;
        if (c0223n != null) {
            c0223n.d("");
            this.f2490H.notifyDataSetChanged();
        }
        TextView textView = this.f2484B;
        if (textView != null) {
            textView.setText("暂无搜索结果");
        }
        N0();
        R0();
    }

    public final void I0(boolean z2) {
        String str;
        String str2;
        if (this.f2505W) {
            return;
        }
        String str3 = this.f2493K;
        if (str3 == null || str3.length() == 0) {
            Toast.makeText(this, getString(f0.j.f5190N), 0).show();
            return;
        }
        if ("group".equals(this.f2494L) && ((str2 = this.f2498P) == null || str2.length() == 0)) {
            Toast.makeText(this, "群ID无效", 0).show();
            return;
        }
        if ("direct".equals(this.f2494L) && ((str = this.f2495M) == null || str.length() == 0)) {
            Toast.makeText(this, "好友ID无效", 0).show();
            return;
        }
        if (z2) {
            this.f2503U = 0;
            this.f2504V = false;
            this.f2491I.clear();
            this.f2492J.clear();
            this.f2490H.notifyDataSetChanged();
        }
        this.f2505W = true;
        N0();
        g0.d.R(G0(this.f2503U, this.f2502T), this.f2493K, new a());
    }

    public final void J0(C0223n.a aVar) {
        String str;
        if (aVar == null || (str = aVar.f4685a) == null || str.length() == 0) {
            return;
        }
        if (!"group".equals(this.f2494L)) {
            Intent intent = new Intent(this, (Class<?>) ChatActivity.class);
            intent.putExtra("friend_uid", this.f2495M);
            intent.putExtra("friend_name", this.f2496N);
            intent.putExtra("friend_avatar", this.f2497O);
            intent.putExtra("jump_message_id", aVar.f4685a);
            startActivity(intent);
            return;
        }
        Intent intent2 = new Intent(this, (Class<?>) GroupChatActivity.class);
        intent2.putExtra("group_id", this.f2498P);
        intent2.putExtra("group_name", this.f2499Q);
        intent2.putExtra("group_avatar", this.f2500R);
        intent2.putExtra("group_role", this.f2501S);
        intent2.putExtra("jump_message_id", aVar.f4685a);
        startActivity(intent2);
    }

    public final List K0(String str) {
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("messages");
        if (jSONArrayOptJSONArray == null) {
            return arrayList;
        }
        for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i2);
            if (jSONObjectOptJSONObject != null) {
                C0223n.a aVar = new C0223n.a();
                aVar.f4685a = jSONObjectOptJSONObject.optString("id", "");
                aVar.f4686b = jSONObjectOptJSONObject.optString("from_uid", "");
                aVar.f4687c = jSONObjectOptJSONObject.optString("msg_type", TextBundle.TEXT_ENTRY);
                aVar.f4688d = jSONObjectOptJSONObject.optString("body", "");
                aVar.f4689e = AbstractC0380g.g(jSONObjectOptJSONObject.optLong("created_at", 0L));
                String str2 = aVar.f4685a;
                if (str2 != null && str2.length() != 0) {
                    arrayList.add(aVar);
                }
            }
        }
        return arrayList;
    }

    public final void M0() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        String stringExtra = intent.getStringExtra("mode");
        this.f2494L = stringExtra;
        if (!"group".equals(stringExtra)) {
            this.f2494L = "direct";
        }
        this.f2495M = intent.getStringExtra("friend_uid");
        this.f2496N = intent.getStringExtra("friend_name");
        this.f2497O = intent.getStringExtra("friend_avatar");
        String stringExtra2 = intent.getStringExtra("group_id");
        this.f2498P = stringExtra2;
        if (stringExtra2 != null) {
            this.f2498P = stringExtra2.trim().toUpperCase();
        }
        this.f2499Q = intent.getStringExtra("group_name");
        this.f2500R = intent.getStringExtra("group_avatar");
        this.f2501S = intent.getIntExtra("group_role", 0);
    }

    public final void N0() {
        TextView textView = this.f2483A;
        if (textView == null) {
            return;
        }
        if (this.f2505W) {
            textView.setVisibility(0);
            this.f2483A.setEnabled(false);
            this.f2483A.setText("搜索中...");
        } else {
            if (this.f2491I.isEmpty()) {
                this.f2483A.setVisibility(8);
                return;
            }
            this.f2483A.setVisibility(0);
            if (this.f2504V) {
                this.f2483A.setEnabled(true);
                this.f2483A.setText("加载更多");
            } else {
                this.f2483A.setEnabled(false);
                this.f2483A.setText("没有更多了");
            }
        }
    }

    public final void O0(TextView textView, boolean z2) {
        if (textView == null) {
            return;
        }
        textView.setSelected(z2);
        textView.setTextColor(getResources().getColor(z2 ? f0.d.f4970g : f0.d.f4973j));
    }

    public final void P0() {
        EditText editText;
        if (this.f2510z == null || (editText = this.f2507w) == null) {
            return;
        }
        this.f2510z.setVisibility((editText.getText() == null ? "" : this.f2507w.getText().toString().trim()).length() == 0 ? 8 : 0);
    }

    public final void Q0() {
        O0(this.f2486D, "all".equals(this.f2506X));
        O0(this.f2487E, TextBundle.TEXT_ENTRY.equals(this.f2506X));
        O0(this.f2488F, "media".equals(this.f2506X));
    }

    public final void R0() {
        if (this.f2485C == null) {
            return;
        }
        if (this.f2502T.length() == 0) {
            this.f2485C.setText("请输入关键词开始搜索");
            return;
        }
        String str = TextBundle.TEXT_ENTRY.equals(this.f2506X) ? "文字" : "media".equals(this.f2506X) ? "媒体" : "全部";
        this.f2485C.setText("关键词: " + this.f2502T + " · 类型: " + str + " · 已加载 " + this.f2491I.size() + " 条");
    }

    public final String S0(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception unused) {
            return str;
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5143f);
        this.f2507w = (EditText) a0(f0.f.R4);
        this.f2508x = (TextView) a0(f0.f.Bd);
        this.f2509y = (TextView) a0(f0.f.z3);
        this.f2510z = (TextView) a0(f0.f.B3);
        this.f2483A = (TextView) a0(f0.f.C3);
        this.f2484B = (TextView) a0(f0.f.Cd);
        this.f2485C = (TextView) a0(f0.f.Hd);
        this.f2486D = (TextView) a0(f0.f.N0);
        this.f2487E = (TextView) a0(f0.f.P0);
        this.f2488F = (TextView) a0(f0.f.O0);
        this.f2489G = (ListView) a0(f0.f.f7);
        this.f2493K = getSharedPreferences("auth", 0).getString("access_token", "");
        M0();
        D0();
        C0();
        E0();
        F0();
        String stringExtra = getIntent().getStringExtra("initial_query");
        if (stringExtra == null || stringExtra.trim().length() <= 0) {
            return;
        }
        this.f2507w.setText(stringExtra.trim());
        L0();
    }
}

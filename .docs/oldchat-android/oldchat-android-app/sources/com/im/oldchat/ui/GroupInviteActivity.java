package com.im.oldchat.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import g0.d;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GroupInviteActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public String f2904A;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public h f2909F;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ListView f2914w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public EditText f2915x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f2916y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public String f2917z;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public final List f2905B = new ArrayList();

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final List f2906C = new ArrayList();

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final HashSet f2907D = new HashSet();

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final HashSet f2908E = new HashSet();

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public int f2910G = 0;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public int f2911H = 0;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public int f2912I = 0;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final List f2913J = new ArrayList();

    public class a implements AdapterView.OnItemClickListener {
        public a() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            GroupInviteActivity.this.D0(i2);
        }
    }

    public class b implements TextWatcher {
        public b() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            GroupInviteActivity.this.u0();
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            GroupInviteActivity.this.v0();
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            GroupInviteActivity.this.finish();
        }
    }

    public class e implements d.i {
        public e() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("members");
                GroupInviteActivity.this.f2908E.clear();
                if (jSONArrayOptJSONArray != null) {
                    for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                        JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i2);
                        if (jSONObjectOptJSONObject != null) {
                            String strB0 = GroupInviteActivity.this.B0(jSONObjectOptJSONObject.optString("uid", ""));
                            if (strB0.length() > 0) {
                                GroupInviteActivity.this.f2908E.add(strB0);
                            }
                        }
                    }
                }
            } catch (Exception unused) {
                GroupInviteActivity.this.f2908E.clear();
            }
            GroupInviteActivity.this.y0();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            GroupInviteActivity.this.f2908E.clear();
            GroupInviteActivity.this.y0();
        }
    }

    public class f implements d.i {
        public f() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("friends");
                GroupInviteActivity.this.f2905B.clear();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    j0.w wVar = new j0.w();
                    wVar.f5645a = jSONObject.optString("id");
                    wVar.f5646b = jSONObject.optString("uid");
                    wVar.f5647c = jSONObject.optString("username");
                    wVar.f5648d = jSONObject.optString("display_name");
                    wVar.f5649e = jSONObject.optString("remark_name", "");
                    wVar.f5650f = jSONObject.optString("user_title");
                    wVar.f5651g = jSONObject.optString("avatar_url");
                    GroupInviteActivity.this.f2905B.add(wVar);
                }
                GroupInviteActivity.this.u0();
            } catch (Exception unused) {
                Toast.makeText(GroupInviteActivity.this, "加载好友失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(GroupInviteActivity.this, "加载好友失败: " + i2, 0).show();
        }
    }

    public class g implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ List f2924a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f2925b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f2926c;

        public g(List list, int i2, String str) {
            this.f2924a = list;
            this.f2925b = i2;
            this.f2926c = str;
        }

        @Override // g0.d.i
        public void b(String str) {
            GroupInviteActivity.s0(GroupInviteActivity.this);
            GroupInviteActivity.this.w0(this.f2924a, this.f2925b + 1);
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (i2 == 409 || (str != null && str.contains("already_member"))) {
                GroupInviteActivity.g0(GroupInviteActivity.this);
            } else {
                GroupInviteActivity.h0(GroupInviteActivity.this);
                String strC0 = GroupInviteActivity.this.C0(this.f2926c);
                if (strC0.length() > 0 && GroupInviteActivity.this.f2913J.size() < 3) {
                    GroupInviteActivity.this.f2913J.add(strC0);
                }
            }
            GroupInviteActivity.this.w0(this.f2924a, this.f2925b + 1);
        }
    }

    public class h extends BaseAdapter {

        public class a implements View.OnClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f2929a;

            public a(int i2) {
                this.f2929a = i2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GroupInviteActivity.this.D0(this.f2929a);
            }
        }

        public class b implements View.OnClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f2931a;

            public b(int i2) {
                this.f2931a = i2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GroupInviteActivity.this.D0(this.f2931a);
            }
        }

        public h() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return GroupInviteActivity.this.f2906C.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            if (i2 < 0 || i2 >= GroupInviteActivity.this.f2906C.size()) {
                return null;
            }
            return GroupInviteActivity.this.f2906C.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            i iVar;
            if (view == null) {
                view = LayoutInflater.from(GroupInviteActivity.this).inflate(f0.g.z0, viewGroup, false);
                iVar = new i(view);
                view.setTag(iVar);
            } else {
                iVar = (i) view.getTag();
            }
            j0.w wVar = (j0.w) getItem(i2);
            if (wVar == null) {
                return view;
            }
            String strB = j0.d.b(wVar);
            if ((strB == null || strB.length() == 0) && (strB = wVar.f5646b) == null) {
                strB = "";
            }
            iVar.f2933a.setText(strB);
            String str = wVar.f5646b;
            if (str == null) {
                str = "";
            }
            String str2 = wVar.f5647c;
            String str3 = str2 != null ? str2 : "";
            String str4 = "UID: " + str;
            if (str3.length() > 0) {
                str4 = str4 + " · 用户名: " + str3;
            }
            iVar.f2934b.setText(str4);
            String strB0 = GroupInviteActivity.this.B0(str);
            iVar.f2935c.setOnCheckedChangeListener(null);
            iVar.f2935c.setChecked(GroupInviteActivity.this.f2907D.contains(strB0));
            iVar.f2935c.setOnClickListener(new a(i2));
            view.setOnClickListener(new b(i2));
            return view;
        }

        public /* synthetic */ h(GroupInviteActivity groupInviteActivity, a aVar) {
            this();
        }
    }

    public static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextView f2933a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f2934b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final CheckBox f2935c;

        public i(View view) {
            this.f2933a = (TextView) view.findViewById(f0.f.sa);
            this.f2934b = (TextView) view.findViewById(f0.f.ra);
            this.f2935c = (CheckBox) view.findViewById(f0.f.d4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String B0(String str) {
        return str == null ? "" : str.trim().toUpperCase(Locale.getDefault());
    }

    private String F0(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception unused) {
            return str;
        }
    }

    public static /* synthetic */ int g0(GroupInviteActivity groupInviteActivity) {
        int i2 = groupInviteActivity.f2911H;
        groupInviteActivity.f2911H = i2 + 1;
        return i2;
    }

    public static /* synthetic */ int h0(GroupInviteActivity groupInviteActivity) {
        int i2 = groupInviteActivity.f2912I;
        groupInviteActivity.f2912I = i2 + 1;
        return i2;
    }

    public static /* synthetic */ int s0(GroupInviteActivity groupInviteActivity) {
        int i2 = groupInviteActivity.f2910G;
        groupInviteActivity.f2910G = i2 + 1;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y0() {
        g0.d.R("/friends", this.f2917z, new f());
    }

    public final String A0(String str) {
        return str == null ? "" : str.trim().toLowerCase(Locale.getDefault());
    }

    public final String C0(String str) {
        String str2;
        if (str == null || str.length() == 0) {
            return "";
        }
        for (int i2 = 0; i2 < this.f2905B.size(); i2++) {
            j0.w wVar = (j0.w) this.f2905B.get(i2);
            if (wVar != null && (str2 = wVar.f5646b) != null && str.equalsIgnoreCase(str2)) {
                String strB = j0.d.b(wVar);
                return (strB == null || strB.length() <= 0) ? wVar.f5646b : strB;
            }
        }
        return str;
    }

    public final void D0(int i2) {
        j0.w wVar;
        String str;
        if (i2 < 0 || i2 >= this.f2906C.size() || (wVar = (j0.w) this.f2906C.get(i2)) == null || (str = wVar.f5646b) == null || str.length() == 0) {
            return;
        }
        String strB0 = B0(wVar.f5646b);
        if (strB0.length() == 0) {
            return;
        }
        if (this.f2907D.contains(strB0)) {
            this.f2907D.remove(strB0);
        } else {
            this.f2907D.add(strB0);
        }
        this.f2909F.notifyDataSetChanged();
        E0();
    }

    public final void E0() {
        TextView textView = this.f2916y;
        if (textView != null) {
            textView.setText("已选择 " + this.f2907D.size() + " 人");
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5166s);
        this.f2914w = (ListView) a0(f0.f.N6);
        this.f2915x = (EditText) a0(f0.f.E4);
        this.f2916y = (TextView) a0(f0.f.ta);
        this.f2904A = getIntent().getStringExtra("group_id");
        this.f2917z = getSharedPreferences("auth", 0).getString("access_token", "");
        h hVar = new h(this, null);
        this.f2909F = hVar;
        this.f2914w.setAdapter((ListAdapter) hVar);
        this.f2914w.setOnItemClickListener(new a());
        EditText editText = this.f2915x;
        if (editText != null) {
            editText.addTextChangedListener(new b());
        }
        a0(f0.f.g1).setOnClickListener(new c());
        View viewA0 = a0(f0.f.h1);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new d());
        }
        E0();
        x0();
    }

    public final void u0() {
        EditText editText = this.f2915x;
        String strA0 = A0((editText == null || editText.getText() == null) ? "" : this.f2915x.getText().toString());
        this.f2906C.clear();
        for (int i2 = 0; i2 < this.f2905B.size(); i2++) {
            j0.w wVar = (j0.w) this.f2905B.get(i2);
            if (wVar != null) {
                String strB0 = B0(wVar.f5646b);
                if (strB0.length() != 0 && !this.f2908E.contains(strB0) && (strA0.length() <= 0 || z0(wVar, strA0))) {
                    this.f2906C.add(wVar);
                }
            }
        }
        this.f2909F.notifyDataSetChanged();
        E0();
    }

    public final void v0() {
        String str = this.f2904A;
        if (str == null || str.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str2 : this.f2907D) {
            if (str2 != null && str2.length() > 0) {
                arrayList.add(str2);
            }
        }
        if (arrayList.isEmpty()) {
            Toast.makeText(this, "请选择成员", 0).show();
            return;
        }
        this.f2910G = 0;
        this.f2911H = 0;
        this.f2912I = 0;
        this.f2913J.clear();
        w0(arrayList, 0);
    }

    public final void w0(List list, int i2) {
        if (i2 < list.size()) {
            String str = (String) list.get(i2);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("group_id", this.f2904A);
                jSONObject.put("user_uid", str);
                g0.d.S("/groups/invite", jSONObject, this.f2917z, new g(list, i2, str));
                return;
            } catch (Exception unused) {
                this.f2912I++;
                w0(list, i2 + 1);
                return;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("邀请完成：成功");
        sb.append(this.f2910G);
        sb.append("人");
        if (this.f2911H > 0) {
            sb.append("，已在群");
            sb.append(this.f2911H);
            sb.append("人");
        }
        if (this.f2912I > 0) {
            sb.append("，失败");
            sb.append(this.f2912I);
            sb.append("人");
        }
        Toast.makeText(this, sb.toString(), 1).show();
        if (this.f2910G > 0) {
            finish();
        }
    }

    public final void x0() {
        String str = this.f2904A;
        if (str == null || str.length() == 0) {
            y0();
            return;
        }
        g0.d.R("/groups/members?group_id=" + F0(this.f2904A), this.f2917z, new e());
    }

    public final boolean z0(j0.w wVar, String str) {
        return A0(j0.d.b(wVar)).contains(str) || A0(wVar.f5646b).contains(str) || A0(wVar.f5647c).contains(str) || A0(wVar.f5648d).contains(str) || A0(wVar.f5649e).contains(str);
    }
}

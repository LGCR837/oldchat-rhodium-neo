package com.im.oldchat.ui;

import android.R;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import c.AbstractC0121a;
import com.im.oldchat.ui.AbstractC0227s;
import d.AbstractC0235a;
import g0.d;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import k.AbstractC0285k0;
import l0.AbstractC0400q;
import o.c;
import o0.AbstractC0449o;
import o0.AbstractC0455v;
import org.json.JSONArray;
import org.json.JSONObject;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: loaded from: classes.dex */
public class EmojiPlazaActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public TextView f2691A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public TextView f2692B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public TextView f2693C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public TextView f2694D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public TextView f2695E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public ListView f2696F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public String f2697G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public String f2698H;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public I f2701K;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public int f2707Q;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public EditText f2708w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public TextView f2709x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f2710y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public TextView f2711z;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public boolean f2699I = false;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final List f2700J = new ArrayList();

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public int f2702L = 0;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public int f2703M = 0;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public boolean f2704N = false;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public boolean f2705O = false;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public int f2706P = 0;

    public class A implements View.OnClickListener {
        public A() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EmojiPlazaActivity.this.E1();
        }
    }

    public class B implements View.OnClickListener {
        public B() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (EmojiPlazaActivity.this.f2699I) {
                EmojiPlazaActivity.this.f2699I = false;
                EmojiPlazaActivity.this.f2702L = 0;
                EmojiPlazaActivity.this.W0();
                EmojiPlazaActivity.this.i1();
            }
        }
    }

    public class C implements View.OnClickListener {
        public C() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (EmojiPlazaActivity.this.f2699I) {
                return;
            }
            EmojiPlazaActivity.this.f2699I = true;
            EmojiPlazaActivity.this.f2702L = 0;
            EmojiPlazaActivity.this.W0();
            EmojiPlazaActivity.this.i1();
        }
    }

    public class D implements TextView.OnEditorActionListener {
        public D() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
            if (i2 == 3) {
                EmojiPlazaActivity.this.f2702L = 0;
                EmojiPlazaActivity.this.i1();
                return true;
            }
            if (keyEvent == null || keyEvent.getAction() != 0 || keyEvent.getKeyCode() != 66) {
                return false;
            }
            EmojiPlazaActivity.this.f2702L = 0;
            EmojiPlazaActivity.this.i1();
            return true;
        }
    }

    public class E implements View.OnClickListener {
        public E() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!EmojiPlazaActivity.this.f2705O && EmojiPlazaActivity.this.f2702L > 0) {
                EmojiPlazaActivity.this.f2702L = Math.max(0, r3.f2702L - 50);
                EmojiPlazaActivity.this.i1();
            }
        }
    }

    public class F implements View.OnClickListener {
        public F() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!EmojiPlazaActivity.this.f2705O && EmojiPlazaActivity.this.f2704N) {
                if (EmojiPlazaActivity.this.f2703M <= 0 || EmojiPlazaActivity.this.f2702L + 50 < EmojiPlazaActivity.this.f2703M) {
                    EmojiPlazaActivity.F0(EmojiPlazaActivity.this, 50);
                    EmojiPlazaActivity.this.i1();
                }
            }
        }
    }

    public class G extends BaseAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List f2718a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean[] f2719b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final boolean f2720c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2721d;

        public G(List list, boolean[] zArr, boolean z2, int i2) {
            this.f2718a = list;
            this.f2719b = zArr;
            this.f2720c = z2;
            this.f2721d = i2;
        }

        public void a(int i2) {
            this.f2721d = i2;
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            List list = this.f2718a;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            List list = this.f2718a;
            if (list == null || i2 < 0 || i2 >= list.size()) {
                return null;
            }
            return this.f2718a.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            H h2;
            boolean[] zArr;
            if (view == null) {
                view = LayoutInflater.from(EmojiPlazaActivity.this).inflate(f0.g.t0, viewGroup, false);
                h2 = new H(view);
                view.setTag(h2);
            } else {
                h2 = (H) view.getTag();
            }
            AbstractC0227s.c cVar = (AbstractC0227s.c) getItem(i2);
            if (cVar != null) {
                EmojiPlazaActivity emojiPlazaActivity = EmojiPlazaActivity.this;
                AbstractC0400q.f(emojiPlazaActivity, h2.f2723a, cVar.f4744b, emojiPlazaActivity.f2707Q);
            } else {
                h2.f2723a.setTag(null);
                h2.f2723a.setImageResource(f0.e.f4999T);
            }
            boolean z2 = true;
            if (!this.f2720c ? i2 != this.f2721d : (zArr = this.f2719b) == null || i2 < 0 || i2 >= zArr.length || !zArr[i2]) {
                z2 = false;
            }
            h2.f2724b.setVisibility(z2 ? 0 : 8);
            h2.f2725c.setVisibility(z2 ? 0 : 8);
            return view;
        }
    }

    public static class H {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ImageView f2723a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final View f2724b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final TextView f2725c;

        public H(View view) {
            this.f2723a = (ImageView) view.findViewById(f0.f.w5);
            this.f2724b = view.findViewById(f0.f.ue);
            this.f2725c = (TextView) view.findViewById(f0.f.B9);
        }
    }

    public class I extends BaseAdapter {

        public class a implements View.OnClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ J f2727a;

            public a(J j2) {
                this.f2727a = j2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EmojiPlazaActivity.this.a1(this.f2727a);
            }
        }

        public class b implements View.OnClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ J f2729a;

            public b(J j2) {
                this.f2729a = j2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EmojiPlazaActivity.this.Z0(this.f2729a);
            }
        }

        public class c implements View.OnLongClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ J f2731a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ boolean f2732b;

            public c(J j2, boolean z2) {
                this.f2731a = j2;
                this.f2732b = z2;
            }

            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                EmojiPlazaActivity.this.D1(this.f2731a, this.f2732b);
                return true;
            }
        }

        public I() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return EmojiPlazaActivity.this.f2700J.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            if (i2 < 0 || i2 >= EmojiPlazaActivity.this.f2700J.size()) {
                return null;
            }
            return EmojiPlazaActivity.this.f2700J.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            K k2;
            String str;
            if (view == null) {
                view = LayoutInflater.from(EmojiPlazaActivity.this).inflate(f0.g.u0, viewGroup, false);
                k2 = new K(view);
                view.setTag(k2);
            } else {
                k2 = (K) view.getTag();
            }
            J j2 = (J) getItem(i2);
            if (j2 == null) {
                return view;
            }
            TextView textView = k2.f2746b;
            String str2 = j2.f2735b;
            textView.setText((str2 == null || str2.length() == 0) ? "未命名表情" : j2.f2735b);
            String str3 = j2.f2743j;
            if (str3 == null || str3.length() == 0) {
                str3 = j2.f2742i;
            }
            if (str3 == null || str3.length() == 0) {
                str3 = "匿名用户";
            }
            String str4 = j2.f2744k;
            String strTrim = str4 == null ? "" : str4.trim();
            if (strTrim.length() > 0) {
                k2.f2747c.setText("上传者: " + str3 + " · " + strTrim);
            } else {
                k2.f2747c.setText("上传者: " + str3);
            }
            String str5 = j2.f2737d;
            if (str5 == null || str5.length() <= 0) {
                str = j2.f2740g ? "动图" : "静态";
            } else {
                str = "合集(" + Math.max(1, j2.f2739f) + "个)";
            }
            k2.f2748d.setText(str + " · " + EmojiPlazaActivity.this.f1(j2.f2741h));
            boolean z2 = EmojiPlazaActivity.this.f2699I && EmojiPlazaActivity.this.f2698H != null && EmojiPlazaActivity.this.f2698H.length() > 0 && j2.f2742i != null && EmojiPlazaActivity.this.f2698H.equalsIgnoreCase(j2.f2742i);
            k2.f2749e.setVisibility(z2 ? 8 : 0);
            k2.f2750f.setVisibility(z2 ? 0 : 8);
            k2.f2749e.setOnClickListener(new a(j2));
            k2.f2750f.setOnClickListener(new b(j2));
            view.setOnLongClickListener(new c(j2, z2));
            String str6 = j2.f2738e;
            AbstractC0455v.e(k2.f2745a, (str6 == null || str6.length() <= 0) ? j2.f2736c : j2.f2738e);
            return view;
        }

        public /* synthetic */ I(EmojiPlazaActivity emojiPlazaActivity, ViewOnClickListenerC0189k viewOnClickListenerC0189k) {
            this();
        }
    }

    public static class J {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2734a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f2735b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f2736c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f2737d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f2738e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f2739f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f2740g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public long f2741h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public String f2742i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public String f2743j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public String f2744k;

        public J() {
        }

        public /* synthetic */ J(ViewOnClickListenerC0189k viewOnClickListenerC0189k) {
            this();
        }
    }

    public static class K {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ImageView f2745a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f2746b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final TextView f2747c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final TextView f2748d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final TextView f2749e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final TextView f2750f;

        public K(View view) {
            this.f2745a = (ImageView) view.findViewById(f0.f.x5);
            this.f2746b = (TextView) view.findViewById(f0.f.G9);
            this.f2747c = (TextView) view.findViewById(f0.f.H9);
            this.f2748d = (TextView) view.findViewById(f0.f.F9);
            this.f2749e = (TextView) view.findViewById(f0.f.B0);
            this.f2750f = (TextView) view.findViewById(f0.f.y0);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.EmojiPlazaActivity$a, reason: case insensitive filesystem */
    public class C0179a implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2751a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f2752b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f2753c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f2754d;

        public C0179a(int i2, int i3, int i4, String str) {
            this.f2751a = i2;
            this.f2752b = i3;
            this.f2753c = i4;
            this.f2754d = str;
        }

        @Override // g0.d.i
        public void b(String str) {
            if (this.f2751a != EmojiPlazaActivity.this.f2706P) {
                return;
            }
            EmojiPlazaActivity.this.A1(false);
            try {
                JSONObject jSONObject = new JSONObject(str);
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("items");
                int iOptInt = jSONObject.optInt("offset", this.f2752b);
                if (iOptInt < 0) {
                    iOptInt = 0;
                }
                int i2 = this.f2752b;
                if (iOptInt != i2 && this.f2753c < 1) {
                    EmojiPlazaActivity.this.f2702L = i2;
                    EmojiPlazaActivity.this.j1(true, this.f2753c + 1);
                    return;
                }
                EmojiPlazaActivity.this.f2703M = Math.max(0, jSONObject.optInt("total", 0));
                EmojiPlazaActivity emojiPlazaActivity = EmojiPlazaActivity.this;
                int iG1 = emojiPlazaActivity.g1(emojiPlazaActivity.f2703M);
                if (iOptInt > iG1) {
                    EmojiPlazaActivity.this.f2702L = iG1;
                    EmojiPlazaActivity emojiPlazaActivity2 = EmojiPlazaActivity.this;
                    emojiPlazaActivity2.f2704N = emojiPlazaActivity2.f2702L < iG1;
                    EmojiPlazaActivity.this.H1();
                    EmojiPlazaActivity.this.j1(true, 0);
                    return;
                }
                EmojiPlazaActivity.this.f2702L = iOptInt;
                EmojiPlazaActivity.this.f2704N = jSONObject.optBoolean("has_more", false);
                EmojiPlazaActivity.this.f2700J.clear();
                if (jSONArrayOptJSONArray != null) {
                    for (int i3 = 0; i3 < jSONArrayOptJSONArray.length(); i3++) {
                        JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i3);
                        if (jSONObjectOptJSONObject != null) {
                            J j2 = new J(null);
                            j2.f2734a = jSONObjectOptJSONObject.optString("id", "");
                            j2.f2735b = jSONObjectOptJSONObject.optString("name", "");
                            j2.f2736c = jSONObjectOptJSONObject.optString("media_url", "");
                            j2.f2740g = jSONObjectOptJSONObject.optBoolean("is_gif", false);
                            j2.f2741h = jSONObjectOptJSONObject.optLong("size_bytes", 0L);
                            j2.f2742i = jSONObjectOptJSONObject.optString("owner_uid", "");
                            j2.f2743j = jSONObjectOptJSONObject.optString("owner_name", "");
                            j2.f2744k = jSONObjectOptJSONObject.optString("owner_title", "");
                            j2.f2737d = jSONObjectOptJSONObject.optString("package_url", "");
                            j2.f2738e = jSONObjectOptJSONObject.optString("cover_url", "");
                            j2.f2739f = jSONObjectOptJSONObject.optInt("item_count", 1);
                            if (j2.f2734a.length() != 0 && j2.f2736c.length() != 0) {
                                EmojiPlazaActivity.this.f2700J.add(j2);
                            }
                        }
                    }
                }
                EmojiPlazaActivity.this.f2701K.notifyDataSetChanged();
                if (EmojiPlazaActivity.this.f2692B != null) {
                    if (EmojiPlazaActivity.this.f2700J.isEmpty() && this.f2754d.length() > 0) {
                        EmojiPlazaActivity.this.f2692B.setText("没有找到相关表情");
                    } else if (EmojiPlazaActivity.this.f2700J.isEmpty()) {
                        EmojiPlazaActivity.this.f2692B.setText(EmojiPlazaActivity.this.f2699I ? "你还没有上传表情" : "暂无表情，快来上传第一个吧");
                    }
                }
                EmojiPlazaActivity.this.H1();
            } catch (Exception unused) {
                Toast.makeText(EmojiPlazaActivity.this, "解析广场数据失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (this.f2751a != EmojiPlazaActivity.this.f2706P) {
                return;
            }
            EmojiPlazaActivity.this.A1(false);
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(EmojiPlazaActivity.this, "加载失败: " + i2, 0).show();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.EmojiPlazaActivity$b, reason: case insensitive filesystem */
    public class DialogInterfaceOnClickListenerC0180b implements DialogInterface.OnClickListener {
        public DialogInterfaceOnClickListenerC0180b() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            if (i2 == 2) {
                EmojiPlazaActivity.this.B1();
            } else if (i2 == 1) {
                EmojiPlazaActivity.this.l1();
            } else {
                EmojiPlazaActivity.this.m1();
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.EmojiPlazaActivity$c, reason: case insensitive filesystem */
    public class DialogInterfaceOnClickListenerC0181c implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f2757a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Uri f2758b;

        public DialogInterfaceOnClickListenerC0181c(EditText editText, Uri uri) {
            this.f2757a = editText;
            this.f2758b = uri;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) throws Throwable {
            String strTrim = this.f2757a.getText() == null ? "" : this.f2757a.getText().toString().trim();
            if (strTrim.length() == 0) {
                Toast.makeText(EmojiPlazaActivity.this, "请填写表情名称", 0).show();
            } else {
                EmojiPlazaActivity.this.I1(this.f2758b, strTrim);
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.EmojiPlazaActivity$d, reason: case insensitive filesystem */
    public class DialogInterfaceOnClickListenerC0182d implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f2760a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f2761b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ boolean f2762c;

        public DialogInterfaceOnClickListenerC0182d(EditText editText, String str, boolean z2) {
            this.f2760a = editText;
            this.f2761b = str;
            this.f2762c = z2;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            String strTrim = this.f2760a.getText() == null ? "" : this.f2760a.getText().toString().trim();
            if (strTrim.length() == 0) {
                Toast.makeText(EmojiPlazaActivity.this, "请填写表情名称", 0).show();
            } else {
                EmojiPlazaActivity.this.J1(this.f2761b, this.f2762c, strTrim);
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.EmojiPlazaActivity$e, reason: case insensitive filesystem */
    public class C0183e implements d.k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Uri f2764a;

        public C0183e(Uri uri) {
            this.f2764a = uri;
        }

        @Override // g0.d.k
        public InputStream a() throws FileNotFoundException {
            InputStream inputStreamOpenInputStream = EmojiPlazaActivity.this.getContentResolver().openInputStream(this.f2764a);
            if (inputStreamOpenInputStream != null) {
                return inputStreamOpenInputStream;
            }
            throw new IllegalStateException("open input stream failed");
        }

        @Override // g0.d.k
        public long length() {
            return EmojiPlazaActivity.this.s1(this.f2764a);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.EmojiPlazaActivity$f, reason: case insensitive filesystem */
    public class C0184f implements d.i {
        public C0184f() {
        }

        @Override // g0.d.i
        public void b(String str) {
            Toast.makeText(EmojiPlazaActivity.this, "上传成功", 0).show();
            EmojiPlazaActivity.this.i1();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            EmojiPlazaActivity.this.h1(i2, str);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.EmojiPlazaActivity$g, reason: case insensitive filesystem */
    public class C0185g implements d.k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ File f2767a;

        public C0185g(File file) {
            this.f2767a = file;
        }

        @Override // g0.d.k
        public InputStream a() {
            return new FileInputStream(this.f2767a);
        }

        @Override // g0.d.k
        public long length() {
            return this.f2767a.length();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.EmojiPlazaActivity$h, reason: case insensitive filesystem */
    public class C0186h implements d.i {
        public C0186h() {
        }

        @Override // g0.d.i
        public void b(String str) {
            Toast.makeText(EmojiPlazaActivity.this, "上传成功", 0).show();
            EmojiPlazaActivity.this.i1();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            EmojiPlazaActivity.this.h1(i2, str);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.EmojiPlazaActivity$i, reason: case insensitive filesystem */
    public class DialogInterfaceOnClickListenerC0187i implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ J f2770a;

        public DialogInterfaceOnClickListenerC0187i(J j2) {
            this.f2770a = j2;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            EmojiPlazaActivity.this.z1(this.f2770a);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.EmojiPlazaActivity$j, reason: case insensitive filesystem */
    public class DialogInterfaceOnClickListenerC0188j implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ J f2772a;

        public DialogInterfaceOnClickListenerC0188j(J j2) {
            this.f2772a = j2;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            EmojiPlazaActivity.this.b1(this.f2772a.f2734a);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.EmojiPlazaActivity$k, reason: case insensitive filesystem */
    public class ViewOnClickListenerC0189k implements View.OnClickListener {
        public ViewOnClickListenerC0189k() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EmojiPlazaActivity.this.finish();
        }
    }

    public class l implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ArrayList f2775a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ J f2776b;

        public l(ArrayList arrayList, J j2) {
            this.f2775a = arrayList;
            this.f2776b = j2;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            if (i2 < 0 || i2 >= this.f2775a.size()) {
                return;
            }
            String str = (String) this.f2775a.get(i2);
            if (!"收藏".equals(str)) {
                if ("举报".equals(str)) {
                    EmojiPlazaActivity.this.C1(this.f2776b);
                    return;
                } else {
                    if ("删除上传".equals(str)) {
                        EmojiPlazaActivity.this.Z0(this.f2776b);
                        return;
                    }
                    return;
                }
            }
            String str2 = this.f2776b.f2743j;
            if (str2 == null || str2.length() == 0) {
                str2 = this.f2776b.f2742i;
            }
            String str3 = str2;
            EmojiPlazaActivity emojiPlazaActivity = EmojiPlazaActivity.this;
            J j2 = this.f2776b;
            AbstractC0228t.b(emojiPlazaActivity, j2.f2734a, j2.f2735b, str3, j2.f2736c, j2.f2737d);
        }
    }

    public class m implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f2778a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ J f2779b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f2780c;

        public m(EditText editText, J j2, String str) {
            this.f2778a = editText;
            this.f2779b = j2;
            this.f2780c = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            String strTrim = this.f2778a.getText() == null ? "" : this.f2778a.getText().toString().trim();
            StringBuilder sb = new StringBuilder();
            sb.append("[表情广场举报]");
            String str = this.f2779b.f2734a;
            if (str != null && str.length() > 0) {
                sb.append(" item_id=");
                sb.append(this.f2779b.f2734a);
            }
            String str2 = this.f2779b.f2735b;
            if (str2 != null && str2.length() > 0) {
                sb.append(" name=");
                sb.append(this.f2779b.f2735b);
            }
            if (strTrim.length() > 0) {
                strTrim = strTrim + "\n";
            }
            EmojiPlazaActivity.this.F1(this.f2780c, strTrim + sb.toString());
        }
    }

    public class n implements d.i {
        public n() {
        }

        @Override // g0.d.i
        public void b(String str) {
            Toast.makeText(EmojiPlazaActivity.this, "举报已提交，已进入公开法庭", 0).show();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(EmojiPlazaActivity.this, "举报失败: " + i2, 0).show();
        }
    }

    public class o implements d.i {
        public o() {
        }

        @Override // g0.d.i
        public void b(String str) {
            Toast.makeText(EmojiPlazaActivity.this, "已删除", 0).show();
            EmojiPlazaActivity.this.i1();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            if (i2 == 403) {
                Toast.makeText(EmojiPlazaActivity.this, "只能删除自己上传的表情", 0).show();
                return;
            }
            Toast.makeText(EmojiPlazaActivity.this, "删除失败: " + i2, 0).show();
        }
    }

    public class p implements AdapterView.OnItemClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean[] f2784a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ G f2785b;

        public p(boolean[] zArr, G g2) {
            this.f2784a = zArr;
            this.f2785b = g2;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            if (i2 >= 0) {
                boolean[] zArr = this.f2784a;
                if (i2 >= zArr.length) {
                    return;
                }
                zArr[i2] = !zArr[i2];
                this.f2785b.notifyDataSetChanged();
            }
        }
    }

    public class q implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f2787a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean[] f2788b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ ArrayList f2789c;

        public q(EditText editText, boolean[] zArr, ArrayList arrayList) {
            this.f2787a = editText;
            this.f2788b = zArr;
            this.f2789c = arrayList;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) throws Throwable {
            String strTrim = this.f2787a.getText() == null ? "" : this.f2787a.getText().toString().trim();
            if (strTrim.length() == 0) {
                Toast.makeText(EmojiPlazaActivity.this, "请填写表情包名称", 0).show();
                return;
            }
            ArrayList arrayList = new ArrayList();
            int i3 = 0;
            while (true) {
                boolean[] zArr = this.f2788b;
                if (i3 >= zArr.length) {
                    break;
                }
                if (zArr[i3]) {
                    arrayList.add((AbstractC0227s.c) this.f2789c.get(i3));
                }
                i3++;
            }
            if (arrayList.isEmpty()) {
                Toast.makeText(EmojiPlazaActivity.this, "请至少选择一个表情", 0).show();
            } else {
                EmojiPlazaActivity.this.n1(arrayList, strTrim);
            }
        }
    }

    public class r implements AdapterView.OnItemClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean[] f2791a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int[] f2792b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ G f2793c;

        public r(boolean[] zArr, int[] iArr, G g2) {
            this.f2791a = zArr;
            this.f2792b = iArr;
            this.f2793c = g2;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            if (i2 < 0 || i2 >= this.f2791a.length) {
                return;
            }
            int i3 = 0;
            while (true) {
                boolean[] zArr = this.f2791a;
                if (i3 >= zArr.length) {
                    this.f2792b[0] = i2;
                    this.f2793c.a(i2);
                    return;
                } else {
                    zArr[i3] = i3 == i2;
                    i3++;
                }
            }
        }
    }

    public class s implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int[] f2795a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ArrayList f2796b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f2797c;

        public s(int[] iArr, ArrayList arrayList, String str) {
            this.f2795a = iArr;
            this.f2796b = arrayList;
            this.f2797c = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) throws Throwable {
            int i3 = 0;
            int i4 = this.f2795a[0];
            if (i4 >= 0 && i4 < this.f2796b.size()) {
                i3 = i4;
            }
            EmojiPlazaActivity emojiPlazaActivity = EmojiPlazaActivity.this;
            ArrayList arrayList = this.f2796b;
            emojiPlazaActivity.K1(arrayList, this.f2797c, (AbstractC0227s.c) arrayList.get(i3));
        }
    }

    public class t implements d.k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ File f2799a;

        public t(File file) {
            this.f2799a = file;
        }

        @Override // g0.d.k
        public InputStream a() {
            return new FileInputStream(this.f2799a);
        }

        @Override // g0.d.k
        public long length() {
            return this.f2799a.length();
        }
    }

    public class u implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ File f2801a;

        public u(File file) {
            this.f2801a = file;
        }

        @Override // g0.d.i
        public void b(String str) {
            this.f2801a.delete();
            Toast.makeText(EmojiPlazaActivity.this, "上传成功", 0).show();
            EmojiPlazaActivity.this.f2702L = 0;
            EmojiPlazaActivity.this.i1();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            this.f2801a.delete();
            if (g0.e.v(i2, str)) {
                return;
            }
            if (i2 == 413 || (str != null && str.contains("package_too_large"))) {
                Toast.makeText(EmojiPlazaActivity.this, "表情包压缩包不能超过30MB", 0).show();
                return;
            }
            if (i2 == 400 && str != null && str.contains("invalid_package")) {
                Toast.makeText(EmojiPlazaActivity.this, "压缩包内容无效，需包含图片/动图", 0).show();
                return;
            }
            Toast.makeText(EmojiPlazaActivity.this, "上传失败: " + i2, 0).show();
        }
    }

    public class v implements AdapterView.OnItemClickListener {
        public v() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            if (i2 < 0 || i2 >= EmojiPlazaActivity.this.f2700J.size()) {
                return;
            }
            EmojiPlazaActivity emojiPlazaActivity = EmojiPlazaActivity.this;
            emojiPlazaActivity.a1((J) emojiPlazaActivity.f2700J.get(i2));
        }
    }

    public class w implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ J f2804a;

        public w(J j2) {
            this.f2804a = j2;
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String strOptString = jSONObject.optString("package_url", this.f2804a.f2737d);
                if (strOptString != null && strOptString.length() > 0) {
                    EmojiPlazaActivity.this.y1(strOptString);
                    return;
                }
                EmojiPlazaActivity.this.x1(jSONObject.optString("media_url", this.f2804a.f2736c), jSONObject.optBoolean("is_gif", this.f2804a.f2740g));
            } catch (Exception unused) {
                String str2 = this.f2804a.f2737d;
                if (str2 != null && str2.length() > 0) {
                    EmojiPlazaActivity.this.y1(this.f2804a.f2737d);
                    return;
                }
                EmojiPlazaActivity emojiPlazaActivity = EmojiPlazaActivity.this;
                J j2 = this.f2804a;
                emojiPlazaActivity.x1(j2.f2736c, j2.f2740g);
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            String str2 = this.f2804a.f2737d;
            if (str2 != null && str2.length() > 0) {
                EmojiPlazaActivity.this.y1(this.f2804a.f2737d);
                return;
            }
            EmojiPlazaActivity emojiPlazaActivity = EmojiPlazaActivity.this;
            J j2 = this.f2804a;
            emojiPlazaActivity.x1(j2.f2736c, j2.f2740g);
        }
    }

    public class x implements AbstractC0227s.d {
        public x() {
        }

        @Override // com.im.oldchat.ui.AbstractC0227s.d
        public void a(boolean z2, String str) {
            if (str == null || str.length() == 0) {
                str = z2 ? "已保存到表情包" : "保存失败";
            }
            Toast.makeText(EmojiPlazaActivity.this, str, 0).show();
        }
    }

    public class y implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2807a;

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(EmojiPlazaActivity.this, "下载表情包合集失败", 0).show();
            }
        }

        public class b implements AbstractC0449o.d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ File f2810a;

            public b(File file) {
                this.f2810a = file;
            }

            @Override // o0.AbstractC0449o.d
            public void a(String str) {
                if (this.f2810a.exists()) {
                    this.f2810a.delete();
                }
                EmojiPlazaActivity emojiPlazaActivity = EmojiPlazaActivity.this;
                if (str == null) {
                    str = "导入表情包失败";
                }
                Toast.makeText(emojiPlazaActivity, str, 0).show();
            }

            @Override // o0.AbstractC0449o.d
            public void b(String str, int i2) {
                if (this.f2810a.exists()) {
                    this.f2810a.delete();
                }
                EmojiPlazaActivity emojiPlazaActivity = EmojiPlazaActivity.this;
                if (str == null) {
                    str = "已导入 " + i2 + " 个表情";
                }
                Toast.makeText(emojiPlazaActivity, str, 0).show();
            }
        }

        public y(String str) {
            this.f2807a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            File file = new File(EmojiPlazaActivity.this.getCacheDir(), "emoji_pkg_" + System.currentTimeMillis() + ".zip");
            if (EmojiPlazaActivity.this.c1(this.f2807a, file, 31457280L)) {
                AbstractC0449o.f(EmojiPlazaActivity.this, Uri.fromFile(file), new b(file));
            } else {
                if (file.exists()) {
                    file.delete();
                }
                EmojiPlazaActivity.this.runOnUiThread(new a());
            }
        }
    }

    public class z implements View.OnClickListener {
        public z() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EmojiPlazaActivity.this.f2702L = 0;
            EmojiPlazaActivity.this.i1();
        }
    }

    public static /* synthetic */ int F0(EmojiPlazaActivity emojiPlazaActivity, int i2) {
        int i3 = emojiPlazaActivity.f2702L + i2;
        emojiPlazaActivity.f2702L = i3;
        return i3;
    }

    private String L1(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception unused) {
            return str;
        }
    }

    private int d1(int i2) {
        return (int) ((i2 * getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h1(int i2, String str) {
        if (g0.e.v(i2, str)) {
            return;
        }
        String lowerCase = str == null ? "" : str.toLowerCase(Locale.US);
        if (i2 == 403 || lowerCase.contains("permission denied") || lowerCase.contains("eacces")) {
            Toast.makeText(this, "无权限读取所选文件，请在系统设置中开启存储权限后重试", 0).show();
            return;
        }
        if (i2 == 409 || (str != null && str.contains("duplicate_emoji"))) {
            Toast.makeText(this, "你已经上传过同一个表情", 0).show();
            return;
        }
        if (i2 == 413 || (str != null && str.contains("package_too_large"))) {
            Toast.makeText(this, "表情包压缩包不能超过30MB", 0).show();
            return;
        }
        if (i2 == 413 || (str != null && str.contains("image_too_large"))) {
            Toast.makeText(this, "表情包不能超过3MB", 0).show();
            return;
        }
        if (i2 == 400 && str != null && str.contains("invalid_package")) {
            Toast.makeText(this, "压缩包内容无效，需包含图片/动图", 0).show();
            return;
        }
        if (i2 == 400 && str != null && str.contains("missing_cover")) {
            Toast.makeText(this, "请设置封面后再上传", 0).show();
            return;
        }
        if (i2 == 400 && str != null && str.contains("invalid_name")) {
            Toast.makeText(this, "表情名称无效", 0).show();
            return;
        }
        Toast.makeText(this, "上传失败: " + i2, 0).show();
    }

    public final void A1(boolean z2) {
        this.f2705O = z2;
        H1();
    }

    public final void B1() {
        List listV = AbstractC0227s.v(this);
        if (listV == null || listV.isEmpty()) {
            Toast.makeText(this, "你的表情包为空", 0).show();
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < listV.size(); i2++) {
            AbstractC0227s.c cVar = (AbstractC0227s.c) listV.get(i2);
            if (cVar != null && AbstractC0227s.u(cVar.f4744b)) {
                arrayList.add(cVar);
            }
        }
        if (arrayList.isEmpty()) {
            Toast.makeText(this, "没有可上传的本地表情", 0).show();
            return;
        }
        boolean[] zArr = new boolean[arrayList.size()];
        EditText editText = new EditText(this);
        editText.setHint("请输入表情包名称");
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(32)});
        editText.setText("我的表情包");
        editText.setSelection(editText.getText().length());
        TextView textView = new TextView(this);
        textView.setText("封面使用你勾选列表中的第一个表情");
        textView.setTextSize(12.0f);
        textView.setTextColor(getResources().getColor(f0.d.f4974k));
        textView.setPadding(6, 8, 6, 4);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(18, 8, 18, 0);
        linearLayout.addView(editText);
        linearLayout.addView(textView);
        GridView gridView = new GridView(this);
        gridView.setNumColumns(4);
        gridView.setVerticalSpacing(d1(8));
        gridView.setHorizontalSpacing(d1(8));
        gridView.setStretchMode(2);
        gridView.setSelector(R.color.transparent);
        linearLayout.addView(gridView, new LinearLayout.LayoutParams(-1, d1(300)));
        G g2 = new G(arrayList, zArr, true, 0);
        gridView.setAdapter((ListAdapter) g2);
        gridView.setOnItemClickListener(new p(zArr, g2));
        new c.a(this).t("选择要打包上传的表情").u(linearLayout).k("取消", null).q("打包上传", new q(editText, zArr, arrayList)).v();
    }

    public final void C1(J j2) {
        if (j2 == null) {
            return;
        }
        String str = j2.f2742i;
        String strTrim = str == null ? "" : str.trim();
        if (strTrim.length() == 0) {
            Toast.makeText(this, "无法举报：缺少上传者信息", 0).show();
            return;
        }
        String str2 = this.f2698H;
        if (str2 != null && str2.length() > 0 && this.f2698H.equalsIgnoreCase(strTrim)) {
            Toast.makeText(this, "不能举报自己上传的表情", 0).show();
            return;
        }
        EditText editText = new EditText(this);
        editText.setHint("如：违规内容、引战、广告等");
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256)});
        int iD1 = d1(10);
        editText.setPadding(iD1, iD1, iD1, iD1);
        new c.a(this).t("举报表情").u(editText).k("取消", null).q("提交", new m(editText, j2, strTrim)).v();
    }

    public final void D1(J j2, boolean z2) {
        if (j2 == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("收藏");
        arrayList.add("举报");
        if (z2) {
            arrayList.add("删除上传");
        }
        CharSequence[] charSequenceArr = (CharSequence[]) arrayList.toArray(new CharSequence[arrayList.size()]);
        c.a aVar = new c.a(this);
        String str = j2.f2735b;
        aVar.t((str == null || str.length() == 0) ? "表情操作" : j2.f2735b).g(charSequenceArr, new l(arrayList, j2)).k("取消", null).v();
    }

    public final void E1() {
        new c.a(this).t("选择上传来源").g(new String[]{"从相册选择", "从我的表情包选择", "从我的表情包多选打包上传"}, new DialogInterfaceOnClickListenerC0180b()).v();
    }

    public final void F1(String str, String str2) {
        if (str == null || str.length() == 0) {
            Toast.makeText(this, "举报失败：缺少目标用户", 0).show();
            return;
        }
        String str3 = this.f2697G;
        if (str3 == null || str3.length() == 0) {
            Toast.makeText(this, getString(f0.j.f5190N), 0).show();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("target_uid", str);
            if (str2 == null) {
                str2 = "";
            }
            jSONObject.put("reason", str2);
            g0.d.S("/reports/user", jSONObject, this.f2697G, new n());
        } catch (Exception unused) {
            Toast.makeText(this, "举报失败", 0).show();
        }
    }

    public final void G1(Uri uri, Intent intent) {
        int flags;
        if (uri == null || intent == null || Build.VERSION.SDK_INT < 19 || (flags = intent.getFlags() & 1) == 0) {
            return;
        }
        try {
            getContentResolver().takePersistableUriPermission(uri, flags);
        } catch (Exception unused) {
        }
    }

    public final void H1() {
        int i2 = (this.f2702L / 50) + 1;
        int i3 = this.f2703M;
        int i4 = i3 <= 0 ? 1 : ((i3 - 1) / 50) + 1;
        TextView textView = this.f2693C;
        if (textView != null) {
            textView.setText("第 " + i2 + " / " + i4 + " 页 · 共 " + this.f2703M + " 项");
        }
        int i5 = this.f2702L;
        boolean z2 = i5 > 0 && !this.f2705O;
        boolean z3 = this.f2704N && !this.f2705O;
        int i6 = this.f2703M;
        boolean z4 = (i6 <= 0 || i5 + 50 < i6) ? z3 : false;
        TextView textView2 = this.f2694D;
        if (textView2 != null) {
            textView2.setEnabled(z2);
            AbstractC0285k0.K(this.f2694D, z2 ? 1.0f : 0.5f);
        }
        TextView textView3 = this.f2695E;
        if (textView3 != null) {
            textView3.setEnabled(z4);
            AbstractC0285k0.K(this.f2695E, z4 ? 1.0f : 0.5f);
        }
    }

    public final void I1(Uri uri, String str) throws Throwable {
        if (uri == null) {
            return;
        }
        String str2 = this.f2697G;
        if (str2 == null || str2.length() == 0) {
            Toast.makeText(this, getString(f0.j.f5190N), 0).show();
            return;
        }
        String strT1 = t1(uri);
        String strU1 = u1(uri, strT1);
        if (strU1 == null || !strU1.startsWith("image/")) {
            Toast.makeText(this, "仅支持图片/动图", 0).show();
        } else if (s1(uri) > 3145728) {
            Toast.makeText(this, "表情包不能超过3MB", 0).show();
        } else {
            Toast.makeText(this, "正在上传...", 0).show();
            g0.d.U("/emoji/plaza/upload", new C0183e(uri), strT1, strU1, this.f2697G, "name", str, null, new C0184f());
        }
    }

    public final void J1(String str, boolean z2, String str2) {
        if (str == null || str.length() == 0) {
            return;
        }
        String str3 = this.f2697G;
        if (str3 == null || str3.length() == 0) {
            Toast.makeText(this, getString(f0.j.f5190N), 0).show();
            return;
        }
        File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            Toast.makeText(this, "本地表情文件不存在", 0).show();
            return;
        }
        if (file.length() > 3145728) {
            Toast.makeText(this, "表情包不能超过3MB", 0).show();
            return;
        }
        String name = file.getName();
        String strV1 = v1(name, z2);
        Toast.makeText(this, "正在上传...", 0).show();
        g0.d.U("/emoji/plaza/upload", new C0185g(file), name, strV1, this.f2697G, "name", str2, null, new C0186h());
    }

    public final void K1(ArrayList arrayList, String str, AbstractC0227s.c cVar) throws Throwable {
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        File fileY0 = Y0(arrayList, str);
        if (fileY0 == null || !fileY0.exists()) {
            Toast.makeText(this, "打包失败", 0).show();
            return;
        }
        if (fileY0.length() > 31457280) {
            fileY0.delete();
            Toast.makeText(this, "表情包压缩包不能超过30MB", 0).show();
            return;
        }
        AbstractC0227s.c cVar2 = cVar == null ? (AbstractC0227s.c) arrayList.get(0) : cVar;
        File file = new File(cVar2.f4744b);
        byte[] bArrQ1 = q1(file, 3145728L);
        if (bArrQ1 == null || bArrQ1.length == 0) {
            fileY0.delete();
            Toast.makeText(this, "封面读取失败", 0).show();
            return;
        }
        String strV1 = cVar2.f4745c ? "image/gif" : v1(file.getName(), false);
        if (strV1 == null || strV1.length() == 0) {
            strV1 = "image/jpeg";
        }
        String str2 = strV1;
        Toast.makeText(this, "正在上传表情包...", 0).show();
        g0.d.V("/emoji/plaza/upload?name=" + L1(str) + "&item_count=" + arrayList.size(), new t(fileY0), fileY0.getName(), "application/zip", bArrQ1, file.getName(), str2, this.f2697G, null, new u(fileY0));
    }

    public final void W0() {
        TextView textView = this.f2711z;
        if (textView != null) {
            textView.setBackgroundResource(this.f2699I ? f0.e.f4995P : f0.e.f4980A);
            this.f2711z.setTextColor(getResources().getColor(this.f2699I ? f0.d.f4973j : f0.d.f4970g));
        }
        TextView textView2 = this.f2691A;
        if (textView2 != null) {
            textView2.setBackgroundResource(this.f2699I ? f0.e.f4980A : f0.e.f4995P);
            this.f2691A.setTextColor(getResources().getColor(this.f2699I ? f0.d.f4970g : f0.d.f4973j));
        }
    }

    public final String X0(String str) {
        String strTrim = str == null ? "" : str.trim();
        if (strTrim.length() == 0) {
            return "我的表情";
        }
        int iLastIndexOf = strTrim.lastIndexOf(46);
        if (iLastIndexOf > 0) {
            strTrim = strTrim.substring(0, iLastIndexOf);
        }
        String str2 = strTrim.length() != 0 ? strTrim : "我的表情";
        return str2.length() > 32 ? str2.substring(0, 32) : str2;
    }

    public final File Y0(ArrayList arrayList, String str) throws Throwable {
        ZipOutputStream zipOutputStream;
        ZipOutputStream zipOutputStream2;
        String str2;
        FileInputStream fileInputStream;
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        String strTrim = str == null ? "emoji_package" : str.trim();
        String strReplaceAll = (strTrim.length() != 0 ? strTrim : "emoji_package").replaceAll("[^0-9a-zA-Z_\\-\\u4e00-\\u9fa5]", "_");
        if (strReplaceAll.length() > 24) {
            strReplaceAll = strReplaceAll.substring(0, 24);
        }
        File file = new File(getCacheDir(), strReplaceAll + "_" + System.currentTimeMillis() + ".zip");
        try {
            zipOutputStream2 = new ZipOutputStream(new FileOutputStream(file));
        } catch (Exception unused) {
            zipOutputStream2 = null;
        } catch (Throwable th) {
            th = th;
            zipOutputStream = null;
        }
        try {
            try {
                byte[] bArr = new byte[PKIFailureInfo.certRevoked];
                int i2 = 0;
                long j2 = 0;
                for (int i3 = 0; i3 < arrayList.size(); i3++) {
                    AbstractC0227s.c cVar = (AbstractC0227s.c) arrayList.get(i3);
                    if (cVar != null && (str2 = cVar.f4744b) != null && str2.length() != 0) {
                        File file2 = new File(cVar.f4744b);
                        if (file2.exists() && file2.isFile()) {
                            i2++;
                            zipOutputStream2.putNextEntry(new ZipEntry(String.format(Locale.US, "%03d%s", Integer.valueOf(i2), AbstractC0227s.y(cVar.f4744b, cVar.f4745c))));
                            try {
                                fileInputStream = new FileInputStream(file2);
                                do {
                                    try {
                                        int i4 = fileInputStream.read(bArr);
                                        if (i4 != -1) {
                                            zipOutputStream2.write(bArr, 0, i4);
                                            j2 += (long) i4;
                                        } else {
                                            try {
                                                fileInputStream.close();
                                            } catch (Exception unused2) {
                                            }
                                            zipOutputStream2.closeEntry();
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        if (fileInputStream != null) {
                                            try {
                                                fileInputStream.close();
                                            } catch (Exception unused3) {
                                            }
                                        }
                                        throw th;
                                    }
                                } while (j2 <= 31457280);
                                fileInputStream.close();
                                try {
                                    zipOutputStream2.close();
                                } catch (Exception unused4) {
                                }
                                file.delete();
                                return null;
                            } catch (Throwable th3) {
                                th = th3;
                                fileInputStream = null;
                            }
                        }
                    }
                }
                zipOutputStream2.finish();
                if (i2 <= 0 || file.length() <= 0) {
                    try {
                        zipOutputStream2.close();
                    } catch (Exception unused5) {
                    }
                    file.delete();
                    return null;
                }
                try {
                    zipOutputStream2.close();
                } catch (Exception unused6) {
                }
                if (!file.exists() || file.length() <= 0) {
                    file.delete();
                }
                return file;
            } catch (Throwable th4) {
                th = th4;
                zipOutputStream = zipOutputStream2;
                if (zipOutputStream != null) {
                    try {
                        zipOutputStream.close();
                    } catch (Exception unused7) {
                    }
                }
                file.delete();
                throw th;
            }
        } catch (Exception unused8) {
            if (zipOutputStream2 != null) {
                try {
                    zipOutputStream2.close();
                } catch (Exception unused9) {
                }
            }
            file.delete();
            return null;
        }
    }

    public final void Z0(J j2) {
        String str;
        if (j2 == null || (str = j2.f2734a) == null || str.length() == 0) {
            return;
        }
        new c.a(this).t("删除上传").i("确定删除你上传的“" + j2.f2735b + "”？").k("取消", null).q("删除", new DialogInterfaceOnClickListenerC0188j(j2)).v();
    }

    public final void a1(J j2) {
        if (j2 == null) {
            return;
        }
        new c.a(this).t("保存表情").i("保存“" + j2.f2735b + "”到我的表情包？").k("取消", null).q("保存", new DialogInterfaceOnClickListenerC0187i(j2)).v();
    }

    public final void b1(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        String str2 = this.f2697G;
        if (str2 == null || str2.length() == 0) {
            Toast.makeText(this, getString(f0.j.f5190N), 0).show();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("item_id", str);
            g0.d.S("/emoji/plaza/delete", jSONObject, this.f2697G, new o());
        } catch (Exception unused) {
            Toast.makeText(this, "删除失败", 0).show();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x00e1, code lost:
    
        r5.flush();
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00e6, code lost:
    
        if (r6 <= 0) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00e8, code lost:
    
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00e9, code lost:
    
        r4.close();
     */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0139 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0140 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean c1(java.lang.String r12, java.io.File r13, long r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 348
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.im.oldchat.ui.EmojiPlazaActivity.c1(java.lang.String, java.io.File, long):boolean");
    }

    public final boolean e1() {
        String strW1 = w1();
        if (strW1 == null || strW1.length() == 0 || AbstractC0235a.a(this, strW1) == 0) {
            return true;
        }
        AbstractC0121a.i(this, new String[]{strW1}, 4203);
        return false;
    }

    public final String f1(long j2) {
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

    public final int g1(int i2) {
        if (i2 <= 0) {
            return 0;
        }
        return ((i2 - 1) / 50) * 50;
    }

    public final void i1() {
        j1(false, 0);
    }

    public final void j1(boolean z2, int i2) {
        String str = this.f2697G;
        if (str == null || str.length() == 0) {
            Toast.makeText(this, getString(f0.j.f5190N), 0).show();
            return;
        }
        int i3 = this.f2706P + 1;
        this.f2706P = i3;
        int i4 = this.f2702L;
        EditText editText = this.f2708w;
        String strTrim = (editText == null || editText.getText() == null) ? "" : this.f2708w.getText().toString().trim();
        StringBuilder sb = new StringBuilder(this.f2699I ? "/emoji/plaza/mine" : "/emoji/plaza");
        sb.append("?limit=");
        sb.append(50);
        sb.append("&offset=");
        sb.append(i4);
        if (strTrim.length() > 0) {
            sb.append("&q=");
            sb.append(L1(strTrim));
        }
        if (z2) {
            sb.append("&nonce=");
            sb.append(System.currentTimeMillis());
        }
        A1(true);
        g0.d.R(sb.toString(), this.f2697G, new C0179a(i3, i4, i2, strTrim));
    }

    public final void k1() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.addFlags(1);
        startActivityForResult(Intent.createChooser(intent, "选择要上传的表情"), 4201);
    }

    public final void l1() {
        startActivityForResult(new Intent(this, (Class<?>) EmojiPickerActivity.class), 4202);
    }

    public final void m1() {
        if (e1()) {
            k1();
        }
    }

    public final void n1(ArrayList arrayList, String str) throws Throwable {
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() == 1) {
            K1(arrayList, str, (AbstractC0227s.c) arrayList.get(0));
            return;
        }
        boolean[] zArr = new boolean[arrayList.size()];
        zArr[0] = true;
        int[] iArr = {0};
        GridView gridView = new GridView(this);
        gridView.setNumColumns(4);
        gridView.setVerticalSpacing(d1(8));
        gridView.setHorizontalSpacing(d1(8));
        gridView.setStretchMode(2);
        gridView.setSelector(R.color.transparent);
        G g2 = new G(arrayList, zArr, false, 0);
        gridView.setAdapter((ListAdapter) g2);
        gridView.setOnItemClickListener(new r(zArr, iArr, g2));
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(18, 8, 18, 0);
        TextView textView = new TextView(this);
        textView.setText("请选择封面表情");
        textView.setTextSize(12.0f);
        textView.setTextColor(getResources().getColor(f0.d.f4974k));
        textView.setPadding(6, 0, 6, 8);
        linearLayout.addView(textView);
        linearLayout.addView(gridView, new LinearLayout.LayoutParams(-1, d1(300)));
        new c.a(this).t("选择封面").u(linearLayout).k("取消", null).q("继续上传", new s(iArr, arrayList, str)).v();
    }

    public final void o1(Uri uri) {
        if (uri == null) {
            return;
        }
        String strX0 = X0(t1(uri));
        EditText editText = new EditText(this);
        editText.setHint("请输入表情名称");
        editText.setText(strX0);
        editText.setSelection(strX0.length());
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(32)});
        new c.a(this).t("上传表情").i("给这个表情取个名字，方便别人搜索").u(editText).k("取消", null).q("上传", new DialogInterfaceOnClickListenerC0181c(editText, uri)).v();
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 4201 && i3 == -1 && intent != null) {
            Uri data = intent.getData();
            if (data != null) {
                G1(data, intent);
                o1(data);
                return;
            }
            return;
        }
        if (i2 == 4202 && i3 == -1 && intent != null) {
            String stringExtra = intent.getStringExtra("emoji_path");
            boolean booleanExtra = intent.getBooleanExtra("emoji_is_gif", false);
            if (stringExtra == null || stringExtra.length() == 0) {
                Toast.makeText(this, "未选择表情", 0).show();
            } else {
                p1(stringExtra, booleanExtra);
            }
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5157m);
        SharedPreferences sharedPreferences = getSharedPreferences("auth", 0);
        this.f2697G = sharedPreferences.getString("access_token", "");
        this.f2698H = sharedPreferences.getString("my_uid", "");
        this.f2708w = (EditText) a0(f0.f.x4);
        this.f2709x = (TextView) a0(f0.f.C0);
        this.f2710y = (TextView) a0(f0.f.F0);
        this.f2711z = (TextView) a0(f0.f.D0);
        this.f2691A = (TextView) a0(f0.f.E0);
        this.f2692B = (TextView) a0(f0.f.E9);
        this.f2693C = (TextView) a0(f0.f.I9);
        this.f2694D = (TextView) a0(f0.f.A0);
        this.f2695E = (TextView) a0(f0.f.z0);
        this.f2696F = (ListView) a0(f0.f.L6);
        this.f2707Q = d1(72);
        View viewA0 = a0(f0.f.x0);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new ViewOnClickListenerC0189k());
        }
        I i2 = new I(this, null);
        this.f2701K = i2;
        this.f2696F.setAdapter((ListAdapter) i2);
        this.f2696F.setEmptyView(this.f2692B);
        this.f2696F.setOnItemClickListener(new v());
        TextView textView = this.f2709x;
        if (textView != null) {
            textView.setOnClickListener(new z());
        }
        TextView textView2 = this.f2710y;
        if (textView2 != null) {
            textView2.setOnClickListener(new A());
        }
        TextView textView3 = this.f2711z;
        if (textView3 != null) {
            textView3.setOnClickListener(new B());
        }
        TextView textView4 = this.f2691A;
        if (textView4 != null) {
            textView4.setOnClickListener(new C());
        }
        EditText editText = this.f2708w;
        if (editText != null) {
            editText.setOnEditorActionListener(new D());
        }
        TextView textView5 = this.f2694D;
        if (textView5 != null) {
            textView5.setOnClickListener(new E());
        }
        TextView textView6 = this.f2695E;
        if (textView6 != null) {
            textView6.setOnClickListener(new F());
        }
        W0();
        i1();
    }

    @Override // f0.a, c.AbstractActivityC0167u, android.app.Activity, c.AbstractC0121a.b
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        if (i2 != 4203) {
            return;
        }
        if (iArr == null || iArr.length <= 0 || iArr[0] != 0) {
            Toast.makeText(this, "未授予媒体读取权限，无法上传表情", 0).show();
        } else {
            k1();
        }
    }

    public final void p1(String str, boolean z2) {
        if (str == null || str.length() == 0) {
            return;
        }
        String strX0 = X0(new File(str).getName());
        EditText editText = new EditText(this);
        editText.setHint("请输入表情名称");
        editText.setText(strX0);
        editText.setSelection(strX0.length());
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(32)});
        new c.a(this).t("上传表情").i("给这个表情取个名字，方便别人搜索").u(editText).k("取消", null).q("上传", new DialogInterfaceOnClickListenerC0182d(editText, str, z2)).v();
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0051, code lost:
    
        r12 = r11.toByteArray();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0055, code lost:
    
        r3.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final byte[] q1(java.io.File r11, long r12) throws java.lang.Throwable {
        /*
            r10 = this;
            r0 = 0
            if (r11 == 0) goto L82
            boolean r1 = r11.exists()
            if (r1 == 0) goto L82
            boolean r1 = r11.isFile()
            if (r1 != 0) goto L11
            goto L82
        L11:
            r1 = 0
            int r3 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r3 <= 0) goto L20
            long r3 = r11.length()
            int r5 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            if (r5 <= 0) goto L20
            return r0
        L20:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L62 java.lang.Exception -> L65
            r3.<init>(r11)     // Catch: java.lang.Throwable -> L62 java.lang.Exception -> L65
            java.io.ByteArrayOutputStream r11 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5f
            r11.<init>()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5f
            r4 = 8192(0x2000, float:1.148E-41)
            byte[] r4 = new byte[r4]     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4f
            r5 = r1
        L2f:
            int r7 = r3.read(r4)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4f
            r8 = -1
            if (r7 == r8) goto L51
            long r8 = (long) r7
            long r5 = r5 + r8
            int r8 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r8 <= 0) goto L47
            int r8 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r8 <= 0) goto L47
            r3.close()     // Catch: java.lang.Exception -> L43
        L43:
            r11.close()     // Catch: java.lang.Exception -> L46
        L46:
            return r0
        L47:
            r8 = 0
            r11.write(r4, r8, r7)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4f
            goto L2f
        L4c:
            r12 = move-exception
        L4d:
            r0 = r3
            goto L69
        L4f:
            goto L76
        L51:
            byte[] r12 = r11.toByteArray()     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4f
            r3.close()     // Catch: java.lang.Exception -> L58
        L58:
            r11.close()     // Catch: java.lang.Exception -> L5b
        L5b:
            return r12
        L5c:
            r12 = move-exception
            r11 = r0
            goto L4d
        L5f:
            r11 = r0
            goto L76
        L62:
            r12 = move-exception
            r11 = r0
            goto L69
        L65:
            r11 = r0
            r3 = r11
            goto L76
        L69:
            if (r0 == 0) goto L70
            r0.close()     // Catch: java.lang.Exception -> L6f
            goto L70
        L6f:
        L70:
            if (r11 == 0) goto L75
            r11.close()     // Catch: java.lang.Exception -> L75
        L75:
            throw r12
        L76:
            if (r3 == 0) goto L7d
            r3.close()     // Catch: java.lang.Exception -> L7c
            goto L7d
        L7c:
        L7d:
            if (r11 == 0) goto L82
            r11.close()     // Catch: java.lang.Exception -> L82
        L82:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.im.oldchat.ui.EmojiPlazaActivity.q1(java.io.File, long):byte[]");
    }

    public final String r1(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        if (str.startsWith("http://") || str.startsWith("https://")) {
            return o0.A.g(str);
        }
        if (str.startsWith("/")) {
            return o0.A.g(str);
        }
        return o0.A.g("/" + str);
    }

    public final long s1(Uri uri) {
        if (uri == null) {
            return -1L;
        }
        AssetFileDescriptor assetFileDescriptor = null;
        try {
            AssetFileDescriptor assetFileDescriptorOpenAssetFileDescriptor = getContentResolver().openAssetFileDescriptor(uri, "r");
            if (assetFileDescriptorOpenAssetFileDescriptor == null) {
                if (assetFileDescriptorOpenAssetFileDescriptor != null) {
                    try {
                        assetFileDescriptorOpenAssetFileDescriptor.close();
                    } catch (Exception unused) {
                    }
                }
                return -1L;
            }
            long length = assetFileDescriptorOpenAssetFileDescriptor.getLength();
            try {
                assetFileDescriptorOpenAssetFileDescriptor.close();
            } catch (Exception unused2) {
            }
            return length;
        } catch (Exception unused3) {
            if (0 != 0) {
                try {
                    assetFileDescriptor.close();
                } catch (Exception unused4) {
                }
            }
            return -1L;
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    assetFileDescriptor.close();
                } catch (Exception unused5) {
                }
            }
            throw th;
        }
    }

    public final String t1(Uri uri) throws Throwable {
        Cursor cursorQuery;
        String lastPathSegment;
        int columnIndex;
        if (uri == null) {
            return "emoji.jpg";
        }
        Cursor cursor = null;
        string = null;
        string = null;
        string = null;
        string = null;
        String string = null;
        try {
            cursorQuery = getContentResolver().query(uri, null, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst() && (columnIndex = cursorQuery.getColumnIndex("_display_name")) >= 0) {
                        string = cursorQuery.getString(columnIndex);
                    }
                } catch (Exception unused) {
                    if (cursorQuery != null) {
                    }
                    string = string != null ? lastPathSegment : lastPathSegment;
                    return string == null ? "emoji.jpg" : "emoji.jpg";
                } catch (Throwable th) {
                    th = th;
                    cursor = cursorQuery;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
        } catch (Exception unused2) {
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        if ((string != null || string.length() == 0) && (lastPathSegment = uri.getLastPathSegment()) != null && lastPathSegment.length() > 0) {
        }
        if (string == null && string.length() != 0) {
            return string;
        }
    }

    public final String u1(Uri uri, String str) {
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
        if (lowerCase.endsWith(".gif")) {
            return "image/gif";
        }
        if (lowerCase.endsWith(".png")) {
            return "image/png";
        }
        if (lowerCase.endsWith(".jpg")) {
            return "image/jpeg";
        }
        lowerCase.endsWith(".jpeg");
        return "image/jpeg";
    }

    public final String v1(String str, boolean z2) {
        if (z2) {
            return "image/gif";
        }
        String lowerCase = str == null ? "" : str.toLowerCase(Locale.US);
        return lowerCase.endsWith(".png") ? "image/png" : lowerCase.endsWith(".gif") ? "image/gif" : "image/jpeg";
    }

    public final String w1() {
        int i2 = Build.VERSION.SDK_INT;
        return i2 < 23 ? "" : i2 >= 33 ? "android.permission.READ_MEDIA_IMAGES" : "android.permission.READ_EXTERNAL_STORAGE";
    }

    public final void x1(String str, boolean z2) {
        if (str == null || str.length() == 0) {
            Toast.makeText(this, "表情地址无效", 0).show();
        } else {
            AbstractC0227s.F(this, str, z2, new x());
        }
    }

    public final void y1(String str) {
        if (str == null || str.length() == 0) {
            Toast.makeText(this, "表情包地址无效", 0).show();
        } else {
            Toast.makeText(this, "正在保存合集...", 0).show();
            new Thread(new y(str)).start();
        }
    }

    public final void z1(J j2) {
        String str;
        if (j2 == null || (str = j2.f2734a) == null || str.length() == 0) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("item_id", j2.f2734a);
            g0.d.S("/emoji/plaza/save", jSONObject, this.f2697G, new w(j2));
        } catch (Exception unused) {
            String str2 = j2.f2737d;
            if (str2 == null || str2.length() <= 0) {
                x1(j2.f2736c, j2.f2740g);
            } else {
                y1(j2.f2737d);
            }
        }
    }
}

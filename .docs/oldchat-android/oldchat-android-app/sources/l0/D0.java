package l0;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import g0.d;
import java.net.URLEncoder;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class D0 extends E0 {

    public class a implements d.i {

        /* JADX INFO: renamed from: l0.D0$a$a, reason: collision with other inner class name */
        public class RunnableC0050a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ String f5796a;

            public RunnableC0050a(String str) {
                this.f5796a = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                D0.this.f4506K.setText(this.f5796a);
                D0.this.f4506K.setVisibility(0);
            }
        }

        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                D0.this.f4506K.setVisibility(8);
            }
        }

        public class c implements Runnable {
            public c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                D0.this.f4506K.setVisibility(8);
            }
        }

        public a() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                long jOptLong = jSONObject.optLong("limit_bytes", 0L);
                long jOptLong2 = jSONObject.optLong("used_bytes", 0L);
                long jOptLong3 = jSONObject.optLong("remaining_bytes", 0L);
                if (jOptLong <= 0) {
                    jOptLong = 10737418240L;
                    jOptLong3 = (jOptLong2 <= 0 || jOptLong2 >= 10737418240L) ? 10737418240L : 10737418240L - jOptLong2;
                }
                D0.this.runOnUiThread(new RunnableC0050a("空间: 已用 " + com.im.oldchat.ui.e0.h0(jOptLong2) + " / " + com.im.oldchat.ui.e0.h0(jOptLong) + "  |  剩余 " + com.im.oldchat.ui.e0.h0(jOptLong3)));
            } catch (Exception unused) {
                D0.this.runOnUiThread(new b());
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            D0.this.runOnUiThread(new c());
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            D0 d02 = D0.this;
            if (d02.f4511P || !d02.f4510O) {
                return;
            }
            d02.u0();
        }
    }

    public class c implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f5801a;

        public c(boolean z2) {
            this.f5801a = z2;
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("items");
                if (!this.f5801a) {
                    D0.this.f4517z.clear();
                }
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    j0.t tVar = new j0.t();
                    tVar.f5618a = jSONObject.optString("id");
                    tVar.f5619b = jSONObject.optString("section_id");
                    tVar.f5620c = jSONObject.optString("name");
                    tVar.f5621d = jSONObject.optString("url");
                    tVar.f5622e = jSONObject.optLong("size_bytes", 0L);
                    tVar.f5623f = jSONObject.optString("uploader_uid");
                    tVar.f5624g = jSONObject.optString("uploader_name");
                    tVar.f5625h = jSONObject.optString("uploader_title");
                    tVar.f5626i = jSONObject.optString("uploader_avatar");
                    tVar.f5627j = jSONObject.optLong("created_at");
                    tVar.f5628k = jSONObject.optInt("likes", 0);
                    tVar.f5629l = jSONObject.optInt("comments", 0);
                    tVar.f5630m = jSONObject.optBoolean("liked", false);
                    tVar.f5631n = D0.this.f0(tVar);
                    D0.this.f4517z.add(tVar);
                }
                D0.this.f4510O = jSONArray.length() >= 20;
                D0.this.f4516y.notifyDataSetChanged();
            } catch (Exception unused) {
                Toast.makeText(D0.this, "加载资源失败", 0).show();
            }
            D0 d02 = D0.this;
            d02.f4511P = false;
            d02.l0(false);
            D0.this.r0();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            D0 d02 = D0.this;
            d02.f4511P = false;
            d02.l0(false);
            D0.this.r0();
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(D0.this, "加载资源失败: " + i2, 0).show();
        }
    }

    @Override // l0.E0
    public void p0() {
        if (this.f4506K == null) {
            return;
        }
        String str = this.f4496A;
        if (str == null || str.isEmpty() || !o0.G.d().f()) {
            this.f4506K.setVisibility(8);
        } else {
            g0.d.R("/me/resource-quota", this.f4496A, new a());
        }
    }

    @Override // l0.E0
    public void r0() {
        if (this.f4514w == null || this.f4501F == null) {
            return;
        }
        List list = this.f4517z;
        boolean z2 = (list == null || list.isEmpty()) ? false : true;
        if (this.f4502G != null && this.f4514w.getFooterViewsCount() == 0) {
            this.f4514w.addFooterView(this.f4502G);
        }
        if (!z2) {
            View view = this.f4502G;
            if (view != null) {
                view.setVisibility(8);
                return;
            }
            return;
        }
        View view2 = this.f4502G;
        if (view2 != null) {
            view2.setVisibility(0);
        }
        if (this.f4511P) {
            this.f4501F.setEnabled(false);
            ((TextView) this.f4501F).setText("加载中...");
        } else if (this.f4510O) {
            this.f4501F.setEnabled(true);
            ((TextView) this.f4501F).setText("加载更多");
        } else {
            this.f4501F.setEnabled(false);
            ((TextView) this.f4501F).setText("没有更多了");
        }
    }

    public void t0(boolean z2) {
        String str;
        String strEncode;
        String str2;
        String strEncode2;
        String str3 = this.f4496A;
        if (str3 == null || str3.isEmpty() || (str = this.f4498C) == null || str.isEmpty()) {
            l0(false);
            return;
        }
        if (!o0.G.d().f()) {
            l0(false);
            return;
        }
        if (z2) {
            this.f4511P = true;
        } else {
            l0(true);
        }
        try {
            strEncode = URLEncoder.encode(this.f4498C, "UTF-8");
        } catch (Exception unused) {
            strEncode = this.f4498C;
        }
        String str4 = this.f4513R;
        if (str4 == null || str4.isEmpty()) {
            str2 = "/resources/items?section_id=" + strEncode + "&limit=20&offset=" + this.f4509N;
        } else {
            try {
                strEncode2 = URLEncoder.encode(this.f4513R, "UTF-8");
            } catch (Exception unused2) {
                strEncode2 = this.f4513R;
            }
            str2 = "/resources/search?section_id=" + strEncode + "&q=" + strEncode2 + "&limit=20&offset=" + this.f4509N;
        }
        g0.d.R(str2, this.f4496A, new c(z2));
    }

    public void u0() {
        if (this.f4511P || !this.f4510O) {
            return;
        }
        this.f4509N = this.f4517z.size();
        r0();
        t0(true);
    }

    public void v0() {
        EditText editText = this.f4507L;
        if (editText != null) {
            this.f4513R = editText.getText().toString().trim();
        } else {
            this.f4513R = "";
        }
        this.f4510O = true;
        this.f4509N = 0;
        t0(false);
    }

    public void w0() {
        View viewInflate = getLayoutInflater().inflate(f0.g.c1, (ViewGroup) this.f4514w, false);
        this.f4502G = viewInflate;
        View viewFindViewById = viewInflate.findViewById(f0.f.o1);
        this.f4501F = viewFindViewById;
        viewFindViewById.setOnClickListener(new b());
        if (this.f4514w.getFooterViewsCount() == 0) {
            this.f4514w.addFooterView(this.f4502G);
        }
        r0();
    }
}

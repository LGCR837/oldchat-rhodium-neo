package l0;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.ResourceSectionActivity;
import g0.d;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import o.c;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class H0 extends f0.a {

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public String f5867B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public String f5868C;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ListView f5870w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public ProgressBar f5871x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f5872y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public F0 f5873z;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final List f5866A = new ArrayList();

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public boolean f5869D = false;

    public class a implements d.i {

        /* JADX INFO: renamed from: l0.H0$a$a, reason: collision with other inner class name */
        public class RunnableC0051a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ String f5875a;

            public RunnableC0051a(String str) {
                this.f5875a = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                H0.this.f5872y.setText(this.f5875a);
                H0.this.f5872y.setVisibility(0);
            }
        }

        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                H0.this.f5872y.setVisibility(8);
            }
        }

        public class c implements Runnable {
            public c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                H0.this.f5872y.setVisibility(8);
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
                H0.this.runOnUiThread(new RunnableC0051a("空间: 已用 " + H0.h0(jOptLong2) + " / " + H0.h0(jOptLong) + "  |  剩余 " + H0.h0(jOptLong3)));
            } catch (Exception unused) {
                H0.this.runOnUiThread(new b());
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            H0.this.runOnUiThread(new c());
        }
    }

    public class b implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5879a;

        public b(int i2) {
            this.f5879a = i2;
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("sections");
                int length = jSONArray.length();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    j0.u uVar = new j0.u();
                    uVar.f5632a = jSONObject.optString("id");
                    uVar.f5633b = jSONObject.optString("name");
                    uVar.f5634c = jSONObject.optString("owner_uid");
                    uVar.f5635d = jSONObject.optString("owner_name");
                    uVar.f5636e = jSONObject.optString("owner_title");
                    uVar.f5637f = jSONObject.optString("owner_avatar");
                    uVar.f5638g = jSONObject.optLong("created_at");
                    uVar.f5639h = jSONObject.optInt("resource_count", 0);
                    uVar.f5640i = jSONObject.optBoolean("is_owner", false);
                    H0.this.f5866A.add(uVar);
                }
                H0.this.f5873z.notifyDataSetChanged();
                if (length >= 50) {
                    H0.this.l0(this.f5879a + length);
                    return;
                }
                H0 h02 = H0.this;
                h02.f5869D = false;
                h02.n0(false);
            } catch (Exception unused) {
                H0 h03 = H0.this;
                h03.f5869D = false;
                h03.n0(false);
                Toast.makeText(H0.this, "加载失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            H0 h02 = H0.this;
            h02.f5869D = false;
            h02.n0(false);
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(H0.this, "加载失败: " + i2, 0).show();
        }
    }

    public class c implements DialogInterface.OnShowListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ o.c f5881a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ EditText f5882b;

        public class a implements View.OnClickListener {
            public a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String strTrim = c.this.f5882b.getText().toString().trim();
                if (strTrim.length() == 0) {
                    Toast.makeText(H0.this, "请输入分区名称", 0).show();
                } else if (H0.this.f0() >= 5) {
                    Toast.makeText(H0.this, "最多创建5个分区", 0).show();
                } else {
                    H0.this.g0(strTrim);
                    c.this.f5881a.dismiss();
                }
            }
        }

        public c(o.c cVar, EditText editText) {
            this.f5881a = cVar;
            this.f5882b = editText;
        }

        @Override // android.content.DialogInterface.OnShowListener
        public void onShow(DialogInterface dialogInterface) {
            this.f5881a.d(-1).setOnClickListener(new a());
        }
    }

    public class d implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ TextView f5885a;

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ String f5887a;

            public a(String str) {
                this.f5887a = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                d.this.f5885a.setText(this.f5887a);
            }
        }

        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                d.this.f5885a.setText("空间: 未知\n分区: " + H0.this.f0() + "/5");
            }
        }

        public class c implements Runnable {
            public c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                d.this.f5885a.setText("空间: 未知\n分区: " + H0.this.f0() + "/5");
            }
        }

        public d(TextView textView) {
            this.f5885a = textView;
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
                H0.this.runOnUiThread(new a("空间: 已用 " + H0.h0(jOptLong2) + " / " + H0.h0(jOptLong) + "\n剩余: " + H0.h0(jOptLong3) + "\n分区: " + H0.this.f0() + "/5"));
            } catch (Exception unused) {
                H0.this.runOnUiThread(new b());
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            H0.this.runOnUiThread(new c());
        }
    }

    public class e implements d.i {
        public e() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                j0.u uVar = new j0.u();
                uVar.f5632a = jSONObject.optString("id");
                uVar.f5633b = jSONObject.optString("name");
                uVar.f5634c = jSONObject.optString("owner_uid");
                uVar.f5635d = jSONObject.optString("owner_name");
                uVar.f5636e = jSONObject.optString("owner_title");
                uVar.f5637f = jSONObject.optString("owner_avatar");
                uVar.f5638g = jSONObject.optLong("created_at");
                uVar.f5639h = jSONObject.optInt("resource_count", 0);
                uVar.f5640i = true;
                H0.this.f5866A.add(0, uVar);
                H0.this.f5873z.notifyDataSetChanged();
                Toast.makeText(H0.this, "创建成功", 0).show();
            } catch (Exception unused) {
                Toast.makeText(H0.this, "创建失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            if (i2 == 409) {
                Toast.makeText(H0.this, "分区名已存在", 0).show();
                return;
            }
            if (i2 == 400 && str != null && str.contains("limit")) {
                Toast.makeText(H0.this, "最多创建5个分区", 0).show();
                return;
            }
            Toast.makeText(H0.this, "创建失败: " + i2, 0).show();
        }
    }

    public static String h0(long j2) {
        if (j2 <= 0) {
            return "0B";
        }
        double d2 = j2;
        Double.isNaN(d2);
        double d3 = d2 / 1024.0d;
        double d4 = d3 / 1024.0d;
        double d5 = d4 / 1024.0d;
        return d5 >= 1.0d ? String.format(Locale.US, "%.2fGB", Double.valueOf(d5)) : d4 >= 1.0d ? String.format(Locale.US, "%.2fMB", Double.valueOf(d4)) : d3 >= 1.0d ? String.format(Locale.US, "%.2fKB", Double.valueOf(d3)) : String.format(Locale.US, "%dB", Long.valueOf(j2));
    }

    public int f0() {
        List list = this.f5866A;
        if (list == null || list.isEmpty()) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.f5866A.size(); i3++) {
            j0.u uVar = (j0.u) this.f5866A.get(i3);
            if (uVar != null && uVar.f5640i) {
                i2++;
            }
        }
        return i2;
    }

    public void g0(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("name", str);
            g0.d.S("/resources/sections", jSONObject, this.f5867B, new e());
        } catch (Exception unused) {
            Toast.makeText(this, "创建失败", 0).show();
        }
    }

    public void i0() {
        if (this.f5872y == null) {
            return;
        }
        String str = this.f5867B;
        if (str == null || str.isEmpty() || !o0.G.d().f()) {
            this.f5872y.setVisibility(8);
        } else {
            g0.d.R("/me/resource-quota", this.f5867B, new a());
        }
    }

    public void j0(TextView textView) {
        if (textView == null) {
            return;
        }
        String str = this.f5867B;
        if (str != null && !str.isEmpty() && o0.G.d().f()) {
            g0.d.R("/me/resource-quota", this.f5867B, new d(textView));
            return;
        }
        textView.setText("空间: 未知\n分区: " + f0() + "/5");
    }

    public void k0() {
        String str = this.f5867B;
        if (str == null || str.isEmpty() || !o0.G.d().f()) {
            n0(false);
            return;
        }
        if (this.f5869D) {
            return;
        }
        this.f5869D = true;
        n0(true);
        this.f5866A.clear();
        this.f5873z.notifyDataSetChanged();
        l0(0);
    }

    public void l0(int i2) {
        String str = this.f5867B;
        if (str == null || str.isEmpty()) {
            this.f5869D = false;
            n0(false);
        } else {
            g0.d.R("/resources/sections?limit=50&offset=" + i2, this.f5867B, new b(i2));
        }
    }

    public void m0(j0.u uVar) {
        String str;
        if (uVar == null || (str = uVar.f5632a) == null || str.isEmpty()) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) ResourceSectionActivity.class);
        intent.putExtra("section_id", uVar.f5632a);
        intent.putExtra("section_name", uVar.f5633b);
        intent.putExtra("section_owner_uid", uVar.f5634c);
        intent.putExtra("section_owner_name", uVar.f5635d);
        startActivity(intent);
    }

    public void n0(boolean z2) {
        ProgressBar progressBar = this.f5871x;
        if (progressBar != null) {
            progressBar.setVisibility(z2 ? 0 : 8);
        }
    }

    public void o0() {
        EditText editText = new EditText(this);
        editText.setHint("分区名称");
        TextView textView = new TextView(this);
        textView.setTextColor(getResources().getColor(f0.d.f4974k));
        textView.setTextSize(12.0f);
        textView.setText("空间: 加载中...\n分区: " + f0() + "/5");
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        int i2 = (int) (getResources().getDisplayMetrics().density * 16.0f);
        int i3 = i2 / 2;
        linearLayout.setPadding(i2, i3, i2, i3);
        linearLayout.addView(textView);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = (int) (getResources().getDisplayMetrics().density * 10.0f);
        editText.setLayoutParams(layoutParams);
        linearLayout.addView(editText);
        o.c cVarA = new c.a(this).t("创建资源分区").u(linearLayout).q("创建", null).k("取消", null).a();
        cVarA.setOnShowListener(new c(cVarA, editText));
        cVarA.show();
        j0(textView);
    }
}

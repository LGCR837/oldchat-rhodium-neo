package com.im.oldchat.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.service.ResourceUploadService;
import g0.d;
import o.c;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ResourceSectionActivity extends d0 {

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ResourceSectionActivity.this.finish();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!ResourceSectionActivity.this.f4512Q && !ResourceUploadService.l()) {
                ResourceSectionActivity.this.x0();
            } else if (ResourceUploadService.m(ResourceSectionActivity.this.f4498C)) {
                ResourceSectionActivity.this.q0();
            } else {
                Toast.makeText(ResourceSectionActivity.this, "正在上传其他资源", 0).show();
            }
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ResourceSectionActivity.this.v0();
        }
    }

    public class d implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.t f4071a;

        public d(j0.t tVar) {
            this.f4071a = tVar;
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                j0.t tVar = this.f4071a;
                tVar.f5630m = jSONObject.optBoolean("liked", tVar.f5630m);
                j0.t tVar2 = this.f4071a;
                tVar2.f5628k = jSONObject.optInt("likes", tVar2.f5628k);
            } catch (Exception unused) {
            }
            ResourceSectionActivity.this.f4516y.notifyDataSetChanged();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(ResourceSectionActivity.this, "操作失败", 0).show();
        }
    }

    public class e implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.t f4073a;

        public e(j0.t tVar) {
            this.f4073a = tVar;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            ResourceSectionActivity.this.C0(this.f4073a);
        }
    }

    public class f implements DialogInterface.OnShowListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ o.c f4075a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ EditText f4076b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ j0.t f4077c;

        public class a implements View.OnClickListener {
            public a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String strTrim = f.this.f4076b.getText().toString().trim();
                if (strTrim.length() == 0) {
                    Toast.makeText(ResourceSectionActivity.this, "请输入举报理由", 0).show();
                    return;
                }
                f fVar = f.this;
                ResourceSectionActivity.this.E0(fVar.f4077c, strTrim);
                f.this.f4075a.dismiss();
            }
        }

        public f(o.c cVar, EditText editText, j0.t tVar) {
            this.f4075a = cVar;
            this.f4076b = editText;
            this.f4077c = tVar;
        }

        @Override // android.content.DialogInterface.OnShowListener
        public void onShow(DialogInterface dialogInterface) {
            this.f4075a.d(-1).setOnClickListener(new a());
        }
    }

    public class g implements d.i {
        public g() {
        }

        @Override // g0.d.i
        public void b(String str) {
            Toast.makeText(ResourceSectionActivity.this, "已提交举报", 0).show();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(ResourceSectionActivity.this, "举报失败", 0).show();
        }
    }

    public class h implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.t f4081a;

        public h(j0.t tVar) {
            this.f4081a = tVar;
        }

        @Override // g0.d.i
        public void b(String str) {
            ResourceSectionActivity.this.f4517z.remove(this.f4081a);
            ResourceSectionActivity.this.f4516y.notifyDataSetChanged();
            ResourceSectionActivity.this.r0();
            Toast.makeText(ResourceSectionActivity.this, "已删除", 0).show();
            ResourceSectionActivity.this.p0();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(ResourceSectionActivity.this, "删除失败: " + i2, 0).show();
        }
    }

    public void C0(j0.t tVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("item_id", tVar.f5618a);
            g0.d.S("/resources/items/delete", jSONObject, this.f4496A, new h(tVar));
        } catch (Exception unused) {
            Toast.makeText(this, "删除失败", 0).show();
        }
    }

    public void D0(j0.t tVar) {
        EditText editText = new EditText(this);
        editText.setHint("请输入举报理由");
        editText.setMinLines(3);
        editText.setInputType(131073);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(300)});
        FrameLayout frameLayout = new FrameLayout(this);
        int i2 = (int) (getResources().getDisplayMetrics().density * 16.0f);
        int i3 = i2 / 2;
        frameLayout.setPadding(i2, i3, i2, i3);
        frameLayout.addView(editText);
        o.c cVarA = new c.a(this).t("举报资源").u(frameLayout).q("提交", null).k("取消", null).a();
        cVarA.setOnShowListener(new f(cVarA, editText, tVar));
        cVarA.show();
    }

    public void E0(j0.t tVar, String str) {
        String str2 = this.f4496A;
        if (str2 == null || str2.isEmpty()) {
            Toast.makeText(this, "未登录", 0).show();
            return;
        }
        if (!o0.G.d().f()) {
            Toast.makeText(this, "网络不可用", 0).show();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("item_id", tVar.f5618a);
            jSONObject.put("reason", str);
            g0.d.S("/resources/report", jSONObject, this.f4496A, new g());
        } catch (Exception unused) {
            Toast.makeText(this, "举报失败", 0).show();
        }
    }

    @Override // com.im.oldchat.ui.c0.d
    public void c(j0.t tVar) {
        String str;
        if (tVar == null || (str = tVar.f5618a) == null || str.isEmpty()) {
            return;
        }
        D0(tVar);
    }

    @Override // com.im.oldchat.ui.d0, com.im.oldchat.ui.c0.d
    public /* bridge */ /* synthetic */ void f(j0.t tVar) {
        super.f(tVar);
    }

    @Override // com.im.oldchat.ui.c0.d
    public void g(j0.t tVar) {
        if (tVar == null) {
            return;
        }
        AbstractC0228t.e(this, tVar);
    }

    @Override // com.im.oldchat.ui.c0.d
    public void j(j0.t tVar) {
        String str;
        if (tVar == null || (str = tVar.f5618a) == null || str.isEmpty()) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("item_id", tVar.f5618a);
            g0.d.S(tVar.f5630m ? "/resources/unlike" : "/resources/like", jSONObject, this.f4496A, new d(tVar));
        } catch (Exception unused) {
            Toast.makeText(this, "操作失败", 0).show();
        }
    }

    @Override // com.im.oldchat.ui.c0.d
    public void m(j0.t tVar) {
        String str;
        if (tVar == null || (str = tVar.f5618a) == null || str.isEmpty()) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) ResourceCommentsActivity.class);
        intent.putExtra("item_id", tVar.f5618a);
        intent.putExtra("section_owner_uid", this.f4500E);
        startActivity(intent);
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        setContentView(f0.g.f5138c0);
        this.f4514w = (ListView) a0(f0.f.e7);
        this.f4515x = (ProgressBar) a0(f0.f.E7);
        this.f4506K = (TextView) a0(f0.f.md);
        this.f4507L = (EditText) a0(f0.f.Q4);
        this.f4508M = a0(f0.f.o3);
        this.f4503H = a0(f0.f.H6);
        this.f4504I = (ProgressBar) a0(f0.f.D7);
        this.f4505J = (TextView) a0(f0.f.ud);
        this.f4498C = getIntent().getStringExtra("section_id");
        this.f4499D = getIntent().getStringExtra("section_name");
        this.f4500E = getIntent().getStringExtra("section_owner_uid");
        TextView textView = (TextView) a0(f0.f.rd);
        if (textView != null && (str = this.f4499D) != null) {
            textView.setText(str);
        }
        View viewA0 = a0(f0.f.p3);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        View viewA02 = a0(f0.f.s3);
        if (viewA02 != null) {
            viewA02.setOnClickListener(new b());
        }
        View view = this.f4508M;
        if (view != null) {
            view.setOnClickListener(new c());
        }
        w0();
        c0 c0Var = new c0(this, this.f4517z, this);
        this.f4516y = c0Var;
        this.f4514w.setAdapter((ListAdapter) c0Var);
    }

    @Override // l0.C0, f0.a, c.AbstractActivityC0167u, android.app.Activity, c.AbstractC0121a.b
    public /* bridge */ /* synthetic */ void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("auth", 0);
        this.f4496A = sharedPreferences.getString("access_token", "");
        this.f4497B = sharedPreferences.getString("my_uid", "");
        this.f4513R = "";
        EditText editText = this.f4507L;
        if (editText != null) {
            editText.setText("");
        }
        p0();
        this.f4509N = 0;
        this.f4510O = true;
        this.f4517z.clear();
        this.f4516y.notifyDataSetChanged();
        r0();
        t0(false);
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, android.app.Activity
    public void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.im.oldchat.action.RESOURCE_UPLOAD_PROGRESS");
        intentFilter.addAction("com.im.oldchat.action.RESOURCE_UPLOAD_DONE");
        intentFilter.addAction("com.im.oldchat.action.RESOURCE_UPLOAD_ERROR");
        d.q.c(this).d(this.f5803S, intentFilter);
        q0();
    }

    @Override // o.d, c.AbstractActivityC0167u, android.app.Activity
    public void onStop() {
        d.q.c(this).f(this.f5803S);
        super.onStop();
    }

    @Override // com.im.oldchat.ui.c0.d
    public void s(j0.t tVar) {
        String str;
        if (tVar == null || (str = tVar.f5618a) == null || str.isEmpty()) {
            return;
        }
        new c.a(this).t("删除资源").i("确定删除该资源吗？").q("删除", new e(tVar)).k("取消", null).v();
    }
}

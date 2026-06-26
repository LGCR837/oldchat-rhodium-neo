package com.im.oldchat.ui;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.AbstractC0227s;
import com.im.oldchat.ui.C0214e;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/* JADX INFO: renamed from: com.im.oldchat.ui.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0216g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Activity f4545a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f4546b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final View f4547c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final View f4548d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final LinearLayout f4549e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final GridView f4550f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final List f4551g = new ArrayList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final List f4552h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List f4553i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final C0214e f4554j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f4555k;

    /* JADX INFO: renamed from: com.im.oldchat.ui.g$a */
    public class a implements C0214e.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ c f4556a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Activity f4557b;

        /* JADX INFO: renamed from: com.im.oldchat.ui.g$a$a, reason: collision with other inner class name */
        public class RunnableC0035a implements Runnable {
            public RunnableC0035a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                C0216g.this.q(false);
            }
        }

        public a(c cVar, Activity activity) {
            this.f4556a = cVar;
            this.f4557b = activity;
        }

        @Override // com.im.oldchat.ui.C0214e.f
        public void a(AbstractC0227s.c cVar) {
            String str;
            c cVar2;
            if (cVar == null || (str = cVar.f4744b) == null || str.length() == 0 || (cVar2 = this.f4556a) == null) {
                return;
            }
            cVar2.a(cVar.f4744b, cVar.f4745c);
        }

        @Override // com.im.oldchat.ui.C0214e.f
        public void b(AbstractC0227s.c cVar) {
            if (cVar != null) {
                AbstractC0215f.e(this.f4557b, cVar, C0216g.this.f4553i, C0216g.this.f4551g, new RunnableC0035a());
            }
        }

        @Override // com.im.oldchat.ui.C0214e.f
        public void c() {
            C0216g.this.r();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.g$b */
    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4560a;

        public b(String str) {
            this.f4560a = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f4560a.equals(C0216g.this.f4555k)) {
                return;
            }
            C0216g.this.f4555k = this.f4560a;
            C0216g.this.t();
            C0216g.this.i();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.g$c */
    public interface c {
        void a(String str, boolean z2);
    }

    public C0216g(Activity activity, View view, c cVar) {
        ArrayList arrayList = new ArrayList();
        this.f4552h = arrayList;
        this.f4553i = new ArrayList();
        this.f4555k = "全部";
        this.f4545a = activity;
        this.f4546b = cVar;
        this.f4547c = n(view, "action_tools_container");
        this.f4548d = n(view, "action_emoji_panel_container");
        this.f4549e = k(n(view, "action_emoji_categories"));
        this.f4550f = j(n(view, "action_emoji_grid"));
        this.f4554j = new C0214e(activity, arrayList, new a(cVar, activity));
        l();
    }

    public static GridView j(View view) {
        if (view instanceof GridView) {
            return (GridView) view;
        }
        return null;
    }

    public static LinearLayout k(View view) {
        if (view instanceof LinearLayout) {
            return (LinearLayout) view;
        }
        return null;
    }

    public static View n(View view, String str) {
        if (view != null && str != null) {
            if (str.equals(view.getTag())) {
                return view;
            }
            if (!(view instanceof ViewGroup)) {
                return null;
            }
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                View viewN = n(viewGroup.getChildAt(i2), str);
                if (viewN != null) {
                    return viewN;
                }
            }
        }
        return null;
    }

    public final void i() {
        String str;
        this.f4552h.clear();
        for (int i2 = 0; i2 < this.f4551g.size(); i2++) {
            AbstractC0227s.c cVar = (AbstractC0227s.c) this.f4551g.get(i2);
            if (cVar != null && (str = cVar.f4744b) != null && str.length() != 0 && ("全部".equals(this.f4555k) || this.f4555k.equals(AbstractC0227s.x(cVar.f4747e)))) {
                this.f4552h.add(cVar);
            }
        }
        this.f4554j.notifyDataSetChanged();
    }

    public final void l() {
        if (p()) {
            this.f4550f.setAdapter((ListAdapter) this.f4554j);
            q(true);
        }
    }

    public final int m(int i2) {
        return Math.max(1, (int) ((i2 * this.f4545a.getResources().getDisplayMetrics().density) + 0.5f));
    }

    public boolean o(int i2, int i3, Intent intent) {
        if (i2 != 4201) {
            return false;
        }
        if (i3 == -1 && intent != null && intent.getData() != null) {
            if (AbstractC0227s.k(this.f4545a, intent.getData()) == null) {
                Toast.makeText(this.f4545a, "不支持的表情格式或文件超过3MB", 0).show();
                return true;
            }
            q(false);
            Toast.makeText(this.f4545a, "已添加到“未分类”", 0).show();
        }
        return true;
    }

    public final boolean p() {
        return (this.f4547c == null || this.f4548d == null || this.f4549e == null || this.f4550f == null) ? false : true;
    }

    public final void q(boolean z2) {
        this.f4551g.clear();
        List listV = AbstractC0227s.v(this.f4545a);
        if (listV != null) {
            this.f4551g.addAll(listV);
        }
        s();
        if (z2 || !this.f4553i.contains(this.f4555k)) {
            this.f4555k = "全部";
        }
        t();
        i();
    }

    public final void r() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        intent.addCategory("android.intent.category.OPENABLE");
        try {
            this.f4545a.startActivityForResult(Intent.createChooser(intent, "选择表情"), 4201);
        } catch (Exception unused) {
            Toast.makeText(this.f4545a, "无法选择图片", 0).show();
        }
    }

    public final void s() {
        this.f4553i.clear();
        this.f4553i.add("全部");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (int i2 = 0; i2 < this.f4551g.size(); i2++) {
            AbstractC0227s.c cVar = (AbstractC0227s.c) this.f4551g.get(i2);
            if (cVar != null) {
                String strX = AbstractC0227s.x(cVar.f4747e);
                cVar.f4747e = strX;
                linkedHashSet.add(strX);
            }
        }
        this.f4553i.addAll(linkedHashSet);
    }

    public final void t() {
        this.f4549e.removeAllViews();
        for (int i2 = 0; i2 < this.f4553i.size(); i2++) {
            String str = (String) this.f4553i.get(i2);
            TextView textView = new TextView(this.f4545a);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.rightMargin = m(8);
            textView.setLayoutParams(layoutParams);
            textView.setPadding(m(10), m(5), m(10), m(5));
            textView.setTextSize(12.0f);
            textView.setText(str);
            textView.setBackgroundResource(str.equals(this.f4555k) ? f0.e.f4994O : f0.e.f4995P);
            textView.setOnClickListener(new b(str));
            this.f4549e.addView(textView);
        }
    }

    public void u() {
        if (p()) {
            q(false);
            this.f4547c.setVisibility(8);
            this.f4548d.setVisibility(0);
        }
    }

    public void v() {
        if (p()) {
            this.f4548d.setVisibility(8);
            this.f4547c.setVisibility(0);
        }
    }
}

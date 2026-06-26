package com.im.oldchat.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.C0212c;
import com.im.oldchat.ui.C0216g;

/* JADX INFO: renamed from: com.im.oldchat.ui.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0218i extends AbstractC0222m {

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final C0212c f4600F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final C0216g f4601G;

    /* JADX INFO: renamed from: com.im.oldchat.ui.i$a */
    public class a implements C0216g.c {
        public a() {
        }

        @Override // com.im.oldchat.ui.C0216g.c
        public void a(String str, boolean z2) {
            C0218i.this.c0(str, z2);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.i$b */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C0218i.this.m0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.i$c */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C0218i.this.l0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.i$d */
    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C0218i.this.i0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.i$e */
    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C0218i.this.j0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.i$f */
    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (C0218i.this.f4600F != null) {
                C0218i.this.f4600F.h();
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.i$g */
    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C0218i.this.k0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.i$h */
    public class h implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View.OnClickListener f4609a;

        public h(View.OnClickListener onClickListener) {
            this.f4609a = onClickListener;
        }

        @Override // java.lang.Runnable
        public void run() {
            Activity activity = C0218i.this.f4623a;
            View viewFindViewById = activity == null ? null : activity.findViewById(f0.f.f5092n);
            if (viewFindViewById != null) {
                viewFindViewById.setOnClickListener(this.f4609a);
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.i$i, reason: collision with other inner class name */
    public class ViewOnTouchListenerC0036i implements View.OnTouchListener {
        public ViewOnTouchListenerC0036i() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return C0218i.this.Q(view, motionEvent);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.i$j */
    public interface j {
        void a(String str, String str2, String str3, int i2);
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.i$k */
    public interface k {
        void a(boolean z2);
    }

    public C0218i(Activity activity, String str, EditText editText, Button button, View view, View view2, View view3, View view4, View view5, View view6, View view7, ProgressBar progressBar, TextView textView, j jVar, k kVar, C0212c.b bVar) {
        super(activity, str, editText, button, view, view2, view3, view4, view5, view6, view7, progressBar, textView, jVar, kVar);
        this.f4600F = new C0212c(activity, kVar, bVar);
        this.f4601G = new C0216g(activity, view4, new a());
    }

    public void b0() {
        View view = this.f4627e;
        if (view != null) {
            view.setOnClickListener(new b());
        }
        View view2 = this.f4628f;
        if (view2 != null) {
            view2.setOnClickListener(new c());
        }
        View view3 = this.f4631i;
        if (view3 != null) {
            view3.setOnClickListener(new d());
        }
        View view4 = this.f4632j;
        if (view4 != null) {
            view4.setOnClickListener(new e());
        }
        View viewFindViewById = this.f4623a.findViewById(f0.f.f5094o);
        View viewFindViewById2 = this.f4623a.findViewById(f0.f.f5092n);
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(new f());
        }
        if (viewFindViewById2 != null) {
            g gVar = new g();
            viewFindViewById2.setOnClickListener(gVar);
            viewFindViewById2.post(new h(gVar));
        }
        View view5 = this.f4629g;
        if (view5 != null) {
            view5.setOnTouchListener(new ViewOnTouchListenerC0036i());
        }
    }

    public final void c0(String str, boolean z2) {
        Activity activity;
        AbstractActivityC0232x abstractActivityC0232x;
        O o2;
        if (str == null || str.length() == 0 || (activity = this.f4623a) == null) {
            return;
        }
        if (!(activity instanceof AbstractActivityC0211b)) {
            if (!(activity instanceof AbstractActivityC0232x) || (o2 = (abstractActivityC0232x = (AbstractActivityC0232x) activity).f4867l0) == null) {
                return;
            }
            o2.o(str, z2, abstractActivityC0232x.f4843N);
            f0();
            return;
        }
        AbstractActivityC0211b abstractActivityC0211b = (AbstractActivityC0211b) activity;
        C0226q c0226q = abstractActivityC0211b.f4411b0;
        if (c0226q != null) {
            c0226q.k(str, z2, abstractActivityC0211b.f4394K);
            f0();
        }
    }

    public boolean d0(int i2, int i3, Intent intent) {
        C0216g c0216g = this.f4601G;
        if (c0216g != null && c0216g.o(i2, i3, intent)) {
            return true;
        }
        C0212c c0212c = this.f4600F;
        if (c0212c != null && c0212c.f(i2, i3, intent)) {
            return true;
        }
        if (i2 == 2001 && i3 == -1 && intent != null) {
            Uri data = intent.getData();
            if (data != null) {
                w(data);
            }
            return true;
        }
        if (i2 != 2004 || i3 != -1 || intent == null) {
            return false;
        }
        Uri data2 = intent.getData();
        if (data2 != null) {
            q(data2);
        }
        return true;
    }

    public boolean e0(int i2, int[] iArr) {
        if (i2 == 2002) {
            return P(iArr);
        }
        if (i2 != 2003) {
            return false;
        }
        if (iArr.length <= 0 || iArr[0] != 0) {
            Toast.makeText(this.f4623a, "No storage permission", 0).show();
        } else if (this.f4648z == 2) {
            j0();
        } else {
            i0();
        }
        this.f4648z = 0;
        return true;
    }

    public void f0() {
        this.f4641s = false;
        C0216g c0216g = this.f4601G;
        if (c0216g != null) {
            c0216g.v();
        }
        View view = this.f4630h;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    public final void g0() {
        InputMethodManager inputMethodManager;
        Activity activity = this.f4623a;
        if (activity == null) {
            return;
        }
        View view = this.f4625c;
        if (view == null && (view = this.f4630h) == null) {
            view = this.f4628f;
        }
        if (view == null || (inputMethodManager = (InputMethodManager) activity.getSystemService("input_method")) == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void h0() {
        R();
    }

    public final void i0() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        this.f4623a.startActivityForResult(Intent.createChooser(intent, "Select image"), 2001);
    }

    public final void j0() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("video/*");
        this.f4623a.startActivityForResult(Intent.createChooser(intent, "Select video"), 2004);
    }

    public void k0() {
        g0();
        EditText editText = this.f4625c;
        if (editText != null) {
            editText.clearFocus();
        }
        this.f4641s = true;
        View view = this.f4630h;
        if (view != null) {
            view.setVisibility(0);
        }
        C0216g c0216g = this.f4601G;
        if (c0216g != null) {
            c0216g.u();
        }
    }

    public final void l0() {
        this.f4641s = !this.f4641s;
        g0();
        EditText editText = this.f4625c;
        if (editText != null) {
            editText.clearFocus();
        }
        View view = this.f4630h;
        if (view != null) {
            view.setVisibility(this.f4641s ? 0 : 8);
        }
        C0216g c0216g = this.f4601G;
        if (c0216g == null || !this.f4641s) {
            return;
        }
        c0216g.v();
    }

    public final void m0() {
        boolean z2 = this.f4640r;
        this.f4640r = !z2;
        if (z2) {
            this.f4625c.setVisibility(0);
            this.f4626d.setVisibility(0);
            this.f4629g.setVisibility(8);
            View view = this.f4627e;
            if (view instanceof ImageView) {
                ((ImageView) view).setImageResource(f0.e.f4992M);
                return;
            }
            return;
        }
        this.f4625c.setVisibility(8);
        this.f4626d.setVisibility(8);
        this.f4629g.setVisibility(0);
        View view2 = this.f4627e;
        if (view2 instanceof ImageView) {
            ((ImageView) view2).setImageResource(f0.e.f4991L);
        }
        if (!this.f4641s || this.f4630h == null) {
            return;
        }
        this.f4641s = false;
        C0216g c0216g = this.f4601G;
        if (c0216g != null) {
            c0216g.v();
        }
        this.f4630h.setVisibility(8);
    }
}

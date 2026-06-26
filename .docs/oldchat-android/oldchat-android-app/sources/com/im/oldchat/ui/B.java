package com.im.oldchat.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.C;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import l0.C0370b;
import o.c;
import o0.AbstractC0451q;
import o0.AbstractC0455v;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: classes.dex */
public abstract class B extends f0.a {

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public static final AtomicInteger f2388Z = new AtomicInteger(1);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public SwitchCompat f2389A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public SwitchCompat f2390B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public SwitchCompat f2391C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public View f2392D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public View f2393E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public View f2394F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public View f2395G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public View f2396H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public View f2397I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public TextView f2398J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public ListView f2399K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public TextView f2400L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public D f2401M;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public l0.A f2405Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public C0370b f2406R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public String f2407S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public String f2408T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public String f2409U;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ImageView f2414w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public TextView f2415x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f2416y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public View f2417z;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public final List f2402N = new ArrayList();

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public final C f2403O = new C();

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public final l0.I f2404P = new l0.I();

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public String f2410V = "";

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public int f2411W = 0;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public int f2412X = 0;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public boolean f2413Y = false;

    public class a implements C.i {
        public a() {
        }

        @Override // com.im.oldchat.ui.C.i
        public void a(j0.e eVar) {
            B b2 = B.this;
            b2.f2409U = eVar.f5505b;
            b2.f2412X = eVar.f5509f;
            b2.p0(eVar);
        }
    }

    public class b implements C.j {

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                B.this.q0();
            }
        }

        public b() {
        }

        @Override // com.im.oldchat.ui.C.j
        public void a(List list) {
            B.this.f2402N.clear();
            B.this.f2402N.addAll(list);
            B b2 = B.this;
            b2.f2401M.g(b2.f2402N);
            AbstractC0451q.h(B.this, list);
            int size = list == null ? 0 : list.size();
            B b3 = B.this;
            j0.j.n(b3, b3.f2408T, size);
            B b4 = B.this;
            j0.f.e(b4, b4.f2408T, size);
            B.this.f2399K.post(new a());
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            B.this.i0();
        }
    }

    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            B.this.i0();
        }
    }

    public class e implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f2423a;

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ String f2425a;

            public a(String str) {
                this.f2425a = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                B b2 = B.this;
                String str = this.f2425a;
                b2.f2409U = str;
                b2.f2415x.setText(str);
                B b3 = B.this;
                j0.f.f(b3, b3.f2408T, this.f2425a);
                B b4 = B.this;
                j0.j.o(b4, b4.f2408T, this.f2425a);
                Intent intent = new Intent();
                intent.putExtra("group_name", this.f2425a);
                B.this.setResult(1, intent);
                Toast.makeText(B.this, f0.j.h1, 0).show();
            }
        }

        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(B.this, f0.j.f1, 0).show();
            }
        }

        public e(EditText editText) {
            this.f2423a = editText;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            String str;
            String strTrim = this.f2423a.getText() == null ? "" : this.f2423a.getText().toString().trim();
            if (strTrim.length() == 0 || (str = B.this.f2408T) == null || str.length() == 0) {
                return;
            }
            B b2 = B.this;
            b2.f2403O.g(b2, b2.f2407S, b2.f2408T, strTrim, new a(strTrim), new b());
        }
    }

    public void f0() {
        this.f2417z.setVisibility(this.f2412X == 2 ? 0 : 8);
        this.f2389A.setEnabled(this.f2412X >= 1);
        this.f2390B.setEnabled(this.f2412X >= 1);
        this.f2392D.setVisibility(0);
        this.f2393E.setVisibility(this.f2412X >= 1 ? 0 : 8);
        TextView textView = this.f2398J;
        if (textView != null) {
            textView.setText(this.f2412X == 2 ? f0.j.S0 : f0.j.Y0);
        }
        this.f2413Y = true;
        this.f2391C.setChecked(i0.g.b(this, this.f2408T, true));
        this.f2413Y = false;
    }

    public void g0(j0.h hVar) {
        this.f2403O.a(this, this.f2407S, this.f2408T, hVar, new c());
    }

    public void h0() {
        this.f2403O.b(this, this.f2407S, this.f2408T, new a());
    }

    public void i0() {
        this.f2403O.c(this, this.f2407S, this.f2408T, new b());
    }

    public void j0() {
        Intent intent = new Intent(this, (Class<?>) GroupAnnouncementActivity.class);
        intent.putExtra("group_id", this.f2408T);
        intent.putExtra("group_name", this.f2409U);
        String str = this.f2410V;
        if (str == null) {
            str = "";
        }
        intent.putExtra("announcement_text", str);
        intent.putExtra("announcement_mode", this.f2411W);
        intent.putExtra("can_edit_announcement", this.f2412X >= 1);
        startActivityForResult(intent, 3104);
    }

    public void k0() {
        Intent intent = new Intent(this, (Class<?>) GroupInviteActivity.class);
        intent.putExtra("group_id", this.f2408T);
        startActivity(intent);
    }

    public void l0() {
        Intent intent = new Intent(this, (Class<?>) GroupJoinRequestsActivity.class);
        intent.putExtra("group_id", this.f2408T);
        startActivity(intent);
    }

    public void m0(j0.h hVar, boolean z2) {
        this.f2403O.e(this, this.f2407S, this.f2408T, hVar, z2, new d());
    }

    public void n0() {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        int i2 = (int) (getResources().getDisplayMetrics().density * 12.0f);
        linearLayout.setPadding(i2, i2, i2, i2);
        EditText editText = new EditText(this);
        editText.setHint(f0.j.g1);
        editText.setInputType(1);
        String str = this.f2409U;
        if (str == null) {
            str = "";
        }
        editText.setText(str);
        editText.setTextColor(getResources().getColor(f0.d.f4973j));
        editText.setHintTextColor(getResources().getColor(f0.d.f4974k));
        editText.setPadding(i2, i2, i2, i2);
        editText.setBackgroundResource(f0.e.f4996Q);
        editText.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout.addView(editText);
        new c.a(this, f0.k.f5244a).s(f0.j.i1).u(linearLayout).j(f0.j.f5203a, null).p(f0.j.f5205b, new e(editText)).v();
    }

    public void o0() {
        if (this.f2400L == null) {
            return;
        }
        String str = this.f2410V;
        String strTrim = str == null ? "" : str.trim();
        if (strTrim.length() == 0) {
            this.f2400L.setText(f0.j.B0);
            return;
        }
        String string = getString(this.f2411W == 1 ? f0.j.F0 : f0.j.E0);
        String strTrim2 = strTrim.replace('\n', ' ').replace('\r', ' ').trim();
        while (strTrim2.contains("  ")) {
            strTrim2 = strTrim2.replace("  ", " ");
        }
        if (strTrim2.length() > 80) {
            strTrim2 = strTrim2.substring(0, 80) + "...";
        }
        this.f2400L.setText(getString(f0.j.H0, string, strTrim2));
    }

    public void p0(j0.e eVar) {
        TextView textView = this.f2415x;
        String str = eVar.f5505b;
        if (str == null) {
            str = "";
        }
        textView.setText(str);
        String str2 = eVar.f5506c;
        if (str2 == null || str2.isEmpty()) {
            this.f2414w.setImageResource(f0.e.f4997R);
        } else {
            AbstractC0455v.h(this.f2414w, eVar.f5506c);
        }
        String str3 = eVar.f5510g;
        this.f2410V = str3 != null ? str3 : "";
        this.f2411W = eVar.f5511h;
        o0();
        this.f2413Y = true;
        this.f2389A.setChecked(eVar.f5507d);
        this.f2390B.setChecked(eVar.f5508e);
        this.f2413Y = false;
        this.f2401M.h(this.f2412X);
        f0();
    }

    public void q0() {
        ListAdapter adapter;
        ListView listView = this.f2399K;
        if (listView == null || (adapter = listView.getAdapter()) == null) {
            return;
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.f2399K.getWidth(), PKIFailureInfo.systemUnavail);
        int measuredHeight = 0;
        for (int i2 = 0; i2 < adapter.getCount(); i2++) {
            View view = adapter.getView(i2, null, this.f2399K);
            view.measure(iMakeMeasureSpec, View.MeasureSpec.makeMeasureSpec(0, 0));
            measuredHeight += view.getMeasuredHeight();
        }
        int count = adapter.getCount();
        int dividerHeight = this.f2399K.getDividerHeight();
        ViewGroup.LayoutParams layoutParams = this.f2399K.getLayoutParams();
        layoutParams.height = measuredHeight + (dividerHeight * Math.max(0, count - 1));
        this.f2399K.setLayoutParams(layoutParams);
        this.f2399K.requestLayout();
    }

    public void r0() {
        this.f2403O.h(this, this.f2407S, this.f2408T, this.f2389A.isChecked(), this.f2390B.isChecked());
    }
}

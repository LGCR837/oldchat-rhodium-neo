package com.im.oldchat.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.AbstractC0230v;
import g0.j;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import l0.AbstractC0372c;
import l0.AbstractC0380g;
import l0.AbstractC0390l;
import l0.AbstractC0406v;
import l0.C0370b;
import l0.C0384i;
import l0.C0386j;
import l0.N0;
import o0.C;
import o0.C0432a0;
import org.spongycastle.i18n.TextBundle;

/* JADX INFO: renamed from: com.im.oldchat.ui.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractActivityC0211b extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public TextView f4384A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public ProgressBar f4385B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public ProgressBar f4386C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public View f4387D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public View f4388E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public TextView f4389F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public View f4390G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public TextView f4391H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public TextView f4392I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public TextView f4393J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public C.d f4394K;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public C0218i f4396M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public S f4397N;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public String f4400Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public String f4401R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public String f4402S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public String f4403T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public String f4404U;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public C0384i f4406W;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public View f4407X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public TextView f4408Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public TextView f4409Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public C0386j f4410a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public C0226q f4411b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public C0370b f4412c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public float f4413d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public float f4414e0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public C0432a0 f4416g0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public RecyclerView f4420w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public EditText f4421x;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public Button f4423z;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public String f4422y = "";

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public int f4395L = 0;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public final List f4398O = new ArrayList();

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public final HashSet f4399P = new HashSet();

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public boolean f4405V = false;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public boolean f4415f0 = false;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public final Handler f4417h0 = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public final Runnable f4418i0 = new a();

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public final j.InterfaceC0042j f4419j0 = N0.b(new RunnableC0031b());

    /* JADX INFO: renamed from: com.im.oldchat.ui.b$a */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            String str;
            AbstractActivityC0211b abstractActivityC0211b = AbstractActivityC0211b.this;
            if (abstractActivityC0211b.f4415f0) {
                if (abstractActivityC0211b.f4416g0 != null && (str = abstractActivityC0211b.f4400Q) != null && !str.isEmpty()) {
                    AbstractActivityC0211b abstractActivityC0211b2 = AbstractActivityC0211b.this;
                    abstractActivityC0211b2.f4416g0.v(abstractActivityC0211b2, abstractActivityC0211b2.f4404U, abstractActivityC0211b2.f4400Q, false);
                }
                AbstractActivityC0211b.this.f4415f0 = false;
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.b$b, reason: collision with other inner class name */
    public class RunnableC0031b implements Runnable {
        public RunnableC0031b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractActivityC0211b.this.H0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.b$c */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractActivityC0211b.this.j0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.b$d */
    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractActivityC0211b.this.m0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.b$e */
    public class e implements AbstractC0230v.f {
        public e() {
        }

        @Override // com.im.oldchat.ui.AbstractC0230v.f
        public void a() {
            AbstractActivityC0211b abstractActivityC0211b = AbstractActivityC0211b.this;
            S s2 = abstractActivityC0211b.f4397N;
            if (s2 != null) {
                s2.j0();
            } else {
                abstractActivityC0211b.I0(false, 0);
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.b$f */
    public class f implements TextWatcher {
        public f() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            AbstractActivityC0211b abstractActivityC0211b = AbstractActivityC0211b.this;
            abstractActivityC0211b.f4422y = AbstractC0372c.b(abstractActivityC0211b, abstractActivityC0211b.o0(), editable == null ? "" : editable.toString(), AbstractActivityC0211b.this.f4422y);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            C0432a0 c0432a0;
            String str = AbstractActivityC0211b.this.f4400Q;
            if (str == null || str.isEmpty()) {
                return;
            }
            if (!o0.V.n(AbstractActivityC0211b.this)) {
                AbstractActivityC0211b abstractActivityC0211b = AbstractActivityC0211b.this;
                if (abstractActivityC0211b.f4415f0 && (c0432a0 = abstractActivityC0211b.f4416g0) != null) {
                    c0432a0.v(abstractActivityC0211b, abstractActivityC0211b.f4404U, abstractActivityC0211b.f4400Q, false);
                    AbstractActivityC0211b.this.f4415f0 = false;
                }
                AbstractActivityC0211b abstractActivityC0211b2 = AbstractActivityC0211b.this;
                abstractActivityC0211b2.f4417h0.removeCallbacks(abstractActivityC0211b2.f4418i0);
                return;
            }
            String strTrim = charSequence == null ? "" : charSequence.toString().trim();
            if (strTrim.length() > 0) {
                AbstractActivityC0211b abstractActivityC0211b3 = AbstractActivityC0211b.this;
                if (!abstractActivityC0211b3.f4415f0) {
                    abstractActivityC0211b3.f4415f0 = true;
                    C0432a0 c0432a02 = abstractActivityC0211b3.f4416g0;
                    if (c0432a02 != null) {
                        c0432a02.t(abstractActivityC0211b3, abstractActivityC0211b3.f4404U, abstractActivityC0211b3.f4400Q, false);
                    }
                    AbstractActivityC0211b.this.z0();
                    return;
                }
            }
            if (strTrim.length() == 0) {
                AbstractActivityC0211b abstractActivityC0211b4 = AbstractActivityC0211b.this;
                if (abstractActivityC0211b4.f4415f0) {
                    abstractActivityC0211b4.f4415f0 = false;
                    C0432a0 c0432a03 = abstractActivityC0211b4.f4416g0;
                    if (c0432a03 != null) {
                        c0432a03.v(abstractActivityC0211b4, abstractActivityC0211b4.f4404U, abstractActivityC0211b4.f4400Q, false);
                    }
                    AbstractActivityC0211b abstractActivityC0211b5 = AbstractActivityC0211b.this;
                    abstractActivityC0211b5.f4417h0.removeCallbacks(abstractActivityC0211b5.f4418i0);
                    return;
                }
            }
            if (strTrim.length() > 0) {
                AbstractActivityC0211b abstractActivityC0211b6 = AbstractActivityC0211b.this;
                if (abstractActivityC0211b6.f4415f0) {
                    abstractActivityC0211b6.z0();
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.b$g */
    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            int iC = AbstractActivityC0211b.this.f4397N != null ? r0.c() - 1 : -1;
            if (iC >= 0) {
                AbstractActivityC0211b.this.f4420w.T0(iC);
            }
        }
    }

    public void A0() {
        EditText editText;
        S s2 = this.f4397N;
        if (s2 == null || !s2.n0()) {
            C0218i c0218i = this.f4396M;
            if (c0218i != null) {
                c0218i.f0();
            }
            C0226q c0226q = this.f4411b0;
            if (c0226q == null || (editText = this.f4421x) == null) {
                return;
            }
            c0226q.o(editText.getText().toString(), this.f4394K);
        }
    }

    public void B0(boolean z2) {
        if (z2) {
            this.f4395L++;
        } else {
            this.f4395L = Math.max(0, this.f4395L - 1);
        }
        ProgressBar progressBar = this.f4385B;
        if (progressBar != null) {
            progressBar.setVisibility(this.f4395L > 0 ? 0 : 8);
        }
        Button button = this.f4423z;
        if (button != null) {
            button.setEnabled(this.f4395L == 0);
        }
    }

    public void C0(boolean z2) {
        C0386j c0386j;
        RecyclerView recyclerView;
        S s2 = this.f4397N;
        if (s2 != null) {
            s2.x0(z2);
        }
        if (!z2 || (c0386j = this.f4410a0) == null || !c0386j.g() || (recyclerView = this.f4420w) == null) {
            return;
        }
        recyclerView.post(new g());
    }

    public void D0() {
        this.f4387D = a0(f0.f.l5);
        this.f4390G = a0(f0.f.e5);
        this.f4391H = (TextView) a0(f0.f.Z9);
        this.f4392I = (TextView) a0(f0.f.Q0);
        this.f4393J = (TextView) a0(f0.f.R0);
        TextView textView = this.f4392I;
        if (textView != null) {
            textView.setOnClickListener(new c());
        }
        TextView textView2 = this.f4393J;
        if (textView2 != null) {
            textView2.setOnClickListener(new d());
        }
        I0(false, 0);
    }

    public void E0() {
        this.f4384A = null;
    }

    public void F0() {
        EditText editText = this.f4421x;
        if (editText == null) {
            return;
        }
        editText.addTextChangedListener(new f());
    }

    public final List G0(j0.k kVar) {
        ArrayList arrayList = new ArrayList();
        if (kVar != null) {
            arrayList.add(kVar);
        }
        return arrayList;
    }

    public void H0() {
        if (this.f4407X == null) {
            return;
        }
        this.f4407X.setVisibility(N0.c(this, this.f4400Q, null) ? 0 : 8);
    }

    public final void I0(boolean z2, int i2) {
        View view = this.f4390G;
        if (view != null) {
            view.setVisibility(z2 ? 0 : 8);
        }
        View view2 = this.f4387D;
        if (view2 != null) {
            view2.setVisibility(z2 ? 8 : 0);
        }
        if (z2) {
            C0218i c0218i = this.f4396M;
            if (c0218i != null) {
                c0218i.f0();
            }
            p0();
            k0();
        }
        TextView textView = this.f4391H;
        if (textView != null) {
            textView.setText(getString(f0.j.f5202Z, Integer.valueOf(i2)));
        }
    }

    public void J0() {
        String str = this.f4400Q;
        if (str == null || str.isEmpty() || this.f4398O.isEmpty()) {
            return;
        }
        j0.k kVar = (j0.k) this.f4398O.get(r0.size() - 1);
        if (kVar == null) {
            return;
        }
        String str2 = this.f4401R;
        String str3 = (str2 == null || str2.isEmpty()) ? this.f4400Q : this.f4401R;
        String strD = AbstractC0380g.d(kVar);
        if (o0.F.c(this, kVar.f5552c, this.f4403T)) {
            j0.r.n(this, this.f4400Q, str3, this.f4402S, strD, kVar.f5558i);
        } else {
            j0.r.l(this, this.f4400Q, str3, this.f4402S, strD, kVar.f5558i, 0);
        }
        j0.r.c(this, this.f4400Q);
    }

    public void g0() {
        float fC = o0.V.c(this);
        EditText editText = this.f4421x;
        if (editText != null) {
            if (this.f4413d0 == 0.0f) {
                this.f4413d0 = editText.getTextSize();
            }
            this.f4421x.setTextSize(0, this.f4413d0 * fC);
        }
        TextView textView = this.f4389F;
        if (textView != null) {
            if (this.f4414e0 == 0.0f) {
                this.f4414e0 = textView.getTextSize();
            }
            this.f4389F.setTextSize(0, this.f4414e0 * fC);
        }
        S s2 = this.f4397N;
        if (s2 != null) {
            s2.t0(fC);
        }
    }

    public ChatActivity h0() {
        return (ChatActivity) this;
    }

    public final List i0(List list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                C.b bVarC = AbstractC0406v.c(this, (j0.k) list.get(i2), this.f4403T, this.f4400Q, this.f4401R, this.f4402S);
                if (bVarC != null) {
                    arrayList.add(bVarC);
                }
            }
        }
        return arrayList;
    }

    public void j0() {
        S s2 = this.f4397N;
        if (s2 != null) {
            s2.j0();
        } else {
            I0(false, 0);
        }
    }

    public void k0() {
        this.f4394K = null;
        AbstractC0390l.d(this.f4388E, this.f4389F);
    }

    public void l0(j0.k kVar) {
        S s2 = this.f4397N;
        if (s2 == null) {
            return;
        }
        s2.i0(kVar);
    }

    public void m0() {
        S s2 = this.f4397N;
        if (s2 == null) {
            return;
        }
        List listI0 = i0(s2.k0());
        if (listI0.isEmpty()) {
            Toast.makeText(this, f0.j.f5206b0, 0).show();
        } else {
            AbstractC0230v.i(this, this.f4404U, listI0, new e());
        }
    }

    public void n0(j0.k kVar) {
        List listI0 = i0(G0(kVar));
        if (listI0.isEmpty()) {
            Toast.makeText(this, f0.j.f5206b0, 0).show();
        } else {
            AbstractC0230v.i(this, this.f4404U, listI0, null);
        }
    }

    public String o0() {
        String str = this.f4400Q;
        if (str == null || str.length() == 0) {
            return null;
        }
        String str2 = this.f4403T;
        if (str2 == null) {
            str2 = "";
        }
        return "direct:" + str2 + ":" + this.f4400Q;
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    public final void p0() {
        EditText editText;
        EditText editText2 = this.f4421x;
        if (editText2 != null) {
            editText2.clearFocus();
        }
        Object systemService = getSystemService("input_method");
        if (!(systemService instanceof InputMethodManager) || (editText = this.f4421x) == null) {
            return;
        }
        ((InputMethodManager) systemService).hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public void q0(boolean z2, int i2) {
        I0(z2, i2);
    }

    public void r0() {
        Intent intent = new Intent(this, (Class<?>) ChatSettingsActivity.class);
        intent.putExtra("friend_uid", this.f4400Q);
        intent.putExtra("friend_name", this.f4401R);
        intent.putExtra("friend_avatar", this.f4402S);
        startActivityForResult(intent, 3005);
    }

    public void s0() {
        startActivityForResult(new Intent(this, (Class<?>) EmojiPickerActivity.class), 3101);
    }

    public void t0() {
        String str = this.f4400Q;
        if (str == null || str.isEmpty()) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) UserSpaceActivity.class);
        intent.putExtra("uid", this.f4400Q);
        startActivity(intent);
    }

    public void u0() {
        String str = this.f4400Q;
        if (str == null || str.isEmpty()) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) RedPacketSendActivity.class);
        intent.putExtra("to_uid", this.f4400Q);
        String str2 = this.f4401R;
        if (str2 == null) {
            str2 = "";
        }
        intent.putExtra("target_name", str2);
        startActivityForResult(intent, 3102);
    }

    public void v0(j0.k kVar) {
        C.d dVarC = AbstractC0390l.c(kVar, this.f4401R);
        this.f4394K = dVarC;
        AbstractC0390l.b(this.f4388E, this.f4389F, dVarC);
    }

    public void w0(j0.k kVar) {
        if (kVar == null || this.f4421x == null) {
            return;
        }
        String str = kVar.f5560k;
        String lowerCase = str == null ? TextBundle.TEXT_ENTRY : str.toLowerCase();
        String str2 = kVar.f5561l;
        String strTrim = str2 == null ? "" : str2.trim();
        if (!TextBundle.TEXT_ENTRY.equals(lowerCase) || strTrim.length() == 0) {
            Toast.makeText(this, "该消息暂不支持重新编辑", 0).show();
            return;
        }
        this.f4421x.requestFocus();
        this.f4421x.setText(strTrim);
        this.f4421x.setSelection(strTrim.length());
        if (this.f4394K != null) {
            k0();
        }
    }

    public void x0(j0.k kVar) {
        if (kVar == null || kVar.f5550a == null) {
            return;
        }
        AbstractC0390l.e(h0(), this.f4404U, this.f4400Q, this.f4398O, this.f4399P, this.f4397N, kVar.f5550a);
    }

    public void y0() {
        String string = getSharedPreferences("auth", 0).getString("my_uid", "");
        if (string == null || string.isEmpty() || string.equals(this.f4403T)) {
            return;
        }
        this.f4403T = string;
        S s2 = this.f4397N;
        if (s2 != null) {
            s2.u0(string);
        }
        C0386j c0386j = this.f4410a0;
        if (c0386j != null) {
            c0386j.F(this.f4403T);
        }
        C0384i c0384i = this.f4406W;
        if (c0384i != null) {
            c0384i.h(this.f4403T);
        }
        AbstractC0380g.a(this, this.f4398O, this.f4403T);
        S s3 = this.f4397N;
        if (s3 != null) {
            s3.g();
        }
    }

    public void z0() {
        this.f4417h0.removeCallbacks(this.f4418i0);
        this.f4417h0.postDelayed(this.f4418i0, 3000L);
    }
}

package com.im.oldchat.ui;

import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.im.oldchat.ui.C;
import java.util.ArrayList;
import java.util.Collection;
import l0.AbstractC0372c;
import o.c;
import o0.C0432a0;

/* JADX INFO: renamed from: com.im.oldchat.ui.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractActivityC0233y extends l0.C {

    /* JADX INFO: renamed from: com.im.oldchat.ui.y$a */
    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            AbstractActivityC0233y abstractActivityC0233y = AbstractActivityC0233y.this;
            abstractActivityC0233y.f4873y = AbstractC0372c.b(abstractActivityC0233y, abstractActivityC0233y.q0(), editable == null ? "" : editable.toString(), AbstractActivityC0233y.this.f4873y);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            C0432a0 c0432a0;
            String str = AbstractActivityC0233y.this.f4855Z;
            if (str == null || str.isEmpty()) {
                return;
            }
            if (!o0.V.n(AbstractActivityC0233y.this)) {
                AbstractActivityC0233y abstractActivityC0233y = AbstractActivityC0233y.this;
                if (abstractActivityC0233y.A0 && (c0432a0 = abstractActivityC0233y.C0) != null) {
                    c0432a0.v(abstractActivityC0233y, abstractActivityC0233y.f4858c0, abstractActivityC0233y.f4855Z, true);
                    AbstractActivityC0233y.this.A0 = false;
                }
                AbstractActivityC0233y abstractActivityC0233y2 = AbstractActivityC0233y.this;
                abstractActivityC0233y2.D0.removeCallbacks(abstractActivityC0233y2.E0);
                return;
            }
            String strTrim = charSequence == null ? "" : charSequence.toString().trim();
            if (strTrim.length() > 0) {
                AbstractActivityC0233y abstractActivityC0233y3 = AbstractActivityC0233y.this;
                if (!abstractActivityC0233y3.A0) {
                    abstractActivityC0233y3.A0 = true;
                    C0432a0 c0432a02 = abstractActivityC0233y3.C0;
                    if (c0432a02 != null) {
                        c0432a02.t(abstractActivityC0233y3, abstractActivityC0233y3.f4858c0, abstractActivityC0233y3.f4855Z, true);
                    }
                    AbstractActivityC0233y.this.d1();
                    return;
                }
            }
            if (strTrim.length() == 0) {
                AbstractActivityC0233y abstractActivityC0233y4 = AbstractActivityC0233y.this;
                if (abstractActivityC0233y4.A0) {
                    abstractActivityC0233y4.A0 = false;
                    C0432a0 c0432a03 = abstractActivityC0233y4.C0;
                    if (c0432a03 != null) {
                        c0432a03.v(abstractActivityC0233y4, abstractActivityC0233y4.f4858c0, abstractActivityC0233y4.f4855Z, true);
                    }
                    AbstractActivityC0233y abstractActivityC0233y5 = AbstractActivityC0233y.this;
                    abstractActivityC0233y5.D0.removeCallbacks(abstractActivityC0233y5.E0);
                    return;
                }
            }
            if (strTrim.length() > 0) {
                AbstractActivityC0233y abstractActivityC0233y6 = AbstractActivityC0233y.this;
                if (abstractActivityC0233y6.A0) {
                    abstractActivityC0233y6.d1();
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.y$b */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            int count = AbstractActivityC0233y.this.f4871w.getCount() - 1;
            if (count >= 0) {
                AbstractActivityC0233y.this.f4871w.setSelection(count);
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.y$c */
    public class c implements C.i {
        public c() {
        }

        @Override // com.im.oldchat.ui.C.i
        public void a(j0.e eVar) {
            if (eVar == null) {
                return;
            }
            AbstractActivityC0233y abstractActivityC0233y = AbstractActivityC0233y.this;
            String str = eVar.f5510g;
            abstractActivityC0233y.q0 = str == null ? "" : str.trim();
            AbstractActivityC0233y abstractActivityC0233y2 = AbstractActivityC0233y.this;
            abstractActivityC0233y2.r0 = eVar.f5511h;
            abstractActivityC0233y2.s0 = eVar.f5512i;
            abstractActivityC0233y2.t0 = eVar.f5513j;
            abstractActivityC0233y2.W0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.y$d */
    public class d implements DialogInterface.OnClickListener {
        public d() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            AbstractActivityC0233y.this.a1();
            AbstractActivityC0233y.this.u0 = false;
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.y$e */
    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractActivityC0233y.this.t0 = System.currentTimeMillis() / 1000;
            AbstractActivityC0233y.this.Y0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.y$f */
    public class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
        }
    }

    public void W0() {
        String str = this.q0;
        if (str == null || str.length() == 0) {
            Y0();
            return;
        }
        if (!Z0()) {
            Y0();
        } else if (this.r0 != 1) {
            g1();
        } else {
            Y0();
            h1();
        }
    }

    public void X0() {
        this.B0.clear();
        e1(null);
    }

    public void Y0() {
        View view = this.f4870o0;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    public final boolean Z0() {
        String str = this.q0;
        if (str == null || str.length() == 0) {
            return false;
        }
        long j2 = this.t0;
        if (j2 <= 0) {
            return true;
        }
        long j3 = this.s0;
        return j3 > 0 && j3 > j2;
    }

    public void a1() {
        String str;
        if (this.f4869n0 == null || (str = this.f4858c0) == null || str.isEmpty()) {
            return;
        }
        this.f4869n0.d(this, this.f4858c0, this.f4855Z, new e(), new f());
    }

    public void b1() {
        String str;
        String str2 = this.f4855Z;
        if (str2 == null || str2.isEmpty() || (str = this.f4858c0) == null || str.isEmpty()) {
            return;
        }
        if (this.f4869n0 == null) {
            this.f4869n0 = new C();
        }
        this.f4869n0.b(this, this.f4858c0, this.f4855Z, new c());
    }

    public void c1() {
        String string = getSharedPreferences("auth", 0).getString("my_uid", "");
        if (string == null || string.isEmpty() || string.equals(this.f4859d0)) {
            return;
        }
        this.f4859d0 = string;
        G g2 = this.f4846Q;
        if (g2 != null) {
            g2.r0(string);
        }
        l0.D d2 = this.f4866k0;
        if (d2 != null) {
            d2.D(this.f4859d0);
        }
        l0.L l2 = this.y0;
        if (l2 != null) {
            l2.d(this.f4859d0);
        }
        G g3 = this.f4846Q;
        if (g3 != null) {
            g3.notifyDataSetChanged();
        }
    }

    public void d1() {
        this.D0.removeCallbacks(this.E0);
        this.D0.postDelayed(this.E0, 3000L);
    }

    public void e1(Collection collection) {
        l0.D d2;
        ListView listView;
        boolean z2 = (collection == null || collection.isEmpty()) ? false : true;
        G g2 = this.f4846Q;
        if (g2 != null) {
            if (z2) {
                g2.u0(new ArrayList(collection));
            } else {
                g2.b0();
            }
        }
        if (!z2 || (d2 = this.f4866k0) == null || !d2.f() || (listView = this.f4871w) == null) {
            return;
        }
        listView.post(new b());
    }

    public void f1() {
        EditText editText = this.f4872x;
        if (editText == null) {
            return;
        }
        editText.addTextChangedListener(new a());
    }

    public void g1() {
        TextView textView;
        if (this.f4870o0 == null || (textView = this.p0) == null) {
            return;
        }
        textView.setText(getString(f0.j.t0) + this.q0);
        this.f4870o0.setVisibility(0);
    }

    public void h1() {
        if (this.u0) {
            return;
        }
        this.u0 = true;
        new c.a(this, f0.k.f5244a).s(f0.j.M0).i(this.q0).p(f0.j.L0, new d()).d(false).v();
    }

    public void i1() {
        e1(this.B0);
    }
}

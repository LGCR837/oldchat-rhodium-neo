package com.im.oldchat.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.AbstractC0230v;
import g0.j;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import l0.AbstractC0380g;
import l0.AbstractC0406v;
import l0.C0370b;
import l0.N0;
import o0.C;
import o0.C0432a0;
import org.spongycastle.i18n.TextBundle;

/* JADX INFO: renamed from: com.im.oldchat.ui.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractActivityC0232x extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public TextView f4830A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public ProgressBar f4831B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public ProgressBar f4832C;
    public C0432a0 C0;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public View f4833D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public View f4834E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public TextView f4835F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public View f4836G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public TextView f4837H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public TextView f4838I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public TextView f4839J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public View f4840K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public View f4841L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public TextView f4842M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public C.d f4843N;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public C0218i f4845P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public G f4846Q;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public String f4855Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public String f4856a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public String f4857b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public String f4858c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public String f4859d0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public j.InterfaceC0042j f4862g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public View f4863h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public TextView f4864i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public TextView f4865j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public l0.D f4866k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public O f4867l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public C0370b f4868m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public C f4869n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public View f4870o0;
    public TextView p0;
    public float v0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ListView f4871w;
    public float w0;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public EditText f4872x;
    public o.c x0;
    public l0.L y0;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public Button f4874z;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public String f4873y = "";

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public int f4844O = 0;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public final List f4847R = new ArrayList();

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public final HashSet f4848S = new HashSet();

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public final Map f4849T = new HashMap();

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public final Map f4850U = new HashMap();

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public final Map f4851V = new HashMap();

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public final Map f4852W = new HashMap();

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public final List f4853X = new ArrayList();

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public final List f4854Y = new ArrayList();

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public int f4860e0 = 0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public boolean f4861f0 = false;
    public String q0 = "";
    public int r0 = 0;
    public long s0 = 0;
    public long t0 = 0;
    public boolean u0 = false;
    public boolean z0 = false;
    public boolean A0 = false;
    public final LinkedHashSet B0 = new LinkedHashSet();
    public final Handler D0 = new Handler(Looper.getMainLooper());
    public final Runnable E0 = new a();
    public final j.InterfaceC0042j F0 = N0.b(new b());

    /* JADX INFO: renamed from: com.im.oldchat.ui.x$a */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            String str;
            AbstractActivityC0232x abstractActivityC0232x = AbstractActivityC0232x.this;
            if (abstractActivityC0232x.A0) {
                if (abstractActivityC0232x.C0 != null && (str = abstractActivityC0232x.f4855Z) != null && !str.isEmpty()) {
                    AbstractActivityC0232x abstractActivityC0232x2 = AbstractActivityC0232x.this;
                    abstractActivityC0232x2.C0.v(abstractActivityC0232x2, abstractActivityC0232x2.f4858c0, abstractActivityC0232x2.f4855Z, true);
                }
                AbstractActivityC0232x.this.A0 = false;
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.x$b */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractActivityC0232x.this.G0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.x$c */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            l0.D d2 = AbstractActivityC0232x.this.f4866k0;
            if (d2 == null || !d2.s()) {
                return;
            }
            long jU = AbstractActivityC0232x.this.f4866k0.u();
            AbstractActivityC0232x abstractActivityC0232x = AbstractActivityC0232x.this;
            abstractActivityC0232x.f4866k0.x(abstractActivityC0232x.f4858c0, true, jU, false);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.x$d */
    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractActivityC0232x.this.k0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.x$e */
    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractActivityC0232x.this.o0();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.x$f */
    public class f implements AbstractC0230v.f {
        public f() {
        }

        @Override // com.im.oldchat.ui.AbstractC0230v.f
        public void a() {
            AbstractActivityC0232x abstractActivityC0232x = AbstractActivityC0232x.this;
            G g2 = abstractActivityC0232x.f4846Q;
            if (g2 != null) {
                g2.e0();
            } else {
                abstractActivityC0232x.H0(false, 0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H0(boolean z2, int i2) {
        View view = this.f4836G;
        if (view != null) {
            view.setVisibility(z2 ? 0 : 8);
        }
        View view2 = this.f4833D;
        if (view2 != null) {
            view2.setVisibility(z2 ? 8 : 0);
        }
        if (z2) {
            C0218i c0218i = this.f4845P;
            if (c0218i != null) {
                c0218i.f0();
            }
            r0();
            l0();
            o.c cVar = this.x0;
            if (cVar != null && cVar.isShowing()) {
                this.x0.dismiss();
            }
        }
        TextView textView = this.f4837H;
        if (textView != null) {
            textView.setText(getString(f0.j.f5202Z, Integer.valueOf(i2)));
        }
    }

    private List j0(List list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                j0.i iVar = (j0.i) list.get(i2);
                String str = iVar == null ? "" : iVar.f5531c;
                C.b bVarF = AbstractC0406v.f(this, iVar, A0(str), str != null ? (String) this.f4850U.get(str) : "");
                if (bVarF != null) {
                    arrayList.add(bVarF);
                }
            }
        }
        return arrayList;
    }

    private void r0() {
        EditText editText;
        EditText editText2 = this.f4872x;
        if (editText2 != null) {
            editText2.clearFocus();
        }
        Object systemService = getSystemService("input_method");
        if (!(systemService instanceof InputMethodManager) || (editText = this.f4872x) == null) {
            return;
        }
        ((InputMethodManager) systemService).hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public String A0(String str) {
        if (str == null || str.isEmpty()) {
            return "成员";
        }
        String strB = (String) this.f4849T.get(str);
        if (strB == null || strB.isEmpty()) {
            strB = j0.x.b(this, str);
        }
        return (strB == null || strB.isEmpty()) ? str : strB;
    }

    public void B0() {
        G g2 = this.f4846Q;
        if (g2 == null || !g2.i0()) {
            C0218i c0218i = this.f4845P;
            if (c0218i != null) {
                c0218i.f0();
            }
            if (this.f4867l0 != null) {
                EditText editText = this.f4872x;
                String string = editText == null ? "" : editText.getText().toString();
                this.f4867l0.s(string, this.f4843N, m0(string));
            }
        }
    }

    public void C0(boolean z2) {
        if (z2) {
            this.f4844O++;
        } else {
            this.f4844O = Math.max(0, this.f4844O - 1);
        }
        ProgressBar progressBar = this.f4831B;
        if (progressBar != null) {
            progressBar.setVisibility(this.f4844O > 0 ? 0 : 8);
        }
        Button button = this.f4874z;
        if (button != null) {
            button.setEnabled(this.f4844O == 0);
        }
    }

    public void D0() {
        this.f4833D = a0(f0.f.l5);
        this.f4836G = a0(f0.f.e5);
        this.f4837H = (TextView) a0(f0.f.Z9);
        this.f4838I = (TextView) a0(f0.f.Q0);
        this.f4839J = (TextView) a0(f0.f.R0);
        TextView textView = this.f4838I;
        if (textView != null) {
            textView.setOnClickListener(new d());
        }
        TextView textView2 = this.f4839J;
        if (textView2 != null) {
            textView2.setOnClickListener(new e());
        }
        H0(false, 0);
    }

    public void E0() {
        View viewInflate = getLayoutInflater().inflate(f0.g.c1, (ViewGroup) this.f4871w, false);
        TextView textView = (TextView) viewInflate.findViewById(f0.f.o1);
        this.f4830A = textView;
        textView.setOnClickListener(new c());
        this.f4871w.addHeaderView(viewInflate);
    }

    public final List F0(j0.i iVar) {
        ArrayList arrayList = new ArrayList();
        if (iVar != null) {
            arrayList.add(iVar);
        }
        return arrayList;
    }

    public void G0() {
        if (this.f4863h0 == null) {
            return;
        }
        this.f4863h0.setVisibility(N0.c(this, null, this.f4855Z) ? 0 : 8);
    }

    public void I0() {
        String str = this.f4855Z;
        if (str == null || str.isEmpty() || this.f4847R.isEmpty()) {
            return;
        }
        j0.i iVar = (j0.i) this.f4847R.get(r0.size() - 1);
        if (iVar == null) {
            return;
        }
        String str2 = this.f4856a0;
        String str3 = (str2 == null || str2.isEmpty()) ? this.f4855Z : this.f4856a0;
        String strS0 = s0(iVar.f5533e, iVar.f5532d);
        if (o0.F.c(this, iVar.f5531c, this.f4859d0)) {
            j0.j.k(this, this.f4855Z, str3, this.f4857b0, strS0, iVar.f5537i);
        } else {
            j0.j.j(this, this.f4855Z, str3, this.f4857b0, strS0, iVar.f5537i, 0);
        }
        j0.j.b(this, this.f4855Z);
    }

    public void g0() {
        C0370b c0370b = this.f4868m0;
        if (c0370b != null) {
            c0370b.e();
        }
    }

    public void h0() {
        float fC = o0.V.c(this);
        EditText editText = this.f4872x;
        if (editText != null) {
            if (this.v0 == 0.0f) {
                this.v0 = editText.getTextSize();
            }
            this.f4872x.setTextSize(0, this.v0 * fC);
        }
        TextView textView = this.f4835F;
        if (textView != null) {
            if (this.w0 == 0.0f) {
                this.w0 = textView.getTextSize();
            }
            this.f4835F.setTextSize(0, this.w0 * fC);
        }
        G g2 = this.f4846Q;
        if (g2 != null) {
            g2.p0(fC);
        }
    }

    public GroupChatActivity i0() {
        return (GroupChatActivity) this;
    }

    public void k0() {
        G g2 = this.f4846Q;
        if (g2 != null) {
            g2.e0();
        } else {
            H0(false, 0);
        }
    }

    public void l0() {
        this.f4843N = null;
        l0.G.d(this.f4834E, this.f4835F);
    }

    public abstract List m0(String str);

    public void n0(j0.i iVar) {
        G g2 = this.f4846Q;
        if (g2 == null) {
            return;
        }
        g2.d0(iVar);
    }

    public void o0() {
        G g2 = this.f4846Q;
        if (g2 == null) {
            return;
        }
        List listJ0 = j0(g2.f0());
        if (listJ0.isEmpty()) {
            Toast.makeText(this, f0.j.f5206b0, 0).show();
        } else {
            AbstractC0230v.i(this, this.f4858c0, listJ0, new f());
        }
    }

    public void p0(j0.i iVar) {
        List listJ0 = j0(F0(iVar));
        if (listJ0.isEmpty()) {
            Toast.makeText(this, f0.j.f5206b0, 0).show();
        } else {
            AbstractC0230v.i(this, this.f4858c0, listJ0, null);
        }
    }

    public String q0() {
        String str = this.f4855Z;
        if (str == null || str.length() == 0) {
            return null;
        }
        String str2 = this.f4859d0;
        if (str2 == null) {
            str2 = "";
        }
        return "group:" + str2 + ":" + this.f4855Z;
    }

    public String s0(String str, String str2) {
        return AbstractC0380g.e(str, str2);
    }

    public void t0(boolean z2, int i2) {
        H0(z2, i2);
    }

    public void u0() {
        startActivityForResult(new Intent(this, (Class<?>) EmojiPickerActivity.class), 3201);
    }

    public void v0() {
        Intent intent = new Intent(this, (Class<?>) GroupManageActivity.class);
        intent.putExtra("group_id", this.f4855Z);
        intent.putExtra("group_name", this.f4856a0);
        intent.putExtra("group_role", this.f4860e0);
        startActivityForResult(intent, 3005);
    }

    public void w0() {
        String str = this.f4855Z;
        if (str == null || str.isEmpty()) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) RedPacketSendActivity.class);
        intent.putExtra("group_id", this.f4855Z);
        String str2 = this.f4856a0;
        if (str2 == null) {
            str2 = "";
        }
        intent.putExtra("target_name", str2);
        startActivityForResult(intent, 3202);
    }

    public void x0(j0.i iVar, String str) {
        C.d dVarC = l0.G.c(iVar, str);
        this.f4843N = dVarC;
        l0.G.b(this.f4834E, this.f4835F, dVarC);
    }

    public void y0(j0.i iVar) {
        if (iVar == null || this.f4872x == null) {
            return;
        }
        String str = iVar.f5539k;
        String lowerCase = str == null ? TextBundle.TEXT_ENTRY : str.toLowerCase();
        String str2 = iVar.f5540l;
        String strTrim = str2 == null ? "" : str2.trim();
        if (!TextBundle.TEXT_ENTRY.equals(lowerCase) || strTrim.length() == 0) {
            Toast.makeText(this, "该消息暂不支持重新编辑", 0).show();
            return;
        }
        this.f4872x.requestFocus();
        this.f4872x.setText(strTrim);
        this.f4872x.setSelection(strTrim.length());
        if (this.f4843N != null) {
            l0();
        }
    }

    public void z0(j0.i iVar) {
        if (iVar == null || iVar.f5529a == null) {
            return;
        }
        l0.G.e(i0(), this.f4858c0, this.f4847R, this.f4848S, this.f4846Q, iVar.f5529a, A0(iVar.f5531c));
    }
}

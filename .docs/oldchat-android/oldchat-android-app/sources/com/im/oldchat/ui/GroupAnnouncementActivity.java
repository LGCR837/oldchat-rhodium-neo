package com.im.oldchat.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import o.c;

/* JADX INFO: loaded from: classes.dex */
public class GroupAnnouncementActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f2850A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public String f2851B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public int f2852C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public boolean f2853D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public boolean f2854E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public LinearLayout f2855F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public LinearLayout f2856G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public EditText f2857H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public RadioGroup f2858I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public RadioButton f2859J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public RadioButton f2860K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public TextView f2861L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public TextView f2862M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public TextView f2863N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public TextView f2864O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public TextView f2865P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public TextView f2866Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public ScrollView f2867R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public TextView f2868S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public TextView f2869T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public TextView f2870U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public final C f2871V = new C();

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public String f2872w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public String f2873x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public String f2874y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public String f2875z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            GroupAnnouncementActivity.this.F0();
        }
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2877a;

        public b(int i2) {
            this.f2877a = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            GroupAnnouncementActivity.this.f2854E = false;
            GroupAnnouncementActivity groupAnnouncementActivity = GroupAnnouncementActivity.this;
            groupAnnouncementActivity.f2875z = groupAnnouncementActivity.f2857H.getText() == null ? "" : GroupAnnouncementActivity.this.f2857H.getText().toString().trim();
            GroupAnnouncementActivity.this.f2850A = this.f2877a;
            GroupAnnouncementActivity groupAnnouncementActivity2 = GroupAnnouncementActivity.this;
            groupAnnouncementActivity2.f2851B = groupAnnouncementActivity2.f2875z;
            GroupAnnouncementActivity groupAnnouncementActivity3 = GroupAnnouncementActivity.this;
            groupAnnouncementActivity3.f2852C = groupAnnouncementActivity3.f2850A;
            Toast.makeText(GroupAnnouncementActivity.this, f0.j.K0, 0).show();
            Intent intent = new Intent();
            intent.putExtra("announcement_text", GroupAnnouncementActivity.this.f2875z);
            intent.putExtra("announcement_mode", GroupAnnouncementActivity.this.f2850A);
            GroupAnnouncementActivity.this.setResult(-1, intent);
            GroupAnnouncementActivity.this.finish();
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GroupAnnouncementActivity.this.f2854E = false;
            GroupAnnouncementActivity.this.M0();
            Toast.makeText(GroupAnnouncementActivity.this, f0.j.J0, 0).show();
        }
    }

    public class d implements DialogInterface.OnClickListener {
        public d() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            GroupAnnouncementActivity.this.C0();
        }
    }

    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GroupAnnouncementActivity.this.f2854E = false;
            GroupAnnouncementActivity.this.f2875z = "";
            GroupAnnouncementActivity.this.f2851B = "";
            GroupAnnouncementActivity groupAnnouncementActivity = GroupAnnouncementActivity.this;
            groupAnnouncementActivity.f2852C = groupAnnouncementActivity.f2850A;
            Toast.makeText(GroupAnnouncementActivity.this, f0.j.z0, 0).show();
            Intent intent = new Intent();
            intent.putExtra("announcement_text", GroupAnnouncementActivity.this.f2875z);
            intent.putExtra("announcement_mode", GroupAnnouncementActivity.this.f2850A);
            GroupAnnouncementActivity.this.setResult(-1, intent);
            GroupAnnouncementActivity.this.finish();
        }
    }

    public class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GroupAnnouncementActivity.this.f2854E = false;
            GroupAnnouncementActivity.this.M0();
            Toast.makeText(GroupAnnouncementActivity.this, f0.j.y0, 0).show();
        }
    }

    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(GroupAnnouncementActivity.this, f0.j.L0, 0).show();
            GroupAnnouncementActivity.this.finish();
        }
    }

    public class h implements Runnable {
        public h() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(GroupAnnouncementActivity.this, f0.j.J0, 0).show();
        }
    }

    public class i implements View.OnClickListener {
        public i() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            GroupAnnouncementActivity.this.finish();
        }
    }

    public class j implements View.OnClickListener {
        public j() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            GroupAnnouncementActivity.this.I0(-220);
        }
    }

    public class k implements View.OnClickListener {
        public k() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (GroupAnnouncementActivity.this.f2853D) {
                GroupAnnouncementActivity.this.K0();
            } else {
                GroupAnnouncementActivity.this.I0(220);
            }
        }
    }

    public class l implements View.OnTouchListener {
        public l() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent == null) {
                return false;
            }
            int action = motionEvent.getAction();
            if (action != 0 && action != 2) {
                return action == 1;
            }
            GroupAnnouncementActivity.this.G0(view, motionEvent.getX());
            return true;
        }
    }

    public class m implements TextWatcher {
        public m() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            GroupAnnouncementActivity.this.L0();
        }
    }

    public class n implements RadioGroup.OnCheckedChangeListener {
        public n() {
        }

        @Override // android.widget.RadioGroup.OnCheckedChangeListener
        public void onCheckedChanged(RadioGroup radioGroup, int i2) {
            GroupAnnouncementActivity.this.L0();
        }
    }

    public class o implements View.OnClickListener {
        public o() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            GroupAnnouncementActivity.this.K0();
        }
    }

    public class p implements View.OnClickListener {
        public p() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            GroupAnnouncementActivity.this.B0();
        }
    }

    public class q implements View.OnClickListener {
        public q() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            GroupAnnouncementActivity.this.finish();
        }
    }

    public final void A0() {
        TextView textView = this.f2866Q;
        if (textView != null) {
            textView.setVisibility(8);
        }
        J0(0);
        TextView textView2 = this.f2863N;
        if (textView2 != null) {
            textView2.setText(this.f2850A == 1 ? f0.j.F0 : f0.j.E0);
        }
        if (this.f2864O != null) {
            String str = this.f2875z;
            String strTrim = str == null ? "" : str.trim();
            if (strTrim.length() == 0) {
                this.f2864O.setText(f0.j.B0);
            } else {
                this.f2864O.setText(strTrim);
            }
        }
        if (this.f2865P != null) {
            String str2 = this.f2875z;
            if ((str2 != null ? str2.trim() : "").length() == 0) {
                this.f2865P.setText(f0.j.u0);
                this.f2865P.setOnClickListener(new q());
            } else {
                this.f2865P.setText(f0.j.L0);
                this.f2865P.setOnClickListener(new a());
            }
        }
    }

    public final void B0() {
        new c.a(this, f0.k.f5244a).s(f0.j.w0).h(f0.j.x0).p(f0.j.f5205b, new d()).j(f0.j.f5203a, null).v();
    }

    public final void C0() {
        String str = this.f2873x;
        if (str == null || str.length() == 0 || this.f2854E) {
            return;
        }
        this.f2854E = true;
        M0();
        this.f2871V.f(this, this.f2872w, this.f2873x, "", this.f2850A, new e(), new f());
    }

    public final boolean D0(String str, int i2) {
        String str2 = this.f2851B;
        return ((str2 == null ? "" : str2.trim()).equals(str != null ? str.trim() : "") && this.f2852C == i2) ? false : true;
    }

    public final boolean E0() {
        RadioGroup radioGroup = this.f2858I;
        return radioGroup == null ? this.f2850A == 1 : radioGroup.getCheckedRadioButtonId() == f0.f.N7;
    }

    public final void F0() {
        String str = this.f2873x;
        if (str == null || str.length() == 0) {
            return;
        }
        this.f2871V.d(this, this.f2872w, this.f2873x, new g(), new h());
    }

    public final void G0(View view, float f2) {
        EditText editText;
        if (view == null || (editText = this.f2857H) == null) {
            return;
        }
        Editable text = editText.getText();
        int length = text == null ? 0 : text.length();
        if (!this.f2857H.isFocused()) {
            this.f2857H.requestFocus();
        }
        if (length <= 0) {
            this.f2857H.setSelection(0);
            return;
        }
        int paddingLeft = view.getPaddingLeft();
        int width = (view.getWidth() - view.getPaddingRight()) - paddingLeft;
        if (width <= 0) {
            this.f2857H.setSelection(length);
            return;
        }
        float f3 = (f2 - paddingLeft) / width;
        if (f3 < 0.0f) {
            f3 = 0.0f;
        } else if (f3 > 1.0f) {
            f3 = 1.0f;
        }
        int iRound = Math.round(f3 * length);
        this.f2857H.setSelection(iRound >= 0 ? iRound > length ? length : iRound : 0);
    }

    public final void H0() {
        TextView textView = this.f2869T;
        if (textView == null) {
            return;
        }
        textView.setText(this.f2853D ? "▼发" : "▼");
    }

    public final void I0(int i2) {
        if (this.f2867R == null) {
            return;
        }
        this.f2867R.smoothScrollBy(0, (int) ((getResources().getDisplayMetrics().density * i2) + 0.5f));
    }

    public final void J0(int i2) {
        TextView textView = this.f2865P;
        if (textView == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            int i3 = (int) ((getResources().getDisplayMetrics().density * i2) + 0.5f);
            if (layoutParams2.leftMargin != i3) {
                layoutParams2.leftMargin = i3;
                this.f2865P.setLayoutParams(layoutParams2);
            }
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void K0() {
        String str = this.f2873x;
        if (str == null || str.length() == 0) {
            Toast.makeText(this, f0.j.J0, 0).show();
            return;
        }
        if (this.f2854E) {
            return;
        }
        EditText editText = this.f2857H;
        String strTrim = (editText == null || editText.getText() == null) ? "" : this.f2857H.getText().toString().trim();
        if (strTrim.length() == 0) {
            Toast.makeText(this, f0.j.D0, 0).show();
            return;
        }
        boolean zE0 = E0();
        if (!D0(strTrim, zE0 ? 1 : 0)) {
            Toast.makeText(this, f0.j.G0, 0).show();
            return;
        }
        this.f2854E = true;
        M0();
        this.f2871V.f(this, this.f2872w, this.f2873x, strTrim, zE0 ? 1 : 0, new b(zE0 ? 1 : 0), new c());
    }

    public final void L0() {
        EditText editText = this.f2857H;
        String string = (editText == null || editText.getText() == null) ? "" : this.f2857H.getText().toString();
        TextView textView = this.f2861L;
        if (textView != null) {
            textView.setText(getString(f0.j.v0, Integer.valueOf(string.length())));
        }
        if (this.f2862M != null) {
            String strTrim = string.trim();
            if (strTrim.length() == 0) {
                strTrim = getString(f0.j.B0);
            }
            this.f2862M.setText(getString(f0.j.H0, E0() ? getString(f0.j.F0) : getString(f0.j.E0), strTrim));
        }
        M0();
    }

    public final void M0() {
        if (!this.f2853D || this.f2865P == null) {
            return;
        }
        EditText editText = this.f2857H;
        String strTrim = (editText == null || editText.getText() == null) ? "" : this.f2857H.getText().toString().trim();
        boolean zE0 = E0();
        boolean z2 = false;
        boolean z3 = strTrim.length() > 0;
        boolean zD0 = D0(strTrim, zE0 ? 1 : 0);
        TextView textView = this.f2865P;
        String str = this.f2851B;
        textView.setText((str == null || str.trim().length() == 0) ? f0.j.I0 : f0.j.A0);
        TextView textView2 = this.f2865P;
        if (!this.f2854E && z3 && zD0) {
            z2 = true;
        }
        textView2.setEnabled(z2);
        TextView textView3 = this.f2866Q;
        if (textView3 == null || textView3.getVisibility() != 0) {
            return;
        }
        this.f2866Q.setEnabled(!this.f2854E);
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5163p);
        String str = "";
        this.f2872w = getSharedPreferences("auth", 0).getString("access_token", "");
        Intent intent = getIntent();
        this.f2873x = intent.getStringExtra("group_id");
        this.f2874y = intent.getStringExtra("group_name");
        this.f2875z = intent.getStringExtra("announcement_text");
        this.f2850A = intent.getIntExtra("announcement_mode", 0);
        this.f2853D = intent.getBooleanExtra("can_edit_announcement", false);
        if (this.f2875z == null) {
            this.f2875z = "";
        }
        this.f2851B = this.f2875z;
        this.f2852C = this.f2850A;
        TextView textView = (TextView) a0(f0.f.ia);
        TextView textView2 = (TextView) a0(f0.f.ha);
        this.f2855F = (LinearLayout) a0(f0.f.q6);
        this.f2856G = (LinearLayout) a0(f0.f.r6);
        this.f2857H = (EditText) a0(f0.f.A4);
        this.f2858I = (RadioGroup) a0(f0.f.R7);
        this.f2859J = (RadioButton) a0(f0.f.M7);
        this.f2860K = (RadioButton) a0(f0.f.N7);
        this.f2861L = (TextView) a0(f0.f.ga);
        this.f2862M = (TextView) a0(f0.f.ja);
        this.f2863N = (TextView) a0(f0.f.X8);
        this.f2864O = (TextView) a0(f0.f.W8);
        this.f2865P = (TextView) a0(f0.f.Y0);
        this.f2866Q = (TextView) a0(f0.f.X0);
        this.f2867R = (ScrollView) a0(f0.f.q8);
        this.f2868S = (TextView) a0(f0.f.V0);
        this.f2869T = (TextView) a0(f0.f.U0);
        this.f2870U = (TextView) a0(f0.f.ve);
        if (textView != null) {
            textView.setText(f0.j.M0);
        }
        if (textView2 != null) {
            String str2 = this.f2874y;
            if (str2 != null && str2.length() != 0) {
                str = this.f2874y;
            }
            textView2.setText(getString(f0.j.C0, str));
        }
        View viewA0 = a0(f0.f.W0);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new i());
        }
        w0();
        z0();
    }

    public final void w0() {
        TextView textView = this.f2868S;
        if (textView != null) {
            textView.setOnClickListener(new j());
        }
        TextView textView2 = this.f2869T;
        if (textView2 != null) {
            textView2.setOnClickListener(new k());
        }
        H0();
    }

    public final void x0() {
        TextView textView = this.f2870U;
        if (textView == null) {
            return;
        }
        if (!this.f2853D || this.f2857H == null) {
            textView.setVisibility(8);
            this.f2870U.setOnTouchListener(null);
        } else {
            textView.setVisibility(0);
            this.f2870U.setOnTouchListener(new l());
        }
    }

    public final void y0() {
        EditText editText = this.f2857H;
        if (editText != null) {
            String str = this.f2875z;
            if (str == null) {
                str = "";
            }
            editText.setText(str);
            this.f2857H.addTextChangedListener(new m());
        }
        x0();
        RadioGroup radioGroup = this.f2858I;
        if (radioGroup != null) {
            radioGroup.check(this.f2850A == 1 ? f0.f.N7 : f0.f.M7);
            this.f2858I.setOnCheckedChangeListener(new n());
        }
        TextView textView = this.f2865P;
        if (textView != null) {
            textView.setOnClickListener(new o());
        }
        if (this.f2866Q != null) {
            String str2 = this.f2851B;
            boolean z2 = str2 != null && str2.trim().length() > 0;
            this.f2866Q.setVisibility(z2 ? 0 : 8);
            J0(z2 ? 10 : 0);
            this.f2866Q.setOnClickListener(new p());
        }
        L0();
        M0();
    }

    public final void z0() {
        LinearLayout linearLayout = this.f2855F;
        if (linearLayout == null || this.f2856G == null) {
            H0();
            return;
        }
        if (this.f2853D) {
            linearLayout.setVisibility(0);
            this.f2856G.setVisibility(8);
            y0();
            H0();
            return;
        }
        linearLayout.setVisibility(8);
        this.f2856G.setVisibility(0);
        A0();
        H0();
    }
}

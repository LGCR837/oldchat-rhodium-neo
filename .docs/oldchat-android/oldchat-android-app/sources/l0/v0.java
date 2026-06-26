package l0;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public abstract class v0 extends u0 {
    public boolean A0 = false;
    public boolean B0 = false;
    public TextView y0;
    public TextView z0;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            v0.this.C1(false);
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            v0.this.C1(true);
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            v0 v0Var = v0.this;
            v0Var.B0 = !v0Var.B0;
            v0Var.w1();
        }
    }

    private void A1() {
        TextView textView = this.y0;
        if (textView != null) {
            textView.setBackgroundResource(this.A0 ? f0.e.f5035v : f0.e.f5034u);
            this.y0.setTextColor(getResources().getColor(this.A0 ? f0.d.f4974k : f0.d.f4970g));
        }
        TextView textView2 = this.z0;
        if (textView2 != null) {
            textView2.setBackgroundResource(this.A0 ? f0.e.f5034u : f0.e.f5035v);
            this.z0.setTextColor(getResources().getColor(this.A0 ? f0.d.f4970g : f0.d.f4974k));
        }
    }

    @Override // l0.t0
    public void A0(boolean z2, String str) {
        super.A0(this.A0 && z2, str);
    }

    public void B1() {
        this.B0 = false;
        w1();
    }

    public final void C1(boolean z2) {
        if (this.A0 == z2 && this.y0 != null && this.z0 != null) {
            A1();
            return;
        }
        this.A0 = z2;
        A1();
        z1(z2);
    }

    public final void w1() {
        TextView textView = this.f6307X;
        if (textView == null) {
            return;
        }
        if (this.B0) {
            textView.setMaxLines(12);
            this.f6307X.setEllipsize(null);
        } else {
            textView.setMaxLines(2);
            this.f6307X.setEllipsize(TextUtils.TruncateAt.END);
        }
    }

    public void x1(View view) {
        if (view == null) {
            return;
        }
        this.y0 = (TextView) view.findViewById(f0.f.K8);
        this.z0 = (TextView) view.findViewById(f0.f.J8);
        TextView textView = this.y0;
        if (textView != null) {
            textView.setOnClickListener(new a());
        }
        TextView textView2 = this.z0;
        if (textView2 != null) {
            textView2.setOnClickListener(new b());
        }
        C1(false);
    }

    public void y1() {
        TextView textView = this.f6307X;
        if (textView == null) {
            return;
        }
        textView.setOnClickListener(new c());
        w1();
    }

    public abstract void z1(boolean z2);
}

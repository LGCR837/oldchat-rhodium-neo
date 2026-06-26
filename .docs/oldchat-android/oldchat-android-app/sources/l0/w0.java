package l0;

import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

/* JADX INFO: loaded from: classes.dex */
public abstract class w0 extends v0 {
    public int C0 = 0;
    public int D0 = 0;
    public int E0 = 0;
    public int F0 = 0;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EditText editText = w0.this.f6284A;
            String strTrim = (editText == null || editText.getText() == null) ? "" : w0.this.f6284A.getText().toString().trim();
            if (strTrim.length() == 0) {
                Toast.makeText(w0.this, "请输入评论内容", 0).show();
            } else {
                w0.this.H0(strTrim);
            }
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            w0.this.finish();
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            w0.this.I0();
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            w0.this.u0("play click");
            w0.this.Y0();
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            w0.this.z0(false);
            String str = w0.this.p0;
            if (str == null || str.length() <= 0) {
                w0.this.Y0();
            } else {
                w0 w0Var = w0.this;
                w0Var.U0(w0Var.p0);
            }
        }
    }

    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            w0.this.R0();
        }
    }

    public class g implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f6351a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f6352b;

        public g(int i2, int i3) {
            this.f6351a = i2;
            this.f6352b = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            int count;
            ListView listView = w0.this.f6326x;
            if (listView == null || listView.getAdapter() == null || (count = w0.this.f6326x.getAdapter().getCount()) <= 0) {
                return;
            }
            int i2 = this.f6351a;
            if (i2 < 0) {
                i2 = 0;
            } else if (i2 >= count) {
                i2 = count - 1;
            }
            w0.this.f6326x.setSelectionFromTop(i2, this.f6352b);
        }
    }

    public final void D1(boolean z2) {
        ListView listView = this.f6326x;
        if (listView == null) {
            return;
        }
        listView.post(new g(z2 ? this.E0 : this.C0, z2 ? this.F0 : this.D0));
    }

    public final void E1() {
        ListView listView = this.f6326x;
        if (listView == null) {
            return;
        }
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        View childAt = this.f6326x.getChildCount() > 0 ? this.f6326x.getChildAt(0) : null;
        int top = childAt != null ? childAt.getTop() : 0;
        if (this.f6326x.getAdapter() == this.f6308Y) {
            this.E0 = firstVisiblePosition;
            this.F0 = top;
        } else {
            this.C0 = firstVisiblePosition;
            this.D0 = top;
        }
    }

    public void F1() {
        y1();
        View view = this.f6285B;
        if (view != null) {
            view.setOnClickListener(new a());
        }
        View viewA0 = a0(f0.f.o2);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new b());
        }
        View view2 = this.f6328z;
        if (view2 != null) {
            view2.setOnClickListener(new c());
        }
        View view3 = this.f6290G;
        if (view3 != null) {
            view3.setOnClickListener(new d());
        }
        View view4 = this.f6292I;
        if (view4 != null) {
            view4.setOnClickListener(new e());
        }
        View view5 = this.f6291H;
        if (view5 != null) {
            view5.setOnClickListener(new f());
        }
    }

    @Override // l0.v0
    public void z1(boolean z2) {
        if (this.f6326x == null) {
            return;
        }
        E1();
        ListAdapter listAdapter = z2 ? this.f6308Y : this.f6309Z;
        if (listAdapter != null && this.f6326x.getAdapter() != listAdapter) {
            this.f6326x.setAdapter(listAdapter);
        }
        View view = this.f6310a0;
        if (view != null) {
            view.setVisibility(z2 ? 0 : 8);
        }
        A0(false, null);
        D1(z2);
        if (!z2 || this.f6314e0 > 0 || this.f6315f0) {
            return;
        }
        F0(1, true);
    }
}

package l0;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public abstract class G0 extends I0 {

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            G0.this.finish();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            G0.this.o0();
        }
    }

    public class c implements AdapterView.OnItemClickListener {
        public c() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            if (i2 < 0 || i2 >= G0.this.f5866A.size()) {
                return;
            }
            G0 g02 = G0.this;
            g02.m0((j0.u) g02.f5866A.get(i2));
        }
    }

    public class d implements AdapterView.OnItemLongClickListener {
        public d() {
        }

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView adapterView, View view, int i2, long j2) {
            j0.u uVar;
            if (i2 < 0 || i2 >= G0.this.f5866A.size() || (uVar = (j0.u) G0.this.f5866A.get(i2)) == null || !G0.this.p0(uVar)) {
                return false;
            }
            G0.this.q0(uVar);
            return true;
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5140d0);
        this.f5870w = (ListView) a0(f0.f.d7);
        this.f5871x = (ProgressBar) a0(f0.f.C7);
        this.f5872y = (TextView) a0(f0.f.md);
        View viewA0 = a0(f0.f.r3);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        View viewA02 = a0(f0.f.q3);
        if (viewA02 != null) {
            viewA02.setOnClickListener(new b());
        }
        F0 f02 = new F0(this, this.f5866A);
        this.f5873z = f02;
        this.f5870w.setAdapter((ListAdapter) f02);
        this.f5870w.setOnItemClickListener(new c());
        this.f5870w.setOnItemLongClickListener(new d());
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("auth", 0);
        this.f5867B = sharedPreferences.getString("access_token", "");
        this.f5868C = sharedPreferences.getString("my_uid", "");
        i0();
        k0();
    }
}

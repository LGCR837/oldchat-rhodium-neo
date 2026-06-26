package com.im.oldchat.ui;

import android.os.Bundle;
import android.widget.Toast;
import l0.AbstractActivityC0393m0;

/* JADX INFO: loaded from: classes.dex */
public class OldViewUpProfileActivity extends AbstractActivityC0393m0 {
    @Override // l0.AbstractActivityC0391l0, c.AbstractActivityC0167u, android.app.Activity
    public /* bridge */ /* synthetic */ void onBackPressed() {
        super.onBackPressed();
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5121O);
        g0();
        f0();
        l0();
        if (this.f6182L <= 0) {
            Toast.makeText(this, getString(f0.j.q2), 0).show();
            finish();
            return;
        }
        m0();
        this.f6184N = h0.a.b(this);
        this.f6185O = h0.a.c(this);
        q0();
        t0();
        A0();
        r0(false);
        y0();
        i0(1, true);
    }
}

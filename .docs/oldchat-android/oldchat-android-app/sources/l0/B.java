package l0;

import android.content.Intent;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.im.oldchat.ui.AbstractActivityC0233y;
import com.im.oldchat.ui.AbstractC0217h;
import com.im.oldchat.ui.C0218i;

/* JADX INFO: loaded from: classes.dex */
public abstract class B extends AbstractActivityC0233y {
    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        D d2;
        String stringExtra;
        TextView textView;
        super.onActivityResult(i2, i3, intent);
        if (i2 == 3005 && i3 == -1) {
            finish();
            return;
        }
        if (i2 == 3005 && i3 == 1) {
            if (intent == null || (stringExtra = intent.getStringExtra("group_name")) == null || stringExtra.isEmpty() || (textView = this.f4842M) == null) {
                return;
            }
            this.f4856a0 = stringExtra;
            textView.setText(stringExtra);
            return;
        }
        if (i2 == 3201 && i3 == -1 && intent != null) {
            String stringExtra2 = intent.getStringExtra("emoji_path");
            boolean booleanExtra = intent.getBooleanExtra("emoji_is_gif", false);
            com.im.oldchat.ui.O o2 = this.f4867l0;
            if (o2 != null) {
                o2.o(stringExtra2, booleanExtra, this.f4843N);
                return;
            }
            return;
        }
        if (i2 == 3004 && i3 == -1 && intent != null) {
            Uri data = intent.getData();
            C0370b c0370b = this.f4868m0;
            if (c0370b != null) {
                c0370b.h(data);
                return;
            }
            return;
        }
        if (i2 != 3202 || i3 != -1 || intent == null) {
            C0218i c0218i = this.f4845P;
            if (c0218i != null) {
                c0218i.d0(i2, i3, intent);
                return;
            }
            return;
        }
        String stringExtra3 = intent.getStringExtra("message_json");
        if (stringExtra3 == null || stringExtra3.isEmpty() || (d2 = this.f4866k0) == null) {
            return;
        }
        j0.i iVarL = d2.l(stringExtra3);
        if (iVarL != null) {
            this.f4866k0.b(iVarL, true);
        } else {
            this.f4866k0.x(this.f4858c0, false, 0L, false);
        }
    }

    @Override // android.app.Activity
    public boolean onContextItemSelected(MenuItem menuItem) {
        if (AbstractC0217h.j(this.f4872x, menuItem)) {
            return true;
        }
        return super.onContextItemSelected(menuItem);
    }

    @Override // android.app.Activity, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        if (view == this.f4872x) {
            AbstractC0217h.i(contextMenu);
        }
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onPause() {
        String str;
        super.onPause();
        this.f4873y = AbstractC0372c.c(this, this.f4872x, q0(), this.f4873y);
        I0();
        k0();
        D d2 = this.f4866k0;
        if (d2 != null) {
            d2.m();
        }
        o.c cVar = this.x0;
        if (cVar != null && cVar.isShowing()) {
            this.x0.dismiss();
        }
        C0218i c0218i = this.f4845P;
        if (c0218i != null) {
            c0218i.h0();
        }
        com.im.oldchat.ui.G g2 = this.f4846Q;
        if (g2 != null) {
            g2.x0();
        }
        g0.j.u().x(this.f4862g0);
        g0.j.u().x(this.F0);
        if (this.C0 != null && (str = this.f4855Z) != null && !str.isEmpty()) {
            if (this.A0) {
                this.C0.v(this, this.f4858c0, this.f4855Z, true);
                this.A0 = false;
            }
            this.C0.u(this.f4855Z);
            this.C0.w(this.f4855Z);
        }
        this.D0.removeCallbacks(this.E0);
        X0();
        this.f4861f0 = true;
    }

    @Override // f0.a, c.AbstractActivityC0167u, android.app.Activity, c.AbstractC0121a.b
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        C0218i c0218i = this.f4845P;
        if (c0218i == null || !c0218i.e0(i2, iArr)) {
            super.onRequestPermissionsResult(i2, strArr, iArr);
        }
    }
}

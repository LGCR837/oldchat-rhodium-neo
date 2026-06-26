package l0;

import android.content.Intent;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.im.oldchat.ui.AbstractActivityC0211b;
import com.im.oldchat.ui.AbstractC0217h;
import com.im.oldchat.ui.C0218i;
import com.im.oldchat.ui.C0226q;

/* JADX INFO: renamed from: l0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractActivityC0368a extends AbstractActivityC0211b {
    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        C0386j c0386j;
        String stringExtra;
        super.onActivityResult(i2, i3, intent);
        if (i2 == 3005 && i3 == -1) {
            if (intent != null && intent.getBooleanExtra("friend_deleted", false)) {
                finish();
                return;
            }
            if (intent != null && (stringExtra = intent.getStringExtra("friend_name")) != null && stringExtra.length() > 0) {
                this.f4401R = stringExtra;
                TextView textView = (TextView) a0(f0.f.h9);
                if (textView != null) {
                    textView.setText(this.f4401R);
                }
            }
            String str = this.f4400Q;
            if (str != null && str.length() > 0) {
                String str2 = this.f4401R;
                j0.r.j(this, this.f4400Q, (str2 == null || str2.length() <= 0) ? this.f4400Q : this.f4401R, this.f4402S);
            }
            C0386j c0386j2 = this.f4410a0;
            if (c0386j2 != null) {
                c0386j2.z(this.f4404U, false, 0L, false);
                return;
            }
            return;
        }
        if (i2 == 3101 && i3 == -1 && intent != null) {
            String stringExtra2 = intent.getStringExtra("emoji_path");
            boolean booleanExtra = intent.getBooleanExtra("emoji_is_gif", false);
            C0226q c0226q = this.f4411b0;
            if (c0226q != null) {
                c0226q.k(stringExtra2, booleanExtra, this.f4394K);
                return;
            }
            return;
        }
        if (i2 == 3004 && i3 == -1 && intent != null) {
            Uri data = intent.getData();
            C0370b c0370b = this.f4412c0;
            if (c0370b != null) {
                c0370b.h(data);
                return;
            }
            return;
        }
        if (i2 != 3102 || i3 != -1 || intent == null) {
            C0218i c0218i = this.f4396M;
            if (c0218i != null) {
                c0218i.d0(i2, i3, intent);
                return;
            }
            return;
        }
        String stringExtra3 = intent.getStringExtra("message_json");
        if (stringExtra3 == null || stringExtra3.isEmpty() || (c0386j = this.f4410a0) == null) {
            return;
        }
        j0.k kVarL = c0386j.l(stringExtra3);
        if (kVarL != null) {
            this.f4410a0.a(kVarL, true, this.f4404U);
        } else {
            this.f4410a0.z(this.f4404U, false, 0L, false);
        }
    }

    @Override // android.app.Activity
    public boolean onContextItemSelected(MenuItem menuItem) {
        if (AbstractC0217h.j(this.f4421x, menuItem)) {
            return true;
        }
        return super.onContextItemSelected(menuItem);
    }

    @Override // android.app.Activity, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        if (view == this.f4421x) {
            AbstractC0217h.i(contextMenu);
        }
    }

    @Override // f0.a, c.AbstractActivityC0167u, android.app.Activity, c.AbstractC0121a.b
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        C0218i c0218i = this.f4396M;
        if (c0218i == null || !c0218i.e0(i2, iArr)) {
            super.onRequestPermissionsResult(i2, strArr, iArr);
        }
    }
}

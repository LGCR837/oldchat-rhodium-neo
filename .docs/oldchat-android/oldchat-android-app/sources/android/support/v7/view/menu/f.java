package android.support.v7.view.menu;

import android.content.DialogInterface;
import android.os.IBinder;
import android.support.v7.view.menu.i;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import o.c;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: classes.dex */
public class f implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, i.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public e f1077a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public o.c f1078b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c f1079c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public i.a f1080d;

    public f(e eVar) {
        this.f1077a = eVar;
    }

    @Override // android.support.v7.view.menu.i.a
    public void a(e eVar, boolean z2) {
        if (z2 || eVar == this.f1077a) {
            c();
        }
        i.a aVar = this.f1080d;
        if (aVar != null) {
            aVar.a(eVar, z2);
        }
    }

    @Override // android.support.v7.view.menu.i.a
    public boolean b(e eVar) {
        i.a aVar = this.f1080d;
        if (aVar != null) {
            return aVar.b(eVar);
        }
        return false;
    }

    public void c() {
        o.c cVar = this.f1078b;
        if (cVar != null) {
            cVar.dismiss();
        }
    }

    public void d(IBinder iBinder) {
        e eVar = this.f1077a;
        c.a aVar = new c.a(eVar.u());
        c cVar = new c(aVar.b(), p.g.f7825i);
        this.f1079c = cVar;
        cVar.f(this);
        this.f1077a.b(this.f1079c);
        aVar.c(this.f1079c.c(), this);
        View viewY = eVar.y();
        if (viewY != null) {
            aVar.e(viewY);
        } else {
            aVar.f(eVar.w()).t(eVar.x());
        }
        aVar.o(this);
        o.c cVarA = aVar.a();
        this.f1078b = cVarA;
        cVarA.setOnDismissListener(this);
        WindowManager.LayoutParams attributes = this.f1078b.getWindow().getAttributes();
        attributes.type = 1003;
        if (iBinder != null) {
            attributes.token = iBinder;
        }
        attributes.flags |= PKIFailureInfo.unsupportedVersion;
        this.f1078b.show();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i2) {
        this.f1077a.K((g) this.f1079c.c().getItem(i2), 0);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.f1079c.a(this.f1077a, true);
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        if (i2 == 82 || i2 == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.f1078b.getWindow();
                if (window2 != null && (decorView2 = window2.getDecorView()) != null && (keyDispatcherState2 = decorView2.getKeyDispatcherState()) != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.f1078b.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.f1077a.e(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.f1077a.performShortcut(i2, keyEvent, 0);
    }
}

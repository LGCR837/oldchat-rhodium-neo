package c;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;

/* JADX INFO: renamed from: c.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractActivityC0163s extends r {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1987b;

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i2, Bundle bundle) {
        if (!this.f1987b && i2 != -1) {
            AbstractActivityC0160q.t(i2);
        }
        super.startActivityForResult(intent, i2, bundle);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5, Bundle bundle) throws IntentSender.SendIntentException {
        if (!this.f1986a && i2 != -1) {
            AbstractActivityC0160q.t(i2);
        }
        super.startIntentSenderForResult(intentSender, i2, intent, i3, i4, i5, bundle);
    }
}

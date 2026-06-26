package d;

import android.content.ComponentName;
import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public abstract class p {
    public static Intent a(ComponentName componentName) {
        return Intent.makeMainActivity(componentName);
    }
}

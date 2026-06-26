package k;

import android.view.KeyEvent;

/* JADX INFO: renamed from: k.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0328z {
    public static boolean a(KeyEvent keyEvent) {
        return keyEvent.isCtrlPressed();
    }

    public static boolean b(int i2, int i3) {
        return KeyEvent.metaStateHasModifiers(i2, i3);
    }

    public static boolean c(int i2) {
        return KeyEvent.metaStateHasNoModifiers(i2);
    }

    public static int d(int i2) {
        return KeyEvent.normalizeMetaState(i2);
    }
}

package l;

import android.view.accessibility.AccessibilityNodeInfo;

/* JADX INFO: renamed from: l.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0350h {
    public static Object a(int i2, int i3, boolean z2, int i4) {
        return AccessibilityNodeInfo.CollectionInfo.obtain(i2, i3, z2, i4);
    }

    public static Object b(int i2, int i3, int i4, int i5, boolean z2, boolean z3) {
        return AccessibilityNodeInfo.CollectionItemInfo.obtain(i2, i3, i4, i5, z2, z3);
    }
}

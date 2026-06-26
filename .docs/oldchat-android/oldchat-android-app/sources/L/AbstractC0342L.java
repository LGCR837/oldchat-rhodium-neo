package l;

import android.view.accessibility.AccessibilityNodeInfo;
import k.AbstractC0260c;

/* JADX INFO: renamed from: l.L, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0342L {
    public static Object a(int i2, int i3, boolean z2, int i4) {
        return AccessibilityNodeInfo.CollectionInfo.obtain(i2, i3, z2);
    }

    public static Object b(int i2, int i3, int i4, int i5, boolean z2) {
        return AccessibilityNodeInfo.CollectionItemInfo.obtain(i2, i3, i4, i5, z2);
    }

    public static void c(Object obj, Object obj2) {
        AbstractC0260c.a(obj).setCollectionInfo(AbstractC0339I.a(obj2));
    }

    public static void d(Object obj, Object obj2) {
        AbstractC0260c.a(obj).setCollectionItemInfo(AbstractC0336F.a(obj2));
    }
}

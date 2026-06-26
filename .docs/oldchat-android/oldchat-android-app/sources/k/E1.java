package k;

import android.util.Log;
import android.view.View;
import android.view.ViewParent;

/* JADX INFO: loaded from: classes.dex */
public abstract class E1 {
    public static boolean a(ViewParent viewParent, View view, float f2, float f3, boolean z2) {
        try {
            return viewParent.onNestedFling(view, f2, f3, z2);
        } catch (AbstractMethodError e2) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedFling", e2);
            return false;
        }
    }

    public static boolean b(ViewParent viewParent, View view, float f2, float f3) {
        try {
            return viewParent.onNestedPreFling(view, f2, f3);
        } catch (AbstractMethodError e2) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedPreFling", e2);
            return false;
        }
    }

    public static void c(ViewParent viewParent, View view, int i2, int i3, int[] iArr) {
        try {
            viewParent.onNestedPreScroll(view, i2, i3, iArr);
        } catch (AbstractMethodError e2) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedPreScroll", e2);
        }
    }

    public static void d(ViewParent viewParent, View view, int i2, int i3, int i4, int i5) {
        try {
            viewParent.onNestedScroll(view, i2, i3, i4, i5);
        } catch (AbstractMethodError e2) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedScroll", e2);
        }
    }

    public static void e(ViewParent viewParent, View view, View view2, int i2) {
        try {
            viewParent.onNestedScrollAccepted(view, view2, i2);
        } catch (AbstractMethodError e2) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedScrollAccepted", e2);
        }
    }

    public static boolean f(ViewParent viewParent, View view, View view2, int i2) {
        try {
            return viewParent.onStartNestedScroll(view, view2, i2);
        } catch (AbstractMethodError e2) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onStartNestedScroll", e2);
            return false;
        }
    }

    public static void g(ViewParent viewParent, View view) {
        try {
            viewParent.onStopNestedScroll(view);
        } catch (AbstractMethodError e2) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onStopNestedScroll", e2);
        }
    }
}

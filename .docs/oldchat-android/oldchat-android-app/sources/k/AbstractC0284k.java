package k;

import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/* JADX INFO: renamed from: k.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0284k {

    /* JADX INFO: renamed from: k.k$a */
    public static class a extends View.AccessibilityDelegate {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b f5698a;

        public a(b bVar) {
            this.f5698a = bVar;
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            return this.f5698a.a(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f5698a.e(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            this.f5698a.b(view, accessibilityNodeInfo);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f5698a.c(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return this.f5698a.f(viewGroup, view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEvent(View view, int i2) {
            this.f5698a.g(view, i2);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            this.f5698a.d(view, accessibilityEvent);
        }
    }

    /* JADX INFO: renamed from: k.k$b */
    public interface b {
        boolean a(View view, AccessibilityEvent accessibilityEvent);

        void b(View view, Object obj);

        void c(View view, AccessibilityEvent accessibilityEvent);

        void d(View view, AccessibilityEvent accessibilityEvent);

        void e(View view, AccessibilityEvent accessibilityEvent);

        boolean f(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent);

        void g(View view, int i2);
    }

    public static boolean a(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        return AbstractC0257b.a(obj).dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public static Object b(b bVar) {
        return new a(bVar);
    }

    public static Object c() {
        return new View.AccessibilityDelegate();
    }

    public static void d(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        AbstractC0257b.a(obj).onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public static void e(Object obj, View view, Object obj2) {
        AbstractC0257b.a(obj).onInitializeAccessibilityNodeInfo(view, AbstractC0260c.a(obj2));
    }

    public static void f(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        AbstractC0257b.a(obj).onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public static boolean g(Object obj, ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return AbstractC0257b.a(obj).onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public static void h(Object obj, View view, int i2) {
        AbstractC0257b.a(obj).sendAccessibilityEvent(view, i2);
    }

    public static void i(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        AbstractC0257b.a(obj).sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }
}

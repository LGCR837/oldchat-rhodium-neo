package k;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;

/* JADX INFO: renamed from: k.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0296o {

    /* JADX INFO: renamed from: k.o$a */
    public static class a extends View.AccessibilityDelegate {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b f5710a;

        public a(b bVar) {
            this.f5710a = bVar;
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            return this.f5710a.a(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
            return AbstractC0293n.a(this.f5710a.h(view));
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f5710a.e(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            this.f5710a.b(view, accessibilityNodeInfo);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f5710a.c(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return this.f5710a.f(viewGroup, view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
            return this.f5710a.i(view, i2, bundle);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEvent(View view, int i2) {
            this.f5710a.g(view, i2);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            this.f5710a.d(view, accessibilityEvent);
        }
    }

    /* JADX INFO: renamed from: k.o$b */
    public interface b {
        boolean a(View view, AccessibilityEvent accessibilityEvent);

        void b(View view, Object obj);

        void c(View view, AccessibilityEvent accessibilityEvent);

        void d(View view, AccessibilityEvent accessibilityEvent);

        void e(View view, AccessibilityEvent accessibilityEvent);

        boolean f(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent);

        void g(View view, int i2);

        Object h(View view);

        boolean i(View view, int i2, Bundle bundle);
    }

    public static Object a(Object obj, View view) {
        return AbstractC0257b.a(obj).getAccessibilityNodeProvider(view);
    }

    public static Object b(b bVar) {
        return new a(bVar);
    }

    public static boolean c(Object obj, View view, int i2, Bundle bundle) {
        return AbstractC0257b.a(obj).performAccessibilityAction(view, i2, bundle);
    }
}

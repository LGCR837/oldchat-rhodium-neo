package k;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class B {

    public static class a implements LayoutInflater.Factory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final H f5655a;

        public a(H h2) {
            this.f5655a = h2;
        }

        @Override // android.view.LayoutInflater.Factory
        public View onCreateView(String str, Context context, AttributeSet attributeSet) {
            return this.f5655a.onCreateView(null, str, context, attributeSet);
        }

        public String toString() {
            return getClass().getName() + "{" + this.f5655a + "}";
        }
    }

    public static H a(LayoutInflater layoutInflater) {
        LayoutInflater.Factory factory = layoutInflater.getFactory();
        if (factory instanceof a) {
            return ((a) factory).f5655a;
        }
        return null;
    }

    public static void b(LayoutInflater layoutInflater, H h2) {
        layoutInflater.setFactory(h2 != null ? new a(h2) : null);
    }
}

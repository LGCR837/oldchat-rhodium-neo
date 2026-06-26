package y;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import k.AbstractC0285k0;
import s.AbstractC0468b;

/* JADX INFO: renamed from: y.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0527e extends C0526d {
    @Override // y.C0526d, y.InterfaceC0524b
    public void a(View view) {
        Object tag = view.getTag(AbstractC0468b.f7912a);
        if (tag != null && (tag instanceof Float)) {
            AbstractC0285k0.O(view, ((Float) tag).floatValue());
        }
        view.setTag(AbstractC0468b.f7912a, null);
        super.a(view);
    }

    @Override // y.C0526d, y.InterfaceC0524b
    public void b(Canvas canvas, RecyclerView recyclerView, View view, float f2, float f3, int i2, boolean z2) {
        if (z2 && view.getTag(AbstractC0468b.f7912a) == null) {
            Float fValueOf = Float.valueOf(AbstractC0285k0.h(view));
            AbstractC0285k0.O(view, e(recyclerView, view) + 1.0f);
            view.setTag(AbstractC0468b.f7912a, fValueOf);
        }
        super.b(canvas, recyclerView, view, f2, f3, i2, z2);
    }

    public final float e(RecyclerView recyclerView, View view) {
        int childCount = recyclerView.getChildCount();
        float f2 = 0.0f;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            if (childAt != view) {
                float fH = AbstractC0285k0.h(childAt);
                if (fH > f2) {
                    f2 = fH;
                }
            }
        }
        return f2;
    }
}

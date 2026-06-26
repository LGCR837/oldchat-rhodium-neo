package c;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* JADX INFO: renamed from: c.l0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0151l0 extends FrameLayout {
    public C0151l0(Context context) {
        super(context);
    }

    public static ViewGroup a(View view) {
        C0151l0 c0151l0 = new C0151l0(view.getContext());
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            c0151l0.setLayoutParams(layoutParams);
        }
        view.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        c0151l0.addView(view);
        return c0151l0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchSaveInstanceState(SparseArray sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }
}

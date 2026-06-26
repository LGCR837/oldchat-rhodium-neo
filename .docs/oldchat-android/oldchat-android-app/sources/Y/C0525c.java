package y;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/* JADX INFO: renamed from: y.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0525c implements InterfaceC0524b {
    @Override // y.InterfaceC0524b
    public void a(View view) {
        view.setVisibility(0);
    }

    @Override // y.InterfaceC0524b
    public void b(Canvas canvas, RecyclerView recyclerView, View view, float f2, float f3, int i2, boolean z2) {
        if (i2 != 2) {
            e(canvas, recyclerView, view, f2, f3);
        }
    }

    @Override // y.InterfaceC0524b
    public void c(Canvas canvas, RecyclerView recyclerView, View view, float f2, float f3, int i2, boolean z2) {
        if (i2 == 2) {
            e(canvas, recyclerView, view, f2, f3);
        }
    }

    @Override // y.InterfaceC0524b
    public void d(View view) {
        view.setVisibility(4);
    }

    public final void e(Canvas canvas, RecyclerView recyclerView, View view, float f2, float f3) {
        canvas.save();
        canvas.translate(f2, f3);
        recyclerView.drawChild(canvas, view, 0L);
        canvas.restore();
    }
}

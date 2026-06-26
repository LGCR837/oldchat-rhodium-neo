package x;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.RatingBar;
import k.AbstractC0285k0;
import p.AbstractC0460a;

/* JADX INFO: renamed from: x.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0516t extends RatingBar {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public r f8200a;

    public C0516t(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, AbstractC0460a.f7701E);
    }

    @Override // android.widget.RatingBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public synchronized void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        Bitmap bitmapB = this.f8200a.b();
        if (bitmapB != null) {
            setMeasuredDimension(AbstractC0285k0.I(bitmapB.getWidth() * getNumStars(), i2, 0), getMeasuredHeight());
        }
    }

    public C0516t(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        r rVar = new r(this);
        this.f8200a = rVar;
        rVar.c(attributeSet, i2);
    }
}

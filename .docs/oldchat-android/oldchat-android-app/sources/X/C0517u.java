package x;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.SeekBar;
import p.AbstractC0460a;

/* JADX INFO: renamed from: x.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0517u extends SeekBar {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public C0518v f8201a;

    public C0517u(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, AbstractC0460a.f7702F);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        this.f8201a.h();
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        this.f8201a.i();
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f8201a.g(canvas);
    }

    public C0517u(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        C0518v c0518v = new C0518v(this);
        this.f8201a = c0518v;
        c0518v.c(attributeSet, i2);
    }
}

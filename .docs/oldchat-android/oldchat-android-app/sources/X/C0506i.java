package x;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

/* JADX INFO: renamed from: x.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0506i extends CheckedTextView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f8159b = {R.attr.checkMark};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public C0496B f8160a;

    public C0506i(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.checkedTextViewStyle);
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0496B c0496b = this.f8160a;
        if (c0496b != null) {
            c0496b.b();
        }
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i2) {
        setCheckMarkDrawable(q.b.d(getContext(), i2));
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        C0496B c0496b = this.f8160a;
        if (c0496b != null) {
            c0496b.f(context, i2);
        }
    }

    public C0506i(Context context, AttributeSet attributeSet, int i2) {
        super(c0.b(context), attributeSet, i2);
        C0496B c0496bC = C0496B.c(this);
        this.f8160a = c0496bC;
        c0496bC.e(attributeSet, i2);
        this.f8160a.b();
        f0 f0VarS = f0.s(getContext(), attributeSet, f8159b, i2, 0);
        setCheckMarkDrawable(f0VarS.f(0));
        f0VarS.t();
    }
}

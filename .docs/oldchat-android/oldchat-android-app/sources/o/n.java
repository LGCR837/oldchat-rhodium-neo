package o;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import p.AbstractC0460a;
import v.AbstractC0473b;

/* JADX INFO: loaded from: classes.dex */
public abstract class n extends Dialog implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public f f6925a;

    public n(Context context, int i2) {
        super(context, b(context, i2));
        a().p(null);
        a().d();
    }

    public static int b(Context context, int i2) {
        if (i2 != 0) {
            return i2;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(AbstractC0460a.f7729w, typedValue, true);
        return typedValue.resourceId;
    }

    public f a() {
        if (this.f6925a == null) {
            this.f6925a = f.f(this, this);
        }
        return this.f6925a;
    }

    @Override // android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a().c(view, layoutParams);
    }

    public boolean c(int i2) {
        return a().w(i2);
    }

    @Override // android.app.Dialog
    public View findViewById(int i2) {
        return a().h(i2);
    }

    @Override // o.e
    public AbstractC0473b h(AbstractC0473b.a aVar) {
        return null;
    }

    @Override // android.app.Dialog
    public void invalidateOptionsMenu() {
        a().m();
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        a().l();
        super.onCreate(bundle);
        a().p(bundle);
    }

    @Override // android.app.Dialog
    public void onStop() {
        super.onStop();
        a().v();
    }

    @Override // android.app.Dialog
    public void setContentView(int i2) {
        a().y(i2);
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        a().D(charSequence);
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        a().z(view);
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a().A(view, layoutParams);
    }

    @Override // android.app.Dialog
    public void setTitle(int i2) {
        super.setTitle(i2);
        a().D(getContext().getString(i2));
    }

    @Override // o.e
    public void e(AbstractC0473b abstractC0473b) {
    }

    @Override // o.e
    public void p(AbstractC0473b abstractC0473b) {
    }
}

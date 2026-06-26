package o;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import o.b;
import p.AbstractC0460a;

/* JADX INFO: loaded from: classes.dex */
public class c extends n implements DialogInterface {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f6825b;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b.f f6826a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f6827b;

        public a(Context context) {
            this(context, c.f(context, 0));
        }

        public c a() {
            c cVar = new c(this.f6826a.f6786a, this.f6827b);
            this.f6826a.a(cVar.f6825b);
            cVar.setCancelable(this.f6826a.f6800o);
            if (this.f6826a.f6800o) {
                cVar.setCanceledOnTouchOutside(true);
            }
            cVar.setOnCancelListener(this.f6826a.f6801p);
            cVar.setOnDismissListener(this.f6826a.f6802q);
            DialogInterface.OnKeyListener onKeyListener = this.f6826a.f6803r;
            if (onKeyListener != null) {
                cVar.setOnKeyListener(onKeyListener);
            }
            return cVar;
        }

        public Context b() {
            return this.f6826a.f6786a;
        }

        public a c(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            b.f fVar = this.f6826a;
            fVar.f6805t = listAdapter;
            fVar.f6806u = onClickListener;
            return this;
        }

        public a d(boolean z2) {
            this.f6826a.f6800o = z2;
            return this;
        }

        public a e(View view) {
            this.f6826a.f6792g = view;
            return this;
        }

        public a f(Drawable drawable) {
            this.f6826a.f6789d = drawable;
            return this;
        }

        public a g(CharSequence[] charSequenceArr, DialogInterface.OnClickListener onClickListener) {
            b.f fVar = this.f6826a;
            fVar.f6804s = charSequenceArr;
            fVar.f6806u = onClickListener;
            return this;
        }

        public a h(int i2) {
            b.f fVar = this.f6826a;
            fVar.f6793h = fVar.f6786a.getText(i2);
            return this;
        }

        public a i(CharSequence charSequence) {
            this.f6826a.f6793h = charSequence;
            return this;
        }

        public a j(int i2, DialogInterface.OnClickListener onClickListener) {
            b.f fVar = this.f6826a;
            fVar.f6796k = fVar.f6786a.getText(i2);
            this.f6826a.f6797l = onClickListener;
            return this;
        }

        public a k(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            b.f fVar = this.f6826a;
            fVar.f6796k = charSequence;
            fVar.f6797l = onClickListener;
            return this;
        }

        public a l(int i2, DialogInterface.OnClickListener onClickListener) {
            b.f fVar = this.f6826a;
            fVar.f6798m = fVar.f6786a.getText(i2);
            this.f6826a.f6799n = onClickListener;
            return this;
        }

        public a m(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            b.f fVar = this.f6826a;
            fVar.f6798m = charSequence;
            fVar.f6799n = onClickListener;
            return this;
        }

        public a n(DialogInterface.OnCancelListener onCancelListener) {
            this.f6826a.f6801p = onCancelListener;
            return this;
        }

        public a o(DialogInterface.OnKeyListener onKeyListener) {
            this.f6826a.f6803r = onKeyListener;
            return this;
        }

        public a p(int i2, DialogInterface.OnClickListener onClickListener) {
            b.f fVar = this.f6826a;
            fVar.f6794i = fVar.f6786a.getText(i2);
            this.f6826a.f6795j = onClickListener;
            return this;
        }

        public a q(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            b.f fVar = this.f6826a;
            fVar.f6794i = charSequence;
            fVar.f6795j = onClickListener;
            return this;
        }

        public a r(CharSequence[] charSequenceArr, int i2, DialogInterface.OnClickListener onClickListener) {
            b.f fVar = this.f6826a;
            fVar.f6804s = charSequenceArr;
            fVar.f6806u = onClickListener;
            fVar.f6779F = i2;
            fVar.f6778E = true;
            return this;
        }

        public a s(int i2) {
            b.f fVar = this.f6826a;
            fVar.f6791f = fVar.f6786a.getText(i2);
            return this;
        }

        public a t(CharSequence charSequence) {
            this.f6826a.f6791f = charSequence;
            return this;
        }

        public a u(View view) {
            b.f fVar = this.f6826a;
            fVar.f6808w = view;
            fVar.f6807v = 0;
            fVar.f6775B = false;
            return this;
        }

        public c v() {
            c cVarA = a();
            cVarA.show();
            return cVarA;
        }

        public a(Context context, int i2) {
            this.f6826a = new b.f(new ContextThemeWrapper(context, c.f(context, i2)));
            this.f6827b = i2;
        }
    }

    public c(Context context, int i2) {
        super(context, f(context, i2));
        this.f6825b = new b(getContext(), this, getWindow());
    }

    public static int f(Context context, int i2) {
        if (i2 >= 16777216) {
            return i2;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(AbstractC0460a.f7717k, typedValue, true);
        return typedValue.resourceId;
    }

    public Button d(int i2) {
        return this.f6825b.b(i2);
    }

    @Override // o.n, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f6825b.d();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (this.f6825b.f(i2, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (this.f6825b.g(i2, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i2, keyEvent);
    }

    @Override // o.n, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.f6825b.p(charSequence);
    }
}

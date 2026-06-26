package g;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import g.x;

/* JADX INFO: loaded from: classes.dex */
public class y extends x {

    public static class a extends x.a {
        public a(x.a aVar, Resources resources) {
            super(aVar, resources);
        }

        @Override // g.x.a, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new y(this, resources);
        }
    }

    public y(Drawable drawable) {
        super(drawable);
    }

    @Override // g.x
    public x.a d() {
        return new a(this.f5272d, null);
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        this.f5274f.jumpToCurrentState();
    }

    public y(x.a aVar, Resources resources) {
        super(aVar, resources);
    }
}

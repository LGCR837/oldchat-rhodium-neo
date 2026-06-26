package g;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import g.x;

/* JADX INFO: loaded from: classes.dex */
public class z extends y {

    public static class a extends x.a {
        public a(x.a aVar, Resources resources) {
            super(aVar, resources);
        }

        @Override // g.x.a, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new z(this, resources);
        }
    }

    public z(Drawable drawable) {
        super(drawable);
    }

    @Override // g.y, g.x
    public x.a d() {
        return new a(this.f5272d, null);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.f5274f.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z2) {
        this.f5274f.setAutoMirrored(z2);
    }

    public z(x.a aVar, Resources resources) {
        super(aVar, resources);
    }
}

package v;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.view.LayoutInflater;

/* JADX INFO: renamed from: v.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0475d extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f7924a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Resources.Theme f7925b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LayoutInflater f7926c;

    public C0475d(Context context, int i2) {
        super(context);
        this.f7924a = i2;
    }

    public int a() {
        return this.f7924a;
    }

    public final void b() {
        boolean z2 = this.f7925b == null;
        if (z2) {
            this.f7925b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f7925b.setTo(theme);
            }
        }
        c(this.f7925b, this.f7924a, z2);
    }

    public void c(Resources.Theme theme, int i2, boolean z2) {
        theme.applyStyle(i2, true);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.f7926c == null) {
            this.f7926c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f7926c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f7925b;
        if (theme != null) {
            return theme;
        }
        if (this.f7924a == 0) {
            this.f7924a = p.i.f7835b;
        }
        b();
        return this.f7925b;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i2) {
        if (this.f7924a != i2) {
            this.f7924a = i2;
            b();
        }
    }

    public C0475d(Context context, Resources.Theme theme) {
        super(context);
        this.f7925b = theme;
    }
}

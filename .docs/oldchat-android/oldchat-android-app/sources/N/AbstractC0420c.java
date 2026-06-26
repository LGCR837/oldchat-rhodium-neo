package n;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.CompoundButton;

/* JADX INFO: renamed from: n.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0420c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final InterfaceC0064c f6687a;

    /* JADX INFO: renamed from: n.c$a */
    public static class a extends d {
        @Override // n.AbstractC0420c.b, n.AbstractC0420c.InterfaceC0064c
        public Drawable c(CompoundButton compoundButton) {
            return AbstractC0422e.a(compoundButton);
        }
    }

    /* JADX INFO: renamed from: n.c$b */
    public static class b implements InterfaceC0064c {
        @Override // n.AbstractC0420c.InterfaceC0064c
        public void a(CompoundButton compoundButton, ColorStateList colorStateList) {
            AbstractC0423f.b(compoundButton, colorStateList);
        }

        @Override // n.AbstractC0420c.InterfaceC0064c
        public void b(CompoundButton compoundButton, PorterDuff.Mode mode) {
            AbstractC0423f.c(compoundButton, mode);
        }

        @Override // n.AbstractC0420c.InterfaceC0064c
        public Drawable c(CompoundButton compoundButton) {
            return AbstractC0423f.a(compoundButton);
        }
    }

    /* JADX INFO: renamed from: n.c$c, reason: collision with other inner class name */
    public interface InterfaceC0064c {
        void a(CompoundButton compoundButton, ColorStateList colorStateList);

        void b(CompoundButton compoundButton, PorterDuff.Mode mode);

        Drawable c(CompoundButton compoundButton);
    }

    /* JADX INFO: renamed from: n.c$d */
    public static class d extends b {
        @Override // n.AbstractC0420c.b, n.AbstractC0420c.InterfaceC0064c
        public void a(CompoundButton compoundButton, ColorStateList colorStateList) {
            AbstractC0426i.a(compoundButton, colorStateList);
        }

        @Override // n.AbstractC0420c.b, n.AbstractC0420c.InterfaceC0064c
        public void b(CompoundButton compoundButton, PorterDuff.Mode mode) {
            AbstractC0426i.b(compoundButton, mode);
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 23) {
            f6687a = new a();
        } else if (i2 >= 21) {
            f6687a = new d();
        } else {
            f6687a = new b();
        }
    }

    public static Drawable a(CompoundButton compoundButton) {
        return f6687a.c(compoundButton);
    }

    public static void b(CompoundButton compoundButton, ColorStateList colorStateList) {
        f6687a.a(compoundButton, colorStateList);
    }

    public static void c(CompoundButton compoundButton, PorterDuff.Mode mode) {
        f6687a.b(compoundButton, mode);
    }
}

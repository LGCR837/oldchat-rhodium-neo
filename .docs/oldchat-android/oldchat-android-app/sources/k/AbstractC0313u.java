package k;

import android.os.Build;
import android.view.KeyEvent;

/* JADX INFO: renamed from: k.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0313u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c f5745a;

    /* JADX INFO: renamed from: k.u$a */
    public static class a implements c {
        public static int d(int i2, int i3, int i4, int i5, int i6) {
            int i7;
            boolean z2 = (i3 & i4) != 0;
            int i8 = i5 | i6;
            boolean z3 = (i3 & i8) != 0;
            if (z2) {
                if (z3) {
                    throw new IllegalArgumentException("bad arguments");
                }
                i7 = i8 ^ (-1);
            } else {
                if (!z3) {
                    return i2;
                }
                i7 = i4 ^ (-1);
            }
            return i2 & i7;
        }

        @Override // k.AbstractC0313u.c
        public boolean a(int i2) {
            return (e(i2) & 247) == 0;
        }

        @Override // k.AbstractC0313u.c
        public boolean b(KeyEvent keyEvent) {
            return false;
        }

        @Override // k.AbstractC0313u.c
        public boolean c(int i2, int i3) {
            return d(d(e(i2) & 247, i3, 1, 64, 128), i3, 2, 16, 32) == i3;
        }

        public int e(int i2) {
            if ((i2 & 192) != 0) {
                i2 |= 1;
            }
            if ((i2 & 48) != 0) {
                i2 |= 2;
            }
            return i2 & 247;
        }
    }

    /* JADX INFO: renamed from: k.u$b */
    public static class b extends a {
        @Override // k.AbstractC0313u.a, k.AbstractC0313u.c
        public boolean a(int i2) {
            return AbstractC0328z.c(i2);
        }

        @Override // k.AbstractC0313u.a, k.AbstractC0313u.c
        public boolean b(KeyEvent keyEvent) {
            return AbstractC0328z.a(keyEvent);
        }

        @Override // k.AbstractC0313u.a, k.AbstractC0313u.c
        public boolean c(int i2, int i3) {
            return AbstractC0328z.b(i2, i3);
        }

        @Override // k.AbstractC0313u.a
        public int e(int i2) {
            return AbstractC0328z.d(i2);
        }
    }

    /* JADX INFO: renamed from: k.u$c */
    public interface c {
        boolean a(int i2);

        boolean b(KeyEvent keyEvent);

        boolean c(int i2, int i3);
    }

    static {
        if (Build.VERSION.SDK_INT >= 11) {
            f5745a = new b();
        } else {
            f5745a = new a();
        }
    }

    public static boolean a(KeyEvent keyEvent, int i2) {
        return f5745a.c(keyEvent.getMetaState(), i2);
    }

    public static boolean b(KeyEvent keyEvent) {
        return f5745a.a(keyEvent.getMetaState());
    }

    public static boolean c(KeyEvent keyEvent) {
        return f5745a.b(keyEvent);
    }
}

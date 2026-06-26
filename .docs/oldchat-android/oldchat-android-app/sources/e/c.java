package E;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import z.h;

/* JADX INFO: loaded from: classes.dex */
public final class c extends b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Class f194d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f195b = d();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Field f196c = c();

    public static Field c() {
        try {
            return AccessibleObject.class.getDeclaredField("override");
        } catch (NoSuchFieldException unused) {
            return null;
        }
    }

    public static Object d() {
        try {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            f194d = cls;
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            return declaredField.get(null);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // E.b
    public void b(AccessibleObject accessibleObject) {
        if (e(accessibleObject)) {
            return;
        }
        try {
            accessibleObject.setAccessible(true);
        } catch (SecurityException e2) {
            throw new h("Gson couldn't modify fields for " + accessibleObject + "\nand sun.misc.Unsafe not found.\nEither write a custom type adapter, or make fields accessible, or include sun.misc.Unsafe.", e2);
        }
    }

    public boolean e(AccessibleObject accessibleObject) {
        if (this.f195b != null && this.f196c != null) {
            try {
                Long l2 = (Long) f194d.getMethod("objectFieldOffset", Field.class).invoke(this.f195b, this.f196c);
                l2.longValue();
                f194d.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE).invoke(this.f195b, accessibleObject, l2, Boolean.TRUE);
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }
}

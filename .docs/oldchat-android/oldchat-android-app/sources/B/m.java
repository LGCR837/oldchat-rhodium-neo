package B;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* JADX INFO: loaded from: classes.dex */
public abstract class m {

    public static class a extends m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Method f71a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Object f72b;

        public a(Method method, Object obj) {
            this.f71a = method;
            this.f72b = obj;
        }

        @Override // B.m
        public Object c(Class cls) {
            m.a(cls);
            return this.f71a.invoke(this.f72b, cls);
        }
    }

    public static class b extends m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Method f73a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f74b;

        public b(Method method, int i2) {
            this.f73a = method;
            this.f74b = i2;
        }

        @Override // B.m
        public Object c(Class cls) {
            m.a(cls);
            return this.f73a.invoke(null, cls, Integer.valueOf(this.f74b));
        }
    }

    public static class c extends m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Method f75a;

        public c(Method method) {
            this.f75a = method;
        }

        @Override // B.m
        public Object c(Class cls) {
            m.a(cls);
            return this.f75a.invoke(null, cls, Object.class);
        }
    }

    public static class d extends m {
        @Override // B.m
        public Object c(Class cls) {
            throw new UnsupportedOperationException("Cannot allocate " + cls);
        }
    }

    public static void a(Class cls) {
        int modifiers = cls.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            throw new UnsupportedOperationException("Interface can't be instantiated! Interface name: " + cls.getName());
        }
        if (Modifier.isAbstract(modifiers)) {
            throw new UnsupportedOperationException("Abstract class can't be instantiated! Class name: " + cls.getName());
        }
    }

    public static m b() {
        try {
            try {
                try {
                    Class<?> cls = Class.forName("sun.misc.Unsafe");
                    Field declaredField = cls.getDeclaredField("theUnsafe");
                    declaredField.setAccessible(true);
                    return new a(cls.getMethod("allocateInstance", Class.class), declaredField.get(null));
                } catch (Exception unused) {
                    return new d();
                }
            } catch (Exception unused2) {
                Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                declaredMethod.setAccessible(true);
                int iIntValue = ((Integer) declaredMethod.invoke(null, Object.class)).intValue();
                Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                declaredMethod2.setAccessible(true);
                return new b(declaredMethod2, iIntValue);
            }
        } catch (Exception unused3) {
            Method declaredMethod3 = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
            declaredMethod3.setAccessible(true);
            return new c(declaredMethod3);
        }
    }

    public abstract Object c(Class cls);
}

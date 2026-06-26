package B;

import c.AbstractC0177z;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map f7a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final E.b f8b = E.b.a();

    public class a implements B.i {
        public a() {
        }

        @Override // B.i
        public Object a() {
            return new ConcurrentHashMap();
        }
    }

    public class b implements B.i {
        public b() {
        }

        @Override // B.i
        public Object a() {
            return new TreeMap();
        }
    }

    /* JADX INFO: renamed from: B.c$c, reason: collision with other inner class name */
    public class C0001c implements B.i {
        public C0001c() {
        }

        @Override // B.i
        public Object a() {
            return new LinkedHashMap();
        }
    }

    public class d implements B.i {
        public d() {
        }

        @Override // B.i
        public Object a() {
            return new B.h();
        }
    }

    public class e implements B.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final m f13a = m.b();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Class f14b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Type f15c;

        public e(Class cls, Type type) {
            this.f14b = cls;
            this.f15c = type;
        }

        @Override // B.i
        public Object a() {
            try {
                return this.f13a.c(this.f14b);
            } catch (Exception e2) {
                throw new RuntimeException("Unable to invoke no-args constructor for " + this.f15c + ". Registering an InstanceCreator with Gson for this type may fix this problem.", e2);
            }
        }
    }

    public class f implements B.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Constructor f17a;

        public f(Constructor constructor) {
            this.f17a = constructor;
        }

        @Override // B.i
        public Object a() {
            try {
                return this.f17a.newInstance(null);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (InstantiationException e3) {
                throw new RuntimeException("Failed to invoke " + this.f17a + " with no args", e3);
            } catch (InvocationTargetException e4) {
                throw new RuntimeException("Failed to invoke " + this.f17a + " with no args", e4.getTargetException());
            }
        }
    }

    public class g implements B.i {
        public g() {
        }

        @Override // B.i
        public Object a() {
            return new TreeSet();
        }
    }

    public class h implements B.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Type f20a;

        public h(Type type) {
            this.f20a = type;
        }

        @Override // B.i
        public Object a() {
            Type type = this.f20a;
            if (!(type instanceof ParameterizedType)) {
                throw new z.h("Invalid EnumSet type: " + this.f20a.toString());
            }
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type2 instanceof Class) {
                return EnumSet.noneOf((Class) type2);
            }
            throw new z.h("Invalid EnumSet type: " + this.f20a.toString());
        }
    }

    public class i implements B.i {
        public i() {
        }

        @Override // B.i
        public Object a() {
            return new LinkedHashSet();
        }
    }

    public class j implements B.i {
        public j() {
        }

        @Override // B.i
        public Object a() {
            return new ArrayDeque();
        }
    }

    public class k implements B.i {
        public k() {
        }

        @Override // B.i
        public Object a() {
            return new ArrayList();
        }
    }

    public class l implements B.i {
        public l() {
        }

        @Override // B.i
        public Object a() {
            return new ConcurrentSkipListMap();
        }
    }

    public c(Map map) {
        this.f7a = map;
    }

    public B.i a(F.a aVar) {
        Type typeE = aVar.e();
        Class clsD = aVar.d();
        AbstractC0177z.a(this.f7a.get(typeE));
        AbstractC0177z.a(this.f7a.get(clsD));
        B.i iVarB = b(clsD);
        if (iVarB != null) {
            return iVarB;
        }
        B.i iVarC = c(typeE, clsD);
        return iVarC != null ? iVarC : d(typeE, clsD);
    }

    public final B.i b(Class cls) {
        try {
            Constructor declaredConstructor = cls.getDeclaredConstructor(null);
            if (!declaredConstructor.isAccessible()) {
                this.f8b.b(declaredConstructor);
            }
            return new f(declaredConstructor);
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public final B.i c(Type type, Class cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            return SortedSet.class.isAssignableFrom(cls) ? new g() : EnumSet.class.isAssignableFrom(cls) ? new h(type) : Set.class.isAssignableFrom(cls) ? new i() : Queue.class.isAssignableFrom(cls) ? new j() : new k();
        }
        if (Map.class.isAssignableFrom(cls)) {
            return ConcurrentNavigableMap.class.isAssignableFrom(cls) ? new l() : ConcurrentMap.class.isAssignableFrom(cls) ? new a() : SortedMap.class.isAssignableFrom(cls) ? new b() : (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(F.a.b(((ParameterizedType) type).getActualTypeArguments()[0]).d())) ? new d() : new C0001c();
        }
        return null;
    }

    public final B.i d(Type type, Class cls) {
        return new e(cls, type);
    }

    public String toString() {
        return this.f7a.toString();
    }
}

package z;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class l extends g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Class[] f8330b = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f8331a;

    public l(Boolean bool) {
        q(bool);
    }

    public static boolean m(l lVar) {
        Object obj = lVar.f8331a;
        if (!(obj instanceof Number)) {
            return false;
        }
        Number number = (Number) obj;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }

    public static boolean o(Object obj) {
        if (obj instanceof String) {
            return true;
        }
        Class<?> cls = obj.getClass();
        for (Class cls2 : f8330b) {
            if (cls2.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || l.class != obj.getClass()) {
            return false;
        }
        l lVar = (l) obj;
        if (this.f8331a == null) {
            return lVar.f8331a == null;
        }
        if (m(this) && m(lVar)) {
            return j().longValue() == lVar.j().longValue();
        }
        Object obj2 = this.f8331a;
        if (!(obj2 instanceof Number) || !(lVar.f8331a instanceof Number)) {
            return obj2.equals(lVar.f8331a);
        }
        double dDoubleValue = j().doubleValue();
        double dDoubleValue2 = lVar.j().doubleValue();
        if (dDoubleValue != dDoubleValue2) {
            return Double.isNaN(dDoubleValue) && Double.isNaN(dDoubleValue2);
        }
        return true;
    }

    public boolean h() {
        return l() ? i().booleanValue() : Boolean.parseBoolean(k());
    }

    public int hashCode() {
        long jDoubleToLongBits;
        if (this.f8331a == null) {
            return 31;
        }
        if (m(this)) {
            jDoubleToLongBits = j().longValue();
        } else {
            Object obj = this.f8331a;
            if (!(obj instanceof Number)) {
                return obj.hashCode();
            }
            jDoubleToLongBits = Double.doubleToLongBits(j().doubleValue());
        }
        return (int) ((jDoubleToLongBits >>> 32) ^ jDoubleToLongBits);
    }

    public Boolean i() {
        return (Boolean) this.f8331a;
    }

    public Number j() {
        Object obj = this.f8331a;
        return obj instanceof String ? new B.g((String) obj) : (Number) obj;
    }

    public String k() {
        return n() ? j().toString() : l() ? i().toString() : (String) this.f8331a;
    }

    public boolean l() {
        return this.f8331a instanceof Boolean;
    }

    public boolean n() {
        return this.f8331a instanceof Number;
    }

    public boolean p() {
        return this.f8331a instanceof String;
    }

    public void q(Object obj) {
        if (obj instanceof Character) {
            this.f8331a = String.valueOf(((Character) obj).charValue());
        } else {
            B.a.a((obj instanceof Number) || o(obj));
            this.f8331a = obj;
        }
    }

    public l(Number number) {
        q(number);
    }

    public l(String str) {
        q(str);
    }
}

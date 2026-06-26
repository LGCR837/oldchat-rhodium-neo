package j;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Object[] f5484d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f5485e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static Object[] f5486f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static int f5487g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f5488a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object[] f5489b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5490c;

    public j() {
        this.f5488a = b.f5449a;
        this.f5489b = b.f5451c;
        this.f5490c = 0;
    }

    public static void c(int[] iArr, Object[] objArr, int i2) {
        if (iArr.length == 8) {
            synchronized (C0252a.class) {
                try {
                    if (f5487g < 10) {
                        objArr[0] = f5486f;
                        objArr[1] = iArr;
                        for (int i3 = (i2 << 1) - 1; i3 >= 2; i3--) {
                            objArr[i3] = null;
                        }
                        f5486f = objArr;
                        f5487g++;
                    }
                } finally {
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (C0252a.class) {
                try {
                    if (f5485e < 10) {
                        objArr[0] = f5484d;
                        objArr[1] = iArr;
                        for (int i4 = (i2 << 1) - 1; i4 >= 2; i4--) {
                            objArr[i4] = null;
                        }
                        f5484d = objArr;
                        f5485e++;
                    }
                } finally {
                }
            }
        }
    }

    public final void a(int i2) {
        if (i2 == 8) {
            synchronized (C0252a.class) {
                try {
                    Object[] objArr = f5486f;
                    if (objArr != null) {
                        this.f5489b = objArr;
                        f5486f = (Object[]) objArr[0];
                        this.f5488a = (int[]) objArr[1];
                        objArr[1] = null;
                        objArr[0] = null;
                        f5487g--;
                        return;
                    }
                } finally {
                }
            }
        } else if (i2 == 4) {
            synchronized (C0252a.class) {
                try {
                    Object[] objArr2 = f5484d;
                    if (objArr2 != null) {
                        this.f5489b = objArr2;
                        f5484d = (Object[]) objArr2[0];
                        this.f5488a = (int[]) objArr2[1];
                        objArr2[1] = null;
                        objArr2[0] = null;
                        f5485e--;
                        return;
                    }
                } finally {
                }
            }
        }
        this.f5488a = new int[i2];
        this.f5489b = new Object[i2 << 1];
    }

    public void b(int i2) {
        int[] iArr = this.f5488a;
        if (iArr.length < i2) {
            Object[] objArr = this.f5489b;
            a(i2);
            int i3 = this.f5490c;
            if (i3 > 0) {
                System.arraycopy(iArr, 0, this.f5488a, 0, i3);
                System.arraycopy(objArr, 0, this.f5489b, 0, this.f5490c << 1);
            }
            c(iArr, objArr, this.f5490c);
        }
    }

    public void clear() {
        int i2 = this.f5490c;
        if (i2 != 0) {
            c(this.f5488a, this.f5489b, i2);
            this.f5488a = b.f5449a;
            this.f5489b = b.f5451c;
            this.f5490c = 0;
        }
    }

    public boolean containsKey(Object obj) {
        return e(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return g(obj) >= 0;
    }

    public int d(Object obj, int i2) {
        int i3 = this.f5490c;
        if (i3 == 0) {
            return -1;
        }
        int iA = b.a(this.f5488a, i3, i2);
        if (iA < 0 || obj.equals(this.f5489b[iA << 1])) {
            return iA;
        }
        int i4 = iA + 1;
        while (i4 < i3 && this.f5488a[i4] == i2) {
            if (obj.equals(this.f5489b[i4 << 1])) {
                return i4;
            }
            i4++;
        }
        for (int i5 = iA - 1; i5 >= 0 && this.f5488a[i5] == i2; i5--) {
            if (obj.equals(this.f5489b[i5 << 1])) {
                return i5;
            }
        }
        return i4 ^ (-1);
    }

    public int e(Object obj) {
        return obj == null ? f() : d(obj, obj.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof j) {
            j jVar = (j) obj;
            if (size() != jVar.size()) {
                return false;
            }
            for (int i2 = 0; i2 < this.f5490c; i2++) {
                try {
                    Object objH = h(i2);
                    Object objK = k(i2);
                    Object obj2 = jVar.get(objH);
                    if (objK == null) {
                        if (obj2 != null || !jVar.containsKey(objH)) {
                            return false;
                        }
                    } else if (!objK.equals(obj2)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            }
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (size() != map.size()) {
                return false;
            }
            for (int i3 = 0; i3 < this.f5490c; i3++) {
                try {
                    Object objH2 = h(i3);
                    Object objK2 = k(i3);
                    Object obj3 = map.get(objH2);
                    if (objK2 == null) {
                        if (obj3 != null || !map.containsKey(objH2)) {
                            return false;
                        }
                    } else if (!objK2.equals(obj3)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return true;
        }
        return false;
    }

    public int f() {
        int i2 = this.f5490c;
        if (i2 == 0) {
            return -1;
        }
        int iA = b.a(this.f5488a, i2, 0);
        if (iA < 0 || this.f5489b[iA << 1] == null) {
            return iA;
        }
        int i3 = iA + 1;
        while (i3 < i2 && this.f5488a[i3] == 0) {
            if (this.f5489b[i3 << 1] == null) {
                return i3;
            }
            i3++;
        }
        for (int i4 = iA - 1; i4 >= 0 && this.f5488a[i4] == 0; i4--) {
            if (this.f5489b[i4 << 1] == null) {
                return i4;
            }
        }
        return i3 ^ (-1);
    }

    public int g(Object obj) {
        int i2 = this.f5490c * 2;
        Object[] objArr = this.f5489b;
        if (obj == null) {
            for (int i3 = 1; i3 < i2; i3 += 2) {
                if (objArr[i3] == null) {
                    return i3 >> 1;
                }
            }
            return -1;
        }
        for (int i4 = 1; i4 < i2; i4 += 2) {
            if (obj.equals(objArr[i4])) {
                return i4 >> 1;
            }
        }
        return -1;
    }

    public Object get(Object obj) {
        int iE = e(obj);
        if (iE >= 0) {
            return this.f5489b[(iE << 1) + 1];
        }
        return null;
    }

    public Object h(int i2) {
        return this.f5489b[i2 << 1];
    }

    public int hashCode() {
        int[] iArr = this.f5488a;
        Object[] objArr = this.f5489b;
        int i2 = this.f5490c;
        int i3 = 1;
        int i4 = 0;
        int iHashCode = 0;
        while (i4 < i2) {
            Object obj = objArr[i3];
            iHashCode += (obj == null ? 0 : obj.hashCode()) ^ iArr[i4];
            i4++;
            i3 += 2;
        }
        return iHashCode;
    }

    public Object i(int i2) {
        Object[] objArr = this.f5489b;
        int i3 = i2 << 1;
        Object obj = objArr[i3 + 1];
        int i4 = this.f5490c;
        if (i4 <= 1) {
            c(this.f5488a, objArr, i4);
            this.f5488a = b.f5449a;
            this.f5489b = b.f5451c;
            this.f5490c = 0;
        } else {
            int[] iArr = this.f5488a;
            if (iArr.length <= 8 || i4 >= iArr.length / 3) {
                int i5 = i4 - 1;
                this.f5490c = i5;
                if (i2 < i5) {
                    int i6 = i2 + 1;
                    System.arraycopy(iArr, i6, iArr, i2, i5 - i2);
                    Object[] objArr2 = this.f5489b;
                    System.arraycopy(objArr2, i6 << 1, objArr2, i3, (this.f5490c - i2) << 1);
                }
                Object[] objArr3 = this.f5489b;
                int i7 = this.f5490c;
                objArr3[i7 << 1] = null;
                objArr3[(i7 << 1) + 1] = null;
            } else {
                a(i4 > 8 ? i4 + (i4 >> 1) : 8);
                this.f5490c--;
                if (i2 > 0) {
                    System.arraycopy(iArr, 0, this.f5488a, 0, i2);
                    System.arraycopy(objArr, 0, this.f5489b, 0, i3);
                }
                int i8 = this.f5490c;
                if (i2 < i8) {
                    int i9 = i2 + 1;
                    System.arraycopy(iArr, i9, this.f5488a, i2, i8 - i2);
                    System.arraycopy(objArr, i9 << 1, this.f5489b, i3, (this.f5490c - i2) << 1);
                }
            }
        }
        return obj;
    }

    public boolean isEmpty() {
        return this.f5490c <= 0;
    }

    public Object j(int i2, Object obj) {
        int i3 = (i2 << 1) + 1;
        Object[] objArr = this.f5489b;
        Object obj2 = objArr[i3];
        objArr[i3] = obj;
        return obj2;
    }

    public Object k(int i2) {
        return this.f5489b[(i2 << 1) + 1];
    }

    public Object put(Object obj, Object obj2) {
        int i2;
        int iD;
        if (obj == null) {
            iD = f();
            i2 = 0;
        } else {
            int iHashCode = obj.hashCode();
            i2 = iHashCode;
            iD = d(obj, iHashCode);
        }
        if (iD >= 0) {
            int i3 = (iD << 1) + 1;
            Object[] objArr = this.f5489b;
            Object obj3 = objArr[i3];
            objArr[i3] = obj2;
            return obj3;
        }
        int i4 = iD ^ (-1);
        int i5 = this.f5490c;
        int[] iArr = this.f5488a;
        if (i5 >= iArr.length) {
            int i6 = 8;
            if (i5 >= 8) {
                i6 = (i5 >> 1) + i5;
            } else if (i5 < 4) {
                i6 = 4;
            }
            Object[] objArr2 = this.f5489b;
            a(i6);
            int[] iArr2 = this.f5488a;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr2, 0, this.f5489b, 0, objArr2.length);
            }
            c(iArr, objArr2, this.f5490c);
        }
        int i7 = this.f5490c;
        if (i4 < i7) {
            int[] iArr3 = this.f5488a;
            int i8 = i4 + 1;
            System.arraycopy(iArr3, i4, iArr3, i8, i7 - i4);
            Object[] objArr3 = this.f5489b;
            System.arraycopy(objArr3, i4 << 1, objArr3, i8 << 1, (this.f5490c - i4) << 1);
        }
        this.f5488a[i4] = i2;
        Object[] objArr4 = this.f5489b;
        int i9 = i4 << 1;
        objArr4[i9] = obj;
        objArr4[i9 + 1] = obj2;
        this.f5490c++;
        return null;
    }

    public Object remove(Object obj) {
        int iE = e(obj);
        if (iE >= 0) {
            return i(iE);
        }
        return null;
    }

    public int size() {
        return this.f5490c;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f5490c * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.f5490c; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            Object objH = h(i2);
            if (objH != this) {
                sb.append(objH);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object objK = k(i2);
            if (objK != this) {
                sb.append(objK);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public j(int i2) {
        if (i2 == 0) {
            this.f5488a = b.f5449a;
            this.f5489b = b.f5451c;
        } else {
            a(i2);
        }
        this.f5490c = 0;
    }
}

package j;

/* JADX INFO: loaded from: classes.dex */
public class e implements Cloneable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Object f5454e = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f5455a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long[] f5456b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object[] f5457c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5458d;

    public e() {
        this(10);
    }

    public void a() {
        int i2 = this.f5458d;
        Object[] objArr = this.f5457c;
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = null;
        }
        this.f5458d = 0;
        this.f5455a = false;
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public e clone() {
        e eVar;
        e eVar2 = null;
        try {
            eVar = (e) super.clone();
        } catch (CloneNotSupportedException unused) {
        }
        try {
            eVar.f5456b = (long[]) this.f5456b.clone();
            eVar.f5457c = (Object[]) this.f5457c.clone();
            return eVar;
        } catch (CloneNotSupportedException unused2) {
            eVar2 = eVar;
            return eVar2;
        }
    }

    public void c(long j2) {
        int iB = b.b(this.f5456b, this.f5458d, j2);
        if (iB >= 0) {
            Object[] objArr = this.f5457c;
            Object obj = objArr[iB];
            Object obj2 = f5454e;
            if (obj != obj2) {
                objArr[iB] = obj2;
                this.f5455a = true;
            }
        }
    }

    public final void d() {
        int i2 = this.f5458d;
        long[] jArr = this.f5456b;
        Object[] objArr = this.f5457c;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            Object obj = objArr[i4];
            if (obj != f5454e) {
                if (i4 != i3) {
                    jArr[i3] = jArr[i4];
                    objArr[i3] = obj;
                    objArr[i4] = null;
                }
                i3++;
            }
        }
        this.f5455a = false;
        this.f5458d = i3;
    }

    public Object e(long j2) {
        return f(j2, null);
    }

    public Object f(long j2, Object obj) {
        Object obj2;
        int iB = b.b(this.f5456b, this.f5458d, j2);
        return (iB < 0 || (obj2 = this.f5457c[iB]) == f5454e) ? obj : obj2;
    }

    public long g(int i2) {
        if (this.f5455a) {
            d();
        }
        return this.f5456b[i2];
    }

    public void h(long j2, Object obj) {
        int iB = b.b(this.f5456b, this.f5458d, j2);
        if (iB >= 0) {
            this.f5457c[iB] = obj;
            return;
        }
        int iB2 = iB ^ (-1);
        int i2 = this.f5458d;
        if (iB2 < i2) {
            Object[] objArr = this.f5457c;
            if (objArr[iB2] == f5454e) {
                this.f5456b[iB2] = j2;
                objArr[iB2] = obj;
                return;
            }
        }
        if (this.f5455a && i2 >= this.f5456b.length) {
            d();
            iB2 = b.b(this.f5456b, this.f5458d, j2) ^ (-1);
        }
        int i3 = this.f5458d;
        if (i3 >= this.f5456b.length) {
            int iF = b.f(i3 + 1);
            long[] jArr = new long[iF];
            Object[] objArr2 = new Object[iF];
            long[] jArr2 = this.f5456b;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.f5457c;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f5456b = jArr;
            this.f5457c = objArr2;
        }
        int i4 = this.f5458d;
        if (i4 - iB2 != 0) {
            long[] jArr3 = this.f5456b;
            int i5 = iB2 + 1;
            System.arraycopy(jArr3, iB2, jArr3, i5, i4 - iB2);
            Object[] objArr4 = this.f5457c;
            System.arraycopy(objArr4, iB2, objArr4, i5, this.f5458d - iB2);
        }
        this.f5456b[iB2] = j2;
        this.f5457c[iB2] = obj;
        this.f5458d++;
    }

    public void i(int i2) {
        Object[] objArr = this.f5457c;
        Object obj = objArr[i2];
        Object obj2 = f5454e;
        if (obj != obj2) {
            objArr[i2] = obj2;
            this.f5455a = true;
        }
    }

    public int j() {
        if (this.f5455a) {
            d();
        }
        return this.f5458d;
    }

    public Object k(int i2) {
        if (this.f5455a) {
            d();
        }
        return this.f5457c[i2];
    }

    public String toString() {
        if (j() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f5458d * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.f5458d; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            sb.append(g(i2));
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

    public e(int i2) {
        this.f5455a = false;
        if (i2 == 0) {
            this.f5456b = b.f5450b;
            this.f5457c = b.f5451c;
        } else {
            int iF = b.f(i2);
            this.f5456b = new long[iF];
            this.f5457c = new Object[iF];
        }
        this.f5458d = 0;
    }
}

package j;

/* JADX INFO: loaded from: classes.dex */
public class k implements Cloneable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Object f5491e = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f5492a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f5493b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object[] f5494c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5495d;

    public k() {
        this(10);
    }

    public void a() {
        int i2 = this.f5495d;
        Object[] objArr = this.f5494c;
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = null;
        }
        this.f5495d = 0;
        this.f5492a = false;
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public k clone() {
        k kVar;
        k kVar2 = null;
        try {
            kVar = (k) super.clone();
        } catch (CloneNotSupportedException unused) {
        }
        try {
            kVar.f5493b = (int[]) this.f5493b.clone();
            kVar.f5494c = (Object[]) this.f5494c.clone();
            return kVar;
        } catch (CloneNotSupportedException unused2) {
            kVar2 = kVar;
            return kVar2;
        }
    }

    public void c(int i2) {
        int iA = b.a(this.f5493b, this.f5495d, i2);
        if (iA >= 0) {
            Object[] objArr = this.f5494c;
            Object obj = objArr[iA];
            Object obj2 = f5491e;
            if (obj != obj2) {
                objArr[iA] = obj2;
                this.f5492a = true;
            }
        }
    }

    public final void d() {
        int i2 = this.f5495d;
        int[] iArr = this.f5493b;
        Object[] objArr = this.f5494c;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            Object obj = objArr[i4];
            if (obj != f5491e) {
                if (i4 != i3) {
                    iArr[i3] = iArr[i4];
                    objArr[i3] = obj;
                    objArr[i4] = null;
                }
                i3++;
            }
        }
        this.f5492a = false;
        this.f5495d = i3;
    }

    public Object e(int i2) {
        return f(i2, null);
    }

    public Object f(int i2, Object obj) {
        Object obj2;
        int iA = b.a(this.f5493b, this.f5495d, i2);
        return (iA < 0 || (obj2 = this.f5494c[iA]) == f5491e) ? obj : obj2;
    }

    public int g(int i2) {
        if (this.f5492a) {
            d();
        }
        return b.a(this.f5493b, this.f5495d, i2);
    }

    public int h(int i2) {
        if (this.f5492a) {
            d();
        }
        return this.f5493b[i2];
    }

    public void i(int i2, Object obj) {
        int iA = b.a(this.f5493b, this.f5495d, i2);
        if (iA >= 0) {
            this.f5494c[iA] = obj;
            return;
        }
        int iA2 = iA ^ (-1);
        int i3 = this.f5495d;
        if (iA2 < i3) {
            Object[] objArr = this.f5494c;
            if (objArr[iA2] == f5491e) {
                this.f5493b[iA2] = i2;
                objArr[iA2] = obj;
                return;
            }
        }
        if (this.f5492a && i3 >= this.f5493b.length) {
            d();
            iA2 = b.a(this.f5493b, this.f5495d, i2) ^ (-1);
        }
        int i4 = this.f5495d;
        if (i4 >= this.f5493b.length) {
            int iE = b.e(i4 + 1);
            int[] iArr = new int[iE];
            Object[] objArr2 = new Object[iE];
            int[] iArr2 = this.f5493b;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.f5494c;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f5493b = iArr;
            this.f5494c = objArr2;
        }
        int i5 = this.f5495d;
        if (i5 - iA2 != 0) {
            int[] iArr3 = this.f5493b;
            int i6 = iA2 + 1;
            System.arraycopy(iArr3, iA2, iArr3, i6, i5 - iA2);
            Object[] objArr4 = this.f5494c;
            System.arraycopy(objArr4, iA2, objArr4, i6, this.f5495d - iA2);
        }
        this.f5493b[iA2] = i2;
        this.f5494c[iA2] = obj;
        this.f5495d++;
    }

    public void j(int i2) {
        c(i2);
    }

    public int k() {
        if (this.f5492a) {
            d();
        }
        return this.f5495d;
    }

    public Object l(int i2) {
        if (this.f5492a) {
            d();
        }
        return this.f5494c[i2];
    }

    public String toString() {
        if (k() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f5495d * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.f5495d; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            sb.append(h(i2));
            sb.append('=');
            Object objL = l(i2);
            if (objL != this) {
                sb.append(objL);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public k(int i2) {
        this.f5492a = false;
        if (i2 == 0) {
            this.f5493b = b.f5449a;
            this.f5494c = b.f5451c;
        } else {
            int iE = b.e(i2);
            this.f5493b = new int[iE];
            this.f5494c = new Object[iE];
        }
        this.f5495d = 0;
    }
}

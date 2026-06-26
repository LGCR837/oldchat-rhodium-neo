package j;

/* JADX INFO: loaded from: classes.dex */
public class i implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f5482a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5483b;

    public i(int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f5482a = new Object[i2];
    }

    @Override // j.h
    public boolean a(Object obj) {
        if (c(obj)) {
            throw new IllegalStateException("Already in the pool!");
        }
        int i2 = this.f5483b;
        Object[] objArr = this.f5482a;
        if (i2 >= objArr.length) {
            return false;
        }
        objArr[i2] = obj;
        this.f5483b = i2 + 1;
        return true;
    }

    @Override // j.h
    public Object b() {
        int i2 = this.f5483b;
        if (i2 <= 0) {
            return null;
        }
        int i3 = i2 - 1;
        Object[] objArr = this.f5482a;
        Object obj = objArr[i3];
        objArr[i3] = null;
        this.f5483b = i2 - 1;
        return obj;
    }

    public final boolean c(Object obj) {
        for (int i2 = 0; i2 < this.f5483b; i2++) {
            if (this.f5482a[i2] == obj) {
                return true;
            }
        }
        return false;
    }
}

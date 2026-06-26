package x;

import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: classes.dex */
public class Z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f8104a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f8105b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f8106c = PKIFailureInfo.systemUnavail;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f8107d = PKIFailureInfo.systemUnavail;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8108e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f8109f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f8110g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f8111h = false;

    public int a() {
        return this.f8110g ? this.f8104a : this.f8105b;
    }

    public int b() {
        return this.f8104a;
    }

    public int c() {
        return this.f8105b;
    }

    public int d() {
        return this.f8110g ? this.f8105b : this.f8104a;
    }

    public void e(int i2, int i3) {
        this.f8111h = false;
        if (i2 != Integer.MIN_VALUE) {
            this.f8108e = i2;
            this.f8104a = i2;
        }
        if (i3 != Integer.MIN_VALUE) {
            this.f8109f = i3;
            this.f8105b = i3;
        }
    }

    public void f(boolean z2) {
        if (z2 == this.f8110g) {
            return;
        }
        this.f8110g = z2;
        if (!this.f8111h) {
            this.f8104a = this.f8108e;
            this.f8105b = this.f8109f;
            return;
        }
        if (z2) {
            int i2 = this.f8107d;
            if (i2 == Integer.MIN_VALUE) {
                i2 = this.f8108e;
            }
            this.f8104a = i2;
            int i3 = this.f8106c;
            if (i3 == Integer.MIN_VALUE) {
                i3 = this.f8109f;
            }
            this.f8105b = i3;
            return;
        }
        int i4 = this.f8106c;
        if (i4 == Integer.MIN_VALUE) {
            i4 = this.f8108e;
        }
        this.f8104a = i4;
        int i5 = this.f8107d;
        if (i5 == Integer.MIN_VALUE) {
            i5 = this.f8109f;
        }
        this.f8105b = i5;
    }

    public void g(int i2, int i3) {
        this.f8106c = i2;
        this.f8107d = i3;
        this.f8111h = true;
        if (this.f8110g) {
            if (i3 != Integer.MIN_VALUE) {
                this.f8104a = i3;
            }
            if (i2 != Integer.MIN_VALUE) {
                this.f8105b = i2;
                return;
            }
            return;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f8104a = i2;
        }
        if (i3 != Integer.MIN_VALUE) {
            this.f8105b = i3;
        }
    }
}

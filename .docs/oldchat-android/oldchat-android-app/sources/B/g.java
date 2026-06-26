package B;

import java.math.BigDecimal;

/* JADX INFO: loaded from: classes.dex */
public final class g extends Number {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f41a;

    public g(String str) {
        this.f41a = str;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return Double.parseDouble(this.f41a);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        String str = this.f41a;
        String str2 = ((g) obj).f41a;
        return str == str2 || str.equals(str2);
    }

    @Override // java.lang.Number
    public float floatValue() {
        return Float.parseFloat(this.f41a);
    }

    public int hashCode() {
        return this.f41a.hashCode();
    }

    @Override // java.lang.Number
    public int intValue() {
        try {
            try {
                return Integer.parseInt(this.f41a);
            } catch (NumberFormatException unused) {
                return new BigDecimal(this.f41a).intValue();
            }
        } catch (NumberFormatException unused2) {
            return (int) Long.parseLong(this.f41a);
        }
    }

    @Override // java.lang.Number
    public long longValue() {
        try {
            return Long.parseLong(this.f41a);
        } catch (NumberFormatException unused) {
            return new BigDecimal(this.f41a).longValue();
        }
    }

    public String toString() {
        return this.f41a;
    }
}

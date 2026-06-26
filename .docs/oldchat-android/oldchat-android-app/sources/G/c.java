package G;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: classes.dex */
public class c implements Closeable, Flushable {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final String[] f227j = new String[128];

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String[] f228k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Writer f229a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f230b = new int[32];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f231c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f232d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f233e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f234f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f235g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f236h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f237i;

    static {
        for (int i2 = 0; i2 <= 31; i2++) {
            f227j[i2] = String.format("\\u%04x", Integer.valueOf(i2));
        }
        String[] strArr = f227j;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        f228k = strArr2;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public c(Writer writer) {
        p(6);
        this.f233e = ":";
        this.f237i = true;
        if (writer == null) {
            throw new NullPointerException("out == null");
        }
        this.f229a = writer;
    }

    public c A(boolean z2) throws IOException {
        B();
        b();
        this.f229a.write(z2 ? "true" : "false");
        return this;
    }

    public final void B() throws IOException {
        if (this.f236h != null) {
            a();
            v(this.f236h);
            this.f236h = null;
        }
    }

    public final void a() throws IOException {
        int iO = o();
        if (iO == 5) {
            this.f229a.write(44);
        } else if (iO != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        l();
        q(4);
    }

    public final void b() throws IOException {
        int iO = o();
        if (iO == 1) {
            q(2);
            l();
            return;
        }
        if (iO == 2) {
            this.f229a.append(',');
            l();
        } else {
            if (iO == 4) {
                this.f229a.append((CharSequence) this.f233e);
                q(5);
                return;
            }
            if (iO != 6) {
                if (iO != 7) {
                    throw new IllegalStateException("Nesting problem.");
                }
                if (!this.f234f) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            q(7);
        }
    }

    public c c() throws IOException {
        B();
        return n(1, "[");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f229a.close();
        int i2 = this.f231c;
        if (i2 > 1 || (i2 == 1 && this.f230b[i2 - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.f231c = 0;
    }

    public c d() throws IOException {
        B();
        return n(3, "{");
    }

    public final c e(int i2, int i3, String str) throws IOException {
        int iO = o();
        if (iO != i3 && iO != i2) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.f236h != null) {
            throw new IllegalStateException("Dangling name: " + this.f236h);
        }
        this.f231c--;
        if (iO == i3) {
            l();
        }
        this.f229a.write(str);
        return this;
    }

    public c f() {
        return e(1, 2, "]");
    }

    public void flush() throws IOException {
        if (this.f231c == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.f229a.flush();
    }

    public c g() {
        return e(3, 5, "}");
    }

    public final boolean h() {
        return this.f237i;
    }

    public final boolean i() {
        return this.f235g;
    }

    public boolean j() {
        return this.f234f;
    }

    public c k(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        if (this.f236h != null) {
            throw new IllegalStateException();
        }
        if (this.f231c == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.f236h = str;
        return this;
    }

    public final void l() throws IOException {
        if (this.f232d == null) {
            return;
        }
        this.f229a.write("\n");
        int i2 = this.f231c;
        for (int i3 = 1; i3 < i2; i3++) {
            this.f229a.write(this.f232d);
        }
    }

    public c m() throws IOException {
        if (this.f236h != null) {
            if (!this.f237i) {
                this.f236h = null;
                return this;
            }
            B();
        }
        b();
        this.f229a.write("null");
        return this;
    }

    public final c n(int i2, String str) throws IOException {
        b();
        p(i2);
        this.f229a.write(str);
        return this;
    }

    public final int o() {
        int i2 = this.f231c;
        if (i2 != 0) {
            return this.f230b[i2 - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public final void p(int i2) {
        int i3 = this.f231c;
        int[] iArr = this.f230b;
        if (i3 == iArr.length) {
            int[] iArr2 = new int[i3 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i3);
            this.f230b = iArr2;
        }
        int[] iArr3 = this.f230b;
        int i4 = this.f231c;
        this.f231c = i4 + 1;
        iArr3[i4] = i2;
    }

    public final void q(int i2) {
        this.f230b[this.f231c - 1] = i2;
    }

    public final void r(boolean z2) {
        this.f235g = z2;
    }

    public final void s(String str) {
        if (str.length() == 0) {
            this.f232d = null;
            this.f233e = ":";
        } else {
            this.f232d = str;
            this.f233e = ": ";
        }
    }

    public final void t(boolean z2) {
        this.f234f = z2;
    }

    public final void u(boolean z2) {
        this.f237i = z2;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void v(java.lang.String r9) throws java.io.IOException {
        /*
            r8 = this;
            boolean r0 = r8.f235g
            if (r0 == 0) goto L7
            java.lang.String[] r0 = G.c.f228k
            goto L9
        L7:
            java.lang.String[] r0 = G.c.f227j
        L9:
            java.io.Writer r1 = r8.f229a
            java.lang.String r2 = "\""
            r1.write(r2)
            int r1 = r9.length()
            r3 = 0
            r4 = 0
        L16:
            if (r3 >= r1) goto L45
            char r5 = r9.charAt(r3)
            r6 = 128(0x80, float:1.8E-43)
            if (r5 >= r6) goto L25
            r5 = r0[r5]
            if (r5 != 0) goto L32
            goto L42
        L25:
            r6 = 8232(0x2028, float:1.1535E-41)
            if (r5 != r6) goto L2c
            java.lang.String r5 = "\\u2028"
            goto L32
        L2c:
            r6 = 8233(0x2029, float:1.1537E-41)
            if (r5 != r6) goto L42
            java.lang.String r5 = "\\u2029"
        L32:
            if (r4 >= r3) goto L3b
            java.io.Writer r6 = r8.f229a
            int r7 = r3 - r4
            r6.write(r9, r4, r7)
        L3b:
            java.io.Writer r4 = r8.f229a
            r4.write(r5)
            int r4 = r3 + 1
        L42:
            int r3 = r3 + 1
            goto L16
        L45:
            if (r4 >= r1) goto L4d
            java.io.Writer r0 = r8.f229a
            int r1 = r1 - r4
            r0.write(r9, r4, r1)
        L4d:
            java.io.Writer r9 = r8.f229a
            r9.write(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: G.c.v(java.lang.String):void");
    }

    public c w(long j2) throws IOException {
        B();
        b();
        this.f229a.write(Long.toString(j2));
        return this;
    }

    public c x(Boolean bool) throws IOException {
        if (bool == null) {
            return m();
        }
        B();
        b();
        this.f229a.write(bool.booleanValue() ? "true" : "false");
        return this;
    }

    public c y(Number number) throws IOException {
        if (number == null) {
            return m();
        }
        B();
        String string = number.toString();
        if (this.f234f || !(string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
            b();
            this.f229a.append((CharSequence) string);
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
    }

    public c z(String str) throws IOException {
        if (str == null) {
            return m();
        }
        B();
        b();
        v(str);
        return this;
    }
}

package G;

import B.f;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

/* JADX INFO: loaded from: classes.dex */
public class a implements Closeable {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final char[] f200p = ")]}'\n".toCharArray();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Reader f201a;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f209i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f210j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f211k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int[] f212l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String[] f214n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int[] f215o;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f202b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final char[] f203c = new char[1024];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f204d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f205e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f206f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f207g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f208h = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f213m = 1;

    /* JADX INFO: renamed from: G.a$a, reason: collision with other inner class name */
    public static class C0005a extends f {
        @Override // B.f
        public void a(a aVar) throws IOException {
            int iE = aVar.f208h;
            if (iE == 0) {
                iE = aVar.e();
            }
            if (iE == 13) {
                aVar.f208h = 9;
                return;
            }
            if (iE == 12) {
                aVar.f208h = 8;
                return;
            }
            if (iE == 14) {
                aVar.f208h = 10;
                return;
            }
            throw new IllegalStateException("Expected a name but was " + aVar.x() + aVar.m());
        }
    }

    static {
        f.f40a = new C0005a();
    }

    public a(Reader reader) {
        int[] iArr = new int[32];
        this.f212l = iArr;
        iArr[0] = 6;
        this.f214n = new String[32];
        this.f215o = new int[32];
        if (reader == null) {
            throw new NullPointerException("in == null");
        }
        this.f201a = reader;
    }

    public final void A(int i2) {
        int i3 = this.f213m;
        int[] iArr = this.f212l;
        if (i3 == iArr.length) {
            int[] iArr2 = new int[i3 * 2];
            int[] iArr3 = new int[i3 * 2];
            String[] strArr = new String[i3 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i3);
            System.arraycopy(this.f215o, 0, iArr3, 0, this.f213m);
            System.arraycopy(this.f214n, 0, strArr, 0, this.f213m);
            this.f212l = iArr2;
            this.f215o = iArr3;
            this.f214n = strArr;
        }
        int[] iArr4 = this.f212l;
        int i4 = this.f213m;
        this.f213m = i4 + 1;
        iArr4[i4] = i2;
    }

    public final char B() throws IOException {
        int i2;
        if (this.f204d == this.f205e && !h(1)) {
            throw I("Unterminated escape sequence");
        }
        char[] cArr = this.f203c;
        int i3 = this.f204d;
        int i4 = i3 + 1;
        this.f204d = i4;
        char c2 = cArr[i3];
        if (c2 == '\n') {
            this.f206f++;
            this.f207g = i4;
        } else if (c2 != '\"' && c2 != '\'' && c2 != '/' && c2 != '\\') {
            if (c2 == 'b') {
                return '\b';
            }
            if (c2 == 'f') {
                return '\f';
            }
            if (c2 == 'n') {
                return '\n';
            }
            if (c2 == 'r') {
                return '\r';
            }
            if (c2 == 't') {
                return '\t';
            }
            if (c2 != 'u') {
                throw I("Invalid escape sequence");
            }
            if (i3 + 5 > this.f205e && !h(4)) {
                throw I("Unterminated escape sequence");
            }
            int i5 = this.f204d;
            int i6 = i5 + 4;
            char c3 = 0;
            while (i5 < i6) {
                char c4 = this.f203c[i5];
                char c5 = (char) (c3 << 4);
                if (c4 >= '0' && c4 <= '9') {
                    i2 = c4 - '0';
                } else if (c4 >= 'a' && c4 <= 'f') {
                    i2 = c4 - 'W';
                } else {
                    if (c4 < 'A' || c4 > 'F') {
                        throw new NumberFormatException("\\u" + new String(this.f203c, this.f204d, 4));
                    }
                    i2 = c4 - '7';
                }
                c3 = (char) (c5 + i2);
                i5++;
            }
            this.f204d += 4;
            return c3;
        }
        return c2;
    }

    public final void C(boolean z2) {
        this.f202b = z2;
    }

    public final void D(char c2) throws IOException {
        char[] cArr = this.f203c;
        do {
            int i2 = this.f204d;
            int i3 = this.f205e;
            while (i2 < i3) {
                int i4 = i2 + 1;
                char c3 = cArr[i2];
                if (c3 == c2) {
                    this.f204d = i4;
                    return;
                }
                if (c3 == '\\') {
                    this.f204d = i4;
                    B();
                    i2 = this.f204d;
                    i3 = this.f205e;
                } else {
                    if (c3 == '\n') {
                        this.f206f++;
                        this.f207g = i4;
                    }
                    i2 = i4;
                }
            }
            this.f204d = i2;
        } while (h(1));
        throw I("Unterminated string");
    }

    public final boolean E(String str) {
        int length = str.length();
        while (true) {
            if (this.f204d + length > this.f205e && !h(length)) {
                return false;
            }
            char[] cArr = this.f203c;
            int i2 = this.f204d;
            if (cArr[i2] != '\n') {
                for (int i3 = 0; i3 < length; i3++) {
                    if (this.f203c[this.f204d + i3] != str.charAt(i3)) {
                        break;
                    }
                }
                return true;
            }
            this.f206f++;
            this.f207g = i2 + 1;
            this.f204d++;
        }
    }

    public final void F() {
        char c2;
        do {
            if (this.f204d >= this.f205e && !h(1)) {
                return;
            }
            char[] cArr = this.f203c;
            int i2 = this.f204d;
            int i3 = i2 + 1;
            this.f204d = i3;
            c2 = cArr[i2];
            if (c2 == '\n') {
                this.f206f++;
                this.f207g = i3;
                return;
            }
        } while (c2 != '\r');
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0048, code lost:
    
        c();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void G() throws java.io.IOException {
        /*
            r4 = this;
        L0:
            r0 = 0
        L1:
            int r1 = r4.f204d
            int r2 = r1 + r0
            int r3 = r4.f205e
            if (r2 >= r3) goto L51
            char[] r2 = r4.f203c
            int r1 = r1 + r0
            char r1 = r2[r1]
            r2 = 9
            if (r1 == r2) goto L4b
            r2 = 10
            if (r1 == r2) goto L4b
            r2 = 12
            if (r1 == r2) goto L4b
            r2 = 13
            if (r1 == r2) goto L4b
            r2 = 32
            if (r1 == r2) goto L4b
            r2 = 35
            if (r1 == r2) goto L48
            r2 = 44
            if (r1 == r2) goto L4b
            r2 = 47
            if (r1 == r2) goto L48
            r2 = 61
            if (r1 == r2) goto L48
            r2 = 123(0x7b, float:1.72E-43)
            if (r1 == r2) goto L4b
            r2 = 125(0x7d, float:1.75E-43)
            if (r1 == r2) goto L4b
            r2 = 58
            if (r1 == r2) goto L4b
            r2 = 59
            if (r1 == r2) goto L48
            switch(r1) {
                case 91: goto L4b;
                case 92: goto L48;
                case 93: goto L4b;
                default: goto L45;
            }
        L45:
            int r0 = r0 + 1
            goto L1
        L48:
            r4.c()
        L4b:
            int r1 = r4.f204d
            int r1 = r1 + r0
            r4.f204d = r1
            return
        L51:
            int r1 = r1 + r0
            r4.f204d = r1
            r0 = 1
            boolean r0 = r4.h(r0)
            if (r0 != 0) goto L0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: G.a.G():void");
    }

    public void H() throws IOException {
        int i2 = 0;
        do {
            int iE = this.f208h;
            if (iE == 0) {
                iE = e();
            }
            if (iE == 3) {
                A(1);
            } else if (iE == 1) {
                A(3);
            } else if (iE == 4 || iE == 2) {
                this.f213m--;
                i2--;
                this.f208h = 0;
            } else {
                if (iE == 14 || iE == 10) {
                    G();
                } else if (iE == 8 || iE == 12) {
                    D('\'');
                } else if (iE == 9 || iE == 13) {
                    D('\"');
                } else if (iE == 16) {
                    this.f204d += this.f210j;
                }
                this.f208h = 0;
            }
            i2++;
            this.f208h = 0;
        } while (i2 != 0);
        int[] iArr = this.f215o;
        int i3 = this.f213m;
        int i4 = i3 - 1;
        iArr[i4] = iArr[i4] + 1;
        this.f214n[i3 - 1] = "null";
    }

    public final IOException I(String str) throws d {
        throw new d(str + m());
    }

    public void a() throws IOException {
        int iE = this.f208h;
        if (iE == 0) {
            iE = e();
        }
        if (iE == 3) {
            A(1);
            this.f215o[this.f213m - 1] = 0;
            this.f208h = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_ARRAY but was " + x() + m());
        }
    }

    public void b() throws IOException {
        int iE = this.f208h;
        if (iE == 0) {
            iE = e();
        }
        if (iE == 1) {
            A(3);
            this.f208h = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_OBJECT but was " + x() + m());
        }
    }

    public final void c() throws IOException {
        if (!this.f202b) {
            throw I("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f208h = 0;
        this.f212l[0] = 8;
        this.f213m = 1;
        this.f201a.close();
    }

    public final void d() throws IOException {
        s(true);
        int i2 = this.f204d - 1;
        this.f204d = i2;
        char[] cArr = f200p;
        if (i2 + cArr.length > this.f205e && !h(cArr.length)) {
            return;
        }
        int i3 = 0;
        while (true) {
            char[] cArr2 = f200p;
            if (i3 >= cArr2.length) {
                this.f204d += cArr2.length;
                return;
            } else if (this.f203c[this.f204d + i3] != cArr2[i3]) {
                return;
            } else {
                i3++;
            }
        }
    }

    public int e() throws IOException {
        int iS;
        int[] iArr = this.f212l;
        int i2 = this.f213m;
        int i3 = iArr[i2 - 1];
        if (i3 == 1) {
            iArr[i2 - 1] = 2;
        } else if (i3 == 2) {
            int iS2 = s(true);
            if (iS2 != 44) {
                if (iS2 != 59) {
                    if (iS2 != 93) {
                        throw I("Unterminated array");
                    }
                    this.f208h = 4;
                    return 4;
                }
                c();
            }
        } else {
            if (i3 == 3 || i3 == 5) {
                iArr[i2 - 1] = 4;
                if (i3 == 5 && (iS = s(true)) != 44) {
                    if (iS != 59) {
                        if (iS != 125) {
                            throw I("Unterminated object");
                        }
                        this.f208h = 2;
                        return 2;
                    }
                    c();
                }
                int iS3 = s(true);
                if (iS3 == 34) {
                    this.f208h = 13;
                    return 13;
                }
                if (iS3 == 39) {
                    c();
                    this.f208h = 12;
                    return 12;
                }
                if (iS3 == 125) {
                    if (i3 == 5) {
                        throw I("Expected name");
                    }
                    this.f208h = 2;
                    return 2;
                }
                c();
                this.f204d--;
                if (!l((char) iS3)) {
                    throw I("Expected name");
                }
                this.f208h = 14;
                return 14;
            }
            if (i3 == 4) {
                iArr[i2 - 1] = 5;
                int iS4 = s(true);
                if (iS4 != 58) {
                    if (iS4 != 61) {
                        throw I("Expected ':'");
                    }
                    c();
                    if (this.f204d < this.f205e || h(1)) {
                        char[] cArr = this.f203c;
                        int i4 = this.f204d;
                        if (cArr[i4] == '>') {
                            this.f204d = i4 + 1;
                        }
                    }
                }
            } else if (i3 == 6) {
                if (this.f202b) {
                    d();
                }
                this.f212l[this.f213m - 1] = 7;
            } else if (i3 == 7) {
                if (s(false) == -1) {
                    this.f208h = 17;
                    return 17;
                }
                c();
                this.f204d--;
            } else if (i3 == 8) {
                throw new IllegalStateException("JsonReader is closed");
            }
        }
        int iS5 = s(true);
        if (iS5 == 34) {
            this.f208h = 9;
            return 9;
        }
        if (iS5 == 39) {
            c();
            this.f208h = 8;
            return 8;
        }
        if (iS5 != 44 && iS5 != 59) {
            if (iS5 == 91) {
                this.f208h = 3;
                return 3;
            }
            if (iS5 != 93) {
                if (iS5 == 123) {
                    this.f208h = 1;
                    return 1;
                }
                this.f204d--;
                int iY = y();
                if (iY != 0) {
                    return iY;
                }
                int iZ = z();
                if (iZ != 0) {
                    return iZ;
                }
                if (!l(this.f203c[this.f204d])) {
                    throw I("Expected value");
                }
                c();
                this.f208h = 10;
                return 10;
            }
            if (i3 == 1) {
                this.f208h = 4;
                return 4;
            }
        }
        if (i3 != 1 && i3 != 2) {
            throw I("Unexpected value");
        }
        c();
        this.f204d--;
        this.f208h = 7;
        return 7;
    }

    public void f() throws IOException {
        int iE = this.f208h;
        if (iE == 0) {
            iE = e();
        }
        if (iE != 4) {
            throw new IllegalStateException("Expected END_ARRAY but was " + x() + m());
        }
        int i2 = this.f213m;
        this.f213m = i2 - 1;
        int[] iArr = this.f215o;
        int i3 = i2 - 2;
        iArr[i3] = iArr[i3] + 1;
        this.f208h = 0;
    }

    public void g() throws IOException {
        int iE = this.f208h;
        if (iE == 0) {
            iE = e();
        }
        if (iE != 2) {
            throw new IllegalStateException("Expected END_OBJECT but was " + x() + m());
        }
        int i2 = this.f213m;
        int i3 = i2 - 1;
        this.f213m = i3;
        this.f214n[i3] = null;
        int[] iArr = this.f215o;
        int i4 = i2 - 2;
        iArr[i4] = iArr[i4] + 1;
        this.f208h = 0;
    }

    public final boolean h(int i2) throws IOException {
        int i3;
        int i4;
        char[] cArr = this.f203c;
        int i5 = this.f207g;
        int i6 = this.f204d;
        this.f207g = i5 - i6;
        int i7 = this.f205e;
        if (i7 != i6) {
            int i8 = i7 - i6;
            this.f205e = i8;
            System.arraycopy(cArr, i6, cArr, 0, i8);
        } else {
            this.f205e = 0;
        }
        this.f204d = 0;
        do {
            Reader reader = this.f201a;
            int i9 = this.f205e;
            int i10 = reader.read(cArr, i9, cArr.length - i9);
            if (i10 == -1) {
                return false;
            }
            i3 = this.f205e + i10;
            this.f205e = i3;
            if (this.f206f == 0 && (i4 = this.f207g) == 0 && i3 > 0 && cArr[0] == 65279) {
                this.f204d++;
                this.f207g = i4 + 1;
                i2++;
            }
        } while (i3 < i2);
        return true;
    }

    public String i() {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i2 = this.f213m;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = this.f212l[i3];
            if (i4 == 1 || i4 == 2) {
                sb.append('[');
                sb.append(this.f215o[i3]);
                sb.append(']');
            } else if (i4 == 3 || i4 == 4 || i4 == 5) {
                sb.append('.');
                String str = this.f214n[i3];
                if (str != null) {
                    sb.append(str);
                }
            }
        }
        return sb.toString();
    }

    public boolean j() throws IOException {
        int iE = this.f208h;
        if (iE == 0) {
            iE = e();
        }
        return (iE == 2 || iE == 4) ? false : true;
    }

    public final boolean k() {
        return this.f202b;
    }

    public final boolean l(char c2) throws IOException {
        if (c2 == '\t' || c2 == '\n' || c2 == '\f' || c2 == '\r' || c2 == ' ') {
            return false;
        }
        if (c2 != '#') {
            if (c2 == ',') {
                return false;
            }
            if (c2 != '/' && c2 != '=') {
                if (c2 == '{' || c2 == '}' || c2 == ':') {
                    return false;
                }
                if (c2 != ';') {
                    switch (c2) {
                        case '[':
                        case ']':
                            return false;
                        case '\\':
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        c();
        return false;
    }

    public String m() {
        return " at line " + (this.f206f + 1) + " column " + ((this.f204d - this.f207g) + 1) + " path " + i();
    }

    public boolean n() throws IOException {
        int iE = this.f208h;
        if (iE == 0) {
            iE = e();
        }
        if (iE == 5) {
            this.f208h = 0;
            int[] iArr = this.f215o;
            int i2 = this.f213m - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        }
        if (iE == 6) {
            this.f208h = 0;
            int[] iArr2 = this.f215o;
            int i3 = this.f213m - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return false;
        }
        throw new IllegalStateException("Expected a boolean but was " + x() + m());
    }

    public double o() throws IOException {
        int iE = this.f208h;
        if (iE == 0) {
            iE = e();
        }
        if (iE == 15) {
            this.f208h = 0;
            int[] iArr = this.f215o;
            int i2 = this.f213m - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.f209i;
        }
        if (iE == 16) {
            this.f211k = new String(this.f203c, this.f204d, this.f210j);
            this.f204d += this.f210j;
        } else if (iE == 8 || iE == 9) {
            this.f211k = u(iE == 8 ? '\'' : '\"');
        } else if (iE == 10) {
            this.f211k = w();
        } else if (iE != 11) {
            throw new IllegalStateException("Expected a double but was " + x() + m());
        }
        this.f208h = 11;
        double d2 = Double.parseDouble(this.f211k);
        if (!this.f202b && (Double.isNaN(d2) || Double.isInfinite(d2))) {
            throw new d("JSON forbids NaN and infinities: " + d2 + m());
        }
        this.f211k = null;
        this.f208h = 0;
        int[] iArr2 = this.f215o;
        int i3 = this.f213m - 1;
        iArr2[i3] = iArr2[i3] + 1;
        return d2;
    }

    public int p() throws IOException {
        int iE = this.f208h;
        if (iE == 0) {
            iE = e();
        }
        if (iE == 15) {
            long j2 = this.f209i;
            int i2 = (int) j2;
            if (j2 == i2) {
                this.f208h = 0;
                int[] iArr = this.f215o;
                int i3 = this.f213m - 1;
                iArr[i3] = iArr[i3] + 1;
                return i2;
            }
            throw new NumberFormatException("Expected an int but was " + this.f209i + m());
        }
        if (iE == 16) {
            this.f211k = new String(this.f203c, this.f204d, this.f210j);
            this.f204d += this.f210j;
        } else {
            if (iE != 8 && iE != 9 && iE != 10) {
                throw new IllegalStateException("Expected an int but was " + x() + m());
            }
            if (iE == 10) {
                this.f211k = w();
            } else {
                this.f211k = u(iE == 8 ? '\'' : '\"');
            }
            try {
                int i4 = Integer.parseInt(this.f211k);
                this.f208h = 0;
                int[] iArr2 = this.f215o;
                int i5 = this.f213m - 1;
                iArr2[i5] = iArr2[i5] + 1;
                return i4;
            } catch (NumberFormatException unused) {
            }
        }
        this.f208h = 11;
        double d2 = Double.parseDouble(this.f211k);
        int i6 = (int) d2;
        if (i6 != d2) {
            throw new NumberFormatException("Expected an int but was " + this.f211k + m());
        }
        this.f211k = null;
        this.f208h = 0;
        int[] iArr3 = this.f215o;
        int i7 = this.f213m - 1;
        iArr3[i7] = iArr3[i7] + 1;
        return i6;
    }

    public long q() throws IOException {
        int iE = this.f208h;
        if (iE == 0) {
            iE = e();
        }
        if (iE == 15) {
            this.f208h = 0;
            int[] iArr = this.f215o;
            int i2 = this.f213m - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.f209i;
        }
        if (iE == 16) {
            this.f211k = new String(this.f203c, this.f204d, this.f210j);
            this.f204d += this.f210j;
        } else {
            if (iE != 8 && iE != 9 && iE != 10) {
                throw new IllegalStateException("Expected a long but was " + x() + m());
            }
            if (iE == 10) {
                this.f211k = w();
            } else {
                this.f211k = u(iE == 8 ? '\'' : '\"');
            }
            try {
                long j2 = Long.parseLong(this.f211k);
                this.f208h = 0;
                int[] iArr2 = this.f215o;
                int i3 = this.f213m - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return j2;
            } catch (NumberFormatException unused) {
            }
        }
        this.f208h = 11;
        double d2 = Double.parseDouble(this.f211k);
        long j3 = (long) d2;
        if (j3 != d2) {
            throw new NumberFormatException("Expected a long but was " + this.f211k + m());
        }
        this.f211k = null;
        this.f208h = 0;
        int[] iArr3 = this.f215o;
        int i4 = this.f213m - 1;
        iArr3[i4] = iArr3[i4] + 1;
        return j3;
    }

    public String r() throws IOException {
        String strU;
        int iE = this.f208h;
        if (iE == 0) {
            iE = e();
        }
        if (iE == 14) {
            strU = w();
        } else if (iE == 12) {
            strU = u('\'');
        } else {
            if (iE != 13) {
                throw new IllegalStateException("Expected a name but was " + x() + m());
            }
            strU = u('\"');
        }
        this.f208h = 0;
        this.f214n[this.f213m - 1] = strU;
        return strU;
    }

    public final int s(boolean z2) throws IOException {
        char[] cArr = this.f203c;
        int i2 = this.f204d;
        int i3 = this.f205e;
        while (true) {
            if (i2 == i3) {
                this.f204d = i2;
                if (!h(1)) {
                    if (!z2) {
                        return -1;
                    }
                    throw new EOFException("End of input" + m());
                }
                i2 = this.f204d;
                i3 = this.f205e;
            }
            int i4 = i2 + 1;
            char c2 = cArr[i2];
            if (c2 == '\n') {
                this.f206f++;
                this.f207g = i4;
            } else if (c2 != ' ' && c2 != '\r' && c2 != '\t') {
                if (c2 == '/') {
                    this.f204d = i4;
                    if (i4 == i3) {
                        this.f204d = i2;
                        boolean zH = h(2);
                        this.f204d++;
                        if (!zH) {
                            return c2;
                        }
                    }
                    c();
                    int i5 = this.f204d;
                    char c3 = cArr[i5];
                    if (c3 == '*') {
                        this.f204d = i5 + 1;
                        if (!E("*/")) {
                            throw I("Unterminated comment");
                        }
                        i2 = this.f204d + 2;
                        i3 = this.f205e;
                    } else {
                        if (c3 != '/') {
                            return c2;
                        }
                        this.f204d = i5 + 1;
                        F();
                        i2 = this.f204d;
                        i3 = this.f205e;
                    }
                } else {
                    if (c2 != '#') {
                        this.f204d = i4;
                        return c2;
                    }
                    this.f204d = i4;
                    c();
                    F();
                    i2 = this.f204d;
                    i3 = this.f205e;
                }
            }
            i2 = i4;
        }
    }

    public void t() throws IOException {
        int iE = this.f208h;
        if (iE == 0) {
            iE = e();
        }
        if (iE == 7) {
            this.f208h = 0;
            int[] iArr = this.f215o;
            int i2 = this.f213m - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + x() + m());
    }

    public String toString() {
        return getClass().getSimpleName() + m();
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x005a, code lost:
    
        if (r1 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005c, code lost:
    
        r1 = new java.lang.StringBuilder(java.lang.Math.max((r2 - r3) * 2, 16));
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x006a, code lost:
    
        r1.append(r0, r3, r2 - r3);
        r9.f204d = r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String u(char r10) throws java.io.IOException {
        /*
            r9 = this;
            char[] r0 = r9.f203c
            r1 = 0
        L3:
            int r2 = r9.f204d
            int r3 = r9.f205e
        L7:
            r4 = r3
            r3 = r2
        L9:
            r5 = 16
            r6 = 1
            if (r2 >= r4) goto L5a
            int r7 = r2 + 1
            char r2 = r0[r2]
            if (r2 != r10) goto L28
            r9.f204d = r7
            int r7 = r7 - r3
            int r7 = r7 - r6
            if (r1 != 0) goto L20
            java.lang.String r10 = new java.lang.String
            r10.<init>(r0, r3, r7)
            return r10
        L20:
            r1.append(r0, r3, r7)
            java.lang.String r10 = r1.toString()
            return r10
        L28:
            r8 = 92
            if (r2 != r8) goto L4d
            r9.f204d = r7
            int r7 = r7 - r3
            int r2 = r7 + (-1)
            if (r1 != 0) goto L3e
            int r7 = r7 * 2
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            int r4 = java.lang.Math.max(r7, r5)
            r1.<init>(r4)
        L3e:
            r1.append(r0, r3, r2)
            char r2 = r9.B()
            r1.append(r2)
            int r2 = r9.f204d
            int r3 = r9.f205e
            goto L7
        L4d:
            r5 = 10
            if (r2 != r5) goto L58
            int r2 = r9.f206f
            int r2 = r2 + r6
            r9.f206f = r2
            r9.f207g = r7
        L58:
            r2 = r7
            goto L9
        L5a:
            if (r1 != 0) goto L6a
            int r1 = r2 - r3
            int r1 = r1 * 2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r1 = java.lang.Math.max(r1, r5)
            r4.<init>(r1)
            r1 = r4
        L6a:
            int r4 = r2 - r3
            r1.append(r0, r3, r4)
            r9.f204d = r2
            boolean r2 = r9.h(r6)
            if (r2 == 0) goto L78
            goto L3
        L78:
            java.lang.String r10 = "Unterminated string"
            java.io.IOException r10 = r9.I(r10)
            goto L80
        L7f:
            throw r10
        L80:
            goto L7f
        */
        throw new UnsupportedOperationException("Method not decompiled: G.a.u(char):java.lang.String");
    }

    public String v() throws IOException {
        String str;
        int iE = this.f208h;
        if (iE == 0) {
            iE = e();
        }
        if (iE == 10) {
            str = w();
        } else if (iE == 8) {
            str = u('\'');
        } else if (iE == 9) {
            str = u('\"');
        } else if (iE == 11) {
            str = this.f211k;
            this.f211k = null;
        } else if (iE == 15) {
            str = Long.toString(this.f209i);
        } else {
            if (iE != 16) {
                throw new IllegalStateException("Expected a string but was " + x() + m());
            }
            str = new String(this.f203c, this.f204d, this.f210j);
            this.f204d += this.f210j;
        }
        this.f208h = 0;
        int[] iArr = this.f215o;
        int i2 = this.f213m - 1;
        iArr[i2] = iArr[i2] + 1;
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x004a, code lost:
    
        c();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:32:0x0044. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String w() throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
        L2:
            r2 = 0
        L3:
            int r3 = r6.f204d
            int r4 = r3 + r2
            int r5 = r6.f205e
            if (r4 >= r5) goto L4e
            char[] r4 = r6.f203c
            int r3 = r3 + r2
            char r3 = r4[r3]
            r4 = 9
            if (r3 == r4) goto L5c
            r4 = 10
            if (r3 == r4) goto L5c
            r4 = 12
            if (r3 == r4) goto L5c
            r4 = 13
            if (r3 == r4) goto L5c
            r4 = 32
            if (r3 == r4) goto L5c
            r4 = 35
            if (r3 == r4) goto L4a
            r4 = 44
            if (r3 == r4) goto L5c
            r4 = 47
            if (r3 == r4) goto L4a
            r4 = 61
            if (r3 == r4) goto L4a
            r4 = 123(0x7b, float:1.72E-43)
            if (r3 == r4) goto L5c
            r4 = 125(0x7d, float:1.75E-43)
            if (r3 == r4) goto L5c
            r4 = 58
            if (r3 == r4) goto L5c
            r4 = 59
            if (r3 == r4) goto L4a
            switch(r3) {
                case 91: goto L5c;
                case 92: goto L4a;
                case 93: goto L5c;
                default: goto L47;
            }
        L47:
            int r2 = r2 + 1
            goto L3
        L4a:
            r6.c()
            goto L5c
        L4e:
            char[] r3 = r6.f203c
            int r3 = r3.length
            if (r2 >= r3) goto L5e
            int r3 = r2 + 1
            boolean r3 = r6.h(r3)
            if (r3 == 0) goto L5c
            goto L3
        L5c:
            r1 = r2
            goto L7e
        L5e:
            if (r0 != 0) goto L6b
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r3 = 16
            int r3 = java.lang.Math.max(r2, r3)
            r0.<init>(r3)
        L6b:
            char[] r3 = r6.f203c
            int r4 = r6.f204d
            r0.append(r3, r4, r2)
            int r3 = r6.f204d
            int r3 = r3 + r2
            r6.f204d = r3
            r2 = 1
            boolean r2 = r6.h(r2)
            if (r2 != 0) goto L2
        L7e:
            if (r0 != 0) goto L8a
            java.lang.String r0 = new java.lang.String
            char[] r2 = r6.f203c
            int r3 = r6.f204d
            r0.<init>(r2, r3, r1)
            goto L95
        L8a:
            char[] r2 = r6.f203c
            int r3 = r6.f204d
            r0.append(r2, r3, r1)
            java.lang.String r0 = r0.toString()
        L95:
            int r2 = r6.f204d
            int r2 = r2 + r1
            r6.f204d = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: G.a.w():java.lang.String");
    }

    public b x() throws IOException {
        int iE = this.f208h;
        if (iE == 0) {
            iE = e();
        }
        switch (iE) {
            case 1:
                return b.BEGIN_OBJECT;
            case 2:
                return b.END_OBJECT;
            case 3:
                return b.BEGIN_ARRAY;
            case 4:
                return b.END_ARRAY;
            case 5:
            case 6:
                return b.BOOLEAN;
            case 7:
                return b.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return b.STRING;
            case 12:
            case 13:
            case 14:
                return b.NAME;
            case 15:
            case 16:
                return b.NUMBER;
            case 17:
                return b.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    public final int y() {
        String str;
        String str2;
        int i2;
        char c2 = this.f203c[this.f204d];
        if (c2 == 't' || c2 == 'T') {
            str = "true";
            str2 = "TRUE";
            i2 = 5;
        } else if (c2 == 'f' || c2 == 'F') {
            str = "false";
            str2 = "FALSE";
            i2 = 6;
        } else {
            if (c2 != 'n' && c2 != 'N') {
                return 0;
            }
            str = "null";
            str2 = "NULL";
            i2 = 7;
        }
        int length = str.length();
        for (int i3 = 1; i3 < length; i3++) {
            if (this.f204d + i3 >= this.f205e && !h(i3 + 1)) {
                return 0;
            }
            char c3 = this.f203c[this.f204d + i3];
            if (c3 != str.charAt(i3) && c3 != str2.charAt(i3)) {
                return 0;
            }
        }
        if ((this.f204d + length < this.f205e || h(length + 1)) && l(this.f203c[this.f204d + length])) {
            return 0;
        }
        this.f204d += length;
        this.f208h = i2;
        return i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x0095, code lost:
    
        if (l(r14) != false) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0097, code lost:
    
        if (r9 != 2) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0099, code lost:
    
        if (r10 == false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x009f, code lost:
    
        if (r11 != Long.MIN_VALUE) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00a1, code lost:
    
        if (r13 == false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00a7, code lost:
    
        if (r11 != 0) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00a9, code lost:
    
        if (r13 != false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00ab, code lost:
    
        if (r13 == false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00ae, code lost:
    
        r11 = -r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00af, code lost:
    
        r18.f209i = r11;
        r18.f204d += r8;
        r18.f208h = 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00ba, code lost:
    
        return 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00bb, code lost:
    
        if (r9 == 2) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00be, code lost:
    
        if (r9 == 4) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00c1, code lost:
    
        if (r9 != 7) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00c4, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00c6, code lost:
    
        r18.f210j = r8;
        r18.f208h = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00cc, code lost:
    
        return 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00cd, code lost:
    
        return 0;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x00f1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int z() {
        /*
            Method dump skipped, instruction units count: 253
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: G.a.z():int");
    }
}

package C;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class e extends G.c {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final Writer f85o = new a();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final z.l f86p = new z.l("closed");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final List f87l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f88m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public z.g f89n;

    public static class a extends Writer {
        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            throw new AssertionError();
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
            throw new AssertionError();
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i2, int i3) {
            throw new AssertionError();
        }
    }

    public e() {
        super(f85o);
        this.f87l = new ArrayList();
        this.f89n = z.i.f8328a;
    }

    @Override // G.c
    public G.c A(boolean z2) {
        E(new z.l(Boolean.valueOf(z2)));
        return this;
    }

    public z.g C() {
        if (this.f87l.isEmpty()) {
            return this.f89n;
        }
        throw new IllegalStateException("Expected one JSON element but was " + this.f87l);
    }

    public final z.g D() {
        return (z.g) this.f87l.get(r0.size() - 1);
    }

    public final void E(z.g gVar) {
        if (this.f88m != null) {
            if (!gVar.e() || h()) {
                ((z.j) D()).h(this.f88m, gVar);
            }
            this.f88m = null;
            return;
        }
        if (this.f87l.isEmpty()) {
            this.f89n = gVar;
            return;
        }
        z.g gVarD = D();
        if (!(gVarD instanceof z.f)) {
            throw new IllegalStateException();
        }
        ((z.f) gVarD).h(gVar);
    }

    @Override // G.c
    public G.c c() {
        z.f fVar = new z.f();
        E(fVar);
        this.f87l.add(fVar);
        return this;
    }

    @Override // G.c, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.f87l.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        this.f87l.add(f86p);
    }

    @Override // G.c
    public G.c d() {
        z.j jVar = new z.j();
        E(jVar);
        this.f87l.add(jVar);
        return this;
    }

    @Override // G.c
    public G.c f() {
        if (this.f87l.isEmpty() || this.f88m != null) {
            throw new IllegalStateException();
        }
        if (!(D() instanceof z.f)) {
            throw new IllegalStateException();
        }
        this.f87l.remove(r0.size() - 1);
        return this;
    }

    @Override // G.c
    public G.c g() {
        if (this.f87l.isEmpty() || this.f88m != null) {
            throw new IllegalStateException();
        }
        if (!(D() instanceof z.j)) {
            throw new IllegalStateException();
        }
        this.f87l.remove(r0.size() - 1);
        return this;
    }

    @Override // G.c
    public G.c k(String str) {
        if (this.f87l.isEmpty() || this.f88m != null) {
            throw new IllegalStateException();
        }
        if (!(D() instanceof z.j)) {
            throw new IllegalStateException();
        }
        this.f88m = str;
        return this;
    }

    @Override // G.c
    public G.c m() {
        E(z.i.f8328a);
        return this;
    }

    @Override // G.c
    public G.c w(long j2) {
        E(new z.l(Long.valueOf(j2)));
        return this;
    }

    @Override // G.c
    public G.c x(Boolean bool) {
        if (bool == null) {
            return m();
        }
        E(new z.l(bool));
        return this;
    }

    @Override // G.c
    public G.c y(Number number) {
        if (number == null) {
            return m();
        }
        if (!j()) {
            double dDoubleValue = number.doubleValue();
            if (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        E(new z.l(number));
        return this;
    }

    @Override // G.c
    public G.c z(String str) {
        if (str == null) {
            return m();
        }
        E(new z.l(str));
        return this;
    }

    @Override // G.c, java.io.Flushable
    public void flush() {
    }
}

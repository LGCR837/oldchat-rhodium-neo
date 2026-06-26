package z;

import java.io.IOException;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes.dex */
public abstract class g {
    public f a() {
        if (d()) {
            return (f) this;
        }
        throw new IllegalStateException("Not a JSON Array: " + this);
    }

    public j b() {
        if (f()) {
            return (j) this;
        }
        throw new IllegalStateException("Not a JSON Object: " + this);
    }

    public l c() {
        if (g()) {
            return (l) this;
        }
        throw new IllegalStateException("Not a JSON Primitive: " + this);
    }

    public boolean d() {
        return this instanceof f;
    }

    public boolean e() {
        return this instanceof i;
    }

    public boolean f() {
        return this instanceof j;
    }

    public boolean g() {
        return this instanceof l;
    }

    public String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            G.c cVar = new G.c(stringWriter);
            cVar.t(true);
            B.l.a(this, cVar);
            return stringWriter.toString();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }
}

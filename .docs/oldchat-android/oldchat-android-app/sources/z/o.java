package z;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class o {

    public class a extends o {
        public a() {
        }

        @Override // z.o
        public Object b(G.a aVar) throws IOException {
            if (aVar.x() != G.b.NULL) {
                return o.this.b(aVar);
            }
            aVar.t();
            return null;
        }

        @Override // z.o
        public void d(G.c cVar, Object obj) throws IOException {
            if (obj == null) {
                cVar.m();
            } else {
                o.this.d(cVar, obj);
            }
        }
    }

    public final o a() {
        return new a();
    }

    public abstract Object b(G.a aVar);

    public final g c(Object obj) {
        try {
            C.e eVar = new C.e();
            d(eVar, obj);
            return eVar.C();
        } catch (IOException e2) {
            throw new h(e2);
        }
    }

    public abstract void d(G.c cVar, Object obj);
}

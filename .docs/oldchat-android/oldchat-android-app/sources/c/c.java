package C;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import z.m;
import z.o;
import z.p;

/* JADX INFO: loaded from: classes.dex */
public final class c extends o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final p f82b = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f83a;

    public static class a implements p {
        @Override // z.p
        public o a(z.e eVar, F.a aVar) {
            if (aVar.d() == Date.class) {
                return new c();
            }
            return null;
        }
    }

    public c() {
        ArrayList arrayList = new ArrayList();
        this.f83a = arrayList;
        Locale locale = Locale.US;
        arrayList.add(DateFormat.getDateTimeInstance(2, 2, locale));
        if (!Locale.getDefault().equals(locale)) {
            arrayList.add(DateFormat.getDateTimeInstance(2, 2));
        }
        if (B.e.e()) {
            arrayList.add(B.j.c(2, 2));
        }
    }

    public final synchronized Date e(String str) {
        Iterator it = this.f83a.iterator();
        while (it.hasNext()) {
            try {
                return ((DateFormat) it.next()).parse(str);
            } catch (ParseException unused) {
            }
        }
        try {
            return D.a.c(str, new ParsePosition(0));
        } catch (ParseException e2) {
            throw new m(str, e2);
        }
    }

    @Override // z.o
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public Date b(G.a aVar) throws IOException {
        if (aVar.x() != G.b.NULL) {
            return e(aVar.v());
        }
        aVar.t();
        return null;
    }

    @Override // z.o
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public synchronized void d(G.c cVar, Date date) {
        if (date == null) {
            cVar.m();
        } else {
            cVar.z(((DateFormat) this.f83a.get(0)).format(date));
        }
    }
}

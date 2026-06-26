package C;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import z.m;
import z.o;
import z.p;

/* JADX INFO: loaded from: classes.dex */
public final class i extends o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final p f116b = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final DateFormat f117a = new SimpleDateFormat("MMM d, yyyy");

    public static class a implements p {
        @Override // z.p
        public o a(z.e eVar, F.a aVar) {
            if (aVar.d() == Date.class) {
                return new i();
            }
            return null;
        }
    }

    @Override // z.o
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public synchronized Date b(G.a aVar) {
        if (aVar.x() == G.b.NULL) {
            aVar.t();
            return null;
        }
        try {
            return new Date(this.f117a.parse(aVar.v()).getTime());
        } catch (ParseException e2) {
            throw new m(e2);
        }
    }

    @Override // z.o
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public synchronized void d(G.c cVar, Date date) {
        cVar.z(date == null ? null : this.f117a.format((java.util.Date) date));
    }
}

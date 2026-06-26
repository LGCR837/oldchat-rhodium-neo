package C;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import z.m;
import z.o;
import z.p;

/* JADX INFO: loaded from: classes.dex */
public final class j extends o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final p f118b = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final DateFormat f119a = new SimpleDateFormat("hh:mm:ss a");

    public static class a implements p {
        @Override // z.p
        public o a(z.e eVar, F.a aVar) {
            if (aVar.d() == Time.class) {
                return new j();
            }
            return null;
        }
    }

    @Override // z.o
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public synchronized Time b(G.a aVar) {
        if (aVar.x() == G.b.NULL) {
            aVar.t();
            return null;
        }
        try {
            return new Time(this.f119a.parse(aVar.v()).getTime());
        } catch (ParseException e2) {
            throw new m(e2);
        }
    }

    @Override // z.o
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public synchronized void d(G.c cVar, Time time) {
        cVar.z(time == null ? null : this.f119a.format((Date) time));
    }
}

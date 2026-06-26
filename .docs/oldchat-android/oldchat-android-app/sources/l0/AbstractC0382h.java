package l0;

import java.util.Calendar;

/* JADX INFO: renamed from: l0.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0382h {
    public static String a(long j2) {
        long jB = AbstractC0380g.b(j2);
        if (jB <= 0) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(jB);
        Calendar calendar2 = Calendar.getInstance();
        if (b(calendar, calendar2)) {
            return String.format("%02d:%02d", Integer.valueOf(calendar.get(11)), Integer.valueOf(calendar.get(12)));
        }
        if (!d(calendar, calendar2)) {
            return c(calendar, calendar2) ? String.format("%d月%d日 %02d:%02d", Integer.valueOf(calendar.get(2) + 1), Integer.valueOf(calendar.get(5)), Integer.valueOf(calendar.get(11)), Integer.valueOf(calendar.get(12))) : String.format("%d年%d月%d日", Integer.valueOf(calendar.get(1)), Integer.valueOf(calendar.get(2) + 1), Integer.valueOf(calendar.get(5)));
        }
        return "昨天 " + String.format("%02d:%02d", Integer.valueOf(calendar.get(11)), Integer.valueOf(calendar.get(12)));
    }

    public static boolean b(Calendar calendar, Calendar calendar2) {
        return calendar.get(1) == calendar2.get(1) && calendar.get(6) == calendar2.get(6);
    }

    public static boolean c(Calendar calendar, Calendar calendar2) {
        return calendar.get(1) == calendar2.get(1);
    }

    public static boolean d(Calendar calendar, Calendar calendar2) {
        Calendar calendar3 = (Calendar) calendar2.clone();
        calendar3.add(6, -1);
        return b(calendar, calendar3);
    }

    public static boolean e(long j2, long j3) {
        return Math.abs(AbstractC0380g.b(j2) - AbstractC0380g.b(j3)) > 300000;
    }
}

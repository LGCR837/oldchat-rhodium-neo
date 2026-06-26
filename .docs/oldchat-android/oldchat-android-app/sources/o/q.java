package o;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public class q {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static q f6939d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f6940a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LocationManager f6941b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a f6942c = new a();

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f6943a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f6944b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f6945c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f6946d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f6947e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f6948f;
    }

    public q(Context context, LocationManager locationManager) {
        this.f6940a = context;
        this.f6941b = locationManager;
    }

    public static q a(Context context) {
        if (f6939d == null) {
            Context applicationContext = context.getApplicationContext();
            f6939d = new q(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return f6939d;
    }

    public final Location b() {
        Location locationC = d.r.b(this.f6940a, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? c("network") : null;
        Location locationC2 = d.r.b(this.f6940a, "android.permission.ACCESS_FINE_LOCATION") == 0 ? c("gps") : null;
        return (locationC2 == null || locationC == null) ? locationC2 != null ? locationC2 : locationC : locationC2.getTime() > locationC.getTime() ? locationC2 : locationC;
    }

    public final Location c(String str) {
        LocationManager locationManager = this.f6941b;
        if (locationManager == null) {
            return null;
        }
        try {
            if (locationManager.isProviderEnabled(str)) {
                return this.f6941b.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e2) {
            Log.d("TwilightManager", "Failed to get last known location", e2);
            return null;
        }
    }

    public boolean d() {
        a aVar = this.f6942c;
        if (e()) {
            return aVar.f6943a;
        }
        Location locationB = b();
        if (locationB != null) {
            f(locationB);
            return aVar.f6943a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i2 = Calendar.getInstance().get(11);
        return i2 < 6 || i2 >= 22;
    }

    public final boolean e() {
        a aVar = this.f6942c;
        return aVar != null && aVar.f6948f > System.currentTimeMillis();
    }

    public final void f(Location location) {
        long j2;
        a aVar = this.f6942c;
        long jCurrentTimeMillis = System.currentTimeMillis();
        p pVarB = p.b();
        pVarB.a(jCurrentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        long j3 = pVarB.f6936a;
        pVarB.a(jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z2 = pVarB.f6938c == 1;
        long j4 = pVarB.f6937b;
        long j5 = pVarB.f6936a;
        boolean z3 = z2;
        pVarB.a(86400000 + jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        long j6 = pVarB.f6937b;
        if (j4 == -1 || j5 == -1) {
            j2 = 43200000 + jCurrentTimeMillis;
        } else {
            j2 = (jCurrentTimeMillis > j5 ? j6 : jCurrentTimeMillis > j4 ? j5 : j4) + 60000;
        }
        aVar.f6943a = z3;
        aVar.f6944b = j3;
        aVar.f6945c = j4;
        aVar.f6946d = j5;
        aVar.f6947e = j6;
        aVar.f6948f = j2;
    }
}

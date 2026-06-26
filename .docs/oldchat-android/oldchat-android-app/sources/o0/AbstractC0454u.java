package o0;

import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: renamed from: o0.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0454u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Map f7136a = new a(1024, 0.75f, true);

    /* JADX INFO: renamed from: o0.u$a */
    public class a extends LinkedHashMap {
        public a(int i2, float f2, boolean z2) {
            super(i2, f2, z2);
        }

        @Override // java.util.LinkedHashMap
        public boolean removeEldestEntry(Map.Entry entry) {
            return size() > 1024;
        }
    }

    public static void a() {
        Map map = f7136a;
        synchronized (map) {
            map.clear();
        }
    }

    public static boolean b(String str) {
        boolean zContainsKey;
        if (str == null || str.length() == 0) {
            return false;
        }
        Map map = f7136a;
        synchronized (map) {
            zContainsKey = map.containsKey(str);
        }
        return zContainsKey;
    }

    public static void c(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        Map map = f7136a;
        synchronized (map) {
            map.put(str, Boolean.TRUE);
        }
    }
}

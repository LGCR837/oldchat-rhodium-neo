package o0;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: renamed from: o0.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0450p {

    /* JADX INFO: renamed from: o0.p$a */
    public class a implements Comparator {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(b bVar, b bVar2) {
            if (bVar == bVar2) {
                return 0;
            }
            if (bVar == null && bVar2 == null) {
                return 0;
            }
            if (bVar == null) {
                return -1;
            }
            if (bVar2 == null) {
                return 1;
            }
            long j2 = bVar.f7133b;
            long j3 = bVar2.f7133b;
            if (j2 < j3) {
                return -1;
            }
            if (j2 > j3) {
                return 1;
            }
            int iCompareTo = bVar.f7134c.compareTo(bVar2.f7134c);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
            int i2 = bVar.f7135d;
            int i3 = bVar2.f7135d;
            if (i2 < i3) {
                return -1;
            }
            return i2 > i3 ? 1 : 0;
        }
    }

    /* JADX INFO: renamed from: o0.p$b */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final File f7132a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final long f7133b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final String f7134c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f7135d;

        public b(File file, int i2) {
            long jLastModified;
            String absolutePath;
            this.f7132a = file;
            this.f7135d = i2;
            if (file == null) {
                this.f7133b = Long.MIN_VALUE;
                this.f7134c = "";
                return;
            }
            try {
                jLastModified = file.lastModified();
            } catch (Exception unused) {
                jLastModified = -9223372036854775807L;
            }
            this.f7133b = jLastModified;
            try {
                absolutePath = file.getAbsolutePath();
            } catch (Exception unused2) {
                absolutePath = "";
            }
            this.f7134c = absolutePath != null ? absolutePath : "";
        }
    }

    public static void a(File[] fileArr) {
        if (fileArr == null || fileArr.length < 2) {
            return;
        }
        int length = fileArr.length;
        b[] bVarArr = new b[length];
        for (int i2 = 0; i2 < fileArr.length; i2++) {
            bVarArr[i2] = new b(fileArr[i2], i2);
        }
        Arrays.sort(bVarArr, new a());
        for (int i3 = 0; i3 < length; i3++) {
            b bVar = bVarArr[i3];
            fileArr[i3] = bVar == null ? null : bVar.f7132a;
        }
    }
}

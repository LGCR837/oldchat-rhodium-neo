package o0;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Locale;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: renamed from: o0.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0456w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f7156a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f7157b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final HashMap f7158c = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final HashMap f7159d = new HashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Object f7160e = new Object();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final HashMap f7161f = new HashMap();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static volatile long f7162g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static j.f f7163h;

    /* JADX INFO: renamed from: o0.w$a */
    public class a extends j.f {
        public a(int i2) {
            super(i2);
        }

        @Override // j.f
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public int g(String str, Bitmap bitmap) {
            return AbstractC0456w.j(bitmap);
        }
    }

    /* JADX INFO: renamed from: o0.w$b */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f7164a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f7165b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Bitmap f7166c;

        public b() {
        }

        public /* synthetic */ b(a aVar) {
            this();
        }
    }

    public static Object b(String str) {
        synchronized (f7157b) {
            try {
                HashMap map = f7158c;
                Object obj = map.get(str);
                HashMap map2 = f7159d;
                Integer num = (Integer) map2.get(str);
                if (obj == null) {
                    Object obj2 = new Object();
                    map.put(str, obj2);
                    map2.put(str, 1);
                    return obj2;
                }
                if (num == null) {
                    num = 0;
                }
                map2.put(str, Integer.valueOf(num.intValue() + 1));
                return obj;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void c(HttpURLConnection httpURLConnection, String str) {
        httpURLConnection.setRequestProperty("Accept", "image/*,*/*;q=0.8");
        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
        if (m(str)) {
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 4.0.4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Mobile Safari/537.36");
            httpURLConnection.setRequestProperty("Referer", "https://www.bilibili.com/");
            httpURLConnection.setRequestProperty("Origin", "https://www.bilibili.com");
        }
    }

    public static String d(String str, String str2, int i2, int i3) {
        if (str == null) {
            str = "";
        }
        return str + "#" + r.a(str2) + "#" + i2 + "#" + i3;
    }

    public static int e(int i2, int i3, int i4, int i5) {
        int i6 = 1;
        if (i4 > 0) {
            while (true) {
                if (i2 / i6 <= i4 && i3 / i6 <= i4) {
                    break;
                }
                i6 *= 2;
            }
        }
        if (i5 > 0) {
            while (((long) (i2 / i6)) * ((long) (i3 / i6)) * 2 > i5) {
                i6 *= 2;
            }
        }
        if (i6 < 1) {
            return 1;
        }
        return i6;
    }

    public static Bitmap f(String str, int i2, int i3) {
        int i4;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            int i5 = options.outWidth;
            if (i5 > 0 && (i4 = options.outHeight) > 0) {
                int iE = e(i5, i4, i2, i3);
                Bitmap.Config configP = p(options.outMimeType);
                options.inJustDecodeBounds = false;
                options.inSampleSize = iE;
                options.inPreferredConfig = configP;
                options.inDither = true;
                if (Build.VERSION.SDK_INT < 11) {
                    options.inPurgeable = true;
                    options.inInputShareable = true;
                }
                return BitmapFactory.decodeFile(str, options);
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public static Bitmap g(byte[] bArr, int i2, int i3) {
        int i4;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            int i5 = options.outWidth;
            if (i5 > 0 && (i4 = options.outHeight) > 0) {
                int iE = e(i5, i4, i2, i3);
                Bitmap.Config configP = p(options.outMimeType);
                options.inJustDecodeBounds = false;
                options.inSampleSize = iE;
                options.inPreferredConfig = configP;
                options.inDither = true;
                if (Build.VERSION.SDK_INT < 11) {
                    options.inPurgeable = true;
                    options.inInputShareable = true;
                }
                return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public static Bitmap h(Context context, String str, String str2, int i2, int i3) {
        Object obj;
        HashMap map;
        a aVar = null;
        if (str2 == null || str2.length() == 0) {
            return null;
        }
        String strD = d(str, str2, i2, i3);
        while (true) {
            obj = f7160e;
            synchronized (obj) {
                try {
                    map = f7161f;
                    b bVar = (b) map.get(strD);
                    if (bVar == null) {
                        b bVar2 = new b(aVar);
                        bVar2.f7164a = true;
                        map.put(strD, bVar2);
                    } else if (bVar.f7164a) {
                        bVar.f7165b++;
                        try {
                            obj.wait(12000L);
                            int i4 = bVar.f7165b - 1;
                            bVar.f7165b = i4;
                            if (!bVar.f7164a) {
                                Bitmap bitmap = bVar.f7166c;
                                if (i4 <= 0) {
                                    map.remove(strD);
                                }
                                if (bitmap != null && !bitmap.isRecycled()) {
                                    return bitmap;
                                }
                            }
                        } catch (InterruptedException unused) {
                            int i5 = bVar.f7165b - 1;
                            bVar.f7165b = i5;
                            if (i5 <= 0 && !bVar.f7164a) {
                                f7161f.remove(strD);
                            }
                            Thread.currentThread().interrupt();
                            return null;
                        }
                    } else {
                        Bitmap bitmap2 = bVar.f7166c;
                        map.remove(strD);
                        if (bitmap2 != null && !bitmap2.isRecycled()) {
                            return bitmap2;
                        }
                        bVar.f7164a = true;
                        bVar.f7166c = null;
                        map.put(strD, bVar);
                    }
                } finally {
                }
            }
        }
        try {
            Bitmap bitmapI = i(context, str, str2, i2, i3);
            synchronized (obj) {
                try {
                    b bVar3 = (b) map.get(strD);
                    if (bVar3 != null) {
                        bVar3.f7166c = bitmapI;
                        bVar3.f7164a = false;
                        if (bVar3.f7165b <= 0) {
                            map.remove(strD);
                        }
                    }
                    obj.notifyAll();
                } finally {
                }
            }
            return bitmapI;
        } catch (Throwable th) {
            synchronized (f7160e) {
                try {
                    b bVar4 = (b) f7161f.get(strD);
                    if (bVar4 != null) {
                        bVar4.f7166c = null;
                        bVar4.f7164a = false;
                        if (bVar4.f7165b <= 0) {
                            f7161f.remove(strD);
                        }
                    }
                    f7160e.notifyAll();
                    throw th;
                } finally {
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:198:0x0227 A[Catch: all -> 0x022b, TRY_ENTER, TryCatch #8 {all -> 0x022b, blocks: (B:198:0x0227, B:199:0x022a, B:142:0x01a4, B:152:0x01c3, B:165:0x01e7, B:170:0x01f2, B:178:0x0204), top: B:219:0x0048 }] */
    /* JADX WARN: Removed duplicated region for block: B:228:0x005e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0217 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:252:0x021e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:259:? A[Catch: all -> 0x0187, SYNTHETIC, TRY_ENTER, TRY_LEAVE, TryCatch #7 {all -> 0x0187, blocks: (B:128:0x0183, B:132:0x018a, B:133:0x018d, B:139:0x019c, B:141:0x01a1, B:147:0x01ad, B:149:0x01b7, B:151:0x01c0, B:157:0x01cc, B:159:0x01d2, B:160:0x01d5, B:162:0x01dd, B:164:0x01e4, B:163:0x01e1, B:169:0x01ef, B:176:0x01fe, B:177:0x0201, B:189:0x0217, B:193:0x021e, B:194:0x0221), top: B:230:0x0128 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0092 A[Catch: all -> 0x002d, TRY_ENTER, TRY_LEAVE, TryCatch #12 {all -> 0x002d, blocks: (B:10:0x001d, B:12:0x0023, B:14:0x0029, B:16:0x0030, B:18:0x003a, B:24:0x0045, B:44:0x0092, B:51:0x00a6, B:60:0x00ba, B:79:0x00e6, B:87:0x00f5), top: B:236:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x009a A[Catch: all -> 0x00c2, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x00c2, blocks: (B:41:0x0077, B:48:0x009a, B:57:0x00b2, B:68:0x00c9, B:78:0x00e3, B:86:0x00f2, B:94:0x0101, B:95:0x0104, B:96:0x0105, B:98:0x0125), top: B:218:0x0077 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Bitmap i(android.content.Context r24, java.lang.String r25, java.lang.String r26, int r27, int r28) {
        /*
            Method dump skipped, instruction units count: 565
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: o0.AbstractC0456w.i(android.content.Context, java.lang.String, java.lang.String, int, int):android.graphics.Bitmap");
    }

    public static int j(Bitmap bitmap) {
        int height;
        if (bitmap == null) {
            return 0;
        }
        if (Build.VERSION.SDK_INT >= 12) {
            height = bitmap.getByteCount();
        } else {
            height = bitmap.getHeight() * bitmap.getRowBytes();
        }
        return Math.max(1, height / 1024);
    }

    public static j.f k(Context context) {
        j.f fVar = f7163h;
        if (fVar != null) {
            return fVar;
        }
        synchronized (f7156a) {
            try {
                if (f7163h == null) {
                    f7163h = new a(t(context));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return f7163h;
    }

    public static int l(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager != null) {
                return activityManager.getMemoryClass();
            }
        } catch (Exception unused) {
        }
        return 0;
    }

    public static boolean m(String str) {
        if (str == null) {
            return false;
        }
        String lowerCase = str.toLowerCase(Locale.US);
        return lowerCase.indexOf("hdslb.com") >= 0 || lowerCase.indexOf("biliimg.com") >= 0 || lowerCase.indexOf("bilibili.com") >= 0;
    }

    public static boolean n(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        long jLastModified = file.lastModified();
        return jLastModified > 0 && System.currentTimeMillis() - jLastModified > 604800000;
    }

    public static void o(Context context, String str) {
        if (context == null || str == null || str.length() == 0) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - f7162g < 7200000) {
            return;
        }
        synchronized (f7156a) {
            try {
                if (jCurrentTimeMillis - f7162g < 7200000) {
                    return;
                }
                f7162g = jCurrentTimeMillis;
                File[] fileArrListFiles = new File(context.getCacheDir(), str).listFiles();
                if (fileArrListFiles == null || fileArrListFiles.length == 0) {
                    return;
                }
                long jMax = 0;
                int i2 = 0;
                for (File file : fileArrListFiles) {
                    if (file != null && file.isFile()) {
                        String name = file.getName();
                        if (name != null && name.endsWith(".tmp") && jCurrentTimeMillis - file.lastModified() > 1200000) {
                            file.delete();
                        } else if (n(file)) {
                            file.delete();
                        } else {
                            jMax += Math.max(0L, file.length());
                            i2++;
                        }
                    }
                }
                if (i2 <= 0) {
                    return;
                }
                long jU = u(context);
                if (jMax <= jU) {
                    return;
                }
                AbstractC0450p.a(fileArrListFiles);
                for (int i3 = 0; i3 < fileArrListFiles.length && jMax > jU; i3++) {
                    File file2 = fileArrListFiles[i3];
                    if (file2 != null && file2.isFile()) {
                        long jMax2 = Math.max(0L, file2.length());
                        if (file2.delete()) {
                            jMax -= jMax2;
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static Bitmap.Config p(String str) {
        if (str == null) {
            return Bitmap.Config.RGB_565;
        }
        String lowerCase = str.toLowerCase(Locale.US);
        return (lowerCase.indexOf("png") >= 0 || lowerCase.indexOf("webp") >= 0 || lowerCase.indexOf("gif") >= 0) ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
    }

    public static byte[] q(InputStream inputStream, int i2, long j2) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[PKIFailureInfo.certRevoked];
        int i3 = 0;
        while (true) {
            int i4 = inputStream.read(bArr);
            if (i4 == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            if ((j2 > 0 && System.currentTimeMillis() > j2) || (i3 = i3 + i4) > i2) {
                return null;
            }
            byteArrayOutputStream.write(bArr, 0, i4);
        }
    }

    public static void r(String str) {
        synchronized (f7157b) {
            try {
                HashMap map = f7159d;
                Integer num = (Integer) map.get(str);
                if (num != null && num.intValue() > 1) {
                    map.put(str, Integer.valueOf(num.intValue() - 1));
                    return;
                }
                map.remove(str);
                f7158c.remove(str);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static int s(Context context) {
        int iL = l(context);
        if (Build.VERSION.SDK_INT <= 10) {
            return (iL <= 0 || iL > 32) ? 96 : 64;
        }
        if (iL <= 0 || iL > 64) {
            return (iL <= 0 || iL > 128) ? 192 : 128;
        }
        return 96;
    }

    public static int t(Context context) {
        int iL = l(context);
        if (iL <= 0) {
            return 6144;
        }
        int i2 = (iL * 1024) / 10;
        int i3 = iL <= 128 ? 3072 : 6144;
        if (i2 > i3) {
            i2 = i3;
        }
        if (i2 < 1536) {
            return 1536;
        }
        return i2;
    }

    public static long u(Context context) {
        int iL = l(context);
        if (iL <= 0) {
            return 100663296L;
        }
        if (iL <= 64) {
            return 50331648L;
        }
        return iL <= 192 ? 100663296L : 134217728L;
    }

    public static int v(Context context) {
        int iL = l(context);
        if (Build.VERSION.SDK_INT <= 10) {
            return (iL <= 0 || iL > 32) ? 480 : 360;
        }
        if (iL <= 0 || iL > 64) {
            return (iL <= 0 || iL > 128) ? 1024 : 640;
        }
        return 480;
    }

    public static int w(int i2) {
        if (i2 <= 160) {
            return 614400;
        }
        return i2 <= 640 ? PKIFailureInfo.badSenderNonce : i2 <= 1200 ? 4194304 : 6291456;
    }

    public static boolean x(String str) {
        String strJ;
        if (str == null || str.length() == 0 || !A.d(str) || (strJ = A.j(str)) == null || strJ.length() == 0 || strJ.equals(str)) {
            return false;
        }
        return A.c(strJ);
    }

    public static void y(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        try {
            file.setLastModified(System.currentTimeMillis());
        } catch (Exception unused) {
        }
    }
}

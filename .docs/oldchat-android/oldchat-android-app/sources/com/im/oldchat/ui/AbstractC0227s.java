package com.im.oldchat.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import o0.AbstractC0453t;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: renamed from: com.im.oldchat.ui.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0227s {

    /* JADX INFO: renamed from: com.im.oldchat.ui.s$a */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4736a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Context f4737b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ d f4738c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ boolean f4739d;

        public a(String str, Context context, d dVar, boolean z2) {
            this.f4736a = str;
            this.f4737b = context;
            this.f4738c = dVar;
            this.f4739d = z2;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            long j2;
            File file;
            boolean zC;
            File file2 = null;
            try {
                try {
                    String strD = AbstractC0227s.D(this.f4736a);
                    String strZ = AbstractC0227s.z(strD);
                    List listV = AbstractC0227s.v(this.f4737b);
                    c cVarS = AbstractC0227s.s(listV, strZ);
                    if (cVarS != null) {
                        String str = cVarS.f4744b;
                        if (str != null && str.length() > 0 && new File(cVarS.f4744b).exists()) {
                            AbstractC0227s.A(this.f4738c, true, "该表情已在本地缓存");
                            return;
                        } else {
                            AbstractC0227s.B(listV, cVarS.f4743a);
                            AbstractC0227s.E(this.f4737b, listV);
                        }
                    }
                    File file3 = new File(this.f4737b.getFilesDir(), "emojis");
                    if (!file3.exists()) {
                        file3.mkdirs();
                    }
                    String strT = AbstractC0227s.t(strD, this.f4739d);
                    File file4 = new File(file3, "emoji_" + System.currentTimeMillis() + strT);
                    File file5 = new File(this.f4737b.getCacheDir(), "emoji_tmp_" + System.currentTimeMillis());
                    try {
                        String str2 = "表情包不能超过3MB";
                        if (!AbstractC0227s.m(this.f4737b, strD, file5) && !AbstractC0227s.q(strD, file5, 3145728L)) {
                            if (!file5.exists() || file5.length() <= 3145728) {
                                str2 = "下载表情失败";
                            }
                            AbstractC0227s.A(this.f4738c, false, str2);
                            if (file5.exists()) {
                                file5.delete();
                                return;
                            }
                            return;
                        }
                        if (file5.length() > 3145728) {
                            AbstractC0227s.A(this.f4738c, false, "表情包不能超过3MB");
                            if (file5.exists()) {
                                file5.delete();
                                return;
                            }
                            return;
                        }
                        if (this.f4739d) {
                            zC = AbstractC0227s.w(file5, file4);
                            j2 = 3145728;
                            file = file4;
                        } else {
                            j2 = 3145728;
                            file = file4;
                            zC = AbstractC0453t.c(this.f4737b, Uri.fromFile(file5), file4, 256, 85, ".png".equalsIgnoreCase(strT), 204800L);
                            if (!zC) {
                                zC = AbstractC0227s.w(file5, file);
                            }
                        }
                        if (zC && file.length() > j2) {
                            file.delete();
                            zC = false;
                        }
                        if (!zC) {
                            AbstractC0227s.A(this.f4738c, false, "保存表情失败");
                            if (file5.exists()) {
                                file5.delete();
                                return;
                            }
                            return;
                        }
                        if (AbstractC0227s.o(this.f4737b, file, this.f4739d, strZ) == null) {
                            AbstractC0227s.A(this.f4738c, false, "保存表情失败");
                            if (file5.exists()) {
                                file5.delete();
                                return;
                            }
                            return;
                        }
                        AbstractC0227s.A(this.f4738c, true, "已保存到表情包");
                        if (file5.exists()) {
                            file5.delete();
                        }
                    } catch (Exception unused) {
                        file2 = file5;
                        AbstractC0227s.A(this.f4738c, false, "保存表情失败");
                        if (file2 == null || !file2.exists()) {
                            return;
                        }
                        file2.delete();
                    } catch (Throwable th) {
                        th = th;
                        file2 = file5;
                        if (file2 != null && file2.exists()) {
                            file2.delete();
                        }
                        throw th;
                    }
                } catch (Exception unused2) {
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.s$b */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ d f4740a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f4741b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f4742c;

        public b(d dVar, boolean z2, String str) {
            this.f4740a = dVar;
            this.f4741b = z2;
            this.f4742c = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f4740a.a(this.f4741b, this.f4742c);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.s$c */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f4743a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f4744b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f4745c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f4746d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f4747e;
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.s$d */
    public interface d {
        void a(boolean z2, String str);
    }

    public static void A(d dVar, boolean z2, String str) {
        if (dVar == null) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new b(dVar, z2, str));
    }

    public static void B(List list, String str) {
        if (list == null || list.isEmpty() || str == null || str.length() == 0) {
            return;
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            c cVar = (c) list.get(size);
            if (cVar != null && str.equals(cVar.f4743a)) {
                list.remove(size);
                return;
            }
        }
    }

    public static String C(Context context, Uri uri) {
        String path;
        int iLastIndexOf;
        String type = context.getContentResolver().getType(uri);
        String extensionFromMimeType = type != null ? MimeTypeMap.getSingleton().getExtensionFromMimeType(type) : null;
        if ((extensionFromMimeType == null || extensionFromMimeType.isEmpty()) && (path = uri.getPath()) != null && (iLastIndexOf = path.lastIndexOf(46)) >= 0 && iLastIndexOf < path.length() - 1) {
            extensionFromMimeType = path.substring(iLastIndexOf + 1);
        }
        if (extensionFromMimeType == null || extensionFromMimeType.isEmpty()) {
            return null;
        }
        String lowerCase = extensionFromMimeType.toLowerCase();
        if ("jpg".equals(lowerCase) || "jpeg".equals(lowerCase)) {
            return ".jpg";
        }
        if ("png".equals(lowerCase)) {
            return ".png";
        }
        if ("gif".equals(lowerCase)) {
            return ".gif";
        }
        return null;
    }

    public static String D(String str) {
        return o0.A.g(str);
    }

    public static void E(Context context, List list) {
        if (context == null) {
            return;
        }
        z.e eVar = new z.e();
        if (list == null) {
            list = new ArrayList();
        }
        context.getSharedPreferences("emoji_store", 0).edit().putString("emoji_list", eVar.q(list)).apply();
    }

    public static void F(Context context, String str, boolean z2, d dVar) {
        if (context != null && str != null && !str.isEmpty()) {
            new Thread(new a(str, context, dVar, z2)).start();
        } else if (dVar != null) {
            dVar.a(false, "无效的表情地址");
        }
    }

    public static c k(Context context, Uri uri) throws Throwable {
        String strC;
        boolean zC;
        if (context == null || uri == null || (strC = C(context, uri)) == null || strC.isEmpty()) {
            return null;
        }
        File file = new File(context.getFilesDir(), "emojis");
        if (!file.exists() && !file.mkdirs()) {
            return null;
        }
        File file2 = new File(file, "emoji_" + System.currentTimeMillis() + strC);
        boolean zEqualsIgnoreCase = ".gif".equalsIgnoreCase(strC);
        if (zEqualsIgnoreCase || !(zC = AbstractC0453t.c(context, uri, file2, 256, 85, ".png".equalsIgnoreCase(strC), 204800L))) {
            zC = n(context, uri, file2, 3145728L);
        }
        if (zC && file2.length() > 3145728) {
            file2.delete();
            zC = false;
        }
        if (zC) {
            return o(context, file2, zEqualsIgnoreCase, null);
        }
        return null;
    }

    public static boolean l(File file, File file2) throws Throwable {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    byte[] bArr = new byte[PKIFailureInfo.certRevoked];
                    while (true) {
                        int i2 = fileInputStream2.read(bArr);
                        if (i2 == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, i2);
                    }
                    fileOutputStream.flush();
                    try {
                        fileInputStream2.close();
                    } catch (Exception unused) {
                    }
                    try {
                        fileOutputStream.close();
                        return true;
                    } catch (Exception unused2) {
                        return true;
                    }
                } catch (Exception unused3) {
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception unused4) {
                        }
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception unused5) {
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception unused6) {
                        }
                    }
                    if (fileOutputStream == null) {
                        throw th;
                    }
                    try {
                        fileOutputStream.close();
                        throw th;
                    } catch (Exception unused7) {
                        throw th;
                    }
                }
            } catch (Exception unused8) {
                fileOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
            }
        } catch (Exception unused9) {
            fileOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
        }
    }

    public static boolean m(Context context, String str, File file) {
        File fileC;
        if (context == null || str == null || str.length() == 0 || file == null || (fileC = o0.r.c(context.getApplicationContext(), "img_cache", str)) == null || !fileC.exists()) {
            return false;
        }
        return l(fileC, file);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0040, code lost:
    
        r9.flush();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0043, code lost:
    
        r8.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean n(android.content.Context r8, android.net.Uri r9, java.io.File r10, long r11) throws java.lang.Throwable {
        /*
            r0 = 0
            r1 = 0
            android.content.ContentResolver r8 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L54
            java.io.InputStream r8 = r8.openInputStream(r9)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L54
            if (r8 != 0) goto L12
            if (r8 == 0) goto L11
            r8.close()     // Catch: java.lang.Exception -> L11
        L11:
            return r0
        L12:
            java.io.FileOutputStream r9 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4e
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4e
            r10 = 8192(0x2000, float:1.148E-41)
            byte[] r10 = new byte[r10]     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3d
            r1 = 0
            r3 = r1
        L1e:
            int r5 = r8.read(r10)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3d
            r6 = -1
            if (r5 == r6) goto L40
            long r6 = (long) r5
            long r3 = r3 + r6
            int r6 = (r11 > r1 ? 1 : (r11 == r1 ? 0 : -1))
            if (r6 <= 0) goto L36
            int r6 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
            if (r6 <= 0) goto L36
            r8.close()     // Catch: java.lang.Exception -> L32
        L32:
            r9.close()     // Catch: java.lang.Exception -> L35
        L35:
            return r0
        L36:
            r9.write(r10, r0, r5)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3d
            goto L1e
        L3a:
            r10 = move-exception
        L3b:
            r1 = r8
            goto L57
        L3d:
        L3e:
            r1 = r8
            goto L64
        L40:
            r9.flush()     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3d
            r8.close()     // Catch: java.lang.Exception -> L46
        L46:
            r9.close()     // Catch: java.lang.Exception -> L49
        L49:
            r8 = 1
            return r8
        L4b:
            r10 = move-exception
            r9 = r1
            goto L3b
        L4e:
            r9 = r1
            goto L3e
        L51:
            r10 = move-exception
            r9 = r1
            goto L57
        L54:
            r9 = r1
            goto L64
        L57:
            if (r1 == 0) goto L5e
            r1.close()     // Catch: java.lang.Exception -> L5d
            goto L5e
        L5d:
        L5e:
            if (r9 == 0) goto L63
            r9.close()     // Catch: java.lang.Exception -> L63
        L63:
            throw r10
        L64:
            if (r1 == 0) goto L6b
            r1.close()     // Catch: java.lang.Exception -> L6a
            goto L6b
        L6a:
        L6b:
            if (r9 == 0) goto L70
            r9.close()     // Catch: java.lang.Exception -> L70
        L70:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.im.oldchat.ui.AbstractC0227s.n(android.content.Context, android.net.Uri, java.io.File, long):boolean");
    }

    public static c o(Context context, File file, boolean z2, String str) {
        if (context == null || file == null || !file.exists()) {
            return null;
        }
        c cVar = new c();
        cVar.f4743a = String.valueOf(System.currentTimeMillis());
        cVar.f4744b = file.getAbsolutePath();
        cVar.f4745c = z2;
        if (str == null) {
            str = "";
        }
        cVar.f4746d = str;
        cVar.f4747e = "未分类";
        List listV = v(context);
        listV.add(cVar);
        E(context, listV);
        return cVar;
    }

    public static boolean p(Context context, c cVar) {
        boolean z2 = false;
        if (context != null && cVar != null) {
            List listV = v(context);
            for (int size = listV.size() - 1; size >= 0; size--) {
                c cVar2 = (c) listV.get(size);
                if (cVar2 != null) {
                    String str = cVar.f4743a;
                    if (str == null || !str.equals(cVar2.f4743a)) {
                        String str2 = cVar.f4744b;
                        if (str2 != null && str2.equals(cVar2.f4744b)) {
                            listV.remove(size);
                        }
                    } else {
                        listV.remove(size);
                    }
                    z2 = true;
                    break;
                }
            }
            if (z2) {
                E(context, listV);
            }
            if (cVar.f4744b != null) {
                File file = new File(cVar.f4744b);
                if (file.exists()) {
                    file.delete();
                }
            }
        }
        return z2;
    }

    public static boolean q(String str, File file, long j2) {
        String[] strArrI = o0.A.i(str);
        if (strArrI == null || strArrI.length == 0) {
            return r(str, file, j2);
        }
        for (String str2 : strArrI) {
            if (str2 != null && str2.length() != 0 && r(str2, file, j2)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0065, code lost:
    
        r2.flush();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0068, code lost:
    
        r9.close();
     */
    /* JADX WARN: Removed duplicated region for block: B:62:0x008b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0092 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean r(java.lang.String r9, java.io.File r10, long r11) throws java.lang.Throwable {
        /*
            r0 = 0
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L79
            r2.<init>(r9)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L79
            java.net.URLConnection r9 = r2.openConnection()     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L79
            java.net.HttpURLConnection r9 = (java.net.HttpURLConnection) r9     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L79
            r2 = 5000(0x1388, float:7.006E-42)
            r9.setConnectTimeout(r2)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L79
            r2 = 8000(0x1f40, float:1.121E-41)
            r9.setReadTimeout(r2)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L79
            r9.connect()     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L79
            int r2 = r9.getResponseCode()     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L79
            r3 = 200(0xc8, float:2.8E-43)
            if (r2 == r3) goto L23
            return r0
        L23:
            int r2 = r9.getContentLength()     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L79
            r3 = 0
            int r5 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
            if (r5 <= 0) goto L35
            if (r2 <= 0) goto L35
            long r5 = (long) r2     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L79
            int r2 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r2 <= 0) goto L35
            return r0
        L35:
            java.io.InputStream r9 = r9.getInputStream()     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L79
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L73
            r2.<init>(r10)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L73
            r10 = 8192(0x2000, float:1.148E-41)
            byte[] r10 = new byte[r10]     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L62
            r5 = r3
        L43:
            int r1 = r9.read(r10)     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L62
            r7 = -1
            if (r1 == r7) goto L65
            long r7 = (long) r1
            long r5 = r5 + r7
            int r7 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
            if (r7 <= 0) goto L5b
            int r7 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r7 <= 0) goto L5b
            r9.close()     // Catch: java.lang.Exception -> L57
        L57:
            r2.close()     // Catch: java.lang.Exception -> L5a
        L5a:
            return r0
        L5b:
            r2.write(r10, r0, r1)     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L62
            goto L43
        L5f:
            r10 = move-exception
        L60:
            r1 = r9
            goto L7c
        L62:
        L63:
            r1 = r9
            goto L89
        L65:
            r2.flush()     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L62
            r9.close()     // Catch: java.lang.Exception -> L6b
        L6b:
            r2.close()     // Catch: java.lang.Exception -> L6e
        L6e:
            r9 = 1
            return r9
        L70:
            r10 = move-exception
            r2 = r1
            goto L60
        L73:
            r2 = r1
            goto L63
        L76:
            r10 = move-exception
            r2 = r1
            goto L7c
        L79:
            r2 = r1
            goto L89
        L7c:
            if (r1 == 0) goto L83
            r1.close()     // Catch: java.lang.Exception -> L82
            goto L83
        L82:
        L83:
            if (r2 == 0) goto L88
            r2.close()     // Catch: java.lang.Exception -> L88
        L88:
            throw r10
        L89:
            if (r1 == 0) goto L90
            r1.close()     // Catch: java.lang.Exception -> L8f
            goto L90
        L8f:
        L90:
            if (r2 == 0) goto L95
            r2.close()     // Catch: java.lang.Exception -> L95
        L95:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.im.oldchat.ui.AbstractC0227s.r(java.lang.String, java.io.File, long):boolean");
    }

    public static c s(List list, String str) {
        String str2;
        if (list != null && !list.isEmpty() && str != null && str.length() != 0) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                if (cVar != null && (str2 = cVar.f4746d) != null && str.equals(z(str2))) {
                    return cVar;
                }
            }
        }
        return null;
    }

    public static String t(String str, boolean z2) {
        int iLastIndexOf;
        if (z2) {
            return ".gif";
        }
        if (str != null && (iLastIndexOf = str.lastIndexOf(46)) >= 0 && iLastIndexOf < str.length() - 1) {
            String lowerCase = str.substring(iLastIndexOf + 1).toLowerCase();
            if ("png".equals(lowerCase)) {
                return ".png";
            }
            if (!"jpg".equals(lowerCase)) {
                "jpeg".equals(lowerCase);
            }
        }
        return ".jpg";
    }

    public static boolean u(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        return lowerCase.endsWith(".jpg") || lowerCase.endsWith(".jpeg") || lowerCase.endsWith(".png") || lowerCase.endsWith(".gif");
    }

    public static List v(Context context) {
        boolean z2 = false;
        if (context == null) {
            return new ArrayList();
        }
        String string = context.getSharedPreferences("emoji_store", 0).getString("emoji_list", null);
        if (string == null || string.isEmpty()) {
            return new ArrayList();
        }
        try {
            List<c> list = (List) new z.e().j(string, F.a.c(List.class, c.class).e());
            if (list == null) {
                return new ArrayList();
            }
            for (c cVar : list) {
                if (cVar != null) {
                    String strX = x(cVar.f4747e);
                    String str = cVar.f4747e;
                    if (str == null || !strX.equals(str)) {
                        cVar.f4747e = strX;
                        z2 = true;
                    }
                }
            }
            if (z2) {
                E(context, list);
            }
            return list;
        } catch (Exception unused) {
            return new ArrayList();
        }
    }

    public static boolean w(File file, File file2) throws Throwable {
        if (file == null || file2 == null) {
            return false;
        }
        if (file.renameTo(file2)) {
            return true;
        }
        boolean zL = l(file, file2);
        if (zL) {
            file.delete();
        }
        return zL;
    }

    public static String x(String str) {
        if (str == null) {
            return "未分类";
        }
        String strTrim = str.trim();
        return strTrim.length() == 0 ? "未分类" : strTrim.length() > 10 ? strTrim.substring(0, 10) : strTrim;
    }

    public static String y(String str, boolean z2) {
        if (z2) {
            return ".gif";
        }
        if (str != null) {
            String lowerCase = str.toLowerCase();
            if (lowerCase.endsWith(".png")) {
                return ".png";
            }
            if (!lowerCase.endsWith(".jpg") && !lowerCase.endsWith(".jpeg") && lowerCase.endsWith(".gif")) {
                return ".gif";
            }
        }
        return ".jpg";
    }

    public static String z(String str) {
        if (str == null) {
            return "";
        }
        String strTrim = str.trim();
        if (strTrim.length() == 0) {
            return "";
        }
        int iIndexOf = strTrim.indexOf(63);
        if (iIndexOf >= 0) {
            strTrim = strTrim.substring(0, iIndexOf);
        }
        int iIndexOf2 = strTrim.indexOf(35);
        return iIndexOf2 >= 0 ? strTrim.substring(0, iIndexOf2) : strTrim;
    }
}

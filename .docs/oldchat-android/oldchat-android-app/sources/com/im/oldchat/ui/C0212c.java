package com.im.oldchat.ui;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import android.widget.Toast;
import com.im.oldchat.ui.AbstractC0213d;
import com.im.oldchat.ui.C0218i;
import java.util.Calendar;
import java.util.Locale;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.im.oldchat.ui.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0212c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Activity f4440a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0218i.k f4441b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b f4442c;

    /* JADX INFO: renamed from: com.im.oldchat.ui.c$a */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4443a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Uri f4444b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f4445c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f4446d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ long f4447e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ String f4448f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ String f4449g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ String f4450h;

        /* JADX INFO: renamed from: com.im.oldchat.ui.c$a$a, reason: collision with other inner class name */
        public class C0032a implements AbstractC0213d.a {

            /* JADX INFO: renamed from: com.im.oldchat.ui.c$a$a$a, reason: collision with other inner class name */
            public class RunnableC0033a implements Runnable {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public final /* synthetic */ int f4453a;

                public RunnableC0033a(int i2) {
                    this.f4453a = i2;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (C0212c.this.f4442c != null) {
                        b bVar = C0212c.this.f4442c;
                        a aVar = a.this;
                        bVar.d(aVar.f4448f, aVar.f4449g, aVar.f4447e, this.f4453a);
                    }
                }
            }

            public C0032a() {
            }

            @Override // com.im.oldchat.ui.AbstractC0213d.a
            public void a(int i2) {
                C0212c.this.f4440a.runOnUiThread(new RunnableC0033a(i2));
            }
        }

        /* JADX INFO: renamed from: com.im.oldchat.ui.c$a$b */
        public class b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ AbstractC0213d.b f4455a;

            public b(AbstractC0213d.b bVar) {
                this.f4455a = bVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (C0212c.this.f4441b != null) {
                    C0212c.this.f4441b.a(false);
                }
                a aVar = a.this;
                C0212c.this.g(aVar.f4448f, aVar.f4449g, aVar.f4447e, this.f4455a, aVar.f4450h);
            }
        }

        public a(String str, Uri uri, String str2, String str3, long j2, String str4, String str5, String str6) {
            this.f4443a = str;
            this.f4444b = uri;
            this.f4445c = str2;
            this.f4446d = str3;
            this.f4447e = j2;
            this.f4448f = str4;
            this.f4449g = str5;
            this.f4450h = str6;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            C0212c.this.f4440a.runOnUiThread(new b(AbstractC0213d.l(C0212c.this.f4440a, this.f4443a, this.f4444b, this.f4445c, this.f4446d, this.f4447e, new C0032a())));
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.c$b */
    public interface b {
        void a(String str, String str2, long j2, String str3);

        void b(String str, String str2, long j2, String str3);

        void c(String str, String str2, long j2);

        void d(String str, String str2, long j2, int i2);
    }

    public C0212c(Activity activity, C0218i.k kVar, b bVar) {
        this.f4440a = activity;
        this.f4441b = kVar;
        this.f4442c = bVar;
    }

    public final String e(String str) {
        String strTrim = str == null ? "" : str.trim();
        if (strTrim.length() == 0) {
            strTrim = "file";
        }
        Calendar calendar = Calendar.getInstance();
        String str2 = String.format(Locale.US, "%02d%d%02d%02d%02d", Integer.valueOf(calendar.get(1) % 100), Integer.valueOf(calendar.get(2) + 1), Integer.valueOf(calendar.get(5)), Integer.valueOf(calendar.get(11)), Integer.valueOf(calendar.get(12)));
        int iLastIndexOf = strTrim.lastIndexOf(46);
        if (iLastIndexOf <= 0 || iLastIndexOf >= strTrim.length() - 1) {
            return strTrim + "-" + str2;
        }
        return strTrim.substring(0, iLastIndexOf) + "-" + str2 + strTrim.substring(iLastIndexOf);
    }

    public boolean f(int i2, int i3, Intent intent) {
        if (i2 != 2005) {
            return false;
        }
        if (i3 == -1 && intent != null && intent.getData() != null) {
            l(intent.getData());
        }
        return true;
    }

    public final void g(String str, String str2, long j2, AbstractC0213d.b bVar, String str3) {
        if (bVar == null) {
            b bVar2 = this.f4442c;
            if (bVar2 != null) {
                bVar2.a(str, str2, j2, "发送文件失败");
            }
            Toast.makeText(this.f4440a, "发送文件失败", 0).show();
            return;
        }
        int i2 = bVar.f4479a;
        if (i2 < 200 || i2 >= 300) {
            String str4 = bVar.f4480b;
            if (str4 == null) {
                str4 = "";
            }
            if (i2 == 413 || str4.contains("file_too_large")) {
                b bVar3 = this.f4442c;
                if (bVar3 != null) {
                    bVar3.a(str, str2, j2, "文件不能超过1GB");
                }
                Toast.makeText(this.f4440a, "文件不能超过1GB", 0).show();
                return;
            }
            b bVar4 = this.f4442c;
            if (bVar4 != null) {
                bVar4.a(str, str2, j2, "发送文件失败: " + bVar.f4479a);
            }
            Toast.makeText(this.f4440a, "发送文件失败: " + bVar.f4479a, 0).show();
            return;
        }
        try {
            String str5 = bVar.f4480b;
            if (str5 == null) {
                str5 = "{}";
            }
            JSONObject jSONObject = new JSONObject(str5);
            String strOptString = jSONObject.optString("url");
            if (strOptString == null || strOptString.length() == 0) {
                strOptString = jSONObject.optString("download_path");
            }
            String strF = AbstractC0213d.f(strOptString, str3);
            if (strF != null && strF.length() != 0) {
                b bVar5 = this.f4442c;
                if (bVar5 != null) {
                    bVar5.b(str, str2, j2, strF);
                    return;
                }
                return;
            }
            b bVar6 = this.f4442c;
            if (bVar6 != null) {
                bVar6.a(str, str2, j2, "文件上传响应无下载链接");
            }
            Toast.makeText(this.f4440a, "文件上传响应无下载链接", 0).show();
        } catch (Exception unused) {
            b bVar7 = this.f4442c;
            if (bVar7 != null) {
                bVar7.a(str, str2, j2, "发送文件失败");
            }
            Toast.makeText(this.f4440a, "发送文件失败", 0).show();
        }
    }

    public void h() {
        if (this.f4440a == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("*/*");
        intent.addCategory("android.intent.category.OPENABLE");
        this.f4440a.startActivityForResult(Intent.createChooser(intent, "选择文件"), 2005);
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x0043 A[EXC_TOP_SPLITTER, PHI: r2
  0x0043: PHI (r2v3 android.database.Cursor) = (r2v2 android.database.Cursor), (r2v4 android.database.Cursor) binds: [B:26:0x004f, B:19:0x0041] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String i(android.net.Uri r11) {
        /*
            r10 = this;
            r0 = 1
            java.lang.String r1 = "_display_name"
            r2 = 0
            android.app.Activity r3 = r10.f4440a     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            android.content.ContentResolver r4 = r3.getContentResolver()     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            java.lang.String[] r6 = new java.lang.String[r0]     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            r3 = 0
            r6[r3] = r1     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            r8 = 0
            r9 = 0
            r7 = 0
            r5 = r11
            android.database.Cursor r2 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            if (r2 == 0) goto L41
            boolean r3 = r2.moveToFirst()     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            if (r3 == 0) goto L41
            int r1 = r2.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            if (r1 < 0) goto L41
            java.lang.String r1 = r2.getString(r1)     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            if (r1 == 0) goto L41
            java.lang.String r3 = r1.trim()     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            int r3 = r3.length()     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            if (r3 <= 0) goto L41
            java.lang.String r11 = r1.trim()     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            r2.close()     // Catch: java.lang.Exception -> L3c
        L3c:
            return r11
        L3d:
            r11 = move-exception
            goto L49
        L3f:
            goto L4f
        L41:
            if (r2 == 0) goto L52
        L43:
            r2.close()     // Catch: java.lang.Exception -> L47
            goto L52
        L47:
            goto L52
        L49:
            if (r2 == 0) goto L4e
            r2.close()     // Catch: java.lang.Exception -> L4e
        L4e:
            throw r11
        L4f:
            if (r2 == 0) goto L52
            goto L43
        L52:
            java.lang.String r11 = r11.getPath()
            if (r11 == 0) goto L78
            int r1 = r11.length()
            if (r1 <= 0) goto L78
            r1 = 47
            int r1 = r11.lastIndexOf(r1)
            if (r1 < 0) goto L71
            int r1 = r1 + r0
            int r0 = r11.length()
            if (r1 >= r0) goto L71
            java.lang.String r11 = r11.substring(r1)
        L71:
            int r0 = r11.length()
            if (r0 <= 0) goto L78
            return r11
        L78:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r0 = "file_"
            r11.append(r0)
            long r0 = java.lang.System.currentTimeMillis()
            r11.append(r0)
            java.lang.String r11 = r11.toString()
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.im.oldchat.ui.C0212c.i(android.net.Uri):java.lang.String");
    }

    public final long j(Uri uri) {
        int columnIndex;
        Cursor cursorQuery = null;
        try {
            cursorQuery = this.f4440a.getContentResolver().query(uri, new String[]{"_size"}, null, null, null);
            if (cursorQuery != null && cursorQuery.moveToFirst() && (columnIndex = cursorQuery.getColumnIndex("_size")) >= 0) {
                long j2 = cursorQuery.getLong(columnIndex);
                if (j2 > 0) {
                    try {
                        cursorQuery.close();
                    } catch (Exception unused) {
                    }
                    return j2;
                }
            }
            if (cursorQuery == null) {
                return -1L;
            }
        } catch (Exception unused2) {
            if (cursorQuery == null) {
                return -1L;
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (Exception unused3) {
                }
            }
            throw th;
        }
        try {
            cursorQuery.close();
            return -1L;
        } catch (Exception unused4) {
            return -1L;
        }
    }

    public final String k(Uri uri, String str) {
        String mimeTypeFromExtension;
        int iLastIndexOf;
        int i2;
        String type = this.f4440a.getContentResolver().getType(uri);
        if (type != null && type.length() > 0) {
            return type;
        }
        String lowerCase = (str == null || (iLastIndexOf = str.lastIndexOf(46)) < 0 || (i2 = iLastIndexOf + 1) >= str.length()) ? "" : str.substring(i2).toLowerCase();
        return (lowerCase.length() <= 0 || (mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(lowerCase)) == null || mimeTypeFromExtension.length() <= 0) ? "application/octet-stream" : mimeTypeFromExtension;
    }

    public final void l(Uri uri) {
        if (this.f4440a == null || uri == null) {
            return;
        }
        String strI = i(uri);
        String strE = e(strI);
        long j2 = j(uri);
        String str = "local_file_" + System.currentTimeMillis() + "_" + ((int) (Math.random() * 1000.0d));
        if (j2 > 1073741824) {
            Toast.makeText(this.f4440a, "文件不能超过1GB", 0).show();
            return;
        }
        b bVar = this.f4442c;
        if (bVar != null) {
            bVar.c(str, strI, j2);
        }
        String strK = k(uri, strI);
        String strI2 = AbstractC0213d.i(this.f4440a);
        String strA = AbstractC0213d.a(strI2);
        C0218i.k kVar = this.f4441b;
        if (kVar != null) {
            kVar.a(true);
        }
        new Thread(new a(strA, uri, strE, strK, j2, str, strI, strI2)).start();
    }
}

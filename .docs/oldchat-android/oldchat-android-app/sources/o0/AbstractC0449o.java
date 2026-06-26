package o0;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import com.im.oldchat.ui.AbstractC0227s;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: renamed from: o0.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0449o {

    /* JADX INFO: renamed from: o0.o$a */
    public class a extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f7125a = null;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Context f7126b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ c f7127c;

        public a(Context context, c cVar) {
            this.f7126b = context;
            this.f7127c = cVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public File doInBackground(Void... voidArr) throws Throwable {
            try {
                List listV = AbstractC0227s.v(this.f7126b);
                if (listV != null && !listV.isEmpty()) {
                    String str = "emojis_backup_" + System.currentTimeMillis() + ".zip";
                    File fileC = AbstractC0449o.c(listV, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), str);
                    if (fileC != null) {
                        return fileC;
                    }
                    File fileC2 = AbstractC0449o.c(listV, this.f7126b.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), str);
                    if (fileC2 != null) {
                        return fileC2;
                    }
                    File fileC3 = AbstractC0449o.c(listV, new File(this.f7126b.getFilesDir(), "exports"), str);
                    if (fileC3 != null) {
                        return fileC3;
                    }
                    this.f7125a = "导出失败: 无法写入存储目录";
                    return null;
                }
                this.f7125a = "没有表情包可以导出";
                return null;
            } catch (Exception e2) {
                this.f7125a = "导出失败: " + e2.getMessage();
                return null;
            }
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(File file) {
            if (this.f7127c == null) {
                return;
            }
            if (file == null || !file.exists()) {
                c cVar = this.f7127c;
                String str = this.f7125a;
                if (str == null) {
                    str = "导出失败";
                }
                cVar.a(str);
                return;
            }
            this.f7127c.b("表情包已导出到: " + file.getAbsolutePath(), file);
        }
    }

    /* JADX INFO: renamed from: o0.o$b */
    public class b extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f7128a = null;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Context f7129b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Uri f7130c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ d f7131d;

        public b(Context context, Uri uri, d dVar) {
            this.f7129b = context;
            this.f7130c = uri;
            this.f7131d = dVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Integer doInBackground(Void... voidArr) throws Throwable {
            InputStream inputStreamOpenInputStream;
            FileOutputStream fileOutputStream;
            Throwable th;
            ZipInputStream zipInputStream = null;
            try {
                try {
                    inputStreamOpenInputStream = this.f7129b.getContentResolver().openInputStream(this.f7130c);
                    try {
                        if (inputStreamOpenInputStream == null) {
                            this.f7128a = "无法读取文件";
                            if (inputStreamOpenInputStream != null) {
                                try {
                                    inputStreamOpenInputStream.close();
                                } catch (Exception unused) {
                                }
                            }
                            return 0;
                        }
                        File file = new File(this.f7129b.getFilesDir(), "emojis");
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        ZipInputStream zipInputStream2 = new ZipInputStream(new BufferedInputStream(inputStreamOpenInputStream));
                        int i2 = 0;
                        while (true) {
                            try {
                                try {
                                    ZipEntry nextEntry = zipInputStream2.getNextEntry();
                                    if (nextEntry == null) {
                                        break;
                                    }
                                    if (!nextEntry.isDirectory()) {
                                        String name = nextEntry.getName();
                                        if (name.contains("/")) {
                                            name = name.substring(name.lastIndexOf(47) + 1);
                                        }
                                        String strE = AbstractC0449o.e(name);
                                        if (strE != null) {
                                            File file2 = new File(file, "emoji_" + System.currentTimeMillis() + "_" + i2 + strE);
                                            try {
                                                fileOutputStream = new FileOutputStream(file2);
                                            } catch (Throwable th2) {
                                                fileOutputStream = null;
                                                th = th2;
                                            }
                                            try {
                                                byte[] bArr = new byte[PKIFailureInfo.certRevoked];
                                                while (true) {
                                                    int i3 = zipInputStream2.read(bArr);
                                                    if (i3 == -1) {
                                                        break;
                                                    }
                                                    fileOutputStream.write(bArr, 0, i3);
                                                }
                                                fileOutputStream.flush();
                                                boolean zEqualsIgnoreCase = ".gif".equalsIgnoreCase(strE);
                                                AbstractC0227s.c cVar = new AbstractC0227s.c();
                                                cVar.f4743a = String.valueOf(System.currentTimeMillis() + ((long) i2));
                                                cVar.f4744b = file2.getAbsolutePath();
                                                cVar.f4745c = zEqualsIgnoreCase;
                                                cVar.f4747e = "未分类";
                                                List listV = AbstractC0227s.v(this.f7129b);
                                                listV.add(cVar);
                                                AbstractC0227s.E(this.f7129b, listV);
                                                i2++;
                                                try {
                                                    fileOutputStream.close();
                                                } catch (Exception unused2) {
                                                }
                                                zipInputStream2.closeEntry();
                                            } catch (Throwable th3) {
                                                th = th3;
                                                if (fileOutputStream == null) {
                                                    throw th;
                                                }
                                                try {
                                                    fileOutputStream.close();
                                                    throw th;
                                                } catch (Exception unused3) {
                                                    throw th;
                                                }
                                            }
                                        }
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    zipInputStream = zipInputStream2;
                                    if (zipInputStream != null) {
                                        try {
                                            zipInputStream.close();
                                        } catch (Exception unused4) {
                                        }
                                    }
                                    if (inputStreamOpenInputStream != null) {
                                        try {
                                            inputStreamOpenInputStream.close();
                                        } catch (Exception unused5) {
                                        }
                                    }
                                    throw th;
                                }
                            } catch (Exception e2) {
                                e = e2;
                                zipInputStream = zipInputStream2;
                                this.f7128a = "导入失败: " + e.getMessage();
                                if (zipInputStream != null) {
                                    try {
                                        zipInputStream.close();
                                    } catch (Exception unused6) {
                                    }
                                }
                                if (inputStreamOpenInputStream != null) {
                                    try {
                                        inputStreamOpenInputStream.close();
                                    } catch (Exception unused7) {
                                    }
                                }
                                return 0;
                            }
                        }
                        Integer numValueOf = Integer.valueOf(i2);
                        try {
                            zipInputStream2.close();
                        } catch (Exception unused8) {
                        }
                        try {
                            inputStreamOpenInputStream.close();
                        } catch (Exception unused9) {
                        }
                        return numValueOf;
                    } catch (Exception e3) {
                        e = e3;
                    }
                } catch (Exception e4) {
                    e = e4;
                    inputStreamOpenInputStream = null;
                } catch (Throwable th5) {
                    th = th5;
                    inputStreamOpenInputStream = null;
                }
            } catch (Throwable th6) {
                th = th6;
            }
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Integer num) {
            if (this.f7131d == null) {
                return;
            }
            if (num.intValue() <= 0) {
                d dVar = this.f7131d;
                String str = this.f7128a;
                if (str == null) {
                    str = "导入失败";
                }
                dVar.a(str);
                return;
            }
            this.f7131d.b("成功导入 " + num + " 个表情包", num.intValue());
        }
    }

    /* JADX INFO: renamed from: o0.o$c */
    public interface c {
        void a(String str);

        void b(String str, File file);
    }

    /* JADX INFO: renamed from: o0.o$d */
    public interface d {
        void a(String str);

        void b(String str, int i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00c7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.zip.ZipOutputStream] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.io.File c(java.util.List r7, java.io.File r8, java.lang.String r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 204
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: o0.AbstractC0449o.c(java.util.List, java.io.File, java.lang.String):java.io.File");
    }

    public static void d(Context context, c cVar) {
        new a(context, cVar).execute(new Void[0]);
    }

    public static String e(String str) {
        int iLastIndexOf;
        if (str == null || str.isEmpty() || (iLastIndexOf = str.lastIndexOf(46)) < 0 || iLastIndexOf >= str.length() - 1) {
            return null;
        }
        String lowerCase = str.substring(iLastIndexOf).toLowerCase();
        if (".jpg".equals(lowerCase) || ".jpeg".equals(lowerCase) || ".png".equals(lowerCase) || ".gif".equals(lowerCase)) {
            return lowerCase;
        }
        return null;
    }

    public static void f(Context context, Uri uri, d dVar) {
        new b(context, uri, dVar).execute(new Void[0]);
    }
}

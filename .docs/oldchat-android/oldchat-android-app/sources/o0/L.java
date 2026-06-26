package o0;

import android.content.Context;
import android.util.Xml;
import java.io.File;
import java.io.FileInputStream;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public abstract class L {
    public static boolean a(File file) throws Throwable {
        FileInputStream fileInputStream = null;
        try {
            XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                xmlPullParserNewPullParser.setInput(fileInputStream2, "UTF-8");
                do {
                } while (xmlPullParserNewPullParser.next() != 1);
                fileInputStream2.close();
                return true;
            } catch (Exception unused) {
                fileInputStream = fileInputStream2;
                if (fileInputStream == null) {
                    return false;
                }
                try {
                    fileInputStream.close();
                    return false;
                } catch (Exception unused2) {
                    return false;
                }
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception unused3) {
                    }
                }
                throw th;
            }
        } catch (Exception unused4) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void b(File file) {
        try {
            file.delete();
        } catch (Exception unused) {
        }
    }

    public static void c(Context context) {
        File[] fileArrListFiles;
        String name;
        if (context == null) {
            return;
        }
        File file = new File(context.getApplicationInfo().dataDir, "shared_prefs");
        if (file.exists() && file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                if (file2 != null && file2.isFile() && (name = file2.getName()) != null && name.endsWith(".xml")) {
                    if (file2.length() == 0) {
                        b(file2);
                    } else if (!a(file2)) {
                        try {
                            file2.renameTo(new File(file2.getParent(), name + ".broken"));
                        } catch (Exception unused) {
                        }
                        b(file2);
                    }
                }
            }
        }
    }
}

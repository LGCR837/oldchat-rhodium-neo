package o0;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class P {
    public static String a(String str) {
        return ((str == null || str.trim().length() == 0) ? "oldchat_qr" : str.trim()) + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date()) + ".png";
    }

    public static boolean b() {
        int i2 = Build.VERSION.SDK_INT;
        return i2 >= 23 && i2 <= 28;
    }

    public static String c(Context context, Bitmap bitmap, String str) throws Throwable {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "OldChat");
        if (!file.exists() && !file.mkdirs()) {
            throw new IllegalStateException("mkdirs failed");
        }
        File file2 = new File(file, str);
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
            try {
                if (!bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream2)) {
                    throw new IllegalStateException("compress failed");
                }
                fileOutputStream2.flush();
                try {
                    fileOutputStream2.close();
                } catch (Exception unused) {
                }
                MediaScannerConnection.scanFile(context, new String[]{file2.getAbsolutePath()}, new String[]{"image/png"}, null);
                return file2.getAbsolutePath();
            } catch (Throwable th) {
                th = th;
                fileOutputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception unused2) {
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String d(Context context, Bitmap bitmap, String str) {
        if (context == null || bitmap == null) {
            throw new IllegalArgumentException("context or bitmap is null");
        }
        String strA = a(str);
        return Build.VERSION.SDK_INT >= 29 ? e(context, bitmap, strA) : c(context, bitmap, strA);
    }

    public static String e(Context context, Bitmap bitmap, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", str);
        contentValues.put("mime_type", "image/png");
        contentValues.put("relative_path", "Pictures/OldChat");
        Uri uriInsert = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        if (uriInsert == null) {
            throw new IllegalStateException("insert failed");
        }
        OutputStream outputStream = null;
        try {
            OutputStream outputStreamOpenOutputStream = context.getContentResolver().openOutputStream(uriInsert);
            if (outputStreamOpenOutputStream == null) {
                throw new IllegalStateException("open output failed");
            }
            if (!bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStreamOpenOutputStream)) {
                throw new IllegalStateException("compress failed");
            }
            outputStreamOpenOutputStream.flush();
            String string = uriInsert.toString();
            try {
                outputStreamOpenOutputStream.close();
            } catch (Exception unused) {
            }
            return string;
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    outputStream.close();
                } catch (Exception unused2) {
                }
            }
            throw th;
        }
    }
}

package o0;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public abstract class O {
    public static void a(InputStream inputStream) {
        if (inputStream == null) {
            return;
        }
        try {
            inputStream.close();
        } catch (Exception unused) {
        }
    }

    public static int b(BitmapFactory.Options options, int i2) {
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        int i5 = 1;
        while (true) {
            if (i3 / i5 <= i2 && i4 / i5 <= i2) {
                return Math.max(1, i5);
            }
            i5 *= 2;
        }
    }

    public static String c(Bitmap bitmap) {
        if (bitmap == null) {
            return "";
        }
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width > 0 && height > 0) {
                int[] iArr = new int[width * height];
                bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
                H.n nVarB = new H.i().b(new H.c(new L.j(new H.k(width, height, iArr))));
                return nVarB == null ? "" : e(nVarB.f());
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static Bitmap d(ContentResolver contentResolver, Uri uri, int i2) throws Throwable {
        InputStream inputStreamOpenInputStream;
        InputStream inputStream = null;
        if (contentResolver != null && uri != null) {
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                inputStreamOpenInputStream = contentResolver.openInputStream(uri);
                try {
                    BitmapFactory.decodeStream(inputStreamOpenInputStream, null, options);
                    a(inputStreamOpenInputStream);
                    BitmapFactory.Options options2 = new BitmapFactory.Options();
                    options2.inSampleSize = b(options, Math.max(128, i2));
                    options2.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    inputStreamOpenInputStream = contentResolver.openInputStream(uri);
                    Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenInputStream, null, options2);
                    a(inputStreamOpenInputStream);
                    return bitmapDecodeStream;
                } catch (Exception unused) {
                    a(inputStreamOpenInputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    inputStream = inputStreamOpenInputStream;
                    a(inputStream);
                    throw th;
                }
            } catch (Exception unused2) {
                inputStreamOpenInputStream = null;
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return null;
    }

    public static String e(String str) {
        return str == null ? "" : str.trim();
    }
}

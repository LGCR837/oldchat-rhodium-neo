package o0;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: renamed from: o0.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0452s {
    public static int a(BitmapFactory.Options options, int i2, int i3) {
        int i4 = options.outHeight;
        int i5 = options.outWidth;
        int i6 = 1;
        if (i4 > i3 || i5 > i2) {
            int i7 = i4 / 2;
            int i8 = i5 / 2;
            while (i7 / i6 >= i3 && i8 / i6 >= i2) {
                i6 *= 2;
            }
        }
        return i6;
    }

    public static byte[] b(Bitmap bitmap, int i2) {
        int i3 = 96;
        byte[] bArrC = c(bitmap, 96);
        for (int i4 = 0; i4 < 8 && bArrC.length > i2 && i3 > 82; i4++) {
            i3 -= 3;
            bArrC = c(bitmap, i3);
        }
        Bitmap bitmap2 = bitmap;
        int i5 = 0;
        while (bArrC.length > i2 && i5 < 8) {
            Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap2, Math.max(1, (int) (bitmap2.getWidth() * 0.9f)), Math.max(1, (int) (bitmap2.getHeight() * 0.9f)), true);
            if (bitmap2 != bitmap) {
                bitmap2.recycle();
            }
            int i6 = 94;
            byte[] bArrC2 = c(bitmapCreateScaledBitmap, 94);
            for (int i7 = 0; i7 < 8 && bArrC2.length > i2 && i6 > 82; i7++) {
                i6 -= 3;
                bArrC2 = c(bitmapCreateScaledBitmap, i6);
            }
            i5++;
            bitmap2 = bitmapCreateScaledBitmap;
            bArrC = bArrC2;
        }
        if (bitmap2 != bitmap) {
            bitmap2.recycle();
        }
        bitmap.recycle();
        return bArrC;
    }

    public static byte[] c(Bitmap bitmap, int i2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, i2, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] d(ContentResolver contentResolver, Uri uri, int i2, int i3) throws Exception {
        Bitmap bitmapE = e(contentResolver, uri, i2, i2);
        if (bitmapE != null) {
            return b(bitmapE, i3);
        }
        throw new Exception("decode failed");
    }

    public static Bitmap e(ContentResolver contentResolver, Uri uri, int i2, int i3) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        InputStream inputStreamOpenInputStream = contentResolver.openInputStream(uri);
        BitmapFactory.decodeStream(inputStreamOpenInputStream, null, options);
        if (inputStreamOpenInputStream != null) {
            inputStreamOpenInputStream.close();
        }
        options.inSampleSize = a(options, i2, i3);
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inDither = true;
        InputStream inputStreamOpenInputStream2 = contentResolver.openInputStream(uri);
        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenInputStream2, null, options);
        if (inputStreamOpenInputStream2 != null) {
            inputStreamOpenInputStream2.close();
        }
        return bitmapDecodeStream;
    }
}

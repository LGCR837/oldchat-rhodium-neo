package h0;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {
    public static Bitmap a(String str, int i2) {
        L.b bVarA = new b0.b().a(str, H.a.QR_CODE, i2, i2);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i2, i2, Bitmap.Config.RGB_565);
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i2; i4++) {
                bitmapCreateBitmap.setPixel(i3, i4, bVarA.d(i3, i4) ? -16777216 : -1);
            }
        }
        return bitmapCreateBitmap;
    }
}

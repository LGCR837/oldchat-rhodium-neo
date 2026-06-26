package l0;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.ImageView;
import java.lang.ref.WeakReference;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: renamed from: l0.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0400q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f6262a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static j.f f6263b;

    /* JADX INFO: renamed from: l0.q$a */
    public class a extends j.f {
        public a(int i2) {
            super(i2);
        }

        @Override // j.f
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public int g(String str, Bitmap bitmap) {
            return AbstractC0400q.d(bitmap);
        }
    }

    /* JADX INFO: renamed from: l0.q$b */
    public static class b extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference f6264a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f6265b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f6266c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final j.f f6267d;

        public b(ImageView imageView, String str, int i2, j.f fVar) {
            this.f6264a = new WeakReference(imageView);
            this.f6265b = str;
            this.f6266c = i2;
            this.f6267d = fVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Bitmap doInBackground(Void... voidArr) {
            return AbstractC0400q.c(this.f6265b, this.f6266c);
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Bitmap bitmap) {
            Object tag;
            ImageView imageView = (ImageView) this.f6264a.get();
            if (imageView == null || (tag = imageView.getTag()) == null || !this.f6265b.equals(tag) || bitmap == null) {
                return;
            }
            this.f6267d.e(this.f6265b, bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }

    public static Bitmap c(String str, int i2) {
        if (str != null && str.length() != 0) {
            if (i2 <= 0) {
                i2 = 72;
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i3 = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            int iMax = Math.max(options.outWidth, options.outHeight);
            while (i2 > 0 && iMax / i3 > i2) {
                i3 *= 2;
            }
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inSampleSize = i3;
            options2.inPreferredConfig = Bitmap.Config.RGB_565;
            try {
                return BitmapFactory.decodeFile(str, options2);
            } catch (OutOfMemoryError unused) {
            }
        }
        return null;
    }

    public static int d(Bitmap bitmap) {
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

    public static j.f e(Context context) {
        j.f fVar = f6263b;
        if (fVar != null) {
            return fVar;
        }
        synchronized (f6262a) {
            try {
                if (f6263b == null) {
                    f6263b = new a(g(context));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return f6263b;
    }

    public static void f(Context context, ImageView imageView, String str, int i2) {
        if (imageView == null) {
            return;
        }
        if (str == null || str.length() == 0) {
            imageView.setTag(null);
            imageView.setImageResource(f0.e.f4999T);
            return;
        }
        imageView.setTag(str);
        j.f fVarE = e(context);
        Bitmap bitmap = (Bitmap) fVarE.d(str);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setImageResource(f0.e.f4999T);
            new b(imageView, str, i2, fVarE).execute(new Void[0]);
        }
    }

    public static int g(Context context) {
        int memoryClass = 0;
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager != null) {
                memoryClass = activityManager.getMemoryClass();
            }
        } catch (Exception unused) {
        }
        int i2 = memoryClass > 0 ? (memoryClass * 1024) / 16 : 2048;
        int i3 = (memoryClass <= 0 || memoryClass > 128) ? PKIFailureInfo.certConfirmed : 2048;
        if (i2 > i3) {
            i2 = i3;
        }
        if (i2 < 1024) {
            return 1024;
        }
        return i2;
    }
}

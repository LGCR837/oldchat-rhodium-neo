package o0;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: renamed from: o0.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0455v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ExecutorService f7137a = Executors.newFixedThreadPool(2);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Handler f7138b = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: o0.v$a */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f7139a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f7140b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f7141c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f7142d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ ImageView f7143e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ String f7144f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ c f7145g;

        /* JADX INFO: renamed from: o0.v$a$a, reason: collision with other inner class name */
        public class RunnableC0069a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Bitmap f7146a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ String f7147b;

            public RunnableC0069a(Bitmap bitmap, String str) {
                this.f7146a = bitmap;
                this.f7147b = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (this.f7146a != null) {
                    a aVar = a.this;
                    if (aVar.f7140b.equals(aVar.f7143e.getTag())) {
                        AbstractC0456w.k(a.this.f7139a).e(a.this.f7144f, this.f7146a);
                        AbstractC0454u.c(a.this.f7144f);
                        a.this.f7143e.setImageBitmap(this.f7146a);
                    }
                }
                AbstractC0455v.k(a.this.f7145g, this.f7147b);
            }
        }

        public a(Context context, String str, int i2, int i3, ImageView imageView, String str2, c cVar) {
            this.f7139a = context;
            this.f7140b = str;
            this.f7141c = i2;
            this.f7142d = i3;
            this.f7143e = imageView;
            this.f7144f = str2;
            this.f7145g = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            String strJ;
            Bitmap bitmapH = AbstractC0456w.h(this.f7139a, "img_cache", this.f7140b, this.f7141c, this.f7142d);
            String str = this.f7140b;
            if (bitmapH == null && (strJ = A.j(str)) != null && strJ.length() > 0 && !strJ.equals(this.f7140b) && (bitmapH = AbstractC0456w.h(this.f7139a, "img_cache", strJ, this.f7141c, this.f7142d)) != null) {
                str = strJ;
            }
            AbstractC0455v.f7138b.post(new RunnableC0069a(bitmapH, str));
        }
    }

    /* JADX INFO: renamed from: o0.v$b */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f7149a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f7150b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f7151c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f7152d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ String f7153e;

        /* JADX INFO: renamed from: o0.v$b$a */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Bitmap f7154a;

            public a(Bitmap bitmap) {
                this.f7154a = bitmap;
            }

            @Override // java.lang.Runnable
            public void run() {
                AbstractC0456w.k(b.this.f7149a).e(b.this.f7153e, this.f7154a);
                AbstractC0454u.c(b.this.f7153e);
            }
        }

        public b(Context context, String str, int i2, int i3, String str2) {
            this.f7149a = context;
            this.f7150b = str;
            this.f7151c = i2;
            this.f7152d = i3;
            this.f7153e = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            String strJ;
            Bitmap bitmapH = AbstractC0456w.h(this.f7149a, "img_cache", this.f7150b, this.f7151c, this.f7152d);
            if (bitmapH == null && (strJ = A.j(this.f7150b)) != null && strJ.length() > 0 && !strJ.equals(this.f7150b)) {
                bitmapH = AbstractC0456w.h(this.f7149a, "img_cache", strJ, this.f7151c, this.f7152d);
            }
            if (bitmapH != null) {
                AbstractC0455v.f7138b.post(new a(bitmapH));
            }
        }
    }

    /* JADX INFO: renamed from: o0.v$c */
    public interface c {
        void a(String str);
    }

    public static void c(Context context) {
        if (context == null) {
            return;
        }
        r.b(new File(context.getCacheDir(), "img_cache"));
        AbstractC0454u.a();
        j.f fVarK = AbstractC0456w.k(context.getApplicationContext());
        if (fVarK != null) {
            fVarK.c();
        }
    }

    public static boolean d(Context context, String str) {
        boolean z2 = false;
        if (context != null && str != null && !str.isEmpty()) {
            String strN = n(str);
            String strA = r.a(strN);
            if (AbstractC0454u.b(strA)) {
                return true;
            }
            File fileC = r.c(context.getApplicationContext(), "img_cache", strN);
            if (fileC != null && fileC.exists()) {
                z2 = true;
            }
            if (z2) {
                AbstractC0454u.c(strA);
            }
        }
        return z2;
    }

    public static void e(ImageView imageView, String str) {
        f(imageView, str, null);
    }

    public static void f(ImageView imageView, String str, c cVar) {
        if (str == null || str.isEmpty()) {
            imageView.setTag(null);
            imageView.setImageResource(f0.e.f5018g);
            k(cVar, null);
            return;
        }
        String strN = n(str);
        String strA = r.a(strN);
        boolean zEquals = strN.equals(imageView.getTag());
        imageView.setTag(strN);
        Context context = imageView.getContext();
        Context applicationContext = context != null ? context.getApplicationContext() : null;
        j.f fVarK = AbstractC0456w.k(applicationContext);
        if (zEquals && ((Bitmap) fVarK.d(strA)) != null) {
            k(cVar, strN);
            return;
        }
        Bitmap bitmap = (Bitmap) fVarK.d(strA);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            k(cVar, strN);
            return;
        }
        int iV = AbstractC0456w.v(applicationContext);
        boolean z2 = (zEquals || applicationContext == null || !d(applicationContext, strN)) ? false : true;
        if (!zEquals && !z2) {
            imageView.setImageResource(f0.e.f5018g);
        }
        g(imageView, applicationContext, strN, strA, cVar, iV, 0);
    }

    public static void g(ImageView imageView, Context context, String str, String str2, c cVar, int i2, int i3) {
        f7137a.execute(new a(context, str, i2, i3, imageView, str2, cVar));
    }

    public static void h(ImageView imageView, String str) {
        i(imageView, str, null);
    }

    public static void i(ImageView imageView, String str, c cVar) {
        if (imageView != null) {
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        if (str == null || str.isEmpty()) {
            imageView.setTag(null);
            imageView.setImageResource(f0.e.f4999T);
            k(cVar, null);
            return;
        }
        String strN = n(str);
        String strA = r.a(strN);
        boolean zEquals = strN.equals(imageView.getTag());
        imageView.setTag(strN);
        Context context = imageView.getContext();
        Context applicationContext = context != null ? context.getApplicationContext() : null;
        Bitmap bitmap = (Bitmap) AbstractC0456w.k(applicationContext).d(strA);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            k(cVar, strN);
            return;
        }
        int iS = AbstractC0456w.s(applicationContext);
        boolean z2 = (zEquals || applicationContext == null || !d(applicationContext, strN)) ? false : true;
        if (!zEquals && !z2) {
            imageView.setImageResource(f0.e.f4999T);
        }
        g(imageView, applicationContext, strN, strA, cVar, iS, 16384);
    }

    public static void j(ImageView imageView, String str, c cVar) {
        if (str == null || str.isEmpty()) {
            imageView.setTag(null);
            imageView.setImageResource(f0.e.f5018g);
            k(cVar, null);
            return;
        }
        String strN = n(str);
        String strA = r.a(strN);
        boolean zEquals = strN.equals(imageView.getTag());
        imageView.setTag(strN);
        Context context = imageView.getContext();
        Context applicationContext = context != null ? context.getApplicationContext() : null;
        Bitmap bitmap = (Bitmap) AbstractC0456w.k(applicationContext).d(strA);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            k(cVar, strN);
            return;
        }
        boolean z2 = (zEquals || applicationContext == null || !d(applicationContext, strN)) ? false : true;
        if (!zEquals && !z2) {
            imageView.setImageResource(f0.e.f5018g);
        }
        g(imageView, applicationContext, strN, strA, cVar, AbstractC0456w.v(applicationContext), 0);
    }

    public static void k(c cVar, String str) {
        if (cVar != null) {
            cVar.a(str);
        }
    }

    public static void l(Context context, String str) {
        if (context == null || str == null || str.isEmpty()) {
            return;
        }
        String strN = n(str);
        String strA = r.a(strN);
        if (d(context, strN)) {
            return;
        }
        m(context.getApplicationContext(), strN, strA, AbstractC0456w.s(context), 16384);
    }

    public static void m(Context context, String str, String str2, int i2, int i3) {
        f7137a.execute(new b(context, str, i2, i3, str2));
    }

    public static String n(String str) {
        return A.g(str);
    }
}

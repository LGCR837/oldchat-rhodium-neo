package l0;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;
import java.io.File;
import o.c;

/* JADX INFO: renamed from: l0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0370b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Activity f6011a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final View f6012b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f6013c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f6014d;

    /* JADX INFO: renamed from: l0.b$a */
    public class a implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f6015a;

        public a(int i2) {
            this.f6015a = i2;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            if (i2 == 0) {
                C0370b.this.i(this.f6015a);
            } else {
                C0370b.this.f();
            }
        }
    }

    /* JADX INFO: renamed from: l0.b$b, reason: collision with other inner class name */
    public class AsyncTaskC0052b extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6017a;

        public AsyncTaskC0052b(String str) {
            this.f6017a = str;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Bitmap doInBackground(Void... voidArr) {
            int iMax;
            try {
                int iJ = C0370b.this.j();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(this.f6017a, options);
                int i2 = options.outWidth;
                if (i2 > iJ || options.outHeight > iJ) {
                    iMax = Math.max(i2 / iJ, options.outHeight / iJ);
                    if (iMax < 1) {
                        iMax = 1;
                    }
                } else {
                    iMax = 1;
                }
                options.inJustDecodeBounds = false;
                options.inSampleSize = iMax;
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                options.inDither = true;
                return BitmapFactory.decodeFile(this.f6017a, options);
            } catch (Throwable unused) {
                return null;
            }
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Bitmap bitmap) {
            if (bitmap == null || C0370b.this.f6012b == null) {
                return;
            }
            C0370b c0370b = C0370b.this;
            c0370b.k(c0370b.f6012b, new BitmapDrawable(C0370b.this.f6011a.getResources(), bitmap));
            C0370b.this.f6012b.setVisibility(0);
        }
    }

    public C0370b(Activity activity, View view, String str, boolean z2) {
        this.f6011a = activity;
        this.f6012b = view;
        this.f6013c = str;
        this.f6014d = z2;
    }

    public void e() {
        String str;
        if (this.f6012b == null || (str = this.f6013c) == null) {
            return;
        }
        String strI = i0.a.i(this.f6011a, str, this.f6014d);
        if (strI != null && strI.length() > 0 && new File(strI).exists()) {
            new AsyncTaskC0052b(strI).execute(new Void[0]);
        } else {
            k(this.f6012b, null);
            this.f6012b.setVisibility(8);
        }
    }

    public void f() {
        String str;
        Activity activity = this.f6011a;
        if (activity == null || (str = this.f6013c) == null) {
            return;
        }
        i0.a.e(activity, str, this.f6014d);
        e();
        Toast.makeText(this.f6011a, f0.j.f5213f, 0).show();
    }

    public final int g() {
        try {
            ActivityManager activityManager = (ActivityManager) this.f6011a.getSystemService("activity");
            if (activityManager != null) {
                return activityManager.getMemoryClass();
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    public void h(Uri uri) {
        String str;
        Activity activity = this.f6011a;
        if (activity == null || uri == null || (str = this.f6013c) == null) {
            return;
        }
        if (!i0.a.p(activity, str, this.f6014d, uri)) {
            Toast.makeText(this.f6011a, f0.j.f5195S, 0).show();
        } else {
            e();
            Toast.makeText(this.f6011a, f0.j.f5221j, 0).show();
        }
    }

    public void i(int i2) {
        if (this.f6011a == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        intent.addCategory("android.intent.category.OPENABLE");
        try {
            Activity activity = this.f6011a;
            activity.startActivityForResult(Intent.createChooser(intent, activity.getString(f0.j.f5219i)), i2);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(this.f6011a, f0.j.f5192P, 0).show();
        }
    }

    public final int j() {
        try {
            DisplayMetrics displayMetrics = this.f6011a.getResources().getDisplayMetrics();
            int iMax = Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels);
            int iG = g();
            int i2 = (iG <= 0 || iG > 128) ? 1024 : 720;
            int iMin = Math.min(iMax, i2);
            return iMin <= 0 ? i2 : iMin;
        } catch (Exception unused) {
            return 1024;
        }
    }

    public final void k(View view, BitmapDrawable bitmapDrawable) {
        if (view == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(bitmapDrawable);
        } else {
            view.setBackgroundDrawable(bitmapDrawable);
        }
    }

    public void l(int i2) {
        String str;
        Activity activity = this.f6011a;
        if (activity == null || (str = this.f6013c) == null) {
            return;
        }
        new c.a(this.f6011a, f0.k.f5244a).s(f0.j.f5227m).g(i0.a.k(activity, str, this.f6014d) ? new String[]{this.f6011a.getString(f0.j.f5219i), this.f6011a.getString(f0.j.f5211e)} : new String[]{this.f6011a.getString(f0.j.f5219i)}, new a(i2)).v();
    }
}

package o0;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/* JADX INFO: renamed from: o0.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0453t {
    public static int a(BitmapFactory.Options options, int i2) {
        int iMax = Math.max(options.outWidth, options.outHeight);
        int i3 = 1;
        while (i2 > 0 && iMax / i3 > i2) {
            i3 *= 2;
        }
        return i3;
    }

    public static boolean b(Context context, Uri uri, File file, int i2, int i3, boolean z2) throws Throwable {
        Bitmap bitmapD;
        if (context == null || uri == null || file == null || (bitmapD = d(context, uri, i2, z2)) == null) {
            return false;
        }
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    boolean zCompress = bitmapD.compress(z2 ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG, Math.max(40, Math.min(i3, 100)), fileOutputStream2);
                    fileOutputStream2.flush();
                    bitmapD.recycle();
                    try {
                        fileOutputStream2.close();
                    } catch (Exception unused) {
                    }
                    return zCompress;
                } catch (Exception unused2) {
                    fileOutputStream = fileOutputStream2;
                    bitmapD.recycle();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception unused3) {
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception unused4) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception unused5) {
        }
    }

    public static boolean c(Context context, Uri uri, File file, int i2, int i3, boolean z2, long j2) {
        int iMax;
        int iMax2;
        if (!b(context, uri, file, i2, i3, z2)) {
            return false;
        }
        if (j2 <= 0) {
            return true;
        }
        long length = file.length();
        if (length <= j2) {
            return true;
        }
        int iMax3 = Math.max(40, Math.min(i3, 100));
        int i4 = z2 ? 64 : 96;
        int i5 = iMax3;
        int i6 = 0;
        long length2 = length;
        int i7 = i2;
        while (i6 < 8 && length2 > j2) {
            if (!z2 && i5 > 40) {
                iMax = i7;
                iMax2 = Math.max(40, i5 - 15);
            } else {
                if (i7 <= i4) {
                    break;
                }
                iMax = Math.max(i4, (int) (i7 * 0.85f));
                iMax2 = i5;
            }
            if (!b(context, uri, file, iMax, iMax2, z2)) {
                break;
            }
            length2 = file.length();
            i6++;
            i7 = iMax;
            i5 = iMax2;
        }
        return file.length() > 0;
    }

    public static Bitmap d(Context context, Uri uri, int i2, boolean z2) throws Throwable {
        InputStream inputStreamF;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        e(context, uri, options);
        int iA = a(options, i2);
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inSampleSize = iA;
        options2.inPreferredConfig = z2 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        InputStream inputStream = null;
        try {
            inputStreamF = f(context, uri);
            if (inputStreamF == null) {
                if (inputStreamF != null) {
                    try {
                        inputStreamF.close();
                    } catch (Exception unused) {
                    }
                }
                return null;
            }
            try {
                Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamF, null, options2);
                if (bitmapDecodeStream == null) {
                    try {
                        inputStreamF.close();
                    } catch (Exception unused2) {
                    }
                    return null;
                }
                int width = bitmapDecodeStream.getWidth();
                int height = bitmapDecodeStream.getHeight();
                int iMax = Math.max(width, height);
                if (i2 <= 0 || iMax <= i2) {
                    try {
                        inputStreamF.close();
                    } catch (Exception unused3) {
                    }
                    return bitmapDecodeStream;
                }
                float f2 = i2 / iMax;
                Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapDecodeStream, Math.round(width * f2), Math.round(height * f2), true);
                if (bitmapCreateScaledBitmap != bitmapDecodeStream) {
                    bitmapDecodeStream.recycle();
                }
                try {
                    inputStreamF.close();
                } catch (Exception unused4) {
                }
                return bitmapCreateScaledBitmap;
            } catch (Exception unused5) {
                if (inputStreamF != null) {
                    try {
                        inputStreamF.close();
                    } catch (Exception unused6) {
                    }
                }
                return null;
            } catch (Throwable th) {
                th = th;
                inputStream = inputStreamF;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception unused7) {
                    }
                }
                throw th;
            }
        } catch (Exception unused8) {
            inputStreamF = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void e(Context context, Uri uri, BitmapFactory.Options options) throws Throwable {
        InputStream inputStreamF;
        InputStream inputStream = null;
        try {
            try {
                inputStreamF = f(context, uri);
                if (inputStreamF != null) {
                    try {
                        BitmapFactory.decodeStream(inputStreamF, null, options);
                    } catch (Exception unused) {
                        inputStream = inputStreamF;
                        if (inputStream == null) {
                            return;
                        } else {
                            inputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        inputStream = inputStreamF;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception unused2) {
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception unused3) {
            } catch (Throwable th2) {
                th = th2;
            }
            if (inputStreamF != null) {
                inputStreamF.close();
            }
        } catch (Exception unused4) {
        }
    }

    public static InputStream f(Context context, Uri uri) {
        try {
            return "file".equalsIgnoreCase(uri.getScheme()) ? new BufferedInputStream(new FileInputStream(new File(uri.getPath()))) : new BufferedInputStream(context.getContentResolver().openInputStream(uri));
        } catch (Exception unused) {
            return null;
        }
    }
}

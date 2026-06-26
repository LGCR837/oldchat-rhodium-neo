package o0;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: classes.dex */
public abstract class D0 {
    /* JADX WARN: Removed duplicated region for block: B:21:0x0033 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.io.File a(android.content.Context r6, java.io.File r7, int r8, int r9, long r10) {
        /*
            r0 = 0
            if (r6 == 0) goto Lbf
            if (r7 == 0) goto Lbf
            boolean r1 = r7.exists()
            if (r1 != 0) goto Ld
            goto Lbf
        Ld:
            android.media.MediaMetadataRetriever r1 = new android.media.MediaMetadataRetriever
            r1.<init>()
            java.lang.String r7 = r7.getAbsolutePath()     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L2b
            l0.AbstractC0369a0.a(r1, r7)     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L2b
            r2 = 1000000(0xf4240, double:4.940656E-318)
            android.graphics.Bitmap r7 = o0.C0.a(r1, r2)     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L2b
            l0.AbstractC0373c0.a(r1)     // Catch: java.lang.Exception -> L24
            goto L31
        L24:
            goto L31
        L26:
            r6 = move-exception
            l0.AbstractC0373c0.a(r1)     // Catch: java.lang.Exception -> L2a
        L2a:
            throw r6
        L2b:
            l0.AbstractC0373c0.a(r1)     // Catch: java.lang.Exception -> L2f
            goto L30
        L2f:
        L30:
            r7 = r0
        L31:
            if (r7 != 0) goto L34
            return r0
        L34:
            int r1 = r7.getWidth()
            int r2 = r7.getHeight()
            int r3 = java.lang.Math.max(r1, r2)
            if (r8 <= 0) goto L5b
            if (r3 <= r8) goto L5b
            float r8 = (float) r8
            float r3 = (float) r3
            float r8 = r8 / r3
            float r1 = (float) r1
            float r1 = r1 * r8
            int r1 = java.lang.Math.round(r1)
            float r2 = (float) r2
            float r2 = r2 * r8
            int r8 = java.lang.Math.round(r2)
            r2 = 1
            android.graphics.Bitmap r8 = android.graphics.Bitmap.createScaledBitmap(r7, r1, r8, r2)
            goto L5c
        L5b:
            r8 = r7
        L5c:
            if (r8 == r7) goto L61
            r7.recycle()
        L61:
            java.io.File r7 = new java.io.File
            java.io.File r6 = r6.getCacheDir()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "video_thumb_"
            r1.append(r2)
            long r2 = java.lang.System.currentTimeMillis()
            r1.append(r2)
            java.lang.String r2 = ".jpg"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r7.<init>(r6, r1)
            r6 = 100
            int r6 = java.lang.Math.min(r9, r6)
            r9 = 40
            int r6 = java.lang.Math.max(r9, r6)
            boolean r1 = f(r8, r7, r6)
            if (r1 == 0) goto Lb7
            r2 = 0
            int r4 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r4 <= 0) goto Lb7
            r2 = 0
        L9d:
            r3 = 6
            if (r2 >= r3) goto Lb7
            long r3 = r7.length()
            int r5 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r5 <= 0) goto Lb7
            if (r6 <= r9) goto Lb7
            int r6 = r6 + (-10)
            int r6 = java.lang.Math.max(r9, r6)
            boolean r1 = f(r8, r7, r6)
            int r2 = r2 + 1
            goto L9d
        Lb7:
            if (r8 == 0) goto Lbc
            r8.recycle()
        Lbc:
            if (r1 == 0) goto Lbf
            r0 = r7
        Lbf:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: o0.D0.a(android.content.Context, java.io.File, int, int, long):java.io.File");
    }

    public static File b(Context context, Uri uri, String str) throws Throwable {
        FileOutputStream fileOutputStream;
        Throwable th;
        InputStream inputStreamOpenInputStream;
        File file = new File(context.getCacheDir(), "video_cache");
        if (!file.exists()) {
            file.mkdirs();
        }
        if (str == null || str.isEmpty()) {
            str = ".mp4";
        }
        File file2 = new File(file, "video_" + System.currentTimeMillis() + str);
        try {
            inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
            if (inputStreamOpenInputStream == null) {
                if (inputStreamOpenInputStream != null) {
                    try {
                        inputStreamOpenInputStream.close();
                    } catch (Exception unused) {
                    }
                }
                return null;
            }
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (Exception unused2) {
                fileOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
            }
            try {
                byte[] bArr = new byte[PKIFailureInfo.certRevoked];
                while (true) {
                    int i2 = inputStreamOpenInputStream.read(bArr);
                    if (i2 == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, i2);
                }
                fileOutputStream.flush();
                try {
                    inputStreamOpenInputStream.close();
                } catch (Exception unused3) {
                }
                try {
                    fileOutputStream.close();
                } catch (Exception unused4) {
                }
                return file2;
            } catch (Exception unused5) {
                if (inputStreamOpenInputStream != null) {
                    try {
                        inputStreamOpenInputStream.close();
                    } catch (Exception unused6) {
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception unused7) {
                    }
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                if (inputStreamOpenInputStream != null) {
                    try {
                        inputStreamOpenInputStream.close();
                    } catch (Exception unused8) {
                    }
                }
                if (fileOutputStream == null) {
                    throw th;
                }
                try {
                    fileOutputStream.close();
                    throw th;
                } catch (Exception unused9) {
                    throw th;
                }
            }
        } catch (Exception unused10) {
            inputStreamOpenInputStream = null;
            fileOutputStream = null;
        } catch (Throwable th4) {
            fileOutputStream = null;
            th = th4;
            inputStreamOpenInputStream = null;
        }
    }

    public static long c(Context context, File file) {
        if (context != null && file != null && file.exists()) {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            try {
                mediaMetadataRetriever.setDataSource(file.getAbsolutePath());
                String strExtractMetadata = mediaMetadataRetriever.extractMetadata(9);
                if (strExtractMetadata != null && strExtractMetadata.length() > 0) {
                    long j2 = Long.parseLong(strExtractMetadata);
                    try {
                        mediaMetadataRetriever.release();
                    } catch (Exception unused) {
                    }
                    return j2;
                }
            } catch (Exception unused2) {
            } catch (Throwable th) {
                try {
                    mediaMetadataRetriever.release();
                } catch (Exception unused3) {
                }
                throw th;
            }
            try {
                mediaMetadataRetriever.release();
            } catch (Exception unused4) {
            }
        }
        return 0L;
    }

    public static File d(Context context, Uri uri, long j2, int i2, String str) {
        String strE;
        File fileB;
        if (context == null || uri == null || (fileB = b(context, uri, (strE = e(str)))) == null || !fileB.exists()) {
            return null;
        }
        if (j2 > 0 && fileB.length() > j2 && Build.VERSION.SDK_INT >= 18 && ".mp4".equals(strE)) {
            File file = new File(context.getCacheDir(), "video_trim_" + System.currentTimeMillis() + ".mp4");
            if (B0.a(fileB, file, 30000000L) && file.exists() && file.length() > 0) {
                if (fileB.length() <= 0 || file.length() < fileB.length()) {
                    fileB.delete();
                    return file;
                }
                file.delete();
                return fileB;
            }
            file.delete();
        }
        return fileB;
    }

    public static String e(String str) {
        if (str == null) {
            return ".mp4";
        }
        String lowerCase = str.toLowerCase();
        if (lowerCase.contains("3gpp") || lowerCase.contains("3gp")) {
            return ".3gp";
        }
        if (!lowerCase.contains("mp4")) {
            lowerCase.contains("m4v");
        }
        return ".mp4";
    }

    public static boolean f(Bitmap bitmap, File file, int i2) {
        if (bitmap != null && file != null) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                boolean zCompress = bitmap.compress(Bitmap.CompressFormat.JPEG, i2, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                return zCompress;
            } catch (Exception unused) {
            }
        }
        return false;
    }
}

package o0;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import com.im.oldchat.ui.ChatActivity;
import com.im.oldchat.ui.GroupChatActivity;
import com.im.oldchat.ui.NotificationChatActivity;
import j0.j;
import j0.r;
import java.util.Iterator;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: classes.dex */
public abstract class K {

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f7020a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Context f7021b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f7022c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f7023d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ String f7024e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ PendingIntent f7025f;

        public a(String str, Context context, int i2, String str2, String str3, PendingIntent pendingIntent) {
            this.f7020a = str;
            this.f7021b = context;
            this.f7022c = i2;
            this.f7023d = str2;
            this.f7024e = str3;
            this.f7025f = pendingIntent;
        }

        @Override // java.lang.Runnable
        public void run() {
            NotificationManager notificationManager;
            Bitmap bitmapE = K.e(this.f7020a, 512);
            if (bitmapE == null || (notificationManager = (NotificationManager) this.f7021b.getSystemService("notification")) == null) {
                return;
            }
            K.g(notificationManager);
            try {
                try {
                    int i2 = this.f7022c;
                    Context context = this.f7021b;
                    int i3 = f0.e.f5004Y;
                    String str = this.f7023d;
                    if (str == null) {
                        str = "";
                    }
                    String str2 = this.f7024e;
                    if (str2 == null) {
                        str2 = "";
                    }
                    notificationManager.notify(i2, J.a(context, "oldchat_messages", i3, str, str2, bitmapE, this.f7025f));
                } catch (Throwable unused) {
                    int i4 = this.f7022c;
                    Context context2 = this.f7021b;
                    int i5 = f0.e.f4999T;
                    String str3 = this.f7023d;
                    if (str3 == null) {
                        str3 = "";
                    }
                    String str4 = this.f7024e;
                    if (str4 == null) {
                        str4 = "";
                    }
                    notificationManager.notify(i4, J.a(context2, "oldchat_messages", i5, str3, str4, bitmapE, this.f7025f));
                }
            } catch (Throwable unused2) {
            }
        }
    }

    public static int c(BitmapFactory.Options options, int i2) {
        if (options != null && i2 > 0) {
            int i3 = options.outWidth;
            int i4 = options.outHeight;
            if (i3 > 0 && i4 > 0) {
                int i5 = 1;
                do {
                    if (i3 / i5 <= i2 && i4 / i5 <= i2) {
                        return Math.max(1, i5);
                    }
                    i5 *= 2;
                } while (i5 > 0);
            }
        }
        return 1;
    }

    public static Bitmap d(byte[] bArr, int i2) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        if (i2 <= 0) {
            i2 = 512;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        int iC = c(options, i2);
        Bitmap bitmapDecodeByteArray = null;
        for (int i3 = 0; i3 < 5; i3++) {
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inSampleSize = Math.max(1, iC);
            options2.inPreferredConfig = Bitmap.Config.RGB_565;
            options2.inDither = true;
            if (Build.VERSION.SDK_INT < 21) {
                options2.inPurgeable = true;
                options2.inInputShareable = true;
            }
            try {
                bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options2);
            } catch (OutOfMemoryError unused) {
                bitmapDecodeByteArray = null;
            }
            if (bitmapDecodeByteArray != null) {
                break;
            }
            iC *= 2;
        }
        if (bitmapDecodeByteArray == null) {
            return null;
        }
        int width = bitmapDecodeByteArray.getWidth();
        int height = bitmapDecodeByteArray.getHeight();
        int iMax = Math.max(width, height);
        if (i2 <= 0 || iMax <= i2) {
            return bitmapDecodeByteArray;
        }
        float f2 = i2 / iMax;
        try {
            Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapDecodeByteArray, Math.max(1, Math.round(width * f2)), Math.max(1, Math.round(height * f2)), true);
            if (bitmapCreateScaledBitmap == bitmapDecodeByteArray) {
                return bitmapDecodeByteArray;
            }
            bitmapDecodeByteArray.recycle();
            return bitmapCreateScaledBitmap;
        } catch (OutOfMemoryError unused2) {
            return bitmapDecodeByteArray;
        }
    }

    public static Bitmap e(String str, int i2) {
        byte[] bArrF;
        if (str == null || str.isEmpty() || (bArrF = f(str, PKIFailureInfo.badCertTemplate)) == null || bArrF.length == 0) {
            return null;
        }
        return d(bArrF, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0076, code lost:
    
        r9 = r3.toByteArray();
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x007a, code lost:
    
        r2.close();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] f(java.lang.String r8, int r9) {
        /*
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch: java.lang.Throwable -> L88
            r1.<init>(r8)     // Catch: java.lang.Throwable -> L88
            java.net.URLConnection r8 = r1.openConnection()     // Catch: java.lang.Throwable -> L88
            java.net.HttpURLConnection r8 = (java.net.HttpURLConnection) r8     // Catch: java.lang.Throwable -> L88
            r1 = 8000(0x1f40, float:1.121E-41)
            r8.setConnectTimeout(r1)     // Catch: java.lang.Throwable -> L84
            r1 = 10000(0x2710, float:1.4013E-41)
            r8.setReadTimeout(r1)     // Catch: java.lang.Throwable -> L84
            r1 = 1
            r8.setInstanceFollowRedirects(r1)     // Catch: java.lang.Throwable -> L84
            r8.setUseCaches(r1)     // Catch: java.lang.Throwable -> L84
            java.lang.String r1 = "Accept-Encoding"
            java.lang.String r2 = "gzip"
            r8.setRequestProperty(r1, r2)     // Catch: java.lang.Throwable -> L84
            r8.connect()     // Catch: java.lang.Throwable -> L84
            int r1 = r8.getResponseCode()     // Catch: java.lang.Throwable -> L84
            r2 = 200(0xc8, float:2.8E-43)
            if (r1 == r2) goto L33
            r8.disconnect()     // Catch: java.lang.Exception -> L32
        L32:
            return r0
        L33:
            int r1 = r8.getContentLength()     // Catch: java.lang.Throwable -> L84
            if (r9 <= 0) goto L3f
            if (r1 <= r9) goto L3f
            r8.disconnect()     // Catch: java.lang.Exception -> L3e
        L3e:
            return r0
        L3f:
            java.io.InputStream r2 = r8.getInputStream()     // Catch: java.lang.Throwable -> L84
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L4e
            r4 = 8192(0x2000, float:1.148E-41)
            if (r1 <= 0) goto L51
            int r1 = java.lang.Math.min(r1, r9)     // Catch: java.lang.Throwable -> L4e
            goto L53
        L4e:
            r3 = r0
            goto L8c
        L51:
            r1 = 8192(0x2000, float:1.148E-41)
        L53:
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L4e
            byte[] r1 = new byte[r4]     // Catch: java.lang.Throwable -> L74
            r4 = 0
            r5 = 0
        L5a:
            int r6 = r2.read(r1)     // Catch: java.lang.Throwable -> L74
            r7 = -1
            if (r6 == r7) goto L76
            int r5 = r5 + r6
            if (r9 <= 0) goto L70
            if (r5 <= r9) goto L70
            r2.close()     // Catch: java.lang.Exception -> L69
        L69:
            r3.close()     // Catch: java.lang.Exception -> L6c
        L6c:
            r8.disconnect()     // Catch: java.lang.Exception -> L6f
        L6f:
            return r0
        L70:
            r3.write(r1, r4, r6)     // Catch: java.lang.Throwable -> L74
            goto L5a
        L74:
            goto L8c
        L76:
            byte[] r9 = r3.toByteArray()     // Catch: java.lang.Throwable -> L74
            r2.close()     // Catch: java.lang.Exception -> L7d
        L7d:
            r3.close()     // Catch: java.lang.Exception -> L80
        L80:
            r8.disconnect()     // Catch: java.lang.Exception -> L83
        L83:
            return r9
        L84:
            r2 = r0
        L86:
            r3 = r2
            goto L8c
        L88:
            r8 = r0
            r2 = r8
            goto L86
        L8c:
            if (r2 == 0) goto L93
            r2.close()     // Catch: java.lang.Exception -> L92
            goto L93
        L92:
        L93:
            if (r3 == 0) goto L9a
            r3.close()     // Catch: java.lang.Exception -> L99
            goto L9a
        L99:
        L9a:
            if (r8 == 0) goto L9f
            r8.disconnect()     // Catch: java.lang.Exception -> L9f
        L9f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: o0.K.f(java.lang.String, int):byte[]");
    }

    public static void g(NotificationManager notificationManager) {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        H.a(notificationManager, "oldchat_messages", "消息通知", 3);
    }

    public static int h(String str) {
        return Math.abs(str.hashCode() % 10000);
    }

    public static boolean i(Context context) {
        return V.j(context);
    }

    public static void j(Context context, String str, String str2, PendingIntent pendingIntent, int i2, String str3) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (notificationManager == null) {
            return;
        }
        g(notificationManager);
        try {
            try {
                notificationManager.notify(i2, J.b(context, "oldchat_messages", f0.e.f5004Y, str == null ? "" : str, str2 == null ? "" : str2, pendingIntent));
            } catch (Throwable unused) {
                notificationManager.notify(i2, J.b(context, "oldchat_messages", f0.e.f4999T, str == null ? "" : str, str2 == null ? "" : str2, pendingIntent));
            }
            if (str3 == null || str3.isEmpty()) {
                return;
            }
            new Thread(new a(A.g(str3), context.getApplicationContext(), i2, str, str2, pendingIntent), "notify-image").start();
        } catch (Throwable unused2) {
        }
    }

    public static void k(Context context, String str, String str2, String str3) {
        String str4;
        String str5;
        String strB;
        if (context == null || str == null || str.isEmpty() || !i(context) || i0.g.b(context, str, false)) {
            return;
        }
        Iterator it = j0.r.d(context).iterator();
        while (true) {
            if (!it.hasNext()) {
                str4 = null;
                str5 = str;
                break;
            } else {
                r.b bVar = (r.b) it.next();
                if (str.equals(bVar.f5604b)) {
                    String str6 = bVar.f5605c;
                    str5 = (str6 == null || str6.isEmpty()) ? str : bVar.f5605c;
                    str4 = bVar.f5606d;
                }
            }
        }
        String str7 = ((str5 != null && !str5.isEmpty() && !str5.equals(str)) || (strB = j0.x.b(context, str)) == null || strB.isEmpty()) ? str5 : strB;
        Intent intent = new Intent(context, (Class<?>) ChatActivity.class);
        intent.putExtra("friend_uid", str);
        intent.putExtra("friend_name", str7);
        intent.putExtra("friend_avatar", str4);
        intent.addFlags(335544320);
        j(context, str7, str2, PendingIntent.getActivity(context, h(str) + 10000, intent, n()), h(str) + 10000, str3);
    }

    public static void l(Context context, String str, String str2, String str3, String str4) {
        String str5;
        int i2;
        String str6;
        if (context == null || str == null || str.isEmpty() || !i(context) || i0.g.b(context, str, true)) {
            return;
        }
        Iterator it = j0.j.c(context).iterator();
        while (true) {
            if (!it.hasNext()) {
                str5 = null;
                i2 = 0;
                str6 = str;
                break;
            } else {
                j.b bVar = (j.b) it.next();
                if (str.equals(bVar.f5541a)) {
                    String str7 = bVar.f5542b;
                    String str8 = (str7 == null || str7.isEmpty()) ? str : bVar.f5542b;
                    str5 = bVar.f5543c;
                    i2 = bVar.f5547g;
                    str6 = str8;
                }
            }
        }
        if (str2 != null && !str2.isEmpty()) {
            String strB = j0.x.b(context, str2);
            if (strB != null && !strB.isEmpty()) {
                str2 = strB;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(": ");
            if (str3 == null) {
                str3 = "";
            }
            sb.append(str3);
            str3 = sb.toString();
        }
        Intent intent = new Intent(context, (Class<?>) GroupChatActivity.class);
        intent.putExtra("group_id", str);
        intent.putExtra("group_name", str6);
        intent.putExtra("group_avatar", str5);
        intent.putExtra("group_role", i2);
        intent.addFlags(335544320);
        j(context, str6, str3, PendingIntent.getActivity(context, h(str) + 20000, intent, n()), h(str) + 20000, str4);
    }

    public static void m(Context context, String str, String str2, String str3) {
        if (context == null || !i(context) || i0.g.b(context, "SYSTEM", false)) {
            return;
        }
        if (str2 == null || str2.length() == 0) {
            str2 = "系统通知";
        }
        String str4 = str2;
        String str5 = str3 == null ? "" : str3;
        if (str == null) {
            str = "";
        }
        Intent intent = new Intent(context, (Class<?>) NotificationChatActivity.class);
        intent.addFlags(335544320);
        PendingIntent activity = PendingIntent.getActivity(context, h(str.length() == 0 ? "system" : str) + 30000, intent, n());
        if (str.length() == 0) {
            str = "system";
        }
        j(context, str4, str5, activity, h(str) + 30000, null);
    }

    public static int n() {
        return Build.VERSION.SDK_INT >= 23 ? 201326592 : 134217728;
    }
}

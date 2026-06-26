package o0;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import c.AbstractC0157o0;

/* JADX INFO: loaded from: classes.dex */
public abstract class J {

    public static class a {
        public static Notification a(Context context, String str, int i2, String str2, String str3, Bitmap bitmap, PendingIntent pendingIntent) {
            Notification.Builder smallIcon = new Notification.Builder(context, str).setSmallIcon(i2);
            if (str2 == null) {
                str2 = "";
            }
            return smallIcon.setContentTitle(str2).setContentText(str3 != null ? str3 : "").setStyle(new Notification.BigPictureStyle().bigPicture(bitmap).setSummaryText(str3)).setAutoCancel(true).setContentIntent(pendingIntent).build();
        }

        public static Notification b(Context context, String str, int i2, String str2, String str3, PendingIntent pendingIntent) {
            Notification.Builder smallIcon = new Notification.Builder(context, str).setSmallIcon(i2);
            if (str2 == null) {
                str2 = "";
            }
            Notification.Builder contentText = smallIcon.setContentTitle(str2).setContentText(str3 == null ? "" : str3);
            Notification.BigTextStyle bigTextStyle = new Notification.BigTextStyle();
            if (str3 == null) {
                str3 = "";
            }
            return contentText.setStyle(bigTextStyle.bigText(str3)).setAutoCancel(true).setContentIntent(pendingIntent).build();
        }

        public static Notification c(Context context, String str, int i2, String str2, String str3, boolean z2, PendingIntent pendingIntent) {
            Notification.Builder smallIcon = new Notification.Builder(context, str).setSmallIcon(i2);
            if (str2 == null) {
                str2 = "";
            }
            Notification.Builder contentTitle = smallIcon.setContentTitle(str2);
            if (str3 == null) {
                str3 = "";
            }
            Notification.Builder ongoing = contentTitle.setContentText(str3).setOngoing(z2);
            if (pendingIntent != null) {
                ongoing.setContentIntent(pendingIntent);
            }
            return ongoing.build();
        }

        public static Notification d(Context context, String str, int i2, String str2, String str3, boolean z2, int i3, int i4, boolean z3, int i5, PendingIntent pendingIntent) {
            Notification.Builder smallIcon = new Notification.Builder(context, str).setSmallIcon(i2);
            if (str2 == null) {
                str2 = "";
            }
            Notification.Builder contentTitle = smallIcon.setContentTitle(str2);
            if (str3 == null) {
                str3 = "";
            }
            Notification.Builder priority = contentTitle.setContentText(str3).setOngoing(true).setOnlyAlertOnce(z3).setPriority(i5);
            if (pendingIntent != null) {
                priority.setContentIntent(pendingIntent);
            }
            if (z2) {
                priority.setProgress(0, 0, true);
            } else {
                priority.setProgress(i3, i4, false);
            }
            return priority.build();
        }
    }

    public static Notification a(Context context, String str, int i2, String str2, String str3, Bitmap bitmap, PendingIntent pendingIntent) {
        if (bitmap == null) {
            return b(context, str, i2, str2, str3, pendingIntent);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            return a.a(context, str, i2, str2, str3, bitmap, pendingIntent);
        }
        AbstractC0157o0.d dVarN = new AbstractC0157o0.d(context).n(i2);
        if (str2 == null) {
            str2 = "";
        }
        return dVarN.h(str2).g(str3 != null ? str3 : "").o(new AbstractC0157o0.b().c(bitmap).d(str3)).e(true).f(pendingIntent).b();
    }

    public static Notification b(Context context, String str, int i2, String str2, String str3, PendingIntent pendingIntent) {
        if (Build.VERSION.SDK_INT >= 26) {
            return a.b(context, str, i2, str2, str3, pendingIntent);
        }
        AbstractC0157o0.d dVarN = new AbstractC0157o0.d(context).n(i2);
        if (str2 == null) {
            str2 = "";
        }
        AbstractC0157o0.d dVarG = dVarN.h(str2).g(str3 == null ? "" : str3);
        AbstractC0157o0.c cVar = new AbstractC0157o0.c();
        if (str3 == null) {
            str3 = "";
        }
        return dVarG.o(cVar.c(str3)).e(true).f(pendingIntent).b();
    }

    public static Notification c(Context context, String str, int i2, String str2, String str3, boolean z2, PendingIntent pendingIntent) {
        if (Build.VERSION.SDK_INT >= 26) {
            return a.c(context, str, i2, str2, str3, z2, pendingIntent);
        }
        AbstractC0157o0.d dVarN = new AbstractC0157o0.d(context).n(i2);
        if (str2 == null) {
            str2 = "";
        }
        AbstractC0157o0.d dVarH = dVarN.h(str2);
        if (str3 == null) {
            str3 = "";
        }
        AbstractC0157o0.d dVarJ = dVarH.g(str3).j(z2);
        if (pendingIntent != null) {
            dVarJ.f(pendingIntent);
        }
        return dVarJ.b();
    }

    public static Notification d(Context context, String str, int i2, String str2, String str3, boolean z2, int i3, int i4, boolean z3, int i5, PendingIntent pendingIntent) {
        if (Build.VERSION.SDK_INT >= 26) {
            return a.d(context, str, i2, str2, str3, z2, i3, i4, z3, i5, pendingIntent);
        }
        AbstractC0157o0.d dVarN = new AbstractC0157o0.d(context).n(i2);
        if (str2 == null) {
            str2 = "";
        }
        AbstractC0157o0.d dVarH = dVarN.h(str2);
        if (str3 == null) {
            str3 = "";
        }
        AbstractC0157o0.d dVarL = dVarH.g(str3).j(true).k(z3).l(i5);
        if (pendingIntent != null) {
            dVarL.f(pendingIntent);
        }
        if (z2) {
            dVarL.m(0, 0, true);
        } else {
            dVarL.m(i3, i4, false);
        }
        return dVarL.b();
    }
}

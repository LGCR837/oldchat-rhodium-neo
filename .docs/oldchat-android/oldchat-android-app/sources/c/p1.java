package c;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;

/* JADX INFO: loaded from: classes.dex */
public class p1 implements InterfaceC0155n0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Notification.Builder f1985a;

    public p1(Context context, Notification notification, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, RemoteViews remoteViews, int i2, PendingIntent pendingIntent, PendingIntent pendingIntent2, Bitmap bitmap, int i3, int i4, boolean z2) {
        boolean z3 = false;
        Notification.Builder deleteIntent = new Notification.Builder(context).setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteViews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(charSequence).setContentText(charSequence2).setContentInfo(charSequence3).setContentIntent(pendingIntent).setDeleteIntent(notification.deleteIntent);
        if ((notification.flags & 128) != 0) {
            z3 = true;
        }
        this.f1985a = deleteIntent.setFullScreenIntent(pendingIntent2, z3).setLargeIcon(bitmap).setNumber(i2).setProgress(i3, i4, z2);
    }

    @Override // c.InterfaceC0155n0
    public Notification a() {
        return this.f1985a.getNotification();
    }

    @Override // c.InterfaceC0155n0
    public Notification.Builder b() {
        return this.f1985a;
    }
}

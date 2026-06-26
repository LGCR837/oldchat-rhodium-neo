package c;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import c.m1;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: renamed from: c.f1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0138f1 implements InterfaceC0155n0, InterfaceC0153m0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Notification.Builder f1865a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Bundle f1866b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public RemoteViews f1867c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public RemoteViews f1868d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public RemoteViews f1869e;

    public C0138f1(Context context, Notification notification, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, RemoteViews remoteViews, int i2, PendingIntent pendingIntent, PendingIntent pendingIntent2, Bitmap bitmap, int i3, int i4, boolean z2, boolean z3, boolean z4, int i5, CharSequence charSequence4, boolean z5, String str, ArrayList arrayList, Bundle bundle, int i6, int i7, Notification notification2, String str2, boolean z6, String str3, RemoteViews remoteViews2, RemoteViews remoteViews3, RemoteViews remoteViews4) {
        PendingIntent pendingIntent3;
        boolean z7 = false;
        Notification.Builder deleteIntent = new Notification.Builder(context).setWhen(notification.when).setShowWhen(z3).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteViews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(charSequence).setContentText(charSequence2).setSubText(charSequence4).setContentInfo(charSequence3).setContentIntent(pendingIntent).setDeleteIntent(notification.deleteIntent);
        if ((notification.flags & 128) != 0) {
            pendingIntent3 = pendingIntent2;
            z7 = true;
        } else {
            pendingIntent3 = pendingIntent2;
        }
        this.f1865a = deleteIntent.setFullScreenIntent(pendingIntent3, z7).setLargeIcon(bitmap).setNumber(i2).setUsesChronometer(z4).setPriority(i5).setProgress(i3, i4, z2).setLocalOnly(z5).setGroup(str2).setGroupSummary(z6).setSortKey(str3).setCategory(str).setColor(i6).setVisibility(i7).setPublicVersion(notification2);
        Bundle bundle2 = new Bundle();
        this.f1866b = bundle2;
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.f1865a.addPerson((String) it.next());
        }
        this.f1867c = remoteViews2;
        this.f1868d = remoteViews3;
        this.f1869e = remoteViews4;
    }

    @Override // c.InterfaceC0155n0
    public Notification a() {
        this.f1865a.setExtras(this.f1866b);
        Notification notificationBuild = this.f1865a.build();
        RemoteViews remoteViews = this.f1867c;
        if (remoteViews != null) {
            notificationBuild.contentView = remoteViews;
        }
        RemoteViews remoteViews2 = this.f1868d;
        if (remoteViews2 != null) {
            notificationBuild.bigContentView = remoteViews2;
        }
        RemoteViews remoteViews3 = this.f1869e;
        if (remoteViews3 != null) {
            notificationBuild.headsUpContentView = remoteViews3;
        }
        return notificationBuild;
    }

    @Override // c.InterfaceC0155n0
    public Notification.Builder b() {
        return this.f1865a;
    }

    @Override // c.InterfaceC0153m0
    public void c(m1.a aVar) {
        Y0.a(this.f1865a, aVar);
    }
}

package c;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.widget.RemoteViews;
import c.m1;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class A1 {

    public static class a implements InterfaceC0155n0, InterfaceC0153m0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Notification.Builder f1799a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Bundle f1800b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public List f1801c = new ArrayList();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public RemoteViews f1802d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public RemoteViews f1803e;

        public a(Context context, Notification notification, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, RemoteViews remoteViews, int i2, PendingIntent pendingIntent, PendingIntent pendingIntent2, Bitmap bitmap, int i3, int i4, boolean z2, boolean z3, boolean z4, int i5, CharSequence charSequence4, boolean z5, ArrayList arrayList, Bundle bundle, String str, boolean z6, String str2, RemoteViews remoteViews2, RemoteViews remoteViews3) {
            PendingIntent pendingIntent3;
            boolean z7 = false;
            Notification.Builder deleteIntent = new Notification.Builder(context).setWhen(notification.when).setShowWhen(z3).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteViews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(charSequence).setContentText(charSequence2).setSubText(charSequence4).setContentInfo(charSequence3).setContentIntent(pendingIntent).setDeleteIntent(notification.deleteIntent);
            if ((notification.flags & 128) != 0) {
                pendingIntent3 = pendingIntent2;
                z7 = true;
            } else {
                pendingIntent3 = pendingIntent2;
            }
            this.f1799a = deleteIntent.setFullScreenIntent(pendingIntent3, z7).setLargeIcon(bitmap).setNumber(i2).setUsesChronometer(z4).setPriority(i5).setProgress(i3, i4, z2);
            Bundle bundle2 = new Bundle();
            this.f1800b = bundle2;
            if (bundle != null) {
                bundle2.putAll(bundle);
            }
            if (arrayList != null && !arrayList.isEmpty()) {
                this.f1800b.putStringArray("android.people", (String[]) arrayList.toArray(new String[arrayList.size()]));
            }
            if (z5) {
                this.f1800b.putBoolean("android.support.localOnly", true);
            }
            if (str != null) {
                this.f1800b.putString("android.support.groupKey", str);
                if (z6) {
                    this.f1800b.putBoolean("android.support.isGroupSummary", true);
                } else {
                    this.f1800b.putBoolean("android.support.useSideChannel", true);
                }
            }
            if (str2 != null) {
                this.f1800b.putString("android.support.sortKey", str2);
            }
            this.f1802d = remoteViews2;
            this.f1803e = remoteViews3;
        }

        @Override // c.InterfaceC0155n0
        public Notification a() {
            SparseArray<? extends Parcelable> sparseArrayC = y1.c(this.f1801c);
            if (sparseArrayC != null) {
                this.f1800b.putSparseParcelableArray("android.support.actionExtras", sparseArrayC);
            }
            this.f1799a.setExtras(this.f1800b);
            Notification notificationBuild = this.f1799a.build();
            RemoteViews remoteViews = this.f1802d;
            if (remoteViews != null) {
                notificationBuild.contentView = remoteViews;
            }
            RemoteViews remoteViews2 = this.f1803e;
            if (remoteViews2 != null) {
                notificationBuild.bigContentView = remoteViews2;
            }
            return notificationBuild;
        }

        @Override // c.InterfaceC0155n0
        public Notification.Builder b() {
            return this.f1799a;
        }

        @Override // c.InterfaceC0153m0
        public void c(m1.a aVar) {
            this.f1801c.add(y1.e(this.f1799a, aVar));
        }
    }

    public static Bundle a(Notification notification) {
        return notification.extras;
    }
}

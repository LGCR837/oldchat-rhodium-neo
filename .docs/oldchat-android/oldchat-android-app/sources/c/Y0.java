package c;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import c.m1;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class Y0 {

    public static class a implements InterfaceC0155n0, InterfaceC0153m0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Notification.Builder f1827a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Bundle f1828b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public RemoteViews f1829c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public RemoteViews f1830d;

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
            this.f1827a = deleteIntent.setFullScreenIntent(pendingIntent3, z7).setLargeIcon(bitmap).setNumber(i2).setUsesChronometer(z4).setPriority(i5).setProgress(i3, i4, z2).setLocalOnly(z5).setGroup(str).setGroupSummary(z6).setSortKey(str2);
            Bundle bundle2 = new Bundle();
            this.f1828b = bundle2;
            if (bundle != null) {
                bundle2.putAll(bundle);
            }
            if (arrayList != null && !arrayList.isEmpty()) {
                this.f1828b.putStringArray("android.people", (String[]) arrayList.toArray(new String[arrayList.size()]));
            }
            this.f1829c = remoteViews2;
            this.f1830d = remoteViews3;
        }

        @Override // c.InterfaceC0155n0
        public Notification a() {
            this.f1827a.setExtras(this.f1828b);
            Notification notificationBuild = this.f1827a.build();
            RemoteViews remoteViews = this.f1829c;
            if (remoteViews != null) {
                notificationBuild.contentView = remoteViews;
            }
            RemoteViews remoteViews2 = this.f1830d;
            if (remoteViews2 != null) {
                notificationBuild.bigContentView = remoteViews2;
            }
            return notificationBuild;
        }

        @Override // c.InterfaceC0155n0
        public Notification.Builder b() {
            return this.f1827a;
        }

        @Override // c.InterfaceC0153m0
        public void c(m1.a aVar) {
            Y0.a(this.f1827a, aVar);
        }
    }

    public static void a(Notification.Builder builder, m1.a aVar) {
        Notification.Action.Builder builder2 = new Notification.Action.Builder(aVar.d(), aVar.f(), aVar.a());
        if (aVar.e() != null) {
            for (RemoteInput remoteInput : C1.a(aVar.e())) {
                builder2.addRemoteInput(remoteInput);
            }
        }
        Bundle bundle = aVar.c() != null ? new Bundle(aVar.c()) : new Bundle();
        bundle.putBoolean("android.support.allowGeneratedReplies", aVar.b());
        builder2.addExtras(bundle);
        builder.addAction(builder2.build());
    }
}

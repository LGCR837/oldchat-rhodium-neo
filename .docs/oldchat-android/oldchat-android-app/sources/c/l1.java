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
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class l1 implements InterfaceC0155n0, InterfaceC0153m0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Notification.Builder f1871a;

    public l1(Context context, Notification notification, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, RemoteViews remoteViews, int i2, PendingIntent pendingIntent, PendingIntent pendingIntent2, Bitmap bitmap, int i3, int i4, boolean z2, boolean z3, boolean z4, int i5, CharSequence charSequence4, boolean z5, String str, ArrayList arrayList, Bundle bundle, int i6, int i7, Notification notification2, String str2, boolean z6, String str3, CharSequence[] charSequenceArr, RemoteViews remoteViews2, RemoteViews remoteViews3, RemoteViews remoteViews4) {
        PendingIntent pendingIntent3;
        boolean z7 = false;
        Notification.Builder deleteIntent = new Notification.Builder(context).setWhen(notification.when).setShowWhen(z3).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteViews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(charSequence).setContentText(charSequence2).setSubText(charSequence4).setContentInfo(charSequence3).setContentIntent(pendingIntent).setDeleteIntent(notification.deleteIntent);
        if ((notification.flags & 128) != 0) {
            pendingIntent3 = pendingIntent2;
            z7 = true;
        } else {
            pendingIntent3 = pendingIntent2;
        }
        Notification.Builder remoteInputHistory = deleteIntent.setFullScreenIntent(pendingIntent3, z7).setLargeIcon(bitmap).setNumber(i2).setUsesChronometer(z4).setPriority(i5).setProgress(i3, i4, z2).setLocalOnly(z5).setExtras(bundle).setGroup(str2).setGroupSummary(z6).setSortKey(str3).setCategory(str).setColor(i6).setVisibility(i7).setPublicVersion(notification2).setRemoteInputHistory(charSequenceArr);
        this.f1871a = remoteInputHistory;
        if (remoteViews2 != null) {
            remoteInputHistory.setCustomContentView(remoteViews2);
        }
        if (remoteViews3 != null) {
            this.f1871a.setCustomBigContentView(remoteViews3);
        }
        if (remoteViews4 != null) {
            this.f1871a.setCustomHeadsUpContentView(remoteViews4);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.f1871a.addPerson((String) it.next());
        }
    }

    @Override // c.InterfaceC0155n0
    public Notification a() {
        return this.f1871a.build();
    }

    @Override // c.InterfaceC0155n0
    public Notification.Builder b() {
        return this.f1871a;
    }

    @Override // c.InterfaceC0153m0
    public void c(m1.a aVar) {
        Notification.Action.Builder builder = new Notification.Action.Builder(aVar.d(), aVar.f(), aVar.a());
        if (aVar.e() != null) {
            for (RemoteInput remoteInput : C1.a(aVar.e())) {
                builder.addRemoteInput(remoteInput);
            }
        }
        Bundle bundle = aVar.c() != null ? new Bundle(aVar.c()) : new Bundle();
        bundle.putBoolean("android.support.allowGeneratedReplies", aVar.b());
        builder.addExtras(bundle);
        builder.setAllowGeneratedReplies(aVar.b());
        this.f1871a.addAction(builder.build());
    }
}

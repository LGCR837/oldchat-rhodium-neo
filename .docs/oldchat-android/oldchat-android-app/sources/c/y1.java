package c;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.widget.RemoteViews;
import c.m1;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class y1 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Field f2068b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f2069c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f2067a = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Object f2070d = new Object();

    public static class a implements InterfaceC0155n0, InterfaceC0153m0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Notification.Builder f2071a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Bundle f2072b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public List f2073c = new ArrayList();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public RemoteViews f2074d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public RemoteViews f2075e;

        public a(Context context, Notification notification, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, RemoteViews remoteViews, int i2, PendingIntent pendingIntent, PendingIntent pendingIntent2, Bitmap bitmap, int i3, int i4, boolean z2, boolean z3, int i5, CharSequence charSequence4, boolean z4, Bundle bundle, String str, boolean z5, String str2, RemoteViews remoteViews2, RemoteViews remoteViews3) {
            PendingIntent pendingIntent3;
            boolean z6 = false;
            Notification.Builder deleteIntent = new Notification.Builder(context).setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteViews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(charSequence).setContentText(charSequence2).setSubText(charSequence4).setContentInfo(charSequence3).setContentIntent(pendingIntent).setDeleteIntent(notification.deleteIntent);
            if ((notification.flags & 128) != 0) {
                pendingIntent3 = pendingIntent2;
                z6 = true;
            } else {
                pendingIntent3 = pendingIntent2;
            }
            this.f2071a = deleteIntent.setFullScreenIntent(pendingIntent3, z6).setLargeIcon(bitmap).setNumber(i2).setUsesChronometer(z3).setPriority(i5).setProgress(i3, i4, z2);
            Bundle bundle2 = new Bundle();
            this.f2072b = bundle2;
            if (bundle != null) {
                bundle2.putAll(bundle);
            }
            if (z4) {
                bundle2.putBoolean("android.support.localOnly", true);
            }
            if (str != null) {
                bundle2.putString("android.support.groupKey", str);
                if (z5) {
                    bundle2.putBoolean("android.support.isGroupSummary", true);
                } else {
                    bundle2.putBoolean("android.support.useSideChannel", true);
                }
            }
            if (str2 != null) {
                bundle2.putString("android.support.sortKey", str2);
            }
            this.f2074d = remoteViews2;
            this.f2075e = remoteViews3;
        }

        @Override // c.InterfaceC0155n0
        public Notification a() {
            Notification notificationBuild = this.f2071a.build();
            Bundle bundleD = y1.d(notificationBuild);
            Bundle bundle = new Bundle(this.f2072b);
            for (String str : this.f2072b.keySet()) {
                if (bundleD.containsKey(str)) {
                    bundle.remove(str);
                }
            }
            bundleD.putAll(bundle);
            SparseArray<? extends Parcelable> sparseArrayC = y1.c(this.f2073c);
            if (sparseArrayC != null) {
                y1.d(notificationBuild).putSparseParcelableArray("android.support.actionExtras", sparseArrayC);
            }
            RemoteViews remoteViews = this.f2074d;
            if (remoteViews != null) {
                notificationBuild.contentView = remoteViews;
            }
            RemoteViews remoteViews2 = this.f2075e;
            if (remoteViews2 != null) {
                notificationBuild.bigContentView = remoteViews2;
            }
            return notificationBuild;
        }

        @Override // c.InterfaceC0155n0
        public Notification.Builder b() {
            return this.f2071a;
        }

        @Override // c.InterfaceC0153m0
        public void c(m1.a aVar) {
            this.f2073c.add(y1.e(this.f2071a, aVar));
        }
    }

    public static void a(InterfaceC0155n0 interfaceC0155n0, CharSequence charSequence, boolean z2, CharSequence charSequence2, Bitmap bitmap, Bitmap bitmap2, boolean z3) {
        Notification.BigPictureStyle bigPictureStyleBigPicture = new Notification.BigPictureStyle(interfaceC0155n0.b()).setBigContentTitle(charSequence).bigPicture(bitmap);
        if (z3) {
            bigPictureStyleBigPicture.bigLargeIcon(bitmap2);
        }
        if (z2) {
            bigPictureStyleBigPicture.setSummaryText(charSequence2);
        }
    }

    public static void b(InterfaceC0155n0 interfaceC0155n0, CharSequence charSequence, boolean z2, CharSequence charSequence2, CharSequence charSequence3) {
        Notification.BigTextStyle bigTextStyleBigText = new Notification.BigTextStyle(interfaceC0155n0.b()).setBigContentTitle(charSequence).bigText(charSequence3);
        if (z2) {
            bigTextStyleBigText.setSummaryText(charSequence2);
        }
    }

    public static SparseArray c(List list) {
        int size = list.size();
        SparseArray sparseArray = null;
        for (int i2 = 0; i2 < size; i2++) {
            Bundle bundle = (Bundle) list.get(i2);
            if (bundle != null) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray();
                }
                sparseArray.put(i2, bundle);
            }
        }
        return sparseArray;
    }

    public static Bundle d(Notification notification) {
        synchronized (f2067a) {
            if (f2069c) {
                return null;
            }
            try {
                if (f2068b == null) {
                    Field declaredField = Notification.class.getDeclaredField("extras");
                    if (!Bundle.class.isAssignableFrom(declaredField.getType())) {
                        Log.e("NotificationCompat", "Notification.extras field is not of type Bundle");
                        f2069c = true;
                        return null;
                    }
                    declaredField.setAccessible(true);
                    f2068b = declaredField;
                }
                Bundle bundle = (Bundle) f2068b.get(notification);
                if (bundle == null) {
                    bundle = new Bundle();
                    f2068b.set(notification, bundle);
                }
                return bundle;
            } catch (IllegalAccessException e2) {
                Log.e("NotificationCompat", "Unable to access notification extras", e2);
                f2069c = true;
                return null;
            } catch (NoSuchFieldException e3) {
                Log.e("NotificationCompat", "Unable to access notification extras", e3);
                f2069c = true;
                return null;
            }
        }
    }

    public static Bundle e(Notification.Builder builder, m1.a aVar) {
        builder.addAction(aVar.d(), aVar.f(), aVar.a());
        Bundle bundle = new Bundle(aVar.c());
        if (aVar.e() != null) {
            bundle.putParcelableArray("android.support.remoteInputs", E1.b(aVar.e()));
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", aVar.b());
        return bundle;
    }
}

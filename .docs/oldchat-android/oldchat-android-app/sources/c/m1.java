package c;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public abstract class m1 {

    public static abstract class a {

        /* JADX INFO: renamed from: c.m1$a$a, reason: collision with other inner class name */
        public interface InterfaceC0019a {
        }

        public abstract PendingIntent a();

        public abstract boolean b();

        public abstract Bundle c();

        public abstract int d();

        public abstract D1[] e();

        public abstract CharSequence f();
    }

    public static Notification a(Notification notification, Context context, CharSequence charSequence, CharSequence charSequence2, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
        notification.setLatestEventInfo(context, charSequence, charSequence2, pendingIntent);
        notification.fullScreenIntent = pendingIntent2;
        return notification;
    }
}

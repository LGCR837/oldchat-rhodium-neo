package d;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class q {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Object f4902f = new Object();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static q f4903g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f4904a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f4905b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashMap f4906c = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList f4907d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Handler f4908e;

    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                super.handleMessage(message);
            } else {
                q.this.b();
            }
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Intent f4910a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final ArrayList f4911b;

        public b(Intent intent, ArrayList arrayList) {
            this.f4910a = intent;
            this.f4911b = arrayList;
        }
    }

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final IntentFilter f4912a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final BroadcastReceiver f4913b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f4914c;

        public c(IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
            this.f4912a = intentFilter;
            this.f4913b = broadcastReceiver;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Receiver{");
            sb.append(this.f4913b);
            sb.append(" filter=");
            sb.append(this.f4912a);
            sb.append("}");
            return sb.toString();
        }
    }

    public q(Context context) {
        this.f4904a = context;
        this.f4908e = new a(context.getMainLooper());
    }

    public static q c(Context context) {
        q qVar;
        synchronized (f4902f) {
            try {
                if (f4903g == null) {
                    f4903g = new q(context.getApplicationContext());
                }
                qVar = f4903g;
            } catch (Throwable th) {
                throw th;
            }
        }
        return qVar;
    }

    public final void b() {
        int size;
        b[] bVarArr;
        while (true) {
            synchronized (this.f4905b) {
                try {
                    size = this.f4907d.size();
                    if (size <= 0) {
                        return;
                    }
                    bVarArr = new b[size];
                    this.f4907d.toArray(bVarArr);
                    this.f4907d.clear();
                } catch (Throwable th) {
                    throw th;
                }
            }
            for (int i2 = 0; i2 < size; i2++) {
                b bVar = bVarArr[i2];
                for (int i3 = 0; i3 < bVar.f4911b.size(); i3++) {
                    ((c) bVar.f4911b.get(i3)).f4913b.onReceive(this.f4904a, bVar.f4910a);
                }
            }
        }
    }

    public void d(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        synchronized (this.f4905b) {
            try {
                c cVar = new c(intentFilter, broadcastReceiver);
                ArrayList arrayList = (ArrayList) this.f4905b.get(broadcastReceiver);
                if (arrayList == null) {
                    arrayList = new ArrayList(1);
                    this.f4905b.put(broadcastReceiver, arrayList);
                }
                arrayList.add(intentFilter);
                for (int i2 = 0; i2 < intentFilter.countActions(); i2++) {
                    String action = intentFilter.getAction(i2);
                    ArrayList arrayList2 = (ArrayList) this.f4906c.get(action);
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList(1);
                        this.f4906c.put(action, arrayList2);
                    }
                    arrayList2.add(cVar);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean e(Intent intent) {
        int i2;
        String str;
        ArrayList arrayList;
        ArrayList arrayList2;
        String str2;
        synchronized (this.f4905b) {
            try {
                String action = intent.getAction();
                String strResolveTypeIfNeeded = intent.resolveTypeIfNeeded(this.f4904a.getContentResolver());
                Uri data = intent.getData();
                String scheme = intent.getScheme();
                Set<String> categories = intent.getCategories();
                boolean z2 = (intent.getFlags() & 8) != 0;
                if (z2) {
                    Log.v("LocalBroadcastManager", "Resolving type " + strResolveTypeIfNeeded + " scheme " + scheme + " of intent " + intent);
                }
                ArrayList arrayList3 = (ArrayList) this.f4906c.get(intent.getAction());
                if (arrayList3 != null) {
                    if (z2) {
                        Log.v("LocalBroadcastManager", "Action list: " + arrayList3);
                    }
                    ArrayList arrayList4 = null;
                    int i3 = 0;
                    while (i3 < arrayList3.size()) {
                        c cVar = (c) arrayList3.get(i3);
                        if (z2) {
                            Log.v("LocalBroadcastManager", "Matching against filter " + cVar.f4912a);
                        }
                        if (cVar.f4914c) {
                            if (z2) {
                                Log.v("LocalBroadcastManager", "  Filter's target already added");
                            }
                            i2 = i3;
                            arrayList2 = arrayList3;
                            str = action;
                            str2 = strResolveTypeIfNeeded;
                            arrayList = arrayList4;
                        } else {
                            i2 = i3;
                            str = action;
                            arrayList = arrayList4;
                            arrayList2 = arrayList3;
                            str2 = strResolveTypeIfNeeded;
                            int iMatch = cVar.f4912a.match(action, strResolveTypeIfNeeded, scheme, data, categories, "LocalBroadcastManager");
                            if (iMatch >= 0) {
                                if (z2) {
                                    Log.v("LocalBroadcastManager", "  Filter matched!  match=0x" + Integer.toHexString(iMatch));
                                }
                                arrayList4 = arrayList == null ? new ArrayList() : arrayList;
                                arrayList4.add(cVar);
                                cVar.f4914c = true;
                                i3 = i2 + 1;
                                action = str;
                                arrayList3 = arrayList2;
                                strResolveTypeIfNeeded = str2;
                            } else if (z2) {
                                Log.v("LocalBroadcastManager", "  Filter did not match: " + (iMatch != -4 ? iMatch != -3 ? iMatch != -2 ? iMatch != -1 ? "unknown reason" : "type" : "data" : "action" : "category"));
                            }
                        }
                        arrayList4 = arrayList;
                        i3 = i2 + 1;
                        action = str;
                        arrayList3 = arrayList2;
                        strResolveTypeIfNeeded = str2;
                    }
                    ArrayList arrayList5 = arrayList4;
                    if (arrayList5 != null) {
                        for (int i4 = 0; i4 < arrayList5.size(); i4++) {
                            ((c) arrayList5.get(i4)).f4914c = false;
                        }
                        this.f4907d.add(new b(intent, arrayList5));
                        if (!this.f4908e.hasMessages(1)) {
                            this.f4908e.sendEmptyMessage(1);
                        }
                        return true;
                    }
                }
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void f(BroadcastReceiver broadcastReceiver) {
        synchronized (this.f4905b) {
            try {
                ArrayList arrayList = (ArrayList) this.f4905b.remove(broadcastReceiver);
                if (arrayList == null) {
                    return;
                }
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    IntentFilter intentFilter = (IntentFilter) arrayList.get(i2);
                    for (int i3 = 0; i3 < intentFilter.countActions(); i3++) {
                        String action = intentFilter.getAction(i3);
                        ArrayList arrayList2 = (ArrayList) this.f4906c.get(action);
                        if (arrayList2 != null) {
                            int i4 = 0;
                            while (i4 < arrayList2.size()) {
                                if (((c) arrayList2.get(i4)).f4913b == broadcastReceiver) {
                                    arrayList2.remove(i4);
                                    i4--;
                                }
                                i4++;
                            }
                            if (arrayList2.size() <= 0) {
                                this.f4906c.remove(action);
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

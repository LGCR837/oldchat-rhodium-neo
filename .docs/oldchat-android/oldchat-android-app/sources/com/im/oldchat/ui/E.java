package com.im.oldchat.ui;

import android.os.AsyncTask;
import android.os.Build;
import g0.d;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class E {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AsyncTask f2617a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2618b = 0;

    public class a implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2619a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ c f2620b;

        public a(int i2, c cVar) {
            this.f2619a = i2;
            this.f2620b = cVar;
        }

        @Override // g0.d.i
        public void b(String str) {
            E.this.g(str, this.f2619a, this.f2620b);
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            c cVar;
            if (this.f2619a != E.this.f2618b || (cVar = this.f2620b) == null) {
                return;
            }
            cVar.b(i2, str);
        }
    }

    public class b extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f2622a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f2623b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ c f2624c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f2625d;

        public b(String str, c cVar, int i2) {
            this.f2623b = str;
            this.f2624c = cVar;
            this.f2625d = i2;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public List doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = new JSONObject(this.f2623b).getJSONArray("members");
                for (int i2 = 0; i2 < jSONArray.length() && !isCancelled(); i2++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    j0.h hVar = new j0.h();
                    hVar.f5522a = jSONObject.optString("uid");
                    hVar.f5523b = jSONObject.optString("username");
                    hVar.f5524c = jSONObject.optString("display_name");
                    hVar.f5525d = jSONObject.optString("user_title");
                    hVar.f5526e = jSONObject.optString("avatar_url");
                    hVar.f5527f = jSONObject.optInt("role", 0);
                    hVar.f5528g = jSONObject.optLong("joined_at", 0L);
                    arrayList.add(hVar);
                }
            } catch (Exception unused) {
                this.f2622a = true;
            }
            return arrayList;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List list) {
            if (isCancelled() || this.f2624c == null || this.f2625d != E.this.f2618b) {
                return;
            }
            E.this.f2617a = null;
            if (this.f2622a) {
                this.f2624c.c();
            } else {
                this.f2624c.a(list);
            }
        }
    }

    public interface c {
        void a(List list);

        void b(int i2, String str);

        void c();
    }

    public static void e(AsyncTask asyncTask) {
        if (asyncTask == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 11) {
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            asyncTask.execute(new Void[0]);
        }
    }

    public void d() {
        this.f2618b++;
        AsyncTask asyncTask = this.f2617a;
        if (asyncTask != null) {
            asyncTask.cancel(true);
            this.f2617a = null;
        }
    }

    public void f(String str, String str2, c cVar) {
        int i2 = this.f2618b + 1;
        this.f2618b = i2;
        g0.d.R("/groups/members?group_id=" + str2, str, new a(i2, cVar));
    }

    public final void g(String str, int i2, c cVar) {
        AsyncTask asyncTask = this.f2617a;
        if (asyncTask != null) {
            asyncTask.cancel(true);
            this.f2617a = null;
        }
        b bVar = new b(str, cVar, i2);
        this.f2617a = bVar;
        e(bVar);
    }
}

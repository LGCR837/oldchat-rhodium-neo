package com.im.oldchat.ui;

import android.content.Context;
import android.widget.Toast;
import g0.d;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class C {

    public class a implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2428a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ i f2429b;

        public a(String str, i iVar) {
            this.f2428a = str;
            this.f2429b = iVar;
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("groups");
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    if (this.f2428a.equals(jSONObject.optString("group_id"))) {
                        j0.e eVar = new j0.e();
                        eVar.f5504a = jSONObject.optString("group_id");
                        eVar.f5505b = jSONObject.optString("name");
                        eVar.f5506c = jSONObject.optString("avatar_url");
                        eVar.f5507d = jSONObject.optBoolean("join_approval", false);
                        eVar.f5508e = jSONObject.optBoolean("global_mute", false);
                        eVar.f5509f = jSONObject.optInt("role", 0);
                        eVar.f5510g = jSONObject.optString("announcement", "");
                        eVar.f5511h = jSONObject.optInt("announcement_mode", 0);
                        eVar.f5512i = jSONObject.optLong("announcement_updated_at", 0L);
                        eVar.f5513j = jSONObject.optLong("announcement_read_at", 0L);
                        eVar.f5514k = jSONObject.optInt("member_count", 0);
                        i iVar = this.f2429b;
                        if (iVar != null) {
                            iVar.a(eVar);
                            return;
                        }
                        return;
                    }
                }
            } catch (Exception unused) {
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
        }
    }

    public class b implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j f2431a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Context f2432b;

        public b(j jVar, Context context) {
            this.f2431a = jVar;
            this.f2432b = context;
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("members");
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
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
                j jVar = this.f2431a;
                if (jVar != null) {
                    jVar.a(arrayList);
                }
            } catch (Exception unused) {
                Toast.makeText(this.f2432b, "加载成员失败", 0).show();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(this.f2432b, "加载成员失败: " + i2, 0).show();
        }
    }

    public class c implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f2434a;

        public c(Context context) {
            this.f2434a = context;
        }

        @Override // g0.d.i
        public void b(String str) {
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(this.f2434a, "更新失败: " + i2, 0).show();
        }
    }

    public class d implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Runnable f2436a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Runnable f2437b;

        public d(Runnable runnable, Runnable runnable2) {
            this.f2436a = runnable;
            this.f2437b = runnable2;
        }

        @Override // g0.d.i
        public void b(String str) {
            Runnable runnable = this.f2436a;
            if (runnable != null) {
                runnable.run();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            Runnable runnable;
            if (g0.e.v(i2, str) || (runnable = this.f2437b) == null) {
                return;
            }
            runnable.run();
        }
    }

    public class e implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Runnable f2439a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Runnable f2440b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Context f2441c;

        public e(Runnable runnable, Runnable runnable2, Context context) {
            this.f2439a = runnable;
            this.f2440b = runnable2;
            this.f2441c = context;
        }

        @Override // g0.d.i
        public void b(String str) {
            Runnable runnable = this.f2439a;
            if (runnable != null) {
                runnable.run();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Runnable runnable = this.f2440b;
            if (runnable != null) {
                runnable.run();
                return;
            }
            Context context = this.f2441c;
            if (context != null) {
                Toast.makeText(context, "修改失败: " + i2, 0).show();
            }
        }
    }

    public class f implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Runnable f2443a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Runnable f2444b;

        public f(Runnable runnable, Runnable runnable2) {
            this.f2443a = runnable;
            this.f2444b = runnable2;
        }

        @Override // g0.d.i
        public void b(String str) {
            Runnable runnable = this.f2443a;
            if (runnable != null) {
                runnable.run();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            Runnable runnable;
            if (g0.e.v(i2, str) || (runnable = this.f2444b) == null) {
                return;
            }
            runnable.run();
        }
    }

    public class g implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Runnable f2446a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Context f2447b;

        public g(Runnable runnable, Context context) {
            this.f2446a = runnable;
            this.f2447b = context;
        }

        @Override // g0.d.i
        public void b(String str) {
            Runnable runnable = this.f2446a;
            if (runnable != null) {
                runnable.run();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(this.f2447b, "操作失败: " + i2, 0).show();
        }
    }

    public class h implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Runnable f2449a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Context f2450b;

        public h(Runnable runnable, Context context) {
            this.f2449a = runnable;
            this.f2450b = context;
        }

        @Override // g0.d.i
        public void b(String str) {
            Runnable runnable = this.f2449a;
            if (runnable != null) {
                runnable.run();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(this.f2450b, "操作失败: " + i2, 0).show();
        }
    }

    public interface i {
        void a(j0.e eVar);
    }

    public interface j {
        void a(List list);
    }

    public void a(Context context, String str, String str2, j0.h hVar, Runnable runnable) {
        if (str2 == null || hVar == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("group_id", str2);
            jSONObject.put("user_uid", hVar.f5522a);
            g0.d.S("/groups/kick", jSONObject, str, new g(runnable, context));
        } catch (Exception unused) {
            Toast.makeText(context, "操作失败", 0).show();
        }
    }

    public void b(Context context, String str, String str2, i iVar) {
        g0.d.R("/groups/list", str, new a(str2, iVar));
    }

    public void c(Context context, String str, String str2, j jVar) {
        if (str2 == null || str2.length() == 0) {
            return;
        }
        g0.d.R("/groups/members?group_id=" + str2, str, new b(jVar, context));
    }

    public void d(Context context, String str, String str2, Runnable runnable, Runnable runnable2) {
        if (str2 == null || str2.length() == 0) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("group_id", str2);
            g0.d.S("/groups/announcement/read", jSONObject, str, new f(runnable, runnable2));
        } catch (Exception unused) {
            if (runnable2 != null) {
                runnable2.run();
            }
        }
    }

    public void e(Context context, String str, String str2, j0.h hVar, boolean z2, Runnable runnable) {
        if (str2 == null || hVar == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("group_id", str2);
            jSONObject.put("user_uid", hVar.f5522a);
            jSONObject.put("admin", z2);
            g0.d.S("/groups/admin", jSONObject, str, new h(runnable, context));
        } catch (Exception unused) {
            Toast.makeText(context, "操作失败", 0).show();
        }
    }

    public void f(Context context, String str, String str2, String str3, int i2, Runnable runnable, Runnable runnable2) {
        if (str2 == null || str2.length() == 0) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("group_id", str2);
            if (str3 == null) {
                str3 = "";
            }
            jSONObject.put("announcement", str3);
            jSONObject.put("announcement_mode", i2);
            g0.d.S("/groups/announcement", jSONObject, str, new d(runnable, runnable2));
        } catch (Exception unused) {
            if (runnable2 != null) {
                runnable2.run();
            }
        }
    }

    public void g(Context context, String str, String str2, String str3, Runnable runnable, Runnable runnable2) {
        if (str2 == null || str2.length() == 0) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("group_id", str2);
            if (str3 == null) {
                str3 = "";
            }
            jSONObject.put("name", str3);
            g0.d.S("/groups/name", jSONObject, str, new e(runnable, runnable2, context));
        } catch (Exception unused) {
            if (runnable2 != null) {
                runnable2.run();
            } else if (context != null) {
                Toast.makeText(context, "修改失败", 0).show();
            }
        }
    }

    public void h(Context context, String str, String str2, boolean z2, boolean z3) {
        if (str2 == null || str2.length() == 0) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("group_id", str2);
            jSONObject.put("join_approval", z2);
            jSONObject.put("global_mute", z3);
            g0.d.S("/groups/settings", jSONObject, str, new c(context));
        } catch (Exception unused) {
            Toast.makeText(context, "更新失败", 0).show();
        }
    }
}

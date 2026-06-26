package l0;

import android.content.Context;
import g0.d;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import o0.AbstractC0451q;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class K {
    public static void a(Context context, String str, String str2, com.im.oldchat.ui.G g2, Map map, Map map2, Map map3, Map map4, List list, Runnable runnable) {
        if (str2 == null || str2.isEmpty() || str == null || str.isEmpty() || g2 == null) {
            return;
        }
        if (map2 != null) {
            AbstractC0451q.a(context, map2);
            g2.z0(map2);
        }
        g0.d.R("/groups/members?group_id=" + str2, str, new a(map, map3, map4, list, g2, context, map2, str2, runnable));
    }

    public class a implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Map f5915a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Map f5916b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Map f5917c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ List f5918d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ com.im.oldchat.ui.G f5919e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ Context f5920f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ Map f5921g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ String f5922h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ Runnable f5923i;

        public a(Map map, Map map2, Map map3, List list, com.im.oldchat.ui.G g2, Context context, Map map4, String str, Runnable runnable) {
            this.f5915a = map;
            this.f5916b = map2;
            this.f5917c = map3;
            this.f5918d = list;
            this.f5919e = g2;
            this.f5920f = context;
            this.f5921g = map4;
            this.f5922h = str;
            this.f5923i = runnable;
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("members");
                Map map = this.f5915a;
                if (map != null) {
                    map.clear();
                }
                Map map2 = this.f5916b;
                if (map2 != null) {
                    map2.clear();
                }
                Map map3 = this.f5917c;
                if (map3 != null) {
                    map3.clear();
                }
                List list = this.f5918d;
                if (list != null) {
                    list.clear();
                }
                HashMap map4 = new HashMap();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    String strOptString = jSONObject.optString("uid");
                    if (strOptString != null && !strOptString.isEmpty()) {
                        String strOptString2 = jSONObject.optString("display_name");
                        String strOptString3 = jSONObject.optString("username");
                        String strOptString4 = jSONObject.optString("user_title");
                        String strOptString5 = jSONObject.optString("avatar_url");
                        String strC = j0.d.c("", strOptString2, strOptString3, strOptString);
                        Map map5 = this.f5915a;
                        if (map5 != null) {
                            map5.put(strOptString, strC);
                        }
                        if (strOptString5 != null && strOptString5.length() > 0) {
                            map4.put(strOptString, strOptString5);
                        }
                        Map map6 = this.f5916b;
                        if (map6 != null) {
                            map6.put(strOptString, strOptString4 == null ? "" : strOptString4);
                        }
                        Map map7 = this.f5917c;
                        if (map7 != null) {
                            map7.put(strOptString, Integer.valueOf(jSONObject.optInt("role", 0)));
                        }
                        if (this.f5918d != null) {
                            j0.h hVar = new j0.h();
                            hVar.f5522a = strOptString;
                            hVar.f5524c = strOptString2;
                            hVar.f5523b = strOptString3;
                            hVar.f5525d = strOptString4;
                            hVar.f5526e = strOptString5;
                            hVar.f5527f = jSONObject.optInt("role", 0);
                            hVar.f5528g = jSONObject.optLong("joined_at", 0L);
                            this.f5918d.add(hVar);
                        }
                    }
                }
                Map map8 = this.f5915a;
                if (map8 != null) {
                    this.f5919e.A0(map8);
                    j0.x.e(this.f5920f, this.f5915a);
                }
                Map map9 = this.f5916b;
                if (map9 != null) {
                    this.f5919e.C0(map9);
                    j0.y.d(this.f5920f, this.f5916b);
                }
                if (!map4.isEmpty()) {
                    AbstractC0451q.g(this.f5920f, map4, false);
                    Map map10 = this.f5921g;
                    if (map10 != null) {
                        map10.putAll(map4);
                        AbstractC0451q.a(this.f5920f, this.f5921g);
                        this.f5919e.z0(this.f5921g);
                    } else {
                        this.f5919e.notifyDataSetChanged();
                    }
                }
                Map map11 = this.f5917c;
                if (map11 != null) {
                    this.f5919e.B0(map11);
                }
                int length = jSONArray.length();
                j0.j.n(this.f5920f, this.f5922h, length);
                j0.f.e(this.f5920f, this.f5922h, length);
                Runnable runnable = this.f5923i;
                if (runnable != null) {
                    runnable.run();
                }
            } catch (Exception unused) {
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
        }
    }
}

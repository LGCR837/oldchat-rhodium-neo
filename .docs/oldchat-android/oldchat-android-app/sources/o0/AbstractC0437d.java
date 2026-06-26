package o0;

import android.content.Context;
import android.content.SharedPreferences;
import g0.d;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: renamed from: o0.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0437d {
    public static void d(Context context) {
        if (context == null) {
            return;
        }
        context.getApplicationContext().getSharedPreferences("avatar_cache", 0).edit().clear().apply();
    }

    public static String e(String str) {
        if (str != null && !str.isEmpty()) {
            int iIndexOf = str.indexOf(63);
            if (iIndexOf >= 0) {
                str = str.substring(0, iIndexOf);
            }
            int iLastIndexOf = str.lastIndexOf(47);
            if (iLastIndexOf >= 0 && iLastIndexOf < str.length() - 1) {
                return str.substring(iLastIndexOf + 1);
            }
        }
        return "";
    }

    public static JSONObject f(SharedPreferences sharedPreferences) {
        try {
            return new JSONObject(sharedPreferences.getString("friend_avatar_map", "{}"));
        } catch (Exception unused) {
            return new JSONObject();
        }
    }

    public static void g(Context context, String str) {
        if (context == null || str == null || str.isEmpty()) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        i(applicationContext, str);
        h(applicationContext, str);
    }

    public static void h(Context context, String str) {
        g0.d.R("/friends", str, new b(context));
    }

    public static void i(Context context, String str) {
        g0.d.R("/me", str, new a(context));
    }

    public static void j(Context context, String str, String str2, String str3) {
        if (context == null) {
            return;
        }
        if (str3 == null || str3.isEmpty()) {
            context.getSharedPreferences("avatar_cache", 0).edit().putString(str, "").apply();
            return;
        }
        String strE = e(str3);
        if (strE.isEmpty()) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("avatar_cache", 0);
        if ("self_avatar_file".equals(str)) {
            if (strE.equals(sharedPreferences.getString("self_avatar_file", "")) && AbstractC0455v.d(context, str3)) {
                return;
            }
            sharedPreferences.edit().putString("self_avatar_file", strE).apply();
            AbstractC0455v.l(context, str3);
            return;
        }
        JSONObject jSONObjectF = f(sharedPreferences);
        if (strE.equals(jSONObjectF.optString(str2, "")) && AbstractC0455v.d(context, str3)) {
            return;
        }
        try {
            jSONObjectF.put(str2, strE);
            sharedPreferences.edit().putString("friend_avatar_map", jSONObjectF.toString()).apply();
        } catch (Exception unused) {
        }
        AbstractC0455v.l(context, str3);
    }

    /* JADX INFO: renamed from: o0.d$a */
    public class a implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f7085a;

        public a(Context context) {
            this.f7085a = context;
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                AbstractC0437d.j(this.f7085a, "self_avatar_file", "self", new JSONObject(str).optString("avatar_url", ""));
            } catch (Exception unused) {
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
        }
    }

    /* JADX INFO: renamed from: o0.d$b */
    public class b implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f7086a;

        public b(Context context) {
            this.f7086a = context;
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("friends");
                SharedPreferences sharedPreferences = this.f7086a.getSharedPreferences("avatar_cache", 0);
                JSONObject jSONObjectF = AbstractC0437d.f(sharedPreferences);
                ArrayList arrayList = new ArrayList();
                boolean z2 = false;
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    String strOptString = jSONObject.optString("uid", "");
                    String strOptString2 = jSONObject.optString("avatar_url", "");
                    j0.w wVar = new j0.w();
                    wVar.f5645a = jSONObject.optString("id", "");
                    wVar.f5646b = strOptString;
                    wVar.f5647c = jSONObject.optString("username", "");
                    wVar.f5648d = jSONObject.optString("display_name", "");
                    wVar.f5650f = jSONObject.optString("user_title", "");
                    wVar.f5651g = strOptString2;
                    arrayList.add(wVar);
                    if (!strOptString.isEmpty()) {
                        String strE = AbstractC0437d.e(strOptString2);
                        if (!strE.isEmpty()) {
                            String strOptString3 = jSONObjectF.optString(strOptString, "");
                            boolean zD = AbstractC0455v.d(this.f7086a, strOptString2);
                            if (!strE.equals(strOptString3) || !zD) {
                                jSONObjectF.put(strOptString, strE);
                                AbstractC0455v.l(this.f7086a, strOptString2);
                                z2 = true;
                            }
                        }
                    }
                }
                if (z2) {
                    sharedPreferences.edit().putString("friend_avatar_map", jSONObjectF.toString()).apply();
                }
                if (arrayList.isEmpty()) {
                    return;
                }
                j0.r.e(this.f7086a, arrayList);
                j0.x.c(this.f7086a, arrayList);
                j0.y.b(this.f7086a, arrayList);
            } catch (Exception unused) {
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
        }
    }
}

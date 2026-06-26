package g0;

import android.content.Context;
import android.content.SharedPreferences;
import com.im.oldchat.OldChatApplication;
import g0.e;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import o0.AbstractC0443i;
import o0.AbstractC0445k;
import o0.F;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static String a(SharedPreferences sharedPreferences, Context context, String str, String str2) {
        JSONObject jSONObject = new JSONObject(str2);
        String strOptString = jSONObject.optString("access_token", "");
        String strOptString2 = jSONObject.optString("refresh_token", "");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("user");
        if (strOptString.length() == 0) {
            return null;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString("access_token", strOptString);
        if (strOptString2 != null && strOptString2.length() > 0) {
            editorEdit.putString("refresh_token", strOptString2);
        }
        if (jSONObjectOptJSONObject != null) {
            String strOptString3 = jSONObjectOptJSONObject.optString("id", "");
            String strOptString4 = jSONObjectOptJSONObject.optString("uid", "");
            if (strOptString3.length() > 0) {
                editorEdit.putString("user_id", strOptString3);
            }
            if (strOptString4.length() > 0) {
                if (str.length() > 0 && !str.equals(strOptString4)) {
                    F.e(context, str, strOptString4);
                }
                editorEdit.putString("my_uid", strOptString4);
            }
        }
        editorEdit.apply();
        return strOptString;
    }

    public static boolean b() {
        if (!AbstractC0443i.s()) {
            return false;
        }
        if (AbstractC0443i.q()) {
            return true;
        }
        synchronized (a.class) {
            if (AbstractC0443i.q()) {
                return true;
            }
            try {
                AbstractC0443i.a aVarF = AbstractC0443i.f();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("client_pub", aVarF.b());
                e.c cVarC = c("POST", "/auth/handshake", jSONObject, null);
                int i2 = cVarC.f5348a;
                if (i2 >= 200 && i2 < 300) {
                    JSONObject jSONObject2 = new JSONObject(cVarC.f5349b);
                    String strOptString = jSONObject2.optString("session_id", "");
                    String strOptString2 = jSONObject2.optString("server_pub", "");
                    if (strOptString.length() != 0 && strOptString2.length() != 0) {
                        AbstractC0443i.b bVarA = aVarF.a(strOptString2);
                        AbstractC0443i.u(strOptString, bVarA.f7112a, bVarA.f7113b);
                        return true;
                    }
                    AbstractC0443i.g();
                    return false;
                }
                AbstractC0443i.g();
                return false;
            } catch (Exception unused) {
                AbstractC0443i.g();
                return false;
            }
        }
    }

    public static e.c c(String str, String str2, JSONObject jSONObject, String str3) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(e.f5335a + str2).openConnection();
        httpURLConnection.setConnectTimeout(8000);
        httpURLConnection.setReadTimeout(15000);
        httpURLConnection.setRequestMethod(str);
        if (str3 != null && str3.length() > 0) {
            httpURLConnection.setRequestProperty("Authorization", "Bearer " + str3);
        }
        if (jSONObject != null) {
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(jSONObject.toString().getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();
        }
        int responseCode = httpURLConnection.getResponseCode();
        return new e.c(responseCode, e.p(httpURLConnection, responseCode));
    }

    public static String d() {
        Context contextA = OldChatApplication.a();
        return contextA == null ? "" : contextA.getSharedPreferences("auth", 0).getString("access_token", "");
    }

    public static String e() throws Throwable {
        try {
            Context contextA = OldChatApplication.a();
            if (contextA == null) {
                return null;
            }
            SharedPreferences sharedPreferences = contextA.getSharedPreferences("auth", 0);
            String string = sharedPreferences.getString("my_uid", "");
            String string2 = sharedPreferences.getString("refresh_token", "");
            if (string2 != null && string2.length() > 0) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("refresh_token", string2);
                    e.c cVarH = e.h("POST", "/auth/refresh", jSONObject, null);
                    int i2 = cVarH.f5348a;
                    if (i2 >= 200 && i2 < 300) {
                        return a(sharedPreferences, contextA, string, cVarH.f5349b);
                    }
                    if (i2 == 401 || i2 == 403) {
                        sharedPreferences.edit().remove("refresh_token").apply();
                    }
                } catch (Exception unused) {
                }
            }
            String string3 = sharedPreferences.getString("saved_username", null);
            String string4 = sharedPreferences.getString("saved_password", null);
            if (string3 != null && string3.length() != 0 && string4 != null && string4.length() != 0) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("identifier", string3);
                jSONObject2.put("password", string4);
                jSONObject2.put("device_id", AbstractC0445k.b(contextA));
                jSONObject2.put("imei", AbstractC0445k.d(contextA));
                jSONObject2.put("device_name", AbstractC0445k.c());
                jSONObject2.put("platform", "android");
                jSONObject2.put("app_version", AbstractC0445k.a(contextA));
                e.c cVarH2 = e.h("POST", "/auth/login", jSONObject2, null);
                int i3 = cVarH2.f5348a;
                if (i3 >= 200 && i3 < 300) {
                    return a(sharedPreferences, contextA, string, cVarH2.f5349b);
                }
            }
        } catch (Exception unused2) {
        }
        return null;
    }
}

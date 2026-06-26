package o0;

import g0.d;
import org.json.JSONObject;
import org.spongycastle.i18n.TextBundle;

/* JADX INFO: loaded from: classes.dex */
public abstract class W {

    public class a implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b f7039a;

        public a(b bVar) {
            this.f7039a = bVar;
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                if (str == null) {
                    str = "{}";
                }
                String strOptString = new JSONObject(str).optString(TextBundle.TEXT_ENTRY, "");
                if (strOptString != null) {
                    strOptString = strOptString.trim();
                }
                if (strOptString != null && strOptString.length() != 0) {
                    b bVar = this.f7039a;
                    if (bVar != null) {
                        bVar.b(strOptString);
                        return;
                    }
                    return;
                }
                W.c(this.f7039a, "empty_result");
            } catch (Exception unused) {
                W.c(this.f7039a, "invalid_response");
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            W.c(this.f7039a, W.d(i2, str));
        }
    }

    public interface b {
        void a(String str);

        void b(String str);
    }

    public static void c(b bVar, String str) {
        if (bVar != null) {
            if (str == null) {
                str = "transcribe_failed";
            }
            bVar.a(str);
        }
    }

    public static String d(int i2, String str) {
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String strOptString = jSONObject.optString("error", "");
                if (strOptString != null) {
                    strOptString = strOptString.trim();
                }
                if (strOptString != null && strOptString.length() > 0) {
                    return strOptString;
                }
                String strOptString2 = jSONObject.optString("code", "");
                if (strOptString2 != null) {
                    strOptString2 = strOptString2.trim();
                }
                if (strOptString2 != null) {
                    if (strOptString2.length() > 0) {
                        return strOptString2;
                    }
                }
            } catch (Exception unused) {
            }
        }
        return "http_" + i2;
    }

    public static void e(String str, byte[] bArr, String str2, String str3, b bVar) {
        if (bArr == null || bArr.length == 0) {
            c(bVar, "empty_audio");
            return;
        }
        if (str2 == null || str2.length() == 0) {
            str2 = "voice.3gp";
        }
        String str4 = str2;
        if (str3 == null || str3.length() == 0) {
            str3 = "audio/3gpp";
        }
        g0.d.T("/voice/asr", bArr, str4, str3, str, new a(bVar));
    }
}

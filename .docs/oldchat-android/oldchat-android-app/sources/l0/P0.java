package l0;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.im.oldchat.ui.UserSpaceActivity;
import d.AbstractC0235a;
import java.util.List;
import java.util.Locale;
import o0.AbstractC0434b0;
import o0.AbstractC0455v;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class P0 extends com.im.oldchat.ui.g0 {

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            P0.this.p0(true);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            P0.this.o0(true);
        }
    }

    public void A0() {
        if (this.f4576O) {
            return;
        }
        O0.e((UserSpaceActivity) this, this.f4569H, this.f4570I, this.f4577P, this.f4574M, this.f4578Q, this.f4579R, this.f4573L);
    }

    @Override // com.im.oldchat.ui.g0
    public boolean f0(String str) {
        String str2;
        String str3 = "";
        if (str != null && str.length() != 0) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String strOptString = jSONObject.optString("uid", "");
                String strM0 = m0(strOptString);
                String strM02 = m0(this.f4574M);
                if (strM02.length() > 0 && strM0.length() > 0 && !strM02.equalsIgnoreCase(strM0)) {
                    AbstractC0434b0.c(this, strM02, strM0);
                }
                if (strM0.length() > 0) {
                    this.f4574M = strM0;
                }
                String strOptString2 = jSONObject.optString("display_name", "");
                String strOptString3 = jSONObject.optString("user_title", "");
                String strOptString4 = jSONObject.optString("avatar_url", "");
                String strOptString5 = jSONObject.optString("signature", "");
                String strOptString6 = jSONObject.optString("cover_url", "");
                if (strOptString2 != null && strOptString2.length() != 0) {
                    strOptString = strOptString2;
                }
                if ((strOptString == null || strOptString.length() == 0) && (str2 = this.f4574M) != null) {
                    strOptString = str2;
                }
                if (strOptString == null) {
                    strOptString = "";
                }
                this.f4578Q = strOptString;
                this.f4579R = strOptString4 == null ? "" : strOptString4;
                if (strM0.length() > 0) {
                    j0.x.d(this, strM0, this.f4578Q);
                    j0.y.c(this, strM0, strOptString3);
                }
                TextView textView = this.f4586z;
                if (textView != null) {
                    textView.setText(this.f4578Q);
                }
                TextView textView2 = this.f4563B;
                if (textView2 != null) {
                    if (strM0.length() != 0) {
                        str3 = "UID: " + strM0;
                    }
                    textView2.setText(str3);
                }
                R0.b(this.f4562A, strOptString3);
                w0(strOptString5);
                ImageView imageView = this.f4585y;
                if (imageView != null) {
                    AbstractC0455v.h(imageView, strOptString4);
                }
                u0(strOptString6);
                A0();
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    @Override // com.im.oldchat.ui.g0
    public boolean i0(int i2, String str) {
        if (i2 != 404) {
            return false;
        }
        return y0(str, "user_not_found") || y0(str, "not_found");
    }

    @Override // com.im.oldchat.ui.g0
    public void j0() {
        this.f4581T = false;
        String strZ0 = z0();
        if (strZ0 != null && strZ0.length() > 0 && f0(strZ0)) {
            this.f4581T = true;
        } else if (v0()) {
            this.f4581T = true;
        }
    }

    @Override // com.im.oldchat.ui.g0
    public void n0(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        getSharedPreferences("user_space_profile_cache", 0).edit().putString(x0(), str).apply();
    }

    @Override // com.im.oldchat.ui.g0
    public void q0() {
        ListView listView = this.f4583w;
        if (listView != null) {
            listView.postDelayed(new b(), 500L);
        } else {
            o0(true);
        }
    }

    @Override // com.im.oldchat.ui.g0
    public void r0() {
        ListView listView = this.f4583w;
        if (listView != null) {
            listView.postDelayed(new a(), 500L);
        } else {
            p0(true);
        }
    }

    @Override // com.im.oldchat.ui.g0
    public boolean s0(int i2, String str, boolean z2) {
        String strM0;
        return (z2 || this.f4582U || this.f4576O || !i0(i2, str) || (strM0 = m0(this.f4574M)) == null || strM0.length() <= 0) ? false : true;
    }

    @Override // com.im.oldchat.ui.g0
    public boolean t0(int i2, String str, boolean z2) {
        if (z2) {
            return false;
        }
        if (i2 <= 0 || i2 == 408 || i2 == 429 || i2 == 500 || i2 == 502 || i2 == 503 || i2 == 504) {
            return true;
        }
        return i2 == 404 && !i0(i2, str);
    }

    public void u0(String str) {
        if (this.f4584x == null) {
            return;
        }
        if (str == null || str.length() == 0) {
            this.f4584x.setImageDrawable(null);
            this.f4584x.setBackgroundColor(AbstractC0235a.b(this, f0.d.f4971h));
        } else {
            this.f4584x.setBackgroundColor(0);
            AbstractC0455v.e(this.f4584x, str);
        }
    }

    public boolean v0() {
        String str;
        List listB;
        String str2;
        if (!this.f4576O && (str = this.f4574M) != null && str.length() != 0 && (listB = j0.c.b(this)) != null && !listB.isEmpty()) {
            for (int i2 = 0; i2 < listB.size(); i2++) {
                j0.w wVar = (j0.w) listB.get(i2);
                if (wVar != null && (str2 = wVar.f5646b) != null && str2.length() != 0 && this.f4574M.equalsIgnoreCase(wVar.f5646b)) {
                    String str3 = wVar.f5648d;
                    if (str3 == null || str3.length() == 0) {
                        str3 = wVar.f5647c;
                    }
                    if (str3 == null || str3.length() == 0) {
                        str3 = wVar.f5646b;
                    }
                    this.f4578Q = str3;
                    this.f4579R = wVar.f5651g;
                    TextView textView = this.f4586z;
                    if (textView != null) {
                        textView.setText(str3);
                    }
                    TextView textView2 = this.f4563B;
                    if (textView2 != null) {
                        textView2.setText("UID: " + wVar.f5646b);
                    }
                    R0.b(this.f4562A, wVar.f5650f);
                    w0(wVar.f5652h);
                    ImageView imageView = this.f4585y;
                    if (imageView != null) {
                        AbstractC0455v.h(imageView, wVar.f5651g);
                    }
                    u0("");
                    A0();
                    return true;
                }
            }
        }
        return false;
    }

    public void w0(String str) {
        if (this.f4564C == null) {
            return;
        }
        if (str == null || str.length() == 0) {
            this.f4564C.setText("暂无签名");
            this.f4564C.setTextColor(AbstractC0235a.b(this, f0.d.f4974k));
        } else {
            this.f4564C.setText(str);
            this.f4564C.setTextColor(AbstractC0235a.b(this, f0.d.f4973j));
        }
    }

    public String x0() {
        String strM0 = m0(this.f4574M);
        if (strM0.length() == 0) {
            strM0 = m0(this.f4575N);
        }
        if (strM0.length() == 0) {
            strM0 = "self";
        }
        return "profile_" + strM0.toUpperCase(Locale.US);
    }

    public boolean y0(String str, String str2) {
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            return false;
        }
        Locale locale = Locale.US;
        String lowerCase = str.toLowerCase(locale);
        String lowerCase2 = str2.toLowerCase(locale);
        if (lowerCase.contains(lowerCase2)) {
            return true;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (lowerCase2.equalsIgnoreCase(jSONObject.optString("error", ""))) {
                return true;
            }
            String strOptString = jSONObject.optString("message", "");
            if (strOptString != null) {
                return strOptString.toLowerCase(locale).contains(lowerCase2);
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public String z0() {
        return getSharedPreferences("user_space_profile_cache", 0).getString(x0(), "");
    }
}

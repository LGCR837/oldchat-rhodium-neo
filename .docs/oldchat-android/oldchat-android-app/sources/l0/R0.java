package l0;

import android.text.TextUtils;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public abstract class R0 {
    public static void a(TextView textView, String str) {
        c(textView, str, 0);
    }

    public static void b(TextView textView, String str) {
        c(textView, str, 8);
    }

    public static void c(TextView textView, String str, int i2) {
        if (textView == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            textView.setVisibility(8);
            return;
        }
        String strTrim = str.trim();
        if (strTrim.length() == 0) {
            textView.setVisibility(8);
            return;
        }
        String strD = d(strTrim);
        if (strD.length() == 0) {
            textView.setVisibility(8);
            return;
        }
        if (i2 > 0 && strD.length() > i2) {
            strD = strD.substring(0, i2) + "…";
        }
        textView.setText(strD);
        textView.setTextColor(textView.getResources().getColor(f0.d.f4975l));
        textView.setBackgroundResource(f0.e.f5039z);
        textView.setVisibility(0);
    }

    public static String d(String str) {
        if (str == null) {
            return "";
        }
        String strTrim = str.replace('\n', ' ').replace('\r', ' ').replace('\t', ' ').trim();
        while (strTrim.contains("  ")) {
            strTrim = strTrim.replace("  ", " ");
        }
        return strTrim;
    }
}

package l0;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.widget.EditText;

/* JADX INFO: renamed from: l0.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0372c {
    public static String a(Context context, EditText editText, String str) {
        String string;
        if (context == null || editText == null || str == null || str.length() == 0 || (string = context.getSharedPreferences("drafts", 0).getString(str, "")) == null || string.length() == 0) {
            return "";
        }
        Editable text = editText.getText();
        if (text != null && text.length() > 0) {
            return text.toString();
        }
        editText.setText(string);
        editText.setSelection(string.length());
        return string;
    }

    public static String b(Context context, String str, String str2, String str3) {
        if (context == null || str == null || str.length() == 0) {
            return str3 == null ? "" : str3;
        }
        if (str2 == null) {
            str2 = "";
        }
        if (str3 == null) {
            str3 = "";
        }
        if (str2.equals(str3)) {
            return str3;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("drafts", 0);
        if (str2.trim().length() == 0) {
            sharedPreferences.edit().remove(str).apply();
        } else {
            sharedPreferences.edit().putString(str, str2).apply();
        }
        return str2;
    }

    public static String c(Context context, EditText editText, String str, String str2) {
        if (editText == null) {
            return str2 == null ? "" : str2;
        }
        Editable text = editText.getText();
        return b(context, str, text != null ? text.toString() : "", str2);
    }
}

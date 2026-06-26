package l0;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.QrCardActivity;

/* JADX INFO: loaded from: classes.dex */
public abstract class y0 {
    public static void a(Activity activity, String str, String str2, TextView textView) {
        if (activity == null) {
            return;
        }
        String strTrim = str == null ? "" : str.trim();
        if (strTrim.length() == 0) {
            String string = activity.getSharedPreferences("auth", 0).getString("my_uid", "");
            strTrim = string == null ? "" : string.trim();
        }
        if (strTrim.length() == 0) {
            Toast.makeText(activity, "暂无可用的UID", 0).show();
            return;
        }
        if (str2 == null || str2.trim().length() == 0) {
            str2 = (textView == null || textView.getText() == null) ? "" : textView.getText().toString();
        }
        Intent intent = new Intent(activity, (Class<?>) QrCardActivity.class);
        intent.putExtra("qr_mode", "user");
        intent.putExtra("qr_id", strTrim);
        intent.putExtra("qr_name", str2 != null ? str2.trim() : "");
        activity.startActivity(intent);
    }
}

package l0;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;
import g0.d;
import o.c;
import org.json.JSONObject;
import org.spongycastle.asn1.x509.DisplayText;

/* JADX INFO: loaded from: classes.dex */
public abstract class O {

    public class a implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f5943a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Context f5944b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f5945c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f5946d;

        public a(EditText editText, Context context, String str, String str2) {
            this.f5943a = editText;
            this.f5944b = context;
            this.f5945c = str;
            this.f5946d = str2;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            O.h(this.f5944b, this.f5945c, this.f5943a.getText() == null ? "" : this.f5943a.getText().toString().trim(), this.f5946d);
        }
    }

    public class b implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f5947a;

        public b(Context context) {
            this.f5947a = context;
        }

        @Override // g0.d.i
        public void b(String str) {
            Toast.makeText(this.f5947a, "举报已提交，已进入公开法庭", 0).show();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(this.f5947a, "举报失败: " + i2, 0).show();
        }
    }

    public static String b(j0.k kVar) {
        StringBuilder sb = new StringBuilder();
        sb.append("消息类型: ");
        sb.append(f(kVar.f5554e));
        if (!TextUtils.isEmpty(kVar.f5550a)) {
            sb.append("\n消息ID: ");
            sb.append(kVar.f5550a);
        }
        if (!TextUtils.isEmpty(kVar.f5553d)) {
            sb.append("\n内容: ");
            sb.append(i(kVar.f5553d, 120));
        }
        if (!TextUtils.isEmpty(kVar.f5555f)) {
            sb.append("\n媒体: ");
            sb.append(i(kVar.f5555f, DisplayText.DISPLAY_TEXT_MAXIMUM_SIZE));
        }
        return sb.toString();
    }

    public static String c(j0.i iVar) {
        StringBuilder sb = new StringBuilder();
        sb.append("群ID: ");
        sb.append(f(iVar.f5530b));
        sb.append("\n消息类型: ");
        sb.append(f(iVar.f5533e));
        if (!TextUtils.isEmpty(iVar.f5529a)) {
            sb.append("\n消息ID: ");
            sb.append(iVar.f5529a);
        }
        if (!TextUtils.isEmpty(iVar.f5532d)) {
            sb.append("\n内容: ");
            sb.append(i(iVar.f5532d, 120));
        }
        if (!TextUtils.isEmpty(iVar.f5534f)) {
            sb.append("\n媒体: ");
            sb.append(i(iVar.f5534f, DisplayText.DISPLAY_TEXT_MAXIMUM_SIZE));
        }
        return sb.toString();
    }

    public static void d(Context context, j0.k kVar) {
        if (context == null || kVar == null) {
            return;
        }
        String strF = f(kVar.f5552c);
        if (strF.isEmpty()) {
            Toast.makeText(context, "无法举报该消息", 0).show();
        } else {
            g(context, strF, b(kVar));
        }
    }

    public static void e(Context context, j0.i iVar) {
        if (context == null || iVar == null) {
            return;
        }
        String strF = f(iVar.f5531c);
        if (strF.isEmpty()) {
            Toast.makeText(context, "无法举报该消息", 0).show();
        } else {
            g(context, strF, c(iVar));
        }
    }

    public static String f(String str) {
        return str == null ? "" : str.trim();
    }

    public static void g(Context context, String str, String str2) {
        EditText editText = new EditText(context);
        editText.setHint("如：骚扰、诈骗、辱骂");
        int i2 = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
        editText.setPadding(i2, i2, i2, i2);
        new c.a(context).t("举报消息").u(editText).q("提交", new a(editText, context, str, str2)).k("取消", null).v();
    }

    public static void h(Context context, String str, String str2, String str3) {
        if (context == null) {
            return;
        }
        String string = context.getSharedPreferences("auth", 0).getString("access_token", "");
        if (string == null || string.length() == 0) {
            Toast.makeText(context, "请先登录", 0).show();
            return;
        }
        if (str2 == null) {
            str2 = "";
        }
        if (str3 != null && str3.length() > 0) {
            if (!TextUtils.isEmpty(str2)) {
                str2 = str2 + "\n";
            }
            str2 = str2 + str3;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("target_uid", str);
            jSONObject.put("reason", str2);
            g0.d.S("/reports/user", jSONObject, string, new b(context));
        } catch (Exception unused) {
            Toast.makeText(context, "举报失败", 0).show();
        }
    }

    public static String i(String str, int i2) {
        if (str == null) {
            return "";
        }
        String strTrim = str.trim();
        if (strTrim.length() <= i2) {
            return strTrim;
        }
        return strTrim.substring(0, i2) + "...";
    }
}

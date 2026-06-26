package l0;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.ChatActivity;
import com.im.oldchat.ui.UserSpaceActivity;
import g0.d;
import java.util.Iterator;
import o.c;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class O0 {

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ UserSpaceActivity f5948a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f5949b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f5950c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f5951d;

        public a(UserSpaceActivity userSpaceActivity, String str, String str2, String str3) {
            this.f5948a = userSpaceActivity;
            this.f5949b = str;
            this.f5950c = str2;
            this.f5951d = str3;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            O0.g(this.f5948a, this.f5949b, this.f5950c, this.f5951d);
        }
    }

    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ UserSpaceActivity f5952a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f5953b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f5954c;

        public b(UserSpaceActivity userSpaceActivity, String str, String str2) {
            this.f5952a = userSpaceActivity;
            this.f5953b = str;
            this.f5954c = str2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            O0.h(this.f5952a, this.f5953b, this.f5954c);
        }
    }

    public class c implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ UserSpaceActivity f5955a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f5956b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f5957c;

        public c(UserSpaceActivity userSpaceActivity, String str, String str2) {
            this.f5955a = userSpaceActivity;
            this.f5956b = str;
            this.f5957c = str2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            O0.i(this.f5955a, this.f5956b, this.f5957c);
        }
    }

    public class d implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f5958a;

        public d(Context context) {
            this.f5958a = context;
        }

        @Override // g0.d.i
        public void b(String str) {
            Toast.makeText(this.f5958a, f0.j.s0, 0).show();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            if (i2 != 409) {
                Toast.makeText(this.f5958a, f0.j.p0, 0).show();
            } else if (AbstractC0410z.b(str)) {
                Toast.makeText(this.f5958a, f0.j.r0, 0).show();
            } else {
                Toast.makeText(this.f5958a, f0.j.f5230n0, 0).show();
            }
        }
    }

    public class e implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ UserSpaceActivity f5959a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f5960b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f5961c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ EditText f5962d;

        public e(UserSpaceActivity userSpaceActivity, String str, String str2, EditText editText) {
            this.f5959a = userSpaceActivity;
            this.f5960b = str;
            this.f5961c = str2;
            this.f5962d = editText;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            O0.j(this.f5959a, this.f5960b, this.f5961c, this.f5962d.getText().toString().trim());
        }
    }

    public class f implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f5963a;

        public f(Context context) {
            this.f5963a = context;
        }

        @Override // g0.d.i
        public void b(String str) {
            Toast.makeText(this.f5963a, "举报已提交，已进入公开法庭", 0).show();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(this.f5963a, "举报失败: " + i2, 0).show();
        }
    }

    public static void e(UserSpaceActivity userSpaceActivity, View view, View view2, boolean z2, String str, String str2, String str3, String str4) {
        if (userSpaceActivity == null) {
            return;
        }
        if (view != null) {
            if (z2) {
                if (view instanceof TextView) {
                    ((TextView) view).setText("发消息");
                }
                view.setOnClickListener(new a(userSpaceActivity, str, str2, str3));
            } else {
                if (view instanceof TextView) {
                    ((TextView) view).setText("加好友");
                }
                view.setOnClickListener(new b(userSpaceActivity, str4, str));
            }
        }
        if (view2 != null) {
            view2.setOnClickListener(new c(userSpaceActivity, str4, str));
        }
    }

    public static boolean f(Context context, String str) {
        if (context != null && str != null && !str.isEmpty()) {
            Iterator it = j0.c.b(context).iterator();
            while (it.hasNext()) {
                if (str.equals(((j0.w) it.next()).f5646b)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void g(Context context, String str, String str2, String str3) {
        if (context == null || str == null || str.isEmpty()) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) ChatActivity.class);
        intent.putExtra("friend_uid", str);
        intent.putExtra("friend_name", str2);
        intent.putExtra("friend_avatar", str3);
        context.startActivity(intent);
    }

    public static void h(Context context, String str, String str2) {
        if (context == null || str2 == null || str2.isEmpty()) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("to_uid", str2);
            g0.d.S("/friends/request", jSONObject, str, new d(context));
        } catch (Exception unused) {
            Toast.makeText(context, f0.j.p0, 0).show();
        }
    }

    public static void i(UserSpaceActivity userSpaceActivity, String str, String str2) {
        if (userSpaceActivity == null || str2 == null || str2.isEmpty()) {
            return;
        }
        EditText editText = new EditText(userSpaceActivity);
        editText.setHint("如：骚扰、诈骗、辱骂");
        int i2 = (int) (userSpaceActivity.getResources().getDisplayMetrics().density * 16.0f);
        editText.setPadding(i2, i2, i2, i2);
        new c.a(userSpaceActivity).t("举报用户").u(editText).q("提交", new e(userSpaceActivity, str, str2, editText)).k("取消", null).v();
    }

    public static void j(Context context, String str, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("target_uid", str2);
            if (str3 == null) {
                str3 = "";
            }
            jSONObject.put("reason", str3);
            g0.d.S("/reports/user", jSONObject, str, new f(context));
        } catch (Exception unused) {
            Toast.makeText(context, "举报失败", 0).show();
        }
    }
}

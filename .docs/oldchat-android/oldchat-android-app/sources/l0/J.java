package l0;

import android.content.DialogInterface;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;
import com.im.oldchat.ui.ChatSearchActivity;
import g0.d;
import o.c;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class J extends com.im.oldchat.ui.B {

    public class a implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f5912a;

        public a(EditText editText) {
            this.f5912a = editText;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            J.this.u0(this.f5912a.getText().toString().trim());
        }
    }

    public class b implements d.i {
        public b() {
        }

        @Override // g0.d.i
        public void b(String str) {
            Toast.makeText(J.this, "举报已提交", 0).show();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(J.this, "举报失败: " + i2, 0).show();
        }
    }

    public void s0() {
        String str = this.f2408T;
        if (str == null || str.length() == 0) {
            Toast.makeText(this, "群ID无效", 0).show();
            return;
        }
        Intent intent = new Intent(this, (Class<?>) ChatSearchActivity.class);
        intent.putExtra("mode", "group");
        intent.putExtra("group_id", this.f2408T);
        intent.putExtra("group_name", this.f2409U);
        intent.putExtra("group_role", this.f2412X);
        startActivity(intent);
    }

    public void t0() {
        String str = this.f2408T;
        if (str == null || str.length() == 0) {
            Toast.makeText(this, "群ID无效", 0).show();
            return;
        }
        EditText editText = new EditText(this);
        editText.setHint("如：违规内容、诈骗、骚扰");
        int i2 = (int) (getResources().getDisplayMetrics().density * 16.0f);
        editText.setPadding(i2, i2, i2, i2);
        new c.a(this).t("举报群聊").u(editText).q("提交", new a(editText)).k("取消", null).v();
    }

    public void u0(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("group_id", this.f2408T);
            if (str == null) {
                str = "";
            }
            jSONObject.put("reason", str);
            g0.d.S("/reports/group", jSONObject, this.f2407S, new b());
        } catch (Exception unused) {
            Toast.makeText(this, "举报失败", 0).show();
        }
    }
}

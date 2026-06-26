package l0;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.im.oldchat.service.ResourceUploadService;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class E0 extends com.im.oldchat.ui.e0 {

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public final BroadcastReceiver f5803S = new a();

    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            String action = intent.getAction();
            String stringExtra = intent.getStringExtra("section_id");
            String str = E0.this.f4498C;
            if (str == null || stringExtra == null || !str.equals(stringExtra)) {
                return;
            }
            if (!"com.im.oldchat.action.RESOURCE_UPLOAD_PROGRESS".equals(action)) {
                if ("com.im.oldchat.action.RESOURCE_UPLOAD_DONE".equals(action)) {
                    E0.this.n0(intent.getStringExtra("response"));
                    return;
                } else {
                    if ("com.im.oldchat.action.RESOURCE_UPLOAD_ERROR".equals(action)) {
                        E0.this.m0(intent.getIntExtra("error_code", -1), intent.getStringExtra("error_message"));
                        return;
                    }
                    return;
                }
            }
            String stringExtra2 = intent.getStringExtra("file_name");
            int intExtra = intent.getIntExtra("progress", 0);
            boolean booleanExtra = intent.getBooleanExtra("indeterminate", false);
            long longExtra = intent.getLongExtra("uploaded_bytes", 0L);
            long longExtra2 = intent.getLongExtra("total_bytes", 0L);
            long longExtra3 = intent.getLongExtra("speed_bps", 0L);
            E0 e02 = E0.this;
            e02.f4512Q = true;
            e02.s0(stringExtra2, intExtra, booleanExtra, longExtra, longExtra2, longExtra3);
        }
    }

    public void m0(int i2, String str) {
        this.f4512Q = false;
        o0();
        if (g0.e.v(i2, str)) {
            return;
        }
        if (i2 == -1 && "network_unavailable".equals(str)) {
            Toast.makeText(this, "网络不可用", 0).show();
            return;
        }
        if (i2 == -2) {
            Toast.makeText(this, "已有上传任务", 0).show();
            return;
        }
        if (str != null && str.contains("quota_exceeded")) {
            Toast.makeText(this, "空间不足：单个账号资源总容量上限为10GB", 1).show();
            p0();
            return;
        }
        Toast.makeText(this, "上传失败: " + i2, 0).show();
        p0();
    }

    public void n0(String str) {
        this.f4512Q = false;
        o0();
        if (str == null) {
            Toast.makeText(this, "上传失败", 0).show();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            j0.t tVar = new j0.t();
            tVar.f5618a = jSONObject.optString("id");
            tVar.f5619b = jSONObject.optString("section_id");
            tVar.f5620c = jSONObject.optString("name");
            tVar.f5621d = jSONObject.optString("url");
            tVar.f5622e = jSONObject.optLong("size_bytes", 0L);
            tVar.f5623f = jSONObject.optString("uploader_uid");
            tVar.f5624g = jSONObject.optString("uploader_name");
            tVar.f5625h = jSONObject.optString("uploader_title");
            tVar.f5626i = jSONObject.optString("uploader_avatar");
            tVar.f5627j = jSONObject.optLong("created_at");
            tVar.f5628k = jSONObject.optInt("likes", 0);
            tVar.f5629l = jSONObject.optInt("comments", 0);
            tVar.f5630m = jSONObject.optBoolean("liked", false);
            tVar.f5631n = f0(tVar);
            this.f4517z.add(0, tVar);
            this.f4516y.notifyDataSetChanged();
            Toast.makeText(this, "上传成功", 0).show();
        } catch (Exception unused) {
            Toast.makeText(this, "上传失败", 0).show();
        }
        r0();
        p0();
    }

    public void o0() {
        View view = this.f4503H;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    public abstract void p0();

    public void q0() {
        String str;
        ResourceUploadService.e eVarK = ResourceUploadService.k();
        if (eVarK == null || !eVarK.f2343g || (str = this.f4498C) == null || !str.equals(eVarK.f2337a)) {
            this.f4512Q = false;
            o0();
        } else {
            this.f4512Q = true;
            s0(eVarK.f2338b, eVarK.f2341e, eVarK.f2342f, eVarK.f2340d, eVarK.f2339c, eVarK.f2344h);
        }
    }

    public abstract void r0();

    public void s0(String str, int i2, boolean z2, long j2, long j3, long j4) {
        View view = this.f4503H;
        if (view == null) {
            return;
        }
        view.setVisibility(0);
        ProgressBar progressBar = this.f4504I;
        if (progressBar != null) {
            progressBar.setIndeterminate(z2);
            if (!z2) {
                this.f4504I.setProgress(i2);
            }
        }
        if (this.f4505J != null) {
            if (str == null || str.length() == 0) {
                str = "资源";
            }
            if (z2) {
                this.f4505J.setText("后台上传中：" + str + " " + j0(j4));
                return;
            }
            String strI0 = i0(j2, j3);
            String strJ0 = j0(j4);
            this.f4505J.setText("后台上传中：" + str + " " + i2 + "% " + strI0 + " " + strJ0);
        }
    }
}

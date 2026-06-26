package l0;

import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;
import com.im.oldchat.ui.MomentCommentsActivity;
import com.im.oldchat.ui.UserSpaceActivity;
import g0.d;
import o.c;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class Q0 extends P0 {

    public class a implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.m f5976a;

        public a(j0.m mVar) {
            this.f5976a = mVar;
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                j0.m mVar = this.f5976a;
                mVar.f5577k = jSONObject.optBoolean("liked", mVar.f5577k);
                j0.m mVar2 = this.f5976a;
                mVar2.f5575i = jSONObject.optInt("likes", mVar2.f5575i);
                Q0.this.f4571J.notifyDataSetChanged();
            } catch (Exception unused) {
                Q0.this.f4571J.notifyDataSetChanged();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(Q0.this, "操作失败", 0).show();
        }
    }

    public class b implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.m f5978a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f5979b;

        public b(j0.m mVar, int i2) {
            this.f5978a = mVar;
            this.f5979b = i2;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            Q0.this.B0(this.f5978a, this.f5979b);
        }
    }

    public class c implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5981a;

        public c(int i2) {
            this.f5981a = i2;
        }

        @Override // g0.d.i
        public void b(String str) {
            int i2 = this.f5981a;
            if (i2 >= 0 && i2 < Q0.this.f4572K.size()) {
                Q0.this.f4572K.remove(this.f5981a);
                Q0.this.f4571J.notifyDataSetChanged();
            }
            Toast.makeText(Q0.this, "已删除", 0).show();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(Q0.this, "删除失败: " + i2, 0).show();
        }
    }

    public void B0(j0.m mVar, int i2) {
        String str;
        if (mVar == null || (str = mVar.f5567a) == null || str.isEmpty()) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("moment_id", mVar.f5567a);
            g0.d.S("/moments/delete", jSONObject, this.f4573L, new c(i2));
        } catch (Exception unused) {
            Toast.makeText(this, "删除失败", 0).show();
        }
    }

    @Override // com.im.oldchat.ui.g0
    public boolean g0(j0.m mVar) {
        String str;
        if (!this.f4576O || mVar == null || (str = this.f4575N) == null || str.isEmpty()) {
            return false;
        }
        return this.f4575N.equals(mVar.f5568b);
    }

    @Override // com.im.oldchat.ui.g0
    public void h0(j0.m mVar, int i2) {
        new c.a(this).t("删除动态").i("确定删除这条动态吗？").q("删除", new b(mVar, i2)).k("取消", null).v();
    }

    public void l(j0.m mVar) {
        if (mVar == null || mVar.f5567a == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("moment_id", mVar.f5567a);
            g0.d.S(mVar.f5577k ? "/moments/unlike" : "/moments/like", jSONObject, this.f4573L, new a(mVar));
        } catch (Exception unused) {
            Toast.makeText(this, "操作失败", 0).show();
        }
    }

    public void o(j0.m mVar) {
        if (mVar == null || mVar.f5567a == null) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) MomentCommentsActivity.class);
        intent.putExtra("moment_id", mVar.f5567a);
        intent.putExtra("moment_owner_uid", mVar.f5568b);
        startActivity(intent);
    }

    public void q(j0.m mVar) {
        String str;
        if (mVar == null || (str = mVar.f5568b) == null || str.isEmpty() || mVar.f5568b.equalsIgnoreCase(this.f4574M)) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) UserSpaceActivity.class);
        intent.putExtra("uid", mVar.f5568b);
        startActivity(intent);
    }
}

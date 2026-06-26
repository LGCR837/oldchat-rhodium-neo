package l0;

import android.content.DialogInterface;
import android.widget.Toast;
import g0.d;
import o.c;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class I0 extends H0 {

    public class a implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.u f5908a;

        public a(j0.u uVar) {
            this.f5908a = uVar;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            I0.this.r0(this.f5908a);
        }
    }

    public class b implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.u f5910a;

        public b(j0.u uVar) {
            this.f5910a = uVar;
        }

        @Override // g0.d.i
        public void b(String str) {
            I0.this.f5866A.remove(this.f5910a);
            I0.this.f5873z.notifyDataSetChanged();
            Toast.makeText(I0.this, "已删除", 0).show();
            I0.this.i0();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(I0.this, "删除失败: " + i2, 0).show();
        }
    }

    public boolean p0(j0.u uVar) {
        String str;
        if (uVar == null || (str = this.f5868C) == null || str.isEmpty()) {
            return false;
        }
        if (uVar.f5640i) {
            return true;
        }
        return this.f5868C.equals(uVar.f5634c);
    }

    public void q0(j0.u uVar) {
        new c.a(this).t("删除分区").i("删除后分区内资源将一并移除，是否继续？").q("删除", new a(uVar)).k("取消", null).v();
    }

    public void r0(j0.u uVar) {
        String str;
        if (uVar == null || (str = uVar.f5632a) == null || str.isEmpty()) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("section_id", uVar.f5632a);
            g0.d.S("/resources/sections/delete", jSONObject, this.f5867B, new b(uVar));
        } catch (Exception unused) {
            Toast.makeText(this, "删除失败", 0).show();
        }
    }
}

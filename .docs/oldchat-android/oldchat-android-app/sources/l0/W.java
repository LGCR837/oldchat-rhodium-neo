package l0;

import android.app.Activity;
import android.content.DialogInterface;
import android.widget.Toast;
import g0.d;
import java.util.List;
import o.c;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class W {

    public class a implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f5990a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f5991b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ j0.m f5992c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f5993d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ List f5994e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ com.im.oldchat.ui.X f5995f;

        public a(Activity activity, String str, j0.m mVar, int i2, List list, com.im.oldchat.ui.X x2) {
            this.f5990a = activity;
            this.f5991b = str;
            this.f5992c = mVar;
            this.f5993d = i2;
            this.f5994e = list;
            this.f5995f = x2;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            W.c(this.f5990a, this.f5991b, this.f5992c, this.f5993d, this.f5994e, this.f5995f);
        }
    }

    public class b implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5996a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ List f5997b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ com.im.oldchat.ui.X f5998c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Activity f5999d;

        public b(int i2, List list, com.im.oldchat.ui.X x2, Activity activity) {
            this.f5996a = i2;
            this.f5997b = list;
            this.f5998c = x2;
            this.f5999d = activity;
        }

        @Override // g0.d.i
        public void b(String str) {
            int i2 = this.f5996a;
            if (i2 >= 0 && i2 < this.f5997b.size()) {
                this.f5997b.remove(this.f5996a);
                this.f5998c.notifyDataSetChanged();
            }
            Toast.makeText(this.f5999d, "已删除", 0).show();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(this.f5999d, "删除失败: " + i2, 0).show();
        }
    }

    public static void b(Activity activity, String str, j0.m mVar, int i2, List list, com.im.oldchat.ui.X x2) {
        new c.a(activity).t("删除动态").i("确定删除这条动态吗？").q("删除", new a(activity, str, mVar, i2, list, x2)).k("取消", null).v();
    }

    public static void c(Activity activity, String str, j0.m mVar, int i2, List list, com.im.oldchat.ui.X x2) {
        String str2;
        if (mVar == null || (str2 = mVar.f5567a) == null || str2.isEmpty()) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("moment_id", mVar.f5567a);
            g0.d.S("/moments/delete", jSONObject, str, new b(i2, list, x2, activity));
        } catch (Exception unused) {
            Toast.makeText(activity, "删除失败", 0).show();
        }
    }

    public static boolean d(String str, j0.m mVar) {
        if (mVar == null || str == null || str.isEmpty()) {
            return false;
        }
        return str.equals(mVar.f5568b);
    }
}

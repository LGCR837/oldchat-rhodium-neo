package m0;

import android.R;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import c.AbstractActivityC0167u;
import c.AbstractComponentCallbacksC0165t;
import g0.d;
import j0.r;
import j0.w;
import j0.x;
import java.util.List;
import o.c;
import org.json.JSONObject;
import x.U;

/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    public class a implements U.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ AbstractComponentCallbacksC0165t f6536a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ n f6537b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ boolean f6538c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ d f6539d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ String f6540e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ boolean f6541f;

        public a(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, n nVar, boolean z2, d dVar, String str, boolean z3) {
            this.f6536a = abstractComponentCallbacksC0165t;
            this.f6537b = nVar;
            this.f6538c = z2;
            this.f6539d = dVar;
            this.f6540e = str;
            this.f6541f = z3;
        }

        @Override // x.U.c
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (menuItem.getItemId() == 1) {
                AbstractActivityC0167u abstractActivityC0167uE = this.f6536a.e();
                n nVar = this.f6537b;
                o.c(abstractActivityC0167uE, nVar.f6568a, nVar.f6573f, this.f6538c);
                Toast.makeText(this.f6536a.e(), this.f6538c ? "已置顶" : "已取消置顶", 0).show();
                d dVar = this.f6539d;
                if (dVar != null) {
                    dVar.a();
                }
                return true;
            }
            if (menuItem.getItemId() == 2) {
                j.g(this.f6536a, this.f6537b, this.f6540e, this.f6539d);
                return true;
            }
            if (menuItem.getItemId() != 3) {
                return false;
            }
            AbstractActivityC0167u abstractActivityC0167uE2 = this.f6536a.e();
            n nVar2 = this.f6537b;
            m.c(abstractActivityC0167uE2, nVar2.f6568a, nVar2.f6573f, this.f6541f);
            Toast.makeText(this.f6536a.e(), this.f6541f ? "已折叠" : "已取消折叠", 0).show();
            d dVar2 = this.f6539d;
            if (dVar2 != null) {
                dVar2.a();
            }
            return true;
        }
    }

    public class b implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f6542a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ AbstractComponentCallbacksC0165t f6543b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ n f6544c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f6545d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ d f6546e;

        public b(EditText editText, AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, n nVar, String str, d dVar) {
            this.f6542a = editText;
            this.f6543b = abstractComponentCallbacksC0165t;
            this.f6544c = nVar;
            this.f6545d = str;
            this.f6546e = dVar;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            j.h(this.f6543b, this.f6544c.f6573f, this.f6542a.getText() == null ? "" : this.f6542a.getText().toString().trim(), this.f6545d, this.f6546e);
        }
    }

    public class c implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ AbstractComponentCallbacksC0165t f6547a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f6548b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f6549c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ d f6550d;

        public c(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, String str, String str2, d dVar) {
            this.f6547a = abstractComponentCallbacksC0165t;
            this.f6548b = str;
            this.f6549c = str2;
            this.f6550d = dVar;
        }

        @Override // g0.d.i
        public void b(String str) {
            if (this.f6547a.e() == null) {
                return;
            }
            w wVarE = j.e(this.f6547a, this.f6548b);
            String str2 = wVarE != null ? wVarE.f5648d : "";
            String str3 = wVarE != null ? wVarE.f5647c : "";
            String str4 = wVarE != null ? wVarE.f5651g : "";
            String strC = j0.d.c(this.f6549c, str2, str3, this.f6548b);
            j.i(this.f6547a, this.f6548b, this.f6549c);
            r.j(this.f6547a.e(), this.f6548b, strC, str4);
            x.d(this.f6547a.e(), this.f6548b, strC);
            AbstractActivityC0167u abstractActivityC0167uE = this.f6547a.e();
            String str5 = this.f6549c;
            Toast.makeText(abstractActivityC0167uE, (str5 == null || str5.length() == 0) ? "已清除备注" : "备注已更新", 0).show();
            d dVar = this.f6550d;
            if (dVar != null) {
                dVar.a();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (this.f6547a.e() == null || g0.e.v(i2, str)) {
                return;
            }
            if (str == null || !str.contains("remark_too_long")) {
                Toast.makeText(this.f6547a.e(), "设置备注失败", 0).show();
            } else {
                Toast.makeText(this.f6547a.e(), "备注最多32个字", 0).show();
            }
        }
    }

    public interface d {
        void a();
    }

    public static w e(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, String str) {
        List<w> listB;
        if (abstractComponentCallbacksC0165t != null && abstractComponentCallbacksC0165t.e() != null && str != null && !str.isEmpty() && (listB = j0.c.b(abstractComponentCallbacksC0165t.e())) != null && !listB.isEmpty()) {
            for (w wVar : listB) {
                if (wVar != null && str.equals(wVar.f5646b)) {
                    return wVar;
                }
            }
        }
        return null;
    }

    public static void f(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, View view, n nVar, String str, d dVar) {
        String str2;
        if (abstractComponentCallbacksC0165t == null || abstractComponentCallbacksC0165t.e() == null || nVar == null || (str2 = nVar.f6573f) == null || str2.isEmpty() || nVar.f6569b || nVar.f6570c) {
            return;
        }
        U u2 = new U(abstractComponentCallbacksC0165t.e(), view);
        boolean z2 = nVar.f6571d;
        boolean z3 = !z2;
        boolean z4 = nVar.f6572e;
        boolean z5 = !z4;
        u2.a().add(0, 1, 0, !z2 ? "置顶" : "取消置顶");
        if (!nVar.f6568a) {
            u2.a().add(0, 2, 1, "设置备注");
        }
        u2.a().add(0, 3, 2, !z4 ? "折叠" : "取消折叠");
        u2.c(new a(abstractComponentCallbacksC0165t, nVar, z3, dVar, str, z5));
        u2.d();
    }

    public static void g(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, n nVar, String str, d dVar) {
        String str2;
        if (abstractComponentCallbacksC0165t.e() == null) {
            return;
        }
        EditText editText = new EditText(abstractComponentCallbacksC0165t.e());
        editText.setHint(abstractComponentCallbacksC0165t.q(f0.j.a3));
        w wVarE = e(abstractComponentCallbacksC0165t, nVar.f6573f);
        if (wVarE != null && (str2 = wVarE.f5649e) != null && str2.trim().length() > 0) {
            String strTrim = wVarE.f5649e.trim();
            editText.setText(strTrim);
            editText.setSelection(strTrim.length());
        }
        new c.a(abstractComponentCallbacksC0165t.e()).s(f0.j.b3).u(editText).p(R.string.ok, new b(editText, abstractComponentCallbacksC0165t, nVar, str, dVar)).j(R.string.cancel, null).v();
    }

    public static void h(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, String str, String str2, String str3, d dVar) {
        if (abstractComponentCallbacksC0165t.e() == null || str3 == null || str3.isEmpty()) {
            return;
        }
        if (str2 != null && str2.length() > 32) {
            Toast.makeText(abstractComponentCallbacksC0165t.e(), "备注最多32个字", 0).show();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("friend_uid", str);
            jSONObject.put("remark_name", str2 == null ? "" : str2);
            g0.d.S("/friends/remark", jSONObject, str3, new c(abstractComponentCallbacksC0165t, str, str2, dVar));
        } catch (Exception unused) {
            if (abstractComponentCallbacksC0165t.e() != null) {
                Toast.makeText(abstractComponentCallbacksC0165t.e(), "设置备注失败", 0).show();
            }
        }
    }

    public static void i(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, String str, String str2) {
        List<w> listB;
        String str3;
        if (abstractComponentCallbacksC0165t == null || abstractComponentCallbacksC0165t.e() == null || str == null || str.isEmpty() || (listB = j0.c.b(abstractComponentCallbacksC0165t.e())) == null || listB.isEmpty()) {
            return;
        }
        if (str2 == null) {
            str2 = "";
        }
        for (w wVar : listB) {
            if (wVar != null && (str3 = wVar.f5646b) != null && str.equals(str3)) {
                String str4 = wVar.f5649e;
                if (str2.equals(str4 != null ? str4 : "")) {
                    return;
                }
                wVar.f5649e = str2;
                j0.c.c(abstractComponentCallbacksC0165t.e(), listB);
                return;
            }
        }
    }
}

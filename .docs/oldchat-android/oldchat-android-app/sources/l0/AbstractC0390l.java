package l0;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.ChatActivity;
import g0.d;
import java.util.HashSet;
import java.util.List;
import o.c;
import o0.C;
import org.spongycastle.i18n.TextBundle;

/* JADX INFO: renamed from: l0.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0390l {

    /* JADX INFO: renamed from: l0.l$a */
    public class a implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ChatActivity f6159a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f6160b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f6161c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ List f6162d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ HashSet f6163e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ com.im.oldchat.ui.S f6164f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ String f6165g;

        public a(ChatActivity chatActivity, String str, String str2, List list, HashSet hashSet, com.im.oldchat.ui.S s2, String str3) {
            this.f6159a = chatActivity;
            this.f6160b = str;
            this.f6161c = str2;
            this.f6162d = list;
            this.f6163e = hashSet;
            this.f6164f = s2;
            this.f6165g = str3;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            AbstractC0390l.f(this.f6159a, this.f6160b, this.f6161c, this.f6162d, this.f6163e, this.f6164f, this.f6165g);
        }
    }

    /* JADX INFO: renamed from: l0.l$b */
    public class b implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ List f6166a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f6167b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Context f6168c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f6169d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ com.im.oldchat.ui.S f6170e;

        public b(List list, String str, Context context, String str2, com.im.oldchat.ui.S s2) {
            this.f6166a = list;
            this.f6167b = str;
            this.f6168c = context;
            this.f6169d = str2;
            this.f6170e = s2;
        }

        @Override // g0.d.i
        public void b(String str) {
            String str2;
            for (int i2 = 0; i2 < this.f6166a.size(); i2++) {
                j0.k kVar = (j0.k) this.f6166a.get(i2);
                if (kVar != null && this.f6167b.equals(kVar.f5550a)) {
                    String str3 = kVar.f5554e;
                    String lowerCase = str3 == null ? TextBundle.TEXT_ENTRY : str3.toLowerCase();
                    kVar.f5560k = lowerCase;
                    if (!TextBundle.TEXT_ENTRY.equals(lowerCase) || (str2 = kVar.f5553d) == null) {
                        kVar.f5561l = "";
                    } else {
                        kVar.f5561l = str2;
                    }
                    kVar.f5554e = "recall";
                    kVar.f5553d = this.f6168c.getString(f0.j.w1);
                    kVar.f5555f = "";
                    kVar.f5556g = "";
                    kVar.f5557h = 0;
                    String str4 = this.f6169d;
                    if (str4 != null && !str4.isEmpty()) {
                        j0.l.l(this.f6168c, this.f6169d, this.f6166a);
                    }
                    this.f6170e.g();
                    Toast.makeText(this.f6168c, "消息已撤回", 0).show();
                    return;
                }
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            Toast.makeText(this.f6168c, "撤回失败: " + i2, 0).show();
        }
    }

    public static void b(View view, TextView textView, C.d dVar) {
        if (view == null || textView == null) {
            return;
        }
        if (dVar == null) {
            view.setVisibility(8);
            textView.setText("");
            return;
        }
        String str = dVar.f7009c;
        String str2 = (str == null || str.isEmpty()) ? dVar.f7008b : dVar.f7009c;
        String strF = AbstractC0380g.f(dVar.f7010d, dVar.f7012f, dVar.f7011e);
        StringBuilder sb = new StringBuilder();
        sb.append(str2 != null ? str2 : "");
        sb.append(": ");
        sb.append(strF);
        textView.setText(sb.toString());
        view.setVisibility(0);
    }

    public static C.d c(j0.k kVar, String str) {
        String str2;
        if (kVar == null) {
            return null;
        }
        o0.C cB = o0.C.b(kVar.f5553d);
        C.d dVar = new C.d();
        dVar.f7007a = kVar.f5550a;
        dVar.f7008b = kVar.f5552c;
        if (str == null || str.isEmpty()) {
            str = kVar.f5552c;
        }
        dVar.f7009c = str;
        String str3 = kVar.f5554e;
        String lowerCase = str3 == null ? TextBundle.TEXT_ENTRY : str3.toLowerCase();
        dVar.f7010d = lowerCase;
        dVar.f7012f = cB.f6991b;
        dVar.f7011e = AbstractC0380g.e(lowerCase, kVar.f5553d);
        if (("image".equals(dVar.f7010d) || "video".equals(dVar.f7010d)) && (str2 = kVar.f5555f) != null && !str2.isEmpty()) {
            String str4 = kVar.f5556g;
            dVar.f7013g = (str4 == null || str4.isEmpty()) ? kVar.f5555f : kVar.f5556g;
        }
        return dVar;
    }

    public static void d(View view, TextView textView) {
        if (view != null) {
            view.setVisibility(8);
        }
        if (textView != null) {
            textView.setText("");
        }
    }

    public static void e(ChatActivity chatActivity, String str, String str2, List list, HashSet hashSet, com.im.oldchat.ui.S s2, String str3) {
        if (chatActivity == null || str3 == null) {
            return;
        }
        new c.a(chatActivity).t("撤回消息").i("确定要撤回这条消息吗？").q("确定", new a(chatActivity, str, str2, list, hashSet, s2, str3)).k("取消", null).v();
    }

    public static void f(Context context, String str, String str2, List list, HashSet hashSet, com.im.oldchat.ui.S s2, String str3) {
        g0.d.O("/direct/messages/" + str3, str, new b(list, str3, context, str2, s2));
    }
}

package l0;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.GroupChatActivity;
import g0.d;
import java.util.HashSet;
import java.util.List;
import o.c;
import o0.C;
import org.spongycastle.i18n.TextBundle;

/* JADX INFO: loaded from: classes.dex */
public abstract class G {

    public class a implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ GroupChatActivity f5842a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f5843b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ List f5844c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ HashSet f5845d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ com.im.oldchat.ui.G f5846e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ String f5847f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ String f5848g;

        public a(GroupChatActivity groupChatActivity, String str, List list, HashSet hashSet, com.im.oldchat.ui.G g2, String str2, String str3) {
            this.f5842a = groupChatActivity;
            this.f5843b = str;
            this.f5844c = list;
            this.f5845d = hashSet;
            this.f5846e = g2;
            this.f5847f = str2;
            this.f5848g = str3;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            G.g(this.f5842a, this.f5843b, this.f5844c, this.f5845d, this.f5846e, this.f5847f, this.f5848g);
        }
    }

    public class b implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ List f5849a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f5850b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f5851c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Context f5852d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ com.im.oldchat.ui.G f5853e;

        public b(List list, String str, String str2, Context context, com.im.oldchat.ui.G g2) {
            this.f5849a = list;
            this.f5850b = str;
            this.f5851c = str2;
            this.f5852d = context;
            this.f5853e = g2;
        }

        @Override // g0.d.i
        public void b(String str) {
            String str2;
            for (int i2 = 0; i2 < this.f5849a.size(); i2++) {
                j0.i iVar = (j0.i) this.f5849a.get(i2);
                if (iVar != null && this.f5850b.equals(iVar.f5529a)) {
                    String str3 = this.f5851c;
                    String str4 = (str3 == null || str3.isEmpty()) ? "成员" : this.f5851c;
                    String str5 = iVar.f5533e;
                    String lowerCase = str5 == null ? TextBundle.TEXT_ENTRY : str5.toLowerCase();
                    iVar.f5539k = lowerCase;
                    if (!TextBundle.TEXT_ENTRY.equals(lowerCase) || (str2 = iVar.f5532d) == null) {
                        iVar.f5540l = "";
                    } else {
                        iVar.f5540l = str2;
                    }
                    iVar.f5533e = "recall";
                    iVar.f5532d = this.f5852d.getString(f0.j.u1, str4);
                    iVar.f5534f = "";
                    iVar.f5535g = "";
                    iVar.f5536h = 0;
                    String str6 = iVar.f5530b;
                    if (str6 != null && !str6.isEmpty()) {
                        j0.l.m(this.f5852d, iVar.f5530b, this.f5849a);
                    }
                    this.f5853e.notifyDataSetChanged();
                    Toast.makeText(this.f5852d, "消息已撤回", 0).show();
                    return;
                }
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            Toast.makeText(this.f5852d, "撤回失败: " + i2, 0).show();
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

    public static C.d c(j0.i iVar, String str) {
        String str2;
        if (iVar == null) {
            return null;
        }
        o0.C cB = o0.C.b(iVar.f5532d);
        C.d dVar = new C.d();
        dVar.f7007a = iVar.f5529a;
        dVar.f7008b = iVar.f5531c;
        if (str == null || str.isEmpty()) {
            str = iVar.f5531c;
        }
        dVar.f7009c = str;
        String str3 = iVar.f5533e;
        String lowerCase = str3 == null ? TextBundle.TEXT_ENTRY : str3.toLowerCase();
        dVar.f7010d = lowerCase;
        dVar.f7012f = cB.f6991b;
        dVar.f7011e = AbstractC0380g.e(lowerCase, iVar.f5532d);
        if (("image".equals(dVar.f7010d) || "video".equals(dVar.f7010d)) && (str2 = iVar.f5534f) != null && !str2.isEmpty()) {
            String str4 = iVar.f5535g;
            dVar.f7013g = (str4 == null || str4.isEmpty()) ? iVar.f5534f : iVar.f5535g;
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

    public static void e(GroupChatActivity groupChatActivity, String str, List list, HashSet hashSet, com.im.oldchat.ui.G g2, String str2, String str3) {
        if (groupChatActivity == null || str2 == null) {
            return;
        }
        new c.a(groupChatActivity).t("撤回消息").i("确定要撤回这条消息吗？").q("确定", new a(groupChatActivity, str, list, hashSet, g2, str2, str3)).k("取消", null).v();
    }

    public static void f(EditText editText, j0.i iVar, String str) {
        String str2;
        if (editText == null || iVar == null || (str2 = iVar.f5531c) == null || str2.isEmpty()) {
            return;
        }
        if (str == null || str.isEmpty()) {
            str = iVar.f5531c;
        }
        String str3 = "@" + str + " ";
        int selectionStart = editText.getSelectionStart();
        if (selectionStart < 0) {
            selectionStart = editText.getText().length();
        }
        editText.getText().insert(selectionStart, str3);
    }

    public static void g(Context context, String str, List list, HashSet hashSet, com.im.oldchat.ui.G g2, String str2, String str3) {
        g0.d.O("/groups/messages/" + str2, str, new b(list, str2, str3, context, g2));
    }
}

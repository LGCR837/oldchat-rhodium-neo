package l0;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.im.oldchat.ui.AbstractActivityC0232x;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import o.c;
import o0.C;

/* JADX INFO: loaded from: classes.dex */
public abstract class C extends AbstractActivityC0232x {

    public class b implements AdapterView.OnItemClickListener {
        public b() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            C.this.P0(C.this.y0.getItem(i2));
            o.c cVar = C.this.x0;
            if (cVar != null) {
                cVar.dismiss();
            }
        }
    }

    public final void M0(String str, String str2) {
        if (str == null || str.isEmpty()) {
            return;
        }
        if (str2 == null || str2.isEmpty()) {
            str2 = str;
        }
        for (C.c cVar : this.f4854Y) {
            if (cVar != null && str.equals(cVar.f7005a)) {
                cVar.f7006b = str2;
                return;
            }
        }
        C.c cVar2 = new C.c();
        cVar2.f7005a = str;
        cVar2.f7006b = str2;
        this.f4854Y.add(cVar2);
    }

    public final String N0(C.c cVar) {
        if (cVar == null) {
            return "";
        }
        String str = cVar.f7006b;
        String str2 = (str == null || str.isEmpty()) ? cVar.f7005a : cVar.f7006b;
        StringBuilder sb = new StringBuilder();
        sb.append("@");
        sb.append(str2 != null ? str2 : "");
        return sb.toString();
    }

    public final int O0(CharSequence charSequence, int i2) {
        if (charSequence != null && i2 > 0) {
            for (int i3 = i2 - 1; i3 >= 0; i3--) {
                char cCharAt = charSequence.charAt(i3);
                if (cCharAt == '@' && (i3 == 0 || Character.isWhitespace(charSequence.charAt(i3 - 1)))) {
                    return i3;
                }
                if (Character.isWhitespace(cCharAt)) {
                    break;
                }
            }
        }
        return i2;
    }

    public final void P0(j0.h hVar) {
        String strT0;
        if (hVar == null || this.f4872x == null || (strT0 = T0(hVar)) == null || strT0.isEmpty()) {
            return;
        }
        M0(hVar.f5522a, strT0);
        Editable text = this.f4872x.getText();
        if (text == null) {
            return;
        }
        int selectionStart = this.f4872x.getSelectionStart();
        if (selectionStart < 0 || selectionStart > text.length()) {
            selectionStart = text.length();
        }
        int iO0 = O0(text, selectionStart);
        this.z0 = true;
        text.replace(iO0, selectionStart, "@" + strT0 + " ");
        this.z0 = false;
    }

    public void Q0(j0.i iVar, String str) {
        this.z0 = true;
        G.f(this.f4872x, iVar, str);
        this.z0 = false;
        if (iVar != null) {
            M0(iVar.f5531c, str);
        }
    }

    public final void R0(String str) {
        String str2;
        if (this.f4854Y.isEmpty()) {
            return;
        }
        if (str == null) {
            str = "";
        }
        for (int size = this.f4854Y.size() - 1; size >= 0; size--) {
            C.c cVar = (C.c) this.f4854Y.get(size);
            if (cVar == null || (str2 = cVar.f7005a) == null || str2.isEmpty()) {
                this.f4854Y.remove(size);
            } else if (!str.contains(N0(cVar))) {
                this.f4854Y.remove(size);
            }
        }
    }

    public final void S0() {
        String str;
        String str2;
        HashSet hashSet = new HashSet();
        boolean zIsEmpty = this.f4853X.isEmpty();
        for (int i2 = 0; i2 < this.f4853X.size(); i2++) {
            j0.h hVar = (j0.h) this.f4853X.get(i2);
            if (hVar != null && (str2 = hVar.f5522a) != null && !str2.isEmpty()) {
                hashSet.add(hVar.f5522a);
                String str3 = hVar.f5524c;
                if (str3 == null || str3.isEmpty()) {
                    hVar.f5524c = A0(hVar.f5522a);
                }
                String str4 = hVar.f5526e;
                if (str4 == null || str4.isEmpty()) {
                    hVar.f5526e = (String) this.f4850U.get(hVar.f5522a);
                }
                Integer num = (Integer) this.f4852W.get(hVar.f5522a);
                if (num != null) {
                    hVar.f5527f = num.intValue();
                }
            }
        }
        if (zIsEmpty) {
            for (int size = this.f4847R.size() - 1; size >= 0; size--) {
                j0.i iVar = (j0.i) this.f4847R.get(size);
                if (iVar != null && (str = iVar.f5531c) != null && !str.isEmpty()) {
                    String str5 = iVar.f5531c;
                    if (!hashSet.contains(str5)) {
                        hashSet.add(str5);
                        j0.h hVar2 = new j0.h();
                        hVar2.f5522a = str5;
                        hVar2.f5524c = A0(str5);
                        hVar2.f5526e = (String) this.f4850U.get(str5);
                        Integer num2 = (Integer) this.f4852W.get(str5);
                        hVar2.f5527f = num2 == null ? 0 : num2.intValue();
                        this.f4853X.add(hVar2);
                    }
                }
            }
        }
    }

    public final String T0(j0.h hVar) {
        if (hVar == null) {
            return "";
        }
        String str = hVar.f5524c;
        if (str != null && !str.isEmpty()) {
            return hVar.f5524c;
        }
        String str2 = hVar.f5523b;
        if (str2 != null && !str2.isEmpty()) {
            return hVar.f5523b;
        }
        String str3 = hVar.f5522a;
        return str3 == null ? "" : str3;
    }

    public void U0() {
        EditText editText = this.f4872x;
        if (editText == null) {
            return;
        }
        editText.addTextChangedListener(new a());
    }

    public final void V0() {
        o.c cVar = this.x0;
        if (cVar == null || !cVar.isShowing()) {
            S0();
            View viewInflate = getLayoutInflater().inflate(f0.g.f5150i0, (ViewGroup) null);
            EditText editText = (EditText) viewInflate.findViewById(f0.f.F4);
            ListView listView = (ListView) viewInflate.findViewById(f0.f.P6);
            L l2 = new L(this, this.f4853X, this.f4859d0);
            this.y0 = l2;
            l2.f(this.f4860e0);
            listView.setAdapter((ListAdapter) this.y0);
            listView.setOnItemClickListener(new b());
            editText.addTextChangedListener(new c());
            o.c cVarA = new c.a(this, f0.k.f5244a).t("选择成员").u(viewInflate).k("取消", null).a();
            this.x0 = cVarA;
            cVarA.show();
        }
    }

    @Override // com.im.oldchat.ui.AbstractActivityC0232x
    public List m0(String str) {
        String str2;
        if (str == null || str.isEmpty() || this.f4854Y.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        for (C.c cVar : this.f4854Y) {
            if (cVar != null && (str2 = cVar.f7005a) != null && !str2.isEmpty() && !hashSet.contains(cVar.f7005a) && str.contains(N0(cVar))) {
                arrayList.add(cVar);
                hashSet.add(cVar.f7005a);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    public class a implements TextWatcher {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f5780a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f5781b = 0;

        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (C.this.z0) {
                return;
            }
            String string = editable == null ? "" : editable.toString();
            if (string.length() == 0) {
                C.this.f4854Y.clear();
                return;
            }
            C.this.R0(string);
            int i2 = this.f5781b;
            if (i2 <= 0) {
                return;
            }
            int iMin = Math.min(this.f5780a + i2, string.length());
            for (int i3 = this.f5780a; i3 < iMin; i3++) {
                if (string.charAt(i3) == '@' && (i3 == 0 || Character.isWhitespace(string.charAt(i3 - 1)))) {
                    C.this.V0();
                    return;
                }
            }
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            this.f5780a = i2;
            this.f5781b = i4;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }
    }

    public class c implements TextWatcher {
        public c() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            L l2 = C.this.y0;
            if (l2 != null) {
                l2.b(editable == null ? "" : editable.toString());
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }
    }
}

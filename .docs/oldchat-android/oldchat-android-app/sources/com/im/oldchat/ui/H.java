package com.im.oldchat.ui;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.im.oldchat.ui.G;
import com.im.oldchat.ui.N;
import d.AbstractC0235a;
import java.util.List;
import l0.AbstractC0404t;
import l0.AbstractC0406v;
import org.spongycastle.i18n.TextBundle;

/* JADX INFO: loaded from: classes.dex */
public abstract class H extends K {

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.i f2975a;

        public a(j0.i iVar) {
            this.f2975a = iVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            G.a aVar = H.this.f3465k;
            if (aVar != null) {
                aVar.g(this.f2975a);
            }
        }
    }

    public H(Context context, List list, String str) {
        super(context, list, str);
    }

    public void D0(N.d dVar, boolean z2, boolean z3) {
        if (dVar == null || dVar.f3506Q == null) {
            return;
        }
        int iC = c(1);
        int i2 = z2 ? iC : dVar.f3508S;
        if (!z3) {
            iC = dVar.f3510U;
        }
        dVar.f3506Q.setPadding(dVar.f3507R, i2, dVar.f3509T, iC);
    }

    public abstract void E0(N.d dVar, j0.i iVar, o0.C c2, String str, boolean z2);

    public abstract void F0(N.d dVar, j0.i iVar, String str, boolean z2, boolean z3, boolean z4);

    public boolean G0(j0.i iVar, j0.i iVar2) {
        if (iVar == null || iVar2 == null) {
            return false;
        }
        String str = iVar2.f5531c;
        String strTrim = str == null ? "" : str.trim();
        String str2 = iVar.f5531c;
        String strTrim2 = str2 != null ? str2.trim() : "";
        if (strTrim.length() == 0 || strTrim2.length() == 0 || !strTrim.equals(strTrim2)) {
            return false;
        }
        String str3 = iVar2.f5533e;
        String lowerCase = TextBundle.TEXT_ENTRY;
        String lowerCase2 = str3 == null ? TextBundle.TEXT_ENTRY : str3.trim().toLowerCase();
        String str4 = iVar.f5533e;
        if (str4 != null) {
            lowerCase = str4.trim().toLowerCase();
        }
        return ("recall".equals(lowerCase2) || "recall".equals(lowerCase)) ? false : true;
    }

    public boolean H0(j0.i iVar, boolean z2) {
        if (!z2 || iVar == null) {
            return false;
        }
        String str = iVar.f5540l;
        if ((str == null ? "" : str.trim()).length() == 0) {
            return false;
        }
        String str2 = iVar.f5539k;
        return TextBundle.TEXT_ENTRY.equals(str2 == null ? TextBundle.TEXT_ENTRY : str2.toLowerCase());
    }

    public String I0(String str) {
        if (str == null) {
            return "";
        }
        String strTrim = str.trim();
        while (strTrim.startsWith("@")) {
            strTrim = strTrim.substring(1).trim();
        }
        return strTrim;
    }

    public boolean J0(int i2, j0.i iVar) {
        if (iVar == null || i2 < 0 || i2 >= this.f3456b.size() - 1) {
            return false;
        }
        return G0(iVar, (j0.i) this.f3456b.get(i2 + 1));
    }

    public boolean K0(int i2, j0.i iVar) {
        if (iVar == null || i2 <= 0 || i2 >= this.f3456b.size()) {
            return false;
        }
        return G0((j0.i) this.f3456b.get(i2 - 1), iVar);
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        N.d dVar;
        View view2;
        N.c cVar;
        View viewInflate;
        j0.i iVar = null;
        if (getItemViewType(i2) == 1) {
            if (view == null) {
                viewInflate = LayoutInflater.from(this.f3455a).inflate(f0.g.a1, viewGroup, false);
                cVar = new N.c(viewInflate);
                viewInflate.setTag(cVar);
            } else {
                cVar = (N.c) view.getTag();
                viewInflate = view;
            }
            if (this.f3469o && i2 == this.f3456b.size() - 1) {
                iVar = this.f3468n;
            }
            K(cVar, iVar);
            viewInflate.setBackgroundColor(0);
            return viewInflate;
        }
        j0.i iVar2 = (j0.i) this.f3456b.get(i2);
        if (view == null) {
            View viewInflate2 = LayoutInflater.from(this.f3455a).inflate(f0.g.C0, viewGroup, false);
            dVar = new N.d(viewInflate2);
            viewInflate2.setTag(dVar);
            view2 = viewInflate2;
        } else {
            dVar = (N.d) view.getTag();
            view2 = view;
        }
        N.d dVar2 = dVar;
        a(dVar2);
        b(dVar2);
        V(view2, iVar2.f5529a);
        String str = iVar2.f5533e;
        String lowerCase = str == null ? TextBundle.TEXT_ENTRY : str.toLowerCase();
        o0.C cP = p(iVar2);
        boolean z2 = TextBundle.TEXT_ENTRY.equals(lowerCase) && AbstractC0406v.i(cP);
        boolean zEquals = "image".equals(lowerCase);
        boolean z3 = "music".equals(lowerCase) || ("resource".equals(lowerCase) && cP != null && "music".equals(cP.f6991b));
        boolean zC = o0.F.c(this.f3455a, iVar2.f5531c, this.f3457c);
        dVar2.f3506Q.setVisibility(0);
        TextView textView = dVar2.f3505P;
        if (textView != null) {
            textView.setOnClickListener(null);
            dVar2.f3505P.setClickable(false);
            dVar2.f3505P.setTextColor(AbstractC0235a.b(this.f3455a, f0.d.f4974k));
        }
        if (!"recall".equals(lowerCase)) {
            k(dVar2, iVar2, i2);
            B(dVar2);
            AbstractC0404t.d(dVar2.f3522h);
            boolean zK0 = K0(i2, iVar2);
            boolean zJ0 = J0(i2, iVar2);
            D0(dVar2, zK0, zJ0);
            E0(dVar2, iVar2, cP, lowerCase, zC);
            F0(dVar2, iVar2, lowerCase, zK0, zJ0, zC);
            if (z3) {
                J(dVar2, zC ? f0.e.f5023j : f0.e.f5024k);
            }
            if (zEquals) {
                M(dVar2);
            }
            if (z2) {
                AbstractC0404t.a(dVar2.f3522h, this.f3455a);
            }
            f(dVar2, cP.f6993d);
            U(view2, dVar2, iVar2, cP);
            return view2;
        }
        dVar2.f3506Q.setVisibility(8);
        String string = cP.f6990a;
        if (string == null) {
            string = "";
        }
        if (string.length() == 0) {
            String strC = C(iVar2.f5531c);
            if (strC == null || strC.isEmpty()) {
                strC = "成员";
            }
            string = this.f3455a.getString(f0.j.u1, strC);
        }
        if (!H0(iVar2, zC) || dVar2.f3505P == null) {
            dVar2.f3505P.setText(string);
        } else {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append((CharSequence) string).append((CharSequence) "  ");
            int length = spannableStringBuilder.length();
            spannableStringBuilder.append((CharSequence) "重新编辑");
            spannableStringBuilder.setSpan(new ForegroundColorSpan(o0.X.i(this.f3455a)), length, spannableStringBuilder.length(), 33);
            dVar2.f3505P.setText(spannableStringBuilder);
            dVar2.f3505P.setClickable(true);
            dVar2.f3505P.setOnClickListener(new a(iVar2));
        }
        dVar2.f3505P.setVisibility(0);
        return view2;
    }
}

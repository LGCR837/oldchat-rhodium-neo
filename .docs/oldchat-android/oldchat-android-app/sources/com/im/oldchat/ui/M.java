package com.im.oldchat.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.N;
import h0.c;
import java.util.List;
import java.util.Locale;
import l0.AbstractC0380g;
import l0.AbstractC0382h;
import o0.AbstractC0434b0;
import o0.AbstractC0455v;
import o0.C;
import org.spongycastle.i18n.MessageBundle;

/* JADX INFO: loaded from: classes.dex */
public abstract class M extends N {

    public class a implements AbstractC0455v.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ImageView f3040a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f3041b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f3042c;

        public a(ImageView imageView, String str, String str2) {
            this.f3040a = imageView;
            this.f3041b = str;
            this.f3042c = str2;
        }

        @Override // o0.AbstractC0455v.c
        public void a(String str) {
            String str2;
            Object tag = this.f3040a.getTag();
            if (tag == null || !this.f3041b.equals(tag) || (this.f3040a.getDrawable() instanceof BitmapDrawable) || (str2 = this.f3042c) == null || str2.length() == 0 || this.f3041b.equals(this.f3042c)) {
                return;
            }
            Context context = this.f3040a.getContext();
            if (context == null || !AbstractC0455v.d(context, this.f3041b)) {
                this.f3040a.setTag(this.f3042c);
                this.f3040a.setImageResource(f0.e.f5018g);
                AbstractC0455v.e(this.f3040a, this.f3042c);
            }
        }
    }

    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ C.d f3044a;

        public b(C.d dVar) {
            this.f3044a = dVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String str;
            if (M.this.f3473s == null || (str = this.f3044a.f7007a) == null || str.isEmpty()) {
                return;
            }
            M.this.f3473s.a(this.f3044a);
        }
    }

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f3046a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f3047b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f3048c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f3049d;
    }

    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f3050a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f3051b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f3052c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f3053d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f3054e;
    }

    public M(Context context, List list, String str) {
        super(context, list, str);
    }

    public d A(String str, String str2) {
        String str3;
        int i2;
        int i3;
        d dVar = new d();
        String str4 = str == null ? "" : str;
        boolean zC = h0.c.c(str2);
        c.a aVarE = zC ? h0.c.e(str2) : null;
        String[] strArrSplit = str4.split("\r?\n");
        String strQ = "";
        String strTrim = strQ;
        String str5 = strTrim;
        int i4 = 0;
        while (true) {
            if (i4 >= strArrSplit.length) {
                break;
            }
            String str6 = strArrSplit[i4];
            String strTrim2 = str6 == null ? "" : str6.trim();
            if (strTrim2.length() != 0) {
                if (strQ.length() == 0 && (strTrim2.startsWith("资源:") || strTrim2.startsWith("资源：") || strTrim2.startsWith("文件:") || strTrim2.startsWith("文件：") || strTrim2.startsWith("视频:") || strTrim2.startsWith("视频：") || strTrim2.startsWith("标题:") || strTrim2.startsWith("标题："))) {
                    int iIndexOf = strTrim2.indexOf(58);
                    if (iIndexOf < 0) {
                        iIndexOf = strTrim2.indexOf(65306);
                    }
                    if (iIndexOf >= 0 && (i3 = iIndexOf + 1) < strTrim2.length()) {
                        strQ = strTrim2.substring(i3).trim();
                    }
                } else if (strTrim.length() == 0 && (strTrim2.startsWith("大小:") || strTrim2.startsWith("大小：") || strTrim2.startsWith("时长:") || strTrim2.startsWith("时长："))) {
                    int iIndexOf2 = strTrim2.indexOf(58);
                    if (iIndexOf2 < 0) {
                        iIndexOf2 = strTrim2.indexOf(65306);
                    }
                    if (iIndexOf2 >= 0 && (i2 = iIndexOf2 + 1) < strTrim2.length()) {
                        strTrim = strTrim2.substring(i2).trim();
                    }
                } else if (str5.length() == 0 && strTrim2.contains("点击")) {
                    if (strTrim2.contains("观看")) {
                        str5 = "点击观看";
                    } else if (strTrim2.contains("下载")) {
                        str5 = "点击下载";
                    }
                }
            }
            i4++;
        }
        if (strQ.length() == 0 && aVarE != null && (str3 = aVarE.f5440d) != null && str3.length() > 0) {
            strQ = aVarE.f5440d.trim();
        }
        if (strQ.length() == 0) {
            strQ = q(str2);
        }
        if (strQ.length() == 0) {
            strQ = zC ? "B站视频" : "资源分享";
        }
        if (strTrim.length() == 0 && aVarE != null && aVarE.f5442f > 0) {
            strTrim = "时长 " + o(aVarE.f5442f);
        }
        if (strTrim.length() > 0) {
            dVar.f3051b = strTrim;
        } else if (str5.length() > 0) {
            dVar.f3051b = str5;
        } else {
            dVar.f3051b = zC ? "点击观看" : "点击下载";
        }
        dVar.f3050a = strQ;
        dVar.f3052c = zC;
        String str7 = aVarE == null ? null : aVarE.f5441e;
        dVar.f3053d = t(str7, true);
        dVar.f3054e = t(str7, false);
        return dVar;
    }

    public void B(N.d dVar) {
        dVar.f3522h.setVisibility(8);
        View view = dVar.f3491B;
        if (view != null) {
            view.setVisibility(8);
        }
        ImageView imageView = dVar.f3493D;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        dVar.f3492C.setVisibility(8);
        dVar.f3494E.setVisibility(8);
        dVar.f3494E.setOnClickListener(null);
        TextView textView = dVar.f3497H;
        if (textView != null) {
            textView.setVisibility(8);
            dVar.f3497H.setText("");
        }
        ProgressBar progressBar = dVar.f3498I;
        if (progressBar != null) {
            progressBar.setVisibility(8);
        }
        dVar.f3492C.setOnClickListener(null);
        dVar.f3523i.setOnClickListener(null);
        dVar.f3522h.setOnClickListener(null);
        dVar.f3506Q.setOnClickListener(null);
        dVar.f3506Q.setOnLongClickListener(null);
        dVar.f3523i.setOnLongClickListener(null);
        dVar.f3522h.setOnLongClickListener(null);
        dVar.f3492C.setOnLongClickListener(null);
        dVar.f3494E.setOnLongClickListener(null);
        TextView textView2 = dVar.f3497H;
        if (textView2 != null) {
            textView2.setOnLongClickListener(null);
        }
        dVar.f3538x.setVisibility(8);
        dVar.f3538x.setOnLongClickListener(null);
        dVar.f3495F.clearAnimation();
        dVar.f3519e.setVisibility(8);
        dVar.f3519e.setOnClickListener(null);
        dVar.f3519e.setOnLongClickListener(null);
        ImageView imageView2 = dVar.f3520f;
        if (imageView2 != null) {
            imageView2.setVisibility(8);
            dVar.f3520f.setOnClickListener(null);
            dVar.f3520f.setOnLongClickListener(null);
        }
        dVar.f3524j.setVisibility(8);
        dVar.f3524j.setOnClickListener(null);
        dVar.f3528n.setOnClickListener(null);
        dVar.f3524j.setOnLongClickListener(null);
        dVar.f3528n.setOnLongClickListener(null);
        View view2 = dVar.f3529o;
        if (view2 != null) {
            view2.setVisibility(8);
        }
        ImageView imageView3 = dVar.f3530p;
        if (imageView3 != null) {
            imageView3.setTag(null);
        }
        dVar.f3532r.setVisibility(8);
        dVar.f3532r.setOnClickListener(null);
        dVar.f3532r.setOnLongClickListener(null);
        LinearLayout linearLayout = dVar.f3515a;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        TextView textView3 = dVar.f3517c;
        if (textView3 != null) {
            textView3.setVisibility(8);
        }
        TextView textView4 = dVar.f3518d;
        if (textView4 != null) {
            textView4.setVisibility(8);
        }
    }

    public String C(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        String strD = AbstractC0434b0.d(this.f3455a, str);
        if (strD == null || strD.isEmpty()) {
            strD = str;
        }
        String strB = (String) this.f3459e.get(strD);
        if (strB == null || strB.isEmpty()) {
            strB = (String) this.f3459e.get(str);
        }
        if (strB == null || strB.isEmpty()) {
            strB = j0.x.b(this.f3455a, strD);
        }
        if (strB == null || strB.isEmpty()) {
            strB = j0.x.b(this.f3455a, str);
        }
        return (strB == null || strB.isEmpty()) ? strD : strB;
    }

    public int D(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        String strD = AbstractC0434b0.d(this.f3455a, str);
        if (strD == null || strD.isEmpty()) {
            strD = str;
        }
        Integer num = (Integer) this.f3462h.get(strD);
        if (num == null) {
            num = (Integer) this.f3462h.get(str);
        }
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public String E(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        String strD = AbstractC0434b0.d(this.f3455a, str);
        if (strD == null || strD.isEmpty()) {
            strD = str;
        }
        String strA = (String) this.f3461g.get(strD);
        if (strA == null || strA.isEmpty()) {
            strA = (String) this.f3461g.get(str);
        }
        if (strA == null || strA.isEmpty()) {
            strA = j0.y.a(this.f3455a, strD);
        }
        if (strA == null || strA.isEmpty()) {
            strA = j0.y.a(this.f3455a, str);
        }
        return strA == null ? "" : strA;
    }

    public boolean F(String str, String str2) {
        return str == null ? str2 == null : str.equals(str2);
    }

    public boolean G(j0.i iVar, int i2) {
        if (i2 == 0) {
            return true;
        }
        return AbstractC0382h.e(iVar.f5537i, ((j0.i) this.f3456b.get(i2 - 1)).f5537i);
    }

    public final String H(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        String lowerCase = str.toLowerCase(Locale.US);
        String[] strArr = {".jpg", ".jpeg", ".png", ".webp", ".gif", ".bmp"};
        int i2 = -1;
        for (int i3 = 0; i3 < 6; i3++) {
            int iIndexOf = lowerCase.indexOf(strArr[i3]);
            if (iIndexOf >= 0) {
                int length = iIndexOf + strArr[i3].length();
                if (i2 < 0 || length < i2) {
                    i2 = length;
                }
            }
        }
        return (i2 <= 0 || i2 >= str.length()) ? str : str.substring(0, i2);
    }

    public void e(N.d dVar, String str) {
        View view;
        if (dVar == null || (view = dVar.f3529o) == null) {
            return;
        }
        view.setVisibility(8);
        ImageView imageView = dVar.f3530p;
        if (imageView != null) {
            imageView.setTag(null);
        }
        ImageView imageView2 = dVar.f3531q;
        if (imageView2 != null) {
            imageView2.setVisibility(0);
            dVar.f3531q.setImageResource(f0.e.f5015e0);
        }
    }

    public void f(N.d dVar, C.d dVar2) {
        LinearLayout linearLayout = dVar.f3538x;
        if (linearLayout == null) {
            return;
        }
        if (dVar2 == null) {
            linearLayout.setVisibility(8);
            dVar.f3538x.setOnClickListener(null);
            ImageView imageView = dVar.f3490A;
            if (imageView != null) {
                imageView.setVisibility(8);
                dVar.f3490A.setTag(null);
                return;
            }
            return;
        }
        String str = dVar2.f7009c;
        String str2 = (str == null || str.isEmpty()) ? dVar2.f7008b : dVar2.f7009c;
        if (str2 == null || str2.isEmpty()) {
            str2 = "成员";
        }
        dVar.f3539y.setText(str2);
        dVar.f3540z.setText(AbstractC0380g.f(dVar2.f7010d, dVar2.f7012f, dVar2.f7011e));
        dVar.f3538x.setVisibility(0);
        g(dVar.f3490A, dVar2.f7013g);
        dVar.f3538x.setOnClickListener(new b(dVar2));
    }

    public void g(ImageView imageView, String str) {
        if (imageView == null) {
            return;
        }
        if (str == null || str.isEmpty()) {
            imageView.setVisibility(8);
            imageView.setTag(null);
        } else {
            imageView.setVisibility(0);
            AbstractC0455v.e(imageView, str);
        }
    }

    public void h(N.d dVar, int i2, boolean z2) {
        LinearLayout linearLayout;
        LinearLayout linearLayout2;
        if (dVar == null) {
            return;
        }
        LinearLayout linearLayout3 = z2 ? dVar.f3502M : dVar.f3499J;
        ImageView imageView = z2 ? dVar.f3503N : dVar.f3500K;
        TextView textView = z2 ? dVar.f3504O : dVar.f3501L;
        if (!z2 && (linearLayout2 = dVar.f3502M) != null) {
            linearLayout2.setVisibility(8);
        }
        if (z2 && (linearLayout = dVar.f3499J) != null) {
            linearLayout.setVisibility(8);
        }
        if (linearLayout3 == null || imageView == null || textView == null) {
            return;
        }
        linearLayout3.setVisibility(0);
        if (i2 > 0) {
            imageView.setImageResource(f0.e.f5019g0);
            textView.setText(String.valueOf(i2));
        } else {
            imageView.setImageResource(f0.e.f5021h0);
            textView.setText("");
        }
    }

    public void i(N.d dVar, d dVar2) {
        View view;
        if (dVar == null || (view = dVar.f3529o) == null) {
            return;
        }
        view.setVisibility(8);
        ImageView imageView = dVar.f3530p;
        if (imageView != null) {
            imageView.setTag(null);
        }
        ImageView imageView2 = dVar.f3531q;
        if (imageView2 != null) {
            imageView2.setVisibility(0);
            dVar.f3531q.setImageResource(f0.e.f4998S);
        }
        if (dVar2 == null || !dVar2.f3052c) {
            return;
        }
        String str = dVar2.f3053d;
        String str2 = "";
        String strTrim = str == null ? "" : str.trim();
        String str3 = dVar2.f3054e;
        String strTrim2 = str3 == null ? "" : str3.trim();
        if (strTrim.length() == 0) {
            strTrim = strTrim2;
        } else {
            str2 = strTrim2;
        }
        if (strTrim.length() == 0 || dVar.f3530p == null) {
            return;
        }
        dVar.f3529o.setVisibility(0);
        r(dVar.f3530p, strTrim, str2);
    }

    public void j(TextView textView, int i2) {
        if (textView == null) {
            return;
        }
        if (i2 == 2) {
            textView.setText("群主");
            textView.setBackgroundResource(f0.e.f5008b);
            textView.setVisibility(0);
        } else {
            if (i2 != 1) {
                textView.setVisibility(8);
                return;
            }
            textView.setText("管理员");
            textView.setBackgroundResource(f0.e.f5006a);
            textView.setVisibility(0);
        }
    }

    public void k(N.d dVar, j0.i iVar, int i2) {
        if (dVar.f3505P == null) {
            return;
        }
        if (!G(iVar, i2)) {
            dVar.f3505P.setVisibility(8);
            return;
        }
        String strA = AbstractC0382h.a(iVar.f5537i);
        if (strA.isEmpty()) {
            dVar.f3505P.setVisibility(8);
        } else {
            dVar.f3505P.setText(strA);
            dVar.f3505P.setVisibility(0);
        }
    }

    public Animation l() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.4f, 1.0f);
        alphaAnimation.setDuration(400L);
        alphaAnimation.setRepeatMode(2);
        alphaAnimation.setRepeatCount(-1);
        return alphaAnimation;
    }

    public void m(String str, String str2, o0.C c2) {
        if (str == null) {
            return;
        }
        if (this.f3463i.size() >= 400) {
            this.f3463i.clear();
        }
        N.b bVar = new N.b();
        bVar.f3475a = str2;
        bVar.f3476b = c2;
        this.f3463i.put(str, bVar);
    }

    public boolean n(j0.i iVar) {
        String str;
        if (iVar == null || (str = iVar.f5531c) == null || str.isEmpty()) {
            return false;
        }
        if (o0.F.c(this.f3455a, iVar.f5531c, this.f3457c)) {
            return iVar.f5537i > 0 && System.currentTimeMillis() - AbstractC0380g.b(iVar.f5537i) < 60000;
        }
        int i2 = this.f3458d;
        if (i2 >= 2) {
            return true;
        }
        return i2 >= 1 && D(iVar.f5531c) < 2;
    }

    public final String o(long j2) {
        if (j2 <= 0) {
            return "";
        }
        long j3 = j2 / 3600;
        long j4 = (j2 % 3600) / 60;
        long j5 = j2 % 60;
        return j3 > 0 ? String.format(Locale.US, "%02d:%02d:%02d", Long.valueOf(j3), Long.valueOf(j4), Long.valueOf(j5)) : String.format(Locale.US, "%02d:%02d", Long.valueOf(j4), Long.valueOf(j5));
    }

    public o0.C p(j0.i iVar) {
        N.b bVar;
        o0.C c2;
        if (iVar == null) {
            return new o0.C();
        }
        String str = iVar.f5529a;
        String str2 = (str == null || str.length() <= 0) ? iVar.f5532d : iVar.f5529a;
        if (str2 != null && (bVar = (N.b) this.f3463i.get(str2)) != null && F(bVar.f3475a, iVar.f5532d) && (c2 = bVar.f3476b) != null) {
            return c2;
        }
        o0.C cB = o0.C.b(iVar.f5532d);
        m(str2, iVar.f5532d, cB);
        return cB;
    }

    public String q(String str) {
        int i2;
        if (str == null) {
            return "";
        }
        int iIndexOf = str.indexOf(63);
        if (iIndexOf >= 0) {
            str = str.substring(0, iIndexOf);
        }
        int iIndexOf2 = str.indexOf(35);
        if (iIndexOf2 >= 0) {
            str = str.substring(0, iIndexOf2);
        }
        int iLastIndexOf = str.lastIndexOf(47);
        return (iLastIndexOf < 0 || (i2 = iLastIndexOf + 1) >= str.length()) ? "" : str.substring(i2);
    }

    public final void r(ImageView imageView, String str, String str2) {
        if (imageView == null || str == null || str.length() == 0) {
            return;
        }
        Object tag = imageView.getTag();
        if (tag == null || !str.equals(tag)) {
            imageView.setTag(str);
            imageView.setImageResource(f0.e.f5018g);
            AbstractC0455v.f(imageView, str, new a(imageView, str, str2));
        }
    }

    public final String s(String str) {
        int iIndexOf;
        int i2;
        if (str == null) {
            return "";
        }
        String strTrim = str.trim();
        if (strTrim.length() == 0) {
            return "";
        }
        if (strTrim.length() > 1 && ((strTrim.startsWith("\"") && strTrim.endsWith("\"")) || (strTrim.startsWith("'") && strTrim.endsWith("'")))) {
            strTrim = strTrim.substring(1, strTrim.length() - 1).trim();
        }
        String strReplace = strTrim.replace("\\u0026", "&").replace("\\/", "/");
        if ((strReplace.startsWith("cover_url=") || strReplace.startsWith("thumb_url=")) && (iIndexOf = strReplace.indexOf(61)) >= 0 && (i2 = iIndexOf + 1) < strReplace.length()) {
            strReplace = strReplace.substring(i2).trim();
        }
        if (strReplace.startsWith("v1/") || strReplace.startsWith("music/") || strReplace.startsWith("uploads/")) {
            strReplace = "/" + strReplace;
        }
        if (strReplace.startsWith("/v1/uploads/media/")) {
            String strTrim2 = strReplace.substring(18).trim();
            if (strTrim2.length() <= 0 || strTrim2.indexOf(47) >= 0 || strTrim2.indexOf(92) >= 0) {
                return strReplace;
            }
            return "/v1/music/cover/" + strTrim2;
        }
        if (!strReplace.startsWith("/uploads/media/")) {
            if (!strReplace.startsWith("/music/cover/")) {
                return strReplace;
            }
            return "/v1" + strReplace;
        }
        String strTrim3 = strReplace.substring(15).trim();
        if (strTrim3.length() <= 0 || strTrim3.indexOf(47) >= 0 || strTrim3.indexOf(92) >= 0) {
            return strReplace;
        }
        return "/v1/music/cover/" + strTrim3;
    }

    public final String t(String str, boolean z2) {
        if (str == null) {
            return "";
        }
        String strTrim = str.trim();
        if (strTrim.length() == 0) {
            return "";
        }
        String strI = com.im.oldchat.bili.c.i(strTrim.replace("\\u0026", "&").replace("\\/", "/"));
        if (strI.startsWith("//")) {
            strI = "https:" + strI;
        }
        int iIndexOf = strI.indexOf(35);
        if (iIndexOf >= 0) {
            strI = strI.substring(0, iIndexOf);
        }
        int iIndexOf2 = strI.indexOf(63);
        if (iIndexOf2 >= 0) {
            strI = strI.substring(0, iIndexOf2);
        }
        if (!z2) {
            return strI;
        }
        int iLastIndexOf = strI.lastIndexOf(47);
        if (iLastIndexOf < 0) {
            iLastIndexOf = 0;
        }
        int iIndexOf3 = strI.indexOf(64, iLastIndexOf);
        if (iIndexOf3 > 0) {
            strI = strI.substring(0, iIndexOf3);
        }
        return H(strI);
    }

    public void u(String str) {
        c.a aVarE = h0.c.e(str);
        Intent intent = new Intent(this.f3455a, (Class<?>) OldViewVideoDetailActivity.class);
        String str2 = aVarE.f5437a;
        if (str2 != null && str2.length() > 0) {
            intent.putExtra("bvid", aVarE.f5437a);
        }
        long j2 = aVarE.f5438b;
        if (j2 > 0) {
            intent.putExtra("aid", j2);
        }
        long j3 = aVarE.f5439c;
        if (j3 > 0) {
            intent.putExtra("cid", j3);
        }
        String strT = t(aVarE.f5441e, false);
        if (strT.length() > 0) {
            intent.putExtra("cover_url", strT);
        }
        String str3 = aVarE.f5440d;
        if (str3 != null && str3.length() > 0) {
            intent.putExtra(MessageBundle.TITLE_ENTRY, aVarE.f5440d);
        }
        if (!(this.f3455a instanceof Activity)) {
            intent.addFlags(268435456);
        }
        this.f3455a.startActivity(intent);
    }

    public void v(String str, String str2, String str3, String str4) {
        String strG = o0.A.g(str3);
        if (strG == null || strG.length() == 0) {
            Toast.makeText(this.f3455a, "无法获取播放地址", 0).show();
            return;
        }
        Intent intent = new Intent(this.f3455a, (Class<?>) MusicPlayerActivity.class);
        if (str == null) {
            str = "";
        }
        intent.putExtra("song_name", str);
        intent.putExtra("song_url", strG);
        if (str4 == null) {
            str4 = "";
        }
        intent.putExtra("cover_url", str4);
        if (str2 == null) {
            str2 = "";
        }
        intent.putExtra("owner_name", str2);
        if (!(this.f3455a instanceof Activity)) {
            intent.addFlags(268435456);
        }
        this.f3455a.startActivity(intent);
    }

    public void w(String str) {
        if (str == null || str.isEmpty()) {
            Toast.makeText(this.f3455a, f0.j.O2, 0).show();
            return;
        }
        Intent intent = new Intent(this.f3455a, (Class<?>) RedPacketOpenActivity.class);
        intent.putExtra("packet_id", str);
        if (!(this.f3455a instanceof Activity)) {
            intent.addFlags(268435456);
        }
        this.f3455a.startActivity(intent);
    }

    public void x(j0.i iVar) {
        String str;
        if (iVar == null || (str = iVar.f5534f) == null || str.isEmpty()) {
            Toast.makeText(this.f3455a, "无法获取播放地址", 0).show();
            return;
        }
        String strG = o0.A.g(iVar.f5534f);
        if (strG == null || strG.length() == 0) {
            Toast.makeText(this.f3455a, "无法获取播放地址", 0).show();
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(strG), "video/*");
            if (!(this.f3455a instanceof Activity)) {
                intent.addFlags(268435456);
            }
            this.f3455a.startActivity(intent);
        } catch (Exception unused) {
            Toast.makeText(this.f3455a, "无法播放视频", 0).show();
        }
    }

    public final String y(String str, String[] strArr) {
        if (str != null && strArr != null) {
            String strTrim = str.trim();
            if (strTrim.length() == 0) {
                return "";
            }
            for (String str2 : strArr) {
                if (str2 != null && strTrim.startsWith(str2)) {
                    String strTrim2 = strTrim.substring(str2.length()).trim();
                    return strTrim2 == null ? "" : strTrim2;
                }
            }
        }
        return "";
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0167  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.im.oldchat.ui.M.c z(java.lang.String r24, java.lang.String r25, java.lang.String r26) {
        /*
            Method dump skipped, instruction units count: 528
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.im.oldchat.ui.M.z(java.lang.String, java.lang.String, java.lang.String):com.im.oldchat.ui.M$c");
    }
}

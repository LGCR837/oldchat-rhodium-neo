package com.im.oldchat.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.im.oldchat.ui.S;
import com.im.oldchat.ui.U;
import h0.c;
import java.util.List;
import java.util.Locale;
import l0.AbstractC0380g;
import l0.AbstractC0382h;
import o0.AbstractC0455v;
import o0.C;
import org.spongycastle.i18n.MessageBundle;

/* JADX INFO: loaded from: classes.dex */
public abstract class V extends RecyclerView.g {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List f4233c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f4234d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Context f4235e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public W f4236f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f4237g = 1.0f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public S.n f4238h;

    public class a implements AbstractC0455v.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ImageView f4239a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f4240b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f4241c;

        public a(ImageView imageView, String str, String str2) {
            this.f4239a = imageView;
            this.f4240b = str;
            this.f4241c = str2;
        }

        @Override // o0.AbstractC0455v.c
        public void a(String str) {
            String str2;
            Object tag = this.f4239a.getTag();
            if (tag == null || !this.f4240b.equals(tag) || (this.f4239a.getDrawable() instanceof BitmapDrawable) || (str2 = this.f4241c) == null || str2.length() == 0 || this.f4240b.equals(this.f4241c)) {
                return;
            }
            Context context = this.f4239a.getContext();
            if (context == null || !AbstractC0455v.d(context, this.f4240b)) {
                this.f4239a.setTag(this.f4241c);
                this.f4239a.setImageResource(f0.e.f5018g);
                AbstractC0455v.e(this.f4239a, this.f4241c);
            }
        }
    }

    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ C.d f4243a;

        public b(C.d dVar) {
            this.f4243a = dVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String str;
            if (V.this.f4238h == null || (str = this.f4243a.f7007a) == null || str.isEmpty()) {
                return;
            }
            V.this.f4238h.a(this.f4243a);
        }
    }

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f4245a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f4246b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f4247c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f4248d;
    }

    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f4249a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f4250b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f4251c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f4252d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f4253e;
    }

    public Animation A() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.4f, 1.0f);
        alphaAnimation.setDuration(400L);
        alphaAnimation.setRepeatMode(2);
        alphaAnimation.setRepeatCount(-1);
        return alphaAnimation;
    }

    public boolean B(j0.k kVar) {
        return kVar.f5558i > 0 && System.currentTimeMillis() - AbstractC0380g.b(kVar.f5558i) < 60000;
    }

    public int C(int i2) {
        return (int) ((i2 * this.f4235e.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public final String D(long j2) {
        if (j2 <= 0) {
            return "";
        }
        long j3 = j2 / 3600;
        long j4 = (j2 % 3600) / 60;
        long j5 = j2 % 60;
        return j3 > 0 ? String.format(Locale.US, "%02d:%02d:%02d", Long.valueOf(j3), Long.valueOf(j4), Long.valueOf(j5)) : String.format(Locale.US, "%02d:%02d", Long.valueOf(j4), Long.valueOf(j5));
    }

    public String E(String str) {
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

    public final void F(ImageView imageView, String str, String str2) {
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

    public final String G(String str) {
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

    public final String H(String str, boolean z2) {
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
        return Q(strI);
    }

    public void I(String str) {
        c.a aVarE = h0.c.e(str);
        Intent intent = new Intent(this.f4235e, (Class<?>) OldViewVideoDetailActivity.class);
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
        String strH = H(aVarE.f5441e, false);
        if (strH.length() > 0) {
            intent.putExtra("cover_url", strH);
        }
        String str3 = aVarE.f5440d;
        if (str3 != null && str3.length() > 0) {
            intent.putExtra(MessageBundle.TITLE_ENTRY, aVarE.f5440d);
        }
        if (!(this.f4235e instanceof Activity)) {
            intent.addFlags(268435456);
        }
        this.f4235e.startActivity(intent);
    }

    public void J(String str, String str2, String str3, String str4) {
        String strG = o0.A.g(str3);
        if (strG == null || strG.length() == 0) {
            Toast.makeText(this.f4235e, "无法获取播放地址", 0).show();
            return;
        }
        Intent intent = new Intent(this.f4235e, (Class<?>) MusicPlayerActivity.class);
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
        if (!(this.f4235e instanceof Activity)) {
            intent.addFlags(268435456);
        }
        this.f4235e.startActivity(intent);
    }

    public void K(String str) {
        if (str == null || str.isEmpty()) {
            Toast.makeText(this.f4235e, f0.j.O2, 0).show();
            return;
        }
        Intent intent = new Intent(this.f4235e, (Class<?>) RedPacketOpenActivity.class);
        intent.putExtra("packet_id", str);
        if (!(this.f4235e instanceof Activity)) {
            intent.addFlags(268435456);
        }
        this.f4235e.startActivity(intent);
    }

    public void L(j0.k kVar) {
        String str;
        if (kVar == null || (str = kVar.f5555f) == null || str.isEmpty()) {
            Toast.makeText(this.f4235e, "无法获取播放地址", 0).show();
            return;
        }
        String strG = o0.A.g(kVar.f5555f);
        if (strG == null || strG.length() == 0) {
            Toast.makeText(this.f4235e, "无法获取播放地址", 0).show();
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(strG), "video/*");
            if (!(this.f4235e instanceof Activity)) {
                intent.addFlags(268435456);
            }
            this.f4235e.startActivity(intent);
        } catch (Exception unused) {
            Toast.makeText(this.f4235e, "无法播放视频", 0).show();
        }
    }

    public final String M(String str, String[] strArr) {
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
    public com.im.oldchat.ui.V.c N(java.lang.String r24, java.lang.String r25, java.lang.String r26) {
        /*
            Method dump skipped, instruction units count: 528
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.im.oldchat.ui.V.N(java.lang.String, java.lang.String, java.lang.String):com.im.oldchat.ui.V$c");
    }

    public d O(String str, String str2) {
        String str3;
        int i2;
        int i3;
        d dVar = new d();
        String str4 = str == null ? "" : str;
        boolean zC = h0.c.c(str2);
        c.a aVarE = zC ? h0.c.e(str2) : null;
        String[] strArrSplit = str4.split("\r?\n");
        String strE = "";
        String strTrim = strE;
        String str5 = strTrim;
        int i4 = 0;
        while (true) {
            if (i4 >= strArrSplit.length) {
                break;
            }
            String str6 = strArrSplit[i4];
            String strTrim2 = str6 == null ? "" : str6.trim();
            if (strTrim2.length() != 0) {
                if (strE.length() == 0 && (strTrim2.startsWith("资源:") || strTrim2.startsWith("资源：") || strTrim2.startsWith("文件:") || strTrim2.startsWith("文件：") || strTrim2.startsWith("视频:") || strTrim2.startsWith("视频：") || strTrim2.startsWith("标题:") || strTrim2.startsWith("标题："))) {
                    int iIndexOf = strTrim2.indexOf(58);
                    if (iIndexOf < 0) {
                        iIndexOf = strTrim2.indexOf(65306);
                    }
                    if (iIndexOf >= 0 && (i3 = iIndexOf + 1) < strTrim2.length()) {
                        strE = strTrim2.substring(i3).trim();
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
        if (strE.length() == 0 && aVarE != null && (str3 = aVarE.f5440d) != null && str3.length() > 0) {
            strE = aVarE.f5440d.trim();
        }
        if (strE.length() == 0) {
            strE = E(str2);
        }
        if (strE.length() == 0) {
            strE = zC ? "B站视频" : "资源分享";
        }
        if (strTrim.length() == 0 && aVarE != null && aVarE.f5442f > 0) {
            strTrim = "时长 " + D(aVarE.f5442f);
        }
        if (strTrim.length() > 0) {
            dVar.f4250b = strTrim;
        } else if (str5.length() > 0) {
            dVar.f4250b = str5;
        } else {
            dVar.f4250b = zC ? "点击观看" : "点击下载";
        }
        dVar.f4249a = strE;
        dVar.f4251c = zC;
        String str7 = aVarE == null ? null : aVarE.f5441e;
        dVar.f4252d = H(str7, true);
        dVar.f4253e = H(str7, false);
        return dVar;
    }

    public boolean P(j0.k kVar, int i2) {
        if (i2 == 0) {
            return true;
        }
        return AbstractC0382h.e(kVar.f5558i, ((j0.k) this.f4233c.get(i2 - 1)).f5558i);
    }

    public final String Q(String str) {
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

    public void v(U.d dVar, String str) {
        View view;
        if (dVar == null || (view = dVar.f4209y) == null) {
            return;
        }
        view.setVisibility(8);
        ImageView imageView = dVar.f4210z;
        if (imageView != null) {
            imageView.setTag(null);
        }
        ImageView imageView2 = dVar.f4170A;
        if (imageView2 != null) {
            imageView2.setVisibility(0);
            dVar.f4170A.setImageResource(f0.e.f5015e0);
        }
    }

    public void w(U.d dVar, C.d dVar2) {
        LinearLayout linearLayout = dVar.f4171B;
        if (linearLayout == null) {
            return;
        }
        if (dVar2 == null) {
            linearLayout.setVisibility(8);
            dVar.f4171B.setOnClickListener(null);
            ImageView imageView = dVar.f4174E;
            if (imageView != null) {
                imageView.setVisibility(8);
                dVar.f4174E.setTag(null);
                return;
            }
            return;
        }
        String str = dVar2.f7009c;
        String str2 = (str == null || str.isEmpty()) ? dVar2.f7008b : dVar2.f7009c;
        if (str2 == null || str2.isEmpty()) {
            str2 = "对方";
        }
        dVar.f4172C.setText(str2);
        dVar.f4173D.setText(AbstractC0380g.f(dVar2.f7010d, dVar2.f7012f, dVar2.f7011e));
        dVar.f4171B.setVisibility(0);
        x(dVar.f4174E, dVar2.f7013g);
        dVar.f4171B.setOnClickListener(new b(dVar2));
    }

    public void x(ImageView imageView, String str) {
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

    public void y(U.d dVar, d dVar2) {
        View view;
        if (dVar == null || (view = dVar.f4209y) == null) {
            return;
        }
        view.setVisibility(8);
        ImageView imageView = dVar.f4210z;
        if (imageView != null) {
            imageView.setTag(null);
        }
        ImageView imageView2 = dVar.f4170A;
        if (imageView2 != null) {
            imageView2.setVisibility(0);
            dVar.f4170A.setImageResource(f0.e.f4998S);
        }
        if (dVar2 == null || !dVar2.f4251c) {
            return;
        }
        String str = dVar2.f4252d;
        String str2 = "";
        String strTrim = str == null ? "" : str.trim();
        String str3 = dVar2.f4253e;
        String strTrim2 = str3 == null ? "" : str3.trim();
        if (strTrim.length() == 0) {
            strTrim = strTrim2;
        } else {
            str2 = strTrim2;
        }
        if (strTrim.length() == 0 || dVar.f4210z == null) {
            return;
        }
        dVar.f4209y.setVisibility(0);
        F(dVar.f4210z, strTrim, str2);
    }

    public void z(U.d dVar, j0.k kVar, int i2) {
        if (dVar.f4196a0 == null) {
            return;
        }
        if (!P(kVar, i2)) {
            dVar.f4196a0.setVisibility(8);
            return;
        }
        String strA = AbstractC0382h.a(kVar.f5558i);
        if (strA.isEmpty()) {
            dVar.f4196a0.setVisibility(8);
        } else {
            dVar.f4196a0.setText(strA);
            dVar.f4196a0.setVisibility(0);
        }
    }
}

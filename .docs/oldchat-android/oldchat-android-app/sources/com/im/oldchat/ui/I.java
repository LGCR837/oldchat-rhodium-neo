package com.im.oldchat.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.im.oldchat.ui.M;
import com.im.oldchat.ui.N;
import java.util.List;
import l0.AbstractC0403s;
import l0.AbstractC0406v;
import l0.AbstractC0407w;
import o.c;
import o0.AbstractC0441g;
import o0.AbstractC0455v;
import o0.C;
import org.spongycastle.asn1.x509.DisplayText;

/* JADX INFO: loaded from: classes.dex */
public abstract class I extends H {

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f2977a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ j0.i f2978b;

        public a(boolean z2, j0.i iVar) {
            this.f2977a = z2;
            this.f2978b = iVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f2977a) {
                I.this.x(this.f2978b);
            } else {
                ImagePreviewActivity.f0(I.this.f3455a, this.f2978b.f5534f);
            }
        }
    }

    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.i f2980a;

        public b(j0.i iVar) {
            this.f2980a = iVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            I.this.f3464j.D(this.f2980a);
        }
    }

    public class c implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2982a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f2983b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f2984c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f2985d;

        public c(String str, String str2, String str3, String str4) {
            this.f2982a = str;
            this.f2983b = str2;
            this.f2984c = str3;
            this.f2985d = str4;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            I.this.v(this.f2982a, this.f2983b, this.f2984c, this.f2985d);
        }
    }

    public class d implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2987a;

        public d(String str) {
            this.f2987a = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            I.this.u(this.f2987a);
        }
    }

    public class e implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2989a;

        public class a implements DialogInterface.OnClickListener {
            public a() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                try {
                    I.this.f3455a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(e.this.f2989a)));
                } catch (Exception unused) {
                }
            }
        }

        public class b implements DialogInterface.OnClickListener {
            public b() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                e eVar = e.this;
                AbstractC0441g.a(I.this.f3455a, eVar.f2989a);
            }
        }

        public e(String str) {
            this.f2989a = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            new c.a(I.this.f3455a).t("下载资源").i(this.f2989a).q("复制链接", new b()).k("浏览器下载", new a()).m("取消", null).v();
        }
    }

    public class f implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2993a;

        public f(String str) {
            this.f2993a = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            I.this.w(this.f2993a);
        }
    }

    public class g implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ C.a f2995a;

        public g(C.a aVar) {
            this.f2995a = aVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractC0407w.e(I.this.f3455a, this.f2995a);
        }
    }

    public I(Context context, List list, String str) {
        super(context, list, str);
    }

    @Override // com.im.oldchat.ui.H
    public void E0(N.d dVar, j0.i iVar, o0.C c2, String str, boolean z2) {
        String string;
        String string2;
        if ("image".equals(str) || "video".equals(str)) {
            boolean zEquals = "video".equals(str);
            View view = dVar.f3491B;
            if (view != null) {
                view.setVisibility(0);
            }
            dVar.f3492C.setVisibility(0);
            AbstractC0455v.e(dVar.f3492C, !TextUtils.isEmpty(iVar.f5535g) ? iVar.f5535g : iVar.f5534f);
            dVar.f3492C.setOnClickListener(new a(zEquals, iVar));
            ImageView imageView = dVar.f3493D;
            if (imageView != null) {
                imageView.setVisibility(zEquals ? 0 : 8);
                return;
            }
            return;
        }
        if ("voice".equals(str)) {
            dVar.f3494E.setVisibility(0);
            int iMin = Math.min(Math.max(1, iVar.f5536h / 1000), 60);
            boolean zX = this.f3464j.x(iVar);
            int iC = c(((iMin * 120) / 60) + 80);
            if (zX) {
                iC = c(DisplayText.DISPLAY_TEXT_MAXIMUM_SIZE);
            }
            ViewGroup.LayoutParams layoutParams = dVar.f3494E.getLayoutParams();
            if (layoutParams instanceof LinearLayout.LayoutParams) {
                layoutParams.width = iC;
                dVar.f3494E.setLayoutParams(layoutParams);
            }
            dVar.f3496G.setText(this.f3464j.t(iVar, iMin));
            ProgressBar progressBar = dVar.f3498I;
            if (progressBar != null) {
                progressBar.setVisibility(zX ? 0 : 8);
            }
            dVar.f3494E.setOnClickListener(new b(iVar));
            if (this.f3464j.y(iVar)) {
                dVar.f3495F.startAnimation(l());
            } else {
                dVar.f3495F.clearAnimation();
            }
            L0(dVar, c2);
            return;
        }
        if ("music".equals(str) || ("resource".equals(str) && c2 != null && "music".equals(c2.f6991b))) {
            dVar.f3522h.setVisibility(8);
            dVar.f3524j.setVisibility(0);
            dVar.f3528n.setVisibility(0);
            String strG = o0.A.g(iVar.f5534f);
            M.c cVarZ = z(c2.f6990a, strG, iVar.f5535g);
            dVar.f3526l.setText(cVarZ.f3046a);
            dVar.f3527m.setText(cVarZ.f3048c);
            dVar.f3528n.setText("播放");
            dVar.f3525k.setImageResource(f0.e.f5015e0);
            e(dVar, cVarZ.f3049d);
            if (strG != null && strG.length() > 0) {
                c cVar = new c(cVarZ.f3046a, cVarZ.f3047b, strG, cVarZ.f3049d);
                dVar.f3523i.setOnClickListener(cVar);
                dVar.f3524j.setOnClickListener(cVar);
                dVar.f3528n.setOnClickListener(cVar);
                return;
            }
            dVar.f3527m.setText("歌曲链接不可用");
            dVar.f3528n.setVisibility(8);
            dVar.f3523i.setOnClickListener(null);
            dVar.f3524j.setOnClickListener(null);
            dVar.f3528n.setOnClickListener(null);
            return;
        }
        if ("resource".equals(str)) {
            dVar.f3522h.setVisibility(8);
            dVar.f3524j.setVisibility(0);
            String strG2 = o0.A.g(iVar.f5534f);
            if (strG2 == null || strG2.length() <= 0) {
                dVar.f3526l.setText(A(c2.f6990a, "").f3050a);
                string = z2 ? AbstractC0403s.d(c2.f6990a) : "";
                dVar.f3527m.setText(string.length() > 0 ? string : "链接不可用");
                dVar.f3525k.setImageResource(f0.e.f5000U);
                dVar.f3528n.setText(string.length() > 0 ? "上传中" : "下载");
                dVar.f3528n.setVisibility(8);
                i(dVar, null);
                dVar.f3523i.setOnClickListener(null);
                dVar.f3524j.setOnClickListener(null);
                dVar.f3528n.setOnClickListener(null);
                return;
            }
            boolean zC = h0.c.c(strG2);
            M.d dVarA = A(c2.f6990a, strG2);
            dVar.f3526l.setText(dVarA.f3050a);
            dVar.f3527m.setText(dVarA.f3051b);
            dVar.f3528n.setVisibility(0);
            dVar.f3528n.setText(zC ? "观看" : "下载");
            dVar.f3525k.setImageResource(zC ? f0.e.f4998S : f0.e.f5000U);
            i(dVar, dVarA);
            View.OnClickListener dVar2 = zC ? new d(strG2) : new e(strG2);
            dVar.f3523i.setOnClickListener(dVar2);
            dVar.f3524j.setOnClickListener(dVar2);
            dVar.f3528n.setOnClickListener(dVar2);
            return;
        }
        if (!"red_packet".equals(str)) {
            dVar.f3522h.setVisibility(0);
            if (!AbstractC0406v.i(c2)) {
                dVar.f3522h.setText(c2.f6990a);
                return;
            }
            dVar.f3522h.setText(AbstractC0406v.a(this.f3455a, c2.f6995f));
            g gVar = new g(c2.f6995f);
            dVar.f3523i.setOnClickListener(gVar);
            dVar.f3522h.setOnClickListener(gVar);
            return;
        }
        dVar.f3532r.setVisibility(0);
        o0.S sA = o0.S.a(iVar.f5532d);
        boolean z3 = sA.b() || o0.T.b(this.f3455a, sA.f7029a);
        String str2 = sA.f7030b;
        string = str2 != null ? str2 : "";
        if (string.isEmpty()) {
            string = this.f3455a.getString(f0.j.W2);
        }
        dVar.f3534t.setText(string);
        int i2 = sA.f7031c;
        if (i2 <= 0 || sA.f7032d <= 0) {
            string2 = this.f3455a.getString(f0.j.z1);
        } else {
            string2 = this.f3455a.getString(f0.j.x1, Integer.valueOf(i2)) + " · " + this.f3455a.getString(f0.j.y1, Integer.valueOf(sA.f7032d));
        }
        dVar.f3535u.setText(string2);
        TextView textView = dVar.f3536v;
        if (textView != null) {
            textView.setText(z3 ? f0.j.T2 : f0.j.R2);
        }
        ImageView imageView2 = dVar.f3537w;
        if (imageView2 != null) {
            if (z2) {
                if (iVar.f5538j > 0) {
                    imageView2.setImageResource(f0.e.f5003X);
                } else {
                    imageView2.setImageResource(f0.e.f5004Y);
                }
                dVar.f3537w.setColorFilter(-1711276033);
                dVar.f3537w.setVisibility(0);
            } else {
                imageView2.setVisibility(8);
            }
        }
        f fVar = new f(sA.f7029a);
        dVar.f3523i.setOnClickListener(fVar);
        dVar.f3532r.setOnClickListener(fVar);
    }

    public final void L0(N.d dVar, o0.C c2) {
        if (dVar == null || dVar.f3497H == null) {
            return;
        }
        String strTrim = c2 == null ? "" : c2.f6992c;
        if (strTrim != null) {
            strTrim = strTrim.trim();
        }
        if (strTrim == null || strTrim.length() == 0) {
            dVar.f3497H.setVisibility(8);
            dVar.f3497H.setText("");
        } else {
            dVar.f3497H.setText(strTrim);
            dVar.f3497H.setVisibility(0);
        }
    }
}

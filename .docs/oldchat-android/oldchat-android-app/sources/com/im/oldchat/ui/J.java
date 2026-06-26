package com.im.oldchat.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.im.oldchat.ui.N;
import d.AbstractC0235a;
import java.util.List;
import l0.R0;
import o0.AbstractC0434b0;
import o0.AbstractC0451q;
import o0.AbstractC0455v;

/* JADX INFO: loaded from: classes.dex */
public abstract class J extends I {

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f3000a;

        public a(String str) {
            this.f3000a = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String strI0 = J.this.I0(this.f3000a);
            if (strI0.length() == 0) {
                return;
            }
            Intent intent = new Intent(J.this.f3455a, (Class<?>) UserSpaceActivity.class);
            intent.putExtra("uid", strI0);
            J.this.f3455a.startActivity(intent);
        }
    }

    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f3002a;

        public b(String str) {
            this.f3002a = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String strI0 = J.this.I0(this.f3002a);
            if (strI0.length() == 0) {
                return;
            }
            Intent intent = new Intent(J.this.f3455a, (Class<?>) UserSpaceActivity.class);
            intent.putExtra("uid", strI0);
            J.this.f3455a.startActivity(intent);
        }
    }

    public J(Context context, List list, String str) {
        super(context, list, str);
    }

    @Override // com.im.oldchat.ui.H
    public void F0(N.d dVar, j0.i iVar, String str, boolean z2, boolean z3, boolean z4) {
        boolean z5;
        boolean z6 = false;
        if (!z4) {
            dVar.f3506Q.setGravity(8388611);
            dVar.f3519e.setVisibility(z2 ? 4 : 0);
            ImageView imageView = dVar.f3520f;
            if (imageView != null) {
                imageView.setVisibility(8);
            }
            String strM0 = M0(iVar.f5531c);
            String strC = C(strM0);
            int iD = D(strM0);
            TextView textView = dVar.f3516b;
            if (strC == null) {
                strC = "";
            }
            textView.setText(strC);
            LinearLayout linearLayout = dVar.f3515a;
            if (linearLayout != null) {
                if (z2) {
                    linearLayout.setVisibility(8);
                } else {
                    linearLayout.setVisibility(0);
                    dVar.f3515a.setGravity(8388627);
                    ViewGroup.LayoutParams layoutParams = dVar.f3515a.getLayoutParams();
                    if (layoutParams instanceof LinearLayout.LayoutParams) {
                        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                        layoutParams2.width = -2;
                        layoutParams2.gravity = 8388611;
                        dVar.f3515a.setLayoutParams(layoutParams);
                    }
                }
            }
            LinearLayout linearLayout2 = dVar.f3521g;
            if (linearLayout2 != null) {
                linearLayout2.setGravity(8388611);
                ViewGroup.LayoutParams layoutParams3 = dVar.f3521g.getLayoutParams();
                if (layoutParams3 instanceof LinearLayout.LayoutParams) {
                    ((LinearLayout.LayoutParams) layoutParams3).gravity = 8388611;
                    dVar.f3521g.setLayoutParams(layoutParams3);
                }
            }
            TextView textView2 = dVar.f3516b;
            if (textView2 != null) {
                textView2.setGravity(8388611);
            }
            if (z2) {
                TextView textView3 = dVar.f3517c;
                if (textView3 != null) {
                    textView3.setVisibility(8);
                }
                R0.a(dVar.f3518d, "");
            } else {
                j(dVar.f3517c, iD);
                R0.a(dVar.f3518d, E(strM0));
            }
            String strB = (String) this.f3460f.get(strM0);
            if ((strB == null || strB.isEmpty()) && (strB = AbstractC0451q.b(this.f3455a, strM0)) != null && !strB.isEmpty()) {
                this.f3460f.put(strM0, strB);
            }
            if (z2) {
                dVar.f3519e.setOnClickListener(null);
            } else {
                if (strB == null || strB.isEmpty()) {
                    dVar.f3519e.setImageResource(f0.e.f4999T);
                    dVar.f3519e.setTag(null);
                } else {
                    Object tag = dVar.f3519e.getTag();
                    if (tag == null || !strB.equals(tag)) {
                        dVar.f3519e.setTag(strB);
                        AbstractC0455v.h(dVar.f3519e, strB);
                    }
                }
                dVar.f3519e.setOnClickListener(new b(strM0));
            }
            if ("red_packet".equals(str)) {
                o0.S sA = o0.S.a(iVar.f5532d);
                boolean z7 = sA.b() || o0.T.b(this.f3455a, sA.f7029a);
                J(dVar, z7 ? f0.e.f4982C : f0.e.f4981B);
                z6 = z7;
            } else {
                J(dVar, f0.e.f5025l);
            }
            int iB = AbstractC0235a.b(this.f3455a, f0.d.f4973j);
            int iB2 = AbstractC0235a.b(this.f3455a, f0.d.f4974k);
            int i2 = o0.X.i(this.f3455a);
            dVar.f3522h.setTextColor(iB);
            dVar.f3496G.setTextColor(iB);
            TextView textView4 = dVar.f3497H;
            if (textView4 != null) {
                textView4.setTextColor(iB);
            }
            dVar.f3495F.setColorFilter(iB);
            dVar.f3526l.setTextColor(iB);
            dVar.f3527m.setTextColor(iB2);
            dVar.f3528n.setTextColor(i2);
            dVar.f3525k.setColorFilter(i2);
            dVar.f3534t.setTextColor(z6 ? -352321537 : -1);
            dVar.f3535u.setTextColor(z6 ? -1073741825 : -855638017);
            TextView textView5 = dVar.f3536v;
            if (textView5 != null) {
                textView5.setTextColor(z6 ? -1442840577 : -1711276033);
            }
            ImageView imageView2 = dVar.f3537w;
            if (imageView2 != null) {
                imageView2.setVisibility(8);
            }
            LinearLayout linearLayout3 = dVar.f3499J;
            if (linearLayout3 != null) {
                linearLayout3.setVisibility(8);
            }
            LinearLayout linearLayout4 = dVar.f3502M;
            if (linearLayout4 != null) {
                linearLayout4.setVisibility(8);
                return;
            }
            return;
        }
        dVar.f3506Q.setGravity(8388613);
        dVar.f3519e.setVisibility(8);
        ImageView imageView3 = dVar.f3520f;
        if (imageView3 != null) {
            imageView3.setVisibility(z2 ? 4 : 0);
        }
        LinearLayout linearLayout5 = dVar.f3515a;
        if (linearLayout5 != null) {
            if (z2) {
                linearLayout5.setVisibility(8);
            } else {
                linearLayout5.setVisibility(0);
                dVar.f3515a.setGravity(8388629);
                ViewGroup.LayoutParams layoutParams4 = dVar.f3515a.getLayoutParams();
                if (layoutParams4 instanceof LinearLayout.LayoutParams) {
                    LinearLayout.LayoutParams layoutParams5 = (LinearLayout.LayoutParams) layoutParams4;
                    layoutParams5.width = -2;
                    layoutParams5.gravity = 8388613;
                    dVar.f3515a.setLayoutParams(layoutParams4);
                }
            }
        }
        LinearLayout linearLayout6 = dVar.f3521g;
        if (linearLayout6 != null) {
            linearLayout6.setGravity(8388613);
            ViewGroup.LayoutParams layoutParams6 = dVar.f3521g.getLayoutParams();
            if (layoutParams6 instanceof LinearLayout.LayoutParams) {
                ((LinearLayout.LayoutParams) layoutParams6).gravity = 8388613;
                dVar.f3521g.setLayoutParams(layoutParams6);
            }
        }
        TextView textView6 = dVar.f3516b;
        if (textView6 != null) {
            textView6.setGravity(8388613);
        }
        String strB2 = o0.F.b(this.f3455a);
        if (strB2 == null || strB2.length() <= 0) {
            strB2 = iVar.f5531c;
        }
        String strD = AbstractC0434b0.d(this.f3455a, strB2);
        if (strD != null && strD.length() != 0) {
            strB2 = strD;
        }
        String strC2 = C(strB2);
        TextView textView7 = dVar.f3516b;
        if (textView7 != null) {
            if (strC2 == null) {
                strC2 = "";
            }
            textView7.setText(strC2);
        }
        TextView textView8 = dVar.f3517c;
        if (textView8 != null) {
            if (z2) {
                textView8.setVisibility(8);
            } else {
                j(textView8, D(strB2));
            }
        }
        if (z2) {
            R0.a(dVar.f3518d, "");
        } else {
            R0.a(dVar.f3518d, E(strB2));
        }
        String strB3 = (String) this.f3460f.get(strB2);
        if ((strB3 == null || strB3.isEmpty()) && (strB3 = AbstractC0451q.b(this.f3455a, strB2)) != null && !strB3.isEmpty()) {
            this.f3460f.put(strB2, strB3);
        }
        ImageView imageView4 = dVar.f3520f;
        if (imageView4 != null) {
            if (z2) {
                imageView4.setOnClickListener(null);
            } else {
                if (strB3 == null || strB3.isEmpty()) {
                    dVar.f3520f.setImageResource(f0.e.f4999T);
                    dVar.f3520f.setTag(null);
                } else {
                    Object tag2 = dVar.f3520f.getTag();
                    if (tag2 == null || !strB3.equals(tag2)) {
                        dVar.f3520f.setTag(strB3);
                        AbstractC0455v.h(dVar.f3520f, strB3);
                    }
                }
                dVar.f3520f.setOnClickListener(new a(strB2));
            }
        }
        if ("red_packet".equals(str)) {
            o0.S sA2 = o0.S.a(iVar.f5532d);
            z5 = sA2.b() || o0.T.b(this.f3455a, sA2.f7029a);
            J(dVar, z5 ? f0.e.f4984E : f0.e.f4983D);
        } else {
            J(dVar, f0.e.f5022i);
            z5 = false;
        }
        dVar.f3522h.setTextColor(-1);
        dVar.f3496G.setTextColor(-1);
        TextView textView9 = dVar.f3497H;
        if (textView9 != null) {
            textView9.setTextColor(AbstractC0235a.b(this.f3455a, f0.d.f4973j));
        }
        dVar.f3495F.setColorFilter(-1);
        dVar.f3526l.setTextColor(-1);
        dVar.f3527m.setTextColor(-855638017);
        dVar.f3528n.setTextColor(-1);
        dVar.f3525k.setColorFilter(-1);
        dVar.f3534t.setTextColor(z5 ? -352321537 : -1);
        dVar.f3535u.setTextColor(z5 ? -1073741825 : -855638017);
        TextView textView10 = dVar.f3536v;
        if (textView10 != null) {
            textView10.setTextColor(z5 ? -1442840577 : -1711276033);
        }
        ImageView imageView5 = dVar.f3537w;
        if (imageView5 != null && imageView5.getVisibility() == 0) {
            dVar.f3537w.setColorFilter(z5 ? -1996488705 : -1711276033);
        }
        boolean z8 = "image".equals(str) || "video".equals(str) || "voice".equals(str);
        if (!z3) {
            h(dVar, iVar.f5538j, z8);
            return;
        }
        LinearLayout linearLayout7 = dVar.f3499J;
        if (linearLayout7 != null) {
            linearLayout7.setVisibility(8);
        }
        LinearLayout linearLayout8 = dVar.f3502M;
        if (linearLayout8 != null) {
            linearLayout8.setVisibility(8);
        }
    }

    public final String M0(String str) {
        String strD = AbstractC0434b0.d(this.f3455a, str);
        return (strD == null || strD.length() == 0) ? str == null ? "" : str : strD;
    }
}

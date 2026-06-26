package com.im.oldchat.ui;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.im.oldchat.ui.AbstractC0227s;
import com.im.oldchat.ui.G;
import java.io.File;
import o0.AbstractC0446l;
import org.spongycastle.i18n.TextBundle;
import v.C0475d;
import x.U;

/* JADX INFO: loaded from: classes.dex */
public abstract class F {

    public class a implements View.OnLongClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f2813a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ j0.i f2814b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ o0.C f2815c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ boolean f2816d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ f f2817e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ G.a f2818f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ e f2819g;

        /* JADX INFO: renamed from: com.im.oldchat.ui.F$a$a, reason: collision with other inner class name */
        public class C0026a implements U.c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ boolean f2820a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ String f2821b;

            public C0026a(boolean z2, String str) {
                this.f2820a = z2;
                this.f2821b = str;
            }

            @Override // x.U.c
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case 1:
                        a aVar = a.this;
                        G.a aVar2 = aVar.f2818f;
                        o0.C c2 = aVar.f2815c;
                        aVar2.a(c2 != null ? c2.f6990a : aVar.f2814b.f5532d);
                        break;
                    case 2:
                        a aVar3 = a.this;
                        e eVar = aVar3.f2819g;
                        String strA = eVar == null ? null : eVar.a(aVar3.f2814b.f5531c);
                        a aVar4 = a.this;
                        aVar4.f2818f.c(aVar4.f2814b, strA);
                        break;
                    case 3:
                        a aVar5 = a.this;
                        l0.O.e(aVar5.f2813a, aVar5.f2814b);
                        break;
                    case 4:
                        a aVar6 = a.this;
                        F.i(aVar6.f2813a, aVar6.f2814b, this.f2820a);
                        break;
                    case 5:
                        a aVar7 = a.this;
                        F.h(aVar7.f2813a, aVar7.f2814b);
                        break;
                    case 6:
                        a aVar8 = a.this;
                        Context context = aVar8.f2813a;
                        String str = this.f2821b;
                        j0.i iVar = aVar8.f2814b;
                        AbstractC0228t.a(context, str, iVar.f5529a, iVar.f5531c, iVar.f5534f, "group");
                        break;
                    case 7:
                        a aVar9 = a.this;
                        aVar9.f2818f.h(aVar9.f2814b);
                        break;
                    case 8:
                        a aVar10 = a.this;
                        aVar10.f2818f.f(aVar10.f2814b);
                        break;
                    case 9:
                        a aVar11 = a.this;
                        aVar11.f2818f.e(aVar11.f2814b);
                        break;
                    case 10:
                        a aVar12 = a.this;
                        aVar12.f2818f.d(aVar12.f2814b);
                        break;
                }
                return true;
            }
        }

        public a(Context context, j0.i iVar, o0.C c2, boolean z2, f fVar, G.a aVar, e eVar) {
            this.f2813a = context;
            this.f2814b = iVar;
            this.f2815c = c2;
            this.f2816d = z2;
            this.f2817e = fVar;
            this.f2818f = aVar;
            this.f2819g = eVar;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            String str;
            String str2;
            int i2;
            String str3;
            Context context = this.f2813a;
            x.U u2 = new x.U(new C0475d(context, o0.V.g(context) ? f0.k.f5259p : f0.k.f5260q), view);
            String str4 = this.f2814b.f5533e;
            String lowerCase = str4 == null ? TextBundle.TEXT_ENTRY : str4.toLowerCase();
            boolean zEquals = "voice".equals(lowerCase);
            o0.C c2 = this.f2815c;
            boolean z2 = c2 != null && "emoji".equals(c2.f6991b);
            boolean z3 = ("image".equals(lowerCase) || z2) && (str = this.f2814b.f5534f) != null && str.length() > 0;
            boolean z4 = !this.f2816d && z2 && (str3 = this.f2814b.f5534f) != null && str3.length() > 0;
            boolean z5 = ("image".equals(lowerCase) || zEquals || "video".equals(lowerCase)) && (str2 = this.f2814b.f5534f) != null && str2.length() > 0;
            if (TextBundle.TEXT_ENTRY.equals(lowerCase)) {
                u2.a().add(0, 1, 0, f0.j.n1);
                i2 = 1;
            } else {
                i2 = 0;
            }
            u2.a().add(0, 2, i2, f0.j.r1);
            u2.a().add(0, 9, i2 + 1, f0.j.p1);
            int i3 = i2 + 3;
            u2.a().add(0, 10, i2 + 2, f0.j.q1);
            if (!this.f2816d) {
                u2.a().add(0, 3, i3, f0.j.c3);
                i3 = i2 + 4;
            }
            if (z3) {
                u2.a().add(0, 4, i3, f0.j.t1);
                i3++;
            }
            if (z4) {
                u2.a().add(0, 5, i3, f0.j.m1);
                i3++;
            }
            if (z5) {
                u2.a().add(0, 6, i3, f0.j.o1);
                i3++;
            }
            if (zEquals) {
                u2.a().add(0, 8, i3, f0.j.f5242y);
                i3++;
            }
            f fVar = this.f2817e;
            if (fVar != null && fVar.a(this.f2814b)) {
                u2.a().add(0, 7, i3, f0.j.s1);
            }
            u2.c(new C0026a(z2, lowerCase));
            u2.d();
            return true;
        }
    }

    public class b implements View.OnLongClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ G.a f2823a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ j0.i f2824b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f2825c;

        public b(G.a aVar, j0.i iVar, String str) {
            this.f2823a = aVar;
            this.f2824b = iVar;
            this.f2825c = str;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            this.f2823a.b(this.f2824b, this.f2825c);
            return true;
        }
    }

    public class c implements AbstractC0227s.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f2826a;

        public c(Context context) {
            this.f2826a = context;
        }

        @Override // com.im.oldchat.ui.AbstractC0227s.d
        public void a(boolean z2, String str) {
            Toast.makeText(this.f2826a, str, 0).show();
        }
    }

    public class d implements AbstractC0446l.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f2827a;

        public d(Context context) {
            this.f2827a = context;
        }

        @Override // o0.AbstractC0446l.c
        public void a(boolean z2, String str, File file) {
            Toast.makeText(this.f2827a, str, 0).show();
        }
    }

    public interface e {
        String a(String str);
    }

    public interface f {
        boolean a(j0.i iVar);
    }

    public static void c(Context context, View view, View view2, View view3, View view4, View view5, ImageView imageView, j0.i iVar, o0.C c2, boolean z2, G.a aVar, e eVar, f fVar) {
        if (aVar == null) {
            return;
        }
        a aVar2 = new a(context, iVar, c2, z2, fVar, aVar, eVar);
        d(view, aVar2);
        d(view2, aVar2);
        d(view3, aVar2);
        d(view4, aVar2);
        d(view5, aVar2);
        if (imageView != null && !z2) {
            imageView.setOnLongClickListener(new b(aVar, iVar, eVar != null ? eVar.a(iVar.f5531c) : null));
        } else if (imageView != null) {
            imageView.setOnLongClickListener(null);
        }
    }

    public static void d(View view, View.OnLongClickListener onLongClickListener) {
        if (view != null) {
            view.setOnLongClickListener(onLongClickListener);
        }
    }

    public static void e(View view, View view2, View view3, View view4, View view5, ImageView imageView) {
        f(view);
        f(view2);
        f(view3);
        f(view4);
        f(view5);
        f(imageView);
    }

    public static void f(View view) {
        if (view != null) {
            view.setOnLongClickListener(null);
        }
    }

    public static boolean g(String str) {
        return str != null && str.toLowerCase().contains(".gif");
    }

    public static void h(Context context, j0.i iVar) {
        String str;
        if (iVar == null || (str = iVar.f5534f) == null || str.length() == 0) {
            return;
        }
        AbstractC0227s.F(context, iVar.f5534f, g(iVar.f5534f), new c(context));
    }

    public static void i(Context context, j0.i iVar, boolean z2) {
        String str;
        if (iVar == null || (str = iVar.f5534f) == null || str.length() == 0) {
            return;
        }
        AbstractC0446l.j(context, iVar.f5534f, "oldchat_img_", (z2 && g(iVar.f5534f)) ? ".gif" : ".jpg", new d(context));
    }
}

package com.im.oldchat.ui;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.im.oldchat.ui.AbstractC0227s;
import com.im.oldchat.ui.S;
import java.io.File;
import o0.AbstractC0446l;
import org.spongycastle.i18n.TextBundle;
import v.C0475d;
import x.U;

/* JADX INFO: loaded from: classes.dex */
public abstract class Q {

    public class a implements View.OnLongClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f3916a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ j0.k f3917b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ o0.C f3918c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ boolean f3919d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ d f3920e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ S.m f3921f;

        /* JADX INFO: renamed from: com.im.oldchat.ui.Q$a$a, reason: collision with other inner class name */
        public class C0029a implements U.c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ boolean f3922a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ String f3923b;

            public C0029a(boolean z2, String str) {
                this.f3922a = z2;
                this.f3923b = str;
            }

            @Override // x.U.c
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case 1:
                        a aVar = a.this;
                        S.m mVar = aVar.f3921f;
                        o0.C c2 = aVar.f3918c;
                        mVar.a(c2 != null ? c2.f6990a : aVar.f3917b.f5553d);
                        break;
                    case 2:
                        a aVar2 = a.this;
                        aVar2.f3921f.d(aVar2.f3917b);
                        break;
                    case 3:
                        a aVar3 = a.this;
                        l0.O.d(aVar3.f3916a, aVar3.f3917b);
                        break;
                    case 4:
                        a aVar4 = a.this;
                        Q.i(aVar4.f3916a, aVar4.f3917b, this.f3922a);
                        break;
                    case 5:
                        a aVar5 = a.this;
                        Q.h(aVar5.f3916a, aVar5.f3917b);
                        break;
                    case 6:
                        a aVar6 = a.this;
                        Context context = aVar6.f3916a;
                        String str = this.f3923b;
                        j0.k kVar = aVar6.f3917b;
                        AbstractC0228t.a(context, str, kVar.f5550a, kVar.f5552c, kVar.f5555f, "direct");
                        break;
                    case 7:
                        a aVar7 = a.this;
                        aVar7.f3921f.c(aVar7.f3917b);
                        break;
                    case 8:
                        a aVar8 = a.this;
                        aVar8.f3921f.f(aVar8.f3917b);
                        break;
                    case 9:
                        a aVar9 = a.this;
                        aVar9.f3921f.e(aVar9.f3917b);
                        break;
                    case 10:
                        a aVar10 = a.this;
                        aVar10.f3921f.g(aVar10.f3917b);
                        break;
                }
                return true;
            }
        }

        public a(Context context, j0.k kVar, o0.C c2, boolean z2, d dVar, S.m mVar) {
            this.f3916a = context;
            this.f3917b = kVar;
            this.f3918c = c2;
            this.f3919d = z2;
            this.f3920e = dVar;
            this.f3921f = mVar;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            String str;
            String str2;
            int i2;
            d dVar;
            String str3;
            Context context = this.f3916a;
            x.U u2 = new x.U(new C0475d(context, o0.V.g(context) ? f0.k.f5259p : f0.k.f5260q), view);
            String str4 = this.f3917b.f5554e;
            String lowerCase = str4 == null ? TextBundle.TEXT_ENTRY : str4.toLowerCase();
            boolean zEquals = "voice".equals(lowerCase);
            o0.C c2 = this.f3918c;
            boolean z2 = c2 != null && "emoji".equals(c2.f6991b);
            boolean z3 = ("image".equals(lowerCase) || z2) && (str = this.f3917b.f5555f) != null && str.length() > 0;
            boolean z4 = !this.f3919d && z2 && (str3 = this.f3917b.f5555f) != null && str3.length() > 0;
            boolean z5 = ("image".equals(lowerCase) || zEquals || "video".equals(lowerCase)) && (str2 = this.f3917b.f5555f) != null && str2.length() > 0;
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
            if (!this.f3919d) {
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
            if (this.f3919d && (dVar = this.f3920e) != null && dVar.a(this.f3917b)) {
                u2.a().add(0, 7, i3, f0.j.s1);
            }
            u2.c(new C0029a(z2, lowerCase));
            u2.d();
            return true;
        }
    }

    public class b implements AbstractC0227s.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f3925a;

        public b(Context context) {
            this.f3925a = context;
        }

        @Override // com.im.oldchat.ui.AbstractC0227s.d
        public void a(boolean z2, String str) {
            Toast.makeText(this.f3925a, str, 0).show();
        }
    }

    public class c implements AbstractC0446l.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f3926a;

        public c(Context context) {
            this.f3926a = context;
        }

        @Override // o0.AbstractC0446l.c
        public void a(boolean z2, String str, File file) {
            Toast.makeText(this.f3926a, str, 0).show();
        }
    }

    public interface d {
        boolean a(j0.k kVar);
    }

    public static void c(Context context, View view, View view2, View view3, View view4, View view5, j0.k kVar, o0.C c2, boolean z2, S.m mVar, d dVar) {
        if (mVar == null) {
            return;
        }
        a aVar = new a(context, kVar, c2, z2, dVar, mVar);
        d(view, aVar);
        d(view2, aVar);
        d(view3, aVar);
        d(view4, aVar);
        d(view5, aVar);
    }

    public static void d(View view, View.OnLongClickListener onLongClickListener) {
        if (view != null) {
            view.setOnLongClickListener(onLongClickListener);
        }
    }

    public static void e(View view, View view2, View view3, View view4, View view5) {
        f(view);
        f(view2);
        f(view3);
        f(view4);
        f(view5);
    }

    public static void f(View view) {
        if (view != null) {
            view.setOnLongClickListener(null);
        }
    }

    public static boolean g(String str) {
        return str != null && str.toLowerCase().contains(".gif");
    }

    public static void h(Context context, j0.k kVar) {
        String str;
        if (kVar == null || (str = kVar.f5555f) == null || str.length() == 0) {
            return;
        }
        AbstractC0227s.F(context, kVar.f5555f, g(kVar.f5555f), new b(context));
    }

    public static void i(Context context, j0.k kVar, boolean z2) {
        String str;
        if (kVar == null || (str = kVar.f5555f) == null || str.length() == 0) {
            return;
        }
        AbstractC0446l.j(context, kVar.f5555f, "oldchat_img_", (z2 && g(kVar.f5555f)) ? ".gif" : ".jpg", new c(context));
    }
}

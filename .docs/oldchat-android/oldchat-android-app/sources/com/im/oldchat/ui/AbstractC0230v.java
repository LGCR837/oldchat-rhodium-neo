package com.im.oldchat.ui;

import android.R;
import android.app.Activity;
import android.content.DialogInterface;
import android.widget.Toast;
import com.im.oldchat.ui.f0;
import g0.d;
import java.util.List;
import l0.AbstractC0406v;
import o.c;
import org.json.JSONObject;
import org.spongycastle.i18n.TextBundle;

/* JADX INFO: renamed from: com.im.oldchat.ui.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0230v {

    /* JADX INFO: renamed from: com.im.oldchat.ui.v$a */
    public class a implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f4766a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f4767b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ List f4768c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ f f4769d;

        public a(Activity activity, String str, List list, f fVar) {
            this.f4766a = activity;
            this.f4767b = str;
            this.f4768c = list;
            this.f4769d = fVar;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            if (i2 == 0) {
                AbstractC0230v.e(this.f4766a, this.f4767b, this.f4768c, this.f4769d);
            } else {
                AbstractC0230v.f(this.f4766a, this.f4767b, this.f4768c, this.f4769d);
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.v$b */
    public class b implements f0.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f4770a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f4771b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ List f4772c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ f f4773d;

        public b(Activity activity, String str, List list, f fVar) {
            this.f4770a = activity;
            this.f4771b = str;
            this.f4772c = list;
            this.f4773d = fVar;
        }

        @Override // com.im.oldchat.ui.f0.e
        public void a(j0.w wVar) {
            String str;
            if (wVar == null || (str = wVar.f5646b) == null || str.length() == 0) {
                return;
            }
            AbstractC0230v.g(this.f4770a, this.f4771b, this.f4772c, wVar.f5646b, this.f4773d);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.v$c */
    public class c implements f0.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f4774a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f4775b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ List f4776c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ f f4777d;

        public c(Activity activity, String str, List list, f fVar) {
            this.f4774a = activity;
            this.f4775b = str;
            this.f4776c = list;
            this.f4777d = fVar;
        }

        @Override // com.im.oldchat.ui.f0.f
        public void a(j0.e eVar) {
            String str;
            if (eVar == null || (str = eVar.f5504a) == null || str.length() == 0) {
                return;
            }
            AbstractC0230v.h(this.f4774a, this.f4775b, this.f4776c, eVar.f5504a, this.f4777d);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.v$d */
    public class d implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f4778a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ f f4779b;

        public d(Activity activity, f fVar) {
            this.f4778a = activity;
            this.f4779b = fVar;
        }

        @Override // g0.d.i
        public void b(String str) {
            Toast.makeText(this.f4778a, f0.j.f5224k0, 0).show();
            f fVar = this.f4779b;
            if (fVar != null) {
                fVar.a();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(this.f4778a, f0.j.f5200X, 0).show();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.v$e */
    public class e implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f4780a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ f f4781b;

        public e(Activity activity, f fVar) {
            this.f4780a = activity;
            this.f4781b = fVar;
        }

        @Override // g0.d.i
        public void b(String str) {
            Toast.makeText(this.f4780a, f0.j.f5224k0, 0).show();
            f fVar = this.f4781b;
            if (fVar != null) {
                fVar.a();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(this.f4780a, f0.j.f5200X, 0).show();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.v$f */
    public interface f {
        void a();
    }

    public static void e(Activity activity, String str, List list, f fVar) {
        f0.k(activity, j0.c.b(activity), activity.getString(f0.j.f5210d0), new b(activity, str, list, fVar));
    }

    public static void f(Activity activity, String str, List list, f fVar) {
        f0.l(activity, j0.f.b(activity), activity.getString(f0.j.f5212e0), new c(activity, str, list, fVar));
    }

    public static void g(Activity activity, String str, List list, String str2, f fVar) {
        if (!o0.G.d().f()) {
            Toast.makeText(activity, f0.j.f5204a0, 0).show();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("to_uid", str2);
            jSONObject.put("msg_type", TextBundle.TEXT_ENTRY);
            jSONObject.put("body", AbstractC0406v.e(activity, list));
            g0.d.S("/direct/send", jSONObject, str, new d(activity, fVar));
        } catch (Exception unused) {
            Toast.makeText(activity, f0.j.f5200X, 0).show();
        }
    }

    public static void h(Activity activity, String str, List list, String str2, f fVar) {
        if (!o0.G.d().f()) {
            Toast.makeText(activity, f0.j.f5204a0, 0).show();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("group_id", str2);
            jSONObject.put("msg_type", TextBundle.TEXT_ENTRY);
            jSONObject.put("body", AbstractC0406v.e(activity, list));
            g0.d.S("/groups/message/send", jSONObject, str, new e(activity, fVar));
        } catch (Exception unused) {
            Toast.makeText(activity, f0.j.f5200X, 0).show();
        }
    }

    public static void i(Activity activity, String str, List list, f fVar) {
        if (activity == null) {
            return;
        }
        if (str == null || str.length() == 0) {
            Toast.makeText(activity, f0.j.f5208c0, 0).show();
            return;
        }
        List listH = AbstractC0406v.h(list);
        if (listH.isEmpty()) {
            Toast.makeText(activity, f0.j.f5206b0, 0).show();
        } else {
            new c.a(activity, f0.k.f5244a).s(f0.j.f5214f0).g(new CharSequence[]{activity.getString(f0.j.f5226l0), activity.getString(f0.j.f5228m0)}, new a(activity, str, listH, fVar)).j(R.string.cancel, null).v();
        }
    }
}

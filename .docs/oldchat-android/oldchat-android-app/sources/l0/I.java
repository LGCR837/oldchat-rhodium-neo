package l0;

import android.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.widget.Toast;
import g0.d;
import o.c;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class I {

    public class a implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f5892a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f5893b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f5894c;

        public a(Activity activity, String str, String str2) {
            this.f5892a = activity;
            this.f5893b = str;
            this.f5894c = str2;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            I.this.f(this.f5892a, this.f5893b, this.f5894c);
        }
    }

    public class b implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f5896a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f5897b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f5898c;

        public b(Activity activity, String str, String str2) {
            this.f5896a = activity;
            this.f5897b = str;
            this.f5898c = str2;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            I.this.e(this.f5896a, this.f5897b, this.f5898c);
        }
    }

    public class c implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ProgressDialog f5900a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Activity f5901b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f5902c;

        public c(ProgressDialog progressDialog, Activity activity, String str) {
            this.f5900a = progressDialog;
            this.f5901b = activity;
            this.f5902c = str;
        }

        @Override // g0.d.i
        public void b(String str) {
            this.f5900a.dismiss();
            j0.j.f(this.f5901b, this.f5902c);
            Toast.makeText(this.f5901b, f0.j.e1, 0).show();
            this.f5901b.setResult(-1);
            this.f5901b.finish();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            this.f5900a.dismiss();
            if (str == null || !str.contains("owner_cannot_leave")) {
                Toast.makeText(this.f5901b, f0.j.b1, 0).show();
            } else {
                Toast.makeText(this.f5901b, f0.j.d1, 0).show();
            }
        }
    }

    public class d implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ProgressDialog f5904a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Activity f5905b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f5906c;

        public d(ProgressDialog progressDialog, Activity activity, String str) {
            this.f5904a = progressDialog;
            this.f5905b = activity;
            this.f5906c = str;
        }

        @Override // g0.d.i
        public void b(String str) {
            this.f5904a.dismiss();
            j0.j.f(this.f5905b, this.f5906c);
            Toast.makeText(this.f5905b, f0.j.X0, 0).show();
            this.f5905b.setResult(-1);
            this.f5905b.finish();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            this.f5904a.dismiss();
            Toast.makeText(this.f5905b, f0.j.V0, 0).show();
        }
    }

    public void c(Activity activity, String str, String str2, int i2) {
        if (i2 != 2) {
            Toast.makeText(activity, f0.j.j1, 0).show();
        } else {
            new c.a(activity, f0.k.f5244a).s(f0.j.S0).h(f0.j.U0).p(f0.j.T0, new b(activity, str, str2)).j(R.string.cancel, null).v();
        }
    }

    public void d(Activity activity, String str, String str2) {
        new c.a(activity, f0.k.f5244a).s(f0.j.Y0).h(f0.j.a1).p(f0.j.Z0, new a(activity, str, str2)).j(R.string.cancel, null).v();
    }

    public final void e(Activity activity, String str, String str2) {
        if (str == null || str.length() == 0) {
            return;
        }
        ProgressDialog progressDialogShow = ProgressDialog.show(activity, null, activity.getString(f0.j.W0), true, false);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("group_id", str);
            g0.d.S("/groups/dissolve", jSONObject, str2, new d(progressDialogShow, activity, str));
        } catch (Exception unused) {
            progressDialogShow.dismiss();
            Toast.makeText(activity, f0.j.V0, 0).show();
        }
    }

    public final void f(Activity activity, String str, String str2) {
        if (str == null || str.length() == 0) {
            return;
        }
        ProgressDialog progressDialogShow = ProgressDialog.show(activity, null, activity.getString(f0.j.c1), true, false);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("group_id", str);
            g0.d.S("/groups/leave", jSONObject, str2, new c(progressDialogShow, activity, str));
        } catch (Exception unused) {
            progressDialogShow.dismiss();
            Toast.makeText(activity, f0.j.b1, 0).show();
        }
    }
}

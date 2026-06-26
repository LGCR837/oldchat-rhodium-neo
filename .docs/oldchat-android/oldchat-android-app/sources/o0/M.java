package o0;

import android.R;
import android.app.Activity;
import android.content.DialogInterface;
import android.util.TypedValue;
import android.widget.ScrollView;
import android.widget.TextView;
import o.c;

/* JADX INFO: loaded from: classes.dex */
public abstract class M {

    public class a implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Runnable f7026a;

        public a(Runnable runnable) {
            this.f7026a = runnable;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            Runnable runnable = this.f7026a;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    public static int a(Activity activity, int i2) {
        return activity == null ? i2 : (int) TypedValue.applyDimension(1, i2, activity.getResources().getDisplayMetrics());
    }

    public static void b(Activity activity, Runnable runnable) {
        if (activity == null) {
            return;
        }
        ScrollView scrollView = new ScrollView(activity);
        TextView textView = new TextView(activity);
        int iA = a(activity, 16);
        textView.setPadding(iA, iA, iA, iA);
        textView.setTextSize(2, 13.0f);
        try {
            textView.setTextColor(activity.getResources().getColor(f0.d.f4973j));
        } catch (Exception unused) {
        }
        textView.setText(f0.j.u2);
        scrollView.addView(textView);
        new c.a(activity).s(f0.j.v2).u(scrollView).p(f0.j.s2, new a(runnable)).j(R.string.cancel, null).v();
    }
}

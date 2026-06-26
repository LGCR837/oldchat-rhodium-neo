package l0;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.TextView;
import com.im.oldchat.ui.UiSettingsActivity;
import o.c;

/* JADX INFO: loaded from: classes.dex */
public abstract class L0 {

    public class a implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ UiSettingsActivity f5934a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ TextView f5935b;

        public a(UiSettingsActivity uiSettingsActivity, TextView textView) {
            this.f5934a = uiSettingsActivity;
            this.f5935b = textView;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            o0.V.q(this.f5934a, o0.X.n(i2 - 1));
            TextView textView = this.f5935b;
            if (textView != null) {
                textView.setText(o0.X.h(this.f5934a));
            }
            dialogInterface.dismiss();
            this.f5934a.s0();
        }
    }

    public class b implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ UiSettingsActivity f5936a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ TextView f5937b;

        public b(UiSettingsActivity uiSettingsActivity, TextView textView) {
            this.f5936a = uiSettingsActivity;
            this.f5937b = textView;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            o0.V.p(this.f5936a, o0.X.m(i2 - 1));
            TextView textView = this.f5937b;
            if (textView != null) {
                textView.setText(o0.X.g(this.f5936a));
            }
            dialogInterface.dismiss();
            this.f5936a.s0();
        }
    }

    public static void a(UiSettingsActivity uiSettingsActivity, TextView textView) {
        if (uiSettingsActivity == null) {
            return;
        }
        new c.a(uiSettingsActivity).t("对方气泡颜色").r(o0.X.c(), o0.X.m(o0.V.a(uiSettingsActivity)) + 1, new b(uiSettingsActivity, textView)).j(R.string.cancel, null).v();
    }

    public static void b(UiSettingsActivity uiSettingsActivity, TextView textView) {
        if (uiSettingsActivity == null) {
            return;
        }
        new c.a(uiSettingsActivity).t("我方气泡颜色").r(o0.X.d(), o0.X.n(o0.V.b(uiSettingsActivity)) + 1, new a(uiSettingsActivity, textView)).j(R.string.cancel, null).v();
    }

    public static void c(Context context, TextView textView, TextView textView2) {
        if (context == null) {
            return;
        }
        if (textView != null) {
            textView.setText(o0.X.h(context));
        }
        if (textView2 != null) {
            textView2.setText(o0.X.g(context));
        }
    }
}

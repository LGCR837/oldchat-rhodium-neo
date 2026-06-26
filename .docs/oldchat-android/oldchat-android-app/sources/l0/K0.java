package l0;

import android.R;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import com.im.oldchat.ui.UiSettingsActivity;

/* JADX INFO: loaded from: classes.dex */
public abstract class K0 {

    public class a implements CompoundButton.OnCheckedChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ UiSettingsActivity f5924a;

        public a(UiSettingsActivity uiSettingsActivity) {
            this.f5924a = uiSettingsActivity;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
            o0.V.r(this.f5924a, z2);
            View viewFindViewById = this.f5924a.findViewById(R.id.content);
            if (viewFindViewById == null) {
                return;
            }
            if (z2) {
                n0.b.f(viewFindViewById);
            } else {
                viewFindViewById.clearAnimation();
            }
        }
    }

    public static void a(UiSettingsActivity uiSettingsActivity, SwitchCompat switchCompat) {
        if (uiSettingsActivity == null || switchCompat == null) {
            return;
        }
        switchCompat.setChecked(o0.V.f(uiSettingsActivity));
        switchCompat.setOnCheckedChangeListener(new a(uiSettingsActivity));
    }
}

package l0;

import android.support.v7.widget.SwitchCompat;
import android.widget.CompoundButton;
import com.im.oldchat.ui.UiSettingsActivity;

/* JADX INFO: loaded from: classes.dex */
public abstract class M0 {

    public class a implements CompoundButton.OnCheckedChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ UiSettingsActivity f5941a;

        public a(UiSettingsActivity uiSettingsActivity) {
            this.f5941a = uiSettingsActivity;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
            o0.V.z(this.f5941a, z2);
            this.f5941a.s0();
        }
    }

    public static void a(UiSettingsActivity uiSettingsActivity, SwitchCompat switchCompat) {
        if (uiSettingsActivity == null || switchCompat == null) {
            return;
        }
        switchCompat.setChecked(o0.V.m(uiSettingsActivity));
        switchCompat.setOnCheckedChangeListener(new a(uiSettingsActivity));
    }
}

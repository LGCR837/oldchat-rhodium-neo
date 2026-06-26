package c;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class r extends AbstractActivityC0160q {
    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View viewU = u(view, str, context, attributeSet);
        return (viewU != null || Build.VERSION.SDK_INT < 11) ? viewU : super.onCreateView(view, str, context, attributeSet);
    }
}

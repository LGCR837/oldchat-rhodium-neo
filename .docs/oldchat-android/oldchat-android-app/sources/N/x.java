package n;

import android.view.View;
import android.widget.ListView;

/* JADX INFO: loaded from: classes.dex */
public abstract class x {
    public static void a(ListView listView, int i2) {
        View childAt;
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        if (firstVisiblePosition == -1 || (childAt = listView.getChildAt(0)) == null) {
            return;
        }
        listView.setSelectionFromTop(firstVisiblePosition, childAt.getTop() - i2);
    }
}

package n;

import android.widget.ListView;

/* JADX INFO: loaded from: classes.dex */
public class v extends AbstractViewOnTouchListenerC0418a {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final ListView f6692s;

    public v(ListView listView) {
        super(listView);
        this.f6692s = listView;
    }

    @Override // n.AbstractViewOnTouchListenerC0418a
    public boolean a(int i2) {
        return false;
    }

    @Override // n.AbstractViewOnTouchListenerC0418a
    public boolean b(int i2) {
        ListView listView = this.f6692s;
        int count = listView.getCount();
        if (count == 0) {
            return false;
        }
        int childCount = listView.getChildCount();
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int i3 = firstVisiblePosition + childCount;
        if (i2 > 0) {
            if (i3 >= count && listView.getChildAt(childCount - 1).getBottom() <= listView.getHeight()) {
                return false;
            }
        } else {
            if (i2 >= 0) {
                return false;
            }
            if (firstVisiblePosition <= 0 && listView.getChildAt(0).getTop() >= 0) {
                return false;
            }
        }
        return true;
    }

    @Override // n.AbstractViewOnTouchListenerC0418a
    public void j(int i2, int i3) {
        w.a(this.f6692s, i3);
    }
}

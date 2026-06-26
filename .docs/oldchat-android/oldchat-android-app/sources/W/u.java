package w;

import android.content.Context;
import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

/* JADX INFO: loaded from: classes.dex */
public abstract class u implements z, android.support.v7.view.menu.i, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Rect f8011a;

    public static int o(ListAdapter listAdapter, ViewGroup viewGroup, Context context, int i2) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = listAdapter.getCount();
        View view = null;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < count; i5++) {
            int itemViewType = listAdapter.getItemViewType(i5);
            if (itemViewType != i4) {
                view = null;
                i4 = itemViewType;
            }
            if (viewGroup == null) {
                viewGroup = new FrameLayout(context);
            }
            view = listAdapter.getView(i5, view, viewGroup);
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth >= i2) {
                return i2;
            }
            if (measuredWidth > i3) {
                i3 = measuredWidth;
            }
        }
        return i3;
    }

    public static boolean x(android.support.v7.view.menu.e eVar) {
        int size = eVar.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = eVar.getItem(i2);
            if (item.isVisible() && item.getIcon() != null) {
                return true;
            }
        }
        return false;
    }

    public static android.support.v7.view.menu.d y(ListAdapter listAdapter) {
        return listAdapter instanceof HeaderViewListAdapter ? (android.support.v7.view.menu.d) ((HeaderViewListAdapter) listAdapter).getWrappedAdapter() : (android.support.v7.view.menu.d) listAdapter;
    }

    @Override // android.support.v7.view.menu.i
    public boolean e(android.support.v7.view.menu.e eVar, android.support.v7.view.menu.g gVar) {
        return false;
    }

    @Override // android.support.v7.view.menu.i
    public boolean j(android.support.v7.view.menu.e eVar, android.support.v7.view.menu.g gVar) {
        return false;
    }

    public abstract void l(android.support.v7.view.menu.e eVar);

    public boolean m() {
        return true;
    }

    public Rect n() {
        return this.f8011a;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
        ListAdapter listAdapter = (ListAdapter) adapterView.getAdapter();
        y(listAdapter).f1047a.L((MenuItem) listAdapter.getItem(i2), this, m() ? 0 : 4);
    }

    public abstract void p(View view);

    public void q(Rect rect) {
        this.f8011a = rect;
    }

    public abstract void r(boolean z2);

    public abstract void s(int i2);

    public abstract void t(int i2);

    public abstract void u(PopupWindow.OnDismissListener onDismissListener);

    public abstract void v(boolean z2);

    public abstract void w(int i2);

    @Override // android.support.v7.view.menu.i
    public void g(Context context, android.support.v7.view.menu.e eVar) {
    }
}

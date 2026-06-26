package x;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v7.view.menu.ListMenuItemView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import c.AbstractC0115G;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class T extends M implements O {

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public static Method f8090J;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public O f8091I;

    public static class a extends K {

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public final int f8092o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public final int f8093p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public O f8094q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public MenuItem f8095r;

        public a(Context context, boolean z2) {
            super(context, z2);
            Configuration configuration = context.getResources().getConfiguration();
            if (Build.VERSION.SDK_INT < 17 || 1 != configuration.getLayoutDirection()) {
                this.f8092o = 22;
                this.f8093p = 21;
            } else {
                this.f8092o = 21;
                this.f8093p = 22;
            }
        }

        @Override // x.K, android.view.ViewGroup, android.view.View
        public /* bridge */ /* synthetic */ boolean hasFocus() {
            return super.hasFocus();
        }

        @Override // x.K, android.view.View
        public /* bridge */ /* synthetic */ boolean hasWindowFocus() {
            return super.hasWindowFocus();
        }

        @Override // x.K, android.view.View
        public /* bridge */ /* synthetic */ boolean isFocused() {
            return super.isFocused();
        }

        @Override // x.K, android.view.View
        public /* bridge */ /* synthetic */ boolean isInTouchMode() {
            return super.isInTouchMode();
        }

        @Override // x.K
        public /* bridge */ /* synthetic */ boolean k(MotionEvent motionEvent, int i2) {
            return super.k(motionEvent, i2);
        }

        @Override // android.view.View
        public boolean onHoverEvent(MotionEvent motionEvent) {
            android.support.v7.view.menu.d dVar;
            int headersCount;
            int iPointToPosition;
            int i2;
            if (this.f8094q != null) {
                ListAdapter adapter = getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                    headersCount = headerViewListAdapter.getHeadersCount();
                    dVar = (android.support.v7.view.menu.d) headerViewListAdapter.getWrappedAdapter();
                } else {
                    dVar = (android.support.v7.view.menu.d) adapter;
                    headersCount = 0;
                }
                android.support.v7.view.menu.g item = (motionEvent.getAction() == 10 || (iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) == -1 || (i2 = iPointToPosition - headersCount) < 0 || i2 >= dVar.getCount()) ? null : dVar.getItem(i2);
                MenuItem menuItem = this.f8095r;
                if (menuItem != item) {
                    android.support.v7.view.menu.e eVarB = dVar.b();
                    if (menuItem != null) {
                        this.f8094q.b(eVarB, menuItem);
                    }
                    this.f8095r = item;
                    if (item != null) {
                        this.f8094q.a(eVarB, item);
                    }
                }
            }
            return super.onHoverEvent(motionEvent);
        }

        @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i2, KeyEvent keyEvent) {
            ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
            if (listMenuItemView != null && i2 == this.f8092o) {
                if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                    performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            }
            if (listMenuItemView == null || i2 != this.f8093p) {
                return super.onKeyDown(i2, keyEvent);
            }
            setSelection(-1);
            ((android.support.v7.view.menu.d) getAdapter()).b().e(false);
            return true;
        }

        public void setHoverListener(O o2) {
            this.f8094q = o2;
        }
    }

    static {
        try {
            f8090J = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
        } catch (NoSuchMethodException unused) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    public T(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }

    public void I(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.f8046E.setEnterTransition(AbstractC0115G.a(obj));
        }
    }

    public void J(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.f8046E.setExitTransition(AbstractC0115G.a(obj));
        }
    }

    public void K(O o2) {
        this.f8091I = o2;
    }

    public void L(boolean z2) {
        Method method = f8090J;
        if (method != null) {
            try {
                method.invoke(this.f8046E, Boolean.valueOf(z2));
            } catch (Exception unused) {
                Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
            }
        }
    }

    @Override // x.O
    public void a(android.support.v7.view.menu.e eVar, MenuItem menuItem) {
        O o2 = this.f8091I;
        if (o2 != null) {
            o2.a(eVar, menuItem);
        }
    }

    @Override // x.O
    public void b(android.support.v7.view.menu.e eVar, MenuItem menuItem) {
        O o2 = this.f8091I;
        if (o2 != null) {
            o2.b(eVar, menuItem);
        }
    }

    @Override // x.M
    public K f(Context context, boolean z2) {
        a aVar = new a(context, z2);
        aVar.setHoverListener(this);
        return aVar;
    }
}

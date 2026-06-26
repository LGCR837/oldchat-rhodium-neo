package w;

import android.content.Context;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;
import h.InterfaceMenuItemC0249b;
import k.AbstractC0299p;
import w.MenuItemC0484i;

/* JADX INFO: loaded from: classes.dex */
public class t extends MenuItemC0484i {

    public class a extends MenuItemC0484i.a implements ActionProvider.VisibilityListener {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public AbstractC0299p.b f8009f;

        public a(Context context, ActionProvider actionProvider) {
            super(context, actionProvider);
        }

        @Override // k.AbstractC0299p
        public boolean b() {
            return this.f8004d.isVisible();
        }

        @Override // k.AbstractC0299p
        public View d(MenuItem menuItem) {
            return this.f8004d.onCreateActionView(menuItem);
        }

        @Override // k.AbstractC0299p
        public boolean g() {
            return this.f8004d.overridesItemVisibility();
        }

        @Override // k.AbstractC0299p
        public void j(AbstractC0299p.b bVar) {
            this.f8009f = bVar;
            this.f8004d.setVisibilityListener(bVar != null ? this : null);
        }

        @Override // android.view.ActionProvider.VisibilityListener
        public void onActionProviderVisibilityChanged(boolean z2) {
            AbstractC0299p.b bVar = this.f8009f;
            if (bVar != null) {
                bVar.onActionProviderVisibilityChanged(z2);
            }
        }
    }

    public t(Context context, InterfaceMenuItemC0249b interfaceMenuItemC0249b) {
        super(context, interfaceMenuItemC0249b);
    }

    @Override // w.MenuItemC0484i
    public MenuItemC0484i.a k(ActionProvider actionProvider) {
        return new a(this.f7999b, actionProvider);
    }
}

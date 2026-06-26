package android.support.v7.view.menu;

import android.support.v7.view.menu.j;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class d extends BaseAdapter {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int f1046f = p.g.f7828l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public e f1047a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1048b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1049c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f1050d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final LayoutInflater f1051e;

    public d(e eVar, LayoutInflater layoutInflater, boolean z2) {
        this.f1050d = z2;
        this.f1051e = layoutInflater;
        this.f1047a = eVar;
        a();
    }

    public void a() {
        g gVarV = this.f1047a.v();
        if (gVarV != null) {
            ArrayList arrayListZ = this.f1047a.z();
            int size = arrayListZ.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (((g) arrayListZ.get(i2)) == gVarV) {
                    this.f1048b = i2;
                    return;
                }
            }
        }
        this.f1048b = -1;
    }

    public e b() {
        return this.f1047a;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public g getItem(int i2) {
        ArrayList arrayListZ = this.f1050d ? this.f1047a.z() : this.f1047a.E();
        int i3 = this.f1048b;
        if (i3 >= 0 && i2 >= i3) {
            i2++;
        }
        return (g) arrayListZ.get(i2);
    }

    public void d(boolean z2) {
        this.f1049c = z2;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f1048b < 0 ? (this.f1050d ? this.f1047a.z() : this.f1047a.E()).size() : r0.size() - 1;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f1051e.inflate(f1046f, viewGroup, false);
        }
        j.a aVar = (j.a) view;
        if (this.f1049c) {
            ((ListMenuItemView) view).setForceShowIcon(true);
        }
        aVar.b(getItem(i2), 0);
        return view;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}

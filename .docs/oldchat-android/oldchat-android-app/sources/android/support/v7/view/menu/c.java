package android.support.v7.view.menu;

import android.content.Context;
import android.support.v7.view.menu.i;
import android.support.v7.view.menu.j;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class c implements i, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f1035a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public LayoutInflater f1036b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public e f1037c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ExpandedMenuView f1038d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1039e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1040f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1041g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public i.a f1042h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public a f1043i;

    public class a extends BaseAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1044a = -1;

        public a() {
            a();
        }

        public void a() {
            g gVarV = c.this.f1037c.v();
            if (gVarV != null) {
                ArrayList arrayListZ = c.this.f1037c.z();
                int size = arrayListZ.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (((g) arrayListZ.get(i2)) == gVarV) {
                        this.f1044a = i2;
                        return;
                    }
                }
            }
            this.f1044a = -1;
        }

        @Override // android.widget.Adapter
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public g getItem(int i2) {
            ArrayList arrayListZ = c.this.f1037c.z();
            int i3 = i2 + c.this.f1039e;
            int i4 = this.f1044a;
            if (i4 >= 0 && i3 >= i4) {
                i3++;
            }
            return (g) arrayListZ.get(i3);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int size = c.this.f1037c.z().size() - c.this.f1039e;
            return this.f1044a < 0 ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            if (view == null) {
                c cVar = c.this;
                view = cVar.f1036b.inflate(cVar.f1041g, viewGroup, false);
            }
            ((j.a) view).b(getItem(i2), 0);
            return view;
        }

        @Override // android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }
    }

    public c(Context context, int i2) {
        this(i2, 0);
        this.f1035a = context;
        this.f1036b = LayoutInflater.from(context);
    }

    @Override // android.support.v7.view.menu.i
    public void a(e eVar, boolean z2) {
        i.a aVar = this.f1042h;
        if (aVar != null) {
            aVar.a(eVar, z2);
        }
    }

    @Override // android.support.v7.view.menu.i
    public boolean b(l lVar) {
        if (!lVar.hasVisibleItems()) {
            return false;
        }
        new f(lVar).d(null);
        i.a aVar = this.f1042h;
        if (aVar == null) {
            return true;
        }
        aVar.b(lVar);
        return true;
    }

    public ListAdapter c() {
        if (this.f1043i == null) {
            this.f1043i = new a();
        }
        return this.f1043i;
    }

    @Override // android.support.v7.view.menu.i
    public boolean d() {
        return false;
    }

    @Override // android.support.v7.view.menu.i
    public boolean e(e eVar, g gVar) {
        return false;
    }

    @Override // android.support.v7.view.menu.i
    public void f(i.a aVar) {
        this.f1042h = aVar;
    }

    @Override // android.support.v7.view.menu.i
    public void g(Context context, e eVar) {
        if (this.f1040f != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, this.f1040f);
            this.f1035a = contextThemeWrapper;
            this.f1036b = LayoutInflater.from(contextThemeWrapper);
        } else if (this.f1035a != null) {
            this.f1035a = context;
            if (this.f1036b == null) {
                this.f1036b = LayoutInflater.from(context);
            }
        }
        this.f1037c = eVar;
        a aVar = this.f1043i;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    public j h(ViewGroup viewGroup) {
        if (this.f1038d == null) {
            this.f1038d = (ExpandedMenuView) this.f1036b.inflate(p.g.f7822f, viewGroup, false);
            if (this.f1043i == null) {
                this.f1043i = new a();
            }
            this.f1038d.setAdapter((ListAdapter) this.f1043i);
            this.f1038d.setOnItemClickListener(this);
        }
        return this.f1038d;
    }

    @Override // android.support.v7.view.menu.i
    public boolean j(e eVar, g gVar) {
        return false;
    }

    @Override // android.support.v7.view.menu.i
    public void k(boolean z2) {
        a aVar = this.f1043i;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
        this.f1037c.L(this.f1043i.getItem(i2), this, 0);
    }

    public c(int i2, int i3) {
        this.f1041g = i2;
        this.f1040f = i3;
    }
}
